
package viewsMnuOdoAppWeb.html

import _root_.play.twirl.api.TwirlFeatureImports.*
import _root_.play.twirl.api.TwirlHelperImports.*
import scala.language.adhocExtensions
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import java.lang._
import java.util._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.api.data.Field
import play.data._
import play.core.j.PlayFormsMagicForJava._
import scala.jdk.CollectionConverters._

object odoListarVentasWeb extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.VentaServicio],List[Long],Long,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listVentas: List[tables.VentaServicio], listAnios: List[Long], year: Long):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""

"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),format.raw/*7.2*/("""<div id="enCarga" class="blocker" style="display: none;"><br><br><br><br><br><br><h1>En proceso.....<div id="msgEspera"></div></h1></div>
	

	<!-- menues(mapDiccionario, mapPermiso, userMnu, " ") -->
	<div id="mostrarmostrar" style="display:none;">
		<!-- barraTitulo(mapDiccionario, "LISTA DE REPORT DIARIO", "(SELECCIONAR)") -->
		<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">
				<div align="center">
					"""),_display_(/*16.7*/for(anio <-listAnios) yield /*16.28*/{_display_(Seq[Any](format.raw/*16.29*/("""
						"""),format.raw/*17.7*/("""<a href="#" onclick="location.href='/odoListarVentasWeb/"""),_display_(/*17.64*/anio),format.raw/*17.68*/("""'">
							<kbd style="background-color: #73C6B6">"""),_display_(/*18.48*/anio),format.raw/*18.52*/("""</kbd>
						</a>
					""")))}),format.raw/*20.7*/("""
				"""),format.raw/*21.5*/("""</div>
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>ID</TH>
							<TH style="min-width:80px;">FECHA</TH>
							<TH>"""),_display_(/*27.13*/mapDiccionario/*27.27*/.get("BODEGA")),format.raw/*27.41*/("""</TH>
							<TH>COTI</TH>
							<TH>CODIGO</TH>
							<TH>SERVICIO</TH>
							<TH>EQUIPO</TH>
							<TH>UN</TH>
							<TH>CANTIDAD</TH>
							<TH>REPORT</TH>
							
							<TH>ANEXO</TH>
							<TH>ALBUM</TH>
							
							<TH>DETALLE</TH>
							<TH>OPERADOR</TH>
							<TH>AUTORIZADOR</TH>
							"""),_display_(if(mapPermiso.get("odoVentasEliminar")!=null)/*42.54*/ {_display_(Seq[Any](format.raw/*42.56*/("""
								"""),format.raw/*43.9*/("""<TH>DEL</TH>
							""")))} else {null} ),format.raw/*44.9*/("""
						"""),format.raw/*45.7*/("""</TR>
					</thead>
					<tbody>
						"""),_display_(/*48.8*/for(lista1 <- listVentas) yield /*48.33*/{_display_(Seq[Any](format.raw/*48.34*/("""
							"""),format.raw/*49.8*/("""<TR>
								<td  style="text-align:center;">"""),_display_(/*50.42*/lista1/*50.48*/.getId()),format.raw/*50.56*/("""</td>
								<td  style="text-align:center; min-width:80px;">
									<div style="display:none">"""),_display_(/*52.37*/utilities/*52.46*/.Fechas.AAMMDD(lista1.getFecha())),format.raw/*52.79*/("""</div>
									"""),_display_(/*53.11*/utilities/*53.20*/.Fechas.DDMMAA(lista1.getFecha())),format.raw/*53.53*/("""
								"""),format.raw/*54.9*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*55.40*/lista1/*55.46*/.getNomBodegaEmpresa()),format.raw/*55.68*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*56.42*/lista1/*56.48*/.getNroCotiOdo()),format.raw/*56.64*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*57.42*/lista1/*57.48*/.getCodServicio()),format.raw/*57.65*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*58.40*/lista1/*58.46*/.getNomServicio()),format.raw/*58.63*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*59.40*/lista1/*59.46*/.getNomEquipo()),format.raw/*59.61*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*60.42*/lista1/*60.48*/.getUnidadServicio()),format.raw/*60.68*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*61.41*/lista1/*61.47*/.getCantidad()),format.raw/*61.61*/("""</td>
								<td  style="text-align:center;">
									"""),_display_(if(lista1.getReportPDF().equals("0"))/*63.48*/{_display_(Seq[Any](format.raw/*63.49*/("""
										"""),format.raw/*64.11*/("""--
									""")))}else/*65.15*/{_display_(Seq[Any](format.raw/*65.16*/("""
										"""),format.raw/*66.11*/("""<a href="#" onclick="mostrarPDF('"""),_display_(/*66.45*/lista1/*66.51*/.getReportPDF()),format.raw/*66.66*/("""')">
											<kbd style="background-color: #85C1E9">pdf</kbd>
										</a>
									""")))}),format.raw/*69.11*/("""
								"""),format.raw/*70.9*/("""</td>
								<td  style="text-align:center;">
									"""),_display_(if(lista1.getDocAnexo().equals("0"))/*72.47*/{_display_(Seq[Any](format.raw/*72.48*/("""
										"""),format.raw/*73.11*/("""<span class="btn btn-info btn-sm btn-file" style="font-size: 8px;">
											<form id="odoVentasGrabaDocAnexoWeb" action="/odoVentasGrabaDocAnexoWeb/" enctype="multipart/form-data" method="POST">
												<input type="hidden" name="id_ventaServicio" value=""""),_display_(/*75.66*/lista1/*75.72*/.getId()),format.raw/*75.80*/("""">
												<input type="hidden" name="year" value=""""),_display_(/*76.54*/year),format.raw/*76.58*/("""">
												<input type="hidden" name="docAnexo" value=""""),_display_(/*77.58*/lista1/*77.64*/.getDocAnexo()),format.raw/*77.78*/("""">
													<div id="txtBtn">adjuntar</div>
													<input type="file" multiple id="docAdjunto" name="docAdjunto[]" title="Cabiar el documento" onchange="LimitAttach(this.form, this.form.docAdjunto.value); $('#seModifico').val(1);">
											</form>
										</span>
									""")))}else/*82.15*/{_display_(Seq[Any](format.raw/*82.16*/("""
									
										"""),format.raw/*84.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*84.52*/lista1/*84.58*/.getDocAnexo()),format.raw/*84.72*/("""')">
											<kbd style="background-color: #7F8C8D">docum</kbd>
										</a>
										
										<span class="btn btn-info btn-sm btn-file" style="font-size: 8px; height:10px; width:10px">
											<form id="odoVentasGrabaDocAnexoWeb" action="/odoVentasGrabaDocAnexoWeb/" enctype="multipart/form-data" method="POST">
												<input type="hidden" name="id_ventaServicio" value=""""),_display_(/*90.66*/lista1/*90.72*/.getId()),format.raw/*90.80*/("""">
												<input type="hidden" name="year" value=""""),_display_(/*91.54*/year),format.raw/*91.58*/("""">
												<input type="hidden" name="docAnexo" value=""""),_display_(/*92.58*/lista1/*92.64*/.getDocAnexo()),format.raw/*92.78*/("""">
													<div id="txtBtn">c</div>
													<input type="file" multiple id="docAdjunto" name="docAdjunto[]" title="Cabiar el documento" onchange="LimitAttach(this.form, this.form.docAdjunto.value); $('#seModifico').val(1);">
											</form>
										</span>
									
									""")))}),format.raw/*98.11*/("""
								"""),format.raw/*99.9*/("""</td>
								
								"""),_display_(if(mapPermiso.get("parametro.album-fotos")!=null && mapPermiso.get("parametro.album-fotos").equals("1"))/*101.114*/{_display_(Seq[Any](format.raw/*101.115*/("""
									"""),format.raw/*102.10*/("""<td style="text-align:center;">
										"""),_display_(if(lista1.getAlbumFotos().equals("0"))/*103.50*/{_display_(Seq[Any](format.raw/*103.51*/("""
											"""),format.raw/*104.12*/("""<a href="#" onclick='$("#id_ventaServicioAlbum").val(""""),_display_(/*104.67*/lista1/*104.73*/.getId()),format.raw/*104.81*/(""""); $("#modalTitulo").text("ALBUM FOTOS ID: """),_display_(/*104.126*/lista1/*104.132*/.getId()),format.raw/*104.140*/(""""); $("#modalAlbum").modal("show");'>
												<kbd style="font-size: 8px; height:10px; width:10px; background-color: #17a2b8">crear</kbd>
											</a>
											
										""")))}else/*108.16*/{_display_(Seq[Any](format.raw/*108.17*/("""
											"""),format.raw/*109.12*/("""<a href="/muestraAlbumFotos/"""),_display_(/*109.41*/lista1/*109.47*/.getAlbumFotos()),format.raw/*109.63*/("""">
												<kbd style="background-color: #7F8C8D">album</kbd>
											</a>
											<a href="#" onclick='$("#id_ventaServicioAlbum").val(""""),_display_(/*112.67*/lista1/*112.73*/.getId()),format.raw/*112.81*/(""""); $("#modalTitulo").text("ALBUM FOTOS ID: """),_display_(/*112.126*/lista1/*112.132*/.getId()),format.raw/*112.140*/(""""); $("#modalAlbum").modal("show");'>
												<kbd style="font-size: 8px; height:10px; width:10px; background-color: #17a2b8">+</kbd>
											</a>
										""")))}),format.raw/*115.12*/("""
									"""),format.raw/*116.10*/("""</td>
								""")))}else/*117.14*/{_display_(Seq[Any](format.raw/*117.15*/("""
									"""),format.raw/*118.10*/("""<td style="text-align:center;">--</td>
								""")))}),format.raw/*119.10*/("""
								
								"""),format.raw/*121.9*/("""<td style="text-align:center;">
									<a href="/odoDetalleVentaWeb/"""),_display_(/*122.40*/lista1/*122.46*/.getId()),format.raw/*122.54*/("""">
										<kbd style="background-color: #73C6B6">detalle</kbd>
									</a>
								</td>
								<td style="text-align:center;">
									<a href="/odoFirmaOperadorWeb/"""),_display_(/*127.41*/lista1/*127.47*/.getId()),format.raw/*127.55*/("""">
										<kbd style="background-color: #F0B27A">FIR</kbd>
									</a>
									<img src="data:image/jpeg;base64, """),_display_(/*130.45*/lista1/*130.51*/.getFirmaPDFoperador()),format.raw/*130.73*/("""" height="10px" />
								</td>
								<td style="text-align:center;">
									<a href="/odoFirmaAutorizadorWeb/"""),_display_(/*133.44*/lista1/*133.50*/.getId()),format.raw/*133.58*/("""">
										<kbd style="background-color: #F7DC6F">FIR</kbd>
									</a>
									<img src="data:image/jpeg;base64, """),_display_(/*136.45*/lista1/*136.51*/.getFirmaPDFautorizador()),format.raw/*136.76*/("""" height="10px" />
								</td>
								"""),_display_(if(mapPermiso.get("odoVentasEliminar")!=null)/*138.55*/ {_display_(Seq[Any](format.raw/*138.57*/("""
									"""),format.raw/*139.10*/("""<td  style="text-align:center;">
										"""),_display_(if(lista1.getId()!=1)/*140.33*/{_display_(Seq[Any](format.raw/*140.34*/("""
											"""),format.raw/*141.12*/("""<a href="#" onclick= "eliminarVentaServicio('"""),_display_(/*141.58*/lista1/*141.64*/.getId()),format.raw/*141.72*/("""')">
												<kbd style="background-color: red">X</kbd>
											</a>
										""")))} else {null} ),format.raw/*144.12*/("""
									"""),format.raw/*145.10*/("""</td>
								""")))} else {null} ),format.raw/*146.10*/("""
								
							"""),format.raw/*148.8*/("""</TR>
			 			""")))}),format.raw/*149.9*/("""
					"""),format.raw/*150.6*/("""</tbody>
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/odoVentasHomeWeb/';">
				</div>
			</div>
		</div>
	</div>
	
	<div id='muestraPDF' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>REPORT DIARIO</h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<div id="rutaPDF"></div>
	   				<div class='row'>
						<div class='col-sm-12' style='text-align:center'>
							<button type='button' class='btn btn-sm  btn-success' style='font-size: 10px;' data-dismiss='modal'>Listo</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<input type="hidden" value="0" id="id_ventaServicioAlbum">
	
	"""),_display_(if(mapPermiso.get("parametro.album-fotos")!=null && mapPermiso.get("parametro.album-fotos").equals("1"))/*186.107*/{_display_(Seq[Any](format.raw/*186.108*/("""
	
		"""),format.raw/*188.3*/("""<div id='modalAlbum' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>"+
			<div class='modal-dialog modal-dialog-scrollable' role='document' style="width: 400px">
				<div class='modal-content'>
					<div class='modal-header'>
						<h5 class='modal-title'><div id="modalTitulo">ALBUM FOTOS ID: </div></h5>
						<button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>&times;</span></button>
			        </div>
			        <div class='modal-body'>
			        	<table class="table-bordered table-condensed">
							<tbody>
								<tr>
									<td>
										<span class="btn btn-info btn-sm btn-file" style="font-size: 12px;">
											<div id="txtBtn">Seleccionar Imagenes</div>
												<input type="file" multiple accept="image/*" id="imagen" onchange="cambiarTexto(this)">
											</div>
										</span>
									</td>
									<td style="text-align:center" id="sube2">
									</td>
								</tr>
								<tr>
									<td>
										Anteponer sigla
									</td>
									<td>
										<input type="text" class="form-control" id="textSigla" value="" autocomplete="off" maxlength="5" placeholder="max 5 caracteres" onchange="value = value.trim();">
									</td>
								</tr>
								<tr>
									<td>
										<input type="button" class="btn btn-sm btn-warning" id="btnComprimirBlob" value="Subir las imagenes">
									</td>
									<td>
										<div id="textBtnComprimir">&nbsp;Ninguna imagen seleccionada&nbsp;</div>
									</td>
								</tr>
							</tbody>
						</table>
						<div id="msgEspera2"></div>
			        </div>
		    	</div>
			</div>
		</div>
	
		
	""")))} else {null} ),format.raw/*234.3*/("""
	
	
	"""),format.raw/*237.2*/("""<form id="form_eliminar" method="post" action="/odoVentaServicioEliminaWeb/">
		<input type="hidden" id="form_id_ventaServicio" name="id_ventaServicio">
	</form>
	
	<form class="formulario" id="formDescargaDocumento" method="post" action="/downloadPDF/">	
		<input type="hidden" id="descargaDocumento" name="nombreArchivo">
	</form>
""")))}),format.raw/*244.2*/("""




"""),format.raw/*249.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*250.31*/("""{"""),format.raw/*250.32*/("""
		  """),format.raw/*251.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*251.36*/("""{"""),format.raw/*251.37*/("""
		    	"""),format.raw/*252.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
				"order": [[ 1, "desc" ],[ 0, "desc" ]],
		    	"language": """),format.raw/*255.20*/("""{"""),format.raw/*255.21*/("""
		        	"""),format.raw/*256.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*257.11*/("""}"""),format.raw/*257.12*/("""
		  """),format.raw/*258.5*/("""}"""),format.raw/*258.6*/(""" """),format.raw/*258.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*260.2*/("""}"""),format.raw/*260.3*/(""");
	
	const mostrarPDF = (nombrePDF) => """),format.raw/*262.36*/("""{"""),format.raw/*262.37*/("""
		  """),format.raw/*263.5*/("""document.getElementById('rutaPDF').innerHTML="<object data='/showPdf/"+nombrePDF+"' type='application/pdf' width='100%' height='720'></object>";
		  $('#muestraPDF').modal('show');
	"""),format.raw/*265.2*/("""}"""),format.raw/*265.3*/("""
	
	"""),format.raw/*267.2*/("""const eliminarVentaServicio = (id_ventaServicio) => """),format.raw/*267.54*/("""{"""),format.raw/*267.55*/("""
		"""),format.raw/*268.3*/("""alertify.confirm("Esta seguro de eliminar este report ID: "+id_ventaServicio, function (e) """),format.raw/*268.94*/("""{"""),format.raw/*268.95*/("""
			"""),format.raw/*269.4*/("""if (e) """),format.raw/*269.11*/("""{"""),format.raw/*269.12*/("""
				"""),format.raw/*270.5*/("""$("#form_id_ventaServicio").val(id_ventaServicio);
				document.getElementById("form_eliminar").submit();
			"""),format.raw/*272.4*/("""}"""),format.raw/*272.5*/("""
		"""),format.raw/*273.3*/("""}"""),format.raw/*273.4*/(""");
	"""),format.raw/*274.2*/("""}"""),format.raw/*274.3*/("""
	
	"""),format.raw/*276.2*/("""const descargaDocumento = (nombreDOC) => """),format.raw/*276.43*/("""{"""),format.raw/*276.44*/("""
		  """),format.raw/*277.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*279.2*/("""}"""),format.raw/*279.3*/("""
	
	"""),format.raw/*281.2*/("""let extArray = new Array(".gif", ".jpg", ".png", ".jpeg", ".pdf", ".xls", ".doc", ".xlsx", ".docx", ".tar", ".zip", ".rar");
	const LimitAttach = (form, file) => """),format.raw/*282.38*/("""{"""),format.raw/*282.39*/("""
		
		"""),format.raw/*284.3*/("""if (!file) return;
		
		const $inputArchivos = document.querySelector("#docAdjunto");
		const archivosParaSubir = $inputArchivos.files;
		let tamanio = 0;
		let allowSubmit = false;
		
		for(let i=0; i<archivosParaSubir.length; i++)"""),format.raw/*291.48*/("""{"""),format.raw/*291.49*/("""
			"""),format.raw/*292.4*/("""tamanio += archivosParaSubir[i].size;
			let nombre = archivosParaSubir[i].name;
			let extencion = nombre.substring(nombre.lastIndexOf(".")).toLowerCase();
			for (let j = 0; j < extArray.length; j++) """),format.raw/*295.46*/("""{"""),format.raw/*295.47*/("""
				"""),format.raw/*296.5*/("""if (extArray[j] == extencion) """),format.raw/*296.35*/("""{"""),format.raw/*296.36*/(""" 
					"""),format.raw/*297.6*/("""allowSubmit = true;
				"""),format.raw/*298.5*/("""}"""),format.raw/*298.6*/("""
			"""),format.raw/*299.4*/("""}"""),format.raw/*299.5*/("""
			"""),format.raw/*300.4*/("""if(allowSubmit && i < archivosParaSubir.length -1)"""),format.raw/*300.54*/("""{"""),format.raw/*300.55*/("""
				"""),format.raw/*301.5*/("""allowSubmit = false;
			"""),format.raw/*302.4*/("""}"""),format.raw/*302.5*/("""
		"""),format.raw/*303.3*/("""}"""),format.raw/*303.4*/("""
		
		"""),format.raw/*305.3*/("""if (allowSubmit) """),format.raw/*305.20*/("""{"""),format.raw/*305.21*/("""
			"""),format.raw/*306.4*/("""if(tamanio > 200000000)"""),format.raw/*306.27*/("""{"""),format.raw/*306.28*/("""
				"""),format.raw/*307.5*/("""alert("Se permite máximo subir 200 MB,"
				+" lo que se intenta subir pesa: "+Math.round(tamanio/10000)/100+" MB");
				form.docAdjunto.value="";
				return;
			"""),format.raw/*311.4*/("""}"""),format.raw/*311.5*/("""
		"""),format.raw/*312.3*/("""}"""),format.raw/*312.4*/("""else"""),format.raw/*312.8*/("""{"""),format.raw/*312.9*/("""
			"""),format.raw/*313.4*/("""alert("Se permiten únicamente archivos con la extención: " 
			+ (extArray.join("  ")) + "\nPor favor, seleccione solo archivos con extenciones permitidas "
			+ "e intente de nuevo.");
			form.docAdjunto.value="";
			return;
		"""),format.raw/*318.3*/("""}"""),format.raw/*318.4*/("""
		"""),format.raw/*319.3*/("""document.getElementById('enCarga').style.display="block";
		$("#odoVentasGrabaDocAnexoWeb").submit();
	"""),format.raw/*321.2*/("""}"""),format.raw/*321.3*/("""
	
"""),format.raw/*323.1*/("""</script>

<script type="text/javascript">

	

	const $imagen = document.querySelector("#imagen");
	
	const cambiarTexto = (nodo) =>"""),format.raw/*331.32*/("""{"""),format.raw/*331.33*/("""
		"""),format.raw/*332.3*/("""$("#textBtnComprimir").text(nodo.files.length + " imagenes seleccionadas");
	"""),format.raw/*333.2*/("""}"""),format.raw/*333.3*/("""
	
	"""),format.raw/*335.2*/("""document.querySelector("#btnComprimirBlob").addEventListener("click", async () => """),format.raw/*335.84*/("""{"""),format.raw/*335.85*/("""
		"""),format.raw/*336.3*/("""if ($imagen.files.length <= 0) """),format.raw/*336.34*/("""{"""),format.raw/*336.35*/("""
			"""),format.raw/*337.4*/("""return;
		"""),format.raw/*338.3*/("""}"""),format.raw/*338.4*/("""
		"""),format.raw/*339.3*/("""document.getElementById('enCarga').style.display="block";
		let sigla = $("#textSigla").val();
		if(sigla.length > 0)"""),format.raw/*341.23*/("""{"""),format.raw/*341.24*/("""
			"""),format.raw/*342.4*/("""sigla = sigla+"_";
		"""),format.raw/*343.3*/("""}"""),format.raw/*343.4*/("""
		"""),format.raw/*344.3*/("""subirArchivo2($imagen, contador, sigla);
	"""),format.raw/*345.2*/("""}"""),format.raw/*345.3*/(""");
	
	let contador = 0;

		
		const MAX_WIDTH = 1000;
		const MAX_HEIGHT = 750;
		const MIME_TYPE = "image/jpeg";
		const QUALITY = 0.7;

		const input = document.getElementById("img-input");
		
		function subirArchivo2($imagen, contador, sigla) """),format.raw/*357.52*/("""{"""),format.raw/*357.53*/("""
			"""),format.raw/*358.4*/("""const file = $imagen.files[contador]; 
			  const blobURL = URL.createObjectURL(file);
			  const img = new Image();
			  img.src = blobURL;
			  img.onerror = function () """),format.raw/*362.32*/("""{"""),format.raw/*362.33*/("""
			    """),format.raw/*363.8*/("""URL.revokeObjectURL(this.src);
			  """),format.raw/*364.6*/("""}"""),format.raw/*364.7*/(""";
			  
			  
			  img.onload = function () """),format.raw/*367.31*/("""{"""),format.raw/*367.32*/("""
			    """),format.raw/*368.8*/("""URL.revokeObjectURL(this.src);
			    const [newWidth, newHeight] = calculateSize(img, MAX_WIDTH, MAX_HEIGHT);
			    const canvas = document.createElement("canvas");
			    canvas.width = newWidth;
			    canvas.height = newHeight;
			    const ctx = canvas.getContext("2d");
			    ctx.drawImage(img, 0, 0, newWidth, newHeight);
			    canvas.toBlob(
			      (blob) => """),format.raw/*376.20*/("""{"""),format.raw/*376.21*/("""
			        """),format.raw/*377.12*/("""displayInfo('Original file', file);
			        displayInfo('Compressed file', blob);
			      
			        const myFile = new File([blob], sigla + $imagen.files[contador].name, """),format.raw/*380.82*/("""{"""),format.raw/*380.83*/("""
					    """),format.raw/*381.10*/("""type: blob.type,
					"""),format.raw/*382.6*/("""}"""),format.raw/*382.7*/(""");
					
					$("#msgEspera").text(" cargando imagen "+(contador+1)+" de "+$imagen.files.length);
					$("#msgEspera2").text(" En proceso..... cargando imagen "+(contador+1)+" de "+$imagen.files.length);
					
					let id_ventaServicioAlbum = $("#id_ventaServicioAlbum").val();
					
					var formData = new FormData();
					formData.append("fotosAdjunto[0]",myFile);
					formData.append("id_ventaServicio",id_ventaServicioAlbum);
					formData.append("iniCarpeta",'Fotos_Report_');
						$.ajax("""),format.raw/*393.14*/("""{"""),format.raw/*393.15*/("""
							"""),format.raw/*394.8*/("""async: false,
					        url: "/odoGrabaAlbumFotosAjax/",
					        type: "POST",
					        method: "POST",
					        data: formData,
					        cache: false,
					        contentType: false,
					     	processData: false,
					     	success: function(rs)"""),format.raw/*402.33*/("""{"""),format.raw/*402.34*/("""
					     		"""),format.raw/*403.13*/("""if(rs == "error")"""),format.raw/*403.30*/("""{"""),format.raw/*403.31*/("""
					     			"""),format.raw/*404.14*/("""alertify.alert('Se presento un error, no fueron subidas las imagenes', function () """),format.raw/*404.97*/("""{"""),format.raw/*404.98*/("""
					     				"""),format.raw/*405.15*/("""document.getElementById("imagen").value = "";
					     			"""),format.raw/*406.14*/("""}"""),format.raw/*406.15*/(""");
					     		"""),format.raw/*407.13*/("""}"""),format.raw/*407.14*/("""else"""),format.raw/*407.18*/("""{"""),format.raw/*407.19*/("""
					     			"""),format.raw/*408.14*/("""contador++;
					     			if(contador < $imagen.files.length)"""),format.raw/*409.49*/("""{"""),format.raw/*409.50*/("""
					     				"""),format.raw/*410.15*/("""subirArchivo2($imagen, contador, sigla);
					     			"""),format.raw/*411.14*/("""}"""),format.raw/*411.15*/("""else"""),format.raw/*411.19*/("""{"""),format.raw/*411.20*/("""
					     				"""),format.raw/*412.15*/("""alertify.alert('Subidas las imagenes con exito', function () """),format.raw/*412.76*/("""{"""),format.raw/*412.77*/("""
							     			"""),format.raw/*413.16*/("""document.getElementById("imagen").value = "";
							     			$("#textBtnComprimir").text("Ninguna imagen seleccionada");
							     		"""),format.raw/*415.15*/("""}"""),format.raw/*415.16*/(""");
										document.getElementById('enCarga').style.display="none";
										location.reload();
					     			"""),format.raw/*418.14*/("""}"""),format.raw/*418.15*/("""
					     		"""),format.raw/*419.13*/("""}"""),format.raw/*419.14*/("""
					     	"""),format.raw/*420.12*/("""}"""),format.raw/*420.13*/("""
					    """),format.raw/*421.10*/("""}"""),format.raw/*421.11*/(""");
			      """),format.raw/*422.10*/("""}"""),format.raw/*422.11*/(""",
			      MIME_TYPE,
			      QUALITY
			    );
			  """),format.raw/*426.6*/("""}"""),format.raw/*426.7*/(""";
		"""),format.raw/*427.3*/("""}"""),format.raw/*427.4*/("""

		"""),format.raw/*429.3*/("""function calculateSize(img, maxWidth, maxHeight) """),format.raw/*429.52*/("""{"""),format.raw/*429.53*/("""
		  """),format.raw/*430.5*/("""let width = img.width;
		  let height = img.height;

		  if (width > height) """),format.raw/*433.25*/("""{"""),format.raw/*433.26*/("""
		    """),format.raw/*434.7*/("""if (width > maxWidth) """),format.raw/*434.29*/("""{"""),format.raw/*434.30*/("""
		      """),format.raw/*435.9*/("""height = Math.round((height * maxWidth) / width);
		      width = maxWidth;
		    """),format.raw/*437.7*/("""}"""),format.raw/*437.8*/("""
		  """),format.raw/*438.5*/("""}"""),format.raw/*438.6*/(""" """),format.raw/*438.7*/("""else """),format.raw/*438.12*/("""{"""),format.raw/*438.13*/("""
		    """),format.raw/*439.7*/("""if (height > maxHeight) """),format.raw/*439.31*/("""{"""),format.raw/*439.32*/("""
		      """),format.raw/*440.9*/("""width = Math.round((width * maxHeight) / height);
		      height = maxHeight;
		    """),format.raw/*442.7*/("""}"""),format.raw/*442.8*/("""
		  """),format.raw/*443.5*/("""}"""),format.raw/*443.6*/("""
		  """),format.raw/*444.5*/("""return [width, height];
		"""),format.raw/*445.3*/("""}"""),format.raw/*445.4*/("""

		"""),format.raw/*447.3*/("""function displayInfo(label, file) """),format.raw/*447.37*/("""{"""),format.raw/*447.38*/("""
		  """),format.raw/*448.5*/("""const p = document.createElement('p');
		  p.innerText = `$"""),format.raw/*449.21*/("""{"""),format.raw/*449.22*/("""label"""),format.raw/*449.27*/("""}"""),format.raw/*449.28*/(""" """),format.raw/*449.29*/("""- $"""),format.raw/*449.32*/("""{"""),format.raw/*449.33*/("""readableBytes(file.size)"""),format.raw/*449.57*/("""}"""),format.raw/*449.58*/("""`;
		"""),format.raw/*450.3*/("""}"""),format.raw/*450.4*/("""

		"""),format.raw/*452.3*/("""function readableBytes(bytes) """),format.raw/*452.33*/("""{"""),format.raw/*452.34*/("""
		  """),format.raw/*453.5*/("""const i = Math.floor(Math.log(bytes) / Math.log(1024)),
		    sizes = ['B', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'];

		  return (bytes / Math.pow(1024, i)).toFixed(2) + ' ' + sizes[i];
		"""),format.raw/*457.3*/("""}"""),format.raw/*457.4*/("""
"""),format.raw/*458.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listVentas:List[tables.VentaServicio],listAnios:List[Long],year:Long): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listVentas,listAnios,year)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.VentaServicio],List[Long],Long) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listVentas,listAnios,year) => apply(mapDiccionario,mapPermiso,userMnu,listVentas,listAnios,year)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuOdoAppWeb/odoListarVentasWeb.scala.html
                  HASH: 04e8a69546db43f2d337c4650f77b79a34668ea7
                  MATRIX: 1062->1|1327->173|1355->176|1371->184|1410->186|1439->189|1931->655|1968->676|2007->677|2041->684|2125->741|2150->745|2228->796|2253->800|2307->824|2339->829|2618->1081|2641->1095|2676->1109|3058->1464|3098->1466|3134->1475|3198->1496|3232->1503|3298->1543|3339->1568|3378->1569|3413->1577|3486->1623|3501->1629|3530->1637|3656->1736|3674->1745|3728->1778|3772->1795|3790->1804|3844->1837|3880->1846|3952->1891|3967->1897|4010->1919|4084->1966|4099->1972|4136->1988|4210->2035|4225->2041|4263->2058|4335->2103|4350->2109|4388->2126|4460->2171|4475->2177|4511->2192|4585->2239|4600->2245|4641->2265|4714->2311|4729->2317|4764->2331|4885->2425|4924->2426|4963->2437|4999->2454|5038->2455|5077->2466|5138->2500|5153->2506|5189->2521|5310->2611|5346->2620|5466->2713|5505->2714|5544->2725|5834->2988|5849->2994|5878->3002|5961->3058|5986->3062|6073->3122|6088->3128|6123->3142|6435->3435|6474->3436|6523->3457|6591->3498|6606->3504|6641->3518|7058->3908|7073->3914|7102->3922|7185->3978|7210->3982|7297->4042|7312->4048|7347->4062|7670->4354|7706->4363|7863->4491|7904->4492|7943->4502|8052->4583|8092->4584|8133->4596|8216->4651|8232->4657|8262->4665|8336->4710|8353->4716|8384->4724|8589->4909|8629->4910|8670->4922|8727->4951|8743->4957|8781->4973|8957->5121|8973->5127|9003->5135|9077->5180|9094->5186|9125->5194|9322->5359|9361->5369|9400->5388|9440->5389|9479->5399|9559->5447|9605->5465|9704->5536|9720->5542|9750->5550|9952->5724|9968->5730|9998->5738|10146->5858|10162->5864|10206->5886|10350->6002|10366->6008|10396->6016|10544->6136|10560->6142|10607->6167|10722->6254|10763->6256|10802->6266|10895->6331|10935->6332|10976->6344|11050->6390|11066->6396|11096->6404|11228->6491|11267->6501|11327->6516|11372->6533|11417->6547|11451->6553|12785->7858|12826->7859|12859->7864|14572->9533|14606->9539|14971->9873|15004->9878|15095->9940|15125->9941|15158->9946|15218->9977|15248->9978|15284->9986|15471->10144|15501->10145|15542->10157|15649->10235|15679->10236|15712->10241|15741->10242|15770->10243|15871->10316|15900->10317|15969->10357|15999->10358|16032->10363|16242->10545|16271->10546|16303->10550|16384->10602|16414->10603|16445->10606|16565->10697|16595->10698|16627->10702|16663->10709|16693->10710|16726->10715|16863->10824|16892->10825|16923->10828|16952->10829|16984->10833|17013->10834|17045->10838|17115->10879|17145->10880|17178->10885|17310->10989|17339->10990|17371->10994|17562->11156|17592->11157|17626->11163|17887->11395|17917->11396|17949->11400|18180->11602|18210->11603|18243->11608|18302->11638|18332->11639|18367->11646|18419->11670|18448->11671|18480->11675|18509->11676|18541->11680|18620->11730|18650->11731|18683->11736|18735->11760|18764->11761|18795->11764|18824->11765|18858->11771|18904->11788|18934->11789|18966->11793|19018->11816|19048->11817|19081->11822|19271->11984|19300->11985|19331->11988|19360->11989|19392->11993|19421->11994|19453->11998|19709->12226|19738->12227|19769->12230|19900->12333|19929->12334|19960->12337|20121->12469|20151->12470|20182->12473|20287->12550|20316->12551|20348->12555|20459->12637|20489->12638|20520->12641|20580->12672|20610->12673|20642->12677|20680->12687|20709->12688|20740->12691|20886->12808|20916->12809|20948->12813|20997->12834|21026->12835|21057->12838|21127->12880|21156->12881|21431->13127|21461->13128|21493->13132|21694->13304|21724->13305|21760->13313|21824->13349|21853->13350|21926->13394|21956->13395|21992->13403|22393->13775|22423->13776|22464->13788|22669->13964|22699->13965|22738->13975|22788->13997|22817->13998|23343->14495|23373->14496|23409->14504|23706->14772|23736->14773|23778->14786|23824->14803|23854->14804|23897->14818|24009->14901|24039->14902|24083->14917|24171->14976|24201->14977|24245->14992|24275->14993|24308->14997|24338->14998|24381->15012|24470->15072|24500->15073|24544->15088|24627->15142|24657->15143|24690->15147|24720->15148|24764->15163|24854->15224|24884->15225|24929->15241|25093->15376|25123->15377|25264->15489|25294->15490|25336->15503|25366->15504|25407->15516|25437->15517|25476->15527|25506->15528|25547->15540|25577->15541|25659->15595|25688->15596|25720->15600|25749->15601|25781->15605|25859->15654|25889->15655|25922->15660|26028->15737|26058->15738|26093->15745|26144->15767|26174->15768|26211->15777|26321->15859|26350->15860|26383->15865|26412->15866|26441->15867|26475->15872|26505->15873|26540->15880|26593->15904|26623->15905|26660->15914|26772->15998|26801->15999|26834->16004|26863->16005|26896->16010|26950->16036|26979->16037|27011->16041|27074->16075|27104->16076|27137->16081|27225->16140|27255->16141|27289->16146|27319->16147|27349->16148|27381->16151|27411->16152|27464->16176|27494->16177|27527->16182|27556->16183|27588->16187|27647->16217|27677->16218|27710->16223|27934->16419|27963->16420|27992->16421
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|47->16|47->16|47->16|48->17|48->17|48->17|49->18|49->18|51->20|52->21|58->27|58->27|58->27|73->42|73->42|74->43|75->44|76->45|79->48|79->48|79->48|80->49|81->50|81->50|81->50|83->52|83->52|83->52|84->53|84->53|84->53|85->54|86->55|86->55|86->55|87->56|87->56|87->56|88->57|88->57|88->57|89->58|89->58|89->58|90->59|90->59|90->59|91->60|91->60|91->60|92->61|92->61|92->61|94->63|94->63|95->64|96->65|96->65|97->66|97->66|97->66|97->66|100->69|101->70|103->72|103->72|104->73|106->75|106->75|106->75|107->76|107->76|108->77|108->77|108->77|113->82|113->82|115->84|115->84|115->84|115->84|121->90|121->90|121->90|122->91|122->91|123->92|123->92|123->92|129->98|130->99|132->101|132->101|133->102|134->103|134->103|135->104|135->104|135->104|135->104|135->104|135->104|135->104|139->108|139->108|140->109|140->109|140->109|140->109|143->112|143->112|143->112|143->112|143->112|143->112|146->115|147->116|148->117|148->117|149->118|150->119|152->121|153->122|153->122|153->122|158->127|158->127|158->127|161->130|161->130|161->130|164->133|164->133|164->133|167->136|167->136|167->136|169->138|169->138|170->139|171->140|171->140|172->141|172->141|172->141|172->141|175->144|176->145|177->146|179->148|180->149|181->150|217->186|217->186|219->188|265->234|268->237|275->244|280->249|281->250|281->250|282->251|282->251|282->251|283->252|286->255|286->255|287->256|288->257|288->257|289->258|289->258|289->258|291->260|291->260|293->262|293->262|294->263|296->265|296->265|298->267|298->267|298->267|299->268|299->268|299->268|300->269|300->269|300->269|301->270|303->272|303->272|304->273|304->273|305->274|305->274|307->276|307->276|307->276|308->277|310->279|310->279|312->281|313->282|313->282|315->284|322->291|322->291|323->292|326->295|326->295|327->296|327->296|327->296|328->297|329->298|329->298|330->299|330->299|331->300|331->300|331->300|332->301|333->302|333->302|334->303|334->303|336->305|336->305|336->305|337->306|337->306|337->306|338->307|342->311|342->311|343->312|343->312|343->312|343->312|344->313|349->318|349->318|350->319|352->321|352->321|354->323|362->331|362->331|363->332|364->333|364->333|366->335|366->335|366->335|367->336|367->336|367->336|368->337|369->338|369->338|370->339|372->341|372->341|373->342|374->343|374->343|375->344|376->345|376->345|388->357|388->357|389->358|393->362|393->362|394->363|395->364|395->364|398->367|398->367|399->368|407->376|407->376|408->377|411->380|411->380|412->381|413->382|413->382|424->393|424->393|425->394|433->402|433->402|434->403|434->403|434->403|435->404|435->404|435->404|436->405|437->406|437->406|438->407|438->407|438->407|438->407|439->408|440->409|440->409|441->410|442->411|442->411|442->411|442->411|443->412|443->412|443->412|444->413|446->415|446->415|449->418|449->418|450->419|450->419|451->420|451->420|452->421|452->421|453->422|453->422|457->426|457->426|458->427|458->427|460->429|460->429|460->429|461->430|464->433|464->433|465->434|465->434|465->434|466->435|468->437|468->437|469->438|469->438|469->438|469->438|469->438|470->439|470->439|470->439|471->440|473->442|473->442|474->443|474->443|475->444|476->445|476->445|478->447|478->447|478->447|479->448|480->449|480->449|480->449|480->449|480->449|480->449|480->449|480->449|480->449|481->450|481->450|483->452|483->452|483->452|484->453|488->457|488->457|489->458
                  -- GENERATED --
              */
          