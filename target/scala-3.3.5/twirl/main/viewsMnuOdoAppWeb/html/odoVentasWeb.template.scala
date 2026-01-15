
package viewsMnuOdoAppWeb.html

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

object odoVentasWeb extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template7[Map[String,String],Map[String,String],utilities.UserMnu,String,tables.OperadorServicio,List[List[String]],Long,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
fecha: String, operador: tables.OperadorServicio, listBodegas: List[List[String]], aux_huella: Long):play.twirl.api.HtmlFormat.Appendable = {
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
				<form id="grabarVenta" action="/odoVentasGrabarWeb/" method="POST" enctype="multipart/form-data">
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
						        	<input type="hidden" id="id_userMada" name="id_userMada" value=""""),_display_(/*43.81*/userMnu/*43.88*/.getId_usuario()),format.raw/*43.104*/("""">
						        		<div class="input-group input-group-sm">
										  <input class="form-control" type="text" value=""""),_display_(/*45.61*/userMnu/*45.68*/.getUserName()),format.raw/*45.82*/("""" readonly>
										</div>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>OPERADOR:</strong></td>
						        <td style="text-align:left" width="70%" colspan="4">
						        	<input type="hidden" name="id_operador" value=""""),_display_(/*52.64*/operador/*52.72*/.getId()),format.raw/*52.80*/("""" readonly>
									<input class="form-control" type="text" name="id_operador" value=""""),_display_(/*53.77*/operador/*53.85*/.getNombre()),format.raw/*53.97*/("""" readonly>
						        </td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>"""),_display_(/*57.47*/mapDiccionario/*57.61*/.get("BODEGA")),format.raw/*57.75*/(""" """),format.raw/*57.76*/("""(*):</strong></td>
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
								<td colspan="4" style="text-align:center;">
									<button type="button" class="btn btn-sm btn-success" style="font-size: 10px;"onclick="grabar();" >GRABAR</button>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<button type="button" class="btn btn-sm btn-warning" style="font-size: 10px;" onclick="location.href = '/odo'">SALIR</button>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<button type="button" class="btn btn-sm btn-info" style="font-size: 10px;" onclick="location.href = '/odoListarVentasWeb/0'">REVISAR</button>
								</td>
							</tr>
						</thead>
					</table>
					
					<font style="font-size: 12px;">(*) Campos Obligatorios</font>
				</form>
			</div>
		</div>
		
		<div id="modalListaBodegas" class="modal" role="dialog" data-backdrop="static" data-keyboard="false">
			<div class="modal-dialog modal-lg modal-dialog-scrollable" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title">Seleccionar """),_display_(/*226.49*/mapDiccionario("Bodega")),format.raw/*226.73*/("""/Proyecto</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body">
					<table id="tablaBodegas" class="table table-sm table-bordered table-condensed table-hover table-fluid">
				        <thead style="background-color: #eeeeee">
							<TR> 
								<th>"""),_display_(/*235.14*/mapDiccionario("Bodega")),format.raw/*235.38*/("""/Proyecto</th>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*239.9*/for(lista <- listBodegas) yield /*239.34*/{_display_(Seq[Any](format.raw/*239.35*/("""
								"""),format.raw/*240.9*/("""<TR onclick="selectBodega('"""),_display_(/*240.37*/lista/*240.42*/.get(0)),format.raw/*240.49*/("""', btoa('"""),_display_(/*240.59*/lista/*240.64*/.get(1)),format.raw/*240.71*/("""') )">
									<td style="text-align:left"><a href="#">"""),_display_(/*241.51*/lista/*241.56*/.get(1)),format.raw/*241.63*/("""</a></td>														
								</TR>
							""")))}),format.raw/*243.9*/("""
						"""),format.raw/*244.7*/("""</tbody>
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
					<div id="tablaListaEquipos">Debe seleccionar primero una """),_display_(/*265.64*/mapDiccionario("Bodega")),format.raw/*265.88*/("""/Proyecto</div>
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
					<div id="tablaListaServicios">Debe seleccionar primero una """),_display_(/*285.66*/mapDiccionario("Bodega")),format.raw/*285.90*/("""/Proyecto</div>
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
""")))}),format.raw/*321.2*/("""


"""),format.raw/*324.1*/("""<!-- CONFIGURA PAD -->

<script src=""""),_display_(/*326.15*/routes/*326.21*/.Assets.versioned("javascripts/signature_pad.min.js")),format.raw/*326.74*/(""""></script>
	<script>
		let signaturePad = new SignaturePad(document.getElementById('signature-pad'), """),format.raw/*328.81*/("""{"""),format.raw/*328.82*/("""
		  """),format.raw/*329.5*/("""backgroundColor: 'rgba(255, 255, 255, 0)',
		  penColor: 'rgb(0, 0, 0)'
		"""),format.raw/*331.3*/("""}"""),format.raw/*331.4*/(""");
		
		let signaturePad2 = new SignaturePad(document.getElementById('signature-pad2'), """),format.raw/*333.83*/("""{"""),format.raw/*333.84*/("""
		  """),format.raw/*334.5*/("""backgroundColor: 'rgba(255, 255, 255, 0)',
		  penColor: 'rgb(0, 0, 0)'
		"""),format.raw/*336.3*/("""}"""),format.raw/*336.4*/(""");
	</script>
	
	<style>
		.wrapper """),format.raw/*340.12*/("""{"""),format.raw/*340.13*/("""
		  """),format.raw/*341.5*/("""position: relative;
		  width: 350px;
		  height: 90px;
		  -moz-user-select: none;
		  -webkit-user-select: none;
		  -ms-user-select: none;
		  user-select: none;
		"""),format.raw/*348.3*/("""}"""),format.raw/*348.4*/("""
		"""),format.raw/*349.3*/("""/*
		img """),format.raw/*350.7*/("""{"""),format.raw/*350.8*/("""
		  """),format.raw/*351.5*/("""position: absolute;
		  left: 0;
		  top: 0;
		"""),format.raw/*354.3*/("""}"""),format.raw/*354.4*/("""*/
		
		.signature-pad """),format.raw/*356.18*/("""{"""),format.raw/*356.19*/("""
		  """),format.raw/*357.5*/("""position: absolute;
		  left: 0;
		  top: 0;
		  width:350px;
		  height:90px;
		"""),format.raw/*362.3*/("""}"""),format.raw/*362.4*/("""
	"""),format.raw/*363.2*/("""</style>
<!-- FIN PAD -->
	
<script>


	
	$(document).ready(function() """),format.raw/*370.31*/("""{"""),format.raw/*370.32*/("""

		"""),format.raw/*372.3*/("""$('#tablaBodegas').DataTable("""),format.raw/*372.32*/("""{"""),format.raw/*372.33*/("""
	    	"""),format.raw/*373.7*/(""""fixedHeader": true,
	    	"lengthMenu": false,
	    	"paging":   false,
	        "ordering": [['1','asc']],
	        "info":     false,
			"searching": true,
	    	"language": """),format.raw/*379.19*/("""{"""),format.raw/*379.20*/("""
	        	"""),format.raw/*380.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*381.10*/("""}"""),format.raw/*381.11*/("""
	    """),format.raw/*382.6*/("""}"""),format.raw/*382.7*/(""" """),format.raw/*382.8*/(""");
 		document.getElementById('mostrarmostrar').style.display="block";
	
	"""),format.raw/*385.2*/("""}"""),format.raw/*385.3*/(""" """),format.raw/*385.4*/(""");
	
	function validarLectIni()"""),format.raw/*387.27*/("""{"""),format.raw/*387.28*/("""
		"""),format.raw/*388.3*/("""let lecturaIni = $("#lecturaIni").val().replace(/,/g,'');
		let lecturaTer = $("#lecturaTer").val().replace(/,/g,'');
		if(lecturaTer!="0.00")"""),format.raw/*390.25*/("""{"""),format.raw/*390.26*/("""
			"""),format.raw/*391.4*/("""if(lecturaIni >= lecturaTer)"""),format.raw/*391.32*/("""{"""),format.raw/*391.33*/("""
				"""),format.raw/*392.5*/("""alertify.alert("La lectura inicial no puede ser menor o igual a la lectura de termino");
				$("#lecturaIni").val(formatStandar(0,2));
				$("#lecturaTer").val(formatStandar(0,2));
				$("#cantidad").val(formatStandar(0,2));
			"""),format.raw/*396.4*/("""}"""),format.raw/*396.5*/("""else"""),format.raw/*396.9*/("""{"""),format.raw/*396.10*/("""
				"""),format.raw/*397.5*/("""let dif = parseFloat(lecturaTer) - parseFloat(lecturaIni);
				$("#cantidad").val(formatStandar(dif,2));
			"""),format.raw/*399.4*/("""}"""),format.raw/*399.5*/("""
		"""),format.raw/*400.3*/("""}"""),format.raw/*400.4*/("""
	"""),format.raw/*401.2*/("""}"""),format.raw/*401.3*/("""
	
	"""),format.raw/*403.2*/("""function validarLectTer()"""),format.raw/*403.27*/("""{"""),format.raw/*403.28*/("""
		"""),format.raw/*404.3*/("""let lecturaIni = $("#lecturaIni").val().replace(/,/g,'');
		let lecturaTer = $("#lecturaTer").val().replace(/,/g,'');
		if(lecturaIni=="0.00")"""),format.raw/*406.25*/("""{"""),format.raw/*406.26*/("""
			"""),format.raw/*407.4*/("""alertify.alert("Primero debe ingresar la lectura inicial");
			$("#lecturaTer").val(formatStandar(0,2));
			$("#cantidad").val(formatStandar(0,2));
		"""),format.raw/*410.3*/("""}"""),format.raw/*410.4*/("""else"""),format.raw/*410.8*/("""{"""),format.raw/*410.9*/("""
			"""),format.raw/*411.4*/("""if(lecturaIni >= lecturaTer)"""),format.raw/*411.32*/("""{"""),format.raw/*411.33*/("""
				"""),format.raw/*412.5*/("""alertify.alert("La lectura inicial no puede ser menor o igual a la lectura de termino");
				$("#lecturaTer").val(formatStandar(0,2));
				$("#cantidad").val(formatStandar(0,2));
			"""),format.raw/*415.4*/("""}"""),format.raw/*415.5*/("""else"""),format.raw/*415.9*/("""{"""),format.raw/*415.10*/("""
				"""),format.raw/*416.5*/("""let dif = parseFloat(lecturaTer) - parseFloat(lecturaIni);
				$("#cantidad").val(formatStandar(dif,2));
			"""),format.raw/*418.4*/("""}"""),format.raw/*418.5*/("""
		"""),format.raw/*419.3*/("""}"""),format.raw/*419.4*/("""
	"""),format.raw/*420.2*/("""}"""),format.raw/*420.3*/("""
	
	"""),format.raw/*422.2*/("""function validarHoraIni()"""),format.raw/*422.27*/("""{"""),format.raw/*422.28*/("""
		"""),format.raw/*423.3*/("""let horaIni = $("#horaIni").val();
		let horaTer = $("#horaTer").val();
		if(horaTer!="")"""),format.raw/*425.18*/("""{"""),format.raw/*425.19*/("""
			"""),format.raw/*426.4*/("""if(horaIni >= horaTer)"""),format.raw/*426.26*/("""{"""),format.raw/*426.27*/("""
				"""),format.raw/*427.5*/("""alertify.alert("La hora inicial no puede ser menor o igual a la hora de termino");
				$("#horaIni").val("");
				$("#horaTer").val("");
				$("#cantidad").val(formatStandar(0,2));
			"""),format.raw/*431.4*/("""}"""),format.raw/*431.5*/("""else"""),format.raw/*431.9*/("""{"""),format.raw/*431.10*/("""
				"""),format.raw/*432.5*/("""let hrIni = parseInt(horaIni.substr(0,2));
				let minIni = parseInt(horaIni.substr(3,2));
				let ini = parseFloat(hrIni) + (parseFloat(minIni)/60);
				let hrTer = parseInt(horaTer.substr(0,2));
				let minTer = parseInt(horaTer.substr(3,2));
				let ter = parseFloat(hrTer) + (parseFloat(minTer)/60);
				let dif = ter -ini;
				dif = Math.round(dif*100)/100;
				$("#cantidad").val(formatStandar(dif,2));
			"""),format.raw/*441.4*/("""}"""),format.raw/*441.5*/("""
		"""),format.raw/*442.3*/("""}"""),format.raw/*442.4*/("""
	"""),format.raw/*443.2*/("""}"""),format.raw/*443.3*/("""
	
	"""),format.raw/*445.2*/("""function validarHoraTer()"""),format.raw/*445.27*/("""{"""),format.raw/*445.28*/("""
		"""),format.raw/*446.3*/("""let horaIni = $("#horaIni").val();
		let horaTer = $("#horaTer").val();
		if(horaIni=="")"""),format.raw/*448.18*/("""{"""),format.raw/*448.19*/("""
			"""),format.raw/*449.4*/("""alertify.alert("Primero debe ingresar la hora inicial");
			$("#horaTer").val("");
			$("#cantidad").val(formatStandar(0,2));
		"""),format.raw/*452.3*/("""}"""),format.raw/*452.4*/("""else"""),format.raw/*452.8*/("""{"""),format.raw/*452.9*/("""
			"""),format.raw/*453.4*/("""if(horaIni >= horaTer)"""),format.raw/*453.26*/("""{"""),format.raw/*453.27*/("""
				"""),format.raw/*454.5*/("""alertify.alert("La hora inicial no puede ser menor o igual a la hora de termino");
				$("#horaTer").val("");
				$("#cantidad").val(formatStandar(0,2));
			"""),format.raw/*457.4*/("""}"""),format.raw/*457.5*/("""else"""),format.raw/*457.9*/("""{"""),format.raw/*457.10*/("""
				"""),format.raw/*458.5*/("""let hrIni = parseInt(horaIni.substr(0,2));
				let minIni = parseInt(horaIni.substr(3,2));
				let ini = parseFloat(hrIni) + (parseFloat(minIni)/60);
				let hrTer = parseInt(horaTer.substr(0,2));
				let minTer = parseInt(horaTer.substr(3,2));
				let ter = parseFloat(hrTer) + (parseFloat(minTer)/60);
				let dif = ter -ini;
				dif = Math.round(dif*100)/100;
				$("#cantidad").val(formatStandar(dif,2));
			"""),format.raw/*467.4*/("""}"""),format.raw/*467.5*/("""
		"""),format.raw/*468.3*/("""}"""),format.raw/*468.4*/("""
	"""),format.raw/*469.2*/("""}"""),format.raw/*469.3*/("""

	"""),format.raw/*471.2*/("""function selectBodega(id_bodega, bodega)"""),format.raw/*471.42*/("""{"""),format.raw/*471.43*/("""
		"""),format.raw/*472.3*/("""bodega = atob(bodega);
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
	"""),format.raw/*485.2*/("""}"""),format.raw/*485.3*/("""
	
	"""),format.raw/*487.2*/("""function llenaTablaServicios(id_bodega)"""),format.raw/*487.41*/("""{"""),format.raw/*487.42*/("""
		"""),format.raw/*488.3*/("""var formData = new FormData();
		formData.append("id_bodegaEmpresa",id_bodega);
		$.ajax("""),format.raw/*490.10*/("""{"""),format.raw/*490.11*/("""
            """),format.raw/*491.13*/("""url: "/odoListaServiciosAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(rs)"""),format.raw/*498.29*/("""{"""),format.raw/*498.30*/("""
				"""),format.raw/*499.5*/("""document.getElementById("tablaListaServicios").innerHTML=rs;
	     	"""),format.raw/*500.8*/("""}"""),format.raw/*500.9*/("""
	  	"""),format.raw/*501.5*/("""}"""),format.raw/*501.6*/(""");
	"""),format.raw/*502.2*/("""}"""),format.raw/*502.3*/("""
	
	"""),format.raw/*504.2*/("""function selectServicio(id_servicio, servicio, unidad, id_cotiOdo, equipo, id_equipo)"""),format.raw/*504.87*/("""{"""),format.raw/*504.88*/("""
		"""),format.raw/*505.3*/("""servicio = atob(servicio);
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
		if(id_equipo != "0")"""),format.raw/*517.23*/("""{"""),format.raw/*517.24*/("""
			"""),format.raw/*518.4*/("""$('#id_equipo').val(id_equipo);
			document.getElementById("nombreEquipo").innerHTML=
					"<div class='input-group input-group-sm'>"+
					"<input class='form-control' type='text' value='"+equipo+"' readonly>"+
					"</div>";
		"""),format.raw/*523.3*/("""}"""),format.raw/*523.4*/("""else"""),format.raw/*523.8*/("""{"""),format.raw/*523.9*/("""
			"""),format.raw/*524.4*/("""$('#id_equipo').val("0");
			document.getElementById("nombreEquipo").innerHTML=
					"<div class='input-group input-group-sm'>"+
					"<input class=\"form-control\" type=\"text\" onclick=\"$('#modalListaEquipos').modal('show');\" value=\"Sin equipo asociado\" readonly>"+
					"<div class=\"input-group-append\">"+
					"<span class=\"input-group-text\" onclick=\"$('#modalListaEquipos').modal('show');\">Buscar</span>"+
					"</div>"+
					"</div>";
		"""),format.raw/*532.3*/("""}"""),format.raw/*532.4*/("""

		"""),format.raw/*534.3*/("""$('#modalListaServicios').modal('hide');
	"""),format.raw/*535.2*/("""}"""),format.raw/*535.3*/("""
	
	"""),format.raw/*537.2*/("""function llenaTablaEquipos(id_bodega)"""),format.raw/*537.39*/("""{"""),format.raw/*537.40*/("""
		"""),format.raw/*538.3*/("""var formData = new FormData();
		formData.append("id_bodegaEmpresa",id_bodega);
		$.ajax("""),format.raw/*540.10*/("""{"""),format.raw/*540.11*/("""
            """),format.raw/*541.13*/("""url: "/odoListaEquiposAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(rs)"""),format.raw/*548.29*/("""{"""),format.raw/*548.30*/("""
				"""),format.raw/*549.5*/("""document.getElementById("tablaListaEquipos").innerHTML=rs;
	     	"""),format.raw/*550.8*/("""}"""),format.raw/*550.9*/("""
	  	"""),format.raw/*551.5*/("""}"""),format.raw/*551.6*/(""");
	"""),format.raw/*552.2*/("""}"""),format.raw/*552.3*/("""
	
	"""),format.raw/*554.2*/("""function selectEquipo(id_equipo, equipo)"""),format.raw/*554.42*/("""{"""),format.raw/*554.43*/("""
		"""),format.raw/*555.3*/("""equipo = atob(equipo);
		$('#id_equipo').val(id_equipo);
		document.getElementById("nombreEquipo").innerHTML=
			"<div class='input-group input-group-sm'>"+
			  "<input class='form-control' type='text' onclick=\"$('#modalListaEquipos').modal('show');\" value='"+equipo+"' readonly>"+
			  "<div class='input-group-append'>"+
			    "<span class='input-group-text' onclick=\"$('#modalListaEquipos').modal('show');\">Buscar</span>"+
			  "</div>"+
			"</div>";
		$('#modalListaEquipos').modal('hide');
	"""),format.raw/*565.2*/("""}"""),format.raw/*565.3*/("""
	
	"""),format.raw/*567.2*/("""function grabar()"""),format.raw/*567.19*/("""{"""),format.raw/*567.20*/("""
		  
		  """),format.raw/*569.5*/("""if($('#id_bodegaEmpresa').val()==0)"""),format.raw/*569.40*/("""{"""),format.raw/*569.41*/("""
			  """),format.raw/*570.6*/("""alertify.alert('Debe seleccionar una """),_display_(/*570.44*/mapDiccionario/*570.58*/.get("Bodega")),format.raw/*570.72*/("""/Proyecto');
		  """),format.raw/*571.5*/("""}"""),format.raw/*571.6*/("""else if($('#id_servicio').val()==0)"""),format.raw/*571.41*/("""{"""),format.raw/*571.42*/("""
			  """),format.raw/*572.6*/("""alertify.alert('Debe seleccionar un Servicio');
		  """),format.raw/*573.5*/("""}"""),format.raw/*573.6*/("""else if($('#cantidad').val()=="0.00")"""),format.raw/*573.43*/("""{"""),format.raw/*573.44*/("""
			  """),format.raw/*574.6*/("""alertify.alert('Debe ingresar una cantidad mayor que cero');
		  """),format.raw/*575.5*/("""}"""),format.raw/*575.6*/("""else"""),format.raw/*575.10*/("""{"""),format.raw/*575.11*/("""
			
			"""),format.raw/*577.4*/("""let data = signaturePad.toDataURL('image/png');
			let encodedImage = data.split(",");
			$("#firma").val(encodedImage[1]);
			
			data = signaturePad2.toDataURL('image/png');
			encodedImage = data.split(",");
			$("#firma2").val(encodedImage[1]);
			
			$("#grabarVenta").submit();
		  """),format.raw/*586.5*/("""}"""),format.raw/*586.6*/("""
	"""),format.raw/*587.2*/("""}"""),format.raw/*587.3*/("""
	
	
	"""),format.raw/*590.2*/("""function actualizaFechaReal() """),format.raw/*590.32*/("""{"""),format.raw/*590.33*/("""
		"""),format.raw/*591.3*/("""if(!isValidDate($('#fecha').val()))"""),format.raw/*591.38*/("""{"""),format.raw/*591.39*/("""
    		"""),format.raw/*592.7*/("""alertify.alert("Debe ingresar una fecha valida");
       		$('#fecha').val(""""),_display_(/*593.28*/fecha),format.raw/*593.33*/("""");
    	"""),format.raw/*594.6*/("""}"""),format.raw/*594.7*/("""else"""),format.raw/*594.11*/("""{"""),format.raw/*594.12*/("""
    		"""),format.raw/*595.7*/("""var fechaMas = new Date();
        	var fechaMenos = new Date();
        	var fecha = new Date($('#fecha').val());
        	fechaMas.setDate(fechaMas.getDate() + 0);
        	fechaMenos.setDate(fechaMenos.getDate() - 60);
        	if(fecha>fechaMas)"""),format.raw/*600.28*/("""{"""),format.raw/*600.29*/("""
        		"""),format.raw/*601.11*/("""alertify.alert("La fecha no puede ser posterior a la fecha actual.");
        		$('#fecha').val('"""),_display_(/*602.29*/fecha),format.raw/*602.34*/("""');
        	"""),format.raw/*603.10*/("""}"""),format.raw/*603.11*/("""else if(fecha<fechaMenos)"""),format.raw/*603.36*/("""{"""),format.raw/*603.37*/("""
           		"""),format.raw/*604.14*/("""alertify.alert("La fecha no puede tener mas de 60 d&iacute;as de antiguedad.");
           		$('#fecha').val('"""),_display_(/*605.32*/fecha),format.raw/*605.37*/("""');
        	"""),format.raw/*606.10*/("""}"""),format.raw/*606.11*/("""
    	"""),format.raw/*607.6*/("""}"""),format.raw/*607.7*/("""
    	
    """),format.raw/*609.5*/("""}"""),format.raw/*609.6*/(""";
    
    let extArray = new Array(".gif", ".jpg", ".png", ".jpeg", ".pdf", ".xls", ".doc", ".xlsx", ".docx", ".tar", ".zip", ".rar");
	const LimitAttach = (form, file) => """),format.raw/*612.38*/("""{"""),format.raw/*612.39*/("""
		
		"""),format.raw/*614.3*/("""if (!file) return;
		
		const $inputArchivos = document.querySelector("#docAdjunto");
		const archivosParaSubir = $inputArchivos.files;
		let tamanio = 0;
		let allowSubmit = false;
		
		for(let i=0; i<archivosParaSubir.length; i++)"""),format.raw/*621.48*/("""{"""),format.raw/*621.49*/("""
			"""),format.raw/*622.4*/("""tamanio += archivosParaSubir[i].size;
			let nombre = archivosParaSubir[i].name;
			let extencion = nombre.substring(nombre.lastIndexOf(".")).toLowerCase();
			for (let j = 0; j < extArray.length; j++) """),format.raw/*625.46*/("""{"""),format.raw/*625.47*/("""
				"""),format.raw/*626.5*/("""if (extArray[j] == extencion) """),format.raw/*626.35*/("""{"""),format.raw/*626.36*/(""" 
					"""),format.raw/*627.6*/("""allowSubmit = true;
				"""),format.raw/*628.5*/("""}"""),format.raw/*628.6*/("""
			"""),format.raw/*629.4*/("""}"""),format.raw/*629.5*/("""
			"""),format.raw/*630.4*/("""if(allowSubmit && i < archivosParaSubir.length -1)"""),format.raw/*630.54*/("""{"""),format.raw/*630.55*/("""
				"""),format.raw/*631.5*/("""allowSubmit = false;
			"""),format.raw/*632.4*/("""}"""),format.raw/*632.5*/("""
		"""),format.raw/*633.3*/("""}"""),format.raw/*633.4*/("""
		
		"""),format.raw/*635.3*/("""if (allowSubmit) """),format.raw/*635.20*/("""{"""),format.raw/*635.21*/("""
			"""),format.raw/*636.4*/("""if(tamanio > 200000000)"""),format.raw/*636.27*/("""{"""),format.raw/*636.28*/("""
				"""),format.raw/*637.5*/("""alert("Se permite máximo subir 200 MB,"
				+" lo que se intenta subir pesa: "+Math.round(tamanio/10000)/100+" MB");
				form.docAdjunto.value="";
			"""),format.raw/*640.4*/("""}"""),format.raw/*640.5*/("""
		"""),format.raw/*641.3*/("""}"""),format.raw/*641.4*/("""else"""),format.raw/*641.8*/("""{"""),format.raw/*641.9*/("""
			"""),format.raw/*642.4*/("""alert("Se permiten únicamente archivos con la extención: " 
			+ (extArray.join("  ")) + "\nPor favor, seleccione solo archivos con extenciones permitidas "
			+ "e intente de nuevo.");
			form.docAdjunto.value="";
		"""),format.raw/*646.3*/("""}"""),format.raw/*646.4*/("""
	"""),format.raw/*647.2*/("""}"""),format.raw/*647.3*/("""
	
		
		
		
"""),format.raw/*652.1*/("""</script>
	

"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,fecha:String,operador:tables.OperadorServicio,listBodegas:List[List[String]],aux_huella:Long): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,fecha,operador,listBodegas,aux_huella)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,String,tables.OperadorServicio,List[List[String]],Long) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,fecha,operador,listBodegas,aux_huella) => apply(mapDiccionario,mapPermiso,userMnu,fecha,operador,listBodegas,aux_huella)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuOdoAppWeb/odoVentasWeb.scala.html
                  HASH: b3361835a16f03e2a798966da3e29ff85fc526ae
                  MATRIX: 1068->1|1359->199|1392->207|1408->215|1447->217|1475->219|2046->763|2077->773|2510->1179|2536->1184|3358->1979|3374->1986|3412->2002|3559->2122|3575->2129|3610->2143|3910->2416|3927->2424|3956->2432|4071->2520|4088->2528|4121->2540|4251->2643|4274->2657|4309->2671|4338->2672|12052->10358|12098->10382|12542->10798|12588->10822|12680->10887|12722->10912|12762->10913|12799->10922|12855->10950|12870->10955|12899->10962|12937->10972|12952->10977|12981->10984|13066->11041|13081->11046|13110->11053|13187->11099|13222->11106|14095->11951|14141->11975|15011->12817|15057->12841|16191->13944|16222->13947|16288->13985|16304->13991|16379->14044|16510->14146|16540->14147|16573->14152|16675->14226|16704->14227|16821->14315|16851->14316|16884->14321|16986->14395|17015->14396|17080->14432|17110->14433|17143->14438|17338->14605|17367->14606|17398->14609|17435->14618|17464->14619|17497->14624|17572->14671|17601->14672|17653->14695|17683->14696|17716->14701|17825->14782|17854->14783|17884->14785|17984->14856|18014->14857|18046->14861|18104->14890|18134->14891|18169->14898|18375->15075|18405->15076|18445->15087|18551->15164|18581->15165|18615->15171|18644->15172|18673->15173|18775->15247|18804->15248|18833->15249|18893->15280|18923->15281|18954->15284|19125->15426|19155->15427|19187->15431|19244->15459|19274->15460|19307->15465|19563->15693|19592->15694|19624->15698|19654->15699|19687->15704|19823->15812|19852->15813|19883->15816|19912->15817|19942->15819|19971->15820|20003->15824|20057->15849|20087->15850|20118->15853|20289->15995|20319->15996|20351->16000|20529->16150|20558->16151|20590->16155|20619->16156|20651->16160|20708->16188|20738->16189|20771->16194|20981->16376|21010->16377|21042->16381|21072->16382|21105->16387|21241->16495|21270->16496|21301->16499|21330->16500|21360->16502|21389->16503|21421->16507|21475->16532|21505->16533|21536->16536|21654->16625|21684->16626|21716->16630|21767->16652|21797->16653|21830->16658|22042->16842|22071->16843|22103->16847|22133->16848|22166->16853|22606->17265|22635->17266|22666->17269|22695->17270|22725->17272|22754->17273|22786->17277|22840->17302|22870->17303|22901->17306|23019->17395|23049->17396|23081->17400|23237->17528|23266->17529|23298->17533|23327->17534|23359->17538|23410->17560|23440->17561|23473->17566|23658->17723|23687->17724|23719->17728|23749->17729|23782->17734|24222->18146|24251->18147|24282->18150|24311->18151|24341->18153|24370->18154|24401->18157|24470->18197|24500->18198|24531->18201|25175->18817|25204->18818|25236->18822|25304->18861|25334->18862|25365->18865|25483->18954|25513->18955|25555->18968|25811->19195|25841->19196|25874->19201|25970->19269|25999->19270|26032->19275|26061->19276|26093->19280|26122->19281|26154->19285|26268->19370|26298->19371|26329->19374|26961->19977|26991->19978|27023->19982|27280->20211|27309->20212|27341->20216|27370->20217|27402->20221|27883->20674|27912->20675|27944->20679|28014->20721|28043->20722|28075->20726|28141->20763|28171->20764|28202->20767|28320->20856|28350->20857|28392->20870|28646->21095|28676->21096|28709->21101|28803->21167|28832->21168|28865->21173|28894->21174|28926->21178|28955->21179|28987->21183|29056->21223|29086->21224|29117->21227|29647->21729|29676->21730|29708->21734|29754->21751|29784->21752|29822->21762|29886->21797|29916->21798|29950->21804|30016->21842|30040->21856|30076->21870|30121->21887|30150->21888|30214->21923|30244->21924|30278->21930|30358->21982|30387->21983|30453->22020|30483->22021|30517->22027|30610->22092|30639->22093|30672->22097|30702->22098|30738->22106|31054->22394|31083->22395|31113->22397|31142->22398|31176->22404|31235->22434|31265->22435|31296->22438|31360->22473|31390->22474|31425->22481|31530->22558|31557->22563|31594->22572|31623->22573|31656->22577|31686->22578|31721->22585|31999->22834|32029->22835|32069->22846|32195->22944|32222->22949|32264->22962|32294->22963|32348->22988|32378->22989|32421->23003|32560->23114|32587->23119|32629->23132|32659->23133|32693->23139|32722->23140|32761->23151|32790->23152|32992->23325|33022->23326|33056->23332|33317->23564|33347->23565|33379->23569|33610->23771|33640->23772|33673->23777|33732->23807|33762->23808|33797->23815|33849->23839|33878->23840|33910->23844|33939->23845|33971->23849|34050->23899|34080->23900|34113->23905|34165->23929|34194->23930|34225->23933|34254->23934|34288->23940|34334->23957|34364->23958|34396->23962|34448->23985|34478->23986|34511->23991|34689->24141|34718->24142|34749->24145|34778->24146|34810->24150|34839->24151|34871->24155|35116->24372|35145->24373|35175->24375|35204->24376|35244->24388
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|47->16|47->16|56->25|56->25|74->43|74->43|74->43|76->45|76->45|76->45|83->52|83->52|83->52|84->53|84->53|84->53|88->57|88->57|88->57|88->57|257->226|257->226|266->235|266->235|270->239|270->239|270->239|271->240|271->240|271->240|271->240|271->240|271->240|271->240|272->241|272->241|272->241|274->243|275->244|296->265|296->265|316->285|316->285|352->321|355->324|357->326|357->326|357->326|359->328|359->328|360->329|362->331|362->331|364->333|364->333|365->334|367->336|367->336|371->340|371->340|372->341|379->348|379->348|380->349|381->350|381->350|382->351|385->354|385->354|387->356|387->356|388->357|393->362|393->362|394->363|401->370|401->370|403->372|403->372|403->372|404->373|410->379|410->379|411->380|412->381|412->381|413->382|413->382|413->382|416->385|416->385|416->385|418->387|418->387|419->388|421->390|421->390|422->391|422->391|422->391|423->392|427->396|427->396|427->396|427->396|428->397|430->399|430->399|431->400|431->400|432->401|432->401|434->403|434->403|434->403|435->404|437->406|437->406|438->407|441->410|441->410|441->410|441->410|442->411|442->411|442->411|443->412|446->415|446->415|446->415|446->415|447->416|449->418|449->418|450->419|450->419|451->420|451->420|453->422|453->422|453->422|454->423|456->425|456->425|457->426|457->426|457->426|458->427|462->431|462->431|462->431|462->431|463->432|472->441|472->441|473->442|473->442|474->443|474->443|476->445|476->445|476->445|477->446|479->448|479->448|480->449|483->452|483->452|483->452|483->452|484->453|484->453|484->453|485->454|488->457|488->457|488->457|488->457|489->458|498->467|498->467|499->468|499->468|500->469|500->469|502->471|502->471|502->471|503->472|516->485|516->485|518->487|518->487|518->487|519->488|521->490|521->490|522->491|529->498|529->498|530->499|531->500|531->500|532->501|532->501|533->502|533->502|535->504|535->504|535->504|536->505|548->517|548->517|549->518|554->523|554->523|554->523|554->523|555->524|563->532|563->532|565->534|566->535|566->535|568->537|568->537|568->537|569->538|571->540|571->540|572->541|579->548|579->548|580->549|581->550|581->550|582->551|582->551|583->552|583->552|585->554|585->554|585->554|586->555|596->565|596->565|598->567|598->567|598->567|600->569|600->569|600->569|601->570|601->570|601->570|601->570|602->571|602->571|602->571|602->571|603->572|604->573|604->573|604->573|604->573|605->574|606->575|606->575|606->575|606->575|608->577|617->586|617->586|618->587|618->587|621->590|621->590|621->590|622->591|622->591|622->591|623->592|624->593|624->593|625->594|625->594|625->594|625->594|626->595|631->600|631->600|632->601|633->602|633->602|634->603|634->603|634->603|634->603|635->604|636->605|636->605|637->606|637->606|638->607|638->607|640->609|640->609|643->612|643->612|645->614|652->621|652->621|653->622|656->625|656->625|657->626|657->626|657->626|658->627|659->628|659->628|660->629|660->629|661->630|661->630|661->630|662->631|663->632|663->632|664->633|664->633|666->635|666->635|666->635|667->636|667->636|667->636|668->637|671->640|671->640|672->641|672->641|672->641|672->641|673->642|677->646|677->646|678->647|678->647|683->652
                  -- GENERATED --
              */
          