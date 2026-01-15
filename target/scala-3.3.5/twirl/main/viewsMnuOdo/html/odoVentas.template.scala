
package viewsMnuOdo.html

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

object odoVentas extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template7[Map[String,String],Map[String,String],utilities.UserMnu,String,List[tables.OperadorServicio],List[List[String]],Long,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
fecha: String, listOperadores: List[tables.OperadorServicio], listBodegas: List[List[String]], aux_huella: Long):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),format.raw/*7.1*/("""<!-- menues(mapDiccionario, mapPermiso, userMnu, " ") -->
	<div id="mostrarmostrar" style="display:none;">
		<!-- barraTitulo(mapDiccionario, "INGRESO DE REPORT DIARIO", "") -->
		
		<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
				<br>
				<div align=center><h4 style="color:blue"><strong>INGRESO DE REPORT DIARIO</strong></h4></div>
				<form id="grabarVenta" action="/odoVentasGrabar/" enctype="multipart/form-data" method="POST" enctype="multipart/form-data">
					<input type="hidden" name="aux_huella" value=""""),_display_(/*16.53*/aux_huella),format.raw/*16.63*/("""">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<thead>
							<tr>
								<td style="text-align:left;"><strong>FECHA (*):</strong></td>
						        <td style="text-align:left;" width="70%" colspan="3">
						        	<input style="max-width:150px" type="date" class="form-control form-control-sm" 
										id="fecha" 
										name="fecha" 
										value=""""),_display_(/*25.19*/fecha),format.raw/*25.24*/("""" 
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
						        	<input type="hidden" id="id_userMada" name="id_userMada" value=""""),_display_(/*46.81*/userMnu/*46.88*/.getId_usuario()),format.raw/*46.104*/("""">
						        		<div class="input-group input-group-sm">
										  <input class="form-control" type="text" value=""""),_display_(/*48.61*/userMnu/*48.68*/.getUserName()),format.raw/*48.82*/("""" readonly>
										</div>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>OPERADOR:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
						        	<input type="hidden" id="id_operador" name="id_operador" value="0">
						        	<div id="nombreOperador">
						        		<div class="input-group input-group-sm">
										  <input class="form-control" type="text" onclick="$('#modalListaOperadores').modal('show');" value="Sin operador" readonly>
										  <div class="input-group-append">
										    <span class="input-group-text" onclick="$('#modalListaOperadores').modal('show');">Buscar</span>
										  </div>
										</div> 
				    				</div>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>"""),_display_(/*67.47*/mapDiccionario/*67.61*/.get("BODEGA")),format.raw/*67.75*/(""" """),format.raw/*67.76*/("""(*):</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
						        	<input type="hidden" id="id_bodegaEmpresa" name="id_bodegaEmpresa" value="0">
						        	<div id="nombreBodegaEmpresa">
						        		<div class="input-group input-group-sm">
										  <input class="form-control" type="text" onclick="$('#modalListaBodegas').modal('show');" readonly>
										  <div class="input-group-append">
										    <span class="input-group-text" onclick="$('#modalListaBodegas').modal('show');">Buscar</span>
										  </div>
										</div> 
				    				</div>
						        </td>
							</tr>
							
							<tr>
								<td style="text-align:left;"><strong>SERVICIO (*):</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
						        	<input type="hidden" id="id_servicio" name="id_servicio" value="0">
						        	<input type="hidden" id="id_cotiOdo" name="id_cotiOdo" value="0">
						        	<div id="nombreServicio">
						        		<div class="input-group input-group-sm">
										  <input class="form-control" type="text" onclick="$('#modalListaServicios').modal('show');" readonly>
										  <div class="input-group-append">
										    <span class="input-group-text" onclick="$('#modalListaServicios').modal('show');">Buscar</span>
										  </div>
										</div> 
				    				</div>
						        </td>
							</tr>
							
							<tr>
								<td style="text-align:left;"><strong>EQUIPO:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
						        	<input type="hidden" id="id_equipo" name="id_equipo" value="0">
						        	<div id="nombreEquipo">
						        		<div class="input-group input-group-sm">
										  <input class="form-control" type="text" onclick="$('#modalListaEquipos').modal('show');" value="Sin equipo asociado" readonly>
										  <div class="input-group-append">
										    <span class="input-group-text" onclick="$('#modalListaEquipos').modal('show');">Buscar</span>
										  </div>
										</div> 
				    				</div>
						        </td>
							</tr>
							
							<tr>
								<td style="text-align:left;"><strong>Hr_INI:</strong></td>
						        <td style="text-align:left">
									<input type="time" class="form-control form-control-sm left" style="max-width:100px"
										id="horaIni" 
										name="horaIni"
										onblur="validarHoraIni()">
						        </td>
								<td style="text-align:left;"><strong>Hr_TER:</strong></td>
						        <td style="text-align:left" colspan="2">
									<input type="time" class="form-control form-control-sm left"  style="max-width:100px"
										id="horaTer" 
										name="horaTer"
										onblur="validarHoraTer()">
						        </td>
							</tr>
							
							<tr>
								<td style="text-align:left;"><strong>Lect_INI:</strong></td>
						        <td style="text-align:left">
									<input type="text" class="form-control form-control-sm left" style="max-width:200px"
										id="lecturaIni" 
										name="lecturaIni"
										value = "0.00"
										onClick="this.select();"
										onfocus="value=value.replace(/,/g,'')" 
										onfocusout = "if(value.trim()=='') value=0; value = formatStandar(value, 2);"
										onkeydown="return ingresoDouble(window.event)"
										onchange="validarLectIni()"
										autocomplete="off">
						        </td>
								<td style="text-align:left;"><strong>Lect_TER:</strong></td>
						        <td style="text-align:left" colspan="2">
									<input type="text" class="form-control form-control-sm left" style="max-width:200px"
										id="lecturaTer" 
										name="lecturaTer"
										value = "0.00"
										onClick="this.select();"
										onfocus="value=value.replace(/,/g,'')" 
										onfocusout = "if(value.trim()=='') value=0; value = formatStandar(value, 2);"
										onkeydown="return ingresoDouble(window.event)"
										onchange="validarLectTer()"
										autocomplete="off">
						        </td>
							</tr>
							
							<tr>
								<td style="text-align:left;"><strong><div id="unidad">CANT (*)</div></strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control form-control-sm left"
										id="cantidad" 
										name="cantidad"
										value = "0.00"
										onClick="this.select();"
										onfocus="value=value.replace(/,/g,'')" 
										onfocusout = "if(value.trim()=='') value=0; value = formatStandar(value, 2);"
										onkeydown="return ingresoDouble(window.event)"
										onchange="if(value.trim()=='') value=formatStandar(0,2);"
										autocomplete="off">
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>DETALLE:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<input type="text" class="form-control form-control-sm left"
										id="detalle" 
										name="detalle"
										value = ""
										maxlength="250"
										autocomplete="off">
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>NOTAS:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
									<textarea class="form-control form-control-sm" rows="1" 
										id="observaciones"
										name="observaciones" 
										autocomplete="off"></textarea>
						        </td>
							</tr>
							
							<tr>
								<td style="text-align:left;"  colspan="5">
									<input type="hidden" name="firmaPDFoperador" id="firma" value="0">
									FIRMA OPERADOR:<br>
									<div class="wrapper" align="center">
									<canvas id="signature-pad" class="signature-pad" width=350 height=90 style="border:1px solid #000000;"></canvas>
									</div>
									<button type="button" class="btn btn-sm btn-warning" style="font-size: 10px;" onclick="signaturePad.clear()">Borrar firma</button>
								</td>
							</tr>
							<tr>
								<td style="text-align:left;"   colspan="5">
									<input type="hidden" name="firmaPDFautorizador" id="firma2" value="0">
									FIRMA AUTORIZADA:<br>
									<div class="wrapper" align="center">
									<canvas id="signature-pad2" class="signature-pad" width=350 height=90 style="border:1px solid #000000;"></canvas>
									</div>
									<button type="button" class="btn btn-sm btn-warning" style="font-size: 10px;" onclick="signaturePad2.clear()">Borrar firma</button>
								</td>
							</tr>
						
							<tr>
								<td colspan="5" style="text-align:center;">
									<button type="button" class="btn btn-sm btn-success" style="font-size: 10px;"onclick="grabar();" >GRABAR
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<button type="button" class="btn btn-sm btn-warning" style="font-size: 10px;" onclick="location.href = '/home/'">SALIR</button>
								</td>
							</tr>
						</thead>
					</table>
					
					<font style="font-size: 12px;">(*) Campos Obligatorios</font>
				</form>
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
							</TR>
						</thead>
						<tbody>
							<TR onclick="selectOperador('0',btoa('Sin operador'))">
								<td><a href="#">N/A</a></td>
								<td style="text-align:left"><a href="#">Sin operador</a></td>														
							</TR>
							"""),_display_(/*253.9*/for(lista <- listOperadores) yield /*253.37*/{_display_(Seq[Any](format.raw/*253.38*/("""
								"""),format.raw/*254.9*/("""<TR onclick="selectOperador('"""),_display_(/*254.39*/lista/*254.44*/.getId()),format.raw/*254.52*/("""', btoa('"""),_display_(/*254.62*/lista/*254.67*/.getNombre()),format.raw/*254.79*/("""') )">
									<td style="text-align:left"><a href="#">"""),_display_(/*255.51*/lista/*255.56*/.getRut()),format.raw/*255.65*/("""</a></td>	
									<td style="text-align:left"><a href="#">"""),_display_(/*256.51*/lista/*256.56*/.getNombre()),format.raw/*256.68*/("""</a></td>														
								</TR>
							""")))}),format.raw/*258.9*/("""
						"""),format.raw/*259.7*/("""</tbody>
					</table>
			        <br>
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
			        <h5 class="modal-title">Seleccionar """),_display_(/*274.49*/mapDiccionario("Bodega")),format.raw/*274.73*/("""/Proyecto</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body">
					<table id="tablaBodegas" class="table table-sm table-bordered table-condensed table-hover table-fluid">
				        <thead style="background-color: #eeeeee">
							<TR> 
								<th>Sucursal</th>
								<th>"""),_display_(/*284.14*/mapDiccionario("Bodega")),format.raw/*284.38*/("""/Proyecto</th>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*288.9*/for(lista <- listBodegas) yield /*288.34*/{_display_(Seq[Any](format.raw/*288.35*/("""
								"""),format.raw/*289.9*/("""<TR onclick="selectBodega('"""),_display_(/*289.37*/lista/*289.42*/.get(0)),format.raw/*289.49*/("""', btoa('"""),_display_(/*289.59*/lista/*289.64*/.get(1)),format.raw/*289.71*/("""') )">
									<td style="text-align:left"><a href="#">"""),_display_(/*290.51*/lista/*290.56*/.get(2)),format.raw/*290.63*/("""</a></td>	
									<td style="text-align:left"><a href="#">"""),_display_(/*291.51*/lista/*291.56*/.get(1)),format.raw/*291.63*/("""</a></td>													
								</TR>
							""")))}),format.raw/*293.9*/("""
						"""),format.raw/*294.7*/("""</tbody>
					</table>
			        <br>
					<div align="center">
			           	<button type="button" class="btn btn-sm btn-success" data-dismiss="modal" style="font-size: 10px;">CERRAR</button>
				    </div>
			      </div>
			    </div>
			 </div>
		</div>
		
		<div id="modalListaEquipos" class="modal" role="dialog" data-backdrop="static" data-keyboard="false">
			<div class="modal-dialog modal-lg modal-dialog-scrollable" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title">Seleccionar </h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body">
					<div id="tablaListaEquipos">Debe seleccionar primero una """),_display_(/*315.64*/mapDiccionario("Bodega")),format.raw/*315.88*/("""/Proyecto</div>
			        <br>
					<div align="center">
			           	<button type="button" class="btn btn-sm btn-success" data-dismiss="modal" style="font-size: 10px;">CERRAR</button>
				    </div>
			      </div>
			    </div>
			 </div>
		</div>
		
		<div id="modalListaServicios" class="modal" role="dialog" data-backdrop="static" data-keyboard="false">
			<div class="modal-dialog modal-lg modal-dialog-scrollable" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title">Seleccionar </h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body">
					<div id="tablaListaServicios">Debe seleccionar primero una """),_display_(/*335.66*/mapDiccionario("Bodega")),format.raw/*335.90*/("""/Proyecto</div>
			        <br>
					<div align="center">
			           	<button type="button" class="btn btn-sm btn-success" data-dismiss="modal" style="font-size: 10px;">CERRAR</button>
				    </div>
			      </div>
			    </div>
			 </div>
		</div>
		
		
		
		
		
	
		<div id="modalListaProductos" class="modal" role="dialog" data-backdrop="static" data-keyboard="false">
			<div class="modal-dialog modal-lg modal-dialog-scrollable" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title">Seleccionar producto/equipo</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body">
			      		<div id="tablaListaProductos"></div>
			        <br>
					<div align="center">
			           	<button type="button" class="btn btn-sm btn-success" data-dismiss="modal" style="font-size: 10px;">CERRAR</button>
				    </div>
			      </div>
			    </div>
			 </div>
		</div>
		
	</div>
""")))}),format.raw/*371.2*/("""


"""),format.raw/*374.1*/("""<!-- CONFIGURA PAD -->

<script src=""""),_display_(/*376.15*/routes/*376.21*/.Assets.versioned("javascripts/signature_pad.min.js")),format.raw/*376.74*/(""""></script>
	<script>
		let signaturePad = new SignaturePad(document.getElementById('signature-pad'), """),format.raw/*378.81*/("""{"""),format.raw/*378.82*/("""
		  """),format.raw/*379.5*/("""backgroundColor: 'rgba(255, 255, 255, 0)',
		  penColor: 'rgb(0, 0, 0)'
		"""),format.raw/*381.3*/("""}"""),format.raw/*381.4*/(""");
		
		let signaturePad2 = new SignaturePad(document.getElementById('signature-pad2'), """),format.raw/*383.83*/("""{"""),format.raw/*383.84*/("""
		  """),format.raw/*384.5*/("""backgroundColor: 'rgba(255, 255, 255, 0)',
		  penColor: 'rgb(0, 0, 0)'
		"""),format.raw/*386.3*/("""}"""),format.raw/*386.4*/(""");
	</script>
	
	<style>
		.wrapper """),format.raw/*390.12*/("""{"""),format.raw/*390.13*/("""
		  """),format.raw/*391.5*/("""position: relative;
		  width: 350px;
		  height: 90px;
		  -moz-user-select: none;
		  -webkit-user-select: none;
		  -ms-user-select: none;
		  user-select: none;
		"""),format.raw/*398.3*/("""}"""),format.raw/*398.4*/("""
		"""),format.raw/*399.3*/("""/*
		img """),format.raw/*400.7*/("""{"""),format.raw/*400.8*/("""
		  """),format.raw/*401.5*/("""position: absolute;
		  left: 0;
		  top: 0;
		"""),format.raw/*404.3*/("""}"""),format.raw/*404.4*/("""*/
		
		.signature-pad """),format.raw/*406.18*/("""{"""),format.raw/*406.19*/("""
		  """),format.raw/*407.5*/("""position: absolute;
		  left: 0;
		  top: 0;
		  width:350px;
		  height:90px;
		"""),format.raw/*412.3*/("""}"""),format.raw/*412.4*/("""
	"""),format.raw/*413.2*/("""</style>
<!-- FIN PAD -->
	
<script>
	
	$(document).ready(function() """),format.raw/*418.31*/("""{"""),format.raw/*418.32*/("""
		"""),format.raw/*419.3*/("""$('#tablaOperadores').DataTable("""),format.raw/*419.35*/("""{"""),format.raw/*419.36*/("""
	    	"""),format.raw/*420.7*/(""""fixedHeader": true,
	    	"lengthMenu": false,
	    	"paging":   false,
	        "ordering": [['1','asc']],
	        "info":     false,
			"searching": true,
	    	"language": """),format.raw/*426.19*/("""{"""),format.raw/*426.20*/("""
	        	"""),format.raw/*427.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*428.10*/("""}"""),format.raw/*428.11*/("""
	    """),format.raw/*429.6*/("""}"""),format.raw/*429.7*/(""" """),format.raw/*429.8*/(""");
		$('#tablaBodegas').DataTable("""),format.raw/*430.32*/("""{"""),format.raw/*430.33*/("""
	    	"""),format.raw/*431.7*/(""""fixedHeader": true,
	    	"lengthMenu": false,
	    	"paging":   false,
	        "ordering": [['1','asc']],
	        "info":     false,
			"searching": true,
	    	"language": """),format.raw/*437.19*/("""{"""),format.raw/*437.20*/("""
	        	"""),format.raw/*438.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*439.10*/("""}"""),format.raw/*439.11*/("""
	    """),format.raw/*440.6*/("""}"""),format.raw/*440.7*/(""" """),format.raw/*440.8*/(""");
 		document.getElementById('mostrarmostrar').style.display="block";
	
	"""),format.raw/*443.2*/("""}"""),format.raw/*443.3*/(""" """),format.raw/*443.4*/(""");
	
	function validarLectIni()"""),format.raw/*445.27*/("""{"""),format.raw/*445.28*/("""
		"""),format.raw/*446.3*/("""let lecturaIni = $("#lecturaIni").val().replace(/,/g,'');
		let lecturaTer = $("#lecturaTer").val().replace(/,/g,'');
		if(lecturaTer!="0.00")"""),format.raw/*448.25*/("""{"""),format.raw/*448.26*/("""
			"""),format.raw/*449.4*/("""if(parseFloat(lecturaIni) >=parseFloat(lecturaTer))"""),format.raw/*449.55*/("""{"""),format.raw/*449.56*/("""
				"""),format.raw/*450.5*/("""alertify.alert("La lectura inicial no puede ser menor o igual a la lectura de termino");
				$("#lecturaIni").val(formatStandar(0,2));
				$("#lecturaTer").val(formatStandar(0,2));
				$("#cantidad").val(formatStandar(0,2));
			"""),format.raw/*454.4*/("""}"""),format.raw/*454.5*/("""else"""),format.raw/*454.9*/("""{"""),format.raw/*454.10*/("""
				"""),format.raw/*455.5*/("""let dif = parseFloat(lecturaTer) - parseFloat(lecturaIni);
				$("#cantidad").val(formatStandar(dif,2));
			"""),format.raw/*457.4*/("""}"""),format.raw/*457.5*/("""
		"""),format.raw/*458.3*/("""}"""),format.raw/*458.4*/("""
	"""),format.raw/*459.2*/("""}"""),format.raw/*459.3*/("""
	
	"""),format.raw/*461.2*/("""function validarLectTer()"""),format.raw/*461.27*/("""{"""),format.raw/*461.28*/("""
		"""),format.raw/*462.3*/("""let lecturaIni = $("#lecturaIni").val().replace(/,/g,'');
		let lecturaTer = $("#lecturaTer").val().replace(/,/g,'');
		if(lecturaIni=="0.00")"""),format.raw/*464.25*/("""{"""),format.raw/*464.26*/("""
			"""),format.raw/*465.4*/("""alertify.alert("Primero debe ingresar la lectura inicial");
			$("#lecturaTer").val(formatStandar(0,2));
			$("#cantidad").val(formatStandar(0,2));
		"""),format.raw/*468.3*/("""}"""),format.raw/*468.4*/("""else"""),format.raw/*468.8*/("""{"""),format.raw/*468.9*/("""
			"""),format.raw/*469.4*/("""if(parseFloat(lecturaIni) >= parseFloat(lecturaTer))"""),format.raw/*469.56*/("""{"""),format.raw/*469.57*/("""
				"""),format.raw/*470.5*/("""alertify.alert("La lectura inicial no puede ser menor o igual a la lectura de termino");
				$("#lecturaTer").val(formatStandar(0,2));
				$("#cantidad").val(formatStandar(0,2));
			"""),format.raw/*473.4*/("""}"""),format.raw/*473.5*/("""else"""),format.raw/*473.9*/("""{"""),format.raw/*473.10*/("""
				"""),format.raw/*474.5*/("""let dif = parseFloat(lecturaTer) - parseFloat(lecturaIni);
				$("#cantidad").val(formatStandar(dif,2));
			"""),format.raw/*476.4*/("""}"""),format.raw/*476.5*/("""
		"""),format.raw/*477.3*/("""}"""),format.raw/*477.4*/("""
	"""),format.raw/*478.2*/("""}"""),format.raw/*478.3*/("""
	
	"""),format.raw/*480.2*/("""function validarHoraIni()"""),format.raw/*480.27*/("""{"""),format.raw/*480.28*/("""
		"""),format.raw/*481.3*/("""let horaIni = $("#horaIni").val();
		let horaTer = $("#horaTer").val();
		if(horaTer!="")"""),format.raw/*483.18*/("""{"""),format.raw/*483.19*/("""
			"""),format.raw/*484.4*/("""if(parseFloat(horaIni) >= parseFloat(horaTer))"""),format.raw/*484.50*/("""{"""),format.raw/*484.51*/("""
				"""),format.raw/*485.5*/("""alertify.alert("La hora inicial no puede ser menor o igual a la hora de termino");
				$("#horaIni").val("");
				$("#horaTer").val("");
				$("#cantidad").val(formatStandar(0,2));
			"""),format.raw/*489.4*/("""}"""),format.raw/*489.5*/("""else"""),format.raw/*489.9*/("""{"""),format.raw/*489.10*/("""
				"""),format.raw/*490.5*/("""let hrIni = parseInt(horaIni.substr(0,2));
				let minIni = parseInt(horaIni.substr(3,2));
				let ini = parseFloat(hrIni) + (parseFloat(minIni)/60);
				let hrTer = parseInt(horaTer.substr(0,2));
				let minTer = parseInt(horaTer.substr(3,2));
				let ter = parseFloat(hrTer) + (parseFloat(minTer)/60);
				let dif = ter -ini;
				dif = Math.round(dif*100)/100;
				$("#cantidad").val(formatStandar(dif,2));
			"""),format.raw/*499.4*/("""}"""),format.raw/*499.5*/("""
		"""),format.raw/*500.3*/("""}"""),format.raw/*500.4*/("""
	"""),format.raw/*501.2*/("""}"""),format.raw/*501.3*/("""
	
	"""),format.raw/*503.2*/("""function validarHoraTer()"""),format.raw/*503.27*/("""{"""),format.raw/*503.28*/("""
		"""),format.raw/*504.3*/("""let horaIni = $("#horaIni").val();
		let horaTer = $("#horaTer").val();
		if(horaIni=="")"""),format.raw/*506.18*/("""{"""),format.raw/*506.19*/("""
			"""),format.raw/*507.4*/("""alertify.alert("Primero debe ingresar la hora inicial");
			$("#horaTer").val("");
			$("#cantidad").val(formatStandar(0,2));
		"""),format.raw/*510.3*/("""}"""),format.raw/*510.4*/("""else"""),format.raw/*510.8*/("""{"""),format.raw/*510.9*/("""
			"""),format.raw/*511.4*/("""if(parseFloat(horaIni) >= parseFloat(horaTer))"""),format.raw/*511.50*/("""{"""),format.raw/*511.51*/("""
				"""),format.raw/*512.5*/("""alertify.alert("La hora inicial no puede ser menor o igual a la hora de termino");
				$("#horaTer").val("");
				$("#cantidad").val(formatStandar(0,2));
			"""),format.raw/*515.4*/("""}"""),format.raw/*515.5*/("""else"""),format.raw/*515.9*/("""{"""),format.raw/*515.10*/("""
				"""),format.raw/*516.5*/("""let hrIni = parseInt(horaIni.substr(0,2));
				let minIni = parseInt(horaIni.substr(3,2));
				let ini = parseFloat(hrIni) + (parseFloat(minIni)/60);
				let hrTer = parseInt(horaTer.substr(0,2));
				let minTer = parseInt(horaTer.substr(3,2));
				let ter = parseFloat(hrTer) + (parseFloat(minTer)/60);
				let dif = ter -ini;
				dif = Math.round(dif*100)/100;
				$("#cantidad").val(formatStandar(dif,2));
			"""),format.raw/*525.4*/("""}"""),format.raw/*525.5*/("""
		"""),format.raw/*526.3*/("""}"""),format.raw/*526.4*/("""
	"""),format.raw/*527.2*/("""}"""),format.raw/*527.3*/("""
	
	
	"""),format.raw/*530.2*/("""function selectOperador(id_operador, operador)"""),format.raw/*530.48*/("""{"""),format.raw/*530.49*/("""
		"""),format.raw/*531.3*/("""operador = atob(operador);
		$('#id_operador').val(id_operador);
		document.getElementById("nombreOperador").innerHTML=
			"<div class='input-group input-group-sm'>"+
			  "<input class='form-control' type='text' onclick=\"$('#modalListaOperadores').modal('show');\" value='"+operador+"' readonly>"+
			  "<div class='input-group-append'>"+
			    "<span class='input-group-text' onclick=\"$('#modalListaOperadores').modal('show');\">Buscar</span>"+
			  "</div>"+
			"</div>";
		$('#modalListaOperadores').modal('hide');
	"""),format.raw/*541.2*/("""}"""),format.raw/*541.3*/("""
	
	"""),format.raw/*543.2*/("""function selectBodega(id_bodega, bodega)"""),format.raw/*543.42*/("""{"""),format.raw/*543.43*/("""
		"""),format.raw/*544.3*/("""bodega = atob(bodega);
		$('#id_bodega').val(id_bodega);
		document.getElementById("nombreBodegaEmpresa").innerHTML=
			"<div class='input-group input-group-sm'>"+
			  "<input class='form-control' type='text' onclick=\"$('#modalListaBodegas').modal('show');\" value='"+bodega+"' readonly>"+
			  "<div class='input-group-append'>"+
			    "<span class='input-group-text' onclick=\"$('#modalListaBodegas').modal('show');\">Buscar</span>"+
			  "</div>"+
			"</div>";
		llenaTablaServicios(id_bodega);
		llenaTablaEquipos(id_bodega);
		$("#id_bodegaEmpresa").val(id_bodega);
		$('#modalListaBodegas').modal('hide');
	"""),format.raw/*557.2*/("""}"""),format.raw/*557.3*/("""
	
	"""),format.raw/*559.2*/("""function llenaTablaServicios(id_bodega)"""),format.raw/*559.41*/("""{"""),format.raw/*559.42*/("""
		"""),format.raw/*560.3*/("""var formData = new FormData();
		formData.append("id_bodegaEmpresa",id_bodega);
		$.ajax("""),format.raw/*562.10*/("""{"""),format.raw/*562.11*/("""
            """),format.raw/*563.13*/("""url: "/odoListaServiciosAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(rs)"""),format.raw/*570.29*/("""{"""),format.raw/*570.30*/("""
				"""),format.raw/*571.5*/("""document.getElementById("tablaListaServicios").innerHTML=rs;
	     	"""),format.raw/*572.8*/("""}"""),format.raw/*572.9*/("""
	  	"""),format.raw/*573.5*/("""}"""),format.raw/*573.6*/(""");
	"""),format.raw/*574.2*/("""}"""),format.raw/*574.3*/("""
	
	"""),format.raw/*576.2*/("""function selectServicio(id_servicio, servicio, unidad, id_cotiOdo, equipo, id_equipo)"""),format.raw/*576.87*/("""{"""),format.raw/*576.88*/("""
		"""),format.raw/*577.3*/("""servicio = atob(servicio);
		equipo = atob(equipo);
		$('#id_servicio').val(id_servicio);
		$('#id_cotiOdo').val(id_cotiOdo);
		document.getElementById("nombreServicio").innerHTML=
			"<div class='input-group input-group-sm'>"+
			  "<input class='form-control' type='text' onclick=\"$('#modalListaServicios').modal('show');\" value='"+servicio+"' readonly>"+
			  "<div class='input-group-append'>"+
			    "<span class='input-group-text' onclick=\"$('#modalListaServicios').modal('show');\">Buscar</span>"+
			  "</div>"+
			"</div>";
		$("#unidad").text("CANT (*) en "+unidad);
		if(id_equipo != "0")"""),format.raw/*589.23*/("""{"""),format.raw/*589.24*/("""
			"""),format.raw/*590.4*/("""$('#id_equipo').val(id_equipo);
			document.getElementById("nombreEquipo").innerHTML=
				"<div class='input-group input-group-sm'>"+
					"<input class='form-control' type='text' value='"+equipo+"' readonly>"+
				"</div>";
		"""),format.raw/*595.3*/("""}"""),format.raw/*595.4*/("""else"""),format.raw/*595.8*/("""{"""),format.raw/*595.9*/("""
			"""),format.raw/*596.4*/("""$('#id_equipo').val("0");
			document.getElementById("nombreEquipo").innerHTML=
				"<div class='input-group input-group-sm'>"+
					"<input class=\"form-control\" type=\"text\" onclick=\"$('#modalListaEquipos').modal('show');\" value=\"Sin equipo asociado\" readonly>"+
						"<div class=\"input-group-append\">"+
							"<span class=\"input-group-text\" onclick=\"$('#modalListaEquipos').modal('show');\">Buscar</span>"+
						"</div>"+
				"</div>";
		"""),format.raw/*604.3*/("""}"""),format.raw/*604.4*/("""

		"""),format.raw/*606.3*/("""$('#modalListaServicios').modal('hide');
	"""),format.raw/*607.2*/("""}"""),format.raw/*607.3*/("""
	
	"""),format.raw/*609.2*/("""function llenaTablaEquipos(id_bodega)"""),format.raw/*609.39*/("""{"""),format.raw/*609.40*/("""
		"""),format.raw/*610.3*/("""var formData = new FormData();
		formData.append("id_bodegaEmpresa",id_bodega);
		$.ajax("""),format.raw/*612.10*/("""{"""),format.raw/*612.11*/("""
            """),format.raw/*613.13*/("""url: "/odoListaEquiposAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(rs)"""),format.raw/*620.29*/("""{"""),format.raw/*620.30*/("""
				"""),format.raw/*621.5*/("""document.getElementById("tablaListaEquipos").innerHTML=rs;
	     	"""),format.raw/*622.8*/("""}"""),format.raw/*622.9*/("""
	  	"""),format.raw/*623.5*/("""}"""),format.raw/*623.6*/(""");
	"""),format.raw/*624.2*/("""}"""),format.raw/*624.3*/("""
	
	"""),format.raw/*626.2*/("""function selectEquipo(id_equipo, equipo, lectura)"""),format.raw/*626.51*/("""{"""),format.raw/*626.52*/("""
		"""),format.raw/*627.3*/("""equipo = atob(equipo);
		$('#id_equipo').val(id_equipo);
		document.getElementById("nombreEquipo").innerHTML=
			"<div class='input-group input-group-sm'>"+
			  "<input class='form-control' type='text' onclick=\"$('#modalListaEquipos').modal('show');\" value='"+equipo+"' readonly>"+
			  "<div class='input-group-append'>"+
			    "<span class='input-group-text' onclick=\"$('#modalListaEquipos').modal('show');\">Buscar</span>"+
			  "</div>"+
			"</div>";
		$('#modalListaEquipos').modal('hide');
		
		$('#lecturaIni').val(lectura);
		$('#lecturaTer').val(lectura);
		
	"""),format.raw/*641.2*/("""}"""),format.raw/*641.3*/("""
	
	"""),format.raw/*643.2*/("""function grabar()"""),format.raw/*643.19*/("""{"""),format.raw/*643.20*/("""
		  
		  """),format.raw/*645.5*/("""if($('#id_bodegaEmpresa').val()==0)"""),format.raw/*645.40*/("""{"""),format.raw/*645.41*/("""
			  """),format.raw/*646.6*/("""alertify.alert('Debe seleccionar una """),_display_(/*646.44*/mapDiccionario/*646.58*/.get("Bodega")),format.raw/*646.72*/("""/Proyecto');
		  """),format.raw/*647.5*/("""}"""),format.raw/*647.6*/("""else if($('#id_servicio').val()==0)"""),format.raw/*647.41*/("""{"""),format.raw/*647.42*/("""
			  """),format.raw/*648.6*/("""alertify.alert('Debe seleccionar un Servicio');
		  """),format.raw/*649.5*/("""}"""),format.raw/*649.6*/("""else if($('#cantidad').val()=="0.00")"""),format.raw/*649.43*/("""{"""),format.raw/*649.44*/("""
			  """),format.raw/*650.6*/("""alertify.alert('Debe ingresar una cantidad mayor que cero');
		  """),format.raw/*651.5*/("""}"""),format.raw/*651.6*/("""else"""),format.raw/*651.10*/("""{"""),format.raw/*651.11*/("""
			
			"""),format.raw/*653.4*/("""let data = signaturePad.toDataURL('image/png');
			let encodedImage = data.split(",");
			$("#firma").val(encodedImage[1]);
			
			data = signaturePad2.toDataURL('image/png');
			encodedImage = data.split(",");
			$("#firma2").val(encodedImage[1]);
			
			$("#grabarVenta").submit();
		  """),format.raw/*662.5*/("""}"""),format.raw/*662.6*/("""
	"""),format.raw/*663.2*/("""}"""),format.raw/*663.3*/("""
	
	
	"""),format.raw/*666.2*/("""function actualizaFechaReal() """),format.raw/*666.32*/("""{"""),format.raw/*666.33*/("""
		"""),format.raw/*667.3*/("""if(!isValidDate($('#fecha').val()))"""),format.raw/*667.38*/("""{"""),format.raw/*667.39*/("""
    		"""),format.raw/*668.7*/("""alertify.alert("Debe ingresar una fecha valida");
       		$('#fecha').val(""""),_display_(/*669.28*/fecha),format.raw/*669.33*/("""");
    	"""),format.raw/*670.6*/("""}"""),format.raw/*670.7*/("""else"""),format.raw/*670.11*/("""{"""),format.raw/*670.12*/("""
			"""),format.raw/*671.4*/("""let diasMenos = '"""),_display_(/*671.22*/mapPermiso/*671.32*/.get("parametro.diasMenosGuia")),format.raw/*671.63*/("""';
    		var fechaMas = new Date();
        	var fechaMenos = new Date();
        	var fecha = new Date($('#fecha').val());
        	fechaMas.setDate(fechaMas.getDate() + 0);
        	fechaMenos.setDate(fechaMenos.getDate() - diasMenos);
        	if(fecha>fechaMas)"""),format.raw/*677.28*/("""{"""),format.raw/*677.29*/("""
        		"""),format.raw/*678.11*/("""alertify.alert("La fecha no puede ser posterior a la fecha actual.");
        		$('#fecha').val('"""),_display_(/*679.29*/fecha),format.raw/*679.34*/("""');
        	"""),format.raw/*680.10*/("""}"""),format.raw/*680.11*/("""else if(fecha<fechaMenos)"""),format.raw/*680.36*/("""{"""),format.raw/*680.37*/("""
           		"""),format.raw/*681.14*/("""alertify.alert("La fecha no puede tener mas de "+diasMenos+" d&iacute;as de antiguedad.");
           		$('#fecha').val('"""),_display_(/*682.32*/fecha),format.raw/*682.37*/("""');
        	"""),format.raw/*683.10*/("""}"""),format.raw/*683.11*/("""
    	"""),format.raw/*684.6*/("""}"""),format.raw/*684.7*/("""
    	
    """),format.raw/*686.5*/("""}"""),format.raw/*686.6*/("""
    

	
	"""),format.raw/*690.2*/("""let extArray = new Array(".gif", ".jpg", ".png", ".jpeg", ".pdf", ".xls", ".doc", ".xlsx", ".docx", ".tar", ".zip", ".rar");
	const LimitAttach = (form, file) => """),format.raw/*691.38*/("""{"""),format.raw/*691.39*/("""
		
		"""),format.raw/*693.3*/("""if (!file) return;
		
		const $inputArchivos = document.querySelector("#docAdjunto");
		const archivosParaSubir = $inputArchivos.files;
		let tamanio = 0;
		let allowSubmit = false;
		
		for(let i=0; i<archivosParaSubir.length; i++)"""),format.raw/*700.48*/("""{"""),format.raw/*700.49*/("""
			"""),format.raw/*701.4*/("""tamanio += archivosParaSubir[i].size;
			let nombre = archivosParaSubir[i].name;
			let extencion = nombre.substring(nombre.lastIndexOf(".")).toLowerCase();
			for (let j = 0; j < extArray.length; j++) """),format.raw/*704.46*/("""{"""),format.raw/*704.47*/("""
				"""),format.raw/*705.5*/("""if (extArray[j] == extencion) """),format.raw/*705.35*/("""{"""),format.raw/*705.36*/(""" 
					"""),format.raw/*706.6*/("""allowSubmit = true;
				"""),format.raw/*707.5*/("""}"""),format.raw/*707.6*/("""
			"""),format.raw/*708.4*/("""}"""),format.raw/*708.5*/("""
			"""),format.raw/*709.4*/("""if(allowSubmit && i < archivosParaSubir.length -1)"""),format.raw/*709.54*/("""{"""),format.raw/*709.55*/("""
				"""),format.raw/*710.5*/("""allowSubmit = false;
			"""),format.raw/*711.4*/("""}"""),format.raw/*711.5*/("""
		"""),format.raw/*712.3*/("""}"""),format.raw/*712.4*/("""
		
		"""),format.raw/*714.3*/("""if (allowSubmit) """),format.raw/*714.20*/("""{"""),format.raw/*714.21*/("""
			"""),format.raw/*715.4*/("""if(tamanio > 200000000)"""),format.raw/*715.27*/("""{"""),format.raw/*715.28*/("""
				"""),format.raw/*716.5*/("""alert("Se permite máximo subir 200 MB,"
				+" lo que se intenta subir pesa: "+Math.round(tamanio/10000)/100+" MB");
				form.docAdjunto.value="";
			"""),format.raw/*719.4*/("""}"""),format.raw/*719.5*/("""else"""),format.raw/*719.9*/("""{"""),format.raw/*719.10*/("""
				"""),format.raw/*720.5*/("""alert("Documento subido con éxito");
				$("#txtBtn").text("Cambiar Documento");
			"""),format.raw/*722.4*/("""}"""),format.raw/*722.5*/("""
		"""),format.raw/*723.3*/("""}"""),format.raw/*723.4*/("""else"""),format.raw/*723.8*/("""{"""),format.raw/*723.9*/("""
			"""),format.raw/*724.4*/("""alert("Se permiten únicamente archivos con la extención: " 
			+ (extArray.join("  ")) + "\nPor favor, seleccione solo archivos con extenciones permitidas "
			+ "e intente de nuevo.");
			form.docAdjunto.value="";
		"""),format.raw/*728.3*/("""}"""),format.raw/*728.4*/("""
	"""),format.raw/*729.2*/("""}"""),format.raw/*729.3*/("""
		
"""),format.raw/*731.1*/("""</script>
	

"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,fecha:String,listOperadores:List[tables.OperadorServicio],listBodegas:List[List[String]],aux_huella:Long): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,fecha,listOperadores,listBodegas,aux_huella)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,String,List[tables.OperadorServicio],List[List[String]],Long) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,fecha,listOperadores,listBodegas,aux_huella) => apply(mapDiccionario,mapPermiso,userMnu,fecha,listOperadores,listBodegas,aux_huella)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuOdo/odoVentas.scala.html
                  HASH: 5ee7d51ecab5fc8be261e78fdd8e6fbbce9c561e
                  MATRIX: 1065->1|1368->211|1401->219|1417->227|1456->229|1484->231|2082->802|2113->812|2546->1218|2572->1223|3443->2067|3459->2074|3497->2090|3644->2210|3660->2217|3695->2231|4551->3060|4574->3074|4609->3088|4638->3089|12793->11217|12838->11245|12878->11246|12915->11255|12973->11285|12988->11290|13018->11298|13056->11308|13071->11313|13105->11325|13190->11382|13205->11387|13236->11396|13325->11457|13340->11462|13374->11474|13451->11520|13486->11527|14079->12092|14125->12116|14595->12558|14641->12582|14733->12647|14775->12672|14815->12673|14852->12682|14908->12710|14923->12715|14952->12722|14990->12732|15005->12737|15034->12744|15119->12801|15134->12806|15163->12813|15252->12874|15267->12879|15296->12886|15372->12931|15407->12938|16280->13783|16326->13807|17196->14649|17242->14673|18376->15776|18407->15779|18473->15817|18489->15823|18564->15876|18695->15978|18725->15979|18758->15984|18860->16058|18889->16059|19006->16147|19036->16148|19069->16153|19171->16227|19200->16228|19265->16264|19295->16265|19328->16270|19523->16437|19552->16438|19583->16441|19620->16450|19649->16451|19682->16456|19757->16503|19786->16504|19838->16527|19868->16528|19901->16533|20010->16614|20039->16615|20069->16617|20167->16686|20197->16687|20228->16690|20289->16722|20319->16723|20354->16730|20560->16907|20590->16908|20630->16919|20736->16996|20766->16997|20800->17003|20829->17004|20858->17005|20921->17039|20951->17040|20986->17047|21192->17224|21222->17225|21262->17236|21368->17313|21398->17314|21432->17320|21461->17321|21490->17322|21592->17396|21621->17397|21650->17398|21710->17429|21740->17430|21771->17433|21942->17575|21972->17576|22004->17580|22084->17631|22114->17632|22147->17637|22403->17865|22432->17866|22464->17870|22494->17871|22527->17876|22663->17984|22692->17985|22723->17988|22752->17989|22782->17991|22811->17992|22843->17996|22897->18021|22927->18022|22958->18025|23129->18167|23159->18168|23191->18172|23369->18322|23398->18323|23430->18327|23459->18328|23491->18332|23572->18384|23602->18385|23635->18390|23845->18572|23874->18573|23906->18577|23936->18578|23969->18583|24105->18691|24134->18692|24165->18695|24194->18696|24224->18698|24253->18699|24285->18703|24339->18728|24369->18729|24400->18732|24518->18821|24548->18822|24580->18826|24655->18872|24685->18873|24718->18878|24930->19062|24959->19063|24991->19067|25021->19068|25054->19073|25494->19485|25523->19486|25554->19489|25583->19490|25613->19492|25642->19493|25674->19497|25728->19522|25758->19523|25789->19526|25907->19615|25937->19616|25969->19620|26125->19748|26154->19749|26186->19753|26215->19754|26247->19758|26322->19804|26352->19805|26385->19810|26570->19967|26599->19968|26631->19972|26661->19973|26694->19978|27134->20390|27163->20391|27194->20394|27223->20395|27253->20397|27282->20398|27316->20404|27391->20450|27421->20451|27452->20454|28003->20977|28032->20978|28064->20982|28133->21022|28163->21023|28194->21026|28838->21642|28867->21643|28899->21647|28967->21686|28997->21687|29028->21690|29146->21779|29176->21780|29218->21793|29474->22020|29504->22021|29537->22026|29633->22094|29662->22095|29695->22100|29724->22101|29756->22105|29785->22106|29817->22110|29931->22195|29961->22196|29992->22199|30624->22802|30654->22803|30686->22807|30941->23034|30970->23035|31002->23039|31031->23040|31063->23044|31546->23499|31575->23500|31607->23504|31677->23546|31706->23547|31738->23551|31804->23588|31834->23589|31865->23592|31983->23681|32013->23682|32055->23695|32309->23920|32339->23921|32372->23926|32466->23992|32495->23993|32528->23998|32557->23999|32589->24003|32618->24004|32650->24008|32728->24057|32758->24058|32789->24061|33391->24635|33420->24636|33452->24640|33498->24657|33528->24658|33566->24668|33630->24703|33660->24704|33694->24710|33760->24748|33784->24762|33820->24776|33865->24793|33894->24794|33958->24829|33988->24830|34022->24836|34102->24888|34131->24889|34197->24926|34227->24927|34261->24933|34354->24998|34383->24999|34416->25003|34446->25004|34482->25012|34798->25300|34827->25301|34857->25303|34886->25304|34920->25310|34979->25340|35009->25341|35040->25344|35104->25379|35134->25380|35169->25387|35274->25464|35301->25469|35338->25478|35367->25479|35400->25483|35430->25484|35462->25488|35508->25506|35528->25516|35581->25547|35875->25812|35905->25813|35945->25824|36071->25922|36098->25927|36140->25940|36170->25941|36224->25966|36254->25967|36297->25981|36447->26103|36474->26108|36516->26121|36546->26122|36580->26128|36609->26129|36648->26140|36677->26141|36715->26151|36906->26313|36936->26314|36970->26320|37231->26552|37261->26553|37293->26557|37524->26759|37554->26760|37587->26765|37646->26795|37676->26796|37711->26803|37763->26827|37792->26828|37824->26832|37853->26833|37885->26837|37964->26887|37994->26888|38027->26893|38079->26917|38108->26918|38139->26921|38168->26922|38202->26928|38248->26945|38278->26946|38310->26950|38362->26973|38392->26974|38425->26979|38603->27129|38632->27130|38664->27134|38694->27135|38727->27140|38839->27224|38868->27225|38899->27228|38928->27229|38960->27233|38989->27234|39021->27238|39266->27455|39295->27456|39325->27458|39354->27459|39386->27463
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|47->16|47->16|56->25|56->25|77->46|77->46|77->46|79->48|79->48|79->48|98->67|98->67|98->67|98->67|284->253|284->253|284->253|285->254|285->254|285->254|285->254|285->254|285->254|285->254|286->255|286->255|286->255|287->256|287->256|287->256|289->258|290->259|305->274|305->274|315->284|315->284|319->288|319->288|319->288|320->289|320->289|320->289|320->289|320->289|320->289|320->289|321->290|321->290|321->290|322->291|322->291|322->291|324->293|325->294|346->315|346->315|366->335|366->335|402->371|405->374|407->376|407->376|407->376|409->378|409->378|410->379|412->381|412->381|414->383|414->383|415->384|417->386|417->386|421->390|421->390|422->391|429->398|429->398|430->399|431->400|431->400|432->401|435->404|435->404|437->406|437->406|438->407|443->412|443->412|444->413|449->418|449->418|450->419|450->419|450->419|451->420|457->426|457->426|458->427|459->428|459->428|460->429|460->429|460->429|461->430|461->430|462->431|468->437|468->437|469->438|470->439|470->439|471->440|471->440|471->440|474->443|474->443|474->443|476->445|476->445|477->446|479->448|479->448|480->449|480->449|480->449|481->450|485->454|485->454|485->454|485->454|486->455|488->457|488->457|489->458|489->458|490->459|490->459|492->461|492->461|492->461|493->462|495->464|495->464|496->465|499->468|499->468|499->468|499->468|500->469|500->469|500->469|501->470|504->473|504->473|504->473|504->473|505->474|507->476|507->476|508->477|508->477|509->478|509->478|511->480|511->480|511->480|512->481|514->483|514->483|515->484|515->484|515->484|516->485|520->489|520->489|520->489|520->489|521->490|530->499|530->499|531->500|531->500|532->501|532->501|534->503|534->503|534->503|535->504|537->506|537->506|538->507|541->510|541->510|541->510|541->510|542->511|542->511|542->511|543->512|546->515|546->515|546->515|546->515|547->516|556->525|556->525|557->526|557->526|558->527|558->527|561->530|561->530|561->530|562->531|572->541|572->541|574->543|574->543|574->543|575->544|588->557|588->557|590->559|590->559|590->559|591->560|593->562|593->562|594->563|601->570|601->570|602->571|603->572|603->572|604->573|604->573|605->574|605->574|607->576|607->576|607->576|608->577|620->589|620->589|621->590|626->595|626->595|626->595|626->595|627->596|635->604|635->604|637->606|638->607|638->607|640->609|640->609|640->609|641->610|643->612|643->612|644->613|651->620|651->620|652->621|653->622|653->622|654->623|654->623|655->624|655->624|657->626|657->626|657->626|658->627|672->641|672->641|674->643|674->643|674->643|676->645|676->645|676->645|677->646|677->646|677->646|677->646|678->647|678->647|678->647|678->647|679->648|680->649|680->649|680->649|680->649|681->650|682->651|682->651|682->651|682->651|684->653|693->662|693->662|694->663|694->663|697->666|697->666|697->666|698->667|698->667|698->667|699->668|700->669|700->669|701->670|701->670|701->670|701->670|702->671|702->671|702->671|702->671|708->677|708->677|709->678|710->679|710->679|711->680|711->680|711->680|711->680|712->681|713->682|713->682|714->683|714->683|715->684|715->684|717->686|717->686|721->690|722->691|722->691|724->693|731->700|731->700|732->701|735->704|735->704|736->705|736->705|736->705|737->706|738->707|738->707|739->708|739->708|740->709|740->709|740->709|741->710|742->711|742->711|743->712|743->712|745->714|745->714|745->714|746->715|746->715|746->715|747->716|750->719|750->719|750->719|750->719|751->720|753->722|753->722|754->723|754->723|754->723|754->723|755->724|759->728|759->728|760->729|760->729|762->731
                  -- GENERATED --
              */
          