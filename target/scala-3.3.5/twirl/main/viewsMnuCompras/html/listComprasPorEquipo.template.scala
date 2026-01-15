
package viewsMnuCompras.html

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

object listComprasPorEquipo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listPorEquipos: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "LISTADO DE COMPRAS POR EQUIPOS","")),format.raw/*9.68*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-5 col-sm-4 col-md-3 col-lg-2">
				<form action="/listComprasPorEquipoExcel/" method="POST">
					<input type="submit" class="btn btn-info btn-sm rounded-pill btn-block" value="Excel">
				</form>
			</div>
		</div>
		<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>GRUPO</TH>
							<TH>CODIGO</TH>
							<TH>EQUIPO</TH>
							<TH>UN</TH>
							<TH>CANT</TH>
							<TH>MON</TH>
							<TH>PRECIO<br>(&uacute;ltimo)</TH>
							<TH style="min-width:80px;">FECHA<br>(&uacute;ltima)</TH>
							<TH>SELECT</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*34.8*/for(lista1 <- listPorEquipos) yield /*34.37*/{_display_(Seq[Any](format.raw/*34.38*/("""
							"""),format.raw/*35.8*/("""<TR>
								<td  style="text-align:left;">"""),_display_(/*36.40*/lista1/*36.46*/.get(1)),format.raw/*36.53*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*37.42*/lista1/*37.48*/.get(2)),format.raw/*37.55*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*38.40*/lista1/*38.46*/.get(3)),format.raw/*38.53*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*39.42*/lista1/*39.48*/.get(4)),format.raw/*39.55*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*40.41*/lista1/*40.47*/.get(5)),format.raw/*40.54*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*41.42*/lista1/*41.48*/.get(6)),format.raw/*41.55*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*42.41*/lista1/*42.47*/.get(7)),format.raw/*42.54*/("""</td>
								<td  style="text-align:center;">
									<div style="display:none">"""),_display_(/*44.37*/utilities/*44.46*/.Fechas.AAMMDD(lista1.get(8))),format.raw/*44.75*/("""</div>
									"""),_display_(/*45.11*/utilities/*45.20*/.Fechas.DDMMAA(lista1.get(8))),format.raw/*45.49*/("""
								"""),format.raw/*46.9*/("""</td>
								<td  style="text-align:center;">
									<a href="/compraEquipoPrint/"""),_display_(/*48.39*/lista1/*48.45*/.get(0)),format.raw/*48.52*/("""">
										<kbd style="background-color: #73C6B6">select</kbd>
									</a>
								</td>
								
							</TR>
			 			""")))}),format.raw/*54.9*/("""
					"""),format.raw/*55.6*/("""</tbody>
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
	
	
	

""")))}),format.raw/*71.2*/("""


"""),format.raw/*74.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*75.31*/("""{"""),format.raw/*75.32*/("""
		  """),format.raw/*76.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*76.36*/("""{"""),format.raw/*76.37*/("""
		    	"""),format.raw/*77.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 0, "asc" ],[ 2, "asc" ],[ 1, "asc" ]],
		    	"language": """),format.raw/*80.20*/("""{"""),format.raw/*80.21*/("""
		        	"""),format.raw/*81.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*82.11*/("""}"""),format.raw/*82.12*/("""
		  """),format.raw/*83.5*/("""}"""),format.raw/*83.6*/(""" """),format.raw/*83.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*85.2*/("""}"""),format.raw/*85.3*/(""");
	 
	
	
	
</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listPorEquipos:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listPorEquipos)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listPorEquipos) => apply(mapDiccionario,mapPermiso,userMnu,listPorEquipos)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCompras/listComprasPorEquipo.scala.html
                  HASH: 050cb9acaf5f8bae288c17816fd8665f5e218800
                  MATRIX: 1038->1|1264->134|1297->142|1313->150|1352->152|1381->156|1449->204|1477->206|1553->257|1637->321|1667->324|2552->1183|2597->1212|2636->1213|2671->1221|2742->1265|2757->1271|2785->1278|2859->1325|2874->1331|2902->1338|2974->1383|2989->1389|3017->1396|3091->1443|3106->1449|3134->1456|3207->1502|3222->1508|3250->1515|3324->1562|3339->1568|3367->1575|3440->1621|3455->1627|3483->1634|3593->1717|3611->1726|3661->1755|3705->1772|3723->1781|3773->1810|3809->1819|3921->1904|3936->1910|3964->1917|4117->2040|4150->2046|4523->2389|4553->2392|4643->2454|4672->2455|4704->2460|4763->2491|4792->2492|4827->2500|5027->2672|5056->2673|5096->2685|5202->2763|5231->2764|5263->2769|5291->2770|5319->2771|5419->2844|5447->2845
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|65->34|65->34|65->34|66->35|67->36|67->36|67->36|68->37|68->37|68->37|69->38|69->38|69->38|70->39|70->39|70->39|71->40|71->40|71->40|72->41|72->41|72->41|73->42|73->42|73->42|75->44|75->44|75->44|76->45|76->45|76->45|77->46|79->48|79->48|79->48|85->54|86->55|102->71|105->74|106->75|106->75|107->76|107->76|107->76|108->77|111->80|111->80|112->81|113->82|113->82|114->83|114->83|114->83|116->85|116->85
                  -- GENERATED --
              */
          