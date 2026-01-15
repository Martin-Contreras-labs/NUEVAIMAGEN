
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

object bodegaModificar extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template14[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Cliente],List[tables.Proyecto],tables.TipoBodega,tables.BodegaEmpresa,List[tables.Regiones],List[tables.Comercial],List[tables.Moneda],List[List[String]],List[tables.Sucursal],String,List[tables.Rubro],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listClientes: List[tables.Cliente], listProyectos: List[tables.Proyecto], tipoBodega: tables.TipoBodega, bodega: tables.BodegaEmpresa, listRegiones: List[tables.Regiones],
listComercial: List[tables.Comercial], listMon: List[tables.Moneda], fijaTasas: List[List[String]], listSucursal: List[tables.Sucursal], esPorSucursal: String, listRubro: List[tables.Rubro]):play.twirl.api.HtmlFormat.Appendable = {
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
		"""),_display_(/*86.4*/barraTitulo(mapDiccionario, "MODIFICAR "+mapDiccionario.get("BODEGA")+"/PROYECTO EXISTENTE", "(MODIFICAR)")),format.raw/*86.111*/("""
		"""),format.raw/*87.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<form id="form" method="post" action="/bodegaModifica/" onsubmit="return validarForm();">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<tr>
							<td style="text-align:left;">NOMBRE """),_display_(/*92.45*/mapDiccionario/*92.59*/.get("BODEGA")),format.raw/*92.73*/("""/PROYECTO: </td>
							<td style="text-align:left;">
								<input type="hidden" name="id_bodegaEmpresa" value=""""),_display_(/*94.62*/bodega/*94.68*/.getId()),format.raw/*94.76*/("""">
								<input type="text" class="form-control left"
									id="nombre"
									name="nombre"
									value=""""),_display_(/*98.18*/bodega/*98.24*/.getNombre()),format.raw/*98.36*/(""""
									onkeydown="return sinReservados(window.event)"
									autocomplete="off"
									maxlength="100"
									onchange="value = value.trim(); verificaNombre(value);"
									required>
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">SUCURSAL: </td>
							<td style="text-align:left;">

								"""),_display_(if(! esPorSucursal.equals("1"))/*110.41*/{_display_(Seq[Any](format.raw/*110.42*/("""
									"""),format.raw/*111.10*/("""<select class="custom-select" 
										name="id_sucursal" 
										onchange="vistaSelectComercial(value)"
										required>
										<option value=""""),_display_(/*115.27*/bodega/*115.33*/.getId_sucursal()),format.raw/*115.50*/("""">"""),_display_(/*115.53*/bodega/*115.59*/.getNameSucursal()),format.raw/*115.77*/("""</option>
										"""),_display_(/*116.12*/for(lista <- listSucursal) yield /*116.38*/{_display_(Seq[Any](format.raw/*116.39*/("""
											"""),format.raw/*117.12*/("""<option value=""""),_display_(/*117.28*/lista/*117.33*/.getId()),format.raw/*117.41*/("""">"""),_display_(/*117.44*/lista/*117.49*/.getNombre()),format.raw/*117.61*/("""</option>
										""")))}),format.raw/*118.12*/("""
									"""),format.raw/*119.10*/("""</select>
								""")))}else/*120.14*/{_display_(Seq[Any](format.raw/*120.15*/("""
									"""),format.raw/*121.10*/("""<input type="hidden" name="id_sucursal" value=""""),_display_(/*121.58*/bodega/*121.64*/.getId_sucursal()),format.raw/*121.81*/("""">
									<input type="text" class="form-control left" 
										value=""""),_display_(/*123.19*/bodega/*123.25*/.getNameSucursal()),format.raw/*123.43*/("""" 
										readonly>
								""")))}),format.raw/*125.10*/("""
							"""),format.raw/*126.8*/("""</td>
						</tr>
						<tr>
							<td style="text-align:left;">TIPO DE """),_display_(/*129.46*/mapDiccionario/*129.60*/.get("BODEGA")),format.raw/*129.74*/(""": </td>
							<td style="text-align:left;">
								<input type="hidden" name="id_tipoBodega" value=""""),_display_(/*131.59*/bodega/*131.65*/.getEsInterna()),format.raw/*131.80*/("""">
								<input type="text" class="form-control left"
									value=""""),_display_(/*133.18*/tipoBodega/*133.28*/.getNombre()),format.raw/*133.40*/(""""
									readonly>
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">RUBRO: </td>
							<td style="text-align:left;">
								<select class="custom-select" id="id_rubro" name="id_rubro">"
									<option value=""""),_display_(/*141.26*/bodega/*141.32*/.getId_rubro()),format.raw/*141.46*/("""">"""),_display_(/*141.49*/bodega/*141.55*/.getNameRubro()),format.raw/*141.70*/("""</option>
									"""),_display_(/*142.11*/for(lista <- listRubro) yield /*142.34*/ {_display_(Seq[Any](format.raw/*142.36*/("""
										"""),format.raw/*143.11*/("""<option value=""""),_display_(/*143.27*/lista/*143.32*/.getId()),format.raw/*143.40*/("""">"""),_display_(/*143.43*/lista/*143.48*/.getNombre()),format.raw/*143.60*/("""</option>
									""")))}),format.raw/*144.11*/("""
								"""),format.raw/*145.9*/("""</select>
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">EMAIL ENVÍO: </td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
								id="email_envio"
								name="email_envio"
								value=""""),_display_(/*154.17*/bodega/*154.23*/.getEmailEnvio()),format.raw/*154.39*/(""""
								autocomplete="off"
								onchange="value = value.trim();">
								<small class="text-muted">
									Puede ingresar varios.
								</small>
							</td>
						</tr>

						<tr>
							<td style="text-align:left;">EMAIL CC: </td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
								id="email_ccopia"
								name="email_ccopia"
								value=""""),_display_(/*169.17*/bodega/*169.23*/.getEmailCcopia()),format.raw/*169.40*/(""""
								autocomplete="off"
								onchange="value = value.trim();">
								<small class="text-muted">
									Copia opcional visible para el receptor.
								</small>
							</td>
						</tr>

						<tr>
							<td colspan="2">
								<table id="datosInterna" class="table table-sm table-bordered table-condensed table-fluid" hidden>
									<tr>
										<td style="text-align:left;">PROPIETARIO: </td>
										<td style="text-align:left;">
											<input type="hidden" name="id_propietario" id="id_propietario" value=""""),_display_(/*184.83*/bodega/*184.89*/.getId_cliente()),format.raw/*184.105*/("""">
											<input type="text" class="form-control left"
												id="nickNamePropietario"
												value=""""),_display_(/*187.21*/bodega/*187.27*/.getNickCliente()),format.raw/*187.44*/(""""
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
											<input type="hidden" name="id_cliente" id="id_cliente" value=""""),_display_(/*198.75*/bodega/*198.81*/.getId_cliente()),format.raw/*198.97*/("""">
											<input type="text" class="form-control left"
												id="nickNameCliente"
												value=""""),_display_(/*201.21*/bodega/*201.27*/.getNickCliente()),format.raw/*201.44*/(""""
												onclick='$("#listaCliente").modal("show")'
												style="background:white"
												readonly>
										</td>
									</tr>
									<tr>
										<td style="text-align:left;">PROYECTO/OBRA: </td>
										<td style="text-align:left;">
											<input type="hidden" name="id_proyecto" id="id_proyecto" value=""""),_display_(/*210.77*/bodega/*210.83*/.getId_proyecto()),format.raw/*210.100*/("""">
											<input type="text" class="form-control left"
												id="nickNameProyecto"
												value=""""),_display_(/*213.21*/bodega/*213.27*/.getNickProyecto()),format.raw/*213.45*/(""""
												onclick='$("#listaProyecto").modal("show")'
												style="background:white"
												readonly>
										</td>
									</tr>
									<tr>
										<td style="text-align:left;">COMERCIAL: </td>
										<td style="text-align:left;">
											<select class="custom-select" name="id_comercial">"
	    										<option value=""""),_display_(/*223.32*/bodega/*223.38*/.getId_comercial),format.raw/*223.54*/("""">"""),_display_(/*223.57*/bodega/*223.63*/.getNameComercial()),format.raw/*223.82*/("""</option>
	    										"""),_display_(/*224.17*/for(lista <- listComercial) yield /*224.44*/ {_display_(Seq[Any](format.raw/*224.46*/("""
	    											"""),format.raw/*225.17*/("""<option value=""""),_display_(/*225.33*/lista/*225.38*/.getId()),format.raw/*225.46*/("""">"""),_display_(/*225.49*/lista/*225.54*/.getNameUsuario()),format.raw/*225.71*/("""</option>
	    										""")))}),format.raw/*226.17*/("""
												"""),format.raw/*227.13*/("""<option value="0">Sin Comercial</option>
	    									</select>
										</td>
									</tr>
									<tr>
										<td style="text-align:left;">Cargo fijo inicial (CFI): </td>
										<td style="text-align:left;">
											<input type="text" class="form-control left"
												id="cfi"
												name="cfi"
												value=""""),_display_(/*237.21*/bodega/*237.27*/.getTasaCfi()),format.raw/*237.40*/(""""
												autocomplete="off"
												onfocus="value=value.replace(/,/g,'').replace(/%/g,'').replace(/ /g,'')" 
												onblur = "value = formatPorcentaje(value);"
												onkeydown="return ingresoDouble(window.event)">
										</td>
									</tr>
									<tr>
										<td style="text-align:left;">Se cobra el d&iacute;a de despacho: </td>
										<td style="text-align:left;">
											<select class="custom-select" 
												id="cobraDiaDespacho"
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
												id="baseCalculo"
												name="baseCalculo">
												<option value='1'>DIARIO (por d&iacute;a exacto de cada mes)</option>
												<option value='2'>MENSUAL (meses de 30 d&iacute;as)</option>
											</select>
										</td>
									</tr>
									
									
									"""),_display_(if(mapPermiso.get("parametro.fijaTasas")!=null && mapPermiso.get("parametro.fijaTasas").equals("1"))/*268.111*/ {_display_(Seq[Any](format.raw/*268.113*/("""
										"""),format.raw/*269.11*/("""<tr>
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
														"""),_display_(/*281.16*/for(lista <- fijaTasas) yield /*281.39*/{_display_(Seq[Any](format.raw/*281.40*/("""
															"""),format.raw/*282.16*/("""<tr>
																<td>
																	<input type="hidden" name="idsMoneda[]" value=""""),_display_(/*284.66*/lista/*284.71*/.get(0)),format.raw/*284.78*/("""">
																	"""),_display_(/*285.19*/lista/*285.24*/.get(2)),format.raw/*285.31*/("""
																"""),format.raw/*286.17*/("""</td>
																<td>
																	<input type="text" class="form-control right"
																		name="tasaCambio[]"
																		value=""""),_display_(/*290.27*/lista/*290.32*/.get(1)),format.raw/*290.39*/(""""
																		onkeydown="return ingresoDouble(window.event)"
																		onchange="if(value.trim() == '') value='0';"
																		autocomplete="off">
																</td>
																<td>
																	<a href="#" onclick="eliminarFila(this)"><kbd style="background-color: red">X</kbd></a>
																</td>
															</tr>
														""")))}),format.raw/*299.16*/(""" 
														"""),format.raw/*300.15*/("""<tr>
															<td>
																<select class="custom-select" name="id_moneda[]" onchange="selMon(value)">
																	<option value='0'></option>
																	"""),_display_(/*304.19*/for(lista <- listMon) yield /*304.40*/{_display_(Seq[Any](format.raw/*304.41*/("""
																		"""),format.raw/*305.19*/("""<option value=""""),_display_(/*305.35*/lista/*305.40*/.id),format.raw/*305.43*/(""" """),format.raw/*305.44*/("""_ """),_display_(/*305.47*/lista/*305.52*/.nickName),format.raw/*305.61*/("""">"""),_display_(/*305.64*/lista/*305.69*/.nickName),format.raw/*305.78*/("""</option>
																	""")))}),format.raw/*306.19*/("""
																"""),format.raw/*307.17*/("""</select>
															</td>
															<td></td>
															<td></td>
														</tr>
													</tbody>
												</table>
											</td>
										</tr>
									""")))} else {null} ),format.raw/*316.11*/("""
									
									
									"""),_display_(if(mapPermiso.get("parametro.ivaPorBodega")!=null && mapPermiso.get("parametro.ivaPorBodega").equals("1"))/*319.117*/ {_display_(Seq[Any](format.raw/*319.119*/("""
										"""),format.raw/*320.11*/("""<tr>
											<td style="text-align:left;">PEP: </td>
											<td style="text-align:left;">
												<input type="text" class="form-control left"
													id="pep"
													name="pep"
													value=""""),_display_(/*326.22*/bodega/*326.28*/.getPep()),format.raw/*326.37*/(""""
													autocomplete="off"
													maxlength="50"
													onchange="value = value.trim();">
											</td>
										</tr>
										<tr>
											<td style="text-align:left;">IVA: </td>
											<td style="text-align:left;">
												<input type="text" class="form-control left"
													id="ivaBodega"
													name="ivaBodega"
													value=""""),_display_(/*338.22*/bodega/*338.28*/.getIvaBodega()),format.raw/*338.43*/(""""
													autocomplete="off"
													onfocus="value=value.replace(/,/g,'').replace(/%/g,'').replace(/ /g,'')" 
													onblur = "value = formatPorcentaje(value);"
													onkeydown="return ingresoDouble(window.event)">
											</td>
										</tr>
									""")))} else {null} ),format.raw/*345.11*/("""
									"""),format.raw/*346.10*/("""<tr>
										<td style="text-align:left;">Trato de Devoluciones: </td>
										<td style="text-align:left;">
											<select class="custom-select" 
												id="tratoDevoluciones"
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
												value=""""),_display_(/*362.21*/bodega/*362.27*/.getnDiaGraciaEnvio()),format.raw/*362.48*/(""""
												autocomplete="off"
												onkeydown="return ingresoInt(window.event)"
												onchange="if(value=='') value=0;">
										</td>
									</tr>
									<tr>
										<td style="text-align:left;">D&iacute;as de gracia al regreso: </td>
										<td style="text-align:left;">
											<input type="text" class="form-control left"
												name="nDiasRegreso"
												value=""""),_display_(/*373.21*/bodega/*373.27*/.getnDiaGraciaRegreso()),format.raw/*373.50*/(""""
												autocomplete="off"
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
								<input type="submit" id="btnSubmit" value='GRABAR """),_display_(/*386.60*/mapDiccionario/*386.74*/.get("BODEGA")),format.raw/*386.88*/("""/PROYECTO' class="btn btn-success btn-sm rounded-pill btn-block">
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
								<TH>"""),_display_(/*416.14*/mapDiccionario/*416.28*/.get("RUT")),format.raw/*416.39*/("""</TH>
								<TH>NICK</TH>
								<TH>NOMBRE</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*422.9*/for(lista1 <- listClientes) yield /*422.36*/{_display_(Seq[Any](format.raw/*422.37*/("""
								"""),format.raw/*423.9*/("""<TR onClick="seleccionaCliente("""),_display_(/*423.41*/lista1/*423.47*/.getId()),format.raw/*423.55*/(""")" data-dismiss="modal">
									<td  style="text-align:left;">"""),_display_(/*424.41*/lista1/*424.47*/.getRut()),format.raw/*424.56*/("""</td>
									<td  style="text-align:left;"><div id="cliente_"""),_display_(/*425.58*/lista1/*425.64*/.getId()),format.raw/*425.72*/("""">"""),_display_(/*425.75*/lista1/*425.81*/.getNickName()),format.raw/*425.95*/("""</div></td>
									<td  style="text-align:left;">"""),_display_(/*426.41*/lista1/*426.47*/.getNombre()),format.raw/*426.59*/("""</td>
								</TR>
				 			""")))}),format.raw/*428.10*/("""
						"""),format.raw/*429.7*/("""</tbody>
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
								<TH>"""),_display_(/*459.14*/mapDiccionario/*459.28*/.get("RUT")),format.raw/*459.39*/("""</TH>
								<TH>NICK</TH>
								<TH>NOMBRE</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*465.9*/for(lista1 <- listClientes) yield /*465.36*/{_display_(Seq[Any](format.raw/*465.37*/("""
								"""),format.raw/*466.9*/("""<TR onClick="seleccionaPropietario("""),_display_(/*466.45*/lista1/*466.51*/.getId()),format.raw/*466.59*/(""")" data-dismiss="modal">
									<td  style="text-align:left;">"""),_display_(/*467.41*/lista1/*467.47*/.getRut()),format.raw/*467.56*/("""</td>
									<td  style="text-align:left;"><div id="propietario_"""),_display_(/*468.62*/lista1/*468.68*/.getId()),format.raw/*468.76*/("""">"""),_display_(/*468.79*/lista1/*468.85*/.getNickName()),format.raw/*468.99*/("""</div></td>
									<td  style="text-align:left;">"""),_display_(/*469.41*/lista1/*469.47*/.getNombre()),format.raw/*469.59*/("""</td>
								</TR>
				 			""")))}),format.raw/*471.10*/("""
						"""),format.raw/*472.7*/("""</tbody>
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
								<TH>NICK</TH>
								<TH>NOMBRE</TH>
								<TH>"""),_display_(/*504.14*/mapDiccionario/*504.28*/.get("Comuna")),format.raw/*504.42*/("""</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*508.9*/for(lista1 <- listProyectos) yield /*508.37*/{_display_(Seq[Any](format.raw/*508.38*/("""
								"""),format.raw/*509.9*/("""<TR onClick="seleccionaProyecto("""),_display_(/*509.42*/lista1/*509.48*/.getId()),format.raw/*509.56*/(""")" data-dismiss="modal">
									<td  style="text-align:left;"><div id="proyecto_"""),_display_(/*510.59*/lista1/*510.65*/.getId()),format.raw/*510.73*/("""">"""),_display_(/*510.76*/lista1/*510.82*/.getNickName()),format.raw/*510.96*/("""</div></a></td>
									<td  style="text-align:left;">"""),_display_(/*511.41*/lista1/*511.47*/.getNombre()),format.raw/*511.59*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*512.41*/lista1/*512.47*/.getComuna()),format.raw/*512.59*/("""</td>
								</TR>
				 			""")))}),format.raw/*514.10*/("""
						"""),format.raw/*515.7*/("""</tbody>
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
	
	
""")))}),format.raw/*528.2*/("""



"""),format.raw/*532.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*534.31*/("""{"""),format.raw/*534.32*/("""
		"""),format.raw/*535.3*/("""$('#cobraDiaDespacho > option[value=""""),_display_(/*535.41*/bodega/*535.47*/.getCobraDiaDespacho()),format.raw/*535.69*/(""""]').attr('selected', 'selected');
		$('#baseCalculo > option[value=""""),_display_(/*536.36*/bodega/*536.42*/.getBaseCalculo()),format.raw/*536.59*/(""""]').attr('selected', 'selected');
		$('#tratoDevoluciones > option[value=""""),_display_(/*537.42*/bodega/*537.48*/.getTratoDevoluciones()),format.raw/*537.71*/(""""]').attr('selected', 'selected');
		
		$("#cfi").val(formatStandar(""""),_display_(/*539.33*/(bodega.getTasaCfi()*100)),format.raw/*539.58*/("""",2)+" %");
		$("#ivaBodega").val(formatStandar(""""),_display_(/*540.39*/(bodega.getIvaBodega()*100)),format.raw/*540.66*/("""",2)+" %");
		
		$('#tablaListaPropietario').DataTable("""),format.raw/*542.41*/("""{"""),format.raw/*542.42*/("""
		   	"""),format.raw/*543.7*/(""""fixedHeader": true,
		   	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		   	"language": """),format.raw/*545.19*/("""{"""),format.raw/*545.20*/("""
		       	"""),format.raw/*546.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		       """),format.raw/*547.10*/("""}"""),format.raw/*547.11*/("""
		 """),format.raw/*548.4*/("""}"""),format.raw/*548.5*/(""" """),format.raw/*548.6*/(""");
		 $('#tablaListaCliente').DataTable("""),format.raw/*549.38*/("""{"""),format.raw/*549.39*/("""
		   	"""),format.raw/*550.7*/(""""fixedHeader": true,
		   	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		   	"language": """),format.raw/*552.19*/("""{"""),format.raw/*552.20*/("""
		       	"""),format.raw/*553.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		       """),format.raw/*554.10*/("""}"""),format.raw/*554.11*/("""
		 """),format.raw/*555.4*/("""}"""),format.raw/*555.5*/(""" """),format.raw/*555.6*/(""");
		 $('#tablaListaProyecto').DataTable("""),format.raw/*556.39*/("""{"""),format.raw/*556.40*/("""
		   	"""),format.raw/*557.7*/(""""fixedHeader": true,
		   	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		   	"language": """),format.raw/*559.19*/("""{"""),format.raw/*559.20*/("""
		       	"""),format.raw/*560.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		       """),format.raw/*561.10*/("""}"""),format.raw/*561.11*/("""
		 """),format.raw/*562.4*/("""}"""),format.raw/*562.5*/(""" """),format.raw/*562.6*/(""");
		 
		 if('"""),_display_(/*564.9*/bodega/*564.15*/.getEsInterna),format.raw/*564.28*/("""' == '1')"""),format.raw/*564.37*/("""{"""),format.raw/*564.38*/("""
			 """),format.raw/*565.5*/("""document.getElementById("datosInterna").removeAttribute("hidden");
		 """),format.raw/*566.4*/("""}"""),format.raw/*566.5*/("""else"""),format.raw/*566.9*/("""{"""),format.raw/*566.10*/("""
			 """),format.raw/*567.5*/("""document.getElementById("datosExterna").removeAttribute("hidden");
		 """),format.raw/*568.4*/("""}"""),format.raw/*568.5*/("""
		 
		 """),format.raw/*570.4*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*571.2*/("""}"""),format.raw/*571.3*/(""");
	
	const verificaNombre = (nombreBodega) => """),format.raw/*573.43*/("""{"""),format.raw/*573.44*/("""
		"""),format.raw/*574.3*/("""var formData = new FormData();
	  	formData.append('nombreBodega',nombreBodega);
        $.ajax("""),format.raw/*576.16*/("""{"""),format.raw/*576.17*/("""
            """),format.raw/*577.13*/("""url: "/verificaNombreBodegaAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*584.36*/("""{"""),format.raw/*584.37*/("""
	     		"""),format.raw/*585.9*/("""if(respuesta=="existe")"""),format.raw/*585.32*/("""{"""),format.raw/*585.33*/("""
	     			"""),format.raw/*586.10*/("""alertify.alert('El nombre de bodega ya existe, intente con otro', function () """),format.raw/*586.88*/("""{"""),format.raw/*586.89*/("""
	     				"""),format.raw/*587.11*/("""$("#nombre").val("");
		     		"""),format.raw/*588.10*/("""}"""),format.raw/*588.11*/(""");
	     		"""),format.raw/*589.9*/("""}"""),format.raw/*589.10*/("""
	     		"""),format.raw/*590.9*/("""if(respuesta=="error")"""),format.raw/*590.31*/("""{"""),format.raw/*590.32*/("""
	     			"""),format.raw/*591.10*/("""alertify.alert(msgError, function () """),format.raw/*591.47*/("""{"""),format.raw/*591.48*/("""
		     			"""),format.raw/*592.11*/("""location.href = "/";
		     		"""),format.raw/*593.10*/("""}"""),format.raw/*593.11*/(""");
	     		"""),format.raw/*594.9*/("""}"""),format.raw/*594.10*/("""
	     	"""),format.raw/*595.8*/("""}"""),format.raw/*595.9*/("""
        """),format.raw/*596.9*/("""}"""),format.raw/*596.10*/(""");
	"""),format.raw/*597.2*/("""}"""),format.raw/*597.3*/(""";
	
	const seleccionaCliente = (id) => """),format.raw/*599.36*/("""{"""),format.raw/*599.37*/("""
		"""),format.raw/*600.3*/("""$("#nickNameCliente").val($("#cliente_"+id).text());
		$("#id_cliente").val(id);
	"""),format.raw/*602.2*/("""}"""),format.raw/*602.3*/("""
	
	"""),format.raw/*604.2*/("""const seleccionaPropietario = (id) => """),format.raw/*604.40*/("""{"""),format.raw/*604.41*/("""
		"""),format.raw/*605.3*/("""$("#nickNamePropietario").val($("#propietario_"+id).text());
		$("#id_propietario").val(id);
	"""),format.raw/*607.2*/("""}"""),format.raw/*607.3*/("""
	
	"""),format.raw/*609.2*/("""const seleccionaProyecto = (id) => """),format.raw/*609.37*/("""{"""),format.raw/*609.38*/("""
		"""),format.raw/*610.3*/("""$("#nickNameProyecto").val($("#proyecto_"+id).text());
		$("#id_proyecto").val(id);
	"""),format.raw/*612.2*/("""}"""),format.raw/*612.3*/("""
	
	"""),format.raw/*614.2*/("""const validarForm = () =>"""),format.raw/*614.27*/("""{"""),format.raw/*614.28*/("""
		"""),format.raw/*615.3*/("""$("#btnSubmit").attr('disabled',true);
		return(true);
	"""),format.raw/*617.2*/("""}"""),format.raw/*617.3*/("""
	
	"""),format.raw/*619.2*/("""const selMon = (idMon_mon) => """),format.raw/*619.32*/("""{"""),format.raw/*619.33*/("""
		
		"""),format.raw/*621.3*/("""let aux = idMon_mon.split(" _ ");
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
		
	"""),format.raw/*646.2*/("""}"""),format.raw/*646.3*/("""
	
	"""),format.raw/*648.2*/("""const eliminarFila = (nodo) =>"""),format.raw/*648.32*/("""{"""),format.raw/*648.33*/("""
		"""),format.raw/*649.3*/("""let nodoTd = nodo.parentNode.parentNode; 
		$(nodoTd).remove();
	"""),format.raw/*651.2*/("""}"""),format.raw/*651.3*/("""

