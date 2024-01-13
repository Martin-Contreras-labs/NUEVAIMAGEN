package models.qr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class QrAtributoEquipo {

	public Long id;
	public String nombre;
	public Long id_qrTipoContenido;
	
	public String tipoContenido;
	public String observaciones;
	public String extencion;
	

	public QrAtributoEquipo(Long id, String nombre, Long id_qrTipoContenido, String tipoContenido, String observaciones, String extencion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.id_qrTipoContenido = id_qrTipoContenido;
		this.tipoContenido = tipoContenido;
		this.observaciones = observaciones;
		this.extencion = extencion;
	}



	public QrAtributoEquipo() {super();}

	
	
	public static List<QrAtributoEquipo> all(Connection con, String db) {
		List<QrAtributoEquipo> lista = new ArrayList<QrAtributoEquipo>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select "
							+ " qrAtributoEquipo.id, qrAtributoEquipo.campo, qrAtributoEquipo.id_qrTipoContenido, qrTipoContenido.tipo, qrTipoContenido.observaciones , qrTipoContenido.extencion "
							+ " from `"+db+"`.qrAtributoEquipo "
							+ " left join `"+db+"`.qrTipoContenido on qrTipoContenido.id = qrAtributoEquipo.id_qrTipoContenido "
							+ " order by campo;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				lista.add(new QrAtributoEquipo(rs.getLong(1),rs.getString(2),rs.getLong(3),rs.getString(4),rs.getString(5),rs.getString(6)));
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static QrAtributoEquipo find(Connection con, String db, Long idQrAtributoEquipo) {
		QrAtributoEquipo aux = new QrAtributoEquipo();
		try {
			PreparedStatement smt = con
					.prepareStatement("select qrAtributoEquipo.id,qrAtributoEquipo.campo,qrAtributoEquipo.id_qrTipoContenido,qrTipoContenido.tipo,qrTipoContenido.observaciones ,qrTipoContenido.extencion "
							+ " from `"+db+"`.qrAtributoEquipo left join `"+db+"`.qrTipoContenido on qrTipoContenido.id = qrAtributoEquipo.id_qrTipoContenido where qrAtributoEquipo.id=?;");
			smt.setLong(1, idQrAtributoEquipo);
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				aux = new QrAtributoEquipo(rs.getLong(1),rs.getString(2),rs.getLong(3),rs.getString(4),rs.getString(5),rs.getString(6));
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (aux);
	}
	
	public static Long bloquearTipo(Connection con, String db, Long idQrAtributoEquipo) {
		Long aux=(long)0;
		try {
			PreparedStatement smt = con
					.prepareStatement("select id_qrAtributoEquipo from `"+db+"`.qrTransacEquipo where id_qrAtributoEquipo=?;");
			smt.setLong(1, idQrAtributoEquipo);
			ResultSet rs = smt.executeQuery();
			if(rs.next()) {
				aux=(long)1;
			}else {
				PreparedStatement smt2 = con
						.prepareStatement("select id_qrAtributoEquipo from `"+db+"`.qrTransacEquipoHistorico where id_qrAtributoEquipo=?;");
				smt2.setLong(1, idQrAtributoEquipo);
				ResultSet rs2 = smt2.executeQuery();
				if(rs2.next()) {
					aux=(long)1;
				}
				rs2.close();smt2.close();
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(aux);
	}
	
	public static void update(Connection con, String db,String idTipo, String nombre, String idQrAtributoEquipo) {
		try {
			PreparedStatement smt = con
					.prepareStatement("update `"+db+"`.qrAtributoEquipo set campo=?,id_qrTipoContenido=? where id=?;");
			smt.setString(1, nombre.trim());
			smt.setString(2, idTipo.trim());
			smt.setString(3, idQrAtributoEquipo.trim());
			smt.executeUpdate();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean delete(Connection con, String db, Long idQrAtributoEquipo) {
		Long estaEnUso = QrAtributoEquipo.bloquearTipo(con,db,idQrAtributoEquipo);
		if(estaEnUso==1) return (false);
		try {
			PreparedStatement smt = con
					.prepareStatement("delete from `"+db+"`.qrAtributoEquipo where id=?;");
			smt.setLong(1, idQrAtributoEquipo);
			smt.executeUpdate();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(true);
	}
	
	public static void create(Connection con, String db,String idTipo, String nombre) {
		try {
			PreparedStatement smt = con
					.prepareStatement("insert into `"+db+"`.qrAtributoEquipo (campo,id_qrTipoContenido) values (?,?);");
			smt.setString(1, nombre.trim());
			smt.setString(2, idTipo.trim());
			smt.executeUpdate();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static List<QrAtributoEquipo> allFiltrado(Connection con, String db, List<QrTransacEquipo>  listaTransac) {
		List<QrAtributoEquipo> lista = new ArrayList<QrAtributoEquipo>();
		String filtro="";
		for(int i=0;i<listaTransac.size();i++) {
			filtro = filtro+","+listaTransac.get(i).id_qrAtributoEquipo.toString();
		}
		if(filtro.length()>1) {
			filtro = filtro.substring(1);
			filtro = "where qrAtributoEquipo.id not in ("+filtro+")";
		}
		try {
			PreparedStatement smt = con
					.prepareStatement("select qrAtributoEquipo.id,qrAtributoEquipo.campo,qrAtributoEquipo.id_qrTipoContenido,qrTipoContenido.tipo,qrTipoContenido.observaciones ,qrTipoContenido.extencion "
							+ " from `"+db+"`.qrAtributoEquipo left join `"+db+"`.qrTipoContenido on qrTipoContenido.id = qrAtributoEquipo.id_qrTipoContenido " + filtro 
							+ " order by campo;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				lista.add(new QrAtributoEquipo(rs.getLong(1),rs.getString(2),rs.getLong(3),rs.getString(4),rs.getString(5),rs.getString(6)));
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
