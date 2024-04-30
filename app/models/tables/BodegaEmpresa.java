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

import java.sql.*;

import models.forms.FormBodegaGraba;


public class BodegaEmpresa {
	public Long id;
	public Long esInterna; //aqui va el id_tipoBodega ( 1 interna, 2 o > 1 externa)
	public String nombre;
	public Long id_cliente;
	public Long id_proyecto;
	public Double tasaDescto;
	public Double tasaArriendo;
	public Double tasaCfi;
	public Long cobraDiaDespacho;
	public Long nDiaGraciaEnvio;
	public Long nDiaGraciaRegreso;
	public Double factorM2Viga;
	public Long baseCalculo;		// 1 es DIARIO (por dia exacto de cada mes) y 2 es MENSUAL (meses de 30 dias) 
	public Long tratoDevoluciones;
	
	public String nombreTipoBodega;
	public String nickCliente;
	public String nickProyecto;
	
	public String comercial;
	
	public String rutCliente;

	public String pep;			// un codigo que usa conconcreto de colombia
	public Double ivaBodega;	// 0 no aplica distinto aplica a facturas y cotizaciones por bodega
	
	public Long id_sucursal;
	public String nameSucursal;
	
	public Long id_comercial;
	public String nameComercial;
	
	
	
	
	public BodegaEmpresa(Long id, Long esInterna, String nombre, Long id_cliente, Long id_proyecto, Double tasaDescto,
			Double tasaArriendo, Double tasaCfi, Long cobraDiaDespacho, Long nDiaGraciaEnvio, Long nDiaGraciaRegreso,
			Double factorM2Viga, Long baseCalculo, Long tratoDevoluciones, String nombreTipoBodega, String nickCliente,
			String nickProyecto,String comercial, String rutCliente, String pep, Double ivaBodega, Long id_sucursal, 
			String nameSucursal, Long id_comercial, String nameComercial) {
		super();
		this.id = id;
		this.esInterna = esInterna;
		this.nombre = nombre;
		this.id_cliente = id_cliente;
		this.id_proyecto = id_proyecto;
		this.tasaDescto = tasaDescto;
		this.tasaArriendo = tasaArriendo;
		this.tasaCfi = tasaCfi;
		this.cobraDiaDespacho = cobraDiaDespacho;
		this.nDiaGraciaEnvio = nDiaGraciaEnvio;
		this.nDiaGraciaRegreso = nDiaGraciaRegreso;
		this.factorM2Viga = factorM2Viga;
		this.baseCalculo = baseCalculo;
		this.tratoDevoluciones = tratoDevoluciones;
		this.nombreTipoBodega = nombreTipoBodega;
		this.nickCliente = nickCliente;
		this.nickProyecto = nickProyecto;
		this.comercial = comercial;
		this.rutCliente = rutCliente;
		this.pep = pep;
		this.ivaBodega = ivaBodega;
		this.id_sucursal = id_sucursal;
		this.nameSucursal = nameSucursal;
		this.id_comercial = id_comercial;
		this.nameComercial = nameComercial;
		
		if(id_comercial.toString().equals("0")) {
			this.nameComercial = comercial;
		}
		
	}

