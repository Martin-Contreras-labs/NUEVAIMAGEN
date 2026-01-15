
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

object mantReportNewMecanicoPreventivo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template10[Map[String,String],String,List[tables.MantEstadoEnObra],List[tables.MantOperadorMecanico],List[tables.TipoMantencion],List[tables.MantEstadoEnTaller],List[tables.MantActividad],List[tables.MantTipoActividad],List[tables.MantItemIntervenido],List[tables.MantComponente],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], fecha: String, listEstaOperacional: List[tables.MantEstadoEnObra], 
listOperMec: List[tables.MantOperadorMecanico], listTipoMantencion: List[tables.TipoMantencion],
listEstadoEnTaller: List[tables.MantEstadoEnTaller], 
listActividad: List[tables.MantActividad], listTipoActividad: List[tables.MantTipoActividad], listItemIntervenido: List[tables.MantItemIntervenido],
listComponentes: List[tables.MantComponente]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*6.1*/("""<div id="enCarga" class="blocker" style="display: none;"><br><br><br><br><br><br><h1>En proceso.....<div id="msgEspera"></div></h1></div>
		<table class="table table-sm table-bordered table-condensed table-fluid">
			<thead>
				<tr>
					<td style="text-align:left;"><strong>EQUIPO (*):</strong></td>
			        <td style="text-align:left" width="70%" colspan="4">
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
			        <td style="text-align:left" width="70%" colspan="4">
						<input type="hidden" id="id_tipoPlan_mecP" name="id_tipoPlan_mecP" value="0">
						<input class="form-control" id="tipoPlan_mecP" type="text" value="" readonly>
			        </td>
				</tr>
				<tr>
					<td style="text-align:left;"><strong>"""),_display_(/*34.44*/mapDiccionario/*34.58*/.get("BODEGA")),format.raw/*34.72*/("""/PROYECTO (*):</strong></td>
			        <td style="text-align:left" width="70%" colspan="4">
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
					<td style="text-align:left;"><strong>TIPO """),_display_(/*51.49*/mapDiccionario/*51.63*/.get("BODEGA")),format.raw/*51.77*/("""/PROYECTO:</strong></td>
			        <td style="text-align:left" width="70%" colspan="4">
						<input class="form-control" id="tipoBodega_mecP" type="text" value="" readonly>
			        </td>
				</tr>
				<tr>
					<td style="text-align:left;"><strong>ESTADO OPERACIONAL (*):</strong></td>
			        <td style="text-align:left" width="70%" colspan="4">
			        	<select class="form-control form-control-sm" 
							id="id_mantEstadoOperacionalP" 
							name="id_mantEstadoOperacionalP"
							required disabled>
				        		<option class="blank" value="0">-- Select --</option>
			            		"""),_display_(/*64.19*/for(lista <- listEstaOperacional) yield /*64.52*/{_display_(Seq[Any](format.raw/*64.53*/("""
			                		"""),format.raw/*65.22*/("""<option value=""""),_display_(/*65.38*/lista/*65.43*/.id),format.raw/*65.46*/("""" >"""),_display_(/*65.50*/lista/*65.55*/.nombre),format.raw/*65.62*/("""</option>
								""")))}),format.raw/*66.10*/("""
				        """),format.raw/*67.13*/("""</select> 
			        </td>
				</tr>
				<tr>
					<td style="text-align:left;"><strong>ESTADO EN TALLER (*):</strong></td>
			        <td style="text-align:left" width="70%" colspan="4">
			        	<select class="form-control form-control-sm" 
							id="id_mantEstadoEnTallerP" 
							name="id_mantEstadoEnTallerP"
							required disabled>
				        		<option class="blank" value="0">-- Select --</option>
			            		"""),_display_(/*78.19*/for(lista <- listEstadoEnTaller) yield /*78.51*/{_display_(Seq[Any](format.raw/*78.52*/("""
			                		"""),format.raw/*79.22*/("""<option value=""""),_display_(/*79.38*/lista/*79.43*/.id),format.raw/*79.46*/("""" >"""),_display_(/*79.50*/lista/*79.55*/.nombre),format.raw/*79.62*/("""</option>
								""")))}),format.raw/*80.10*/("""
				        """),format.raw/*81.13*/("""</select> 
			        </td>
				</tr>
				<tr>
					<td style="text-align:left;"><strong>ACTIVIDAD (*):</strong></td>
			        <td style="text-align:left" width="70%" colspan="4">
			        	<select class="form-control form-control-sm" 
							id="id_mantActividadP" 
							name="id_mantActividadP"
							required disabled>
				        		<option class="blank" value="0">-- Select --</option>
			            		"""),_display_(/*92.19*/for(lista <- listActividad) yield /*92.46*/{_display_(Seq[Any](format.raw/*92.47*/("""
			                		"""),format.raw/*93.22*/("""<option value=""""),_display_(/*93.38*/lista/*93.43*/.id),format.raw/*93.46*/("""" >"""),_display_(/*93.50*/lista/*93.55*/.nombre),format.raw/*93.62*/("""</option>
								""")))}),format.raw/*94.10*/("""
				        """),format.raw/*95.13*/("""</select> 
			        </td>
				</tr>
				<tr>
					<td style="text-align:left;"><strong>TIPO DE ACTIVIDAD (*):</strong></td>
			        <td style="text-align:left" width="70%" colspan="4">
			        	<select class="form-control form-control-sm" 
							id="id_mantTipoActividadP" 
							name="id_mantTipoActividadP"
							required disabled>
				        		<option class="blank" value="0">-- Select --</option>
			            		"""),_display_(/*106.19*/for(lista <- listTipoActividad) yield /*106.50*/{_display_(Seq[Any](format.raw/*106.51*/("""
			                		"""),format.raw/*107.22*/("""<option value=""""),_display_(/*107.38*/lista/*107.43*/.id),format.raw/*107.46*/("""" >"""),_display_(/*107.50*/lista/*107.55*/.nombre),format.raw/*107.62*/("""</option>
								""")))}),format.raw/*108.10*/("""
				        """),format.raw/*109.13*/("""</select> 
			        </td>
				</tr>
				<tr>
					<td style="text-align:left;"><strong>ITEM INTERVENIDO (*):</strong></td>
			        <td style="text-align:left" width="70%" colspan="4">
			        	<select class="form-control form-control-sm" 
							id="id_mantItemIntervenidoP" 
							name="id_mantItemIntervenidoP"
							required disabled>
				        		<option class="blank" value="0">-- Select --</option>
			            		"""),_display_(/*120.19*/for(lista <- listComponentes) yield /*120.48*/{_display_(Seq[Any](format.raw/*120.49*/("""
			                		"""),format.raw/*121.22*/("""<option value=""""),_display_(/*121.38*/lista/*121.43*/.id),format.raw/*121.46*/("""" >"""),_display_(/*121.50*/lista/*121.55*/.nombre),format.raw/*121.62*/("""</option>
								""")))}),format.raw/*122.10*/("""
				        """),format.raw/*123.13*/("""</select> 
			        </td>
				</tr>
				<tr>
					<td style="text-align:left;"><strong>COMPONENTES:</strong></td>
			        <td style="text-align:left" width="70%" colspan="4">
			        	<table id="tblComponentesP" class="table table-sm table-bordered table-condensed table-fluid">
							<thead>
								<tr>
									<th width="70%" style="text-align:left; vertical-align: middle;">Componentes</th>
									<th width="10%" style="text-align:left; vertical-align: middle;">Cantidad</th>
									<th width="5%" style="text-align:left; vertical-align: middle;">DEL</th>
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
							value=""""),_display_(/*172.16*/fecha),format.raw/*172.21*/("""">
			        </td>
					<td style="text-align:left;"><strong>Fecha_TER:</strong></td>
			        <td style="text-align:left" colspan="2">
						<input type="date" class="form-control form-control-sm left"  style="max-width:150px"
							id="fechaTer_mecP" 
							name="fechaTer_mecP"
							onblur="validarFechaTer_mecP()"
							value=""""),_display_(/*180.16*/fecha),format.raw/*180.21*/("""">
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
			        <td style="text-align:left" width="70%" colspan="4">
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
			        <td style="text-align:left" width="70%" colspan="4">
						<textarea class="form-control form-control-sm" rows="1" 
							id="descTrabajoP"
							name="descTrabajoP" 
							autocomplete="off"></textarea>
			        </td>
				</tr>
				<tr>
					<td style="text-align:left;"><strong>ESTADO FINAL:</strong></td>
			        <td style="text-align:left" width="70%" colspan="4">
						<textarea class="form-control form-control-sm" rows="1" 
							id="estadoFinalP"
							name="estadoFinalP" 
							autocomplete="off"></textarea>
			        </td>
				</tr>
				<tr>
					<td style="text-align:left;"><strong>OBSERVACIONES:</strong></td>
			        <td style="text-align:left" width="70%" colspan="4">
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
						<button type="button" class="btn btn-sm btn-warning" style="font-size: 10px;" onclick="location.href = '/home/'">SALIR</button>
					</td>
				</tr>
			</thead>
		</table>

<script>

function validarLectIni_mecP()"""),format.raw/*308.31*/("""{"""),format.raw/*308.32*/("""
		"""),format.raw/*309.3*/("""let lecturaIni = $("#lecturaIni_mecP").val().replace(/,/g,'');
		let lecturaTer = $("#lecturaTer_mecP").val().replace(/,/g,'');
		try"""),format.raw/*311.6*/("""{"""),format.raw/*311.7*/("""
			"""),format.raw/*312.4*/("""lectAnterior = lectAnterior.replace(/,/g,'');
		"""),format.raw/*313.3*/("""}"""),format.raw/*313.4*/("""catch(e)"""),format.raw/*313.12*/("""{"""),format.raw/*313.13*/("""
			"""),format.raw/*314.4*/("""lectAnterior = lectAnterior;
		"""),format.raw/*315.3*/("""}"""),format.raw/*315.4*/("""
		"""),format.raw/*316.3*/("""if(parseFloat(lecturaIni) < parseFloat(lectAnterior))"""),format.raw/*316.56*/("""{"""),format.raw/*316.57*/("""
			"""),format.raw/*317.4*/("""if(parseFloat(lecturaIni) == 0 && $("#id_tipoMantencion").val() == 1)"""),format.raw/*317.73*/("""{"""),format.raw/*317.74*/("""
				"""),format.raw/*318.5*/("""alertify.confirm(
	            	"Colocar la lectura inicial en cero corresponde a cambio, reset o inicio de odómetro y/u horómetro. Esta seguro de esta acción?",
		            function(e) """),format.raw/*320.27*/("""{"""),format.raw/*320.28*/(""" 
						"""),format.raw/*321.7*/("""if (e) """),format.raw/*321.14*/("""{"""),format.raw/*321.15*/("""
							"""),format.raw/*322.8*/("""$("#lecturaTer_mecP").val("0.00");
						"""),format.raw/*323.7*/("""}"""),format.raw/*323.8*/("""else"""),format.raw/*323.12*/("""{"""),format.raw/*323.13*/("""
							"""),format.raw/*324.8*/("""$("#lecturaIni_mecP").val(formatStandar(lectAnterior,2));
						"""),format.raw/*325.7*/("""}"""),format.raw/*325.8*/("""
					"""),format.raw/*326.6*/("""}"""),format.raw/*326.7*/("""
	        	"""),format.raw/*327.11*/(""");
			"""),format.raw/*328.4*/("""}"""),format.raw/*328.5*/("""else"""),format.raw/*328.9*/("""{"""),format.raw/*328.10*/("""
				"""),format.raw/*329.5*/("""alertify.alert("La lectura inicial no puede ser menor a la ultima lectura registrada");
			$("#lecturaIni_mecP").val(formatStandar(lectAnterior,2));
			"""),format.raw/*331.4*/("""}"""),format.raw/*331.5*/("""
			
		"""),format.raw/*333.3*/("""}"""),format.raw/*333.4*/("""else if(lecturaTer!="0.00")"""),format.raw/*333.31*/("""{"""),format.raw/*333.32*/("""
			"""),format.raw/*334.4*/("""if(parseFloat(lecturaIni) > parseFloat(lecturaTer))"""),format.raw/*334.55*/("""{"""),format.raw/*334.56*/("""
				"""),format.raw/*335.5*/("""alertify.alert("La lectura inicial no puede ser menor a la lectura de termino");
				$("#lecturaIni_mecP").val(formatStandar(formatStandar(lectAnterior,2)));
			"""),format.raw/*337.4*/("""}"""),format.raw/*337.5*/("""else"""),format.raw/*337.9*/("""{"""),format.raw/*337.10*/("""
				"""),format.raw/*338.5*/("""let dif = parseFloat(lecturaTer) - parseFloat(lecturaIni);
				$("#cantidad_mecP").val(formatStandar(dif,2));
			"""),format.raw/*340.4*/("""}"""),format.raw/*340.5*/("""
		"""),format.raw/*341.3*/("""}"""),format.raw/*341.4*/("""
	"""),format.raw/*342.2*/("""}"""),format.raw/*342.3*/("""
	
	"""),format.raw/*344.2*/("""function validarLectTer_mecP()"""),format.raw/*344.32*/("""{"""),format.raw/*344.33*/("""
		"""),format.raw/*345.3*/("""let lecturaIni = $("#lecturaIni_mecP").val().replace(/,/g,'');
		let lecturaTer = $("#lecturaTer_mecP").val().replace(/,/g,'');
			if(parseFloat(lecturaIni) > parseFloat(lecturaTer))"""),format.raw/*347.55*/("""{"""),format.raw/*347.56*/("""
				"""),format.raw/*348.5*/("""alertify.alert("La lectura inicial no puede ser menor a la lectura de termino");
				$("#lecturaTer_mecP").val(formatStandar(0,2));
				$("#cantidad_mecP").val(formatStandar(0,2));
			"""),format.raw/*351.4*/("""}"""),format.raw/*351.5*/("""else"""),format.raw/*351.9*/("""{"""),format.raw/*351.10*/("""
				"""),format.raw/*352.5*/("""let dif = parseFloat(lecturaTer) - parseFloat(lecturaIni);
				$("#cantidad_mecP").val(formatStandar(dif,2));
			"""),format.raw/*354.4*/("""}"""),format.raw/*354.5*/("""
	"""),format.raw/*355.2*/("""}"""),format.raw/*355.3*/("""
	
	"""),format.raw/*357.2*/("""function validarHoraIni_mecP()"""),format.raw/*357.32*/("""{"""),format.raw/*357.33*/("""
		"""),format.raw/*358.3*/("""let horaIni = $("#horaIni_mecP").val();
		let horaTer = $("#horaTer_mecP").val();
		if(horaTer!="")"""),format.raw/*360.18*/("""{"""),format.raw/*360.19*/("""
			"""),format.raw/*361.4*/("""if(parseFloat(horaIni) >= parseFloat(horaTer))"""),format.raw/*361.50*/("""{"""),format.raw/*361.51*/("""
				"""),format.raw/*362.5*/("""alertify.alert("La hora inicial no puede ser menor o igual a la hora de termino");
				$("#horaIni_mecP").val("");
				$("#horaTer_mecP").val("");
			"""),format.raw/*365.4*/("""}"""),format.raw/*365.5*/("""
		"""),format.raw/*366.3*/("""}"""),format.raw/*366.4*/("""
	"""),format.raw/*367.2*/("""}"""),format.raw/*367.3*/("""
	
	"""),format.raw/*369.2*/("""function validarHoraTer_mecP()"""),format.raw/*369.32*/("""{"""),format.raw/*369.33*/("""
		"""),format.raw/*370.3*/("""let horaIni = $("#horaIni_mecP").val();
		let horaTer = $("#horaTer_mecP").val();
		if(horaIni=="")"""),format.raw/*372.18*/("""{"""),format.raw/*372.19*/("""
			"""),format.raw/*373.4*/("""alertify.alert("Primero debe ingresar la hora inicial");
			$("#horaTer_mecP").val("");
		"""),format.raw/*375.3*/("""}"""),format.raw/*375.4*/("""else"""),format.raw/*375.8*/("""{"""),format.raw/*375.9*/("""
			"""),format.raw/*376.4*/("""if(horaIni > horaTer)"""),format.raw/*376.25*/("""{"""),format.raw/*376.26*/("""
				"""),format.raw/*377.5*/("""alertify.alert("La hora inicial no puede ser menor a la hora de termino");
				$("#horaTer_mecP").val("");
			"""),format.raw/*379.4*/("""}"""),format.raw/*379.5*/("""
		"""),format.raw/*380.3*/("""}"""),format.raw/*380.4*/("""
	"""),format.raw/*381.2*/("""}"""),format.raw/*381.3*/("""
	
	
	"""),format.raw/*384.2*/("""function validarFechaIni_mecP()"""),format.raw/*384.33*/("""{"""),format.raw/*384.34*/("""
		"""),format.raw/*385.3*/("""let fechaIni = new Date($("#fechaIni_mecP").val());
		if(isValidDate(fechaIni))"""),format.raw/*386.28*/("""{"""),format.raw/*386.29*/("""
			"""),format.raw/*387.4*/("""let fechaTer = new Date($("#fechaTer_mecP").val());
			if(fechaIni > fechaTer)"""),format.raw/*388.27*/("""{"""),format.raw/*388.28*/("""
				"""),format.raw/*389.5*/("""alertify.alert("La fecha de inicio no puede ser posterior a la fecha de termino.");
	        		$('#fechaIni_mecP').val('"""),_display_(/*390.38*/fecha),format.raw/*390.43*/("""');
			"""),format.raw/*391.4*/("""}"""),format.raw/*391.5*/("""
		"""),format.raw/*392.3*/("""}"""),format.raw/*392.4*/("""else"""),format.raw/*392.8*/("""{"""),format.raw/*392.9*/("""
			"""),format.raw/*393.4*/("""alertify.alert("Debe ingresar una fecha valida.");
	        		$('#fechaIni_mecP').val('"""),_display_(/*394.38*/fecha),format.raw/*394.43*/("""');
		"""),format.raw/*395.3*/("""}"""),format.raw/*395.4*/("""
		
	"""),format.raw/*397.2*/("""}"""),format.raw/*397.3*/("""
	
	"""),format.raw/*399.2*/("""function validarFechaTer_mecP()"""),format.raw/*399.33*/("""{"""),format.raw/*399.34*/("""
		"""),format.raw/*400.3*/("""let fechaTer = new Date($("#fechaTer_mecP").val());
		if(isValidDate(fechaTer))"""),format.raw/*401.28*/("""{"""),format.raw/*401.29*/("""
			"""),format.raw/*402.4*/("""let fechaIni = new Date($("#fechaIni_mecP").val());
			if(fechaIni > fechaTer)"""),format.raw/*403.27*/("""{"""),format.raw/*403.28*/("""
				"""),format.raw/*404.5*/("""alertify.alert("La fecha de termino no puede ser anterior a la fecha de inicio.");
	        		$('#fechaTer_mecP').val('"""),_display_(/*405.38*/fecha),format.raw/*405.43*/("""');
			"""),format.raw/*406.4*/("""}"""),format.raw/*406.5*/("""
		"""),format.raw/*407.3*/("""}"""),format.raw/*407.4*/("""else"""),format.raw/*407.8*/("""{"""),format.raw/*407.9*/("""
			"""),format.raw/*408.4*/("""alertify.alert("Debe ingresar una fecha valida.");
	        		$('#fechaTer_mecP').val('"""),_display_(/*409.38*/fecha),format.raw/*409.43*/("""');
		"""),format.raw/*410.3*/("""}"""),format.raw/*410.4*/("""
	"""),format.raw/*411.2*/("""}"""),format.raw/*411.3*/("""
	
	"""),format.raw/*413.2*/("""function grabarMecanicoP()"""),format.raw/*413.28*/("""{"""),format.raw/*413.29*/("""
		  
		  """),format.raw/*415.5*/("""if($('#id_mecanico').val()==0)"""),format.raw/*415.35*/("""{"""),format.raw/*415.36*/("""
			  """),format.raw/*416.6*/("""alertify.alert('Debe seleccionar un Mecanico');
		  """),format.raw/*417.5*/("""}"""),format.raw/*417.6*/("""else if($('#id_tipoMantencion').val()==0)"""),format.raw/*417.47*/("""{"""),format.raw/*417.48*/("""
			  """),format.raw/*418.6*/("""alertify.alert('Debe seleccionar un Tipo de Mantencion');
		  """),format.raw/*419.5*/("""}"""),format.raw/*419.6*/("""else if($('#id_equipo_mecP').val()==0)"""),format.raw/*419.44*/("""{"""),format.raw/*419.45*/("""
			  """),format.raw/*420.6*/("""alertify.alert('Debe seleccionar un Equipo');
		  """),format.raw/*421.5*/("""}"""),format.raw/*421.6*/("""else if($('#id_bodega_mecP').val()==0)"""),format.raw/*421.44*/("""{"""),format.raw/*421.45*/("""
			  """),format.raw/*422.6*/("""alertify.alert('Debe seleccionar una """),_display_(/*422.44*/mapDiccionario/*422.58*/.get("Bodega")),format.raw/*422.72*/("""/Proyecto');
		  """),format.raw/*423.5*/("""}"""),format.raw/*423.6*/("""else if($('#id_mantEstadoOperacionalP').val()==0)"""),format.raw/*423.55*/("""{"""),format.raw/*423.56*/("""
			  """),format.raw/*424.6*/("""alertify.alert('Debe ingresar un Estado en Operacional');
		  """),format.raw/*425.5*/("""}"""),format.raw/*425.6*/("""else if($('#id_mantEstadoEnTallerP').val()==0)"""),format.raw/*425.52*/("""{"""),format.raw/*425.53*/("""
			  """),format.raw/*426.6*/("""alertify.alert('Debe ingresar un Estado en Taller');
		  """),format.raw/*427.5*/("""}"""),format.raw/*427.6*/("""else if($('#id_mantActividadP').val()==0)"""),format.raw/*427.47*/("""{"""),format.raw/*427.48*/("""
			  """),format.raw/*428.6*/("""alertify.alert('Debe ingresar una Actividad');
	      """),format.raw/*429.8*/("""}"""),format.raw/*429.9*/("""else if($('#id_mantTipoActividadP').val()==0)"""),format.raw/*429.54*/("""{"""),format.raw/*429.55*/("""
			  """),format.raw/*430.6*/("""alertify.alert('Debe ingresar un tipo de Actividad');
		  """),format.raw/*431.5*/("""}"""),format.raw/*431.6*/("""else if($('#id_mantItemIntervenidoP').val()==0)"""),format.raw/*431.53*/("""{"""),format.raw/*431.54*/("""
			  """),format.raw/*432.6*/("""alertify.alert('Debe ingresar un Item Intervenido');
		  """),format.raw/*433.5*/("""}"""),format.raw/*433.6*/("""else if($('#lecturaTer_mecP').val()=="0.00")"""),format.raw/*433.50*/("""{"""),format.raw/*433.51*/("""
			  """),format.raw/*434.6*/("""alertify.alert('Debe ingresar Lect_TER que debe ser mayor a cero');
		  """),format.raw/*435.5*/("""}"""),format.raw/*435.6*/("""else"""),format.raw/*435.10*/("""{"""),format.raw/*435.11*/("""
			"""),format.raw/*436.4*/("""let data = signaturePad5.toDataURL('image/png');
			let encodedImage = data.split(",");
			$("#firmaEjecutor").val(encodedImage[1]);
			
			data = signaturePad6.toDataURL('image/png');
			encodedImage = data.split(",");
			$("#firmaAprobador").val(encodedImage[1]);
			document.getElementById('enCarga').style.display="block";
			$("#grabarRptOperadorMecanico").submit();
		  """),format.raw/*445.5*/("""}"""),format.raw/*445.6*/("""
	"""),format.raw/*446.2*/("""}"""),format.raw/*446.3*/("""
	
"""),format.raw/*448.1*/("""</script>
	"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],fecha:String,listEstaOperacional:List[tables.MantEstadoEnObra],listOperMec:List[tables.MantOperadorMecanico],listTipoMantencion:List[tables.TipoMantencion],listEstadoEnTaller:List[tables.MantEstadoEnTaller],listActividad:List[tables.MantActividad],listTipoActividad:List[tables.MantTipoActividad],listItemIntervenido:List[tables.MantItemIntervenido],listComponentes:List[tables.MantComponente]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,fecha,listEstaOperacional,listOperMec,listTipoMantencion,listEstadoEnTaller,listActividad,listTipoActividad,listItemIntervenido,listComponentes)

  def f:((Map[String,String],String,List[tables.MantEstadoEnObra],List[tables.MantOperadorMecanico],List[tables.TipoMantencion],List[tables.MantEstadoEnTaller],List[tables.MantActividad],List[tables.MantTipoActividad],List[tables.MantItemIntervenido],List[tables.MantComponente]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,fecha,listEstaOperacional,listOperMec,listTipoMantencion,listEstadoEnTaller,listActividad,listTipoActividad,listItemIntervenido,listComponentes) => apply(mapDiccionario,fecha,listEstaOperacional,listOperMec,listTipoMantencion,listEstadoEnTaller,listActividad,listTipoActividad,listItemIntervenido,listComponentes)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuMantencion/mantReportNewMecanicoPreventivo.scala.html
                  HASH: 9a806cb560029b7e063a08131126c2376bf5789d
                  MATRIX: 1247->1|1791->452|3163->1797|3186->1811|3221->1825|3977->2554|4000->2568|4035->2582|4666->3186|4715->3219|4754->3220|4804->3242|4847->3258|4861->3263|4885->3266|4916->3270|4930->3275|4958->3282|5008->3301|5049->3314|5508->3746|5556->3778|5595->3779|5645->3801|5688->3817|5702->3822|5726->3825|5757->3829|5771->3834|5799->3841|5849->3860|5890->3873|6332->4288|6375->4315|6414->4316|6464->4338|6507->4354|6521->4359|6545->4362|6576->4366|6590->4371|6618->4378|6668->4397|6709->4410|7168->4841|7216->4872|7256->4873|7307->4895|7351->4911|7366->4916|7391->4919|7423->4923|7438->4928|7467->4935|7518->4954|7560->4967|8022->5401|8068->5430|8108->5431|8159->5453|8203->5469|8218->5474|8243->5477|8275->5481|8290->5486|8319->5493|8370->5512|8412->5525|10462->7547|10489->7552|10857->7892|10884->7897|16016->13000|16046->13001|16077->13004|16238->13137|16267->13138|16299->13142|16375->13190|16404->13191|16441->13199|16471->13200|16503->13204|16562->13235|16591->13236|16622->13239|16704->13292|16734->13293|16766->13297|16864->13366|16894->13367|16927->13372|17144->13560|17174->13561|17210->13569|17246->13576|17276->13577|17312->13585|17381->13626|17410->13627|17443->13631|17473->13632|17509->13640|17601->13704|17630->13705|17664->13711|17693->13712|17733->13723|17767->13729|17796->13730|17828->13734|17858->13735|17891->13740|18071->13892|18100->13893|18135->13900|18164->13901|18220->13928|18250->13929|18282->13933|18362->13984|18392->13985|18425->13990|18614->14151|18643->14152|18675->14156|18705->14157|18738->14162|18879->14275|18908->14276|18939->14279|18968->14280|18998->14282|19027->14283|19059->14287|19118->14317|19148->14318|19179->14321|19390->14503|19420->14504|19453->14509|19665->14693|19694->14694|19726->14698|19756->14699|19789->14704|19930->14817|19959->14818|19989->14820|20018->14821|20050->14825|20109->14855|20139->14856|20170->14859|20298->14958|20328->14959|20360->14963|20435->15009|20465->15010|20498->15015|20676->15165|20705->15166|20736->15169|20765->15170|20795->15172|20824->15173|20856->15177|20915->15207|20945->15208|20976->15211|21104->15310|21134->15311|21166->15315|21284->15405|21313->15406|21345->15410|21374->15411|21406->15415|21456->15436|21486->15437|21519->15442|21657->15552|21686->15553|21717->15556|21746->15557|21776->15559|21805->15560|21839->15566|21899->15597|21929->15598|21960->15601|22068->15680|22098->15681|22130->15685|22237->15763|22267->15764|22300->15769|22449->15890|22476->15895|22511->15902|22540->15903|22571->15906|22600->15907|22632->15911|22661->15912|22693->15916|22809->16004|22836->16009|22870->16015|22899->16016|22932->16021|22961->16022|22993->16026|23053->16057|23083->16058|23114->16061|23222->16140|23252->16141|23284->16145|23391->16223|23421->16224|23454->16229|23602->16349|23629->16354|23664->16361|23693->16362|23724->16365|23753->16366|23785->16370|23814->16371|23846->16375|23962->16463|23989->16468|24023->16474|24052->16475|24082->16477|24111->16478|24143->16482|24198->16508|24228->16509|24266->16519|24325->16549|24355->16550|24389->16556|24469->16608|24498->16609|24568->16650|24598->16651|24632->16657|24722->16719|24751->16720|24818->16758|24848->16759|24882->16765|24960->16815|24989->16816|25056->16854|25086->16855|25120->16861|25186->16899|25210->16913|25246->16927|25291->16944|25320->16945|25398->16994|25428->16995|25462->17001|25552->17063|25581->17064|25656->17110|25686->17111|25720->17117|25805->17174|25834->17175|25904->17216|25934->17217|25968->17223|26050->17277|26079->17278|26153->17323|26183->17324|26217->17330|26303->17388|26332->17389|26408->17436|26438->17437|26472->17443|26557->17500|26586->17501|26659->17545|26689->17546|26723->17552|26823->17624|26852->17625|26885->17629|26915->17630|26947->17634|27351->18010|27380->18011|27410->18013|27439->18014|27470->18017
                  LINES: 28->1|37->6|65->34|65->34|65->34|82->51|82->51|82->51|95->64|95->64|95->64|96->65|96->65|96->65|96->65|96->65|96->65|96->65|97->66|98->67|109->78|109->78|109->78|110->79|110->79|110->79|110->79|110->79|110->79|110->79|111->80|112->81|123->92|123->92|123->92|124->93|124->93|124->93|124->93|124->93|124->93|124->93|125->94|126->95|137->106|137->106|137->106|138->107|138->107|138->107|138->107|138->107|138->107|138->107|139->108|140->109|151->120|151->120|151->120|152->121|152->121|152->121|152->121|152->121|152->121|152->121|153->122|154->123|203->172|203->172|211->180|211->180|339->308|339->308|340->309|342->311|342->311|343->312|344->313|344->313|344->313|344->313|345->314|346->315|346->315|347->316|347->316|347->316|348->317|348->317|348->317|349->318|351->320|351->320|352->321|352->321|352->321|353->322|354->323|354->323|354->323|354->323|355->324|356->325|356->325|357->326|357->326|358->327|359->328|359->328|359->328|359->328|360->329|362->331|362->331|364->333|364->333|364->333|364->333|365->334|365->334|365->334|366->335|368->337|368->337|368->337|368->337|369->338|371->340|371->340|372->341|372->341|373->342|373->342|375->344|375->344|375->344|376->345|378->347|378->347|379->348|382->351|382->351|382->351|382->351|383->352|385->354|385->354|386->355|386->355|388->357|388->357|388->357|389->358|391->360|391->360|392->361|392->361|392->361|393->362|396->365|396->365|397->366|397->366|398->367|398->367|400->369|400->369|400->369|401->370|403->372|403->372|404->373|406->375|406->375|406->375|406->375|407->376|407->376|407->376|408->377|410->379|410->379|411->380|411->380|412->381|412->381|415->384|415->384|415->384|416->385|417->386|417->386|418->387|419->388|419->388|420->389|421->390|421->390|422->391|422->391|423->392|423->392|423->392|423->392|424->393|425->394|425->394|426->395|426->395|428->397|428->397|430->399|430->399|430->399|431->400|432->401|432->401|433->402|434->403|434->403|435->404|436->405|436->405|437->406|437->406|438->407|438->407|438->407|438->407|439->408|440->409|440->409|441->410|441->410|442->411|442->411|444->413|444->413|444->413|446->415|446->415|446->415|447->416|448->417|448->417|448->417|448->417|449->418|450->419|450->419|450->419|450->419|451->420|452->421|452->421|452->421|452->421|453->422|453->422|453->422|453->422|454->423|454->423|454->423|454->423|455->424|456->425|456->425|456->425|456->425|457->426|458->427|458->427|458->427|458->427|459->428|460->429|460->429|460->429|460->429|461->430|462->431|462->431|462->431|462->431|463->432|464->433|464->433|464->433|464->433|465->434|466->435|466->435|466->435|466->435|467->436|476->445|476->445|477->446|477->446|479->448
                  -- GENERATED --
              */
          