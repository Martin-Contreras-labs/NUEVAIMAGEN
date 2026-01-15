
package viewsMnuBodegas.html

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

object bodegaAsignaDctos extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listBodegas: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "ASIGNAR TASAS DE DESCUENTOS SOBRE PRECIOS DE "+mapDiccionario.get("ARRIENDO"), "(LISTA DE SELECCION " + mapDiccionario.get("BODEGA")+"S/PROYECTOS)")),format.raw/*9.181*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-10 col-md-10 col-lg-10">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>SUCURSAL</TH>
							<TH>"""),_display_(/*16.13*/mapDiccionario/*16.27*/.get("BODEGA")),format.raw/*16.41*/("""/PROYECTO</TH>
							<TH>"""),_display_(/*17.13*/mapDiccionario/*17.27*/.get("RUT")),format.raw/*17.38*/(""" """),format.raw/*17.39*/("""(Cliente o Propietario)</TH>
							<TH>NOMBRE CLIENTE O PROPIETARIO</TH>
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
									<a href="/bodegaFijaDctos/"""),_display_(/*34.37*/lista1/*34.43*/.get(1)),format.raw/*34.50*/("""">
										<kbd style="background-color: #73C6B6">Sel</kbd>
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
					<input type="button"  value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
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
				"order":[[1,"asc"]],
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
                  SOURCE: app/viewsMnuBodegas/bodegaAsignaDctos.scala.html
                  HASH: 0d9602a7b58fb586cf72c3cecbfc71f0ad3b1f0d
                  MATRIX: 1035->1|1258->131|1291->139|1307->147|1346->149|1374->152|1442->200|1470->202|1546->253|1744->430|1774->433|2108->740|2131->754|2166->768|2220->795|2243->809|2275->820|2304->821|2465->955|2488->969|2523->983|2552->984|2661->1067|2703->1093|2742->1094|2777->1102|2848->1146|2863->1152|2892->1160|2964->1205|2979->1211|3007->1218|3081->1265|3096->1271|3124->1278|3196->1323|3211->1329|3239->1336|3311->1381|3326->1387|3354->1394|3426->1439|3441->1445|3469->1452|3579->1535|3594->1541|3622->1548|3763->1659|3796->1665|4163->2002|4195->2007|4285->2069|4314->2070|4346->2075|4405->2106|4434->2107|4469->2115|4636->2254|4665->2255|4705->2267|4811->2345|4840->2346|4872->2351|4900->2352|4928->2353|5028->2426|5056->2427
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|47->16|47->16|47->16|48->17|48->17|48->17|48->17|51->20|51->20|51->20|51->20|56->25|56->25|56->25|57->26|58->27|58->27|58->27|59->28|59->28|59->28|60->29|60->29|60->29|61->30|61->30|61->30|62->31|62->31|62->31|63->32|63->32|63->32|65->34|65->34|65->34|70->39|71->40|83->52|88->57|89->58|89->58|90->59|90->59|90->59|91->60|94->63|94->63|95->64|96->65|96->65|97->66|97->66|97->66|99->68|99->68
                  -- GENERATED --
              */
          