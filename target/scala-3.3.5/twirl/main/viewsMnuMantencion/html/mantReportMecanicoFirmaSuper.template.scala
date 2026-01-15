
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

object mantReportMecanicoFirmaSuper extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template7[Map[String,String],Map[String,String],utilities.UserMnu,tables.MantTransacReport,List[tables.MantTransacComponentes],String,String,play.twirl.api.HtmlFormat.Appendable] {

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
				<form id="grabarFirmaSupervisor" action="/routes3/mantGrabarFirmaAutorizador/" enctype="multipart/form-data" method="POST" enctype="multipart/form-data">
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
								<td style="text-align:left;"><strong>MECANICO:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*69.19*/mantTransacReport/*69.36*/.getFullNameMecanico()),format.raw/*69.58*/(""""
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
								<td style="text-align:left;"><strong>TIPO DE MANTENCION (*):</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*85.19*/mantTransacReport/*85.36*/.getNameTipoMantencion().toUpperCase()),format.raw/*85.74*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>EQUIPO:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*93.19*/mantTransacReport/*93.36*/.getCodigoEquipo()),format.raw/*93.54*/(""" """),format.raw/*93.55*/("""- """),_display_(/*93.58*/mantTransacReport/*93.75*/.getNameEquipo()),format.raw/*93.91*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>TIPO DE PLAN:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*101.19*/mantTransacReport/*101.36*/.getNamePlanMantencion()),format.raw/*101.60*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>"""),_display_(/*106.47*/mapDiccionario/*106.61*/.get("BODEGA")),format.raw/*106.75*/("""/PROYECTO:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*109.19*/mantTransacReport/*109.36*/.getNameBodega()),format.raw/*109.52*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>TIPO """),_display_(/*114.52*/mapDiccionario/*114.66*/.get("BODEGA")),format.raw/*114.80*/("""/PROYECTO:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*117.19*/mantTransacReport/*117.36*/.getNameTipoBodega()),format.raw/*117.56*/(""""
										readonly>
						        </td>
							</tr>
							
							<tr>
								<td style="text-align:left;"><strong>ESTADO OPERACIONAL (*):</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
						        	<input type="text" class="form-control"
										value=""""),_display_(/*126.19*/mantTransacReport/*126.36*/.getNameEstadoOperacional()),format.raw/*126.63*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>ESTADO EN TALLER (*):</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
						        	<input type="text" class="form-control"
										value=""""),_display_(/*134.19*/mantTransacReport/*134.36*/.getNameEstadoEnTaller()),format.raw/*134.60*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>ACTIVIDAD (*):</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
						        	<input type="text" class="form-control"
										value=""""),_display_(/*142.19*/mantTransacReport/*142.36*/.getNameActividad()),format.raw/*142.55*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>TIPO DE ACTIVIDAD (*):</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
						        	<input type="text" class="form-control"
										value=""""),_display_(/*150.19*/mantTransacReport/*150.36*/.getNameTipoActividad()),format.raw/*150.59*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>ITEM INTERVENIDO (*):</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
						        	<input type="text" class="form-control"
										value=""""),_display_(/*158.19*/mantTransacReport/*158.36*/.getNameItemIntervenido()),format.raw/*158.61*/(""""
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
											"""),_display_(/*174.13*/for(lista <- mantTransacComponentes) yield /*174.49*/{_display_(Seq[Any](format.raw/*174.50*/("""
												"""),format.raw/*175.13*/("""<tr>
													<td style="text-align:left;vertical-align:middle">
														<input type="text" class="form-control"
															value=""""),_display_(/*178.24*/lista/*178.29*/.getNameComponente()),format.raw/*178.49*/(""""
															readonly>
									    			</td>
													<td>
														<input type="text" class="form-control form-control-sm left" style="max-width:200px"
															value=""""),_display_(/*183.24*/utilities/*183.33*/.DecimalFormato.formato(lista.getCantidad(),2)),format.raw/*183.79*/(""""
															readonly>
													</td>
												</tr>
											""")))}),format.raw/*187.13*/("""
											
										"""),format.raw/*189.11*/("""</tbody>
						        	</table>
								</td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>Fecha_INI:</strong></td>
						        <td style="text-align:left">
									<input type="date" class="form-control form-control-sm left" style="max-width:150px"
										value=""""),_display_(/*197.19*/mantTransacReport/*197.36*/.getFechaIni),format.raw/*197.48*/("""">
						        </td>
								<td style="text-align:left;"><strong>Fecha_TER:</strong></td>
						        <td style="text-align:left" colspan="2">
									<input type="date" class="form-control form-control-sm left"  style="max-width:150px"
										value=""""),_display_(/*202.19*/mantTransacReport/*202.36*/.getFechaFin),format.raw/*202.48*/("""">
						        </td>
							</tr>	
							<tr>
								<td style="text-align:left;"><strong>Hr_INI:</strong></td>
						        <td style="text-align:left">
									"""),_display_(if( ! mantTransacReport.getHoraIni().equals("00:00:00") && mantTransacReport.getHoraFin().equals("00:00:00"))/*208.120*/{_display_(Seq[Any](format.raw/*208.121*/("""
										"""),format.raw/*209.11*/("""<input type="time" class="form-control form-control-sm left" style="max-width:100px"
											value=""""),_display_(/*210.20*/mantTransacReport/*210.37*/.getHoraIni()),format.raw/*210.50*/(""""
											readonly>
									""")))}else/*212.15*/{_display_(Seq[Any](format.raw/*212.16*/("""
										"""),format.raw/*213.11*/("""<input type="time" class="form-control form-control-sm left" style="max-width:100px"
											readonly>
									""")))}),format.raw/*215.11*/("""
						        """),format.raw/*216.15*/("""</td>
								<td style="text-align:left;"><strong>Hr_TER:</strong></td>
						        <td style="text-align:left" colspan="2">
									"""),_display_(if( ! mantTransacReport.getHoraIni().equals("00:00:00") && mantTransacReport.getHoraFin().equals("00:00:00"))/*219.120*/{_display_(Seq[Any](format.raw/*219.121*/("""
										"""),format.raw/*220.11*/("""<input type="time" class="form-control form-control-sm left" style="max-width:100px"
											value=""""),_display_(/*221.20*/mantTransacReport/*221.37*/.getHoraFin()),format.raw/*221.50*/(""""
											readonly>
									""")))}else/*223.15*/{_display_(Seq[Any](format.raw/*223.16*/("""
										"""),format.raw/*224.11*/("""<input type="time" class="form-control form-control-sm left" style="max-width:100px"
											readonly>
									""")))}),format.raw/*226.11*/("""
						        """),format.raw/*227.15*/("""</td>
							</tr>
							
							<tr>
								<td style="text-align:left;"><strong><div id="lectIni_oper">Lect_INI:</div></strong></td>
						        <td style="text-align:left">
									<input type="text" class="form-control form-control-sm left" style="max-width:200px"
										value=""""),_display_(/*234.19*/utilities/*234.28*/.DecimalFormato.formato(mantTransacReport.getLectAnterior(),2)),format.raw/*234.90*/(""""
										readonly>
						        </td>
								<td style="text-align:left;"><strong><div id="lectTer_oper">Lect_TER:</div></strong></td>
						        <td style="text-align:left" colspan="2">
									<input type="text" class="form-control form-control-sm left" style="max-width:200px"
										value=""""),_display_(/*240.19*/utilities/*240.28*/.DecimalFormato.formato(mantTransacReport.getLectActual(),2)),format.raw/*240.88*/(""""
										readonly>
						        </td>
							</tr>
							
							<tr>
								<td style="text-align:left;"><strong><div id="cant_oper">CANT:</div></strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control form-control-sm left" style="max-width:200px"
										value=""""),_display_(/*249.19*/mantTransacReport/*249.36*/.getLectDif()),format.raw/*249.49*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>DESCRIPCION TRABAJO:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<textarea class="form-control form-control-sm" rows="1" 
										readonly>"""),_display_(/*257.21*/mantTransacReport/*257.38*/.getDescTrabajo()),format.raw/*257.55*/("""</textarea>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>ESTADO FINAL:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<textarea class="form-control form-control-sm" rows="1" 
										readonly>"""),_display_(/*264.21*/mantTransacReport/*264.38*/.getEstadoFinal()),format.raw/*264.55*/("""</textarea>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>OBSERVACIONES:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<textarea class="form-control form-control-sm" rows="2" 
										readonly>"""),_display_(/*271.21*/mantTransacReport/*271.38*/.getObservaciones()),format.raw/*271.57*/("""</textarea>
						        </td>
							</tr>
							
							<tr>
								<td style="text-align:left;"  colspan="5">
									FIRMA MECANICO:<br>
									<div class="wrapper" align="center">
										<img src="data:image/jpeg;base64, """),_display_(/*279.46*/mantTransacReport/*279.63*/.getFirmaPDFoperador()),format.raw/*279.85*/(""""  height="60px" />
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
									<button type="button" class="btn btn-sm btn-warning" style="font-size: 10px;" onclick="location.href = '/routes3/mantListarReports1Get/"""),_display_(/*298.146*/desdeAAMMDD),format.raw/*298.157*/(""","""),_display_(/*298.159*/hastaAAMMDD),format.raw/*298.170*/("""'">VOLVER</button>
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
""")))}),format.raw/*313.2*/("""


"""),format.raw/*316.1*/("""<!-- CONFIGURA PAD -->

<script src=""""),_display_(/*318.15*/routes/*318.21*/.Assets.versioned("javascripts/signature_pad.min.js")),format.raw/*318.74*/(""""></script>
	<script>
		let signaturePad2 = new SignaturePad(document.getElementById('signature-pad2'), """),format.raw/*320.83*/("""{"""),format.raw/*320.84*/("""
		  """),format.raw/*321.5*/("""backgroundColor: 'rgba(255, 255, 255, 0)',
		  penColor: 'rgb(0, 0, 0)'
		"""),format.raw/*323.3*/("""}"""),format.raw/*323.4*/(""");
	</script>
	
	<style>
		.wrapper """),format.raw/*327.12*/("""{"""),format.raw/*327.13*/("""
		  """),format.raw/*328.5*/("""position: relative;
		  width: 350px;
		  height: 90px;
		  -moz-user-select: none;
		  -webkit-user-select: none;
		  -ms-user-select: none;
		  user-select: none;
		"""),format.raw/*335.3*/("""}"""),format.raw/*335.4*/("""

		"""),format.raw/*337.3*/(""".signature-pad """),format.raw/*337.18*/("""{"""),format.raw/*337.19*/("""
		  """),format.raw/*338.5*/("""position: absolute;
		  left: 0;
		  top: 0;
		  width:350px;
		  height:90px;
		"""),format.raw/*343.3*/("""}"""),format.raw/*343.4*/("""
	"""),format.raw/*344.2*/("""</style>
<!-- FIN PAD -->

	
<script>
	$(document).ready(function() """),format.raw/*349.31*/("""{"""),format.raw/*349.32*/("""
 		"""),format.raw/*350.4*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*351.2*/("""}"""),format.raw/*351.3*/(""" """),format.raw/*351.4*/(""");
	const descargaDocumento = (nombreDOC) => """),format.raw/*352.43*/("""{"""),format.raw/*352.44*/("""
		  """),format.raw/*353.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*355.2*/("""}"""),format.raw/*355.3*/("""
	"""),format.raw/*356.2*/("""function grabar()"""),format.raw/*356.19*/("""{"""),format.raw/*356.20*/("""
			"""),format.raw/*357.4*/("""let data = signaturePad2.toDataURL('image/png');
			let encodedImage = data.split(",");
			$("#firma").val(encodedImage[1]);
			$("#grabarFirmaSupervisor").submit();
	"""),format.raw/*361.2*/("""}"""),format.raw/*361.3*/("""
	"""),format.raw/*362.2*/("""window.onload = function () """),format.raw/*362.30*/("""{"""),format.raw/*362.31*/("""
	    """),format.raw/*363.6*/("""var canvas = document.getElementById('signature-pad2');
	    var ctx = canvas.getContext('2d');
	    var img = new Image();
	    img.onload = function () """),format.raw/*366.31*/("""{"""),format.raw/*366.32*/("""
	        """),format.raw/*367.10*/("""ctx.drawImage(img, 0, 0, canvas.width, canvas.height);
	    """),format.raw/*368.6*/("""}"""),format.raw/*368.7*/(""";
	    img.src = "data:image/png;base64,"""),_display_(/*369.40*/mantTransacReport/*369.57*/.getFirmaPDFautorizador()),format.raw/*369.82*/("""";
	"""),format.raw/*370.2*/("""}"""),format.raw/*370.3*/(""";
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
                  SOURCE: app/viewsMnuMantencion/mantReportMecanicoFirmaSuper.scala.html
                  HASH: 7cf1f40c1bc1c7bfa19034954885362bd2633ef9
                  MATRIX: 1105->1|1443->246|1473->251|1489->259|1528->261|1556->264|1624->312|1652->314|1729->365|1820->435|1853->441|2209->770|2235->787|2264->795|2346->850|2378->861|2460->916|2492->927|2861->1269|2887->1286|2916->1294|3258->1609|3284->1626|3316->1637|3505->1799|3544->1800|3583->1811|3651->1852|3677->1869|3712->1883|3848->1975|3884->1984|4152->2225|4178->2242|4217->2260|4512->2528|4538->2545|4582->2568|5001->2960|5027->2977|5070->2999|5370->3272|5396->3289|5439->3311|5750->3595|5776->3612|5835->3650|6130->3918|6156->3935|6195->3953|6224->3954|6254->3957|6280->3974|6317->3990|6619->4264|6646->4281|6692->4305|6833->4418|6857->4432|6893->4446|7080->4605|7107->4622|7145->4638|7291->4756|7315->4770|7351->4784|7538->4943|7565->4960|7607->4980|7933->5278|7960->5295|8009->5322|8325->5610|8352->5627|8398->5651|8707->5932|8734->5949|8775->5968|9092->6257|9119->6274|9164->6297|9480->6585|9507->6602|9554->6627|10195->7240|10248->7276|10288->7277|10330->7290|10504->7436|10519->7441|10561->7461|10778->7650|10797->7659|10865->7705|10973->7781|11025->7804|11350->8101|11377->8118|11411->8130|11700->8391|11727->8408|11761->8420|12068->8698|12109->8699|12149->8710|12281->8814|12308->8831|12343->8844|12400->8881|12440->8882|12480->8893|12628->9009|12672->9024|12948->9271|12989->9272|13029->9283|13161->9387|13188->9404|13223->9417|13280->9454|13320->9455|13360->9466|13508->9582|13552->9597|13872->9889|13891->9898|13975->9960|14310->10267|14329->10276|14411->10336|14784->10681|14811->10698|14846->10711|15174->11011|15201->11028|15240->11045|15551->11328|15578->11345|15617->11362|15929->11646|15956->11663|15997->11682|16261->11918|16288->11935|16332->11957|17410->13006|17444->13017|17475->13019|17509->13030|17839->13329|17870->13332|17936->13370|17952->13376|18027->13429|18160->13533|18190->13534|18223->13539|18325->13613|18354->13614|18419->13650|18449->13651|18482->13656|18677->13823|18706->13824|18738->13828|18782->13843|18812->13844|18845->13849|18954->13930|18983->13931|19013->13933|19110->14001|19140->14002|19172->14006|19266->14072|19295->14073|19324->14074|19398->14119|19428->14120|19461->14125|19593->14229|19622->14230|19652->14232|19698->14249|19728->14250|19760->14254|19955->14421|19984->14422|20014->14424|20071->14452|20101->14453|20135->14459|20318->14613|20348->14614|20387->14624|20475->14684|20504->14685|20573->14726|20600->14743|20647->14768|20679->14772|20708->14773
                  LINES: 28->1|34->3|37->6|37->6|37->6|39->8|39->8|40->9|41->10|41->10|43->12|47->16|47->16|47->16|48->17|48->17|49->18|49->18|57->26|57->26|57->26|65->34|65->34|65->34|69->38|69->38|70->39|70->39|70->39|70->39|73->42|74->43|80->49|80->49|80->49|88->57|88->57|88->57|100->69|100->69|100->69|108->77|108->77|108->77|116->85|116->85|116->85|124->93|124->93|124->93|124->93|124->93|124->93|124->93|132->101|132->101|132->101|137->106|137->106|137->106|140->109|140->109|140->109|145->114|145->114|145->114|148->117|148->117|148->117|157->126|157->126|157->126|165->134|165->134|165->134|173->142|173->142|173->142|181->150|181->150|181->150|189->158|189->158|189->158|205->174|205->174|205->174|206->175|209->178|209->178|209->178|214->183|214->183|214->183|218->187|220->189|228->197|228->197|228->197|233->202|233->202|233->202|239->208|239->208|240->209|241->210|241->210|241->210|243->212|243->212|244->213|246->215|247->216|250->219|250->219|251->220|252->221|252->221|252->221|254->223|254->223|255->224|257->226|258->227|265->234|265->234|265->234|271->240|271->240|271->240|280->249|280->249|280->249|288->257|288->257|288->257|295->264|295->264|295->264|302->271|302->271|302->271|310->279|310->279|310->279|329->298|329->298|329->298|329->298|344->313|347->316|349->318|349->318|349->318|351->320|351->320|352->321|354->323|354->323|358->327|358->327|359->328|366->335|366->335|368->337|368->337|368->337|369->338|374->343|374->343|375->344|380->349|380->349|381->350|382->351|382->351|382->351|383->352|383->352|384->353|386->355|386->355|387->356|387->356|387->356|388->357|392->361|392->361|393->362|393->362|393->362|394->363|397->366|397->366|398->367|399->368|399->368|400->369|400->369|400->369|401->370|401->370
                  -- GENERATED --
              */
          