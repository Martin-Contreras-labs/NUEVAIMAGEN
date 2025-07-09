package models.reports;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

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

import models.tables.Precio;
import models.tables.TasasCambio;
import models.tables.UnidadTiempo;
import models.utilities.Archivos;
import models.utilities.Fechas;

public class ReportHojaVida {
	static DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
	static DecimalFormat myformatint = new DecimalFormat("#,##0",symbols);
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00",symbols);
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00",symbols);
	
	
	public static List<List<String>> reportDiasNoOperativo(Connection con, String db, String pais, Long id_sucursal) {

		List<List<String>> lista = new ArrayList<List<String>>();
		List<List<String>> equipos = new ArrayList<List<String>>();
		try {
			PreparedStatement smt22 = con
					.prepareStatement("select id_equipo from `"+db+"`.baja where esModificable=0");
			String listaCond ="";
			ResultSet rs22 = smt22.executeQuery();
			while (rs22.next()) {
				listaCond = listaCond + rs22.getString(1) + ",";
			}
			rs22.close();
			smt22.close();
			if(listaCond.length()>1) {
				listaCond = listaCond.substring(0,listaCond.length()-1);
				PreparedStatement smt1 = con
						.prepareStatement(" select distinct id_equipo, codigo,nombre " +
								" from `"+db+"`.hojaVida  " +
								" left join `"+db+"`.equipo on equipo.id=id_equipo " +
								" where id_equipo not in " +
								" ("+listaCond+") " +
								" order by nombre;");
				ResultSet rs1 = smt1.executeQuery();
				while (rs1.next()) {
					List<String> aux = new ArrayList<String>();
					aux.add(rs1.getString(1));
					aux.add(rs1.getString(2));
					aux.add(rs1.getString(3));
					equipos.add(aux);
				}
				rs1.close();
				smt1.close();
			}
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		
		Map<String,List<String>> costos30 = reportCostoMantencionXequipoPeriodo(con, db,(long)30,pais, id_sucursal);
		Map<String,List<String>> costos60 = reportCostoMantencionXequipoPeriodo(con, db,(long)60,pais, id_sucursal);
		Map<String,List<String>> costos90 = reportCostoMantencionXequipoPeriodo(con, db,(long)90,pais, id_sucursal);
		Map<String,List<String>> costos180 = reportCostoMantencionXequipoPeriodo(con, db,(long)180,pais, id_sucursal);
		Map<String,List<String>> costos360 = reportCostoMantencionXequipoPeriodo(con, db,(long)360,pais, id_sucursal);
		
		Map<String,String> vta30 = reportVtaRealXequipoPeriodo(con, db,(long)30,pais);
		Map<String,String> vta60 = reportVtaRealXequipoPeriodo(con, db,(long)60,pais);
		Map<String,String> vta90 = reportVtaRealXequipoPeriodo(con, db,(long)90,pais);
		Map<String,String> vta180 = reportVtaRealXequipoPeriodo(con, db,(long)180,pais);
		Map<String,String> vta360 = reportVtaRealXequipoPeriodo(con, db,(long)360,pais);
		
		Fechas hoy = Fechas.hoy();
		Map<Long,Double> tasas = TasasCambio.mapTasasPorFecha(con, db, hoy.getFechaStrAAMMDD(), pais);	
		
		Map<Long,List<Double>> precios = Precio.maestroPListaPorSucursal(con, db, id_sucursal);
		Map<Long,Double> equivalencia = UnidadTiempo.equivalencia(con, db);
		
		for(int i=0;i<equipos.size();i++){
			Long idEquipo =Long.parseLong(equipos.get(i).get(0).trim());
					
			Double factor = equivalencia.get((long)(double)precios.get(idEquipo).get(2));
			Double idMoneda = precios.get(idEquipo).get(3);
			Double tasa = tasas.get((long) Math.round(idMoneda));
			Double precio = precios.get(idEquipo).get(1);
			Double precioArriendoDia=precio/factor*tasa;// precio de arriendo dia
			
			Long id_equipo = Long.parseLong(equipos.get(i).get(0));
			
			List<String> periodo30 = reportDiasNoOperativoXequipoPeriodo(con, db, id_equipo, (long)30);
			List<String> periodo60 = reportDiasNoOperativoXequipoPeriodo(con, db, id_equipo, (long)60);
			List<String> periodo90 = reportDiasNoOperativoXequipoPeriodo(con, db, id_equipo, (long)90);
			List<String> periodo180 = reportDiasNoOperativoXequipoPeriodo(con, db, id_equipo, (long)180);
			List<String> periodo360 = reportDiasNoOperativoXequipoPeriodo(con, db, id_equipo, (long)360);
			
			/*****************************************************/
			List<String> aux = new ArrayList<String>();
			aux.add(equipos.get(i).get(0));
			aux.add(equipos.get(i).get(1));
			aux.add(equipos.get(i).get(2));
			lista.add(aux);
			
			/*****************************************************/
			List<String> aux1 = new ArrayList<String>();
			aux1.add("");
			aux1.add("");
			aux1.add("    ");
			aux1.add("Período(días)");
			aux1.add(periodo30.get(0));
			aux1.add(periodo60.get(0));
			aux1.add(periodo90.get(0));
			aux1.add(periodo180.get(0));
			aux1.add(periodo360.get(0));
			aux1.add("    ");
			aux1.add("Período(días)");
			aux1.add(periodo30.get(0));
			aux1.add(periodo60.get(0));
			aux1.add(periodo90.get(0));
			aux1.add(periodo180.get(0));
			aux1.add(periodo360.get(0));
			aux1.add("    ");
			aux1.add("Período(días)");
			aux1.add(periodo30.get(0));
			aux1.add(periodo60.get(0));
			aux1.add(periodo90.get(0));
			aux1.add(periodo180.get(0));
			aux1.add(periodo360.get(0));
			aux1.add("    ");
			aux1.add("Período(días)");
			aux1.add(periodo30.get(0));
			aux1.add(periodo60.get(0));
			aux1.add(periodo90.get(0));
			aux1.add(periodo180.get(0));
			aux1.add(periodo360.get(0));
			aux1.add("    ");
			lista.add(aux1);
			
			/***********************************************************/
			List<String> aux2 = new ArrayList<String>();
			aux2.add(equipos.get(i).get(0));
			aux2.add("");
			aux2.add("    ");
			aux2.add("DíasFalla");
			aux2.add(periodo30.get(1));
			aux2.add(periodo60.get(1));
			aux2.add(periodo90.get(1));
			aux2.add(periodo180.get(1));
			aux2.add(periodo360.get(1));
			aux2.add("    ");
			aux2.add("PotArrOper");
			
			Long x = (long)0;
			Long y = (long)0;
			Double p = precioArriendoDia; //precio arriendo lista
			
			String auxNum = periodo30.get(0).trim();
	   		if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
			try{x = myformatint.parse(auxNum).longValue();}catch(Exception e){} //periodo
			auxNum = periodo30.get(1).trim();
	   		if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
			try{y = myformatint.parse(auxNum).longValue();}catch(Exception e){}//dias no operativo
			aux2.add(myformatint.format((x-y)*p));
			
			auxNum = periodo60.get(0).trim();
	   		if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
			try{x = myformatint.parse(auxNum).longValue();}catch(Exception e){} //periodo
			auxNum = periodo60.get(1).trim();
	   		if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
			try{y = myformatint.parse(auxNum).longValue();}catch(Exception e){}//dias no operativo
			aux2.add(myformatint.format((x-y)*p));
			
			auxNum = periodo90.get(0).trim();
	   		if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
			try{x = myformatint.parse(auxNum).longValue();}catch(Exception e){} //periodo
			auxNum = periodo90.get(1).trim();
	   		if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
			try{y = myformatint.parse(auxNum).longValue();}catch(Exception e){}//dias no operativo
			aux2.add(myformatint.format((x-y)*p));
			
			auxNum = periodo180.get(0).trim();
	   		if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
			try{x = myformatint.parse(auxNum).longValue();}catch(Exception e){} //periodo
			auxNum = periodo180.get(1).trim();
	   		if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
			try{y = myformatint.parse(auxNum).longValue();}catch(Exception e){}//dias no operativo
			aux2.add(myformatint.format((x-y)*p));
			
			auxNum = periodo360.get(0).trim();
	   		if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
			try{x = myformatint.parse(auxNum).longValue();}catch(Exception e){} //periodo
			auxNum = periodo360.get(1).trim();
	   		if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
			try{y = myformatint.parse(auxNum).longValue();}catch(Exception e){}//dias no operativo
			aux2.add(myformatint.format((x-y)*p));
			
			aux2.add("    ");
			aux2.add("PotArrMes");
			
			auxNum = periodo30.get(0).trim();
	   		if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
			try{x = myformatint.parse(auxNum).longValue();}catch(Exception e){} //periodo
			auxNum = periodo30.get(1).trim();
	   		if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
			try{y = myformatint.parse(auxNum).longValue();}catch(Exception e){}//dias no operativo
			aux2.add(myformatint.format(x*p));
			
			auxNum = periodo60.get(0).trim();
	   		if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
			try{x = myformatint.parse(auxNum).longValue();}catch(Exception e){} //periodo
			auxNum = periodo60.get(1).trim();
	   		if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
			try{y = myformatint.parse(auxNum).longValue();}catch(Exception e){}//dias no operativo
			aux2.add(myformatint.format(x*p));
			
			auxNum = periodo90.get(0).trim();
	   		if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
			try{x = myformatint.parse(auxNum).longValue();}catch(Exception e){} //periodo
			auxNum = periodo90.get(1).trim();
	   		if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
			try{y = myformatint.parse(auxNum).longValue();}catch(Exception e){}//dias no operativo
			aux2.add(myformatint.format(x*p));
			
			auxNum = periodo180.get(0).trim();
	   		if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
			try{x = myformatint.parse(auxNum).longValue();}catch(Exception e){} //periodo
			auxNum = periodo180.get(1).trim();
	   		if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
			try{y = myformatint.parse(auxNum).longValue();}catch(Exception e){}//dias no operativo
			aux2.add(myformatint.format(x*p));
			
			auxNum = periodo360.get(0).trim();
	   		if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
			try{x = myformatint.parse(auxNum).longValue();}catch(Exception e){} //periodo
			auxNum = periodo360.get(1).trim();
	   		if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
			try{y = myformatint.parse(auxNum).longValue();}catch(Exception e){}//dias no operativo
			aux2.add(myformatint.format(x*p));
			
			aux2.add("    ");
			aux2.add("VtaReal");
			String vta = vta30.get(equipos.get(i).get(0).trim());if(vta==null) vta="0";
			aux2.add(vta);
			vta = vta60.get(equipos.get(i).get(0).trim());if(vta==null) vta="0";
			aux2.add(vta);
			vta = vta90.get(equipos.get(i).get(0).trim());if(vta==null) vta="0";
			aux2.add(vta);
			vta = vta180.get(equipos.get(i).get(0).trim());if(vta==null) vta="0";
			aux2.add(vta);
			vta = vta360.get(equipos.get(i).get(0).trim());if(vta==null) vta="0";
			aux2.add(vta);
			
			
			aux2.add("    ");
			lista.add(aux2);			
			
			/***********************************************************/
			List<String> aux3 = new ArrayList<String>();
			aux3.add(equipos.get(i).get(0));
			aux3.add("");
			aux3.add("    ");
			aux3.add("(Falla/Per)");
			aux3.add(periodo30.get(2));
			aux3.add(periodo60.get(2));
			aux3.add(periodo90.get(2));
			aux3.add(periodo180.get(2));
			aux3.add(periodo360.get(2));
			aux3.add("    ");
			aux3.add("GastosMant");
			try{aux3.add(costos30.get(equipos.get(i).get(0)).get(5));}catch(Exception e){aux3.add("0");}
			try{aux3.add(costos60.get(equipos.get(i).get(0)).get(5));}catch(Exception e){aux3.add("0");}
			try{aux3.add(costos90.get(equipos.get(i).get(0)).get(5));}catch(Exception e){aux3.add("0");}
			try{aux3.add(costos180.get(equipos.get(i).get(0)).get(5));}catch(Exception e){aux3.add("0");}
			try{aux3.add(costos360.get(equipos.get(i).get(0)).get(5));}catch(Exception e){aux3.add("0");}
			aux3.add("    ");
			aux3.add("GastosMant");
			try{aux3.add(costos30.get(equipos.get(i).get(0)).get(5));}catch(Exception e){aux3.add("0");}
			try{aux3.add(costos60.get(equipos.get(i).get(0)).get(5));}catch(Exception e){aux3.add("0");}
			try{aux3.add(costos90.get(equipos.get(i).get(0)).get(5));}catch(Exception e){aux3.add("0");}
			try{aux3.add(costos180.get(equipos.get(i).get(0)).get(5));}catch(Exception e){aux3.add("0");}
			try{aux3.add(costos360.get(equipos.get(i).get(0)).get(5));}catch(Exception e){aux3.add("0");}
			aux3.add("    ");
			aux3.add("GastosMant");
			try{aux3.add(costos30.get(equipos.get(i).get(0)).get(5));}catch(Exception e){aux3.add("0");}
			try{aux3.add(costos60.get(equipos.get(i).get(0)).get(5));}catch(Exception e){aux3.add("0");}
			try{aux3.add(costos90.get(equipos.get(i).get(0)).get(5));}catch(Exception e){aux3.add("0");}
			try{aux3.add(costos180.get(equipos.get(i).get(0)).get(5));}catch(Exception e){aux3.add("0");}
			try{aux3.add(costos360.get(equipos.get(i).get(0)).get(5));}catch(Exception e){aux3.add("0");}
			aux3.add("    ");
			lista.add(aux3);
			
			/***********************************************************/
			List<String> aux4 = new ArrayList<String>();
			aux4.add(equipos.get(i).get(0));
			aux4.add("");
			aux4.add("    ");
			aux4.add("");
			aux4.add("");
			aux4.add("");
			aux4.add("");
			aux4.add("");
			aux4.add("");
			aux4.add("    ");
			aux4.add("(Gtos/DiaArr)");
			
			Long g = (long) 0;
			
			try{x = myformatint.parse(periodo30.get(0).trim()).longValue();}catch(Exception e){x = (long)0;} //periodo
			try{y = myformatint.parse(periodo30.get(1).trim()).longValue();}catch(Exception e){y = (long)0;}//dias no operativo
			try{g = myformatint.parse(costos30.get(equipos.get(i).get(0)).get(5).trim()).longValue();}catch(Exception e){g = (long)0;}//dias no operativo
			if((x-y)*p==0) aux4.add("0.00");else aux4.add(myformatdouble.format(g/((x-y)*p)));
			
			try{x = myformatint.parse(periodo60.get(0).trim()).longValue();}catch(Exception e){x = (long)0;} //periodo
			try{y = myformatint.parse(periodo60.get(1).trim()).longValue();}catch(Exception e){y = (long)0;}//dias no operativo
			try{g = myformatint.parse(costos60.get(equipos.get(i).get(0)).get(5).trim()).longValue();}catch(Exception e){g = (long)0;}//dias no operativo
			if((x-y)*p==0) aux4.add("0.00");else aux4.add(myformatdouble.format(g/((x-y)*p)));
			
			try{x = myformatint.parse(periodo90.get(0).trim()).longValue();}catch(Exception e){x = (long)0;} //periodo
			try{y = myformatint.parse(periodo90.get(1).trim()).longValue();}catch(Exception e){y = (long)0;}//dias no operativo
			try{g = myformatint.parse(costos90.get(equipos.get(i).get(0)).get(5).trim()).longValue();}catch(Exception e){g = (long)0;}//dias no operativo
			if((x-y)*p==0) aux4.add("0.00");else aux4.add(myformatdouble.format(g/((x-y)*p)));
			
			try{x = myformatint.parse(periodo180.get(0).trim()).longValue();}catch(Exception e){x = (long)0;} //periodo
			try{y = myformatint.parse(periodo180.get(1).trim()).longValue();}catch(Exception e){y = (long)0;}//dias no operativo
			try{g = myformatint.parse(costos180.get(equipos.get(i).get(0)).get(5).trim()).longValue();}catch(Exception e){g = (long)0;}//dias no operativo
			if((x-y)*p==0) aux4.add("0.00");else aux4.add(myformatdouble.format(g/((x-y)*p)));
			
			try{x = myformatint.parse(periodo360.get(0).trim()).longValue();}catch(Exception e){x = (long)0;} //periodo
			try{y = myformatint.parse(periodo360.get(1).trim()).longValue();}catch(Exception e){y = (long)0;}//dias no operativo
			try{g = myformatint.parse(costos360.get(equipos.get(i).get(0)).get(5).trim()).longValue();}catch(Exception e){g = (long)0;}//dias no operativo
			if((x-y)*p==0) aux4.add("0.00");else aux4.add(myformatdouble.format(g/((x-y)*p)));
			
			
			aux4.add("    ");
			aux4.add("(Gtos/PotArr)");
			try{aux4.add(costos30.get(equipos.get(i).get(0)).get(6));}catch(Exception e){aux4.add("0.00");}
			try{aux4.add(costos60.get(equipos.get(i).get(0)).get(6));}catch(Exception e){aux4.add("0.00");}
			try{aux4.add(costos90.get(equipos.get(i).get(0)).get(6));}catch(Exception e){aux4.add("0.00");}
			try{aux4.add(costos180.get(equipos.get(i).get(0)).get(6));}catch(Exception e){aux4.add("0.00");}
			try{aux4.add(costos360.get(equipos.get(i).get(0)).get(6));}catch(Exception e){aux4.add("0.00");}
			aux4.add("    ");
			
			aux4.add("(Gtos/VtaReal)");
			String gtos = ""; try{gtos = costos30.get(equipos.get(i).get(0)).get(5);}catch(Exception e) {}
			String vta1 = vta30.get(equipos.get(i).get(0).trim());if(vta1==null) vta1="0";
			Double gtos2 = (double) 0;
			auxNum = gtos.trim();
			if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
			try {gtos2=myformatdouble.parse(auxNum).doubleValue();}catch(Exception e) {}
			
			Double vta2 = (double) 0; 
			auxNum = vta1.trim();
			if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
			try {vta2=myformatdouble.parse(auxNum).doubleValue();}catch(Exception e) {}
			String result = myformatdouble2.format((double)0);if(vta2!=0) result = myformatdouble2.format(gtos2/vta2);
			aux4.add(result);
			
			gtos = ""; try{gtos = costos60.get(equipos.get(i).get(0)).get(5);}catch(Exception e) {}
			vta1 = vta60.get(equipos.get(i).get(0).trim());if(vta1==null) vta1="0";
			gtos2 = (double) 0; 
			auxNum = gtos.trim();
			if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
			try {gtos2=myformatdouble.parse(auxNum).doubleValue();}catch(Exception e) {}
			
			vta2 = (double) 0; 
			auxNum = vta1.trim();
			if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
			try {vta2=myformatdouble.parse(auxNum).doubleValue();}catch(Exception e) {}
			result = myformatdouble2.format((double)0);if(vta2!=0) result = myformatdouble2.format(gtos2/vta2);
			aux4.add(result);
			
			gtos = ""; try{gtos = costos90.get(equipos.get(i).get(0)).get(5);}catch(Exception e) {}
			vta1 = vta90.get(equipos.get(i).get(0).trim());if(vta1==null) vta1="0";
			gtos2 = (double) 0; 
			auxNum = gtos.trim();
			if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
			try {gtos2=myformatdouble.parse(auxNum).doubleValue();}catch(Exception e) {}
			vta2 = (double) 0; 
			auxNum = vta1.trim();
			if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
			try {vta2=myformatdouble.parse(auxNum).doubleValue();}catch(Exception e) {}
			result = myformatdouble2.format((double)0);if(vta2!=0) result = myformatdouble2.format(gtos2/vta2);
			aux4.add(result);
			
			gtos = ""; try{gtos = costos180.get(equipos.get(i).get(0)).get(5);}catch(Exception e) {}
			vta1 = vta180.get(equipos.get(i).get(0).trim());if(vta1==null) vta1="0";
			gtos2 = (double) 0; 
			auxNum = gtos.trim();
			if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
			try {gtos2=myformatdouble.parse(auxNum).doubleValue();}catch(Exception e) {}
			vta2 = (double) 0; 
			auxNum = vta1.trim();
			if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
			try {vta2=myformatdouble.parse(auxNum).doubleValue();}catch(Exception e) {}
			result = myformatdouble2.format((double)0);if(vta2!=0) result = myformatdouble2.format(gtos2/vta2);
			aux4.add(result);
			
			gtos = ""; try{gtos = costos360.get(equipos.get(i).get(0)).get(5);}catch(Exception e) {}
			vta1 = vta360.get(equipos.get(i).get(0).trim());if(vta1==null) vta1="0";
			gtos2 = (double) 0; 
			auxNum = gtos.trim();
			if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
			try {gtos2=myformatdouble.parse(auxNum).doubleValue();}catch(Exception e) {}
			vta2 = (double) 0; 
			auxNum = vta1.trim();
			if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
			try {vta2=myformatdouble.parse(auxNum).doubleValue();}catch(Exception e) {}
			result = myformatdouble2.format((double)0);if(vta2!=0) result = myformatdouble2.format(gtos2/vta2);
			aux4.add(result);
			
			aux4.add("    ");
			lista.add(aux4);
		}
		return (lista);
	}
	
	public static Map<String,List<String>> reportCostoMantencionXequipoPeriodo(Connection con, String db, Long periodo, String pais, Long id_sucursal) {
		Map<String,List<String>> listado = new HashMap<String,List<String>>();
		Calendar fechaHoy =  Calendar.getInstance();
		Calendar fechaInicio = Calendar.getInstance();
		fechaInicio.add(Calendar.DAY_OF_MONTH, (int) (long) periodo * -1);
		java.sql.Date sqlDateHoy = new java.sql.Date(fechaHoy.getTime().getTime());
		java.sql.Date sqlDateInicio = new java.sql.Date(fechaInicio.getTime().getTime());
		Map<Long,List<Double>> precios = Precio.maestroPListaPorSucursal(con, db, id_sucursal);
		Map<Long,Double> equivalencia = UnidadTiempo.equivalencia(con, db);
		Fechas hoy = Fechas.hoy();
		Map<Long,Double> tasas = TasasCambio.mapTasasPorFecha(con, db, hoy.getFechaStrAAMMDD(), pais);
		String uf =  "1";
		String usd = "1";
		String eur = "1";
		if(pais.equals("CHILE")) {
			 uf = tasas.get((long)4).toString();
			 usd = tasas.get((long)2).toString();
			 eur = tasas.get((long)3).toString();
		}
		try {
			PreparedStatement smt5 = con
					.prepareStatement(" select " +
							" id_equipo, " +
							" codigo, " +
							" nombre, " +
							" sum(if(id_moneda=1,costoNeto*1," +
							" if(id_moneda=2,costoNeto*?," +
							" if(id_moneda=3,costoNeto*?," +
							" if(id_moneda=4,costoNeto*?,0))))) as tot, " +
							" fechaInicio " +
							" from `"+db+"`.hojaVida  " +
							" left join `"+db+"`.equipo on equipo.id=id_equipo " +
							" where fechaInicio>=? and fechaInicio<=? " +
							" group by id_equipo " +
							" order by nombre;");
			smt5.setString(1, usd.trim());
			smt5.setString(2, eur.trim());
			smt5.setString(3, uf.trim());
			smt5.setDate(4,sqlDateInicio);
			smt5.setDate(5,sqlDateHoy);
			ResultSet rs5 = smt5.executeQuery();
			while (rs5.next()) {
				Long idEquipo = rs5.getLong(1);
				Double factor = equivalencia.get((long)(double)precios.get(idEquipo).get(2));				
				Double tasa = tasas.get((long)(double)precios.get(idEquipo).get(3));
				Double precioArriendoDia=precios.get(idEquipo).get(1)*tasa/factor;
				List<String> aux = new ArrayList<String>();
				aux.add(rs5.getString(1)); // 0 idequipo
				aux.add(rs5.getString(2)); // 1 codigo
				aux.add(rs5.getString(3)); // 2 nombre
				aux.add(myformatint.format(periodo)); // 3 periodo
				aux.add(myformatint.format(periodo*precioArriendoDia)); // 4 potencial de arriendo
				aux.add(myformatint.format(rs5.getDouble(4))); // 5 gastos
				Double kpi = (double)0;
				if((periodo*precioArriendoDia)!=0) kpi = rs5.getDouble(4)/(periodo*precioArriendoDia);
				aux.add(myformatdouble.format(kpi)); // 6 gastos/pot
				listado.put(rs5.getString(1), aux);
			}
			rs5.close();
			smt5.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (listado); 
	}
	
	public static Map<String,String> reportVtaRealXequipoPeriodo(Connection con, String db, Long periodo, String pais) {
		Map<String,String> listado = new HashMap<String,String>();
		Calendar fechaHoy =  Calendar.getInstance();
		Calendar fechaInicio = Calendar.getInstance();
		fechaInicio.add(Calendar.DAY_OF_MONTH, (int) (long) periodo * -1);
		java.sql.Date sqlDateHoy = new java.sql.Date(fechaHoy.getTime().getTime());
		java.sql.Date sqlDateInicio = new java.sql.Date(fechaInicio.getTime().getTime());	
		Fechas hoy = Fechas.hoy();
		Map<Long,Double> tasas = TasasCambio.mapTasasPorFecha(con, db, hoy.getFechaStrAAMMDD(), pais);
		String uf =  "1";
		String usd = "1";
		String eur = "1";
		if(pais.equals("CHILE")) {
			 uf = tasas.get((long)4).toString();
			 usd = tasas.get((long)2).toString();
			 eur = tasas.get((long)3).toString();
		}
		try {
			PreparedStatement smt5 = con
					.prepareStatement("(select " + 
							" M.id_bodegaEmpresa," + 
							" B.nombre as obra," + 
							" M.id_equipo," + 
							" E.codigo," + 
							" E.nombre as equipo," + 
							" (if(L.id_moneda=1,1,if(L.id_moneda=2,?,if(L.id_moneda=3,?,if(id_moneda=4,?,0)))))*" + 
							" (TIMESTAMPDIFF(DAY, ?, ?)+1)*(L.precioArriendo/U.factor)*(sum(if(M.id_tipoMovimiento=1,1,-1)*M.cantidad)) as totalPesos" + 
							" from `"+db+"`.movimiento as M" + 
							" left join `"+db+"`.guia as G on G.id=M.id_guia" + 
							" left join `"+db+"`.otDespachado as D on D.id = M.id_otDespachado" + 
							" left join `"+db+"`.ot as O on O.id = D.id_ot" + 
							" left join `"+db+"`.listaPrecio as L on L.id_bodegaEmpresa = M.id_bodegaEmpresa and L.id_equipo=M.id_equipo and L.id_cotizacion=ifnull(O.id_cotizacion,0)" + 
							" left join `"+db+"`.unidadTiempo as U on U.id = L.id_unidadTiempo" + 
							" left join `"+db+"`.bodegaEmpresa as B on B.id = M.id_bodegaEmpresa" + 
							" left join `"+db+"`.equipo as E on E.id = M.id_equipo" + 
							" where " + 
							" M.id_bodegaEmpresa in (select id from `"+db+"`.bodegaEmpresa where esInterna=2) and" + 
							" M.id_guia in (select id from `"+db+"`.guia where fecha<?) and" + 
							" M.id_equipo in (select distinct id_equipo from `"+db+"`.planMantencion)" + 
							" group by M.id_equipo,M.id_bodegaEmpresa,L.id_moneda" + 
							" having sum(if(M.id_tipoMovimiento=1,1,-1)*M.cantidad)>0)" + 
							" union" + 
							" (select " + 
							" M.id_bodegaEmpresa," + 
							" B.nombre as obra," + 
							" M.id_equipo," + 
							" E.codigo," + 
							" E.nombre as equipo," + 
							" (if(L.id_moneda=1,1,if(L.id_moneda=2,?,if(L.id_moneda=3,?,if(id_moneda=4,?,0)))))*" + 
							" (TIMESTAMPDIFF(DAY, G.fecha, ?)+ if(M.id_tipoMovimiento=1,1,0))*(L.precioArriendo/U.factor)*(if(M.id_tipoMovimiento=1,1,-1)*M.cantidad) as totalPesos" + 
							" from `"+db+"`.movimiento as M" + 
							" left join `"+db+"`.guia as G on G.id=M.id_guia" + 
							" left join `"+db+"`.otDespachado as D on D.id = M.id_otDespachado" + 
							" left join `"+db+"`.ot as O on O.id = D.id_ot" + 
							" left join `"+db+"`.listaPrecio as L on L.id_bodegaEmpresa = M.id_bodegaEmpresa and L.id_equipo=M.id_equipo and L.id_cotizacion=ifnull(O.id_cotizacion,0)" + 
							" left join `"+db+"`.unidadTiempo as U on U.id = L.id_unidadTiempo" + 
							" left join `"+db+"`.bodegaEmpresa as B on B.id = M.id_bodegaEmpresa" + 
							" left join `"+db+"`.equipo as E on E.id = M.id_equipo" + 
							" where" + 
							" M.id_bodegaEmpresa in (select id from `"+db+"`.bodegaEmpresa where esInterna=2) and" + 
							" M.id_guia in (select id from `"+db+"`.guia where fecha BETWEEN ? AND ?) and" + 
							" M.id_equipo in (select distinct id_equipo from `"+db+"`.planMantencion) and" + 
							" M.cantidad<>0)" + 
							" order by id_equipo;");
			
			smt5.setString(1, usd.trim());
			smt5.setString(2, eur.trim());
			smt5.setString(3, uf.trim());
			smt5.setDate(4,sqlDateInicio);
			smt5.setDate(5,sqlDateHoy);
			smt5.setDate(6,sqlDateInicio);
			smt5.setString(7, usd.trim());
			smt5.setString(8, eur.trim());
			smt5.setString(9, uf.trim());
			smt5.setDate(10,sqlDateHoy);
			smt5.setDate(11,sqlDateInicio);
			smt5.setDate(12,sqlDateHoy);
			ResultSet rs5 = smt5.executeQuery();
			List<List<String>> todo = new ArrayList<List<String>>();
			while (rs5.next()) {
				Long idEquipo = rs5.getLong(3);
				Double totalArriendo=rs5.getDouble(6);
				List<String> aux = new ArrayList<String>();
				aux.add(idEquipo.toString()); 		// 0 idequipo
				aux.add(totalArriendo.toString()); 	// 1 total Arriendo
				todo.add(aux);
			}
			rs5.close();
			smt5.close();
			for(int i=0;i<todo.size();i++) {
				String idEquipo = todo.get(i).get(0);
				Double total = Double.parseDouble(todo.get(i).get(1).trim());
				int x=i+1;
				for(int k=x;k<todo.size();k++) {
					if(idEquipo.equals(todo.get(k).get(0))) {
						total = total + Double.parseDouble(todo.get(k).get(1).trim());
						i++;
					}
				}
				String vtaReal = myformatint.format(total);
				listado.put(idEquipo, vtaReal);
			}
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (listado); 
	}
	
	public static List<String> reportDiasNoOperativoXequipoPeriodo(Connection con, String db, Long id_equipo, Long periodo) {
		List<String> lista = new ArrayList<String>();
		List<List<Calendar>> listaFechas = new ArrayList<List<Calendar>>();
		try {
			PreparedStatement smt5 = con
					.prepareStatement(" select " +
							" ifnull(fechaInicio,now()),ifnull(fechaTermino,now()) " +
							" from `"+db+"`.hojaVida where id_equipo=?;");
			smt5.setLong(1, id_equipo);
			ResultSet rs5 = smt5.executeQuery();
			while (rs5.next()) {
				List<Calendar> aux = new ArrayList<Calendar>();
				Calendar fechaIni =  Calendar.getInstance();
				fechaIni.setTime( rs5.getDate(1));				
				aux.add(fechaIni);
				Calendar fechaFin =  Calendar.getInstance();
				fechaFin.setTime( rs5.getDate(2));				
				aux.add(fechaFin);
				listaFechas.add(aux);
			}
			rs5.close();
			smt5.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}		
		Calendar fechaHoy =  Calendar.getInstance();
		Long hoy = Math.round( (double) fechaHoy.getTimeInMillis()/(24*60*60*1000));
		Long dias = (long)0;
		for(Long i=hoy-periodo;i<hoy;i++){
			Long aux = (long)0;
			for(int j=0;j<listaFechas.size();j++){
				Long ini = Math.round( (double) listaFechas.get(j).get(0).getTimeInMillis()/(24*60*60*1000));
				Long fin = Math.round( (double) listaFechas.get(j).get(1).getTimeInMillis()/(24*60*60*1000));
				if(i>=ini&&i<=fin){
					aux=(long)1;
				}
			}
			dias=dias+aux;
		}
		lista.add(myformatint.format(periodo));    			//0 periodo
		lista.add(myformatint.format(dias));				//1 dias no operativo
		Double aux = (double) dias/ (double) periodo;		
		lista.add(myformatdouble.format(aux));				//2 factor (p/dno)
		return (lista); 
	}
	
	public static List<String> reportGrafico(Connection con, String db, Long id_grupo, Long periodo, String pais, Long id_sucursal) {
		String cond ="";
		if(id_grupo!=0) cond = " and id_grupo = '"+id_grupo+"'";
		List<String> series = new ArrayList<String>();
		List<String> serieX = new ArrayList<String>();
		List<List<String>> equipos = new ArrayList<List<String>>();
		try {
			PreparedStatement smt22 = con
					.prepareStatement("select id_equipo from `"+db+"`.baja where esModificable=0");
			String listaCond ="0,";
			ResultSet rs22 = smt22.executeQuery();
			while (rs22.next()) {
				listaCond = listaCond + rs22.getString(1) + ",";
			}
			rs22.close();smt22.close();
			
			if(listaCond.length()>1) {
				listaCond = listaCond.substring(0,listaCond.length()-1);
			}
			
			
			PreparedStatement smt1 = con
					.prepareStatement(" select distinct id_equipo, codigo,nombre " +
							" from `"+db+"`.hojaVida  " +
							" left join `"+db+"`.equipo on equipo.id=id_equipo " +
							" where id_equipo not in " +
							" ("+listaCond+") " + cond +
							" order by nombre;");
			
			ResultSet rs1 = smt1.executeQuery();
			while (rs1.next()) {
				List<String> aux = new ArrayList<String>();
				aux.add(rs1.getString(1));
				aux.add(rs1.getString(2));
				aux.add(rs1.getString(3));
				equipos.add(aux);
				String auxNombre = rs1.getString(3);
				int auxLargo = auxNombre.length();
				if(auxLargo>15) auxLargo=15;
				serieX.add(rs1.getString(2)+"-"+auxNombre.substring(0,auxLargo));
			}
			rs1.close();
			smt1.close();
			
		} catch (SQLException e) {
				e.printStackTrace();
		}		
		String ejeX="";
		for(int i=0;i<serieX.size();i++){
			ejeX = ejeX+"'"+ serieX.get(i).toString() + "'";
			if(i<serieX.size()-1) ejeX=ejeX+",";
		}		
		Map<String,List<String>> costos360 = reportCostoMantencionXequipoPeriodo(con, db, periodo, pais, id_sucursal);
		List<String> serieYmaxArriendo = new ArrayList<String>();
		List<String> serieYpotArriendo = new ArrayList<String>();
		List<String> serieYgastosMant = new ArrayList<String>();
		List<String> serieYvtaReal = new ArrayList<String>();
		
		Fechas hoy = Fechas.hoy();
		Map<Long,Double> tasas = TasasCambio.mapTasasPorFecha(con, db, hoy.getFechaStrAAMMDD(), pais);
		
		Map<String,String> vta = reportVtaRealXequipoPeriodo(con, db,periodo,pais);
		
		Map<Long,List<Double>> precios = Precio.maestroPListaPorSucursal(con, db, id_sucursal);
		Map<Long,Double> equivalencia = UnidadTiempo.equivalencia(con, db);	
		
		for(int i=0;i<equipos.size();i++){
			Long idEquipo=Long.parseLong(equipos.get(i).get(0).trim());
				
			Double factor = equivalencia.get((long)(double)precios.get(idEquipo).get(2));
			Double idMoneda = precios.get(idEquipo).get(3);
			Double tasa = tasas.get((long) Math.round(idMoneda));
			Double precio = precios.get(idEquipo).get(1);
			Double precioArriendoDia=precio/factor*tasa;// precio de arriendo dia
			
			Long x = (long)0;
			Long y = (long)0;
			
			Long id_equipo = Long.parseLong(equipos.get(i).get(0));
			List<String> periodo360 = reportDiasNoOperativoXequipoPeriodo(con, db, id_equipo, periodo);
			
			Long gastosMant = (long)0;
			try{
				String auxNum = costos360.get(equipos.get(i).get(0)).get(5).trim();
		   		if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
				gastosMant = myformatint.parse(auxNum).longValue();
			}catch(Exception e){gastosMant = (long)0;}
			
			String auxNum = periodo360.get(0).trim();
	   		if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
			try{x = myformatint.parse(auxNum).longValue();}catch(Exception e){} //periodo
			auxNum = periodo360.get(1).trim();
	   		if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
			try{y = myformatint.parse(auxNum).longValue();}catch(Exception e){}//dias no operativo
			
			Long potArriendo = (long) ((x-y)*precioArriendoDia);
			Long maxArriendo = (long) (x*precioArriendoDia);
			
			serieYmaxArriendo.add(maxArriendo.toString());
			serieYpotArriendo.add(potArriendo.toString());
			serieYgastosMant.add(gastosMant.toString());
			
			String vtaAux = vta.get(equipos.get(i).get(0));
			if(vtaAux==null) vtaAux="0";
			serieYvtaReal.add(vtaAux.replaceAll(",", ""));
			
		}
		
		String ejeY = "{name: 'POTENCIAL VENTA ESPERADA (todos los dias)', data: [";
		if(serieYmaxArriendo.size()==0) {
			ejeY=ejeY+"]},";
		}
		for(int i=0;i<serieYmaxArriendo.size();i++){
			ejeY = ejeY + serieYmaxArriendo.get(i);
			if(i==serieYmaxArriendo.size()-1) {
				ejeY=ejeY+"]},";
			}else{
				ejeY=ejeY+",";
			}
		}
		ejeY = ejeY + "{name: 'POTENCIAL VENTA MAXIMA (solo dias Operativos)', data: [";
		if(serieYpotArriendo.size()==0) {
			ejeY=ejeY+"]},";
		}
		for(int i=0;i<serieYpotArriendo.size();i++){
			ejeY = ejeY + serieYpotArriendo.get(i);
			if(i==serieYpotArriendo.size()-1){
				ejeY=ejeY+"]},";
			}else{
				ejeY=ejeY+",";
			}
		}
		ejeY = ejeY + "{name: 'GASTOS de MANTENCION', data: [";
		if(serieYgastosMant.size()==0) {
			ejeY=ejeY+"]},";
		}
		for(int i=0;i<serieYgastosMant.size();i++){
			ejeY = ejeY + serieYgastosMant.get(i);
			if(i==serieYgastosMant.size()-1){
				ejeY=ejeY+"]},";
			}else{
				ejeY=ejeY+",";
			}
		}
		ejeY = ejeY + "{name: 'VENTA REAL', data: [";
		if(serieYvtaReal.size()==0) {
			ejeY=ejeY+"]}";
		}
		for(int i=0;i<serieYvtaReal.size();i++){
			ejeY = ejeY + serieYvtaReal.get(i);
			if(i==serieYvtaReal.size()-1){
				ejeY=ejeY+"]}";
			}else{
				ejeY=ejeY+",";
			}
		}
		
	
		series.add(ejeX);
		series.add(ejeY);
		
	return (series);
	  
	}
	
	public static List<List<String>> listaGruposConHojaVida(Connection con, String db) {
		List<List<String>> lista = new ArrayList<List<String>>();
		try {
			PreparedStatement smt1 = con
					.prepareStatement(" select distinct " +
							" equipo.id_grupo,grupo.nombre " +
							" from `"+db+"`.hojaVida " +
							" left join `"+db+"`.equipo on equipo.id=id_equipo " +
							" left join `"+db+"`.grupo on grupo.id = id_grupo " +
							" order by nombre;");
			ResultSet rs1 = smt1.executeQuery();
			while (rs1.next()) {
				List<String> aux = new ArrayList<String>();
				aux.add(rs1.getString(1));
				aux.add(rs1.getString(2));
				lista.add(aux);
			}
			rs1.close();
			smt1.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}		
		return (lista);
	  
	}
	
	public static File hojaVidaReportKpisExcel(String db, Map<String,String> mapDiccionario, List<List<String>> listado, Map<String,String> mapUbicacion) {
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
            Font font3 = libro.createFont();
            font3.setBoldweight(Font.BOLDWEIGHT_BOLD);
            font3.setColor((short)0);
            encabezado.setFont(font3);
            
            CellStyle detalle = libro.createCellStyle();
            detalle.setBorderBottom(CellStyle.BORDER_THIN);
            detalle.setBorderTop(CellStyle.BORDER_THIN);
            detalle.setBorderRight(CellStyle.BORDER_THIN);
            detalle.setBorderLeft(CellStyle.BORDER_THIN);
            
            CellStyle pie = libro.createCellStyle();
            pie.setBorderBottom(CellStyle.BORDER_THIN);
            pie.setBorderTop(CellStyle.BORDER_THIN);
            pie.setBorderRight(CellStyle.BORDER_THIN);
            pie.setBorderLeft(CellStyle.BORDER_THIN);
            pie.setFillPattern(CellStyle.SOLID_FOREGROUND);
            pie.setFillForegroundColor((short)19);
            pie.setAlignment(CellStyle.ALIGN_RIGHT);
            
            
            
            CreationHelper creationHelper = libro.getCreationHelper();
            CellStyle hora = libro.createCellStyle();
            hora.setDataFormat(creationHelper.createDataFormat().getFormat("hh:mm"));
            hora.setBorderBottom(CellStyle.BORDER_THIN);
            hora.setBorderTop(CellStyle.BORDER_THIN);
            hora.setBorderRight(CellStyle.BORDER_THIN);
            hora.setBorderLeft(CellStyle.BORDER_THIN);
            
            CellStyle fecha = libro.createCellStyle();
            fecha.setDataFormat(creationHelper.createDataFormat().getFormat("dd/MM/yyyy"));
            fecha.setBorderBottom(CellStyle.BORDER_THIN);
            fecha.setBorderTop(CellStyle.BORDER_THIN);
            fecha.setBorderRight(CellStyle.BORDER_THIN);
            fecha.setBorderLeft(CellStyle.BORDER_THIN);


            
            
            //titulos del archivo
            
            libro.setSheetName(0, "report");
            Sheet hoja1 = libro.getSheetAt(0);
            
            Row row = null;
            Cell cell = null;
            
            row = hoja1.createRow(1);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("REPORTE KPIs MANTENCIONES");
			
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
			
			
			for(int i=0; i<40; i++) {
				hoja1.setColumnWidth(i, 2*1200);
			}
			
			hoja1.setColumnWidth( 2, 500);
			hoja1.setColumnWidth( 9, 500);
			hoja1.setColumnWidth(16, 500);
			hoja1.setColumnWidth(23, 500);
			hoja1.setColumnWidth(30, 500);
			
			hoja1.setColumnWidth( 3, 5*1000);
			hoja1.setColumnWidth(10, 5*1000);
			hoja1.setColumnWidth(17, 5*1000);
			hoja1.setColumnWidth(24, 5*1000);
			
			
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
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("EQUIPO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("DIAS DE EQUIPO NO OPERATIVOS");
			
			posCell += 6;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			
            posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("COSTO vs VENTA MAXIMA (PLista X Días Operativos)");
			
			posCell += 6;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			
            posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("COSTO vs VENTA ESPERADA (PLista X Total Días)");
			
			posCell += 6;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			
            posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("COSTO vs VENTA REAL (Venta real período)");
			
			posCell += 6;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			
			for(List<String> lista: listado){
				posRow++;
				posCell = 0;
		        row = hoja1.createRow(posRow);
				
		        if(lista.get(1)=="") {
		        	for(int i=1; i<lista.size(); i++) {
		        		if(lista.get(i)=="    ") {
		        			posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(encabezado);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue(lista.get(i));
			        	}else {
			        		posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							try {
								Double aux = Double.parseDouble(lista.get(i).replaceAll(",", ""));
								cell.setCellType(Cell.CELL_TYPE_NUMERIC);
								cell.setCellValue(aux);
							}catch(Exception ee){
								cell.setCellType(Cell.CELL_TYPE_STRING);
								cell.setCellValue(lista.get(i));
							}
							
			        	}
		        	
		        	}
		        }else {
		        	posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(encabezado);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(lista.get(1));
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(encabezado);
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(encabezado);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(lista.get(2));
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(encabezado);
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(encabezado);
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(encabezado);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					String ubicacion = "Ubicacion actual: ";
					if(mapUbicacion.get(lista.get(0))!=null) {
						ubicacion += mapUbicacion.get(lista.get(0));
					}
					cell.setCellValue(ubicacion);
					
					for(int i=0; i<24; i++) {
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(encabezado);
					}
					
		        }
			}
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
//		        
//				cell = row.createCell(posCell);
//				Double valor = Double.parseDouble(list.get(0).replaceAll(",", ""));
//				cell.setCellStyle(detalle);
//				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
//				cell.setCellValue(valor);
//				
//				posCell++;
//				cell = row.createCell(posCell);
//				Fechas fechax = Fechas.obtenerFechaDesdeStrAAMMDD(Fechas.AAMMDD(list.get(1)));
//				cell.setCellValue(fechax.fechaUtil);
//				cell.setCellStyle(fecha);
//				
//				posCell++;
//				cell = row.createCell(posCell);
//				cell.setCellStyle(detalle);
//				cell.setCellType(Cell.CELL_TYPE_STRING);
//				cell.setCellValue(list.get(2));
//				
//				posCell++;
//				cell = row.createCell(posCell);
//				cell.setCellStyle(detalle);
//				cell.setCellType(Cell.CELL_TYPE_STRING);
//				cell.setCellValue(list.get(3));
//				
//				posCell++;
//				cell = row.createCell(posCell);
//				cell.setCellStyle(detalle);
//				cell.setCellType(Cell.CELL_TYPE_STRING);
//				cell.setCellValue(list.get(4));
//				
//				posCell++;
//				cell = row.createCell(posCell);
//				cell.setCellStyle(detalle);
//				cell.setCellType(Cell.CELL_TYPE_STRING);
//				cell.setCellValue(list.get(5));
//				
//				posCell++;
//				cell = row.createCell(posCell);
//				cell.setCellStyle(detalle);
//				cell.setCellType(Cell.CELL_TYPE_STRING);
//				cell.setCellValue(list.get(6));
//				
//				posCell++;
//				cell = row.createCell(posCell);
//				cell.setCellStyle(detalle);
//				cell.setCellType(Cell.CELL_TYPE_STRING);
//				cell.setCellValue(list.get(7));
//			
//				posCell++; 
//				cell = row.createCell(posCell);
//	            cell.setCellStyle(detalle);
//				byte[] firmaOperador = Base64.getDecoder().decode(list.get(10));
//				int indexfo = libro.addPicture(firmaOperador, Workbook.PICTURE_TYPE_PNG);
//				Drawing drawfo = hoja1.createDrawingPatriarch();
//				CreationHelper helperfo = libro.getCreationHelper();
//				ClientAnchor anchorfo = helperfo.createClientAnchor();
//				anchorfo.setCol1(posCell);
//				anchorfo.setRow1(posRow);
//				anchorfo.setCol2(posCell+11);
//				anchorfo.setRow2(posRow+1);
//				Picture imgfo = drawfo.createPicture(anchorfo, indexfo);
//				imgfo.resize(0.2);
//				
//				posCell++; 
//				cell = row.createCell(posCell);
//	            cell.setCellStyle(detalle);
//				byte[] firmaAurorizador = Base64.getDecoder().decode(list.get(11));
//				int indexfa = libro.addPicture(firmaAurorizador, Workbook.PICTURE_TYPE_PNG);
//				Drawing drawfa = hoja1.createDrawingPatriarch();
//				CreationHelper helperfa = libro.getCreationHelper();
//				ClientAnchor anchorfa = helperfa.createClientAnchor();
//				anchorfa.setCol1(posCell);
//				anchorfa.setRow1(posRow);
//				anchorfa.setCol2(posCell+11);
//				anchorfa.setRow2(posRow+1);
//				Picture imgfa = drawfa.createPicture(anchorfa, indexfa);
//				imgfa.resize(0.2);
//				
//			
//			}
			
			
			
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
		return(tmp);
	}
	
	public static List<List<String>> detalleProductoUploadMada(File file) {
		 List<List<String>> lista = new ArrayList<List<String>>();
		 DecimalFormat df = new DecimalFormat("#",symbols);
 	     df.setMaximumFractionDigits(8);
		try {
           Workbook libro = WorkbookFactory.create(file);
           Sheet hoja1 = libro.getSheetAt(0);
           Row row = null;
           Cell cell = null;
           int x = 1;
           row = hoja1.getRow(1);
           if(row!=null) {
           	cell = row.getCell(4);
           }
           while (row!=null && cell !=null ) {
           	row = hoja1.getRow(x++);
           	if(row!=null) {
           		cell = row.getCell(1);
               	if(cell!=null) {
               		boolean noEsBlanco = true;
                   	try {
                   		String dato = cell.getStringCellValue().trim();
                   		if(dato.trim().equals("")) {
                   			noEsBlanco = false;
                   		}
                   	}catch(Exception e){
                   		Double aux = cell.getNumericCellValue();
                   		if(aux.toString().trim().equals("")) {
                   			noEsBlanco = false;
                   		}
                   	}
               		if(noEsBlanco) {
               			List<String> auxList = new ArrayList<String>();
                   		for(int i=1; i<18; i++) {
                   			String dato = "";
                   			cell = row.getCell(i);
                               if(cell!=null) {
                               	try {
                               		dato = cell.getStringCellValue().trim();
                               		dato = dato.replaceAll("'", "\"");
                               	}catch(Exception e){
                               		Double aux = cell.getNumericCellValue();
                               		dato = df.format(aux);
                               	}
                               }
                               auxList.add(dato);
                           }
                   		lista.add(auxList);
               		}
               	}
               	cell = row.getCell(4);
           	}
           }
		} catch (Exception e) {
			e.printStackTrace();
       }
		
		/* estructura de la lista
			0 CODIGO_EQUIPO;
			1 GRUPO_FAMILIA;
			2 N_OC;
			3 Nombre_OC;
			4 Fecha;
			5 Obra;
			6 Rut_Proveedor;
			7 Proveedor;
			8 Descripción;
			9 Glosa;
			10 Codigo_C_C;
			11 Cuentas_de_Costo;
			12 Moneda;
			13 Sub_Total;
			14 Facturado;
			15 ID
		 */
		return(lista);
	}
	


			
}
	
	
	
