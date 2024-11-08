package models.utilities;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;




public class Fechas{
	public java.sql.Date fechaSql;
	public java.util.Date fechaUtil;
	public java.util.Calendar fechaCal;
	public String fechaStr;
	public String fechaStrDDMMAA;
	public String fechaStrAAMMDD;
	
	public Fechas(java.sql.Date fechaSql, Date fechaUtil, Calendar fechaCal, String fechaStr, String fechaStrDDMMAA,
			String fechaStrAAMMDD) {
		super();
		this.fechaSql = fechaSql;
		this.fechaUtil = fechaUtil;
		this.fechaCal = fechaCal;
		this.fechaStr = fechaStr;
		this.fechaStrDDMMAA = fechaStrDDMMAA;
		this.fechaStrAAMMDD = fechaStrAAMMDD;
	}

	public Fechas() {super();}

	public java.sql.Date getFechaSql() {return fechaSql;}
	public void setFechaSql(java.sql.Date fechaSql) {this.fechaSql = fechaSql;}
	public java.util.Date getFechaUtil() {return fechaUtil;}
	public void setFechaUtil(java.util.Date fechaUtil) {this.fechaUtil = fechaUtil;}
	public java.util.Calendar getFechaCal() {return fechaCal;}
	public void setFechaCal(java.util.Calendar fechaCal) {this.fechaCal = fechaCal;}
	public String getFechaStr() {return fechaStr;}
	public void setFechaStr(String fechaStr) {this.fechaStr = fechaStr;}
	public String getFechaStrDDMMAA() {return fechaStrDDMMAA;}
	public void setFechaStrDDMMAA(String fechaStrDDMMAA) {this.fechaStrDDMMAA = fechaStrDDMMAA;}
	public String getFechaStrAAMMDD() {return fechaStrAAMMDD;}
	public void setFechaStrAAMMDD(String fechaStrAAMMDD) {this.fechaStrAAMMDD = fechaStrAAMMDD;}


	static SimpleDateFormat myformatfecha = new SimpleDateFormat("dd-MM-yyyy");
	static SimpleDateFormat myformatDDMMAA = new SimpleDateFormat("dd-MM-yyyy");
	static SimpleDateFormat myformatAAMMDD = new SimpleDateFormat("yyyy-MM-dd");

	
	public static Fechas hoy() {
		Fechas fecha = new Fechas();
		java.util.Date auxUtil = new Date();
		java.util.Calendar auxCal = Calendar.getInstance();
		java.sql.Date auxSql = new java.sql.Date(auxUtil.getTime());
		String auxStr = myformatfecha.format(auxUtil);
		String auxStrDDMMAA = myformatDDMMAA.format(auxUtil);
		String auxStrAAMMDD = myformatAAMMDD.format(auxUtil);
		fecha.setFechaCal(auxCal);
		fecha.setFechaSql(auxSql);
		fecha.setFechaStr(auxStr);
		fecha.setFechaUtil(auxUtil);
		fecha.setFechaStrDDMMAA(auxStrDDMMAA);
		fecha.setFechaStrAAMMDD(auxStrAAMMDD);
		return(fecha);
	}
	
	public static Fechas hoyChile() {
		Fechas fecha = new Fechas();
		java.util.Date auxUtil = new Date();
		java.util.Calendar auxCal = Calendar.getInstance();
		java.sql.Date auxSql = new java.sql.Date(auxUtil.getTime());
		String auxStr = myformatfecha.format(auxUtil);
		String auxStrDDMMAA = myformatDDMMAA.format(auxUtil);
		String auxStrAAMMDD = myformatAAMMDD.format(auxUtil);
		fecha.setFechaCal(auxCal);
		fecha.setFechaSql(auxSql);
		fecha.setFechaStr(auxStr);
		fecha.setFechaUtil(auxUtil);
		fecha.setFechaStrDDMMAA(auxStrDDMMAA);
		fecha.setFechaStrAAMMDD(auxStrAAMMDD);
		
		int mesHoy = auxCal.get(Calendar.MONTH);
		int diaHoy = auxCal.get(Calendar.DAY_OF_MONTH);
		
		if(mesHoy > 8 || diaHoy < 4) {
			Calendar primerSab = Calendar.getInstance();
			primerSab.set(Calendar.DAY_OF_MONTH, 1);
			while (primerSab.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
	            primerSab.add(Calendar.DAY_OF_MONTH, 1);
	        }
			
			Calendar segunDom = Calendar.getInstance();
			segunDom.set(Calendar.DAY_OF_MONTH, 1);
			while (segunDom.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
				segunDom.add(Calendar.DAY_OF_MONTH, 1);
	        }
			segunDom.add(Calendar.DAY_OF_MONTH, 1);
			segunDom.set(Calendar.DAY_OF_MONTH, 1);
			while (segunDom.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
				segunDom.add(Calendar.DAY_OF_MONTH, 1);
	        }
			
			if(primerSab.before(auxCal) && auxCal.before(segunDom)) {
				fecha = Fechas.addHoras(auxCal, -3);
			}else {
				fecha = Fechas.addHoras(auxCal, -4);
			}
		} else {
			fecha = Fechas.addHoras(auxCal, -4);
		}
		
		return(fecha);
	}
	
