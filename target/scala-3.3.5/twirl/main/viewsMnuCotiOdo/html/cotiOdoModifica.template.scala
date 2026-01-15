
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

object cotiOdoModifica extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template10[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Cliente],List[tables.Proyecto],List[tables.Regiones],forms.FormCotizaOdo,List[List[String]],Long,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listClientes: List[tables.Cliente], listProyectos: List[tables.Proyecto], listRegiones: List[tables.Regiones],
formCotizaOdo: forms.FormCotizaOdo, listadoServicios: List[List[String]], numDec: Long, listEquipos: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.1*/("""

"""),_display_(/*6.2*/main("")/*6.10*/ {_display_(Seq[Any](format.raw/*6.12*/("""

"""),_display_(/*8.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*8.50*/("""

	"""),_display_(/*10.3*/modalEquipoDescripcion()),format.raw/*10.27*/("""

	"""),format.raw/*12.2*/("""<!-- MODAL CREA CLIENTES -->
		"""),_display_(/*13.4*/modalClienteNuevo(mapDiccionario, listRegiones)),format.raw/*13.51*/("""
		"""),format.raw/*14.3*/("""<script>
			const clienteGrabaAjax = (id_cliente) =>"""),format.raw/*15.44*/("""{"""),format.raw/*15.45*/("""
				"""),format.raw/*16.5*/("""$('#id_cliente').val(id_cliente);
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
			"""),format.raw/*32.4*/("""}"""),format.raw/*32.5*/("""
		"""),format.raw/*33.3*/("""</script>
	<!-- FIN MODAL CREA CLIENTES -->
	
	<!-- MODAL CREA PROYECTOS -->
		"""),_display_(/*37.4*/modalProyectoNuevo(mapDiccionario, listRegiones)),format.raw/*37.52*/("""
		"""),format.raw/*38.3*/("""<script>
			const proyectoGrabaAjax = (id_proyecto) =>"""),format.raw/*39.46*/("""{"""),format.raw/*39.47*/("""
				"""),format.raw/*40.5*/("""$('#id_proyecto').val(id_proyecto);
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
			"""),format.raw/*55.4*/("""}"""),format.raw/*55.5*/("""
		"""),format.raw/*56.3*/("""</script>
	<!-- FIN MODAL CREA PROYECTOS -->
	

	<form action="/routes2/cotiOdoUpdate/" enctype="multipart/form-data" method="POST" onsubmit="return validarForm();">
		<div id="mostrarmostrar" style="display:block;">
			"""),_display_(/*62.5*/barraTitulo(mapDiccionario, "MODIFICAR COTIZACION ODO", "")),format.raw/*62.64*/("""
			"""),format.raw/*63.4*/("""<div class="row  justify-content-md-center">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<table class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<tr>
								<td width="230px">
									<input type="hidden" id="id_cotiOdo" name="id_cotiOdo" value=""""),_display_(/*69.73*/formCotizaOdo/*69.86*/.id_cotiOdo),format.raw/*69.97*/("""">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Nro.Cotizacion</span>
								  		</div>
								  		<input type="text" class="form-control left"
								  				id="numeroCoti"
												name="numeroCoti"
												value = """"),_display_(/*77.23*/formCotizaOdo/*77.36*/.numeroCoti),format.raw/*77.47*/(""""
												onkeydown="return ingresoInt(window.event)"
												onchange="if(value.trim()=='')"""),format.raw/*79.43*/("""{"""),format.raw/*79.44*/("""value='"""),_display_(/*79.52*/formCotizaOdo/*79.65*/.numeroCoti),format.raw/*79.76*/("""';"""),format.raw/*79.78*/("""}"""),format.raw/*79.79*/("""else"""),format.raw/*79.83*/("""{"""),format.raw/*79.84*/("""validarNumero(value);"""),format.raw/*79.105*/("""}"""),format.raw/*79.106*/("""">
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
								  			onblur="if(!limitaFecha(value,720,10)) value='"""),_display_(/*90.61*/formCotizaOdo/*90.74*/.fechaCoti),format.raw/*90.84*/("""';"
								  			value=""""),_display_(/*91.22*/formCotizaOdo/*91.35*/.fechaCoti),format.raw/*91.45*/(""""
						        			required>
									</div>
								</td>
								<td  width="230px">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">"""),_display_(/*98.65*/mapDiccionario/*98.79*/.get("RUT")),format.raw/*98.90*/(""" """),format.raw/*98.91*/("""Cliente """),_display_(/*98.100*/formCotizaOdo/*98.113*/.id_cliente),format.raw/*98.124*/("""</span>
								  		</div>
								  		<input type="hidden" id="id_cliente" name="id_cliente" value=""""),_display_(/*100.76*/formCotizaOdo/*100.89*/.id_cliente),format.raw/*100.100*/("""">
										<input type="text" class="form-control"
		  										id="rutCliente"
		  										value=""""),_display_(/*103.23*/formCotizaOdo/*103.36*/.rutCliente),format.raw/*103.47*/("""" 
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
													value=""""),_display_(/*116.22*/formCotizaOdo/*116.35*/.nickCliente),format.raw/*116.47*/(""""
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
										<input type="hidden" id="id_proyecto" name="id_proyecto" value=""""),_display_(/*128.76*/formCotizaOdo/*128.89*/.id_proyecto),format.raw/*128.101*/("""">
										<input type="text" class="form-control left"
												id="nombreProyecto"
												value=""""),_display_(/*131.21*/formCotizaOdo/*131.34*/.nickProyecto),format.raw/*131.47*/(""""
												style="background:white"
												onclick='$("#listaProyecto").modal("show")'
												readonly>
									</div>
								</td>
								<td style="text-align:center;" width="70px">
									<span class="btn btn-info btn-sm btn-file" style="font-size: 8px;">
										"""),_display_(if(formCotizaOdo.cotiOdoPDF.equals("0"))/*139.52*/{_display_(Seq[Any](format.raw/*139.53*/("""
											"""),format.raw/*140.12*/("""<div id="txtBtn">Adjuntar</div>
										""")))}else/*141.16*/{_display_(Seq[Any](format.raw/*141.17*/("""
											"""),format.raw/*142.12*/("""<div id="txtBtn">Cambiar</div>
										""")))}),format.raw/*143.12*/("""
				    					"""),format.raw/*144.14*/("""<input type="file" id="docAdjunto" name="docAdjunto" onchange="LimitAttach(this.form, this.form.docAdjunto.value);">
									</span>
								</td>
								<td style="text-align:left;">
									"""),_display_(if(formCotizaOdo.cotiOdoPDF.equals("0"))/*148.51*/{_display_(Seq[Any](format.raw/*148.52*/("""
										"""),format.raw/*149.11*/("""--
									""")))}else/*150.15*/{_display_(Seq[Any](format.raw/*150.16*/("""
										"""),format.raw/*151.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*151.52*/formCotizaOdo/*151.65*/.cotiOdoPDF),format.raw/*151.76*/("""')">
											<kbd style="background-color: #7F8C8D">doc</kbd>
										</a>
									""")))}),format.raw/*154.11*/("""
								"""),format.raw/*155.9*/("""</td>
							</tr>
							<tr>
								<input type="hidden" id="id_bodegaEmpresa" name="id_bodegaEmpresa" value=""""),_display_(/*158.84*/formCotizaOdo/*158.97*/.id_bodegaEmpresa),format.raw/*158.114*/("""">
								<td colspan="20">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Observaciones</span>
								  		</div>
	  									<textarea class="form-control" 
	  										name="observaciones" 
	  										autocomplete="off">"""),_display_(/*166.34*/formCotizaOdo/*166.47*/.observaciones),format.raw/*166.61*/("""</textarea>
									</div>
								</td>
							</tr>
						</thead>
					</table>
					<hr>
					
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
							"""),_display_(/*194.9*/for(lista1 <- listadoServicios) yield /*194.40*/{_display_(Seq[Any](format.raw/*194.41*/("""
								"""),format.raw/*195.9*/("""<TR>
									<td style="text-align:left;">
										<input type="hidden" name="id_servicio[]" value=""""),_display_(/*197.61*/lista1/*197.67*/.get(0)),format.raw/*197.74*/("""">
										<input type="hidden" name="id_moneda[]" value=""""),_display_(/*198.59*/lista1/*198.65*/.get(13)),format.raw/*198.73*/("""">
										"""),_display_(/*199.12*/lista1/*199.18*/.get(1)),format.raw/*199.25*/("""
									"""),format.raw/*200.10*/("""</td>
									<td style= "text-align: left;">"""),_display_(/*201.42*/lista1/*201.48*/.get(2)),format.raw/*201.55*/("""</td>
									<td style= "text-align: left;">"""),_display_(/*202.42*/lista1/*202.48*/.get(3)),format.raw/*202.55*/("""</td>
									<td style= "text-align: center;">"""),_display_(/*203.44*/lista1/*203.50*/.get(4)),format.raw/*203.57*/("""</td>
									<td style="text-align:center;">
										<input type="text" class="cantidad form-control height23px right width80px"
											name="cantidad[]"
											id="cantidad_"""),_display_(/*207.26*/lista1/*207.32*/.get(0)),format.raw/*207.39*/(""""
											value=""""),_display_(/*208.20*/lista1/*208.26*/.get(5)),format.raw/*208.33*/(""""
											onfocus="value=value.replace(/,/g,''); cantAux = value;" 
											onblur = "value = formatStandar(value, '2');"
											onkeydown="return ingresoDouble(window.event)"
											autocomplete="off"
											onchange="if(value=='') value=0; calculaLinea('"""),_display_(/*213.60*/lista1/*213.66*/.get(0)),format.raw/*213.73*/("""', '"""),_display_(/*213.78*/lista1/*213.84*/.get(12)),format.raw/*213.92*/("""');">
									</td>
									<td style="text-align:center;">"""),_display_(/*215.42*/lista1/*215.48*/.get(6)),format.raw/*215.55*/("""</td>
									<td style="text-align:center;">
										<input type="text" class="form-control height23px right width100px"
											name="precio[]"
											id="precio_"""),_display_(/*219.24*/lista1/*219.30*/.get(0)),format.raw/*219.37*/(""""
											value=""""),_display_(/*220.20*/lista1/*220.26*/.get(7)),format.raw/*220.33*/(""""
											onfocus="value=value.replace(/,/g,'');" 
											onblur = "value = formatStandar(value, '"""),_display_(/*222.53*/lista1/*222.59*/.get(12)),format.raw/*222.67*/("""');"
											onkeydown="return ingresoDouble(window.event)"
											autocomplete="off"
											onchange="if(value=='') value=0; calculaLinea('"""),_display_(/*225.60*/lista1/*225.66*/.get(0)),format.raw/*225.73*/("""', '"""),_display_(/*225.78*/lista1/*225.84*/.get(12)),format.raw/*225.92*/("""');">
									</td>
									<td id="total_"""),_display_(/*227.25*/lista1/*227.31*/.get(0)),format.raw/*227.38*/("""" class="total" style= "text-align: right;">"""),_display_(/*227.83*/lista1/*227.89*/.get(8)),format.raw/*227.96*/("""</td>
									<td  style="text-align:center;">
										<input type="hidden" name="aplicaMinimo[]" id="aplicaMinimo_"""),_display_(/*229.72*/lista1/*229.78*/.get(0)),format.raw/*229.85*/("""" value=""""),_display_(/*229.95*/lista1/*229.101*/.get(9)),format.raw/*229.108*/("""">
										"""),_display_(if(lista1.get(9).equals("0"))/*230.41*/{_display_(Seq[Any](format.raw/*230.42*/("""
											"""),format.raw/*231.12*/("""<input type="checkbox" id="checkbox_"""),_display_(/*231.49*/lista1/*231.55*/.get(0)),format.raw/*231.62*/("""" onchange="checkAplicaMinimo('"""),_display_(/*231.94*/lista1/*231.100*/.get(0)),format.raw/*231.107*/("""');">
										""")))}else/*232.16*/{_display_(Seq[Any](format.raw/*232.17*/("""
											"""),format.raw/*233.12*/("""<input type="checkbox" id="checkbox_"""),_display_(/*233.49*/lista1/*233.55*/.get(0)),format.raw/*233.62*/("""" onchange="checkAplicaMinimo('"""),_display_(/*233.94*/lista1/*233.100*/.get(0)),format.raw/*233.107*/("""');" checked>
										""")))}),format.raw/*234.12*/("""
									"""),format.raw/*235.10*/("""</td>
									<td style="text-align:center;">
										<input type="text" class="cantidad form-control height23px right width80px"
											name="cantidadMinimo[]"
											id="cantidadMinimo_"""),_display_(/*239.32*/lista1/*239.38*/.get(0)),format.raw/*239.45*/(""""
											value=""""),_display_(/*240.20*/lista1/*240.26*/.get(10)),format.raw/*240.34*/(""""
											onfocus="value=value.replace(/,/g,''); cantAux = value;" 
											onblur = "value = formatStandar(value, '2');"
											onkeydown="return ingresoDouble(window.event)"
											autocomplete="off"
											onchange="if(value=='') value=0;">
									</td>
									<td style="text-align:center;">
										<input type="text" class="form-control height23px right width100px"
											name="precioAdicional[]"
											id="precioAdicional_"""),_display_(/*250.33*/lista1/*250.39*/.get(0)),format.raw/*250.46*/(""""
											value=""""),_display_(/*251.20*/lista1/*251.26*/.get(11)),format.raw/*251.34*/(""""
											onfocus="value=value.replace(/,/g,'');" 
											onblur = "value = formatStandar(value, '"""),_display_(/*253.53*/lista1/*253.59*/.get(12)),format.raw/*253.67*/("""');"
											onkeydown="return ingresoDouble(window.event)"
											autocomplete="off"
											onchange="if(value=='') value=0;">
									</td>
									<td style= "text-align: left;">
										<input type="text" class="form-control"
										id="equipoAsociado_"""),_display_(/*260.31*/lista1/*260.37*/.get(0)),format.raw/*260.44*/(""""
										value=""""),_display_(/*261.19*/lista1/*261.25*/.get(15)),format.raw/*261.33*/(""" """),format.raw/*261.34*/("""- """),_display_(/*261.37*/lista1/*261.43*/.get(16)),format.raw/*261.51*/(""""
										style="background:white"
										onclick="auxEquipoAsociado = '"""),_display_(/*263.42*/lista1/*263.48*/.get(0)),format.raw/*263.55*/("""'; $('#listaEquipos').modal('show');"
										readonly>
									</td>
									<td style = "display:none" id="idServicio_"""),_display_(/*266.53*/lista1/*266.59*/.get(0)),format.raw/*266.66*/("""">"""),_display_(/*266.69*/lista1/*266.75*/.get(0)),format.raw/*266.82*/("""</td>
									<td style = "display:none" >
										<input type="hidden" id="idEquipo_"""),_display_(/*268.46*/lista1/*268.52*/.get(0)),format.raw/*268.59*/("""" name="id_equipo[]" value=""""),_display_(/*268.88*/lista1/*268.94*/.get(14)),format.raw/*268.102*/("""">
									</td>
								</TR>
				 			""")))}),format.raw/*271.10*/("""
						"""),format.raw/*272.7*/("""</tbody>
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
										value=""""),_display_(/*290.19*/formCotizaOdo/*290.32*/.dctoOdo),format.raw/*290.40*/(""" """),format.raw/*290.41*/("""%"
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
								<TH>"""),_display_(/*359.14*/mapDiccionario/*359.28*/.get("RUT")),format.raw/*359.39*/("""</TH>
								<TH>NICK</TH>
								<TH>NOMBRE</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*365.9*/for(lista1 <- listClientes) yield /*365.36*/{_display_(Seq[Any](format.raw/*365.37*/("""
								"""),format.raw/*366.9*/("""<TR onClick="$('#id_cliente').val("""),_display_(/*366.44*/lista1/*366.50*/.getId()),format.raw/*366.58*/("""); $('#rutCliente').val('"""),_display_(/*366.84*/lista1/*366.90*/.getRut()),format.raw/*366.99*/("""');$('#nombreCliente').val('"""),_display_(/*366.128*/lista1/*366.134*/.getNickName()),format.raw/*366.148*/("""');" data-dismiss="modal">
									<td  style="text-align:left;">"""),_display_(/*367.41*/lista1/*367.47*/.getRut()),format.raw/*367.56*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*368.41*/lista1/*368.47*/.getNickName()),format.raw/*368.61*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*369.41*/lista1/*369.47*/.getNombre()),format.raw/*369.59*/("""</td>
								</TR>
				 			""")))}),format.raw/*371.10*/("""
						"""),format.raw/*372.7*/("""</tbody>
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
							"""),_display_(/*410.9*/for(lista1 <- listEquipos) yield /*410.35*/{_display_(Seq[Any](format.raw/*410.36*/("""
								"""),format.raw/*411.9*/("""<TR onClick="
										$('#idEquipo_'+auxEquipoAsociado).val('"""),_display_(/*412.51*/lista1/*412.57*/.get(0)),format.raw/*412.64*/("""');
										$('#equipoAsociado_'+auxEquipoAsociado).val('"""),_display_(/*413.57*/lista1/*413.63*/.get(1)),format.raw/*413.70*/(""" """),format.raw/*413.71*/("""- """),_display_(/*413.74*/lista1/*413.80*/.get(2)),format.raw/*413.87*/("""');" data-dismiss="modal">
									<td  style="text-align:left;">"""),_display_(/*414.41*/lista1/*414.47*/.get(1)),format.raw/*414.54*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*415.41*/lista1/*415.47*/.get(2)),format.raw/*415.54*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*416.41*/lista1/*416.47*/.get(3)),format.raw/*416.54*/("""</td>
								</TR>
							""")))}),format.raw/*418.9*/("""
						"""),format.raw/*419.7*/("""</tbody>
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
								<TH>"""),_display_(/*451.14*/mapDiccionario/*451.28*/.get("Comuna")),format.raw/*451.42*/("""</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*455.9*/for(lista1 <- listProyectos) yield /*455.37*/{_display_(Seq[Any](format.raw/*455.38*/("""
								"""),format.raw/*456.9*/("""<TR onClick="$('#id_proyecto').val("""),_display_(/*456.45*/lista1/*456.51*/.getId()),format.raw/*456.59*/("""); $('#nombreProyecto').val('"""),_display_(/*456.89*/lista1/*456.95*/.getNickName()),format.raw/*456.109*/("""');" data-dismiss="modal">
									<td  style="text-align:left;">"""),_display_(/*457.41*/lista1/*457.47*/.getNickName()),format.raw/*457.61*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*458.41*/lista1/*458.47*/.getNombre()),format.raw/*458.59*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*459.41*/lista1/*459.47*/.getComuna()),format.raw/*459.59*/("""</td>
								</TR>
				 			""")))}),format.raw/*461.10*/("""
						"""),format.raw/*462.7*/("""</tbody>
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
	
	<form class="formulario" id="formDescargaDocumento" method="post" action="/downloadPDF/">	
		<input type="hidden" id="descargaDocumento" name="nombreArchivo">
	</form>
	
	
""")))}),format.raw/*479.2*/("""



"""),format.raw/*483.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*485.31*/("""{"""),format.raw/*485.32*/("""
		"""),format.raw/*486.3*/("""$('#tablaListaClientes').DataTable("""),format.raw/*486.38*/("""{"""),format.raw/*486.39*/("""
	    	"""),format.raw/*487.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
	    	"order": [[ 1, "asc" ]],
	    	"language": """),format.raw/*490.19*/("""{"""),format.raw/*490.20*/("""
	        	"""),format.raw/*491.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*492.10*/("""}"""),format.raw/*492.11*/("""
	  	"""),format.raw/*493.5*/("""}"""),format.raw/*493.6*/(""" """),format.raw/*493.7*/(""");

		$('#tablaListaEquipos').DataTable("""),format.raw/*495.37*/("""{"""),format.raw/*495.38*/("""
			"""),format.raw/*496.4*/(""""fixedHeader": true,
			"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
			"order": [[ 1, "asc" ]],
			"language": """),format.raw/*499.16*/("""{"""),format.raw/*499.17*/("""
				"""),format.raw/*500.5*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
			"""),format.raw/*501.4*/("""}"""),format.raw/*501.5*/("""
		"""),format.raw/*502.3*/("""}"""),format.raw/*502.4*/(""" """),format.raw/*502.5*/(""");

		$('#tablaListaProyectos').DataTable("""),format.raw/*504.39*/("""{"""),format.raw/*504.40*/("""
	    	"""),format.raw/*505.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
	    	"order": [[ 0, "asc" ]],
	    	"language": """),format.raw/*508.19*/("""{"""),format.raw/*508.20*/("""
	        	"""),format.raw/*509.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*510.10*/("""}"""),format.raw/*510.11*/("""
	  	"""),format.raw/*511.5*/("""}"""),format.raw/*511.6*/(""" """),format.raw/*511.7*/(""");

		sumaTotales();
		
		$('#tablaPrincipal').DataTable("""),format.raw/*515.34*/("""{"""),format.raw/*515.35*/("""
		    	"""),format.raw/*516.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[15, 50, 100, 200, -1], [15, 50, 100, 200, "All"]],
		    	"order": [[ 2, "asc" ]],
		    	"language": """),format.raw/*519.20*/("""{"""),format.raw/*519.21*/("""
		        	"""),format.raw/*520.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*521.11*/("""}"""),format.raw/*521.12*/("""
		"""),format.raw/*522.3*/("""}"""),format.raw/*522.4*/(""" """),format.raw/*522.5*/(""");
		
		  
		document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*526.2*/("""}"""),format.raw/*526.3*/(""");
	
	const validarNumero = (numero) =>"""),format.raw/*528.35*/("""{"""),format.raw/*528.36*/("""
		"""),format.raw/*529.3*/("""var formData = new FormData();
		formData.append('numeroCoti',numero);
        $.ajax("""),format.raw/*531.16*/("""{"""),format.raw/*531.17*/("""
            """),format.raw/*532.13*/("""url: "/routes2/existeNumeroCotiOdoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*539.36*/("""{"""),format.raw/*539.37*/("""
				"""),format.raw/*540.5*/("""if(respuesta!="OK")"""),format.raw/*540.24*/("""{"""),format.raw/*540.25*/("""
					"""),format.raw/*541.6*/("""$("#numeroCoti").val(""""),_display_(/*541.29*/formCotizaOdo/*541.42*/.numeroCoti),format.raw/*541.53*/("""");
					alertify.alert(respuesta, function () """),format.raw/*542.44*/("""{"""),format.raw/*542.45*/(""" """),format.raw/*542.46*/("""}"""),format.raw/*542.47*/(""");
				"""),format.raw/*543.5*/("""}"""),format.raw/*543.6*/("""
	     	"""),format.raw/*544.8*/("""}"""),format.raw/*544.9*/("""
        """),format.raw/*545.9*/("""}"""),format.raw/*545.10*/(""");
	"""),format.raw/*546.2*/("""}"""),format.raw/*546.3*/("""
	
	
	
	"""),format.raw/*550.2*/("""const sumaTotales = () =>"""),format.raw/*550.27*/("""{"""),format.raw/*550.28*/("""
		"""),format.raw/*551.3*/("""$("#tablaPrincipal").dataTable().fnDestroy();
		let tabla = document.getElementById("tablaPrincipal");
		let totCant = 0;
		
		let cant = 0;
		$(".cantidad").each(function() """),format.raw/*556.34*/("""{"""),format.raw/*556.35*/("""
			"""),format.raw/*557.4*/("""let val = $(this).val().replace(/,/g,'');
			cant += parseFloat(val);
		"""),format.raw/*559.3*/("""}"""),format.raw/*559.4*/(""");
		$("#subTotCant").text(formatStandar(cant,'2'));
		$("#granTotCant").text(formatStandar(cant,'2'));
		
		let total = 0;
		$(".total").each(function() """),format.raw/*564.31*/("""{"""),format.raw/*564.32*/("""
			"""),format.raw/*565.4*/("""let val = $(this).text().replace(/,/g,'');
			total += parseFloat(val);
		"""),format.raw/*567.3*/("""}"""),format.raw/*567.4*/("""); 
		$("#subTotPrecio").text(formatStandar(total,'"""),_display_(/*568.49*/numDec),format.raw/*568.55*/("""'));
		
		let dctoOdo = $("#dctoOdo").val().replace(/,/g,'').replace("%","").trim();
		
		$("#granTotPrecio").text(formatStandar((total*(1-dctoOdo/100)),'"""),_display_(/*572.68*/numDec),format.raw/*572.74*/("""'));
		
		
		
		
	"""),format.raw/*577.2*/("""}"""),format.raw/*577.3*/("""
	
	
	
	
	"""),format.raw/*582.2*/("""const checkAplicaMinimo = (id_servicio) =>"""),format.raw/*582.44*/("""{"""),format.raw/*582.45*/("""
		"""),format.raw/*583.3*/("""let aplicaMinimo = $("#aplicaMinimo_"+id_servicio).val();
		if(aplicaMinimo==0)"""),format.raw/*584.22*/("""{"""),format.raw/*584.23*/("""
			"""),format.raw/*585.4*/("""$("#aplicaMinimo_"+id_servicio).val('1');
		"""),format.raw/*586.3*/("""}"""),format.raw/*586.4*/("""else"""),format.raw/*586.8*/("""{"""),format.raw/*586.9*/("""
			"""),format.raw/*587.4*/("""$("#aplicaMinimo_"+id_servicio).val('0');
		"""),format.raw/*588.3*/("""}"""),format.raw/*588.4*/("""
	"""),format.raw/*589.2*/("""}"""),format.raw/*589.3*/("""
	
	
	
	
	
	"""),format.raw/*595.2*/("""const calculaLinea = (id_servicio, numDec) =>"""),format.raw/*595.47*/("""{"""),format.raw/*595.48*/("""
		"""),format.raw/*596.3*/("""let cant = $("#cantidad_"+id_servicio).val().replace(/,/g,'');
		let precio = $("#precio_"+id_servicio).val().replace(/,/g,'');
		let total = parseFloat(cant) * parseFloat(precio);
		$("#total_"+id_servicio).text(formatStandar(total,numDec));
		if(verificando == 1)"""),format.raw/*600.23*/("""{"""),format.raw/*600.24*/("""
			"""),format.raw/*601.4*/("""sumaTotales();
		"""),format.raw/*602.3*/("""}"""),format.raw/*602.4*/("""
		
	"""),format.raw/*604.2*/("""}"""),format.raw/*604.3*/("""
	
	
	
	
	"""),format.raw/*609.2*/("""let verificando = 0;
	const verificaCotizaOdo = () =>"""),format.raw/*610.33*/("""{"""),format.raw/*610.34*/("""
		"""),format.raw/*611.3*/("""sumaTotales();
		
		
		
		let subTotPrecio = $("#subTotPrecio").text().replace(/,/g,'');
		
		let sumaAux = parseFloat(subTotPrecio);
		
		if(sumaAux > 0)"""),format.raw/*619.18*/("""{"""),format.raw/*619.19*/("""
			"""),format.raw/*620.4*/("""let tabla = document.getElementById("tablaPrincipal");
			for(i=1; i<tabla.rows.length-3; i++)"""),format.raw/*621.40*/("""{"""),format.raw/*621.41*/("""
				"""),format.raw/*622.5*/("""let cant = $(".cantidad",tabla.rows[i].cells[4]).val().replace(/,/g,'');
				let precio = tabla.rows[i].cells[7].innerHTML.replace(/,/g,'');
				if((parseFloat(cant)+parseFloat(precio))<=0)"""),format.raw/*624.49*/("""{"""),format.raw/*624.50*/("""
					"""),format.raw/*625.6*/("""tabla.rows[i].style.display = 'none';
				"""),format.raw/*626.5*/("""}"""),format.raw/*626.6*/("""
			"""),format.raw/*627.4*/("""}"""),format.raw/*627.5*/("""
			"""),format.raw/*628.4*/("""verificando = 1;
			document.getElementById('tfoot').style.display = '';
			document.getElementById('modifica').style.visibility = 'visible';
			document.getElementById('aplica').style.visibility = 'visible';
			
			
		"""),format.raw/*634.3*/("""}"""),format.raw/*634.4*/("""else"""),format.raw/*634.8*/("""{"""),format.raw/*634.9*/("""
			"""),format.raw/*635.4*/("""$('#tablaPrincipal').DataTable("""),format.raw/*635.35*/("""{"""),format.raw/*635.36*/("""
		    	"""),format.raw/*636.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[15, 50, 100, 200, -1], [15, 50, 100, 200, "All"]],
		    	"order": [[ 2, "asc" ]],
		    	"language": """),format.raw/*639.20*/("""{"""),format.raw/*639.21*/("""
		        	"""),format.raw/*640.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*641.11*/("""}"""),format.raw/*641.12*/("""
			"""),format.raw/*642.4*/("""}"""),format.raw/*642.5*/(""" """),format.raw/*642.6*/(""");
			alertify.alert('NO PUEDE VERIFICAR UNA COTIZACION SIN CANTIDADES', function () """),format.raw/*643.83*/("""{"""),format.raw/*643.84*/(""" """),format.raw/*643.85*/("""}"""),format.raw/*643.86*/(""");
		"""),format.raw/*644.3*/("""}"""),format.raw/*644.4*/("""
	"""),format.raw/*645.2*/("""}"""),format.raw/*645.3*/("""
	
	
	
	"""),format.raw/*649.2*/("""const modificaCotizacion = () =>"""),format.raw/*649.34*/("""{"""),format.raw/*649.35*/("""
		"""),format.raw/*650.3*/("""let tabla = document.getElementById("tablaPrincipal");
		for(i=1; i<tabla.rows.length-3; i++)"""),format.raw/*651.39*/("""{"""),format.raw/*651.40*/("""
			"""),format.raw/*652.4*/("""tabla.rows[i].style.display = '';
		"""),format.raw/*653.3*/("""}"""),format.raw/*653.4*/("""
		"""),format.raw/*654.3*/("""$('#tablaPrincipal').DataTable("""),format.raw/*654.34*/("""{"""),format.raw/*654.35*/("""
		    	"""),format.raw/*655.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[15, 50, 100, 200, -1], [15, 50, 100, 200, "All"]],
		    	"order": [[ 2, "asc" ]],
		    	"language": """),format.raw/*658.20*/("""{"""),format.raw/*658.21*/("""
		        	"""),format.raw/*659.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*660.11*/("""}"""),format.raw/*660.12*/("""
		"""),format.raw/*661.3*/("""}"""),format.raw/*661.4*/(""" """),format.raw/*661.5*/(""");
		document.getElementById('tfoot').style.display = 'none';
		document.getElementById('modifica').style.visibility = 'hidden';
		document.getElementById('aplica').style.visibility = 'hidden';
		verificando = 0;
	"""),format.raw/*666.2*/("""}"""),format.raw/*666.3*/("""
	

	"""),format.raw/*669.2*/("""const validarForm = () =>"""),format.raw/*669.27*/("""{"""),format.raw/*669.28*/("""
		"""),format.raw/*670.3*/("""let flag = true;
		sumaTotales();
		let granTotPrecio = $("#granTotPrecio").text().replace(/,/g,'');
		if(granTotPrecio <= 0)"""),format.raw/*673.25*/("""{"""),format.raw/*673.26*/("""
			"""),format.raw/*674.4*/("""flag = false;
			alertify.alert('NO PUEDE CONFIRMAR UNA COTIZACION SIN CANTIDADES Y PRECIOS', function () """),format.raw/*675.93*/("""{"""),format.raw/*675.94*/("""
				"""),format.raw/*676.5*/("""return(flag);
     		"""),format.raw/*677.8*/("""}"""),format.raw/*677.9*/(""");
		"""),format.raw/*678.3*/("""}"""),format.raw/*678.4*/("""else if($("#id_cliente").val()=='0')"""),format.raw/*678.40*/("""{"""),format.raw/*678.41*/("""
			"""),format.raw/*679.4*/("""flag = false;
			alertify.alert('NO PUEDE CONFIRMAR UNA COTIZACION SIN ASIGNAR UN CLIENTE', function () """),format.raw/*680.91*/("""{"""),format.raw/*680.92*/("""
				"""),format.raw/*681.5*/("""return(flag);
     		"""),format.raw/*682.8*/("""}"""),format.raw/*682.9*/(""");
		"""),format.raw/*683.3*/("""}"""),format.raw/*683.4*/("""else"""),format.raw/*683.8*/("""{"""),format.raw/*683.9*/("""
			"""),format.raw/*684.4*/("""let tabla = document.getElementById("tablaPrincipal");
			for(i=1; i<tabla.rows.length-3; i++)"""),format.raw/*685.40*/("""{"""),format.raw/*685.41*/("""
				"""),format.raw/*686.5*/("""let cant = $(".cantidad",tabla.rows[i].cells[4]).val().replace(/,/g,'');
				if(parseFloat(cant)<=0)"""),format.raw/*687.28*/("""{"""),format.raw/*687.29*/("""
					"""),format.raw/*688.6*/("""tabla.deleteRow(i);
					i--;
				"""),format.raw/*690.5*/("""}"""),format.raw/*690.6*/("""
			"""),format.raw/*691.4*/("""}"""),format.raw/*691.5*/("""
			"""),format.raw/*692.4*/("""return(true);
		"""),format.raw/*693.3*/("""}"""),format.raw/*693.4*/("""
		"""),format.raw/*694.3*/("""return(flag);
	"""),format.raw/*695.2*/("""}"""),format.raw/*695.3*/("""
	
	
	
	"""),format.raw/*699.2*/("""const descargaDocumento = (nombreDOC) => """),format.raw/*699.43*/("""{"""),format.raw/*699.44*/("""
		  """),format.raw/*700.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*702.2*/("""}"""),format.raw/*702.3*/("""
	
	

	"""),format.raw/*706.2*/("""let extArray = new Array(".gif", ".jpg", ".png", ".jpeg", ".pdf", ".xls", ".doc", ".xlsx", ".docx", ".tar", ".zip", ".rar");
	const LimitAttach = (form, file) => """),format.raw/*707.38*/("""{"""),format.raw/*707.39*/("""
		"""),format.raw/*708.3*/("""let allowSubmit = false;
		if (!file) return;
		while (file.indexOf("\\") != -1)"""),format.raw/*710.35*/("""{"""),format.raw/*710.36*/("""
			"""),format.raw/*711.4*/("""file = file.slice(file.indexOf("\\") + 1);
		"""),format.raw/*712.3*/("""}"""),format.raw/*712.4*/("""
		"""),format.raw/*713.3*/("""let extencion = file.slice(file.lastIndexOf(".")).toLowerCase();
		for (var i = 0; i < extArray.length; i++) """),format.raw/*714.45*/("""{"""),format.raw/*714.46*/("""
			"""),format.raw/*715.4*/("""if (extArray[i] == extencion) """),format.raw/*715.34*/("""{"""),format.raw/*715.35*/(""" """),format.raw/*715.36*/("""allowSubmit = true; break; """),format.raw/*715.63*/("""}"""),format.raw/*715.64*/("""
		"""),format.raw/*716.3*/("""}"""),format.raw/*716.4*/("""
		"""),format.raw/*717.3*/("""if (allowSubmit) """),format.raw/*717.20*/("""{"""),format.raw/*717.21*/("""
			"""),format.raw/*718.4*/("""var file = $("#docAdjunto")[0].files[0];
			var fileSize = file.size; //tamaño del archivo
			if(fileSize>100000000)"""),format.raw/*720.26*/("""{"""),format.raw/*720.27*/("""
				"""),format.raw/*721.5*/("""alert("Se permiten únicamente archivos que no superen los 100 MB,"
				+" el que se intenta subir pesa: "+Math.round(file.size/10)/100+" KB");
				form.docAdjunto.value="";
			"""),format.raw/*724.4*/("""}"""),format.raw/*724.5*/("""else"""),format.raw/*724.9*/("""{"""),format.raw/*724.10*/("""
				"""),format.raw/*725.5*/("""alert("Documento subido con éxito");
				$("#txtBtn").text("Cambiar Documento");
			"""),format.raw/*727.4*/("""}"""),format.raw/*727.5*/("""
		"""),format.raw/*728.3*/("""}"""),format.raw/*728.4*/("""else"""),format.raw/*728.8*/("""{"""),format.raw/*728.9*/("""
			"""),format.raw/*729.4*/("""alert("Se permiten únicamente archivos con la extención: " 
			+ (extArray.join("  ")) + "\nPor favor, seleccione otro archivo "
			+ "e intente de nuevo.");
			form.docAdjunto.value="";
		"""),format.raw/*733.3*/("""}"""),format.raw/*733.4*/("""
	"""),format.raw/*734.2*/("""}"""),format.raw/*734.3*/("""
	


	
"""),format.raw/*739.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listClientes:List[tables.Cliente],listProyectos:List[tables.Proyecto],listRegiones:List[tables.Regiones],formCotizaOdo:forms.FormCotizaOdo,listadoServicios:List[List[String]],numDec:Long,listEquipos:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listClientes,listProyectos,listRegiones,formCotizaOdo,listadoServicios,numDec,listEquipos)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Cliente],List[tables.Proyecto],List[tables.Regiones],forms.FormCotizaOdo,List[List[String]],Long,List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listClientes,listProyectos,listRegiones,formCotizaOdo,listadoServicios,numDec,listEquipos) => apply(mapDiccionario,mapPermiso,userMnu,listClientes,listProyectos,listRegiones,formCotizaOdo,listadoServicios,numDec,listEquipos)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotiOdo/cotiOdoModifica.scala.html
                  HASH: 039cf75ef92de330c880c8654e9ebc4fe45cecb6
                  MATRIX: 1143->1|1565->330|1593->333|1609->341|1648->343|1676->346|1744->394|1774->398|1819->422|1849->425|1907->457|1975->504|2005->507|2085->559|2114->560|2146->565|3019->1411|3047->1412|3077->1415|3183->1495|3252->1543|3282->1546|3364->1600|3393->1601|3425->1606|4184->2338|4212->2339|4242->2342|4489->2563|4569->2622|4600->2626|4980->2979|5002->2992|5034->3003|5388->3330|5410->3343|5442->3354|5570->3454|5599->3455|5634->3463|5656->3476|5688->3487|5718->3489|5747->3490|5779->3494|5808->3495|5858->3516|5888->3517|6329->3931|6351->3944|6382->3954|6434->3979|6456->3992|6487->4002|6746->4234|6769->4248|6801->4259|6830->4260|6867->4269|6890->4282|6923->4293|7053->4395|7076->4408|7110->4419|7243->4524|7266->4537|7299->4548|7781->5002|7804->5015|7838->5027|8304->5465|8327->5478|8362->5490|8500->5600|8523->5613|8558->5626|8914->5954|8954->5955|8995->5967|9062->6014|9102->6015|9143->6027|9217->6069|9260->6083|9524->6319|9564->6320|9604->6331|9641->6348|9681->6349|9721->6360|9790->6401|9813->6414|9846->6425|9968->6515|10005->6524|10147->6638|10170->6651|10210->6668|10563->6993|10586->7006|10622->7020|11463->7834|11511->7865|11551->7866|11588->7875|11720->7979|11736->7985|11765->7992|11854->8053|11870->8059|11900->8067|11942->8081|11958->8087|11987->8094|12026->8104|12101->8151|12117->8157|12146->8164|12221->8211|12237->8217|12266->8224|12343->8273|12359->8279|12388->8286|12603->8473|12619->8479|12648->8486|12697->8507|12713->8513|12742->8520|13045->8795|13061->8801|13090->8808|13123->8813|13139->8819|13169->8827|13259->8889|13275->8895|13304->8902|13507->9077|13523->9083|13552->9090|13601->9111|13617->9117|13646->9124|13780->9230|13796->9236|13826->9244|14006->9396|14022->9402|14051->9409|14084->9414|14100->9420|14130->9428|14203->9473|14219->9479|14248->9486|14321->9531|14337->9537|14366->9544|14513->9663|14529->9669|14558->9676|14596->9686|14613->9692|14643->9699|14714->9742|14754->9743|14795->9755|14860->9792|14876->9798|14905->9805|14965->9837|14982->9843|15012->9850|15053->9871|15093->9872|15134->9884|15199->9921|15215->9927|15244->9934|15304->9966|15321->9972|15351->9979|15408->10004|15447->10014|15674->10213|15690->10219|15719->10226|15768->10247|15784->10253|15814->10261|16306->10725|16322->10731|16351->10738|16400->10759|16416->10765|16446->10773|16580->10879|16596->10885|16626->10893|16929->11168|16945->11174|16974->11181|17022->11201|17038->11207|17068->11215|17098->11216|17129->11219|17145->11225|17175->11233|17281->11311|17297->11317|17326->11324|17479->11449|17495->11455|17524->11462|17555->11465|17571->11471|17600->11478|17717->11567|17733->11573|17762->11580|17819->11609|17835->11615|17866->11623|17939->11664|17974->11671|18754->12423|18777->12436|18807->12444|18837->12445|21639->15219|21663->15233|21696->15244|21825->15346|21869->15373|21909->15374|21946->15383|22009->15418|22025->15424|22055->15432|22109->15458|22125->15464|22156->15473|22214->15502|22231->15508|22268->15522|22363->15589|22379->15595|22410->15604|22484->15650|22500->15656|22536->15670|22610->15716|22626->15722|22660->15734|22721->15763|22756->15770|24192->17179|24235->17205|24275->17206|24312->17215|24404->17279|24420->17285|24449->17292|24537->17352|24553->17358|24582->17365|24612->17366|24643->17369|24659->17375|24688->17382|24783->17449|24799->17455|24828->17462|24902->17508|24918->17514|24947->17521|25021->17567|25037->17573|25066->17580|25125->17608|25160->17615|26448->18875|26472->18889|26508->18903|26591->18959|26636->18987|26676->18988|26713->18997|26777->19033|26793->19039|26823->19047|26881->19077|26897->19083|26934->19097|27029->19164|27045->19170|27081->19184|27155->19230|27171->19236|27205->19248|27279->19294|27295->19300|27329->19312|27390->19341|27425->19348|27925->19817|27957->19821|28049->19884|28079->19885|28110->19888|28174->19923|28204->19924|28239->19931|28411->20074|28441->20075|28481->20086|28587->20163|28617->20164|28650->20169|28679->20170|28708->20171|28777->20211|28807->20212|28839->20216|29002->20350|29032->20351|29065->20356|29164->20427|29193->20428|29224->20431|29253->20432|29282->20433|29353->20475|29383->20476|29418->20483|29590->20626|29620->20627|29660->20638|29766->20715|29796->20716|29829->20721|29858->20722|29887->20723|29973->20780|30003->20781|30039->20789|30214->20935|30244->20936|30285->20948|30392->21026|30422->21027|30453->21030|30482->21031|30511->21032|30618->21111|30647->21112|30715->21151|30745->21152|30776->21155|30891->21241|30921->21242|30963->21255|31236->21499|31266->21500|31299->21505|31347->21524|31377->21525|31411->21531|31462->21554|31485->21567|31518->21578|31594->21625|31624->21626|31654->21627|31684->21628|31719->21635|31748->21636|31784->21644|31813->21645|31850->21654|31880->21655|31912->21659|31941->21660|31977->21668|32031->21693|32061->21694|32092->21697|32295->21871|32325->21872|32357->21876|32457->21948|32486->21949|32669->22103|32699->22104|32731->22108|32833->22182|32862->22183|32942->22235|32970->22241|33153->22396|33181->22402|33227->22420|33256->22421|33294->22431|33365->22473|33395->22474|33426->22477|33534->22556|33564->22557|33596->22561|33668->22605|33697->22606|33729->22610|33758->22611|33790->22615|33862->22659|33891->22660|33921->22662|33950->22663|33990->22675|34064->22720|34094->22721|34125->22724|34419->22989|34449->22990|34481->22994|34526->23011|34555->23012|34588->23017|34617->23018|34655->23028|34737->23081|34767->23082|34798->23085|34981->23239|35011->23240|35043->23244|35166->23338|35196->23339|35229->23344|35447->23533|35477->23534|35511->23540|35581->23582|35610->23583|35642->23587|35671->23588|35703->23592|35950->23811|35979->23812|36011->23816|36040->23817|36072->23821|36132->23852|36162->23853|36198->23861|36373->24007|36403->24008|36444->24020|36551->24098|36581->24099|36613->24103|36642->24104|36671->24105|36785->24190|36815->24191|36845->24192|36875->24193|36908->24198|36937->24199|36967->24201|36996->24202|37032->24210|37093->24242|37123->24243|37154->24246|37276->24339|37306->24340|37338->24344|37402->24380|37431->24381|37462->24384|37522->24415|37552->24416|37588->24424|37763->24570|37793->24571|37834->24583|37941->24661|37971->24662|38002->24665|38031->24666|38060->24667|38302->24881|38331->24882|38364->24887|38418->24912|38448->24913|38479->24916|38633->25041|38663->25042|38695->25046|38830->25152|38860->25153|38893->25158|38942->25179|38971->25180|39004->25185|39033->25186|39098->25222|39128->25223|39160->25227|39293->25331|39323->25332|39356->25337|39405->25358|39434->25359|39467->25364|39496->25365|39528->25369|39557->25370|39589->25374|39712->25468|39742->25469|39775->25474|39904->25574|39934->25575|39968->25581|40030->25615|40059->25616|40091->25620|40120->25621|40152->25625|40196->25641|40225->25642|40256->25645|40299->25660|40328->25661|40364->25669|40434->25710|40464->25711|40497->25716|40629->25820|40658->25821|40693->25828|40884->25990|40914->25991|40945->25994|41054->26074|41084->26075|41116->26079|41189->26124|41218->26125|41249->26128|41387->26237|41417->26238|41449->26242|41508->26272|41538->26273|41568->26274|41624->26301|41654->26302|41685->26305|41714->26306|41745->26309|41791->26326|41821->26327|41853->26331|41998->26447|42028->26448|42061->26453|42265->26629|42294->26630|42326->26634|42356->26635|42389->26640|42501->26724|42530->26725|42561->26728|42590->26729|42622->26733|42651->26734|42683->26738|42900->26927|42929->26928|42959->26930|42988->26931|43023->26938
                  LINES: 28->1|35->4|37->6|37->6|37->6|39->8|39->8|41->10|41->10|43->12|44->13|44->13|45->14|46->15|46->15|47->16|63->32|63->32|64->33|68->37|68->37|69->38|70->39|70->39|71->40|86->55|86->55|87->56|93->62|93->62|94->63|100->69|100->69|100->69|108->77|108->77|108->77|110->79|110->79|110->79|110->79|110->79|110->79|110->79|110->79|110->79|110->79|110->79|121->90|121->90|121->90|122->91|122->91|122->91|129->98|129->98|129->98|129->98|129->98|129->98|129->98|131->100|131->100|131->100|134->103|134->103|134->103|147->116|147->116|147->116|159->128|159->128|159->128|162->131|162->131|162->131|170->139|170->139|171->140|172->141|172->141|173->142|174->143|175->144|179->148|179->148|180->149|181->150|181->150|182->151|182->151|182->151|182->151|185->154|186->155|189->158|189->158|189->158|197->166|197->166|197->166|225->194|225->194|225->194|226->195|228->197|228->197|228->197|229->198|229->198|229->198|230->199|230->199|230->199|231->200|232->201|232->201|232->201|233->202|233->202|233->202|234->203|234->203|234->203|238->207|238->207|238->207|239->208|239->208|239->208|244->213|244->213|244->213|244->213|244->213|244->213|246->215|246->215|246->215|250->219|250->219|250->219|251->220|251->220|251->220|253->222|253->222|253->222|256->225|256->225|256->225|256->225|256->225|256->225|258->227|258->227|258->227|258->227|258->227|258->227|260->229|260->229|260->229|260->229|260->229|260->229|261->230|261->230|262->231|262->231|262->231|262->231|262->231|262->231|262->231|263->232|263->232|264->233|264->233|264->233|264->233|264->233|264->233|264->233|265->234|266->235|270->239|270->239|270->239|271->240|271->240|271->240|281->250|281->250|281->250|282->251|282->251|282->251|284->253|284->253|284->253|291->260|291->260|291->260|292->261|292->261|292->261|292->261|292->261|292->261|292->261|294->263|294->263|294->263|297->266|297->266|297->266|297->266|297->266|297->266|299->268|299->268|299->268|299->268|299->268|299->268|302->271|303->272|321->290|321->290|321->290|321->290|390->359|390->359|390->359|396->365|396->365|396->365|397->366|397->366|397->366|397->366|397->366|397->366|397->366|397->366|397->366|397->366|398->367|398->367|398->367|399->368|399->368|399->368|400->369|400->369|400->369|402->371|403->372|441->410|441->410|441->410|442->411|443->412|443->412|443->412|444->413|444->413|444->413|444->413|444->413|444->413|444->413|445->414|445->414|445->414|446->415|446->415|446->415|447->416|447->416|447->416|449->418|450->419|482->451|482->451|482->451|486->455|486->455|486->455|487->456|487->456|487->456|487->456|487->456|487->456|487->456|488->457|488->457|488->457|489->458|489->458|489->458|490->459|490->459|490->459|492->461|493->462|510->479|514->483|516->485|516->485|517->486|517->486|517->486|518->487|521->490|521->490|522->491|523->492|523->492|524->493|524->493|524->493|526->495|526->495|527->496|530->499|530->499|531->500|532->501|532->501|533->502|533->502|533->502|535->504|535->504|536->505|539->508|539->508|540->509|541->510|541->510|542->511|542->511|542->511|546->515|546->515|547->516|550->519|550->519|551->520|552->521|552->521|553->522|553->522|553->522|557->526|557->526|559->528|559->528|560->529|562->531|562->531|563->532|570->539|570->539|571->540|571->540|571->540|572->541|572->541|572->541|572->541|573->542|573->542|573->542|573->542|574->543|574->543|575->544|575->544|576->545|576->545|577->546|577->546|581->550|581->550|581->550|582->551|587->556|587->556|588->557|590->559|590->559|595->564|595->564|596->565|598->567|598->567|599->568|599->568|603->572|603->572|608->577|608->577|613->582|613->582|613->582|614->583|615->584|615->584|616->585|617->586|617->586|617->586|617->586|618->587|619->588|619->588|620->589|620->589|626->595|626->595|626->595|627->596|631->600|631->600|632->601|633->602|633->602|635->604|635->604|640->609|641->610|641->610|642->611|650->619|650->619|651->620|652->621|652->621|653->622|655->624|655->624|656->625|657->626|657->626|658->627|658->627|659->628|665->634|665->634|665->634|665->634|666->635|666->635|666->635|667->636|670->639|670->639|671->640|672->641|672->641|673->642|673->642|673->642|674->643|674->643|674->643|674->643|675->644|675->644|676->645|676->645|680->649|680->649|680->649|681->650|682->651|682->651|683->652|684->653|684->653|685->654|685->654|685->654|686->655|689->658|689->658|690->659|691->660|691->660|692->661|692->661|692->661|697->666|697->666|700->669|700->669|700->669|701->670|704->673|704->673|705->674|706->675|706->675|707->676|708->677|708->677|709->678|709->678|709->678|709->678|710->679|711->680|711->680|712->681|713->682|713->682|714->683|714->683|714->683|714->683|715->684|716->685|716->685|717->686|718->687|718->687|719->688|721->690|721->690|722->691|722->691|723->692|724->693|724->693|725->694|726->695|726->695|730->699|730->699|730->699|731->700|733->702|733->702|737->706|738->707|738->707|739->708|741->710|741->710|742->711|743->712|743->712|744->713|745->714|745->714|746->715|746->715|746->715|746->715|746->715|746->715|747->716|747->716|748->717|748->717|748->717|749->718|751->720|751->720|752->721|755->724|755->724|755->724|755->724|756->725|758->727|758->727|759->728|759->728|759->728|759->728|760->729|764->733|764->733|765->734|765->734|770->739
                  -- GENERATED --
              */
          