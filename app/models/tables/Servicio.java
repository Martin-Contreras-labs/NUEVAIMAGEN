package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.forms.FormServicioGraba;
import models.utilities.Fechas;




public class Servicio {
	public Long id;
	public String codigo;
	public String nombre;
	public Long id_unidadServicio;
	public Long id_claseServicio;
	public String descripcion;
	public Long id_moneda;
	public String fecha;
	public Double precio;
	
	public String nombreClase;
	public String nombreUnidad;
	public String nickMoneda; 
	public Long nroDecimal;
	
	public Double cantMinimo;				// es la cantidad de hr minimas u otra unidad
	public Double precioAdicional;			// es el valor a aplicar sobre el excedente
	

	public Servicio(Long id, String codigo, String nombre, Long id_unidadServicio, Long id_claseServicio,
			String descripcion, Long id_moneda, String fecha, Double precio, String nombreClase,
			String nombreUnidad, String nickMoneda, Long nroDecimal, Double cantMinimo, Double precioAdicional) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
		this.id_unidadServicio = id_unidadServicio;
		this.id_claseServicio = id_claseServicio;
		this.descripcion = descripcion;
		this.id_moneda = id_moneda;
		this.fecha = fecha;
		this.precio = precio;
		this.nombreClase = nombreClase;
		this.nombreUnidad = nombreUnidad;
		this.nickMoneda = nickMoneda;
		this.nroDecimal = nroDecimal;
		this.cantMinimo = cantMinimo;
		this.precioAdicional = precioAdicional;
	}



	public Servicio() {
		super();
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getId_unidadServicio() {
		return id_unidadServicio;
	}

	public void setId_unidadServicio(Long id_unidadServicio) {
		this.id_unidadServicio = id_unidadServicio;
	}

	public Long getId_claseServicio() {
		return id_claseServicio;
	}

	public void setId_claseServicio(Long id_claseServicio) {
		this.id_claseServicio = id_claseServicio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getId_moneda() {
		return id_moneda;
	}

	public void setId_moneda(Long id_moneda) {
		this.id_moneda = id_moneda;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getNombreClase() {
		return nombreClase;
	}

	public void setNombreClase(String nombreClase) {
		this.nombreClase = nombreClase;
	}

	public String getNombreUnidad() {
		return nombreUnidad;
	}

	public void setNombreUnidad(String nombreUnidad) {
		this.nombreUnidad = nombreUnidad;
	}

	public String getNickMoneda() {
		return nickMoneda;
	}

	public void setNickMoneda(String nickMoneda) {
		this.nickMoneda = nickMoneda;
	}

	public Long getNroDecimal() {
		return nroDecimal;
	}

	public void setNroDecimal(Long nroDecimal) {
		this.nroDecimal = nroDecimal;
	}

	public Double getCantMinimo() {
		return cantMinimo;
	}

	public void setCantMinimo(Double cantMinimo) {
		this.cantMinimo = cantMinimo;
	}

	public Double getPrecioAdicional() {
		return precioAdicional;
	}

	public void setPrecioAdicional(Double precioAdicional) {
		this.precioAdicional = precioAdicional;
	}



	public static Map<Long,Servicio> mapAll(Connection con, String db) {
		Map<Long,Servicio> map = new HashMap<Long,Servicio>();
		List<Servicio> lista = Servicio.all(con, db);
		lista.forEach(x->{
			map.put(x.getId(), x);
		});
		return(map);
	}
	
	public static Map<String,Servicio> mapAllPorCodigo(Connection con, String db) {
		Map<String,Servicio> map = new HashMap<String,Servicio>();
		List<Servicio> lista = Servicio.all(con, db);
		lista.forEach(x->{
			map.put(x.getCodigo(), x);
		});
		return(map);
	}
	
	public static List<Servicio> all(Connection con, String db) {
		List<Servicio> lista = new ArrayList<Servicio>();
		
		Map<Long,ClaseServicio> mapClase = ClaseServicio.mapAll(con, db);
		Map<Long,UnidadServicio> mapUnidad = UnidadServicio.mapAll(con, db);
		Map<Long,Moneda> mapMoneda = Moneda.mapMonedas(con, db);
		
		try {
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" servicio.id, " +
							" servicio.codigo, " +
							" servicio.nombre, " +
							" servicio.id_unidadServicio, " +
							" servicio.id_claseServicio, " +
							" servicio.descripcion, " +
							" servicio.id_moneda, " +
							" servicio.fecha, " +
							" servicio.precio, " +
							" servicio.cantMinimo, " +
							" servicio.precioAdicional " +
							" from `"+db+"`.servicio " +
							" order by servicio.nombre;");
			ResultSet rs = smt.executeQuery();
			
			while (rs.next()) {
				
				
				ClaseServicio clase = mapClase.get(rs.getLong(5));
				String nombreClase = "Sin Familia";
				if(clase !=null) nombreClase = clase.getNombre();
				
				UnidadServicio unidad = mapUnidad.get(rs.getLong(4));
				String nombreUnidad = "";
				if(unidad !=null) nombreUnidad = unidad.getNombre();
				
				Moneda moneda = mapMoneda.get(rs.getLong(7));
				String nickMoneda = "";
				Long decimal = (long)0;
				if(moneda !=null) {
					nickMoneda = moneda.getNickName();
					decimal = moneda.getNumeroDecimales();
				}

				lista.add(new Servicio(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getLong(4),rs.getLong(5),rs.getString(6),
						rs.getLong(7),rs.getString(8),rs.getDouble(9),nombreClase,nombreUnidad,nickMoneda,decimal,rs.getDouble(10),rs.getDouble(11)));
			}
			
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<Servicio> allNoEnBodega(Connection con, String db, BodegaEmpresa bodega, List<ListaPrecioServicio> listPrecios) {
		List<Servicio> lista = new ArrayList<Servicio>();
		List<Servicio> listServicios = Servicio.all(con, db);
		Map<String,ListaPrecioServicio> mapListaPrecio = ListaPrecioServicio.mapListaPrecioServicioXBodega(con, db, bodega);
		
		listServicios.forEach(s->{
			ListaPrecioServicio precio = mapListaPrecio.get(bodega.getId()+"_"+s.getId());
			if(precio==null) {
				lista.add(s);
			}
		});
		return (lista);
	}
	
	public static List<List<String>> allBodegaNoEnSevicio(Connection con, String db, List<List<String>> listBodegas, Long id_servicio) {
		List<List<String>> lista = new ArrayList<List<String>>();
		Map<Long,ListaPrecioServicio> mapListaPrecio = ListaPrecioServicio.mapListaPrecioServicioXServicio(con, db, id_servicio);
		
		listBodegas.forEach(s->{
			Long id_bodegaEmpresa = Long.parseLong(s.get(1));
			ListaPrecioServicio precio = mapListaPrecio.get(id_bodegaEmpresa);
			if(precio == null) {
				lista.add(s);
			}
		});
		return (lista);
	}
	
	public static boolean existeCodigo(Connection con, String db, String codigo) {
		boolean flag = false;
		try {
			PreparedStatement smt1 = con.prepareStatement("select * from `"+db+"`.servicio WHERE upper(codigo) = ?;");
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
	
	public static boolean create(Connection con, String db, FormServicioGraba aux) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("insert into `"+db+"`.servicio (codigo, nombre, id_unidadServicio, id_claseServicio, descripcion, id_moneda, fecha, precio) " +
							" values (?,?,?,?,?,?,?,?)");
			smt.setString(1, aux.codigo.replaceAll("\\,","").trim());
			smt.setString(2, aux.nombre.trim());
			smt.setLong(3, aux.id_unidadServicio);
			smt.setLong(4, aux.id_claseServicio);
			smt.setString(5, aux.descripcion);
			smt.setLong(6, aux.id_moneda);
			smt.setString(7, Fechas.hoy().getFechaStrAAMMDD());
			smt.setDouble(8, Double.parseDouble(aux.precio.replaceAll(",", "").trim()));
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static Servicio find(Connection con, String db, Long id_servicio) {
		Servicio aux = null;
		
		Map<Long,ClaseServicio> mapClase = ClaseServicio.mapAll(con, db);
		Map<Long,UnidadServicio> mapUnidad = UnidadServicio.mapAll(con, db);
		Map<Long,Moneda> mapMoneda = Moneda.mapMonedas(con, db);
		
		try {
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" servicio.id, " +
							" servicio.codigo, " +
							" servicio.nombre, " +
							" servicio.id_unidadServicio, " +
							" servicio.id_claseServicio, " +
							" servicio.descripcion, " +
							" servicio.id_moneda, " +
							" servicio.fecha, " +
							" servicio.precio, " +
							" servicio.cantMinimo, " +
							" servicio.precioAdicional " +
							" from `"+db+"`.servicio " +
							" where id = ?;");
			smt.setLong(1, id_servicio);

			ResultSet rs = smt.executeQuery();
			
			if (rs.next()) {
				ClaseServicio clase = mapClase.get(rs.getLong(5));
				String nombreClase = "Sin Familia";
				if(clase !=null) nombreClase = clase.getNombre();
				
				UnidadServicio unidad = mapUnidad.get(rs.getLong(4));
				String nombreUnidad = "";
				if(unidad !=null) nombreUnidad = unidad.getNombre();
				
				Moneda moneda = mapMoneda.get(rs.getLong(7));
				String nickMoneda = "";
				Long decimal = (long)0;
				if(moneda !=null) {
					nickMoneda = moneda.getNickName();
					decimal = moneda.getNumeroDecimales();
				}

				aux = new Servicio(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getLong(4),rs.getLong(5),rs.getString(6),
						rs.getLong(7),rs.getString(8),rs.getDouble(9),nombreClase,nombreUnidad,nickMoneda,decimal,rs.getDouble(10),rs.getDouble(11));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (aux);
	}
	
	public static boolean update(Connection con, String db, FormServicioGraba aux) {
		boolean flag = false;
		try {
			PreparedStatement smt2 = con
					.prepareStatement("update `"+db+"`.servicio set codigo=?, nombre=?, id_unidadServicio=?, id_claseServicio=?, descripcion=?, id_moneda=?, precio=? where id=?;");
			smt2.setString(1, aux.codigo.replaceAll("\\,","").trim());
			smt2.setString(2, aux.nombre.trim());
			smt2.setLong(3, aux.id_unidadServicio);
			smt2.setLong(4, aux.id_claseServicio);
			smt2.setString(5, aux.descripcion);
			smt2.setLong(6, aux.id_moneda);
			String auxprecio = aux.precio;
			Double precio = Double.parseDouble(auxprecio.replaceAll(",", ""));
			smt2.setDouble(7, precio);
			smt2.setLong(8, aux.id);
			smt2.executeUpdate();
			smt2.close();
			
			if(aux.cambioElprecio.equals("1")){
				PreparedStatement smt = con
						.prepareStatement("update `"+db+"`.servicio set fecha=?, precio=? where id=?;");
				smt.setString(1, Fechas.hoy().getFechaStrAAMMDD());
				smt.setDouble(2, Double.parseDouble(aux.precio.replaceAll(",", "").trim()));
				smt.setLong(3, aux.id);
				smt.executeUpdate();
				smt.close();
			}
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean modifyPrecio(Connection con, String db, Long id_servicio, String valor, String fecha) {
		boolean flag = false;
		try {
			PreparedStatement smt2 = con
					.prepareStatement("update `"+db+"`.servicio set precio=?, fecha=? where id=?;");
			smt2.setString(1, valor);
			smt2.setString(2, fecha);
			smt2.setLong(3, id_servicio);
			smt2.executeUpdate();
			smt2.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean modifyCantMinimo(Connection con, String db, Long id_servicio, String valor, String fecha) {
		boolean flag = false;
		try {
			PreparedStatement smt2 = con
					.prepareStatement("update `"+db+"`.servicio set cantMinimo=?, fecha=? where id=?;");
			smt2.setString(1, valor);
			smt2.setString(2, fecha);
			smt2.setLong(3, id_servicio);
			smt2.executeUpdate();
			smt2.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean modifyPrecioAdicional(Connection con, String db, Long id_servicio, String valor, String fecha) {
		boolean flag = false;
		try {
			PreparedStatement smt2 = con
					.prepareStatement("update `"+db+"`.servicio set precioAdicional=?, fecha=? where id=?;");
			smt2.setString(1, valor);
			smt2.setString(2, fecha);
			smt2.setLong(3, id_servicio);
			smt2.executeUpdate();
			smt2.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean estaEnUso(Connection con, String db, Long id_servicio) {
		boolean flag = false;
		try {
			PreparedStatement smt1 = con
					.prepareStatement("Select * from `"+db+"`.ventaServicio WHERE id_servicio = ?");
			smt1.setLong(1, id_servicio);
			ResultSet rs1 = smt1.executeQuery();
			if (rs1.next()) {
				flag = true;
			}
			rs1.close();
			smt1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean delete(Connection con, String db, Long id_servicio) {
		boolean flag = false;
		try {
			PreparedStatement smt1 = con.prepareStatement("delete from `"+db+"`.servicio WHERE id = ?;");
			smt1.setLong(1, id_servicio);
			smt1.executeUpdate();
			smt1.close();
	
			PreparedStatement smt = con.prepareStatement("delete from `"+db+"`.listaPrecioServicio WHERE id_servicio = ?;");
			smt.setLong(1, id_servicio);
			smt.executeUpdate();
			smt.close();
			
			PreparedStatement smt2 = con.prepareStatement("delete from `"+db+"`.precioVariableServicio WHERE id_servicio = ?;");
			smt2.setLong(1, id_servicio);
			smt2.executeUpdate();
			smt2.close();
			
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	
	
	
	
}
