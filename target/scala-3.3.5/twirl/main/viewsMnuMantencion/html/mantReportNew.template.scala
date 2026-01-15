
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

object mantReportNew extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template17[Map[String,String],Map[String,String],utilities.UserMnu,String,List[tables.MantActorPersonal],List[tables.MantOperadorMecanico],List[tables.PlanMantencion],String,List[tables.BodegaEmpresa],List[tables.MantEstadoEnObra],List[List[String]],List[tables.TipoMantencion],List[tables.MantEstadoEnTaller],List[tables.MantActividad],List[tables.MantTipoActividad],List[tables.MantComponente],List[tables.MantItemIntervenido],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
fecha: String, listActor: List[tables.MantActorPersonal], listOperMec: List[tables.MantOperadorMecanico],
listPlanMant: List[tables.PlanMantencion], mapIdEquipVsBod: String, listBod: List[tables.BodegaEmpresa],
listEstaObra: List[tables.MantEstadoEnObra], listEquipos: List[List[String]],
listTipoMantencion: List[tables.TipoMantencion], listEstadoEnTaller: List[tables.MantEstadoEnTaller], 
listActividad: List[tables.MantActividad], listTipoActividad: List[tables.MantTipoActividad],
listComponentes: List[tables.MantComponente], listItemIntervenido: List[tables.MantItemIntervenido]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*8.1*/("""


"""),_display_(/*11.2*/main("")/*11.10*/ {_display_(Seq[Any](format.raw/*11.12*/("""

"""),_display_(/*13.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*13.50*/("""
	"""),format.raw/*14.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*15.4*/barraTitulo(mapDiccionario, "INGRESO DE REPORT OPERADOR MECANICO", "")),format.raw/*15.74*/("""
		
		"""),format.raw/*17.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
				<br>
				<form id="grabarRptOperadorMecanico" action="/routes3/mantReportNewSave/" method="POST" enctype="multipart/form-data">

					<table class="table table-sm table-bordered table-condensed table-fluid">
						<thead>
							<tr>
								<td style="text-align:left;"><strong>FECHA (*):</strong></td>
						        <td style="text-align:left;" width="70%" colspan="3">
						        	<input style="max-width:150px" type="date" class="form-control form-control-sm" 
										id="fecha" 
										name="fecha" 
										value=""""),_display_(/*30.19*/fecha),format.raw/*30.24*/("""" 
										onblur="actualizaFechaReal()" 
										required>
						        </td>
						        <td style="text-align:center;" width="70px">
									<span class="btn btn-info btn-sm btn-file" style="font-size: 8px;">
											<input type="hidden" name="docAnexo" value="0">
											<div id="txtBtn">Adjuntar</div>
				    						<input type="file" multiple id="docAdjunto" name="docAdjunto[]" onchange="LimitAttach(this.form, this.form.docAdjunto.value); $('#seModifico').val(1);">
									</span>
								</td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>USER_MADA:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
						        	<input type="hidden" id="id_userMada" name="id_userMada" value=""""),_display_(/*45.81*/userMnu/*45.88*/.getId_usuario()),format.raw/*45.104*/("""">
						        		<div class="input-group input-group-sm">
										  <input class="form-control" type="text" value=""""),_display_(/*47.61*/userMnu/*47.68*/.getUserName()),format.raw/*47.82*/("""" readonly>
										</div>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>PERFIL (*):</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
						        	<select class="form-control form-control-sm" 
										id="id_mantActorPersonal" 
										name="id_mantActorPersonal" 
										onchange="selectPerfil(value)" 
										style="font-weight: bold; color: blue; font-size: 14px;"
										required>
							        		<option class="blank" value="">-- Perfil --</option>
						            		"""),_display_(/*61.22*/for(lista <- listActor) yield /*61.45*/{_display_(Seq[Any](format.raw/*61.46*/("""
						                		"""),format.raw/*62.25*/("""<option value=""""),_display_(/*62.41*/lista/*62.46*/.id),format.raw/*62.49*/("""">"""),_display_(/*62.52*/lista/*62.57*/.nombre.toUpperCase()),format.raw/*62.78*/("""</option>
											""")))}),format.raw/*63.13*/("""
							        """),format.raw/*64.16*/("""</select>
						        </td>
							</tr>
							</thead>
					</table>
							
					<input type="hidden" name="firmaEjecutor" id="firmaEjecutor" value="0">
					<input type="hidden" name="firmaAprobador" id="firmaAprobador" value="0">
						
					<div id="viewOperador" style="display:none;">
						"""),_display_(/*74.8*/mantReportNewOperador(mapDiccionario, fecha, listEstaObra, listOperMec)),format.raw/*74.79*/("""
					"""),format.raw/*75.6*/("""</div>
					<div id="viewMecanico" style="display:none;">
						"""),_display_(/*77.8*/mantReportNewMecanico(mapDiccionario, fecha, listEstaObra, listOperMec, listTipoMantencion,
								listEstadoEnTaller,listActividad,listTipoActividad,listComponentes,listItemIntervenido)),format.raw/*78.96*/("""
					"""),format.raw/*79.6*/("""</div>
					<font style="font-size: 12px;">(*) Campos Obligatorios</font>
					
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
							"""),_display_(/*106.9*/for(lista <- listPlanMant) yield /*106.35*/{_display_(Seq[Any](format.raw/*106.36*/("""
								"""),format.raw/*107.9*/("""<TR onclick="selectEquipo(
										'"""),_display_(/*108.13*/lista/*108.18*/.getId_equipo()),format.raw/*108.33*/("""', 
										btoa('"""),_display_(/*109.18*/lista/*109.23*/.getEquipoCodigo()),format.raw/*109.41*/("""'), 
										btoa('"""),_display_(/*110.18*/lista/*110.23*/.getEquipoNombre()),format.raw/*110.41*/("""'),
										btoa('"""),_display_(/*111.18*/lista/*111.23*/.getTipoPlanNombre()),format.raw/*111.43*/("""'),
										'"""),_display_(/*112.13*/lista/*112.18*/.getId_tipoPlan()),format.raw/*112.35*/("""',
										'"""),_display_(/*113.13*/lista/*113.18*/.getUnidadMantencion()),format.raw/*113.40*/("""',
										'"""),_display_(/*114.13*/lista/*114.18*/.getEstadoActual()),format.raw/*114.36*/("""',
										'"""),_display_(/*115.13*/lista/*115.18*/.getId_unidadMantencion()),format.raw/*115.43*/("""')">
									<td style="text-align:left"><a href="#">"""),_display_(/*116.51*/lista/*116.56*/.getEquipoGrupo()),format.raw/*116.73*/("""</a></td>	
									<td style="text-align:left"><a href="#">"""),_display_(/*117.51*/lista/*117.56*/.getEquipoCodigo()),format.raw/*117.74*/("""</a></td>
									<td style="text-align:left"><a href="#">"""),_display_(/*118.51*/lista/*118.56*/.getEquipoNombre()),format.raw/*118.74*/("""</a></td>
									<td style="text-align:left"><a href="#">"""),_display_(/*119.51*/lista/*119.56*/.getTipoPlanNombre()),format.raw/*119.76*/("""</a></td>
								</TR>
							""")))}),format.raw/*121.9*/("""
						"""),format.raw/*122.7*/("""</tbody>
					</table>
					<div align="center">
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
							"""),_display_(/*151.9*/for(lista <- listEquipos) yield /*151.34*/{_display_(Seq[Any](format.raw/*151.35*/("""
								"""),format.raw/*152.9*/("""<TR onclick="selectEquipo(
										'"""),_display_(/*153.13*/lista/*153.18*/.get(0)),format.raw/*153.25*/("""',
										btoa('"""),_display_(/*154.18*/lista/*154.23*/.get(2)),format.raw/*154.30*/("""'),
										btoa('"""),_display_(/*155.18*/lista/*155.23*/.get(3)),format.raw/*155.30*/("""'),
										btoa('no Aplica'),
										'0',
										'"""),_display_(/*158.13*/lista/*158.18*/.get(4)),format.raw/*158.25*/("""',
										'"""),_display_(/*159.13*/lista/*159.18*/.get(5)),format.raw/*159.25*/("""',
										'"""),_display_(/*160.13*/lista/*160.18*/.get(6)),format.raw/*160.25*/("""')">
									<td style="text-align:left"><a href="#">"""),_display_(/*161.51*/lista/*161.56*/.get(1)),format.raw/*161.63*/("""</a></td>	
									<td style="text-align:left"><a href="#">"""),_display_(/*162.51*/lista/*162.56*/.get(2)),format.raw/*162.63*/("""</a></td>
									<td style="text-align:left"><a href="#">"""),_display_(/*163.51*/lista/*163.56*/.get(3)),format.raw/*163.63*/("""</a></td>
								</TR>
							""")))}),format.raw/*165.9*/("""
						"""),format.raw/*166.7*/("""</tbody>
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
			        <h5 class="modal-title">Seleccionar """),_display_(/*180.49*/mapDiccionario("Bodega")),format.raw/*180.73*/("""/Proyecto</h5>
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
								<th>"""),_display_(/*191.14*/mapDiccionario("Bodega")),format.raw/*191.38*/("""/Proyecto</th>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*195.9*/for(lista <- listBod) yield /*195.30*/{_display_(Seq[Any](format.raw/*195.31*/("""
								"""),format.raw/*196.9*/("""<TR onclick="selectBodega('"""),_display_(/*196.37*/lista/*196.42*/.getId()),format.raw/*196.50*/("""', btoa('"""),_display_(/*196.60*/lista/*196.65*/.getNameSucursal()),format.raw/*196.83*/("""'), btoa('"""),_display_(/*196.94*/lista/*196.99*/.getNombreTipoBodega()),format.raw/*196.121*/("""') , btoa('"""),_display_(/*196.133*/lista/*196.138*/.getNombre()),format.raw/*196.150*/("""') )">
									<td style="text-align:left"><a href="#">"""),_display_(/*197.51*/lista/*197.56*/.getNameSucursal()),format.raw/*197.74*/("""</a></td>	
									<td style="text-align:left"><a href="#">"""),_display_(/*198.51*/lista/*198.56*/.getNombreTipoBodega()),format.raw/*198.78*/("""</a></td>
									<td style="text-align:left"><a href="#">"""),_display_(/*199.51*/lista/*199.56*/.getNombre()),format.raw/*199.68*/("""</a></td>													
								</TR>
							""")))}),format.raw/*201.9*/("""
						"""),format.raw/*202.7*/("""</tbody>
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
								"""),_display_(/*231.10*/for(lista <- listComponentes) yield /*231.39*/{_display_(Seq[Any](format.raw/*231.40*/("""
									"""),format.raw/*232.10*/("""<tr onclick="selectComponenteC('"""),_display_(/*232.43*/lista/*232.48*/.getId()),format.raw/*232.56*/("""',btoa('"""),_display_(/*232.65*/lista/*232.70*/.getNombre()),format.raw/*232.82*/("""'))">
										<td style='display:none'>"""),_display_(/*233.37*/lista/*233.42*/.getId()),format.raw/*233.50*/("""</td>
										<td><a href="#">"""),_display_(/*234.28*/lista/*234.33*/.getNombre()),format.raw/*234.45*/("""</a></td>
									</tr>
								""")))}),format.raw/*236.10*/("""
							"""),format.raw/*237.8*/("""</tbody>
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
								"""),_display_(/*266.10*/for(lista <- listComponentes) yield /*266.39*/{_display_(Seq[Any](format.raw/*266.40*/("""
									"""),format.raw/*267.10*/("""<tr onclick="selectComponenteP('"""),_display_(/*267.43*/lista/*267.48*/.getId()),format.raw/*267.56*/("""',btoa('"""),_display_(/*267.65*/lista/*267.70*/.getNombre()),format.raw/*267.82*/("""'))">
										<td style='display:none'>"""),_display_(/*268.37*/lista/*268.42*/.getId()),format.raw/*268.50*/("""</td>
										<td><a href="#">"""),_display_(/*269.28*/lista/*269.33*/.getNombre()),format.raw/*269.45*/("""</a></td>
									</tr>
								""")))}),format.raw/*271.10*/("""
							"""),format.raw/*272.8*/("""</tbody>
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
""")))}),format.raw/*285.2*/("""


"""),format.raw/*288.1*/("""<!-- CONFIGURA PAD -->

<script src=""""),_display_(/*290.15*/routes/*290.21*/.Assets.versioned("javascripts/signature_pad.min.js")),format.raw/*290.74*/(""""></script>
	<script>
		let signaturePad = new SignaturePad(document.getElementById('signature-pad'), """),format.raw/*292.81*/("""{"""),format.raw/*292.82*/("""
		  """),format.raw/*293.5*/("""backgroundColor: 'rgba(255, 255, 255, 0)',
		  penColor: 'rgb(0, 0, 0)'
		"""),format.raw/*295.3*/("""}"""),format.raw/*295.4*/(""");
		
		let signaturePad2 = new SignaturePad(document.getElementById('signature-pad2'), """),format.raw/*297.83*/("""{"""),format.raw/*297.84*/("""
		  """),format.raw/*298.5*/("""backgroundColor: 'rgba(255, 255, 255, 0)',
		  penColor: 'rgb(0, 0, 0)'
		"""),format.raw/*300.3*/("""}"""),format.raw/*300.4*/(""");
		let signaturePad3 = new SignaturePad(document.getElementById('signature-pad3'), """),format.raw/*301.83*/("""{"""),format.raw/*301.84*/("""
		  """),format.raw/*302.5*/("""backgroundColor: 'rgba(255, 255, 255, 0)',
		  penColor: 'rgb(0, 0, 0)'
		"""),format.raw/*304.3*/("""}"""),format.raw/*304.4*/(""");
		
		let signaturePad4 = new SignaturePad(document.getElementById('signature-pad4'), """),format.raw/*306.83*/("""{"""),format.raw/*306.84*/("""
		  """),format.raw/*307.5*/("""backgroundColor: 'rgba(255, 255, 255, 0)',
		  penColor: 'rgb(0, 0, 0)'
		"""),format.raw/*309.3*/("""}"""),format.raw/*309.4*/(""");
		
		let signaturePad5 = new SignaturePad(document.getElementById('signature-pad5'), """),format.raw/*311.83*/("""{"""),format.raw/*311.84*/("""
		  """),format.raw/*312.5*/("""backgroundColor: 'rgba(255, 255, 255, 0)',
		  penColor: 'rgb(0, 0, 0)'
		"""),format.raw/*314.3*/("""}"""),format.raw/*314.4*/(""");
		
		let signaturePad6 = new SignaturePad(document.getElementById('signature-pad6'), """),format.raw/*316.83*/("""{"""),format.raw/*316.84*/("""
		  """),format.raw/*317.5*/("""backgroundColor: 'rgba(255, 255, 255, 0)',
		  penColor: 'rgb(0, 0, 0)'
		"""),format.raw/*319.3*/("""}"""),format.raw/*319.4*/(""");
	</script>
	
	<style>
		.wrapper """),format.raw/*323.12*/("""{"""),format.raw/*323.13*/("""
		  """),format.raw/*324.5*/("""position: relative;
		  width: 350px;
		  height: 90px;
		  -moz-user-select: none;
		  -webkit-user-select: none;
		  -ms-user-select: none;
		  user-select: none;
		"""),format.raw/*331.3*/("""}"""),format.raw/*331.4*/("""
		
		"""),format.raw/*333.3*/(""".signature-pad """),format.raw/*333.18*/("""{"""),format.raw/*333.19*/("""
		  """),format.raw/*334.5*/("""position: absolute;
		  left: 0;
		  top: 0;
		  width:350px;
		  height:90px;
		"""),format.raw/*339.3*/("""}"""),format.raw/*339.4*/("""
	"""),format.raw/*340.2*/("""</style>
<!-- FIN PAD -->
	
<script>
	
	$(document).ready(function() """),format.raw/*345.31*/("""{"""),format.raw/*345.32*/("""
		"""),format.raw/*346.3*/("""$('#tablaOperadores').DataTable("""),format.raw/*346.35*/("""{"""),format.raw/*346.36*/("""
	    	"""),format.raw/*347.7*/(""""fixedHeader": true,
	    	"lengthMenu": false,
	    	"paging":   false,
	        "ordering": [['1','asc']],
	        "info":     false,
			"searching": true,
	    	"language": """),format.raw/*353.19*/("""{"""),format.raw/*353.20*/("""
	        	"""),format.raw/*354.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*355.10*/("""}"""),format.raw/*355.11*/("""
	    """),format.raw/*356.6*/("""}"""),format.raw/*356.7*/(""" """),format.raw/*356.8*/(""");

		$('#tablaEquipos_oper').DataTable("""),format.raw/*358.37*/("""{"""),format.raw/*358.38*/("""
	    	"""),format.raw/*359.7*/(""""fixedHeader": true,
	    	"lengthMenu": false,
	    	"paging":   false,
	        "ordering": [['1','asc']],
	        "info":     false,
			"searching": true,
	    	"language": """),format.raw/*365.19*/("""{"""),format.raw/*365.20*/("""
	        	"""),format.raw/*366.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*367.10*/("""}"""),format.raw/*367.11*/("""
	    """),format.raw/*368.6*/("""}"""),format.raw/*368.7*/(""" """),format.raw/*368.8*/(""");
		$('#tablaEquipos_mec').DataTable("""),format.raw/*369.36*/("""{"""),format.raw/*369.37*/("""
	    	"""),format.raw/*370.7*/(""""fixedHeader": true,
	    	"lengthMenu": false,
	    	"paging":   false,
	        "ordering": [['1','asc']],
	        "info":     false,
			"searching": true,
	    	"language": """),format.raw/*376.19*/("""{"""),format.raw/*376.20*/("""
	        	"""),format.raw/*377.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*378.10*/("""}"""),format.raw/*378.11*/("""
	    """),format.raw/*379.6*/("""}"""),format.raw/*379.7*/(""" """),format.raw/*379.8*/(""");
		$('#tablaBodegas').DataTable("""),format.raw/*380.32*/("""{"""),format.raw/*380.33*/("""
	    	"""),format.raw/*381.7*/(""""fixedHeader": true,
	    	"lengthMenu": false,
	    	"paging":   false,
	        "ordering": [['1','asc']],
	        "info":     false,
			"searching": true,
	    	"language": """),format.raw/*387.19*/("""{"""),format.raw/*387.20*/("""
	        	"""),format.raw/*388.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*389.10*/("""}"""),format.raw/*389.11*/("""
	    """),format.raw/*390.6*/("""}"""),format.raw/*390.7*/(""" """),format.raw/*390.8*/(""");

		$('#tablaComponentesC').DataTable("""),format.raw/*392.37*/("""{"""),format.raw/*392.38*/("""
			"""),format.raw/*393.4*/(""""dom": '<"pull-left"f><"pull-right"l>tip',
	    	"fixedHeader": true,
	    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
	    	"paging":   true,
	        "ordering": true,
	        "info":     true,
	    	"language": """),format.raw/*399.19*/("""{"""),format.raw/*399.20*/("""
	        	"""),format.raw/*400.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*401.10*/("""}"""),format.raw/*401.11*/("""
	    """),format.raw/*402.6*/("""}"""),format.raw/*402.7*/(""" """),format.raw/*402.8*/(""");

		$('#tablaComponentesP').DataTable("""),format.raw/*404.37*/("""{"""),format.raw/*404.38*/("""
			"""),format.raw/*405.4*/(""""dom": '<"pull-left"f><"pull-right"l>tip',
	    	"fixedHeader": true,
	    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
	    	"paging":   true,
	        "ordering": true,
	        "info":     true,
	    	"language": """),format.raw/*411.19*/("""{"""),format.raw/*411.20*/("""
	        	"""),format.raw/*412.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*413.10*/("""}"""),format.raw/*413.11*/("""
	    """),format.raw/*414.6*/("""}"""),format.raw/*414.7*/(""" """),format.raw/*414.8*/(""");

 		document.getElementById('mostrarmostrar').style.display="block";
	
	
	
	"""),format.raw/*420.2*/("""}"""),format.raw/*420.3*/(""" """),format.raw/*420.4*/(""");
	
	
	const selectPerfil = (id_mantActor) => """),format.raw/*423.41*/("""{"""),format.raw/*423.42*/(""" 
		"""),format.raw/*424.3*/("""if(id_mantActor == 1)"""),format.raw/*424.24*/("""{"""),format.raw/*424.25*/("""
			"""),format.raw/*425.4*/("""$("#id_operador").val(0);
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
		"""),format.raw/*447.3*/("""}"""),format.raw/*447.4*/("""else if(id_mantActor == 2)"""),format.raw/*447.30*/("""{"""),format.raw/*447.31*/("""
			"""),format.raw/*448.4*/("""$("#id_mecanico").val(0);
			$("#valNomMecanico").val("--- select ---");
			$("#tipoMecanico").val("");
			document.querySelector("#id_tipoMantencion").value = "0";
			limpiaMecanico();
			document.getElementById('viewMecanico').style.display="block";
			document.getElementById('viewOperador').style.display="none";
		"""),format.raw/*455.3*/("""}"""),format.raw/*455.4*/("""
		
		"""),format.raw/*457.3*/("""bloqueaDisabled();
		
	"""),format.raw/*459.2*/("""}"""),format.raw/*459.3*/("""
	
	"""),format.raw/*461.2*/("""const liberaDisabled = () =>"""),format.raw/*461.30*/("""{"""),format.raw/*461.31*/("""
		"""),format.raw/*462.3*/("""$("#id_mantEstadoEnObra").attr("disabled",false);
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
	"""),format.raw/*484.2*/("""}"""),format.raw/*484.3*/("""
	
	"""),format.raw/*486.2*/("""const bloqueaDisabled = () =>"""),format.raw/*486.31*/("""{"""),format.raw/*486.32*/("""
		"""),format.raw/*487.3*/("""$("#id_mantEstadoEnObra").attr("disabled",true);
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
	"""),format.raw/*509.2*/("""}"""),format.raw/*509.3*/("""
	
	"""),format.raw/*511.2*/("""const selectTipoMantencion = (id_tipoMantencion) => """),format.raw/*511.54*/("""{"""),format.raw/*511.55*/(""" 
		"""),format.raw/*512.3*/("""if(id_tipoMantencion == 1)"""),format.raw/*512.29*/("""{"""),format.raw/*512.30*/("""
			"""),format.raw/*513.4*/("""limpiaMecanico();
			document.getElementById('viewPreventivo').style.display="block";
			document.getElementById('viewCorrectivo').style.display="none";
		"""),format.raw/*516.3*/("""}"""),format.raw/*516.4*/("""else if(id_tipoMantencion == 2)"""),format.raw/*516.35*/("""{"""),format.raw/*516.36*/("""
			"""),format.raw/*517.4*/("""limpiaMecanico();
			document.getElementById('viewCorrectivo').style.display="block";
			document.getElementById('viewPreventivo').style.display="none";
		"""),format.raw/*520.3*/("""}"""),format.raw/*520.4*/("""
		"""),format.raw/*521.3*/("""bloqueaDisabled();
	"""),format.raw/*522.2*/("""}"""),format.raw/*522.3*/("""
	
	"""),format.raw/*524.2*/("""const limpiaMecanico = () =>"""),format.raw/*524.30*/("""{"""),format.raw/*524.31*/("""
		"""),format.raw/*525.3*/("""$("#id_equipo_mec").val(0);
		$("#valNomEquip_mec").val("--- select ---");
		$("#id_tipoPlan_mec").val(0);
		$("#tipoPlan_mec").val("");
		$("#id_bodega_mec").val(0);
		$("#valNomBod_mec").val("--- select ---");
		$("#tipoBodega_mec").val("");
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
		
	"""),format.raw/*571.2*/("""}"""),format.raw/*571.3*/("""
	
	"""),format.raw/*573.2*/("""function selectOperador(id_operador, operador, tipo)"""),format.raw/*573.54*/("""{"""),format.raw/*573.55*/("""
		"""),format.raw/*574.3*/("""operador = atob(operador);
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
	"""),format.raw/*587.2*/("""}"""),format.raw/*587.3*/("""
	
	"""),format.raw/*589.2*/("""function selectMecanico(id_mecanico, mecanico, tipo)"""),format.raw/*589.54*/("""{"""),format.raw/*589.55*/("""
		"""),format.raw/*590.3*/("""mecanico = atob(mecanico);
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
	"""),format.raw/*603.2*/("""}"""),format.raw/*603.3*/("""
	
	"""),format.raw/*605.2*/("""function selectEquipo(id_equipo, codigo, equipo, tipoPlan, id_tipoPlan, unidad, estadoActual, id_unidadMantencion)"""),format.raw/*605.116*/("""{"""),format.raw/*605.117*/("""
		"""),format.raw/*606.3*/("""codigo = atob(codigo);
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
		let aux = """"),_display_(/*640.15*/mapIdEquipVsBod),format.raw/*640.30*/("""";
		aux = aux.replace(/&quot;/g, '"');
		let mapIdEquipVsBod = JSON.parse(aux);
		try"""),format.raw/*643.6*/("""{"""),format.raw/*643.7*/("""
			"""),format.raw/*644.4*/("""let aux2 = mapIdEquipVsBod[id_equipo];
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
			
		"""),format.raw/*679.3*/("""}"""),format.raw/*679.4*/("""catch(e)"""),format.raw/*679.12*/("""{"""),format.raw/*679.13*/("""
			"""),format.raw/*680.4*/("""$("#id_bodega_oper").val(0);
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
		"""),format.raw/*710.3*/("""}"""),format.raw/*710.4*/("""

		"""),format.raw/*712.3*/("""$('#idUnidadMant_oper').val(id_unidadMantencion);
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
	"""),format.raw/*737.2*/("""}"""),format.raw/*737.3*/("""
	
	"""),format.raw/*739.2*/("""function selectBodega(id_bodega, sucursal, tipo, nombre)"""),format.raw/*739.58*/("""{"""),format.raw/*739.59*/("""
		"""),format.raw/*740.3*/("""sucursal = atob(sucursal);
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
	"""),format.raw/*778.2*/("""}"""),format.raw/*778.3*/("""
	
	"""),format.raw/*780.2*/("""let lectAnterior = 0;
	
	function actualizaFechaReal() """),format.raw/*782.32*/("""{"""),format.raw/*782.33*/("""
		"""),format.raw/*783.3*/("""if(!isValidDate($('#fecha').val()))"""),format.raw/*783.38*/("""{"""),format.raw/*783.39*/("""
    		"""),format.raw/*784.7*/("""alertify.alert("Debe ingresar una fecha valida");
       		$('#fecha').val(""""),_display_(/*785.28*/fecha),format.raw/*785.33*/("""");
    	"""),format.raw/*786.6*/("""}"""),format.raw/*786.7*/("""else"""),format.raw/*786.11*/("""{"""),format.raw/*786.12*/("""
    		"""),format.raw/*787.7*/("""var fechaMas = new Date();
        	var fechaMenos = new Date();
        	var fecha = new Date($('#fecha').val());
        	fechaMas.setDate(fechaMas.getDate() + 0);
        	fechaMenos.setDate(fechaMenos.getDate() - 180);
        	if(fecha>fechaMas)"""),format.raw/*792.28*/("""{"""),format.raw/*792.29*/("""
        		"""),format.raw/*793.11*/("""alertify.alert("La fecha no puede ser posterior a la fecha actual.");
        		$('#fecha').val('"""),_display_(/*794.29*/fecha),format.raw/*794.34*/("""');
        	"""),format.raw/*795.10*/("""}"""),format.raw/*795.11*/("""else if(fecha<fechaMenos)"""),format.raw/*795.36*/("""{"""),format.raw/*795.37*/("""
           		"""),format.raw/*796.14*/("""alertify.alert("La fecha no puede tener mas de 180 d&iacute;as de antiguedad.");
           		$('#fecha').val('"""),_display_(/*797.32*/fecha),format.raw/*797.37*/("""');
        	"""),format.raw/*798.10*/("""}"""),format.raw/*798.11*/("""
    	"""),format.raw/*799.6*/("""}"""),format.raw/*799.7*/("""
    	
    """),format.raw/*801.5*/("""}"""),format.raw/*801.6*/("""


"""),format.raw/*804.1*/("""// ************************ COMPONENTES CORRECTIVO *****************************
	const showComponentesC = () => """),format.raw/*805.33*/("""{"""),format.raw/*805.34*/("""
		"""),format.raw/*806.3*/("""var tabla = document.getElementById('tablaComponentesC');
		for (var i=1; i<tabla.rows.length; i++)"""),format.raw/*807.42*/("""{"""),format.raw/*807.43*/("""
			"""),format.raw/*808.4*/("""var row = tabla.rows[i].getElementsByTagName('td');
			var classIdComponentesC = document.getElementsByClassName("classIdComponentesC");
			for(var j=0; j<classIdComponentesC.length; j++)"""),format.raw/*810.51*/("""{"""),format.raw/*810.52*/("""
				"""),format.raw/*811.5*/("""var id_componenteC = classIdComponentesC[j].value;
				if(row[0].innerHTML == id_componenteC)"""),format.raw/*812.43*/("""{"""),format.raw/*812.44*/("""
					"""),format.raw/*813.6*/("""document.getElementById("tablaComponentesC").rows[i].style.display='none';
					tabla.rows[i].style.display = 'none';
				"""),format.raw/*815.5*/("""}"""),format.raw/*815.6*/("""
			"""),format.raw/*816.4*/("""}"""),format.raw/*816.5*/("""
		"""),format.raw/*817.3*/("""}"""),format.raw/*817.4*/("""
		"""),format.raw/*818.3*/("""$('#modalComponentesC').modal('show')
  	"""),format.raw/*819.4*/("""}"""),format.raw/*819.5*/("""

    """),format.raw/*821.5*/("""const selectComponenteC = (id_componente, componente) => """),format.raw/*821.62*/("""{"""),format.raw/*821.63*/("""
		"""),format.raw/*822.3*/("""componente = atob(componente);
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
	"""),format.raw/*837.2*/("""}"""),format.raw/*837.3*/("""
	
	"""),format.raw/*839.2*/("""function agregaLineaC()"""),format.raw/*839.25*/("""{"""),format.raw/*839.26*/("""
		"""),format.raw/*840.3*/("""var tabla = document.getElementById('tblComponentesC');
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
	"""),format.raw/*865.2*/("""}"""),format.raw/*865.3*/("""
	
	"""),format.raw/*867.2*/("""function eliminarLineaC(nodo, id_componente)"""),format.raw/*867.46*/("""{"""),format.raw/*867.47*/("""
		"""),format.raw/*868.3*/("""var nodoTd = nodo.parentNode.parentNode; 
		var nodoTr = nodoTd.parentNode; 
		$(nodoTr).remove();
		var tabla = document.getElementById('tablaComponentesC');
		for (var i=1; i<tabla.rows.length; i++)"""),format.raw/*872.42*/("""{"""),format.raw/*872.43*/("""
			"""),format.raw/*873.4*/("""var row = tabla.rows[i].getElementsByTagName('td');
			if(row[0].innerHTML == id_componente)"""),format.raw/*874.41*/("""{"""),format.raw/*874.42*/("""
				"""),format.raw/*875.5*/("""tabla.rows[i].style.display="";
			"""),format.raw/*876.4*/("""}"""),format.raw/*876.5*/("""
		"""),format.raw/*877.3*/("""}"""),format.raw/*877.4*/("""
	"""),format.raw/*878.2*/("""}"""),format.raw/*878.3*/("""
"""),format.raw/*879.1*/("""// ************************ FIN CORRECTIVO *****************************

// ************************ COMPONENTES PREVENTIVO *****************************
	const showComponentesP = () => """),format.raw/*882.33*/("""{"""),format.raw/*882.34*/("""
		"""),format.raw/*883.3*/("""var tabla = document.getElementById('tablaComponentesP');
		for (var i=1; i<tabla.rows.length; i++)"""),format.raw/*884.42*/("""{"""),format.raw/*884.43*/("""
			"""),format.raw/*885.4*/("""var row = tabla.rows[i].getElementsByTagName('td');
			var classIdComponentesP = document.getElementsByClassName("classIdComponentesP");
			for(var j=0; j<classIdComponentesP.length; j++)"""),format.raw/*887.51*/("""{"""),format.raw/*887.52*/("""
				"""),format.raw/*888.5*/("""var id_componenteP = classIdComponentesP[j].value;
				if(row[0].innerHTML == id_componenteP)"""),format.raw/*889.43*/("""{"""),format.raw/*889.44*/("""
					"""),format.raw/*890.6*/("""document.getElementById("tablaComponentesP").rows[i].style.display='none';
					tabla.rows[i].style.display = 'none';
				"""),format.raw/*892.5*/("""}"""),format.raw/*892.6*/("""
			"""),format.raw/*893.4*/("""}"""),format.raw/*893.5*/("""
		"""),format.raw/*894.3*/("""}"""),format.raw/*894.4*/("""
		"""),format.raw/*895.3*/("""$('#modalComponentesP').modal('show')
  	"""),format.raw/*896.4*/("""}"""),format.raw/*896.5*/("""

    """),format.raw/*898.5*/("""const selectComponenteP = (id_componente, componente) => """),format.raw/*898.62*/("""{"""),format.raw/*898.63*/("""
		"""),format.raw/*899.3*/("""componente = atob(componente);
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
	"""),format.raw/*914.2*/("""}"""),format.raw/*914.3*/("""
	
	"""),format.raw/*916.2*/("""function agregaLineaP()"""),format.raw/*916.25*/("""{"""),format.raw/*916.26*/("""
		"""),format.raw/*917.3*/("""var tabla = document.getElementById('tblComponentesP');
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
	"""),format.raw/*942.2*/("""}"""),format.raw/*942.3*/("""
	
	"""),format.raw/*944.2*/("""function eliminarLineaP(nodo, id_componente)"""),format.raw/*944.46*/("""{"""),format.raw/*944.47*/("""
		"""),format.raw/*945.3*/("""var nodoTd = nodo.parentNode.parentNode; 
		var nodoTr = nodoTd.parentNode; 
		$(nodoTr).remove();
		var tabla = document.getElementById('tablaComponentesP');
		for (var i=1; i<tabla.rows.length; i++)"""),format.raw/*949.42*/("""{"""),format.raw/*949.43*/("""
			"""),format.raw/*950.4*/("""var row = tabla.rows[i].getElementsByTagName('td');
			if(row[0].innerHTML == id_componente)"""),format.raw/*951.41*/("""{"""),format.raw/*951.42*/("""
				"""),format.raw/*952.5*/("""tabla.rows[i].style.display="";
			"""),format.raw/*953.4*/("""}"""),format.raw/*953.5*/("""
		"""),format.raw/*954.3*/("""}"""),format.raw/*954.4*/("""
	"""),format.raw/*955.2*/("""}"""),format.raw/*955.3*/("""
"""),format.raw/*956.1*/("""// ************************ FIN CORRECTIVO *****************************
	
	let extArray = new Array(".gif", ".jpg", ".png", ".jpeg", ".pdf", ".xls", ".doc", ".xlsx", ".docx", ".tar", ".zip", ".rar");
	const LimitAttach = (form, file) => """),format.raw/*959.38*/("""{"""),format.raw/*959.39*/("""
		
		"""),format.raw/*961.3*/("""if (!file) return;
		
		const $inputArchivos = document.querySelector("#docAdjunto");
		const archivosParaSubir = $inputArchivos.files;
		let tamanio = 0;
		let allowSubmit = false;
		
		for(let i=0; i<archivosParaSubir.length; i++)"""),format.raw/*968.48*/("""{"""),format.raw/*968.49*/("""
			"""),format.raw/*969.4*/("""tamanio += archivosParaSubir[i].size;
			let nombre = archivosParaSubir[i].name;
			let extencion = nombre.substring(nombre.lastIndexOf(".")).toLowerCase();
			for (let j = 0; j < extArray.length; j++) """),format.raw/*972.46*/("""{"""),format.raw/*972.47*/("""
				"""),format.raw/*973.5*/("""if (extArray[j] == extencion) """),format.raw/*973.35*/("""{"""),format.raw/*973.36*/(""" 
					"""),format.raw/*974.6*/("""allowSubmit = true;
				"""),format.raw/*975.5*/("""}"""),format.raw/*975.6*/("""
			"""),format.raw/*976.4*/("""}"""),format.raw/*976.5*/("""
			"""),format.raw/*977.4*/("""if(allowSubmit && i < archivosParaSubir.length -1)"""),format.raw/*977.54*/("""{"""),format.raw/*977.55*/("""
				"""),format.raw/*978.5*/("""allowSubmit = false;
			"""),format.raw/*979.4*/("""}"""),format.raw/*979.5*/("""
		"""),format.raw/*980.3*/("""}"""),format.raw/*980.4*/("""
		
		"""),format.raw/*982.3*/("""if (allowSubmit) """),format.raw/*982.20*/("""{"""),format.raw/*982.21*/("""
			"""),format.raw/*983.4*/("""if(tamanio > 200000000)"""),format.raw/*983.27*/("""{"""),format.raw/*983.28*/("""
				"""),format.raw/*984.5*/("""alert("Se permite máximo subir 200 MB,"
				+" lo que se intenta subir pesa: "+Math.round(tamanio/10000)/100+" MB");
				form.docAdjunto.value="";
			"""),format.raw/*987.4*/("""}"""),format.raw/*987.5*/("""else"""),format.raw/*987.9*/("""{"""),format.raw/*987.10*/("""
				"""),format.raw/*988.5*/("""alert("Documento subido con éxito");
				$("#txtBtn").text("Cambiar Documento");
			"""),format.raw/*990.4*/("""}"""),format.raw/*990.5*/("""
		"""),format.raw/*991.3*/("""}"""),format.raw/*991.4*/("""else"""),format.raw/*991.8*/("""{"""),format.raw/*991.9*/("""
			"""),format.raw/*992.4*/("""alert("Se permiten únicamente archivos con la extención: " 
			+ (extArray.join("  ")) + "\nPor favor, seleccione solo archivos con extenciones permitidas "
			+ "e intente de nuevo.");
			form.docAdjunto.value="";
		"""),format.raw/*996.3*/("""}"""),format.raw/*996.4*/("""
	"""),format.raw/*997.2*/("""}"""),format.raw/*997.3*/("""
		
"""),format.raw/*999.1*/("""</script>
	

"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,fecha:String,listActor:List[tables.MantActorPersonal],listOperMec:List[tables.MantOperadorMecanico],listPlanMant:List[tables.PlanMantencion],mapIdEquipVsBod:String,listBod:List[tables.BodegaEmpresa],listEstaObra:List[tables.MantEstadoEnObra],listEquipos:List[List[String]],listTipoMantencion:List[tables.TipoMantencion],listEstadoEnTaller:List[tables.MantEstadoEnTaller],listActividad:List[tables.MantActividad],listTipoActividad:List[tables.MantTipoActividad],listComponentes:List[tables.MantComponente],listItemIntervenido:List[tables.MantItemIntervenido]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,fecha,listActor,listOperMec,listPlanMant,mapIdEquipVsBod,listBod,listEstaObra,listEquipos,listTipoMantencion,listEstadoEnTaller,listActividad,listTipoActividad,listComponentes,listItemIntervenido)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,String,List[tables.MantActorPersonal],List[tables.MantOperadorMecanico],List[tables.PlanMantencion],String,List[tables.BodegaEmpresa],List[tables.MantEstadoEnObra],List[List[String]],List[tables.TipoMantencion],List[tables.MantEstadoEnTaller],List[tables.MantActividad],List[tables.MantTipoActividad],List[tables.MantComponente],List[tables.MantItemIntervenido]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,fecha,listActor,listOperMec,listPlanMant,mapIdEquipVsBod,listBod,listEstaObra,listEquipos,listTipoMantencion,listEstadoEnTaller,listActividad,listTipoActividad,listComponentes,listItemIntervenido) => apply(mapDiccionario,mapPermiso,userMnu,fecha,listActor,listOperMec,listPlanMant,mapIdEquipVsBod,listBod,listEstaObra,listEquipos,listTipoMantencion,listEstadoEnTaller,listActividad,listTipoActividad,listComponentes,listItemIntervenido)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuMantencion/mantReportNew.scala.html
                  HASH: 4dccf1ef1617f00076f19553bb59da162b72dc57
                  MATRIX: 1378->1|2155->685|2185->689|2202->697|2242->699|2271->702|2340->750|2369->752|2446->803|2537->873|2570->879|3233->1515|3259->1520|4048->2282|4064->2289|4102->2305|4249->2425|4265->2432|4300->2446|4896->3015|4935->3038|4974->3039|5027->3064|5070->3080|5084->3085|5108->3088|5138->3091|5152->3096|5194->3117|5247->3139|5291->3155|5619->3457|5711->3528|5744->3534|5835->3599|6043->3786|6076->3792|7068->4757|7111->4783|7151->4784|7188->4793|7255->4832|7270->4837|7307->4852|7356->4873|7371->4878|7411->4896|7461->4918|7476->4923|7516->4941|7565->4962|7580->4967|7622->4987|7666->5003|7681->5008|7720->5025|7763->5040|7778->5045|7822->5067|7865->5082|7880->5087|7920->5105|7963->5120|7978->5125|8025->5150|8108->5205|8123->5210|8162->5227|8251->5288|8266->5293|8306->5311|8394->5371|8409->5376|8449->5394|8537->5454|8552->5459|8594->5479|8657->5511|8692->5518|9792->6591|9834->6616|9874->6617|9911->6626|9978->6665|9993->6670|10022->6677|10070->6697|10085->6702|10114->6709|10163->6730|10178->6735|10207->6742|10295->6802|10310->6807|10339->6814|10382->6829|10397->6834|10426->6841|10469->6856|10484->6861|10513->6868|10596->6923|10611->6928|10640->6935|10729->6996|10744->7001|10773->7008|10861->7068|10876->7073|10905->7080|10968->7112|11003->7119|11580->7668|11626->7692|12118->8156|12164->8180|12256->8245|12294->8266|12334->8267|12371->8276|12427->8304|12442->8309|12472->8317|12510->8327|12525->8332|12565->8350|12604->8361|12619->8366|12664->8388|12705->8400|12721->8405|12756->8417|12841->8474|12856->8479|12896->8497|12985->8558|13000->8563|13044->8585|13132->8645|13147->8650|13181->8662|13257->8707|13292->8714|14446->9840|14492->9869|14532->9870|14571->9880|14632->9913|14647->9918|14677->9926|14714->9935|14729->9940|14763->9952|14833->9994|14848->9999|14878->10007|14939->10040|14954->10045|14988->10057|15054->10091|15090->10099|16251->11232|16297->11261|16337->11262|16376->11272|16437->11305|16452->11310|16482->11318|16519->11327|16534->11332|16568->11344|16638->11386|16653->11391|16683->11399|16744->11432|16759->11437|16793->11449|16859->11483|16895->11491|17207->11772|17238->11775|17304->11813|17320->11819|17395->11872|17526->11974|17556->11975|17589->11980|17691->12054|17720->12055|17837->12143|17867->12144|17900->12149|18002->12223|18031->12224|18145->12309|18175->12310|18208->12315|18310->12389|18339->12390|18456->12478|18486->12479|18519->12484|18621->12558|18650->12559|18767->12647|18797->12648|18830->12653|18932->12727|18961->12728|19078->12816|19108->12817|19141->12822|19243->12896|19272->12897|19337->12933|19367->12934|19400->12939|19595->13106|19624->13107|19658->13113|19702->13128|19732->13129|19765->13134|19874->13215|19903->13216|19933->13218|20031->13287|20061->13288|20092->13291|20153->13323|20183->13324|20218->13331|20424->13508|20454->13509|20494->13520|20600->13597|20630->13598|20664->13604|20693->13605|20722->13606|20791->13646|20821->13647|20856->13654|21062->13831|21092->13832|21132->13843|21238->13920|21268->13921|21302->13927|21331->13928|21360->13929|21427->13967|21457->13968|21492->13975|21698->14152|21728->14153|21768->14164|21874->14241|21904->14242|21938->14248|21967->14249|21996->14250|22059->14284|22089->14285|22124->14292|22330->14469|22360->14470|22400->14481|22506->14558|22536->14559|22570->14565|22599->14566|22628->14567|22697->14607|22727->14608|22759->14612|23027->14851|23057->14852|23097->14863|23203->14940|23233->14941|23267->14947|23296->14948|23325->14949|23394->14989|23424->14990|23456->14994|23724->15233|23754->15234|23794->15245|23900->15322|23930->15323|23964->15329|23993->15330|24022->15331|24129->15410|24158->15411|24187->15412|24263->15459|24293->15460|24325->15464|24375->15485|24405->15486|24437->15490|25384->16409|25413->16410|25468->16436|25498->16437|25530->16441|25877->16760|25906->16761|25940->16767|25991->16790|26020->16791|26052->16795|26109->16823|26139->16824|26170->16827|27220->17849|27249->17850|27281->17854|27339->17883|27369->17884|27400->17887|28430->18889|28459->18890|28491->18894|28572->18946|28602->18947|28634->18951|28689->18977|28719->18978|28751->18982|28934->19137|28963->19138|29023->19169|29053->19170|29085->19174|29268->19329|29297->19330|29328->19333|29376->19353|29405->19354|29437->19358|29494->19386|29524->19387|29555->19390|31350->21157|31379->21158|31411->21162|31492->21214|31522->21215|31553->21218|32189->21826|32218->21827|32250->21831|32331->21883|32361->21884|32392->21887|33024->22491|33053->22492|33085->22496|33229->22610|33260->22611|33291->22614|35072->24367|35109->24382|35223->24468|35252->24469|35284->24473|36974->26135|37003->26136|37040->26144|37070->26145|37102->26149|38665->27684|38694->27685|38726->27689|39725->28660|39754->28661|39786->28665|39871->28721|39901->28722|39932->28725|41743->30508|41772->30509|41804->30513|41888->30568|41918->30569|41949->30572|42013->30607|42043->30608|42078->30615|42183->30692|42210->30697|42247->30706|42276->30707|42309->30711|42339->30712|42374->30719|42653->30969|42683->30970|42723->30981|42849->31079|42876->31084|42918->31097|42948->31098|43002->31123|43032->31124|43075->31138|43215->31250|43242->31255|43284->31268|43314->31269|43348->31275|43377->31276|43416->31287|43445->31288|43476->31291|43618->31404|43648->31405|43679->31408|43807->31507|43837->31508|43869->31512|44085->31699|44115->31700|44148->31705|44270->31798|44300->31799|44334->31805|44484->31927|44513->31928|44545->31932|44574->31933|44605->31936|44634->31937|44665->31940|44734->31981|44763->31982|44797->31988|44883->32045|44913->32046|44944->32049|46306->33383|46335->33384|46367->33388|46419->33411|46449->33412|46480->33415|47671->34578|47700->34579|47732->34583|47805->34627|47835->34628|47866->34631|48095->34831|48125->34832|48157->34836|48278->34928|48308->34929|48341->34934|48404->34969|48433->34970|48464->34973|48493->34974|48523->34976|48552->34977|48581->34978|48797->35165|48827->35166|48858->35169|48986->35268|49016->35269|49048->35273|49264->35460|49294->35461|49327->35466|49449->35559|49479->35560|49513->35566|49663->35688|49692->35689|49724->35693|49753->35694|49784->35697|49813->35698|49844->35701|49913->35742|49942->35743|49976->35749|50062->35806|50092->35807|50123->35810|51485->37144|51514->37145|51546->37149|51598->37172|51628->37173|51659->37176|52850->38339|52879->38340|52911->38344|52984->38388|53014->38389|53045->38392|53274->38592|53304->38593|53336->38597|53457->38689|53487->38690|53520->38695|53583->38730|53612->38731|53643->38734|53672->38735|53702->38737|53731->38738|53760->38739|54027->38977|54057->38978|54091->38984|54352->39216|54382->39217|54414->39221|54645->39423|54675->39424|54708->39429|54767->39459|54797->39460|54832->39467|54884->39491|54913->39492|54945->39496|54974->39497|55006->39501|55085->39551|55115->39552|55148->39557|55200->39581|55229->39582|55260->39585|55289->39586|55323->39592|55369->39609|55399->39610|55431->39614|55483->39637|55513->39638|55546->39643|55724->39793|55753->39794|55785->39798|55815->39799|55848->39804|55960->39888|55989->39889|56020->39892|56049->39893|56081->39897|56110->39898|56142->39902|56387->40119|56416->40120|56446->40122|56475->40123|56507->40127
                  LINES: 28->1|39->8|42->11|42->11|42->11|44->13|44->13|45->14|46->15|46->15|48->17|61->30|61->30|76->45|76->45|76->45|78->47|78->47|78->47|92->61|92->61|92->61|93->62|93->62|93->62|93->62|93->62|93->62|93->62|94->63|95->64|105->74|105->74|106->75|108->77|109->78|110->79|137->106|137->106|137->106|138->107|139->108|139->108|139->108|140->109|140->109|140->109|141->110|141->110|141->110|142->111|142->111|142->111|143->112|143->112|143->112|144->113|144->113|144->113|145->114|145->114|145->114|146->115|146->115|146->115|147->116|147->116|147->116|148->117|148->117|148->117|149->118|149->118|149->118|150->119|150->119|150->119|152->121|153->122|182->151|182->151|182->151|183->152|184->153|184->153|184->153|185->154|185->154|185->154|186->155|186->155|186->155|189->158|189->158|189->158|190->159|190->159|190->159|191->160|191->160|191->160|192->161|192->161|192->161|193->162|193->162|193->162|194->163|194->163|194->163|196->165|197->166|211->180|211->180|222->191|222->191|226->195|226->195|226->195|227->196|227->196|227->196|227->196|227->196|227->196|227->196|227->196|227->196|227->196|227->196|227->196|227->196|228->197|228->197|228->197|229->198|229->198|229->198|230->199|230->199|230->199|232->201|233->202|262->231|262->231|262->231|263->232|263->232|263->232|263->232|263->232|263->232|263->232|264->233|264->233|264->233|265->234|265->234|265->234|267->236|268->237|297->266|297->266|297->266|298->267|298->267|298->267|298->267|298->267|298->267|298->267|299->268|299->268|299->268|300->269|300->269|300->269|302->271|303->272|316->285|319->288|321->290|321->290|321->290|323->292|323->292|324->293|326->295|326->295|328->297|328->297|329->298|331->300|331->300|332->301|332->301|333->302|335->304|335->304|337->306|337->306|338->307|340->309|340->309|342->311|342->311|343->312|345->314|345->314|347->316|347->316|348->317|350->319|350->319|354->323|354->323|355->324|362->331|362->331|364->333|364->333|364->333|365->334|370->339|370->339|371->340|376->345|376->345|377->346|377->346|377->346|378->347|384->353|384->353|385->354|386->355|386->355|387->356|387->356|387->356|389->358|389->358|390->359|396->365|396->365|397->366|398->367|398->367|399->368|399->368|399->368|400->369|400->369|401->370|407->376|407->376|408->377|409->378|409->378|410->379|410->379|410->379|411->380|411->380|412->381|418->387|418->387|419->388|420->389|420->389|421->390|421->390|421->390|423->392|423->392|424->393|430->399|430->399|431->400|432->401|432->401|433->402|433->402|433->402|435->404|435->404|436->405|442->411|442->411|443->412|444->413|444->413|445->414|445->414|445->414|451->420|451->420|451->420|454->423|454->423|455->424|455->424|455->424|456->425|478->447|478->447|478->447|478->447|479->448|486->455|486->455|488->457|490->459|490->459|492->461|492->461|492->461|493->462|515->484|515->484|517->486|517->486|517->486|518->487|540->509|540->509|542->511|542->511|542->511|543->512|543->512|543->512|544->513|547->516|547->516|547->516|547->516|548->517|551->520|551->520|552->521|553->522|553->522|555->524|555->524|555->524|556->525|602->571|602->571|604->573|604->573|604->573|605->574|618->587|618->587|620->589|620->589|620->589|621->590|634->603|634->603|636->605|636->605|636->605|637->606|671->640|671->640|674->643|674->643|675->644|710->679|710->679|710->679|710->679|711->680|741->710|741->710|743->712|768->737|768->737|770->739|770->739|770->739|771->740|809->778|809->778|811->780|813->782|813->782|814->783|814->783|814->783|815->784|816->785|816->785|817->786|817->786|817->786|817->786|818->787|823->792|823->792|824->793|825->794|825->794|826->795|826->795|826->795|826->795|827->796|828->797|828->797|829->798|829->798|830->799|830->799|832->801|832->801|835->804|836->805|836->805|837->806|838->807|838->807|839->808|841->810|841->810|842->811|843->812|843->812|844->813|846->815|846->815|847->816|847->816|848->817|848->817|849->818|850->819|850->819|852->821|852->821|852->821|853->822|868->837|868->837|870->839|870->839|870->839|871->840|896->865|896->865|898->867|898->867|898->867|899->868|903->872|903->872|904->873|905->874|905->874|906->875|907->876|907->876|908->877|908->877|909->878|909->878|910->879|913->882|913->882|914->883|915->884|915->884|916->885|918->887|918->887|919->888|920->889|920->889|921->890|923->892|923->892|924->893|924->893|925->894|925->894|926->895|927->896|927->896|929->898|929->898|929->898|930->899|945->914|945->914|947->916|947->916|947->916|948->917|973->942|973->942|975->944|975->944|975->944|976->945|980->949|980->949|981->950|982->951|982->951|983->952|984->953|984->953|985->954|985->954|986->955|986->955|987->956|990->959|990->959|992->961|999->968|999->968|1000->969|1003->972|1003->972|1004->973|1004->973|1004->973|1005->974|1006->975|1006->975|1007->976|1007->976|1008->977|1008->977|1008->977|1009->978|1010->979|1010->979|1011->980|1011->980|1013->982|1013->982|1013->982|1014->983|1014->983|1014->983|1015->984|1018->987|1018->987|1018->987|1018->987|1019->988|1021->990|1021->990|1022->991|1022->991|1022->991|1022->991|1023->992|1027->996|1027->996|1028->997|1028->997|1030->999
                  -- GENERATED --
              */
          