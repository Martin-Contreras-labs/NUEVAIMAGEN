
package viewsMnuOdoAppWeb.html

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

object odoAutorizaDetalleVentaWeb extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,tables.VentaServicio,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
ventaServicio: tables.VentaServicio):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

 """),format.raw/*7.2*/("""<!-- menues(mapDiccionario, mapPermiso, userMnu, " ") -->
	<div id="mostrarmostrar" style="display:none;">
		<!-- barraTitulo(mapDiccionario, "DETALLE DE REPORT DIARIO", "") -->
		
		<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
				<br>
				<div align=center><h4 style="color:blue"><strong>DETALLE - REPORT DIARIO</strong></h4></div>
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<thead>
							<tr>
								<td style="text-align:left;"><strong>FECHA (*):</strong></td>
						        <td style="text-align:left;" width="70%" colspan="3">"""),_display_(/*19.69*/ventaServicio/*19.82*/.getFecha()),format.raw/*19.93*/("""</td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>USER_MADA:</strong></td>
						        <td style="text-align:left" width="70%" colspan="3">"""),_display_(/*23.68*/ventaServicio/*23.81*/.getNomUserAdam()),format.raw/*23.98*/("""</td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>OPERADOR:</strong></td>
						        <td style="text-align:left" width="70%" colspan="3">"""),_display_(/*27.68*/ventaServicio/*27.81*/.getNomOperador()),format.raw/*27.98*/("""</td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>"""),_display_(/*30.47*/mapDiccionario/*30.61*/.get("BODEGA")),format.raw/*30.75*/(""" """),format.raw/*30.76*/("""(*):</strong></td>
						        <td style="text-align:left" width="70%" colspan="3">"""),_display_(/*31.68*/ventaServicio/*31.81*/.getNomBodegaEmpresa()),format.raw/*31.103*/("""</td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>SERVICIO (*):</strong></td>
								<td style="text-align:left" width="70%" colspan="3">"""),_display_(/*35.62*/ventaServicio/*35.75*/.getNroCotiOdo()),format.raw/*35.91*/(""" """),format.raw/*35.92*/("""- """),_display_(/*35.95*/ventaServicio/*35.108*/.getCodServicio()),format.raw/*35.125*/(""" """),format.raw/*35.126*/("""- """),_display_(/*35.129*/ventaServicio/*35.142*/.getNomServicio()),format.raw/*35.159*/("""</td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>EQUIPO:</strong></td>
								<td style="text-align:left" width="70%" colspan="3">"""),_display_(/*39.62*/ventaServicio/*39.75*/.getCodEquipo()),format.raw/*39.90*/(""" """),format.raw/*39.91*/("""- """),_display_(/*39.94*/ventaServicio/*39.107*/.getNomEquipo()),format.raw/*39.122*/("""</td>
							</tr>
							
							<tr>
								<td style="text-align:left;"><strong>Hr_INI:</strong></td>
						        <td style="text-align:left">"""),_display_(/*44.44*/ventaServicio/*44.57*/.getHoraIni()),format.raw/*44.70*/("""</td>
								<td style="text-align:left;"><strong>Hr_TER:</strong></td>
						        <td style="text-align:left">"""),_display_(/*46.44*/ventaServicio/*46.57*/.getHoraTer()),format.raw/*46.70*/("""</td>
							</tr>
							
							<tr>
								<td style="text-align:left;"><strong>Lect_INI:</strong></td>
						        <td style="text-align:left">"""),_display_(/*51.44*/ventaServicio/*51.57*/.getLecturaIni()),format.raw/*51.73*/("""</td>
								<td style="text-align:left;"><strong>Lect_TER:</strong></td>
						        <td style="text-align:left">"""),_display_(/*53.44*/ventaServicio/*53.57*/.getLecturaTer()),format.raw/*53.73*/("""</td>
							</tr>
							
							<tr>
								<td style="text-align:left;"><strong><div id="unidad">CANT (*)</div></strong></td>
						        <td style="text-align:left" width="70%" colspan="3">"""),_display_(/*58.68*/ventaServicio/*58.81*/.getCantidad()),format.raw/*58.95*/(""" """),format.raw/*58.96*/("""</td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>DETALLE:</strong></td>
						        <td style="text-align:left" width="70%" colspan="3">"""),_display_(/*62.68*/ventaServicio/*62.81*/.getDetalle()),format.raw/*62.94*/("""</td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>NOTAS:</strong></td>
						        <td style="text-align:left" width="70%" colspan="3">"""),_display_(/*66.68*/ventaServicio/*66.81*/.getObservaciones()),format.raw/*66.100*/("""</td>
							</tr>
							
							<tr>
								<td style="text-align:left;"  colspan="4">
									<input type="hidden" name="firmaPDFoperador" id="firma" value="0">
									FIRMA OPERADOR:<br>
									<img src="data:image/jpeg;base64, """),_display_(/*73.45*/ventaServicio/*73.58*/.getFirmaPDFoperador()),format.raw/*73.80*/("""" />
								</td>
							</tr>
							
							<tr>
								<td style="text-align:left;"   colspan="4">
									FIRMA AUTORIZADA:<br>
									<img src="data:image/jpeg;base64, """),_display_(/*80.45*/ventaServicio/*80.58*/.getFirmaPDFautorizador()),format.raw/*80.83*/("""" />
								</td>
							</tr>
						
							<tr>
								<td colspan="4" style="text-align:center;">
									<button type="button" class="btn btn-sm btn-success" style="font-size: 10px;" onclick="location.href = '/odoAutorizaListarVentasWeb/'">Listo</button>
								</td>
							</tr>
						</thead>
					</table>
			</div>
		</div>

		
	</div>
""")))}),format.raw/*96.2*/("""


	
"""),format.raw/*100.1*/("""<script>


	
	$(document).ready(function() """),format.raw/*104.31*/("""{"""),format.raw/*104.32*/("""
 		"""),format.raw/*105.4*/("""document.getElementById('mostrarmostrar').style.display="block";
	
	"""),format.raw/*107.2*/("""}"""),format.raw/*107.3*/(""" """),format.raw/*107.4*/(""");
	
	
		
		
</script>
	

"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,ventaServicio:tables.VentaServicio): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,ventaServicio)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.VentaServicio) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,ventaServicio) => apply(mapDiccionario,mapPermiso,userMnu,ventaServicio)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuOdoAppWeb/odoAutorizaDetalleVentaWeb.scala.html
                  HASH: fd1a6f9de4492ab7a85067ce579b02a9340a7b65
                  MATRIX: 1048->1|1275->135|1308->143|1324->151|1363->153|1392->156|2051->788|2073->801|2105->812|2300->980|2322->993|2360->1010|2554->1177|2576->1190|2614->1207|2718->1284|2741->1298|2776->1312|2805->1313|2918->1399|2940->1412|2984->1434|3176->1599|3198->1612|3235->1628|3264->1629|3294->1632|3317->1645|3356->1662|3386->1663|3417->1666|3440->1679|3479->1696|3665->1855|3687->1868|3723->1883|3752->1884|3782->1887|3805->1900|3842->1915|4018->2064|4040->2077|4074->2090|4217->2206|4239->2219|4273->2232|4451->2383|4473->2396|4510->2412|4655->2530|4677->2543|4714->2559|4938->2756|4960->2769|4995->2783|5024->2784|5217->2950|5239->2963|5273->2976|5464->3140|5486->3153|5527->3172|5793->3411|5815->3424|5858->3446|6064->3625|6086->3638|6132->3663|6512->4013|6545->4018|6617->4061|6647->4062|6679->4066|6775->4134|6804->4135|6833->4136
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|50->19|50->19|50->19|54->23|54->23|54->23|58->27|58->27|58->27|61->30|61->30|61->30|61->30|62->31|62->31|62->31|66->35|66->35|66->35|66->35|66->35|66->35|66->35|66->35|66->35|66->35|66->35|70->39|70->39|70->39|70->39|70->39|70->39|70->39|75->44|75->44|75->44|77->46|77->46|77->46|82->51|82->51|82->51|84->53|84->53|84->53|89->58|89->58|89->58|89->58|93->62|93->62|93->62|97->66|97->66|97->66|104->73|104->73|104->73|111->80|111->80|111->80|127->96|131->100|135->104|135->104|136->105|138->107|138->107|138->107
                  -- GENERATED --
              */
          