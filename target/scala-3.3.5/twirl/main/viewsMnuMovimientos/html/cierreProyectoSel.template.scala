
package viewsMnuMovimientos.html

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

object cierreProyectoSel extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

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
		"""),_display_(/*14.4*/barraTitulo(mapDiccionario, "SELECCION DE "+mapDiccionario.get("BODEGA")+"S/PROYECTOS PARA CIERRE", "(este proceso permite vender o pasar a baja todo el saldo de stock)")),format.raw/*14.174*/("""
		"""),format.raw/*15.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>SUCURSAL</TH>
							<TH>TIPO</TH>
							<TH>SELECT</TH>
							<TH>"""),_display_(/*23.13*/mapDiccionario/*23.27*/.get("BODEGA")),format.raw/*23.41*/("""/PROYECTO</TH>
							<TH>"""),_display_(/*24.13*/mapDiccionario/*24.27*/.get("RUT")),format.raw/*24.38*/(""" """),format.raw/*24.39*/("""(Cliente o Propietario)</TH>
							<TH>NOMBRE CLIENTE O PROPIETARIO</TH>
					        <TH>NOMBRE DEL PROYECTO</TH>
					        <TH>"""),_display_(/*27.19*/mapDiccionario/*27.33*/.get("COMUNA")),format.raw/*27.47*/(""" """),format.raw/*27.48*/("""PROYECTO</TH>
							<TH>SELECT</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*32.8*/for(lista1 <- listBodegas) yield /*32.34*/{_display_(Seq[Any](format.raw/*32.35*/("""
							"""),format.raw/*33.8*/("""<TR>
								<td  style="text-align:left;">"""),_display_(/*34.40*/lista1/*34.46*/.get(16)),format.raw/*34.54*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*35.40*/lista1/*35.46*/.get(4)),format.raw/*35.53*/("""</td>
								<td  style="text-align:center;">
									<form id="form_"""),_display_(/*37.26*/lista1/*37.32*/.get(1)),format.raw/*37.39*/("""" method="post" action="/cierreProyectoDet/">
										<input type="hidden" name="id_bodegaEmpresa" value=""""),_display_(/*38.64*/lista1/*38.70*/.get(1)),format.raw/*38.77*/("""">
										<a href="#" onclick='document.getElementById("form_"""),_display_(/*39.63*/lista1/*39.69*/.get(1)),format.raw/*39.76*/("""").submit();'>
											<kbd style="background-color: #73C6B6">select</kbd>
										</a>
									</form>
								</td>
								
								
								
								<td style="text-align:left;"><a href="#" onclick="buscaBodega('"""),_display_(/*47.73*/lista1/*47.79*/.get(5)),format.raw/*47.86*/("""')"><div id=""""),_display_(/*47.100*/lista1/*47.106*/.get(1)),format.raw/*47.113*/("""">"""),_display_(/*47.116*/lista1/*47.122*/.get(5)),format.raw/*47.129*/("""</div></a></td>
								<td style="text-align:center;"><a href="#" onclick="buscaCliente('"""),_display_(/*48.76*/lista1/*48.82*/.get(7)),format.raw/*48.89*/("""')">"""),_display_(/*48.94*/lista1/*48.100*/.get(6)),format.raw/*48.107*/("""</a></td>
								<td style="text-align:left;"><a href="#" onclick="buscaCliente('"""),_display_(/*49.74*/lista1/*49.80*/.get(7)),format.raw/*49.87*/("""')">"""),_display_(/*49.92*/lista1/*49.98*/.get(7)),format.raw/*49.105*/("""</a></td>
								<td style="text-align:left;"><a href="#" onclick="buscaProyecto('"""),_display_(/*50.75*/lista1/*50.81*/.get(8)),format.raw/*50.88*/("""')">"""),_display_(/*50.93*/lista1/*50.99*/.get(8)),format.raw/*50.106*/("""</a></td>
								
								<td  style="text-align:left;">"""),_display_(/*52.40*/lista1/*52.46*/.get(9)),format.raw/*52.53*/("""</td>
								<td  style="text-align:center;">
									<form id="form2_"""),_display_(/*54.27*/lista1/*54.33*/.get(1)),format.raw/*54.40*/("""" method="post" action="/cierreProyectoDet/">
										<input type="hidden" name="id_bodegaEmpresa" value=""""),_display_(/*55.64*/lista1/*55.70*/.get(1)),format.raw/*55.77*/("""">
										<a href="#" onclick='document.getElementById("form2_"""),_display_(/*56.64*/lista1/*56.70*/.get(1)),format.raw/*56.77*/("""").submit();'>
											<kbd style="background-color: #73C6B6">select</kbd>
										</a>
									</form>
								</td>
							</TR>
			 			""")))}),format.raw/*62.9*/("""
					"""),format.raw/*63.6*/("""</tbody>
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value='AGREGAR """),_display_(/*70.44*/mapDiccionario/*70.58*/.get("BODEGA")),format.raw/*70.72*/("""' class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/bodegaCrear/';">
				</div>
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
				</div>
			</div>
		</div>
	</div>
""")))}),format.raw/*78.2*/("""




"""),format.raw/*83.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*84.31*/("""{"""),format.raw/*84.32*/("""
		  """),format.raw/*85.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*85.36*/("""{"""),format.raw/*85.37*/("""
		    	"""),format.raw/*86.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
				"order": [[ 1, "asc" ], [ 3, "asc" ]],
		    	"language": """),format.raw/*89.20*/("""{"""),format.raw/*89.21*/("""
		        	"""),format.raw/*90.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*91.11*/("""}"""),format.raw/*91.12*/("""
		  """),format.raw/*92.5*/("""}"""),format.raw/*92.6*/(""" """),format.raw/*92.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*94.2*/("""}"""),format.raw/*94.3*/(""");
	
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
                  SOURCE: app/viewsMnuMovimientos/cierreProyectoSel.scala.html
                  HASH: f6488bb554ec669a3329af5d9933e96d50baf733
                  MATRIX: 1039->1|1262->131|1295->139|1311->147|1350->149|1379->153|1447->201|1476->205|1531->240|1560->243|1603->265|1632->268|1676->291|1707->295|1784->346|1976->516|2006->519|2384->870|2407->884|2442->898|2496->925|2519->939|2551->950|2580->951|2741->1085|2764->1099|2799->1113|2828->1114|2937->1197|2979->1223|3018->1224|3053->1232|3124->1276|3139->1282|3168->1290|3240->1335|3255->1341|3283->1348|3382->1420|3397->1426|3425->1433|3561->1542|3576->1548|3604->1555|3696->1620|3711->1626|3739->1633|3989->1856|4004->1862|4032->1869|4074->1883|4090->1889|4119->1896|4150->1899|4166->1905|4195->1912|4313->2003|4328->2009|4356->2016|4388->2021|4404->2027|4433->2034|4543->2117|4558->2123|4586->2130|4618->2135|4633->2141|4662->2148|4773->2232|4788->2238|4816->2245|4848->2250|4863->2256|4892->2263|4977->2321|4992->2327|5020->2334|5120->2407|5135->2413|5163->2420|5299->2529|5314->2535|5342->2542|5435->2608|5450->2614|5478->2621|5653->2766|5686->2772|5923->2982|5946->2996|5981->3010|6344->3343|6376->3348|6466->3410|6495->3411|6527->3416|6586->3447|6615->3448|6650->3456|6835->3613|6864->3614|6904->3626|7010->3704|7039->3705|7071->3710|7099->3711|7127->3712|7227->3785|7255->3786
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|41->10|41->10|42->11|42->11|44->13|45->14|45->14|46->15|54->23|54->23|54->23|55->24|55->24|55->24|55->24|58->27|58->27|58->27|58->27|63->32|63->32|63->32|64->33|65->34|65->34|65->34|66->35|66->35|66->35|68->37|68->37|68->37|69->38|69->38|69->38|70->39|70->39|70->39|78->47|78->47|78->47|78->47|78->47|78->47|78->47|78->47|78->47|79->48|79->48|79->48|79->48|79->48|79->48|80->49|80->49|80->49|80->49|80->49|80->49|81->50|81->50|81->50|81->50|81->50|81->50|83->52|83->52|83->52|85->54|85->54|85->54|86->55|86->55|86->55|87->56|87->56|87->56|93->62|94->63|101->70|101->70|101->70|109->78|114->83|115->84|115->84|116->85|116->85|116->85|117->86|120->89|120->89|121->90|122->91|122->91|123->92|123->92|123->92|125->94|125->94
                  -- GENERATED --
              */
          