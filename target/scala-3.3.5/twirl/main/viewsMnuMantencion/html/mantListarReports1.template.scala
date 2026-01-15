
package viewsMnuMantencion.html

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

object mantListarReports1 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template7[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String,Map[String,String],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listado: List[List[String]], desdeAAMMDD: String, hastaAAMMDD: String, mapDelete: Map[String,String]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""

"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),format.raw/*9.2*/("""<div id="enCarga" class="blocker" style="display: none;"><br><br><br><br><br><br><h1>En proceso.....<div id="msgEspera"></div></h1></div>
	
	<form id="excel" class="formulario" method="post" action="/routes3/mantListarReports1Excel/">
		<input type="hidden" name="fechaDesde" value=""""),_display_(/*12.50*/desdeAAMMDD),format.raw/*12.61*/("""">
		<input type="hidden" name="fechaHasta" value=""""),_display_(/*13.50*/hastaAAMMDD),format.raw/*13.61*/("""">
	</form>
	
	
	<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*18.4*/barraTitulo(mapDiccionario, "LISTA DE REPORT INGRESADOS", "")),format.raw/*18.65*/("""
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
							<TH>PERFIL</TH>
							<TH>OPERADOR<br>MECANICO</TH>
							<TH>TIPO<br>MANTENCION</TH>
							<TH>CODIGO</TH>
							<TH>EQUIPO</TH>
							<TH>REPORT</TH>
							<TH>DETALLE</TH>
							<TH>ANEXO</TH>
							<TH>FIRMA<br>OPER/MEC</TH>
							<TH>FIRMA<br>SUPERVISOR</TH>
							"""),_display_(if(mapPermiso.get("mantEliminaReportMada")!=null)/*44.58*/ {_display_(Seq[Any](format.raw/*44.60*/("""
								"""),format.raw/*45.9*/("""<TH>DEL</TH>
							""")))} else {null} ),format.raw/*46.9*/("""
							
						"""),format.raw/*48.7*/("""</TR>
					</thead>
					<tbody>
						"""),_display_(/*51.8*/for(lista1 <- listado) yield /*51.30*/{_display_(Seq[Any](format.raw/*51.31*/("""
							"""),format.raw/*52.8*/("""<TR>
								<td  style="text-align:center;">"""),_display_(/*53.42*/lista1/*53.48*/.get(0)),format.raw/*53.55*/("""</td>
								<td  style="text-align:center; min-width:80px;">
									<div style="display:none">"""),_display_(/*55.37*/utilities/*55.46*/.Fechas.AAMMDD(lista1.get(1))),format.raw/*55.75*/("""</div>
									"""),_display_(/*56.11*/utilities/*56.20*/.Fechas.DDMMAA(lista1.get(1))),format.raw/*56.49*/("""
								"""),format.raw/*57.9*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*58.42*/lista1/*58.48*/.get(2)),format.raw/*58.55*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*59.40*/lista1/*59.46*/.get(3)),format.raw/*59.53*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*60.40*/lista1/*60.46*/.get(4)),format.raw/*60.53*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*61.40*/lista1/*61.46*/.get(5).toUpperCase()),format.raw/*61.67*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*62.40*/lista1/*62.46*/.get(6)),format.raw/*62.53*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*63.40*/lista1/*63.46*/.get(7)),format.raw/*63.53*/("""</td>
								<td  style="text-align:center;">
									"""),_display_(if(lista1.get(8).equals("0"))/*65.40*/{_display_(Seq[Any](format.raw/*65.41*/("""
										"""),format.raw/*66.11*/("""--
									""")))}else/*67.15*/{_display_(Seq[Any](format.raw/*67.16*/("""
										"""),format.raw/*68.11*/("""<a href="#" onclick="mostrarPDF('"""),_display_(/*68.45*/lista1/*68.51*/.get(8)),format.raw/*68.58*/("""')">
											<kbd style="background-color: #85C1E9">pdf</kbd>
										</a>
									""")))}),format.raw/*71.11*/("""
								"""),format.raw/*72.9*/("""</td>
								<td style="text-align:center;">
									<form id="mantReportDetalle_"""),_display_(/*74.39*/lista1/*74.45*/.get(0)),format.raw/*74.52*/("""" action="/routes3/mantReportDetalle/" method="POST">
										<input type="hidden" name="id_mantTransacReport" value=""""),_display_(/*75.68*/lista1/*75.74*/.get(0)),format.raw/*75.81*/("""">
										<input type="hidden" name="desdeAAMMDD" value=""""),_display_(/*76.59*/desdeAAMMDD),format.raw/*76.70*/("""">
										<input type="hidden" name="hastaAAMMDD" value=""""),_display_(/*77.59*/hastaAAMMDD),format.raw/*77.70*/("""">
									</form>
									<a href="#" onclick='$("#mantReportDetalle_"""),_display_(/*79.54*/lista1/*79.60*/.get(0)),format.raw/*79.67*/("""").submit();'>
										<kbd style="background-color: #73C6B6">detalle</kbd>
									</a>
								</td>
								<td  style="text-align:center;">
									"""),_display_(if(lista1.get(9).equals("0"))/*84.40*/{_display_(Seq[Any](format.raw/*84.41*/("""
										"""),format.raw/*85.11*/("""<span class="btn btn-info btn-sm btn-file" style="font-size: 8px;">
											<form id="mantReportGrabaDocAnexo_"""),_display_(/*86.47*/lista1/*86.53*/.get(0)),format.raw/*86.60*/("""" action="/routes3/mantReportGrabaDocAnexo/" enctype="multipart/form-data" method="POST">
												<input type="hidden" name="id_mantTransacReport" value=""""),_display_(/*87.70*/lista1/*87.76*/.get(0)),format.raw/*87.83*/("""">
												<input type="hidden" name="desdeAAMMDD" value=""""),_display_(/*88.61*/desdeAAMMDD),format.raw/*88.72*/("""">
												<input type="hidden" name="hastaAAMMDD" value=""""),_display_(/*89.61*/hastaAAMMDD),format.raw/*89.72*/("""">
												<input type="hidden" name="docAnexo" value=""""),_display_(/*90.58*/lista1/*90.64*/.get(9)),format.raw/*90.71*/("""">
													<div id="txtBtn">adjuntar</div>
													<input type="file" multiple id="docAdjunto" name="docAdjunto[]" 
													onchange="LimitAttach(this.form, this.form.docAdjunto.value, '"""),_display_(/*93.77*/lista1/*93.83*/.get(0)),format.raw/*93.90*/("""');">
											</form>
										</span>
									""")))}else/*96.15*/{_display_(Seq[Any](format.raw/*96.16*/("""
										"""),format.raw/*97.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*97.52*/lista1/*97.58*/.get(9)),format.raw/*97.65*/("""')">
											<kbd style="background-color: #7F8C8D">docum</kbd>
										</a>
									""")))}),format.raw/*100.11*/("""
								"""),format.raw/*101.9*/("""</td>
								<td style="text-align:center;">
									<a href="/routes3/mantFirmaOperador/"""),_display_(/*103.47*/lista1/*103.53*/.get(0)),format.raw/*103.60*/(""","""),_display_(/*103.62*/desdeAAMMDD),format.raw/*103.73*/(""","""),_display_(/*103.75*/hastaAAMMDD),format.raw/*103.86*/("""">
										<kbd style="background-color: #F0B27A">FIR</kbd>
									</a>
									<img src="data:image/jpeg;base64, """),_display_(/*106.45*/lista1/*106.51*/.get(10)),format.raw/*106.59*/("""" height="10px" />
								</td>
								<td style="text-align:center;">
									<a href="/routes3/mantFirmaAutorizador/"""),_display_(/*109.50*/lista1/*109.56*/.get(0)),format.raw/*109.63*/(""","""),_display_(/*109.65*/desdeAAMMDD),format.raw/*109.76*/(""","""),_display_(/*109.78*/hastaAAMMDD),format.raw/*109.89*/("""">
										<kbd style="background-color: #F7DC6F">FIR</kbd>
									</a>
									<img src="data:image/jpeg;base64, """),_display_(/*112.45*/lista1/*112.51*/.get(11)),format.raw/*112.59*/("""" height="10px" />
								</td>
								
								"""),_display_(if(mapPermiso.get("mantEliminaReportMada")!=null)/*115.59*/ {_display_(Seq[Any](format.raw/*115.61*/("""
									"""),format.raw/*116.10*/("""<td  style="text-align:center;">
										"""),_display_(if(mapDelete.get(lista1.get(0))!=null)/*117.50*/ {_display_(Seq[Any](format.raw/*117.52*/("""
											"""),format.raw/*118.12*/("""<a href="#" onclick= "eliminarMantReport('"""),_display_(/*118.55*/lista1/*118.61*/.get(0)),format.raw/*118.68*/("""')">
												<kbd style="background-color: red">X</kbd>
											</a>
										""")))} else {null} ),format.raw/*121.12*/("""
									"""),format.raw/*122.10*/("""</td>
								""")))} else {null} ),format.raw/*123.10*/("""
								
							"""),format.raw/*125.8*/("""</TR>
			 			""")))}),format.raw/*126.9*/("""
					"""),format.raw/*127.6*/("""</tbody>
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
					<h5 class='modal-title'>REPORT</h5>
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
	
	
	<form id="form_eliminar" method="post" action="/routes3/mantReportElimina/">
		<input type="hidden" id="id_mantTransacReport" name="id_mantTransacReport">
		<input type="hidden" name="fechaDesde" value=""""),_display_(/*164.50*/desdeAAMMDD),format.raw/*164.61*/("""">
		<input type="hidden" name="fechaHasta" value=""""),_display_(/*165.50*/hastaAAMMDD),format.raw/*165.61*/("""">
	</form>
	
	<form class="formulario" id="formDescargaDocumento" method="post" action="/downloadPDF/">	
		<input type="hidden" id="descargaDocumento" name="nombreArchivo">
	</form>
""")))}),format.raw/*171.2*/("""




"""),format.raw/*176.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*177.31*/("""{"""),format.raw/*177.32*/("""
		  """),format.raw/*178.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*178.36*/("""{"""),format.raw/*178.37*/("""
		    	"""),format.raw/*179.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
				"order": [[ 1, "desc" ],[ 0, "desc" ]],
		    	"language": """),format.raw/*182.20*/("""{"""),format.raw/*182.21*/("""
		        	"""),format.raw/*183.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*184.11*/("""}"""),format.raw/*184.12*/("""
		  """),format.raw/*185.5*/("""}"""),format.raw/*185.6*/(""" """),format.raw/*185.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*187.2*/("""}"""),format.raw/*187.3*/(""");
	
	const mostrarPDF = (nombrePDF) => """),format.raw/*189.36*/("""{"""),format.raw/*189.37*/("""
		  """),format.raw/*190.5*/("""document.getElementById('rutaPDF').innerHTML="<object data='/showPdf/"+nombrePDF+"' type='application/pdf' width='100%' height='720'></object>";
		  $('#muestraPDF').modal('show');
	"""),format.raw/*192.2*/("""}"""),format.raw/*192.3*/("""
	
	"""),format.raw/*194.2*/("""const eliminarMantReport = (id_mantTransacReport) => """),format.raw/*194.55*/("""{"""),format.raw/*194.56*/("""
		"""),format.raw/*195.3*/("""alertify.confirm("Esta seguro de eliminar este report ID: "+id_mantTransacReport, function (e) """),format.raw/*195.98*/("""{"""),format.raw/*195.99*/("""
			"""),format.raw/*196.4*/("""if (e) """),format.raw/*196.11*/("""{"""),format.raw/*196.12*/("""
				"""),format.raw/*197.5*/("""$("#id_mantTransacReport").val(id_mantTransacReport);
				document.getElementById("form_eliminar").submit();
			"""),format.raw/*199.4*/("""}"""),format.raw/*199.5*/("""
		"""),format.raw/*200.3*/("""}"""),format.raw/*200.4*/(""");
	"""),format.raw/*201.2*/("""}"""),format.raw/*201.3*/("""
	
	"""),format.raw/*203.2*/("""const descargaDocumento = (nombreDOC) => """),format.raw/*203.43*/("""{"""),format.raw/*203.44*/("""
		  """),format.raw/*204.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*206.2*/("""}"""),format.raw/*206.3*/("""
	
	"""),format.raw/*208.2*/("""let extArray = new Array(".gif", ".jpg", ".png", ".jpeg", ".pdf", ".xls", ".doc", ".xlsx", ".docx", ".tar", ".zip", ".rar");
	const LimitAttach = (form, file, id_mantTransacReport) => """),format.raw/*209.60*/("""{"""),format.raw/*209.61*/("""
		
		"""),format.raw/*211.3*/("""if (!file) return;
		
		const $inputArchivos = document.querySelector("#docAdjunto");
		const archivosParaSubir = $inputArchivos.files;
		let tamanio = 0;
		let allowSubmit = false;
		
		for(let i=0; i<archivosParaSubir.length; i++)"""),format.raw/*218.48*/("""{"""),format.raw/*218.49*/("""
			"""),format.raw/*219.4*/("""tamanio += archivosParaSubir[i].size;
			let nombre = archivosParaSubir[i].name;
			let extencion = nombre.substring(nombre.lastIndexOf(".")).toLowerCase();
			for (let j = 0; j < extArray.length; j++) """),format.raw/*222.46*/("""{"""),format.raw/*222.47*/("""
				"""),format.raw/*223.5*/("""if (extArray[j] == extencion) """),format.raw/*223.35*/("""{"""),format.raw/*223.36*/(""" 
					"""),format.raw/*224.6*/("""allowSubmit = true;
				"""),format.raw/*225.5*/("""}"""),format.raw/*225.6*/("""
			"""),format.raw/*226.4*/("""}"""),format.raw/*226.5*/("""
			"""),format.raw/*227.4*/("""if(allowSubmit && i < archivosParaSubir.length -1)"""),format.raw/*227.54*/("""{"""),format.raw/*227.55*/("""
				"""),format.raw/*228.5*/("""allowSubmit = false;
			"""),format.raw/*229.4*/("""}"""),format.raw/*229.5*/("""
		"""),format.raw/*230.3*/("""}"""),format.raw/*230.4*/("""
		
		"""),format.raw/*232.3*/("""if (allowSubmit) """),format.raw/*232.20*/("""{"""),format.raw/*232.21*/("""
			"""),format.raw/*233.4*/("""if(tamanio > 200000000)"""),format.raw/*233.27*/("""{"""),format.raw/*233.28*/("""
				"""),format.raw/*234.5*/("""alert("Se permite máximo subir 200 MB,"
				+" lo que se intenta subir pesa: "+Math.round(tamanio/10000)/100+" MB");
				form.docAdjunto.value="";
				return;
			"""),format.raw/*238.4*/("""}"""),format.raw/*238.5*/("""
		"""),format.raw/*239.3*/("""}"""),format.raw/*239.4*/("""else"""),format.raw/*239.8*/("""{"""),format.raw/*239.9*/("""
			"""),format.raw/*240.4*/("""alert("Se permiten únicamente archivos con la extención: " 
			+ (extArray.join("  ")) + "\nPor favor, seleccione solo archivos con extenciones permitidas "
			+ "e intente de nuevo.");
			form.docAdjunto.value="";
			return;
		"""),format.raw/*245.3*/("""}"""),format.raw/*245.4*/("""
		"""),format.raw/*246.3*/("""document.getElementById('enCarga').style.display="block";
		$("#mantReportGrabaDocAnexo_"+id_mantTransacReport).submit();
	"""),format.raw/*248.2*/("""}"""),format.raw/*248.3*/("""
	
"""),format.raw/*250.1*/("""</script>


		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listado:List[List[String]],desdeAAMMDD:String,hastaAAMMDD:String,mapDelete:Map[String,String]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listado,desdeAAMMDD,hastaAAMMDD,mapDelete)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String,Map[String,String]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listado,desdeAAMMDD,hastaAAMMDD,mapDelete) => apply(mapDiccionario,mapPermiso,userMnu,listado,desdeAAMMDD,hastaAAMMDD,mapDelete)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuMantencion/mantListarReports1.scala.html
                  HASH: 5f7ec6a4406567a5d1db3075665c6d1d8a2c71c3
                  MATRIX: 1072->1|1364->200|1392->203|1408->211|1447->213|1476->217|1544->265|1574->269|1885->553|1917->564|1996->616|2028->627|2122->695|2204->756|2234->759|3154->1652|3194->1654|3230->1663|3294->1684|3336->1699|3402->1739|3440->1761|3479->1762|3514->1770|3587->1816|3602->1822|3630->1829|3756->1928|3774->1937|3824->1966|3868->1983|3886->1992|3936->2021|3972->2030|4046->2077|4061->2083|4089->2090|4161->2135|4176->2141|4204->2148|4276->2193|4291->2199|4319->2206|4391->2251|4406->2257|4448->2278|4520->2323|4535->2329|4563->2336|4635->2381|4650->2387|4678->2394|4791->2480|4830->2481|4869->2492|4905->2509|4944->2510|4983->2521|5044->2555|5059->2561|5087->2568|5208->2658|5244->2667|5355->2751|5370->2757|5398->2764|5546->2885|5561->2891|5589->2898|5677->2959|5709->2970|5797->3031|5829->3042|5929->3115|5944->3121|5972->3128|6185->3314|6224->3315|6263->3326|6404->3440|6419->3446|6447->3453|6633->3612|6648->3618|6676->3625|6766->3688|6798->3699|6888->3762|6920->3773|7007->3833|7022->3839|7050->3846|7279->4048|7294->4054|7322->4061|7398->4118|7437->4119|7476->4130|7544->4171|7559->4177|7587->4184|7711->4276|7748->4285|7868->4377|7884->4383|7913->4390|7943->4392|7976->4403|8006->4405|8039->4416|8187->4536|8203->4542|8233->4550|8383->4672|8399->4678|8428->4685|8458->4687|8491->4698|8521->4700|8554->4711|8702->4831|8718->4837|8748->4845|8876->4945|8917->4947|8956->4957|9066->5039|9107->5041|9148->5053|9219->5096|9235->5102|9264->5109|9396->5196|9435->5206|9495->5221|9540->5238|9585->5252|9619->5258|10972->6583|11005->6594|11085->6646|11118->6657|11333->6841|11366->6846|11457->6908|11487->6909|11520->6914|11580->6945|11610->6946|11646->6954|11833->7112|11863->7113|11904->7125|12011->7203|12041->7204|12074->7209|12103->7210|12132->7211|12233->7284|12262->7285|12331->7325|12361->7326|12394->7331|12604->7513|12633->7514|12665->7518|12747->7571|12777->7572|12808->7575|12932->7670|12962->7671|12994->7675|13030->7682|13060->7683|13093->7688|13233->7800|13262->7801|13293->7804|13322->7805|13354->7809|13383->7810|13415->7814|13485->7855|13515->7856|13548->7861|13680->7965|13709->7966|13741->7970|13954->8154|13984->8155|14018->8161|14279->8393|14309->8394|14341->8398|14572->8600|14602->8601|14635->8606|14694->8636|14724->8637|14759->8644|14811->8668|14840->8669|14872->8673|14901->8674|14933->8678|15012->8728|15042->8729|15075->8734|15127->8758|15156->8759|15187->8762|15216->8763|15250->8769|15296->8786|15326->8787|15358->8791|15410->8814|15440->8815|15473->8820|15663->8982|15692->8983|15723->8986|15752->8987|15784->8991|15813->8992|15845->8996|16101->9224|16130->9225|16161->9228|16312->9351|16341->9352|16372->9355
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|43->12|43->12|44->13|44->13|49->18|49->18|50->19|75->44|75->44|76->45|77->46|79->48|82->51|82->51|82->51|83->52|84->53|84->53|84->53|86->55|86->55|86->55|87->56|87->56|87->56|88->57|89->58|89->58|89->58|90->59|90->59|90->59|91->60|91->60|91->60|92->61|92->61|92->61|93->62|93->62|93->62|94->63|94->63|94->63|96->65|96->65|97->66|98->67|98->67|99->68|99->68|99->68|99->68|102->71|103->72|105->74|105->74|105->74|106->75|106->75|106->75|107->76|107->76|108->77|108->77|110->79|110->79|110->79|115->84|115->84|116->85|117->86|117->86|117->86|118->87|118->87|118->87|119->88|119->88|120->89|120->89|121->90|121->90|121->90|124->93|124->93|124->93|127->96|127->96|128->97|128->97|128->97|128->97|131->100|132->101|134->103|134->103|134->103|134->103|134->103|134->103|134->103|137->106|137->106|137->106|140->109|140->109|140->109|140->109|140->109|140->109|140->109|143->112|143->112|143->112|146->115|146->115|147->116|148->117|148->117|149->118|149->118|149->118|149->118|152->121|153->122|154->123|156->125|157->126|158->127|195->164|195->164|196->165|196->165|202->171|207->176|208->177|208->177|209->178|209->178|209->178|210->179|213->182|213->182|214->183|215->184|215->184|216->185|216->185|216->185|218->187|218->187|220->189|220->189|221->190|223->192|223->192|225->194|225->194|225->194|226->195|226->195|226->195|227->196|227->196|227->196|228->197|230->199|230->199|231->200|231->200|232->201|232->201|234->203|234->203|234->203|235->204|237->206|237->206|239->208|240->209|240->209|242->211|249->218|249->218|250->219|253->222|253->222|254->223|254->223|254->223|255->224|256->225|256->225|257->226|257->226|258->227|258->227|258->227|259->228|260->229|260->229|261->230|261->230|263->232|263->232|263->232|264->233|264->233|264->233|265->234|269->238|269->238|270->239|270->239|270->239|270->239|271->240|276->245|276->245|277->246|279->248|279->248|281->250
                  -- GENERATED --
              */
          