	public BodegaEmpresa() {
		super();
	}
	
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public Long getEsInterna() {return esInterna;}
	public void setEsInterna(Long esInterna) {	this.esInterna = esInterna;}
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}
	public Long getId_cliente() {return id_cliente;}
	public void setId_cliente(Long id_cliente) {this.id_cliente = id_cliente;}
	public Long getId_proyecto() {return id_proyecto;}
	public void setId_proyecto(Long id_proyecto) {this.id_proyecto = id_proyecto;}
	public Double getTasaDescto() {return tasaDescto;}
	public void setTasaDescto(Double tasaDescto) {this.tasaDescto = tasaDescto;}
	public Double getTasaCfi() {return tasaCfi;}
	public void setTasaCfi(Double tasaCfi) {this.tasaCfi = tasaCfi;}
	public Long getCobraDiaDespacho() {return cobraDiaDespacho;}
	public void setCobraDiaDespacho(Long cobraDiaDespacho) {this.cobraDiaDespacho = cobraDiaDespacho;}
	public Long getnDiaGraciaEnvio() {return nDiaGraciaEnvio;}
	public void setnDiaGraciaEnvio(Long nDiaGraciaEnvio) {this.nDiaGraciaEnvio = nDiaGraciaEnvio;}
	public Long getnDiaGraciaRegreso() {return nDiaGraciaRegreso;}
	public void setnDiaGraciaRegreso(Long nDiaGraciaRegreso) {this.nDiaGraciaRegreso = nDiaGraciaRegreso;}
	public String getNombreTipoBodega() {return nombreTipoBodega;}
	public void setNombreTipoBodega(String nombreTipoBodega) {this.nombreTipoBodega = nombreTipoBodega;}
	public Double getTasaArriendo() {return tasaArriendo;}
	public void setTasaArriendo(Double tasaArriendo) {this.tasaArriendo = tasaArriendo;}
	public Double getFactorM2Viga() {return factorM2Viga;}
	public void setFactorM2Viga(Double factorM2Viga) {this.factorM2Viga = factorM2Viga;}
	public String getNickCliente() {return nickCliente;}
	public void setNickCliente(String nickCliente) {this.nickCliente = nickCliente;}
	public String getNickProyecto() {return nickProyecto;}
	public void setNickProyecto(String nickProyecto) {this.nickProyecto = nickProyecto;}
	public Long getBaseCalculo() {return baseCalculo;}
	public void setBaseCalculo(Long baseCalculo) {this.baseCalculo = baseCalculo;}
	public Long getTratoDevoluciones() {return tratoDevoluciones;}
	public void setTratoDevoluciones(Long tratoDevoluciones) {this.tratoDevoluciones = tratoDevoluciones;}
	public String getComercial() {return comercial;}
	public void setComercial(String comercial) {this.comercial = comercial;}
	public String getPep() {return pep;}
	public void setPep(String pep) {this.pep = pep;}
	public Double getIvaBodega() {return ivaBodega;}
	public void setIvaBodega(Double ivaBodega) {this.ivaBodega = ivaBodega;}
	public String getRutCliente() {return rutCliente;}
	public void setRutCliente(String rutCliente) {this.rutCliente = rutCliente;}

	public Long getId_sucursal() {
		return id_sucursal;
	}

	public void setId_sucursal(Long id_sucursal) {
		this.id_sucursal = id_sucursal;
	}

	public String getNameSucursal() {
		return nameSucursal;
	}

	public void setNameSucursal(String nameSucursal) {
		this.nameSucursal = nameSucursal;
	}

	public Long getId_comercial() {
		return id_comercial;
	}

	public void setId_comercial(Long id_comercial) {
		this.id_comercial = id_comercial;
	}

	public String getNameComercial() {
		return nameComercial;
	}

	public void setNameComercial(String nameComercial) {
		this.nameComercial = nameComercial;
	}



	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdouble4 = new DecimalFormat("#,##0.0000");
	
	
	public static Map<Long,BodegaEmpresa> mapAll(Connection con, String db) {
		Map<Long,BodegaEmpresa> map = new HashMap<Long,BodegaEmpresa>();
		List<BodegaEmpresa> lista = BodegaEmpresa.all(con, db);
		lista.forEach(x->{
			map.put(x.getId(), x);
		});
		return (map);
	}
	
	public static Map<String,Long> mapNombreVsId(Connection con, String db) {
		Map<String,Long> map = new HashMap<String,Long>();
		List<BodegaEmpresa> lista = BodegaEmpresa.all(con, db);
		lista.forEach(x->{
			map.put(x.getNombre(), x.getId());
		});
		return (map);
	}
	
	public static Map<Long, List<String>> mapAllVigentesExternas(Connection con, String db, Map<String,String> mapeoPermiso, String esPorSucursal, String id_sucursal) {
		Map<Long, List<String>> map = new HashMap<Long, List<String>>();
		 List<List<String>> lista = BodegaEmpresa.listaAllBodegasVigentesExternas(con, db, mapeoPermiso, esPorSucursal, id_sucursal);
		lista.forEach(x->{
			Long aux = Long.parseLong(x.get(1));
			map.put(aux, x);
		});
		return (map);
	}
	
	public static Map<Long, List<String>> mapAllVigentesInternas(Connection con, String db, String esPorSucursal, String id_susursal) {
		Map<Long, List<String>> map = new HashMap<Long, List<String>>();
		 List<List<String>> lista = BodegaEmpresa.listaAllBodegasVigentesInternas(con, db, esPorSucursal, id_susursal);
		lista.forEach(x->{
			Long aux = Long.parseLong(x.get(1));
			map.put(aux, x);
		});
		return (map);
	}
	
	public static Map<String,BodegaEmpresa> mapAllNombreInternas(Connection con, String db) {
		Map<String,BodegaEmpresa> map = new HashMap<String,BodegaEmpresa>();
		List<BodegaEmpresa> lista = BodegaEmpresa.allInternas(con, db);
		lista.forEach(x->{
			map.put(x.getNombre().toUpperCase(), x);
		});
		return (map);
	}
	
	public static Map<String,BodegaEmpresa> mapAllNombreExternas(Connection con, String db) {
		Map<String,BodegaEmpresa> map = new HashMap<String,BodegaEmpresa>();
		List<BodegaEmpresa> lista = BodegaEmpresa.allExternas(con, db);
		lista.forEach(x->{
			map.put(x.getNombre().toUpperCase(), x);
		});
		return (map);
	}
	
	public static Map<String,BodegaEmpresa> mapAllNombre(Connection con, String db) {
		Map<String,BodegaEmpresa> map = new HashMap<String,BodegaEmpresa>();
		List<BodegaEmpresa> lista = BodegaEmpresa.allInternas(con, db);
		lista.forEach(x->{
			map.put(x.getNombre().toUpperCase(), x);
		});
		return (map);
	}
	
	
	public static List<BodegaEmpresa> all(Connection con, String db) {
		List<BodegaEmpresa> lista = new ArrayList<BodegaEmpresa>();
		try {
			PreparedStatement smt = con
						.prepareStatement("select " +
								" bodegaEmpresa.id," +
								" esInterna," +
								" bodegaEmpresa.nombre," +
								" id_cliente," +
								" id_proyecto," +
								" tasaDescto, " +
								" tasaArriendo," +
								" tasaCfi," +
								" cobraDiaDespacho," +
								" nDiaGraciaEnvio," +
								" nDiaGraciaRegreso," +
								" tipoBodega.nombre," +
								" factorM2Viga," +
								" baseCalculo," +
								" tratoDevoluciones," +
								" ifnull(cliente.nickName,''), " +
								" ifnull(proyecto.nickName,''), " +
								" ifnull(bodegaEmpresa.comercial,''), "+
								" ifnull(cliente.rut,''), "+
								" ifnull(bodegaEmpresa.pep,''), "+
								" ifnull(bodegaEmpresa.ivaBodega,0), "+
								" bodegaEmpresa.id_sucursal, "+
								" bodegaEmpresa.id_comercial "+
								" from `"+db+"`.bodegaEmpresa " +
								" left join `"+db+"`.tipoBodega on tipoBodega.id = esInterna " +
								" left join `"+db+"`.cliente on cliente.id = bodegaEmpresa.id_cliente " +
								" left join `"+db+"`.proyecto on proyecto.id = bodegaEmpresa.id_proyecto;" );
				ResultSet rs = smt.executeQuery();
				Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
				Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
				while (rs.next()) {
					String nameSucursal = "";
					Sucursal sucursal = mapSucursal.get(rs.getLong(22));
					if(sucursal!=null) {
						nameSucursal = sucursal.nombre;
					}
					String nameComercial = "";
					Comercial comercial = mapComercial.get(rs.getLong(23));
					if(comercial!=null) {
						nameComercial = comercial.getNameUsuario();
					}else {
						nameComercial = rs.getString(18);
					}
					lista.add(new BodegaEmpresa(rs.getLong(1),rs.getLong(2),rs.getString(3),
							rs.getLong(4),rs.getLong(5),	rs.getDouble(6),rs.getDouble(7),
							rs.getDouble(8),rs.getLong(9),
							rs.getLong(10),rs.getLong(11),rs.getDouble(13),
							rs.getLong(14),rs.getLong(15),rs.getString(12),
							rs.getString(16),rs.getString(17),nameComercial,rs.getString(19),
							rs.getString(20),rs.getDouble(21),
							rs.getLong(22),nameSucursal,rs.getLong(23),nameComercial));
				}
				rs.close();
				smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<BodegaEmpresa> allInternas(Connection con, String db) {
		List<BodegaEmpresa> lista = new ArrayList<BodegaEmpresa>();
		try {
			PreparedStatement smt = con
						.prepareStatement("select " +
								" bodegaEmpresa.id," +
								" esInterna," +
								" bodegaEmpresa.nombre," +
								" id_cliente," +
								" id_proyecto," +
								" tasaDescto, " +
								" tasaArriendo," +
								" tasaCfi," +
								" cobraDiaDespacho," +
								" nDiaGraciaEnvio," +
								" nDiaGraciaRegreso," +
								" tipoBodega.nombre," +
								" factorM2Viga," +
								" baseCalculo," +
								" tratoDevoluciones," +
								" ifnull(cliente.nickName,''), " +
								" ifnull(proyecto.nickName,''), " +
								" ifnull(bodegaEmpresa.comercial,''), "+
								" ifnull(cliente.rut,''), "+
								" ifnull(bodegaEmpresa.pep,''), "+
								" ifnull(bodegaEmpresa.ivaBodega,0), "+
								" bodegaEmpresa.id_sucursal, "+
								" bodegaEmpresa.id_comercial "+
								" from `"+db+"`.bodegaEmpresa " +
								" left join `"+db+"`.tipoBodega on tipoBodega.id = esInterna " +
								" left join `"+db+"`.cliente on cliente.id = bodegaEmpresa.id_cliente " +
								" left join `"+db+"`.proyecto on proyecto.id = bodegaEmpresa.id_proyecto " +
								" where esInterna = 1;" );
				ResultSet rs = smt.executeQuery();
				Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
				Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
				while (rs.next()) {
					String nameSucursal = "";
					Sucursal sucursal = mapSucursal.get(rs.getLong(22));
					if(sucursal!=null) {
						nameSucursal = sucursal.nombre;
					}
					String nameComercial = "";
					Comercial comercial = mapComercial.get(rs.getLong(23));
					if(comercial!=null) {
						nameComercial = comercial.getNameUsuario();
					}else {
						nameComercial = rs.getString(18);
					}
					lista.add(new BodegaEmpresa(rs.getLong(1),rs.getLong(2),rs.getString(3),
							rs.getLong(4),rs.getLong(5),	rs.getDouble(6),rs.getDouble(7),
							rs.getDouble(8),rs.getLong(9),
							rs.getLong(10),rs.getLong(11),rs.getDouble(13),
							rs.getLong(14),rs.getLong(15),rs.getString(12),
							rs.getString(16),rs.getString(17),nameComercial,rs.getString(19),
							rs.getString(20),rs.getDouble(21),
							rs.getLong(22),nameSucursal,rs.getLong(23),nameComercial));
				}
				rs.close();
				smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<BodegaEmpresa> allExternas(Connection con, String db) {
		List<BodegaEmpresa> lista = new ArrayList<BodegaEmpresa>();
		try {
			PreparedStatement smt = con
						.prepareStatement("select " +
								" bodegaEmpresa.id," +
								" esInterna," +
								" bodegaEmpresa.nombre," +
								" id_cliente," +
								" id_proyecto," +
								" tasaDescto, " +
								" tasaArriendo," +
								" tasaCfi," +
								" cobraDiaDespacho," +
								" nDiaGraciaEnvio," +
								" nDiaGraciaRegreso," +
								" tipoBodega.nombre," +
								" factorM2Viga," +
								" baseCalculo," +
								" tratoDevoluciones," +
								" ifnull(cliente.nickName,''), " +
								" ifnull(proyecto.nickName,''), " +
								" ifnull(bodegaEmpresa.comercial,''), "+
								" ifnull(cliente.rut,''), "+
								" ifnull(bodegaEmpresa.pep,''), "+
								" ifnull(bodegaEmpresa.ivaBodega,0), "+
								" bodegaEmpresa.id_sucursal, "+
								" bodegaEmpresa.id_comercial "+
								" from `"+db+"`.bodegaEmpresa " +
								" left join `"+db+"`.tipoBodega on tipoBodega.id = esInterna " +
								" left join `"+db+"`.cliente on cliente.id = bodegaEmpresa.id_cliente " +
								" left join `"+db+"`.proyecto on proyecto.id = bodegaEmpresa.id_proyecto " +
								" where esInterna <> 1;" );
				ResultSet rs = smt.executeQuery();
				Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
				Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
				while (rs.next()) {
					String nameSucursal = "";
					Sucursal sucursal = mapSucursal.get(rs.getLong(22));
					if(sucursal!=null) {
						nameSucursal = sucursal.nombre;
					}
					String nameComercial = "";
					Comercial comercial = mapComercial.get(rs.getLong(23));
					if(comercial!=null) {
						nameComercial = comercial.getNameUsuario();
					}else {
						nameComercial = rs.getString(18);
					}
					lista.add(new BodegaEmpresa(rs.getLong(1),rs.getLong(2),rs.getString(3),
							rs.getLong(4),rs.getLong(5),	rs.getDouble(6),rs.getDouble(7),
							rs.getDouble(8),rs.getLong(9),
							rs.getLong(10),rs.getLong(11),rs.getDouble(13),
							rs.getLong(14),rs.getLong(15),rs.getString(12),
							rs.getString(16),rs.getString(17),nameComercial,rs.getString(19),
							rs.getString(20),rs.getDouble(21),
							rs.getLong(22),nameSucursal,rs.getLong(23),nameComercial));
				}
				rs.close();
				smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<BodegaEmpresa> allFiltroPorNombre(Connection con, String db, String filtroPorNombreBodega) {
		List<BodegaEmpresa> lista = new ArrayList<BodegaEmpresa>();
		if(filtroPorNombreBodega.length()>0) {
			filtroPorNombreBodega = " where bodegaEmpresa.nombre in ("+filtroPorNombreBodega+") ";
			try {
				PreparedStatement smt = con
							.prepareStatement("select " +
									" bodegaEmpresa.id," +
									" esInterna," +
									" bodegaEmpresa.nombre," +
									" id_cliente," +
									" id_proyecto," +
									" tasaDescto, " +
									" tasaArriendo," +
									" tasaCfi," +
									" cobraDiaDespacho," +
									" nDiaGraciaEnvio," +
									" nDiaGraciaRegreso," +
									" tipoBodega.nombre," +
									" factorM2Viga," +
									" baseCalculo," +
									" tratoDevoluciones," +
									" ifnull(cliente.nickName,''), " +
									" ifnull(proyecto.nickName,''), " +
									" ifnull(bodegaEmpresa.comercial,''), "+
									" ifnull(cliente.rut,''), "+
									" ifnull(bodegaEmpresa.pep,''), "+
									" ifnull(bodegaEmpresa.ivaBodega,0), "+
									" bodegaEmpresa.id_sucursal, "+
									" bodegaEmpresa.id_comercial "+
									" from `"+db+"`.bodegaEmpresa " +
									" left join `"+db+"`.tipoBodega on tipoBodega.id = esInterna " +
									" left join `"+db+"`.cliente on cliente.id = bodegaEmpresa.id_cliente " +
									" left join `"+db+"`.proyecto on proyecto.id = bodegaEmpresa.id_proyecto " + filtroPorNombreBodega +
									" order by bodegaEmpresa.nombre;" );
					ResultSet rs = smt.executeQuery();
					Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
					Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
					while (rs.next()) {
						String nameSucursal = "";
						Sucursal sucursal = mapSucursal.get(rs.getLong(22));
						if(sucursal!=null) {
							nameSucursal = sucursal.nombre;
						}
						String nameComercial = "";
						Comercial comercial = mapComercial.get(rs.getLong(23));
						if(comercial!=null) {
							nameComercial = comercial.getNameUsuario();
						}else {
							nameComercial = rs.getString(18);
						}
						lista.add(new BodegaEmpresa(rs.getLong(1),rs.getLong(2),rs.getString(3),
								rs.getLong(4),rs.getLong(5),	rs.getDouble(6),rs.getDouble(7),
								rs.getDouble(8),rs.getLong(9),
								rs.getLong(10),rs.getLong(11),rs.getDouble(13),
								rs.getLong(14),rs.getLong(15),rs.getString(12),
								rs.getString(16),rs.getString(17),nameComercial,rs.getString(19),
								rs.getString(20),rs.getDouble(21),
								rs.getLong(22),nameSucursal,rs.getLong(23),nameComercial));
					}
					rs.close();
					smt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return (lista);
	}
	
	public static List<BodegaEmpresa> allFiltroPorId(Connection con, String db, String filtroPorIdBodega) {
		List<BodegaEmpresa> lista = new ArrayList<BodegaEmpresa>();
		if(filtroPorIdBodega.length()>0) {
			filtroPorIdBodega = " where bodegaEmpresa.id in ("+filtroPorIdBodega+") ";
			try {
				PreparedStatement smt = con
							.prepareStatement("select " +
									" bodegaEmpresa.id," +
									" esInterna," +
									" bodegaEmpresa.nombre," +
									" id_cliente," +
									" id_proyecto," +
									" tasaDescto, " +
									" tasaArriendo," +
									" tasaCfi," +
									" cobraDiaDespacho," +
									" nDiaGraciaEnvio," +
									" nDiaGraciaRegreso," +
									" tipoBodega.nombre," +
									" factorM2Viga," +
									" baseCalculo," +
									" tratoDevoluciones," +
									" ifnull(cliente.nickName,''), " +
									" ifnull(proyecto.nickName,''), " +
									" ifnull(bodegaEmpresa.comercial,''), "+
									" ifnull(cliente.rut,''), "+
									" ifnull(bodegaEmpresa.pep,''), "+
									" ifnull(bodegaEmpresa.ivaBodega,0), "+
									" bodegaEmpresa.id_sucursal, "+
									" bodegaEmpresa.id_comercial "+
									" from `"+db+"`.bodegaEmpresa " +
									" left join `"+db+"`.tipoBodega on tipoBodega.id = esInterna " +
									" left join `"+db+"`.cliente on cliente.id = bodegaEmpresa.id_cliente " +
									" left join `"+db+"`.proyecto on proyecto.id = bodegaEmpresa.id_proyecto " + filtroPorIdBodega +
									" order by bodegaEmpresa.nombre;" );
					ResultSet rs = smt.executeQuery();
					Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
					Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
					while (rs.next()) {
						String nameSucursal = "";
						Sucursal sucursal = mapSucursal.get(rs.getLong(22));
						if(sucursal!=null) {
							nameSucursal = sucursal.nombre;
						}
						String nameComercial = "";
						Comercial comercial = mapComercial.get(rs.getLong(23));
						if(comercial!=null) {
							nameComercial = comercial.getNameUsuario();
						}else {
							nameComercial = rs.getString(18);
						}
						lista.add(new BodegaEmpresa(rs.getLong(1),rs.getLong(2),rs.getString(3),
								rs.getLong(4),rs.getLong(5),	rs.getDouble(6),rs.getDouble(7),
								rs.getDouble(8),rs.getLong(9),
								rs.getLong(10),rs.getLong(11),rs.getDouble(13),
								rs.getLong(14),rs.getLong(15),rs.getString(12),
								rs.getString(16),rs.getString(17),nameComercial,rs.getString(19),
								rs.getString(20),rs.getDouble(21),
								rs.getLong(22),nameSucursal,rs.getLong(23),nameComercial));
					}
					rs.close();
					smt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return (lista);
	}
	
	public static BodegaEmpresa findXIdBodega(Connection con, String db, Long id_bodegaEmpresa) {
		BodegaEmpresa aux = null;
		try {
			PreparedStatement smt = con
						.prepareStatement("select " +
								" bodegaEmpresa.id," +
								" esInterna," +
								" bodegaEmpresa.nombre," +
								" id_cliente," +
								" id_proyecto," +
								" tasaDescto, " +
								" tasaArriendo," +
								" tasaCfi," +
								" cobraDiaDespacho," +
								" nDiaGraciaEnvio," +
								" nDiaGraciaRegreso," +
								" tipoBodega.nombre," +
								" factorM2Viga," +
								" baseCalculo," +
								" tratoDevoluciones," +
								" ifnull(cliente.nickName,''), " +
								" ifnull(proyecto.nickName,''), " +
								" ifnull(bodegaEmpresa.comercial,''), "+
								" ifnull(cliente.rut,''), "+
								" ifnull(bodegaEmpresa.pep,''), "+
								" ifnull(bodegaEmpresa.ivaBodega,0), "+
								" bodegaEmpresa.id_sucursal, "+
								" bodegaEmpresa.id_comercial "+
								" from `"+db+"`.bodegaEmpresa " +
								" left join `"+db+"`.tipoBodega on tipoBodega.id = esInterna " +
								" left join `"+db+"`.cliente on cliente.id = bodegaEmpresa.id_cliente " +
								" left join `"+db+"`.proyecto on proyecto.id = bodegaEmpresa.id_proyecto " +
								" where bodegaEmpresa.id = ?;" );
				smt.setLong(1, id_bodegaEmpresa);
				ResultSet rs = smt.executeQuery();
				Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
				Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
				if (rs.next()) {
					String nameSucursal = "";
					Sucursal sucursal = mapSucursal.get(rs.getLong(22));
					if(sucursal!=null) {
						nameSucursal = sucursal.nombre;
					}
					String nameComercial = "";
					Comercial comercial = mapComercial.get(rs.getLong(23));
					if(comercial!=null) {
						nameComercial = comercial.getNameUsuario();
					}else {
						nameComercial = rs.getString(18);
					}
					aux = new BodegaEmpresa(rs.getLong(1),rs.getLong(2),rs.getString(3),
							rs.getLong(4),rs.getLong(5),	rs.getDouble(6),rs.getDouble(7),
							rs.getDouble(8),rs.getLong(9),
							rs.getLong(10),rs.getLong(11),rs.getDouble(13),
							rs.getLong(14),rs.getLong(15),rs.getString(12),
							rs.getString(16),rs.getString(17),nameComercial,rs.getString(19),
							rs.getString(20),rs.getDouble(21),
							rs.getLong(22),nameSucursal,rs.getLong(23),nameComercial);
				}
				rs.close();
				smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (aux);
	}
	
	public static BodegaEmpresa findXNombreBodega(Connection con, String db, String nombreBodega) {
		BodegaEmpresa aux = null;
		try {
			PreparedStatement smt = con
						.prepareStatement("select " +
								" bodegaEmpresa.id," +
								" esInterna," +
								" bodegaEmpresa.nombre," +
								" id_cliente," +
								" id_proyecto," +
								" tasaDescto, " +
								" tasaArriendo," +
								" tasaCfi," +
								" cobraDiaDespacho," +
								" nDiaGraciaEnvio," +
								" nDiaGraciaRegreso," +
								" tipoBodega.nombre," +
								" factorM2Viga," +
								" baseCalculo," +
								" tratoDevoluciones," +
								" ifnull(cliente.nickName,''), " +
								" ifnull(proyecto.nickName,''), " +
								" ifnull(bodegaEmpresa.comercial,''), "+
								" ifnull(cliente.rut,''), "+
								" ifnull(bodegaEmpresa.pep,''), "+
								" ifnull(bodegaEmpresa.ivaBodega,0), "+
								" bodegaEmpresa.id_sucursal, "+
								" bodegaEmpresa.id_comercial "+
								" from `"+db+"`.bodegaEmpresa " +
								" left join `"+db+"`.tipoBodega on tipoBodega.id = esInterna " +
								" left join `"+db+"`.cliente on cliente.id = bodegaEmpresa.id_cliente " +
								" left join `"+db+"`.proyecto on proyecto.id = bodegaEmpresa.id_proyecto " +
								" where bodegaEmpresa.nombre = ?;" );
				smt.setString(1, nombreBodega);
				ResultSet rs = smt.executeQuery();
				Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
				Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
				if (rs.next()) {
					String nameSucursal = "";
					Sucursal sucursal = mapSucursal.get(rs.getLong(22));
					if(sucursal!=null) {
						nameSucursal = sucursal.nombre;
					}
					String nameComercial = "";
					Comercial comercial = mapComercial.get(rs.getLong(23));
					if(comercial!=null) {
						nameComercial = comercial.getNameUsuario();
					}else {
						nameComercial = rs.getString(18);
					}
					aux = new BodegaEmpresa(rs.getLong(1),rs.getLong(2),rs.getString(3),
							rs.getLong(4),rs.getLong(5),	rs.getDouble(6),rs.getDouble(7),
							rs.getDouble(8),rs.getLong(9),
							rs.getLong(10),rs.getLong(11),rs.getDouble(13),
							rs.getLong(14),rs.getLong(15),rs.getString(12),
							rs.getString(16),rs.getString(17),nameComercial,rs.getString(19),
							rs.getString(20),rs.getDouble(21),
							rs.getLong(22),nameSucursal,rs.getLong(23),nameComercial);
				}
				rs.close();
				smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (aux);
	}
	
	public static boolean estaEnUso(Connection con, String db, Long id_bodegaEmpresa) {
		boolean flag = false;
		try {
			
			PreparedStatement smt = con
					.prepareStatement("select id from `"+db+"`.movimiento WHERE id_bodegaEmpresa = ?;" );
			smt.setLong(1, id_bodegaEmpresa);
			ResultSet resultado = smt.executeQuery();
			if (resultado.next()) {
				flag = true;
			}
			resultado.close();
			smt.close();
			
			PreparedStatement smt3 = con
					.prepareStatement("select id from `"+db+"`.tipoEstado WHERE id_bodegaAsociada = ?");
			smt3.setLong(1, id_bodegaEmpresa);
			ResultSet rs3 = smt3.executeQuery();
			if (rs3.next()) {
				flag = true;
			}
			rs3.close();
			smt3.close();
			
			PreparedStatement smt4 = con
					.prepareStatement("select id from `"+db+"`.cotizacion WHERE id_bodegaEmpresa = ?");
			smt4.setLong(1, id_bodegaEmpresa);
			ResultSet rs4 = smt4.executeQuery();
			if (rs4.next()) {
				flag = true;
			}
			
			PreparedStatement smt5 = con
					.prepareStatement("select id from `"+db+"`.ventaServicio WHERE id_bodegaEmpresa = ?");
			smt5.setLong(1, id_bodegaEmpresa);
			ResultSet rs5 = smt5.executeQuery();
			if (rs5.next()) {
				flag = true;
			}
			
			rs5.close();
			smt5.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean delete(Connection con, String db, Long id_bodegaEmpresa) {
		boolean flag = false;
		try {
			
			PreparedStatement smt4 = con.prepareStatement("delete from `"+db+"`.bodegaEmpresa where id = ?;");
			smt4.setLong(1, id_bodegaEmpresa);
			smt4.executeUpdate();
			smt4.close();
			
			PreparedStatement smt5 = con.prepareStatement("delete from `"+db+"`.dctoGrupo where id_bodegaEmpresa = ?;");
			smt5.setLong(1, id_bodegaEmpresa);
			smt5.executeUpdate();
			smt5.close();
			
			PreparedStatement smt6 = con.prepareStatement("delete from `"+db+"`.dctoEquipo where id_bodegaEmpresa = ?;");
			smt6.setLong(1, id_bodegaEmpresa);
			smt6.executeUpdate();
			smt6.close();
			
			PreparedStatement smt7 = con.prepareStatement("delete from `"+db+"`.listaPrecio where id_bodegaEmpresa = ?;");
			smt7.setLong(1, id_bodegaEmpresa);
			smt7.executeUpdate();
			smt7.close();
			
			PreparedStatement smt8 = con.prepareStatement("delete from `"+db+"`.ppto where id_bodegaEmpresa = ?;");
			smt8.setLong(1, id_bodegaEmpresa);
			smt8.executeUpdate();
			smt8.close();
			
			PreparedStatement smt9 = con.prepareStatement("delete from `"+db+"`.fijaTasasCambio where id_bodegaEmpresa = ?;");
			smt9.setLong(1, id_bodegaEmpresa);
			smt9.executeUpdate();
			smt9.close();
			
			flag = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static List<List<String>> listaAllBodegasVigentesNoVigentes(Connection con, String db, String esPorSucursal, String id_sucursal) {
		List<List<String>> lista = new ArrayList<List<String>>();
		
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " and bodegaEmpresa.id_sucursal = " + id_sucursal;
		}
		
		try {
			PreparedStatement smt = con
					.prepareStatement(" select  " +
							" bodegaEmpresa.esInterna,   " +
							" bodegaEmpresa.id,   " +
							" ifnull(cliente.id,0),   " +
							" ifnull(proyecto.id,0),   " +
							" bodegaEmpresa.nombre,     " +
							" ifnull(cliente.rut,''),   " +
							" ifnull(cliente.nickName,''),   " +
							" ifnull(proyecto.nickName,''),   " +
							" ifnull(comunas.nombre,''),   " +
							" tipoBodega.nombre,   " +
							" bodegaEmpresa.vigente, " +
							" ifnull(bodegaEmpresa.pep,''), "+
							" ifnull(bodegaEmpresa.ivaBodega,0), "+
							" ifnull(bodegaEmpresa.comercial,''), "+
							" bodegaEmpresa.id_sucursal, " +
							" bodegaEmpresa.id_comercial " +
							" from `"+db+"`.bodegaEmpresa    " +
							" left join `"+db+"`.cliente on cliente.id = bodegaEmpresa.id_cliente   " +
							" left join `"+db+"`.proyecto on proyecto.id = bodegaEmpresa.id_proyecto   " +
							" left join `"+db+"`.comunas on comunas.codigo = proyecto.cod_comuna   " +
							" left join `"+db+"`.tipoBodega on tipoBodega.id = bodegaEmpresa.esInterna " +
							" where bodegaEmpresa.id != 1 and bodegaEmpresa.id != 2 and (bodegaEmpresa.vigente=1 or bodegaEmpresa.vigente=0) " + condSucursal +
							" order by bodegaEmpresa.vigente desc,bodegaEmpresa.esInterna,bodegaEmpresa.nombre;"); 
			ResultSet rs = smt.executeQuery();
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
			while (rs.next()) {
				String nameSucursal = "";
				Sucursal sucursal = mapSucursal.get(rs.getLong(15));
				if(sucursal!=null) {
					nameSucursal = sucursal.getNombre();
				}
				String nameComercial = "";
				Comercial comercial = mapComercial.get(rs.getLong(16));
				if(comercial!=null) {
					nameComercial = comercial.getNameUsuario();
				}else {
					nameComercial = rs.getString(14);
				}
				List<String> aux = new ArrayList<String>();
				aux.add(rs.getString(1)); // 0 es cliente interno
				aux.add(rs.getString(2));  // 1 idbodega empresa
				aux.add(rs.getString(3));  // 2 id de cliente
				aux.add(rs.getString(4));  // 3 id del proyecto
				aux.add(rs.getString(10));  //4 tipo de cliente interno o externo
				aux.add(rs.getString(5));  // 5 nombre bodega o empresa
				aux.add(rs.getString(6));  // 6 rut del cliente
				aux.add(rs.getString(7));  // 7 nombre del cliente
				aux.add(rs.getString(8));  // 8 nombre del proyecto
				aux.add(rs.getString(9));  // 9 comuna
				aux.add(rs.getString(11));  //10 vigente
				aux.add(rs.getString(12));  //11 pep
				aux.add(rs.getString(13));  //12 ivaBodega
				aux.add(nameSucursal);  //13 nameSucursal
				aux.add(nameComercial);  //14 nameComercial
				lista.add(aux);
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<List<String>> listaAllBodegasVigentesInternasExternas(Connection con, String db, Map<String,String> mapeoPermiso, String esPorSucursal, String id_sucursal) {
		List<List<String>> lista = new ArrayList<List<String>>();
		
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " and bodegaEmpresa.id_sucursal = " + id_sucursal;
		}
		
		try {
			PreparedStatement smt = con
					.prepareStatement(" select  " +
							" bodegaEmpresa.esInterna,   " +
							" bodegaEmpresa.id,   " +
							" bodegaEmpresa.id_cliente,   " +
							" bodegaEmpresa.id_proyecto,   " +
							" bodegaEmpresa.nombre,     " +
							" ifnull(bodegaEmpresa.tasaDescto,'0'),  " +
							" ifnull(bodegaEmpresa.comercial,''), "+ 
							" ifnull(bodegaEmpresa.factorM2Viga,0), "+
							" ifnull(bodegaEmpresa.pep,''), "+
							" ifnull(bodegaEmpresa.ivaBodega,0), "+
							" bodegaEmpresa.id_sucursal, " +
							" bodegaEmpresa.id_comercial " +
							" from `"+db+"`.bodegaEmpresa " +
							" where bodegaEmpresa.vigente = 1  " + condSucursal +
							" order by bodegaEmpresa.esInterna,bodegaEmpresa.nombre;"); 
			ResultSet rs = smt.executeQuery();
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
			Map<Long,Cliente> mapCliente = Cliente.mapAllClientes(con, db);
			Map<Long,Proyecto> mapProyecto = Proyecto.mapAllProyectos(con, db);
			Map<Long,TipoBodega> mapTipoBodega = TipoBodega.mapAll(con, db);
			while (rs.next()) {
				String nameSucursal = "";
				Sucursal sucursal = mapSucursal.get(rs.getLong(11));
				if(sucursal!=null) {
					nameSucursal = sucursal.getNombre();
				}
				String nameComercial = "";
				Comercial comercial = mapComercial.get(rs.getLong(12));
				if(comercial!=null) {
					nameComercial = comercial.getNameUsuario();
				}else {
					nameComercial = rs.getString(7);
				}
				String nameTipoBodega = "";
				TipoBodega tipoBodega = mapTipoBodega.get(rs.getLong(1));
				if(tipoBodega!=null) {
					nameTipoBodega = tipoBodega.getNombre();
				}
				String rutCliente = "";
				String nombreCliente = "";
				Cliente cliente = mapCliente.get(rs.getLong(3));
				if(cliente!=null) {
					rutCliente = cliente.getRut();
					nombreCliente = cliente.getNickName();
				}
				String nombreProyecto = "";
				String comunaProyecto = "";
				Proyecto proyecto = mapProyecto.get(rs.getLong(4));
				if(proyecto!=null) {
					nombreProyecto = proyecto.getNickName();
					comunaProyecto = proyecto.getComuna();
				}
				
				
				
				List<String> aux = new ArrayList<String>();
				aux.add(rs.getString(1)); 			// 0 es cliente interno
				aux.add(rs.getString(2));  			// 1 idbodega empresa
				aux.add(rs.getString(3));  			// 2 id de cliente
				aux.add(rs.getString(4));  			// 3 id del proyecto
				aux.add(nameTipoBodega);  			// 4 tipo de cliente interno o externo
				aux.add(rs.getString(5));  			// 5 nombre bodega o empresa
				aux.add(rutCliente);  			// 6 rut del cliente
				aux.add(nombreCliente);  			// 7 nombre del cliente
				aux.add(nombreProyecto);  			// 8 nombre del proyecto
				aux.add(comunaProyecto);  			// 9 comuna
				String tasa = rs.getString(6);
				if(!tasa.equals("0.00 %")){
					tasa = myformatdouble2.format(rs.getDouble(6)*100) + " %";
				}
				aux.add(tasa);  									// 10 tasa de descuento global
				aux.add(nameComercial);							// 11 comercial
				aux.add(myformatdouble4.format(rs.getDouble(8)));	// 12 factorM2Viga
				aux.add(rs.getString(9));  						// 13 pep
				aux.add(rs.getString(10));  						// 14 ivaBodega
				aux.add((rs.getDouble(10)*100)+"%");  				// 15 ivaBodega en %
				aux.add(nameSucursal);  							// 16 nombre sucursal
				aux.add(rs.getString(11));  						// 17 id sucursal
				lista.add(aux);
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<List<String>> listaAllBodegasVigentesInternas(Connection con, String db, String esPorSucursal, String id_sucursal) {
		List<List<String>> lista = new ArrayList<List<String>>();
		
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " and bodegaEmpresa.id_sucursal = " + id_sucursal;
		}
		
		try {
			PreparedStatement smt = con
					.prepareStatement(" select  " +
							" bodegaEmpresa.esInterna, " +
							" bodegaEmpresa.id, " +
							" ifnull(cliente.id,0), " +
							" ifnull(proyecto.id,0), " +
							" bodegaEmpresa.nombre, " +
							" ifnull(cliente.rut,''), " +
							" ifnull(cliente.nickName,''), " +
							" ifnull(proyecto.nickName,''), " +
							" ifnull(comunas.nombre,''), " +
							" tipoBodega.nombre," +
							" ifnull(bodegaEmpresa.tasaDescto,'0'), " + 
							" ifnull(comercial,''), " + 
							" ifnull(bodegaEmpresa.factorM2Viga,0), "+
							" ifnull(bodegaEmpresa.pep,''), "+
							" ifnull(bodegaEmpresa.ivaBodega,0), "+
							" bodegaEmpresa.id_sucursal, " +
							" bodegaEmpresa.id_comercial "+
							" from `"+db+"`.bodegaEmpresa    " +
							" left join `"+db+"`.cliente on cliente.id = bodegaEmpresa.id_cliente " +
							" left join `"+db+"`.proyecto on proyecto.id = bodegaEmpresa.id_proyecto " +
							" left join `"+db+"`.comunas on comunas.codigo = proyecto.cod_comuna   " +
							" left join `"+db+"`.tipoBodega on tipoBodega.id = bodegaEmpresa.esInterna " +
							" where bodegaEmpresa.vigente = 1 and bodegaEmpresa.esInterna = 1 " + condSucursal +
							" order by bodegaEmpresa.esInterna,bodegaEmpresa.nombre;"); 
			ResultSet rs = smt.executeQuery();
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
			while (rs.next()) {
				String nameSucursal = "";
				Sucursal sucursal = mapSucursal.get(rs.getLong(16));
				if(sucursal!=null) {
					nameSucursal = sucursal.getNombre();
				}
				String nameComercial = "";
				Comercial comercial = mapComercial.get(rs.getLong(17));
				if(comercial!=null) {
					nameComercial = comercial.getNameUsuario();
				}else {
					nameComercial = rs.getString(12);
				}
				List<String> aux = new ArrayList<String>();
				aux.add(rs.getString(1)); 		// 0 es cliente interno
				aux.add(rs.getString(2));  		// 1 idbodega empresa
				aux.add(rs.getString(3));  		// 2 id de cliente
				aux.add(rs.getString(4));  		// 3 id del proyecto
				aux.add(rs.getString(10));  	// 4 tipo de cliente interno o externo
				aux.add(rs.getString(5));  		// 5 nombre bodega o empresa
				aux.add(rs.getString(6));  		// 6 rut del cliente
				aux.add(rs.getString(7));  		// 7 nombre del cliente
				aux.add(rs.getString(8));  		// 8 nombre del proyecto
				aux.add(rs.getString(9));  		// 9 comuna
				String tasa = rs.getString(11);
				if(!tasa.equals("0.00 %")){
					tasa = myformatdouble2.format(rs.getDouble(11)*100) + " %";
				}
				aux.add(tasa);  									// 10 tasa de descuento global
				aux.add(nameComercial);							// 11 comercial
				aux.add(myformatdouble4.format(rs.getDouble(13)));	// 12 factorM2Viga
				aux.add(rs.getString(14));  						// 13 pep
				aux.add(rs.getString(15));  						// 14 ivaBodega
				aux.add((rs.getDouble(15)*100)+"%");  				// 15 ivaBodega en %
				aux.add(nameSucursal);  							// 16 nombre sucursal
				aux.add(rs.getString(16));  						// 17 id sucursal
				lista.add(aux);
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<List<String>> listaAllBodegasVigentesExternas(Connection con, String db, Map<String,String> mapeoPermiso, String esPorSucursal, String id_sucursal) {
		List<List<String>> lista = new ArrayList<List<String>>();
		
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " and bodegaEmpresa.id_sucursal = " + id_sucursal;
		}
		
		try {
			PreparedStatement smt = con
					.prepareStatement(" select  " +
							" bodegaEmpresa.esInterna,   " +
							" bodegaEmpresa.id,   " +
							" ifnull(cliente.id,0),   " +
							" ifnull(proyecto.id,0),   " +
							" bodegaEmpresa.nombre,     " +
							" ifnull(cliente.rut,''),   " +
							" ifnull(cliente.nickName,''),   " +
							" ifnull(proyecto.nickName,''),   " +
							" ifnull(comunas.nombre,''),   " +
							" tipoBodega.nombre," +
							" ifnull(bodegaEmpresa.tasaDescto,'0'), " + 
							" ifnull(comercial,''), " + 
							" ifnull(bodegaEmpresa.factorM2Viga,0), "+
							" ifnull(bodegaEmpresa.pep,''), "+
							" ifnull(bodegaEmpresa.ivaBodega,0), "+
							" bodegaEmpresa.id_sucursal, " +
							" bodegaEmpresa.id_comercial "+
							" from `"+db+"`.bodegaEmpresa    " +
							" left join `"+db+"`.cliente on cliente.id = bodegaEmpresa.id_cliente   " +
							" left join `"+db+"`.proyecto on proyecto.id = bodegaEmpresa.id_proyecto   " +
							" left join `"+db+"`.comunas on comunas.codigo = proyecto.cod_comuna   " +
							" left join `"+db+"`.tipoBodega on tipoBodega.id = bodegaEmpresa.esInterna " +
							" where bodegaEmpresa.vigente = 1 and bodegaEmpresa.esInterna <> 1   " + condSucursal +
							" order by bodegaEmpresa.esInterna,bodegaEmpresa.nombre;");
			ResultSet rs = smt.executeQuery();
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
			while (rs.next()) {
				String nameSucursal = "";
				Sucursal sucursal = mapSucursal.get(rs.getLong(16));
				if(sucursal!=null) {
					nameSucursal = sucursal.getNombre();
				}
				String nameComercial = "";
				Comercial comercial = mapComercial.get(rs.getLong(17));
				if(comercial!=null) {
					nameComercial = comercial.getNameUsuario();
				}else {
					nameComercial = rs.getString(12);
				}
				List<String> aux = new ArrayList<String>();
				aux.add(rs.getString(1)); 		// 0 es cliente interno
				aux.add(rs.getString(2));  		// 1 idbodega empresa
				aux.add(rs.getString(3));  		// 2 id de cliente
				aux.add(rs.getString(4));  		// 3 id del proyecto
				aux.add(rs.getString(10)); 		// 4 tipo de cliente interno o externo
				aux.add(rs.getString(5));  		// 5 nombre bodega o empresa
				aux.add(rs.getString(6));  		// 6 rut del cliente
				aux.add(rs.getString(7));  		// 7 nombre del cliente
				aux.add(rs.getString(8));  		// 8 nombre del proyecto
				aux.add(rs.getString(9));  		// 9 comuna
				String tasa = rs.getString(11);
				if(!tasa.equals("0.00 %")){
					tasa = myformatdouble2.format(rs.getDouble(11)*100) + " %";
				}
				aux.add(tasa);  									// 10 tasa de descuento global
				aux.add(nameComercial);							// 11 comercial
				aux.add(myformatdouble4.format(rs.getDouble(13)));	// 12 factorM2Viga
				aux.add(rs.getString(14));  						// 13 pep
				aux.add(rs.getString(15));  						// 14 ivaBodega
				aux.add((rs.getDouble(15)*100)+"%");  				// 15 ivaBodega en %
				aux.add(nameSucursal);  							// 16 nombre sucursal
				aux.add(rs.getString(16));  						// 17 id sucursal
				lista.add(aux);
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<List<String>> listaAllBodegasVigentesExternasConStock(Connection con, String db, Map<String,String> mapeoPermiso, 
			String esPorSucursal, String id_sucursal, List<List<String>> listBodegas) {
		List<List<String>> lista = new ArrayList<List<String>>();
		Map<Long,List<String>> mapBodega = BodegaEmpresa.mapAllVigentesExternas(con, db, mapeoPermiso, esPorSucursal, id_sucursal);
		listBodegas.forEach(x->{
			List<String> aux = mapBodega.get(Long.parseLong(x.get(0)));
			if(aux != null) {
				
				lista.add(aux);
			}
		});
		return (lista);
	}
	
	public static List<List<String>> listaAllBodegasVigentesExternasFiltradas(Connection con, String db, String permisoPorBodega, String esPorSucursal, String id_sucursal) {
		List<List<String>> lista = new ArrayList<List<String>>();
		
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " and bodegaEmpresa.id_sucursal = " + id_sucursal;
		}
		
		
		try {
			PreparedStatement smt = con
					.prepareStatement(" select  " +
							" bodegaEmpresa.esInterna,   " +
							" bodegaEmpresa.id,   " +
							" ifnull(cliente.id,0),   " +
							" ifnull(proyecto.id,0),   " +
							" bodegaEmpresa.nombre,     " +
							" ifnull(cliente.rut,''),   " +
							" ifnull(cliente.nickName,''),   " +
							" ifnull(proyecto.nickName,''),   " +
							" ifnull(comunas.nombre,''),   " +
							" tipoBodega.nombre," +
							" ifnull(bodegaEmpresa.tasaDescto,'0'), " + 
							" ifnull(comercial,''), " + 
							" ifnull(bodegaEmpresa.factorM2Viga,0), "+
							" ifnull(bodegaEmpresa.pep,''), "+
							" ifnull(bodegaEmpresa.ivaBodega,0), "+
							" bodegaEmpresa.id_sucursal,   " +
							" bodegaEmpresa.id_comercial "+
							" from `"+db+"`.bodegaEmpresa    " +
							" left join `"+db+"`.cliente on cliente.id = bodegaEmpresa.id_cliente   " +
							" left join `"+db+"`.proyecto on proyecto.id = bodegaEmpresa.id_proyecto   " +
							" left join `"+db+"`.comunas on comunas.codigo = proyecto.cod_comuna   " +
							" left join `"+db+"`.tipoBodega on tipoBodega.id = bodegaEmpresa.esInterna " +
							" where bodegaEmpresa.vigente = 1 and bodegaEmpresa.esInterna <> 1   " + permisoPorBodega + condSucursal +
							" order by bodegaEmpresa.esInterna,bodegaEmpresa.nombre;"); 
			ResultSet rs = smt.executeQuery();

			Map<Long, Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
			while (rs.next()) {
				String nameSucursal = "";
				Sucursal sucursal = mapSucursal.get(rs.getLong(16));
				if(sucursal!=null) {
					nameSucursal = sucursal.getNombre();
				}
				String nameComercial = "";
				Comercial comercial = mapComercial.get(rs.getLong(17));
				if(comercial!=null) {
					nameComercial = comercial.getNameUsuario();
				}else {
					nameComercial = rs.getString(12);
				}
				List<String> aux = new ArrayList<String>();
				aux.add(rs.getString(1)); 		// 0 es cliente interno
				aux.add(rs.getString(2));  		// 1 idbodega empresa
				aux.add(rs.getString(3));  		// 2 id de cliente
				aux.add(rs.getString(4));  		// 3 id del proyecto
				aux.add(rs.getString(10));  	// 4 tipo de cliente interno o externo
				aux.add(rs.getString(5));  		// 5 nombre bodega o empresa
				aux.add(rs.getString(6));  		// 6 rut del cliente
				aux.add(rs.getString(7));  		// 7 nombre del cliente
				aux.add(rs.getString(8));  		// 8 nombre del proyecto
				aux.add(rs.getString(9));  		// 9 comuna
				String tasa = rs.getString(11);
				if(!tasa.equals("0.00 %")){
					tasa = myformatdouble2.format(rs.getDouble(11)*100) + " %";
				}
				aux.add(tasa);  									// 10 tasa de descuento global
				aux.add(nameComercial);							// 11 comercial
				aux.add(myformatdouble4.format(rs.getDouble(13)));	// 12 factorM2Viga
				aux.add(rs.getString(14));  						// 13 pep
				aux.add(rs.getString(15));  						// 14 ivaBodega
				aux.add(nameSucursal);  						// 15 nameSucursal

				lista.add(aux);
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static boolean existeBodega(Connection con, String db, String nombreBodega) {
		boolean flag = false;
		try {
			PreparedStatement smt1 = con
					.prepareStatement("select id from `"+db+"`.bodegaEmpresa WHERE UPPER(nombre) = ?;");
			smt1.setString(1, nombreBodega.toUpperCase().trim());
			ResultSet resultado = smt1.executeQuery();
			if (resultado.next()) {
				flag = true;
			}
			resultado.close();
			smt1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean create(Connection con, String db, FormBodegaGraba form) {
		boolean flag = false;
		try {
			if(form.id_tipoBodega == (long) 1) {
				PreparedStatement smt1 = con
						.prepareStatement("insert into `"+db+"`.bodegaEmpresa (nombre,esInterna,id_cliente,id_sucursal) values (?,?,?,?);");
				smt1.setString(1, form.nombre.trim());
				smt1.setLong(2, form.id_tipoBodega);
				smt1.setLong(3, form.id_propietario);
				smt1.setLong(4, form.id_sucursal);
				smt1.executeUpdate();
				smt1.close();
				flag = true;
			}else {
				
				String pep = "";
				String ivaBodegaAux = "";
				Double ivaBodega = (double)0;
				if(form.pep!=null) {
					pep = form.pep.trim();
					ivaBodegaAux = form.ivaBodega.replace("%", "").replaceAll(",", "").trim();
					ivaBodega = Double.parseDouble(ivaBodegaAux.trim()) / 100;
				}else {
					EmisorTributario emisor = EmisorTributario.find(con, db);
					ivaBodega = emisor.getTasaIva()/100;
				}
				
				
				PreparedStatement smt1 = con
						.prepareStatement("insert into `"+db+"`.bodegaEmpresa (nombre,esInterna,id_cliente,id_proyecto," +
								" tasaCfi,cobraDiaDespacho,nDiaGraciaEnvio,nDiaGraciaRegreso,baseCalculo,tratoDevoluciones,pep,ivaBodega,id_sucursal,id_comercial) "
								+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
				smt1.setString(1, form.nombre.trim());
				smt1.setLong(2, form.id_tipoBodega);
				smt1.setLong(3, form.id_cliente);
				smt1.setLong(4, form.id_proyecto);
				String cfiAux = form.cfi.replace("%", "").replaceAll(",", "").trim();
				Double cfi = Double.parseDouble(cfiAux.trim()) / 100;
				smt1.setDouble(5, cfi);
				smt1.setLong(6, form.cobraDiaDespacho);
				smt1.setLong(7, form.nDiasEnvio);
				smt1.setLong(8, form.nDiasRegreso);
				smt1.setLong(9, form.baseCalculo);
				smt1.setLong(10, form.tratoDevoluciones);
				smt1.setString(11, pep);
				smt1.setDouble(12, ivaBodega);
				smt1.setLong(13, form.id_sucursal);
				smt1.setLong(14, form.id_comercial);
				smt1.executeUpdate();
				
				ResultSet rs1 = smt1.getGeneratedKeys();
				if(rs1.next()) {
					
					Long id_bodegaEmpresa = rs1.getLong(1);
					
					
					List<Long> idsMoneda = form.idsMoneda;
		    	  	List<Double> tasaCambio = form.tasaCambio;
		    	  	Map<Long,Double> mapAux = new HashMap<Long,Double>();
		    	  	for(int i=0; idsMoneda!=null && i<idsMoneda.size(); i++) {
		    	  		if(tasaCambio.get(i) > 0) {
		    	  			mapAux.put(idsMoneda.get(i), tasaCambio.get(i));
		    	  		}
		    	  	}
		    	  	
		    	  	if(mapAux.size() > 0) {
		    	  		String insertar = "";
			    	  	for (Map.Entry<Long, Double> entry : mapAux.entrySet()) {
			    	  		Long k = entry.getKey();
			                Double v = entry.getValue();
			    	  		insertar += "("+id_bodegaEmpresa+","+k+","+v+"),";
			    	  	}
			    	  	insertar = insertar.substring(0,insertar.length()-1);
			    	  	PreparedStatement smt3 = con
								.prepareStatement("insert into `"+db+"`.fijaTasasCambio (id_bodegaEmpresa, id_moneda, tasaCambio) values "+insertar+";");
			    		smt3.executeUpdate();
			    	  	smt3.close();
		    	  	}
		    	  	
		    	  	
				}
				rs1.close();
				smt1.close();
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			flag=false;
		}
		return(flag);
	}
	
	public static boolean update(Connection con, String db, FormBodegaGraba form) {
		boolean flag = false;
		try {
			if(form.id_tipoBodega == (long) 1) {
				PreparedStatement smt1 = con
						.prepareStatement("update `"+db+"`.bodegaEmpresa set nombre = ?, id_cliente= ? where id = ?;");
				smt1.setString(1, form.nombre.trim());
				smt1.setLong(2, form.id_propietario);
				smt1.setLong(3, form.id_bodegaEmpresa);
				smt1.executeUpdate();
				smt1.close();
				flag = true;
			}else {
				String pep = "";
				String ivaBodegaAux = "";
				Double ivaBodega = (double)0;
				if(form.pep!=null) {
					pep = form.pep.trim();
					ivaBodegaAux = form.ivaBodega.replace("%", "").replaceAll(",", "").trim();
					ivaBodega = Double.parseDouble(ivaBodegaAux.trim()) / 100;
				}else {
					EmisorTributario emisor = EmisorTributario.find(con, db);
					ivaBodega = emisor.getTasaIva()/100;
				}
				
				
				PreparedStatement smt1 = con
						.prepareStatement("update `"+db+"`.bodegaEmpresa set nombre = ?, id_cliente= ?, id_proyecto = ?, " +
								" tasaCfi = ?, cobraDiaDespacho = ?, nDiaGraciaEnvio = ?, nDiaGraciaRegreso = ?, baseCalculo=?, tratoDevoluciones=?, " +
								" id_comercial=?, pep=?, ivaBodega=? where id = ?;");
				smt1.setString(1, form.nombre.trim());
				smt1.setLong(2, form.id_cliente);
				smt1.setLong(3, form.id_proyecto);
				String cfiAux = form.cfi.replace("%", "").replaceAll(",", "").trim();
				Double cfi = Double.parseDouble(cfiAux.trim()) / 100;
				smt1.setDouble(4, cfi);
				smt1.setLong(5, form.cobraDiaDespacho);
				smt1.setLong(6, form.nDiasEnvio);
				smt1.setLong(7, form.nDiasRegreso);
				smt1.setLong(8, form.baseCalculo);
				smt1.setLong(9, form.tratoDevoluciones);
				smt1.setLong(10, form.id_comercial);
				smt1.setString(11, pep);
				smt1.setDouble(12, ivaBodega);
				smt1.setLong(13, form.id_bodegaEmpresa);
				smt1.executeUpdate();
				smt1.close();
				
				List<Long> idsMoneda = form.idsMoneda;
	    	  	List<Double> tasaCambio = form.tasaCambio;
	    	  	Map<Long,Double> mapAux = new HashMap<Long,Double>();
	    	  	for(int i=0; idsMoneda!=null && i<idsMoneda.size(); i++) {
	    	  		if(tasaCambio.get(i) > 0) {
	    	  			mapAux.put(idsMoneda.get(i), tasaCambio.get(i));
	    	  		}
	    	  	}
	    	  	
	    	  	PreparedStatement smt2 = con
						.prepareStatement("delete from `"+db+"`.fijaTasasCambio where id_bodegaEmpresa=?;");
	    	  	smt2.setLong(1, form.id_bodegaEmpresa);
	    	  	smt2.executeUpdate();
	    	  	smt2.close();
	    	  	
	    	  	if(mapAux.size() > 0) {
	    	  		String insertar = "";
		    	  	for (Map.Entry<Long, Double> entry : mapAux.entrySet()) {
		    	  		Long k = entry.getKey();
		                Double v = entry.getValue();
		    	  		insertar += "("+form.id_bodegaEmpresa+","+k+","+v+"),";
		    	  	}
		    	  	insertar = insertar.substring(0,insertar.length()-1);
		    	  	PreparedStatement smt3 = con
							.prepareStatement("insert into `"+db+"`.fijaTasasCambio (id_bodegaEmpresa, id_moneda, tasaCambio) values "+insertar+";");
		    		smt3.executeUpdate();
		    	  	smt3.close();
	    	  	}
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			flag=false;
		}
		return(flag);
	}
	
	public static List<List<String>> listaFijaTasas(Connection con, String db, Long id_bodegaEmpresa) {
		List<List<String>> lista = new ArrayList<List<String>>();
		try {
			PreparedStatement smt1 = con
					.prepareStatement("select id_moneda, tasaCambio from `"+db+"`.fijaTasasCambio where id_bodegaEmpresa = ?;");
			smt1.setLong(1, id_bodegaEmpresa);
			ResultSet rs1 = smt1.executeQuery();
			Map<Long,String> mapMoneda = Moneda.mapIdMonedaMoneda(con, db);
			while(rs1.next()) {
				String mon = mapMoneda.get(rs1.getLong(1));
				List<String> aux = new ArrayList<String>();
				aux.add(rs1.getString(1)); // id_moneda
				aux.add(rs1.getString(2)); // valor tasaCAmbo
				aux.add(mon); // nick moneda
				lista.add(aux);
			}
			rs1.close();
			smt1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static Map<String,Double> mapFijaTasasAll(Connection con, String db) {
		Map<String,Double> map = new HashMap<String,Double>();
		try {
			PreparedStatement smt1 = con
					.prepareStatement("select id_bodegaEmpresa, id_moneda, tasaCambio from `"+db+"`.fijaTasasCambio;");
			ResultSet rs1 = smt1.executeQuery();
			while(rs1.next()) {
				map.put(rs1.getString(1)+"_"+rs1.getString(2), rs1.getDouble(3));
			}
			rs1.close();
			smt1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (map);
	}
	
	public static boolean modificarBodegaPorCampo(Connection con, String db, Long id_bodegaEmpresa, String campo, String valor) {
		boolean flag = false;
		try {
			PreparedStatement smt1 = con
					.prepareStatement("update `"+db+"`.bodegaEmpresa set `"+campo+"` = ? where id = ?;");
			smt1.setString(1, valor.trim());
			smt1.setLong(2, id_bodegaEmpresa);
			smt1.executeUpdate();
			smt1.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean existeStockEnArriendo(Connection con, String db, Long id_bodegaEmpresa) {
		boolean flag = false;
		try {
			
			String aux = " and esVenta = 0 ";
			BodegaEmpresa bodegaEmpresa = BodegaEmpresa.findXIdBodega(con, db, id_bodegaEmpresa);
			if((long) bodegaEmpresa.esInterna == (long) 1) {
				aux = "";
			}
			
			PreparedStatement smt1 = con
					.prepareStatement("select id_equipo, sum(cantidad*if(id_tipoMovimiento=1,1,-1)) "+
							" from `"+db+"`.movimiento where id_bodegaEmpresa = ? "+aux+
							" group by id_equipo "+
							" having sum(cantidad*if(id_tipoMovimiento=1,1,-1)) <> 0;");
			smt1.setLong(1, id_bodegaEmpresa);
			
			ResultSet resultado = smt1.executeQuery();
			if (resultado.next()) {
				flag = true;
			}
			resultado.close();
			smt1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static Map<Long,List<String>> mapIdBod_BodegaEmpresaInternasExternas(Connection con, String db, String esPorSucursal, String id_sucursal) {
		Map<Long,List<String>> map = new HashMap<Long,List<String>>();
		List<List<String>> lista = BodegaEmpresa.listaAllBodegasSiyNoVigentesInternasExternas(con, db, esPorSucursal, id_sucursal);
		for(int i=0; i<lista.size(); i++) {
			List<String> aux = new ArrayList<String>();
			aux.add(lista.get(i).get(0)); 	// 0 es cliente interno
			aux.add(lista.get(i).get(1));  	// 1 idbodega empresa
			aux.add(lista.get(i).get(2));  	// 2 id de cliente
			aux.add(lista.get(i).get(3));  	// 3 id del proyecto
			aux.add(lista.get(i).get(4));  	// 4 tipo de cliente interno o externo
			aux.add(lista.get(i).get(5));  	// 5 nombre bodega o empresa
			aux.add(lista.get(i).get(6));  	// 6 rut del cliente
			aux.add(lista.get(i).get(7));  	// 7 nombre del cliente
			aux.add(lista.get(i).get(8));  	// 8 nombre del proyecto
			aux.add(lista.get(i).get(9));  	// 9 comuna
			aux.add(lista.get(i).get(11));  // 10 comercial
			aux.add(lista.get(i).get(1));  	// 11 idBodegaEmpresa
			aux.add(lista.get(i).get(12));  // 12 pep
			aux.add(lista.get(i).get(13));  // 13 ivaBodega
			aux.add(lista.get(i).get(15));  // 14 nameSucursal
			map.put(Long.parseLong(lista.get(i).get(1)), aux);
		}
		return (map);
	}
	
	public static List<List<String>> listaAllBodegasSiyNoVigentesInternasExternas(Connection con, String db, String esPorSucursal, String id_sucursal) {
		List<List<String>> lista = new ArrayList<List<String>>();
		
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " where bodegaEmpresa.id_sucursal = " + id_sucursal;
		}
		
		try {
			PreparedStatement smt = con
					.prepareStatement(" select  " +
							" bodegaEmpresa.esInterna,   " +
							" bodegaEmpresa.id,   " +
							" ifnull(cliente.id,0),   " +
							" ifnull(proyecto.id,0),   " +
							" bodegaEmpresa.nombre,     " +
							" ifnull(cliente.rut,''),   " +
							" ifnull(cliente.nickName,''),   " +
							" ifnull(proyecto.nickName,''),   " +
							" ifnull(comunas.nombre,''),   " +
							" tipoBodega.nombre," +
							" ifnull(bodegaEmpresa.tasaDescto,'0.00 %'),  " +
							" ifnull(comercial,''), " +
							" ifnull(bodegaEmpresa.pep,''), "+
							" ifnull(bodegaEmpresa.ivaBodega,0), "+
							" bodegaEmpresa.id_sucursal, "+
							" bodegaEmpresa.id_comercial "+
							" from `"+db+"`.bodegaEmpresa    " +
							" left join `"+db+"`.cliente on cliente.id = bodegaEmpresa.id_cliente   " +
							" left join `"+db+"`.proyecto on proyecto.id = bodegaEmpresa.id_proyecto   " +
							" left join `"+db+"`.comunas on comunas.codigo = proyecto.cod_comuna   " +
							" left join `"+db+"`.tipoBodega on tipoBodega.id = bodegaEmpresa.esInterna " +
							condSucursal +
							" order by bodegaEmpresa.esInterna,bodegaEmpresa.nombre;"); 
			ResultSet rs = smt.executeQuery();
			Map<Long, Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
			while (rs.next()) {
				String nameSucursal = "";
				Sucursal sucursal = mapSucursal.get(rs.getLong(15));
				if(sucursal!=null) {
					nameSucursal = sucursal.getNombre();
				}
				String nameComercial = "";
				Comercial comercial = mapComercial.get(rs.getLong(16));
				if(comercial!=null) {
					nameComercial = comercial.getNameUsuario();
				}else {
					nameComercial = rs.getString(12);
				}
				List<String> aux = new ArrayList<String>();
				aux.add(rs.getString(1)); 			// 0 es cliente interno
				aux.add(rs.getString(2));  			// 1 idbodega empresa
				aux.add(rs.getString(3));  			// 2 id de cliente
				aux.add(rs.getString(4));  			// 3 id del proyecto
				aux.add(rs.getString(10));  		// 4 tipo de cliente interno o externo
				aux.add(rs.getString(5));  			// 5 nombre bodega o empresa
				aux.add(rs.getString(6));  			// 6 rut del cliente
				aux.add(rs.getString(7));  			// 7 nombre del cliente
				aux.add(rs.getString(8));  			// 8 nombre del proyecto
				aux.add(rs.getString(9));  			// 9 comuna
				String tasa = rs.getString(11);
				if(!tasa.equals("0.00 %")){
					tasa = myformatdouble2.format(rs.getDouble(11)*100) + " %";
				}
				aux.add(tasa);  					// 10 tasa de descuento global
				aux.add(nameComercial);			// 11 comercial
				aux.add(rs.getString(13));  		// 12 pep
				aux.add(rs.getString(14));  		// 13 ivaBodega
				aux.add(rs.getString(15));  		// 14 id sucursal
				aux.add(nameSucursal);  		// 15 nameSucursal
				lista.add(aux);
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static boolean esInterna(Connection con, String db, Long id_bodegaEmpresa) {
		boolean flag = false;
		try {
			PreparedStatement smt1 = con
					.prepareStatement("Select esInterna from `"+db+"`.bodegaEmpresa where id = ?;");
			smt1.setLong(1, id_bodegaEmpresa);
			ResultSet rs = smt1.executeQuery();
			if (rs.next()) {
				if(rs.getLong(1) == (long)1) {
					flag = true;
				}
			}
			rs.close();
			smt1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static Map<Long,List<String>> mapIdBod_BodegaEmpresaExternas(Connection con, String db, String esPorSucursal, String id_sucursal) {
		Map<Long,List<String>> map = new HashMap<Long,List<String>>();
		List<List<String>> lista = BodegaEmpresa.listaAllBodegasSiyNoVigentesExternas(con, db, esPorSucursal, id_sucursal);
		for(int i=0; i<lista.size(); i++) {
			List<String> aux = new ArrayList<String>();
			aux.add(lista.get(i).get(0)); 	// 0 es cliente interno
			aux.add(lista.get(i).get(1));  	// 1 idbodega empresa
			aux.add(lista.get(i).get(2));  	// 2 id de cliente
			aux.add(lista.get(i).get(3));  	// 3 id del proyecto
			aux.add(lista.get(i).get(4));  	// 4 tipo de cliente interno o externo
			aux.add(lista.get(i).get(5));  	// 5 nombre bodega o empresa
			aux.add(lista.get(i).get(6));  	// 6 rut del cliente
			aux.add(lista.get(i).get(7));  	// 7 nombre del cliente
			aux.add(lista.get(i).get(8));  	// 8 nombre del proyecto
			aux.add(lista.get(i).get(9));  	// 9 comuna
			aux.add(lista.get(i).get(11));  // 10 comercial
			aux.add(lista.get(i).get(1));  	// 11 idBodegaEmpresa
			aux.add(lista.get(i).get(12));  // 12 pep
			aux.add(lista.get(i).get(13));  // 13 ivaBodega
			aux.add(lista.get(i).get(15));  // 14 namesucursal
			map.put(Long.parseLong(lista.get(i).get(1)), aux);
		}
		return (map);
	}
	
	
	public static List<List<String>> listaAllBodegasSiyNoVigentesExternas(Connection con, String db, String esPorSucursal, String id_sucursal) {
		List<List<String>> lista = new ArrayList<List<String>>();
		
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " and bodegaEmpresa.id_sucursal = " + id_sucursal;
		}
		
		try {
			PreparedStatement smt = con
					.prepareStatement(" select  " +
							" bodegaEmpresa.esInterna,   " +
							" bodegaEmpresa.id,   " +
							" ifnull(cliente.id,0),   " +
							" ifnull(proyecto.id,0),   " +
							" bodegaEmpresa.nombre,     " +
							" ifnull(cliente.rut,''),   " +
							" ifnull(cliente.nickName,''),   " +
							" ifnull(proyecto.nickName,''),   " +
							" ifnull(comunas.nombre,''),   " +
							" tipoBodega.nombre," +
							" ifnull(bodegaEmpresa.tasaDescto,'0.00 %'),  " +
							" ifnull(comercial,''), " +
							" ifnull(bodegaEmpresa.pep,''), "+
							" ifnull(bodegaEmpresa.ivaBodega,0), "+
							" bodegaEmpresa.id_sucursal, "+
							" bodegaEmpresa.id_comercial "+
							" from `"+db+"`.bodegaEmpresa    " +
							" left join `"+db+"`.cliente on cliente.id = bodegaEmpresa.id_cliente   " +
							" left join `"+db+"`.proyecto on proyecto.id = bodegaEmpresa.id_proyecto   " +
							" left join `"+db+"`.comunas on comunas.codigo = proyecto.cod_comuna   " +
							" left join `"+db+"`.tipoBodega on tipoBodega.id = bodegaEmpresa.esInterna " +
							" where bodegaEmpresa.esInterna <> 1 " + condSucursal +
							" order by bodegaEmpresa.esInterna,bodegaEmpresa.nombre;"); 
			ResultSet rs = smt.executeQuery();
			Map<Long, Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
			while (rs.next()) {
				String nameSucursal = "";
				Sucursal sucursal = mapSucursal.get(rs.getLong(15));
				if(sucursal!=null) {
					nameSucursal = sucursal.getNombre();
				}
				String nameComercial = "";
				Comercial comercial = mapComercial.get(rs.getLong(16));
				if(comercial!=null) {
					nameComercial = comercial.getNameUsuario();
				}else {
					nameComercial = rs.getString(12);
				}
				List<String> aux = new ArrayList<String>();
				aux.add(rs.getString(1)); 			// 0 es cliente interno
				aux.add(rs.getString(2));  			// 1 idbodega empresa
				aux.add(rs.getString(3));  			// 2 id de cliente
				aux.add(rs.getString(4));  			// 3 id del proyecto
				aux.add(rs.getString(10));  		// 4 tipo de cliente interno o externo
				aux.add(rs.getString(5)); 			// 5 nombre bodega o empresa
				aux.add(rs.getString(6)); 			// 6 rut del cliente
				aux.add(rs.getString(7)); 			// 7 nombre del cliente
				aux.add(rs.getString(8)); 			// 8 nombre del proyecto
				aux.add(rs.getString(9));  			// 9 comuna
				String tasa = rs.getString(11);
				if(!tasa.equals("0.00 %")){
					tasa = myformatdouble2.format(rs.getDouble(11)*100) + " %";
				}
				aux.add(tasa);  					// 10 tasa de descuento global
				aux.add(nameComercial);			// 11 comercial
				aux.add(rs.getString(13));  		// 12 pep
				aux.add(rs.getString(14));  		// 13 ivaBodega
				aux.add(rs.getString(15));  		// 14 id sucursal
				aux.add(nameSucursal);  		// 15 nameSucursal
				lista.add(aux);
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}

	public static List<List<String>> listaAllBodegasVigentesExternasConAjustes(Connection con, String db, String permisoPorBodega, String esPorSucursal, String id_sucursal) {
		List<List<String>> lista = new ArrayList<List<String>>();
		
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " and bodegaEmpresa.id_sucursal = " + id_sucursal;
		}
		
		try {
			
			PreparedStatement smt100 = con
					.prepareStatement("select distinct id_bodegaEmpresa from `"+db+"`.ajustesEP;");
			ResultSet rs100 = smt100.executeQuery();
			List<String> auxLista = new ArrayList<String>();
			while (rs100.next()) {
				auxLista.add(rs100.getString(1));
			}
			rs100.close();smt100.close();
			String condicion = auxLista.toString();
			condicion = condicion.replace("[", "").replace("]", "");
			if(condicion.length()>0) {
				condicion = " and bodegaEmpresa.id in ("+condicion+") ";
			}else {
				condicion="";
			}
			
			PreparedStatement smt = con
					.prepareStatement(" select  " +
							" bodegaEmpresa.esInterna,   " +
							" bodegaEmpresa.id,   " +
							" ifnull(cliente.id,0),   " +
							" ifnull(proyecto.id,0),   " +
							" bodegaEmpresa.nombre,     " +
							" ifnull(cliente.rut,''),   " +
							" ifnull(cliente.nickName,''),   " +
							" ifnull(proyecto.nickName,''),   " +
							" ifnull(comunas.nombre,''),   " +
							" tipoBodega.nombre," +
							" ifnull(bodegaEmpresa.tasaDescto,'0.00 %'),  " +
							" ifnull(comercial,''), " +
							" ifnull(bodegaEmpresa.pep,''), "+
							" ifnull(bodegaEmpresa.ivaBodega,0), "+
							" bodegaEmpresa.id_sucursal, "+
							" bodegaEmpresa.id_comercial "+
							" from `"+db+"`.bodegaEmpresa    " +
							" left join `"+db+"`.cliente on cliente.id = bodegaEmpresa.id_cliente   " +
							" left join `"+db+"`.proyecto on proyecto.id = bodegaEmpresa.id_proyecto   " +
							" left join `"+db+"`.comunas on comunas.codigo = proyecto.cod_comuna   " +
							" left join `"+db+"`.tipoBodega on tipoBodega.id = bodegaEmpresa.esInterna " +
							" where bodegaEmpresa.vigente = 1 and bodegaEmpresa.esInterna = 2   " + permisoPorBodega + condicion + condSucursal +
							" order by bodegaEmpresa.esInterna,bodegaEmpresa.nombre;"); 
			ResultSet rs = smt.executeQuery();
			Map<Long, Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
			while (rs.next()) {
				String nameSucursal = "";
				Sucursal sucursal = mapSucursal.get(rs.getLong(15));
				if(sucursal!=null) {
					nameSucursal = sucursal.getNombre();
				}
				String nameComercial = "";
				Comercial comercial = mapComercial.get(rs.getLong(16));
				if(comercial!=null) {
					nameComercial = comercial.getNameUsuario();
				}else {
					nameComercial = rs.getString(12);
				}
				List<String> aux = new ArrayList<String>();
				aux.add(rs.getString(1)); 		// 0 es cliente interno
				aux.add(rs.getString(2));  		// 1 idbodega empresa
				aux.add(rs.getString(3));  		// 2 id de cliente
				aux.add(rs.getString(4));  		// 3 id del proyecto
				aux.add(rs.getString(10));  	// 4 tipo de cliente interno o externo
				aux.add(rs.getString(5));  		// 5 nombre bodega o empresa
				aux.add(rs.getString(6));  		// 6 rut del cliente
				aux.add(rs.getString(7));  		// 7 nombre del cliente
				aux.add(rs.getString(8));  		// 8 nombre del proyecto
				aux.add(rs.getString(9));  		// 9 comuna
				String tasa = rs.getString(11);
				if(!tasa.equals("0.00 %")){
					tasa = myformatdouble2.format(rs.getDouble(11)*100) + " %";
				}
				aux.add(tasa);  					// 10 tasa de descuento global
				aux.add(nameComercial);			// 11 comercial
				aux.add(rs.getString(13));  		// 12 pep
				aux.add(rs.getString(14));  		// 13 ivaBodega
				aux.add(rs.getString(15));  		// 14 id sucursal
				aux.add(nameSucursal);  		// 15 nameSucursal
				lista.add(aux);
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<List<String>> listaAllBodegasVigentesExternasConAjustesOdo(Connection con, String db, String esPorSucursal, String id_sucursal) {
		List<List<String>> lista = new ArrayList<List<String>>();
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " and bodegaEmpresa.id_sucursal = "+id_sucursal;
		}
		try {
			
			PreparedStatement smt100 = con
					.prepareStatement("select distinct id_bodegaEmpresa from `"+db+"`.ajustesEpOdo;");
			ResultSet rs100 = smt100.executeQuery();
			List<String> auxLista = new ArrayList<String>();
			while (rs100.next()) {
				auxLista.add(rs100.getString(1));
			}
			rs100.close();smt100.close();
			String condicion = auxLista.toString();
			condicion = condicion.replace("[", "").replace("]", "");
			if(condicion.length()>0) {
				condicion = " and bodegaEmpresa.id in ("+condicion+") ";
			}else {
				condicion="";
			}
			
			PreparedStatement smt = con
					.prepareStatement(" select  " +
							" bodegaEmpresa.esInterna,   " +
							" bodegaEmpresa.id,   " +
							" ifnull(cliente.id,0),   " +
							" ifnull(proyecto.id,0),   " +
							" bodegaEmpresa.nombre,     " +
							" ifnull(cliente.rut,''),   " +
							" ifnull(cliente.nickName,''),   " +
							" ifnull(proyecto.nickName,''),   " +
							" ifnull(comunas.nombre,''),   " +
							" tipoBodega.nombre," +
							" ifnull(bodegaEmpresa.tasaDescto,'0.00 %'),  " +
							" ifnull(comercial,''), " +
							" ifnull(bodegaEmpresa.pep,''), "+
							" ifnull(bodegaEmpresa.ivaBodega,0), "+
							" bodegaEmpresa.id_sucursal, "+
							" bodegaEmpresa.id_comercial "+
							" from `"+db+"`.bodegaEmpresa    " +
							" left join `"+db+"`.cliente on cliente.id = bodegaEmpresa.id_cliente   " +
							" left join `"+db+"`.proyecto on proyecto.id = bodegaEmpresa.id_proyecto   " +
							" left join `"+db+"`.comunas on comunas.codigo = proyecto.cod_comuna   " +
							" left join `"+db+"`.tipoBodega on tipoBodega.id = bodegaEmpresa.esInterna " +
							" where bodegaEmpresa.vigente = 1 and bodegaEmpresa.esInterna = 2   " + condicion + condSucursal +
							" order by bodegaEmpresa.esInterna,bodegaEmpresa.nombre;"); 
			ResultSet rs = smt.executeQuery();
			Map<Long, Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
			while (rs.next()) {
				String nameSucursal = "";
				Sucursal sucursal = mapSucursal.get(rs.getLong(15));
				if(sucursal!=null) {
					nameSucursal = sucursal.getNombre();
				}
				String nameComercial = "";
				Comercial comercial = mapComercial.get(rs.getLong(16));
				if(comercial!=null) {
					nameComercial = comercial.getNameUsuario();
				}else {
					nameComercial = rs.getString(12);
				}
				List<String> aux = new ArrayList<String>();
				aux.add(rs.getString(1)); 		// 0 es cliente interno
				aux.add(rs.getString(2));  		// 1 idbodega empresa
				aux.add(rs.getString(3));  		// 2 id de cliente
				aux.add(rs.getString(4));  		// 3 id del proyecto
				aux.add(rs.getString(10));  	// 4 tipo de cliente interno o externo
				aux.add(rs.getString(5));  		// 5 nombre bodega o empresa
				aux.add(rs.getString(6));  		// 6 rut del cliente
				aux.add(rs.getString(7));  		// 7 nombre del cliente
				aux.add(rs.getString(8));  		// 8 nombre del proyecto
				aux.add(rs.getString(9));  		// 9 comuna
				String tasa = rs.getString(11);
				if(!tasa.equals("0.00 %")){
					tasa = myformatdouble2.format(rs.getDouble(11)*100) + " %";
				}
				aux.add(tasa);  					// 10 tasa de descuento global
				aux.add(nameComercial);			// 11 comercial
				aux.add(rs.getString(13));  		// 12 pep
				aux.add(rs.getString(14));  		// 13 ivaBodega
				aux.add(nameSucursal);  		// 14 nameSucursal
				lista.add(aux);
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	

}
