
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

object mantWebReportMecanicoFirmaMec extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template7[Map[String,String],Map[String,String],tables.MantTransacReport,List[tables.MantTransacComponentes],String,String,Long,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String],
mantTransacReport: tables.MantTransacReport, mantTransacComponentes: List[tables.MantTransacComponentes], desdeAAMMDD: String, hastaAAMMDD: String, id_equipo: Long):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/(""" 


"""),_display_(/*6.2*/main("")/*6.10*/ {_display_(Seq[Any](format.raw/*6.12*/("""


	"""),format.raw/*9.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*10.4*/barraTitulo(mapDiccionario, "INGRESO DE REPORT OPERADOR MECANICO", "")),format.raw/*10.74*/("""
		
		"""),format.raw/*12.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
				<br>
				<form id="grabarFirmaOperador" action="/routes3/mantWebGrabarFirmaOperador/" enctype="multipart/form-data" method="POST" enctype="multipart/form-data">
					<input type="hidden" name="id_mantTransacReport" value=""""),_display_(/*16.63*/mantTransacReport/*16.80*/.getId()),format.raw/*16.88*/("""">
					<input type="hidden" name="fechaDesde" value=""""),_display_(/*17.53*/desdeAAMMDD),format.raw/*17.64*/("""">
					<input type="hidden" name="fechaHasta" value=""""),_display_(/*18.53*/hastaAAMMDD),format.raw/*18.64*/("""">
					<input type="hidden" name="id_equipo" value=""""),_display_(/*19.52*/id_equipo),format.raw/*19.61*/("""">
					
					
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<thead>
							<tr>
								<td style="text-align:left;"><strong>ID:</strong></td>
						        <td style="text-align:left; width: 70%" colspan="4">
						        	<input style="max-width:150px" type="text" class="form-control"
										value=""""),_display_(/*28.19*/mantTransacReport/*28.36*/.getId()),format.raw/*28.44*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>FECHA:</strong></td>
						        <td style="text-align:left; width: 70%" colspan="3">
						        	<input style="max-width:150px" type="date" class="form-control form-control-sm" 
										value=""""),_display_(/*36.19*/mantTransacReport/*36.36*/.getFecha()),format.raw/*36.47*/("""" 
										readonly>
						        </td>
						        <td style="text-align:center; width: 70%" width="70px">
									"""),_display_(if( ! mantTransacReport.getDocAnexo().equals("0"))/*40.61*/{_display_(Seq[Any](format.raw/*40.62*/("""
										"""),format.raw/*41.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*41.52*/mantTransacReport/*41.69*/.getDocAnexo()),format.raw/*41.83*/("""')">
											<kbd style="background-color: #7F8C8D">docum</kbd>
										</a>
									""")))} else {null} ),format.raw/*44.11*/("""
								"""),format.raw/*45.9*/("""</td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>USER_MADA:</strong></td>
						        <td style="text-align:left; width: 70%" colspan="4">
						        	<input type="text" class="form-control"
										value=""""),_display_(/*51.19*/mantTransacReport/*51.36*/.getUserNameAdam()),format.raw/*51.54*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>PERFIL:</strong></td>
						        <td style="text-align:left; width: 70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*59.19*/mantTransacReport/*59.36*/.getNameActorPersonal()),format.raw/*59.59*/(""""
										readonly>
						        </td>
							</tr>
						</thead>
					</table>
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<thead>
							<tr>
								<td style="text-align:left;"><strong>MECANICO:</strong></td>
						        <td style="text-align:left; width: 70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*71.19*/mantTransacReport/*71.36*/.getFullNameMecanico()),format.raw/*71.58*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>ORIGEN/TIPO:</strong></td>
						        <td style="text-align:left; width: 70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*79.19*/mantTransacReport/*79.36*/.getNameTipoPersonal()),format.raw/*79.58*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>TIPO DE MANTENCION (*):</strong></td>
						        <td style="text-align:left; width: 70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*87.19*/mantTransacReport/*87.36*/.getNameTipoMantencion().toUpperCase()),format.raw/*87.74*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>EQUIPO:</strong></td>
						        <td style="text-align:left; width: 70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*95.19*/mantTransacReport/*95.36*/.getCodigoEquipo()),format.raw/*95.54*/(""" """),format.raw/*95.55*/("""- """),_display_(/*95.58*/mantTransacReport/*95.75*/.getNameEquipo()),format.raw/*95.91*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>TIPO DE PLAN:</strong></td>
						        <td style="text-align:left; width: 70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*103.19*/mantTransacReport/*103.36*/.getNamePlanMantencion()),format.raw/*103.60*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>"""),_display_(/*108.47*/mapDiccionario/*108.61*/.get("BODEGA")),format.raw/*108.75*/("""/PROYECTO:</strong></td>
						        <td style="text-align:left; width: 70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*111.19*/mantTransacReport/*111.36*/.getNameBodega()),format.raw/*111.52*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>TIPO """),_display_(/*116.52*/mapDiccionario/*116.66*/.get("BODEGA")),format.raw/*116.80*/("""/PROYECTO:</strong></td>
						        <td style="text-align:left; width: 70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*119.19*/mantTransacReport/*119.36*/.getNameTipoBodega()),format.raw/*119.56*/(""""
										readonly>
						        </td>
							</tr>
							
							<tr>
								<td style="text-align:left;"><strong>ESTADO OPERACIONAL (*):</strong></td>
						        <td style="text-align:left; width: 70%" colspan="4">
						        	<input type="text" class="form-control"
										value=""""),_display_(/*128.19*/mantTransacReport/*128.36*/.getNameEstadoOperacional()),format.raw/*128.63*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>ESTADO EN TALLER (*):</strong></td>
						        <td style="text-align:left; width: 70%" colspan="4">
						        	<input type="text" class="form-control"
										value=""""),_display_(/*136.19*/mantTransacReport/*136.36*/.getNameEstadoEnTaller()),format.raw/*136.60*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>ACTIVIDAD (*):</strong></td>
						        <td style="text-align:left; width: 70%" colspan="4">
						        	<input type="text" class="form-control"
										value=""""),_display_(/*144.19*/mantTransacReport/*144.36*/.getNameActividad()),format.raw/*144.55*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>TIPO DE ACTIVIDAD (*):</strong></td>
						        <td style="text-align:left; width: 70%" colspan="4">
						        	<input type="text" class="form-control"
										value=""""),_display_(/*152.19*/mantTransacReport/*152.36*/.getNameTipoActividad()),format.raw/*152.59*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>ITEM INTERVENIDO (*):</strong></td>
						        <td style="text-align:left; width: 70%" colspan="4">
						        	<input type="text" class="form-control"
										value=""""),_display_(/*160.19*/mantTransacReport/*160.36*/.getNameItemIntervenido()),format.raw/*160.61*/(""""
										readonly>
						        </td>
							</tr>
							
							<tr>
								<td style="text-align:left;"><strong>COMPONENTES (*):</strong></td>
						        <td style="text-align:left; width: 70%" colspan="4">
						        	<table id="tblComponentesC" class="table table-sm table-bordered table-condensed table-fluid">
										<thead>
											<tr>
												<th style="text-align:left; vertical-align: middle; width: 70%">Componentes</th>
												<th width="10%" style="text-align:left; vertical-align: middle;">Cantidad</th>
											</tr>
										</thead>
										<tbody>
											"""),_display_(/*176.13*/for(lista <- mantTransacComponentes) yield /*176.49*/{_display_(Seq[Any](format.raw/*176.50*/("""
												"""),format.raw/*177.13*/("""<tr>
													<td style="text-align:left;vertical-align:middle">
														<input type="text" class="form-control"
															value=""""),_display_(/*180.24*/lista/*180.29*/.getNameComponente()),format.raw/*180.49*/(""""
															readonly>
									    			</td>
													<td>
														<input type="text" class="form-control form-control-sm left" style="max-width:200px"
															value=""""),_display_(/*185.24*/utilities/*185.33*/.DecimalFormato.formato(lista.getCantidad(),2)),format.raw/*185.79*/(""""
															readonly>
													</td>
												</tr>
											""")))}),format.raw/*189.13*/("""
											
										"""),format.raw/*191.11*/("""</tbody>
						        	</table>
								</td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>Fecha_INI:</strong></td>
						        <td style="text-align:left">
									<input type="date" class="form-control form-control-sm left" style="max-width:150px"
										value=""""),_display_(/*199.19*/mantTransacReport/*199.36*/.getFechaIni),format.raw/*199.48*/("""">
						        </td>
								<td style="text-align:left;"><strong>Fecha_TER:</strong></td>
						        <td style="text-align:left" colspan="2">
									<input type="date" class="form-control form-control-sm left"  style="max-width:150px"
										value=""""),_display_(/*204.19*/mantTransacReport/*204.36*/.getFechaFin),format.raw/*204.48*/("""">
						        </td>
							</tr>	
							<tr>
								<td style="text-align:left;"><strong>Hr_INI:</strong></td>
						        <td style="text-align:left">
									"""),_display_(if( ! mantTransacReport.getHoraIni().equals("00:00:00") && mantTransacReport.getHoraFin().equals("00:00:00"))/*210.120*/{_display_(Seq[Any](format.raw/*210.121*/("""
										"""),format.raw/*211.11*/("""<input type="time" class="form-control form-control-sm left" style="max-width:100px"
											value=""""),_display_(/*212.20*/mantTransacReport/*212.37*/.getHoraIni()),format.raw/*212.50*/(""""
											readonly>
									""")))}else/*214.15*/{_display_(Seq[Any](format.raw/*214.16*/("""
										"""),format.raw/*215.11*/("""<input type="time" class="form-control form-control-sm left" style="max-width:100px"
											readonly>
									""")))}),format.raw/*217.11*/("""
						        """),format.raw/*218.15*/("""</td>
								<td style="text-align:left;"><strong>Hr_TER:</strong></td>
						        <td style="text-align:left" colspan="2">
									"""),_display_(if( ! mantTransacReport.getHoraIni().equals("00:00:00") && mantTransacReport.getHoraFin().equals("00:00:00"))/*221.120*/{_display_(Seq[Any](format.raw/*221.121*/("""
										"""),format.raw/*222.11*/("""<input type="time" class="form-control form-control-sm left" style="max-width:100px"
											value=""""),_display_(/*223.20*/mantTransacReport/*223.37*/.getHoraFin()),format.raw/*223.50*/(""""
											readonly>
									""")))}else/*225.15*/{_display_(Seq[Any](format.raw/*225.16*/("""
										"""),format.raw/*226.11*/("""<input type="time" class="form-control form-control-sm left" style="max-width:100px"
											readonly>
									""")))}),format.raw/*228.11*/("""
						        """),format.raw/*229.15*/("""</td>
							</tr>
							
							<tr>
								<td style="text-align:left;"><strong><div id="lectIni_oper">Lect_INI:</div></strong></td>
						        <td style="text-align:left">
									<input type="text" class="form-control form-control-sm left" style="max-width:200px"
										value=""""),_display_(/*236.19*/utilities/*236.28*/.DecimalFormato.formato(mantTransacReport.getLectAnterior(),2)),format.raw/*236.90*/(""""
										readonly>
						        </td>
								<td style="text-align:left;"><strong><div id="lectTer_oper">Lect_TER:</div></strong></td>
						        <td style="text-align:left" colspan="2">
									<input type="text" class="form-control form-control-sm left" style="max-width:200px"
										value=""""),_display_(/*242.19*/utilities/*242.28*/.DecimalFormato.formato(mantTransacReport.getLectActual(),2)),format.raw/*242.88*/(""""
										readonly>
						        </td>
							</tr>
							
							<tr>
								<td style="text-align:left;"><strong><div id="cant_oper">CANT:</div></strong></td>
						        <td style="text-align:left; width: 70%" colspan="4">
									<input type="text" class="form-control form-control-sm left" style="max-width:200px"
										value=""""),_display_(/*251.19*/mantTransacReport/*251.36*/.getLectDif()),format.raw/*251.49*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>DESCRIPCION TRABAJO:</strong></td>
						        <td style="text-align:left; width: 70%" colspan="4">
									<textarea class="form-control form-control-sm" rows="1" 
										readonly>"""),_display_(/*259.21*/mantTransacReport/*259.38*/.getDescTrabajo()),format.raw/*259.55*/("""</textarea>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>ESTADO FINAL:</strong></td>
						        <td style="text-align:left; width: 70%" colspan="4">
									<textarea class="form-control form-control-sm" rows="1" 
										readonly>"""),_display_(/*266.21*/mantTransacReport/*266.38*/.getEstadoFinal()),format.raw/*266.55*/("""</textarea>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>OBSERVACIONES:</strong></td>
						        <td style="text-align:left; width: 70%" colspan="4">
									<textarea class="form-control form-control-sm" rows="2" 
										readonly>"""),_display_(/*273.21*/mantTransacReport/*273.38*/.getObservaciones()),format.raw/*273.57*/("""</textarea>
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
										<img src="data:image/jpeg;base64, """),_display_(/*291.46*/mantTransacReport/*291.63*/.getFirmaPDFautorizador()),format.raw/*291.88*/("""" height="60px" />
									</div>
								</td>
							</tr>	
						
							<tr>
								<td colspan="5" style="text-align:center;">
									<button type="button" class="btn btn-sm btn-success" style="font-size: 10px;"onclick="grabar();" >GRABAR</button>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<button type="button" class="btn btn-sm btn-warning" style="font-size: 10px;" onclick="location.href = '/routes3/mantListarReports1Get/"""),_display_(/*300.146*/desdeAAMMDD),format.raw/*300.157*/(""","""),_display_(/*300.159*/hastaAAMMDD),format.raw/*300.170*/(""","""),_display_(/*300.172*/id_equipo),format.raw/*300.181*/("""'">VOLVER</button>
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
""")))}),format.raw/*315.2*/("""


"""),format.raw/*318.1*/("""<!-- CONFIGURA PAD -->

<script src=""""),_display_(/*320.15*/routes/*320.21*/.Assets.versioned("javascripts/signature_pad.min.js")),format.raw/*320.74*/(""""></script>
	<script>
		let signaturePad = new SignaturePad(document.getElementById('signature-pad'), """),format.raw/*322.81*/("""{"""),format.raw/*322.82*/("""
		  """),format.raw/*323.5*/("""backgroundColor: 'rgba(255, 255, 255, 0)',
		  penColor: 'rgb(0, 0, 0)'
		"""),format.raw/*325.3*/("""}"""),format.raw/*325.4*/(""");
	</script>
	
	<style>
		.wrapper """),format.raw/*329.12*/("""{"""),format.raw/*329.13*/("""
		  """),format.raw/*330.5*/("""position: relative;
		  width: 350px;
		  height: 90px;
		  -moz-user-select: none;
		  -webkit-user-select: none;
		  -ms-user-select: none;
		  user-select: none;
		"""),format.raw/*337.3*/("""}"""),format.raw/*337.4*/("""

		"""),format.raw/*339.3*/(""".signature-pad """),format.raw/*339.18*/("""{"""),format.raw/*339.19*/("""
		  """),format.raw/*340.5*/("""position: absolute;
		  left: 0;
		  top: 0;
		  width:350px;
		  height:90px;
		"""),format.raw/*345.3*/("""}"""),format.raw/*345.4*/("""
	"""),format.raw/*346.2*/("""</style>
<!-- FIN PAD -->

	
<script>
	$(document).ready(function() """),format.raw/*351.31*/("""{"""),format.raw/*351.32*/("""
 		"""),format.raw/*352.4*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*353.2*/("""}"""),format.raw/*353.3*/(""" """),format.raw/*353.4*/(""");
	const descargaDocumento = (nombreDOC) => """),format.raw/*354.43*/("""{"""),format.raw/*354.44*/("""
		  """),format.raw/*355.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*357.2*/("""}"""),format.raw/*357.3*/("""
	"""),format.raw/*358.2*/("""function grabar()"""),format.raw/*358.19*/("""{"""),format.raw/*358.20*/("""
			"""),format.raw/*359.4*/("""let data = signaturePad.toDataURL('image/png');
			let encodedImage = data.split(",");
			$("#firma").val(encodedImage[1]);
			$("#grabarFirmaOperador").submit();
	"""),format.raw/*363.2*/("""}"""),format.raw/*363.3*/("""
	"""),format.raw/*364.2*/("""window.onload = function () """),format.raw/*364.30*/("""{"""),format.raw/*364.31*/("""
	    """),format.raw/*365.6*/("""var canvas = document.getElementById('signature-pad');
	    var ctx = canvas.getContext('2d');
	    var img = new Image();
	    img.onload = function () """),format.raw/*368.31*/("""{"""),format.raw/*368.32*/("""
	        """),format.raw/*369.10*/("""ctx.drawImage(img, 0, 0, canvas.width, canvas.height);
	    """),format.raw/*370.6*/("""}"""),format.raw/*370.7*/(""";
	    img.src = "data:image/png;base64,"""),_display_(/*371.40*/mantTransacReport/*371.57*/.getFirmaPDFoperador()),format.raw/*371.79*/("""";
	"""),format.raw/*372.2*/("""}"""),format.raw/*372.3*/(""";
</script>
	

"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],mantTransacReport:tables.MantTransacReport,mantTransacComponentes:List[tables.MantTransacComponentes],desdeAAMMDD:String,hastaAAMMDD:String,id_equipo:Long): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,mantTransacReport,mantTransacComponentes,desdeAAMMDD,hastaAAMMDD,id_equipo)

  def f:((Map[String,String],Map[String,String],tables.MantTransacReport,List[tables.MantTransacComponentes],String,String,Long) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,mantTransacReport,mantTransacComponentes,desdeAAMMDD,hastaAAMMDD,id_equipo) => apply(mapDiccionario,mapPermiso,mantTransacReport,mantTransacComponentes,desdeAAMMDD,hastaAAMMDD,id_equipo)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuMantencion/mantWebReportMecanicoFirmaMec.scala.html
                  HASH: ba64bcd580f953275ec4b044f45bb1abb17d27d8
                  MATRIX: 1093->1|1420->235|1450->240|1466->248|1505->250|1535->254|1612->305|1703->375|1736->381|2090->708|2116->725|2145->733|2227->788|2259->799|2341->854|2373->865|2454->919|2484->928|2858->1275|2884->1292|2913->1300|3254->1614|3280->1631|3312->1642|3512->1815|3551->1816|3590->1827|3658->1868|3684->1885|3719->1899|3855->1991|3891->2000|4159->2241|4185->2258|4224->2276|4519->2544|4545->2561|4589->2584|5008->2976|5034->2993|5077->3015|5377->3288|5403->3305|5446->3327|5757->3611|5783->3628|5842->3666|6137->3934|6163->3951|6202->3969|6231->3970|6261->3973|6287->3990|6324->4006|6626->4280|6653->4297|6699->4321|6840->4434|6864->4448|6900->4462|7087->4621|7114->4638|7152->4654|7298->4772|7322->4786|7358->4800|7545->4959|7572->4976|7614->4996|7940->5294|7967->5311|8016->5338|8332->5626|8359->5643|8405->5667|8714->5948|8741->5965|8782->5984|9099->6273|9126->6290|9171->6313|9487->6601|9514->6618|9561->6643|10201->7255|10254->7291|10294->7292|10336->7305|10510->7451|10525->7456|10567->7476|10784->7665|10803->7674|10871->7720|10979->7796|11031->7819|11356->8116|11383->8133|11417->8145|11706->8406|11733->8423|11767->8435|12074->8713|12115->8714|12155->8725|12287->8829|12314->8846|12349->8859|12406->8896|12446->8897|12486->8908|12634->9024|12678->9039|12954->9286|12995->9287|13035->9298|13167->9402|13194->9419|13229->9432|13286->9469|13326->9470|13366->9481|13514->9597|13558->9612|13878->9904|13897->9913|13981->9975|14316->10282|14335->10291|14417->10351|14790->10696|14817->10713|14852->10726|15180->11026|15207->11043|15246->11060|15557->11343|15584->11360|15623->11377|15935->11661|15962->11678|16003->11697|16790->12456|16817->12473|16864->12498|17413->13018|17447->13029|17478->13031|17512->13042|17543->13044|17575->13053|17905->13352|17936->13355|18002->13393|18018->13399|18093->13452|18224->13554|18254->13555|18287->13560|18389->13634|18418->13635|18483->13671|18513->13672|18546->13677|18741->13844|18770->13845|18802->13849|18846->13864|18876->13865|18909->13870|19018->13951|19047->13952|19077->13954|19174->14022|19204->14023|19236->14027|19330->14093|19359->14094|19388->14095|19462->14140|19492->14141|19525->14146|19657->14250|19686->14251|19716->14253|19762->14270|19792->14271|19824->14275|20016->14439|20045->14440|20075->14442|20132->14470|20162->14471|20196->14477|20378->14630|20408->14631|20447->14641|20535->14701|20564->14702|20633->14743|20660->14760|20704->14782|20736->14786|20765->14787
                  LINES: 28->1|34->3|37->6|37->6|37->6|40->9|41->10|41->10|43->12|47->16|47->16|47->16|48->17|48->17|49->18|49->18|50->19|50->19|59->28|59->28|59->28|67->36|67->36|67->36|71->40|71->40|72->41|72->41|72->41|72->41|75->44|76->45|82->51|82->51|82->51|90->59|90->59|90->59|102->71|102->71|102->71|110->79|110->79|110->79|118->87|118->87|118->87|126->95|126->95|126->95|126->95|126->95|126->95|126->95|134->103|134->103|134->103|139->108|139->108|139->108|142->111|142->111|142->111|147->116|147->116|147->116|150->119|150->119|150->119|159->128|159->128|159->128|167->136|167->136|167->136|175->144|175->144|175->144|183->152|183->152|183->152|191->160|191->160|191->160|207->176|207->176|207->176|208->177|211->180|211->180|211->180|216->185|216->185|216->185|220->189|222->191|230->199|230->199|230->199|235->204|235->204|235->204|241->210|241->210|242->211|243->212|243->212|243->212|245->214|245->214|246->215|248->217|249->218|252->221|252->221|253->222|254->223|254->223|254->223|256->225|256->225|257->226|259->228|260->229|267->236|267->236|267->236|273->242|273->242|273->242|282->251|282->251|282->251|290->259|290->259|290->259|297->266|297->266|297->266|304->273|304->273|304->273|322->291|322->291|322->291|331->300|331->300|331->300|331->300|331->300|331->300|346->315|349->318|351->320|351->320|351->320|353->322|353->322|354->323|356->325|356->325|360->329|360->329|361->330|368->337|368->337|370->339|370->339|370->339|371->340|376->345|376->345|377->346|382->351|382->351|383->352|384->353|384->353|384->353|385->354|385->354|386->355|388->357|388->357|389->358|389->358|389->358|390->359|394->363|394->363|395->364|395->364|395->364|396->365|399->368|399->368|400->369|401->370|401->370|402->371|402->371|402->371|403->372|403->372
                  -- GENERATED --
              */
          