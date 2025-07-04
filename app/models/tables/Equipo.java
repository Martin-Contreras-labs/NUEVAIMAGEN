package models.tables;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.TempFile;

import models.forms.FormEquipoGraba;
import models.utilities.Archivos;
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
	
	public Double kg;
	public Double m2;
	
	public Long vigente;

	public Equipo(Long id, Long id_fabrica, String codigo, String nombre, Long id_grupo, Long id_unidad, String fabrica,
			String grupo, String unidad, String img, Double kg, Double m2, Long vigente) {
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
		this.kg = kg;
		this.m2 = m2;
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
	public Double getKg() {return kg;}
	public void setKg(Double kg) {this.kg = kg;}
	public Double getM2() {return m2;}
	public void setM2(Double m2) {this.m2 = m2;}
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
	
	public static Map<String,String> mapConExistenciaUnaUnidad (Connection con, String db, Map<String,String> mapeoDiccionario) {
		Map<String,String> map = new HashMap<String,String>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select"
							+ " sucursal.nombre,"
							+ " bodegaEmpresa.nombre,"
							+ " movimiento.id_equipo,"
							+ " bodegaEmpresa.id,"
							+ " sum(if(movimiento.id_tipoMovimiento=1,1,-1)*movimiento.cantidad),"
							+ " bodegaEmpresa.esInterna"
							+ " from `"+db+"`.movimiento"
							+ " left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = movimiento.id_bodegaEmpresa"
							+ " left join `"+db+"`.sucursal on sucursal.id = bodegaEmpresa.id_sucursal"
							+ " left join `"+db+"`.guia on guia.id = movimiento.id_guia"
							+ " where bodegaEmpresa.id is not null"
							+ " group by movimiento.id_bodegaEmpresa, movimiento.id_equipo"
							+ " having sum(if(id_tipoMovimiento=1,1,-1)*cantidad) = 1"
							+ " order by guia.fecha desc;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				String tipoBodega = "CLIENTE/PROYECTO";
				if(rs.getLong(5) == (long)6) {
					tipoBodega = mapeoDiccionario.get("BODEGA")+"/INTERNA";
				}
				// id_equipo vs nameSucursal-nameBodega_&_id_bodega_&_nameTipoBodega
				map.put(rs.getString(3), rs.getString(1)+" - "+rs.getString(2)+"_&_"+rs.getString(4)+"_&_"+tipoBodega);
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
			map.put(lista.get(i).codigo.toUpperCase(), lista.get(i));
		}
		return map;
	}
	
	public static Map<String,Equipo> mapAllVigentesPorCodigo (Connection con, String db) {
		Map<String,Equipo> map = new HashMap<String,Equipo>();
		List<Equipo> lista = Equipo.allVigentes(con, db);
		for(int i=0; i<lista.size(); i++) {
			map.put(lista.get(i).codigo.toUpperCase(), lista.get(i));
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
							" ifnull(grupo.nombre,'SIN GRUPO'), " +
							" unidad.nombre, " +
							" equipo.img, " +
							" equipo.vigente, " +
							" equipo.kg, " +
							" equipo.m2 " +
							" from `"+db+"`.equipo " +
							" left join `"+db+"`.fabrica on fabrica.id = equipo.id_fabrica " +
							" left join `"+db+"`.grupo on grupo.id = equipo.id_grupo " +
							" left join `"+db+"`.unidad on unidad.id = equipo.id_unidad " +
							" where equipo.vigente = 1 " +
							" order by ifnull(grupo.nombre,'SIN GRUPO'),equipo.nombre;");
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
							" ifnull(grupo.nombre,'SIN GRUPO'), " +
							" unidad.nombre, " +
							" equipo.img, " +
							" equipo.vigente, " +
							" equipo.kg, " +
							" equipo.m2 " +
							" from `"+db+"`.equipo " +
							" left join `"+db+"`.fabrica on fabrica.id = equipo.id_fabrica " +
							" left join `"+db+"`.grupo on grupo.id = equipo.id_grupo " +
							" left join `"+db+"`.unidad on unidad.id = equipo.id_unidad " +
							" order by ifnull(grupo.nombre,'SIN GRUPO'),equipo.nombre;");
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
							" ifnull(grupo.nombre,'SIN GRUPO'), " +
							" unidad.nombre, " +
							" equipo.img, " +
							" equipo.vigente, " +
							" equipo.kg, " +
							" equipo.m2 " +
							" from `"+db+"`.equipo " +
							" left join `"+db+"`.fabrica on fabrica.id = equipo.id_fabrica " +
							" left join `"+db+"`.grupo on grupo.id = equipo.id_grupo " +
							" left join `"+db+"`.unidad on unidad.id = equipo.id_unidad " +
							" where equipo.id=?;");
			smt.setLong(1, id_equipo);
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				aux = new Equipo(rs.getLong(1),rs.getLong(2),rs.getString(3),rs.getString(4),rs.getLong(5),rs.getLong(6),
						rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getDouble(12),rs.getDouble(13),rs.getLong(11));
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
							" ifnull(grupo.nombre,'SIN GRUPO'), " +
							" unidad.nombre, " +
							" equipo.img, " +
							" equipo.vigente, " +
							" equipo.kg, " +
							" equipo.m2 " +
							" from `"+db+"`.equipo " +
							" left join `"+db+"`.fabrica on fabrica.id = equipo.id_fabrica " +
							" left join `"+db+"`.grupo on grupo.id = equipo.id_grupo " +
							" left join `"+db+"`.unidad on unidad.id = equipo.id_unidad " +
							" where upper(equipo.codigo)=?;" );
			smt.setString(1, codigo.toUpperCase().trim());
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				aux = new Equipo(rs.getLong(1),rs.getLong(2),rs.getString(3),rs.getString(4),rs.getLong(5),rs.getLong(6),
						rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getDouble(12),rs.getDouble(13),rs.getLong(11));
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
			PreparedStatement smt1 = con.prepareStatement("update `"+db+"`.equipo set id_grupo = ? WHERE id = ?;");
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
			PreparedStatement smt = con.prepareStatement("update `"+db+"`.equipo set `" + campo + "` = ? WHERE id = ?;");
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
			PreparedStatement smta = con.prepareStatement("delete from `"+db+"`.equipo WHERE id = ?");
			smta.setLong(1, idEquipo);smta.executeUpdate();
			smta.close();
			PreparedStatement smt8 = con.prepareStatement("delete from `"+db+"`.precio WHERE id_equipo = ?");
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
					.prepareStatement("insert into `"+db+"`.equipo (id_fabrica,codigo,nombre,id_grupo,id_unidad,kg,m2) " +
							" values (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			smt.setLong(1, aux.id_fabrica);
			smt.setString(2, aux.codigo.replaceAll("\\,","").trim().toUpperCase());
			smt.setString(3, aux.nombre.trim());
			smt.setLong(4, aux.id_grupo);
			smt.setLong(5, aux.id_unidad);
			smt.setDouble(6, aux.kg);
			smt.setDouble(7, aux.m2);
			smt.executeUpdate();
			
			Long id_equipo = (long)0;
			ResultSet rs = smt.getGeneratedKeys();
            if (rs.next()) {
            	id_equipo = rs.getLong(1);
            }
            smt.close();
            rs.close();
			
			List<Sucursal> listSucursal = Sucursal.all(con, db);
			String datos = "";
			for(Sucursal s: listSucursal) {
				 datos += "("+id_equipo+",'1','"+Fechas.hoy().getFechaStrAAMMDD()+"','0','0','0','4','0','0',"+s.getId()+"),";
			}
			if(datos.length()>10) {
				datos = datos.substring(0,datos.length()-1);
				PreparedStatement smt3 = con
						.prepareStatement("insert into `"+db+"`.precio "
								+ " (id_equipo,id_moneda,fecha,precioVenta,precioReposicion,tasaArriendo,id_unidadTiempo,precioMinimo,permanenciaMinima, id_sucursal) "
								+ " values "+datos+";");
				smt3.executeUpdate();
				smt3.close();
			}
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static File allExcel(String db, Map<String,String> mapDiccionario, 
			List<Equipo> listEquipos, Map<Long,Double> mapStock, Map<Long,List<String>> mapUbicacion, Map<Long,List<String>> mapPCompra,
			List<List<String>> listAtribGroup, Map<String,List<String>> mapAtributos) {
		File tmp = TempFile.createTempFile("tmp","null");
				
				try {
					String path = "formatos/excel.xlsx";
					InputStream formato = Archivos.leerArchivo(path);
		            Workbook libro = WorkbookFactory.create(formato);
		            formato.close();
		            
		            // 0 negro 1 blanco 2 rojo 3 verde 4 azul 5 amarillo 19 celeste
		            CellStyle titulo = libro.createCellStyle();
		            Font font = libro.createFont();
		            font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		            font.setColor((short)4);
		            font.setFontHeight((short)(14*20));
		            titulo.setFont(font);
		            
		            CellStyle subtitulo = libro.createCellStyle();
		            Font font2 = libro.createFont();
		            font2.setBoldweight(Font.BOLDWEIGHT_BOLD);
		            font2.setColor((short)0);
		            font2.setFontHeight((short)(12*20));
		            subtitulo.setFont(font2);
		            
		            CellStyle encabezado = libro.createCellStyle();
		            encabezado.setBorderBottom(CellStyle.BORDER_THIN);
		            encabezado.setBorderTop(CellStyle.BORDER_THIN);
		            encabezado.setBorderRight(CellStyle.BORDER_THIN);
		            encabezado.setBorderLeft(CellStyle.BORDER_THIN);
		            encabezado.setFillPattern(CellStyle.SOLID_FOREGROUND);
		            encabezado.setFillForegroundColor((short)19);
		            encabezado.setAlignment(CellStyle.ALIGN_LEFT);
		            
		            CellStyle detalle = libro.createCellStyle();
		            detalle.setBorderBottom(CellStyle.BORDER_THIN);
		            detalle.setBorderTop(CellStyle.BORDER_THIN);
		            detalle.setBorderRight(CellStyle.BORDER_THIN);
		            detalle.setBorderLeft(CellStyle.BORDER_THIN);
		            
		            
		            
		            //titulos del archivo
		            
		            libro.setSheetName(0, "EQUIPOS");
		            Sheet hoja1 = libro.getSheetAt(0);
		            
		            Row row = null;
		            Cell cell = null;
		            
		            row = hoja1.createRow(1);
		            cell = row.createCell(1);
		            cell.setCellStyle(titulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("LISTADO DE EQUIPOS");
					
					row = hoja1.createRow(2);
		            cell = row.createCell(1);
		            cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("EMPRESA: "+mapDiccionario.get("nEmpresa"));
					
					row = hoja1.createRow(3);
		            cell = row.createCell(1);
		            cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("FECHA: "+Fechas.hoy().getFechaStrDDMMAA());
					
					
					
					//anchos de columnas
					for(int i=1; i<100; i++) {
						hoja1.setColumnWidth(i, 6*1000);
					}
					//INSERTA LOGO DESPUES DE ANCHOS DE COLUMNAS
					InputStream x = Archivos.leerArchivo(db+"/"+mapDiccionario.get("logoEmpresa"));
		            byte[] bytes = IOUtils.toByteArray(x);
		            x.close();
		            int pngIndex = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
					Drawing draw = hoja1.createDrawingPatriarch();
					CreationHelper helper = libro.getCreationHelper();
					ClientAnchor anchor = helper.createClientAnchor();
			        //set top-left corner for the image
			        anchor.setCol1(9);
			        anchor.setRow1(1);
					Picture img = draw.createPicture(anchor, pngIndex);
					img.resize(0.4);
					hoja1.createFreezePane(0, 0, 0,0);
					
					
					// encabezado de la tabla
					
					int posRow = 8;
					
					row = hoja1.createRow(posRow);
					int posCell = 0;
					
					posCell++;
		            cell = row.createCell(posCell);
		            cell.setCellStyle(titulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("LISTADO:");
					
					posRow += 2;
					posCell = 0;
					
					row = hoja1.createRow(posRow);
					
					posCell++;
					cell = row.createCell(posCell);
		            cell.setCellStyle(encabezado);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("VIGENTE");
					
					posCell++;
					cell = row.createCell(posCell);
		            cell.setCellStyle(encabezado);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("GRUPO");
					
					posCell++;
					cell = row.createCell(posCell);
		            cell.setCellStyle(encabezado);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("CODIGO");
					
					posCell++;
					cell = row.createCell(posCell);
		            cell.setCellStyle(encabezado);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("EQUIPO");
					
					posCell++;
					cell = row.createCell(posCell);
		            cell.setCellStyle(encabezado);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("FABRICANTE");
					
					posCell++;
					cell = row.createCell(posCell);
		            cell.setCellStyle(encabezado);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("UN");
					
					posCell++;
					cell = row.createCell(posCell);
		            cell.setCellStyle(encabezado);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("STOCK");
					
					posCell++;
					cell = row.createCell(posCell);
		            cell.setCellStyle(encabezado);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("UBICACION");
					
					posCell++;
					cell = row.createCell(posCell);
		            cell.setCellStyle(encabezado);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("Nro FACTURA");
					
					posCell++;
					cell = row.createCell(posCell);
		            cell.setCellStyle(encabezado);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("FECHA\nFACTURA");
					
					posCell++;
					cell = row.createCell(posCell);
		            cell.setCellStyle(encabezado);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("MONEDA");
					
					posCell++;
					cell = row.createCell(posCell);
		            cell.setCellStyle(encabezado);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("PRECIO\nCOMPRA");
					
					posCell++;
					cell = row.createCell(posCell);
		            cell.setCellStyle(encabezado);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("PROVEEDOR");
					
					posCell++;
					cell = row.createCell(posCell);
		            cell.setCellStyle(encabezado);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("PESO (KG)");
					
					posCell++;
					cell = row.createCell(posCell);
		            cell.setCellStyle(encabezado);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("AREA (M2)");
					
					
					Row rowAtrib = hoja1.createRow(posRow-2);
					Row rowNum = hoja1.createRow(posRow-1);
					Row rowUn = row;
					
					for(List<String> atrib: listAtribGroup) {
						posCell++;
						cell = rowAtrib.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(atrib.get(0));
						
						cell = rowNum.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(atrib.get(1));
						
						cell = rowUn.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(atrib.get(2));
					}
					
					
					
					
			        
					for(int i=0;i<listEquipos.size();i++){
									
						posRow++;
						posCell = 0;
				        row = hoja1.createRow(posRow);
								
				        posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(listEquipos.get(i).getVigente());
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(listEquipos.get(i).getGrupo());
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(listEquipos.get(i).getCodigo());
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(listEquipos.get(i).getNombre());
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(listEquipos.get(i).getFabrica());
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(listEquipos.get(i).getUnidad());
						
						Double stock = mapStock.get(listEquipos.get(i).getId());
						if(stock == null) {
							stock = (double) 0;
						}
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellValue(stock);
						
						//mapStock, mapUbicacion
						List<String> ubicacion = mapUbicacion.get(listEquipos.get(i).getId());
						if(ubicacion != null) {
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							if(stock > 1) {
								cell.setCellValue("varias");
							}else{
								cell.setCellValue(ubicacion.get(1));
							}

							
						}else {
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
						}
						
						List<String> listCompra = mapPCompra.get(listEquipos.get(i).getId());
						
						if(listCompra != null) {
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue(listCompra.get(3));
							
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue(Fechas.DDMMAA(listCompra.get(2)));
							
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue(listCompra.get(1));
							
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue(Double.parseDouble(listCompra.get(0).replaceAll(",", "")));
							
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue(listCompra.get(4));
						}else {
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
						}
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellValue(listEquipos.get(i).getKg());
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellValue(listEquipos.get(i).getM2());
						
						for(List<String> atrib: listAtribGroup) {
							List<String> list = mapAtributos.get(atrib.get(0)+"_"+atrib.get(3)+"_"+listEquipos.get(i).getId());
							if(list != null) {
								if(atrib.get(3).equals("1")) {
									posCell++;
									cell = row.createCell(posCell);
									cell.setCellStyle(detalle);
									cell.setCellType(Cell.CELL_TYPE_NUMERIC);
									cell.setCellValue(Double.parseDouble(list.get(2).replaceAll(",", "")));
								}else {
									posCell++;
									cell = row.createCell(posCell);
									cell.setCellStyle(detalle);
									cell.setCellType(Cell.CELL_TYPE_STRING);
									cell.setCellValue(list.get(2));
								}
							}else {
								posCell++;
								cell = row.createCell(posCell);
								cell.setCellStyle(detalle);
								cell.setCellType(Cell.CELL_TYPE_STRING);
								cell.setCellValue("");
							}
							
						}
						
					}
					
					posRow = posRow + 5;
					row = hoja1.createRow(posRow);
					cell = row.createCell(1);
					Hyperlink hiper = helper.createHyperlink(0);
					hiper.setAddress("https://www.inqsol.cl");
					cell.setHyperlink(hiper);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("Documento generado desde MADA propiedad de INQSOL");

					// Write the output to a file tmp
					FileOutputStream fileOut = new FileOutputStream(tmp);
					libro.write(fileOut);
					fileOut.close();
					
					
				} catch (Exception e) {
					e.printStackTrace();
		        }
				
				return tmp;
			}
		
	
	
	
}
