package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperadorServicio {
	public Long id;
	public Long id_userAdam;
	public String rut;
	public String nombre;
	public String cargo;
	public String email;
	public String fono;
	public String notas;
	public Long activo;
	
	public String userAdamName;
	
	public OperadorServicio(Long id, Long id_userAdam, String rut, String nombre, String cargo, String email,
			String fono, String notas, Long activo, String userAdamName) {
		super();
		this.id = id;
		this.id_userAdam = id_userAdam;
		this.rut = rut;
		this.nombre = nombre;
		this.cargo = cargo;
		this.email = email;
		this.fono = fono;
		this.notas = notas;
		this.activo = activo;
		this.userAdamName = userAdamName;
	}

	public OperadorServicio() {
		super();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId_userAdam() {
		return id_userAdam;
	}

	public void setId_userAdam(Long id_userAdam) {
		this.id_userAdam = id_userAdam;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFono() {
		return fono;
	}

	public void setFono(String fono) {
		this.fono = fono;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	public Long getActivo() {
		return activo;
	}

	public void setActivo(Long activo) {
		this.activo = activo;
	}

	public String getUserAdamName() {
		return userAdamName;
	}

	public void setUserAdamName(String userName) {
		this.userAdamName = userName;
	}
	
	public static List<OperadorServicio> allActivos(Connection con, String db) {
		List<OperadorServicio> lista = new ArrayList<OperadorServicio>();
		List<OperadorServicio> all = OperadorServicio.all(con, db);
		all.forEach(x->{
			if((long)x.getActivo()==(long)1) {
				lista.add(x);
			}
		});
		return (lista);
	}
	
	public static Map<Long,OperadorServicio> mapAll(Connection con, String db) {
		Map<Long,OperadorServicio> map = new HashMap<Long,OperadorServicio>();
		List<OperadorServicio> lista = OperadorServicio.all(con, db);
		lista.forEach(x->{
			map.put(x.getId(), x);
		});
		return (map);
	}

	public static List<OperadorServicio> all(Connection con, String db) {
		List<OperadorServicio> lista = new ArrayList<OperadorServicio>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" operadorServicio.id, " +
							" operadorServicio.id_userAdam, " +
							" operadorServicio.rut, " +
							" operadorServicio.nombre, " +
							" operadorServicio.cargo, " +
							" operadorServicio.email, " +
							" operadorServicio.fono, " +
							" operadorServicio.notas, " +
							" operadorServicio.activo, " +
							" ifnull(usuario.userName,'N/A') " +
							" from `"+db+"`.operadorServicio " +
							" left join `"+db+"`.usuario on usuario.id = operadorServicio.id_userAdam " +
							" order by operadorServicio.nombre;");
			ResultSet rs = smt.executeQuery();
			
			while (rs.next()) {
				lista.add(new OperadorServicio(rs.getLong(1),rs.getLong(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),
						rs.getString(7),rs.getString(8),rs.getLong(9),rs.getString(10)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static OperadorServicio find(Connection con, String db, Long id_operador) {
		OperadorServicio aux = null;
		try {
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" operadorServicio.id, " +
							" operadorServicio.id_userAdam, " +
							" operadorServicio.rut, " +
							" operadorServicio.nombre, " +
							" operadorServicio.cargo, " +
							" operadorServicio.email, " +
							" operadorServicio.fono, " +
							" operadorServicio.notas, " +
							" operadorServicio.activo, " +
							" ifnull(usuario.userName,'N/A') " +
							" from `"+db+"`.operadorServicio " +
							" left join `"+db+"`.usuario on usuario.id = operadorServicio.id_userAdam " +
							" where operadorServicio.id=?;");
			smt.setLong(1, id_operador);
			ResultSet rs = smt.executeQuery();
			
			if (rs.next()) {
				aux = new OperadorServicio(rs.getLong(1),rs.getLong(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),
						rs.getString(7),rs.getString(8),rs.getLong(9),rs.getString(10));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (aux);
	}
	
	public static boolean existeRut(Connection con, String db, String rut) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement(" select rut from `"+db+"`.operadorServicio where rut=?;");
			smt.setString(1, rut);
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				flag = true;
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean create(Connection con, String db, OperadorServicio form) {
		boolean flag = false;
		if(!OperadorServicio.existeRut(con, db, form.getRut())) {
			try {
				PreparedStatement smt = con
						.prepareStatement(" insert into `"+db+"`.operadorServicio (id_userAdam, rut, nombre, cargo, email, fono, notas, activo) "
								+ " values (?,?,?,?,?,?,?,?);");
				smt.setLong(1, form.getId_userAdam());
				smt.setString(2, form.getRut());
				smt.setString(3, form.getNombre());
				smt.setString(4, form.getCargo());
				smt.setString(5, form.getEmail());
				smt.setString(6, form.getFono());
				smt.setString(7, form.getNotas());
				smt.setLong(8, (long) 1);
				smt.executeUpdate();
				smt.close();
				flag = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return (flag);
	}
	
	public static boolean update(Connection con, String db, OperadorServicio form) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement(" update `"+db+"`.operadorServicio set id_userAdam=?, rut=?, nombre=?, cargo=?, email=?, fono=?, notas=? "
							+ " where id=?;");
			smt.setLong(1, form.getId_userAdam());
			smt.setString(2, form.getRut());
			smt.setString(3, form.getNombre());
			smt.setString(4, form.getCargo());
			smt.setString(5, form.getEmail());
			smt.setString(6, form.getFono());
			smt.setString(7, form.getNotas());
			smt.setLong(8, form.getId());
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean cambiaActivo(Connection con, String db, Long id_operador, Long valor) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement(" update `"+db+"`.operadorServicio set activo=? where id=?;");
			smt.setLong(1, valor);
			smt.setLong(2, id_operador);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	
	
}
