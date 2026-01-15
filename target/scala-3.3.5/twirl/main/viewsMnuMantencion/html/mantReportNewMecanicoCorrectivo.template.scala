
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

object mantReportNewMecanicoCorrectivo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template10[Map[String,String],String,List[tables.MantEstadoEnObra],List[tables.MantOperadorMecanico],List[tables.TipoMantencion],List[tables.MantEstadoEnTaller],List[tables.MantActividad],List[tables.MantTipoActividad],List[tables.MantItemIntervenido],List[tables.MantComponente],play.twirl.api.HtmlFormat.Appendable] {

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
			        	<input type="hidden" id="id_equipo_mec" name="id_equipo_mec" value="0">
			        	<div id="nombreEquipo_mec">
			        		<div class="input-group input-group-sm">
							  <input class="form-control" type="text" 
								onclick="$('#modalListaEquipos_oper').modal('show');" 
								id="valNomEquip_mec"
								value="--- select ---" readonly>
							  <div class="input-group-append">
							    <span class="input-group-text" onclick="$('#modalListaEquipos_oper').modal('show');">Buscar</span>
							  </div>
							</div> 
	    				</div>
			        </td>
				</tr>
				<tr style="display: none">
					<td style="text-align:left;"><strong>TIPO DE PLAN:</strong></td>
			        <td style="text-align:left" width="70%" colspan="4">
						<input type="hidden" id="id_tipoPlan_mec" name="id_tipoPlan_mec" value="0">
						<input class="form-control" id="tipoPlan_mec" type="text" value="" readonly>
			        </td>
				</tr>
				<tr>
					<td style="text-align:left;"><strong>"""),_display_(/*34.44*/mapDiccionario/*34.58*/.get("BODEGA")),format.raw/*34.72*/("""/PROYECTO (*):</strong></td>
			        <td style="text-align:left" width="70%" colspan="4">
			        	<input type="hidden" id="id_bodega_mec" name="id_bodega_mec" value="0">
			        	<div id="nombreBodega_mec">
			        		<div class="input-group input-group-sm">
							  <input class="form-control" type="text" 
								onclick="$('#modalListaBodegas').modal('show');" 
								id="valNomBod_mec"
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
						<input class="form-control" id="tipoBodega_mec" type="text" value="" readonly>
			        </td>
				</tr>
				<tr>
					<td style="text-align:left;"><strong>ESTADO OPERACIONAL (*):</strong></td>
			        <td style="text-align:left" width="70%" colspan="4">
			        	<select class="form-control form-control-sm" 
							id="id_mantEstadoOperacional" 
							name="id_mantEstadoOperacional"
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
							id="id_mantEstadoEnTaller" 
							name="id_mantEstadoEnTaller"
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
							id="id_mantActividad" 
							name="id_mantActividad"
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
							id="id_mantTipoActividad" 
							name="id_mantTipoActividad"
							required disabled>
				        		<option class="blank" value="0">-- Select --</option>
			            		"""),_display_(/*106.19*/for(lista <- listItemIntervenido) yield /*106.52*/{_display_(Seq[Any](format.raw/*106.53*/("""
			                		"""),format.raw/*107.22*/("""<option value=""""),_display_(/*107.38*/lista/*107.43*/.id),format.raw/*107.46*/("""" >"""),_display_(/*107.50*/lista/*107.55*/.nombre),format.raw/*107.62*/("""</option>
								""")))}),format.raw/*108.10*/("""
				        """),format.raw/*109.13*/("""</select> 
			        </td>
				</tr>
				
				
				
				<tr>
					<td style="text-align:left;"><strong>ITEM INTERVENIDO (*):</strong></td>
			        <td style="text-align:left" width="70%" colspan="4">
			        	<select class="form-control form-control-sm" 
							id="id_mantItemIntervenido" 
							name="id_mantItemIntervenido"
							required disabled>
				        		<option class="blank" value="0">-- Select --</option>
			            		"""),_display_(/*123.19*/for(lista <- listComponentes) yield /*123.48*/{_display_(Seq[Any](format.raw/*123.49*/("""
			                		"""),format.raw/*124.22*/("""<option value=""""),_display_(/*124.38*/lista/*124.43*/.id),format.raw/*124.46*/("""" >"""),_display_(/*124.50*/lista/*124.55*/.nombre),format.raw/*124.62*/("""</option>
								""")))}),format.raw/*125.10*/("""
				        """),format.raw/*126.13*/("""</select> 
			        </td>
				</tr>
				
				
				
				<tr>
					<td style="text-align:left;"><strong>COMPONENTES:</strong></td>
			        <td style="text-align:left" width="70%" colspan="4">
			        	<table id="tblComponentesC" class="table table-sm table-bordered table-condensed table-fluid">
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
										<input type="hidden" class="classIdComponentesC" id="id_componenteC0" name="id_mantComponenteC[]" value="0">
										<div id="componenteC0">
											<div class="input-group input-group-sm">
											  <input class="form-control" type="text" onclick="showComponentesC();" readonly>
											  <div class="input-group-append">
											    <span class="input-group-text" onclick="showComponentesC();">Buscar</span>
											  </div>
											</div>
					    				</div>
					    			</td>
									<td>
										<div id="divCantidadC0">
											<input type='text' style='text-align:right;' class='form-control form-control-sm' 
												name='cantidadC[]' id='cantidadC0' readonly></div>
									</td>
									<td style="text-align:center;">
										<div id='delLineaC0'></div>
									</td>
									<td style='display:none' id="tdId_componenteC0">0</td>
								</tr>
							</tbody>
			        	</table>
					</td>
				</tr>
				<tr>
					<td style="text-align:left;"><strong>Fecha_INI:</strong></td>
			        <td style="text-align:left">
						<input type="date" class="form-control form-control-sm left" style="max-width:150px"
							id="fechaIni_mec" 
							name="fechaIni_mec"
							onblur="validarFechaIni_mec()"
							value=""""),_display_(/*178.16*/fecha),format.raw/*178.21*/("""">
			        </td>
					<td style="text-align:left;"><strong>Fecha_TER:</strong></td>
			        <td style="text-align:left" colspan="2">
						<input type="date" class="form-control form-control-sm left"  style="max-width:150px"
							id="fechaTer_mec" 
							name="fechaTer_mec"
							onblur="validarFechaTer_mec()"
							value=""""),_display_(/*186.16*/fecha),format.raw/*186.21*/("""">
			        </td>
				</tr>	
				<tr>
					<td style="text-align:left;"><strong>Hr_INI:</strong></td>
			        <td style="text-align:left">
						<input type="time" class="form-control form-control-sm left" style="max-width:100px"
							id="horaIni_mec" 
							name="horaIni_mec"
							onblur="validarHoraIni_mec()">
			        </td>
					<td style="text-align:left;"><strong>Hr_TER:</strong></td>
			        <td style="text-align:left" colspan="2">
						<input type="time" class="form-control form-control-sm left"  style="max-width:100px"
							id="horaTer_mec" 
							name="horaTer_mec"
							onblur="validarHoraTer_mec()">
			        </td>
				</tr>
				
				<tr>
					<td style="text-align:left;"><strong><div id="lectIni_mec">Lect_INI:</div></strong></td>
			        <td style="text-align:left">
						<input type="hidden" id="idUnidadMant_mec" name="id_unidadMantencion">
						<input type="text" class="form-control form-control-sm left" style="max-width:200px"
							id="lecturaIni_mec" 
							name="lecturaIni_mec"
							value = "0.00"
							onClick="this.select();"
							onfocus="value=value.replace(/,/g,'')" 
							onfocusout = "if(value.trim()=='') value=0; value = formatStandar(value, 2);"
							onkeydown="return ingresoDouble(window.event)"
							onchange="validarLectIni_mec()"
							autocomplete="off"
							required disabled>
			        </td>
					<td style="text-align:left;"><strong><div id="lectTer_mec">Lect_TER(*):</div></strong></td>
			        <td style="text-align:left" colspan="2">
						<input type="text" class="form-control form-control-sm left" style="max-width:200px"
							id="lecturaTer_mec" 
							name="lecturaTer_mec"
							value = "0.00"
							onClick="this.select();"
							onfocus="value=value.replace(/,/g,'')" 
							onfocusout = "if(value.trim()=='') value=0; value = formatStandar(value, 2);"
							onkeydown="return ingresoDouble(window.event)"
							onchange="validarLectTer_mec()"
							autocomplete="off"
							required disabled>
			        </td>
				</tr>
				
				<tr>
					<td style="text-align:left;"><strong><div id="cant_mec">CANT:</div></strong></td>
			        <td style="text-align:left" width="70%" colspan="4">
						<input type="text" class="form-control form-control-sm left"
							id="cantidad_mec" 
							name="cantidad_mec"
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
							id="descTrabajo"
							name="descTrabajo" 
							autocomplete="off"></textarea>
			        </td>
				</tr>
				<tr>
					<td style="text-align:left;"><strong>ESTADO FINAL:</strong></td>
			        <td style="text-align:left" width="70%" colspan="4">
						<textarea class="form-control form-control-sm" rows="1" 
							id="estadoFinal"
							name="estadoFinal" 
							autocomplete="off"></textarea>
			        </td>
				</tr>
				<tr>
					<td style="text-align:left;"><strong>OBSERVACIONES:</strong></td>
			        <td style="text-align:left" width="70%" colspan="4">
						<textarea class="form-control form-control-sm" rows="2" 
							id="observaciones_mec"
							name="observaciones_mec" 
							autocomplete="off"></textarea>
			        </td>
				</tr>
				
				<tr>
					<td style="text-align:left;"  colspan="5">
						FIRMA MECANICO:<br>
						<div class="wrapper" align="center">
						<canvas id="signature-pad3" class="signature-pad" width=350 height=90 style="border:1px solid #000000;"></canvas>
						</div>
						<button type="button" class="btn btn-sm btn-warning" style="font-size: 10px;" onclick="signaturePad3.clear()">Borrar firma</button>
					</td>
				</tr>
				<tr>
					<td style="text-align:left;"   colspan="5">
						FIRMA AUTORIZADA:<br>
						<div class="wrapper" align="center">
						<canvas id="signature-pad4" class="signature-pad" width=350 height=90 style="border:1px solid #000000;"></canvas>
						</div>
						<button type="button" class="btn btn-sm btn-warning" style="font-size: 10px;" onclick="signaturePad4.clear()">Borrar firma</button>
					</td>
				</tr>
			
				<tr>
					<td colspan="5" style="text-align:center;">
						<button type="button" class="btn btn-sm btn-success" style="font-size: 10px;"onclick="grabarMecanico();" >GRABAR REPORT
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="btn btn-sm btn-warning" style="font-size: 10px;" onclick="location.href = '/home/'">SALIR</button>
					</td>
				</tr>
			</thead>
		</table>
		
		
<script>

	function validarLectIni_mec()"""),format.raw/*315.31*/("""{"""),format.raw/*315.32*/("""
		"""),format.raw/*316.3*/("""let lecturaIni = $("#lecturaIni_mec").val().replace(/,/g,'');
		let lecturaTer = $("#lecturaTer_mec").val().replace(/,/g,'');
		try"""),format.raw/*318.6*/("""{"""),format.raw/*318.7*/("""
			"""),format.raw/*319.4*/("""lectAnterior = lectAnterior.replace(/,/g,'');
		"""),format.raw/*320.3*/("""}"""),format.raw/*320.4*/("""catch(e)"""),format.raw/*320.12*/("""{"""),format.raw/*320.13*/("""
			"""),format.raw/*321.4*/("""lectAnterior = lectAnterior;
		"""),format.raw/*322.3*/("""}"""),format.raw/*322.4*/("""
		"""),format.raw/*323.3*/("""if(parseFloat(lecturaIni) < parseFloat(lectAnterior))"""),format.raw/*323.56*/("""{"""),format.raw/*323.57*/("""
			"""),format.raw/*324.4*/("""alertify.alert("La lectura inicial no puede ser menor a la ultima lectura registrada");
			$("#lecturaIni_mec").val(formatStandar(lectAnterior,2));
		"""),format.raw/*326.3*/("""}"""),format.raw/*326.4*/("""else if(lecturaTer!="0.00")"""),format.raw/*326.31*/("""{"""),format.raw/*326.32*/("""
			"""),format.raw/*327.4*/("""if(parseFloat(lecturaIni) > parseFloat(lecturaTer))"""),format.raw/*327.55*/("""{"""),format.raw/*327.56*/("""
				"""),format.raw/*328.5*/("""alertify.alert("La lectura inicial no puede ser menor a la lectura de termino");
				$("#lecturaIni_mec").val(formatStandar(formatStandar(lectAnterior,2)));
			"""),format.raw/*330.4*/("""}"""),format.raw/*330.5*/("""else"""),format.raw/*330.9*/("""{"""),format.raw/*330.10*/("""
				"""),format.raw/*331.5*/("""let dif = parseFloat(lecturaTer) - parseFloat(lecturaIni);
				$("#cantidad_mec").val(formatStandar(dif,2));
			"""),format.raw/*333.4*/("""}"""),format.raw/*333.5*/("""
		"""),format.raw/*334.3*/("""}"""),format.raw/*334.4*/("""
	"""),format.raw/*335.2*/("""}"""),format.raw/*335.3*/("""
	
	"""),format.raw/*337.2*/("""function validarLectTer_mec()"""),format.raw/*337.31*/("""{"""),format.raw/*337.32*/("""
		"""),format.raw/*338.3*/("""let lecturaIni = $("#lecturaIni_mec").val().replace(/,/g,'');
		let lecturaTer = $("#lecturaTer_mec").val().replace(/,/g,'');
			if(parseFloat(lecturaIni) > parseFloat(lecturaTer))"""),format.raw/*340.55*/("""{"""),format.raw/*340.56*/("""
				"""),format.raw/*341.5*/("""alertify.alert("La lectura inicial no puede ser menor a la lectura de termino");
				$("#lecturaTer_mec").val(formatStandar(0,2));
				$("#cantidad_mec").val(formatStandar(0,2));
			"""),format.raw/*344.4*/("""}"""),format.raw/*344.5*/("""else"""),format.raw/*344.9*/("""{"""),format.raw/*344.10*/("""
				"""),format.raw/*345.5*/("""let dif = parseFloat(lecturaTer) - parseFloat(lecturaIni);
				$("#cantidad_mec").val(formatStandar(dif,2));
			"""),format.raw/*347.4*/("""}"""),format.raw/*347.5*/("""
	"""),format.raw/*348.2*/("""}"""),format.raw/*348.3*/("""
	
	"""),format.raw/*350.2*/("""function validarHoraIni_mec()"""),format.raw/*350.31*/("""{"""),format.raw/*350.32*/("""
		"""),format.raw/*351.3*/("""let horaIni = $("#horaIni_mec").val();
		let horaTer = $("#horaTer_mec").val();
		if(horaTer!="")"""),format.raw/*353.18*/("""{"""),format.raw/*353.19*/("""
			"""),format.raw/*354.4*/("""if(parseFloat(horaIni) >= parseFloat(horaTer))"""),format.raw/*354.50*/("""{"""),format.raw/*354.51*/("""
				"""),format.raw/*355.5*/("""alertify.alert("La hora inicial no puede ser menor o igual a la hora de termino");
				$("#horaIni_mec").val("");
				$("#horaTer_mec").val("");
			"""),format.raw/*358.4*/("""}"""),format.raw/*358.5*/("""
		"""),format.raw/*359.3*/("""}"""),format.raw/*359.4*/("""
	"""),format.raw/*360.2*/("""}"""),format.raw/*360.3*/("""
	
	"""),format.raw/*362.2*/("""function validarHoraTer_mec()"""),format.raw/*362.31*/("""{"""),format.raw/*362.32*/("""
		"""),format.raw/*363.3*/("""let horaIni = $("#horaIni_mec").val();
		let horaTer = $("#horaTer_mec").val();
		if(horaIni=="")"""),format.raw/*365.18*/("""{"""),format.raw/*365.19*/("""
			"""),format.raw/*366.4*/("""alertify.alert("Primero debe ingresar la hora inicial");
			$("#horaTer_mec").val("");
		"""),format.raw/*368.3*/("""}"""),format.raw/*368.4*/("""else"""),format.raw/*368.8*/("""{"""),format.raw/*368.9*/("""
			"""),format.raw/*369.4*/("""if(horaIni > horaTer)"""),format.raw/*369.25*/("""{"""),format.raw/*369.26*/("""
				"""),format.raw/*370.5*/("""alertify.alert("La hora inicial no puede ser menor a la hora de termino");
				$("#horaTer_mec").val("");
			"""),format.raw/*372.4*/("""}"""),format.raw/*372.5*/("""
		"""),format.raw/*373.3*/("""}"""),format.raw/*373.4*/("""
	"""),format.raw/*374.2*/("""}"""),format.raw/*374.3*/("""
	
	"""),format.raw/*376.2*/("""function validarFechaIni_mec()"""),format.raw/*376.32*/("""{"""),format.raw/*376.33*/("""
		"""),format.raw/*377.3*/("""let fechaIni = new Date($("#fechaIni_mec").val());
		if(isValidDate(fechaIni))"""),format.raw/*378.28*/("""{"""),format.raw/*378.29*/("""
			"""),format.raw/*379.4*/("""let fechaTer = new Date($("#fechaTer_mec").val());
			if(fechaIni > fechaTer)"""),format.raw/*380.27*/("""{"""),format.raw/*380.28*/("""
				"""),format.raw/*381.5*/("""alertify.alert("La fecha de inicio no puede ser posterior a la fecha de termino.");
	        		$('#fechaIni_mec').val('"""),_display_(/*382.37*/fecha),format.raw/*382.42*/("""');
			"""),format.raw/*383.4*/("""}"""),format.raw/*383.5*/("""
		"""),format.raw/*384.3*/("""}"""),format.raw/*384.4*/("""else"""),format.raw/*384.8*/("""{"""),format.raw/*384.9*/("""
			"""),format.raw/*385.4*/("""alertify.alert("Debe ingresar una fecha valida.");
	        		$('#fechaIni_mec').val('"""),_display_(/*386.37*/fecha),format.raw/*386.42*/("""');
		"""),format.raw/*387.3*/("""}"""),format.raw/*387.4*/("""
		
	"""),format.raw/*389.2*/("""}"""),format.raw/*389.3*/("""
	
	"""),format.raw/*391.2*/("""function validarFechaTer_mec()"""),format.raw/*391.32*/("""{"""),format.raw/*391.33*/("""
		"""),format.raw/*392.3*/("""let fechaTer = new Date($("#fechaTer_mec").val());
		if(isValidDate(fechaTer))"""),format.raw/*393.28*/("""{"""),format.raw/*393.29*/("""
			"""),format.raw/*394.4*/("""let fechaIni = new Date($("#fechaIni_mec").val());
			if(fechaIni > fechaTer)"""),format.raw/*395.27*/("""{"""),format.raw/*395.28*/("""
				"""),format.raw/*396.5*/("""alertify.alert("La fecha de termino no puede ser anterior a la fecha de inicio.");
	        		$('#fechaTer_mec').val('"""),_display_(/*397.37*/fecha),format.raw/*397.42*/("""');
			"""),format.raw/*398.4*/("""}"""),format.raw/*398.5*/("""
		"""),format.raw/*399.3*/("""}"""),format.raw/*399.4*/("""else"""),format.raw/*399.8*/("""{"""),format.raw/*399.9*/("""
			"""),format.raw/*400.4*/("""alertify.alert("Debe ingresar una fecha valida.");
	        		$('#fechaTer_mec').val('"""),_display_(/*401.37*/fecha),format.raw/*401.42*/("""');
		"""),format.raw/*402.3*/("""}"""),format.raw/*402.4*/("""
	"""),format.raw/*403.2*/("""}"""),format.raw/*403.3*/("""
	
	"""),format.raw/*405.2*/("""function grabarMecanico()"""),format.raw/*405.27*/("""{"""),format.raw/*405.28*/("""
		  
		  """),format.raw/*407.5*/("""if($('#id_mecanico').val()==0)"""),format.raw/*407.35*/("""{"""),format.raw/*407.36*/("""
			  """),format.raw/*408.6*/("""alertify.alert('Debe seleccionar un Mecanico');
		  """),format.raw/*409.5*/("""}"""),format.raw/*409.6*/("""else if($('#id_tipoMantencion').val()==0)"""),format.raw/*409.47*/("""{"""),format.raw/*409.48*/("""
			  """),format.raw/*410.6*/("""alertify.alert('Debe seleccionar un Tipo de Mantencion');
		  """),format.raw/*411.5*/("""}"""),format.raw/*411.6*/("""else if($('#id_equipo_mec').val()==0)"""),format.raw/*411.43*/("""{"""),format.raw/*411.44*/("""
			  """),format.raw/*412.6*/("""alertify.alert('Debe seleccionar un Equipo');
		  """),format.raw/*413.5*/("""}"""),format.raw/*413.6*/("""else if($('#id_bodega_mec').val()==0)"""),format.raw/*413.43*/("""{"""),format.raw/*413.44*/("""
			  """),format.raw/*414.6*/("""alertify.alert('Debe seleccionar una """),_display_(/*414.44*/mapDiccionario/*414.58*/.get("Bodega")),format.raw/*414.72*/("""/Proyecto');
		  """),format.raw/*415.5*/("""}"""),format.raw/*415.6*/("""else if($('#id_mantEstadoOperacional').val()==0)"""),format.raw/*415.54*/("""{"""),format.raw/*415.55*/("""
			  """),format.raw/*416.6*/("""alertify.alert('Debe ingresar un Estado en Operacional');
		  """),format.raw/*417.5*/("""}"""),format.raw/*417.6*/("""else if($('#id_mantEstadoEnTaller').val()==0)"""),format.raw/*417.51*/("""{"""),format.raw/*417.52*/("""
			  """),format.raw/*418.6*/("""alertify.alert('Debe ingresar un Estado en Taller');
		  """),format.raw/*419.5*/("""}"""),format.raw/*419.6*/("""else if($('#id_mantActividad').val()==0)"""),format.raw/*419.46*/("""{"""),format.raw/*419.47*/("""
			  """),format.raw/*420.6*/("""alertify.alert('Debe ingresar una Actividad');
	      """),format.raw/*421.8*/("""}"""),format.raw/*421.9*/("""else if($('#id_mantTipoActividad').val()==0)"""),format.raw/*421.53*/("""{"""),format.raw/*421.54*/("""
			  """),format.raw/*422.6*/("""alertify.alert('Debe ingresar un tipo de Actividad');
		  """),format.raw/*423.5*/("""}"""),format.raw/*423.6*/("""else if($('#id_mantItemIntervenido').val()==0)"""),format.raw/*423.52*/("""{"""),format.raw/*423.53*/("""
			  """),format.raw/*424.6*/("""alertify.alert('Debe ingresar un Item Intervenido');
		  """),format.raw/*425.5*/("""}"""),format.raw/*425.6*/("""else if($('#lecturaTer_mec').val()=="0.00")"""),format.raw/*425.49*/("""{"""),format.raw/*425.50*/("""
			  """),format.raw/*426.6*/("""alertify.alert('Debe ingresar Lect_TER que debe ser mayor a cero');
		  """),format.raw/*427.5*/("""}"""),format.raw/*427.6*/("""else"""),format.raw/*427.10*/("""{"""),format.raw/*427.11*/("""
			"""),format.raw/*428.4*/("""let data = signaturePad3.toDataURL('image/png');
			let encodedImage = data.split(",");
			$("#firmaEjecutor").val(encodedImage[1]);
			
			data = signaturePad4.toDataURL('image/png');
			encodedImage = data.split(",");
			$("#firmaAprobador").val(encodedImage[1]);
			document.getElementById('enCarga').style.display="block";
			$("#grabarRptOperadorMecanico").submit();
		  """),format.raw/*437.5*/("""}"""),format.raw/*437.6*/("""
	"""),format.raw/*438.2*/("""}"""),format.raw/*438.3*/("""
	
"""),format.raw/*440.1*/("""</script>











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
                  SOURCE: app/viewsMnuMantencion/mantReportNewMecanicoCorrectivo.scala.html
                  HASH: dfe7f96ca2aa55cd4a356e98715e24fda3d881a1
                  MATRIX: 1247->1|1791->452|3180->1814|3203->1828|3238->1842|3990->2567|4013->2581|4048->2595|4676->3196|4725->3229|4764->3230|4814->3252|4857->3268|4871->3273|4895->3276|4926->3280|4940->3285|4968->3292|5018->3311|5059->3324|5516->3754|5564->3786|5603->3787|5653->3809|5696->3825|5710->3830|5734->3833|5765->3837|5779->3842|5807->3849|5857->3868|5898->3881|6338->4294|6381->4321|6420->4322|6470->4344|6513->4360|6527->4365|6551->4368|6582->4372|6596->4377|6624->4384|6674->4403|6715->4416|7172->4845|7222->4878|7262->4879|7313->4901|7357->4917|7372->4922|7397->4925|7429->4929|7444->4934|7473->4941|7524->4960|7566->4973|8041->5420|8087->5449|8127->5450|8178->5472|8222->5488|8237->5493|8262->5496|8294->5500|8309->5505|8338->5512|8389->5531|8431->5544|10493->7578|10520->7583|10885->7920|10912->7925|16024->13008|16054->13009|16085->13012|16244->13143|16273->13144|16305->13148|16381->13196|16410->13197|16447->13205|16477->13206|16509->13210|16568->13241|16597->13242|16628->13245|16710->13298|16740->13299|16772->13303|16950->13453|16979->13454|17035->13481|17065->13482|17097->13486|17177->13537|17207->13538|17240->13543|17428->13703|17457->13704|17489->13708|17519->13709|17552->13714|17692->13826|17721->13827|17752->13830|17781->13831|17811->13833|17840->13834|17872->13838|17930->13867|17960->13868|17991->13871|18200->14051|18230->14052|18263->14057|18473->14239|18502->14240|18534->14244|18564->14245|18597->14250|18737->14362|18766->14363|18796->14365|18825->14366|18857->14370|18915->14399|18945->14400|18976->14403|19102->14500|19132->14501|19164->14505|19239->14551|19269->14552|19302->14557|19478->14705|19507->14706|19538->14709|19567->14710|19597->14712|19626->14713|19658->14717|19716->14746|19746->14747|19777->14750|19903->14847|19933->14848|19965->14852|20082->14941|20111->14942|20143->14946|20172->14947|20204->14951|20254->14972|20284->14973|20317->14978|20454->15087|20483->15088|20514->15091|20543->15092|20573->15094|20602->15095|20634->15099|20693->15129|20723->15130|20754->15133|20861->15211|20891->15212|20923->15216|21029->15293|21059->15294|21092->15299|21240->15419|21267->15424|21302->15431|21331->15432|21362->15435|21391->15436|21423->15440|21452->15441|21484->15445|21599->15532|21626->15537|21660->15543|21689->15544|21722->15549|21751->15550|21783->15554|21842->15584|21872->15585|21903->15588|22010->15666|22040->15667|22072->15671|22178->15748|22208->15749|22241->15754|22388->15873|22415->15878|22450->15885|22479->15886|22510->15889|22539->15890|22571->15894|22600->15895|22632->15899|22747->15986|22774->15991|22808->15997|22837->15998|22867->16000|22896->16001|22928->16005|22982->16030|23012->16031|23050->16041|23109->16071|23139->16072|23173->16078|23253->16130|23282->16131|23352->16172|23382->16173|23416->16179|23506->16241|23535->16242|23601->16279|23631->16280|23665->16286|23743->16336|23772->16337|23838->16374|23868->16375|23902->16381|23968->16419|23992->16433|24028->16447|24073->16464|24102->16465|24179->16513|24209->16514|24243->16520|24333->16582|24362->16583|24436->16628|24466->16629|24500->16635|24585->16692|24614->16693|24683->16733|24713->16734|24747->16740|24829->16794|24858->16795|24931->16839|24961->16840|24995->16846|25081->16904|25110->16905|25185->16951|25215->16952|25249->16958|25334->17015|25363->17016|25435->17059|25465->17060|25499->17066|25599->17138|25628->17139|25661->17143|25691->17144|25723->17148|26127->17524|26156->17525|26186->17527|26215->17528|26246->17531
                  LINES: 28->1|37->6|65->34|65->34|65->34|82->51|82->51|82->51|95->64|95->64|95->64|96->65|96->65|96->65|96->65|96->65|96->65|96->65|97->66|98->67|109->78|109->78|109->78|110->79|110->79|110->79|110->79|110->79|110->79|110->79|111->80|112->81|123->92|123->92|123->92|124->93|124->93|124->93|124->93|124->93|124->93|124->93|125->94|126->95|137->106|137->106|137->106|138->107|138->107|138->107|138->107|138->107|138->107|138->107|139->108|140->109|154->123|154->123|154->123|155->124|155->124|155->124|155->124|155->124|155->124|155->124|156->125|157->126|209->178|209->178|217->186|217->186|346->315|346->315|347->316|349->318|349->318|350->319|351->320|351->320|351->320|351->320|352->321|353->322|353->322|354->323|354->323|354->323|355->324|357->326|357->326|357->326|357->326|358->327|358->327|358->327|359->328|361->330|361->330|361->330|361->330|362->331|364->333|364->333|365->334|365->334|366->335|366->335|368->337|368->337|368->337|369->338|371->340|371->340|372->341|375->344|375->344|375->344|375->344|376->345|378->347|378->347|379->348|379->348|381->350|381->350|381->350|382->351|384->353|384->353|385->354|385->354|385->354|386->355|389->358|389->358|390->359|390->359|391->360|391->360|393->362|393->362|393->362|394->363|396->365|396->365|397->366|399->368|399->368|399->368|399->368|400->369|400->369|400->369|401->370|403->372|403->372|404->373|404->373|405->374|405->374|407->376|407->376|407->376|408->377|409->378|409->378|410->379|411->380|411->380|412->381|413->382|413->382|414->383|414->383|415->384|415->384|415->384|415->384|416->385|417->386|417->386|418->387|418->387|420->389|420->389|422->391|422->391|422->391|423->392|424->393|424->393|425->394|426->395|426->395|427->396|428->397|428->397|429->398|429->398|430->399|430->399|430->399|430->399|431->400|432->401|432->401|433->402|433->402|434->403|434->403|436->405|436->405|436->405|438->407|438->407|438->407|439->408|440->409|440->409|440->409|440->409|441->410|442->411|442->411|442->411|442->411|443->412|444->413|444->413|444->413|444->413|445->414|445->414|445->414|445->414|446->415|446->415|446->415|446->415|447->416|448->417|448->417|448->417|448->417|449->418|450->419|450->419|450->419|450->419|451->420|452->421|452->421|452->421|452->421|453->422|454->423|454->423|454->423|454->423|455->424|456->425|456->425|456->425|456->425|457->426|458->427|458->427|458->427|458->427|459->428|468->437|468->437|469->438|469->438|471->440
                  -- GENERATED --
              */
          