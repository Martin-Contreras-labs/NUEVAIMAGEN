
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

object servicioPreciosVariable0 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listBodegas: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""

"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "MANTENCION PRECIOS VARIABLES de SERVICIOS POR "+mapDiccionario.get("BODEGA")+"S/PROYECTOS", "(SELECCIONAR)")),format.raw/*9.141*/("""
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
									<a href="/servicioPreciosVariable1/"""),_display_(/*34.46*/lista1/*34.52*/.get(1)),format.raw/*34.59*/("""">
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
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
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
                  SOURCE: app/viewsMnuOdo/servicioPreciosVariable0.scala.html
                  HASH: def81537cc830e1e5c52bad3b0a823a20d020c2f
                  MATRIX: 1038->1|1261->131|1289->134|1305->142|1344->144|1373->148|1441->196|1469->198|1545->249|1703->386|1733->389|2067->696|2090->710|2125->724|2179->751|2202->765|2234->776|2263->777|2396->883|2419->897|2454->911|2483->912|2592->995|2634->1021|2673->1022|2708->1030|2779->1074|2794->1080|2823->1088|2895->1133|2910->1139|2938->1146|3012->1193|3027->1199|3055->1206|3127->1251|3142->1257|3170->1264|3242->1309|3257->1315|3285->1322|3357->1367|3372->1373|3400->1380|3519->1472|3534->1478|3562->1485|3706->1599|3739->1605|4105->1941|4137->1946|4227->2008|4256->2009|4288->2014|4347->2045|4376->2046|4411->2054|4579->2194|4608->2195|4648->2207|4754->2285|4783->2286|4815->2291|4843->2292|4871->2293|4971->2366|4999->2367
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|47->16|47->16|47->16|48->17|48->17|48->17|48->17|51->20|51->20|51->20|51->20|56->25|56->25|56->25|57->26|58->27|58->27|58->27|59->28|59->28|59->28|60->29|60->29|60->29|61->30|61->30|61->30|62->31|62->31|62->31|63->32|63->32|63->32|65->34|65->34|65->34|70->39|71->40|83->52|88->57|89->58|89->58|90->59|90->59|90->59|91->60|94->63|94->63|95->64|96->65|96->65|97->66|97->66|97->66|99->68|99->68
                  -- GENERATED --
              */
          