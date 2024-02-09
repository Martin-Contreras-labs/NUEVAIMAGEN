package models.tables;

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

import models.forms.FormBaja;

public class ActaBaja {
	public Long id;
	public Long numero;
	public String fecha;
	public String actaBajaPDF;
	public String observaciones;
	
	public ActaBaja(Long id, Long numero, String fecha, String actaBajaPDF, String observaciones) {
		super();
		this.id = id;
		this.numero = numero;
		this.fecha = fecha;
		this.actaBajaPDF = actaBajaPDF;
		this.observaciones = observaciones;
	}
	
	public ActaBaja(FormBaja form) {
		super();
		this.id = (long)0;
		this.numero = form.numero;
		this.fecha = form.fecha;
		this.actaBajaPDF = "";
		this.observaciones = form.observaciones;
	}

	public ActaBaja() {
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

	public String getActaBajaPDF() {
		return actaBajaPDF;
	}

	public void setActaBajaPDF(String actaBajaPDF) {
		this.actaBajaPDF = actaBajaPDF;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}


	static SimpleDateFormat myformatfecha = new SimpleDateFormat("dd-MM-yyyy");
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	
	public static boolean modifyXCampo(Connection con, String db, String campo, String valor, Long id_actaBaja) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("UPDATE `"+db+"`.actaBaja SET `"+campo+"` = ? WHERE id = ?;");
			smt.setString(1, valor.trim());
			smt.setLong(2, id_actaBaja);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static Long findNuevoNumero(Connection con, String db) {
		Long aux = (long) 1;
		try {
			PreparedStatement smt=null;
			ResultSet resultado = null;
			smt = con
					.prepareStatement("select max(numero)+1 from `"+db+"`.actaBaja;");
			resultado = smt.executeQuery();
			if (resultado.next()) {		
				aux = resultado.getLong(1);
			}
			smt.close();
			resultado.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(aux < 1) {
			aux = (long) 1;
		}
		return (aux);
	}
	
	public static boolean existeNumero (Connection con, String db, Long numero) {
		boolean aux = false;
		try {
			PreparedStatement smt=null;
			ResultSet resultado = null;
			smt = con
					.prepareStatement("select numero from `"+db+"`.actaBaja where numero = ?;");
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
	
	public static Long findIdActaBaja (Connection con, String db, Long numero) {
		Long aux = (long)0;
		try {
			PreparedStatement smt = con
					.prepareStatement("select id from `"+db+"`.actaBaja where numero = ?;");
			smt.setLong(1, numero);
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {		
				aux = rs.getLong(1);
			}
			smt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (aux);
	}
	
	public static Long create(Connection con, String db, ActaBaja actaBaja) {
		Long id_acta = (long)0;
		try {
			PreparedStatement smt = con
					.prepareStatement("INSERT INTO `"+db+"`.actaBaja (numero,fecha,observaciones) " +
								" VALUES (?,?,?)");
			smt.setLong(1, actaBaja.getNumero());
			smt.setString(2, actaBaja.getFecha());
			smt.setString(3, actaBaja.getObservaciones());
			smt.executeUpdate();
			smt.close();
			PreparedStatement smt2 = con
					.prepareStatement("select id from `"+db+"`.actaBaja where numero = ?");
			smt2.setLong(1, actaBaja.getNumero());
			ResultSet rs2 = smt2.executeQuery();
			if(rs2.next()) {
				id_acta = rs2.getLong(1);
			}
			rs2.close();
			smt2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (id_acta);
	}
	
	public static boolean delete(Connection con, String db, Long id_actaBaja) {
		boolean flag = false;
		try {
			PreparedStatement smt1 = con
					.prepareStatement("select id from `"+db+"`.baja WHERE id_actaBaja = ?;");
			smt1.setLong(1, id_actaBaja);
			ResultSet resultado1 = smt1.executeQuery();
			if (resultado1.next()) {
				flag = false;
			}else{			
				PreparedStatement smt = con
						.prepareStatement("DELETE FROM `"+db+"`.actaBaja WHERE id = ?;");
				smt.setLong(1, id_actaBaja);
				smt.executeUpdate();
				smt.close();
			}
			smt1.close();
			resultado1.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static List<ActaBaja> all(Connection con, String db) {
		List<ActaBaja> lista = new ArrayList<ActaBaja>();
		
		try {
			PreparedStatement smt=null;
			ResultSet rs = null;
			smt = con
					.prepareStatement(" select " +
							" id, numero, fecha, actaBajaPDF, observaciones " +
							" from `"+db+"`.actaBaja;");
			rs = smt.executeQuery();
			while (rs.next()) {	
				String fecha = null;
				if (rs.getString(3) != null) {
					fecha = myformatfecha.format(rs.getDate(3));
				}
				lista.add(new ActaBaja(rs.getLong(1),rs.getLong(2),fecha,rs.getString(4),rs.getString(5)));
			}
			smt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<ActaBaja> allModificables(Connection con, String db) {
		List<ActaBaja> lista = new ArrayList<ActaBaja>();
		try {
			Map<Long,Long> mapIdActas = new HashMap<Long,Long>();
			PreparedStatement smt2=null;
			ResultSet rs2 = null;
			smt2 = con
					.prepareStatement("select id_actaBaja from `"+db+"`.baja where esModificable=1 and fechaConfirma is null group by id_actaBaja;");
			rs2 = smt2.executeQuery();
			while (rs2.next()) {		
				mapIdActas.put(rs2.getLong(1), rs2.getLong(1));
			}
			smt2.close();
			rs2.close();
			
			PreparedStatement smt=null;
			ResultSet rs = null;
			smt = con
					.prepareStatement(" select " +
							" id, numero, fecha, actaBajaPDF, observaciones " +
							" from `"+db+"`.actaBaja;");
			rs = smt.executeQuery();
			while (rs.next()) {
				if(mapIdActas.get(rs.getLong(1)) != null) {
					String fecha = null;		
					if (rs.getString(3) != null) {
						fecha = myformatfecha.format(rs.getDate(3));
					}
					lista.add(new ActaBaja(rs.getLong(1),rs.getLong(2),fecha,rs.getString(4),rs.getString(5)));
				}
			}
			smt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static ActaBaja find(Connection con, String db, Long id_actaBaja) {
		ActaBaja aux = null;
		try {
			PreparedStatement smt=null;
			ResultSet rs = null;
			smt = con
					.prepareStatement(" select " +
							" id, numero, fecha, actaBajaPDF, observaciones " +
							" from `"+db+"`.actaBaja where id = ?;");
			smt.setLong(1, id_actaBaja);
			rs = smt.executeQuery();
			if (rs.next()) {
				String fecha="";
				if (rs.getString(3) != null) {fecha = myformatfecha.format(rs.getDate(3));}
				aux = new ActaBaja(rs.getLong(1),rs.getLong(2),fecha,rs.getString(4),rs.getString(5));
			}
			smt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (aux);
	}
	
	
	
	
	
}
