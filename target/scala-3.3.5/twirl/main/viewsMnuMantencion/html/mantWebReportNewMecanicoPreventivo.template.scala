
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

object mantWebReportNewMecanicoPreventivo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template10[Map[String,String],String,List[tables.MantEstadoEnObra],tables.MantOperadorMecanico,List[tables.TipoMantencion],List[tables.MantEstadoEnTaller],List[tables.MantActividad],List[tables.MantTipoActividad],List[tables.MantItemIntervenido],List[tables.MantComponente],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], fecha: String, listEstaOperacional: List[tables.MantEstadoEnObra], operMec: tables.MantOperadorMecanico, listTipoMantencion: List[tables.TipoMantencion], listEstadoEnTaller: List[tables.MantEstadoEnTaller], listActividad: List[tables.MantActividad], listTipoActividad: List[tables.MantTipoActividad], listItemIntervenido: List[tables.MantItemIntervenido], listComponentes: List[tables.MantComponente]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""<div id="enCarga" class="blocker" style="display: none;"><br><br><br><br><br><br><h1>En proceso.....<div id="msgEspera"></div></h1></div>
		<table class="table table-sm table-bordered table-condensed table-fluid">
			<thead>
				<tr>
					<td style="text-align:left;"><strong>EQUIPO (*):</strong></td>
			        <td style="text-align:left; width: 70%"  colspan="4">
			        	<input type="hidden" id="id_equipo_mecP" name="id_equipo_mecP" value="0">
			        	<div id="nombreEquipo_mecP">
			        		<div class="input-group input-group-sm">
							  <input class="form-control" type="text" 
								onclick="$('#modalListaEquipos_mec').modal('show');" 
								id="valNomEquip_mecP"
								value="--- select ---" readonly>
							  <div class="input-group-append">
							    <span class="input-group-text" onclick="$('#modalListaEquipos_mec').modal('show');">Buscar</span>
							  </div>
							</div> 
	    				</div>
			        </td>
				</tr>
				<tr>
					<td style="text-align:left;"><strong>TIPO DE PLAN:</strong></td>
			        <td style="text-align:left; width: 70%"  colspan="4">
						<input type="hidden" id="id_tipoPlan_mecP" name="id_tipoPlan_mecP" value="0">
						<input class="form-control" id="tipoPlan_mecP" type="text" value="" readonly>
			        </td>
				</tr>
				<tr>
					<td style="text-align:left;"><strong>"""),_display_(/*30.44*/mapDiccionario/*30.58*/.get("BODEGA")),format.raw/*30.72*/("""/PROYECTO (*):</strong></td>
			        <td style="text-align:left; width: 70%"  colspan="4">
			        	<input type="hidden" id="id_bodega_mecP" name="id_bodega_mecP" value="0">
			        	<div id="nombreBodega_mecP">
			        		<div class="input-group input-group-sm">
							  <input class="form-control" type="text" 
								onclick="$('#modalListaBodegas').modal('show');" 
								id="valNomBod_mecP"
								value="--- select ---" readonly>
							  <div class="input-group-append">
							    <span class="input-group-text" onclick="$('#modalListaBodegas').modal('show');">Buscar</span>
							  </div>
							</div> 
	    				</div>
			        </td>
				</tr>
				<tr>
					<td style="text-align:left;"><strong>TIPO """),_display_(/*47.49*/mapDiccionario/*47.63*/.get("BODEGA")),format.raw/*47.77*/("""/PROYECTO:</strong></td>
			        <td style="text-align:left; width: 70%"  colspan="4">
						<input class="form-control" id="tipoBodega_mecP" type="text" value="" readonly>
			        </td>
				</tr>
				<tr>
					<td style="text-align:left;"><strong>ESTADO OPERACIONAL (*):</strong></td>
			        <td style="text-align:left; width: 70%"  colspan="4">
			        	<select class="form-control form-control-sm" 
							id="id_mantEstadoOperacionalP" 
							name="id_mantEstadoOperacionalP"
							required disabled>
				        		<option class="blank" value="0">-- Select --</option>
			            		"""),_display_(/*60.19*/for(lista <- listEstaOperacional) yield /*60.52*/{_display_(Seq[Any](format.raw/*60.53*/("""
			                		"""),format.raw/*61.22*/("""<option value=""""),_display_(/*61.38*/lista/*61.43*/.id),format.raw/*61.46*/("""" >"""),_display_(/*61.50*/lista/*61.55*/.nombre),format.raw/*61.62*/("""</option>
								""")))}),format.raw/*62.10*/("""
				        """),format.raw/*63.13*/("""</select> 
			        </td>
				</tr>
				<tr>
					<td style="text-align:left;"><strong>ESTADO EN TALLER (*):</strong></td>
			        <td style="text-align:left; width: 70%"  colspan="4">
			        	<select class="form-control form-control-sm" 
							id="id_mantEstadoEnTallerP" 
							name="id_mantEstadoEnTallerP"
							required disabled>
				        		<option class="blank" value="0">-- Select --</option>
			            		"""),_display_(/*74.19*/for(lista <- listEstadoEnTaller) yield /*74.51*/{_display_(Seq[Any](format.raw/*74.52*/("""
			                		"""),format.raw/*75.22*/("""<option value=""""),_display_(/*75.38*/lista/*75.43*/.id),format.raw/*75.46*/("""" >"""),_display_(/*75.50*/lista/*75.55*/.nombre),format.raw/*75.62*/("""</option>
								""")))}),format.raw/*76.10*/("""
				        """),format.raw/*77.13*/("""</select> 
			        </td>
				</tr>
				<tr>
					<td style="text-align:left;"><strong>ACTIVIDAD (*):</strong></td>
			        <td style="text-align:left; width: 70%"  colspan="4">
			        	<select class="form-control form-control-sm" 
							id="id_mantActividadP" 
							name="id_mantActividadP"
							required disabled>
				        		<option class="blank" value="0">-- Select --</option>
			            		"""),_display_(/*88.19*/for(lista <- listActividad) yield /*88.46*/{_display_(Seq[Any](format.raw/*88.47*/("""
			                		"""),format.raw/*89.22*/("""<option value=""""),_display_(/*89.38*/lista/*89.43*/.id),format.raw/*89.46*/("""" >"""),_display_(/*89.50*/lista/*89.55*/.nombre),format.raw/*89.62*/("""</option>
								""")))}),format.raw/*90.10*/("""
				        """),format.raw/*91.13*/("""</select> 
			        </td>
				</tr>
				<tr>
					<td style="text-align:left;"><strong>TIPO DE ACTIVIDAD (*):</strong></td>
			        <td style="text-align:left; width: 70%"  colspan="4">
			        	<select class="form-control form-control-sm" 
							id="id_mantTipoActividadP" 
							name="id_mantTipoActividadP"
							required disabled>
				        		<option class="blank" value="0">-- Select --</option>
			            		"""),_display_(/*102.19*/for(lista <- listTipoActividad) yield /*102.50*/{_display_(Seq[Any](format.raw/*102.51*/("""
			                		"""),format.raw/*103.22*/("""<option value=""""),_display_(/*103.38*/lista/*103.43*/.id),format.raw/*103.46*/("""" >"""),_display_(/*103.50*/lista/*103.55*/.nombre),format.raw/*103.62*/("""</option>
								""")))}),format.raw/*104.10*/("""
				        """),format.raw/*105.13*/("""</select> 
			        </td>
				</tr>
				<tr>
					<td style="text-align:left;"><strong>ITEM INTERVENIDO (*):</strong></td>
			        <td style="text-align:left; width: 70%"  colspan="4">
			        	<select class="form-control form-control-sm" 
							id="id_mantItemIntervenidoP" 
							name="id_mantItemIntervenidoP"
							required disabled>
				        		<option class="blank" value="0">-- Select --</option>
			            		"""),_display_(/*116.19*/for(lista <- listComponentes) yield /*116.48*/{_display_(Seq[Any](format.raw/*116.49*/("""
			                		"""),format.raw/*117.22*/("""<option value=""""),_display_(/*117.38*/lista/*117.43*/.id),format.raw/*117.46*/("""" >"""),_display_(/*117.50*/lista/*117.55*/.nombre),format.raw/*117.62*/("""</option>
								""")))}),format.raw/*118.10*/("""
				        """),format.raw/*119.13*/("""</select> 
			        </td>
				</tr>
				<tr>
					<td style="text-align:left;"><strong>COMPONENTES:</strong></td>
			        <td style="text-align:left; width: 70%"  colspan="4">
			        	<table id="tblComponentesP" class="table table-sm table-bordered table-condensed table-fluid">
							<thead>
								<tr>
									<th style="text-align:left; vertical-align: middle; width: 70%">Componentes</th>
									<th style="text-align:left; vertical-align: middle; width: 70%">Cantidad</th>
									<th style="text-align:left; vertical-align: middle; width: 70%">DEL</th>
									<th style='display:none'>id_producto</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td style="text-align:left;vertical-align:middle">
										<input type="hidden" class="classIdComponentesP" id="id_componenteP0" name="id_mantComponenteP[]" value="0">
										<div id="componenteP0">
											<div class="input-group input-group-sm">
											  <input class="form-control" type="text" onclick="showComponentesP();" readonly>
											  <div class="input-group-append">
											    <span class="input-group-text" onclick="showComponentesP();">Buscar</span>
											  </div>
											</div>
					    				</div>
					    			</td>
									<td>
										<div id="divCantidadP0">
											<input type='text' style='text-align:right;' class='form-control form-control-sm' 
												name='cantidadP[]' id='cantidadP0' readonly></div>
									</td>
									<td style="text-align:center;">
										<div id='delLineaP0'></div>
									</td>
									<td style='display:none' id="tdId_componenteP0">0</td>
								</tr>
							</tbody>
			        	</table>
					</td>
				</tr>
				<tr>
					<td style="text-align:left;"><strong>Fecha_INI:</strong></td>
			        <td style="text-align:left">
						<input type="date" class="form-control form-control-sm left" style="max-width:150px"
							id="fechaIni_mecP" 
							name="fechaIni_mecP"
							onblur="validarFechaIni_mecP()"
							value=""""),_display_(/*168.16*/fecha),format.raw/*168.21*/("""">
			        </td>
					<td style="text-align:left;"><strong>Fecha_TER:</strong></td>
			        <td style="text-align:left" colspan="2">
						<input type="date" class="form-control form-control-sm left"  style="max-width:150px"
							id="fechaTer_mecP" 
							name="fechaTer_mecP"
							onblur="validarFechaTer_mecP()"
							value=""""),_display_(/*176.16*/fecha),format.raw/*176.21*/("""">
			        </td>
				</tr>	
				<tr>
					<td style="text-align:left;"><strong>Hr_INI:</strong></td>
			        <td style="text-align:left">
						<input type="time" class="form-control form-control-sm left" style="max-width:100px"
							id="horaIni_mecP" 
							name="horaIni_mecP"
							onblur="validarHoraIni_mecP()">
			        </td>
					<td style="text-align:left;"><strong>Hr_TER:</strong></td>
			        <td style="text-align:left" colspan="2">
						<input type="time" class="form-control form-control-sm left"  style="max-width:100px"
							id="horaTer_mecP" 
							name="horaTer_mecP"
							onblur="validarHoraTer_mecP()">
			        </td>
				</tr>
				
				<tr>
					<td style="text-align:left;"><strong><div id="lectIni_mecP">Lect_INI:</div></strong></td>
			        <td style="text-align:left">
						<input type="hidden" id="idUnidadMant_mecP" name="id_unidadMantencion">
						<input type="text" class="form-control form-control-sm left" style="max-width:200px"
							id="lecturaIni_mecP" 
							name="lecturaIni_mecP"
							value = "0.00"
							onClick="this.select();"
							onfocus="value=value.replace(/,/g,'')" 
							onfocusout = "if(value.trim()=='') value=0; value = formatStandar(value, 2);"
							onkeydown="return ingresoDouble(window.event)"
							onchange="validarLectIni_mecP()"
							autocomplete="off"
							required disabled>
			        </td>
					<td style="text-align:left;"><strong><div id="lectTer_mecP">Lect_TER(*):</div></strong></td>
			        <td style="text-align:left" colspan="2">
						<input type="text" class="form-control form-control-sm left" style="max-width:200px"
							id="lecturaTer_mecP" 
							name="lecturaTer_mecP"
							value = "0.00"
							onClick="this.select();"
							onfocus="value=value.replace(/,/g,'')" 
							onfocusout = "if(value.trim()=='') value=0; value = formatStandar(value, 2);"
							onkeydown="return ingresoDouble(window.event)"
							onchange="validarLectTer_mecP()"
							autocomplete="off"
							required disabled>
			        </td>
				</tr>
				
				<tr>
					<td style="text-align:left;"><strong><div id="cant_mecP">CANT:</div></strong></td>
			        <td style="text-align:left; width: 70%"  colspan="4">
						<input type="text" class="form-control form-control-sm left"
							id="cantidad_mecP" 
							name="cantidad_mecP"
							value = "0.00"
							onClick="this.select();"
							onfocus="value=value.replace(/,/g,'')" 
							onfocusout = "if(value.trim()=='') value=0; value = formatStandar(value, 2);"
							onkeydown="return ingresoDouble(window.event)"
							onchange="if(value.trim()=='') value=formatStandar(0,2);"
							autocomplete="off"
							required disabled>
			        </td>
				</tr>
				<tr>
					<td style="text-align:left;"><strong>DESCRIPCION TRABAJO:</strong></td>
			        <td style="text-align:left; width: 70%"  colspan="4">
						<textarea class="form-control form-control-sm" rows="1" 
							id="descTrabajoP"
							name="descTrabajoP" 
							autocomplete="off"></textarea>
			        </td>
				</tr>
				<tr>
					<td style="text-align:left;"><strong>ESTADO FINAL:</strong></td>
			        <td style="text-align:left; width: 70%"  colspan="4">
						<textarea class="form-control form-control-sm" rows="1" 
							id="estadoFinalP"
							name="estadoFinalP" 
							autocomplete="off"></textarea>
			        </td>
				</tr>
				<tr>
					<td style="text-align:left;"><strong>OBSERVACIONES:</strong></td>
			        <td style="text-align:left; width: 70%"  colspan="4">
						<textarea class="form-control form-control-sm" rows="2" 
							id="observaciones_mecP"
							name="observaciones_mecP" 
							autocomplete="off"></textarea>
			        </td>
				</tr>
				
				<tr>
					<td style="text-align:left;"  colspan="5">
						FIRMA MECANICO:<br>
						<div class="wrapper" align="center">
						<canvas id="signature-pad5" class="signature-pad" width=350 height=90 style="border:1px solid #000000;"></canvas>
						</div>
						<button type="button" class="btn btn-sm btn-warning" style="font-size: 10px;" onclick="signaturePad5.clear()">Borrar firma</button>
					</td>
				</tr>
				<tr>
					<td style="text-align:left;"   colspan="5">
						FIRMA AUTORIZADA:<br>
						<div class="wrapper" align="center">
						<canvas id="signature-pad6" class="signature-pad" width=350 height=90 style="border:1px solid #000000;"></canvas>
						</div>
						<button type="button" class="btn btn-sm btn-warning" style="font-size: 10px;" onclick="signaturePad6.clear()">Borrar firma</button>
					</td>
				</tr>
			
				<tr>
					<td colspan="5" style="text-align:center;">
						<button type="button" class="btn btn-sm btn-success" style="font-size: 10px;"onclick="grabarMecanicoP();" >GRABAR REPORT
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="btn btn-sm btn-warning" style="font-size: 10px;" onclick="location.href = '/report'">SALIR</button>
					</td>
				</tr>
			</thead>
		</table>

<script>

function validarLectIni_mecP()"""),format.raw/*304.31*/("""{"""),format.raw/*304.32*/("""
		"""),format.raw/*305.3*/("""let lecturaIni = $("#lecturaIni_mecP").val().replace(/,/g,'');
		let lecturaTer = $("#lecturaTer_mecP").val().replace(/,/g,'');
		try"""),format.raw/*307.6*/("""{"""),format.raw/*307.7*/("""
			"""),format.raw/*308.4*/("""lectAnterior = lectAnterior.replace(/,/g,'');
		"""),format.raw/*309.3*/("""}"""),format.raw/*309.4*/("""catch(e)"""),format.raw/*309.12*/("""{"""),format.raw/*309.13*/("""
			"""),format.raw/*310.4*/("""lectAnterior = lectAnterior;
		"""),format.raw/*311.3*/("""}"""),format.raw/*311.4*/("""
		"""),format.raw/*312.3*/("""if(parseFloat(lecturaIni) < parseFloat(lectAnterior))"""),format.raw/*312.56*/("""{"""),format.raw/*312.57*/("""
			"""),format.raw/*313.4*/("""if(parseFloat(lecturaIni) == 0 && $("#id_tipoMantencion").val() == 1)"""),format.raw/*313.73*/("""{"""),format.raw/*313.74*/("""
				"""),format.raw/*314.5*/("""alertify.confirm(
	            	"Colocar la lectura inicial en cero corresponde a cambio, reset o inicio de odómetro y/u horómetro. Esta seguro de esta acción?",
		            function(e) """),format.raw/*316.27*/("""{"""),format.raw/*316.28*/(""" 
						"""),format.raw/*317.7*/("""if (e) """),format.raw/*317.14*/("""{"""),format.raw/*317.15*/("""
							"""),format.raw/*318.8*/("""$("#lecturaTer_mecP").val("0.00");
						"""),format.raw/*319.7*/("""}"""),format.raw/*319.8*/("""else"""),format.raw/*319.12*/("""{"""),format.raw/*319.13*/("""
							"""),format.raw/*320.8*/("""$("#lecturaIni_mecP").val(formatStandar(lectAnterior,2));
						"""),format.raw/*321.7*/("""}"""),format.raw/*321.8*/("""
					"""),format.raw/*322.6*/("""}"""),format.raw/*322.7*/("""
	        	"""),format.raw/*323.11*/(""");
			"""),format.raw/*324.4*/("""}"""),format.raw/*324.5*/("""else"""),format.raw/*324.9*/("""{"""),format.raw/*324.10*/("""
				"""),format.raw/*325.5*/("""alertify.alert("La lectura inicial no puede ser menor a la ultima lectura registrada");
			$("#lecturaIni_mecP").val(formatStandar(lectAnterior,2));
			"""),format.raw/*327.4*/("""}"""),format.raw/*327.5*/("""
			
		"""),format.raw/*329.3*/("""}"""),format.raw/*329.4*/("""else if(lecturaTer!="0.00")"""),format.raw/*329.31*/("""{"""),format.raw/*329.32*/("""
			"""),format.raw/*330.4*/("""if(parseFloat(lecturaIni) > parseFloat(lecturaTer))"""),format.raw/*330.55*/("""{"""),format.raw/*330.56*/("""
				"""),format.raw/*331.5*/("""alertify.alert("La lectura inicial no puede ser menor a la lectura de termino");
				$("#lecturaIni_mecP").val(formatStandar(formatStandar(lectAnterior,2)));
			"""),format.raw/*333.4*/("""}"""),format.raw/*333.5*/("""else"""),format.raw/*333.9*/("""{"""),format.raw/*333.10*/("""
				"""),format.raw/*334.5*/("""let dif = parseFloat(lecturaTer) - parseFloat(lecturaIni);
				$("#cantidad_mecP").val(formatStandar(dif,2));
			"""),format.raw/*336.4*/("""}"""),format.raw/*336.5*/("""
		"""),format.raw/*337.3*/("""}"""),format.raw/*337.4*/("""
	"""),format.raw/*338.2*/("""}"""),format.raw/*338.3*/("""
	
	"""),format.raw/*340.2*/("""function validarLectTer_mecP()"""),format.raw/*340.32*/("""{"""),format.raw/*340.33*/("""
		"""),format.raw/*341.3*/("""let lecturaIni = $("#lecturaIni_mecP").val().replace(/,/g,'');
		let lecturaTer = $("#lecturaTer_mecP").val().replace(/,/g,'');
			if(parseFloat(lecturaIni) > parseFloat(lecturaTer))"""),format.raw/*343.55*/("""{"""),format.raw/*343.56*/("""
				"""),format.raw/*344.5*/("""alertify.alert("La lectura inicial no puede ser menor a la lectura de termino");
				$("#lecturaTer_mecP").val(formatStandar(0,2));
				$("#cantidad_mecP").val(formatStandar(0,2));
			"""),format.raw/*347.4*/("""}"""),format.raw/*347.5*/("""else"""),format.raw/*347.9*/("""{"""),format.raw/*347.10*/("""
				"""),format.raw/*348.5*/("""let dif = parseFloat(lecturaTer) - parseFloat(lecturaIni);
				$("#cantidad_mecP").val(formatStandar(dif,2));
			"""),format.raw/*350.4*/("""}"""),format.raw/*350.5*/("""
	"""),format.raw/*351.2*/("""}"""),format.raw/*351.3*/("""
	
	"""),format.raw/*353.2*/("""function validarHoraIni_mecP()"""),format.raw/*353.32*/("""{"""),format.raw/*353.33*/("""
		"""),format.raw/*354.3*/("""let horaIni = $("#horaIni_mecP").val();
		let horaTer = $("#horaTer_mecP").val();
		if(horaTer!="")"""),format.raw/*356.18*/("""{"""),format.raw/*356.19*/("""
			"""),format.raw/*357.4*/("""if(parseFloat(horaIni) >= parseFloat(horaTer))"""),format.raw/*357.50*/("""{"""),format.raw/*357.51*/("""
				"""),format.raw/*358.5*/("""alertify.alert("La hora inicial no puede ser menor o igual a la hora de termino");
				$("#horaIni_mecP").val("");
				$("#horaTer_mecP").val("");
			"""),format.raw/*361.4*/("""}"""),format.raw/*361.5*/("""
		"""),format.raw/*362.3*/("""}"""),format.raw/*362.4*/("""
	"""),format.raw/*363.2*/("""}"""),format.raw/*363.3*/("""
	
	"""),format.raw/*365.2*/("""function validarHoraTer_mecP()"""),format.raw/*365.32*/("""{"""),format.raw/*365.33*/("""
		"""),format.raw/*366.3*/("""let horaIni = $("#horaIni_mecP").val();
		let horaTer = $("#horaTer_mecP").val();
		if(horaIni=="")"""),format.raw/*368.18*/("""{"""),format.raw/*368.19*/("""
			"""),format.raw/*369.4*/("""alertify.alert("Primero debe ingresar la hora inicial");
			$("#horaTer_mecP").val("");
		"""),format.raw/*371.3*/("""}"""),format.raw/*371.4*/("""else"""),format.raw/*371.8*/("""{"""),format.raw/*371.9*/("""
			"""),format.raw/*372.4*/("""if(horaIni > horaTer)"""),format.raw/*372.25*/("""{"""),format.raw/*372.26*/("""
				"""),format.raw/*373.5*/("""alertify.alert("La hora inicial no puede ser menor a la hora de termino");
				$("#horaTer_mecP").val("");
			"""),format.raw/*375.4*/("""}"""),format.raw/*375.5*/("""
		"""),format.raw/*376.3*/("""}"""),format.raw/*376.4*/("""
	"""),format.raw/*377.2*/("""}"""),format.raw/*377.3*/("""
	
	
	"""),format.raw/*380.2*/("""function validarFechaIni_mecP()"""),format.raw/*380.33*/("""{"""),format.raw/*380.34*/("""
		"""),format.raw/*381.3*/("""let fechaIni = new Date($("#fechaIni_mecP").val());
		if(isValidDate(fechaIni))"""),format.raw/*382.28*/("""{"""),format.raw/*382.29*/("""
			"""),format.raw/*383.4*/("""let fechaTer = new Date($("#fechaTer_mecP").val());
			if(fechaIni > fechaTer)"""),format.raw/*384.27*/("""{"""),format.raw/*384.28*/("""
				"""),format.raw/*385.5*/("""alertify.alert("La fecha de inicio no puede ser posterior a la fecha de termino.");
	        		$('#fechaIni_mecP').val('"""),_display_(/*386.38*/fecha),format.raw/*386.43*/("""');
			"""),format.raw/*387.4*/("""}"""),format.raw/*387.5*/("""
		"""),format.raw/*388.3*/("""}"""),format.raw/*388.4*/("""else"""),format.raw/*388.8*/("""{"""),format.raw/*388.9*/("""
			"""),format.raw/*389.4*/("""alertify.alert("Debe ingresar una fecha valida.");
	        		$('#fechaIni_mecP').val('"""),_display_(/*390.38*/fecha),format.raw/*390.43*/("""');
		"""),format.raw/*391.3*/("""}"""),format.raw/*391.4*/("""
		
	"""),format.raw/*393.2*/("""}"""),format.raw/*393.3*/("""
	
	"""),format.raw/*395.2*/("""function validarFechaTer_mecP()"""),format.raw/*395.33*/("""{"""),format.raw/*395.34*/("""
		"""),format.raw/*396.3*/("""let fechaTer = new Date($("#fechaTer_mecP").val());
		if(isValidDate(fechaTer))"""),format.raw/*397.28*/("""{"""),format.raw/*397.29*/("""
			"""),format.raw/*398.4*/("""let fechaIni = new Date($("#fechaIni_mecP").val());
			if(fechaIni > fechaTer)"""),format.raw/*399.27*/("""{"""),format.raw/*399.28*/("""
				"""),format.raw/*400.5*/("""alertify.alert("La fecha de termino no puede ser anterior a la fecha de inicio.");
	        		$('#fechaTer_mecP').val('"""),_display_(/*401.38*/fecha),format.raw/*401.43*/("""');
			"""),format.raw/*402.4*/("""}"""),format.raw/*402.5*/("""
		"""),format.raw/*403.3*/("""}"""),format.raw/*403.4*/("""else"""),format.raw/*403.8*/("""{"""),format.raw/*403.9*/("""
			"""),format.raw/*404.4*/("""alertify.alert("Debe ingresar una fecha valida.");
	        		$('#fechaTer_mecP').val('"""),_display_(/*405.38*/fecha),format.raw/*405.43*/("""');
		"""),format.raw/*406.3*/("""}"""),format.raw/*406.4*/("""
	"""),format.raw/*407.2*/("""}"""),format.raw/*407.3*/("""
	
	"""),format.raw/*409.2*/("""function grabarMecanicoP()"""),format.raw/*409.28*/("""{"""),format.raw/*409.29*/("""
		  """),format.raw/*410.5*/("""if($('#id_mecanico').val()==0)"""),format.raw/*410.35*/("""{"""),format.raw/*410.36*/("""
			  """),format.raw/*411.6*/("""alertify.alert('Debe seleccionar un Mecanico');
		  """),format.raw/*412.5*/("""}"""),format.raw/*412.6*/("""else if($('#id_tipoMantencion').val()==0)"""),format.raw/*412.47*/("""{"""),format.raw/*412.48*/("""
			  """),format.raw/*413.6*/("""alertify.alert('Debe seleccionar un Tipo de Mantencion');
		  """),format.raw/*414.5*/("""}"""),format.raw/*414.6*/("""else if($('#id_equipo_mecP').val()==0)"""),format.raw/*414.44*/("""{"""),format.raw/*414.45*/("""
			  """),format.raw/*415.6*/("""alertify.alert('Debe seleccionar un Equipo');
		  """),format.raw/*416.5*/("""}"""),format.raw/*416.6*/("""else if($('#id_bodega_mecP').val()==0)"""),format.raw/*416.44*/("""{"""),format.raw/*416.45*/("""
			  """),format.raw/*417.6*/("""alertify.alert('Debe seleccionar una """),_display_(/*417.44*/mapDiccionario/*417.58*/.get("Bodega")),format.raw/*417.72*/("""/Proyecto');
		  """),format.raw/*418.5*/("""}"""),format.raw/*418.6*/("""else if($('#id_mantEstadoOperacionalP').val()==0)"""),format.raw/*418.55*/("""{"""),format.raw/*418.56*/("""
			  """),format.raw/*419.6*/("""alertify.alert('Debe ingresar un Estado en Operacional');
		  """),format.raw/*420.5*/("""}"""),format.raw/*420.6*/("""else if($('#id_mantEstadoEnTallerP').val()==0)"""),format.raw/*420.52*/("""{"""),format.raw/*420.53*/("""
			  """),format.raw/*421.6*/("""alertify.alert('Debe ingresar un Estado en Taller');
		  """),format.raw/*422.5*/("""}"""),format.raw/*422.6*/("""else if($('#id_mantActividadP').val()==0)"""),format.raw/*422.47*/("""{"""),format.raw/*422.48*/("""
			  """),format.raw/*423.6*/("""alertify.alert('Debe ingresar una Actividad');
	      """),format.raw/*424.8*/("""}"""),format.raw/*424.9*/("""else if($('#id_mantTipoActividadP').val()==0)"""),format.raw/*424.54*/("""{"""),format.raw/*424.55*/("""
			  """),format.raw/*425.6*/("""alertify.alert('Debe ingresar un tipo de Actividad');
		  """),format.raw/*426.5*/("""}"""),format.raw/*426.6*/("""else if($('#id_mantItemIntervenidoP').val()==0)"""),format.raw/*426.53*/("""{"""),format.raw/*426.54*/("""
			  """),format.raw/*427.6*/("""alertify.alert('Debe ingresar un Item Intervenido');
		  """),format.raw/*428.5*/("""}"""),format.raw/*428.6*/("""else if($('#lecturaTer_mecP').val()=="0.00")"""),format.raw/*428.50*/("""{"""),format.raw/*428.51*/("""
			  """),format.raw/*429.6*/("""alertify.alert('Debe ingresar Lect_TER que debe ser mayor a cero');
		  """),format.raw/*430.5*/("""}"""),format.raw/*430.6*/("""else"""),format.raw/*430.10*/("""{"""),format.raw/*430.11*/("""
			"""),format.raw/*431.4*/("""let data = signaturePad5.toDataURL('image/png');
			let encodedImage = data.split(",");
			$("#firmaEjecutor").val(encodedImage[1]);

			data = signaturePad6.toDataURL('image/png');
			encodedImage = data.split(",");
			$("#firmaAprobador").val(encodedImage[1]);
			document.getElementById('enCarga').style.display="block";
			$("#grabarRptOperadorMecanico").submit();
		  """),format.raw/*440.5*/("""}"""),format.raw/*440.6*/("""
	"""),format.raw/*441.2*/("""}"""),format.raw/*441.3*/("""
	
"""),format.raw/*443.1*/("""</script>
	"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],fecha:String,listEstaOperacional:List[tables.MantEstadoEnObra],operMec:tables.MantOperadorMecanico,listTipoMantencion:List[tables.TipoMantencion],listEstadoEnTaller:List[tables.MantEstadoEnTaller],listActividad:List[tables.MantActividad],listTipoActividad:List[tables.MantTipoActividad],listItemIntervenido:List[tables.MantItemIntervenido],listComponentes:List[tables.MantComponente]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,fecha,listEstaOperacional,operMec,listTipoMantencion,listEstadoEnTaller,listActividad,listTipoActividad,listItemIntervenido,listComponentes)

  def f:((Map[String,String],String,List[tables.MantEstadoEnObra],tables.MantOperadorMecanico,List[tables.TipoMantencion],List[tables.MantEstadoEnTaller],List[tables.MantActividad],List[tables.MantTipoActividad],List[tables.MantItemIntervenido],List[tables.MantComponente]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,fecha,listEstaOperacional,operMec,listTipoMantencion,listEstadoEnTaller,listActividad,listTipoActividad,listItemIntervenido,listComponentes) => apply(mapDiccionario,fecha,listEstaOperacional,operMec,listTipoMantencion,listEstadoEnTaller,listActividad,listTipoActividad,listItemIntervenido,listComponentes)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuMantencion/mantWebReportNewMecanicoPreventivo.scala.html
                  HASH: fa39ae0fcad47447a5b0be7e4ec90c0348dd972e
                  MATRIX: 1244->1|1776->440|3150->1787|3173->1801|3208->1815|3965->2545|3988->2559|4023->2573|4656->3179|4705->3212|4744->3213|4794->3235|4837->3251|4851->3256|4875->3259|4906->3263|4920->3268|4948->3275|4998->3294|5039->3307|5499->3740|5547->3772|5586->3773|5636->3795|5679->3811|5693->3816|5717->3819|5748->3823|5762->3828|5790->3835|5840->3854|5881->3867|6324->4283|6367->4310|6406->4311|6456->4333|6499->4349|6513->4354|6537->4357|6568->4361|6582->4366|6610->4373|6660->4392|6701->4405|7161->4837|7209->4868|7249->4869|7300->4891|7344->4907|7359->4912|7384->4915|7416->4919|7431->4924|7460->4931|7511->4950|7553->4963|8016->5398|8062->5427|8102->5428|8153->5450|8197->5466|8212->5471|8237->5474|8269->5478|8284->5483|8313->5490|8364->5509|8406->5522|10455->7543|10482->7548|10850->7888|10877->7893|16014->13001|16044->13002|16075->13005|16236->13138|16265->13139|16297->13143|16373->13191|16402->13192|16439->13200|16469->13201|16501->13205|16560->13236|16589->13237|16620->13240|16702->13293|16732->13294|16764->13298|16862->13367|16892->13368|16925->13373|17142->13561|17172->13562|17208->13570|17244->13577|17274->13578|17310->13586|17379->13627|17408->13628|17441->13632|17471->13633|17507->13641|17599->13705|17628->13706|17662->13712|17691->13713|17731->13724|17765->13730|17794->13731|17826->13735|17856->13736|17889->13741|18069->13893|18098->13894|18133->13901|18162->13902|18218->13929|18248->13930|18280->13934|18360->13985|18390->13986|18423->13991|18612->14152|18641->14153|18673->14157|18703->14158|18736->14163|18877->14276|18906->14277|18937->14280|18966->14281|18996->14283|19025->14284|19057->14288|19116->14318|19146->14319|19177->14322|19388->14504|19418->14505|19451->14510|19663->14694|19692->14695|19724->14699|19754->14700|19787->14705|19928->14818|19957->14819|19987->14821|20016->14822|20048->14826|20107->14856|20137->14857|20168->14860|20296->14959|20326->14960|20358->14964|20433->15010|20463->15011|20496->15016|20674->15166|20703->15167|20734->15170|20763->15171|20793->15173|20822->15174|20854->15178|20913->15208|20943->15209|20974->15212|21102->15311|21132->15312|21164->15316|21282->15406|21311->15407|21343->15411|21372->15412|21404->15416|21454->15437|21484->15438|21517->15443|21655->15553|21684->15554|21715->15557|21744->15558|21774->15560|21803->15561|21837->15567|21897->15598|21927->15599|21958->15602|22066->15681|22096->15682|22128->15686|22235->15764|22265->15765|22298->15770|22447->15891|22474->15896|22509->15903|22538->15904|22569->15907|22598->15908|22630->15912|22659->15913|22691->15917|22807->16005|22834->16010|22868->16016|22897->16017|22930->16022|22959->16023|22991->16027|23051->16058|23081->16059|23112->16062|23220->16141|23250->16142|23282->16146|23389->16224|23419->16225|23452->16230|23600->16350|23627->16355|23662->16362|23691->16363|23722->16366|23751->16367|23783->16371|23812->16372|23844->16376|23960->16464|23987->16469|24021->16475|24050->16476|24080->16478|24109->16479|24141->16483|24196->16509|24226->16510|24259->16515|24318->16545|24348->16546|24382->16552|24462->16604|24491->16605|24561->16646|24591->16647|24625->16653|24715->16715|24744->16716|24811->16754|24841->16755|24875->16761|24953->16811|24982->16812|25049->16850|25079->16851|25113->16857|25179->16895|25203->16909|25239->16923|25284->16940|25313->16941|25391->16990|25421->16991|25455->16997|25545->17059|25574->17060|25649->17106|25679->17107|25713->17113|25798->17170|25827->17171|25897->17212|25927->17213|25961->17219|26043->17273|26072->17274|26146->17319|26176->17320|26210->17326|26296->17384|26325->17385|26401->17432|26431->17433|26465->17439|26550->17496|26579->17497|26652->17541|26682->17542|26716->17548|26816->17620|26845->17621|26878->17625|26908->17626|26940->17630|27341->18003|27370->18004|27400->18006|27429->18007|27460->18010
                  LINES: 28->1|33->2|61->30|61->30|61->30|78->47|78->47|78->47|91->60|91->60|91->60|92->61|92->61|92->61|92->61|92->61|92->61|92->61|93->62|94->63|105->74|105->74|105->74|106->75|106->75|106->75|106->75|106->75|106->75|106->75|107->76|108->77|119->88|119->88|119->88|120->89|120->89|120->89|120->89|120->89|120->89|120->89|121->90|122->91|133->102|133->102|133->102|134->103|134->103|134->103|134->103|134->103|134->103|134->103|135->104|136->105|147->116|147->116|147->116|148->117|148->117|148->117|148->117|148->117|148->117|148->117|149->118|150->119|199->168|199->168|207->176|207->176|335->304|335->304|336->305|338->307|338->307|339->308|340->309|340->309|340->309|340->309|341->310|342->311|342->311|343->312|343->312|343->312|344->313|344->313|344->313|345->314|347->316|347->316|348->317|348->317|348->317|349->318|350->319|350->319|350->319|350->319|351->320|352->321|352->321|353->322|353->322|354->323|355->324|355->324|355->324|355->324|356->325|358->327|358->327|360->329|360->329|360->329|360->329|361->330|361->330|361->330|362->331|364->333|364->333|364->333|364->333|365->334|367->336|367->336|368->337|368->337|369->338|369->338|371->340|371->340|371->340|372->341|374->343|374->343|375->344|378->347|378->347|378->347|378->347|379->348|381->350|381->350|382->351|382->351|384->353|384->353|384->353|385->354|387->356|387->356|388->357|388->357|388->357|389->358|392->361|392->361|393->362|393->362|394->363|394->363|396->365|396->365|396->365|397->366|399->368|399->368|400->369|402->371|402->371|402->371|402->371|403->372|403->372|403->372|404->373|406->375|406->375|407->376|407->376|408->377|408->377|411->380|411->380|411->380|412->381|413->382|413->382|414->383|415->384|415->384|416->385|417->386|417->386|418->387|418->387|419->388|419->388|419->388|419->388|420->389|421->390|421->390|422->391|422->391|424->393|424->393|426->395|426->395|426->395|427->396|428->397|428->397|429->398|430->399|430->399|431->400|432->401|432->401|433->402|433->402|434->403|434->403|434->403|434->403|435->404|436->405|436->405|437->406|437->406|438->407|438->407|440->409|440->409|440->409|441->410|441->410|441->410|442->411|443->412|443->412|443->412|443->412|444->413|445->414|445->414|445->414|445->414|446->415|447->416|447->416|447->416|447->416|448->417|448->417|448->417|448->417|449->418|449->418|449->418|449->418|450->419|451->420|451->420|451->420|451->420|452->421|453->422|453->422|453->422|453->422|454->423|455->424|455->424|455->424|455->424|456->425|457->426|457->426|457->426|457->426|458->427|459->428|459->428|459->428|459->428|460->429|461->430|461->430|461->430|461->430|462->431|471->440|471->440|472->441|472->441|474->443
                  -- GENERATED --
              */
          