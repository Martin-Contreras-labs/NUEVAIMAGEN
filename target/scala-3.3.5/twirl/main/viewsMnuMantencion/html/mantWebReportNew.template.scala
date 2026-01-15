
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

object mantWebReportNew extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template21[Map[String,String],String,tables.MantActorPersonal,tables.MantOperadorMecanico,List[tables.PlanMantencion],String,List[tables.BodegaEmpresa],List[tables.MantEstadoEnObra],List[List[String]],List[tables.TipoMantencion],List[tables.MantEstadoEnTaller],List[tables.MantActividad],List[tables.MantTipoActividad],List[tables.MantComponente],List[tables.MantItemIntervenido],String,String,String,String,String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], fecha: String, actor: tables.MantActorPersonal, operMec: tables.MantOperadorMecanico, listPlanMant: List[tables.PlanMantencion], mapIdEquipVsBod: String, listBod: List[tables.BodegaEmpresa], listEstaObra: List[tables.MantEstadoEnObra], listEquipos: List[List[String]], listTipoMantencion: List[tables.TipoMantencion], listEstadoEnTaller: List[tables.MantEstadoEnTaller], listActividad: List[tables.MantActividad], listTipoActividad: List[tables.MantTipoActividad], listComponentes: List[tables.MantComponente], listItemIntervenido: List[tables.MantItemIntervenido], idEquipo: String, cod_nameEquipo: String, id_bodega: String, suc_nameBodega: String, nameTipoBodega: String, estadoActual: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""


