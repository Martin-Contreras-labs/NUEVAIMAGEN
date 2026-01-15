
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

object bodegaAsignaTasas extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

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
		"""),_display_(/*14.4*/barraTitulo(mapDiccionario, "ASIGNAR TASAS DE "+mapDiccionario.get("ARRIENDO")+" SOBRE PRECIOS DE VENTA", "(LISTA DE SELECCION " + mapDiccionario.get("BODEGA")+"S/PROYECTOS)")),format.raw/*14.179*/("""
		"""),format.raw/*15.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-10 col-md-10 col-lg-10">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>SUCURSAL</TH>
							<TH>"""),_display_(/*21.13*/mapDiccionario/*21.27*/.get("BODEGA")),format.raw/*21.41*/("""/PROYECTO</TH>
							<TH>"""),_display_(/*22.13*/mapDiccionario/*22.27*/.get("RUT")),format.raw/*22.38*/(""" """),format.raw/*22.39*/("""(Cliente o Propietario)</TH>
							<TH>NOMBRE CLIENTE O PROPIETARIO</TH>
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
									<a href="/bodegaFijaTasas/"""),_display_(/*40.37*/lista1/*40.43*/.get(1)),format.raw/*40.50*/("""">
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
                  SOURCE: app/viewsMnuBodegas/bodegaAsignaTasas.scala.html
                  HASH: 8906197796e7c6f1ffcc76e3a0bb691e57df6a5b
                  MATRIX: 1035->1|1258->131|1291->139|1307->147|1346->149|1375->153|1443->201|1473->206|1528->241|1557->244|1600->266|1629->269|1673->292|1704->296|1781->347|1978->522|2008->525|2342->832|2365->846|2400->860|2454->887|2477->901|2509->912|2538->913|2699->1047|2722->1061|2757->1075|2786->1076|2895->1159|2937->1185|2976->1186|3011->1194|3082->1238|3097->1244|3126->1252|3231->1330|3246->1336|3274->1343|3306->1348|3321->1354|3350->1361|3462->1446|3477->1452|3505->1459|3537->1464|3553->1470|3582->1477|3692->1560|3707->1566|3735->1573|3767->1578|3782->1584|3811->1591|3922->1675|3937->1681|3965->1688|3997->1693|4012->1699|4041->1706|4126->1764|4141->1770|4169->1777|4279->1860|4294->1866|4322->1873|4466->1987|4499->1993|4866->2330|4898->2335|4988->2397|5017->2398|5049->2403|5108->2434|5137->2435|5172->2443|5339->2582|5368->2583|5408->2595|5514->2673|5543->2674|5575->2679|5603->2680|5631->2681|5731->2754|5759->2755
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|41->10|41->10|42->11|42->11|44->13|45->14|45->14|46->15|52->21|52->21|52->21|53->22|53->22|53->22|53->22|56->25|56->25|56->25|56->25|61->30|61->30|61->30|62->31|63->32|63->32|63->32|64->33|64->33|64->33|64->33|64->33|64->33|65->34|65->34|65->34|65->34|65->34|65->34|66->35|66->35|66->35|66->35|66->35|66->35|67->36|67->36|67->36|67->36|67->36|67->36|69->38|69->38|69->38|71->40|71->40|71->40|76->45|77->46|89->58|94->63|95->64|95->64|96->65|96->65|96->65|97->66|100->69|100->69|101->70|102->71|102->71|103->72|103->72|103->72|105->74|105->74
                  -- GENERATED --
              */
          