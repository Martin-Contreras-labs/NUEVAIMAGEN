
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

object hojaVidaPlanModifica extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template8[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.PlanMantencion],List[String],List[String],List[tables.UnidadMantencion],List[tables.TipoPlan],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
allPlan: List[tables.PlanMantencion], listAtributos: List[String], listCompra: List[String], listUnidad: List[tables.UnidadMantencion], listTipo: List[tables.TipoPlan]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""


    	
"""),_display_(/*7.2*/main("")/*7.10*/ {_display_(Seq[Any](format.raw/*7.12*/("""

"""),_display_(/*9.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*9.50*/("""
	"""),format.raw/*10.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*11.4*/barraTitulo(mapDiccionario, "PROGRAMA PLAN DE MANTENCION","MODIFICAR/AGREGAR")),format.raw/*11.82*/("""
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
					"""),_display_(/*89.7*/for((lista, index) <- allPlan.zipWithIndex) yield /*89.50*/ {_display_(Seq[Any](format.raw/*89.52*/("""
						  """),format.raw/*90.9*/("""<tr>
						    <!-- Tipo de Plan -->
						    <td style="text-align:center">
						      <select class="id_tipoPlan custom-select center"
						        id="tipoPlan_"""),_display_(/*94.29*/index),format.raw/*94.34*/(""""
						        onchange="actualizaPorCampo('"""),_display_(/*95.45*/lista/*95.50*/.id_tipoPlan),format.raw/*95.62*/("""', '"""),_display_(/*95.67*/lista/*95.72*/.id_equipo),format.raw/*95.82*/("""', 'id_tipoPlan', value);">
						        <option value=""""),_display_(/*96.31*/lista/*96.36*/.id_tipoPlan),format.raw/*96.48*/("""">"""),_display_(/*96.51*/lista/*96.56*/.tipoPlanNombre),format.raw/*96.71*/("""</option>
						        """),_display_(/*97.16*/for(opcion <- listTipo) yield /*97.39*/ {_display_(Seq[Any](format.raw/*97.41*/("""
						          """),format.raw/*98.17*/("""<option value=""""),_display_(/*98.33*/opcion/*98.39*/.id),format.raw/*98.42*/("""">"""),_display_(/*98.45*/opcion/*98.51*/.nombre),format.raw/*98.58*/("""</option>
						        """)))}),format.raw/*99.16*/("""
						      """),format.raw/*100.13*/("""</select>
						    </td>
						    
						    <!-- Fecha Reset -->
						    <td style="text-align:center">
						      <input type="date" class="fechaReset form-control center"
						        id="fechaReset_"""),_display_(/*106.31*/index),format.raw/*106.36*/(""""
						        onblur="if(!limitaFecha(value, 720, 10)) """),format.raw/*107.56*/("""{"""),format.raw/*107.57*/("""
						          """),format.raw/*108.17*/("""value = '"""),_display_(/*108.27*/utilities/*108.36*/.Fechas.AAMMDD(lista.fechaReset)),format.raw/*108.68*/("""';
						        """),format.raw/*109.15*/("""}"""),format.raw/*109.16*/(""" """),format.raw/*109.17*/("""else """),format.raw/*109.22*/("""{"""),format.raw/*109.23*/("""
						          """),format.raw/*110.17*/("""actualizaAll('"""),_display_(/*110.32*/index),format.raw/*110.37*/("""', '"""),_display_(/*110.42*/lista/*110.47*/.id_equipo),format.raw/*110.57*/("""', 'fechaReset', value);
						        """),format.raw/*111.15*/("""}"""),format.raw/*111.16*/(""""
						        value=""""),_display_(/*112.23*/utilities/*112.32*/.Fechas.AAMMDD(lista.fechaReset)),format.raw/*112.64*/("""">
						    </td>
						    
						    <!-- Unidad de Mantención -->
						    <td style="text-align:center">
						      <select class="id_unidadMantencion custom-select center"
						        id="unidadMantencion_"""),_display_(/*118.37*/index),format.raw/*118.42*/(""""
						        onchange="actualizaPorCampo('"""),_display_(/*119.45*/lista/*119.50*/.id_tipoPlan),format.raw/*119.62*/("""', '"""),_display_(/*119.67*/lista/*119.72*/.id_equipo),format.raw/*119.82*/("""', 'id_unidadMantencion', value);">
						        <option value=""""),_display_(/*120.31*/lista/*120.36*/.id_unidadMantencion),format.raw/*120.56*/("""">"""),_display_(/*120.59*/lista/*120.64*/.unidadMantencion),format.raw/*120.81*/("""</option>
						        """),_display_(/*121.16*/for(opcion <- listUnidad) yield /*121.41*/ {_display_(Seq[Any](format.raw/*121.43*/("""
						          """),format.raw/*122.17*/("""<option value=""""),_display_(/*122.33*/opcion/*122.39*/.id),format.raw/*122.42*/("""">"""),_display_(/*122.45*/opcion/*122.51*/.nombre),format.raw/*122.58*/("""</option>
						        """)))}),format.raw/*123.16*/("""
						      """),format.raw/*124.13*/("""</select>
						    </td>
						    
						    <!-- Estado Actual -->
						    <td style="text-align:right">
						      <input type="text" class="estadoActual form-control center"
						        id="lectActual_"""),_display_(/*130.31*/index),format.raw/*130.36*/(""""
						        value=""""),_display_(/*131.23*/lista/*131.28*/.estadoActual),format.raw/*131.41*/(""""
						        onfocus="value = value.replace(/,/g, ''); auxiliar = value;"
						        onkeydown="return ingresoDouble(event, value)"
						        autocomplete="off"
						        onchange="if(value == '') value = 0; 
						          actualizaAll('"""),_display_(/*136.32*/index),format.raw/*136.37*/("""', '"""),_display_(/*136.42*/lista/*136.47*/.id_equipo),format.raw/*136.57*/("""', 'estadoActual', value); 
						          actualizarProximaMantencion('"""),_display_(/*137.47*/index),format.raw/*137.52*/("""', '"""),_display_(/*137.57*/lista/*137.62*/.id_tipoPlan),format.raw/*137.74*/("""', '"""),_display_(/*137.79*/lista/*137.84*/.id_equipo),format.raw/*137.94*/("""','lectura');">
						    </td>
						    
						    <!-- Rotación/Frecuencia -->
						    <td style="text-align:right">
						      <input type="text" class="cadaNEstimado form-control center"
						        id="frecuencia_"""),_display_(/*143.31*/index),format.raw/*143.36*/(""""
						        value=""""),_display_(/*144.23*/lista/*144.28*/.cadaNEstimado),format.raw/*144.42*/(""""
						        onfocus="value = value.replace(/,/g, ''); auxiliar = value;"
						        onkeydown="return ingresoDouble(event, value)"
						        autocomplete="off"
						        onchange="if(value == '') value = 0; 
						          actualizaPorCampo('"""),_display_(/*149.37*/lista/*149.42*/.id_tipoPlan),format.raw/*149.54*/("""', '"""),_display_(/*149.59*/lista/*149.64*/.id_equipo),format.raw/*149.74*/("""', 'cadaNEstimado', value);
						          actualizarProximaMantencion('"""),_display_(/*150.47*/index),format.raw/*150.52*/("""', '"""),_display_(/*150.57*/lista/*150.62*/.id_tipoPlan),format.raw/*150.74*/("""', '"""),_display_(/*150.79*/lista/*150.84*/.id_equipo),format.raw/*150.94*/("""','frecuencia');">
						    </td>
						    
						    <!-- Consumo Estimado -->
						    <td style="text-align:right">
						      <input type="text" class="consumoEstimadoPorMes form-control center"
						        id="consumoEstimado_"""),_display_(/*156.36*/index),format.raw/*156.41*/(""""
						        value=""""),_display_(/*157.23*/lista/*157.28*/.consumoEstimadoPorMes),format.raw/*157.50*/(""""
						        onfocus="value = value.replace(/,/g, '');"
						        onkeydown="return ingresoDouble(event, value)"
						        autocomplete="off"
						        onchange="if(value == '') value = 0; 
						          actualizaPorCampo('"""),_display_(/*162.37*/lista/*162.42*/.id_tipoPlan),format.raw/*162.54*/("""', '"""),_display_(/*162.59*/lista/*162.64*/.id_equipo),format.raw/*162.74*/("""', 'consumoEstimadoPorMes', value);">
						    </td>
						    
						    <!-- Próxima Mantención -->
							"""),_display_(if(mapPermiso.get("parametro.planes_calculaProximaMant") != null && mapPermiso.get("parametro.planes_calculaProximaMant").equals("0"))/*166.143*/ {_display_(Seq[Any](format.raw/*166.145*/("""
							    """),format.raw/*167.12*/("""<!-- Modo edición manual -->
							    <td style="text-align:right">
							        <input type="text" class="proximaMantencion form-control center"
							               id="proximaMantencion_"""),_display_(/*170.46*/index),format.raw/*170.51*/(""""
							               value=""""),_display_(/*171.31*/lista/*171.36*/.proximaMantencion),format.raw/*171.54*/(""""
							               onfocus="value = value.replace(/,/g,'');"
							               onkeydown="return ingresoDouble(event, value)"
							               autocomplete="off"
							               onchange="if(value == '') value = 0; recalcularFechaEstimada('"""),_display_(/*175.86*/index),format.raw/*175.91*/("""', '"""),_display_(/*175.96*/lista/*175.101*/.id_tipoPlan),format.raw/*175.113*/("""', '"""),_display_(/*175.118*/lista/*175.123*/.id_equipo),format.raw/*175.133*/("""');">
							    </td>
							""")))}else/*177.15*/{_display_(Seq[Any](format.raw/*177.16*/("""
							    """),format.raw/*178.12*/("""<!-- Modo cálculo automático -->
							    <td style="text-align:right">
							        <input type="text" class="proximaMantencion form-control center"
							               id="proximaMantencion_"""),_display_(/*181.46*/index),format.raw/*181.51*/(""""
							               value=""""),_display_(/*182.31*/lista/*182.36*/.proximaMantencion),format.raw/*182.54*/(""""
							               readonly>
							    </td>
							""")))}),format.raw/*185.9*/("""

							"""),format.raw/*187.8*/("""<td style="text-align:center">
								<input type="date" class="fechaEstimada form-control center" disabled>
							</td>
						  </tr>
					""")))}),format.raw/*191.7*/("""
										
					"""),_display_(if(listTipo.size()>0)/*193.28*/{_display_(Seq[Any](format.raw/*193.29*/("""
						"""),format.raw/*194.7*/("""<tr>
							<td style="text-align:center">
								<form id="formAgrega" method="post" action="/hojaVidaAgregaPlan/">
									<input type="hidden" name="id_equipo" value=""""),_display_(/*197.56*/allPlan/*197.63*/.get(0).id_equipo),format.raw/*197.80*/("""">
									<select class="custom-select center"
										name="id_tipoPlan"
										onchange='document.getElementById("formAgrega").submit()'>
										<option value="0"></option>
										"""),_display_(/*202.12*/for(lista <- listTipo) yield /*202.34*/{_display_(Seq[Any](format.raw/*202.35*/("""
									     	"""),format.raw/*203.16*/("""<option value=""""),_display_(/*203.32*/lista/*203.37*/.id),format.raw/*203.40*/("""">"""),_display_(/*203.43*/lista/*203.48*/.nombre),format.raw/*203.55*/("""</option>
										""")))}),format.raw/*204.12*/("""
									"""),format.raw/*205.10*/("""</select>
								</form>
							</td>
							<td style="text-align:center"></td>
							<td style="text-align:center"></td>
							<td style="text-align:right"></td>
							<td style="text-align:right"></td>
							<td style="text-align:right"></td>
							<td style="text-align:right"></td>
						</tr>
					""")))} else {null} ),format.raw/*215.7*/("""
				"""),format.raw/*216.5*/("""</table>
			</div>
		</div>	
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="VOLVER" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/hojaVidaPlanMantencion/';">
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
	
""")))}),format.raw/*252.2*/("""




"""),format.raw/*257.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*258.31*/("""{"""),format.raw/*258.32*/("""
		"""),format.raw/*259.3*/("""calcFechaProxima();
		document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*261.2*/("""}"""),format.raw/*261.3*/(""");
	
	const calcFechaProxima = () => """),format.raw/*263.33*/("""{"""),format.raw/*263.34*/("""
	    """),format.raw/*264.6*/("""let filas = $(".estadoActual").length;
	    let hoy = new Date();
	
	    for (let i = 0; i < filas; i++) """),format.raw/*267.38*/("""{"""),format.raw/*267.39*/("""
	        """),format.raw/*268.10*/("""let fecha = $(".fechaReset")[i].value; // Fecha de referencia
	        let proxima = parseFloat($(".proximaMantencion")[i].value.replace(/,/g, '') || 0);
	        let actual = parseFloat($(".estadoActual")[i].value.replace(/,/g, '') || 0);
	        let rotacion = parseFloat($(".consumoEstimadoPorMes")[i].value.replace(/,/g, '') || 0);
	
	        let dif = proxima - actual;
	
	        if (dif <= 0 || rotacion <= 0) """),format.raw/*275.41*/("""{"""),format.raw/*275.42*/("""
	            """),format.raw/*276.14*/("""$(".fechaEstimada")[i].value = fechaAAMMDD(hoy);
	        """),format.raw/*277.10*/("""}"""),format.raw/*277.11*/(""" """),format.raw/*277.12*/("""else """),format.raw/*277.17*/("""{"""),format.raw/*277.18*/("""
	            """),format.raw/*278.14*/("""let diasASumar = (dif / rotacion) * 30;
	
	            let auxFecha;
	            try """),format.raw/*281.18*/("""{"""),format.raw/*281.19*/("""
	                """),format.raw/*282.18*/("""let aux = fecha.split("-");
	                auxFecha = new Date(aux[0], parseInt(aux[1]) - 1, aux[2]);
	                auxFecha.setDate(auxFecha.getDate() + diasASumar);
	            """),format.raw/*285.14*/("""}"""),format.raw/*285.15*/(""" """),format.raw/*285.16*/("""catch (error) """),format.raw/*285.30*/("""{"""),format.raw/*285.31*/("""
	                """),format.raw/*286.18*/("""auxFecha = hoy;
	            """),format.raw/*287.14*/("""}"""),format.raw/*287.15*/("""
	
	            """),format.raw/*289.14*/("""$(".fechaEstimada")[i].value = fechaAAMMDD(auxFecha);
	        """),format.raw/*290.10*/("""}"""),format.raw/*290.11*/("""
	    """),format.raw/*291.6*/("""}"""),format.raw/*291.7*/("""
	"""),format.raw/*292.2*/("""}"""),format.raw/*292.3*/("""

	
	"""),format.raw/*295.2*/("""const mostrarPDF = (nombrePDF) => """),format.raw/*295.36*/("""{"""),format.raw/*295.37*/("""
		  """),format.raw/*296.5*/("""document.getElementById('rutaPDF').innerHTML="<object data='/showPdf/"+nombrePDF+"' type='application/pdf' width='100%' height='720'></object>";
		  $('#muestraPDF').modal('show');
	"""),format.raw/*298.2*/("""}"""),format.raw/*298.3*/("""
	
	"""),format.raw/*300.2*/("""const actualizaPorCampo = (id_tipoPlan, id_equipo, campo, valor) =>"""),format.raw/*300.69*/("""{"""),format.raw/*300.70*/("""
		"""),format.raw/*301.3*/("""var formData = new FormData();
	  	formData.append('id_tipoPlan',id_tipoPlan);
		formData.append('id_equipo',id_equipo);
		formData.append('campo',campo);
	  	formData.append('valor',valor);
        $.ajax("""),format.raw/*306.16*/("""{"""),format.raw/*306.17*/("""
            """),format.raw/*307.13*/("""url: "/actualCampoPlanMantencionAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*314.36*/("""{"""),format.raw/*314.37*/("""
				"""),format.raw/*315.5*/("""calcFechaProxima();
	     	"""),format.raw/*316.8*/("""}"""),format.raw/*316.9*/("""
        """),format.raw/*317.9*/("""}"""),format.raw/*317.10*/(""");
	"""),format.raw/*318.2*/("""}"""),format.raw/*318.3*/("""

	"""),format.raw/*320.2*/("""const actualizaAll = (index, id_equipo, campo, valor) => """),format.raw/*320.59*/("""{"""),format.raw/*320.60*/("""
	    """),format.raw/*321.6*/("""let filas = $(".estadoActual").length;
		let unidCambiada = $("#unidadMantencion_"+index).val();
	
	    for (let i = 0; i < filas; i++) """),format.raw/*324.38*/("""{"""),format.raw/*324.39*/("""	
	        """),format.raw/*325.10*/("""if ($(".id_unidadMantencion")[i].value == unidCambiada) """),format.raw/*325.66*/("""{"""),format.raw/*325.67*/("""
	            """),format.raw/*326.14*/("""if (campo === 'fechaReset') """),format.raw/*326.42*/("""{"""),format.raw/*326.43*/("""
	                """),format.raw/*327.18*/("""$(".fechaReset")[i].value = valor;
	                actualizaPorCampo($(".id_tipoPlan")[i].value, id_equipo, campo, valor);
	                actualizarProximaMantencion(i, $(".id_tipoPlan")[i].value, id_equipo, campo);
	            """),format.raw/*330.14*/("""}"""),format.raw/*330.15*/("""
	
	            """),format.raw/*332.14*/("""if (campo === 'estadoActual') """),format.raw/*332.44*/("""{"""),format.raw/*332.45*/("""
	                """),format.raw/*333.18*/("""$(".estadoActual")[i].value = valor;
	                actualizaPorCampo($(".id_tipoPlan")[i].value, id_equipo, campo, valor);
	                actualizarProximaMantencion(i, $(".id_tipoPlan")[i].value, id_equipo, campo);
	            """),format.raw/*336.14*/("""}"""),format.raw/*336.15*/("""
	        """),format.raw/*337.10*/("""}"""),format.raw/*337.11*/("""
	    """),format.raw/*338.6*/("""}"""),format.raw/*338.7*/("""
	"""),format.raw/*339.2*/("""}"""),format.raw/*339.3*/(""";

	let calculoAutomatico = "1";
	"""),_display_(if(mapPermiso.get("parametro.planes_calculaProximaMant") != null && mapPermiso.get("parametro.planes_calculaProximaMant").equals("0"))/*342.137*/ {_display_(Seq[Any](format.raw/*342.139*/("""
		"""),format.raw/*343.3*/("""calculoAutomatico = "0";
	""")))} else {null} ),format.raw/*344.3*/("""
	  """),format.raw/*345.4*/("""function determinaProxima(lectActual, frecuencia) """),format.raw/*345.54*/("""{"""),format.raw/*345.55*/("""
		"""),format.raw/*346.3*/("""if(calculoAutomatico == 1)"""),format.raw/*346.29*/("""{"""),format.raw/*346.30*/("""
			"""),format.raw/*347.4*/("""if (frecuencia < 0)"""),format.raw/*347.23*/("""{"""),format.raw/*347.24*/("""
				"""),format.raw/*348.5*/("""alert("la rotacion debe ser mayor a 0");
			"""),format.raw/*349.4*/("""}"""),format.raw/*349.5*/("""else if (lectActual % frecuencia === 0) """),format.raw/*349.45*/("""{"""),format.raw/*349.46*/("""
				"""),format.raw/*350.5*/("""return lectActual + frecuencia;
			"""),format.raw/*351.4*/("""}"""),format.raw/*351.5*/(""" """),format.raw/*351.6*/("""else """),format.raw/*351.11*/("""{"""),format.raw/*351.12*/("""
				"""),format.raw/*352.5*/("""// Redondear al siguiente múltiplo
				const resto = lectActual % frecuencia;
				return lectActual - resto + frecuencia;
			"""),format.raw/*355.4*/("""}"""),format.raw/*355.5*/("""
		"""),format.raw/*356.3*/("""}"""),format.raw/*356.4*/("""else"""),format.raw/*356.8*/("""{"""),format.raw/*356.9*/("""
			"""),format.raw/*357.4*/("""if (frecuencia < 0)"""),format.raw/*357.23*/("""{"""),format.raw/*357.24*/("""
				"""),format.raw/*358.5*/("""alert("la rotacion debe ser mayor a 0");
			"""),format.raw/*359.4*/("""}"""),format.raw/*359.5*/("""else """),format.raw/*359.10*/("""{"""),format.raw/*359.11*/("""
				"""),format.raw/*360.5*/("""return lectActual + frecuencia;
			"""),format.raw/*361.4*/("""}"""),format.raw/*361.5*/("""
		"""),format.raw/*362.3*/("""}"""),format.raw/*362.4*/("""

	"""),format.raw/*364.2*/("""}"""),format.raw/*364.3*/("""

	"""),format.raw/*366.2*/("""function validaHoras(frecuencia, siguienteMantencion, mantencionAnterior, origen) """),format.raw/*366.84*/("""{"""),format.raw/*366.85*/("""
		"""),format.raw/*367.3*/("""if (!frecuencia || !siguienteMantencion) """),format.raw/*367.44*/("""{"""),format.raw/*367.45*/("""
			"""),format.raw/*368.4*/("""return false;
		"""),format.raw/*369.3*/("""}"""),format.raw/*369.4*/("""
		"""),format.raw/*370.3*/("""if(calculoAutomatico == 1)"""),format.raw/*370.29*/("""{"""),format.raw/*370.30*/("""
			"""),format.raw/*371.4*/("""const isValid = siguienteMantencion % frecuencia === 0;
			return isValid;
		"""),format.raw/*373.3*/("""}"""),format.raw/*373.4*/("""else"""),format.raw/*373.8*/("""{"""),format.raw/*373.9*/("""
			"""),format.raw/*374.4*/("""return true;
		"""),format.raw/*375.3*/("""}"""),format.raw/*375.4*/("""

	"""),format.raw/*377.2*/("""}"""),format.raw/*377.3*/("""

	"""),format.raw/*379.2*/("""let auxiliar = 0;


