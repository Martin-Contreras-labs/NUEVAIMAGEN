package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Diccionario {

	
	
	
	public static Map<String,String> mapDiccionario(Connection con, String db) {
		Map<String,String> map = new HashMap<String,String>();
		try {
			PreparedStatement smt = con
				.prepareStatement("select palabra, equiv from `"+db+"`.diccionario;" );
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				map.put(rs.getString(1), rs.getString(2));
			}
			rs.close();
			smt.close();
			
			List<TipoBodega> lista = TipoBodega.all(con, db);
			lista.forEach(x->{
				map.put("tipoBodega_"+x.getId().toString(), x.getNombre());
			});
			
			PreparedStatement smt2 = con
					.prepareStatement("select empresa, baseDato, logoEmpresa, pais from empresa where baseDato = ?;" );
			smt2.setString(1, db);
			ResultSet rs2 = smt2.executeQuery();
			if (rs2.next()) {
				map.put("nEmpresa", rs2.getString(1).toUpperCase());
				map.put("baseDato", rs2.getString(2));
				map.put("logoEmpresa", rs2.getString(3));
				map.put("pais", rs2.getString(4).toUpperCase());
			}
			rs2.close();
			smt2.close();
			
		} catch (SQLException e) {
    			e.printStackTrace();
		}
	
		
		
		return (map);
	}
	

	
	
	
}
