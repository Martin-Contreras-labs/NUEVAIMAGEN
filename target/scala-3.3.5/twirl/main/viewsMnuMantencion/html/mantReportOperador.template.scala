
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

object mantReportOperador extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,tables.MantTransacReport,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
mantTransacReport: tables.MantTransacReport):play.twirl.api.HtmlFormat.Appendable = {
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
								<td style="text-align:left;"><strong>ORIGEN/TIPO:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*72.19*/mantTransacReport/*72.36*/.getNameTipoPersonal()),format.raw/*72.58*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>EQUIPO:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*80.19*/mantTransacReport/*80.36*/.getCodigoEquipo()),format.raw/*80.54*/(""" """),format.raw/*80.55*/("""- """),_display_(/*80.58*/mantTransacReport/*80.75*/.getNameEquipo()),format.raw/*80.91*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>"""),_display_(/*85.47*/mapDiccionario/*85.61*/.get("BODEGA")),format.raw/*85.75*/("""/PROYECTO:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*88.19*/mantTransacReport/*88.36*/.getNameBodega()),format.raw/*88.52*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>TIPO """),_display_(/*93.52*/mapDiccionario/*93.66*/.get("BODEGA")),format.raw/*93.80*/("""/PROYECTO:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*96.19*/mantTransacReport/*96.36*/.getNameTipoBodega()),format.raw/*96.56*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>ESTADO EN SITIO:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*104.19*/mantTransacReport/*104.36*/.getId_mantEstadoEnObra()),format.raw/*104.61*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>Hr_INI:</strong></td>
						        <td style="text-align:left">
									"""),_display_(if( ! mantTransacReport.getHoraIni().equals("00:00:00") && mantTransacReport.getHoraFin().equals("00:00:00"))/*111.120*/{_display_(Seq[Any](format.raw/*111.121*/("""
										"""),format.raw/*112.11*/("""<input type="time" class="form-control form-control-sm left" style="max-width:100px"
											value=""""),_display_(/*113.20*/mantTransacReport/*113.37*/.getHoraIni()),format.raw/*113.50*/(""""
											readonly>
									""")))}else/*115.15*/{_display_(Seq[Any](format.raw/*115.16*/("""
										"""),format.raw/*116.11*/("""<input type="time" class="form-control form-control-sm left" style="max-width:100px"
											readonly>
									""")))}),format.raw/*118.11*/("""
						        """),format.raw/*119.15*/("""</td>
								<td style="text-align:left;"><strong>Hr_TER:</strong></td>
						        <td style="text-align:left" colspan="2">
									"""),_display_(if( ! mantTransacReport.getHoraIni().equals("00:00:00") && mantTransacReport.getHoraFin().equals("00:00:00"))/*122.120*/{_display_(Seq[Any](format.raw/*122.121*/("""
										"""),format.raw/*123.11*/("""<input type="time" class="form-control form-control-sm left" style="max-width:100px"
											value=""""),_display_(/*124.20*/mantTransacReport/*124.37*/.getHoraFin()),format.raw/*124.50*/(""""
											readonly>
									""")))}else/*126.15*/{_display_(Seq[Any](format.raw/*126.16*/("""
										"""),format.raw/*127.11*/("""<input type="time" class="form-control form-control-sm left" style="max-width:100px"
											readonly>
									""")))}),format.raw/*129.11*/("""
						        """),format.raw/*130.15*/("""</td>
							</tr>
							
							<tr>
								<td style="text-align:left;"><strong><div id="lectIni_oper">Lect_INI:</div></strong></td>
						        <td style="text-align:left">
									<input type="text" class="form-control form-control-sm left" style="max-width:200px"
										value=""""),_display_(/*137.19*/utilities/*137.28*/.DecimalFormato.formato(mantTransacReport.getLectAnterior(),2)),format.raw/*137.90*/(""""
										readonly>
						        </td>
								<td style="text-align:left;"><strong><div id="lectTer_oper">Lect_TER:</div></strong></td>
						        <td style="text-align:left" colspan="2">
									<input type="text" class="form-control form-control-sm left" style="max-width:200px"
										value=""""),_display_(/*143.19*/utilities/*143.28*/.DecimalFormato.formato(mantTransacReport.getLectActual(),2)),format.raw/*143.88*/(""""
										readonly>
						        </td>
							</tr>
							
							<tr>
								<td style="text-align:left;"><strong><div id="cant_oper">CANT:</div></strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control form-control-sm left" style="max-width:200px"
										value=""""),_display_(/*152.19*/mantTransacReport/*152.36*/.getLectDif()),format.raw/*152.49*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>COMENTARIOS:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<textarea class="form-control form-control-sm" rows="1" 
										readonly>"""),_display_(/*160.21*/mantTransacReport/*160.38*/.getComentario()),format.raw/*160.54*/("""</textarea>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>OBSERVACIONES:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<textarea class="form-control form-control-sm" rows="2" 
										readonly>"""),_display_(/*167.21*/mantTransacReport/*167.38*/.getObservaciones()),format.raw/*167.57*/("""</textarea>
						        </td>
							</tr>
							
							<tr>
								<td style="text-align:left;"  colspan="5">
									FIRMA OPERADOR:<br>
									<div class="wrapper" align="center">
										<img src="data:image/jpeg;base64, """),_display_(/*175.46*/mantTransacReport/*175.63*/.getFirmaPDFoperador()),format.raw/*175.85*/(""""  height="60px" />
									</div>
								</td>
							</tr>
							<tr>
								<td style="text-align:left;"   colspan="5">
									FIRMA AUTORIZADA:<br>
									<div class="wrapper" align="center">
										<img src="data:image/jpeg;base64, """),_display_(/*183.46*/mantTransacReport/*183.63*/.getFirmaPDFautorizador()),format.raw/*183.88*/("""" height="60px" />
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
""")))}),format.raw/*204.2*/("""

	
"""),format.raw/*207.1*/("""<script>
	$(document).ready(function() """),format.raw/*208.31*/("""{"""),format.raw/*208.32*/("""
 		"""),format.raw/*209.4*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*210.2*/("""}"""),format.raw/*210.3*/(""" """),format.raw/*210.4*/(""");
	const descargaDocumento = (nombreDOC) => """),format.raw/*211.43*/("""{"""),format.raw/*211.44*/("""
		  """),format.raw/*212.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*214.2*/("""}"""),format.raw/*214.3*/("""
"""),format.raw/*215.1*/("""</script>
	

"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,mantTransacReport:tables.MantTransacReport): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,mantTransacReport)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.MantTransacReport) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,mantTransacReport) => apply(mapDiccionario,mapPermiso,userMnu,mantTransacReport)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuMantencion/mantReportOperador.scala.html
                  HASH: 6afbb8943673fb48c16f542de1df7d4da878d6c2
                  MATRIX: 1045->1|1280->143|1309->147|1325->155|1364->157|1392->160|1460->208|1488->210|1565->261|1656->331|1689->337|2158->779|2184->796|2213->804|2555->1119|2581->1136|2613->1147|2802->1309|2841->1310|2880->1321|2948->1362|2974->1379|3009->1393|3145->1485|3181->1494|3449->1735|3475->1752|3514->1770|3809->2038|3835->2055|3879->2078|4299->2471|4325->2488|4368->2510|4668->2783|4694->2800|4737->2822|5032->3090|5058->3107|5097->3125|5126->3126|5156->3129|5182->3146|5219->3162|5359->3275|5382->3289|5417->3303|5603->3462|5629->3479|5666->3495|5811->3613|5834->3627|5869->3641|6055->3800|6081->3817|6122->3837|6427->4114|6454->4131|6501->4156|6826->4452|6867->4453|6907->4464|7039->4568|7066->4585|7101->4598|7158->4635|7198->4636|7238->4647|7386->4763|7430->4778|7706->5025|7747->5026|7787->5037|7919->5141|7946->5158|7981->5171|8038->5208|8078->5209|8118->5220|8266->5336|8310->5351|8630->5643|8649->5652|8733->5714|9068->6021|9087->6030|9169->6090|9542->6435|9569->6452|9604->6465|9924->6757|9951->6774|9989->6790|10301->7074|10328->7091|10369->7110|10633->7346|10660->7363|10704->7385|10981->7634|11008->7651|11055->7676|11736->8326|11768->8330|11836->8369|11866->8370|11898->8374|11992->8440|12021->8441|12050->8442|12124->8487|12154->8488|12187->8493|12319->8597|12348->8598|12377->8599
                  LINES: 28->1|34->3|37->6|37->6|37->6|39->8|39->8|40->9|41->10|41->10|43->12|52->21|52->21|52->21|60->29|60->29|60->29|64->33|64->33|65->34|65->34|65->34|65->34|68->37|69->38|75->44|75->44|75->44|83->52|83->52|83->52|95->64|95->64|95->64|103->72|103->72|103->72|111->80|111->80|111->80|111->80|111->80|111->80|111->80|116->85|116->85|116->85|119->88|119->88|119->88|124->93|124->93|124->93|127->96|127->96|127->96|135->104|135->104|135->104|142->111|142->111|143->112|144->113|144->113|144->113|146->115|146->115|147->116|149->118|150->119|153->122|153->122|154->123|155->124|155->124|155->124|157->126|157->126|158->127|160->129|161->130|168->137|168->137|168->137|174->143|174->143|174->143|183->152|183->152|183->152|191->160|191->160|191->160|198->167|198->167|198->167|206->175|206->175|206->175|214->183|214->183|214->183|235->204|238->207|239->208|239->208|240->209|241->210|241->210|241->210|242->211|242->211|243->212|245->214|245->214|246->215
                  -- GENERATED --
              */
          