function actualizarProximaMantencion(index, id_tipoPlan, id_equipo, origen) """),format.raw/*382.77*/("""{"""),format.raw/*382.78*/("""

    """),format.raw/*384.5*/("""const lectActual = parseFloat($("#lectActual_" + index).val()?.replace(/,/g, "") || 0);

    const frecuencia = parseFloat($("#frecuencia_" + index).val()?.replace(/,/g, "") || 0);

    if (frecuencia > 0) """),format.raw/*388.25*/("""{"""),format.raw/*388.26*/("""
        """),format.raw/*389.9*/("""const proximaMantencion = determinaProxima(lectActual, frecuencia);
		"""),_display_(if(mapDiccionario.get("nEmpresa").equals("GYL"))/*390.52*/{_display_(Seq[Any](format.raw/*390.53*/("""
			"""),format.raw/*391.4*/("""return("no aplica");
		""")))} else {null} ),format.raw/*392.4*/("""
        """),format.raw/*393.9*/("""let mantencionAnterior = parseFloat($("#proximaMantencion_" + index).val()?.replace(/,/g, "") || 0);

        const isValid = validaHoras(frecuencia, proximaMantencion, mantencionAnterior, origen);

        if (isValid) """),format.raw/*397.22*/("""{"""),format.raw/*397.23*/("""
            """),format.raw/*398.13*/("""$("#proximaMantencion_" + index).val(proximaMantencion);
            actualizaPorCampo(id_tipoPlan, id_equipo, 'proximaMantencion', proximaMantencion);
            auxiliar = 0; // Limpieza
        """),format.raw/*401.9*/("""}"""),format.raw/*401.10*/(""" """),format.raw/*401.11*/("""else """),format.raw/*401.16*/("""{"""),format.raw/*401.17*/("""
            """),format.raw/*402.13*/("""alert("El valor a ingresar debe ser mayor que cero.");
            $("#proximaMantencion_" + index).val(0);

            if (origen === 'lectura') $("#lectActual_" + index).val(auxiliar);
            if (origen === 'frecuencia') $("#frecuencia_" + index).val(auxiliar);

            auxiliar = 0; // Limpieza

        """),format.raw/*410.9*/("""}"""),format.raw/*410.10*/("""
    """),format.raw/*411.5*/("""}"""),format.raw/*411.6*/("""

"""),format.raw/*413.1*/("""}"""),format.raw/*413.2*/("""

