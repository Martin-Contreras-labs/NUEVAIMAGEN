package models.tables;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
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

import controllers.HomeController;
import models.utilities.Archivos;
import models.utilities.Fechas;


public class Guia {
	public Long id;
	public Long id_cotizacion;
	public Long numero;
	public String fecha;
	public String docAnexo;
	public String guiaPDF;
	public String guiaXml;
	public String observaciones;
	public String numGuiaCliente;
	public String jsonGenerado;
	
	
	public Long id_bodegaOrigen;
	public Long id_bodegaDestino; // bodegaDestino es equiv a bodegaEmpresa
	public String bodegaOrigen;
	public String bodegaDestino;
	public Long numeroCotizacion;
	public String tipoGuia;
	public Long numeroOt;
	
	public Long id_ot;
	
	public String linkFolio;
	
	public String response;
	
	public Long id_transportista;
	
	public String fotos;
	
	public Long id_userCrea;
	public Long id_userModifica;
	public String fechaUserModifica;
	
	public Long id_sucursalOrigen;
	public Long id_sucursalDestino;
	public String nameSucursalOrigen;
	public String nameSucursalDestino;
	
	public String nameComercial;
	
	public String fechaActualizacion;
	public String fechaEnvio;
	
	public String totalKg;
	public String totalM2;
	
	
	public Guia(Long id, Long id_cotizacion, Long numero, String fecha, String docAnexo, String guiaPDF, String guiaXml,
			String observaciones, String numGuiaCliente, String jsonGenerado, Long id_bodegaOrigen,
			Long id_bodegaDestino, String bodegaOrigen, String bodegaDestino, Long numeroCotizacion, String tipoGuia,
			Long numeroOt, Long id_ot, String linkFolio, String response, Long id_transportista, String fotos,
			Long id_userCrea, Long id_userModifica, String fechaUserModifica, Long id_sucursalOrigen,
			Long id_sucursalDestino, String nameSucursalOrigen, String nameSucursalDestino, String nameComercial,
			String fechaActualizacion, String fechaEnvio, String totalKg, String totalM2) {
		super();
		this.id = id;
		this.id_cotizacion = id_cotizacion;
		this.numero = numero;
		this.fecha = fecha;
		this.docAnexo = docAnexo;
		this.guiaPDF = guiaPDF;
		this.guiaXml = guiaXml;
		this.observaciones = observaciones;
		this.numGuiaCliente = numGuiaCliente;
		this.jsonGenerado = jsonGenerado;
		this.id_bodegaOrigen = id_bodegaOrigen;
		this.id_bodegaDestino = id_bodegaDestino;
		this.bodegaOrigen = bodegaOrigen;
		this.bodegaDestino = bodegaDestino;
		this.numeroCotizacion = numeroCotizacion;
		this.tipoGuia = tipoGuia;
		this.numeroOt = numeroOt;
		this.id_ot = id_ot;
		this.linkFolio = linkFolio;
		this.response = response;
		this.id_transportista = id_transportista;
		this.fotos = fotos;
		this.id_userCrea = id_userCrea;
		this.id_userModifica = id_userModifica;
		this.fechaUserModifica = fechaUserModifica;
		this.id_sucursalOrigen = id_sucursalOrigen;
		this.id_sucursalDestino = id_sucursalDestino;
		this.nameSucursalOrigen = nameSucursalOrigen;
		this.nameSucursalDestino = nameSucursalDestino;
		this.nameComercial = nameComercial;
		this.fechaActualizacion = fechaActualizacion;
		this.fechaEnvio = fechaEnvio;
		this.totalKg = totalKg;
		this.totalM2 = totalM2;
	}

