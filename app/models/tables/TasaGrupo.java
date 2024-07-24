package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TasaGrupo {
	public Long id_bodegaEmpresa;
	public Long id_grupo;
	public Double tasaArriendo;
	public String nombreGrupo;

	public TasaGrupo(Long id_bodegaEmpresa, Long id_grupo, Double tasaArriendo,
			String nombreGrupo) {
		super();
		this.id_bodegaEmpresa = id_bodegaEmpresa;
		this.id_grupo = id_grupo;
		this.tasaArriendo = tasaArriendo;
		this.nombreGrupo = nombreGrupo;
	}

	public TasaGrupo() {super();}

	public Long getId_bodegaEmpresa() {return id_bodegaEmpresa;}
	public void setId_bodegaEmpresa(Long id_bodegaEmpresa) {this.id_bodegaEmpresa = id_bodegaEmpresa;}
	public Long getId_grupo() {return id_grupo;}
	public void setId_grupo(Long id_grupo) {this.id_grupo = id_grupo;}
	public Double getTasaArriendo() {return tasaArriendo;}
	public void setTasaArriendo(Double tasaArriendo) {this.tasaArriendo = tasaArriendo;}
	public String getNombreGrupo() {return nombreGrupo;}
	public void setNombreGrupo(String nombreGrupo) {this.nombreGrupo = nombreGrupo;}
	
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");
	
	
	public static Map<String,TasaGrupo> mapAllXBodegaEmpresa(Connection con, String db, Long id_bodegaEmpresa) {
		Map<String,TasaGrupo> map = new HashMap<String,TasaGrupo>();
		List<TasaGrupo> lista = TasaGrupo.allXBodegaEmpresa(con, db, id_bodegaEmpresa);
		lista.forEach(x->{
			map.put(x.getId_bodegaEmpresa()+"_"+x.getId_grupo(), x);
		});
		return(map);
	}
	
	
	public static List<TasaGrupo> allXBodegaEmpresa(Connection con, String db, Long id_bodegaEmpresa) {
		List<TasaGrupo> lista = new ArrayList<TasaGrupo>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select id_bodegaEmpresa,id_grupo,tasaArriendo,nombre " +
							" from `"+db+"`.tasaGrupo  " +
							" left join `"+db+"`.grupo on grupo.id = tasaGrupo.id_grupo " +
							" where id_bodegaEmpresa = ? "+
							" order by grupo.nombre;");
			smt.setLong(1, id_bodegaEmpresa);
			ResultSet resultado = smt.executeQuery();
			while (resultado.next()) {
				lista.add(new TasaGrupo(resultado.getLong(1),resultado.getLong(2),resultado.getDouble(3),resultado.getString(4)));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static boolean modificar(Connection con,String db, Long id_bodegaEmpresa, Long id_grupo, Double tasa) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("update `"+db+"`.tasaGrupo set tasaArriendo = ? " +
							" WHERE id_bodegaEmpresa = ? and id_grupo = ?");
			smt.setDouble(1, tasa);
			smt.setLong(2, id_bodegaEmpresa);
			smt.setLong(3, id_grupo);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean eliminar(Connection con, String db, Long id_bodegaEmpresa, Long id_grupo) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("delete from `"+db+"`.tasaGrupo " +
							" WHERE id_bodegaEmpresa = ? and id_grupo = ?");
			smt.setLong(1, id_bodegaEmpresa);
			smt.setLong(2, id_grupo);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean agregar(Connection con, String db, Long id_bodegaEmpresa, Long id_grupo) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("insert into `"+db+"`.tasaGrupo (id_bodegaEmpresa,id_grupo,tasaArriendo) values (?,?,?);");		
			smt.setLong(1, id_bodegaEmpresa);
			smt.setLong(2, id_grupo);
			smt.setDouble(3, (double)0);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}
	
	public static Map<String,TasaGrupo> mapTasaPorBodegaGrupo(Connection con, String db) {
		Map<String,TasaGrupo> map = new HashMap<String,TasaGrupo>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select id_bodegaEmpresa, id_grupo, tasaArriendo from `"+db+"`.tasaGrupo;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				TasaGrupo tasaGrupo = new TasaGrupo(rs.getLong(1),rs.getLong(2),rs.getDouble(3),"");
				map.put(rs.getString(1)+"_"+rs.getString(2), tasaGrupo);
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (map);
	}
	


}