"""),format.raw/*415.1*/("""// FUNCION PARA RECALCULAR FECHA CUANDO parametro.planes_calculaProximaMant ES 0, ES DECIR LA PROXIMA MATENCION ES MANIPULABLE DE FORMA MANUAL


	function recalcularFechaEstimada(index, id_tipoPlan, id_equipo) """),format.raw/*418.66*/("""{"""),format.raw/*418.67*/("""
	    """),format.raw/*419.6*/("""// Leer los valores de la fila correspondiente
	    const proximaMantencion = parseFloat($("#proximaMantencion_" + index).val()?.replace(/,/g, "") || 0);
	    const fechaReset = $(".fechaReset")[index].value;
	    const consumoPromedio = parseFloat($(".consumoEstimadoPorMes")[index].value?.replace(/,/g, "") || 0);

	    // Validar valores
	    if (proximaMantencion <= 0 || consumoPromedio <= 0) """),format.raw/*425.58*/("""{"""),format.raw/*425.59*/("""
	        """),format.raw/*426.10*/("""alert("El valor ingresado y el consumo promedio deben ser mayor que cero.");
	        return;
	    """),format.raw/*428.6*/("""}"""),format.raw/*428.7*/("""
	
	    """),format.raw/*430.6*/("""// Calcular nueva fecha estimada
	    let diasASumar = (proximaMantencion / consumoPromedio) * 30;
	    let nuevaFecha;
	
	    try """),format.raw/*434.10*/("""{"""),format.raw/*434.11*/("""
	        """),format.raw/*435.10*/("""let aux = fechaReset.split("-");
	        nuevaFecha = new Date(aux[0], parseInt(aux[1]) - 1, aux[2]);
	        nuevaFecha.setDate(nuevaFecha.getDate() + diasASumar);
	    """),format.raw/*438.6*/("""}"""),format.raw/*438.7*/(""" """),format.raw/*438.8*/("""catch (error) """),format.raw/*438.22*/("""{"""),format.raw/*438.23*/("""
	        """),format.raw/*439.10*/("""nuevaFecha = new Date(); // Fecha actual si hay errores
	    """),format.raw/*440.6*/("""}"""),format.raw/*440.7*/("""
	
	    """),format.raw/*442.6*/("""// Actualizar la fecha estimada en la interfaz
	    $(".fechaEstimada")[index].value = fechaAAMMDD(nuevaFecha);
	
	    // Guardar el cambio en la base de datos
	    actualizaPorCampo(id_tipoPlan, id_equipo, "proximaMantencion", proximaMantencion);
	"""),format.raw/*447.2*/("""}"""),format.raw/*447.3*/("""



	
		
	
	





	           


	
