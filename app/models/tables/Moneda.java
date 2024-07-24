package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Moneda {
	public Long id;
	public String nombre;
	public String nickName;
	public Long numeroDecimales;

	public Moneda(Long id, String nombre, String nickName, Long numeroDecimales) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.nickName = nickName;
		this.numeroDecimales = numeroDecimales;
	}

	public Moneda() {super();}

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}
	public String getNickName() {return nickName;}
	public void setNickName(String nickName) {this.nickName = nickName;}
	public Long getNumeroDecimales() {return numeroDecimales;}
	public void setNumeroDecimales(Long numeroDecimales) {this.numeroDecimales = numeroDecimales;}
	
	
	public static Map<String,Long> numeroDecimalxNombre(Connection con, String db) {
		Map<String,Long> decimales = new HashMap<String,Long>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select nickName,numeroDecimales from `"+db+"`.moneda");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				decimales.put(rs.getString(1).toUpperCase(),rs.getLong(2));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (decimales);
	}
	
	public static int numeroDecimalxId(Connection con, String db, String id_moneda) {
		int decimales=0;
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,numeroDecimales from `"+db+"`.moneda where id =?;");
			smt.setString(1, id_moneda.trim());
			ResultSet resultado = smt.executeQuery();
			if (resultado.next()) {
				decimales =resultado.getInt(2);
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (decimales);
	}
	
	public static Map<Long,Long> numeroDecimal(Connection con, String db) {
		Map<Long,Long> decimales = new HashMap<Long,Long>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,numeroDecimales from `"+db+"`.moneda");
			ResultSet resultado = smt.executeQuery();
			while (resultado.next()) {
				decimales.put(resultado.getLong(1),resultado.getLong(2));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (decimales);
	}
	
	public static List<Moneda> all(Connection con, String db) {
		List<Moneda> lista = new ArrayList<Moneda>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,nombre,nickName,numeroDecimales " +
							" from `"+db+"`.moneda order by nickName");
			ResultSet resultado = smt.executeQuery();
			while (resultado.next()) {
				lista.add(new Moneda(resultado.getLong(1),resultado.getString(2),
						resultado.getString(3),resultado.getLong(4)));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<Moneda> allSinMonPrincipal(Connection con, String db) {
		List<Moneda> lista = new ArrayList<Moneda>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,nombre,nickName,numeroDecimales " +
							" from `"+db+"`.moneda where id <> 1 order by nickName");
			ResultSet resultado = smt.executeQuery();
			while (resultado.next()) {
				lista.add(new Moneda(resultado.getLong(1),resultado.getString(2),
						resultado.getString(3),resultado.getLong(4)));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static boolean modifyDecimales(Connection con,String db, Long id_moneda, Long numeroDecimales) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("update `"+db+"`.moneda set numeroDecimales = ? " +
							" WHERE id = ?");
			smt.setLong(1, numeroDecimales);
			smt.setLong(2, id_moneda);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}

	
	public static Map<Long,String> mapIdMonedaMoneda(Connection con, String db) {
		Map<Long,String> lista = new HashMap<Long,String>();
		try {
			PreparedStatement smt5 = con
					.prepareStatement(" select id,nickName from `"+db+"`.moneda;");
			ResultSet rs5 = smt5.executeQuery();
			while (rs5.next()) {
				lista.put(rs5.getLong(1), rs5.getString(2));
			}
			rs5.close();
			smt5.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (lista);
	}
	
	public static Moneda find(Connection con, String db, Long id_moneda) {
		Moneda aux = null;
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,nombre,nickName,numeroDecimales " +
							" from `"+db+"`.moneda WHERE id = ?" );
			smt.setLong(1, id_moneda);
			ResultSet resultado = smt.executeQuery();
			if (resultado.next()) {
				aux = new Moneda(resultado.getLong(1),resultado.getString(2),
						resultado.getString(3),resultado.getLong(4));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (aux);
	}
	
	public static Moneda findPorNickName(Connection con, String db, String nickName) {
		Moneda aux = null;
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,nombre,nickName,numeroDecimales " +
							" from `"+db+"`.moneda WHERE id = ?" );
			smt.setString(1, nickName);
			ResultSet resultado = smt.executeQuery();
			if (resultado.next()) {
				aux = new Moneda(resultado.getLong(1),resultado.getString(2),
						resultado.getString(3),resultado.getLong(4));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (aux);
	}
	
	public static Map<Long,Moneda> mapMonedas(Connection con, String db) {
		Map<Long,Moneda> map = new HashMap<Long,Moneda>();
		List<Moneda> all = Moneda.all(con, db);
		for(int i=0; i<all.size(); i++) {
			map.put(all.get(i).id, all.get(i));
		}
		return map;
	}
	
	public static Map<String,Moneda> mapNickMonedas(Connection con, String db) {
		Map<String,Moneda> map = new HashMap<String,Moneda>();
		List<Moneda> all = Moneda.all(con, db);
		for(int i=0; i<all.size(); i++) {
			map.put(all.get(i).nickName.toUpperCase(), all.get(i));
		}
		return map;
	}
	
	public static Moneda create(Connection con, String db, String nickMoneda, String nombreMoneda) {
		Moneda aux = null;
		try {
			PreparedStatement smt = con
					.prepareStatement("insert into `"+db+"`.moneda (nombre,nickName,numeroDecimales) values (?,?,?);" );
			smt.setString(1, nombreMoneda);
			smt.setString(2, nickMoneda);
			smt.setString(3, "0");
			smt.executeUpdate();
			smt.close();
			
			PreparedStatement smt2 = con
					.prepareStatement("select max(id) from `"+db+"`.unidad");
			ResultSet rs2 = smt2.executeQuery();
			if (rs2.next()) {
				aux = Moneda.find(con, db, rs2.getLong(1));
			}
			smt2.close();
			rs2.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (aux);
	}

	
	

}
