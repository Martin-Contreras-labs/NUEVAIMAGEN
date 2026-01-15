
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

object hojaVidaMantencionHoja extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template12[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.PlanMantencion],List[String],List[String],List[tables.TipoMantencion],List[tables.TipoTrabajo],List[tables.Moneda],List[tables.Proveedor],List[tables.HojaVida],List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
allPlan: List[tables.PlanMantencion], listAtributos: List[String], listCompra: List[String],
listTipoMant: List[tables.TipoMantencion], listTipoTrab: List[tables.TipoTrabajo], listMoneda: List[tables.Moneda], listProveedor: List[tables.Proveedor], listDetalle: List[tables.HojaVida], listTiposPlan: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.1*/("""
"""),format.raw/*5.1*/("""<!-- ANCLA CABECERAS DE TABLAS -->
	<style type="text/css"> 
        thead tr th """),format.raw/*7.21*/("""{"""),format.raw/*7.22*/(""" 
            """),format.raw/*8.13*/("""position: sticky;
			background-color: #eeeeee;
            top: 0;
        """),format.raw/*11.9*/("""}"""),format.raw/*11.10*/("""
    """),format.raw/*12.5*/("""</style>

    	
"""),_display_(/*15.2*/main("")/*15.10*/ {_display_(Seq[Any](format.raw/*15.12*/("""

"""),_display_(/*17.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*17.50*/("""
	"""),format.raw/*18.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*19.4*/barraTitulo(mapDiccionario, "HOJA DE VIDA","AGREGAR/MODIFICAR/ELIMINAR")),format.raw/*19.76*/("""
		"""),format.raw/*20.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
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
						<td>"""),_display_(/*33.12*/allPlan/*33.19*/.get(0).equipoGrupo),format.raw/*33.38*/("""</td>
						<td>"""),_display_(/*34.12*/mapDiccionario/*34.26*/.get("RUT")),format.raw/*34.37*/("""-PROV:</td>
						<td>"""),_display_(/*35.12*/listCompra/*35.22*/.get(0)),format.raw/*35.29*/("""</td>
						<td>FECHA:</td>
						<td>"""),_display_(/*37.12*/listCompra/*37.22*/.get(3)),format.raw/*37.29*/("""</td>
						<td>MONEDA:</td>
						<td>"""),_display_(/*39.12*/listCompra/*39.22*/.get(5)),format.raw/*39.29*/("""</td>
					</tr>
					<tr>
						<td>CODIGO EQUIPO:</td>
						<td>"""),_display_(/*43.12*/allPlan/*43.19*/.get(0).equipoCodigo),format.raw/*43.39*/("""</td>
						<td>PROVEEDOR:</td>
						<td>"""),_display_(/*45.12*/listCompra/*45.22*/.get(1)),format.raw/*45.29*/("""</td>
						<td>PDF:</td>
						<td>
							"""),_display_(if(listCompra.get(4)!="0")/*48.35*/{_display_(Seq[Any](format.raw/*48.36*/("""
								"""),format.raw/*49.9*/("""<a href="#" onclick="mostrarPDF('"""),_display_(/*49.43*/listCompra/*49.53*/.get(4)),format.raw/*49.60*/("""')">
									<kbd style="background-color: #85C1E9">pdf</kbd>
								</a>
							""")))}else/*52.13*/{_display_(Seq[Any](format.raw/*52.14*/("""
							"""),format.raw/*53.8*/("""---
							""")))}),format.raw/*54.9*/("""
						"""),format.raw/*55.7*/("""</td>
						<td>PRECIO:</td>
						<td>"""),_display_(/*57.12*/listCompra/*57.22*/.get(6)),format.raw/*57.29*/("""</td>
					</tr>
					<tr>
						<td>NOMBRE EQUIPO:</td>
						<td>"""),_display_(/*61.12*/allPlan/*61.19*/.get(0).equipoNombre),format.raw/*61.39*/("""</td>
						<td>FACTURA:</td>
						<td>"""),_display_(/*63.12*/listCompra/*63.22*/.get(2)),format.raw/*63.29*/("""</td>
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
							"""),_display_(/*74.9*/for(lista <- listAtributos) yield /*74.36*/{_display_(Seq[Any](format.raw/*74.37*/("""
									"""),_display_(/*75.11*/lista),format.raw/*75.16*/("""|
								""")))}),format.raw/*76.10*/("""
						"""),format.raw/*77.7*/("""</td>
					</tr>
				</table>
				<table id="planes" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<tr>
						<td colspan="20">
							<b>PLAN DE MANTENCION</b>
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
					"""),_display_(/*96.7*/for((lista, index) <- allPlan.zipWithIndex) yield /*96.50*/ {_display_(Seq[Any](format.raw/*96.52*/("""
					    """),format.raw/*97.10*/("""<tr>
					        <td style="text-align:center">
					            <input type="hidden" class="id_tipoPlan" value=""""),_display_(/*99.67*/lista/*99.72*/.id_tipoPlan),format.raw/*99.84*/("""">
					            """),_display_(/*100.19*/lista/*100.24*/.tipoPlanNombre),format.raw/*100.39*/("""
					        """),format.raw/*101.14*/("""</td>
					        <td style="text-align:center">
					            <input type="date" class="fechaReset form-control center"
					                  onblur="if(!limitaFecha(value, 720, 10)) """),format.raw/*104.65*/("""{"""),format.raw/*104.66*/("""
					                               """),format.raw/*105.37*/("""value = '"""),_display_(/*105.47*/utilities/*105.56*/.Fechas.AAMMDD(lista.fechaReset)),format.raw/*105.88*/("""';
					                           """),format.raw/*106.33*/("""}"""),format.raw/*106.34*/(""" """),format.raw/*106.35*/("""else """),format.raw/*106.40*/("""{"""),format.raw/*106.41*/("""
					                               """),format.raw/*107.37*/("""actualizaAll('"""),_display_(/*107.52*/index),format.raw/*107.57*/("""', '"""),_display_(/*107.62*/lista/*107.67*/.id_equipo),format.raw/*107.77*/("""', 'fechaReset', value);
					                           """),format.raw/*108.33*/("""}"""),format.raw/*108.34*/(""""
					                  value=""""),_display_(/*109.32*/utilities/*109.41*/.Fechas.AAMMDD(lista.fechaReset)),format.raw/*109.73*/("""">
					        </td>
					        <td style="text-align:center">
					            <input type="hidden" class="id_unidadMantencion" value=""""),_display_(/*112.75*/lista/*112.80*/.id_unidadMantencion),format.raw/*112.100*/("""">
					            """),_display_(/*113.19*/lista/*113.24*/.unidadMantencion),format.raw/*113.41*/("""
					        """),format.raw/*114.14*/("""</td>
					        <td style="text-align:right">
					            <input type="text" class="estadoActual form-control center"
					                   id="lectActual_"""),_display_(/*117.41*/index),format.raw/*117.46*/(""""
					                   value=""""),_display_(/*118.33*/lista/*118.38*/.estadoActual),format.raw/*118.51*/(""""
					                   onfocus="value = value.replace(/,/g, ''); auxiliar = value;"
					                   onkeydown="return ingresoDouble(event, value)"
					                   autocomplete="off"
					                   onchange="if(value == '') value = 0; 
					                             actualizaAll('"""),_display_(/*123.50*/index),format.raw/*123.55*/("""', '"""),_display_(/*123.60*/lista/*123.65*/.id_equipo),format.raw/*123.75*/("""', 'estadoActual', value); 
					                             actualizarProximaMantencion('"""),_display_(/*124.65*/index),format.raw/*124.70*/("""', '"""),_display_(/*124.75*/lista/*124.80*/.id_tipoPlan),format.raw/*124.92*/("""', '"""),_display_(/*124.97*/lista/*124.102*/.id_equipo),format.raw/*124.112*/("""', 'lectura');">
					        </td>
							<td style="text-align:center">
							    <input type="hidden" class="cadaNEstimado" value=""""),_display_(/*127.63*/lista/*127.68*/.cadaNEstimado),format.raw/*127.82*/("""">
							    """),_display_(/*128.13*/lista/*128.18*/.cadaNEstimado),format.raw/*128.32*/("""
							"""),format.raw/*129.8*/("""</td>
							<td style="text-align:center">
							    <input type="hidden" class="consumoEstimadoPorMes" value=""""),_display_(/*131.71*/lista/*131.76*/.consumoEstimadoPorMes),format.raw/*131.98*/("""">
							    """),_display_(/*132.13*/lista/*132.18*/.consumoEstimadoPorMes),format.raw/*132.40*/("""
							"""),format.raw/*133.8*/("""</td>
							"""),_display_(if(mapPermiso.get("parametro.planes_calculaProximaMant") != null && mapPermiso.get("parametro.planes_calculaProximaMant").equals("0"))/*134.143*/ {_display_(Seq[Any](format.raw/*134.145*/("""
							    """),format.raw/*135.12*/("""<!-- Modo edición manual -->
							    <td style="text-align:right">
							        <input type="text" class="proximaMantencion form-control center"
							               id="proximaMantencion_"""),_display_(/*138.46*/index),format.raw/*138.51*/(""""
							               value=""""),_display_(/*139.31*/lista/*139.36*/.proximaMantencion),format.raw/*139.54*/(""""
							               onfocus="value = value.replace(/,/g,'');"
							               onkeydown="return ingresoDouble(event, value)"
							               autocomplete="off"
							               onchange="if(value == '') value = 0; actualizaPlan('"""),_display_(/*143.76*/lista/*143.81*/.id_tipoPlan),format.raw/*143.93*/("""', '"""),_display_(/*143.98*/lista/*143.103*/.id_equipo),format.raw/*143.113*/("""', 'proximaMantencion', value);">
							    </td>
							""")))}else/*145.15*/{_display_(Seq[Any](format.raw/*145.16*/("""
							    """),format.raw/*146.12*/("""<!-- Modo cálculo automático -->
							    <td style="text-align:right">
							        <input type="text" class="proximaMantencion form-control center"
							               id="proximaMantencion_"""),_display_(/*149.46*/index),format.raw/*149.51*/(""""
							               value=""""),_display_(/*150.31*/lista/*150.36*/.proximaMantencion),format.raw/*150.54*/(""""
							               disabled>
							    </td>
							""")))}),format.raw/*153.9*/("""


					        """),format.raw/*156.14*/("""<td style="text-align:center">
					            <input type="date" class="fechaEstimada form-control center" disabled>
					        </td>
					    </tr>
					""")))}),format.raw/*160.7*/("""

				"""),format.raw/*162.5*/("""</table>
				
				<br>
				<div class="noprint">
					<div class="row justify-content-md-center" >
						<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
							<form method="post" action="/hojaVidaMantencionHojaAgrega/">
								<input type="hidden" name="id_equipo" value=""""),_display_(/*169.55*/allPlan/*169.62*/.get(0).id_equipo),format.raw/*169.79*/("""">
								<input type="submit" value="AGREGAR LINEA" class="btn btn-success btn-sm rounded-pill btn-block">
							</form>
						</div>
						
						<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
							<input type="button"  value="VOLVER" class="btn btn-info btn-sm rounded-pill btn-block" onclick="location.href='/hojaVidaMantencionLista/0';">
						</div>
					</div>
				</div>
				
				<table id="detalle" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead>
						<tr>
							<td colspan="12">
								<h5><b><font color="#008000"> REGISTRO DE GASTOS Y OTROS ASOCIADOS</font></b></h5>
							</td>
						</tr>
						<tr>
							<th style="text-align:center">Tipo<br>Mantención</th>
							<th style="text-align:center">Tipo<br>Plan</th>
							<th style="text-align:center">Fecha<br>Inicio</th>
							<th style="text-align:center">Tipo<br>Trabajo</th>
							<th style="text-align:center">Moneda<br></th>
							<th style="text-align:center">Costo<br>Neto</th>
							<th style="text-align:center">Prestador<br>Servicio</th>
							<th style="text-align:center">Número<br>Documento</th>
							<th style="text-align:center">Fecha<br>Documento</th>
							<th style="text-align:center">Archivo<br></th>
							<th style="text-align:center">Fecha<br>Termino</th>
							<TH style="text-align:center">Del</TH>
						</tr>
					</thead>
					<tbody>
						"""),_display_(/*203.8*/for(det <- listDetalle) yield /*203.31*/{_display_(Seq[Any](format.raw/*203.32*/("""
							"""),format.raw/*204.8*/("""<tr>
								<td hidden id="idHojaVida"""),_display_(/*205.35*/det/*205.38*/.contador),format.raw/*205.47*/("""">"""),_display_(/*205.50*/det/*205.53*/.id),format.raw/*205.56*/("""</td>
								<td hidden id="tipoMant"""),_display_(/*206.33*/det/*206.36*/.contador),format.raw/*206.45*/("""">"""),_display_(/*206.48*/det/*206.51*/.tipoMantencionNombre),format.raw/*206.72*/("""</td>
								<td hidden id="tipoTrab"""),_display_(/*207.33*/det/*207.36*/.contador),format.raw/*207.45*/("""">"""),_display_(/*207.48*/det/*207.51*/.tipoTrabajoNombre),format.raw/*207.69*/("""</td>
								<td hidden id="prest"""),_display_(/*208.30*/det/*208.33*/.contador),format.raw/*208.42*/("""">"""),_display_(/*208.45*/det/*208.48*/.proveedorNickName),format.raw/*208.66*/("""</td>
								
								<td style="text-align:center">
									<select class="custom-select center"
										onchange="actualizaPorCampo('"""),_display_(/*212.41*/det/*212.44*/.id),format.raw/*212.47*/("""','id_tipoMantencion',value);">
										<option value=""""),_display_(/*213.27*/det/*213.30*/.id_tipoMantencion),format.raw/*213.48*/("""">"""),_display_(/*213.51*/det/*213.54*/.tipoMantencionNombre),format.raw/*213.75*/("""</option>
										"""),_display_(/*214.12*/for(lista <- listTipoMant) yield /*214.38*/{_display_(Seq[Any](format.raw/*214.39*/("""
											"""),format.raw/*215.12*/("""<option value=""""),_display_(/*215.28*/lista/*215.33*/.id),format.raw/*215.36*/("""">"""),_display_(/*215.39*/lista/*215.44*/.nombre),format.raw/*215.51*/("""</option>
										""")))}),format.raw/*216.12*/("""
									"""),format.raw/*217.10*/("""</select>
								</td>
								<td style="text-align:center">
									<select class="custom-select center"
										onchange="actualizaPorCampo('"""),_display_(/*221.41*/det/*221.44*/.id),format.raw/*221.47*/("""','id_tipoPlan',value);">
										<option value=""""),_display_(/*222.27*/det/*222.30*/.id_tipoPlan),format.raw/*222.42*/("""">"""),_display_(/*222.45*/det/*222.48*/.tipoPlanNombre),format.raw/*222.63*/("""</option>
										"""),_display_(/*223.12*/for(lista <- listTiposPlan) yield /*223.39*/{_display_(Seq[Any](format.raw/*223.40*/("""
											"""),format.raw/*224.12*/("""<option value=""""),_display_(/*224.28*/lista/*224.33*/.get(0)),format.raw/*224.40*/("""">"""),_display_(/*224.43*/lista/*224.48*/.get(1)),format.raw/*224.55*/("""</option>
										""")))}),format.raw/*225.12*/("""
									"""),format.raw/*226.10*/("""</select>
								</td>
								<td style="text-align:center">
									<input type="date" class="form-control center"
							  			onblur="if(!limitaFecha(value,720,10)) """),format.raw/*230.52*/("""{"""),format.raw/*230.53*/("""
													"""),format.raw/*231.14*/("""value = '"""),_display_(/*231.24*/utilities/*231.33*/.Fechas.AAMMDD(det.fechaInicio)),format.raw/*231.64*/("""';
												"""),format.raw/*232.13*/("""}"""),format.raw/*232.14*/(""" """),format.raw/*232.15*/("""else """),format.raw/*232.20*/("""{"""),format.raw/*232.21*/("""
													"""),format.raw/*233.14*/("""actualizaPorCampo('"""),_display_(/*233.34*/det/*233.37*/.id),format.raw/*233.40*/("""','fechaInicio',value);
												"""),format.raw/*234.13*/("""}"""),format.raw/*234.14*/(""""
							  			value=""""),_display_(/*235.21*/utilities/*235.30*/.Fechas.AAMMDD(det.fechaInicio)),format.raw/*235.61*/("""">
								</td>
								<td style="text-align:center">
									<select class="custom-select center"
										onchange="actualizaPorCampo('"""),_display_(/*239.41*/det/*239.44*/.id),format.raw/*239.47*/("""','id_tipoTrabajo',value);">
										<option value=""""),_display_(/*240.27*/det/*240.30*/.id_tipoTrabajo),format.raw/*240.45*/("""">"""),_display_(/*240.48*/det/*240.51*/.tipoTrabajoNombre),format.raw/*240.69*/("""</option>
										"""),_display_(/*241.12*/for(lista <- listTipoTrab) yield /*241.38*/{_display_(Seq[Any](format.raw/*241.39*/("""
											"""),format.raw/*242.12*/("""<option value=""""),_display_(/*242.28*/lista/*242.33*/.id),format.raw/*242.36*/("""">"""),_display_(/*242.39*/lista/*242.44*/.nombre),format.raw/*242.51*/("""</option>
										""")))}),format.raw/*243.12*/("""
									"""),format.raw/*244.10*/("""</select>
								</td>
								<td style="text-align:center">
									<select class="custom-select center"
										onchange="actualizaPorCampo('"""),_display_(/*248.41*/det/*248.44*/.id),format.raw/*248.47*/("""','id_moneda',value);">
										<option value=""""),_display_(/*249.27*/det/*249.30*/.id_moneda),format.raw/*249.40*/("""">"""),_display_(/*249.43*/det/*249.46*/.monedaNickName),format.raw/*249.61*/("""</option>
										"""),_display_(/*250.12*/for(lista <- listMoneda) yield /*250.36*/{_display_(Seq[Any](format.raw/*250.37*/("""
											"""),format.raw/*251.12*/("""<option value=""""),_display_(/*251.28*/lista/*251.33*/.id),format.raw/*251.36*/("""">"""),_display_(/*251.39*/lista/*251.44*/.nickName),format.raw/*251.53*/("""</option>
										""")))}),format.raw/*252.12*/("""
									"""),format.raw/*253.10*/("""</select>
								</td>
								<td style="text-align:right">
									<input type="text" class="form-control center"
										value=""""),_display_(/*257.19*/det/*257.22*/.costoNeto),format.raw/*257.32*/(""""
										onfocus="value = value.replace(/,/g,'');" 
										onkeydown="return ingresoDouble(window.event)"
										autocomplete="off"
										onchange="actualizaPorCampo('"""),_display_(/*261.41*/det/*261.44*/.id),format.raw/*261.47*/("""','costoNeto',value);">
								</td>
								<td style="text-align:center">
									<select class="custom-select center"
										onchange="actualizaPorCampo('"""),_display_(/*265.41*/det/*265.44*/.id),format.raw/*265.47*/("""','id_proveedor',value);">
										<option value=""""),_display_(/*266.27*/det/*266.30*/.id_proveedor),format.raw/*266.43*/("""">"""),_display_(/*266.46*/det/*266.49*/.proveedorNickName),format.raw/*266.67*/("""</option>
										"""),_display_(/*267.12*/for(lista <- listProveedor) yield /*267.39*/{_display_(Seq[Any](format.raw/*267.40*/("""
											"""),format.raw/*268.12*/("""<option value=""""),_display_(/*268.28*/lista/*268.33*/.id),format.raw/*268.36*/("""">"""),_display_(/*268.39*/lista/*268.44*/.nickName),format.raw/*268.53*/("""</option>
										""")))}),format.raw/*269.12*/("""
									"""),format.raw/*270.10*/("""</select>
								</td>
								<td style="text-align:right">
									<input type="text" class="form-control center"
										value=""""),_display_(/*274.19*/det/*274.22*/.numeroDocumento),format.raw/*274.38*/("""" 
										maxlength="50"
										autocomplete="off"
										onchange="actualizaPorCampo('"""),_display_(/*277.41*/det/*277.44*/.id),format.raw/*277.47*/("""','numeroDocumento',value);">
								</td>
								<td style="text-align:center">
									"""),_display_(if(det.fechaDocumento==null)/*280.39*/{_display_(Seq[Any](format.raw/*280.40*/("""
										"""),format.raw/*281.11*/("""<input type="date" class="form-control center"
								  			onblur="if(limitaFecha(value,720,10)) """),format.raw/*282.52*/("""{"""),format.raw/*282.53*/("""
														"""),format.raw/*283.15*/("""actualizaPorCampo('"""),_display_(/*283.35*/det/*283.38*/.id),format.raw/*283.41*/("""','fechaDocumento',value);
													"""),format.raw/*284.14*/("""}"""),format.raw/*284.15*/("""">
									""")))}else/*285.15*/{_display_(Seq[Any](format.raw/*285.16*/("""
										"""),format.raw/*286.11*/("""<input type="date" class="form-control center"
								  			onblur="if(!limitaFecha(value,720,10)) """),format.raw/*287.53*/("""{"""),format.raw/*287.54*/("""
														"""),format.raw/*288.15*/("""value = '"""),_display_(/*288.25*/utilities/*288.34*/.Fechas.AAMMDD(det.fechaDocumento)),format.raw/*288.68*/("""';
													"""),format.raw/*289.14*/("""}"""),format.raw/*289.15*/(""" """),format.raw/*289.16*/("""else """),format.raw/*289.21*/("""{"""),format.raw/*289.22*/("""
														"""),format.raw/*290.15*/("""actualizaPorCampo('"""),_display_(/*290.35*/det/*290.38*/.id),format.raw/*290.41*/("""','fechaDocumento',value);
													"""),format.raw/*291.14*/("""}"""),format.raw/*291.15*/(""""
								  			value=""""),_display_(/*292.22*/utilities/*292.31*/.Fechas.AAMMDD(det.fechaDocumento)),format.raw/*292.65*/("""">
									""")))}),format.raw/*293.11*/("""
								"""),format.raw/*294.9*/("""</td>
								<td style="text-align:center;">
									<form id="form_"""),_display_(/*296.26*/det/*296.29*/.id),format.raw/*296.32*/("""" action="/grabarHojaVidaPdf/" enctype="multipart/form-data" method="POST">
										<input type="hidden" name="id_equipo" value=""""),_display_(/*297.57*/det/*297.60*/.id_equipo),format.raw/*297.70*/("""">
										<input type="hidden" name="id_hojaVida" value=""""),_display_(/*298.59*/det/*298.62*/.id),format.raw/*298.65*/("""">
										<span class="btn btn-info btn-sm btn-file" style="font-size: 8px;">
											"""),_display_(if(det.documentoPDF.equals("0"))/*300.45*/{_display_(Seq[Any](format.raw/*300.46*/("""
												"""),format.raw/*301.13*/("""<div id="txtBtn">Adjuntar</div>
											""")))}else/*302.17*/{_display_(Seq[Any](format.raw/*302.18*/("""
												"""),format.raw/*303.13*/("""<div id="txtBtn">Reemplazar</div>
											""")))}),format.raw/*304.13*/("""
					    					"""),format.raw/*305.15*/("""<input type="file" id="docAdjunto_"""),_display_(/*305.50*/det/*305.53*/.id),format.raw/*305.56*/("""" name="docAdjunto" 
												onchange="LimitAttach(this.form, this.form.docAdjunto.value, '"""),_display_(/*306.76*/det/*306.79*/.id),format.raw/*306.82*/("""'); 
													$('#form_"""),_display_(/*307.24*/det/*307.27*/.id),format.raw/*307.30*/("""').submit();">
										</span>
										"""),_display_(if(!det.documentoPDF.equals("0"))/*309.45*/{_display_(Seq[Any](format.raw/*309.46*/("""
											"""),format.raw/*310.12*/("""<a href="#" onclick="mostrarPDF('"""),_display_(/*310.46*/det/*310.49*/.documentoPDF),format.raw/*310.62*/("""')">
												<kbd style="background-color: #85C1E9">VER</kbd>
											</a>
										""")))} else {null} ),format.raw/*313.12*/("""
									"""),format.raw/*314.10*/("""</form>
									
								</td>
								<td style="text-align:center">
									<input type="date" class="form-control center"
							  			onblur="if(!limitaFecha(value,720,10)) """),format.raw/*319.52*/("""{"""),format.raw/*319.53*/("""
													"""),format.raw/*320.14*/("""value = '"""),_display_(/*320.24*/utilities/*320.33*/.Fechas.AAMMDD(det.fechaTermino)),format.raw/*320.65*/("""';
												"""),format.raw/*321.13*/("""}"""),format.raw/*321.14*/(""" """),format.raw/*321.15*/("""else """),format.raw/*321.20*/("""{"""),format.raw/*321.21*/("""
													"""),format.raw/*322.14*/("""actualizaPorCampo('"""),_display_(/*322.34*/det/*322.37*/.id),format.raw/*322.40*/("""','fechaTermino',value);
												"""),format.raw/*323.13*/("""}"""),format.raw/*323.14*/(""""
							  			value=""""),_display_(/*324.21*/utilities/*324.30*/.Fechas.AAMMDD(det.fechaTermino)),format.raw/*324.62*/("""">
								</td>
								<td rowspan="2" style="text-align:center;">
									<form id="del_"""),_display_(/*327.25*/det/*327.28*/.id),format.raw/*327.31*/("""" method="post" action="/hojaVidaMantencionHojaElimina/">
										<input type="hidden" name="id_equipo" value=""""),_display_(/*328.57*/det/*328.60*/.id_equipo),format.raw/*328.70*/("""">
										<input type="hidden" name="id_hojaVida" value=""""),_display_(/*329.59*/det/*329.62*/.id),format.raw/*329.65*/("""">
										<a href="#" onclick='document.getElementById("del_"""),_display_(/*330.62*/det/*330.65*/.id),format.raw/*330.68*/("""").submit();'>
											<kbd style="background-color: red">X</kbd>
										</a>
									</form>
								</td>
							</tr>
							<tr>
								<td style="text-align:left">Trabajo hecho:</TH>
								<td colspan="10" style="text-align:center">
									<textarea class="form-control" 
	  										autocomplete="off"
											onchange="actualizaPorCampo('"""),_display_(/*341.42*/det/*341.45*/.id),format.raw/*341.48*/("""','trabajoHecho',value);">"""),_display_(/*341.75*/det/*341.78*/.trabajoHecho),format.raw/*341.91*/("""</textarea>
								</td>
							</tr>
						""")))}),format.raw/*344.8*/("""
					"""),format.raw/*345.6*/("""</tbody>
				</table>
			</div>
		</div>
	</div>
	
	<div id='muestraPDF' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'></h5>
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
	
""")))}),format.raw/*372.2*/("""




"""),format.raw/*377.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*378.31*/("""{"""),format.raw/*378.32*/("""
		"""),format.raw/*379.3*/("""calcFechaProxima();
		document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*381.2*/("""}"""),format.raw/*381.3*/(""");
	
	const calcFechaProxima = () => """),format.raw/*383.33*/("""{"""),format.raw/*383.34*/("""
	    """),format.raw/*384.6*/("""let filas = $(".estadoActual").length;
	    let hoy = new Date();
	
	    for (let i = 0; i < filas; i++) """),format.raw/*387.38*/("""{"""),format.raw/*387.39*/("""
	        """),format.raw/*388.10*/("""let fecha = $(".fechaReset")[i]?.value;
	        let proxima = parseFloat($(".proximaMantencion")[i]?.value.replace(/,/g, '') || 0);
	        let actual = parseFloat($(".estadoActual")[i]?.value.replace(/,/g, '') || 0);
	        let rotacion = parseFloat($(".consumoEstimadoPorMes")[i]?.value.replace(/,/g, '') || 0);
	
	        let dif = proxima - actual;
	
	        if (dif <= 0 || rotacion <= 0) """),format.raw/*395.41*/("""{"""),format.raw/*395.42*/("""
	            """),format.raw/*396.14*/("""$(".fechaEstimada")[i].value = fechaAAMMDD(hoy);
	        """),format.raw/*397.10*/("""}"""),format.raw/*397.11*/(""" """),format.raw/*397.12*/("""else """),format.raw/*397.17*/("""{"""),format.raw/*397.18*/("""
	            """),format.raw/*398.14*/("""let diasASumar = (dif / rotacion) * 30;
	            let auxFecha;
	            try """),format.raw/*400.18*/("""{"""),format.raw/*400.19*/("""
	                """),format.raw/*401.18*/("""let aux = fecha.split("-");
	                auxFecha = new Date(aux[0], parseInt(aux[1]) - 1, aux[2]);
	                auxFecha.setDate(auxFecha.getDate() + diasASumar);
	            """),format.raw/*404.14*/("""}"""),format.raw/*404.15*/(""" """),format.raw/*404.16*/("""catch (error) """),format.raw/*404.30*/("""{"""),format.raw/*404.31*/("""
	                """),format.raw/*405.18*/("""auxFecha = hoy;
	            """),format.raw/*406.14*/("""}"""),format.raw/*406.15*/("""
	
	            """),format.raw/*408.14*/("""$(".fechaEstimada")[i].value = fechaAAMMDD(auxFecha);
	        """),format.raw/*409.10*/("""}"""),format.raw/*409.11*/("""
	    """),format.raw/*410.6*/("""}"""),format.raw/*410.7*/("""
	"""),format.raw/*411.2*/("""}"""),format.raw/*411.3*/("""

	
	"""),format.raw/*414.2*/("""const mostrarPDF = (nombrePDF) => """),format.raw/*414.36*/("""{"""),format.raw/*414.37*/("""
	    """),format.raw/*415.6*/("""document.getElementById('rutaPDF').innerHTML = `<object data='/showPdf/$"""),format.raw/*415.78*/("""{"""),format.raw/*415.79*/("""nombrePDF"""),format.raw/*415.88*/("""}"""),format.raw/*415.89*/("""' type='application/pdf' width='100%' height='720'></object>`;
	    $('#muestraPDF').modal('show');
	"""),format.raw/*417.2*/("""}"""),format.raw/*417.3*/("""


	
	"""),format.raw/*421.2*/("""const actualizaPorCampoPlan = (id_tipoPlan, id_equipo, campo, valor) => """),format.raw/*421.74*/("""{"""),format.raw/*421.75*/("""
	
	    """),format.raw/*423.6*/("""var formData = new FormData();
	    formData.append('id_tipoPlan', id_tipoPlan);
	    formData.append('id_equipo', id_equipo);
	    formData.append('campo', campo);
	    formData.append('valor', valor);
	
	    $.ajax("""),format.raw/*429.13*/("""{"""),format.raw/*429.14*/("""
	        """),format.raw/*430.10*/("""url: "/actualCampoPlanMantencionAjax/",
	        type: "POST",
	        method: "POST",
	        data: formData,
	        cache: false,
	        contentType: false,
	        processData: false,
	        success: function (respuesta) """),format.raw/*437.40*/("""{"""),format.raw/*437.41*/("""
	            """),format.raw/*438.14*/("""calcFechaProxima();
	        """),format.raw/*439.10*/("""}"""),format.raw/*439.11*/("""
	    """),format.raw/*440.6*/("""}"""),format.raw/*440.7*/(""");
	"""),format.raw/*441.2*/("""}"""),format.raw/*441.3*/("""
	
	"""),format.raw/*443.2*/("""const actualizaPorCampo = (id_hojaVida, campo, valor) =>"""),format.raw/*443.58*/("""{"""),format.raw/*443.59*/("""
		"""),format.raw/*444.3*/("""var formData = new FormData();
	  	formData.append('id_hojaVida',id_hojaVida);
		formData.append('campo',campo);
	  	formData.append('valor',valor);
        $.ajax("""),format.raw/*448.16*/("""{"""),format.raw/*448.17*/("""
            """),format.raw/*449.13*/("""url: "/actualCampoHojaVidaAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*456.36*/("""{"""),format.raw/*456.37*/("""
	     	"""),format.raw/*457.8*/("""}"""),format.raw/*457.9*/("""
        """),format.raw/*458.9*/("""}"""),format.raw/*458.10*/(""");
	"""),format.raw/*459.2*/("""}"""),format.raw/*459.3*/("""

	
	"""),format.raw/*462.2*/("""const actualizaAll = (index, id_equipo, campo, valor) => """),format.raw/*462.59*/("""{"""),format.raw/*462.60*/("""
	    """),format.raw/*463.6*/("""const unidadCambiada = $(".id_unidadMantencion")[index].value;
	    const filas = $(".estadoActual").length;
	    for (let i = 0; i < filas; i++) """),format.raw/*465.38*/("""{"""),format.raw/*465.39*/("""
	        """),format.raw/*466.10*/("""if ($(".id_unidadMantencion")[i].value === unidadCambiada) """),format.raw/*466.69*/("""{"""),format.raw/*466.70*/("""
	            """),format.raw/*467.14*/("""if (campo === 'estadoActual') """),format.raw/*467.44*/("""{"""),format.raw/*467.45*/("""
	                """),format.raw/*468.18*/("""$(".estadoActual")[i].value = valor;
	                actualizaPlan($(".id_tipoPlan")[i].value, id_equipo, campo, valor);
	                actualizarProximaMantencion(i, $(".id_tipoPlan")[i].value, id_equipo, campo);
	            """),format.raw/*471.14*/("""}"""),format.raw/*471.15*/("""
				"""),format.raw/*472.5*/("""if (campo === 'fechaReset') """),format.raw/*472.33*/("""{"""),format.raw/*472.34*/("""
					"""),format.raw/*473.6*/("""$(".fechaReset")[i].value = valor;
					actualizaPlan($(".id_tipoPlan")[i].value, id_equipo, campo, valor);
					actualizarProximaMantencion(i, $(".id_tipoPlan")[i].value, id_equipo, campo);
				"""),format.raw/*476.5*/("""}"""),format.raw/*476.6*/("""
	        """),format.raw/*477.10*/("""}"""),format.raw/*477.11*/("""
	    """),format.raw/*478.6*/("""}"""),format.raw/*478.7*/("""
	"""),format.raw/*479.2*/("""}"""),format.raw/*479.3*/("""


	
	


	"""),format.raw/*486.2*/("""let calculoAutomatico = "1";
	"""),_display_(if(mapPermiso.get("parametro.planes_calculaProximaMant") != null && mapPermiso.get("parametro.planes_calculaProximaMant").equals("0"))/*487.137*/ {_display_(Seq[Any](format.raw/*487.139*/("""
		"""),format.raw/*488.3*/("""calculoAutomatico = "0";
	""")))} else {null} ),format.raw/*489.3*/("""

	"""),format.raw/*491.2*/("""function determinaProxima(lectActual, frecuencia) """),format.raw/*491.52*/("""{"""),format.raw/*491.53*/("""
		"""),format.raw/*492.3*/("""if(calculoAutomatico == 1)"""),format.raw/*492.29*/("""{"""),format.raw/*492.30*/("""
			"""),format.raw/*493.4*/("""if (frecuencia <= 0) """),format.raw/*493.25*/("""{"""),format.raw/*493.26*/("""
				"""),format.raw/*494.5*/("""alert("la rotacion debe ser mayor a 0")
				return 0;
			"""),format.raw/*496.4*/("""}"""),format.raw/*496.5*/("""
			"""),format.raw/*497.4*/("""if (lectActual % frecuencia === 0) """),format.raw/*497.39*/("""{"""),format.raw/*497.40*/("""
				"""),format.raw/*498.5*/("""// Caso múltiplo exacto
				return lectActual + frecuencia;
			"""),format.raw/*500.4*/("""}"""),format.raw/*500.5*/(""" """),format.raw/*500.6*/("""else """),format.raw/*500.11*/("""{"""),format.raw/*500.12*/("""
				"""),format.raw/*501.5*/("""// Redondear al siguiente múltiplo
				const resto = lectActual % frecuencia;
				return lectActual - resto + frecuencia;
			"""),format.raw/*504.4*/("""}"""),format.raw/*504.5*/("""
		"""),format.raw/*505.3*/("""}"""),format.raw/*505.4*/("""else"""),format.raw/*505.8*/("""{"""),format.raw/*505.9*/("""
			"""),format.raw/*506.4*/("""if (frecuencia < 0)"""),format.raw/*506.23*/("""{"""),format.raw/*506.24*/("""
				"""),format.raw/*507.5*/("""alert("la rotacion debe ser mayor a 0");
			"""),format.raw/*508.4*/("""}"""),format.raw/*508.5*/("""else """),format.raw/*508.10*/("""{"""),format.raw/*508.11*/("""
				"""),format.raw/*509.5*/("""return lectActual + frecuencia;
			"""),format.raw/*510.4*/("""}"""),format.raw/*510.5*/("""
		"""),format.raw/*511.3*/("""}"""),format.raw/*511.4*/("""

	"""),format.raw/*513.2*/("""}"""),format.raw/*513.3*/("""

	"""),format.raw/*515.2*/("""function validaHoras(frecuencia, siguienteMantencion, mantencionAnterior, origen) """),format.raw/*515.84*/("""{"""),format.raw/*515.85*/("""
	    """),format.raw/*516.6*/("""if (!frecuencia || !siguienteMantencion) """),format.raw/*516.47*/("""{"""),format.raw/*516.48*/("""
	        """),format.raw/*517.10*/("""return false;
	    """),format.raw/*518.6*/("""}"""),format.raw/*518.7*/("""
		"""),format.raw/*519.3*/("""if(calculoAutomatico == 1)"""),format.raw/*519.29*/("""{"""),format.raw/*519.30*/("""
			"""),format.raw/*520.4*/("""const isValid = siguienteMantencion % frecuencia === 0;
			return isValid;
		"""),format.raw/*522.3*/("""}"""),format.raw/*522.4*/("""else"""),format.raw/*522.8*/("""{"""),format.raw/*522.9*/("""
			"""),format.raw/*523.4*/("""return true;
		"""),format.raw/*524.3*/("""}"""),format.raw/*524.4*/("""

	"""),format.raw/*526.2*/("""}"""),format.raw/*526.3*/("""


	"""),format.raw/*529.2*/("""let auxiliar = 0;
	
	let extArray = new Array(".gif", ".jpg", ".png", ".jpeg", ".pdf", ".xls", ".doc", ".xlsx", ".docx", ".tar", ".zip", ".rar");
	const LimitAttach = (form, file, id_ot) => """),format.raw/*532.45*/("""{"""),format.raw/*532.46*/("""
		"""),format.raw/*533.3*/("""let allowSubmit = false;
		if (!file) return;
		while (file.indexOf("\\") != -1)"""),format.raw/*535.35*/("""{"""),format.raw/*535.36*/("""
			"""),format.raw/*536.4*/("""file = file.slice(file.indexOf("\\") + 1);
		"""),format.raw/*537.3*/("""}"""),format.raw/*537.4*/("""
		"""),format.raw/*538.3*/("""let extencion = file.slice(file.lastIndexOf(".")).toLowerCase();
		for (var i = 0; i < extArray.length; i++) """),format.raw/*539.45*/("""{"""),format.raw/*539.46*/("""
			"""),format.raw/*540.4*/("""if (extArray[i] == extencion) """),format.raw/*540.34*/("""{"""),format.raw/*540.35*/(""" """),format.raw/*540.36*/("""allowSubmit = true; break; """),format.raw/*540.63*/("""}"""),format.raw/*540.64*/("""
		"""),format.raw/*541.3*/("""}"""),format.raw/*541.4*/("""
		"""),format.raw/*542.3*/("""if (allowSubmit) """),format.raw/*542.20*/("""{"""),format.raw/*542.21*/("""
			"""),format.raw/*543.4*/("""var file = $("#docAdjunto_"+id_ot)[0].files[0];
			var fileSize = file.size; //tamaño del archivo
			if(fileSize>100000000)"""),format.raw/*545.26*/("""{"""),format.raw/*545.27*/("""
				"""),format.raw/*546.5*/("""alert("Se permiten únicamente archivos que no superen los 100 MB,"
				+" el que se intenta subir pesa: "+Math.round(file.size/10)/100+" KB");
				form.docAdjunto.value="";
			"""),format.raw/*549.4*/("""}"""),format.raw/*549.5*/("""
		"""),format.raw/*550.3*/("""}"""),format.raw/*550.4*/("""else"""),format.raw/*550.8*/("""{"""),format.raw/*550.9*/("""
			"""),format.raw/*551.4*/("""alert("Se permiten únicamente archivos con la extención: " 
			+ (extArray.join("  ")) + "\nPor favor, seleccione otro archivo "
			+ "e intente de nuevo.");
			form.docAdjunto.value="";
		"""),format.raw/*555.3*/("""}"""),format.raw/*555.4*/("""
	"""),format.raw/*556.2*/("""}"""),format.raw/*556.3*/("""
	
	
	
	"""),format.raw/*560.2*/("""const actualizaPlan = (id_tipoPlan, id_equipo, campo, valor) => """),format.raw/*560.66*/("""{"""),format.raw/*560.67*/("""
	    """),format.raw/*561.6*/("""var formData = new FormData();
	    formData.append('id_tipoPlan', id_tipoPlan);
	    formData.append('id_equipo', id_equipo);
	    formData.append('campo', campo);
	    formData.append('valor', valor);
	
	    $.ajax("""),format.raw/*567.13*/("""{"""),format.raw/*567.14*/("""
	        """),format.raw/*568.10*/("""url: "/actualCampoPlanMantencionAjax/",
	        type: "POST",
	        method: "POST",
	        data: formData,
	        cache: false,
	        contentType: false,
	        processData: false,
	        success: function (respuesta) """),format.raw/*575.40*/("""{"""),format.raw/*575.41*/("""
	            """),format.raw/*576.14*/("""calcFechaProxima();
	        """),format.raw/*577.10*/("""}"""),format.raw/*577.11*/("""
	    """),format.raw/*578.6*/("""}"""),format.raw/*578.7*/(""");
	"""),format.raw/*579.2*/("""}"""),format.raw/*579.3*/("""





	"""),format.raw/*585.2*/("""function actualizarProximaMantencion(index, id_tipoPlan, id_equipo, origen) """),format.raw/*585.78*/("""{"""),format.raw/*585.79*/("""
	    """),format.raw/*586.6*/("""const lectActual = parseFloat($("#lectActual_" + index).val()?.replace(/,/g, "") || 0);
	
	    if (index < $(".cadaNEstimado").length) """),format.raw/*588.46*/("""{"""),format.raw/*588.47*/("""
	        """),format.raw/*589.10*/("""const frecuencia = parseFloat($(".cadaNEstimado")[index]?.value.replace(/,/g, "") || 0);
	
	        if (frecuencia > 0) """),format.raw/*591.30*/("""{"""),format.raw/*591.31*/("""
				"""),_display_(if(mapDiccionario.get("nEmpresa").equals("GYL"))/*592.54*/{_display_(Seq[Any](format.raw/*592.55*/("""
					"""),format.raw/*593.6*/("""return("no aplica");
				""")))} else {null} ),format.raw/*594.6*/("""
	            """),format.raw/*595.14*/("""const proximaMantencion = determinaProxima(lectActual, frecuencia);
	            let mantencionAnterior = parseFloat($("#proximaMantencion_" + index).val()?.replace(/,/g, "") || 0);
	
	            const isValid = validaHoras(frecuencia, proximaMantencion, mantencionAnterior, origen);
	
	            if (isValid) """),format.raw/*600.27*/("""{"""),format.raw/*600.28*/("""
	                """),format.raw/*601.18*/("""$("#proximaMantencion_" + index).val(proximaMantencion);
	                actualizaPorCampoPlan(id_tipoPlan, id_equipo, "proximaMantencion", proximaMantencion);
	                calcFechaProxima(index);
	            """),format.raw/*604.14*/("""}"""),format.raw/*604.15*/(""" """),format.raw/*604.16*/("""else """),format.raw/*604.21*/("""{"""),format.raw/*604.22*/("""
	                """),format.raw/*605.18*/("""alert(`Fila $"""),format.raw/*605.31*/("""{"""),format.raw/*605.32*/("""index"""),format.raw/*605.37*/("""}"""),format.raw/*605.38*/(""": Validación fallida.`);
	            """),format.raw/*606.14*/("""}"""),format.raw/*606.15*/("""
	        """),format.raw/*607.10*/("""}"""),format.raw/*607.11*/("""

	    """),format.raw/*609.6*/("""}"""),format.raw/*609.7*/(""" """),format.raw/*609.8*/("""else """),format.raw/*609.13*/("""{"""),format.raw/*609.14*/("""
	        """),format.raw/*610.10*/("""alert(`Índice fuera de rango: $"""),format.raw/*610.41*/("""{"""),format.raw/*610.42*/("""index"""),format.raw/*610.47*/("""}"""),format.raw/*610.48*/("""`);
	    """),format.raw/*611.6*/("""}"""),format.raw/*611.7*/("""
	"""),format.raw/*612.2*/("""}"""),format.raw/*612.3*/("""
	
