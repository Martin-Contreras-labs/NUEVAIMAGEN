package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class DctoGrupo{
	public Long id_bodegaEmpresa;
	public Long id_grupo;
	public Double tasaDescto;
	public String nombreGrupo;

	public DctoGrupo(Long id_bodegaEmpresa, Long id_grupo, Double tasaDescto,
			String nombreGrupo) {
		super();
		this.id_bodegaEmpresa = id_bodegaEmpresa;
		this.id_grupo = id_grupo;
		this.tasaDescto = tasaDescto;
		this.nombreGrupo = nombreGrupo;
	}

	public DctoGrupo() {super();}

	public Long getId_bodegaEmpresa() {return id_bodegaEmpresa;}
	public void setId_bodegaEmpresa(Long id_bodegaEmpresa) {this.id_bodegaEmpresa = id_bodegaEmpresa;}
	public Long getId_grupo() {return id_grupo;}
	public void setId_grupo(Long id_grupo) {this.id_grupo = id_grupo;}
	public Double getTasaDescto() {return tasaDescto;}
	public void setTasaDescto(Double tasaDescto) {this.tasaDescto = tasaDescto;}
	public String getNombreGrupo() {return nombreGrupo;}
	public void setNombreGrupo(String nombreGrupo) {this.nombreGrupo = nombreGrupo;}
	
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");
	
	
	
	public static List<DctoGrupo> allXBodegaEmpresa(Connection con, String db, Long id_bodegaEmpresa) {
		List<DctoGrupo> lista = new ArrayList<DctoGrupo>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select id_bodegaEmpresa,id_grupo,tasaDescto,nombre " +
							" from `"+db+"`.dctoGrupo  " +
							" left join `"+db+"`.grupo on grupo.id = dctoGrupo.id_grupo " +
							" where id_bodegaEmpresa = ? "+
							" order by grupo.nombre;");
			smt.setLong(1, id_bodegaEmpresa);
			ResultSet resultado = smt.executeQuery();
			while (resultado.next()) {
				lista.add(new DctoGrupo(resultado.getLong(1),resultado.getLong(2),resultado.getDouble(3),resultado.getString(4)));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static boolean modificar(Connection con, String db, Long id_bodegaEmpresa, Long id_grupo, Double tasaDcto) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("update `"+db+"`.dctoGrupo set tasaDescto = ? " +
							" WHERE id_bodegaEmpresa = ? and id_grupo = ?");
			smt.setDouble(1, tasaDcto);
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
					.prepareStatement("delete from `"+db+"`.dctoGrupo " +
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
					.prepareStatement("insert into `"+db+"`.dctoGrupo (id_bodegaEmpresa,id_grupo,tasaDescto) values (?,?,?);");		
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
	
	


}
