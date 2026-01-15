
package viewsMnuCotizar.html

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

object cotizaIngreso2 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template20[Map[String,String],Map[String,String],utilities.UserMnu,forms.FormCotiza,List[tables.Cliente],List[tables.Proyecto],String,List[tables.Regiones],String,tables.Sucursal,tables.Comercial,List[tables.Sucursal],List[tables.Comercial],String,String,List[tables.CotizaSolucion],Double,String,List[tables.Dibujante],List[tables.PlazoProbable],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
formCotiza: forms.FormCotiza, listClientes: List[tables.Cliente], listProyectos: List[tables.Proyecto],  
numDecParaTot: String, listRegiones: List[tables.Regiones], jsonListUnTiempo: String, 
sucursal: tables.Sucursal, comercial: tables.Comercial, listSucursal: List[tables.Sucursal], listComercial: List[tables.Comercial],
importDesdeExcel: String, jsonDetalle: String, listSoluciones: List[tables.CotizaSolucion], iva: Double, jsonListMoneda: String,
listDibujantes: List[tables.Dibujante], listPlazoProbable: List[tables.PlazoProbable]):play.twirl.api.HtmlFormat.Appendable = {
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
								  			onblur="if(!limitaFecha(value,720,10)) value='"""),_display_(/*92.61*/formCotiza/*92.71*/.fechaCoti),format.raw/*92.81*/("""'; calcFechaProbable();"
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
											"""),format.raw/*214.12*/("""<input id="selSucursal" type="text" class="form-control left"
												value = """"),_display_(/*215.23*/sucursal/*215.31*/.getNombre()),format.raw/*215.43*/(""""
												readonly>
										""")))}),format.raw/*217.12*/("""
									"""),format.raw/*218.10*/("""</div>
								</td>

								
								
								<td colspan="20" rowspan="6">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Observaciones</span>
								  		</div>
	  									<textarea class="form-control" rows="9"
											id="observaciones"
	  										name="observaciones" 
	  										autocomplete="off">"""),_display_(/*231.34*/formCotiza/*231.44*/.observaciones),format.raw/*231.58*/("""</textarea>
									</div>
								</td>
							</tr>
							<tr>
							
							
								<td align="center"  colspan="2">
									<div id="actualComerciales">
										<input type="hidden" id="id_comercial" name="id_comercial" value=""""),_display_(/*240.78*/comercial/*240.87*/.getId()),format.raw/*240.95*/("""">
										<div class="input-group">
									  		<div class="input-group-prepend">
									    		<span class="input-group-text" id="basic-addon1">Comercial</span>
									  		</div>
											"""),_display_(if(mapPermiso.get("cambiarComercial")!=null)/*245.57*/{_display_(Seq[Any](format.raw/*245.58*/("""
												"""),format.raw/*246.13*/("""<select  id="selComercial" class="custom-select"  style="width: 80px;" onchange="$('#id_comercial').val(value)">
													<option value=""""),_display_(/*247.30*/comercial/*247.39*/.getId()),format.raw/*247.47*/("""">"""),_display_(/*247.50*/comercial/*247.59*/.getNameUsuario()),format.raw/*247.76*/("""</option>
													"""),_display_(/*248.15*/for(lista <- listComercial) yield /*248.42*/{_display_(Seq[Any](format.raw/*248.43*/("""
														"""),format.raw/*249.15*/("""<option value=""""),_display_(/*249.31*/lista/*249.36*/.getId()),format.raw/*249.44*/("""">"""),_display_(/*249.47*/lista/*249.52*/.getNameUsuario()),format.raw/*249.69*/("""</option>
													""")))}),format.raw/*250.15*/("""
												"""),format.raw/*251.13*/("""</select>
											""")))}else/*252.17*/{_display_(Seq[Any](format.raw/*252.18*/("""
												"""),format.raw/*253.13*/("""<input  id="selComercial" type="text" class="form-control left"
													value = """"),_display_(/*254.24*/comercial/*254.33*/.getNameUsuario()),format.raw/*254.50*/(""""
													readonly>
											""")))}),format.raw/*256.13*/("""
										"""),format.raw/*257.11*/("""</div>
									</div>
								</td>
							</tr>
							<tr>
								<td align="center"  colspan="2">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Tipo de Solucion</span>
								  		</div>
										<input type="hidden" id="id_cotizaSolucion" name="id_cotizaSolucion" value="1">
										<select id="selSolucion" class="custom-select"  style="width: 80px;" onchange="$('#id_cotizaSolucion').val(value)">
											"""),_display_(/*269.13*/for(lista <- listSoluciones) yield /*269.41*/{_display_(Seq[Any](format.raw/*269.42*/("""
												"""),format.raw/*270.13*/("""<option value=""""),_display_(/*270.29*/lista/*270.34*/.getId()),format.raw/*270.42*/("""">"""),_display_(/*270.45*/lista/*270.50*/.getSolucion()),format.raw/*270.64*/("""</option>
											""")))}),format.raw/*271.13*/("""
										"""),format.raw/*272.11*/("""</select>
									</div>
								</td>
							</tr>
							<tr>
								<td align="center">
									<div class="input-group">
										<div class="input-group-prepend">
											<span class="input-group-text" id="basic-addon1">Plazo Probable</span>
										</div>
										<select id="diasProbable" class="custom-select"  style="width: 80px;" onchange="calcFechaProbable()">
											<option value="0">0 dias</option>
											"""),_display_(/*284.13*/for(lista <- listPlazoProbable) yield /*284.44*/{_display_(Seq[Any](format.raw/*284.45*/("""
											"""),format.raw/*285.12*/("""<option value=""""),_display_(/*285.28*/lista/*285.33*/.getId()),format.raw/*285.41*/("""">"""),_display_(/*285.44*/lista/*285.49*/.getPlazo()),format.raw/*285.60*/("""</option>
											""")))}),format.raw/*286.13*/("""
										"""),format.raw/*287.11*/("""</select>
									</div>
								</td>
								<td align="center">
									<div class="input-group">
										<div class="input-group-prepend">
											<span class="input-group-text" id="basic-addon1">Fecha Probable</span>
										</div>
										<input type="date" class="form-control center"
											   name="fechaProbable"
											   id="fechaProbable"
											   onblur="if(!limitaFecha(value,720,720)) value='"""),_display_(/*298.63*/formCotiza/*298.73*/.fechaCoti),format.raw/*298.83*/("""';"
											   value=""""),_display_(/*299.23*/formCotiza/*299.33*/.fechaCoti),format.raw/*299.43*/(""""
											   required>
									</div>
								</td>
							</tr>
							<tr>
								<td align="center"  colspan="2">
									<div class="input-group">
										<div class="input-group-prepend">
											<span class="input-group-text" id="basic-addon1">Dibujante/Proyectista</span>
										</div>
										<input type="hidden" id="id_dibujante" name="id_dibujante" value="0">
										<select id="selSolucion" class="custom-select"  style="width: 80px;" onchange="$('#id_dibujante').val(value)">
											<option value="0"></option>
											"""),_display_(/*313.13*/for(lista <- listDibujantes) yield /*313.41*/{_display_(Seq[Any](format.raw/*313.42*/("""
											"""),format.raw/*314.12*/("""<option value=""""),_display_(/*314.28*/lista/*314.33*/.getId()),format.raw/*314.41*/("""">"""),_display_(/*314.44*/lista/*314.49*/.getNombre()),format.raw/*314.61*/("""</option>
											""")))}),format.raw/*315.13*/("""
										"""),format.raw/*316.11*/("""</select>
									</div>
								</td>
							</tr>
							<tr>
								<input type="hidden" id="id_bodegaEmpresa" name="id_bodegaEmpresa" value=""""),_display_(/*321.84*/formCotiza/*321.94*/.id_bodegaEmpresa),format.raw/*321.111*/("""">
								"""),_display_(if(mapPermiso.get("parametro.cotizaPorTasa").equals("1"))/*322.67*/ {_display_(Seq[Any](format.raw/*322.69*/("""
									"""),format.raw/*323.10*/("""<td align="center"  colspan="2">
										<div class="input-group">
									  		<div class="input-group-prepend">
									    		<span class="input-group-text" id="basic-addon1">"""),_display_(/*326.66*/mapDiccionario/*326.80*/.get("BODEGA")),format.raw/*326.94*/("""/PROYECTO</span>
									  		</div>
									  		<input type="text" class="form-control left"
													value = """"),_display_(/*329.24*/formCotiza/*329.34*/.nombreBodega),format.raw/*329.47*/(""""
													readonly>
										</div>
									</td>
								""")))} else {null} ),format.raw/*333.10*/("""
							"""),format.raw/*334.8*/("""</tr>
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
							
							

						"""),_display_(if(mapPermiso.get("parametro.coti8columnas")!=null && mapPermiso.get("parametro.coti8columnas").equals("1"))/*352.116*/ {_display_(Seq[Any](format.raw/*352.118*/("""
							"""),format.raw/*353.8*/("""<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
								<input id="btnImport8column" type="button"  value="Import 8 Columnas Excel" class="btn btn-primary btn-sm rounded-pill btn-block" 
									onclick="$('#modalImport8column').modal('show');">&nbsp;&nbsp;&nbsp;
							</div>
						""")))} else {null} ),format.raw/*357.8*/("""
			
			
			
						"""),format.raw/*361.7*/("""</div>
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
											onchange="value = formatPorcentaje(value); if(value=='') """),format.raw/*385.69*/("""{"""),format.raw/*385.70*/("""value='0.00 %';"""),format.raw/*385.85*/("""}"""),format.raw/*385.86*/("""">
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
					
					<div id='modalPermGlobal' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
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
								    		<span class="input-group-text" id="basic-addon1">APLICAR PERMANENCIA GLOBAL</span>
								  		</div>
								  		<input type="text" class="form-control center"
											id="permGlobal"
											value="0.00"
											onfocus="value = value.replace(/,/g,'')"
											onkeydown="return ingresoDouble(window.event)"
											autocomplete="off"
											onchange="value = formatStandar(value,2); if(value=='') """),format.raw/*418.68*/("""{"""),format.raw/*418.69*/("""value='0.00';"""),format.raw/*418.82*/("""}"""),format.raw/*418.83*/("""">
									</div>
									<br>
									<div class='row'>
										<div class='col-sm-12' style='text-align:center'>
											<input type="button" class="btn btn-sm btn-primary" value='Aplicar' onclick='$("#modalPermGlobal").modal("hide"); aplicarPermGlobal();'>
											<input type='button' class='btn btn-sm btn-warning' value='Cancelar' data-dismiss='modal'>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
	
					<div id="tblPrincipal"></div>
				</div>
			</div>
			
			
			<div class="noprint" align="left">
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
								<TH>"""),_display_(/*475.14*/mapDiccionario/*475.28*/.get("RUT")),format.raw/*475.39*/("""</TH>
								<TH>NICK</TH>
								<TH>NOMBRE</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*481.9*/for(lista1 <- listClientes) yield /*481.36*/{_display_(Seq[Any](format.raw/*481.37*/("""
								"""),format.raw/*482.9*/("""<TR onClick="$('#id_cliente').val("""),_display_(/*482.44*/lista1/*482.50*/.getId()),format.raw/*482.58*/("""); $('#rutCliente').val('"""),_display_(/*482.84*/lista1/*482.90*/.getRut()),format.raw/*482.99*/("""');$('#nombreCliente').val('"""),_display_(/*482.128*/lista1/*482.134*/.getNickName()),format.raw/*482.148*/("""');" data-dismiss="modal">
									<td  style="text-align:left;">"""),_display_(/*483.41*/lista1/*483.47*/.getRut()),format.raw/*483.56*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*484.41*/lista1/*484.47*/.getNickName()),format.raw/*484.61*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*485.41*/lista1/*485.47*/.getNombre()),format.raw/*485.59*/("""</td>
								</TR>
				 			""")))}),format.raw/*487.10*/("""
						"""),format.raw/*488.7*/("""</tbody>
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
								<TH>"""),_display_(/*520.14*/mapDiccionario/*520.28*/.get("Comuna")),format.raw/*520.42*/("""</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*524.9*/for(lista1 <- listProyectos) yield /*524.37*/{_display_(Seq[Any](format.raw/*524.38*/("""
								"""),format.raw/*525.9*/("""<TR onClick="$('#id_proyecto').val("""),_display_(/*525.45*/lista1/*525.51*/.getId()),format.raw/*525.59*/("""); $('#nombreProyecto').val('"""),_display_(/*525.89*/lista1/*525.95*/.getNickName()),format.raw/*525.109*/("""');" data-dismiss="modal">
									<td  style="text-align:left;">"""),_display_(/*526.41*/lista1/*526.47*/.getNickName()),format.raw/*526.61*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*527.41*/lista1/*527.47*/.getNombre()),format.raw/*527.59*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*528.41*/lista1/*528.47*/.getComuna()),format.raw/*528.59*/("""</td>
								</TR>
				 			""")))}),format.raw/*530.10*/("""
						"""),format.raw/*531.7*/("""</tbody>
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
	
	
	<div id="modalListaCotizacion" class="modal" role="dialog" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog modal-dialog-scrollable modal-xl" role="document">
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
	        <h5 class="modal-title">Carga masiva de equipos a cotizar</h5>
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
					<form id="cotiCargaPlantilla" action="/cotiCargaPlantilla/" method="POST" enctype="multipart/form-data">
						<input type="hidden" id="formSucursal" name="id_sucursal" value=""""),_display_(/*604.73*/sucursal/*604.81*/.getId()),format.raw/*604.89*/("""">
						<span class="btn btn-info btn-sm btn-file" style="font-size: 8px;">
							<div>SUBIR ARCHIVO</div>
							<input type="hidden" id="id_bodegaEmpresa" name="id_bodegaEmpresa" value=""""),_display_(/*607.83*/formCotiza/*607.93*/.id_bodegaEmpresa),format.raw/*607.110*/("""">
							<input type="file" id="archivoXLSX" name="archivoXLSX" value="" onchange="subirArchivo(this.form, this.form.archivoXLSX.value,this)">
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
	
	<div id="modalImport8column" class="modal" role="dialog" data-backdrop="static" data-keyboard="false">
	  <div class="modal-dialog modal-sm modal-dialog-scrollable" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title">Carga masiva plantilla 8 columnas</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        
			<div class="row">
				<div class="col-sm-12" style="text-align:center">
					<form action="/routes2/coti8columnas/" method="GET"  onsubmit="return validarForm2();">
						<button type="submit" id="btnSubmit2" class="btn btn-sm  btn-success" style="font-size: 10px;">DESCARGAR PLANTILLA</button>
					</form>
					<br>
					<form id="coti8ValidarPlantilla" action="/routes2/coti8columnasValida/" method="POST" enctype="multipart/form-data">
						<span class="btn btn-info btn-sm btn-file" style="font-size: 8px;">
							<input type="hidden" id="formSucursal" name="id_sucursal" value=""""),_display_(/*640.74*/sucursal/*640.82*/.getId()),format.raw/*640.90*/("""">
							<div>SUBIR ARCHIVO</div>
							<input type="hidden" id="id_bodegaEmpresa" name="id_bodegaEmpresa" value=""""),_display_(/*642.83*/formCotiza/*642.93*/.id_bodegaEmpresa),format.raw/*642.110*/("""">
							<input type="file" id="plantillaCoti8colun" name="archivoXLSX" value="" onchange="subirCoti8colum(this.form, this.form.plantillaCoti8colun.value)">
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
	
	<div id="msgNuevosCodigos" class="modal" role="dialog" data-backdrop="static" data-keyboard="false">
	  <div class="modal-dialog modal-sm modal-dialog-scrollable modal-lg" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title">EXISTE EQUIPOS EN LA PLANTILLA EXCEL QUE NO EXISTEN EN MADA</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
				<div class="col-sm-12" style="text-align:center">
				  <button type="button" class="btn btn-sm  btn-primary" style="font-size: 10px;" 
					onclick='$("#cotiCargaPlantilla").submit();'>CREAR LOS EQUIPOS QUE NO EXISTEN</button><br><br>
				  <button type="button" class="btn btn-sm  btn-info" style="font-size: 10px;" 
					onclick="revisarEquiposNuevos();">REVISAR EQUIPOS QUE NO EXISTEN</button><br><br>
				  <button type="button" class="btn btn-sm  btn-warning" style="font-size: 10px;" 
					onclick="location.reload();">CANCELAR</button>
				</div>
			<br>
	      </div>
	    </div>
	  </div>
	</div>
	
	
	
""")))}),format.raw/*682.2*/("""


"""),format.raw/*685.1*/("""<script type="text/javascript">

	let noCambiarPorSucursal = "0";
	
	let factorVta = 1;
	let factorArr = 1;
	let mapMoneda = new Map();
	
	$(document).ready(function() """),format.raw/*693.31*/("""{"""),format.raw/*693.32*/("""
		
		"""),format.raw/*695.3*/("""noCambiarPorSucursal = """"),_display_(/*695.28*/importDesdeExcel),format.raw/*695.44*/("""";
		
		let jsonListUnTiempo = """),_display_(/*697.27*/Html(jsonListUnTiempo)),format.raw/*697.49*/(""";
		let unTiempo = "";
		for(var i in jsonListUnTiempo)"""),format.raw/*699.33*/("""{"""),format.raw/*699.34*/("""
			"""),format.raw/*700.4*/("""unTiempo += "<option value=\""+jsonListUnTiempo[i]["id"]+"\">"+jsonListUnTiempo[i]["nombre"]+"</option>";
		"""),format.raw/*701.3*/("""}"""),format.raw/*701.4*/("""
		
		"""),format.raw/*703.3*/("""let jsonListMoneda = """),_display_(/*703.25*/Html(jsonListMoneda)),format.raw/*703.45*/(""";
		let moneda = "";
		for(var i in jsonListMoneda)"""),format.raw/*705.31*/("""{"""),format.raw/*705.32*/("""
			"""),format.raw/*706.4*/("""moneda += "<option value=\""+jsonListMoneda[i]["id"]+"\">"+jsonListMoneda[i]["nickName"]+"</option>";
			mapMoneda.set(jsonListMoneda[i]["id"], jsonListMoneda[i]["numeroDecimales"]);
		"""),format.raw/*708.3*/("""}"""),format.raw/*708.4*/("""
		
		
		"""),format.raw/*711.3*/("""let jsonDetalle = """),_display_(/*711.22*/Html(jsonDetalle)),format.raw/*711.39*/(""";

		let escondeLosM2 = 0;
		"""),_display_(if(mapPermiso.get("parametro.escondeLosM2")!=null && mapPermiso.get("parametro.escondeLosM2").equals("1"))/*714.110*/ {_display_(Seq[Any](format.raw/*714.112*/("""
			"""),format.raw/*715.4*/("""escondeLosM2 = 1;
		""")))} else {null} ),format.raw/*716.4*/("""
		
		
		
		"""),format.raw/*720.3*/("""let tabla = "<table id=\"tablaPrincipal\" class=\"table table-sm table-hover table-bordered table-condensed table-fluid\">"
			+ "	<thead style=\"background-color: #eeeeee\">"
			+ "<TR>"
				+ "<TH colspan=\"8\" style=\"background-color: white\"></TH>"
				+ "<TH style=\"text-align:center\">";
				if( ! ('"""),_display_(/*725.14*/mapPermiso/*725.24*/.get("parametro.cotizar_bloqueaVtaReposi")),format.raw/*725.66*/("""'!=null && '"""),_display_(/*725.79*/mapPermiso/*725.89*/.get("parametro.cotizar_bloqueaVtaReposi")),format.raw/*725.131*/("""' == "1") ) """),format.raw/*725.143*/("""{"""),format.raw/*725.144*/("""
					"""),format.raw/*726.6*/("""tabla += "<div id=\"titAplicarFactorRepos\">Aplica_Factor</div>"
					+ "<input type=\"text\" class=\"form-control rounded-pill height23px right\""
					+ " style='background-color: #ffc107'"
					+ " id=\"btnAplicarFactorRepos\""
					+ " value=\"1.00\""
					+ " onkeydown=\"return ingresoDouble(window.event)\""
					+ " onfocus=\"value = value.replace(/,/g,'').replace(/%/g,'').replace(/ /g,'')\""
					+ " onchange=\"if(value=='' || value==0) """),format.raw/*733.47*/("""{"""),format.raw/*733.48*/(""""
							+ " value = formatStandar(factorVta,2);"
							+ """"),format.raw/*735.11*/("""}"""),format.raw/*735.12*/("""else"""),format.raw/*735.16*/("""{"""),format.raw/*735.17*/(""""
								+ " aplicarFactorGlobalRepos(value);"
								+ " value = formatStandar(value,2);"
							+ """"),format.raw/*738.11*/("""}"""),format.raw/*738.12*/("""\">"
				"""),format.raw/*739.5*/("""}"""),format.raw/*739.6*/("""
				"""),format.raw/*740.5*/("""tabla += "</TH>"
				+ "<TH style=\"background-color: white\"></TH>"
				+ "<TH style=\"text-align:center\">"
					+ "<br><input id=\"btnAplicarTasa\" type=\"button\"  value=\"Aplica Tasa\" class=\"btn btn-warning btn-sm rounded-pill btn-block\" onclick='$(\"#modalTasaGlobal\").modal(\"show\")'>"
				+ "</TH>"
			+ "<TH style=\"text-align:center\">"
				+ "<div id=\"titAplicarFactorArr\">Aplica_Factor</div><input type=\"text\" class=\"form-control rounded-pill height23px right\""
						+ " style='background-color: #ffc107'"
						+ " id=\"btnAplicarFactorArr\""
						+ " value=\"1.00\""
						+ " onkeydown=\"return ingresoDouble(window.event)\""
						+ " onfocus=\"value = value.replace(/,/g,'').replace(/%/g,'').replace(/ /g,'')\""
						+ " onchange=\"if(value=='' || value==0) """),format.raw/*752.48*/("""{"""),format.raw/*752.49*/(""""
							+ " value = formatStandar(factorArr,2);"
							+ """"),format.raw/*754.11*/("""}"""),format.raw/*754.12*/("""else"""),format.raw/*754.16*/("""{"""),format.raw/*754.17*/(""""
								+ " aplicarFactorGlobal(value);"
								+ " value = formatStandar(value,2);"
							+ """"),format.raw/*757.11*/("""}"""),format.raw/*757.12*/("""\">"
			+ "</TH>"
			+ "<TH style=\"text-align:center\">"
			+ "<br><input id=\"btnAplicarPerm\" type=\"button\"  value=\"Aplica Perm\" class=\"btn btn-warning btn-sm rounded-pill btn-block\" onclick='$(\"#modalPermGlobal\").modal(\"show\")'>"
			+ "</TH>"
			+ "<TH colspan=\"12\" style=\"background-color: white\"></TH>"
			+ "</TR>"
			+ "<TR>"
			+ "<TH>GRUPO</TH>"
			+ "<TH>CODIGO</TH>"
			+ "<TH style=\"min-width:30%\">EQUIPO</TH>"
			+ "<TH>STOCK</TH>"
			+ "<TH>UN</TH>"
			+ "<TH>CANT</TH>"
			+ "<TH>SOLO<br>VENTA</TH>"
			+ "<TH>MON</TH>"
			+ "<TH>P.UNITARIO<br>VTA/REPOS</TH>"
			+ '<TH>UN<br>"""),_display_(/*774.18*/mapDiccionario/*774.32*/.get("ARR")),format.raw/*774.43*/("""</TH>'
			+ '<TH>TASA<br>"""),_display_(/*775.20*/mapDiccionario/*775.34*/.get("ARR")),format.raw/*775.45*/("""/VTA</TH>'
			+ '<TH>P.UNITARIO<br>"""),_display_(/*776.26*/mapDiccionario/*776.40*/.get("ARR")),format.raw/*776.51*/("""</TH>'
			+ "<TH>PERMAN</TH>"
			+ "<TH>P.TOTAL<br>REPOSICION</TH>"
			+ '<TH>P.TOTAL<br>"""),_display_(/*779.23*/mapDiccionario/*779.37*/.get("ARRIENDO")),format.raw/*779.53*/("""</TH>'
			+ "<TH>P.TOTAL<br>VENTA</TH>"
			+ "<TH>TOT.KG</TH>";


			if(escondeLosM2 == 1) """),format.raw/*784.26*/("""{"""),format.raw/*784.27*/("""
				"""),format.raw/*785.5*/("""tabla += "<TH style = \"display:none\">TOT.M2</TH>";
			"""),format.raw/*786.4*/("""}"""),format.raw/*786.5*/("""else"""),format.raw/*786.9*/("""{"""),format.raw/*786.10*/("""
				"""),format.raw/*787.5*/("""tabla += "<TH>TOT.M2</TH>";
			"""),format.raw/*788.4*/("""}"""),format.raw/*788.5*/("""

			"""),format.raw/*790.4*/("""tabla += "<TH style = \"display:none\">kg</TH>"
				+ "<TH style = \"display:none\">m2</TH>"
				+ "<TH style = \"display:none\">id_equipo</TH>"
				+ "<TH style = \"display:none\">nrodecimal</TH>"
				+ "</TR>"
				+ "</thead>"
				+ "<tbody>";

			for(var i in jsonDetalle)"""),format.raw/*798.29*/("""{"""),format.raw/*798.30*/("""
				"""),format.raw/*799.5*/("""tabla += "<TR>"
					+ "<td style=\"text-align:left;\">"+jsonDetalle[i][23]+"</td>"
					+ "<td style=\"text-align:left;\">"
					+ "<input type=\"hidden\" name=\"id_equipo[]\" value=\""+jsonDetalle[i][0]+"\">"
					+ "<a href=\"#\" onclick=\"equipoDescripcion('"+jsonDetalle[i][0]+"');\">"+jsonDetalle[i][1]+"</a>"
					+ "</td>"
					+ "<td style= \"text-align: left;\"><a href=\"#\" onclick=\"equipoDescripcion('"+jsonDetalle[i][0]+"');\">"+jsonDetalle[i][2]+"</a></td>"
					+ "<td style=\"text-align:center;\">"
					+ "<div class=\"noprint\">"
					+ "<a href=\"#\" onclick=\"vistaStockPorEquipo('"+jsonDetalle[i][0]+"');\">"
					+ "<kbd style=\"background-color: #73C6B6\">stock</kbd>"
					+ "</a>"
					+ "</div>"
					+ "</td>"
					+ "<td style=\"text-align:center;\">"+jsonDetalle[i][3]+"</td>"
					+ "<td style=\"text-align:center;\">"
					+ "<div style=\"display:none\" id=\"hiddencantidad_"+jsonDetalle[i][0]+"\">"+jsonDetalle[i][25]+"</div>"
					+ "<input type=\"text\" class=\"cantidad form-control height23px right width80px\""
					+ "name=\"cantidad[]\""
					+ "id=\"cantidad_"+jsonDetalle[i][0]+"\""
					+ "value=\""+jsonDetalle[i][10]+"\""
					+ "onfocus=\"value=value.replace(/,/g,''); cantAux = value;\" "
					+ "onblur = \"value = formatStandar(value, '2');\""
					+ "onkeydown=\"return ingresoDouble(window.event)\""
					+ "autocomplete=\"off\""
					+ "onchange=\"if(value=='') value=0; calculaLinea('"+jsonDetalle[i][0]+"', '"+jsonDetalle[i][20]+"');\">"
					+ "</td>"
					+ "<td style=\"text-align:center;\">"
					+ "<input type=\"hidden\" name=\"esVenta[]\" id=\"esVenta_"+jsonDetalle[i][0]+"\"  value=\""+jsonDetalle[i][11]+"\">";


					if(jsonDetalle[i][11] == "0")"""),format.raw/*830.35*/("""{"""),format.raw/*830.36*/("""
						"""),format.raw/*831.7*/("""tabla += "<input type=\"checkbox\" id=\"checkbox_"+jsonDetalle[i][0]+"\" onchange=\"checkVenta('"+jsonDetalle[i][0]+"'); "
							+ "calculaLinea('"+jsonDetalle[i][0]+"', '"+jsonDetalle[i][20]+"');\">"
					"""),format.raw/*833.6*/("""}"""),format.raw/*833.7*/("""else"""),format.raw/*833.11*/("""{"""),format.raw/*833.12*/("""
						"""),format.raw/*834.7*/("""tabla += "<input type=\"checkbox\" id=\"checkbox_"+jsonDetalle[i][0]+"\" onchange=\"checkVenta('"+jsonDetalle[i][0]+"');"
							+ "calculaLinea('"+jsonDetalle[i][0]+"', '"+jsonDetalle[i][20]+"');\" checked>";
					"""),format.raw/*836.6*/("""}"""),format.raw/*836.7*/("""

					"""),format.raw/*838.6*/("""tabla += "</td>"
						+ "<td style=\"text-align:center;\">" 
						+ "<input type=\"hidden\" id=\"id_moneda_"+jsonDetalle[i][0]+"\" name=\"id_moneda[]\" value=\""+jsonDetalle[i][8]+"\">"
						
							+ "<select class=\"custom-select\"  style=\"width: 80px;\""
							+ "onchange=\"$('#id_moneda_"+jsonDetalle[i][0]+"').val(value); calculaLinea('"+jsonDetalle[i][0]+"', '"+jsonDetalle[i][20]+"');\">"
							+ "<option value=\""+jsonDetalle[i][8]+"\">"+jsonDetalle[i][4]+"</option>"+moneda
							+ "</select>"
							
						+"</td>"
						+ "<td style=\"text-align:center;\">"
						+ "<div style=\"display:none\" id=\"hiddenpuVentaRepos_"+jsonDetalle[i][0]+"\">"+jsonDetalle[i][26]+"</div>";
						
						if( '"""),_display_(/*851.13*/mapPermiso/*851.23*/.get("parametro.cotizar_bloqueaVtaReposi")),format.raw/*851.65*/("""'!=null && '"""),_display_(/*851.78*/mapPermiso/*851.88*/.get("parametro.cotizar_bloqueaVtaReposi")),format.raw/*851.130*/("""' == "1") """),format.raw/*851.140*/("""{"""),format.raw/*851.141*/("""
							"""),format.raw/*852.8*/("""tabla += "<input type=\"text\" class=\"form-control height23px right width100px\""
							+ "name=\"puVentaRepos[]\""
							+ "id=\"puVentaRepos_"+jsonDetalle[i][0]+"\""
							+ "value=\""+jsonDetalle[i][5]+"\""
							+ " readonly>";
						"""),format.raw/*857.7*/("""}"""),format.raw/*857.8*/("""else"""),format.raw/*857.12*/("""{"""),format.raw/*857.13*/("""
							"""),format.raw/*858.8*/("""tabla += "<input type=\"text\" class=\"form-control height23px right width100px\""
							+ "name=\"puVentaRepos[]\""
							+ "id=\"puVentaRepos_"+jsonDetalle[i][0]+"\""
							+ "value=\""+jsonDetalle[i][5]+"\""
							+ "onfocus=\"value=value.replace(/,/g,'');\" "
							+ "onblur = \"calculaLinea('"+jsonDetalle[i][0]+"', '"+jsonDetalle[i][20]+"');\""
							+ "onkeydown=\"return ingresoDouble(window.event)\""
							+ "autocomplete=\"off\" "
							+ "onchange=\"if(value=='') """),format.raw/*866.36*/("""{"""),format.raw/*866.37*/("""value=0;"""),format.raw/*866.45*/("""}"""),format.raw/*866.46*/(""" """),format.raw/*866.47*/("""else """),format.raw/*866.52*/("""{"""),format.raw/*866.53*/("""calculaTasa(1,'"+jsonDetalle[i][0]+"', '"+jsonDetalle[i][20]+"'); calculaLinea('"+jsonDetalle[i][0]+"', '"+jsonDetalle[i][20]+"');"""),format.raw/*866.183*/("""}"""),format.raw/*866.184*/("""\">";
						"""),format.raw/*867.7*/("""}"""),format.raw/*867.8*/("""
						"""),format.raw/*868.7*/("""tabla += "</td>"
						+ "<td style=\"text-align:center;\">"
						+ "<input type=\"hidden\" id=\"id_unidadTiempo_"+jsonDetalle[i][0]+"\" name=\"id_unidadTiempo[]\" value=\""+jsonDetalle[i][9]+"\">"
						+ "<select class=\"custom-select\"  style=\"width: 80px;\""
						+ "onchange=\"$('#id_unidadTiempo_"+jsonDetalle[i][0]+"').val(value);\">"
						+ "<option value=\""+jsonDetalle[i][9]+"\">"+jsonDetalle[i][6]+"</option>"+unTiempo
						+ "</td>"
						+ "<td>"
						+ "<div style=\"display:none\" id=\"hiddentasaArr_"+jsonDetalle[i][0]+"\">"+jsonDetalle[i][27]+"</div>"
						+ "<input type=\"text\" class=\"tasaGlobal form-control height23px right width80px\""
						+ "id=\"tasaArr_"+jsonDetalle[i][0]+"\""
						+ "value=\""+jsonDetalle[i][24]+"\""
						+ "onfocus=\"value=value.replace(/,/g,'').replace(/%/g,'').replace(/ /g,'')\""
						+ "onblur = \"value = formatPorcentaje(value);\""
						+ "onkeydown=\"return ingresoDouble(window.event)\""
						+ "autocomplete=\"off\""
						+ "onchange=\"if(value=='') """),format.raw/*884.35*/("""{"""),format.raw/*884.36*/("""value='0.00 %';"""),format.raw/*884.51*/("""}"""),format.raw/*884.52*/(""" """),format.raw/*884.53*/("""else """),format.raw/*884.58*/("""{"""),format.raw/*884.59*/("""calculaTasa(2,'"+jsonDetalle[i][0]+"', '"+jsonDetalle[i][20]+"'); calculaLinea('"+jsonDetalle[i][0]+"', '"+jsonDetalle[i][20]+"');"""),format.raw/*884.189*/("""}"""),format.raw/*884.190*/("""\">"
						+ "</td>"
						+ "<td style=\"text-align:center;\">"
						+ "<div style=\"display:none\" id=\"hiddenpuArriendo_"+jsonDetalle[i][0]+"\">"+jsonDetalle[i][28]+"</div>"
						+ "<input type=\"text\" class=\"factorGlobal form-control height23px right width80px\""
						+ "name=\"puArriendo[]\""
						+ "id=\"puArriendo_"+jsonDetalle[i][0]+"\""
						+ "value=\""+jsonDetalle[i][7]+"\""
						+ "onfocus=\"value=value.replace(/,/g,'');\" "
						+ "onblur = \"calculaLinea('"+jsonDetalle[i][0]+"', '"+jsonDetalle[i][20]+"');\""
						+ "onkeydown=\"return ingresoDouble(window.event)\""
						+ "autocomplete=\"off\""
						+ "onchange=\"if(value=='') """),format.raw/*896.35*/("""{"""),format.raw/*896.36*/("""value=0;"""),format.raw/*896.44*/("""}"""),format.raw/*896.45*/(""" """),format.raw/*896.46*/("""else """),format.raw/*896.51*/("""{"""),format.raw/*896.52*/("""calculaTasa(1,'"+jsonDetalle[i][0]+"', '"+jsonDetalle[i][20]+"'); calculaLinea('"+jsonDetalle[i][0]+"', '"+jsonDetalle[i][20]+"');"""),format.raw/*896.182*/("""}"""),format.raw/*896.183*/("""\">"
						+ "</td>"
						+ "<td style=\"text-align:center;\">"
						+ "<div style=\"display:none\" id=\"hiddenpermanencia_"+jsonDetalle[i][0]+"\">"+jsonDetalle[i][29]+"</div>"
						+ "<input type=\"text\" class=\"permGlobal form-control height23px right\""
						+ "name=\"permanencia[]\""
						+ "id=\"permanencia_"+jsonDetalle[i][0]+"\""
						+ "value=\""+jsonDetalle[i][12]+"\""
						+ "onfocus=\"value=value.replace(/,/g,'');\" "
						+ "onblur = \"$('#hiddenpermanencia_"+jsonDetalle[i][0]+"').text(value); value = formatStandar(value, '2');\""
						+ "onkeydown=\"return ingresoDouble(window.event)\""
						+ "autocomplete=\"off\""
						+ "onchange=\"if(value=='') value=0; calculaLinea('"+jsonDetalle[i][0]+"', '"+jsonDetalle[i][20]+"');\">"
						+ "</td>"
						+ "<td class=\"totRepos\" style=\"text-align:right;\" id=\"totRepos_"+jsonDetalle[i][0]+"\">"+jsonDetalle[i][13]+"</td>"
						+ "<td class=\"totArrie\" style=\"text-align:right;\" id=\"totArrie_"+jsonDetalle[i][0]+"\">"+jsonDetalle[i][14]+"</td>"
						+ "<td class=\"totVenta\" style=\"text-align:right;\" id=\"totVenta_"+jsonDetalle[i][0]+"\">"+jsonDetalle[i][15]+"</td>"
						+ "<td class=\"totKg\" style=\"text-align:right;\" id=\"totKg_"+jsonDetalle[i][0]+"\">"+jsonDetalle[i][18]+"</td>";
					
					if(escondeLosM2 == 1) """),format.raw/*915.28*/("""{"""),format.raw/*915.29*/("""
						"""),format.raw/*916.7*/("""tabla +="<td class=\"totM2\" style=\"display:none; text-align:right;\" id=\"totM2_"+jsonDetalle[i][0]+"\">"+jsonDetalle[i][19]+"</td>";
					"""),format.raw/*917.6*/("""}"""),format.raw/*917.7*/("""else"""),format.raw/*917.11*/("""{"""),format.raw/*917.12*/("""
						"""),format.raw/*918.7*/("""tabla +="<td class=\"totM2\" style = \"text-align:right;\" id=\"totM2_"+jsonDetalle[i][0]+"\">"+jsonDetalle[i][19]+"</td>";
					"""),format.raw/*919.6*/("""}"""),format.raw/*919.7*/("""
					
						"""),format.raw/*921.7*/("""tabla +="<td style = \"display:none\" id=\"uniKg_"+jsonDetalle[i][0]+"\">"+jsonDetalle[i][16]+"</td>"
						+ "<td style = \"display:none\" id=\"uniM2_"+jsonDetalle[i][0]+"\">"+jsonDetalle[i][17]+"</td>"
						+ "<td style = \"display:none\" id=\"idEquipo_"+jsonDetalle[i][0]+"\">"+jsonDetalle[i][0]+"</td>"
						+ "<td style = \"display:none\" id=\"nroDecimales_"+jsonDetalle[i][0]+"\">"+jsonDetalle[i][20]+"</td>"
						+ ""
						+ "</TR>";
			"""),format.raw/*927.4*/("""}"""),format.raw/*927.5*/("""

			"""),format.raw/*929.4*/("""tabla +="</tbody>"
				+ "<tfoot id=\"tfoot\" style=\"background-color: #eeeeee; display:none\">"
				+ "<TR> "
				+ "<TH colspan=\"13\" style= \"text-align: right;\">SUB-TOTALES </TH>"
				+ "<TH style=\"text-align:right;\"><div id=\"subtotalRepos\">0.00</div></TH>"
				+ "<TH style=\"text-align:right;\"><div id=\"subtotalArrie\">0.00</div></TH>"
				+ "<TH style=\"text-align:right;\"><div id=\"subtotalVenta\">0.00</div></TH>"
				+ "<TH></TH>";
				
			if(escondeLosM2 == 1) """),format.raw/*938.26*/("""{"""),format.raw/*938.27*/("""
				"""),format.raw/*939.5*/("""tabla +="<TH style = \"display:none\"></TH>";
			"""),format.raw/*940.4*/("""}"""),format.raw/*940.5*/("""else"""),format.raw/*940.9*/("""{"""),format.raw/*940.10*/("""
				"""),format.raw/*941.5*/("""tabla +="<TH></TH>";
			"""),format.raw/*942.4*/("""}"""),format.raw/*942.5*/("""
			
			"""),format.raw/*944.4*/("""tabla +="<TH style = \"display:none\"></TH>"
				+ "<TH style = \"display:none\"></TH>"
				+ "<TH style = \"display:none\"></TH>"
				+ "</TR>"
				+ "<TR> "
				+ "<TH colspan=\"13\" style=\"text-align:right;\">DESCUENTOS</TH>"
				+ "<TH style=\"text-align:right;\"></TH>"
				+ "<TH style=\"text-align:center;\">"
				+ "<input type=\"text\" class=\"form-control height23px right\""
				+ "id=\"dctoArriendo\""
				+ "name=\"dctoArriendo\""
				+ "value=\""""),_display_(/*955.17*/formCotiza/*955.27*/.dctoArriendo),format.raw/*955.40*/(""" """),format.raw/*955.41*/("""%\""
				+ "onfocus=\"value=value.replace(/,/g,'').replace(/%/g,'').replace(/ /g,'')\" "
				+ "onblur = \"value = formatPorcentaje(value);\""
				+ "onkeydown=\"return ingresoDouble(window.event)\""
				+ "onchange=\"sumaTotales(); dctoObligado=1;\">"
				+ "</td>"
				+ "</TH>"
				+ "<TH style=\"text-align:center;\">"
				+ "<input type=\"text\" class=\"form-control height23px right\""
				+ "id=\"dctoVenta\""
				+ "name=\"dctoVenta\""
				+ "value=\""""),_display_(/*966.17*/formCotiza/*966.27*/.dctoVenta),format.raw/*966.37*/(""" """),format.raw/*966.38*/("""%\""
				+ "onfocus=\"value=value.replace(/,/g,'').replace(/%/g,'').replace(/ /g,'')\" "
				+ "onblur = \"value = formatPorcentaje(value);\""
				+ "onkeydown=\"return ingresoDouble(window.event)\""
				+ "onchange=\"sumaTotales(); dctoObligado=1;\">"
				+ "</td>"
				+ "</TH>"
				+ "<TH></TH>";
				
			if(escondeLosM2 == 1) """),format.raw/*975.26*/("""{"""),format.raw/*975.27*/("""
				"""),format.raw/*976.5*/("""tabla +="<TH style = \"display:none\"></TH>";
			"""),format.raw/*977.4*/("""}"""),format.raw/*977.5*/("""else"""),format.raw/*977.9*/("""{"""),format.raw/*977.10*/("""
				"""),format.raw/*978.5*/("""tabla +="<TH></TH>";
			"""),format.raw/*979.4*/("""}"""),format.raw/*979.5*/("""
			
			"""),format.raw/*981.4*/("""tabla +="<TH style = \"display:none\"></TH>"
				+ "<TH style = \"display:none\"></TH>"
				+ "<TH style = \"display:none\"></TH>"
				+ "</TR>"
				+ "<TR>"
				+ "<TH colspan=\"5\" style= \"text-align: right;\">Total cantidad</TH>"
				+ "<TH colspan=\"1\" style= \"text-align: right;\"><div id=\"granTotCant\">0.00</div></TH>"
				+ "<TH colspan=\"7\" style= \"text-align: right;\">TOTAL NETO</TH>"
				+ "<TH style=\"text-align:right;\"><div id=\"granTotRepos\">0.00</div></TH>"
				+ "<TH style=\"text-align:right;\"><div id=\"granTotArrie\">0.00</div></TH>"
				+ "<TH style=\"text-align:right;\"><div id=\"granTotVenta\">0.00</div></TH>"
				+ "<TH style=\"text-align:right;\"><div id=\"granTotKg\">0.00</div></TH>";
				
			if(escondeLosM2 == 1) """),format.raw/*994.26*/("""{"""),format.raw/*994.27*/("""
				"""),format.raw/*995.5*/("""tabla +="<TH style=\"display:none; text-align:right;\"><div id=\"granTotM2\">0.00</div></TH>";
			"""),format.raw/*996.4*/("""}"""),format.raw/*996.5*/("""else"""),format.raw/*996.9*/("""{"""),format.raw/*996.10*/("""
				"""),format.raw/*997.5*/("""tabla +="<TH style = \"text-align:right;\"><div id=\"granTotM2\">0.00</div></TH>";
			"""),format.raw/*998.4*/("""}"""),format.raw/*998.5*/("""
			
			
			"""),format.raw/*1001.4*/("""tabla +="<TH style = \"display:none\"></TH>"
				+ "<TH style = \"display:none\"></TH>"
				+ "<TH style = \"display:none\"></TH>"
				+ "</TR>"
				+ "<TR id='tIva' style='display:none'>"
				+ "<TH colspan=\"13\" style= \"text-align: right;\">IVA </TH>"
				+ "<TH style=\"text-align:right;\"><div id=\"ivaRepos\">0.00</div></TH>"
				+ "<TH style=\"text-align:right;\"><div id=\"ivaArrie\">0.00</div></TH>"
				+ "<TH style=\"text-align:right;\"><div id=\"ivaVenta\">0.00</div></TH>"
				+ "<TH></TH>";
				
			if(escondeLosM2 == 1) """),format.raw/*1012.26*/("""{"""),format.raw/*1012.27*/("""
				"""),format.raw/*1013.5*/("""tabla +="<TH style = \"display:none\"></TH>";
			"""),format.raw/*1014.4*/("""}"""),format.raw/*1014.5*/("""else"""),format.raw/*1014.9*/("""{"""),format.raw/*1014.10*/("""
				"""),format.raw/*1015.5*/("""tabla +="<TH></TH>";
			"""),format.raw/*1016.4*/("""}"""),format.raw/*1016.5*/("""
			
			
			"""),format.raw/*1019.4*/("""tabla +="<TH style = \"display:none\"></TH>"
				+ "<TH style = \"display:none\"></TH>"
				+ "<TH style = \"display:none\"></TH>"
				+ "</TR>"
				+ "<TR id='tTotal' style='display:none'>"
				+ "<TH colspan=\"13\" style= \"text-align: right;\">TOTAL CON IVA </TH>"
				+ "<TH style=\"text-align:right;\"><div id=\"totalConIvaRepos\">0.00</div></TH>"
				+ "<TH style=\"text-align:right;\"><div id=\"totalConIvaArrie\">0.00</div></TH>"
				+ "<TH style=\"text-align:right;\"><div id=\"totalConIvaVenta\">0.00</div></TH>"
				+ "<TH></TH>";
				
			if(escondeLosM2 == 1) """),format.raw/*1030.26*/("""{"""),format.raw/*1030.27*/("""
				"""),format.raw/*1031.5*/("""tabla +="<TH style = \"display:none\"></TH>";
			"""),format.raw/*1032.4*/("""}"""),format.raw/*1032.5*/("""else"""),format.raw/*1032.9*/("""{"""),format.raw/*1032.10*/("""
				"""),format.raw/*1033.5*/("""tabla +="<TH></TH>";
			"""),format.raw/*1034.4*/("""}"""),format.raw/*1034.5*/("""
			
			
			
			"""),format.raw/*1038.4*/("""tabla +="<TH style = \"display:none\"></TH>"
				+ "<TH style = \"display:none\"></TH>"
				+ "<TH style = \"display:none\"></TH>"
				+ "</TR>"
				+ "</tfoot>"
				+ "</table>";

		document.getElementById('tblPrincipal').innerHTML = tabla;
		
		$('#tablaListaClientes').DataTable("""),format.raw/*1047.38*/("""{"""),format.raw/*1047.39*/("""
	    	"""),format.raw/*1048.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
	    	"order": [[ 1, "asc" ]],
	    	"language": """),format.raw/*1051.19*/("""{"""),format.raw/*1051.20*/("""
	        	"""),format.raw/*1052.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*1053.10*/("""}"""),format.raw/*1053.11*/("""
	  	"""),format.raw/*1054.5*/("""}"""),format.raw/*1054.6*/(""" """),format.raw/*1054.7*/(""");

		$('#tablaListaProyectos').DataTable("""),format.raw/*1056.39*/("""{"""),format.raw/*1056.40*/("""
	    	"""),format.raw/*1057.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
	    	"order": [[ 0, "asc" ]],
	    	"language": """),format.raw/*1060.19*/("""{"""),format.raw/*1060.20*/("""
	        	"""),format.raw/*1061.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*1062.10*/("""}"""),format.raw/*1062.11*/("""
	  	"""),format.raw/*1063.5*/("""}"""),format.raw/*1063.6*/(""" """),format.raw/*1063.7*/(""");

		sumaTotales();
		
		$('#tablaPrincipal').DataTable("""),format.raw/*1067.34*/("""{"""),format.raw/*1067.35*/("""
		    	"""),format.raw/*1068.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[15, 50, 100, 200, -1], [15, 50, 100, 200, "All"]],
		    	"order": [[ 0, "asc" ],[ 2, "asc" ]],
		    	"language": """),format.raw/*1071.20*/("""{"""),format.raw/*1071.21*/("""
		        	"""),format.raw/*1072.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*1073.11*/("""}"""),format.raw/*1073.12*/("""
		"""),format.raw/*1074.3*/("""}"""),format.raw/*1074.4*/(""" """),format.raw/*1074.5*/(""");
		
		document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*1077.2*/("""}"""),format.raw/*1077.3*/(""");
	
	const validarNumero = (numero) =>"""),format.raw/*1079.35*/("""{"""),format.raw/*1079.36*/("""
		"""),format.raw/*1080.3*/("""var formData = new FormData();
		formData.append('numeroCoti',numero);
        $.ajax("""),format.raw/*1082.16*/("""{"""),format.raw/*1082.17*/("""
            """),format.raw/*1083.13*/("""url: "/existeNumeroCotizacionAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*1090.36*/("""{"""),format.raw/*1090.37*/("""
				"""),format.raw/*1091.5*/("""if(respuesta!="OK")"""),format.raw/*1091.24*/("""{"""),format.raw/*1091.25*/("""
					"""),format.raw/*1092.6*/("""$("#numeroCoti").val(""""),_display_(/*1092.29*/formCotiza/*1092.39*/.numeroCoti),format.raw/*1092.50*/("""");
					alertify.alert(respuesta, function () """),format.raw/*1093.44*/("""{"""),format.raw/*1093.45*/(""" """),format.raw/*1093.46*/("""}"""),format.raw/*1093.47*/(""");
				"""),format.raw/*1094.5*/("""}"""),format.raw/*1094.6*/("""
	     	"""),format.raw/*1095.8*/("""}"""),format.raw/*1095.9*/("""
        """),format.raw/*1096.9*/("""}"""),format.raw/*1096.10*/(""");
	"""),format.raw/*1097.2*/("""}"""),format.raw/*1097.3*/("""
	
	"""),format.raw/*1099.2*/("""const vistaStockPorEquipo = (id_equipo) =>"""),format.raw/*1099.44*/("""{"""),format.raw/*1099.45*/("""
		"""),format.raw/*1100.3*/("""var formData = new FormData();
		formData.append('id_equipo',id_equipo);
        $.ajax("""),format.raw/*1102.16*/("""{"""),format.raw/*1102.17*/("""
            """),format.raw/*1103.13*/("""url: "/tablaInvPorEquipoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*1110.36*/("""{"""),format.raw/*1110.37*/("""
				"""),format.raw/*1111.5*/("""document.getElementById('stockPorEquipo').innerHTML = respuesta;
				$('#modalStockPorEquipo').modal('show');
	     	"""),format.raw/*1113.8*/("""}"""),format.raw/*1113.9*/("""
        """),format.raw/*1114.9*/("""}"""),format.raw/*1114.10*/(""");
	"""),format.raw/*1115.2*/("""}"""),format.raw/*1115.3*/("""
	
	
	"""),format.raw/*1118.2*/("""const sumaTotales = () =>"""),format.raw/*1118.27*/("""{"""),format.raw/*1118.28*/("""
		"""),format.raw/*1119.3*/("""$("#tablaPrincipal").dataTable().fnDestroy();
		let tabla = document.getElementById("tablaPrincipal");
		let totCant = 0;
		
		let cant = 0;
		$(".cantidad").each(function() """),format.raw/*1124.34*/("""{"""),format.raw/*1124.35*/("""
			"""),format.raw/*1125.4*/("""let val = $(this).val().replace(/,/g,'');
			cant += parseFloat(val);
		"""),format.raw/*1127.3*/("""}"""),format.raw/*1127.4*/(""");
		
		let repos = 0;
		$(".totRepos").each(function() """),format.raw/*1130.34*/("""{"""),format.raw/*1130.35*/("""
			"""),format.raw/*1131.4*/("""let val = $(this).text().replace(/,/g,'');
			repos += parseFloat(val);
		"""),format.raw/*1133.3*/("""}"""),format.raw/*1133.4*/("""); $("#subtotalRepos").text(formatStandar(repos,'"""),_display_(/*1133.54*/numDecParaTot),format.raw/*1133.67*/("""'));
		
		let arr = 0;
		$(".totArrie").each(function() """),format.raw/*1136.34*/("""{"""),format.raw/*1136.35*/("""
			"""),format.raw/*1137.4*/("""let val = $(this).text().replace(/,/g,'');
			arr += parseFloat(val);
		"""),format.raw/*1139.3*/("""}"""),format.raw/*1139.4*/("""); $("#subtotalArrie").text(formatStandar(arr,'"""),_display_(/*1139.52*/numDecParaTot),format.raw/*1139.65*/("""'));
		
		let vta = 0;
		$(".totVenta").each(function() """),format.raw/*1142.34*/("""{"""),format.raw/*1142.35*/("""
			"""),format.raw/*1143.4*/("""let val = $(this).text().replace(/,/g,'');
			vta += parseFloat(val);
		"""),format.raw/*1145.3*/("""}"""),format.raw/*1145.4*/("""); $("#subtotalVenta").text(formatStandar(vta,'"""),_display_(/*1145.52*/numDecParaTot),format.raw/*1145.65*/("""'));
		
		let kg = 0;
		$(".totKg").each(function() """),format.raw/*1148.31*/("""{"""),format.raw/*1148.32*/("""
			"""),format.raw/*1149.4*/("""let val = $(this).text().replace(/,/g,'');
			kg += parseFloat(val);
		"""),format.raw/*1151.3*/("""}"""),format.raw/*1151.4*/(""");
		
		let m2 = 0;
		$(".totM2").each(function() """),format.raw/*1154.31*/("""{"""),format.raw/*1154.32*/("""
			"""),format.raw/*1155.4*/("""let val = $(this).text().replace(/,/g,'');
			m2 += parseFloat(val);
		"""),format.raw/*1157.3*/("""}"""),format.raw/*1157.4*/(""");
		
		let dctoArriendo = $("#dctoArriendo").val().replace(/,/g,'').replace("%","").trim();
		let dctoVenta = $("#dctoVenta").val().replace(/,/g,'').replace("%","").trim();
		
		$("#granTotRepos").text(formatStandar(repos,'"""),_display_(/*1162.49*/numDecParaTot),format.raw/*1162.62*/("""'));
		$("#granTotArrie").text(formatStandar((arr*(1-dctoArriendo/100)),'"""),_display_(/*1163.70*/numDecParaTot),format.raw/*1163.83*/("""'));
		$("#granTotVenta").text(formatStandar((vta*(1-dctoVenta/100)),'"""),_display_(/*1164.67*/numDecParaTot),format.raw/*1164.80*/("""'));
		$("#granTotKg").text(formatStandar(kg,2));
		$("#granTotM2").text(formatStandar(m2,2));
		$("#granTotCant").text(formatStandar(cant,2));
		
	"""),format.raw/*1169.2*/("""}"""),format.raw/*1169.3*/("""
	
	"""),format.raw/*1171.2*/("""const checkVenta = (id_equipo) =>"""),format.raw/*1171.35*/("""{"""),format.raw/*1171.36*/("""
		"""),format.raw/*1172.3*/("""let esVenta = $("#esVenta_"+id_equipo).val();
		if(esVenta==0)"""),format.raw/*1173.17*/("""{"""),format.raw/*1173.18*/("""
			"""),format.raw/*1174.4*/("""$("#esVenta_"+id_equipo).val('1');
		"""),format.raw/*1175.3*/("""}"""),format.raw/*1175.4*/("""else"""),format.raw/*1175.8*/("""{"""),format.raw/*1175.9*/("""
			"""),format.raw/*1176.4*/("""$("#esVenta_"+id_equipo).val('0');
		"""),format.raw/*1177.3*/("""}"""),format.raw/*1177.4*/("""
	"""),format.raw/*1178.2*/("""}"""),format.raw/*1178.3*/("""
	
	"""),format.raw/*1180.2*/("""let calculaTasa = (flag, id_equipo, numDec) => """),format.raw/*1180.49*/("""{"""),format.raw/*1180.50*/("""
		"""),format.raw/*1181.3*/("""if(flag==1)"""),format.raw/*1181.14*/("""{"""),format.raw/*1181.15*/("""
			"""),format.raw/*1182.4*/("""let uniVta = $("#puVentaRepos_"+id_equipo).val().replace(/,/g,'');
			let uniArr = $("#puArriendo_"+id_equipo).val().replace(/,/g,'');
			let uniTasa = parseFloat(uniArr)/parseFloat(uniVta);
			$("#tasaArr_"+id_equipo).val(formatStandar(uniTasa * 100,2)+" %");
			$("#hiddentasaArr_"+id_equipo).text(uniTasa);
		"""),format.raw/*1187.3*/("""}"""),format.raw/*1187.4*/("""else if(flag==2)"""),format.raw/*1187.20*/("""{"""),format.raw/*1187.21*/("""
			"""),format.raw/*1188.4*/("""let uniVta = $("#puVentaRepos_"+id_equipo).val().replace(/,/g,'');
			let uniTasa = $("#tasaArr_"+id_equipo).val().replace(/,/g,'').replace(/%/g,'').replace(/ /g,'');
			let uniArr = parseFloat(uniVta)*parseFloat(uniTasa)/100;
			
			let idMon = $("#id_moneda_"+id_equipo).val();
			numDec = mapMoneda.get(parseFloat(idMon));
			
			$("#puArriendo_"+id_equipo).val(formatStandar(uniArr,numDec));
			$("#hiddentasaArr_"+id_equipo).text(uniTasa);
		"""),format.raw/*1197.3*/("""}"""),format.raw/*1197.4*/("""else"""),format.raw/*1197.8*/("""{"""),format.raw/*1197.9*/("""
			"""),format.raw/*1198.4*/("""let uniVta = $("#puVentaRepos_"+id_equipo).val().replace(/,/g,'');
			let uniArr = $("#puArriendo_"+id_equipo).val().replace(/,/g,'');
			let uniTasa = parseFloat(uniArr)/parseFloat(uniVta);
			$("#tasaArr_"+id_equipo).val(formatStandar(uniTasa * 100,2)+" %");
			$("#hiddentasaArr_"+id_equipo).text(uniTasa);
		"""),format.raw/*1203.3*/("""}"""),format.raw/*1203.4*/("""
		
	"""),format.raw/*1205.2*/("""}"""),format.raw/*1205.3*/("""
	
	"""),format.raw/*1207.2*/("""const calculaLinea = (id_equipo, numDec) =>"""),format.raw/*1207.45*/("""{"""),format.raw/*1207.46*/("""
		"""),format.raw/*1208.3*/("""let cant = $("#cantidad_"+id_equipo).val().replace(/,/g,'');
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
		if(esVenta == 0)"""),format.raw/*1221.19*/("""{"""),format.raw/*1221.20*/("""
			 """),format.raw/*1222.5*/("""totRepos = parseFloat(cant) * parseFloat(uniVta);
			 totArr = parseFloat(cant) * parseFloat(perm) * parseFloat(uniArr);
			 totVta = 0;
			 totKg = parseFloat(cant) * parseFloat(uniKg);
			 totM2 = parseFloat(cant) * parseFloat(uniM2);
		"""),format.raw/*1227.3*/("""}"""),format.raw/*1227.4*/("""else"""),format.raw/*1227.8*/("""{"""),format.raw/*1227.9*/("""
			 """),format.raw/*1228.5*/("""totRepos = 0;
			 totArr = 0;
			 totVta = parseFloat(cant) * parseFloat(uniVta);
			 totKg = parseFloat(cant) * parseFloat(uniKg);
			 totM2 = parseFloat(cant) * parseFloat(uniM2);
		"""),format.raw/*1233.3*/("""}"""),format.raw/*1233.4*/("""
		
		"""),format.raw/*1235.3*/("""let idMon = $("#id_moneda_"+id_equipo).val();
		numDec = mapMoneda.get(parseFloat(idMon));
		
		$("#cantidad_"+id_equipo).val(formatStandar(cant,2));
		$("#puVentaRepos_"+id_equipo).val(formatStandar(uniVta,numDec));
		
		$("#nroDecimales_"+id_equipo).text(numDec);
		$("#puArriendo_"+id_equipo).val(formatStandar(uniArr,numDec));
		
		
		$("#totRepos_"+id_equipo).text(formatStandar(totRepos,numDec));
		$("#totArrie_"+id_equipo).text(formatStandar(totArr,numDec));
		$("#totVenta_"+id_equipo).text(formatStandar(totVta,numDec));
		$("#totKg_"+id_equipo).text(formatStandar(totKg,2));
		$("#totM2_"+id_equipo).text(formatStandar(totM2,2));
		
		$("#hiddencantidad_"+id_equipo).text(cant);
		$("#hiddenpuVentaRepos_"+id_equipo).text(uniVta);
		$("#hiddenpuArriendo_"+id_equipo).text(uniArr);
		
		
		/*
		if(verificando == 1)"""),format.raw/*1257.23*/("""{"""),format.raw/*1257.24*/("""
			"""),format.raw/*1258.4*/("""sumaTotales();
		"""),format.raw/*1259.3*/("""}"""),format.raw/*1259.4*/("""
		"""),format.raw/*1260.3*/("""*/
	"""),format.raw/*1261.2*/("""}"""),format.raw/*1261.3*/("""
	
	"""),format.raw/*1263.2*/("""let verificando = 0;
	const verificaCotizacion = () =>"""),format.raw/*1264.34*/("""{"""),format.raw/*1264.35*/("""
		"""),format.raw/*1265.3*/("""document.getElementById("btnCopiaCoti").style.display = "none";
		document.getElementById("btnCargaExcel").style.display = "none";
		if('"""),_display_(/*1267.8*/mapPermiso/*1267.18*/.get("parametro.coti8columnas")),format.raw/*1267.49*/("""'!=null && '"""),_display_(/*1267.62*/mapPermiso/*1267.72*/.get("parametro.coti8columnas")),format.raw/*1267.103*/("""' == "1") """),format.raw/*1267.113*/("""{"""),format.raw/*1267.114*/("""
				"""),format.raw/*1268.5*/("""document.getElementById("btnImport8column").style.display = "none";
		"""),format.raw/*1269.3*/("""}"""),format.raw/*1269.4*/("""
		
		"""),format.raw/*1271.3*/("""sumaTotales();
		let granTotRepos = $("#granTotRepos").text().replace(/,/g,'');
		let granTotArrie = $("#granTotArrie").text().replace(/,/g,'');
		let granTotVenta = $("#granTotVenta").text().replace(/,/g,'');
		let sumaAux = parseFloat(granTotRepos) + parseFloat(granTotArrie) + parseFloat(granTotVenta);
		if(sumaAux > 0)"""),format.raw/*1276.18*/("""{"""),format.raw/*1276.19*/("""
			"""),format.raw/*1277.4*/("""let tabla = document.getElementById("tablaPrincipal");
			for(i=2; i<tabla.rows.length-5; i++)"""),format.raw/*1278.40*/("""{"""),format.raw/*1278.41*/("""
				"""),format.raw/*1279.5*/("""let cant = $(".cantidad",tabla.rows[i].cells[5]).val().replace(/,/g,'');
				let arr = tabla.rows[i].cells[14].innerHTML.replace(/,/g,'');
				let vta = tabla.rows[i].cells[15].innerHTML.replace(/,/g,'');
				if((parseFloat(cant)+parseFloat(arr)+parseFloat(vta))<=0)"""),format.raw/*1282.62*/("""{"""),format.raw/*1282.63*/("""
					"""),format.raw/*1283.6*/("""tabla.rows[i].style.display = 'none';
				"""),format.raw/*1284.5*/("""}"""),format.raw/*1284.6*/("""
			"""),format.raw/*1285.4*/("""}"""),format.raw/*1285.5*/("""
			
			"""),format.raw/*1287.4*/("""let iva = parseFloat(""""),_display_(/*1287.27*/iva),format.raw/*1287.30*/("""");
			
			$("#ivaRepos").text(formatStandar(granTotRepos*iva),'"""),_display_(/*1289.58*/numDecParaTot),format.raw/*1289.71*/("""');
			$("#ivaArrie").text(formatStandar(granTotArrie*iva),'"""),_display_(/*1290.58*/numDecParaTot),format.raw/*1290.71*/("""');
			$("#ivaVenta").text(formatStandar(granTotVenta*iva),'"""),_display_(/*1291.58*/numDecParaTot),format.raw/*1291.71*/("""');
			
			$("#totalConIvaRepos").text(formatStandar(formatStandar(granTotRepos*(1+iva)),'"""),_display_(/*1293.84*/numDecParaTot),format.raw/*1293.97*/("""'));
			$("#totalConIvaArrie").text(formatStandar(formatStandar(granTotArrie*(1+iva)),'"""),_display_(/*1294.84*/numDecParaTot),format.raw/*1294.97*/("""'));
			$("#totalConIvaVenta").text(formatStandar(formatStandar(granTotVenta*(1+iva)),'"""),_display_(/*1295.84*/numDecParaTot),format.raw/*1295.97*/("""'));
			
			"""),_display_(if(mapPermiso.get("parametro.cotizaciones-con-iva")!=null && mapPermiso.get("parametro.cotizaciones-con-iva").equals("1"))/*1297.127*/{_display_(Seq[Any](format.raw/*1297.128*/("""
				"""),format.raw/*1298.5*/("""document.getElementById('tIva').style.display = '';
				document.getElementById('tTotal').style.display = '';
			""")))} else {null} ),format.raw/*1300.5*/("""
			
			"""),format.raw/*1302.4*/("""verificando = 1;
			document.getElementById('tfoot').style.display = '';
			document.getElementById('modifica').style.visibility = 'visible';
			document.getElementById('aplica').style.visibility = 'visible';
			document.getElementById('verifica').style.visibility = 'hidden';
			document.getElementById('selSucursal').disabled = true;
			document.getElementById('selComercial').disabled = true;
			$('#btnAplicarTasa').hide();
			$('#titAplicarFactorRepos').hide();
			$('#btnAplicarFactorRepos').hide();
			$('#titAplicarFactorArr').hide();
			$('#btnAplicarFactorArr').hide();
			$('#btnAplicarPerm').hide();
			
	
		"""),format.raw/*1317.3*/("""}"""),format.raw/*1317.4*/("""else"""),format.raw/*1317.8*/("""{"""),format.raw/*1317.9*/("""
			"""),format.raw/*1318.4*/("""$('#tablaPrincipal').DataTable("""),format.raw/*1318.35*/("""{"""),format.raw/*1318.36*/("""
		    	"""),format.raw/*1319.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[15, 50, 100, 200, -1], [15, 50, 100, 200, "All"]],
		    	"order": [[ 0, "asc" ],[ 2, "asc" ]],
		    	"language": """),format.raw/*1322.20*/("""{"""),format.raw/*1322.21*/("""
		        	"""),format.raw/*1323.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*1324.11*/("""}"""),format.raw/*1324.12*/("""
			"""),format.raw/*1325.4*/("""}"""),format.raw/*1325.5*/(""" """),format.raw/*1325.6*/(""");
			alertify.alert('NO PUEDE VERIFICAR UNA COTIZACION SIN UN VALOR O PRECIO IGUAL A CERO (al menos uno de los items con cantidades a cotizar debe estar valorizado)', function () """),format.raw/*1326.178*/("""{"""),format.raw/*1326.179*/(""" """),format.raw/*1326.180*/("""}"""),format.raw/*1326.181*/(""");
		"""),format.raw/*1327.3*/("""}"""),format.raw/*1327.4*/("""
	"""),format.raw/*1328.2*/("""}"""),format.raw/*1328.3*/("""
	
	"""),format.raw/*1330.2*/("""const modificaCotizacion = () =>"""),format.raw/*1330.34*/("""{"""),format.raw/*1330.35*/("""
		"""),format.raw/*1331.3*/("""let tabla = document.getElementById("tablaPrincipal");
		for(i=2; i<tabla.rows.length-5; i++)"""),format.raw/*1332.39*/("""{"""),format.raw/*1332.40*/("""
			"""),format.raw/*1333.4*/("""tabla.rows[i].style.display = '';
		"""),format.raw/*1334.3*/("""}"""),format.raw/*1334.4*/("""
		"""),format.raw/*1335.3*/("""$('#tablaPrincipal').DataTable("""),format.raw/*1335.34*/("""{"""),format.raw/*1335.35*/("""
		    	"""),format.raw/*1336.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[15, 50, 100, 200, -1], [15, 50, 100, 200, "All"]],
		    	"order": [[ 0, "asc" ],[ 2, "asc" ]],
		    	"language": """),format.raw/*1339.20*/("""{"""),format.raw/*1339.21*/("""
		        	"""),format.raw/*1340.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*1341.11*/("""}"""),format.raw/*1341.12*/("""
		"""),format.raw/*1342.3*/("""}"""),format.raw/*1342.4*/(""" """),format.raw/*1342.5*/(""");
		document.getElementById('tfoot').style.display = 'none';
		document.getElementById('modifica').style.visibility = 'hidden';
		document.getElementById('aplica').style.visibility = 'hidden';
		document.getElementById('verifica').style.visibility = 'visible';
		document.getElementById('selSucursal').disabled = false;
		document.getElementById('selComercial').disabled = false;
		$('#btnAplicarTasa').show();
		$('#titAplicarFactorRepos').show();
		$('#btnAplicarFactorRepos').show();
		$('#titAplicarFactorArr').show();
		$('#btnAplicarFactorArr').show();
		$('#btnAplicarPerm').show();
			
		verificando = 0;
	"""),format.raw/*1357.2*/("""}"""),format.raw/*1357.3*/("""
	
	"""),format.raw/*1359.2*/("""let importDesdeOtraCoti = "0";
	const seleccionaCotizacion = (id_cotizacion) =>"""),format.raw/*1360.49*/("""{"""),format.raw/*1360.50*/("""
		"""),format.raw/*1361.3*/("""document.getElementById("btnCopiaCoti").style.display = "none";
		document.getElementById("btnCargaExcel").style.display = "none";
		if('"""),_display_(/*1363.8*/mapPermiso/*1363.18*/.get("parametro.coti8columnas")),format.raw/*1363.49*/("""'!=null && '"""),_display_(/*1363.62*/mapPermiso/*1363.72*/.get("parametro.coti8columnas")),format.raw/*1363.103*/("""' == "1") """),format.raw/*1363.113*/("""{"""),format.raw/*1363.114*/("""
				"""),format.raw/*1364.5*/("""document.getElementById("btnImport8column").style.display = "none";
		"""),format.raw/*1365.3*/("""}"""),format.raw/*1365.4*/("""
		
		"""),format.raw/*1367.3*/("""let formData = new FormData();
	    formData.append('id_cotizacion',id_cotizacion);
		$.ajax("""),format.raw/*1369.10*/("""{"""),format.raw/*1369.11*/("""
            """),format.raw/*1370.13*/("""url: "/cotizaModificaJson/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(rs)"""),format.raw/*1377.29*/("""{"""),format.raw/*1377.30*/("""
				
				"""),format.raw/*1379.5*/("""let obj = rs["detalle"];
				let coti = rs["cotizacion"];
		
	     		let mapCantidad = new Map();
	     		let mapEsVenta = new Map();
	     		let mapPuReposicion = new Map();
	     		let mapPuArriendo = new Map();
	     		let mapPermanencia = new Map();
	     		let mapNroDecimal = new Map();
	     		
	     		for(i=0;i<obj.length;i++)"""),format.raw/*1389.34*/("""{"""),format.raw/*1389.35*/("""
	     			"""),format.raw/*1390.10*/("""mapCantidad.set(obj[i].id_equipo,obj[i].cantidad);
	     			mapEsVenta.set(obj[i].id_equipo,obj[i].esVenta);
	     			mapPuReposicion.set(obj[i].id_equipo,obj[i].puReposicion);
	     			mapPuArriendo.set(obj[i].id_equipo,obj[i].puArriendo);
	     			mapPermanencia.set(obj[i].id_equipo,obj[i].permanencia);
	     			mapNroDecimal.set(obj[i].id_equipo,obj[i].nroDecimal);
	     		"""),format.raw/*1396.9*/("""}"""),format.raw/*1396.10*/("""
	     		
				"""),format.raw/*1398.5*/("""$("#tablaPrincipal").dataTable().fnDestroy();
				let tableReg = document.getElementById("tablaPrincipal");
				
				for (let i = 2; i < tableReg.rows.length-5; i++)"""),format.raw/*1401.53*/("""{"""),format.raw/*1401.54*/("""
					"""),format.raw/*1402.6*/("""let cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
					let id_equipo = cellsOfRow[20].innerHTML;
					
					let cantidad = mapCantidad.get(id_equipo);
					
					if(cantidad != null)"""),format.raw/*1407.26*/("""{"""),format.raw/*1407.27*/("""
						"""),format.raw/*1408.7*/("""let esVenta = mapEsVenta.get(id_equipo);
						let puReposicion = mapPuReposicion.get(id_equipo);
						let puArriendo = mapPuArriendo.get(id_equipo);
						let permanencia = mapPermanencia.get(id_equipo);
						let nroDecimales = mapNroDecimal.get(id_equipo);
						
			 			if(cantidad == null) cantidad = 0;
						if(esVenta == null) esVenta = 0;
						if(puReposicion == null) puReposicion = 0;
						if(puArriendo == null) puArriendo = 0;
						if(permanencia == null) permanencia = 0;
						if(nroDecimales == null) nroDecimales = 0;
						
						$("#cantidad_"+id_equipo).val(cantidad);
						$("#esVenta_"+id_equipo).val(esVenta);
						if(esVenta==1)"""),format.raw/*1423.21*/("""{"""),format.raw/*1423.22*/("""
							"""),format.raw/*1424.8*/("""document.getElementById("checkbox_"+id_equipo).checked = true;
						"""),format.raw/*1425.7*/("""}"""),format.raw/*1425.8*/("""else"""),format.raw/*1425.12*/("""{"""),format.raw/*1425.13*/("""
							"""),format.raw/*1426.8*/("""document.getElementById("checkbox_"+id_equipo).checked = false;
						"""),format.raw/*1427.7*/("""}"""),format.raw/*1427.8*/("""
						"""),format.raw/*1428.7*/("""$("#puVentaRepos_"+id_equipo).val(puReposicion);
						$("#puArriendo_"+id_equipo).val(puArriendo);
						$("#permanencia_"+id_equipo).val(permanencia);
						calculaLinea(id_equipo, nroDecimales);
					"""),format.raw/*1432.6*/("""}"""),format.raw/*1432.7*/("""
					
				"""),format.raw/*1434.5*/("""}"""),format.raw/*1434.6*/("""
				"""),format.raw/*1435.5*/("""sumaTotales();
				
				let obser = "Origen coti nro."+coti["numero"]+"\n"+coti["observaciones"];
				$("#observaciones").val(obser);
				
				noCambiarPorSucursal = "1";
				
				importDesdeOtraCoti = 1;
				$('#tablaPrincipal').DataTable("""),format.raw/*1443.36*/("""{"""),format.raw/*1443.37*/("""
			    	"""),format.raw/*1444.9*/(""""fixedHeader": true,
			    	"lengthMenu": [[15, 50, 100, 200, -1], [15, 50, 100, 200, "All"]],
			    	"order": [[ 0, "asc" ],[ 2, "asc" ]],
			    	"language": """),format.raw/*1447.21*/("""{"""),format.raw/*1447.22*/("""
			        	"""),format.raw/*1448.13*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
			        """),format.raw/*1449.12*/("""}"""),format.raw/*1449.13*/("""
				"""),format.raw/*1450.5*/("""}"""),format.raw/*1450.6*/(""");
	     	"""),format.raw/*1451.8*/("""}"""),format.raw/*1451.9*/("""


        """),format.raw/*1454.9*/("""}"""),format.raw/*1454.10*/(""");
	"""),format.raw/*1455.2*/("""}"""),format.raw/*1455.3*/("""
	
	"""),format.raw/*1457.2*/("""if(""""),_display_(/*1457.7*/importDesdeExcel),format.raw/*1457.23*/("""" == "1")"""),format.raw/*1457.32*/("""{"""),format.raw/*1457.33*/("""
		"""),format.raw/*1458.3*/("""document.getElementById("btnCopiaCoti").style.display = "none";
		document.getElementById("btnCargaExcel").style.display = "none";
		if('"""),_display_(/*1460.8*/mapPermiso/*1460.18*/.get("parametro.coti8columnas")),format.raw/*1460.49*/("""'!=null && '"""),_display_(/*1460.62*/mapPermiso/*1460.72*/.get("parametro.coti8columnas")),format.raw/*1460.103*/("""' == "1") """),format.raw/*1460.113*/("""{"""),format.raw/*1460.114*/("""
				"""),format.raw/*1461.5*/("""document.getElementById("btnImport8column").style.display = "none";
		"""),format.raw/*1462.3*/("""}"""),format.raw/*1462.4*/("""
		
	"""),format.raw/*1464.2*/("""}"""),format.raw/*1464.3*/("""
	
	
	
	"""),format.raw/*1468.2*/("""let dctoObligado = 0;
	const validarForm = () =>"""),format.raw/*1469.27*/("""{"""),format.raw/*1469.28*/("""
		"""),format.raw/*1470.3*/("""$("#aplica").attr('disabled',true);
		let flag = false;
		sumaTotales();
		let granTotRepos = $("#granTotRepos").text().replace(/,/g,'');
		let granTotArrie = $("#granTotArrie").text().replace(/,/g,'');
		let granTotVenta = $("#granTotVenta").text().replace(/,/g,'');
		let sumaAux = parseFloat(granTotRepos) + parseFloat(granTotArrie) + parseFloat(granTotVenta);
		
		
		if(sumaAux <= 0)"""),format.raw/*1479.19*/("""{"""),format.raw/*1479.20*/("""
			"""),format.raw/*1480.4*/("""alertify.alert('NO PUEDE GENERAR UNA COTIZACION SIN CANTIDADES', function () """),format.raw/*1480.81*/("""{"""),format.raw/*1480.82*/("""
				"""),format.raw/*1481.5*/("""$("#aplica").attr('disabled',false);
				return(flag);
     		"""),format.raw/*1483.8*/("""}"""),format.raw/*1483.9*/(""");
		"""),format.raw/*1484.3*/("""}"""),format.raw/*1484.4*/("""else if($("#id_cliente").val()=='0')"""),format.raw/*1484.40*/("""{"""),format.raw/*1484.41*/("""
			"""),format.raw/*1485.4*/("""alertify.alert('NO PUEDE GENERAR UNA COTIZACION SIN ASIGNAR UN CLIENTE', function () """),format.raw/*1485.89*/("""{"""),format.raw/*1485.90*/("""
				"""),format.raw/*1486.5*/("""$("#aplica").attr('disabled',false);
				return(flag);
     		"""),format.raw/*1488.8*/("""}"""),format.raw/*1488.9*/(""");
		"""),format.raw/*1489.3*/("""}"""),format.raw/*1489.4*/("""else if(dctoObligado==0 && '"""),_display_(/*1489.33*/mapDiccionario/*1489.47*/.get("nEmpresa")),format.raw/*1489.63*/("""' == 'MONTAX')"""),format.raw/*1489.77*/("""{"""),format.raw/*1489.78*/("""
			"""),format.raw/*1490.4*/("""alertify.alert('ESTA OBLIGADO A INGRESAR UN DESCUENTO, SI NO APLICA DESCUENTO INGREZAR UN CERO', function () """),format.raw/*1490.113*/("""{"""),format.raw/*1490.114*/("""
				"""),format.raw/*1491.5*/("""$("#aplica").attr('disabled',false);
				return(flag);
     		"""),format.raw/*1493.8*/("""}"""),format.raw/*1493.9*/(""");
		"""),format.raw/*1494.3*/("""}"""),format.raw/*1494.4*/("""else if ((sumaAux-parseFloat(granTotRepos))<=0)"""),format.raw/*1494.51*/("""{"""),format.raw/*1494.52*/("""
			"""),format.raw/*1495.4*/("""alertify.alert('NO PUEDE GENERAR UNA COTIZACION QUE SUMA CERO, REVISAR PERMANENCIA', function () """),format.raw/*1495.101*/("""{"""),format.raw/*1495.102*/("""
				"""),format.raw/*1496.5*/("""$("#aplica").attr('disabled',false);
				return(flag);
     		"""),format.raw/*1498.8*/("""}"""),format.raw/*1498.9*/(""");
		"""),format.raw/*1499.3*/("""}"""),format.raw/*1499.4*/("""else"""),format.raw/*1499.8*/("""{"""),format.raw/*1499.9*/("""
			"""),format.raw/*1500.4*/("""let tabla = document.getElementById("tablaPrincipal");
			for(i=2; i<tabla.rows.length-5; i++)"""),format.raw/*1501.40*/("""{"""),format.raw/*1501.41*/("""
				"""),format.raw/*1502.5*/("""let cant = $(".cantidad",tabla.rows[i].cells[5]).val().replace(/,/g,'');
				let arr = tabla.rows[i].cells[13].innerHTML.replace(/,/g,'');
				let vta = tabla.rows[i].cells[14].innerHTML.replace(/,/g,'');
				if((parseFloat(cant)+parseFloat(arr)+parseFloat(vta))<=0)"""),format.raw/*1505.62*/("""{"""),format.raw/*1505.63*/("""
					"""),format.raw/*1506.6*/("""tabla.deleteRow(i);
					i--;
				"""),format.raw/*1508.5*/("""}"""),format.raw/*1508.6*/("""
			"""),format.raw/*1509.4*/("""}"""),format.raw/*1509.5*/("""
			"""),format.raw/*1510.4*/("""return(true);
		"""),format.raw/*1511.3*/("""}"""),format.raw/*1511.4*/("""
		"""),format.raw/*1512.3*/("""return(flag);
	"""),format.raw/*1513.2*/("""}"""),format.raw/*1513.3*/("""

	"""),format.raw/*1515.2*/("""let extArray = new Array(".gif", ".jpg", ".png", ".jpeg", ".pdf", ".xls", ".doc", ".xlsx", ".docx", ".tar", ".zip", ".rar");
	const LimitAttach = (form, file) => """),format.raw/*1516.38*/("""{"""),format.raw/*1516.39*/("""
		"""),format.raw/*1517.3*/("""let allowSubmit = false;
		if (!file) return;
		while (file.indexOf("\\") != -1)"""),format.raw/*1519.35*/("""{"""),format.raw/*1519.36*/("""
			"""),format.raw/*1520.4*/("""file = file.slice(file.indexOf("\\") + 1);
		"""),format.raw/*1521.3*/("""}"""),format.raw/*1521.4*/("""
		"""),format.raw/*1522.3*/("""let extencion = file.slice(file.lastIndexOf(".")).toLowerCase();
		for (var i = 0; i < extArray.length; i++) """),format.raw/*1523.45*/("""{"""),format.raw/*1523.46*/("""
			"""),format.raw/*1524.4*/("""if (extArray[i] == extencion) """),format.raw/*1524.34*/("""{"""),format.raw/*1524.35*/(""" """),format.raw/*1524.36*/("""allowSubmit = true; break; """),format.raw/*1524.63*/("""}"""),format.raw/*1524.64*/("""
		"""),format.raw/*1525.3*/("""}"""),format.raw/*1525.4*/("""
		"""),format.raw/*1526.3*/("""if (allowSubmit) """),format.raw/*1526.20*/("""{"""),format.raw/*1526.21*/("""
			"""),format.raw/*1527.4*/("""var file = $("#docAdjunto")[0].files[0];
			var fileSize = file.size; //tamaño del archivo
			if(fileSize>100000000)"""),format.raw/*1529.26*/("""{"""),format.raw/*1529.27*/("""
				"""),format.raw/*1530.5*/("""alert("Se permiten únicamente archivos que no superen los 100 MB,"
				+" el que se intenta subir pesa: "+Math.round(file.size/10)/100+" KB");
				form.docAdjunto.value="";
			"""),format.raw/*1533.4*/("""}"""),format.raw/*1533.5*/("""else"""),format.raw/*1533.9*/("""{"""),format.raw/*1533.10*/("""
				"""),format.raw/*1534.5*/("""alert("Documento subido con éxito");
				$("#txtBtn").text("Cambiar Documento");
			"""),format.raw/*1536.4*/("""}"""),format.raw/*1536.5*/("""
		"""),format.raw/*1537.3*/("""}"""),format.raw/*1537.4*/("""else"""),format.raw/*1537.8*/("""{"""),format.raw/*1537.9*/("""
			"""),format.raw/*1538.4*/("""alert("Se permiten únicamente archivos con la extención: " 
			+ (extArray.join("  ")) + "\nPor favor, seleccione otro archivo "
			+ "e intente de nuevo.");
			form.docAdjunto.value="";
		"""),format.raw/*1542.3*/("""}"""),format.raw/*1542.4*/("""
	"""),format.raw/*1543.2*/("""}"""),format.raw/*1543.3*/("""
	
	"""),format.raw/*1545.2*/("""let extArray2 = new Array(".xls", ".xlsx");
	let rsGlobal = "";
	function subirArchivo(form, file, nodo) """),format.raw/*1547.42*/("""{"""),format.raw/*1547.43*/("""
		"""),format.raw/*1548.3*/("""allowSubmit = false;
		if (!file) return;
		while (file.indexOf("\\") != -1)
			file = file.slice(file.indexOf("\\") + 1);
		ext = file.slice(file.lastIndexOf(".")).toLowerCase();
		for (var i = 0; i < extArray2.length; i++) """),format.raw/*1553.46*/("""{"""),format.raw/*1553.47*/("""
			"""),format.raw/*1554.4*/("""if (extArray2[i] == ext) """),format.raw/*1554.29*/("""{"""),format.raw/*1554.30*/(""" """),format.raw/*1554.31*/("""allowSubmit = true; break; """),format.raw/*1554.58*/("""}"""),format.raw/*1554.59*/("""
		"""),format.raw/*1555.3*/("""}"""),format.raw/*1555.4*/("""
		"""),format.raw/*1556.3*/("""if (allowSubmit) """),format.raw/*1556.20*/("""{"""),format.raw/*1556.21*/("""
			"""),format.raw/*1557.4*/("""var formData = new FormData();
			formData.append("file",nodo.files[0]);
	        $.ajax("""),format.raw/*1559.17*/("""{"""),format.raw/*1559.18*/("""
	            """),format.raw/*1560.14*/("""url: "/cotiValidarPlantilla/",
	            type: "POST",
	            method: "POST",
	            data: formData,
	            cache: false,
	            contentType: false,
		     	processData: false,
		     	success: function(rs)"""),format.raw/*1567.30*/("""{"""),format.raw/*1567.31*/("""
					"""),format.raw/*1568.6*/("""$('#modalCargaMasiva').modal('hide');
					if(rs[0] == 'true')"""),format.raw/*1569.25*/("""{"""),format.raw/*1569.26*/("""
						"""),format.raw/*1570.7*/("""$("#cotiCargaPlantilla").submit();
					"""),format.raw/*1571.6*/("""}"""),format.raw/*1571.7*/("""else if(rs[0].substring(0,7) == "EQUIPOS")"""),format.raw/*1571.49*/("""{"""),format.raw/*1571.50*/("""
						"""),format.raw/*1572.7*/("""rsGlobal = rs;
						$("#msgNuevosCodigos").modal('show');
					"""),format.raw/*1574.6*/("""}"""),format.raw/*1574.7*/("""else"""),format.raw/*1574.11*/("""{"""),format.raw/*1574.12*/("""
						"""),format.raw/*1575.7*/("""alertify.alert("SE PRESENTO UN PROBLEMA: "+rs, function () """),format.raw/*1575.66*/("""{"""),format.raw/*1575.67*/("""
							"""),format.raw/*1576.8*/("""location.reload();
						"""),format.raw/*1577.7*/("""}"""),format.raw/*1577.8*/(""");
					"""),format.raw/*1578.6*/("""}"""),format.raw/*1578.7*/("""
		     	"""),format.raw/*1579.9*/("""}"""),format.raw/*1579.10*/("""
	        """),format.raw/*1580.10*/("""}"""),format.raw/*1580.11*/(""");	
		"""),format.raw/*1581.3*/("""}"""),format.raw/*1581.4*/("""else"""),format.raw/*1581.8*/("""{"""),format.raw/*1581.9*/("""
			"""),format.raw/*1582.4*/("""alertify.alert("Se permiten &uacute;nicamente archivos con la extenci&oacute;n: " 
			+ (extArray2.join("  ")) + "\nPor favor, seleccione otro archivo "
			+ "e intente de nuevo.");
			form.archivoXLSX.value="";
		"""),format.raw/*1586.3*/("""}"""),format.raw/*1586.4*/("""
	"""),format.raw/*1587.2*/("""}"""),format.raw/*1587.3*/("""
	
	"""),format.raw/*1589.2*/("""const revisarEquiposNuevos = () =>"""),format.raw/*1589.36*/("""{"""),format.raw/*1589.37*/("""
		"""),format.raw/*1590.3*/("""$('#msgNuevosCodigos').modal('hide');
		let mensaje = "";
		for(var i in rsGlobal)"""),format.raw/*1592.25*/("""{"""),format.raw/*1592.26*/("""
			"""),format.raw/*1593.4*/("""mensaje += rsGlobal[i];
		"""),format.raw/*1594.3*/("""}"""),format.raw/*1594.4*/("""
		"""),format.raw/*1595.3*/("""alertify.alert(mensaje, function () """),format.raw/*1595.39*/("""{"""),format.raw/*1595.40*/(""" 
			"""),format.raw/*1596.4*/("""location.reload();
		"""),format.raw/*1597.3*/("""}"""),format.raw/*1597.4*/(""");
	"""),format.raw/*1598.2*/("""}"""),format.raw/*1598.3*/("""
	
	
	
	
	
	
	
	"""),format.raw/*1606.2*/("""function subirCoti8colum(form, file) """),format.raw/*1606.39*/("""{"""),format.raw/*1606.40*/("""
		"""),format.raw/*1607.3*/("""allowSubmit = false;
		if (!file) return;
		while (file.indexOf("\\") != -1)
			file = file.slice(file.indexOf("\\") + 1);
		ext = file.slice(file.lastIndexOf(".")).toLowerCase();
		for (var i = 0; i < extArray2.length; i++) """),format.raw/*1612.46*/("""{"""),format.raw/*1612.47*/("""
			"""),format.raw/*1613.4*/("""if (extArray2[i] == ext) """),format.raw/*1613.29*/("""{"""),format.raw/*1613.30*/(""" """),format.raw/*1613.31*/("""allowSubmit = true; break; """),format.raw/*1613.58*/("""}"""),format.raw/*1613.59*/("""
		"""),format.raw/*1614.3*/("""}"""),format.raw/*1614.4*/("""
		"""),format.raw/*1615.3*/("""if (allowSubmit) """),format.raw/*1615.20*/("""{"""),format.raw/*1615.21*/("""
			"""),format.raw/*1616.4*/("""$("#coti8ValidarPlantilla").submit();
		"""),format.raw/*1617.3*/("""}"""),format.raw/*1617.4*/("""else"""),format.raw/*1617.8*/("""{"""),format.raw/*1617.9*/("""
			"""),format.raw/*1618.4*/("""alertify.alert("Se permiten &uacute;nicamente archivos con la extenci&oacute;n: " 
			+ (extArray2.join("  ")) + "\nPor favor, seleccione otro archivo "
			+ "e intente de nuevo.");
			form.archivoXLSX.value="";
		"""),format.raw/*1622.3*/("""}"""),format.raw/*1622.4*/("""
	"""),format.raw/*1623.2*/("""}"""),format.raw/*1623.3*/("""

	"""),format.raw/*1625.2*/("""const aplicarTasaGlobal = () => """),format.raw/*1625.34*/("""{"""),format.raw/*1625.35*/("""
		"""),format.raw/*1626.3*/("""$("#tablaPrincipal").dataTable().fnDestroy();
		$(".tasaGlobal").each(function() """),format.raw/*1627.36*/("""{"""),format.raw/*1627.37*/("""
			 """),format.raw/*1628.5*/("""$(this).val($("#tasaGlobal").val());
		"""),format.raw/*1629.3*/("""}"""),format.raw/*1629.4*/(""");
		let tasa = $("#tasaGlobal").val();
		tasa = tasa.replace(/,/g,'').replace(/%/g,'').replace(/ /g,'');
		let tabla = document.getElementById("tablaPrincipal");
		for(i=2; i<tabla.rows.length-5; i++)"""),format.raw/*1633.39*/("""{"""),format.raw/*1633.40*/("""
			"""),format.raw/*1634.4*/("""let id_equipo = tabla.rows[i].cells[20].innerHTML;
			let nroDecimal = tabla.rows[i].cells[21].innerHTML;
			let prepos = $("#puVentaRepos_"+id_equipo).val().replace(/,/g,'');
			$("#puArriendo_"+id_equipo).val(formatStandar(parseFloat(prepos*tasa/100),nroDecimal));
			calculaLinea(id_equipo,nroDecimal);
		"""),format.raw/*1639.3*/("""}"""),format.raw/*1639.4*/("""
		"""),format.raw/*1640.3*/("""sumaTotales();
		document.getElementById('selSucursal').disabled = true;
		document.getElementById('selComercial').disabled = true;
		$('#tablaPrincipal').DataTable("""),format.raw/*1643.34*/("""{"""),format.raw/*1643.35*/("""
	    	"""),format.raw/*1644.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[15, 50, 100, 200, -1], [15, 50, 100, 200, "All"]],
	    	"order": [[ 0, "asc" ],[ 2, "asc" ]],
	    	"language": """),format.raw/*1647.19*/("""{"""),format.raw/*1647.20*/("""
	        	"""),format.raw/*1648.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*1649.10*/("""}"""),format.raw/*1649.11*/("""
		"""),format.raw/*1650.3*/("""}"""),format.raw/*1650.4*/(""");
	"""),format.raw/*1651.2*/("""}"""),format.raw/*1651.3*/("""
	
	"""),format.raw/*1653.2*/("""const aplicarFactorGlobal = (valor) => """),format.raw/*1653.41*/("""{"""),format.raw/*1653.42*/("""
		"""),format.raw/*1654.3*/("""$("#tablaPrincipal").dataTable().fnDestroy();
		let factorGlobal = valor/factorArr;
		factorArr = valor;
		let tabla = document.getElementById("tablaPrincipal");
		for(i=2; i<tabla.rows.length-5; i++)"""),format.raw/*1658.39*/("""{"""),format.raw/*1658.40*/("""
			"""),format.raw/*1659.4*/("""let id_equipo = tabla.rows[i].cells[20].innerHTML;
			let nroDecimal = tabla.rows[i].cells[21].innerHTML;
			let pArr = $("#puArriendo_"+id_equipo).val().replace(/,/g,'');
			let prepos = $("#puVentaRepos_"+id_equipo).val().replace(/,/g,'');
			$("#puArriendo_"+id_equipo).val(formatStandar(parseFloat(pArr*factorGlobal),nroDecimal));
			if(parseFloat(prepos)>0)"""),format.raw/*1664.28*/("""{"""),format.raw/*1664.29*/("""
				"""),format.raw/*1665.5*/("""$("#tasaArr_"+id_equipo).val(formatStandar(parseFloat(pArr*factorGlobal/prepos*100),2) + "%");
			"""),format.raw/*1666.4*/("""}"""),format.raw/*1666.5*/("""
			"""),format.raw/*1667.4*/("""calculaLinea(id_equipo,nroDecimal);
		"""),format.raw/*1668.3*/("""}"""),format.raw/*1668.4*/("""
		"""),format.raw/*1669.3*/("""sumaTotales();
		document.getElementById('selSucursal').disabled = true;
		document.getElementById('selComercial').disabled = true;
		$('#tablaPrincipal').DataTable("""),format.raw/*1672.34*/("""{"""),format.raw/*1672.35*/("""
	    	"""),format.raw/*1673.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[15, 50, 100, 200, -1], [15, 50, 100, 200, "All"]],
	    	"order": [[ 0, "asc" ],[ 2, "asc" ]],
	    	"language": """),format.raw/*1676.19*/("""{"""),format.raw/*1676.20*/("""
	        	"""),format.raw/*1677.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*1678.10*/("""}"""),format.raw/*1678.11*/("""
		"""),format.raw/*1679.3*/("""}"""),format.raw/*1679.4*/(""");
	"""),format.raw/*1680.2*/("""}"""),format.raw/*1680.3*/("""
	
	"""),format.raw/*1682.2*/("""const aplicarFactorGlobalRepos = (valor) => """),format.raw/*1682.46*/("""{"""),format.raw/*1682.47*/("""
		"""),format.raw/*1683.3*/("""$("#tablaPrincipal").dataTable().fnDestroy();
		let factorGlobalRepos = valor/factorVta;
		factorVta = valor;
		let tabla = document.getElementById("tablaPrincipal");
		for(i=2; i<tabla.rows.length-5; i++)"""),format.raw/*1687.39*/("""{"""),format.raw/*1687.40*/("""
			"""),format.raw/*1688.4*/("""let id_equipo = tabla.rows[i].cells[20].innerHTML;
			let nroDecimal = tabla.rows[i].cells[21].innerHTML;
			let pArr = $("#puArriendo_"+id_equipo).val().replace(/,/g,'');
			let prepos = $("#puVentaRepos_"+id_equipo).val().replace(/,/g,'');
			prepos = prepos * factorGlobalRepos;
			$("#puVentaRepos_"+id_equipo).val(formatStandar(parseFloat(prepos),nroDecimal));
			if(parseFloat(prepos*factorGlobalRepos)>0)"""),format.raw/*1694.46*/("""{"""),format.raw/*1694.47*/("""
				"""),format.raw/*1695.5*/("""$("#tasaArr_"+id_equipo).val(formatStandar(parseFloat(pArr/prepos*100),2) + "%");
			"""),format.raw/*1696.4*/("""}"""),format.raw/*1696.5*/("""
			"""),format.raw/*1697.4*/("""calculaLinea(id_equipo,nroDecimal);
		"""),format.raw/*1698.3*/("""}"""),format.raw/*1698.4*/("""
		"""),format.raw/*1699.3*/("""sumaTotales();
		document.getElementById('selSucursal').disabled = true;
		document.getElementById('selComercial').disabled = true;
		$('#tablaPrincipal').DataTable("""),format.raw/*1702.34*/("""{"""),format.raw/*1702.35*/("""
	    	"""),format.raw/*1703.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[15, 50, 100, 200, -1], [15, 50, 100, 200, "All"]],
	    	"order": [[ 0, "asc" ],[ 2, "asc" ]],
	    	"language": """),format.raw/*1706.19*/("""{"""),format.raw/*1706.20*/("""
	        	"""),format.raw/*1707.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*1708.10*/("""}"""),format.raw/*1708.11*/("""
		"""),format.raw/*1709.3*/("""}"""),format.raw/*1709.4*/(""");
	"""),format.raw/*1710.2*/("""}"""),format.raw/*1710.3*/("""
	
	"""),format.raw/*1712.2*/("""const aplicarPermGlobal = () => """),format.raw/*1712.34*/("""{"""),format.raw/*1712.35*/("""
		"""),format.raw/*1713.3*/("""$("#tablaPrincipal").dataTable().fnDestroy();
		let permGlobal = $("#permGlobal").val();
		permGlobal = permGlobal.replace(/,/g,'');
		let tabla = document.getElementById("tablaPrincipal");
		for(i=2; i<tabla.rows.length-5; i++)"""),format.raw/*1717.39*/("""{"""),format.raw/*1717.40*/("""
			"""),format.raw/*1718.4*/("""let id_equipo = tabla.rows[i].cells[20].innerHTML;
			let nroDecimal = tabla.rows[i].cells[21].innerHTML;
			let pArr = $("#puArriendo_"+id_equipo).val().replace(/,/g,'');
			let prepos = $("#puVentaRepos_"+id_equipo).val().replace(/,/g,'');
			$("#permanencia_"+id_equipo).val(formatStandar(parseFloat(permGlobal),2));
			calculaLinea(id_equipo,nroDecimal);
		"""),format.raw/*1724.3*/("""}"""),format.raw/*1724.4*/("""
		"""),format.raw/*1725.3*/("""sumaTotales();
		document.getElementById('selSucursal').disabled = true;
		document.getElementById('selComercial').disabled = true;
		$('#tablaPrincipal').DataTable("""),format.raw/*1728.34*/("""{"""),format.raw/*1728.35*/("""
	    	"""),format.raw/*1729.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[15, 50, 100, 200, -1], [15, 50, 100, 200, "All"]],
	    	"order": [[ 0, "asc" ],[ 2, "asc" ]],
	    	"language": """),format.raw/*1732.19*/("""{"""),format.raw/*1732.20*/("""
	        	"""),format.raw/*1733.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*1734.10*/("""}"""),format.raw/*1734.11*/("""
		"""),format.raw/*1735.3*/("""}"""),format.raw/*1735.4*/(""");
	"""),format.raw/*1736.2*/("""}"""),format.raw/*1736.3*/("""
	
	"""),format.raw/*1738.2*/("""const actualizaComerciales = (id_sucursal) =>"""),format.raw/*1738.47*/("""{"""),format.raw/*1738.48*/("""
		"""),format.raw/*1739.3*/("""var formData = new FormData();
		formData.append('id_sucursal',id_sucursal);
        $.ajax("""),format.raw/*1741.16*/("""{"""),format.raw/*1741.17*/("""
            """),format.raw/*1742.13*/("""url: "/actualizaComercialesAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*1749.36*/("""{"""),format.raw/*1749.37*/("""
				"""),format.raw/*1750.5*/("""document.getElementById('actualComerciales').innerHTML = respuesta;
				$("#formSucursal").val(id_sucursal);
	     	"""),format.raw/*1752.8*/("""}"""),format.raw/*1752.9*/("""
        """),format.raw/*1753.9*/("""}"""),format.raw/*1753.10*/(""");	
	"""),format.raw/*1754.2*/("""}"""),format.raw/*1754.3*/("""
	
	"""),format.raw/*1756.2*/("""const actualizaPrecios = (id_sucursal) =>"""),format.raw/*1756.43*/("""{"""),format.raw/*1756.44*/("""

		"""),format.raw/*1758.3*/("""if(noCambiarPorSucursal != "1")"""),format.raw/*1758.34*/("""{"""),format.raw/*1758.35*/("""
			"""),format.raw/*1759.4*/("""document.getElementById('bloquear2').style.display="block";
			var formData = new FormData();
			formData.append('id_sucursal',id_sucursal);
	        $.ajax("""),format.raw/*1762.17*/("""{"""),format.raw/*1762.18*/("""
	            """),format.raw/*1763.14*/("""url: "/actualizaPreciosAjax/",
	            type: "POST",
	            method: "POST",
	            data: formData,
	            cache: false,
	            contentType: false,
		     	processData: false,
		     	success: function(rs)"""),format.raw/*1770.30*/("""{"""),format.raw/*1770.31*/("""
			
					"""),format.raw/*1772.6*/("""$("#tablaPrincipal").dataTable().fnDestroy();
					
					let tabla = document.getElementById("tablaPrincipal");
					
					for(i=2; i<tabla.rows.length-5; i++)"""),format.raw/*1776.42*/("""{"""),format.raw/*1776.43*/("""
						"""),format.raw/*1777.7*/("""let id_equipo = tabla.rows[i].cells[20].innerHTML;
						let nroDecimal = tabla.rows[i].cells[21].innerHTML;
						
						for(let i=0; i<rs.length; i++)"""),format.raw/*1780.37*/("""{"""),format.raw/*1780.38*/("""
							"""),format.raw/*1781.8*/("""if(rs[i].id_equipo == id_equipo)"""),format.raw/*1781.40*/("""{"""),format.raw/*1781.41*/("""
								"""),format.raw/*1782.9*/("""let puVentaRepos = rs[i].precioVenta;
								let puArriendo = rs[i].precioArriendo;
								let tasaArriendo = 0
								if(parseFloat(puVentaRepos) > 0)"""),format.raw/*1785.41*/("""{"""),format.raw/*1785.42*/("""
									"""),format.raw/*1786.10*/("""tasaArriendo = parseFloat(puArriendo)/ parseFloat(puVentaRepos);
								"""),format.raw/*1787.9*/("""}"""),format.raw/*1787.10*/("""
								"""),format.raw/*1788.9*/("""$("#hiddenpuVentaRepos_"+id_equipo).val(puVentaRepos);
								$("#puVentaRepos_"+id_equipo).val(formatStandar(parseFloat(puVentaRepos),nroDecimal));
								$("#hiddenpuArriendo_"+id_equipo).val(puArriendo);
								$("#puArriendo_"+id_equipo).val(formatStandar(parseFloat(puArriendo),nroDecimal));
								$("#tasaArr_"+id_equipo).val(formatPorcentaje(parseFloat(tasaArriendo) * 100));
								calculaLinea(id_equipo,nroDecimal);
							"""),format.raw/*1794.8*/("""}"""),format.raw/*1794.9*/("""
						"""),format.raw/*1795.7*/("""}"""),format.raw/*1795.8*/("""
						
					"""),format.raw/*1797.6*/("""}"""),format.raw/*1797.7*/("""

					"""),format.raw/*1799.6*/("""sumaTotales();
					$('#tablaPrincipal').DataTable("""),format.raw/*1800.37*/("""{"""),format.raw/*1800.38*/("""
				    	"""),format.raw/*1801.10*/(""""fixedHeader": true,
				    	"lengthMenu": [[15, 50, 100, 200, -1], [15, 50, 100, 200, "All"]],
				    	"order": [[ 0, "asc" ],[ 2, "asc" ]],
				    	"language": """),format.raw/*1804.22*/("""{"""),format.raw/*1804.23*/("""
				        	"""),format.raw/*1805.14*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
				        """),format.raw/*1806.13*/("""}"""),format.raw/*1806.14*/("""
					"""),format.raw/*1807.6*/("""}"""),format.raw/*1807.7*/(""");
					
					document.getElementById('bloquear2').style.display="none"; 
				"""),format.raw/*1810.5*/("""}"""),format.raw/*1810.6*/("""
	        """),format.raw/*1811.10*/("""}"""),format.raw/*1811.11*/(""");	
		"""),format.raw/*1812.3*/("""}"""),format.raw/*1812.4*/("""
	"""),format.raw/*1813.2*/("""}"""),format.raw/*1813.3*/("""
	
	"""),format.raw/*1815.2*/("""const listadoCotizaciones = () =>"""),format.raw/*1815.35*/("""{"""),format.raw/*1815.36*/("""
		"""),format.raw/*1816.3*/("""var formData = new FormData();
		formData.append('id_sucursal',id_sucursal);
        $.ajax("""),format.raw/*1818.16*/("""{"""),format.raw/*1818.17*/("""
            """),format.raw/*1819.13*/("""url: "/actualizaListaCotiAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*1826.36*/("""{"""),format.raw/*1826.37*/("""
				"""),format.raw/*1827.5*/("""document.getElementById('vistaModListaCotizacion').innerHTML = respuesta;
				$('#tablaListaCotizacion').DataTable("""),format.raw/*1828.42*/("""{"""),format.raw/*1828.43*/("""
				    	"""),format.raw/*1829.10*/(""""fixedHeader": true,
				    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
				    	"order": [[ 2, "desc" ],[ 1, "desc" ],[ 0, "asc" ]],
				    	"language": """),format.raw/*1832.22*/("""{"""),format.raw/*1832.23*/("""
				        	"""),format.raw/*1833.14*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
				        """),format.raw/*1834.13*/("""}"""),format.raw/*1834.14*/("""
				"""),format.raw/*1835.5*/("""}"""),format.raw/*1835.6*/(""" """),format.raw/*1835.7*/(""");
				$('#modalListaCotizacion').modal('show');
	     	"""),format.raw/*1837.8*/("""}"""),format.raw/*1837.9*/("""
        """),format.raw/*1838.9*/("""}"""),format.raw/*1838.10*/(""");
	"""),format.raw/*1839.2*/("""}"""),format.raw/*1839.3*/("""
	
	"""),format.raw/*1841.2*/("""const validarForm2 = () =>"""),format.raw/*1841.28*/("""{"""),format.raw/*1841.29*/("""
		"""),format.raw/*1842.3*/("""$("#btnSubmit2").attr('disabled',true);
		return(true);
	"""),format.raw/*1844.2*/("""}"""),format.raw/*1844.3*/("""

	"""),format.raw/*1846.2*/("""const calcFechaProbable = () =>"""),format.raw/*1846.33*/("""{"""),format.raw/*1846.34*/("""
		"""),format.raw/*1847.3*/("""let fecha = $("#fechaCoti").val();
		let dias = $("#diasProbable").val();
		let fechaObj = new Date(fecha);
		fechaObj.setDate(fechaObj.getDate() + parseInt(dias));
		const fechaFormateada = fechaObj.toISOString().split('T')[0];
		$("#fechaProbable").val(fechaFormateada);
	"""),format.raw/*1853.2*/("""}"""),format.raw/*1853.3*/("""

	
"""),format.raw/*1856.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,formCotiza:forms.FormCotiza,listClientes:List[tables.Cliente],listProyectos:List[tables.Proyecto],numDecParaTot:String,listRegiones:List[tables.Regiones],jsonListUnTiempo:String,sucursal:tables.Sucursal,comercial:tables.Comercial,listSucursal:List[tables.Sucursal],listComercial:List[tables.Comercial],importDesdeExcel:String,jsonDetalle:String,listSoluciones:List[tables.CotizaSolucion],iva:Double,jsonListMoneda:String,listDibujantes:List[tables.Dibujante],listPlazoProbable:List[tables.PlazoProbable]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,formCotiza,listClientes,listProyectos,numDecParaTot,listRegiones,jsonListUnTiempo,sucursal,comercial,listSucursal,listComercial,importDesdeExcel,jsonDetalle,listSoluciones,iva,jsonListMoneda,listDibujantes,listPlazoProbable)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,forms.FormCotiza,List[tables.Cliente],List[tables.Proyecto],String,List[tables.Regiones],String,tables.Sucursal,tables.Comercial,List[tables.Sucursal],List[tables.Comercial],String,String,List[tables.CotizaSolucion],Double,String,List[tables.Dibujante],List[tables.PlazoProbable]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,formCotiza,listClientes,listProyectos,numDecParaTot,listRegiones,jsonListUnTiempo,sucursal,comercial,listSucursal,listComercial,importDesdeExcel,jsonDetalle,listSoluciones,iva,jsonListMoneda,listDibujantes,listPlazoProbable) => apply(mapDiccionario,mapPermiso,userMnu,formCotiza,listClientes,listProyectos,numDecParaTot,listRegiones,jsonListUnTiempo,sucursal,comercial,listSucursal,listComercial,importDesdeExcel,jsonDetalle,listSoluciones,iva,jsonListMoneda,listDibujantes,listPlazoProbable)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizar/cotizaIngreso2.scala.html
                  HASH: f0ab41c1ac9d03b43872d3c7c523294ad2f43454
                  MATRIX: 1294->1|2025->639|2052->641|2068->649|2107->651|2136->654|2205->702|2233->703|2383->827|2428->851|2458->854|2516->886|2584->933|2614->936|2694->988|2723->989|2755->994|3628->1840|3656->1841|3686->1844|3792->1924|3861->1972|3891->1975|3973->2029|4002->2030|4034->2035|4793->2767|4821->2768|4851->2771|5089->2983|5172->3045|5203->3049|5589->3408|5608->3418|5644->3432|5998->3759|6017->3769|6049->3780|6177->3880|6206->3881|6241->3889|6260->3899|6292->3910|6322->3912|6351->3913|6383->3917|6412->3918|6462->3939|6492->3940|6933->4354|6952->4364|6983->4374|7056->4420|7075->4430|7106->4440|7365->4671|7389->4685|7422->4696|7452->4697|7589->4806|7609->4816|7642->4827|7741->4898|7782->4900|7850->4940|7890->4941|7932->4954|8054->5048|8074->5058|8107->5069|8170->5112|8210->5113|8252->5126|8374->5220|8394->5230|8427->5241|8598->5380|8634->5396|8674->5397|8715->5409|8835->5501|8855->5511|8888->5522|9055->5657|9094->5667|9403->5948|9444->5950|9513->5991|9553->5992|9596->6006|9722->6104|9742->6114|9776->6126|9860->6190|9900->6191|9943->6205|10069->6303|10089->6313|10123->6325|10309->6479|10346->6496|10386->6497|10428->6510|10554->6608|10574->6618|10608->6630|10793->6783|10832->6793|11148->7081|11168->7091|11202->7103|11301->7174|11342->7176|11383->7188|11508->7285|11528->7295|11563->7308|11622->7347|11662->7348|11703->7360|11828->7457|11848->7467|11883->7480|12043->7608|12082->7618|12589->8097|12607->8105|12637->8113|12901->8349|12941->8350|13026->8407|13066->8408|13108->8421|13332->8617|13350->8625|13380->8633|13411->8636|13429->8644|13463->8656|13515->8680|13558->8706|13598->8707|13642->8722|13686->8738|13701->8743|13731->8751|13762->8754|13777->8759|13811->8771|13867->8795|13909->8808|13955->8834|13995->8835|14037->8848|14232->9015|14250->9023|14280->9031|14311->9034|14329->9042|14363->9054|14415->9078|14458->9104|14498->9105|14542->9120|14586->9136|14601->9141|14631->9149|14662->9152|14677->9157|14711->9169|14767->9193|14809->9206|14863->9228|14899->9244|14939->9245|14980->9257|15092->9341|15110->9349|15144->9361|15211->9396|15250->9406|15690->9818|15710->9828|15746->9842|16013->10081|16032->10090|16062->10098|16333->10341|16373->10342|16415->10355|16585->10497|16604->10506|16634->10514|16665->10517|16684->10526|16723->10543|16775->10567|16819->10594|16859->10595|16903->10610|16947->10626|16962->10631|16992->10639|17023->10642|17038->10647|17077->10664|17133->10688|17175->10701|17221->10727|17261->10728|17303->10741|17418->10828|17437->10837|17476->10854|17545->10891|17585->10902|18131->11420|18176->11448|18216->11449|18258->11462|18302->11478|18317->11483|18347->11491|18378->11494|18393->11499|18429->11513|18483->11535|18523->11546|18991->11986|19039->12017|19079->12018|19120->12030|19164->12046|19179->12051|19209->12059|19240->12062|19255->12067|19288->12078|19342->12100|19382->12111|19843->12544|19863->12554|19895->12564|19949->12590|19969->12600|20001->12610|20588->13169|20633->13197|20673->13198|20714->13210|20758->13226|20773->13231|20803->13239|20834->13242|20849->13247|20883->13259|20937->13281|20977->13292|21153->13440|21173->13450|21213->13467|21310->13536|21351->13538|21390->13548|21599->13729|21623->13743|21659->13757|21805->13875|21825->13885|21860->13898|21971->13964|22007->13972|22861->14797|22903->14799|22939->14807|23271->15095|23318->15114|24416->16183|24446->16184|24490->16199|24520->16200|26019->17670|26049->17671|26091->17684|26121->17685|28591->20127|28615->20141|28648->20152|28777->20254|28821->20281|28861->20282|28898->20291|28961->20326|28977->20332|29007->20340|29061->20366|29077->20372|29108->20381|29166->20410|29183->20416|29220->20430|29315->20497|29331->20503|29362->20512|29436->20558|29452->20564|29488->20578|29562->20624|29578->20630|29612->20642|29673->20671|29708->20678|31000->21942|31024->21956|31060->21970|31143->22026|31188->22054|31228->22055|31265->22064|31329->22100|31345->22106|31375->22114|31433->22144|31449->22150|31486->22164|31581->22231|31597->22237|31633->22251|31707->22297|31723->22303|31757->22315|31831->22361|31847->22367|31881->22379|31942->22408|31977->22415|34994->25404|35012->25412|35042->25420|35261->25611|35281->25621|35321->25638|36852->27141|36870->27149|36900->27157|37045->27274|37065->27284|37105->27301|38704->28869|38735->28872|38932->29040|38962->29041|38996->29047|39049->29072|39087->29088|39147->29120|39191->29142|39275->29197|39305->29198|39337->29202|39473->29310|39502->29311|39536->29317|39586->29339|39628->29359|39708->29410|39738->29411|39770->29415|39983->29600|40012->29601|40049->29610|40096->29629|40135->29646|40300->29782|40342->29784|40374->29788|40439->29809|40479->29821|40816->30130|40836->30140|40900->30182|40941->30195|40961->30205|41026->30247|41068->30259|41099->30260|41133->30266|41610->30714|41640->30715|41729->30775|41759->30776|41792->30780|41822->30781|41954->30884|41984->30885|42021->30894|42050->30895|42083->30900|42901->31689|42931->31690|43020->31750|43050->31751|43083->31755|43113->31756|43240->31854|43270->31855|43907->32464|43931->32478|43964->32489|44018->32515|44042->32529|44075->32540|44139->32576|44163->32590|44196->32601|44314->32691|44338->32705|44376->32721|44496->32812|44526->32813|44559->32818|44643->32874|44672->32875|44704->32879|44734->32880|44767->32885|44826->32916|44855->32917|44888->32922|45192->33197|45222->33198|45255->33203|46995->34914|47025->34915|47060->34922|47295->35129|47324->35130|47357->35134|47387->35135|47422->35142|47665->35357|47694->35358|47729->35365|48471->36079|48491->36089|48555->36131|48596->36144|48616->36154|48681->36196|48721->36206|48752->36207|48788->36215|49059->36458|49088->36459|49121->36463|49151->36464|49187->36472|49701->36957|49731->36958|49768->36966|49798->36967|49828->36968|49862->36973|49892->36974|50052->37104|50083->37105|50123->37117|50152->37118|50187->37125|51239->38148|51269->38149|51313->38164|51343->38165|51373->38166|51407->38171|51437->38172|51597->38302|51628->38303|52318->38964|52348->38965|52385->38973|52415->38974|52445->38975|52479->38980|52509->38981|52669->39111|52700->39112|54041->40424|54071->40425|54106->40432|54275->40573|54304->40574|54337->40578|54367->40579|54402->40586|54559->40715|54588->40716|54629->40729|55105->41177|55134->41178|55167->41183|55678->41665|55708->41666|55741->41671|55818->41720|55847->41721|55879->41725|55909->41726|55942->41731|55994->41755|56023->41756|56059->41764|56548->42225|56568->42235|56603->42248|56633->42249|57121->42709|57141->42719|57173->42729|57203->42730|57562->43060|57592->43061|57625->43066|57702->43115|57731->43116|57763->43120|57793->43121|57826->43126|57878->43150|57907->43151|57943->43159|58726->43913|58756->43914|58789->43919|58915->44017|58944->44018|58976->44022|59006->44023|59039->44028|59153->44114|59182->44115|59223->44127|59789->44663|59820->44664|59854->44669|59932->44718|59962->44719|59995->44723|60026->44724|60060->44729|60113->44753|60143->44754|60184->44766|60786->45338|60817->45339|60851->45344|60929->45393|60959->45394|60992->45398|61023->45399|61057->45404|61110->45428|61140->45429|61185->45445|61497->45727|61528->45728|61564->45735|61737->45878|61768->45879|61809->45890|61916->45967|61947->45968|61981->45973|62011->45974|62041->45975|62113->46017|62144->46018|62180->46025|62353->46168|62384->46169|62425->46180|62532->46257|62563->46258|62597->46263|62627->46264|62657->46265|62744->46322|62775->46323|62812->46331|63001->46490|63032->46491|63074->46503|63182->46581|63213->46582|63245->46585|63275->46586|63305->46587|63408->46661|63438->46662|63507->46701|63538->46702|63570->46705|63686->46791|63717->46792|63760->46805|64029->47044|64060->47045|64094->47050|64143->47069|64174->47070|64209->47076|64261->47099|64282->47109|64316->47120|64393->47167|64424->47168|64455->47169|64486->47170|64522->47177|64552->47178|64589->47186|64619->47187|64657->47196|64688->47197|64721->47201|64751->47202|64784->47206|64856->47248|64887->47249|64919->47252|65037->47340|65068->47341|65111->47354|65375->47588|65406->47589|65440->47594|65586->47711|65616->47712|65654->47721|65685->47722|65718->47726|65748->47727|65783->47733|65838->47758|65869->47759|65901->47762|66105->47936|66136->47937|66169->47941|66270->48013|66300->48014|66386->48070|66417->48071|66450->48075|66553->48149|66583->48150|66662->48200|66698->48213|66784->48269|66815->48270|66848->48274|66949->48346|66979->48347|67056->48395|67092->48408|67178->48464|67209->48465|67242->48469|67343->48541|67373->48542|67450->48590|67486->48603|67568->48655|67599->48656|67632->48660|67732->48731|67762->48732|67842->48782|67873->48783|67906->48787|68006->48858|68036->48859|68290->49084|68326->49097|68429->49171|68465->49184|68565->49255|68601->49268|68778->49416|68808->49417|68841->49421|68904->49454|68935->49455|68967->49458|69059->49520|69090->49521|69123->49525|69189->49562|69219->49563|69252->49567|69282->49568|69315->49572|69381->49609|69411->49610|69442->49612|69472->49613|69505->49617|69582->49664|69613->49665|69645->49668|69686->49679|69717->49680|69750->49684|70091->49996|70121->49997|70167->50013|70198->50014|70231->50018|70707->50465|70737->50466|70770->50470|70800->50471|70833->50475|71174->50787|71204->50788|71238->50793|71268->50794|71301->50798|71374->50841|71405->50842|71437->50845|72098->51476|72129->51477|72163->51482|72431->51721|72461->51722|72494->51726|72524->51727|72558->51732|72771->51916|72801->51917|72836->51923|73691->52748|73722->52749|73755->52753|73801->52770|73831->52771|73863->52774|73896->52778|73926->52779|73959->52783|74043->52837|74074->52838|74106->52841|74272->52979|74293->52989|74347->53020|74389->53033|74410->53043|74465->53074|74506->53084|74538->53085|74572->53090|74671->53160|74701->53161|74736->53167|75089->53490|75120->53491|75153->53495|75277->53589|75308->53590|75342->53595|75638->53861|75669->53862|75704->53868|75775->53910|75805->53911|75838->53915|75868->53916|75905->53924|75957->53947|75983->53950|76077->54015|76113->54028|76203->54089|76239->54102|76329->54163|76365->54176|76485->54267|76521->54280|76638->54368|76674->54381|76791->54469|76827->54482|76992->54617|77034->54618|77068->54623|77227->54737|77264->54745|77913->55365|77943->55366|77976->55370|78006->55371|78039->55375|78100->55406|78131->55407|78168->55415|78357->55574|78388->55575|78430->55587|78538->55665|78569->55666|78602->55670|78632->55671|78662->55672|78873->55852|78905->55853|78937->55854|78969->55855|79003->55860|79033->55861|79064->55863|79094->55864|79127->55868|79189->55900|79220->55901|79252->55904|79375->55997|79406->55998|79439->56002|79504->56038|79534->56039|79566->56042|79627->56073|79658->56074|79695->56082|79884->56241|79915->56242|79957->56254|80065->56332|80096->56333|80128->56336|80158->56337|80188->56338|80832->56953|80862->56954|80895->56958|81004->57037|81035->57038|81067->57041|81233->57179|81254->57189|81308->57220|81350->57233|81371->57243|81426->57274|81467->57284|81499->57285|81533->57290|81632->57360|81662->57361|81697->57367|81820->57460|81851->57461|81894->57474|82148->57698|82179->57699|82218->57709|82583->58044|82614->58045|82654->58055|83062->58434|83093->58435|83136->58449|83331->58614|83362->58615|83397->58621|83621->58815|83652->58816|83688->58823|84376->59481|84407->59482|84444->59490|84542->59559|84572->59560|84606->59564|84637->59565|84674->59573|84773->59643|84803->59644|84839->59651|85071->59854|85101->59855|85141->59866|85171->59867|85205->59872|85475->60112|85506->60113|85544->60122|85736->60284|85767->60285|85810->60298|85919->60377|85950->60378|85984->60383|86014->60384|86053->60394|86083->60395|86123->60406|86154->60407|86187->60411|86217->60412|86250->60416|86283->60421|86322->60437|86361->60446|86392->60447|86424->60450|86590->60588|86611->60598|86665->60629|86707->60642|86728->60652|86783->60683|86824->60693|86856->60694|86890->60699|86989->60769|87019->60770|87053->60775|87083->60776|87120->60784|87198->60832|87229->60833|87261->60836|87679->61224|87710->61225|87743->61229|87850->61306|87881->61307|87915->61312|88006->61374|88036->61375|88070->61380|88100->61381|88166->61417|88197->61418|88230->61422|88345->61507|88376->61508|88410->61513|88501->61575|88531->61576|88565->61581|88595->61582|88653->61611|88678->61625|88717->61641|88761->61655|88792->61656|88825->61660|88965->61769|88997->61770|89031->61775|89122->61837|89152->61838|89186->61843|89216->61844|89293->61891|89324->61892|89357->61896|89485->61993|89517->61994|89551->61999|89642->62061|89672->62062|89706->62067|89736->62068|89769->62072|89799->62073|89832->62077|89956->62171|89987->62172|90021->62177|90317->62443|90348->62444|90383->62450|90446->62484|90476->62485|90509->62489|90539->62490|90572->62494|90617->62510|90647->62511|90679->62514|90723->62529|90753->62530|90785->62533|90977->62695|91008->62696|91040->62699|91150->62779|91181->62780|91214->62784|91288->62829|91318->62830|91350->62833|91489->62942|91520->62943|91553->62947|91613->62977|91644->62978|91675->62979|91732->63006|91763->63007|91795->63010|91825->63011|91857->63014|91904->63031|91935->63032|91968->63036|92114->63152|92145->63153|92179->63158|92384->63334|92414->63335|92447->63339|92478->63340|92512->63345|92625->63429|92655->63430|92687->63433|92717->63434|92750->63438|92780->63439|92813->63443|93031->63632|93061->63633|93092->63635|93122->63636|93155->63640|93290->63745|93321->63746|93353->63749|93608->63974|93639->63975|93672->63979|93727->64004|93758->64005|93789->64006|93846->64033|93877->64034|93909->64037|93939->64038|93971->64041|94018->64058|94049->64059|94082->64063|94201->64152|94232->64153|94276->64167|94539->64400|94570->64401|94605->64407|94697->64469|94728->64470|94764->64477|94833->64517|94863->64518|94935->64560|94966->64561|95002->64568|95095->64632|95125->64633|95159->64637|95190->64638|95226->64645|95315->64704|95346->64705|95383->64713|95437->64738|95467->64739|95504->64747|95534->64748|95572->64757|95603->64758|95643->64768|95674->64769|95709->64775|95739->64776|95772->64780|95802->64781|95835->64785|96078->64999|96108->65000|96139->65002|96169->65003|96202->65007|96266->65041|96297->65042|96329->65045|96441->65127|96472->65128|96505->65132|96560->65158|96590->65159|96622->65162|96688->65198|96719->65199|96753->65204|96803->65225|96833->65226|96866->65230|96896->65231|96941->65247|97008->65284|97039->65285|97071->65288|97326->65513|97357->65514|97390->65518|97445->65543|97476->65544|97507->65545|97564->65572|97595->65573|97627->65576|97657->65577|97689->65580|97736->65597|97767->65598|97800->65602|97869->65642|97899->65643|97932->65647|97962->65648|97995->65652|98238->65866|98268->65867|98299->65869|98329->65870|98361->65873|98423->65905|98454->65906|98486->65909|98597->65990|98628->65991|98662->65996|98730->66035|98760->66036|98991->66237|99022->66238|99055->66242|99392->66550|99422->66551|99454->66554|99649->66719|99680->66720|99716->66727|99902->66883|99933->66884|99974->66895|100081->66972|100112->66973|100144->66976|100174->66977|100207->66981|100237->66982|100270->66986|100339->67025|100370->67026|100402->67029|100632->67229|100663->67230|100696->67234|101088->67596|101119->67597|101153->67602|101280->67700|101310->67701|101343->67705|101410->67743|101440->67744|101472->67747|101667->67912|101698->67913|101734->67920|101920->68076|101951->68077|101992->68088|102099->68165|102130->68166|102162->68169|102192->68170|102225->68174|102255->68175|102288->68179|102362->68223|102393->68224|102425->68227|102660->68432|102691->68433|102724->68437|103165->68848|103196->68849|103230->68854|103344->68939|103374->68940|103407->68944|103474->68982|103504->68983|103536->68986|103731->69151|103762->69152|103798->69159|103984->69315|104015->69316|104056->69327|104163->69404|104194->69405|104226->69408|104256->69409|104289->69413|104319->69414|104352->69418|104414->69450|104445->69451|104477->69454|104735->69682|104766->69683|104799->69687|105189->70048|105219->70049|105251->70052|105446->70217|105477->70218|105513->70225|105699->70381|105730->70382|105771->70393|105878->70470|105909->70471|105941->70474|105971->70475|106004->70479|106034->70480|106067->70484|106142->70529|106173->70530|106205->70533|106327->70625|106358->70626|106401->70639|106668->70876|106699->70877|106733->70882|106878->70998|106908->70999|106946->71008|106977->71009|107011->71014|107041->71015|107074->71019|107145->71060|107176->71061|107209->71065|107270->71096|107301->71097|107334->71101|107521->71258|107552->71259|107596->71273|107859->71506|107890->71507|107929->71517|108118->71676|108149->71677|108185->71684|108367->71836|108398->71837|108435->71845|108497->71877|108528->71878|108566->71887|108750->72041|108781->72042|108821->72052|108923->72125|108954->72126|108992->72135|109460->72574|109490->72575|109526->72582|109556->72583|109598->72596|109628->72597|109664->72604|109745->72655|109776->72656|109816->72666|110011->72831|110042->72832|110086->72846|110196->72926|110227->72927|110262->72933|110292->72934|110399->73012|110429->73013|110469->73023|110500->73024|110535->73030|110565->73031|110596->73033|110626->73034|110659->73038|110722->73071|110753->73072|110785->73075|110907->73167|110938->73168|110981->73181|111246->73416|111277->73417|111311->73422|111456->73537|111487->73538|111527->73548|111737->73728|111768->73729|111812->73743|111922->73823|111953->73824|111987->73829|112017->73830|112047->73831|112132->73887|112162->73888|112200->73897|112231->73898|112264->73902|112294->73903|112327->73907|112383->73933|112414->73934|112446->73937|112532->73994|112562->73995|112594->73998|112655->74029|112686->74030|112718->74033|113021->74307|113051->74308|113084->74312
                  LINES: 28->1|38->7|39->8|39->8|39->8|41->10|41->10|42->11|43->12|43->12|45->14|46->15|46->15|47->16|48->17|48->17|49->18|65->34|65->34|66->35|70->39|70->39|71->40|72->41|72->41|73->42|88->57|88->57|89->58|95->64|95->64|96->65|102->71|102->71|102->71|110->79|110->79|110->79|112->81|112->81|112->81|112->81|112->81|112->81|112->81|112->81|112->81|112->81|112->81|123->92|123->92|123->92|124->93|124->93|124->93|131->100|131->100|131->100|131->100|133->102|133->102|133->102|134->103|134->103|135->104|135->104|136->105|138->107|138->107|138->107|140->109|140->109|141->110|143->112|143->112|143->112|147->116|148->117|148->117|149->118|151->120|151->120|151->120|155->124|156->125|163->132|163->132|164->133|164->133|165->134|167->136|167->136|167->136|170->139|170->139|171->140|173->142|173->142|173->142|178->147|179->148|179->148|180->149|182->151|182->151|182->151|187->156|188->157|195->164|195->164|195->164|196->165|196->165|197->166|199->168|199->168|199->168|201->170|201->170|202->171|204->173|204->173|204->173|208->177|209->178|223->192|223->192|223->192|228->197|228->197|229->198|229->198|230->199|231->200|231->200|231->200|231->200|231->200|231->200|232->201|232->201|232->201|233->202|233->202|233->202|233->202|233->202|233->202|233->202|234->203|235->204|236->205|236->205|237->206|238->207|238->207|238->207|238->207|238->207|238->207|239->208|239->208|239->208|240->209|240->209|240->209|240->209|240->209|240->209|240->209|241->210|242->211|243->212|244->213|244->213|245->214|246->215|246->215|246->215|248->217|249->218|262->231|262->231|262->231|271->240|271->240|271->240|276->245|276->245|277->246|278->247|278->247|278->247|278->247|278->247|278->247|279->248|279->248|279->248|280->249|280->249|280->249|280->249|280->249|280->249|280->249|281->250|282->251|283->252|283->252|284->253|285->254|285->254|285->254|287->256|288->257|300->269|300->269|300->269|301->270|301->270|301->270|301->270|301->270|301->270|301->270|302->271|303->272|315->284|315->284|315->284|316->285|316->285|316->285|316->285|316->285|316->285|316->285|317->286|318->287|329->298|329->298|329->298|330->299|330->299|330->299|344->313|344->313|344->313|345->314|345->314|345->314|345->314|345->314|345->314|345->314|346->315|347->316|352->321|352->321|352->321|353->322|353->322|354->323|357->326|357->326|357->326|360->329|360->329|360->329|364->333|365->334|383->352|383->352|384->353|388->357|392->361|416->385|416->385|416->385|416->385|449->418|449->418|449->418|449->418|506->475|506->475|506->475|512->481|512->481|512->481|513->482|513->482|513->482|513->482|513->482|513->482|513->482|513->482|513->482|513->482|514->483|514->483|514->483|515->484|515->484|515->484|516->485|516->485|516->485|518->487|519->488|551->520|551->520|551->520|555->524|555->524|555->524|556->525|556->525|556->525|556->525|556->525|556->525|556->525|557->526|557->526|557->526|558->527|558->527|558->527|559->528|559->528|559->528|561->530|562->531|635->604|635->604|635->604|638->607|638->607|638->607|671->640|671->640|671->640|673->642|673->642|673->642|713->682|716->685|724->693|724->693|726->695|726->695|726->695|728->697|728->697|730->699|730->699|731->700|732->701|732->701|734->703|734->703|734->703|736->705|736->705|737->706|739->708|739->708|742->711|742->711|742->711|745->714|745->714|746->715|747->716|751->720|756->725|756->725|756->725|756->725|756->725|756->725|756->725|756->725|757->726|764->733|764->733|766->735|766->735|766->735|766->735|769->738|769->738|770->739|770->739|771->740|783->752|783->752|785->754|785->754|785->754|785->754|788->757|788->757|805->774|805->774|805->774|806->775|806->775|806->775|807->776|807->776|807->776|810->779|810->779|810->779|815->784|815->784|816->785|817->786|817->786|817->786|817->786|818->787|819->788|819->788|821->790|829->798|829->798|830->799|861->830|861->830|862->831|864->833|864->833|864->833|864->833|865->834|867->836|867->836|869->838|882->851|882->851|882->851|882->851|882->851|882->851|882->851|882->851|883->852|888->857|888->857|888->857|888->857|889->858|897->866|897->866|897->866|897->866|897->866|897->866|897->866|897->866|897->866|898->867|898->867|899->868|915->884|915->884|915->884|915->884|915->884|915->884|915->884|915->884|915->884|927->896|927->896|927->896|927->896|927->896|927->896|927->896|927->896|927->896|946->915|946->915|947->916|948->917|948->917|948->917|948->917|949->918|950->919|950->919|952->921|958->927|958->927|960->929|969->938|969->938|970->939|971->940|971->940|971->940|971->940|972->941|973->942|973->942|975->944|986->955|986->955|986->955|986->955|997->966|997->966|997->966|997->966|1006->975|1006->975|1007->976|1008->977|1008->977|1008->977|1008->977|1009->978|1010->979|1010->979|1012->981|1025->994|1025->994|1026->995|1027->996|1027->996|1027->996|1027->996|1028->997|1029->998|1029->998|1032->1001|1043->1012|1043->1012|1044->1013|1045->1014|1045->1014|1045->1014|1045->1014|1046->1015|1047->1016|1047->1016|1050->1019|1061->1030|1061->1030|1062->1031|1063->1032|1063->1032|1063->1032|1063->1032|1064->1033|1065->1034|1065->1034|1069->1038|1078->1047|1078->1047|1079->1048|1082->1051|1082->1051|1083->1052|1084->1053|1084->1053|1085->1054|1085->1054|1085->1054|1087->1056|1087->1056|1088->1057|1091->1060|1091->1060|1092->1061|1093->1062|1093->1062|1094->1063|1094->1063|1094->1063|1098->1067|1098->1067|1099->1068|1102->1071|1102->1071|1103->1072|1104->1073|1104->1073|1105->1074|1105->1074|1105->1074|1108->1077|1108->1077|1110->1079|1110->1079|1111->1080|1113->1082|1113->1082|1114->1083|1121->1090|1121->1090|1122->1091|1122->1091|1122->1091|1123->1092|1123->1092|1123->1092|1123->1092|1124->1093|1124->1093|1124->1093|1124->1093|1125->1094|1125->1094|1126->1095|1126->1095|1127->1096|1127->1096|1128->1097|1128->1097|1130->1099|1130->1099|1130->1099|1131->1100|1133->1102|1133->1102|1134->1103|1141->1110|1141->1110|1142->1111|1144->1113|1144->1113|1145->1114|1145->1114|1146->1115|1146->1115|1149->1118|1149->1118|1149->1118|1150->1119|1155->1124|1155->1124|1156->1125|1158->1127|1158->1127|1161->1130|1161->1130|1162->1131|1164->1133|1164->1133|1164->1133|1164->1133|1167->1136|1167->1136|1168->1137|1170->1139|1170->1139|1170->1139|1170->1139|1173->1142|1173->1142|1174->1143|1176->1145|1176->1145|1176->1145|1176->1145|1179->1148|1179->1148|1180->1149|1182->1151|1182->1151|1185->1154|1185->1154|1186->1155|1188->1157|1188->1157|1193->1162|1193->1162|1194->1163|1194->1163|1195->1164|1195->1164|1200->1169|1200->1169|1202->1171|1202->1171|1202->1171|1203->1172|1204->1173|1204->1173|1205->1174|1206->1175|1206->1175|1206->1175|1206->1175|1207->1176|1208->1177|1208->1177|1209->1178|1209->1178|1211->1180|1211->1180|1211->1180|1212->1181|1212->1181|1212->1181|1213->1182|1218->1187|1218->1187|1218->1187|1218->1187|1219->1188|1228->1197|1228->1197|1228->1197|1228->1197|1229->1198|1234->1203|1234->1203|1236->1205|1236->1205|1238->1207|1238->1207|1238->1207|1239->1208|1252->1221|1252->1221|1253->1222|1258->1227|1258->1227|1258->1227|1258->1227|1259->1228|1264->1233|1264->1233|1266->1235|1288->1257|1288->1257|1289->1258|1290->1259|1290->1259|1291->1260|1292->1261|1292->1261|1294->1263|1295->1264|1295->1264|1296->1265|1298->1267|1298->1267|1298->1267|1298->1267|1298->1267|1298->1267|1298->1267|1298->1267|1299->1268|1300->1269|1300->1269|1302->1271|1307->1276|1307->1276|1308->1277|1309->1278|1309->1278|1310->1279|1313->1282|1313->1282|1314->1283|1315->1284|1315->1284|1316->1285|1316->1285|1318->1287|1318->1287|1318->1287|1320->1289|1320->1289|1321->1290|1321->1290|1322->1291|1322->1291|1324->1293|1324->1293|1325->1294|1325->1294|1326->1295|1326->1295|1328->1297|1328->1297|1329->1298|1331->1300|1333->1302|1348->1317|1348->1317|1348->1317|1348->1317|1349->1318|1349->1318|1349->1318|1350->1319|1353->1322|1353->1322|1354->1323|1355->1324|1355->1324|1356->1325|1356->1325|1356->1325|1357->1326|1357->1326|1357->1326|1357->1326|1358->1327|1358->1327|1359->1328|1359->1328|1361->1330|1361->1330|1361->1330|1362->1331|1363->1332|1363->1332|1364->1333|1365->1334|1365->1334|1366->1335|1366->1335|1366->1335|1367->1336|1370->1339|1370->1339|1371->1340|1372->1341|1372->1341|1373->1342|1373->1342|1373->1342|1388->1357|1388->1357|1390->1359|1391->1360|1391->1360|1392->1361|1394->1363|1394->1363|1394->1363|1394->1363|1394->1363|1394->1363|1394->1363|1394->1363|1395->1364|1396->1365|1396->1365|1398->1367|1400->1369|1400->1369|1401->1370|1408->1377|1408->1377|1410->1379|1420->1389|1420->1389|1421->1390|1427->1396|1427->1396|1429->1398|1432->1401|1432->1401|1433->1402|1438->1407|1438->1407|1439->1408|1454->1423|1454->1423|1455->1424|1456->1425|1456->1425|1456->1425|1456->1425|1457->1426|1458->1427|1458->1427|1459->1428|1463->1432|1463->1432|1465->1434|1465->1434|1466->1435|1474->1443|1474->1443|1475->1444|1478->1447|1478->1447|1479->1448|1480->1449|1480->1449|1481->1450|1481->1450|1482->1451|1482->1451|1485->1454|1485->1454|1486->1455|1486->1455|1488->1457|1488->1457|1488->1457|1488->1457|1488->1457|1489->1458|1491->1460|1491->1460|1491->1460|1491->1460|1491->1460|1491->1460|1491->1460|1491->1460|1492->1461|1493->1462|1493->1462|1495->1464|1495->1464|1499->1468|1500->1469|1500->1469|1501->1470|1510->1479|1510->1479|1511->1480|1511->1480|1511->1480|1512->1481|1514->1483|1514->1483|1515->1484|1515->1484|1515->1484|1515->1484|1516->1485|1516->1485|1516->1485|1517->1486|1519->1488|1519->1488|1520->1489|1520->1489|1520->1489|1520->1489|1520->1489|1520->1489|1520->1489|1521->1490|1521->1490|1521->1490|1522->1491|1524->1493|1524->1493|1525->1494|1525->1494|1525->1494|1525->1494|1526->1495|1526->1495|1526->1495|1527->1496|1529->1498|1529->1498|1530->1499|1530->1499|1530->1499|1530->1499|1531->1500|1532->1501|1532->1501|1533->1502|1536->1505|1536->1505|1537->1506|1539->1508|1539->1508|1540->1509|1540->1509|1541->1510|1542->1511|1542->1511|1543->1512|1544->1513|1544->1513|1546->1515|1547->1516|1547->1516|1548->1517|1550->1519|1550->1519|1551->1520|1552->1521|1552->1521|1553->1522|1554->1523|1554->1523|1555->1524|1555->1524|1555->1524|1555->1524|1555->1524|1555->1524|1556->1525|1556->1525|1557->1526|1557->1526|1557->1526|1558->1527|1560->1529|1560->1529|1561->1530|1564->1533|1564->1533|1564->1533|1564->1533|1565->1534|1567->1536|1567->1536|1568->1537|1568->1537|1568->1537|1568->1537|1569->1538|1573->1542|1573->1542|1574->1543|1574->1543|1576->1545|1578->1547|1578->1547|1579->1548|1584->1553|1584->1553|1585->1554|1585->1554|1585->1554|1585->1554|1585->1554|1585->1554|1586->1555|1586->1555|1587->1556|1587->1556|1587->1556|1588->1557|1590->1559|1590->1559|1591->1560|1598->1567|1598->1567|1599->1568|1600->1569|1600->1569|1601->1570|1602->1571|1602->1571|1602->1571|1602->1571|1603->1572|1605->1574|1605->1574|1605->1574|1605->1574|1606->1575|1606->1575|1606->1575|1607->1576|1608->1577|1608->1577|1609->1578|1609->1578|1610->1579|1610->1579|1611->1580|1611->1580|1612->1581|1612->1581|1612->1581|1612->1581|1613->1582|1617->1586|1617->1586|1618->1587|1618->1587|1620->1589|1620->1589|1620->1589|1621->1590|1623->1592|1623->1592|1624->1593|1625->1594|1625->1594|1626->1595|1626->1595|1626->1595|1627->1596|1628->1597|1628->1597|1629->1598|1629->1598|1637->1606|1637->1606|1637->1606|1638->1607|1643->1612|1643->1612|1644->1613|1644->1613|1644->1613|1644->1613|1644->1613|1644->1613|1645->1614|1645->1614|1646->1615|1646->1615|1646->1615|1647->1616|1648->1617|1648->1617|1648->1617|1648->1617|1649->1618|1653->1622|1653->1622|1654->1623|1654->1623|1656->1625|1656->1625|1656->1625|1657->1626|1658->1627|1658->1627|1659->1628|1660->1629|1660->1629|1664->1633|1664->1633|1665->1634|1670->1639|1670->1639|1671->1640|1674->1643|1674->1643|1675->1644|1678->1647|1678->1647|1679->1648|1680->1649|1680->1649|1681->1650|1681->1650|1682->1651|1682->1651|1684->1653|1684->1653|1684->1653|1685->1654|1689->1658|1689->1658|1690->1659|1695->1664|1695->1664|1696->1665|1697->1666|1697->1666|1698->1667|1699->1668|1699->1668|1700->1669|1703->1672|1703->1672|1704->1673|1707->1676|1707->1676|1708->1677|1709->1678|1709->1678|1710->1679|1710->1679|1711->1680|1711->1680|1713->1682|1713->1682|1713->1682|1714->1683|1718->1687|1718->1687|1719->1688|1725->1694|1725->1694|1726->1695|1727->1696|1727->1696|1728->1697|1729->1698|1729->1698|1730->1699|1733->1702|1733->1702|1734->1703|1737->1706|1737->1706|1738->1707|1739->1708|1739->1708|1740->1709|1740->1709|1741->1710|1741->1710|1743->1712|1743->1712|1743->1712|1744->1713|1748->1717|1748->1717|1749->1718|1755->1724|1755->1724|1756->1725|1759->1728|1759->1728|1760->1729|1763->1732|1763->1732|1764->1733|1765->1734|1765->1734|1766->1735|1766->1735|1767->1736|1767->1736|1769->1738|1769->1738|1769->1738|1770->1739|1772->1741|1772->1741|1773->1742|1780->1749|1780->1749|1781->1750|1783->1752|1783->1752|1784->1753|1784->1753|1785->1754|1785->1754|1787->1756|1787->1756|1787->1756|1789->1758|1789->1758|1789->1758|1790->1759|1793->1762|1793->1762|1794->1763|1801->1770|1801->1770|1803->1772|1807->1776|1807->1776|1808->1777|1811->1780|1811->1780|1812->1781|1812->1781|1812->1781|1813->1782|1816->1785|1816->1785|1817->1786|1818->1787|1818->1787|1819->1788|1825->1794|1825->1794|1826->1795|1826->1795|1828->1797|1828->1797|1830->1799|1831->1800|1831->1800|1832->1801|1835->1804|1835->1804|1836->1805|1837->1806|1837->1806|1838->1807|1838->1807|1841->1810|1841->1810|1842->1811|1842->1811|1843->1812|1843->1812|1844->1813|1844->1813|1846->1815|1846->1815|1846->1815|1847->1816|1849->1818|1849->1818|1850->1819|1857->1826|1857->1826|1858->1827|1859->1828|1859->1828|1860->1829|1863->1832|1863->1832|1864->1833|1865->1834|1865->1834|1866->1835|1866->1835|1866->1835|1868->1837|1868->1837|1869->1838|1869->1838|1870->1839|1870->1839|1872->1841|1872->1841|1872->1841|1873->1842|1875->1844|1875->1844|1877->1846|1877->1846|1877->1846|1878->1847|1884->1853|1884->1853|1887->1856
                  -- GENERATED --
              */
          