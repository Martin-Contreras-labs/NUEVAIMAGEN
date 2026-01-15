
package viewsMnuOdo.html

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

object odoListarVentas1 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template7[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.VentaServicio],Map[Long, List[List[String]]],String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listVentas: List[tables.VentaServicio], mapEquiposVigentes: Map[Long, List[List[String]]], desdeAAMMDD: String, hastaAAMMDD: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""

"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),format.raw/*9.2*/("""<div id="enCarga" class="blocker" style="display: none;"><br><br><br><br><br><br><h1>En proceso.....<div id="msgEspera"></div></h1></div>
	
	<form id="excel" class="formulario" method="post" action="/odoListarVentas1Excel/">
		<input type="hidden" name="fechaDesde" value=""""),_display_(/*12.50*/desdeAAMMDD),format.raw/*12.61*/("""">
		<input type="hidden" name="fechaHasta" value=""""),_display_(/*13.50*/hastaAAMMDD),format.raw/*13.61*/("""">
	</form>
	
	
	<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*18.4*/barraTitulo(mapDiccionario, "LISTA DE REPORT DIARIO", "(SELECCIONAR)")),format.raw/*18.74*/("""
		"""),format.raw/*19.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			
				<div align="center">
					<button type="button"  class="btn btn-sm btn-success" onclick="document.getElementById('excel').submit()">
						Exportar a Excel
					</button>
				</div>
				
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>ID</TH>
							<TH style="min-width:80px;">FECHA</TH>
							<TH>USER_MADA</TH>
							<TH>OPERADOR</TH>
							<TH>SUCURSAL</TH>
							<TH>"""),_display_(/*36.13*/mapDiccionario/*36.27*/.get("BODEGA")),format.raw/*36.41*/("""</TH>
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
							
							"""),_display_(if(mapPermiso.get("odoVentasEliminar")!=null)/*52.54*/ {_display_(Seq[Any](format.raw/*52.56*/("""
								"""),format.raw/*53.9*/("""<TH>DEL</TH>
							""")))} else {null} ),format.raw/*54.9*/("""
							
						"""),format.raw/*56.7*/("""</TR>
					</thead>
					<tbody>
						"""),_display_(/*59.8*/for(lista1 <- listVentas) yield /*59.33*/{_display_(Seq[Any](format.raw/*59.34*/("""
							"""),format.raw/*60.8*/("""<TR>
								<td  style="text-align:center;">"""),_display_(/*61.42*/lista1/*61.48*/.getId()),format.raw/*61.56*/("""</td>
								<td  style="text-align:center; min-width:80px;">
									<div style="display:none">"""),_display_(/*63.37*/utilities/*63.46*/.Fechas.AAMMDD(lista1.getFecha())),format.raw/*63.79*/("""</div>
									"""),_display_(/*64.11*/utilities/*64.20*/.Fechas.DDMMAA(lista1.getFecha())),format.raw/*64.53*/("""
								"""),format.raw/*65.9*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*66.42*/lista1/*66.48*/.getNomUserAdam()),format.raw/*66.65*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*67.40*/lista1/*67.46*/.getNomOperador()),format.raw/*67.63*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*68.40*/lista1/*68.46*/.getNameSucursal()),format.raw/*68.64*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*69.40*/lista1/*69.46*/.getNomBodegaEmpresa()),format.raw/*69.68*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*70.42*/lista1/*70.48*/.getNroCotiOdo()),format.raw/*70.64*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*71.42*/lista1/*71.48*/.getCodServicio()),format.raw/*71.65*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*72.40*/lista1/*72.46*/.getNomServicio()),format.raw/*72.63*/("""</td>
								<td  style="text-align:left;">
								
									"""),_display_(if(mapEquiposVigentes.get(lista1.getId_bodegaEmpresa())!=null)/*75.73*/{_display_(Seq[Any](format.raw/*75.74*/("""
										"""),format.raw/*76.11*/("""<select class="custom-select" onchange="modificaEquipo(value, '"""),_display_(/*76.75*/lista1/*76.81*/.getId()),format.raw/*76.89*/("""')">
											<option value=""""),_display_(/*77.28*/lista1/*77.34*/.getId_equipo()),format.raw/*77.49*/("""">"""),_display_(/*77.52*/lista1/*77.58*/.getNomEquipo()),format.raw/*77.73*/("""</option>
											"""),_display_(/*78.13*/for(listEquip <- mapEquiposVigentes.get(lista1.getId_bodegaEmpresa())) yield /*78.83*/{_display_(Seq[Any](format.raw/*78.84*/("""
												"""),format.raw/*79.13*/("""<option value=""""),_display_(/*79.29*/listEquip/*79.38*/.get(0)),format.raw/*79.45*/("""">"""),_display_(/*79.48*/listEquip/*79.57*/.get(2)),format.raw/*79.64*/("""</option>
											""")))}),format.raw/*80.13*/("""
											"""),format.raw/*81.12*/("""<option value="0"></option>
										</select>
									""")))} else {null} ),format.raw/*83.11*/("""
								
								"""),format.raw/*85.9*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*86.42*/lista1/*86.48*/.getUnidadServicio()),format.raw/*86.68*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*87.41*/lista1/*87.47*/.getCantidad()),format.raw/*87.61*/("""</td>
								<td  style="text-align:center;">
									"""),_display_(if(lista1.getReportPDF().equals("0"))/*89.48*/{_display_(Seq[Any](format.raw/*89.49*/("""
										"""),format.raw/*90.11*/("""--
									""")))}else/*91.15*/{_display_(Seq[Any](format.raw/*91.16*/("""
										"""),format.raw/*92.11*/("""<a href="#" onclick="mostrarPDF('"""),_display_(/*92.45*/lista1/*92.51*/.getReportPDF()),format.raw/*92.66*/("""')">
											<kbd style="background-color: #85C1E9">pdf</kbd>
										</a>
									""")))}),format.raw/*95.11*/("""
								"""),format.raw/*96.9*/("""</td>
								<td  style="text-align:center;">
									"""),_display_(if(lista1.getDocAnexo().equals("0"))/*98.47*/{_display_(Seq[Any](format.raw/*98.48*/("""
										"""),format.raw/*99.11*/("""<span class="btn btn-info btn-sm btn-file" style="font-size: 8px;">
											<form id="odoVentasGrabaDocAnexo" action="/odoVentasGrabaDocAnexo/" enctype="multipart/form-data" method="POST">
												<input type="hidden" name="id_ventaServicio" value=""""),_display_(/*101.66*/lista1/*101.72*/.getId()),format.raw/*101.80*/("""">
												<input type="hidden" name="desdeAAMMDD" value=""""),_display_(/*102.61*/desdeAAMMDD),format.raw/*102.72*/("""">
												<input type="hidden" name="hastaAAMMDD" value=""""),_display_(/*103.61*/hastaAAMMDD),format.raw/*103.72*/("""">
												<input type="hidden" name="docAnexo" value=""""),_display_(/*104.58*/lista1/*104.64*/.getDocAnexo()),format.raw/*104.78*/("""">
													<div id="txtBtn">adjuntar</div>
													<input type="file" multiple id="docAdjunto" name="docAdjunto[]" title="Cabiar el documento" onchange="LimitAttach(this.form, this.form.docAdjunto.value); $('#seModifico').val(1);">
											</form>
										</span>
									""")))}else/*109.15*/{_display_(Seq[Any](format.raw/*109.16*/("""
									
										"""),format.raw/*111.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*111.52*/lista1/*111.58*/.getDocAnexo()),format.raw/*111.72*/("""')">
											<kbd style="background-color: #7F8C8D">docum</kbd>
										</a>
										
										<span class="btn btn-info btn-sm btn-file" style="font-size: 8px; height:10px; width:10px">
											<form id="odoVentasGrabaDocAnexo" action="/odoVentasGrabaDocAnexo/" enctype="multipart/form-data" method="POST">
												<input type="hidden" name="id_ventaServicio" value=""""),_display_(/*117.66*/lista1/*117.72*/.getId()),format.raw/*117.80*/("""">
												<input type="hidden" name="desdeAAMMDD" value=""""),_display_(/*118.61*/desdeAAMMDD),format.raw/*118.72*/("""">
												<input type="hidden" name="hastaAAMMDD" value=""""),_display_(/*119.61*/hastaAAMMDD),format.raw/*119.72*/("""">
												<input type="hidden" name="docAnexo" value=""""),_display_(/*120.58*/lista1/*120.64*/.getDocAnexo()),format.raw/*120.78*/("""">
													<div id="txtBtn">c</div>
													<input type="file" multiple id="docAdjunto" name="docAdjunto[]" title="Cabiar el documento" onchange="LimitAttach(this.form, this.form.docAdjunto.value); $('#seModifico').val(1);">
											</form>
										</span>
									
									""")))}),format.raw/*126.11*/("""
								"""),format.raw/*127.9*/("""</td>
								
								"""),_display_(if(mapPermiso.get("parametro.album-fotos")!=null && mapPermiso.get("parametro.album-fotos").equals("1"))/*129.114*/{_display_(Seq[Any](format.raw/*129.115*/("""
									"""),format.raw/*130.10*/("""<td style="text-align:center;">
										"""),_display_(if(lista1.getAlbumFotos().equals("0"))/*131.50*/{_display_(Seq[Any](format.raw/*131.51*/("""
											"""),format.raw/*132.12*/("""<a href="#" onclick='$("#id_ventaServicioAlbum").val(""""),_display_(/*132.67*/lista1/*132.73*/.getId()),format.raw/*132.81*/(""""); $("#modalTitulo").text("ALBUM FOTOS ID: """),_display_(/*132.126*/lista1/*132.132*/.getId()),format.raw/*132.140*/(""""); $("#modalAlbum").modal("show");'>
												<kbd style="font-size: 8px; height:10px; width:10px; background-color: #17a2b8">crear</kbd>
											</a>
											
										""")))}else/*136.16*/{_display_(Seq[Any](format.raw/*136.17*/("""
											"""),format.raw/*137.12*/("""<a href="/muestraAlbumFotos/"""),_display_(/*137.41*/lista1/*137.47*/.getAlbumFotos()),format.raw/*137.63*/("""">
												<kbd style="background-color: #7F8C8D">album</kbd>
											</a>
											<a href="#" onclick='$("#id_ventaServicioAlbum").val(""""),_display_(/*140.67*/lista1/*140.73*/.getId()),format.raw/*140.81*/(""""); $("#modalTitulo").text("ALBUM FOTOS ID: """),_display_(/*140.126*/lista1/*140.132*/.getId()),format.raw/*140.140*/(""""); $("#modalAlbum").modal("show");'>
												<kbd style="font-size: 8px; height:10px; width:10px; background-color: #17a2b8">+</kbd>
											</a>
										""")))}),format.raw/*143.12*/("""
									"""),format.raw/*144.10*/("""</td>
								""")))}else/*145.14*/{_display_(Seq[Any](format.raw/*145.15*/("""
									"""),format.raw/*146.10*/("""<td style="text-align:center;">--</td>
								""")))}),format.raw/*147.10*/("""
								
								
								
								"""),format.raw/*151.9*/("""<td style="text-align:center;">
									<a href="/odoDetalleVenta/"""),_display_(/*152.37*/lista1/*152.43*/.getId()),format.raw/*152.51*/(""","""),_display_(/*152.53*/desdeAAMMDD),format.raw/*152.64*/(""","""),_display_(/*152.66*/hastaAAMMDD),format.raw/*152.77*/("""">
										<kbd style="background-color: #73C6B6">detalle</kbd>
									</a>
								</td>
								<td style="text-align:center;">
									<a href="/odoFirmaOperador/"""),_display_(/*157.38*/lista1/*157.44*/.getId()),format.raw/*157.52*/(""","""),_display_(/*157.54*/desdeAAMMDD),format.raw/*157.65*/(""","""),_display_(/*157.67*/hastaAAMMDD),format.raw/*157.78*/("""">
										<kbd style="background-color: #F0B27A">FIR</kbd>
									</a>
									<img src="data:image/jpeg;base64, """),_display_(/*160.45*/lista1/*160.51*/.getFirmaPDFoperador()),format.raw/*160.73*/("""" height="10px" />
								</td>
								<td style="text-align:center;">
									<a href="/odoFirmaAutorizador/"""),_display_(/*163.41*/lista1/*163.47*/.getId()),format.raw/*163.55*/(""","""),_display_(/*163.57*/desdeAAMMDD),format.raw/*163.68*/(""","""),_display_(/*163.70*/hastaAAMMDD),format.raw/*163.81*/("""">
										<kbd style="background-color: #F7DC6F">FIR</kbd>
									</a>
									<img src="data:image/jpeg;base64, """),_display_(/*166.45*/lista1/*166.51*/.getFirmaPDFautorizador()),format.raw/*166.76*/("""" height="10px" />
								</td>
								
								"""),_display_(if(mapPermiso.get("odoVentasEliminar")!=null)/*169.55*/ {_display_(Seq[Any](format.raw/*169.57*/("""
									"""),format.raw/*170.10*/("""<td  style="text-align:center;">
										<a href="#" onclick= "eliminarVentaServicio('"""),_display_(/*171.57*/lista1/*171.63*/.getId()),format.raw/*171.71*/("""')">
											<kbd style="background-color: red">X</kbd>
										</a>
									</td>
								""")))} else {null} ),format.raw/*175.10*/("""
								
							"""),format.raw/*177.8*/("""</TR>
			 			""")))}),format.raw/*178.9*/("""
					"""),format.raw/*179.6*/("""</tbody>
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
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
	
	"""),_display_(if(mapPermiso.get("parametro.album-fotos")!=null && mapPermiso.get("parametro.album-fotos").equals("1"))/*216.107*/{_display_(Seq[Any](format.raw/*216.108*/("""
	
		"""),format.raw/*218.3*/("""<div id='modalAlbum' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>"+
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
	
		
	""")))} else {null} ),format.raw/*264.3*/("""
	
	
	
	
	"""),format.raw/*269.2*/("""<form id="form_eliminar" method="post" action="/odoVentaServicioElimina/">
		<input type="hidden" id="form_id_ventaServicio" name="id_ventaServicio">
		<input type="hidden" name="fechaDesde" value=""""),_display_(/*271.50*/desdeAAMMDD),format.raw/*271.61*/("""">
		<input type="hidden" name="fechaHasta" value=""""),_display_(/*272.50*/hastaAAMMDD),format.raw/*272.61*/("""">
	</form>
	
	<form class="formulario" id="formDescargaDocumento" method="post" action="/downloadPDF/">	
		<input type="hidden" id="descargaDocumento" name="nombreArchivo">
	</form>
""")))}),format.raw/*278.2*/("""




"""),format.raw/*283.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*284.31*/("""{"""),format.raw/*284.32*/("""
		  """),format.raw/*285.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*285.36*/("""{"""),format.raw/*285.37*/("""
		    	"""),format.raw/*286.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
				"order": [[ 1, "desc" ],[ 0, "desc" ]],
		    	"language": """),format.raw/*289.20*/("""{"""),format.raw/*289.21*/("""
		        	"""),format.raw/*290.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*291.11*/("""}"""),format.raw/*291.12*/("""
		  """),format.raw/*292.5*/("""}"""),format.raw/*292.6*/(""" """),format.raw/*292.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*294.2*/("""}"""),format.raw/*294.3*/(""");
	
	const mostrarPDF = (nombrePDF) => """),format.raw/*296.36*/("""{"""),format.raw/*296.37*/("""
		  """),format.raw/*297.5*/("""document.getElementById('rutaPDF').innerHTML="<object data='/showPdf/"+nombrePDF+"' type='application/pdf' width='100%' height='720'></object>";
		  $('#muestraPDF').modal('show');
	"""),format.raw/*299.2*/("""}"""),format.raw/*299.3*/("""
	
	"""),format.raw/*301.2*/("""const eliminarVentaServicio = (id_ventaServicio) => """),format.raw/*301.54*/("""{"""),format.raw/*301.55*/("""
		"""),format.raw/*302.3*/("""alertify.confirm("Esta seguro de eliminar este report ID: "+id_ventaServicio, function (e) """),format.raw/*302.94*/("""{"""),format.raw/*302.95*/("""
			"""),format.raw/*303.4*/("""if (e) """),format.raw/*303.11*/("""{"""),format.raw/*303.12*/("""
				"""),format.raw/*304.5*/("""$("#form_id_ventaServicio").val(id_ventaServicio);
				document.getElementById("form_eliminar").submit();
			"""),format.raw/*306.4*/("""}"""),format.raw/*306.5*/("""
		"""),format.raw/*307.3*/("""}"""),format.raw/*307.4*/(""");
	"""),format.raw/*308.2*/("""}"""),format.raw/*308.3*/("""
	
	"""),format.raw/*310.2*/("""const modificaEquipo = (id_equipo, id_ventaServicio) => """),format.raw/*310.58*/("""{"""),format.raw/*310.59*/("""
		"""),format.raw/*311.3*/("""var formData = new FormData();
		formData.append("id_equipo",id_equipo);
		formData.append("id_ventaServicio",id_ventaServicio);
		$.ajax("""),format.raw/*314.10*/("""{"""),format.raw/*314.11*/("""
            """),format.raw/*315.13*/("""url: "/odoCambiaEquipoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(rs)"""),format.raw/*322.29*/("""{"""),format.raw/*322.30*/("""
	     	"""),format.raw/*323.8*/("""}"""),format.raw/*323.9*/("""
	  	"""),format.raw/*324.5*/("""}"""),format.raw/*324.6*/(""");
	"""),format.raw/*325.2*/("""}"""),format.raw/*325.3*/("""
	
	"""),format.raw/*327.2*/("""const descargaDocumento = (nombreDOC) => """),format.raw/*327.43*/("""{"""),format.raw/*327.44*/("""
		  """),format.raw/*328.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*330.2*/("""}"""),format.raw/*330.3*/("""
	
	"""),format.raw/*332.2*/("""let extArray = new Array(".gif", ".jpg", ".png", ".jpeg", ".pdf", ".xls", ".doc", ".xlsx", ".docx", ".tar", ".zip", ".rar");
	const LimitAttach = (form, file) => """),format.raw/*333.38*/("""{"""),format.raw/*333.39*/("""
		
		"""),format.raw/*335.3*/("""if (!file) return;
		
		const $inputArchivos = document.querySelector("#docAdjunto");
		const archivosParaSubir = $inputArchivos.files;
		let tamanio = 0;
		let allowSubmit = false;
		
		for(let i=0; i<archivosParaSubir.length; i++)"""),format.raw/*342.48*/("""{"""),format.raw/*342.49*/("""
			"""),format.raw/*343.4*/("""tamanio += archivosParaSubir[i].size;
			let nombre = archivosParaSubir[i].name;
			let extencion = nombre.substring(nombre.lastIndexOf(".")).toLowerCase();
			for (let j = 0; j < extArray.length; j++) """),format.raw/*346.46*/("""{"""),format.raw/*346.47*/("""
				"""),format.raw/*347.5*/("""if (extArray[j] == extencion) """),format.raw/*347.35*/("""{"""),format.raw/*347.36*/(""" 
					"""),format.raw/*348.6*/("""allowSubmit = true;
				"""),format.raw/*349.5*/("""}"""),format.raw/*349.6*/("""
			"""),format.raw/*350.4*/("""}"""),format.raw/*350.5*/("""
			"""),format.raw/*351.4*/("""if(allowSubmit && i < archivosParaSubir.length -1)"""),format.raw/*351.54*/("""{"""),format.raw/*351.55*/("""
				"""),format.raw/*352.5*/("""allowSubmit = false;
			"""),format.raw/*353.4*/("""}"""),format.raw/*353.5*/("""
		"""),format.raw/*354.3*/("""}"""),format.raw/*354.4*/("""
		
		"""),format.raw/*356.3*/("""if (allowSubmit) """),format.raw/*356.20*/("""{"""),format.raw/*356.21*/("""
			"""),format.raw/*357.4*/("""if(tamanio > 200000000)"""),format.raw/*357.27*/("""{"""),format.raw/*357.28*/("""
				"""),format.raw/*358.5*/("""alert("Se permite máximo subir 200 MB,"
				+" lo que se intenta subir pesa: "+Math.round(tamanio/10000)/100+" MB");
				form.docAdjunto.value="";
				return;
			"""),format.raw/*362.4*/("""}"""),format.raw/*362.5*/("""
		"""),format.raw/*363.3*/("""}"""),format.raw/*363.4*/("""else"""),format.raw/*363.8*/("""{"""),format.raw/*363.9*/("""
			"""),format.raw/*364.4*/("""alert("Se permiten únicamente archivos con la extención: " 
			+ (extArray.join("  ")) + "\nPor favor, seleccione solo archivos con extenciones permitidas "
			+ "e intente de nuevo.");
			form.docAdjunto.value="";
			return;
		"""),format.raw/*369.3*/("""}"""),format.raw/*369.4*/("""
		"""),format.raw/*370.3*/("""document.getElementById('enCarga').style.display="block";
		$("#odoVentasGrabaDocAnexo").submit();
	"""),format.raw/*372.2*/("""}"""),format.raw/*372.3*/("""
	
"""),format.raw/*374.1*/("""</script>


<script type="text/javascript">

	

	const $imagen = document.querySelector("#imagen");
	
	const cambiarTexto = (nodo) =>"""),format.raw/*383.32*/("""{"""),format.raw/*383.33*/("""
		"""),format.raw/*384.3*/("""$("#textBtnComprimir").text(nodo.files.length + " imagenes seleccionadas");
	"""),format.raw/*385.2*/("""}"""),format.raw/*385.3*/("""
	
	"""),format.raw/*387.2*/("""document.querySelector("#btnComprimirBlob").addEventListener("click", async () => """),format.raw/*387.84*/("""{"""),format.raw/*387.85*/("""
		"""),format.raw/*388.3*/("""if ($imagen.files.length <= 0) """),format.raw/*388.34*/("""{"""),format.raw/*388.35*/("""
			"""),format.raw/*389.4*/("""return;
		"""),format.raw/*390.3*/("""}"""),format.raw/*390.4*/("""
		"""),format.raw/*391.3*/("""document.getElementById('enCarga').style.display="block";
		let sigla = $("#textSigla").val();
		if(sigla.length > 0)"""),format.raw/*393.23*/("""{"""),format.raw/*393.24*/("""
			"""),format.raw/*394.4*/("""sigla = sigla+"_";
		"""),format.raw/*395.3*/("""}"""),format.raw/*395.4*/("""
		"""),format.raw/*396.3*/("""subirArchivo2($imagen, contador, sigla);
	"""),format.raw/*397.2*/("""}"""),format.raw/*397.3*/(""");
	
	let contador = 0;

		
		const MAX_WIDTH = 1000;
		const MAX_HEIGHT = 750;
		const MIME_TYPE = "image/jpeg";
		const QUALITY = 0.7;

		const input = document.getElementById("img-input");
		
		function subirArchivo2($imagen, contador, sigla) """),format.raw/*409.52*/("""{"""),format.raw/*409.53*/("""
			"""),format.raw/*410.4*/("""const file = $imagen.files[contador]; 
			  const blobURL = URL.createObjectURL(file);
			  const img = new Image();
			  img.src = blobURL;
			  img.onerror = function () """),format.raw/*414.32*/("""{"""),format.raw/*414.33*/("""
			    """),format.raw/*415.8*/("""URL.revokeObjectURL(this.src);
			  """),format.raw/*416.6*/("""}"""),format.raw/*416.7*/(""";
			  
			  
			  img.onload = function () """),format.raw/*419.31*/("""{"""),format.raw/*419.32*/("""
			    """),format.raw/*420.8*/("""URL.revokeObjectURL(this.src);
			    const [newWidth, newHeight] = calculateSize(img, MAX_WIDTH, MAX_HEIGHT);
			    const canvas = document.createElement("canvas");
			    canvas.width = newWidth;
			    canvas.height = newHeight;
			    const ctx = canvas.getContext("2d");
			    ctx.drawImage(img, 0, 0, newWidth, newHeight);
			    canvas.toBlob(
			      (blob) => """),format.raw/*428.20*/("""{"""),format.raw/*428.21*/("""
			        """),format.raw/*429.12*/("""displayInfo('Original file', file);
			        displayInfo('Compressed file', blob);
			      
			        const myFile = new File([blob], sigla + $imagen.files[contador].name, """),format.raw/*432.82*/("""{"""),format.raw/*432.83*/("""
					    """),format.raw/*433.10*/("""type: blob.type,
					"""),format.raw/*434.6*/("""}"""),format.raw/*434.7*/(""");
					
					$("#msgEspera").text(" cargando imagen "+(contador+1)+" de "+$imagen.files.length);
					$("#msgEspera2").text(" En proceso..... cargando imagen "+(contador+1)+" de "+$imagen.files.length);
					
					let id_ventaServicioAlbum = $("#id_ventaServicioAlbum").val();
					
					var formData = new FormData();
					formData.append("fotosAdjunto[0]",myFile);
					formData.append("id_ventaServicio",id_ventaServicioAlbum);
					formData.append("iniCarpeta",'Fotos_Report_');
						$.ajax("""),format.raw/*445.14*/("""{"""),format.raw/*445.15*/("""
							"""),format.raw/*446.8*/("""async: false,
					        url: "/odoGrabaAlbumFotosAjax/",
					        type: "POST",
					        method: "POST",
					        data: formData,
					        cache: false,
					        contentType: false,
					     	processData: false,
					     	success: function(rs)"""),format.raw/*454.33*/("""{"""),format.raw/*454.34*/("""
					     		"""),format.raw/*455.13*/("""if(rs == "error")"""),format.raw/*455.30*/("""{"""),format.raw/*455.31*/("""
					     			"""),format.raw/*456.14*/("""alertify.alert('Se presento un error, no fueron subidas las imagenes', function () """),format.raw/*456.97*/("""{"""),format.raw/*456.98*/("""
					     				"""),format.raw/*457.15*/("""document.getElementById("imagen").value = "";
					     			"""),format.raw/*458.14*/("""}"""),format.raw/*458.15*/(""");
					     		"""),format.raw/*459.13*/("""}"""),format.raw/*459.14*/("""else"""),format.raw/*459.18*/("""{"""),format.raw/*459.19*/("""
					     			"""),format.raw/*460.14*/("""contador++;
					     			if(contador < $imagen.files.length)"""),format.raw/*461.49*/("""{"""),format.raw/*461.50*/("""
					     				"""),format.raw/*462.15*/("""subirArchivo2($imagen, contador, sigla);
					     			"""),format.raw/*463.14*/("""}"""),format.raw/*463.15*/("""else"""),format.raw/*463.19*/("""{"""),format.raw/*463.20*/("""
					     				"""),format.raw/*464.15*/("""alertify.alert('Subidas las imagenes con exito', function () """),format.raw/*464.76*/("""{"""),format.raw/*464.77*/("""
							     			"""),format.raw/*465.16*/("""document.getElementById("imagen").value = "";
							     			$("#textBtnComprimir").text("Ninguna imagen seleccionada");
							     		"""),format.raw/*467.15*/("""}"""),format.raw/*467.16*/(""");
										document.getElementById('enCarga').style.display="none";
										location.reload();
					     			"""),format.raw/*470.14*/("""}"""),format.raw/*470.15*/("""
					     		"""),format.raw/*471.13*/("""}"""),format.raw/*471.14*/("""
					     	"""),format.raw/*472.12*/("""}"""),format.raw/*472.13*/("""
					    """),format.raw/*473.10*/("""}"""),format.raw/*473.11*/(""");
			      """),format.raw/*474.10*/("""}"""),format.raw/*474.11*/(""",
			      MIME_TYPE,
			      QUALITY
			    );
			  """),format.raw/*478.6*/("""}"""),format.raw/*478.7*/(""";
		"""),format.raw/*479.3*/("""}"""),format.raw/*479.4*/("""

		"""),format.raw/*481.3*/("""function calculateSize(img, maxWidth, maxHeight) """),format.raw/*481.52*/("""{"""),format.raw/*481.53*/("""
		  """),format.raw/*482.5*/("""let width = img.width;
		  let height = img.height;

		  if (width > height) """),format.raw/*485.25*/("""{"""),format.raw/*485.26*/("""
		    """),format.raw/*486.7*/("""if (width > maxWidth) """),format.raw/*486.29*/("""{"""),format.raw/*486.30*/("""
		      """),format.raw/*487.9*/("""height = Math.round((height * maxWidth) / width);
		      width = maxWidth;
		    """),format.raw/*489.7*/("""}"""),format.raw/*489.8*/("""
		  """),format.raw/*490.5*/("""}"""),format.raw/*490.6*/(""" """),format.raw/*490.7*/("""else """),format.raw/*490.12*/("""{"""),format.raw/*490.13*/("""
		    """),format.raw/*491.7*/("""if (height > maxHeight) """),format.raw/*491.31*/("""{"""),format.raw/*491.32*/("""
		      """),format.raw/*492.9*/("""width = Math.round((width * maxHeight) / height);
		      height = maxHeight;
		    """),format.raw/*494.7*/("""}"""),format.raw/*494.8*/("""
		  """),format.raw/*495.5*/("""}"""),format.raw/*495.6*/("""
		  """),format.raw/*496.5*/("""return [width, height];
		"""),format.raw/*497.3*/("""}"""),format.raw/*497.4*/("""

		"""),format.raw/*499.3*/("""function displayInfo(label, file) """),format.raw/*499.37*/("""{"""),format.raw/*499.38*/("""
		  """),format.raw/*500.5*/("""const p = document.createElement('p');
		  p.innerText = `$"""),format.raw/*501.21*/("""{"""),format.raw/*501.22*/("""label"""),format.raw/*501.27*/("""}"""),format.raw/*501.28*/(""" """),format.raw/*501.29*/("""- $"""),format.raw/*501.32*/("""{"""),format.raw/*501.33*/("""readableBytes(file.size)"""),format.raw/*501.57*/("""}"""),format.raw/*501.58*/("""`;
		"""),format.raw/*502.3*/("""}"""),format.raw/*502.4*/("""

		"""),format.raw/*504.3*/("""function readableBytes(bytes) """),format.raw/*504.33*/("""{"""),format.raw/*504.34*/("""
		  """),format.raw/*505.5*/("""const i = Math.floor(Math.log(bytes) / Math.log(1024)),
		    sizes = ['B', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'];

		  return (bytes / Math.pow(1024, i)).toFixed(2) + ' ' + sizes[i];
		"""),format.raw/*509.3*/("""}"""),format.raw/*509.4*/("""
"""),format.raw/*510.1*/("""</script>
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listVentas:List[tables.VentaServicio],mapEquiposVigentes:Map[Long, List[List[String]]],desdeAAMMDD:String,hastaAAMMDD:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listVentas,mapEquiposVigentes,desdeAAMMDD,hastaAAMMDD)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.VentaServicio],Map[Long, List[List[String]]],String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listVentas,mapEquiposVigentes,desdeAAMMDD,hastaAAMMDD) => apply(mapDiccionario,mapPermiso,userMnu,listVentas,mapEquiposVigentes,desdeAAMMDD,hastaAAMMDD)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuOdo/odoListarVentas1.scala.html
                  HASH: 331b9ff055d657ebb19747fb5a8d9b4dbe599e93
                  MATRIX: 1082->1|1405->231|1433->234|1449->242|1488->244|1517->248|1585->296|1615->300|1916->574|1948->585|2027->637|2059->648|2153->716|2244->786|2274->789|2919->1407|2942->1421|2977->1435|3367->1798|3407->1800|3443->1809|3507->1830|3549->1845|3615->1885|3656->1910|3695->1911|3730->1919|3803->1965|3818->1971|3847->1979|3973->2078|3991->2087|4045->2120|4089->2137|4107->2146|4161->2179|4197->2188|4271->2235|4286->2241|4324->2258|4396->2303|4411->2309|4449->2326|4521->2371|4536->2377|4575->2395|4647->2440|4662->2446|4705->2468|4779->2515|4794->2521|4831->2537|4905->2584|4920->2590|4958->2607|5030->2652|5045->2658|5083->2675|5236->2801|5275->2802|5314->2813|5405->2877|5420->2883|5449->2891|5508->2923|5523->2929|5559->2944|5589->2947|5604->2953|5640->2968|5689->2990|5775->3060|5814->3061|5855->3074|5898->3090|5916->3099|5944->3106|5974->3109|5992->3118|6020->3125|6073->3147|6113->3159|6215->3217|6260->3235|6334->3282|6349->3288|6390->3308|6463->3354|6478->3360|6513->3374|6634->3468|6673->3469|6712->3480|6748->3497|6787->3498|6826->3509|6887->3543|6902->3549|6938->3564|7059->3654|7095->3663|7215->3756|7254->3757|7293->3768|7578->4025|7594->4031|7624->4039|7715->4102|7748->4113|7839->4176|7872->4187|7960->4247|7976->4253|8012->4267|8325->4560|8365->4561|8415->4582|8484->4623|8500->4629|8536->4643|8948->5027|8964->5033|8994->5041|9085->5104|9118->5115|9209->5178|9242->5189|9330->5249|9346->5255|9382->5269|9706->5561|9743->5570|9900->5698|9941->5699|9980->5709|10089->5790|10129->5791|10170->5803|10253->5858|10269->5864|10299->5872|10373->5917|10390->5923|10421->5931|10626->6116|10666->6117|10707->6129|10764->6158|10780->6164|10818->6180|10994->6328|11010->6334|11040->6342|11114->6387|11131->6393|11162->6401|11359->6566|11398->6576|11437->6595|11477->6596|11516->6606|11596->6654|11660->6690|11756->6758|11772->6764|11802->6772|11832->6774|11865->6785|11895->6787|11928->6798|12127->6969|12143->6975|12173->6983|12203->6985|12236->6996|12266->6998|12299->7009|12447->7129|12463->7135|12507->7157|12648->7270|12664->7276|12694->7284|12724->7286|12757->7297|12787->7299|12820->7310|12968->7430|12984->7436|13031->7461|13155->7557|13196->7559|13235->7569|13352->7658|13368->7664|13398->7672|13541->7770|13586->7787|13631->7801|13665->7807|14989->9102|15030->9103|15063->9108|16776->10777|16814->10787|17041->10986|17074->10997|17154->11049|17187->11060|17402->11244|17435->11249|17526->11311|17556->11312|17589->11317|17649->11348|17679->11349|17715->11357|17902->11515|17932->11516|17973->11528|18080->11606|18110->11607|18143->11612|18172->11613|18201->11614|18302->11687|18331->11688|18400->11728|18430->11729|18463->11734|18673->11916|18702->11917|18734->11921|18815->11973|18845->11974|18876->11977|18996->12068|19026->12069|19058->12073|19094->12080|19124->12081|19157->12086|19294->12195|19323->12196|19354->12199|19383->12200|19415->12204|19444->12205|19476->12209|19561->12265|19591->12266|19622->12269|19789->12407|19819->12408|19861->12421|20115->12646|20145->12647|20181->12655|20210->12656|20243->12661|20272->12662|20304->12666|20333->12667|20365->12671|20435->12712|20465->12713|20498->12718|20630->12822|20659->12823|20691->12827|20882->12989|20912->12990|20946->12996|21207->13228|21237->13229|21269->13233|21500->13435|21530->13436|21563->13441|21622->13471|21652->13472|21687->13479|21739->13503|21768->13504|21800->13508|21829->13509|21861->13513|21940->13563|21970->13564|22003->13569|22055->13593|22084->13594|22115->13597|22144->13598|22178->13604|22224->13621|22254->13622|22286->13626|22338->13649|22368->13650|22401->13655|22591->13817|22620->13818|22651->13821|22680->13822|22712->13826|22741->13827|22773->13831|23029->14059|23058->14060|23089->14063|23217->14163|23246->14164|23277->14167|23439->14300|23469->14301|23500->14304|23605->14381|23634->14382|23666->14386|23777->14468|23807->14469|23838->14472|23898->14503|23928->14504|23960->14508|23998->14518|24027->14519|24058->14522|24204->14639|24234->14640|24266->14644|24315->14665|24344->14666|24375->14669|24445->14711|24474->14712|24749->14958|24779->14959|24811->14963|25012->15135|25042->15136|25078->15144|25142->15180|25171->15181|25244->15225|25274->15226|25310->15234|25711->15606|25741->15607|25782->15619|25987->15795|26017->15796|26056->15806|26106->15828|26135->15829|26661->16326|26691->16327|26727->16335|27024->16603|27054->16604|27096->16617|27142->16634|27172->16635|27215->16649|27327->16732|27357->16733|27401->16748|27489->16807|27519->16808|27563->16823|27593->16824|27626->16828|27656->16829|27699->16843|27788->16903|27818->16904|27862->16919|27945->16973|27975->16974|28008->16978|28038->16979|28082->16994|28172->17055|28202->17056|28247->17072|28411->17207|28441->17208|28582->17320|28612->17321|28654->17334|28684->17335|28725->17347|28755->17348|28794->17358|28824->17359|28865->17371|28895->17372|28977->17426|29006->17427|29038->17431|29067->17432|29099->17436|29177->17485|29207->17486|29240->17491|29346->17568|29376->17569|29411->17576|29462->17598|29492->17599|29529->17608|29639->17690|29668->17691|29701->17696|29730->17697|29759->17698|29793->17703|29823->17704|29858->17711|29911->17735|29941->17736|29978->17745|30090->17829|30119->17830|30152->17835|30181->17836|30214->17841|30268->17867|30297->17868|30329->17872|30392->17906|30422->17907|30455->17912|30543->17971|30573->17972|30607->17977|30637->17978|30667->17979|30699->17982|30729->17983|30782->18007|30812->18008|30845->18013|30874->18014|30906->18018|30965->18048|30995->18049|31028->18054|31252->18250|31281->18251|31310->18252
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|43->12|43->12|44->13|44->13|49->18|49->18|50->19|67->36|67->36|67->36|83->52|83->52|84->53|85->54|87->56|90->59|90->59|90->59|91->60|92->61|92->61|92->61|94->63|94->63|94->63|95->64|95->64|95->64|96->65|97->66|97->66|97->66|98->67|98->67|98->67|99->68|99->68|99->68|100->69|100->69|100->69|101->70|101->70|101->70|102->71|102->71|102->71|103->72|103->72|103->72|106->75|106->75|107->76|107->76|107->76|107->76|108->77|108->77|108->77|108->77|108->77|108->77|109->78|109->78|109->78|110->79|110->79|110->79|110->79|110->79|110->79|110->79|111->80|112->81|114->83|116->85|117->86|117->86|117->86|118->87|118->87|118->87|120->89|120->89|121->90|122->91|122->91|123->92|123->92|123->92|123->92|126->95|127->96|129->98|129->98|130->99|132->101|132->101|132->101|133->102|133->102|134->103|134->103|135->104|135->104|135->104|140->109|140->109|142->111|142->111|142->111|142->111|148->117|148->117|148->117|149->118|149->118|150->119|150->119|151->120|151->120|151->120|157->126|158->127|160->129|160->129|161->130|162->131|162->131|163->132|163->132|163->132|163->132|163->132|163->132|163->132|167->136|167->136|168->137|168->137|168->137|168->137|171->140|171->140|171->140|171->140|171->140|171->140|174->143|175->144|176->145|176->145|177->146|178->147|182->151|183->152|183->152|183->152|183->152|183->152|183->152|183->152|188->157|188->157|188->157|188->157|188->157|188->157|188->157|191->160|191->160|191->160|194->163|194->163|194->163|194->163|194->163|194->163|194->163|197->166|197->166|197->166|200->169|200->169|201->170|202->171|202->171|202->171|206->175|208->177|209->178|210->179|247->216|247->216|249->218|295->264|300->269|302->271|302->271|303->272|303->272|309->278|314->283|315->284|315->284|316->285|316->285|316->285|317->286|320->289|320->289|321->290|322->291|322->291|323->292|323->292|323->292|325->294|325->294|327->296|327->296|328->297|330->299|330->299|332->301|332->301|332->301|333->302|333->302|333->302|334->303|334->303|334->303|335->304|337->306|337->306|338->307|338->307|339->308|339->308|341->310|341->310|341->310|342->311|345->314|345->314|346->315|353->322|353->322|354->323|354->323|355->324|355->324|356->325|356->325|358->327|358->327|358->327|359->328|361->330|361->330|363->332|364->333|364->333|366->335|373->342|373->342|374->343|377->346|377->346|378->347|378->347|378->347|379->348|380->349|380->349|381->350|381->350|382->351|382->351|382->351|383->352|384->353|384->353|385->354|385->354|387->356|387->356|387->356|388->357|388->357|388->357|389->358|393->362|393->362|394->363|394->363|394->363|394->363|395->364|400->369|400->369|401->370|403->372|403->372|405->374|414->383|414->383|415->384|416->385|416->385|418->387|418->387|418->387|419->388|419->388|419->388|420->389|421->390|421->390|422->391|424->393|424->393|425->394|426->395|426->395|427->396|428->397|428->397|440->409|440->409|441->410|445->414|445->414|446->415|447->416|447->416|450->419|450->419|451->420|459->428|459->428|460->429|463->432|463->432|464->433|465->434|465->434|476->445|476->445|477->446|485->454|485->454|486->455|486->455|486->455|487->456|487->456|487->456|488->457|489->458|489->458|490->459|490->459|490->459|490->459|491->460|492->461|492->461|493->462|494->463|494->463|494->463|494->463|495->464|495->464|495->464|496->465|498->467|498->467|501->470|501->470|502->471|502->471|503->472|503->472|504->473|504->473|505->474|505->474|509->478|509->478|510->479|510->479|512->481|512->481|512->481|513->482|516->485|516->485|517->486|517->486|517->486|518->487|520->489|520->489|521->490|521->490|521->490|521->490|521->490|522->491|522->491|522->491|523->492|525->494|525->494|526->495|526->495|527->496|528->497|528->497|530->499|530->499|530->499|531->500|532->501|532->501|532->501|532->501|532->501|532->501|532->501|532->501|532->501|533->502|533->502|535->504|535->504|535->504|536->505|540->509|540->509|541->510
                  -- GENERATED --
              */
          