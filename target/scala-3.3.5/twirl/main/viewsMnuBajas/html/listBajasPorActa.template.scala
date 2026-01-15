
package viewsMnuBajas.html

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

object listBajasPorActa extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.ActaBaja],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listActas: List[tables.ActaBaja]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "LISTADO DE ACTAS DE BAJA","")),format.raw/*9.62*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-10 col-sm-8 col-md-8 col-lg-8">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH style="min-width:80px;">FECHA</TH>
							<TH>NUMERO</TH>
							<TH>OBSERVACIONES</TH>
							<TH>DOCUMENTO</TH>
							<TH>SELECT</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*23.8*/for(lista1 <- listActas) yield /*23.32*/{_display_(Seq[Any](format.raw/*23.33*/("""
							"""),format.raw/*24.8*/("""<TR>
								<td style="text-align:center;">
									<div style="display:none">"""),_display_(/*26.37*/utilities/*26.46*/.Fechas.AAMMDD(lista1.getFecha())),format.raw/*26.79*/("""</div>
									"""),_display_(/*27.11*/utilities/*27.20*/.Fechas.DDMMAA(lista1.getFecha())),format.raw/*27.53*/("""
								"""),format.raw/*28.9*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*29.42*/lista1/*29.48*/.getNumero()),format.raw/*29.60*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*30.40*/lista1/*30.46*/.getObservaciones()),format.raw/*30.65*/("""</td>
								<td style="text-align:center;">
									"""),_display_(if(lista1.getActaBajaPDF().equals("0"))/*32.50*/{_display_(Seq[Any](format.raw/*32.51*/("""
										"""),format.raw/*33.11*/("""--
									""")))}else/*34.15*/{_display_(Seq[Any](format.raw/*34.16*/("""
										"""),format.raw/*35.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*35.52*/lista1/*35.58*/.getActaBajaPDF()),format.raw/*35.75*/("""')">
											<kbd style="background-color: #7F8C8D">doc</kbd>
										</a>
									""")))}),format.raw/*38.11*/("""
								"""),format.raw/*39.9*/("""</td>
								<td  style="text-align:center;">
									<a href="/bajaActaPrint/"""),_display_(/*41.35*/lista1/*41.41*/.getId()),format.raw/*41.49*/("""">
										<kbd style="background-color: #73C6B6">select</kbd>
									</a>
								</td>
								
							</TR>
			 			""")))}),format.raw/*47.9*/("""
					"""),format.raw/*48.6*/("""</tbody>
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
	
	<form class="formulario" id="formDescargaDocumento" method="post" action="/downloadPDF/">	
		<input type="hidden" id="descargaDocumento" name="nombreArchivo">
	</form>
	
	

""")))}),format.raw/*67.2*/("""


"""),format.raw/*70.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*71.31*/("""{"""),format.raw/*71.32*/("""
		  """),format.raw/*72.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*72.36*/("""{"""),format.raw/*72.37*/("""
		    	"""),format.raw/*73.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 0, "desc" ],[ 1, "desc" ]],
		    	"language": """),format.raw/*76.20*/("""{"""),format.raw/*76.21*/("""
		        	"""),format.raw/*77.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*78.11*/("""}"""),format.raw/*78.12*/("""
		  """),format.raw/*79.5*/("""}"""),format.raw/*79.6*/(""" """),format.raw/*79.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*81.2*/("""}"""),format.raw/*81.3*/(""");
	
	
	const descargaDocumento = (nombreDOC) => """),format.raw/*84.43*/("""{"""),format.raw/*84.44*/("""
		  """),format.raw/*85.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*87.2*/("""}"""),format.raw/*87.3*/("""
	
	
		
		
	
	
	
"""),format.raw/*95.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listActas:List[tables.ActaBaja]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listActas)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.ActaBaja]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listActas) => apply(mapDiccionario,mapPermiso,userMnu,listActas)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuBajas/listBajasPorActa.scala.html
                  HASH: 286bfd0b4ddb7c53b79efc47ad36178257c1400c
                  MATRIX: 1035->1|1259->132|1292->140|1308->148|1347->150|1376->154|1444->202|1472->204|1548->255|1626->313|1656->316|2143->777|2183->801|2222->802|2257->810|2365->891|2383->900|2437->933|2481->950|2499->959|2553->992|2589->1001|2663->1048|2678->1054|2711->1066|2783->1111|2798->1117|2838->1136|2960->1231|2999->1232|3038->1243|3074->1260|3113->1261|3152->1272|3220->1313|3235->1319|3273->1336|3394->1426|3430->1435|3538->1516|3553->1522|3582->1530|3735->1653|3768->1659|4310->2171|4340->2174|4430->2236|4459->2237|4491->2242|4550->2273|4579->2274|4614->2282|4803->2443|4832->2444|4872->2456|4978->2534|5007->2535|5039->2540|5067->2541|5095->2542|5195->2615|5223->2616|5300->2665|5329->2666|5361->2671|5492->2775|5520->2776|5564->2793
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|54->23|54->23|54->23|55->24|57->26|57->26|57->26|58->27|58->27|58->27|59->28|60->29|60->29|60->29|61->30|61->30|61->30|63->32|63->32|64->33|65->34|65->34|66->35|66->35|66->35|66->35|69->38|70->39|72->41|72->41|72->41|78->47|79->48|98->67|101->70|102->71|102->71|103->72|103->72|103->72|104->73|107->76|107->76|108->77|109->78|109->78|110->79|110->79|110->79|112->81|112->81|115->84|115->84|116->85|118->87|118->87|126->95
                  -- GENERATED --
              */
          