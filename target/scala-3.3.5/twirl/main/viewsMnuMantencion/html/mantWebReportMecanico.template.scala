
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

object mantWebReportMecanico extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],tables.MantTransacReport,List[tables.MantTransacComponentes],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String],
mantTransacReport: tables.MantTransacReport, mantTransacComponentes: List[tables.MantTransacComponentes]):play.twirl.api.HtmlFormat.Appendable = {
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
								<td style="text-align:left;"><strong>MECANICO:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*64.19*/mantTransacReport/*64.36*/.getFullNameMecanico()),format.raw/*64.58*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>TIPO DE MANTENCION (*):</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*72.19*/mantTransacReport/*72.36*/.getNameTipoMantencion().toUpperCase()),format.raw/*72.74*/(""""
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
								<td style="text-align:left;"><strong>TIPO DE PLAN:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*88.19*/mantTransacReport/*88.36*/.getNamePlanMantencion()),format.raw/*88.60*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>"""),_display_(/*93.47*/mapDiccionario/*93.61*/.get("BODEGA")),format.raw/*93.75*/("""/PROYECTO:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*96.19*/mantTransacReport/*96.36*/.getNameBodega()),format.raw/*96.52*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>TIPO """),_display_(/*101.52*/mapDiccionario/*101.66*/.get("BODEGA")),format.raw/*101.80*/("""/PROYECTO:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*104.19*/mantTransacReport/*104.36*/.getNameTipoBodega()),format.raw/*104.56*/(""""
										readonly>
						        </td>
							</tr>
							
							<tr>
								<td style="text-align:left;"><strong>ESTADO OPERACIONAL (*):</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
						        	<input type="text" class="form-control"
										value=""""),_display_(/*113.19*/mantTransacReport/*113.36*/.getNameEstadoOperacional()),format.raw/*113.63*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>ESTADO EN TALLER (*):</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
						        	<input type="text" class="form-control"
										value=""""),_display_(/*121.19*/mantTransacReport/*121.36*/.getNameEstadoEnTaller()),format.raw/*121.60*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>ACTIVIDAD (*):</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
						        	<input type="text" class="form-control"
										value=""""),_display_(/*129.19*/mantTransacReport/*129.36*/.getNameActividad()),format.raw/*129.55*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>TIPO DE ACTIVIDAD (*):</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
						        	<input type="text" class="form-control"
										value=""""),_display_(/*137.19*/mantTransacReport/*137.36*/.getNameTipoActividad()),format.raw/*137.59*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>ITEM INTERVENIDO (*):</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
						        	<input type="text" class="form-control"
										value=""""),_display_(/*145.19*/mantTransacReport/*145.36*/.getNameItemIntervenido()),format.raw/*145.61*/(""""
										readonly>
						        </td>
							</tr>
							
							<tr>
								<td style="text-align:left;"><strong>COMPONENTES (*):</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
						        	<table id="tblComponentesC" class="table table-sm table-bordered table-condensed table-fluid">
										<thead>
											<tr>
												<th width="70%" style="text-align:left; vertical-align: middle;">Componentes</th>
												<th width="10%" style="text-align:left; vertical-align: middle;">Cantidad</th>
											</tr>
										</thead>
										<tbody>
											"""),_display_(/*161.13*/for(lista <- mantTransacComponentes) yield /*161.49*/{_display_(Seq[Any](format.raw/*161.50*/("""
												"""),format.raw/*162.13*/("""<tr>
													<td style="text-align:left;vertical-align:middle">
														<input type="text" class="form-control"
															value=""""),_display_(/*165.24*/lista/*165.29*/.getNameComponente()),format.raw/*165.49*/(""""
															readonly>
									    			</td>
													<td>
														<input type="text" class="form-control form-control-sm left" style="max-width:200px"
															value=""""),_display_(/*170.24*/utilities/*170.33*/.DecimalFormato.formato(lista.getCantidad(),2)),format.raw/*170.79*/(""""
															readonly>
													</td>
												</tr>
											""")))}),format.raw/*174.13*/("""
											
										"""),format.raw/*176.11*/("""</tbody>
						        	</table>
								</td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>Fecha_INI:</strong></td>
						        <td style="text-align:left">
									<input type="date" class="form-control form-control-sm left" style="max-width:150px"
										value=""""),_display_(/*184.19*/mantTransacReport/*184.36*/.getFechaIni),format.raw/*184.48*/("""">
						        </td>
								<td style="text-align:left;"><strong>Fecha_TER:</strong></td>
						        <td style="text-align:left" colspan="2">
									<input type="date" class="form-control form-control-sm left"  style="max-width:150px"
										value=""""),_display_(/*189.19*/mantTransacReport/*189.36*/.getFechaFin),format.raw/*189.48*/("""">
						        </td>
							</tr>	
							<tr>
								<td style="text-align:left;"><strong>Hr_INI:</strong></td>
						        <td style="text-align:left">
									"""),_display_(if( ! mantTransacReport.getHoraIni().equals("00:00:00") && mantTransacReport.getHoraFin().equals("00:00:00"))/*195.120*/{_display_(Seq[Any](format.raw/*195.121*/("""
										"""),format.raw/*196.11*/("""<input type="time" class="form-control form-control-sm left" style="max-width:100px"
											value=""""),_display_(/*197.20*/mantTransacReport/*197.37*/.getHoraIni()),format.raw/*197.50*/(""""
											readonly>
									""")))}else/*199.15*/{_display_(Seq[Any](format.raw/*199.16*/("""
										"""),format.raw/*200.11*/("""<input type="time" class="form-control form-control-sm left" style="max-width:100px"
											readonly>
									""")))}),format.raw/*202.11*/("""
						        """),format.raw/*203.15*/("""</td>
								<td style="text-align:left;"><strong>Hr_TER:</strong></td>
						        <td style="text-align:left" colspan="2">
									"""),_display_(if( ! mantTransacReport.getHoraIni().equals("00:00:00") && mantTransacReport.getHoraFin().equals("00:00:00"))/*206.120*/{_display_(Seq[Any](format.raw/*206.121*/("""
										"""),format.raw/*207.11*/("""<input type="time" class="form-control form-control-sm left" style="max-width:100px"
											value=""""),_display_(/*208.20*/mantTransacReport/*208.37*/.getHoraFin()),format.raw/*208.50*/(""""
											readonly>
									""")))}else/*210.15*/{_display_(Seq[Any](format.raw/*210.16*/("""
										"""),format.raw/*211.11*/("""<input type="time" class="form-control form-control-sm left" style="max-width:100px"
											readonly>
									""")))}),format.raw/*213.11*/("""
						        """),format.raw/*214.15*/("""</td>
							</tr>
							
							<tr>
								<td style="text-align:left;"><strong><div id="lectIni_oper">Lect_INI:</div></strong></td>
						        <td style="text-align:left">
									<input type="text" class="form-control form-control-sm left" style="max-width:200px"
										value=""""),_display_(/*221.19*/utilities/*221.28*/.DecimalFormato.formato(mantTransacReport.getLectAnterior(),2)),format.raw/*221.90*/(""""
										readonly>
						        </td>
								<td style="text-align:left;"><strong><div id="lectTer_oper">Lect_TER:</div></strong></td>
						        <td style="text-align:left" colspan="2">
									<input type="text" class="form-control form-control-sm left" style="max-width:200px"
										value=""""),_display_(/*227.19*/utilities/*227.28*/.DecimalFormato.formato(mantTransacReport.getLectActual(),2)),format.raw/*227.88*/(""""
										readonly>
						        </td>
							</tr>
							
							<tr>
								<td style="text-align:left;"><strong><div id="cant_oper">CANT:</div></strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control form-control-sm left" style="max-width:200px"
										value=""""),_display_(/*236.19*/mantTransacReport/*236.36*/.getLectDif()),format.raw/*236.49*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>DESCRIPCION TRABAJO:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<textarea class="form-control form-control-sm" rows="1" 
										readonly>"""),_display_(/*244.21*/mantTransacReport/*244.38*/.getDescTrabajo()),format.raw/*244.55*/("""</textarea>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>ESTADO FINAL:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<textarea class="form-control form-control-sm" rows="1" 
										readonly>"""),_display_(/*251.21*/mantTransacReport/*251.38*/.getEstadoFinal()),format.raw/*251.55*/("""</textarea>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>OBSERVACIONES:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<textarea class="form-control form-control-sm" rows="2" 
										readonly>"""),_display_(/*258.21*/mantTransacReport/*258.38*/.getObservaciones()),format.raw/*258.57*/("""</textarea>
						        </td>
							</tr>
							
							<tr>
								<td style="text-align:left;"  colspan="5">
									FIRMA MECANICO:<br>
									<div class="wrapper" align="center">
										<img src="data:image/jpeg;base64, """),_display_(/*266.46*/mantTransacReport/*266.63*/.getFirmaPDFoperador()),format.raw/*266.85*/(""""  height="60px" />
									</div>
								</td>
							</tr>
							<tr>
								<td style="text-align:left;"   colspan="5">
									FIRMA AUTORIZADA:<br>
									<div class="wrapper" align="center">
										<img src="data:image/jpeg;base64, """),_display_(/*274.46*/mantTransacReport/*274.63*/.getFirmaPDFautorizador()),format.raw/*274.88*/("""" height="60px" />
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
""")))}),format.raw/*295.2*/("""

	
"""),format.raw/*298.1*/("""<script>
	$(document).ready(function() """),format.raw/*299.31*/("""{"""),format.raw/*299.32*/("""
 		"""),format.raw/*300.4*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*301.2*/("""}"""),format.raw/*301.3*/(""" """),format.raw/*301.4*/(""");
	const descargaDocumento = (nombreDOC) => """),format.raw/*302.43*/("""{"""),format.raw/*302.44*/("""
		  """),format.raw/*303.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*305.2*/("""}"""),format.raw/*305.3*/("""
"""),format.raw/*306.1*/("""</script>
	

"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],mantTransacReport:tables.MantTransacReport,mantTransacComponentes:List[tables.MantTransacComponentes]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,mantTransacReport,mantTransacComponentes)

  def f:((Map[String,String],Map[String,String],tables.MantTransacReport,List[tables.MantTransacComponentes]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,mantTransacReport,mantTransacComponentes) => apply(mapDiccionario,mapPermiso,mantTransacReport,mantTransacComponentes)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuMantencion/mantWebReportMecanico.scala.html
                  HASH: 5a3e05ab26df7ea71b9df0d803a7b322b8851bfe
                  MATRIX: 1066->1|1334->176|1364->181|1380->189|1419->191|1449->195|1526->246|1617->316|1650->322|2119->764|2145->781|2174->789|2516->1104|2542->1121|2574->1132|2763->1294|2802->1295|2841->1306|2909->1347|2935->1364|2970->1378|3106->1470|3142->1479|3410->1720|3436->1737|3475->1755|3770->2023|3796->2040|3840->2063|4259->2455|4285->2472|4328->2494|4639->2778|4665->2795|4724->2833|5019->3101|5045->3118|5084->3136|5113->3137|5143->3140|5169->3157|5206->3173|5507->3447|5533->3464|5578->3488|5718->3601|5741->3615|5776->3629|5962->3788|5988->3805|6025->3821|6171->3939|6195->3953|6231->3967|6418->4126|6445->4143|6487->4163|6813->4461|6840->4478|6889->4505|7205->4793|7232->4810|7278->4834|7587->5115|7614->5132|7655->5151|7972->5440|7999->5457|8044->5480|8360->5768|8387->5785|8434->5810|9075->6423|9128->6459|9168->6460|9210->6473|9384->6619|9399->6624|9441->6644|9658->6833|9677->6842|9745->6888|9853->6964|9905->6987|10230->7284|10257->7301|10291->7313|10580->7574|10607->7591|10641->7603|10948->7881|10989->7882|11029->7893|11161->7997|11188->8014|11223->8027|11280->8064|11320->8065|11360->8076|11508->8192|11552->8207|11828->8454|11869->8455|11909->8466|12041->8570|12068->8587|12103->8600|12160->8637|12200->8638|12240->8649|12388->8765|12432->8780|12752->9072|12771->9081|12855->9143|13190->9450|13209->9459|13291->9519|13664->9864|13691->9881|13726->9894|14054->10194|14081->10211|14120->10228|14431->10511|14458->10528|14497->10545|14809->10829|14836->10846|14877->10865|15141->11101|15168->11118|15212->11140|15489->11389|15516->11406|15563->11431|16244->12081|16276->12085|16344->12124|16374->12125|16406->12129|16500->12195|16529->12196|16558->12197|16632->12242|16662->12243|16695->12248|16827->12352|16856->12353|16885->12354
                  LINES: 28->1|34->3|37->6|37->6|37->6|40->9|41->10|41->10|43->12|52->21|52->21|52->21|60->29|60->29|60->29|64->33|64->33|65->34|65->34|65->34|65->34|68->37|69->38|75->44|75->44|75->44|83->52|83->52|83->52|95->64|95->64|95->64|103->72|103->72|103->72|111->80|111->80|111->80|111->80|111->80|111->80|111->80|119->88|119->88|119->88|124->93|124->93|124->93|127->96|127->96|127->96|132->101|132->101|132->101|135->104|135->104|135->104|144->113|144->113|144->113|152->121|152->121|152->121|160->129|160->129|160->129|168->137|168->137|168->137|176->145|176->145|176->145|192->161|192->161|192->161|193->162|196->165|196->165|196->165|201->170|201->170|201->170|205->174|207->176|215->184|215->184|215->184|220->189|220->189|220->189|226->195|226->195|227->196|228->197|228->197|228->197|230->199|230->199|231->200|233->202|234->203|237->206|237->206|238->207|239->208|239->208|239->208|241->210|241->210|242->211|244->213|245->214|252->221|252->221|252->221|258->227|258->227|258->227|267->236|267->236|267->236|275->244|275->244|275->244|282->251|282->251|282->251|289->258|289->258|289->258|297->266|297->266|297->266|305->274|305->274|305->274|326->295|329->298|330->299|330->299|331->300|332->301|332->301|332->301|333->302|333->302|334->303|336->305|336->305|337->306
                  -- GENERATED --
              */
          