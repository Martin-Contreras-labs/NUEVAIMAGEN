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

import models.api.ApiRelBase;
import play.libs.ws.WSClient;


public class CotizaSolucion {
	public Long id;
	public String solucion;

	public CotizaSolucion(Long id, String solucion) {
		super();
		this.id = id;
		this.solucion = solucion;
	}

	public CotizaSolucion() {super();}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSolucion() {
		return solucion;
	}

	public void setSolucion(String solucion) {
		this.solucion = solucion;
	}

	public static Map<Long,CotizaSolucion> mapAll(Connection con, String db){
		List<CotizaSolucion> lista = CotizaSolucion.allconIdNegativos(con, db);
		Map<Long, CotizaSolucion> map = new HashMap<Long, CotizaSolucion>();
		lista.forEach(x->{
			map.put(x.getId(), x);
		});
		return(map);
	}
	
	public static List<CotizaSolucion> all(Connection con, String db) {
		List<CotizaSolucion> lista = new ArrayList<CotizaSolucion>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,solucion from `"+db+"`.cotizaSolucion");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				if(rs.getLong(1) > 0) {
					lista.add(new CotizaSolucion(rs.getLong(1),rs.getString(2)));
				}
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<CotizaSolucion> allconIdNegativos(Connection con, String db) {
		List<CotizaSolucion> lista = new ArrayList<CotizaSolucion>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,solucion from `"+db+"`.cotizaSolucion");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				lista.add(new CotizaSolucion(rs.getLong(1),rs.getString(2)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (lista);
	}
	
	public static CotizaSolucion find(Connection con,String db, Long id) {
		CotizaSolucion aux = null;
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,solucion " +
							" from `"+db+"`.cotizaSolucion WHERE id = ?" );
			smt.setLong(1, id);
			ResultSet resultado = smt.executeQuery();
			if (resultado.next()) {
				aux = new CotizaSolucion(resultado.getLong(1),resultado.getString(2));
			}
			resultado.close();
			smt.close();

		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (aux);
	}
	
	public static boolean estaEnUso(Connection con, String db, Long id_cotizaSolucion) {
		boolean flag = false;
		try {
			PreparedStatement smt1 = con
					.prepareStatement("Select * from `"+db+"`.cotizacion WHERE id_cotizaSolucion = ?");
			smt1.setLong(1, id_cotizaSolucion);
			ResultSet rs1 = smt1.executeQuery();
			if (rs1.next()) {
				flag = true;
			}
			rs1.close();
			smt1.close();
			
			PreparedStatement smt2 = con
					.prepareStatement("Select * from `"+db+"`.cotiOdo WHERE id_cotizaSolucion = ?");
			smt2.setLong(1, id_cotizaSolucion);
			ResultSet rs2 = smt2.executeQuery();
			if (rs2.next()) {
				flag = true;
			}
			rs2.close();
			smt2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean delete(Connection con, String db, Long id_cotizaSolucion) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("delete from `"+db+"`.cotizaSolucion WHERE id = ?");
			smt.setLong(1, id_cotizaSolucion);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean existeSolucion(Connection con, String db, String nombre_solucion) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,solucion from `"+db+"`.cotizaSolucion WHERE upper(solucion) = ?" );
			smt.setString(1, nombre_solucion.toUpperCase());
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
	
	public static boolean modificaPorCampo(Connection con,String db, String campo, Long id_cotizaSolucion, String valor) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("update `"+db+"`.cotizaSolucion set `"+campo+"` = ? WHERE id = ?");
			smt.setString(1, valor.trim());
			smt.setLong(2, id_cotizaSolucion);
			smt.executeUpdate();
			smt.close();
			flag=true;
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean create(Connection con, String db, String nombre_solucion, WSClient ws) {	
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("insert into `"+db+"`.cotizaSolucion (solucion) values (?)",Statement.RETURN_GENERATED_KEYS);		
			smt.setString(1, nombre_solucion.trim());
			smt.executeUpdate();
			Long id = (long)0;
			ResultSet rs = smt.getGeneratedKeys();
			
			if(db.equals("madaAludom")) {
				if (rs.next()) {
	            	id = rs.getLong(1);
	            	EmisorTributario emisor = EmisorTributario.find(con, db);
	            	String json = "{\n"
	            			+ "   \"name\": \""+nombre_solucion+"\",\n"
	            			+ "   \"code\": \"MADA-"+id+"\",\n"
	            			+ "   \"category_id\": 35357,\n"
	            			+ "   \"product_type\": 1,\n"
	            			+ "   \"is_tax\": true,\n"
	            			+ "   \"is_inventory\": false,\n"
	            			+ "   \"currency\": 1,\n"
	            			+ "   \"price\": 100\n"
	            			+ "}";
	            	ApiRelBase.createProducto(emisor, json, ws);
	            }
			}
			
            smt.close();
            rs.close();
			flag = true;
			
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}
	
	

}
