
package viewsMnuBodegas.html

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

object bodegaCrear extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template12[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Cliente],List[tables.Proyecto],List[tables.TipoBodega],List[tables.Regiones],List[tables.Sucursal],tables.Sucursal,String,List[tables.Moneda],List[tables.Rubro],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listClientes: List[tables.Cliente], listProyectos: List[tables.Proyecto], listTipoBodega: List[tables.TipoBodega], listRegiones: List[tables.Regiones],
listSucursal: List[tables.Sucursal], sucursal: tables.Sucursal, esPorSucursal: String, listMon: List[tables.Moneda], listRubro: List[tables.Rubro]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.1*/("""
    	
"""),_display_(/*6.2*/main("")/*6.10*/ {_display_(Seq[Any](format.raw/*6.12*/("""

"""),_display_(/*8.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*8.50*/("""

	"""),format.raw/*10.2*/("""<!-- MODAL CREA CLIENTES -->
		"""),_display_(/*11.4*/modalClienteNuevo(mapDiccionario, listRegiones)),format.raw/*11.51*/("""
		"""),format.raw/*12.3*/("""<script>
			const clienteGrabaAjax = (id_cliente) =>"""),format.raw/*13.44*/("""{"""),format.raw/*13.45*/("""
				"""),format.raw/*14.5*/("""$('#id_cliente').val(id_cliente);
				$('#rutCliente').val($("#clienteRut").val());
				$('#nombreCliente').val($("#clienteNickName").val());
				let tabla = document.getElementById('tablaListaCliente');
				let row = tabla.insertRow(tabla.rows.length);
				let cell = row.insertCell(0);
				cell.style.textAlign = "left";
				cell.innerHTML = $("#clienteRut").val();
				cell = row.insertCell(1);
				cell.style.textAlign = "left";
				cell.innerHTML = "<div id='cliente_" + id_cliente + "'>" + $("#clienteNickName").val() + "</div>";
				cell = row.insertCell(2);
				cell.style.textAlign = "left";
				cell.innerHTML = $("#clienteNombre").val();
				row.setAttribute("onClick", "seleccionaCliente('" + id_cliente + "')");
				row.setAttribute("data-dismiss", "modal");
				$("#nickNameCliente").val($("#clienteNickName").val());
			"""),format.raw/*31.4*/("""}"""),format.raw/*31.5*/("""
		"""),format.raw/*32.3*/("""</script>
	<!-- FIN MODAL CREA CLIENTES -->
	
	<!-- MODAL CREA PROYECTOS -->
		"""),_display_(/*36.4*/modalProyectoNuevo(mapDiccionario, listRegiones)),format.raw/*36.52*/("""
		"""),format.raw/*37.3*/("""<script>
			const proyectoGrabaAjax = (id_proyecto) =>"""),format.raw/*38.46*/("""{"""),format.raw/*38.47*/("""
				"""),format.raw/*39.5*/("""$('#id_proyecto').val(id_proyecto);
				$('#nombreProyecto').val($("#proyectoNickName").val());
				let tabla = document.getElementById('tablaListaProyecto');
				let row = tabla.insertRow(tabla.rows.length);
				let cell = row.insertCell(0);
				cell.style.textAlign = "left";
				cell.innerHTML = "<div id='proyecto_" + id_proyecto + "'>" + $("#proyectoNickName").val() + "</div>";
				cell = row.insertCell(1);
				cell.style.textAlign = "left";
				cell.innerHTML = $("#proyectoNombre").val();
				cell = row.insertCell(2);
				cell.style.textAlign = "left";
				cell.innerHTML = "";
				row.setAttribute("onClick", "seleccionaProyecto('" + id_proyecto + "')");
				row.setAttribute("data-dismiss", "modal");
				$("#nickNameProyecto").val($("#proyectoNickName").val());
			"""),format.raw/*55.4*/("""}"""),format.raw/*55.5*/("""
		"""),format.raw/*56.3*/("""</script>
	<!-- FIN MODAL CREA PROYECTOS -->
	
	<!-- MODAL CREA PROPIETARIOS -->
		"""),_display_(/*60.4*/modalPropietarioNuevo(mapDiccionario, listRegiones)),format.raw/*60.55*/("""
		"""),format.raw/*61.3*/("""<script>
			const propietarioGrabaAjax = (id_propietario) =>"""),format.raw/*62.52*/("""{"""),format.raw/*62.53*/("""
				"""),format.raw/*63.5*/("""$('#id_propietario').val(id_propietario);
				$('#rutPropietario').val($("#propietarioRut").val());
				$('#nombrePropietario').val($("#propietarioNickName").val());
				let tabla = document.getElementById('tablaListaPropietario');
				let row = tabla.insertRow(tabla.rows.length);
				let cell = row.insertCell(0);
				cell.style.textAlign = "left";
				cell.innerHTML = $("#propietarioRut").val();
				cell = row.insertCell(1);
				cell.style.textAlign = "left";
				cell.innerHTML = "<div id='propietario_" + id_propietario + "'>" + $("#propietarioNickName").val() + "</div>";
				cell = row.insertCell(2);
				cell.style.textAlign = "left";
				cell.innerHTML = $("#propietarioNombre").val();
				row.setAttribute("onClick", "seleccionaPropietario('" + id_propietario + "')");
				row.setAttribute("data-dismiss", "modal");
				$("#nickNamePropietario").val($("#propietarioNickName").val());
			"""),format.raw/*80.4*/("""}"""),format.raw/*80.5*/("""
		"""),format.raw/*81.3*/("""</script>
	<!-- FIN MODAL CREA CLIENTES -->
	
	<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*85.4*/barraTitulo(mapDiccionario, "AGREGAR "+mapDiccionario.get("BODEGA")+"/PROYECTO", "(CREAR)")),format.raw/*85.95*/("""
		"""),format.raw/*86.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<form id="form" method="post" action="/bodegaGraba/" onsubmit="return validarForm();">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<tr>
							<td style="text-align:left;">NOMBRE """),_display_(/*91.45*/mapDiccionario/*91.59*/.get("BODEGA")),format.raw/*91.73*/("""/PROYECTO: </td>
							<td style="text-align:left;">
								<input type="hidden" name="id_bodegaEmpresa" value="0">
								<input type="text" class="form-control left"
									id="nombre"
									name="nombre"
									autocomplete="off"
									onkeydown="return sinReservados(window.event)"
									maxlength="100"
									onchange="value = value.trim(); verificaNombre(value);"
									required>
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">SUCURSAL: </td>
							<td style="text-align:left;">
								"""),_display_(if(esPorSucursal.equals("1"))/*107.39*/{_display_(Seq[Any](format.raw/*107.40*/("""
									"""),format.raw/*108.10*/("""<select class="custom-select" 
										name="id_sucursal" 
										required>
										<option value=""""),_display_(/*111.27*/sucursal/*111.35*/.getId()),format.raw/*111.43*/("""">"""),_display_(/*111.46*/sucursal/*111.54*/.getNombre()),format.raw/*111.66*/("""</option>
									</select>
									
								""")))}else/*114.14*/{_display_(Seq[Any](format.raw/*114.15*/("""
									"""),format.raw/*115.10*/("""<select class="custom-select" 
										name="id_sucursal" 
										onchange="vistaSelectComercial(value)"
										required>
										<option value=""></option>
										"""),_display_(/*120.12*/for(lista <- listSucursal) yield /*120.38*/{_display_(Seq[Any](format.raw/*120.39*/("""
											"""),format.raw/*121.12*/("""<option value=""""),_display_(/*121.28*/lista/*121.33*/.getId()),format.raw/*121.41*/("""">"""),_display_(/*121.44*/lista/*121.49*/.getNombre()),format.raw/*121.61*/("""</option>
										""")))}),format.raw/*122.12*/("""
									"""),format.raw/*123.10*/("""</select>
								""")))}),format.raw/*124.10*/("""
							"""),format.raw/*125.8*/("""</td>
						</tr>
						<tr>
							<td style="text-align:left;">TIPO DE """),_display_(/*128.46*/mapDiccionario/*128.60*/.get("BODEGA")),format.raw/*128.74*/(""": </td>
							<td style="text-align:left;">
								<select class="custom-select" 
									name="id_tipoBodega" 
									onchange='cambiaDatos(value); if(""""),_display_(/*132.45*/esPorSucursal),format.raw/*132.58*/("""" == "1")"""),format.raw/*132.67*/("""{"""),format.raw/*132.68*/("""vistaSelectComercial(""""),_display_(/*132.91*/sucursal/*132.99*/.getId()),format.raw/*132.107*/("""")"""),format.raw/*132.109*/("""}"""),format.raw/*132.110*/("""'
									required>
									<option value=""></option>
									"""),_display_(/*135.11*/for(lista <- listTipoBodega) yield /*135.39*/{_display_(Seq[Any](format.raw/*135.40*/("""
										"""),format.raw/*136.11*/("""<option value=""""),_display_(/*136.27*/lista/*136.32*/.getId()),format.raw/*136.40*/("""">"""),_display_(/*136.43*/lista/*136.48*/.getNombre()),format.raw/*136.60*/("""</option>
									""")))}),format.raw/*137.11*/("""
								"""),format.raw/*138.9*/("""</select>
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">RUBRO: </td>
							<td style="text-align:left;">
								<select class="custom-select" id="id_rubro" name="id_rubro" required>"
									<option value="0"></option>
									"""),_display_(/*146.11*/for(lista <- listRubro) yield /*146.34*/ {_display_(Seq[Any](format.raw/*146.36*/("""
									"""),format.raw/*147.10*/("""<option value=""""),_display_(/*147.26*/lista/*147.31*/.getId()),format.raw/*147.39*/("""">"""),_display_(/*147.42*/lista/*147.47*/.getNombre()),format.raw/*147.59*/("""</option>
									""")))}),format.raw/*148.11*/("""
								"""),format.raw/*149.9*/("""</select>
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">CORREO ENVÍO:</td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
								name="email_envio"
								id="email_envio"
								autocomplete="off"
								maxlength="1000"
								onchange="value = value.trim();">
								<small class="text-muted">
									Puedes ingresar varios.
								</small>
							</td>
						</tr>

						<tr>
							<td style="text-align:left;">CORREO CC:</td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
								name="email_ccopia"
								id="email_ccopia"
								autocomplete="off"
								maxlength="1000"
								onchange="value = value.trim();">
								<small class="text-muted">
									Copia visible para el receptor.
								</small>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<table id="datosInterna" class="table table-sm table-bordered table-condensed table-fluid" hidden>
									<tr>
										<td style="text-align:left;">PROPIETARIO: </td>
										<td style="text-align:left;">
											<input type="hidden" name="id_propietario" id="id_propietario" value="0">
											<input type="text" class="form-control left"
												id="nickNamePropietario"
												onclick='$("#listaPropietario").modal("show")'
												style="background:white"
												readonly>
										</td>
									</tr>
								</table>
								<table id="datosExterna" class="table table-sm table-bordered table-condensed table-fluid" hidden>
									<tr>
										<td style="text-align:left;">CLIENTE: </td>
										<td style="text-align:left;">
											<input type="hidden" name="id_cliente" id="id_cliente" value="0">
											<input type="text" class="form-control left"
												id="nickNameCliente"
												onclick='$("#listaCliente").modal("show")'
												style="background:white"
												readonly>
										</td>
									</tr>
									<tr>
										<td style="text-align:left;">PROYECTO/OBRA: </td>
										<td style="text-align:left;">
											<input type="hidden" name="id_proyecto" id="id_proyecto" value="0">
											<input type="text" class="form-control left"
												id="nickNameProyecto"
												onclick='$("#listaProyecto").modal("show")'
												style="background:white"
												readonly>
										</td>
									</tr>
									<tr>
										<td style="text-align:left;">COMERCIAL: </td>
										<td style="text-align:left;">
											<div id="selectComercial">
												<input type="hidden" name="id_comercial" value="0">
												<select class="custom-select" readonly>
													<option value="0">Sin Comercial</option>
												</select>
											</div>
										</td>
									</tr>


									<tr>
										<td style="text-align:left;">Cargo fijo inicial (CFI): </td>
										<td style="text-align:left;">
											<input type="text" class="form-control left"
												name="cfi"
												autocomplete="off"
												value="0.00 %"
												onfocus="value=value.replace(/,/g,'').replace(/%/g,'').replace(/ /g,'')" 
												onblur = "value = formatPorcentaje(value);"
												onkeydown="return ingresoDouble(window.event)">
										</td>
									</tr>
									<tr>
										<td style="text-align:left;">Se cobra el d&iacute;a de despacho: </td>
										<td style="text-align:left;">
											<select class="custom-select" 
												name="cobraDiaDespacho">
												<option value="1">SI</option>
												<option value="0">NO</option>
											</select>
										</td>
									</tr>
									<tr>
										<td style="text-align:left;">Base de C&aacute;lculo: </td>
										<td style="text-align:left;">
											<select class="custom-select" 
												name="baseCalculo">
												<option value='1'>DIARIO (por d&iacute;a exacto de cada mes)</option>
												<option value='2'>MENSUAL (meses de 30 d&iacute;as)</option>
											</select>
										</td>
									</tr>
									
									
									"""),_display_(if(mapPermiso.get("parametro.fijaTasas")!=null && mapPermiso.get("parametro.fijaTasas").equals("1"))/*266.111*/ {_display_(Seq[Any](format.raw/*266.113*/("""
										"""),format.raw/*267.11*/("""<tr>
											<td style="text-align:left;">Fijar tasas de cambio: </td>
											<td style="text-align:left;">
												<table id="fijaTasas" class="table table-sm table-bordered table-condensed table-fluid">
													<thead>
														<tr>
															<th width="30%">Mon</th>
															<th>Tasa</th>
															<th width="10%">Del</th>
														</tr>
													</thead>
													<tbody>
														<tr>
															<td>
																<select class="custom-select" name="id_moneda[]" onchange="selMon(value)">
																	<option value='0'></option>
																	"""),_display_(/*283.19*/for(lista <- listMon) yield /*283.40*/{_display_(Seq[Any](format.raw/*283.41*/("""
																		"""),format.raw/*284.19*/("""<option value=""""),_display_(/*284.35*/lista/*284.40*/.id),format.raw/*284.43*/(""" """),format.raw/*284.44*/("""_ """),_display_(/*284.47*/lista/*284.52*/.nickName),format.raw/*284.61*/("""">"""),_display_(/*284.64*/lista/*284.69*/.nickName),format.raw/*284.78*/("""</option>
																	""")))}),format.raw/*285.19*/("""
																"""),format.raw/*286.17*/("""</select>
															</td>
															<td></td>
															<td></td>
														</tr>
													</tbody>
												</table>
											</td>
										</tr>
									""")))} else {null} ),format.raw/*295.11*/("""
									
									"""),_display_(if(mapPermiso.get("parametro.ivaPorBodega")!=null && mapPermiso.get("parametro.ivaPorBodega").equals("1"))/*297.117*/ {_display_(Seq[Any](format.raw/*297.119*/("""
	    								"""),format.raw/*298.14*/("""<tr>
											<td style="text-align:left;">PEP: </td>
											<td style="text-align:left;">
												<input type="text" class="form-control left"
													name="pep"
													autocomplete="off"
													maxlength="50"
													onchange="value = value.trim();">
											</td>
										</tr>
										<tr>
											<td style="text-align:left;">IVA: </td>
											<td style="text-align:left;">
												<input type="text" class="form-control left"
													name="ivaBodega"
													autocomplete="off"
													value="0.00 %"
													onfocus="value=value.replace(/,/g,'').replace(/%/g,'').replace(/ /g,'')" 
													onblur = "value = formatPorcentaje(value);"
													onkeydown="return ingresoDouble(window.event)">
											</td>
										</tr>
    								""")))} else {null} ),format.raw/*320.14*/("""
									"""),format.raw/*321.10*/("""<tr>
										<td style="text-align:left;">Trato de Devoluciones: </td>
										<td style="text-align:left;">
											<select class="custom-select" 
												name="tratoDevoluciones">
												<option value='1'>A partir del 1er d&iacute;a</option>
												<option value='2'>A partir del 2do d&iacute;a</option>
											</select>
										</td>
									</tr>
									<tr>
										<td style="text-align:left;">D&iacute;as de gracia en el env&iacute;o: </td>
										<td style="text-align:left;">
											<input type="text" class="form-control left"
												name="nDiasEnvio"
												autocomplete="off"
												value="0"
												onkeydown="return ingresoInt(window.event)"
												onchange="if(value=='') value=0;">
										</td>
									</tr>
									<tr>
										<td style="text-align:left;">D&iacute;as de gracia al regreso: </td>
										<td style="text-align:left;">
											<input type="text" class="form-control left"
												name="nDiasRegreso"
												autocomplete="off"
												value="0"
												onkeydown="return ingresoInt(window.event)"
												onchange="if(value=='') value=0;">
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<div class="noprint">
						<div class="row justify-content-md-center" >
							<div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
								<input type="submit" id="btnSubmit"  value='GRABAR """),_display_(/*360.61*/mapDiccionario/*360.75*/.get("BODEGA")),format.raw/*360.89*/("""/PROYECTO' class="btn btn-success btn-sm rounded-pill btn-block">
							</div>
							<div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
								<input type="button" value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/bodegaAdministrar/';">
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<div id='listaCliente' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>Lista de selecci&oacute;n cliente</h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<div class="noprint" align="center">
						<a href="#" onclick="$('#listaCliente').modal('hide'); $('#modalClienteNuevo').modal('show');">
							<kbd style="background-color: #73C6B6">Agregar Nuevo</kbd>
						</a>
					</div>
					<table id="tablaListaCliente" class='table table-sm table-bordered table-condensed table-hover table-fluid'>
						<thead style="background-color: #eeeeee">
							<TR> 
								<TH>NOMBRE CORTO (NICK NAME)</TH>
						        <TH>"""),_display_(/*391.20*/mapDiccionario/*391.34*/.get("Region")),format.raw/*391.48*/("""</TH>
						        <TH>"""),_display_(/*392.20*/mapDiccionario/*392.34*/.get("Comuna")),format.raw/*392.48*/("""</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*396.9*/for(lista1 <- listClientes) yield /*396.36*/{_display_(Seq[Any](format.raw/*396.37*/("""
								"""),format.raw/*397.9*/("""<TR onClick="seleccionaCliente("""),_display_(/*397.41*/lista1/*397.47*/.getId()),format.raw/*397.55*/(""")" data-dismiss="modal">
									<td  style="text-align:left;"><a href="#"><div id="cliente_"""),_display_(/*398.70*/lista1/*398.76*/.getId()),format.raw/*398.84*/("""">"""),_display_(/*398.87*/lista1/*398.93*/.getNickName()),format.raw/*398.107*/("""</div></a></td>
									<td  style="text-align:left;"><a href="#">"""),_display_(/*399.53*/lista1/*399.59*/.getRegion()),format.raw/*399.71*/("""</a></td>
									<td  style="text-align:left;"><a href="#">"""),_display_(/*400.53*/lista1/*400.59*/.getComuna()),format.raw/*400.71*/("""</a></td>
								</TR>
				 			""")))}),format.raw/*402.10*/("""
						"""),format.raw/*403.7*/("""</tbody>
					</table>
	   				<div class='row'>
						<div class='col-sm-12' style='text-align:center'>
							<button type='button' class='btn btn-sm  btn-warning' style='font-size: 10px;' data-dismiss='modal'>Cancelar</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div id='listaPropietario' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>Lista de selecci&oacute;n propietario</h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<div class="noprint" align="center">
						<a href="#" onclick="$('#listaPropietario').modal('hide'); $('#modalPropietarioNuevo').modal('show');">
							<kbd style="background-color: #73C6B6">Agregar Nuevo</kbd>
						</a>
					</div>
					<table id="tablaListaPropietario" class='table table-sm table-bordered table-condensed table-hover table-fluid'>
						<thead style="background-color: #eeeeee">
							<TR> 
								<TH>NOMBRE CORTO (NICK NAME)</TH>
						        <TH>"""),_display_(/*434.20*/mapDiccionario/*434.34*/.get("Region")),format.raw/*434.48*/("""</TH>
						        <TH>"""),_display_(/*435.20*/mapDiccionario/*435.34*/.get("Comuna")),format.raw/*435.48*/("""</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*439.9*/for(lista1 <- listClientes) yield /*439.36*/{_display_(Seq[Any](format.raw/*439.37*/("""
								"""),format.raw/*440.9*/("""<TR onClick="seleccionaPropietario("""),_display_(/*440.45*/lista1/*440.51*/.getId()),format.raw/*440.59*/(""")" data-dismiss="modal">
									<td  style="text-align:left;"><a href="#"><div id="propietario_"""),_display_(/*441.74*/lista1/*441.80*/.getId()),format.raw/*441.88*/("""">"""),_display_(/*441.91*/lista1/*441.97*/.getNickName()),format.raw/*441.111*/("""</div></a></td>
									<td  style="text-align:left;"><a href="#">"""),_display_(/*442.53*/lista1/*442.59*/.getRegion()),format.raw/*442.71*/("""</a></td>
									<td  style="text-align:left;"><a href="#">"""),_display_(/*443.53*/lista1/*443.59*/.getComuna()),format.raw/*443.71*/("""</a></td>
								</TR>
				 			""")))}),format.raw/*445.10*/("""
						"""),format.raw/*446.7*/("""</tbody>
					</table>
	   				<div class='row'>
						<div class='col-sm-12' style='text-align:center'>
							<button type='button' class='btn btn-sm  btn-warning' style='font-size: 10px;' data-dismiss='modal'>Cancelar</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div id='listaProyecto' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>Lista de selecci&oacute;n proyecto</h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<div class="noprint" align="center">
						<a href="#" onclick="$('#listaProyecto').modal('hide'); $('#modalProyectoNuevo').modal('show');">
							<kbd style="background-color: #73C6B6">Agregar Nuevo</kbd>
						</a>
					</div>
					<table id="tablaListaProyecto" class='table table-sm table-bordered table-condensed table-hover table-fluid'>
						<thead style="background-color: #eeeeee">
							<TR> 
								<TH>NOMBRE CORTO (NICK NAME)</TH>
						        <TH>"""),_display_(/*477.20*/mapDiccionario/*477.34*/.get("Region")),format.raw/*477.48*/("""</TH>
						        <TH>"""),_display_(/*478.20*/mapDiccionario/*478.34*/.get("Comuna")),format.raw/*478.48*/("""</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*482.9*/for(lista1 <- listProyectos) yield /*482.37*/{_display_(Seq[Any](format.raw/*482.38*/("""
								"""),format.raw/*483.9*/("""<TR onClick="seleccionaProyecto("""),_display_(/*483.42*/lista1/*483.48*/.getId()),format.raw/*483.56*/(""")" data-dismiss="modal">
									<td  style="text-align:left;"><a href="#"><div id="proyecto_"""),_display_(/*484.71*/lista1/*484.77*/.getId()),format.raw/*484.85*/("""">"""),_display_(/*484.88*/lista1/*484.94*/.getNickName()),format.raw/*484.108*/("""</div></a></td>
									<td  style="text-align:left;"><a href="#">"""),_display_(/*485.53*/lista1/*485.59*/.getRegion()),format.raw/*485.71*/("""</a></td>
									<td  style="text-align:left;"><a href="#">"""),_display_(/*486.53*/lista1/*486.59*/.getComuna()),format.raw/*486.71*/("""</a></td>
								</TR>
				 			""")))}),format.raw/*488.10*/("""
						"""),format.raw/*489.7*/("""</tbody>
					</table>
	   				<div class='row'>
						<div class='col-sm-12' style='text-align:center'>
							<button type='button' class='btn btn-sm  btn-warning' style='font-size: 10px;' data-dismiss='modal'>Cancelar</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
""")))}),format.raw/*502.2*/("""



"""),format.raw/*506.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*508.31*/("""{"""),format.raw/*508.32*/("""
		"""),format.raw/*509.3*/("""$('#tablaListaPropietario').DataTable("""),format.raw/*509.41*/("""{"""),format.raw/*509.42*/("""
		   	"""),format.raw/*510.7*/(""""fixedHeader": true,
		   	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		   	"language": """),format.raw/*512.19*/("""{"""),format.raw/*512.20*/("""
		       	"""),format.raw/*513.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		       """),format.raw/*514.10*/("""}"""),format.raw/*514.11*/("""
		 """),format.raw/*515.4*/("""}"""),format.raw/*515.5*/(""" """),format.raw/*515.6*/(""");
		 $('#tablaListaCliente').DataTable("""),format.raw/*516.38*/("""{"""),format.raw/*516.39*/("""
		   	"""),format.raw/*517.7*/(""""fixedHeader": true,
		   	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		   	"language": """),format.raw/*519.19*/("""{"""),format.raw/*519.20*/("""
		       	"""),format.raw/*520.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		       """),format.raw/*521.10*/("""}"""),format.raw/*521.11*/("""
		 """),format.raw/*522.4*/("""}"""),format.raw/*522.5*/(""" """),format.raw/*522.6*/(""");
		 $('#tablaListaProyecto').DataTable("""),format.raw/*523.39*/("""{"""),format.raw/*523.40*/("""
		   	"""),format.raw/*524.7*/(""""fixedHeader": true,
		   	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		   	"language": """),format.raw/*526.19*/("""{"""),format.raw/*526.20*/("""
		       	"""),format.raw/*527.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		       """),format.raw/*528.10*/("""}"""),format.raw/*528.11*/("""
		 """),format.raw/*529.4*/("""}"""),format.raw/*529.5*/(""" """),format.raw/*529.6*/(""");
		 document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*531.2*/("""}"""),format.raw/*531.3*/(""");
	
	
	const cambiaDatos = (id_tipoBodega) => """),format.raw/*534.41*/("""{"""),format.raw/*534.42*/("""
		"""),format.raw/*535.3*/("""if(id_tipoBodega==1)"""),format.raw/*535.23*/("""{"""),format.raw/*535.24*/("""
			"""),format.raw/*536.4*/("""document.getElementById("datosInterna").removeAttribute("hidden");
			document.getElementById("datosExterna").setAttribute("hidden","true");
	  	"""),format.raw/*538.5*/("""}"""),format.raw/*538.6*/("""else"""),format.raw/*538.10*/("""{"""),format.raw/*538.11*/("""
	  		"""),format.raw/*539.6*/("""document.getElementById("datosExterna").removeAttribute("hidden");
	  		document.getElementById("datosInterna").setAttribute("hidden","true");
	  	"""),format.raw/*541.5*/("""}"""),format.raw/*541.6*/("""
	"""),format.raw/*542.2*/("""}"""),format.raw/*542.3*/("""
	
	"""),format.raw/*544.2*/("""const verificaNombre = (nombreBodega) => """),format.raw/*544.43*/("""{"""),format.raw/*544.44*/("""
		"""),format.raw/*545.3*/("""var formData = new FormData();
	  	formData.append('nombreBodega',nombreBodega);
        $.ajax("""),format.raw/*547.16*/("""{"""),format.raw/*547.17*/("""
            """),format.raw/*548.13*/("""url: "/verificaNombreBodegaAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*555.36*/("""{"""),format.raw/*555.37*/("""
	     		"""),format.raw/*556.9*/("""if(respuesta=="existe")"""),format.raw/*556.32*/("""{"""),format.raw/*556.33*/("""
	     			"""),format.raw/*557.10*/("""alertify.alert('El nombre de bodega ya existe, intente con otro', function () """),format.raw/*557.88*/("""{"""),format.raw/*557.89*/("""
	     				"""),format.raw/*558.11*/("""$("#nombre").val("");
		     		"""),format.raw/*559.10*/("""}"""),format.raw/*559.11*/(""");
	     		"""),format.raw/*560.9*/("""}"""),format.raw/*560.10*/("""
	     		"""),format.raw/*561.9*/("""if(respuesta=="error")"""),format.raw/*561.31*/("""{"""),format.raw/*561.32*/("""
	     			"""),format.raw/*562.10*/("""alertify.alert(msgError, function () """),format.raw/*562.47*/("""{"""),format.raw/*562.48*/("""
		     			"""),format.raw/*563.11*/("""location.href = "/";
		     		"""),format.raw/*564.10*/("""}"""),format.raw/*564.11*/(""");
	     		"""),format.raw/*565.9*/("""}"""),format.raw/*565.10*/("""
	     	"""),format.raw/*566.8*/("""}"""),format.raw/*566.9*/("""
        """),format.raw/*567.9*/("""}"""),format.raw/*567.10*/(""");
	"""),format.raw/*568.2*/("""}"""),format.raw/*568.3*/(""";
	
	const seleccionaCliente = (id) => """),format.raw/*570.36*/("""{"""),format.raw/*570.37*/("""
		"""),format.raw/*571.3*/("""$("#nickNameCliente").val($("#cliente_"+id).text());
		$("#id_cliente").val(id);
	"""),format.raw/*573.2*/("""}"""),format.raw/*573.3*/("""
	
	"""),format.raw/*575.2*/("""const seleccionaPropietario = (id) => """),format.raw/*575.40*/("""{"""),format.raw/*575.41*/("""
		"""),format.raw/*576.3*/("""$("#nickNamePropietario").val($("#propietario_"+id).text());
		$("#id_propietario").val(id);
	"""),format.raw/*578.2*/("""}"""),format.raw/*578.3*/("""
	
	"""),format.raw/*580.2*/("""const seleccionaProyecto = (id) => """),format.raw/*580.37*/("""{"""),format.raw/*580.38*/("""
		"""),format.raw/*581.3*/("""$("#nickNameProyecto").val($("#proyecto_"+id).text());
		$("#id_proyecto").val(id);
	"""),format.raw/*583.2*/("""}"""),format.raw/*583.3*/("""
	
	"""),format.raw/*585.2*/("""const vistaSelectComercial = (id_sucursal) =>"""),format.raw/*585.47*/("""{"""),format.raw/*585.48*/("""
		"""),format.raw/*586.3*/("""var formData = new FormData();
		formData.append('id_sucursal',id_sucursal);
        $.ajax("""),format.raw/*588.16*/("""{"""),format.raw/*588.17*/("""
            """),format.raw/*589.13*/("""url: "/vistaSelectComercialAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*596.36*/("""{"""),format.raw/*596.37*/("""
				"""),format.raw/*597.5*/("""document.getElementById('selectComercial').innerHTML = respuesta;
	     	"""),format.raw/*598.8*/("""}"""),format.raw/*598.9*/("""
        """),format.raw/*599.9*/("""}"""),format.raw/*599.10*/(""");	
	"""),format.raw/*600.2*/("""}"""),format.raw/*600.3*/("""
	
	"""),format.raw/*602.2*/("""const validarForm = () =>"""),format.raw/*602.27*/("""{"""),format.raw/*602.28*/("""
		"""),format.raw/*603.3*/("""$("#btnSubmit").attr('disabled',true);
		return(true);
	"""),format.raw/*605.2*/("""}"""),format.raw/*605.3*/("""
	
	"""),format.raw/*607.2*/("""const selMon = (idMon_mon) => """),format.raw/*607.32*/("""{"""),format.raw/*607.33*/("""
		
		"""),format.raw/*609.3*/("""let aux = idMon_mon.split(" _ ");
		let id = aux[0];
		let mon = aux[1];
	
		let tabla = document.getElementById("fijaTasas");
		let row = tabla.insertRow(tabla.rows.length-1);
		let cell;
		
		cell = row.insertCell(0);
		cell.style.textAlign = "left";
		cell.innerHTML = "<input type=\"hidden\" name=\"idsMoneda[]\" value=\""+id+"\">"+mon;
		
		cell = row.insertCell(1);
		cell.style.textAlign = "left";
		cell.innerHTML = "<input type=\"text\" class=\"form-control right\" "+
			"name=\"tasaCambio[]\" "+
			"value=\"0\" "+
			"onkeydown=\"return ingresoDouble(window.event)\" "+
			"onchange=\"if(value.trim() == '') value='0';\" "+
			"autocomplete=\"off\" \">";
		
		cell = row.insertCell(2);
		cell.style.textAlign = "center";
		cell.innerHTML = "<a href=\"#\" onclick= \"eliminarFila(this)\"><kbd style=\"background-color: red\">X</kbd></a>";
		
	"""),format.raw/*634.2*/("""}"""),format.raw/*634.3*/("""
	
	"""),format.raw/*636.2*/("""const eliminarFila = (nodo) =>"""),format.raw/*636.32*/("""{"""),format.raw/*636.33*/("""
		"""),format.raw/*637.3*/("""let nodoTd = nodo.parentNode.parentNode; 
		$(nodoTd).remove();
	"""),format.raw/*639.2*/("""}"""),format.raw/*639.3*/("""
	
	

