package models.forms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import models.tables.BodegaEmpresa;
import models.tables.CotiOdo;
import models.tables.OtOdo;
import models.utilities.Archivos;
import models.utilities.Fechas;
import play.libs.Files.TemporaryFile;
import play.mvc.Http;

public class FormOtOdo {
	public Long id_otOdo;
	public Long id_cotiOdo;
	public Long id_bodegaEmpresa;
	public Long numeroOt;
	public String fechaOt;
	public String observaciones;
	
	public String nombreBodega;
	public Long id_proyecto;
	public String nickProyecto;
	
	public Long id_operadorServicio;
	public String fechaEnvio;

	public FormOtOdo(Long id_otOdo, Long id_cotiOdo, Long id_bodegaEmpresa, Long numeroOt, String fechaOt,
			String observaciones, Long id_proyecto, String nickProyecto, Long id_operadorServicio, String fechaEnvio) {
		super();
		this.id_otOdo = id_otOdo;
		this.id_cotiOdo = id_cotiOdo;
		this.id_bodegaEmpresa = id_bodegaEmpresa;
		this.numeroOt = numeroOt;
		this.fechaOt = fechaOt;
		this.observaciones = observaciones;
		this.id_proyecto = id_proyecto;
		this.nickProyecto = nickProyecto;
		this.id_operadorServicio = id_operadorServicio;
		this.fechaEnvio = fechaEnvio;
	}

	public FormOtOdo(Long id_otOdo, Long id_cotiOdo, Long id_bodegaEmpresa, Long numeroOt, String fechaOt,
			String observaciones, String nombreBodega, Long id_proyecto, String nickProyecto) {
		super();
		this.id_otOdo = id_otOdo;
		this.id_cotiOdo = id_cotiOdo;
		this.id_bodegaEmpresa = id_bodegaEmpresa;
		this.numeroOt = numeroOt;
		this.fechaOt = fechaOt;
		this.observaciones = observaciones;
		this.nombreBodega = nombreBodega;
		this.id_proyecto = id_proyecto;
		this.nickProyecto = nickProyecto;
	}

	public FormOtOdo() {
		super();
	}
	
	
	public static boolean create(Connection con, String db, FormOtOdo form, Http.MultipartFormData.FilePart<TemporaryFile> docAdjunto) {
		boolean flag = false;
		try {
			
			String fechaActalizacion = null;
			String fechaEnvio = form.fechaEnvio;
			if(fechaEnvio != null && !fechaEnvio.equals("")) {
				Fechas hoy = Fechas.hoy();
				fechaActalizacion = hoy.fechaStrAAMMDD;
			}
			
			
			PreparedStatement smt = con
					.prepareStatement("insert into  `"+db+"`.otOdo (id_cotiOdo, numero, fecha, observaciones, id_operadorServicio, fechaActualizacion, fechaEnvio) " +
							" VALUES (?,?,?,?,?,?,?)");
			smt.setLong(1, form.id_cotiOdo);
			smt.setLong(2, form.numeroOt);
			smt.setString(3, form.fechaOt);
			smt.setString(4, form.observaciones.trim());
			smt.setLong(5, form.id_operadorServicio);
			
			if (fechaEnvio != null && !fechaEnvio.equals("")) {
				smt.setString(6, fechaActalizacion);
				smt.setString(7, form.fechaEnvio);
			} else {
				smt.setNull(6, Types.INTEGER);
				smt.setNull(7, Types.INTEGER);
			}

			smt.executeUpdate();
			smt.close();

			OtOdo otOdo = OtOdo.findPorNumero(con, db, form.numeroOt);
			if(otOdo != null) {
				CotiOdo.modifyXCampo(con, db, "id_otOdo", otOdo.getId().toString(), otOdo.getId_cotiOdo());
				CotiOdo.modifyXCampo(con, db, "id_bodegaEmpresa", form.id_bodegaEmpresa.toString(), otOdo.getId_cotiOdo());
				
				if((long) form.id_proyecto > (long) 0) {
					CotiOdo.modifyXCampo(con, db, "id_proyecto", form.id_proyecto.toString(), otOdo.getId_cotiOdo());
					BodegaEmpresa.modificarBodegaPorCampo(con, db, form.id_bodegaEmpresa, "id_proyecto", form.id_proyecto.toString());
				}
				
				String path = "0";
				if (docAdjunto != null) {
					String nombreArchivoSinExtencion = "Doc_OtOdo_" + otOdo.id;
					path = Archivos.grabarArchivos(docAdjunto, db, nombreArchivoSinExtencion);
					OtOdo.modifyXCampo(con, db, "otOdoPDF", path, otOdo.getId());
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














