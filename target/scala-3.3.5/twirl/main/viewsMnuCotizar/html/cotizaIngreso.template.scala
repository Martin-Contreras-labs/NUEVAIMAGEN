
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

object cotizaIngreso extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listBodegas: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalContactoBodega(mapDiccionario)),format.raw/*9.38*/("""
	"""),_display_(/*10.3*/modalContactoCliente()),format.raw/*10.25*/("""
	"""),_display_(/*11.3*/modalContactoProyecto()),format.raw/*11.26*/("""
	
	"""),format.raw/*13.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*14.4*/barraTitulo(mapDiccionario, "SELECCION DE "+mapDiccionario.get("BODEGA")+"S/PROYECTOS", "(HACER COTIZACION)")),format.raw/*14.113*/("""
		"""),format.raw/*15.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-10 col-sm-8 col-md-8 col-lg-8">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>SUCURSAL</TH>
							<TH>"""),_display_(/*21.13*/mapDiccionario/*21.27*/.get("BODEGA")),format.raw/*21.41*/("""/PROYECTO</TH>
							<TH>CLIENTE</TH>
					        <TH>PROYECTO</TH>
							<TH>SELECT</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*28.8*/for(lista1 <- listBodegas) yield /*28.34*/{_display_(Seq[Any](format.raw/*28.35*/("""
							"""),format.raw/*29.8*/("""<TR>
								<td style="text-align:left;vertical-align:middle;">"""),_display_(/*30.61*/lista1/*30.67*/.get(16)),format.raw/*30.75*/("""</td>
								<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="modalContactoBodega('"""),_display_(/*31.103*/lista1/*31.109*/.get(1)),format.raw/*31.116*/("""');">"""),_display_(/*31.122*/lista1/*31.128*/.get(5)),format.raw/*31.135*/("""</a></td>
								<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="modalContactoCliente('"""),_display_(/*32.104*/lista1/*32.110*/.get(2)),format.raw/*32.117*/("""');">"""),_display_(/*32.123*/lista1/*32.129*/.get(7)),format.raw/*32.136*/("""</a></td>
								<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="modalContactoProyecto('"""),_display_(/*33.105*/lista1/*33.111*/.get(3)),format.raw/*33.118*/("""');">"""),_display_(/*33.124*/lista1/*33.130*/.get(8)),format.raw/*33.137*/("""</a></td>
								<td  style="text-align:center;">
									<a href="/cotizaIngreso2/"""),_display_(/*35.36*/lista1/*35.42*/.get(1)),format.raw/*35.49*/("""">
										<kbd style="background-color: #73C6B6">select</kbd>
									</a>
								</td>
							</TR>
			 			""")))}),format.raw/*40.9*/("""
					"""),format.raw/*41.6*/("""</tbody>
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
				</div>
			</div>
		</div>
	</div>
""")))}),format.raw/*53.2*/("""




"""),format.raw/*58.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*59.31*/("""{"""),format.raw/*59.32*/("""
		  """),format.raw/*60.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*60.36*/("""{"""),format.raw/*60.37*/("""
		    	"""),format.raw/*61.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 1, "asc" ]],
		    	"language": """),format.raw/*64.20*/("""{"""),format.raw/*64.21*/("""
		        	"""),format.raw/*65.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*66.11*/("""}"""),format.raw/*66.12*/("""
		  """),format.raw/*67.5*/("""}"""),format.raw/*67.6*/(""" """),format.raw/*67.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*69.2*/("""}"""),format.raw/*69.3*/(""");
	
</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listBodegas:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listBodegas)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listBodegas) => apply(mapDiccionario,mapPermiso,userMnu,listBodegas)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizar/cotizaIngreso.scala.html
                  HASH: ecc1b574c34578906c30747e3e957fc5f8ec9307
                  MATRIX: 1031->1|1254->131|1287->139|1303->147|1342->149|1371->153|1439->201|1469->206|1524->241|1553->244|1596->266|1625->269|1669->292|1700->296|1777->347|1908->456|1938->459|2269->763|2292->777|2327->791|2492->930|2534->956|2573->957|2608->965|2700->1030|2715->1036|2744->1044|2880->1152|2896->1158|2925->1165|2959->1171|2975->1177|3004->1184|3145->1297|3161->1303|3190->1310|3224->1316|3240->1322|3269->1329|3411->1443|3427->1449|3456->1456|3490->1462|3506->1468|3535->1475|3648->1561|3663->1567|3691->1574|3835->1688|3868->1694|4235->2031|4267->2036|4357->2098|4386->2099|4418->2104|4477->2135|4506->2136|4541->2144|4715->2290|4744->2291|4784->2303|4890->2381|4919->2382|4951->2387|4979->2388|5007->2389|5107->2462|5135->2463
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|41->10|41->10|42->11|42->11|44->13|45->14|45->14|46->15|52->21|52->21|52->21|59->28|59->28|59->28|60->29|61->30|61->30|61->30|62->31|62->31|62->31|62->31|62->31|62->31|63->32|63->32|63->32|63->32|63->32|63->32|64->33|64->33|64->33|64->33|64->33|64->33|66->35|66->35|66->35|71->40|72->41|84->53|89->58|90->59|90->59|91->60|91->60|91->60|92->61|95->64|95->64|96->65|97->66|97->66|98->67|98->67|98->67|100->69|100->69
                  -- GENERATED --
              */
          