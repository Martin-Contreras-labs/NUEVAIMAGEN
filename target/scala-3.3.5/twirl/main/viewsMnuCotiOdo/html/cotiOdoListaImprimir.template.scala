
package viewsMnuCotiOdo.html

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

object cotiOdoListaImprimir extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

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
		"""),_display_(/*13.4*/barraTitulo(mapDiccionario, "LISTADO DE TODAS LAS COTIZACIONES ODO","(pendientes y confirmadas)")),format.raw/*13.101*/("""
		"""),format.raw/*14.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>Nro.COTI</TH>
							<TH style="min-width:80px;">FECHA<br>COTIZACION</TH>
							<TH>CLIENTE</TH>
							<TH>PROYECTO</TH>
							<TH>OBSERVACIONES</TH>
							<TH>DOC<br>ADJ</TH>
							<TH>PDF<br>VTA</TH>
							<TH>ESTADO</TH>
							<TH style="min-width:80px;">FECHA<br>CONFIRMADA</TH>
							<TH>SELECT</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*32.8*/for(lista1 <- listCotizaciones) yield /*32.39*/{_display_(Seq[Any](format.raw/*32.40*/("""
							"""),format.raw/*33.8*/("""<TR>
								<td style="text-align:center;">
									<a href="#" onclick="verCotizacion('"""),_display_(/*35.47*/lista1/*35.53*/.get(0)),format.raw/*35.60*/("""')">
											<kbd style="background-color: rgb(90, 200, 245)">
												<font color="green">"""),_display_(/*37.34*/lista1/*37.40*/.get(1)),format.raw/*37.47*/("""</font>
											</kbd>
									</a>
								</td>
								<td style="text-align:center;">
									<div style="display:none">"""),_display_(/*42.37*/lista1/*42.43*/.get(2)),format.raw/*42.50*/("""</div>
									"""),_display_(/*43.11*/utilities/*43.20*/.Fechas.DDMMAA(lista1.get(2))),format.raw/*43.49*/("""
								"""),format.raw/*44.9*/("""</td>
								<td style="text-align:left;"><a href="#" onclick="buscaCliente('"""),_display_(/*45.74*/lista1/*45.80*/.get(3)),format.raw/*45.87*/("""')">"""),_display_(/*45.92*/lista1/*45.98*/.get(3)),format.raw/*45.105*/("""</a></td>
								<td style="text-align:left;"><a href="#" onclick="buscaProyecto('"""),_display_(/*46.75*/lista1/*46.81*/.get(6)),format.raw/*46.88*/("""')">"""),_display_(/*46.93*/lista1/*46.99*/.get(6)),format.raw/*46.106*/("""</a></td>
								<td style="text-align:left;">"""),_display_(/*47.39*/lista1/*47.45*/.get(4)),format.raw/*47.52*/("""</td>
								<td style="text-align:center;">
									"""),_display_(if(lista1.get(5).equals("0"))/*49.40*/{_display_(Seq[Any](format.raw/*49.41*/("""
										"""),format.raw/*50.11*/("""--
									""")))}else/*51.15*/{_display_(Seq[Any](format.raw/*51.16*/("""
										"""),format.raw/*52.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*52.52*/lista1/*52.58*/.get(5)),format.raw/*52.65*/("""')">
											<kbd style="background-color: #7F8C8D">doc</kbd>
										</a>
									""")))}),format.raw/*55.11*/("""
								"""),format.raw/*56.9*/("""</td>
								<td style="text-align:center;">
									"""),_display_(if(lista1.get(10).equals("0"))/*58.41*/{_display_(Seq[Any](format.raw/*58.42*/("""
										"""),format.raw/*59.11*/("""--
									""")))}else/*60.15*/{_display_(Seq[Any](format.raw/*60.16*/("""
										"""),format.raw/*61.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*61.52*/lista1/*61.58*/.get(10)),format.raw/*61.66*/("""')">
											<kbd style="background-color: #7F8C8D">PDF</kbd>
										</a>
									""")))}),format.raw/*64.11*/("""
								"""),format.raw/*65.9*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*66.42*/lista1/*66.48*/.get(8)),format.raw/*66.55*/("""</td>
								<td style="text-align:center;">
									<div style="display:none">"""),_display_(/*68.37*/lista1/*68.43*/.get(9)),format.raw/*68.50*/("""</div>
									"""),_display_(/*69.11*/utilities/*69.20*/.Fechas.DDMMAA(lista1.get(9))),format.raw/*69.49*/("""
								"""),format.raw/*70.9*/("""</td>
								<td  style="text-align:center;">
									<a href="/routes2/cotiOdoImprimir/"""),_display_(/*72.45*/lista1/*72.51*/.get(0)),format.raw/*72.58*/("""">
										<kbd style="background-color: #73C6B6">select</kbd>
									</a>
								</td>
							</TR>
			 			""")))}),format.raw/*77.9*/("""
					"""),format.raw/*78.6*/("""</tbody>
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
	
	<div id='modalVerCotizacion' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable' style="max-width: 80% !important;" role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>COTIZACION ODO</h5>
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
	
	

""")))}),format.raw/*118.2*/("""


"""),format.raw/*121.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*122.31*/("""{"""),format.raw/*122.32*/("""
		  """),format.raw/*123.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*123.36*/("""{"""),format.raw/*123.37*/("""
		    	"""),format.raw/*124.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 1, "desc" ],[ 0, "desc" ]],
		    	"language": """),format.raw/*127.20*/("""{"""),format.raw/*127.21*/("""
		        	"""),format.raw/*128.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*129.11*/("""}"""),format.raw/*129.12*/("""
		  """),format.raw/*130.5*/("""}"""),format.raw/*130.6*/(""" """),format.raw/*130.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*132.2*/("""}"""),format.raw/*132.3*/(""");
	
	
	const verCotizacion = (id_cotiOdo) => """),format.raw/*135.40*/("""{"""),format.raw/*135.41*/("""
		"""),format.raw/*136.3*/("""var formData = new FormData();
	  	formData.append('id_cotiOdo',id_cotiOdo);
        $.ajax("""),format.raw/*138.16*/("""{"""),format.raw/*138.17*/("""
            """),format.raw/*139.13*/("""url: "/routes2/verCotiOdoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*146.36*/("""{"""),format.raw/*146.37*/("""
	     		"""),format.raw/*147.9*/("""document.getElementById('mostrarLaCotizacion').innerHTML = respuesta;
	     		$('#modalVerCotizacion').modal('show');
	     	"""),format.raw/*149.8*/("""}"""),format.raw/*149.9*/("""
        """),format.raw/*150.9*/("""}"""),format.raw/*150.10*/(""");
	"""),format.raw/*151.2*/("""}"""),format.raw/*151.3*/("""
	
	"""),format.raw/*153.2*/("""const descargaDocumento = (nombreDOC) => """),format.raw/*153.43*/("""{"""),format.raw/*153.44*/("""
		  """),format.raw/*154.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*156.2*/("""}"""),format.raw/*156.3*/("""
	
	
		
		
	
	
	
