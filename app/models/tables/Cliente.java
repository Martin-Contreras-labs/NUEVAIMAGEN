package models.tables;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

import models.forms.FormClienteGraba;
import models.utilities.Archivos;
import models.utilities.Fechas;



public class Cliente {
	public Long id;
	public String rut;
	public String nombre; //VARCHAR(100)
	public String nickName; //VARCHAR(20) not null
	public String direccion;  //VARCHAR(100)
	public String cod_region;
	public String cod_comuna;
	public String giro;
	public String ciudad;
	public String contactoFactura;
	public String mailFactura;
	
	public String cod_medioPago; //3 
	public String cod_tipoCuenta; //3 
	public String ctaPago; //20
	public String bcoPago; //40 
	public int diasVencPago; 
	public String cod_termPago; //4
	public String glosaPago; //100
	
	public String region;
	public String comuna;
	
	public String medioPago;
	public String tipoCuenta;
	public String termPago;
	
	public String fonoContacto;
	
	public String rutRepresentante1;
	public String nombreRepresentante1; //VARCHAR(100)
	public String rutRepresentante2;
	public String nombreRepresentante2; //VARCHAR(100)
	
	public String cargoContactoFactura; // VARCHAR(100)
	public String cargoRepresentante1;  // VARCHAR(100)
	public String cargoRepresentante2; // VARCHAR(100)
	
	public String formaDePago;
	public String especialidad;
	
	public Long vigente;

	public Cliente(Long id, String rut, String nombre, String nickName, String direccion, String cod_region,
			String cod_comuna, String giro, String ciudad, String contactoFactura, String mailFactura,
			String cod_medioPago, String cod_tipoCuenta, String ctaPago, String bcoPago, int diasVencPago,
			String cod_termPago, String glosaPago, String region, String comuna, String medioPago, String tipoCuenta,
			String termPago, String fonoContacto, String rutRepresentante1, String nombreRepresentante1,
			String rutRepresentante2, String nombreRepresentante2, String cargoContactoFactura,
			String cargoRepresentante1, String cargoRepresentante2,String formaDePago,String especialidad,Long vigente) {
		super();
		this.id = id;
		this.rut = rut;
		this.nombre = nombre;
		this.nickName = nickName;
		this.direccion = direccion;
		this.cod_region = cod_region;
		this.cod_comuna = cod_comuna;
		this.giro = giro;
		this.ciudad = ciudad;
		this.contactoFactura = contactoFactura;
		this.mailFactura = mailFactura;
		this.cod_medioPago = cod_medioPago;
		this.cod_tipoCuenta = cod_tipoCuenta;
		this.ctaPago = ctaPago;
		this.bcoPago = bcoPago;
		this.diasVencPago = diasVencPago;
		this.cod_termPago = cod_termPago;
		this.glosaPago = glosaPago;
		this.region = region;
		this.comuna = comuna;
		this.medioPago = medioPago;
		this.tipoCuenta = tipoCuenta;
		this.termPago = termPago;
		this.fonoContacto = fonoContacto;
		this.rutRepresentante1 = rutRepresentante1;
		this.nombreRepresentante1 = nombreRepresentante1;
		this.rutRepresentante2 = rutRepresentante2;
		this.nombreRepresentante2 = nombreRepresentante2;
		this.cargoContactoFactura = cargoContactoFactura;
		this.cargoRepresentante1 = cargoRepresentante1;
		this.cargoRepresentante2 = cargoRepresentante2;
		this.formaDePago = formaDePago;
		this.especialidad = especialidad;
		this.vigente = vigente;
	}