"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""


	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">

		<form id="mantWebListarReports1" action="/routes3/mantWebListarReports0/" method="POST">
			<input type="hidden" name="id_userOperMec" value=""""),_display_(/*11.55*/operMec/*11.62*/.getId()),format.raw/*11.70*/("""">
			<input type="hidden" name="id_equipo" value=""""),_display_(/*12.50*/idEquipo),format.raw/*12.58*/("""">
		</form>
		
		<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
				<br>
				<form id="grabarRptOperadorMecanico" action="/routes3/mantWebReportNewSave/" method="POST" enctype="multipart/form-data">

					<table class="table table-sm table-bordered table-condensed table-fluid">
						<thead>
							<tr>
								<td colspan="5" style="text-align:center;">
									<button type="button" class="btn btn-sm btn-info" style="font-size: 10px;"onclick="$('#mantWebListarReports1').submit();" >MIS REPORT
									</button>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<button type="button" class="btn btn-sm btn-warning" style="font-size: 10px;" onclick="location.href = '/report'">SALIR</button>
								</td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>FECHA (*):</strong></td>
								<td style="text-align:left; width: 70%" colspan="3">
									<input style="max-width:150px" type="date" class="form-control form-control-sm"
										id="fecha"
										name="fecha"
										value=""""),_display_(/*36.19*/fecha),format.raw/*36.24*/(""""
										onblur="actualizaFechaReal()"
										required>
								</td>
								<td style="text-align:center; width: 70%" >
									<span class="btn btn-info btn-sm btn-file" style="font-size: 8px;">
											<input type="hidden" name="docAnexo" value="0">
											<div id="txtBtn">Adjuntar</div>
											<input type="file" multiple id="docAdjunto" name="docAdjunto[]" onchange="LimitAttach(this.form, this.form.docAdjunto.value); $('#seModifico').val(1);">
									</span>
								</td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>PERFIL (*):</strong></td>
								<td style="text-align:left; width: 70%" colspan="4">
									<input type="hidden" name="id_userMada" value="0">
									<input type="hidden" name="id_mantActorPersonal" value=""""),_display_(/*52.67*/actor/*52.72*/.getId()),format.raw/*52.80*/("""">
									<input type="text" class="form-control" name="id_mantActorPersonal" value=""""),_display_(/*53.86*/actor/*53.91*/.getNombre()),format.raw/*53.103*/("""" readonly>
								</td>
							</tr>
							</thead>
					</table>

					<input type="hidden" name="firmaEjecutor" id="firmaEjecutor" value="0">
					<input type="hidden" name="firmaAprobador" id="firmaAprobador" value="0">

					"""),_display_(if(actor.getId() == 1L)/*62.30*/{_display_(Seq[Any](format.raw/*62.31*/("""
						"""),_display_(/*63.8*/mantWebReportNewOperador(mapDiccionario, fecha, listEstaObra, operMec,
							idEquipo, cod_nameEquipo, id_bodega, suc_nameBodega, nameTipoBodega)),format.raw/*64.76*/(""")
					""")))} else {null} ),format.raw/*65.7*/("""

					"""),_display_(if(actor.getId() == 2L)/*67.30*/{_display_(Seq[Any](format.raw/*67.31*/("""
						"""),_display_(/*68.8*/mantWebReportNewMecanico(mapDiccionario, fecha, listEstaObra, operMec, listTipoMantencion,
							listEstadoEnTaller, listActividad, listTipoActividad, listComponentes, listItemIntervenido,
							idEquipo, cod_nameEquipo, id_bodega, suc_nameBodega, nameTipoBodega)),format.raw/*70.76*/("""
					""")))} else {null} ),format.raw/*71.7*/("""
					"""),format.raw/*72.6*/("""<span style="font-size: 12px;">(*) Campos Obligatorios</span>
				</form>
			</div>
		</div>
		
		<div id="modalListaEquipos_mec" class="modal" role="dialog" data-backdrop="static" data-keyboard="false">
			<div class="modal-dialog modal-lg modal-dialog-scrollable" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title">Seleccionar </h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body">
					<table id="tablaEquipos_mec" class="table table-sm table-bordered table-condensed table-hover table-fluid">
				        <thead style="background-color: #eeeeee">
							<TR> 
								<th>GRUPO</th>
								<th>CODIGO</th>
								<th>EQUIPO</th>
								<th>TIPO PLAN</th>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*97.9*/for(lista <- listPlanMant) yield /*97.35*/{_display_(Seq[Any](format.raw/*97.36*/("""
								"""),format.raw/*98.9*/("""<TR onclick="selectEquipo(
										'"""),_display_(/*99.13*/lista/*99.18*/.getId_equipo()),format.raw/*99.33*/("""', 
										btoa('"""),_display_(/*100.18*/lista/*100.23*/.getEquipoCodigo()),format.raw/*100.41*/("""'), 
										btoa('"""),_display_(/*101.18*/lista/*101.23*/.getEquipoNombre()),format.raw/*101.41*/("""'),
										btoa('"""),_display_(/*102.18*/lista/*102.23*/.getTipoPlanNombre()),format.raw/*102.43*/("""'),
										'"""),_display_(/*103.13*/lista/*103.18*/.getId_tipoPlan()),format.raw/*103.35*/("""',
										'"""),_display_(/*104.13*/lista/*104.18*/.getUnidadMantencion()),format.raw/*104.40*/("""',
										'"""),_display_(/*105.13*/lista/*105.18*/.getEstadoActual()),format.raw/*105.36*/("""',
										'"""),_display_(/*106.13*/lista/*106.18*/.getId_unidadMantencion()),format.raw/*106.43*/("""')">
									<td style="text-align:left"><a href="#">"""),_display_(/*107.51*/lista/*107.56*/.getEquipoGrupo()),format.raw/*107.73*/("""</a></td>	
									<td style="text-align:left"><a href="#">"""),_display_(/*108.51*/lista/*108.56*/.getEquipoCodigo()),format.raw/*108.74*/("""</a></td>
									<td style="text-align:left"><a href="#">"""),_display_(/*109.51*/lista/*109.56*/.getEquipoNombre()),format.raw/*109.74*/("""</a></td>
									<td style="text-align:left"><a href="#">"""),_display_(/*110.51*/lista/*110.56*/.getTipoPlanNombre()),format.raw/*110.76*/("""</a></td>
								</TR>
							""")))}),format.raw/*112.9*/("""
						"""),format.raw/*113.7*/("""</tbody>
					</table>
					<div style="text-align: center">
			           	<button type="button" class="btn btn-sm btn-success" data-dismiss="modal" style="font-size: 10px;">CERRAR</button>
				    </div>
			      </div>
			    </div>
			 </div>
		</div>
		
		<div id="modalListaEquipos_oper" class="modal" role="dialog" data-backdrop="static" data-keyboard="false">
			<div class="modal-dialog modal-lg modal-dialog-scrollable" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title">Seleccionar </h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body">
					<table id="tablaEquipos_oper" class="table table-sm table-bordered table-condensed table-hover table-fluid">
				        <thead style="background-color: #eeeeee">
							<TR> 
								<th>GRUPO</th>
								<th>CODIGO</th>
								<th>EQUIPO</th>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*142.9*/for(lista <- listEquipos) yield /*142.34*/{_display_(Seq[Any](format.raw/*142.35*/("""
								"""),format.raw/*143.9*/("""<TR onclick="selectEquipo(
										'"""),_display_(/*144.13*/lista/*144.18*/.get(0)),format.raw/*144.25*/("""',
										btoa('"""),_display_(/*145.18*/lista/*145.23*/.get(2)),format.raw/*145.30*/("""'),
										btoa('"""),_display_(/*146.18*/lista/*146.23*/.get(3)),format.raw/*146.30*/("""'),
										btoa('no Aplica'),
										'0',
										'"""),_display_(/*149.13*/lista/*149.18*/.get(4)),format.raw/*149.25*/("""',
										'"""),_display_(/*150.13*/lista/*150.18*/.get(5)),format.raw/*150.25*/("""',
										'"""),_display_(/*151.13*/lista/*151.18*/.get(6)),format.raw/*151.25*/("""')">
									<td style="text-align:left"><a href="#">"""),_display_(/*152.51*/lista/*152.56*/.get(1)),format.raw/*152.63*/("""</a></td>	
									<td style="text-align:left"><a href="#">"""),_display_(/*153.51*/lista/*153.56*/.get(2)),format.raw/*153.63*/("""</a></td>
									<td style="text-align:left"><a href="#">"""),_display_(/*154.51*/lista/*154.56*/.get(3)),format.raw/*154.63*/("""</a></td>
								</TR>
							""")))}),format.raw/*156.9*/("""
						"""),format.raw/*157.7*/("""</tbody>
					</table>
					<div align="center">
			           	<button type="button" class="btn btn-sm btn-success" data-dismiss="modal" style="font-size: 10px;">CERRAR</button>
				    </div>
			      </div>
			    </div>
			 </div>
		</div>
		
		<div id="modalListaBodegas" class="modal" role="dialog" data-backdrop="static" data-keyboard="false">
			<div class="modal-dialog modal-lg modal-dialog-scrollable" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title">Seleccionar """),_display_(/*171.49*/mapDiccionario("Bodega")),format.raw/*171.73*/("""/Proyecto</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body">
					<table id="tablaBodegas" class="table table-sm table-bordered table-condensed table-hover table-fluid">
				        <thead style="background-color: #eeeeee">
							<TR> 
								<th>Sucursal</th>
								<th>Tipo</th>
								<th>"""),_display_(/*182.14*/mapDiccionario("Bodega")),format.raw/*182.38*/("""/Proyecto</th>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*186.9*/for(lista <- listBod) yield /*186.30*/{_display_(Seq[Any](format.raw/*186.31*/("""
								"""),format.raw/*187.9*/("""<TR onclick="selectBodega('"""),_display_(/*187.37*/lista/*187.42*/.getId()),format.raw/*187.50*/("""', btoa('"""),_display_(/*187.60*/lista/*187.65*/.getNameSucursal()),format.raw/*187.83*/("""'), btoa('"""),_display_(/*187.94*/lista/*187.99*/.getNombreTipoBodega()),format.raw/*187.121*/("""') , btoa('"""),_display_(/*187.133*/lista/*187.138*/.getNombre()),format.raw/*187.150*/("""') )">
									<td style="text-align:left"><a href="#">"""),_display_(/*188.51*/lista/*188.56*/.getNameSucursal()),format.raw/*188.74*/("""</a></td>	
									<td style="text-align:left"><a href="#">"""),_display_(/*189.51*/lista/*189.56*/.getNombreTipoBodega()),format.raw/*189.78*/("""</a></td>
									<td style="text-align:left"><a href="#">"""),_display_(/*190.51*/lista/*190.56*/.getNombre()),format.raw/*190.68*/("""</a></td>													
								</TR>
							""")))}),format.raw/*192.9*/("""
						"""),format.raw/*193.7*/("""</tbody>
					</table>
			        <br>
					<div align="center">
			           	<button type="button" class="btn btn-sm btn-success" data-dismiss="modal" style="font-size: 10px;">CERRAR</button>
				    </div>
			      </div>
			    </div>
			 </div>
		</div>
		
		<div id="modalComponentesC" class="modal" role="dialog" data-backdrop="static" data-keyboard="false">
			<div class="modal-dialog modal-sm modal-dialog-scrollable" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title">Seleccionar componentes</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body">
						<table id="tablaComponentesC" class="table table-sm table-bordered table-condensed table-hover table-fluid" style="width:100%">
		     				<thead style='background-color: #eeeeee'>
								<TR>
									<th style='display:none'>id_componenteC</th>
									<th>Nombre</th>
								</TR>
							</thead>
							<tbody>
								"""),_display_(/*222.10*/for(lista <- listComponentes) yield /*222.39*/{_display_(Seq[Any](format.raw/*222.40*/("""
									"""),format.raw/*223.10*/("""<tr onclick="selectComponenteC('"""),_display_(/*223.43*/lista/*223.48*/.getId()),format.raw/*223.56*/("""',btoa('"""),_display_(/*223.65*/lista/*223.70*/.getNombre()),format.raw/*223.82*/("""'))">
										<td style='display:none'>"""),_display_(/*224.37*/lista/*224.42*/.getId()),format.raw/*224.50*/("""</td>
										<td><a href="#">"""),_display_(/*225.28*/lista/*225.33*/.getNombre()),format.raw/*225.45*/("""</a></td>
									</tr>
								""")))}),format.raw/*227.10*/("""
							"""),format.raw/*228.8*/("""</tbody>
			      		</table>
			        <br>
					<div align="center">
			           	<button type="button" class="btn btn-sm btn-success" data-dismiss="modal" style="font-size: 10px;">CERRAR</button>
				    </div>
			      </div>
			    </div>
			 </div>
		</div>	
		
		<div id="modalComponentesP" class="modal" role="dialog" data-backdrop="static" data-keyboard="false">
			<div class="modal-dialog modal-sm modal-dialog-scrollable" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title">Seleccionar componentes</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body">
						<table id="tablaComponentesP" class="table table-sm table-bordered table-condensed table-hover table-fluid" style="width:100%">
		     				<thead style='background-color: #eeeeee'>
								<TR>
									<th style='display:none'>id_componenteP</th>
									<th>Nombre</th>
								</TR>
							</thead>
							<tbody>
								"""),_display_(/*257.10*/for(lista <- listComponentes) yield /*257.39*/{_display_(Seq[Any](format.raw/*257.40*/("""
									"""),format.raw/*258.10*/("""<tr onclick="selectComponenteP('"""),_display_(/*258.43*/lista/*258.48*/.getId()),format.raw/*258.56*/("""',btoa('"""),_display_(/*258.65*/lista/*258.70*/.getNombre()),format.raw/*258.82*/("""'))">
										<td style='display:none'>"""),_display_(/*259.37*/lista/*259.42*/.getId()),format.raw/*259.50*/("""</td>
										<td><a href="#">"""),_display_(/*260.28*/lista/*260.33*/.getNombre()),format.raw/*260.45*/("""</a></td>
									</tr>
								""")))}),format.raw/*262.10*/("""
							"""),format.raw/*263.8*/("""</tbody>
			      		</table>
			        <br>
					<div align="center">
			           	<button type="button" class="btn btn-sm btn-success" data-dismiss="modal" style="font-size: 10px;">CERRAR</button>
				    </div>
			      </div>
			    </div>
			 </div>
		</div>	
	
		
	</div>
""")))}),format.raw/*276.2*/("""


"""),format.raw/*279.1*/("""<!-- CONFIGURA PAD -->

<script src=""""),_display_(/*281.15*/routes/*281.21*/.Assets.versioned("javascripts/signature_pad.min.js")),format.raw/*281.74*/(""""></script>
	<script>
		let signaturePad = new SignaturePad(document.getElementById('signature-pad'), """),format.raw/*283.81*/("""{"""),format.raw/*283.82*/("""
		  """),format.raw/*284.5*/("""backgroundColor: 'rgba(255, 255, 255, 0)',
		  penColor: 'rgb(0, 0, 0)'
		"""),format.raw/*286.3*/("""}"""),format.raw/*286.4*/(""");
		
		let signaturePad2 = new SignaturePad(document.getElementById('signature-pad2'), """),format.raw/*288.83*/("""{"""),format.raw/*288.84*/("""
		  """),format.raw/*289.5*/("""backgroundColor: 'rgba(255, 255, 255, 0)',
		  penColor: 'rgb(0, 0, 0)'
		"""),format.raw/*291.3*/("""}"""),format.raw/*291.4*/(""");
		let signaturePad3 = new SignaturePad(document.getElementById('signature-pad3'), """),format.raw/*292.83*/("""{"""),format.raw/*292.84*/("""
		  """),format.raw/*293.5*/("""backgroundColor: 'rgba(255, 255, 255, 0)',
		  penColor: 'rgb(0, 0, 0)'
		"""),format.raw/*295.3*/("""}"""),format.raw/*295.4*/(""");
		
		let signaturePad4 = new SignaturePad(document.getElementById('signature-pad4'), """),format.raw/*297.83*/("""{"""),format.raw/*297.84*/("""
		  """),format.raw/*298.5*/("""backgroundColor: 'rgba(255, 255, 255, 0)',
		  penColor: 'rgb(0, 0, 0)'
		"""),format.raw/*300.3*/("""}"""),format.raw/*300.4*/(""");
		
		let signaturePad5 = new SignaturePad(document.getElementById('signature-pad5'), """),format.raw/*302.83*/("""{"""),format.raw/*302.84*/("""
		  """),format.raw/*303.5*/("""backgroundColor: 'rgba(255, 255, 255, 0)',
		  penColor: 'rgb(0, 0, 0)'
		"""),format.raw/*305.3*/("""}"""),format.raw/*305.4*/(""");
		
		let signaturePad6 = new SignaturePad(document.getElementById('signature-pad6'), """),format.raw/*307.83*/("""{"""),format.raw/*307.84*/("""
		  """),format.raw/*308.5*/("""backgroundColor: 'rgba(255, 255, 255, 0)',
		  penColor: 'rgb(0, 0, 0)'
		"""),format.raw/*310.3*/("""}"""),format.raw/*310.4*/(""");
	</script>
	
	<style>
		.wrapper """),format.raw/*314.12*/("""{"""),format.raw/*314.13*/("""
		  """),format.raw/*315.5*/("""position: relative;
		  width: 350px;
		  height: 90px;
		  -moz-user-select: none;
		  -webkit-user-select: none;
		  -ms-user-select: none;
		  user-select: none;
		"""),format.raw/*322.3*/("""}"""),format.raw/*322.4*/("""
		
		"""),format.raw/*324.3*/(""".signature-pad """),format.raw/*324.18*/("""{"""),format.raw/*324.19*/("""
		  """),format.raw/*325.5*/("""position: absolute;
		  left: 0;
		  top: 0;
		  width:350px;
		  height:90px;
		"""),format.raw/*330.3*/("""}"""),format.raw/*330.4*/("""
	"""),format.raw/*331.2*/("""</style>
<!-- FIN PAD -->
	
<script>
	let lectAnterior = 0;
	$(document).ready(function() """),format.raw/*336.31*/("""{"""),format.raw/*336.32*/("""
		"""),format.raw/*337.3*/("""$('#tablaOperadores').DataTable("""),format.raw/*337.35*/("""{"""),format.raw/*337.36*/("""
	    	"""),format.raw/*338.7*/(""""fixedHeader": true,
	    	"lengthMenu": false,
	    	"paging":   false,
	        "ordering": [['1','asc']],
	        "info":     false,
			"searching": true,
	    	"language": """),format.raw/*344.19*/("""{"""),format.raw/*344.20*/("""
	        	"""),format.raw/*345.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*346.10*/("""}"""),format.raw/*346.11*/("""
	    """),format.raw/*347.6*/("""}"""),format.raw/*347.7*/(""" """),format.raw/*347.8*/(""");

		$('#tablaEquipos_oper').DataTable("""),format.raw/*349.37*/("""{"""),format.raw/*349.38*/("""
	    	"""),format.raw/*350.7*/(""""fixedHeader": true,
	    	"lengthMenu": false,
	    	"paging":   false,
	        "ordering": [['1','asc']],
	        "info":     false,
			"searching": true,
	    	"language": """),format.raw/*356.19*/("""{"""),format.raw/*356.20*/("""
	        	"""),format.raw/*357.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*358.10*/("""}"""),format.raw/*358.11*/("""
	    """),format.raw/*359.6*/("""}"""),format.raw/*359.7*/(""" """),format.raw/*359.8*/(""");
		$('#tablaEquipos_mec').DataTable("""),format.raw/*360.36*/("""{"""),format.raw/*360.37*/("""
	    	"""),format.raw/*361.7*/(""""fixedHeader": true,
	    	"lengthMenu": false,
	    	"paging":   false,
	        "ordering": [['1','asc']],
	        "info":     false,
			"searching": true,
	    	"language": """),format.raw/*367.19*/("""{"""),format.raw/*367.20*/("""
	        	"""),format.raw/*368.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*369.10*/("""}"""),format.raw/*369.11*/("""
	    """),format.raw/*370.6*/("""}"""),format.raw/*370.7*/(""" """),format.raw/*370.8*/(""");
		$('#tablaBodegas').DataTable("""),format.raw/*371.32*/("""{"""),format.raw/*371.33*/("""
	    	"""),format.raw/*372.7*/(""""fixedHeader": true,
	    	"lengthMenu": false,
	    	"paging":   false,
	        "ordering": [['1','asc']],
	        "info":     false,
			"searching": true,
	    	"language": """),format.raw/*378.19*/("""{"""),format.raw/*378.20*/("""
	        	"""),format.raw/*379.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*380.10*/("""}"""),format.raw/*380.11*/("""
	    """),format.raw/*381.6*/("""}"""),format.raw/*381.7*/(""" """),format.raw/*381.8*/(""");

		$('#tablaComponentesC').DataTable("""),format.raw/*383.37*/("""{"""),format.raw/*383.38*/("""
			"""),format.raw/*384.4*/(""""dom": '<"pull-left"f><"pull-right"l>tip',
	    	"fixedHeader": true,
	    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
	    	"paging":   true,
	        "ordering": true,
	        "info":     true,
	    	"language": """),format.raw/*390.19*/("""{"""),format.raw/*390.20*/("""
	        	"""),format.raw/*391.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*392.10*/("""}"""),format.raw/*392.11*/("""
	    """),format.raw/*393.6*/("""}"""),format.raw/*393.7*/(""" """),format.raw/*393.8*/(""");

		$('#tablaComponentesP').DataTable("""),format.raw/*395.37*/("""{"""),format.raw/*395.38*/("""
			"""),format.raw/*396.4*/(""""dom": '<"pull-left"f><"pull-right"l>tip',
	    	"fixedHeader": true,
	    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
	    	"paging":   true,
	        "ordering": true,
	        "info":     true,
	    	"language": """),format.raw/*402.19*/("""{"""),format.raw/*402.20*/("""
	        	"""),format.raw/*403.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*404.10*/("""}"""),format.raw/*404.11*/("""
	    """),format.raw/*405.6*/("""}"""),format.raw/*405.7*/(""" """),format.raw/*405.8*/(""");

		if(""""),_display_(/*407.8*/idEquipo),format.raw/*407.16*/("""" != "0")"""),format.raw/*407.25*/("""{"""),format.raw/*407.26*/("""
			"""),format.raw/*408.4*/("""$('#lecturaIni_oper').val('"""),_display_(/*408.32*/estadoActual),format.raw/*408.44*/("""');
			$('#lecturaIni_mec').val('"""),_display_(/*409.31*/estadoActual),format.raw/*409.43*/("""');
			$('#lecturaIni_mecP').val('"""),_display_(/*410.32*/estadoActual),format.raw/*410.44*/("""');
			lectAnterior = '"""),_display_(/*411.21*/estadoActual),format.raw/*411.33*/("""';
			liberaDisabled();
		"""),format.raw/*413.3*/("""}"""),format.raw/*413.4*/("""

		"""),format.raw/*415.3*/("""document.getElementById('mostrarmostrar').style.display="block";
	
	
	
	"""),format.raw/*419.2*/("""}"""),format.raw/*419.3*/(""" """),format.raw/*419.4*/(""");
	
	
	const selectPerfil = (id_mantActor) => """),format.raw/*422.41*/("""{"""),format.raw/*422.42*/(""" 
		"""),format.raw/*423.3*/("""if(id_mantActor == 1)"""),format.raw/*423.24*/("""{"""),format.raw/*423.25*/("""
			"""),format.raw/*424.4*/("""$("#id_operador").val(0);
			$("#valNomOperador").val("--- select ---");
			$("#tipoOperador").val("");
			$("#id_equipo_oper").val(0);
			$("#valNomEquip_oper").val("--- select ---");
			$("#id_bodega_oper").val(0);
			$("#valNomBod_oper").val("--- select ---");
			$("#tipoBodega_oper").val("");
			document.querySelector("#id_mantEstadoEnObra").value = "0";
			$("#horaIni_oper").val("");
			$("#horaTer_oper").val("");
			$("#lecturaIni_oper").val("0.00");
			$("#lecturaTer_oper").val("0.00");
			$("#cantidad_oper").val("0.00");
			$("#comentario").val("");
			$("#observaciones_oper").val("");
			signaturePad.clear();
			signaturePad2.clear();
			document.getElementById('viewOperador').style.display="block";
			document.getElementById('viewMecanico').style.display="none";
			document.getElementById('viewCorrectivo').style.display="none";
			document.getElementById('viewPreventivo').style.display="none";
		"""),format.raw/*446.3*/("""}"""),format.raw/*446.4*/("""else if(id_mantActor == 2)"""),format.raw/*446.30*/("""{"""),format.raw/*446.31*/("""
			"""),format.raw/*447.4*/("""$("#id_mecanico").val(0);
			$("#valNomMecanico").val("--- select ---");
			$("#tipoMecanico").val("");
			document.querySelector("#id_tipoMantencion").value = "0";
			limpiaMecanico();
			document.getElementById('viewMecanico').style.display="block";
			document.getElementById('viewOperador').style.display="none";
		"""),format.raw/*454.3*/("""}"""),format.raw/*454.4*/("""
		
		"""),format.raw/*456.3*/("""bloqueaDisabled();
		
	"""),format.raw/*458.2*/("""}"""),format.raw/*458.3*/("""
	
	"""),format.raw/*460.2*/("""const liberaDisabled = () =>"""),format.raw/*460.30*/("""{"""),format.raw/*460.31*/("""
		"""),format.raw/*461.3*/("""$("#id_mantEstadoEnObra").attr("disabled",false);
		$("#lecturaIni_oper").attr("disabled",false);
		$("#lecturaTer_oper").attr("disabled",false);
		$("#cantidad_oper").attr("disabled",false);

		$("#id_mantEstadoOperacional").attr("disabled",false);
		$("#id_mantEstadoEnTaller").attr("disabled",false);
		$("#id_mantActividad").attr("disabled",false);
		$("#id_mantTipoActividad").attr("disabled",false);
		$("#id_mantItemIntervenido").attr("disabled",false);
		$("#lecturaIni_mec").attr("disabled",false);
		$("#lecturaTer_mec").attr("disabled",false);
		$("#cantidad_mec").attr("disabled",false);
		
		$("#id_mantEstadoOperacionalP").attr("disabled",false);
		$("#id_mantEstadoEnTallerP").attr("disabled",false);
		$("#id_mantActividadP").attr("disabled",false);
		$("#id_mantTipoActividadP").attr("disabled",false);
		$("#id_mantItemIntervenidoP").attr("disabled",false);
		$("#lecturaIni_mecP").attr("disabled",false);
		$("#lecturaTer_mecP").attr("disabled",false);
		$("#cantidad_mecP").attr("disabled",false);
	"""),format.raw/*483.2*/("""}"""),format.raw/*483.3*/("""
	
	"""),format.raw/*485.2*/("""const bloqueaDisabled = () =>"""),format.raw/*485.31*/("""{"""),format.raw/*485.32*/("""
		"""),format.raw/*486.3*/("""$("#id_mantEstadoEnObra").attr("disabled",true);
		$("#lecturaIni_oper").attr("disabled",true);
		$("#lecturaTer_oper").attr("disabled",true);
		$("#cantidad_oper").attr("disabled",true);
			
		$("#id_mantEstadoOperacional").attr("disabled",true);
		$("#id_mantEstadoEnTaller").attr("disabled",true);
		$("#id_mantActividad").attr("disabled",true);
		$("#id_mantTipoActividad").attr("disabled",true);
		$("#id_mantItemIntervenido").attr("disabled",true);
		$("#lecturaIni_mec").attr("disabled",true);
		$("#lecturaTer_mec").attr("disabled",true);
		$("#cantidad_mec").attr("disabled",true);
		
		$("#id_mantEstadoOperacionalP").attr("disabled",true);
		$("#id_mantEstadoEnTallerP").attr("disabled",true);
		$("#id_mantActividadP").attr("disabled",true);
		$("#id_mantTipoActividadP").attr("disabled",true);
		$("#id_mantItemIntervenidoP").attr("disabled",true);
		$("#lecturaIni_mecP").attr("disabled",true);
		$("#lecturaTer_mecP").attr("disabled",true);
		$("#cantidad_mecP").attr("disabled",true);
	"""),format.raw/*508.2*/("""}"""),format.raw/*508.3*/("""
	
	"""),format.raw/*510.2*/("""const selectTipoMantencion = (id_tipoMantencion) => """),format.raw/*510.54*/("""{"""),format.raw/*510.55*/(""" 
		"""),format.raw/*511.3*/("""if(id_tipoMantencion == 1)"""),format.raw/*511.29*/("""{"""),format.raw/*511.30*/("""
			"""),format.raw/*512.4*/("""limpiaMecanico();
			document.getElementById('viewPreventivo').style.display="block";
			document.getElementById('viewCorrectivo').style.display="none";
			bloqueaDisabled();
		"""),format.raw/*516.3*/("""}"""),format.raw/*516.4*/("""else if(id_tipoMantencion == 2)"""),format.raw/*516.35*/("""{"""),format.raw/*516.36*/("""
			"""),format.raw/*517.4*/("""limpiaMecanico();
			if(""""),_display_(/*518.9*/idEquipo),format.raw/*518.17*/("""" != "0")"""),format.raw/*518.26*/("""{"""),format.raw/*518.27*/("""
				"""),format.raw/*519.5*/("""$('#lecturaIni_oper').val('"""),_display_(/*519.33*/estadoActual),format.raw/*519.45*/("""');
				$('#lecturaIni_mec').val('"""),_display_(/*520.32*/estadoActual),format.raw/*520.44*/("""');
				$('#lecturaIni_mecP').val('"""),_display_(/*521.33*/estadoActual),format.raw/*521.45*/("""');
				lectAnterior = '"""),_display_(/*522.22*/estadoActual),format.raw/*522.34*/("""';
				liberaDisabled();
			"""),format.raw/*524.4*/("""}"""),format.raw/*524.5*/("""else"""),format.raw/*524.9*/("""{"""),format.raw/*524.10*/("""
				"""),format.raw/*525.5*/("""bloqueaDisabled();
			"""),format.raw/*526.4*/("""}"""),format.raw/*526.5*/("""
			"""),format.raw/*527.4*/("""document.getElementById('viewCorrectivo').style.display="block";
			document.getElementById('viewPreventivo').style.display="none";
		"""),format.raw/*529.3*/("""}"""),format.raw/*529.4*/("""

	"""),format.raw/*531.2*/("""}"""),format.raw/*531.3*/("""
	
	"""),format.raw/*533.2*/("""const limpiaMecanico = () =>"""),format.raw/*533.30*/("""{"""),format.raw/*533.31*/("""
		"""),format.raw/*534.3*/("""$("#id_equipo_mec").val(""""),_display_(/*534.29*/idEquipo),format.raw/*534.37*/("""");
		$("#valNomEquip_mec").val(""""),_display_(/*535.31*/cod_nameEquipo),format.raw/*535.45*/("""");
		$("#id_tipoPlan_mec").val(0);
		$("#tipoPlan_mec").val("");
		$("#id_bodega_mec").val(""""),_display_(/*538.29*/id_bodega),format.raw/*538.38*/("""");
		$("#valNomBod_mec").val(""""),_display_(/*539.29*/suc_nameBodega),format.raw/*539.43*/("""");
		$("#tipoBodega_mec").val(""""),_display_(/*540.30*/nameTipoBodega),format.raw/*540.44*/("""");
		document.querySelector("#id_mantEstadoOperacional").value = "0";
		document.querySelector("#id_mantEstadoEnTaller").value = "0";
		document.querySelector("#id_mantActividad").value = "0";
		document.querySelector("#id_mantTipoActividad").value = "0";
		document.querySelector("#id_mantItemIntervenido").value = "0";
		$("#horaIni_mec").val("");
		$("#horaTer_mec").val("");
		$("#lecturaIni_mec").val("0.00");
		$("#lecturaTer_mec").val("0.00");
		$("#cantidad_mec").val("0.00");
		$("#descTrabajo").val("");
		$("#estadoFinal").val("");
		$("#observaciones_mec").val("");
		signaturePad3.clear();
		signaturePad4.clear();
		
		$("#id_equipo_mecP").val(0);
		$("#valNomEquip_mecP").val("--- select ---");
		$("#id_tipoPlan_mecP").val(0);
		$("#tipoPlan_mecP").val("");
		$("#id_bodega_mecP").val(0);
		$("#valNomBod_mecP").val("--- select ---");
		$("#tipoBodega_mecP").val("");
		document.querySelector("#id_mantEstadoOperacionalP").value = "0";
		document.querySelector("#id_mantEstadoEnTallerP").value = "0";
		document.querySelector("#id_mantActividadP").value = "0";
		document.querySelector("#id_mantTipoActividadP").value = "0";
		document.querySelector("#id_mantItemIntervenidoP").value = "0";
		$("#horaIni_mecP").val("");
		$("#horaTer_mecP").val("");
		$("#lecturaIni_mecP").val("0.00");
		$("#lecturaTer_mecP").val("0.00");
		$("#cantidad_mecP").val("0.00");
		$("#descTrabajoP").val("");
		$("#estadoFinalP").val("");
		$("#observaciones_mecP").val("");
		signaturePad5.clear();
		signaturePad6.clear();
		
	"""),format.raw/*580.2*/("""}"""),format.raw/*580.3*/("""
	
	"""),format.raw/*582.2*/("""function selectOperador(id_operador, operador, tipo)"""),format.raw/*582.54*/("""{"""),format.raw/*582.55*/("""
		"""),format.raw/*583.3*/("""operador = atob(operador);
		tipo = atob(tipo);
		$('#id_operador').val(id_operador);
		$('#tipoOperador').val(tipo);
		document.getElementById("nombreOperador").innerHTML=
			"<div class='input-group input-group-sm'>"+
			  "<input class='form-control' type='text' onclick=\"$('#modalListaOperadores').modal('show');\" "+
					" id=\"valNomOperador\" value='"+operador+"' readonly>"+
			  "<div class='input-group-append'>"+
			    "<span class='input-group-text' onclick=\"$('#modalListaOperadores').modal('show');\">Buscar</span>"+
			  "</div>"+
			"</div>";
		$('#modalListaOperadores').modal('hide');
	"""),format.raw/*596.2*/("""}"""),format.raw/*596.3*/("""
	
	"""),format.raw/*598.2*/("""function selectMecanico(id_mecanico, mecanico, tipo)"""),format.raw/*598.54*/("""{"""),format.raw/*598.55*/("""
		"""),format.raw/*599.3*/("""mecanico = atob(mecanico);
		tipo = atob(tipo);
		$('#id_mecanico').val(id_mecanico);
		$('#tipoMecanico').val(tipo);
		document.getElementById("nombreMecanico").innerHTML=
			"<div class='input-group input-group-sm'>"+
			  "<input class='form-control' type='text' onclick=\"$('#modalListaMecanicos').modal('show');\" "+
				" id=\"valNomMecanico\" value='"+mecanico+"' readonly>"+
			  "<div class='input-group-append'>"+
			    "<span class='input-group-text' onclick=\"$('#modalListaMecanicos').modal('show');\">Buscar</span>"+
			  "</div>"+
			"</div>";
		$('#modalListaMecanicos').modal('hide');
	"""),format.raw/*612.2*/("""}"""),format.raw/*612.3*/("""
	
	"""),format.raw/*614.2*/("""function selectEquipo(id_equipo, codigo, equipo, tipoPlan, id_tipoPlan, unidad, estadoActual, id_unidadMantencion)"""),format.raw/*614.116*/("""{"""),format.raw/*614.117*/("""
		"""),format.raw/*615.3*/("""codigo = atob(codigo);
		equipo = atob(equipo);
		tipoPlan = atob(tipoPlan);
		$('#id_equipo_oper').val(id_equipo);
		$('#id_equipo_mec').val(id_equipo);
		$('#tipoPlan_mec').val(tipoPlan);
		$('#id_tipoPlan_mec').val(id_tipoPlan);
		$('#id_equipo_mecP').val(id_equipo);
		$('#tipoPlan_mecP').val(tipoPlan);
		$('#id_tipoPlan_mecP').val(id_tipoPlan);
		document.getElementById("nombreEquipo_oper").innerHTML=
			"<div class='input-group input-group-sm'>"+
			  "<input class='form-control' type='text' onclick=\"$('#modalListaEquipos_oper').modal('show');\" "+
				" id=\"valNomEquip_oper\" value='"+codigo+" - "+equipo+"' readonly>"+
			  "<div class='input-group-append'>"+
			    "<span class='input-group-text' onclick=\"$('#modalListaEquipos_oper').modal('show');\">Buscar</span>"+
			  "</div>"+
			"</div>";
		document.getElementById("nombreEquipo_mec").innerHTML=
			"<div class='input-group input-group-sm'>"+
			  "<input class='form-control' type='text' onclick=\"$('#modalListaEquipos_oper').modal('show');\" "+
				" id=\"valNomEquip_mec\" value='"+codigo+" - "+equipo+"' readonly>"+
			  "<div class='input-group-append'>"+
			    "<span class='input-group-text' onclick=\"$('#modalListaEquipos_oper').modal('show');\">Buscar</span>"+
			  "</div>"+
			"</div>";
		document.getElementById("nombreEquipo_mecP").innerHTML=
			"<div class='input-group input-group-sm'>"+
			  "<input class='form-control' type='text' onclick=\"$('#modalListaEquipos_mec').modal('show');\" "+
				" id=\"valNomEquip_mecP\" value='"+codigo+" - "+equipo+"' readonly>"+
			  "<div class='input-group-append'>"+
			    "<span class='input-group-text' onclick=\"$('#modalListaEquipos_mec').modal('show');\">Buscar</span>"+
			  "</div>"+
			"</div>";
		let aux = """"),_display_(/*649.15*/mapIdEquipVsBod),format.raw/*649.30*/("""";
		aux = aux.replace(/&quot;/g, '"');
		let mapIdEquipVsBod = JSON.parse(aux);
		try"""),format.raw/*652.6*/("""{"""),format.raw/*652.7*/("""
			"""),format.raw/*653.4*/("""let aux2 = mapIdEquipVsBod[id_equipo];
			let aux3 = aux2.split("_&amp;_");
			$("#id_bodega_oper").val(aux3[1]);
			document.getElementById("nombreBodega_oper").innerHTML=
				"<div class='input-group input-group-sm'>"+
				  "<input class='form-control' type='text' onclick=\"$('#modalListaBodegas').modal('show');\" "+
					" id=\"valNomBod_oper\" value='"+aux3[0]+"' readonly>"+
				  "<div class='input-group-append'>"+
				    "<span class='input-group-text' onclick=\"$('#modalListaBodegas').modal('show');\">Buscar</span>"+
				  "</div>"+
				"</div>";
			$("#tipoBodega_oper").val(aux3[2]);
				
			$("#id_bodega_mec").val(aux3[1]);
			document.getElementById("nombreBodega_mec").innerHTML=
				"<div class='input-group input-group-sm'>"+
				  "<input class='form-control' type='text' onclick=\"$('#modalListaBodegas').modal('show');\" "+
					" id=\"valNomBod_mec\" value='"+aux3[0]+"' readonly>"+
				  "<div class='input-group-append'>"+
				    "<span class='input-group-text' onclick=\"$('#modalListaBodegas').modal('show');\">Buscar</span>"+
				  "</div>"+
				"</div>";
			$("#tipoBodega_mec").val(aux3[2]);

			$("#id_bodega_mecP").val(aux3[1]);
			document.getElementById("nombreBodega_mecP").innerHTML=
				"<div class='input-group input-group-sm'>"+
				  "<input class='form-control' type='text' onclick=\"$('#modalListaBodegas').modal('show');\" "+
					" id=\"valNomBod_mecP\" value='"+aux3[0]+"' readonly>"+
				  "<div class='input-group-append'>"+
				    "<span class='input-group-text' onclick=\"$('#modalListaBodegas').modal('show');\">Buscar</span>"+
				  "</div>"+
				"</div>";
			$("#tipoBodega_mecP").val(aux3[2]);

		"""),format.raw/*688.3*/("""}"""),format.raw/*688.4*/("""catch(e)"""),format.raw/*688.12*/("""{"""),format.raw/*688.13*/("""
			"""),format.raw/*689.4*/("""$("#id_bodega_oper").val(0);
			document.getElementById("nombreBodega_oper").innerHTML=
				"<div class='input-group input-group-sm'>"+
				  "<input class='form-control' type='text' onclick=\"$('#modalListaBodegas').modal('show');\" "+
					" id=\"valNomBod_oper\" value='--- select ---' readonly>"+
				  "<div class='input-group-append'>"+
				    "<span class='input-group-text' onclick=\"$('#modalListaBodegas').modal('show');\">Buscar</span>"+
				  "</div>"+
				"</div>";
				$('#tipoBodega').val("");
			$("#id_bodega_mec").val(0);
			document.getElementById("nombreBodega_mec").innerHTML=
				"<div class='input-group input-group-sm'>"+
				  "<input class='form-control' type='text' onclick=\"$('#modalListaBodegas').modal('show');\" "+
					" id=\"valNomBod_mec\" value='--- select ---' readonly>"+
				  "<div class='input-group-append'>"+
				    "<span class='input-group-text' onclick=\"$('#modalListaBodegas').modal('show');\">Buscar</span>"+
				  "</div>"+
				"</div>";
				$('#tipoBodega').val("");

			$("#id_bodega_mecP").val(0);
			document.getElementById("nombreBodega_mecP").innerHTML=
				"<div class='input-group input-group-sm'>"+
				  "<input class='form-control' type='text' onclick=\"$('#modalListaBodegas').modal('show');\" "+
					" id=\"valNomBod_mecP\" value='--- select ---' readonly>"+
				  "<div class='input-group-append'>"+
				    "<span class='input-group-text' onclick=\"$('#modalListaBodegas').modal('show');\">Buscar</span>"+
				  "</div>"+
				"</div>";
				$('#tipoBodega').val("");
		"""),format.raw/*720.3*/("""}"""),format.raw/*720.4*/("""

		"""),format.raw/*722.3*/("""$('#idUnidadMant_oper').val(id_unidadMantencion);
		$('#idUnidadMant_mec').val(id_unidadMantencion);
		$('#idUnidadMant_mecP').val(id_unidadMantencion);

		$('#lectIni_oper').text("Lect_INI ("+unidad+"):");
		$('#lectIni_mec').text("Lect_INI ("+unidad+"):");
		$('#lectIni_mecP').text("Lect_INI ("+unidad+"):");

		$('#lecturaIni_oper').val(estadoActual);
		$('#lecturaIni_mec').val(estadoActual);
		$('#lecturaIni_mecP').val(estadoActual);
		lectAnterior = estadoActual;
		$('#lectTer_oper').text("Lect_TER ("+unidad+") (*):");
		$('#lectTer_mec').text("Lect_TER ("+unidad+") (*):");
		$('#lectTer_mecP').text("Lect_TER ("+unidad+") (*):");
		$('#lecturaTer_mec').val("0.00");
		$('#lecturaTer_meP').val("0.00");
		$('#cant_oper').text("CANT ("+unidad+"):");
		$('#cant_mec').text("CANT ("+unidad+"):");
		$('#cant_mecP').text("CANT ("+unidad+"):");

		liberaDisabled();

		$('#modalListaEquipos_oper').modal('hide');
		$('#modalListaEquipos_mec').modal('hide');

	"""),format.raw/*748.2*/("""}"""),format.raw/*748.3*/("""
	
	"""),format.raw/*750.2*/("""function selectBodega(id_bodega, sucursal, tipo, nombre)"""),format.raw/*750.58*/("""{"""),format.raw/*750.59*/("""
		"""),format.raw/*751.3*/("""sucursal = atob(sucursal);
		tipo = atob(tipo);
		nombre = atob(nombre);
		$("#id_bodega_oper").val(id_bodega);
		document.getElementById("nombreBodega_oper").innerHTML=
			"<div class='input-group input-group-sm'>"+
			  "<input class='form-control' type='text' onclick=\"$('#modalListaBodegas').modal('show');\" "+
				" id=\"valNomBod_oper\" value='"+sucursal+" - "+nombre+"' readonly>"+
			  "<div class='input-group-append'>"+
			    "<span class='input-group-text' onclick=\"$('#modalListaBodegas').modal('show');\">Buscar</span>"+
			  "</div>"+
			"</div>";
		$('#tipoBodega_oper').val(tipo);
		$('#modalListaBodegas').modal('hide');
		
		$("#id_bodega_mec").val(id_bodega);
		document.getElementById("nombreBodega_mec").innerHTML=
			"<div class='input-group input-group-sm'>"+
			  "<input class='form-control' type='text' onclick=\"$('#modalListaBodegas').modal('show');\" "+
				" id=\"valNomBod_mec\" value='"+sucursal+" - "+nombre+"' readonly>"+
			  "<div class='input-group-append'>"+
			    "<span class='input-group-text' onclick=\"$('#modalListaBodegas').modal('show');\">Buscar</span>"+
			  "</div>"+
			"</div>";
		$('#tipoBodega_mec').val(tipo);
		$('#modalListaBodegas').modal('hide');
		
		$("#id_bodega_mecP").val(id_bodega);
		document.getElementById("nombreBodega_mecP").innerHTML=
			"<div class='input-group input-group-sm'>"+
			  "<input class='form-control' type='text' onclick=\"$('#modalListaBodegas').modal('show');\" "+
				" id=\"valNomBod_mecP\" value='"+sucursal+" - "+nombre+"' readonly>"+
			  "<div class='input-group-append'>"+
			    "<span class='input-group-text' onclick=\"$('#modalListaBodegas').modal('show');\">Buscar</span>"+
			  "</div>"+
			"</div>";
		$('#tipoBodega_mecP').val(tipo);
		$('#modalListaBodegas').modal('hide');
	"""),format.raw/*789.2*/("""}"""),format.raw/*789.3*/("""
	
	"""),format.raw/*791.2*/("""function actualizaFechaReal() """),format.raw/*791.32*/("""{"""),format.raw/*791.33*/("""
		"""),format.raw/*792.3*/("""if(!isValidDate($('#fecha').val()))"""),format.raw/*792.38*/("""{"""),format.raw/*792.39*/("""
    		"""),format.raw/*793.7*/("""alertify.alert("Debe ingresar una fecha valida");
       		$('#fecha').val(""""),_display_(/*794.28*/fecha),format.raw/*794.33*/("""");
    	"""),format.raw/*795.6*/("""}"""),format.raw/*795.7*/("""else"""),format.raw/*795.11*/("""{"""),format.raw/*795.12*/("""
    		"""),format.raw/*796.7*/("""var fechaMas = new Date();
        	var fechaMenos = new Date();
        	var fecha = new Date($('#fecha').val());
        	fechaMas.setDate(fechaMas.getDate() + 0);
        	fechaMenos.setDate(fechaMenos.getDate() - 180);
        	if(fecha>fechaMas)"""),format.raw/*801.28*/("""{"""),format.raw/*801.29*/("""
        		"""),format.raw/*802.11*/("""alertify.alert("La fecha no puede ser posterior a la fecha actual.");
        		$('#fecha').val('"""),_display_(/*803.29*/fecha),format.raw/*803.34*/("""');
        	"""),format.raw/*804.10*/("""}"""),format.raw/*804.11*/("""else if(fecha<fechaMenos)"""),format.raw/*804.36*/("""{"""),format.raw/*804.37*/("""
           		"""),format.raw/*805.14*/("""alertify.alert("La fecha no puede tener mas de 180 d&iacute;as de antiguedad.");
           		$('#fecha').val('"""),_display_(/*806.32*/fecha),format.raw/*806.37*/("""');
        	"""),format.raw/*807.10*/("""}"""),format.raw/*807.11*/("""
    	"""),format.raw/*808.6*/("""}"""),format.raw/*808.7*/("""
    	
    """),format.raw/*810.5*/("""}"""),format.raw/*810.6*/("""


"""),format.raw/*813.1*/("""// ************************ COMPONENTES CORRECTIVO *****************************
	const showComponentesC = () => """),format.raw/*814.33*/("""{"""),format.raw/*814.34*/("""
		"""),format.raw/*815.3*/("""var tabla = document.getElementById('tablaComponentesC');
		for (var i=1; i<tabla.rows.length; i++)"""),format.raw/*816.42*/("""{"""),format.raw/*816.43*/("""
			"""),format.raw/*817.4*/("""var row = tabla.rows[i].getElementsByTagName('td');
			var classIdComponentesC = document.getElementsByClassName("classIdComponentesC");
			for(var j=0; j<classIdComponentesC.length; j++)"""),format.raw/*819.51*/("""{"""),format.raw/*819.52*/("""
				"""),format.raw/*820.5*/("""var id_componenteC = classIdComponentesC[j].value;
				if(row[0].innerHTML == id_componenteC)"""),format.raw/*821.43*/("""{"""),format.raw/*821.44*/("""
					"""),format.raw/*822.6*/("""document.getElementById("tablaComponentesC").rows[i].style.display='none';
					tabla.rows[i].style.display = 'none';
				"""),format.raw/*824.5*/("""}"""),format.raw/*824.6*/("""
			"""),format.raw/*825.4*/("""}"""),format.raw/*825.5*/("""
		"""),format.raw/*826.3*/("""}"""),format.raw/*826.4*/("""
		"""),format.raw/*827.3*/("""$('#modalComponentesC').modal('show')
  	"""),format.raw/*828.4*/("""}"""),format.raw/*828.5*/("""

    """),format.raw/*830.5*/("""const selectComponenteC = (id_componente, componente) => """),format.raw/*830.62*/("""{"""),format.raw/*830.63*/("""
		"""),format.raw/*831.3*/("""componente = atob(componente);
		$('#id_componenteC0').val(id_componente);
		document.getElementById('divCantidadC0').innerHTML=
			"<input type='text' class='form-control form-control-sm' id='cantidadC"+id_componente+"' name='cantidadC[]' style='text-align:right;'"+
			" onfocus=\"value=value.replace(/,/g,'')\" onkeydown='return ingresoDouble(window.event)' onChange='value=formatStandar2(value);' value='1.00' required>";
		document.getElementById('componenteC0').innerHTML="<input type='text' class='form-control form-control-sm' value='"+componente+"' readonly>";
		document.getElementById('delLineaC0').innerHTML="<a href='#' onclick='eliminarLineaC(this,"+id_componente+")'><kbd style='background-color:red'>X</kbd></a>";
		document.getElementById('tdId_componenteC0').innerHTML=id_componente;
		document.getElementById('id_componenteC0').setAttribute("id", "id_id_componenteC"+id_componente);
		document.getElementById('componenteC0').setAttribute("id", "componenteC"+id_componente);
		document.getElementById('delLineaC0').setAttribute("id", "delLineaC"+id_componente);
		document.getElementById('divCantidadC0').setAttribute("id", "divCantidadC"+id_componente);
		document.getElementById('tdId_componenteC0').setAttribute("id", "tdId_componenteC"+id_componente);
		agregaLineaC();
		$('#modalComponentesC').modal('hide');
	"""),format.raw/*846.2*/("""}"""),format.raw/*846.3*/("""
	
	"""),format.raw/*848.2*/("""function agregaLineaC()"""),format.raw/*848.25*/("""{"""),format.raw/*848.26*/("""
		"""),format.raw/*849.3*/("""var tabla = document.getElementById('tblComponentesC');
			var row = tabla.insertRow(tabla.rows.length);
		var cell=row.insertCell(0);
		cell.style.textAlign="left";
		cell.style.textAlign="left";
		cell.innerHTML=
			"<input type='hidden' class='classIdComponentesC' id='id_componenteC0' name='id_mantComponenteC[]' value='0'>"+
				"<div id='componenteC0'>"+
					"<div class='input-group input-group-sm'>"+
					  "<input class='form-control' type='text' onclick='showComponentesC()' readonly>"+
					  "<div class='input-group-append'>"+
					    "<span class='input-group-text' onclick='showComponentesC()'>Buscar</span>"+
					  "</div>"+
					"</div>"+
				"</div>";
		var cell=row.insertCell(1);
		cell.innerHTML="<div id='divCantidadC0'><input type='text' style='text-align:right;' class='form-control form-control-sm' "+
							"name='cantidadC[]' id='cantidadC0' readonly></div>";
		var cell=row.insertCell(2);
		cell.style.textAlign="center";
		cell.innerHTML="<div id='delLineaC0'></div>";
		var cell=row.insertCell(3);
		cell.style.display="none";
		cell.setAttribute("id", "tdId_componenteC0");
		cell.innerHTML="<td style='display:none'>0</td>";
	"""),format.raw/*874.2*/("""}"""),format.raw/*874.3*/("""
	
	"""),format.raw/*876.2*/("""function eliminarLineaC(nodo, id_componente)"""),format.raw/*876.46*/("""{"""),format.raw/*876.47*/("""
		"""),format.raw/*877.3*/("""var nodoTd = nodo.parentNode.parentNode; 
		var nodoTr = nodoTd.parentNode; 
		$(nodoTr).remove();
		var tabla = document.getElementById('tablaComponentesC');
		for (var i=1; i<tabla.rows.length; i++)"""),format.raw/*881.42*/("""{"""),format.raw/*881.43*/("""
			"""),format.raw/*882.4*/("""var row = tabla.rows[i].getElementsByTagName('td');
			if(row[0].innerHTML == id_componente)"""),format.raw/*883.41*/("""{"""),format.raw/*883.42*/("""
				"""),format.raw/*884.5*/("""tabla.rows[i].style.display="";
			"""),format.raw/*885.4*/("""}"""),format.raw/*885.5*/("""
		"""),format.raw/*886.3*/("""}"""),format.raw/*886.4*/("""
	"""),format.raw/*887.2*/("""}"""),format.raw/*887.3*/("""
"""),format.raw/*888.1*/("""// ************************ FIN CORRECTIVO *****************************

// ************************ COMPONENTES PREVENTIVO *****************************
	const showComponentesP = () => """),format.raw/*891.33*/("""{"""),format.raw/*891.34*/("""
		"""),format.raw/*892.3*/("""var tabla = document.getElementById('tablaComponentesP');
		for (var i=1; i<tabla.rows.length; i++)"""),format.raw/*893.42*/("""{"""),format.raw/*893.43*/("""
			"""),format.raw/*894.4*/("""var row = tabla.rows[i].getElementsByTagName('td');
			var classIdComponentesP = document.getElementsByClassName("classIdComponentesP");
			for(var j=0; j<classIdComponentesP.length; j++)"""),format.raw/*896.51*/("""{"""),format.raw/*896.52*/("""
				"""),format.raw/*897.5*/("""var id_componenteP = classIdComponentesP[j].value;
				if(row[0].innerHTML == id_componenteP)"""),format.raw/*898.43*/("""{"""),format.raw/*898.44*/("""
					"""),format.raw/*899.6*/("""document.getElementById("tablaComponentesP").rows[i].style.display='none';
					tabla.rows[i].style.display = 'none';
				"""),format.raw/*901.5*/("""}"""),format.raw/*901.6*/("""
			"""),format.raw/*902.4*/("""}"""),format.raw/*902.5*/("""
		"""),format.raw/*903.3*/("""}"""),format.raw/*903.4*/("""
		"""),format.raw/*904.3*/("""$('#modalComponentesP').modal('show')
  	"""),format.raw/*905.4*/("""}"""),format.raw/*905.5*/("""

    """),format.raw/*907.5*/("""const selectComponenteP = (id_componente, componente) => """),format.raw/*907.62*/("""{"""),format.raw/*907.63*/("""
		"""),format.raw/*908.3*/("""componente = atob(componente);
		$('#id_componenteP0').val(id_componente);
		document.getElementById('divCantidadP0').innerHTML=
			"<input type='text' class='form-control form-control-sm' id='cantidadP"+id_componente+"' name='cantidadP[]' style='text-align:right;'"+
			" onfocus=\"value=value.replace(/,/g,'')\" onkeydown='return ingresoDouble(window.event)' onChange='value=formatStandar2(value);' value='1.00' required>";
		document.getElementById('componenteP0').innerHTML="<input type='text' class='form-control form-control-sm' value='"+componente+"' readonly>";
		document.getElementById('delLineaP0').innerHTML="<a href='#' onclick='eliminarLineaP(this,"+id_componente+")'><kbd style='background-color:red'>X</kbd></a>";
		document.getElementById('tdId_componenteP0').innerHTML=id_componente;
		document.getElementById('id_componenteP0').setAttribute("id", "id_id_componenteP"+id_componente);
		document.getElementById('componenteP0').setAttribute("id", "componenteP"+id_componente);
		document.getElementById('delLineaP0').setAttribute("id", "delLineaP"+id_componente);
		document.getElementById('divCantidadP0').setAttribute("id", "divCantidadP"+id_componente);
		document.getElementById('tdId_componenteP0').setAttribute("id", "tdId_componenteP"+id_componente);
		agregaLineaP();
		$('#modalComponentesP').modal('hide');
	"""),format.raw/*923.2*/("""}"""),format.raw/*923.3*/("""
	
	"""),format.raw/*925.2*/("""function agregaLineaP()"""),format.raw/*925.25*/("""{"""),format.raw/*925.26*/("""
		"""),format.raw/*926.3*/("""var tabla = document.getElementById('tblComponentesP');
			var row = tabla.insertRow(tabla.rows.length);
		var cell=row.insertCell(0);
		cell.style.textAlign="left";
		cell.style.textAlign="left";
		cell.innerHTML=
			"<input type='hidden' class='classIdComponentesP' id='id_componenteP0' name='id_mantComponenteP[]' value='0'>"+
				"<div id='componenteP0'>"+
					"<div class='input-group input-group-sm'>"+
					  "<input class='form-control' type='text' onclick='showComponentesP()' readonly>"+
					  "<div class='input-group-append'>"+
					    "<span class='input-group-text' onclick='showComponentesP()'>Buscar</span>"+
					  "</div>"+
					"</div>"+
				"</div>";
		var cell=row.insertCell(1);
		cell.innerHTML="<div id='divCantidadP0'><input type='text' style='text-align:right;' class='form-control form-control-sm' "+
							"name='cantidadP[]' id='cantidadP0' readonly></div>";
		var cell=row.insertCell(2);
		cell.style.textAlign="center";
		cell.innerHTML="<div id='delLineaP0'></div>";
		var cell=row.insertCell(3);
		cell.style.display="none";
		cell.setAttribute("id", "tdId_componenteP0");
		cell.innerHTML="<td style='display:none'>0</td>";
	"""),format.raw/*951.2*/("""}"""),format.raw/*951.3*/("""
	
	"""),format.raw/*953.2*/("""function eliminarLineaP(nodo, id_componente)"""),format.raw/*953.46*/("""{"""),format.raw/*953.47*/("""
		"""),format.raw/*954.3*/("""var nodoTd = nodo.parentNode.parentNode; 
		var nodoTr = nodoTd.parentNode; 
		$(nodoTr).remove();
		var tabla = document.getElementById('tablaComponentesP');
		for (var i=1; i<tabla.rows.length; i++)"""),format.raw/*958.42*/("""{"""),format.raw/*958.43*/("""
			"""),format.raw/*959.4*/("""var row = tabla.rows[i].getElementsByTagName('td');
			if(row[0].innerHTML == id_componente)"""),format.raw/*960.41*/("""{"""),format.raw/*960.42*/("""
				"""),format.raw/*961.5*/("""tabla.rows[i].style.display="";
			"""),format.raw/*962.4*/("""}"""),format.raw/*962.5*/("""
		"""),format.raw/*963.3*/("""}"""),format.raw/*963.4*/("""
	"""),format.raw/*964.2*/("""}"""),format.raw/*964.3*/("""
"""),format.raw/*965.1*/("""// ************************ FIN CORRECTIVO *****************************
	
	let extArray = new Array(".gif", ".jpg", ".png", ".jpeg", ".pdf", ".xls", ".doc", ".xlsx", ".docx", ".tar", ".zip", ".rar");
	const LimitAttach = (form, file) => """),format.raw/*968.38*/("""{"""),format.raw/*968.39*/("""
		
		"""),format.raw/*970.3*/("""if (!file) return;
		
		const $inputArchivos = document.querySelector("#docAdjunto");
		const archivosParaSubir = $inputArchivos.files;
		let tamanio = 0;
		let allowSubmit = false;
		
		for(let i=0; i<archivosParaSubir.length; i++)"""),format.raw/*977.48*/("""{"""),format.raw/*977.49*/("""
			"""),format.raw/*978.4*/("""tamanio += archivosParaSubir[i].size;
			let nombre = archivosParaSubir[i].name;
			let extencion = nombre.substring(nombre.lastIndexOf(".")).toLowerCase();
			for (let j = 0; j < extArray.length; j++) """),format.raw/*981.46*/("""{"""),format.raw/*981.47*/("""
				"""),format.raw/*982.5*/("""if (extArray[j] == extencion) """),format.raw/*982.35*/("""{"""),format.raw/*982.36*/(""" 
					"""),format.raw/*983.6*/("""allowSubmit = true;
				"""),format.raw/*984.5*/("""}"""),format.raw/*984.6*/("""
			"""),format.raw/*985.4*/("""}"""),format.raw/*985.5*/("""
			"""),format.raw/*986.4*/("""if(allowSubmit && i < archivosParaSubir.length -1)"""),format.raw/*986.54*/("""{"""),format.raw/*986.55*/("""
				"""),format.raw/*987.5*/("""allowSubmit = false;
			"""),format.raw/*988.4*/("""}"""),format.raw/*988.5*/("""
		"""),format.raw/*989.3*/("""}"""),format.raw/*989.4*/("""
		
		"""),format.raw/*991.3*/("""if (allowSubmit) """),format.raw/*991.20*/("""{"""),format.raw/*991.21*/("""
			"""),format.raw/*992.4*/("""if(tamanio > 200000000)"""),format.raw/*992.27*/("""{"""),format.raw/*992.28*/("""
				"""),format.raw/*993.5*/("""alert("Se permite máximo subir 200 MB,"
				+" lo que se intenta subir pesa: "+Math.round(tamanio/10000)/100+" MB");
				form.docAdjunto.value="";
			"""),format.raw/*996.4*/("""}"""),format.raw/*996.5*/("""else"""),format.raw/*996.9*/("""{"""),format.raw/*996.10*/("""
				"""),format.raw/*997.5*/("""alert("Documento subido con éxito");
				$("#txtBtn").text("Cambiar Documento");
			"""),format.raw/*999.4*/("""}"""),format.raw/*999.5*/("""
		"""),format.raw/*1000.3*/("""}"""),format.raw/*1000.4*/("""else"""),format.raw/*1000.8*/("""{"""),format.raw/*1000.9*/("""
			"""),format.raw/*1001.4*/("""alert("Se permiten únicamente archivos con la extención: " 
			+ (extArray.join("  ")) + "\nPor favor, seleccione solo archivos con extenciones permitidas "
			+ "e intente de nuevo.");
			form.docAdjunto.value="";
		"""),format.raw/*1005.3*/("""}"""),format.raw/*1005.4*/("""
	"""),format.raw/*1006.2*/("""}"""),format.raw/*1006.3*/("""
		
"""),format.raw/*1008.1*/("""</script>
	

"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],fecha:String,actor:tables.MantActorPersonal,operMec:tables.MantOperadorMecanico,listPlanMant:List[tables.PlanMantencion],mapIdEquipVsBod:String,listBod:List[tables.BodegaEmpresa],listEstaObra:List[tables.MantEstadoEnObra],listEquipos:List[List[String]],listTipoMantencion:List[tables.TipoMantencion],listEstadoEnTaller:List[tables.MantEstadoEnTaller],listActividad:List[tables.MantActividad],listTipoActividad:List[tables.MantTipoActividad],listComponentes:List[tables.MantComponente],listItemIntervenido:List[tables.MantItemIntervenido],idEquipo:String,cod_nameEquipo:String,id_bodega:String,suc_nameBodega:String,nameTipoBodega:String,estadoActual:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,fecha,actor,operMec,listPlanMant,mapIdEquipVsBod,listBod,listEstaObra,listEquipos,listTipoMantencion,listEstadoEnTaller,listActividad,listTipoActividad,listComponentes,listItemIntervenido,idEquipo,cod_nameEquipo,id_bodega,suc_nameBodega,nameTipoBodega,estadoActual)

  def f:((Map[String,String],String,tables.MantActorPersonal,tables.MantOperadorMecanico,List[tables.PlanMantencion],String,List[tables.BodegaEmpresa],List[tables.MantEstadoEnObra],List[List[String]],List[tables.TipoMantencion],List[tables.MantEstadoEnTaller],List[tables.MantActividad],List[tables.MantTipoActividad],List[tables.MantComponente],List[tables.MantItemIntervenido],String,String,String,String,String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,fecha,actor,operMec,listPlanMant,mapIdEquipVsBod,listBod,listEstaObra,listEquipos,listTipoMantencion,listEstadoEnTaller,listActividad,listTipoActividad,listComponentes,listItemIntervenido,idEquipo,cod_nameEquipo,id_bodega,suc_nameBodega,nameTipoBodega,estadoActual) => apply(mapDiccionario,fecha,actor,operMec,listPlanMant,mapIdEquipVsBod,listBod,listEstaObra,listEquipos,listTipoMantencion,listEstadoEnTaller,listActividad,listTipoActividad,listComponentes,listItemIntervenido,idEquipo,cod_nameEquipo,id_bodega,suc_nameBodega,nameTipoBodega,estadoActual)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuMantencion/mantWebReportNew.scala.html
                  HASH: 1d57e068dc6d500118554f96fb6a89f0fcf89dab
                  MATRIX: 1374->1|2201->735|2230->739|2246->747|2285->749|2315->753|2536->947|2552->954|2581->962|2660->1014|2689->1022|3867->2173|3893->2178|4705->2963|4719->2968|4748->2976|4863->3064|4877->3069|4911->3081|5194->3337|5233->3338|5267->3346|5434->3492|5485->3500|5543->3531|5582->3532|5616->3540|5902->3805|5952->3812|5985->3818|6958->4765|7000->4791|7039->4792|7075->4801|7141->4840|7155->4845|7191->4860|7240->4881|7255->4886|7295->4904|7345->4926|7360->4931|7400->4949|7449->4970|7464->4975|7506->4995|7550->5011|7565->5016|7604->5033|7647->5048|7662->5053|7706->5075|7749->5090|7764->5095|7804->5113|7847->5128|7862->5133|7909->5158|7992->5213|8007->5218|8046->5235|8135->5296|8150->5301|8190->5319|8278->5379|8293->5384|8333->5402|8421->5462|8436->5467|8478->5487|8541->5519|8576->5526|9688->6611|9730->6636|9770->6637|9807->6646|9874->6685|9889->6690|9918->6697|9966->6717|9981->6722|10010->6729|10059->6750|10074->6755|10103->6762|10191->6822|10206->6827|10235->6834|10278->6849|10293->6854|10322->6861|10365->6876|10380->6881|10409->6888|10492->6943|10507->6948|10536->6955|10625->7016|10640->7021|10669->7028|10757->7088|10772->7093|10801->7100|10864->7132|10899->7139|11476->7688|11522->7712|12014->8176|12060->8200|12152->8265|12190->8286|12230->8287|12267->8296|12323->8324|12338->8329|12368->8337|12406->8347|12421->8352|12461->8370|12500->8381|12515->8386|12560->8408|12601->8420|12617->8425|12652->8437|12737->8494|12752->8499|12792->8517|12881->8578|12896->8583|12940->8605|13028->8665|13043->8670|13077->8682|13153->8727|13188->8734|14342->9860|14388->9889|14428->9890|14467->9900|14528->9933|14543->9938|14573->9946|14610->9955|14625->9960|14659->9972|14729->10014|14744->10019|14774->10027|14835->10060|14850->10065|14884->10077|14950->10111|14986->10119|16147->11252|16193->11281|16233->11282|16272->11292|16333->11325|16348->11330|16378->11338|16415->11347|16430->11352|16464->11364|16534->11406|16549->11411|16579->11419|16640->11452|16655->11457|16689->11469|16755->11503|16791->11511|17103->11792|17134->11795|17200->11833|17216->11839|17291->11892|17422->11994|17452->11995|17485->12000|17587->12074|17616->12075|17733->12163|17763->12164|17796->12169|17898->12243|17927->12244|18041->12329|18071->12330|18104->12335|18206->12409|18235->12410|18352->12498|18382->12499|18415->12504|18517->12578|18546->12579|18663->12667|18693->12668|18726->12673|18828->12747|18857->12748|18974->12836|19004->12837|19037->12842|19139->12916|19168->12917|19233->12953|19263->12954|19296->12959|19491->13126|19520->13127|19554->13133|19598->13148|19628->13149|19661->13154|19770->13235|19799->13236|19829->13238|19948->13328|19978->13329|20009->13332|20070->13364|20100->13365|20135->13372|20341->13549|20371->13550|20411->13561|20517->13638|20547->13639|20581->13645|20610->13646|20639->13647|20708->13687|20738->13688|20773->13695|20979->13872|21009->13873|21049->13884|21155->13961|21185->13962|21219->13968|21248->13969|21277->13970|21344->14008|21374->14009|21409->14016|21615->14193|21645->14194|21685->14205|21791->14282|21821->14283|21855->14289|21884->14290|21913->14291|21976->14325|22006->14326|22041->14333|22247->14510|22277->14511|22317->14522|22423->14599|22453->14600|22487->14606|22516->14607|22545->14608|22614->14648|22644->14649|22676->14653|22944->14892|22974->14893|23014->14904|23120->14981|23150->14982|23184->14988|23213->14989|23242->14990|23311->15030|23341->15031|23373->15035|23641->15274|23671->15275|23711->15286|23817->15363|23847->15364|23881->15370|23910->15371|23939->15372|23977->15383|24007->15391|24045->15400|24075->15401|24107->15405|24163->15433|24197->15445|24259->15479|24293->15491|24356->15526|24390->15538|24442->15562|24476->15574|24530->15600|24559->15601|24591->15605|24691->15677|24720->15678|24749->15679|24825->15726|24855->15727|24887->15731|24937->15752|24967->15753|24999->15757|25946->16676|25975->16677|26030->16703|26060->16704|26092->16708|26439->17027|26468->17028|26502->17034|26553->17057|26582->17058|26614->17062|26671->17090|26701->17091|26732->17094|27779->18113|27808->18114|27840->18118|27898->18147|27928->18148|27959->18151|28989->19153|29018->19154|29050->19158|29131->19210|29161->19211|29193->19215|29248->19241|29278->19242|29310->19246|29515->19423|29544->19424|29604->19455|29634->19456|29666->19460|29719->19486|29749->19494|29787->19503|29817->19504|29850->19509|29906->19537|29940->19549|30003->19584|30037->19596|30101->19632|30135->19644|30188->19669|30222->19681|30278->19709|30307->19710|30339->19714|30369->19715|30402->19720|30452->19742|30481->19743|30513->19747|30675->19881|30704->19882|30735->19885|30764->19886|30796->19890|30853->19918|30883->19919|30914->19922|30968->19948|30998->19956|31060->19990|31096->20004|31218->20098|31249->20107|31309->20139|31345->20153|31406->20186|31442->20200|32997->21727|33026->21728|33058->21732|33139->21784|33169->21785|33200->21788|33836->22396|33865->22397|33897->22401|33978->22453|34008->22454|34039->22457|34671->23061|34700->23062|34732->23066|34876->23180|34907->23181|34938->23184|36719->24937|36756->24952|36870->25038|36899->25039|36931->25043|38615->26699|38644->26700|38681->26708|38711->26709|38743->26713|40307->28249|40336->28250|40368->28254|41362->29220|41391->29221|41423->29225|41508->29281|41538->29282|41569->29285|43380->31068|43409->31069|43441->31073|43500->31103|43530->31104|43561->31107|43625->31142|43655->31143|43690->31150|43795->31227|43822->31232|43859->31241|43888->31242|43921->31246|43951->31247|43986->31254|44265->31504|44295->31505|44335->31516|44461->31614|44488->31619|44530->31632|44560->31633|44614->31658|44644->31659|44687->31673|44827->31785|44854->31790|44896->31803|44926->31804|44960->31810|44989->31811|45028->31822|45057->31823|45088->31826|45230->31939|45260->31940|45291->31943|45419->32042|45449->32043|45481->32047|45697->32234|45727->32235|45760->32240|45882->32333|45912->32334|45946->32340|46096->32462|46125->32463|46157->32467|46186->32468|46217->32471|46246->32472|46277->32475|46346->32516|46375->32517|46409->32523|46495->32580|46525->32581|46556->32584|47918->33918|47947->33919|47979->33923|48031->33946|48061->33947|48092->33950|49283->35113|49312->35114|49344->35118|49417->35162|49447->35163|49478->35166|49707->35366|49737->35367|49769->35371|49890->35463|49920->35464|49953->35469|50016->35504|50045->35505|50076->35508|50105->35509|50135->35511|50164->35512|50193->35513|50409->35700|50439->35701|50470->35704|50598->35803|50628->35804|50660->35808|50876->35995|50906->35996|50939->36001|51061->36094|51091->36095|51125->36101|51275->36223|51304->36224|51336->36228|51365->36229|51396->36232|51425->36233|51456->36236|51525->36277|51554->36278|51588->36284|51674->36341|51704->36342|51735->36345|53097->37679|53126->37680|53158->37684|53210->37707|53240->37708|53271->37711|54462->38874|54491->38875|54523->38879|54596->38923|54626->38924|54657->38927|54886->39127|54916->39128|54948->39132|55069->39224|55099->39225|55132->39230|55195->39265|55224->39266|55255->39269|55284->39270|55314->39272|55343->39273|55372->39274|55639->39512|55669->39513|55703->39519|55964->39751|55994->39752|56026->39756|56257->39958|56287->39959|56320->39964|56379->39994|56409->39995|56444->40002|56496->40026|56525->40027|56557->40031|56586->40032|56618->40036|56697->40086|56727->40087|56760->40092|56812->40116|56841->40117|56872->40120|56901->40121|56935->40127|56981->40144|57011->40145|57043->40149|57095->40172|57125->40173|57158->40178|57336->40328|57365->40329|57397->40333|57427->40334|57460->40339|57572->40423|57601->40424|57633->40427|57663->40428|57696->40432|57726->40433|57759->40437|58005->40654|58035->40655|58066->40657|58096->40658|58129->40662
                  LINES: 28->1|33->2|36->5|36->5|36->5|39->8|42->11|42->11|42->11|43->12|43->12|67->36|67->36|83->52|83->52|83->52|84->53|84->53|84->53|93->62|93->62|94->63|95->64|96->65|98->67|98->67|99->68|101->70|102->71|103->72|128->97|128->97|128->97|129->98|130->99|130->99|130->99|131->100|131->100|131->100|132->101|132->101|132->101|133->102|133->102|133->102|134->103|134->103|134->103|135->104|135->104|135->104|136->105|136->105|136->105|137->106|137->106|137->106|138->107|138->107|138->107|139->108|139->108|139->108|140->109|140->109|140->109|141->110|141->110|141->110|143->112|144->113|173->142|173->142|173->142|174->143|175->144|175->144|175->144|176->145|176->145|176->145|177->146|177->146|177->146|180->149|180->149|180->149|181->150|181->150|181->150|182->151|182->151|182->151|183->152|183->152|183->152|184->153|184->153|184->153|185->154|185->154|185->154|187->156|188->157|202->171|202->171|213->182|213->182|217->186|217->186|217->186|218->187|218->187|218->187|218->187|218->187|218->187|218->187|218->187|218->187|218->187|218->187|218->187|218->187|219->188|219->188|219->188|220->189|220->189|220->189|221->190|221->190|221->190|223->192|224->193|253->222|253->222|253->222|254->223|254->223|254->223|254->223|254->223|254->223|254->223|255->224|255->224|255->224|256->225|256->225|256->225|258->227|259->228|288->257|288->257|288->257|289->258|289->258|289->258|289->258|289->258|289->258|289->258|290->259|290->259|290->259|291->260|291->260|291->260|293->262|294->263|307->276|310->279|312->281|312->281|312->281|314->283|314->283|315->284|317->286|317->286|319->288|319->288|320->289|322->291|322->291|323->292|323->292|324->293|326->295|326->295|328->297|328->297|329->298|331->300|331->300|333->302|333->302|334->303|336->305|336->305|338->307|338->307|339->308|341->310|341->310|345->314|345->314|346->315|353->322|353->322|355->324|355->324|355->324|356->325|361->330|361->330|362->331|367->336|367->336|368->337|368->337|368->337|369->338|375->344|375->344|376->345|377->346|377->346|378->347|378->347|378->347|380->349|380->349|381->350|387->356|387->356|388->357|389->358|389->358|390->359|390->359|390->359|391->360|391->360|392->361|398->367|398->367|399->368|400->369|400->369|401->370|401->370|401->370|402->371|402->371|403->372|409->378|409->378|410->379|411->380|411->380|412->381|412->381|412->381|414->383|414->383|415->384|421->390|421->390|422->391|423->392|423->392|424->393|424->393|424->393|426->395|426->395|427->396|433->402|433->402|434->403|435->404|435->404|436->405|436->405|436->405|438->407|438->407|438->407|438->407|439->408|439->408|439->408|440->409|440->409|441->410|441->410|442->411|442->411|444->413|444->413|446->415|450->419|450->419|450->419|453->422|453->422|454->423|454->423|454->423|455->424|477->446|477->446|477->446|477->446|478->447|485->454|485->454|487->456|489->458|489->458|491->460|491->460|491->460|492->461|514->483|514->483|516->485|516->485|516->485|517->486|539->508|539->508|541->510|541->510|541->510|542->511|542->511|542->511|543->512|547->516|547->516|547->516|547->516|548->517|549->518|549->518|549->518|549->518|550->519|550->519|550->519|551->520|551->520|552->521|552->521|553->522|553->522|555->524|555->524|555->524|555->524|556->525|557->526|557->526|558->527|560->529|560->529|562->531|562->531|564->533|564->533|564->533|565->534|565->534|565->534|566->535|566->535|569->538|569->538|570->539|570->539|571->540|571->540|611->580|611->580|613->582|613->582|613->582|614->583|627->596|627->596|629->598|629->598|629->598|630->599|643->612|643->612|645->614|645->614|645->614|646->615|680->649|680->649|683->652|683->652|684->653|719->688|719->688|719->688|719->688|720->689|751->720|751->720|753->722|779->748|779->748|781->750|781->750|781->750|782->751|820->789|820->789|822->791|822->791|822->791|823->792|823->792|823->792|824->793|825->794|825->794|826->795|826->795|826->795|826->795|827->796|832->801|832->801|833->802|834->803|834->803|835->804|835->804|835->804|835->804|836->805|837->806|837->806|838->807|838->807|839->808|839->808|841->810|841->810|844->813|845->814|845->814|846->815|847->816|847->816|848->817|850->819|850->819|851->820|852->821|852->821|853->822|855->824|855->824|856->825|856->825|857->826|857->826|858->827|859->828|859->828|861->830|861->830|861->830|862->831|877->846|877->846|879->848|879->848|879->848|880->849|905->874|905->874|907->876|907->876|907->876|908->877|912->881|912->881|913->882|914->883|914->883|915->884|916->885|916->885|917->886|917->886|918->887|918->887|919->888|922->891|922->891|923->892|924->893|924->893|925->894|927->896|927->896|928->897|929->898|929->898|930->899|932->901|932->901|933->902|933->902|934->903|934->903|935->904|936->905|936->905|938->907|938->907|938->907|939->908|954->923|954->923|956->925|956->925|956->925|957->926|982->951|982->951|984->953|984->953|984->953|985->954|989->958|989->958|990->959|991->960|991->960|992->961|993->962|993->962|994->963|994->963|995->964|995->964|996->965|999->968|999->968|1001->970|1008->977|1008->977|1009->978|1012->981|1012->981|1013->982|1013->982|1013->982|1014->983|1015->984|1015->984|1016->985|1016->985|1017->986|1017->986|1017->986|1018->987|1019->988|1019->988|1020->989|1020->989|1022->991|1022->991|1022->991|1023->992|1023->992|1023->992|1024->993|1027->996|1027->996|1027->996|1027->996|1028->997|1030->999|1030->999|1031->1000|1031->1000|1031->1000|1031->1000|1032->1001|1036->1005|1036->1005|1037->1006|1037->1006|1039->1008
                  -- GENERATED --
              */
          