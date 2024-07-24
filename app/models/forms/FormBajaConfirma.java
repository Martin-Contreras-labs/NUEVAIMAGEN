package models.forms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.utilities.Fechas;

public class FormBajaConfirma {
	public List<Long> id_baja;
	public List<Long> confirmar;

	public FormBajaConfirma(List<Long> id_baja, List<Long> confirmar) {
		super();
		this.id_baja = id_baja;
		this.confirmar = confirmar;
	}
	public FormBajaConfirma() {
		super();
	}
	

	public static boolean confirmarLista(Connection con, String db, FormBajaConfirma form) {
		boolean flag = true;
		
		List<Long> aCambiar = new ArrayList<Long>();
		for(int i=0; i<form.id_baja.size(); i++) {
			if((long)form.confirmar.get(i) == (long)1) {
				aCambiar.add(form.id_baja.get(i));
			}
		}
		
		if(aCambiar.size()>0) {
			String lista = aCambiar.toString().replace("[", "(").replace("]", ")");
			Fechas fecha = Fechas.hoy();
			try {
				PreparedStatement smt = con
						.prepareStatement("update `"+db+"`.baja set esModificable = ?, fecha = ? where id_baja in "+lista+";");
				smt.setLong(1, (long)0);
				smt.setString(2, fecha.getFechaStrAAMMDD());
				smt.close();
	
			} catch (SQLException e) {
				flag = false;
				e.printStackTrace();
			}
		}
		return(flag);
	}
	
	public static boolean aplicaBajas(Connection con, String db, String detalle) {
		boolean flag = false;
		try {
			PreparedStatement smt1 = con
					.prepareStatement("insert into  `"+db+"`.compra (id_factura, id_equipo, cantidad, id_moneda, precioUnidad,id_bodegaEmpresa)"
							+ " VALUES "+detalle+";");
			smt1.executeUpdate();
			smt1.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
}














