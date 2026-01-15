
package viewsMnuCotizarSelect.html

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

object cotizaIngreso3 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template16[Map[String,String],Map[String,String],utilities.UserMnu,forms.FormCotiza,List[tables.Cliente],List[tables.Proyecto],List[List[String]],String,List[tables.Regiones],List[tables.UnidadTiempo],tables.Sucursal,tables.Comercial,List[tables.Sucursal],List[tables.Comercial],String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
formCotiza: forms.FormCotiza, listClientes: List[tables.Cliente], listProyectos: List[tables.Proyecto], detalle: List[List[String]], 
numDecParaTot: String,listRegiones: List[tables.Regiones],
listUnTiempo: List[tables.UnidadTiempo], 
sucursal: tables.Sucursal, comercial: tables.Comercial, listSucursal: List[tables.Sucursal], listComercial: List[tables.Comercial],
importDesdeExcel: String, json: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*7.1*/("""
"""),_display_(/*8.2*/main("")/*8.10*/ {_display_(Seq[Any](format.raw/*8.12*/("""

"""),_display_(/*10.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*10.50*/("""
"""),format.raw/*11.1*/("""<div id="bloquear2" class="blocker" style="display:none;"><br><br><br><br><br><br><h1>Se esta actualizando... </h1></div>
	"""),_display_(/*12.3*/modalEquipoDescripcion()),format.raw/*12.27*/("""

	"""),format.raw/*14.2*/("""<!-- MODAL CREA CLIENTES -->
		"""),_display_(/*15.4*/modalClienteNuevo(mapDiccionario, listRegiones)),format.raw/*15.51*/("""
		"""),format.raw/*16.3*/("""<script>
			const clienteGrabaAjax = (id_cliente) =>"""),format.raw/*17.44*/("""{"""),format.raw/*17.45*/("""
				"""),format.raw/*18.5*/("""$('#id_cliente').val(id_cliente);
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
			"""),format.raw/*34.4*/("""}"""),format.raw/*34.5*/("""
		"""),format.raw/*35.3*/("""</script>
	<!-- FIN MODAL CREA CLIENTES -->
	
	<!-- MODAL CREA PROYECTOS -->
		"""),_display_(/*39.4*/modalProyectoNuevo(mapDiccionario, listRegiones)),format.raw/*39.52*/("""
		"""),format.raw/*40.3*/("""<script>
			const proyectoGrabaAjax = (id_proyecto) =>"""),format.raw/*41.46*/("""{"""),format.raw/*41.47*/("""
				"""),format.raw/*42.5*/("""$('#id_proyecto').val(id_proyecto);
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
			"""),format.raw/*57.4*/("""}"""),format.raw/*57.5*/("""
		"""),format.raw/*58.3*/("""</script>
	<!-- FIN MODAL CREA PROYECTOS -->
	

	<form action="/cotizarNuevo/" enctype="multipart/form-data" method="POST" onsubmit="return validarForm();">
		<div id="mostrarmostrar" style="display:none;">
			"""),_display_(/*64.5*/barraTitulo(mapDiccionario, "INGRESO DE NUEVA COTIZACION", "")),format.raw/*64.67*/("""
			"""),format.raw/*65.4*/("""<div class="row  justify-content-md-center">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<table class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<tr>
								<td width="230px">
									<input type="hidden" id="id_cotizacion" name="id_cotizacion" value=""""),_display_(/*71.79*/formCotiza/*71.89*/.id_cotizacion),format.raw/*71.103*/("""">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Nro.Cotizacion</span>
								  		</div>
								  		<input type="text" class="form-control left"
								  				id="numeroCoti"
												name="numeroCoti"
												value = """"),_display_(/*79.23*/formCotiza/*79.33*/.numeroCoti),format.raw/*79.44*/(""""
												onkeydown="return ingresoInt(window.event)"
												onchange="if(value.trim()=='')"""),format.raw/*81.43*/("""{"""),format.raw/*81.44*/("""value='"""),_display_(/*81.52*/formCotiza/*81.62*/.numeroCoti),format.raw/*81.73*/("""';"""),format.raw/*81.75*/("""}"""),format.raw/*81.76*/("""else"""),format.raw/*81.80*/("""{"""),format.raw/*81.81*/("""validarNumero(value);"""),format.raw/*81.102*/("""}"""),format.raw/*81.103*/("""">
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
								  			onblur="if(!limitaFecha(value,720,10)) value='"""),_display_(/*92.61*/formCotiza/*92.71*/.fechaCoti),format.raw/*92.81*/("""';"
								  			value=""""),_display_(/*93.22*/formCotiza/*93.32*/.fechaCoti),format.raw/*93.42*/(""""
						        			required>
									</div>
								</td>
								<td width="230px">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">"""),_display_(/*100.65*/mapDiccionario/*100.79*/.get("RUT")),format.raw/*100.90*/(""" """),format.raw/*100.91*/("""Cliente</span>
								  		</div>
								  		<input type="hidden" id="id_cliente" name="id_cliente" value=""""),_display_(/*102.76*/formCotiza/*102.86*/.id_cliente),format.raw/*102.97*/("""">
										"""),_display_(if(mapPermiso.get("parametro.cotizaPorTasa").equals("1"))/*103.69*/ {_display_(Seq[Any](format.raw/*103.71*/("""
											"""),_display_(if(formCotiza.id_cliente>0)/*104.40*/{_display_(Seq[Any](format.raw/*104.41*/("""
												"""),format.raw/*105.13*/("""<input type="text" class="form-control"
			  										id="rutCliente"
			  										value=""""),_display_(/*107.24*/formCotiza/*107.34*/.rutCliente),format.raw/*107.45*/(""""
			  										readonly>
											""")))}else/*109.17*/{_display_(Seq[Any](format.raw/*109.18*/("""
												"""),format.raw/*110.13*/("""<input type="text" class="form-control"
			  										id="rutCliente"
			  										value=""""),_display_(/*112.24*/formCotiza/*112.34*/.rutCliente),format.raw/*112.45*/("""" 
			  										style="background:white"
			  										onclick="$('#listaCliente').modal('show');"
			  										readonly>
											""")))}),format.raw/*116.13*/("""
										""")))}else/*117.16*/{_display_(Seq[Any](format.raw/*117.17*/("""
											"""),format.raw/*118.12*/("""<input type="text" class="form-control"
		  										id="rutCliente"
		  										value=""""),_display_(/*120.23*/formCotiza/*120.33*/.rutCliente),format.raw/*120.44*/("""" 
		  										style="background:white"
		  										onclick="$('#listaCliente').modal('show');"
		  										readonly>
										""")))}),format.raw/*124.12*/("""
									"""),format.raw/*125.10*/("""</div>
								</td>
								<td>
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Cliente</span>
								  		</div>
											"""),_display_(if(mapPermiso.get("parametro.cotizaPorTasa").equals("1"))/*132.70*/ {_display_(Seq[Any](format.raw/*132.72*/("""
												"""),_display_(if(formCotiza.id_cliente>0)/*133.41*/{_display_(Seq[Any](format.raw/*133.42*/("""
													"""),format.raw/*134.14*/("""<input type="text" class="form-control left"
													id="nombreCliente"
													value=""""),_display_(/*136.22*/formCotiza/*136.32*/.nickCliente),format.raw/*136.44*/(""""
													required
													readonly>
												""")))}else/*139.18*/{_display_(Seq[Any](format.raw/*139.19*/("""
													"""),format.raw/*140.14*/("""<input type="text" class="form-control left"
													id="nombreCliente"
													value=""""),_display_(/*142.22*/formCotiza/*142.32*/.nickCliente),format.raw/*142.44*/(""""
													style="background:white"
													onclick='$("#listaCliente").modal("show")'
													required
													readonly>
												""")))}),format.raw/*147.14*/("""
											""")))}else/*148.17*/{_display_(Seq[Any](format.raw/*148.18*/("""
												"""),format.raw/*149.13*/("""<input type="text" class="form-control left"
													id="nombreCliente"
													value=""""),_display_(/*151.22*/formCotiza/*151.32*/.nickCliente),format.raw/*151.44*/(""""
													style="background:white"
													onclick='$("#listaCliente").modal("show")'
													required
													readonly>
											""")))}),format.raw/*156.13*/("""
									"""),format.raw/*157.10*/("""</div>
								</td>
								<td>
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Proyecto</span>
								  		</div>
										<input type="hidden" id="id_proyecto" name="id_proyecto" value=""""),_display_(/*164.76*/formCotiza/*164.86*/.id_proyecto),format.raw/*164.98*/("""">
										"""),_display_(if(mapPermiso.get("parametro.cotizaPorTasa").equals("1"))/*165.69*/ {_display_(Seq[Any](format.raw/*165.71*/("""
											"""),format.raw/*166.12*/("""<input type="text" class="form-control left"
												id="nombreProyecto"
												value=""""),_display_(/*168.21*/formCotiza/*168.31*/.nickProyecto),format.raw/*168.44*/(""""
												readonly>
										""")))}else/*170.16*/{_display_(Seq[Any](format.raw/*170.17*/("""
											"""),format.raw/*171.12*/("""<input type="text" class="form-control left"
												id="nombreProyecto"
												value=""""),_display_(/*173.21*/formCotiza/*173.31*/.nickProyecto),format.raw/*173.44*/(""""
												style="background:white"
												onclick='$("#listaProyecto").modal("show")'
												readonly>
										""")))}),format.raw/*177.12*/("""
									"""),format.raw/*178.10*/("""</div>
								</td>
								<td>
									<span class="btn btn-info btn-sm btn-file" style="font-size: 8px;">
										<div id="txtBtn">Adjuntar</div>
				    					<input type="file" id="docAdjunto" name="docAdjunto" onchange="LimitAttach(this.form, this.form.docAdjunto.value);">
									</span>
								</td>
							</tr>
							<tr>
							
							
								
								<td align="center"  colspan="2">
									<input type="hidden" id="id_sucursal" name="id_sucursal" value=""""),_display_(/*192.75*/sucursal/*192.83*/.getId()),format.raw/*192.91*/("""">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Sucursal</span>
								  		</div>
										"""),_display_(if(mapPermiso.get("cambiarSucursal")!=null)/*197.55*/{_display_(Seq[Any](format.raw/*197.56*/("""
											"""),_display_(if(mapPermiso.get("cambiarComercial")!=null)/*198.57*/{_display_(Seq[Any](format.raw/*198.58*/("""
												"""),format.raw/*199.13*/("""<select id="selSucursal" class="custom-select"  style="width: 80px;" onchange = "$('#id_sucursal').val(value); actualizaComerciales(value); actualizaPrecios(value);">
													<option value=""""),_display_(/*200.30*/sucursal/*200.38*/.getId()),format.raw/*200.46*/("""">"""),_display_(/*200.49*/sucursal/*200.57*/.getNombre()),format.raw/*200.69*/("""</option>
													"""),_display_(/*201.15*/for(lista <- listSucursal) yield /*201.41*/{_display_(Seq[Any](format.raw/*201.42*/("""
														"""),format.raw/*202.15*/("""<option value=""""),_display_(/*202.31*/lista/*202.36*/.getId()),format.raw/*202.44*/("""">"""),_display_(/*202.47*/lista/*202.52*/.getNombre()),format.raw/*202.64*/("""</option>
													""")))}),format.raw/*203.15*/("""
												"""),format.raw/*204.13*/("""</select>
											""")))}else/*205.17*/{_display_(Seq[Any](format.raw/*205.18*/("""
												"""),format.raw/*206.13*/("""<select id="selSucursal" class="custom-select"  style="width: 80px;" onchange = "$('#id_sucursal').val(value); actualizaPrecios(value);">
													<option value=""""),_display_(/*207.30*/sucursal/*207.38*/.getId()),format.raw/*207.46*/("""">"""),_display_(/*207.49*/sucursal/*207.57*/.getNombre()),format.raw/*207.69*/("""</option>
													"""),_display_(/*208.15*/for(lista <- listSucursal) yield /*208.41*/{_display_(Seq[Any](format.raw/*208.42*/("""
														"""),format.raw/*209.15*/("""<option value=""""),_display_(/*209.31*/lista/*209.36*/.getId()),format.raw/*209.44*/("""">"""),_display_(/*209.47*/lista/*209.52*/.getNombre()),format.raw/*209.64*/("""</option>
													""")))}),format.raw/*210.15*/("""
												"""),format.raw/*211.13*/("""</select>
											""")))}),format.raw/*212.13*/("""
										""")))}else/*213.16*/{_display_(Seq[Any](format.raw/*213.17*/("""
											"""),format.raw/*214.12*/("""<input  id="selSucursal" type="text" class="form-control left"
												value = """"),_display_(/*215.23*/sucursal/*215.31*/.getNombre()),format.raw/*215.43*/(""""
												readonly>
										""")))}),format.raw/*217.12*/("""
									"""),format.raw/*218.10*/("""</div>
								</td>
								
								
								
								<td colspan="20" rowspan="3">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Observaciones</span>
								  		</div>
	  									<textarea class="form-control" rows="3"
	  										name="observaciones" 
	  										autocomplete="off">"""),_display_(/*230.34*/formCotiza/*230.44*/.observaciones),format.raw/*230.58*/("""</textarea>
									</div>
								</td>
							</tr>
							<tr>
							
							
								<td align="center"  colspan="2">
									<div id="actualComerciales">
										<input type="hidden" id="id_comercial" name="id_comercial" value=""""),_display_(/*239.78*/comercial/*239.87*/.getId()),format.raw/*239.95*/("""">
										<div class="input-group">
									  		<div class="input-group-prepend">
									    		<span class="input-group-text" id="basic-addon1">Comercial</span>
									  		</div>
											"""),_display_(if(mapPermiso.get("cambiarComercial")!=null)/*244.57*/{_display_(Seq[Any](format.raw/*244.58*/("""
												"""),format.raw/*245.13*/("""<select  id="selComercial" class="custom-select"  style="width: 80px;" onchange="$('#id_comercial').val(value)">
													<option value=""""),_display_(/*246.30*/comercial/*246.39*/.getId()),format.raw/*246.47*/("""">"""),_display_(/*246.50*/comercial/*246.59*/.getNameUsuario()),format.raw/*246.76*/("""</option>
													"""),_display_(/*247.15*/for(lista <- listComercial) yield /*247.42*/{_display_(Seq[Any](format.raw/*247.43*/("""
														"""),format.raw/*248.15*/("""<option value=""""),_display_(/*248.31*/lista/*248.36*/.getId()),format.raw/*248.44*/("""">"""),_display_(/*248.47*/lista/*248.52*/.getNameUsuario()),format.raw/*248.69*/("""</option>
													""")))}),format.raw/*249.15*/("""
												"""),format.raw/*250.13*/("""</select>
											""")))}else/*251.17*/{_display_(Seq[Any](format.raw/*251.18*/("""
												"""),format.raw/*252.13*/("""<input  id="selComercial" type="text" class="form-control left"
													value = """"),_display_(/*253.24*/comercial/*253.33*/.getNameUsuario()),format.raw/*253.50*/(""""
													readonly>
											""")))}),format.raw/*255.13*/("""
										"""),format.raw/*256.11*/("""</div>
									</div>
								</td>
									
									
									
							</tr>
							<tr>
								<input type="hidden" id="id_bodegaEmpresa" name="id_bodegaEmpresa" value=""""),_display_(/*264.84*/formCotiza/*264.94*/.id_bodegaEmpresa),format.raw/*264.111*/("""">
								"""),_display_(if(mapPermiso.get("parametro.cotizaPorTasa").equals("1"))/*265.67*/ {_display_(Seq[Any](format.raw/*265.69*/("""
									"""),format.raw/*266.10*/("""<td align="center"  colspan="2">
										<div class="input-group">
									  		<div class="input-group-prepend">
									    		<span class="input-group-text" id="basic-addon1">"""),_display_(/*269.66*/mapDiccionario/*269.80*/.get("BODEGA")),format.raw/*269.94*/("""/PROYECTO</span>
									  		</div>
									  		<input type="text" class="form-control left"
													value = """"),_display_(/*272.24*/formCotiza/*272.34*/.nombreBodega),format.raw/*272.47*/(""""
													readonly>
										</div>
									</td>
								""")))} else {null} ),format.raw/*276.10*/("""
							"""),format.raw/*277.8*/("""</tr>
						</thead>
					</table>
					<hr>
					
					<div class="noprint" align="center">
						<div class="row justify-content-md-center" >
							<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
								<input id="btnCopiaCoti" type="button"  value="Copiar desde otra cotización" class="btn btn-warning btn-sm rounded-pill btn-block" 
									onclick="listadoCotizaciones()">&nbsp;&nbsp;&nbsp;
							</div>
							<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
								<input id="btnCargaExcel" type="button"  value="Copiar desde un Excel" class="btn btn-info btn-sm rounded-pill btn-block" 
									onclick="$('#modalCargaMasiva').modal('show');">&nbsp;&nbsp;&nbsp;
							</div>
						</div>
					</div>
					
					
					<div id='modalTasaGlobal' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
						<div class='modal-dialog modal-dialog-scrollable' role='document' style="width:300px">
							<div class='modal-content'>
								<div class='modal-header'>
								        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
								          <span aria-hidden='true'>&times;</span>
								        </button>
								</div>
								<div class='modal-body'>
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">APLICAR TASA GLOBAL</span>
								  		</div>
								  		<input type="text" class="form-control center"
											id="tasaGlobal"
											value="0.00 %"
											onfocus="value = value.replace(/,/g,'').replace(/%/g,'').replace(/ /g,'')"
											onkeydown="return ingresoDouble(window.event)"
											autocomplete="off"
											onchange="value = formatPorcentaje(value); if(value=='') """),format.raw/*315.69*/("""{"""),format.raw/*315.70*/("""value='0.00 %';"""),format.raw/*315.85*/("""}"""),format.raw/*315.86*/("""">
									</div>
									<br>
									<div class='row'>
										<div class='col-sm-12' style='text-align:center'>
											<input type="button" class="btn btn-sm btn-primary" value='Aplicar' onclick='$("#modalTasaGlobal").modal("hide"); aplicarTasaGlobal();'>
											<input type='button' class='btn btn-sm btn-warning' value='Cancelar' data-dismiss='modal'>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					<div id='modalFactorGlobal' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
						<div class='modal-dialog modal-dialog-scrollable' role='document' style="width:300px">
							<div class='modal-content'>
								<div class='modal-header'>
								        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
								          <span aria-hidden='true'>&times;</span>
								        </button>
								</div>
								<div class='modal-body'>
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">FACTOR SOBRE """),_display_(/*340.78*/mapDiccionario/*340.92*/.get("ARRIENDO")),format.raw/*340.108*/("""</span>
								  		</div>
								  		<input type="text" class="form-control center"
											id="factorGlobal"
											value="0.0000"
											onfocus="value = value.replace(/,/g,'')"
											onkeydown="return ingresoDouble(window.event)"
											autocomplete="off"
											onchange="value = formatStandar(value,4); if(value=='') """),format.raw/*348.68*/("""{"""),format.raw/*348.69*/("""value='0.0000';"""),format.raw/*348.84*/("""}"""),format.raw/*348.85*/("""">
									</div>
									<br>
									<div class='row'>
										<div class='col-sm-12' style='text-align:center'>
											<input type="button" class="btn btn-sm btn-primary" value='Aplicar' onclick='$("#modalFactorGlobal").modal("hide"); aplicarFactorGlobal();'>
											<input type='button' class='btn btn-sm btn-warning' value='Cancelar' data-dismiss='modal'>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					<div id='modalFactorGlobalRepos' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
						<div class='modal-dialog modal-dialog-scrollable' role='document' style="width:300px">
							<div class='modal-content'>
								<div class='modal-header'>
								        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
								          <span aria-hidden='true'>&times;</span>
								        </button>
								</div>
								<div class='modal-body'>
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">FACTOR SOBRE VTA/REPOS</span>
								  		</div>
								  		<input type="text" class="form-control center"
											id="factorGlobalRepos"
											value="0.0000"
											onfocus="value = value.replace(/,/g,'')"
											onkeydown="return ingresoDouble(window.event)"
											autocomplete="off"
											onchange="value = formatStandar(value,4); if(value=='') """),format.raw/*381.68*/("""{"""),format.raw/*381.69*/("""value='0.0000';"""),format.raw/*381.84*/("""}"""),format.raw/*381.85*/("""">
									</div>
									<br>
									<div class='row'>
										<div class='col-sm-12' style='text-align:center'>
											<input type="button" class="btn btn-sm btn-primary" value='Aplicar' onclick='$("#modalFactorGlobalRepos").modal("hide"); aplicarFactorGlobalRepos();'>
											<input type='button' class='btn btn-sm btn-warning' value='Cancelar' data-dismiss='modal'>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
	
					<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<TR>
								<TH colspan="8" style="background-color: white"></TH>
								<TH style="text-align:center">
									<input id="btnAplicarFactor" type="button"  value="Aplica Factor" class="btn btn-warning btn-sm rounded-pill btn-block" onclick='$("#modalFactorGlobalRepos").modal("show")'>
								</TH>
								<TH style="background-color: white"></TH>
								<TH style="text-align:center">
									<input id="btnAplicarTasa" type="button"  value="Aplica Tasa" class="btn btn-warning btn-sm rounded-pill btn-block" onclick='$("#modalTasaGlobal").modal("show")'>
								</TH>
								<TH style="text-align:center">
									<input id="btnAplicarFactor" type="button"  value="Aplica Factor" class="btn btn-warning btn-sm rounded-pill btn-block" onclick='$("#modalFactorGlobal").modal("show")'>
								</TH>
								<TH colspan="12" style="background-color: white"></TH>
							</TR>
							<TR>
								<TH>GRUPO</TH>
								<TH>CODIGO</TH>
								<TH style="min-width:30%">EQUIPO</TH>
								<TH>STOCK</TH>
								<TH>UN</TH>
								<TH>CANT</TH>
						        <TH>SOLO<br>VENTA</TH>
						        <TH>MON</TH>
								<TH>P.UNITARIO<br>VTA/REPOS</TH>
								<TH>UN<br>"""),_display_(/*421.20*/mapDiccionario/*421.34*/.get("ARR")),format.raw/*421.45*/("""</TH>
								<TH>TASA<br>"""),_display_(/*422.22*/mapDiccionario/*422.36*/.get("ARR")),format.raw/*422.47*/("""/VTA</TH>
								<TH>P.UNITARIO<br>"""),_display_(/*423.28*/mapDiccionario/*423.42*/.get("ARR")),format.raw/*423.53*/("""</TH>
								<TH>PERMAN</TH>
								<TH>P.TOTAL<br>REPOSICION</TH>
								<TH>P.TOTAL<br>"""),_display_(/*426.25*/mapDiccionario/*426.39*/.get("ARRIENDO")),format.raw/*426.55*/("""</TH>
								<TH>P.TOTAL<br>VENTA</TH>
								<TH>TOT.KG</TH>
								"""),_display_(if(mapPermiso.get("parametro.escondeLosM2")!=null && mapPermiso.get("parametro.escondeLosM2").equals("1"))/*429.116*/ {_display_(Seq[Any](format.raw/*429.118*/("""
									"""),format.raw/*430.10*/("""<TH style = "display:none">TOT.M2</TH>
								""")))}else/*431.14*/{_display_(Seq[Any](format.raw/*431.15*/("""
									"""),format.raw/*432.10*/("""<TH>TOT.M2</TH>
								""")))}),format.raw/*433.10*/("""
								"""),format.raw/*434.9*/("""<TH style = "display:none">kg</TH>
								<TH style = "display:none">m2</TH>
								<TH style = "display:none">id_equipo</TH>
								<TH style = "display:none">nrodecimal</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*441.9*/for(lista1 <- detalle) yield /*441.31*/{_display_(Seq[Any](format.raw/*441.32*/("""
								"""),format.raw/*442.9*/("""<TR>
									<td style="text-align:left;">
											"""),_display_(/*444.13*/lista1/*444.19*/.get(23)),format.raw/*444.27*/("""
									"""),format.raw/*445.10*/("""</td>
									<td style="text-align:left;">
										<input class="idEquipo" type="hidden" name="id_equipo[]" value=""""),_display_(/*447.76*/lista1/*447.82*/.get(0)),format.raw/*447.89*/("""">
										<input type="hidden" name="id_moneda[]" value=""""),_display_(/*448.59*/lista1/*448.65*/.get(8)),format.raw/*448.72*/("""">
										
										<a href="#" onclick="equipoDescripcion('"""),_display_(/*450.52*/lista1/*450.58*/.get(0)),format.raw/*450.65*/("""');">
											"""),_display_(/*451.13*/lista1/*451.19*/.get(1)),format.raw/*451.26*/("""
										"""),format.raw/*452.11*/("""</a>
									</td>
									<td style= "text-align: left;"><a href="#" onclick="equipoDescripcion('"""),_display_(/*454.82*/lista1/*454.88*/.get(0)),format.raw/*454.95*/("""');">"""),_display_(/*454.101*/lista1/*454.107*/.get(2)),format.raw/*454.114*/("""</a></td>
									<td style="text-align:center;">
										<div class="noprint">
											<a href="#" onclick="vistaStockPorEquipo('"""),_display_(/*457.55*/lista1/*457.61*/.get(0)),format.raw/*457.68*/("""');">
												<kbd style="background-color: #73C6B6">stock</kbd>
											</a>
										</div>
									</td>
									<td style="text-align:center;">"""),_display_(/*462.42*/lista1/*462.48*/.get(3)),format.raw/*462.55*/("""</td>
									<td style="text-align:center;">
										<div style="display:none" id="hiddencantidad_"""),_display_(/*464.57*/lista1/*464.63*/.get(0)),format.raw/*464.70*/("""">"""),_display_(/*464.73*/lista1/*464.79*/.get(25)),format.raw/*464.87*/("""</div>
										<input type="text" class="cantidad form-control height23px right width80px"
											name="cantidad[]"
											id="cantidad_"""),_display_(/*467.26*/lista1/*467.32*/.get(0)),format.raw/*467.39*/(""""
											value=""""),_display_(/*468.20*/lista1/*468.26*/.get(10)),format.raw/*468.34*/(""""
											onfocus="value=value.replace(/,/g,''); cantAux = value;" 
											onblur = "value = formatStandar(value, '2');"
											onkeydown="return ingresoDouble(window.event)"
											autocomplete="off"
											onchange="if(value=='') value=0; calculaLinea('"""),_display_(/*473.60*/lista1/*473.66*/.get(0)),format.raw/*473.73*/("""', '"""),_display_(/*473.78*/lista1/*473.84*/.get(20)),format.raw/*473.92*/("""');">
									</td>
									<td style="text-align:center;">
										<input type="hidden" name="esVenta[]" id="esVenta_"""),_display_(/*476.62*/lista1/*476.68*/.get(0)),format.raw/*476.75*/(""""  value=""""),_display_(/*476.86*/lista1/*476.92*/.get(11)),format.raw/*476.100*/("""">
										"""),_display_(if(lista1.get(11).equals("0"))/*477.42*/{_display_(Seq[Any](format.raw/*477.43*/("""
											"""),format.raw/*478.12*/("""<input type="checkbox" id="checkbox_"""),_display_(/*478.49*/lista1/*478.55*/.get(0)),format.raw/*478.62*/("""" onchange="checkVenta('"""),_display_(/*478.87*/lista1/*478.93*/.get(0)),format.raw/*478.100*/("""'); calculaLinea('"""),_display_(/*478.119*/lista1/*478.125*/.get(0)),format.raw/*478.132*/("""', '"""),_display_(/*478.137*/lista1/*478.143*/.get(20)),format.raw/*478.151*/("""');">
										""")))}else/*479.16*/{_display_(Seq[Any](format.raw/*479.17*/("""
											"""),format.raw/*480.12*/("""<input type="checkbox" id="checkbox_"""),_display_(/*480.49*/lista1/*480.55*/.get(0)),format.raw/*480.62*/("""" onchange="checkVenta('"""),_display_(/*480.87*/lista1/*480.93*/.get(0)),format.raw/*480.100*/("""'); calculaLinea('"""),_display_(/*480.119*/lista1/*480.125*/.get(0)),format.raw/*480.132*/("""', '"""),_display_(/*480.137*/lista1/*480.143*/.get(20)),format.raw/*480.151*/("""');" checked>
										""")))}),format.raw/*481.12*/("""
									"""),format.raw/*482.10*/("""</td>
									<td style="text-align:center;">"""),_display_(/*483.42*/lista1/*483.48*/.get(4)),format.raw/*483.55*/("""</td>
									<td style="text-align:center;">
										<div style="display:none" id="hiddenpuVentaRepos_"""),_display_(/*485.61*/lista1/*485.67*/.get(0)),format.raw/*485.74*/("""">"""),_display_(/*485.77*/lista1/*485.83*/.get(26)),format.raw/*485.91*/("""</div>
										<input type="text" class="form-control height23px right width100px"
											name="puVentaRepos[]"
											id="puVentaRepos_"""),_display_(/*488.30*/lista1/*488.36*/.get(0)),format.raw/*488.43*/(""""
											value=""""),_display_(/*489.20*/lista1/*489.26*/.get(5)),format.raw/*489.33*/(""""
											onfocus="value=value.replace(/,/g,'');" 
											onblur = "value = formatStandar(value, '"""),_display_(/*491.53*/lista1/*491.59*/.get(20)),format.raw/*491.67*/("""');"
											onkeydown="return ingresoDouble(window.event)"
											autocomplete="off"
											onchange="if(value=='') """),format.raw/*494.36*/("""{"""),format.raw/*494.37*/("""value=0;"""),format.raw/*494.45*/("""}"""),format.raw/*494.46*/(""" """),format.raw/*494.47*/("""else """),format.raw/*494.52*/("""{"""),format.raw/*494.53*/("""calculaTasa(1,'"""),_display_(/*494.69*/lista1/*494.75*/.get(0)),format.raw/*494.82*/("""', '"""),_display_(/*494.87*/lista1/*494.93*/.get(20)),format.raw/*494.101*/("""'); calculaLinea('"""),_display_(/*494.120*/lista1/*494.126*/.get(0)),format.raw/*494.133*/("""', '"""),_display_(/*494.138*/lista1/*494.144*/.get(20)),format.raw/*494.152*/("""');"""),format.raw/*494.155*/("""}"""),format.raw/*494.156*/("""">
									</td>
									<td style="text-align:center;">
										<input type="hidden" id="id_unidadTiempo_"""),_display_(/*497.53*/lista1/*497.59*/.get(0)),format.raw/*497.66*/("""" name="id_unidadTiempo[]" value=""""),_display_(/*497.101*/lista1/*497.107*/.get(9)),format.raw/*497.114*/("""">
										<select class="custom-select"  style="width: 80px;"
											onchange="$('#id_unidadTiempo_"""),_display_(/*499.43*/lista1/*499.49*/.get(0)),format.raw/*499.56*/("""').val(value);">
											<option value=""""),_display_(/*500.28*/lista1/*500.34*/.get(9)),format.raw/*500.41*/("""">"""),_display_(/*500.44*/lista1/*500.50*/.get(6)),format.raw/*500.57*/("""</option>
											"""),_display_(/*501.13*/for(lista <- listUnTiempo) yield /*501.39*/{_display_(Seq[Any](format.raw/*501.40*/("""
												"""),format.raw/*502.13*/("""<option value=""""),_display_(/*502.29*/lista/*502.34*/.id),format.raw/*502.37*/("""">"""),_display_(/*502.40*/lista/*502.45*/.nombre),format.raw/*502.52*/("""</option>
											""")))}),format.raw/*503.13*/("""
										"""),format.raw/*504.11*/("""</select>
									</td>
									<td>
										<div style="display:none" id="hiddentasaArr_"""),_display_(/*507.56*/lista1/*507.62*/.get(0)),format.raw/*507.69*/("""">"""),_display_(/*507.72*/lista1/*507.78*/.get(27)),format.raw/*507.86*/("""</div>
										<input type="text" class="tasaGlobal form-control height23px right width80px"
											id="tasaArr_"""),_display_(/*509.25*/lista1/*509.31*/.get(0)),format.raw/*509.38*/(""""
											value=""""),_display_(/*510.20*/lista1/*510.26*/.get(24)),format.raw/*510.34*/(""""
											onfocus="value=value.replace(/,/g,'').replace(/%/g,'').replace(/ /g,'')"
											onblur = "value = formatPorcentaje(value);"
											onkeydown="return ingresoDouble(window.event)"
											autocomplete="off"
											onchange="if(value=='') """),format.raw/*515.36*/("""{"""),format.raw/*515.37*/("""value='0.00 %';"""),format.raw/*515.52*/("""}"""),format.raw/*515.53*/(""" """),format.raw/*515.54*/("""else """),format.raw/*515.59*/("""{"""),format.raw/*515.60*/("""calculaTasa(2,'"""),_display_(/*515.76*/lista1/*515.82*/.get(0)),format.raw/*515.89*/("""', '"""),_display_(/*515.94*/lista1/*515.100*/.get(20)),format.raw/*515.108*/("""'); calculaLinea('"""),_display_(/*515.127*/lista1/*515.133*/.get(0)),format.raw/*515.140*/("""', '"""),_display_(/*515.145*/lista1/*515.151*/.get(20)),format.raw/*515.159*/("""');"""),format.raw/*515.162*/("""}"""),format.raw/*515.163*/("""">
									</td>
									<td style="text-align:center;">
										<div style="display:none" id="hiddenpuArriendo_"""),_display_(/*518.59*/lista1/*518.65*/.get(0)),format.raw/*518.72*/("""">"""),_display_(/*518.75*/lista1/*518.81*/.get(28)),format.raw/*518.89*/("""</div>
										<input type="text" class="factorGlobal form-control height23px right width80px"
											name="puArriendo[]"
											id="puArriendo_"""),_display_(/*521.28*/lista1/*521.34*/.get(0)),format.raw/*521.41*/(""""
											value=""""),_display_(/*522.20*/lista1/*522.26*/.get(7)),format.raw/*522.33*/(""""
											onfocus="value=value.replace(/,/g,'');" 
											onblur = "value = formatStandar(value, '"""),_display_(/*524.53*/lista1/*524.59*/.get(20)),format.raw/*524.67*/("""');"
											onkeydown="return ingresoDouble(window.event)"
											autocomplete="off"
											onchange="if(value=='') """),format.raw/*527.36*/("""{"""),format.raw/*527.37*/("""value=0;"""),format.raw/*527.45*/("""}"""),format.raw/*527.46*/(""" """),format.raw/*527.47*/("""else """),format.raw/*527.52*/("""{"""),format.raw/*527.53*/("""calculaTasa(1,'"""),_display_(/*527.69*/lista1/*527.75*/.get(0)),format.raw/*527.82*/("""', '"""),_display_(/*527.87*/lista1/*527.93*/.get(20)),format.raw/*527.101*/("""'); calculaLinea('"""),_display_(/*527.120*/lista1/*527.126*/.get(0)),format.raw/*527.133*/("""', '"""),_display_(/*527.138*/lista1/*527.144*/.get(20)),format.raw/*527.152*/("""');"""),format.raw/*527.155*/("""}"""),format.raw/*527.156*/("""">
									</td>
									<td style="text-align:center;">
										<div style="display:none" id="hiddenpermanencia_"""),_display_(/*530.60*/lista1/*530.66*/.get(0)),format.raw/*530.73*/("""">"""),_display_(/*530.76*/lista1/*530.82*/.get(29)),format.raw/*530.90*/("""</div>
										<input type="text" class="form-control height23px right"
											name="permanencia[]"
											id="permanencia_"""),_display_(/*533.29*/lista1/*533.35*/.get(0)),format.raw/*533.42*/(""""
											value=""""),_display_(/*534.20*/lista1/*534.26*/.get(12)),format.raw/*534.34*/(""""
											onfocus="value=value.replace(/,/g,'');" 
											onblur = "$('#hiddenpermanencia_"""),_display_(/*536.45*/lista1/*536.51*/.get(0)),format.raw/*536.58*/("""').text(value); value = formatStandar(value, '2');"
											onkeydown="return ingresoDouble(window.event)"
											autocomplete="off"
											onchange="if(value=='') value=0; calculaLinea('"""),_display_(/*539.60*/lista1/*539.66*/.get(0)),format.raw/*539.73*/("""', '"""),_display_(/*539.78*/lista1/*539.84*/.get(20)),format.raw/*539.92*/("""');">
									</td>
									
									
								
									<td class="totRepos" style="text-align:right;" id="totRepos_"""),_display_(/*544.71*/lista1/*544.77*/.get(0)),format.raw/*544.84*/("""">"""),_display_(/*544.87*/lista1/*544.93*/.get(13)),format.raw/*544.101*/("""</td>
									<td class="totArrie" style="text-align:right;" id="totArrie_"""),_display_(/*545.71*/lista1/*545.77*/.get(0)),format.raw/*545.84*/("""">"""),_display_(/*545.87*/lista1/*545.93*/.get(14)),format.raw/*545.101*/("""</td>
									<td class="totVenta" style="text-align:right;" id="totVenta_"""),_display_(/*546.71*/lista1/*546.77*/.get(0)),format.raw/*546.84*/("""">"""),_display_(/*546.87*/lista1/*546.93*/.get(15)),format.raw/*546.101*/("""</td>
									<td class="totKg" style="text-align:right;" id="totKg_"""),_display_(/*547.65*/lista1/*547.71*/.get(0)),format.raw/*547.78*/("""">"""),_display_(/*547.81*/lista1/*547.87*/.get(18)),format.raw/*547.95*/("""</td>
									"""),_display_(if(mapPermiso.get("parametro.escondeLosM2")!=null && mapPermiso.get("parametro.escondeLosM2").equals("1"))/*548.117*/ {_display_(Seq[Any](format.raw/*548.119*/("""
										"""),format.raw/*549.11*/("""<td class="totM2" style="display:none; text-align:right;" id="totM2_"""),_display_(/*549.80*/lista1/*549.86*/.get(0)),format.raw/*549.93*/("""">"""),_display_(/*549.96*/lista1/*549.102*/.get(19)),format.raw/*549.110*/("""</td>
									""")))}else/*550.15*/{_display_(Seq[Any](format.raw/*550.16*/("""
										"""),format.raw/*551.11*/("""<td class="totM2" style = "text-align:right;" id="totM2_"""),_display_(/*551.68*/lista1/*551.74*/.get(0)),format.raw/*551.81*/("""">"""),_display_(/*551.84*/lista1/*551.90*/.get(19)),format.raw/*551.98*/("""</td>
									""")))}),format.raw/*552.11*/("""
									
									
									"""),format.raw/*555.10*/("""<td style = "display:none" id="uniKg_"""),_display_(/*555.48*/lista1/*555.54*/.get(0)),format.raw/*555.61*/("""">"""),_display_(/*555.64*/lista1/*555.70*/.get(16)),format.raw/*555.78*/("""</td>
									<td style = "display:none" id="uniM2_"""),_display_(/*556.48*/lista1/*556.54*/.get(0)),format.raw/*556.61*/("""">"""),_display_(/*556.64*/lista1/*556.70*/.get(17)),format.raw/*556.78*/("""</td>
									<td style = "display:none" id="idEquipo_"""),_display_(/*557.51*/lista1/*557.57*/.get(0)),format.raw/*557.64*/("""">"""),_display_(/*557.67*/lista1/*557.73*/.get(0)),format.raw/*557.80*/("""</td>
									<td style = "display:none" id="nroDecimales_"""),_display_(/*558.55*/lista1/*558.61*/.get(0)),format.raw/*558.68*/("""">"""),_display_(/*558.71*/lista1/*558.77*/.get(20)),format.raw/*558.85*/("""</td>
									
								</TR>
				 			""")))}),format.raw/*561.10*/("""
						"""),format.raw/*562.7*/("""</tbody>
						<tfoot id="tfoot" style="background-color: #eeeeee; display:none">
							<TR> 
								<TH colspan="13" style= "text-align: right;">SUB-TOTALES </TH>
								<TH style="text-align:right;"><div id="subtotalRepos">0.00</div></TH>
								<TH style="text-align:right;"><div id="subtotalArrie">0.00</div></TH>
								<TH style="text-align:right;"><div id="subtotalVenta">0.00</div></TH>
								<TH></TH>
								"""),_display_(if(mapPermiso.get("parametro.escondeLosM2")!=null && mapPermiso.get("parametro.escondeLosM2").equals("1"))/*570.116*/ {_display_(Seq[Any](format.raw/*570.118*/("""
									"""),format.raw/*571.10*/("""<TH style = "display:none"></TH>
								""")))}else/*572.14*/{_display_(Seq[Any](format.raw/*572.15*/("""
									"""),format.raw/*573.10*/("""<TH></TH>
								""")))}),format.raw/*574.10*/("""
								"""),format.raw/*575.9*/("""<TH style = "display:none"></TH>
								<TH style = "display:none"></TH>
								<TH style = "display:none"></TH>
							</TR>
							<TR> 
								<TH colspan="13" style="text-align:right;">DESCUENTOS</TH>
								<TH style="text-align:right;"></TH>
								<TH style="text-align:center;">
									<input type="text" class="form-control height23px right"
										id="dctoArriendo"
										name="dctoArriendo"
										value=""""),_display_(/*586.19*/formCotiza/*586.29*/.dctoArriendo),format.raw/*586.42*/(""" """),format.raw/*586.43*/("""%"
										onfocus="value=value.replace(/,/g,'').replace(/%/g,'').replace(/ /g,'')" 
										onblur = "value = formatPorcentaje(value);"
										onkeydown="return ingresoDouble(window.event)"
										onchange="sumaTotales(); dctoObligado=1;">
									</td>
								</TH>
								<TH style="text-align:center;">
									<input type="text" class="form-control height23px right"
										id="dctoVenta"
										name="dctoVenta"
										value=""""),_display_(/*597.19*/formCotiza/*597.29*/.dctoVenta),format.raw/*597.39*/(""" """),format.raw/*597.40*/("""%"
										onfocus="value=value.replace(/,/g,'').replace(/%/g,'').replace(/ /g,'')" 
										onblur = "value = formatPorcentaje(value);"
										onkeydown="return ingresoDouble(window.event)"
										onchange="sumaTotales(); dctoObligado=1;">
									</td>
								</TH>
								<TH></TH>
								
								"""),_display_(if(mapPermiso.get("parametro.escondeLosM2")!=null && mapPermiso.get("parametro.escondeLosM2").equals("1"))/*606.116*/ {_display_(Seq[Any](format.raw/*606.118*/("""
									"""),format.raw/*607.10*/("""<TH style = "display:none"></TH>
								""")))}else/*608.14*/{_display_(Seq[Any](format.raw/*608.15*/("""
									"""),format.raw/*609.10*/("""<TH></TH>
								""")))}),format.raw/*610.10*/("""
								"""),format.raw/*611.9*/("""<TH style = "display:none"></TH>
								<TH style = "display:none"></TH>
								<TH style = "display:none"></TH>
							</TR>
							<TR>
								<TH colspan="5" style= "text-align: right;">Total cantidad</TH>
								<TH colspan="1" style= "text-align: right;"><div id="granTotCant">0.00</div></TH>
								<TH colspan="7" style= "text-align: right;">TOTALES </TH>
								<TH style="text-align:right;"><div id="granTotRepos">0.00</div></TH>
								<TH style="text-align:right;"><div id="granTotArrie">0.00</div></TH>
								<TH style="text-align:right;"><div id="granTotVenta">0.00</div></TH>
								<TH style="text-align:right;"><div id="granTotKg">0.00</div></TH>
								"""),_display_(if(mapPermiso.get("parametro.escondeLosM2")!=null && mapPermiso.get("parametro.escondeLosM2").equals("1"))/*623.116*/ {_display_(Seq[Any](format.raw/*623.118*/("""
									"""),format.raw/*624.10*/("""<TH style="display:none; text-align:right;"><div id="granTotM2">0.00</div></TH>
								""")))}else/*625.14*/{_display_(Seq[Any](format.raw/*625.15*/("""
									"""),format.raw/*626.10*/("""<TH style = "text-align:right;"><div id="granTotM2">0.00</div></TH>
								""")))}),format.raw/*627.10*/("""
								
								"""),format.raw/*629.9*/("""<TH style = "display:none"></TH>
								<TH style = "display:none"></TH>
								<TH style = "display:none"></TH>
							</TR>
					</tfoot>
					</table>
				</div>
			</div>
			
			
			<div class="noprint" align="left">
					<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
						<input type="button"  id="agregarEquipo" value='AgregarEquipo' class="btn btn-success btn-sm rounded-pill btn-block" onclick="agregaNuevoEquipo();" >
					</div>
				<div class="row justify-content-md-center" >
					<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
						<input type="button"  value="Cancelar" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
					</div>
					<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
						<input type="button"  id="verifica" value='Verificar' class="btn btn-success btn-sm rounded-pill btn-block" onclick="verificaCotizacion();" >
					</div>
					<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
						<input type="button"  id="modifica" value='Modificar' class="btn btn-warning btn-sm rounded-pill btn-block" onclick="modificaCotizacion();" style="visibility:hidden">
					</div>
					<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
						<input type="submit" id="aplica" value='Generar Cotización' class="btn btn-primary btn-sm rounded-pill btn-block" style="visibility:hidden">
					</div>
				</div>
			</div>
			
		</div>
	</form>
	
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
								<TH>"""),_display_(/*680.14*/mapDiccionario/*680.28*/.get("RUT")),format.raw/*680.39*/("""</TH>
								<TH>NICK</TH>
								<TH>NOMBRE</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*686.9*/for(lista1 <- listClientes) yield /*686.36*/{_display_(Seq[Any](format.raw/*686.37*/("""
								"""),format.raw/*687.9*/("""<TR onClick="$('#id_cliente').val("""),_display_(/*687.44*/lista1/*687.50*/.getId()),format.raw/*687.58*/("""); $('#rutCliente').val('"""),_display_(/*687.84*/lista1/*687.90*/.getRut()),format.raw/*687.99*/("""');$('#nombreCliente').val('"""),_display_(/*687.128*/lista1/*687.134*/.getNickName()),format.raw/*687.148*/("""');" data-dismiss="modal">
									<td  style="text-align:left;">"""),_display_(/*688.41*/lista1/*688.47*/.getRut()),format.raw/*688.56*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*689.41*/lista1/*689.47*/.getNickName()),format.raw/*689.61*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*690.41*/lista1/*690.47*/.getNombre()),format.raw/*690.59*/("""</td>
								</TR>
				 			""")))}),format.raw/*692.10*/("""
						"""),format.raw/*693.7*/("""</tbody>
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
					<h5 class="modal-title">SELECCIONAR PROYECTO</h5>
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
								<TH>"""),_display_(/*725.14*/mapDiccionario/*725.28*/.get("Comuna")),format.raw/*725.42*/("""</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*729.9*/for(lista1 <- listProyectos) yield /*729.37*/{_display_(Seq[Any](format.raw/*729.38*/("""
								"""),format.raw/*730.9*/("""<TR onClick="$('#id_proyecto').val("""),_display_(/*730.45*/lista1/*730.51*/.getId()),format.raw/*730.59*/("""); $('#nombreProyecto').val('"""),_display_(/*730.89*/lista1/*730.95*/.getNickName()),format.raw/*730.109*/("""');" data-dismiss="modal">
									<td  style="text-align:left;">"""),_display_(/*731.41*/lista1/*731.47*/.getNickName()),format.raw/*731.61*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*732.41*/lista1/*732.47*/.getNombre()),format.raw/*732.59*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*733.41*/lista1/*733.47*/.getComuna()),format.raw/*733.59*/("""</td>
								</TR>
				 			""")))}),format.raw/*735.10*/("""
						"""),format.raw/*736.7*/("""</tbody>
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
					<div id="vistaModListaCotizacion"></div>
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
					<form action="/cotiPlantilla/" method="POST"  onsubmit="return validarForm2();">
						<button type="submit" id="btnSubmit2" class="btn btn-sm  btn-success" style="font-size: 10px;">DESCARGAR PLANTILLA</button>
					</form>
					<br>
					<form id="cotiValidarPlantilla" action="/routes2/cotiValidarPlantillaSel/" method="POST" enctype="multipart/form-data">
						<input type="hidden" id="formSucursal" name="id_sucursal" value=""""),_display_(/*787.73*/sucursal/*787.81*/.getId()),format.raw/*787.89*/("""">
						<span class="btn btn-info btn-sm btn-file" style="font-size: 8px;">
							<div>SUBIR ARCHIVO</div>
							<input type="hidden" id="id_bodegaEmpresa" name="id_bodegaEmpresa" value=""""),_display_(/*790.83*/formCotiza/*790.93*/.id_bodegaEmpresa),format.raw/*790.110*/("""">
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
	
	<form id="copyCotizacion" action="/routes2/cotizaModificaJsonSel/" method="POST" enctype="multipart/form-data">
		<input type="hidden" id="formCotizacion" name="id_cotizacion" value="0">
	</form>
	
	<div id="modalListaEquipos" class="modal" role="dialog" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog modal-dialog-scrollable modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">LISTADO DE EQUIPOS (SELECT)</h5>
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				          <span aria-hidden="true">&times;</span>
				        </button>
				</div>
				<div class="modal-body">
					<div id="vistalistadoEquipos"></div>
	   				<div class="row justify-content-md-center" >
						<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
							<button type='button' class='btn btn-sm btn-warning rounded-pill btn-block' style='font-size: 10px;' data-dismiss='modal'>Cancelar</button>
						</div>
						<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
							<button type="button" class="btn btn-sm btn-primary rounded-pill btn-block" style='font-size: 10px;' data-dismiss='modal' onclick="agregarEquipos()">SELECCIONAR</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div id="modalStockPorEquipo" class="modal" role="dialog" data-backdrop="static" data-keyboard="false">
		<div class='modal-dialog modal-dialog-scrollable' style='max-width: 80% !important;' role='document'>
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">STOCK DISPONIBLE</h5>
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				          <span aria-hidden="true">&times;</span>
				        </button>
				</div>
				<div class="modal-body">
					<div id="stockPorEquipo"></div>
	   				<div class="row">
						<div class="col-sm-12" style="text-align:center">
							<button type="button" class="btn btn-sm  btn-warning" style="font-size: 10px;" data-dismiss="modal">Cancelar</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
""")))}),format.raw/*854.2*/("""



"""),format.raw/*858.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*860.31*/("""{"""),format.raw/*860.32*/("""
		"""),format.raw/*861.3*/("""$('#tablaListaClientes').DataTable("""),format.raw/*861.38*/("""{"""),format.raw/*861.39*/("""
	    	"""),format.raw/*862.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
	    	"order": [[ 1, "asc" ]],
	    	"language": """),format.raw/*865.19*/("""{"""),format.raw/*865.20*/("""
	        	"""),format.raw/*866.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*867.10*/("""}"""),format.raw/*867.11*/("""
	  	"""),format.raw/*868.5*/("""}"""),format.raw/*868.6*/(""" """),format.raw/*868.7*/(""");

		$('#tablaListaProyectos').DataTable("""),format.raw/*870.39*/("""{"""),format.raw/*870.40*/("""
	    	"""),format.raw/*871.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
	    	"order": [[ 0, "asc" ]],
	    	"language": """),format.raw/*874.19*/("""{"""),format.raw/*874.20*/("""
	        	"""),format.raw/*875.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*876.10*/("""}"""),format.raw/*876.11*/("""
	  	"""),format.raw/*877.5*/("""}"""),format.raw/*877.6*/(""" """),format.raw/*877.7*/(""");

		sumaTotales();
		
		$('#tablaPrincipal').DataTable("""),format.raw/*881.34*/("""{"""),format.raw/*881.35*/("""
		    	"""),format.raw/*882.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[15, 50, 100, 200, -1], [15, 50, 100, 200, "All"]],
		    	"order": [[ 0, "asc" ],[ 2, "asc" ]],
		    	"language": """),format.raw/*885.20*/("""{"""),format.raw/*885.21*/("""
		        	"""),format.raw/*886.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*887.11*/("""}"""),format.raw/*887.12*/("""
		"""),format.raw/*888.3*/("""}"""),format.raw/*888.4*/(""" """),format.raw/*888.5*/(""");
		
		
		  
		document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*893.2*/("""}"""),format.raw/*893.3*/(""");
	
	const validarNumero = (numero) =>"""),format.raw/*895.35*/("""{"""),format.raw/*895.36*/("""
		"""),format.raw/*896.3*/("""var formData = new FormData();
		formData.append('numeroCoti',numero);
        $.ajax("""),format.raw/*898.16*/("""{"""),format.raw/*898.17*/("""
            """),format.raw/*899.13*/("""url: "/existeNumeroCotizacionAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*906.36*/("""{"""),format.raw/*906.37*/("""
				"""),format.raw/*907.5*/("""if(respuesta!="OK")"""),format.raw/*907.24*/("""{"""),format.raw/*907.25*/("""
					"""),format.raw/*908.6*/("""$("#numeroCoti").val(""""),_display_(/*908.29*/formCotiza/*908.39*/.numeroCoti),format.raw/*908.50*/("""");
					alertify.alert(respuesta, function () """),format.raw/*909.44*/("""{"""),format.raw/*909.45*/(""" """),format.raw/*909.46*/("""}"""),format.raw/*909.47*/(""");
				"""),format.raw/*910.5*/("""}"""),format.raw/*910.6*/("""
	     	"""),format.raw/*911.8*/("""}"""),format.raw/*911.9*/("""
        """),format.raw/*912.9*/("""}"""),format.raw/*912.10*/(""");
	"""),format.raw/*913.2*/("""}"""),format.raw/*913.3*/("""
	
	"""),format.raw/*915.2*/("""const vistaStockPorEquipo = (id_equipo) =>"""),format.raw/*915.44*/("""{"""),format.raw/*915.45*/("""
		"""),format.raw/*916.3*/("""var formData = new FormData();
		formData.append('id_equipo',id_equipo);
        $.ajax("""),format.raw/*918.16*/("""{"""),format.raw/*918.17*/("""
            """),format.raw/*919.13*/("""url: "/tablaInvPorEquipoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*926.36*/("""{"""),format.raw/*926.37*/("""
				"""),format.raw/*927.5*/("""document.getElementById('stockPorEquipo').innerHTML = respuesta;
				$('#modalStockPorEquipo').modal('show');
	     	"""),format.raw/*929.8*/("""}"""),format.raw/*929.9*/("""
        """),format.raw/*930.9*/("""}"""),format.raw/*930.10*/(""");
	"""),format.raw/*931.2*/("""}"""),format.raw/*931.3*/("""
	
	
	"""),format.raw/*934.2*/("""const sumaTotales = () =>"""),format.raw/*934.27*/("""{"""),format.raw/*934.28*/("""
		"""),format.raw/*935.3*/("""$("#tablaPrincipal").dataTable().fnDestroy();
		let tabla = document.getElementById("tablaPrincipal");
		let totCant = 0;
		
		let cant = 0;
		$(".cantidad").each(function() """),format.raw/*940.34*/("""{"""),format.raw/*940.35*/("""
			"""),format.raw/*941.4*/("""let val = $(this).val().replace(/,/g,'');
			cant += parseFloat(val);
		"""),format.raw/*943.3*/("""}"""),format.raw/*943.4*/(""");
		
		let repos = 0;
		$(".totRepos").each(function() """),format.raw/*946.34*/("""{"""),format.raw/*946.35*/("""
			"""),format.raw/*947.4*/("""let val = $(this).text().replace(/,/g,'');
			repos += parseFloat(val);
		"""),format.raw/*949.3*/("""}"""),format.raw/*949.4*/("""); $("#subtotalRepos").text(formatStandar(repos,'"""),_display_(/*949.54*/numDecParaTot),format.raw/*949.67*/("""'));
		
		let arr = 0;
		$(".totArrie").each(function() """),format.raw/*952.34*/("""{"""),format.raw/*952.35*/("""
			"""),format.raw/*953.4*/("""let val = $(this).text().replace(/,/g,'');
			arr += parseFloat(val);
		"""),format.raw/*955.3*/("""}"""),format.raw/*955.4*/("""); $("#subtotalArrie").text(formatStandar(arr,'"""),_display_(/*955.52*/numDecParaTot),format.raw/*955.65*/("""'));
		
		let vta = 0;
		$(".totVenta").each(function() """),format.raw/*958.34*/("""{"""),format.raw/*958.35*/("""
			"""),format.raw/*959.4*/("""let val = $(this).text().replace(/,/g,'');
			vta += parseFloat(val);
		"""),format.raw/*961.3*/("""}"""),format.raw/*961.4*/("""); $("#subtotalVenta").text(formatStandar(vta,'"""),_display_(/*961.52*/numDecParaTot),format.raw/*961.65*/("""'));
		
		let kg = 0;
		$(".totKg").each(function() """),format.raw/*964.31*/("""{"""),format.raw/*964.32*/("""
			"""),format.raw/*965.4*/("""let val = $(this).text().replace(/,/g,'');
			kg += parseFloat(val);
		"""),format.raw/*967.3*/("""}"""),format.raw/*967.4*/(""");
		
		let m2 = 0;
		$(".totM2").each(function() """),format.raw/*970.31*/("""{"""),format.raw/*970.32*/("""
			"""),format.raw/*971.4*/("""let val = $(this).text().replace(/,/g,'');
			m2 += parseFloat(val);
		"""),format.raw/*973.3*/("""}"""),format.raw/*973.4*/(""");
		
		let dctoArriendo = $("#dctoArriendo").val().replace(/,/g,'').replace("%","").trim();
		let dctoVenta = $("#dctoVenta").val().replace(/,/g,'').replace("%","").trim();
		
		$("#granTotRepos").text(formatStandar(repos,'"""),_display_(/*978.49*/numDecParaTot),format.raw/*978.62*/("""'));
		$("#granTotArrie").text(formatStandar((arr*(1-dctoArriendo/100)),'"""),_display_(/*979.70*/numDecParaTot),format.raw/*979.83*/("""'));
		$("#granTotVenta").text(formatStandar((vta*(1-dctoVenta/100)),'"""),_display_(/*980.67*/numDecParaTot),format.raw/*980.80*/("""'));
		$("#granTotKg").text(formatStandar(kg,2));
		$("#granTotM2").text(formatStandar(m2,2));
		$("#granTotCant").text(formatStandar(cant,2));
		
	"""),format.raw/*985.2*/("""}"""),format.raw/*985.3*/("""
	
	"""),format.raw/*987.2*/("""const checkVenta = (id_equipo) =>"""),format.raw/*987.35*/("""{"""),format.raw/*987.36*/("""
		"""),format.raw/*988.3*/("""let esVenta = $("#esVenta_"+id_equipo).val();
		if(esVenta==0)"""),format.raw/*989.17*/("""{"""),format.raw/*989.18*/("""
			"""),format.raw/*990.4*/("""$("#esVenta_"+id_equipo).val('1');
		"""),format.raw/*991.3*/("""}"""),format.raw/*991.4*/("""else"""),format.raw/*991.8*/("""{"""),format.raw/*991.9*/("""
			"""),format.raw/*992.4*/("""$("#esVenta_"+id_equipo).val('0');
		"""),format.raw/*993.3*/("""}"""),format.raw/*993.4*/("""
	"""),format.raw/*994.2*/("""}"""),format.raw/*994.3*/("""
	
	"""),format.raw/*996.2*/("""let calculaTasa = (flag, id_equipo, numDec) => """),format.raw/*996.49*/("""{"""),format.raw/*996.50*/("""
		"""),format.raw/*997.3*/("""if(flag==1)"""),format.raw/*997.14*/("""{"""),format.raw/*997.15*/("""
			"""),format.raw/*998.4*/("""let uniVta = $("#puVentaRepos_"+id_equipo).val().replace(/,/g,'');
			let uniArr = $("#puArriendo_"+id_equipo).val().replace(/,/g,'');
			let uniTasa = parseFloat(uniArr)/parseFloat(uniVta);
			$("#tasaArr_"+id_equipo).val(formatStandar(uniTasa * 100,2)+" %");
			$("#hiddentasaArr_"+id_equipo).text(uniTasa);
		"""),format.raw/*1003.3*/("""}"""),format.raw/*1003.4*/("""else if(flag==2)"""),format.raw/*1003.20*/("""{"""),format.raw/*1003.21*/("""
			"""),format.raw/*1004.4*/("""let uniVta = $("#puVentaRepos_"+id_equipo).val().replace(/,/g,'');
			let uniTasa = $("#tasaArr_"+id_equipo).val().replace(/,/g,'').replace(/%/g,'').replace(/ /g,'');
			let uniArr = parseFloat(uniVta)*parseFloat(uniTasa)/100;
			$("#puArriendo_"+id_equipo).val(formatStandar(uniArr,numDec));
			$("#hiddentasaArr_"+id_equipo).text(uniTasa);
		"""),format.raw/*1009.3*/("""}"""),format.raw/*1009.4*/("""else"""),format.raw/*1009.8*/("""{"""),format.raw/*1009.9*/("""
			"""),format.raw/*1010.4*/("""let uniVta = $("#puVentaRepos_"+id_equipo).val().replace(/,/g,'');
			let uniArr = $("#puArriendo_"+id_equipo).val().replace(/,/g,'');
			let uniTasa = parseFloat(uniArr)/parseFloat(uniVta);
			$("#tasaArr_"+id_equipo).val(formatStandar(uniTasa * 100,2)+" %");
			$("#hiddentasaArr_"+id_equipo).text(uniTasa);
		"""),format.raw/*1015.3*/("""}"""),format.raw/*1015.4*/("""
		
	"""),format.raw/*1017.2*/("""}"""),format.raw/*1017.3*/("""
	
	"""),format.raw/*1019.2*/("""const calculaLinea = (id_equipo, numDec) =>"""),format.raw/*1019.45*/("""{"""),format.raw/*1019.46*/("""
		"""),format.raw/*1020.3*/("""let cant = $("#cantidad_"+id_equipo).val().replace(/,/g,'');
		let ckeckEsVta = $("#checkbox_"+id_equipo).val();
		let esVenta = $("#esVenta_"+id_equipo).val();
		let uniVta = $("#puVentaRepos_"+id_equipo).val().replace(/,/g,'');
		let uniArr = $("#puArriendo_"+id_equipo).val().replace(/,/g,'');
		let perm = $("#permanencia_"+id_equipo).val().replace(/,/g,'');
		let uniKg = $("#uniKg_"+id_equipo).text().replace(/,/g,'');
		let uniM2 = $("#uniM2_"+id_equipo).text().replace(/,/g,'');
		let totRepos = parseFloat(cant) * parseFloat(uniVta);
		let totArr = 0;
		let totVta = 0;
		let totKg = 0;
		let totM2 = 0;
		if(esVenta == 0)"""),format.raw/*1033.19*/("""{"""),format.raw/*1033.20*/("""
			 """),format.raw/*1034.5*/("""totRepos = parseFloat(cant) * parseFloat(uniVta);
			 totArr = parseFloat(cant) * parseFloat(perm) * parseFloat(uniArr);
			 totVta = 0;
			 totKg = parseFloat(cant) * parseFloat(uniKg);
			 totM2 = parseFloat(cant) * parseFloat(uniM2);
		"""),format.raw/*1039.3*/("""}"""),format.raw/*1039.4*/("""else"""),format.raw/*1039.8*/("""{"""),format.raw/*1039.9*/("""
			 """),format.raw/*1040.5*/("""totRepos = 0;
			 totArr = 0;
			 totVta = parseFloat(cant) * parseFloat(uniVta);
			 totKg = parseFloat(cant) * parseFloat(uniKg);
			 totM2 = parseFloat(cant) * parseFloat(uniM2);
		"""),format.raw/*1045.3*/("""}"""),format.raw/*1045.4*/("""
		"""),format.raw/*1046.3*/("""$("#cantidad_"+id_equipo).val(formatStandar(cant,2));
		$("#puVentaRepos_"+id_equipo).val(formatStandar(uniVta,numDec));
		$("#puArriendo_"+id_equipo).val(formatStandar(uniArr,numDec));
		$("#totRepos_"+id_equipo).text(formatStandar(totRepos,numDec));
		$("#totArrie_"+id_equipo).text(formatStandar(totArr,numDec));
		$("#totVenta_"+id_equipo).text(formatStandar(totVta,numDec));
		$("#totKg_"+id_equipo).text(formatStandar(totKg,2));
		$("#totM2_"+id_equipo).text(formatStandar(totM2,2));
		
		
		$("#hiddencantidad_"+id_equipo).text(cant);
		$("#hiddenpuVentaRepos_"+id_equipo).text(uniVta);
		$("#hiddenpuArriendo_"+id_equipo).text(uniArr);
		
		
		
		if(verificando == 1)"""),format.raw/*1062.23*/("""{"""),format.raw/*1062.24*/("""
			"""),format.raw/*1063.4*/("""sumaTotales();
		"""),format.raw/*1064.3*/("""}"""),format.raw/*1064.4*/("""
		
	"""),format.raw/*1066.2*/("""}"""),format.raw/*1066.3*/("""
	
	"""),format.raw/*1068.2*/("""let verificando = 0;
	const verificaCotizacion = () =>"""),format.raw/*1069.34*/("""{"""),format.raw/*1069.35*/("""
		"""),format.raw/*1070.3*/("""document.getElementById("btnCopiaCoti").style.display = "none";
		document.getElementById("btnCargaExcel").style.display = "none";
		sumaTotales();
		let granTotRepos = $("#granTotRepos").text().replace(/,/g,'');
		let granTotArrie = $("#granTotArrie").text().replace(/,/g,'');
		let granTotVenta = $("#granTotVenta").text().replace(/,/g,'');
		let sumaAux = parseFloat(granTotRepos) + parseFloat(granTotArrie) + parseFloat(granTotVenta);
		if(sumaAux > 0)"""),format.raw/*1077.18*/("""{"""),format.raw/*1077.19*/("""
			"""),format.raw/*1078.4*/("""let tabla = document.getElementById("tablaPrincipal");
			for(i=2; i<tabla.rows.length-3; i++)"""),format.raw/*1079.40*/("""{"""),format.raw/*1079.41*/("""
				"""),format.raw/*1080.5*/("""let cant = $(".cantidad",tabla.rows[i].cells[5]).val().replace(/,/g,'');
				let arr = tabla.rows[i].cells[13].innerHTML.replace(/,/g,'');
				let vta = tabla.rows[i].cells[14].innerHTML.replace(/,/g,'');
				if((parseFloat(cant)+parseFloat(arr)+parseFloat(vta))<=0)"""),format.raw/*1083.62*/("""{"""),format.raw/*1083.63*/("""
					"""),format.raw/*1084.6*/("""tabla.rows[i].style.display = 'none';
				"""),format.raw/*1085.5*/("""}"""),format.raw/*1085.6*/("""
			"""),format.raw/*1086.4*/("""}"""),format.raw/*1086.5*/("""
			"""),format.raw/*1087.4*/("""verificando = 1;
			document.getElementById('tfoot').style.display = '';
			document.getElementById('modifica').style.visibility = 'visible';
			document.getElementById('aplica').style.visibility = 'visible';
			document.getElementById('verifica').style.visibility = 'hidden';
			

			document.getElementById('selSucursal').disabled = true;
			document.getElementById('selComercial').disabled = true;
			$('#btnAplicarTasa').hide();
			$('#btnAplicarFactor').hide();
			
	
		"""),format.raw/*1100.3*/("""}"""),format.raw/*1100.4*/("""else"""),format.raw/*1100.8*/("""{"""),format.raw/*1100.9*/("""
			"""),format.raw/*1101.4*/("""$('#tablaPrincipal').DataTable("""),format.raw/*1101.35*/("""{"""),format.raw/*1101.36*/("""
		    	"""),format.raw/*1102.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[15, 50, 100, 200, -1], [15, 50, 100, 200, "All"]],
		    	"order": [[ 0, "asc" ],[ 2, "asc" ]],
		    	"language": """),format.raw/*1105.20*/("""{"""),format.raw/*1105.21*/("""
		        	"""),format.raw/*1106.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*1107.11*/("""}"""),format.raw/*1107.12*/("""
			"""),format.raw/*1108.4*/("""}"""),format.raw/*1108.5*/(""" """),format.raw/*1108.6*/(""");
			alertify.alert('NO PUEDE VERIFICAR UNA COTIZACION SIN CANTIDADES', function () """),format.raw/*1109.83*/("""{"""),format.raw/*1109.84*/(""" """),format.raw/*1109.85*/("""}"""),format.raw/*1109.86*/(""");
		"""),format.raw/*1110.3*/("""}"""),format.raw/*1110.4*/("""
	"""),format.raw/*1111.2*/("""}"""),format.raw/*1111.3*/("""
	
	"""),format.raw/*1113.2*/("""const modificaCotizacion = () =>"""),format.raw/*1113.34*/("""{"""),format.raw/*1113.35*/("""
		"""),format.raw/*1114.3*/("""let tabla = document.getElementById("tablaPrincipal");
		for(i=2; i<tabla.rows.length-3; i++)"""),format.raw/*1115.39*/("""{"""),format.raw/*1115.40*/("""
			"""),format.raw/*1116.4*/("""tabla.rows[i].style.display = '';
		"""),format.raw/*1117.3*/("""}"""),format.raw/*1117.4*/("""
		"""),format.raw/*1118.3*/("""$('#tablaPrincipal').DataTable("""),format.raw/*1118.34*/("""{"""),format.raw/*1118.35*/("""
		    	"""),format.raw/*1119.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[15, 50, 100, 200, -1], [15, 50, 100, 200, "All"]],
		    	"order": [[ 0, "asc" ],[ 2, "asc" ]],
		    	"language": """),format.raw/*1122.20*/("""{"""),format.raw/*1122.21*/("""
		        	"""),format.raw/*1123.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*1124.11*/("""}"""),format.raw/*1124.12*/("""
		"""),format.raw/*1125.3*/("""}"""),format.raw/*1125.4*/(""" """),format.raw/*1125.5*/(""");
		document.getElementById('tfoot').style.display = 'none';
		document.getElementById('modifica').style.visibility = 'hidden';
		document.getElementById('aplica').style.visibility = 'hidden';
		document.getElementById('verifica').style.visibility = 'visible';
		document.getElementById('selSucursal').disabled = false;
		document.getElementById('selComercial').disabled = false;
		$('#btnAplicarTasa').show();
		$('#btnAplicarFactor').show();
		verificando = 0;
	"""),format.raw/*1135.2*/("""}"""),format.raw/*1135.3*/("""
	
	"""),format.raw/*1137.2*/("""let importDesdeOtraCoti = "0";
	const seleccionaCotizacion = (id_cotizacion) =>"""),format.raw/*1138.49*/("""{"""),format.raw/*1138.50*/("""
		"""),format.raw/*1139.3*/("""document.getElementById("btnCopiaCoti").style.display = "none";
		document.getElementById("btnCargaExcel").style.display = "none";
		$("#formCotizacion").val(id_cotizacion);
		$("#copyCotizacion").submit();
	"""),format.raw/*1143.2*/("""}"""),format.raw/*1143.3*/("""
	
	"""),format.raw/*1145.2*/("""if(""""),_display_(/*1145.7*/importDesdeExcel),format.raw/*1145.23*/("""" == "1")"""),format.raw/*1145.32*/("""{"""),format.raw/*1145.33*/("""
		"""),format.raw/*1146.3*/("""document.getElementById("btnCopiaCoti").style.display = "none";
		document.getElementById("btnCargaExcel").style.display = "none";
	"""),format.raw/*1148.2*/("""}"""),format.raw/*1148.3*/("""
	
	
	
	"""),format.raw/*1152.2*/("""let dctoObligado = 0;
	const validarForm = () =>"""),format.raw/*1153.27*/("""{"""),format.raw/*1153.28*/("""
		"""),format.raw/*1154.3*/("""$("#aplica").attr('disabled',true);
		let flag = true;
		sumaTotales();
		let granTotRepos = $("#granTotRepos").text().replace(/,/g,'');
		let granTotArrie = $("#granTotArrie").text().replace(/,/g,'');
		let granTotVenta = $("#granTotVenta").text().replace(/,/g,'');
		let sumaAux = parseFloat(granTotRepos) + parseFloat(granTotArrie) + parseFloat(granTotVenta);
		
		
		if(sumaAux <= 0)"""),format.raw/*1163.19*/("""{"""),format.raw/*1163.20*/("""
			"""),format.raw/*1164.4*/("""flag = false;
			alertify.alert('NO PUEDE GENERAR UNA COTIZACION SIN CANTIDADES', function () """),format.raw/*1165.81*/("""{"""),format.raw/*1165.82*/("""
				"""),format.raw/*1166.5*/("""$("#aplica").attr('disabled',false);
				return(flag);
     		"""),format.raw/*1168.8*/("""}"""),format.raw/*1168.9*/(""");
		"""),format.raw/*1169.3*/("""}"""),format.raw/*1169.4*/("""else if($("#id_cliente").val()=='0')"""),format.raw/*1169.40*/("""{"""),format.raw/*1169.41*/("""
			"""),format.raw/*1170.4*/("""flag = false;
			alertify.alert('NO PUEDE GENERAR UNA COTIZACION SIN ASIGNAR UN CLIENTE', function () """),format.raw/*1171.89*/("""{"""),format.raw/*1171.90*/("""
				"""),format.raw/*1172.5*/("""$("#aplica").attr('disabled',false);
				return(flag);
     		"""),format.raw/*1174.8*/("""}"""),format.raw/*1174.9*/(""");
		"""),format.raw/*1175.3*/("""}"""),format.raw/*1175.4*/("""else if(dctoObligado==0 && '"""),_display_(/*1175.33*/mapDiccionario/*1175.47*/.get("nEmpresa")),format.raw/*1175.63*/("""' == 'MONTAX')"""),format.raw/*1175.77*/("""{"""),format.raw/*1175.78*/("""
			"""),format.raw/*1176.4*/("""flag = false;
			alertify.alert('ESTA OBLIGADO A INGRESAR UN DESCUENTO, SI NO APLICA DESCUENTO INGREZAR UN CERO', function () """),format.raw/*1177.113*/("""{"""),format.raw/*1177.114*/("""
				"""),format.raw/*1178.5*/("""$("#aplica").attr('disabled',false);
				return(flag);
     		"""),format.raw/*1180.8*/("""}"""),format.raw/*1180.9*/(""");
		"""),format.raw/*1181.3*/("""}"""),format.raw/*1181.4*/("""else if ((sumaAux-parseFloat(granTotRepos))<=0)"""),format.raw/*1181.51*/("""{"""),format.raw/*1181.52*/("""
			"""),format.raw/*1182.4*/("""flag = false;
			alertify.alert('NO PUEDE GENERAR UNA COTIZACION QUE SUMA CERO, REVISAR PERMANENCIA', function () """),format.raw/*1183.101*/("""{"""),format.raw/*1183.102*/("""
				"""),format.raw/*1184.5*/("""$("#aplica").attr('disabled',false);
				return(flag);
     		"""),format.raw/*1186.8*/("""}"""),format.raw/*1186.9*/(""");
		"""),format.raw/*1187.3*/("""}"""),format.raw/*1187.4*/("""else"""),format.raw/*1187.8*/("""{"""),format.raw/*1187.9*/("""
			"""),format.raw/*1188.4*/("""let tabla = document.getElementById("tablaPrincipal");
			for(i=2; i<tabla.rows.length-3; i++)"""),format.raw/*1189.40*/("""{"""),format.raw/*1189.41*/("""
				"""),format.raw/*1190.5*/("""let cant = $(".cantidad",tabla.rows[i].cells[5]).val().replace(/,/g,'');
				let arr = tabla.rows[i].cells[13].innerHTML.replace(/,/g,'');
				let vta = tabla.rows[i].cells[14].innerHTML.replace(/,/g,'');
				if((parseFloat(cant)+parseFloat(arr)+parseFloat(vta))<=0)"""),format.raw/*1193.62*/("""{"""),format.raw/*1193.63*/("""
					"""),format.raw/*1194.6*/("""tabla.deleteRow(i);
					i--;
				"""),format.raw/*1196.5*/("""}"""),format.raw/*1196.6*/("""
			"""),format.raw/*1197.4*/("""}"""),format.raw/*1197.5*/("""
			"""),format.raw/*1198.4*/("""return(true);
		"""),format.raw/*1199.3*/("""}"""),format.raw/*1199.4*/("""
		"""),format.raw/*1200.3*/("""return(flag);
	"""),format.raw/*1201.2*/("""}"""),format.raw/*1201.3*/("""

	"""),format.raw/*1203.2*/("""let extArray = new Array(".gif", ".jpg", ".png", ".jpeg", ".pdf", ".xls", ".doc", ".xlsx", ".docx", ".tar", ".zip", ".rar");
	const LimitAttach = (form, file) => """),format.raw/*1204.38*/("""{"""),format.raw/*1204.39*/("""
		"""),format.raw/*1205.3*/("""let allowSubmit = false;
		if (!file) return;
		while (file.indexOf("\\") != -1)"""),format.raw/*1207.35*/("""{"""),format.raw/*1207.36*/("""
			"""),format.raw/*1208.4*/("""file = file.slice(file.indexOf("\\") + 1);
		"""),format.raw/*1209.3*/("""}"""),format.raw/*1209.4*/("""
		"""),format.raw/*1210.3*/("""let extencion = file.slice(file.lastIndexOf(".")).toLowerCase();
		for (var i = 0; i < extArray.length; i++) """),format.raw/*1211.45*/("""{"""),format.raw/*1211.46*/("""
			"""),format.raw/*1212.4*/("""if (extArray[i] == extencion) """),format.raw/*1212.34*/("""{"""),format.raw/*1212.35*/(""" """),format.raw/*1212.36*/("""allowSubmit = true; break; """),format.raw/*1212.63*/("""}"""),format.raw/*1212.64*/("""
		"""),format.raw/*1213.3*/("""}"""),format.raw/*1213.4*/("""
		"""),format.raw/*1214.3*/("""if (allowSubmit) """),format.raw/*1214.20*/("""{"""),format.raw/*1214.21*/("""
			"""),format.raw/*1215.4*/("""var file = $("#docAdjunto")[0].files[0];
			var fileSize = file.size; //tamaño del archivo
			if(fileSize>100000000)"""),format.raw/*1217.26*/("""{"""),format.raw/*1217.27*/("""
				"""),format.raw/*1218.5*/("""alert("Se permiten únicamente archivos que no superen los 100 MB,"
				+" el que se intenta subir pesa: "+Math.round(file.size/10)/100+" KB");
				form.docAdjunto.value="";
			"""),format.raw/*1221.4*/("""}"""),format.raw/*1221.5*/("""else"""),format.raw/*1221.9*/("""{"""),format.raw/*1221.10*/("""
				"""),format.raw/*1222.5*/("""alert("Documento subido con éxito");
				$("#txtBtn").text("Cambiar Documento");
			"""),format.raw/*1224.4*/("""}"""),format.raw/*1224.5*/("""
		"""),format.raw/*1225.3*/("""}"""),format.raw/*1225.4*/("""else"""),format.raw/*1225.8*/("""{"""),format.raw/*1225.9*/("""
			"""),format.raw/*1226.4*/("""alert("Se permiten únicamente archivos con la extención: " 
			+ (extArray.join("  ")) + "\nPor favor, seleccione otro archivo "
			+ "e intente de nuevo.");
			form.docAdjunto.value="";
		"""),format.raw/*1230.3*/("""}"""),format.raw/*1230.4*/("""
	"""),format.raw/*1231.2*/("""}"""),format.raw/*1231.3*/("""
	
	"""),format.raw/*1233.2*/("""let extArray2 = new Array(".xls", ".xlsx");
	function subirArchivo(form, file) """),format.raw/*1234.36*/("""{"""),format.raw/*1234.37*/("""
		"""),format.raw/*1235.3*/("""allowSubmit = false;
		if (!file) return;
		while (file.indexOf("\\") != -1)
			file = file.slice(file.indexOf("\\") + 1);
		ext = file.slice(file.lastIndexOf(".")).toLowerCase();
		for (var i = 0; i < extArray2.length; i++) """),format.raw/*1240.46*/("""{"""),format.raw/*1240.47*/("""
			"""),format.raw/*1241.4*/("""if (extArray2[i] == ext) """),format.raw/*1241.29*/("""{"""),format.raw/*1241.30*/(""" """),format.raw/*1241.31*/("""allowSubmit = true; break; """),format.raw/*1241.58*/("""}"""),format.raw/*1241.59*/("""
		"""),format.raw/*1242.3*/("""}"""),format.raw/*1242.4*/("""
		"""),format.raw/*1243.3*/("""if (allowSubmit) """),format.raw/*1243.20*/("""{"""),format.raw/*1243.21*/("""
			"""),format.raw/*1244.4*/("""$("#cotiValidarPlantilla").submit();
		"""),format.raw/*1245.3*/("""}"""),format.raw/*1245.4*/("""else"""),format.raw/*1245.8*/("""{"""),format.raw/*1245.9*/("""
			"""),format.raw/*1246.4*/("""alertify.alert("Se permiten &uacute;nicamente archivos con la extenci&oacute;n: " 
			+ (extArray2.join("  ")) + "\nPor favor, seleccione otro archivo "
			+ "e intente de nuevo.");
			form.archivoXLSX.value="";
		"""),format.raw/*1250.3*/("""}"""),format.raw/*1250.4*/("""
	"""),format.raw/*1251.2*/("""}"""),format.raw/*1251.3*/("""

	"""),format.raw/*1253.2*/("""const aplicarTasaGlobal = () => """),format.raw/*1253.34*/("""{"""),format.raw/*1253.35*/("""
		"""),format.raw/*1254.3*/("""$("#tablaPrincipal").dataTable().fnDestroy();
		$(".tasaGlobal").each(function() """),format.raw/*1255.36*/("""{"""),format.raw/*1255.37*/("""
			 """),format.raw/*1256.5*/("""$(this).val($("#tasaGlobal").val());
		"""),format.raw/*1257.3*/("""}"""),format.raw/*1257.4*/(""");
		let tasa = $("#tasaGlobal").val();
		tasa = tasa.replace(/,/g,'').replace(/%/g,'').replace(/ /g,'');
		let tabla = document.getElementById("tablaPrincipal");
		for(i=2; i<tabla.rows.length-3; i++)"""),format.raw/*1261.39*/("""{"""),format.raw/*1261.40*/("""
			"""),format.raw/*1262.4*/("""let id_equipo = tabla.rows[i].cells[20].innerHTML;
			let nroDecimal = tabla.rows[i].cells[21].innerHTML;
			let prepos = $("#puVentaRepos_"+id_equipo).val().replace(/,/g,'');
			$("#puArriendo_"+id_equipo).val(formatStandar(parseFloat(prepos*tasa/100),nroDecimal));
			calculaLinea(id_equipo,nroDecimal);
		"""),format.raw/*1267.3*/("""}"""),format.raw/*1267.4*/("""
		"""),format.raw/*1268.3*/("""sumaTotales();
		document.getElementById('selSucursal').disabled = true;
		document.getElementById('selComercial').disabled = true;
		$('#tablaPrincipal').DataTable("""),format.raw/*1271.34*/("""{"""),format.raw/*1271.35*/("""
	    	"""),format.raw/*1272.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[15, 50, 100, 200, -1], [15, 50, 100, 200, "All"]],
	    	"order": [[ 0, "asc" ],[ 2, "asc" ]],
	    	"language": """),format.raw/*1275.19*/("""{"""),format.raw/*1275.20*/("""
	        	"""),format.raw/*1276.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*1277.10*/("""}"""),format.raw/*1277.11*/("""
		"""),format.raw/*1278.3*/("""}"""),format.raw/*1278.4*/(""");
	"""),format.raw/*1279.2*/("""}"""),format.raw/*1279.3*/("""
	
	"""),format.raw/*1281.2*/("""const aplicarFactorGlobal = () => """),format.raw/*1281.36*/("""{"""),format.raw/*1281.37*/("""
		"""),format.raw/*1282.3*/("""$("#tablaPrincipal").dataTable().fnDestroy();
		let factorGlobal = $("#factorGlobal").val();
		factorGlobal = factorGlobal.replace(/,/g,'');
		let tabla = document.getElementById("tablaPrincipal");
		for(i=2; i<tabla.rows.length-3; i++)"""),format.raw/*1286.39*/("""{"""),format.raw/*1286.40*/("""
			"""),format.raw/*1287.4*/("""let id_equipo = tabla.rows[i].cells[20].innerHTML;
			let nroDecimal = tabla.rows[i].cells[21].innerHTML;
			let pArr = $("#puArriendo_"+id_equipo).val().replace(/,/g,'');
			let prepos = $("#puVentaRepos_"+id_equipo).val().replace(/,/g,'');
			$("#puArriendo_"+id_equipo).val(formatStandar(parseFloat(pArr*factorGlobal),nroDecimal));
			if(parseFloat(prepos)>0)"""),format.raw/*1292.28*/("""{"""),format.raw/*1292.29*/("""
				"""),format.raw/*1293.5*/("""$("#tasaArr_"+id_equipo).val(formatStandar(parseFloat(pArr*factorGlobal/prepos*100),2) + "%");
			"""),format.raw/*1294.4*/("""}"""),format.raw/*1294.5*/("""
			"""),format.raw/*1295.4*/("""calculaLinea(id_equipo,nroDecimal);
		"""),format.raw/*1296.3*/("""}"""),format.raw/*1296.4*/("""
		"""),format.raw/*1297.3*/("""sumaTotales();
		document.getElementById('selSucursal').disabled = true;
		document.getElementById('selComercial').disabled = true;
		$('#tablaPrincipal').DataTable("""),format.raw/*1300.34*/("""{"""),format.raw/*1300.35*/("""
	    	"""),format.raw/*1301.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[15, 50, 100, 200, -1], [15, 50, 100, 200, "All"]],
	    	"order": [[ 0, "asc" ],[ 2, "asc" ]],
	    	"language": """),format.raw/*1304.19*/("""{"""),format.raw/*1304.20*/("""
	        	"""),format.raw/*1305.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*1306.10*/("""}"""),format.raw/*1306.11*/("""
		"""),format.raw/*1307.3*/("""}"""),format.raw/*1307.4*/(""");
	"""),format.raw/*1308.2*/("""}"""),format.raw/*1308.3*/("""
	
	"""),format.raw/*1310.2*/("""const aplicarFactorGlobalRepos = () => """),format.raw/*1310.41*/("""{"""),format.raw/*1310.42*/("""
		"""),format.raw/*1311.3*/("""$("#tablaPrincipal").dataTable().fnDestroy();
		let factorGlobalRepos = $("#factorGlobalRepos").val();
		factorGlobalRepos = factorGlobalRepos.replace(/,/g,'');
		let tabla = document.getElementById("tablaPrincipal");
		for(i=2; i<tabla.rows.length-3; i++)"""),format.raw/*1315.39*/("""{"""),format.raw/*1315.40*/("""
			"""),format.raw/*1316.4*/("""let id_equipo = tabla.rows[i].cells[20].innerHTML;
			let nroDecimal = tabla.rows[i].cells[21].innerHTML;
			let pArr = $("#puArriendo_"+id_equipo).val().replace(/,/g,'');
			let prepos = $("#puVentaRepos_"+id_equipo).val().replace(/,/g,'');
			prepos = prepos * factorGlobalRepos;
			$("#puVentaRepos_"+id_equipo).val(formatStandar(parseFloat(prepos),nroDecimal));
			if(parseFloat(prepos*factorGlobalRepos)>0)"""),format.raw/*1322.46*/("""{"""),format.raw/*1322.47*/("""
				"""),format.raw/*1323.5*/("""$("#tasaArr_"+id_equipo).val(formatStandar(parseFloat(pArr/prepos*100),2) + "%");
			"""),format.raw/*1324.4*/("""}"""),format.raw/*1324.5*/("""
			"""),format.raw/*1325.4*/("""calculaLinea(id_equipo,nroDecimal);
		"""),format.raw/*1326.3*/("""}"""),format.raw/*1326.4*/("""
		"""),format.raw/*1327.3*/("""sumaTotales();
		document.getElementById('selSucursal').disabled = true;
		document.getElementById('selComercial').disabled = true;
		$('#tablaPrincipal').DataTable("""),format.raw/*1330.34*/("""{"""),format.raw/*1330.35*/("""
	    	"""),format.raw/*1331.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[15, 50, 100, 200, -1], [15, 50, 100, 200, "All"]],
	    	"order": [[ 0, "asc" ],[ 2, "asc" ]],
	    	"language": """),format.raw/*1334.19*/("""{"""),format.raw/*1334.20*/("""
	        	"""),format.raw/*1335.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*1336.10*/("""}"""),format.raw/*1336.11*/("""
		"""),format.raw/*1337.3*/("""}"""),format.raw/*1337.4*/(""");
	"""),format.raw/*1338.2*/("""}"""),format.raw/*1338.3*/("""
	
	"""),format.raw/*1340.2*/("""const actualizaComerciales = (id_sucursal) =>"""),format.raw/*1340.47*/("""{"""),format.raw/*1340.48*/("""
		"""),format.raw/*1341.3*/("""var formData = new FormData();
		formData.append('id_sucursal',id_sucursal);
        $.ajax("""),format.raw/*1343.16*/("""{"""),format.raw/*1343.17*/("""
            """),format.raw/*1344.13*/("""url: "/actualizaComercialesAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*1351.36*/("""{"""),format.raw/*1351.37*/("""
				"""),format.raw/*1352.5*/("""document.getElementById('actualComerciales').innerHTML = respuesta;
				$("#formSucursal").val(id_sucursal);
	     	"""),format.raw/*1354.8*/("""}"""),format.raw/*1354.9*/("""
        """),format.raw/*1355.9*/("""}"""),format.raw/*1355.10*/(""");	
	"""),format.raw/*1356.2*/("""}"""),format.raw/*1356.3*/("""
	
	"""),format.raw/*1358.2*/("""const actualizaPrecios = (id_sucursal) =>"""),format.raw/*1358.43*/("""{"""),format.raw/*1358.44*/("""
		"""),format.raw/*1359.3*/("""document.getElementById('bloquear2').style.display="block";
			var formData = new FormData();
			formData.append('id_sucursal',id_sucursal);
	        $.ajax("""),format.raw/*1362.17*/("""{"""),format.raw/*1362.18*/("""
	            """),format.raw/*1363.14*/("""url: "/actualizaPreciosAjax/",
	            type: "POST",
	            method: "POST",
	            data: formData,
	            cache: false,
	            contentType: false,
		     	processData: false,
		     	success: function(rs)"""),format.raw/*1370.30*/("""{"""),format.raw/*1370.31*/("""
			
					"""),format.raw/*1372.6*/("""$("#tablaPrincipal").dataTable().fnDestroy();
					
					let tabla = document.getElementById("tablaPrincipal");
					
					for(i=2; i<tabla.rows.length-3; i++)"""),format.raw/*1376.42*/("""{"""),format.raw/*1376.43*/("""
						"""),format.raw/*1377.7*/("""let id_equipo = tabla.rows[i].cells[20].innerHTML;
						let nroDecimal = tabla.rows[i].cells[21].innerHTML;
						
						for(let i=0; i<rs.length; i++)"""),format.raw/*1380.37*/("""{"""),format.raw/*1380.38*/("""
							"""),format.raw/*1381.8*/("""if(rs[i].id_equipo == id_equipo)"""),format.raw/*1381.40*/("""{"""),format.raw/*1381.41*/("""
								"""),format.raw/*1382.9*/("""let puVentaRepos = rs[i].precioVenta;
								let puArriendo = rs[i].precioArriendo;
								let tasaArriendo = 0
								if(parseFloat(puVentaRepos) > 0)"""),format.raw/*1385.41*/("""{"""),format.raw/*1385.42*/("""
									"""),format.raw/*1386.10*/("""tasaArriendo = parseFloat(puArriendo)/ parseFloat(puVentaRepos);
								"""),format.raw/*1387.9*/("""}"""),format.raw/*1387.10*/("""
								"""),format.raw/*1388.9*/("""$("#hiddenpuVentaRepos_"+id_equipo).val(puVentaRepos);
								$("#puVentaRepos_"+id_equipo).val(formatStandar(parseFloat(puVentaRepos),nroDecimal));
								$("#hiddenpuArriendo_"+id_equipo).val(puArriendo);
								$("#puArriendo_"+id_equipo).val(formatStandar(parseFloat(puArriendo),nroDecimal));
								$("#tasaArr_"+id_equipo).val(formatPorcentaje(parseFloat(tasaArriendo) * 100));
								calculaLinea(id_equipo,nroDecimal);
							"""),format.raw/*1394.8*/("""}"""),format.raw/*1394.9*/("""
						"""),format.raw/*1395.7*/("""}"""),format.raw/*1395.8*/("""
						
					"""),format.raw/*1397.6*/("""}"""),format.raw/*1397.7*/("""

					"""),format.raw/*1399.6*/("""sumaTotales();
					$('#tablaPrincipal').DataTable("""),format.raw/*1400.37*/("""{"""),format.raw/*1400.38*/("""
				    	"""),format.raw/*1401.10*/(""""fixedHeader": true,
				    	"lengthMenu": [[15, 50, 100, 200, -1], [15, 50, 100, 200, "All"]],
				    	"order": [[ 0, "asc" ],[ 2, "asc" ]],
				    	"language": """),format.raw/*1404.22*/("""{"""),format.raw/*1404.23*/("""
				        	"""),format.raw/*1405.14*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
				        """),format.raw/*1406.13*/("""}"""),format.raw/*1406.14*/("""
					"""),format.raw/*1407.6*/("""}"""),format.raw/*1407.7*/(""");
					document.getElementById('bloquear2').style.display="none";
				"""),format.raw/*1409.5*/("""}"""),format.raw/*1409.6*/("""
	        """),format.raw/*1410.10*/("""}"""),format.raw/*1410.11*/(""");	

	"""),format.raw/*1412.2*/("""}"""),format.raw/*1412.3*/("""
	
	"""),format.raw/*1414.2*/("""const listadoCotizaciones = () =>"""),format.raw/*1414.35*/("""{"""),format.raw/*1414.36*/("""
		"""),format.raw/*1415.3*/("""var formData = new FormData();
		formData.append('id_sucursal',id_sucursal);
        $.ajax("""),format.raw/*1417.16*/("""{"""),format.raw/*1417.17*/("""
            """),format.raw/*1418.13*/("""url: "/routes2/actualizaListaCotiSelAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*1425.36*/("""{"""),format.raw/*1425.37*/("""
				"""),format.raw/*1426.5*/("""document.getElementById('vistaModListaCotizacion').innerHTML = respuesta;
				$('#tablaListaCotizacion').DataTable("""),format.raw/*1427.42*/("""{"""),format.raw/*1427.43*/("""
				    	"""),format.raw/*1428.10*/(""""fixedHeader": true,
				    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
				    	"order": [[ 2, "desc" ],[ 1, "desc" ],[ 0, "asc" ]],
				    	"language": """),format.raw/*1431.22*/("""{"""),format.raw/*1431.23*/("""
				        	"""),format.raw/*1432.14*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
				        """),format.raw/*1433.13*/("""}"""),format.raw/*1433.14*/("""
				"""),format.raw/*1434.5*/("""}"""),format.raw/*1434.6*/(""" """),format.raw/*1434.7*/(""");
				$('#modalListaCotizacion').modal('show');
	     	"""),format.raw/*1436.8*/("""}"""),format.raw/*1436.9*/("""
        """),format.raw/*1437.9*/("""}"""),format.raw/*1437.10*/(""");
	"""),format.raw/*1438.2*/("""}"""),format.raw/*1438.3*/("""
	
	"""),format.raw/*1440.2*/("""const validarForm2 = () =>"""),format.raw/*1440.28*/("""{"""),format.raw/*1440.29*/("""
		"""),format.raw/*1441.3*/("""$("#btnSubmit2").attr('disabled',true);
		return(true);
	"""),format.raw/*1443.2*/("""}"""),format.raw/*1443.3*/("""
	
	
	
	"""),format.raw/*1447.2*/("""let jsonDetalle = """"),_display_(/*1447.22*/json),format.raw/*1447.26*/("""";
	jsonDetalle = jsonDetalle.replace(/&quot;/g,'"');
	let arrayDetalle = JSON.parse(jsonDetalle);
	
	let arrayUnTiempo = [];
	let arrAux = [];
	"""),_display_(/*1453.3*/for(lista <- listUnTiempo) yield /*1453.29*/{_display_(Seq[Any](format.raw/*1453.30*/("""
		"""),format.raw/*1454.3*/("""arrAux.push(""""),_display_(/*1454.17*/lista/*1454.22*/.id),format.raw/*1454.25*/("""");
		arrAux.push(""""),_display_(/*1455.17*/lista/*1455.22*/.nombre),format.raw/*1455.29*/("""");
		arrayUnTiempo.push(arrAux);
		arrAux = [];
	""")))}),format.raw/*1458.3*/("""
	
	"""),format.raw/*1460.2*/("""let escondeLosM2 = '"""),_display_(/*1460.23*/mapPermiso/*1460.33*/.get("parametro.escondeLosM2")),format.raw/*1460.63*/("""';
	
	const agregaNuevoEquipo = () =>"""),format.raw/*1462.33*/("""{"""),format.raw/*1462.34*/("""
		"""),format.raw/*1463.3*/("""$("#tablaPrincipal").dataTable().fnDestroy();
		let array = [];
		for(var i=0; i < arrayDetalle.length; i++) """),format.raw/*1465.46*/("""{"""),format.raw/*1465.47*/("""
				"""),format.raw/*1466.5*/("""let flag = true;
				$(".idEquipo").each(function() """),format.raw/*1467.36*/("""{"""),format.raw/*1467.37*/("""
					"""),format.raw/*1468.6*/("""if(arrayDetalle[i][0] == $(this).val())"""),format.raw/*1468.45*/("""{"""),format.raw/*1468.46*/("""
						"""),format.raw/*1469.7*/("""flag = false;
					"""),format.raw/*1470.6*/("""}"""),format.raw/*1470.7*/("""
				"""),format.raw/*1471.5*/("""}"""),format.raw/*1471.6*/(""");
				if(flag)"""),format.raw/*1472.13*/("""{"""),format.raw/*1472.14*/("""
						"""),format.raw/*1473.7*/("""array.push(arrayDetalle[i]);
				"""),format.raw/*1474.5*/("""}"""),format.raw/*1474.6*/("""
		"""),format.raw/*1475.3*/("""}"""),format.raw/*1475.4*/("""
		"""),format.raw/*1476.3*/("""$('#tablaPrincipal').DataTable("""),format.raw/*1476.34*/("""{"""),format.raw/*1476.35*/("""
			    	"""),format.raw/*1477.9*/(""""fixedHeader": true,
			    	"lengthMenu": [[15, 50, 100, 200, -1], [15, 50, 100, 200, "All"]],
			    	"order": [[ 0, "asc" ],[ 2, "asc" ]],
			    	"language": """),format.raw/*1480.21*/("""{"""),format.raw/*1480.22*/("""
			        	"""),format.raw/*1481.13*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
			        """),format.raw/*1482.12*/("""}"""),format.raw/*1482.13*/("""
				"""),format.raw/*1483.5*/("""}"""),format.raw/*1483.6*/(""" """),format.raw/*1483.7*/(""");
		
		let modTable = "<table id=\"tablaSelEquipos\" class=\"table table-sm table-hover table-bordered table-condensed table-fluid\">"
			+ "<thead style=\"background-color: #eeeeee\">"
			+ "<TR><TH style=\"vertical-align: top;\">GRUPO</TH>"
			+ "<TH>CODIGO</TH><TH>EQUIPO</TH><TH>STOCK</TH><TH>SELECT</TH></TR>"
			+ "</thead><tbody>";
		for(var i=0; i < array.length; i++) """),format.raw/*1490.39*/("""{"""),format.raw/*1490.40*/("""
			"""),format.raw/*1491.4*/("""modTable +="<TR><td style=\"text-align:left;\">"+ array[i][23] + "</td>";
			modTable +="<td style=\"text-align:left;\">"+ array[i][1] + "</td>";
			modTable +="<td style=\"text-align:left;\">"+ array[i][2] + "</td>";
			modTable +="<td style=\"text-align:center;\"><div class=\"noprint\">"+ 
							"<a href=\"#\" onclick=\"vistaStockPorEquipo("+array[i][0]+");\" tabindex=\"-1\">"+
							"<kbd style=\"background-color: #73C6B6\">stock</kbd>"+
							"</a></div></td>";
			modTable +="<td  style=\"text-align:center;\">"+
							"<input type=\"checkbox\" id=\"sel_"+array[i][0]+"\" value =\"0\" onchange=\"seleccionaPorEq("+array[i][0]+",value)\"></td>";
			modTable +="</TR>";
		"""),format.raw/*1501.3*/("""}"""),format.raw/*1501.4*/("""
		"""),format.raw/*1502.3*/("""modTable +="</tbody></table>";
		
		array = [];
		document.getElementById('vistalistadoEquipos').innerHTML = modTable;
		
		$('#tablaSelEquipos').DataTable("""),format.raw/*1507.35*/("""{"""),format.raw/*1507.36*/("""
		    	"""),format.raw/*1508.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 2, "asc" ]],
		    	"language": """),format.raw/*1511.20*/("""{"""),format.raw/*1511.21*/("""
		        	"""),format.raw/*1512.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*1513.11*/("""}"""),format.raw/*1513.12*/("""
		  """),format.raw/*1514.5*/("""}"""),format.raw/*1514.6*/(""" """),format.raw/*1514.7*/(""");
		
		$('#modalListaEquipos').modal('show');
						

	"""),format.raw/*1519.2*/("""}"""),format.raw/*1519.3*/("""
	
	"""),format.raw/*1521.2*/("""let equipSelecionados="";
	const seleccionaPorEq = (id_equipo, valor) => """),format.raw/*1522.48*/("""{"""),format.raw/*1522.49*/("""
		"""),format.raw/*1523.3*/("""if(valor == 0) """),format.raw/*1523.18*/("""{"""),format.raw/*1523.19*/("""
			"""),format.raw/*1524.4*/("""document.getElementById("sel_"+id_equipo).value = "1";
			equipSelecionados += id_equipo + ",";
		"""),format.raw/*1526.3*/("""}"""),format.raw/*1526.4*/("""else"""),format.raw/*1526.8*/("""{"""),format.raw/*1526.9*/("""
			"""),format.raw/*1527.4*/("""document.getElementById("sel_"+id_equipo).value = "0";
			let aux = ""+id_equipo + ",";
			equipSelecionados = equipSelecionados.replace(aux,"");
		"""),format.raw/*1530.3*/("""}"""),format.raw/*1530.4*/("""
	"""),format.raw/*1531.2*/("""}"""),format.raw/*1531.3*/("""
	
	"""),format.raw/*1533.2*/("""const agregarEquipos = (id_equipo, valor) => """),format.raw/*1533.47*/("""{"""),format.raw/*1533.48*/("""
		
		"""),format.raw/*1535.3*/("""if(equipSelecionados.length>0) """),format.raw/*1535.34*/("""{"""),format.raw/*1535.35*/("""
			
			"""),format.raw/*1537.4*/("""equipSelecionados = equipSelecionados.substring(0,equipSelecionados.length-1);
			let aux = equipSelecionados.split(",");
			equipSelecionados = "";
			
			$("#tablaPrincipal").dataTable().fnDestroy();
			let tableReg = document.getElementById("tablaPrincipal");
			
			for(var i=0; i < arrayDetalle.length; i++) """),format.raw/*1544.47*/("""{"""),format.raw/*1544.48*/("""
			
				"""),format.raw/*1546.5*/("""for(var j=0; j<aux.length; j++)"""),format.raw/*1546.36*/("""{"""),format.raw/*1546.37*/("""
					
					"""),format.raw/*1548.6*/("""if(arrayDetalle[i][0] == aux[j])"""),format.raw/*1548.38*/("""{"""),format.raw/*1548.39*/("""
						
						"""),format.raw/*1550.7*/("""let row = tableReg.insertRow(2);
						
						let cell = row.insertCell(0);
						cell.style.textAlign = "left";
						cell.innerHTML = arrayDetalle[i][23];
				
						cell = row.insertCell(1);
						cell.style.textAlign = "left";
						cell.innerHTML = "<input class=\"idEquipo\" type=\"hidden\" name=\"id_equipo[]\" value=\""+arrayDetalle[i][0]+"\">"+
								"<input type=\"hidden\" name=\"id_moneda[]\" value=\""+arrayDetalle[i][8]+"\">"+
								"<a href=\"#\" onclick=\"equipoDescripcion('"+arrayDetalle[i][0]+"');\">"+arrayDetalle[i][1]+"</a>";
						
						cell = row.insertCell(2);
						cell.style.textAlign = "left";
						cell.innerHTML = "<a href=\"#\" onclick=\"equipoDescripcion('"+arrayDetalle[i][0]+"');\">"+arrayDetalle[i][2]+"</a>";
						
						cell = row.insertCell(3);
						cell.style.textAlign = "center";
						cell.innerHTML = "<div class=\"noprint\"><a href=\"#\" onclick=\"vistaStockPorEquipo('"+arrayDetalle[i][0]+"');\">"+
								"<kbd style=\"background-color: #73C6B6\">stock</kbd></a></div>";
						
						cell = row.insertCell(4);
						cell.style.textAlign = "center";
						cell.innerHTML = arrayDetalle[i][3];
									
						cell = row.insertCell(5);
						cell.style.textAlign = "center";
						cell.innerHTML = "<div style=\"display:none\" id=\"hiddencantidad_"+arrayDetalle[i][0]+"\">"+arrayDetalle[i][25]+"</div>"+
								"<input type=\"text\" class=\"cantidad form-control height23px right width80px\""+
											" name=\"cantidad[]\""+
											" id=\"cantidad_"+arrayDetalle[i][0]+"\""+
											" value=\""+arrayDetalle[i][10]+"\""+
											" onfocus=\"value=value.replace(/,/g,''); cantAux = value;\""+
											" onblur = \"value = formatStandar(value, '2');\""+
											" onkeydown=\"return ingresoDouble(window.event)\""+
											" autocomplete=\"off\""+
											" onchange=\"if(value=='') value=0; calculaLinea('"+arrayDetalle[i][0]+"', '"+arrayDetalle[i][20]+"');\">";

						cell = row.insertCell(6);
						cell.style.textAlign = "center";
						let x6 = "<input type=\"hidden\" name=\"esVenta[]\" id=\"esVenta_"+arrayDetalle[i][0]+"\"  value=\""+arrayDetalle[i][11]+"\">";
								if(arrayDetalle[i][11] == 0)"""),format.raw/*1591.37*/("""{"""),format.raw/*1591.38*/("""
									"""),format.raw/*1592.10*/("""x6 += "<input type=\"checkbox\" id=\"checkbox_"+arrayDetalle[i][0]+"\" onchange=\"checkVenta('"+arrayDetalle[i][0]+"'); calculaLinea('"+arrayDetalle[i][0]+"', '"+arrayDetalle[i][20]+"');\">";
								"""),format.raw/*1593.9*/("""}"""),format.raw/*1593.10*/("""else"""),format.raw/*1593.14*/("""{"""),format.raw/*1593.15*/("""
									"""),format.raw/*1594.10*/("""x6 += "<input type=\"checkbox\" id=\"checkbox_"+arrayDetalle[i][0]+"\" onchange=\"checkVenta('"+arrayDetalle[i][0]+"'); calculaLinea('"+arrayDetalle[i][0]+"', '"+arrayDetalle[i][20]+"');\" checked>";
								"""),format.raw/*1595.9*/("""}"""),format.raw/*1595.10*/("""
						"""),format.raw/*1596.7*/("""cell.innerHTML = x6;
						
						cell = row.insertCell(7);
						cell.style.textAlign = "center";
						cell.innerHTML = arrayDetalle[i][4];
						
						cell = row.insertCell(8);
						cell.style.textAlign = "center";
						cell.innerHTML = "<div style=\"display:none\" id=\"hiddenpuVentaRepos_"+arrayDetalle[i][0]+"\">"+arrayDetalle[i][26]+"</div>"+
									"<input type=\"text\" class=\"form-control height23px right width100px\""+
											" name=\"puVentaRepos[]\""+
											" id=\"puVentaRepos_"+arrayDetalle[i][0]+"\""+
											" value=\""+arrayDetalle[i][5]+"\""+
											" onfocus=\"value=value.replace(/,/g,'');\""+
											" onblur =\"value = formatStandar(value, '"+arrayDetalle[i][20]+"');\""+
											" onkeydown=\"return ingresoDouble(window.event)\""+
											" autocomplete=\"off\""+
											" onchange=\"if(value=='') """),format.raw/*1613.39*/("""{"""),format.raw/*1613.40*/("""value=0;"""),format.raw/*1613.48*/("""}"""),format.raw/*1613.49*/(""" """),format.raw/*1613.50*/("""else """),format.raw/*1613.55*/("""{"""),format.raw/*1613.56*/("""calculaTasa(1,'"+arrayDetalle[i][0]+"', '"+arrayDetalle[i][20]+"'); calculaLinea('"+arrayDetalle[i][0]+"', '"+arrayDetalle[i][20]+"');"""),format.raw/*1613.190*/("""}"""),format.raw/*1613.191*/("""\">";
						
						cell = row.insertCell(9);
						cell.style.textAlign = "center";
						let x9 = "<input type=\"hidden\" id=\"id_unidadTiempo_"+arrayDetalle[i][0]+"\" name=\"id_unidadTiempo[]\" value=\""+arrayDetalle[i][9]+"\">"+
									"<select class=\"custom-select\"  style=\"width: 80px;\""+
											"onchange=\"$('#id_unidadTiempo_"+arrayDetalle[i][0]+"').val(value);\">"+
											"<option value=\""+arrayDetalle[i][9]+"\">"+arrayDetalle[i][6]+"</option>";
									for(var k=0; k<arrayUnTiempo.length; k++)"""),format.raw/*1621.51*/("""{"""),format.raw/*1621.52*/("""
										"""),format.raw/*1622.11*/("""x9 += "<option value=\""+arrayUnTiempo[k][0]+"\">"+arrayUnTiempo[k][1]+"</option>";
									"""),format.raw/*1623.10*/("""}"""),format.raw/*1623.11*/("""	
									"""),format.raw/*1624.10*/("""x9 += "</select>";
						cell.innerHTML = x9;
										
						cell = row.insertCell(10);
						cell.innerHTML = "<div style=\"display:none\" id=\"hiddentasaArr_"+arrayDetalle[i][0]+"\">"+arrayDetalle[i][27]+"</div>"+
									"<input type=\text\" class=\"tasaGlobal form-control height23px right width80px\""+
										" id=\"tasaArr_"+arrayDetalle[i][0]+"\""+
										" value=\""+arrayDetalle[i][24]+"\""+
										" onfocus=\"value=value.replace(/,/g,'').replace(/%/g,'').replace(/ /g,'')\""+
										" onblur = \"value = formatPorcentaje(value);\""+
										" onkeydown=\"return ingresoDouble(window.event)\""+
										" autocomplete=\"off\""+
										" onchange=\"if(value=='') """),format.raw/*1636.38*/("""{"""),format.raw/*1636.39*/("""value='0.00 %';"""),format.raw/*1636.54*/("""}"""),format.raw/*1636.55*/(""" """),format.raw/*1636.56*/("""else """),format.raw/*1636.61*/("""{"""),format.raw/*1636.62*/("""calculaTasa(2,'"+arrayDetalle[i][0]+"', '"+arrayDetalle[i][20]+"'); calculaLinea('"+arrayDetalle[i][0]+"', '"+arrayDetalle[i][20]+"');"""),format.raw/*1636.196*/("""}"""),format.raw/*1636.197*/("""\">";
											
						cell = row.insertCell(11);
						cell.style.textAlign = "center";
						cell.innerHTML = "<div style=\"display:none\" id=\"hiddenpuArriendo_"+arrayDetalle[i][0]+"\">"+arrayDetalle[i][28]+"</div>"+
										"<input type=\"text\" class=\"factorGlobal form-control height23px right width80px\""+
											" name=\"puArriendo[]\""+
											" id=\"puArriendo_"+arrayDetalle[i][0]+"\""+
											" value=\""+arrayDetalle[i][7]+"\""+
											" onfocus=\"value=value.replace(/,/g,'');\""+ 
											" onblur = \"value = formatStandar(value, '"+arrayDetalle[i][20]+"');\""+
											" onkeydown=\"return ingresoDouble(window.event)\""+
											" autocomplete=\"off\""+
											" onchange=\"if(value=='') """),format.raw/*1649.39*/("""{"""),format.raw/*1649.40*/("""value=0;"""),format.raw/*1649.48*/("""}"""),format.raw/*1649.49*/(""" """),format.raw/*1649.50*/("""else """),format.raw/*1649.55*/("""{"""),format.raw/*1649.56*/("""calculaTasa(1,'"+arrayDetalle[i][0]+"', '"+arrayDetalle[i][20]+"'); calculaLinea('"+arrayDetalle[i][0]+"', '"+arrayDetalle[i][20]+"');"""),format.raw/*1649.190*/("""}"""),format.raw/*1649.191*/("""\">";
						
						cell = row.insertCell(12);
						cell.style.textAlign = "center";
						cell.innerHTML = "<div style=\"display:none\" id=\"hiddenpermanencia_"+arrayDetalle[i][0]+"\">"+arrayDetalle[i][29]+"</div>"+
										"<input type=\"text\" class=\"form-control height23px right\""+
											" name=\"permanencia[]\""+
											" id=\"permanencia_"+arrayDetalle[i][0]+"\""+
											" value=\""+arrayDetalle[i][12]+"\""+
											" onfocus=\"value=value.replace(/,/g,'');\""+ 
											" onblur = \"$('#hiddenpermanencia_"+arrayDetalle[i][0]+"').text(value); value = formatStandar(value, '2');\""+
											" onkeydown=\"return ingresoDouble(window.event)\""+
											" autocomplete=\"off\""+
											" onchange=\"if(value=='') value=0; calculaLinea('"+arrayDetalle[i][0]+"', '"+arrayDetalle[i][20]+"');\">";
										
						cell = row.insertCell(13);
						cell.style.textAlign = "right";
						cell.setAttribute("class","totRepos");
						cell.setAttribute("id","totRepos_"+arrayDetalle[i][0]);
						cell.innerHTML = arrayDetalle[i][13];
						
						cell = row.insertCell(14);
						cell.style.textAlign = "right";
						cell.setAttribute("class","totArrie");
						cell.setAttribute("id","totArrie_"+arrayDetalle[i][0]);
						cell.innerHTML = arrayDetalle[i][14];
						
						cell = row.insertCell(15);
						cell.style.textAlign = "right";
						cell.setAttribute("class","totVenta");
						cell.setAttribute("id","totVenta_"+arrayDetalle[i][0]);
						cell.innerHTML = arrayDetalle[i][15];
						
						cell = row.insertCell(16);
						cell.style.textAlign = "right";
						cell.setAttribute("class","totKg");
						cell.setAttribute("id","totKg_"+arrayDetalle[i][0]);
						cell.innerHTML = arrayDetalle[i][18];
						
						cell = row.insertCell(17);
						cell.style.textAlign = "right";
						cell.setAttribute("class","totM2");
						cell.setAttribute("id","totM2_"+arrayDetalle[i][0]);
						cell.innerHTML = arrayDetalle[i][19];
						if(escondeLosM2 != null && escondeLosM2 == 1)"""),format.raw/*1693.52*/("""{"""),format.raw/*1693.53*/("""
							"""),format.raw/*1694.8*/("""cell.style.display = "none";
						"""),format.raw/*1695.7*/("""}"""),format.raw/*1695.8*/("""
						
						"""),format.raw/*1697.7*/("""cell = row.insertCell(18);
						cell.style.display = "none";
						cell.setAttribute("id","uniKg_"+arrayDetalle[i][0]);
						cell.innerHTML = arrayDetalle[i][16];
						
						cell = row.insertCell(19);
						cell.style.display = "none";
						cell.setAttribute("id","uniM2_"+arrayDetalle[i][0]);
						cell.innerHTML = arrayDetalle[i][17];
						
						cell = row.insertCell(20);
						cell.style.display = "none";
						cell.setAttribute("id","idEquipo_"+arrayDetalle[i][0]);
						cell.innerHTML = arrayDetalle[i][0];
						
						cell = row.insertCell(21);
						cell.style.display = "none";
						cell.setAttribute("id","nroDecimales_"+arrayDetalle[i][0]);
						cell.innerHTML = arrayDetalle[i][20];
					"""),format.raw/*1716.6*/("""}"""),format.raw/*1716.7*/("""
				"""),format.raw/*1717.5*/("""}"""),format.raw/*1717.6*/("""
			"""),format.raw/*1718.4*/("""}"""),format.raw/*1718.5*/("""
		"""),format.raw/*1719.3*/("""}"""),format.raw/*1719.4*/("""
		"""),format.raw/*1720.3*/("""$('#tablaPrincipal').DataTable("""),format.raw/*1720.34*/("""{"""),format.raw/*1720.35*/("""
	    	"""),format.raw/*1721.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[15, 50, 100, 200, -1], [15, 50, 100, 200, "All"]],
	    	"order": [[ 0, "asc" ],[ 2, "asc" ]],
	    	"language": """),format.raw/*1724.19*/("""{"""),format.raw/*1724.20*/("""
	        	"""),format.raw/*1725.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*1726.10*/("""}"""),format.raw/*1726.11*/("""
		"""),format.raw/*1727.3*/("""}"""),format.raw/*1727.4*/(""" """),format.raw/*1727.5*/(""");
	"""),format.raw/*1728.2*/("""}"""),format.raw/*1728.3*/("""
	
	
"""),format.raw/*1731.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,formCotiza:forms.FormCotiza,listClientes:List[tables.Cliente],listProyectos:List[tables.Proyecto],detalle:List[List[String]],numDecParaTot:String,listRegiones:List[tables.Regiones],listUnTiempo:List[tables.UnidadTiempo],sucursal:tables.Sucursal,comercial:tables.Comercial,listSucursal:List[tables.Sucursal],listComercial:List[tables.Comercial],importDesdeExcel:String,json:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,formCotiza,listClientes,listProyectos,detalle,numDecParaTot,listRegiones,listUnTiempo,sucursal,comercial,listSucursal,listComercial,importDesdeExcel,json)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,forms.FormCotiza,List[tables.Cliente],List[tables.Proyecto],List[List[String]],String,List[tables.Regiones],List[tables.UnidadTiempo],tables.Sucursal,tables.Comercial,List[tables.Sucursal],List[tables.Comercial],String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,formCotiza,listClientes,listProyectos,detalle,numDecParaTot,listRegiones,listUnTiempo,sucursal,comercial,listSucursal,listComercial,importDesdeExcel,json) => apply(mapDiccionario,mapPermiso,userMnu,formCotiza,listClientes,listProyectos,detalle,numDecParaTot,listRegiones,listUnTiempo,sucursal,comercial,listSucursal,listComercial,importDesdeExcel,json)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizarSelect/cotizaIngreso3.scala.html
                  HASH: cde4400fbb19ee35ac2b637e09541dd0c870e7f5
                  MATRIX: 1246->1|1843->505|1870->507|1886->515|1925->517|1954->520|2023->568|2051->569|2201->693|2246->717|2276->720|2334->752|2402->799|2432->802|2512->854|2541->855|2573->860|3446->1706|3474->1707|3504->1710|3610->1790|3679->1838|3709->1841|3791->1895|3820->1896|3852->1901|4611->2633|4639->2634|4669->2637|4906->2848|4989->2910|5020->2914|5406->3273|5425->3283|5461->3297|5815->3624|5834->3634|5866->3645|5994->3745|6023->3746|6058->3754|6077->3764|6109->3775|6139->3777|6168->3778|6200->3782|6229->3783|6279->3804|6309->3805|6750->4219|6769->4229|6800->4239|6852->4264|6871->4274|6902->4284|7161->4515|7185->4529|7218->4540|7248->4541|7385->4650|7405->4660|7438->4671|7537->4742|7578->4744|7646->4784|7686->4785|7728->4798|7850->4892|7870->4902|7903->4913|7966->4956|8006->4957|8048->4970|8170->5064|8190->5074|8223->5085|8394->5224|8430->5240|8470->5241|8511->5253|8631->5345|8651->5355|8684->5366|8851->5501|8890->5511|9199->5792|9240->5794|9309->5835|9349->5836|9392->5850|9518->5948|9538->5958|9572->5970|9656->6034|9696->6035|9739->6049|9865->6147|9885->6157|9919->6169|10105->6323|10142->6340|10182->6341|10224->6354|10350->6452|10370->6462|10404->6474|10589->6627|10628->6637|10944->6925|10964->6935|10998->6947|11097->7018|11138->7020|11179->7032|11304->7129|11324->7139|11359->7152|11418->7191|11458->7192|11499->7204|11624->7301|11644->7311|11679->7324|11839->7452|11878->7462|12385->7941|12403->7949|12433->7957|12697->8193|12737->8194|12822->8251|12862->8252|12904->8265|13128->8461|13146->8469|13176->8477|13207->8480|13225->8488|13259->8500|13311->8524|13354->8550|13394->8551|13438->8566|13482->8582|13497->8587|13527->8595|13558->8598|13573->8603|13607->8615|13663->8639|13705->8652|13751->8678|13791->8679|13833->8692|14028->8859|14046->8867|14076->8875|14107->8878|14125->8886|14159->8898|14211->8922|14254->8948|14294->8949|14338->8964|14382->8980|14397->8985|14427->8993|14458->8996|14473->9001|14507->9013|14563->9037|14605->9050|14659->9072|14695->9088|14735->9089|14776->9101|14889->9186|14907->9194|14941->9206|15008->9241|15047->9251|15465->9641|15485->9651|15521->9665|15788->9904|15807->9913|15837->9921|16108->10164|16148->10165|16190->10178|16360->10320|16379->10329|16409->10337|16440->10340|16459->10349|16498->10366|16550->10390|16594->10417|16634->10418|16678->10433|16722->10449|16737->10454|16767->10462|16798->10465|16813->10470|16852->10487|16908->10511|16950->10524|16996->10550|17036->10551|17078->10564|17193->10651|17212->10660|17251->10677|17320->10714|17360->10725|17563->10900|17583->10910|17623->10927|17720->10996|17761->10998|17800->11008|18009->11189|18033->11203|18069->11217|18215->11335|18235->11345|18270->11358|18381->11424|18417->11432|20213->13199|20243->13200|20287->13215|20317->13216|21461->14332|21485->14346|21524->14362|21901->14710|21931->14711|21975->14726|22005->14727|23520->16213|23550->16214|23594->16229|23624->16230|25470->18048|25494->18062|25527->18073|25582->18100|25606->18114|25639->18125|25704->18162|25728->18176|25761->18187|25882->18280|25906->18294|25944->18310|26152->18489|26194->18491|26233->18501|26305->18553|26345->18554|26384->18564|26441->18589|26478->18598|26734->18827|26773->18849|26813->18850|26850->18859|26934->18915|26950->18921|26980->18929|27019->18939|27167->19059|27183->19065|27212->19072|27301->19133|27317->19139|27346->19146|27439->19211|27455->19217|27484->19224|27530->19242|27546->19248|27575->19255|27615->19266|27744->19367|27760->19373|27789->19380|27824->19386|27841->19392|27871->19399|28036->19536|28052->19542|28081->19549|28267->19707|28283->19713|28312->19720|28443->19823|28459->19829|28488->19836|28519->19839|28535->19845|28565->19853|28740->20000|28756->20006|28785->20013|28834->20034|28850->20040|28880->20048|29183->20323|29199->20329|29228->20336|29261->20341|29277->20347|29307->20355|29458->20478|29474->20484|29503->20491|29542->20502|29558->20508|29589->20516|29661->20560|29701->20561|29742->20573|29807->20610|29823->20616|29852->20623|29905->20648|29921->20654|29951->20661|29999->20680|30016->20686|30046->20693|30080->20698|30097->20704|30128->20712|30169->20733|30209->20734|30250->20746|30315->20783|30331->20789|30360->20796|30413->20821|30429->20827|30459->20834|30507->20853|30524->20859|30554->20866|30588->20871|30605->20877|30636->20885|30693->20910|30732->20920|30807->20967|30823->20973|30852->20980|30987->21087|31003->21093|31032->21100|31063->21103|31079->21109|31109->21117|31284->21264|31300->21270|31329->21277|31378->21298|31394->21304|31423->21311|31557->21417|31573->21423|31603->21431|31760->21559|31790->21560|31827->21568|31857->21569|31887->21570|31921->21575|31951->21576|31995->21592|32011->21598|32040->21605|32073->21610|32089->21616|32120->21624|32168->21643|32185->21649|32215->21656|32249->21661|32266->21667|32297->21675|32330->21678|32361->21679|32500->21790|32516->21796|32545->21803|32609->21838|32626->21844|32656->21851|32791->21958|32807->21964|32836->21971|32908->22015|32924->22021|32953->22028|32984->22031|33000->22037|33029->22044|33079->22066|33122->22092|33162->22093|33204->22106|33248->22122|33263->22127|33288->22130|33319->22133|33334->22138|33363->22145|33417->22167|33457->22178|33579->22272|33595->22278|33624->22285|33655->22288|33671->22294|33701->22302|33848->22421|33864->22427|33893->22434|33942->22455|33958->22461|33988->22469|34281->22733|34311->22734|34355->22749|34385->22750|34415->22751|34449->22756|34479->22757|34523->22773|34539->22779|34568->22786|34601->22791|34618->22797|34649->22805|34697->22824|34714->22830|34744->22837|34778->22842|34795->22848|34826->22856|34859->22859|34890->22860|35035->22977|35051->22983|35080->22990|35111->22993|35127->22999|35157->23007|35340->23162|35356->23168|35385->23175|35434->23196|35450->23202|35479->23209|35613->23315|35629->23321|35659->23329|35816->23457|35846->23458|35883->23466|35913->23467|35943->23468|35977->23473|36007->23474|36051->23490|36067->23496|36096->23503|36129->23508|36145->23514|36176->23522|36224->23541|36241->23547|36271->23554|36305->23559|36322->23565|36353->23573|36386->23576|36417->23577|36563->23695|36579->23701|36608->23708|36639->23711|36655->23717|36685->23725|36847->23859|36863->23865|36892->23872|36941->23893|36957->23899|36987->23907|37113->24005|37129->24011|37158->24018|37385->24217|37401->24223|37430->24230|37463->24235|37479->24241|37509->24249|37657->24369|37673->24375|37702->24382|37733->24385|37749->24391|37780->24399|37884->24475|37900->24481|37929->24488|37960->24491|37976->24497|38007->24505|38111->24581|38127->24587|38156->24594|38187->24597|38203->24603|38234->24611|38332->24681|38348->24687|38377->24694|38408->24697|38424->24703|38454->24711|38605->24833|38647->24835|38687->24846|38784->24915|38800->24921|38829->24928|38860->24931|38877->24937|38908->24945|38948->24965|38988->24966|39028->24977|39113->25034|39129->25040|39158->25047|39189->25050|39205->25056|39235->25064|39283->25080|39342->25110|39408->25148|39424->25154|39453->25161|39484->25164|39500->25170|39530->25178|39611->25231|39627->25237|39656->25244|39687->25247|39703->25253|39733->25261|39817->25317|39833->25323|39862->25330|39893->25333|39909->25339|39938->25346|40026->25406|40042->25412|40071->25419|40102->25422|40118->25428|40148->25436|40219->25475|40254->25482|40816->26015|40858->26017|40897->26027|40963->26073|41003->26074|41042->26084|41093->26103|41130->26112|41592->26546|41612->26556|41647->26569|41677->26570|42161->27026|42181->27036|42213->27046|42243->27047|42694->27469|42736->27471|42775->27481|42841->27527|42881->27528|42920->27538|42971->27557|43008->27566|43825->28354|43867->28356|43906->28366|44019->28459|44059->28460|44098->28470|44207->28547|44253->28565|46608->30892|46632->30906|46665->30917|46794->31019|46838->31046|46878->31047|46915->31056|46978->31091|46994->31097|47024->31105|47078->31131|47094->31137|47125->31146|47183->31175|47200->31181|47237->31195|47332->31262|47348->31268|47379->31277|47453->31323|47469->31329|47505->31343|47579->31389|47595->31395|47629->31407|47690->31436|47725->31443|49017->32707|49041->32721|49077->32735|49160->32791|49205->32819|49245->32820|49282->32829|49346->32865|49362->32871|49392->32879|49450->32909|49466->32915|49503->32929|49598->32996|49614->33002|49650->33016|49724->33062|49740->33068|49774->33080|49848->33126|49864->33132|49898->33144|49959->33173|49994->33180|52178->35336|52196->35344|52226->35352|52445->35543|52465->35553|52505->35570|55067->38101|55099->38105|55191->38168|55221->38169|55252->38172|55316->38207|55346->38208|55381->38215|55553->38358|55583->38359|55623->38370|55729->38447|55759->38448|55792->38453|55821->38454|55850->38455|55921->38497|55951->38498|55986->38505|56158->38648|56188->38649|56228->38660|56334->38737|56364->38738|56397->38743|56426->38744|56455->38745|56541->38802|56571->38803|56607->38811|56795->38970|56825->38971|56866->38983|56973->39061|57003->39062|57034->39065|57063->39066|57092->39067|57202->39149|57231->39150|57299->39189|57329->39190|57360->39193|57475->39279|57505->39280|57547->39293|57815->39532|57845->39533|57878->39538|57926->39557|57956->39558|57990->39564|58041->39587|58061->39597|58094->39608|58170->39655|58200->39656|58230->39657|58260->39658|58295->39665|58324->39666|58360->39674|58389->39675|58426->39684|58456->39685|58488->39689|58517->39690|58549->39694|58620->39736|58650->39737|58681->39740|58798->39828|58828->39829|58870->39842|59133->40076|59163->40077|59196->40082|59341->40199|59370->40200|59407->40209|59437->40210|59469->40214|59498->40215|59532->40221|59586->40246|59616->40247|59647->40250|59850->40424|59880->40425|59912->40429|60012->40501|60041->40502|60126->40558|60156->40559|60188->40563|60290->40637|60319->40638|60397->40688|60432->40701|60517->40757|60547->40758|60579->40762|60679->40834|60708->40835|60784->40883|60819->40896|60904->40952|60934->40953|60966->40957|61066->41029|61095->41030|61171->41078|61206->41091|61287->41143|61317->41144|61349->41148|61448->41219|61477->41220|61556->41270|61586->41271|61618->41275|61717->41346|61746->41347|61999->41572|62034->41585|62136->41659|62171->41672|62270->41743|62305->41756|62481->41904|62510->41905|62542->41909|62604->41942|62634->41943|62665->41946|62756->42008|62786->42009|62818->42013|62883->42050|62912->42051|62944->42055|62973->42056|63005->42060|63070->42097|63099->42098|63129->42100|63158->42101|63190->42105|63266->42152|63296->42153|63327->42156|63367->42167|63397->42168|63429->42172|63770->42484|63800->42485|63846->42501|63877->42502|63910->42506|64283->42850|64313->42851|64346->42855|64376->42856|64409->42860|64750->43172|64780->43173|64814->43178|64844->43179|64877->43183|64950->43226|64981->43227|65013->43230|65674->43861|65705->43862|65739->43867|66007->44106|66037->44107|66070->44111|66100->44112|66134->44117|66347->44301|66377->44302|66409->44305|67114->44980|67145->44981|67178->44985|67224->45002|67254->45003|67288->45008|67318->45009|67351->45013|67435->45067|67466->45068|67498->45071|67984->45527|68015->45528|68048->45532|68172->45626|68203->45627|68237->45632|68533->45898|68564->45899|68599->45905|68670->45947|68700->45948|68733->45952|68763->45953|68796->45957|69300->46432|69330->46433|69363->46437|69393->46438|69426->46442|69487->46473|69518->46474|69555->46482|69744->46641|69775->46642|69817->46654|69925->46732|69956->46733|69989->46737|70019->46738|70049->46739|70164->46824|70195->46825|70226->46826|70257->46827|70291->46832|70321->46833|70352->46835|70382->46836|70415->46840|70477->46872|70508->46873|70540->46876|70663->46969|70694->46970|70727->46974|70792->47010|70822->47011|70854->47014|70915->47045|70946->47046|70983->47054|71172->47213|71203->47214|71245->47226|71353->47304|71384->47305|71416->47308|71446->47309|71476->47310|71970->47775|72000->47776|72033->47780|72142->47859|72173->47860|72205->47863|72442->48071|72472->48072|72505->48076|72538->48081|72577->48097|72616->48106|72647->48107|72679->48110|72840->48242|72870->48243|72907->48251|72985->48299|73016->48300|73048->48303|73465->48690|73496->48691|73529->48695|73653->48789|73684->48790|73718->48795|73809->48857|73839->48858|73873->48863|73903->48864|73969->48900|74000->48901|74033->48905|74165->49007|74196->49008|74230->49013|74321->49075|74351->49076|74385->49081|74415->49082|74473->49111|74498->49125|74537->49141|74581->49155|74612->49156|74645->49160|74802->49286|74834->49287|74868->49292|74959->49354|74989->49355|75023->49360|75053->49361|75130->49408|75161->49409|75194->49413|75339->49527|75371->49528|75405->49533|75496->49595|75526->49596|75560->49601|75590->49602|75623->49606|75653->49607|75686->49611|75810->49705|75841->49706|75875->49711|76171->49977|76202->49978|76237->49984|76300->50018|76330->50019|76363->50023|76393->50024|76426->50028|76471->50044|76501->50045|76533->50048|76577->50063|76607->50064|76639->50067|76831->50229|76862->50230|76894->50233|77004->50313|77035->50314|77068->50318|77142->50363|77172->50364|77204->50367|77343->50476|77374->50477|77407->50481|77467->50511|77498->50512|77529->50513|77586->50540|77617->50541|77649->50544|77679->50545|77711->50548|77758->50565|77789->50566|77822->50570|77968->50686|77999->50687|78033->50692|78238->50868|78268->50869|78301->50873|78332->50874|78366->50879|78479->50963|78509->50964|78541->50967|78571->50968|78604->50972|78634->50973|78667->50977|78885->51166|78915->51167|78946->51169|78976->51170|79009->51174|79118->51253|79149->51254|79181->51257|79436->51482|79467->51483|79500->51487|79555->51512|79586->51513|79617->51514|79674->51541|79705->51542|79737->51545|79767->51546|79799->51549|79846->51566|79877->51567|79910->51571|79978->51610|80008->51611|80041->51615|80071->51616|80104->51620|80347->51834|80377->51835|80408->51837|80438->51838|80470->51841|80532->51873|80563->51874|80595->51877|80706->51958|80737->51959|80771->51964|80839->52003|80869->52004|81100->52205|81131->52206|81164->52210|81501->52518|81531->52519|81563->52522|81758->52687|81789->52688|81825->52695|82011->52851|82042->52852|82083->52863|82190->52940|82221->52941|82253->52944|82283->52945|82316->52949|82346->52950|82379->52954|82443->52988|82474->52989|82506->52992|82772->53228|82803->53229|82836->53233|83228->53595|83259->53596|83293->53601|83420->53699|83450->53700|83483->53704|83550->53742|83580->53743|83612->53746|83807->53911|83838->53912|83874->53919|84060->54075|84091->54076|84132->54087|84239->54164|84270->54165|84302->54168|84332->54169|84365->54173|84395->54174|84428->54178|84497->54217|84528->54218|84560->54221|84846->54477|84877->54478|84910->54482|85351->54893|85382->54894|85416->54899|85530->54984|85560->54985|85593->54989|85660->55027|85690->55028|85722->55031|85917->55196|85948->55197|85984->55204|86170->55360|86201->55361|86242->55372|86349->55449|86380->55450|86412->55453|86442->55454|86475->55458|86505->55459|86538->55463|86613->55508|86644->55509|86676->55512|86798->55604|86829->55605|86872->55618|87139->55855|87170->55856|87204->55861|87349->55977|87379->55978|87417->55987|87448->55988|87482->55993|87512->55994|87545->55998|87616->56039|87647->56040|87679->56043|87866->56200|87897->56201|87941->56215|88204->56448|88235->56449|88274->56459|88463->56618|88494->56619|88530->56626|88712->56778|88743->56779|88780->56787|88842->56819|88873->56820|88911->56829|89095->56983|89126->56984|89166->56994|89268->57067|89299->57068|89337->57077|89805->57516|89835->57517|89871->57524|89901->57525|89943->57538|89973->57539|90009->57546|90090->57597|90121->57598|90161->57608|90356->57773|90387->57774|90431->57788|90541->57868|90572->57869|90607->57875|90637->57876|90737->57947|90767->57948|90807->57958|90838->57959|90873->57965|90903->57966|90936->57970|90999->58003|91030->58004|91062->58007|91184->58099|91215->58100|91258->58113|91534->58359|91565->58360|91599->58365|91744->58480|91775->58481|91815->58491|92025->58671|92056->58672|92100->58686|92210->58766|92241->58767|92275->58772|92305->58773|92335->58774|92420->58830|92450->58831|92488->58840|92519->58841|92552->58845|92582->58846|92615->58850|92671->58876|92702->58877|92734->58880|92820->58937|92850->58938|92887->58946|92936->58966|92963->58970|93137->59116|93181->59142|93222->59143|93254->59146|93297->59160|93313->59165|93339->59168|93388->59188|93404->59193|93434->59200|93517->59251|93550->59255|93600->59276|93621->59286|93674->59316|93741->59353|93772->59354|93804->59357|93943->59466|93974->59467|94008->59472|94090->59524|94121->59525|94156->59531|94225->59570|94256->59571|94292->59578|94340->59597|94370->59598|94404->59603|94434->59604|94479->59619|94510->59620|94546->59627|94608->59660|94638->59661|94670->59664|94700->59665|94732->59668|94793->59699|94824->59700|94862->59709|95054->59871|95085->59872|95128->59885|95237->59964|95268->59965|95302->59970|95332->59971|95362->59972|95770->60350|95801->60351|95834->60355|96546->61038|96576->61039|96608->61042|96794->61198|96825->61199|96862->61207|97038->61353|97069->61354|97111->61366|97219->61444|97250->61445|97284->61450|97314->61451|97344->61452|97429->61508|97459->61509|97492->61513|97595->61586|97626->61587|97658->61590|97703->61605|97734->61606|97767->61610|97894->61708|97924->61709|97957->61713|97987->61714|98020->61718|98197->61866|98227->61867|98258->61869|98288->61870|98321->61874|98396->61919|98427->61920|98462->61926|98523->61957|98554->61958|98591->61966|98934->62279|98965->62280|99003->62289|99064->62320|99095->62321|99136->62333|99198->62365|99229->62366|99272->62380|101487->64565|101518->64566|101558->64576|101787->64776|101818->64777|101852->64781|101883->64782|101923->64792|102160->65000|102191->65001|102227->65008|103118->65869|103149->65870|103187->65878|103218->65879|103249->65880|103284->65885|103315->65886|103480->66020|103512->66021|104064->66543|104095->66544|104136->66555|104259->66648|104290->66649|104331->66660|105056->67355|105087->67356|105132->67371|105163->67372|105194->67373|105229->67378|105260->67379|105425->67513|105457->67514|106227->68254|106258->68255|106296->68263|106327->68264|106358->68265|106393->68270|106424->68271|106589->68405|106621->68406|108674->70429|108705->70430|108742->70438|108806->70473|108836->70474|108879->70488|109621->71201|109651->71202|109685->71207|109715->71208|109748->71212|109778->71213|109810->71216|109840->71217|109872->71220|109933->71251|109964->71252|110000->71259|110186->71415|110217->71416|110258->71427|110365->71504|110396->71505|110428->71508|110458->71509|110488->71510|110521->71514|110551->71515|110585->71520
                  LINES: 28->1|38->7|39->8|39->8|39->8|41->10|41->10|42->11|43->12|43->12|45->14|46->15|46->15|47->16|48->17|48->17|49->18|65->34|65->34|66->35|70->39|70->39|71->40|72->41|72->41|73->42|88->57|88->57|89->58|95->64|95->64|96->65|102->71|102->71|102->71|110->79|110->79|110->79|112->81|112->81|112->81|112->81|112->81|112->81|112->81|112->81|112->81|112->81|112->81|123->92|123->92|123->92|124->93|124->93|124->93|131->100|131->100|131->100|131->100|133->102|133->102|133->102|134->103|134->103|135->104|135->104|136->105|138->107|138->107|138->107|140->109|140->109|141->110|143->112|143->112|143->112|147->116|148->117|148->117|149->118|151->120|151->120|151->120|155->124|156->125|163->132|163->132|164->133|164->133|165->134|167->136|167->136|167->136|170->139|170->139|171->140|173->142|173->142|173->142|178->147|179->148|179->148|180->149|182->151|182->151|182->151|187->156|188->157|195->164|195->164|195->164|196->165|196->165|197->166|199->168|199->168|199->168|201->170|201->170|202->171|204->173|204->173|204->173|208->177|209->178|223->192|223->192|223->192|228->197|228->197|229->198|229->198|230->199|231->200|231->200|231->200|231->200|231->200|231->200|232->201|232->201|232->201|233->202|233->202|233->202|233->202|233->202|233->202|233->202|234->203|235->204|236->205|236->205|237->206|238->207|238->207|238->207|238->207|238->207|238->207|239->208|239->208|239->208|240->209|240->209|240->209|240->209|240->209|240->209|240->209|241->210|242->211|243->212|244->213|244->213|245->214|246->215|246->215|246->215|248->217|249->218|261->230|261->230|261->230|270->239|270->239|270->239|275->244|275->244|276->245|277->246|277->246|277->246|277->246|277->246|277->246|278->247|278->247|278->247|279->248|279->248|279->248|279->248|279->248|279->248|279->248|280->249|281->250|282->251|282->251|283->252|284->253|284->253|284->253|286->255|287->256|295->264|295->264|295->264|296->265|296->265|297->266|300->269|300->269|300->269|303->272|303->272|303->272|307->276|308->277|346->315|346->315|346->315|346->315|371->340|371->340|371->340|379->348|379->348|379->348|379->348|412->381|412->381|412->381|412->381|452->421|452->421|452->421|453->422|453->422|453->422|454->423|454->423|454->423|457->426|457->426|457->426|460->429|460->429|461->430|462->431|462->431|463->432|464->433|465->434|472->441|472->441|472->441|473->442|475->444|475->444|475->444|476->445|478->447|478->447|478->447|479->448|479->448|479->448|481->450|481->450|481->450|482->451|482->451|482->451|483->452|485->454|485->454|485->454|485->454|485->454|485->454|488->457|488->457|488->457|493->462|493->462|493->462|495->464|495->464|495->464|495->464|495->464|495->464|498->467|498->467|498->467|499->468|499->468|499->468|504->473|504->473|504->473|504->473|504->473|504->473|507->476|507->476|507->476|507->476|507->476|507->476|508->477|508->477|509->478|509->478|509->478|509->478|509->478|509->478|509->478|509->478|509->478|509->478|509->478|509->478|509->478|510->479|510->479|511->480|511->480|511->480|511->480|511->480|511->480|511->480|511->480|511->480|511->480|511->480|511->480|511->480|512->481|513->482|514->483|514->483|514->483|516->485|516->485|516->485|516->485|516->485|516->485|519->488|519->488|519->488|520->489|520->489|520->489|522->491|522->491|522->491|525->494|525->494|525->494|525->494|525->494|525->494|525->494|525->494|525->494|525->494|525->494|525->494|525->494|525->494|525->494|525->494|525->494|525->494|525->494|525->494|525->494|528->497|528->497|528->497|528->497|528->497|528->497|530->499|530->499|530->499|531->500|531->500|531->500|531->500|531->500|531->500|532->501|532->501|532->501|533->502|533->502|533->502|533->502|533->502|533->502|533->502|534->503|535->504|538->507|538->507|538->507|538->507|538->507|538->507|540->509|540->509|540->509|541->510|541->510|541->510|546->515|546->515|546->515|546->515|546->515|546->515|546->515|546->515|546->515|546->515|546->515|546->515|546->515|546->515|546->515|546->515|546->515|546->515|546->515|546->515|546->515|549->518|549->518|549->518|549->518|549->518|549->518|552->521|552->521|552->521|553->522|553->522|553->522|555->524|555->524|555->524|558->527|558->527|558->527|558->527|558->527|558->527|558->527|558->527|558->527|558->527|558->527|558->527|558->527|558->527|558->527|558->527|558->527|558->527|558->527|558->527|558->527|561->530|561->530|561->530|561->530|561->530|561->530|564->533|564->533|564->533|565->534|565->534|565->534|567->536|567->536|567->536|570->539|570->539|570->539|570->539|570->539|570->539|575->544|575->544|575->544|575->544|575->544|575->544|576->545|576->545|576->545|576->545|576->545|576->545|577->546|577->546|577->546|577->546|577->546|577->546|578->547|578->547|578->547|578->547|578->547|578->547|579->548|579->548|580->549|580->549|580->549|580->549|580->549|580->549|580->549|581->550|581->550|582->551|582->551|582->551|582->551|582->551|582->551|582->551|583->552|586->555|586->555|586->555|586->555|586->555|586->555|586->555|587->556|587->556|587->556|587->556|587->556|587->556|588->557|588->557|588->557|588->557|588->557|588->557|589->558|589->558|589->558|589->558|589->558|589->558|592->561|593->562|601->570|601->570|602->571|603->572|603->572|604->573|605->574|606->575|617->586|617->586|617->586|617->586|628->597|628->597|628->597|628->597|637->606|637->606|638->607|639->608|639->608|640->609|641->610|642->611|654->623|654->623|655->624|656->625|656->625|657->626|658->627|660->629|711->680|711->680|711->680|717->686|717->686|717->686|718->687|718->687|718->687|718->687|718->687|718->687|718->687|718->687|718->687|718->687|719->688|719->688|719->688|720->689|720->689|720->689|721->690|721->690|721->690|723->692|724->693|756->725|756->725|756->725|760->729|760->729|760->729|761->730|761->730|761->730|761->730|761->730|761->730|761->730|762->731|762->731|762->731|763->732|763->732|763->732|764->733|764->733|764->733|766->735|767->736|818->787|818->787|818->787|821->790|821->790|821->790|885->854|889->858|891->860|891->860|892->861|892->861|892->861|893->862|896->865|896->865|897->866|898->867|898->867|899->868|899->868|899->868|901->870|901->870|902->871|905->874|905->874|906->875|907->876|907->876|908->877|908->877|908->877|912->881|912->881|913->882|916->885|916->885|917->886|918->887|918->887|919->888|919->888|919->888|924->893|924->893|926->895|926->895|927->896|929->898|929->898|930->899|937->906|937->906|938->907|938->907|938->907|939->908|939->908|939->908|939->908|940->909|940->909|940->909|940->909|941->910|941->910|942->911|942->911|943->912|943->912|944->913|944->913|946->915|946->915|946->915|947->916|949->918|949->918|950->919|957->926|957->926|958->927|960->929|960->929|961->930|961->930|962->931|962->931|965->934|965->934|965->934|966->935|971->940|971->940|972->941|974->943|974->943|977->946|977->946|978->947|980->949|980->949|980->949|980->949|983->952|983->952|984->953|986->955|986->955|986->955|986->955|989->958|989->958|990->959|992->961|992->961|992->961|992->961|995->964|995->964|996->965|998->967|998->967|1001->970|1001->970|1002->971|1004->973|1004->973|1009->978|1009->978|1010->979|1010->979|1011->980|1011->980|1016->985|1016->985|1018->987|1018->987|1018->987|1019->988|1020->989|1020->989|1021->990|1022->991|1022->991|1022->991|1022->991|1023->992|1024->993|1024->993|1025->994|1025->994|1027->996|1027->996|1027->996|1028->997|1028->997|1028->997|1029->998|1034->1003|1034->1003|1034->1003|1034->1003|1035->1004|1040->1009|1040->1009|1040->1009|1040->1009|1041->1010|1046->1015|1046->1015|1048->1017|1048->1017|1050->1019|1050->1019|1050->1019|1051->1020|1064->1033|1064->1033|1065->1034|1070->1039|1070->1039|1070->1039|1070->1039|1071->1040|1076->1045|1076->1045|1077->1046|1093->1062|1093->1062|1094->1063|1095->1064|1095->1064|1097->1066|1097->1066|1099->1068|1100->1069|1100->1069|1101->1070|1108->1077|1108->1077|1109->1078|1110->1079|1110->1079|1111->1080|1114->1083|1114->1083|1115->1084|1116->1085|1116->1085|1117->1086|1117->1086|1118->1087|1131->1100|1131->1100|1131->1100|1131->1100|1132->1101|1132->1101|1132->1101|1133->1102|1136->1105|1136->1105|1137->1106|1138->1107|1138->1107|1139->1108|1139->1108|1139->1108|1140->1109|1140->1109|1140->1109|1140->1109|1141->1110|1141->1110|1142->1111|1142->1111|1144->1113|1144->1113|1144->1113|1145->1114|1146->1115|1146->1115|1147->1116|1148->1117|1148->1117|1149->1118|1149->1118|1149->1118|1150->1119|1153->1122|1153->1122|1154->1123|1155->1124|1155->1124|1156->1125|1156->1125|1156->1125|1166->1135|1166->1135|1168->1137|1169->1138|1169->1138|1170->1139|1174->1143|1174->1143|1176->1145|1176->1145|1176->1145|1176->1145|1176->1145|1177->1146|1179->1148|1179->1148|1183->1152|1184->1153|1184->1153|1185->1154|1194->1163|1194->1163|1195->1164|1196->1165|1196->1165|1197->1166|1199->1168|1199->1168|1200->1169|1200->1169|1200->1169|1200->1169|1201->1170|1202->1171|1202->1171|1203->1172|1205->1174|1205->1174|1206->1175|1206->1175|1206->1175|1206->1175|1206->1175|1206->1175|1206->1175|1207->1176|1208->1177|1208->1177|1209->1178|1211->1180|1211->1180|1212->1181|1212->1181|1212->1181|1212->1181|1213->1182|1214->1183|1214->1183|1215->1184|1217->1186|1217->1186|1218->1187|1218->1187|1218->1187|1218->1187|1219->1188|1220->1189|1220->1189|1221->1190|1224->1193|1224->1193|1225->1194|1227->1196|1227->1196|1228->1197|1228->1197|1229->1198|1230->1199|1230->1199|1231->1200|1232->1201|1232->1201|1234->1203|1235->1204|1235->1204|1236->1205|1238->1207|1238->1207|1239->1208|1240->1209|1240->1209|1241->1210|1242->1211|1242->1211|1243->1212|1243->1212|1243->1212|1243->1212|1243->1212|1243->1212|1244->1213|1244->1213|1245->1214|1245->1214|1245->1214|1246->1215|1248->1217|1248->1217|1249->1218|1252->1221|1252->1221|1252->1221|1252->1221|1253->1222|1255->1224|1255->1224|1256->1225|1256->1225|1256->1225|1256->1225|1257->1226|1261->1230|1261->1230|1262->1231|1262->1231|1264->1233|1265->1234|1265->1234|1266->1235|1271->1240|1271->1240|1272->1241|1272->1241|1272->1241|1272->1241|1272->1241|1272->1241|1273->1242|1273->1242|1274->1243|1274->1243|1274->1243|1275->1244|1276->1245|1276->1245|1276->1245|1276->1245|1277->1246|1281->1250|1281->1250|1282->1251|1282->1251|1284->1253|1284->1253|1284->1253|1285->1254|1286->1255|1286->1255|1287->1256|1288->1257|1288->1257|1292->1261|1292->1261|1293->1262|1298->1267|1298->1267|1299->1268|1302->1271|1302->1271|1303->1272|1306->1275|1306->1275|1307->1276|1308->1277|1308->1277|1309->1278|1309->1278|1310->1279|1310->1279|1312->1281|1312->1281|1312->1281|1313->1282|1317->1286|1317->1286|1318->1287|1323->1292|1323->1292|1324->1293|1325->1294|1325->1294|1326->1295|1327->1296|1327->1296|1328->1297|1331->1300|1331->1300|1332->1301|1335->1304|1335->1304|1336->1305|1337->1306|1337->1306|1338->1307|1338->1307|1339->1308|1339->1308|1341->1310|1341->1310|1341->1310|1342->1311|1346->1315|1346->1315|1347->1316|1353->1322|1353->1322|1354->1323|1355->1324|1355->1324|1356->1325|1357->1326|1357->1326|1358->1327|1361->1330|1361->1330|1362->1331|1365->1334|1365->1334|1366->1335|1367->1336|1367->1336|1368->1337|1368->1337|1369->1338|1369->1338|1371->1340|1371->1340|1371->1340|1372->1341|1374->1343|1374->1343|1375->1344|1382->1351|1382->1351|1383->1352|1385->1354|1385->1354|1386->1355|1386->1355|1387->1356|1387->1356|1389->1358|1389->1358|1389->1358|1390->1359|1393->1362|1393->1362|1394->1363|1401->1370|1401->1370|1403->1372|1407->1376|1407->1376|1408->1377|1411->1380|1411->1380|1412->1381|1412->1381|1412->1381|1413->1382|1416->1385|1416->1385|1417->1386|1418->1387|1418->1387|1419->1388|1425->1394|1425->1394|1426->1395|1426->1395|1428->1397|1428->1397|1430->1399|1431->1400|1431->1400|1432->1401|1435->1404|1435->1404|1436->1405|1437->1406|1437->1406|1438->1407|1438->1407|1440->1409|1440->1409|1441->1410|1441->1410|1443->1412|1443->1412|1445->1414|1445->1414|1445->1414|1446->1415|1448->1417|1448->1417|1449->1418|1456->1425|1456->1425|1457->1426|1458->1427|1458->1427|1459->1428|1462->1431|1462->1431|1463->1432|1464->1433|1464->1433|1465->1434|1465->1434|1465->1434|1467->1436|1467->1436|1468->1437|1468->1437|1469->1438|1469->1438|1471->1440|1471->1440|1471->1440|1472->1441|1474->1443|1474->1443|1478->1447|1478->1447|1478->1447|1484->1453|1484->1453|1484->1453|1485->1454|1485->1454|1485->1454|1485->1454|1486->1455|1486->1455|1486->1455|1489->1458|1491->1460|1491->1460|1491->1460|1491->1460|1493->1462|1493->1462|1494->1463|1496->1465|1496->1465|1497->1466|1498->1467|1498->1467|1499->1468|1499->1468|1499->1468|1500->1469|1501->1470|1501->1470|1502->1471|1502->1471|1503->1472|1503->1472|1504->1473|1505->1474|1505->1474|1506->1475|1506->1475|1507->1476|1507->1476|1507->1476|1508->1477|1511->1480|1511->1480|1512->1481|1513->1482|1513->1482|1514->1483|1514->1483|1514->1483|1521->1490|1521->1490|1522->1491|1532->1501|1532->1501|1533->1502|1538->1507|1538->1507|1539->1508|1542->1511|1542->1511|1543->1512|1544->1513|1544->1513|1545->1514|1545->1514|1545->1514|1550->1519|1550->1519|1552->1521|1553->1522|1553->1522|1554->1523|1554->1523|1554->1523|1555->1524|1557->1526|1557->1526|1557->1526|1557->1526|1558->1527|1561->1530|1561->1530|1562->1531|1562->1531|1564->1533|1564->1533|1564->1533|1566->1535|1566->1535|1566->1535|1568->1537|1575->1544|1575->1544|1577->1546|1577->1546|1577->1546|1579->1548|1579->1548|1579->1548|1581->1550|1622->1591|1622->1591|1623->1592|1624->1593|1624->1593|1624->1593|1624->1593|1625->1594|1626->1595|1626->1595|1627->1596|1644->1613|1644->1613|1644->1613|1644->1613|1644->1613|1644->1613|1644->1613|1644->1613|1644->1613|1652->1621|1652->1621|1653->1622|1654->1623|1654->1623|1655->1624|1667->1636|1667->1636|1667->1636|1667->1636|1667->1636|1667->1636|1667->1636|1667->1636|1667->1636|1680->1649|1680->1649|1680->1649|1680->1649|1680->1649|1680->1649|1680->1649|1680->1649|1680->1649|1724->1693|1724->1693|1725->1694|1726->1695|1726->1695|1728->1697|1747->1716|1747->1716|1748->1717|1748->1717|1749->1718|1749->1718|1750->1719|1750->1719|1751->1720|1751->1720|1751->1720|1752->1721|1755->1724|1755->1724|1756->1725|1757->1726|1757->1726|1758->1727|1758->1727|1758->1727|1759->1728|1759->1728|1762->1731
                  -- GENERATED --
              */
          