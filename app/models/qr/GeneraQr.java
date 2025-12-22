package models.qr;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.apache.poi.util.TempFile;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import models.utilities.Archivos;

public class GeneraQr {

	
	public static String generaQR(String empresa, String db, String codigo, Long id_equipo, String tipo, String web){
		String nombreArchivo = "0";
        try {
	       	String str = "{\"empresa\":\""+empresa+"\",\"base\":\""+db+"\",\"id_equipo\":\""+id_equipo.toString()+"\",\"tipo\":\""+tipo+"\"}";
	   		String strEncoded = Base64.getEncoder().encodeToString( str.getBytes( "utf-8" ) );
	    	
	   		
	   		
	   		/*****************************************/
	   		// USAR SOLO PARA QR DIRECTO A MI EQUIPO EN golf
	   		//web = "192.168.100.10:9002";
	   		/*****************************************/
	   		
	   		
	   		
	   		
	   		
	   		String url = web+"/leeUnQr/"+strEncoded;
	    		
	       int size = 200;
           QRCodeWriter qrcode = new QRCodeWriter();
           
           BitMatrix matrix = qrcode.encode(url, BarcodeFormat.QR_CODE, size, size);
           
	       int matrixWidth = matrix.getWidth();
	       BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
           image.createGraphics();
           
           Graphics2D graphics = (Graphics2D) image.getGraphics();
           graphics.setColor(Color.WHITE);
           graphics.fillRect(0, 0, matrixWidth, matrixWidth);
           graphics.setColor(Color.BLACK);
			
           for (int b = 0; b < matrixWidth; b++) {
               for (int j = 0; j < matrixWidth; j++) {
                   if (matrix.get(b, j)) {
                       graphics.fillRect(b, j, 1, 1);
                   }
               }
           }
           nombreArchivo = "QR_"+id_equipo.toString()+"_equipo.png";
           String path = db+"/"+nombreArchivo;
           
           
           File tmp = null;
try{
	tmp = TempFile.createTempFile("tmp","null");
}catch(Exception e){}
           ImageIO.write(image,"png", tmp);
           Archivos.grabarArchivo(tmp, path);
           
			
           
		} catch (WriterException | IOException e) {
			e.printStackTrace();
		}
        
        return(nombreArchivo);
	}
	
	
	
	
	
        
}
