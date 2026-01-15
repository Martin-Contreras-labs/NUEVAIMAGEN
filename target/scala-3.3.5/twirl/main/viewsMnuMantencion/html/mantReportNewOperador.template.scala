
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

object mantReportNewOperador extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],String,List[tables.MantEstadoEnObra],List[tables.MantOperadorMecanico],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], fecha: String, listEstaObra: List[tables.MantEstadoEnObra], listOperMec: List[tables.MantOperadorMecanico]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""<div id="enCarga" class="blocker" style="display: none;"><br><br><br><br><br><br><h1>En proceso.....<div id="msgEspera"></div></h1></div>
	<table class="table table-sm table-bordered table-condensed table-fluid">
		<thead>
			<tr>
				<td style="text-align:left;"><strong>OPERADOR (*):</strong></td>
		        <td style="text-align:left" width="70%" colspan="4">
		        	<input type="hidden" id="id_operador" name="id_operador" value="0" required>
		        	<div id="nombreOperador">
		        		<div class="input-group input-group-sm">
						  <input class="form-control" type="text" 
							onclick="$('#modalListaOperadores').modal('show');" 
							id="valNomOperador"
							value="--- select ---" readonly>
						  <div class="input-group-append">
						    <span class="input-group-text" onclick="$('#modalListaOperadores').modal('show');">Buscar</span>
						  </div>
						</div> 
    				</div>
		        </td>
			</tr>
			<tr>
				<td style="text-align:left;"><strong>ORIGEN/TIPO:</strong></td>
		        <td style="text-align:left" width="70%" colspan="4">
					<input class="form-control" id="tipoOperador" type="text" value="" readonly>
		        </td>
			</tr>
			<tr>
				<td style="text-align:left;"><strong>EQUIPO (*):</strong></td>
		        <td style="text-align:left" width="70%" colspan="4">
		        	<input type="hidden" id="id_equipo_oper" name="id_equipo_oper" value="0">
		        	<div id="nombreEquipo_oper">
		        		<div class="input-group input-group-sm">
						  <input class="form-control" type="text" 
							onclick="$('#modalListaEquipos_oper').modal('show');" 
							id="valNomEquip_oper"
							value="--- select ---" readonly>
						  <div class="input-group-append">
						    <span class="input-group-text" onclick="$('#modalListaEquipos_oper').modal('show');">Buscar</span>
						  </div>
						</div> 
    				</div>
		        </td>
			</tr>
			<tr>
				<td style="text-align:left;"><strong>"""),_display_(/*46.43*/mapDiccionario/*46.57*/.get("BODEGA")),format.raw/*46.71*/("""/PROYECTO (*):</strong></td>
		        <td style="text-align:left" width="70%" colspan="4">
		        	<input type="hidden" id="id_bodega_oper" name="id_bodega_oper" value="0">
		        	<div id="nombreBodega_oper">
		        		<div class="input-group input-group-sm">
						  <input class="form-control" type="text" 
							onclick="$('#modalListaBodegas').modal('show');" 
							id="valNomBod_oper"
							value="--- select ---" readonly>
						  <div class="input-group-append">
						    <span class="input-group-text" onclick="$('#modalListaBodegas').modal('show');">Buscar</span>
						  </div>
						</div> 
    				</div>
		        </td>
			</tr>
			<tr>
				<td style="text-align:left;"><strong>TIPO """),_display_(/*63.48*/mapDiccionario/*63.62*/.get("BODEGA")),format.raw/*63.76*/("""/PROYECTO:</strong></td>
		        <td style="text-align:left" width="70%" colspan="4">
					<input class="form-control" id="tipoBodega_oper" type="text" value="" readonly>
		        </td>
			</tr>
			<tr>
				<td style="text-align:left;"><strong>ESTADO EN SITIO (*):</strong></td>
		        <td style="text-align:left" width="70%" colspan="4">
		        	<select class="form-control form-control-sm" 
						id="id_mantEstadoEnObra" 
						name="id_mantEstadoEnObra"
						required disabled>
			        		<option class="blank" value="0">-- Select --</option>
		            		"""),_display_(/*76.18*/for(lista <- listEstaObra) yield /*76.44*/{_display_(Seq[Any](format.raw/*76.45*/("""
		                		"""),format.raw/*77.21*/("""<option value=""""),_display_(/*77.37*/lista/*77.42*/.id),format.raw/*77.45*/("""" >"""),_display_(/*77.49*/lista/*77.54*/.nombre),format.raw/*77.61*/("""</option>
							""")))}),format.raw/*78.9*/("""
			        """),format.raw/*79.12*/("""</select> 
		        </td>
			</tr>
			<tr>
				<td style="text-align:left;"><strong>Hr_INI:</strong></td>
		        <td style="text-align:left">
					<input type="time" class="form-control form-control-sm left" style="max-width:100px"
						id="horaIni_oper" 
						name="horaIni_oper"
						onblur="validarHoraIni_oper()">
		        </td>
				<td style="text-align:left;"><strong>Hr_TER:</strong></td>
		        <td style="text-align:left" colspan="2">
					<input type="time" class="form-control form-control-sm left"  style="max-width:100px"
						id="horaTer_oper" 
						name="horaTer_oper"
						onblur="validarHoraTer_oper()">
		        </td>
			</tr>
			
			<tr>
				<td style="text-align:left;"><strong><div id="lectIni_oper">Lect_INI:</div></strong></td>
		        <td style="text-align:left">
					<input type="hidden" id="idUnidadMant_oper" name="id_unidadMantencion">
					<input type="text" class="form-control form-control-sm left" style="max-width:200px"
						id="lecturaIni_oper" 
						name="lecturaIni_oper"
						value = "0.00"
						onClick="this.select();"
						onfocus="value=value.replace(/,/g,'')" 
						onfocusout = "if(value.trim()=='') value=0; value = formatStandar(value, 2);"
						onkeydown="return ingresoDouble(window.event)"
						onchange="validarLectIni_oper()"
						autocomplete="off"
						required disabled>
		        </td>
				<td style="text-align:left;"><strong><div id="lectTer_oper">Lect_TER(*):</div></strong></td>
		        <td style="text-align:left" colspan="2">
					<input type="text" class="form-control form-control-sm left" style="max-width:200px"
						id="lecturaTer_oper" 
						name="lecturaTer_oper"
						value = "0.00"
						onClick="this.select();"
						onfocus="value=value.replace(/,/g,'')" 
						onfocusout = "if(value.trim()=='') value=0; value = formatStandar(value, 2);"
						onkeydown="return ingresoDouble(window.event)"
						onchange="validarLectTer_oper()"
						autocomplete="off"
						required disabled>
		        </td>
			</tr>
			
			<tr>
				<td style="text-align:left;"><strong><div id="cant_oper">CANT:</div></strong></td>
		        <td style="text-align:left" width="70%" colspan="4">
					<input type="text" class="form-control form-control-sm left"
						id="cantidad_oper" 
						name="cantidad_oper"
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
				<td style="text-align:left;"><strong>COMENTARIOS:</strong></td>
		        <td style="text-align:left" width="70%" colspan="4">
					<textarea class="form-control form-control-sm" rows="1" 
						id="comentario"
						name="comentario" 
						autocomplete="off"></textarea>
		        </td>
			</tr>
			<tr>
				<td style="text-align:left;"><strong>OBSERVACIONES:</strong></td>
		        <td style="text-align:left" width="70%" colspan="4">
					<textarea class="form-control form-control-sm" rows="2" 
						id="observaciones_oper"
						name="observaciones_oper" 
						autocomplete="off"></textarea>
		        </td>
			</tr>
			
			<tr>
				<td style="text-align:left;"  colspan="5">
					FIRMA OPERADOR:<br>
					<div class="wrapper" align="center">
					<canvas id="signature-pad" class="signature-pad" width=350 height=90 style="border:1px solid #000000;"></canvas>
					</div>
					<button type="button" class="btn btn-sm btn-warning" style="font-size: 10px;" onclick="signaturePad.clear()">Borrar firma</button>
				</td>
			</tr>
			<tr>
				<td style="text-align:left;"   colspan="5">
					FIRMA AUTORIZADA:<br>
					<div class="wrapper" align="center">
					<canvas id="signature-pad2" class="signature-pad" width=350 height=90 style="border:1px solid #000000;"></canvas>
					</div>
					<button type="button" class="btn btn-sm btn-warning" style="font-size: 10px;" onclick="signaturePad2.clear()">Borrar firma</button>
				</td>
			</tr>
		
			<tr>
				<td colspan="5" style="text-align:center;">
					<button type="button" class="btn btn-sm btn-success" style="font-size: 10px;"onclick="grabarOperador();" >GRABAR REPORT
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="btn btn-sm btn-warning" style="font-size: 10px;" onclick="location.href = '/home/'">SALIR</button>
				</td>
			</tr>
		</thead>
	</table>
	
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
						"""),_display_(/*215.8*/for(lista <- listOperMec) yield /*215.33*/{_display_(Seq[Any](format.raw/*215.34*/("""
							"""),_display_(if(lista.getId_mantActorPersonal() == 1)/*216.49*/{_display_(Seq[Any](format.raw/*216.50*/("""
								"""),format.raw/*217.9*/("""<TR onclick="selectOperador('"""),_display_(/*217.39*/lista/*217.44*/.getId()),format.raw/*217.52*/("""', btoa('"""),_display_(/*217.62*/lista/*217.67*/.getNombre()),format.raw/*217.79*/("""'), btoa('"""),_display_(/*217.90*/lista/*217.95*/.getNameTipoPersonal()),format.raw/*217.117*/("""') )">
									<td style="text-align:left"><a href="#">"""),_display_(/*218.51*/lista/*218.56*/.getRut()),format.raw/*218.65*/("""</a></td>	
									<td style="text-align:left"><a href="#">"""),_display_(/*219.51*/lista/*219.56*/.getNombre()),format.raw/*219.68*/("""</a></td>
									<td style="text-align:left"><a href="#">"""),_display_(/*220.51*/lista/*220.56*/.getNameTipoPersonal()),format.raw/*220.78*/("""</a></td>
								</TR>
							""")))} else {null} ),format.raw/*222.9*/("""
							
						""")))}),format.raw/*224.8*/("""
					"""),format.raw/*225.6*/("""</tbody>
				</table>
		        <br>
				<div align="center">
		           	<button type="button" class="btn btn-sm btn-success" data-dismiss="modal" style="font-size: 10px;">CERRAR</button>
			    </div>
		      </div>
		    </div>
		 </div>
	</div>
	
	
	
<script>
	function validarLectIni_oper()"""),format.raw/*239.32*/("""{"""),format.raw/*239.33*/("""
		"""),format.raw/*240.3*/("""let lecturaIni = $("#lecturaIni_oper").val().replace(/,/g,'');
		let lecturaTer = $("#lecturaTer_oper").val().replace(/,/g,'');
		try"""),format.raw/*242.6*/("""{"""),format.raw/*242.7*/("""
			"""),format.raw/*243.4*/("""lectAnterior = lectAnterior.replace(/,/g,'');
		"""),format.raw/*244.3*/("""}"""),format.raw/*244.4*/("""catch(e)"""),format.raw/*244.12*/("""{"""),format.raw/*244.13*/("""
			"""),format.raw/*245.4*/("""lectAnterior = lectAnterior;
		"""),format.raw/*246.3*/("""}"""),format.raw/*246.4*/("""
		"""),format.raw/*247.3*/("""if(parseFloat(lecturaIni) < parseFloat(lectAnterior))"""),format.raw/*247.56*/("""{"""),format.raw/*247.57*/("""
			"""),format.raw/*248.4*/("""alertify.alert("La lectura inicial no puede ser menor a la ultima lectura registrada");
			$("#lecturaIni_oper").val(formatStandar(lectAnterior,2));
		"""),format.raw/*250.3*/("""}"""),format.raw/*250.4*/("""else if(lecturaTer!="0.00")"""),format.raw/*250.31*/("""{"""),format.raw/*250.32*/("""
			"""),format.raw/*251.4*/("""if(parseFloat(lecturaIni) > parseFloat(lecturaTer))"""),format.raw/*251.55*/("""{"""),format.raw/*251.56*/("""
				"""),format.raw/*252.5*/("""alertify.alert("La lectura inicial no puede ser menor a la lectura de termino");
				$("#lecturaIni_oper").val(formatStandar(formatStandar(lectAnterior,2)));
			"""),format.raw/*254.4*/("""}"""),format.raw/*254.5*/("""else"""),format.raw/*254.9*/("""{"""),format.raw/*254.10*/("""
				"""),format.raw/*255.5*/("""let dif = parseFloat(lecturaTer) - parseFloat(lecturaIni);
				$("#cantidad_oper").val(formatStandar(dif,2));
			"""),format.raw/*257.4*/("""}"""),format.raw/*257.5*/("""
		"""),format.raw/*258.3*/("""}"""),format.raw/*258.4*/("""
	"""),format.raw/*259.2*/("""}"""),format.raw/*259.3*/("""
	
	"""),format.raw/*261.2*/("""function validarLectTer_oper()"""),format.raw/*261.32*/("""{"""),format.raw/*261.33*/("""
		"""),format.raw/*262.3*/("""let lecturaIni = $("#lecturaIni_oper").val().replace(/,/g,'');
		let lecturaTer = $("#lecturaTer_oper").val().replace(/,/g,'');
			if(parseFloat(lecturaIni) > parseFloat(lecturaTer))"""),format.raw/*264.55*/("""{"""),format.raw/*264.56*/("""
				"""),format.raw/*265.5*/("""alertify.alert("La lectura inicial no puede ser menor a la lectura de termino");
				$("#lecturaTer_oper").val(formatStandar(0,2));
				$("#cantidad_oper").val(formatStandar(0,2));
			"""),format.raw/*268.4*/("""}"""),format.raw/*268.5*/("""else"""),format.raw/*268.9*/("""{"""),format.raw/*268.10*/("""
				"""),format.raw/*269.5*/("""let dif = parseFloat(lecturaTer) - parseFloat(lecturaIni);
				$("#cantidad_oper").val(formatStandar(dif,2));
			"""),format.raw/*271.4*/("""}"""),format.raw/*271.5*/("""
	"""),format.raw/*272.2*/("""}"""),format.raw/*272.3*/("""
	
	"""),format.raw/*274.2*/("""function validarHoraIni_oper()"""),format.raw/*274.32*/("""{"""),format.raw/*274.33*/("""
		"""),format.raw/*275.3*/("""let horaIni = $("#horaIni_oper").val();
		let horaTer = $("#horaTer_oper").val();
		if(horaTer!="")"""),format.raw/*277.18*/("""{"""),format.raw/*277.19*/("""
			"""),format.raw/*278.4*/("""if(parseFloat(horaIni) >= parseFloat(horaTer))"""),format.raw/*278.50*/("""{"""),format.raw/*278.51*/("""
				"""),format.raw/*279.5*/("""alertify.alert("La hora inicial no puede ser menor o igual a la hora de termino");
				$("#horaIni_oper").val("");
				$("#horaTer_oper").val("");
			"""),format.raw/*282.4*/("""}"""),format.raw/*282.5*/("""
		"""),format.raw/*283.3*/("""}"""),format.raw/*283.4*/("""
	"""),format.raw/*284.2*/("""}"""),format.raw/*284.3*/("""
	
	"""),format.raw/*286.2*/("""function validarHoraTer_oper()"""),format.raw/*286.32*/("""{"""),format.raw/*286.33*/("""
		"""),format.raw/*287.3*/("""let horaIni = $("#horaIni_oper").val();
		let horaTer = $("#horaTer_oper").val();
		if(horaIni=="")"""),format.raw/*289.18*/("""{"""),format.raw/*289.19*/("""
			"""),format.raw/*290.4*/("""alertify.alert("Primero debe ingresar la hora inicial");
			$("#horaTer_oper").val("");
		"""),format.raw/*292.3*/("""}"""),format.raw/*292.4*/("""else"""),format.raw/*292.8*/("""{"""),format.raw/*292.9*/("""
			"""),format.raw/*293.4*/("""if(horaIni > horaTer)"""),format.raw/*293.25*/("""{"""),format.raw/*293.26*/("""
				"""),format.raw/*294.5*/("""alertify.alert("La hora inicial no puede ser menor a la hora de termino");
				$("#horaTer_oper").val("");
			"""),format.raw/*296.4*/("""}"""),format.raw/*296.5*/("""
		"""),format.raw/*297.3*/("""}"""),format.raw/*297.4*/("""
	"""),format.raw/*298.2*/("""}"""),format.raw/*298.3*/("""
	
	"""),format.raw/*300.2*/("""function grabarOperador()"""),format.raw/*300.27*/("""{"""),format.raw/*300.28*/(""" 
		  
		  """),format.raw/*302.5*/("""if($('#id_operador').val()==0)"""),format.raw/*302.35*/("""{"""),format.raw/*302.36*/("""
			  """),format.raw/*303.6*/("""alertify.alert('Debe seleccionar un Operador');
		  """),format.raw/*304.5*/("""}"""),format.raw/*304.6*/("""else if($('#id_equipo_oper').val()==0)"""),format.raw/*304.44*/("""{"""),format.raw/*304.45*/("""
			  """),format.raw/*305.6*/("""alertify.alert('Debe seleccionar un Equipo');
		  """),format.raw/*306.5*/("""}"""),format.raw/*306.6*/("""else if($('#id_bodega_oper').val()==0)"""),format.raw/*306.44*/("""{"""),format.raw/*306.45*/("""
			  """),format.raw/*307.6*/("""alertify.alert('Debe seleccionar una """),_display_(/*307.44*/mapDiccionario/*307.58*/.get("Bodega")),format.raw/*307.72*/("""/Proyecto');
		  """),format.raw/*308.5*/("""}"""),format.raw/*308.6*/("""else if($('#id_mantEstadoEnObra').val()==0)"""),format.raw/*308.49*/("""{"""),format.raw/*308.50*/("""
			  """),format.raw/*309.6*/("""alertify.alert('Debe ingresar un Estado en Sitio');
		  """),format.raw/*310.5*/("""}"""),format.raw/*310.6*/("""else if($('#lecturaTer_oper').val()=="0.00")"""),format.raw/*310.50*/("""{"""),format.raw/*310.51*/("""
			  """),format.raw/*311.6*/("""alertify.alert('Debe ingresar Lect_TER que debe ser mayor a cero');
		  """),format.raw/*312.5*/("""}"""),format.raw/*312.6*/("""else"""),format.raw/*312.10*/("""{"""),format.raw/*312.11*/("""
			"""),format.raw/*313.4*/("""let data = signaturePad.toDataURL('image/png');
			let encodedImage = data.split(",");
			$("#firmaEjecutor").val(encodedImage[1]);
			
			data = signaturePad2.toDataURL('image/png');
			encodedImage = data.split(",");
			$("#firmaAprobador").val(encodedImage[1]);
			document.getElementById('enCarga').style.display="block";
			$("#grabarRptOperadorMecanico").submit();
		  """),format.raw/*322.5*/("""}"""),format.raw/*322.6*/("""
	"""),format.raw/*323.2*/("""}"""),format.raw/*323.3*/("""
"""),format.raw/*324.1*/("""</script>"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],fecha:String,listEstaObra:List[tables.MantEstadoEnObra],listOperMec:List[tables.MantOperadorMecanico]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,fecha,listEstaObra,listOperMec)

  def f:((Map[String,String],String,List[tables.MantEstadoEnObra],List[tables.MantOperadorMecanico]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,fecha,listEstaObra,listOperMec) => apply(mapDiccionario,fecha,listEstaObra,listOperMec)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuMantencion/mantReportNewOperador.scala.html
                  HASH: 2eeaeb0644c455c6e9edff642eb515e269871003
                  MATRIX: 1057->1|1295->146|3267->2091|3290->2105|3325->2119|4064->2831|4087->2845|4122->2859|4725->3435|4767->3461|4806->3462|4855->3483|4898->3499|4912->3504|4936->3507|4967->3511|4981->3516|5009->3523|5057->3541|5097->3553|10556->8985|10598->9010|10638->9011|10715->9060|10755->9061|10792->9070|10850->9100|10865->9105|10895->9113|10933->9123|10948->9128|10982->9140|11021->9151|11036->9156|11081->9178|11166->9235|11181->9240|11212->9249|11301->9310|11316->9315|11350->9327|11438->9387|11453->9392|11497->9414|11573->9446|11620->9462|11654->9468|11980->9765|12010->9766|12041->9769|12202->9902|12231->9903|12263->9907|12339->9955|12368->9956|12405->9964|12435->9965|12467->9969|12526->10000|12555->10001|12586->10004|12668->10057|12698->10058|12730->10062|12909->10213|12938->10214|12994->10241|13024->10242|13056->10246|13136->10297|13166->10298|13199->10303|13388->10464|13417->10465|13449->10469|13479->10470|13512->10475|13653->10588|13682->10589|13713->10592|13742->10593|13772->10595|13801->10596|13833->10600|13892->10630|13922->10631|13953->10634|14164->10816|14194->10817|14227->10822|14439->11006|14468->11007|14500->11011|14530->11012|14563->11017|14704->11130|14733->11131|14763->11133|14792->11134|14824->11138|14883->11168|14913->11169|14944->11172|15072->11271|15102->11272|15134->11276|15209->11322|15239->11323|15272->11328|15450->11478|15479->11479|15510->11482|15539->11483|15569->11485|15598->11486|15630->11490|15689->11520|15719->11521|15750->11524|15878->11623|15908->11624|15940->11628|16058->11718|16087->11719|16119->11723|16148->11724|16180->11728|16230->11749|16260->11750|16293->11755|16431->11865|16460->11866|16491->11869|16520->11870|16550->11872|16579->11873|16611->11877|16665->11902|16695->11903|16734->11914|16793->11944|16823->11945|16857->11951|16937->12003|16966->12004|17033->12042|17063->12043|17097->12049|17175->12099|17204->12100|17271->12138|17301->12139|17335->12145|17401->12183|17425->12197|17461->12211|17506->12228|17535->12229|17607->12272|17637->12273|17671->12279|17755->12335|17784->12336|17857->12380|17887->12381|17921->12387|18021->12459|18050->12460|18083->12464|18113->12465|18145->12469|18548->12844|18577->12845|18607->12847|18636->12848|18665->12849
                  LINES: 28->1|33->2|77->46|77->46|77->46|94->63|94->63|94->63|107->76|107->76|107->76|108->77|108->77|108->77|108->77|108->77|108->77|108->77|109->78|110->79|246->215|246->215|246->215|247->216|247->216|248->217|248->217|248->217|248->217|248->217|248->217|248->217|248->217|248->217|248->217|249->218|249->218|249->218|250->219|250->219|250->219|251->220|251->220|251->220|253->222|255->224|256->225|270->239|270->239|271->240|273->242|273->242|274->243|275->244|275->244|275->244|275->244|276->245|277->246|277->246|278->247|278->247|278->247|279->248|281->250|281->250|281->250|281->250|282->251|282->251|282->251|283->252|285->254|285->254|285->254|285->254|286->255|288->257|288->257|289->258|289->258|290->259|290->259|292->261|292->261|292->261|293->262|295->264|295->264|296->265|299->268|299->268|299->268|299->268|300->269|302->271|302->271|303->272|303->272|305->274|305->274|305->274|306->275|308->277|308->277|309->278|309->278|309->278|310->279|313->282|313->282|314->283|314->283|315->284|315->284|317->286|317->286|317->286|318->287|320->289|320->289|321->290|323->292|323->292|323->292|323->292|324->293|324->293|324->293|325->294|327->296|327->296|328->297|328->297|329->298|329->298|331->300|331->300|331->300|333->302|333->302|333->302|334->303|335->304|335->304|335->304|335->304|336->305|337->306|337->306|337->306|337->306|338->307|338->307|338->307|338->307|339->308|339->308|339->308|339->308|340->309|341->310|341->310|341->310|341->310|342->311|343->312|343->312|343->312|343->312|344->313|353->322|353->322|354->323|354->323|355->324
                  -- GENERATED --
              */
          