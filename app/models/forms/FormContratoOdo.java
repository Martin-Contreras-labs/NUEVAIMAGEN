package models.forms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import models.tables.Cliente;
import models.tables.Comunas;
import models.tables.CotiOdo;
import models.tables.Regiones;
import models.utilities.Fechas;



public class FormContratoOdo {
	public Long id_cotiOdo;
	public Long id_cliente;
	public String clienteNombre;
	public String clienteRut;
	public String clienteRepresentante1;
	public String clienteRutRepresentante1;
	public String clienteDireccion;
	public String clienteRegion;
	public String clienteComuna;
	public String clienteOC;
	public String clienteFechaOC;
	public String clienteContacto;
	public String clienteCargoContacto;
	public String fechaContrato;
	public String clienteCargoRepresentante1;
	public String numeroContrato;
	
	public String clienteEMail;
	public String clienteFono;
	public String clienteGiro;
	public String notasAlContrato;
	
	
	

	public FormContratoOdo(Long id_cotiOdo, Long id_cliente, String clienteNombre, String clienteRut,
			String clienteRepresentante1, String clienteRutRepresentante1, String clienteDireccion,
			String clienteRegion, String clienteComuna, String clienteOC, String clienteFechaOC, String clienteContacto,
			String clienteCargoContacto, String fechaContrato, String clienteCargoRepresentante1, String numeroContrato,
			String clienteEMail, String clienteFono, String clienteGiro, String notasAlContrato) {
		super();
		this.id_cotiOdo = id_cotiOdo;
		this.id_cliente = id_cliente;
		this.clienteNombre = clienteNombre;
		this.clienteRut = clienteRut;
		this.clienteRepresentante1 = clienteRepresentante1;
		this.clienteRutRepresentante1 = clienteRutRepresentante1;
		this.clienteDireccion = clienteDireccion;
		this.clienteRegion = clienteRegion;
		this.clienteComuna = clienteComuna;
		this.clienteOC = clienteOC;
		this.clienteFechaOC = clienteFechaOC;
		this.clienteContacto = clienteContacto;
		this.clienteCargoContacto = clienteCargoContacto;
		this.fechaContrato = fechaContrato;
		this.clienteCargoRepresentante1 = clienteCargoRepresentante1;
		this.numeroContrato = numeroContrato;
		this.clienteEMail = clienteEMail;
		this.clienteFono = clienteFono;
		this.clienteGiro = clienteGiro;
		this.notasAlContrato = notasAlContrato;
	}

	public FormContratoOdo() {
		super();
	}

	public static FormContratoOdo find(Connection con, String db, Long id_cotiOdo) {
		CotiOdo cotiOdo = CotiOdo.find(con, db, id_cotiOdo);
		Cliente cliente = Cliente.find(con, db, cotiOdo.id_cliente);
		FormContratoOdo contrato = new FormContratoOdo();
		String numeroContrato = cotiOdo.numeroContrato.trim();
		if(numeroContrato.length()<=0) {
			Fechas hoy = Fechas.hoy();
			numeroContrato = hoy.getFechaStrAAMMDD().replaceAll("-", "") + "_" + cotiOdo.numero.toString();
		}
		contrato.id_cotiOdo=cotiOdo.id;
		contrato.id_cliente=cotiOdo.id_cliente;
		contrato.clienteNombre = cliente.nombre;
		contrato.clienteRut = cliente.rut;
		contrato.clienteRepresentante1 = cliente.nombreRepresentante1;
		contrato.clienteRutRepresentante1 = cliente.rutRepresentante1;
		contrato.clienteDireccion = cliente.direccion;
		contrato.clienteRegion = cliente.region;
		contrato.clienteComuna = cliente.comuna;
		contrato.clienteOC = cotiOdo.numeroOC;
		contrato.clienteFechaOC = cotiOdo.fechaOC;
		contrato.clienteContacto = cliente.contactoFactura;
		contrato.clienteCargoContacto = cliente.cargoContactoFactura;
		contrato.fechaContrato = cotiOdo.fechaContrato;
		contrato.clienteCargoRepresentante1 = cliente.cargoRepresentante1;
		contrato.clienteEMail = cliente.mailFactura;
		contrato.clienteFono = cliente.fonoContacto;
		contrato.clienteGiro = cliente.giro;
		contrato.numeroContrato = numeroContrato;
		contrato.notasAlContrato = cotiOdo.notasAlContrato;
		
		return (contrato);
	}
	
	public static boolean update(Connection con, String db, FormContratoOdo contrato) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("UPDATE `"+db+"`.cliente SET "
							+ " rut = ?,"
							+ " nombre = ?,"
							+ " direccion = ?,"
							+ " cod_region = ?,"
							+ " cod_comuna = ?,"
							+ " rutRepresentante1=?,"
							+ " nombreRepresentante1=?,"
							+ " cargoRepresentante1=?, "
							+ " mailFactura=?, "
							+ " fonoContacto=?, "
							+ " giro=? "
							+ " WHERE id = ?");
			
			Regiones region= Regiones.findPorNombre(con, db, contrato.clienteRegion);
			String codRegion="--";
			if(region!=null) {
				codRegion = region.codigo;
				if(codRegion == null) {
					codRegion="--";
				}
			}
			
			Comunas comuna= Comunas.findPorNombre(con, db, contrato.clienteComuna);
			String codComuna = "--";
			if(comuna!=null) {
				codComuna = comuna.codigo;
				if(codComuna == null) {
					codComuna = "--";
				}
			}
			
			smt.setString(1, contrato.clienteRut.trim());
			smt.setString(2, contrato.clienteNombre.trim());
			smt.setString(3, contrato.clienteDireccion.trim());
			smt.setString(4, codRegion.trim());
			smt.setString(5, codComuna.trim());
			
			smt.setString(6, contrato.clienteRutRepresentante1.trim());
			smt.setString(7, contrato.clienteRepresentante1.trim());
			smt.setString(8, contrato.clienteCargoRepresentante1.trim());
			smt.setString(9, contrato.clienteEMail.trim());
			smt.setString(10, contrato.clienteFono.trim());
			smt.setString(11, contrato.clienteGiro.trim());
			smt.setLong(12, contrato.id_cliente);
			smt.executeUpdate();
			smt.close();
			
			
	
			String fechaContrato = contrato.fechaContrato;
			
			if(!fechaContrato.equals("")){
				fechaContrato = "fechaContrato = '"+fechaContrato+"',";
			}
			
			
			PreparedStatement smt2 = con
					.prepareStatement("UPDATE `"+db+"`.cotiOdo SET "
							+ fechaContrato
							+ " numeroContrato = ?,"
							+ " notasAlContrato=? "
							+ " WHERE id = ?");
			
			smt2.setString(1, contrato.numeroContrato.trim());
			smt2.setString(2, contrato.notasAlContrato.trim());
			smt2.setLong(3, contrato.id_cotiOdo);
			
			smt2.executeUpdate();
			smt2.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}


	
	
}