"""),format.raw/*653.1*/("""</script>

		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listClientes:List[tables.Cliente],listProyectos:List[tables.Proyecto],tipoBodega:tables.TipoBodega,bodega:tables.BodegaEmpresa,listRegiones:List[tables.Regiones],listComercial:List[tables.Comercial],listMon:List[tables.Moneda],fijaTasas:List[List[String]],listSucursal:List[tables.Sucursal],esPorSucursal:String,listRubro:List[tables.Rubro]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listClientes,listProyectos,tipoBodega,bodega,listRegiones,listComercial,listMon,fijaTasas,listSucursal,esPorSucursal,listRubro)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Cliente],List[tables.Proyecto],tables.TipoBodega,tables.BodegaEmpresa,List[tables.Regiones],List[tables.Comercial],List[tables.Moneda],List[List[String]],List[tables.Sucursal],String,List[tables.Rubro]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listClientes,listProyectos,tipoBodega,bodega,listRegiones,listComercial,listMon,fijaTasas,listSucursal,esPorSucursal,listRubro) => apply(mapDiccionario,mapPermiso,userMnu,listClientes,listProyectos,tipoBodega,bodega,listRegiones,listComercial,listMon,fijaTasas,listSucursal,esPorSucursal,listRubro)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuBodegas/bodegaModificar.scala.html
                  HASH: 98483a4ffcc91d8720325ae65ff60b5c65d7ae1b
                  MATRIX: 1229->1|1782->461|1815->469|1831->477|1870->479|1898->482|1966->530|1996->533|2054->565|2122->612|2152->615|2232->667|2261->668|2293->673|3155->1508|3183->1509|3213->1512|3319->1592|3388->1640|3418->1643|3500->1697|3529->1698|3561->1703|4366->2481|4394->2482|4424->2485|4534->2569|4606->2620|4636->2623|4724->2683|4753->2684|4785->2689|5711->3588|5739->3589|5769->3592|5895->3692|6024->3799|6054->3802|6408->4129|6431->4143|6466->4157|6608->4272|6623->4278|6652->4286|6796->4403|6811->4409|6844->4421|7233->4782|7273->4783|7312->4793|7496->4949|7512->4955|7551->4972|7582->4975|7598->4981|7638->4999|7687->5020|7730->5046|7770->5047|7811->5059|7855->5075|7870->5080|7900->5088|7931->5091|7946->5096|7980->5108|8033->5129|8072->5139|8115->5162|8155->5163|8194->5173|8270->5221|8286->5227|8325->5244|8429->5320|8445->5326|8485->5344|8549->5376|8585->5384|8687->5458|8711->5472|8747->5486|8878->5589|8894->5595|8931->5610|9032->5683|9052->5693|9086->5705|9352->5943|9368->5949|9404->5963|9435->5966|9451->5972|9488->5987|9536->6007|9576->6030|9617->6032|9657->6043|9701->6059|9716->6064|9746->6072|9777->6075|9792->6080|9826->6092|9878->6112|9915->6121|10202->6380|10218->6386|10256->6402|10688->6806|10704->6812|10743->6829|11306->7364|11322->7370|11361->7386|11505->7502|11521->7508|11560->7525|12045->7982|12061->7988|12099->8004|12239->8116|12255->8122|12294->8139|12659->8476|12675->8482|12715->8499|12856->8612|12872->8618|12912->8636|13292->8988|13308->8994|13346->9010|13377->9013|13393->9019|13434->9038|13488->9064|13532->9091|13573->9093|13619->9110|13663->9126|13678->9131|13708->9139|13739->9142|13754->9147|13793->9164|13851->9190|13893->9203|14262->9544|14278->9550|14313->9563|15555->10776|15597->10778|15637->10789|16108->11232|16148->11255|16188->11256|16233->11272|16352->11363|16367->11368|16396->11375|16445->11396|16460->11401|16489->11408|16535->11425|16717->11579|16732->11584|16761->11591|17167->11965|17212->11981|17419->12160|17457->12181|17497->12182|17545->12201|17589->12217|17604->12222|17629->12225|17659->12226|17690->12229|17705->12234|17736->12243|17767->12246|17782->12251|17813->12260|17873->12288|17919->12305|18151->12492|18317->12629|18359->12631|18399->12642|18648->12863|18664->12869|18695->12878|19108->13263|19124->13269|19161->13284|19488->13566|19527->13576|20216->14237|20232->14243|20275->14264|20711->14672|20727->14678|20772->14701|21217->15118|21241->15132|21277->15146|22600->16441|22624->16455|22657->16466|22786->16568|22830->16595|22870->16596|22907->16605|22967->16637|22983->16643|23013->16651|23106->16716|23122->16722|23153->16731|23244->16794|23260->16800|23290->16808|23321->16811|23337->16817|23373->16831|23453->16883|23469->16889|23503->16901|23564->16930|23599->16937|24874->18184|24898->18198|24931->18209|25060->18311|25104->18338|25144->18339|25181->18348|25245->18384|25261->18390|25291->18398|25384->18463|25400->18469|25431->18478|25526->18545|25542->18551|25572->18559|25603->18562|25619->18568|25655->18582|25735->18634|25751->18640|25785->18652|25846->18681|25881->18688|27187->19966|27211->19980|27247->19994|27330->20050|27375->20078|27415->20079|27452->20088|27513->20121|27529->20127|27559->20135|27670->20218|27686->20224|27716->20232|27747->20235|27763->20241|27799->20255|27883->20311|27899->20317|27933->20329|28007->20375|28023->20381|28057->20393|28118->20422|28153->20429|28482->20727|28514->20731|28606->20794|28636->20795|28667->20798|28733->20836|28749->20842|28793->20864|28891->20934|28907->20940|28946->20957|29050->21033|29066->21039|29111->21062|29209->21132|29256->21157|29334->21207|29383->21234|29467->21289|29497->21290|29532->21297|29673->21409|29703->21410|29743->21421|29849->21498|29879->21499|29911->21503|29940->21504|29969->21505|30038->21545|30068->21546|30103->21553|30244->21665|30274->21666|30314->21677|30420->21754|30450->21755|30482->21759|30511->21760|30540->21761|30610->21802|30640->21803|30675->21810|30816->21922|30846->21923|30886->21934|30992->22011|31022->22012|31054->22016|31083->22017|31112->22018|31154->22033|31170->22039|31205->22052|31243->22061|31273->22062|31306->22067|31404->22137|31433->22138|31465->22142|31495->22143|31528->22148|31626->22218|31655->22219|31691->22227|31785->22293|31814->22294|31890->22341|31920->22342|31951->22345|32076->22441|32106->22442|32148->22455|32414->22692|32444->22693|32481->22702|32533->22725|32563->22726|32602->22736|32709->22814|32739->22815|32779->22826|32839->22857|32869->22858|32908->22869|32938->22870|32975->22879|33026->22901|33056->22902|33095->22912|33161->22949|33191->22950|33231->22961|33290->22991|33320->22992|33359->23003|33389->23004|33425->23012|33454->23013|33491->23022|33521->23023|33553->23027|33582->23028|33650->23067|33680->23068|33711->23071|33821->23153|33850->23154|33882->23158|33949->23196|33979->23197|34010->23200|34132->23294|34161->23295|34193->23299|34257->23334|34287->23335|34318->23338|34431->23423|34460->23424|34492->23428|34546->23453|34576->23454|34607->23457|34691->23513|34720->23514|34752->23518|34811->23548|34841->23549|34875->23555|35757->24409|35786->24410|35818->24414|35877->24444|35907->24445|35938->24448|36031->24513|36060->24514|36090->24516
                  LINES: 28->1|35->4|37->6|37->6|37->6|39->8|39->8|41->10|42->11|42->11|43->12|44->13|44->13|45->14|62->31|62->31|63->32|67->36|67->36|68->37|69->38|69->38|70->39|86->55|86->55|87->56|91->60|91->60|92->61|93->62|93->62|94->63|111->80|111->80|112->81|117->86|117->86|118->87|123->92|123->92|123->92|125->94|125->94|125->94|129->98|129->98|129->98|141->110|141->110|142->111|146->115|146->115|146->115|146->115|146->115|146->115|147->116|147->116|147->116|148->117|148->117|148->117|148->117|148->117|148->117|148->117|149->118|150->119|151->120|151->120|152->121|152->121|152->121|152->121|154->123|154->123|154->123|156->125|157->126|160->129|160->129|160->129|162->131|162->131|162->131|164->133|164->133|164->133|172->141|172->141|172->141|172->141|172->141|172->141|173->142|173->142|173->142|174->143|174->143|174->143|174->143|174->143|174->143|174->143|175->144|176->145|185->154|185->154|185->154|200->169|200->169|200->169|215->184|215->184|215->184|218->187|218->187|218->187|229->198|229->198|229->198|232->201|232->201|232->201|241->210|241->210|241->210|244->213|244->213|244->213|254->223|254->223|254->223|254->223|254->223|254->223|255->224|255->224|255->224|256->225|256->225|256->225|256->225|256->225|256->225|256->225|257->226|258->227|268->237|268->237|268->237|299->268|299->268|300->269|312->281|312->281|312->281|313->282|315->284|315->284|315->284|316->285|316->285|316->285|317->286|321->290|321->290|321->290|330->299|331->300|335->304|335->304|335->304|336->305|336->305|336->305|336->305|336->305|336->305|336->305|336->305|336->305|336->305|336->305|337->306|338->307|347->316|350->319|350->319|351->320|357->326|357->326|357->326|369->338|369->338|369->338|376->345|377->346|393->362|393->362|393->362|404->373|404->373|404->373|417->386|417->386|417->386|447->416|447->416|447->416|453->422|453->422|453->422|454->423|454->423|454->423|454->423|455->424|455->424|455->424|456->425|456->425|456->425|456->425|456->425|456->425|457->426|457->426|457->426|459->428|460->429|490->459|490->459|490->459|496->465|496->465|496->465|497->466|497->466|497->466|497->466|498->467|498->467|498->467|499->468|499->468|499->468|499->468|499->468|499->468|500->469|500->469|500->469|502->471|503->472|535->504|535->504|535->504|539->508|539->508|539->508|540->509|540->509|540->509|540->509|541->510|541->510|541->510|541->510|541->510|541->510|542->511|542->511|542->511|543->512|543->512|543->512|545->514|546->515|559->528|563->532|565->534|565->534|566->535|566->535|566->535|566->535|567->536|567->536|567->536|568->537|568->537|568->537|570->539|570->539|571->540|571->540|573->542|573->542|574->543|576->545|576->545|577->546|578->547|578->547|579->548|579->548|579->548|580->549|580->549|581->550|583->552|583->552|584->553|585->554|585->554|586->555|586->555|586->555|587->556|587->556|588->557|590->559|590->559|591->560|592->561|592->561|593->562|593->562|593->562|595->564|595->564|595->564|595->564|595->564|596->565|597->566|597->566|597->566|597->566|598->567|599->568|599->568|601->570|602->571|602->571|604->573|604->573|605->574|607->576|607->576|608->577|615->584|615->584|616->585|616->585|616->585|617->586|617->586|617->586|618->587|619->588|619->588|620->589|620->589|621->590|621->590|621->590|622->591|622->591|622->591|623->592|624->593|624->593|625->594|625->594|626->595|626->595|627->596|627->596|628->597|628->597|630->599|630->599|631->600|633->602|633->602|635->604|635->604|635->604|636->605|638->607|638->607|640->609|640->609|640->609|641->610|643->612|643->612|645->614|645->614|645->614|646->615|648->617|648->617|650->619|650->619|650->619|652->621|677->646|677->646|679->648|679->648|679->648|680->649|682->651|682->651|684->653
                  -- GENERATED --
              */
          