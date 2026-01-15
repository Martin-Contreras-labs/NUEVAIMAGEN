
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

object cotizaModificaSel extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template15[Map[String,String],Map[String,String],utilities.UserMnu,forms.FormCotiza,List[tables.Cliente],List[tables.Proyecto],List[List[String]],String,List[tables.Regiones],List[tables.UnidadTiempo],tables.Sucursal,tables.Comercial,List[tables.Sucursal],List[tables.Comercial],String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
formCotiza: forms.FormCotiza, listClientes: List[tables.Cliente], listProyectos: List[tables.Proyecto], lista: List[List[String]], numDecParaTot: String, listRegiones: List[tables.Regiones],
listUnTiempo: List[tables.UnidadTiempo],
sucursal: tables.Sucursal, comercial: tables.Comercial, listSucursal: List[tables.Sucursal], listComercial: List[tables.Comercial],
json:String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*6.1*/("""

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
	
	<form action="/cotizarUpdate/" enctype="multipart/form-data" method="POST" onsubmit="return validarForm();">
		<div id="mostrarmostrar" style="display:none;">
			"""),_display_(/*63.5*/barraTitulo(mapDiccionario, "MODIFICAR COTIZACION", "")),format.raw/*63.60*/("""
			"""),format.raw/*64.4*/("""<div class="row  justify-content-md-center">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<table class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<tr>
								<td width="230px">
									<input type="hidden" id="id_cotizacion" name="id_cotizacion" value=""""),_display_(/*70.79*/formCotiza/*70.89*/.id_cotizacion),format.raw/*70.103*/("""">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Nro.Cotizacion</span>
								  		</div>
								  		<input type="text" class="form-control left"
								  				name="numeroCoti"
												value = """"),_display_(/*77.23*/formCotiza/*77.33*/.numeroCoti),format.raw/*77.44*/(""""
												style="width:100px;" 
												onkeydown="return ingresoInt(window.event)"
												onchange="if(value.trim()=='')"""),format.raw/*80.43*/("""{"""),format.raw/*80.44*/("""value='"""),_display_(/*80.52*/formCotiza/*80.62*/.numeroCoti),format.raw/*80.73*/("""';"""),format.raw/*80.75*/("""}"""),format.raw/*80.76*/("""else"""),format.raw/*80.80*/("""{"""),format.raw/*80.81*/("""validarNumero(value);"""),format.raw/*80.102*/("""}"""),format.raw/*80.103*/("""">
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
								  			onblur="if(!limitaFecha(value,720,10)) value='"""),_display_(/*91.61*/formCotiza/*91.71*/.fechaCoti),format.raw/*91.81*/("""';"
								  			value=""""),_display_(/*92.22*/formCotiza/*92.32*/.fechaCoti),format.raw/*92.42*/(""""
						        			required>
									</div>
								</td>
								<td width="230px">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">"""),_display_(/*99.65*/mapDiccionario/*99.79*/.get("RUT")),format.raw/*99.90*/(""" """),format.raw/*99.91*/("""Cliente</span>
								  		</div>
								  		<input type="hidden" id="id_cliente" name="id_cliente" value=""""),_display_(/*101.76*/formCotiza/*101.86*/.id_cliente),format.raw/*101.97*/("""">
										"""),_display_(if(mapPermiso.get("parametro.cotizaPorTasa").equals("1"))/*102.69*/ {_display_(Seq[Any](format.raw/*102.71*/("""
											"""),_display_(if(formCotiza.id_cliente>0)/*103.40*/{_display_(Seq[Any](format.raw/*103.41*/("""
												"""),format.raw/*104.13*/("""<input type="text" class="form-control"
			  										id="rutCliente"
			  										value=""""),_display_(/*106.24*/formCotiza/*106.34*/.rutCliente),format.raw/*106.45*/(""""
			  										readonly>
											""")))}else/*108.17*/{_display_(Seq[Any](format.raw/*108.18*/("""
												"""),format.raw/*109.13*/("""<input type="text" class="form-control"
			  										id="rutCliente"
			  										value=""""),_display_(/*111.24*/formCotiza/*111.34*/.rutCliente),format.raw/*111.45*/("""" 
			  										style="background:white"
			  										onclick="$('#listaCliente').modal('show');"
			  										readonly>
											""")))}),format.raw/*115.13*/("""
										""")))}else/*116.16*/{_display_(Seq[Any](format.raw/*116.17*/("""
											"""),format.raw/*117.12*/("""<input type="text" class="form-control"
		  										id="rutCliente"
		  										value=""""),_display_(/*119.23*/formCotiza/*119.33*/.rutCliente),format.raw/*119.44*/("""" 
		  										style="background:white"
		  										onclick="$('#listaCliente').modal('show');"
		  										readonly>
										""")))}),format.raw/*123.12*/("""
									"""),format.raw/*124.10*/("""</div>
								</td>
								<td>
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Cliente</span>
								  		</div>
											"""),_display_(if(mapPermiso.get("parametro.cotizaPorTasa").equals("1"))/*131.70*/ {_display_(Seq[Any](format.raw/*131.72*/("""
												"""),_display_(if(formCotiza.id_cliente>0)/*132.41*/{_display_(Seq[Any](format.raw/*132.42*/("""
													"""),format.raw/*133.14*/("""<input type="text" class="form-control left"
													id="nombreCliente"
													value=""""),_display_(/*135.22*/formCotiza/*135.32*/.nickCliente),format.raw/*135.44*/(""""
													required
													readonly>
												""")))}else/*138.18*/{_display_(Seq[Any](format.raw/*138.19*/("""
													"""),format.raw/*139.14*/("""<input type="text" class="form-control left"
													id="nombreCliente"
													value=""""),_display_(/*141.22*/formCotiza/*141.32*/.nickCliente),format.raw/*141.44*/(""""
													style="background:white"
													onclick='$("#listaCliente").modal("show")'
													required
													readonly>
												""")))}),format.raw/*146.14*/("""
											""")))}else/*147.17*/{_display_(Seq[Any](format.raw/*147.18*/("""
												"""),format.raw/*148.13*/("""<input type="text" class="form-control left"
													id="nombreCliente"
													value=""""),_display_(/*150.22*/formCotiza/*150.32*/.nickCliente),format.raw/*150.44*/(""""
													style="background:white"
													onclick='$("#listaCliente").modal("show")'
													required
													readonly>
											""")))}),format.raw/*155.13*/("""
									"""),format.raw/*156.10*/("""</div>
								</td>
								<td>
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Proyecto</span>
								  		</div>
										<input type="hidden" id="id_proyecto" name="id_proyecto" value=""""),_display_(/*163.76*/formCotiza/*163.86*/.id_proyecto),format.raw/*163.98*/("""">
										"""),_display_(if(mapPermiso.get("parametro.cotizaPorTasa").equals("1"))/*164.69*/ {_display_(Seq[Any](format.raw/*164.71*/("""
											"""),format.raw/*165.12*/("""<input type="text" class="form-control left"
												id="nombreProyecto"
												value=""""),_display_(/*167.21*/formCotiza/*167.31*/.nickProyecto),format.raw/*167.44*/(""""
												readonly>
										""")))}else/*169.16*/{_display_(Seq[Any](format.raw/*169.17*/("""
											"""),format.raw/*170.12*/("""<input type="text" class="form-control left"
												id="nombreProyecto"
												value=""""),_display_(/*172.21*/formCotiza/*172.31*/.nickProyecto),format.raw/*172.44*/(""""
												style="background:white"
												onclick='$("#listaProyecto").modal("show")'
												readonly>
										""")))}),format.raw/*176.12*/("""
									"""),format.raw/*177.10*/("""</div>
								</td>
								<td style="text-align:center;" width="70px">
									<span class="btn btn-info btn-sm btn-file" style="font-size: 8px;">
										"""),_display_(if(formCotiza.cotizacionPDF.equals("0"))/*181.52*/{_display_(Seq[Any](format.raw/*181.53*/("""
											"""),format.raw/*182.12*/("""<div id="txtBtn">Adjuntar</div>
										""")))}else/*183.16*/{_display_(Seq[Any](format.raw/*183.17*/("""
											"""),format.raw/*184.12*/("""<div id="txtBtn">Cambiar</div>
										""")))}),format.raw/*185.12*/("""
				    					"""),format.raw/*186.14*/("""<input type="file" id="docAdjunto" name="docAdjunto" onchange="LimitAttach(this.form, this.form.docAdjunto.value);">
									</span>
								</td>
								<td style="text-align:left;">
									"""),_display_(if(formCotiza.cotizacionPDF.equals("0"))/*190.51*/{_display_(Seq[Any](format.raw/*190.52*/("""
										"""),format.raw/*191.11*/("""--
									""")))}else/*192.15*/{_display_(Seq[Any](format.raw/*192.16*/("""
										"""),format.raw/*193.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*193.52*/formCotiza/*193.62*/.cotizacionPDF),format.raw/*193.76*/("""')">
											<kbd style="background-color: #7F8C8D">doc</kbd>
										</a>
									""")))}),format.raw/*196.11*/("""
								"""),format.raw/*197.9*/("""</td>
								
								
							</tr>
							<tr>
							
							
								
								<td align="center"  colspan="2">
									<input type="hidden" id="id_sucursal" name="id_sucursal" value=""""),_display_(/*206.75*/sucursal/*206.83*/.getId()),format.raw/*206.91*/("""">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Sucursal</span>
								  		</div>
										"""),_display_(if(mapPermiso.get("cambiarSucursal")!=null)/*211.55*/{_display_(Seq[Any](format.raw/*211.56*/("""
											"""),_display_(if(mapPermiso.get("cambiarComercial")!=null)/*212.57*/{_display_(Seq[Any](format.raw/*212.58*/("""
												"""),format.raw/*213.13*/("""<select id="selSucursal" class="custom-select"  style="width: 80px;" onchange = "$('#id_sucursal').val(value); actualizaComerciales(value); actualizaPrecios(value);">
													<option value=""""),_display_(/*214.30*/sucursal/*214.38*/.getId()),format.raw/*214.46*/("""">"""),_display_(/*214.49*/sucursal/*214.57*/.getNombre()),format.raw/*214.69*/("""</option>
													"""),_display_(/*215.15*/for(lista <- listSucursal) yield /*215.41*/{_display_(Seq[Any](format.raw/*215.42*/("""
														"""),format.raw/*216.15*/("""<option value=""""),_display_(/*216.31*/lista/*216.36*/.getId()),format.raw/*216.44*/("""">"""),_display_(/*216.47*/lista/*216.52*/.getNombre()),format.raw/*216.64*/("""</option>
													""")))}),format.raw/*217.15*/("""
												"""),format.raw/*218.13*/("""</select>
											""")))}else/*219.17*/{_display_(Seq[Any](format.raw/*219.18*/("""
												"""),format.raw/*220.13*/("""<select id="selSucursal" class="custom-select"  style="width: 80px;" onchange = "$('#id_sucursal').val(value); actualizaPrecios(value);">
													<option value=""""),_display_(/*221.30*/sucursal/*221.38*/.getId()),format.raw/*221.46*/("""">"""),_display_(/*221.49*/sucursal/*221.57*/.getNombre()),format.raw/*221.69*/("""</option>
													"""),_display_(/*222.15*/for(lista <- listSucursal) yield /*222.41*/{_display_(Seq[Any](format.raw/*222.42*/("""
														"""),format.raw/*223.15*/("""<option value=""""),_display_(/*223.31*/lista/*223.36*/.getId()),format.raw/*223.44*/("""">"""),_display_(/*223.47*/lista/*223.52*/.getNombre()),format.raw/*223.64*/("""</option>
													""")))}),format.raw/*224.15*/("""
												"""),format.raw/*225.13*/("""</select>
											""")))}),format.raw/*226.13*/("""
										""")))}else/*227.16*/{_display_(Seq[Any](format.raw/*227.17*/("""
											"""),format.raw/*228.12*/("""<input id="selSucursal" type="text" class="form-control left"
												value = """"),_display_(/*229.23*/sucursal/*229.31*/.getNombre()),format.raw/*229.43*/(""""
												readonly>
										""")))}),format.raw/*231.12*/("""
									"""),format.raw/*232.10*/("""</div>
								</td>
								
								
								
								<td colspan="20" rowspan="3">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Observaciones</span>
								  		</div>
	  									<textarea class="form-control" rows="3"
	  										name="observaciones" 
	  										autocomplete="off">"""),_display_(/*244.34*/formCotiza/*244.44*/.observaciones),format.raw/*244.58*/("""</textarea>
									</div>
								</td>
							</tr>
							<tr>
							
							
								<td align="center"  colspan="2">
									<div id="actualComerciales">
										<input type="hidden" id="id_comercial" name="id_comercial" value=""""),_display_(/*253.78*/comercial/*253.87*/.getId()),format.raw/*253.95*/("""">
										<div class="input-group">
									  		<div class="input-group-prepend">
									    		<span class="input-group-text" id="basic-addon1">Comercial</span>
									  		</div>
											"""),_display_(if(mapPermiso.get("cambiarComercial")!=null)/*258.57*/{_display_(Seq[Any](format.raw/*258.58*/("""
												"""),format.raw/*259.13*/("""<select id="selComercial" class="custom-select"  style="width: 80px;" onchange="$('#id_comercial').val(value)">
													<option value=""""),_display_(/*260.30*/comercial/*260.39*/.getId()),format.raw/*260.47*/("""">"""),_display_(/*260.50*/comercial/*260.59*/.getNameUsuario()),format.raw/*260.76*/("""</option>
													"""),_display_(/*261.15*/for(lista <- listComercial) yield /*261.42*/{_display_(Seq[Any](format.raw/*261.43*/("""
														"""),format.raw/*262.15*/("""<option value=""""),_display_(/*262.31*/lista/*262.36*/.getId()),format.raw/*262.44*/("""">"""),_display_(/*262.47*/lista/*262.52*/.getNameUsuario()),format.raw/*262.69*/("""</option>
													""")))}),format.raw/*263.15*/("""
												"""),format.raw/*264.13*/("""</select>
											""")))}else/*265.17*/{_display_(Seq[Any](format.raw/*265.18*/("""
												"""),format.raw/*266.13*/("""<input id="selComercial" type="text" class="form-control left"
													value = """"),_display_(/*267.24*/comercial/*267.33*/.getNameUsuario()),format.raw/*267.50*/(""""
													readonly>
											""")))}),format.raw/*269.13*/("""
										"""),format.raw/*270.11*/("""</div>
									</div>
								</td>
									
									
									
							</tr>
							<tr>
								<input type="hidden" id="id_bodegaEmpresa" name="id_bodegaEmpresa" value=""""),_display_(/*278.84*/formCotiza/*278.94*/.id_bodegaEmpresa),format.raw/*278.111*/("""">
								"""),_display_(if(mapPermiso.get("parametro.cotizaPorTasa").equals("1"))/*279.67*/ {_display_(Seq[Any](format.raw/*279.69*/("""
									"""),format.raw/*280.10*/("""<td align="center"  colspan="2">
										<div class="input-group">
									  		<div class="input-group-prepend">
									    		<span class="input-group-text" id="basic-addon1">"""),_display_(/*283.66*/mapDiccionario/*283.80*/.get("BODEGA")),format.raw/*283.94*/("""/PROYECTO</span>
									  		</div>
									  		<input type="text" class="form-control left"
													value = """"),_display_(/*286.24*/formCotiza/*286.34*/.nombreBodega),format.raw/*286.47*/(""""
													readonly>
										</div>
									</td>
								""")))} else {null} ),format.raw/*290.10*/("""
							"""),format.raw/*291.8*/("""</tr>
						</thead>
					</table>
					<hr>
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
											onchange="value = formatPorcentaje(value); if(value=='') """),format.raw/*314.69*/("""{"""),format.raw/*314.70*/("""value='0.00 %';"""),format.raw/*314.85*/("""}"""),format.raw/*314.86*/("""">
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
								    		<span class="input-group-text" id="basic-addon1">FACTOR SOBRE """),_display_(/*339.78*/mapDiccionario/*339.92*/.get("ARRIENDO")),format.raw/*339.108*/("""</span>
								  		</div>
								  		<input type="text" class="form-control center"
											id="factorGlobal"
											value="0.0000"
											onfocus="value = value.replace(/,/g,'')"
											onkeydown="return ingresoDouble(window.event)"
											autocomplete="off"
											onchange="value = formatStandar(value,4); if(value=='') """),format.raw/*347.68*/("""{"""),format.raw/*347.69*/("""value='0.0000';"""),format.raw/*347.84*/("""}"""),format.raw/*347.85*/("""">
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
											onchange="value = formatStandar(value,4); if(value=='') """),format.raw/*380.68*/("""{"""),format.raw/*380.69*/("""value='0.0000';"""),format.raw/*380.84*/("""}"""),format.raw/*380.85*/("""">
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
								<TH>UN<br>"""),_display_(/*419.20*/mapDiccionario/*419.34*/.get("ARR")),format.raw/*419.45*/("""</TH>
								<TH>TASA<br>"""),_display_(/*420.22*/mapDiccionario/*420.36*/.get("ARR")),format.raw/*420.47*/("""/VTA</TH>
								<TH>P.UNITARIO<br>"""),_display_(/*421.28*/mapDiccionario/*421.42*/.get("ARR")),format.raw/*421.53*/("""</TH>
								<TH>PERMAN</TH>
								<TH>P.TOTAL<br>REPOSICION</TH>
								<TH>P.TOTAL<br>"""),_display_(/*424.25*/mapDiccionario/*424.39*/.get("ARRIENDO")),format.raw/*424.55*/("""</TH>
								<TH>P.TOTAL<br>VENTA</TH>
								<TH>TOT.KG</TH>
								"""),_display_(if(mapPermiso.get("parametro.escondeLosM2")!=null && mapPermiso.get("parametro.escondeLosM2").equals("1"))/*427.116*/ {_display_(Seq[Any](format.raw/*427.118*/("""
									"""),format.raw/*428.10*/("""<TH style = "display: none;">TOT.M2</TH>
								""")))}else/*429.14*/{_display_(Seq[Any](format.raw/*429.15*/("""
									"""),format.raw/*430.10*/("""<TH>TOT.M2</TH>
								""")))}),format.raw/*431.10*/("""
								
								"""),format.raw/*433.9*/("""<TH style = "display:none">kg</TH>
								<TH style = "display:none">m2</TH>
								<TH style = "display:none">id_equipo</TH>
								<TH style = "display:none">nrodecimal</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*440.9*/for(lista1 <- lista) yield /*440.29*/{_display_(Seq[Any](format.raw/*440.30*/("""
								"""),format.raw/*441.9*/("""<TR>
									<td style="text-align:left;">
											"""),_display_(/*443.13*/lista1/*443.19*/.get(23)),format.raw/*443.27*/("""
									"""),format.raw/*444.10*/("""</td>
									<td style="text-align:left;">
										<input class="idEquipo" type="hidden" name="id_equipo[]" value=""""),_display_(/*446.76*/lista1/*446.82*/.get(0)),format.raw/*446.89*/("""">
										<input type="hidden" name="id_moneda[]" value=""""),_display_(/*447.59*/lista1/*447.65*/.get(8)),format.raw/*447.72*/("""">
										
										<a href="#" onclick="equipoDescripcion('"""),_display_(/*449.52*/lista1/*449.58*/.get(0)),format.raw/*449.65*/("""');">
											"""),_display_(/*450.13*/lista1/*450.19*/.get(1)),format.raw/*450.26*/("""
										"""),format.raw/*451.11*/("""</a>
									</td>
									<td style= "text-align: left;"><a href="#" onclick="equipoDescripcion('"""),_display_(/*453.82*/lista1/*453.88*/.get(0)),format.raw/*453.95*/("""');">"""),_display_(/*453.101*/lista1/*453.107*/.get(2)),format.raw/*453.114*/("""</a></td>
									<td style="text-align:center;">
										<div class="noprint">
											<a href="#" onclick="vistaStockPorEquipo('"""),_display_(/*456.55*/lista1/*456.61*/.get(0)),format.raw/*456.68*/("""');">
												<kbd style="background-color: #73C6B6">stock</kbd>
											</a>
										</div>
									</td>
									<td style="text-align:center;">"""),_display_(/*461.42*/lista1/*461.48*/.get(3)),format.raw/*461.55*/("""</td>
									<td style="text-align:center;">
										<div style="display:none" id="hiddencantidad_"""),_display_(/*463.57*/lista1/*463.63*/.get(0)),format.raw/*463.70*/("""">"""),_display_(/*463.73*/lista1/*463.79*/.get(25)),format.raw/*463.87*/("""</div>
										<input type="text" class="cantidad form-control height23px right width80px"
											name="cantidad[]"
											id="cantidad_"""),_display_(/*466.26*/lista1/*466.32*/.get(0)),format.raw/*466.39*/(""""
											value=""""),_display_(/*467.20*/lista1/*467.26*/.get(10)),format.raw/*467.34*/(""""
											onfocus="value = value.replace(/,/g,''); cantAux = value;" 
											onblur = "value = formatStandar(value, '2');"
											onkeydown="return ingresoDouble(window.event)"
											autocomplete="off"
											onchange="if(value=='') value=0; calculaLinea('"""),_display_(/*472.60*/lista1/*472.66*/.get(0)),format.raw/*472.73*/("""', '"""),_display_(/*472.78*/lista1/*472.84*/.get(20)),format.raw/*472.92*/("""');">
									</td>
									<td style="text-align:center;">
										<input type="hidden" name="esVenta[]" id="esVenta_"""),_display_(/*475.62*/lista1/*475.68*/.get(0)),format.raw/*475.75*/(""""  value=""""),_display_(/*475.86*/lista1/*475.92*/.get(11)),format.raw/*475.100*/("""">
										"""),_display_(if(lista1.get(11).equals("0"))/*476.42*/{_display_(Seq[Any](format.raw/*476.43*/("""
											"""),format.raw/*477.12*/("""<input type="checkbox" id="checkbox_"""),_display_(/*477.49*/lista1/*477.55*/.get(0)),format.raw/*477.62*/("""" onchange="checkVenta('"""),_display_(/*477.87*/lista1/*477.93*/.get(0)),format.raw/*477.100*/("""'); calculaLinea('"""),_display_(/*477.119*/lista1/*477.125*/.get(0)),format.raw/*477.132*/("""', '"""),_display_(/*477.137*/lista1/*477.143*/.get(20)),format.raw/*477.151*/("""');">
										""")))}else/*478.16*/{_display_(Seq[Any](format.raw/*478.17*/("""
											"""),format.raw/*479.12*/("""<input type="checkbox" id="checkbox_"""),_display_(/*479.49*/lista1/*479.55*/.get(0)),format.raw/*479.62*/("""" onchange="checkVenta('"""),_display_(/*479.87*/lista1/*479.93*/.get(0)),format.raw/*479.100*/("""'); calculaLinea('"""),_display_(/*479.119*/lista1/*479.125*/.get(0)),format.raw/*479.132*/("""', '"""),_display_(/*479.137*/lista1/*479.143*/.get(20)),format.raw/*479.151*/("""');" checked>
										""")))}),format.raw/*480.12*/("""
									"""),format.raw/*481.10*/("""</td>
									<td style="text-align:center;">"""),_display_(/*482.42*/lista1/*482.48*/.get(4)),format.raw/*482.55*/("""</td>
									<td style="text-align:center;">
										<div style="display:none" id="hiddenpuVentaRepos_"""),_display_(/*484.61*/lista1/*484.67*/.get(0)),format.raw/*484.74*/("""">"""),_display_(/*484.77*/lista1/*484.83*/.get(26)),format.raw/*484.91*/("""</div>
										<input type="text" class="form-control height23px right width100px"
											name="puVentaRepos[]"
											id="puVentaRepos_"""),_display_(/*487.30*/lista1/*487.36*/.get(0)),format.raw/*487.43*/(""""
											value=""""),_display_(/*488.20*/lista1/*488.26*/.get(5)),format.raw/*488.33*/(""""
											onfocus="value=value.replace(/,/g,'');" 
											onblur = "value = formatStandar(value, '"""),_display_(/*490.53*/lista1/*490.59*/.get(20)),format.raw/*490.67*/("""');"
											onkeydown="return ingresoDouble(window.event)"
											autocomplete="off"
											onchange="if(value=='') """),format.raw/*493.36*/("""{"""),format.raw/*493.37*/("""value=0;"""),format.raw/*493.45*/("""}"""),format.raw/*493.46*/(""" """),format.raw/*493.47*/("""else """),format.raw/*493.52*/("""{"""),format.raw/*493.53*/("""calculaTasa(1,'"""),_display_(/*493.69*/lista1/*493.75*/.get(0)),format.raw/*493.82*/("""', '"""),_display_(/*493.87*/lista1/*493.93*/.get(20)),format.raw/*493.101*/("""'); calculaLinea('"""),_display_(/*493.120*/lista1/*493.126*/.get(0)),format.raw/*493.133*/("""', '"""),_display_(/*493.138*/lista1/*493.144*/.get(20)),format.raw/*493.152*/("""');"""),format.raw/*493.155*/("""}"""),format.raw/*493.156*/("""">
									</td>
									<td style="text-align:center;">
										<input type="hidden" id="id_unidadTiempo_"""),_display_(/*496.53*/lista1/*496.59*/.get(0)),format.raw/*496.66*/("""" name="id_unidadTiempo[]" value=""""),_display_(/*496.101*/lista1/*496.107*/.get(9)),format.raw/*496.114*/("""">
										<select class="custom-select"  style="width: 80px;"
											onchange="$('#id_unidadTiempo_"""),_display_(/*498.43*/lista1/*498.49*/.get(0)),format.raw/*498.56*/("""').val(value);">
											<option value=""""),_display_(/*499.28*/lista1/*499.34*/.get(9)),format.raw/*499.41*/("""">"""),_display_(/*499.44*/lista1/*499.50*/.get(6)),format.raw/*499.57*/("""</option>
											"""),_display_(/*500.13*/for(lista <- listUnTiempo) yield /*500.39*/{_display_(Seq[Any](format.raw/*500.40*/("""
												"""),format.raw/*501.13*/("""<option value=""""),_display_(/*501.29*/lista/*501.34*/.id),format.raw/*501.37*/("""">"""),_display_(/*501.40*/lista/*501.45*/.nombre),format.raw/*501.52*/("""</option>
											""")))}),format.raw/*502.13*/("""
										"""),format.raw/*503.11*/("""</select>
									</td>
									<td>
										<div style="display:none" id="hiddentasaArr_"""),_display_(/*506.56*/lista1/*506.62*/.get(0)),format.raw/*506.69*/("""">"""),_display_(/*506.72*/lista1/*506.78*/.get(27)),format.raw/*506.86*/("""</div>
										<input type="text" class="tasaGlobal form-control height23px right width80px"
											id="tasaArr_"""),_display_(/*508.25*/lista1/*508.31*/.get(0)),format.raw/*508.38*/(""""
											value=""""),_display_(/*509.20*/lista1/*509.26*/.get(24)),format.raw/*509.34*/(""""
											onfocus="value=value.replace(/,/g,'').replace(/%/g,'').replace(/ /g,'')"
											onblur = "value = formatPorcentaje(value);"
											onkeydown="return ingresoDouble(window.event)"
											autocomplete="off"
											onchange="if(value=='') """),format.raw/*514.36*/("""{"""),format.raw/*514.37*/("""value='0.00 %';"""),format.raw/*514.52*/("""}"""),format.raw/*514.53*/(""" """),format.raw/*514.54*/("""else """),format.raw/*514.59*/("""{"""),format.raw/*514.60*/("""calculaTasa(2,'"""),_display_(/*514.76*/lista1/*514.82*/.get(0)),format.raw/*514.89*/("""', '"""),_display_(/*514.94*/lista1/*514.100*/.get(20)),format.raw/*514.108*/("""'); calculaLinea('"""),_display_(/*514.127*/lista1/*514.133*/.get(0)),format.raw/*514.140*/("""', '"""),_display_(/*514.145*/lista1/*514.151*/.get(20)),format.raw/*514.159*/("""');"""),format.raw/*514.162*/("""}"""),format.raw/*514.163*/("""">
									</td>
									<td style="text-align:center;">
										<div style="display:none" id="hiddenpuArriendo_"""),_display_(/*517.59*/lista1/*517.65*/.get(0)),format.raw/*517.72*/("""">"""),_display_(/*517.75*/lista1/*517.81*/.get(28)),format.raw/*517.89*/("""</div>
										<input type="text" class="form-control height23px right width80px"
											name="puArriendo[]"
											id="puArriendo_"""),_display_(/*520.28*/lista1/*520.34*/.get(0)),format.raw/*520.41*/(""""
											value=""""),_display_(/*521.20*/lista1/*521.26*/.get(7)),format.raw/*521.33*/(""""
											onfocus="value=value.replace(/,/g,'');" 
											onblur = "value = formatStandar(value, '"""),_display_(/*523.53*/lista1/*523.59*/.get(20)),format.raw/*523.67*/("""');"
											onkeydown="return ingresoDouble(window.event)"
											autocomplete="off"
											onchange="if(value=='') """),format.raw/*526.36*/("""{"""),format.raw/*526.37*/("""value=0;"""),format.raw/*526.45*/("""}"""),format.raw/*526.46*/(""" """),format.raw/*526.47*/("""else """),format.raw/*526.52*/("""{"""),format.raw/*526.53*/("""calculaTasa(3,'"""),_display_(/*526.69*/lista1/*526.75*/.get(0)),format.raw/*526.82*/("""', '"""),_display_(/*526.87*/lista1/*526.93*/.get(20)),format.raw/*526.101*/("""'); calculaLinea('"""),_display_(/*526.120*/lista1/*526.126*/.get(0)),format.raw/*526.133*/("""', '"""),_display_(/*526.138*/lista1/*526.144*/.get(20)),format.raw/*526.152*/("""');"""),format.raw/*526.155*/("""}"""),format.raw/*526.156*/("""">
									</td>
									<td style="text-align:center;">
										<div style="display:none" id="hiddenpermanencia_"""),_display_(/*529.60*/lista1/*529.66*/.get(0)),format.raw/*529.73*/("""">"""),_display_(/*529.76*/lista1/*529.82*/.get(29)),format.raw/*529.90*/("""</div>
										<input type="text" class="form-control height23px right"
											name="permanencia[]"
											id="permanencia_"""),_display_(/*532.29*/lista1/*532.35*/.get(0)),format.raw/*532.42*/(""""
											value=""""),_display_(/*533.20*/lista1/*533.26*/.get(12)),format.raw/*533.34*/(""""
											onfocus="value=value.replace(/,/g,'');" 
											onblur = "$('#hiddenpermanencia_"""),_display_(/*535.45*/lista1/*535.51*/.get(0)),format.raw/*535.58*/("""').text(value); value = formatStandar(value, '2');"
											onkeydown="return ingresoDouble(window.event)"
											autocomplete="off"
											onchange="if(value=='') value=0; calculaLinea('"""),_display_(/*538.60*/lista1/*538.66*/.get(0)),format.raw/*538.73*/("""', '"""),_display_(/*538.78*/lista1/*538.84*/.get(20)),format.raw/*538.92*/("""');">
									</td>
									
									
								
									<td class="totRepos" style="text-align:right;" id="totRepos_"""),_display_(/*543.71*/lista1/*543.77*/.get(0)),format.raw/*543.84*/("""">"""),_display_(/*543.87*/lista1/*543.93*/.get(13)),format.raw/*543.101*/("""</td>
									<td class="totArrie" style="text-align:right;" id="totArrie_"""),_display_(/*544.71*/lista1/*544.77*/.get(0)),format.raw/*544.84*/("""">"""),_display_(/*544.87*/lista1/*544.93*/.get(14)),format.raw/*544.101*/("""</td>
									<td class="totVenta" style="text-align:right;" id="totVenta_"""),_display_(/*545.71*/lista1/*545.77*/.get(0)),format.raw/*545.84*/("""">"""),_display_(/*545.87*/lista1/*545.93*/.get(15)),format.raw/*545.101*/("""</td>
									
						
									<td class="totKg" style="text-align:right;" id="totKg_"""),_display_(/*548.65*/lista1/*548.71*/.get(0)),format.raw/*548.78*/("""">"""),_display_(/*548.81*/lista1/*548.87*/.get(18)),format.raw/*548.95*/("""</td>
									
									"""),_display_(if(mapPermiso.get("parametro.escondeLosM2")!=null && mapPermiso.get("parametro.escondeLosM2").equals("1"))/*550.117*/ {_display_(Seq[Any](format.raw/*550.119*/("""
										"""),format.raw/*551.11*/("""<td  style = "display:none; text-align:right;" class="totM2" id="totM2_"""),_display_(/*551.83*/lista1/*551.89*/.get(0)),format.raw/*551.96*/("""">"""),_display_(/*551.99*/lista1/*551.105*/.get(19)),format.raw/*551.113*/("""</td>
									""")))}else/*552.15*/{_display_(Seq[Any](format.raw/*552.16*/("""
										"""),format.raw/*553.11*/("""<td class="totM2" style="text-align:right;" id="totM2_"""),_display_(/*553.66*/lista1/*553.72*/.get(0)),format.raw/*553.79*/("""">"""),_display_(/*553.82*/lista1/*553.88*/.get(19)),format.raw/*553.96*/("""</td>
									""")))}),format.raw/*554.11*/("""
									"""),format.raw/*555.10*/("""<td style = "display:none" id="uniKg_"""),_display_(/*555.48*/lista1/*555.54*/.get(0)),format.raw/*555.61*/("""">"""),_display_(/*555.64*/lista1/*555.70*/.get(16)),format.raw/*555.78*/("""</td>
									<td style = "display:none" id="uniM2_"""),_display_(/*556.48*/lista1/*556.54*/.get(0)),format.raw/*556.61*/("""">"""),_display_(/*556.64*/lista1/*556.70*/.get(17)),format.raw/*556.78*/("""</td>
									<td style = "display:none" id="idEquipo_"""),_display_(/*557.51*/lista1/*557.57*/.get(0)),format.raw/*557.64*/("""">"""),_display_(/*557.67*/lista1/*557.73*/.get(0)),format.raw/*557.80*/("""</td>
									<td style = "display:none" id="nroDecimales_"""),_display_(/*558.55*/lista1/*558.61*/.get(0)),format.raw/*558.68*/("""">"""),_display_(/*558.71*/lista1/*558.77*/.get(20)),format.raw/*558.85*/("""</td>
									
								</TR>
				 			""")))}),format.raw/*561.10*/("""
						"""),format.raw/*562.7*/("""</tbody>
						<tfoot id="tfoot" style="background-color: #eeeeee; display:none"">
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
							</TR>
							<TR> 
								<TH colspan="13" style="text-align:right;">DESCUENTOS</TH>
								<TH style="text-align:right;"></TH>
								<TH style="text-align:center;">
									<input type="text" class="form-control height23px right"
										id="dctoArriendo"
										name="dctoArriendo"
										value=""""),_display_(/*585.19*/formCotiza/*585.29*/.dctoArriendo),format.raw/*585.42*/(""" """),format.raw/*585.43*/("""%"
										onfocus="value=value.replace(/,/g,'').replace(/%/g,'').replace(/ /g,'')" 
										onblur = "value = formatPorcentaje(value);"
										onkeydown="return ingresoDouble(window.event)"
										onchange="sumaTotales();">
								</TH>
								<TH style="text-align:center;">
									<input type="text" class="form-control height23px right"
										id="dctoVenta"
										name="dctoVenta"
										value=""""),_display_(/*595.19*/formCotiza/*595.29*/.dctoVenta),format.raw/*595.39*/(""" """),format.raw/*595.40*/("""%"
										onfocus="value=value.replace(/,/g,'').replace(/%/g,'').replace(/ /g,'')" 
										onblur = "value = formatPorcentaje(value);"
										onkeydown="return ingresoDouble(window.event)"
										onchange="sumaTotales();">
								</TH>
								<TH></TH>
								"""),_display_(if(mapPermiso.get("parametro.escondeLosM2")!=null && mapPermiso.get("parametro.escondeLosM2").equals("1"))/*602.116*/ {_display_(Seq[Any](format.raw/*602.118*/("""
									"""),format.raw/*603.10*/("""<TH style = "display:none"></TH>
								""")))}else/*604.14*/{_display_(Seq[Any](format.raw/*604.15*/("""
									"""),format.raw/*605.10*/("""<TH></TH>
								""")))}),format.raw/*606.10*/("""
								"""),format.raw/*607.9*/("""<TH style = "display:none"></TH>
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
								
								"""),_display_(if(mapPermiso.get("parametro.escondeLosM2")!=null && mapPermiso.get("parametro.escondeLosM2").equals("1"))/*619.116*/ {_display_(Seq[Any](format.raw/*619.118*/("""
									"""),format.raw/*620.10*/("""<TH style="display:none; text-align:right;"><div id="granTotM2">0.00</div></TH>
								""")))}else/*621.14*/{_display_(Seq[Any](format.raw/*621.15*/("""
									"""),format.raw/*622.10*/("""<TH style = "text-align:right;"><div id="granTotM2">0.00</div></TH>
								""")))}),format.raw/*623.10*/("""
								"""),format.raw/*624.9*/("""<TH style = "display:none"></TH>
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
						<input type="submit"  id="aplica" value='Generar Cotización' class="btn btn-primary btn-sm rounded-pill btn-block" style="visibility:hidden">
					</div>
				</div>
			</div>
			
		</div>
	</form>
	
	<div id='listaCliente' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>SELECCIONAR CLIENTE</h5>
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
					<table id="tablaListaClientes" class='table table-sm table-bordered table-condensed table-hover table-fluid'>
						<thead style="background-color: #eeeeee">
							<TR> 
								<TH>"""),_display_(/*674.14*/mapDiccionario/*674.28*/.get("RUT")),format.raw/*674.39*/("""</TH>
								<TH>NICK</TH>
								<TH>NOMBRE</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*680.9*/for(lista1 <- listClientes) yield /*680.36*/{_display_(Seq[Any](format.raw/*680.37*/("""
								"""),format.raw/*681.9*/("""<TR onClick="$('#id_cliente').val("""),_display_(/*681.44*/lista1/*681.50*/.getId()),format.raw/*681.58*/("""); $('#rutCliente').val('"""),_display_(/*681.84*/lista1/*681.90*/.getRut()),format.raw/*681.99*/("""');$('#nombreCliente').val('"""),_display_(/*681.128*/lista1/*681.134*/.getNickName()),format.raw/*681.148*/("""');" data-dismiss="modal">
									<td  style="text-align:left;">"""),_display_(/*682.41*/lista1/*682.47*/.getRut()),format.raw/*682.56*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*683.41*/lista1/*683.47*/.getNickName()),format.raw/*683.61*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*684.41*/lista1/*684.47*/.getNombre()),format.raw/*684.59*/("""</td>
								</TR>
				 			""")))}),format.raw/*686.10*/("""
						"""),format.raw/*687.7*/("""</tbody>
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
					<h5 class='modal-title'>SELECCIONAR PROYECTO</h5>
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
					<table id="tablaListaProyectos" class='table table-sm table-bordered table-condensed table-hover table-fluid'>
						<thead style="background-color: #eeeeee">
							<TR>
								<TH>NICK</TH>
								<TH>NOMBRE</TH>
								<TH>"""),_display_(/*719.14*/mapDiccionario/*719.28*/.get("Comuna")),format.raw/*719.42*/("""</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*723.9*/for(lista1 <- listProyectos) yield /*723.37*/{_display_(Seq[Any](format.raw/*723.38*/("""
								"""),format.raw/*724.9*/("""<TR onClick="$('#id_proyecto').val("""),_display_(/*724.45*/lista1/*724.51*/.getId()),format.raw/*724.59*/("""); $('#nombreProyecto').val('"""),_display_(/*724.89*/lista1/*724.95*/.getNickName()),format.raw/*724.109*/("""');" data-dismiss="modal">
									<td  style="text-align:left;">"""),_display_(/*725.41*/lista1/*725.47*/.getNickName()),format.raw/*725.61*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*726.41*/lista1/*726.47*/.getNombre()),format.raw/*726.59*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*727.41*/lista1/*727.47*/.getComuna()),format.raw/*727.59*/("""</td>
								</TR>
				 			""")))}),format.raw/*729.10*/("""
						"""),format.raw/*730.7*/("""</tbody>
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
	
	<div id='modalStockPorEquipo' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable' style='max-width: 80% !important;' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>STOCK DISPONIBLE</h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<div id="stockPorEquipo"></div>
	   				<div class='row'>
						<div class='col-sm-12' style='text-align:center'>
							<button type='button' class='btn btn-sm  btn-warning' style='font-size: 10px;' data-dismiss='modal'>Cancelar</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<form class="formulario" id="formDescargaDocumento" method="post" action="/downloadPDF/">	
		<input type="hidden" id="descargaDocumento" name="nombreArchivo">
	</form>
	
""")))}),format.raw/*791.2*/("""



"""),format.raw/*795.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*797.31*/("""{"""),format.raw/*797.32*/("""
		"""),format.raw/*798.3*/("""$('#tablaListaClientes').DataTable("""),format.raw/*798.38*/("""{"""),format.raw/*798.39*/("""
	    	"""),format.raw/*799.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
	    	"order": [[ 1, "asc" ]],
	    	"language": """),format.raw/*802.19*/("""{"""),format.raw/*802.20*/("""
	        	"""),format.raw/*803.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*804.10*/("""}"""),format.raw/*804.11*/("""
	  	"""),format.raw/*805.5*/("""}"""),format.raw/*805.6*/(""" """),format.raw/*805.7*/(""");

		$('#tablaListaProyectos').DataTable("""),format.raw/*807.39*/("""{"""),format.raw/*807.40*/("""
	    	"""),format.raw/*808.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
	    	"order": [[ 0, "asc" ]],
	    	"language": """),format.raw/*811.19*/("""{"""),format.raw/*811.20*/("""
	        	"""),format.raw/*812.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*813.10*/("""}"""),format.raw/*813.11*/("""
	  	"""),format.raw/*814.5*/("""}"""),format.raw/*814.6*/(""" """),format.raw/*814.7*/(""");

		sumaTotales();
		
		$('#tablaPrincipal').DataTable("""),format.raw/*818.34*/("""{"""),format.raw/*818.35*/("""
		    	"""),format.raw/*819.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[15, 50, 100, 200, -1], [15, 50, 100, 200, "All"]],
		    	"order": [[ 0, "asc" ],[ 2, "asc" ]],
		    	"language": """),format.raw/*822.20*/("""{"""),format.raw/*822.21*/("""
		        	"""),format.raw/*823.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*824.11*/("""}"""),format.raw/*824.12*/("""
		"""),format.raw/*825.3*/("""}"""),format.raw/*825.4*/(""" """),format.raw/*825.5*/(""");
		  
		document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*828.2*/("""}"""),format.raw/*828.3*/(""");
	
	
	const validarNumero = (numero) =>"""),format.raw/*831.35*/("""{"""),format.raw/*831.36*/("""
		"""),format.raw/*832.3*/("""var formData = new FormData();
		formData.append('numeroCoti',numero);
        $.ajax("""),format.raw/*834.16*/("""{"""),format.raw/*834.17*/("""
            """),format.raw/*835.13*/("""url: "/existeNumeroCotizacionAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*842.36*/("""{"""),format.raw/*842.37*/("""
				"""),format.raw/*843.5*/("""if(respuesta!="OK")"""),format.raw/*843.24*/("""{"""),format.raw/*843.25*/("""
					"""),format.raw/*844.6*/("""$("#numeroCoti").val(""""),_display_(/*844.29*/formCotiza/*844.39*/.numeroCoti),format.raw/*844.50*/("""");
					alertify.alert(respuesta, function () """),format.raw/*845.44*/("""{"""),format.raw/*845.45*/(""" """),format.raw/*845.46*/("""}"""),format.raw/*845.47*/(""");
				"""),format.raw/*846.5*/("""}"""),format.raw/*846.6*/("""
	     	"""),format.raw/*847.8*/("""}"""),format.raw/*847.9*/("""
        """),format.raw/*848.9*/("""}"""),format.raw/*848.10*/(""");
	"""),format.raw/*849.2*/("""}"""),format.raw/*849.3*/("""
	
	"""),format.raw/*851.2*/("""const vistaStockPorEquipo = (id_equipo) =>"""),format.raw/*851.44*/("""{"""),format.raw/*851.45*/("""
		"""),format.raw/*852.3*/("""var formData = new FormData();
		formData.append('id_equipo',id_equipo);
        $.ajax("""),format.raw/*854.16*/("""{"""),format.raw/*854.17*/("""
            """),format.raw/*855.13*/("""url: "/tablaInvPorEquipoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*862.36*/("""{"""),format.raw/*862.37*/("""
				"""),format.raw/*863.5*/("""document.getElementById('stockPorEquipo').innerHTML = respuesta;
				$('#modalStockPorEquipo').modal('show');
	     	"""),format.raw/*865.8*/("""}"""),format.raw/*865.9*/("""
        """),format.raw/*866.9*/("""}"""),format.raw/*866.10*/(""");
	"""),format.raw/*867.2*/("""}"""),format.raw/*867.3*/("""
	
	
	"""),format.raw/*870.2*/("""const sumaTotales = () =>"""),format.raw/*870.27*/("""{"""),format.raw/*870.28*/("""
		"""),format.raw/*871.3*/("""$("#tablaPrincipal").dataTable().fnDestroy();
		let tabla = document.getElementById("tablaPrincipal");
		let totCant = 0;
		
		let cant = 0;
		$(".cantidad").each(function() """),format.raw/*876.34*/("""{"""),format.raw/*876.35*/("""
			"""),format.raw/*877.4*/("""let val = $(this).val().replace(/,/g,'');
			cant += parseFloat(val);
		"""),format.raw/*879.3*/("""}"""),format.raw/*879.4*/(""");
		
		let repos = 0;
		$(".totRepos").each(function() """),format.raw/*882.34*/("""{"""),format.raw/*882.35*/("""
			"""),format.raw/*883.4*/("""let val = $(this).text().replace(/,/g,'');
			repos += parseFloat(val);
		"""),format.raw/*885.3*/("""}"""),format.raw/*885.4*/("""); $("#subtotalRepos").text(formatStandar(repos,'"""),_display_(/*885.54*/numDecParaTot),format.raw/*885.67*/("""'));
		
		let arr = 0;
		$(".totArrie").each(function() """),format.raw/*888.34*/("""{"""),format.raw/*888.35*/("""
			"""),format.raw/*889.4*/("""let val = $(this).text().replace(/,/g,'');
			arr += parseFloat(val);
		"""),format.raw/*891.3*/("""}"""),format.raw/*891.4*/("""); $("#subtotalArrie").text(formatStandar(arr,'"""),_display_(/*891.52*/numDecParaTot),format.raw/*891.65*/("""'));
		
		let vta = 0;
		$(".totVenta").each(function() """),format.raw/*894.34*/("""{"""),format.raw/*894.35*/("""
			"""),format.raw/*895.4*/("""let val = $(this).text().replace(/,/g,'');
			vta += parseFloat(val);
		"""),format.raw/*897.3*/("""}"""),format.raw/*897.4*/("""); $("#subtotalVenta").text(formatStandar(vta,'"""),_display_(/*897.52*/numDecParaTot),format.raw/*897.65*/("""'));
		
		let kg = 0;
		$(".totKg").each(function() """),format.raw/*900.31*/("""{"""),format.raw/*900.32*/("""
			"""),format.raw/*901.4*/("""let val = $(this).text().replace(/,/g,'');
			kg += parseFloat(val);
		"""),format.raw/*903.3*/("""}"""),format.raw/*903.4*/(""");
		
		let m2 = 0;
		$(".totM2").each(function() """),format.raw/*906.31*/("""{"""),format.raw/*906.32*/("""
			"""),format.raw/*907.4*/("""let val = $(this).text().replace(/,/g,'');
			m2 += parseFloat(val);
		"""),format.raw/*909.3*/("""}"""),format.raw/*909.4*/(""");
		
		let dctoArriendo = $("#dctoArriendo").val().replace(/,/g,'').replace("%","").trim();
		let dctoVenta = $("#dctoVenta").val().replace(/,/g,'').replace("%","").trim();
		
		$("#granTotRepos").text(formatStandar(repos,'"""),_display_(/*914.49*/numDecParaTot),format.raw/*914.62*/("""'));
		$("#granTotArrie").text(formatStandar((arr*(1-dctoArriendo/100)),'"""),_display_(/*915.70*/numDecParaTot),format.raw/*915.83*/("""'));
		$("#granTotVenta").text(formatStandar((vta*(1-dctoVenta/100)),'"""),_display_(/*916.67*/numDecParaTot),format.raw/*916.80*/("""'));
		$("#granTotKg").text(formatStandar(kg,2));
		$("#granTotM2").text(formatStandar(m2,2));
		$("#granTotCant").text(formatStandar(cant,2));
		
	"""),format.raw/*921.2*/("""}"""),format.raw/*921.3*/("""
	
	"""),format.raw/*923.2*/("""const checkVenta = (id_equipo) =>"""),format.raw/*923.35*/("""{"""),format.raw/*923.36*/("""
		"""),format.raw/*924.3*/("""let esVenta = $("#esVenta_"+id_equipo).val();
		if(esVenta==0)"""),format.raw/*925.17*/("""{"""),format.raw/*925.18*/("""
			"""),format.raw/*926.4*/("""$("#esVenta_"+id_equipo).val('1');
		"""),format.raw/*927.3*/("""}"""),format.raw/*927.4*/("""else"""),format.raw/*927.8*/("""{"""),format.raw/*927.9*/("""
			"""),format.raw/*928.4*/("""$("#esVenta_"+id_equipo).val('0');
		"""),format.raw/*929.3*/("""}"""),format.raw/*929.4*/("""
	"""),format.raw/*930.2*/("""}"""),format.raw/*930.3*/("""
	
	"""),format.raw/*932.2*/("""let calculaTasa = (flag, id_equipo, numDec) => """),format.raw/*932.49*/("""{"""),format.raw/*932.50*/("""
		"""),format.raw/*933.3*/("""if(flag==1)"""),format.raw/*933.14*/("""{"""),format.raw/*933.15*/("""
			"""),format.raw/*934.4*/("""let uniVta = $("#puVentaRepos_"+id_equipo).val().replace(/,/g,'');
			let uniArr = $("#puArriendo_"+id_equipo).val().replace(/,/g,'');
			let uniTasa = parseFloat(uniArr)/parseFloat(uniVta);
			$("#tasaArr_"+id_equipo).val(formatStandar(uniTasa * 100,2)+" %");
			$("#hiddentasaArr_"+id_equipo).text(uniTasa);
		"""),format.raw/*939.3*/("""}"""),format.raw/*939.4*/("""else if(flag==2)"""),format.raw/*939.20*/("""{"""),format.raw/*939.21*/("""
			"""),format.raw/*940.4*/("""let uniVta = $("#puVentaRepos_"+id_equipo).val().replace(/,/g,'');
			let uniTasa = $("#tasaArr_"+id_equipo).val().replace(/,/g,'').replace(/%/g,'').replace(/ /g,'');
			let uniArr = parseFloat(uniVta)*parseFloat(uniTasa)/100;
			$("#puArriendo_"+id_equipo).val(formatStandar(uniArr,numDec));
			$("#hiddentasaArr_"+id_equipo).text(uniTasa);
		"""),format.raw/*945.3*/("""}"""),format.raw/*945.4*/("""else"""),format.raw/*945.8*/("""{"""),format.raw/*945.9*/("""
			"""),format.raw/*946.4*/("""let uniVta = $("#puVentaRepos_"+id_equipo).val().replace(/,/g,'');
			let uniArr = $("#puArriendo_"+id_equipo).val().replace(/,/g,'');
			let uniTasa = parseFloat(uniArr)/parseFloat(uniVta);
			$("#tasaArr_"+id_equipo).val(formatStandar(uniTasa * 100,2)+" %");
			$("#hiddentasaArr_"+id_equipo).text(uniTasa);
		"""),format.raw/*951.3*/("""}"""),format.raw/*951.4*/("""
		
	"""),format.raw/*953.2*/("""}"""),format.raw/*953.3*/("""
	
	"""),format.raw/*955.2*/("""const calculaLinea = (id_equipo, numDec) =>"""),format.raw/*955.45*/("""{"""),format.raw/*955.46*/("""
		"""),format.raw/*956.3*/("""let cant = $("#cantidad_"+id_equipo).val().replace(/,/g,'');
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
		if(esVenta == 0)"""),format.raw/*969.19*/("""{"""),format.raw/*969.20*/("""
			 """),format.raw/*970.5*/("""totRepos = parseFloat(cant) * parseFloat(uniVta);
			 totArr = parseFloat(cant) * parseFloat(perm) * parseFloat(uniArr);
			 totVta = 0;
			 totKg = parseFloat(cant) * parseFloat(uniKg);
			 totM2 = parseFloat(cant) * parseFloat(uniM2);
		"""),format.raw/*975.3*/("""}"""),format.raw/*975.4*/("""else"""),format.raw/*975.8*/("""{"""),format.raw/*975.9*/("""
			 """),format.raw/*976.5*/("""totRepos = 0;
			 totArr = 0;
			 totVta = parseFloat(cant) * parseFloat(uniVta);
			 totKg = parseFloat(cant) * parseFloat(uniKg);
			 totM2 = parseFloat(cant) * parseFloat(uniM2);
		"""),format.raw/*981.3*/("""}"""),format.raw/*981.4*/("""
		"""),format.raw/*982.3*/("""$("#cantidad_"+id_equipo).val(formatStandar(cant,2));
		$("#puVentaRepos_"+id_equipo).val(formatStandar(uniVta,numDec));
		$("#puArriendo_"+id_equipo).val(formatStandar(uniArr,numDec))
		$("#totRepos_"+id_equipo).text(formatStandar(totRepos,numDec));
		$("#totArrie_"+id_equipo).text(formatStandar(totArr,numDec));
		$("#totVenta_"+id_equipo).text(formatStandar(totVta,numDec));
		$("#totKg_"+id_equipo).text(formatStandar(totKg,2));
		$("#totM2_"+id_equipo).text(formatStandar(totM2,2));
		
		$("#hiddencantidad_"+id_equipo).text(cant);
		$("#hiddenpuVentaRepos_"+id_equipo).text(uniVta);
		$("#hiddenpuArriendo_"+id_equipo).text(uniArr);
		
		if(verificando == 1)"""),format.raw/*995.23*/("""{"""),format.raw/*995.24*/("""
			"""),format.raw/*996.4*/("""sumaTotales();
		"""),format.raw/*997.3*/("""}"""),format.raw/*997.4*/("""
		
	"""),format.raw/*999.2*/("""}"""),format.raw/*999.3*/("""
	
	"""),format.raw/*1001.2*/("""let verificando = 0;
	const verificaCotizacion = () =>"""),format.raw/*1002.34*/("""{"""),format.raw/*1002.35*/("""
		"""),format.raw/*1003.3*/("""sumaTotales();
		let granTotRepos = $("#granTotRepos").text().replace(/,/g,'');
		let granTotArrie = $("#granTotArrie").text().replace(/,/g,'');
		let granTotVenta = $("#granTotVenta").text().replace(/,/g,'');
		let sumaAux = parseFloat(granTotRepos) + parseFloat(granTotArrie) + parseFloat(granTotVenta);
		if(sumaAux > 0)"""),format.raw/*1008.18*/("""{"""),format.raw/*1008.19*/("""
			"""),format.raw/*1009.4*/("""let tabla = document.getElementById("tablaPrincipal");
			for(i=2; i<tabla.rows.length-3; i++)"""),format.raw/*1010.40*/("""{"""),format.raw/*1010.41*/("""
				"""),format.raw/*1011.5*/("""let cant = $(".cantidad",tabla.rows[i].cells[5]).val().replace(/,/g,'');
				let arr = tabla.rows[i].cells[13].innerHTML.replace(/,/g,'');
				let vta = tabla.rows[i].cells[14].innerHTML.replace(/,/g,'');
				if((parseFloat(cant)+parseFloat(arr)+parseFloat(vta))<=0)"""),format.raw/*1014.62*/("""{"""),format.raw/*1014.63*/("""
					"""),format.raw/*1015.6*/("""tabla.rows[i].style.display = 'none';
				"""),format.raw/*1016.5*/("""}"""),format.raw/*1016.6*/("""
			"""),format.raw/*1017.4*/("""}"""),format.raw/*1017.5*/("""
			"""),format.raw/*1018.4*/("""verificando = 1;
			document.getElementById('tfoot').style.display = '';
			document.getElementById('modifica').style.visibility = 'visible';
			document.getElementById('aplica').style.visibility = 'visible';
			document.getElementById('verifica').style.visibility = 'hidden';
			document.getElementById('selSucursal').disabled = true;
			document.getElementById('selComercial').disabled = true;
			$('#btnAplicarTasa').hide();
			$('#btnAplicarFactor').hide();
		"""),format.raw/*1027.3*/("""}"""),format.raw/*1027.4*/("""else"""),format.raw/*1027.8*/("""{"""),format.raw/*1027.9*/("""
			"""),format.raw/*1028.4*/("""$('#tablaPrincipal').DataTable("""),format.raw/*1028.35*/("""{"""),format.raw/*1028.36*/("""
		    	"""),format.raw/*1029.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[15, 50, 100, 200, -1], [15, 50, 100, 200, "All"]],
		    	"order": [[ 0, "asc" ],[ 2, "asc" ]],
		    	"language": """),format.raw/*1032.20*/("""{"""),format.raw/*1032.21*/("""
		        	"""),format.raw/*1033.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*1034.11*/("""}"""),format.raw/*1034.12*/("""
			"""),format.raw/*1035.4*/("""}"""),format.raw/*1035.5*/(""" """),format.raw/*1035.6*/(""");
			alertify.alert('NO PUEDE VERIFICAR UNA COTIZACION SIN CANTIDADES', function () """),format.raw/*1036.83*/("""{"""),format.raw/*1036.84*/(""" """),format.raw/*1036.85*/("""}"""),format.raw/*1036.86*/(""");
		"""),format.raw/*1037.3*/("""}"""),format.raw/*1037.4*/("""
	"""),format.raw/*1038.2*/("""}"""),format.raw/*1038.3*/("""
	
	"""),format.raw/*1040.2*/("""const modificaCotizacion = () =>"""),format.raw/*1040.34*/("""{"""),format.raw/*1040.35*/("""
		"""),format.raw/*1041.3*/("""let tabla = document.getElementById("tablaPrincipal");
		for(i=2; i<tabla.rows.length-3; i++)"""),format.raw/*1042.39*/("""{"""),format.raw/*1042.40*/("""
			"""),format.raw/*1043.4*/("""tabla.rows[i].style.display = '';
		"""),format.raw/*1044.3*/("""}"""),format.raw/*1044.4*/("""
		"""),format.raw/*1045.3*/("""$('#tablaPrincipal').DataTable("""),format.raw/*1045.34*/("""{"""),format.raw/*1045.35*/("""
		    	"""),format.raw/*1046.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[15, 50, 100, 200, -1], [15, 50, 100, 200, "All"]],
		    	"order": [[ 0, "asc" ],[ 2, "asc" ]],
		    	"language": """),format.raw/*1049.20*/("""{"""),format.raw/*1049.21*/("""
		        	"""),format.raw/*1050.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*1051.11*/("""}"""),format.raw/*1051.12*/("""
		"""),format.raw/*1052.3*/("""}"""),format.raw/*1052.4*/(""" """),format.raw/*1052.5*/(""");
		document.getElementById('tfoot').style.display = 'none';
		document.getElementById('modifica').style.visibility = 'hidden';
		document.getElementById('aplica').style.visibility = 'hidden';
		document.getElementById('verifica').style.visibility = 'visible';
		document.getElementById('selSucursal').disabled = false;
		document.getElementById('selComercial').disabled = false;
		$('#btnAplicarTasa').show();
		$('#btnAplicarFactor').show();
		verificando = 0;
	"""),format.raw/*1062.2*/("""}"""),format.raw/*1062.3*/("""

	"""),format.raw/*1064.2*/("""const validarForm = () =>"""),format.raw/*1064.27*/("""{"""),format.raw/*1064.28*/("""
		"""),format.raw/*1065.3*/("""$("#aplica").attr('disabled',true);
		let flag = true;
		sumaTotales();
		let granTotRepos = $("#granTotRepos").text().replace(/,/g,'');
		let granTotArrie = $("#granTotArrie").text().replace(/,/g,'');
		let granTotVenta = $("#granTotVenta").text().replace(/,/g,'');
		let sumaAux = parseFloat(granTotRepos) + parseFloat(granTotArrie) + parseFloat(granTotVenta);
		
		
		if(sumaAux <= 0)"""),format.raw/*1074.19*/("""{"""),format.raw/*1074.20*/("""
			"""),format.raw/*1075.4*/("""flag = false;
			alertify.alert('NO PUEDE GENERAR UNA COTIZACION SIN CANTIDADES', function () """),format.raw/*1076.81*/("""{"""),format.raw/*1076.82*/("""
				"""),format.raw/*1077.5*/("""$("#aplica").attr('disabled',false);
				return(flag);
     		"""),format.raw/*1079.8*/("""}"""),format.raw/*1079.9*/(""");
		"""),format.raw/*1080.3*/("""}"""),format.raw/*1080.4*/("""else if($("#id_cliente").val()=='0')"""),format.raw/*1080.40*/("""{"""),format.raw/*1080.41*/("""
			"""),format.raw/*1081.4*/("""flag = false;
			alertify.alert('NO PUEDE GENERAR UNA COTIZACION SIN ASIGNAR UN CLIENTE', function () """),format.raw/*1082.89*/("""{"""),format.raw/*1082.90*/("""
				"""),format.raw/*1083.5*/("""$("#aplica").attr('disabled',false);
				return(flag);
     		"""),format.raw/*1085.8*/("""}"""),format.raw/*1085.9*/(""");
		"""),format.raw/*1086.3*/("""}"""),format.raw/*1086.4*/("""else if ((sumaAux-parseFloat(granTotRepos))<=0)"""),format.raw/*1086.51*/("""{"""),format.raw/*1086.52*/("""
			"""),format.raw/*1087.4*/("""flag = false;
				alertify.alert('NO PUEDE GENERAR UNA COTIZACION QUE SUMA CERO, REVISAR PERMANENCIA', function () """),format.raw/*1088.102*/("""{"""),format.raw/*1088.103*/("""
					"""),format.raw/*1089.6*/("""$("#aplica").attr('disabled',false);
					return(flag);
	     		"""),format.raw/*1091.9*/("""}"""),format.raw/*1091.10*/(""");
		"""),format.raw/*1092.3*/("""}"""),format.raw/*1092.4*/("""else"""),format.raw/*1092.8*/("""{"""),format.raw/*1092.9*/("""
			"""),format.raw/*1093.4*/("""let tabla = document.getElementById("tablaPrincipal");
			for(i=2; i<tabla.rows.length-3; i++)"""),format.raw/*1094.40*/("""{"""),format.raw/*1094.41*/("""
				"""),format.raw/*1095.5*/("""let cant = $(".cantidad",tabla.rows[i].cells[5]).val().replace(/,/g,'');
				let arr = tabla.rows[i].cells[13].innerHTML.replace(/,/g,'');
				let vta = tabla.rows[i].cells[14].innerHTML.replace(/,/g,'');
				if((parseFloat(cant)+parseFloat(arr)+parseFloat(vta))<=0)"""),format.raw/*1098.62*/("""{"""),format.raw/*1098.63*/("""
					"""),format.raw/*1099.6*/("""tabla.deleteRow(i);
					i--;
				"""),format.raw/*1101.5*/("""}"""),format.raw/*1101.6*/("""
			"""),format.raw/*1102.4*/("""}"""),format.raw/*1102.5*/("""
			"""),format.raw/*1103.4*/("""return(true);
		"""),format.raw/*1104.3*/("""}"""),format.raw/*1104.4*/("""
		"""),format.raw/*1105.3*/("""return(flag);
	"""),format.raw/*1106.2*/("""}"""),format.raw/*1106.3*/("""
	
	"""),format.raw/*1108.2*/("""const descargaDocumento = (nombreDOC) => """),format.raw/*1108.43*/("""{"""),format.raw/*1108.44*/("""
		  """),format.raw/*1109.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*1111.2*/("""}"""),format.raw/*1111.3*/("""

	"""),format.raw/*1113.2*/("""let extArray = new Array(".gif", ".jpg", ".png", ".jpeg", ".pdf", ".xls", ".doc", ".xlsx", ".docx", ".tar", ".zip", ".rar");
	const LimitAttach = (form, file) => """),format.raw/*1114.38*/("""{"""),format.raw/*1114.39*/("""
		"""),format.raw/*1115.3*/("""let allowSubmit = false;
		if (!file) return;
		while (file.indexOf("\\") != -1)"""),format.raw/*1117.35*/("""{"""),format.raw/*1117.36*/("""
			"""),format.raw/*1118.4*/("""file = file.slice(file.indexOf("\\") + 1);
		"""),format.raw/*1119.3*/("""}"""),format.raw/*1119.4*/("""
		"""),format.raw/*1120.3*/("""let extencion = file.slice(file.lastIndexOf(".")).toLowerCase();
		for (var i = 0; i < extArray.length; i++) """),format.raw/*1121.45*/("""{"""),format.raw/*1121.46*/("""
			"""),format.raw/*1122.4*/("""if (extArray[i] == extencion) """),format.raw/*1122.34*/("""{"""),format.raw/*1122.35*/(""" """),format.raw/*1122.36*/("""allowSubmit = true; break; """),format.raw/*1122.63*/("""}"""),format.raw/*1122.64*/("""
		"""),format.raw/*1123.3*/("""}"""),format.raw/*1123.4*/("""
		"""),format.raw/*1124.3*/("""if (allowSubmit) """),format.raw/*1124.20*/("""{"""),format.raw/*1124.21*/("""
			"""),format.raw/*1125.4*/("""var file = $("#docAdjunto")[0].files[0];
			var fileSize = file.size; //tamaño del archivo
			if(fileSize>100000000)"""),format.raw/*1127.26*/("""{"""),format.raw/*1127.27*/("""
				"""),format.raw/*1128.5*/("""alert("Se permiten únicamente archivos que no superen los 100 MB,"
				+" el que se intenta subir pesa: "+Math.round(file.size/10)/100+" KB");
				form.docAdjunto.value="";
			"""),format.raw/*1131.4*/("""}"""),format.raw/*1131.5*/("""else"""),format.raw/*1131.9*/("""{"""),format.raw/*1131.10*/("""
				"""),format.raw/*1132.5*/("""alert("Documento subido con éxito");
				$("#txtBtn").text("Cambiar Documento");
			"""),format.raw/*1134.4*/("""}"""),format.raw/*1134.5*/("""
		"""),format.raw/*1135.3*/("""}"""),format.raw/*1135.4*/("""else"""),format.raw/*1135.8*/("""{"""),format.raw/*1135.9*/("""
			"""),format.raw/*1136.4*/("""alert("Se permiten únicamente archivos con la extención: " 
			+ (extArray.join("  ")) + "\nPor favor, seleccione otro archivo "
			+ "e intente de nuevo.");
			form.docAdjunto.value="";
		"""),format.raw/*1140.3*/("""}"""),format.raw/*1140.4*/("""
	"""),format.raw/*1141.2*/("""}"""),format.raw/*1141.3*/("""
	
	
	"""),format.raw/*1144.2*/("""const aplicarTasaGlobal = () => """),format.raw/*1144.34*/("""{"""),format.raw/*1144.35*/("""
		"""),format.raw/*1145.3*/("""$("#tablaPrincipal").dataTable().fnDestroy();
		$(".tasaGlobal").each(function() """),format.raw/*1146.36*/("""{"""),format.raw/*1146.37*/("""
			 """),format.raw/*1147.5*/("""$(this).val($("#tasaGlobal").val());
		"""),format.raw/*1148.3*/("""}"""),format.raw/*1148.4*/(""");
		let tasa = $("#tasaGlobal").val();
		tasa = tasa.replace(/,/g,'').replace(/%/g,'').replace(/ /g,'');
		let tabla = document.getElementById("tablaPrincipal");
		for(i=2; i<tabla.rows.length-3; i++)"""),format.raw/*1152.39*/("""{"""),format.raw/*1152.40*/("""
			"""),format.raw/*1153.4*/("""let id_equipo = tabla.rows[i].cells[20].innerHTML;
			let nroDecimal = tabla.rows[i].cells[21].innerHTML;
			let prepos = $("#puVentaRepos_"+id_equipo).val().replace(/,/g,'');
			$("#puArriendo_"+id_equipo).val(formatStandar(parseFloat(prepos*tasa/100),nroDecimal));
			calculaLinea(id_equipo,nroDecimal);
		"""),format.raw/*1158.3*/("""}"""),format.raw/*1158.4*/("""
		"""),format.raw/*1159.3*/("""sumaTotales();
		document.getElementById('selSucursal').disabled = true;
		document.getElementById('selComercial').disabled = true;
		
		$('#tablaPrincipal').DataTable("""),format.raw/*1163.34*/("""{"""),format.raw/*1163.35*/("""
	    	"""),format.raw/*1164.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[15, 50, 100, 200, -1], [15, 50, 100, 200, "All"]],
	    	"order": [[ 0, "asc" ],[ 2, "asc" ]],
	    	"language": """),format.raw/*1167.19*/("""{"""),format.raw/*1167.20*/("""
	        	"""),format.raw/*1168.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*1169.10*/("""}"""),format.raw/*1169.11*/("""
		"""),format.raw/*1170.3*/("""}"""),format.raw/*1170.4*/(""");
	"""),format.raw/*1171.2*/("""}"""),format.raw/*1171.3*/("""
	
	"""),format.raw/*1173.2*/("""const aplicarFactorGlobal = () => """),format.raw/*1173.36*/("""{"""),format.raw/*1173.37*/("""
		"""),format.raw/*1174.3*/("""$("#tablaPrincipal").dataTable().fnDestroy();
		let factorGlobal = $("#factorGlobal").val();
		factorGlobal = factorGlobal.replace(/,/g,'');
		let tabla = document.getElementById("tablaPrincipal");
		for(i=2; i<tabla.rows.length-3; i++)"""),format.raw/*1178.39*/("""{"""),format.raw/*1178.40*/("""
			"""),format.raw/*1179.4*/("""let id_equipo = tabla.rows[i].cells[20].innerHTML;
			let nroDecimal = tabla.rows[i].cells[21].innerHTML;
			let pArr = $("#puArriendo_"+id_equipo).val().replace(/,/g,'');
			let prepos = $("#puVentaRepos_"+id_equipo).val().replace(/,/g,'');
			$("#puArriendo_"+id_equipo).val(formatStandar(parseFloat(pArr*factorGlobal),nroDecimal));
			if(parseFloat(prepos)>0)"""),format.raw/*1184.28*/("""{"""),format.raw/*1184.29*/("""
				"""),format.raw/*1185.5*/("""$("#tasaArr_"+id_equipo).val(formatStandar(parseFloat(pArr*factorGlobal/prepos*100),2) + "%");
			"""),format.raw/*1186.4*/("""}"""),format.raw/*1186.5*/("""
			"""),format.raw/*1187.4*/("""calculaLinea(id_equipo,nroDecimal);
		"""),format.raw/*1188.3*/("""}"""),format.raw/*1188.4*/("""
		"""),format.raw/*1189.3*/("""sumaTotales();
		document.getElementById('selSucursal').disabled = true;
		document.getElementById('selComercial').disabled = true;
		$('#tablaPrincipal').DataTable("""),format.raw/*1192.34*/("""{"""),format.raw/*1192.35*/("""
	    	"""),format.raw/*1193.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[15, 50, 100, 200, -1], [15, 50, 100, 200, "All"]],
	    	"order": [[ 0, "asc" ],[ 2, "asc" ]],
	    	"language": """),format.raw/*1196.19*/("""{"""),format.raw/*1196.20*/("""
	        	"""),format.raw/*1197.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*1198.10*/("""}"""),format.raw/*1198.11*/("""
		"""),format.raw/*1199.3*/("""}"""),format.raw/*1199.4*/(""");
	"""),format.raw/*1200.2*/("""}"""),format.raw/*1200.3*/("""
	
	"""),format.raw/*1202.2*/("""const aplicarFactorGlobalRepos = () => """),format.raw/*1202.41*/("""{"""),format.raw/*1202.42*/("""
		"""),format.raw/*1203.3*/("""$("#tablaPrincipal").dataTable().fnDestroy();
		let factorGlobalRepos = $("#factorGlobalRepos").val();
		factorGlobalRepos = factorGlobalRepos.replace(/,/g,'');
		let tabla = document.getElementById("tablaPrincipal");
		for(i=2; i<tabla.rows.length-3; i++)"""),format.raw/*1207.39*/("""{"""),format.raw/*1207.40*/("""
			"""),format.raw/*1208.4*/("""let id_equipo = tabla.rows[i].cells[20].innerHTML;
			let nroDecimal = tabla.rows[i].cells[21].innerHTML;
			let pArr = $("#puArriendo_"+id_equipo).val().replace(/,/g,'');
			let prepos = $("#puVentaRepos_"+id_equipo).val().replace(/,/g,'');
			prepos = prepos * factorGlobalRepos;
			$("#puVentaRepos_"+id_equipo).val(formatStandar(parseFloat(prepos),nroDecimal));
			if(parseFloat(prepos*factorGlobalRepos)>0)"""),format.raw/*1214.46*/("""{"""),format.raw/*1214.47*/("""
				"""),format.raw/*1215.5*/("""$("#tasaArr_"+id_equipo).val(formatStandar(parseFloat(pArr/prepos*100),2) + "%");
			"""),format.raw/*1216.4*/("""}"""),format.raw/*1216.5*/("""
			"""),format.raw/*1217.4*/("""calculaLinea(id_equipo,nroDecimal);
		"""),format.raw/*1218.3*/("""}"""),format.raw/*1218.4*/("""
		"""),format.raw/*1219.3*/("""sumaTotales();
		document.getElementById('selSucursal').disabled = true;
		document.getElementById('selComercial').disabled = true;
		$('#tablaPrincipal').DataTable("""),format.raw/*1222.34*/("""{"""),format.raw/*1222.35*/("""
	    	"""),format.raw/*1223.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[15, 50, 100, 200, -1], [15, 50, 100, 200, "All"]],
	    	"order": [[ 0, "asc" ],[ 2, "asc" ]],
	    	"language": """),format.raw/*1226.19*/("""{"""),format.raw/*1226.20*/("""
	        	"""),format.raw/*1227.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*1228.10*/("""}"""),format.raw/*1228.11*/("""
		"""),format.raw/*1229.3*/("""}"""),format.raw/*1229.4*/(""");
	"""),format.raw/*1230.2*/("""}"""),format.raw/*1230.3*/("""
	
	"""),format.raw/*1232.2*/("""const actualizaComerciales = (id_sucursal) =>"""),format.raw/*1232.47*/("""{"""),format.raw/*1232.48*/("""
		"""),format.raw/*1233.3*/("""var formData = new FormData();
		formData.append('id_sucursal',id_sucursal);
        $.ajax("""),format.raw/*1235.16*/("""{"""),format.raw/*1235.17*/("""
            """),format.raw/*1236.13*/("""url: "/actualizaComercialesAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*1243.36*/("""{"""),format.raw/*1243.37*/("""
				"""),format.raw/*1244.5*/("""document.getElementById('actualComerciales').innerHTML = respuesta;
	     	"""),format.raw/*1245.8*/("""}"""),format.raw/*1245.9*/("""
        """),format.raw/*1246.9*/("""}"""),format.raw/*1246.10*/(""");	
	"""),format.raw/*1247.2*/("""}"""),format.raw/*1247.3*/("""
	
	"""),format.raw/*1249.2*/("""const actualizaPrecios = (id_sucursal) =>"""),format.raw/*1249.43*/("""{"""),format.raw/*1249.44*/("""
		"""),format.raw/*1250.3*/("""document.getElementById('bloquear2').style.display="block";
			var formData = new FormData();
			formData.append('id_sucursal',id_sucursal);
	        $.ajax("""),format.raw/*1253.17*/("""{"""),format.raw/*1253.18*/("""
	            """),format.raw/*1254.14*/("""url: "/actualizaPreciosAjax/",
	            type: "POST",
	            method: "POST",
	            data: formData,
	            cache: false,
	            contentType: false,
		     	processData: false,
		     	success: function(rs)"""),format.raw/*1261.30*/("""{"""),format.raw/*1261.31*/("""
			
					"""),format.raw/*1263.6*/("""$("#tablaPrincipal").dataTable().fnDestroy();
					
					let tabla = document.getElementById("tablaPrincipal");
					
					for(i=2; i<tabla.rows.length-3; i++)"""),format.raw/*1267.42*/("""{"""),format.raw/*1267.43*/("""
						"""),format.raw/*1268.7*/("""let id_equipo = tabla.rows[i].cells[20].innerHTML;
						let nroDecimal = tabla.rows[i].cells[21].innerHTML;
						
						for(let i=0; i<rs.length; i++)"""),format.raw/*1271.37*/("""{"""),format.raw/*1271.38*/("""
							"""),format.raw/*1272.8*/("""if(rs[i].id_equipo == id_equipo)"""),format.raw/*1272.40*/("""{"""),format.raw/*1272.41*/("""
								"""),format.raw/*1273.9*/("""let puVentaRepos = rs[i].precioVenta;
								let puArriendo = rs[i].precioArriendo;
								let tasaArriendo = 0
								if(parseFloat(puVentaRepos) > 0)"""),format.raw/*1276.41*/("""{"""),format.raw/*1276.42*/("""
									"""),format.raw/*1277.10*/("""tasaArriendo = parseFloat(puArriendo)/ parseFloat(puVentaRepos);
								"""),format.raw/*1278.9*/("""}"""),format.raw/*1278.10*/("""
								"""),format.raw/*1279.9*/("""$("#hiddenpuVentaRepos_"+id_equipo).val(puVentaRepos);
								$("#puVentaRepos_"+id_equipo).val(formatStandar(parseFloat(puVentaRepos),nroDecimal));
								$("#hiddenpuArriendo_"+id_equipo).val(puArriendo);
								$("#puArriendo_"+id_equipo).val(formatStandar(parseFloat(puArriendo),nroDecimal));
								$("#tasaArr_"+id_equipo).val(formatPorcentaje(parseFloat(tasaArriendo) * 100));
								calculaLinea(id_equipo,nroDecimal);
							"""),format.raw/*1285.8*/("""}"""),format.raw/*1285.9*/("""
						"""),format.raw/*1286.7*/("""}"""),format.raw/*1286.8*/("""
						
					"""),format.raw/*1288.6*/("""}"""),format.raw/*1288.7*/("""

					"""),format.raw/*1290.6*/("""sumaTotales();
					$('#tablaPrincipal').DataTable("""),format.raw/*1291.37*/("""{"""),format.raw/*1291.38*/("""
				    	"""),format.raw/*1292.10*/(""""fixedHeader": true,
				    	"lengthMenu": [[15, 50, 100, 200, -1], [15, 50, 100, 200, "All"]],
				    	"order": [[ 0, "asc" ],[ 2, "asc" ]],
				    	"language": """),format.raw/*1295.22*/("""{"""),format.raw/*1295.23*/("""
				        	"""),format.raw/*1296.14*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
				        """),format.raw/*1297.13*/("""}"""),format.raw/*1297.14*/("""
					"""),format.raw/*1298.6*/("""}"""),format.raw/*1298.7*/(""");
					document.getElementById('bloquear2').style.display="none";
				"""),format.raw/*1300.5*/("""}"""),format.raw/*1300.6*/("""
	        """),format.raw/*1301.10*/("""}"""),format.raw/*1301.11*/(""");	
	"""),format.raw/*1302.2*/("""}"""),format.raw/*1302.3*/("""
	
	
	"""),format.raw/*1305.2*/("""let jsonDetalle = """"),_display_(/*1305.22*/json),format.raw/*1305.26*/("""";
	jsonDetalle = jsonDetalle.replace(/&quot;/g,'"');
	let arrayDetalle = JSON.parse(jsonDetalle);
	
	let arrayUnTiempo = [];
	let arrAux = [];
	"""),_display_(/*1311.3*/for(lista <- listUnTiempo) yield /*1311.29*/{_display_(Seq[Any](format.raw/*1311.30*/("""
		"""),format.raw/*1312.3*/("""arrAux.push(""""),_display_(/*1312.17*/lista/*1312.22*/.id),format.raw/*1312.25*/("""");
		arrAux.push(""""),_display_(/*1313.17*/lista/*1313.22*/.nombre),format.raw/*1313.29*/("""");
		arrayUnTiempo.push(arrAux);
		arrAux = [];
	""")))}),format.raw/*1316.3*/("""
	
	"""),format.raw/*1318.2*/("""let escondeLosM2 = '"""),_display_(/*1318.23*/mapPermiso/*1318.33*/.get("parametro.escondeLosM2")),format.raw/*1318.63*/("""';
	
	const agregaNuevoEquipo = () =>"""),format.raw/*1320.33*/("""{"""),format.raw/*1320.34*/("""
		"""),format.raw/*1321.3*/("""$("#tablaPrincipal").dataTable().fnDestroy();
		let array = [];
		for(var i=0; i < arrayDetalle.length; i++) """),format.raw/*1323.46*/("""{"""),format.raw/*1323.47*/("""
				"""),format.raw/*1324.5*/("""let flag = true;
				$(".idEquipo").each(function() """),format.raw/*1325.36*/("""{"""),format.raw/*1325.37*/("""
					"""),format.raw/*1326.6*/("""if(arrayDetalle[i][0] == $(this).val())"""),format.raw/*1326.45*/("""{"""),format.raw/*1326.46*/("""
						"""),format.raw/*1327.7*/("""flag = false;
					"""),format.raw/*1328.6*/("""}"""),format.raw/*1328.7*/("""
				"""),format.raw/*1329.5*/("""}"""),format.raw/*1329.6*/(""");
				if(flag)"""),format.raw/*1330.13*/("""{"""),format.raw/*1330.14*/("""
						"""),format.raw/*1331.7*/("""array.push(arrayDetalle[i]);
				"""),format.raw/*1332.5*/("""}"""),format.raw/*1332.6*/("""
		"""),format.raw/*1333.3*/("""}"""),format.raw/*1333.4*/("""
		"""),format.raw/*1334.3*/("""$('#tablaPrincipal').DataTable("""),format.raw/*1334.34*/("""{"""),format.raw/*1334.35*/("""
			    	"""),format.raw/*1335.9*/(""""fixedHeader": true,
			    	"lengthMenu": [[15, 50, 100, 200, -1], [15, 50, 100, 200, "All"]],
			    	"order": [[ 0, "asc" ],[ 2, "asc" ]],
			    	"language": """),format.raw/*1338.21*/("""{"""),format.raw/*1338.22*/("""
			        	"""),format.raw/*1339.13*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
			        """),format.raw/*1340.12*/("""}"""),format.raw/*1340.13*/("""
				"""),format.raw/*1341.5*/("""}"""),format.raw/*1341.6*/(""" """),format.raw/*1341.7*/(""");
		
		let modTable = "<table id=\"tablaSelEquipos\" class=\"table table-sm table-hover table-bordered table-condensed table-fluid\">"
			+ "<thead style=\"background-color: #eeeeee\">"
			+ "<TR><TH style=\"vertical-align: top;\">GRUPO</TH>"
			+ "<TH>CODIGO</TH><TH>EQUIPO</TH><TH>STOCK</TH><TH>SELECT</TH></TR>"
			+ "</thead><tbody>";
		for(var i=0; i < array.length; i++) """),format.raw/*1348.39*/("""{"""),format.raw/*1348.40*/("""
			"""),format.raw/*1349.4*/("""modTable +="<TR><td style=\"text-align:left;\">"+ array[i][23] + "</td>";
			modTable +="<td style=\"text-align:left;\">"+ array[i][1] + "</td>";
			modTable +="<td style=\"text-align:left;\">"+ array[i][2] + "</td>";
			modTable +="<td style=\"text-align:center;\"><div class=\"noprint\">"+ 
							"<a href=\"#\" onclick=\"vistaStockPorEquipo("+array[i][0]+");\" tabindex=\"-1\">"+
							"<kbd style=\"background-color: #73C6B6\">stock</kbd>"+
							"</a></div></td>";
			modTable +="<td  style=\"text-align:center;\">"+
							"<input type=\"checkbox\" id=\"sel_"+array[i][0]+"\" value =\"0\" onchange=\"seleccionaPorEq("+array[i][0]+",value)\"></td>";
			modTable +="</TR>";
		"""),format.raw/*1359.3*/("""}"""),format.raw/*1359.4*/("""
		"""),format.raw/*1360.3*/("""modTable +="</tbody></table>";
		
		array = [];
		document.getElementById('vistalistadoEquipos').innerHTML = modTable;
		
		$('#tablaSelEquipos').DataTable("""),format.raw/*1365.35*/("""{"""),format.raw/*1365.36*/("""
		    	"""),format.raw/*1366.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 2, "asc" ]],
		    	"language": """),format.raw/*1369.20*/("""{"""),format.raw/*1369.21*/("""
		        	"""),format.raw/*1370.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*1371.11*/("""}"""),format.raw/*1371.12*/("""
		  """),format.raw/*1372.5*/("""}"""),format.raw/*1372.6*/(""" """),format.raw/*1372.7*/(""");
		
		$('#modalListaEquipos').modal('show');
						

	"""),format.raw/*1377.2*/("""}"""),format.raw/*1377.3*/("""
	
	"""),format.raw/*1379.2*/("""let equipSelecionados="";
	const seleccionaPorEq = (id_equipo, valor) => """),format.raw/*1380.48*/("""{"""),format.raw/*1380.49*/("""
		"""),format.raw/*1381.3*/("""if(valor == 0) """),format.raw/*1381.18*/("""{"""),format.raw/*1381.19*/("""
			"""),format.raw/*1382.4*/("""document.getElementById("sel_"+id_equipo).value = "1";
			equipSelecionados += id_equipo + ",";
		"""),format.raw/*1384.3*/("""}"""),format.raw/*1384.4*/("""else"""),format.raw/*1384.8*/("""{"""),format.raw/*1384.9*/("""
			"""),format.raw/*1385.4*/("""document.getElementById("sel_"+id_equipo).value = "0";
			let aux = ""+id_equipo + ",";
			equipSelecionados = equipSelecionados.replace(aux,"");
		"""),format.raw/*1388.3*/("""}"""),format.raw/*1388.4*/("""
	"""),format.raw/*1389.2*/("""}"""),format.raw/*1389.3*/("""
	
	"""),format.raw/*1391.2*/("""const agregarEquipos = (id_equipo, valor) => """),format.raw/*1391.47*/("""{"""),format.raw/*1391.48*/("""
		
		"""),format.raw/*1393.3*/("""if(equipSelecionados.length>0) """),format.raw/*1393.34*/("""{"""),format.raw/*1393.35*/("""
			
			"""),format.raw/*1395.4*/("""equipSelecionados = equipSelecionados.substring(0,equipSelecionados.length-1);
			let aux = equipSelecionados.split(",");
			equipSelecionados = "";
			
			$("#tablaPrincipal").dataTable().fnDestroy();
			let tableReg = document.getElementById("tablaPrincipal");
			
			for(var i=0; i < arrayDetalle.length; i++) """),format.raw/*1402.47*/("""{"""),format.raw/*1402.48*/("""
			
				"""),format.raw/*1404.5*/("""for(var j=0; j<aux.length; j++)"""),format.raw/*1404.36*/("""{"""),format.raw/*1404.37*/("""
					
					"""),format.raw/*1406.6*/("""if(arrayDetalle[i][0] == aux[j])"""),format.raw/*1406.38*/("""{"""),format.raw/*1406.39*/("""
						
						"""),format.raw/*1408.7*/("""let row = tableReg.insertRow(2);
						
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
								if(arrayDetalle[i][11] == 0)"""),format.raw/*1449.37*/("""{"""),format.raw/*1449.38*/("""
									"""),format.raw/*1450.10*/("""x6 += "<input type=\"checkbox\" id=\"checkbox_"+arrayDetalle[i][0]+"\" onchange=\"checkVenta('"+arrayDetalle[i][0]+"'); calculaLinea('"+arrayDetalle[i][0]+"', '"+arrayDetalle[i][20]+"');\">";
								"""),format.raw/*1451.9*/("""}"""),format.raw/*1451.10*/("""else"""),format.raw/*1451.14*/("""{"""),format.raw/*1451.15*/("""
									"""),format.raw/*1452.10*/("""x6 += "<input type=\"checkbox\" id=\"checkbox_"+arrayDetalle[i][0]+"\" onchange=\"checkVenta('"+arrayDetalle[i][0]+"'); calculaLinea('"+arrayDetalle[i][0]+"', '"+arrayDetalle[i][20]+"');\" checked>";
								"""),format.raw/*1453.9*/("""}"""),format.raw/*1453.10*/("""
						"""),format.raw/*1454.7*/("""cell.innerHTML = x6;
						
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
											" onchange=\"if(value=='') """),format.raw/*1471.39*/("""{"""),format.raw/*1471.40*/("""value=0;"""),format.raw/*1471.48*/("""}"""),format.raw/*1471.49*/(""" """),format.raw/*1471.50*/("""else """),format.raw/*1471.55*/("""{"""),format.raw/*1471.56*/("""calculaTasa(1,'"+arrayDetalle[i][0]+"', '"+arrayDetalle[i][20]+"'); calculaLinea('"+arrayDetalle[i][0]+"', '"+arrayDetalle[i][20]+"');"""),format.raw/*1471.190*/("""}"""),format.raw/*1471.191*/("""\">";
						
						cell = row.insertCell(9);
						cell.style.textAlign = "center";
						let x9 = "<input type=\"hidden\" id=\"id_unidadTiempo_"+arrayDetalle[i][0]+"\" name=\"id_unidadTiempo[]\" value=\""+arrayDetalle[i][9]+"\">"+
									"<select class=\"custom-select\"  style=\"width: 80px;\""+
											"onchange=\"$('#id_unidadTiempo_"+arrayDetalle[i][0]+"').val(value);\">"+
											"<option value=\""+arrayDetalle[i][9]+"\">"+arrayDetalle[i][6]+"</option>";
									for(var k=0; k<arrayUnTiempo.length; k++)"""),format.raw/*1479.51*/("""{"""),format.raw/*1479.52*/("""
										"""),format.raw/*1480.11*/("""x9 += "<option value=\""+arrayUnTiempo[k][0]+"\">"+arrayUnTiempo[k][1]+"</option>";
									"""),format.raw/*1481.10*/("""}"""),format.raw/*1481.11*/("""	
									"""),format.raw/*1482.10*/("""x9 += "</select>";
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
										" onchange=\"if(value=='') """),format.raw/*1494.38*/("""{"""),format.raw/*1494.39*/("""value='0.00 %';"""),format.raw/*1494.54*/("""}"""),format.raw/*1494.55*/(""" """),format.raw/*1494.56*/("""else """),format.raw/*1494.61*/("""{"""),format.raw/*1494.62*/("""calculaTasa(2,'"+arrayDetalle[i][0]+"', '"+arrayDetalle[i][20]+"'); calculaLinea('"+arrayDetalle[i][0]+"', '"+arrayDetalle[i][20]+"');"""),format.raw/*1494.196*/("""}"""),format.raw/*1494.197*/("""\">";
											
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
											" onchange=\"if(value=='') """),format.raw/*1507.39*/("""{"""),format.raw/*1507.40*/("""value=0;"""),format.raw/*1507.48*/("""}"""),format.raw/*1507.49*/(""" """),format.raw/*1507.50*/("""else """),format.raw/*1507.55*/("""{"""),format.raw/*1507.56*/("""calculaTasa(1,'"+arrayDetalle[i][0]+"', '"+arrayDetalle[i][20]+"'); calculaLinea('"+arrayDetalle[i][0]+"', '"+arrayDetalle[i][20]+"');"""),format.raw/*1507.190*/("""}"""),format.raw/*1507.191*/("""\">";
						
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
						if(escondeLosM2 != null && escondeLosM2 == 1)"""),format.raw/*1551.52*/("""{"""),format.raw/*1551.53*/("""
							"""),format.raw/*1552.8*/("""cell.style.display = "none";
						"""),format.raw/*1553.7*/("""}"""),format.raw/*1553.8*/("""
						
						"""),format.raw/*1555.7*/("""cell = row.insertCell(18);
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
					"""),format.raw/*1574.6*/("""}"""),format.raw/*1574.7*/("""
				"""),format.raw/*1575.5*/("""}"""),format.raw/*1575.6*/("""
			"""),format.raw/*1576.4*/("""}"""),format.raw/*1576.5*/("""
		"""),format.raw/*1577.3*/("""}"""),format.raw/*1577.4*/("""
		"""),format.raw/*1578.3*/("""$('#tablaPrincipal').DataTable("""),format.raw/*1578.34*/("""{"""),format.raw/*1578.35*/("""
	    	"""),format.raw/*1579.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[15, 50, 100, 200, -1], [15, 50, 100, 200, "All"]],
	    	"order": [[ 0, "asc" ],[ 2, "asc" ]],
	    	"language": """),format.raw/*1582.19*/("""{"""),format.raw/*1582.20*/("""
	        	"""),format.raw/*1583.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*1584.10*/("""}"""),format.raw/*1584.11*/("""
		"""),format.raw/*1585.3*/("""}"""),format.raw/*1585.4*/(""" """),format.raw/*1585.5*/(""");
	"""),format.raw/*1586.2*/("""}"""),format.raw/*1586.3*/("""
	


	
"""),format.raw/*1591.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,formCotiza:forms.FormCotiza,listClientes:List[tables.Cliente],listProyectos:List[tables.Proyecto],lista:List[List[String]],numDecParaTot:String,listRegiones:List[tables.Regiones],listUnTiempo:List[tables.UnidadTiempo],sucursal:tables.Sucursal,comercial:tables.Comercial,listSucursal:List[tables.Sucursal],listComercial:List[tables.Comercial],json:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,formCotiza,listClientes,listProyectos,lista,numDecParaTot,listRegiones,listUnTiempo,sucursal,comercial,listSucursal,listComercial,json)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,forms.FormCotiza,List[tables.Cliente],List[tables.Proyecto],List[List[String]],String,List[tables.Regiones],List[tables.UnidadTiempo],tables.Sucursal,tables.Comercial,List[tables.Sucursal],List[tables.Comercial],String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,formCotiza,listClientes,listProyectos,lista,numDecParaTot,listRegiones,listUnTiempo,sucursal,comercial,listSucursal,listComercial,json) => apply(mapDiccionario,mapPermiso,userMnu,formCotiza,listClientes,listProyectos,lista,numDecParaTot,listRegiones,listUnTiempo,sucursal,comercial,listSucursal,listComercial,json)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizarSelect/cotizaModificaSel.scala.html
                  HASH: 7147c8d4803c8939a67521024ed199cc8a1a3199
                  MATRIX: 1242->1|1809->475|1837->478|1853->486|1892->488|1921->491|1990->539|2018->540|2168->664|2213->688|2243->691|2301->723|2369->770|2399->773|2479->825|2508->826|2540->831|3413->1677|3441->1678|3471->1681|3577->1761|3646->1809|3676->1812|3758->1866|3787->1867|3819->1872|4578->2604|4606->2605|4636->2608|4873->2819|4949->2874|4980->2878|5366->3237|5385->3247|5421->3261|5747->3560|5766->3570|5798->3581|5960->3715|5989->3716|6024->3724|6043->3734|6075->3745|6105->3747|6134->3748|6166->3752|6195->3753|6245->3774|6275->3775|6716->4189|6735->4199|6766->4209|6818->4234|6837->4244|6868->4254|7126->4485|7149->4499|7181->4510|7210->4511|7347->4620|7367->4630|7400->4641|7499->4712|7540->4714|7608->4754|7648->4755|7690->4768|7812->4862|7832->4872|7865->4883|7928->4926|7968->4927|8010->4940|8132->5034|8152->5044|8185->5055|8356->5194|8392->5210|8432->5211|8473->5223|8593->5315|8613->5325|8646->5336|8813->5471|8852->5481|9161->5762|9202->5764|9271->5805|9311->5806|9354->5820|9480->5918|9500->5928|9534->5940|9618->6004|9658->6005|9701->6019|9827->6117|9847->6127|9881->6139|10067->6293|10104->6310|10144->6311|10186->6324|10312->6422|10332->6432|10366->6444|10551->6597|10590->6607|10906->6895|10926->6905|10960->6917|11059->6988|11100->6990|11141->7002|11266->7099|11286->7109|11321->7122|11380->7161|11420->7162|11461->7174|11586->7271|11606->7281|11641->7294|11801->7422|11840->7432|12070->7634|12110->7635|12151->7647|12218->7694|12258->7695|12299->7707|12373->7749|12416->7763|12680->7999|12720->8000|12760->8011|12797->8028|12837->8029|12877->8040|12946->8081|12966->8091|13002->8105|13124->8195|13161->8204|13378->8393|13396->8401|13426->8409|13690->8645|13730->8646|13815->8703|13855->8704|13897->8717|14121->8913|14139->8921|14169->8929|14200->8932|14218->8940|14252->8952|14304->8976|14347->9002|14387->9003|14431->9018|14475->9034|14490->9039|14520->9047|14551->9050|14566->9055|14600->9067|14656->9091|14698->9104|14744->9130|14784->9131|14826->9144|15021->9311|15039->9319|15069->9327|15100->9330|15118->9338|15152->9350|15204->9374|15247->9400|15287->9401|15331->9416|15375->9432|15390->9437|15420->9445|15451->9448|15466->9453|15500->9465|15556->9489|15598->9502|15652->9524|15688->9540|15728->9541|15769->9553|15881->9637|15899->9645|15933->9657|16000->9692|16039->9702|16457->10092|16477->10102|16513->10116|16780->10355|16799->10364|16829->10372|17100->10615|17140->10616|17182->10629|17351->10770|17370->10779|17400->10787|17431->10790|17450->10799|17489->10816|17541->10840|17585->10867|17625->10868|17669->10883|17713->10899|17728->10904|17758->10912|17789->10915|17804->10920|17843->10937|17899->10961|17941->10974|17987->11000|18027->11001|18069->11014|18183->11100|18202->11109|18241->11126|18310->11163|18350->11174|18553->11349|18573->11359|18613->11376|18710->11445|18751->11447|18790->11457|18999->11638|19023->11652|19059->11666|19205->11784|19225->11794|19260->11807|19371->11873|19407->11881|20518->12963|20548->12964|20592->12979|20622->12980|21766->14096|21790->14110|21829->14126|22206->14474|22236->14475|22280->14490|22310->14491|23825->15977|23855->15978|23899->15993|23929->15994|25765->17802|25789->17816|25822->17827|25877->17854|25901->17868|25934->17879|25999->17916|26023->17930|26056->17941|26177->18034|26201->18048|26239->18064|26447->18243|26489->18245|26528->18255|26602->18309|26642->18310|26681->18320|26738->18345|26784->18363|27040->18592|27077->18612|27117->18613|27154->18622|27238->18678|27254->18684|27284->18692|27323->18702|27471->18822|27487->18828|27516->18835|27605->18896|27621->18902|27650->18909|27743->18974|27759->18980|27788->18987|27834->19005|27850->19011|27879->19018|27919->19029|28048->19130|28064->19136|28093->19143|28128->19149|28145->19155|28175->19162|28340->19299|28356->19305|28385->19312|28571->19470|28587->19476|28616->19483|28747->19586|28763->19592|28792->19599|28823->19602|28839->19608|28869->19616|29044->19763|29060->19769|29089->19776|29138->19797|29154->19803|29184->19811|29489->20088|29505->20094|29534->20101|29567->20106|29583->20112|29613->20120|29764->20243|29780->20249|29809->20256|29848->20267|29864->20273|29895->20281|29967->20325|30007->20326|30048->20338|30113->20375|30129->20381|30158->20388|30211->20413|30227->20419|30257->20426|30305->20445|30322->20451|30352->20458|30386->20463|30403->20469|30434->20477|30475->20498|30515->20499|30556->20511|30621->20548|30637->20554|30666->20561|30719->20586|30735->20592|30765->20599|30813->20618|30830->20624|30860->20631|30894->20636|30911->20642|30942->20650|30999->20675|31038->20685|31113->20732|31129->20738|31158->20745|31293->20852|31309->20858|31338->20865|31369->20868|31385->20874|31415->20882|31590->21029|31606->21035|31635->21042|31684->21063|31700->21069|31729->21076|31863->21182|31879->21188|31909->21196|32066->21324|32096->21325|32133->21333|32163->21334|32193->21335|32227->21340|32257->21341|32301->21357|32317->21363|32346->21370|32379->21375|32395->21381|32426->21389|32474->21408|32491->21414|32521->21421|32555->21426|32572->21432|32603->21440|32636->21443|32667->21444|32806->21555|32822->21561|32851->21568|32915->21603|32932->21609|32962->21616|33097->21723|33113->21729|33142->21736|33214->21780|33230->21786|33259->21793|33290->21796|33306->21802|33335->21809|33385->21831|33428->21857|33468->21858|33510->21871|33554->21887|33569->21892|33594->21895|33625->21898|33640->21903|33669->21910|33723->21932|33763->21943|33885->22037|33901->22043|33930->22050|33961->22053|33977->22059|34007->22067|34154->22186|34170->22192|34199->22199|34248->22220|34264->22226|34294->22234|34587->22498|34617->22499|34661->22514|34691->22515|34721->22516|34755->22521|34785->22522|34829->22538|34845->22544|34874->22551|34907->22556|34924->22562|34955->22570|35003->22589|35020->22595|35050->22602|35084->22607|35101->22613|35132->22621|35165->22624|35196->22625|35341->22742|35357->22748|35386->22755|35417->22758|35433->22764|35463->22772|35633->22914|35649->22920|35678->22927|35727->22948|35743->22954|35772->22961|35906->23067|35922->23073|35952->23081|36109->23209|36139->23210|36176->23218|36206->23219|36236->23220|36270->23225|36300->23226|36344->23242|36360->23248|36389->23255|36422->23260|36438->23266|36469->23274|36517->23293|36534->23299|36564->23306|36598->23311|36615->23317|36646->23325|36679->23328|36710->23329|36856->23447|36872->23453|36901->23460|36932->23463|36948->23469|36978->23477|37140->23611|37156->23617|37185->23624|37234->23645|37250->23651|37280->23659|37406->23757|37422->23763|37451->23770|37678->23969|37694->23975|37723->23982|37756->23987|37772->23993|37802->24001|37950->24121|37966->24127|37995->24134|38026->24137|38042->24143|38073->24151|38177->24227|38193->24233|38222->24240|38253->24243|38269->24249|38300->24257|38404->24333|38420->24339|38449->24346|38480->24349|38496->24355|38527->24363|38642->24450|38658->24456|38687->24463|38718->24466|38734->24472|38764->24480|38925->24612|38967->24614|39007->24625|39107->24697|39123->24703|39152->24710|39183->24713|39200->24719|39231->24727|39271->24747|39311->24748|39351->24759|39434->24814|39450->24820|39479->24827|39510->24830|39526->24836|39556->24844|39604->24860|39643->24870|39709->24908|39725->24914|39754->24921|39785->24924|39801->24930|39831->24938|39912->24991|39928->24997|39957->25004|39988->25007|40004->25013|40034->25021|40118->25077|40134->25083|40163->25090|40194->25093|40210->25099|40239->25106|40327->25166|40343->25172|40372->25179|40403->25182|40419->25188|40449->25196|40520->25235|40555->25242|41117->25775|41159->25777|41198->25787|41264->25833|41304->25834|41343->25844|41394->25863|41431->25872|41852->26265|41872->26275|41907->26288|41937->26289|42390->26714|42410->26724|42442->26734|42472->26735|42883->27117|42925->27119|42964->27129|43030->27175|43070->27176|43109->27186|43160->27205|43197->27214|43983->27971|44025->27973|44064->27983|44177->28076|44217->28077|44256->28087|44365->28164|44402->28173|46715->30458|46739->30472|46772->30483|46901->30585|46945->30612|46985->30613|47022->30622|47085->30657|47101->30663|47131->30671|47185->30697|47201->30703|47232->30712|47290->30741|47307->30747|47344->30761|47439->30828|47455->30834|47486->30843|47560->30889|47576->30895|47612->30909|47686->30955|47702->30961|47736->30973|47797->31002|47832->31009|49124->32273|49148->32287|49184->32301|49267->32357|49312->32385|49352->32386|49389->32395|49453->32431|49469->32437|49499->32445|49557->32475|49573->32481|49610->32495|49705->32562|49721->32568|49757->32582|49831->32628|49847->32634|49881->32646|49955->32692|49971->32698|50005->32710|50066->32739|50101->32746|52554->35168|52586->35172|52678->35235|52708->35236|52739->35239|52803->35274|52833->35275|52868->35282|53040->35425|53070->35426|53110->35437|53216->35514|53246->35515|53279->35520|53308->35521|53337->35522|53408->35564|53438->35565|53473->35572|53645->35715|53675->35716|53715->35727|53821->35804|53851->35805|53884->35810|53913->35811|53942->35812|54028->35869|54058->35870|54094->35878|54282->36037|54312->36038|54353->36050|54460->36128|54490->36129|54521->36132|54550->36133|54579->36134|54683->36210|54712->36211|54782->36252|54812->36253|54843->36256|54958->36342|54988->36343|55030->36356|55298->36595|55328->36596|55361->36601|55409->36620|55439->36621|55473->36627|55524->36650|55544->36660|55577->36671|55653->36718|55683->36719|55713->36720|55743->36721|55778->36728|55807->36729|55843->36737|55872->36738|55909->36747|55939->36748|55971->36752|56000->36753|56032->36757|56103->36799|56133->36800|56164->36803|56281->36891|56311->36892|56353->36905|56616->37139|56646->37140|56679->37145|56824->37262|56853->37263|56890->37272|56920->37273|56952->37277|56981->37278|57015->37284|57069->37309|57099->37310|57130->37313|57333->37487|57363->37488|57395->37492|57495->37564|57524->37565|57609->37621|57639->37622|57671->37626|57773->37700|57802->37701|57880->37751|57915->37764|58000->37820|58030->37821|58062->37825|58162->37897|58191->37898|58267->37946|58302->37959|58387->38015|58417->38016|58449->38020|58549->38092|58578->38093|58654->38141|58689->38154|58770->38206|58800->38207|58832->38211|58931->38282|58960->38283|59039->38333|59069->38334|59101->38338|59200->38409|59229->38410|59482->38635|59517->38648|59619->38722|59654->38735|59753->38806|59788->38819|59964->38967|59993->38968|60025->38972|60087->39005|60117->39006|60148->39009|60239->39071|60269->39072|60301->39076|60366->39113|60395->39114|60427->39118|60456->39119|60488->39123|60553->39160|60582->39161|60612->39163|60641->39164|60673->39168|60749->39215|60779->39216|60810->39219|60850->39230|60880->39231|60912->39235|61252->39547|61281->39548|61326->39564|61356->39565|61388->39569|61760->39913|61789->39914|61821->39918|61850->39919|61882->39923|62222->40235|62251->40236|62284->40241|62313->40242|62345->40246|62417->40289|62447->40290|62478->40293|63138->40924|63168->40925|63201->40930|63468->41169|63497->41170|63529->41174|63558->41175|63591->41180|63803->41364|63832->41365|63863->41368|64557->42033|64587->42034|64619->42038|64664->42055|64693->42056|64726->42061|64755->42062|64788->42066|64872->42120|64903->42121|64935->42124|65288->42447|65319->42448|65352->42452|65476->42546|65507->42547|65541->42552|65837->42818|65868->42819|65903->42825|65974->42867|66004->42868|66037->42872|66067->42873|66100->42877|66593->43341|66623->43342|66656->43346|66686->43347|66719->43351|66780->43382|66811->43383|66848->43391|67037->43550|67068->43551|67110->43563|67218->43641|67249->43642|67282->43646|67312->43647|67342->43648|67457->43733|67488->43734|67519->43735|67550->43736|67584->43741|67614->43742|67645->43744|67675->43745|67708->43749|67770->43781|67801->43782|67833->43785|67956->43878|67987->43879|68020->43883|68085->43919|68115->43920|68147->43923|68208->43954|68239->43955|68276->43963|68465->44122|68496->44123|68538->44135|68646->44213|68677->44214|68709->44217|68739->44218|68769->44219|69263->44684|69293->44685|69325->44688|69380->44713|69411->44714|69443->44717|69860->45104|69891->45105|69924->45109|70048->45203|70079->45204|70113->45209|70204->45271|70234->45272|70268->45277|70298->45278|70364->45314|70395->45315|70428->45319|70560->45421|70591->45422|70625->45427|70716->45489|70746->45490|70780->45495|70810->45496|70887->45543|70918->45544|70951->45548|71097->45663|71129->45664|71164->45670|71257->45734|71288->45735|71322->45740|71352->45741|71385->45745|71415->45746|71448->45750|71572->45844|71603->45845|71637->45850|71933->46116|71964->46117|71999->46123|72062->46157|72092->46158|72125->46162|72155->46163|72188->46167|72233->46183|72263->46184|72295->46187|72339->46202|72369->46203|72402->46207|72473->46248|72504->46249|72538->46254|72671->46358|72701->46359|72733->46362|72925->46524|72956->46525|72988->46528|73098->46608|73129->46609|73162->46613|73236->46658|73266->46659|73298->46662|73437->46771|73468->46772|73501->46776|73561->46806|73592->46807|73623->46808|73680->46835|73711->46836|73743->46839|73773->46840|73805->46843|73852->46860|73883->46861|73916->46865|74062->46981|74093->46982|74127->46987|74332->47163|74362->47164|74395->47168|74426->47169|74460->47174|74573->47258|74603->47259|74635->47262|74665->47263|74698->47267|74728->47268|74761->47272|74979->47461|75009->47462|75040->47464|75070->47465|75105->47471|75167->47503|75198->47504|75230->47507|75341->47588|75372->47589|75406->47594|75474->47633|75504->47634|75735->47835|75766->47836|75799->47840|76136->48148|76166->48149|76198->48152|76396->48320|76427->48321|76463->48328|76649->48484|76680->48485|76721->48496|76828->48573|76859->48574|76891->48577|76921->48578|76954->48582|76984->48583|77017->48587|77081->48621|77112->48622|77144->48625|77410->48861|77441->48862|77474->48866|77866->49228|77897->49229|77931->49234|78058->49332|78088->49333|78121->49337|78188->49375|78218->49376|78250->49379|78445->49544|78476->49545|78512->49552|78698->49708|78729->49709|78770->49720|78877->49797|78908->49798|78940->49801|78970->49802|79003->49806|79033->49807|79066->49811|79135->49850|79166->49851|79198->49854|79484->50110|79515->50111|79548->50115|79989->50526|80020->50527|80054->50532|80168->50617|80198->50618|80231->50622|80298->50660|80328->50661|80360->50664|80555->50829|80586->50830|80622->50837|80808->50993|80839->50994|80880->51005|80987->51082|81018->51083|81050->51086|81080->51087|81113->51091|81143->51092|81176->51096|81251->51141|81282->51142|81314->51145|81436->51237|81467->51238|81510->51251|81777->51488|81808->51489|81842->51494|81946->51569|81976->51570|82014->51579|82045->51580|82079->51585|82109->51586|82142->51590|82213->51631|82244->51632|82276->51635|82463->51792|82494->51793|82538->51807|82801->52040|82832->52041|82871->52051|83060->52210|83091->52211|83127->52218|83309->52370|83340->52371|83377->52379|83439->52411|83470->52412|83508->52421|83692->52575|83723->52576|83763->52586|83865->52659|83896->52660|83934->52669|84402->53108|84432->53109|84468->53116|84498->53117|84540->53130|84570->53131|84606->53138|84687->53189|84718->53190|84758->53200|84953->53365|84984->53366|85028->53380|85138->53460|85169->53461|85204->53467|85234->53468|85334->53539|85364->53540|85404->53550|85435->53551|85469->53556|85499->53557|85534->53563|85583->53583|85610->53587|85784->53733|85828->53759|85869->53760|85901->53763|85944->53777|85960->53782|85986->53785|86035->53805|86051->53810|86081->53817|86164->53868|86197->53872|86247->53893|86268->53903|86321->53933|86388->53970|86419->53971|86451->53974|86590->54083|86621->54084|86655->54089|86737->54141|86768->54142|86803->54148|86872->54187|86903->54188|86939->54195|86987->54214|87017->54215|87051->54220|87081->54221|87126->54236|87157->54237|87193->54244|87255->54277|87285->54278|87317->54281|87347->54282|87379->54285|87440->54316|87471->54317|87509->54326|87701->54488|87732->54489|87775->54502|87884->54581|87915->54582|87949->54587|87979->54588|88009->54589|88417->54967|88448->54968|88481->54972|89193->55655|89223->55656|89255->55659|89441->55815|89472->55816|89509->55824|89685->55970|89716->55971|89758->55983|89866->56061|89897->56062|89931->56067|89961->56068|89991->56069|90076->56125|90106->56126|90139->56130|90242->56203|90273->56204|90305->56207|90350->56222|90381->56223|90414->56227|90541->56325|90571->56326|90604->56330|90634->56331|90667->56335|90844->56483|90874->56484|90905->56486|90935->56487|90968->56491|91043->56536|91074->56537|91109->56543|91170->56574|91201->56575|91238->56583|91581->56896|91612->56897|91650->56906|91711->56937|91742->56938|91783->56950|91845->56982|91876->56983|91919->56997|94134->59182|94165->59183|94205->59193|94434->59393|94465->59394|94499->59398|94530->59399|94570->59409|94807->59617|94838->59618|94874->59625|95765->60486|95796->60487|95834->60495|95865->60496|95896->60497|95931->60502|95962->60503|96127->60637|96159->60638|96711->61160|96742->61161|96783->61172|96906->61265|96937->61266|96978->61277|97703->61972|97734->61973|97779->61988|97810->61989|97841->61990|97876->61995|97907->61996|98072->62130|98104->62131|98874->62871|98905->62872|98943->62880|98974->62881|99005->62882|99040->62887|99071->62888|99236->63022|99268->63023|101321->65046|101352->65047|101389->65055|101453->65090|101483->65091|101526->65105|102268->65818|102298->65819|102332->65824|102362->65825|102395->65829|102425->65830|102457->65833|102487->65834|102519->65837|102580->65868|102611->65869|102647->65876|102833->66032|102864->66033|102905->66044|103012->66121|103043->66122|103075->66125|103105->66126|103135->66127|103168->66131|103198->66132|103234->66139
                  LINES: 28->1|37->6|39->8|39->8|39->8|41->10|41->10|42->11|43->12|43->12|45->14|46->15|46->15|47->16|48->17|48->17|49->18|65->34|65->34|66->35|70->39|70->39|71->40|72->41|72->41|73->42|88->57|88->57|89->58|94->63|94->63|95->64|101->70|101->70|101->70|108->77|108->77|108->77|111->80|111->80|111->80|111->80|111->80|111->80|111->80|111->80|111->80|111->80|111->80|122->91|122->91|122->91|123->92|123->92|123->92|130->99|130->99|130->99|130->99|132->101|132->101|132->101|133->102|133->102|134->103|134->103|135->104|137->106|137->106|137->106|139->108|139->108|140->109|142->111|142->111|142->111|146->115|147->116|147->116|148->117|150->119|150->119|150->119|154->123|155->124|162->131|162->131|163->132|163->132|164->133|166->135|166->135|166->135|169->138|169->138|170->139|172->141|172->141|172->141|177->146|178->147|178->147|179->148|181->150|181->150|181->150|186->155|187->156|194->163|194->163|194->163|195->164|195->164|196->165|198->167|198->167|198->167|200->169|200->169|201->170|203->172|203->172|203->172|207->176|208->177|212->181|212->181|213->182|214->183|214->183|215->184|216->185|217->186|221->190|221->190|222->191|223->192|223->192|224->193|224->193|224->193|224->193|227->196|228->197|237->206|237->206|237->206|242->211|242->211|243->212|243->212|244->213|245->214|245->214|245->214|245->214|245->214|245->214|246->215|246->215|246->215|247->216|247->216|247->216|247->216|247->216|247->216|247->216|248->217|249->218|250->219|250->219|251->220|252->221|252->221|252->221|252->221|252->221|252->221|253->222|253->222|253->222|254->223|254->223|254->223|254->223|254->223|254->223|254->223|255->224|256->225|257->226|258->227|258->227|259->228|260->229|260->229|260->229|262->231|263->232|275->244|275->244|275->244|284->253|284->253|284->253|289->258|289->258|290->259|291->260|291->260|291->260|291->260|291->260|291->260|292->261|292->261|292->261|293->262|293->262|293->262|293->262|293->262|293->262|293->262|294->263|295->264|296->265|296->265|297->266|298->267|298->267|298->267|300->269|301->270|309->278|309->278|309->278|310->279|310->279|311->280|314->283|314->283|314->283|317->286|317->286|317->286|321->290|322->291|345->314|345->314|345->314|345->314|370->339|370->339|370->339|378->347|378->347|378->347|378->347|411->380|411->380|411->380|411->380|450->419|450->419|450->419|451->420|451->420|451->420|452->421|452->421|452->421|455->424|455->424|455->424|458->427|458->427|459->428|460->429|460->429|461->430|462->431|464->433|471->440|471->440|471->440|472->441|474->443|474->443|474->443|475->444|477->446|477->446|477->446|478->447|478->447|478->447|480->449|480->449|480->449|481->450|481->450|481->450|482->451|484->453|484->453|484->453|484->453|484->453|484->453|487->456|487->456|487->456|492->461|492->461|492->461|494->463|494->463|494->463|494->463|494->463|494->463|497->466|497->466|497->466|498->467|498->467|498->467|503->472|503->472|503->472|503->472|503->472|503->472|506->475|506->475|506->475|506->475|506->475|506->475|507->476|507->476|508->477|508->477|508->477|508->477|508->477|508->477|508->477|508->477|508->477|508->477|508->477|508->477|508->477|509->478|509->478|510->479|510->479|510->479|510->479|510->479|510->479|510->479|510->479|510->479|510->479|510->479|510->479|510->479|511->480|512->481|513->482|513->482|513->482|515->484|515->484|515->484|515->484|515->484|515->484|518->487|518->487|518->487|519->488|519->488|519->488|521->490|521->490|521->490|524->493|524->493|524->493|524->493|524->493|524->493|524->493|524->493|524->493|524->493|524->493|524->493|524->493|524->493|524->493|524->493|524->493|524->493|524->493|524->493|524->493|527->496|527->496|527->496|527->496|527->496|527->496|529->498|529->498|529->498|530->499|530->499|530->499|530->499|530->499|530->499|531->500|531->500|531->500|532->501|532->501|532->501|532->501|532->501|532->501|532->501|533->502|534->503|537->506|537->506|537->506|537->506|537->506|537->506|539->508|539->508|539->508|540->509|540->509|540->509|545->514|545->514|545->514|545->514|545->514|545->514|545->514|545->514|545->514|545->514|545->514|545->514|545->514|545->514|545->514|545->514|545->514|545->514|545->514|545->514|545->514|548->517|548->517|548->517|548->517|548->517|548->517|551->520|551->520|551->520|552->521|552->521|552->521|554->523|554->523|554->523|557->526|557->526|557->526|557->526|557->526|557->526|557->526|557->526|557->526|557->526|557->526|557->526|557->526|557->526|557->526|557->526|557->526|557->526|557->526|557->526|557->526|560->529|560->529|560->529|560->529|560->529|560->529|563->532|563->532|563->532|564->533|564->533|564->533|566->535|566->535|566->535|569->538|569->538|569->538|569->538|569->538|569->538|574->543|574->543|574->543|574->543|574->543|574->543|575->544|575->544|575->544|575->544|575->544|575->544|576->545|576->545|576->545|576->545|576->545|576->545|579->548|579->548|579->548|579->548|579->548|579->548|581->550|581->550|582->551|582->551|582->551|582->551|582->551|582->551|582->551|583->552|583->552|584->553|584->553|584->553|584->553|584->553|584->553|584->553|585->554|586->555|586->555|586->555|586->555|586->555|586->555|586->555|587->556|587->556|587->556|587->556|587->556|587->556|588->557|588->557|588->557|588->557|588->557|588->557|589->558|589->558|589->558|589->558|589->558|589->558|592->561|593->562|601->570|601->570|602->571|603->572|603->572|604->573|605->574|606->575|616->585|616->585|616->585|616->585|626->595|626->595|626->595|626->595|633->602|633->602|634->603|635->604|635->604|636->605|637->606|638->607|650->619|650->619|651->620|652->621|652->621|653->622|654->623|655->624|705->674|705->674|705->674|711->680|711->680|711->680|712->681|712->681|712->681|712->681|712->681|712->681|712->681|712->681|712->681|712->681|713->682|713->682|713->682|714->683|714->683|714->683|715->684|715->684|715->684|717->686|718->687|750->719|750->719|750->719|754->723|754->723|754->723|755->724|755->724|755->724|755->724|755->724|755->724|755->724|756->725|756->725|756->725|757->726|757->726|757->726|758->727|758->727|758->727|760->729|761->730|822->791|826->795|828->797|828->797|829->798|829->798|829->798|830->799|833->802|833->802|834->803|835->804|835->804|836->805|836->805|836->805|838->807|838->807|839->808|842->811|842->811|843->812|844->813|844->813|845->814|845->814|845->814|849->818|849->818|850->819|853->822|853->822|854->823|855->824|855->824|856->825|856->825|856->825|859->828|859->828|862->831|862->831|863->832|865->834|865->834|866->835|873->842|873->842|874->843|874->843|874->843|875->844|875->844|875->844|875->844|876->845|876->845|876->845|876->845|877->846|877->846|878->847|878->847|879->848|879->848|880->849|880->849|882->851|882->851|882->851|883->852|885->854|885->854|886->855|893->862|893->862|894->863|896->865|896->865|897->866|897->866|898->867|898->867|901->870|901->870|901->870|902->871|907->876|907->876|908->877|910->879|910->879|913->882|913->882|914->883|916->885|916->885|916->885|916->885|919->888|919->888|920->889|922->891|922->891|922->891|922->891|925->894|925->894|926->895|928->897|928->897|928->897|928->897|931->900|931->900|932->901|934->903|934->903|937->906|937->906|938->907|940->909|940->909|945->914|945->914|946->915|946->915|947->916|947->916|952->921|952->921|954->923|954->923|954->923|955->924|956->925|956->925|957->926|958->927|958->927|958->927|958->927|959->928|960->929|960->929|961->930|961->930|963->932|963->932|963->932|964->933|964->933|964->933|965->934|970->939|970->939|970->939|970->939|971->940|976->945|976->945|976->945|976->945|977->946|982->951|982->951|984->953|984->953|986->955|986->955|986->955|987->956|1000->969|1000->969|1001->970|1006->975|1006->975|1006->975|1006->975|1007->976|1012->981|1012->981|1013->982|1026->995|1026->995|1027->996|1028->997|1028->997|1030->999|1030->999|1032->1001|1033->1002|1033->1002|1034->1003|1039->1008|1039->1008|1040->1009|1041->1010|1041->1010|1042->1011|1045->1014|1045->1014|1046->1015|1047->1016|1047->1016|1048->1017|1048->1017|1049->1018|1058->1027|1058->1027|1058->1027|1058->1027|1059->1028|1059->1028|1059->1028|1060->1029|1063->1032|1063->1032|1064->1033|1065->1034|1065->1034|1066->1035|1066->1035|1066->1035|1067->1036|1067->1036|1067->1036|1067->1036|1068->1037|1068->1037|1069->1038|1069->1038|1071->1040|1071->1040|1071->1040|1072->1041|1073->1042|1073->1042|1074->1043|1075->1044|1075->1044|1076->1045|1076->1045|1076->1045|1077->1046|1080->1049|1080->1049|1081->1050|1082->1051|1082->1051|1083->1052|1083->1052|1083->1052|1093->1062|1093->1062|1095->1064|1095->1064|1095->1064|1096->1065|1105->1074|1105->1074|1106->1075|1107->1076|1107->1076|1108->1077|1110->1079|1110->1079|1111->1080|1111->1080|1111->1080|1111->1080|1112->1081|1113->1082|1113->1082|1114->1083|1116->1085|1116->1085|1117->1086|1117->1086|1117->1086|1117->1086|1118->1087|1119->1088|1119->1088|1120->1089|1122->1091|1122->1091|1123->1092|1123->1092|1123->1092|1123->1092|1124->1093|1125->1094|1125->1094|1126->1095|1129->1098|1129->1098|1130->1099|1132->1101|1132->1101|1133->1102|1133->1102|1134->1103|1135->1104|1135->1104|1136->1105|1137->1106|1137->1106|1139->1108|1139->1108|1139->1108|1140->1109|1142->1111|1142->1111|1144->1113|1145->1114|1145->1114|1146->1115|1148->1117|1148->1117|1149->1118|1150->1119|1150->1119|1151->1120|1152->1121|1152->1121|1153->1122|1153->1122|1153->1122|1153->1122|1153->1122|1153->1122|1154->1123|1154->1123|1155->1124|1155->1124|1155->1124|1156->1125|1158->1127|1158->1127|1159->1128|1162->1131|1162->1131|1162->1131|1162->1131|1163->1132|1165->1134|1165->1134|1166->1135|1166->1135|1166->1135|1166->1135|1167->1136|1171->1140|1171->1140|1172->1141|1172->1141|1175->1144|1175->1144|1175->1144|1176->1145|1177->1146|1177->1146|1178->1147|1179->1148|1179->1148|1183->1152|1183->1152|1184->1153|1189->1158|1189->1158|1190->1159|1194->1163|1194->1163|1195->1164|1198->1167|1198->1167|1199->1168|1200->1169|1200->1169|1201->1170|1201->1170|1202->1171|1202->1171|1204->1173|1204->1173|1204->1173|1205->1174|1209->1178|1209->1178|1210->1179|1215->1184|1215->1184|1216->1185|1217->1186|1217->1186|1218->1187|1219->1188|1219->1188|1220->1189|1223->1192|1223->1192|1224->1193|1227->1196|1227->1196|1228->1197|1229->1198|1229->1198|1230->1199|1230->1199|1231->1200|1231->1200|1233->1202|1233->1202|1233->1202|1234->1203|1238->1207|1238->1207|1239->1208|1245->1214|1245->1214|1246->1215|1247->1216|1247->1216|1248->1217|1249->1218|1249->1218|1250->1219|1253->1222|1253->1222|1254->1223|1257->1226|1257->1226|1258->1227|1259->1228|1259->1228|1260->1229|1260->1229|1261->1230|1261->1230|1263->1232|1263->1232|1263->1232|1264->1233|1266->1235|1266->1235|1267->1236|1274->1243|1274->1243|1275->1244|1276->1245|1276->1245|1277->1246|1277->1246|1278->1247|1278->1247|1280->1249|1280->1249|1280->1249|1281->1250|1284->1253|1284->1253|1285->1254|1292->1261|1292->1261|1294->1263|1298->1267|1298->1267|1299->1268|1302->1271|1302->1271|1303->1272|1303->1272|1303->1272|1304->1273|1307->1276|1307->1276|1308->1277|1309->1278|1309->1278|1310->1279|1316->1285|1316->1285|1317->1286|1317->1286|1319->1288|1319->1288|1321->1290|1322->1291|1322->1291|1323->1292|1326->1295|1326->1295|1327->1296|1328->1297|1328->1297|1329->1298|1329->1298|1331->1300|1331->1300|1332->1301|1332->1301|1333->1302|1333->1302|1336->1305|1336->1305|1336->1305|1342->1311|1342->1311|1342->1311|1343->1312|1343->1312|1343->1312|1343->1312|1344->1313|1344->1313|1344->1313|1347->1316|1349->1318|1349->1318|1349->1318|1349->1318|1351->1320|1351->1320|1352->1321|1354->1323|1354->1323|1355->1324|1356->1325|1356->1325|1357->1326|1357->1326|1357->1326|1358->1327|1359->1328|1359->1328|1360->1329|1360->1329|1361->1330|1361->1330|1362->1331|1363->1332|1363->1332|1364->1333|1364->1333|1365->1334|1365->1334|1365->1334|1366->1335|1369->1338|1369->1338|1370->1339|1371->1340|1371->1340|1372->1341|1372->1341|1372->1341|1379->1348|1379->1348|1380->1349|1390->1359|1390->1359|1391->1360|1396->1365|1396->1365|1397->1366|1400->1369|1400->1369|1401->1370|1402->1371|1402->1371|1403->1372|1403->1372|1403->1372|1408->1377|1408->1377|1410->1379|1411->1380|1411->1380|1412->1381|1412->1381|1412->1381|1413->1382|1415->1384|1415->1384|1415->1384|1415->1384|1416->1385|1419->1388|1419->1388|1420->1389|1420->1389|1422->1391|1422->1391|1422->1391|1424->1393|1424->1393|1424->1393|1426->1395|1433->1402|1433->1402|1435->1404|1435->1404|1435->1404|1437->1406|1437->1406|1437->1406|1439->1408|1480->1449|1480->1449|1481->1450|1482->1451|1482->1451|1482->1451|1482->1451|1483->1452|1484->1453|1484->1453|1485->1454|1502->1471|1502->1471|1502->1471|1502->1471|1502->1471|1502->1471|1502->1471|1502->1471|1502->1471|1510->1479|1510->1479|1511->1480|1512->1481|1512->1481|1513->1482|1525->1494|1525->1494|1525->1494|1525->1494|1525->1494|1525->1494|1525->1494|1525->1494|1525->1494|1538->1507|1538->1507|1538->1507|1538->1507|1538->1507|1538->1507|1538->1507|1538->1507|1538->1507|1582->1551|1582->1551|1583->1552|1584->1553|1584->1553|1586->1555|1605->1574|1605->1574|1606->1575|1606->1575|1607->1576|1607->1576|1608->1577|1608->1577|1609->1578|1609->1578|1609->1578|1610->1579|1613->1582|1613->1582|1614->1583|1615->1584|1615->1584|1616->1585|1616->1585|1616->1585|1617->1586|1617->1586|1622->1591
                  -- GENERATED --
              */
          