
package viewsMnuCompras.html

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

object compraIngreso extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template14[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Proveedor],List[tables.Equipo],List[tables.Moneda],List[List[String]],List[tables.Moneda],List[tables.Grupo],List[tables.Fabrica],List[tables.Unidad],List[tables.Regiones],List[tables.Sucursal],List[tables.Propiedad],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listProveedor: List[tables.Proveedor], listEquipo: List[tables.Equipo], listMoneda: List[tables.Moneda], listBodegas: List[List[String]], listMon: List[tables.Moneda],
listGrupos: List[tables.Grupo], listFabrica: List[tables.Fabrica], listUnidades: List[tables.Unidad], listRegiones: List[tables.Regiones],
listSucursales: List[tables.Sucursal], listPropiedad: List[tables.Propiedad]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*5.1*/("""
    	
"""),_display_(/*7.2*/main("")/*7.10*/ {_display_(Seq[Any](format.raw/*7.12*/("""

"""),_display_(/*9.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*9.50*/("""


	"""),format.raw/*12.2*/("""<!-- MODAL CREA PROVEEDOR -->
		"""),_display_(/*13.4*/modalProveedorNuevo(mapDiccionario, listRegiones)),format.raw/*13.53*/("""
		"""),format.raw/*14.3*/("""<script>
			const proveedorGrabaAjax = (id_proveedor) =>"""),format.raw/*15.48*/("""{"""),format.raw/*15.49*/("""
				"""),format.raw/*16.5*/("""$('#id_proveedor').val(id_proveedor);
				$('#rutProveedor').val($("#proveedorRut").val());
				$('#nombreProveedor').val($("#proveedorNickName").val());
				let tabla = document.getElementById('tablaListaProveedores');
				let row = tabla.insertRow(tabla.rows.length);
				let cell = row.insertCell(0);
				cell.style.textAlign = "left";
				cell.innerHTML = $("#proveedorRut").val();
				cell = row.insertCell(1);
				cell.style.textAlign = "left";
				cell.innerHTML = $("#proveedorNickName").val();
				cell = row.insertCell(2);
				cell.style.textAlign = "left";
				cell.innerHTML = $("#proveedorNombre").val();
				row.setAttribute("onClick", "$('#id_proveedor').val('" + id_proveedor + "'); $('#rutProveedor').val('" + $("#proveedorRut").val() + "');$('#nombreProveedor').val('" + $("#proveedorNickName").val() + "');");
				row.setAttribute("data-dismiss", "modal");
			"""),format.raw/*32.4*/("""}"""),format.raw/*32.5*/("""
		"""),format.raw/*33.3*/("""</script>
	<!-- FIN MODAL CREA PROVEEDOR -->
	
	
	<form action="/compraNuevo/" enctype="multipart/form-data" method="POST" onsubmit="return validarForm();">
		<div id="mostrarmostrar" style="display:none;">
			"""),_display_(/*39.5*/barraTitulo(mapDiccionario, "INGRESAR COMPRA Y/O ADQUISICION","(modifica inventarios)")),format.raw/*39.92*/("""
			"""),format.raw/*40.4*/("""<div class="row  justify-content-md-center">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<table class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<tr>
								<td  width="350px">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">"""),_display_(/*48.65*/mapDiccionario/*48.79*/.get("RUT")),format.raw/*48.90*/(""" """),format.raw/*48.91*/("""PROVEEDOR</span>
								  		</div>
								  		<input type="hidden" name="id_factura" value="0">
								  		<input type="hidden" name="numOcIConstruye" value="0">
								  		<input type="hidden" id="id_proveedor" name="id_proveedor" value="0">
	  									<input type="text" class="form-control"
	  										id="rutProveedor"
	  										value="" 
	  										style="background:white"
	  										onclick="$('#listaProveedor').modal('show');"
	  										readonly>
									</div>
								</td>
								<td>
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Nombre Proveedor</span>
								  		</div>
											<input type="text" class="form-control left"
												id="nombreProveedor"
												value=""
												style="background:white"
												onclick='$("#listaProveedor").modal("show")'
												required
												readonly>
									</div>
								</td>
								<td width="250px">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Nro.Documento</span>
								  		</div>
								  		<input type="text" class="form-control left"
								  				name="numeroFactura"
												id="numeroFactura"
												value = ""
												onkeydown="return ingresoInt(window.event)"
												onchange="if(value!='') verificarNumeroDocumento(value)"
												autocomplete="off"
												required>
									</div>
								</td>
								<td width="230px">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Fecha</span>
								  		</div>
								  		<input type="date" class="form-control center"
								  			name="fechaFactura" 
								  			id="fechaFactura"
								  			onblur="if(!limitaFecha(value,720,10)) value=''"
								  			value=""
						        			required>
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
								<td colspan="6">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Observaciones</span>
								  		</div>
	  									<textarea class="form-control" 
	  										name="observaciones" 
	  										autocomplete="off"></textarea>
									</div>
								</td>
							</tr>
						</thead>
					</table>
					<hr>
					
					
					
					
					
						<div class="noprint" align="center">
							<div class="row justify-content-md-center" >
								<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
									<input id="btnCargaExcel" type="button"  value="Copiar desde un Excel" class="btn btn-info btn-sm rounded-pill btn-block" 
										onclick="$('#modalCargaMasiva').modal('show');">&nbsp;&nbsp;&nbsp;
								</div>
							</div>
						</div>
					
						
					
					
					
					
					
					<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<TR>
								<TH width="10%">CODIGO</TH>
								<TH width="20%">EQUIPO</TH>
								<TH width="4%">KG</TH>
						        <TH width="4%">M2</TH>
						        <TH width="3%">UN</TH>
								<TH width="8%">CANTIDAD</TH>
								<TH width="3%">MONEDA</TH>
								<TH width="10%">P.UNITARIO<br>COMPRA</TH>
								<TH width="10%">TOTAL</TH>
								<TH width="10%">SUCURSAL</TH>
								<TH width="4%">ASIGNA<br>P.LISTA</TH>
								<TH width="15%">DESTINO</TH>
								<TH width="4%">TOT.KG</TH>
								<TH width="4%">TOT.M2</TH>
								<TH width="1%">DEL</TH>
							</TR>
						</thead>
						<tbody>
				 			<TR>
						        <td>
									<input type='text' class='form-control' style='background:white' 
										onclick="$('#listaEquipos').modal('show');"
										readonly>
						        </td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</TR>
				 			<TR style="background-color: #eeeeee">
						        <td></td>
								<td>TOTALES</td>
								<td></td>
								<td></td>
								<td></td>
								<td><div id="totalCantidad" align="right">0.00</div></td>
								<td></td>
								<td></td>
								<td><div id="totalTotal" align="right">0.00</div></td>
								<td></td>
								<td></td>
								<td></td>
								<td><div id="totalKG" align="right">0.00</div></td>
								<td><div id="totalM2" align="right">0.00</div></td>
								<td></td>
							</TR>
						</tbody>
					</table>
				</div>
			</div>
			<div class="noprint" align="left">
				<div class="row justify-content-md-center" >
					<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
						<input type="button"  value="Cancelar" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
					</div>
					<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
						<input type="submit"  id="aplica" value='Grabar' class="btn btn-primary btn-sm rounded-pill btn-block">
					</div>
				</div>
			</div>
		</div>
	</form>
	
	<div id="modalCargaMasiva" class="modal" role="dialog" data-backdrop="static" data-keyboard="false">
	  <div class="modal-dialog modal-sm modal-dialog-scrollable" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title">Carga masiva de compras</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        
			<div class="row">
				<div class="col-sm-12" style="text-align:center">
					<form action="/routes2/compraPlantilla/" method="POST"  onsubmit="return validarForm2();">
						<button type="submit" id="btnSubmit2" class="btn btn-sm  btn-success" style="font-size: 10px;">DESCARGAR PLANTILLA</button>
					</form>
					<br>
					<form id="compraCargaPlantilla" action="/routes2/compraCargaPlantilla/" method="POST" enctype="multipart/form-data">
						<span class="btn btn-info btn-sm btn-file" style="font-size: 8px;">
							<div>SUBIR ARCHIVO</div>
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
	
	<div id="msgNuevosCodigos" class="modal" role="dialog" data-backdrop="static" data-keyboard="false">
	  <div class="modal-dialog modal-sm modal-dialog-scrollable modal-lg" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title">EXISTE EQUIPOS EN LA PLANTILLA EXCEL QUE NO EXISTEN EN MADA</h5>
	       		<button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				 	<span aria-hidden='true'>&times;</span>
				</button>
	      </div>
	      <div class="modal-body">
				<div class="col-sm-12" style="text-align:center">
				  <button type="button" class="btn btn-sm  btn-primary" style="font-size: 10px;" 
					onclick='$("#compraCargaPlantilla").submit();'>CREAR LOS EQUIPOS QUE NO EXISTEN</button><br><br>
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
	
	<div id='listaProveedor' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>SELECCIONAR PROVEEDOR</h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<div class="noprint" align="center">
						<a href="#" onclick="$('#listaProveedor').modal('hide'); $('#modalProveedorNuevo').modal('show');">
							<kbd style="background-color: #73C6B6">Agregar Nuevo</kbd>
						</a>
					</div>
					<table id="tablaListaProveedores" class='table table-sm table-bordered table-condensed table-hover table-fluid'>
						<thead style="background-color: #eeeeee">
							<TR> 
								<TH>"""),_display_(/*296.14*/mapDiccionario/*296.28*/.get("RUT")),format.raw/*296.39*/("""</TH>
								<TH>NICK</TH>
								<TH>NOMBRE</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*302.9*/for(lista1 <- listProveedor) yield /*302.37*/{_display_(Seq[Any](format.raw/*302.38*/("""
								"""),format.raw/*303.9*/("""<TR onClick="$('#id_proveedor').val("""),_display_(/*303.46*/lista1/*303.52*/.getId()),format.raw/*303.60*/("""); $('#rutProveedor').val('"""),_display_(/*303.88*/lista1/*303.94*/.getRut()),format.raw/*303.103*/("""');$('#nombreProveedor').val('"""),_display_(/*303.134*/lista1/*303.140*/.getNickName()),format.raw/*303.154*/("""');" data-dismiss="modal">
									<td  style="text-align:left;">"""),_display_(/*304.41*/lista1/*304.47*/.getRut()),format.raw/*304.56*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*305.41*/lista1/*305.47*/.getNickName()),format.raw/*305.61*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*306.41*/lista1/*306.47*/.getNombre()),format.raw/*306.59*/("""</td>
								</TR>
				 			""")))}),format.raw/*308.10*/("""
						"""),format.raw/*309.7*/("""</tbody>
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
	
	<div id='listaEquipos' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>SELECCIONAR EQUIPO</h5>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type='button' class='btn btn-sm  btn-info' style='font-size: 10px;' data-dismiss='modal' onclick='$("#modalNuevoEquipo").modal("show")'>Agregar nuevo equipo</button>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<table id="tablaListaEquipos" class='table table-sm table-bordered table-condensed table-hover table-fluid'>
						<thead style="background-color: #eeeeee">
							<TR> 
								<th>GRUPO</th>
								<th>CODIGO</th>
								<th>EQUIPO</th>
								<th>ORIGEN</th>
								
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*344.9*/for(lista1 <- listEquipo) yield /*344.34*/{_display_(Seq[Any](format.raw/*344.35*/("""
								"""),format.raw/*345.9*/("""<TR onClick="auxCodEquip='"""),_display_(/*345.36*/lista1/*345.42*/.getCodigo()),format.raw/*345.54*/("""'; auxNomEquip='"""),_display_(/*345.71*/lista1/*345.77*/.getNombre()),format.raw/*345.89*/("""'; auxKgEquip='"""),_display_(/*345.105*/lista1/*345.111*/.getKg()),format.raw/*345.119*/("""'; auxM2Equip='"""),_display_(/*345.135*/lista1/*345.141*/.getM2()),format.raw/*345.149*/("""'; auxUnEquip='"""),_display_(/*345.165*/lista1/*345.171*/.getUnidad()),format.raw/*345.183*/("""'; selectEquipo("""),_display_(/*345.200*/lista1/*345.206*/.getId()),format.raw/*345.214*/(""")" data-dismiss="modal">
									<td  style="text-align:left;">"""),_display_(/*346.41*/lista1/*346.47*/.getGrupo()),format.raw/*346.58*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*347.41*/lista1/*347.47*/.getCodigo()),format.raw/*347.59*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*348.41*/lista1/*348.47*/.getNombre()),format.raw/*348.59*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*349.41*/lista1/*349.47*/.getFabrica()),format.raw/*349.60*/("""</td>
								</TR>
				 			""")))}),format.raw/*351.10*/("""
						"""),format.raw/*352.7*/("""</tbody>
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
	
	
	
	
	<div id='modalNuevoEquipo' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>INGRESAR NUEVO EQUIPO</h5>
					<button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<TR>
							<td  style="text-align:left;">GRUPO</td>
							<td style="text-align:left">
								<select class="custom-select"
									id="nuevoEquipoId_grupo"
									required>
									"""),_display_(/*384.11*/for(lista <- listGrupos) yield /*384.35*/{_display_(Seq[Any](format.raw/*384.36*/("""
										"""),format.raw/*385.11*/("""<option value=""""),_display_(/*385.27*/lista/*385.32*/.getId()),format.raw/*385.40*/("""">"""),_display_(/*385.43*/lista/*385.48*/.getNombre()),format.raw/*385.60*/("""</option>
									""")))}),format.raw/*386.11*/("""
								"""),format.raw/*387.9*/("""</select>
							</td>
						</TR>
						<TR>
							<td  style="text-align:left;">PROPIEDAD</td>
							<td style="text-align:left">
								<select class="custom-select"
								id="id_propiedad"
								name="id_propiedad">
									<option value=""""),_display_(/*396.26*/listPropiedad/*396.39*/.get(0).getId()),format.raw/*396.54*/("""">"""),_display_(/*396.57*/listPropiedad/*396.70*/.get(0).getNombre()),format.raw/*396.89*/("""</option>
									"""),_display_(/*397.11*/for(lista <- listPropiedad) yield /*397.38*/{_display_(Seq[Any](format.raw/*397.39*/("""
										"""),format.raw/*398.11*/("""<option value=""""),_display_(/*398.27*/lista/*398.32*/.getId()),format.raw/*398.40*/("""">"""),_display_(/*398.43*/lista/*398.48*/.getNombre()),format.raw/*398.60*/("""</option>
									""")))}),format.raw/*399.11*/("""
								"""),format.raw/*400.9*/("""</select>
							</td>
						</TR>
						<tr>
							<td style="text-align:left; vertical-align:top">CODIGO EQUIPO: </td>
							<td style="text-align:left; vertical-align:top">
								<input type="text" class="form-control left"
									id="nuevoEquipoCodigo"
									required
									autocomplete="off"
									onkeydown="return sinReservCodigos(window.event)"
									onchange="value=value.replace(/\s/g,'').toUpperCase(); verificaCodigo(value)"
									maxlength="50">
							</td>
						</tr>
						<tr>
							<td style="text-align:left; vertical-align:top">NOMBRE EQUIPO: </td>
							<td style="text-align:left; vertical-align:top">
								<input type="text" class="form-control left"
									id="nuevoEquipoNombre"
									onkeydown="return sinReservados(window.event)"
									required
									autocomplete="off"
									maxlength="100">
							</td>
						</tr>
						<tr>
							<td style="text-align:left; vertical-align:top">FABRICANTE: </td>
							<td style="text-align:left; vertical-align:top">
								<select class="custom-select" 
									id="nuevoEquipoId_fabrica"
									required>
									"""),_display_(/*432.11*/for(lista <- listFabrica) yield /*432.36*/{_display_(Seq[Any](format.raw/*432.37*/("""
										"""),format.raw/*433.11*/("""<option value=""""),_display_(/*433.27*/lista/*433.32*/.id),format.raw/*433.35*/("""">"""),_display_(/*433.38*/lista/*433.43*/.nickName),format.raw/*433.52*/("""</option>
									""")))}),format.raw/*434.11*/("""
								"""),format.raw/*435.9*/("""</select>
							</td>
						</tr>
						<tr>
							<td style="text-align:left; vertical-align:top">UNIDAD: </td>
							<td style="text-align:left; vertical-align:top">
								<select class="custom-select" 
									id="nuevoEquipoId_unidad"
									required>
									"""),_display_(/*444.11*/for(lista <- listUnidades) yield /*444.37*/{_display_(Seq[Any](format.raw/*444.38*/("""
										"""),format.raw/*445.11*/("""<option value=""""),_display_(/*445.27*/lista/*445.32*/.id),format.raw/*445.35*/("""">"""),_display_(/*445.38*/lista/*445.43*/.nombre),format.raw/*445.50*/("""</option>
									""")))}),format.raw/*446.11*/("""
								"""),format.raw/*447.9*/("""</select>
							</td>
						</tr>
						<tr>
							<td style="text-align:left; vertical-align:top">PESO (KG): </td>
							<td style="text-align:left; vertical-align:top">
								<input type="text" class="form-control left"
									id="nuevoEquipoId_kg"
									autocomplete="off"
									value="0.00"
									onfocus="value=value.replace(/,/g,'')" 
									onkeydown="return ingresoDouble(window.event)"
									onchange="value = formatStandar(value,2);">
							</td>
						</tr>
						<tr>
							<td style="text-align:left; vertical-align:top">AREA (M2): </td>
							<td style="text-align:left; vertical-align:top">
								<input type="text" class="form-control left"
									id="nuevoEquipoId_m2"
									autocomplete="off"
									value="0.00"
									onfocus="value=value.replace(/,/g,'')" 
									onkeydown="return ingresoDouble(window.event)"
									onchange="value = formatStandar(value,2);">
							</td>
						</tr>
					</table>
					<div class='row'>
						<div class='col-sm-5' style='text-align:center'>
							<button type='button' class='btn btn-sm  btn-success' style='font-size: 10px;' onclick="grabaNuevoEquipo()">GRABAR</button>
						</div>
						<div class='col-sm-5' style='text-align:center'>
							<button type='button' class='btn btn-sm  btn-light' style='font-size: 10px;' data-dismiss='modal'>Cancelar</button>
						</div>
					</div>
			</div>
			</div>
		</div>
	</div>
	
	<div id='modalPrecioLista' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-md' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>ASIGNAR/CAMBIAR PRECIO LISTA</h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<div id="modalPrecios"></div>
	   				<div class='row'>
						<div class='col-sm-12' style='text-align:center'>
							<button type='button' class='btn btn-sm  btn-warning' style='font-size: 10px;' data-dismiss='modal'>CERRAR</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div id='modalRevisarEquiposNuevos' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-md' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<div id="listaRevisarEquiposNuevos"></div>
					<br><br>
	   				<div class='row'>
						<div class='col-sm-12' style='text-align:center'>
							<button type='button' class='btn btn-sm  btn-warning' 
							style='font-size: 10px;' data-dismiss='modal'
							onclick="location.reload()">CERRAR</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
	
""")))}),format.raw/*534.2*/("""




"""),format.raw/*539.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*541.31*/("""{"""),format.raw/*541.32*/("""
		
		"""),format.raw/*543.3*/("""$('#tablaListaProveedores').DataTable("""),format.raw/*543.41*/("""{"""),format.raw/*543.42*/("""
	    	"""),format.raw/*544.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
	    	"order": [[ 1, "asc" ]],
	    	"language": """),format.raw/*547.19*/("""{"""),format.raw/*547.20*/("""
	        	"""),format.raw/*548.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*549.10*/("""}"""),format.raw/*549.11*/("""
	  	"""),format.raw/*550.5*/("""}"""),format.raw/*550.6*/(""" """),format.raw/*550.7*/(""");
		
		$('#tablaListaEquipos').DataTable("""),format.raw/*552.37*/("""{"""),format.raw/*552.38*/("""
	    	"""),format.raw/*553.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
	    	"order": [[ 1, "asc" ]],
	    	"language": """),format.raw/*556.19*/("""{"""),format.raw/*556.20*/("""
	        	"""),format.raw/*557.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*558.10*/("""}"""),format.raw/*558.11*/("""
	  	"""),format.raw/*559.5*/("""}"""),format.raw/*559.6*/(""" """),format.raw/*559.7*/(""");
		  
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*562.2*/("""}"""),format.raw/*562.3*/(""");
	
	const verificarNumeroDocumento = (value) =>"""),format.raw/*564.45*/("""{"""),format.raw/*564.46*/("""
		"""),format.raw/*565.3*/("""let id_proveedor = $("#id_proveedor").val();
		if(id_proveedor==0)"""),format.raw/*566.22*/("""{"""),format.raw/*566.23*/("""
			"""),format.raw/*567.4*/("""alertify.alert('Primero debe seleccionar el proveedor', function () """),format.raw/*567.72*/("""{"""),format.raw/*567.73*/("""
 				"""),format.raw/*568.6*/("""$("#numeroFactura").val("");
 			"""),format.raw/*569.5*/("""}"""),format.raw/*569.6*/(""");
		"""),format.raw/*570.3*/("""}"""),format.raw/*570.4*/("""else"""),format.raw/*570.8*/("""{"""),format.raw/*570.9*/("""
			"""),format.raw/*571.4*/("""var formData = new FormData();
		  	formData.append('numeroFactura',value);
		  	formData.append('id_proveedor',id_proveedor);
	        $.ajax("""),format.raw/*574.17*/("""{"""),format.raw/*574.18*/("""
	            """),format.raw/*575.14*/("""url: "/verificaNumeroFacturaAjax/",
	            type: "POST",
	            method: "POST",
	            data: formData,
	            cache: false,
	            contentType: false,
		     	processData: false,
		     	success: function(respuesta)"""),format.raw/*582.37*/("""{"""),format.raw/*582.38*/("""
		     		"""),format.raw/*583.10*/("""if(respuesta=="existe")"""),format.raw/*583.33*/("""{"""),format.raw/*583.34*/("""
		     			"""),format.raw/*584.11*/("""alertify.alert('El número de documento ya existe para este proveedor, intente con otro', function () """),format.raw/*584.112*/("""{"""),format.raw/*584.113*/("""
		     				"""),format.raw/*585.12*/("""$("#numeroFactura").val("");
		     			"""),format.raw/*586.11*/("""}"""),format.raw/*586.12*/(""");
		     		"""),format.raw/*587.10*/("""}"""),format.raw/*587.11*/("""
		     		"""),format.raw/*588.10*/("""if(respuesta=="error")"""),format.raw/*588.32*/("""{"""),format.raw/*588.33*/("""
		     			"""),format.raw/*589.11*/("""alertify.alert(msgError, function () """),format.raw/*589.48*/("""{"""),format.raw/*589.49*/("""
			     			"""),format.raw/*590.12*/("""location.href = "/";
			     		"""),format.raw/*591.11*/("""}"""),format.raw/*591.12*/(""");
		     		"""),format.raw/*592.10*/("""}"""),format.raw/*592.11*/("""
		     	"""),format.raw/*593.9*/("""}"""),format.raw/*593.10*/("""
	        """),format.raw/*594.10*/("""}"""),format.raw/*594.11*/(""");
		"""),format.raw/*595.3*/("""}"""),format.raw/*595.4*/("""
			
	"""),format.raw/*597.2*/("""}"""),format.raw/*597.3*/("""
	
	"""),format.raw/*599.2*/("""let mapDec = new Map();
	"""),_display_(/*600.3*/for(lista <- listMon) yield /*600.24*/{_display_(Seq[Any](format.raw/*600.25*/("""
		"""),format.raw/*601.3*/("""mapDec.set("""),_display_(/*601.15*/lista/*601.20*/.getId()),format.raw/*601.28*/(""","""),_display_(/*601.30*/lista/*601.35*/.getNumeroDecimales()),format.raw/*601.56*/(""");
	""")))}),format.raw/*602.3*/("""
	

	
	"""),format.raw/*606.2*/("""let auxCodEquip = "";
	let auxNomEquip = "";
	let auxKgEquip = "";
	let auxM2Equip = "";
	let auxUnEquip = "";
	let cont = 0;
	
	const selectEquipo = (id_equipo) => """),format.raw/*613.38*/("""{"""),format.raw/*613.39*/("""
		"""),format.raw/*614.3*/("""cont++;
		let tabla = document.getElementById("tablaPrincipal");
		let row = tabla.insertRow(tabla.rows.length-2);
		let cell;
		
		cell = row.insertCell(0);
		cell.style.textAlign = "left";
		cell.innerHTML = "<input type=\"hidden\" name=\"id_equipo[]\" value=\""+id_equipo+"\">"+auxCodEquip;
		
		cell = row.insertCell(1);
		cell.style.textAlign = "left";
		cell.innerHTML = auxNomEquip;
		
		cell = row.insertCell(2);
		cell.style.textAlign = "right";
		cell.innerHTML = "<div id=\"KG_"+cont+"\" align=\"right\">"+formatStandar(auxKgEquip,2)+"</div>";
		
		cell = row.insertCell(3);
		cell.style.textAlign = "right";
		cell.innerHTML = "<div id=\"M2_"+cont+"\" align=\"right\">"+formatStandar(auxM2Equip,2)+"</div>";
		
		cell = row.insertCell(4);
		cell.style.textAlign = "center";
		cell.innerHTML = auxUnEquip;
		
		cell = row.insertCell(5);
		cell.style.textAlign = "center";
		cell.innerHTML = "<input type=\"text\" class=\"cantidad form-control right\" "+
			"name=\"cantidad[]\" "+
			"id=\"cantidad_"+cont+"\" "+
			"value=\"0.00\" "+
			"onfocus=\"value=value.replace(/,/g,'');\" "+
			"onkeydown=\"return ingresoDouble(window.event)\" "+
			"autocomplete=\"off\" "+
			"onchange=\"if(value=='') value=0; value=formatStandar(value,2); actualTotales(id);\">";
		
		cell = row.insertCell(6);
		cell.style.textAlign = "center";
		let mon = "<select class=\"form-control form-control-sm\" id=\"idMoneda_"+cont+"\" name=\"id_monedaCompra[]\">";
    		"""),_display_(/*653.8*/for(mon <- listMoneda) yield /*653.30*/{_display_(Seq[Any](format.raw/*653.31*/("""
    			"""),format.raw/*654.8*/("""mon += "<option value='"""),_display_(/*654.32*/mon/*654.35*/.getId()),format.raw/*654.43*/("""'>"""),_display_(/*654.46*/mon/*654.49*/.getNickName()),format.raw/*654.63*/("""</option>";
			""")))}),format.raw/*655.5*/("""
    		"""),format.raw/*656.7*/("""mon += "</select>";
    	cell.innerHTML = mon;
	
		cell = row.insertCell(7);
		cell.style.textAlign = "center";
		cell.innerHTML = "<input type=\"text\" class=\"form-control right\" "+
			"name=\"puCompra[]\" "+
			"id=\"puCompra_"+cont+"\" "+
			"value=\"0\" "+
			"onfocus=\"value=value.replace(/,/g,'');\" "+ 
			"onkeydown=\"return ingresoDouble(window.event)\" "+
			"autocomplete=\"off\" "+
			"onchange=\"if(value=='') value=0; value=formatStandar(value,2); actualTotales(id);\"> ";
		
		cell = row.insertCell(8);
		cell.style.textAlign = "right";
		cell.innerHTML = "<div class=\"total\" id=\"total_"+cont+"\" align=\"right\">0.00</div>";
		
		cell = row.insertCell(9);
		cell.style.textAlign = "center";
		let auxSucursales = "<select class=\"form-control form-control-sm \"" +
								" onchange= \"actualizaSucursal('"+cont+"','"+id_equipo+"',value);\">" +
										"<option value='0'></option>";
												"""),_display_(/*679.14*/for(lista <- listSucursales) yield /*679.42*/{_display_(Seq[Any](format.raw/*679.43*/("""
													"""),format.raw/*680.14*/("""auxSucursales += "<option value=\""""),_display_(/*680.49*/lista/*680.54*/.getId()),format.raw/*680.62*/("""\">"""),_display_(/*680.66*/lista/*680.71*/.getNombre()),format.raw/*680.83*/("""</option>";
												""")))}),format.raw/*681.14*/("""
							        		"""),format.raw/*682.18*/("""auxSucursales += "</select></td>";
		cell.innerHTML = auxSucursales;
		
		
		
		
		cell = row.insertCell(10);
		cell.style.textAlign = "center";
		cell.innerHTML = "<input type=\"hidden\" id=\"id_sucursal_"+cont+"\" value=\"0\">" +
						"<a href=\"#\" onclick=\"selSucursalPrecio("+cont+","+id_equipo+")\"> "+
							"<kbd style=\"background-color: #CACFD2\">Asignar</kbd></a>";
		
		cell = row.insertCell(11);
		cell.style.textAlign = "center";
		let des = "<div id='selBodegaDestino_"+cont+"'><select class=\"selBodDest form-control form-control-sm\" name=\"id_bodegaDestino[]\">"+
					"<option value='0'></option>";
    		"""),_display_(/*698.8*/for(bod <- listBodegas) yield /*698.31*/{_display_(Seq[Any](format.raw/*698.32*/("""
    			"""),format.raw/*699.8*/("""des += "<option value='"""),_display_(/*699.32*/bod/*699.35*/.get(1)),format.raw/*699.42*/("""'>"""),_display_(/*699.45*/bod/*699.48*/.get(5)),format.raw/*699.55*/("""</option>";
			""")))}),format.raw/*700.5*/("""
    		"""),format.raw/*701.7*/("""des += "</select></div>";
		cell.innerHTML = des;
			
		cell = row.insertCell(12);
		cell.style.textAlign = "right";
		cell.innerHTML = "<div class=\"kg\" id=\"totKG_"+cont+"\" align=\"right\">0.00</div>";
		
		cell = row.insertCell(13);
		cell.style.textAlign = "right";
		cell.innerHTML = "<div class=\"m2\" id=\"totM2_"+cont+"\" align=\"right\">0.00</div>";
		
		cell = row.insertCell(14);
		cell.style.textAlign = "center";
		cell.innerHTML = "<a href=\"#\" onclick= \"eliminarFila(this)\"><kbd style=\"background-color: red\">X</kbd></a>";
		
	"""),format.raw/*716.2*/("""}"""),format.raw/*716.3*/("""
	
	"""),format.raw/*718.2*/("""const eliminarFila = (nodo) =>"""),format.raw/*718.32*/("""{"""),format.raw/*718.33*/("""
		"""),format.raw/*719.3*/("""let nodoTd = nodo.parentNode.parentNode; 
		$(nodoTd).remove();
		sumaTotales();
	"""),format.raw/*722.2*/("""}"""),format.raw/*722.3*/("""
	
	"""),format.raw/*724.2*/("""const actualTotales = (id) =>"""),format.raw/*724.31*/("""{"""),format.raw/*724.32*/("""
		"""),format.raw/*725.3*/("""let aux = id.split("_");
		let cantidad = $("#cantidad_"+aux[1]).val().replace(/,/g,'');
		let puCompra = $("#puCompra_"+aux[1]).val().replace(/,/g,'');
		let id_moneda = $("#idMoneda_"+aux[1]).val();
		let nroDecimal = mapDec.get(parseFloat(id_moneda));
		let kg = $("#KG_"+aux[1]).text().replace(/,/g,'');
		let m2 = $("#M2_"+aux[1]).text().replace(/,/g,'');
		let total = parseFloat(cantidad) * parseFloat(puCompra);
		let totKG = parseFloat(cantidad) * parseFloat(kg);
		let totM2 = parseFloat(cantidad) * parseFloat(m2);
		$("#puCompra_"+aux[1]).val(formatStandar(puCompra,nroDecimal));
		$("#total_"+aux[1]).text(formatStandar(total,nroDecimal));
		$("#totKG_"+aux[1]).text(formatStandar(totKG,2));
		$("#totM2_"+aux[1]).text(formatStandar(totM2,2));
		sumaTotales();
	"""),format.raw/*740.2*/("""}"""),format.raw/*740.3*/("""
	
	"""),format.raw/*742.2*/("""const sumaTotales = () => """),format.raw/*742.28*/("""{"""),format.raw/*742.29*/("""
		 """),format.raw/*743.4*/("""let sum = 0;
			$(".cantidad").each(function() """),format.raw/*744.35*/("""{"""),format.raw/*744.36*/("""
				"""),format.raw/*745.5*/("""let val = $(this).val().replace(/,/g,'');
				sum += parseFloat(val);
			"""),format.raw/*747.4*/("""}"""),format.raw/*747.5*/("""); $("#totalCantidad").text(formatStandar(sum,2));
			sum = 0;
			$(".total").each(function() """),format.raw/*749.32*/("""{"""),format.raw/*749.33*/("""
				"""),format.raw/*750.5*/("""let val = $(this).text().replace(/,/g,'');
				sum += parseFloat(val);
			"""),format.raw/*752.4*/("""}"""),format.raw/*752.5*/("""); $("#totalTotal").text(formatStandar(sum,2));
			sum = 0;
			$(".kg").each(function() """),format.raw/*754.29*/("""{"""),format.raw/*754.30*/("""
				"""),format.raw/*755.5*/("""let val = $(this).text().replace(/,/g,'');
				sum += parseFloat(val);
			"""),format.raw/*757.4*/("""}"""),format.raw/*757.5*/("""); $("#totalKG").text(formatStandar(sum,2));
			sum = 0;
			$(".m2").each(function() """),format.raw/*759.29*/("""{"""),format.raw/*759.30*/("""
				"""),format.raw/*760.5*/("""let val = $(this).text().replace(/,/g,'');
				sum += parseFloat(val);
			"""),format.raw/*762.4*/("""}"""),format.raw/*762.5*/("""); $("#totalM2").text(formatStandar(sum,2));
	 """),format.raw/*763.3*/("""}"""),format.raw/*763.4*/("""
	
	
	"""),format.raw/*766.2*/("""const validarForm = () =>"""),format.raw/*766.27*/("""{"""),format.raw/*766.28*/("""
		"""),format.raw/*767.3*/("""let flag = true;
		if($("#id_proveedor").val()=="0")"""),format.raw/*768.36*/("""{"""),format.raw/*768.37*/("""
			"""),format.raw/*769.4*/("""flag = false;
			alertify.alert('FALTA SELECCIONAR PROVEEDOR', function () """),format.raw/*770.62*/("""{"""),format.raw/*770.63*/("""
				"""),format.raw/*771.5*/("""return(flag);
     		"""),format.raw/*772.8*/("""}"""),format.raw/*772.9*/(""");
		"""),format.raw/*773.3*/("""}"""),format.raw/*773.4*/("""else"""),format.raw/*773.8*/("""{"""),format.raw/*773.9*/("""
			"""),format.raw/*774.4*/("""$("#tablaPrincipal").dataTable().fnDestroy();
			let tabla = document.getElementById("tablaPrincipal");
			let cantTotal = $("#totalCantidad").text().replace(/,/g,'');
			if(tabla.rows.length > 3 && parseFloat(cantTotal) > 0)"""),format.raw/*777.58*/("""{"""),format.raw/*777.59*/("""
				
				"""),format.raw/*779.5*/("""flag = true;
				$(".selBodDest").each(function() """),format.raw/*780.38*/("""{"""),format.raw/*780.39*/("""
					"""),format.raw/*781.6*/("""let val = $(this).val();
					if(val == 0)"""),format.raw/*782.18*/("""{"""),format.raw/*782.19*/("""
						"""),format.raw/*783.7*/("""flag = false;
					"""),format.raw/*784.6*/("""}"""),format.raw/*784.7*/("""
				"""),format.raw/*785.5*/("""}"""),format.raw/*785.6*/(""");
				
				if(flag)"""),format.raw/*787.13*/("""{"""),format.raw/*787.14*/("""
					"""),format.raw/*788.6*/("""return(flag);
				"""),format.raw/*789.5*/("""}"""),format.raw/*789.6*/("""else"""),format.raw/*789.10*/("""{"""),format.raw/*789.11*/("""
					"""),format.raw/*790.6*/("""alertify.alert('PARA GRABAR UNA COMPRA TODOS LAS LINEAS DEBEN TENER UN DESTINO', function () """),format.raw/*790.99*/("""{"""),format.raw/*790.100*/("""
						"""),format.raw/*791.7*/("""return(flag);
	     			"""),format.raw/*792.10*/("""}"""),format.raw/*792.11*/(""");
				"""),format.raw/*793.5*/("""}"""),format.raw/*793.6*/("""
			"""),format.raw/*794.4*/("""}"""),format.raw/*794.5*/("""else"""),format.raw/*794.9*/("""{"""),format.raw/*794.10*/("""
				"""),format.raw/*795.5*/("""flag = false;
				alertify.alert('PARA GRABAR UNA COMPRA AL MENOS DEBE COMPRAR UN EQUIPOS', function () """),format.raw/*796.91*/("""{"""),format.raw/*796.92*/("""
					"""),format.raw/*797.6*/("""return(flag);
	     		"""),format.raw/*798.9*/("""}"""),format.raw/*798.10*/(""");
			"""),format.raw/*799.4*/("""}"""),format.raw/*799.5*/("""
		"""),format.raw/*800.3*/("""}"""),format.raw/*800.4*/("""
		"""),format.raw/*801.3*/("""return(flag);
	"""),format.raw/*802.2*/("""}"""),format.raw/*802.3*/("""
	
	"""),format.raw/*804.2*/("""const selSucursalPrecio = (index,id_equipo) => """),format.raw/*804.49*/("""{"""),format.raw/*804.50*/("""
		"""),format.raw/*805.3*/("""let id_sucursal = $("#id_sucursal_"+index).val();
		if(id_sucursal != 0)"""),format.raw/*806.23*/("""{"""),format.raw/*806.24*/("""
			"""),format.raw/*807.4*/("""var formData = new FormData();
			formData.append('id_sucursal',id_sucursal);
		  	formData.append('id_equipo',id_equipo);

	        $.ajax("""),format.raw/*811.17*/("""{"""),format.raw/*811.18*/("""
	            """),format.raw/*812.14*/("""url: "/modalPreciosAjax/",
	            type: "POST",
	            method: "POST",
	            data: formData,
	            cache: false,
	            contentType: false,
		     	processData: false,
		     	success: function(rs1)"""),format.raw/*819.31*/("""{"""),format.raw/*819.32*/("""
					"""),format.raw/*820.6*/("""$.ajax("""),format.raw/*820.13*/("""{"""),format.raw/*820.14*/("""
			            """),format.raw/*821.16*/("""url: "/routes2/compraVistaSelBodDestAjax/",
			            type: "POST",
			            method: "POST",
			            data: formData,
			            cache: false,
			            contentType: false,
				     	processData: false,
				     	success: function(rs2)"""),format.raw/*828.33*/("""{"""),format.raw/*828.34*/("""
							"""),format.raw/*829.8*/("""document.getElementById("modalPrecios").innerHTML =  rs1;
				     		$("#modalPrecioLista").modal("show");
				     	"""),format.raw/*831.11*/("""}"""),format.raw/*831.12*/("""
			        """),format.raw/*832.12*/("""}"""),format.raw/*832.13*/(""");
		     	"""),format.raw/*833.9*/("""}"""),format.raw/*833.10*/("""
	        """),format.raw/*834.10*/("""}"""),format.raw/*834.11*/(""");
		"""),format.raw/*835.3*/("""}"""),format.raw/*835.4*/("""else"""),format.raw/*835.8*/("""{"""),format.raw/*835.9*/("""
			"""),format.raw/*836.4*/("""alertify.alert("Primero debe seleccionar la Sucursal", function () """),format.raw/*836.71*/("""{"""),format.raw/*836.72*/("""
     			"""),format.raw/*837.9*/("""}"""),format.raw/*837.10*/(""");
		"""),format.raw/*838.3*/("""}"""),format.raw/*838.4*/("""
	"""),format.raw/*839.2*/("""}"""),format.raw/*839.3*/("""
	
	"""),format.raw/*841.2*/("""const actualizaSucursal = (index, auxIdEquipo, id_sucursal) =>"""),format.raw/*841.64*/("""{"""),format.raw/*841.65*/("""
		"""),format.raw/*842.3*/("""$("#modalSucursal").modal("hide");
		if(id_sucursal != 0)"""),format.raw/*843.23*/("""{"""),format.raw/*843.24*/("""
			"""),format.raw/*844.4*/("""var formData = new FormData();
			formData.append('id_sucursal',id_sucursal);
		  	formData.append('id_equipo',auxIdEquipo);
	        $.ajax("""),format.raw/*847.17*/("""{"""),format.raw/*847.18*/("""
	            """),format.raw/*848.14*/("""url: "/routes2/compraVistaSelBodDestAjax/",
	            type: "POST",
	            method: "POST",
	            data: formData,
	            cache: false,
	            contentType: false,
		     	processData: false,
		     	success: function(rs2)"""),format.raw/*855.31*/("""{"""),format.raw/*855.32*/("""
		     		"""),format.raw/*856.10*/("""document.getElementById("selBodegaDestino_"+index).innerHTML =  rs2["vista"];
					$("#id_sucursal_"+index).val(id_sucursal);
		     		$("#sucursal_"+index).val(rs2["nameSucursal"]);
		     	"""),format.raw/*859.9*/("""}"""),format.raw/*859.10*/("""
	        """),format.raw/*860.10*/("""}"""),format.raw/*860.11*/(""");
		"""),format.raw/*861.3*/("""}"""),format.raw/*861.4*/("""
	"""),format.raw/*862.2*/("""}"""),format.raw/*862.3*/("""
	
	
	
	"""),format.raw/*866.2*/("""const actualizaSelect  = (index, id_bodega, id_equipo) => """),format.raw/*866.60*/("""{"""),format.raw/*866.61*/("""
		"""),format.raw/*867.3*/("""var formData = new FormData();
		formData.append('id_bodega',id_bodega);
	  	formData.append('id_equipo',id_equipo);
		$.ajax("""),format.raw/*870.10*/("""{"""),format.raw/*870.11*/("""
            """),format.raw/*871.13*/("""url: "/routes2/compraVistaSelBodDestAjax2/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(rs2)"""),format.raw/*878.30*/("""{"""),format.raw/*878.31*/("""
	     		"""),format.raw/*879.9*/("""document.getElementById("selBodegaDestino_"+index).innerHTML =  rs2;
	     	"""),format.raw/*880.8*/("""}"""),format.raw/*880.9*/("""
        """),format.raw/*881.9*/("""}"""),format.raw/*881.10*/(""");
	"""),format.raw/*882.2*/("""}"""),format.raw/*882.3*/("""
	
	"""),format.raw/*884.2*/("""const cambiarPrecio = (id_sucursal, id_equipo,campo,valor) => """),format.raw/*884.64*/("""{"""),format.raw/*884.65*/("""
		"""),format.raw/*885.3*/("""let id_moneda = $("#precioMoneda").val();
		var formData = new FormData();
		formData.append('id_sucursal',id_sucursal);
		formData.append('id_equipo',id_equipo);
		formData.append('id_moneda',id_moneda);
		formData.append('campo',campo);
		formData.append('valor',valor);
		$.ajax("""),format.raw/*892.10*/("""{"""),format.raw/*892.11*/("""
            """),format.raw/*893.13*/("""url: "/actualizaPrecioAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*900.36*/("""{"""),format.raw/*900.37*/("""
	     		"""),format.raw/*901.9*/("""if(respuesta=="error")"""),format.raw/*901.31*/("""{"""),format.raw/*901.32*/("""
	     			"""),format.raw/*902.10*/("""alertify.alert(msgError, function () """),format.raw/*902.47*/("""{"""),format.raw/*902.48*/("""
		     			"""),format.raw/*903.11*/("""location.href = "/";
		     		"""),format.raw/*904.10*/("""}"""),format.raw/*904.11*/(""");
	     		"""),format.raw/*905.9*/("""}"""),format.raw/*905.10*/("""
	     		"""),format.raw/*906.9*/("""if(respuesta.status)"""),format.raw/*906.29*/("""{"""),format.raw/*906.30*/("""
	     			"""),format.raw/*907.10*/("""let nroDecimal = mapDec.get(parseFloat(id_moneda));
	     			let precioVenta = $("#precioVenta").val().replace(/,/g,'');
	     			let precioArriendo = $("#precioArriendo").val().replace(/,/g,'');
	     			let precioMinimo = $("#precioMinimo").val().replace(/,/g,'');
	     			let permanenciaMinima = $("#permanenciaMinima").val().replace(/,/g,'');
	     			if(precioVenta=="") precioVenta = 0;
	     			if(precioArriendo=="") precioArriendo = 0;
	     			if(precioMinimo=="") precioMinimo = 0;
	     			if(permanenciaMinima=="") permanenciaMinima = 0;
	     			let tasa = parseFloat(precioArriendo)/parseFloat(precioVenta);
	     			if(parseFloat(precioVenta)==0) tasa = 0;
	     			$("#precioVenta").val(formatStandar(parseFloat(precioVenta),nroDecimal));
	     			$("#precioArriendo").val(formatStandar(parseFloat(precioArriendo),nroDecimal));
	     			$("#precioMinimo").val(formatStandar(parseFloat(precioMinimo),nroDecimal));
	     			$("#permanenciaMinima").val(formatStandar(parseFloat(permanenciaMinima),0));
	     			$("#precioTasa").val(formatPorcentaje(parseFloat(tasa*100)));
	     		"""),format.raw/*923.9*/("""}"""),format.raw/*923.10*/("""
	     	"""),format.raw/*924.8*/("""}"""),format.raw/*924.9*/("""
        """),format.raw/*925.9*/("""}"""),format.raw/*925.10*/(""");
	"""),format.raw/*926.2*/("""}"""),format.raw/*926.3*/("""
	
	
	"""),format.raw/*929.2*/("""const grabaNuevoEquipo = () =>"""),format.raw/*929.32*/("""{"""),format.raw/*929.33*/("""
		
		"""),format.raw/*931.3*/("""nuevoEquipoCodigo = $("#nuevoEquipoCodigo").val().trim();
		nuevoEquipoNombre = $("#nuevoEquipoNombre").val().trim();
		if(nuevoEquipoCodigo=="")"""),format.raw/*933.28*/("""{"""),format.raw/*933.29*/("""
			"""),format.raw/*934.4*/("""alertify.alert("Debe ingresar un código de equipo", function () """),format.raw/*934.68*/("""{"""),format.raw/*934.69*/("""
 				"""),format.raw/*935.6*/("""$("#nuevoEquipoCodigo").focus();
     		"""),format.raw/*936.8*/("""}"""),format.raw/*936.9*/(""");
		"""),format.raw/*937.3*/("""}"""),format.raw/*937.4*/("""else if(nuevoEquipoNombre=="")"""),format.raw/*937.34*/("""{"""),format.raw/*937.35*/("""
			"""),format.raw/*938.4*/("""alertify.alert("Debe ingresar un nombre de equipo", function () """),format.raw/*938.68*/("""{"""),format.raw/*938.69*/("""
 				"""),format.raw/*939.6*/("""$("#nuevoEquipoNombre").focus();
     		"""),format.raw/*940.8*/("""}"""),format.raw/*940.9*/(""");
		"""),format.raw/*941.3*/("""}"""),format.raw/*941.4*/("""else"""),format.raw/*941.8*/("""{"""),format.raw/*941.9*/("""
			"""),format.raw/*942.4*/("""$("#modalNuevoEquipo").modal('hide');
			let auxOpcion = document.getElementById("nuevoEquipoId_unidad");
			let auxEquipoUnidad = auxOpcion.options[auxOpcion.selectedIndex].text;
			
			var formData = new FormData();
			formData.append('id_grupo',$("#nuevoEquipoId_grupo").val());
			formData.append('codigo',$("#nuevoEquipoCodigo").val());
			formData.append('nombre',$("#nuevoEquipoNombre").val());
			formData.append('id_fabrica',$("#nuevoEquipoId_fabrica").val());
			formData.append('id_unidad',$("#nuevoEquipoId_unidad").val());
			formData.append('desdeMenu',"COMPRA");
			formData.append('kg',$("#nuevoEquipoId_kg").val().replace(/,/g,''));
			formData.append('m2',$("#nuevoEquipoId_m2").val().replace(/,/g,''));
			formData.append('id_propiedad',$("#id_propiedad").val());
			
			$.ajax("""),format.raw/*957.11*/("""{"""),format.raw/*957.12*/("""
	            """),format.raw/*958.14*/("""url: "/nuevoEquipoAjax/",
	            type: "POST",
	            method: "POST",
	            data: formData,
	            cache: false,
	            contentType: false,
		     	processData: false,
		     	success: function(respuesta)"""),format.raw/*965.37*/("""{"""),format.raw/*965.38*/("""
		     		"""),format.raw/*966.10*/("""if(respuesta=="existe")"""),format.raw/*966.33*/("""{"""),format.raw/*966.34*/("""
		     			"""),format.raw/*967.11*/("""alertify.alert("El código de equipo ya existe, intente con otro", function () """),format.raw/*967.89*/("""{"""),format.raw/*967.90*/("""
		     				"""),format.raw/*968.12*/("""$("#nuevoEquipoCodigo").val("");
			     		"""),format.raw/*969.11*/("""}"""),format.raw/*969.12*/(""");
		     		"""),format.raw/*970.10*/("""}"""),format.raw/*970.11*/("""else if(respuesta=="error")"""),format.raw/*970.38*/("""{"""),format.raw/*970.39*/("""
		     			"""),format.raw/*971.11*/("""alertify.alert(msgError, function () """),format.raw/*971.48*/("""{"""),format.raw/*971.49*/("""
			     			"""),format.raw/*972.12*/("""location.href = "/";
			     		"""),format.raw/*973.11*/("""}"""),format.raw/*973.12*/(""");
		     		"""),format.raw/*974.10*/("""}"""),format.raw/*974.11*/("""else"""),format.raw/*974.15*/("""{"""),format.raw/*974.16*/("""
		     			
		     			"""),format.raw/*976.11*/("""let rs = respuesta.split("_");
		     			auxCodEquip = $("#nuevoEquipoCodigo").val();
		     			auxNomEquip = $("#nuevoEquipoNombre").val();
		     			auxKgEquip = rs[1];
		     			auxM2Equip = rs[2];
		     			auxUnEquip = auxEquipoUnidad;
		     			selectEquipo(rs[0]);
		     			
		     			$("#nuevoEquipoCodigo").val("");
		     			$("#nuevoEquipoNombre").val("")
		     		"""),format.raw/*986.10*/("""}"""),format.raw/*986.11*/("""
		     	"""),format.raw/*987.9*/("""}"""),format.raw/*987.10*/("""
	        """),format.raw/*988.10*/("""}"""),format.raw/*988.11*/(""");
		"""),format.raw/*989.3*/("""}"""),format.raw/*989.4*/("""
	"""),format.raw/*990.2*/("""}"""),format.raw/*990.3*/("""
	
	"""),format.raw/*992.2*/("""const verificaCodigo = (codigo) => """),format.raw/*992.37*/("""{"""),format.raw/*992.38*/("""
		"""),format.raw/*993.3*/("""var formData = new FormData();
	  	formData.append('codigo',codigo);
        $.ajax("""),format.raw/*995.16*/("""{"""),format.raw/*995.17*/("""
            """),format.raw/*996.13*/("""url: "/verificaCodigoEquipoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*1003.36*/("""{"""),format.raw/*1003.37*/("""
	     		"""),format.raw/*1004.9*/("""if(respuesta=="existe")"""),format.raw/*1004.32*/("""{"""),format.raw/*1004.33*/("""
	     			"""),format.raw/*1005.10*/("""alertify.alert("El código de equipo ya existe, intente con otro", function () """),format.raw/*1005.88*/("""{"""),format.raw/*1005.89*/("""
	     				"""),format.raw/*1006.11*/("""$("#nuevoEquipoCodigo").val("");
		     		"""),format.raw/*1007.10*/("""}"""),format.raw/*1007.11*/(""");
	     		"""),format.raw/*1008.9*/("""}"""),format.raw/*1008.10*/("""
	     		"""),format.raw/*1009.9*/("""if(respuesta=="error")"""),format.raw/*1009.31*/("""{"""),format.raw/*1009.32*/("""
	     			"""),format.raw/*1010.10*/("""alertify.alert(msgError, function () """),format.raw/*1010.47*/("""{"""),format.raw/*1010.48*/("""
		     			"""),format.raw/*1011.11*/("""location.href = "/";
		     		"""),format.raw/*1012.10*/("""}"""),format.raw/*1012.11*/(""");
	     		"""),format.raw/*1013.9*/("""}"""),format.raw/*1013.10*/("""
	     	"""),format.raw/*1014.8*/("""}"""),format.raw/*1014.9*/("""
        """),format.raw/*1015.9*/("""}"""),format.raw/*1015.10*/(""");
	"""),format.raw/*1016.2*/("""}"""),format.raw/*1016.3*/("""
	
	"""),format.raw/*1018.2*/("""let extArray = new Array(".gif", ".jpg", ".png", ".jpeg", ".pdf", ".xls", ".doc", ".xlsx", ".docx", ".tar", ".zip", ".rar");
	const LimitAttach = (form, file) => """),format.raw/*1019.38*/("""{"""),format.raw/*1019.39*/("""
		"""),format.raw/*1020.3*/("""let allowSubmit = false;
		if (!file) return;
		while (file.indexOf("\\") != -1)"""),format.raw/*1022.35*/("""{"""),format.raw/*1022.36*/("""
			"""),format.raw/*1023.4*/("""file = file.slice(file.indexOf("\\") + 1);
		"""),format.raw/*1024.3*/("""}"""),format.raw/*1024.4*/("""
		"""),format.raw/*1025.3*/("""let extencion = file.slice(file.lastIndexOf(".")).toLowerCase();
		for (var i = 0; i < extArray.length; i++) """),format.raw/*1026.45*/("""{"""),format.raw/*1026.46*/("""
			"""),format.raw/*1027.4*/("""if (extArray[i] == extencion) """),format.raw/*1027.34*/("""{"""),format.raw/*1027.35*/(""" """),format.raw/*1027.36*/("""allowSubmit = true; break; """),format.raw/*1027.63*/("""}"""),format.raw/*1027.64*/("""
		"""),format.raw/*1028.3*/("""}"""),format.raw/*1028.4*/("""
		"""),format.raw/*1029.3*/("""if (allowSubmit) """),format.raw/*1029.20*/("""{"""),format.raw/*1029.21*/("""
			"""),format.raw/*1030.4*/("""var file = $("#docAdjunto")[0].files[0];
			var fileSize = file.size; //tamaño del archivo
			if(fileSize>100000000)"""),format.raw/*1032.26*/("""{"""),format.raw/*1032.27*/("""
				"""),format.raw/*1033.5*/("""alert("Se permiten únicamente archivos que no superen los 100 MB,"
				+" el que se intenta subir pesa: "+Math.round(file.size/10)/100+" KB");
				form.docAdjunto.value="";
			"""),format.raw/*1036.4*/("""}"""),format.raw/*1036.5*/("""
		"""),format.raw/*1037.3*/("""}"""),format.raw/*1037.4*/("""else"""),format.raw/*1037.8*/("""{"""),format.raw/*1037.9*/("""
			"""),format.raw/*1038.4*/("""alert("Se permiten únicamente archivos con la extención: " 
			+ (extArray.join("  ")) + "\nPor favor, seleccione otro archivo "
			+ "e intente de nuevo.");
			form.docAdjunto.value="";
		"""),format.raw/*1042.3*/("""}"""),format.raw/*1042.4*/("""
	"""),format.raw/*1043.2*/("""}"""),format.raw/*1043.3*/("""
	
	"""),format.raw/*1045.2*/("""let extArray2 = new Array(".xls", ".xlsx");
	function subirArchivo(form, file, nodo) """),format.raw/*1046.42*/("""{"""),format.raw/*1046.43*/("""
		"""),format.raw/*1047.3*/("""allowSubmit = false;
		if (!file) return;
		while (file.indexOf("\\") != -1)
			file = file.slice(file.indexOf("\\") + 1);
		ext = file.slice(file.lastIndexOf(".")).toLowerCase();
		for (var i = 0; i < extArray2.length; i++) """),format.raw/*1052.46*/("""{"""),format.raw/*1052.47*/("""
			"""),format.raw/*1053.4*/("""if (extArray2[i] == ext) """),format.raw/*1053.29*/("""{"""),format.raw/*1053.30*/(""" """),format.raw/*1053.31*/("""allowSubmit = true; break; """),format.raw/*1053.58*/("""}"""),format.raw/*1053.59*/("""
		"""),format.raw/*1054.3*/("""}"""),format.raw/*1054.4*/("""
		"""),format.raw/*1055.3*/("""if (allowSubmit) """),format.raw/*1055.20*/("""{"""),format.raw/*1055.21*/("""
			"""),format.raw/*1056.4*/("""var formData = new FormData();
			formData.append("file",nodo.files[0]);
	        $.ajax("""),format.raw/*1058.17*/("""{"""),format.raw/*1058.18*/("""
				"""),format.raw/*1059.5*/("""url: "/routes2/compraValidarPlantilla/",
	            type: "POST",
	            method: "POST",
	            data: formData,
	            cache: false,
	            contentType: false,
		     	processData: false,
		     	success: function(rs)"""),format.raw/*1066.30*/("""{"""),format.raw/*1066.31*/("""
					"""),format.raw/*1067.6*/("""$('#modalCargaMasiva').modal('hide');
					if(rs[0] == 'true')"""),format.raw/*1068.25*/("""{"""),format.raw/*1068.26*/("""
						"""),format.raw/*1069.7*/("""$("#compraCargaPlantilla").submit();
					"""),format.raw/*1070.6*/("""}"""),format.raw/*1070.7*/("""else if(rs[0].substring(0,7) == "EQUIPOS")"""),format.raw/*1070.49*/("""{"""),format.raw/*1070.50*/("""
						"""),format.raw/*1071.7*/("""rsGlobal = rs;
						$("#msgNuevosCodigos").modal('show');
					"""),format.raw/*1073.6*/("""}"""),format.raw/*1073.7*/("""else if(rs[0].substring(0,4) == "ERR1" || rs[0].substring(0,4) == "ERR2" || rs[0].substring(0,4) == "ERR3")"""),format.raw/*1073.114*/("""{"""),format.raw/*1073.115*/("""
						"""),format.raw/*1074.7*/("""alertify.alert(rs[0], function () """),format.raw/*1074.41*/("""{"""),format.raw/*1074.42*/(""" 
							"""),format.raw/*1075.8*/("""location.reload();
						"""),format.raw/*1076.7*/("""}"""),format.raw/*1076.8*/(""");
					"""),format.raw/*1077.6*/("""}"""),format.raw/*1077.7*/("""else"""),format.raw/*1077.11*/("""{"""),format.raw/*1077.12*/("""
						"""),format.raw/*1078.7*/("""alertify.alert("SE PRESENTO UN PROBLEMA", function () """),format.raw/*1078.61*/("""{"""),format.raw/*1078.62*/(""" 
							"""),format.raw/*1079.8*/("""location.reload();
						"""),format.raw/*1080.7*/("""}"""),format.raw/*1080.8*/(""");
					"""),format.raw/*1081.6*/("""}"""),format.raw/*1081.7*/("""
		     	"""),format.raw/*1082.9*/("""}"""),format.raw/*1082.10*/("""
	        """),format.raw/*1083.10*/("""}"""),format.raw/*1083.11*/(""");
		"""),format.raw/*1084.3*/("""}"""),format.raw/*1084.4*/("""else"""),format.raw/*1084.8*/("""{"""),format.raw/*1084.9*/("""
			"""),format.raw/*1085.4*/("""alertify.alert("Se permiten &uacute;nicamente archivos con la extenci&oacute;n: " 
			+ (extArray2.join("  ")) + "\nPor favor, seleccione otro archivo "
			+ "e intente de nuevo.");
			form.archivoXLSX.value="";
		"""),format.raw/*1089.3*/("""}"""),format.raw/*1089.4*/("""
	"""),format.raw/*1090.2*/("""}"""),format.raw/*1090.3*/("""
	
	"""),format.raw/*1092.2*/("""const revisarEquiposNuevos = () =>"""),format.raw/*1092.36*/("""{"""),format.raw/*1092.37*/("""
		"""),format.raw/*1093.3*/("""$('#msgNuevosCodigos').modal('hide');
		let mensaje = "";
		for(var i in rsGlobal)"""),format.raw/*1095.25*/("""{"""),format.raw/*1095.26*/("""
			"""),format.raw/*1096.4*/("""mensaje += rsGlobal[i];
		"""),format.raw/*1097.3*/("""}"""),format.raw/*1097.4*/("""
		
		"""),format.raw/*1099.3*/("""document.getElementById("listaRevisarEquiposNuevos").innerHTML =  mensaje;
		$("#modalRevisarEquiposNuevos").modal('show');
		
	"""),format.raw/*1102.2*/("""}"""),format.raw/*1102.3*/("""
	
	"""),format.raw/*1104.2*/("""const validarForm2 = () =>"""),format.raw/*1104.28*/("""{"""),format.raw/*1104.29*/("""
		"""),format.raw/*1105.3*/("""$("#btnSubmit2").attr('disabled',true);
		return(true);
	"""),format.raw/*1107.2*/("""}"""),format.raw/*1107.3*/("""

	
