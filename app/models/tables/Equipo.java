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

import models.forms.FormEquipoGraba;
import models.utilities.Fechas;



public class Equipo {
	public Long id;
	public Long id_fabrica;
	public String codigo;
	public String nombre;
	public Long id_grupo;
	public Long id_unidad;
	public String fabrica;
	public String grupo;
	public String unidad;
	public String img;
	
	public Double KG;
	public Double M2;
	
	public Long vigente;

	public Equipo(Long id, Long id_fabrica, String codigo, String nombre, Long id_grupo, Long id_unidad, String fabrica,
			String grupo, String unidad, String img, Double kG, Double m2, Long vigente) {
		super();
		this.id = id;
		this.id_fabrica = id_fabrica;
		this.codigo = codigo;
		this.nombre = nombre;
		this.id_grupo = id_grupo;
		this.id_unidad = id_unidad;
		this.fabrica = fabrica;
		this.grupo = grupo;
		this.unidad = unidad;
		this.img = img;
		this.KG = kG;
		this.M2 = m2;
		this.vigente = vigente;
	}

	public Equipo() {
		super();
	}
	
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public Long getId_fabrica() {return id_fabrica;}
	public void setId_fabrica(Long id_fabrica) {this.id_fabrica = id_fabrica;}
	public String getCodigo() {return codigo;}
	public void setCodigo(String codigo) {this.codigo = codigo;}
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}
	public Long getId_grupo() {return id_grupo;}
	public void setId_grupo(Long id_grupo) {this.id_grupo = id_grupo;}
	public Long getId_unidad() {return id_unidad;}
	public void setId_unidad(Long id_unidad) {this.id_unidad = id_unidad;}
	public String getFabrica() {return fabrica;}
	public void setFabrica(String fabrica) {this.fabrica = fabrica;}
	public String getGrupo() {return grupo;}
	public void setGrupo(String grupo) {this.grupo = grupo;}
	public String getUnidad() {return unidad;}
	public void setUnidad(String unidad) {this.unidad = unidad;}
	public String getImg() {return img;}
	public void setImg(String img) {this.img = img;}
	public Double getKG() {return KG;}
	public void setKG(Double kG) {KG = kG;}
	public Double getM2() {return M2;}
	public void setM2(Double m2) {M2 = m2;}
	public Long getVigente() {return vigente;}
	public void setVigente(Long vigente) {this.vigente = vigente;}


	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatentero = new DecimalFormat("#,##0");
	static SimpleDateFormat myformatfecha = new SimpleDateFormat("dd-MM-yyyy");
	
	
	
	public static Map<Long,Long> mapIdEquipoVsIdGrupo (Connection con, String db) {
		Map<Long,Long> map = new HashMap<Long,Long>();
		List<Equipo> lista = Equipo.allVigentes(con, db);
		for(int i=0; i<lista.size(); i++) {
			map.put(lista.get(i).id, lista.get(i).id_grupo);
		}
		return map;
	}
	
	
	
	public static Map<String,String> mapUnaSolaUbicacion (Connection con, String db) {
		Map<String,String> map = new HashMap<String,String>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select bodegaEmpresa.nombre, id_equipo, sum(if(id_tipoMovimiento=1,1,-1)*cantidad) "
							+ " from `"+db+"`.movimiento "
							+ " left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = id_bodegaEmpresa "
							+ " group by bodegaEmpresa.nombre, id_equipo "
							+ " having sum(if(id_tipoMovimiento=1,1,-1)*cantidad)>0;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				map.put(rs.getString(2), rs.getString(1));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public static Map<Long,Equipo> mapAllVigentes (Connection con, String db) {
		Map<Long,Equipo> map = new HashMap<Long,Equipo>();
		List<Equipo> lista = Equipo.allVigentes(con, db);
		for(int i=0; i<lista.size(); i++) {
			map.put(lista.get(i).id, lista.get(i));
		}
		return map;
	}
	
	public static Map<Long,Equipo> mapAllAll (Connection con, String db) {
		Map<Long,Equipo> map = new HashMap<Long,Equipo>();
		List<Equipo> lista = Equipo.allAll(con, db);
		for(int i=0; i<lista.size(); i++) {
			map.put(lista.get(i).id, lista.get(i));
		}
		return map;
	}
	
	public static Map<String,Equipo> mapAllAllPorCodigo (Connection con, String db) {
		Map<String,Equipo> map = new HashMap<String,Equipo>();
		List<Equipo> lista = Equipo.allAll(con, db);
		for(int i=0; i<lista.size(); i++) {
			map.put(lista.get(i).codigo, lista.get(i));
		}
		return map;
	}
	
	public static Map<String,Equipo> mapAllVigentesPorCodigo (Connection con, String db) {
		Map<String,Equipo> map = new HashMap<String,Equipo>();
		List<Equipo> lista = Equipo.allVigentes(con, db);
		for(int i=0; i<lista.size(); i++) {
			map.put(lista.get(i).codigo, lista.get(i));
		}
		return map;
	}
	
	
	
	public static List<Equipo> allVigentes(Connection con, String db) {
		List<Equipo> lista = new ArrayList<Equipo>();
		try {
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
							" equipo.vigente " +
							" from `"+db+"`.equipo " +
							" left join `"+db+"`.fabrica on fabrica.id = equipo.id_fabrica " +
							" left join `"+db+"`.grupo on grupo.id = equipo.id_grupo " +
							" left join `"+db+"`.unidad on unidad.id = equipo.id_unidad " +
							" where equipo.vigente = 1 " +
							" order by grupo.nombre,equipo.nombre;");
			ResultSet rs = smt.executeQuery();
			Map<Long,Double> pesos = Atributo.mapAtributoPESO(con, db);
			Map<Long,Double> superficies = Atributo.mapAtributoM2(con, db);
			
			while (rs.next()) {
				Double peso = pesos.get(rs.getLong(1));
				if(peso==null) peso=(double)0;
				Double sup = superficies.get(rs.getLong(1));
				if(sup==null) sup=(double)0;
				lista.add(new Equipo(rs.getLong(1),rs.getLong(2),rs.getString(3),rs.getString(4),rs.getLong(5),rs.getLong(6),
						rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),peso,sup,rs.getLong(11)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<Equipo> allAll(Connection con, String db) {
		List<Equipo> lista = new ArrayList<Equipo>();
		try {
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
							" equipo.vigente " +
							" from `"+db+"`.equipo " +
							" left join `"+db+"`.fabrica on fabrica.id = equipo.id_fabrica " +
							" left join `"+db+"`.grupo on grupo.id = equipo.id_grupo " +
							" left join `"+db+"`.unidad on unidad.id = equipo.id_unidad " +
							" order by grupo.nombre,equipo.nombre;");
			ResultSet rs = smt.executeQuery();
			Map<Long,Double> pesos = Atributo.mapAtributoPESO(con, db);
			Map<Long,Double> superficies = Atributo.mapAtributoM2(con, db);
			
			while (rs.next()) {
				Double peso = pesos.get(rs.getLong(1));
				if(peso==null) peso=(double)0;
				Double sup = superficies.get(rs.getLong(1));
				if(sup==null) sup=(double)0;
				lista.add(new Equipo(rs.getLong(1),rs.getLong(2),rs.getString(3),rs.getString(4),rs.getLong(5),rs.getLong(6),
						rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),peso,sup,rs.getLong(11)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static Equipo find(Connection con, String db, Long id_equipo) {
		Equipo aux = null;
		try {
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
							" equipo.vigente " +
							" from `"+db+"`.equipo " +
							" left join `"+db+"`.fabrica on fabrica.id = equipo.id_fabrica " +
							" left join `"+db+"`.grupo on grupo.id = equipo.id_grupo " +
							" left join `"+db+"`.unidad on unidad.id = equipo.id_unidad " +
							" where equipo.id=?;");
			smt.setLong(1, id_equipo);
			ResultSet rs = smt.executeQuery();
			Map<Long,Double> pesos = Atributo.mapAtributoPESO(con, db);
			Map<Long,Double> superficies = Atributo.mapAtributoM2(con, db);
			if (rs.next()) {
				Double peso = pesos.get(rs.getLong(1));
				if(peso==null) peso=(double)0;
				Double sup = superficies.get(rs.getLong(1));
				if(sup==null) sup=(double)0;	
				aux = new Equipo(rs.getLong(1),rs.getLong(2),rs.getString(3),rs.getString(4),rs.getLong(5),rs.getLong(6),
						rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),peso,sup,rs.getLong(11));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (aux);
	}
	
	public static Equipo findXCodigo(Connection con, String db, String codigo) {
		Equipo aux = null;
		try {
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
							" equipo.vigente " +
							" from `"+db+"`.equipo " +
							" left join `"+db+"`.fabrica on fabrica.id = equipo.id_fabrica " +
							" left join `"+db+"`.grupo on grupo.id = equipo.id_grupo " +
							" left join `"+db+"`.unidad on unidad.id = equipo.id_unidad " +
							" where upper(equipo.codigo)=?;" );
			smt.setString(1, codigo.toUpperCase().trim());
			ResultSet rs = smt.executeQuery();
			Map<Long,Double> pesos = Atributo.mapAtributoPESO(con, db);
			Map<Long,Double> superficies = Atributo.mapAtributoM2(con, db);
			if (rs.next()) {
				Double peso = pesos.get(rs.getLong(1));
				if(peso==null) peso=(double)0;
				Double sup = superficies.get(rs.getLong(1));
				if(sup==null) sup=(double)0;	
				aux = new Equipo(rs.getLong(1),rs.getLong(2),rs.getString(3),rs.getString(4),rs.getLong(5),rs.getLong(6),
						rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),peso,sup,rs.getLong(11));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (aux);
	}
	
	public static boolean cambiarGrupo(Connection con, String db, Long id_grupo, Long id_equipo) {
		boolean flag = false;
		try {
			PreparedStatement smt1 = con.prepareStatement("UPDATE `"+db+"`.equipo set id_grupo = ? WHERE id = ?;");
			smt1.setLong(1, id_grupo);
			smt1.setLong(2, id_equipo);
			smt1.executeUpdate();
			smt1.close();
	
			PreparedStatement smt = con.prepareStatement("delete from `"+db+"`.atributoEquipo WHERE id_equipo = ?;");
			smt.setLong(1, id_equipo);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean existeCodigo(Connection con, String db, String codigo) {
		boolean flag = false;
		try {
			PreparedStatement smt1 = con.prepareStatement("select * from `"+db+"`.equipo WHERE upper(codigo) = ?;");
			smt1.setString(1, codigo.toUpperCase());
			ResultSet rs = smt1.executeQuery();
			if (rs.next()) {
				flag = true;
			}
			smt1.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean modificaPorCampo(Connection con, String db, String campo, Long id_equipo, String valor) {
		boolean flag = false;
		try {
			PreparedStatement smt = con.prepareStatement("UPDATE `"+db+"`.equipo set `" + campo + "` = ? WHERE id = ?;");
			if(campo.equals("codigo")) valor = valor.replaceAll("\\,","").trim().toUpperCase();
			if(campo.equals("nombre")) valor = valor.trim();
			smt.setString(1, valor.trim());
			smt.setLong(2, id_equipo);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean estaEnUso(Connection con, String db, Long id_equipo) {
		boolean flag = false;
		try {
			PreparedStatement smt1 = con.prepareStatement("select * from `"+db+"`.movimiento WHERE id_equipo = ?;");
			smt1.setLong(1, id_equipo);
			ResultSet rs1 = smt1.executeQuery();
			if(rs1.next()) {
				flag=true;
			}else {
				smt1 = con.prepareStatement("select * from `"+db+"`.planMantencion WHERE id_equipo = ?;");
				smt1.setLong(1, id_equipo);
				rs1 = smt1.executeQuery();
				if(rs1.next()) {
					flag=true;
				}else {
					smt1 = con.prepareStatement("select * from `"+db+"`.hojaVida WHERE id_equipo = ?;");
					smt1.setLong(1, id_equipo);
					rs1 = smt1.executeQuery();
					if(rs1.next()) {
						flag=true;
					}else {
						smt1 = con.prepareStatement("select * from `"+db+"`.reparacionEquipoTMP WHERE id_equipo = ?;");
						smt1.setLong(1, id_equipo);
						rs1 = smt1.executeQuery();
						if(rs1.next()) {
							flag=true;
						}else {
							smt1 = con.prepareStatement("select * from `"+db+"`.compra WHERE id_equipo = ?;");
							smt1.setLong(1, id_equipo);
							rs1 = smt1.executeQuery();
							if(rs1.next()) {
								flag=true;
							}else {
								smt1 = con.prepareStatement("select * from `"+db+"`.baja WHERE id_equipo = ?;");
								smt1.setLong(1, id_equipo);
								rs1 = smt1.executeQuery();
								if(rs1.next()) {
									flag=true;
								}else {
									smt1 = con.prepareStatement("select * from `"+db+"`.adicional WHERE id_equipo = ?;");
									smt1.setLong(1, id_equipo);
									rs1 = smt1.executeQuery();
									if(rs1.next()) {
										flag=true;
									}else {
										smt1 = con.prepareStatement("select * from `"+db+"`.adicionalAuxiliarDePaso WHERE id_equipo = ?;");
										smt1.setLong(1, id_equipo);
										rs1 = smt1.executeQuery();
										if(rs1.next()) {
											flag=true;
										}else {
											smt1 = con.prepareStatement("select * from `"+db+"`.cotizaDetalle WHERE id_equipo = ?;");
											smt1.setLong(1, id_equipo);
											rs1 = smt1.executeQuery();
											if(rs1.next()) {
												flag=true;
											}else {
												smt1 = con.prepareStatement("select * from `"+db+"`.cronograma WHERE id_equipo = ?;");
												smt1.setLong(1, id_equipo);
												rs1 = smt1.executeQuery();
												if(rs1.next()) {
													flag=true;
												}else {
													smt1 = con.prepareStatement("select * from `"+db+"`.equipoServicio WHERE id_equipo = ?;");
													smt1.setLong(1, id_equipo);
													rs1 = smt1.executeQuery();
													if(rs1.next()) {
														flag=true;
													}else {
														smt1 = con.prepareStatement("select * from `"+db+"`.redimensionar WHERE id_equipoRedimensionar = ? or id_equipoOrigen = ?;");
														smt1.setLong(1, id_equipo);
														smt1.setLong(2, id_equipo);
														rs1 = smt1.executeQuery();
														if(rs1.next()) {
															flag=true;
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			rs1.close();
			smt1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	
	public static boolean delete(Connection con, String db, Long idEquipo) {
		boolean flag = false;
		try {
			PreparedStatement smta = con.prepareStatement("DELETE FROM `"+db+"`.equipo WHERE id = ?");
			smta.setLong(1, idEquipo);smta.executeUpdate();
			smta.close();
			PreparedStatement smt8 = con.prepareStatement("DELETE from `"+db+"`.precio WHERE id_equipo = ?");
			smt8.setLong(1, idEquipo);smt8.executeUpdate();
			smt8.close();
			PreparedStatement smt9 = con.prepareStatement("delete from `"+db+"`.precioHistorico WHERE id_equipo = ?");
			smt9.setLong(1, idEquipo);smt9.executeUpdate();
			smt9.close();
			PreparedStatement smte = con.prepareStatement("delete from `"+db+"`.listaPrecio WHERE id_equipo = ?");
			smte.setLong(1, idEquipo);smte.executeUpdate();
			smte.close();
			PreparedStatement smtr = con.prepareStatement("delete from `"+db+"`.dctoEquipo WHERE id_equipo = ?");
			smtr.setLong(1, idEquipo);smtr.executeUpdate();
			smtr.close();
			PreparedStatement smtt = con.prepareStatement("delete from `"+db+"`.tasaEquipo WHERE id_equipo = ?");
			smtt.setLong(1, idEquipo);smtt.executeUpdate();
			smtt.close();
			PreparedStatement smttt = con.prepareStatement("delete from `"+db+"`.atributoEquipo WHERE id_equipo = ?");
			smttt.setLong(1, idEquipo);smttt.executeUpdate();
			smttt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean create(Connection con, String db, FormEquipoGraba aux) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("INSERT INTO `"+db+"`.equipo (id_fabrica,codigo,nombre,id_grupo,id_unidad) " +
							" VALUES (?,?,?,?,?)");
			smt.setLong(1, aux.id_fabrica);
			smt.setString(2, aux.codigo.replaceAll("\\,","").trim().toUpperCase());
			smt.setString(3, aux.nombre.trim());
			smt.setLong(4, aux.id_grupo);
			smt.setLong(5, aux.id_unidad);
			smt.executeUpdate();
			smt.close();
			
			PreparedStatement smt2 = con
					.prepareStatement("select id from `"+db+"`.equipo where id_fabrica=? and codigo=? and nombre=? and id_grupo=? and id_unidad=?;");
			smt2.setLong(1, aux.id_fabrica);
			smt2.setString(2, aux.codigo.replaceAll("\\,","").trim().toUpperCase());
			smt2.setString(3, aux.nombre.trim());
			smt2.setLong(4, aux.id_grupo);
			smt2.setLong(5, aux.id_unidad);
			ResultSet rs = smt2.executeQuery();
			
			if(rs.next()) {
				
				Long id_equipo = rs.getLong(1);
				List<Sucursal> listSucursal = Sucursal.all(con, db);
				String datos = "";
				for(Sucursal s: listSucursal) {
					 datos += "("+id_equipo+",'1','"+Fechas.hoy().getFechaStrAAMMDD()+"','0','0','0','4','0','0',"+s.getId()+"),";
				}
				datos = datos.substring(0,datos.length()-1);
				
				
				PreparedStatement smt3 = con
						.prepareStatement("insert into `"+db+"`.precio "
								+ " (id_equipo,id_moneda,fecha,precioVenta,precioReposicion,tasaArriendo,id_unidadTiempo,precioMinimo,permanenciaMinima, id_sucursal) "
								+ " values "+datos+";");
				smt3.executeUpdate();
				smt3.close();
			}
			
			smt2.close();
			rs.close();
			
			flag = true;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
		
	
	
	
}
