
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

object odoDetalleVentaWeb extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,tables.VentaServicio,play.twirl.api.HtmlFormat.Appendable] {

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
									<button type="button" class="btn btn-sm btn-success" style="font-size: 10px;" onclick="location.href = '/odoListarVentasWeb/0'">Listo</button>
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
                  SOURCE: app/viewsMnuOdoAppWeb/odoDetalleVentaWeb.scala.html
                  HASH: 7bcd1cba8275b63f84d8aee9865cf19451027716
                  MATRIX: 1040->1|1267->135|1300->143|1316->151|1355->153|1384->156|2043->788|2065->801|2097->812|2292->980|2314->993|2352->1010|2546->1177|2568->1190|2606->1207|2710->1284|2733->1298|2768->1312|2797->1313|2910->1399|2932->1412|2976->1434|3168->1599|3190->1612|3227->1628|3256->1629|3286->1632|3309->1645|3348->1662|3378->1663|3409->1666|3432->1679|3471->1696|3657->1855|3679->1868|3715->1883|3744->1884|3774->1887|3797->1900|3834->1915|4010->2064|4032->2077|4066->2090|4209->2206|4231->2219|4265->2232|4443->2383|4465->2396|4502->2412|4647->2530|4669->2543|4706->2559|4930->2756|4952->2769|4987->2783|5016->2784|5209->2950|5231->2963|5265->2976|5456->3140|5478->3153|5519->3172|5785->3411|5807->3424|5850->3446|6056->3625|6078->3638|6124->3663|6497->4006|6530->4011|6602->4054|6632->4055|6664->4059|6760->4127|6789->4128|6818->4129
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|50->19|50->19|50->19|54->23|54->23|54->23|58->27|58->27|58->27|61->30|61->30|61->30|61->30|62->31|62->31|62->31|66->35|66->35|66->35|66->35|66->35|66->35|66->35|66->35|66->35|66->35|66->35|70->39|70->39|70->39|70->39|70->39|70->39|70->39|75->44|75->44|75->44|77->46|77->46|77->46|82->51|82->51|82->51|84->53|84->53|84->53|89->58|89->58|89->58|89->58|93->62|93->62|93->62|97->66|97->66|97->66|104->73|104->73|104->73|111->80|111->80|111->80|127->96|131->100|135->104|135->104|136->105|138->107|138->107|138->107
                  -- GENERATED --
              */
          