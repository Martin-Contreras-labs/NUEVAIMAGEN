
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

object odoAutorizaFirmaWeb extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,tables.VentaServicio,play.twirl.api.HtmlFormat.Appendable] {

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
				<form id="grabarFirmaAutorizador" action="/grabarAutorizaFirmaWeb/" method="POST" enctype="multipart/form-data">
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
									<button type="button" class="btn btn-sm btn-warning" style="font-size: 10px;" onclick="location.href = '/odoAutorizaListarVentasWeb/'">CANCELAR</button>
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
                  SOURCE: app/viewsMnuOdoAppWeb/odoAutorizaFirmaWeb.scala.html
                  HASH: ecf1794e841001d62dcc1cefe391a2de503be615
                  MATRIX: 1041->1|1268->135|1301->143|1317->151|1356->153|1384->155|1985->729|2007->742|2036->750|2309->996|2331->1009|2363->1020|2558->1188|2580->1201|2618->1218|2812->1385|2834->1398|2872->1415|2976->1492|2999->1506|3034->1520|3063->1521|3176->1607|3198->1620|3242->1642|3434->1807|3456->1820|3493->1836|3522->1837|3552->1840|3575->1853|3614->1870|3644->1871|3675->1874|3698->1887|3737->1904|3923->2063|3945->2076|3981->2091|4010->2092|4040->2095|4063->2108|4100->2123|4276->2272|4298->2285|4332->2298|4475->2414|4497->2427|4531->2440|4709->2591|4731->2604|4768->2620|4913->2738|4935->2751|4972->2767|5196->2964|5218->2977|5253->2991|5282->2992|5475->3158|5497->3171|5531->3184|5722->3348|5744->3361|5785->3380|5975->3543|5997->3556|6040->3578|7297->4804|7328->4807|7394->4845|7410->4851|7485->4904|7618->5008|7648->5009|7681->5014|7783->5088|7812->5089|7877->5125|7907->5126|7940->5131|8135->5298|8164->5299|8198->5305|8242->5320|8272->5321|8305->5326|8414->5407|8443->5408|8473->5410|8573->5481|8603->5482|8635->5486|8885->5708|8908->5721|8955->5746|9025->5788|9054->5789|9083->5790|9141->5819|9171->5820|9203->5824|9400->5993|9429->5994|9471->6008
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|47->16|47->16|47->16|52->21|52->21|52->21|56->25|56->25|56->25|60->29|60->29|60->29|63->32|63->32|63->32|63->32|64->33|64->33|64->33|68->37|68->37|68->37|68->37|68->37|68->37|68->37|68->37|68->37|68->37|68->37|72->41|72->41|72->41|72->41|72->41|72->41|72->41|77->46|77->46|77->46|79->48|79->48|79->48|84->53|84->53|84->53|86->55|86->55|86->55|91->60|91->60|91->60|91->60|95->64|95->64|95->64|99->68|99->68|99->68|105->74|105->74|105->74|138->107|141->110|143->112|143->112|143->112|145->114|145->114|146->115|148->117|148->117|152->121|152->121|153->122|160->129|160->129|162->131|162->131|162->131|163->132|168->137|168->137|169->138|176->145|176->145|177->146|180->149|180->149|180->149|183->152|183->152|183->152|188->157|188->157|189->158|193->162|193->162|199->168
                  -- GENERATED --
              */
          