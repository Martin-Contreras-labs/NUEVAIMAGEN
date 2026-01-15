
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

object mantReportMecanicoFirmaMec extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template7[Map[String,String],Map[String,String],utilities.UserMnu,tables.MantTransacReport,List[tables.MantTransacComponentes],String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
mantTransacReport: tables.MantTransacReport, mantTransacComponentes: List[tables.MantTransacComponentes], desdeAAMMDD: String, hastaAAMMDD: String):play.twirl.api.HtmlFormat.Appendable = {
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
				<form id="grabarFirmaOperador" action="/routes3/mantGrabarFirmaOperador/" enctype="multipart/form-data" method="POST" enctype="multipart/form-data">
					<input type="hidden" name="id_mantTransacReport" value=""""),_display_(/*16.63*/mantTransacReport/*16.80*/.getId()),format.raw/*16.88*/("""">
					<input type="hidden" name="fechaDesde" value=""""),_display_(/*17.53*/desdeAAMMDD),format.raw/*17.64*/("""">
					<input type="hidden" name="fechaHasta" value=""""),_display_(/*18.53*/hastaAAMMDD),format.raw/*18.64*/("""">
					
					
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
								<td style="text-align:left;"><strong>MECANICO:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*70.19*/mantTransacReport/*70.36*/.getFullNameMecanico()),format.raw/*70.58*/(""""
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
								<td style="text-align:left;"><strong>TIPO DE MANTENCION (*):</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*86.19*/mantTransacReport/*86.36*/.getNameTipoMantencion().toUpperCase()),format.raw/*86.74*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>EQUIPO:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*94.19*/mantTransacReport/*94.36*/.getCodigoEquipo()),format.raw/*94.54*/(""" """),format.raw/*94.55*/("""- """),_display_(/*94.58*/mantTransacReport/*94.75*/.getNameEquipo()),format.raw/*94.91*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>TIPO DE PLAN:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*102.19*/mantTransacReport/*102.36*/.getNamePlanMantencion()),format.raw/*102.60*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>"""),_display_(/*107.47*/mapDiccionario/*107.61*/.get("BODEGA")),format.raw/*107.75*/("""/PROYECTO:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*110.19*/mantTransacReport/*110.36*/.getNameBodega()),format.raw/*110.52*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>TIPO """),_display_(/*115.52*/mapDiccionario/*115.66*/.get("BODEGA")),format.raw/*115.80*/("""/PROYECTO:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*118.19*/mantTransacReport/*118.36*/.getNameTipoBodega()),format.raw/*118.56*/(""""
										readonly>
						        </td>
							</tr>
							
							<tr>
								<td style="text-align:left;"><strong>ESTADO OPERACIONAL (*):</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
						        	<input type="text" class="form-control"
										value=""""),_display_(/*127.19*/mantTransacReport/*127.36*/.getNameEstadoOperacional()),format.raw/*127.63*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>ESTADO EN TALLER (*):</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
						        	<input type="text" class="form-control"
										value=""""),_display_(/*135.19*/mantTransacReport/*135.36*/.getNameEstadoEnTaller()),format.raw/*135.60*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>ACTIVIDAD (*):</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
						        	<input type="text" class="form-control"
										value=""""),_display_(/*143.19*/mantTransacReport/*143.36*/.getNameActividad()),format.raw/*143.55*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>TIPO DE ACTIVIDAD (*):</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
						        	<input type="text" class="form-control"
										value=""""),_display_(/*151.19*/mantTransacReport/*151.36*/.getNameTipoActividad()),format.raw/*151.59*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>ITEM INTERVENIDO (*):</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
						        	<input type="text" class="form-control"
										value=""""),_display_(/*159.19*/mantTransacReport/*159.36*/.getNameItemIntervenido()),format.raw/*159.61*/(""""
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
											"""),_display_(/*175.13*/for(lista <- mantTransacComponentes) yield /*175.49*/{_display_(Seq[Any](format.raw/*175.50*/("""
												"""),format.raw/*176.13*/("""<tr>
													<td style="text-align:left;vertical-align:middle">
														<input type="text" class="form-control"
															value=""""),_display_(/*179.24*/lista/*179.29*/.getNameComponente()),format.raw/*179.49*/(""""
															readonly>
									    			</td>
													<td>
														<input type="text" class="form-control form-control-sm left" style="max-width:200px"
															value=""""),_display_(/*184.24*/utilities/*184.33*/.DecimalFormato.formato(lista.getCantidad(),2)),format.raw/*184.79*/(""""
															readonly>
													</td>
												</tr>
											""")))}),format.raw/*188.13*/("""
											
										"""),format.raw/*190.11*/("""</tbody>
						        	</table>
								</td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>Fecha_INI:</strong></td>
						        <td style="text-align:left">
									<input type="date" class="form-control form-control-sm left" style="max-width:150px"
										value=""""),_display_(/*198.19*/mantTransacReport/*198.36*/.getFechaIni),format.raw/*198.48*/("""">
						        </td>
								<td style="text-align:left;"><strong>Fecha_TER:</strong></td>
						        <td style="text-align:left" colspan="2">
									<input type="date" class="form-control form-control-sm left"  style="max-width:150px"
										value=""""),_display_(/*203.19*/mantTransacReport/*203.36*/.getFechaFin),format.raw/*203.48*/("""">
						        </td>
							</tr>	
							<tr>
								<td style="text-align:left;"><strong>Hr_INI:</strong></td>
						        <td style="text-align:left">
									"""),_display_(if( ! mantTransacReport.getHoraIni().equals("00:00:00") && mantTransacReport.getHoraFin().equals("00:00:00"))/*209.120*/{_display_(Seq[Any](format.raw/*209.121*/("""
										"""),format.raw/*210.11*/("""<input type="time" class="form-control form-control-sm left" style="max-width:100px"
											value=""""),_display_(/*211.20*/mantTransacReport/*211.37*/.getHoraIni()),format.raw/*211.50*/(""""
											readonly>
									""")))}else/*213.15*/{_display_(Seq[Any](format.raw/*213.16*/("""
										"""),format.raw/*214.11*/("""<input type="time" class="form-control form-control-sm left" style="max-width:100px"
											readonly>
									""")))}),format.raw/*216.11*/("""
						        """),format.raw/*217.15*/("""</td>
								<td style="text-align:left;"><strong>Hr_TER:</strong></td>
						        <td style="text-align:left" colspan="2">
									"""),_display_(if( ! mantTransacReport.getHoraIni().equals("00:00:00") && mantTransacReport.getHoraFin().equals("00:00:00"))/*220.120*/{_display_(Seq[Any](format.raw/*220.121*/("""
										"""),format.raw/*221.11*/("""<input type="time" class="form-control form-control-sm left" style="max-width:100px"
											value=""""),_display_(/*222.20*/mantTransacReport/*222.37*/.getHoraFin()),format.raw/*222.50*/(""""
											readonly>
									""")))}else/*224.15*/{_display_(Seq[Any](format.raw/*224.16*/("""
										"""),format.raw/*225.11*/("""<input type="time" class="form-control form-control-sm left" style="max-width:100px"
											readonly>
									""")))}),format.raw/*227.11*/("""
						        """),format.raw/*228.15*/("""</td>
							</tr>
							
							<tr>
								<td style="text-align:left;"><strong><div id="lectIni_oper">Lect_INI:</div></strong></td>
						        <td style="text-align:left">
									<input type="text" class="form-control form-control-sm left" style="max-width:200px"
										value=""""),_display_(/*235.19*/utilities/*235.28*/.DecimalFormato.formato(mantTransacReport.getLectAnterior(),2)),format.raw/*235.90*/(""""
										readonly>
						        </td>
								<td style="text-align:left;"><strong><div id="lectTer_oper">Lect_TER:</div></strong></td>
						        <td style="text-align:left" colspan="2">
									<input type="text" class="form-control form-control-sm left" style="max-width:200px"
										value=""""),_display_(/*241.19*/utilities/*241.28*/.DecimalFormato.formato(mantTransacReport.getLectActual(),2)),format.raw/*241.88*/(""""
										readonly>
						        </td>
							</tr>
							
							<tr>
								<td style="text-align:left;"><strong><div id="cant_oper">CANT:</div></strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control form-control-sm left" style="max-width:200px"
										value=""""),_display_(/*250.19*/mantTransacReport/*250.36*/.getLectDif()),format.raw/*250.49*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>DESCRIPCION TRABAJO:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<textarea class="form-control form-control-sm" rows="1" 
										readonly>"""),_display_(/*258.21*/mantTransacReport/*258.38*/.getDescTrabajo()),format.raw/*258.55*/("""</textarea>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>ESTADO FINAL:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<textarea class="form-control form-control-sm" rows="1" 
										readonly>"""),_display_(/*265.21*/mantTransacReport/*265.38*/.getEstadoFinal()),format.raw/*265.55*/("""</textarea>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>OBSERVACIONES:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<textarea class="form-control form-control-sm" rows="2" 
										readonly>"""),_display_(/*272.21*/mantTransacReport/*272.38*/.getObservaciones()),format.raw/*272.57*/("""</textarea>
						        </td>
							</tr>
							
							<tr>
								<td style="text-align:left;"  colspan="4">
									<input type="hidden" name="firmaPDFoperador" id="firma" value="0">
									FIRMA MECANICO:<br>
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
										<img src="data:image/jpeg;base64, """),_display_(/*290.46*/mantTransacReport/*290.63*/.getFirmaPDFautorizador()),format.raw/*290.88*/("""" height="60px" />
									</div>
								</td>
							</tr>	
						
							<tr>
								<td colspan="5" style="text-align:center;">
									<button type="button" class="btn btn-sm btn-success" style="font-size: 10px;"onclick="grabar();" >GRABAR</button>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<button type="button" class="btn btn-sm btn-warning" style="font-size: 10px;" onclick="location.href = '/routes3/mantListarReports1Get/"""),_display_(/*299.146*/desdeAAMMDD),format.raw/*299.157*/(""","""),_display_(/*299.159*/hastaAAMMDD),format.raw/*299.170*/("""'">VOLVER</button>
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
""")))}),format.raw/*314.2*/("""


"""),format.raw/*317.1*/("""<!-- CONFIGURA PAD -->

<script src=""""),_display_(/*319.15*/routes/*319.21*/.Assets.versioned("javascripts/signature_pad.min.js")),format.raw/*319.74*/(""""></script>
	<script>
		let signaturePad = new SignaturePad(document.getElementById('signature-pad'), """),format.raw/*321.81*/("""{"""),format.raw/*321.82*/("""
		  """),format.raw/*322.5*/("""backgroundColor: 'rgba(255, 255, 255, 0)',
		  penColor: 'rgb(0, 0, 0)'
		"""),format.raw/*324.3*/("""}"""),format.raw/*324.4*/(""");
	</script>
	
	<style>
		.wrapper """),format.raw/*328.12*/("""{"""),format.raw/*328.13*/("""
		  """),format.raw/*329.5*/("""position: relative;
		  width: 350px;
		  height: 90px;
		  -moz-user-select: none;
		  -webkit-user-select: none;
		  -ms-user-select: none;
		  user-select: none;
		"""),format.raw/*336.3*/("""}"""),format.raw/*336.4*/("""

		"""),format.raw/*338.3*/(""".signature-pad """),format.raw/*338.18*/("""{"""),format.raw/*338.19*/("""
		  """),format.raw/*339.5*/("""position: absolute;
		  left: 0;
		  top: 0;
		  width:350px;
		  height:90px;
		"""),format.raw/*344.3*/("""}"""),format.raw/*344.4*/("""
	"""),format.raw/*345.2*/("""</style>
<!-- FIN PAD -->

	
<script>
	$(document).ready(function() """),format.raw/*350.31*/("""{"""),format.raw/*350.32*/("""
 		"""),format.raw/*351.4*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*352.2*/("""}"""),format.raw/*352.3*/(""" """),format.raw/*352.4*/(""");
	const descargaDocumento = (nombreDOC) => """),format.raw/*353.43*/("""{"""),format.raw/*353.44*/("""
		  """),format.raw/*354.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*356.2*/("""}"""),format.raw/*356.3*/("""
	"""),format.raw/*357.2*/("""function grabar()"""),format.raw/*357.19*/("""{"""),format.raw/*357.20*/("""
			"""),format.raw/*358.4*/("""let data = signaturePad.toDataURL('image/png');
			let encodedImage = data.split(",");
			$("#firma").val(encodedImage[1]);
			$("#grabarFirmaOperador").submit();
	"""),format.raw/*362.2*/("""}"""),format.raw/*362.3*/("""
	"""),format.raw/*363.2*/("""window.onload = function () """),format.raw/*363.30*/("""{"""),format.raw/*363.31*/("""
	    """),format.raw/*364.6*/("""var canvas = document.getElementById('signature-pad');
	    var ctx = canvas.getContext('2d');
	    var img = new Image();
	    img.onload = function () """),format.raw/*367.31*/("""{"""),format.raw/*367.32*/("""
	        """),format.raw/*368.10*/("""ctx.drawImage(img, 0, 0, canvas.width, canvas.height);
	    """),format.raw/*369.6*/("""}"""),format.raw/*369.7*/(""";
	    img.src = "data:image/png;base64,"""),_display_(/*370.40*/mantTransacReport/*370.57*/.getFirmaPDFoperador()),format.raw/*370.79*/("""";
	"""),format.raw/*371.2*/("""}"""),format.raw/*371.3*/(""";
</script>
	

"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,mantTransacReport:tables.MantTransacReport,mantTransacComponentes:List[tables.MantTransacComponentes],desdeAAMMDD:String,hastaAAMMDD:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,mantTransacReport,mantTransacComponentes,desdeAAMMDD,hastaAAMMDD)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.MantTransacReport,List[tables.MantTransacComponentes],String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,mantTransacReport,mantTransacComponentes,desdeAAMMDD,hastaAAMMDD) => apply(mapDiccionario,mapPermiso,userMnu,mantTransacReport,mantTransacComponentes,desdeAAMMDD,hastaAAMMDD)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuMantencion/mantReportMecanicoFirmaMec.scala.html
                  HASH: c77804f1df937791261bd18dd1794574805aa6b8
                  MATRIX: 1103->1|1441->246|1471->251|1487->259|1526->261|1554->264|1622->312|1650->314|1727->365|1818->435|1851->441|2202->765|2228->782|2257->790|2339->845|2371->856|2453->911|2485->922|2860->1270|2886->1287|2915->1295|3257->1610|3283->1627|3315->1638|3504->1800|3543->1801|3582->1812|3650->1853|3676->1870|3711->1884|3847->1976|3883->1985|4151->2226|4177->2243|4216->2261|4511->2529|4537->2546|4581->2569|5000->2961|5026->2978|5069->3000|5369->3273|5395->3290|5438->3312|5749->3596|5775->3613|5834->3651|6129->3919|6155->3936|6194->3954|6223->3955|6253->3958|6279->3975|6316->3991|6618->4265|6645->4282|6691->4306|6832->4419|6856->4433|6892->4447|7079->4606|7106->4623|7144->4639|7290->4757|7314->4771|7350->4785|7537->4944|7564->4961|7606->4981|7932->5279|7959->5296|8008->5323|8324->5611|8351->5628|8397->5652|8706->5933|8733->5950|8774->5969|9091->6258|9118->6275|9163->6298|9479->6586|9506->6603|9553->6628|10194->7241|10247->7277|10287->7278|10329->7291|10503->7437|10518->7442|10560->7462|10777->7651|10796->7660|10864->7706|10972->7782|11024->7805|11349->8102|11376->8119|11410->8131|11699->8392|11726->8409|11760->8421|12067->8699|12108->8700|12148->8711|12280->8815|12307->8832|12342->8845|12399->8882|12439->8883|12479->8894|12627->9010|12671->9025|12947->9272|12988->9273|13028->9284|13160->9388|13187->9405|13222->9418|13279->9455|13319->9456|13359->9467|13507->9583|13551->9598|13871->9890|13890->9899|13974->9961|14309->10268|14328->10277|14410->10337|14783->10682|14810->10699|14845->10712|15173->11012|15200->11029|15239->11046|15550->11329|15577->11346|15616->11363|15928->11647|15955->11664|15996->11683|16783->12442|16810->12459|16857->12484|17406->13004|17440->13015|17471->13017|17505->13028|17835->13327|17866->13330|17932->13368|17948->13374|18023->13427|18154->13529|18184->13530|18217->13535|18319->13609|18348->13610|18413->13646|18443->13647|18476->13652|18671->13819|18700->13820|18732->13824|18776->13839|18806->13840|18839->13845|18948->13926|18977->13927|19007->13929|19104->13997|19134->13998|19166->14002|19260->14068|19289->14069|19318->14070|19392->14115|19422->14116|19455->14121|19587->14225|19616->14226|19646->14228|19692->14245|19722->14246|19754->14250|19946->14414|19975->14415|20005->14417|20062->14445|20092->14446|20126->14452|20308->14605|20338->14606|20377->14616|20465->14676|20494->14677|20563->14718|20590->14735|20634->14757|20666->14761|20695->14762
                  LINES: 28->1|34->3|37->6|37->6|37->6|39->8|39->8|40->9|41->10|41->10|43->12|47->16|47->16|47->16|48->17|48->17|49->18|49->18|58->27|58->27|58->27|66->35|66->35|66->35|70->39|70->39|71->40|71->40|71->40|71->40|74->43|75->44|81->50|81->50|81->50|89->58|89->58|89->58|101->70|101->70|101->70|109->78|109->78|109->78|117->86|117->86|117->86|125->94|125->94|125->94|125->94|125->94|125->94|125->94|133->102|133->102|133->102|138->107|138->107|138->107|141->110|141->110|141->110|146->115|146->115|146->115|149->118|149->118|149->118|158->127|158->127|158->127|166->135|166->135|166->135|174->143|174->143|174->143|182->151|182->151|182->151|190->159|190->159|190->159|206->175|206->175|206->175|207->176|210->179|210->179|210->179|215->184|215->184|215->184|219->188|221->190|229->198|229->198|229->198|234->203|234->203|234->203|240->209|240->209|241->210|242->211|242->211|242->211|244->213|244->213|245->214|247->216|248->217|251->220|251->220|252->221|253->222|253->222|253->222|255->224|255->224|256->225|258->227|259->228|266->235|266->235|266->235|272->241|272->241|272->241|281->250|281->250|281->250|289->258|289->258|289->258|296->265|296->265|296->265|303->272|303->272|303->272|321->290|321->290|321->290|330->299|330->299|330->299|330->299|345->314|348->317|350->319|350->319|350->319|352->321|352->321|353->322|355->324|355->324|359->328|359->328|360->329|367->336|367->336|369->338|369->338|369->338|370->339|375->344|375->344|376->345|381->350|381->350|382->351|383->352|383->352|383->352|384->353|384->353|385->354|387->356|387->356|388->357|388->357|388->357|389->358|393->362|393->362|394->363|394->363|394->363|395->364|398->367|398->367|399->368|400->369|400->369|401->370|401->370|401->370|402->371|402->371
                  -- GENERATED --
              */
          