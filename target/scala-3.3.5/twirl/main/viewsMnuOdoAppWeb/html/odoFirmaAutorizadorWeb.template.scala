
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

object odoFirmaAutorizadorWeb extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,tables.VentaServicio,play.twirl.api.HtmlFormat.Appendable] {

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
				<div align=center><h4 style="color:blue"><strong>FIRMA AUTORIZADOR - REPORT DIARIO</strong></h4></div>
				<form id="grabarFirmaAutorizador" action="/grabarFirmaAutorizadorWeb/" method="POST" enctype="multipart/form-data">
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
									FIRMA OPERADOR:<br>
									<img src="data:image/jpeg;base64, """),_display_(/*74.45*/ventaServicio/*74.58*/.getFirmaPDFoperador()),format.raw/*74.80*/("""" />
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
		let signaturePad2 = new SignaturePad(document.getElementById('signature-pad2'), """),format.raw/*114.83*/("""{"""),format.raw/*114.84*/("""
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


	
	$(document).ready(function() """),format.raw/*145.31*/("""{"""),format.raw/*145.32*/("""
 		"""),format.raw/*146.4*/("""document.getElementById('mostrarmostrar').style.display="block";
		//var img = document.createElement('img');
		//img.src = 'data:image/jpeg;base64,' + btoa('your-binary-data');
		//img.src = 'data:image/jpeg;base64,' + '"""),_display_(/*149.45*/ventaServicio/*149.58*/.getFirmaPDFautorizador()),format.raw/*149.83*/("""';
		//document.body.appendChild(img);
	
	"""),format.raw/*152.2*/("""}"""),format.raw/*152.3*/(""" """),format.raw/*152.4*/(""");
	
	
	
	
	function grabar()"""),format.raw/*157.19*/("""{"""),format.raw/*157.20*/("""
			"""),format.raw/*158.4*/("""let data = signaturePad2.toDataURL('image/png');
			let encodedImage = data.split(",");
			$("#firma2").val(encodedImage[1]);
			$("#grabarFirmaAutorizador").submit();
	"""),format.raw/*162.2*/("""}"""),format.raw/*162.3*/("""
	
	
		
		
		
"""),format.raw/*168.1*/("""</script>
	

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
                  SOURCE: app/viewsMnuOdoAppWeb/odoFirmaAutorizadorWeb.scala.html
                  HASH: 262c2647e649b974f9dabda55ead06f6c1f2dd30
                  MATRIX: 1044->1|1271->135|1304->143|1320->151|1359->153|1387->155|1991->732|2013->745|2042->753|2315->999|2337->1012|2369->1023|2564->1191|2586->1204|2624->1221|2818->1388|2840->1401|2878->1418|2982->1495|3005->1509|3040->1523|3069->1524|3182->1610|3204->1623|3248->1645|3440->1810|3462->1823|3499->1839|3528->1840|3558->1843|3581->1856|3620->1873|3650->1874|3681->1877|3704->1890|3743->1907|3929->2066|3951->2079|3987->2094|4016->2095|4046->2098|4069->2111|4106->2126|4282->2275|4304->2288|4338->2301|4481->2417|4503->2430|4537->2443|4715->2594|4737->2607|4774->2623|4919->2741|4941->2754|4978->2770|5202->2967|5224->2980|5259->2994|5288->2995|5481->3161|5503->3174|5537->3187|5728->3351|5750->3364|5791->3383|5981->3546|6003->3559|6046->3581|7296->4800|7327->4803|7393->4841|7409->4847|7484->4900|7617->5004|7647->5005|7680->5010|7782->5084|7811->5085|7876->5121|7906->5122|7939->5127|8134->5294|8163->5295|8197->5301|8241->5316|8271->5317|8304->5322|8413->5403|8442->5404|8472->5406|8572->5477|8602->5478|8634->5482|8884->5704|8907->5717|8954->5742|9024->5784|9053->5785|9082->5786|9140->5815|9170->5816|9202->5820|9399->5989|9428->5990|9470->6004
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|47->16|47->16|47->16|52->21|52->21|52->21|56->25|56->25|56->25|60->29|60->29|60->29|63->32|63->32|63->32|63->32|64->33|64->33|64->33|68->37|68->37|68->37|68->37|68->37|68->37|68->37|68->37|68->37|68->37|68->37|72->41|72->41|72->41|72->41|72->41|72->41|72->41|77->46|77->46|77->46|79->48|79->48|79->48|84->53|84->53|84->53|86->55|86->55|86->55|91->60|91->60|91->60|91->60|95->64|95->64|95->64|99->68|99->68|99->68|105->74|105->74|105->74|138->107|141->110|143->112|143->112|143->112|145->114|145->114|146->115|148->117|148->117|152->121|152->121|153->122|160->129|160->129|162->131|162->131|162->131|163->132|168->137|168->137|169->138|176->145|176->145|177->146|180->149|180->149|180->149|183->152|183->152|183->152|188->157|188->157|189->158|193->162|193->162|199->168
                  -- GENERATED --
              */
          