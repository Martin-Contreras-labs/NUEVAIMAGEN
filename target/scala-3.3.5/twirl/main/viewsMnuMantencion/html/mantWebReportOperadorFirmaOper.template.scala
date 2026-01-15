
package viewsMnuMantencion.html

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

object mantWebReportOperadorFirmaOper extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],tables.MantTransacReport,String,String,Long,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], mantTransacReport: tables.MantTransacReport, desdeAAMMDD: String, hastaAAMMDD: String, id_equipo: Long):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""


"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""


	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">

		
		<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
				<br>
				<form id="grabarFirmaOperador" action="/routes3/mantWebGrabarFirmaOperador/" method="POST" enctype="multipart/form-data">
					<input type="hidden" name="id_mantTransacReport" value=""""),_display_(/*15.63*/mantTransacReport/*15.80*/.getId()),format.raw/*15.88*/("""">
					<input type="hidden" name="fechaDesde" value=""""),_display_(/*16.53*/desdeAAMMDD),format.raw/*16.64*/("""">
					<input type="hidden" name="fechaHasta" value=""""),_display_(/*17.53*/hastaAAMMDD),format.raw/*17.64*/("""">
					<input type="hidden" name="id_equipo" value=""""),_display_(/*18.52*/id_equipo),format.raw/*18.61*/("""">

					<table class="table table-sm table-bordered table-condensed table-fluid">
						<thead>
							<tr>
								<td style="text-align:left;"><strong>ID:</strong></td>
						        <td style="text-align:left;" width="70%" colspan="4">
						        	<input style="max-width:150px" type="text" class="form-control"
										value=""""),_display_(/*26.19*/mantTransacReport/*26.36*/.getId()),format.raw/*26.44*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>FECHA:</strong></td>
						        <td style="text-align:left;" width="70%" colspan="3">
						        	<input style="max-width:150px" type="date" class="form-control form-control-sm" 
										value=""""),_display_(/*34.19*/mantTransacReport/*34.36*/.getFecha()),format.raw/*34.47*/("""" 
										readonly>
						        </td>
						        <td style="text-align:center;" width="70px">
									"""),_display_(if( ! mantTransacReport.getDocAnexo().equals("0"))/*38.61*/{_display_(Seq[Any](format.raw/*38.62*/("""
										"""),format.raw/*39.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*39.52*/mantTransacReport/*39.69*/.getDocAnexo()),format.raw/*39.83*/("""')">
											<kbd style="background-color: #7F8C8D">docum</kbd>
										</a>
									""")))} else {null} ),format.raw/*42.11*/("""
								"""),format.raw/*43.9*/("""</td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>USER_MADA:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
						        	<input type="text" class="form-control"
										value=""""),_display_(/*49.19*/mantTransacReport/*49.36*/.getUserNameAdam()),format.raw/*49.54*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>PERFIL:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*57.19*/mantTransacReport/*57.36*/.getNameActorPersonal()),format.raw/*57.59*/(""""
										readonly>
						        </td>
							</tr>
							</thead>
					</table>
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<thead>
							<tr>
								<td style="text-align:left;"><strong>OPERADOR:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*69.19*/mantTransacReport/*69.36*/.getFullNameOperador()),format.raw/*69.58*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>ORIGEN/TIPO:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*77.19*/mantTransacReport/*77.36*/.getNameTipoPersonal()),format.raw/*77.58*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>EQUIPO:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*85.19*/mantTransacReport/*85.36*/.getCodigoEquipo()),format.raw/*85.54*/(""" """),format.raw/*85.55*/("""- """),_display_(/*85.58*/mantTransacReport/*85.75*/.getNameEquipo()),format.raw/*85.91*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>"""),_display_(/*90.47*/mapDiccionario/*90.61*/.get("BODEGA")),format.raw/*90.75*/("""/PROYECTO:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*93.19*/mantTransacReport/*93.36*/.getNameBodega()),format.raw/*93.52*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>TIPO """),_display_(/*98.52*/mapDiccionario/*98.66*/.get("BODEGA")),format.raw/*98.80*/("""/PROYECTO:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*101.19*/mantTransacReport/*101.36*/.getNameTipoBodega()),format.raw/*101.56*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>ESTADO EN SITIO:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*109.19*/mantTransacReport/*109.36*/.getId_mantEstadoEnObra()),format.raw/*109.61*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>Hr_INI:</strong></td>
						        <td style="text-align:left">
									"""),_display_(if( ! mantTransacReport.getHoraIni().equals("00:00:00") && mantTransacReport.getHoraFin().equals("00:00:00"))/*116.120*/{_display_(Seq[Any](format.raw/*116.121*/("""
										"""),format.raw/*117.11*/("""<input type="time" class="form-control form-control-sm left" style="max-width:100px"
											value=""""),_display_(/*118.20*/mantTransacReport/*118.37*/.getHoraIni()),format.raw/*118.50*/(""""
											readonly>
									""")))}else/*120.15*/{_display_(Seq[Any](format.raw/*120.16*/("""
										"""),format.raw/*121.11*/("""<input type="time" class="form-control form-control-sm left" style="max-width:100px"
											readonly>
									""")))}),format.raw/*123.11*/("""
						        """),format.raw/*124.15*/("""</td>
								<td style="text-align:left;"><strong>Hr_TER:</strong></td>
						        <td style="text-align:left" colspan="2">
									"""),_display_(if( ! mantTransacReport.getHoraIni().equals("00:00:00") && mantTransacReport.getHoraFin().equals("00:00:00"))/*127.120*/{_display_(Seq[Any](format.raw/*127.121*/("""
										"""),format.raw/*128.11*/("""<input type="time" class="form-control form-control-sm left" style="max-width:100px"
											value=""""),_display_(/*129.20*/mantTransacReport/*129.37*/.getHoraFin()),format.raw/*129.50*/(""""
											readonly>
									""")))}else/*131.15*/{_display_(Seq[Any](format.raw/*131.16*/("""
										"""),format.raw/*132.11*/("""<input type="time" class="form-control form-control-sm left" style="max-width:100px"
											readonly>
									""")))}),format.raw/*134.11*/("""
						        """),format.raw/*135.15*/("""</td>
							</tr>
							
							<tr>
								<td style="text-align:left;"><strong><div id="lectIni_oper">Lect_INI:</div></strong></td>
						        <td style="text-align:left">
									<input type="text" class="form-control form-control-sm left" style="max-width:200px"
										value=""""),_display_(/*142.19*/utilities/*142.28*/.DecimalFormato.formato(mantTransacReport.getLectAnterior(),2)),format.raw/*142.90*/(""""
										readonly>
						        </td>
								<td style="text-align:left;"><strong><div id="lectTer_oper">Lect_TER:</div></strong></td>
						        <td style="text-align:left" colspan="2">
									<input type="text" class="form-control form-control-sm left" style="max-width:200px"
										value=""""),_display_(/*148.19*/utilities/*148.28*/.DecimalFormato.formato(mantTransacReport.getLectActual(),2)),format.raw/*148.88*/(""""
										readonly>
						        </td>
							</tr>
							
							<tr>
								<td style="text-align:left;"><strong><div id="cant_oper">CANT:</div></strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control form-control-sm left" style="max-width:200px"
										value=""""),_display_(/*157.19*/mantTransacReport/*157.36*/.getLectDif()),format.raw/*157.49*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>COMENTARIOS:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<textarea class="form-control form-control-sm" rows="1" 
										readonly>"""),_display_(/*165.21*/mantTransacReport/*165.38*/.getComentario()),format.raw/*165.54*/("""</textarea>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>OBSERVACIONES:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<textarea class="form-control form-control-sm" rows="2" 
										readonly>"""),_display_(/*172.21*/mantTransacReport/*172.38*/.getObservaciones()),format.raw/*172.57*/("""</textarea>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"  colspan="4">
									<input type="hidden" name="firmaPDFoperador" id="firma" value="0">
									FIRMA OPERADOR:<br>
									<div class="wrapper" align="center">
									<canvas id="signature-pad" class="signature-pad" width=350 height=90 style="border:1px solid #000000;" ></canvas>
									</div>
									<button type="button" class="btn btn-sm btn-warning" style="font-size: 10px;" onclick="signaturePad.clear()">Borrar firma</button>
								</td>
							</tr>
							<tr>
								<td style="text-align:left;"   colspan="5">
									FIRMA AUTORIZADA:<br>
									<div class="wrapper" align="center">
										<img src="data:image/jpeg;base64, """),_display_(/*189.46*/mantTransacReport/*189.63*/.getFirmaPDFautorizador()),format.raw/*189.88*/("""" height="60px" />
									</div>
								</td>
							</tr>	
						
							<tr>
								<td colspan="5" style="text-align:center;">
									<button type="button" class="btn btn-sm btn-success" style="font-size: 10px;"onclick="grabar();" >GRABAR</button>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<button type="button" class="btn btn-sm btn-warning" style="font-size: 10px;" onclick="location.href = '/routes3/mantWebListarReports1Get/"""),_display_(/*198.149*/desdeAAMMDD),format.raw/*198.160*/(""","""),_display_(/*198.162*/hastaAAMMDD),format.raw/*198.173*/(""","""),_display_(/*198.175*/id_equipo),format.raw/*198.184*/("""'">CANCELAR</button>
								</td>
							</tr>
						</thead>
					</table>
					
				</form>
			</div>
		</div>
		
		<form class="formulario" id="formDescargaDocumento" method="post" action="/downloadPDF/">	
			<input type="hidden" id="descargaDocumento" name="nombreArchivo">
		</form>
		
	</div>
""")))}),format.raw/*213.2*/("""

"""),format.raw/*215.1*/("""<!-- CONFIGURA PAD -->

<script src=""""),_display_(/*217.15*/routes/*217.21*/.Assets.versioned("javascripts/signature_pad.min.js")),format.raw/*217.74*/(""""></script>
	<script>
		let signaturePad = new SignaturePad(document.getElementById('signature-pad'), """),format.raw/*219.81*/("""{"""),format.raw/*219.82*/("""
		  """),format.raw/*220.5*/("""backgroundColor: 'rgba(255, 255, 255, 0)',
		  penColor: 'rgb(0, 0, 0)'
		"""),format.raw/*222.3*/("""}"""),format.raw/*222.4*/(""");
	</script>
	
	<style>
		.wrapper """),format.raw/*226.12*/("""{"""),format.raw/*226.13*/("""
		  """),format.raw/*227.5*/("""position: relative;
		  width: 350px;
		  height: 90px;
		  -moz-user-select: none;
		  -webkit-user-select: none;
		  -ms-user-select: none;
		  user-select: none;
		"""),format.raw/*234.3*/("""}"""),format.raw/*234.4*/("""

		"""),format.raw/*236.3*/(""".signature-pad """),format.raw/*236.18*/("""{"""),format.raw/*236.19*/("""
		  """),format.raw/*237.5*/("""position: absolute;
		  left: 0;
		  top: 0;
		  width:350px;
		  height:90px;
		"""),format.raw/*242.3*/("""}"""),format.raw/*242.4*/("""
	"""),format.raw/*243.2*/("""</style>
<!-- FIN PAD -->

	
<script>
	$(document).ready(function() """),format.raw/*248.31*/("""{"""),format.raw/*248.32*/("""
 		"""),format.raw/*249.4*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*250.2*/("""}"""),format.raw/*250.3*/(""" """),format.raw/*250.4*/(""");
	const descargaDocumento = (nombreDOC) => """),format.raw/*251.43*/("""{"""),format.raw/*251.44*/("""
		  """),format.raw/*252.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*254.2*/("""}"""),format.raw/*254.3*/("""
	"""),format.raw/*255.2*/("""function grabar()"""),format.raw/*255.19*/("""{"""),format.raw/*255.20*/("""
			"""),format.raw/*256.4*/("""let data = signaturePad.toDataURL('image/png');
			let encodedImage = data.split(",");
			$("#firma").val(encodedImage[1]);
			$("#grabarFirmaOperador").submit();
	"""),format.raw/*260.2*/("""}"""),format.raw/*260.3*/("""
	"""),format.raw/*261.2*/("""window.onload = function () """),format.raw/*261.30*/("""{"""),format.raw/*261.31*/("""
	    """),format.raw/*262.6*/("""var canvas = document.getElementById('signature-pad');
	    var ctx = canvas.getContext('2d');
	    var img = new Image();
	    img.onload = function () """),format.raw/*265.31*/("""{"""),format.raw/*265.32*/("""
	        """),format.raw/*266.10*/("""ctx.drawImage(img, 0, 0, canvas.width, canvas.height);
	    """),format.raw/*267.6*/("""}"""),format.raw/*267.7*/(""";
	    img.src = "data:image/png;base64,"""),_display_(/*268.40*/mantTransacReport/*268.57*/.getFirmaPDFoperador()),format.raw/*268.79*/("""";
	"""),format.raw/*269.2*/("""}"""),format.raw/*269.3*/(""";

</script>
	

"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],mantTransacReport:tables.MantTransacReport,desdeAAMMDD:String,hastaAAMMDD:String,id_equipo:Long): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,mantTransacReport,desdeAAMMDD,hastaAAMMDD,id_equipo)

  def f:((Map[String,String],Map[String,String],tables.MantTransacReport,String,String,Long) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,mantTransacReport,desdeAAMMDD,hastaAAMMDD,id_equipo) => apply(mapDiccionario,mapPermiso,mantTransacReport,desdeAAMMDD,hastaAAMMDD,id_equipo)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuMantencion/mantWebReportOperadorFirmaOper.scala.html
                  HASH: 276a3bbad96bbce7c3bed16d423cc926f5f6f09b
                  MATRIX: 1058->1|1324->174|1353->178|1369->186|1408->188|1438->192|1816->543|1842->560|1871->568|1953->623|1985->634|2067->689|2099->700|2180->754|2210->763|2574->1100|2600->1117|2629->1125|2971->1440|2997->1457|3029->1468|3218->1630|3257->1631|3296->1642|3364->1683|3390->1700|3425->1714|3561->1806|3597->1815|3865->2056|3891->2073|3930->2091|4225->2359|4251->2376|4295->2399|4715->2792|4741->2809|4784->2831|5084->3104|5110->3121|5153->3143|5448->3411|5474->3428|5513->3446|5542->3447|5572->3450|5598->3467|5635->3483|5775->3596|5798->3610|5833->3624|6019->3783|6045->3800|6082->3816|6227->3934|6250->3948|6285->3962|6472->4121|6499->4138|6541->4158|6846->4435|6873->4452|6920->4477|7245->4773|7286->4774|7326->4785|7458->4889|7485->4906|7520->4919|7577->4956|7617->4957|7657->4968|7805->5084|7849->5099|8125->5346|8166->5347|8206->5358|8338->5462|8365->5479|8400->5492|8457->5529|8497->5530|8537->5541|8685->5657|8729->5672|9049->5964|9068->5973|9152->6035|9487->6342|9506->6351|9588->6411|9961->6756|9988->6773|10023->6786|10343->7078|10370->7095|10408->7111|10720->7395|10747->7412|10788->7431|11567->8182|11594->8199|11641->8224|12193->8747|12227->8758|12258->8760|12292->8771|12323->8773|12355->8782|12687->9083|12717->9085|12783->9123|12799->9129|12874->9182|13005->9284|13035->9285|13068->9290|13170->9364|13199->9365|13264->9401|13294->9402|13327->9407|13522->9574|13551->9575|13583->9579|13627->9594|13657->9595|13690->9600|13799->9681|13828->9682|13858->9684|13955->9752|13985->9753|14017->9757|14111->9823|14140->9824|14169->9825|14243->9870|14273->9871|14306->9876|14438->9980|14467->9981|14497->9983|14543->10000|14573->10001|14605->10005|14797->10169|14826->10170|14856->10172|14913->10200|14943->10201|14977->10207|15159->10360|15189->10361|15228->10371|15316->10431|15345->10432|15414->10473|15441->10490|15485->10512|15517->10516|15546->10517
                  LINES: 28->1|33->2|36->5|36->5|36->5|39->8|46->15|46->15|46->15|47->16|47->16|48->17|48->17|49->18|49->18|57->26|57->26|57->26|65->34|65->34|65->34|69->38|69->38|70->39|70->39|70->39|70->39|73->42|74->43|80->49|80->49|80->49|88->57|88->57|88->57|100->69|100->69|100->69|108->77|108->77|108->77|116->85|116->85|116->85|116->85|116->85|116->85|116->85|121->90|121->90|121->90|124->93|124->93|124->93|129->98|129->98|129->98|132->101|132->101|132->101|140->109|140->109|140->109|147->116|147->116|148->117|149->118|149->118|149->118|151->120|151->120|152->121|154->123|155->124|158->127|158->127|159->128|160->129|160->129|160->129|162->131|162->131|163->132|165->134|166->135|173->142|173->142|173->142|179->148|179->148|179->148|188->157|188->157|188->157|196->165|196->165|196->165|203->172|203->172|203->172|220->189|220->189|220->189|229->198|229->198|229->198|229->198|229->198|229->198|244->213|246->215|248->217|248->217|248->217|250->219|250->219|251->220|253->222|253->222|257->226|257->226|258->227|265->234|265->234|267->236|267->236|267->236|268->237|273->242|273->242|274->243|279->248|279->248|280->249|281->250|281->250|281->250|282->251|282->251|283->252|285->254|285->254|286->255|286->255|286->255|287->256|291->260|291->260|292->261|292->261|292->261|293->262|296->265|296->265|297->266|298->267|298->267|299->268|299->268|299->268|300->269|300->269
                  -- GENERATED --
              */
          