
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

object mantHistorialReports0 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template10[Map[String,String],Map[String,String],utilities.UserMnu,String,String,List[tables.MantActorPersonal],List[tables.MantOperadorMecanico],List[tables.TipoMantencion],List[List[String]],List[tables.PlanMantencion],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
fechaDesde: String, fechaHasta: String,
listActor: List[tables.MantActorPersonal], listOperMec: List[tables.MantOperadorMecanico], 
listTipoMantencion: List[tables.TipoMantencion], listEquipos: List[List[String]],
listPlanMant: List[tables.PlanMantencion]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*6.1*/("""
"""),_display_(/*7.2*/main("")/*7.10*/ {_display_(Seq[Any](format.raw/*7.12*/("""

	"""),_display_(/*9.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*9.51*/("""
	"""),format.raw/*10.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*11.4*/barraTitulo(mapDiccionario, "HISTORIAL DE REPORT INGRESADOS","")),format.raw/*11.68*/("""
		"""),format.raw/*12.3*/("""<form action="/routes3/mantHistorialReports1/" method="POST" onsubmit="return validarForm();">
			<br><br><br>
			<div class="row  justify-content-md-center">
				<div class="col-xs-11 col-sm-11 col-md-6 col-lg-6">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<TR>
								<TH colspan="2">PERIODO A CONSIDERAR</TH>
							</TR>
							<TR>
								<TH>FECHA DESDE:</TH>
								<td>
									<input type="date" class="form-control center"
										id="fechaDesde"
							  			name="fechaDesde"
							  			value=""""),_display_(/*27.21*/fechaDesde),format.raw/*27.31*/(""""
					        			required>
								</td>
							</TR>
							<TR>
								<TH>FECHA HASTA:</TH>
								<td>
									<input type="date" class="form-control center"
										id="fechaHasta"
							  			name="fechaHasta"
							  			value=""""),_display_(/*37.21*/fechaHasta),format.raw/*37.31*/(""""
					        			required>
								</td>
							</TR>
							<tr>
								<td style="text-align:left;"><strong>PERFIL:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
						        	<select class="form-control " 
										id="id_mantActorPersonal" 
										name="id_mantActorPersonal" 
										onchange="selectPerfil(value)" 
										required>
							        		<option class="blank" value="0">-- todos --</option>
						            		"""),_display_(/*50.22*/for(lista <- listActor) yield /*50.45*/{_display_(Seq[Any](format.raw/*50.46*/("""
						                		"""),format.raw/*51.25*/("""<option value=""""),_display_(/*51.41*/lista/*51.46*/.id),format.raw/*51.49*/("""">"""),_display_(/*51.52*/lista/*51.57*/.nombre.toUpperCase()),format.raw/*51.78*/("""</option>
											""")))}),format.raw/*52.13*/("""
							        """),format.raw/*53.16*/("""</select>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>OPERADOR/MECANICO:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
						        	<input type="hidden" id="id_operMec" name="id_operMec" value="0" required>
						        	<div id="nombreOperMec">
						        		<div class="input-group">
										  <input class="form-control" type="text" 
											onclick="listaOperMec()"
											value="--- todos ---" readonly>
										  <div class="input-group-append">
										    <span class="input-group-text" onclick="listaOperMec()">Buscar</span>
										  </div>
										</div> 
				    				</div>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>TIPO DE MANTENCION (*):</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<div id="id_tipoMantencion">
							        	<select class="form-control " 
											id="aux_id_tipoMantencion"
											name="id_tipoMantencion"
											onchange="selectTipoMantencion()"
											required>
								        		<option class="blank" value="0">-- todos --</option>
							            		"""),_display_(/*82.23*/for(lista <- listTipoMantencion) yield /*82.55*/{_display_(Seq[Any](format.raw/*82.56*/("""
							                		"""),format.raw/*83.26*/("""<option value=""""),_display_(/*83.42*/lista/*83.47*/.id),format.raw/*83.50*/("""" >"""),_display_(/*83.54*/lista/*83.59*/.nombre.toUpperCase()),format.raw/*83.80*/("""</option>
												""")))}),format.raw/*84.14*/("""
								        """),format.raw/*85.17*/("""</select>
									</div>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>EQUIPO:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<div id="auxModalListaEquipos">
							        	<input type="hidden" id="id_equipo" name="id_equipo" value="0">
							        	<div id="nombreEquipo">
							        		<div class="input-group ">
											  <input class="form-control" type="text" 
												onclick="listaEquipo();" 
												id="valNomEquip"
												value="--- todos ---" readonly>
											  <div class="input-group-append">
											    <span class="input-group-text" onclick="listaEquipo();">Buscar</span>
											  </div>
											</div> 
					    				</div>
									</div>
						        </td>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
			<div class="row  justify-content-md-center">
				<div class="col-xs-4 col-sm-2 col-md-2 col-lg-2">
					<input type="submit" value='Generar Reporte' class="btn btn-primary btn-sm rounded-pill btn-block">
				</div>
			</div>
		</form>
		
		
		
		
		<div id="modalListaALL" class="modal" role="dialog" data-backdrop="static" data-keyboard="false">
			<div class="modal-dialog modal-lg modal-dialog-scrollable" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title">Seleccionar operador</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body">
					<table id="tablaOperMec" class="table table-sm table-bordered table-condensed table-hover table-fluid">
				        <thead style="background-color: #eeeeee">
							<TR> 
								<th>RUT</th>
								<th>Operador/Mecanico</th>
								<th>Origen/Tipo</th>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*143.9*/for(lista <- listOperMec) yield /*143.34*/{_display_(Seq[Any](format.raw/*143.35*/("""
								"""),format.raw/*144.9*/("""<TR onclick="selectOperMec('"""),_display_(/*144.38*/lista/*144.43*/.getId()),format.raw/*144.51*/("""', btoa('"""),_display_(/*144.61*/lista/*144.66*/.getNombre()),format.raw/*144.78*/("""'), btoa('"""),_display_(/*144.89*/lista/*144.94*/.getNameTipoPersonal()),format.raw/*144.116*/("""') )">
									<td style="text-align:left"><a href="#">"""),_display_(/*145.51*/lista/*145.56*/.getRut()),format.raw/*145.65*/("""</a></td>	
									<td style="text-align:left"><a href="#">"""),_display_(/*146.51*/lista/*146.56*/.getNombre()),format.raw/*146.68*/("""</a></td>
									<td style="text-align:left"><a href="#">"""),_display_(/*147.51*/lista/*147.56*/.getNameTipoPersonal()),format.raw/*147.78*/("""</a></td>
								</TR>
							""")))}),format.raw/*149.9*/("""
						"""),format.raw/*150.7*/("""</tbody>
					</table>
			        <br>
					<div align="center">
			           	<button type="button" class="btn btn-sm btn-success" data-dismiss="modal" style="font-size: 10px;">CERRAR</button>
				    </div>
			      </div>
			    </div>
			 </div>
		</div>
	
		<div id="modalListaOperadores" class="modal" role="dialog" data-backdrop="static" data-keyboard="false">
			<div class="modal-dialog modal-lg modal-dialog-scrollable" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title">Seleccionar operador</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body">
					<table id="tablaOperadores" class="table table-sm table-bordered table-condensed table-hover table-fluid">
				        <thead style="background-color: #eeeeee">
							<TR> 
								<th>RUT</th>
								<th>Operador</th>
								<th>Origen/Tipo</th>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*180.9*/for(lista <- listOperMec) yield /*180.34*/{_display_(Seq[Any](format.raw/*180.35*/("""
								"""),_display_(if(lista.getId_mantActorPersonal() == 1)/*181.50*/{_display_(Seq[Any](format.raw/*181.51*/("""
									"""),format.raw/*182.10*/("""<TR onclick="selectOperMec('"""),_display_(/*182.39*/lista/*182.44*/.getId()),format.raw/*182.52*/("""', btoa('"""),_display_(/*182.62*/lista/*182.67*/.getNombre()),format.raw/*182.79*/("""'), btoa('"""),_display_(/*182.90*/lista/*182.95*/.getNameTipoPersonal()),format.raw/*182.117*/("""') )">
										<td style="text-align:left"><a href="#">"""),_display_(/*183.52*/lista/*183.57*/.getRut()),format.raw/*183.66*/("""</a></td>	
										<td style="text-align:left"><a href="#">"""),_display_(/*184.52*/lista/*184.57*/.getNombre()),format.raw/*184.69*/("""</a></td>
										<td style="text-align:left"><a href="#">"""),_display_(/*185.52*/lista/*185.57*/.getNameTipoPersonal()),format.raw/*185.79*/("""</a></td>
									</TR>
								""")))} else {null} ),format.raw/*187.10*/("""
								
							""")))}),format.raw/*189.9*/("""
						"""),format.raw/*190.7*/("""</tbody>
					</table>
			        <br>
					<div align="center">
			           	<button type="button" class="btn btn-sm btn-success" data-dismiss="modal" style="font-size: 10px;">CERRAR</button>
				    </div>
			      </div>
			    </div>
			 </div>
		</div>
	
		<div id="modalListaMecanicos" class="modal" role="dialog" data-backdrop="static" data-keyboard="false">
			<div class="modal-dialog modal-lg modal-dialog-scrollable" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title">Seleccionar mecanico</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body">
					<table id="tablaMecanicos" class="table table-sm table-bordered table-condensed table-hover table-fluid">
				        <thead style="background-color: #eeeeee">
							<TR> 
								<th>RUT</th>
								<th>Mecanico</th>
								<th>Origen/Tipo</th>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*220.9*/for(lista <- listOperMec) yield /*220.34*/{_display_(Seq[Any](format.raw/*220.35*/("""
								"""),_display_(if(lista.getId_mantActorPersonal() == 2)/*221.50*/{_display_(Seq[Any](format.raw/*221.51*/("""
									"""),format.raw/*222.10*/("""<TR onclick="selectOperMec('"""),_display_(/*222.39*/lista/*222.44*/.getId()),format.raw/*222.52*/("""', btoa('"""),_display_(/*222.62*/lista/*222.67*/.getNombre()),format.raw/*222.79*/("""'), btoa('"""),_display_(/*222.90*/lista/*222.95*/.getNameTipoPersonal()),format.raw/*222.117*/("""') )">
										<td style="text-align:left"><a href="#">"""),_display_(/*223.52*/lista/*223.57*/.getRut()),format.raw/*223.66*/("""</a></td>	
										<td style="text-align:left"><a href="#">"""),_display_(/*224.52*/lista/*224.57*/.getNombre()),format.raw/*224.69*/("""</a></td>
										<td style="text-align:left"><a href="#">"""),_display_(/*225.52*/lista/*225.57*/.getNameTipoPersonal()),format.raw/*225.79*/("""</a></td>
									</TR>
								""")))} else {null} ),format.raw/*227.10*/("""
							""")))}),format.raw/*228.9*/("""
						"""),format.raw/*229.7*/("""</tbody>
					</table>
			        <br>
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
							"""),_display_(/*259.9*/for(lista <- listEquipos) yield /*259.34*/{_display_(Seq[Any](format.raw/*259.35*/("""
								"""),format.raw/*260.9*/("""<TR onclick="selectEquipo(
										'"""),_display_(/*261.13*/lista/*261.18*/.get(0)),format.raw/*261.25*/("""', 
										btoa('"""),_display_(/*262.18*/lista/*262.23*/.get(2)),format.raw/*262.30*/("""'), 
										btoa('"""),_display_(/*263.18*/lista/*263.23*/.get(3)),format.raw/*263.30*/("""'),
										0)">
									<td style="text-align:left"><a href="#">"""),_display_(/*265.51*/lista/*265.56*/.get(1)),format.raw/*265.63*/("""</a></td>	
									<td style="text-align:left"><a href="#">"""),_display_(/*266.51*/lista/*266.56*/.get(2)),format.raw/*266.63*/("""</a></td>
									<td style="text-align:left"><a href="#">"""),_display_(/*267.51*/lista/*267.56*/.get(3)),format.raw/*267.63*/("""</a></td>
								</TR>
							""")))}),format.raw/*269.9*/("""
						"""),format.raw/*270.7*/("""</tbody>
					</table>
					<div align="center">
			           	<button type="button" class="btn btn-sm btn-success" data-dismiss="modal" style="font-size: 10px;">CERRAR</button>
				    </div>
			      </div>
			    </div>
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
							"""),_display_(/*300.9*/for(lista <- listPlanMant) yield /*300.35*/{_display_(Seq[Any](format.raw/*300.36*/("""
								"""),format.raw/*301.9*/("""<TR onclick="selectEquipo(
										'"""),_display_(/*302.13*/lista/*302.18*/.getId_equipo()),format.raw/*302.33*/("""', 
										btoa('"""),_display_(/*303.18*/lista/*303.23*/.getEquipoCodigo()),format.raw/*303.41*/("""'), 
										btoa('"""),_display_(/*304.18*/lista/*304.23*/.getEquipoNombre()),format.raw/*304.41*/("""'),
										btoa('"""),_display_(/*305.18*/lista/*305.23*/.getTipoPlanNombre()),format.raw/*305.43*/("""'))">
									<td style="text-align:left"><a href="#">"""),_display_(/*306.51*/lista/*306.56*/.getEquipoGrupo()),format.raw/*306.73*/("""</a></td>	
									<td style="text-align:left"><a href="#">"""),_display_(/*307.51*/lista/*307.56*/.getEquipoCodigo()),format.raw/*307.74*/("""</a></td>
									<td style="text-align:left"><a href="#">"""),_display_(/*308.51*/lista/*308.56*/.getEquipoNombre()),format.raw/*308.74*/("""</a></td>
									<td style="text-align:left"><a href="#">"""),_display_(/*309.51*/lista/*309.56*/.getTipoPlanNombre()),format.raw/*309.76*/("""</a></td>
								</TR>
							""")))}),format.raw/*311.9*/("""
						"""),format.raw/*312.7*/("""</tbody>
					</table>
					<div align="center">
			           	<button type="button" class="btn btn-sm btn-success" data-dismiss="modal" style="font-size: 10px;">CERRAR</button>
				    </div>
			      </div>
			    </div>
			 </div>
		</div>
	
	</div>

""")))}),format.raw/*324.2*/("""


"""),format.raw/*327.1*/("""<script type="text/javascript">
	let auxId_tipoMantencion = "";
	let auxModalListaEquipos = "";
	$(document).ready(function() """),format.raw/*330.31*/("""{"""),format.raw/*330.32*/("""
		
		"""),format.raw/*332.3*/("""$('#tablaOperMec').DataTable("""),format.raw/*332.32*/("""{"""),format.raw/*332.33*/("""
	    	"""),format.raw/*333.7*/(""""fixedHeader": true,
	    	"lengthMenu": false,
	    	"paging":   false,
	        "ordering": [['1','asc']],
	        "info":     false,
			"searching": true,
	    	"language": """),format.raw/*339.19*/("""{"""),format.raw/*339.20*/("""
	        	"""),format.raw/*340.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*341.10*/("""}"""),format.raw/*341.11*/("""
	    """),format.raw/*342.6*/("""}"""),format.raw/*342.7*/(""" """),format.raw/*342.8*/(""");
		$('#tablaOperadores').DataTable("""),format.raw/*343.35*/("""{"""),format.raw/*343.36*/("""
	    	"""),format.raw/*344.7*/(""""fixedHeader": true,
	    	"lengthMenu": false,
	    	"paging":   false,
	        "ordering": [['1','asc']],
	        "info":     false,
			"searching": true,
	    	"language": """),format.raw/*350.19*/("""{"""),format.raw/*350.20*/("""
	        	"""),format.raw/*351.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*352.10*/("""}"""),format.raw/*352.11*/("""
	    """),format.raw/*353.6*/("""}"""),format.raw/*353.7*/(""" """),format.raw/*353.8*/(""");
		$('#tablaMecanicos').DataTable("""),format.raw/*354.34*/("""{"""),format.raw/*354.35*/("""
	    	"""),format.raw/*355.7*/(""""fixedHeader": true,
	    	"lengthMenu": false,
	    	"paging":   false,
	        "ordering": [['1','asc']],
	        "info":     false,
			"searching": true,
	    	"language": """),format.raw/*361.19*/("""{"""),format.raw/*361.20*/("""
	        	"""),format.raw/*362.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*363.10*/("""}"""),format.raw/*363.11*/("""
	    """),format.raw/*364.6*/("""}"""),format.raw/*364.7*/(""" """),format.raw/*364.8*/(""");
		$('#tablaEquipos_oper').DataTable("""),format.raw/*365.37*/("""{"""),format.raw/*365.38*/("""
	    	"""),format.raw/*366.7*/(""""fixedHeader": true,
	    	"lengthMenu": false,
	    	"paging":   false,
	        "ordering": [['1','asc']],
	        "info":     false,
			"searching": true,
	    	"language": """),format.raw/*372.19*/("""{"""),format.raw/*372.20*/("""
	        	"""),format.raw/*373.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*374.10*/("""}"""),format.raw/*374.11*/("""
	    """),format.raw/*375.6*/("""}"""),format.raw/*375.7*/(""" """),format.raw/*375.8*/(""");
		$('#tablaEquipos_mec').DataTable("""),format.raw/*376.36*/("""{"""),format.raw/*376.37*/("""
	    	"""),format.raw/*377.7*/(""""fixedHeader": true,
	    	"lengthMenu": false,
	    	"paging":   false,
	        "ordering": [['1','asc']],
	        "info":     false,
			"searching": true,
	    	"language": """),format.raw/*383.19*/("""{"""),format.raw/*383.20*/("""
	        	"""),format.raw/*384.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*385.10*/("""}"""),format.raw/*385.11*/("""
	    """),format.raw/*386.6*/("""}"""),format.raw/*386.7*/(""" """),format.raw/*386.8*/(""");

			auxId_tipoMantencion = document.getElementById("id_tipoMantencion").innerHTML;
			auxModalListaEquipos = document.getElementById("auxModalListaEquipos").innerHTML;
		  	document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*391.2*/("""}"""),format.raw/*391.3*/(""");
	
	const validarForm = () =>"""),format.raw/*393.27*/("""{"""),format.raw/*393.28*/("""
		"""),format.raw/*394.3*/("""let flag = true;
		var desde = Date.parse(document.getElementById('fechaDesde').value);
		var hasta = Date.parse(document.getElementById('fechaHasta').value);
		let diffDias = Math.floor((hasta - desde)/(1000 * 60 * 60 * 24))+1;
		if(desde > hasta)"""),format.raw/*398.20*/("""{"""),format.raw/*398.21*/("""
			"""),format.raw/*399.4*/("""flag = false;
			alertify.alert('LA FECHA INICIAL NO PUEDE SER MAYOR A LA FECHA FINAL', function () """),format.raw/*400.87*/("""{"""),format.raw/*400.88*/("""
				"""),format.raw/*401.5*/("""return(flag);
     		"""),format.raw/*402.8*/("""}"""),format.raw/*402.9*/(""");
		"""),format.raw/*403.3*/("""}"""),format.raw/*403.4*/("""
		
		"""),format.raw/*405.3*/("""if(diffDias > 92)"""),format.raw/*405.20*/("""{"""),format.raw/*405.21*/("""
			"""),format.raw/*406.4*/("""flag = false;
			alertify.alert('EL PERIODO NO PUEDE SER MAYOR A 92 DIAS', function () """),format.raw/*407.74*/("""{"""),format.raw/*407.75*/("""
				"""),format.raw/*408.5*/("""return(flag);
     		"""),format.raw/*409.8*/("""}"""),format.raw/*409.9*/(""");
		"""),format.raw/*410.3*/("""}"""),format.raw/*410.4*/("""
		"""),format.raw/*411.3*/("""return(flag);
	"""),format.raw/*412.2*/("""}"""),format.raw/*412.3*/("""
	
	"""),format.raw/*414.2*/("""const listaOperMec = () =>"""),format.raw/*414.28*/("""{"""),format.raw/*414.29*/("""
		"""),format.raw/*415.3*/("""if($('#id_mantActorPersonal').val() == 0)"""),format.raw/*415.44*/("""{"""),format.raw/*415.45*/("""
			"""),format.raw/*416.4*/("""$('#modalListaALL').modal('show');
		"""),format.raw/*417.3*/("""}"""),format.raw/*417.4*/("""else if($('#id_mantActorPersonal').val() == 1)"""),format.raw/*417.50*/("""{"""),format.raw/*417.51*/("""
			"""),format.raw/*418.4*/("""$('#modalListaOperadores').modal('show'); 
		"""),format.raw/*419.3*/("""}"""),format.raw/*419.4*/("""else if($('#id_mantActorPersonal').val() == 2)"""),format.raw/*419.50*/("""{"""),format.raw/*419.51*/("""
			"""),format.raw/*420.4*/("""$('#modalListaMecanicos').modal('show'); 
		"""),format.raw/*421.3*/("""}"""),format.raw/*421.4*/("""
		
	"""),format.raw/*423.2*/("""}"""),format.raw/*423.3*/("""
	
	"""),format.raw/*425.2*/("""const listaEquipo = () =>"""),format.raw/*425.27*/("""{"""),format.raw/*425.28*/("""
		"""),format.raw/*426.3*/("""if($('#id_mantActorPersonal').val() == 2 && $('#aux_id_tipoMantencion').val() == 1)"""),format.raw/*426.86*/("""{"""),format.raw/*426.87*/("""
			"""),format.raw/*427.4*/("""$('#modalListaEquipos_mec').modal('show'); 
		"""),format.raw/*428.3*/("""}"""),format.raw/*428.4*/("""else"""),format.raw/*428.8*/("""{"""),format.raw/*428.9*/("""
			"""),format.raw/*429.4*/("""$('#modalListaEquipos_oper').modal('show');
		"""),format.raw/*430.3*/("""}"""),format.raw/*430.4*/("""
	"""),format.raw/*431.2*/("""}"""),format.raw/*431.3*/("""
	
	"""),format.raw/*433.2*/("""const selectTipoMantencion = () =>"""),format.raw/*433.36*/("""{"""),format.raw/*433.37*/("""
		"""),format.raw/*434.3*/("""document.getElementById("auxModalListaEquipos").innerHTML = auxModalListaEquipos;
	"""),format.raw/*435.2*/("""}"""),format.raw/*435.3*/("""
	
	
	
	"""),format.raw/*439.2*/("""const selectPerfil = (id_perfil) =>"""),format.raw/*439.37*/("""{"""),format.raw/*439.38*/("""
		"""),format.raw/*440.3*/("""$('#id_operMec').val(0);
		document.getElementById("nombreOperMec").innerHTML=
			"<div class='input-group '>"+
			  "<input class='form-control' type='text' onclick=\"listaOperMec()\" "+
					" value='--- todos ---' readonly>"+
			  "<div class='input-group-append'>"+
			    "<span class='input-group-text' onclick=\"listaOperMec()\">Buscar</span>"+
			  "</div>"+
			"</div>";
			
			if(id_perfil == 1)"""),format.raw/*450.22*/("""{"""),format.raw/*450.23*/("""
				"""),format.raw/*451.5*/("""document.getElementById("id_tipoMantencion").innerHTML= 
					"<select class=\"form-control \" "+
						" name=\"id_tipoMantencion\">"+
						" <option class=\"blank\" value=\"0\">NO APLICA</option>"+
						" </select>"
			"""),format.raw/*456.4*/("""}"""),format.raw/*456.5*/("""else"""),format.raw/*456.9*/("""{"""),format.raw/*456.10*/("""
				"""),format.raw/*457.5*/("""document.getElementById("id_tipoMantencion").innerHTML= auxId_tipoMantencion;
			"""),format.raw/*458.4*/("""}"""),format.raw/*458.5*/("""
			"""),format.raw/*459.4*/("""document.getElementById("auxModalListaEquipos").innerHTML = auxModalListaEquipos;
	"""),format.raw/*460.2*/("""}"""),format.raw/*460.3*/("""
	
	"""),format.raw/*462.2*/("""const selectOperMec = (id_operMec, operador, tipo) =>"""),format.raw/*462.55*/("""{"""),format.raw/*462.56*/("""
		"""),format.raw/*463.3*/("""operador = atob(operador);
		tipo = atob(tipo);
		$('#id_operMec').val(id_operMec);
		document.getElementById("nombreOperMec").innerHTML=
			"<div class='input-group'>"+
			  "<input class='form-control' type='text' onclick=\"$('#modalListaOperadores').modal('show');\" "+
					" value='"+operador+"' readonly>"+
			  "<div class='input-group-append'>"+
			    "<span class='input-group-text' onclick=\"$('#modalListaOperadores').modal('show');\">Buscar</span>"+
			  "</div>"+
			"</div>";
		$('#modalListaALL').modal('hide');
		$('#modalListaOperadores').modal('hide');
		$('#modalListaMecanicos').modal('hide');
	"""),format.raw/*477.2*/("""}"""),format.raw/*477.3*/("""
	
	"""),format.raw/*479.2*/("""const selectEquipo = (id_equipo, codigo, equipo, tipoPlanNombre) =>"""),format.raw/*479.69*/("""{"""),format.raw/*479.70*/("""
		"""),format.raw/*480.3*/("""codigo = atob(codigo);
		equipo = atob(equipo);
		if(tipoPlanNombre != 0)"""),format.raw/*482.26*/("""{"""),format.raw/*482.27*/("""
			"""),format.raw/*483.4*/("""tipoPlanNombre = " ("+atob(tipoPlanNombre)+")";
		"""),format.raw/*484.3*/("""}"""),format.raw/*484.4*/("""else"""),format.raw/*484.8*/("""{"""),format.raw/*484.9*/("""
			"""),format.raw/*485.4*/("""tipoPlanNombre = "";
		"""),format.raw/*486.3*/("""}"""),format.raw/*486.4*/("""
		
		"""),format.raw/*488.3*/("""$('#id_equipo').val(id_equipo);
		
		document.getElementById("nombreEquipo").innerHTML=
			"<div class='input-group '>"+
			  "<input class='form-control' type='text' onclick=\"listaEquipo();\" "+
				" id=\"valNomEquip_oper\" value='"+codigo+" - "+equipo+tipoPlanNombre+"' readonly>"+
			  "<div class='input-group-append'>"+
			    "<span class='input-group-text' onclick=\"listaEquipo();\">Buscar</span>"+
			  "</div>"+
			"</div>";
		$('#modalListaEquipos_oper').modal('hide');
		$('#modalListaEquipos_mec').modal('hide');
			
	"""),format.raw/*501.2*/("""}"""),format.raw/*501.3*/("""
	
	
