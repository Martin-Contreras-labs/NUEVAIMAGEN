
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

object reporteMovimientosListaProyectosIE extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

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
		"""),_display_(/*13.4*/barraTitulo(mapDiccionario, "SELECCION DE "+mapDiccionario.get("BODEGA")+"/PROYECTO","REPORTE DETALLE DE MOVIMIENTOS")),format.raw/*13.122*/("""
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
								<TH width="5%" >"""),_display_(/*42.26*/mapDiccionario/*42.40*/.get("ARRIENDO")),format.raw/*42.56*/("""<BR></TH>
								"""),_display_(if(!mapDiccionario.get("nEmpresa").equals("HOHE"))/*43.60*/{_display_(Seq[Any](format.raw/*43.61*/("""
									"""),format.raw/*44.10*/("""<TH width="5%" >VENTA<BR></TH>
								""")))} else {null} ),format.raw/*45.10*/("""
								
							"""),format.raw/*47.8*/("""</TR>
						</thead>
						<tbody>
							"""),_display_(/*50.9*/for(lista1 <- lista) yield /*50.29*/{_display_(Seq[Any](format.raw/*50.30*/("""
								"""),format.raw/*51.9*/("""<TR>
									<td style="text-align:left;vertical-align:middle;">"""),_display_(/*52.62*/lista1/*52.68*/.get(11)),format.raw/*52.76*/("""</td>
									<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="modalContactoBodega('"""),_display_(/*53.104*/lista1/*53.110*/.get(1)),format.raw/*53.117*/("""');">"""),_display_(/*53.123*/lista1/*53.129*/.get(5)),format.raw/*53.136*/("""</a></td>
									<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="modalContactoCliente('"""),_display_(/*54.105*/lista1/*54.111*/.get(2)),format.raw/*54.118*/("""');">"""),_display_(/*54.124*/lista1/*54.130*/.get(7)),format.raw/*54.137*/("""</a></td>
									<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="modalContactoProyecto('"""),_display_(/*55.106*/lista1/*55.112*/.get(3)),format.raw/*55.119*/("""');">"""),_display_(/*55.125*/lista1/*55.131*/.get(8)),format.raw/*55.138*/("""</a></td>
									<td style="text-align:left;vertical-align:middle;">"""),_display_(/*56.62*/lista1/*56.68*/.get(9)),format.raw/*56.75*/("""</td>
									<td style="text-align:left;vertical-align:middle;">
										<form id="form0_"""),_display_(/*58.28*/lista1/*58.34*/.get(1)),format.raw/*58.41*/("""" class="formulario" method="post" action="/reporteMovimientosDetalleIE/">
											<input type="hidden" name="id_bodega" value=""""),_display_(/*59.58*/lista1/*59.64*/.get(1)),format.raw/*59.71*/("""">
											<input type="hidden" name="esVenta" value="0">
											<a href="#" onclick="document.getElementById('form0_"""),_display_(/*61.65*/lista1/*61.71*/.get(1)),format.raw/*61.78*/("""').submit()">
												<kbd style="background-color: #73C6B6">"""),_display_(/*62.53*/mapDiccionario/*62.67*/.get("Arriendos")),format.raw/*62.84*/("""</kbd>
											</a>
										</form>
									</td>
									"""),_display_(if(!mapDiccionario.get("nEmpresa").equals("HOHE"))/*66.61*/{_display_(Seq[Any](format.raw/*66.62*/("""
										"""),format.raw/*67.11*/("""<td style="text-align:left;vertical-align:middle;">
											<form id="form1_"""),_display_(/*68.29*/lista1/*68.35*/.get(1)),format.raw/*68.42*/("""" class="formulario" method="post" action="/reporteMovimientosDetalleIE/">
												<input type="hidden" name="id_bodega" value=""""),_display_(/*69.59*/lista1/*69.65*/.get(1)),format.raw/*69.72*/("""">
												<input type="hidden" name="esVenta" value="1">
												<a href="#" onclick="document.getElementById('form1_"""),_display_(/*71.66*/lista1/*71.72*/.get(1)),format.raw/*71.79*/("""').submit()">
													<kbd style="background-color: #73C6B6">Ventas</kbd>
												</a>
											</form>
										</td>
									""")))} else {null} ),format.raw/*76.11*/("""
								"""),format.raw/*77.9*/("""</TR>
				 			""")))}),format.raw/*78.10*/("""
						"""),format.raw/*79.7*/("""</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>


""")))}),format.raw/*87.2*/("""


"""),format.raw/*90.1*/("""<script type="text/javascript">
	let flag = true;
	$(document).ready(function() """),format.raw/*92.31*/("""{"""),format.raw/*92.32*/("""

		 """),format.raw/*94.4*/("""$('#tablaPrincipal').DataTable("""),format.raw/*94.35*/("""{"""),format.raw/*94.36*/("""
		    	"""),format.raw/*95.8*/(""""fixedHeader": true,
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [[ 1, "asc" ]],
		    	"language": """),format.raw/*100.20*/("""{"""),format.raw/*100.21*/("""
		        	"""),format.raw/*101.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*102.11*/("""}"""),format.raw/*102.12*/("""
		  """),format.raw/*103.5*/("""}"""),format.raw/*103.6*/(""" """),format.raw/*103.7*/(""");

			document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*106.2*/("""}"""),format.raw/*106.3*/(""");
	
	
	
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
                  SOURCE: app/viewsMnuReportes/reporteMovimientosListaProyectosIE.scala.html
                  HASH: 47b1048a38b2470a2b3237160c55061552411f8c
                  MATRIX: 1053->1|1270->125|1302->132|1318->140|1357->142|1386->146|1454->194|1484->199|1539->234|1567->237|1609->259|1638->262|1682->285|1713->289|1790->340|1930->458|1960->461|2968->1442|2991->1456|3026->1470|3299->1716|3322->1730|3357->1744|3427->1787|3450->1801|3487->1817|3583->1886|3622->1887|3660->1897|3744->1937|3788->1954|3857->1997|3893->2017|3932->2018|3968->2027|4061->2093|4076->2099|4105->2107|4242->2216|4258->2222|4287->2229|4321->2235|4337->2241|4366->2248|4508->2362|4524->2368|4553->2375|4587->2381|4603->2387|4632->2394|4775->2509|4791->2515|4820->2522|4854->2528|4870->2534|4899->2541|4997->2612|5012->2618|5040->2625|5161->2719|5176->2725|5204->2732|5363->2864|5378->2870|5406->2877|5558->3002|5573->3008|5601->3015|5694->3081|5717->3095|5755->3112|5898->3228|5937->3229|5976->3240|6083->3320|6098->3326|6126->3333|6286->3466|6301->3472|6329->3479|6483->3606|6498->3612|6526->3619|6711->3760|6747->3769|6793->3784|6827->3791|6921->3855|6951->3858|7059->3938|7088->3939|7120->3944|7179->3975|7208->3976|7243->3984|7411->4123|7441->4124|7482->4136|7589->4214|7619->4215|7652->4220|7681->4221|7710->4222|7811->4295|7840->4296
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|39->8|39->8|40->9|40->9|41->10|41->10|43->12|44->13|44->13|45->14|69->38|69->38|69->38|72->41|72->41|72->41|73->42|73->42|73->42|74->43|74->43|75->44|76->45|78->47|81->50|81->50|81->50|82->51|83->52|83->52|83->52|84->53|84->53|84->53|84->53|84->53|84->53|85->54|85->54|85->54|85->54|85->54|85->54|86->55|86->55|86->55|86->55|86->55|86->55|87->56|87->56|87->56|89->58|89->58|89->58|90->59|90->59|90->59|92->61|92->61|92->61|93->62|93->62|93->62|97->66|97->66|98->67|99->68|99->68|99->68|100->69|100->69|100->69|102->71|102->71|102->71|107->76|108->77|109->78|110->79|118->87|121->90|123->92|123->92|125->94|125->94|125->94|126->95|131->100|131->100|132->101|133->102|133->102|134->103|134->103|134->103|137->106|137->106
                  -- GENERATED --
              */
          