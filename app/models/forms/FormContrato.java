package models.forms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import models.tables.Cliente;
import models.tables.Comunas;
import models.tables.Cotizacion;
import models.tables.Regiones;
import models.utilities.Fechas;



public class FormContrato {
	public Long id_cotizacion;
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
	public String usadosEn;
	public String garantiaDoc;
	public String garantiaDet;
	public String garantiaVenc;
	public String garantiaEquiv;
	public String clienteEMail;
	public String clienteFono;
	public String clienteGiro;
	public String notasAlContrato;
	
	public String listadoPlanos;
	public String fechaPlanos;
	public String nomRepresEmpresa;
	public String rutRepresEmpresa;
	public String direccionObra;

	
	public FormContrato(Long id_cotizacion, Long id_cliente, String clienteNombre, String clienteRut,
			String clienteRepresentante1, String clienteRutRepresentante1, String clienteDireccion,
			String clienteRegion, String clienteComuna, String clienteOC, String clienteFechaOC, String clienteContacto,
			String clienteCargoContacto, String fechaContrato, String clienteCargoRepresentante1,
			String numeroContrato, String usadosEn, String garantiaDoc, String garantiaDet, String garantiaVenc, 
			String garantiaEquiv, String clienteEMail, String clienteFono, String clienteGiro, String notasAlContrato, String listadoPlanos, String fechaPlanos, String nomRepresEmpresa, String rutRepresEmpresa, String direccionObra) {
		super();
		this.id_cotizacion = id_cotizacion;
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
		this.usadosEn = usadosEn;
		this.garantiaDoc = garantiaDoc;
		this.garantiaDet = garantiaDet;
		this.garantiaVenc = garantiaVenc;
		this.garantiaEquiv = garantiaEquiv;
		this.clienteEMail = clienteEMail;
		this.clienteFono = clienteFono;
		this.clienteGiro = clienteGiro;
		this.notasAlContrato = notasAlContrato;
		
		this.listadoPlanos = listadoPlanos;
		this.fechaPlanos = fechaPlanos;
		this.nomRepresEmpresa = nomRepresEmpresa;
		this.rutRepresEmpresa = rutRepresEmpresa;
		this.direccionObra = direccionObra;
	}

	public FormContrato() {
		super();
	}

	public static FormContrato find(Connection con, String db, Long idCotizacion) {
		Cotizacion cotizacion = Cotizacion.find(con, db, idCotizacion);
		Cliente cliente = Cliente.find(con, db, cotizacion.id_cliente);
		FormContrato contrato = new FormContrato();
		String numeroContrato = cotizacion.numeroContrato.trim();
		if(numeroContrato.length()<=0) {
			Fechas hoy = Fechas.hoy();
			numeroContrato = hoy.getFechaStrAAMMDD().replaceAll("-", "") + "_" + cotizacion.numero.toString();
		}
		contrato.id_cotizacion=cotizacion.id;
		contrato.id_cliente=cotizacion.id_cliente;
		contrato.clienteNombre = cliente.nombre;
		contrato.clienteRut = cliente.rut;
		contrato.clienteRepresentante1 = cliente.nombreRepresentante1;
		contrato.clienteRutRepresentante1 = cliente.rutRepresentante1;
		contrato.clienteDireccion = cliente.direccion;
		contrato.clienteRegion = cliente.region;
		contrato.clienteComuna = cliente.comuna;
		contrato.clienteOC = cotizacion.numeroOC;
		contrato.clienteFechaOC = cotizacion.fechaOC;
		contrato.clienteContacto = cliente.contactoFactura;
		contrato.clienteCargoContacto = cliente.cargoContactoFactura;
		contrato.fechaContrato = cotizacion.fechaContrato;
		contrato.clienteCargoRepresentante1 = cliente.cargoRepresentante1;
		contrato.numeroContrato = numeroContrato;
		contrato.usadosEn = cotizacion.usadosEn;
		contrato.garantiaDoc = cotizacion.garantiaDoc;
		contrato.garantiaDet = cotizacion.garantiaDet;
		contrato.garantiaVenc = cotizacion.garantiaVenc;
		contrato.garantiaEquiv = cotizacion.garantiaEquiv;
		contrato.clienteEMail = cliente.mailFactura;
		contrato.clienteFono = cliente.fonoContacto;
		contrato.clienteGiro = cliente.giro;
		contrato.notasAlContrato = cotizacion.notasAlContrato;
		
		contrato.listadoPlanos = cotizacion.listadoPlanos;
		contrato.fechaPlanos = cotizacion.fechaPlanos;
		contrato.nomRepresEmpresa = cotizacion.nomRepresEmpresa;
		contrato.rutRepresEmpresa = cotizacion.rutRepresEmpresa;
		contrato.direccionObra = cotizacion.direccionObra;
		
		
		return (contrato);
	}
	