	public Guia() {
		super();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId_cotizacion() {
		return id_cotizacion;
	}

	public void setId_cotizacion(Long id_cotizacion) {
		this.id_cotizacion = id_cotizacion;
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

	public String getDocAnexo() {
		return docAnexo;
	}

	public void setDocAnexo(String docAnexo) {
		this.docAnexo = docAnexo;
	}

	public String getGuiaPDF() {
		return guiaPDF;
	}

	public void setGuiaPDF(String guiaPDF) {
		this.guiaPDF = guiaPDF;
	}

	public String getGuiaXml() {
		return guiaXml;
	}

	public void setGuiaXml(String guiaXml) {
		this.guiaXml = guiaXml;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getNumGuiaCliente() {
		return numGuiaCliente;
	}

	public void setNumGuiaCliente(String numGuiaCliente) {
		this.numGuiaCliente = numGuiaCliente;
	}

	public String getJsonGenerado() {
		return jsonGenerado;
	}

	public void setJsonGenerado(String jsonGenerado) {
		this.jsonGenerado = jsonGenerado;
	}

	public Long getId_bodegaOrigen() {
		return id_bodegaOrigen;
	}

	public void setId_bodegaOrigen(Long id_bodegaOrigen) {
		this.id_bodegaOrigen = id_bodegaOrigen;
	}

	public Long getId_bodegaDestino() {
		return id_bodegaDestino;
	}

	public void setId_bodegaDestino(Long id_bodegaDestino) {
		this.id_bodegaDestino = id_bodegaDestino;
	}

	public String getBodegaOrigen() {
		return bodegaOrigen;
	}

	public void setBodegaOrigen(String bodegaOrigen) {
		this.bodegaOrigen = bodegaOrigen;
	}

	public String getBodegaDestino() {
		return bodegaDestino;
	}

	public void setBodegaDestino(String bodegaDestino) {
		this.bodegaDestino = bodegaDestino;
	}

	public Long getNumeroCotizacion() {
		return numeroCotizacion;
	}

	public void setNumeroCotizacion(Long numeroCotizacion) {
		this.numeroCotizacion = numeroCotizacion;
	}

	public String getTipoGuia() {
		return tipoGuia;
	}

	public void setTipoGuia(String tipoGuia) {
		this.tipoGuia = tipoGuia;
	}

	public Long getNumeroOt() {
		return numeroOt;
	}

	public void setNumeroOt(Long numeroOt) {
		this.numeroOt = numeroOt;
	}

	public Long getId_ot() {
		return id_ot;
	}

	public void setId_ot(Long id_ot) {
		this.id_ot = id_ot;
	}

	public String getLinkFolio() {
		return linkFolio;
	}

	public void setLinkFolio(String linkFolio) {
		this.linkFolio = linkFolio;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Long getId_transportista() {
		return id_transportista;
	}

	public void setId_transportista(Long id_transportista) {
		this.id_transportista = id_transportista;
	}

	public String getFotos() {
		return fotos;
	}

	public void setFotos(String fotos) {
		this.fotos = fotos;
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

	public Long getId_sucursalOrigen() {
		return id_sucursalOrigen;
	}

	public void setId_sucursalOrigen(Long id_sucursalOrigen) {
		this.id_sucursalOrigen = id_sucursalOrigen;
	}

	public Long getId_sucursalDestino() {
		return id_sucursalDestino;
	}

	public void setId_sucursalDestino(Long id_sucursalDestino) {
		this.id_sucursalDestino = id_sucursalDestino;
	}

	public String getNameSucursalOrigen() {
		return nameSucursalOrigen;
	}

	public void setNameSucursalOrigen(String nameSucursalOrigen) {
		this.nameSucursalOrigen = nameSucursalOrigen;
	}

	public String getNameSucursalDestino() {
		return nameSucursalDestino;
	}

	public void setNameSucursalDestino(String nameSucursalDestino) {
		this.nameSucursalDestino = nameSucursalDestino;
	}

	public String getNameComercial() {
		return nameComercial;
	}

	public void setNameComercial(String nameComercial) {
		this.nameComercial = nameComercial;
	}

	public String getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(String fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public String getFechaEnvio() {
		return fechaEnvio;
	}

	public void setFechaEnvio(String fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	public String getTotalKg() {
		return totalKg;
	}

	public void setTotalKg(String totalKg) {
		this.totalKg = totalKg;
	}
	

	public String getTotalM2() {
		return totalM2;
	}

	public void setTotalM2(String totalM2) {
		this.totalM2 = totalM2;
	}


	static SimpleDateFormat myformatfecha = new SimpleDateFormat("dd-MM-yyyy");
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	
	public static Long anioPrimeraGuia(Connection con, String db) {
		Fechas hoy = Fechas.hoy();
		String[] aux = hoy.getFechaStrAAMMDD().split("-");
		Long year = Long.parseLong(aux[0]);
		try {
			PreparedStatement smt = con
					.prepareStatement(" select year(min(guia.fecha)) from `"+db+"`.guia;" );
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
	
	public static Long findNuevoNumero(Connection con, String db) {
		Long aux = (long) 0;
		try {
			PreparedStatement smt = con
					.prepareStatement(" select max(numero)+1 from `"+db+"`.guia;" );
			ResultSet resultado = smt.executeQuery();
			if (resultado.next()) {	
				aux = resultado.getLong(1);
			}else {
				aux = (long) 1;
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		if((long)aux == (long)0) {
			aux = (long)1;
		}
		return (aux);
	}
	
	public static Boolean existeNumero(Connection con,String db, Long numeroGuia) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement(" select guia.id from `"+db+"`.guia where guia.numero = ?;" );
			smt.setLong(1, numeroGuia);
			ResultSet resultado = smt.executeQuery();
			if (resultado.next()) {	
				flag = true;	
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}
	
	public static Guia create(Connection con, String db, Guia aux, String id_userCrea, String id_userModifica) {
		Guia guia = null;
		if(Guia.existeNumero(con, db, aux.getNumero())) {
			aux.setNumero(Guia.findNuevoNumero(con, db));
		}
		try {
			if(id_userModifica.equals("0")) {
				PreparedStatement smt = con
						.prepareStatement("insert into `"+db+"`.guia (numero, numGuiaCliente, fecha, docAnexo, observaciones, id_bodegaOrigen, id_bodegaDestino, id_cotizacion, "
									+ "id_ot, id_transportista, fotos, id_userCrea) " +
								" values (?,?,?,?,?,?,?,?,?,?,?,?)");
				smt.setLong(1, aux.getNumero());
				smt.setString(2, aux.getNumGuiaCliente());
				smt.setString(3, aux.getFecha());
				smt.setString(4, aux.getDocAnexo());
				smt.setString(5, aux.getObservaciones().trim());
				smt.setLong(6, aux.getId_bodegaOrigen());
				smt.setLong(7, aux.getId_bodegaDestino());
				smt.setLong(8, aux.getId_cotizacion());
				smt.setLong(9, aux.getId_ot());
				smt.setLong(10, aux.getId_transportista());
				smt.setString(11, aux.getFotos());
				smt.setString(12, id_userCrea);
				smt.executeUpdate();
				smt.close();
			}else {
				PreparedStatement smt = con
						.prepareStatement("insert into `"+db+"`.guia (numero, numGuiaCliente, fecha, docAnexo, observaciones, id_bodegaOrigen, id_bodegaDestino, id_cotizacion, "
									+ "id_ot, id_transportista, fotos, id_userCrea, id_userModifica, fechaUserModifica) " +
								" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				smt.setLong(1, aux.getNumero());
				smt.setString(2, aux.getNumGuiaCliente());
				smt.setString(3, aux.getFecha());
				smt.setString(4, aux.getDocAnexo());
				smt.setString(5, aux.getObservaciones().trim());
				smt.setLong(6, aux.getId_bodegaOrigen());
				smt.setLong(7, aux.getId_bodegaDestino());
				smt.setLong(8, aux.getId_cotizacion());
				smt.setLong(9, aux.getId_ot());
				smt.setLong(10, aux.getId_transportista());
				smt.setString(11, aux.getFotos());
				smt.setString(12, id_userCrea);
				smt.setString(13, id_userModifica);
				smt.setString(14, Fechas.hoy().getFechaStrAAMMDD());
				smt.executeUpdate();
				smt.close();
			}
			
			guia = Guia.findXNumeroGuia(con, db, aux.getNumero());
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (guia);
	}
	
	public static String tipoGuia (String db, BodegaEmpresa bodegaOrigen, BodegaEmpresa bodegaDestino) {
		String tipoGuia = "";
		if(bodegaOrigen!=null && bodegaDestino!=null) {
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(db);
			if((long)bodegaOrigen.getEsInterna()==(long)1 && (long)bodegaDestino.getEsInterna()>(long)1) {
				tipoGuia = mapeoDiccionario.get("Despacho");
			}else if((long)bodegaOrigen.getEsInterna()>(long)1 && (long)bodegaDestino.getEsInterna()==(long)1) {
				tipoGuia = mapeoDiccionario.get("Devolucion");
			}else{
				tipoGuia = mapeoDiccionario.get("Traslado");
			}
		}
		return(tipoGuia);
	}
	
	public static Guia findXNumeroGuia(Connection con, String db, Long numeroGuia) {
		Guia aux = null;
		try {
			PreparedStatement smt = con
					.prepareStatement(" select  "
							+ "guia.id, "
							+ "guia.numero, "
							+ "guia.fecha, "
							+ "guia.docAnexo, "
					/*5*/	+ "guia.guiaPDF, "
							+ "guia.guiaXml, "
							+ "guia.observaciones, "
							+ "guia.numGuiaCliente, "
							+ "guia.jsonGenerado, "
					/*10*/	+ "guia.id_bodegaOrigen, "
							+ "guia.id_bodegaDestino, " 
							+ "guia.id_cotizacion, "
							+ "guia.id_ot, "
							+ "guia.linkFolio, "
					/*15*/	+ "ifnull(guia.response,0), "
							+ "guia.id_transportista, "
							+ "guia.fotos, "
							+ "'', " 
							+ "guia.id_userCrea, "
					/*20*/	+ "guia.id_userModifica, "
							+ "ifnull(guia.fechaUserModifica,''), "
							+ "guia.totalKg, "
							+ "guia.totalM2 " +
							" from `"+db+"`.guia " +
							" where guia.numero = ?;" );
			smt.setLong(1, numeroGuia);
			
			ResultSet rs = smt.executeQuery();
			Map<Long,BodegaEmpresa> bodegas = BodegaEmpresa.mapAll(con, db);
			Map<Long,Ot> ots = Ot.mapAll(con, db);
			Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
			
			Map<Long,List<Long>> mapIdvsNumCotiSucur = Cotizacion.mapIdvsNumCotiSucur(con, db);
			Map<Long,List<String>> mapIdvsFechActEnvio = Ot.mapIdvsFechActEnvio(con, db);
			
			if (rs.next()) {

				BodegaEmpresa bodegaOrigen = bodegas.get(rs.getLong(10));
				BodegaEmpresa bodegaDestino = bodegas.get(rs.getLong(11));
				
				String nameSucursalOrigen = "Sin Sucursal";
				Long id_sucursalOrigen = (long)0;
				String nombreBodegaOrigen = ""; if(bodegaOrigen!=null) { 
					nombreBodegaOrigen = bodegaOrigen.getNombre();
					nameSucursalOrigen = bodegaOrigen.getNameSucursal(); 
					id_sucursalOrigen = bodegaOrigen.getId_sucursal();
				}
				
				String nameSucursalDestino = "Sin Sucursal";
				Long id_sucursalDestino = (long)0;
				String nombreBodegaDestino = ""; if(bodegaDestino!=null) { 
					nombreBodegaDestino = bodegaDestino.getNombre();
					nameSucursalDestino = bodegaDestino.getNameSucursal();
					id_sucursalDestino = bodegaDestino.getId_sucursal();
				}
				
				Ot ot = ots.get(rs.getLong(13));
				Long numeroOt = (long)0; 
				if(ot!=null) { 
					numeroOt = ot.numero; 
				}
				String tipoGuia = Guia.tipoGuia(db, bodegaOrigen, bodegaDestino);
				
				String nameComercial = "";
    			if((long)bodegaOrigen.getEsInterna() == (long)2) {
    				if(bodegaOrigen.getId_comercial().toString().equals("0")) {
    					nameComercial = "Sin Comercial";
    				}else {
    					Comercial comercial = mapComercial.get(bodegaOrigen.getId_comercial());
    					if(comercial!=null) {
    						nameComercial = comercial.nameUsuario;
	    				}
    				}
    			}else if((long)bodegaDestino.getEsInterna() == (long)2){
    				if(bodegaDestino.getId_comercial().toString().equals("0")) {
    					nameComercial = "Sin Comercial";
    				}else {
    					Comercial comercial = mapComercial.get(bodegaDestino.getId_comercial());
	    				if(comercial!=null) {
	    					nameComercial = comercial.nameUsuario;
	    				}
    				}
    			}else {
    				nameComercial = "";
    			}
				
    			String fechaActualizacion = "";
    			String fechaEnvio = "";
    			List<String> auxFecha = mapIdvsFechActEnvio.get(rs.getLong(13));
    			if(auxFecha!=null) {
    				fechaActualizacion = auxFecha.get(0);
    				fechaEnvio = auxFecha.get(1);
    			}
    			
    			Long numCoti = (long)0;
    			List<Long> auxNumCotiSucur = mapIdvsNumCotiSucur.get(rs.getLong(12));
    			if(auxNumCotiSucur!=null) {
    				numCoti = auxNumCotiSucur.get(0);
    			}
    			
    			String totalKg = myformatdouble2.format(rs.getDouble(22));
    			String totalM2 = myformatdouble2.format(rs.getDouble(23));
    			
				aux = new Guia(rs.getLong(1),rs.getLong(12),rs.getLong(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),
						rs.getString(7),rs.getString(8),rs.getString(9),rs.getLong(10),rs.getLong(11),nombreBodegaOrigen,nombreBodegaDestino,numCoti,tipoGuia,
						numeroOt, rs.getLong(13), rs.getString(14), rs.getString(15), rs.getLong(16), rs.getString(17),rs.getLong(19),rs.getLong(20),rs.getString(21),
						id_sucursalOrigen,id_sucursalDestino,nameSucursalOrigen,nameSucursalDestino,nameComercial, fechaActualizacion, fechaEnvio, totalKg, totalM2);
			}
			
			rs.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (aux);
	}
	
	
	
	public static Guia find(Connection con, String db, Long id_guia) {
		Guia aux = new Guia();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select  "
							+ "guia.id, "
							+ "guia.numero, "
							+ "guia.fecha, "
							+ "guia.docAnexo, "
					/*5*/	+ "guia.guiaPDF, "
							+ "guia.guiaXml, "
							+ "guia.observaciones, "
							+ "guia.numGuiaCliente, "
							+ "guia.jsonGenerado, "
					/*10*/	+ "guia.id_bodegaOrigen, "
							+ "guia.id_bodegaDestino, " 
							+ "guia.id_cotizacion, "
							+ "guia.id_ot, "
							+ "guia.linkFolio, "
					/*15*/	+ "ifnull(guia.response,0), "
							+ "guia.id_transportista, "
							+ "guia.fotos, "
							+ "'', " 
							+ "guia.id_userCrea, "
					/*20*/	+ "guia.id_userModifica, "
							+ "ifnull(guia.fechaUserModifica,''), " 
							+ "guia.totalKg, "
							+ "guia.totalM2 " +
							" from `"+db+"`.guia " +
							" where guia.id = ?;" );
			smt.setLong(1, id_guia);
			ResultSet rs = smt.executeQuery();
			Map<Long,BodegaEmpresa> bodegas = BodegaEmpresa.mapAll(con, db);
			Map<Long,Ot> ots = Ot.mapAll(con, db);
			Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
			
			Map<Long,List<Long>> mapIdvsNumCotiSucur = Cotizacion.mapIdvsNumCotiSucur(con, db);
			Map<Long,List<String>> mapIdvsFechActEnvio = Ot.mapIdvsFechActEnvio(con, db);
			
			if (rs.next()) {

				BodegaEmpresa bodegaOrigen = bodegas.get(rs.getLong(10));
				BodegaEmpresa bodegaDestino = bodegas.get(rs.getLong(11));
				
				String nameSucursalOrigen = "Sin Sucursal";
				Long id_sucursalOrigen = (long)0;
				String nombreBodegaOrigen = ""; if(bodegaOrigen!=null) { 
					nombreBodegaOrigen = bodegaOrigen.getNombre();
					nameSucursalOrigen = bodegaOrigen.getNameSucursal(); 
					id_sucursalOrigen = bodegaOrigen.getId_sucursal();
				}
				
				String nameSucursalDestino = "Sin Sucursal";
				Long id_sucursalDestino = (long)0;
				String nombreBodegaDestino = ""; if(bodegaDestino!=null) { 
					nombreBodegaDestino = bodegaDestino.getNombre();
					nameSucursalDestino = bodegaDestino.getNameSucursal();
					id_sucursalDestino = bodegaDestino.getId_sucursal();
				}
				
				Ot ot = ots.get(rs.getLong(13));
				Long numeroOt = (long)0; 
				if(ot!=null) { 
					numeroOt = ot.numero; 
				}
				String tipoGuia = Guia.tipoGuia(db, bodegaOrigen, bodegaDestino);
				
				String nameComercial = "";
    			if((long)bodegaOrigen.getEsInterna() == (long)2) {
    				if(bodegaOrigen.getId_comercial().toString().equals("0")) {
    					nameComercial = "Sin Comercial";
    				}else {
    					Comercial comercial = mapComercial.get(bodegaOrigen.getId_comercial());
    					if(comercial!=null) {
    						nameComercial = comercial.nameUsuario;
	    				}
    				}
    			}else if((long)bodegaDestino.getEsInterna() == (long)2){
    				if(bodegaDestino.getId_comercial().toString().equals("0")) {
    					nameComercial = "Sin Comercial";
    				}else {
    					Comercial comercial = mapComercial.get(bodegaDestino.getId_comercial());
	    				if(comercial!=null) {
	    					nameComercial = comercial.nameUsuario;
	    				}
    				}
    			}else {
    				nameComercial = "";
    			}
				
    			String fechaActualizacion = "";
    			String fechaEnvio = "";
    			List<String> auxFecha = mapIdvsFechActEnvio.get(rs.getLong(13));
    			if(auxFecha!=null) {
    				fechaActualizacion = auxFecha.get(0);
    				fechaEnvio = auxFecha.get(1);
    			}
    			
    			Long numCoti = (long)0;
    			List<Long> auxNumCotiSucur = mapIdvsNumCotiSucur.get(rs.getLong(12));
    			if(auxNumCotiSucur!=null) {
    				numCoti = auxNumCotiSucur.get(0);
    			}
    			
    			String totalKg = myformatdouble2.format(rs.getDouble(22));
    			String totalM2 = myformatdouble2.format(rs.getDouble(23));
    			
				aux = new Guia(rs.getLong(1),rs.getLong(12),rs.getLong(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),
						rs.getString(7),rs.getString(8),rs.getString(9),rs.getLong(10),rs.getLong(11),nombreBodegaOrigen,nombreBodegaDestino,numCoti,tipoGuia,
						numeroOt, rs.getLong(13), rs.getString(14), rs.getString(15), rs.getLong(16), rs.getString(17),rs.getLong(19),rs.getLong(20),rs.getString(21),
						id_sucursalOrigen,id_sucursalDestino,nameSucursalOrigen,nameSucursalDestino,nameComercial, fechaActualizacion, fechaEnvio, totalKg, totalM2);
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (aux);
	}
	
	public static Guia findPorAlbum(Connection con, String db, String album) {
		Guia aux = new Guia();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select  "
							+ "guia.id, "
							+ "guia.numero, "
							+ "guia.fecha, "
							+ "guia.docAnexo, "
					/*5*/	+ "guia.guiaPDF, "
							+ "guia.guiaXml, "
							+ "guia.observaciones, "
							+ "guia.numGuiaCliente, "
							+ "guia.jsonGenerado, "
					/*10*/	+ "guia.id_bodegaOrigen, "
							+ "guia.id_bodegaDestino, " 
							+ "guia.id_cotizacion, "
							+ "guia.id_ot, "
							+ "guia.linkFolio, "
					/*15*/	+ "ifnull(guia.response,0), "
							+ "guia.id_transportista, "
							+ "guia.fotos, "
							+ "'', " 
							+ "guia.id_userCrea, "
					/*20*/	+ "guia.id_userModifica, "
							+ "ifnull(guia.fechaUserModifica,''), " 
							+ "guia.totalKg, " 
							+ "guia.totalM2 " +
							" from `"+db+"`.guia " +
							" where fotos = ?;" );
			smt.setString(1, album);
			ResultSet rs = smt.executeQuery();
			Map<Long,BodegaEmpresa> bodegas = BodegaEmpresa.mapAll(con, db);
			Map<Long,Ot> ots = Ot.mapAll(con, db);
			Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
			
			Map<Long,List<Long>> mapIdvsNumCotiSucur = Cotizacion.mapIdvsNumCotiSucur(con, db);
			Map<Long,List<String>> mapIdvsFechActEnvio = Ot.mapIdvsFechActEnvio(con, db);
			
			if (rs.next()) {

				BodegaEmpresa bodegaOrigen = bodegas.get(rs.getLong(10));
				BodegaEmpresa bodegaDestino = bodegas.get(rs.getLong(11));
				
				String nameSucursalOrigen = "Sin Sucursal";
				Long id_sucursalOrigen = (long)0;
				String nombreBodegaOrigen = ""; if(bodegaOrigen!=null) { 
					nombreBodegaOrigen = bodegaOrigen.getNombre();
					nameSucursalOrigen = bodegaOrigen.getNameSucursal(); 
					id_sucursalOrigen = bodegaOrigen.getId_sucursal();
				}
				
				String nameSucursalDestino = "Sin Sucursal";
				Long id_sucursalDestino = (long)0;
				String nombreBodegaDestino = ""; if(bodegaDestino!=null) { 
					nombreBodegaDestino = bodegaDestino.getNombre();
					nameSucursalDestino = bodegaDestino.getNameSucursal();
					id_sucursalDestino = bodegaDestino.getId_sucursal();
				}
				
				Ot ot = ots.get(rs.getLong(13));
				Long numeroOt = (long)0; 
				if(ot!=null) { 
					numeroOt = ot.numero; 
				}
				String tipoGuia = Guia.tipoGuia(db, bodegaOrigen, bodegaDestino);
				
				String nameComercial = "";
    			if((long)bodegaOrigen.getEsInterna() == (long)2) {
    				if(bodegaOrigen.getId_comercial().toString().equals("0")) {
    					nameComercial = "Sin Comercial";
    				}else {
    					Comercial comercial = mapComercial.get(bodegaOrigen.getId_comercial());
    					if(comercial!=null) {
    						nameComercial = comercial.nameUsuario;
	    				}
    				}
    			}else if((long)bodegaDestino.getEsInterna() == (long)2){
    				if(bodegaDestino.getId_comercial().toString().equals("0")) {
    					nameComercial = "Sin Comercial";
    				}else {
    					Comercial comercial = mapComercial.get(bodegaDestino.getId_comercial());
	    				if(comercial!=null) {
	    					nameComercial = comercial.nameUsuario;
	    				}
    				}
    			}else {
    				nameComercial = "";
    			}
    			
    			String fechaActualizacion = "";
    			String fechaEnvio = "";
    			List<String> auxFecha = mapIdvsFechActEnvio.get(rs.getLong(13));
    			if(auxFecha!=null) {
    				fechaActualizacion = auxFecha.get(0);
    				fechaEnvio = auxFecha.get(1);
    			}
    			
    			Long numCoti = (long)0;
    			List<Long> auxNumCotiSucur = mapIdvsNumCotiSucur.get(rs.getLong(12));
    			if(auxNumCotiSucur!=null) {
    				numCoti = auxNumCotiSucur.get(0);
    			}
    			
    			String totalKg = myformatdouble2.format(rs.getDouble(22));
    			String totalM2 = myformatdouble2.format(rs.getDouble(23));
				
				aux = new Guia(rs.getLong(1),rs.getLong(12),rs.getLong(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),
						rs.getString(7),rs.getString(8),rs.getString(9),rs.getLong(10),rs.getLong(11),nombreBodegaOrigen,nombreBodegaDestino,numCoti,tipoGuia,
						numeroOt, rs.getLong(13), rs.getString(14), rs.getString(15), rs.getLong(16), rs.getString(17),rs.getLong(19),rs.getLong(20),rs.getString(21),
						id_sucursalOrigen,id_sucursalDestino,nameSucursalOrigen,nameSucursalDestino,nameComercial, fechaActualizacion, fechaEnvio, totalKg, totalM2);
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (aux);
	}
	
	public static Map<Long,Guia> mapAll(Connection con, String db){
		Map<Long,Guia> map = new HashMap<Long,Guia>();
		List<Guia> lista = Guia.all(con, db);
		for(Guia guia: lista) {
			map.put(guia.getId(), guia);
		}
		
		return(map);
	}
	
	
	public static List<Guia> allDesdeHastaSinNumNeg(Connection con, String db, String permisoPorBodega, String desde, String hasta, String esPorSucursal, String id_sucursal, boolean paraModificar) {
		List<Guia> aux = new ArrayList<Guia>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select  "
							+ "guia.id, "
							+ "guia.numero, "
							+ "guia.fecha, "
							+ "guia.docAnexo, "
					/*5*/	+ "guia.guiaPDF, "
							+ "guia.guiaXml, "
							+ "guia.observaciones, "
							+ "guia.numGuiaCliente, "
							+ "guia.jsonGenerado, "
					/*10*/	+ "guia.id_bodegaOrigen, "
							+ "guia.id_bodegaDestino, " 
							+ "guia.id_cotizacion, "
							+ "guia.id_ot, "
							+ "guia.linkFolio, "
					/*15*/	+ "ifnull(guia.response,0), "
							+ "guia.id_transportista, "
							+ "guia.fotos, "
							+ "'', " 
							+ "guia.id_userCrea, "
					/*20*/	+ "guia.id_userModifica, "
							+ "ifnull(guia.fechaUserModifica,''), " 
							+ "guia.totalKg, " 
							+ "guia.totalM2 "  
							+ " from `"+db+"`.guia " +
							" where guia.numero > 0 and (guia.fecha between ? and ?) "+ permisoPorBodega +
							" order by guia.fecha desc, guia.id desc;" );
			smt.setString(1, desde);
			smt.setString(2, hasta);

			ResultSet rs = smt.executeQuery();
			Map<Long,BodegaEmpresa> bodegas = BodegaEmpresa.mapAll(con, db);
			Map<Long,Ot> ots = Ot.mapAll(con, db);
			Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
			
			Map<Long,List<Long>> mapIdvsNumCotiSucur = Cotizacion.mapIdvsNumCotiSucur(con, db);
			Map<Long,List<String>> mapIdvsFechActEnvio = Ot.mapIdvsFechActEnvio(con, db);
			
			while (rs.next()) {

				BodegaEmpresa bodegaOrigen = bodegas.get(rs.getLong(10));
				BodegaEmpresa bodegaDestino = bodegas.get(rs.getLong(11));
				
				String nameSucursalOrigen = "Sin Sucursal";
				Long id_sucursalOrigen = (long)0;
				String nombreBodegaOrigen = ""; 
				String tipoGuia = "";
				if(bodegaOrigen!=null) { 
					nombreBodegaOrigen = bodegaOrigen.getNombre();
					nameSucursalOrigen = bodegaOrigen.getNameSucursal(); 
					id_sucursalOrigen = bodegaOrigen.getId_sucursal();
					tipoGuia = Guia.tipoGuia(db, bodegaOrigen, bodegaDestino);
				}
				
				String nameSucursalDestino = "Sin Sucursal";
				Long id_sucursalDestino = (long)0;
				String nombreBodegaDestino = "";
				if(bodegaDestino!=null) { 
					nombreBodegaDestino = bodegaDestino.getNombre();
					nameSucursalDestino = bodegaDestino.getNameSucursal();
					id_sucursalDestino = bodegaDestino.getId_sucursal();
				}
				
				Ot ot = ots.get(rs.getLong(13));
				Long numeroOt = (long)0; 
				if(ot!=null) { 
					numeroOt = ot.numero; 
				}
				
				String nameComercial = "";
    			if(bodegaOrigen!=null && (long)bodegaOrigen.getEsInterna() == (long)2) {
    				if(bodegaOrigen.getId_comercial().toString().equals("0")) {
    					nameComercial = "Sin Comercial";
    				}else {
    					Comercial comercial = mapComercial.get(bodegaOrigen.getId_comercial());
    					if(comercial!=null) {
    						nameComercial = comercial.nameUsuario;
	    				}
    				}
    			}else if(bodegaDestino!=null && (long)bodegaDestino.getEsInterna() == (long)2){
    				if(bodegaDestino.getId_comercial().toString().equals("0")) {
    					nameComercial = "Sin Comercial";
    				}else {
    					Comercial comercial = mapComercial.get(bodegaDestino.getId_comercial());
	    				if(comercial!=null) {
	    					nameComercial = comercial.nameUsuario;
	    				}
    				}
    			}else {
    				nameComercial = "";
    			}
    			
    			String fechaActualizacion = "";
    			String fechaEnvio = "";
    			List<String> auxFecha = mapIdvsFechActEnvio.get(rs.getLong(13));
    			if(auxFecha!=null) {
    				fechaActualizacion = auxFecha.get(0);
    				fechaEnvio = auxFecha.get(1);
    			}
    			
    			Long numCoti = (long)0;
    			List<Long> auxNumCotiSucur = mapIdvsNumCotiSucur.get(rs.getLong(12));
    			if(auxNumCotiSucur!=null) {
    				numCoti = auxNumCotiSucur.get(0);
    			}
    			
    			String totalKg = myformatdouble2.format(rs.getDouble(22));
    			String totalM2 = myformatdouble2.format(rs.getDouble(23));
    			
				if(bodegaOrigen!=null && bodegaDestino!=null && esPorSucursal.equals("1")) {
					if(paraModificar) {
						if(bodegaOrigen.getId_sucursal().toString().equals(id_sucursal) && bodegaDestino.getId_sucursal().toString().equals(id_sucursal)) {
							aux.add(new Guia(rs.getLong(1),rs.getLong(12),rs.getLong(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),
									rs.getString(7),rs.getString(8),rs.getString(9),rs.getLong(10),rs.getLong(11),nombreBodegaOrigen,nombreBodegaDestino,numCoti,tipoGuia,
									numeroOt, rs.getLong(13), rs.getString(14), rs.getString(15), rs.getLong(16), rs.getString(17),rs.getLong(19),rs.getLong(20),rs.getString(21),
									id_sucursalOrigen,id_sucursalDestino,nameSucursalOrigen,nameSucursalDestino,nameComercial, fechaActualizacion, fechaEnvio, totalKg, totalM2));
						}
					}else {
						if(bodegaOrigen.getId_sucursal().toString().equals(id_sucursal) || bodegaDestino.getId_sucursal().toString().equals(id_sucursal)) {
							aux.add(new Guia(rs.getLong(1),rs.getLong(12),rs.getLong(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),
									rs.getString(7),rs.getString(8),rs.getString(9),rs.getLong(10),rs.getLong(11),nombreBodegaOrigen,nombreBodegaDestino,numCoti,tipoGuia,
									numeroOt, rs.getLong(13), rs.getString(14), rs.getString(15), rs.getLong(16), rs.getString(17),rs.getLong(19),rs.getLong(20),rs.getString(21),
									id_sucursalOrigen,id_sucursalDestino,nameSucursalOrigen,nameSucursalDestino,nameComercial, fechaActualizacion, fechaEnvio, totalKg, totalM2));
						}
					}
					
				}else {
					aux.add(new Guia(rs.getLong(1),rs.getLong(12),rs.getLong(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),
							rs.getString(7),rs.getString(8),rs.getString(9),rs.getLong(10),rs.getLong(11),nombreBodegaOrigen,nombreBodegaDestino,numCoti,tipoGuia,
							numeroOt, rs.getLong(13), rs.getString(14), rs.getString(15), rs.getLong(16), rs.getString(17),rs.getLong(19),rs.getLong(20),rs.getString(21),
							id_sucursalOrigen,id_sucursalDestino,nameSucursalOrigen,nameSucursalDestino,nameComercial, fechaActualizacion, fechaEnvio, totalKg, totalM2));
				}
				
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (aux);
	}
	
	public static List<Guia> allDesdeHastaSinNumNegClientesVig(Connection con, String db, String permisoPorBodega, String desde, String hasta, String esPorSucursal, String id_sucursal, boolean paraModificar,
						   Map<Long,BodegaEmpresa> bodegas, Map<Long,Ot> ots, Map<Long,Comercial> mapComercial, Map<Long,List<Long>> mapIdvsNumCotiSucur, Map<Long,List<String>> mapIdvsFechActEnvio) {
		List<Guia> aux = new ArrayList<Guia>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select  "
							+ "guia.id, "
							+ "guia.numero, "
							+ "guia.fecha, "
							+ "guia.docAnexo, "
					/*5*/	+ "guia.guiaPDF, "
							+ "guia.guiaXml, "
							+ "guia.observaciones, "
							+ "guia.numGuiaCliente, "
							+ "guia.jsonGenerado, "
					/*10*/	+ "guia.id_bodegaOrigen, "
							+ "guia.id_bodegaDestino, " 
							+ "guia.id_cotizacion, "
							+ "guia.id_ot, "
							+ "guia.linkFolio, "
					/*15*/	+ "ifnull(guia.response,0), "
							+ "guia.id_transportista, "
							+ "guia.fotos, "
							+ "'', " 
							+ "guia.id_userCrea, "
					/*20*/	+ "guia.id_userModifica, "
							+ "ifnull(guia.fechaUserModifica,''), " 
							+ "guia.totalKg, " 
							+ "guia.totalM2 "  
							+ " from `"+db+"`.guia " +
							" where guia.numero > 0 and (guia.fecha between ? and ?) "+ permisoPorBodega +
							" order by guia.fecha desc, guia.id desc;" );
			smt.setString(1, desde);
			smt.setString(2, hasta);

			ResultSet rs = smt.executeQuery();

			
			while (rs.next()) {

				BodegaEmpresa bodegaOrigen = bodegas.get(rs.getLong(10));
				BodegaEmpresa bodegaDestino = bodegas.get(rs.getLong(11));
				
				if(bodegaOrigen!=null && bodegaDestino!=null && bodegaOrigen.getClienteVigente()==(long)1 && bodegaDestino.getClienteVigente()==(long)1) {
					String nameSucursalOrigen = "Sin Sucursal";
					Long id_sucursalOrigen = (long)0;
					String nombreBodegaOrigen = ""; 
					String tipoGuia = "";
					if(bodegaOrigen!=null) { 
						nombreBodegaOrigen = bodegaOrigen.getNombre();
						nameSucursalOrigen = bodegaOrigen.getNameSucursal(); 
						id_sucursalOrigen = bodegaOrigen.getId_sucursal();
						tipoGuia = Guia.tipoGuia(db, bodegaOrigen, bodegaDestino);
					}
					
					String nameSucursalDestino = "Sin Sucursal";
					Long id_sucursalDestino = (long)0;
					String nombreBodegaDestino = "";
					if(bodegaDestino!=null) { 
						nombreBodegaDestino = bodegaDestino.getNombre();
						nameSucursalDestino = bodegaDestino.getNameSucursal();
						id_sucursalDestino = bodegaDestino.getId_sucursal();
					}
					
					Ot ot = ots.get(rs.getLong(13));
					Long numeroOt = (long)0; 
					if(ot!=null) { 
						numeroOt = ot.numero; 
					}
					
					String nameComercial = "";
	    			if(bodegaOrigen!=null && (long)bodegaOrigen.getEsInterna() == (long)2) {
	    				if(bodegaOrigen.getId_comercial().toString().equals("0")) {
	    					nameComercial = "Sin Comercial";
	    				}else {
	    					Comercial comercial = mapComercial.get(bodegaOrigen.getId_comercial());
	    					if(comercial!=null) {
	    						nameComercial = comercial.nameUsuario;
		    				}
	    				}
	    			}else if(bodegaDestino!=null && (long)bodegaDestino.getEsInterna() == (long)2){
	    				if(bodegaDestino.getId_comercial().toString().equals("0")) {
	    					nameComercial = "Sin Comercial";
	    				}else {
	    					Comercial comercial = mapComercial.get(bodegaDestino.getId_comercial());
		    				if(comercial!=null) {
		    					nameComercial = comercial.nameUsuario;
		    				}
	    				}
	    			}else {
	    				nameComercial = "";
	    			}
	    			
	    			String fechaActualizacion = "";
	    			String fechaEnvio = "";
	    			List<String> auxFecha = mapIdvsFechActEnvio.get(rs.getLong(13));
	    			if(auxFecha!=null) {
	    				fechaActualizacion = auxFecha.get(0);
	    				fechaEnvio = auxFecha.get(1);
	    			}
	    			
	    			Long numCoti = (long)0;
	    			List<Long> auxNumCotiSucur = mapIdvsNumCotiSucur.get(rs.getLong(12));
	    			if(auxNumCotiSucur!=null) {
	    				numCoti = auxNumCotiSucur.get(0);
	    			}
	    			
	    			String totalKg = myformatdouble2.format(rs.getDouble(22));
	    			String totalM2 = myformatdouble2.format(rs.getDouble(23));
	    			
					if(bodegaOrigen!=null && bodegaDestino!=null && esPorSucursal.equals("1")) {
						if(paraModificar) {
							if(bodegaOrigen.getId_sucursal().toString().equals(id_sucursal) && bodegaDestino.getId_sucursal().toString().equals(id_sucursal)) {
								aux.add(new Guia(rs.getLong(1),rs.getLong(12),rs.getLong(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),
										rs.getString(7),rs.getString(8),rs.getString(9),rs.getLong(10),rs.getLong(11),nombreBodegaOrigen,nombreBodegaDestino,numCoti,tipoGuia,
										numeroOt, rs.getLong(13), rs.getString(14), rs.getString(15), rs.getLong(16), rs.getString(17),rs.getLong(19),rs.getLong(20),rs.getString(21),
										id_sucursalOrigen,id_sucursalDestino,nameSucursalOrigen,nameSucursalDestino,nameComercial, fechaActualizacion, fechaEnvio, totalKg, totalM2));
							}
						}else {
							if(bodegaOrigen.getId_sucursal().toString().equals(id_sucursal) || bodegaDestino.getId_sucursal().toString().equals(id_sucursal)) {
								aux.add(new Guia(rs.getLong(1),rs.getLong(12),rs.getLong(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),
										rs.getString(7),rs.getString(8),rs.getString(9),rs.getLong(10),rs.getLong(11),nombreBodegaOrigen,nombreBodegaDestino,numCoti,tipoGuia,
										numeroOt, rs.getLong(13), rs.getString(14), rs.getString(15), rs.getLong(16), rs.getString(17),rs.getLong(19),rs.getLong(20),rs.getString(21),
										id_sucursalOrigen,id_sucursalDestino,nameSucursalOrigen,nameSucursalDestino,nameComercial, fechaActualizacion, fechaEnvio, totalKg, totalM2));
							}
						}
						
					}else {
						aux.add(new Guia(rs.getLong(1),rs.getLong(12),rs.getLong(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),
								rs.getString(7),rs.getString(8),rs.getString(9),rs.getLong(10),rs.getLong(11),nombreBodegaOrigen,nombreBodegaDestino,numCoti,tipoGuia,
								numeroOt, rs.getLong(13), rs.getString(14), rs.getString(15), rs.getLong(16), rs.getString(17),rs.getLong(19),rs.getLong(20),rs.getString(21),
								id_sucursalOrigen,id_sucursalDestino,nameSucursalOrigen,nameSucursalDestino,nameComercial, fechaActualizacion, fechaEnvio, totalKg, totalM2));
					}
				}
				
				
				
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (aux);
	}
	
	
	public static List<Guia> all(Connection con, String db) {
		List<Guia> aux = new ArrayList<Guia>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select  "
							+ "guia.id, "
							+ "guia.numero, "
							+ "guia.fecha, "
							+ "guia.docAnexo, "
					/*5*/	+ "guia.guiaPDF, "
							+ "guia.guiaXml, "
							+ "guia.observaciones, "
							+ "guia.numGuiaCliente, "
							+ "guia.jsonGenerado, "
					/*10*/	+ "guia.id_bodegaOrigen, "
							+ "guia.id_bodegaDestino, " 
							+ "guia.id_cotizacion, "
							+ "guia.id_ot, "
							+ "guia.linkFolio, "
					/*15*/	+ "ifnull(guia.response,0), "
							+ "guia.id_transportista, "
							+ "guia.fotos, "
							+ "'', " 
							+ "guia.id_userCrea, "
					/*20*/	+ "guia.id_userModifica, "
							+ "ifnull(guia.fechaUserModifica,''), " 
							+ "guia.totalKg, " 
							+ "guia.totalM2 " +
							" from `"+db+"`.guia " +
							" order by guia.fecha desc, guia.id desc;" );
			ResultSet rs = smt.executeQuery();
			Map<Long,BodegaEmpresa> bodegas = BodegaEmpresa.mapAll(con, db);
			Map<Long,Ot> ots = Ot.mapAll(con, db);
			Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
			
			Map<Long,List<Long>> mapIdvsNumCotiSucur = Cotizacion.mapIdvsNumCotiSucur(con, db);
			Map<Long,List<String>> mapIdvsFechActEnvio = Ot.mapIdvsFechActEnvio(con, db);
			
			while (rs.next()) {

				BodegaEmpresa bodegaOrigen = bodegas.get(rs.getLong(10));
				
				String nameSucursalOrigen = "Sin Sucursal";
				Long id_sucursalOrigen = (long)0;
				String nombreBodegaOrigen = "";
				Long esInternaOrigen = (long)0;
				Long id_comercialOrigen = (long)0;
				
				if(bodegaOrigen!=null) { 
					nombreBodegaOrigen = bodegaOrigen.getNombre();
					nameSucursalOrigen = bodegaOrigen.getNameSucursal(); 
					id_sucursalOrigen = bodegaOrigen.getId_sucursal();
					esInternaOrigen = bodegaOrigen.getEsInterna();
					id_comercialOrigen = bodegaOrigen.getId_comercial();
				}
				
				BodegaEmpresa bodegaDestino = bodegas.get(rs.getLong(11));
				
				String nameSucursalDestino = "Sin Sucursal";
				Long id_sucursalDestino = (long)0;
				String nombreBodegaDestino = ""; 
				Long esInternaDestino = (long)0;
				Long id_comercialDestino = (long)0;
				if(bodegaDestino!=null) { 
					nombreBodegaDestino = bodegaDestino.getNombre();
					nameSucursalDestino = bodegaDestino.getNameSucursal();
					id_sucursalDestino = bodegaDestino.getId_sucursal();
					esInternaDestino = bodegaDestino.getEsInterna();
					id_comercialDestino = bodegaDestino.getId_comercial();
				}
				
				Ot ot = ots.get(rs.getLong(13));
				Long numeroOt = (long)0; 
				if(ot!=null) { 
					numeroOt = ot.numero; 
				}
				String tipoGuia = Guia.tipoGuia(db, bodegaOrigen, bodegaDestino);
				
				String nameComercial = "";
    			if((long)esInternaOrigen == (long)2) {
    				if(id_comercialOrigen.toString().equals("0")) {
    					nameComercial = "Sin Comercial";
    				}else {
    					Comercial comercial = mapComercial.get(id_comercialOrigen);
    					if(comercial!=null) {
    						nameComercial = comercial.nameUsuario;
	    				}
    				}
    			}else if((long)esInternaDestino == (long)2){
    				if(id_comercialDestino.toString().equals("0")) {
    					nameComercial = "Sin Comercial";
    				}else {
    					Comercial comercial = mapComercial.get(id_comercialDestino);
	    				if(comercial!=null) {
	    					nameComercial = comercial.nameUsuario;
	    				}
    				}
    			}else {
    				nameComercial = "";
    			}
    			
    			String fechaActualizacion = "";
    			String fechaEnvio = "";
    			List<String> auxFecha = mapIdvsFechActEnvio.get(rs.getLong(13));
    			if(auxFecha!=null) {
    				fechaActualizacion = auxFecha.get(0);
    				fechaEnvio = auxFecha.get(1);
    			}
    			
    			Long numCoti = (long)0;
    			List<Long> auxNumCotiSucur = mapIdvsNumCotiSucur.get(rs.getLong(12));
    			if(auxNumCotiSucur!=null) {
    				numCoti = auxNumCotiSucur.get(0);
    			}
    			
    			String totalKg = myformatdouble2.format(rs.getDouble(22));
    			String totalM2 = myformatdouble2.format(rs.getDouble(23));
				
				aux.add(new Guia(rs.getLong(1),rs.getLong(12),rs.getLong(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),
						rs.getString(7),rs.getString(8),rs.getString(9),rs.getLong(10),rs.getLong(11),nombreBodegaOrigen,nombreBodegaDestino,numCoti,tipoGuia,
						numeroOt, rs.getLong(13), rs.getString(14), rs.getString(15), rs.getLong(16), rs.getString(17),rs.getLong(19),rs.getLong(20),rs.getString(21),
						id_sucursalOrigen,id_sucursalDestino,nameSucursalOrigen,nameSucursalDestino,nameComercial, fechaActualizacion, fechaEnvio, totalKg, totalM2));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (aux);
	}
	
	public static List<Guia> allDespachosConOtDesdeHasta(Connection con, String db, String desde, String hasta, String permisoPorBodega, String esPorSucursal, String id_sucursal) {
		List<Guia> aux = new ArrayList<Guia>();
		
		try {
			PreparedStatement smt = con
					.prepareStatement(" select  "
							+ "guia.id, "
							+ "guia.numero, "
							+ "guia.fecha, "
							+ "guia.docAnexo, "
					/*5*/	+ "guia.guiaPDF, "
							+ "guia.guiaXml, "
							+ "guia.observaciones, "
							+ "guia.numGuiaCliente, "
							+ "guia.jsonGenerado, "
					/*10*/	+ "guia.id_bodegaOrigen, "
							+ "guia.id_bodegaDestino, " 
							+ "guia.id_cotizacion, "
							+ "guia.id_ot, "
							+ "guia.linkFolio, "
					/*15*/	+ "ifnull(guia.response,0), "
							+ "guia.id_transportista, "
							+ "guia.fotos, "
							+ "'', " 
							+ "guia.id_userCrea, "
					/*20*/	+ "guia.id_userModifica, "
							+ "ifnull(guia.fechaUserModifica,''), " 
							+ "guia.totalKg, " 
							+ "guia.totalM2 " +
							" from `"+db+"`.guia " +
							" where guia.id_cotizacion > 0 and (guia.fecha between ? and ?) "+ permisoPorBodega  +
							" order by guia.fecha desc, guia.id desc;" );
			smt.setString(1, desde);
			smt.setString(2, hasta);
			ResultSet rs = smt.executeQuery();
			Map<Long,BodegaEmpresa> bodegas = BodegaEmpresa.mapAll(con, db);
			Map<Long,Ot> ots = Ot.mapAll(con, db);
			Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
			
			Map<Long,List<Long>> mapIdvsNumCotiSucur = Cotizacion.mapIdvsNumCotiSucur(con, db);
			Map<Long,List<String>> mapIdvsFechActEnvio = Ot.mapIdvsFechActEnvio(con, db);
			
			
			boolean condicionadoPorSucursal = false;
			if(permisoPorBodega.length()<5) {
				if(esPorSucursal.equals("1")) {
					condicionadoPorSucursal = true;
				}
			}
			
			while (rs.next()) {
				
					BodegaEmpresa bodegaOrigen = bodegas.get(rs.getLong(10));
					BodegaEmpresa bodegaDestino = bodegas.get(rs.getLong(11));
					
					if(bodegaOrigen!=null && bodegaDestino!=null) {
						String nameSucursalOrigen = "Sin Sucursal";
						Long id_sucursalOrigen = (long)0;
						String nombreBodegaOrigen = ""; if(bodegaOrigen!=null) { 
							nombreBodegaOrigen = bodegaOrigen.getNombre();
							nameSucursalOrigen = bodegaOrigen.getNameSucursal(); 
							id_sucursalOrigen = bodegaOrigen.getId_sucursal();
						}
						
						String nameSucursalDestino = "Sin Sucursal";
						Long id_sucursalDestino = (long)0;
						String nombreBodegaDestino = ""; if(bodegaDestino!=null) { 
							nombreBodegaDestino = bodegaDestino.getNombre();
							nameSucursalDestino = bodegaDestino.getNameSucursal();
							id_sucursalDestino = bodegaDestino.getId_sucursal();
						}
						
						Ot ot = ots.get(rs.getLong(13));
						Long numeroOt = (long)0; 
						if(ot!=null) { 
							numeroOt = ot.numero; 
						}
						String tipoGuia = Guia.tipoGuia(db, bodegaOrigen, bodegaDestino);
						
						
					if(tipoGuia.equals("Despacho")) {
							
						String nameComercial = "";
		    			if((long)bodegaOrigen.getEsInterna() == (long)2) {
		    				if(bodegaOrigen.getId_comercial().toString().equals("0")) {
		    					nameComercial = "Sin Comercial";
		    				}else {
		    					Comercial comercial = mapComercial.get(bodegaOrigen.getId_comercial());
		    					if(comercial!=null) {
		    						nameComercial = comercial.nameUsuario;
			    				}
		    				}
		    			}else if((long)bodegaDestino.getEsInterna() == (long)2){
		    				if(bodegaDestino.getId_comercial().toString().equals("0")) {
		    					nameComercial = "Sin Comercial";
		    				}else {
		    					Comercial comercial = mapComercial.get(bodegaDestino.getId_comercial());
			    				if(comercial!=null) {
			    					nameComercial = comercial.nameUsuario;
			    				}
		    				}
		    			}else {
		    				nameComercial = "";
		    			}
		    			
		    			String fechaActualizacion = "";
		    			String fechaEnvio = "";
		    			List<String> auxFecha = mapIdvsFechActEnvio.get(rs.getLong(13));
		    			if(auxFecha!=null) {
		    				fechaActualizacion = auxFecha.get(0);
		    				fechaEnvio = auxFecha.get(1);
		    			}
		    			
		    			Long numCoti = (long)0;
		    			Long idSucursal = (long)0;
		    			List<Long> auxNumCotiSucur = mapIdvsNumCotiSucur.get(rs.getLong(12));
		    			if(auxNumCotiSucur!=null) {
		    				numCoti = auxNumCotiSucur.get(0);
		    				idSucursal = auxNumCotiSucur.get(1);
		    			}
		    			
		    			String totalKg = myformatdouble2.format(rs.getDouble(22));
		    			String totalM2 = myformatdouble2.format(rs.getDouble(23));
					
						if(condicionadoPorSucursal) {
							if(idSucursal.toString().equals(id_sucursal)) {
								aux.add(new Guia(rs.getLong(1),rs.getLong(12),rs.getLong(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),
										rs.getString(7),rs.getString(8),rs.getString(9),rs.getLong(10),rs.getLong(11),nombreBodegaOrigen,nombreBodegaDestino,numCoti,tipoGuia,
										numeroOt, rs.getLong(13), rs.getString(14), rs.getString(15), rs.getLong(16), rs.getString(17),rs.getLong(19),rs.getLong(20),rs.getString(21),
										id_sucursalOrigen,id_sucursalDestino,nameSucursalOrigen,nameSucursalDestino,nameComercial, fechaActualizacion, fechaEnvio, totalKg, totalM2));
							}
						}else {
							aux.add(new Guia(rs.getLong(1),rs.getLong(12),rs.getLong(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),
									rs.getString(7),rs.getString(8),rs.getString(9),rs.getLong(10),rs.getLong(11),nombreBodegaOrigen,nombreBodegaDestino,numCoti,tipoGuia,
									numeroOt, rs.getLong(13), rs.getString(14), rs.getString(15), rs.getLong(16), rs.getString(17),rs.getLong(19),rs.getLong(20),rs.getString(21),
									id_sucursalOrigen,id_sucursalDestino,nameSucursalOrigen,nameSucursalDestino,nameComercial, fechaActualizacion, fechaEnvio, totalKg, totalM2));
						}
					}
				}
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (aux);
	}
	
	public static List<Guia> allDespConOtDeHastaClientesVig(Connection con, String db, String desde, String hasta, String permisoPorBodega, String esPorSucursal, String id_sucursal) {
		List<Guia> aux = new ArrayList<Guia>();
		
		try {
			PreparedStatement smt = con
					.prepareStatement(" select  "
							+ "guia.id, "
							+ "guia.numero, "
							+ "guia.fecha, "
							+ "guia.docAnexo, "
					/*5*/	+ "guia.guiaPDF, "
							+ "guia.guiaXml, "
							+ "guia.observaciones, "
							+ "guia.numGuiaCliente, "
							+ "guia.jsonGenerado, "
					/*10*/	+ "guia.id_bodegaOrigen, "
							+ "guia.id_bodegaDestino, " 
							+ "guia.id_cotizacion, "
							+ "guia.id_ot, "
							+ "guia.linkFolio, "
					/*15*/	+ "ifnull(guia.response,0), "
							+ "guia.id_transportista, "
							+ "guia.fotos, "
							+ "'', " 
							+ "guia.id_userCrea, "
					/*20*/	+ "guia.id_userModifica, "
							+ "ifnull(guia.fechaUserModifica,''), " 
							+ "guia.totalKg, " 
							+ "guia.totalM2 " +
							" from `"+db+"`.guia " +
							" where guia.id_cotizacion > 0 and (guia.fecha between ? and ?) "+ permisoPorBodega  +
							" order by guia.fecha desc, guia.id desc;" );
			smt.setString(1, desde);
			smt.setString(2, hasta);
			ResultSet rs = smt.executeQuery();
			Map<Long,BodegaEmpresa> bodegas = BodegaEmpresa.mapAll(con, db);
			Map<Long,Ot> ots = Ot.mapAll(con, db);
			Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
			
			Map<Long,List<Long>> mapIdvsNumCotiSucur = Cotizacion.mapIdvsNumCotiSucur(con, db);
			Map<Long,List<String>> mapIdvsFechActEnvio = Ot.mapIdvsFechActEnvio(con, db);
			
			
			boolean condicionadoPorSucursal = false;
			if(permisoPorBodega.length()<5) {
				if(esPorSucursal.equals("1")) {
					condicionadoPorSucursal = true;
				}
			}
			
			while (rs.next()) {
				
					BodegaEmpresa bodegaOrigen = bodegas.get(rs.getLong(10));
					BodegaEmpresa bodegaDestino = bodegas.get(rs.getLong(11));
					
					if(bodegaOrigen!=null && bodegaDestino!=null && bodegaOrigen.getClienteVigente()==(long)1 && bodegaDestino.getClienteVigente()==(long)1) {
						String nameSucursalOrigen = "Sin Sucursal";
						Long id_sucursalOrigen = (long)0;
						String nombreBodegaOrigen = ""; if(bodegaOrigen!=null) { 
							nombreBodegaOrigen = bodegaOrigen.getNombre();
							nameSucursalOrigen = bodegaOrigen.getNameSucursal(); 
							id_sucursalOrigen = bodegaOrigen.getId_sucursal();
						}
						
						String nameSucursalDestino = "Sin Sucursal";
						Long id_sucursalDestino = (long)0;
						String nombreBodegaDestino = ""; if(bodegaDestino!=null) { 
							nombreBodegaDestino = bodegaDestino.getNombre();
							nameSucursalDestino = bodegaDestino.getNameSucursal();
							id_sucursalDestino = bodegaDestino.getId_sucursal();
						}
						
						Ot ot = ots.get(rs.getLong(13));
						Long numeroOt = (long)0; 
						if(ot!=null) { 
							numeroOt = ot.numero; 
						}
						String tipoGuia = Guia.tipoGuia(db, bodegaOrigen, bodegaDestino);
						
						
						if(tipoGuia.equals("Despacho")) {
								
							String nameComercial = "";
			    			if((long)bodegaOrigen.getEsInterna() == (long)2) {
			    				if(bodegaOrigen.getId_comercial().toString().equals("0")) {
			    					nameComercial = "Sin Comercial";
			    				}else {
			    					Comercial comercial = mapComercial.get(bodegaOrigen.getId_comercial());
			    					if(comercial!=null) {
			    						nameComercial = comercial.nameUsuario;
				    				}
			    				}
			    			}else if((long)bodegaDestino.getEsInterna() == (long)2){
			    				if(bodegaDestino.getId_comercial().toString().equals("0")) {
			    					nameComercial = "Sin Comercial";
			    				}else {
			    					Comercial comercial = mapComercial.get(bodegaDestino.getId_comercial());
				    				if(comercial!=null) {
				    					nameComercial = comercial.nameUsuario;
				    				}
			    				}
			    			}else {
			    				nameComercial = "";
			    			}
			    			
			    			String fechaActualizacion = "";
			    			String fechaEnvio = "";
			    			List<String> auxFecha = mapIdvsFechActEnvio.get(rs.getLong(13));
			    			if(auxFecha!=null) {
			    				fechaActualizacion = auxFecha.get(0);
			    				fechaEnvio = auxFecha.get(1);
			    			}
			    			
			    			Long numCoti = (long)0;
			    			Long idSucursal = (long)0;
			    			List<Long> auxNumCotiSucur = mapIdvsNumCotiSucur.get(rs.getLong(12));
			    			if(auxNumCotiSucur!=null) {
			    				numCoti = auxNumCotiSucur.get(0);
			    				idSucursal = auxNumCotiSucur.get(1);
			    			}
			    			
			    			String totalKg = myformatdouble2.format(rs.getDouble(22));
			    			String totalM2 = myformatdouble2.format(rs.getDouble(23));
						
							if(condicionadoPorSucursal) {
								if(idSucursal.toString().equals(id_sucursal)) {
									aux.add(new Guia(rs.getLong(1),rs.getLong(12),rs.getLong(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),
											rs.getString(7),rs.getString(8),rs.getString(9),rs.getLong(10),rs.getLong(11),nombreBodegaOrigen,nombreBodegaDestino,numCoti,tipoGuia,
											numeroOt, rs.getLong(13), rs.getString(14), rs.getString(15), rs.getLong(16), rs.getString(17),rs.getLong(19),rs.getLong(20),rs.getString(21),
											id_sucursalOrigen,id_sucursalDestino,nameSucursalOrigen,nameSucursalDestino,nameComercial, fechaActualizacion, fechaEnvio, totalKg, totalM2));
								}
							}else {
								aux.add(new Guia(rs.getLong(1),rs.getLong(12),rs.getLong(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),
										rs.getString(7),rs.getString(8),rs.getString(9),rs.getLong(10),rs.getLong(11),nombreBodegaOrigen,nombreBodegaDestino,numCoti,tipoGuia,
										numeroOt, rs.getLong(13), rs.getString(14), rs.getString(15), rs.getLong(16), rs.getString(17),rs.getLong(19),rs.getLong(20),rs.getString(21),
										id_sucursalOrigen,id_sucursalDestino,nameSucursalOrigen,nameSucursalDestino,nameComercial, fechaActualizacion, fechaEnvio, totalKg, totalM2));
							}
							
						}
					}
					
					
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (aux);
	}

	public static boolean modificaPorCampo(Connection con, String db, String campo, Long id_guia, String valor) {
		boolean flag = false;
		if(campo.equals("rut")) {
			campo = campo.replaceAll("[,\\.\\s]", "").toUpperCase();
       		if (campo.length() > 1) {
       			campo = campo.substring(0, campo.length() - 1) + "-" + campo.charAt(campo.length() - 1);
            }campo = campo.replaceAll("[\\,\\.]","").trim();
		}
		try {
			PreparedStatement smt = con.prepareStatement("update `"+db+"`.guia set `"+campo+"` = ? WHERE id = ?;");	
			smt.setString(1, valor.trim());
			smt.setLong(2, id_guia);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static List<List<String>> findDetalleGuiaOrigenDestinoYPrecios (Connection con, String db, Long id_guia, Long id_bodegaEmpresa, String pais, Long id_bodegaOrigen) {
		List<List<String>> listaDetalle = new ArrayList<List<String>>();
		try {
			
			Map<String,ListaPrecio> mapListaPrecio = ListaPrecio.mapListaPrecio(con, db, id_bodegaEmpresa, pais);
			Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
			Map<Long,String> strEstados = EstadoEquipo.mapStrEstadoEquipo(con, db, id_guia);
			Map<Long,String> strReparaciones = ReparacionEquipo.mapStrReparacionEquipo(con, db, id_guia);
			
			Map<String, Double> mapExcesos = new HashMap<String,Double>();
			Map<Long, Long> mapIdCoti = new HashMap<Long,Long>();
			PreparedStatement smt2 = con
					.prepareStatement("select id_equipo, id_cotizacion, exceso, id, id_cotizacion from `"+db+"`.movimiento where id_guia = ? and id_bodegaEmpresa = ?;");
			smt2.setLong(1, id_guia);
			smt2.setLong(2, id_bodegaOrigen);
			ResultSet rs2 = smt2.executeQuery();
			while (rs2.next()) {
				mapExcesos.put(rs2.getString(1)+"_"+rs2.getString(2), rs2.getDouble(3));
				mapIdCoti.put(rs2.getLong(4), rs2.getLong(5));
			}
			rs2.close();
			smt2.close();
			
			
			PreparedStatement smt = con
					.prepareStatement(" select distinct " +
							/*1*/" max(movimiento.id) as idMovimiento, " +
							/*2*/" guia.id as idGuia,  " +
							/*3*/" movimiento.id_equipo as idEquipo, " +
							/*4*/" movimiento.id_bodegaEmpresa as idBodDest, " +
							/*5*/" sum(movimiento.cantidad)," +
							/*6*/" sum(movimiento.exceso),  " +
							/*7*/" movimiento.esVenta,  " +
							/*8*/" movimiento.esNuevo,  " +
							/*9*/" movimiento.id_cotizacion, " +
							/*10*/" guia.observaciones, " +
							/*11*/" sum(movimiento.cantCliente)" +
							" from `"+db+"`.guia   " +
							" left join `"+db+"`.movimiento on movimiento.id_guia = guia.id " +
							" where guia.id = ? and movimiento.id_bodegaEmpresa=? " +
							" group by movimiento.id_equipo, movimiento.id_cotizacion;");
			smt.setLong(1, id_guia);
			smt.setLong(2, id_bodegaEmpresa);
			
			ResultSet rs = smt.executeQuery();
			
			Map<Long,Equipo> mapEquipo = Equipo.mapAllAll(con, db);
			Map<Long,Cotizacion> mapCoti = Cotizacion.mapAll(con, db);
			
			
			while (rs.next()) {
				
				String pventa = "0", pventaTotal = "0", parriendo = "0", nmoneda = "", utarriendo = "", dglobal = "0", dgrupo = "0", dequipo = "0", varriendo= "0", dcomp = "0", idMoneda = "1";
				
				ListaPrecio listaPrecio = mapListaPrecio.get(rs.getString(3)+"_"+rs.getString(9));
				
				Equipo equipo = mapEquipo.get(rs.getLong(3));
				Long id_grupo = (long)0;
				String codEquipo = "";
				String nomEquipo = "";
				String unEquipo = "";
				String grupo = "";
				String imgEquipo = "";
				if(equipo != null) {
					id_grupo = equipo.getId_grupo();
					codEquipo = equipo.getCodigo();
					nomEquipo = equipo.getNombre();
					unEquipo = equipo.getUnidad();
					grupo = equipo.getGrupo();
					imgEquipo = equipo.getImg();
				}
				
				Cotizacion coti = mapCoti.get(rs.getLong(9));
				Long numCoti = (long)0;
				if(coti!=null) {
					numCoti = coti.getNumero();
				}
				
				if(listaPrecio!=null && equipo!=null) {
					
					Long id_moneda = listaPrecio.getId_moneda();
					if(id_moneda == null) {id_moneda=(long)1;}
					Long decimal = dec.get(id_moneda);
					if(decimal == null) {decimal=(long)0;}
					switch(decimal.toString()) {
					 case "0": myformatdouble = new DecimalFormat("#,##0"); break;
					 case "2": myformatdouble = new DecimalFormat("#,##0.00"); break;
					 case "4": myformatdouble = new DecimalFormat("#,##0.0000"); break;
					 case "6": myformatdouble = new DecimalFormat("#,##0.000000"); break;
					 default:  break;
					}
					
					nmoneda = listaPrecio.getNickNameMoneda();
					utarriendo = listaPrecio.getNombreUnidadTiempo();
					dglobal = listaPrecio.gettasaDesctoGlobal();
					dgrupo = listaPrecio.getTasaDesctoGrupo();
					dequipo = listaPrecio.getTasaDesctoEquipo();
					
					Double auxDglobal = Double.parseDouble(dglobal.replaceAll(",", "").replace("%", "").trim())/100;
					Double auxDgrupo = Double.parseDouble(dgrupo.replaceAll(",", "").replace("%", "").trim())/100;
					Double auxDequipo = Double.parseDouble(dequipo.replaceAll(",", "").replace("%", "").trim())/100;
					Double auxDcomp = (1-auxDglobal) * (1-auxDgrupo) * (1-auxDequipo);
					
					
					parriendo = listaPrecio.getPrecioArriendo();  // 10 precio de arriendo en moneda de origen sin descuentos globales descontinuados
					Double auxDparriendo = Double.parseDouble(parriendo.replaceAll(",", "").trim());
					Double auxVarriendo = auxDparriendo * (auxDcomp);
					pventa = listaPrecio.getPrecioReposicion();
					varriendo = myformatdouble.format(auxVarriendo);
					dcomp = myformatdouble2.format(auxDcomp*100)+" %";
					idMoneda = listaPrecio.getId_moneda().toString();
					Double auxpventa = Double.parseDouble(pventa.replaceAll(",", "").trim());
					Double auxpventaTotal = auxpventa * rs.getDouble(5);
					pventaTotal = myformatdouble.format(auxpventaTotal);
				}
				
				Double kg = equipo.getKg();
				Double m2 = equipo.getM2();
				
				String cantidad = myformatdouble2.format(rs.getDouble(5));
				String cantCliente = myformatdouble2.format(rs.getDouble(11));
				String difCantCliente = myformatdouble2.format(rs.getDouble(5) - rs.getDouble(11));
				
				
				String exceso = myformatdouble2.format((double)0);
				
				Double auxExceso = mapExcesos.get(rs.getString(3)+"_"+rs.getString(9));
				if(auxExceso!=null) {
					exceso = myformatdouble2.format(auxExceso);
				}
				
				Long idMovActual = rs.getLong(1);	// BODEGA DE ORIGEN
				
				
				
				Long id_coti = mapIdCoti.get(idMovActual);
				if(id_coti==null) {
					id_coti = (long)0;
				}
				
				
				
				String estadosDeEquipos = strEstados.get(idMovActual);
				if(estadosDeEquipos==null) { estadosDeEquipos = ""; }
				String reparDeEquipos = strReparaciones.get(idMovActual);
				if(reparDeEquipos==null) { reparDeEquipos = ""; }
				
				
				List<String> aux = new ArrayList<String>();
				aux.add(idMovActual.toString());		//  0 id idMovimiento linea de mov actual BODEGA DE ORIGEN
				aux.add(rs.getString(2)); 				//  1 id idGuia
				aux.add(rs.getString(3)); 				//  2 id idEquipo
				aux.add(id_grupo.toString()); 			//  3 id idGrupo
				aux.add(grupo);			 				//  4 nombre grupo
				aux.add(codEquipo); 					//  5 codigo equipo
				aux.add(nomEquipo); 					//  6 nombre equipo
				aux.add(unEquipo); 						//  7 nombre unidad
				aux.add(cantidad); 						//  8 cantidad
				
				aux.add(pventa);  						//  9 precio de venta en moneda de origen
				aux.add(parriendo);  					// 10 precio de arriendo en moneda de origen sin descuentos globales descontinuado
				aux.add(nmoneda);  						// 11 nombre moneda
				aux.add(utarriendo);  					// 12 unidad tiempo arriendo
				aux.add(dglobal);  						// 13 descuento global de equipo.dctoGlobal
				aux.add(dgrupo);  						// 14 descuento grupo de tabla dctoGrupo
				aux.add(dequipo); 						// 15 descuento equipo de tabla dctoEquipo
				aux.add(varriendo); 					// 16 valorArriendo aplicando todos los descuentos globales descontinuado
				aux.add(dcomp); 						// 17 descuento total compuesto
				aux.add(exceso); 						// 18 exceso
				aux.add(idMovActual.toString());		// 19 id idMovimiento linea de mov anterior a la actual
				aux.add(rs.getString(7));				// 20 esVenta
				aux.add(rs.getString(8));				// 21 esNuevo
				aux.add(imgEquipo);						// 22 img
				aux.add(id_coti.toString());							// 23 idCotizacion
				aux.add(numCoti.toString());							// 24 numero de Cotizacion
				aux.add(myformatdouble2.format(kg));					//25 peso unitario en kg
				aux.add(myformatdouble2.format(m2));					//26 superficie unitario en m2
				aux.add(myformatdouble2.format(kg*rs.getDouble(5)));	//27 total por cant por peso en kg
				aux.add(myformatdouble2.format(m2*rs.getDouble(5)));	//28 total por cant por superficie en m2
				aux.add(rs.getString(10));								//29 observaciones de guia
				aux.add(idMoneda);										//30 id moneda
				aux.add(estadosDeEquipos);								//31 string estados
				aux.add(reparDeEquipos);								//32 string reparaciones
				aux.add(pventaTotal);									//33 total pventa unitario por cant 
				
				aux.add(cantCliente);									//34 cantidad que dice el cliente 
				aux.add(difCantCliente);								//35 diferencia con cliente 
				
				aux.add(equipo.getKg().toString());						//36 kg unitario 
				aux.add(equipo.getM2().toString());						//37 m2 unitario 
				
				listaDetalle.add(aux);
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		
		return (listaDetalle);
	}
	
	public static File movimientoListarExcel(Map<String,String> mapPermiso, String db, List<Guia> listaGuias, Map<String,String> mapDiccionario) {
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
            
            //titulos del archivo
            
            libro.setSheetName(0, "LISTADO");
            Sheet hoja1 = libro.getSheetAt(0);
            
            Row row = null;
            Cell cell = null;
            
            row = hoja1.createRow(1);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("LISTADO DE MOVIMIENTOS");
			
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
			
			//anchos de columnas
			for(int i=1; i<13; i++) {
				hoja1.setColumnWidth(i, 5*1000);
			}
			hoja1.setColumnWidth(9, 10*1000);
			hoja1.setColumnWidth(11, 10*1000);
            
			//INSERTA LOGO DESPUES DE ANCHOS DE COLUMNAS
			InputStream x = Archivos.leerArchivo(db+"/"+mapDiccionario.get("logoEmpresa"));
            byte[] bytes = IOUtils.toByteArray(x);
            x.close();
            int pngIndex = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			Drawing draw = hoja1.createDrawingPatriarch();
			CreationHelper helper = libro.getCreationHelper();
			ClientAnchor anchor = helper.createClientAnchor();
	        //set top-left corner for the image
	        anchor.setCol1(7);
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
			cell.setCellValue("TIPO");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("COMERCIAL");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Nro "+mapDiccionario.get("OT"));
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Nro COTI");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Nro MOV");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Nro REF");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("FECHA");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("SUCURSAL DESDE");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("DESDE "+mapDiccionario.get("BODEGA")+"/PROYECTO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("SUCURSAL HASTA");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("HASTA "+mapDiccionario.get("BODEGA")+"/PROYECTO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("TOTAL KG");
			
			if( ! (mapPermiso.get("parametro.escondeLosM2")!=null && mapPermiso.get("parametro.escondeLosM2").equals("1")) ) {
				posCell++;
				cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue("TOTAL M2");
			}
			
			for(int i=0;i<listaGuias.size();i++){
				if(listaGuias.get(i).getNumero()>0) {
					posRow++;
					posCell = 0;
			        row = hoja1.createRow(posRow);
					
			        posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(listaGuias.get(i).getTipoGuia());

					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(listaGuias.get(i).getNameComercial());
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					if(listaGuias.get(i).getNumeroCotizacion() > 0){
						cell.setCellValue(listaGuias.get(i).getNumeroOt());
					}
					
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					if(listaGuias.get(i).getNumeroCotizacion() > 0){
						cell.setCellValue(listaGuias.get(i).getNumeroCotizacion());
					}
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(listaGuias.get(i).getNumero());
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(listaGuias.get(i).getNumGuiaCliente());
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(listaGuias.get(i).getFecha());
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(listaGuias.get(i).getNameSucursalOrigen());
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(listaGuias.get(i).getBodegaOrigen());
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(listaGuias.get(i).getNameSucursalDestino());
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(listaGuias.get(i).getBodegaDestino());
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					Double totalKg = (double)0;
					try {
						totalKg = Double.parseDouble(listaGuias.get(i).getTotalKg().replaceAll(",", ""));
					}catch(Exception e) {}
					cell.setCellValue(totalKg);
					
					if( ! (mapPermiso.get("parametro.escondeLosM2")!=null && mapPermiso.get("parametro.escondeLosM2").equals("1")) ) {
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						Double totalM2 = (double)0;
						try {
							totalM2 = Double.parseDouble(listaGuias.get(i).getTotalM2().replaceAll(",", ""));
						}catch(Exception e) {}
						cell.setCellValue(totalM2);
					}
				}
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
		return tmp;
	}
	
	public static File detalleGuiaExcel(String db, Map<String,String> mapDiccionario, Map<String,String> mapeoPermiso, Guia guia, List<List<String>> detalleGuia, List<TipoEstado> listTipoEstado, BodegaEmpresa bodegaOrigen) {
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
            
            //titulos del archivo
            
            libro.setSheetName(0, "LISTADO");
            Sheet hoja1 = libro.getSheetAt(0);
            
            Row row = null;
            Cell cell = null;
            
            row = hoja1.createRow(1);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("DETALLE DEL MOVIMIENTO");
			
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
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("ORIGEN: "+guia.getBodegaOrigen().toUpperCase());
			
			row = hoja1.createRow(6);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("DESTINO: "+guia.getBodegaDestino().toUpperCase());
			
			row = hoja1.createRow(7);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Nro MOV: "+guia.getNumero());
			
			row = hoja1.createRow(8);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Nro REF: "+guia.getNumGuiaCliente());
			
			row = hoja1.createRow(9);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("FECHA: "+Fechas.AAMMDD(guia.getFecha()));
			
			row = hoja1.createRow(10);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("OBSERVACIONES: "+guia.getObservaciones());
			
			
			
			
			
			
			//anchos de columnas
			for(int i=1; i<25; i++) {
				hoja1.setColumnWidth(i, 5*1000);
			}
            
			//INSERTA LOGO DESPUES DE ANCHOS DE COLUMNAS
			InputStream x = Archivos.leerArchivo(db+"/"+mapDiccionario.get("logoEmpresa"));
            byte[] bytes = IOUtils.toByteArray(x);
            x.close();
            int pngIndex = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			Drawing draw = hoja1.createDrawingPatriarch();
			CreationHelper helper = libro.getCreationHelper();
			ClientAnchor anchor = helper.createClientAnchor();
	        //set top-left corner for the image
	        anchor.setCol1(14);
	        anchor.setRow1(1);
			Picture img = draw.createPicture(anchor, pngIndex);
			img.resize(0.4);
			hoja1.createFreezePane(0, 0, 0,0);
			
			
			// encabezado de la tabla
			
			int posRow = 13;
			
			row = hoja1.createRow(posRow);
			int posCell = 0;
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("GRUPO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Nro COTI");
			
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
			cell.setCellValue("KG");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("M2");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("UN");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("SALIDA");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("VTA");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("EXCESO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("MON");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("P.UNITARIO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("P.TOTAL");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("TOT.KG");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("TOT.M2");
			
			if((long)bodegaOrigen.esInterna != (long)1){
				for(TipoEstado tipo: listTipoEstado){
					posCell++;
					cell = row.createCell(posCell);
		            cell.setCellStyle(encabezado);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(tipo.getSigla());
				}
				
				if(mapeoPermiso.get("parametro.movimiento-devolucion-cantCliente")!=null && mapeoPermiso.get("parametro.movimiento-devolucion-cantCliente").equals("1")){
					posCell++;
					cell = row.createCell(posCell);
		            cell.setCellStyle(encabezado);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("cliente");
					
					posCell++;
					cell = row.createCell(posCell);
		            cell.setCellStyle(encabezado);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("diferen");
				}
			}
			
			
			
			for(int i=0;i<detalleGuia.size();i++){
				
				posRow++;
				posCell = 0;
		        row = hoja1.createRow(posRow);
				
		        posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(detalleGuia.get(i).get(4));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				Long auxLong = Long.parseLong(detalleGuia.get(i).get(24));
				if(auxLong != 0) {
					cell.setCellValue(auxLong);
				}
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(detalleGuia.get(i).get(5));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(detalleGuia.get(i).get(6));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				Double auxDbl = Double.parseDouble(detalleGuia.get(i).get(25).replaceAll(",", ""));
				cell.setCellValue(auxDbl);
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				auxDbl = Double.parseDouble(detalleGuia.get(i).get(26).replaceAll(",", ""));
				cell.setCellValue(auxDbl);
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(detalleGuia.get(i).get(7));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				auxDbl = Double.parseDouble(detalleGuia.get(i).get(8).replaceAll(",", ""));
				cell.setCellValue(auxDbl);
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				auxLong = Long.parseLong(detalleGuia.get(i).get(20));
				if(auxLong != 0) {
					cell.setCellValue("SI"); // CHECK DE VTA
				}
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				auxDbl = Double.parseDouble(detalleGuia.get(i).get(18).replaceAll(",", ""));
				cell.setCellValue(auxDbl);
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(detalleGuia.get(i).get(11));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				auxDbl = Double.parseDouble(detalleGuia.get(i).get(9).replaceAll(",", ""));
				cell.setCellValue(auxDbl);
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				auxDbl = Double.parseDouble(detalleGuia.get(i).get(33).replaceAll(",", ""));
				cell.setCellValue(auxDbl);
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				auxDbl = Double.parseDouble(detalleGuia.get(i).get(27).replaceAll(",", ""));
				cell.setCellValue(auxDbl);
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				auxDbl = Double.parseDouble(detalleGuia.get(i).get(28).replaceAll(",", ""));
				cell.setCellValue(auxDbl);
				
				if((long)bodegaOrigen.esInterna != (long)1){
					for(TipoEstado tipo: listTipoEstado){
						Double valor = (double)0;
						String niv = detalleGuia.get(i).get(31);
						String niv1[] = niv.split(";");
						for(int k=0; k<niv1.length && niv1.length>0; k++){
							String niv2[] = niv1[k].split(":");
							if(niv2[0].equals(tipo.getId().toString())){
								valor = Double.parseDouble(niv2[1].replaceAll(",", ""));
							}
						}
						posCell++;
						cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellValue(valor);
					}
					if(mapeoPermiso.get("parametro.movimiento-devolucion-cantCliente")!=null && mapeoPermiso.get("parametro.movimiento-devolucion-cantCliente").equals("1")){
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						auxDbl = Double.parseDouble(detalleGuia.get(i).get(34).replaceAll(",", ""));
						cell.setCellValue(auxDbl);
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						auxDbl = Double.parseDouble(detalleGuia.get(i).get(35).replaceAll(",", ""));
						cell.setCellValue(auxDbl);
					}
				}
				
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
		return tmp;
	}
	
	
	
}
