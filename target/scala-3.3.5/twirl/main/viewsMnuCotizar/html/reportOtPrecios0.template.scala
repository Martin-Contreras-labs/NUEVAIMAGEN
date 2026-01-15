
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

object reportOtPrecios0 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
lista: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	
	"""),_display_(/*8.3*/modalContactoBodega(mapDiccionario)),format.raw/*8.38*/("""
	"""),_display_(/*9.3*/modalContactoCliente()),format.raw/*9.25*/("""
	"""),_display_(/*10.3*/modalContactoProyecto()),format.raw/*10.26*/("""
	
	"""),format.raw/*12.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*13.4*/barraTitulo(mapDiccionario, "LISTADO "+mapDiccionario.get("BODEGA")+"/PROYECTO VIGENTES CON "+mapDiccionario.get("OT"),"PARA OBTENER DETALLE")),format.raw/*13.146*/("""
		"""),format.raw/*14.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-8 col-md-8 col-lg-8">
				<div class="noprint">
					<table class="table table-sm table-condensed table-fluid">
						<tr>
							<td width="25%">
								<div class="input-group">
							  		<div class="input-group-prepend">
							    		<span class="input-group-text" id="basic-addon1">BUSCAR</span>
							  		</div>
							  		<input type="text" class="form-control left"
										id="searchTermtablaPrincipal"
										onkeyup="doSearch2('tablaPrincipal');">
								</div>
							</td>
						</tr>
					</table>
				</div>
				
				<div class="table-responsive">
					<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
				        <thead style="background-color: #eeeeee">
					        <TR> 
						        <TH style= "text-align: center;vertical-align: top;">SUCURSAL</TH>
								<TH style= "text-align: center;vertical-align: top;">"""),_display_(/*38.63*/mapDiccionario/*38.77*/.get("BODEGA")),format.raw/*38.91*/("""/PROYECTO</TH>
								<TH style= "text-align: center;vertical-align: top;">NOMBRE<BR>CLIENTE</TH>
								<TH style= "text-align: center;vertical-align: top;">NOMBRE<br>PROYECTO</TH>
								<TH style= "text-align: center;vertical-align: top;">"""),_display_(/*41.63*/mapDiccionario/*41.77*/.get("COMUNA")),format.raw/*41.91*/("""<BR>PROYECTO</TH>
								<TH style= "text-align: center;vertical-align: top;width:4%">SELECT</TH>
							</TR>
						</thead>
						<tbody>
						"""),_display_(/*46.8*/for(lista1 <- lista) yield /*46.28*/{_display_(Seq[Any](format.raw/*46.29*/("""
							"""),format.raw/*47.8*/("""<TR>
								<td style="text-align:left;vertical-align:middle;">"""),_display_(/*48.61*/lista1/*48.67*/.get(15)),format.raw/*48.75*/("""</td>
								<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="modalContactoBodega('"""),_display_(/*49.103*/lista1/*49.109*/.get(1)),format.raw/*49.116*/("""');">"""),_display_(/*49.122*/lista1/*49.128*/.get(5)),format.raw/*49.135*/("""</a></td>
								<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="modalContactoCliente('"""),_display_(/*50.104*/lista1/*50.110*/.get(2)),format.raw/*50.117*/("""');">"""),_display_(/*50.123*/lista1/*50.129*/.get(7)),format.raw/*50.136*/("""</a></td>
								<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="modalContactoProyecto('"""),_display_(/*51.105*/lista1/*51.111*/.get(3)),format.raw/*51.118*/("""');">"""),_display_(/*51.124*/lista1/*51.130*/.get(8)),format.raw/*51.137*/("""</a></td>
								<td style="text-align:left;vertical-align:middle;">"""),_display_(/*52.61*/lista1/*52.67*/.get(9)),format.raw/*52.74*/("""</td>
								<td style="text-align:left;vertical-align:middle;">
									<form id="form_"""),_display_(/*54.26*/lista1/*54.32*/.get(1)),format.raw/*54.39*/("""" class="formulario" method="post" action="/routes2/reportOtPrecios1/">
										<input type="hidden" name="id_bodega" value=""""),_display_(/*55.57*/lista1/*55.63*/.get(1)),format.raw/*55.70*/("""">
										<a href="#" onclick="document.getElementById('form_"""),_display_(/*56.63*/lista1/*56.69*/.get(1)),format.raw/*56.76*/("""').submit()">
											<kbd style="background-color: #73C6B6">select</kbd>
										</a>
									</form>
								</td>
							</TR>
			 			""")))}),format.raw/*62.9*/("""
						"""),format.raw/*63.7*/("""</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	

""")))}),format.raw/*71.2*/("""


"""),format.raw/*74.1*/("""<script type="text/javascript">
	let flag = true;
	$(document).ready(function() """),format.raw/*76.31*/("""{"""),format.raw/*76.32*/("""

		 """),format.raw/*78.4*/("""$('#tablaPrincipal').DataTable("""),format.raw/*78.35*/("""{"""),format.raw/*78.36*/("""
		    	"""),format.raw/*79.8*/(""""fixedHeader": true,
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [[ 1, "asc" ]],
		    	"language": """),format.raw/*84.20*/("""{"""),format.raw/*84.21*/("""
		        	"""),format.raw/*85.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*86.11*/("""}"""),format.raw/*86.12*/("""
		  """),format.raw/*87.5*/("""}"""),format.raw/*87.6*/(""" """),format.raw/*87.7*/(""");

			document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*90.2*/("""}"""),format.raw/*90.3*/(""");
	
	
	
