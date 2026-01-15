
package viewsMnuCotizar.html

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

object otListaCotizaSinOt extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listCotizaciones: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalContactoCliente()),format.raw/*9.25*/("""
	"""),_display_(/*10.3*/modalContactoProyecto()),format.raw/*10.26*/("""
	
	"""),format.raw/*12.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*13.4*/barraTitulo(mapDiccionario, "LISTADO DE COTIZACIONES","(SIN "+mapDiccionario.get("ORDEN_DE_TRABAJO")+")")),format.raw/*13.109*/("""
		"""),format.raw/*14.3*/("""<!-- <form method="post" action="/cotizaConfirma/"> -->
			<input type="hidden" id="cambiosDeEstados" name="cambiosDeEstados">
			<div class="row  justify-content-md-center">
				<div class="col-xs-11 col-sm-9 col-md-9 col-lg-9">
					<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<TR>
								<TH>SUCURSAL</TH>
								<TH>COMERCIAL</TH>
								<TH>Nro.COTI</TH>
								<TH>SELECT</TH>
								<TH style="min-width:80px;">FECHA<br>COTIZACION</TH>
								<TH>CLIENTE</TH>
								<TH>PROYECTO</TH>
								<TH>OBSERVACIONES</TH>
								<TH>DOC<br>ADJ</TH>
								<TH>SELECT</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*34.9*/for(lista1 <- listCotizaciones) yield /*34.40*/{_display_(Seq[Any](format.raw/*34.41*/("""
								"""),format.raw/*35.9*/("""<TR>
									<td style="text-align:left;">"""),_display_(/*36.40*/lista1/*36.46*/.get(13)),format.raw/*36.54*/("""</td>
									<td style="text-align:left;">"""),_display_(/*37.40*/lista1/*37.46*/.get(14)),format.raw/*37.54*/("""</td>
									<td style="text-align:center;">
										<a href="#" onclick="verCotizacion('"""),_display_(/*39.48*/lista1/*39.54*/.get(0)),format.raw/*39.61*/("""')">
											<kbd style="background-color: rgb(90, 200, 245)">
												<font color="green">"""),_display_(/*41.34*/lista1/*41.40*/.get(1)),format.raw/*41.47*/("""</font>
											</kbd>
										</a>
									</td>
									<td  style="text-align:center;">
										<form id="otHacer_"""),_display_(/*46.30*/lista1/*46.36*/.get(0)),format.raw/*46.43*/("""" method="post" action="/otHacer/">
											<input type="hidden" name="id_cotizacion" value=""""),_display_(/*47.62*/lista1/*47.68*/.get(0)),format.raw/*47.75*/("""">
											<a href="#" onclick='document.getElementById("otHacer_"""),_display_(/*48.67*/lista1/*48.73*/.get(0)),format.raw/*48.80*/("""").submit();'>
												<kbd style="background-color: #73C6B6">select</kbd>
											</a>
										</form>
									</td>
									<td style="text-align:center;">
										<div style="display:none">"""),_display_(/*54.38*/lista1/*54.44*/.get(2)),format.raw/*54.51*/("""</div>
										"""),_display_(/*55.12*/utilities/*55.21*/.Fechas.DDMMAA(lista1.get(2))),format.raw/*55.50*/("""
									"""),format.raw/*56.10*/("""</td>
									<td style="text-align:left;"><a href="#" onclick="buscaCliente('"""),_display_(/*57.75*/lista1/*57.81*/.get(3)),format.raw/*57.88*/("""')">"""),_display_(/*57.93*/lista1/*57.99*/.get(3)),format.raw/*57.106*/("""</a></td>
									<td style="text-align:left;"><a href="#" onclick="buscaProyecto('"""),_display_(/*58.76*/lista1/*58.82*/.get(6)),format.raw/*58.89*/("""')">"""),_display_(/*58.94*/lista1/*58.100*/.get(6)),format.raw/*58.107*/("""</a></td>
									<td style="text-align:left;">"""),_display_(/*59.40*/lista1/*59.46*/.get(4)),format.raw/*59.53*/("""</td>
									<td style="text-align:center;">
										"""),_display_(if(lista1.get(5).equals("0"))/*61.41*/{_display_(Seq[Any](format.raw/*61.42*/("""
											"""),format.raw/*62.12*/("""--
										""")))}else/*63.16*/{_display_(Seq[Any](format.raw/*63.17*/("""
											"""),format.raw/*64.12*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*64.53*/lista1/*64.59*/.get(5)),format.raw/*64.66*/("""')">
												<kbd style="background-color: #7F8C8D">doc</kbd>
											</a>
										""")))}),format.raw/*67.12*/("""
									"""),format.raw/*68.10*/("""</td>
									<td  style="text-align:center;">
										<form id="otHacer_"""),_display_(/*70.30*/lista1/*70.36*/.get(0)),format.raw/*70.43*/("""" method="post" action="/otHacer/">
											<input type="hidden" name="id_cotizacion" value=""""),_display_(/*71.62*/lista1/*71.68*/.get(0)),format.raw/*71.75*/("""">
											<a href="#" onclick='document.getElementById("otHacer_"""),_display_(/*72.67*/lista1/*72.73*/.get(0)),format.raw/*72.80*/("""").submit();'>
												<kbd style="background-color: #73C6B6">select</kbd>
											</a>
										</form>
									</td>
								</TR>
				 			""")))}),format.raw/*78.10*/("""
						"""),format.raw/*79.7*/("""</tbody>
					</table>
				</div>
			</div>
			<div class="noprint">
				<div class="row justify-content-md-center" >
					<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
						<input type="button"  value="Cancelar" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
					</div>
					<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
						<!-- <input type="submit" value='Grabar' class="btn btn-primary btn-sm rounded-pill btn-block"> -->
					</div>
				</div>
			</div>
		<!-- </form> -->
	</div>
	
	<div id='modalVerCotizacion' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable' style="max-width: 80% !important;" role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>COTIZACION</h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<div id='mostrarLaCotizacion'></div>
	   				<div class='row'>
						<div class='col-sm-12' style='text-align:center'>
							<button type='button' class='btn btn-sm  btn-success' style='font-size: 10px;' data-dismiss='modal'>Listo</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<form class="formulario" id="formDescargaDocumento" method="post" action="/downloadPDF/">	
		<input type="hidden" id="descargaDocumento" name="nombreArchivo">
	</form>
	
	

""")))}),format.raw/*123.2*/("""


"""),format.raw/*126.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*127.31*/("""{"""),format.raw/*127.32*/("""
		  """),format.raw/*128.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*128.36*/("""{"""),format.raw/*128.37*/("""
		    	"""),format.raw/*129.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 4, "desc" ]],
		    	"language": """),format.raw/*132.20*/("""{"""),format.raw/*132.21*/("""
		        	"""),format.raw/*133.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*134.11*/("""}"""),format.raw/*134.12*/("""
		  """),format.raw/*135.5*/("""}"""),format.raw/*135.6*/(""" """),format.raw/*135.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*137.2*/("""}"""),format.raw/*137.3*/(""");
	
	
	const verCotizacion = (id_cotizacion) => """),format.raw/*140.43*/("""{"""),format.raw/*140.44*/("""
		"""),format.raw/*141.3*/("""var formData = new FormData();
	  	formData.append('id_cotizacion',id_cotizacion);
        $.ajax("""),format.raw/*143.16*/("""{"""),format.raw/*143.17*/("""
            """),format.raw/*144.13*/("""url: "/verCotizacionAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*151.36*/("""{"""),format.raw/*151.37*/("""
	     		"""),format.raw/*152.9*/("""document.getElementById('mostrarLaCotizacion').innerHTML = respuesta;
	     		$('#modalVerCotizacion').modal('show');
	     	"""),format.raw/*154.8*/("""}"""),format.raw/*154.9*/("""
        """),format.raw/*155.9*/("""}"""),format.raw/*155.10*/(""");
	"""),format.raw/*156.2*/("""}"""),format.raw/*156.3*/("""
	
	"""),format.raw/*158.2*/("""const descargaDocumento = (nombreDOC) => """),format.raw/*158.43*/("""{"""),format.raw/*158.44*/("""
		  """),format.raw/*159.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*161.2*/("""}"""),format.raw/*161.3*/("""
	
	"""),format.raw/*163.2*/("""const cambiaEstado = (id_cotizacion, valor) => """),format.raw/*163.49*/("""{"""),format.raw/*163.50*/("""
		"""),format.raw/*164.3*/("""if(valor == 0) """),format.raw/*164.18*/("""{"""),format.raw/*164.19*/("""
			"""),format.raw/*165.4*/("""document.getElementById(id_cotizacion).value = "1";
			let aux = ""+$("#cambiosDeEstados").val()+id_cotizacion + ","
			$("#cambiosDeEstados").val(aux);
		"""),format.raw/*168.3*/("""}"""),format.raw/*168.4*/("""else"""),format.raw/*168.8*/("""{"""),format.raw/*168.9*/("""
			"""),format.raw/*169.4*/("""document.getElementById(id_cotizacion).value = "0";
			let aux = ""+id_cotizacion + ","
			$("#cambiosDeEstados").val($("#cambiosDeEstados").val().replace(aux,""));
		"""),format.raw/*172.3*/("""}"""),format.raw/*172.4*/("""
		
	"""),format.raw/*174.2*/("""}"""),format.raw/*174.3*/("""
		
		
	
	
	
