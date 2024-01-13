package models.utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Integridad {
	
	
	public static void verifica(Connection con, String db, String idUser) {
		try {
			PreparedStatement smt11 = con
					.prepareStatement("select distinct id_guia from `"+db+"`.movimiento;");
			ResultSet rs11 = smt11.executeQuery();
			List<String> auxIdGuia = new ArrayList<String>();
			while(rs11.next()) {
				auxIdGuia.add(rs11.getString(1));
			}
			smt11.close();
			rs11.close();
			
			
			if(auxIdGuia.size()>0) {
				String auxid = auxIdGuia.toString();
				auxid = auxid.substring(1,auxid.length()-1);
				
				PreparedStatement smt1 = con
						.prepareStatement("select guia.id from `"+db+"`.guia where guia.id not in ("+auxid+");");
				ResultSet rs1 = smt1.executeQuery();
				
				List<String> auxGuia = new ArrayList<String>();
				while(rs1.next()) {
					auxGuia.add(rs1.getString(1));
				}
				
				if(auxGuia.size()>0) {
					PreparedStatement smt2 = con
							.prepareStatement("delete from `"+db+"`.guia where guia.id in ("+auxGuia.toString().replace("[", "").replace("]", "")+");");
					smt2.executeUpdate();
					smt2.close();
				}
				smt1.close();
				rs1.close();
			}
			
			
			PreparedStatement smt3 = con
					.prepareStatement("delete from `"+db+"`.actaBaja where id not in (select id_actaBaja from `"+db+"`.baja);");
			smt3.executeUpdate();
			smt3.close();
			
	} catch (SQLException e) {
		e.printStackTrace();
	}
		
	}
	
	
	
	

}
