
package viewsMnuPlanes.html

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

object hojaVidaMantencionPlan extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.PlanMantencion],List[String],List[String],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
allPlan: List[tables.PlanMantencion], listAtributos: List[String], listCompra: List[String]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""


    	
"""),_display_(/*7.2*/main("")/*7.10*/ {_display_(Seq[Any](format.raw/*7.12*/("""

"""),_display_(/*9.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*9.50*/("""
	"""),format.raw/*10.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*11.4*/barraTitulo(mapDiccionario, "PROGRAMA PLAN DE MANTENCION","MODIFICAR")),format.raw/*11.74*/("""
		"""),format.raw/*12.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-9 col-md-9 col-lg-9">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<tr>
						<td colspan="2">
							<b>DETALLE DEL EQUIPO</b>
						</td>
						<td colspan="6">
							<b>ULTIMA COMPRA</b>
						</td>
						
					</tr>
					<tr>
						<td>GRUPO:</td>
						<td>"""),_display_(/*26.12*/allPlan/*26.19*/.get(0).equipoGrupo),format.raw/*26.38*/("""</td>
						<td>"""),_display_(/*27.12*/mapDiccionario/*27.26*/.get("RUT")),format.raw/*27.37*/("""-PROV:</td>
						<td>"""),_display_(/*28.12*/listCompra/*28.22*/.get(0)),format.raw/*28.29*/("""</td>
						<td>FECHA:</td>
						<td>"""),_display_(/*30.12*/listCompra/*30.22*/.get(3)),format.raw/*30.29*/("""</td>
						<td>MONEDA:</td>
						<td>"""),_display_(/*32.12*/listCompra/*32.22*/.get(5)),format.raw/*32.29*/("""</td>
					</tr>
					<tr>
						<td>CODIGO EQUIPO:</td>
						<td>"""),_display_(/*36.12*/allPlan/*36.19*/.get(0).equipoCodigo),format.raw/*36.39*/("""</td>
						<td>PROVEEDOR:</td>
						<td>"""),_display_(/*38.12*/listCompra/*38.22*/.get(1)),format.raw/*38.29*/("""</td>
						<td>PDF:</td>
						<td>
							"""),_display_(if(listCompra.get(4)!="0")/*41.35*/{_display_(Seq[Any](format.raw/*41.36*/("""
								"""),format.raw/*42.9*/("""<a href="#" onclick="mostrarPDF('"""),_display_(/*42.43*/listCompra/*42.53*/.get(4)),format.raw/*42.60*/("""')">
									<kbd style="background-color: #85C1E9">pdf</kbd>
								</a>
							""")))}else/*45.13*/{_display_(Seq[Any](format.raw/*45.14*/("""
							"""),format.raw/*46.8*/("""---
							""")))}),format.raw/*47.9*/("""
						"""),format.raw/*48.7*/("""</td>
						<td>PRECIO:</td>
						<td>"""),_display_(/*50.12*/listCompra/*50.22*/.get(6)),format.raw/*50.29*/("""</td>
					</tr>
					<tr>
						<td>NOMBRE EQUIPO:</td>
						<td>"""),_display_(/*54.12*/allPlan/*54.19*/.get(0).equipoNombre),format.raw/*54.39*/("""</td>
						<td>FACTURA:</td>
						<td>"""),_display_(/*56.12*/listCompra/*56.22*/.get(2)),format.raw/*56.29*/("""</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td colspan="20">
							<b>ATRIBUTOS DEL EQUIPO</b>
						</td>
					</tr>
					<tr>
						<td colspan="20">
							"""),_display_(/*67.9*/for(lista <- listAtributos) yield /*67.36*/{_display_(Seq[Any](format.raw/*67.37*/("""
									"""),_display_(/*68.11*/lista),format.raw/*68.16*/("""|
								""")))}),format.raw/*69.10*/("""
						"""),format.raw/*70.7*/("""</td>
					</tr>
				</table>
				<table id="planes" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead>
						<tr>
							<td colspan="20">
								<h5><b><font color="#008000"> PLAN DE MANTENCION</font></b></h5>
							</td>
						</tr>
						<tr>
							<TH style="text-align:center">Plan</TH>
							<TH style="text-align:center">Fecha<br>Lectura</TH>
							<TH style="text-align:center">Unidad</TH>
							<TH style="text-align:center">Cantidad<br>Lectura</TH>
							<TH style="text-align:center">Rotación (Cada X Cantidad<br>Correspone Mantención)</TH>
							<TH style="text-align:center">Consumo Promedio<br>Mensual Estimado</TH>
							<TH style="text-align:center">Corresponde Próxima<br>Mantención a los</TH>
							<TH style="text-align:center">Fecha Estimada<br>Mantencion</TH>
						</tr>
					</thead>
					<tbody>
						"""),_display_(/*92.8*/for((lista, index) <- allPlan.zipWithIndex) yield /*92.51*/ {_display_(Seq[Any](format.raw/*92.53*/("""
							"""),format.raw/*93.8*/("""<tr>
								<td style="text-align:center">
									<input type="hidden" class="id_tipoPlan" value=""""),_display_(/*95.59*/lista/*95.64*/.id_tipoPlan),format.raw/*95.76*/("""">
									"""),_display_(/*96.11*/lista/*96.16*/.tipoPlanNombre),format.raw/*96.31*/("""
								"""),format.raw/*97.9*/("""</td>

								<td style="text-align:center">
									<input type="date" class="fechaReset form-control center"
										onblur="if(!limitaFecha(value,720,10)) """),format.raw/*101.50*/("""{"""),format.raw/*101.51*/("""
													"""),format.raw/*102.14*/("""value = '"""),_display_(/*102.24*/utilities/*102.33*/.Fechas.AAMMDD(lista.fechaReset)),format.raw/*102.65*/("""';
												"""),format.raw/*103.13*/("""}"""),format.raw/*103.14*/(""" """),format.raw/*103.15*/("""else """),format.raw/*103.20*/("""{"""),format.raw/*103.21*/("""
													"""),format.raw/*104.14*/("""actualizaAll('"""),_display_(/*104.29*/index),format.raw/*104.34*/("""', '"""),_display_(/*104.39*/lista/*104.44*/.id_equipo),format.raw/*104.54*/("""', 'fechaReset', value);
												"""),format.raw/*105.13*/("""}"""),format.raw/*105.14*/(""""
										value=""""),_display_(/*106.19*/utilities/*106.28*/.Fechas.AAMMDD(lista.fechaReset)),format.raw/*106.60*/("""">
								</td>

								<td style="text-align:center">
									<input type="hidden" class="id_unidadMantencion" value=""""),_display_(/*110.67*/lista/*110.72*/.id_unidadMantencion),format.raw/*110.92*/("""">
									"""),_display_(/*111.11*/lista/*111.16*/.unidadMantencion),format.raw/*111.33*/("""
								"""),format.raw/*112.9*/("""</td>
								<!-- Estado Actual -->
								<td style="text-align:right">
									<input type="text" class="estadoActual form-control center"
										id="lectActual_"""),_display_(/*116.27*/index),format.raw/*116.32*/(""""
										value=""""),_display_(/*117.19*/lista/*117.24*/.estadoActual),format.raw/*117.37*/(""""
										onfocus="value = value.replace(/,/g,''); auxiliar = value;"
										onkeydown="return ingresoDouble(window.event)"
										autocomplete="off"
										onchange="if(value=='') value=0;
											actualizaAll('"""),_display_(/*122.27*/index),format.raw/*122.32*/("""', '"""),_display_(/*122.37*/lista/*122.42*/.id_equipo),format.raw/*122.52*/("""', 'estadoActual', value);">
								</td>

								<td style="text-align:center">
									<input type="hidden" class="cadaNEstimado" value=""""),_display_(/*126.61*/lista/*126.66*/.cadaNEstimado),format.raw/*126.80*/("""">
									"""),_display_(/*127.11*/lista/*127.16*/.cadaNEstimado),format.raw/*127.30*/("""
								"""),format.raw/*128.9*/("""</td>
								<td style="text-align:center">
									<input type="hidden" class="consumoEstimadoPorMes" value=""""),_display_(/*130.69*/lista/*130.74*/.consumoEstimadoPorMes),format.raw/*130.96*/("""">
									"""),_display_(/*131.11*/lista/*131.16*/.consumoEstimadoPorMes),format.raw/*131.38*/("""
								"""),format.raw/*132.9*/("""</td>

								"""),_display_(if(mapPermiso.get("parametro.planes_calculaProximaMant") != null && mapPermiso.get("parametro.planes_calculaProximaMant").equals("0"))/*134.144*/ {_display_(Seq[Any](format.raw/*134.146*/("""
									"""),format.raw/*135.10*/("""<!-- Modo edición manual -->
									<td style="text-align:right">
										<input type="text" class="proximaMantencion form-control center"
											   id="proximaMantencion_"""),_display_(/*138.38*/index),format.raw/*138.43*/(""""
											   value=""""),_display_(/*139.23*/lista/*139.28*/.proximaMantencion),format.raw/*139.46*/(""""
											   onfocus="value = value.replace(/,/g,'');"
											   onkeydown="return ingresoDouble(event, value)"
											   autocomplete="off"
											   onchange="if(value == '') value = 0; recalcularFechaEstimada('"""),_display_(/*143.78*/index),format.raw/*143.83*/("""', '"""),_display_(/*143.88*/lista/*143.93*/.id_tipoPlan),format.raw/*143.105*/("""', '"""),_display_(/*143.110*/lista/*143.115*/.id_equipo),format.raw/*143.125*/("""');">
									</td>
								""")))}else/*145.16*/{_display_(Seq[Any](format.raw/*145.17*/("""
									"""),format.raw/*146.10*/("""<!-- Modo cálculo automático -->
									<td style="text-align:right">
										<input type="text" class="proximaMantencion form-control center"
											   id="proximaMantencion_"""),_display_(/*149.38*/index),format.raw/*149.43*/(""""
											   value=""""),_display_(/*150.23*/lista/*150.28*/.proximaMantencion),format.raw/*150.46*/(""""
											   disabled>
									</td>
								""")))}),format.raw/*153.10*/("""

								"""),format.raw/*155.9*/("""<td style="text-align:center">
									<input type="date" class="fechaEstimada form-control center" disabled>
								</td>
							</tr>
						""")))}),format.raw/*159.8*/("""
					"""),format.raw/*160.6*/("""</tbody>
				</table>
			</div>
		</div>	
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="VOLVER" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/hojaVidaMantencionLista/0';">
				</div>
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
				</div>
			</div>
		</div>
	</div>
	
	<div id='muestraPDF' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>COMPRA</h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<div id="rutaPDF"></div>
	   				<div class='row'>
						<div class='col-sm-12' style='text-align:center'>
							<button type='button' class='btn btn-sm  btn-success' style='font-size: 10px;' data-dismiss='modal'>Listo</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
""")))}),format.raw/*197.2*/("""




"""),format.raw/*202.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*203.31*/("""{"""),format.raw/*203.32*/("""
		"""),format.raw/*204.3*/("""calcFechaProxima();
		document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*206.2*/("""}"""),format.raw/*206.3*/(""");
	
	const calcFechaProxima = () => """),format.raw/*208.33*/("""{"""),format.raw/*208.34*/("""
	    """),format.raw/*209.6*/("""let filas = $(".estadoActual").length;
	    let hoy = new Date();
	
	    for (let i = 0; i < filas; i++) """),format.raw/*212.38*/("""{"""),format.raw/*212.39*/("""
	        """),format.raw/*213.10*/("""let fecha = $(".fechaReset")[i].value; // Fecha de referencia
	        let proxima = parseFloat($(".proximaMantencion")[i].value.replace(/,/g, '') || 0);
	        let actual = parseFloat($(".estadoActual")[i].value.replace(/,/g, '') || 0);
	        let rotacion = parseFloat($(".consumoEstimadoPorMes")[i].value.replace(/,/g, '') || 0);
	
	        let dif = proxima - actual;
	
	        if (dif <= 0 || rotacion <= 0) """),format.raw/*220.41*/("""{"""),format.raw/*220.42*/("""
	            """),format.raw/*221.14*/("""$(".fechaEstimada")[i].value = fechaAAMMDD(hoy);
	        """),format.raw/*222.10*/("""}"""),format.raw/*222.11*/(""" """),format.raw/*222.12*/("""else """),format.raw/*222.17*/("""{"""),format.raw/*222.18*/("""
	            """),format.raw/*223.14*/("""let diasASumar = (dif / rotacion) * 30;
	
	            let auxFecha;
	            try """),format.raw/*226.18*/("""{"""),format.raw/*226.19*/("""
	                """),format.raw/*227.18*/("""let aux = fecha.split("-");
	                auxFecha = new Date(aux[0], parseInt(aux[1]) - 1, aux[2]);
	                auxFecha.setDate(auxFecha.getDate() + diasASumar);
	            """),format.raw/*230.14*/("""}"""),format.raw/*230.15*/(""" """),format.raw/*230.16*/("""catch (error) """),format.raw/*230.30*/("""{"""),format.raw/*230.31*/("""
	                """),format.raw/*231.18*/("""auxFecha = hoy;
	            """),format.raw/*232.14*/("""}"""),format.raw/*232.15*/("""
	
	            """),format.raw/*234.14*/("""$(".fechaEstimada")[i].value = fechaAAMMDD(auxFecha);
	        """),format.raw/*235.10*/("""}"""),format.raw/*235.11*/("""
	    """),format.raw/*236.6*/("""}"""),format.raw/*236.7*/("""
	"""),format.raw/*237.2*/("""}"""),format.raw/*237.3*/("""

	
	"""),format.raw/*240.2*/("""const mostrarPDF = (nombrePDF) => """),format.raw/*240.36*/("""{"""),format.raw/*240.37*/("""
		  """),format.raw/*241.5*/("""document.getElementById('rutaPDF').innerHTML="<object data='/showPdf/"+nombrePDF+"' type='application/pdf' width='100%' height='720'></object>";
		  $('#muestraPDF').modal('show');
	"""),format.raw/*243.2*/("""}"""),format.raw/*243.3*/("""
	
	"""),format.raw/*245.2*/("""const actualizaPorCampo = (id_tipoPlan, id_equipo, campo, valor) =>"""),format.raw/*245.69*/("""{"""),format.raw/*245.70*/("""
		"""),format.raw/*246.3*/("""var formData = new FormData();
	  	formData.append('id_tipoPlan',id_tipoPlan);
		formData.append('id_equipo',id_equipo);
		formData.append('campo',campo);
	  	formData.append('valor',valor);
        $.ajax("""),format.raw/*251.16*/("""{"""),format.raw/*251.17*/("""
            """),format.raw/*252.13*/("""url: "/actualCampoPlanMantencionAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*259.36*/("""{"""),format.raw/*259.37*/("""
				"""),format.raw/*260.5*/("""calcFechaProxima();
	     	"""),format.raw/*261.8*/("""}"""),format.raw/*261.9*/("""
        """),format.raw/*262.9*/("""}"""),format.raw/*262.10*/(""");
	"""),format.raw/*263.2*/("""}"""),format.raw/*263.3*/("""

	"""),format.raw/*265.2*/("""const actualizaAll = (index, id_equipo, campo, valor) => """),format.raw/*265.59*/("""{"""),format.raw/*265.60*/("""
	    """),format.raw/*266.6*/("""const unidadCambiada = $(".id_unidadMantencion")[index].value;
	    const filas = $(".estadoActual").length;
	    for (let i = 0; i < filas; i++) """),format.raw/*268.38*/("""{"""),format.raw/*268.39*/("""
	        """),format.raw/*269.10*/("""if ($(".id_unidadMantencion")[i].value === unidadCambiada) """),format.raw/*269.69*/("""{"""),format.raw/*269.70*/("""
	            """),format.raw/*270.14*/("""if (campo === 'estadoActual') """),format.raw/*270.44*/("""{"""),format.raw/*270.45*/(""" 
	                """),format.raw/*271.18*/("""$(".estadoActual")[i].value = valor;
	                actualizaPorCampo($(".id_tipoPlan")[i].value, id_equipo, campo, valor); 
	                actualizarProximaMantencion(i, $(".id_tipoPlan")[i].value, id_equipo, campo); 
	            """),format.raw/*274.14*/("""}"""),format.raw/*274.15*/("""
				"""),format.raw/*275.5*/("""if (campo === 'fechaReset') """),format.raw/*275.33*/("""{"""),format.raw/*275.34*/("""
					"""),format.raw/*276.6*/("""$(".fechaReset")[i].value = valor;
					actualizaPorCampo($(".id_tipoPlan")[i].value, id_equipo, campo, valor);
					actualizarProximaMantencion(i, $(".id_tipoPlan")[i].value, id_equipo, campo);
				"""),format.raw/*279.5*/("""}"""),format.raw/*279.6*/("""
	        """),format.raw/*280.10*/("""}"""),format.raw/*280.11*/("""
			"""),format.raw/*281.4*/("""if ($(".id_unidadMantencion")[i].value === unidadCambiada) """),format.raw/*281.63*/("""{"""),format.raw/*281.64*/("""

			"""),format.raw/*283.4*/("""}"""),format.raw/*283.5*/("""
	    """),format.raw/*284.6*/("""}"""),format.raw/*284.7*/("""
	"""),format.raw/*285.2*/("""}"""),format.raw/*285.3*/(""";



	let calculoAutomatico = "1";
	"""),_display_(if(mapPermiso.get("parametro.planes_calculaProximaMant") != null && mapPermiso.get("parametro.planes_calculaProximaMant").equals("0"))/*290.137*/ {_display_(Seq[Any](format.raw/*290.139*/("""
		"""),format.raw/*291.3*/("""calculoAutomatico = "0";
	""")))} else {null} ),format.raw/*292.3*/("""

	"""),format.raw/*294.2*/("""function determinaProxima(lectActual, frecuencia) """),format.raw/*294.52*/("""{"""),format.raw/*294.53*/("""
		"""),format.raw/*295.3*/("""if(calculoAutomatico == 1)"""),format.raw/*295.29*/("""{"""),format.raw/*295.30*/("""
			"""),format.raw/*296.4*/("""if (frecuencia <= 0) """),format.raw/*296.25*/("""{"""),format.raw/*296.26*/("""
				"""),format.raw/*297.5*/("""alert("la rotacion debe ser mayor a 0")
				return 0;
			"""),format.raw/*299.4*/("""}"""),format.raw/*299.5*/("""
			"""),format.raw/*300.4*/("""if (lectActual % frecuencia === 0) """),format.raw/*300.39*/("""{"""),format.raw/*300.40*/("""
				"""),format.raw/*301.5*/("""// Caso múltiplo exacto
				return lectActual + frecuencia;
			"""),format.raw/*303.4*/("""}"""),format.raw/*303.5*/(""" """),format.raw/*303.6*/("""else """),format.raw/*303.11*/("""{"""),format.raw/*303.12*/("""
				"""),format.raw/*304.5*/("""// Redondear al siguiente múltiplo
				const resto = lectActual % frecuencia;
				return lectActual - resto + frecuencia;
			"""),format.raw/*307.4*/("""}"""),format.raw/*307.5*/("""
		"""),format.raw/*308.3*/("""}"""),format.raw/*308.4*/("""else"""),format.raw/*308.8*/("""{"""),format.raw/*308.9*/("""
			"""),format.raw/*309.4*/("""if (frecuencia < 0)"""),format.raw/*309.23*/("""{"""),format.raw/*309.24*/("""
				"""),format.raw/*310.5*/("""alert("la rotacion debe ser mayor a 0");
			"""),format.raw/*311.4*/("""}"""),format.raw/*311.5*/("""else """),format.raw/*311.10*/("""{"""),format.raw/*311.11*/("""
				"""),format.raw/*312.5*/("""return lectActual + frecuencia;
			"""),format.raw/*313.4*/("""}"""),format.raw/*313.5*/("""
		"""),format.raw/*314.3*/("""}"""),format.raw/*314.4*/("""

	"""),format.raw/*316.2*/("""}"""),format.raw/*316.3*/("""

	"""),format.raw/*318.2*/("""function validaHoras(frecuencia, siguienteMantencion, mantencionAnterior, origen) """),format.raw/*318.84*/("""{"""),format.raw/*318.85*/("""
		"""),format.raw/*319.3*/("""if (!frecuencia || !siguienteMantencion) """),format.raw/*319.44*/("""{"""),format.raw/*319.45*/("""
			"""),format.raw/*320.4*/("""return false;
		"""),format.raw/*321.3*/("""}"""),format.raw/*321.4*/("""
		"""),format.raw/*322.3*/("""if(calculoAutomatico == 1)"""),format.raw/*322.29*/("""{"""),format.raw/*322.30*/("""
			"""),format.raw/*323.4*/("""const isValid = siguienteMantencion % frecuencia === 0;
			return isValid;
		"""),format.raw/*325.3*/("""}"""),format.raw/*325.4*/("""else"""),format.raw/*325.8*/("""{"""),format.raw/*325.9*/("""
			"""),format.raw/*326.4*/("""return true;
		"""),format.raw/*327.3*/("""}"""),format.raw/*327.4*/("""

	"""),format.raw/*329.2*/("""}"""),format.raw/*329.3*/("""

	"""),format.raw/*331.2*/("""let auxiliar = 0;

	function actualizarProximaMantencion(index, id_tipoPlan, id_equipo, origen) """),format.raw/*333.78*/("""{"""),format.raw/*333.79*/("""
	    """),format.raw/*334.6*/("""const lectActual = parseFloat($("#lectActual_" + index).val()?.replace(/,/g, "") || 0);
	    const frecuencia = parseFloat($(".cadaNEstimado")[index]?.value?.replace(/,/g, "") || 0);
	
	
	    if (frecuencia > 0) """),format.raw/*338.26*/("""{"""),format.raw/*338.27*/("""
			"""),_display_(if(mapDiccionario.get("nEmpresa").equals("GYL"))/*339.53*/{_display_(Seq[Any](format.raw/*339.54*/("""
				"""),format.raw/*340.5*/("""return("no aplica");
			""")))} else {null} ),format.raw/*341.5*/("""
	        """),format.raw/*342.10*/("""const proximaMantencion = determinaProxima(lectActual, frecuencia);
	        let mantencionAnterior = parseFloat($("#proximaMantencion_" + index).val()?.replace(/,/g, "") || 0);
	
	        const isValid = validaHoras(frecuencia, proximaMantencion, mantencionAnterior, origen);
	
	        if (isValid) """),format.raw/*347.23*/("""{"""),format.raw/*347.24*/("""
	            """),format.raw/*348.14*/("""$("#proximaMantencion_" + index).val(proximaMantencion);
	            actualizaPorCampo(id_tipoPlan, id_equipo, 'proximaMantencion', proximaMantencion);
				calcFechaProxima(index);
	
	        """),format.raw/*352.10*/("""}"""),format.raw/*352.11*/(""" """),format.raw/*352.12*/("""else """),format.raw/*352.17*/("""{"""),format.raw/*352.18*/("""
	            """),format.raw/*353.14*/("""alert("El valor a ingresar debe ser mayor que cero.");
	        """),format.raw/*354.10*/("""}"""),format.raw/*354.11*/("""
	    """),format.raw/*355.6*/("""}"""),format.raw/*355.7*/("""
	"""),format.raw/*356.2*/("""}"""),format.raw/*356.3*/("""
	
