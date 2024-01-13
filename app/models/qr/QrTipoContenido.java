
package models.qr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class QrTipoContenido {

	public Long id;
	public String tipo;
	public String observaciones;
	
	
	public QrTipoContenido(Long id, String tipo, String observaciones) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.observaciones = observaciones;
	}


	public QrTipoContenido() {super();}

	
	
	public static List<QrTipoContenido> all(Connection con, String db) {
		List<QrTipoContenido> lista = new ArrayList<QrTipoContenido>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,tipo,observaciones from `"+db+"`.qrTipoContenido;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				lista.add(new QrTipoContenido(rs.getLong(1),rs.getString(2),rs.getString(3)));
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	

}
