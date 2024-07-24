package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class ClaseServicio {
	public Long id;
	public String nombre;

	public ClaseServicio(Long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public ClaseServicio() {super();}

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}

	public static Map<Long,ClaseServicio> mapAll(Connection con, String db) {
		Map<Long,ClaseServicio> map = new HashMap<Long,ClaseServicio>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,nombre from `"+db+"`.claseServicio");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				map.put(rs.getLong(1), new ClaseServicio(rs.getLong(1),rs.getString(2)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (map);
	}
	
	public static List<ClaseServicio> all(Connection con, String db) {
		List<ClaseServicio> lista = new ArrayList<ClaseServicio>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,nombre from `"+db+"`.claseServicio order by nombre");
			ResultSet resultado = smt.executeQuery();
			while (resultado.next()) {
				lista.add(new ClaseServicio(resultado.getLong(1),resultado.getString(2)));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
    		e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<ClaseServicio> allFiltroPorNombre(Connection con, String db, String filtroPorNombreClase) {
		List<ClaseServicio> lista = new ArrayList<ClaseServicio>();
		if(filtroPorNombreClase.length()>0) {
			filtroPorNombreClase = " where nombre in ("+filtroPorNombreClase+") ";
			try {
				PreparedStatement smt = con
						.prepareStatement("select id,nombre from `"+db+"`.claseServicio "+filtroPorNombreClase+" order by nombre");
				ResultSet resultado = smt.executeQuery();
				while (resultado.next()) {
					lista.add(new ClaseServicio(resultado.getLong(1),resultado.getString(2)));
				}
				resultado.close();
				smt.close();
			} catch (SQLException e) {
	    		e.printStackTrace();
			}
		}
		return (lista);
	}
	
	public static List<ClaseServicio> allFiltroPorId(Connection con, String db, String filtroPorIdClase) {
		List<ClaseServicio> lista = new ArrayList<ClaseServicio>();
		if(filtroPorIdClase.length()>0) {
			filtroPorIdClase = " where id in ("+filtroPorIdClase+") ";
			try {
				PreparedStatement smt = con
						.prepareStatement("select id,nombre from `"+db+"`.claseServicio "+filtroPorIdClase+" order by nombre");
				ResultSet resultado = smt.executeQuery();
				while (resultado.next()) {
					lista.add(new ClaseServicio(resultado.getLong(1),resultado.getString(2)));
				}
				resultado.close();
				smt.close();
			} catch (SQLException e) {
	    		e.printStackTrace();
			}
		}
		return (lista);
	}
	
	
	public static boolean estaEnUso(Connection con, String db, Long id_clase) {
		boolean flag = false;
		try {
			PreparedStatement smt1 = con
					.prepareStatement("Select * from `"+db+"`.servicio WHERE id_claseServicio = ?");
			smt1.setLong(1, id_clase);
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
	
	public static boolean delete(Connection con, String db, Long id_clase) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("delete from `"+db+"`.claseServicio WHERE id = ?");
			smt.setLong(1, id_clase);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static ClaseServicio find(Connection con, String db, Long id_clase) {
		ClaseServicio aux = new ClaseServicio();
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,nombre from `"+db+"`.claseServicio WHERE id = ?" );
			smt.setLong(1, id_clase);
			ResultSet resultado = smt.executeQuery();
			if (resultado.next()) {
				aux = new ClaseServicio(resultado.getLong(1),resultado.getString(2));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (aux);
	}
	
	public static boolean existeClase(Connection con, String db, String nombre_clase) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,nombre from `"+db+"`.claseServicio WHERE upper(nombre) = ?" );
			smt.setString(1, nombre_clase.toUpperCase());
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
	
	public static boolean create(Connection con,String db, String nombre_clase) {	
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("insert into `"+db+"`.claseServicio (nombre) values (?)");		
			smt.setString(1, nombre_clase.trim());
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean modificaPorCampo(Connection con,String db, String campo, Long id_clase, String valor) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("update `"+db+"`.claseServicio set `"+campo+"` = ? WHERE id = ?");
			smt.setString(1, valor.trim());
			smt.setLong(2, id_clase);
			smt.executeUpdate();
			smt.close();
			flag=true;
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}
	
	public static List<ClaseServicio> allConServicios(Connection con, String db) {
		List<ClaseServicio> lista = new ArrayList<ClaseServicio>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select distinct servicio.id_claseServicio, claseServicio.nombre " +
							" from `"+db+"`.servicio " +
							" left join `"+db+"`.claseServicio on claseServicio.id = servicio.id_claseServicio " +
							" order by claseServicio.nombre;");
			ResultSet resultado = smt.executeQuery();
			while (resultado.next()) {
				lista.add(new ClaseServicio(resultado.getLong(1),resultado.getString(2)));
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
					.prepareStatement("select id from `"+db+"`.claseServicio where (upper(nombre))=(upper(?))" );
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