"""),format.raw/*358.1*/("""// FUNCION PARA RECALCULAR FECHA CUANDO horometro.planes_calculaProximaMant ES 0, ES DECIR LA PROXIMA MATENCION ES MANIPULABLE DE FORMA MANUAL


	function recalcularFechaEstimada(index, id_tipoPlan, id_equipo) """),format.raw/*361.66*/("""{"""),format.raw/*361.67*/("""
	    """),format.raw/*362.6*/("""// Leer los valores de la fila correspondiente
	    const proximaMantencion = parseFloat($("#proximaMantencion_" + index).val()?.replace(/,/g, "") || 0);
	    const fechaReset = $(".fechaReset")[index].value;
	    const consumoPromedio = parseFloat($(".consumoEstimadoPorMes")[index].value?.replace(/,/g, "") || 0);

	    // Validar valores
	    if (proximaMantencion <= 0 || consumoPromedio <= 0) """),format.raw/*368.58*/("""{"""),format.raw/*368.59*/("""
	        """),format.raw/*369.10*/("""alert("El valor ingresado no es válido.");
	        return;
	    """),format.raw/*371.6*/("""}"""),format.raw/*371.7*/("""
	
	    """),format.raw/*373.6*/("""// Calcular nueva fecha estimada
	    let diasASumar = (proximaMantencion / consumoPromedio) * 30;
	    let nuevaFecha;
	
	    try """),format.raw/*377.10*/("""{"""),format.raw/*377.11*/("""
	        """),format.raw/*378.10*/("""let aux = fechaReset.split("-");
	        nuevaFecha = new Date(aux[0], parseInt(aux[1]) - 1, aux[2]);
	        nuevaFecha.setDate(nuevaFecha.getDate() + diasASumar);
	    """),format.raw/*381.6*/("""}"""),format.raw/*381.7*/(""" """),format.raw/*381.8*/("""catch (error) """),format.raw/*381.22*/("""{"""),format.raw/*381.23*/("""
	        """),format.raw/*382.10*/("""nuevaFecha = new Date(); // Fecha actual si hay errores
	    """),format.raw/*383.6*/("""}"""),format.raw/*383.7*/("""
	
	    """),format.raw/*385.6*/("""// Actualizar la fecha estimada en la interfaz
	    $(".fechaEstimada")[index].value = fechaAAMMDD(nuevaFecha);
	
	    // Guardar el cambio en la base de datos
	    actualizaPorCampo(id_tipoPlan, id_equipo, "proximaMantencion", proximaMantencion);
	"""),format.raw/*390.2*/("""}"""),format.raw/*390.3*/("""