"""),format.raw/*643.1*/("""</script>

		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listClientes:List[tables.Cliente],listProyectos:List[tables.Proyecto],listTipoBodega:List[tables.TipoBodega],listRegiones:List[tables.Regiones],listSucursal:List[tables.Sucursal],sucursal:tables.Sucursal,esPorSucursal:String,listMon:List[tables.Moneda],listRubro:List[tables.Rubro]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listClientes,listProyectos,listTipoBodega,listRegiones,listSucursal,sucursal,esPorSucursal,listMon,listRubro)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Cliente],List[tables.Proyecto],List[tables.TipoBodega],List[tables.Regiones],List[tables.Sucursal],tables.Sucursal,String,List[tables.Moneda],List[tables.Rubro]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listClientes,listProyectos,listTipoBodega,listRegiones,listSucursal,sucursal,esPorSucursal,listMon,listRubro) => apply(mapDiccionario,mapPermiso,userMnu,listClientes,listProyectos,listTipoBodega,listRegiones,listSucursal,sucursal,esPorSucursal,listMon,listRubro)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuBodegas/bodegaCrear.scala.html
                  HASH: 71ca3d88f00011ec265ec08e76e0e1f8ab5a7840
                  MATRIX: 1184->1|1674->398|1707->406|1723->414|1762->416|1790->419|1858->467|1888->470|1946->502|2014->549|2044->552|2124->604|2153->605|2185->610|3047->1445|3075->1446|3105->1449|3211->1529|3280->1577|3310->1580|3392->1634|3421->1635|3453->1640|4258->2418|4286->2419|4316->2422|4426->2506|4498->2557|4528->2560|4616->2620|4645->2621|4677->2626|5603->3525|5631->3526|5661->3529|5785->3627|5897->3718|5927->3721|6278->4045|6301->4059|6336->4073|6935->4644|6975->4645|7014->4655|7149->4762|7167->4770|7197->4778|7228->4781|7246->4789|7280->4801|7352->4853|7392->4854|7431->4864|7637->5042|7680->5068|7720->5069|7761->5081|7805->5097|7820->5102|7850->5110|7881->5113|7896->5118|7930->5130|7983->5151|8022->5161|8073->5180|8109->5188|8211->5262|8235->5276|8271->5290|8458->5449|8493->5462|8531->5471|8561->5472|8612->5495|8630->5503|8661->5511|8693->5513|8724->5514|8819->5581|8864->5609|8904->5610|8944->5621|8988->5637|9003->5642|9033->5650|9064->5653|9079->5658|9113->5670|9165->5690|9202->5699|9488->5957|9528->5980|9569->5982|9608->5992|9652->6008|9667->6013|9697->6021|9728->6024|9743->6029|9777->6041|9829->6061|9866->6070|14085->10260|14127->10262|14167->10273|14816->10894|14854->10915|14894->10916|14942->10935|14986->10951|15001->10956|15026->10959|15056->10960|15087->10963|15102->10968|15133->10977|15164->10980|15179->10985|15210->10994|15270->11022|15316->11039|15548->11226|15704->11353|15746->11355|15789->11369|16657->12192|16696->12202|18176->13654|18200->13668|18236->13682|19607->15025|19631->15039|19667->15053|19720->15078|19744->15092|19780->15106|19863->15162|19907->15189|19947->15190|19984->15199|20044->15231|20060->15237|20090->15245|20212->15339|20228->15345|20258->15353|20289->15356|20305->15362|20342->15376|20438->15444|20454->15450|20488->15462|20578->15524|20594->15530|20628->15542|20693->15575|20728->15582|22051->16877|22075->16891|22111->16905|22164->16930|22188->16944|22224->16958|22307->17014|22351->17041|22391->17042|22428->17051|22492->17087|22508->17093|22538->17101|22664->17199|22680->17205|22710->17213|22741->17216|22757->17222|22794->17236|22890->17304|22906->17310|22940->17322|23030->17384|23046->17390|23080->17402|23145->17435|23180->17442|24488->18722|24512->18736|24548->18750|24601->18775|24625->18789|24661->18803|24744->18859|24789->18887|24829->18888|24866->18897|24927->18930|24943->18936|24973->18944|25096->19039|25112->19045|25142->19053|25173->19056|25189->19062|25226->19076|25322->19144|25338->19150|25372->19162|25462->19224|25478->19230|25512->19242|25577->19275|25612->19282|25941->19580|25973->19584|26065->19647|26095->19648|26126->19651|26193->19689|26223->19690|26258->19697|26399->19809|26429->19810|26469->19821|26575->19898|26605->19899|26637->19903|26666->19904|26695->19905|26764->19945|26794->19946|26829->19953|26970->20065|27000->20066|27040->20077|27146->20154|27176->20155|27208->20159|27237->20160|27266->20161|27336->20202|27366->20203|27401->20210|27542->20322|27572->20323|27612->20334|27718->20411|27748->20412|27780->20416|27809->20417|27838->20418|27938->20490|27967->20491|28043->20538|28073->20539|28104->20542|28153->20562|28183->20563|28215->20567|28388->20712|28417->20713|28450->20717|28480->20718|28514->20724|28689->20871|28718->20872|28748->20874|28777->20875|28809->20879|28879->20920|28909->20921|28940->20924|29065->21020|29095->21021|29137->21034|29403->21271|29433->21272|29470->21281|29522->21304|29552->21305|29591->21315|29698->21393|29728->21394|29768->21405|29828->21436|29858->21437|29897->21448|29927->21449|29964->21458|30015->21480|30045->21481|30084->21491|30150->21528|30180->21529|30220->21540|30279->21570|30309->21571|30348->21582|30378->21583|30414->21591|30443->21592|30480->21601|30510->21602|30542->21606|30571->21607|30639->21646|30669->21647|30700->21650|30810->21732|30839->21733|30871->21737|30938->21775|30968->21776|30999->21779|31121->21873|31150->21874|31182->21878|31246->21913|31276->21914|31307->21917|31420->22002|31449->22003|31481->22007|31555->22052|31585->22053|31616->22056|31737->22148|31767->22149|31809->22162|32075->22399|32105->22400|32138->22405|32239->22478|32268->22479|32305->22488|32335->22489|32368->22494|32397->22495|32429->22499|32483->22524|32513->22525|32544->22528|32628->22584|32657->22585|32689->22589|32748->22619|32778->22620|32812->22626|33694->23480|33723->23481|33755->23485|33814->23515|33844->23516|33875->23519|33968->23584|33997->23585|34031->23591
                  LINES: 28->1|35->4|37->6|37->6|37->6|39->8|39->8|41->10|42->11|42->11|43->12|44->13|44->13|45->14|62->31|62->31|63->32|67->36|67->36|68->37|69->38|69->38|70->39|86->55|86->55|87->56|91->60|91->60|92->61|93->62|93->62|94->63|111->80|111->80|112->81|116->85|116->85|117->86|122->91|122->91|122->91|138->107|138->107|139->108|142->111|142->111|142->111|142->111|142->111|142->111|145->114|145->114|146->115|151->120|151->120|151->120|152->121|152->121|152->121|152->121|152->121|152->121|152->121|153->122|154->123|155->124|156->125|159->128|159->128|159->128|163->132|163->132|163->132|163->132|163->132|163->132|163->132|163->132|163->132|166->135|166->135|166->135|167->136|167->136|167->136|167->136|167->136|167->136|167->136|168->137|169->138|177->146|177->146|177->146|178->147|178->147|178->147|178->147|178->147|178->147|178->147|179->148|180->149|297->266|297->266|298->267|314->283|314->283|314->283|315->284|315->284|315->284|315->284|315->284|315->284|315->284|315->284|315->284|315->284|315->284|316->285|317->286|326->295|328->297|328->297|329->298|351->320|352->321|391->360|391->360|391->360|422->391|422->391|422->391|423->392|423->392|423->392|427->396|427->396|427->396|428->397|428->397|428->397|428->397|429->398|429->398|429->398|429->398|429->398|429->398|430->399|430->399|430->399|431->400|431->400|431->400|433->402|434->403|465->434|465->434|465->434|466->435|466->435|466->435|470->439|470->439|470->439|471->440|471->440|471->440|471->440|472->441|472->441|472->441|472->441|472->441|472->441|473->442|473->442|473->442|474->443|474->443|474->443|476->445|477->446|508->477|508->477|508->477|509->478|509->478|509->478|513->482|513->482|513->482|514->483|514->483|514->483|514->483|515->484|515->484|515->484|515->484|515->484|515->484|516->485|516->485|516->485|517->486|517->486|517->486|519->488|520->489|533->502|537->506|539->508|539->508|540->509|540->509|540->509|541->510|543->512|543->512|544->513|545->514|545->514|546->515|546->515|546->515|547->516|547->516|548->517|550->519|550->519|551->520|552->521|552->521|553->522|553->522|553->522|554->523|554->523|555->524|557->526|557->526|558->527|559->528|559->528|560->529|560->529|560->529|562->531|562->531|565->534|565->534|566->535|566->535|566->535|567->536|569->538|569->538|569->538|569->538|570->539|572->541|572->541|573->542|573->542|575->544|575->544|575->544|576->545|578->547|578->547|579->548|586->555|586->555|587->556|587->556|587->556|588->557|588->557|588->557|589->558|590->559|590->559|591->560|591->560|592->561|592->561|592->561|593->562|593->562|593->562|594->563|595->564|595->564|596->565|596->565|597->566|597->566|598->567|598->567|599->568|599->568|601->570|601->570|602->571|604->573|604->573|606->575|606->575|606->575|607->576|609->578|609->578|611->580|611->580|611->580|612->581|614->583|614->583|616->585|616->585|616->585|617->586|619->588|619->588|620->589|627->596|627->596|628->597|629->598|629->598|630->599|630->599|631->600|631->600|633->602|633->602|633->602|634->603|636->605|636->605|638->607|638->607|638->607|640->609|665->634|665->634|667->636|667->636|667->636|668->637|670->639|670->639|674->643
                  -- GENERATED --
              */
          