	public static Fechas addHoras(java.util.Calendar auxCal, int horas) {
		Fechas fecha = new Fechas();
		auxCal.add(Calendar.HOUR_OF_DAY, horas);
		java.util.Date auxUtil = auxCal.getTime();
		java.sql.Date auxSql = new java.sql.Date(auxUtil.getTime());
		String auxStr = myformatfecha.format(auxUtil);
		String auxStrDDMMAA = myformatDDMMAA.format(auxUtil);
		String auxStrAAMMDD = myformatAAMMDD.format(auxUtil);
		fecha.setFechaCal(auxCal);
		fecha.setFechaSql(auxSql);
		fecha.setFechaStr(auxStr);
		fecha.setFechaUtil(auxUtil);
		fecha.setFechaStrDDMMAA(auxStrDDMMAA);
		fecha.setFechaStrAAMMDD(auxStrAAMMDD);
		return(fecha);
	}
	
	public static Fechas addDias(java.util.Calendar auxCal,int dias) {
		Fechas fecha = new Fechas();
		auxCal.add(Calendar.DAY_OF_MONTH, dias);
		java.util.Date auxUtil = auxCal.getTime();
		java.sql.Date auxSql = new java.sql.Date(auxUtil.getTime());
		String auxStr = myformatfecha.format(auxUtil);
		String auxStrDDMMAA = myformatDDMMAA.format(auxUtil);
		String auxStrAAMMDD = myformatAAMMDD.format(auxUtil);
		fecha.setFechaCal(auxCal);
		fecha.setFechaSql(auxSql);
		fecha.setFechaStr(auxStr);
		fecha.setFechaUtil(auxUtil);
		fecha.setFechaStrDDMMAA(auxStrDDMMAA);
		fecha.setFechaStrAAMMDD(auxStrAAMMDD);
		return(fecha);
	}
	
	public static Fechas addMeses(java.util.Calendar auxCal, int meses) {
		Fechas fecha = new Fechas();
		auxCal.add(Calendar.MONTH,meses);
		java.util.Date auxUtil = auxCal.getTime();
		java.sql.Date auxSql = new java.sql.Date(auxUtil.getTime());
		String auxStr = myformatfecha.format(auxUtil);
		String auxStrDDMMAA = myformatDDMMAA.format(auxUtil);
		String auxStrAAMMDD = myformatAAMMDD.format(auxUtil);
		fecha.setFechaCal(auxCal);
		fecha.setFechaSql(auxSql);
		fecha.setFechaStr(auxStr);
		fecha.setFechaUtil(auxUtil);
		fecha.setFechaStrDDMMAA(auxStrDDMMAA);
		fecha.setFechaStrAAMMDD(auxStrAAMMDD);
		return(fecha);
	}

	public static Fechas obtenerFinMes(java.util.Calendar auxCal) {
		Fechas fecha = new Fechas();
		auxCal.set(auxCal.get(Calendar.YEAR), auxCal.get(Calendar.MONTH), auxCal.getActualMaximum(Calendar.DAY_OF_MONTH));
		java.util.Date auxUtil = auxCal.getTime();
		java.sql.Date auxSql = new java.sql.Date(auxUtil.getTime());
		String auxStr = myformatfecha.format(auxUtil);
		String auxStrDDMMAA = myformatDDMMAA.format(auxUtil);
		String auxStrAAMMDD = myformatAAMMDD.format(auxUtil);
		fecha.setFechaCal(auxCal);
		fecha.setFechaSql(auxSql);
		fecha.setFechaStr(auxStr);
		fecha.setFechaUtil(auxUtil);
		fecha.setFechaStrDDMMAA(auxStrDDMMAA);
		fecha.setFechaStrAAMMDD(auxStrAAMMDD);
		return(fecha);
	}
	
	public static Fechas obtenerInicioMes(java.util.Calendar auxCal) {
		Fechas fecha = new Fechas();
		auxCal.set(auxCal.get(Calendar.YEAR), auxCal.get(Calendar.MONTH), auxCal.getActualMinimum(Calendar.DAY_OF_MONTH));
		java.util.Date auxUtil = auxCal.getTime();
		java.sql.Date auxSql = new java.sql.Date(auxUtil.getTime());
		String auxStr = myformatfecha.format(auxUtil);
		String auxStrDDMMAA = myformatDDMMAA.format(auxUtil);
		String auxStrAAMMDD = myformatAAMMDD.format(auxUtil);
		fecha.setFechaCal(auxCal);
		fecha.setFechaSql(auxSql);
		fecha.setFechaStr(auxStr);
		fecha.setFechaUtil(auxUtil);
		fecha.setFechaStrDDMMAA(auxStrDDMMAA);
		fecha.setFechaStrAAMMDD(auxStrAAMMDD);
		return(fecha);
	}