"""),format.raw/*395.1*/("""</script>
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,allPlan:List[tables.PlanMantencion],listAtributos:List[String],listCompra:List[String]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,allPlan,listAtributos,listCompra)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.PlanMantencion],List[String],List[String]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,allPlan,listAtributos,listCompra) => apply(mapDiccionario,mapPermiso,userMnu,allPlan,listAtributos,listCompra)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuPlanes/hojaVidaMantencionPlan.scala.html
                  HASH: 5714142303a376b81f30f48f500392afe225ab4a
                  MATRIX: 1074->1|1357->191|1392->201|1408->209|1447->211|1475->214|1543->262|1572->264|1649->315|1740->385|1770->388|2208->799|2224->806|2264->825|2308->842|2331->856|2363->867|2413->890|2432->900|2460->907|2526->946|2545->956|2573->963|2640->1003|2659->1013|2687->1020|2782->1088|2798->1095|2839->1115|2909->1158|2928->1168|2956->1175|3054->1246|3093->1247|3129->1256|3190->1290|3209->1300|3237->1307|3344->1395|3383->1396|3418->1404|3460->1416|3494->1423|3561->1463|3580->1473|3608->1480|3703->1548|3719->1555|3760->1575|3828->1616|3847->1626|3875->1633|4084->1816|4127->1843|4166->1844|4204->1855|4230->1860|4272->1871|4306->1878|5211->2757|5270->2800|5310->2802|5345->2810|5474->2912|5488->2917|5521->2929|5561->2942|5575->2947|5611->2962|5647->2971|5838->3133|5868->3134|5911->3148|5949->3158|5968->3167|6022->3199|6066->3214|6096->3215|6126->3216|6160->3221|6190->3222|6233->3236|6276->3251|6303->3256|6336->3261|6351->3266|6383->3276|6449->3313|6479->3314|6527->3334|6546->3343|6600->3375|6751->3498|6766->3503|6808->3523|6849->3536|6864->3541|6903->3558|6940->3567|7138->3737|7165->3742|7213->3762|7228->3767|7263->3780|7518->4007|7545->4012|7578->4017|7593->4022|7625->4032|7796->4175|7811->4180|7847->4194|7888->4207|7903->4212|7939->4226|7976->4235|8117->4348|8132->4353|8176->4375|8217->4388|8232->4393|8276->4415|8313->4424|8492->4574|8534->4576|8573->4586|8781->4766|8808->4771|8860->4795|8875->4800|8915->4818|9172->5047|9199->5052|9232->5057|9247->5062|9282->5074|9316->5079|9332->5084|9365->5094|9419->5130|9459->5131|9498->5141|9710->5325|9737->5330|9789->5354|9804->5359|9844->5377|9926->5427|9964->5437|10140->5582|10174->5588|11542->6925|11575->6930|11666->6992|11696->6993|11727->6996|11843->7084|11872->7085|11938->7122|11968->7123|12002->7129|12136->7234|12166->7235|12205->7245|12652->7663|12682->7664|12725->7678|12812->7736|12842->7737|12872->7738|12906->7743|12936->7744|12979->7758|13094->7844|13124->7845|13171->7863|13385->8048|13415->8049|13445->8050|13488->8064|13518->8065|13565->8083|13623->8112|13653->8113|13698->8129|13790->8192|13820->8193|13854->8199|13883->8200|13913->8202|13942->8203|13975->8208|14038->8242|14068->8243|14101->8248|14311->8430|14340->8431|14372->8435|14468->8502|14498->8503|14529->8506|14764->8712|14794->8713|14836->8726|15107->8968|15137->8969|15170->8974|15225->9001|15254->9002|15291->9011|15321->9012|15353->9016|15382->9017|15413->9020|15499->9077|15529->9078|15563->9084|15738->9230|15768->9231|15807->9241|15895->9300|15925->9301|15968->9315|16027->9345|16057->9346|16105->9365|16370->9601|16400->9602|16433->9607|16490->9635|16520->9636|16554->9642|16781->9841|16810->9842|16849->9852|16879->9853|16911->9857|16999->9916|17029->9917|17062->9922|17091->9923|17125->9929|17154->9930|17184->9932|17213->9933|17413->10104|17455->10106|17486->10109|17557->10136|17588->10139|17667->10189|17697->10190|17728->10193|17783->10219|17813->10220|17845->10224|17895->10245|17925->10246|17958->10251|18043->10308|18072->10309|18104->10313|18168->10348|18198->10349|18231->10354|18322->10417|18351->10418|18380->10419|18414->10424|18444->10425|18477->10430|18630->10555|18659->10556|18690->10559|18719->10560|18751->10564|18780->10565|18812->10569|18860->10588|18890->10589|18923->10594|18995->10638|19024->10639|19058->10644|19088->10645|19121->10650|19184->10685|19213->10686|19244->10689|19273->10690|19304->10693|19333->10694|19364->10697|19475->10779|19505->10780|19536->10783|19606->10824|19636->10825|19668->10829|19712->10845|19741->10846|19772->10849|19827->10875|19857->10876|19889->10880|19994->10957|20023->10958|20055->10962|20084->10963|20116->10967|20159->10982|20188->10983|20219->10986|20248->10987|20279->10990|20404->11086|20434->11087|20468->11093|20709->11305|20739->11306|20820->11359|20860->11360|20893->11365|20962->11390|21001->11400|21331->11701|21361->11702|21404->11716|21626->11909|21656->11910|21686->11911|21720->11916|21750->11917|21793->11931|21886->11995|21916->11996|21950->12002|21979->12003|22009->12005|22038->12006|22069->12009|22308->12219|22338->12220|22372->12226|22799->12624|22829->12625|22868->12635|22961->12700|22990->12701|23026->12709|23186->12840|23216->12841|23255->12851|23455->13023|23484->13024|23513->13025|23556->13039|23586->13040|23625->13050|23714->13111|23743->13112|23779->13120|24056->13369|24085->13370|24118->13375
                  LINES: 28->1|34->3|38->7|38->7|38->7|40->9|40->9|41->10|42->11|42->11|43->12|57->26|57->26|57->26|58->27|58->27|58->27|59->28|59->28|59->28|61->30|61->30|61->30|63->32|63->32|63->32|67->36|67->36|67->36|69->38|69->38|69->38|72->41|72->41|73->42|73->42|73->42|73->42|76->45|76->45|77->46|78->47|79->48|81->50|81->50|81->50|85->54|85->54|85->54|87->56|87->56|87->56|98->67|98->67|98->67|99->68|99->68|100->69|101->70|123->92|123->92|123->92|124->93|126->95|126->95|126->95|127->96|127->96|127->96|128->97|132->101|132->101|133->102|133->102|133->102|133->102|134->103|134->103|134->103|134->103|134->103|135->104|135->104|135->104|135->104|135->104|135->104|136->105|136->105|137->106|137->106|137->106|141->110|141->110|141->110|142->111|142->111|142->111|143->112|147->116|147->116|148->117|148->117|148->117|153->122|153->122|153->122|153->122|153->122|157->126|157->126|157->126|158->127|158->127|158->127|159->128|161->130|161->130|161->130|162->131|162->131|162->131|163->132|165->134|165->134|166->135|169->138|169->138|170->139|170->139|170->139|174->143|174->143|174->143|174->143|174->143|174->143|174->143|174->143|176->145|176->145|177->146|180->149|180->149|181->150|181->150|181->150|184->153|186->155|190->159|191->160|228->197|233->202|234->203|234->203|235->204|237->206|237->206|239->208|239->208|240->209|243->212|243->212|244->213|251->220|251->220|252->221|253->222|253->222|253->222|253->222|253->222|254->223|257->226|257->226|258->227|261->230|261->230|261->230|261->230|261->230|262->231|263->232|263->232|265->234|266->235|266->235|267->236|267->236|268->237|268->237|271->240|271->240|271->240|272->241|274->243|274->243|276->245|276->245|276->245|277->246|282->251|282->251|283->252|290->259|290->259|291->260|292->261|292->261|293->262|293->262|294->263|294->263|296->265|296->265|296->265|297->266|299->268|299->268|300->269|300->269|300->269|301->270|301->270|301->270|302->271|305->274|305->274|306->275|306->275|306->275|307->276|310->279|310->279|311->280|311->280|312->281|312->281|312->281|314->283|314->283|315->284|315->284|316->285|316->285|321->290|321->290|322->291|323->292|325->294|325->294|325->294|326->295|326->295|326->295|327->296|327->296|327->296|328->297|330->299|330->299|331->300|331->300|331->300|332->301|334->303|334->303|334->303|334->303|334->303|335->304|338->307|338->307|339->308|339->308|339->308|339->308|340->309|340->309|340->309|341->310|342->311|342->311|342->311|342->311|343->312|344->313|344->313|345->314|345->314|347->316|347->316|349->318|349->318|349->318|350->319|350->319|350->319|351->320|352->321|352->321|353->322|353->322|353->322|354->323|356->325|356->325|356->325|356->325|357->326|358->327|358->327|360->329|360->329|362->331|364->333|364->333|365->334|369->338|369->338|370->339|370->339|371->340|372->341|373->342|378->347|378->347|379->348|383->352|383->352|383->352|383->352|383->352|384->353|385->354|385->354|386->355|386->355|387->356|387->356|389->358|392->361|392->361|393->362|399->368|399->368|400->369|402->371|402->371|404->373|408->377|408->377|409->378|412->381|412->381|412->381|412->381|412->381|413->382|414->383|414->383|416->385|421->390|421->390|426->395
                  -- GENERATED --
              */
          