	public static boolean update(Connection con, String db, FormContrato contrato) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("update `"+db+"`.cliente set "
							+ " rut = ?,"
							+ " nombre = ?,"
							+ " direccion = ?,"
							+ " cod_region = ?,"
							+ " cod_comuna = ?,"
							+ " contactoFactura=?,"
							+ " rutRepresentante1=?,"
							+ " nombreRepresentante1=?,"
							+ " cargoContactoFactura=?,"
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
			smt.setString(6, contrato.clienteContacto.trim());
			smt.setString(7, contrato.clienteRutRepresentante1.trim());
			smt.setString(8, contrato.clienteRepresentante1.trim());
			smt.setString(9, contrato.clienteCargoContacto.trim());
			smt.setString(10, contrato.clienteCargoRepresentante1.trim());
			smt.setString(11, contrato.clienteEMail.trim());
			smt.setString(12, contrato.clienteFono.trim());
			smt.setString(13, contrato.clienteGiro.trim());
			smt.setLong(14, contrato.id_cliente);
			smt.executeUpdate();
			smt.close();
			
			
			String fechaOC = contrato.clienteFechaOC;
			String fechaContrato = contrato.fechaContrato;
			String garantiaVenc = contrato.garantiaVenc;
			
			if(!fechaOC.equals("")){
				fechaOC = "fechaOC = '"+fechaOC+"',";
			}
			if(!fechaContrato.equals("")){
				fechaContrato = "fechaContrato = '"+fechaContrato+"',";
			}
			if(!garantiaVenc.equals("")){
				garantiaVenc = "garantiaVenc = '"+garantiaVenc+"',";
			}
			
			PreparedStatement smt2 = con
					.prepareStatement("update `"+db+"`.cotizacion set "
							+ " numeroOC = ?,"
							+ fechaOC
							+ fechaContrato
							+ " numeroContrato = ?,"
							+ " usadosEn = ?,"
							+ " garantiaDoc=?,"
							+ garantiaVenc
							+ " garantiaDet=?, "
							+ " garantiaEquiv=?, "
							+ " notasAlContrato=?, "
							
							+ " nomRepresEmpresa=?, "
							+ " rutRepresEmpresa=?, "
							+ " listadoPlanos=?, "
							+ " fechaPlanos=?, "
							+ " direccionObra=? "
							+ " WHERE id = ?");
			
			smt2.setString(1, contrato.clienteOC.trim());
			smt2.setString(2, contrato.numeroContrato.trim());
			smt2.setString(3, contrato.usadosEn.trim());
			smt2.setString(4, contrato.garantiaDoc.trim());
			smt2.setString(5, contrato.garantiaDet.trim());
			smt2.setString(6, contrato.garantiaEquiv.trim());
			smt2.setString(7, contrato.notasAlContrato.trim());
			
			smt2.setString(8, contrato.nomRepresEmpresa.trim());
			smt2.setString(9, contrato.rutRepresEmpresa.trim());
			smt2.setString(10, contrato.listadoPlanos.trim());
			smt2.setString(11, contrato.fechaPlanos.trim());
			smt2.setString(12, contrato.direccionObra.trim());
			
			smt2.setLong(13, contrato.id_cotizacion);
			
			
			smt2.executeUpdate();
			smt2.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}


	
	
}
