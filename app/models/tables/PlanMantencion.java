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

import controllers.HomeController;
import models.calculo.Inventarios;
import models.utilities.Fechas;


public class PlanMantencion {
	public Long id_equipo;
	public String fechaReset;
	public Long id_unidadMantencion;
	public String cadaNEstimado;
	public String consumoEstimadoPorMes;
	public String estadoActual;
	public String proximaMantencion;
	
	public String unidadMantencion;
	public String consumoEstimadoAHoy;
	public String equipoGrupo;
	public String equipoCodigo;
	public String equipoNombre;
	
	public Long id_tipoPlan;
	public String tipoPlanNombre;
	
	public String diferencia;

	

	public PlanMantencion(Long id_equipo, String fechaReset, Long id_unidadMantencion, String cadaNEstimado,
			String consumoEstimadoPorMes, String estadoActual, String proximaMantencion, String unidadMantencion,
			String consumoEstimadoAHoy, String equipoGrupo, String equipoCodigo, String equipoNombre, Long id_tipoPlan,
			String tipoPlanNombre, String diferencia) {
		super();
		this.id_equipo = id_equipo;
		this.fechaReset = fechaReset;
		this.id_unidadMantencion = id_unidadMantencion;
		this.cadaNEstimado = cadaNEstimado;
		this.consumoEstimadoPorMes = consumoEstimadoPorMes;
		this.estadoActual = estadoActual;
		this.proximaMantencion = proximaMantencion;
		this.unidadMantencion = unidadMantencion;
		this.consumoEstimadoAHoy = consumoEstimadoAHoy;
		this.equipoGrupo = equipoGrupo;
		this.equipoCodigo = equipoCodigo;
		this.equipoNombre = equipoNombre;
		this.id_tipoPlan = id_tipoPlan;
		this.tipoPlanNombre = tipoPlanNombre;
		this.diferencia = diferencia;
	}

	public PlanMantencion() {
		super();
	}
	
	public Long getId_equipo() {return id_equipo;}
	public void setId_equipo(Long id_equipo) {this.id_equipo = id_equipo;}
	public String getFechaReset() {return fechaReset;}
	public void setFechaReset(String fechaReset) {this.fechaReset = fechaReset;}
	public String getCadaNEstimado() {return cadaNEstimado;}
	public void setCadaNEstimado(String cadaNEstimado) {this.cadaNEstimado = cadaNEstimado;}
	public String getConsumoEstimadoPorMes() {return consumoEstimadoPorMes;}
	public void setConsumoEstimadoPorMes(String consumoEstimadoPorMes) {this.consumoEstimadoPorMes = consumoEstimadoPorMes;}
	public String getEstadoActual() {return estadoActual;}
	public void setEstadoActual(String estadoActual) {this.estadoActual = estadoActual;}
	public String getProximaMantencion() {return proximaMantencion;}
	public void setProximaMantencion(String proximaMantencion) {this.proximaMantencion = proximaMantencion;}
	public Long getId_unidadMantencion() {return id_unidadMantencion;}
	public void setId_unidadMantencion(Long id_unidadMantencion) {this.id_unidadMantencion = id_unidadMantencion;}
	public String getUnidadMantencion() {return unidadMantencion;}
	public void setUnidadMantencion(String unidadMantencion) {this.unidadMantencion = unidadMantencion;}
	public String getEquipoGrupo() {return equipoGrupo;}
	public void setEquipoGrupo(String equipoGrupo) {this.equipoGrupo = equipoGrupo;}
	public String getEquipoCodigo() {return equipoCodigo;}
	public void setEquipoCodigo(String equipoCodigo) {this.equipoCodigo = equipoCodigo;}
	public String getEquipoNombre() {return equipoNombre;}
	public void setEquipoNombre(String equipoNombre) {this.equipoNombre = equipoNombre;}
	public String getConsumoEstimadoAHoy() {return consumoEstimadoAHoy;}
	public void setConsumoEstimadoAHoy(String consumoEstimadoAHoy) {this.consumoEstimadoAHoy = consumoEstimadoAHoy;}
	public Long getId_tipoPlan() {return id_tipoPlan;}
	public void setId_tipoPlan(Long id_tipoPlan) {this.id_tipoPlan = id_tipoPlan;}
	public String getTipoPlanNombre() {return tipoPlanNombre;}
	public void setTipoPlanNombre(String tipoPlanNombre) {this.tipoPlanNombre = tipoPlanNombre;}
	public String getDiferencia() {return diferencia;}
	public void setDiferencia(String diferencia) {this.diferencia = diferencia;}




