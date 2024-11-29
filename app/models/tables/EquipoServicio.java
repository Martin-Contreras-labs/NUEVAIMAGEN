package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class EquipoServicio {
	public Long id_bodegaEmpresa;
	public Long id_equipo;
	public Long vigente;
	
	public String bodega;
	public String grupo;
	public String codigo;
	public String equipo;
	
	public String nameSucursal;
	
	
	public EquipoServicio(Long id_bodegaEmpresa, Long id_equipo, Long vigente, String bodega, String grupo, String codigo,
			String equipo, String nameSucursal) {
		super();
		this.id_bodegaEmpresa = id_bodegaEmpresa;
		this.id_equipo = id_equipo;
		this.vigente = vigente;
		this.bodega = bodega;
		this.grupo = grupo;
		this.codigo = codigo;
		this.equipo = equipo;
		this.nameSucursal = nameSucursal;
	}

	public EquipoServicio() {super();}
	
	public Long getId_bodegaEmpresa() {
		return id_bodegaEmpresa;
	}

	public void setId_bodegaEmpresa(Long id_bodegaEmpresa) {
		this.id_bodegaEmpresa = id_bodegaEmpresa;
	}

	public Long getId_equipo() {
		return id_equipo;
	}

	public void setId_equipo(Long id_equipo) {
		this.id_equipo = id_equipo;
	}

	public Long getVigente() {
		return vigente;
	}

	public void setVigente(Long vigente) {
		this.vigente = vigente;
	}

	public String getBodega() {
		return bodega;
	}

	public void setBodega(String bodega) {
		this.bodega = bodega;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getEquipo() {
		return equipo;
	}

	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}
	
	public String getNameSucursal() {
		return nameSucursal;
	}

	public void setNameSucursal(String nameSucursal) {
		this.nameSucursal = nameSucursal;
	}

	public static List<List<String>> listaBodegasConEquipos(Connection con, String db, String esPorSucursal, String id_sucursal) {
		List<List<String>> lista = new ArrayList<List<String>>();
		
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " and bodegaEmpresa.id_sucursal = " + id_sucursal;
		}
		
		try {
			PreparedStatement smt = con
					.prepareStatement("select "
							+ " equipoServicio.id_bodegaEmpresa, "
							+ " bodegaEmpresa.nombre, "
							+ " bodegaEmpresa.id_sucursal, "
							+ " ifnull(cliente.vigente,1) "
							+ " from `"+db+"`.equipoServicio "
							+ " left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = equipoServicio.id_bodegaEmpresa "
							+ " left join `"+db+"`.cliente on cliente.id = bodegaEmpresa.id_cliente "
							+ " where equipoServicio.vigente = 1 and bodegaEmpresa.vigente = 1 " + condSucursal
							+ " group by equipoServicio.id_bodegaEmpresa, bodegaEmpresa.nombre "
							+ " order by bodegaEmpresa.nombre;");
			ResultSet rs = smt.executeQuery();
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			while (rs.next()) {
				String nameSucursal = "";
				Sucursal sucursal  = mapSucursal.get(rs.getLong(3));
				if(sucursal!=null) {
					nameSucursal = sucursal.getNombre();
				}
				List<String> aux = new ArrayList<String>();
				aux.add(rs.getString(1));
				aux.add(rs.getString(2));
				aux.add(nameSucursal);
				aux.add(rs.getString(4));
				lista.add(aux);
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
    		e.printStackTrace();
		}
		return(lista);
	}
	
	public static List<List<String>> allEquiposVigXBod(Connection con, String db, Long id_bodegaEmpresa){
		List<List<String>> lista = new ArrayList<List<String>>();
		List<EquipoServicio> auxLista = EquipoServicio.allInscritosPorBod(con, db, id_bodegaEmpresa);
		Map<Long,String> mapLectura = VentaServicio.mapUltimaLecturaEquipo(con, db);
		auxLista.forEach(x->{
			if((long)x.vigente == (long)1) {
				String lectura = mapLectura.get(x.getId_equipo());
				if(lectura == null) {
					lectura = "0.00";
				}
				List<String> aux = new ArrayList<String>();
				aux.add(x.getId_equipo().toString());
				aux.add(x.getCodigo());
				aux.add(x.getEquipo());
				aux.add(lectura);
				lista.add(aux);
			}
		});
		return(lista);
	}
	
	public static Map<Long,List<List<String>>> mapEquiposVigentes(Connection con, String db, String esPorSucursal, String id_sucursal){
		Map<Long,List<List<String>>> map =new HashMap<Long,List<List<String>>>();
		List<EquipoServicio> auxLista = EquipoServicio.allInscritos(con, db, esPorSucursal, id_sucursal);
		auxLista.forEach(x->{
			
			if((long)x.vigente == (long)1) {
				List<String> aux = new ArrayList<String>();
				aux.add(x.getId_equipo().toString());
				aux.add(x.getCodigo());
				aux.add(x.getEquipo());
				
				List<List<String>> listMap = map.get(x.getId_bodegaEmpresa());
				if(listMap == null) {
					List<List<String>> auxlist = new ArrayList<List<String>>();
					auxlist.add(aux);
					map.put(x.getId_bodegaEmpresa(), auxlist);
				}else {
					listMap.add(aux);
					map.put(x.getId_bodegaEmpresa(), listMap);
				}
			}
		});
		return(map);
	}
	
	public static List<EquipoServicio> allInscritos(Connection con, String db, String esPorSucursal, String id_sucursal) {
		List<EquipoServicio> lista = new ArrayList<EquipoServicio>();
		
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " where bodegaEmpresa.id_sucursal = " + id_sucursal;
		}
		
		try {
			PreparedStatement smt = con
					.prepareStatement("select "
							+ " equipoServicio.id_bodegaEmpresa, "
							+ " equipoServicio.id_equipo, "
							+ " equipoServicio.vigente, "
							+ " bodegaEmpresa.nombre, "
							+ " grupo.nombre, "
							+ " equipo.codigo, "
							+ " equipo.nombre, "
							+ " bodegaEmpresa.id_sucursal "
							+ " from `"+db+"`.equipoServicio "
							+ " left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = equipoServicio.id_bodegaEmpresa "
							+ " left join `"+db+"`.equipo on equipo.id = equipoServicio.id_equipo "
							+ " left join `"+db+"`.grupo on grupo.id = equipo.id_grupo "
							+ condSucursal
							+ " order by bodegaEmpresa.nombre, equipo.nombre;");
			ResultSet rs = smt.executeQuery();
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			while (rs.next()) {
				String nameSucursal = "";
				Sucursal sucursal = mapSucursal.get(rs.getLong(8));
				if(sucursal!=null) {
					nameSucursal = sucursal.getNombre();
				}
				lista.add(new EquipoServicio(
						rs.getLong(1), 
						rs.getLong(2),
						rs.getLong(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						nameSucursal));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
    		e.printStackTrace();
		}
		return (lista);
	}
	
	public static boolean modifyVigencia(Connection con, String db, Long id_bodegaEmpresa, Long id_equipo, Long valor) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("update `"+db+"`.equipoServicio set vigente=? "
							+ " where id_bodegaEmpresa=? and id_equipo=?;");
			smt.setLong(1, valor);
			smt.setLong(2, id_bodegaEmpresa);
			smt.setLong(3, id_equipo);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
    		e.printStackTrace();
		}
		return (flag);
	}
	
	public static List<EquipoServicio> allInscritosPorBod(Connection con, String db, Long id_bodegaEmpresa) {
		List<EquipoServicio> lista = new ArrayList<EquipoServicio>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select "
							+ " equipoServicio.id_bodegaEmpresa, "
							+ " equipoServicio.id_equipo, "
							+ " equipoServicio.vigente, "
							+ " bodegaEmpresa.nombre, "
							+ " grupo.nombre, "
							+ " equipo.codigo, "
							+ " equipo.nombre, "
							+ " bodegaEmpresa.id_sucursal "
							+ " from `"+db+"`.equipoServicio "
							+ " left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = equipoServicio.id_bodegaEmpresa "
							+ " left join `"+db+"`.equipo on equipo.id = equipoServicio.id_equipo "
							+ " left join `"+db+"`.grupo on grupo.id = equipo.id_grupo "
							+ " where equipoServicio.id_bodegaEmpresa = ? "
							+ " order by bodegaEmpresa.nombre, equipo.nombre;");
			smt.setLong(1, id_bodegaEmpresa);
			ResultSet rs = smt.executeQuery();
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			while (rs.next()) {
				String nameSucursal = "";
				Sucursal sucursal = mapSucursal.get(rs.getLong(8));
				if(sucursal!=null) {
					nameSucursal = sucursal.getNombre();
				}
				lista.add(new EquipoServicio(
						rs.getLong(1), 
						rs.getLong(2),
						rs.getLong(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						nameSucursal));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
    		e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<EquipoServicio> allNoInscritosPorBod(Connection con, String db, BodegaEmpresa bodega) {
		List<EquipoServicio> lista = new ArrayList<EquipoServicio>();
		List<Long> listAux = new ArrayList<Long>();
		String cond = "";
		try {
			PreparedStatement smt1 = con
					.prepareStatement("select "
							+ " equipoServicio.id_equipo from `"+db+"`.equipoServicio where id_bodegaEmpresa = ? ");
			smt1.setLong(1, bodega.getId());
			ResultSet rs1 = smt1.executeQuery();
			while (rs1.next()) {
				listAux.add(rs1.getLong(1));
			}
			if(listAux.size()>0) {
				cond = " and equipo.id not in "+listAux.toString().replace("[", "(").replace("]", ")");
			}
			
			PreparedStatement smt = con
					.prepareStatement("select "
							+ " equipo.id, "
							+ " grupo.nombre, "
							+ " equipo.codigo, "
							+ " equipo.nombre "
							+ " from `"+db+"`.equipo "
							+ " left join `"+db+"`.grupo on grupo.id = equipo.id_grupo where equipo.vigente=1 "+cond
							+ " order by equipo.nombre;");
			ResultSet rs = smt.executeQuery();
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			while (rs.next()) {
				String nameSucursal = "";
				Sucursal sucursal = mapSucursal.get(bodega.getId_sucursal());
				if(sucursal!=null) {
					nameSucursal = sucursal.getNombre();
				}
				lista.add(new EquipoServicio(
						bodega.getId(), 
						rs.getLong(1),
						(long)1,
						bodega.getNombre(),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						nameSucursal));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
    		e.printStackTrace();
		}
		return (lista);
	}
	
	public static boolean insert(Connection con, String db, Long id_bodegaEmpresa, Long id_equipo) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("select id_equipo from `"+db+"`.equipoServicio where id_bodegaEmpresa=? and id_equipo=?;");
			smt.setLong(1, id_bodegaEmpresa);
			smt.setLong(2, id_equipo);
			ResultSet rs = smt.executeQuery();
			if(!rs.next()) {
				PreparedStatement smt2 = con
						.prepareStatement("insert into `"+db+"`.equipoServicio (id_bodegaEmpresa, id_equipo, vigente) "
								+ " values (?,?,?);");
				smt2.setLong(1, id_bodegaEmpresa);
				smt2.setLong(2, id_equipo);
				smt2.setLong(3, (long)1);
				smt2.executeUpdate();
				smt2.close();
			}
			smt.close();
			flag = true;
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}
	
	
	
	


}