"""),format.raw/*180.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listCotizaciones:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listCotizaciones)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listCotizaciones) => apply(mapDiccionario,mapPermiso,userMnu,listCotizaciones)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizar/otListaCotizaSinOt.scala.html
                  HASH: a6b2960af3c39c6e7e4b805524514c55c8d3d8a7
                  MATRIX: 1036->1|1264->136|1297->144|1313->152|1352->154|1381->158|1449->206|1479->211|1521->233|1550->236|1594->259|1625->263|1702->314|1829->419|1859->422|2634->1171|2681->1202|2720->1203|2756->1212|2827->1256|2842->1262|2871->1270|2943->1315|2958->1321|2987->1329|3108->1423|3123->1429|3151->1436|3277->1535|3292->1541|3320->1548|3474->1675|3489->1681|3517->1688|3641->1785|3656->1791|3684->1798|3780->1867|3795->1873|3823->1880|4056->2086|4071->2092|4099->2099|4144->2117|4162->2126|4212->2155|4250->2165|4357->2245|4372->2251|4400->2258|4432->2263|4447->2269|4476->2276|4588->2361|4603->2367|4631->2374|4663->2379|4679->2385|4708->2392|4784->2441|4799->2447|4827->2454|4941->2541|4980->2542|5020->2554|5057->2572|5096->2573|5136->2585|5204->2626|5219->2632|5247->2639|5371->2732|5409->2742|5513->2819|5528->2825|5556->2832|5680->2929|5695->2935|5723->2942|5819->3011|5834->3017|5862->3024|6044->3175|6078->3182|7655->4728|7686->4731|7777->4793|7807->4794|7840->4799|7900->4830|7930->4831|7966->4839|8142->4986|8172->4987|8213->4999|8320->5077|8350->5078|8383->5083|8412->5084|8441->5085|8542->5158|8571->5159|8649->5208|8679->5209|8710->5212|8837->5310|8867->5311|8909->5324|9168->5554|9198->5555|9235->5564|9388->5689|9417->5690|9454->5699|9484->5700|9516->5704|9545->5705|9577->5709|9647->5750|9677->5751|9710->5756|9842->5860|9871->5861|9903->5865|9979->5912|10009->5913|10040->5916|10084->5931|10114->5932|10146->5936|10329->6091|10358->6092|10390->6096|10419->6097|10451->6101|10646->6268|10675->6269|10708->6274|10737->6275|10778->6288
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|41->10|41->10|43->12|44->13|44->13|45->14|65->34|65->34|65->34|66->35|67->36|67->36|67->36|68->37|68->37|68->37|70->39|70->39|70->39|72->41|72->41|72->41|77->46|77->46|77->46|78->47|78->47|78->47|79->48|79->48|79->48|85->54|85->54|85->54|86->55|86->55|86->55|87->56|88->57|88->57|88->57|88->57|88->57|88->57|89->58|89->58|89->58|89->58|89->58|89->58|90->59|90->59|90->59|92->61|92->61|93->62|94->63|94->63|95->64|95->64|95->64|95->64|98->67|99->68|101->70|101->70|101->70|102->71|102->71|102->71|103->72|103->72|103->72|109->78|110->79|154->123|157->126|158->127|158->127|159->128|159->128|159->128|160->129|163->132|163->132|164->133|165->134|165->134|166->135|166->135|166->135|168->137|168->137|171->140|171->140|172->141|174->143|174->143|175->144|182->151|182->151|183->152|185->154|185->154|186->155|186->155|187->156|187->156|189->158|189->158|189->158|190->159|192->161|192->161|194->163|194->163|194->163|195->164|195->164|195->164|196->165|199->168|199->168|199->168|199->168|200->169|203->172|203->172|205->174|205->174|211->180
                  -- GENERATED --
              */
          