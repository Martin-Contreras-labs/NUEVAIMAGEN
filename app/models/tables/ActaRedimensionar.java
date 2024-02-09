package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import models.forms.FormRedimensionar;


public class ActaRedimensionar {
	public Long id;
	public Long numero;
	public String fecha;
	public String actaPDF;
	public String observaciones;
	public String fechaConfirma;
	
	public ActaRedimensionar(Long id, Long numero, String fecha, String actaPDF, String observaciones, String fechaConfirma) {
		super();
		this.id = id;
		this.numero = numero;
		this.fecha = fecha;
		this.actaPDF = actaPDF;
		this.observaciones = observaciones;
		this.fechaConfirma = fechaConfirma;
	}
	
	public ActaRedimensionar(FormRedimensionar form) {
		super();
		this.id = (long)0;
		this.numero = form.numero;
		this.fecha = form.fecha;
		this.actaPDF = "";
		this.observaciones = form.observaciones;
	}

	public ActaRedimensionar() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getActaPDF() {
		return actaPDF;
	}

	public void setActaPDF(String actaPDF) {
		this.actaPDF = actaPDF;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getFechaConfirma() {
		return fechaConfirma;
	}

	public void setFechaConfirma(String fechaConfirma) {
		this.fechaConfirma = fechaConfirma;
	}

	static SimpleDateFormat myformatfecha = new SimpleDateFormat("dd-MM-yyyy");
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	
	public static Long create(Connection con, String db, ActaRedimensionar actaRedireccionar) {
		Long id_acta = (long)0;
		try {
			PreparedStatement smt = con
					.prepareStatement("INSERT INTO `"+db+"`.actaRedimensionar (numero,fecha,observaciones) " +
								" VALUES (?,?,?);",Statement.RETURN_GENERATED_KEYS);
			smt.setLong(1, actaRedireccionar.getNumero());
			smt.setString(2, actaRedireccionar.getFecha());
			smt.setString(3, actaRedireccionar.getObservaciones());
			smt.executeUpdate();
			
			ResultSet rs = smt.getGeneratedKeys();
            if (rs.next()) {
            	id_acta = rs.getLong(1);
            }
            smt.close();
            rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (id_acta);
	}
	
	public static boolean delete(Connection con, String db, Long id_actaRedimensionar) {
		boolean flag = false;
		try {
			PreparedStatement smt1 = con
					.prepareStatement("select id from `"+db+"`.actaRedimensionar WHERE id = ? and fechaConfirma is null;");
			smt1.setLong(1, id_actaRedimensionar);
			ResultSet resultado1 = smt1.executeQuery();
			if (resultado1.next()) {
				PreparedStatement smt = con
						.prepareStatement("DELETE FROM `"+db+"`.actaRedimensionar WHERE id = ?;");
				smt.setLong(1, id_actaRedimensionar);
				smt.executeUpdate();
				smt.close();
				PreparedStatement smt2 = con
						.prepareStatement("DELETE FROM `"+db+"`.redimensionar WHERE id_actaRedimensionar = ?;");
				smt2.setLong(1, id_actaRedimensionar);
				smt2.executeUpdate();
				smt2.close();
				flag = true;
			}
			smt1.close();
			resultado1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean modifyXCampo(Connection con, String db, String campo, String valor, Long id_actaRedimensionar) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("UPDATE `"+db+"`.actaRedimensionar SET `"+campo+"` = ? WHERE id = ?;");
			smt.setString(1, valor.trim());
			smt.setLong(2, id_actaRedimensionar);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean existeNumero (Connection con, String db, Long numero) {
		boolean aux = false;
		try {
			PreparedStatement smt=null;
			ResultSet resultado = null;
			smt = con
					.prepareStatement("select numero from `"+db+"`.actaRedimensionar where numero = ?;");
			smt.setLong(1, numero);
			resultado = smt.executeQuery();
			if (resultado.next()) {		
				aux = true;
			}
			smt.close();
			resultado.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (aux);
	}
	
	public static Long findNuevoNumero(Connection con, String db) {
		Long aux = (long) 1;
		try {
			PreparedStatement smt = con
					.prepareStatement("select max(numero)+1 from `"+db+"`.actaRedimensionar;");
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {		
				aux = rs.getLong(1);
			}
			smt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(aux < 1) {
			aux = (long) 1;
		}
		return (aux);
	}
	
	public static List<ActaRedimensionar> allPorConfirmar(Connection con, String db) {
		List<ActaRedimensionar> lista = new ArrayList<ActaRedimensionar>();
		try {
			PreparedStatement smt=null;
			ResultSet rs = null;
			smt = con
					.prepareStatement("select id, numero, ifnull(fecha,''), actaPDF, ifnull(observaciones,''), ifnull(fechaConfirma,'') "
							+ " from `"+db+"`.actaRedimensionar"
							+ " where fechaConfirma is null;");
			rs = smt.executeQuery();
			while (rs.next()) {
				String fecha = myformatfecha.format(rs.getDate(3));
				String fechaConfirma = rs.getString(6);	
					lista.add(new ActaRedimensionar(rs.getLong(1),rs.getLong(2),fecha,rs.getString(4),rs.getString(5),fechaConfirma));
			}
			smt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<ActaRedimensionar> allConfirmadas(Connection con, String db) {
		List<ActaRedimensionar> lista = new ArrayList<ActaRedimensionar>();
		try {
			PreparedStatement smt=null;
			ResultSet rs = null;
			smt = con
					.prepareStatement("select id, numero, ifnull(fecha,''), actaPDF, ifnull(observaciones,''), ifnull(fechaConfirma,'') "
							+ " from `"+db+"`.actaRedimensionar"
							+ " where fechaConfirma is not null;");
			rs = smt.executeQuery();
			while (rs.next()) {
				String fecha = myformatfecha.format(rs.getDate(3));
				String fechaConfirma = rs.getString(6);	
					lista.add(new ActaRedimensionar(rs.getLong(1),rs.getLong(2),fecha,rs.getString(4),rs.getString(5),fechaConfirma));
			}
			smt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	
	public static ActaRedimensionar find(Connection con, String db, Long id_actaRedimensionar) {
		ActaRedimensionar aux = null;
		try {
			PreparedStatement smt=null;
			ResultSet rs = null;
			smt = con
					.prepareStatement(" select " +
							" id, numero, ifnull(fecha,''), actaPDF, ifnull(observaciones,''), ifnull(fechaConfirma,'') " +
							" from `"+db+"`.actaRedimensionar where id = ?;");
			smt.setLong(1, id_actaRedimensionar);
			rs = smt.executeQuery();
			if (rs.next()) {
				String fecha = rs.getString(3);
				String fechaConfirma = rs.getString(6);	
				aux = new ActaRedimensionar(rs.getLong(1),rs.getLong(2),fecha,rs.getString(4),rs.getString(5),fechaConfirma);
			}
			smt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (aux);
	}
	
	

	
	
	
}
