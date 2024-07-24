package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TipoBodega {
	public Long id;
	public String nombre;

	public TipoBodega(Long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public TipoBodega() {super();}

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}

	
	public static Map<Long,TipoBodega> mapAll(Connection con, String db) {
		List<TipoBodega> lista = TipoBodega.all(con, db);
		Map<Long,TipoBodega> map = new HashMap<Long,TipoBodega>();
		lista.forEach(x->{
			map.put(x.id, x);
		});
		return (map);
	}
	
	
	public static List<TipoBodega> all(Connection con, String db) {
		List<TipoBodega> lista = new ArrayList<TipoBodega>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,nombre from `"+db+"`.tipoBodega order by nombre");
			ResultSet resultado = smt.executeQuery();
			while (resultado.next()) {
				lista.add(new TipoBodega(resultado.getLong(1),resultado.getString(2)));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (lista);
	}
	
	public static TipoBodega find(Connection con, String db, Long id_tipoBodega) {
		TipoBodega aux = null;
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,nombre from `"+db+"`.tipoBodega WHERE id = ?" );
			smt.setLong(1, id_tipoBodega);
			ResultSet resultado = smt.executeQuery();
			if (resultado.next()) {
				aux = new TipoBodega(resultado.getLong(1),resultado.getString(2));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (aux);
	}
	
	
	

}
