package models.tables;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UsuarioTipo {
	public Long id;
	public String tipo;
	public String descripcion;

	public UsuarioTipo(Long id, String tipo,String descripcion) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.descripcion = descripcion;
	}

	public UsuarioTipo() {super();}

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getTipo() {return tipo;}
	public void setTipo(String tipo) {this.tipo = tipo;}
	public String getDescripcion() {return descripcion;}
	public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
	
	public static boolean esPorProyecto(Connection con, String db, Long idTipoUsuario) {
		boolean flag=true;
		try {
			PreparedStatement smt = con
					.prepareStatement("SELECT porProyecto " +
							" FROM `"+db+"`.tipoUsuario WHERE id = ?" );
			smt.setLong(1, idTipoUsuario);
			ResultSet resultado = smt.executeQuery();
			if (resultado.next()) {
				if(resultado.getLong(1)==0) {
					flag=false;
				};
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}

	public static List<UsuarioTipo> all(Connection con, String db) {
		List<UsuarioTipo> lista = new ArrayList<UsuarioTipo>();
		try {
			PreparedStatement smt = con
					.prepareStatement("SELECT id,tipo,descripcion FROM `"+db+"`.tipoUsuario");
			ResultSet resultado = smt.executeQuery();
			while (resultado.next()) {
				lista.add(new UsuarioTipo(resultado.getLong(1),resultado.getString(2),resultado.getString(3)));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (lista);
	}
	

}
