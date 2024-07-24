package models.forms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import models.tables.BodegaEmpresa;
import models.tables.Cotizacion;
import models.tables.Ot;
import models.utilities.Archivos;
import models.utilities.Fechas;
import play.libs.Files.TemporaryFile;
import play.mvc.Http;

public class FormOt {
	public Long id_ot;
	public Long id_cotizacion;
	public Long id_bodegaEmpresa;
	public Long numeroOt;
	public String fechaOt;
	public String observaciones;
	
	public String nombreBodega;
	public Long id_proyecto;
	public String nickProyecto;
	
	public String fechaEnvio;
	
	public FormOt(Long id_ot, Long id_cotizacion, Long id_bodegaEmpresa, Long numeroOt, String fechaOt,
			String observaciones, Long id_proyecto, String nickProyecto) {
		super();
		this.id_ot = id_ot;
		this.id_cotizacion = id_cotizacion;
		this.id_bodegaEmpresa = id_bodegaEmpresa;
		this.numeroOt = numeroOt;
		this.fechaOt = fechaOt;
		this.observaciones = observaciones;
		this.id_proyecto = id_proyecto;
		this.nickProyecto = nickProyecto;
	}

	public FormOt(Long id_ot, Long id_cotizacion, Long id_bodegaEmpresa, Long numeroOt, String fechaOt,
			String observaciones, String nombreBodega, Long id_proyecto, String nickProyecto, String fechaEnvio) {
		super();
		this.id_ot = id_ot;
		this.id_cotizacion = id_cotizacion;
		this.id_bodegaEmpresa = id_bodegaEmpresa;
		this.numeroOt = numeroOt;
		this.fechaOt = fechaOt;
		this.observaciones = observaciones;
		this.nombreBodega = nombreBodega;
		this.id_proyecto = id_proyecto;
		this.nickProyecto = nickProyecto;
		this.fechaEnvio = fechaEnvio;
	}

	public FormOt() {
		super();
	}
	
	
	public static boolean create(Connection con, String db, FormOt form, Http.MultipartFormData.FilePart<TemporaryFile> docAdjunto, String id_userCrea) {
		boolean flag = false;
		try {
			String fechaActalizacion = null;
			String fechaEnvio = form.fechaEnvio;
			if(fechaEnvio != null && !fechaEnvio.equals("")) {
				Fechas hoy = Fechas.hoy();
				fechaActalizacion = hoy.fechaStrAAMMDD;
			}
			
			PreparedStatement smt = con
					.prepareStatement("insert into  `"+db+"`.ot (id_cotizacion, numero, fecha, observaciones, id_userCrea, fechaActualizacion, fechaEnvio) " +
							" VALUES (?,?,?,?,?,?,?)");
			smt.setLong(1, form.id_cotizacion);
			smt.setLong(2, form.numeroOt);
			smt.setString(3, form.fechaOt);
			smt.setString(4, form.observaciones.trim());
			smt.setString(5, id_userCrea);
			
			if (fechaEnvio != null && !fechaEnvio.equals("")) {
				smt.setString(6, fechaActalizacion);
				smt.setString(7, form.fechaEnvio);
			} else {
				smt.setNull(6, Types.INTEGER);
				smt.setNull(7, Types.INTEGER);
			}
			
			smt.executeUpdate();
			smt.close();
			Ot ot = Ot.findPorNumero(con, db, form.numeroOt);
			if(ot != null) {
				Cotizacion.modifyXCampo(con, db, "id_ot", ot.getId().toString(), ot.getId_cotizacion());
				Cotizacion.modifyXCampo(con, db, "id_bodegaEmpresa", form.id_bodegaEmpresa.toString(), ot.getId_cotizacion());
				Cotizacion.modifyXCampo(con, db, "id_cotizaEstado", "-2", ot.getId_cotizacion());
				
				if((long) form.id_proyecto > (long) 0) {
					Cotizacion.modifyXCampo(con, db, "id_proyecto", form.id_proyecto.toString(), ot.getId_cotizacion());
					BodegaEmpresa.modificarBodegaPorCampo(con, db, form.id_bodegaEmpresa, "id_proyecto", form.id_proyecto.toString());
				}
				
				String path = "0";
				if (docAdjunto != null) {
					String nombreArchivoSinExtencion = "Doc_Ot_" + ot.id;
					path = Archivos.grabarArchivos(docAdjunto, db, nombreArchivoSinExtencion);
					Ot.modifyXCampo(con, db, "otPDF", path, ot.getId());
				}
				flag = true;
			}
		} catch (SQLException e) {
    			e.printStackTrace();
			flag=false;
		}
		return (flag);
	}

	
	
	
}














