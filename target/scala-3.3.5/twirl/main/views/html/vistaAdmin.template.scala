
package views.html

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

object vistaAdmin extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(listProyectos: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
"""),_display_(/*3.2*/main("")/*3.10*/ {_display_(Seq[Any](format.raw/*3.12*/("""

"""),format.raw/*5.1*/("""<div class="row  justify-content-md-center">
	<div class="col-xs-10 col-sm-10 col-md-10 col-lg-10">
		<h1>ADMIN</h1>
		<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
			<thead style="background-color: #eeeeee">
	        	<TR> 
	        		<TH style="text-align:center;">Nro</TH>
	        		<TH style="text-align:center;">PAIS</TH>
			        <TH style="text-align:center; min-width:80px;">FECHA<br>CREADO</TH>
					<TH style="text-align:center;">NOMBRE EMPRESA</TH>
					<TH style="text-align:center;">ULTIMO<br>INGRESO</TH>
				
				<TH style="text-align:center;">ULTIMO<br>MOVIMIENTO</TH>
				<TH style="text-align:center;">CANTIDAD<br>MOVIMIENTOS</TH>
				<TH style="text-align:center;">ULTIMO<br>ODO</TH>
				<TH style="text-align:center;">CANTIDAD<br>ODO</TH>
					<TH style="text-align:center;">SELECT</TH>
				</TR>
			</thead>
			<tbody>
				"""),_display_(/*25.6*/for(lista1 <- listProyectos) yield /*25.34*/{_display_(Seq[Any](format.raw/*25.35*/("""
					"""),format.raw/*26.6*/("""<TR>
						<td style="text-align:center;">"""),_display_(/*27.39*/lista1/*27.45*/.get(0)),format.raw/*27.52*/("""</td>
						<td style="text-align:left;">"""),_display_(/*28.37*/lista1/*28.43*/.get(5)),format.raw/*28.50*/("""</td>
						<td style="text-align:center;">
							<div style="display:none">"""),_display_(/*30.35*/utilities/*30.44*/.Fechas.AAMMDD(lista1.get(1))),format.raw/*30.73*/("""</div>
							"""),_display_(/*31.9*/lista1/*31.15*/.get(1)),format.raw/*31.22*/("""
						"""),format.raw/*32.7*/("""</td>
						<td style="text-align:left;">"""),_display_(/*33.37*/lista1/*33.43*/.get(3)),format.raw/*33.50*/("""</td>
						<td style="text-align:center;">
							<div style="display:none">"""),_display_(/*35.35*/utilities/*35.44*/.Fechas.AAMMDD(lista1.get(8))),format.raw/*35.73*/("""</div>
							"""),_display_(/*36.9*/lista1/*36.15*/.get(8)),format.raw/*36.22*/("""
						"""),format.raw/*37.7*/("""</td>
						
						<td style="text-align:center;">
							<div style="display:none">"""),_display_(/*40.35*/utilities/*40.44*/.Fechas.AAMMDD(lista1.get(6))),format.raw/*40.73*/("""</div>
							"""),_display_(/*41.9*/lista1/*41.15*/.get(6)),format.raw/*41.22*/("""
						"""),format.raw/*42.7*/("""</td>
						<td style="text-align:center;">"""),_display_(/*43.39*/lista1/*43.45*/.get(7)),format.raw/*43.52*/("""</td>
						<td style="text-align:center;">
							<div style="display:none">"""),_display_(/*45.35*/utilities/*45.44*/.Fechas.AAMMDD(lista1.get(9))),format.raw/*45.73*/("""</div>
							"""),_display_(/*46.9*/lista1/*46.15*/.get(6)),format.raw/*46.22*/("""
						"""),format.raw/*47.7*/("""</td>
						<td style="text-align:center;">"""),_display_(/*48.39*/lista1/*48.45*/.get(10)),format.raw/*48.53*/("""</td>
						<td style="text-align:center;">
							<a href="/inicio2Admin/"""),_display_(/*50.32*/lista1/*50.38*/.get(4)),format.raw/*50.45*/("""">
								<kbd style="background-color: #73C6B6">select</kbd>
							</a>
						</td>
					</TR>
			 	""")))}),format.raw/*55.7*/("""
			"""),format.raw/*56.4*/("""</tbody>
		</table>
	</div>
</div>


		
""")))}),format.raw/*63.2*/("""

"""),format.raw/*65.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*66.31*/("""{"""),format.raw/*66.32*/("""
	    """),format.raw/*67.6*/("""$('#tablaPrincipal').DataTable("""),format.raw/*67.37*/("""{"""),format.raw/*67.38*/("""
	    	"""),format.raw/*68.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[-1, 20, 50, 100, 200], ["ALL", 20, 50, 100, 200]]
	    """),format.raw/*70.6*/("""}"""),format.raw/*70.7*/(""" """),format.raw/*70.8*/(""");
	"""),format.raw/*71.2*/("""}"""),format.raw/*71.3*/(""" """),format.raw/*71.4*/(""");
</script>
	
	
	
	
	
	
	"""))
      }
    }
  }

  def render(listProyectos:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(listProyectos)

  def f:((List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (listProyectos) => apply(listProyectos)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/vistaAdmin.scala.html
                  HASH: 044860a8fd8dca7136d4411043eb0bcb5a8f4c2d
                  MATRIX: 962->1|1091->37|1118->39|1134->47|1173->49|1201->51|2136->960|2180->988|2219->989|2252->995|2322->1038|2337->1044|2365->1051|2434->1093|2449->1099|2477->1106|2582->1184|2600->1193|2650->1222|2691->1237|2706->1243|2734->1250|2768->1257|2837->1299|2852->1305|2880->1312|2985->1390|3003->1399|3053->1428|3094->1443|3109->1449|3137->1456|3171->1463|3283->1548|3301->1557|3351->1586|3392->1601|3407->1607|3435->1614|3469->1621|3540->1665|3555->1671|3583->1678|3688->1756|3706->1765|3756->1794|3797->1809|3812->1815|3840->1822|3874->1829|3945->1873|3960->1879|3989->1887|4091->1962|4106->1968|4134->1975|4268->2079|4299->2083|4370->2124|4399->2126|4489->2188|4518->2189|4551->2195|4610->2226|4639->2227|4673->2234|4798->2332|4826->2333|4854->2334|4885->2338|4913->2339|4941->2340
                  LINES: 28->1|33->2|34->3|34->3|34->3|36->5|56->25|56->25|56->25|57->26|58->27|58->27|58->27|59->28|59->28|59->28|61->30|61->30|61->30|62->31|62->31|62->31|63->32|64->33|64->33|64->33|66->35|66->35|66->35|67->36|67->36|67->36|68->37|71->40|71->40|71->40|72->41|72->41|72->41|73->42|74->43|74->43|74->43|76->45|76->45|76->45|77->46|77->46|77->46|78->47|79->48|79->48|79->48|81->50|81->50|81->50|86->55|87->56|94->63|96->65|97->66|97->66|98->67|98->67|98->67|99->68|101->70|101->70|101->70|102->71|102->71|102->71
                  -- GENERATED --
              */
          