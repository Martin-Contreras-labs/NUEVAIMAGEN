
package viewsMnuCotiOdo.html

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

object cotiOdoIngreso extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template11[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Cliente],List[tables.Proyecto],List[tables.Regiones],forms.FormCotizaOdo,List[List[String]],List[List[String]],Long,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu, listClientes: List[tables.Cliente], listProyectos: List[tables.Proyecto], listRegiones: List[tables.Regiones], formCotizaOdo: forms.FormCotizaOdo, listadoServicios: List[List[String]], listCotiOdo: List[List[String]], numDec: Long, listEquipos: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
"""),_display_(/*3.2*/main("")/*3.10*/ {_display_(Seq[Any](format.raw/*3.12*/("""

"""),_display_(/*5.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*5.50*/("""

	"""),_display_(/*7.3*/modalEquipoDescripcion()),format.raw/*7.27*/("""

	"""),format.raw/*9.2*/("""<!-- MODAL CREA CLIENTES -->
		"""),_display_(/*10.4*/modalClienteNuevo(mapDiccionario, listRegiones)),format.raw/*10.51*/("""
		"""),format.raw/*11.3*/("""<script>
			const clienteGrabaAjax = (id_cliente) =>"""),format.raw/*12.44*/("""{"""),format.raw/*12.45*/("""
				"""),format.raw/*13.5*/("""$('#id_cliente').val(id_cliente);
				$('#rutCliente').val($("#clienteRut").val());
				$('#nombreCliente').val($("#clienteNickName").val());
				let tabla = document.getElementById('tablaListaClientes');
				let row = tabla.insertRow(tabla.rows.length);
				let cell = row.insertCell(0);
				cell.style.textAlign = "left";
				cell.innerHTML = $("#clienteRut").val();
				cell = row.insertCell(1);
				cell.style.textAlign = "left";
				cell.innerHTML = $("#clienteNickName").val();
				cell = row.insertCell(2);
				cell.style.textAlign = "left";
				cell.innerHTML = $("#clienteNombre").val();
				row.setAttribute("onClick", "$('#id_cliente').val('" + id_cliente + "'); $('#rutCliente').val('" + $("#clienteRut").val() + "');$('#nombreCliente').val('" + $("#clienteNickName").val() + "');");
				row.setAttribute("data-dismiss", "modal");
			"""),format.raw/*29.4*/("""}"""),format.raw/*29.5*/("""
		"""),format.raw/*30.3*/("""</script>
	<!-- FIN MODAL CREA CLIENTES -->

	<!-- MODAL CREA PROYECTOS -->
		"""),_display_(/*34.4*/modalProyectoNuevo(mapDiccionario, listRegiones)),format.raw/*34.52*/("""
		"""),format.raw/*35.3*/("""<script>
			const proyectoGrabaAjax = (id_proyecto) =>"""),format.raw/*36.46*/("""{"""),format.raw/*36.47*/("""
				"""),format.raw/*37.5*/("""$('#id_proyecto').val(id_proyecto);
				$('#nombreProyecto').val($("#proyectoNickName").val());
				let tabla = document.getElementById('tablaListaProyectos');
				let row = tabla.insertRow(tabla.rows.length);
				let cell = row.insertCell(0);
				cell.style.textAlign = "left";
				cell.innerHTML = $("#proyectoNickName").val();
				cell = row.insertCell(1);
				cell.style.textAlign = "left";
				cell.innerHTML = $("#proyectoNombre").val();
				cell = row.insertCell(2);
				cell.style.textAlign = "left";
				cell.innerHTML = "";
				row.setAttribute("onClick", "$('#id_proyecto').val('" + id_proyecto + "'); $('#nombreProyecto').val('" + $("#proyectoNickName").val() + "');");
				row.setAttribute("data-dismiss", "modal");
			"""),format.raw/*52.4*/("""}"""),format.raw/*52.5*/("""
		"""),format.raw/*53.3*/("""</script>
	<!-- FIN MODAL CREA PROYECTOS -->


	<form action="/routes2/cotiOdoNuevo/" enctype="multipart/form-data" method="POST" onsubmit="return validarForm();">
		<div id="mostrarmostrar" style="display:block;">
			"""),_display_(/*59.5*/barraTitulo(mapDiccionario, "INGRESO DE NUEVA COTIZACION ODO", "")),format.raw/*59.71*/("""
			"""),format.raw/*60.4*/("""<div class="row  justify-content-md-center">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<table class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<tr>
								<td width="230px">
									<input type="hidden" id="id_cotiOdo" name="id_cotiOdo" value=""""),_display_(/*66.73*/formCotizaOdo/*66.86*/.id_cotiOdo),format.raw/*66.97*/("""">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Nro.Cotizacion</span>
								  		</div>
								  		<input type="text" class="form-control left"
								  				id="numeroCoti"
												name="numeroCoti"
												value = """"),_display_(/*74.23*/formCotizaOdo/*74.36*/.numeroCoti),format.raw/*74.47*/(""""
												onkeydown="return ingresoInt(window.event)"
												onchange="if(value.trim()=='')"""),format.raw/*76.43*/("""{"""),format.raw/*76.44*/("""value='"""),_display_(/*76.52*/formCotizaOdo/*76.65*/.numeroCoti),format.raw/*76.76*/("""';"""),format.raw/*76.78*/("""}"""),format.raw/*76.79*/("""else"""),format.raw/*76.83*/("""{"""),format.raw/*76.84*/("""validarNumero(value);"""),format.raw/*76.105*/("""}"""),format.raw/*76.106*/("""">
									</div>
								</td>
								<td width="230px">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Fecha</span>
								  		</div>
								  		<input type="date" class="form-control center"
								  			name="fechaCoti"
								  			id="fechaCoti"
								  			onblur="if(!limitaFecha(value,720,10)) value='"""),_display_(/*87.61*/formCotizaOdo/*87.74*/.fechaCoti),format.raw/*87.84*/("""';"
								  			value=""""),_display_(/*88.22*/formCotizaOdo/*88.35*/.fechaCoti),format.raw/*88.45*/(""""
						        			required>
									</div>
								</td>
								<td  width="230px">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">"""),_display_(/*95.65*/mapDiccionario/*95.79*/.get("RUT")),format.raw/*95.90*/(""" """),format.raw/*95.91*/("""Cliente</span>
								  		</div>
								  		<input type="hidden" id="id_cliente" name="id_cliente" value=""""),_display_(/*97.76*/formCotizaOdo/*97.89*/.id_cliente),format.raw/*97.100*/("""">
										<input type="text" class="form-control"
		  										id="rutCliente"
		  										value=""""),_display_(/*100.23*/formCotizaOdo/*100.36*/.rutCliente),format.raw/*100.47*/(""""
		  										style="background:white"
		  										onclick="$('#listaCliente').modal('show');"
		  										readonly>
									</div>
								</td>
								<td>
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Cliente</span>
								  		</div>
											<input type="text" class="form-control left"
													id="nombreCliente"
													value=""""),_display_(/*113.22*/formCotizaOdo/*113.35*/.nickCliente),format.raw/*113.47*/(""""
													style="background:white"
													onclick='$("#listaCliente").modal("show")'
													required
													readonly>
									</div>
								</td>
								<td>
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Proyecto</span>
								  		</div>
										<input type="hidden" id="id_proyecto" name="id_proyecto" value=""""),_display_(/*125.76*/formCotizaOdo/*125.89*/.id_proyecto),format.raw/*125.101*/("""">
										<input type="text" class="form-control left"
												id="nombreProyecto"
												value=""""),_display_(/*128.21*/formCotizaOdo/*128.34*/.nickProyecto),format.raw/*128.47*/(""""
												style="background:white"
												onclick='$("#listaProyecto").modal("show")'
												readonly>
									</div>
								</td>
								<td>
									<span class="btn btn-info btn-sm btn-file" style="font-size: 8px;">
										<div id="txtBtn">Adjuntar</div>
				    					<input type="file" id="docAdjunto" name="docAdjunto" onchange="LimitAttach(this.form, this.form.docAdjunto.value);">
									</span>
								</td>
							</tr>
							<tr>
								<input type="hidden" id="id_bodegaEmpresa" name="id_bodegaEmpresa" value=""""),_display_(/*142.84*/formCotizaOdo/*142.97*/.id_bodegaEmpresa),format.raw/*142.114*/("""">
								<td colspan="20">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Observaciones</span>
								  		</div>
	  									<textarea class="form-control"
	  										name="observaciones"
	  										autocomplete="off">"""),_display_(/*150.34*/formCotizaOdo/*150.47*/.observaciones),format.raw/*150.61*/("""</textarea>
									</div>
								</td>
							</tr>
						</thead>
					</table>
					<hr>

					<div class="noprint" align="center">
						<div class="row justify-content-md-center" >
							<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
								<input id="btnCopiaCoti" type="button"  value="Copiar desde otra cotización" class="btn btn-warning btn-sm rounded-pill btn-block"
									onclick="$('#modalListaCotizacion').modal('show');">&nbsp;&nbsp;&nbsp;
							</div>
							<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
								<input id="btnCargaExcel" type="button"  value="Copiar desde un Excel" class="btn btn-info btn-sm rounded-pill btn-block"
									onclick="$('#modalCargaMasiva').modal('show');">&nbsp;&nbsp;&nbsp;
							</div>
						</div>
					</div>

					<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid" style="width:100%">
						<thead style="background-color: #eeeeee">
							<TR>
								<TH>FAMILIA</TH>
								<TH>CODIGO</TH>
								<TH style="min-width:30%">SERVICIO</TH>
								<TH>UN</TH>
								<TH>CANTIDAD</TH>
						        <TH>MON</TH>
								<TH>PRECIO<br>UNITARIO</TH>
								<TH>TOTAL</TH>
								<TH>Aplica<br>Minimo</TH>
								<TH>Cantidad<br>Minimo</TH>
								<TH>Precio<br>Adicional</TH>
								<TH>EQUIPO<br>ASOCIADO</TH>
								<TH style = "display:none">id_servicio</TH>
								<TH style = "display:none">id_equipo</TH>

							</TR>
						</thead>
						<tbody>
							"""),_display_(/*192.9*/for(lista1 <- listadoServicios) yield /*192.40*/{_display_(Seq[Any](format.raw/*192.41*/("""
								"""),format.raw/*193.9*/("""<TR>
									<td style="text-align:left;">
										<input type="hidden" name="id_servicio[]" value=""""),_display_(/*195.61*/lista1/*195.67*/.get(0)),format.raw/*195.74*/("""">
										<input type="hidden" name="id_moneda[]" value=""""),_display_(/*196.59*/lista1/*196.65*/.get(13)),format.raw/*196.73*/("""">
										"""),_display_(/*197.12*/lista1/*197.18*/.get(1)),format.raw/*197.25*/("""
									"""),format.raw/*198.10*/("""</td>
									<td style= "text-align: left;">"""),_display_(/*199.42*/lista1/*199.48*/.get(2)),format.raw/*199.55*/("""</td>
									<td style= "text-align: left;">"""),_display_(/*200.42*/lista1/*200.48*/.get(3)),format.raw/*200.55*/("""</td>
									<td style= "text-align: center;">"""),_display_(/*201.44*/lista1/*201.50*/.get(4)),format.raw/*201.57*/("""</td>
									<td style="text-align:center;">
										<input type="text" class="cantidad form-control height23px right width80px"
											name="cantidad[]"
											id="cantidad_"""),_display_(/*205.26*/lista1/*205.32*/.get(0)),format.raw/*205.39*/(""""
											value=""""),_display_(/*206.20*/lista1/*206.26*/.get(5)),format.raw/*206.33*/(""""
											onfocus="value=value.replace(/,/g,''); cantAux = value;"
											onblur = "value = formatStandar(value, '2');"
											onkeydown="return ingresoDouble(window.event)"
											autocomplete="off"
											onchange="if(value=='') value=0; calculaLinea('"""),_display_(/*211.60*/lista1/*211.66*/.get(0)),format.raw/*211.73*/("""', '"""),_display_(/*211.78*/lista1/*211.84*/.get(12)),format.raw/*211.92*/("""');">
									</td>
									<td style="text-align:center;">"""),_display_(/*213.42*/lista1/*213.48*/.get(6)),format.raw/*213.55*/("""</td>
									<td style="text-align:center;">
										<input type="text" class="form-control height23px right width100px"
											name="precio[]"
											id="precio_"""),_display_(/*217.24*/lista1/*217.30*/.get(0)),format.raw/*217.37*/(""""
											value=""""),_display_(/*218.20*/lista1/*218.26*/.get(7)),format.raw/*218.33*/(""""
											onfocus="value=value.replace(/,/g,'');"
											onblur = "value = formatStandar(value, '"""),_display_(/*220.53*/lista1/*220.59*/.get(12)),format.raw/*220.67*/("""');"
											onkeydown="return ingresoDouble(window.event)"
											autocomplete="off"
											onchange="if(value=='') value=0; calculaLinea('"""),_display_(/*223.60*/lista1/*223.66*/.get(0)),format.raw/*223.73*/("""', '"""),_display_(/*223.78*/lista1/*223.84*/.get(12)),format.raw/*223.92*/("""');">
									</td>
									<td id="total_"""),_display_(/*225.25*/lista1/*225.31*/.get(0)),format.raw/*225.38*/("""" class="total" style= "text-align: right;">"""),_display_(/*225.83*/lista1/*225.89*/.get(8)),format.raw/*225.96*/("""</td>
									<td  style="text-align:center;">
										<input type="hidden" name="aplicaMinimo[]" id="aplicaMinimo_"""),_display_(/*227.72*/lista1/*227.78*/.get(0)),format.raw/*227.85*/("""" value=""""),_display_(/*227.95*/lista1/*227.101*/.get(9)),format.raw/*227.108*/("""">
										"""),_display_(if(lista1.get(9).equals("0"))/*228.41*/{_display_(Seq[Any](format.raw/*228.42*/("""
											"""),format.raw/*229.12*/("""<input type="checkbox" id="checkbox_"""),_display_(/*229.49*/lista1/*229.55*/.get(0)),format.raw/*229.62*/("""" onchange="checkAplicaMinimo('"""),_display_(/*229.94*/lista1/*229.100*/.get(0)),format.raw/*229.107*/("""');">
										""")))}else/*230.16*/{_display_(Seq[Any](format.raw/*230.17*/("""
											"""),format.raw/*231.12*/("""<input type="checkbox" id="checkbox_"""),_display_(/*231.49*/lista1/*231.55*/.get(0)),format.raw/*231.62*/("""" onchange="checkAplicaMinimo('"""),_display_(/*231.94*/lista1/*231.100*/.get(0)),format.raw/*231.107*/("""');" checked>
										""")))}),format.raw/*232.12*/("""
									"""),format.raw/*233.10*/("""</td>
									<td style="text-align:center;">
										<input type="text" class="cantidad form-control height23px right width80px"
											name="cantidadMinimo[]"
											id="cantidadMinimo_"""),_display_(/*237.32*/lista1/*237.38*/.get(0)),format.raw/*237.45*/(""""
											value=""""),_display_(/*238.20*/lista1/*238.26*/.get(10)),format.raw/*238.34*/(""""
											onfocus="value=value.replace(/,/g,''); cantAux = value;"
											onblur = "value = formatStandar(value, '2');"
											onkeydown="return ingresoDouble(window.event)"
											autocomplete="off"
											onchange="if(value=='') value=0;">
									</td>
									<td style="text-align:center;">
										<input type="text" class="form-control height23px right width100px"
											name="precioAdicional[]"
											id="precioAdicional_"""),_display_(/*248.33*/lista1/*248.39*/.get(0)),format.raw/*248.46*/(""""
											value=""""),_display_(/*249.20*/lista1/*249.26*/.get(11)),format.raw/*249.34*/(""""
											onfocus="value=value.replace(/,/g,'');"
											onblur = "value = formatStandar(value, '"""),_display_(/*251.53*/lista1/*251.59*/.get(12)),format.raw/*251.67*/("""');"
											onkeydown="return ingresoDouble(window.event)"
											autocomplete="off"
											onchange="if(value=='') value=0;">
									</td>
									<td style= "text-align: left;">
										<input type="text" class="form-control"
										id="equipoAsociado_"""),_display_(/*258.31*/lista1/*258.37*/.get(0)),format.raw/*258.44*/(""""
										value=" - "
										style="background:white"
										onclick="auxEquipoAsociado = '"""),_display_(/*261.42*/lista1/*261.48*/.get(0)),format.raw/*261.55*/("""'; $('#listaEquipos').modal('show');"
										readonly>
									</td>
									<td style = "display:none" id="idServicio_"""),_display_(/*264.53*/lista1/*264.59*/.get(0)),format.raw/*264.66*/("""">"""),_display_(/*264.69*/lista1/*264.75*/.get(0)),format.raw/*264.82*/("""</td>
									<td style = "display:none" >
										<input type="hidden" id="idEquipo_"""),_display_(/*266.46*/lista1/*266.52*/.get(0)),format.raw/*266.59*/("""" name="id_equipo[]" value="0">
									</td>
								</TR>
				 			""")))}),format.raw/*269.10*/("""
						"""),format.raw/*270.7*/("""</tbody>
						<tfoot id="tfoot" style="background-color: #eeeeee; display:none">
							<TR>
								<TH colspan="4" style= "text-align: right;">SUB-TOTALES </TH>
								<TH style="text-align:right;"><div id="subTotCant">0.00</div></TH>
								<TH colspan="2"></TH>
								<TH style="text-align:right;"><div id="subTotPrecio">0.00</div></TH>
								<TH colspan="4"></TH>
								<TH style = "display:none"></TH>
								<TH style = "display:none"></TH>
							</TR>
							<TR>
								<TH colspan="4" style="text-align:right;">DESCUENTO</TH>
								<TH colspan="3"></TH>
								<TH style="text-align:center;">
									<input type="text" class="form-control height23px right"
										id="dctoOdo"
										name="dctoOdo"
										value=""""),_display_(/*288.19*/formCotizaOdo/*288.32*/.dctoOdo),format.raw/*288.40*/(""" """),format.raw/*288.41*/("""%"
										onfocus="value=value.replace(/,/g,'').replace(/%/g,'').replace(/ /g,'')"
										onblur = "value = formatPorcentaje(value);"
										onkeydown="return ingresoDouble(window.event)"
										onchange="sumaTotales();">
									</td>
								</TH>
								<TH colspan="4"></TH>
								<TH style = "display:none"></TH>
								<TH style = "display:none"></TH>
							</TR>
							<TR>
								<TH colspan="4" style= "text-align: right;">TOTALES </TH>
								<TH style="text-align:right;"><div id="granTotCant">0.00</div></TH>
								<TH colspan="2"></TH>
								<TH style="text-align:right;"><div id="granTotPrecio">0.00</div></TH>
								<TH colspan="4"></TH>
								<TH style = "display:none"></TH>
								<TH style = "display:none"></TH>
							</TR>
						</tfoot>
					</table>
				</div>
			</div>


			<div class="noprint" align="left">
				<div class="row justify-content-md-center" >
					<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
						<input type="button"  value="Cancelar" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
					</div>
					<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
						<input type="button"  id="verifica" value='Verificar' class="btn btn-success btn-sm rounded-pill btn-block" onclick="verificaCotizaOdo();" >
					</div>
					<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
						<input type="button"  id="modifica" value='Modificar' class="btn btn-warning btn-sm rounded-pill btn-block" onclick="modificaCotizacion();" style="visibility:hidden">
					</div>
					<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
						<input type="submit"  id="aplica" value='Confirmar' class="btn btn-primary btn-sm rounded-pill btn-block" style="visibility:hidden">
					</div>
				</div>
			</div>

		</div>
	</form>
	<br><br><br><br><br>





	<div id="listaCliente" class="modal" role="dialog" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog modal-dialog-scrollable modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">SELECCIONAR CLIENTE</h5>
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				          <span aria-hidden="true">&times;</span>
				        </button>
				</div>
				<div class="modal-body">
					<div class="noprint" align="center">
						<a href="#" onclick="$('#listaCliente').modal('hide'); $('#modalClienteNuevo').modal('show');">
							<kbd style="background-color: #73C6B6">Agregar Nuevo</kbd>
						</a>
					</div>
					<table id="tablaListaClientes" class="table table-sm table-bordered table-condensed table-hover table-fluid">
						<thead style="background-color: #eeeeee">
							<TR>
								<TH>"""),_display_(/*357.14*/mapDiccionario/*357.28*/.get("RUT")),format.raw/*357.39*/("""</TH>
								<TH>NICK</TH>
								<TH>NOMBRE</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*363.9*/for(lista1 <- listClientes) yield /*363.36*/{_display_(Seq[Any](format.raw/*363.37*/("""
								"""),format.raw/*364.9*/("""<TR onClick="$('#id_cliente').val("""),_display_(/*364.44*/lista1/*364.50*/.getId()),format.raw/*364.58*/("""); $('#rutCliente').val('"""),_display_(/*364.84*/lista1/*364.90*/.getRut()),format.raw/*364.99*/("""');$('#nombreCliente').val('"""),_display_(/*364.128*/lista1/*364.134*/.getNickName()),format.raw/*364.148*/("""');" data-dismiss="modal">
									<td  style="text-align:left;">"""),_display_(/*365.41*/lista1/*365.47*/.getRut()),format.raw/*365.56*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*366.41*/lista1/*366.47*/.getNickName()),format.raw/*366.61*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*367.41*/lista1/*367.47*/.getNombre()),format.raw/*367.59*/("""</td>
								</TR>
				 			""")))}),format.raw/*369.10*/("""
						"""),format.raw/*370.7*/("""</tbody>
					</table>
	   				<div class="row">
						<div class="col-sm-12" style="text-align:center">
							<button type="button" class="btn btn-sm  btn-warning" style="font-size: 10px;" data-dismiss="modal">Cancelar</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div id="listaEquipos" class="modal" role="dialog" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog modal-dialog-scrollable modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">SELECCIONAR EQUIPO ASOCIADO</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<table id="tablaListaEquipos" class="table table-sm table-bordered table-condensed table-hover table-fluid">
						<thead style="background-color: #eeeeee">
							<TR>
								<TH>CODIGO</TH>
								<TH>EQUIPO</TH>
								<TH>UBICACION</TH>
							</TR>
						</thead>
						<tbody>
							<TR onClick="
									$('#idEquipo_'+auxEquipoAsociado).val('0');
									$('#equipoAsociado_'+auxEquipoAsociado).val(' - ');" data-dismiss="modal">
								<td  style="text-align:left;">Sin Equipo</td>
								<td  style="text-align:left;">Sin Equipo</td>
								<td  style="text-align:left;">Sin Equipo</td>
							</TR>
							"""),_display_(/*408.9*/for(lista1 <- listEquipos) yield /*408.35*/{_display_(Seq[Any](format.raw/*408.36*/("""
								"""),format.raw/*409.9*/("""<TR onClick="
										$('#idEquipo_'+auxEquipoAsociado).val('"""),_display_(/*410.51*/lista1/*410.57*/.get(0)),format.raw/*410.64*/("""');
										$('#equipoAsociado_'+auxEquipoAsociado).val('"""),_display_(/*411.57*/lista1/*411.63*/.get(1)),format.raw/*411.70*/(""" """),format.raw/*411.71*/("""- """),_display_(/*411.74*/lista1/*411.80*/.get(2)),format.raw/*411.87*/("""');" data-dismiss="modal">
									<td  style="text-align:left;">"""),_display_(/*412.41*/lista1/*412.47*/.get(1)),format.raw/*412.54*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*413.41*/lista1/*413.47*/.get(2)),format.raw/*413.54*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*414.41*/lista1/*414.47*/.get(3)),format.raw/*414.54*/("""</td>

								</TR>
							""")))}),format.raw/*417.9*/("""
						"""),format.raw/*418.7*/("""</tbody>
					</table>
					<div class="row">
						<div class="col-sm-12" style="text-align:center">
							<button type="button" class="btn btn-sm  btn-warning" style="font-size: 10px;" data-dismiss="modal">Cancelar</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div id="listaProyecto" class="modal" role="dialog" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog modal-dialog-scrollable modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">SELECCIONAR CLIENTE</h5>
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				          <span aria-hidden="true">&times;</span>
				        </button>
				</div>
				<div class="modal-body">
					<div class="noprint" align="center">
						<a href="#" onclick="$('#listaProyecto').modal('hide'); $('#modalProyectoNuevo').modal('show');">
							<kbd style="background-color: #73C6B6">Agregar Nuevo</kbd>
						</a>
					</div>
					<table id="tablaListaProyectos" class='table table-sm table-bordered table-condensed table-hover table-fluid'>
						<thead style="background-color: #eeeeee">
							<TR>
								<TH>NICK</TH>
								<TH>NOMBRE</TH>
								<TH>"""),_display_(/*450.14*/mapDiccionario/*450.28*/.get("Comuna")),format.raw/*450.42*/("""</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*454.9*/for(lista1 <- listProyectos) yield /*454.37*/{_display_(Seq[Any](format.raw/*454.38*/("""
								"""),format.raw/*455.9*/("""<TR onClick="$('#id_proyecto').val("""),_display_(/*455.45*/lista1/*455.51*/.getId()),format.raw/*455.59*/("""); $('#nombreProyecto').val('"""),_display_(/*455.89*/lista1/*455.95*/.getNickName()),format.raw/*455.109*/("""');" data-dismiss="modal">
									<td  style="text-align:left;">"""),_display_(/*456.41*/lista1/*456.47*/.getNickName()),format.raw/*456.61*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*457.41*/lista1/*457.47*/.getNombre()),format.raw/*457.59*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*458.41*/lista1/*458.47*/.getComuna()),format.raw/*458.59*/("""</td>
								</TR>
				 			""")))}),format.raw/*460.10*/("""
						"""),format.raw/*461.7*/("""</tbody>
					</table>
	   				<div class="row">
						<div class="col-sm-12" style="text-align:center">
							<button type="button" class="btn btn-sm  btn-warning" style="font-size: 10px;" data-dismiss="modal">Cancelar</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>



	<div id="modalListaCotizacion" class="modal" role="dialog" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog modal-dialog-scrollable modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">COTIZACIONES DISPONIBLES</h5>
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				          <span aria-hidden="true">&times;</span>
				        </button>
				</div>
				<div class="modal-body">
					<table id="tablaListaCotizacion" class="table table-sm table-bordered table-condensed table-hover table-fluid">
						<thead style="background-color: #eeeeee">
							<TR>
								<TH>NUMERO</TH>
								<TH>FECHA</TH>
								<TH>CLIENTE</TH>
								<TH>PROYECTO</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*495.9*/for(lista1 <- listCotiOdo) yield /*495.35*/{_display_(Seq[Any](format.raw/*495.36*/("""
								"""),format.raw/*496.9*/("""<TR onClick="seleccionaCotizacion('"""),_display_(/*496.45*/lista1/*496.51*/.get(0)),format.raw/*496.58*/("""')" data-dismiss="modal">
									<td  style="text-align:center;">"""),_display_(/*497.43*/lista1/*497.49*/.get(1)),format.raw/*497.56*/("""</td>
									<td  style="text-align:center;">
										<div style="display:none">"""),_display_(/*499.38*/utilities/*499.47*/.Fechas.AAMMDD(lista1.get(2))),format.raw/*499.76*/("""</div>
										"""),_display_(/*500.12*/utilities/*500.21*/.Fechas.DDMMAA(lista1.get(2))),format.raw/*500.50*/("""
									"""),format.raw/*501.10*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*502.41*/lista1/*502.47*/.get(3)),format.raw/*502.54*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*503.41*/lista1/*503.47*/.get(4)),format.raw/*503.54*/("""</td>
								</TR>
				 			""")))}),format.raw/*505.10*/("""
						"""),format.raw/*506.7*/("""</tbody>
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

	<div id="modalCargaMasiva" class="modal" role="dialog" data-backdrop="static" data-keyboard="false">
	  <div class="modal-dialog modal-sm modal-dialog-scrollable" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title">Carga masiva de equipos</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">

			<div class="row">
				<div class="col-sm-12" style="text-align:center">
					<form action="/routes2/cotiOdoPlantilla/" method="POST">
						<button type="submit" class="btn btn-sm  btn-success" style="font-size: 10px;">DESCARGAR PLANTILLA</button>
					</form>
					<br>
					<form id="cotiOdoValidarPlantilla" action="/routes2/cotiOdoValidarPlantilla/" method="POST" enctype="multipart/form-data">
						<span class="btn btn-info btn-sm btn-file" style="font-size: 8px;">
							<div>SUBIR ARCHIVO</div>
							<input type="hidden" id="id_bodegaEmpresa" name="id_bodegaEmpresa" value=""""),_display_(/*538.83*/formCotizaOdo/*538.96*/.id_bodegaEmpresa),format.raw/*538.113*/("""">
							<input type="file" id="archivoXLSX" name="archivoXLSX" value="" onchange="subirArchivo(this.form, this.form.archivoXLSX.value)">
						</span>
					</form>
					<br>
					<button type="button" class="btn btn-sm  btn-warning" style="font-size: 10px;" data-dismiss="modal">Cancelar</button>
				</div>
			</div>
			<br>
	      </div>
	    </div>
	  </div>
	</div>


""")))}),format.raw/*553.2*/("""



"""),format.raw/*557.1*/("""<script type="text/javascript">
	let auxEquipoAsociado = "";

	$(document).ready(function() """),format.raw/*560.31*/("""{"""),format.raw/*560.32*/("""
		"""),format.raw/*561.3*/("""$('#tablaListaClientes').DataTable("""),format.raw/*561.38*/("""{"""),format.raw/*561.39*/("""
	    	"""),format.raw/*562.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
	    	"order": [[ 1, "asc" ]],
	    	"language": """),format.raw/*565.19*/("""{"""),format.raw/*565.20*/("""
	        	"""),format.raw/*566.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*567.10*/("""}"""),format.raw/*567.11*/("""
	  	"""),format.raw/*568.5*/("""}"""),format.raw/*568.6*/(""" """),format.raw/*568.7*/(""");

		$('#tablaListaEquipos').DataTable("""),format.raw/*570.37*/("""{"""),format.raw/*570.38*/("""
			"""),format.raw/*571.4*/(""""fixedHeader": true,
			"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
			"order": [[ 1, "asc" ]],
			"language": """),format.raw/*574.16*/("""{"""),format.raw/*574.17*/("""
				"""),format.raw/*575.5*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
			"""),format.raw/*576.4*/("""}"""),format.raw/*576.5*/("""
		"""),format.raw/*577.3*/("""}"""),format.raw/*577.4*/(""" """),format.raw/*577.5*/(""");

		$('#tablaListaProyectos').DataTable("""),format.raw/*579.39*/("""{"""),format.raw/*579.40*/("""
	    	"""),format.raw/*580.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
	    	"order": [[ 0, "asc" ]],
	    	"language": """),format.raw/*583.19*/("""{"""),format.raw/*583.20*/("""
	        	"""),format.raw/*584.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*585.10*/("""}"""),format.raw/*585.11*/("""
	  	"""),format.raw/*586.5*/("""}"""),format.raw/*586.6*/(""" """),format.raw/*586.7*/(""");

		sumaTotales();
		
		$('#tablaPrincipal').DataTable("""),format.raw/*590.34*/("""{"""),format.raw/*590.35*/("""
				"""),format.raw/*591.5*/(""""responsive": true,
		    	"fixedHeader": true,
		    	"lengthMenu": [[15, 50, 100, 200, -1], [15, 50, 100, 200, "All"]],
		    	"order": [[ 2, "asc" ]],
		    	"language": """),format.raw/*595.20*/("""{"""),format.raw/*595.21*/("""
		        	"""),format.raw/*596.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*597.11*/("""}"""),format.raw/*597.12*/("""
		"""),format.raw/*598.3*/("""}"""),format.raw/*598.4*/(""" """),format.raw/*598.5*/(""");
		
		$('#tablaListaCotizacion').DataTable("""),format.raw/*600.40*/("""{"""),format.raw/*600.41*/("""
		    	"""),format.raw/*601.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 1, "desc" ],[ 0, "desc" ]],
		    	"language": """),format.raw/*604.20*/("""{"""),format.raw/*604.21*/("""
		        	"""),format.raw/*605.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*606.11*/("""}"""),format.raw/*606.12*/("""
		"""),format.raw/*607.3*/("""}"""),format.raw/*607.4*/(""" """),format.raw/*607.5*/(""");
		  
		document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*610.2*/("""}"""),format.raw/*610.3*/(""");
	
	const validarNumero = (numero) =>"""),format.raw/*612.35*/("""{"""),format.raw/*612.36*/("""
		"""),format.raw/*613.3*/("""var formData = new FormData();
		formData.append('numeroCoti',numero);
        $.ajax("""),format.raw/*615.16*/("""{"""),format.raw/*615.17*/("""
            """),format.raw/*616.13*/("""url: "/routes2/existeNumeroCotiOdoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*623.36*/("""{"""),format.raw/*623.37*/("""
				"""),format.raw/*624.5*/("""if(respuesta!="OK")"""),format.raw/*624.24*/("""{"""),format.raw/*624.25*/("""
					"""),format.raw/*625.6*/("""$("#numeroCoti").val(""""),_display_(/*625.29*/formCotizaOdo/*625.42*/.numeroCoti),format.raw/*625.53*/("""");
					alertify.alert(respuesta, function () """),format.raw/*626.44*/("""{"""),format.raw/*626.45*/(""" """),format.raw/*626.46*/("""}"""),format.raw/*626.47*/(""");
				"""),format.raw/*627.5*/("""}"""),format.raw/*627.6*/("""
	     	"""),format.raw/*628.8*/("""}"""),format.raw/*628.9*/("""
        """),format.raw/*629.9*/("""}"""),format.raw/*629.10*/(""");
	"""),format.raw/*630.2*/("""}"""),format.raw/*630.3*/("""
	
	
	
	"""),format.raw/*634.2*/("""const sumaTotales = () =>"""),format.raw/*634.27*/("""{"""),format.raw/*634.28*/("""
		"""),format.raw/*635.3*/("""$("#tablaPrincipal").dataTable().fnDestroy();
		let tabla = document.getElementById("tablaPrincipal");
		let totCant = 0;
		
		let cant = 0;
		$(".cantidad").each(function() """),format.raw/*640.34*/("""{"""),format.raw/*640.35*/("""
			"""),format.raw/*641.4*/("""let val = $(this).val().replace(/,/g,'');
			cant += parseFloat(val);
		"""),format.raw/*643.3*/("""}"""),format.raw/*643.4*/(""");
		$("#subTotCant").text(formatStandar(cant,'2'));
		$("#granTotCant").text(formatStandar(cant,'2'));
		
		let total = 0;
		$(".total").each(function() """),format.raw/*648.31*/("""{"""),format.raw/*648.32*/("""
			"""),format.raw/*649.4*/("""let val = $(this).text().replace(/,/g,'');
			total += parseFloat(val);
		"""),format.raw/*651.3*/("""}"""),format.raw/*651.4*/("""); 
		$("#subTotPrecio").text(formatStandar(total,'"""),_display_(/*652.49*/numDec),format.raw/*652.55*/("""'));
		
		let dctoOdo = $("#dctoOdo").val().replace(/,/g,'').replace("%","").trim();
		
		$("#granTotPrecio").text(formatStandar((total*(1-dctoOdo/100)),'"""),_display_(/*656.68*/numDec),format.raw/*656.74*/("""'));

	"""),format.raw/*658.2*/("""}"""),format.raw/*658.3*/("""
	
	
	
	
	"""),format.raw/*663.2*/("""const checkAplicaMinimo = (id_servicio) =>"""),format.raw/*663.44*/("""{"""),format.raw/*663.45*/("""
		"""),format.raw/*664.3*/("""let aplicaMinimo = $("#aplicaMinimo_"+id_servicio).val();
		if(aplicaMinimo==0)"""),format.raw/*665.22*/("""{"""),format.raw/*665.23*/("""
			"""),format.raw/*666.4*/("""$("#aplicaMinimo_"+id_servicio).val('1');
		"""),format.raw/*667.3*/("""}"""),format.raw/*667.4*/("""else"""),format.raw/*667.8*/("""{"""),format.raw/*667.9*/("""
			"""),format.raw/*668.4*/("""$("#aplicaMinimo_"+id_servicio).val('0');
		"""),format.raw/*669.3*/("""}"""),format.raw/*669.4*/("""
	"""),format.raw/*670.2*/("""}"""),format.raw/*670.3*/("""
	
	
	
	
	
	"""),format.raw/*676.2*/("""const calculaLinea = (id_servicio, numDec) =>"""),format.raw/*676.47*/("""{"""),format.raw/*676.48*/("""
		"""),format.raw/*677.3*/("""let cant = $("#cantidad_"+id_servicio).val().replace(/,/g,'');
		let precio = $("#precio_"+id_servicio).val().replace(/,/g,'');
		let total = parseFloat(cant) * parseFloat(precio);
		$("#total_"+id_servicio).text(formatStandar(total,numDec));
		if(verificando == 1)"""),format.raw/*681.23*/("""{"""),format.raw/*681.24*/("""
			"""),format.raw/*682.4*/("""sumaTotales();
		"""),format.raw/*683.3*/("""}"""),format.raw/*683.4*/("""
		
	"""),format.raw/*685.2*/("""}"""),format.raw/*685.3*/("""
	
	
	
	
	"""),format.raw/*690.2*/("""let verificando = 0;
	const verificaCotizaOdo = () =>"""),format.raw/*691.33*/("""{"""),format.raw/*691.34*/("""
		"""),format.raw/*692.3*/("""document.getElementById("btnCopiaCoti").style.display = "none";
		document.getElementById("btnCargaExcel").style.display = "none";
		sumaTotales();
		
		
		
		let subTotPrecio = $("#subTotPrecio").text().replace(/,/g,'');
		
		let sumaAux = parseFloat(subTotPrecio);
		
		if(sumaAux > 0)"""),format.raw/*702.18*/("""{"""),format.raw/*702.19*/("""
			"""),format.raw/*703.4*/("""let tabla = document.getElementById("tablaPrincipal");
			for(i=1; i<tabla.rows.length-3; i++)"""),format.raw/*704.40*/("""{"""),format.raw/*704.41*/("""
				"""),format.raw/*705.5*/("""let cant = $(".cantidad",tabla.rows[i].cells[4]).val().replace(/,/g,'');
				let precio = tabla.rows[i].cells[7].innerHTML.replace(/,/g,'');
				if((parseFloat(cant)+parseFloat(precio))<=0)"""),format.raw/*707.49*/("""{"""),format.raw/*707.50*/("""
					"""),format.raw/*708.6*/("""tabla.rows[i].style.display = 'none';
				"""),format.raw/*709.5*/("""}"""),format.raw/*709.6*/("""
			"""),format.raw/*710.4*/("""}"""),format.raw/*710.5*/("""
			"""),format.raw/*711.4*/("""verificando = 1;
			document.getElementById('tfoot').style.display = '';
			document.getElementById('modifica').style.visibility = 'visible';
			document.getElementById('aplica').style.visibility = 'visible';
			
			
		"""),format.raw/*717.3*/("""}"""),format.raw/*717.4*/("""else"""),format.raw/*717.8*/("""{"""),format.raw/*717.9*/("""
			"""),format.raw/*718.4*/("""$('#tablaPrincipal').DataTable("""),format.raw/*718.35*/("""{"""),format.raw/*718.36*/("""
		    	"""),format.raw/*719.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[15, 50, 100, 200, -1], [15, 50, 100, 200, "All"]],
		    	"order": [[ 2, "asc" ]],
		    	"language": """),format.raw/*722.20*/("""{"""),format.raw/*722.21*/("""
		        	"""),format.raw/*723.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*724.11*/("""}"""),format.raw/*724.12*/("""
			"""),format.raw/*725.4*/("""}"""),format.raw/*725.5*/(""" """),format.raw/*725.6*/(""");
			alertify.alert('NO PUEDE VERIFICAR UNA COTIZACION SIN CANTIDADES', function () """),format.raw/*726.83*/("""{"""),format.raw/*726.84*/(""" """),format.raw/*726.85*/("""}"""),format.raw/*726.86*/(""");
		"""),format.raw/*727.3*/("""}"""),format.raw/*727.4*/("""
	"""),format.raw/*728.2*/("""}"""),format.raw/*728.3*/("""
	
	
	
	
	
	
	
	
	
	
	
	
	
	"""),format.raw/*742.2*/("""const modificaCotizacion = () =>"""),format.raw/*742.34*/("""{"""),format.raw/*742.35*/("""
		"""),format.raw/*743.3*/("""let tabla = document.getElementById("tablaPrincipal");
		for(i=1; i<tabla.rows.length-3; i++)"""),format.raw/*744.39*/("""{"""),format.raw/*744.40*/("""
			"""),format.raw/*745.4*/("""tabla.rows[i].style.display = '';
		"""),format.raw/*746.3*/("""}"""),format.raw/*746.4*/("""
		"""),format.raw/*747.3*/("""$('#tablaPrincipal').DataTable("""),format.raw/*747.34*/("""{"""),format.raw/*747.35*/("""
		    	"""),format.raw/*748.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[15, 50, 100, 200, -1], [15, 50, 100, 200, "All"]],
		    	"order": [[ 2, "asc" ]],
		    	"language": """),format.raw/*751.20*/("""{"""),format.raw/*751.21*/("""
		        	"""),format.raw/*752.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*753.11*/("""}"""),format.raw/*753.12*/("""
		"""),format.raw/*754.3*/("""}"""),format.raw/*754.4*/(""" """),format.raw/*754.5*/(""");
		document.getElementById('tfoot').style.display = 'none';
		document.getElementById('modifica').style.visibility = 'hidden';
		document.getElementById('aplica').style.visibility = 'hidden';
		verificando = 0;
	"""),format.raw/*759.2*/("""}"""),format.raw/*759.3*/("""
	
	"""),format.raw/*761.2*/("""const seleccionaCotizacion = (id_cotiOdo) =>"""),format.raw/*761.46*/("""{"""),format.raw/*761.47*/("""
		"""),format.raw/*762.3*/("""document.getElementById("btnCopiaCoti").style.display = "none";
		document.getElementById("btnCargaExcel").style.display = "none";
		let formData = new FormData();
	    formData.append('id_cotiOdo',id_cotiOdo);
		$.ajax("""),format.raw/*766.10*/("""{"""),format.raw/*766.11*/("""
            """),format.raw/*767.13*/("""url: "/routes2/cotiOdoModificaJson/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*774.36*/("""{"""),format.raw/*774.37*/("""
	     		"""),format.raw/*775.9*/("""let obj = JSON.parse(respuesta);
	     		let mapCantidad = new Map();
	     		let mapPrecio = new Map();
	     		let mapAplicaMinimo = new Map();
	     		let mapCantidadMinimo = new Map();
	     		let mapPrecioAdicional = new Map();
	     		let mapNroDecimal = new Map();
	     		
	     		for(i=0;i<obj.length;i++)"""),format.raw/*783.34*/("""{"""),format.raw/*783.35*/("""
	     			"""),format.raw/*784.10*/("""mapCantidad.set(obj[i].id_servicio,obj[i].cantidad);
	     			mapPrecio.set(obj[i].id_servicio,obj[i].precio);
	     			mapAplicaMinimo.set(obj[i].id_servicio,obj[i].aplicaMinimo);
	     			mapCantidadMinimo.set(obj[i].id_servicio,obj[i].cantidadMinimo);
	     			mapPrecioAdicional.set(obj[i].id_servicio,obj[i].precioAdicional);
	     			mapNroDecimal.set(obj[i].id_servicio,obj[i].nroDecimal);
	     		"""),format.raw/*790.9*/("""}"""),format.raw/*790.10*/("""
	     		
				"""),format.raw/*792.5*/("""$("#tablaPrincipal").dataTable().fnDestroy();
				let tableReg = document.getElementById("tablaPrincipal");
				
				for (let i = 1; i < tableReg.rows.length-3; i++)"""),format.raw/*795.53*/("""{"""),format.raw/*795.54*/("""
					"""),format.raw/*796.6*/("""let cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
					let id_servicio = cellsOfRow[11].innerHTML;
					
					let cantidad = mapCantidad.get(id_servicio);
					
					if(cantidad != null)"""),format.raw/*801.26*/("""{"""),format.raw/*801.27*/("""
						"""),format.raw/*802.7*/("""let precio = mapPrecio.get(id_servicio);
						let aplicaMinimo = mapAplicaMinimo.get(id_servicio);
						let cantidadMinimo = mapCantidadMinimo.get(id_servicio);
						let precioAdicional = mapPrecioAdicional.get(id_servicio);
						let nroDecimales = mapNroDecimal.get(id_servicio);
						
			 			if(cantidad == null) cantidad = 0;
						if(aplicaMinimo == null) aplicaMinimo = 0;
						if(cantidadMinimo == null) cantidadMinimo = 0;
						if(precioAdicional == null) precioAdicional = 0;
						if(nroDecimales == null) nroDecimales = 0;
						
						$("#cantidad_"+id_servicio).val(cantidad);
						$("#precio_"+id_servicio).val(precio);
						$("#aplicaMinimo_"+id_servicio).val(aplicaMinimo);
						if(aplicaMinimo==1)"""),format.raw/*817.26*/("""{"""),format.raw/*817.27*/("""
							"""),format.raw/*818.8*/("""document.getElementById("checkbox_"+id_servicio).checked = true;
						"""),format.raw/*819.7*/("""}"""),format.raw/*819.8*/("""else"""),format.raw/*819.12*/("""{"""),format.raw/*819.13*/("""
							"""),format.raw/*820.8*/("""document.getElementById("checkbox_"+id_servicio).checked = false;
						"""),format.raw/*821.7*/("""}"""),format.raw/*821.8*/("""
						"""),format.raw/*822.7*/("""$("#cantidadMinimo_"+id_servicio).val(cantidadMinimo);
						$("#precioAdicional_"+id_servicio).val(precioAdicional);
						calculaLinea(id_servicio, nroDecimales);
					"""),format.raw/*825.6*/("""}"""),format.raw/*825.7*/("""
					
				"""),format.raw/*827.5*/("""}"""),format.raw/*827.6*/("""
				"""),format.raw/*828.5*/("""sumaTotales();
				$('#tablaPrincipal').DataTable("""),format.raw/*829.36*/("""{"""),format.raw/*829.37*/("""
			    	"""),format.raw/*830.9*/(""""fixedHeader": true,
			    	"lengthMenu": [[15, 50, 100, 200, -1], [15, 50, 100, 200, "All"]],
			    	"order": [[ 2, "asc" ]],
			    	"language": """),format.raw/*833.21*/("""{"""),format.raw/*833.22*/("""
			        	"""),format.raw/*834.13*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
			        """),format.raw/*835.12*/("""}"""),format.raw/*835.13*/("""
				"""),format.raw/*836.5*/("""}"""),format.raw/*836.6*/(""");
	     	"""),format.raw/*837.8*/("""}"""),format.raw/*837.9*/("""


        """),format.raw/*840.9*/("""}"""),format.raw/*840.10*/(""");
	"""),format.raw/*841.2*/("""}"""),format.raw/*841.3*/("""

	"""),format.raw/*843.2*/("""const validarForm = () =>"""),format.raw/*843.27*/("""{"""),format.raw/*843.28*/("""
		"""),format.raw/*844.3*/("""let flag = true;
		sumaTotales();
		let granTotPrecio = $("#granTotPrecio").text().replace(/,/g,'');
		if(granTotPrecio <= 0)"""),format.raw/*847.25*/("""{"""),format.raw/*847.26*/("""
			"""),format.raw/*848.4*/("""flag = false;
			alertify.alert('NO PUEDE CONFIRMAR UNA COTIZACION SIN CANTIDADES Y PRECIOS', function () """),format.raw/*849.93*/("""{"""),format.raw/*849.94*/("""
				"""),format.raw/*850.5*/("""return(flag);
     		"""),format.raw/*851.8*/("""}"""),format.raw/*851.9*/(""");
		"""),format.raw/*852.3*/("""}"""),format.raw/*852.4*/("""else if($("#id_cliente").val()=='0')"""),format.raw/*852.40*/("""{"""),format.raw/*852.41*/("""
			"""),format.raw/*853.4*/("""flag = false;
			alertify.alert('NO PUEDE CONFIRMAR UNA COTIZACION SIN ASIGNAR UN CLIENTE', function () """),format.raw/*854.91*/("""{"""),format.raw/*854.92*/("""
				"""),format.raw/*855.5*/("""return(flag);
     		"""),format.raw/*856.8*/("""}"""),format.raw/*856.9*/(""");
		"""),format.raw/*857.3*/("""}"""),format.raw/*857.4*/("""else"""),format.raw/*857.8*/("""{"""),format.raw/*857.9*/("""
			"""),format.raw/*858.4*/("""let tabla = document.getElementById("tablaPrincipal");
			for(i=1; i<tabla.rows.length-3; i++)"""),format.raw/*859.40*/("""{"""),format.raw/*859.41*/("""
				"""),format.raw/*860.5*/("""let cant = $(".cantidad",tabla.rows[i].cells[4]).val().replace(/,/g,'');
				if(parseFloat(cant)<=0)"""),format.raw/*861.28*/("""{"""),format.raw/*861.29*/("""
					"""),format.raw/*862.6*/("""tabla.deleteRow(i);
					i--;
				"""),format.raw/*864.5*/("""}"""),format.raw/*864.6*/("""
			"""),format.raw/*865.4*/("""}"""),format.raw/*865.5*/("""
			"""),format.raw/*866.4*/("""return(true);
		"""),format.raw/*867.3*/("""}"""),format.raw/*867.4*/("""
		"""),format.raw/*868.3*/("""return(flag);
	"""),format.raw/*869.2*/("""}"""),format.raw/*869.3*/("""
	


	"""),format.raw/*873.2*/("""let extArray = new Array(".gif", ".jpg", ".png", ".jpeg", ".pdf", ".xls", ".doc", ".xlsx", ".docx", ".tar", ".zip", ".rar");
	const LimitAttach = (form, file) => """),format.raw/*874.38*/("""{"""),format.raw/*874.39*/("""
		"""),format.raw/*875.3*/("""let allowSubmit = false;
		if (!file) return;
		while (file.indexOf("\\") != -1)"""),format.raw/*877.35*/("""{"""),format.raw/*877.36*/("""
			"""),format.raw/*878.4*/("""file = file.slice(file.indexOf("\\") + 1);
		"""),format.raw/*879.3*/("""}"""),format.raw/*879.4*/("""
		"""),format.raw/*880.3*/("""let extencion = file.slice(file.lastIndexOf(".")).toLowerCase();
		for (var i = 0; i < extArray.length; i++) """),format.raw/*881.45*/("""{"""),format.raw/*881.46*/("""
			"""),format.raw/*882.4*/("""if (extArray[i] == extencion) """),format.raw/*882.34*/("""{"""),format.raw/*882.35*/(""" """),format.raw/*882.36*/("""allowSubmit = true; break; """),format.raw/*882.63*/("""}"""),format.raw/*882.64*/("""
		"""),format.raw/*883.3*/("""}"""),format.raw/*883.4*/("""
		"""),format.raw/*884.3*/("""if (allowSubmit) """),format.raw/*884.20*/("""{"""),format.raw/*884.21*/("""
			"""),format.raw/*885.4*/("""var file = $("#docAdjunto")[0].files[0];
			var fileSize = file.size; //tamaño del archivo
			if(fileSize>100000000)"""),format.raw/*887.26*/("""{"""),format.raw/*887.27*/("""
				"""),format.raw/*888.5*/("""alert("Se permiten únicamente archivos que no superen los 100 MB,"
				+" el que se intenta subir pesa: "+Math.round(file.size/10)/100+" KB");
				form.docAdjunto.value="";
			"""),format.raw/*891.4*/("""}"""),format.raw/*891.5*/("""else"""),format.raw/*891.9*/("""{"""),format.raw/*891.10*/("""
				"""),format.raw/*892.5*/("""alert("Documento subido con éxito");
				$("#txtBtn").text("Cambiar Documento");
			"""),format.raw/*894.4*/("""}"""),format.raw/*894.5*/("""
		"""),format.raw/*895.3*/("""}"""),format.raw/*895.4*/("""else"""),format.raw/*895.8*/("""{"""),format.raw/*895.9*/("""
			"""),format.raw/*896.4*/("""alert("Se permiten únicamente archivos con la extención: " 
			+ (extArray.join("  ")) + "\nPor favor, seleccione otro archivo "
			+ "e intente de nuevo.");
			form.docAdjunto.value="";
		"""),format.raw/*900.3*/("""}"""),format.raw/*900.4*/("""
	"""),format.raw/*901.2*/("""}"""),format.raw/*901.3*/("""
	
	"""),format.raw/*903.2*/("""let extArray2 = new Array(".xls", ".xlsx");
	function subirArchivo(form, file) """),format.raw/*904.36*/("""{"""),format.raw/*904.37*/("""
		"""),format.raw/*905.3*/("""allowSubmit = false;
		if (!file) return;
		while (file.indexOf("\\") != -1)
			file = file.slice(file.indexOf("\\") + 1);
		ext = file.slice(file.lastIndexOf(".")).toLowerCase();
		for (var i = 0; i < extArray2.length; i++) """),format.raw/*910.46*/("""{"""),format.raw/*910.47*/("""
			"""),format.raw/*911.4*/("""if (extArray2[i] == ext) """),format.raw/*911.29*/("""{"""),format.raw/*911.30*/(""" """),format.raw/*911.31*/("""allowSubmit = true; break; """),format.raw/*911.58*/("""}"""),format.raw/*911.59*/("""
		"""),format.raw/*912.3*/("""}"""),format.raw/*912.4*/("""
		"""),format.raw/*913.3*/("""if (allowSubmit) """),format.raw/*913.20*/("""{"""),format.raw/*913.21*/("""
			"""),format.raw/*914.4*/("""$("#cotiOdoValidarPlantilla").submit();
		"""),format.raw/*915.3*/("""}"""),format.raw/*915.4*/("""else"""),format.raw/*915.8*/("""{"""),format.raw/*915.9*/("""
			"""),format.raw/*916.4*/("""alertify.alert("Se permiten &uacute;nicamente archivos con la extenci&oacute;n: " 
			+ (extArray2.join("  ")) + "\nPor favor, seleccione otro archivo "
			+ "e intente de nuevo.");
			form.archivoXLSX.value="";
		"""),format.raw/*920.3*/("""}"""),format.raw/*920.4*/("""
	"""),format.raw/*921.2*/("""}"""),format.raw/*921.3*/("""


	
"""),format.raw/*925.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listClientes:List[tables.Cliente],listProyectos:List[tables.Proyecto],listRegiones:List[tables.Regiones],formCotizaOdo:forms.FormCotizaOdo,listadoServicios:List[List[String]],listCotiOdo:List[List[String]],numDec:Long,listEquipos:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listClientes,listProyectos,listRegiones,formCotizaOdo,listadoServicios,listCotiOdo,numDec,listEquipos)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Cliente],List[tables.Proyecto],List[tables.Regiones],forms.FormCotizaOdo,List[List[String]],List[List[String]],Long,List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listClientes,listProyectos,listRegiones,formCotizaOdo,listadoServicios,listCotiOdo,numDec,listEquipos) => apply(mapDiccionario,mapPermiso,userMnu,listClientes,listProyectos,listRegiones,formCotizaOdo,listadoServicios,listCotiOdo,numDec,listEquipos)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotiOdo/cotiOdoIngreso.scala.html
                  HASH: 30b89d6e4c9201b725b0105f939824a13154b32d
                  MATRIX: 1161->1|1616->363|1643->365|1659->373|1698->375|1726->378|1794->426|1823->430|1867->454|1896->457|1954->489|2022->536|2052->539|2132->591|2161->592|2193->597|3066->1443|3094->1444|3124->1447|3229->1526|3298->1574|3328->1577|3410->1631|3439->1632|3471->1637|4230->2369|4258->2370|4288->2373|4533->2592|4620->2658|4651->2662|5031->3015|5053->3028|5085->3039|5439->3366|5461->3379|5493->3390|5621->3490|5650->3491|5685->3499|5707->3512|5739->3523|5769->3525|5798->3526|5830->3530|5859->3531|5909->3552|5939->3553|6379->3966|6401->3979|6432->3989|6484->4014|6506->4027|6537->4037|6796->4269|6819->4283|6851->4294|6880->4295|7016->4404|7038->4417|7071->4428|7204->4533|7227->4546|7260->4557|7741->5010|7764->5023|7798->5035|8264->5473|8287->5486|8322->5498|8460->5608|8483->5621|8518->5634|9094->6182|9117->6195|9157->6212|9508->6535|9531->6548|9567->6562|11090->8058|11138->8089|11178->8090|11215->8099|11347->8203|11363->8209|11392->8216|11481->8277|11497->8283|11527->8291|11569->8305|11585->8311|11614->8318|11653->8328|11728->8375|11744->8381|11773->8388|11848->8435|11864->8441|11893->8448|11970->8497|11986->8503|12015->8510|12230->8697|12246->8703|12275->8710|12324->8731|12340->8737|12369->8744|12671->9018|12687->9024|12716->9031|12749->9036|12765->9042|12795->9050|12885->9112|12901->9118|12930->9125|13133->9300|13149->9306|13178->9313|13227->9334|13243->9340|13272->9347|13405->9452|13421->9458|13451->9466|13631->9618|13647->9624|13676->9631|13709->9636|13725->9642|13755->9650|13828->9695|13844->9701|13873->9708|13946->9753|13962->9759|13991->9766|14138->9885|14154->9891|14183->9898|14221->9908|14238->9914|14268->9921|14339->9964|14379->9965|14420->9977|14485->10014|14501->10020|14530->10027|14590->10059|14607->10065|14637->10072|14678->10093|14718->10094|14759->10106|14824->10143|14840->10149|14869->10156|14929->10188|14946->10194|14976->10201|15033->10226|15072->10236|15299->10435|15315->10441|15344->10448|15393->10469|15409->10475|15439->10483|15930->10946|15946->10952|15975->10959|16024->10980|16040->10986|16070->10994|16203->11099|16219->11105|16249->11113|16552->11388|16568->11394|16597->11401|16725->11501|16741->11507|16770->11514|16923->11639|16939->11645|16968->11652|16999->11655|17015->11661|17044->11668|17161->11757|17177->11763|17206->11770|17308->11840|17343->11847|18121->12597|18144->12610|18174->12618|18204->12619|20990->15377|21014->15391|21047->15402|21176->15504|21220->15531|21260->15532|21297->15541|21360->15576|21376->15582|21406->15590|21460->15616|21476->15622|21507->15631|21565->15660|21582->15666|21619->15680|21714->15747|21730->15753|21761->15762|21835->15808|21851->15814|21887->15828|21961->15874|21977->15880|22011->15892|22072->15921|22107->15928|23543->17337|23586->17363|23626->17364|23663->17373|23755->17437|23771->17443|23800->17450|23888->17510|23904->17516|23933->17523|23963->17524|23994->17527|24010->17533|24039->17540|24134->17607|24150->17613|24179->17620|24253->17666|24269->17672|24298->17679|24372->17725|24388->17731|24417->17738|24477->17767|24512->17774|25799->19033|25823->19047|25859->19061|25942->19117|25987->19145|26027->19146|26064->19155|26128->19191|26144->19197|26174->19205|26232->19235|26248->19241|26285->19255|26380->19322|26396->19328|26432->19342|26506->19388|26522->19394|26556->19406|26630->19452|26646->19458|26680->19470|26741->19499|26776->19506|27934->20637|27977->20663|28017->20664|28054->20673|28118->20709|28134->20715|28163->20722|28259->20790|28275->20796|28304->20803|28417->20888|28436->20897|28487->20926|28533->20944|28552->20953|28603->20982|28642->20992|28716->21038|28732->21044|28761->21051|28835->21097|28851->21103|28880->21110|28941->21139|28976->21146|30401->22543|30424->22556|30464->22573|30870->22948|30902->22952|31023->23044|31053->23045|31084->23048|31148->23083|31178->23084|31213->23091|31385->23234|31415->23235|31455->23246|31561->23323|31591->23324|31624->23329|31653->23330|31682->23331|31751->23371|31781->23372|31813->23376|31976->23510|32006->23511|32039->23516|32138->23587|32167->23588|32198->23591|32227->23592|32256->23593|32327->23635|32357->23636|32392->23643|32564->23786|32594->23787|32634->23798|32740->23875|32770->23876|32803->23881|32832->23882|32861->23883|32947->23940|32977->23941|33010->23946|33212->24119|33242->24120|33283->24132|33390->24210|33420->24211|33451->24214|33480->24215|33509->24216|33583->24261|33613->24262|33649->24270|33839->24431|33869->24432|33910->24444|34017->24522|34047->24523|34078->24526|34107->24527|34136->24528|34240->24604|34269->24605|34337->24644|34367->24645|34398->24648|34513->24734|34543->24735|34585->24748|34858->24992|34888->24993|34921->24998|34969->25017|34999->25018|35033->25024|35084->25047|35107->25060|35140->25071|35216->25118|35246->25119|35276->25120|35306->25121|35341->25128|35370->25129|35406->25137|35435->25138|35472->25147|35502->25148|35534->25152|35563->25153|35599->25161|35653->25186|35683->25187|35714->25190|35917->25364|35947->25365|35979->25369|36079->25441|36108->25442|36291->25596|36321->25597|36353->25601|36455->25675|36484->25676|36564->25728|36592->25734|36775->25889|36803->25895|36838->25902|36867->25903|36905->25913|36976->25955|37006->25956|37037->25959|37145->26038|37175->26039|37207->26043|37279->26087|37308->26088|37340->26092|37369->26093|37401->26097|37473->26141|37502->26142|37532->26144|37561->26145|37601->26157|37675->26202|37705->26203|37736->26206|38030->26471|38060->26472|38092->26476|38137->26493|38166->26494|38199->26499|38228->26500|38266->26510|38348->26563|38378->26564|38409->26567|38725->26854|38755->26855|38787->26859|38910->26953|38940->26954|38973->26959|39191->27148|39221->27149|39255->27155|39325->27197|39354->27198|39386->27202|39415->27203|39447->27207|39694->27426|39723->27427|39755->27431|39784->27432|39816->27436|39876->27467|39906->27468|39942->27476|40117->27622|40147->27623|40188->27635|40295->27713|40325->27714|40357->27718|40386->27719|40415->27720|40529->27805|40559->27806|40589->27807|40619->27808|40652->27813|40681->27814|40711->27816|40740->27817|40796->27845|40857->27877|40887->27878|40918->27881|41040->27974|41070->27975|41102->27979|41166->28015|41195->28016|41226->28019|41286->28050|41316->28051|41352->28059|41527->28205|41557->28206|41598->28218|41705->28296|41735->28297|41766->28300|41795->28301|41824->28302|42066->28516|42095->28517|42127->28521|42200->28565|42230->28566|42261->28569|42510->28789|42540->28790|42582->28803|42851->29043|42881->29044|42918->29053|43261->29367|43291->29368|43330->29378|43763->29783|43793->29784|43835->29798|44029->29963|44059->29964|44093->29970|44320->30168|44350->30169|44385->30176|45137->30899|45167->30900|45203->30908|45302->30979|45331->30980|45364->30984|45394->30985|45430->30993|45530->31065|45559->31066|45594->31073|45792->31243|45821->31244|45860->31255|45889->31256|45922->31261|46001->31311|46031->31312|46068->31321|46246->31470|46276->31471|46318->31484|46426->31563|46456->31564|46489->31569|46518->31570|46556->31580|46585->31581|46624->31592|46654->31593|46686->31597|46715->31598|46746->31601|46800->31626|46830->31627|46861->31630|47015->31755|47045->31756|47077->31760|47212->31866|47242->31867|47275->31872|47324->31893|47353->31894|47386->31899|47415->31900|47480->31936|47510->31937|47542->31941|47675->32045|47705->32046|47738->32051|47787->32072|47816->32073|47849->32078|47878->32079|47910->32083|47939->32084|47971->32088|48094->32182|48124->32183|48157->32188|48286->32288|48316->32289|48350->32295|48412->32329|48441->32330|48473->32334|48502->32335|48534->32339|48578->32355|48607->32356|48638->32359|48681->32374|48710->32375|48744->32381|48935->32543|48965->32544|48996->32547|49105->32627|49135->32628|49167->32632|49240->32677|49269->32678|49300->32681|49438->32790|49468->32791|49500->32795|49559->32825|49589->32826|49619->32827|49675->32854|49705->32855|49736->32858|49765->32859|49796->32862|49842->32879|49872->32880|49904->32884|50049->33000|50079->33001|50112->33006|50316->33182|50345->33183|50377->33187|50407->33188|50440->33193|50552->33277|50581->33278|50612->33281|50641->33282|50673->33286|50702->33287|50734->33291|50951->33480|50980->33481|51010->33483|51039->33484|51071->33488|51179->33567|51209->33568|51240->33571|51494->33796|51524->33797|51556->33801|51610->33826|51640->33827|51670->33828|51726->33855|51756->33856|51787->33859|51816->33860|51847->33863|51893->33880|51923->33881|51955->33885|52025->33927|52054->33928|52086->33932|52115->33933|52147->33937|52389->34151|52418->34152|52448->34154|52477->34155|52510->34160
                  LINES: 28->1|33->2|34->3|34->3|34->3|36->5|36->5|38->7|38->7|40->9|41->10|41->10|42->11|43->12|43->12|44->13|60->29|60->29|61->30|65->34|65->34|66->35|67->36|67->36|68->37|83->52|83->52|84->53|90->59|90->59|91->60|97->66|97->66|97->66|105->74|105->74|105->74|107->76|107->76|107->76|107->76|107->76|107->76|107->76|107->76|107->76|107->76|107->76|118->87|118->87|118->87|119->88|119->88|119->88|126->95|126->95|126->95|126->95|128->97|128->97|128->97|131->100|131->100|131->100|144->113|144->113|144->113|156->125|156->125|156->125|159->128|159->128|159->128|173->142|173->142|173->142|181->150|181->150|181->150|223->192|223->192|223->192|224->193|226->195|226->195|226->195|227->196|227->196|227->196|228->197|228->197|228->197|229->198|230->199|230->199|230->199|231->200|231->200|231->200|232->201|232->201|232->201|236->205|236->205|236->205|237->206|237->206|237->206|242->211|242->211|242->211|242->211|242->211|242->211|244->213|244->213|244->213|248->217|248->217|248->217|249->218|249->218|249->218|251->220|251->220|251->220|254->223|254->223|254->223|254->223|254->223|254->223|256->225|256->225|256->225|256->225|256->225|256->225|258->227|258->227|258->227|258->227|258->227|258->227|259->228|259->228|260->229|260->229|260->229|260->229|260->229|260->229|260->229|261->230|261->230|262->231|262->231|262->231|262->231|262->231|262->231|262->231|263->232|264->233|268->237|268->237|268->237|269->238|269->238|269->238|279->248|279->248|279->248|280->249|280->249|280->249|282->251|282->251|282->251|289->258|289->258|289->258|292->261|292->261|292->261|295->264|295->264|295->264|295->264|295->264|295->264|297->266|297->266|297->266|300->269|301->270|319->288|319->288|319->288|319->288|388->357|388->357|388->357|394->363|394->363|394->363|395->364|395->364|395->364|395->364|395->364|395->364|395->364|395->364|395->364|395->364|396->365|396->365|396->365|397->366|397->366|397->366|398->367|398->367|398->367|400->369|401->370|439->408|439->408|439->408|440->409|441->410|441->410|441->410|442->411|442->411|442->411|442->411|442->411|442->411|442->411|443->412|443->412|443->412|444->413|444->413|444->413|445->414|445->414|445->414|448->417|449->418|481->450|481->450|481->450|485->454|485->454|485->454|486->455|486->455|486->455|486->455|486->455|486->455|486->455|487->456|487->456|487->456|488->457|488->457|488->457|489->458|489->458|489->458|491->460|492->461|526->495|526->495|526->495|527->496|527->496|527->496|527->496|528->497|528->497|528->497|530->499|530->499|530->499|531->500|531->500|531->500|532->501|533->502|533->502|533->502|534->503|534->503|534->503|536->505|537->506|569->538|569->538|569->538|584->553|588->557|591->560|591->560|592->561|592->561|592->561|593->562|596->565|596->565|597->566|598->567|598->567|599->568|599->568|599->568|601->570|601->570|602->571|605->574|605->574|606->575|607->576|607->576|608->577|608->577|608->577|610->579|610->579|611->580|614->583|614->583|615->584|616->585|616->585|617->586|617->586|617->586|621->590|621->590|622->591|626->595|626->595|627->596|628->597|628->597|629->598|629->598|629->598|631->600|631->600|632->601|635->604|635->604|636->605|637->606|637->606|638->607|638->607|638->607|641->610|641->610|643->612|643->612|644->613|646->615|646->615|647->616|654->623|654->623|655->624|655->624|655->624|656->625|656->625|656->625|656->625|657->626|657->626|657->626|657->626|658->627|658->627|659->628|659->628|660->629|660->629|661->630|661->630|665->634|665->634|665->634|666->635|671->640|671->640|672->641|674->643|674->643|679->648|679->648|680->649|682->651|682->651|683->652|683->652|687->656|687->656|689->658|689->658|694->663|694->663|694->663|695->664|696->665|696->665|697->666|698->667|698->667|698->667|698->667|699->668|700->669|700->669|701->670|701->670|707->676|707->676|707->676|708->677|712->681|712->681|713->682|714->683|714->683|716->685|716->685|721->690|722->691|722->691|723->692|733->702|733->702|734->703|735->704|735->704|736->705|738->707|738->707|739->708|740->709|740->709|741->710|741->710|742->711|748->717|748->717|748->717|748->717|749->718|749->718|749->718|750->719|753->722|753->722|754->723|755->724|755->724|756->725|756->725|756->725|757->726|757->726|757->726|757->726|758->727|758->727|759->728|759->728|773->742|773->742|773->742|774->743|775->744|775->744|776->745|777->746|777->746|778->747|778->747|778->747|779->748|782->751|782->751|783->752|784->753|784->753|785->754|785->754|785->754|790->759|790->759|792->761|792->761|792->761|793->762|797->766|797->766|798->767|805->774|805->774|806->775|814->783|814->783|815->784|821->790|821->790|823->792|826->795|826->795|827->796|832->801|832->801|833->802|848->817|848->817|849->818|850->819|850->819|850->819|850->819|851->820|852->821|852->821|853->822|856->825|856->825|858->827|858->827|859->828|860->829|860->829|861->830|864->833|864->833|865->834|866->835|866->835|867->836|867->836|868->837|868->837|871->840|871->840|872->841|872->841|874->843|874->843|874->843|875->844|878->847|878->847|879->848|880->849|880->849|881->850|882->851|882->851|883->852|883->852|883->852|883->852|884->853|885->854|885->854|886->855|887->856|887->856|888->857|888->857|888->857|888->857|889->858|890->859|890->859|891->860|892->861|892->861|893->862|895->864|895->864|896->865|896->865|897->866|898->867|898->867|899->868|900->869|900->869|904->873|905->874|905->874|906->875|908->877|908->877|909->878|910->879|910->879|911->880|912->881|912->881|913->882|913->882|913->882|913->882|913->882|913->882|914->883|914->883|915->884|915->884|915->884|916->885|918->887|918->887|919->888|922->891|922->891|922->891|922->891|923->892|925->894|925->894|926->895|926->895|926->895|926->895|927->896|931->900|931->900|932->901|932->901|934->903|935->904|935->904|936->905|941->910|941->910|942->911|942->911|942->911|942->911|942->911|942->911|943->912|943->912|944->913|944->913|944->913|945->914|946->915|946->915|946->915|946->915|947->916|951->920|951->920|952->921|952->921|956->925
                  -- GENERATED --
              */
          