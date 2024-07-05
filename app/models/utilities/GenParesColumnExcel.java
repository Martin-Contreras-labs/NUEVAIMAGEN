package models.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenParesColumnExcel {

	public static Map<Long,String> pares() {
		Map<Long,String> map = new HashMap<Long,String>();
		
		Long cont = (long)0;
		
		// Generar pares "a" a "z"
        for (char c = 'a'; c <= 'z'; c++) {
        	map.put(cont, String.valueOf(c));
        	cont++;
        }

        // Generar pares "aa" a "az"
        for (char c1 = 'a'; c1 <= 'a'; c1++) {
            for (char c2 = 'a'; c2 <= 'z'; c2++) {
            	map.put(cont, String.valueOf(c1) + c2);
            	cont++;
            }
        }
        
        // Generar pares "ba" a "bz"
        for (char c1 = 'b'; c1 <= 'b'; c1++) {
            for (char c2 = 'a'; c2 <= 'z'; c2++) {
            	map.put(cont, String.valueOf(c1) + c2);
            	cont++;
            }
        }
		
		
		return(map);
	}
	
	
	
	
}
