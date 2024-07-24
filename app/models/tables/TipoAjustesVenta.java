package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TipoAjustesVenta {
	public Long id;
	public String ajusteVenta;

	public TipoAjustesVenta(Long id, String ajusteVenta) {
		super();
		this.id = id;
		this.ajusteVenta = ajusteVenta;
	}

	public TipoAjustesVenta() {super();}

	public static List<TipoAjustesVenta> all(Connection con, String db) {
		List<TipoAjustesVenta> lista = new ArrayList<TipoAjustesVenta>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,ajusteVenta from `"+db+"`.`tipoAjusteVenta` order by ajusteVenta");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				lista.add(new TipoAjustesVenta(rs.getLong(1),rs.getString(2)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (lista);
	}
	
	
}
