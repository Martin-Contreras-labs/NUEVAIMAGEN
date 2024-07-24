package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class TipoReferencia {
	public String codigo;
	public String concepto;

	public TipoReferencia(String codigo, String concepto) {
		super();
		this.codigo = codigo;
		this.concepto = concepto;
	}

	public TipoReferencia() {super();}
	
	public static Map<String,String> mapAllCodVsConcep(Connection con,String db){
		Map<String,String> map = new HashMap<String,String>();
		List<TipoReferencia> lista = TipoReferencia.all(con, db);
		for(TipoReferencia x: lista) {
			map.put(x.codigo, x.concepto);
		}
		return(map);
	}

	
	public static List<TipoReferencia> all(Connection con,String db) {
		List<TipoReferencia> lista = new ArrayList<TipoReferencia>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select codigo,concepto " +
							" from `"+db+"`.tipoReferencia");
			ResultSet resultado = smt.executeQuery();
			while (resultado.next()) {
				lista.add(new TipoReferencia(resultado.getString(1),resultado.getString(2)));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (lista);
	}
	
	public static TipoReferencia find(Connection con,String db, String codigo) {
		TipoReferencia aux = new TipoReferencia();
		try {
			PreparedStatement smt = con
					.prepareStatement("select codigo,concepto from `"+db+"`.tipoReferencia WHERE codigo=?");
			smt.setString(1, codigo);
			ResultSet resultado = smt.executeQuery();
			if (resultado.next()) {
				aux = new TipoReferencia(resultado.getString(1),resultado.getString(2));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (aux);
	}
	
	

}
