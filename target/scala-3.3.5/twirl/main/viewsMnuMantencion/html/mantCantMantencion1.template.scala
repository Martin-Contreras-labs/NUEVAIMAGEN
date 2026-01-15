
package viewsMnuMantencion.html

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

object mantCantMantencion1 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu, listado: List[List[String]], desdeAAMMDD: String, hastaAAMMDD: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""

"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	
	"""),format.raw/*8.2*/("""<div id="enCarga" class="blocker" style="display: none;"><br><br><br><br><br><br><h1>En proceso.....<div id="msgEspera"></div></h1></div>
	
	<form id="excel" class="formulario" method="post" action="/routes3/mantCantMantencion1Excel/">
		<input type="hidden" name="fechaDesde" value=""""),_display_(/*11.50*/desdeAAMMDD),format.raw/*11.61*/("""">
		<input type="hidden" name="fechaHasta" value=""""),_display_(/*12.50*/hastaAAMMDD),format.raw/*12.61*/("""">
		
	</form>
	
	
	<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*18.4*/barraTitulo(mapDiccionario, "CANTIDADES PREVENTIVO (MANTENCION)","DESDE: "+utilities.Fechas.DDMMAA(desdeAAMMDD)+" HASTA: "+utilities.Fechas.DDMMAA(hastaAAMMDD))),format.raw/*18.164*/("""
		"""),format.raw/*19.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			
				<div align="center">
					<button type="button"  class="btn btn-sm btn-success" onclick="document.getElementById('excel').submit()">
						Exportar a Excel
					</button>
				</div>
				
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>CODIGO</TH>
							<TH>EQUIPO</TH>
							<TH></TH>
							<TH>PLAN MANTENCION</TH>
							<TH>UNIDAD</TH>
							<TH>CONSUMO<BR>REPORT MEC</TH>
							<TH>HORAS<BR>MECANICO</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*41.8*/for(lista1 <- listado) yield /*41.30*/{_display_(Seq[Any](format.raw/*41.31*/("""
							"""),format.raw/*42.8*/("""<TR>
								<td  style="text-align:left;">"""),_display_(/*43.40*/lista1/*43.46*/.get(1)),format.raw/*43.53*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*44.40*/lista1/*44.46*/.get(2)),format.raw/*44.53*/("""</td>
								<td></td>
								<td  style="text-align:left;">"""),_display_(/*46.40*/lista1/*46.46*/.get(3)),format.raw/*46.53*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*47.42*/lista1/*47.48*/.get(6)),format.raw/*47.55*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*48.41*/lista1/*48.47*/.get(5)),format.raw/*48.54*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*49.41*/lista1/*49.47*/.get(4)),format.raw/*49.54*/("""</td>
							</TR>
			 			""")))}),format.raw/*51.9*/("""
					"""),format.raw/*52.6*/("""</tbody>
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

""")))}),format.raw/*65.2*/("""




"""),format.raw/*70.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*71.31*/("""{"""),format.raw/*71.32*/("""
		  """),format.raw/*72.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*72.36*/("""{"""),format.raw/*72.37*/("""
		    	"""),format.raw/*73.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
				"order": [[ 1, "asc" ],[ 0, "asc" ]],
		    	"language": """),format.raw/*76.20*/("""{"""),format.raw/*76.21*/("""
		        	"""),format.raw/*77.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*78.11*/("""}"""),format.raw/*78.12*/("""
		  """),format.raw/*79.5*/("""}"""),format.raw/*79.6*/(""" """),format.raw/*79.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*81.2*/("""}"""),format.raw/*81.3*/(""");

	
	
	
</script>


		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listado:List[List[String]],desdeAAMMDD:String,hastaAAMMDD:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listado,desdeAAMMDD,hastaAAMMDD)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listado,desdeAAMMDD,hastaAAMMDD) => apply(mapDiccionario,mapPermiso,userMnu,listado,desdeAAMMDD,hastaAAMMDD)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuMantencion/mantCantMantencion1.scala.html
                  HASH: 2a9e8d6353124505b44850acc5fcb630b0d471b1
                  MATRIX: 1054->1|1315->169|1343->172|1359->180|1398->182|1427->186|1495->234|1525->238|1837->523|1869->534|1948->586|1980->597|2077->668|2259->828|2289->831|3016->1532|3054->1554|3093->1555|3128->1563|3199->1607|3214->1613|3242->1620|3314->1665|3329->1671|3357->1678|3447->1741|3462->1747|3490->1754|3564->1801|3579->1807|3607->1814|3680->1860|3695->1866|3723->1873|3796->1919|3811->1925|3839->1932|3896->1959|3929->1965|4296->2302|4328->2307|4418->2369|4447->2370|4479->2375|4538->2406|4567->2407|4602->2415|4786->2571|4815->2572|4855->2584|4961->2662|4990->2663|5022->2668|5050->2669|5078->2670|5178->2743|5206->2744
                  LINES: 28->1|33->2|35->4|35->4|35->4|37->6|37->6|39->8|42->11|42->11|43->12|43->12|49->18|49->18|50->19|72->41|72->41|72->41|73->42|74->43|74->43|74->43|75->44|75->44|75->44|77->46|77->46|77->46|78->47|78->47|78->47|79->48|79->48|79->48|80->49|80->49|80->49|82->51|83->52|96->65|101->70|102->71|102->71|103->72|103->72|103->72|104->73|107->76|107->76|108->77|109->78|109->78|110->79|110->79|110->79|112->81|112->81
                  -- GENERATED --
              */
          