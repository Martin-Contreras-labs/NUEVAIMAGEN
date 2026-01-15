
package viewsMnuReportes.html

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

object reportFacturaProyecto extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template12[Map[String,String],Map[String,String],utilities.UserMnu,String,String,String,Double,Double,Double,String,String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
tabla: String, fechaDesde: String, fechaHasta: String, usd: Double, eur: Double, uf: Double, 
desdeDDMMAA: String, hastaDDMMAA: String, archivoPDF: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.1*/("""    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalContactoBodega(mapDiccionario)),format.raw/*9.38*/("""
	"""),_display_(/*10.3*/modalContactoCliente()),format.raw/*10.25*/("""
	"""),_display_(/*11.3*/modalContactoProyecto()),format.raw/*11.26*/("""
	
	"""),format.raw/*13.2*/("""<div id="enCarga" class="blocker" style="display: none;"><br><br><br><br><br><br><h1>En proceso.....<div id="msgEspera1"></div><div id="msgEspera2"></div></h1></div>
	
	<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*16.4*/barraTitulo(mapDiccionario, "SELECCION DE "+mapDiccionario.get("BODEGA")+"/PROYECTO","PROCESO DE EP/FACTURACION PROFORMA")),format.raw/*16.126*/("""
		
		
			"""),format.raw/*19.4*/("""<div class="noprint">
				<div class="row">
					<div class="col-1"></div>
				  	<div class="col-5">
						"""),_display_(if(mapPermiso.get("parametro.envioMasivoProformas")!=null && mapPermiso.get("parametro.envioMasivoProformas").equals("1"))/*23.130*/{_display_(Seq[Any](format.raw/*23.131*/("""
							"""),format.raw/*24.8*/("""<button type="button"  id="btnEnvioMasivo" class="btn btn-sm btn-warning" 
								onclick="this.setAttribute('disabled', 'true'); document.getElementById('enCarga').style.display='block'; $('#modalEnvioMasivo').modal('show');">
								ENVIO MASIVO A: "Listado de Proformas"
							</button>
						""")))} else {null} ),format.raw/*28.8*/("""
						"""),_display_(if(mapPermiso.get("parametro.envioMasivoProformas")!=null && mapPermiso.get("parametro.envioMasivoProformas").equals("2"))/*29.130*/{_display_(Seq[Any](format.raw/*29.131*/("""
							"""),format.raw/*30.8*/("""<button type="button"  id="btnEnvioMasivo" class="btn btn-sm btn-warning" 
								onclick="$('#modalEnvioMasivoEnProceso').modal('show');">
								ENVIO MASIVO A: "Listado de Proformas"
							</button>
						""")))} else {null} ),format.raw/*34.8*/("""
						
				  	"""),format.raw/*36.8*/("""</div>
				  	<div class="col-6">
						<table class="table table-sm table-condensed table-fluid">
							<tr>
								<td>
										<button type="button"  class="btn btn-sm btn-success" onclick="document.getElementById('excel').submit()">
											Exportar a Excel
										</button>
										<button type="button" class="btn btn-sm btn-success" tabindex="-1" onclick="window.print(); return false;" >
											Imprimir
										</button>
										<button type="button"  class="btn btn-sm btn-success" 
											onclick="history.go(-1);return false;">
											Volver
										</button>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			
			<form id="excel" class="formulario" method="post" action="/reportFacturaProyectoExcel/">
				<input type="hidden" name="fechaDesde" value=""""),_display_(/*59.52*/fechaDesde),format.raw/*59.62*/("""">
				<input type="hidden" name="fechaHasta" value=""""),_display_(/*60.52*/fechaHasta),format.raw/*60.62*/("""">
				<input type="hidden" name="uf" value=""""),_display_(/*61.44*/uf),format.raw/*61.46*/("""">
				<input type="hidden" name="usd" value=""""),_display_(/*62.45*/usd),format.raw/*62.48*/("""">
				<input type="hidden" name="eur" value=""""),_display_(/*63.45*/eur),format.raw/*63.48*/("""">
			</form>
			
			
			
			
			
		<div class="row  justify-content-md-center">
			
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="noprint">
					<table class="table table-sm table-condensed table-fluid">
						<tr>
							<td width="25%">
								<div class="input-group">
							  		<div class="input-group-prepend">
							    		<span class="input-group-text" id="basic-addon1">BUSCAR</span>
							  		</div>
							  		<input type="text" class="form-control left"
										id="searchTermtablaPrincipal"
										onkeyup="doSearch2_conTot('tablaPrincipal');sumarTotales();">
								</div>
							</td>
						</tr>
					</table>
				</div>
				<h5>Período desde """),_display_(/*89.24*/desdeDDMMAA),format.raw/*89.35*/(""" """),format.raw/*89.36*/("""a """),_display_(/*89.39*/hastaDDMMAA),format.raw/*89.50*/("""</h5>
				<div class="table-responsive">
					"""),_display_(/*91.7*/Html(tabla)),format.raw/*91.18*/("""
				"""),format.raw/*92.5*/("""</div>
			</div>
		</div>
	</div>
	
	<div id='muestraPDF' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>FACTURA PROFORMA</h5>
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
	
	<div id='modalEnvioMasivo' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable' role='document' style="width:300px">
			<div class='modal-content'>
				<div class='modal-header'>
					ENVIO MASIVO PROFORMAS
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<div class="input-group">

					        	
					        	<div class="input-group">
								  <div class="input-group-prepend">
								    <div class="input-group-text">
								      <input type="radio" name="optradio" checked onchange="envioDeVentas = 0;">
								    </div>
								  </div>
								  <input type="text" class="form-control" value="Envio de proformas ALQUILER" disabled>
								</div>
								
								<div class="input-group">
								  <div class="input-group-prepend">
								    <div class="input-group-text">
								      <input type="radio" name="optradio" onchange="envioDeVentas = 1;">
								    </div>
								  </div>
								  <input type="text" class="form-control" value="Envio de proformas VENTAS" disabled>
								</div>
					</div>
					<br>
					<div class='row'>
						<div class='col-sm-12' style='text-align:center'>
							<input type="button" class="btn btn-sm btn-primary" value='Enviar' onclick='envioMasivo1();' data-dismiss='modal'>
							<input type='button' class='btn btn-sm btn-warning' value='Cancelar' 
								onclick='$("#btnEnvioMasivo").prop("disabled",false); document.getElementById("enCarga").style.display="none";'
								data-dismiss='modal'>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div id='modalEnvioMasivoEnProceso' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable' role='document' style="width:300px">
			<div class='modal-content'>
				<div class='modal-header'>
					ENVIO MASIVO PROFORMAS
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<div class="input-group">
			        	<p>
							No puede generar una nueva solicitud, ya existe una solicitud en proceso.
			        	</p>
					</div>
					<br>
					<div class='row'>
						<div class='col-sm-12' style='text-align:center'>
							<input type='button' class='btn btn-sm btn-warning' value='Cerrar' 
								onclick='$("#btnEnvioMasivo").prop("disabled",false);'
								data-dismiss='modal'>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


			<form id="envioMasivoListadoProforma" class="formulario" method="post" action="/routes2/envioMasivoListadoProforma2/">
				<input type="hidden" name="fechaDesde" value=""""),_display_(/*193.52*/fechaDesde),format.raw/*193.62*/("""">
				<input type="hidden" name="fechaHasta" value=""""),_display_(/*194.52*/fechaHasta),format.raw/*194.62*/("""">
				<input type="hidden" name="uf" value=""""),_display_(/*195.44*/uf),format.raw/*195.46*/("""">
				<input type="hidden" name="usd" value=""""),_display_(/*196.45*/usd),format.raw/*196.48*/("""">
				<input type="hidden" name="eur" value=""""),_display_(/*197.45*/eur),format.raw/*197.48*/("""">
				<input type="hidden" id="esVenta" name="esVenta" value=""""),_display_(/*198.62*/eur),format.raw/*198.65*/("""">
				<input type="hidden" name="comentarios" value="">
			</form>


""")))}),format.raw/*203.2*/("""


"""),format.raw/*206.1*/("""<script type="text/javascript">


	
	let recorrer = document.querySelectorAll(".arr").length;
	let contador = 0;
	let envioDeVentas = 0;
		
	let classArr = document.querySelectorAll(".arr");
	let classVta = document.querySelectorAll(".vta");
	let classGranTotal = document.querySelectorAll(".granTotal");
	let classIdBodega = document.querySelectorAll(".idBodega");

	let resultado = 'Creadas y enviadas con exito proformas a "Listado de Proformas" las Nros: ';

	const envioMasivo1 = () =>"""),format.raw/*221.28*/("""{"""),format.raw/*221.29*/("""
		
		"""),format.raw/*223.3*/("""document.getElementById('enCarga').style.display="block";
		
		if(envioDeVentas == 0)"""),format.raw/*225.25*/("""{"""),format.raw/*225.26*/("""
			"""),format.raw/*226.4*/("""if (contador < recorrer) """),format.raw/*226.29*/("""{"""),format.raw/*226.30*/("""
				"""),format.raw/*227.5*/("""$("#esVenta").val(envioDeVentas);
				document.getElementById('envioMasivoListadoProforma').submit();
			"""),format.raw/*229.4*/("""}"""),format.raw/*229.5*/("""else"""),format.raw/*229.9*/("""{"""),format.raw/*229.10*/("""
				"""),format.raw/*230.5*/("""alertify.alert("No existen proformas de alquiler a enviar", function () """),format.raw/*230.77*/("""{"""),format.raw/*230.78*/(""" """),format.raw/*230.79*/("""}"""),format.raw/*230.80*/(""");
				document.getElementById('enCarga').style.display="none";
			"""),format.raw/*232.4*/("""}"""),format.raw/*232.5*/("""
		"""),format.raw/*233.3*/("""}"""),format.raw/*233.4*/("""else"""),format.raw/*233.8*/("""{"""),format.raw/*233.9*/("""
			"""),format.raw/*234.4*/("""if (contador < recorrer) """),format.raw/*234.29*/("""{"""),format.raw/*234.30*/("""
				"""),format.raw/*235.5*/("""$("#esVenta").val(envioDeVentas);
				document.getElementById('envioMasivoListadoProforma').submit();
			"""),format.raw/*237.4*/("""}"""),format.raw/*237.5*/("""else"""),format.raw/*237.9*/("""{"""),format.raw/*237.10*/("""
				"""),format.raw/*238.5*/("""alertify.alert("No existen proformas de venta a enviar", function () """),format.raw/*238.74*/("""{"""),format.raw/*238.75*/(""" """),format.raw/*238.76*/("""}"""),format.raw/*238.77*/(""");
				document.getElementById('enCarga').style.display="none";
			"""),format.raw/*240.4*/("""}"""),format.raw/*240.5*/("""
		"""),format.raw/*241.3*/("""}"""),format.raw/*241.4*/("""
		
	"""),format.raw/*243.2*/("""}"""),format.raw/*243.3*/("""
	

	"""),format.raw/*246.2*/("""let flag = true;
	$(document).ready(function() """),format.raw/*247.31*/("""{"""),format.raw/*247.32*/("""

		 """),format.raw/*249.4*/("""$('#tablaPrincipal').DataTable("""),format.raw/*249.35*/("""{"""),format.raw/*249.36*/("""
		    	"""),format.raw/*250.8*/(""""fixedHeader": true,
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [[ 2, "asc" ]],
		    	"language": """),format.raw/*255.20*/("""{"""),format.raw/*255.21*/("""
		        	"""),format.raw/*256.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*257.11*/("""}"""),format.raw/*257.12*/("""
		  """),format.raw/*258.5*/("""}"""),format.raw/*258.6*/(""" """),format.raw/*258.7*/(""");
		sumarTotales();
	"""),format.raw/*260.2*/("""}"""),format.raw/*260.3*/(""");

	const sumarTotales = () => """),format.raw/*262.29*/("""{"""),format.raw/*262.30*/("""
	    """),format.raw/*263.6*/("""let totalCFI = 0;
	    let totalARR = 0;
	    let totalVTA = 0;
	    let totalAjustArr = 0;
	    let totalAjustVta = 0;
	    let totalGranTotal = 0;
	
	    // Sumar solo celdas visibles 
	    $(".cfi:visible").each(function () """),format.raw/*271.41*/("""{"""),format.raw/*271.42*/("""
	        """),format.raw/*272.10*/("""totalCFI += parseFloat($(this).text().replace(/,/g, '') || 0);
	    """),format.raw/*273.6*/("""}"""),format.raw/*273.7*/(""");
	
	    $(".arr:visible").each(function () """),format.raw/*275.41*/("""{"""),format.raw/*275.42*/("""
	        """),format.raw/*276.10*/("""totalARR += parseFloat($(this).text().replace(/,/g, '') || 0);
	    """),format.raw/*277.6*/("""}"""),format.raw/*277.7*/(""");
	
	    $(".vta:visible").each(function () """),format.raw/*279.41*/("""{"""),format.raw/*279.42*/("""
	        """),format.raw/*280.10*/("""totalVTA += parseFloat($(this).text().replace(/,/g, '') || 0);
	    """),format.raw/*281.6*/("""}"""),format.raw/*281.7*/(""");
	
	    $(".ajustArr:visible").each(function () """),format.raw/*283.46*/("""{"""),format.raw/*283.47*/("""
	        """),format.raw/*284.10*/("""totalAjustArr += parseFloat($(this).text().replace(/,/g, '') || 0);
	    """),format.raw/*285.6*/("""}"""),format.raw/*285.7*/(""");
	
	    $(".ajustVta:visible").each(function () """),format.raw/*287.46*/("""{"""),format.raw/*287.47*/("""
	        """),format.raw/*288.10*/("""totalAjustVta += parseFloat($(this).text().replace(/,/g, '') || 0);
	    """),format.raw/*289.6*/("""}"""),format.raw/*289.7*/(""");
	
	    $(".granTotal:visible").each(function () """),format.raw/*291.47*/("""{"""),format.raw/*291.48*/("""
	        """),format.raw/*292.10*/("""totalGranTotal += parseFloat($(this).text().replace(/,/g, '') || 0);
	    """),format.raw/*293.6*/("""}"""),format.raw/*293.7*/(""");
	
	    // Actualizar los totales en la tabla
	    $("#cfi").text(formatStandar(totalCFI,0));
	    $("#arr").text(formatStandar(totalARR,0));
	    $("#vta").text(formatStandar(totalVTA,0));
	    $("#ajustArr").text(formatStandar(totalAjustArr,0));
	    $("#ajustVta").text(formatStandar(totalAjustVta,0));
	    $("#granTotal").text(formatStandar(totalGranTotal,0));
	
	    // Mostrar contenedor (CHAT GPT)
	    $("#mostrarmostrar").show();
	
	    if (""""),_display_(/*306.12*/archivoPDF),format.raw/*306.22*/("""" !== "0") """),format.raw/*306.33*/("""{"""),format.raw/*306.34*/("""
	        """),format.raw/*307.10*/("""$("#rutaPDF").html(
	            `<object data='/showPdf/"""),_display_(/*308.39*/archivoPDF),format.raw/*308.49*/("""' type='application/pdf' width='100%' height='720'></object>`
	        );
	        $("#muestraPDF").modal("show");
	    """),format.raw/*311.6*/("""}"""),format.raw/*311.7*/("""
	"""),format.raw/*312.2*/("""}"""),format.raw/*312.3*/("""
			
"""),format.raw/*314.1*/("""// Llamar a sumarTotales al cargar la página
	$(document).ready(() => """),format.raw/*315.26*/("""{"""),format.raw/*315.27*/("""
	    """),format.raw/*316.6*/("""sumarTotales();
	"""),format.raw/*317.2*/("""}"""),format.raw/*317.3*/(""");
	
	
	
</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,tabla:String,fechaDesde:String,fechaHasta:String,usd:Double,eur:Double,uf:Double,desdeDDMMAA:String,hastaDDMMAA:String,archivoPDF:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,tabla,fechaDesde,fechaHasta,usd,eur,uf,desdeDDMMAA,hastaDDMMAA,archivoPDF)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,String,String,String,Double,Double,Double,String,String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,tabla,fechaDesde,fechaHasta,usd,eur,uf,desdeDDMMAA,hastaDDMMAA,archivoPDF) => apply(mapDiccionario,mapPermiso,userMnu,tabla,fechaDesde,fechaHasta,usd,eur,uf,desdeDDMMAA,hastaDDMMAA,archivoPDF)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reportFacturaProyecto.scala.html
                  HASH: ad04379fc3044fcd9674772f562c6a6282a8ef52
                  MATRIX: 1085->1|1431->254|1463->261|1479->269|1518->271|1547->275|1615->323|1645->328|1700->363|1729->366|1772->388|1801->391|1845->414|1876->418|2122->638|2266->760|2303->770|2562->1001|2602->1002|2637->1010|2980->1310|3138->1440|3178->1441|3213->1449|3468->1661|3510->1676|4361->2500|4392->2510|4473->2564|4504->2574|4577->2620|4600->2622|4674->2669|4698->2672|4772->2719|4796->2722|5519->3418|5551->3429|5580->3430|5610->3433|5642->3444|5715->3491|5747->3502|5779->3507|9548->7248|9580->7258|9662->7312|9694->7322|9768->7368|9792->7370|9867->7417|9892->7420|9967->7467|9992->7470|10084->7534|10109->7537|10211->7608|10242->7611|10761->8101|10791->8102|10825->8108|10939->8193|10969->8194|11001->8198|11055->8223|11085->8224|11118->8229|11251->8334|11280->8335|11312->8339|11342->8340|11375->8345|11476->8417|11506->8418|11536->8419|11566->8420|11661->8487|11690->8488|11721->8491|11750->8492|11782->8496|11811->8497|11843->8501|11897->8526|11927->8527|11960->8532|12093->8637|12122->8638|12154->8642|12184->8643|12217->8648|12315->8717|12345->8718|12375->8719|12405->8720|12500->8787|12529->8788|12560->8791|12589->8792|12622->8797|12651->8798|12684->8803|12760->8850|12790->8851|12823->8856|12883->8887|12913->8888|12949->8896|13117->9035|13147->9036|13188->9048|13295->9126|13325->9127|13358->9132|13387->9133|13416->9134|13466->9156|13495->9157|13556->9189|13586->9190|13620->9196|13876->9423|13906->9424|13945->9434|14041->9502|14070->9503|14144->9548|14174->9549|14213->9559|14309->9627|14338->9628|14412->9673|14442->9674|14481->9684|14577->9752|14606->9753|14685->9803|14715->9804|14754->9814|14855->9887|14884->9888|14963->9938|14993->9939|15032->9949|15133->10022|15162->10023|15242->10074|15272->10075|15311->10085|15413->10159|15442->10160|15925->10615|15957->10625|15997->10636|16027->10637|16066->10647|16152->10705|16184->10715|16332->10835|16361->10836|16391->10838|16420->10839|16453->10844|16552->10914|16582->10915|16616->10921|16661->10938|16690->10939
                  LINES: 28->1|35->4|36->5|36->5|36->5|38->7|38->7|40->9|40->9|41->10|41->10|42->11|42->11|44->13|47->16|47->16|50->19|54->23|54->23|55->24|59->28|60->29|60->29|61->30|65->34|67->36|90->59|90->59|91->60|91->60|92->61|92->61|93->62|93->62|94->63|94->63|120->89|120->89|120->89|120->89|120->89|122->91|122->91|123->92|224->193|224->193|225->194|225->194|226->195|226->195|227->196|227->196|228->197|228->197|229->198|229->198|234->203|237->206|252->221|252->221|254->223|256->225|256->225|257->226|257->226|257->226|258->227|260->229|260->229|260->229|260->229|261->230|261->230|261->230|261->230|261->230|263->232|263->232|264->233|264->233|264->233|264->233|265->234|265->234|265->234|266->235|268->237|268->237|268->237|268->237|269->238|269->238|269->238|269->238|269->238|271->240|271->240|272->241|272->241|274->243|274->243|277->246|278->247|278->247|280->249|280->249|280->249|281->250|286->255|286->255|287->256|288->257|288->257|289->258|289->258|289->258|291->260|291->260|293->262|293->262|294->263|302->271|302->271|303->272|304->273|304->273|306->275|306->275|307->276|308->277|308->277|310->279|310->279|311->280|312->281|312->281|314->283|314->283|315->284|316->285|316->285|318->287|318->287|319->288|320->289|320->289|322->291|322->291|323->292|324->293|324->293|337->306|337->306|337->306|337->306|338->307|339->308|339->308|342->311|342->311|343->312|343->312|345->314|346->315|346->315|347->316|348->317|348->317
                  -- GENERATED --
              */
          