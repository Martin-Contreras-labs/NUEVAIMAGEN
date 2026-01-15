
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

object reporteEstadosAll0 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

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
		"""),_display_(/*13.4*/barraTitulo(mapDiccionario, "SELECCION DE "+mapDiccionario.get("BODEGA")+"/PROYECTO","TODO EL DETALLE POR ESTADO Y REPARACIONES")),format.raw/*13.133*/("""
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
										<form id="form0_"""),_display_(/*54.28*/lista1/*54.34*/.get(1)),format.raw/*54.41*/("""" class="formulario" method="post" action="/reporteEstadosAll1/">
											<input type="hidden" name="id_bodegaEmpresa" value=""""),_display_(/*55.65*/lista1/*55.71*/.get(1)),format.raw/*55.78*/("""">
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
                  SOURCE: app/viewsMnuReportes/reporteEstadosAll0.scala.html
                  HASH: f7ffe4be91745ad0b780bbcb48f2949205f6ac09
                  MATRIX: 1037->1|1254->125|1286->132|1302->140|1341->142|1370->146|1438->194|1468->199|1523->234|1551->237|1593->259|1622->262|1666->285|1697->289|1774->340|1925->469|1955->472|2963->1453|2986->1467|3021->1481|3294->1727|3317->1741|3352->1755|3482->1859|3518->1879|3557->1880|3593->1889|3688->1957|3703->1963|3732->1971|3869->2080|3885->2086|3914->2093|3948->2099|3964->2105|3993->2112|4135->2226|4151->2232|4180->2239|4214->2245|4230->2251|4259->2258|4402->2373|4418->2379|4447->2386|4481->2392|4497->2398|4526->2405|4624->2476|4639->2482|4667->2489|4788->2583|4803->2589|4831->2596|4988->2726|5003->2732|5031->2739|5125->2806|5140->2812|5168->2819|5349->2969|5383->2976|5477->3040|5507->3043|5615->3123|5644->3124|5676->3129|5735->3160|5764->3161|5799->3169|5966->3308|5995->3309|6035->3321|6141->3399|6170->3400|6202->3405|6230->3406|6258->3407|6358->3480|6386->3481
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|39->8|39->8|40->9|40->9|41->10|41->10|43->12|44->13|44->13|45->14|69->38|69->38|69->38|72->41|72->41|72->41|77->46|77->46|77->46|78->47|79->48|79->48|79->48|80->49|80->49|80->49|80->49|80->49|80->49|81->50|81->50|81->50|81->50|81->50|81->50|82->51|82->51|82->51|82->51|82->51|82->51|83->52|83->52|83->52|85->54|85->54|85->54|86->55|86->55|86->55|87->56|87->56|87->56|93->62|94->63|102->71|105->74|107->76|107->76|109->78|109->78|109->78|110->79|115->84|115->84|116->85|117->86|117->86|118->87|118->87|118->87|121->90|121->90
                  -- GENERATED --
              */
          