"""),format.raw/*1110.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listProveedor:List[tables.Proveedor],listEquipo:List[tables.Equipo],listMoneda:List[tables.Moneda],listBodegas:List[List[String]],listMon:List[tables.Moneda],listGrupos:List[tables.Grupo],listFabrica:List[tables.Fabrica],listUnidades:List[tables.Unidad],listRegiones:List[tables.Regiones],listSucursales:List[tables.Sucursal],listPropiedad:List[tables.Propiedad]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listProveedor,listEquipo,listMoneda,listBodegas,listMon,listGrupos,listFabrica,listUnidades,listRegiones,listSucursales,listPropiedad)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Proveedor],List[tables.Equipo],List[tables.Moneda],List[List[String]],List[tables.Moneda],List[tables.Grupo],List[tables.Fabrica],List[tables.Unidad],List[tables.Regiones],List[tables.Sucursal],List[tables.Propiedad]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listProveedor,listEquipo,listMoneda,listBodegas,listMon,listGrupos,listFabrica,listUnidades,listRegiones,listSucursales,listPropiedad) => apply(mapDiccionario,mapPermiso,userMnu,listProveedor,listEquipo,listMoneda,listBodegas,listMon,listGrupos,listFabrica,listUnidades,listRegiones,listSucursales,listPropiedad)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCompras/compraIngreso.scala.html
                  HASH: 1c1b5dd86f503f5a01a2cc954ccb2424613e098a
                  MATRIX: 1242->1|1817->483|1850->491|1866->499|1905->501|1933->504|2001->552|2032->556|2091->589|2161->638|2191->641|2275->697|2304->698|2336->703|3242->1582|3270->1583|3300->1586|3537->1797|3645->1884|3676->1888|4130->2315|4153->2329|4185->2340|4214->2341|13317->11416|13341->11430|13374->11441|13503->11543|13548->11571|13588->11572|13625->11581|13690->11618|13706->11624|13736->11632|13792->11660|13808->11666|13840->11675|13900->11706|13917->11712|13954->11726|14049->11793|14065->11799|14096->11808|14170->11854|14186->11860|14222->11874|14296->11920|14312->11926|14346->11938|14407->11967|14442->11974|15920->13425|15962->13450|16002->13451|16039->13460|16094->13487|16110->13493|16144->13505|16189->13522|16205->13528|16239->13540|16284->13556|16301->13562|16332->13570|16377->13586|16394->13592|16425->13600|16470->13616|16487->13622|16522->13634|16568->13651|16585->13657|16616->13665|16709->13730|16725->13736|16758->13747|16832->13793|16848->13799|16882->13811|16956->13857|16972->13863|17006->13875|17080->13921|17096->13927|17131->13940|17192->13969|17227->13976|18327->15048|18368->15072|18408->15073|18448->15084|18492->15100|18507->15105|18537->15113|18568->15116|18583->15121|18617->15133|18669->15153|18706->15162|18986->15414|19009->15427|19046->15442|19077->15445|19100->15458|19141->15477|19189->15497|19233->15524|19273->15525|19313->15536|19357->15552|19372->15557|19402->15565|19433->15568|19448->15573|19482->15585|19534->15605|19571->15614|20729->16744|20771->16769|20811->16770|20851->16781|20895->16797|20910->16802|20935->16805|20966->16808|20981->16813|21012->16822|21064->16842|21101->16851|21403->17125|21446->17151|21486->17152|21526->17163|21570->17179|21585->17184|21610->17187|21641->17190|21656->17195|21685->17202|21737->17222|21774->17231|24881->20307|24914->20312|25006->20375|25036->20376|25070->20382|25137->20420|25167->20421|25202->20428|25374->20571|25404->20572|25444->20583|25550->20660|25580->20661|25613->20666|25642->20667|25671->20668|25742->20710|25772->20711|25807->20718|25979->20861|26009->20862|26049->20873|26155->20950|26185->20951|26218->20956|26247->20957|26276->20958|26382->21036|26411->21037|26489->21086|26519->21087|26550->21090|26645->21156|26675->21157|26707->21161|26804->21229|26834->21230|26868->21236|26929->21269|26958->21270|26991->21275|27020->21276|27052->21280|27081->21281|27113->21285|27285->21428|27315->21429|27358->21443|27632->21688|27662->21689|27701->21699|27753->21722|27783->21723|27823->21734|27954->21835|27985->21836|28026->21848|28094->21887|28124->21888|28165->21900|28195->21901|28234->21911|28285->21933|28315->21934|28355->21945|28421->21982|28451->21983|28492->21995|28552->22026|28582->22027|28623->22039|28653->22040|28690->22049|28720->22050|28759->22060|28789->22061|28822->22066|28851->22067|28885->22073|28914->22074|28946->22078|28999->22104|29037->22125|29077->22126|29108->22129|29148->22141|29163->22146|29193->22154|29223->22156|29238->22161|29281->22182|29317->22187|29352->22194|29546->22359|29576->22360|29607->22363|31093->23822|31132->23844|31172->23845|31208->23853|31260->23877|31273->23880|31303->23888|31334->23891|31347->23894|31383->23908|31430->23924|31465->23931|32415->24853|32460->24881|32500->24882|32543->24896|32606->24931|32621->24936|32651->24944|32683->24948|32698->24953|32732->24965|32789->24990|32836->25008|33491->25636|33531->25659|33571->25660|33607->25668|33659->25692|33672->25695|33701->25702|33732->25705|33745->25708|33774->25715|33821->25731|33856->25738|34433->26287|34462->26288|34494->26292|34553->26322|34583->26323|34614->26326|34724->26408|34753->26409|34785->26413|34843->26442|34873->26443|34904->26446|35707->27221|35736->27222|35768->27226|35823->27252|35853->27253|35885->27257|35961->27304|35991->27305|36024->27310|36125->27383|36154->27384|36277->27478|36307->27479|36340->27484|36442->27558|36471->27559|36588->27647|36618->27648|36651->27653|36753->27727|36782->27728|36896->27813|36926->27814|36959->27819|37061->27893|37090->27894|37165->27941|37194->27942|37228->27948|37282->27973|37312->27974|37343->27977|37424->28029|37454->28030|37486->28034|37590->28109|37620->28110|37653->28115|37702->28136|37731->28137|37764->28142|37793->28143|37825->28147|37854->28148|37886->28152|38140->28377|38170->28378|38208->28388|38287->28438|38317->28439|38351->28445|38422->28487|38452->28488|38487->28495|38534->28514|38563->28515|38596->28520|38625->28521|38674->28541|38704->28542|38738->28548|38784->28566|38813->28567|38846->28571|38876->28572|38910->28578|39032->28671|39063->28672|39098->28679|39150->28702|39180->28703|39215->28710|39244->28711|39276->28715|39305->28716|39337->28720|39367->28721|39400->28726|39533->28830|39563->28831|39597->28837|39647->28859|39677->28860|39711->28866|39740->28867|39771->28870|39800->28871|39831->28874|39874->28889|39903->28890|39935->28894|40011->28941|40041->28942|40072->28945|40173->29017|40203->29018|40235->29022|40404->29162|40434->29163|40477->29177|40736->29407|40766->29408|40800->29414|40836->29421|40866->29422|40911->29438|41201->29699|41231->29700|41267->29708|41413->29825|41443->29826|41484->29838|41514->29839|41553->29850|41583->29851|41622->29861|41652->29862|41685->29867|41714->29868|41746->29872|41775->29873|41807->29877|41903->29944|41933->29945|41970->29954|42000->29955|42033->29960|42062->29961|42092->29963|42121->29964|42153->29968|42244->30030|42274->30031|42305->30034|42391->30091|42421->30092|42453->30096|42623->30237|42653->30238|42696->30252|42972->30499|43002->30500|43041->30510|43260->30701|43290->30702|43329->30712|43359->30713|43392->30718|43421->30719|43451->30721|43480->30722|43516->30730|43603->30788|43633->30789|43664->30792|43819->30918|43849->30919|43891->30932|44161->31173|44191->31174|44228->31183|44332->31259|44361->31260|44398->31269|44428->31270|44460->31274|44489->31275|44521->31279|44612->31341|44642->31342|44673->31345|44984->31627|45014->31628|45056->31641|45317->31873|45347->31874|45384->31883|45435->31905|45465->31906|45504->31916|45570->31953|45600->31954|45640->31965|45699->31995|45729->31996|45768->32007|45798->32008|45835->32017|45884->32037|45914->32038|45953->32048|47077->33144|47107->33145|47143->33153|47172->33154|47209->33163|47239->33164|47271->33168|47300->33169|47334->33175|47393->33205|47423->33206|47457->33212|47631->33357|47661->33358|47693->33362|47786->33426|47816->33427|47850->33433|47918->33473|47947->33474|47980->33479|48009->33480|48068->33510|48098->33511|48130->33515|48223->33579|48253->33580|48287->33586|48355->33626|48384->33627|48417->33632|48446->33633|48478->33637|48507->33638|48539->33642|49365->34439|49395->34440|49438->34454|49702->34689|49732->34690|49771->34700|49823->34723|49853->34724|49893->34735|50000->34813|50030->34814|50071->34826|50143->34869|50173->34870|50214->34882|50244->34883|50300->34910|50330->34911|50370->34922|50436->34959|50466->34960|50507->34972|50567->35003|50597->35004|50638->35016|50668->35017|50701->35021|50731->35022|50782->35044|51188->35421|51218->35422|51255->35431|51285->35432|51324->35442|51354->35443|51387->35448|51416->35449|51446->35451|51475->35452|51507->35456|51571->35491|51601->35492|51632->35495|51745->35579|51775->35580|51817->35593|52084->35830|52115->35831|52153->35840|52206->35863|52237->35864|52277->35874|52385->35952|52416->35953|52457->35964|52529->36006|52560->36007|52600->36018|52631->36019|52669->36028|52721->36050|52752->36051|52792->36061|52859->36098|52890->36099|52931->36110|52991->36140|53022->36141|53062->36152|53093->36153|53130->36161|53160->36162|53198->36171|53229->36172|53262->36176|53292->36177|53325->36181|53517->36343|53548->36344|53580->36347|53690->36427|53721->36428|53754->36432|53828->36477|53858->36478|53890->36481|54029->36590|54060->36591|54093->36595|54153->36625|54184->36626|54215->36627|54272->36654|54303->36655|54335->36658|54365->36659|54397->36662|54444->36679|54475->36680|54508->36684|54654->36800|54685->36801|54719->36806|54924->36982|54954->36983|54986->36986|55016->36987|55049->36991|55079->36992|55112->36996|55330->37185|55360->37186|55391->37188|55421->37189|55454->37193|55569->37278|55600->37279|55632->37282|55887->37507|55918->37508|55951->37512|56006->37537|56037->37538|56068->37539|56125->37566|56156->37567|56188->37570|56218->37571|56250->37574|56297->37591|56328->37592|56361->37596|56480->37685|56511->37686|56545->37691|56818->37934|56849->37935|56884->37941|56976->38003|57007->38004|57043->38011|57114->38053|57144->38054|57216->38096|57247->38097|57283->38104|57376->38168|57406->38169|57544->38276|57576->38277|57612->38284|57676->38318|57707->38319|57745->38328|57799->38353|57829->38354|57866->38362|57896->38363|57930->38367|57961->38368|57997->38375|58081->38429|58112->38430|58150->38439|58204->38464|58234->38465|58271->38473|58301->38474|58339->38483|58370->38484|58410->38494|58441->38495|58475->38500|58505->38501|58538->38505|58568->38506|58601->38510|58844->38724|58874->38725|58905->38727|58935->38728|58968->38732|59032->38766|59063->38767|59095->38770|59207->38852|59238->38853|59271->38857|59326->38883|59356->38884|59391->38890|59548->39018|59578->39019|59611->39023|59667->39049|59698->39050|59730->39053|59816->39110|59846->39111|59879->39115
                  LINES: 28->1|36->5|38->7|38->7|38->7|40->9|40->9|43->12|44->13|44->13|45->14|46->15|46->15|47->16|63->32|63->32|64->33|70->39|70->39|71->40|79->48|79->48|79->48|79->48|327->296|327->296|327->296|333->302|333->302|333->302|334->303|334->303|334->303|334->303|334->303|334->303|334->303|334->303|334->303|334->303|335->304|335->304|335->304|336->305|336->305|336->305|337->306|337->306|337->306|339->308|340->309|375->344|375->344|375->344|376->345|376->345|376->345|376->345|376->345|376->345|376->345|376->345|376->345|376->345|376->345|376->345|376->345|376->345|376->345|376->345|376->345|376->345|376->345|377->346|377->346|377->346|378->347|378->347|378->347|379->348|379->348|379->348|380->349|380->349|380->349|382->351|383->352|415->384|415->384|415->384|416->385|416->385|416->385|416->385|416->385|416->385|416->385|417->386|418->387|427->396|427->396|427->396|427->396|427->396|427->396|428->397|428->397|428->397|429->398|429->398|429->398|429->398|429->398|429->398|429->398|430->399|431->400|463->432|463->432|463->432|464->433|464->433|464->433|464->433|464->433|464->433|464->433|465->434|466->435|475->444|475->444|475->444|476->445|476->445|476->445|476->445|476->445|476->445|476->445|477->446|478->447|565->534|570->539|572->541|572->541|574->543|574->543|574->543|575->544|578->547|578->547|579->548|580->549|580->549|581->550|581->550|581->550|583->552|583->552|584->553|587->556|587->556|588->557|589->558|589->558|590->559|590->559|590->559|593->562|593->562|595->564|595->564|596->565|597->566|597->566|598->567|598->567|598->567|599->568|600->569|600->569|601->570|601->570|601->570|601->570|602->571|605->574|605->574|606->575|613->582|613->582|614->583|614->583|614->583|615->584|615->584|615->584|616->585|617->586|617->586|618->587|618->587|619->588|619->588|619->588|620->589|620->589|620->589|621->590|622->591|622->591|623->592|623->592|624->593|624->593|625->594|625->594|626->595|626->595|628->597|628->597|630->599|631->600|631->600|631->600|632->601|632->601|632->601|632->601|632->601|632->601|632->601|633->602|637->606|644->613|644->613|645->614|684->653|684->653|684->653|685->654|685->654|685->654|685->654|685->654|685->654|685->654|686->655|687->656|710->679|710->679|710->679|711->680|711->680|711->680|711->680|711->680|711->680|711->680|712->681|713->682|729->698|729->698|729->698|730->699|730->699|730->699|730->699|730->699|730->699|730->699|731->700|732->701|747->716|747->716|749->718|749->718|749->718|750->719|753->722|753->722|755->724|755->724|755->724|756->725|771->740|771->740|773->742|773->742|773->742|774->743|775->744|775->744|776->745|778->747|778->747|780->749|780->749|781->750|783->752|783->752|785->754|785->754|786->755|788->757|788->757|790->759|790->759|791->760|793->762|793->762|794->763|794->763|797->766|797->766|797->766|798->767|799->768|799->768|800->769|801->770|801->770|802->771|803->772|803->772|804->773|804->773|804->773|804->773|805->774|808->777|808->777|810->779|811->780|811->780|812->781|813->782|813->782|814->783|815->784|815->784|816->785|816->785|818->787|818->787|819->788|820->789|820->789|820->789|820->789|821->790|821->790|821->790|822->791|823->792|823->792|824->793|824->793|825->794|825->794|825->794|825->794|826->795|827->796|827->796|828->797|829->798|829->798|830->799|830->799|831->800|831->800|832->801|833->802|833->802|835->804|835->804|835->804|836->805|837->806|837->806|838->807|842->811|842->811|843->812|850->819|850->819|851->820|851->820|851->820|852->821|859->828|859->828|860->829|862->831|862->831|863->832|863->832|864->833|864->833|865->834|865->834|866->835|866->835|866->835|866->835|867->836|867->836|867->836|868->837|868->837|869->838|869->838|870->839|870->839|872->841|872->841|872->841|873->842|874->843|874->843|875->844|878->847|878->847|879->848|886->855|886->855|887->856|890->859|890->859|891->860|891->860|892->861|892->861|893->862|893->862|897->866|897->866|897->866|898->867|901->870|901->870|902->871|909->878|909->878|910->879|911->880|911->880|912->881|912->881|913->882|913->882|915->884|915->884|915->884|916->885|923->892|923->892|924->893|931->900|931->900|932->901|932->901|932->901|933->902|933->902|933->902|934->903|935->904|935->904|936->905|936->905|937->906|937->906|937->906|938->907|954->923|954->923|955->924|955->924|956->925|956->925|957->926|957->926|960->929|960->929|960->929|962->931|964->933|964->933|965->934|965->934|965->934|966->935|967->936|967->936|968->937|968->937|968->937|968->937|969->938|969->938|969->938|970->939|971->940|971->940|972->941|972->941|972->941|972->941|973->942|988->957|988->957|989->958|996->965|996->965|997->966|997->966|997->966|998->967|998->967|998->967|999->968|1000->969|1000->969|1001->970|1001->970|1001->970|1001->970|1002->971|1002->971|1002->971|1003->972|1004->973|1004->973|1005->974|1005->974|1005->974|1005->974|1007->976|1017->986|1017->986|1018->987|1018->987|1019->988|1019->988|1020->989|1020->989|1021->990|1021->990|1023->992|1023->992|1023->992|1024->993|1026->995|1026->995|1027->996|1034->1003|1034->1003|1035->1004|1035->1004|1035->1004|1036->1005|1036->1005|1036->1005|1037->1006|1038->1007|1038->1007|1039->1008|1039->1008|1040->1009|1040->1009|1040->1009|1041->1010|1041->1010|1041->1010|1042->1011|1043->1012|1043->1012|1044->1013|1044->1013|1045->1014|1045->1014|1046->1015|1046->1015|1047->1016|1047->1016|1049->1018|1050->1019|1050->1019|1051->1020|1053->1022|1053->1022|1054->1023|1055->1024|1055->1024|1056->1025|1057->1026|1057->1026|1058->1027|1058->1027|1058->1027|1058->1027|1058->1027|1058->1027|1059->1028|1059->1028|1060->1029|1060->1029|1060->1029|1061->1030|1063->1032|1063->1032|1064->1033|1067->1036|1067->1036|1068->1037|1068->1037|1068->1037|1068->1037|1069->1038|1073->1042|1073->1042|1074->1043|1074->1043|1076->1045|1077->1046|1077->1046|1078->1047|1083->1052|1083->1052|1084->1053|1084->1053|1084->1053|1084->1053|1084->1053|1084->1053|1085->1054|1085->1054|1086->1055|1086->1055|1086->1055|1087->1056|1089->1058|1089->1058|1090->1059|1097->1066|1097->1066|1098->1067|1099->1068|1099->1068|1100->1069|1101->1070|1101->1070|1101->1070|1101->1070|1102->1071|1104->1073|1104->1073|1104->1073|1104->1073|1105->1074|1105->1074|1105->1074|1106->1075|1107->1076|1107->1076|1108->1077|1108->1077|1108->1077|1108->1077|1109->1078|1109->1078|1109->1078|1110->1079|1111->1080|1111->1080|1112->1081|1112->1081|1113->1082|1113->1082|1114->1083|1114->1083|1115->1084|1115->1084|1115->1084|1115->1084|1116->1085|1120->1089|1120->1089|1121->1090|1121->1090|1123->1092|1123->1092|1123->1092|1124->1093|1126->1095|1126->1095|1127->1096|1128->1097|1128->1097|1130->1099|1133->1102|1133->1102|1135->1104|1135->1104|1135->1104|1136->1105|1138->1107|1138->1107|1141->1110
                  -- GENERATED --
              */
          