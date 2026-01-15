
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

object mantWebReportNewOperador extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template9[Map[String,String],String,List[tables.MantEstadoEnObra],tables.MantOperadorMecanico,String,String,String,String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], fecha: String, listEstaObra: List[tables.MantEstadoEnObra], operMec: tables.MantOperadorMecanico, idEquipo: String, cod_nameEquipo: String, id_bodega: String, suc_nameBodega: String, nameTipoBodega: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""<div id="enCarga" class="blocker" style="display: none;"><br><br><br><br><br><br><h1>En proceso.....<div id="msgEspera"></div></h1></div>
	<table class="table table-sm table-bordered table-condensed table-fluid">
		<thead>
			<tr>
				<td style="text-align:left;"><strong>OPERADOR (*):</strong></td>
		        <td style="text-align:left;  width: 70%"  colspan="4">
					<input type="hidden" id="id_operador" name="id_operador" value=""""),_display_(/*8.71*/operMec/*8.78*/.getId()),format.raw/*8.86*/("""" required>
					<input type="hidden" id="id_mecanico" name="id_mecanico" value="0" required>
					<input type="text" class="form-control" value=""""),_display_(/*10.54*/operMec/*10.61*/.getNombre()),format.raw/*10.73*/("""" readonly>
		        </td>
			</tr>
			<tr>
				<td style="text-align:left;"><strong>EQUIPO (*):</strong></td>
		        <td style="text-align:left; width: 70%" colspan="4">
		        	<input type="hidden" id="id_equipo_oper" name="id_equipo_oper" value=""""),_display_(/*16.83*/idEquipo),format.raw/*16.91*/("""">
		        	<div id="nombreEquipo_oper">
		        		<div class="input-group input-group-sm">
						  <input class="form-control" type="text" 
							onclick="$('#modalListaEquipos_oper').modal('show');" 
							id="valNomEquip_oper"
							value=""""),_display_(/*22.16*/cod_nameEquipo),format.raw/*22.30*/("""" readonly>
						  <div class="input-group-append">
						    <span class="input-group-text" onclick="$('#modalListaEquipos_oper').modal('show');">Buscar</span>
						  </div>
						</div> 
    				</div>
		        </td>
			</tr>
			<tr>
				<td style="text-align:left;"><strong>"""),_display_(/*31.43*/mapDiccionario/*31.57*/.get("BODEGA")),format.raw/*31.71*/("""/PROYECTO (*):</strong></td>
		        <td style="text-align:left; width: 70%" colspan="4">
		        	<input type="hidden" id="id_bodega_oper" name="id_bodega_oper" value=""""),_display_(/*33.83*/id_bodega),format.raw/*33.92*/("""">
		        	<div id="nombreBodega_oper">
		        		<div class="input-group input-group-sm">
						  <input class="form-control" type="text" 
							onclick="$('#modalListaBodegas').modal('show');" 
							id="valNomBod_oper"
							value=""""),_display_(/*39.16*/suc_nameBodega),format.raw/*39.30*/("""" readonly>
						  <div class="input-group-append">
						    <span class="input-group-text" onclick="$('#modalListaBodegas').modal('show');">Buscar</span>
						  </div>
						</div> 
    				</div>
		        </td>
			</tr>
			<tr>
				<td style="text-align:left;"><strong>TIPO """),_display_(/*48.48*/mapDiccionario/*48.62*/.get("BODEGA")),format.raw/*48.76*/("""/PROYECTO:</strong></td>
		        <td style="text-align:left; width: 70%" colspan="4">
					<input class="form-control" id="tipoBodega_oper" type="text" value=""""),_display_(/*50.75*/nameTipoBodega),format.raw/*50.89*/("""" readonly>
		        </td>
			</tr>
			<tr>
				<td style="text-align:left;"><strong>ESTADO EN SITIO (*):</strong></td>
		        <td style="text-align:left; width: 70%" colspan="4">
		        	<select class="form-control form-control-sm" 
						id="id_mantEstadoEnObra" 
						name="id_mantEstadoEnObra"
						required disabled>
			        		<option class="blank" value="0">-- Select --</option>
		            		"""),_display_(/*61.18*/for(lista <- listEstaObra) yield /*61.44*/{_display_(Seq[Any](format.raw/*61.45*/("""
		                		"""),format.raw/*62.21*/("""<option value=""""),_display_(/*62.37*/lista/*62.42*/.id),format.raw/*62.45*/("""" >"""),_display_(/*62.49*/lista/*62.54*/.nombre),format.raw/*62.61*/("""</option>
							""")))}),format.raw/*63.9*/("""
			        """),format.raw/*64.12*/("""</select> 
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
		        <td style="text-align:left; width: 70%" width="70%" colspan="4">
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
					<button type="button" class="btn btn-sm btn-warning" style="font-size: 10px;" onclick="location.href = '/report'">SALIR</button>
				</td>
			</tr>
		</thead>
	</table>

<div id="viewMecanico" style="display:none;">
	<canvas id="signature-pad3" class="signature-pad" width=350 height=90 style="border:1px solid #000000;"></canvas>
	<canvas id="signature-pad4" class="signature-pad" width=350 height=90 style="border:1px solid #000000;"></canvas>
	<canvas id="signature-pad5" class="signature-pad" width=350 height=90 style="border:1px solid #000000;"></canvas>
	<canvas id="signature-pad6" class="signature-pad" width=350 height=90 style="border:1px solid #000000;"></canvas>
</div>

<input type="hidden" id="nombreEquipo_mec" value="0">
<input type="hidden" id="nombreEquipo_mecP" value="0">
<input type="hidden" id="nombreBodega_mec" value="0">
<input type="hidden" id="nombreBodega_mecP" value="0">
	
	
<script>
	function validarLectIni_oper()"""),format.raw/*195.32*/("""{"""),format.raw/*195.33*/("""
		"""),format.raw/*196.3*/("""let lecturaIni = $("#lecturaIni_oper").val().replace(/,/g,'');
		let lecturaTer = $("#lecturaTer_oper").val().replace(/,/g,'');
		try"""),format.raw/*198.6*/("""{"""),format.raw/*198.7*/("""
			"""),format.raw/*199.4*/("""lectAnterior = lectAnterior.replace(/,/g,'');
		"""),format.raw/*200.3*/("""}"""),format.raw/*200.4*/("""catch(e)"""),format.raw/*200.12*/("""{"""),format.raw/*200.13*/("""
			"""),format.raw/*201.4*/("""lectAnterior = lectAnterior;
		"""),format.raw/*202.3*/("""}"""),format.raw/*202.4*/("""
		"""),format.raw/*203.3*/("""if(parseFloat(lecturaIni) < parseFloat(lectAnterior))"""),format.raw/*203.56*/("""{"""),format.raw/*203.57*/("""
			"""),format.raw/*204.4*/("""alertify.alert("La lectura inicial no puede ser menor a la ultima lectura registrada");
			$("#lecturaIni_oper").val(formatStandar(lectAnterior,2));
		"""),format.raw/*206.3*/("""}"""),format.raw/*206.4*/("""else if(lecturaTer!="0.00")"""),format.raw/*206.31*/("""{"""),format.raw/*206.32*/("""
			"""),format.raw/*207.4*/("""if(parseFloat(lecturaIni) > parseFloat(lecturaTer))"""),format.raw/*207.55*/("""{"""),format.raw/*207.56*/("""
				"""),format.raw/*208.5*/("""alertify.alert("La lectura inicial no puede ser menor a la lectura de termino");
				$("#lecturaIni_oper").val(formatStandar(formatStandar(lectAnterior,2)));
			"""),format.raw/*210.4*/("""}"""),format.raw/*210.5*/("""else"""),format.raw/*210.9*/("""{"""),format.raw/*210.10*/("""
				"""),format.raw/*211.5*/("""let dif = parseFloat(lecturaTer) - parseFloat(lecturaIni);
				$("#cantidad_oper").val(formatStandar(dif,2));
			"""),format.raw/*213.4*/("""}"""),format.raw/*213.5*/("""
		"""),format.raw/*214.3*/("""}"""),format.raw/*214.4*/("""
	"""),format.raw/*215.2*/("""}"""),format.raw/*215.3*/("""
	
	"""),format.raw/*217.2*/("""function validarLectTer_oper()"""),format.raw/*217.32*/("""{"""),format.raw/*217.33*/("""
		"""),format.raw/*218.3*/("""let lecturaIni = $("#lecturaIni_oper").val().replace(/,/g,'');
		let lecturaTer = $("#lecturaTer_oper").val().replace(/,/g,'');
			if(parseFloat(lecturaIni) > parseFloat(lecturaTer))"""),format.raw/*220.55*/("""{"""),format.raw/*220.56*/("""
				"""),format.raw/*221.5*/("""alertify.alert("La lectura inicial no puede ser menor a la lectura de termino");
				$("#lecturaTer_oper").val(formatStandar(0,2));
				$("#cantidad_oper").val(formatStandar(0,2));
			"""),format.raw/*224.4*/("""}"""),format.raw/*224.5*/("""else"""),format.raw/*224.9*/("""{"""),format.raw/*224.10*/("""
				"""),format.raw/*225.5*/("""let dif = parseFloat(lecturaTer) - parseFloat(lecturaIni);
				$("#cantidad_oper").val(formatStandar(dif,2));
			"""),format.raw/*227.4*/("""}"""),format.raw/*227.5*/("""
	"""),format.raw/*228.2*/("""}"""),format.raw/*228.3*/("""
	
	"""),format.raw/*230.2*/("""function validarHoraIni_oper()"""),format.raw/*230.32*/("""{"""),format.raw/*230.33*/("""
		"""),format.raw/*231.3*/("""let horaIni = $("#horaIni_oper").val();
		let horaTer = $("#horaTer_oper").val();
		if(horaTer!="")"""),format.raw/*233.18*/("""{"""),format.raw/*233.19*/("""
			"""),format.raw/*234.4*/("""if(parseFloat(horaIni) >= parseFloat(horaTer))"""),format.raw/*234.50*/("""{"""),format.raw/*234.51*/("""
				"""),format.raw/*235.5*/("""alertify.alert("La hora inicial no puede ser menor o igual a la hora de termino");
				$("#horaIni_oper").val("");
				$("#horaTer_oper").val("");
			"""),format.raw/*238.4*/("""}"""),format.raw/*238.5*/("""
		"""),format.raw/*239.3*/("""}"""),format.raw/*239.4*/("""
	"""),format.raw/*240.2*/("""}"""),format.raw/*240.3*/("""
	
	"""),format.raw/*242.2*/("""function validarHoraTer_oper()"""),format.raw/*242.32*/("""{"""),format.raw/*242.33*/("""
		"""),format.raw/*243.3*/("""let horaIni = $("#horaIni_oper").val();
		let horaTer = $("#horaTer_oper").val();
		if(horaIni=="")"""),format.raw/*245.18*/("""{"""),format.raw/*245.19*/("""
			"""),format.raw/*246.4*/("""alertify.alert("Primero debe ingresar la hora inicial");
			$("#horaTer_oper").val("");
		"""),format.raw/*248.3*/("""}"""),format.raw/*248.4*/("""else"""),format.raw/*248.8*/("""{"""),format.raw/*248.9*/("""
			"""),format.raw/*249.4*/("""if(horaIni > horaTer)"""),format.raw/*249.25*/("""{"""),format.raw/*249.26*/("""
				"""),format.raw/*250.5*/("""alertify.alert("La hora inicial no puede ser menor a la hora de termino");
				$("#horaTer_oper").val("");
			"""),format.raw/*252.4*/("""}"""),format.raw/*252.5*/("""
		"""),format.raw/*253.3*/("""}"""),format.raw/*253.4*/("""
	"""),format.raw/*254.2*/("""}"""),format.raw/*254.3*/("""
	
	"""),format.raw/*256.2*/("""function grabarOperador()"""),format.raw/*256.27*/("""{"""),format.raw/*256.28*/("""
		  """),format.raw/*257.5*/("""if($('#id_operador').val()==0)"""),format.raw/*257.35*/("""{"""),format.raw/*257.36*/("""
			  """),format.raw/*258.6*/("""alertify.alert('Debe seleccionar un Operador');
		  """),format.raw/*259.5*/("""}"""),format.raw/*259.6*/("""else if($('#id_equipo_oper').val()==0)"""),format.raw/*259.44*/("""{"""),format.raw/*259.45*/("""
			  """),format.raw/*260.6*/("""alertify.alert('Debe seleccionar un Equipo');
		  """),format.raw/*261.5*/("""}"""),format.raw/*261.6*/("""else if($('#id_bodega_oper').val()==0)"""),format.raw/*261.44*/("""{"""),format.raw/*261.45*/("""
			  """),format.raw/*262.6*/("""alertify.alert('Debe seleccionar una """),_display_(/*262.44*/mapDiccionario/*262.58*/.get("Bodega")),format.raw/*262.72*/("""/Proyecto');
		  """),format.raw/*263.5*/("""}"""),format.raw/*263.6*/("""else if($('#id_mantEstadoEnObra').val()==0)"""),format.raw/*263.49*/("""{"""),format.raw/*263.50*/("""
			  """),format.raw/*264.6*/("""alertify.alert('Debe ingresar un Estado en Sitio');
		  """),format.raw/*265.5*/("""}"""),format.raw/*265.6*/("""else if($('#lecturaTer_oper').val()=="0.00")"""),format.raw/*265.50*/("""{"""),format.raw/*265.51*/("""
			  """),format.raw/*266.6*/("""alertify.alert('Debe ingresar Lect_TER que debe ser mayor a cero');
		  """),format.raw/*267.5*/("""}"""),format.raw/*267.6*/("""else"""),format.raw/*267.10*/("""{"""),format.raw/*267.11*/("""
			"""),format.raw/*268.4*/("""let data = signaturePad.toDataURL('image/png');
			let encodedImage = data.split(",");
			$("#firmaEjecutor").val(encodedImage[1]);

			data = signaturePad2.toDataURL('image/png');
			encodedImage = data.split(",");
			$("#firmaAprobador").val(encodedImage[1]);
			document.getElementById('enCarga').style.display="block";
			$("#grabarRptOperadorMecanico").submit();
		  """),format.raw/*277.5*/("""}"""),format.raw/*277.6*/("""
	"""),format.raw/*278.2*/("""}"""),format.raw/*278.3*/("""
"""),format.raw/*279.1*/("""</script>"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],fecha:String,listEstaObra:List[tables.MantEstadoEnObra],operMec:tables.MantOperadorMecanico,idEquipo:String,cod_nameEquipo:String,id_bodega:String,suc_nameBodega:String,nameTipoBodega:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,fecha,listEstaObra,operMec,idEquipo,cod_nameEquipo,id_bodega,suc_nameBodega,nameTipoBodega)

  def f:((Map[String,String],String,List[tables.MantEstadoEnObra],tables.MantOperadorMecanico,String,String,String,String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,fecha,listEstaObra,operMec,idEquipo,cod_nameEquipo,id_bodega,suc_nameBodega,nameTipoBodega) => apply(mapDiccionario,fecha,listEstaObra,operMec,idEquipo,cod_nameEquipo,id_bodega,suc_nameBodega,nameTipoBodega)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuMantencion/mantWebReportNewOperador.scala.html
                  HASH: 6d924d707326032b9c742d529a74d68d601ed5ef
                  MATRIX: 1089->1|1426->245|1887->680|1902->687|1930->695|2104->842|2120->849|2153->861|2437->1118|2466->1126|2744->1377|2779->1391|3087->1672|3110->1686|3145->1700|3346->1874|3376->1883|3647->2127|3682->2141|3990->2422|4013->2436|4048->2450|4237->2612|4272->2626|4714->3041|4756->3067|4795->3068|4844->3089|4887->3105|4901->3110|4925->3113|4956->3117|4970->3122|4998->3129|5046->3147|5086->3159|10520->8564|10550->8565|10581->8568|10742->8701|10771->8702|10803->8706|10879->8754|10908->8755|10945->8763|10975->8764|11007->8768|11066->8799|11095->8800|11126->8803|11208->8856|11238->8857|11270->8861|11449->9012|11478->9013|11534->9040|11564->9041|11596->9045|11676->9096|11706->9097|11739->9102|11928->9263|11957->9264|11989->9268|12019->9269|12052->9274|12193->9387|12222->9388|12253->9391|12282->9392|12312->9394|12341->9395|12373->9399|12432->9429|12462->9430|12493->9433|12704->9615|12734->9616|12767->9621|12979->9805|13008->9806|13040->9810|13070->9811|13103->9816|13244->9929|13273->9930|13303->9932|13332->9933|13364->9937|13423->9967|13453->9968|13484->9971|13612->10070|13642->10071|13674->10075|13749->10121|13779->10122|13812->10127|13990->10277|14019->10278|14050->10281|14079->10282|14109->10284|14138->10285|14170->10289|14229->10319|14259->10320|14290->10323|14418->10422|14448->10423|14480->10427|14598->10517|14627->10518|14659->10522|14688->10523|14720->10527|14770->10548|14800->10549|14833->10554|14971->10664|15000->10665|15031->10668|15060->10669|15090->10671|15119->10672|15151->10676|15205->10701|15235->10702|15268->10707|15327->10737|15357->10738|15391->10744|15471->10796|15500->10797|15567->10835|15597->10836|15631->10842|15709->10892|15738->10893|15805->10931|15835->10932|15869->10938|15935->10976|15959->10990|15995->11004|16040->11021|16069->11022|16141->11065|16171->11066|16205->11072|16289->11128|16318->11129|16391->11173|16421->11174|16455->11180|16555->11252|16584->11253|16617->11257|16647->11258|16679->11262|17079->11634|17108->11635|17138->11637|17167->11638|17196->11639
                  LINES: 28->1|33->2|39->8|39->8|39->8|41->10|41->10|41->10|47->16|47->16|53->22|53->22|62->31|62->31|62->31|64->33|64->33|70->39|70->39|79->48|79->48|79->48|81->50|81->50|92->61|92->61|92->61|93->62|93->62|93->62|93->62|93->62|93->62|93->62|94->63|95->64|226->195|226->195|227->196|229->198|229->198|230->199|231->200|231->200|231->200|231->200|232->201|233->202|233->202|234->203|234->203|234->203|235->204|237->206|237->206|237->206|237->206|238->207|238->207|238->207|239->208|241->210|241->210|241->210|241->210|242->211|244->213|244->213|245->214|245->214|246->215|246->215|248->217|248->217|248->217|249->218|251->220|251->220|252->221|255->224|255->224|255->224|255->224|256->225|258->227|258->227|259->228|259->228|261->230|261->230|261->230|262->231|264->233|264->233|265->234|265->234|265->234|266->235|269->238|269->238|270->239|270->239|271->240|271->240|273->242|273->242|273->242|274->243|276->245|276->245|277->246|279->248|279->248|279->248|279->248|280->249|280->249|280->249|281->250|283->252|283->252|284->253|284->253|285->254|285->254|287->256|287->256|287->256|288->257|288->257|288->257|289->258|290->259|290->259|290->259|290->259|291->260|292->261|292->261|292->261|292->261|293->262|293->262|293->262|293->262|294->263|294->263|294->263|294->263|295->264|296->265|296->265|296->265|296->265|297->266|298->267|298->267|298->267|298->267|299->268|308->277|308->277|309->278|309->278|310->279
                  -- GENERATED --
              */
          