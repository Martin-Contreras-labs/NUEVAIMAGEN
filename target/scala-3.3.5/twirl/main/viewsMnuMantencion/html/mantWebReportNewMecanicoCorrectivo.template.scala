
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

object mantWebReportNewMecanicoCorrectivo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template15[Map[String,String],String,List[tables.MantEstadoEnObra],tables.MantOperadorMecanico,List[tables.TipoMantencion],List[tables.MantEstadoEnTaller],List[tables.MantActividad],List[tables.MantTipoActividad],List[tables.MantItemIntervenido],List[tables.MantComponente],String,String,String,String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], fecha: String, listEstaOperacional: List[tables.MantEstadoEnObra], operMec: tables.MantOperadorMecanico, listTipoMantencion: List[tables.TipoMantencion], listEstadoEnTaller: List[tables.MantEstadoEnTaller], listActividad: List[tables.MantActividad], listTipoActividad: List[tables.MantTipoActividad], listItemIntervenido: List[tables.MantItemIntervenido], listComponentes: List[tables.MantComponente], idEquipo: String, cod_nameEquipo: String, id_bodega: String, suc_nameBodega: String, nameTipoBodega: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""<div id="enCarga" class="blocker" style="display: none;"><br><br><br><br><br><br><h1>En proceso.....<div id="msgEspera"></div></h1></div>
		<table class="table table-sm table-bordered table-condensed table-fluid">
			<thead>
				<tr>
					<td style="text-align:left;"><strong>EQUIPO (*):</strong></td>
			        <td style="text-align:left;  width: 70%" colspan="4">
			        	<input type="hidden" id="id_equipo_mec" name="id_equipo_mec" value=""""),_display_(/*8.82*/idEquipo),format.raw/*8.90*/("""">
			        	<div id="nombreEquipo_mec">
			        		<div class="input-group input-group-sm">
							  <input class="form-control" type="text" 
								onclick="$('#modalListaEquipos_oper').modal('show');" 
								id="valNomEquip_mec"
								value=""""),_display_(/*14.17*/cod_nameEquipo),format.raw/*14.31*/("""" readonly>
							  <div class="input-group-append">
							    <span class="input-group-text" onclick="$('#modalListaEquipos_oper').modal('show');">Buscar</span>
							  </div>
							</div> 
	    				</div>
			        </td>
				</tr>
				<tr style="display: none">
					<td style="text-align:left;"><strong>TIPO DE PLAN:</strong></td>
					<td style="text-align:left;  width: 70%" colspan="4">
						<input type="hidden" id="id_tipoPlan_mec" name="id_tipoPlan_mec" value="0">
						<input class="form-control" id="tipoPlan_mec" type="text" value="" readonly>
					</td>
				</tr>
				<tr>
					<td style="text-align:left;"><strong>"""),_display_(/*30.44*/mapDiccionario/*30.58*/.get("BODEGA")),format.raw/*30.72*/("""/PROYECTO (*):</strong></td>
			        <td style="text-align:left;  width: 70%" colspan="4">
			        	<input type="hidden" id="id_bodega_mec" name="id_bodega_mec" value=""""),_display_(/*32.82*/id_bodega),format.raw/*32.91*/("""">
			        	<div id="nombreBodega_mec">
			        		<div class="input-group input-group-sm">
							  <input class="form-control" type="text" 
								onclick="$('#modalListaBodegas').modal('show');" 
								id="valNomBod_mec"
								value=""""),_display_(/*38.17*/suc_nameBodega),format.raw/*38.31*/("""" readonly>
							  <div class="input-group-append">
							    <span class="input-group-text" onclick="$('#modalListaBodegas').modal('show');">Buscar</span>
							  </div>
							</div> 
	    				</div>
			        </td>
				</tr>
				<tr>
					<td style="text-align:left;"><strong>TIPO """),_display_(/*47.49*/mapDiccionario/*47.63*/.get("BODEGA")),format.raw/*47.77*/("""/PROYECTO:</strong></td>
			        <td style="text-align:left;  width: 70%" colspan="4">
						<input class="form-control" id="tipoBodega_mec" type="text" value=""""),_display_(/*49.75*/nameTipoBodega),format.raw/*49.89*/("""" readonly>
			        </td>
				</tr>
				<tr>
					<td style="text-align:left;"><strong>ESTADO OPERACIONAL (*):</strong></td>
			        <td style="text-align:left;  width: 70%" colspan="4">
			        	<select class="form-control form-control-sm" 
							id="id_mantEstadoOperacional" 
							name="id_mantEstadoOperacional"
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
			        <td style="text-align:left;  width: 70%" colspan="4">
			        	<select class="form-control form-control-sm" 
							id="id_mantEstadoEnTaller" 
							name="id_mantEstadoEnTaller"
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
			        <td style="text-align:left;  width: 70%" colspan="4">
			        	<select class="form-control form-control-sm" 
							id="id_mantActividad" 
							name="id_mantActividad"
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
			        <td style="text-align:left;  width: 70%" colspan="4">
			        	<select class="form-control form-control-sm" 
							id="id_mantTipoActividad" 
							name="id_mantTipoActividad"
							required disabled>
				        		<option class="blank" value="0">-- Select --</option>
			            		"""),_display_(/*102.19*/for(lista <- listItemIntervenido) yield /*102.52*/{_display_(Seq[Any](format.raw/*102.53*/("""
			                		"""),format.raw/*103.22*/("""<option value=""""),_display_(/*103.38*/lista/*103.43*/.id),format.raw/*103.46*/("""" >"""),_display_(/*103.50*/lista/*103.55*/.nombre),format.raw/*103.62*/("""</option>
								""")))}),format.raw/*104.10*/("""
				        """),format.raw/*105.13*/("""</select> 
			        </td>
				</tr>
				
				
				
				<tr>
					<td style="text-align:left;"><strong>ITEM INTERVENIDO (*):</strong></td>
			        <td style="text-align:left;  width: 70%" colspan="4">
			        	<select class="form-control form-control-sm" 
							id="id_mantItemIntervenido" 
							name="id_mantItemIntervenido"
							required disabled>
				        		<option class="blank" value="0">-- Select --</option>
			            		"""),_display_(/*119.19*/for(lista <- listComponentes) yield /*119.48*/{_display_(Seq[Any](format.raw/*119.49*/("""
			                		"""),format.raw/*120.22*/("""<option value=""""),_display_(/*120.38*/lista/*120.43*/.id),format.raw/*120.46*/("""" >"""),_display_(/*120.50*/lista/*120.55*/.nombre),format.raw/*120.62*/("""</option>
								""")))}),format.raw/*121.10*/("""
				        """),format.raw/*122.13*/("""</select> 
			        </td>
				</tr>
				
				
				
				<tr>
					<td style="text-align:left;"><strong>COMPONENTES:</strong></td>
			        <td style="text-align:left;  width: 70%" colspan="4">
			        	<table id="tblComponentesC" class="table table-sm table-bordered table-condensed table-fluid">
							<thead>
								<tr>
									<th style="text-align:left; vertical-align: middle;  width: 70%">Componentes</th>
									<th style="text-align:left; vertical-align: middle;  width: 10%">Cantidad</th>
									<th style="text-align:left; vertical-align: middle;  width: 5%">DEL</th>
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
							value=""""),_display_(/*174.16*/fecha),format.raw/*174.21*/("""">
			        </td>
					<td style="text-align:left;"><strong>Fecha_TER:</strong></td>
			        <td style="text-align:left" colspan="2">
						<input type="date" class="form-control form-control-sm left"  style="max-width:150px"
							id="fechaTer_mec" 
							name="fechaTer_mec"
							onblur="validarFechaTer_mec()"
							value=""""),_display_(/*182.16*/fecha),format.raw/*182.21*/("""">
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
			        <td style="text-align:left;  width: 70%" colspan="4">
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
			        <td style="text-align:left;  width: 70%" colspan="4">
						<textarea class="form-control form-control-sm" rows="1" 
							id="descTrabajo"
							name="descTrabajo" 
							autocomplete="off"></textarea>
			        </td>
				</tr>
				<tr>
					<td style="text-align:left;"><strong>ESTADO FINAL:</strong></td>
			        <td style="text-align:left;  width: 70%" colspan="4">
						<textarea class="form-control form-control-sm" rows="1" 
							id="estadoFinal"
							name="estadoFinal" 
							autocomplete="off"></textarea>
			        </td>
				</tr>
				<tr>
					<td style="text-align:left;"><strong>OBSERVACIONES:</strong></td>
			        <td style="text-align:left;  width: 70%" colspan="4">
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
						<button type="button" class="btn btn-sm btn-warning" style="font-size: 10px;" onclick="location.href = '/report'">SALIR</button>
					</td>
				</tr>
			</thead>
		</table>
		
		
<script>

	function validarLectIni_mec()"""),format.raw/*311.31*/("""{"""),format.raw/*311.32*/("""
		"""),format.raw/*312.3*/("""let lecturaIni = $("#lecturaIni_mec").val().replace(/,/g,'');
		let lecturaTer = $("#lecturaTer_mec").val().replace(/,/g,'');
		try"""),format.raw/*314.6*/("""{"""),format.raw/*314.7*/("""
			"""),format.raw/*315.4*/("""lectAnterior = lectAnterior.replace(/,/g,'');
		"""),format.raw/*316.3*/("""}"""),format.raw/*316.4*/("""catch(e)"""),format.raw/*316.12*/("""{"""),format.raw/*316.13*/("""
			"""),format.raw/*317.4*/("""lectAnterior = lectAnterior;
		"""),format.raw/*318.3*/("""}"""),format.raw/*318.4*/("""
		"""),format.raw/*319.3*/("""if(parseFloat(lecturaIni) < parseFloat(lectAnterior))"""),format.raw/*319.56*/("""{"""),format.raw/*319.57*/("""
			"""),format.raw/*320.4*/("""alertify.alert("La lectura inicial no puede ser menor a la ultima lectura registrada");
			$("#lecturaIni_mec").val(formatStandar(lectAnterior,2));
		"""),format.raw/*322.3*/("""}"""),format.raw/*322.4*/("""else if(lecturaTer!="0.00")"""),format.raw/*322.31*/("""{"""),format.raw/*322.32*/("""
			"""),format.raw/*323.4*/("""if(parseFloat(lecturaIni) > parseFloat(lecturaTer))"""),format.raw/*323.55*/("""{"""),format.raw/*323.56*/("""
				"""),format.raw/*324.5*/("""alertify.alert("La lectura inicial no puede ser menor a la lectura de termino");
				$("#lecturaIni_mec").val(formatStandar(formatStandar(lectAnterior,2)));
			"""),format.raw/*326.4*/("""}"""),format.raw/*326.5*/("""else"""),format.raw/*326.9*/("""{"""),format.raw/*326.10*/("""
				"""),format.raw/*327.5*/("""let dif = parseFloat(lecturaTer) - parseFloat(lecturaIni);
				$("#cantidad_mec").val(formatStandar(dif,2));
			"""),format.raw/*329.4*/("""}"""),format.raw/*329.5*/("""
		"""),format.raw/*330.3*/("""}"""),format.raw/*330.4*/("""
	"""),format.raw/*331.2*/("""}"""),format.raw/*331.3*/("""
	
	"""),format.raw/*333.2*/("""function validarLectTer_mec()"""),format.raw/*333.31*/("""{"""),format.raw/*333.32*/("""
		"""),format.raw/*334.3*/("""let lecturaIni = $("#lecturaIni_mec").val().replace(/,/g,'');
		let lecturaTer = $("#lecturaTer_mec").val().replace(/,/g,'');
			if(parseFloat(lecturaIni) > parseFloat(lecturaTer))"""),format.raw/*336.55*/("""{"""),format.raw/*336.56*/("""
				"""),format.raw/*337.5*/("""alertify.alert("La lectura inicial no puede ser menor a la lectura de termino");
				$("#lecturaTer_mec").val(formatStandar(0,2));
				$("#cantidad_mec").val(formatStandar(0,2));
			"""),format.raw/*340.4*/("""}"""),format.raw/*340.5*/("""else"""),format.raw/*340.9*/("""{"""),format.raw/*340.10*/("""
				"""),format.raw/*341.5*/("""let dif = parseFloat(lecturaTer) - parseFloat(lecturaIni);
				$("#cantidad_mec").val(formatStandar(dif,2));
			"""),format.raw/*343.4*/("""}"""),format.raw/*343.5*/("""
	"""),format.raw/*344.2*/("""}"""),format.raw/*344.3*/("""
	
	"""),format.raw/*346.2*/("""function validarHoraIni_mec()"""),format.raw/*346.31*/("""{"""),format.raw/*346.32*/("""
		"""),format.raw/*347.3*/("""let horaIni = $("#horaIni_mec").val();
		let horaTer = $("#horaTer_mec").val();
		if(horaTer!="")"""),format.raw/*349.18*/("""{"""),format.raw/*349.19*/("""
			"""),format.raw/*350.4*/("""if(parseFloat(horaIni) >= parseFloat(horaTer))"""),format.raw/*350.50*/("""{"""),format.raw/*350.51*/("""
				"""),format.raw/*351.5*/("""alertify.alert("La hora inicial no puede ser menor o igual a la hora de termino");
				$("#horaIni_mec").val("");
				$("#horaTer_mec").val("");
			"""),format.raw/*354.4*/("""}"""),format.raw/*354.5*/("""
		"""),format.raw/*355.3*/("""}"""),format.raw/*355.4*/("""
	"""),format.raw/*356.2*/("""}"""),format.raw/*356.3*/("""
	
	"""),format.raw/*358.2*/("""function validarHoraTer_mec()"""),format.raw/*358.31*/("""{"""),format.raw/*358.32*/("""
		"""),format.raw/*359.3*/("""let horaIni = $("#horaIni_mec").val();
		let horaTer = $("#horaTer_mec").val();
		if(horaIni=="")"""),format.raw/*361.18*/("""{"""),format.raw/*361.19*/("""
			"""),format.raw/*362.4*/("""alertify.alert("Primero debe ingresar la hora inicial");
			$("#horaTer_mec").val("");
		"""),format.raw/*364.3*/("""}"""),format.raw/*364.4*/("""else"""),format.raw/*364.8*/("""{"""),format.raw/*364.9*/("""
			"""),format.raw/*365.4*/("""if(horaIni > horaTer)"""),format.raw/*365.25*/("""{"""),format.raw/*365.26*/("""
				"""),format.raw/*366.5*/("""alertify.alert("La hora inicial no puede ser menor a la hora de termino");
				$("#horaTer_mec").val("");
			"""),format.raw/*368.4*/("""}"""),format.raw/*368.5*/("""
		"""),format.raw/*369.3*/("""}"""),format.raw/*369.4*/("""
	"""),format.raw/*370.2*/("""}"""),format.raw/*370.3*/("""
	
	"""),format.raw/*372.2*/("""function validarFechaIni_mec()"""),format.raw/*372.32*/("""{"""),format.raw/*372.33*/("""
		"""),format.raw/*373.3*/("""let fechaIni = new Date($("#fechaIni_mec").val());
		if(isValidDate(fechaIni))"""),format.raw/*374.28*/("""{"""),format.raw/*374.29*/("""
			"""),format.raw/*375.4*/("""let fechaTer = new Date($("#fechaTer_mec").val());
			if(fechaIni > fechaTer)"""),format.raw/*376.27*/("""{"""),format.raw/*376.28*/("""
				"""),format.raw/*377.5*/("""alertify.alert("La fecha de inicio no puede ser posterior a la fecha de termino.");
	        		$('#fechaIni_mec').val('"""),_display_(/*378.37*/fecha),format.raw/*378.42*/("""');
			"""),format.raw/*379.4*/("""}"""),format.raw/*379.5*/("""
		"""),format.raw/*380.3*/("""}"""),format.raw/*380.4*/("""else"""),format.raw/*380.8*/("""{"""),format.raw/*380.9*/("""
			"""),format.raw/*381.4*/("""alertify.alert("Debe ingresar una fecha valida.");
	        		$('#fechaIni_mec').val('"""),_display_(/*382.37*/fecha),format.raw/*382.42*/("""');
		"""),format.raw/*383.3*/("""}"""),format.raw/*383.4*/("""
		
	"""),format.raw/*385.2*/("""}"""),format.raw/*385.3*/("""
	
	"""),format.raw/*387.2*/("""function validarFechaTer_mec()"""),format.raw/*387.32*/("""{"""),format.raw/*387.33*/("""
		"""),format.raw/*388.3*/("""let fechaTer = new Date($("#fechaTer_mec").val());
		if(isValidDate(fechaTer))"""),format.raw/*389.28*/("""{"""),format.raw/*389.29*/("""
			"""),format.raw/*390.4*/("""let fechaIni = new Date($("#fechaIni_mec").val());
			if(fechaIni > fechaTer)"""),format.raw/*391.27*/("""{"""),format.raw/*391.28*/("""
				"""),format.raw/*392.5*/("""alertify.alert("La fecha de termino no puede ser anterior a la fecha de inicio.");
	        		$('#fechaTer_mec').val('"""),_display_(/*393.37*/fecha),format.raw/*393.42*/("""');
			"""),format.raw/*394.4*/("""}"""),format.raw/*394.5*/("""
		"""),format.raw/*395.3*/("""}"""),format.raw/*395.4*/("""else"""),format.raw/*395.8*/("""{"""),format.raw/*395.9*/("""
			"""),format.raw/*396.4*/("""alertify.alert("Debe ingresar una fecha valida.");
	        		$('#fechaTer_mec').val('"""),_display_(/*397.37*/fecha),format.raw/*397.42*/("""');
		"""),format.raw/*398.3*/("""}"""),format.raw/*398.4*/("""
	"""),format.raw/*399.2*/("""}"""),format.raw/*399.3*/("""
	
	"""),format.raw/*401.2*/("""function grabarMecanico()"""),format.raw/*401.27*/("""{"""),format.raw/*401.28*/("""
		  """),format.raw/*402.5*/("""if($('#id_mecanico').val()==0)"""),format.raw/*402.35*/("""{"""),format.raw/*402.36*/("""
			  """),format.raw/*403.6*/("""alertify.alert('Debe seleccionar un Mecanico');
		  """),format.raw/*404.5*/("""}"""),format.raw/*404.6*/("""else if($('#id_tipoMantencion').val()==0)"""),format.raw/*404.47*/("""{"""),format.raw/*404.48*/("""
			  """),format.raw/*405.6*/("""alertify.alert('Debe seleccionar un Tipo de Mantencion');
		  """),format.raw/*406.5*/("""}"""),format.raw/*406.6*/("""else if($('#id_equipo_mec').val()==0)"""),format.raw/*406.43*/("""{"""),format.raw/*406.44*/("""
			  """),format.raw/*407.6*/("""alertify.alert('Debe seleccionar un Equipo');
		  """),format.raw/*408.5*/("""}"""),format.raw/*408.6*/("""else if($('#id_bodega_mec').val()==0)"""),format.raw/*408.43*/("""{"""),format.raw/*408.44*/("""
			  """),format.raw/*409.6*/("""alertify.alert('Debe seleccionar una """),_display_(/*409.44*/mapDiccionario/*409.58*/.get("Bodega")),format.raw/*409.72*/("""/Proyecto');
		  """),format.raw/*410.5*/("""}"""),format.raw/*410.6*/("""else if($('#id_mantEstadoOperacional').val()==0)"""),format.raw/*410.54*/("""{"""),format.raw/*410.55*/("""
			  """),format.raw/*411.6*/("""alertify.alert('Debe ingresar un Estado en Operacional');
		  """),format.raw/*412.5*/("""}"""),format.raw/*412.6*/("""else if($('#id_mantEstadoEnTaller').val()==0)"""),format.raw/*412.51*/("""{"""),format.raw/*412.52*/("""
			  """),format.raw/*413.6*/("""alertify.alert('Debe ingresar un Estado en Taller');
		  """),format.raw/*414.5*/("""}"""),format.raw/*414.6*/("""else if($('#id_mantActividad').val()==0)"""),format.raw/*414.46*/("""{"""),format.raw/*414.47*/("""
			  """),format.raw/*415.6*/("""alertify.alert('Debe ingresar una Actividad');
	      """),format.raw/*416.8*/("""}"""),format.raw/*416.9*/("""else if($('#id_mantTipoActividad').val()==0)"""),format.raw/*416.53*/("""{"""),format.raw/*416.54*/("""
			  """),format.raw/*417.6*/("""alertify.alert('Debe ingresar un tipo de Actividad');
		  """),format.raw/*418.5*/("""}"""),format.raw/*418.6*/("""else if($('#id_mantItemIntervenido').val()==0)"""),format.raw/*418.52*/("""{"""),format.raw/*418.53*/("""
			  """),format.raw/*419.6*/("""alertify.alert('Debe ingresar un Item Intervenido');
		  """),format.raw/*420.5*/("""}"""),format.raw/*420.6*/("""else if($('#lecturaTer_mec').val()=="0.00")"""),format.raw/*420.49*/("""{"""),format.raw/*420.50*/("""
			  """),format.raw/*421.6*/("""alertify.alert('Debe ingresar Lect_TER que debe ser mayor a cero');
		  """),format.raw/*422.5*/("""}"""),format.raw/*422.6*/("""else"""),format.raw/*422.10*/("""{"""),format.raw/*422.11*/("""
			"""),format.raw/*423.4*/("""let data = signaturePad3.toDataURL('image/png');
			let encodedImage = data.split(",");
			$("#firmaEjecutor").val(encodedImage[1]);

			data = signaturePad4.toDataURL('image/png');
			encodedImage = data.split(",");
			$("#firmaAprobador").val(encodedImage[1]);
			document.getElementById('enCarga').style.display="block";
			$("#grabarRptOperadorMecanico").submit();
		  """),format.raw/*432.5*/("""}"""),format.raw/*432.6*/("""
	"""),format.raw/*433.2*/("""}"""),format.raw/*433.3*/("""
	
"""),format.raw/*435.1*/("""</script>











	"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],fecha:String,listEstaOperacional:List[tables.MantEstadoEnObra],operMec:tables.MantOperadorMecanico,listTipoMantencion:List[tables.TipoMantencion],listEstadoEnTaller:List[tables.MantEstadoEnTaller],listActividad:List[tables.MantActividad],listTipoActividad:List[tables.MantTipoActividad],listItemIntervenido:List[tables.MantItemIntervenido],listComponentes:List[tables.MantComponente],idEquipo:String,cod_nameEquipo:String,id_bodega:String,suc_nameBodega:String,nameTipoBodega:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,fecha,listEstaOperacional,operMec,listTipoMantencion,listEstadoEnTaller,listActividad,listTipoActividad,listItemIntervenido,listComponentes,idEquipo,cod_nameEquipo,id_bodega,suc_nameBodega,nameTipoBodega)

  def f:((Map[String,String],String,List[tables.MantEstadoEnObra],tables.MantOperadorMecanico,List[tables.TipoMantencion],List[tables.MantEstadoEnTaller],List[tables.MantActividad],List[tables.MantTipoActividad],List[tables.MantItemIntervenido],List[tables.MantComponente],String,String,String,String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,fecha,listEstaOperacional,operMec,listTipoMantencion,listEstadoEnTaller,listActividad,listTipoActividad,listItemIntervenido,listComponentes,idEquipo,cod_nameEquipo,id_bodega,suc_nameBodega,nameTipoBodega) => apply(mapDiccionario,fecha,listEstaOperacional,operMec,listTipoMantencion,listEstadoEnTaller,listActividad,listTipoActividad,listItemIntervenido,listComponentes,idEquipo,cod_nameEquipo,id_bodega,suc_nameBodega,nameTipoBodega)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuMantencion/mantWebReportNewMecanicoCorrectivo.scala.html
                  HASH: 1f1330bfb815c9c0734f92d48e31b286909865a2
                  MATRIX: 1279->1|1920->549|2394->997|2422->1005|2704->1260|2739->1274|3402->1910|3425->1924|3460->1938|3662->2113|3692->2122|3967->2370|4002->2384|4319->2674|4342->2688|4377->2702|4568->2866|4603->2880|5070->3320|5119->3353|5158->3354|5208->3376|5251->3392|5265->3397|5289->3400|5320->3404|5334->3409|5362->3416|5412->3435|5453->3448|5911->3879|5959->3911|5998->3912|6048->3934|6091->3950|6105->3955|6129->3958|6160->3962|6174->3967|6202->3974|6252->3993|6293->4006|6734->4420|6777->4447|6816->4448|6866->4470|6909->4486|6923->4491|6947->4494|6978->4498|6992->4503|7020->4510|7070->4529|7111->4542|7569->4972|7619->5005|7659->5006|7710->5028|7754->5044|7769->5049|7794->5052|7826->5056|7841->5061|7870->5068|7921->5087|7963->5100|8439->5548|8485->5577|8525->5578|8576->5600|8620->5616|8635->5621|8660->5624|8692->5628|8707->5633|8736->5640|8787->5659|8829->5672|10892->7707|10919->7712|11284->8049|11311->8054|16428->13142|16458->13143|16489->13146|16648->13277|16677->13278|16709->13282|16785->13330|16814->13331|16851->13339|16881->13340|16913->13344|16972->13375|17001->13376|17032->13379|17114->13432|17144->13433|17176->13437|17354->13587|17383->13588|17439->13615|17469->13616|17501->13620|17581->13671|17611->13672|17644->13677|17832->13837|17861->13838|17893->13842|17923->13843|17956->13848|18096->13960|18125->13961|18156->13964|18185->13965|18215->13967|18244->13968|18276->13972|18334->14001|18364->14002|18395->14005|18604->14185|18634->14186|18667->14191|18877->14373|18906->14374|18938->14378|18968->14379|19001->14384|19141->14496|19170->14497|19200->14499|19229->14500|19261->14504|19319->14533|19349->14534|19380->14537|19506->14634|19536->14635|19568->14639|19643->14685|19673->14686|19706->14691|19882->14839|19911->14840|19942->14843|19971->14844|20001->14846|20030->14847|20062->14851|20120->14880|20150->14881|20181->14884|20307->14981|20337->14982|20369->14986|20486->15075|20515->15076|20547->15080|20576->15081|20608->15085|20658->15106|20688->15107|20721->15112|20858->15221|20887->15222|20918->15225|20947->15226|20977->15228|21006->15229|21038->15233|21097->15263|21127->15264|21158->15267|21265->15345|21295->15346|21327->15350|21433->15427|21463->15428|21496->15433|21644->15553|21671->15558|21706->15565|21735->15566|21766->15569|21795->15570|21827->15574|21856->15575|21888->15579|22003->15666|22030->15671|22064->15677|22093->15678|22126->15683|22155->15684|22187->15688|22246->15718|22276->15719|22307->15722|22414->15800|22444->15801|22476->15805|22582->15882|22612->15883|22645->15888|22792->16007|22819->16012|22854->16019|22883->16020|22914->16023|22943->16024|22975->16028|23004->16029|23036->16033|23151->16120|23178->16125|23212->16131|23241->16132|23271->16134|23300->16135|23332->16139|23386->16164|23416->16165|23449->16170|23508->16200|23538->16201|23572->16207|23652->16259|23681->16260|23751->16301|23781->16302|23815->16308|23905->16370|23934->16371|24000->16408|24030->16409|24064->16415|24142->16465|24171->16466|24237->16503|24267->16504|24301->16510|24367->16548|24391->16562|24427->16576|24472->16593|24501->16594|24578->16642|24608->16643|24642->16649|24732->16711|24761->16712|24835->16757|24865->16758|24899->16764|24984->16821|25013->16822|25082->16862|25112->16863|25146->16869|25228->16923|25257->16924|25330->16968|25360->16969|25394->16975|25480->17033|25509->17034|25584->17080|25614->17081|25648->17087|25733->17144|25762->17145|25834->17188|25864->17189|25898->17195|25998->17267|26027->17268|26060->17272|26090->17273|26122->17277|26523->17650|26552->17651|26582->17653|26611->17654|26642->17657
                  LINES: 28->1|33->2|39->8|39->8|45->14|45->14|61->30|61->30|61->30|63->32|63->32|69->38|69->38|78->47|78->47|78->47|80->49|80->49|91->60|91->60|91->60|92->61|92->61|92->61|92->61|92->61|92->61|92->61|93->62|94->63|105->74|105->74|105->74|106->75|106->75|106->75|106->75|106->75|106->75|106->75|107->76|108->77|119->88|119->88|119->88|120->89|120->89|120->89|120->89|120->89|120->89|120->89|121->90|122->91|133->102|133->102|133->102|134->103|134->103|134->103|134->103|134->103|134->103|134->103|135->104|136->105|150->119|150->119|150->119|151->120|151->120|151->120|151->120|151->120|151->120|151->120|152->121|153->122|205->174|205->174|213->182|213->182|342->311|342->311|343->312|345->314|345->314|346->315|347->316|347->316|347->316|347->316|348->317|349->318|349->318|350->319|350->319|350->319|351->320|353->322|353->322|353->322|353->322|354->323|354->323|354->323|355->324|357->326|357->326|357->326|357->326|358->327|360->329|360->329|361->330|361->330|362->331|362->331|364->333|364->333|364->333|365->334|367->336|367->336|368->337|371->340|371->340|371->340|371->340|372->341|374->343|374->343|375->344|375->344|377->346|377->346|377->346|378->347|380->349|380->349|381->350|381->350|381->350|382->351|385->354|385->354|386->355|386->355|387->356|387->356|389->358|389->358|389->358|390->359|392->361|392->361|393->362|395->364|395->364|395->364|395->364|396->365|396->365|396->365|397->366|399->368|399->368|400->369|400->369|401->370|401->370|403->372|403->372|403->372|404->373|405->374|405->374|406->375|407->376|407->376|408->377|409->378|409->378|410->379|410->379|411->380|411->380|411->380|411->380|412->381|413->382|413->382|414->383|414->383|416->385|416->385|418->387|418->387|418->387|419->388|420->389|420->389|421->390|422->391|422->391|423->392|424->393|424->393|425->394|425->394|426->395|426->395|426->395|426->395|427->396|428->397|428->397|429->398|429->398|430->399|430->399|432->401|432->401|432->401|433->402|433->402|433->402|434->403|435->404|435->404|435->404|435->404|436->405|437->406|437->406|437->406|437->406|438->407|439->408|439->408|439->408|439->408|440->409|440->409|440->409|440->409|441->410|441->410|441->410|441->410|442->411|443->412|443->412|443->412|443->412|444->413|445->414|445->414|445->414|445->414|446->415|447->416|447->416|447->416|447->416|448->417|449->418|449->418|449->418|449->418|450->419|451->420|451->420|451->420|451->420|452->421|453->422|453->422|453->422|453->422|454->423|463->432|463->432|464->433|464->433|466->435
                  -- GENERATED --
              */
          