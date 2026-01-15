
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

object reporteExcedentesListaProyectos extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

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
		"""),_display_(/*13.4*/barraTitulo(mapDiccionario, "SELECCION DE "+mapDiccionario.get("BODEGA")+"/PROYECTO","REPORTE DE EXCEDENTES")),format.raw/*13.113*/("""
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
									<td style="text-align:center;vertical-align:middle;">"""),_display_(/*48.64*/lista1/*48.70*/.get(11)),format.raw/*48.78*/("""</td>
									<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="modalContactoBodega('"""),_display_(/*49.104*/lista1/*49.110*/.get(1)),format.raw/*49.117*/("""');">"""),_display_(/*49.123*/lista1/*49.129*/.get(5)),format.raw/*49.136*/("""</a></td>
									<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="modalContactoCliente('"""),_display_(/*50.105*/lista1/*50.111*/.get(2)),format.raw/*50.118*/("""');">"""),_display_(/*50.124*/lista1/*50.130*/.get(7)),format.raw/*50.137*/("""</a></td>
									<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="modalContactoProyecto('"""),_display_(/*51.106*/lista1/*51.112*/.get(3)),format.raw/*51.119*/("""');">"""),_display_(/*51.125*/lista1/*51.131*/.get(8)),format.raw/*51.138*/("""</a></td>
									<td style="text-align:left;vertical-align:middle;">"""),_display_(/*52.62*/lista1/*52.68*/.get(9)),format.raw/*52.75*/("""</td>
									<td style="text-align:left;vertical-align:middle;">
										<form id="form0_"""),_display_(/*54.28*/lista1/*54.34*/.get(1)),format.raw/*54.41*/("""" class="formulario" method="post" action="/reporteExcedentesDetalle/">
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
                  SOURCE: app/viewsMnuReportes/reporteExcedentesListaProyectos.scala.html
                  HASH: 71fbc3f969bfa64b0eb3b5676d1df9da358101d0
                  MATRIX: 1050->1|1267->125|1299->132|1315->140|1354->142|1383->146|1451->194|1481->199|1536->234|1564->237|1606->259|1635->262|1679->285|1710->289|1787->340|1918->449|1948->452|2956->1433|2979->1447|3014->1461|3287->1707|3310->1721|3345->1735|3475->1839|3511->1859|3550->1860|3586->1869|3681->1937|3696->1943|3725->1951|3862->2060|3878->2066|3907->2073|3941->2079|3957->2085|3986->2092|4128->2206|4144->2212|4173->2219|4207->2225|4223->2231|4252->2238|4395->2353|4411->2359|4440->2366|4474->2372|4490->2378|4519->2385|4617->2456|4632->2462|4660->2469|4781->2563|4796->2569|4824->2576|4980->2705|4995->2711|5023->2718|5117->2785|5132->2791|5160->2798|5341->2948|5375->2955|5469->3019|5499->3022|5607->3102|5636->3103|5668->3108|5727->3139|5756->3140|5791->3148|5958->3287|5987->3288|6027->3300|6133->3378|6162->3379|6194->3384|6222->3385|6250->3386|6350->3459|6378->3460
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|39->8|39->8|40->9|40->9|41->10|41->10|43->12|44->13|44->13|45->14|69->38|69->38|69->38|72->41|72->41|72->41|77->46|77->46|77->46|78->47|79->48|79->48|79->48|80->49|80->49|80->49|80->49|80->49|80->49|81->50|81->50|81->50|81->50|81->50|81->50|82->51|82->51|82->51|82->51|82->51|82->51|83->52|83->52|83->52|85->54|85->54|85->54|86->55|86->55|86->55|87->56|87->56|87->56|93->62|94->63|102->71|105->74|107->76|107->76|109->78|109->78|109->78|110->79|115->84|115->84|116->85|117->86|117->86|118->87|118->87|118->87|121->90|121->90
                  -- GENERATED --
              */
          