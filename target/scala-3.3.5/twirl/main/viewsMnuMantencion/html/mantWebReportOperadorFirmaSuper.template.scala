
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

object mantWebReportOperadorFirmaSuper extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],tables.MantTransacReport,String,String,Long,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String],
mantTransacReport: tables.MantTransacReport, desdeAAMMDD: String, hastaAAMMDD: String, id_equipo: Long):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""


"""),_display_(/*6.2*/main("")/*6.10*/ {_display_(Seq[Any](format.raw/*6.12*/("""


	"""),format.raw/*9.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*10.4*/barraTitulo(mapDiccionario, "INGRESO DE REPORT OPERADOR MECANICO", "")),format.raw/*10.74*/("""
		
		"""),format.raw/*12.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
				<br>
				<form id="grabarFirmaSupervisor" action="/routes3/mantWebGrabarFirmaAutorizador/" method="POST" enctype="multipart/form-data">
					<input type="hidden" name="id_mantTransacReport" value=""""),_display_(/*16.63*/mantTransacReport/*16.80*/.getId()),format.raw/*16.88*/("""">
					<input type="hidden" name="fechaDesde" value=""""),_display_(/*17.53*/desdeAAMMDD),format.raw/*17.64*/("""">
					<input type="hidden" name="fechaHasta" value=""""),_display_(/*18.53*/hastaAAMMDD),format.raw/*18.64*/("""">
					<input type="hidden" name="id_equipo" value=""""),_display_(/*19.52*/id_equipo),format.raw/*19.61*/("""">

					<table class="table table-sm table-bordered table-condensed table-fluid">
						<thead>
							<tr>
								<td style="text-align:left;"><strong>ID:</strong></td>
						        <td style="text-align:left;" width="70%" colspan="4">
						        	<input style="max-width:150px" type="text" class="form-control"
										value=""""),_display_(/*27.19*/mantTransacReport/*27.36*/.getId()),format.raw/*27.44*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>FECHA:</strong></td>
						        <td style="text-align:left;" width="70%" colspan="3">
						        	<input style="max-width:150px" type="date" class="form-control form-control-sm" 
										value=""""),_display_(/*35.19*/mantTransacReport/*35.36*/.getFecha()),format.raw/*35.47*/("""" 
										readonly>
						        </td>
						        <td style="text-align:center;" width="70px">
									"""),_display_(if( ! mantTransacReport.getDocAnexo().equals("0"))/*39.61*/{_display_(Seq[Any](format.raw/*39.62*/("""
										"""),format.raw/*40.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*40.52*/mantTransacReport/*40.69*/.getDocAnexo()),format.raw/*40.83*/("""')">
											<kbd style="background-color: #7F8C8D">docum</kbd>
										</a>
									""")))} else {null} ),format.raw/*43.11*/("""
								"""),format.raw/*44.9*/("""</td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>USER_MADA:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
						        	<input type="text" class="form-control"
										value=""""),_display_(/*50.19*/mantTransacReport/*50.36*/.getUserNameAdam()),format.raw/*50.54*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>PERFIL:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*58.19*/mantTransacReport/*58.36*/.getNameActorPersonal()),format.raw/*58.59*/(""""
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
										value=""""),_display_(/*70.19*/mantTransacReport/*70.36*/.getFullNameOperador()),format.raw/*70.58*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>ORIGEN/TIPO:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*78.19*/mantTransacReport/*78.36*/.getNameTipoPersonal()),format.raw/*78.58*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>EQUIPO:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*86.19*/mantTransacReport/*86.36*/.getCodigoEquipo()),format.raw/*86.54*/(""" """),format.raw/*86.55*/("""- """),_display_(/*86.58*/mantTransacReport/*86.75*/.getNameEquipo()),format.raw/*86.91*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>"""),_display_(/*91.47*/mapDiccionario/*91.61*/.get("BODEGA")),format.raw/*91.75*/("""/PROYECTO:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*94.19*/mantTransacReport/*94.36*/.getNameBodega()),format.raw/*94.52*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>TIPO """),_display_(/*99.52*/mapDiccionario/*99.66*/.get("BODEGA")),format.raw/*99.80*/("""/PROYECTO:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*102.19*/mantTransacReport/*102.36*/.getNameTipoBodega()),format.raw/*102.56*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>ESTADO EN SITIO:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*110.19*/mantTransacReport/*110.36*/.getId_mantEstadoEnObra()),format.raw/*110.61*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>Hr_INI:</strong></td>
						        <td style="text-align:left">
									"""),_display_(if( ! mantTransacReport.getHoraIni().equals("00:00:00") && mantTransacReport.getHoraFin().equals("00:00:00"))/*117.120*/{_display_(Seq[Any](format.raw/*117.121*/("""
										"""),format.raw/*118.11*/("""<input type="time" class="form-control form-control-sm left" style="max-width:100px"
											value=""""),_display_(/*119.20*/mantTransacReport/*119.37*/.getHoraIni()),format.raw/*119.50*/(""""
											readonly>
									""")))}else/*121.15*/{_display_(Seq[Any](format.raw/*121.16*/("""
										"""),format.raw/*122.11*/("""<input type="time" class="form-control form-control-sm left" style="max-width:100px"
											readonly>
									""")))}),format.raw/*124.11*/("""
						        """),format.raw/*125.15*/("""</td>
								<td style="text-align:left;"><strong>Hr_TER:</strong></td>
						        <td style="text-align:left" colspan="2">
									"""),_display_(if( ! mantTransacReport.getHoraIni().equals("00:00:00") && mantTransacReport.getHoraFin().equals("00:00:00"))/*128.120*/{_display_(Seq[Any](format.raw/*128.121*/("""
										"""),format.raw/*129.11*/("""<input type="time" class="form-control form-control-sm left" style="max-width:100px"
											value=""""),_display_(/*130.20*/mantTransacReport/*130.37*/.getHoraFin()),format.raw/*130.50*/(""""
											readonly>
									""")))}else/*132.15*/{_display_(Seq[Any](format.raw/*132.16*/("""
										"""),format.raw/*133.11*/("""<input type="time" class="form-control form-control-sm left" style="max-width:100px"
											readonly>
									""")))}),format.raw/*135.11*/("""
						        """),format.raw/*136.15*/("""</td>
							</tr>
							
							<tr>
								<td style="text-align:left;"><strong><div id="lectIni_oper">Lect_INI:</div></strong></td>
						        <td style="text-align:left">
									<input type="text" class="form-control form-control-sm left" style="max-width:200px"
										value=""""),_display_(/*143.19*/utilities/*143.28*/.DecimalFormato.formato(mantTransacReport.getLectAnterior(),2)),format.raw/*143.90*/(""""
										readonly>
						        </td>
								<td style="text-align:left;"><strong><div id="lectTer_oper">Lect_TER:</div></strong></td>
						        <td style="text-align:left" colspan="2">
									<input type="text" class="form-control form-control-sm left" style="max-width:200px"
										value=""""),_display_(/*149.19*/utilities/*149.28*/.DecimalFormato.formato(mantTransacReport.getLectActual(),2)),format.raw/*149.88*/(""""
										readonly>
						        </td>
							</tr>
							
							<tr>
								<td style="text-align:left;"><strong><div id="cant_oper">CANT:</div></strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control form-control-sm left" style="max-width:200px"
										value=""""),_display_(/*158.19*/mantTransacReport/*158.36*/.getLectDif()),format.raw/*158.49*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>COMENTARIOS:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<textarea class="form-control form-control-sm" rows="1" 
										readonly>"""),_display_(/*166.21*/mantTransacReport/*166.38*/.getComentario()),format.raw/*166.54*/("""</textarea>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>OBSERVACIONES:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<textarea class="form-control form-control-sm" rows="2" 
										readonly>"""),_display_(/*173.21*/mantTransacReport/*173.38*/.getObservaciones()),format.raw/*173.57*/("""</textarea>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"  colspan="4">
									FIRMA OPERADOR:<br>
									<div class="wrapper" align="center">
										<img src="data:image/jpeg;base64, """),_display_(/*180.46*/mantTransacReport/*180.63*/.getFirmaPDFoperador()),format.raw/*180.85*/("""" height="60px" />
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
									<button type="button" class="btn btn-sm btn-warning" style="font-size: 10px;" onclick="location.href = '/routes3/mantWebListarReports1Get/"""),_display_(/*199.149*/desdeAAMMDD),format.raw/*199.160*/(""","""),_display_(/*199.162*/hastaAAMMDD),format.raw/*199.173*/(""","""),_display_(/*199.175*/id_equipo),format.raw/*199.184*/("""'">CANCELAR</button>
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
""")))}),format.raw/*214.2*/("""

"""),format.raw/*216.1*/("""<!-- CONFIGURA PAD -->

<script src=""""),_display_(/*218.15*/routes/*218.21*/.Assets.versioned("javascripts/signature_pad.min.js")),format.raw/*218.74*/(""""></script>
	<script>
		let signaturePad2 = new SignaturePad(document.getElementById('signature-pad2'), """),format.raw/*220.83*/("""{"""),format.raw/*220.84*/("""
		  """),format.raw/*221.5*/("""backgroundColor: 'rgba(255, 255, 255, 0)',
		  penColor: 'rgb(0, 0, 0)'
		"""),format.raw/*223.3*/("""}"""),format.raw/*223.4*/(""");
	</script>
	
	<style>
		.wrapper """),format.raw/*227.12*/("""{"""),format.raw/*227.13*/("""
		  """),format.raw/*228.5*/("""position: relative;
		  width: 350px;
		  height: 90px;
		  -moz-user-select: none;
		  -webkit-user-select: none;
		  -ms-user-select: none;
		  user-select: none;
		"""),format.raw/*235.3*/("""}"""),format.raw/*235.4*/("""

		"""),format.raw/*237.3*/(""".signature-pad """),format.raw/*237.18*/("""{"""),format.raw/*237.19*/("""
		  """),format.raw/*238.5*/("""position: absolute;
		  left: 0;
		  top: 0;
		  width:350px;
		  height:90px;
		"""),format.raw/*243.3*/("""}"""),format.raw/*243.4*/("""
	"""),format.raw/*244.2*/("""</style>
<!-- FIN PAD -->

	
<script>
	$(document).ready(function() """),format.raw/*249.31*/("""{"""),format.raw/*249.32*/("""
 		"""),format.raw/*250.4*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*251.2*/("""}"""),format.raw/*251.3*/(""" """),format.raw/*251.4*/(""");
	const descargaDocumento = (nombreDOC) => """),format.raw/*252.43*/("""{"""),format.raw/*252.44*/("""
		  """),format.raw/*253.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*255.2*/("""}"""),format.raw/*255.3*/("""
	"""),format.raw/*256.2*/("""function grabar()"""),format.raw/*256.19*/("""{"""),format.raw/*256.20*/("""
			"""),format.raw/*257.4*/("""let data = signaturePad2.toDataURL('image/png');
			let encodedImage = data.split(",");
			$("#firma").val(encodedImage[1]);
			$("#grabarFirmaSupervisor").submit();
	"""),format.raw/*261.2*/("""}"""),format.raw/*261.3*/("""
	"""),format.raw/*262.2*/("""window.onload = function () """),format.raw/*262.30*/("""{"""),format.raw/*262.31*/("""
	    """),format.raw/*263.6*/("""var canvas = document.getElementById('signature-pad2');
	    var ctx = canvas.getContext('2d');
	    var img = new Image();
	    img.onload = function () """),format.raw/*266.31*/("""{"""),format.raw/*266.32*/("""
	        """),format.raw/*267.10*/("""ctx.drawImage(img, 0, 0, canvas.width, canvas.height);
	    """),format.raw/*268.6*/("""}"""),format.raw/*268.7*/(""";
	    img.src = "data:image/png;base64,"""),_display_(/*269.40*/mantTransacReport/*269.57*/.getFirmaPDFautorizador()),format.raw/*269.82*/("""";
	"""),format.raw/*270.2*/("""}"""),format.raw/*270.3*/(""";

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
                  SOURCE: app/viewsMnuMantencion/mantWebReportOperadorFirmaSuper.scala.html
                  HASH: a3b7a9f29fd87ad032eb73b1a35e705ed286fe28
                  MATRIX: 1059->1|1325->174|1354->178|1370->186|1409->188|1439->192|1516->243|1607->313|1640->319|1969->621|1995->638|2024->646|2106->701|2138->712|2220->767|2252->778|2333->832|2363->841|2727->1178|2753->1195|2782->1203|3124->1518|3150->1535|3182->1546|3371->1708|3410->1709|3449->1720|3517->1761|3543->1778|3578->1792|3714->1884|3750->1893|4018->2134|4044->2151|4083->2169|4378->2437|4404->2454|4448->2477|4868->2870|4894->2887|4937->2909|5237->3182|5263->3199|5306->3221|5601->3489|5627->3506|5666->3524|5695->3525|5725->3528|5751->3545|5788->3561|5928->3674|5951->3688|5986->3702|6172->3861|6198->3878|6235->3894|6380->4012|6403->4026|6438->4040|6625->4199|6652->4216|6694->4236|6999->4513|7026->4530|7073->4555|7398->4851|7439->4852|7479->4863|7611->4967|7638->4984|7673->4997|7730->5034|7770->5035|7810->5046|7958->5162|8002->5177|8278->5424|8319->5425|8359->5436|8491->5540|8518->5557|8553->5570|8610->5607|8650->5608|8690->5619|8838->5735|8882->5750|9202->6042|9221->6051|9305->6113|9640->6420|9659->6429|9741->6489|10114->6834|10141->6851|10176->6864|10496->7156|10523->7173|10561->7189|10873->7473|10900->7490|10941->7509|11197->7737|11224->7754|11268->7776|12349->8828|12383->8839|12414->8841|12448->8852|12479->8854|12511->8863|12843->9164|12873->9166|12939->9204|12955->9210|13030->9263|13163->9367|13193->9368|13226->9373|13328->9447|13357->9448|13422->9484|13452->9485|13485->9490|13680->9657|13709->9658|13741->9662|13785->9677|13815->9678|13848->9683|13957->9764|13986->9765|14016->9767|14113->9835|14143->9836|14175->9840|14269->9906|14298->9907|14327->9908|14401->9953|14431->9954|14464->9959|14596->10063|14625->10064|14655->10066|14701->10083|14731->10084|14763->10088|14958->10255|14987->10256|15017->10258|15074->10286|15104->10287|15138->10293|15321->10447|15351->10448|15390->10458|15478->10518|15507->10519|15576->10560|15603->10577|15650->10602|15682->10606|15711->10607
                  LINES: 28->1|34->3|37->6|37->6|37->6|40->9|41->10|41->10|43->12|47->16|47->16|47->16|48->17|48->17|49->18|49->18|50->19|50->19|58->27|58->27|58->27|66->35|66->35|66->35|70->39|70->39|71->40|71->40|71->40|71->40|74->43|75->44|81->50|81->50|81->50|89->58|89->58|89->58|101->70|101->70|101->70|109->78|109->78|109->78|117->86|117->86|117->86|117->86|117->86|117->86|117->86|122->91|122->91|122->91|125->94|125->94|125->94|130->99|130->99|130->99|133->102|133->102|133->102|141->110|141->110|141->110|148->117|148->117|149->118|150->119|150->119|150->119|152->121|152->121|153->122|155->124|156->125|159->128|159->128|160->129|161->130|161->130|161->130|163->132|163->132|164->133|166->135|167->136|174->143|174->143|174->143|180->149|180->149|180->149|189->158|189->158|189->158|197->166|197->166|197->166|204->173|204->173|204->173|211->180|211->180|211->180|230->199|230->199|230->199|230->199|230->199|230->199|245->214|247->216|249->218|249->218|249->218|251->220|251->220|252->221|254->223|254->223|258->227|258->227|259->228|266->235|266->235|268->237|268->237|268->237|269->238|274->243|274->243|275->244|280->249|280->249|281->250|282->251|282->251|282->251|283->252|283->252|284->253|286->255|286->255|287->256|287->256|287->256|288->257|292->261|292->261|293->262|293->262|293->262|294->263|297->266|297->266|298->267|299->268|299->268|300->269|300->269|300->269|301->270|301->270
                  -- GENERATED --
              */
          