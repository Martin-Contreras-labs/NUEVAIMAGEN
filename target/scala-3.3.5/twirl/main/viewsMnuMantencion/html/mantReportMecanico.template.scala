
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

object mantReportMecanico extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,tables.MantTransacReport,List[tables.MantTransacComponentes],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
mantTransacReport: tables.MantTransacReport, mantTransacComponentes: List[tables.MantTransacComponentes]):play.twirl.api.HtmlFormat.Appendable = {
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
								<td style="text-align:left;"><strong>MECANICO:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*64.19*/mantTransacReport/*64.36*/.getFullNameMecanico()),format.raw/*64.58*/(""""
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
								<td style="text-align:left;"><strong>TIPO DE MANTENCION (*):</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*80.19*/mantTransacReport/*80.36*/.getNameTipoMantencion().toUpperCase()),format.raw/*80.74*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>EQUIPO:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*88.19*/mantTransacReport/*88.36*/.getCodigoEquipo()),format.raw/*88.54*/(""" """),format.raw/*88.55*/("""- """),_display_(/*88.58*/mantTransacReport/*88.75*/.getNameEquipo()),format.raw/*88.91*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>TIPO DE PLAN:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*96.19*/mantTransacReport/*96.36*/.getNamePlanMantencion()),format.raw/*96.60*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>"""),_display_(/*101.47*/mapDiccionario/*101.61*/.get("BODEGA")),format.raw/*101.75*/("""/PROYECTO:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*104.19*/mantTransacReport/*104.36*/.getNameBodega()),format.raw/*104.52*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>TIPO """),_display_(/*109.52*/mapDiccionario/*109.66*/.get("BODEGA")),format.raw/*109.80*/("""/PROYECTO:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control"
										value=""""),_display_(/*112.19*/mantTransacReport/*112.36*/.getNameTipoBodega()),format.raw/*112.56*/(""""
										readonly>
						        </td>
							</tr>
							
							<tr>
								<td style="text-align:left;"><strong>ESTADO OPERACIONAL (*):</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
						        	<input type="text" class="form-control"
										value=""""),_display_(/*121.19*/mantTransacReport/*121.36*/.getNameEstadoOperacional()),format.raw/*121.63*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>ESTADO EN TALLER (*):</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
						        	<input type="text" class="form-control"
										value=""""),_display_(/*129.19*/mantTransacReport/*129.36*/.getNameEstadoEnTaller()),format.raw/*129.60*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>ACTIVIDAD (*):</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
						        	<input type="text" class="form-control"
										value=""""),_display_(/*137.19*/mantTransacReport/*137.36*/.getNameActividad()),format.raw/*137.55*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>TIPO DE ACTIVIDAD (*):</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
						        	<input type="text" class="form-control"
										value=""""),_display_(/*145.19*/mantTransacReport/*145.36*/.getNameTipoActividad()),format.raw/*145.59*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>ITEM INTERVENIDO (*):</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
						        	<input type="text" class="form-control"
										value=""""),_display_(/*153.19*/mantTransacReport/*153.36*/.getNameItemIntervenido()),format.raw/*153.61*/(""""
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
											"""),_display_(/*169.13*/for(lista <- mantTransacComponentes) yield /*169.49*/{_display_(Seq[Any](format.raw/*169.50*/("""
												"""),format.raw/*170.13*/("""<tr>
													<td style="text-align:left;vertical-align:middle">
														<input type="text" class="form-control"
															value=""""),_display_(/*173.24*/lista/*173.29*/.getNameComponente()),format.raw/*173.49*/(""""
															readonly>
									    			</td>
													<td>
														<input type="text" class="form-control form-control-sm left" style="max-width:200px"
															value=""""),_display_(/*178.24*/utilities/*178.33*/.DecimalFormato.formato(lista.getCantidad(),2)),format.raw/*178.79*/(""""
															readonly>
													</td>
												</tr>
											""")))}),format.raw/*182.13*/("""
											
										"""),format.raw/*184.11*/("""</tbody>
						        	</table>
								</td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>Fecha_INI:</strong></td>
						        <td style="text-align:left">
									<input type="date" class="form-control form-control-sm left" style="max-width:150px"
										value=""""),_display_(/*192.19*/mantTransacReport/*192.36*/.getFechaIni),format.raw/*192.48*/("""">
						        </td>
								<td style="text-align:left;"><strong>Fecha_TER:</strong></td>
						        <td style="text-align:left" colspan="2">
									<input type="date" class="form-control form-control-sm left"  style="max-width:150px"
										value=""""),_display_(/*197.19*/mantTransacReport/*197.36*/.getFechaFin),format.raw/*197.48*/("""">
						        </td>
							</tr>	
							<tr>
								<td style="text-align:left;"><strong>Hr_INI:</strong></td>
						        <td style="text-align:left">
									"""),_display_(if( ! mantTransacReport.getHoraIni().equals("00:00:00") && mantTransacReport.getHoraFin().equals("00:00:00"))/*203.120*/{_display_(Seq[Any](format.raw/*203.121*/("""
										"""),format.raw/*204.11*/("""<input type="time" class="form-control form-control-sm left" style="max-width:100px"
											value=""""),_display_(/*205.20*/mantTransacReport/*205.37*/.getHoraIni()),format.raw/*205.50*/(""""
											readonly>
									""")))}else/*207.15*/{_display_(Seq[Any](format.raw/*207.16*/("""
										"""),format.raw/*208.11*/("""<input type="time" class="form-control form-control-sm left" style="max-width:100px"
											readonly>
									""")))}),format.raw/*210.11*/("""
						        """),format.raw/*211.15*/("""</td>
								<td style="text-align:left;"><strong>Hr_TER:</strong></td>
						        <td style="text-align:left" colspan="2">
									"""),_display_(if( ! mantTransacReport.getHoraIni().equals("00:00:00") && mantTransacReport.getHoraFin().equals("00:00:00"))/*214.120*/{_display_(Seq[Any](format.raw/*214.121*/("""
										"""),format.raw/*215.11*/("""<input type="time" class="form-control form-control-sm left" style="max-width:100px"
											value=""""),_display_(/*216.20*/mantTransacReport/*216.37*/.getHoraFin()),format.raw/*216.50*/(""""
											readonly>
									""")))}else/*218.15*/{_display_(Seq[Any](format.raw/*218.16*/("""
										"""),format.raw/*219.11*/("""<input type="time" class="form-control form-control-sm left" style="max-width:100px"
											readonly>
									""")))}),format.raw/*221.11*/("""
						        """),format.raw/*222.15*/("""</td>
							</tr>
							
							<tr>
								<td style="text-align:left;"><strong><div id="lectIni_oper">Lect_INI:</div></strong></td>
						        <td style="text-align:left">
									<input type="text" class="form-control form-control-sm left" style="max-width:200px"
										value=""""),_display_(/*229.19*/utilities/*229.28*/.DecimalFormato.formato(mantTransacReport.getLectAnterior(),2)),format.raw/*229.90*/(""""
										readonly>
						        </td>
								<td style="text-align:left;"><strong><div id="lectTer_oper">Lect_TER:</div></strong></td>
						        <td style="text-align:left" colspan="2">
									<input type="text" class="form-control form-control-sm left" style="max-width:200px"
										value=""""),_display_(/*235.19*/utilities/*235.28*/.DecimalFormato.formato(mantTransacReport.getLectActual(),2)),format.raw/*235.88*/(""""
										readonly>
						        </td>
							</tr>
							
							<tr>
								<td style="text-align:left;"><strong><div id="cant_oper">CANT:</div></strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control form-control-sm left" style="max-width:200px"
										value=""""),_display_(/*244.19*/mantTransacReport/*244.36*/.getLectDif()),format.raw/*244.49*/(""""
										readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>DESCRIPCION TRABAJO:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<textarea class="form-control form-control-sm" rows="1" 
										readonly>"""),_display_(/*252.21*/mantTransacReport/*252.38*/.getDescTrabajo()),format.raw/*252.55*/("""</textarea>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>ESTADO FINAL:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<textarea class="form-control form-control-sm" rows="1" 
										readonly>"""),_display_(/*259.21*/mantTransacReport/*259.38*/.getEstadoFinal()),format.raw/*259.55*/("""</textarea>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>OBSERVACIONES:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<textarea class="form-control form-control-sm" rows="2" 
										readonly>"""),_display_(/*266.21*/mantTransacReport/*266.38*/.getObservaciones()),format.raw/*266.57*/("""</textarea>
						        </td>
							</tr>
							
							<tr>
								<td style="text-align:left;"  colspan="5">
									FIRMA MECANICO:<br>
									<div class="wrapper" align="center">
										<img src="data:image/jpeg;base64, """),_display_(/*274.46*/mantTransacReport/*274.63*/.getFirmaPDFoperador()),format.raw/*274.85*/(""""  height="60px" />
									</div>
								</td>
							</tr>
							<tr>
								<td style="text-align:left;"   colspan="5">
									FIRMA AUTORIZADA:<br>
									<div class="wrapper" align="center">
										<img src="data:image/jpeg;base64, """),_display_(/*282.46*/mantTransacReport/*282.63*/.getFirmaPDFautorizador()),format.raw/*282.88*/("""" height="60px" />
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
""")))}),format.raw/*303.2*/("""

	
"""),format.raw/*306.1*/("""<script>
	$(document).ready(function() """),format.raw/*307.31*/("""{"""),format.raw/*307.32*/("""
 		"""),format.raw/*308.4*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*309.2*/("""}"""),format.raw/*309.3*/(""" """),format.raw/*309.4*/(""");
	const descargaDocumento = (nombreDOC) => """),format.raw/*310.43*/("""{"""),format.raw/*310.44*/("""
		  """),format.raw/*311.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*313.2*/("""}"""),format.raw/*313.3*/("""
"""),format.raw/*314.1*/("""</script>
	

"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,mantTransacReport:tables.MantTransacReport,mantTransacComponentes:List[tables.MantTransacComponentes]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,mantTransacReport,mantTransacComponentes)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.MantTransacReport,List[tables.MantTransacComponentes]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,mantTransacReport,mantTransacComponentes) => apply(mapDiccionario,mapPermiso,userMnu,mantTransacReport,mantTransacComponentes)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuMantencion/mantReportMecanico.scala.html
                  HASH: 985a6d8f7dc5b2c9bad9491ac909df5b69592ce4
                  MATRIX: 1081->1|1377->204|1407->209|1423->217|1462->219|1490->222|1558->270|1586->272|1663->323|1754->393|1787->399|2256->841|2282->858|2311->866|2653->1181|2679->1198|2711->1209|2900->1371|2939->1372|2978->1383|3046->1424|3072->1441|3107->1455|3243->1547|3279->1556|3547->1797|3573->1814|3612->1832|3907->2100|3933->2117|3977->2140|4396->2532|4422->2549|4465->2571|4765->2844|4791->2861|4834->2883|5145->3167|5171->3184|5230->3222|5525->3490|5551->3507|5590->3525|5619->3526|5649->3529|5675->3546|5712->3562|6013->3836|6039->3853|6084->3877|6225->3990|6249->4004|6285->4018|6472->4177|6499->4194|6537->4210|6683->4328|6707->4342|6743->4356|6930->4515|6957->4532|6999->4552|7325->4850|7352->4867|7401->4894|7717->5182|7744->5199|7790->5223|8099->5504|8126->5521|8167->5540|8484->5829|8511->5846|8556->5869|8872->6157|8899->6174|8946->6199|9587->6812|9640->6848|9680->6849|9722->6862|9896->7008|9911->7013|9953->7033|10170->7222|10189->7231|10257->7277|10365->7353|10417->7376|10742->7673|10769->7690|10803->7702|11092->7963|11119->7980|11153->7992|11460->8270|11501->8271|11541->8282|11673->8386|11700->8403|11735->8416|11792->8453|11832->8454|11872->8465|12020->8581|12064->8596|12340->8843|12381->8844|12421->8855|12553->8959|12580->8976|12615->8989|12672->9026|12712->9027|12752->9038|12900->9154|12944->9169|13264->9461|13283->9470|13367->9532|13702->9839|13721->9848|13803->9908|14176->10253|14203->10270|14238->10283|14566->10583|14593->10600|14632->10617|14943->10900|14970->10917|15009->10934|15321->11218|15348->11235|15389->11254|15653->11490|15680->11507|15724->11529|16001->11778|16028->11795|16075->11820|16756->12470|16788->12474|16856->12513|16886->12514|16918->12518|17012->12584|17041->12585|17070->12586|17144->12631|17174->12632|17207->12637|17339->12741|17368->12742|17397->12743
                  LINES: 28->1|34->3|37->6|37->6|37->6|39->8|39->8|40->9|41->10|41->10|43->12|52->21|52->21|52->21|60->29|60->29|60->29|64->33|64->33|65->34|65->34|65->34|65->34|68->37|69->38|75->44|75->44|75->44|83->52|83->52|83->52|95->64|95->64|95->64|103->72|103->72|103->72|111->80|111->80|111->80|119->88|119->88|119->88|119->88|119->88|119->88|119->88|127->96|127->96|127->96|132->101|132->101|132->101|135->104|135->104|135->104|140->109|140->109|140->109|143->112|143->112|143->112|152->121|152->121|152->121|160->129|160->129|160->129|168->137|168->137|168->137|176->145|176->145|176->145|184->153|184->153|184->153|200->169|200->169|200->169|201->170|204->173|204->173|204->173|209->178|209->178|209->178|213->182|215->184|223->192|223->192|223->192|228->197|228->197|228->197|234->203|234->203|235->204|236->205|236->205|236->205|238->207|238->207|239->208|241->210|242->211|245->214|245->214|246->215|247->216|247->216|247->216|249->218|249->218|250->219|252->221|253->222|260->229|260->229|260->229|266->235|266->235|266->235|275->244|275->244|275->244|283->252|283->252|283->252|290->259|290->259|290->259|297->266|297->266|297->266|305->274|305->274|305->274|313->282|313->282|313->282|334->303|337->306|338->307|338->307|339->308|340->309|340->309|340->309|341->310|341->310|342->311|344->313|344->313|345->314
                  -- GENERATED --
              */
          