
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

object mantReportOperadorFirmaSuper extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,tables.MantTransacReport,String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
mantTransacReport: tables.MantTransacReport, desdeAAMMDD: String, hastaAAMMDD: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""


"""),_display_(/*6.2*/main("")/*6.10*/ {_display_(Seq[Any](format.raw/*6.12*/("""

"""),_display_(/*8.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*8.50*/("""
	"""),format.raw/*9.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*10.4*/barraTitulo(mapDiccionario, "INGRESO DE REPORT OPERADOR MECANICO", "")),format.raw/*10.74*/("""
		
		"""),format.raw/*12.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
				<br>
				<form id="grabarFirmaSupervisor" action="/routes3/mantGrabarFirmaAutorizador/" method="POST" enctype="multipart/form-data">
					<input type="hidden" name="id_mantTransacReport" value=""""),_display_(/*16.63*/mantTransacReport/*16.80*/.getId()),format.raw/*16.88*/("""">
					<input type="hidden" name="fechaDesde" value=""""),_display_(/*17.53*/desdeAAMMDD),format.raw/*17.64*/("""">
					<input type="hidden" name="fechaHasta" value=""""),_display_(/*18.53*/hastaAAMMDD),format.raw/*18.64*/("""">

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
									FIRMA OPERADOR:<br>
									<div class="wrapper" align="center">
										<img src="data:image/jpeg;base64, """),_display_(/*179.46*/mantTransacReport/*179.63*/.getFirmaPDFoperador()),format.raw/*179.85*/("""" height="60px" />
									</div>
								</td>
							</tr>
							<tr>
								<td style="text-align:left;"   colspan="5">
									<input type="hidden" name="firmaPDFautorizador" id="firma" value="0">
									FIRMA AUTORIZADA:<br>
									<div class="wrapper" align="center">
										<canvas id="signature-pad2" class="signature-pad" width=350 height=90 style="border:1px solid #000000;"></canvas>
									</div>
									<button type="button" class="btn btn-sm btn-warning" style="font-size: 10px;" onclick="signaturePad2.clear()">Borrar firma</button>
								</td>
							</tr>	
							
							<tr>
								<td colspan="5" style="text-align:center;">
									<button type="button" class="btn btn-sm btn-success" style="font-size: 10px;"onclick="grabar();" >GRABAR</button>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<button type="button" class="btn btn-sm btn-warning" style="font-size: 10px;" onclick="location.href = '/routes3/mantListarReports1Get/"""),_display_(/*198.146*/desdeAAMMDD),format.raw/*198.157*/(""","""),_display_(/*198.159*/hastaAAMMDD),format.raw/*198.170*/("""'">CANCELAR</button>
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
		let signaturePad2 = new SignaturePad(document.getElementById('signature-pad2'), """),format.raw/*219.83*/("""{"""),format.raw/*219.84*/("""
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
			"""),format.raw/*256.4*/("""let data = signaturePad2.toDataURL('image/png');
			let encodedImage = data.split(",");
			$("#firma").val(encodedImage[1]);
			$("#grabarFirmaSupervisor").submit();
	"""),format.raw/*260.2*/("""}"""),format.raw/*260.3*/("""
	"""),format.raw/*261.2*/("""window.onload = function () """),format.raw/*261.30*/("""{"""),format.raw/*261.31*/("""
	    """),format.raw/*262.6*/("""var canvas = document.getElementById('signature-pad2');
	    var ctx = canvas.getContext('2d');
	    var img = new Image();
	    img.onload = function () """),format.raw/*265.31*/("""{"""),format.raw/*265.32*/("""
	        """),format.raw/*266.10*/("""ctx.drawImage(img, 0, 0, canvas.width, canvas.height);
	    """),format.raw/*267.6*/("""}"""),format.raw/*267.7*/(""";
	    img.src = "data:image/png;base64,"""),_display_(/*268.40*/mantTransacReport/*268.57*/.getFirmaPDFautorizador()),format.raw/*268.82*/("""";
	"""),format.raw/*269.2*/("""}"""),format.raw/*269.3*/(""";

</script>
	

"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,mantTransacReport:tables.MantTransacReport,desdeAAMMDD:String,hastaAAMMDD:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,mantTransacReport,desdeAAMMDD,hastaAAMMDD)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.MantTransacReport,String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,mantTransacReport,desdeAAMMDD,hastaAAMMDD) => apply(mapDiccionario,mapPermiso,userMnu,mantTransacReport,desdeAAMMDD,hastaAAMMDD)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuMantencion/mantReportOperadorFirmaSuper.scala.html
                  HASH: 06ab91109b71ef9a84c1e12f55673ca4133b24d2
                  MATRIX: 1069->1|1346->185|1375->189|1391->197|1430->199|1458->202|1526->250|1554->252|1631->303|1722->373|1755->379|2081->678|2107->695|2136->703|2218->758|2250->769|2332->824|2364->835|2728->1172|2754->1189|2783->1197|3125->1512|3151->1529|3183->1540|3372->1702|3411->1703|3450->1714|3518->1755|3544->1772|3579->1786|3715->1878|3751->1887|4019->2128|4045->2145|4084->2163|4379->2431|4405->2448|4449->2471|4869->2864|4895->2881|4938->2903|5238->3176|5264->3193|5307->3215|5602->3483|5628->3500|5667->3518|5696->3519|5726->3522|5752->3539|5789->3555|5929->3668|5952->3682|5987->3696|6173->3855|6199->3872|6236->3888|6381->4006|6404->4020|6439->4034|6626->4193|6653->4210|6695->4230|7000->4507|7027->4524|7074->4549|7399->4845|7440->4846|7480->4857|7612->4961|7639->4978|7674->4991|7731->5028|7771->5029|7811->5040|7959->5156|8003->5171|8279->5418|8320->5419|8360->5430|8492->5534|8519->5551|8554->5564|8611->5601|8651->5602|8691->5613|8839->5729|8883->5744|9203->6036|9222->6045|9306->6107|9641->6414|9660->6423|9742->6483|10115->6828|10142->6845|10177->6858|10497->7150|10524->7167|10562->7183|10874->7467|10901->7484|10942->7503|11198->7731|11225->7748|11269->7770|12347->8819|12381->8830|12412->8832|12446->8843|12778->9144|12808->9146|12874->9184|12890->9190|12965->9243|13098->9347|13128->9348|13161->9353|13263->9427|13292->9428|13357->9464|13387->9465|13420->9470|13615->9637|13644->9638|13676->9642|13720->9657|13750->9658|13783->9663|13892->9744|13921->9745|13951->9747|14048->9815|14078->9816|14110->9820|14204->9886|14233->9887|14262->9888|14336->9933|14366->9934|14399->9939|14531->10043|14560->10044|14590->10046|14636->10063|14666->10064|14698->10068|14893->10235|14922->10236|14952->10238|15009->10266|15039->10267|15073->10273|15256->10427|15286->10428|15325->10438|15413->10498|15442->10499|15511->10540|15538->10557|15585->10582|15617->10586|15646->10587
                  LINES: 28->1|34->3|37->6|37->6|37->6|39->8|39->8|40->9|41->10|41->10|43->12|47->16|47->16|47->16|48->17|48->17|49->18|49->18|57->26|57->26|57->26|65->34|65->34|65->34|69->38|69->38|70->39|70->39|70->39|70->39|73->42|74->43|80->49|80->49|80->49|88->57|88->57|88->57|100->69|100->69|100->69|108->77|108->77|108->77|116->85|116->85|116->85|116->85|116->85|116->85|116->85|121->90|121->90|121->90|124->93|124->93|124->93|129->98|129->98|129->98|132->101|132->101|132->101|140->109|140->109|140->109|147->116|147->116|148->117|149->118|149->118|149->118|151->120|151->120|152->121|154->123|155->124|158->127|158->127|159->128|160->129|160->129|160->129|162->131|162->131|163->132|165->134|166->135|173->142|173->142|173->142|179->148|179->148|179->148|188->157|188->157|188->157|196->165|196->165|196->165|203->172|203->172|203->172|210->179|210->179|210->179|229->198|229->198|229->198|229->198|244->213|246->215|248->217|248->217|248->217|250->219|250->219|251->220|253->222|253->222|257->226|257->226|258->227|265->234|265->234|267->236|267->236|267->236|268->237|273->242|273->242|274->243|279->248|279->248|280->249|281->250|281->250|281->250|282->251|282->251|283->252|285->254|285->254|286->255|286->255|286->255|287->256|291->260|291->260|292->261|292->261|292->261|293->262|296->265|296->265|297->266|298->267|298->267|299->268|299->268|299->268|300->269|300->269
                  -- GENERATED --
              */
          