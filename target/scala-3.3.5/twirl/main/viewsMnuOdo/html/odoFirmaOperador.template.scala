
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

object odoFirmaOperador extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,tables.VentaServicio,String,String,play.twirl.api.HtmlFormat.Appendable] {

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
				<div align=center><h4 style="color:blue"><strong>FIRMA OPERADOR - REPORT DIARIO</strong></h4></div>
				<form id="grabarFirmaOperador" action="/grabarFirmaOperador/" method="POST" enctype="multipart/form-data">
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
									<img src="data:image/jpeg;base64, """),_display_(/*87.45*/ventaServicio/*87.58*/.getFirmaPDFautorizador()),format.raw/*87.83*/("""" />
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
			<input type="hidden" name="fechaDesde" value=""""),_display_(/*108.51*/desdeAAMMDD),format.raw/*108.62*/("""">
			<input type="hidden" name="fechaHasta" value=""""),_display_(/*109.51*/hastaAAMMDD),format.raw/*109.62*/("""">
		</form>
		
	</div>
""")))}),format.raw/*113.2*/("""


"""),format.raw/*116.1*/("""<!-- CONFIGURA PAD -->

<script src=""""),_display_(/*118.15*/routes/*118.21*/.Assets.versioned("javascripts/signature_pad.min.js")),format.raw/*118.74*/(""""></script>
	<script>
		let signaturePad = new SignaturePad(document.getElementById('signature-pad'), """),format.raw/*120.81*/("""{"""),format.raw/*120.82*/("""
		  """),format.raw/*121.5*/("""backgroundColor: 'rgba(255, 255, 255, 0)',
		  penColor: 'rgb(0, 0, 0)'
		"""),format.raw/*123.3*/("""}"""),format.raw/*123.4*/(""");
	</script>
	
	<style>
		.wrapper """),format.raw/*127.12*/("""{"""),format.raw/*127.13*/("""
		  """),format.raw/*128.5*/("""position: relative;
		  width: 350px;
		  height: 90px;
		  -moz-user-select: none;
		  -webkit-user-select: none;
		  -ms-user-select: none;
		  user-select: none;
		"""),format.raw/*135.3*/("""}"""),format.raw/*135.4*/("""

		"""),format.raw/*137.3*/(""".signature-pad """),format.raw/*137.18*/("""{"""),format.raw/*137.19*/("""
		  """),format.raw/*138.5*/("""position: absolute;
		  left: 0;
		  top: 0;
		  width:350px;
		  height:90px;
		"""),format.raw/*143.3*/("""}"""),format.raw/*143.4*/("""
	"""),format.raw/*144.2*/("""</style>
<!-- FIN PAD -->
	
<script>

	$(document).ready(function() """),format.raw/*149.31*/("""{"""),format.raw/*149.32*/("""
 		"""),format.raw/*150.4*/("""document.getElementById('mostrarmostrar').style.display="block";
	
	"""),format.raw/*152.2*/("""}"""),format.raw/*152.3*/(""" """),format.raw/*152.4*/(""");
	
	function grabar()"""),format.raw/*154.19*/("""{"""),format.raw/*154.20*/("""
			"""),format.raw/*155.4*/("""let data = signaturePad.toDataURL('image/png');
			let encodedImage = data.split(",");
			$("#firma").val(encodedImage[1]);
			$("#grabarFirmaOperador").submit();
	"""),format.raw/*159.2*/("""}"""),format.raw/*159.3*/("""
		
"""),format.raw/*161.1*/("""</script>
	

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
                  SOURCE: app/viewsMnuOdo/odoFirmaOperador.scala.html
                  HASH: 09cd0e105d7ad79cc154a5870fff26d4aa72e79c
                  MATRIX: 1046->1|1315->177|1348->185|1364->193|1403->195|1431->197|2023->762|2045->775|2074->783|2156->838|2188->849|2270->904|2302->915|2575->1161|2597->1174|2629->1185|2824->1353|2846->1366|2884->1383|3078->1550|3100->1563|3138->1580|3242->1657|3265->1671|3300->1685|3329->1686|3442->1772|3464->1785|3508->1807|3700->1972|3722->1985|3759->2001|3788->2002|3818->2005|3841->2018|3880->2035|3910->2036|3941->2039|3964->2052|4003->2069|4189->2228|4211->2241|4247->2256|4276->2257|4306->2260|4329->2273|4366->2288|4542->2437|4564->2450|4598->2463|4741->2579|4763->2592|4797->2605|4975->2756|4997->2769|5034->2785|5179->2903|5201->2916|5238->2932|5462->3129|5484->3142|5519->3156|5548->3157|5741->3323|5763->3336|5797->3349|5988->3513|6010->3526|6051->3545|6771->4238|6793->4251|6839->4276|7638->5047|7671->5058|7752->5111|7785->5122|7841->5147|7872->5150|7938->5188|7954->5194|8029->5247|8160->5349|8190->5350|8223->5355|8325->5429|8354->5430|8419->5466|8449->5467|8482->5472|8677->5639|8706->5640|8738->5644|8782->5659|8812->5660|8845->5665|8954->5746|8983->5747|9013->5749|9110->5817|9140->5818|9172->5822|9268->5890|9297->5891|9326->5892|9378->5915|9408->5916|9440->5920|9632->6084|9661->6085|9693->6089
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|47->16|47->16|47->16|48->17|48->17|49->18|49->18|54->23|54->23|54->23|58->27|58->27|58->27|62->31|62->31|62->31|65->34|65->34|65->34|65->34|66->35|66->35|66->35|70->39|70->39|70->39|70->39|70->39|70->39|70->39|70->39|70->39|70->39|70->39|74->43|74->43|74->43|74->43|74->43|74->43|74->43|79->48|79->48|79->48|81->50|81->50|81->50|86->55|86->55|86->55|88->57|88->57|88->57|93->62|93->62|93->62|93->62|97->66|97->66|97->66|101->70|101->70|101->70|118->87|118->87|118->87|139->108|139->108|140->109|140->109|144->113|147->116|149->118|149->118|149->118|151->120|151->120|152->121|154->123|154->123|158->127|158->127|159->128|166->135|166->135|168->137|168->137|168->137|169->138|174->143|174->143|175->144|180->149|180->149|181->150|183->152|183->152|183->152|185->154|185->154|186->155|190->159|190->159|192->161
                  -- GENERATED --
              */
          