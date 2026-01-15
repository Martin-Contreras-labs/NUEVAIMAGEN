
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

object compraImportIconstruye2 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template13[Map[String,String],Map[String,String],utilities.UserMnu,tables.Proveedor,List[List[String]],List[tables.Equipo],List[tables.Moneda],List[List[String]],List[tables.Moneda],List[tables.Grupo],List[tables.Fabrica],List[tables.Unidad],Long,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
proveedor: tables.Proveedor, detalleOC: List[List[String]],
listEquipo: List[tables.Equipo], listMoneda: List[tables.Moneda], listBodegas: List[List[String]], listMon: List[tables.Moneda],
listGrupos: List[tables.Grupo], listFabrica: List[tables.Fabrica], listUnidades: List[tables.Unidad], numeroDecimales: Long):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*5.1*/("""

    	
"""),_display_(/*8.2*/main("")/*8.10*/ {_display_(Seq[Any](format.raw/*8.12*/("""

"""),_display_(/*10.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*10.50*/("""
	
	
	"""),format.raw/*13.2*/("""<form action="/compraNuevo/" enctype="multipart/form-data" method="POST" onsubmit="return validarForm();">
		<div id="mostrarmostrar" style="display:none;">
			"""),_display_(/*15.5*/barraTitulo(mapDiccionario, "PROCESO IMPORTAR COMPRAS (DESDE ICONSTRUYE)","REVISAR y GRABAR ORDEN DE COMPRA")),format.raw/*15.114*/("""
			"""),format.raw/*16.4*/("""<div class="row  justify-content-md-center">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<table class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<tr>
								<td  width="350px">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">"""),_display_(/*24.65*/mapDiccionario/*24.79*/.get("RUT")),format.raw/*24.90*/(""" """),format.raw/*24.91*/("""PROVEEDOR</span>
								  		</div>
								  		<input type="hidden" name="id_factura" value="0">
								  		<input type="hidden" name="numOcIConstruye" value=""""),_display_(/*27.65*/detalleOC/*27.74*/.get(0).get(1)),format.raw/*27.88*/("""">
								  		<input type="hidden" id="id_proveedor" name="id_proveedor" value=""""),_display_(/*28.80*/proveedor/*28.89*/.id),format.raw/*28.92*/("""">
	  									<input type="text" class="form-control"
	  										value=""""),_display_(/*30.22*/proveedor/*30.31*/.rut),format.raw/*30.35*/("""" 
	  										style="background:white"
	  										readonly>
									</div>
								</td>
								<td>
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Nombre Proveedor</span>
								  		</div>
											<input type="text" class="form-control left"
												value=""""),_display_(/*41.21*/proveedor/*41.30*/.nickName),format.raw/*41.39*/(""""
												style="background:white"
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
												value = """"),_display_(/*53.23*/detalleOC/*53.32*/.get(0).get(0)),format.raw/*53.46*/(""""
												readonly>
									</div>
								</td>
								<td width="230px">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Fecha</span>
								  		</div>
								  		<input type="date" class="form-control center"
								  			name="fechaFactura"
								  			value=""""),_display_(/*64.22*/detalleOC/*64.31*/.get(0).get(19)),format.raw/*64.46*/(""""
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
	  										autocomplete="off">"""),_display_(/*83.34*/detalleOC/*83.43*/.get(0).get(5)),format.raw/*83.57*/("""</textarea>
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
						        <TH width="3%">UN</TH>
								<TH width="8%">CANTIDAD</TH>
								<TH width="3%">MONEDA</TH>
								<TH width="10%">P.UNITARIO<br>COMPRA</TH>
								<TH width="10%">TOTAL</TH>
								<TH width="4%">ASIGNA<br>P.LISTA</TH>
								<TH width="15%">DESTINO</TH>
								<TH width="1%">DEL</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*106.9*/for((lista1, index) <- detalleOC.zipWithIndex) yield /*106.55*/{_display_(Seq[Any](format.raw/*106.56*/("""
								"""),format.raw/*107.9*/("""<TR>
									<td  style="text-align:left;">
										<input type="hidden" name="id_equipo[]" value=""""),_display_(/*109.59*/lista1/*109.65*/.get(14)),format.raw/*109.73*/("""">
										"""),_display_(/*110.12*/lista1/*110.18*/.get(6)),format.raw/*110.25*/("""
									"""),format.raw/*111.10*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*112.41*/lista1/*112.47*/.get(7)),format.raw/*112.54*/("""</td>
									<td  style="text-align:center;">"""),_display_(/*113.43*/lista1/*113.49*/.get(9)),format.raw/*113.56*/("""</td>
									<td  style="text-align:center;">
										<input type="text" class="cantidad form-control right" 
											name="cantidad[]" 
											id="cantidad_"""),_display_(/*117.26*/index),format.raw/*117.31*/(""""
											value=""""),_display_(/*118.20*/lista1/*118.26*/.get(10)),format.raw/*118.34*/("""" 
											onfocus="value=value.replace(/,/g,'');" 
											onkeydown="return ingresoDouble(window.event)" 
											autocomplete="off" 
											onchange="if(value=='') value='"""),_display_(/*122.44*/lista1/*122.50*/.get(10)),format.raw/*122.58*/("""'; value=formatStandar(value,'"""),_display_(/*122.89*/index),format.raw/*122.94*/("""'); actualTotales(id);">
									</td>
									<td  style="text-align:center;">
										<select class="form-control form-control-sm" id="idMoneda_"""),_display_(/*125.70*/index),format.raw/*125.75*/("""" name="id_monedaCompra[]">
											<option value=""""),_display_(/*126.28*/lista1/*126.34*/.get(15)),format.raw/*126.42*/("""">"""),_display_(/*126.45*/lista1/*126.51*/.get(11)),format.raw/*126.59*/("""</option>
							            	"""),_display_(/*127.22*/for(mon <- listMoneda) yield /*127.44*/{_display_(Seq[Any](format.raw/*127.45*/("""
							                	"""),format.raw/*128.25*/("""<option value=""""),_display_(/*128.41*/mon/*128.44*/.getId()),format.raw/*128.52*/("""" >"""),_display_(/*128.56*/mon/*128.59*/.getNickName()),format.raw/*128.73*/("""</option>
											""")))}),format.raw/*129.13*/("""
						        		"""),format.raw/*130.17*/("""</select>
									</td>
									<td  style="text-align:center;">
										<input type="text" class="form-control right" 
											name="puCompra[]" 
											id="puCompra_"""),_display_(/*135.26*/index),format.raw/*135.31*/("""" 
											value=""""),_display_(/*136.20*/lista1/*136.26*/.get(16)),format.raw/*136.34*/("""" 
											onfocus="value=value.replace(/,/g,'');" 
											onkeydown="return ingresoDouble(window.event)" 
											autocomplete="off" 
											onchange="if(value=='') value=0; value=formatStandar(value,'"""),_display_(/*140.73*/index),format.raw/*140.78*/("""'); actualTotales(id);">
									</td>
									<td  style="text-align:center;">
										<div class="total" id="total_"""),_display_(/*143.41*/index),format.raw/*143.46*/("""" align="right">"""),_display_(/*143.63*/lista1/*143.69*/.get(13)),format.raw/*143.77*/("""</div>
									</td>
									<td style="text-align: center">
										<a href="#" onclick="muestraPrecios('"""),_display_(/*146.49*/lista1/*146.55*/.get(14)),format.raw/*146.63*/("""')">
											<kbd style="background-color: #CACFD2">Asignar</kbd>
										</a>
									</td>
									<td  style="text-align:center;">
										<select class="form-control form-control-sm" name="id_bodegaDestino[]">
											<option value="2">"""),_display_(/*152.31*/lista1/*152.37*/.get(17)),format.raw/*152.45*/("""</option><option value="1">
							            	"""),_display_(/*153.22*/for(bod <- listBodegas) yield /*153.45*/{_display_(Seq[Any](format.raw/*153.46*/("""
							                	"""),format.raw/*154.25*/("""<option value='"""),_display_(/*154.41*/bod/*154.44*/.get(1)),format.raw/*154.51*/("""'>"""),_display_(/*154.54*/bod/*154.57*/.get(5)),format.raw/*154.64*/("""</option>
											""")))}),format.raw/*155.13*/("""
						        		"""),format.raw/*156.17*/("""</select>
									</td>
									<td style="text-align: center;">
											<a href="#" onclick="eliminarFila(this, '"""),_display_(/*159.54*/lista1/*159.60*/.get(9)),format.raw/*159.67*/("""')">
												<kbd style="background-color: red">X</kbd>
											</a>
									</td>
								</TR>
				 			""")))}),format.raw/*164.10*/("""
				 			"""),format.raw/*165.9*/("""<TR>
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
							</TR>
				 			<TR style="background-color: #eeeeee">
						        <td></td>
								<td>TOTALES</td>
								<td></td>
								<td><div id="totalCantidad" align="right">0.00</div></td>
								<td></td>
								<td></td>
								<td><div id="totalTotal" align="right">0.00</div></td>
								<td></td>
								<td></td>
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
							"""),_display_(/*235.9*/for(lista1 <- listEquipo) yield /*235.34*/{_display_(Seq[Any](format.raw/*235.35*/("""
								"""),format.raw/*236.9*/("""<TR onClick="auxCodEquip='"""),_display_(/*236.36*/lista1/*236.42*/.getCodigo()),format.raw/*236.54*/("""'; auxNomEquip='"""),_display_(/*236.71*/lista1/*236.77*/.getNombre()),format.raw/*236.89*/("""'; auxKgEquip='"""),_display_(/*236.105*/lista1/*236.111*/.getKg()),format.raw/*236.119*/("""'; auxM2Equip='"""),_display_(/*236.135*/lista1/*236.141*/.getM2()),format.raw/*236.149*/("""'; auxUnEquip='"""),_display_(/*236.165*/lista1/*236.171*/.getUnidad()),format.raw/*236.183*/("""'; selectEquipo("""),_display_(/*236.200*/lista1/*236.206*/.getId()),format.raw/*236.214*/(""")" data-dismiss="modal">
									<td  style="text-align:left;">"""),_display_(/*237.41*/lista1/*237.47*/.getGrupo()),format.raw/*237.58*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*238.41*/lista1/*238.47*/.getCodigo()),format.raw/*238.59*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*239.41*/lista1/*239.47*/.getNombre()),format.raw/*239.59*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*240.41*/lista1/*240.47*/.getFabrica()),format.raw/*240.60*/("""</td>
								</TR>
				 			""")))}),format.raw/*242.10*/("""
						"""),format.raw/*243.7*/("""</tbody>
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
									"""),_display_(/*275.11*/for(lista <- listGrupos) yield /*275.35*/{_display_(Seq[Any](format.raw/*275.36*/("""
										"""),format.raw/*276.11*/("""<option value=""""),_display_(/*276.27*/lista/*276.32*/.getId()),format.raw/*276.40*/("""">"""),_display_(/*276.43*/lista/*276.48*/.getNombre()),format.raw/*276.60*/("""</option>
									""")))}),format.raw/*277.11*/("""
								"""),format.raw/*278.9*/("""</select>
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
									"""),_display_(/*310.11*/for(lista <- listFabrica) yield /*310.36*/{_display_(Seq[Any](format.raw/*310.37*/("""
										"""),format.raw/*311.11*/("""<option value=""""),_display_(/*311.27*/lista/*311.32*/.id),format.raw/*311.35*/("""">"""),_display_(/*311.38*/lista/*311.43*/.nickName),format.raw/*311.52*/("""</option>
									""")))}),format.raw/*312.11*/("""
								"""),format.raw/*313.9*/("""</select>
							</td>
						</tr>
						<tr>
							<td style="text-align:left; vertical-align:top">UNIDAD: </td>
							<td style="text-align:left; vertical-align:top">
								<select class="custom-select" 
									id="nuevoEquipoId_unidad"
									required>
									"""),_display_(/*322.11*/for(lista <- listUnidades) yield /*322.37*/{_display_(Seq[Any](format.raw/*322.38*/("""
										"""),format.raw/*323.11*/("""<option value=""""),_display_(/*323.27*/lista/*323.32*/.id),format.raw/*323.35*/("""">"""),_display_(/*323.38*/lista/*323.43*/.nombre),format.raw/*323.50*/("""</option>
									""")))}),format.raw/*324.11*/("""
								"""),format.raw/*325.9*/("""</select>
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
	
	
""")))}),format.raw/*388.2*/("""




"""),format.raw/*393.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*395.31*/("""{"""),format.raw/*395.32*/("""
		
		
		
		"""),format.raw/*399.3*/("""$('#tablaListaEquipos').DataTable("""),format.raw/*399.37*/("""{"""),format.raw/*399.38*/("""
	    	"""),format.raw/*400.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
	    	"order": [[ 1, "asc" ]],
	    	"language": """),format.raw/*403.19*/("""{"""),format.raw/*403.20*/("""
	        	"""),format.raw/*404.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*405.10*/("""}"""),format.raw/*405.11*/("""
	  	"""),format.raw/*406.5*/("""}"""),format.raw/*406.6*/(""" """),format.raw/*406.7*/(""");
		  
		  sumaTotales();
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*410.2*/("""}"""),format.raw/*410.3*/(""");
	
	
	
	let mapDec = new Map();
	"""),_display_(/*415.3*/for(lista <- listMon) yield /*415.24*/{_display_(Seq[Any](format.raw/*415.25*/("""
		"""),format.raw/*416.3*/("""mapDec.set('"""),_display_(/*416.16*/lista/*416.21*/.getId()),format.raw/*416.29*/("""','"""),_display_(/*416.33*/lista/*416.38*/.getNumeroDecimales()),format.raw/*416.59*/("""');
	""")))}),format.raw/*417.3*/("""
	

	
	"""),format.raw/*421.2*/("""let auxCodEquip = "";
	let auxNomEquip = "";
	let auxUnEquip = "";
	let cont = '"""),_display_(/*424.15*/detalleOC/*424.24*/.size()),format.raw/*424.31*/("""';
	
	const selectEquipo = (id_equipo) => """),format.raw/*426.38*/("""{"""),format.raw/*426.39*/("""
		"""),format.raw/*427.3*/("""cont++;
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
		cell.style.textAlign = "center";
		cell.innerHTML = auxUnEquip;
		
		cell = row.insertCell(3);
		cell.style.textAlign = "center";
		cell.innerHTML = "<input type=\"text\" class=\"cantidad form-control right\" "+
			"name=\"cantidad[]\" "+
			"id=\"cantidad_"+cont+"\" "+
			"value=\"0.00\" "+
			"onfocus=\"value=value.replace(/,/g,'');\" "+
			"onkeydown=\"return ingresoDouble(window.event)\" "+
			"autocomplete=\"off\" "+
			"onchange=\"if(value=='') value=0; value=formatStandar(value,2); actualTotales(id);\">";
		
		cell = row.insertCell(4);
		cell.style.textAlign = "center";
		let mon = "<select class=\"form-control form-control-sm\" id=\"idMoneda_"+cont+"\" name=\"id_monedaCompra[]\">";
    		"""),_display_(/*458.8*/for(mon <- listMoneda) yield /*458.30*/{_display_(Seq[Any](format.raw/*458.31*/("""
    			"""),format.raw/*459.8*/("""mon += "<option value='"""),_display_(/*459.32*/mon/*459.35*/.getId()),format.raw/*459.43*/("""'>"""),_display_(/*459.46*/mon/*459.49*/.getNickName()),format.raw/*459.63*/("""</option>";
			""")))}),format.raw/*460.5*/("""
    		"""),format.raw/*461.7*/("""mon += "</select>";
    	cell.innerHTML = mon;
	
		cell = row.insertCell(5);
		cell.style.textAlign = "center";
		cell.innerHTML = "<input type=\"text\" class=\"form-control right\" "+
			"name=\"puCompra[]\" "+
			"id=\"puCompra_"+cont+"\" "+
			"value=\"0\" "+
			"onfocus=\"value=value.replace(/,/g,'');\" "+ 
			"onkeydown=\"return ingresoDouble(window.event)\" "+
			"autocomplete=\"off\" "+
			"onchange=\"if(value=='') value=0; value=formatStandar(value,2); actualTotales(id);\"> ";
		
		cell = row.insertCell(6);
		cell.style.textAlign = "right";
		cell.innerHTML = "<div class=\"total\" id=\"total_"+cont+"\" align=\"right\">0.00</div>";
		
		cell = row.insertCell(7);
		cell.style.textAlign = "center";
		cell.innerHTML = "<a href=\"#\" onclick=\"muestraPrecios("+id_equipo+")\"> "+
			"<kbd style=\"background-color: #CACFD2\">Asignar</kbd></a>";
		
		cell = row.insertCell(8);
		cell.style.textAlign = "center";
		let des = "<select class=\"form-control form-control-sm\" name=\"id_bodegaDestino[]\">";
    		"""),_display_(/*487.8*/for(bod <- listBodegas) yield /*487.31*/{_display_(Seq[Any](format.raw/*487.32*/("""
    			"""),format.raw/*488.8*/("""des += "<option value='"""),_display_(/*488.32*/bod/*488.35*/.get(1)),format.raw/*488.42*/("""'>"""),_display_(/*488.45*/bod/*488.48*/.get(5)),format.raw/*488.55*/("""</option>";
			""")))}),format.raw/*489.5*/("""
    		"""),format.raw/*490.7*/("""des += "</select>";
		cell.innerHTML = des;
		
		cell = row.insertCell(9);
		cell.style.textAlign = "center";
		cell.innerHTML = "<a href=\"#\" onclick= \"eliminarFila(this)\"><kbd style=\"background-color: red\">X</kbd></a>";
		
	"""),format.raw/*497.2*/("""}"""),format.raw/*497.3*/("""
	
	"""),format.raw/*499.2*/("""const eliminarFila = (nodo) =>"""),format.raw/*499.32*/("""{"""),format.raw/*499.33*/("""
		"""),format.raw/*500.3*/("""let nodoTd = nodo.parentNode.parentNode; 
		$(nodoTd).remove();
		sumaTotales();
	"""),format.raw/*503.2*/("""}"""),format.raw/*503.3*/("""
	
	"""),format.raw/*505.2*/("""const actualTotales = (id) =>"""),format.raw/*505.31*/("""{"""),format.raw/*505.32*/("""
		"""),format.raw/*506.3*/("""let aux = id.split("_");
		let cantidad = $("#cantidad_"+aux[1]).val().replace(/,/g,'');
		let puCompra = $("#puCompra_"+aux[1]).val().replace(/,/g,'');
		let id_moneda = $("#idMoneda_"+aux[1]).val();
		let nroDecimal = mapDec.get(parseFloat(id_moneda));
		let total = parseFloat(cantidad) * parseFloat(puCompra);
		$("#puCompra_"+aux[1]).val(formatStandar(puCompra,nroDecimal));
		$("#total_"+aux[1]).text(formatStandar(total,nroDecimal));
		sumaTotales();
	"""),format.raw/*515.2*/("""}"""),format.raw/*515.3*/("""
	
	"""),format.raw/*517.2*/("""const sumaTotales = () => """),format.raw/*517.28*/("""{"""),format.raw/*517.29*/("""
		 """),format.raw/*518.4*/("""let sum = 0;
			$(".cantidad").each(function() """),format.raw/*519.35*/("""{"""),format.raw/*519.36*/("""
				"""),format.raw/*520.5*/("""let val = $(this).val().replace(/,/g,'');
				$(this).val(formatStandar(val,2));
				sum += parseFloat(val);
			"""),format.raw/*523.4*/("""}"""),format.raw/*523.5*/("""); 
			$("#totalCantidad").text(formatStandar(sum,2));
			
			sum = 0;
			$(".total").each(function() """),format.raw/*527.32*/("""{"""),format.raw/*527.33*/("""
				"""),format.raw/*528.5*/("""let val = $(this).text().replace(/,/g,'');
				$(this).text(formatStandar(val,'"""),_display_(/*529.38*/numeroDecimales),format.raw/*529.53*/("""'));
				sum += parseFloat(val);
			"""),format.raw/*531.4*/("""}"""),format.raw/*531.5*/(""");
			$("#totalTotal").text(formatStandar(sum,2));
	 """),format.raw/*533.3*/("""}"""),format.raw/*533.4*/("""
	
	
	"""),format.raw/*536.2*/("""const validarForm = () =>"""),format.raw/*536.27*/("""{"""),format.raw/*536.28*/("""
		"""),format.raw/*537.3*/("""let flag = true;
		if($("#id_proveedor").val()=="0")"""),format.raw/*538.36*/("""{"""),format.raw/*538.37*/("""
			"""),format.raw/*539.4*/("""flag = false;
			alertify.alert('FALTA SELECCIONAR PROVEEDOR', function () """),format.raw/*540.62*/("""{"""),format.raw/*540.63*/("""
				"""),format.raw/*541.5*/("""return(flag);
     		"""),format.raw/*542.8*/("""}"""),format.raw/*542.9*/(""");
		"""),format.raw/*543.3*/("""}"""),format.raw/*543.4*/("""else"""),format.raw/*543.8*/("""{"""),format.raw/*543.9*/("""
			"""),format.raw/*544.4*/("""$("#tablaPrincipal").dataTable().fnDestroy();
			let tabla = document.getElementById("tablaPrincipal");
			let cantTotal = $("#totalCantidad").text().replace(/,/g,'');
			if(tabla.rows.length > 3 && parseFloat(cantTotal) > 0)"""),format.raw/*547.58*/("""{"""),format.raw/*547.59*/("""
				"""),format.raw/*548.5*/("""return(true);
			"""),format.raw/*549.4*/("""}"""),format.raw/*549.5*/("""else"""),format.raw/*549.9*/("""{"""),format.raw/*549.10*/("""
				"""),format.raw/*550.5*/("""flag = false;
				alertify.alert('PARA GRABAR UNA COMPRA AL MENOS DEBE COMPRAR UN EQUIPOS', function () """),format.raw/*551.91*/("""{"""),format.raw/*551.92*/("""
					"""),format.raw/*552.6*/("""return(flag);
	     		"""),format.raw/*553.9*/("""}"""),format.raw/*553.10*/(""");
			"""),format.raw/*554.4*/("""}"""),format.raw/*554.5*/("""
		"""),format.raw/*555.3*/("""}"""),format.raw/*555.4*/("""
		"""),format.raw/*556.3*/("""return(flag);
	"""),format.raw/*557.2*/("""}"""),format.raw/*557.3*/("""
	
	"""),format.raw/*559.2*/("""const muestraPrecios = (id_equipo) => """),format.raw/*559.40*/("""{"""),format.raw/*559.41*/("""
		"""),format.raw/*560.3*/("""var formData = new FormData();
	  	formData.append('id_sucursal','1');
		formData.append('id_equipo',id_equipo);
        $.ajax("""),format.raw/*563.16*/("""{"""),format.raw/*563.17*/("""
            """),format.raw/*564.13*/("""url: "/modalPreciosAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*571.36*/("""{"""),format.raw/*571.37*/("""
	     		"""),format.raw/*572.9*/("""document.getElementById("modalPrecios").innerHTML =  respuesta;
	     		$("#modalPrecioLista").modal("show");
	     	"""),format.raw/*574.8*/("""}"""),format.raw/*574.9*/("""
        """),format.raw/*575.9*/("""}"""),format.raw/*575.10*/(""");
	"""),format.raw/*576.2*/("""}"""),format.raw/*576.3*/("""
	
	"""),format.raw/*578.2*/("""const cambiarPrecio = (id_equipo,campo,valor) => """),format.raw/*578.51*/("""{"""),format.raw/*578.52*/("""
		"""),format.raw/*579.3*/("""let id_moneda = $("#precioMoneda").val();
		var formData = new FormData();
		formData.append('id_equipo',id_equipo);
		formData.append('id_moneda',id_moneda);
		formData.append('campo',campo);
		formData.append('valor',valor);
		$.ajax("""),format.raw/*585.10*/("""{"""),format.raw/*585.11*/("""
            """),format.raw/*586.13*/("""url: "/actualizaPrecioAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*593.36*/("""{"""),format.raw/*593.37*/("""
	     		"""),format.raw/*594.9*/("""if(respuesta=="error")"""),format.raw/*594.31*/("""{"""),format.raw/*594.32*/("""
	     			"""),format.raw/*595.10*/("""alertify.alert(msgError, function () """),format.raw/*595.47*/("""{"""),format.raw/*595.48*/("""
		     			"""),format.raw/*596.11*/("""location.href = "/";
		     		"""),format.raw/*597.10*/("""}"""),format.raw/*597.11*/(""");
	     		"""),format.raw/*598.9*/("""}"""),format.raw/*598.10*/("""
	     		"""),format.raw/*599.9*/("""if(respuesta.status)"""),format.raw/*599.29*/("""{"""),format.raw/*599.30*/("""
	     			"""),format.raw/*600.10*/("""let nroDecimal = mapDec.get(parseFloat(id_moneda));
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
	     		"""),format.raw/*616.9*/("""}"""),format.raw/*616.10*/("""
	     	"""),format.raw/*617.8*/("""}"""),format.raw/*617.9*/("""
        """),format.raw/*618.9*/("""}"""),format.raw/*618.10*/(""");
	"""),format.raw/*619.2*/("""}"""),format.raw/*619.3*/("""
	

	
	"""),format.raw/*623.2*/("""const grabaNuevoEquipo = () =>"""),format.raw/*623.32*/("""{"""),format.raw/*623.33*/("""
		
		"""),format.raw/*625.3*/("""nuevoEquipoCodigo = $("#nuevoEquipoCodigo").val().trim();
		nuevoEquipoNombre = $("#nuevoEquipoNombre").val().trim();
		if(nuevoEquipoCodigo=="")"""),format.raw/*627.28*/("""{"""),format.raw/*627.29*/("""
			"""),format.raw/*628.4*/("""alertify.alert("Debe ingresar un código de equipo", function () """),format.raw/*628.68*/("""{"""),format.raw/*628.69*/("""
 				"""),format.raw/*629.6*/("""$("#nuevoEquipoCodigo").focus();
     		"""),format.raw/*630.8*/("""}"""),format.raw/*630.9*/(""");
		"""),format.raw/*631.3*/("""}"""),format.raw/*631.4*/("""else if(nuevoEquipoNombre=="")"""),format.raw/*631.34*/("""{"""),format.raw/*631.35*/("""
			"""),format.raw/*632.4*/("""alertify.alert("Debe ingresar un nombre de equipo", function () """),format.raw/*632.68*/("""{"""),format.raw/*632.69*/("""
 				"""),format.raw/*633.6*/("""$("#nuevoEquipoNombre").focus();
     		"""),format.raw/*634.8*/("""}"""),format.raw/*634.9*/(""");
		"""),format.raw/*635.3*/("""}"""),format.raw/*635.4*/("""else"""),format.raw/*635.8*/("""{"""),format.raw/*635.9*/("""
			"""),format.raw/*636.4*/("""$("#modalNuevoEquipo").modal('hide');
			let auxOpcion = document.getElementById("nuevoEquipoId_unidad");
			let auxEquipoUnidad = auxOpcion.options[auxOpcion.selectedIndex].text;
			
			var formData = new FormData();
			formData.append('id_grupo',$("#nuevoEquipoId_grupo").val());
			formData.append('codigo',$("#nuevoEquipoCodigo").val());
			formData.append('nombre',$("#nuevoEquipoNombre").val());
			formData.append('id_fabrica',$("#nuevoEquipoId_fabrica").val());
			formData.append('id_unidad',$("#nuevoEquipoId_unidad").val());
			formData.append('desdeMenu',"COMPRA_ICONSTRUYE");
			formData.append('kg',$("#nuevoEquipoId_kg").val().replace(/,/g,''));
			formData.append('m2',$("#nuevoEquipoId_m2").val().replace(/,/g,''));
			
			$.ajax("""),format.raw/*650.11*/("""{"""),format.raw/*650.12*/("""
	            """),format.raw/*651.14*/("""url: "/nuevoEquipoAjax/",
	            type: "POST",
	            method: "POST",
	            data: formData,
	            cache: false,
	            contentType: false,
		     	processData: false,
		     	success: function(respuesta)"""),format.raw/*658.37*/("""{"""),format.raw/*658.38*/("""
		     		"""),format.raw/*659.10*/("""if(respuesta=="existe")"""),format.raw/*659.33*/("""{"""),format.raw/*659.34*/("""
		     			"""),format.raw/*660.11*/("""alertify.alert("El código de equipo ya existe, intente con otro", function () """),format.raw/*660.89*/("""{"""),format.raw/*660.90*/("""
		     				"""),format.raw/*661.12*/("""$("#nuevoEquipoCodigo").val("");
			     		"""),format.raw/*662.11*/("""}"""),format.raw/*662.12*/(""");
		     		"""),format.raw/*663.10*/("""}"""),format.raw/*663.11*/("""else if(respuesta=="error")"""),format.raw/*663.38*/("""{"""),format.raw/*663.39*/("""
		     			"""),format.raw/*664.11*/("""alertify.alert(msgError, function () """),format.raw/*664.48*/("""{"""),format.raw/*664.49*/("""
			     			"""),format.raw/*665.12*/("""location.href = "/";
			     		"""),format.raw/*666.11*/("""}"""),format.raw/*666.12*/(""");
		     		"""),format.raw/*667.10*/("""}"""),format.raw/*667.11*/("""else"""),format.raw/*667.15*/("""{"""),format.raw/*667.16*/("""
		     			
		     			"""),format.raw/*669.11*/("""let rs = respuesta.split("_");
		     			auxCodEquip = $("#nuevoEquipoCodigo").val();
		     			auxNomEquip = $("#nuevoEquipoNombre").val();
		     			auxKgEquip = rs[1];
		     			auxM2Equip = rs[2];
		     			auxUnEquip = auxEquipoUnidad;
		     			selectEquipo(rs[0]);
		     			
		     			$("#nuevoEquipoCodigo").val("");
		     			$("#nuevoEquipoNombre").val("")
		     		"""),format.raw/*679.10*/("""}"""),format.raw/*679.11*/("""
		     	"""),format.raw/*680.9*/("""}"""),format.raw/*680.10*/("""
	        """),format.raw/*681.10*/("""}"""),format.raw/*681.11*/(""");
		"""),format.raw/*682.3*/("""}"""),format.raw/*682.4*/("""
	"""),format.raw/*683.2*/("""}"""),format.raw/*683.3*/("""
	
	"""),format.raw/*685.2*/("""const verificaCodigo = (codigo) => """),format.raw/*685.37*/("""{"""),format.raw/*685.38*/("""
		"""),format.raw/*686.3*/("""var formData = new FormData();
	  	formData.append('codigo',codigo);
        $.ajax("""),format.raw/*688.16*/("""{"""),format.raw/*688.17*/("""
            """),format.raw/*689.13*/("""url: "/verificaCodigoEquipoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*696.36*/("""{"""),format.raw/*696.37*/("""
	     		"""),format.raw/*697.9*/("""if(respuesta=="existe")"""),format.raw/*697.32*/("""{"""),format.raw/*697.33*/("""
	     			"""),format.raw/*698.10*/("""alertify.alert("El código de equipo ya existe, intente con otro", function () """),format.raw/*698.88*/("""{"""),format.raw/*698.89*/("""
	     				"""),format.raw/*699.11*/("""$("#nuevoEquipoCodigo").val("");
		     		"""),format.raw/*700.10*/("""}"""),format.raw/*700.11*/(""");
	     		"""),format.raw/*701.9*/("""}"""),format.raw/*701.10*/("""
	     		"""),format.raw/*702.9*/("""if(respuesta=="error")"""),format.raw/*702.31*/("""{"""),format.raw/*702.32*/("""
	     			"""),format.raw/*703.10*/("""alertify.alert(msgError, function () """),format.raw/*703.47*/("""{"""),format.raw/*703.48*/("""
		     			"""),format.raw/*704.11*/("""location.href = "/";
		     		"""),format.raw/*705.10*/("""}"""),format.raw/*705.11*/(""");
	     		"""),format.raw/*706.9*/("""}"""),format.raw/*706.10*/("""
	     	"""),format.raw/*707.8*/("""}"""),format.raw/*707.9*/("""
        """),format.raw/*708.9*/("""}"""),format.raw/*708.10*/(""");
	"""),format.raw/*709.2*/("""}"""),format.raw/*709.3*/("""
	

	"""),format.raw/*712.2*/("""let extArray = new Array(".xls", ".xlsx");
	const LimitAttach = (form, file) => """),format.raw/*713.38*/("""{"""),format.raw/*713.39*/("""
		"""),format.raw/*714.3*/("""let allowSubmit = false;
		if (!file) return;
		while (file.indexOf("\\") != -1)"""),format.raw/*716.35*/("""{"""),format.raw/*716.36*/("""
			"""),format.raw/*717.4*/("""file = file.slice(file.indexOf("\\") + 1);
		"""),format.raw/*718.3*/("""}"""),format.raw/*718.4*/("""
		"""),format.raw/*719.3*/("""let extencion = file.slice(file.lastIndexOf(".")).toLowerCase();
		for (var i = 0; i < extArray.length; i++) """),format.raw/*720.45*/("""{"""),format.raw/*720.46*/("""
			"""),format.raw/*721.4*/("""if (extArray[i] == extencion) """),format.raw/*721.34*/("""{"""),format.raw/*721.35*/(""" """),format.raw/*721.36*/("""allowSubmit = true; break; """),format.raw/*721.63*/("""}"""),format.raw/*721.64*/("""
		"""),format.raw/*722.3*/("""}"""),format.raw/*722.4*/("""
		"""),format.raw/*723.3*/("""if (allowSubmit) """),format.raw/*723.20*/("""{"""),format.raw/*723.21*/("""
			"""),format.raw/*724.4*/("""var file = $("#docAdjunto")[0].files[0];
			var fileSize = file.size; //tamaño del archivo
			if(fileSize>100000000)"""),format.raw/*726.26*/("""{"""),format.raw/*726.27*/("""
				"""),format.raw/*727.5*/("""alert("Se permiten únicamente archivos que no superen los 100 MB,"
				+" el que se intenta subir pesa: "+Math.round(file.size/10)/100+" KB");
				form.docAdjunto.value="";
			"""),format.raw/*730.4*/("""}"""),format.raw/*730.5*/("""
		"""),format.raw/*731.3*/("""}"""),format.raw/*731.4*/("""else"""),format.raw/*731.8*/("""{"""),format.raw/*731.9*/("""
			"""),format.raw/*732.4*/("""alert("Se permiten únicamente archivos con la extención: " 
			+ (extArray.join("  ")) + "\nPor favor, seleccione otro archivo "
			+ "e intente de nuevo.");
			form.docAdjunto.value="";
		"""),format.raw/*736.3*/("""}"""),format.raw/*736.4*/("""
	"""),format.raw/*737.2*/("""}"""),format.raw/*737.3*/("""
	
	


	
