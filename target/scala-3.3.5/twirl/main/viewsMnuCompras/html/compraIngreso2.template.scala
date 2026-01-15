
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

object compraIngreso2 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template16[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Proveedor],List[tables.Equipo],List[tables.Moneda],List[List[String]],List[tables.Moneda],List[tables.Grupo],List[tables.Fabrica],List[tables.Unidad],List[tables.Regiones],List[tables.Sucursal],List[List[String]],tables.Proveedor,List[tables.Propiedad],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listProveedor: List[tables.Proveedor], listEquipo: List[tables.Equipo], listMoneda: List[tables.Moneda], listBodegas: List[List[String]], listMon: List[tables.Moneda],
listGrupos: List[tables.Grupo], listFabrica: List[tables.Fabrica], listUnidades: List[tables.Unidad], listRegiones: List[tables.Regiones],
listSucursales: List[tables.Sucursal], lista: List[List[String]], proveedor: tables.Proveedor, listPropiedad: List[tables.Propiedad]):play.twirl.api.HtmlFormat.Appendable = {
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
								  		<input type="hidden" id="id_proveedor" name="id_proveedor" value=""""),_display_(/*52.80*/proveedor/*52.89*/.getId()),format.raw/*52.97*/("""">
	  									<input type="text" class="form-control"
	  										id="rutProveedor"
	  										value=""""),_display_(/*55.22*/proveedor/*55.31*/.getRut()),format.raw/*55.40*/("""" 
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
												value=""""),_display_(/*68.21*/proveedor/*68.30*/.getNickName()),format.raw/*68.44*/(""""
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
						
							"""),_display_(/*149.9*/for((l,i) <- lista.zipWithIndex) yield /*149.41*/{_display_(Seq[Any](format.raw/*149.42*/("""
								"""),format.raw/*150.9*/("""<tr>
									<td style="text-align: left;">
										<input type="hidden" name="id_equipo[]" value=""""),_display_(/*152.59*/l/*152.60*/.get(10)),format.raw/*152.68*/("""">
										"""),_display_(/*153.12*/l/*153.13*/.get(1)),format.raw/*153.20*/("""
									"""),format.raw/*154.10*/("""</td>
									<td style="text-align: left;">"""),_display_(/*155.41*/l/*155.42*/.get(2)),format.raw/*155.49*/("""</td>
									<td style="text-align: right;">
										<div id="KG_"""),_display_(/*157.24*/(i+1)),format.raw/*157.29*/("""" align="right">"""),_display_(/*157.46*/l/*157.47*/.get(3)),format.raw/*157.54*/("""</div>
									</td>
									<td style="text-align: right;">
										<div id="M2_"""),_display_(/*160.24*/(i+1)),format.raw/*160.29*/("""" align="right">"""),_display_(/*160.46*/l/*160.47*/.get(4)),format.raw/*160.54*/("""</div>
									</td>
									<td style="text-align: center;">"""),_display_(/*162.43*/l/*162.44*/.get(5)),format.raw/*162.51*/("""</td>
									<td style="text-align: center;">
										<input type="text" class="cantidad form-control right" 
											name="cantidad[]" 
											id="cantidad_"""),_display_(/*166.26*/(i+1)),format.raw/*166.31*/("""" 
											value=""""),_display_(/*167.20*/l/*167.21*/.get(6)),format.raw/*167.28*/("""" 
											onfocus="value=value.replace(/,/g,'');" 
											onkeydown="return ingresoDouble(window.event)" 
											autocomplete="off" 
											onchange="if(value=='') value=0; value=formatStandar(value,2); actualTotales(id);">
									</td>
									<td style="text-align: center;">
										<select class="form-control form-control-sm" 
											id="idMoneda_"""),_display_(/*175.26*/(i+1)),format.raw/*175.31*/("""" 
											name="id_monedaCompra[]">
											<option value=""""),_display_(/*177.28*/l/*177.29*/.get(11)),format.raw/*177.37*/("""">"""),_display_(/*177.40*/l/*177.41*/.get(7)),format.raw/*177.48*/("""</option>
											"""),_display_(/*178.13*/for(mon <- listMoneda) yield /*178.35*/{_display_(Seq[Any](format.raw/*178.36*/("""
								    			"""),format.raw/*179.16*/("""<option value='"""),_display_(/*179.32*/mon/*179.35*/.getId()),format.raw/*179.43*/("""'>"""),_display_(/*179.46*/mon/*179.49*/.getNickName()),format.raw/*179.63*/("""</option>
											""")))}),format.raw/*180.13*/("""
										"""),format.raw/*181.11*/("""</select>
									</td>
									<td style="text-align: center;">
										<input type="text" class="form-control right" 
											name="puCompra[]" 
											id="puCompra_"""),_display_(/*186.26*/(i+1)),format.raw/*186.31*/("""" 
											value=""""),_display_(/*187.20*/l/*187.21*/.get(8)),format.raw/*187.28*/("""" 
											onfocus="value=value.replace(/,/g,'');" 
											onkeydown="return ingresoDouble(window.event)" 
											autocomplete="off" 
											onchange="if(value=='') value=0; value=formatStandar(value,'"""),_display_(/*191.73*/l/*191.74*/.get(12)),format.raw/*191.82*/("""'); actualTotales(id);"> 
									</td>
									<td style="text-align: right;">
										<div class="total" id="total_"""),_display_(/*194.41*/(i+1)),format.raw/*194.46*/("""" align="right">0.00</div>
									</td>
									<td style="text-align: center;">
										<select class="form-control form-control-sm " 
											onchange="actualizaSucursal('"""),_display_(/*198.42*/(i+1)),format.raw/*198.47*/("""','"""),_display_(/*198.51*/l/*198.52*/.get(10)),format.raw/*198.60*/("""',value);">
											<option value=""""),_display_(/*199.28*/l/*199.29*/.get(13)),format.raw/*199.37*/("""">"""),_display_(/*199.40*/l/*199.41*/.get(14)),format.raw/*199.49*/("""</option>
											"""),_display_(/*200.13*/for(lista <- listSucursales) yield /*200.41*/{_display_(Seq[Any](format.raw/*200.42*/("""
												"""),format.raw/*201.13*/("""<option value=""""),_display_(/*201.29*/lista/*201.34*/.getId()),format.raw/*201.42*/("""">"""),_display_(/*201.45*/lista/*201.50*/.getNombre()),format.raw/*201.62*/("""</option>
											""")))}),format.raw/*202.13*/("""
										"""),format.raw/*203.11*/("""</select>
									</td>
									<td style="text-align: center;">
										<input type="hidden" id="id_sucursal_"""),_display_(/*206.49*/(i+1)),format.raw/*206.54*/("""" value=""""),_display_(/*206.64*/l/*206.65*/.get(13)),format.raw/*206.73*/("""">
											<a href="#" onclick="selSucursalPrecio('"""),_display_(/*207.53*/(i+1)),format.raw/*207.58*/("""','"""),_display_(/*207.62*/l/*207.63*/.get(10)),format.raw/*207.71*/("""')"> 
												<kbd style="background-color: #CACFD2">Asignar</kbd>
											</a>
									</td>
									<td style="text-align: center;">
										<div id="selBodegaDestino_"""),_display_(/*212.38*/(i+1)),format.raw/*212.43*/("""">
											<select class="selBodDest form-control form-control-sm" name="id_bodegaDestino[]">
												<option value=""""),_display_(/*214.29*/l/*214.30*/.get(15)),format.raw/*214.38*/("""">"""),_display_(/*214.41*/l/*214.42*/.get(9)),format.raw/*214.49*/("""</option>
												"""),_display_(/*215.14*/for(bod <- listBodegas) yield /*215.37*/{_display_(Seq[Any](format.raw/*215.38*/("""
    												"""),format.raw/*216.17*/("""<option value='"""),_display_(/*216.33*/bod/*216.36*/.get(1)),format.raw/*216.43*/("""'>"""),_display_(/*216.46*/bod/*216.49*/.get(5)),format.raw/*216.56*/("""</option>
												""")))}),format.raw/*217.14*/("""
											"""),format.raw/*218.12*/("""</select>
										</div>
									</td>
									<td style="text-align: right;">
										<div class="kg" id="totKG_"""),_display_(/*222.38*/(i+1)),format.raw/*222.43*/("""" align="right">0.00</div>
									</td>
									<td style="text-align: right;">
										<div class="m2" id="totM2_"""),_display_(/*225.38*/(i+1)),format.raw/*225.43*/("""" align="right">0.00</div>
									</td>
									<td style="text-align: center;">
										<a href="#" onclick="eliminarFila(this)">
											<kbd style="background-color: red">X</kbd>
										</a>
									</td>
								</tr>
							
							""")))}),format.raw/*234.9*/("""
				 			"""),format.raw/*235.9*/("""<TR>
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
								<TH>"""),_display_(/*310.14*/mapDiccionario/*310.28*/.get("RUT")),format.raw/*310.39*/("""</TH>
								<TH>NICK</TH>
								<TH>NOMBRE</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*316.9*/for(lista1 <- listProveedor) yield /*316.37*/{_display_(Seq[Any](format.raw/*316.38*/("""
								"""),format.raw/*317.9*/("""<TR onClick="$('#id_proveedor').val("""),_display_(/*317.46*/lista1/*317.52*/.getId()),format.raw/*317.60*/("""); $('#rutProveedor').val('"""),_display_(/*317.88*/lista1/*317.94*/.getRut()),format.raw/*317.103*/("""');$('#nombreProveedor').val('"""),_display_(/*317.134*/lista1/*317.140*/.getNickName()),format.raw/*317.154*/("""');" data-dismiss="modal">
									<td  style="text-align:left;">"""),_display_(/*318.41*/lista1/*318.47*/.getRut()),format.raw/*318.56*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*319.41*/lista1/*319.47*/.getNickName()),format.raw/*319.61*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*320.41*/lista1/*320.47*/.getNombre()),format.raw/*320.59*/("""</td>
								</TR>
				 			""")))}),format.raw/*322.10*/("""
						"""),format.raw/*323.7*/("""</tbody>
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
							"""),_display_(/*358.9*/for(lista1 <- listEquipo) yield /*358.34*/{_display_(Seq[Any](format.raw/*358.35*/("""
								"""),format.raw/*359.9*/("""<TR onClick="auxCodEquip='"""),_display_(/*359.36*/lista1/*359.42*/.getCodigo()),format.raw/*359.54*/("""'; auxNomEquip='"""),_display_(/*359.71*/lista1/*359.77*/.getNombre()),format.raw/*359.89*/("""'; auxKgEquip='"""),_display_(/*359.105*/lista1/*359.111*/.getKg()),format.raw/*359.119*/("""'; auxM2Equip='"""),_display_(/*359.135*/lista1/*359.141*/.getM2()),format.raw/*359.149*/("""'; auxUnEquip='"""),_display_(/*359.165*/lista1/*359.171*/.getUnidad()),format.raw/*359.183*/("""'; selectEquipo("""),_display_(/*359.200*/lista1/*359.206*/.getId()),format.raw/*359.214*/(""")" data-dismiss="modal">
									<td  style="text-align:left;">"""),_display_(/*360.41*/lista1/*360.47*/.getGrupo()),format.raw/*360.58*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*361.41*/lista1/*361.47*/.getCodigo()),format.raw/*361.59*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*362.41*/lista1/*362.47*/.getNombre()),format.raw/*362.59*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*363.41*/lista1/*363.47*/.getFabrica()),format.raw/*363.60*/("""</td>
								</TR>
				 			""")))}),format.raw/*365.10*/("""
						"""),format.raw/*366.7*/("""</tbody>
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
									"""),_display_(/*398.11*/for(lista <- listGrupos) yield /*398.35*/{_display_(Seq[Any](format.raw/*398.36*/("""
										"""),format.raw/*399.11*/("""<option value=""""),_display_(/*399.27*/lista/*399.32*/.getId()),format.raw/*399.40*/("""">"""),_display_(/*399.43*/lista/*399.48*/.getNombre()),format.raw/*399.60*/("""</option>
									""")))}),format.raw/*400.11*/("""
								"""),format.raw/*401.9*/("""</select>
							</td>
						</TR>
						<TR>
							<td  style="text-align:left;">PROPIEDAD</td>
							<td style="text-align:left">
								<select class="custom-select"
								id="id_propiedad"
								name="id_propiedad">
									<option value=""""),_display_(/*410.26*/listPropiedad/*410.39*/.get(0).getId()),format.raw/*410.54*/("""">"""),_display_(/*410.57*/listPropiedad/*410.70*/.get(0).getNombre()),format.raw/*410.89*/("""</option>
									"""),_display_(/*411.11*/for(lista <- listPropiedad) yield /*411.38*/{_display_(Seq[Any](format.raw/*411.39*/("""
										"""),format.raw/*412.11*/("""<option value=""""),_display_(/*412.27*/lista/*412.32*/.getId()),format.raw/*412.40*/("""">"""),_display_(/*412.43*/lista/*412.48*/.getNombre()),format.raw/*412.60*/("""</option>
									""")))}),format.raw/*413.11*/("""
								"""),format.raw/*414.9*/("""</select>
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
									"""),_display_(/*446.11*/for(lista <- listFabrica) yield /*446.36*/{_display_(Seq[Any](format.raw/*446.37*/("""
										"""),format.raw/*447.11*/("""<option value=""""),_display_(/*447.27*/lista/*447.32*/.id),format.raw/*447.35*/("""">"""),_display_(/*447.38*/lista/*447.43*/.nickName),format.raw/*447.52*/("""</option>
									""")))}),format.raw/*448.11*/("""
								"""),format.raw/*449.9*/("""</select>
							</td>
						</tr>
						<tr>
							<td style="text-align:left; vertical-align:top">UNIDAD: </td>
							<td style="text-align:left; vertical-align:top">
								<select class="custom-select" 
									id="nuevoEquipoId_unidad"
									required>
									"""),_display_(/*458.11*/for(lista <- listUnidades) yield /*458.37*/{_display_(Seq[Any](format.raw/*458.38*/("""
										"""),format.raw/*459.11*/("""<option value=""""),_display_(/*459.27*/lista/*459.32*/.id),format.raw/*459.35*/("""">"""),_display_(/*459.38*/lista/*459.43*/.nombre),format.raw/*459.50*/("""</option>
									""")))}),format.raw/*460.11*/("""
								"""),format.raw/*461.9*/("""</select>
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
	
	
	
""")))}),format.raw/*525.2*/("""




"""),format.raw/*530.1*/("""<script type="text/javascript">

	let cont = 0;

	$(document).ready(function() """),format.raw/*534.31*/("""{"""),format.raw/*534.32*/("""
		
		"""),format.raw/*536.3*/("""$('#tablaListaProveedores').DataTable("""),format.raw/*536.41*/("""{"""),format.raw/*536.42*/("""
	    	"""),format.raw/*537.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
	    	"order": [[ 1, "asc" ]],
	    	"language": """),format.raw/*540.19*/("""{"""),format.raw/*540.20*/("""
	        	"""),format.raw/*541.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*542.10*/("""}"""),format.raw/*542.11*/("""
	  	"""),format.raw/*543.5*/("""}"""),format.raw/*543.6*/(""" """),format.raw/*543.7*/(""");
		
		$('#tablaListaEquipos').DataTable("""),format.raw/*545.37*/("""{"""),format.raw/*545.38*/("""
	    	"""),format.raw/*546.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
	    	"order": [[ 1, "asc" ]],
	    	"language": """),format.raw/*549.19*/("""{"""),format.raw/*549.20*/("""
	        	"""),format.raw/*550.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*551.10*/("""}"""),format.raw/*551.11*/("""
	  	"""),format.raw/*552.5*/("""}"""),format.raw/*552.6*/(""" """),format.raw/*552.7*/(""");
		  
		cont = """"),_display_(/*554.12*/(lista.size()+1)),format.raw/*554.28*/("""";
		todosLosTotales();
		
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*558.2*/("""}"""),format.raw/*558.3*/(""");
	
	const verificarNumeroDocumento = (value) =>"""),format.raw/*560.45*/("""{"""),format.raw/*560.46*/("""
		"""),format.raw/*561.3*/("""let id_proveedor = $("#id_proveedor").val();
		if(id_proveedor==0)"""),format.raw/*562.22*/("""{"""),format.raw/*562.23*/("""
			"""),format.raw/*563.4*/("""alertify.alert('Primero debe seleccionar el proveedor', function () """),format.raw/*563.72*/("""{"""),format.raw/*563.73*/("""
 				"""),format.raw/*564.6*/("""$("#numeroFactura").val("");
 			"""),format.raw/*565.5*/("""}"""),format.raw/*565.6*/(""");
		"""),format.raw/*566.3*/("""}"""),format.raw/*566.4*/("""else"""),format.raw/*566.8*/("""{"""),format.raw/*566.9*/("""
			"""),format.raw/*567.4*/("""var formData = new FormData();
		  	formData.append('numeroFactura',value);
		  	formData.append('id_proveedor',id_proveedor);
	        $.ajax("""),format.raw/*570.17*/("""{"""),format.raw/*570.18*/("""
	            """),format.raw/*571.14*/("""url: "/verificaNumeroFacturaAjax/",
	            type: "POST",
	            method: "POST",
	            data: formData,
	            cache: false,
	            contentType: false,
		     	processData: false,
		     	success: function(respuesta)"""),format.raw/*578.37*/("""{"""),format.raw/*578.38*/("""
		     		"""),format.raw/*579.10*/("""if(respuesta=="existe")"""),format.raw/*579.33*/("""{"""),format.raw/*579.34*/("""
		     			"""),format.raw/*580.11*/("""alertify.alert('El número de documento ya existe para este proveedor, intente con otro', function () """),format.raw/*580.112*/("""{"""),format.raw/*580.113*/("""
		     				"""),format.raw/*581.12*/("""$("#numeroFactura").val("");
		     			"""),format.raw/*582.11*/("""}"""),format.raw/*582.12*/(""");
		     		"""),format.raw/*583.10*/("""}"""),format.raw/*583.11*/("""
		     		"""),format.raw/*584.10*/("""if(respuesta=="error")"""),format.raw/*584.32*/("""{"""),format.raw/*584.33*/("""
		     			"""),format.raw/*585.11*/("""alertify.alert(msgError, function () """),format.raw/*585.48*/("""{"""),format.raw/*585.49*/("""
			     			"""),format.raw/*586.12*/("""location.href = "/";
			     		"""),format.raw/*587.11*/("""}"""),format.raw/*587.12*/(""");
		     		"""),format.raw/*588.10*/("""}"""),format.raw/*588.11*/("""
		     	"""),format.raw/*589.9*/("""}"""),format.raw/*589.10*/("""
	        """),format.raw/*590.10*/("""}"""),format.raw/*590.11*/(""");
		"""),format.raw/*591.3*/("""}"""),format.raw/*591.4*/("""
			
	"""),format.raw/*593.2*/("""}"""),format.raw/*593.3*/("""
	
	"""),format.raw/*595.2*/("""let mapDec = new Map();
	"""),_display_(/*596.3*/for(lista <- listMon) yield /*596.24*/{_display_(Seq[Any](format.raw/*596.25*/("""
		"""),format.raw/*597.3*/("""mapDec.set("""),_display_(/*597.15*/lista/*597.20*/.getId()),format.raw/*597.28*/(""","""),_display_(/*597.30*/lista/*597.35*/.getNumeroDecimales()),format.raw/*597.56*/(""");
	""")))}),format.raw/*598.3*/("""
	

	
	"""),format.raw/*602.2*/("""let auxCodEquip = "";
	let auxNomEquip = "";
	let auxKgEquip = "";
	let auxM2Equip = "";
	let auxUnEquip = "";
	
	
	const selectEquipo = (id_equipo) => """),format.raw/*609.38*/("""{"""),format.raw/*609.39*/("""
		"""),format.raw/*610.3*/("""cont++;
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
    		"""),_display_(/*649.8*/for(mon <- listMoneda) yield /*649.30*/{_display_(Seq[Any](format.raw/*649.31*/("""
    			"""),format.raw/*650.8*/("""mon += "<option value='"""),_display_(/*650.32*/mon/*650.35*/.getId()),format.raw/*650.43*/("""'>"""),_display_(/*650.46*/mon/*650.49*/.getNickName()),format.raw/*650.63*/("""</option>";
			""")))}),format.raw/*651.5*/("""
    		"""),format.raw/*652.7*/("""mon += "</select>";
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
												"""),_display_(/*675.14*/for(lista <- listSucursales) yield /*675.42*/{_display_(Seq[Any](format.raw/*675.43*/("""
													"""),format.raw/*676.14*/("""auxSucursales += "<option value=\""""),_display_(/*676.49*/lista/*676.54*/.getId()),format.raw/*676.62*/("""\">"""),_display_(/*676.66*/lista/*676.71*/.getNombre()),format.raw/*676.83*/("""</option>";
												""")))}),format.raw/*677.14*/("""
							        		"""),format.raw/*678.18*/("""auxSucursales += "</select></td>";
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
    		"""),_display_(/*694.8*/for(bod <- listBodegas) yield /*694.31*/{_display_(Seq[Any](format.raw/*694.32*/("""
    			"""),format.raw/*695.8*/("""des += "<option value='"""),_display_(/*695.32*/bod/*695.35*/.get(1)),format.raw/*695.42*/("""'>"""),_display_(/*695.45*/bod/*695.48*/.get(5)),format.raw/*695.55*/("""</option>";
			""")))}),format.raw/*696.5*/("""
    		"""),format.raw/*697.7*/("""des += "</select></div>";
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
		
	"""),format.raw/*712.2*/("""}"""),format.raw/*712.3*/("""
	
	"""),format.raw/*714.2*/("""const eliminarFila = (nodo) =>"""),format.raw/*714.32*/("""{"""),format.raw/*714.33*/("""
		"""),format.raw/*715.3*/("""let nodoTd = nodo.parentNode.parentNode; 
		$(nodoTd).remove();
		sumaTotales();
	"""),format.raw/*718.2*/("""}"""),format.raw/*718.3*/("""
	
	"""),format.raw/*720.2*/("""const actualTotales = (id) =>"""),format.raw/*720.31*/("""{"""),format.raw/*720.32*/("""
		"""),format.raw/*721.3*/("""let aux = id.split("_");
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
	"""),format.raw/*736.2*/("""}"""),format.raw/*736.3*/("""
	
	"""),format.raw/*738.2*/("""const todosLosTotales = () =>"""),format.raw/*738.31*/("""{"""),format.raw/*738.32*/("""
		"""),format.raw/*739.3*/("""for(let i=1; i<cont; i++)"""),format.raw/*739.28*/("""{"""),format.raw/*739.29*/("""
			"""),format.raw/*740.4*/("""let cantidad = $("#cantidad_"+i).val().replace(/,/g,'');
			let puCompra = $("#puCompra_"+i).val().replace(/,/g,'');
			let id_moneda = $("#idMoneda_"+i).val();
			let nroDecimal = mapDec.get(parseFloat(id_moneda));
			let kg = $("#KG_"+i).text().replace(/,/g,'');
			let m2 = $("#M2_"+i).text().replace(/,/g,'');
			let total = parseFloat(cantidad) * parseFloat(puCompra);
			let totKG = parseFloat(cantidad) * parseFloat(kg);
			let totM2 = parseFloat(cantidad) * parseFloat(m2);
			$("#puCompra_"+i).val(formatStandar(puCompra,nroDecimal));
			$("#total_"+i).text(formatStandar(total,nroDecimal));
			$("#totKG_"+i).text(formatStandar(totKG,2));
			$("#totM2_"+i).text(formatStandar(totM2,2));
		"""),format.raw/*753.3*/("""}"""),format.raw/*753.4*/("""
		"""),format.raw/*754.3*/("""sumaTotales();
	"""),format.raw/*755.2*/("""}"""),format.raw/*755.3*/("""
	
	"""),format.raw/*757.2*/("""const sumaTotales = () => """),format.raw/*757.28*/("""{"""),format.raw/*757.29*/("""
		 """),format.raw/*758.4*/("""let sum = 0;
			$(".cantidad").each(function() """),format.raw/*759.35*/("""{"""),format.raw/*759.36*/("""
				"""),format.raw/*760.5*/("""let val = $(this).val().replace(/,/g,'');
				sum += parseFloat(val);
			"""),format.raw/*762.4*/("""}"""),format.raw/*762.5*/("""); $("#totalCantidad").text(formatStandar(sum,2));
			sum = 0;
			$(".total").each(function() """),format.raw/*764.32*/("""{"""),format.raw/*764.33*/("""
				"""),format.raw/*765.5*/("""let val = $(this).text().replace(/,/g,'');
				sum += parseFloat(val);
			"""),format.raw/*767.4*/("""}"""),format.raw/*767.5*/("""); $("#totalTotal").text(formatStandar(sum,2));
			sum = 0;
			$(".kg").each(function() """),format.raw/*769.29*/("""{"""),format.raw/*769.30*/("""
				"""),format.raw/*770.5*/("""let val = $(this).text().replace(/,/g,'');
				sum += parseFloat(val);
			"""),format.raw/*772.4*/("""}"""),format.raw/*772.5*/("""); $("#totalKG").text(formatStandar(sum,2));
			sum = 0;
			$(".m2").each(function() """),format.raw/*774.29*/("""{"""),format.raw/*774.30*/("""
				"""),format.raw/*775.5*/("""let val = $(this).text().replace(/,/g,'');
				sum += parseFloat(val);
			"""),format.raw/*777.4*/("""}"""),format.raw/*777.5*/("""); $("#totalM2").text(formatStandar(sum,2));
	 """),format.raw/*778.3*/("""}"""),format.raw/*778.4*/("""
	
	
	"""),format.raw/*781.2*/("""const validarForm = () =>"""),format.raw/*781.27*/("""{"""),format.raw/*781.28*/("""
		"""),format.raw/*782.3*/("""let flag = true;
		if($("#id_proveedor").val()=="0")"""),format.raw/*783.36*/("""{"""),format.raw/*783.37*/("""
			"""),format.raw/*784.4*/("""flag = false;
			alertify.alert('FALTA SELECCIONAR PROVEEDOR', function () """),format.raw/*785.62*/("""{"""),format.raw/*785.63*/("""
				"""),format.raw/*786.5*/("""return(flag);
     		"""),format.raw/*787.8*/("""}"""),format.raw/*787.9*/(""");
		"""),format.raw/*788.3*/("""}"""),format.raw/*788.4*/("""else"""),format.raw/*788.8*/("""{"""),format.raw/*788.9*/("""
			"""),format.raw/*789.4*/("""$("#tablaPrincipal").dataTable().fnDestroy();
			let tabla = document.getElementById("tablaPrincipal");
			let cantTotal = $("#totalCantidad").text().replace(/,/g,'');
			if(tabla.rows.length > 3 && parseFloat(cantTotal) > 0)"""),format.raw/*792.58*/("""{"""),format.raw/*792.59*/("""
				
				"""),format.raw/*794.5*/("""flag = true;
				$(".selBodDest").each(function() """),format.raw/*795.38*/("""{"""),format.raw/*795.39*/("""
					"""),format.raw/*796.6*/("""let val = $(this).val();
					if(val == 0)"""),format.raw/*797.18*/("""{"""),format.raw/*797.19*/("""
						"""),format.raw/*798.7*/("""flag = false;
					"""),format.raw/*799.6*/("""}"""),format.raw/*799.7*/("""
				"""),format.raw/*800.5*/("""}"""),format.raw/*800.6*/(""");
				
				if(flag)"""),format.raw/*802.13*/("""{"""),format.raw/*802.14*/("""
					"""),format.raw/*803.6*/("""return(flag);
				"""),format.raw/*804.5*/("""}"""),format.raw/*804.6*/("""else"""),format.raw/*804.10*/("""{"""),format.raw/*804.11*/("""
					"""),format.raw/*805.6*/("""alertify.alert('PARA GRABAR UNA COMPRA TODOS LAS LINEAS DEBEN TENER UN DESTINO', function () """),format.raw/*805.99*/("""{"""),format.raw/*805.100*/("""
						"""),format.raw/*806.7*/("""return(flag);
	     			"""),format.raw/*807.10*/("""}"""),format.raw/*807.11*/(""");
				"""),format.raw/*808.5*/("""}"""),format.raw/*808.6*/("""
			"""),format.raw/*809.4*/("""}"""),format.raw/*809.5*/("""else"""),format.raw/*809.9*/("""{"""),format.raw/*809.10*/("""
				"""),format.raw/*810.5*/("""flag = false;
				alertify.alert('PARA GRABAR UNA COMPRA AL MENOS DEBE COMPRAR UN EQUIPOS', function () """),format.raw/*811.91*/("""{"""),format.raw/*811.92*/("""
					"""),format.raw/*812.6*/("""return(flag);
	     		"""),format.raw/*813.9*/("""}"""),format.raw/*813.10*/(""");
			"""),format.raw/*814.4*/("""}"""),format.raw/*814.5*/("""
		"""),format.raw/*815.3*/("""}"""),format.raw/*815.4*/("""
		"""),format.raw/*816.3*/("""return(flag);
	"""),format.raw/*817.2*/("""}"""),format.raw/*817.3*/("""
	
	"""),format.raw/*819.2*/("""const selSucursalPrecio = (index,id_equipo) => """),format.raw/*819.49*/("""{"""),format.raw/*819.50*/("""
		"""),format.raw/*820.3*/("""let id_sucursal = $("#id_sucursal_"+index).val();
		if(id_sucursal != 0)"""),format.raw/*821.23*/("""{"""),format.raw/*821.24*/("""
			"""),format.raw/*822.4*/("""var formData = new FormData();
			formData.append('id_sucursal',id_sucursal);
		  	formData.append('id_equipo',id_equipo);

	        $.ajax("""),format.raw/*826.17*/("""{"""),format.raw/*826.18*/("""
	            """),format.raw/*827.14*/("""url: "/modalPreciosAjax/",
	            type: "POST",
	            method: "POST",
	            data: formData,
	            cache: false,
	            contentType: false,
		     	processData: false,
		     	success: function(rs1)"""),format.raw/*834.31*/("""{"""),format.raw/*834.32*/("""
					"""),format.raw/*835.6*/("""$.ajax("""),format.raw/*835.13*/("""{"""),format.raw/*835.14*/("""
			            """),format.raw/*836.16*/("""url: "/routes2/compraVistaSelBodDestAjax/",
			            type: "POST",
			            method: "POST",
			            data: formData,
			            cache: false,
			            contentType: false,
				     	processData: false,
				     	success: function(rs2)"""),format.raw/*843.33*/("""{"""),format.raw/*843.34*/("""
							"""),format.raw/*844.8*/("""document.getElementById("modalPrecios").innerHTML =  rs1;
				     		$("#modalPrecioLista").modal("show");
				     	"""),format.raw/*846.11*/("""}"""),format.raw/*846.12*/("""
			        """),format.raw/*847.12*/("""}"""),format.raw/*847.13*/(""");
		     	"""),format.raw/*848.9*/("""}"""),format.raw/*848.10*/("""
	        """),format.raw/*849.10*/("""}"""),format.raw/*849.11*/(""");
		"""),format.raw/*850.3*/("""}"""),format.raw/*850.4*/("""else"""),format.raw/*850.8*/("""{"""),format.raw/*850.9*/("""
			"""),format.raw/*851.4*/("""alertify.alert("Primero debe seleccionar la Sucursal", function () """),format.raw/*851.71*/("""{"""),format.raw/*851.72*/("""
     			"""),format.raw/*852.9*/("""}"""),format.raw/*852.10*/(""");
		"""),format.raw/*853.3*/("""}"""),format.raw/*853.4*/("""
	"""),format.raw/*854.2*/("""}"""),format.raw/*854.3*/("""
	
	"""),format.raw/*856.2*/("""const actualizaSucursal = (index, auxIdEquipo, id_sucursal) =>"""),format.raw/*856.64*/("""{"""),format.raw/*856.65*/("""
		"""),format.raw/*857.3*/("""$("#modalSucursal").modal("hide");
		if(id_sucursal != 0)"""),format.raw/*858.23*/("""{"""),format.raw/*858.24*/("""
			"""),format.raw/*859.4*/("""var formData = new FormData();
			formData.append('id_sucursal',id_sucursal);
		  	formData.append('id_equipo',auxIdEquipo);
	        $.ajax("""),format.raw/*862.17*/("""{"""),format.raw/*862.18*/("""
	            """),format.raw/*863.14*/("""url: "/routes2/compraVistaSelBodDestAjax/",
	            type: "POST",
	            method: "POST",
	            data: formData,
	            cache: false,
	            contentType: false,
		     	processData: false,
		     	success: function(rs2)"""),format.raw/*870.31*/("""{"""),format.raw/*870.32*/("""
		     		"""),format.raw/*871.10*/("""document.getElementById("selBodegaDestino_"+index).innerHTML =  rs2["vista"];
					$("#id_sucursal_"+index).val(id_sucursal);
		     		$("#sucursal_"+index).val(rs2["nameSucursal"]);
		     	"""),format.raw/*874.9*/("""}"""),format.raw/*874.10*/("""
	        """),format.raw/*875.10*/("""}"""),format.raw/*875.11*/(""");
		"""),format.raw/*876.3*/("""}"""),format.raw/*876.4*/("""
	"""),format.raw/*877.2*/("""}"""),format.raw/*877.3*/("""
	
	
	
	"""),format.raw/*881.2*/("""const actualizaSelect  = (index, id_bodega, id_equipo) => """),format.raw/*881.60*/("""{"""),format.raw/*881.61*/("""
		"""),format.raw/*882.3*/("""var formData = new FormData();
		formData.append('id_bodega',id_bodega);
	  	formData.append('id_equipo',id_equipo);
		$.ajax("""),format.raw/*885.10*/("""{"""),format.raw/*885.11*/("""
            """),format.raw/*886.13*/("""url: "/routes2/compraVistaSelBodDestAjax2/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(rs2)"""),format.raw/*893.30*/("""{"""),format.raw/*893.31*/("""
	     		"""),format.raw/*894.9*/("""document.getElementById("selBodegaDestino_"+index).innerHTML =  rs2;
	     	"""),format.raw/*895.8*/("""}"""),format.raw/*895.9*/("""
        """),format.raw/*896.9*/("""}"""),format.raw/*896.10*/(""");
	"""),format.raw/*897.2*/("""}"""),format.raw/*897.3*/("""
	
	"""),format.raw/*899.2*/("""const cambiarPrecio = (id_sucursal, id_equipo,campo,valor) => """),format.raw/*899.64*/("""{"""),format.raw/*899.65*/("""
		"""),format.raw/*900.3*/("""let id_moneda = $("#precioMoneda").val();
		var formData = new FormData();
		formData.append('id_sucursal',id_sucursal);
		formData.append('id_equipo',id_equipo);
		formData.append('id_moneda',id_moneda);
		formData.append('campo',campo);
		formData.append('valor',valor);
		$.ajax("""),format.raw/*907.10*/("""{"""),format.raw/*907.11*/("""
            """),format.raw/*908.13*/("""url: "/actualizaPrecioAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*915.36*/("""{"""),format.raw/*915.37*/("""
	     		"""),format.raw/*916.9*/("""if(respuesta=="error")"""),format.raw/*916.31*/("""{"""),format.raw/*916.32*/("""
	     			"""),format.raw/*917.10*/("""alertify.alert(msgError, function () """),format.raw/*917.47*/("""{"""),format.raw/*917.48*/("""
		     			"""),format.raw/*918.11*/("""location.href = "/";
		     		"""),format.raw/*919.10*/("""}"""),format.raw/*919.11*/(""");
	     		"""),format.raw/*920.9*/("""}"""),format.raw/*920.10*/("""
	     		"""),format.raw/*921.9*/("""if(respuesta.status)"""),format.raw/*921.29*/("""{"""),format.raw/*921.30*/("""
	     			"""),format.raw/*922.10*/("""let nroDecimal = mapDec.get(parseFloat(id_moneda));
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
	     		"""),format.raw/*938.9*/("""}"""),format.raw/*938.10*/("""
	     	"""),format.raw/*939.8*/("""}"""),format.raw/*939.9*/("""
        """),format.raw/*940.9*/("""}"""),format.raw/*940.10*/(""");
	"""),format.raw/*941.2*/("""}"""),format.raw/*941.3*/("""
	
	
	"""),format.raw/*944.2*/("""const grabaNuevoEquipo = () =>"""),format.raw/*944.32*/("""{"""),format.raw/*944.33*/("""
		
		"""),format.raw/*946.3*/("""nuevoEquipoCodigo = $("#nuevoEquipoCodigo").val().trim();
		nuevoEquipoNombre = $("#nuevoEquipoNombre").val().trim();
		if(nuevoEquipoCodigo=="")"""),format.raw/*948.28*/("""{"""),format.raw/*948.29*/("""
			"""),format.raw/*949.4*/("""alertify.alert("Debe ingresar un código de equipo", function () """),format.raw/*949.68*/("""{"""),format.raw/*949.69*/("""
 				"""),format.raw/*950.6*/("""$("#nuevoEquipoCodigo").focus();
     		"""),format.raw/*951.8*/("""}"""),format.raw/*951.9*/(""");
		"""),format.raw/*952.3*/("""}"""),format.raw/*952.4*/("""else if(nuevoEquipoNombre=="")"""),format.raw/*952.34*/("""{"""),format.raw/*952.35*/("""
			"""),format.raw/*953.4*/("""alertify.alert("Debe ingresar un nombre de equipo", function () """),format.raw/*953.68*/("""{"""),format.raw/*953.69*/("""
 				"""),format.raw/*954.6*/("""$("#nuevoEquipoNombre").focus();
     		"""),format.raw/*955.8*/("""}"""),format.raw/*955.9*/(""");
		"""),format.raw/*956.3*/("""}"""),format.raw/*956.4*/("""else"""),format.raw/*956.8*/("""{"""),format.raw/*956.9*/("""
			"""),format.raw/*957.4*/("""$("#modalNuevoEquipo").modal('hide');
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
			
			$.ajax("""),format.raw/*972.11*/("""{"""),format.raw/*972.12*/("""
	            """),format.raw/*973.14*/("""url: "/nuevoEquipoAjax/",
	            type: "POST",
	            method: "POST",
	            data: formData,
	            cache: false,
	            contentType: false,
		     	processData: false,
		     	success: function(respuesta)"""),format.raw/*980.37*/("""{"""),format.raw/*980.38*/("""
		     		"""),format.raw/*981.10*/("""if(respuesta=="existe")"""),format.raw/*981.33*/("""{"""),format.raw/*981.34*/("""
		     			"""),format.raw/*982.11*/("""alertify.alert("El código de equipo ya existe, intente con otro", function () """),format.raw/*982.89*/("""{"""),format.raw/*982.90*/("""
		     				"""),format.raw/*983.12*/("""$("#nuevoEquipoCodigo").val("");
			     		"""),format.raw/*984.11*/("""}"""),format.raw/*984.12*/(""");
		     		"""),format.raw/*985.10*/("""}"""),format.raw/*985.11*/("""else if(respuesta=="error")"""),format.raw/*985.38*/("""{"""),format.raw/*985.39*/("""
		     			"""),format.raw/*986.11*/("""alertify.alert(msgError, function () """),format.raw/*986.48*/("""{"""),format.raw/*986.49*/("""
			     			"""),format.raw/*987.12*/("""location.href = "/";
			     		"""),format.raw/*988.11*/("""}"""),format.raw/*988.12*/(""");
		     		"""),format.raw/*989.10*/("""}"""),format.raw/*989.11*/("""else"""),format.raw/*989.15*/("""{"""),format.raw/*989.16*/("""
		     			
		     			"""),format.raw/*991.11*/("""let rs = respuesta.split("_");
		     			auxCodEquip = $("#nuevoEquipoCodigo").val();
		     			auxNomEquip = $("#nuevoEquipoNombre").val();
		     			auxKgEquip = rs[1];
		     			auxM2Equip = rs[2];
		     			auxUnEquip = auxEquipoUnidad;
		     			selectEquipo(rs[0]);
		     			
		     			$("#nuevoEquipoCodigo").val("");
		     			$("#nuevoEquipoNombre").val("")
		     		"""),format.raw/*1001.10*/("""}"""),format.raw/*1001.11*/("""
		     	"""),format.raw/*1002.9*/("""}"""),format.raw/*1002.10*/("""
	        """),format.raw/*1003.10*/("""}"""),format.raw/*1003.11*/(""");
		"""),format.raw/*1004.3*/("""}"""),format.raw/*1004.4*/("""
	"""),format.raw/*1005.2*/("""}"""),format.raw/*1005.3*/("""
	
	"""),format.raw/*1007.2*/("""const verificaCodigo = (codigo) => """),format.raw/*1007.37*/("""{"""),format.raw/*1007.38*/("""
		"""),format.raw/*1008.3*/("""var formData = new FormData();
	  	formData.append('codigo',codigo);
        $.ajax("""),format.raw/*1010.16*/("""{"""),format.raw/*1010.17*/("""
            """),format.raw/*1011.13*/("""url: "/verificaCodigoEquipoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*1018.36*/("""{"""),format.raw/*1018.37*/("""
	     		"""),format.raw/*1019.9*/("""if(respuesta=="existe")"""),format.raw/*1019.32*/("""{"""),format.raw/*1019.33*/("""
	     			"""),format.raw/*1020.10*/("""alertify.alert("El código de equipo ya existe, intente con otro", function () """),format.raw/*1020.88*/("""{"""),format.raw/*1020.89*/("""
	     				"""),format.raw/*1021.11*/("""$("#nuevoEquipoCodigo").val("");
		     		"""),format.raw/*1022.10*/("""}"""),format.raw/*1022.11*/(""");
	     		"""),format.raw/*1023.9*/("""}"""),format.raw/*1023.10*/("""
	     		"""),format.raw/*1024.9*/("""if(respuesta=="error")"""),format.raw/*1024.31*/("""{"""),format.raw/*1024.32*/("""
	     			"""),format.raw/*1025.10*/("""alertify.alert(msgError, function () """),format.raw/*1025.47*/("""{"""),format.raw/*1025.48*/("""
		     			"""),format.raw/*1026.11*/("""location.href = "/";
		     		"""),format.raw/*1027.10*/("""}"""),format.raw/*1027.11*/(""");
	     		"""),format.raw/*1028.9*/("""}"""),format.raw/*1028.10*/("""
	     	"""),format.raw/*1029.8*/("""}"""),format.raw/*1029.9*/("""
        """),format.raw/*1030.9*/("""}"""),format.raw/*1030.10*/(""");
	"""),format.raw/*1031.2*/("""}"""),format.raw/*1031.3*/("""
	
	"""),format.raw/*1033.2*/("""let extArray = new Array(".gif", ".jpg", ".png", ".jpeg", ".pdf", ".xls", ".doc", ".xlsx", ".docx", ".tar", ".zip", ".rar");
	const LimitAttach = (form, file) => """),format.raw/*1034.38*/("""{"""),format.raw/*1034.39*/("""
		"""),format.raw/*1035.3*/("""let allowSubmit = false;
		if (!file) return;
		while (file.indexOf("\\") != -1)"""),format.raw/*1037.35*/("""{"""),format.raw/*1037.36*/("""
			"""),format.raw/*1038.4*/("""file = file.slice(file.indexOf("\\") + 1);
		"""),format.raw/*1039.3*/("""}"""),format.raw/*1039.4*/("""
		"""),format.raw/*1040.3*/("""let extencion = file.slice(file.lastIndexOf(".")).toLowerCase();
		for (var i = 0; i < extArray.length; i++) """),format.raw/*1041.45*/("""{"""),format.raw/*1041.46*/("""
			"""),format.raw/*1042.4*/("""if (extArray[i] == extencion) """),format.raw/*1042.34*/("""{"""),format.raw/*1042.35*/(""" """),format.raw/*1042.36*/("""allowSubmit = true; break; """),format.raw/*1042.63*/("""}"""),format.raw/*1042.64*/("""
		"""),format.raw/*1043.3*/("""}"""),format.raw/*1043.4*/("""
		"""),format.raw/*1044.3*/("""if (allowSubmit) """),format.raw/*1044.20*/("""{"""),format.raw/*1044.21*/("""
			"""),format.raw/*1045.4*/("""var file = $("#docAdjunto")[0].files[0];
			var fileSize = file.size; //tamaño del archivo
			if(fileSize>100000000)"""),format.raw/*1047.26*/("""{"""),format.raw/*1047.27*/("""
				"""),format.raw/*1048.5*/("""alert("Se permiten únicamente archivos que no superen los 100 MB,"
				+" el que se intenta subir pesa: "+Math.round(file.size/10)/100+" KB");
				form.docAdjunto.value="";
			"""),format.raw/*1051.4*/("""}"""),format.raw/*1051.5*/("""
		"""),format.raw/*1052.3*/("""}"""),format.raw/*1052.4*/("""else"""),format.raw/*1052.8*/("""{"""),format.raw/*1052.9*/("""
			"""),format.raw/*1053.4*/("""alert("Se permiten únicamente archivos con la extención: " 
			+ (extArray.join("  ")) + "\nPor favor, seleccione otro archivo "
			+ "e intente de nuevo.");
			form.docAdjunto.value="";
		"""),format.raw/*1057.3*/("""}"""),format.raw/*1057.4*/("""
	"""),format.raw/*1058.2*/("""}"""),format.raw/*1058.3*/("""
	

	