</script>




		
		
	
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
                  SOURCE: app/viewsMnuCotizar/reportOtPrecios0.scala.html
                  HASH: d120e93f20cfb93837f15d1b0c1650e916161797
                  MATRIX: 1034->1|1251->125|1283->132|1299->140|1338->142|1367->146|1435->194|1465->199|1520->234|1548->237|1590->259|1619->262|1663->285|1694->289|1771->340|1935->482|1965->485|2967->1460|2990->1474|3025->1488|3298->1734|3321->1748|3356->1762|3530->1910|3566->1930|3605->1931|3640->1939|3732->2004|3747->2010|3776->2018|3912->2126|3928->2132|3957->2139|3991->2145|4007->2151|4036->2158|4177->2271|4193->2277|4222->2284|4256->2290|4272->2296|4301->2303|4443->2417|4459->2423|4488->2430|4522->2436|4538->2442|4567->2449|4664->2519|4679->2525|4707->2532|4825->2623|4840->2629|4868->2636|5023->2764|5038->2770|5066->2777|5158->2842|5173->2848|5201->2855|5375->2999|5409->3006|5504->3071|5534->3074|5642->3154|5671->3155|5703->3160|5762->3191|5791->3192|5826->3200|5993->3339|6022->3340|6062->3352|6168->3430|6197->3431|6229->3436|6257->3437|6285->3438|6385->3511|6413->3512
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|39->8|39->8|40->9|40->9|41->10|41->10|43->12|44->13|44->13|45->14|69->38|69->38|69->38|72->41|72->41|72->41|77->46|77->46|77->46|78->47|79->48|79->48|79->48|80->49|80->49|80->49|80->49|80->49|80->49|81->50|81->50|81->50|81->50|81->50|81->50|82->51|82->51|82->51|82->51|82->51|82->51|83->52|83->52|83->52|85->54|85->54|85->54|86->55|86->55|86->55|87->56|87->56|87->56|93->62|94->63|102->71|105->74|107->76|107->76|109->78|109->78|109->78|110->79|115->84|115->84|116->85|117->86|117->86|118->87|118->87|118->87|121->90|121->90
                  -- GENERATED --
              */
          