"""),format.raw/*743.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,proveedor:tables.Proveedor,detalleOC:List[List[String]],listEquipo:List[tables.Equipo],listMoneda:List[tables.Moneda],listBodegas:List[List[String]],listMon:List[tables.Moneda],listGrupos:List[tables.Grupo],listFabrica:List[tables.Fabrica],listUnidades:List[tables.Unidad],numeroDecimales:Long): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,proveedor,detalleOC,listEquipo,listMoneda,listBodegas,listMon,listGrupos,listFabrica,listUnidades,numeroDecimales)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.Proveedor,List[List[String]],List[tables.Equipo],List[tables.Moneda],List[List[String]],List[tables.Moneda],List[tables.Grupo],List[tables.Fabrica],List[tables.Unidad],Long) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,proveedor,detalleOC,listEquipo,listMoneda,listBodegas,listMon,listGrupos,listFabrica,listUnidades,numeroDecimales) => apply(mapDiccionario,mapPermiso,userMnu,proveedor,detalleOC,listEquipo,listMoneda,listBodegas,listMon,listGrupos,listFabrica,listUnidades,numeroDecimales)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCompras/compraImportIconstruye2.scala.html
                  HASH: b73e8a3458a40b45629ad34152edb986f559cd4d
                  MATRIX: 1203->1|1707->412|1741->421|1757->429|1796->431|1825->434|1894->482|1927->488|2114->649|2245->758|2276->762|2730->1189|2753->1203|2785->1214|2814->1215|3003->1377|3021->1386|3056->1400|3165->1482|3183->1491|3207->1494|3310->1570|3328->1579|3353->1583|3750->1953|3768->1962|3798->1971|4241->2387|4259->2396|4294->2410|4691->2780|4709->2789|4745->2804|5470->3502|5488->3511|5523->3525|6246->4221|6309->4267|6349->4268|6386->4277|6517->4380|6533->4386|6563->4394|6605->4408|6621->4414|6650->4421|6689->4431|6763->4477|6779->4483|6808->4490|6884->4538|6900->4544|6929->4551|7126->4720|7153->4725|7202->4746|7218->4752|7248->4760|7464->4948|7480->4954|7510->4962|7569->4993|7596->4998|7775->5149|7802->5154|7885->5209|7901->5215|7931->5223|7962->5226|7978->5232|8008->5240|8067->5271|8106->5293|8146->5294|8200->5319|8244->5335|8257->5338|8287->5346|8319->5350|8332->5353|8368->5367|8422->5389|8468->5406|8675->5585|8702->5590|8752->5612|8768->5618|8798->5626|9043->5843|9070->5848|9220->5970|9247->5975|9292->5992|9308->5998|9338->6006|9477->6117|9493->6123|9523->6131|9804->6384|9820->6390|9850->6398|9927->6447|9967->6470|10007->6471|10061->6496|10105->6512|10118->6515|10147->6522|10178->6525|10191->6528|10220->6535|10274->6557|10320->6574|10468->6694|10484->6700|10513->6707|10659->6821|10696->6830|13151->9258|13193->9283|13233->9284|13270->9293|13325->9320|13341->9326|13375->9338|13420->9355|13436->9361|13470->9373|13515->9389|13532->9395|13563->9403|13608->9419|13625->9425|13656->9433|13701->9449|13718->9455|13753->9467|13799->9484|13816->9490|13847->9498|13940->9563|13956->9569|13989->9580|14063->9626|14079->9632|14113->9644|14187->9690|14203->9696|14237->9708|14311->9754|14327->9760|14362->9773|14423->9802|14458->9809|15558->10881|15599->10905|15639->10906|15679->10917|15723->10933|15738->10938|15768->10946|15799->10949|15814->10954|15848->10966|15900->10986|15937->10995|17095->12125|17137->12150|17177->12151|17217->12162|17261->12178|17276->12183|17301->12186|17332->12189|17347->12194|17378->12203|17430->12223|17467->12232|17769->12506|17812->12532|17852->12533|17892->12544|17936->12560|17951->12565|17976->12568|18007->12571|18022->12576|18051->12583|18103->12603|18140->12612|20414->14855|20447->14860|20539->14923|20569->14924|20609->14936|20672->14970|20702->14971|20737->14978|20909->15121|20939->15122|20979->15133|21085->15210|21115->15211|21148->15216|21177->15217|21206->15218|21331->15315|21360->15316|21423->15352|21461->15373|21501->15374|21532->15377|21573->15390|21588->15395|21618->15403|21650->15407|21665->15412|21708->15433|21745->15439|21780->15446|21889->15527|21908->15536|21937->15543|22008->15585|22038->15586|22069->15589|23225->16718|23264->16740|23304->16741|23340->16749|23392->16773|23405->16776|23435->16784|23466->16787|23479->16790|23515->16804|23562->16820|23597->16827|24646->17849|24686->17872|24726->17873|24762->17881|24814->17905|24827->17908|24856->17915|24887->17918|24900->17921|24929->17928|24976->17944|25011->17951|25270->18182|25299->18183|25331->18187|25390->18217|25420->18218|25451->18221|25561->18303|25590->18304|25622->18308|25680->18337|25710->18338|25741->18341|26228->18800|26257->18801|26289->18805|26344->18831|26374->18832|26406->18836|26482->18883|26512->18884|26545->18889|26685->19001|26714->19002|26845->19104|26875->19105|26908->19110|27016->19190|27053->19205|27117->19241|27146->19242|27227->19295|27256->19296|27290->19302|27344->19327|27374->19328|27405->19331|27486->19383|27516->19384|27548->19388|27652->19463|27682->19464|27715->19469|27764->19490|27793->19491|27826->19496|27855->19497|27887->19501|27916->19502|27948->19506|28202->19731|28232->19732|28265->19737|28310->19754|28339->19755|28371->19759|28401->19760|28434->19765|28567->19869|28597->19870|28631->19876|28681->19898|28711->19899|28745->19905|28774->19906|28805->19909|28834->19910|28865->19913|28908->19928|28937->19929|28969->19933|29036->19971|29066->19972|29097->19975|29254->20103|29284->20104|29326->20117|29584->20346|29614->20347|29651->20356|29796->20473|29825->20474|29862->20483|29892->20484|29924->20488|29953->20489|29985->20493|30063->20542|30093->20543|30124->20546|30389->20782|30419->20783|30461->20796|30722->21028|30752->21029|30789->21038|30840->21060|30870->21061|30909->21071|30975->21108|31005->21109|31045->21120|31104->21150|31134->21151|31173->21162|31203->21163|31240->21172|31289->21192|31319->21193|31358->21203|32482->22299|32512->22300|32548->22308|32577->22309|32614->22318|32644->22319|32676->22323|32705->22324|32740->22331|32799->22361|32829->22362|32863->22368|33037->22513|33067->22514|33099->22518|33192->22582|33222->22583|33256->22589|33324->22629|33353->22630|33386->22635|33415->22636|33474->22666|33504->22667|33536->22671|33629->22735|33659->22736|33693->22742|33761->22782|33790->22783|33823->22788|33852->22789|33884->22793|33913->22794|33945->22798|34721->23545|34751->23546|34794->23560|35058->23795|35088->23796|35127->23806|35179->23829|35209->23830|35249->23841|35356->23919|35386->23920|35427->23932|35499->23975|35529->23976|35570->23988|35600->23989|35656->24016|35686->24017|35726->24028|35792->24065|35822->24066|35863->24078|35923->24109|35953->24110|35994->24122|36024->24123|36057->24127|36087->24128|36138->24150|36544->24527|36574->24528|36611->24537|36641->24538|36680->24548|36710->24549|36743->24554|36772->24555|36802->24557|36831->24558|36863->24562|36927->24597|36957->24598|36988->24601|37101->24685|37131->24686|37173->24699|37439->24936|37469->24937|37506->24946|37558->24969|37588->24970|37627->24980|37734->25058|37764->25059|37804->25070|37875->25112|37905->25113|37944->25124|37974->25125|38011->25134|38062->25156|38092->25157|38131->25167|38197->25204|38227->25205|38267->25216|38326->25246|38356->25247|38395->25258|38425->25259|38461->25267|38490->25268|38527->25277|38557->25278|38589->25282|38618->25283|38651->25288|38760->25368|38790->25369|38821->25372|38930->25452|38960->25453|38992->25457|39065->25502|39094->25503|39125->25506|39263->25615|39293->25616|39325->25620|39384->25650|39414->25651|39444->25652|39500->25679|39530->25680|39561->25683|39590->25684|39621->25687|39667->25704|39697->25705|39729->25709|39874->25825|39904->25826|39937->25831|40141->26007|40170->26008|40201->26011|40230->26012|40262->26016|40291->26017|40323->26021|40540->26210|40569->26211|40599->26213|40628->26214|40665->26223
                  LINES: 28->1|36->5|39->8|39->8|39->8|41->10|41->10|44->13|46->15|46->15|47->16|55->24|55->24|55->24|55->24|58->27|58->27|58->27|59->28|59->28|59->28|61->30|61->30|61->30|72->41|72->41|72->41|84->53|84->53|84->53|95->64|95->64|95->64|114->83|114->83|114->83|137->106|137->106|137->106|138->107|140->109|140->109|140->109|141->110|141->110|141->110|142->111|143->112|143->112|143->112|144->113|144->113|144->113|148->117|148->117|149->118|149->118|149->118|153->122|153->122|153->122|153->122|153->122|156->125|156->125|157->126|157->126|157->126|157->126|157->126|157->126|158->127|158->127|158->127|159->128|159->128|159->128|159->128|159->128|159->128|159->128|160->129|161->130|166->135|166->135|167->136|167->136|167->136|171->140|171->140|174->143|174->143|174->143|174->143|174->143|177->146|177->146|177->146|183->152|183->152|183->152|184->153|184->153|184->153|185->154|185->154|185->154|185->154|185->154|185->154|185->154|186->155|187->156|190->159|190->159|190->159|195->164|196->165|266->235|266->235|266->235|267->236|267->236|267->236|267->236|267->236|267->236|267->236|267->236|267->236|267->236|267->236|267->236|267->236|267->236|267->236|267->236|267->236|267->236|267->236|268->237|268->237|268->237|269->238|269->238|269->238|270->239|270->239|270->239|271->240|271->240|271->240|273->242|274->243|306->275|306->275|306->275|307->276|307->276|307->276|307->276|307->276|307->276|307->276|308->277|309->278|341->310|341->310|341->310|342->311|342->311|342->311|342->311|342->311|342->311|342->311|343->312|344->313|353->322|353->322|353->322|354->323|354->323|354->323|354->323|354->323|354->323|354->323|355->324|356->325|419->388|424->393|426->395|426->395|430->399|430->399|430->399|431->400|434->403|434->403|435->404|436->405|436->405|437->406|437->406|437->406|441->410|441->410|446->415|446->415|446->415|447->416|447->416|447->416|447->416|447->416|447->416|447->416|448->417|452->421|455->424|455->424|455->424|457->426|457->426|458->427|489->458|489->458|489->458|490->459|490->459|490->459|490->459|490->459|490->459|490->459|491->460|492->461|518->487|518->487|518->487|519->488|519->488|519->488|519->488|519->488|519->488|519->488|520->489|521->490|528->497|528->497|530->499|530->499|530->499|531->500|534->503|534->503|536->505|536->505|536->505|537->506|546->515|546->515|548->517|548->517|548->517|549->518|550->519|550->519|551->520|554->523|554->523|558->527|558->527|559->528|560->529|560->529|562->531|562->531|564->533|564->533|567->536|567->536|567->536|568->537|569->538|569->538|570->539|571->540|571->540|572->541|573->542|573->542|574->543|574->543|574->543|574->543|575->544|578->547|578->547|579->548|580->549|580->549|580->549|580->549|581->550|582->551|582->551|583->552|584->553|584->553|585->554|585->554|586->555|586->555|587->556|588->557|588->557|590->559|590->559|590->559|591->560|594->563|594->563|595->564|602->571|602->571|603->572|605->574|605->574|606->575|606->575|607->576|607->576|609->578|609->578|609->578|610->579|616->585|616->585|617->586|624->593|624->593|625->594|625->594|625->594|626->595|626->595|626->595|627->596|628->597|628->597|629->598|629->598|630->599|630->599|630->599|631->600|647->616|647->616|648->617|648->617|649->618|649->618|650->619|650->619|654->623|654->623|654->623|656->625|658->627|658->627|659->628|659->628|659->628|660->629|661->630|661->630|662->631|662->631|662->631|662->631|663->632|663->632|663->632|664->633|665->634|665->634|666->635|666->635|666->635|666->635|667->636|681->650|681->650|682->651|689->658|689->658|690->659|690->659|690->659|691->660|691->660|691->660|692->661|693->662|693->662|694->663|694->663|694->663|694->663|695->664|695->664|695->664|696->665|697->666|697->666|698->667|698->667|698->667|698->667|700->669|710->679|710->679|711->680|711->680|712->681|712->681|713->682|713->682|714->683|714->683|716->685|716->685|716->685|717->686|719->688|719->688|720->689|727->696|727->696|728->697|728->697|728->697|729->698|729->698|729->698|730->699|731->700|731->700|732->701|732->701|733->702|733->702|733->702|734->703|734->703|734->703|735->704|736->705|736->705|737->706|737->706|738->707|738->707|739->708|739->708|740->709|740->709|743->712|744->713|744->713|745->714|747->716|747->716|748->717|749->718|749->718|750->719|751->720|751->720|752->721|752->721|752->721|752->721|752->721|752->721|753->722|753->722|754->723|754->723|754->723|755->724|757->726|757->726|758->727|761->730|761->730|762->731|762->731|762->731|762->731|763->732|767->736|767->736|768->737|768->737|774->743
                  -- GENERATED --
              */
          