	static SimpleDateFormat myformatfecha = new SimpleDateFormat("dd-MM-yyyy");
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	
	public static PlanMantencion find(Connection con, String db, Long id_equipo, Long id_tipoPlan) {
		PlanMantencion aux = new PlanMantencion();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select "
							+ " planMantencion.id_equipo, "
							+ " planMantencion.fechaReset, "
							+ " planMantencion.id_unidadMantencion, "
							+ " planMantencion.cadaNEstimado, "
							+ " planMantencion.consumoEstimadoPorMes, "
							+ " planMantencion.estadoActual, "
							+ " planMantencion.proximaMantencion, "
							+ " unidadMantencion.nombre, "
							+ " grupo.nombre, "
							+ " equipo.codigo, "
							+ " equipo.nombre, "
							+ " planMantencion.id_tipoPlan, "
							+ " tipoPlan.nombre "
							+ " from `"+db+"`.planMantencion "
							+ " left join `"+db+"`.tipoPlan on tipoPlan.id = planMantencion.id_tipoPlan "
							+ " left join `"+db+"`.unidadMantencion on unidadMantencion.id = id_unidadMantencion "
							+ " left join `"+db+"`.equipo on equipo.id = id_equipo "
							+ " left join `"+db+"`.grupo on grupo.id = equipo.id_grupo "
							+ " where equipo.id=? and planMantencion.id_tipoPlan=?;");
			smt.setLong(1, id_equipo);
			smt.setLong(2, id_tipoPlan);
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				Double proximaMantencion = rs.getDouble(7);
				Double estimado = (double)0;
					java.util.Date hoy = new java.util.Date();
					Long deltaDias = Math.round((double) ( hoy.getTime()-rs.getDate(2).getTime())/(24 * 60 * 60 * 1000));
				estimado = rs.getDouble(6) + rs.getDouble(5)* deltaDias/30;
				String fecha = null;
				if (rs.getDate(2) != null) {
					fecha = myformatfecha.format(rs.getDate(2));
				}
				
				Double difEstimMenosProxMant = proximaMantencion - estimado;
				