	public Cliente() {
		super();
	}
	
	
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getRut() {return rut;}
	public void setRut(String rut) {this.rut = rut;}
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}
	public String getNickName() {return nickName;}
	public void setNickName(String nickName) {this.nickName = nickName;}
	public String getDireccion() {return direccion;}
	public void setDireccion(String direccion) {this.direccion = direccion;}
	public String getCod_region() {return cod_region;}
	public void setCod_region(String cod_region) {this.cod_region = cod_region;}
	public String getCod_comuna() {return cod_comuna;}
	public void setCod_comuna(String cod_comuna) {this.cod_comuna = cod_comuna;}
	public String getRegion() {return region;}
	public void setRegion(String region) {this.region = region;}
	public String getComuna() {return comuna;}
	public void setComuna(String comuna) {this.comuna = comuna;}
	public String getCiudad() {return ciudad;}
	public void setCiudad(String ciudad) {this.ciudad = ciudad;}
	public String getGiro() {return giro;}
	public void setGiro(String giro) {this.giro = giro;}
	public String getContactoFactura() {return contactoFactura;}
	public void setContactoFactura(String contactoFactura) {this.contactoFactura = contactoFactura;}
	public String getMailFactura() {return mailFactura;}
	public void setMailFactura(String mailFactura) {this.mailFactura = mailFactura;}
	public String getCod_medioPago() {return cod_medioPago;}
	public void setCod_medioPago(String cod_medioPago) {this.cod_medioPago = cod_medioPago;}
	public String getCod_tipoCuenta() {return cod_tipoCuenta;}
	public void setCod_tipoCuenta(String cod_tipoCuenta) {this.cod_tipoCuenta = cod_tipoCuenta;}
	public String getCtaPago() {return ctaPago;}
	public void setCtaPago(String ctaPago) {this.ctaPago = ctaPago;}
	public String getBcoPago() {return bcoPago;}
	public void setBcoPago(String bcoPago) {this.bcoPago = bcoPago;}
	public int getDiasVencPago() {return diasVencPago;}
	public void setDiasVencPago(int diasVencPago) {this.diasVencPago = diasVencPago;}
	public String getGlosaPago() {return glosaPago;}
	public void setGlosaPago(String glosaPago) {this.glosaPago = glosaPago;}
	public String getMedioPago() {return medioPago;}
	public void setMedioPago(String medioPago) {this.medioPago = medioPago;}
	public String getTipoCuenta() {return tipoCuenta;}
	public void setTipoCuenta(String tipoCuenta) {this.tipoCuenta = tipoCuenta;}
	public String getCod_termPago() {return cod_termPago;}
	public void setCod_termPago(String cod_termPago) {this.cod_termPago = cod_termPago;}
	public String getTermPago() {return termPago;}
	public void setTermPago(String termPago) {this.termPago = termPago;}
	public String getFonoContacto() {return fonoContacto;}
	public void setFonoContacto(String fonoContacto) {this.fonoContacto = fonoContacto;}
	public String getRutRepresentante1() {return rutRepresentante1;}
	public void setRutRepresentante1(String rutRepresentante1) {this.rutRepresentante1 = rutRepresentante1;}
	public String getNombreRepresentante1() {return nombreRepresentante1;}
	public void setNombreRepresentante1(String nombreRepresentante1) {this.nombreRepresentante1 = nombreRepresentante1;}
	public String getRutRepresentante2() {return rutRepresentante2;}
	public void setRutRepresentante2(String rutRepresentante2) {this.rutRepresentante2 = rutRepresentante2;}
	public String getNombreRepresentante2() {return nombreRepresentante2;}
	public void setNombreRepresentante2(String nombreRepresentante2) {this.nombreRepresentante2 = nombreRepresentante2;}
	public String getCargoContactoFactura() {return cargoContactoFactura;}
	public void setCargoContactoFactura(String cargoContactoFactura) {this.cargoContactoFactura = cargoContactoFactura;}
	public String getCargoRepresentante1() {return cargoRepresentante1;}
	public void setCargoRepresentante1(String cargoRepresentante1) {this.cargoRepresentante1 = cargoRepresentante1;}
	public String getCargoRepresentante2() {return cargoRepresentante2;}
	public void setCargoRepresentante2(String cargoRepresentante2) {this.cargoRepresentante2 = cargoRepresentante2;}

	public String getFormaDePago() {
		return formaDePago;
	}

	public void setFormaDePago(String formaDePago) {
		this.formaDePago = formaDePago;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public Long getVigente() {
		return vigente;
	}

	public void setVigente(Long vigente) {
		this.vigente = vigente;
	}


	public static Map<Long,Cliente> mapAllClientes(Connection con, String db){
		Map<Long,Cliente> map = new HashMap<Long,Cliente>();
		List<Cliente> listClientes = Cliente.all(con, db);
		listClientes.forEach(x->{
			map.put(x.getId(), x);
		});
		return(map);
	}
	
	public static Map<String,Cliente> mapAllClientesPorRut(Connection con, String db){
		Map<String,Cliente> map = new HashMap<String,Cliente>();
		List<Cliente> listClientes = Cliente.all(con, db);
		listClientes.forEach(x->{
			map.put(x.getRut(), x);
		});
		return(map);
	}
	
	public static List<Cliente> all(Connection con, String db) {
		List<Cliente> lista = new ArrayList<Cliente>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select " + 
							" cliente.id, " + 
							" cliente.rut, " + 
							" cliente.nombre, " + 
							" cliente.nickName, " + 
							" cliente.direccion, " + 
							" cliente.cod_region, " + 
							" cliente.cod_comuna, " + 
							" cliente.giro,  " + 
							" cliente.ciudad, " + 
							" cliente.contactoFactura, " + 
							" cliente.mailFactura," + 
							" cliente.cod_medioPago, " + 
							" cliente.cod_tipoCuenta, " + 
							" cliente.ctaPago, " + 
							" cliente.bcoPago, " + 
							" cliente.diasVencPago," + 
							" cliente.cod_termPago, " + 
							" cliente.glosaPago, " + 
							" ifnull(regiones.nombre,'--'), " + 
							" ifnull(comunas.nombre,'--'), " + 
							" ifnull(medioPago.nombre,'--'), " + 
							" ifnull(tipoCuenta.nombre,'--'), " + 
							" ifnull(termPago.nombre,'--'), " + 
							" cliente.fonoContacto, " +
							" cliente.rutRepresentante1, " +
							" cliente.nombreRepresentante1, " +
							" cliente.rutRepresentante2, " +
							" cliente.nombreRepresentante2, " +
							" cliente.cargoContactoFactura, " +
							" cliente.cargoRepresentante1, " +
							" cliente.cargoRepresentante2, " +
							" cliente.formaDePago, " +
							" cliente.especialidad, " +
							" cliente.vigente " +
							" from `"+db+"`.cliente " + 
							" left join `"+db+"`.regiones on regiones.codigo = cliente.cod_region " + 
							" left join `"+db+"`.comunas on comunas.codigo = cliente.cod_comuna " + 
							" left join `"+db+"`.medioPago on medioPago.codigo = cliente.cod_medioPago " + 
							" left join `"+db+"`.tipoCuenta on tipoCuenta.codigo = cliente.cod_tipoCuenta " + 
							" left join `"+db+"`.termPago on termPago.codigo = cliente.cod_termPago  " +
							" order by nickName;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {		
				lista.add(new Cliente(rs.getLong(1),rs.getString(2),rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),
						rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15),
						rs.getInt(16),rs.getString(17),rs.getString(18),rs.getString(19),rs.getString(20),rs.getString(21),
						rs.getString(22),rs.getString(23),rs.getString(24),
						rs.getString(25),rs.getString(26),rs.getString(27),rs.getString(28),
						rs.getString(29),rs.getString(30),rs.getString(31),rs.getString(32),rs.getString(33),rs.getLong(34)));
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<Cliente> allsoloVigentes(Connection con, String db) {
		List<Cliente> lista = new ArrayList<Cliente>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select " + 
							" cliente.id, " + 
							" cliente.rut, " + 
							" cliente.nombre, " + 
							" cliente.nickName, " + 
							" cliente.direccion, " + 
							" cliente.cod_region, " + 
							" cliente.cod_comuna, " + 
							" cliente.giro,  " + 
							" cliente.ciudad, " + 
							" cliente.contactoFactura, " + 
							" cliente.mailFactura," + 
							" cliente.cod_medioPago, " + 
							" cliente.cod_tipoCuenta, " + 
							" cliente.ctaPago, " + 
							" cliente.bcoPago, " + 
							" cliente.diasVencPago," + 
							" cliente.cod_termPago, " + 
							" cliente.glosaPago, " + 
							" ifnull(regiones.nombre,'--'), " + 
							" ifnull(comunas.nombre,'--'), " + 
							" ifnull(medioPago.nombre,'--'), " + 
							" ifnull(tipoCuenta.nombre,'--'), " + 
							" ifnull(termPago.nombre,'--'), " + 
							" cliente.fonoContacto, " +
							" cliente.rutRepresentante1, " +
							" cliente.nombreRepresentante1, " +
							" cliente.rutRepresentante2, " +
							" cliente.nombreRepresentante2, " +
							" cliente.cargoContactoFactura, " +
							" cliente.cargoRepresentante1, " +
							" cliente.cargoRepresentante2, " +
							" cliente.formaDePago, " +
							" cliente.especialidad, " +
							" cliente.vigente " +
							" from `"+db+"`.cliente " + 
							" left join `"+db+"`.regiones on regiones.codigo = cliente.cod_region " + 
							" left join `"+db+"`.comunas on comunas.codigo = cliente.cod_comuna " + 
							" left join `"+db+"`.medioPago on medioPago.codigo = cliente.cod_medioPago " + 
							" left join `"+db+"`.tipoCuenta on tipoCuenta.codigo = cliente.cod_tipoCuenta " + 
							" left join `"+db+"`.termPago on termPago.codigo = cliente.cod_termPago  " +
							" where cliente.vigente=1 " +
							" order by nickName;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {		
				lista.add(new Cliente(rs.getLong(1),rs.getString(2),rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),
						rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15),
						rs.getInt(16),rs.getString(17),rs.getString(18),rs.getString(19),rs.getString(20),rs.getString(21),
						rs.getString(22),rs.getString(23),rs.getString(24),
						rs.getString(25),rs.getString(26),rs.getString(27),rs.getString(28),
						rs.getString(29),rs.getString(30),rs.getString(31),rs.getString(32),rs.getString(33),rs.getLong(34)));
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	
	
	public static Cliente find(Connection con, String db, Long id_cliente) {
		Cliente aux = null;
		try {
			PreparedStatement smt = con
					.prepareStatement("select " + 
							" cliente.id, " + 
							" cliente.rut, " + 
							" cliente.nombre, " + 
							" cliente.nickName, " + 
							" cliente.direccion, " + 
							" cliente.cod_region, " + 
							" cliente.cod_comuna, " + 
							" cliente.giro,  " + 
							" cliente.ciudad, " + 
							" cliente.contactoFactura, " + 
							" cliente.mailFactura," + 
							" cliente.cod_medioPago, " + 
							" cliente.cod_tipoCuenta, " + 
							" cliente.ctaPago, " + 
							" cliente.bcoPago, " + 
							" cliente.diasVencPago," + 
							" cliente.cod_termPago, " + 
							" cliente.glosaPago, " + 
							" ifnull(regiones.nombre,''), " + 
							" ifnull(comunas.nombre,''), " + 
							" ifnull(medioPago.nombre,''), " + 
							" ifnull(tipoCuenta.nombre,''), " + 
							" ifnull(termPago.nombre,''), " + 
							" cliente.fonoContacto, " +
							" cliente.rutRepresentante1, " +
							" cliente.nombreRepresentante1, " +
							" cliente.rutRepresentante2, " +
							" cliente.nombreRepresentante2, " +
							" cliente.cargoContactoFactura, " +
							" cliente.cargoRepresentante1, " +
							" cliente.cargoRepresentante2, " +
							" cliente.formaDePago, " +
							" cliente.especialidad, " +
							" cliente.vigente " +
							" from `"+db+"`.cliente " + 
							" left join `"+db+"`.regiones on regiones.codigo = cliente.cod_region " + 
							" left join `"+db+"`.comunas on comunas.codigo = cliente.cod_comuna " + 
							" left join `"+db+"`.medioPago on medioPago.codigo = cliente.cod_medioPago " + 
							" left join `"+db+"`.tipoCuenta on tipoCuenta.codigo = cliente.cod_tipoCuenta " + 
							" left join `"+db+"`.termPago on termPago.codigo = cliente.cod_termPago  " +
							" where cliente.id = ?;" );
			smt.setLong(1, id_cliente);
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {				
				aux = new Cliente(rs.getLong(1),rs.getString(2),rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),
						rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15),
						rs.getInt(16),rs.getString(17),rs.getString(18),rs.getString(19),rs.getString(20),rs.getString(21),
						rs.getString(22),rs.getString(23),rs.getString(24),
						rs.getString(25),rs.getString(26),rs.getString(27),rs.getString(28),
						rs.getString(29),rs.getString(30),rs.getString(31),rs.getString(32),rs.getString(33),rs.getLong(34));
			}else{
				aux = new Cliente((long) 0,"","","","","","","","","","","","","","",0,"","","","","","","","","","","","","","","","","",(long) 1);
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (aux);
	}
	
	public static Cliente findPorNickName(Connection con, String db, String nickName) {
		Cliente aux = null;
		try {
			PreparedStatement smt = con
					.prepareStatement("select " + 
							" cliente.id, " + 
							" cliente.rut, " + 
							" cliente.nombre, " + 
							" cliente.nickName, " + 
							" cliente.direccion, " + 
							" cliente.cod_region, " + 
							" cliente.cod_comuna, " + 
							" cliente.giro,  " + 
							" cliente.ciudad, " + 
							" cliente.contactoFactura, " + 
							" cliente.mailFactura," + 
							" cliente.cod_medioPago, " + 
							" cliente.cod_tipoCuenta, " + 
							" cliente.ctaPago, " + 
							" cliente.bcoPago, " + 
							" cliente.diasVencPago," + 
							" cliente.cod_termPago, " + 
							" cliente.glosaPago, " + 
							" ifnull(regiones.nombre,'--'), " + 
							" ifnull(comunas.nombre,'--'), " + 
							" ifnull(medioPago.nombre,'--'), " + 
							" ifnull(tipoCuenta.nombre,'--'), " + 
							" ifnull(termPago.nombre,'--'), " + 
							" cliente.fonoContacto, " +
							" cliente.rutRepresentante1, " +
							" cliente.nombreRepresentante1, " +
							" cliente.rutRepresentante2, " +
							" cliente.nombreRepresentante2, " +
							" cliente.cargoContactoFactura, " +
							" cliente.cargoRepresentante1, " +
							" cliente.cargoRepresentante2, " +
							" cliente.formaDePago, " +
							" cliente.especialidad, " +
							" cliente.vigente " +
							" from `"+db+"`.cliente " + 
							" left join `"+db+"`.regiones on regiones.codigo = cliente.cod_region " + 
							" left join `"+db+"`.comunas on comunas.codigo = cliente.cod_comuna " + 
							" left join `"+db+"`.medioPago on medioPago.codigo = cliente.cod_medioPago " + 
							" left join `"+db+"`.tipoCuenta on tipoCuenta.codigo = cliente.cod_tipoCuenta " + 
							" left join `"+db+"`.termPago on termPago.codigo = cliente.cod_termPago  " +
							" where cliente.nickName = ?;" );
			smt.setString(1, nickName);
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {				
				aux = new Cliente(rs.getLong(1),rs.getString(2),rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),
						rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15),
						rs.getInt(16),rs.getString(17),rs.getString(18),rs.getString(19),rs.getString(20),rs.getString(21),
						rs.getString(22),rs.getString(23),rs.getString(24),
						rs.getString(25),rs.getString(26),rs.getString(27),rs.getString(28),
						rs.getString(29),rs.getString(30),rs.getString(31),rs.getString(32),rs.getString(33),rs.getLong(34));
			}else{
				aux = new Cliente((long) 0,"","","","","","","","","","","","","","",0,"","","","","","","","","","","","","","","","","",(long) 1);
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (aux);
	}
	
	public static boolean existeNickName(Connection con, String db, String nickName) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("select id from `"+db+"`.cliente where upper(nickName)=?");
			smt.setString(1, nickName.toUpperCase());
			ResultSet rs = smt.executeQuery();
			if(rs.next()) {
				flag = true;
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean modificaPorCampo(Connection con, String db, String campo, Long id_cliente, String valor) {
		boolean flag = false;
		if(campo.equals("rut")) campo = campo.replaceAll("[\\,\\.]","").trim();
		try {
			PreparedStatement smt = con.prepareStatement("update `"+db+"`.cliente set `"+campo+"` = ? WHERE id = ?;");	
			smt.setString(1, valor.trim());
			smt.setLong(2, id_cliente);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean estaEnUso(Connection con, String db, Long id_cliente) {
		boolean flag = false;
		try {
			PreparedStatement smt2 = con
					.prepareStatement("select * from `"+db+"`.bodegaEmpresa WHERE id_cliente = ?");
			smt2.setLong(1, id_cliente);
			ResultSet resultado2 = smt2.executeQuery();
			if (resultado2.next()) {
				flag = true;
			}
			resultado2.close();
			smt2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean delete(Connection con, String db, Long idCliente) {
		boolean flag = false;
		try {
			PreparedStatement smt3 = con
					.prepareStatement("delete from `"+db+"`.contactoCliente WHERE id_cliente = ?");
			smt3.setLong(1, idCliente);
			smt3.executeUpdate();
			smt3.close();
			PreparedStatement smt4 = con
					.prepareStatement("delete from `"+db+"`.cliente WHERE id = ?");
			smt4.setLong(1, idCliente);
			smt4.executeUpdate();
			smt4.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean create(Connection con, String db, FormClienteGraba aux) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("insert into `"+db+"`.cliente (rut,nombre,nickName,direccion,cod_region,cod_comuna,ciudad," + 
							"giro,mailFactura,fonoContacto,rutRepresentante1,nombreRepresentante1,rutRepresentante2,nombreRepresentante2,contactoFactura,formaDePago,especialidad) " +
							" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			String rut = aux.rut.trim().replaceAll("[\\,\\.]","").trim();
			smt.setString(1, rut);
			smt.setString(2, aux.nombre.trim());
			smt.setString(3, aux.nickName.trim());
			smt.setString(4, aux.direccion.trim());
			smt.setString(5, aux.cod_region.trim());
			smt.setString(6, aux.cod_comuna.trim());
			smt.setString(7, aux.ciudad.trim());
			smt.setString(8, aux.giro.trim());
			smt.setString(9, aux.mailFactura.trim());
			smt.setString(10, aux.fonoContacto.trim());
			smt.setString(11, aux.rutRepresentante1.trim());
			smt.setString(12, aux.nombreRepresentante1.trim());
			smt.setString(13, aux.rutRepresentante2.trim());
			smt.setString(14, aux.nombreRepresentante2.trim());
			smt.setString(15, aux.contactoFactura);
			smt.setString(16, aux.formaDePago);
			smt.setString(17, aux.especialidad);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static File allExcel(String db, Map<String,String> mapDiccionario, List<Cliente> listClientes) {
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
		            
		            libro.setSheetName(0, "CLIENTES");
		            Sheet hoja1 = libro.getSheetAt(0);
		            
		            Row row = null;
		            Cell cell = null;
		            
		            row = hoja1.createRow(1);
		            cell = row.createCell(1);
		            cell.setCellStyle(titulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("LISTADO DE CLIENTES");
					
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
					for(int i=1; i<17; i++) {
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
					cell.setCellValue(mapDiccionario.get("RUT"));
					
					posCell++;
					cell = row.createCell(posCell);
		            cell.setCellStyle(encabezado);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("RAZON SOCIAL");
					
					posCell++;
					cell = row.createCell(posCell);
		            cell.setCellStyle(encabezado);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("NOMBRE CORTO");
					
					posCell++;
					cell = row.createCell(posCell);
		            cell.setCellStyle(encabezado);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("GIRO");
					
					posCell++;
					cell = row.createCell(posCell);
		            cell.setCellStyle(encabezado);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("DIRECCION");
					
					posCell++;
					cell = row.createCell(posCell);
		            cell.setCellStyle(encabezado);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("COMUNA");
					
					posCell++;
					cell = row.createCell(posCell);
		            cell.setCellStyle(encabezado);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("CIUDAD");
					
					posCell++;
					cell = row.createCell(posCell);
		            cell.setCellStyle(encabezado);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("CONTACTO");
					
					posCell++;
					cell = row.createCell(posCell);
		            cell.setCellStyle(encabezado);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("E-MAIL");
					
					posCell++;
					cell = row.createCell(posCell);
		            cell.setCellStyle(encabezado);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("TELEFONO");
					
			        
					for(int i=0;i<listClientes.size();i++){
									
						posRow++;
						posCell = 0;
				        row = hoja1.createRow(posRow);
								
				        posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(listClientes.get(i).rut);
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(listClientes.get(i).nombre);
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(listClientes.get(i).nickName);
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(listClientes.get(i).giro);
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(listClientes.get(i).direccion);
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(listClientes.get(i).comuna);
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(listClientes.get(i).ciudad);
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(listClientes.get(i).contactoFactura);
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(listClientes.get(i).mailFactura);
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(listClientes.get(i).fonoContacto);
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
