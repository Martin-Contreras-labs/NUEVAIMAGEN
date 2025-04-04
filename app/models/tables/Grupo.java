package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controllers.HomeController;

public class Grupo {
	public Long id;
	public String nombre;

	public Grupo(Long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public Grupo() {super();}

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	public static Map<Long,Grupo> mapAll(Connection con, String db) {
		Map<Long,Grupo> map = new HashMap<Long,Grupo>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,nombre from `"+db+"`.grupo");
			ResultSet rs = smt.executeQuery();
			map.put((long)0, new Grupo((long)0,"sin grupo"));
			while (rs.next()) {
				map.put(rs.getLong(1), new Grupo(rs.getLong(1),rs.getString(2)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (map);
	}
	
	public static Map<String,Grupo> mapAllPorNombre(Connection con, String db) {
		Map<String,Grupo> map = new HashMap<String,Grupo>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,nombre from `"+db+"`.grupo");
			ResultSet rs = smt.executeQuery();
			map.put("sin grupo", new Grupo((long)0,"sin grupo"));
			while (rs.next()) {
				map.put(rs.getString(2).toUpperCase(), new Grupo(rs.getLong(1),rs.getString(2)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (map);
	}
	
	public static List<Grupo> all(Connection con, String db) {
		List<Grupo> lista = new ArrayList<Grupo>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,nombre from `"+db+"`.grupo order by nombre");
			ResultSet resultado = smt.executeQuery();
			while (resultado.next()) {
				lista.add(new Grupo(resultado.getLong(1),resultado.getString(2)));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
    		e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<Grupo> allFiltroPorNombre(Connection con, String db, String filtroPorNombreGrupo) {
		List<Grupo> lista = new ArrayList<Grupo>();
		if(filtroPorNombreGrupo.length()>0) {
			filtroPorNombreGrupo = " where nombre in ("+filtroPorNombreGrupo+") ";
			try {
				PreparedStatement smt = con
						.prepareStatement("select id,nombre from `"+db+"`.grupo "+filtroPorNombreGrupo+" order by nombre");
				ResultSet resultado = smt.executeQuery();
				while (resultado.next()) {
					lista.add(new Grupo(resultado.getLong(1),resultado.getString(2)));
				}
				resultado.close();
				smt.close();
			} catch (SQLException e) {
	    		e.printStackTrace();
			}
		}
		return (lista);
	}
	
	public static List<Grupo> allFiltroPorId(Connection con, String db, String filtroPorIdGrupo) {
		List<Grupo> lista = new ArrayList<Grupo>();
		if(filtroPorIdGrupo.length()>0) {
			filtroPorIdGrupo = " where id in ("+filtroPorIdGrupo+") ";
			try {
				PreparedStatement smt = con
						.prepareStatement("select id,nombre from `"+db+"`.grupo "+filtroPorIdGrupo+" order by nombre");
				ResultSet resultado = smt.executeQuery();
				while (resultado.next()) {
					lista.add(new Grupo(resultado.getLong(1),resultado.getString(2)));
				}
				resultado.close();
				smt.close();
			} catch (SQLException e) {
	    		e.printStackTrace();
			}
		}
		return (lista);
	}
	
	
	public static boolean estaEnUso(Connection con, String db, Long id) {
		boolean flag = false;
		try {
			PreparedStatement smt1 = con
					.prepareStatement("Select * from `"+db+"`.equipo WHERE id_grupo = ?");
			smt1.setLong(1, id);
			ResultSet rs1 = smt1.executeQuery();
			if (rs1.next()) {
				flag = true;
			}
			rs1.close();
			smt1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean delete(Connection con, String db, Long id) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("delete from `"+db+"`.grupo WHERE id = ?");
			smt.setLong(1, id);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static Grupo find(Connection con, String db, Long id) {
		Grupo aux = new Grupo();
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,nombre from `"+db+"`.grupo WHERE id = ?" );
			smt.setLong(1, id);
			ResultSet resultado = smt.executeQuery();
			if (resultado.next()) {
				aux = new Grupo(resultado.getLong(1),resultado.getString(2));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (aux);
	}
	
	public static Long minId_grupo(Connection con, String db) {
		Long aux = (long) 1;
		try {
			PreparedStatement smt = con
					.prepareStatement("select min(id) from `"+db+"`.grupo;" );
			ResultSet resultado = smt.executeQuery();
			if (resultado.next()) {
				aux = resultado.getLong(1);
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (aux);
	}
	
	public static boolean existeGrupo(Connection con, String db, String nombre_grupo) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,nombre from `"+db+"`.grupo WHERE upper(nombre) = ?" );
			smt.setString(1, nombre_grupo.toUpperCase());
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
	
	public static boolean create(Connection con,String db, String nombreGrupo) {	
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("insert into `"+db+"`.grupo (nombre) values (?)", Statement.RETURN_GENERATED_KEYS);		
			smt.setString(1, nombreGrupo.trim());
			smt.executeUpdate();
			
			Long id_grupo = (long)0;
			ResultSet rs1 = smt.getGeneratedKeys();
            if (rs1.next()) {
            	id_grupo = rs1.getLong(1);
            }
            smt.close();
            rs1.close();
			
            PreparedStatement smt2 = con
					.prepareStatement("insert into `"+db+"`.atributo (id_grupo, atributo) "
							+ " (select '"+id_grupo+"', atributo from `"+db+"`.atributo where id_grupo = 1)");
			smt2.executeUpdate();
			smt2.close();
			
			flag = true;
			
			
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean modificaPorCampo(Connection con,String db, String campo, Long id_grupo, String valor) {
		boolean flag = false;
		String query = "update `"+db+"`.grupo set `"+campo+"` = ? WHERE id = ?;";
		try (PreparedStatement smt = con.prepareStatement(query)) {
				smt.setString(1, valor.trim());
				smt.setLong(2, id_grupo);
				int rowsAffected = smt.executeUpdate();
				if (rowsAffected > 0) {
	                flag = true;
	            }
		} catch (SQLException e) {
			logger.error("Error de base de datos en Grupo.modificaPorCampo. BASE: " + db, e);
		}
		return (flag);
	}
	
	public static List<Grupo> allConEquipos(Connection con,String db) {
		List<Grupo> lista = new ArrayList<Grupo>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select distinct equipo.id_grupo,grupo.nombre " +
							" from `"+db+"`.equipo " +
							" left join `"+db+"`.grupo on grupo.id=equipo.id_grupo " +
							" order by grupo.nombre;");
			ResultSet resultado = smt.executeQuery();
			while (resultado.next()) {
				lista.add(new Grupo(resultado.getLong(1),resultado.getString(2)));
			}
			resultado.close();
			smt.close();

		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (lista);
	}
	
	public static Long findIdXnombre(Connection con,String db, String nombre) {
		Long aux = (long)0;
		try {
			PreparedStatement smt = con
					.prepareStatement("select id from `"+db+"`.grupo where (upper(nombre))=(upper(?))" );
			smt.setString(1, nombre);
			ResultSet resultado = smt.executeQuery();
			if (resultado.next()) {
				aux = resultado.getLong(1);
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (aux);
	}
	
	


}
