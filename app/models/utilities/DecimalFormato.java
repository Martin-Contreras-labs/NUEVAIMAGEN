package models.utilities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class DecimalFormato {

	static DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
	static DecimalFormat myformatdouble0 = new DecimalFormat("#,##0",symbols);
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00",symbols);
	static DecimalFormat myformatdouble4 = new DecimalFormat("#,##0.0000",symbols);
	static DecimalFormat myformatdouble6 = new DecimalFormat("#,##0.000000",symbols);
	
	
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
