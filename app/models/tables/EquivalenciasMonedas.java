package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class EquivalenciasMonedas {
	public String nickMoneda;
	public String equivEnMada;

	public EquivalenciasMonedas(String nickMoneda, String equivEnMada) {
		super();
		this.nickMoneda = nickMoneda;
		this.equivEnMada = equivEnMada;
	}

	public EquivalenciasMonedas() {super();}

	
	public static Map<String,EquivalenciasMonedas> mapEquivNickMonedas(Connection con, String db) {
		Map<String,EquivalenciasMonedas> map = new HashMap<String,EquivalenciasMonedas>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select nickMoneda,equivEnMada " +
							" from `"+db+"`.equivalenciasMonedas;" );
			ResultSet resultado = smt.executeQuery();
			while (resultado.next()) {
				map.put(resultado.getString(1), new EquivalenciasMonedas(resultado.getString(1),resultado.getString(2)));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return map;
	}
	
	
	

}
