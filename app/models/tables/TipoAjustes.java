package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TipoAjustes {
	public Long id;
	public String ajuste;
	public Long factor;

	public TipoAjustes(Long id, String ajuste, Long factor) {
		super();
		this.id = id;
		this.ajuste = ajuste;
		this.factor = factor;
	}

	public TipoAjustes() {super();}

	public static List<TipoAjustes> all(Connection con, String db) {
		List<TipoAjustes> lista = new ArrayList<TipoAjustes>();
		try {
			PreparedStatement smt = con
					.prepareStatement("SELECT id,ajuste,factor FROM `"+db+"`.`tipoAjuste` order by ajuste");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				lista.add(new TipoAjustes(rs.getLong(1),rs.getString(2),rs.getLong(3)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (lista);
	}
	
	
}
