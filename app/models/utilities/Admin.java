package models.utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Admin {
	
	
	public static List<List<String>> listaProyectos (Connection con) {
		List<List<String>> lista = new ArrayList<List<String>>();
		try {
			PreparedStatement smt = con.
					prepareStatement("select date_format(dateCreate,'%d-%m-%Y'),id,empresa, baseDato, pais from empresa " +
							" where vigente=1 order by pais, empresa;" );
			ResultSet rs = smt.executeQuery();
			int cont=0;
			while (rs.next()) {
				cont++;
				List<String> aux = new ArrayList<String>();
				aux.add(cont+"");
				aux.add(rs.getString(1));
				aux.add(rs.getString(2));
				aux.add(rs.getString(3));
				aux.add(rs.getString(4));
				aux.add(rs.getString(5));
				
				
				try {
					PreparedStatement smt2 = con.
							prepareStatement("select ifnull(DATE_FORMAT(max(fecha), '%d-%m-%Y'),''),ifnull(count(fecha),'') from `"+rs.getString(4)+"`.guia;" );

					ResultSet rs2 = smt2.executeQuery();
					
					if(rs2.next()) {
						aux.add(rs2.getString(1));	//6 fecha ultimo movimiento
						aux.add(rs2.getString(2));	//7 cantidad de movimientos
					}else {
						aux.add("");				//6 fecha ultimo movimiento
						aux.add("");				//7 cantidad de movimientos
					}
					rs2.close();
					smt2.close();
					
					PreparedStatement smt3 = con.
							prepareStatement("select ifnull(DATE_FORMAT(max(dateCreate), '%d-%m-%Y'),'') from `"+rs.getString(4)+"`.registroAccesos where usuario not in ('Admin','PBA','JIP');" );
					ResultSet rs3 = smt3.executeQuery();
					
					if(rs3.next()) {
						aux.add(rs3.getString(1));	//8 fecha ultimo ingreso
					}else {
						aux.add("");				//8 fecha ultimo ingreso
					}
					rs3.close();
					smt3.close();
					
					PreparedStatement smt4 = con.
							prepareStatement("select ifnull(DATE_FORMAT(max(fecha), '%d-%m-%Y'),''),ifnull(count(fecha),'') from `"+rs.getString(4)+"`.ventaServicio;" );
					ResultSet rs4 = smt4.executeQuery();
					
					if(rs4.next()) {
						aux.add(rs4.getString(1));	//9 fecha ultimo odo
						aux.add(rs4.getString(2));	//10 cantidad de odo
					}else {
						aux.add("");				//9 fecha ultimo odo
						aux.add("");				//10 cantidad de odo
					}
					rs4.close();
					smt4.close();
				} catch (SQLException e) {
					
					aux.add("");
					aux.add("");
					aux.add("");
					aux.add("");
					aux.add("");
					
				}

				
				
				lista.add(aux);
			}
			rs.close();smt.close();
		} catch (SQLException e) {e.printStackTrace();}
		return(lista);
	}
	
	

}
