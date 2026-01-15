
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

object estadosMantCobraArriendo0 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
lista: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "ESTADO EQUIPO COBRAR "+mapDiccionario("ARRIENDO")+" POR DAÑOS: MODIFICAR", "")),format.raw/*9.111*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-9 col-sm-7 col-md-7 col-lg-7">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR> 
							<TH>SUCURSAL</TH>
							<TH>"""),_display_(/*16.13*/mapDiccionario/*16.27*/.get("BODEGA")),format.raw/*16.41*/("""</TH>
							<TH>EDIT</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*21.8*/for(lista1 <- lista) yield /*21.28*/{_display_(Seq[Any](format.raw/*21.29*/("""
							"""),format.raw/*22.8*/("""<TR>
								<td  style="text-align:left;">"""),_display_(/*23.40*/lista1/*23.46*/.get(1)),format.raw/*23.53*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*24.40*/lista1/*24.46*/.get(2)),format.raw/*24.53*/("""</td>
								<td  style="text-align:center;">
									<a href="#" onclick="modificar('"""),_display_(/*26.43*/lista1/*26.49*/.get(0)),format.raw/*26.56*/("""');">
										<kbd style="background-color: #73C6B6">E</kbd>
									</a>
								</td>
							</TR>
			 			""")))}),format.raw/*31.9*/("""
					"""),format.raw/*32.6*/("""</tbody>
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
	
	<form id="modificar" method="post" action="/routes3/estadosMantCobraArriendo1/">
		<input type="hidden" id="mod_idBodega" name="id_bodegaEmpresa">
	</form>
""")))}),format.raw/*48.2*/("""




"""),format.raw/*53.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*54.31*/("""{"""),format.raw/*54.32*/("""
		  """),format.raw/*55.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*55.36*/("""{"""),format.raw/*55.37*/("""
		    	"""),format.raw/*56.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[-1, 20, 50, 100, 200], ["ALL", 20, 50, 100, 200]],
		    	"language": """),format.raw/*58.20*/("""{"""),format.raw/*58.21*/("""
		        	"""),format.raw/*59.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*60.11*/("""}"""),format.raw/*60.12*/("""
		  """),format.raw/*61.5*/("""}"""),format.raw/*61.6*/(""" """),format.raw/*61.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*63.2*/("""}"""),format.raw/*63.3*/(""");
	
	const modificar = (id_bodegaEmpresa) => """),format.raw/*65.42*/("""{"""),format.raw/*65.43*/("""
		"""),format.raw/*66.3*/("""$("#mod_idBodega").val(id_bodegaEmpresa);
		document.getElementById("modificar").submit();
	"""),format.raw/*68.2*/("""}"""),format.raw/*68.3*/("""
"""),format.raw/*69.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,lista:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,lista)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,lista) => apply(mapDiccionario,mapPermiso,userMnu,lista)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/estadosMantCobraArriendo0.scala.html
                  HASH: a169ae917baf2565d16b38bb7dba33fc5f2c7e89
                  MATRIX: 1044->1|1261->125|1294->133|1310->141|1349->143|1377->146|1445->194|1473->196|1549->247|1677->354|1707->357|2038->661|2061->675|2096->689|2195->762|2231->782|2270->783|2305->791|2376->835|2391->841|2419->848|2491->893|2506->899|2534->906|2650->995|2665->1001|2693->1008|2835->1120|2868->1126|3394->1622|3426->1627|3516->1689|3545->1690|3577->1695|3636->1726|3665->1727|3700->1735|3842->1849|3871->1850|3911->1862|4017->1940|4046->1941|4078->1946|4106->1947|4134->1948|4234->2021|4262->2022|4336->2068|4365->2069|4395->2072|4514->2164|4542->2165|4570->2166
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|47->16|47->16|47->16|52->21|52->21|52->21|53->22|54->23|54->23|54->23|55->24|55->24|55->24|57->26|57->26|57->26|62->31|63->32|79->48|84->53|85->54|85->54|86->55|86->55|86->55|87->56|89->58|89->58|90->59|91->60|91->60|92->61|92->61|92->61|94->63|94->63|96->65|96->65|97->66|99->68|99->68|100->69
                  -- GENERATED --
              */
          