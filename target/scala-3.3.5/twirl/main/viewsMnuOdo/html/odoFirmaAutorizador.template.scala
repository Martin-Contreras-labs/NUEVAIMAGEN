
package viewsMnuOdo.html

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

object odoFirmaAutorizador extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,tables.VentaServicio,String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
ventaServicio: tables.VentaServicio, desdeAAMMDD: String, hastaAAMMDD: String):play.twirl.api.HtmlFormat.Appendable = {
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
				<div align=center><h4 style="color:blue"><strong>FIRMA AUTORIZADOR - REPORT DIARIO</strong></h4></div>
				<form id="grabarFirmaAutorizador" action="/grabarFirmaAutorizador/" method="POST" enctype="multipart/form-data">
					<input type="hidden" name="id_ventaServicio" value=""""),_display_(/*16.59*/ventaServicio/*16.72*/.getId()),format.raw/*16.80*/("""">
					<input type="hidden" name="fechaDesde" value=""""),_display_(/*17.53*/desdeAAMMDD),format.raw/*17.64*/("""">
					<input type="hidden" name="fechaHasta" value=""""),_display_(/*18.53*/hastaAAMMDD),format.raw/*18.64*/("""">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<thead>
							<tr>
								<td style="text-align:left;"><strong>FECHA (*):</strong></td>
						        <td style="text-align:left;" width="70%" colspan="3">"""),_display_(/*23.69*/ventaServicio/*23.82*/.getFecha()),format.raw/*23.93*/("""</td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>USER_MADA:</strong></td>
						        <td style="text-align:left" width="70%" colspan="3">"""),_display_(/*27.68*/ventaServicio/*27.81*/.getNomUserAdam()),format.raw/*27.98*/("""</td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>OPERADOR:</strong></td>
						        <td style="text-align:left" width="70%" colspan="3">"""),_display_(/*31.68*/ventaServicio/*31.81*/.getNomOperador()),format.raw/*31.98*/("""</td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>"""),_display_(/*34.47*/mapDiccionario/*34.61*/.get("BODEGA")),format.raw/*34.75*/(""" """),format.raw/*34.76*/("""(*):</strong></td>
						        <td style="text-align:left" width="70%" colspan="3">"""),_display_(/*35.68*/ventaServicio/*35.81*/.getNomBodegaEmpresa()),format.raw/*35.103*/("""</td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>SERVICIO (*):</strong></td>
								<td style="text-align:left" width="70%" colspan="3">"""),_display_(/*39.62*/ventaServicio/*39.75*/.getNroCotiOdo()),format.raw/*39.91*/(""" """),format.raw/*39.92*/("""- """),_display_(/*39.95*/ventaServicio/*39.108*/.getCodServicio()),format.raw/*39.125*/(""" """),format.raw/*39.126*/("""- """),_display_(/*39.129*/ventaServicio/*39.142*/.getNomServicio()),format.raw/*39.159*/("""</td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>EQUIPO:</strong></td>
								<td style="text-align:left" width="70%" colspan="3">"""),_display_(/*43.62*/ventaServicio/*43.75*/.getCodEquipo()),format.raw/*43.90*/(""" """),format.raw/*43.91*/("""- """),_display_(/*43.94*/ventaServicio/*43.107*/.getNomEquipo()),format.raw/*43.122*/("""</td>
							</tr>
							
							<tr>
								<td style="text-align:left;"><strong>Hr_INI:</strong></td>
						        <td style="text-align:left">"""),_display_(/*48.44*/ventaServicio/*48.57*/.getHoraIni()),format.raw/*48.70*/("""</td>
								<td style="text-align:left;"><strong>Hr_TER:</strong></td>
						        <td style="text-align:left">"""),_display_(/*50.44*/ventaServicio/*50.57*/.getHoraTer()),format.raw/*50.70*/("""</td>
							</tr>
							
							<tr>
								<td style="text-align:left;"><strong>Lect_INI:</strong></td>
						        <td style="text-align:left">"""),_display_(/*55.44*/ventaServicio/*55.57*/.getLecturaIni()),format.raw/*55.73*/("""</td>
								<td style="text-align:left;"><strong>Lect_TER:</strong></td>
						        <td style="text-align:left">"""),_display_(/*57.44*/ventaServicio/*57.57*/.getLecturaTer()),format.raw/*57.73*/("""</td>
							</tr>
							
							<tr>
								<td style="text-align:left;"><strong><div id="unidad">CANT (*)</div></strong></td>
						        <td style="text-align:left" width="70%" colspan="3">"""),_display_(/*62.68*/ventaServicio/*62.81*/.getCantidad()),format.raw/*62.95*/(""" """),format.raw/*62.96*/("""</td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>DETALLE:</strong></td>
						        <td style="text-align:left" width="70%" colspan="3">"""),_display_(/*66.68*/ventaServicio/*66.81*/.getDetalle()),format.raw/*66.94*/("""</td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>NOTAS:</strong></td>
						        <td style="text-align:left" width="70%" colspan="3">"""),_display_(/*70.68*/ventaServicio/*70.81*/.getObservaciones()),format.raw/*70.100*/("""</td>
							</tr>
							
							<tr>
								<td style="text-align:left;"  colspan="4">
									FIRMA OPERADOR:<br>
									<img src="data:image/jpeg;base64, """),_display_(/*76.45*/ventaServicio/*76.58*/.getFirmaPDFoperador()),format.raw/*76.80*/("""" />
								</td>
							</tr>
							
							<tr>
								<td style="text-align:left;"   colspan="4">
									<input type="hidden" name="firmaPDFautorizador" id="firma2" value="0">
									FIRMA AUTORIZADA:<br>
									<div class="wrapper" align="center">
									<canvas id="signature-pad2" class="signature-pad" width=350 height=90 style="border:1px solid #000000;"></canvas>
									</div>
									<button type="button" class="btn btn-sm btn-warning" style="font-size: 10px;" onclick="signaturePad2.clear()">Borrar firma</button>
								</td>
							</tr>
							
							<tr>
								<td colspan="4" style="text-align:center;">
									<button type="button" class="btn btn-sm btn-success" style="font-size: 10px;"onclick="grabar();" >GRABAR
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<button type="button" class="btn btn-sm btn-warning" style="font-size: 10px;" onclick="$('#cancelar').submit()">CANCELAR</button>
								</td>
							</tr>
						</thead>
					</table>
					
					<font style="font-size: 12px;">(*) Campos Obligatorios</font>
				</form>
			</div>
		</div>


		<form id="cancelar" action="/odoListarVentas1/" method="POST">
			<input type="hidden" name="fechaDesde" value=""""),_display_(/*109.51*/desdeAAMMDD),format.raw/*109.62*/("""">
			<input type="hidden" name="fechaHasta" value=""""),_display_(/*110.51*/hastaAAMMDD),format.raw/*110.62*/("""">
		</form>

	</div>
""")))}),format.raw/*114.2*/("""


"""),format.raw/*117.1*/("""<!-- CONFIGURA PAD -->

<script src=""""),_display_(/*119.15*/routes/*119.21*/.Assets.versioned("javascripts/signature_pad.min.js")),format.raw/*119.74*/(""""></script>
	<script>
		let signaturePad2 = new SignaturePad(document.getElementById('signature-pad2'), """),format.raw/*121.83*/("""{"""),format.raw/*121.84*/("""
		  """),format.raw/*122.5*/("""backgroundColor: 'rgba(255, 255, 255, 0)',
		  penColor: 'rgb(0, 0, 0)'
		"""),format.raw/*124.3*/("""}"""),format.raw/*124.4*/(""");
	</script>
	
	<style>
		.wrapper """),format.raw/*128.12*/("""{"""),format.raw/*128.13*/("""
		  """),format.raw/*129.5*/("""position: relative;
		  width: 350px;
		  height: 90px;
		  -moz-user-select: none;
		  -webkit-user-select: none;
		  -ms-user-select: none;
		  user-select: none;
		"""),format.raw/*136.3*/("""}"""),format.raw/*136.4*/("""
		
		"""),format.raw/*138.3*/(""".signature-pad """),format.raw/*138.18*/("""{"""),format.raw/*138.19*/("""
		  """),format.raw/*139.5*/("""position: absolute;
		  left: 0;
		  top: 0;
		  width:350px;
		  height:90px;
		"""),format.raw/*144.3*/("""}"""),format.raw/*144.4*/("""
	"""),format.raw/*145.2*/("""</style>
<!-- FIN PAD -->
	
<script>


	
	$(document).ready(function() """),format.raw/*152.31*/("""{"""),format.raw/*152.32*/("""
 		"""),format.raw/*153.4*/("""document.getElementById('mostrarmostrar').style.display="block";
		//var img = document.createElement('img');
		//img.src = 'data:image/jpeg;base64,' + btoa('your-binary-data');
		//img.src = 'data:image/jpeg;base64,' + '"""),_display_(/*156.45*/ventaServicio/*156.58*/.getFirmaPDFautorizador()),format.raw/*156.83*/("""';
		//document.body.appendChild(img);
	
	"""),format.raw/*159.2*/("""}"""),format.raw/*159.3*/(""" """),format.raw/*159.4*/(""");
	
	
	
	
	function grabar()"""),format.raw/*164.19*/("""{"""),format.raw/*164.20*/("""
			"""),format.raw/*165.4*/("""let data = signaturePad2.toDataURL('image/png');
			let encodedImage = data.split(",");
			$("#firma2").val(encodedImage[1]);
			$("#grabarFirmaAutorizador").submit();
	"""),format.raw/*169.2*/("""}"""),format.raw/*169.3*/("""
	
	
		
		
		
"""),format.raw/*175.1*/("""</script>
	

"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,ventaServicio:tables.VentaServicio,desdeAAMMDD:String,hastaAAMMDD:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,ventaServicio,desdeAAMMDD,hastaAAMMDD)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.VentaServicio,String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,ventaServicio,desdeAAMMDD,hastaAAMMDD) => apply(mapDiccionario,mapPermiso,userMnu,ventaServicio,desdeAAMMDD,hastaAAMMDD)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuOdo/odoFirmaAutorizador.scala.html
                  HASH: 2a42f191439defd377b061dccce384a7e08ade74
                  MATRIX: 1049->1|1318->177|1351->185|1367->193|1406->195|1434->197|2035->771|2057->784|2086->792|2168->847|2200->858|2282->913|2314->924|2587->1170|2609->1183|2641->1194|2836->1362|2858->1375|2896->1392|3090->1559|3112->1572|3150->1589|3254->1666|3277->1680|3312->1694|3341->1695|3454->1781|3476->1794|3520->1816|3712->1981|3734->1994|3771->2010|3800->2011|3830->2014|3853->2027|3892->2044|3922->2045|3953->2048|3976->2061|4015->2078|4201->2237|4223->2250|4259->2265|4288->2266|4318->2269|4341->2282|4378->2297|4554->2446|4576->2459|4610->2472|4753->2588|4775->2601|4809->2614|4987->2765|5009->2778|5046->2794|5191->2912|5213->2925|5250->2941|5474->3138|5496->3151|5531->3165|5560->3166|5753->3332|5775->3345|5809->3358|6000->3522|6022->3535|6063->3554|6253->3717|6275->3730|6318->3752|7653->5059|7686->5070|7767->5123|7800->5134|7854->5157|7885->5160|7951->5198|7967->5204|8042->5257|8175->5361|8205->5362|8238->5367|8340->5441|8369->5442|8434->5478|8464->5479|8497->5484|8692->5651|8721->5652|8755->5658|8799->5673|8829->5674|8862->5679|8971->5760|9000->5761|9030->5763|9130->5834|9160->5835|9192->5839|9442->6061|9465->6074|9512->6099|9582->6141|9611->6142|9640->6143|9698->6172|9728->6173|9760->6177|9957->6346|9986->6347|10028->6361
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|47->16|47->16|47->16|48->17|48->17|49->18|49->18|54->23|54->23|54->23|58->27|58->27|58->27|62->31|62->31|62->31|65->34|65->34|65->34|65->34|66->35|66->35|66->35|70->39|70->39|70->39|70->39|70->39|70->39|70->39|70->39|70->39|70->39|70->39|74->43|74->43|74->43|74->43|74->43|74->43|74->43|79->48|79->48|79->48|81->50|81->50|81->50|86->55|86->55|86->55|88->57|88->57|88->57|93->62|93->62|93->62|93->62|97->66|97->66|97->66|101->70|101->70|101->70|107->76|107->76|107->76|140->109|140->109|141->110|141->110|145->114|148->117|150->119|150->119|150->119|152->121|152->121|153->122|155->124|155->124|159->128|159->128|160->129|167->136|167->136|169->138|169->138|169->138|170->139|175->144|175->144|176->145|183->152|183->152|184->153|187->156|187->156|187->156|190->159|190->159|190->159|195->164|195->164|196->165|200->169|200->169|206->175
                  -- GENERATED --
              */
          