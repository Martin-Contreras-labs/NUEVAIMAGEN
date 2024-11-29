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

import models.utilities.Fechas;



public class ListaPrecioServicio {
	public Long id_bodegaEmpresa;		// 0 listaPrecio.id_bodegaEmpresa
	public Long id_servicio;			// 1 listaPrecio.id_equipo
	public Long id_moneda;				// 2 listaPrecio.id_moneda
	public String fecha;				// 3 listaPrecio.fecha
	public Double precio;				// 4 listaPrecio.precioVenta
	public Long esVariable;				// 6
	public Long vigente;
	
	public String nomBodega;
	public String codServicio;
	public String nomServicio;
	public String nickMoneda;
	public String precioStr;
	public Long id_unidad;
	public String nomUnidad;
	public Long id_claseServicio;
	public String nomClaseServicio;
	
	public Long aplicaMinimo;				// indica si corresponde aplicar valor minimo
	public Double cantMinimo;				// es la cantidad de hr minimas u otra unidad
	public Double precioAdicional;			// es el valor a aplicar sobre el excedente
	
	public String cantMinimoStr;
	public String precioAdicionalStr;
	
	public Long id_cotiOdo;
	public Long numeroCotiOdo;
	
	
	public ListaPrecioServicio(Long id_bodegaEmpresa, Long id_servicio, Long id_moneda, String fecha, Double precio,
			Long esVariable, Long vigente, String nomBodega, String codServicio, String nomServicio, String nickMoneda,
			String precioStr, Long id_unidad, String nomUnidad, Long id_claseServicio, String nomClaseServicio,
			Long aplicaMinimo, Double cantMinimo, Double precioAdicional, String cantMinimoStr,
			String precioAdicionalStr, Long id_cotiOdo, Long numeroCotiOdo) {
		super();
		this.id_bodegaEmpresa = id_bodegaEmpresa;
		this.id_servicio = id_servicio;
		this.id_moneda = id_moneda;
		this.fecha = fecha;
		this.precio = precio;
		this.esVariable = esVariable;
		this.vigente = vigente;
		this.nomBodega = nomBodega;
		this.codServicio = codServicio;
		this.nomServicio = nomServicio;
		this.nickMoneda = nickMoneda;
		this.precioStr = precioStr;
		this.id_unidad = id_unidad;
		this.nomUnidad = nomUnidad;
		this.id_claseServicio = id_claseServicio;
		this.nomClaseServicio = nomClaseServicio;
		this.aplicaMinimo = aplicaMinimo;
		this.cantMinimo = cantMinimo;
		this.precioAdicional = precioAdicional;
		this.cantMinimoStr = cantMinimoStr;
		this.precioAdicionalStr = precioAdicionalStr;
		this.id_cotiOdo = id_cotiOdo;
		this.numeroCotiOdo = numeroCotiOdo;
	}
	public ListaPrecioServicio() {
		super();
	}
	public Long getId_bodegaEmpresa() {
		return id_bodegaEmpresa;
	}
	public void setId_bodegaEmpresa(Long id_bodegaEmpresa) {
		this.id_bodegaEmpresa = id_bodegaEmpresa;
	}
	public Long getId_servicio() {
		return id_servicio;
	}
	public void setId_servicio(Long id_servicio) {
		this.id_servicio = id_servicio;
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
	public Long getEsVariable() {
		return esVariable;
	}
	public void setEsVariable(Long esVariable) {
		this.esVariable = esVariable;
	}
	public Long getVigente() {
		return vigente;
	}
	public void setVigente(Long vigente) {
		this.vigente = vigente;
	}
	public String getNomBodega() {
		return nomBodega;
	}
	public void setNomBodega(String nomBodega) {
		this.nomBodega = nomBodega;
	}
	public String getCodServicio() {
		return codServicio;
	}
	public void setCodServicio(String codServicio) {
		this.codServicio = codServicio;
	}
	public String getNomServicio() {
		return nomServicio;
	}
	public void setNomServicio(String nomServicio) {
		this.nomServicio = nomServicio;
	}
	public String getNickMoneda() {
		return nickMoneda;
	}
	public void setNickMoneda(String nickMoneda) {
		this.nickMoneda = nickMoneda;
	}
	public String getPrecioStr() {
		return precioStr;
	}
	public void setPrecioStr(String precioStr) {
		this.precioStr = precioStr;
	}
	public Long getId_unidad() {
		return id_unidad;
	}
	public void setId_unidad(Long id_unidad) {
		this.id_unidad = id_unidad;
	}
	public String getNomUnidad() {
		return nomUnidad;
	}
	public void setNomUnidad(String nomUnidad) {
		this.nomUnidad = nomUnidad;
	}
	public Long getId_claseServicio() {
		return id_claseServicio;
	}
	public void setId_claseServicio(Long id_claseServicio) {
		this.id_claseServicio = id_claseServicio;
	}
	public String getNomClaseServicio() {
		return nomClaseServicio;
	}
	public void setNomClaseServicio(String nomClaseServicio) {
		this.nomClaseServicio = nomClaseServicio;
	}
	public Long getAplicaMinimo() {
		return aplicaMinimo;
	}
	public void setAplicaMinimo(Long aplicaMinimo) {
		this.aplicaMinimo = aplicaMinimo;
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
	public String getCantMinimoStr() {
		return cantMinimoStr;
	}
	public void setCantMinimoStr(String cantMinimoStr) {
		this.cantMinimoStr = cantMinimoStr;
	}
	public String getPrecioAdicionalStr() {
		return precioAdicionalStr;
	}
	public void setPrecioAdicionalStr(String precioAdicionalStr) {
		this.precioAdicionalStr = precioAdicionalStr;
	}

	public Long getId_cotiOdo() {
		return id_cotiOdo;
	}
	public void setId_cotiOdo(Long id_cotiOdo) {
		this.id_cotiOdo = id_cotiOdo;
	}

	public Long getNumeroCotiOdo() {
		return numeroCotiOdo;
	}
	public void setNumeroCotiOdo(Long numeroCotiOdo) {
		this.numeroCotiOdo = numeroCotiOdo;
	}


	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	
	public static List<List<String>> listaBodegasConServicios(Connection con, String db, String esPorSucursal, String id_sucursal) {
		List<List<String>> lista = new ArrayList<List<String>>();
		
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			if(esPorSucursal.equals("1")) {
				condSucursal = " and bodegaEmpresa.id_sucursal = " + id_sucursal;
			}
		}
		
		try {
			PreparedStatement smt = con
					.prepareStatement("select "
							+ " listaPrecioServicio.id_bodegaEmpresa, "
							+ " bodegaEmpresa.nombre, "
							+ " bodegaEmpresa.id_sucursal, "
							+ " ifnull(cliente.vigente,1) "
							+ " from `"+db+"`.listaPrecioServicio "
							+ " left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = listaPrecioServicio.id_bodegaEmpresa "
							+ " left join `"+db+"`.cliente on cliente.id = bodegaEmpresa.id_cliente "
							+ " where listaPrecioServicio.vigente = 1 and bodegaEmpresa.vigente = 1 " +condSucursal
							+ " group by listaPrecioServicio.id_bodegaEmpresa, bodegaEmpresa.nombre "
							+ " order by bodegaEmpresa.nombre;");
			ResultSet rs = smt.executeQuery();
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			while (rs.next()) {
				String nameSucursal = "";
				Sucursal sucursal  = mapSucursal.get(rs.getLong(3));
				if(sucursal!=null) {
					nameSucursal = sucursal.getNombre();
				}
				List<String> aux = new ArrayList<String>();
				aux.add(rs.getString(1));
				aux.add(rs.getString(2));
				aux.add(nameSucursal);
				aux.add(rs.getString(4));
				lista.add(aux);
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
    		e.printStackTrace();
		}
		return(lista);
	}
	
	public static List<List<String>> allServiciosVigXBod(Connection con, String db, Long id_bodegaEmpresa){
		List<List<String>> lista = new ArrayList<List<String>>();
		BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, db, id_bodegaEmpresa);
		List<ListaPrecioServicio> auxLista = ListaPrecioServicio.allXBodega(con, db, bodega);
		auxLista.forEach(x->{
			if((long)x.vigente == (long)1) {
				List<String> aux = new ArrayList<String>();
				aux.add(x.getId_servicio().toString());
				aux.add(x.getCodServicio());
				aux.add(x.getNomServicio());
				aux.add(x.getNomUnidad());
				aux.add(x.getId_cotiOdo().toString()); 		//4 id_cotiOdo
				aux.add(x.getNumeroCotiOdo().toString());	//5 nro cotiOdo
				lista.add(aux);
			}
		});
		
		return(lista);
	}
	

	public static Map<String,ListaPrecioServicio> mapAllListaPrecioServicio(Connection con, String db){
		Map<String,ListaPrecioServicio> map = new HashMap<String,ListaPrecioServicio>();
		List<ListaPrecioServicio> lista = ListaPrecioServicio.all(con, db);
		lista.forEach(x->{
			if((long)x.getEsVariable() == (long)1) {
				String fechaAAMMDD = Fechas.AAMMDD(x.getFecha());
				Double precio = PrecioVariableServicio.buscaPrecio(con, db, x.getId_bodegaEmpresa(), x.getId_servicio(), fechaAAMMDD, x.getId_cotiOdo());
				if(precio != null) {
					x.setPrecio(precio);
				}
			}
			
			map.put(x.getId_bodegaEmpresa()+"_"+x.getId_servicio()+"_"+x.getId_cotiOdo(), x);
		});
		return(map);
	}
	
	public static Map<String,ListaPrecioServicio> mapListaPrecioServicioXBodega(Connection con, String db, BodegaEmpresa bodega){
		Map<String,ListaPrecioServicio> map = new HashMap<String,ListaPrecioServicio>();
		List<ListaPrecioServicio> lista = ListaPrecioServicio.allXBodega(con, db, bodega);
		lista.forEach(x->{
			map.put(x.getId_bodegaEmpresa()+"_"+x.getId_servicio()+"_"+x.getId_cotiOdo(), x);
		});
		return(map);
	}
	
	public static Map<Long,ListaPrecioServicio> mapListaPrecioServicioXServicio(Connection con, String db, Long id_servicio){
		Map<Long,ListaPrecioServicio> map = new HashMap<Long,ListaPrecioServicio>();
		List<ListaPrecioServicio> lista = ListaPrecioServicio.allXServicio(con, db, id_servicio);
		lista.forEach(x->{
			map.put(x.getId_bodegaEmpresa(), x);
		});
		return(map);
	}
	
	public static List<ListaPrecioServicio> listaServiciosVariablesXBodega(Connection con, String db, BodegaEmpresa bodega){
		List<ListaPrecioServicio> list = new ArrayList<ListaPrecioServicio>();
		List<ListaPrecioServicio> lista = ListaPrecioServicio.allXBodega(con, db, bodega);
		lista.forEach(x->{
			if((long)x.getEsVariable()==(long)1) {
				list.add(x);
			}
		});
		return(list);
	}
	
	public static List<ListaPrecioServicio> allXBodega(Connection con, String db, BodegaEmpresa bodega) {
		List<ListaPrecioServicio> lista = new ArrayList<ListaPrecioServicio>();
		Map<Long,Moneda> mapMoneda = Moneda.mapMonedas(con, db);
		Map<Long,UnidadServicio> mapUnidad = UnidadServicio.mapAll(con, db);
		Map<Long,ClaseServicio> mapClase = ClaseServicio.mapAll(con, db);
		try {
			PreparedStatement smt1 = con
					.prepareStatement(" select "
							+ " listaPrecioServicio.id_bodegaEmpresa,"
							+ " listaPrecioServicio.id_servicio,"
							+ " listaPrecioServicio.id_moneda,"
							+ " listaPrecioServicio.fecha,"
							+ " listaPrecioServicio.precio,"
							+ " listaPrecioServicio.esVariable,"
							+ " servicio.codigo,"
							+ " servicio.nombre,"
							+ " servicio.id_unidadServicio,"
							+ " servicio.id_claseServicio,"
							+ " listaPrecioServicio.vigente, "
							+ " servicio.descripcion, "
							+ " listaPrecioServicio.aplicaMinimo, "
							+ " listaPrecioServicio.cantMinimo, "
							+ " listaPrecioServicio.precioAdicional, "
							+ " listaPrecioServicio.id_cotiOdo, "
							+ " cotiOdo.numero "
							+ " from `"+db+"`.listaPrecioServicio " 
							+ " left join `"+db+"`.servicio on servicio.id = listaPrecioServicio.id_servicio " 
							+ " left join `"+db+"`.cotiOdo on cotiOdo.id = listaPrecioServicio.id_cotiOdo " 
							+ " where listaPrecioServicio.id_bodegaEmpresa = ? " 
							+ " order by servicio.nombre;");
			smt1.setLong(1, bodega.getId());
			ResultSet rs1 = smt1.executeQuery();
		
			while (rs1.next()) {
				Moneda moneda = mapMoneda.get(rs1.getLong(3));
				switch(moneda.getNumeroDecimales().toString()) {
				 	case "0": myformatdouble = new DecimalFormat("#,##0"); break;
				 	case "2": myformatdouble = new DecimalFormat("#,##0.00"); break;
				 	case "4": myformatdouble = new DecimalFormat("#,##0.0000"); break;
				 	case "6": myformatdouble = new DecimalFormat("#,##0.000000"); break;
				 	default:  break;
				}
				String nickMoneda = "";
				if(moneda!=null){
					nickMoneda = moneda.getNickName();
				}
				UnidadServicio unidad = mapUnidad.get(rs1.getLong(9));
				String nombreUnidad = "";
				if(unidad!=null){
					nombreUnidad = unidad.getNombre();
				}
				ClaseServicio claseServicio = mapClase.get(rs1.getLong(10));
				String nombreClase = "";
				if(claseServicio!=null){
					nombreClase = claseServicio.getNombre();
				}
				lista.add(new ListaPrecioServicio(
						rs1.getLong(1),									// 0 listaPrecioServicio.id_bodegaEmpresa
						rs1.getLong(2),									// 1 listaPrecioServicio.id_equipo
						rs1.getLong(3),									// 2 listaPrecioServicio.id_moneda
						rs1.getString(4),								// 3 listaPrecioServicio.fecha
						rs1.getDouble(5),								// 4 listaPrecioServicio.precio double
						rs1.getLong(6),									// 5 listaPrecioServicio.esVariable
						rs1.getLong(11),								// 6 listaPrecioServicio.vigente
						bodega.getNombre(),								// 7 nombre bodega
						rs1.getString(7),								// 8 servicio.codigo
						rs1.getString(8),								// 9 servicio.nombre
						nickMoneda,										// 10 nick moneda
						myformatdouble.format(rs1.getDouble(5)),		// 11 precio str con formato
						rs1.getLong(9),									// 12 servicio.id_unidadServicio
						nombreUnidad,									// 13 nombreUnidadServicio
						rs1.getLong(10),								// 14 servicio.id_claseServicio
						nombreClase,									// 15 nombreClaseServicio
						rs1.getLong(13),								// 16 listaPrecioServicio.aplicaMinimo
						rs1.getDouble(14),								// 17 listaPrecioServicio.cantMinimo
						rs1.getDouble(15),								// 18 listaPrecioServicio.precioAdicional
						myformatdouble2.format(rs1.getDouble(14)),		// 19 cantMinimo con formato
						myformatdouble.format(rs1.getDouble(15)),		// 20 precioAdicional con formato
						rs1.getLong(16),								// 21 listaPrecioServicio.id_cotiOdo
						rs1.getLong(17)									// 22 cotiOdo.numero
					));
			}
			rs1.close();
			smt1.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<ListaPrecioServicio> allXServicio(Connection con, String db, Long id_servicio) {
		List<ListaPrecioServicio> lista = new ArrayList<ListaPrecioServicio>();
		Map<Long,Moneda> mapMoneda = Moneda.mapMonedas(con, db);
		Map<Long,UnidadServicio> mapUnidad = UnidadServicio.mapAll(con, db);
		Map<Long,ClaseServicio> mapClase = ClaseServicio.mapAll(con, db);
		try {
			PreparedStatement smt1 = con
					.prepareStatement(" select "
							+ " listaPrecioServicio.id_bodegaEmpresa,"
							+ " listaPrecioServicio.id_servicio,"
							+ " listaPrecioServicio.id_moneda,"
							+ " listaPrecioServicio.fecha,"
							+ " listaPrecioServicio.precio,"
							+ " listaPrecioServicio.esVariable,"
							+ " servicio.codigo,"
							+ " servicio.nombre,"
							+ " servicio.id_unidadServicio,"
							+ " servicio.id_claseServicio,"
							+ " listaPrecioServicio.vigente, "
							+ " servicio.descripcion, "
							+ " listaPrecioServicio.aplicaMinimo, "
							+ " listaPrecioServicio.cantMinimo, "
							+ " listaPrecioServicio.precioAdicional, "
							+ " listaPrecioServicio.id_cotiOdo, "
							+ " cotiOdo.numero "
							+ " from `"+db+"`.listaPrecioServicio " 
							+ " left join `"+db+"`.servicio on servicio.id = listaPrecioServicio.id_servicio " 
							+ " left join `"+db+"`.cotiOdo on cotiOdo.id = listaPrecioServicio.id_cotiOdo " 
							+ " where listaPrecioServicio.id_servicio = ? ;");
			smt1.setLong(1, id_servicio);
			ResultSet rs1 = smt1.executeQuery();
			
			Map<Long,BodegaEmpresa> mapBodega = BodegaEmpresa.mapAll(con, db);
		
			while (rs1.next()) {
				Moneda moneda = mapMoneda.get(rs1.getLong(3));
				switch(moneda.getNumeroDecimales().toString()) {
				 	case "0": myformatdouble = new DecimalFormat("#,##0"); break;
				 	case "2": myformatdouble = new DecimalFormat("#,##0.00"); break;
				 	case "4": myformatdouble = new DecimalFormat("#,##0.0000"); break;
				 	case "6": myformatdouble = new DecimalFormat("#,##0.000000"); break;
				 	default:  break;
				}
				String nickMoneda = "";
				if(moneda!=null){
					nickMoneda = moneda.getNickName();
				}
				UnidadServicio unidad = mapUnidad.get(rs1.getLong(9));
				String nombreUnidad = "";
				if(unidad!=null){
					nombreUnidad = unidad.getNombre();
				}
				ClaseServicio claseServicio = mapClase.get(rs1.getLong(10));
				String nombreClase = "";
				if(claseServicio!=null){
					nombreClase = claseServicio.getNombre();
				}
				BodegaEmpresa bodega = mapBodega.get(rs1.getLong(1));
				if(bodega!=null) {
					lista.add(new ListaPrecioServicio(
							rs1.getLong(1),									// 0 listaPrecioServicio.id_bodegaEmpresa
							rs1.getLong(2),									// 1 listaPrecioServicio.id_equipo
							rs1.getLong(3),									// 2 listaPrecioServicio.id_moneda
							rs1.getString(4),								// 3 listaPrecioServicio.fecha
							rs1.getDouble(5),								// 4 listaPrecioServicio.precio double
							rs1.getLong(6),									// 5 listaPrecioServicio.esVariable
							rs1.getLong(11),								// 6 listaPrecioServicio.vigente
							bodega.getNombre(),								// 7 nombre bodega
							rs1.getString(7),								// 8 servicio.codigo
							rs1.getString(8),								// 9 servicio.nombre
							nickMoneda,										// 10 nick moneda
							myformatdouble.format(rs1.getDouble(5)),		// 11 precio str con formato
							rs1.getLong(9),									// 12 servicio.id_unidadServicio
							nombreUnidad,									// 13 nombreUnidadServicio
							rs1.getLong(10),								// 14 servicio.id_claseServicio
							nombreClase,									// 15 nombreClaseServicio
							rs1.getLong(13),								// 16 listaPrecioServicio.aplicaMinimo
							rs1.getDouble(14),								// 17 listaPrecioServicio.cantMinimo
							rs1.getDouble(15),								// 18 listaPrecioServicio.precioAdicional
							myformatdouble2.format(rs1.getDouble(14)),		// 19 cantMinimo con formato
							myformatdouble.format(rs1.getDouble(15)),		// 20 precioAdicional con formato
							rs1.getLong(16),								// 21 listaPrecioServicio.id_cotiOdo
							rs1.getLong(17)									// 22 cotiOdo.numero
						));
				}
			}
			rs1.close();
			smt1.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<ListaPrecioServicio> allXServicioClienteVig(Connection con, String db, Long id_servicio) {
		List<ListaPrecioServicio> lista = new ArrayList<ListaPrecioServicio>();
		Map<Long,Moneda> mapMoneda = Moneda.mapMonedas(con, db);
		Map<Long,UnidadServicio> mapUnidad = UnidadServicio.mapAll(con, db);
		Map<Long,ClaseServicio> mapClase = ClaseServicio.mapAll(con, db);
		try {
			PreparedStatement smt1 = con
					.prepareStatement(" select "
							+ " listaPrecioServicio.id_bodegaEmpresa,"
							+ " listaPrecioServicio.id_servicio,"
							+ " listaPrecioServicio.id_moneda,"
							+ " listaPrecioServicio.fecha,"
							+ " listaPrecioServicio.precio,"
							+ " listaPrecioServicio.esVariable,"
							+ " servicio.codigo,"
							+ " servicio.nombre,"
							+ " servicio.id_unidadServicio,"
							+ " servicio.id_claseServicio,"
							+ " listaPrecioServicio.vigente, "
							+ " servicio.descripcion, "
							+ " listaPrecioServicio.aplicaMinimo, "
							+ " listaPrecioServicio.cantMinimo, "
							+ " listaPrecioServicio.precioAdicional, "
							+ " listaPrecioServicio.id_cotiOdo, "
							+ " cotiOdo.numero "
							+ " from `"+db+"`.listaPrecioServicio " 
							+ " left join `"+db+"`.servicio on servicio.id = listaPrecioServicio.id_servicio " 
							+ " left join `"+db+"`.cotiOdo on cotiOdo.id = listaPrecioServicio.id_cotiOdo " 
							+ " where listaPrecioServicio.id_servicio = ? ;");
			smt1.setLong(1, id_servicio);
			ResultSet rs1 = smt1.executeQuery();
			
			Map<Long,BodegaEmpresa> mapBodega = BodegaEmpresa.mapAll(con, db);
		
			while (rs1.next()) {
				Moneda moneda = mapMoneda.get(rs1.getLong(3));
				switch(moneda.getNumeroDecimales().toString()) {
				 	case "0": myformatdouble = new DecimalFormat("#,##0"); break;
				 	case "2": myformatdouble = new DecimalFormat("#,##0.00"); break;
				 	case "4": myformatdouble = new DecimalFormat("#,##0.0000"); break;
				 	case "6": myformatdouble = new DecimalFormat("#,##0.000000"); break;
				 	default:  break;
				}
				String nickMoneda = "";
				if(moneda!=null){
					nickMoneda = moneda.getNickName();
				}
				UnidadServicio unidad = mapUnidad.get(rs1.getLong(9));
				String nombreUnidad = "";
				if(unidad!=null){
					nombreUnidad = unidad.getNombre();
				}
				ClaseServicio claseServicio = mapClase.get(rs1.getLong(10));
				String nombreClase = "";
				if(claseServicio!=null){
					nombreClase = claseServicio.getNombre();
				}
				BodegaEmpresa bodega = mapBodega.get(rs1.getLong(1));
				if(bodega!=null && bodega.getClienteVigente() == (long)1) {
					lista.add(new ListaPrecioServicio(
							rs1.getLong(1),									// 0 listaPrecioServicio.id_bodegaEmpresa
							rs1.getLong(2),									// 1 listaPrecioServicio.id_equipo
							rs1.getLong(3),									// 2 listaPrecioServicio.id_moneda
							rs1.getString(4),								// 3 listaPrecioServicio.fecha
							rs1.getDouble(5),								// 4 listaPrecioServicio.precio double
							rs1.getLong(6),									// 5 listaPrecioServicio.esVariable
							rs1.getLong(11),								// 6 listaPrecioServicio.vigente
							bodega.getNombre(),								// 7 nombre bodega
							rs1.getString(7),								// 8 servicio.codigo
							rs1.getString(8),								// 9 servicio.nombre
							nickMoneda,										// 10 nick moneda
							myformatdouble.format(rs1.getDouble(5)),		// 11 precio str con formato
							rs1.getLong(9),									// 12 servicio.id_unidadServicio
							nombreUnidad,									// 13 nombreUnidadServicio
							rs1.getLong(10),								// 14 servicio.id_claseServicio
							nombreClase,									// 15 nombreClaseServicio
							rs1.getLong(13),								// 16 listaPrecioServicio.aplicaMinimo
							rs1.getDouble(14),								// 17 listaPrecioServicio.cantMinimo
							rs1.getDouble(15),								// 18 listaPrecioServicio.precioAdicional
							myformatdouble2.format(rs1.getDouble(14)),		// 19 cantMinimo con formato
							myformatdouble.format(rs1.getDouble(15)),		// 20 precioAdicional con formato
							rs1.getLong(16),								// 21 listaPrecioServicio.id_cotiOdo
							rs1.getLong(17)									// 22 cotiOdo.numero
						));
				}
			}
			rs1.close();
			smt1.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<ListaPrecioServicio> all(Connection con, String db) {
		List<ListaPrecioServicio> lista = new ArrayList<ListaPrecioServicio>();
		Map<Long,Moneda> mapMoneda = Moneda.mapMonedas(con, db);
		Map<Long,UnidadServicio> mapUnidad = UnidadServicio.mapAll(con, db);
		Map<Long,ClaseServicio> mapClase = ClaseServicio.mapAll(con, db);
		Map<Long,BodegaEmpresa> mapBodegas = BodegaEmpresa.mapAll(con, db);
		try {
			PreparedStatement smt1 = con
					.prepareStatement(" select "
							+ " listaPrecioServicio.id_bodegaEmpresa,"
							+ " listaPrecioServicio.id_servicio,"
							+ " listaPrecioServicio.id_moneda,"
							+ " listaPrecioServicio.fecha,"
							+ " listaPrecioServicio.precio,"
							+ " listaPrecioServicio.esVariable,"
							+ " servicio.codigo,"
							+ " servicio.nombre,"
							+ " servicio.id_unidadServicio,"
							+ " servicio.id_claseServicio,"
							+ " listaPrecioServicio.vigente, "
							+ " servicio.descripcion, "
							+ " listaPrecioServicio.aplicaMinimo, "
							+ " listaPrecioServicio.cantMinimo, "
							+ " listaPrecioServicio.precioAdicional, "
							+ " listaPrecioServicio.id_cotiOdo, "
							+ " cotiOdo.numero "
							+ " from `"+db+"`.listaPrecioServicio " 
							+ " left join `"+db+"`.servicio on servicio.id = listaPrecioServicio.id_servicio " 
							+ " left join `"+db+"`.cotiOdo on cotiOdo.id = listaPrecioServicio.id_cotiOdo " 
							+ " order by servicio.nombre;");
			ResultSet rs1 = smt1.executeQuery();
		
			while (rs1.next()) {
				Moneda moneda = mapMoneda.get(rs1.getLong(3));
				switch(moneda.getNumeroDecimales().toString()) {
				 	case "0": myformatdouble = new DecimalFormat("#,##0"); break;
				 	case "2": myformatdouble = new DecimalFormat("#,##0.00"); break;
				 	case "4": myformatdouble = new DecimalFormat("#,##0.0000"); break;
				 	case "6": myformatdouble = new DecimalFormat("#,##0.000000"); break;
				 	default:  break;
				}
				String nickMoneda = "";
				if(moneda!=null){
					nickMoneda = moneda.getNickName();
				}
				UnidadServicio unidad = mapUnidad.get(rs1.getLong(9));
				String nombreUnidad = "";
				if(unidad!=null){
					nombreUnidad = unidad.getNombre();
				}
				ClaseServicio claseServicio = mapClase.get(rs1.getLong(10));
				String nombreClase = "";
				if(claseServicio!=null){
					nombreClase = claseServicio.getNombre();
				}
				String nomBodega = "";
				BodegaEmpresa bodega = mapBodegas.get(rs1.getLong(1));
				if(bodega!=null) {
					nomBodega = bodega.getNombre();
				}
				
				lista.add(new ListaPrecioServicio(
						rs1.getLong(1),									// 0 listaPrecioServicio.id_bodegaEmpresa
						rs1.getLong(2),									// 1 listaPrecioServicio.id_equipo
						rs1.getLong(3),									// 2 listaPrecioServicio.id_moneda
						rs1.getString(4),								// 3 listaPrecioServicio.fecha
						rs1.getDouble(5),								// 4 listaPrecioServicio.precio double
						rs1.getLong(6),									// 5 listaPrecioServicio.esVariable
						rs1.getLong(11),								// 6 listaPrecioServicio.vigente
						nomBodega,										// 7 nombre bodega
						rs1.getString(7),								// 8 servicio.codigo
						rs1.getString(8),								// 9 servicio.nombre
						nickMoneda,										// 10 nick moneda
						myformatdouble.format(rs1.getDouble(5)),		// 11 precio str con formato
						rs1.getLong(9),									// 12 servicio.id_unidadServicio
						nombreUnidad,									// 13 nombreUnidadServicio
						rs1.getLong(10),								// 14 servicio.id_claseServicio
						nombreClase,									// 15 nombreClaseServicio
						rs1.getLong(13),								// 16 listaPrecioServicio.aplicaMinimo
						rs1.getDouble(14),								// 17 listaPrecioServicio.cantMinimo
						rs1.getDouble(15),								// 18 listaPrecioServicio.precioAdicional
						myformatdouble2.format(rs1.getDouble(14)),		// 19 cantMinimo con formato
						myformatdouble.format(rs1.getDouble(15)),		// 20 precioAdicional con formato
						rs1.getLong(16),								// 21 listaPrecioServicio.id_cotiOdo
						rs1.getLong(17)									// 22 cotiOdo.numero
					));
				
			}
			rs1.close();
			smt1.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static ListaPrecioServicio find(Connection con, String db, BodegaEmpresa bodega, Long id_servicio) {
		ListaPrecioServicio aux = null;
		Map<Long,Moneda> mapMoneda = Moneda.mapMonedas(con, db);
		Map<Long,UnidadServicio> mapUnidad = UnidadServicio.mapAll(con, db);
		Map<Long,ClaseServicio> mapClase = ClaseServicio.mapAll(con, db);
		try {
			PreparedStatement smt1 = con
					.prepareStatement(" select "
							+ " listaPrecioServicio.id_bodegaEmpresa,"
							+ " listaPrecioServicio.id_servicio,"
							+ " listaPrecioServicio.id_moneda,"
							+ " listaPrecioServicio.fecha,"
							+ " listaPrecioServicio.precio,"
							+ " listaPrecioServicio.esVariable,"
							+ " servicio.codigo,"
							+ " servicio.nombre,"
							+ " servicio.id_unidadServicio,"
							+ " servicio.id_claseServicio,"
							+ " listaPrecioServicio.vigente, "
							+ " servicio.descripcion, "
							+ " listaPrecioServicio.aplicaMinimo, "
							+ " listaPrecioServicio.cantMinimo, "
							+ " listaPrecioServicio.precioAdicional, "
							+ " listaPrecioServicio.id_cotiOdo, "
							+ " cotiOdo.numero "
							+ " from `"+db+"`.listaPrecioServicio " 
							+ " left join `"+db+"`.servicio on servicio.id = listaPrecioServicio.id_servicio " 
							+ " left join `"+db+"`.cotiOdo on cotiOdo.id = listaPrecioServicio.id_cotiOdo " 
							+ " where listaPrecioServicio.id_bodegaEmpresa = ? and listaPrecioServicio.id_servicio=?;");
			smt1.setLong(1, bodega.getId());
			smt1.setLong(2, id_servicio);
			
			ResultSet rs1 = smt1.executeQuery();
		
			if (rs1.next()) {
				Moneda moneda = mapMoneda.get(rs1.getLong(3));
				switch(moneda.getNumeroDecimales().toString()) {
				 	case "0": myformatdouble = new DecimalFormat("#,##0"); break;
				 	case "2": myformatdouble = new DecimalFormat("#,##0.00"); break;
				 	case "4": myformatdouble = new DecimalFormat("#,##0.0000"); break;
				 	case "6": myformatdouble = new DecimalFormat("#,##0.000000"); break;
				 	default:  break;
				}
				String nickMoneda = "";
				if(moneda!=null){
					nickMoneda = moneda.getNickName();
				}
				UnidadServicio unidad = mapUnidad.get(rs1.getLong(9));
				String nombreUnidad = "";
				if(unidad!=null){
					nombreUnidad = unidad.getNombre();
				}
				ClaseServicio claseServicio = mapClase.get(rs1.getLong(10));
				String nombreClase = "";
				if(claseServicio!=null){
					nombreClase = claseServicio.getNombre();
				}
				aux = new ListaPrecioServicio(
						rs1.getLong(1),									// 0 listaPrecioServicio.id_bodegaEmpresa
						rs1.getLong(2),									// 1 listaPrecioServicio.id_equipo
						rs1.getLong(3),									// 2 listaPrecioServicio.id_moneda
						rs1.getString(4),								// 3 listaPrecioServicio.fecha
						rs1.getDouble(5),								// 4 listaPrecioServicio.precio double
						rs1.getLong(6),									// 5 listaPrecioServicio.esVariable
						rs1.getLong(11),								// 6 listaPrecioServicio.vigente
						bodega.getNombre(),								// 7 nombre bodega
						rs1.getString(7),								// 8 servicio.codigo
						rs1.getString(8),								// 9 servicio.nombre
						nickMoneda,										// 10 nick moneda
						myformatdouble.format(rs1.getDouble(5)),		// 11 precio str con formato
						rs1.getLong(9),									// 12 servicio.id_unidadServicio
						nombreUnidad,									// 13 nombreUnidadServicio
						rs1.getLong(10),								// 14 servicio.id_claseServicio
						nombreClase,									// 15 nombreClaseServicio
						rs1.getLong(13),								// 16 listaPrecioServicio.aplicaMinimo
						rs1.getDouble(14),								// 17 listaPrecioServicio.cantMinimo
						rs1.getDouble(15),								// 18 listaPrecioServicio.precioAdicional
						myformatdouble2.format(rs1.getDouble(14)),		// 19 cantMinimo con formato
						myformatdouble.format(rs1.getDouble(15)),		// 20 precioAdicional con formato
						rs1.getLong(16),								// 21 listaPrecioServicio.id_cotiOdo
						rs1.getLong(17)									// 22 cotiOdo.numero
					);
			}
			rs1.close();
			smt1.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (aux);
	}
	
	public static boolean insert(Connection con, String db, Long id_servicio, Long id_bodegaEmpresa, Long id_cotiOdo) {
		boolean flag=false;
		Servicio servicio = Servicio.find(con, db, id_servicio);
		Fechas hoy = Fechas.hoy();
		try {
			PreparedStatement smt2 = con
					.prepareStatement("select id_servicio from `"+db+"`.listaPrecioServicio where id_bodegaEmpresa=? and id_servicio=?;");
			smt2.setLong(1, id_bodegaEmpresa);
			smt2.setLong(2, id_servicio);
			ResultSet rs2 = smt2.executeQuery();
			if(!rs2.next() && servicio!=null) {
				PreparedStatement smt1 = con
						.prepareStatement("insert into `"+db+"`.listaPrecioServicio "
								+ " (id_bodegaEmpresa, id_servicio, id_moneda, fecha, precio, esVariable, vigente, id_cotiOdo, cantMinimo, precioAdicional) "
								+ " values (?,?,?,?,?,?,?,?,?,?);");
				smt1.setLong(1, id_bodegaEmpresa);
				smt1.setLong(2, id_servicio);
				smt1.setLong(3, servicio.getId_moneda());
				smt1.setString(4, hoy.getFechaStrAAMMDD());
				smt1.setDouble(5, servicio.getPrecio());
				smt1.setLong(6, (long)0);
				smt1.setLong(7, (long)1);
				smt1.setLong(8, id_cotiOdo);
				smt1.setDouble(9, servicio.getCantMinimo());
				smt1.setDouble(10, servicio.getPrecioAdicional());
				smt1.executeUpdate();
				smt1.close();
				flag=true;
			}
			smt2.close();
			rs2.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean modifyPorCampo(Connection con, String db, String campo, String valor, Long id_bodegaEmpresa, Long id_servicio, Long id_cotiOdo) {
		boolean flag = false;
		try {
			Fechas hoy = Fechas.hoy();
			PreparedStatement smt1 = con
						.prepareStatement("update `"+db+"`.listaPrecioServicio set `" + campo + "` = ?, fecha = ?" +
								" WHERE id_bodegaEmpresa = ? and id_servicio = ? and id_cotiOdo = ?;");
			smt1.setString(1, valor);
			smt1.setString(2, hoy.getFechaStrAAMMDD());
			smt1.setLong(3, id_bodegaEmpresa);
			smt1.setLong(4, id_servicio);
			smt1.setLong(5, id_cotiOdo);
			smt1.executeUpdate();
			smt1.close();
			flag = true;
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean insertOtOdoConfirmada(Connection con, String db, String insertListado) {
		boolean flag=false;
		try {
				PreparedStatement smt1 = con
						.prepareStatement("insert into `"+db+"`.listaPrecioServicio "
								+ " (id_bodegaEmpresa, id_servicio, id_moneda, fecha, precio, esVariable, vigente, aplicaMinimo, cantidadMinimo, precioAdicional, id_cotiOdo) "
								+ " values "+insertListado+";");
				smt1.executeUpdate();
				smt1.close();
				flag=true;
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}
	

		

}
