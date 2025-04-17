package models.tables;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MantOperadorMecanico {
	public Long id;
	public String userName;
	public String userKey;
	public String rut;
	public String nombre;
	public String cargo;
	public String fono;
	public String emailCor;
	public String emailPer;
	public Long id_mantActorPersonal; 	// 1 operador 2 mecanico (esto es FIJO)
	public Long id_mantTipoPersonal;	// 1 interno 2 externo (esto es FIJO)
	public String observaciones;
	public Long vigente;
	
	public String nameActorPersonal;
	public String nameTipoPersonal;
	
	public MantOperadorMecanico(Long id, String userName, String userKey, String rut, String nombre,
			String cargo, String fono, String emailCor, String emailPer, Long id_mantActorPersonal,
			Long id_mantTipoPersonal, String observaciones, Long vigente, String nameActorPersonal, String nameTipoPersonal) {
		super();
		this.id = id;
		this.userName = userName;
		this.userKey = userKey;
		this.rut = rut;
		this.nombre = nombre;
		this.cargo = cargo;
		this.fono = fono;
		this.emailCor = emailCor;
		this.emailPer = emailPer;
		this.id_mantActorPersonal = id_mantActorPersonal;
		this.id_mantTipoPersonal = id_mantTipoPersonal;
		this.observaciones = observaciones;
		this.vigente = vigente;
		this.nameActorPersonal = nameActorPersonal;
		this.nameTipoPersonal = nameTipoPersonal;
	}


	public MantOperadorMecanico() {super();}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getFono() {
		return fono;
	}

	public void setFono(String fono) {
		this.fono = fono;
	}

	public String getEmailCor() {
		return emailCor;
	}

	public void setEmailCor(String emailCor) {
		this.emailCor = emailCor;
	}

	public String getEmailPer() {
		return emailPer;
	}

	public void setEmailPer(String emailPer) {
		this.emailPer = emailPer;
	}

	public Long getId_mantActorPersonal() {
		return id_mantActorPersonal;
	}

	public void setId_mantActorPersonal(Long id_mantActorPersonal) {
		this.id_mantActorPersonal = id_mantActorPersonal;
	}

	public Long getId_mantTipoPersonal() {
		return id_mantTipoPersonal;
	}

	public void setId_mantTipoPersonal(Long id_mantTipoPersonal) {
		this.id_mantTipoPersonal = id_mantTipoPersonal;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Long getVigente() {
		return vigente;
	}

	public void setVigente(Long vigente) {
		this.vigente = vigente;
	}

	public String getNameActorPersonal() {
		return nameActorPersonal;
	}

	public void setNameActorPersonal(String nameActorPersonal) {
		this.nameActorPersonal = nameActorPersonal;
	}

	public String getNameTipoPersonal() {
		return nameTipoPersonal;
	}

	public void setNameTipoPersonal(String nameTipoPersonal) {
		this.nameTipoPersonal = nameTipoPersonal;
	}

	static SimpleDateFormat myformatfecha = new SimpleDateFormat("dd-MM-yyyy");
	
	
	public static Map<Long,MantOperadorMecanico> mapAll(Connection con, String db){
		Map<Long,MantOperadorMecanico> map = new HashMap<Long,MantOperadorMecanico>();
		List<MantOperadorMecanico> lista = MantOperadorMecanico.all(con, db);
		lista.forEach(x->{
			map.put(x.getId(), x);
		});
		return(map);
	}
	
	public static MantOperadorMecanico findXIdUser(Connection con, String db, Long id_operadorMecanico) {
		MantOperadorMecanico aux = null;
		try {
			PreparedStatement smt = con
						.prepareStatement("select"
								+ " mantOperadorMecanico.id,"
								+ " mantOperadorMecanico.userName,"
								+ " mantOperadorMecanico.userKey,"
								+ " mantOperadorMecanico.rut,"
								+ " mantOperadorMecanico.nombre,"
								+ " mantOperadorMecanico.cargo,"
								+ " mantOperadorMecanico.fono,"
								+ " mantOperadorMecanico.emailCor,"
								+ " mantOperadorMecanico.emailPer,"
								+ " mantOperadorMecanico.id_mantActorPersonal,"
								+ " mantOperadorMecanico.id_mantTipoPersonal,"
								+ " mantOperadorMecanico.observaciones,"
								+ " mantOperadorMecanico.vigente,"
								+ " mantActorPersonal.nombre,"
								+ " mantTipoPersonal.nombre"
								+ " from `"+db+"`.mantOperadorMecanico"
								+ " left join `"+db+"`.mantActorPersonal on mantActorPersonal.id = mantOperadorMecanico.id_mantActorPersonal"
								+ " left join `"+db+"`.mantTipoPersonal on mantTipoPersonal.id = mantOperadorMecanico.id_mantTipoPersonal"
								+ " where mantOperadorMecanico.id = ?;" );
				smt.setLong(1, id_operadorMecanico);
				ResultSet rs = smt.executeQuery();
				if (rs.next()) {
					aux = new MantOperadorMecanico(rs.getLong(1),
							rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),
							rs.getLong(10),rs.getLong(11),rs.getString(12),rs.getLong(13),rs.getString(14),rs.getString(15));
				}
				rs.close();
				smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (aux);
	}

	public static MantOperadorMecanico findXUserNameUser(Connection con, String db, String userName) {
		MantOperadorMecanico aux = null;
		try {
			PreparedStatement smt = con
					.prepareStatement("select"
							+ " mantOperadorMecanico.id,"
							+ " mantOperadorMecanico.userName,"
							+ " mantOperadorMecanico.userKey,"
							+ " mantOperadorMecanico.rut,"
							+ " mantOperadorMecanico.nombre,"
							+ " mantOperadorMecanico.cargo,"
							+ " mantOperadorMecanico.fono,"
							+ " mantOperadorMecanico.emailCor,"
							+ " mantOperadorMecanico.emailPer,"
							+ " mantOperadorMecanico.id_mantActorPersonal,"
							+ " mantOperadorMecanico.id_mantTipoPersonal,"
							+ " mantOperadorMecanico.observaciones,"
							+ " mantOperadorMecanico.vigente,"
							+ " mantActorPersonal.nombre,"
							+ " mantTipoPersonal.nombre"
							+ " from `"+db+"`.mantOperadorMecanico"
							+ " left join `"+db+"`.mantActorPersonal on mantActorPersonal.id = mantOperadorMecanico.id_mantActorPersonal"
							+ " left join `"+db+"`.mantTipoPersonal on mantTipoPersonal.id = mantOperadorMecanico.id_mantTipoPersonal"
							+ " where UPPER(mantOperadorMecanico.userName) = ?;" );
			smt.setString(1, userName.toUpperCase().trim());
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				aux = new MantOperadorMecanico(rs.getLong(1),
						rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),
						rs.getLong(10),rs.getLong(11),rs.getString(12),rs.getLong(13),rs.getString(14),rs.getString(15));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (aux);
	}

	public static boolean existe(Connection con, String db, String nombre) {
		boolean flag = false;
		try {
			PreparedStatement smt2 = con
					.prepareStatement("select mantOperadorMecanico.id, userName " +
							" from `"+db+"`.mantOperadorMecanico " +
							" where upper(mantOperadorMecanico.userName) = ?" );
			smt2.setString(1, nombre.toUpperCase());
			ResultSet rs2 = smt2.executeQuery();
			if (rs2.next()) {
				flag=true;
			}
			rs2.close();
			smt2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static List<MantOperadorMecanico> all(Connection con,String db) {
		List<MantOperadorMecanico> lista = new ArrayList<MantOperadorMecanico>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select"
							+ " mantOperadorMecanico.id,"
							+ " mantOperadorMecanico.userName,"
							+ " mantOperadorMecanico.userKey,"
							+ " mantOperadorMecanico.rut,"
							+ " mantOperadorMecanico.nombre,"
							+ " mantOperadorMecanico.cargo,"
							+ " mantOperadorMecanico.fono,"
							+ " mantOperadorMecanico.emailCor,"
							+ " mantOperadorMecanico.emailPer,"
							+ " mantOperadorMecanico.id_mantActorPersonal,"
							+ " mantOperadorMecanico.id_mantTipoPersonal,"
							+ " mantOperadorMecanico.observaciones,"
							+ " mantOperadorMecanico.vigente,"
							+ " mantActorPersonal.nombre,"
							+ " mantTipoPersonal.nombre"
							+ " from `"+db+"`.mantOperadorMecanico"
							+ " left join `"+db+"`.mantActorPersonal on mantActorPersonal.id = mantOperadorMecanico.id_mantActorPersonal"
							+ " left join `"+db+"`.mantTipoPersonal on mantTipoPersonal.id = mantOperadorMecanico.id_mantTipoPersonal;" );
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				lista.add(new MantOperadorMecanico(rs.getLong(1),
						rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),
						rs.getLong(10),rs.getLong(11),rs.getString(12),rs.getLong(13),rs.getString(14),rs.getString(15)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<MantOperadorMecanico> allVigentes(Connection con,String db) {
		List<MantOperadorMecanico> lista = new ArrayList<MantOperadorMecanico>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select"
							+ " mantOperadorMecanico.id,"
							+ " mantOperadorMecanico.userName,"
							+ " mantOperadorMecanico.userKey,"
							+ " mantOperadorMecanico.rut,"
							+ " mantOperadorMecanico.nombre,"
							+ " mantOperadorMecanico.cargo,"
							+ " mantOperadorMecanico.fono,"
							+ " mantOperadorMecanico.emailCor,"
							+ " mantOperadorMecanico.emailPer,"
							+ " mantOperadorMecanico.id_mantActorPersonal,"
							+ " mantOperadorMecanico.id_mantTipoPersonal,"
							+ " mantOperadorMecanico.observaciones,"
							+ " mantOperadorMecanico.vigente,"
							+ " mantActorPersonal.nombre,"
							+ " mantTipoPersonal.nombre"
							+ " from `"+db+"`.mantOperadorMecanico"
							+ " left join `"+db+"`.mantActorPersonal on mantActorPersonal.id = mantOperadorMecanico.id_mantActorPersonal"
							+ " left join `"+db+"`.mantTipoPersonal on mantTipoPersonal.id = mantOperadorMecanico.id_mantTipoPersonal"
							+ " where mantOperadorMecanico.vigente=1;" );
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				lista.add(new MantOperadorMecanico(rs.getLong(1),
						rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),
						rs.getLong(10),rs.getLong(11),rs.getString(12),rs.getLong(13),rs.getString(14),rs.getString(15)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (lista);
	}
	
	public static boolean modificaPorCampo(Connection con,String db,String campo,Long id_operadorMecanico,String valor) {
		boolean flag = false;
		try {
			PreparedStatement smt = con.prepareStatement("update `"+db+"`.mantOperadorMecanico set `" + campo + "` = ? WHERE id = ?;");		
			smt.setString(1, valor.trim());
			smt.setLong(2, id_operadorMecanico);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean modificaVigencia(Connection con,String db, Long id_operadorMecanico, Long vigencia) {
		if(id_operadorMecanico==1){
			return (false);
		}
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("update `"+db+"`.mantOperadorMecanico set vigente=? WHERE id = ?;");
			smt.setLong(1, vigencia);
			smt.setLong(2, id_operadorMecanico);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return(flag);
	}
	
	public static boolean create(Connection con,String db, MantOperadorMecanico operadorMecanico) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("insert into `"+db+"`.mantOperadorMecanico (userName, userKey, rut, nombre, cargo, fono, emailCor, emailPer,"
							+ "id_mantActorPersonal, id_mantTipoPersonal, observaciones)"
							+ " values (?,?,?,?,?,?,?,?,?,?,?)");
			smt.setString(1, operadorMecanico.getUserName().trim().toUpperCase());
			smt.setString(2, operadorMecanico.getUserKey().trim());
			smt.setString(3, operadorMecanico.getRut().trim());
			smt.setString(4, operadorMecanico.getNombre().trim());
			smt.setString(5, operadorMecanico.getCargo().trim());
			smt.setString(6, operadorMecanico.getFono().trim());
			smt.setString(7, operadorMecanico.getEmailCor().trim());
			smt.setString(8, operadorMecanico.getEmailPer().trim());
			smt.setLong(9, operadorMecanico.getId_mantActorPersonal());
			smt.setLong(10, operadorMecanico.getId_mantTipoPersonal());
			smt.setString(11, operadorMecanico.getObservaciones().trim());
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}
	

	
	
}
	
	
	