	public static Fechas obtenerFechaDesdeStrAAMMDD(String fechaSqlComoTexto) {
		SimpleDateFormat formatsql = new SimpleDateFormat("yyyy-MM-dd");
		Fechas fecha = new Fechas();
		java.util.Date auxUtil = new java.util.Date();
		String aux[] = fechaSqlComoTexto.split("-");
		if(aux.length<3) return(Fechas.hoy());
		try { auxUtil = formatsql.parse(aux[0]+"-"+aux[1]+"-"+aux[2]); }catch(ParseException e) {return(Fechas.hoy());}
        java.util.Calendar auxCal = Calendar.getInstance();
        auxCal.setTime(auxUtil);
		java.sql.Date auxSql = new java.sql.Date(auxUtil.getTime());
		String auxStr = myformatfecha.format(auxUtil);
		String auxStrDDMMAA = myformatDDMMAA.format(auxUtil);
		String auxStrAAMMDD = myformatAAMMDD.format(auxUtil);
		fecha.setFechaCal(auxCal);
		fecha.setFechaSql(auxSql);
		fecha.setFechaStr(auxStr);
		fecha.setFechaUtil(auxUtil);
		fecha.setFechaStrDDMMAA(auxStrDDMMAA);
		fecha.setFechaStrAAMMDD(auxStrAAMMDD);
		return(fecha);
	}
	
	public static Fechas obtenerFechaDesdeStrDDMMAA(String fechaStrComoTexto) {
		SimpleDateFormat formatsql = new SimpleDateFormat("yyyy-MM-dd");
		Fechas fecha = new Fechas();
		java.util.Date auxUtil = new java.util.Date();
		String aux[] = fechaStrComoTexto.split("-");
		if(aux.length<3) return(Fechas.hoy());
		try { auxUtil = formatsql.parse(aux[2]+"-"+aux[1]+"-"+aux[0]); }catch(ParseException e) {}
        java.util.Calendar auxCal = Calendar.getInstance();
        auxCal.setTime(auxUtil);
		java.sql.Date auxSql = new java.sql.Date(auxUtil.getTime());
		String auxStr = myformatfecha.format(auxUtil);
		String auxStrDDMMAA = myformatDDMMAA.format(auxUtil);
		String auxStrAAMMDD = myformatAAMMDD.format(auxUtil);
		fecha.setFechaCal(auxCal);
		fecha.setFechaSql(auxSql);
		fecha.setFechaStr(auxStr);
		fecha.setFechaUtil(auxUtil);
		fecha.setFechaStrDDMMAA(auxStrDDMMAA);
		fecha.setFechaStrAAMMDD(auxStrAAMMDD);
		return(fecha);
	}
	
	public static String AAMMDD(String fechaSTR) {
		if(fechaSTR==null) {
			return(null);
		}else {
			String aux[] = fechaSTR.split("-");
			if(aux[0].length()==2||aux[0].length()==1) {
				fechaSTR = aux[2]+"-"+aux[1]+"-"+aux[0];
			}
			return (fechaSTR);
		}
		
	}
	
	public static String DDMMAA(String fechaSTR) {
		if(fechaSTR==null) {
			return(null);
		}else {
			String aux[] = fechaSTR.split("-");
			if(aux[0].length()==4) {
				fechaSTR = aux[2]+"-"+aux[1]+"-"+aux[0];
			}
			return (fechaSTR);
		}
	}
	
	public static int diasEntreFechas(Calendar desde, Calendar hasta) {
		Long milis1 = desde.getTimeInMillis();
        Long milis2 = hasta.getTimeInMillis();
        Long diff = milis2 - milis1;
        int dias = (int) (Math.round((double)diff / (24 * 60 * 60 * 1000)));
		return (dias);
	}
	
	public static Fechas obtenerFechaDesdeDate(Date date) {
		Fechas fecha = new Fechas();
		java.util.Date auxUtil = date;
        java.util.Calendar auxCal = Calendar.getInstance();
        auxCal.setTime(auxUtil);
		java.sql.Date auxSql = new java.sql.Date(auxUtil.getTime());
		String auxStr = myformatfecha.format(auxUtil);
		String auxStrDDMMAA = myformatDDMMAA.format(auxUtil);
		String auxStrAAMMDD = myformatAAMMDD.format(auxUtil);
		fecha.setFechaCal(auxCal);
		fecha.setFechaSql(auxSql);
		fecha.setFechaStr(auxStr);
		fecha.setFechaUtil(auxUtil);
		fecha.setFechaStrDDMMAA(auxStrDDMMAA);
		fecha.setFechaStrAAMMDD(auxStrAAMMDD);
		return(fecha);
	}
	
	
	

	

}
