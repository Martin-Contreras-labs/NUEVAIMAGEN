package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Atributo{

	public Long id;
	public Long id_grupo;
	public String atributo;
	public Long id_unidad;
	public Long esNumerico;
	public String grupo;
	public String unidad;
	public String valorEnEquipo;

	public Atributo(Long id, Long id_grupo, String atributo, Long id_unidad,
			Long esNumerico, String grupo, String unidad, String valorEnEquipo) {
		super();
		this.id = id;
		this.id_grupo = id_grupo;
		this.atributo = atributo;
		this.id_unidad = id_unidad;
		this.esNumerico = esNumerico;
		this.grupo = grupo;
		this.unidad = unidad;
		this.valorEnEquipo = valorEnEquipo;
	}
	
	public Atributo() {
		super();
	}

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public Long getId_grupo() {return id_grupo;}
	public void setId_grupo(Long id_grupo) {this.id_grupo = id_grupo;}
	public String getAtributo() {return atributo;}
	public void setAtributo(String atributo) {this.atributo = atributo;}
	public Long getId_unidad() {return id_unidad;}
	public void setId_unidad(Long id_unidad) {this.id_unidad = id_unidad;}
	public Long getEsNumerico() {return esNumerico;}
	public void setEsNumerico(Long esNumerico) {this.esNumerico = esNumerico;}
	public String getGrupo() {return grupo;}
	public void setGrupo(String grupo) {this.grupo = grupo;}
	public String getUnidad() {return unidad;}
	public void setUnidad(String unidad) {this.unidad = unidad;}
	public String getValorEnEquipo() {return valorEnEquipo;}
	public void setValorEnEquipo(String valorEnEquipo) {this.valorEnEquipo = valorEnEquipo;}



	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");

	public static List<Atributo> allXEquipoConValor(Connection con, String db, Equipo equipo) {
		List<Atributo> listAtributo = Atributo.allXGrupo(con, db, equipo.id_grupo);
		Map<String,List<String>> map = Atributo.mapIdAtributoXequipoConValor(con, db, equipo.getId());
		listAtributo.forEach(x ->{
			List<String> valor = map.get(x.getId().toString()+"_"+equipo.getId().toString());
			x.setValorEnEquipo("");
			if(valor!=null) {
				if(x.esNumerico==(long)1) {
					x.setValorEnEquipo(myformatdouble2.format(Double.parseDouble(valor.get(1))));
				}else {
					x.setValorEnEquipo(valor.get(0));
				}
			}
		});
		return (listAtributo);
	}
	
	public static List<List<String>> listAtributosGroup(Connection con, String db) {
		List<List<String>> lista = new ArrayList<List<String>>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select "
							+ "	atributo.atributo, atributo.esNumerico, unidad.nombre"
							+ "	from `"+db+"`.atributo"
							+ "	left join `"+db+"`.unidad on unidad.id = atributo.id_unidad"
							+ "	group by atributo, esNumerico;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				List<String> aux = new ArrayList<String>();
				String esNumerico = "Texto";
				if(rs.getLong(2)>0) {
					esNumerico = "Numero";
				}
				aux.add(rs.getString(1)); 	// 0 nombre atributo
				aux.add(esNumerico);		// 1 texto o numero
				aux.add(rs.getString(3));	// 2 unidad
				aux.add(rs.getString(2));	// 3 0=texto o 1=numero
				lista.add(aux);
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static Map<String,List<String>> mapIdAtributoXequipoConValor(Connection con, String db, Long id_equipo) {
		Map<String,List<String>> map = new HashMap<String,List<String>>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select id_atributo, id_equipo, strAtributo, numAtributo from `"+db+"`.atributoEquipo where id_equipo = ?; ");
			smt.setLong(1, id_equipo);
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				List<String> aux = new ArrayList<String>();
				aux.add(rs.getString(3));	// strAtributo
				aux.add(rs.getString(4));	// numAtributo
				map.put(rs.getString(1)+"_"+rs.getString(2), aux);
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (map);
	}
	
	public static Map<String,List<String>> mapAtributosAllEquipos(Connection con, String db) {
		Map<String,List<String>> map = new HashMap<String,List<String>>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select"
							+ " atributo.atributo,"
							+ " atributoEquipo.id_equipo,"
							+ " atributoEquipo.strAtributo,"
							+ " atributoEquipo.numAtributo,"
							+ " atributo.esNumerico "
							+ " from `"+db+"`.atributoEquipo"
							+ " left join `"+db+"`.atributo on atributo.id = atributoEquipo.id_atributo"
							+ " group by atributo.atributo, atributoEquipo.id_equipo;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				String valorFinal = rs.getString(3);
				if(rs.getLong(5) > 0) {
					valorFinal = rs.getString(4);
				}
				List<String> aux = new ArrayList<String>();
				aux.add(rs.getString(3));	// strAtributo
				aux.add(rs.getString(4));	// numAtributo
				aux.add(valorFinal);	// valorFinal
				map.put(rs.getString(1)+"_"+rs.getString(5)+"_"+rs.getString(2), aux);
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (map);
	}
	
	
	public static List<Atributo> allXGrupo(Connection con, String db, Long id_grupo) {
		List<Atributo> lista = new ArrayList<Atributo>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" atributo.id, " +
							" atributo.id_grupo, " +
							" atributo.atributo, " +
							" atributo.id_unidad, " +
							" atributo.esNumerico, " +
							" grupo.nombre, " +
							" unidad.nombre " +
							" from `"+db+"`.atributo " +
							" left join `"+db+"`.grupo on grupo.id = atributo.id_grupo " +
							" left join `"+db+"`.unidad on unidad.id = atributo.id_unidad " +
							" where atributo.id_grupo = ? order by atributo.atributo;");
			smt.setLong(1, id_grupo);
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				lista.add(new Atributo(rs.getLong(1),rs.getLong(2),rs.getString(3),
						rs.getLong(4),rs.getLong(5),rs.getString(6),rs.getString(7),"noAplica"));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}

	public static boolean existeAtributo(Connection con, String db, Long id_grupo, String nombreAtributo) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement(" select atributo.id from `"+db+"`.atributo " +
							" where upper(atributo.atributo) = ? and atributo.id_grupo = ?" );
			smt.setString(1, nombreAtributo);
			smt.setLong(2, id_grupo);
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				flag =true;
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean estaEnUso(Connection con, String db, Long id_atributo) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("Select * FROM `"+db+"`.atributoEquipo WHERE id_atributo = ?");
			smt.setLong(1, id_atributo);
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				flag =true;
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean create(Connection con, String db, Atributo atributo) {	
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("INSERT INTO `"+db+"`.atributo (id_grupo,atributo,id_unidad,esNumerico) " +
							" VALUES (?,?,?,?)");		
			smt.setLong(1, atributo.getId_grupo());
			smt.setString(2, atributo.getAtributo().trim());
			smt.setLong(3, atributo.getId_unidad());
			smt.setLong(4, atributo.getEsNumerico());
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean delete(Connection con, String db, Long id_atributo) {
		boolean flag = false;
		try {
			PreparedStatement smt2 = con
					.prepareStatement("DELETE FROM `"+db+"`.atributo WHERE id = ?");
			smt2.setLong(1,id_atributo);
			smt2.executeUpdate();
			smt2.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static Map<Long,Double> mapAtributoPESO(Connection con, String db) {
		Map<Long,Double> par = new HashMap<Long,Double>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select id_equipo,numAtributo from `"+db+"`.atributoEquipo where id_atributo "
							+ "in (select id from `"+db+"`.atributo where atributo like '%peso%');"); 
	
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				par.put(rs.getLong(1), rs.getDouble(2));
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (par);
	}
	
	public static Map<Long,Double> mapAtributoM2(Connection con, String db) {
		Map<Long,Double> par = new HashMap<Long,Double>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select id_equipo,numAtributo from `"+db+"`.atributoEquipo where id_atributo "
							+ "in (select id from `"+db+"`.atributo where atributo like '%m2%');"); 
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				par.put(rs.getLong(1), rs.getDouble(2));
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (par);
	}
	
	public static boolean modifyAtributoEquipo(Connection con, String db, Long id_atributo, Long id_equipo, String valor, Long esNumero) {
		Double numAtributo = (double) 0;
		String strAtributo = "";
		if(esNumero == (long)1 && !valor.equals("")){
			numAtributo = Double.parseDouble(valor);
		}else{
			strAtributo = valor.trim();
		}
		boolean flag = false;
		try {
			PreparedStatement smt1 = con
					.prepareStatement("select * from `"+db+"`.atributoEquipo " +
							" WHERE id_atributo = ? and id_equipo = ?; ");
			smt1.setLong(1, id_atributo);
			smt1.setLong(2, id_equipo);
			ResultSet rs1 = smt1.executeQuery();
			if(rs1.next()){
				PreparedStatement smt2 = con
						.prepareStatement("UPDATE `"+db+"`.atributoEquipo SET strAtributo = ?, numAtributo =  ?" +
								" WHERE id_atributo = ? and id_equipo =  ?;" );
				smt2.setString(1, strAtributo.trim());
				smt2.setDouble(2, numAtributo);
				smt2.setLong(3, id_atributo);
				smt2.setLong(4, id_equipo);
				smt2.executeUpdate();
				smt2.close();
				flag = true;
			}else{
				PreparedStatement smt3 = con
						.prepareStatement("insert into `"+db+"`.atributoEquipo (id_atributo, id_equipo, strAtributo, numAtributo) " +
								" values(?,?,?,?);");
				smt3.setLong(1, id_atributo);
				smt3.setLong(2, id_equipo);
				smt3.setString(3, strAtributo.trim());
				smt3.setDouble(4, numAtributo);
				smt3.executeUpdate();
				smt3.close();
				flag = true;
			}
			rs1.close();
			smt1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean grabaAtributoEquipo(Connection con, String db, Long id_Equipo, Long id_atributo, String valor) {
		boolean flag = false;
		String strValor = " ";
		String numValor = "0";
		if(Atributo.esNumerico(con, db, id_atributo)){
			numValor = valor.replaceAll("\\,","").trim();
			strValor = " ";
		}else{
			strValor=valor;
			numValor = "0";
		}
		try {
			PreparedStatement smt = con
					.prepareStatement("INSERT INTO `"+db+"`.atributoEquipo (id_equipo,id_atributo,strAtributo,numAtributo) " +
							" VALUES (?,?,?,?);");
			smt.setLong(1, id_Equipo);
			smt.setLong(2, id_atributo);
			smt.setString(3, strValor.trim());
			smt.setString(4, numValor.trim());
			smt.executeUpdate();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		}
		return (flag);
	}
	
	public static boolean esNumerico(Connection con, String db, Long id_atributo) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement(" select atributo.esNumerico " +
							" from `"+db+"`.atributo where atributo.id = ?" );
			smt.setLong(1, id_atributo);
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				if(rs.getLong(1) == (long)1) {
					flag = true;
				}
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		}
		return (flag);
	}
	
	public static List<String> findValorAtributoEquipo(Connection con, String db, Long id_equipo, Long id_atributo) {
		List<String> lista = new ArrayList<String>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select id_equipo,id_atributo,strAtributo,numAtributo,publico from `"+db+"`.atributoEquipo " +
							" where id_equipo = ? and id_atributo = ?;");
			smt.setLong(1, id_equipo);
			smt.setLong(2, id_atributo);
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				lista.add(rs.getString(1)); //0 id_equipo
				lista.add(rs.getString(2)); //1 id_atributo
				lista.add(rs.getString(3)); //2 strAtributo
				lista.add(rs.getString(4)); //3 numAtributo
				lista.add(rs.getString(5)); //4 publico
			}else{
				lista.add(id_equipo.toString());
				lista.add(id_atributo.toString());
				lista.add("No Aplica");
				lista.add("No Aplica");
				lista.add("No Aplica");
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static String detalleEquipo(Connection con, String db, Long id_equipo) {
		String vistaHtml = "<table class='table table-sm table-hover table-bordered table-condensed table-fluid'>";
			try {
				PreparedStatement smt1 = con
						.prepareStatement(" select distinct " +
								" equipo.id, " +
								" equipo.id_grupo, " +
								" equipo.id_unidad, " +
								" equipo.codigo, " +
								" equipo.nombre, " +
								" grupo.nombre, " +
								" unidad.nombre, " +
								" equipo.img " +
								" from `"+db+"`.equipo " +
								" left join `"+db+"`.grupo on grupo.id = equipo.id_grupo " +
								" left join `"+db+"`.unidad on unidad.id = equipo.id_unidad " +
								" where equipo.id = ?;");
				smt1.setLong(1, id_equipo);
				ResultSet rs1 = smt1.executeQuery();
				String img = "";
				if (rs1.next()) {
					vistaHtml = vistaHtml + 
						"<TR>" +
							"<td style= 'text-align: left;'>CODIGO</td>" +
							"<td style= 'text-align: left;'>"+rs1.getString(4)+"</td>" +
						"</TR><TR>"+
							"<td style= 'text-align: left;'>EQUIPO</td>" +
							"<td style= 'text-align: left;'>"+rs1.getString(5)+"</td>" +
						"</TR><TR>"+
							"<td style= 'text-align: left;'>GRUPO:</td>"+
							"<td style= 'text-align: left;'>"+rs1.getString(6)+"</td>"+
						"</TR><TR>"+
							"<td style= 'text-align: left;'>UNIDAD:</td>"+
							"<td style= 'text-align: left;'>"+rs1.getString(7)+"</td>"+
						"</TR><TR>";
					img=rs1.getString(8);
				}
				rs1.close();
				smt1.close();
				
				PreparedStatement smt2 = con
						.prepareStatement(" select  " +
								" atributo.esNumerico, " +
								" atributo.id_unidad, " +
								" atributoEquipo.id_equipo, " +
								" atributo.atributo, " +
								" unidad.nombre, " +
								" if(atributo.esNumerico,numAtributo,strAtributo) " +
								" from `"+db+"`.atributoEquipo " +
								" left join `"+db+"`.atributo on atributo.id = atributoEquipo.id_atributo " +
								" left join `"+db+"`.unidad on unidad.id = atributo.id_unidad " +
								" where atributoEquipo.id_equipo = ? order by atributo.atributo;");
				smt2.setLong(1, id_equipo);
				ResultSet rs2 = smt2.executeQuery();
				while (rs2.next()) {
					vistaHtml = vistaHtml + 
						"<TR>" +
							"<td style= 'text-align: left;'>"+rs2.getString(4)+"</td>" +
							"<td style= 'text-align: left;'>"+rs2.getString(6);
						if(rs2.getBoolean(1)){
							vistaHtml = vistaHtml + " " + rs2.getString(5);
						}
						vistaHtml = vistaHtml + "</td></TR>";
				} 
				if(!img.equals("0")) {
					vistaHtml = vistaHtml + "<td colspan='2'><div align='center'><img src='/viewImg/" +img+ 
							"' style='max-height:100%;max-width:100%'/></div></td>";					
				}
				vistaHtml = vistaHtml + "</tbody></table>";
				rs2.close();
				smt2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        return (vistaHtml);
    } 

	
	

}