"""),format.raw/*614.1*/("""// FUNCION PARA RECALCULAR FECHA CUANDO arametro.planes_calculaProximaMant ES 0, ES DECIR LA PROXIMA MATENCION ES MANIPULABLE DE FORMA MANUAL
	
	function recalcularFechaEstimada(index, id_tipoPlan, id_equipo) """),format.raw/*616.66*/("""{"""),format.raw/*616.67*/("""
	    """),format.raw/*617.6*/("""// Leer los valores de la fila correspondiente
	    const proximaMantencion = parseFloat($("#proximaMantencion_" + index).val()?.replace(/,/g, "") || 0);
	    const fechaReset = $(".fechaReset")[index].value;
	    const consumoPromedio = parseFloat($(".consumoEstimadoPorMes")[index].value?.replace(/,/g, "") || 0);

	    // Validar valores
	    if (proximaMantencion <= 0 || consumoPromedio <= 0) """),format.raw/*623.58*/("""{"""),format.raw/*623.59*/("""
	        """),format.raw/*624.10*/("""alert("El valor ingresado y el consumo promedio deben ser mayor que cero.");
	        return;
	    """),format.raw/*626.6*/("""}"""),format.raw/*626.7*/("""
	
	    """),format.raw/*628.6*/("""// Calcular nueva fecha estimada
	    let diasASumar = (proximaMantencion / consumoPromedio) * 30;
	    let nuevaFecha;
	
	    try """),format.raw/*632.10*/("""{"""),format.raw/*632.11*/("""
	        """),format.raw/*633.10*/("""let aux = fechaReset.split("-");
	        nuevaFecha = new Date(aux[0], parseInt(aux[1]) - 1, aux[2]);
	        nuevaFecha.setDate(nuevaFecha.getDate() + diasASumar);
	    """),format.raw/*636.6*/("""}"""),format.raw/*636.7*/(""" """),format.raw/*636.8*/("""catch (error) """),format.raw/*636.22*/("""{"""),format.raw/*636.23*/("""
	        """),format.raw/*637.10*/("""nuevaFecha = new Date(); // Fecha actual si hay errores
	    """),format.raw/*638.6*/("""}"""),format.raw/*638.7*/("""
	
	    """),format.raw/*640.6*/("""// Actualizar la fecha estimada en la interfaz
	    $(".fechaEstimada")[index].value = fechaAAMMDD(nuevaFecha);

	    // Guardar el cambio en la base de datos
	    actualizaPorCampoPlan(id_tipoPlan, id_equipo, "proximaMantencion", proximaMantencion);
	"""),format.raw/*645.2*/("""}"""),format.raw/*645.3*/("""


	
	
	
