
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

object cotizaListaBiblioteca extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.CotiBiblioteca],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listBiblioteca: List[tables.CotiBiblioteca]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalContactoCliente()),format.raw/*9.25*/("""
	"""),_display_(/*10.3*/modalContactoProyecto()),format.raw/*10.26*/("""
	
	"""),format.raw/*12.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*13.4*/barraTitulo(mapDiccionario, "RESUMEN DE COTIZACIONES HECHAS","")),format.raw/*13.68*/("""
			"""),format.raw/*14.4*/("""<div class="row  justify-content-md-center">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<TR>
								<TH>ID</TH>
								<TH style="min-width:100px;">FECHA DE CAMBIO<br>Universal Time Coordinated (UTC)</TH>
								<TH>SUCURSAL</TH>
								<TH>CLIENTE</TH>
								<TH>PROYECTO</TH>
								<TH>COTIZACIONES</TH>
								<TH>NETO</TH>
								<TH>IVA</TH>
								<TH>TOTAL</TH>
								<TH>PDF CON<br>DETALLE</TH>
								<TH>PDF SIN<br>DETALLE</TH>
								<TH>EXCEL</TH>
								<TH>Del</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*35.9*/for(lista1 <- listBiblioteca) yield /*35.38*/{_display_(Seq[Any](format.raw/*35.39*/("""
								"""),format.raw/*36.9*/("""<TR>
									<td style="text-align:center;vertical-align:middle;">"""),_display_(/*37.64*/lista1/*37.70*/.getId()),format.raw/*37.78*/("""</td>
									<td style="text-align:center;vertical-align:middle;">"""),_display_(/*38.64*/lista1/*38.70*/.getDateCreate()),format.raw/*38.86*/("""</td>
									<td style="text-align:left;vertical-align:middle;">"""),_display_(/*39.62*/lista1/*39.68*/.getNameSucursal()),format.raw/*39.86*/("""</td>
									<td style="text-align:left;vertical-align:middle;">"""),_display_(/*40.62*/lista1/*40.68*/.getCliente()),format.raw/*40.81*/("""</td>
									<td style="text-align:left;vertical-align:middle;">"""),_display_(/*41.62*/lista1/*41.68*/.getProyecto()),format.raw/*41.82*/("""</td>
									<td style="text-align:left;vertical-align:middle;">"""),_display_(/*42.62*/lista1/*42.68*/.getNumCotizaciones()),format.raw/*42.89*/("""</td>
									<td style="text-align:right;vertical-align:middle;">"""),_display_(/*43.63*/lista1/*43.69*/.getNeto()),format.raw/*43.79*/("""</td>
									<td style="text-align:right;vertical-align:middle;">"""),_display_(/*44.63*/lista1/*44.69*/.getIva()),format.raw/*44.78*/("""</td>
									<td style="text-align:right;vertical-align:middle;">"""),_display_(/*45.63*/lista1/*45.69*/.getTotal),format.raw/*45.78*/("""</td>
									<td style="text-align:center;vertical-align:middle;">
										"""),_display_(if(lista1.getPdfConDetalle.equals("0"))/*47.51*/{_display_(Seq[Any](format.raw/*47.52*/("""
											"""),format.raw/*48.12*/("""--
										""")))}else/*49.16*/{_display_(Seq[Any](format.raw/*49.17*/("""
											"""),format.raw/*50.12*/("""<a href="#" onclick="mostrarPDF('"""),_display_(/*50.46*/lista1/*50.52*/.getPdfConDetalle()),format.raw/*50.71*/("""')">
												<kbd style="background-color: #73C6B6">PDF</kbd>
											</a>
										""")))}),format.raw/*53.12*/("""
										
									"""),format.raw/*55.10*/("""</td>
									<td style="text-align:center;vertical-align:middle;">
										"""),_display_(if(lista1.getPdfSinDetalle.equals("0"))/*57.51*/{_display_(Seq[Any](format.raw/*57.52*/("""
											"""),format.raw/*58.12*/("""--
										""")))}else/*59.16*/{_display_(Seq[Any](format.raw/*59.17*/("""
											"""),format.raw/*60.12*/("""<a href="#" onclick="mostrarPDF('"""),_display_(/*60.46*/lista1/*60.52*/.getPdfSinDetalle()),format.raw/*60.71*/("""')">
												<kbd style="background-color: #73C6B6">PDF</kbd>
											</a>
										""")))}),format.raw/*63.12*/("""
										
									"""),format.raw/*65.10*/("""</td>
									<td style="text-align:center;vertical-align:middle;">
										"""),_display_(if(lista1.xlsConDetalle.equals("0"))/*67.48*/{_display_(Seq[Any](format.raw/*67.49*/("""
											"""),format.raw/*68.12*/("""--
										""")))}else/*69.16*/{_display_(Seq[Any](format.raw/*69.17*/("""
											"""),format.raw/*70.12*/("""<a href="#" onclick= "descargaDocumento('"""),_display_(/*70.54*/lista1/*70.60*/.getXlsConDetalle()),format.raw/*70.79*/("""')">
												<kbd style="background-color: #85C1E9">xls</kbd>
											</a>
										""")))}),format.raw/*73.12*/("""
										
									"""),format.raw/*75.10*/("""</td>
									<td style="text-align:center;">
										<a href="#" onclick="eliminaBiblioteca('"""),_display_(/*77.52*/lista1/*77.58*/.getId()),format.raw/*77.66*/("""');">
											<kbd style="background-color: red">X</kbd>
										</a>
									</td>
								</TR>
				 			""")))}),format.raw/*82.10*/("""
						"""),format.raw/*83.7*/("""</tbody>
					</table>
				</div>
			</div>
			<div class="noprint">
				<div class="row justify-content-md-center" >
					<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
						<input type="button"  value="Cancelar" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
					</div>
				</div>
			</div>
	</div>
	
	<div id='muestraPDF' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>DETALLE</h5>
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
	
	<form class="formulario" id="formDescargaDocumento" method="post" action="/downloadPDF/">	
		<input type="hidden" id="descargaDocumento" name="nombreArchivo">
	</form>

	<form id="form_eliminar" method="post" action="/routes2/cotiBibliotecaElimina/">
		<input type="hidden" id="form_id_cotiBiblioteca" name="id_cotiBiblioteca">
	</form>
	

""")))}),format.raw/*126.2*/("""


"""),format.raw/*129.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*130.31*/("""{"""),format.raw/*130.32*/("""
		  """),format.raw/*131.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*131.36*/("""{"""),format.raw/*131.37*/("""
		    	"""),format.raw/*132.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 1, "desc" ]],
		    	"language": """),format.raw/*135.20*/("""{"""),format.raw/*135.21*/("""
		        	"""),format.raw/*136.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*137.11*/("""}"""),format.raw/*137.12*/("""
		  """),format.raw/*138.5*/("""}"""),format.raw/*138.6*/(""" """),format.raw/*138.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*140.2*/("""}"""),format.raw/*140.3*/(""");
	
	const mostrarPDF = (nombrePDF) => """),format.raw/*142.36*/("""{"""),format.raw/*142.37*/("""
		  """),format.raw/*143.5*/("""document.getElementById('rutaPDF').innerHTML="<object data='/showPdf/"+nombrePDF+"' type='application/pdf' width='100%' height='720'></object>";
		  $('#muestraPDF').modal('show');
	"""),format.raw/*145.2*/("""}"""),format.raw/*145.3*/("""
	
	"""),format.raw/*147.2*/("""const descargaDocumento = (nombreDOC) => """),format.raw/*147.43*/("""{"""),format.raw/*147.44*/("""
		  """),format.raw/*148.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*150.2*/("""}"""),format.raw/*150.3*/("""
	
	"""),format.raw/*152.2*/("""const eliminaBiblioteca = (id_cotiBiblioteca) => """),format.raw/*152.51*/("""{"""),format.raw/*152.52*/("""
		"""),format.raw/*153.3*/("""alertify.confirm('Esta seguro de eliminar este resumen id: '+id_cotiBiblioteca, function (e) """),format.raw/*153.96*/("""{"""),format.raw/*153.97*/("""
			"""),format.raw/*154.4*/("""if (e) """),format.raw/*154.11*/("""{"""),format.raw/*154.12*/("""
				"""),format.raw/*155.5*/("""$("#form_id_cotiBiblioteca").val(id_cotiBiblioteca);
				document.getElementById("form_eliminar").submit();
			"""),format.raw/*157.4*/("""}"""),format.raw/*157.5*/("""
		"""),format.raw/*158.3*/("""}"""),format.raw/*158.4*/(""");
	"""),format.raw/*159.2*/("""}"""),format.raw/*159.3*/("""
	
	
	
"""),format.raw/*163.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listBiblioteca:List[tables.CotiBiblioteca]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listBiblioteca)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.CotiBiblioteca]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listBiblioteca) => apply(mapDiccionario,mapPermiso,userMnu,listBiblioteca)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizar/cotizaListaBiblioteca.scala.html
                  HASH: 7e7b94241d9ba7cae9f916a90f657bd24ade7adf
                  MATRIX: 1048->1|1283->143|1316->151|1332->159|1371->161|1400->165|1468->213|1498->218|1540->240|1569->243|1613->266|1644->270|1721->321|1806->385|1837->389|2590->1116|2635->1145|2674->1146|2710->1155|2805->1223|2820->1229|2849->1237|2945->1306|2960->1312|2997->1328|3091->1395|3106->1401|3145->1419|3239->1486|3254->1492|3288->1505|3382->1572|3397->1578|3432->1592|3526->1659|3541->1665|3583->1686|3678->1754|3693->1760|3724->1770|3819->1838|3834->1844|3864->1853|3959->1921|3974->1927|4004->1936|4150->2055|4189->2056|4229->2068|4266->2086|4305->2087|4345->2099|4406->2133|4421->2139|4461->2158|4585->2251|4634->2272|4780->2391|4819->2392|4859->2404|4896->2422|4935->2423|4975->2435|5036->2469|5051->2475|5091->2494|5215->2587|5264->2608|5407->2724|5446->2725|5486->2737|5523->2755|5562->2756|5602->2768|5671->2810|5686->2816|5726->2835|5850->2928|5899->2949|6024->3047|6039->3053|6068->3061|6212->3174|6246->3181|7749->4653|7780->4656|7871->4718|7901->4719|7934->4724|7994->4755|8024->4756|8060->4764|8236->4911|8266->4912|8307->4924|8414->5002|8444->5003|8477->5008|8506->5009|8535->5010|8636->5083|8665->5084|8734->5124|8764->5125|8797->5130|9007->5312|9036->5313|9068->5317|9138->5358|9168->5359|9201->5364|9333->5468|9362->5469|9394->5473|9472->5522|9502->5523|9533->5526|9655->5619|9685->5620|9717->5624|9753->5631|9783->5632|9816->5637|9955->5748|9984->5749|10015->5752|10044->5753|10076->5757|10105->5758|10140->5765
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|41->10|41->10|43->12|44->13|44->13|45->14|66->35|66->35|66->35|67->36|68->37|68->37|68->37|69->38|69->38|69->38|70->39|70->39|70->39|71->40|71->40|71->40|72->41|72->41|72->41|73->42|73->42|73->42|74->43|74->43|74->43|75->44|75->44|75->44|76->45|76->45|76->45|78->47|78->47|79->48|80->49|80->49|81->50|81->50|81->50|81->50|84->53|86->55|88->57|88->57|89->58|90->59|90->59|91->60|91->60|91->60|91->60|94->63|96->65|98->67|98->67|99->68|100->69|100->69|101->70|101->70|101->70|101->70|104->73|106->75|108->77|108->77|108->77|113->82|114->83|157->126|160->129|161->130|161->130|162->131|162->131|162->131|163->132|166->135|166->135|167->136|168->137|168->137|169->138|169->138|169->138|171->140|171->140|173->142|173->142|174->143|176->145|176->145|178->147|178->147|178->147|179->148|181->150|181->150|183->152|183->152|183->152|184->153|184->153|184->153|185->154|185->154|185->154|186->155|188->157|188->157|189->158|189->158|190->159|190->159|194->163
                  -- GENERATED --
              */
          