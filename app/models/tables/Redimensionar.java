package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;





public class Redimensionar {
	public Long id;
	public Long id_actaRedimensionar;
	public Long id_equipoOrigen;
	public Double cant_equipoOrigen;
	public Long id_equipoRedimensionar;
	public Double cantEquipoRedimensionar;
	public Long id_bodegaDestino;
	
	public Redimensionar(Long id, Long id_actaRedimensionar, Long id_equipoOrigen, Double cant_equipoOrigen,
			Long id_equipoRedimensionar, Double cantEquipoRedimensionar, Long id_bodegaDestino) {
		super();
		this.id = id;
		this.id_actaRedimensionar = id_actaRedimensionar;
		this.id_equipoOrigen = id_equipoOrigen;
		this.cant_equipoOrigen = cant_equipoOrigen;
		this.id_equipoRedimensionar = id_equipoRedimensionar;
		this.cantEquipoRedimensionar = cantEquipoRedimensionar;
		this.id_bodegaDestino = id_bodegaDestino;
	}
	
	public Redimensionar() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId_actaRedimensionar() {
		return id_actaRedimensionar;
	}

	public void setId_actaRedimensionar(Long id_actaRedimensionar) {
		this.id_actaRedimensionar = id_actaRedimensionar;
	}

	public Long getId_equipoOrigen() {
		return id_equipoOrigen;
	}

	public void setId_equipoOrigen(Long id_equipoOrigen) {
		this.id_equipoOrigen = id_equipoOrigen;
	}

	public Double getCant_equipoOrigen() {
		return cant_equipoOrigen;
	}

	public void setCant_equipoOrigen(Double cant_equipoOrigen) {
		this.cant_equipoOrigen = cant_equipoOrigen;
	}

	public Long getId_equipoRedimensionar() {
		return id_equipoRedimensionar;
	}

	public void setId_equipoRedimensionar(Long id_equipoRedimensionar) {
		this.id_equipoRedimensionar = id_equipoRedimensionar;
	}

	public Double getCantEquipoRedimensionar() {
		return cantEquipoRedimensionar;
	}

	public void setCantEquipoRedimensionar(Double cantEquipoRedimensionar) {
		this.cantEquipoRedimensionar = cantEquipoRedimensionar;
	}

	public Long getId_bodegaDestino() {
		return id_bodegaDestino;
	}

	public void setId_bodegaDestino(Long id_bodegaDestino) {
		this.id_bodegaDestino = id_bodegaDestino;
	}

	static SimpleDateFormat myformatfecha = new SimpleDateFormat("dd-MM-yyyy");
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatint = new DecimalFormat("#,##0");
	
	
	
	public static boolean create(Connection con, String db, String detalle) {
		boolean flag = false;
		try {
			PreparedStatement smt1 = con
					.prepareStatement("INSERT INTO `"+db+"`.redimensionar "
							+ " (id_actaRedimensionar, id_equipoOrigen, cantEquipoOrigen, id_equipoRedimensionar, cantEquipoRedimensionar, id_bodegaDestino)"
							+ " VALUES "+detalle+";");
			smt1.executeUpdate();
			smt1.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static Map<Long,Double> redimensionarPorIdEquipoPendientes(Connection con, String db){
		Map<Long,Double> aux = new HashMap<Long,Double>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select id_equipoOrigen, sum(cantEquipoOrigen)"
							+ " from `"+db+"`.redimensionar "
							+ " left join `"+db+"`.actaRedimensionar on actaRedimensionar.id = redimensionar.id_actaRedimensionar"
							+ " where fechaConfirma is null"
							+ " group by id_equipoOrigen;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				aux.put(rs.getLong(1), rs.getDouble(2) / 2);
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (aux);
	}
	
	public static List<Redimensionar> allPorIdActa(Connection con, String db, Long id_actaRedimensionar){
		List<Redimensionar> lista = new ArrayList<Redimensionar>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select"
							+ " id,"
							+ " id_actaRedimensionar,"
							+ " id_equipoOrigen,"
							+ " cantEquipoOrigen,"
							+ " id_equipoRedimensionar,"
							+ " cantEquipoRedimensionar,"
							+ " id_bodegaDestino"
							+ " from `"+db+"`.redimensionar "
							+ " where id_actaRedimensionar = ?;");
			smt.setLong(1, id_actaRedimensionar);
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				lista.add(new Redimensionar(rs.getLong(1), rs.getLong(2), rs.getLong(3), rs.getDouble(4), rs.getLong(5), rs.getDouble(6), rs.getLong(7)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(lista);
	}
	
	
	
	
	
	
	
}
