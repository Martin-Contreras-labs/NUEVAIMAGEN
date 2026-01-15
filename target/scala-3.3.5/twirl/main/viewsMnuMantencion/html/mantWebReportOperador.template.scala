
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

object mantWebReportOperador extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[Map[String,String],Map[String,String],tables.MantTransacReport,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String],
mantTransacReport: tables.MantTransacReport):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""


"""),_display_(/*6.2*/main("")/*6.10*/ {_display_(Seq[Any](format.raw/*6.12*/("""


	"""),format.raw/*9.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*10.4*/barraTitulo(mapDiccionario, "INGRESO DE REPORT OPERADOR MECANICO", "")),format.raw/*10.74*/("""
		
		"""),format.raw/*12.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
				<br>
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<thead>
							<tr>
								<td style="text-align:left;"><strong>ID:</strong></td>
						        <td style="text-align:left;" width="70%" colspan="4">
						        	<input style="max-width:150px" type="text" class="form-control"
										value=""""),_display_(/*21.19*/mantTransacReport/*21.36*/.getId()),format.raw/*21.44*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>FECHA:</strong></td>
						        <td style="text-align:left;" width="70%" colspan="3">
						        	<input style="max-width:150px" type="date" class="form-control form-control-sm" 
										value=""""),_display_(/*29.19*/mantTransacReport/*29.36*/.getFecha()),format.raw/*29.47*/("""" 
										readonly>
						        </td>
						        <td style="text-align:center;" width="70px">
									"""),_display_(if( ! mantTransacReport.getDocAnexo().equals("0"))/*33.61*/{_display_(Seq[Any](format.raw/*33.62*/("""
										"""),format.raw/*34.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*34.52*/mantTransacReport/*34.69*/.getDocAnexo()),format.raw/*34.83*/("""')">
											<kbd style="background-color: #7F8C8D">docum</kbd>
										</a>
									""")))} else {null} ),format.raw/*37.11*/("""
								"""),format.raw/*38.9*/("""</td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>USER_MADA:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
						        	<input type="text" class="form-control"
										value=""""),_display_(/*44.19*/mantTransacReport/*44.36*/.getUserNameAdam()),format.raw/*44.54*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>PERFIL:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*52.19*/mantTransacReport/*52.36*/.getNameActorPersonal()),format.raw/*52.59*/(""""
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
										value=""""),_display_(/*64.19*/mantTransacReport/*64.36*/.getFullNameOperador()),format.raw/*64.58*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>EQUIPO:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*72.19*/mantTransacReport/*72.36*/.getCodigoEquipo()),format.raw/*72.54*/(""" """),format.raw/*72.55*/("""- """),_display_(/*72.58*/mantTransacReport/*72.75*/.getNameEquipo()),format.raw/*72.91*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>"""),_display_(/*77.47*/mapDiccionario/*77.61*/.get("BODEGA")),format.raw/*77.75*/("""/PROYECTO:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*80.19*/mantTransacReport/*80.36*/.getNameBodega()),format.raw/*80.52*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>TIPO """),_display_(/*85.52*/mapDiccionario/*85.66*/.get("BODEGA")),format.raw/*85.80*/("""/PROYECTO:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*88.19*/mantTransacReport/*88.36*/.getNameTipoBodega()),format.raw/*88.56*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>ESTADO EN SITIO:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*96.19*/mantTransacReport/*96.36*/.getId_mantEstadoEnObra()),format.raw/*96.61*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>Hr_INI:</strong></td>
						        <td style="text-align:left">
									"""),_display_(if( ! mantTransacReport.getHoraIni().equals("00:00:00") && mantTransacReport.getHoraFin().equals("00:00:00"))/*103.120*/{_display_(Seq[Any](format.raw/*103.121*/("""
										"""),format.raw/*104.11*/("""<input type="time" class="form-control form-control-sm left" style="max-width:100px"
											value=""""),_display_(/*105.20*/mantTransacReport/*105.37*/.getHoraIni()),format.raw/*105.50*/(""""
											readonly>
									""")))}else/*107.15*/{_display_(Seq[Any](format.raw/*107.16*/("""
										"""),format.raw/*108.11*/("""<input type="time" class="form-control form-control-sm left" style="max-width:100px"
											readonly>
									""")))}),format.raw/*110.11*/("""
						        """),format.raw/*111.15*/("""</td>
								<td style="text-align:left;"><strong>Hr_TER:</strong></td>
						        <td style="text-align:left" colspan="2">
									"""),_display_(if( ! mantTransacReport.getHoraIni().equals("00:00:00") && mantTransacReport.getHoraFin().equals("00:00:00"))/*114.120*/{_display_(Seq[Any](format.raw/*114.121*/("""
										"""),format.raw/*115.11*/("""<input type="time" class="form-control form-control-sm left" style="max-width:100px"
											value=""""),_display_(/*116.20*/mantTransacReport/*116.37*/.getHoraFin()),format.raw/*116.50*/(""""
											readonly>
									""")))}else/*118.15*/{_display_(Seq[Any](format.raw/*118.16*/("""
										"""),format.raw/*119.11*/("""<input type="time" class="form-control form-control-sm left" style="max-width:100px"
											readonly>
									""")))}),format.raw/*121.11*/("""
						        """),format.raw/*122.15*/("""</td>
							</tr>
							
							<tr>
								<td style="text-align:left;"><strong><div id="lectIni_oper">Lect_INI:</div></strong></td>
						        <td style="text-align:left">
									<input type="text" class="form-control form-control-sm left" style="max-width:200px"
										value=""""),_display_(/*129.19*/utilities/*129.28*/.DecimalFormato.formato(mantTransacReport.getLectAnterior(),2)),format.raw/*129.90*/(""""
										readonly>
						        </td>
								<td style="text-align:left;"><strong><div id="lectTer_oper">Lect_TER:</div></strong></td>
						        <td style="text-align:left" colspan="2">
									<input type="text" class="form-control form-control-sm left" style="max-width:200px"
										value=""""),_display_(/*135.19*/utilities/*135.28*/.DecimalFormato.formato(mantTransacReport.getLectActual(),2)),format.raw/*135.88*/(""""
										readonly>
						        </td>
							</tr>
							
							<tr>
								<td style="text-align:left;"><strong><div id="cant_oper">CANT:</div></strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control form-control-sm left" style="max-width:200px"
										value=""""),_display_(/*144.19*/mantTransacReport/*144.36*/.getLectDif()),format.raw/*144.49*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>COMENTARIOS:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<textarea class="form-control form-control-sm" rows="1" 
										readonly>"""),_display_(/*152.21*/mantTransacReport/*152.38*/.getComentario()),format.raw/*152.54*/("""</textarea>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>OBSERVACIONES:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<textarea class="form-control form-control-sm" rows="2" 
										readonly>"""),_display_(/*159.21*/mantTransacReport/*159.38*/.getObservaciones()),format.raw/*159.57*/("""</textarea>
						        </td>
							</tr>
							
							<tr>
								<td style="text-align:left;"  colspan="5">
									FIRMA OPERADOR:<br>
									<div class="wrapper" align="center">
										<img src="data:image/jpeg;base64, """),_display_(/*167.46*/mantTransacReport/*167.63*/.getFirmaPDFoperador()),format.raw/*167.85*/(""""  height="60px" />
									</div>
								</td>
							</tr>
							<tr>
								<td style="text-align:left;"   colspan="5">
									FIRMA AUTORIZADA:<br>
									<div class="wrapper" align="center">
										<img src="data:image/jpeg;base64, """),_display_(/*175.46*/mantTransacReport/*175.63*/.getFirmaPDFautorizador()),format.raw/*175.88*/("""" height="60px" />
									</div>
								</td>
							</tr>	
						
							<tr>
								<td colspan="5" style="text-align:center;">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<button type="button" class="btn btn-sm btn-warning" style="font-size: 10px;" onclick="window.history.back();">VOLVER</button>
								</td>
							</tr>
						</thead>
					</table>
			</div>
		</div>
		
		<form class="formulario" id="formDescargaDocumento" method="post" action="/downloadPDF/">	
			<input type="hidden" id="descargaDocumento" name="nombreArchivo">
		</form>
		
	</div>
""")))}),format.raw/*196.2*/("""

	
"""),format.raw/*199.1*/("""<script>
	$(document).ready(function() """),format.raw/*200.31*/("""{"""),format.raw/*200.32*/("""
 		"""),format.raw/*201.4*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*202.2*/("""}"""),format.raw/*202.3*/(""" """),format.raw/*202.4*/(""");
	const descargaDocumento = (nombreDOC) => """),format.raw/*203.43*/("""{"""),format.raw/*203.44*/("""
		  """),format.raw/*204.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*206.2*/("""}"""),format.raw/*206.3*/("""
"""),format.raw/*207.1*/("""</script>
	

"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],mantTransacReport:tables.MantTransacReport): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,mantTransacReport)

  def f:((Map[String,String],Map[String,String],tables.MantTransacReport) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,mantTransacReport) => apply(mapDiccionario,mapPermiso,mantTransacReport)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuMantencion/mantWebReportOperador.scala.html
                  HASH: 2236aaf962677933fff40cb74475e4bf18588b1e
                  MATRIX: 1030->1|1237->115|1266->119|1282->127|1321->129|1351->133|1428->184|1519->254|1552->260|2021->702|2047->719|2076->727|2418->1042|2444->1059|2476->1070|2665->1232|2704->1233|2743->1244|2811->1285|2837->1302|2872->1316|3008->1408|3044->1417|3312->1658|3338->1675|3377->1693|3672->1961|3698->1978|3742->2001|4162->2394|4188->2411|4231->2433|4526->2701|4552->2718|4591->2736|4620->2737|4650->2740|4676->2757|4713->2773|4853->2886|4876->2900|4911->2914|5097->3073|5123->3090|5160->3106|5305->3224|5328->3238|5363->3252|5549->3411|5575->3428|5616->3448|5920->3725|5946->3742|5992->3767|6317->4063|6358->4064|6398->4075|6530->4179|6557->4196|6592->4209|6649->4246|6689->4247|6729->4258|6877->4374|6921->4389|7197->4636|7238->4637|7278->4648|7410->4752|7437->4769|7472->4782|7529->4819|7569->4820|7609->4831|7757->4947|7801->4962|8121->5254|8140->5263|8224->5325|8559->5632|8578->5641|8660->5701|9033->6046|9060->6063|9095->6076|9415->6368|9442->6385|9480->6401|9792->6685|9819->6702|9860->6721|10124->6957|10151->6974|10195->6996|10472->7245|10499->7262|10546->7287|11227->7937|11259->7941|11327->7980|11357->7981|11389->7985|11483->8051|11512->8052|11541->8053|11615->8098|11645->8099|11678->8104|11810->8208|11839->8209|11868->8210
                  LINES: 28->1|34->3|37->6|37->6|37->6|40->9|41->10|41->10|43->12|52->21|52->21|52->21|60->29|60->29|60->29|64->33|64->33|65->34|65->34|65->34|65->34|68->37|69->38|75->44|75->44|75->44|83->52|83->52|83->52|95->64|95->64|95->64|103->72|103->72|103->72|103->72|103->72|103->72|103->72|108->77|108->77|108->77|111->80|111->80|111->80|116->85|116->85|116->85|119->88|119->88|119->88|127->96|127->96|127->96|134->103|134->103|135->104|136->105|136->105|136->105|138->107|138->107|139->108|141->110|142->111|145->114|145->114|146->115|147->116|147->116|147->116|149->118|149->118|150->119|152->121|153->122|160->129|160->129|160->129|166->135|166->135|166->135|175->144|175->144|175->144|183->152|183->152|183->152|190->159|190->159|190->159|198->167|198->167|198->167|206->175|206->175|206->175|227->196|230->199|231->200|231->200|232->201|233->202|233->202|233->202|234->203|234->203|235->204|237->206|237->206|238->207
                  -- GENERATED --
              */
          