				aux = new PlanMantencion(
						rs.getLong(1),
						fecha,
						rs.getLong(3),
					myformatdouble2.format(rs.getDouble(4)),
					myformatdouble2.format(rs.getDouble(5)),
					myformatdouble2.format(rs.getDouble(6)),
					myformatdouble2.format(proximaMantencion),
					rs.getString(8),
					myformatdouble2.format(estimado),
					rs.getString(9),rs.getString(10),rs.getString(11),rs.getLong(12),rs.getString(13),
					myformatdouble2.format(difEstimMenosProxMant));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
		}
		return (aux);
	}
	
	public static PlanMantencion findPorCodigo(Connection con, String db, String codigo) {
		PlanMantencion aux = new PlanMantencion();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select "
							+ " planMantencion.id_equipo, "
							+ " planMantencion.fechaReset, "
							+ " planMantencion.id_unidadMantencion, "
							+ " planMantencion.cadaNEstimado, "
							+ " planMantencion.consumoEstimadoPorMes, "
							+ " planMantencion.estadoActual, "
							+ " planMantencion.proximaMantencion, "
							+ " unidadMantencion.nombre, "
							+ " grupo.nombre, "
							+ " equipo.codigo, "
							+ " equipo.nombre, "
							+ " planMantencion.id_tipoPlan, "
							+ " tipoPlan.nombre "
							+ " from `"+db+"`.planMantencion "
							+ " left join `"+db+"`.tipoPlan on tipoPlan.id = planMantencion.id_tipoPlan "
							+ " left join `"+db+"`.unidadMantencion on unidadMantencion.id = id_unidadMantencion "
							+ " left join `"+db+"`.equipo on equipo.id = id_equipo "
							+ " left join `"+db+"`.grupo on grupo.id = equipo.id_grupo "
							+ " where equipo.codigo=?;");
			smt.setString(1, codigo.trim());
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				Double proximaMantencion = rs.getDouble(7);
				Double estimado = (double)0;
					java.util.Date hoy = new java.util.Date();
					Long deltaDias = Math.round((double) ( hoy.getTime()-rs.getDate(2).getTime())/(24 * 60 * 60 * 1000));
				estimado = rs.getDouble(6) + rs.getDouble(5)* deltaDias/30;
				String fecha = null;
				if (rs.getDate(2) != null) {
					fecha = myformatfecha.format(rs.getDate(2));
				}
				
				Double difEstimMenosProxMant = proximaMantencion - estimado;
				
				aux = new PlanMantencion(
						rs.getLong(1),
						fecha,
						rs.getLong(3),
					myformatdouble2.format(rs.getDouble(4)),
					myformatdouble2.format(rs.getDouble(5)),
					myformatdouble2.format(rs.getDouble(6)),
					myformatdouble2.format(proximaMantencion),
					rs.getString(8),
					myformatdouble2.format(estimado),
					rs.getString(9),rs.getString(10),rs.getString(11),rs.getLong(12),rs.getString(13),
					myformatdouble2.format(difEstimMenosProxMant));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
		}
		return (aux);
	}

	public static List<PlanMantencion> all(Connection con, String db) {
		List<PlanMantencion> lista = new ArrayList<PlanMantencion>();
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(db);
		Map<Long,Double> mapStock = Inventarios.mapEquiposConStock(con, db, "TODO", mapeoDiccionario);
		try {
			PreparedStatement smt = con
					.prepareStatement(" select "
							+ " planMantencion.id_equipo, "
							+ " planMantencion.fechaReset, "
							+ " planMantencion.id_unidadMantencion, "
							+ " planMantencion.cadaNEstimado, "
							+ " planMantencion.consumoEstimadoPorMes, "
							+ " planMantencion.estadoActual, "
							+ " planMantencion.proximaMantencion, "
							+ " unidadMantencion.nombre, "
							+ " grupo.nombre, "
							+ " equipo.codigo, "
							+ " equipo.nombre, "
							+ " planMantencion.id_tipoPlan, "
							+ " tipoPlan.nombre "
							+ " from `"+db+"`.planMantencion "
							+ " left join `"+db+"`.tipoPlan on tipoPlan.id = planMantencion.id_tipoPlan "
							+ " left join `"+db+"`.unidadMantencion on unidadMantencion.id = id_unidadMantencion "
							+ " left join `"+db+"`.equipo on equipo.id = id_equipo "
							+ " left join `"+db+"`.grupo on grupo.id = equipo.id_grupo "
							+ " order by grupo.nombre,equipo.nombre;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				
				Double stock = mapStock.get(rs.getLong(1));
				if(stock!=null ) {
					Double proximaMantencion = rs.getDouble(7);
					Double estimado = (double)0;
						java.util.Date hoy = new java.util.Date();
						Long deltaDias = Math.round((double) ( hoy.getTime()-rs.getDate(2).getTime())/(24 * 60 * 60 * 1000));
					estimado = rs.getDouble(6) + rs.getDouble(5)* deltaDias/30;
					String fecha = null;
					if (rs.getDate(2) != null) {
						fecha = myformatfecha.format(rs.getDate(2));
					}
					
					Double difEstimMenosProxMant = proximaMantencion - estimado;
					
					lista.add(new PlanMantencion(
							rs.getLong(1),
							fecha,
							rs.getLong(3),
						myformatdouble2.format(rs.getDouble(4)),
						myformatdouble2.format(rs.getDouble(5)),
						myformatdouble2.format(rs.getDouble(6)),
						myformatdouble2.format(proximaMantencion),
						rs.getString(8),
						myformatdouble2.format(estimado),
						rs.getString(9),rs.getString(10),rs.getString(11),rs.getLong(12),rs.getString(13),
						myformatdouble2.format(difEstimMenosProxMant)));
				}
				
				
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
		}
		return (lista);
	}
	
	public static Map<String,PlanMantencion> mapPorCodigoEquipo(Connection con, String db) {
		Map<String,PlanMantencion> map = new HashMap<String,PlanMantencion>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select "
							+ " planMantencion.id_equipo, "
							+ " planMantencion.fechaReset, "
							+ " planMantencion.id_unidadMantencion, "
							+ " planMantencion.cadaNEstimado, "
							+ " planMantencion.consumoEstimadoPorMes, "
							+ " planMantencion.estadoActual, "
							+ " planMantencion.proximaMantencion, "
							+ " unidadMantencion.nombre, "
							+ " grupo.nombre, "
							+ " equipo.codigo, "
							+ " equipo.nombre, "
							+ " planMantencion.id_tipoPlan, "
							+ " tipoPlan.nombre "
							+ " from `"+db+"`.planMantencion "
							+ " left join `"+db+"`.tipoPlan on tipoPlan.id = planMantencion.id_tipoPlan "
							+ " left join `"+db+"`.unidadMantencion on unidadMantencion.id = id_unidadMantencion "
							+ " left join `"+db+"`.equipo on equipo.id = id_equipo "
							+ " left join `"+db+"`.grupo on grupo.id = equipo.id_grupo "
							+ " order by grupo.nombre,equipo.nombre;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				Double proximaMantencion = rs.getDouble(7);
				Double estimado = (double)0;
					java.util.Date hoy = new java.util.Date();
					Long deltaDias = Math.round((double) ( hoy.getTime()-rs.getDate(2).getTime())/(24 * 60 * 60 * 1000));
				estimado = rs.getDouble(6) + rs.getDouble(5)* deltaDias/30;
				String fecha = null;
				if (rs.getDate(2) != null) {fecha = myformatfecha.format(rs.getDate(2));}
				Double difEstimMenosProxMant = proximaMantencion - estimado;
				map.put(rs.getString(10).toUpperCase(), new PlanMantencion(rs.getLong(1),fecha,rs.getLong(3),
					myformatdouble2.format(rs.getDouble(4)),
					myformatdouble2.format(rs.getDouble(5)),
					myformatdouble2.format(rs.getDouble(6)),
					myformatdouble2.format(proximaMantencion),
					rs.getString(8),
					myformatdouble2.format(estimado),rs.getString(9),rs.getString(10),rs.getString(11),rs.getLong(12),rs.getString(13),
					myformatdouble2.format(difEstimMenosProxMant)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
		}
		return (map);
	}
	
	public static List<PlanMantencion> allTipoPlanPorId_equipo(Connection con, String db, Long id_equipo) {
		List<PlanMantencion> lista = new ArrayList<PlanMantencion>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select "
							+ " planMantencion.id_equipo, "
							+ " planMantencion.fechaReset, "
							+ " planMantencion.id_unidadMantencion, "
							+ " planMantencion.cadaNEstimado, "
							+ " planMantencion.consumoEstimadoPorMes, "
							+ " planMantencion.estadoActual, "
							+ " planMantencion.proximaMantencion, "
							+ " unidadMantencion.nombre, "
							+ " grupo.nombre, "
							+ " equipo.codigo, "
							+ " equipo.nombre, "
							+ " planMantencion.id_tipoPlan, "
							+ " tipoPlan.nombre "
							+ " from `"+db+"`.planMantencion "
							+ " left join `"+db+"`.tipoPlan on tipoPlan.id = planMantencion.id_tipoPlan "
							+ " left join `"+db+"`.unidadMantencion on unidadMantencion.id = id_unidadMantencion "
							+ " left join `"+db+"`.equipo on equipo.id = id_equipo "
							+ " left join `"+db+"`.grupo on grupo.id = equipo.id_grupo "
							+ " where planMantencion.id_equipo = ?;");
			smt.setLong(1, id_equipo);
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				Double proximaMantencion = rs.getDouble(7);
				Double estimado = (double)0;
					java.util.Date hoy = new java.util.Date();
					Long deltaDias = Math.round((double) ( hoy.getTime()-rs.getDate(2).getTime())/(24 * 60 * 60 * 1000));
				estimado = rs.getDouble(6) + rs.getDouble(5)* deltaDias/30;
				String fecha = null;		
				if (rs.getDate(2) != null) {fecha = myformatfecha.format(rs.getDate(2));}
				Double difEstimMenosProxMant = proximaMantencion - estimado;
				lista.add(new PlanMantencion(rs.getLong(1),fecha,rs.getLong(3),
						myformatdouble2.format(rs.getDouble(4)),
						myformatdouble2.format(rs.getDouble(5)),
						myformatdouble2.format(rs.getDouble(6)),
						myformatdouble2.format(proximaMantencion),
						rs.getString(8),
						myformatdouble2.format(estimado),rs.getString(9),rs.getString(10),rs.getString(11),rs.getLong(12),rs.getString(13),
						myformatdouble2.format(difEstimMenosProxMant)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (lista);
	}
	
	public static boolean addPlan(Connection con, String db, Long id_tipoPlan, Long id_equipo) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("insert into `"+db+"`.planMantencion (fechaReset, id_tipoPlan, id_equipo) values (?, ?, ?);");
			smt.setString(1, Fechas.hoy().getFechaStrAAMMDD());
			smt.setLong(2, id_tipoPlan);
			smt.setLong(3, id_equipo);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return(flag);
	}
	
	public static Boolean delete(Connection con, String db, Long id_tipoPlan, Long id_equipo) {
		Boolean flag = false;
		try {
				PreparedStatement smt2 = con
						.prepareStatement(" select id from `"+db+"`.hojaVida where id_tipoPlan = ? and id_equipo = ?;");
				smt2.setLong(1, id_tipoPlan);
				smt2.setLong(2, id_equipo);
				ResultSet rs2 = smt2.executeQuery();
				
				if (!rs2.next()) {
					PreparedStatement smt = con
							.prepareStatement("delete from `"+db+"`.planMantencion where id_tipoPlan = ? and id_equipo = ?;");
					smt.setLong(1, id_tipoPlan);
					smt.setLong(2, id_equipo);
					smt.executeUpdate();
					smt.close();
					flag = true;
				}
				rs2.close();
				smt2.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}
	
	public static List<Equipo> allSinPlanMantencionConStock( Connection con, String db) {
		List<Equipo> lista = new ArrayList<Equipo>();
		try {
			PreparedStatement smt2 = con
					.prepareStatement(" select distinct id_equipo from `"+db+"`.planMantencion;");
			String listaCond ="";
			ResultSet rs2 = smt2.executeQuery();
			while (rs2.next()) {
				listaCond = listaCond + rs2.getString(1) + ",";
			}
			rs2.close();
			smt2.close();
			
			if(listaCond.length()>1) {
				listaCond = listaCond.substring(0,listaCond.length()-1);
				listaCond = " where equipo.vigente=1 and equipo.id not in ("+listaCond+") ";
			}
			
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" equipo.id, " +
							" equipo.id_fabrica, " +
							" equipo.codigo, " +
							" equipo.nombre, " +
							" equipo.id_grupo, " +
							" equipo.id_unidad, " +
							" fabrica.nickName, " +
							" grupo.nombre, " +
							" unidad.nombre, " +
							" equipo.img, " +
							" equipo.vigente, " +
							" equipo.kg, " +
							" equipo.m2 " +
							" from `"+db+"`.movimiento " +
							" left join `"+db+"`.equipo on equipo.id = movimiento.id_equipo " +
							" left join `"+db+"`.fabrica on fabrica.id = equipo.id_fabrica " +
							" left join `"+db+"`.grupo on grupo.id = equipo.id_grupo " +
							" left join `"+db+"`.unidad on unidad.id = equipo.id_unidad " +
							listaCond +
							" group by movimiento.id_equipo " +
							" having if(sum(movimiento.cantidad*if(movimiento.id_tipoMovimiento=1,1,-1))=-0,0,sum(movimiento.cantidad*if(movimiento.id_tipoMovimiento=1,1,-1)))>0 " +
							" order by grupo.nombre, equipo.nombre;");
				ResultSet rs = smt.executeQuery();
				while (rs.next()) {
						lista.add(new Equipo(rs.getLong(1),rs.getLong(2),rs.getString(3),rs.getString(4),rs.getLong(5),rs.getLong(6),
								rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getDouble(12),rs.getDouble(13),rs.getLong(11)));
				}
				rs.close();
				smt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static boolean create(Connection con,String db, Long id_equipo) {
		boolean flag = false;
		try {	
			PreparedStatement smt = con
					.prepareStatement("insert into `"+db+"`.planMantencion (id_equipo,fechaReset) values (?,?)");
			java.sql.Date hoy = new java.sql.Date(new java.util.Date().getTime());
			smt.setLong(1, id_equipo);
			smt.setDate(2, hoy);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException  e) {
				e.printStackTrace();
		}
		return (flag);
	}
	
	public static Boolean actualizaPorCampo(Connection con, String db, Long id_tipoPlan, Long id_equipo, String campo, String valor) {
		Boolean flag = false;
		try {	
			PreparedStatement smt = con
					.prepareStatement("update `"+db+"`.planMantencion set `" + campo + "` = ? where id_tipoPlan = ? and id_equipo = ?;");
			smt.setString(1, valor.trim());
			smt.setLong(2, id_tipoPlan);
			smt.setLong(3, id_equipo);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}
	
	

	
}
