package models.utilities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class DecimalFormato {
	
	
	static DecimalFormat myformatdouble0 = new DecimalFormat("#,##0");
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdouble4 = new DecimalFormat("#,##0.0000");
	static DecimalFormat myformatdouble6 = new DecimalFormat("#,##0.000000");
	
	
	public static String formato(Double numero, Long nroDecimales){
		String formateado = numero.toString();
		if((long)nroDecimales == (long)0) {
			formateado = myformatdouble0.format(numero);
		}else if ((long)nroDecimales == (long)2){
			formateado = myformatdouble2.format(numero);
		}else if ((long)nroDecimales == (long)4){
			formateado = myformatdouble4.format(numero);
		}else if ((long)nroDecimales == (long)6){
			formateado = myformatdouble6.format(numero);
		}
		return (formateado);
	}
	
	public static double redondear(double valor, int decimales) {
        BigDecimal bd = new BigDecimal(valor).setScale(decimales, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
	
	

}