"""),format.raw/*164.1*/("""</script>




		
		
	
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
                  SOURCE: app/viewsMnuCotiOdo/cotiOdoListaImprimir.scala.html
                  HASH: 879574659fe57f6b333591148b2392160220f381
                  MATRIX: 1038->1|1266->136|1299->144|1315->152|1354->154|1383->158|1451->206|1481->211|1523->233|1552->236|1596->259|1627->263|1704->314|1823->411|1853->414|2519->1054|2566->1085|2605->1086|2640->1094|2758->1185|2773->1191|2801->1198|2927->1297|2942->1303|2970->1310|3127->1440|3142->1446|3170->1453|3214->1470|3232->1479|3282->1508|3318->1517|3424->1596|3439->1602|3467->1609|3499->1614|3514->1620|3543->1627|3654->1711|3669->1717|3697->1724|3729->1729|3744->1735|3773->1742|3848->1790|3863->1796|3891->1803|4003->1888|4042->1889|4081->1900|4117->1917|4156->1918|4195->1929|4263->1970|4278->1976|4306->1983|4427->2073|4463->2082|4576->2168|4615->2169|4654->2180|4690->2197|4729->2198|4768->2209|4836->2250|4851->2256|4880->2264|5001->2354|5037->2363|5111->2410|5126->2416|5154->2423|5263->2505|5278->2511|5306->2518|5350->2535|5368->2544|5418->2573|5454->2582|5572->2673|5587->2679|5615->2686|5759->2800|5792->2806|7170->4153|7201->4156|7292->4218|7322->4219|7355->4224|7415->4255|7445->4256|7481->4264|7671->4425|7701->4426|7742->4438|7849->4516|7879->4517|7912->4522|7941->4523|7970->4524|8071->4597|8100->4598|8175->4644|8205->4645|8236->4648|8357->4740|8387->4741|8429->4754|8693->4989|8723->4990|8760->4999|8913->5124|8942->5125|8979->5134|9009->5135|9041->5139|9070->5140|9102->5144|9172->5185|9202->5186|9235->5191|9367->5295|9396->5296|9441->5313
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|41->10|41->10|43->12|44->13|44->13|45->14|63->32|63->32|63->32|64->33|66->35|66->35|66->35|68->37|68->37|68->37|73->42|73->42|73->42|74->43|74->43|74->43|75->44|76->45|76->45|76->45|76->45|76->45|76->45|77->46|77->46|77->46|77->46|77->46|77->46|78->47|78->47|78->47|80->49|80->49|81->50|82->51|82->51|83->52|83->52|83->52|83->52|86->55|87->56|89->58|89->58|90->59|91->60|91->60|92->61|92->61|92->61|92->61|95->64|96->65|97->66|97->66|97->66|99->68|99->68|99->68|100->69|100->69|100->69|101->70|103->72|103->72|103->72|108->77|109->78|149->118|152->121|153->122|153->122|154->123|154->123|154->123|155->124|158->127|158->127|159->128|160->129|160->129|161->130|161->130|161->130|163->132|163->132|166->135|166->135|167->136|169->138|169->138|170->139|177->146|177->146|178->147|180->149|180->149|181->150|181->150|182->151|182->151|184->153|184->153|184->153|185->154|187->156|187->156|195->164
                  -- GENERATED --
              */
          