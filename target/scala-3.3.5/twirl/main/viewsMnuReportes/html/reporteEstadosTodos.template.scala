
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

object reporteEstadosTodos extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

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
		"""),_display_(/*13.4*/barraTitulo(mapDiccionario, "SELECCION DE "+mapDiccionario.get("BODEGA")+"/PROYECTO","REPORTE DETALLADO POR ESTADOS DE EQUIPOS (DEVOLUCIONES)")),format.raw/*13.147*/("""
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
						        <TH style= "text-align: center;vertical-align: top;">"""),_display_(/*38.69*/mapDiccionario/*38.83*/.get("BODEGA")),format.raw/*38.97*/("""/PROYECTO</TH>
								<TH style= "text-align: center;vertical-align: top;">NOMBRE<BR>CLIENTE</TH>
								<TH style= "text-align: center;vertical-align: top;">NOMBRE<br>PROYECTO</TH>
								<TH style= "text-align: center;vertical-align: top;">"""),_display_(/*41.63*/mapDiccionario/*41.77*/.get("COMUNA")),format.raw/*41.91*/("""<BR>PROYECTO</TH>
								<TH width="5%" >SELECT</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*46.9*/for(lista1 <- lista) yield /*46.29*/{_display_(Seq[Any](format.raw/*46.30*/("""
								"""),format.raw/*47.9*/("""<TR>
									<td style="text-align:center;vertical-align:middle;">"""),_display_(/*48.64*/lista1/*48.70*/.get(10)),format.raw/*48.78*/("""</td>
									<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="modalContactoBodega('"""),_display_(/*49.104*/lista1/*49.110*/.get(1)),format.raw/*49.117*/("""');">"""),_display_(/*49.123*/lista1/*49.129*/.get(5)),format.raw/*49.136*/("""</a></td>
									<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="modalContactoCliente('"""),_display_(/*50.105*/lista1/*50.111*/.get(2)),format.raw/*50.118*/("""');">"""),_display_(/*50.124*/lista1/*50.130*/.get(7)),format.raw/*50.137*/("""</a></td>
									<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="modalContactoProyecto('"""),_display_(/*51.106*/lista1/*51.112*/.get(3)),format.raw/*51.119*/("""');">"""),_display_(/*51.125*/lista1/*51.131*/.get(8)),format.raw/*51.138*/("""</a></td>
									<td style="text-align:left;vertical-align:middle;">"""),_display_(/*52.62*/lista1/*52.68*/.get(9)),format.raw/*52.75*/("""</td>
									<td style="text-align:left;vertical-align:middle;">
										<form id="form0_"""),_display_(/*54.28*/lista1/*54.34*/.get(1)),format.raw/*54.41*/("""" class="formulario" method="post" action="/reporteEstadosTodosDetalle/">
											<input type="hidden" name="id_bodega" value=""""),_display_(/*55.58*/lista1/*55.64*/.get(1)),format.raw/*55.71*/("""">
											<a href="#" onclick="document.getElementById('form0_"""),_display_(/*56.65*/lista1/*56.71*/.get(1)),format.raw/*56.78*/("""').submit()">
												<kbd style="background-color: #73C6B6">select</kbd>
											</a>
										</form>
									</td>
								</TR>
				 			""")))}),format.raw/*62.10*/("""
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
                  SOURCE: app/viewsMnuReportes/reporteEstadosTodos.scala.html
                  HASH: 9ebb33e40349b713c9f066f6e74a2a6987573f97
                  MATRIX: 1038->1|1255->125|1287->132|1303->140|1342->142|1371->146|1439->194|1469->199|1524->234|1552->237|1594->259|1623->262|1667->285|1698->289|1775->340|1940->483|1970->486|2978->1467|3001->1481|3036->1495|3309->1741|3332->1755|3367->1769|3497->1873|3533->1893|3572->1894|3608->1903|3703->1971|3718->1977|3747->1985|3884->2094|3900->2100|3929->2107|3963->2113|3979->2119|4008->2126|4150->2240|4166->2246|4195->2253|4229->2259|4245->2265|4274->2272|4417->2387|4433->2393|4462->2400|4496->2406|4512->2412|4541->2419|4639->2490|4654->2496|4682->2503|4803->2597|4818->2603|4846->2610|5004->2741|5019->2747|5047->2754|5141->2821|5156->2827|5184->2834|5365->2984|5399->2991|5493->3055|5523->3058|5631->3138|5660->3139|5692->3144|5751->3175|5780->3176|5815->3184|5982->3323|6011->3324|6051->3336|6157->3414|6186->3415|6218->3420|6246->3421|6274->3422|6374->3495|6402->3496
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|39->8|39->8|40->9|40->9|41->10|41->10|43->12|44->13|44->13|45->14|69->38|69->38|69->38|72->41|72->41|72->41|77->46|77->46|77->46|78->47|79->48|79->48|79->48|80->49|80->49|80->49|80->49|80->49|80->49|81->50|81->50|81->50|81->50|81->50|81->50|82->51|82->51|82->51|82->51|82->51|82->51|83->52|83->52|83->52|85->54|85->54|85->54|86->55|86->55|86->55|87->56|87->56|87->56|93->62|94->63|102->71|105->74|107->76|107->76|109->78|109->78|109->78|110->79|115->84|115->84|116->85|117->86|117->86|118->87|118->87|118->87|121->90|121->90
                  -- GENERATED --
              */
          