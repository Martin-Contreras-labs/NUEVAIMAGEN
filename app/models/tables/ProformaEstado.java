package models.tables;

import controllers.HomeController;
import models.utilities.Archivos;
import models.utilities.Fechas;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.TempFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class ProformaEstado {
	public Long id;
	public String fecha;
	public String desde;
	public String hasta;
	public Long id_cliente;
	public Long id_bodegaEmpresa;
	public Long id_proyecto;
	public String excel;
	public String pdf;
	public Double neto;
	public Double iva;
	public Double total;
	public String une_ajustesEP;

	public ProformaEstado(Long id, String fecha, String desde, String hasta, Long id_cliente, Long id_bodegaEmpresa, Long id_proyecto, String excel, String pdf,
						  Double neto, Double iva, Double total, String une_ajustesEP) {
		this.id = id;
		this.fecha = fecha;
		this.desde = desde;
		this.hasta = hasta;
		this.id_cliente = id_cliente;
		this.id_bodegaEmpresa = id_bodegaEmpresa;
		this.id_proyecto = id_proyecto;
		this.excel = excel;
		this.pdf = pdf;
		this.neto = neto;
		this.iva = iva;
		this.total = total;
		this.une_ajustesEP = une_ajustesEP;
	}

	public ProformaEstado() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getDesde() {
		return desde;
	}

	public void setDesde(String desde) {
		this.desde = desde;
	}

	public String getHasta() {
		return hasta;
	}

	public void setHasta(String hasta) {
		this.hasta = hasta;
	}

	public Long getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Long id_cliente) {
		this.id_cliente = id_cliente;
	}

	public Long getId_bodegaEmpresa() {
		return id_bodegaEmpresa;
	}

	public void setId_bodegaEmpresa(Long id_bodegaEmpresa) {
		this.id_bodegaEmpresa = id_bodegaEmpresa;
	}

	public Long getId_proyecto() {
		return id_proyecto;
	}

	public void setId_proyecto(Long id_proyecto) {
		this.id_proyecto = id_proyecto;
	}

	public String getExcel() {
		return excel;
	}

	public void setExcel(String excel) {
		this.excel = excel;
	}

	public String getPdf() {
		return pdf;
	}

	public void setPdf(String pdf) {
		this.pdf = pdf;
	}

	public Double getNeto() {
		return neto;
	}

	public void setNeto(Double neto) {
		this.neto = neto;
	}

	public Double getIva() {
		return iva;
	}

	public void setIva(Double iva) {
		this.iva = iva;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getUne_ajustesEP() {
		return une_ajustesEP;
	}

	public void setUne_ajustesEP(String une_ajustesEP) {
		this.une_ajustesEP = une_ajustesEP;
	}

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	public static ProformaEstado create(Connection con, String db, Fechas hoy, Fechas desde, Fechas hasta, BodegaEmpresa bodegaEmpresa) {
		String query = String.format("insert into  `%s`.proformaEstado (fecha,desde,hasta,id_cliente,id_bodegaEmpresa,id_proyecto,une_ajustesEP) values (?,?,?,?,?,?,?);",db);
		try (PreparedStatement smt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			smt.setString(1,hoy.getFechaStrAAMMDD());
			smt.setString(2,desde.getFechaStrAAMMDD());
			smt.setString(3,hasta.getFechaStrAAMMDD());
			smt.setLong(4, bodegaEmpresa.getId_cliente());
			smt.setLong(5, bodegaEmpresa.getId());
			smt.setLong(6, bodegaEmpresa.getId_proyecto());
			String une_ajustesEP = bodegaEmpresa.getId()+"::"+desde.getFechaStrAAMMDD()+"::"+hasta.getFechaStrAAMMDD();
			smt.setString(7,une_ajustesEP);
			smt.executeUpdate();
			try (ResultSet generatedKeys = smt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					Long id = generatedKeys.getLong(1);
					query = String.format("update  `%s`.proformaEstado set excel = ?, pdf=? where id = ?;",db);
					try (PreparedStatement smt2 = con.prepareStatement(query)) {
						smt2.setString(1, "profEstadoXLS_"+id+".xlsx");
						smt2.setString(2, "profEstadoPDF_"+id+".pdf");
						smt2.setLong(3, id);
						smt2.executeUpdate();
						return (new ProformaEstado(id, hoy.getFechaStrAAMMDD(), desde.getFechaStrAAMMDD(), hasta.getFechaStrAAMMDD(),
								bodegaEmpresa.getId_cliente(), bodegaEmpresa.getId(), bodegaEmpresa.getId_proyecto(),
								"profEstadoXLS_"+id+".xlsx", "profEstadoPDF_"+id+".pdf", 0.0, 0.0, 0.0, une_ajustesEP));
					}
				}
			}
		} catch (SQLException e) {
			String className = ActaBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (null);
	}

	public static boolean updateTotales(Connection con, String db, ProformaEstado proforma) {
		String query = String.format("update  `%s`.proformaEstado set neto = ?, iva=?, total=? where id = ?;",db);
		try (PreparedStatement smt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			smt.setDouble(1,proforma.getNeto());
			smt.setDouble(2,proforma.getIva());
			smt.setDouble(3,proforma.getTotal());
			smt.setLong(4, proforma.getId());
			smt.executeUpdate();
			return (true);
		} catch (SQLException e) {
			String className = ActaBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (false);
	}

	
	
}
