package models.tables;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UsuarioPermiso {
	
	
	
	public static Map<String,String> mapPermisos(Connection con, String db, String permiso){
		Map<String,String> map = new HashMap<String, String>();
		if(permiso==null) return (map);
		try {
			PreparedStatement smt = con
					.prepareStatement(" select vista,id_tipoUsuario from `"+db+"`.permisoPorTipo " +
							" left join `"+db+"`.vista on vista.id = id_vista " +
							" where id_tipoUsuario=?;");
			smt.setString(1, permiso.trim());
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				map.put(rs.getString(1), rs.getString(2));
			}
			rs.close();
			smt.close();
			
			PreparedStatement smt2 = con
					.prepareStatement(" select nombre, valor from `"+db+"`.parametros;");
			ResultSet rs2 = smt2.executeQuery();
			while (rs2.next()) {
					map.put("parametro."+rs2.getString(1), rs2.getString(2));
			}
			rs2.close();
			smt2.close();
			
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		
		return(map);
		
	}
	
	public static String permisoBodegaEmpresa(Connection con, String db, Long idUsuario) {
		String listIdBodegas = "";
		Usuario usuario = Usuario.findXIdUser(con, db, idUsuario);
		if(!UsuarioTipo.esPorProyecto(con, db, usuario.id_tipoUsuario)) {
			return("");
		}else {
			try {
				PreparedStatement smt = con
						.prepareStatement(" select id_bodegaEmpresa " +
								" from `"+db+"`.permisoPorBodegaEmpresa where id_usuario=?;");
				smt.setLong(1, idUsuario);
				ResultSet resultado = smt.executeQuery();
				String aux = " and (";
				while (resultado.next()) {
					listIdBodegas = listIdBodegas + aux + " `movimiento`.`id_bodegaEmpresa`='"+resultado.getLong(1)+"'";
					aux = " or ";
				}
				if(listIdBodegas.length()>0) {
					listIdBodegas = listIdBodegas + ")";
				}else {
					listIdBodegas = " and (`movimiento`.`id_bodegaEmpresa`='0')";
				}
				resultado.close();
				smt.close();
			} catch (SQLException e) {
	    			e.printStackTrace();
			}
			return (listIdBodegas);
		}
	}
	
	public static Long permisoSoloABodega(Connection con, String db, String idUsuario) {
		Long permisoSoloABodega = (long)0;
			try {
				PreparedStatement smt = con
						.prepareStatement(" select id_bodegaEmpresa " +
								" from `"+db+"`.permisoPorBodegaEmpresa where id_usuario=?;");
				smt.setString(1, idUsuario);
				ResultSet resultado = smt.executeQuery();
				while (resultado.next()) {
					permisoSoloABodega = resultado.getLong(1);
				}
				resultado.close();
				smt.close();
			} catch (SQLException e) {
	    			e.printStackTrace();
			}
			return (permisoSoloABodega);
	}
	
	public static Map<String,String> mapPermisoIdBodega(Connection con, String db, Long idUsuario) {
		Map<String,String> map = new HashMap<String,String>();
		Usuario usuario = Usuario.findXIdUser(con, db, idUsuario);
		if(UsuarioTipo.esPorProyecto(con, db, usuario.id_tipoUsuario)) {
			try {
				PreparedStatement smt = con
						.prepareStatement(" select id_bodegaEmpresa " +
								" from `"+db+"`.permisoPorBodegaEmpresa where id_usuario=?;");
				smt.setLong(1, idUsuario);
				ResultSet resultado = smt.executeQuery();
				while (resultado.next()) {
					map.put(resultado.getString(1),resultado.getString(1));
				}
				resultado.close();
				smt.close();
			} catch (SQLException e) {
	    			e.printStackTrace();
			}
			return (map);
		}
		return (map);
	}
	
	public static List<List<String>> listaPermisoBodPorUsuario(Connection con, String db,Long id_usuario) {
		List<List<String>> lista = new ArrayList<List<String>>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select bodegaEmpresa.id,tipoBodega.nombre,bodegaEmpresa.nombre " +
							" from `"+db+"`.permisoPorBodegaEmpresa " +
							" left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = id_bodegaEmpresa " +
							" left join `"+db+"`.tipoBodega on tipoBodega.id = bodegaEmpresa.esInterna " +
							" where id_usuario=?;");
			smt.setLong(1, id_usuario);
			ResultSet resultado = smt.executeQuery();
			while (resultado.next()) {
				List<String> aux = new ArrayList<String>();
				aux.add(resultado.getString(1));  //0 id_bodegaEmpresa
				aux.add(resultado.getString(2));
				aux.add(resultado.getString(3));
				lista.add(aux);
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static boolean asignaPermisoPorBodega(Connection con,String db,Long id_usuario,Long id_bodegaEmpresa) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement(" insert into `"+db+"`.permisoPorBodegaEmpresa (id_usuario,id_bodegaEmpresa)" +
							" values (?,?);");
			smt.setLong(1, id_usuario);
			smt.setLong(2, id_bodegaEmpresa);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean eliminaPermisoPorBodega(Connection con,String db,Long id_usuario,Long id_bodegaEmpresa) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement(" delete from `"+db+"`.permisoPorBodegaEmpresa where " +
							" id_usuario=? and id_bodegaEmpresa=?;");
			smt.setLong(1, id_usuario);
			smt.setLong(2, id_bodegaEmpresa);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}
	
	


	
}