"""),format.raw/*1062.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listProveedor:List[tables.Proveedor],listEquipo:List[tables.Equipo],listMoneda:List[tables.Moneda],listBodegas:List[List[String]],listMon:List[tables.Moneda],listGrupos:List[tables.Grupo],listFabrica:List[tables.Fabrica],listUnidades:List[tables.Unidad],listRegiones:List[tables.Regiones],listSucursales:List[tables.Sucursal],lista:List[List[String]],proveedor:tables.Proveedor,listPropiedad:List[tables.Propiedad]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listProveedor,listEquipo,listMoneda,listBodegas,listMon,listGrupos,listFabrica,listUnidades,listRegiones,listSucursales,lista,proveedor,listPropiedad)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Proveedor],List[tables.Equipo],List[tables.Moneda],List[List[String]],List[tables.Moneda],List[tables.Grupo],List[tables.Fabrica],List[tables.Unidad],List[tables.Regiones],List[tables.Sucursal],List[List[String]],tables.Proveedor,List[tables.Propiedad]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listProveedor,listEquipo,listMoneda,listBodegas,listMon,listGrupos,listFabrica,listUnidades,listRegiones,listSucursales,lista,proveedor,listPropiedad) => apply(mapDiccionario,mapPermiso,userMnu,listProveedor,listEquipo,listMoneda,listBodegas,listMon,listGrupos,listFabrica,listUnidades,listRegiones,listSucursales,lista,proveedor,listPropiedad)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCompras/compraIngreso2.scala.html
                  HASH: 11e45ea6b8d9ef385cec6eba035146950a25aa5f
                  MATRIX: 1279->1|1910->539|1943->547|1959->555|1998->557|2026->560|2094->608|2125->612|2184->645|2254->694|2284->697|2368->753|2397->754|2429->759|3335->1638|3363->1639|3393->1642|3630->1853|3738->1940|3769->1944|4223->2371|4246->2385|4278->2396|4307->2397|4578->2641|4596->2650|4625->2658|4759->2765|4777->2774|4807->2783|5296->3245|5314->3254|5349->3268|8107->5999|8156->6031|8196->6032|8233->6041|8364->6144|8375->6145|8405->6153|8447->6167|8458->6168|8487->6175|8526->6185|8600->6231|8611->6232|8640->6239|8738->6309|8765->6314|8810->6331|8821->6332|8850->6339|8964->6425|8991->6430|9036->6447|9047->6448|9076->6455|9168->6519|9179->6520|9208->6527|9405->6696|9432->6701|9482->6723|9493->6724|9522->6731|9928->7109|9955->7114|10050->7181|10061->7182|10091->7190|10122->7193|10133->7194|10162->7201|10212->7223|10251->7245|10291->7246|10336->7262|10380->7278|10393->7281|10423->7289|10454->7292|10467->7295|10503->7309|10557->7331|10597->7342|10804->7521|10831->7526|10881->7548|10892->7549|10921->7556|11166->7773|11177->7774|11207->7782|11357->7904|11384->7909|11594->8091|11621->8096|11653->8100|11664->8101|11694->8109|11761->8148|11772->8149|11802->8157|11833->8160|11844->8161|11874->8169|11924->8191|11969->8219|12009->8220|12051->8233|12095->8249|12110->8254|12140->8262|12171->8265|12186->8270|12220->8282|12274->8304|12314->8315|12457->8430|12484->8435|12522->8445|12533->8446|12563->8454|12646->8509|12673->8514|12705->8518|12716->8519|12746->8527|12955->8708|12982->8713|13135->8838|13146->8839|13176->8847|13207->8850|13218->8851|13247->8858|13298->8881|13338->8904|13378->8905|13424->8922|13468->8938|13481->8941|13510->8948|13541->8951|13554->8954|13583->8961|13638->8984|13679->8996|13827->9116|13854->9121|14002->9241|14029->9246|14310->9496|14347->9505|16853->11983|16877->11997|16910->12008|17039->12110|17084->12138|17124->12139|17161->12148|17226->12185|17242->12191|17272->12199|17328->12227|17344->12233|17376->12242|17436->12273|17453->12279|17490->12293|17585->12360|17601->12366|17632->12375|17706->12421|17722->12427|17758->12441|17832->12487|17848->12493|17882->12505|17943->12534|17978->12541|19456->13992|19498->14017|19538->14018|19575->14027|19630->14054|19646->14060|19680->14072|19725->14089|19741->14095|19775->14107|19820->14123|19837->14129|19868->14137|19913->14153|19930->14159|19961->14167|20006->14183|20023->14189|20058->14201|20104->14218|20121->14224|20152->14232|20245->14297|20261->14303|20294->14314|20368->14360|20384->14366|20418->14378|20492->14424|20508->14430|20542->14442|20616->14488|20632->14494|20667->14507|20728->14536|20763->14543|21863->15615|21904->15639|21944->15640|21984->15651|22028->15667|22043->15672|22073->15680|22104->15683|22119->15688|22153->15700|22205->15720|22242->15729|22522->15981|22545->15994|22582->16009|22613->16012|22636->16025|22677->16044|22725->16064|22769->16091|22809->16092|22849->16103|22893->16119|22908->16124|22938->16132|22969->16135|22984->16140|23018->16152|23070->16172|23107->16181|24265->17311|24307->17336|24347->17337|24387->17348|24431->17364|24446->17369|24471->17372|24502->17375|24517->17380|24548->17389|24600->17409|24637->17418|24939->17692|24982->17718|25022->17719|25062->17730|25106->17746|25121->17751|25146->17754|25177->17757|25192->17762|25221->17769|25273->17789|25310->17798|27586->20043|27619->20048|27727->20127|27757->20128|27791->20134|27858->20172|27888->20173|27923->20180|28095->20323|28125->20324|28165->20335|28271->20412|28301->20413|28334->20418|28363->20419|28392->20420|28463->20462|28493->20463|28528->20470|28700->20613|28730->20614|28770->20625|28876->20702|28906->20703|28939->20708|28968->20709|28997->20710|29044->20729|29082->20745|29207->20842|29236->20843|29314->20892|29344->20893|29375->20896|29470->20962|29500->20963|29532->20967|29629->21035|29659->21036|29693->21042|29754->21075|29783->21076|29816->21081|29845->21082|29877->21086|29906->21087|29938->21091|30110->21234|30140->21235|30183->21249|30457->21494|30487->21495|30526->21505|30578->21528|30608->21529|30648->21540|30779->21641|30810->21642|30851->21654|30919->21693|30949->21694|30990->21706|31020->21707|31059->21717|31110->21739|31140->21740|31180->21751|31246->21788|31276->21789|31317->21801|31377->21832|31407->21833|31448->21845|31478->21846|31515->21855|31545->21856|31584->21866|31614->21867|31647->21872|31676->21873|31710->21879|31739->21880|31771->21884|31824->21910|31862->21931|31902->21932|31933->21935|31973->21947|31988->21952|32018->21960|32048->21962|32063->21967|32106->21988|32142->21993|32177->22000|32358->22152|32388->22153|32419->22156|33905->23615|33944->23637|33984->23638|34020->23646|34072->23670|34085->23673|34115->23681|34146->23684|34159->23687|34195->23701|34242->23717|34277->23724|35227->24646|35272->24674|35312->24675|35355->24689|35418->24724|35433->24729|35463->24737|35495->24741|35510->24746|35544->24758|35601->24783|35648->24801|36303->25429|36343->25452|36383->25453|36419->25461|36471->25485|36484->25488|36513->25495|36544->25498|36557->25501|36586->25508|36633->25524|36668->25531|37245->26080|37274->26081|37306->26085|37365->26115|37395->26116|37426->26119|37536->26201|37565->26202|37597->26206|37655->26235|37685->26236|37716->26239|38519->27014|38548->27015|38580->27019|38638->27048|38668->27049|38699->27052|38753->27077|38783->27078|38815->27082|39542->27781|39571->27782|39602->27785|39646->27801|39675->27802|39707->27806|39762->27832|39792->27833|39824->27837|39900->27884|39930->27885|39963->27890|40064->27963|40093->27964|40216->28058|40246->28059|40279->28064|40381->28138|40410->28139|40527->28227|40557->28228|40590->28233|40692->28307|40721->28308|40835->28393|40865->28394|40898->28399|41000->28473|41029->28474|41104->28521|41133->28522|41167->28528|41221->28553|41251->28554|41282->28557|41363->28609|41393->28610|41425->28614|41529->28689|41559->28690|41592->28695|41641->28716|41670->28717|41703->28722|41732->28723|41764->28727|41793->28728|41825->28732|42079->28957|42109->28958|42147->28968|42226->29018|42256->29019|42290->29025|42361->29067|42391->29068|42426->29075|42473->29094|42502->29095|42535->29100|42564->29101|42613->29121|42643->29122|42677->29128|42723->29146|42752->29147|42785->29151|42815->29152|42849->29158|42971->29251|43002->29252|43037->29259|43089->29282|43119->29283|43154->29290|43183->29291|43215->29295|43244->29296|43276->29300|43306->29301|43339->29306|43472->29410|43502->29411|43536->29417|43586->29439|43616->29440|43650->29446|43679->29447|43710->29450|43739->29451|43770->29454|43813->29469|43842->29470|43874->29474|43950->29521|43980->29522|44011->29525|44112->29597|44142->29598|44174->29602|44343->29742|44373->29743|44416->29757|44675->29987|44705->29988|44739->29994|44775->30001|44805->30002|44850->30018|45140->30279|45170->30280|45206->30288|45352->30405|45382->30406|45423->30418|45453->30419|45492->30430|45522->30431|45561->30441|45591->30442|45624->30447|45653->30448|45685->30452|45714->30453|45746->30457|45842->30524|45872->30525|45909->30534|45939->30535|45972->30540|46001->30541|46031->30543|46060->30544|46092->30548|46183->30610|46213->30611|46244->30614|46330->30671|46360->30672|46392->30676|46562->30817|46592->30818|46635->30832|46911->31079|46941->31080|46980->31090|47199->31281|47229->31282|47268->31292|47298->31293|47331->31298|47360->31299|47390->31301|47419->31302|47455->31310|47542->31368|47572->31369|47603->31372|47758->31498|47788->31499|47830->31512|48100->31753|48130->31754|48167->31763|48271->31839|48300->31840|48337->31849|48367->31850|48399->31854|48428->31855|48460->31859|48551->31921|48581->31922|48612->31925|48923->32207|48953->32208|48995->32221|49256->32453|49286->32454|49323->32463|49374->32485|49404->32486|49443->32496|49509->32533|49539->32534|49579->32545|49638->32575|49668->32576|49707->32587|49737->32588|49774->32597|49823->32617|49853->32618|49892->32628|51016->33724|51046->33725|51082->33733|51111->33734|51148->33743|51178->33744|51210->33748|51239->33749|51273->33755|51332->33785|51362->33786|51396->33792|51570->33937|51600->33938|51632->33942|51725->34006|51755->34007|51789->34013|51857->34053|51886->34054|51919->34059|51948->34060|52007->34090|52037->34091|52069->34095|52162->34159|52192->34160|52226->34166|52294->34206|52323->34207|52356->34212|52385->34213|52417->34217|52446->34218|52478->34222|53304->35019|53334->35020|53377->35034|53641->35269|53671->35270|53710->35280|53762->35303|53792->35304|53832->35315|53939->35393|53969->35394|54010->35406|54082->35449|54112->35450|54153->35462|54183->35463|54239->35490|54269->35491|54309->35502|54375->35539|54405->35540|54446->35552|54506->35583|54536->35584|54577->35596|54607->35597|54640->35601|54670->35602|54721->35624|55128->36001|55159->36002|55197->36011|55228->36012|55268->36022|55299->36023|55333->36028|55363->36029|55394->36031|55424->36032|55457->36036|55522->36071|55553->36072|55585->36075|55699->36159|55730->36160|55773->36173|56040->36410|56071->36411|56109->36420|56162->36443|56193->36444|56233->36454|56341->36532|56372->36533|56413->36544|56485->36586|56516->36587|56556->36598|56587->36599|56625->36608|56677->36630|56708->36631|56748->36641|56815->36678|56846->36679|56887->36690|56947->36720|56978->36721|57018->36732|57049->36733|57086->36741|57116->36742|57154->36751|57185->36752|57218->36756|57248->36757|57281->36761|57473->36923|57504->36924|57536->36927|57646->37007|57677->37008|57710->37012|57784->37057|57814->37058|57846->37061|57985->37170|58016->37171|58049->37175|58109->37205|58140->37206|58171->37207|58228->37234|58259->37235|58291->37238|58321->37239|58353->37242|58400->37259|58431->37260|58464->37264|58610->37380|58641->37381|58675->37386|58880->37562|58910->37563|58942->37566|58972->37567|59005->37571|59035->37572|59068->37576|59286->37765|59316->37766|59347->37768|59377->37769|59412->37775
                  LINES: 28->1|36->5|38->7|38->7|38->7|40->9|40->9|43->12|44->13|44->13|45->14|46->15|46->15|47->16|63->32|63->32|64->33|70->39|70->39|71->40|79->48|79->48|79->48|79->48|83->52|83->52|83->52|86->55|86->55|86->55|99->68|99->68|99->68|180->149|180->149|180->149|181->150|183->152|183->152|183->152|184->153|184->153|184->153|185->154|186->155|186->155|186->155|188->157|188->157|188->157|188->157|188->157|191->160|191->160|191->160|191->160|191->160|193->162|193->162|193->162|197->166|197->166|198->167|198->167|198->167|206->175|206->175|208->177|208->177|208->177|208->177|208->177|208->177|209->178|209->178|209->178|210->179|210->179|210->179|210->179|210->179|210->179|210->179|211->180|212->181|217->186|217->186|218->187|218->187|218->187|222->191|222->191|222->191|225->194|225->194|229->198|229->198|229->198|229->198|229->198|230->199|230->199|230->199|230->199|230->199|230->199|231->200|231->200|231->200|232->201|232->201|232->201|232->201|232->201|232->201|232->201|233->202|234->203|237->206|237->206|237->206|237->206|237->206|238->207|238->207|238->207|238->207|238->207|243->212|243->212|245->214|245->214|245->214|245->214|245->214|245->214|246->215|246->215|246->215|247->216|247->216|247->216|247->216|247->216|247->216|247->216|248->217|249->218|253->222|253->222|256->225|256->225|265->234|266->235|341->310|341->310|341->310|347->316|347->316|347->316|348->317|348->317|348->317|348->317|348->317|348->317|348->317|348->317|348->317|348->317|349->318|349->318|349->318|350->319|350->319|350->319|351->320|351->320|351->320|353->322|354->323|389->358|389->358|389->358|390->359|390->359|390->359|390->359|390->359|390->359|390->359|390->359|390->359|390->359|390->359|390->359|390->359|390->359|390->359|390->359|390->359|390->359|390->359|391->360|391->360|391->360|392->361|392->361|392->361|393->362|393->362|393->362|394->363|394->363|394->363|396->365|397->366|429->398|429->398|429->398|430->399|430->399|430->399|430->399|430->399|430->399|430->399|431->400|432->401|441->410|441->410|441->410|441->410|441->410|441->410|442->411|442->411|442->411|443->412|443->412|443->412|443->412|443->412|443->412|443->412|444->413|445->414|477->446|477->446|477->446|478->447|478->447|478->447|478->447|478->447|478->447|478->447|479->448|480->449|489->458|489->458|489->458|490->459|490->459|490->459|490->459|490->459|490->459|490->459|491->460|492->461|556->525|561->530|565->534|565->534|567->536|567->536|567->536|568->537|571->540|571->540|572->541|573->542|573->542|574->543|574->543|574->543|576->545|576->545|577->546|580->549|580->549|581->550|582->551|582->551|583->552|583->552|583->552|585->554|585->554|589->558|589->558|591->560|591->560|592->561|593->562|593->562|594->563|594->563|594->563|595->564|596->565|596->565|597->566|597->566|597->566|597->566|598->567|601->570|601->570|602->571|609->578|609->578|610->579|610->579|610->579|611->580|611->580|611->580|612->581|613->582|613->582|614->583|614->583|615->584|615->584|615->584|616->585|616->585|616->585|617->586|618->587|618->587|619->588|619->588|620->589|620->589|621->590|621->590|622->591|622->591|624->593|624->593|626->595|627->596|627->596|627->596|628->597|628->597|628->597|628->597|628->597|628->597|628->597|629->598|633->602|640->609|640->609|641->610|680->649|680->649|680->649|681->650|681->650|681->650|681->650|681->650|681->650|681->650|682->651|683->652|706->675|706->675|706->675|707->676|707->676|707->676|707->676|707->676|707->676|707->676|708->677|709->678|725->694|725->694|725->694|726->695|726->695|726->695|726->695|726->695|726->695|726->695|727->696|728->697|743->712|743->712|745->714|745->714|745->714|746->715|749->718|749->718|751->720|751->720|751->720|752->721|767->736|767->736|769->738|769->738|769->738|770->739|770->739|770->739|771->740|784->753|784->753|785->754|786->755|786->755|788->757|788->757|788->757|789->758|790->759|790->759|791->760|793->762|793->762|795->764|795->764|796->765|798->767|798->767|800->769|800->769|801->770|803->772|803->772|805->774|805->774|806->775|808->777|808->777|809->778|809->778|812->781|812->781|812->781|813->782|814->783|814->783|815->784|816->785|816->785|817->786|818->787|818->787|819->788|819->788|819->788|819->788|820->789|823->792|823->792|825->794|826->795|826->795|827->796|828->797|828->797|829->798|830->799|830->799|831->800|831->800|833->802|833->802|834->803|835->804|835->804|835->804|835->804|836->805|836->805|836->805|837->806|838->807|838->807|839->808|839->808|840->809|840->809|840->809|840->809|841->810|842->811|842->811|843->812|844->813|844->813|845->814|845->814|846->815|846->815|847->816|848->817|848->817|850->819|850->819|850->819|851->820|852->821|852->821|853->822|857->826|857->826|858->827|865->834|865->834|866->835|866->835|866->835|867->836|874->843|874->843|875->844|877->846|877->846|878->847|878->847|879->848|879->848|880->849|880->849|881->850|881->850|881->850|881->850|882->851|882->851|882->851|883->852|883->852|884->853|884->853|885->854|885->854|887->856|887->856|887->856|888->857|889->858|889->858|890->859|893->862|893->862|894->863|901->870|901->870|902->871|905->874|905->874|906->875|906->875|907->876|907->876|908->877|908->877|912->881|912->881|912->881|913->882|916->885|916->885|917->886|924->893|924->893|925->894|926->895|926->895|927->896|927->896|928->897|928->897|930->899|930->899|930->899|931->900|938->907|938->907|939->908|946->915|946->915|947->916|947->916|947->916|948->917|948->917|948->917|949->918|950->919|950->919|951->920|951->920|952->921|952->921|952->921|953->922|969->938|969->938|970->939|970->939|971->940|971->940|972->941|972->941|975->944|975->944|975->944|977->946|979->948|979->948|980->949|980->949|980->949|981->950|982->951|982->951|983->952|983->952|983->952|983->952|984->953|984->953|984->953|985->954|986->955|986->955|987->956|987->956|987->956|987->956|988->957|1003->972|1003->972|1004->973|1011->980|1011->980|1012->981|1012->981|1012->981|1013->982|1013->982|1013->982|1014->983|1015->984|1015->984|1016->985|1016->985|1016->985|1016->985|1017->986|1017->986|1017->986|1018->987|1019->988|1019->988|1020->989|1020->989|1020->989|1020->989|1022->991|1032->1001|1032->1001|1033->1002|1033->1002|1034->1003|1034->1003|1035->1004|1035->1004|1036->1005|1036->1005|1038->1007|1038->1007|1038->1007|1039->1008|1041->1010|1041->1010|1042->1011|1049->1018|1049->1018|1050->1019|1050->1019|1050->1019|1051->1020|1051->1020|1051->1020|1052->1021|1053->1022|1053->1022|1054->1023|1054->1023|1055->1024|1055->1024|1055->1024|1056->1025|1056->1025|1056->1025|1057->1026|1058->1027|1058->1027|1059->1028|1059->1028|1060->1029|1060->1029|1061->1030|1061->1030|1062->1031|1062->1031|1064->1033|1065->1034|1065->1034|1066->1035|1068->1037|1068->1037|1069->1038|1070->1039|1070->1039|1071->1040|1072->1041|1072->1041|1073->1042|1073->1042|1073->1042|1073->1042|1073->1042|1073->1042|1074->1043|1074->1043|1075->1044|1075->1044|1075->1044|1076->1045|1078->1047|1078->1047|1079->1048|1082->1051|1082->1051|1083->1052|1083->1052|1083->1052|1083->1052|1084->1053|1088->1057|1088->1057|1089->1058|1089->1058|1093->1062
                  -- GENERATED --
              */
          