"""),format.raw/*651.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,allPlan:List[tables.PlanMantencion],listAtributos:List[String],listCompra:List[String],listTipoMant:List[tables.TipoMantencion],listTipoTrab:List[tables.TipoTrabajo],listMoneda:List[tables.Moneda],listProveedor:List[tables.Proveedor],listDetalle:List[tables.HojaVida],listTiposPlan:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,allPlan,listAtributos,listCompra,listTipoMant,listTipoTrab,listMoneda,listProveedor,listDetalle,listTiposPlan)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.PlanMantencion],List[String],List[String],List[tables.TipoMantencion],List[tables.TipoTrabajo],List[tables.Moneda],List[tables.Proveedor],List[tables.HojaVida],List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,allPlan,listAtributos,listCompra,listTipoMant,listTipoTrab,listMoneda,listProveedor,listDetalle,listTiposPlan) => apply(mapDiccionario,mapPermiso,userMnu,allPlan,listAtributos,listCompra,listTipoMant,listTipoTrab,listMoneda,listProveedor,listDetalle,listTiposPlan)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuPlanes/hojaVidaMantencionHoja.scala.html
                  HASH: 94c6eab513c7f79b341e1695315040379f111ec6
                  MATRIX: 1212->1|1721->417|1748->418|1856->499|1884->500|1925->514|2028->590|2057->591|2089->596|2132->613|2149->621|2189->623|2218->626|2287->674|2316->676|2393->727|2486->799|2516->802|2950->1209|2966->1216|3006->1235|3050->1252|3073->1266|3105->1277|3155->1300|3174->1310|3202->1317|3268->1356|3287->1366|3315->1373|3382->1413|3401->1423|3429->1430|3524->1498|3540->1505|3581->1525|3651->1568|3670->1578|3698->1585|3796->1656|3835->1657|3871->1666|3932->1700|3951->1710|3979->1717|4086->1805|4125->1806|4160->1814|4202->1826|4236->1833|4303->1873|4322->1883|4350->1890|4445->1958|4461->1965|4502->1985|4570->2026|4589->2036|4617->2043|4826->2226|4869->2253|4908->2254|4946->2265|4972->2270|5014->2281|5048->2288|5858->3072|5917->3115|5957->3117|5995->3127|6137->3242|6151->3247|6184->3259|6233->3280|6248->3285|6285->3300|6328->3314|6546->3503|6576->3504|6642->3541|6680->3551|6699->3560|6753->3592|6817->3627|6847->3628|6877->3629|6911->3634|6941->3635|7007->3672|7050->3687|7077->3692|7110->3697|7125->3702|7157->3712|7243->3769|7273->3770|7334->3803|7353->3812|7407->3844|7575->3984|7590->3989|7633->4009|7682->4030|7697->4035|7736->4052|7779->4066|7973->4232|8000->4237|8062->4271|8077->4276|8112->4289|8452->4601|8479->4606|8512->4611|8527->4616|8559->4626|8679->4718|8706->4723|8739->4728|8754->4733|8788->4745|8821->4750|8837->4755|8870->4765|9034->4901|9049->4906|9085->4920|9128->4935|9143->4940|9179->4954|9215->4962|9357->5076|9372->5081|9416->5103|9459->5118|9474->5123|9518->5145|9554->5153|9731->5301|9773->5303|9814->5315|10037->5510|10064->5515|10124->5547|10139->5552|10179->5570|10458->5821|10473->5826|10507->5838|10540->5843|10556->5848|10589->5858|10672->5923|10712->5924|10753->5936|10980->6135|11007->6140|11067->6172|11082->6177|11122->6195|11212->6254|11257->6270|11447->6429|11481->6435|11786->6712|11803->6719|11842->6736|13272->8139|13312->8162|13352->8163|13388->8171|13455->8210|13468->8213|13499->8222|13530->8225|13543->8228|13568->8231|13634->8269|13647->8272|13678->8281|13709->8284|13722->8287|13765->8308|13831->8346|13844->8349|13875->8358|13906->8361|13919->8364|13959->8382|14022->8417|14035->8420|14066->8429|14097->8432|14110->8435|14150->8453|14318->8593|14331->8596|14356->8599|14442->8657|14455->8660|14495->8678|14526->8681|14539->8684|14582->8705|14631->8726|14674->8752|14714->8753|14755->8765|14799->8781|14814->8786|14839->8789|14870->8792|14885->8797|14914->8804|14967->8825|15006->8835|15183->8984|15196->8987|15221->8990|15301->9042|15314->9045|15348->9057|15379->9060|15392->9063|15429->9078|15478->9099|15522->9126|15562->9127|15603->9139|15647->9155|15662->9160|15691->9167|15722->9170|15737->9175|15766->9182|15819->9203|15858->9213|16057->9383|16087->9384|16130->9398|16168->9408|16187->9417|16240->9448|16284->9463|16314->9464|16344->9465|16378->9470|16408->9471|16451->9485|16499->9505|16512->9508|16537->9511|16602->9547|16632->9548|16682->9570|16701->9579|16754->9610|16924->9752|16937->9755|16962->9758|17045->9813|17058->9816|17095->9831|17126->9834|17139->9837|17179->9855|17228->9876|17271->9902|17311->9903|17352->9915|17396->9931|17411->9936|17436->9939|17467->9942|17482->9947|17511->9954|17564->9975|17603->9985|17780->10134|17793->10137|17818->10140|17896->10190|17909->10193|17941->10203|17972->10206|17985->10209|18022->10224|18071->10245|18112->10269|18152->10270|18193->10282|18237->10298|18252->10303|18277->10306|18308->10309|18323->10314|18354->10323|18407->10344|18446->10354|18610->10490|18623->10493|18655->10503|18864->10684|18877->10687|18902->10690|19093->10853|19106->10856|19131->10859|19212->10912|19225->10915|19260->10928|19291->10931|19304->10934|19344->10952|19393->10973|19437->11000|19477->11001|19518->11013|19562->11029|19577->11034|19602->11037|19633->11040|19648->11045|19679->11054|19732->11075|19771->11085|19935->11221|19948->11224|19986->11240|20111->11337|20124->11340|20149->11343|20298->11464|20338->11465|20378->11476|20505->11574|20535->11575|20579->11590|20627->11610|20640->11613|20665->11616|20734->11656|20764->11657|20801->11674|20841->11675|20881->11686|21009->11785|21039->11786|21083->11801|21121->11811|21140->11820|21196->11854|21241->11870|21271->11871|21301->11872|21335->11877|21365->11878|21409->11893|21457->11913|21470->11916|21495->11919|21564->11959|21594->11960|21645->11983|21664->11992|21720->12026|21765->12039|21802->12048|21901->12119|21914->12122|21939->12125|22099->12257|22112->12260|22144->12270|22233->12331|22246->12334|22271->12337|22424->12462|22464->12463|22506->12476|22574->12524|22614->12525|22656->12538|22734->12584|22778->12599|22841->12634|22854->12637|22879->12640|23003->12736|23016->12739|23041->12742|23097->12770|23110->12773|23135->12776|23240->12853|23280->12854|23321->12866|23383->12900|23396->12903|23431->12916|23569->13009|23608->13019|23815->13197|23845->13198|23888->13212|23926->13222|23945->13231|23999->13263|24043->13278|24073->13279|24103->13280|24137->13285|24167->13286|24210->13300|24258->13320|24271->13323|24296->13326|24362->13363|24392->13364|24442->13386|24461->13395|24515->13427|24636->13520|24649->13523|24674->13526|24816->13640|24829->13643|24861->13653|24950->13714|24963->13717|24988->13720|25080->13784|25093->13787|25118->13790|25508->14152|25521->14155|25546->14158|25601->14185|25614->14188|25649->14201|25726->14247|25760->14253|26618->15080|26651->15085|26742->15147|26772->15148|26803->15151|26919->15239|26948->15240|27014->15277|27044->15278|27078->15284|27212->15389|27242->15390|27281->15400|27709->15799|27739->15800|27782->15814|27869->15872|27899->15873|27929->15874|27963->15879|27993->15880|28036->15894|28149->15978|28179->15979|28226->15997|28440->16182|28470->16183|28500->16184|28543->16198|28573->16199|28620->16217|28678->16246|28708->16247|28753->16263|28845->16326|28875->16327|28909->16333|28938->16334|28968->16336|28997->16337|29030->16342|29093->16376|29123->16377|29157->16383|29258->16455|29288->16456|29326->16465|29356->16466|29485->16567|29514->16568|29548->16574|29649->16646|29679->16647|29715->16655|29961->16872|29991->16873|30030->16883|30292->17116|30322->17117|30365->17131|30423->17160|30453->17161|30487->17167|30516->17168|30548->17172|30577->17173|30609->17177|30694->17233|30724->17234|30755->17237|30948->17401|30978->17402|31020->17415|31285->17651|31315->17652|31351->17660|31380->17661|31417->17670|31447->17671|31479->17675|31508->17676|31541->17681|31627->17738|31657->17739|31691->17745|31866->17891|31896->17892|31935->17902|32023->17961|32053->17962|32096->17976|32155->18006|32185->18007|32232->18025|32491->18255|32521->18256|32554->18261|32611->18289|32641->18290|32675->18296|32898->18491|32927->18492|32966->18502|32996->18503|33030->18509|33059->18510|33089->18512|33118->18513|33156->18523|33350->18688|33392->18690|33423->18693|33494->18720|33525->18723|33604->18773|33634->18774|33665->18777|33720->18803|33750->18804|33782->18808|33832->18829|33862->18830|33895->18835|33980->18892|34009->18893|34041->18897|34105->18932|34135->18933|34168->18938|34259->19001|34288->19002|34317->19003|34351->19008|34381->19009|34414->19014|34567->19139|34596->19140|34627->19143|34656->19144|34688->19148|34717->19149|34749->19153|34797->19172|34827->19173|34860->19178|34932->19222|34961->19223|34995->19228|35025->19229|35058->19234|35121->19269|35150->19270|35181->19273|35210->19274|35241->19277|35270->19278|35301->19281|35412->19363|35442->19364|35476->19370|35546->19411|35576->19412|35615->19422|35662->19441|35691->19442|35722->19445|35777->19471|35807->19472|35839->19476|35944->19553|35973->19554|36005->19558|36034->19559|36066->19563|36109->19578|36138->19579|36169->19582|36198->19583|36230->19587|36449->19777|36479->19778|36510->19781|36619->19861|36649->19862|36681->19866|36754->19911|36783->19912|36814->19915|36952->20024|36982->20025|37014->20029|37073->20059|37103->20060|37133->20061|37189->20088|37219->20089|37250->20092|37279->20093|37310->20096|37356->20113|37386->20114|37418->20118|37570->20241|37600->20242|37633->20247|37837->20423|37866->20424|37897->20427|37926->20428|37958->20432|37987->20433|38019->20437|38236->20626|38265->20627|38295->20629|38324->20630|38360->20638|38453->20702|38483->20703|38517->20709|38763->20926|38793->20927|38832->20937|39094->21170|39124->21171|39167->21185|39225->21214|39255->21215|39289->21221|39318->21222|39350->21226|39379->21227|39414->21234|39519->21310|39549->21311|39583->21317|39747->21452|39777->21453|39816->21463|39965->21583|39995->21584|40077->21638|40117->21639|40151->21645|40221->21671|40264->21685|40606->21998|40636->21999|40683->22017|40928->22233|40958->22234|40988->22235|41022->22240|41052->22241|41099->22259|41141->22272|41171->22273|41205->22278|41235->22279|41302->22317|41332->22318|41371->22328|41401->22329|41436->22336|41465->22337|41494->22338|41528->22343|41558->22344|41597->22354|41657->22385|41687->22386|41721->22391|41751->22392|41788->22401|41817->22402|41847->22404|41876->22405|41907->22408|42145->22617|42175->22618|42209->22624|42636->23022|42666->23023|42705->23033|42832->23132|42861->23133|42897->23141|43057->23272|43087->23273|43126->23283|43326->23455|43355->23456|43384->23457|43427->23471|43457->23472|43496->23482|43585->23543|43614->23544|43650->23552|43930->23804|43959->23805|43996->23814
                  LINES: 28->1|35->4|36->5|38->7|38->7|39->8|42->11|42->11|43->12|46->15|46->15|46->15|48->17|48->17|49->18|50->19|50->19|51->20|64->33|64->33|64->33|65->34|65->34|65->34|66->35|66->35|66->35|68->37|68->37|68->37|70->39|70->39|70->39|74->43|74->43|74->43|76->45|76->45|76->45|79->48|79->48|80->49|80->49|80->49|80->49|83->52|83->52|84->53|85->54|86->55|88->57|88->57|88->57|92->61|92->61|92->61|94->63|94->63|94->63|105->74|105->74|105->74|106->75|106->75|107->76|108->77|127->96|127->96|127->96|128->97|130->99|130->99|130->99|131->100|131->100|131->100|132->101|135->104|135->104|136->105|136->105|136->105|136->105|137->106|137->106|137->106|137->106|137->106|138->107|138->107|138->107|138->107|138->107|138->107|139->108|139->108|140->109|140->109|140->109|143->112|143->112|143->112|144->113|144->113|144->113|145->114|148->117|148->117|149->118|149->118|149->118|154->123|154->123|154->123|154->123|154->123|155->124|155->124|155->124|155->124|155->124|155->124|155->124|155->124|158->127|158->127|158->127|159->128|159->128|159->128|160->129|162->131|162->131|162->131|163->132|163->132|163->132|164->133|165->134|165->134|166->135|169->138|169->138|170->139|170->139|170->139|174->143|174->143|174->143|174->143|174->143|174->143|176->145|176->145|177->146|180->149|180->149|181->150|181->150|181->150|184->153|187->156|191->160|193->162|200->169|200->169|200->169|234->203|234->203|234->203|235->204|236->205|236->205|236->205|236->205|236->205|236->205|237->206|237->206|237->206|237->206|237->206|237->206|238->207|238->207|238->207|238->207|238->207|238->207|239->208|239->208|239->208|239->208|239->208|239->208|243->212|243->212|243->212|244->213|244->213|244->213|244->213|244->213|244->213|245->214|245->214|245->214|246->215|246->215|246->215|246->215|246->215|246->215|246->215|247->216|248->217|252->221|252->221|252->221|253->222|253->222|253->222|253->222|253->222|253->222|254->223|254->223|254->223|255->224|255->224|255->224|255->224|255->224|255->224|255->224|256->225|257->226|261->230|261->230|262->231|262->231|262->231|262->231|263->232|263->232|263->232|263->232|263->232|264->233|264->233|264->233|264->233|265->234|265->234|266->235|266->235|266->235|270->239|270->239|270->239|271->240|271->240|271->240|271->240|271->240|271->240|272->241|272->241|272->241|273->242|273->242|273->242|273->242|273->242|273->242|273->242|274->243|275->244|279->248|279->248|279->248|280->249|280->249|280->249|280->249|280->249|280->249|281->250|281->250|281->250|282->251|282->251|282->251|282->251|282->251|282->251|282->251|283->252|284->253|288->257|288->257|288->257|292->261|292->261|292->261|296->265|296->265|296->265|297->266|297->266|297->266|297->266|297->266|297->266|298->267|298->267|298->267|299->268|299->268|299->268|299->268|299->268|299->268|299->268|300->269|301->270|305->274|305->274|305->274|308->277|308->277|308->277|311->280|311->280|312->281|313->282|313->282|314->283|314->283|314->283|314->283|315->284|315->284|316->285|316->285|317->286|318->287|318->287|319->288|319->288|319->288|319->288|320->289|320->289|320->289|320->289|320->289|321->290|321->290|321->290|321->290|322->291|322->291|323->292|323->292|323->292|324->293|325->294|327->296|327->296|327->296|328->297|328->297|328->297|329->298|329->298|329->298|331->300|331->300|332->301|333->302|333->302|334->303|335->304|336->305|336->305|336->305|336->305|337->306|337->306|337->306|338->307|338->307|338->307|340->309|340->309|341->310|341->310|341->310|341->310|344->313|345->314|350->319|350->319|351->320|351->320|351->320|351->320|352->321|352->321|352->321|352->321|352->321|353->322|353->322|353->322|353->322|354->323|354->323|355->324|355->324|355->324|358->327|358->327|358->327|359->328|359->328|359->328|360->329|360->329|360->329|361->330|361->330|361->330|372->341|372->341|372->341|372->341|372->341|372->341|375->344|376->345|403->372|408->377|409->378|409->378|410->379|412->381|412->381|414->383|414->383|415->384|418->387|418->387|419->388|426->395|426->395|427->396|428->397|428->397|428->397|428->397|428->397|429->398|431->400|431->400|432->401|435->404|435->404|435->404|435->404|435->404|436->405|437->406|437->406|439->408|440->409|440->409|441->410|441->410|442->411|442->411|445->414|445->414|445->414|446->415|446->415|446->415|446->415|446->415|448->417|448->417|452->421|452->421|452->421|454->423|460->429|460->429|461->430|468->437|468->437|469->438|470->439|470->439|471->440|471->440|472->441|472->441|474->443|474->443|474->443|475->444|479->448|479->448|480->449|487->456|487->456|488->457|488->457|489->458|489->458|490->459|490->459|493->462|493->462|493->462|494->463|496->465|496->465|497->466|497->466|497->466|498->467|498->467|498->467|499->468|502->471|502->471|503->472|503->472|503->472|504->473|507->476|507->476|508->477|508->477|509->478|509->478|510->479|510->479|517->486|518->487|518->487|519->488|520->489|522->491|522->491|522->491|523->492|523->492|523->492|524->493|524->493|524->493|525->494|527->496|527->496|528->497|528->497|528->497|529->498|531->500|531->500|531->500|531->500|531->500|532->501|535->504|535->504|536->505|536->505|536->505|536->505|537->506|537->506|537->506|538->507|539->508|539->508|539->508|539->508|540->509|541->510|541->510|542->511|542->511|544->513|544->513|546->515|546->515|546->515|547->516|547->516|547->516|548->517|549->518|549->518|550->519|550->519|550->519|551->520|553->522|553->522|553->522|553->522|554->523|555->524|555->524|557->526|557->526|560->529|563->532|563->532|564->533|566->535|566->535|567->536|568->537|568->537|569->538|570->539|570->539|571->540|571->540|571->540|571->540|571->540|571->540|572->541|572->541|573->542|573->542|573->542|574->543|576->545|576->545|577->546|580->549|580->549|581->550|581->550|581->550|581->550|582->551|586->555|586->555|587->556|587->556|591->560|591->560|591->560|592->561|598->567|598->567|599->568|606->575|606->575|607->576|608->577|608->577|609->578|609->578|610->579|610->579|616->585|616->585|616->585|617->586|619->588|619->588|620->589|622->591|622->591|623->592|623->592|624->593|625->594|626->595|631->600|631->600|632->601|635->604|635->604|635->604|635->604|635->604|636->605|636->605|636->605|636->605|636->605|637->606|637->606|638->607|638->607|640->609|640->609|640->609|640->609|640->609|641->610|641->610|641->610|641->610|641->610|642->611|642->611|643->612|643->612|645->614|647->616|647->616|648->617|654->623|654->623|655->624|657->626|657->626|659->628|663->632|663->632|664->633|667->636|667->636|667->636|667->636|667->636|668->637|669->638|669->638|671->640|676->645|676->645|682->651
                  -- GENERATED --
              */
          