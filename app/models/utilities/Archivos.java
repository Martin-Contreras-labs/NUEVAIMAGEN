package models.utilities;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.poi.util.TempFile;

import controllers.HomeController;
import play.libs.Files.TemporaryFile;
import play.mvc.Http;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.ListObjectsRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.model.S3Object;



public class Archivos{
	
	public String path;
	public List<Http.MultipartFormData.FilePart<TemporaryFile>> docAdjunto;
	public List<Http.MultipartFormData.FilePart<TemporaryFile>> fotosAdjunto;
	public Long id_guia;
	public Long id_ventaServicio;
	public String iniCarpeta;
	
	public Http.MultipartFormData.FilePart<TemporaryFile> file;
	public Long id_bodegaOrigen;

	public Archivos(String path, List<Http.MultipartFormData.FilePart<TemporaryFile>> docAdjunto, 
			List<Http.MultipartFormData.FilePart<TemporaryFile>> fotosAdjunto, Long id_guia, Long id_ventaServicio, String iniCarpeta, 
			Http.MultipartFormData.FilePart<TemporaryFile> file, Long id_bodegaOrigen) {
		super();
		this.path = path;
		this.docAdjunto = docAdjunto;
		this.fotosAdjunto = fotosAdjunto;
		this.id_guia = id_guia;
		this.id_ventaServicio = id_ventaServicio;
		this.iniCarpeta = iniCarpeta;
		this.file = file;
		this.id_bodegaOrigen = id_bodegaOrigen;
	}

	public Archivos() {
		super();
	}
	
	public static File parseInputStreamToFile(InputStream input){
		File output = TempFile.createTempFile("tmp","tmp");
		try {
			FileUtils.copyInputStreamToFile(input, output);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (output);
	}
	
	public static File parseMultipartFormDatatoFile(Http.MultipartFormData.FilePart<TemporaryFile> archivo) {
		String fileName = archivo.getFilename();
		String ext = fileName.substring(fileName.lastIndexOf("."));
		while(ext.substring(1).indexOf(".")>0) {
			ext=ext.substring(1);
			ext=ext.substring(fileName.indexOf("."));
		}
		TemporaryFile file = archivo.getRef();
		File tmp = TempFile.createTempFile("tmp",ext);
		file.moveTo(tmp);
		return tmp;
	}

	public static InputStream leerArchivo(String path){
		String bucket = HomeController.config.getString("bucket");
		Region region = Region.US_EAST_2;
		S3Client s3 = S3Client.builder().region(region).build();
		ResponseBytes<GetObjectResponse> file = s3.getObjectAsBytes(GetObjectRequest.builder().bucket(bucket).key(path).build());
		s3.close();
		InputStream archivo = file.asInputStream();
		return(archivo);
	}
	
	public static List<List<String>> listaDeObjetos(String base, String carpeta){
		List<List<String>> lista = new ArrayList<List<String>>();
		List<String> listAux = new ArrayList<String>();
		String bucket = HomeController.config.getString("bucket");
		Region region = Region.US_EAST_2;
		S3Client s3 = S3Client.builder().region(region).build();
		try {
            ListObjectsRequest listObjects = ListObjectsRequest
                .builder()
                .bucket(bucket)
                .prefix(base+"/"+carpeta)
                .build();

            ListObjectsResponse res = s3.listObjects(listObjects);
            
            List<S3Object> objects = res.contents();
            for (S3Object myValue : objects) {
            	String[] aux = myValue.key().split("/");
            	if(aux.length==3) {
            		listAux.add(aux[2]);
            	}
            }
        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
        }
		
		//ORDENA LA LISTA
		for(int j=0;j<listAux.size();j++) {
	        for(int i=0;i<listAux.size()-j;i++) {
	            if (i+1!=listAux.size() && listAux.get(i).toLowerCase().compareTo(listAux.get(i+1).toLowerCase()) > 0) {
	                String aux = listAux.get(i);
	                listAux.set(i,listAux.get(i+1));
	                listAux.set(i+1, aux);
	            }
	        }
	    }
		
		
		
		for(int i=0; i<listAux.size(); i++) {
			
			List<String> aux = new ArrayList<String>();
			for(int j=0; j<6 && i<listAux.size(); j++) {
				aux.add(listAux.get(i));
				i++;
			}
			i--;
			lista.add(aux);
		}
		return(lista);
	}

	public static void grabarArchivo(File file, String path){
		String bucket = HomeController.config.getString("bucket");
		Region region = Region.US_EAST_2;
		S3Client s3 = S3Client.builder().region(region).build();
		s3.putObject(PutObjectRequest.builder().bucket(bucket).key(path).build(), RequestBody.fromFile(file));
		s3.close();
	}
	
	public static String grabarArchivos(Http.MultipartFormData.FilePart<TemporaryFile> archivo, String subCarpeta, String nombreArchivoSinExtencion) {
		 	String fileName = archivo.getFilename();
         	fileName = fileName.substring(fileName.indexOf("."));
			while(fileName.substring(1).indexOf(".")>0) {
				fileName=fileName.substring(1);
				fileName=fileName.substring(fileName.indexOf("."));
			}
			String ext = fileName;
			String path = subCarpeta+"/"+nombreArchivoSinExtencion + ext;
			String bucket = HomeController.config.getString("bucket");
			
			TemporaryFile file = archivo.getRef();
			Region region = Region.US_EAST_2;
			S3Client s3 = S3Client.builder().region(region).build();
			s3.putObject(PutObjectRequest.builder().bucket(bucket).key(path).build(),RequestBody.fromFile(file.path()));
			s3.close();
         return (nombreArchivoSinExtencion + ext);
	}
	
	public static String comprimirYgrabar(List<Http.MultipartFormData.FilePart<TemporaryFile>> FILES, String subCarpeta, String nombreArchivoSinExtencion) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		File tmp = TempFile.createTempFile("tmp","null");
        ZipOutputStream zos = new ZipOutputStream(baos);
        try (zos) {
            for (Http.MultipartFormData.FilePart<TemporaryFile> f : FILES) {
            	
            	String fileName = f.getFilename();
        		String ext = fileName.substring(fileName.lastIndexOf("."));
        		while(ext.substring(1).indexOf(".")>0) {
        			ext=ext.substring(1);
        			ext=ext.substring(fileName.indexOf("."));
        		}
        		
            	TemporaryFile file = f.getRef();
        		File auxTmp = TempFile.createTempFile("tmp",ext);
        		file.moveTo(auxTmp);
                
                zos.putNextEntry(new ZipEntry(fileName));
                try (FileInputStream fileInputStream = new FileInputStream(auxTmp)) {
					byte[] buf = new byte[1024];
					int bytesRead;

					while ((bytesRead = fileInputStream.read(buf)) > 0) {
						zos.write(buf, 0, bytesRead);
					}
				}
                zos.closeEntry();
            }
        }

        try (FileOutputStream outputStream = new FileOutputStream(tmp)) {
			outputStream.write(baos.toByteArray());
		}
		
        String ext = ".zip";
		String path = subCarpeta+"/"+ nombreArchivoSinExtencion + ext;
		String bucket = HomeController.config.getString("bucket");
		Region region = Region.US_EAST_2;
		S3Client s3 = S3Client.builder().region(region).build();
		s3.putObject(PutObjectRequest.builder().bucket(bucket).key(path).build(),RequestBody.fromFile(tmp));
		s3.close();
		return (nombreArchivoSinExtencion + ext);
    }
	

	

}
