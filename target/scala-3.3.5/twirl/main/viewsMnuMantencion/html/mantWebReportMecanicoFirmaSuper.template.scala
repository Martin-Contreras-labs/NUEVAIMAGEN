
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

object mantWebReportMecanicoFirmaSuper extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template7[Map[String,String],Map[String,String],tables.MantTransacReport,List[tables.MantTransacComponentes],String,String,Long,play.twirl.api.HtmlFormat.Appendable] {

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
				<form id="grabarFirmaSupervisor" action="/routes3/mantWebGrabarFirmaAutorizador/" enctype="multipart/form-data" method="POST" enctype="multipart/form-data">
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
										value=""""),_display_(/*27.19*/mantTransacReport/*27.36*/.getId()),format.raw/*27.44*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>FECHA:</strong></td>
						        <td style="text-align:left; width: 70%" colspan="3">
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
						        <td style="text-align:left; width: 70%" colspan="4">
						        	<input type="text" class="form-control"
										value=""""),_display_(/*50.19*/mantTransacReport/*50.36*/.getUserNameAdam()),format.raw/*50.54*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>PERFIL:</strong></td>
						        <td style="text-align:left; width: 70%" colspan="4">
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
						        <td style="text-align:left; width: 70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*70.19*/mantTransacReport/*70.36*/.getFullNameMecanico()),format.raw/*70.58*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>ORIGEN/TIPO:</strong></td>
						        <td style="text-align:left; width: 70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*78.19*/mantTransacReport/*78.36*/.getNameTipoPersonal()),format.raw/*78.58*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>TIPO DE MANTENCION (*):</strong></td>
						        <td style="text-align:left; width: 70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*86.19*/mantTransacReport/*86.36*/.getNameTipoMantencion().toUpperCase()),format.raw/*86.74*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>EQUIPO:</strong></td>
						        <td style="text-align:left; width: 70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*94.19*/mantTransacReport/*94.36*/.getCodigoEquipo()),format.raw/*94.54*/(""" """),format.raw/*94.55*/("""- """),_display_(/*94.58*/mantTransacReport/*94.75*/.getNameEquipo()),format.raw/*94.91*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>TIPO DE PLAN:</strong></td>
						        <td style="text-align:left; width: 70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*102.19*/mantTransacReport/*102.36*/.getNamePlanMantencion()),format.raw/*102.60*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>"""),_display_(/*107.47*/mapDiccionario/*107.61*/.get("BODEGA")),format.raw/*107.75*/("""/PROYECTO:</strong></td>
						        <td style="text-align:left; width: 70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*110.19*/mantTransacReport/*110.36*/.getNameBodega()),format.raw/*110.52*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>TIPO """),_display_(/*115.52*/mapDiccionario/*115.66*/.get("BODEGA")),format.raw/*115.80*/("""/PROYECTO:</strong></td>
						        <td style="text-align:left; width: 70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*118.19*/mantTransacReport/*118.36*/.getNameTipoBodega()),format.raw/*118.56*/(""""
										readonly>
						        </td>
							</tr>
							
							<tr>
								<td style="text-align:left;"><strong>ESTADO OPERACIONAL (*):</strong></td>
						        <td style="text-align:left; width: 70%" colspan="4">
						        	<input type="text" class="form-control"
										value=""""),_display_(/*127.19*/mantTransacReport/*127.36*/.getNameEstadoOperacional()),format.raw/*127.63*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>ESTADO EN TALLER (*):</strong></td>
						        <td style="text-align:left; width: 70%" colspan="4">
						        	<input type="text" class="form-control"
										value=""""),_display_(/*135.19*/mantTransacReport/*135.36*/.getNameEstadoEnTaller()),format.raw/*135.60*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>ACTIVIDAD (*):</strong></td>
						        <td style="text-align:left; width: 70%" colspan="4">
						        	<input type="text" class="form-control"
										value=""""),_display_(/*143.19*/mantTransacReport/*143.36*/.getNameActividad()),format.raw/*143.55*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>TIPO DE ACTIVIDAD (*):</strong></td>
						        <td style="text-align:left; width: 70%" colspan="4">
						        	<input type="text" class="form-control"
										value=""""),_display_(/*151.19*/mantTransacReport/*151.36*/.getNameTipoActividad()),format.raw/*151.59*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>ITEM INTERVENIDO (*):</strong></td>
						        <td style="text-align:left; width: 70%" colspan="4">
						        	<input type="text" class="form-control"
										value=""""),_display_(/*159.19*/mantTransacReport/*159.36*/.getNameItemIntervenido()),format.raw/*159.61*/(""""
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
						        <td style="text-align:left; width: 70%" colspan="4">
									<input type="text" class="form-control form-control-sm left" style="max-width:200px"
										value=""""),_display_(/*250.19*/mantTransacReport/*250.36*/.getLectDif()),format.raw/*250.49*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>DESCRIPCION TRABAJO:</strong></td>
						        <td style="text-align:left; width: 70%" colspan="4">
									<textarea class="form-control form-control-sm" rows="1" 
										readonly>"""),_display_(/*258.21*/mantTransacReport/*258.38*/.getDescTrabajo()),format.raw/*258.55*/("""</textarea>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>ESTADO FINAL:</strong></td>
						        <td style="text-align:left; width: 70%" colspan="4">
									<textarea class="form-control form-control-sm" rows="1" 
										readonly>"""),_display_(/*265.21*/mantTransacReport/*265.38*/.getEstadoFinal()),format.raw/*265.55*/("""</textarea>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>OBSERVACIONES:</strong></td>
						        <td style="text-align:left; width: 70%" colspan="4">
									<textarea class="form-control form-control-sm" rows="2" 
										readonly>"""),_display_(/*272.21*/mantTransacReport/*272.38*/.getObservaciones()),format.raw/*272.57*/("""</textarea>
						        </td>
							</tr>
							
							<tr>
								<td style="text-align:left;"  colspan="5">
									FIRMA MECANICO:<br>
									<div class="wrapper" align="center">
										<img src="data:image/jpeg;base64, """),_display_(/*280.46*/mantTransacReport/*280.63*/.getFirmaPDFoperador()),format.raw/*280.85*/(""""  height="60px" />
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
									<button type="button" class="btn btn-sm btn-warning" style="font-size: 10px;" onclick="location.href = '/routes3/mantListarReports1Get/"""),_display_(/*299.146*/desdeAAMMDD),format.raw/*299.157*/(""","""),_display_(/*299.159*/hastaAAMMDD),format.raw/*299.170*/(""","""),_display_(/*299.172*/id_equipo),format.raw/*299.181*/("""'">VOLVER</button>
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
		let signaturePad2 = new SignaturePad(document.getElementById('signature-pad2'), """),format.raw/*321.83*/("""{"""),format.raw/*321.84*/("""
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
			"""),format.raw/*358.4*/("""let data = signaturePad2.toDataURL('image/png');
			let encodedImage = data.split(",");
			$("#firma").val(encodedImage[1]);
			$("#grabarFirmaSupervisor").submit();
	"""),format.raw/*362.2*/("""}"""),format.raw/*362.3*/("""
	"""),format.raw/*363.2*/("""window.onload = function () """),format.raw/*363.30*/("""{"""),format.raw/*363.31*/("""
	    """),format.raw/*364.6*/("""var canvas = document.getElementById('signature-pad2');
	    var ctx = canvas.getContext('2d');
	    var img = new Image();
	    img.onload = function () """),format.raw/*367.31*/("""{"""),format.raw/*367.32*/("""
	        """),format.raw/*368.10*/("""ctx.drawImage(img, 0, 0, canvas.width, canvas.height);
	    """),format.raw/*369.6*/("""}"""),format.raw/*369.7*/(""";
	    img.src = "data:image/png;base64,"""),_display_(/*370.40*/mantTransacReport/*370.57*/.getFirmaPDFautorizador()),format.raw/*370.82*/("""";
	"""),format.raw/*371.2*/("""}"""),format.raw/*371.3*/(""";
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
                  SOURCE: app/viewsMnuMantencion/mantWebReportMecanicoFirmaSuper.scala.html
                  HASH: 695c9804c7dc5f7a55022584a9b485355a8087d2
                  MATRIX: 1095->1|1422->235|1452->240|1468->248|1507->250|1537->254|1614->305|1705->375|1738->381|2097->713|2123->730|2152->738|2234->793|2266->804|2348->859|2380->870|2461->924|2491->933|2859->1274|2885->1291|2914->1299|3255->1613|3281->1630|3313->1641|3502->1803|3541->1804|3580->1815|3648->1856|3674->1873|3709->1887|3845->1979|3881->1988|4149->2229|4175->2246|4214->2264|4509->2532|4535->2549|4579->2572|4998->2964|5024->2981|5067->3003|5367->3276|5393->3293|5436->3315|5747->3599|5773->3616|5832->3654|6127->3922|6153->3939|6192->3957|6221->3958|6251->3961|6277->3978|6314->3994|6616->4268|6643->4285|6689->4309|6830->4422|6854->4436|6890->4450|7077->4609|7104->4626|7142->4642|7288->4760|7312->4774|7348->4788|7535->4947|7562->4964|7604->4984|7930->5282|7957->5299|8006->5326|8322->5614|8349->5631|8395->5655|8704->5936|8731->5953|8772->5972|9089->6261|9116->6278|9161->6301|9477->6589|9504->6606|9551->6631|10191->7243|10244->7279|10284->7280|10326->7293|10500->7439|10515->7444|10557->7464|10774->7653|10793->7662|10861->7708|10969->7784|11021->7807|11346->8104|11373->8121|11407->8133|11696->8394|11723->8411|11757->8423|12064->8701|12105->8702|12145->8713|12277->8817|12304->8834|12339->8847|12396->8884|12436->8885|12476->8896|12624->9012|12668->9027|12944->9274|12985->9275|13025->9286|13157->9390|13184->9407|13219->9420|13276->9457|13316->9458|13356->9469|13504->9585|13548->9600|13868->9892|13887->9901|13971->9963|14306->10270|14325->10279|14407->10339|14780->10684|14807->10701|14842->10714|15170->11014|15197->11031|15236->11048|15547->11331|15574->11348|15613->11365|15925->11649|15952->11666|15993->11685|16257->11921|16284->11938|16328->11960|17406->13009|17440->13020|17471->13022|17505->13033|17536->13035|17568->13044|17898->13343|17929->13346|17995->13384|18011->13390|18086->13443|18219->13547|18249->13548|18282->13553|18384->13627|18413->13628|18478->13664|18508->13665|18541->13670|18736->13837|18765->13838|18797->13842|18841->13857|18871->13858|18904->13863|19013->13944|19042->13945|19072->13947|19169->14015|19199->14016|19231->14020|19325->14086|19354->14087|19383->14088|19457->14133|19487->14134|19520->14139|19652->14243|19681->14244|19711->14246|19757->14263|19787->14264|19819->14268|20014->14435|20043->14436|20073->14438|20130->14466|20160->14467|20194->14473|20377->14627|20407->14628|20446->14638|20534->14698|20563->14699|20632->14740|20659->14757|20706->14782|20738->14786|20767->14787
                  LINES: 28->1|34->3|37->6|37->6|37->6|40->9|41->10|41->10|43->12|47->16|47->16|47->16|48->17|48->17|49->18|49->18|50->19|50->19|58->27|58->27|58->27|66->35|66->35|66->35|70->39|70->39|71->40|71->40|71->40|71->40|74->43|75->44|81->50|81->50|81->50|89->58|89->58|89->58|101->70|101->70|101->70|109->78|109->78|109->78|117->86|117->86|117->86|125->94|125->94|125->94|125->94|125->94|125->94|125->94|133->102|133->102|133->102|138->107|138->107|138->107|141->110|141->110|141->110|146->115|146->115|146->115|149->118|149->118|149->118|158->127|158->127|158->127|166->135|166->135|166->135|174->143|174->143|174->143|182->151|182->151|182->151|190->159|190->159|190->159|206->175|206->175|206->175|207->176|210->179|210->179|210->179|215->184|215->184|215->184|219->188|221->190|229->198|229->198|229->198|234->203|234->203|234->203|240->209|240->209|241->210|242->211|242->211|242->211|244->213|244->213|245->214|247->216|248->217|251->220|251->220|252->221|253->222|253->222|253->222|255->224|255->224|256->225|258->227|259->228|266->235|266->235|266->235|272->241|272->241|272->241|281->250|281->250|281->250|289->258|289->258|289->258|296->265|296->265|296->265|303->272|303->272|303->272|311->280|311->280|311->280|330->299|330->299|330->299|330->299|330->299|330->299|345->314|348->317|350->319|350->319|350->319|352->321|352->321|353->322|355->324|355->324|359->328|359->328|360->329|367->336|367->336|369->338|369->338|369->338|370->339|375->344|375->344|376->345|381->350|381->350|382->351|383->352|383->352|383->352|384->353|384->353|385->354|387->356|387->356|388->357|388->357|388->357|389->358|393->362|393->362|394->363|394->363|394->363|395->364|398->367|398->367|399->368|400->369|400->369|401->370|401->370|401->370|402->371|402->371
                  -- GENERATED --
              */
          