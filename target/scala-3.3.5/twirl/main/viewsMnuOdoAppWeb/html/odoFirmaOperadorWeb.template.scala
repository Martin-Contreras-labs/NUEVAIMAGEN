
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

object odoFirmaOperadorWeb extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,tables.VentaServicio,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
ventaServicio: tables.VentaServicio):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),format.raw/*7.1*/("""<!-- menues(mapDiccionario, mapPermiso, userMnu, " ") -->
	<div id="mostrarmostrar" style="display:none;">
		<!-- barraTitulo(mapDiccionario, "INGRESO DE REPORT DIARIO", "") -->
		
		<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
				<br>
				<div align=center><h4 style="color:blue"><strong>FIRMA OPERADOR - REPORT DIARIO</strong></h4></div>
				<form id="grabarFirmaOperador" action="/grabarFirmaOperadorWeb/" method="POST" enctype="multipart/form-data">
					<input type="hidden" name="id_ventaServicio" value=""""),_display_(/*16.59*/ventaServicio/*16.72*/.getId()),format.raw/*16.80*/("""">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<thead>
							<tr>
								<td style="text-align:left;"><strong>FECHA (*):</strong></td>
						        <td style="text-align:left;" width="70%" colspan="3">"""),_display_(/*21.69*/ventaServicio/*21.82*/.getFecha()),format.raw/*21.93*/("""</td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>USER_MADA:</strong></td>
						        <td style="text-align:left" width="70%" colspan="3">"""),_display_(/*25.68*/ventaServicio/*25.81*/.getNomUserAdam()),format.raw/*25.98*/("""</td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>OPERADOR:</strong></td>
						        <td style="text-align:left" width="70%" colspan="3">"""),_display_(/*29.68*/ventaServicio/*29.81*/.getNomOperador()),format.raw/*29.98*/("""</td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>"""),_display_(/*32.47*/mapDiccionario/*32.61*/.get("BODEGA")),format.raw/*32.75*/(""" """),format.raw/*32.76*/("""(*):</strong></td>
						        <td style="text-align:left" width="70%" colspan="3">"""),_display_(/*33.68*/ventaServicio/*33.81*/.getNomBodegaEmpresa()),format.raw/*33.103*/("""</td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>SERVICIO (*):</strong></td>
								<td style="text-align:left" width="70%" colspan="3">"""),_display_(/*37.62*/ventaServicio/*37.75*/.getNroCotiOdo()),format.raw/*37.91*/(""" """),format.raw/*37.92*/("""- """),_display_(/*37.95*/ventaServicio/*37.108*/.getCodServicio()),format.raw/*37.125*/(""" """),format.raw/*37.126*/("""- """),_display_(/*37.129*/ventaServicio/*37.142*/.getNomServicio()),format.raw/*37.159*/("""</td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>EQUIPO:</strong></td>
								<td style="text-align:left" width="70%" colspan="3">"""),_display_(/*41.62*/ventaServicio/*41.75*/.getCodEquipo()),format.raw/*41.90*/(""" """),format.raw/*41.91*/("""- """),_display_(/*41.94*/ventaServicio/*41.107*/.getNomEquipo()),format.raw/*41.122*/("""</td>
							</tr>
							
							<tr>
								<td style="text-align:left;"><strong>Hr_INI:</strong></td>
						        <td style="text-align:left">"""),_display_(/*46.44*/ventaServicio/*46.57*/.getHoraIni()),format.raw/*46.70*/("""</td>
								<td style="text-align:left;"><strong>Hr_TER:</strong></td>
						        <td style="text-align:left">"""),_display_(/*48.44*/ventaServicio/*48.57*/.getHoraTer()),format.raw/*48.70*/("""</td>
							</tr>
							
							<tr>
								<td style="text-align:left;"><strong>Lect_INI:</strong></td>
						        <td style="text-align:left">"""),_display_(/*53.44*/ventaServicio/*53.57*/.getLecturaIni()),format.raw/*53.73*/("""</td>
								<td style="text-align:left;"><strong>Lect_TER:</strong></td>
						        <td style="text-align:left">"""),_display_(/*55.44*/ventaServicio/*55.57*/.getLecturaTer()),format.raw/*55.73*/("""</td>
							</tr>
							
							<tr>
								<td style="text-align:left;"><strong><div id="unidad">CANT (*)</div></strong></td>
						        <td style="text-align:left" width="70%" colspan="3">"""),_display_(/*60.68*/ventaServicio/*60.81*/.getCantidad()),format.raw/*60.95*/(""" """),format.raw/*60.96*/("""</td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>DETALLE:</strong></td>
						        <td style="text-align:left" width="70%" colspan="3">"""),_display_(/*64.68*/ventaServicio/*64.81*/.getDetalle()),format.raw/*64.94*/("""</td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>NOTAS:</strong></td>
						        <td style="text-align:left" width="70%" colspan="3">"""),_display_(/*68.68*/ventaServicio/*68.81*/.getObservaciones()),format.raw/*68.100*/("""</td>
							</tr>
							
							<tr>
								<td style="text-align:left;"  colspan="4">
									<input type="hidden" name="firmaPDFoperador" id="firma" value="0">
									FIRMA OPERADOR:<br>
									<div class="wrapper" align="center">
									<canvas id="signature-pad" class="signature-pad" width=350 height=90 style="border:1px solid #000000;"></canvas>
									</div>
									<button type="button" class="btn btn-sm btn-warning" style="font-size: 10px;" onclick="signaturePad.clear()">Borrar firma</button>
								</td>
							</tr>
							
							<tr>
								<td style="text-align:left;"   colspan="4">
									FIRMA AUTORIZADA:<br>
									<img src="data:image/jpeg;base64, """),_display_(/*85.45*/ventaServicio/*85.58*/.getFirmaPDFautorizador()),format.raw/*85.83*/("""" />
								</td>
							</tr>
						
							<tr>
								<td colspan="4" style="text-align:center;">
									<button type="button" class="btn btn-sm btn-success" style="font-size: 10px;"onclick="grabar();" >GRABAR
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<button type="button" class="btn btn-sm btn-warning" style="font-size: 10px;" onclick="location.href = '/odoListarVentasWeb/0'">CANCELAR</button>
								</td>
							</tr>
						</thead>
					</table>
					
					<font style="font-size: 12px;">(*) Campos Obligatorios</font>
				</form>
			</div>
		</div>

		
	</div>
""")))}),format.raw/*107.2*/("""


"""),format.raw/*110.1*/("""<!-- CONFIGURA PAD -->

<script src=""""),_display_(/*112.15*/routes/*112.21*/.Assets.versioned("javascripts/signature_pad.min.js")),format.raw/*112.74*/(""""></script>
	<script>
		let signaturePad = new SignaturePad(document.getElementById('signature-pad'), """),format.raw/*114.81*/("""{"""),format.raw/*114.82*/("""
		  """),format.raw/*115.5*/("""backgroundColor: 'rgba(255, 255, 255, 0)',
		  penColor: 'rgb(0, 0, 0)'
		"""),format.raw/*117.3*/("""}"""),format.raw/*117.4*/(""");
	</script>
	
	<style>
		.wrapper """),format.raw/*121.12*/("""{"""),format.raw/*121.13*/("""
		  """),format.raw/*122.5*/("""position: relative;
		  width: 350px;
		  height: 90px;
		  -moz-user-select: none;
		  -webkit-user-select: none;
		  -ms-user-select: none;
		  user-select: none;
		"""),format.raw/*129.3*/("""}"""),format.raw/*129.4*/("""

		"""),format.raw/*131.3*/(""".signature-pad """),format.raw/*131.18*/("""{"""),format.raw/*131.19*/("""
		  """),format.raw/*132.5*/("""position: absolute;
		  left: 0;
		  top: 0;
		  width:350px;
		  height:90px;
		"""),format.raw/*137.3*/("""}"""),format.raw/*137.4*/("""
	"""),format.raw/*138.2*/("""</style>
<!-- FIN PAD -->
	
<script>

	$(document).ready(function() """),format.raw/*143.31*/("""{"""),format.raw/*143.32*/("""
 		"""),format.raw/*144.4*/("""document.getElementById('mostrarmostrar').style.display="block";
	
	"""),format.raw/*146.2*/("""}"""),format.raw/*146.3*/(""" """),format.raw/*146.4*/(""");
	
	function grabar()"""),format.raw/*148.19*/("""{"""),format.raw/*148.20*/("""
			"""),format.raw/*149.4*/("""let data = signaturePad.toDataURL('image/png');
			let encodedImage = data.split(",");
			$("#firma").val(encodedImage[1]);
			$("#grabarFirmaOperador").submit();
	"""),format.raw/*153.2*/("""}"""),format.raw/*153.3*/("""
		
"""),format.raw/*155.1*/("""</script>
	

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
                  SOURCE: app/viewsMnuOdoAppWeb/odoFirmaOperadorWeb.scala.html
                  HASH: e67d5878c099bf74b27051fde2050ae768a66948
                  MATRIX: 1041->1|1268->135|1301->143|1317->151|1356->153|1384->155|1979->723|2001->736|2030->744|2303->990|2325->1003|2357->1014|2552->1182|2574->1195|2612->1212|2806->1379|2828->1392|2866->1409|2970->1486|2993->1500|3028->1514|3057->1515|3170->1601|3192->1614|3236->1636|3428->1801|3450->1814|3487->1830|3516->1831|3546->1834|3569->1847|3608->1864|3638->1865|3669->1868|3692->1881|3731->1898|3917->2057|3939->2070|3975->2085|4004->2086|4034->2089|4057->2102|4094->2117|4270->2266|4292->2279|4326->2292|4469->2408|4491->2421|4525->2434|4703->2585|4725->2598|4762->2614|4907->2732|4929->2745|4966->2761|5190->2958|5212->2971|5247->2985|5276->2986|5469->3152|5491->3165|5525->3178|5716->3342|5738->3355|5779->3374|6499->4067|6521->4080|6567->4105|7280->4787|7311->4790|7377->4828|7393->4834|7468->4887|7599->4989|7629->4990|7662->4995|7764->5069|7793->5070|7858->5106|7888->5107|7921->5112|8116->5279|8145->5280|8177->5284|8221->5299|8251->5300|8284->5305|8393->5386|8422->5387|8452->5389|8549->5457|8579->5458|8611->5462|8707->5530|8736->5531|8765->5532|8817->5555|8847->5556|8879->5560|9071->5724|9100->5725|9132->5729
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|47->16|47->16|47->16|52->21|52->21|52->21|56->25|56->25|56->25|60->29|60->29|60->29|63->32|63->32|63->32|63->32|64->33|64->33|64->33|68->37|68->37|68->37|68->37|68->37|68->37|68->37|68->37|68->37|68->37|68->37|72->41|72->41|72->41|72->41|72->41|72->41|72->41|77->46|77->46|77->46|79->48|79->48|79->48|84->53|84->53|84->53|86->55|86->55|86->55|91->60|91->60|91->60|91->60|95->64|95->64|95->64|99->68|99->68|99->68|116->85|116->85|116->85|138->107|141->110|143->112|143->112|143->112|145->114|145->114|146->115|148->117|148->117|152->121|152->121|153->122|160->129|160->129|162->131|162->131|162->131|163->132|168->137|168->137|169->138|174->143|174->143|175->144|177->146|177->146|177->146|179->148|179->148|180->149|184->153|184->153|186->155
                  -- GENERATED --
              */
          