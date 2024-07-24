package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class CotiBiblioteca{

	public String dateCreate;
	public Long id;
	public String cliente;
	public String proyecto;
	public String numCotizaciones;
	public String idCotizaciones;
	public String pdfConDetalle;
	public String pdfSinDetalle;
	public String xlsConDetalle;
	public String id_sucursal;
	public String neto;
	public String iva;
	public String total;
	public String nameSucursal;
	
	public CotiBiblioteca(String dateCreate, Long id, String cliente, String proyecto, String numCotizaciones,
			String idCotizaciones, String pdfConDetalle, String pdfSinDetalle, String xlsConDetalle, String id_sucursal,
			String neto, String iva, String total, String nameSucursal) {
		super();
		this.dateCreate = dateCreate;
		this.id = id;
		this.cliente = cliente;
		this.proyecto = proyecto;
		this.numCotizaciones = numCotizaciones;
		this.idCotizaciones = idCotizaciones;
		this.pdfConDetalle = pdfConDetalle;
		this.pdfSinDetalle = pdfSinDetalle;
		this.xlsConDetalle = xlsConDetalle;
		this.id_sucursal = id_sucursal;
		this.neto = neto;
		this.iva = iva;
		this.total = total;
		this.nameSucursal = nameSucursal;
	}

	public CotiBiblioteca(String cliente, String proyecto, String numCotizaciones,
			String idCotizaciones, String id_sucursal, String neto, String iva, String total) {
		super();
		this.cliente = cliente;
		this.proyecto = proyecto;
		this.numCotizaciones = numCotizaciones;
		this.idCotizaciones = idCotizaciones;
		this.id_sucursal = id_sucursal;
		this.neto = neto;
		this.iva = iva;
		this.total = total;
	}

	public CotiBiblioteca() {
		super();
	}

	public String getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(String dateCreate) {
		this.dateCreate = dateCreate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getProyecto() {
		return proyecto;
	}

	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}

	public String getNumCotizaciones() {
		return numCotizaciones;
	}

	public void setNumCotizaciones(String numCotizaciones) {
		this.numCotizaciones = numCotizaciones;
	}

	public String getIdCotizaciones() {
		return idCotizaciones;
	}

	public void setIdCotizaciones(String idCotizaciones) {
		this.idCotizaciones = idCotizaciones;
	}

	public String getPdfConDetalle() {
		return pdfConDetalle;
	}

	public void setPdfConDetalle(String pdfConDetalle) {
		this.pdfConDetalle = pdfConDetalle;
	}

	public String getPdfSinDetalle() {
		return pdfSinDetalle;
	}

	public void setPdfSinDetalle(String pdfSinDetalle) {
		this.pdfSinDetalle = pdfSinDetalle;
	}

	public String getXlsConDetalle() {
		return xlsConDetalle;
	}

	public void setXlsConDetalle(String xlsConDetalle) {
		this.xlsConDetalle = xlsConDetalle;
	}

	public String getId_sucursal() {
		return id_sucursal;
	}

	public void setId_sucursal(String id_sucursal) {
		this.id_sucursal = id_sucursal;
	}

	public String getNeto() {
		return neto;
	}

	public void setNeto(String neto) {
		this.neto = neto;
	}

	public String getIva() {
		return iva;
	}

	public void setIva(String iva) {
		this.iva = iva;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
	
	public String getNameSucursal() {
		return nameSucursal;
	}
	
	public void setNameSucursal(String nameSucursal) {
		this.nameSucursal = nameSucursal;
	}
	
	public static List<CotiBiblioteca> all(Connection con, String db, String esPorSucursal, String id_sucursal) {
		List<CotiBiblioteca> lista = new ArrayList<CotiBiblioteca>();
		
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " where cotiBiblioteca.id_sucursal = " + id_sucursal;
		}
		
		try {
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" cotiBiblioteca.dateCreate, " +
							" cotiBiblioteca.id, " +
							" cotiBiblioteca.cliente, " +
							" cotiBiblioteca.proyecto, " +
							" cotiBiblioteca.numCotizaciones, " +
							" cotiBiblioteca.idCotizaciones, " +
							" cotiBiblioteca.pdfConDetalle, " +
							" cotiBiblioteca.pdfSinDetalle, " +
							" cotiBiblioteca.xlsConDetalle, " +
							" cotiBiblioteca.id_sucursal, " +
							" cotiBiblioteca.neto, " +
							" cotiBiblioteca.iva, " +
							" cotiBiblioteca.total " +
							" from `"+db+"`.cotiBiblioteca " + condSucursal +
							" order by cotiBiblioteca.dateCreate desc;");
			ResultSet rs = smt.executeQuery();
			
			while (rs.next()) {
				Sucursal sucursal = Sucursal.find(con, db, id_sucursal);
				lista.add(new CotiBiblioteca(rs.getString(1),rs.getLong(2),rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),
						rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),sucursal.nombre));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}

	public static Long create(Connection con, String db, CotiBiblioteca cotiBiblioteca) {	
		Long id_cotiBiblioteca = null;
		try {
			PreparedStatement smt = con
					.prepareStatement("insert into `"+db+"`.cotiBiblioteca (cliente,proyecto,numCotizaciones,idCotizaciones,id_sucursal,neto,iva,total) " +
							" values (?,?,?,?,?,?,?,?)");		
			smt.setString(1, cotiBiblioteca.getCliente());
			smt.setString(2, cotiBiblioteca.getProyecto());
			smt.setString(3, cotiBiblioteca.getNumCotizaciones());
			smt.setString(4, cotiBiblioteca.getIdCotizaciones());
			smt.setString(5, cotiBiblioteca.getId_sucursal());
			smt.setString(6, cotiBiblioteca.getNeto());
			smt.setString(7, cotiBiblioteca.getIva());
			smt.setString(8, cotiBiblioteca.getTotal());
			smt.executeUpdate();
			smt.close();
			
			PreparedStatement smt1 = con
					.prepareStatement("select max(cotiBiblioteca.id) from `"+db+"`.cotiBiblioteca "
							+ " where cliente=? and proyecto=? and numCotizaciones=? and idCotizaciones=? and "
							+ " id_sucursal=? and neto=? and iva=? and total=?;");		
			smt1.setString(1, cotiBiblioteca.getCliente());
			smt1.setString(2, cotiBiblioteca.getProyecto());
			smt1.setString(3, cotiBiblioteca.getNumCotizaciones());
			smt1.setString(4, cotiBiblioteca.getIdCotizaciones());
			smt1.setString(5, cotiBiblioteca.getId_sucursal());
			smt1.setString(6, cotiBiblioteca.getNeto());
			smt1.setString(7, cotiBiblioteca.getIva());
			smt1.setString(8, cotiBiblioteca.getTotal());
			ResultSet rs1 = smt1.executeQuery();
			if(rs1.next()) {
				id_cotiBiblioteca = rs1.getLong(1);
			}
			rs1.close();
			smt1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (id_cotiBiblioteca);
	}
	
	public static boolean updatePdfConDetalle(Connection con, String db, String pdfConDetalle, Long id_cotiBiblioteca) {	
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("update `"+db+"`.cotiBiblioteca set pdfConDetalle=? where id=?");		
			smt.setString(1, pdfConDetalle);
			smt.setLong(2, id_cotiBiblioteca);
			smt.executeUpdate();
			smt.close();
			flag=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean updatePdfSinDetalle(Connection con, String db, String pdfSinDetalle, Long id_cotiBiblioteca) {	
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("update `"+db+"`.cotiBiblioteca set pdfSinDetalle=? where id=?");		
			smt.setString(1, pdfSinDetalle);
			smt.setLong(2, id_cotiBiblioteca);
			smt.executeUpdate();
			smt.close();
			flag=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean updateXlsConDetalle(Connection con, String db, String xlsConDetalle, Long id_cotiBiblioteca) {	
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("update `"+db+"`.cotiBiblioteca set xlsConDetalle=? where id=?");		
			smt.setString(1, xlsConDetalle);
			smt.setLong(2, id_cotiBiblioteca);
			smt.executeUpdate();
			smt.close();
			flag=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean delete(Connection con, String db, String id_cotiBiblioteca) {
		boolean flag = false;
		try {
			PreparedStatement smt2 = con
					.prepareStatement("delete from `"+db+"`.cotiBiblioteca WHERE id = ?");
			smt2.setString(1,id_cotiBiblioteca);
			smt2.executeUpdate();
			smt2.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	

	
	

}
