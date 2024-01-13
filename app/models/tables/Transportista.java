package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Transportista {
	public Long id;
	public String rutConductor;
	public String conductor;
	public String rutEmpresa;
	public String empresa;
	public String vehiculo;    // camion con tolva, camion con rampla, camion 3/4, camioneta
	public String patente;
	public String inscripcion;
	public String licencia;
	public String fonoContacto;
	
	public Transportista(Long id, String rutConductor, String conductor, String rutEmpresa, String empresa,
			String vehiculo, String patente, String inscripcion, String licencia, String fonoContacto) {
		super();
		this.id = id;
		this.rutConductor = rutConductor;
		this.conductor = conductor;
		this.rutEmpresa = rutEmpresa;
		this.empresa = empresa;
		this.vehiculo = vehiculo;
		this.patente = patente;
		this.inscripcion = inscripcion;
		this.licencia = licencia;
		this.fonoContacto = fonoContacto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRutConductor() {
		return rutConductor;
	}

	public void setRutConductor(String rutConductor) {
		this.rutConductor = rutConductor;
	}

	public String getConductor() {
		return conductor;
	}

	public void setConductor(String conductor) {
		this.conductor = conductor;
	}

	public String getRutEmpresa() {
		return rutEmpresa;
	}

	public void setRutEmpresa(String rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(String vehiculo) {
		this.vehiculo = vehiculo;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public String getInscripcion() {
		return inscripcion;
	}

	public void setInscripcion(String inscripcion) {
		this.inscripcion = inscripcion;
	}

	public String getLicencia() {
		return licencia;
	}

	public void setLicencia(String licencia) {
		this.licencia = licencia;
	}

	public Transportista() {
		super();
	}
	
	public String getFonoContacto() {
		return fonoContacto;
	}

	public void setFonoContacto(String fonoContacto) {
		this.fonoContacto = fonoContacto;
	}

	public static List<Transportista> listaTransportista(Connection con, String db) {
		List<Transportista> listTransp = new ArrayList<Transportista>();
		try {
			PreparedStatement smt = con.prepareStatement("select id,rutConductor,conductor,rutEmpresa,empresa,vehiculo,patente,inscripcion,licencia,fonoContacto " +
					" from `"+db+"`.transportista order by conductor;");	
			ResultSet rs = smt.executeQuery();
			while(rs.next()) {
				Transportista aux = new Transportista(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getString(4),
						rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10));
				listTransp.add(aux);
			}
			rs.close();smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (listTransp);
	}
	
	public static Transportista find(Connection con, String db,Long id) {
		Transportista transp = null;
		try {
			PreparedStatement smt = con.prepareStatement("select id,rutConductor,conductor,rutEmpresa,empresa,vehiculo,patente,inscripcion,licencia,fonoContacto " +
					" from `"+db+"`.transportista where id=?;");
			smt.setLong(1, id);
			ResultSet rs = smt.executeQuery();
			if(rs.next()) {
				transp = new Transportista(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getString(4),
						rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10));
			}
			rs.close();smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (transp);
	}
	
	public static boolean create(Connection con, String db, Transportista transportista) {
		boolean flag = false;
			try {
				PreparedStatement smt = con.prepareStatement("insert into `"+db+"`.transportista "
						+ " (rutConductor,conductor,rutEmpresa,empresa,vehiculo,patente,inscripcion,licencia,fonoContacto) values (?,?,?,?,?,?,?,?,?);");		
				smt.setString(1, transportista.getRutConductor());
				smt.setString(2, transportista.getConductor());
				smt.setString(3, transportista.getRutEmpresa());
				smt.setString(4, transportista.getEmpresa());
				smt.setString(5, transportista.getVehiculo());
				smt.setString(6, transportista.getPatente());
				smt.setString(7, transportista.getInscripcion());
				smt.setString(8, transportista.getLicencia());
				smt.setString(9, transportista.getFonoContacto());
				smt.executeUpdate();
				smt.close();
				flag = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return (flag);
	}
	
	public static boolean update(Connection con, String db, Transportista transportista) {
		boolean flag = false;
			try {
				PreparedStatement smt = con.prepareStatement("update `"+db+"`.transportista set "
						+ " rutConductor=?,conductor=?,rutEmpresa=?,empresa=?,vehiculo=?,patente=?,inscripcion=?,licencia=?,fonoContacto=? where id=?;");		
				smt.setString(1, transportista.getRutConductor());
				smt.setString(2, transportista.getConductor());
				smt.setString(3, transportista.getRutEmpresa());
				smt.setString(4, transportista.getEmpresa());
				smt.setString(5, transportista.getVehiculo());
				smt.setString(6, transportista.getPatente());
				smt.setString(7, transportista.getInscripcion());
				smt.setString(8, transportista.getLicencia());
				smt.setString(9, transportista.getFonoContacto());
				smt.setLong(10, transportista.getId());
				smt.executeUpdate();
				smt.close();
				flag = true;
			} catch (SQLException e) {
    			e.printStackTrace();
			}
		return (flag);
	}
	
	public static boolean delete(Connection con, String db, Long id_transportista) {
		boolean flag = false;
		try {
			PreparedStatement smt2 = con.prepareStatement("select id_transportista from `"+db+"`.guia where id_transportista=?;");
			smt2.setLong(1, id_transportista);
			ResultSet rs = smt2.executeQuery();
			if(rs.next()) {
				smt2.close();
				rs.close();
				flag = false;
			}else {
				PreparedStatement smt = con.prepareStatement("delete from `"+db+"`.transportista where id=?;");
				smt.setLong(1, id_transportista);
				smt.executeUpdate();
				smt.close();
				flag = true;
			}
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}

	
	public static String vistaEditaTransportista(Connection con, String db, Long id_transportista, Map<String,String> mapDiccionario) {
		Transportista transp = Transportista.find(con, db, id_transportista);
		String vista =
				"<form id='formModificaTransportista' method='post' action='/modificaTransportistaAjax/'>"+
				"<table class='table table-sm table-bordered table-condensed table-fluid'><tr>"+
					"<td style='text-align:left;'>"+mapDiccionario.get("RUT")+" Conductor: </td>"+
					"<td style='text-align:left;'>"+
						"<input type='hidden' value='"+transp.id+"' name='id'>"+
						"<input type='text' class='form-control left' name='rutConductor' id='updateRutConductor' autocomplete='off' maxlength='50' "+
						" value='"+transp.rutConductor+"' onchange='value = value.trim();'>"+
					"</td></tr><tr>"+
					"<td style='text-align:left;'>Nombre Conductor: </td>"+
					"<td style='text-align:left;'>"+
						"<input type='text' class='form-control left' name='conductor' id='updateNombreConductor' autocomplete='off' maxlength='100' "+
						" value='"+transp.conductor+"' onchange='value = value.trim();'>"+
					"</td></tr><tr>"+
					"<td style='text-align:left;'>"+mapDiccionario.get("RUT")+" Empresa: </td>"+
					"<td style='text-align:left;'>"+
						"<input type='text' class='form-control left' name='rutEmpresa' id='updateRutEmpresa' autocomplete='off' maxlength='50' "+
						" value='"+transp.rutEmpresa+"' onchange='value = value.trim();'>"+
					"</td></tr><tr>"+
					"<td style='text-align:left;'>Nombre Empresa: </td>"+
					"<td style='text-align:left;'>"+
						"<input type='text' class='form-control left' name='empresa' id='updateNombreEmpresa' autocomplete='off' maxlength='100' "+
						" value='"+transp.empresa+"' onchange='value = value.trim();'>"+
					"</td></tr><tr>"+
					"<td style='text-align:left;'>Veh&iacute;culo: </td>"+
					"<td style='text-align:left;'>"+
						"<input type='text' class='form-control left' name='vehiculo' id='updateVehiculo' autocomplete='off' maxlength='100' "+
						" value='"+transp.vehiculo+"' onchange='value = value.trim();'>"+
					"</td></tr><tr>"+
					"<td style='text-align:left;'>"+mapDiccionario.get("Patente")+": </td>"+
					"<td style='text-align:left;'>"+
						"<input type='text' class='form-control left' name='patente' id='updatePatente' autocomplete='off' maxlength='50' "+
						" value='"+transp.patente+"' onchange='value = value.trim();'>"+
					"</td></tr><tr>"+
					"<td style='text-align:left;'>Nro Inscripci&oacute;n: </td>"+
					"<td style='text-align:left;'>"+
						"<input type='text' class='form-control left' name='inscripcion' id='updateInscripcion' autocomplete='off' maxlength='50' "+
						" value='"+transp.inscripcion+"' onchange='value = value.trim();'>"+
					"</td></tr><tr>"+
					"<td style='text-align:left;'>Nro Licencia: </td>"+
					"<td style='text-align:left;'>"+
						"<input type='text' class='form-control left' name='licencia' id='updateLicencia' autocomplete='off' maxlength='50' "+
						" value='"+transp.licencia+"' onchange='value = value.trim();'>"+
					"</td></tr><tr>"+
					"<td style='text-align:left;'>Fono Contacto: </td>"+
					"<td style='text-align:left;'>"+
						"<input type='text' class='form-control left' name='fonoContacto' id='updateFonoContacto' autocomplete='off' maxlength='50' "+
						" value='"+transp.fonoContacto+"' onchange='value = value.trim();'>"+
					"</td></tr>"+
				"</table>"+
				"</form>"+
				"<div align='center'>"+
					"<button type='button' class='btn btn-sm btn-light' style='font-size: 10px;' data-dismiss='modal' onclick='limpiarTransportista()'>Cancelar</button>"+
					"<button type='button' class='btn btn-sm btn-success' style='font-size: 10px;' data-dismiss='modal' onClick='modificarTransportista()'>Grabar</button>"+
				"</div>";
		return(vista);
	}
	
	public static String vistaListado(Connection con, String db, Map<String,String> mapDiccionario) {
			List<Transportista> listTransp = Transportista.listaTransportista(con, db);
			String vista = ""+
			"<table id='tablaListaTransporte' class='table table-sm table-hover table-bordered table-condensed table-fluid'>"+
			"<thead style='background-color: #eeeeee'>"+
				"<TR>"+
					   "<th>id</th>"+
				       "<th>"+mapDiccionario.get("RUT")+" Conductor</th>"+
				       "<th>Conductor</th>"+
				       "<th>"+mapDiccionario.get("RUT")+" Empresa</th>"+
				       "<th>Empresa</th>"+
				       "<th>Vehiculo</th>"+
				       "<th>"+mapDiccionario.get("Patente")+"</th>"+
				       "<th>Nro Inscripcion</th>"+
				       "<th>Nro Licencia</th>"+
				       "<th>Fono Contacto</th>"+
				       "<th>SELECT</th>"+
				       "<th>EDIT</th>"+
				       "<th>DEL</th>"+
				"</TR>"+
			"</thead>"+
			"<tbody>";
			for(int i=0; i<listTransp.size(); i++){
				vista += ""+
						"<TR>"+
						"<td style='text-align:left;'>"+listTransp.get(i).getId()+"</td>"+
						"<td style='text-align:left;'>"+listTransp.get(i).getRutConductor()+"</td>"+
						"<td style='text-align:left;'>"+listTransp.get(i).getConductor()+"</td>"+
						"<td style='text-align:left;'>"+listTransp.get(i).getRutEmpresa()+"</td>"+
						"<td style='text-align:left;'>"+listTransp.get(i).getEmpresa()+"</td>"+
						"<td style='text-align:left;'>"+listTransp.get(i).getVehiculo()+"</td>"+
						"<td style='text-align:left;'>"+listTransp.get(i).getPatente()+"</td>"+
						"<td style='text-align:left;'>"+listTransp.get(i).getInscripcion()+"</td>"+
						"<td style='text-align:left;'>"+listTransp.get(i).getLicencia()+"</td>"+
						"<td style='text-align:left;'>"+listTransp.get(i).getFonoContacto()+"</td>"+
						"<td style='text-align:center;'>"+
							"<a href='#' onclick='seleccionaTransportista("+listTransp.get(i).getId()+")'>"+
								"<kbd style='background-color: green'>SELECT</kbd>"+
							"</a>"+
						"</td>"+
						"<td style='text-align:center;'>"+
							"<a href='#' onclick='editaTransportista("+listTransp.get(i).getId()+")'>"+
								"<kbd style='background-color: #73C6B6'>edit</kbd>"+
							"</a>"+
						"</td>"+
						"<td style='text-align:center;'>"+
							"<a href='#' onclick='eliminaTransportista("+listTransp.get(i).getId()+")'>"+
								"<kbd style='background-color: red'>X</kbd>"+
							"</a>"+
						"</td>"+
					"</TR>";
			}
			vista += "</tbody></table>";
		return (vista);
	}
	


	

}
