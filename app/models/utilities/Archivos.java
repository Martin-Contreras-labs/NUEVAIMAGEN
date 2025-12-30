package models.utilities;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
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
		if (input == null) return null;
		File output = null;
		try (InputStream autoCloseInput = input) {
			output = TempFile.createTempFile("stream_upload", ".tmp");
			FileUtils.copyInputStreamToFile(autoCloseInput, output);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return output;
	}


	
	public static File parseMultipartFormDatatoFile(Http.MultipartFormData.FilePart<TemporaryFile> archivo) {
		if (archivo == null) return null;
		String fileName = archivo.getFilename();
		String ext = "";
		int i = fileName.lastIndexOf('.');
		if (i > 0) {
			ext = fileName.substring(i);
		}
		TemporaryFile tempRef = archivo.getRef();
		File tmp = null;
		try {
			tmp = TempFile.createTempFile("upload", ext);
			tempRef.atomicMoveWithFallback(tmp.toPath());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return tmp;
	}

	public static InputStream parseFileToInputStream(File file) {
		if (file == null || !file.exists() || !file.isFile()) {
			return null;
		}
		try {
			return new FileInputStream(file);
		} catch (Exception e) {
			return null;
		}
	}

	public static InputStream leerArchivo(String path){
		String bucket = HomeController.config.getString("bucket");
		Region region = Region.US_EAST_2;
		try (S3Client s3 = S3Client.builder().region(region).build()) {
			GetObjectRequest getObjectRequest = GetObjectRequest.builder()
					.bucket(bucket)
					.key(path)
					.build();
			ResponseBytes<GetObjectResponse> objectBytes = s3.getObjectAsBytes(getObjectRequest);
			return objectBytes.asInputStream();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<List<String>> listaDeObjetos(String base, String carpeta){
		List<List<String>> listaFinal = new ArrayList<>();
		List<String> nombresArchivos = new ArrayList<>();
		String bucket = HomeController.config.getString("bucket");
		Region region = Region.US_EAST_2;
		try (S3Client s3 = S3Client.builder().region(region).build()) {
			ListObjectsRequest listObjects = ListObjectsRequest.builder()
					.bucket(bucket)
					.prefix(base + "/" + carpeta + "/")
					.build();
			ListObjectsResponse res = s3.listObjects(listObjects);
			for (S3Object myValue : res.contents()) {
				String key = myValue.key();
				// Extraer solo el nombre del archivo (después de la última "/")
				String[] partes = key.split("/");
				if (partes.length > 0) {
					String nombreArchivo = partes[partes.length - 1];
					if (!nombreArchivo.isEmpty()) {
						nombresArchivos.add(nombreArchivo);
					}
				}
			}
		} catch (S3Exception e) {
			return listaFinal;
		}
		Collections.sort(nombresArchivos, String.CASE_INSENSITIVE_ORDER);
		for (int i = 0; i < nombresArchivos.size(); i += 6) {
			int fin = Math.min(i + 6, nombresArchivos.size());
			listaFinal.add(new ArrayList<>(nombresArchivos.subList(i, fin)));
		}
		return listaFinal;
	}

	public static void grabarArchivo(File file, String path){
		if (file == null || !file.exists()) {
			return;
		}
		String bucket = HomeController.config.getString("bucket");
		Region region = Region.US_EAST_2;
		try (S3Client s3 = S3Client.builder().region(region).build()) {
			PutObjectRequest putObjectRequest = PutObjectRequest.builder()
					.bucket(bucket)
					.key(path)
					.build();
			s3.putObject(putObjectRequest, RequestBody.fromFile(file));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String grabarArchivos(Http.MultipartFormData.FilePart<TemporaryFile> archivo, String subCarpeta, String nombreArchivoSinExtencion) {
		if (archivo == null) return null;
		String fileNameOriginal = archivo.getFilename();
		String ext = "";
		int lastDotIndex = fileNameOriginal.lastIndexOf('.');
		if (lastDotIndex >= 0) {
			ext = fileNameOriginal.substring(lastDotIndex);
		}
		String nombreFinalConExt = nombreArchivoSinExtencion + ext;
		String pathS3 = subCarpeta + "/" + nombreFinalConExt;
		String bucket = HomeController.config.getString("bucket");
		Region region = Region.US_EAST_2;
		try (S3Client s3 = S3Client.builder().region(region).build()) {
			TemporaryFile tempFile = archivo.getRef();
			PutObjectRequest putObjectRequest = PutObjectRequest.builder()
					.bucket(bucket)
					.key(pathS3)
					.build();
			s3.putObject(putObjectRequest, RequestBody.fromFile(tempFile.path()));
			return nombreFinalConExt;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String comprimirYgrabar(List<Http.MultipartFormData.FilePart<TemporaryFile>> FILES, String subCarpeta, String nombreArchivoSinExtencion) throws Exception {
		if (FILES == null || FILES.isEmpty()) return null;
		String extZip = ".zip";
		String nombreFinal = nombreArchivoSinExtencion + extZip;
		String pathS3 = subCarpeta + "/" + nombreFinal;
		String bucket = HomeController.config.getString("bucket");
		Region region = Region.US_EAST_2;
		File zipFile = TempFile.createTempFile("compress", ".zip");
		try (FileOutputStream fos = new FileOutputStream(zipFile);
			 ZipOutputStream zos = new ZipOutputStream(fos)) {
			for (Http.MultipartFormData.FilePart<TemporaryFile> f : FILES) {
				String fileName = f.getFilename();
				TemporaryFile tempRef = f.getRef();
				zos.putNextEntry(new ZipEntry(fileName));
				Files.copy(tempRef.path(), zos);
				zos.closeEntry();
			}
		} catch (IOException e) {
			if (zipFile.exists()) zipFile.delete();
			throw e;
		}
		try (S3Client s3 = S3Client.builder().region(region).build()) {
			s3.putObject(PutObjectRequest.builder().bucket(bucket).key(pathS3).build(),
					RequestBody.fromFile(zipFile));
		} finally {
			if (zipFile.exists()) zipFile.delete();
		}
		return nombreFinal;
    }
	

	

}
