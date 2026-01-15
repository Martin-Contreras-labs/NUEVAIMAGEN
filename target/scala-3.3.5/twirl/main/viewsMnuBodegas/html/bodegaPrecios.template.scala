
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

object bodegaPrecios extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

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
		"""),_display_(/*14.4*/barraTitulo(mapDiccionario, "MANTENCION DE PRECIOS POR "+mapDiccionario.get("BODEGA")+"S/PROYECTOS", "(SELECCIONAR)")),format.raw/*14.121*/("""
		"""),format.raw/*15.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>SUCURSAL</TH>
							<TH>"""),_display_(/*21.13*/mapDiccionario/*21.27*/.get("BODEGA")),format.raw/*21.41*/("""/PROYECTO</TH>
							<TH>"""),_display_(/*22.13*/mapDiccionario/*22.27*/.get("RUT")),format.raw/*22.38*/(""" """),format.raw/*22.39*/("""(Cliente)</TH>
							<TH>NOMBRE CLIENTE</TH>
					        <TH>NOMBRE DEL PROYECTO</TH>
					        <TH>"""),_display_(/*25.19*/mapDiccionario/*25.33*/.get("COMUNA")),format.raw/*25.47*/(""" """),format.raw/*25.48*/("""PROYECTO</TH>
							<TH>SELECT</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*30.8*/for(lista1 <- listBodegas) yield /*30.34*/{_display_(Seq[Any](format.raw/*30.35*/("""
							"""),format.raw/*31.8*/("""<TR>
								<td  style="text-align:left;">"""),_display_(/*32.40*/lista1/*32.46*/.get(16)),format.raw/*32.54*/("""</td>
								<td style="text-align:left;"><a href="#" onclick="buscaBodega('"""),_display_(/*33.73*/lista1/*33.79*/.get(5)),format.raw/*33.86*/("""')">"""),_display_(/*33.91*/lista1/*33.97*/.get(5)),format.raw/*33.104*/("""</a></td>
								<td style="text-align:center;"><a href="#" onclick="buscaCliente('"""),_display_(/*34.76*/lista1/*34.82*/.get(7)),format.raw/*34.89*/("""')">"""),_display_(/*34.94*/lista1/*34.100*/.get(6)),format.raw/*34.107*/("""</a></td>
								<td style="text-align:left;"><a href="#" onclick="buscaCliente('"""),_display_(/*35.74*/lista1/*35.80*/.get(7)),format.raw/*35.87*/("""')">"""),_display_(/*35.92*/lista1/*35.98*/.get(7)),format.raw/*35.105*/("""</a></td>
								<td style="text-align:left;"><a href="#" onclick="buscaProyecto('"""),_display_(/*36.75*/lista1/*36.81*/.get(8)),format.raw/*36.88*/("""')">"""),_display_(/*36.93*/lista1/*36.99*/.get(8)),format.raw/*36.106*/("""</a></td>
								
								<td  style="text-align:left;">"""),_display_(/*38.40*/lista1/*38.46*/.get(9)),format.raw/*38.53*/("""</td>
								<td  style="text-align:center;">
									<a href="/bodegaListaPrecios/"""),_display_(/*40.40*/lista1/*40.46*/.get(1)),format.raw/*40.53*/("""">
										<kbd style="background-color: #73C6B6">select</kbd>
									</a>
								</td>
							</TR>
			 			""")))}),format.raw/*45.9*/("""
					"""),format.raw/*46.6*/("""</tbody>
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
""")))}),format.raw/*58.2*/("""




"""),format.raw/*63.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*64.31*/("""{"""),format.raw/*64.32*/("""
		  """),format.raw/*65.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*65.36*/("""{"""),format.raw/*65.37*/("""
		    	"""),format.raw/*66.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
				"order":[[1,"asc"]],
		    	"language": """),format.raw/*69.20*/("""{"""),format.raw/*69.21*/("""
		        	"""),format.raw/*70.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*71.11*/("""}"""),format.raw/*71.12*/("""
		  """),format.raw/*72.5*/("""}"""),format.raw/*72.6*/(""" """),format.raw/*72.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*74.2*/("""}"""),format.raw/*74.3*/(""");
	
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
                  SOURCE: app/viewsMnuBodegas/bodegaPrecios.scala.html
                  HASH: 55814fc55da796bd9c69ace66c3ed889a04c07ed
                  MATRIX: 1031->1|1254->131|1282->134|1298->142|1337->144|1366->148|1434->196|1464->201|1519->236|1548->239|1591->261|1620->264|1664->287|1695->291|1772->342|1911->459|1941->462|2275->769|2298->783|2333->797|2387->824|2410->838|2442->849|2471->850|2604->956|2627->970|2662->984|2691->985|2800->1068|2842->1094|2881->1095|2916->1103|2987->1147|3002->1153|3031->1161|3136->1239|3151->1245|3179->1252|3211->1257|3226->1263|3255->1270|3367->1355|3382->1361|3410->1368|3442->1373|3458->1379|3487->1386|3597->1469|3612->1475|3640->1482|3672->1487|3687->1493|3716->1500|3827->1584|3842->1590|3870->1597|3902->1602|3917->1608|3946->1615|4031->1673|4046->1679|4074->1686|4187->1772|4202->1778|4230->1785|4374->1899|4407->1905|4774->2242|4806->2247|4896->2309|4925->2310|4957->2315|5016->2346|5045->2347|5080->2355|5247->2494|5276->2495|5316->2507|5422->2585|5451->2586|5483->2591|5511->2592|5539->2593|5639->2666|5667->2667
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|41->10|41->10|42->11|42->11|44->13|45->14|45->14|46->15|52->21|52->21|52->21|53->22|53->22|53->22|53->22|56->25|56->25|56->25|56->25|61->30|61->30|61->30|62->31|63->32|63->32|63->32|64->33|64->33|64->33|64->33|64->33|64->33|65->34|65->34|65->34|65->34|65->34|65->34|66->35|66->35|66->35|66->35|66->35|66->35|67->36|67->36|67->36|67->36|67->36|67->36|69->38|69->38|69->38|71->40|71->40|71->40|76->45|77->46|89->58|94->63|95->64|95->64|96->65|96->65|96->65|97->66|100->69|100->69|101->70|102->71|102->71|103->72|103->72|103->72|105->74|105->74
                  -- GENERATED --
              */
          