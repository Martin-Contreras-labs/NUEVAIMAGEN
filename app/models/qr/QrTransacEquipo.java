
package models.qr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class QrTransacEquipo{

	public Long orden;
	public Long id_equipo;
	public Long id_qrAtributoEquipo;
	public String contenido;
	
	public String campo;
	public Long id_qrTipoContenido;
	public String tipo;
	public String extencion;
	
	public Long activo;
	

	public QrTransacEquipo(Long orden, Long id_equipo, Long id_qrAtributoEquipo, String contenido, String campo,
			Long id_qrTipoContenido, String tipo, String extencion, Long activo) {
		super();
		this.orden = orden;
		this.id_equipo = id_equipo;
		this.id_qrAtributoEquipo = id_qrAtributoEquipo;
		this.contenido = contenido;
		this.campo = campo;
		this.id_qrTipoContenido = id_qrTipoContenido;
		this.tipo = tipo;
		this.extencion = extencion;
		this.activo = activo;
	}

	public QrTransacEquipo() {super();}

	public static List<QrTransacEquipo> allPorIdEquipo(Connection con, String db, Long idEquipo) {
		List<QrTransacEquipo> lista = new ArrayList<QrTransacEquipo>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select " + 
							" qrTransacEquipo.orden, " + 
							" qrTransacEquipo.id_equipo, " + 
							" qrTransacEquipo.id_qrAtributoEquipo, " + 
							" ifnull(qrTransacEquipo.contenido,0), " + 
							" qrAtributoEquipo.campo, " + 
							" qrAtributoEquipo.id_qrTipoContenido, " + 
							" qrTipoContenido.tipo, " + 
							" qrTipoContenido.extencion, " + 
							" qrTransacEquipo.activo " +
							" from `"+db+"`.qrTransacEquipo " + 
							" left join `"+db+"`.qrAtributoEquipo on qrAtributoEquipo.id = qrTransacEquipo.id_qrAtributoEquipo " + 
							" left join `"+db+"`.qrTipoContenido on qrTipoContenido.id = qrAtributoEquipo.id_qrTipoContenido " + 
							" where qrTransacEquipo.id_equipo =? order by qrTransacEquipo.orden;");
			smt.setLong(1, idEquipo);
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				lista.add(new QrTransacEquipo(rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getString(4),
						rs.getString(5),rs.getLong(6),rs.getString(7),rs.getString(8),rs.getLong(9)));
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<QrTransacEquipo> allPorIdEquipoSoloActivos(Connection con, String db, Long idEquipo) {
		List<QrTransacEquipo> lista = new ArrayList<QrTransacEquipo>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select " + 
							" qrTransacEquipo.orden, " + 
							" qrTransacEquipo.id_equipo, " + 
							" qrTransacEquipo.id_qrAtributoEquipo, " + 
							" ifnull(qrTransacEquipo.contenido,0), " + 
							" qrAtributoEquipo.campo, " + 
							" qrAtributoEquipo.id_qrTipoContenido, " + 
							" qrTipoContenido.tipo, " + 
							" qrTipoContenido.extencion, " + 
							" qrTransacEquipo.activo " +
							" from `"+db+"`.qrTransacEquipo " + 
							" left join `"+db+"`.qrAtributoEquipo on qrAtributoEquipo.id = qrTransacEquipo.id_qrAtributoEquipo " + 
							" left join `"+db+"`.qrTipoContenido on qrTipoContenido.id = qrAtributoEquipo.id_qrTipoContenido " + 
							" where qrTransacEquipo.id_equipo =? and qrTransacEquipo.activo=1 order by qrTransacEquipo.orden;");
			smt.setLong(1, idEquipo);
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				lista.add(new QrTransacEquipo(rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getString(4),
						rs.getString(5),rs.getLong(6),rs.getString(7),rs.getString(8),rs.getLong(9)));
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static boolean updatePorCampo(Connection con, String db, String idEquipo, String idCampo, String campoMySql, String valor) {
		boolean flag=false;
		try {
			PreparedStatement smt = con
					.prepareStatement("update `"+db+"`.qrTransacEquipo set `"+campoMySql.trim()+"` = '"+valor.trim()+"' where id_equipo=? and id_qrAtributoEquipo=?;");
			smt.setString(1, idEquipo.trim());
			smt.setString(2, idCampo.trim());
			smt.executeUpdate();
			smt.close();
			flag=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static QrTransacEquipo find(Connection con, String db, String idEquipo, String idCampo) {
		QrTransacEquipo aux = new QrTransacEquipo();
		try {
			PreparedStatement smt = con
					.prepareStatement("select " + 
							" qrTransacEquipo.orden, " + 
							" qrTransacEquipo.id_equipo, " + 
							" qrTransacEquipo.id_qrAtributoEquipo, " + 
							" ifnull(qrTransacEquipo.contenido,0), " + 
							" qrAtributoEquipo.campo, " + 
							" qrAtributoEquipo.id_qrTipoContenido, " + 
							" qrTipoContenido.tipo, " + 
							" qrTipoContenido.extencion, " + 
							" qrTransacEquipo.activo " +
							" from `"+db+"`.qrTransacEquipo " + 
							" left join `"+db+"`.qrAtributoEquipo on qrAtributoEquipo.id = qrTransacEquipo.id_qrAtributoEquipo " + 
							" left join `"+db+"`.qrTipoContenido on qrTipoContenido.id = qrAtributoEquipo.id_qrTipoContenido " + 
							" where qrTransacEquipo.id_equipo =? and qrTransacEquipo.id_qrAtributoEquipo=?;");
			smt.setString(1, idEquipo.trim());
			smt.setString(2, idCampo.trim());
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				aux = new QrTransacEquipo(rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getString(4),
						rs.getString(5),rs.getLong(6),rs.getString(7),rs.getString(8),rs.getLong(9));
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (aux);
	}
	
	public static boolean eliminaCampo(Connection con, String db, QrTransacEquipo qrTransacEquipo) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("delete from `"+db+"`.qrTransacEquipo where id_equipo=? and id_qrAtributoEquipo=?;");
			smt.setLong(1, qrTransacEquipo.id_equipo);
			smt.setLong(2, qrTransacEquipo.id_qrAtributoEquipo);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean create(Connection con, String db, String idEquipo, String idCampo) {
		boolean flag=false;
		try {
			Long orden=(long)1;
			PreparedStatement smt2 = con.prepareStatement("select max(orden) from `"+db+"`.qrTransacEquipo;");
			ResultSet rs2 = smt2.executeQuery();
			if(rs2.next()) {
				orden=rs2.getLong(1)+1;
			}
			rs2.close();smt2.close();
			PreparedStatement smt = con
					.prepareStatement("insert into `"+db+"`.qrTransacEquipo (id_equipo,id_qrAtributoEquipo,orden) values (?,?,?);");
			smt.setString(1, idEquipo);
			smt.setString(2, idCampo);
			smt.setString(3, orden.toString());
			smt.executeUpdate();
			smt.close();
			flag=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	
	
	
	
	
	
	

}