"""),format.raw/*464.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,allPlan:List[tables.PlanMantencion],listAtributos:List[String],listCompra:List[String],listUnidad:List[tables.UnidadMantencion],listTipo:List[tables.TipoPlan]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,allPlan,listAtributos,listCompra,listUnidad,listTipo)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.PlanMantencion],List[String],List[String],List[tables.UnidadMantencion],List[tables.TipoPlan]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,allPlan,listAtributos,listCompra,listUnidad,listTipo) => apply(mapDiccionario,mapPermiso,userMnu,allPlan,listAtributos,listCompra,listUnidad,listTipo)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuPlanes/hojaVidaPlanModifica.scala.html
                  HASH: 4185b06ae36df0e6954f401605f6e5e7ba05bbd4
                  MATRIX: 1124->1|1483->267|1518->277|1534->285|1573->287|1601->290|1669->338|1698->340|1775->391|1874->469|1904->472|2342->883|2358->890|2398->909|2442->926|2465->940|2497->951|2547->974|2566->984|2594->991|2660->1030|2679->1040|2707->1047|2774->1087|2793->1097|2821->1104|2916->1172|2932->1179|2973->1199|3043->1242|3062->1252|3090->1259|3188->1330|3227->1331|3263->1340|3324->1374|3343->1384|3371->1391|3478->1479|3517->1480|3552->1488|3594->1500|3628->1507|3695->1547|3714->1557|3742->1564|3837->1632|3853->1639|3894->1659|3962->1700|3981->1710|4009->1717|4218->1900|4261->1927|4300->1928|4338->1939|4364->1944|4406->1955|4440->1962|5289->2785|5348->2828|5388->2830|5424->2839|5618->3006|5644->3011|5717->3057|5731->3062|5764->3074|5796->3079|5810->3084|5841->3094|5926->3152|5940->3157|5973->3169|6003->3172|6017->3177|6053->3192|6105->3217|6144->3240|6184->3242|6229->3259|6272->3275|6287->3281|6311->3284|6341->3287|6356->3293|6384->3300|6440->3325|6482->3338|6719->3547|6746->3552|6832->3609|6862->3610|6908->3627|6946->3637|6965->3646|7019->3678|7065->3695|7095->3696|7125->3697|7159->3702|7189->3703|7235->3720|7278->3735|7305->3740|7338->3745|7353->3750|7385->3760|7453->3799|7483->3800|7535->3824|7554->3833|7608->3865|7852->4081|7879->4086|7953->4132|7968->4137|8002->4149|8035->4154|8050->4159|8082->4169|8176->4235|8191->4240|8233->4260|8264->4263|8279->4268|8318->4285|8371->4310|8413->4335|8454->4337|8500->4354|8544->4370|8560->4376|8585->4379|8616->4382|8632->4388|8661->4395|8718->4420|8760->4433|9000->4645|9027->4650|9079->4674|9094->4679|9129->4692|9411->4946|9438->4951|9471->4956|9486->4961|9518->4971|9620->5045|9647->5050|9680->5055|9695->5060|9729->5072|9762->5077|9777->5082|9809->5092|10062->5317|10089->5322|10141->5346|10156->5351|10192->5365|10479->5624|10494->5629|10528->5641|10561->5646|10576->5651|10608->5661|10710->5735|10737->5740|10770->5745|10785->5750|10819->5762|10852->5767|10867->5772|10899->5782|11165->6020|11192->6025|11244->6049|11259->6054|11303->6076|11572->6317|11587->6322|11621->6334|11654->6339|11669->6344|11701->6354|11975->6599|12017->6601|12058->6613|12281->6808|12308->6813|12368->6845|12383->6850|12423->6868|12712->7129|12739->7134|12772->7139|12788->7144|12823->7156|12857->7161|12873->7166|12906->7176|12961->7213|13001->7214|13042->7226|13269->7425|13296->7430|13356->7462|13371->7467|13411->7485|13501->7544|13538->7553|13712->7696|13779->7735|13819->7736|13854->7743|14055->7916|14072->7923|14111->7940|14335->8136|14374->8158|14414->8159|14459->8175|14503->8191|14518->8196|14543->8199|14574->8202|14589->8207|14618->8214|14671->8235|14710->8245|15065->8556|15098->8561|16451->9883|16484->9888|16575->9950|16605->9951|16636->9954|16752->10042|16781->10043|16847->10080|16877->10081|16911->10087|17045->10192|17075->10193|17114->10203|17561->10621|17591->10622|17634->10636|17721->10694|17751->10695|17781->10696|17815->10701|17845->10702|17888->10716|18003->10802|18033->10803|18080->10821|18294->11006|18324->11007|18354->11008|18397->11022|18427->11023|18474->11041|18532->11070|18562->11071|18607->11087|18699->11150|18729->11151|18763->11157|18792->11158|18822->11160|18851->11161|18884->11166|18947->11200|18977->11201|19010->11206|19220->11388|19249->11389|19281->11393|19377->11460|19407->11461|19438->11464|19673->11670|19703->11671|19745->11684|20016->11926|20046->11927|20079->11932|20134->11959|20163->11960|20200->11969|20230->11970|20262->11974|20291->11975|20322->11978|20408->12035|20438->12036|20472->12042|20637->12178|20667->12179|20707->12190|20792->12246|20822->12247|20865->12261|20922->12289|20952->12290|20999->12308|21260->12540|21290->12541|21335->12557|21394->12587|21424->12588|21471->12606|21734->12840|21764->12841|21803->12851|21833->12852|21867->12858|21896->12859|21926->12861|21955->12862|22153->13031|22195->13033|22226->13036|22297->13063|22329->13067|22408->13117|22438->13118|22469->13121|22524->13147|22554->13148|22586->13152|22634->13171|22664->13172|22697->13177|22769->13221|22798->13222|22867->13262|22897->13263|22930->13268|22993->13303|23022->13304|23051->13305|23085->13310|23115->13311|23148->13316|23301->13441|23330->13442|23361->13445|23390->13446|23422->13450|23451->13451|23483->13455|23531->13474|23561->13475|23594->13480|23666->13524|23695->13525|23729->13530|23759->13531|23792->13536|23855->13571|23884->13572|23915->13575|23944->13576|23975->13579|24004->13580|24035->13583|24146->13665|24176->13666|24207->13669|24277->13710|24307->13711|24339->13715|24383->13731|24412->13732|24443->13735|24498->13761|24528->13762|24560->13766|24665->13843|24694->13844|24726->13848|24755->13849|24787->13853|24830->13868|24859->13869|24890->13872|24919->13873|24950->13876|25075->13972|25105->13973|25139->13979|25374->14185|25404->14186|25441->14195|25588->14314|25628->14315|25660->14319|25728->14343|25765->14352|26014->14572|26044->14573|26086->14586|26312->14784|26342->14785|26372->14786|26406->14791|26436->14792|26478->14805|26824->15123|26854->15124|26887->15129|26916->15130|26946->15132|26975->15133|27005->15135|27244->15345|27274->15346|27308->15352|27735->15750|27765->15751|27804->15761|27931->15860|27960->15861|27996->15869|28156->16000|28186->16001|28225->16011|28425->16183|28454->16184|28483->16185|28526->16199|28556->16200|28595->16210|28684->16271|28713->16272|28749->16280|29026->16529|29055->16530|29118->16565
                  LINES: 28->1|34->3|38->7|38->7|38->7|40->9|40->9|41->10|42->11|42->11|43->12|57->26|57->26|57->26|58->27|58->27|58->27|59->28|59->28|59->28|61->30|61->30|61->30|63->32|63->32|63->32|67->36|67->36|67->36|69->38|69->38|69->38|72->41|72->41|73->42|73->42|73->42|73->42|76->45|76->45|77->46|78->47|79->48|81->50|81->50|81->50|85->54|85->54|85->54|87->56|87->56|87->56|98->67|98->67|98->67|99->68|99->68|100->69|101->70|120->89|120->89|120->89|121->90|125->94|125->94|126->95|126->95|126->95|126->95|126->95|126->95|127->96|127->96|127->96|127->96|127->96|127->96|128->97|128->97|128->97|129->98|129->98|129->98|129->98|129->98|129->98|129->98|130->99|131->100|137->106|137->106|138->107|138->107|139->108|139->108|139->108|139->108|140->109|140->109|140->109|140->109|140->109|141->110|141->110|141->110|141->110|141->110|141->110|142->111|142->111|143->112|143->112|143->112|149->118|149->118|150->119|150->119|150->119|150->119|150->119|150->119|151->120|151->120|151->120|151->120|151->120|151->120|152->121|152->121|152->121|153->122|153->122|153->122|153->122|153->122|153->122|153->122|154->123|155->124|161->130|161->130|162->131|162->131|162->131|167->136|167->136|167->136|167->136|167->136|168->137|168->137|168->137|168->137|168->137|168->137|168->137|168->137|174->143|174->143|175->144|175->144|175->144|180->149|180->149|180->149|180->149|180->149|180->149|181->150|181->150|181->150|181->150|181->150|181->150|181->150|181->150|187->156|187->156|188->157|188->157|188->157|193->162|193->162|193->162|193->162|193->162|193->162|197->166|197->166|198->167|201->170|201->170|202->171|202->171|202->171|206->175|206->175|206->175|206->175|206->175|206->175|206->175|206->175|208->177|208->177|209->178|212->181|212->181|213->182|213->182|213->182|216->185|218->187|222->191|224->193|224->193|225->194|228->197|228->197|228->197|233->202|233->202|233->202|234->203|234->203|234->203|234->203|234->203|234->203|234->203|235->204|236->205|246->215|247->216|283->252|288->257|289->258|289->258|290->259|292->261|292->261|294->263|294->263|295->264|298->267|298->267|299->268|306->275|306->275|307->276|308->277|308->277|308->277|308->277|308->277|309->278|312->281|312->281|313->282|316->285|316->285|316->285|316->285|316->285|317->286|318->287|318->287|320->289|321->290|321->290|322->291|322->291|323->292|323->292|326->295|326->295|326->295|327->296|329->298|329->298|331->300|331->300|331->300|332->301|337->306|337->306|338->307|345->314|345->314|346->315|347->316|347->316|348->317|348->317|349->318|349->318|351->320|351->320|351->320|352->321|355->324|355->324|356->325|356->325|356->325|357->326|357->326|357->326|358->327|361->330|361->330|363->332|363->332|363->332|364->333|367->336|367->336|368->337|368->337|369->338|369->338|370->339|370->339|373->342|373->342|374->343|375->344|376->345|376->345|376->345|377->346|377->346|377->346|378->347|378->347|378->347|379->348|380->349|380->349|380->349|380->349|381->350|382->351|382->351|382->351|382->351|382->351|383->352|386->355|386->355|387->356|387->356|387->356|387->356|388->357|388->357|388->357|389->358|390->359|390->359|390->359|390->359|391->360|392->361|392->361|393->362|393->362|395->364|395->364|397->366|397->366|397->366|398->367|398->367|398->367|399->368|400->369|400->369|401->370|401->370|401->370|402->371|404->373|404->373|404->373|404->373|405->374|406->375|406->375|408->377|408->377|410->379|413->382|413->382|415->384|419->388|419->388|420->389|421->390|421->390|422->391|423->392|424->393|428->397|428->397|429->398|432->401|432->401|432->401|432->401|432->401|433->402|441->410|441->410|442->411|442->411|444->413|444->413|446->415|449->418|449->418|450->419|456->425|456->425|457->426|459->428|459->428|461->430|465->434|465->434|466->435|469->438|469->438|469->438|469->438|469->438|470->439|471->440|471->440|473->442|478->447|478->447|495->464
                  -- GENERATED --
              */
          