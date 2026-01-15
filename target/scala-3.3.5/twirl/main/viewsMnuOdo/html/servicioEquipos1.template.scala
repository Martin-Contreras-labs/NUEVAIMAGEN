
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

object servicioEquipos1 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listBodegas: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""

"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "MANTENCION DE EQUIPOS PARA SERVICIOS", "(SELECIONAR "+mapDiccionario("BODEGA")+"/PROYECTO)")),format.raw/*9.125*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>SUCURSAL</TH>
							<TH>"""),_display_(/*16.13*/mapDiccionario/*16.27*/.get("BODEGA")),format.raw/*16.41*/("""/PROYECTO</TH>
							<TH>"""),_display_(/*17.13*/mapDiccionario/*17.27*/.get("RUT")),format.raw/*17.38*/(""" """),format.raw/*17.39*/("""(Cliente)</TH>
							<TH>NOMBRE CLIENTE</TH>
					        <TH>NOMBRE DEL PROYECTO</TH>
					        <TH>"""),_display_(/*20.19*/mapDiccionario/*20.33*/.get("COMUNA")),format.raw/*20.47*/(""" """),format.raw/*20.48*/("""PROYECTO</TH>
							<TH>SELECT</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*25.8*/for(lista1 <- listBodegas) yield /*25.34*/{_display_(Seq[Any](format.raw/*25.35*/("""
							"""),format.raw/*26.8*/("""<TR>
								<td  style="text-align:left;">"""),_display_(/*27.40*/lista1/*27.46*/.get(16)),format.raw/*27.54*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*28.40*/lista1/*28.46*/.get(5)),format.raw/*28.53*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*29.42*/lista1/*29.48*/.get(6)),format.raw/*29.55*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*30.40*/lista1/*30.46*/.get(7)),format.raw/*30.53*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*31.40*/lista1/*31.46*/.get(8)),format.raw/*31.53*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*32.40*/lista1/*32.46*/.get(9)),format.raw/*32.53*/("""</td>
								<td  style="text-align:center;">
									<a href="/servicioEquipos2/"""),_display_(/*34.38*/lista1/*34.44*/.get(1)),format.raw/*34.51*/("""">
										<kbd style="background-color: #73C6B6">select</kbd>
									</a>
								</td>
							</TR>
			 			""")))}),format.raw/*39.9*/("""
					"""),format.raw/*40.6*/("""</tbody>
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/servicioEquipos0/';">
				</div>
			</div>
		</div>
	</div>
""")))}),format.raw/*52.2*/("""




"""),format.raw/*57.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*58.31*/("""{"""),format.raw/*58.32*/("""
		  """),format.raw/*59.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*59.36*/("""{"""),format.raw/*59.37*/("""
		    	"""),format.raw/*60.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
				"order": [[1,"asc"]],
		    	"language": """),format.raw/*63.20*/("""{"""),format.raw/*63.21*/("""
		        	"""),format.raw/*64.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*65.11*/("""}"""),format.raw/*65.12*/("""
		  """),format.raw/*66.5*/("""}"""),format.raw/*66.6*/(""" """),format.raw/*66.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*68.2*/("""}"""),format.raw/*68.3*/(""");
	
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
                  SOURCE: app/viewsMnuOdo/servicioEquipos1.scala.html
                  HASH: c27c05c76f890d5803de2b26664fc66486b4d849
                  MATRIX: 1030->1|1253->131|1281->134|1297->142|1336->144|1365->148|1433->196|1461->198|1537->249|1679->370|1709->373|2043->680|2066->694|2101->708|2155->735|2178->749|2210->760|2239->761|2372->867|2395->881|2430->895|2459->896|2568->979|2610->1005|2649->1006|2684->1014|2755->1058|2770->1064|2799->1072|2871->1117|2886->1123|2914->1130|2988->1177|3003->1183|3031->1190|3103->1235|3118->1241|3146->1248|3218->1293|3233->1299|3261->1306|3333->1351|3348->1357|3376->1364|3487->1448|3502->1454|3530->1461|3674->1575|3707->1581|4085->1929|4117->1934|4207->1996|4236->1997|4268->2002|4327->2033|4356->2034|4391->2042|4559->2182|4588->2183|4628->2195|4734->2273|4763->2274|4795->2279|4823->2280|4851->2281|4951->2354|4979->2355
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|47->16|47->16|47->16|48->17|48->17|48->17|48->17|51->20|51->20|51->20|51->20|56->25|56->25|56->25|57->26|58->27|58->27|58->27|59->28|59->28|59->28|60->29|60->29|60->29|61->30|61->30|61->30|62->31|62->31|62->31|63->32|63->32|63->32|65->34|65->34|65->34|70->39|71->40|83->52|88->57|89->58|89->58|90->59|90->59|90->59|91->60|94->63|94->63|95->64|96->65|96->65|97->66|97->66|97->66|99->68|99->68
                  -- GENERATED --
              */
          