"""),format.raw/*504.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,fechaDesde:String,fechaHasta:String,listActor:List[tables.MantActorPersonal],listOperMec:List[tables.MantOperadorMecanico],listTipoMantencion:List[tables.TipoMantencion],listEquipos:List[List[String]],listPlanMant:List[tables.PlanMantencion]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,fechaDesde,fechaHasta,listActor,listOperMec,listTipoMantencion,listEquipos,listPlanMant)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,String,String,List[tables.MantActorPersonal],List[tables.MantOperadorMecanico],List[tables.TipoMantencion],List[List[String]],List[tables.PlanMantencion]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,fechaDesde,fechaHasta,listActor,listOperMec,listTipoMantencion,listEquipos,listPlanMant) => apply(mapDiccionario,mapPermiso,userMnu,fechaDesde,fechaHasta,listActor,listOperMec,listTipoMantencion,listEquipos,listPlanMant)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuMantencion/mantHistorialReports0.scala.html
                  HASH: 486a4461158ec04d288215d261a41636d7937c29
                  MATRIX: 1178->1|1625->355|1652->357|1668->365|1707->367|1736->371|1804->419|1833->421|1910->472|1995->536|2025->539|2656->1143|2687->1153|2956->1395|2987->1405|3490->1881|3529->1904|3568->1905|3621->1930|3664->1946|3678->1951|3702->1954|3732->1957|3746->1962|3788->1983|3841->2005|3885->2021|5114->3223|5162->3255|5201->3256|5255->3282|5298->3298|5312->3303|5336->3306|5367->3310|5381->3315|5423->3336|5477->3359|5522->3376|7526->5353|7568->5378|7608->5379|7645->5388|7702->5417|7717->5422|7747->5430|7785->5440|7800->5445|7834->5457|7873->5468|7888->5473|7933->5495|8018->5552|8033->5557|8064->5566|8153->5627|8168->5632|8202->5644|8290->5704|8305->5709|8349->5731|8412->5763|8447->5770|9571->6867|9613->6892|9653->6893|9731->6943|9771->6944|9810->6954|9867->6983|9882->6988|9912->6996|9950->7006|9965->7011|9999->7023|10038->7034|10053->7039|10098->7061|10184->7119|10199->7124|10230->7133|10320->7195|10335->7200|10369->7212|10458->7273|10473->7278|10517->7300|10596->7334|10645->7352|10680->7359|11802->8454|11844->8479|11884->8480|11962->8530|12002->8531|12041->8541|12098->8570|12113->8575|12143->8583|12181->8593|12196->8598|12230->8610|12269->8621|12284->8626|12329->8648|12415->8706|12430->8711|12461->8720|12551->8782|12566->8787|12600->8799|12689->8860|12704->8865|12748->8887|12827->8921|12867->8930|12902->8937|14018->10026|14060->10051|14100->10052|14137->10061|14204->10100|14219->10105|14248->10112|14297->10133|14312->10138|14341->10145|14391->10167|14406->10172|14435->10179|14532->10248|14547->10253|14576->10260|14665->10321|14680->10326|14709->10333|14797->10393|14812->10398|14841->10405|14904->10437|14939->10444|16064->11542|16107->11568|16147->11569|16184->11578|16251->11617|16266->11622|16303->11637|16352->11658|16367->11663|16407->11681|16457->11703|16472->11708|16512->11726|16561->11747|16576->11752|16618->11772|16702->11828|16717->11833|16756->11850|16845->11911|16860->11916|16900->11934|16988->11994|17003->11999|17043->12017|17131->12077|17146->12082|17188->12102|17251->12134|17286->12141|17573->12397|17604->12400|17759->12526|17789->12527|17823->12533|17881->12562|17911->12563|17946->12570|18152->12747|18182->12748|18222->12759|18328->12836|18358->12837|18392->12843|18421->12844|18450->12845|18516->12882|18546->12883|18581->12890|18787->13067|18817->13068|18857->13079|18963->13156|18993->13157|19027->13163|19056->13164|19085->13165|19150->13201|19180->13202|19215->13209|19421->13386|19451->13387|19491->13398|19597->13475|19627->13476|19661->13482|19690->13483|19719->13484|19787->13523|19817->13524|19852->13531|20058->13708|20088->13709|20128->13720|20234->13797|20264->13798|20298->13804|20327->13805|20356->13806|20423->13844|20453->13845|20488->13852|20694->14029|20724->14030|20764->14041|20870->14118|20900->14119|20934->14125|20963->14126|20992->14127|21262->14369|21291->14370|21351->14401|21381->14402|21412->14405|21689->14653|21719->14654|21751->14658|21880->14758|21910->14759|21943->14764|21992->14785|22021->14786|22054->14791|22083->14792|22117->14798|22163->14815|22193->14816|22225->14820|22341->14907|22371->14908|22404->14913|22453->14934|22482->14935|22515->14940|22544->14941|22575->14944|22618->14959|22647->14960|22679->14964|22734->14990|22764->14991|22795->14994|22865->15035|22895->15036|22927->15040|22992->15077|23021->15078|23096->15124|23126->15125|23158->15129|23231->15174|23260->15175|23335->15221|23365->15222|23397->15226|23469->15270|23498->15271|23531->15276|23560->15277|23592->15281|23646->15306|23676->15307|23707->15310|23819->15393|23849->15394|23881->15398|23955->15444|23984->15445|24016->15449|24045->15450|24077->15454|24151->15500|24180->15501|24210->15503|24239->15504|24271->15508|24334->15542|24364->15543|24395->15546|24506->15629|24535->15630|24571->15638|24635->15673|24665->15674|24696->15677|25130->16082|25160->16083|25193->16088|25444->16311|25473->16312|25505->16316|25535->16317|25568->16322|25677->16403|25706->16404|25738->16408|25849->16491|25878->16492|25910->16496|25992->16549|26022->16550|26053->16553|26697->17169|26726->17170|26758->17174|26854->17241|26884->17242|26915->17245|27017->17318|27047->17319|27079->17323|27157->17373|27186->17374|27218->17378|27247->17379|27279->17383|27330->17406|27359->17407|27393->17413|27954->17946|27983->17947|28016->17952
                  LINES: 28->1|37->6|38->7|38->7|38->7|40->9|40->9|41->10|42->11|42->11|43->12|58->27|58->27|68->37|68->37|81->50|81->50|81->50|82->51|82->51|82->51|82->51|82->51|82->51|82->51|83->52|84->53|113->82|113->82|113->82|114->83|114->83|114->83|114->83|114->83|114->83|114->83|115->84|116->85|174->143|174->143|174->143|175->144|175->144|175->144|175->144|175->144|175->144|175->144|175->144|175->144|175->144|176->145|176->145|176->145|177->146|177->146|177->146|178->147|178->147|178->147|180->149|181->150|211->180|211->180|211->180|212->181|212->181|213->182|213->182|213->182|213->182|213->182|213->182|213->182|213->182|213->182|213->182|214->183|214->183|214->183|215->184|215->184|215->184|216->185|216->185|216->185|218->187|220->189|221->190|251->220|251->220|251->220|252->221|252->221|253->222|253->222|253->222|253->222|253->222|253->222|253->222|253->222|253->222|253->222|254->223|254->223|254->223|255->224|255->224|255->224|256->225|256->225|256->225|258->227|259->228|260->229|290->259|290->259|290->259|291->260|292->261|292->261|292->261|293->262|293->262|293->262|294->263|294->263|294->263|296->265|296->265|296->265|297->266|297->266|297->266|298->267|298->267|298->267|300->269|301->270|331->300|331->300|331->300|332->301|333->302|333->302|333->302|334->303|334->303|334->303|335->304|335->304|335->304|336->305|336->305|336->305|337->306|337->306|337->306|338->307|338->307|338->307|339->308|339->308|339->308|340->309|340->309|340->309|342->311|343->312|355->324|358->327|361->330|361->330|363->332|363->332|363->332|364->333|370->339|370->339|371->340|372->341|372->341|373->342|373->342|373->342|374->343|374->343|375->344|381->350|381->350|382->351|383->352|383->352|384->353|384->353|384->353|385->354|385->354|386->355|392->361|392->361|393->362|394->363|394->363|395->364|395->364|395->364|396->365|396->365|397->366|403->372|403->372|404->373|405->374|405->374|406->375|406->375|406->375|407->376|407->376|408->377|414->383|414->383|415->384|416->385|416->385|417->386|417->386|417->386|422->391|422->391|424->393|424->393|425->394|429->398|429->398|430->399|431->400|431->400|432->401|433->402|433->402|434->403|434->403|436->405|436->405|436->405|437->406|438->407|438->407|439->408|440->409|440->409|441->410|441->410|442->411|443->412|443->412|445->414|445->414|445->414|446->415|446->415|446->415|447->416|448->417|448->417|448->417|448->417|449->418|450->419|450->419|450->419|450->419|451->420|452->421|452->421|454->423|454->423|456->425|456->425|456->425|457->426|457->426|457->426|458->427|459->428|459->428|459->428|459->428|460->429|461->430|461->430|462->431|462->431|464->433|464->433|464->433|465->434|466->435|466->435|470->439|470->439|470->439|471->440|481->450|481->450|482->451|487->456|487->456|487->456|487->456|488->457|489->458|489->458|490->459|491->460|491->460|493->462|493->462|493->462|494->463|508->477|508->477|510->479|510->479|510->479|511->480|513->482|513->482|514->483|515->484|515->484|515->484|515->484|516->485|517->486|517->486|519->488|532->501|532->501|535->504
                  -- GENERATED --
              */
          