
package viewsMnuPlanes.html

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

object importaHojaVidaIconstruye2 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template13[Map[String,String],Map[String,String],utilities.UserMnu,tables.Proveedor,List[List[String]],List[tables.Equipo],List[tables.Moneda],List[List[String]],List[tables.Moneda],List[tables.Grupo],List[tables.Fabrica],List[tables.Unidad],Long,play.twirl.api.HtmlFormat.Appendable] {

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
								  			value=""""),_display_(/*64.22*/detalleOC/*64.31*/.get(0).get(2)),format.raw/*64.45*/(""""
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
		formData.append('id_sucursal','1');
		formData.append('id_equipo',id_equipo);
		formData.append('id_moneda',id_moneda);
		formData.append('campo',campo);
		formData.append('valor',valor);
		$.ajax("""),format.raw/*586.10*/("""{"""),format.raw/*586.11*/("""
            """),format.raw/*587.13*/("""url: "/actualizaPrecioAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*594.36*/("""{"""),format.raw/*594.37*/("""
	     		"""),format.raw/*595.9*/("""if(respuesta=="error")"""),format.raw/*595.31*/("""{"""),format.raw/*595.32*/("""
	     			"""),format.raw/*596.10*/("""alertify.alert(msgError, function () """),format.raw/*596.47*/("""{"""),format.raw/*596.48*/("""
		     			"""),format.raw/*597.11*/("""location.href = "/";
		     		"""),format.raw/*598.10*/("""}"""),format.raw/*598.11*/(""");
	     		"""),format.raw/*599.9*/("""}"""),format.raw/*599.10*/("""
	     		"""),format.raw/*600.9*/("""if(respuesta.status)"""),format.raw/*600.29*/("""{"""),format.raw/*600.30*/("""
	     			"""),format.raw/*601.10*/("""let nroDecimal = mapDec.get(parseFloat(id_moneda));
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
	     		"""),format.raw/*617.9*/("""}"""),format.raw/*617.10*/("""
	     	"""),format.raw/*618.8*/("""}"""),format.raw/*618.9*/("""
        """),format.raw/*619.9*/("""}"""),format.raw/*619.10*/(""");
	"""),format.raw/*620.2*/("""}"""),format.raw/*620.3*/("""
	

	
	"""),format.raw/*624.2*/("""const grabaNuevoEquipo = () =>"""),format.raw/*624.32*/("""{"""),format.raw/*624.33*/("""
		
		"""),format.raw/*626.3*/("""nuevoEquipoCodigo = $("#nuevoEquipoCodigo").val().trim();
		nuevoEquipoNombre = $("#nuevoEquipoNombre").val().trim();
		if(nuevoEquipoCodigo=="")"""),format.raw/*628.28*/("""{"""),format.raw/*628.29*/("""
			"""),format.raw/*629.4*/("""alertify.alert("Debe ingresar un código de equipo", function () """),format.raw/*629.68*/("""{"""),format.raw/*629.69*/("""
 				"""),format.raw/*630.6*/("""$("#nuevoEquipoCodigo").focus();
     		"""),format.raw/*631.8*/("""}"""),format.raw/*631.9*/(""");
		"""),format.raw/*632.3*/("""}"""),format.raw/*632.4*/("""else if(nuevoEquipoNombre=="")"""),format.raw/*632.34*/("""{"""),format.raw/*632.35*/("""
			"""),format.raw/*633.4*/("""alertify.alert("Debe ingresar un nombre de equipo", function () """),format.raw/*633.68*/("""{"""),format.raw/*633.69*/("""
 				"""),format.raw/*634.6*/("""$("#nuevoEquipoNombre").focus();
     		"""),format.raw/*635.8*/("""}"""),format.raw/*635.9*/(""");
		"""),format.raw/*636.3*/("""}"""),format.raw/*636.4*/("""else"""),format.raw/*636.8*/("""{"""),format.raw/*636.9*/("""
			"""),format.raw/*637.4*/("""$("#modalNuevoEquipo").modal('hide');
			let auxOpcion = document.getElementById("nuevoEquipoId_unidad");
			let auxEquipoUnidad = auxOpcion.options[auxOpcion.selectedIndex].text;
			
			var formData = new FormData();
			formData.append('id_grupo',$("#nuevoEquipoId_grupo").val());
			formData.append('codigo',$("#nuevoEquipoCodigo").val());
			formData.append('nombre',$("#nuevoEquipoNombre").val());
			formData.append('id_fabrica',$("#nuevoEquipoId_fabrica").val());
			formData.append('id_unidad',$("#nuevoEquipoId_unidad").val());
			formData.append('desdeMenu',"PLANES_ICONSTRUYE");
			formData.append('kg',$("#nuevoEquipoId_kg").val().replace(/,/g,''));
			formData.append('m2',$("#nuevoEquipoId_m2").val().replace(/,/g,''));
			
			$.ajax("""),format.raw/*651.11*/("""{"""),format.raw/*651.12*/("""
	            """),format.raw/*652.14*/("""url: "/nuevoEquipoAjax/",
	            type: "POST",
	            method: "POST",
	            data: formData,
	            cache: false,
	            contentType: false,
		     	processData: false,
		     	success: function(respuesta)"""),format.raw/*659.37*/("""{"""),format.raw/*659.38*/("""
		     		"""),format.raw/*660.10*/("""if(respuesta=="existe")"""),format.raw/*660.33*/("""{"""),format.raw/*660.34*/("""
		     			"""),format.raw/*661.11*/("""alertify.alert("El código de equipo ya existe, intente con otro", function () """),format.raw/*661.89*/("""{"""),format.raw/*661.90*/("""
		     				"""),format.raw/*662.12*/("""$("#nuevoEquipoCodigo").val("");
			     		"""),format.raw/*663.11*/("""}"""),format.raw/*663.12*/(""");
		     		"""),format.raw/*664.10*/("""}"""),format.raw/*664.11*/("""else if(respuesta=="error")"""),format.raw/*664.38*/("""{"""),format.raw/*664.39*/("""
		     			"""),format.raw/*665.11*/("""alertify.alert(msgError, function () """),format.raw/*665.48*/("""{"""),format.raw/*665.49*/("""
			     			"""),format.raw/*666.12*/("""location.href = "/";
			     		"""),format.raw/*667.11*/("""}"""),format.raw/*667.12*/(""");
		     		"""),format.raw/*668.10*/("""}"""),format.raw/*668.11*/("""else"""),format.raw/*668.15*/("""{"""),format.raw/*668.16*/("""
		     			
		     			"""),format.raw/*670.11*/("""let rs = respuesta.split("_");
		     			auxCodEquip = $("#nuevoEquipoCodigo").val();
		     			auxNomEquip = $("#nuevoEquipoNombre").val();
		     			auxKgEquip = rs[1];
		     			auxM2Equip = rs[2];
		     			auxUnEquip = auxEquipoUnidad;
		     			selectEquipo(rs[0]);
		     			
		     			$("#nuevoEquipoCodigo").val("");
		     			$("#nuevoEquipoNombre").val("")
		     		"""),format.raw/*680.10*/("""}"""),format.raw/*680.11*/("""
		     	"""),format.raw/*681.9*/("""}"""),format.raw/*681.10*/("""
	        """),format.raw/*682.10*/("""}"""),format.raw/*682.11*/(""");
		"""),format.raw/*683.3*/("""}"""),format.raw/*683.4*/("""
	"""),format.raw/*684.2*/("""}"""),format.raw/*684.3*/("""
	
	"""),format.raw/*686.2*/("""const verificaCodigo = (codigo) => """),format.raw/*686.37*/("""{"""),format.raw/*686.38*/("""
		"""),format.raw/*687.3*/("""var formData = new FormData();
	  	formData.append('codigo',codigo);
        $.ajax("""),format.raw/*689.16*/("""{"""),format.raw/*689.17*/("""
            """),format.raw/*690.13*/("""url: "/verificaCodigoEquipoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*697.36*/("""{"""),format.raw/*697.37*/("""
	     		"""),format.raw/*698.9*/("""if(respuesta=="existe")"""),format.raw/*698.32*/("""{"""),format.raw/*698.33*/("""
	     			"""),format.raw/*699.10*/("""alertify.alert("El código de equipo ya existe, intente con otro", function () """),format.raw/*699.88*/("""{"""),format.raw/*699.89*/("""
	     				"""),format.raw/*700.11*/("""$("#nuevoEquipoCodigo").val("");
		     		"""),format.raw/*701.10*/("""}"""),format.raw/*701.11*/(""");
	     		"""),format.raw/*702.9*/("""}"""),format.raw/*702.10*/("""
	     		"""),format.raw/*703.9*/("""if(respuesta=="error")"""),format.raw/*703.31*/("""{"""),format.raw/*703.32*/("""
	     			"""),format.raw/*704.10*/("""alertify.alert(msgError, function () """),format.raw/*704.47*/("""{"""),format.raw/*704.48*/("""
		     			"""),format.raw/*705.11*/("""location.href = "/";
		     		"""),format.raw/*706.10*/("""}"""),format.raw/*706.11*/(""");
	     		"""),format.raw/*707.9*/("""}"""),format.raw/*707.10*/("""
	     	"""),format.raw/*708.8*/("""}"""),format.raw/*708.9*/("""
        """),format.raw/*709.9*/("""}"""),format.raw/*709.10*/(""");
	"""),format.raw/*710.2*/("""}"""),format.raw/*710.3*/("""
	

	"""),format.raw/*713.2*/("""let extArray = new Array( ".xls", ".xlsx");
	const LimitAttach = (form, file) => """),format.raw/*714.38*/("""{"""),format.raw/*714.39*/("""
		"""),format.raw/*715.3*/("""let allowSubmit = false;
		if (!file) return;
		while (file.indexOf("\\") != -1)"""),format.raw/*717.35*/("""{"""),format.raw/*717.36*/("""
			"""),format.raw/*718.4*/("""file = file.slice(file.indexOf("\\") + 1);
		"""),format.raw/*719.3*/("""}"""),format.raw/*719.4*/("""
		"""),format.raw/*720.3*/("""let extencion = file.slice(file.lastIndexOf(".")).toLowerCase();
		for (var i = 0; i < extArray.length; i++) """),format.raw/*721.45*/("""{"""),format.raw/*721.46*/("""
			"""),format.raw/*722.4*/("""if (extArray[i] == extencion) """),format.raw/*722.34*/("""{"""),format.raw/*722.35*/(""" """),format.raw/*722.36*/("""allowSubmit = true; break; """),format.raw/*722.63*/("""}"""),format.raw/*722.64*/("""
		"""),format.raw/*723.3*/("""}"""),format.raw/*723.4*/("""
		"""),format.raw/*724.3*/("""if (allowSubmit) """),format.raw/*724.20*/("""{"""),format.raw/*724.21*/("""
			"""),format.raw/*725.4*/("""var file = $("#docAdjunto")[0].files[0];
			var fileSize = file.size; //tamaño del archivo
			if(fileSize>100000000)"""),format.raw/*727.26*/("""{"""),format.raw/*727.27*/("""
				"""),format.raw/*728.5*/("""alert("Se permiten únicamente archivos que no superen los 100 MB,"
				+" el que se intenta subir pesa: "+Math.round(file.size/10)/100+" KB");
				form.docAdjunto.value="";
			"""),format.raw/*731.4*/("""}"""),format.raw/*731.5*/("""
		"""),format.raw/*732.3*/("""}"""),format.raw/*732.4*/("""else"""),format.raw/*732.8*/("""{"""),format.raw/*732.9*/("""
			"""),format.raw/*733.4*/("""alert("Se permiten únicamente archivos con la extención: " 
			+ (extArray.join("  ")) + "\nPor favor, seleccione otro archivo "
			+ "e intente de nuevo.");
			form.docAdjunto.value="";
		"""),format.raw/*737.3*/("""}"""),format.raw/*737.4*/("""
	"""),format.raw/*738.2*/("""}"""),format.raw/*738.3*/("""
	
	


	
"""),format.raw/*744.1*/("""</script>


		
		
	
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
                  SOURCE: app/viewsMnuPlanes/importaHojaVidaIconstruye2.scala.html
                  HASH: a6981eb6baf2419f989f9fd0edabcbfeead429d2
                  MATRIX: 1205->1|1709->412|1743->421|1759->429|1798->431|1827->434|1896->482|1929->488|2116->649|2247->758|2278->762|2732->1189|2755->1203|2787->1214|2816->1215|3005->1377|3023->1386|3058->1400|3167->1482|3185->1491|3209->1494|3312->1570|3330->1579|3355->1583|3752->1953|3770->1962|3800->1971|4243->2387|4261->2396|4296->2410|4693->2780|4711->2789|4746->2803|5471->3501|5489->3510|5524->3524|6247->4220|6310->4266|6350->4267|6387->4276|6518->4379|6534->4385|6564->4393|6606->4407|6622->4413|6651->4420|6690->4430|6764->4476|6780->4482|6809->4489|6885->4537|6901->4543|6930->4550|7127->4719|7154->4724|7203->4745|7219->4751|7249->4759|7465->4947|7481->4953|7511->4961|7570->4992|7597->4997|7776->5148|7803->5153|7886->5208|7902->5214|7932->5222|7963->5225|7979->5231|8009->5239|8068->5270|8107->5292|8147->5293|8201->5318|8245->5334|8258->5337|8288->5345|8320->5349|8333->5352|8369->5366|8423->5388|8469->5405|8676->5584|8703->5589|8753->5611|8769->5617|8799->5625|9044->5842|9071->5847|9221->5969|9248->5974|9293->5991|9309->5997|9339->6005|9478->6116|9494->6122|9524->6130|9805->6383|9821->6389|9851->6397|9928->6446|9968->6469|10008->6470|10062->6495|10106->6511|10119->6514|10148->6521|10179->6524|10192->6527|10221->6534|10275->6556|10321->6573|10469->6693|10485->6699|10514->6706|10660->6820|10697->6829|13152->9257|13194->9282|13234->9283|13271->9292|13326->9319|13342->9325|13376->9337|13421->9354|13437->9360|13471->9372|13516->9388|13533->9394|13564->9402|13609->9418|13626->9424|13657->9432|13702->9448|13719->9454|13754->9466|13800->9483|13817->9489|13848->9497|13941->9562|13957->9568|13990->9579|14064->9625|14080->9631|14114->9643|14188->9689|14204->9695|14238->9707|14312->9753|14328->9759|14363->9772|14424->9801|14459->9808|15559->10880|15600->10904|15640->10905|15680->10916|15724->10932|15739->10937|15769->10945|15800->10948|15815->10953|15849->10965|15901->10985|15938->10994|17096->12124|17138->12149|17178->12150|17218->12161|17262->12177|17277->12182|17302->12185|17333->12188|17348->12193|17379->12202|17431->12222|17468->12231|17770->12505|17813->12531|17853->12532|17893->12543|17937->12559|17952->12564|17977->12567|18008->12570|18023->12575|18052->12582|18104->12602|18141->12611|20415->14854|20448->14859|20540->14922|20570->14923|20610->14935|20673->14969|20703->14970|20738->14977|20910->15120|20940->15121|20980->15132|21086->15209|21116->15210|21149->15215|21178->15216|21207->15217|21332->15314|21361->15315|21424->15351|21462->15372|21502->15373|21533->15376|21574->15389|21589->15394|21619->15402|21651->15406|21666->15411|21709->15432|21746->15438|21781->15445|21890->15526|21909->15535|21938->15542|22009->15584|22039->15585|22070->15588|23226->16717|23265->16739|23305->16740|23341->16748|23393->16772|23406->16775|23436->16783|23467->16786|23480->16789|23516->16803|23563->16819|23598->16826|24647->17848|24687->17871|24727->17872|24763->17880|24815->17904|24828->17907|24857->17914|24888->17917|24901->17920|24930->17927|24977->17943|25012->17950|25271->18181|25300->18182|25332->18186|25391->18216|25421->18217|25452->18220|25562->18302|25591->18303|25623->18307|25681->18336|25711->18337|25742->18340|26229->18799|26258->18800|26290->18804|26345->18830|26375->18831|26407->18835|26483->18882|26513->18883|26546->18888|26686->19000|26715->19001|26846->19103|26876->19104|26909->19109|27017->19189|27054->19204|27118->19240|27147->19241|27228->19294|27257->19295|27291->19301|27345->19326|27375->19327|27406->19330|27487->19382|27517->19383|27549->19387|27653->19462|27683->19463|27716->19468|27765->19489|27794->19490|27827->19495|27856->19496|27888->19500|27917->19501|27949->19505|28203->19730|28233->19731|28266->19736|28311->19753|28340->19754|28372->19758|28402->19759|28435->19764|28568->19868|28598->19869|28632->19875|28682->19897|28712->19898|28746->19904|28775->19905|28806->19908|28835->19909|28866->19912|28909->19927|28938->19928|28970->19932|29037->19970|29067->19971|29098->19974|29255->20102|29285->20103|29327->20116|29585->20345|29615->20346|29652->20355|29797->20472|29826->20473|29863->20482|29893->20483|29925->20487|29954->20488|29986->20492|30064->20541|30094->20542|30125->20545|30428->20819|30458->20820|30500->20833|30761->21065|30791->21066|30828->21075|30879->21097|30909->21098|30948->21108|31014->21145|31044->21146|31084->21157|31143->21187|31173->21188|31212->21199|31242->21200|31279->21209|31328->21229|31358->21230|31397->21240|32521->22336|32551->22337|32587->22345|32616->22346|32653->22355|32683->22356|32715->22360|32744->22361|32779->22368|32838->22398|32868->22399|32902->22405|33076->22550|33106->22551|33138->22555|33231->22619|33261->22620|33295->22626|33363->22666|33392->22667|33425->22672|33454->22673|33513->22703|33543->22704|33575->22708|33668->22772|33698->22773|33732->22779|33800->22819|33829->22820|33862->22825|33891->22826|33923->22830|33952->22831|33984->22835|34760->23582|34790->23583|34833->23597|35097->23832|35127->23833|35166->23843|35218->23866|35248->23867|35288->23878|35395->23956|35425->23957|35466->23969|35538->24012|35568->24013|35609->24025|35639->24026|35695->24053|35725->24054|35765->24065|35831->24102|35861->24103|35902->24115|35962->24146|35992->24147|36033->24159|36063->24160|36096->24164|36126->24165|36177->24187|36583->24564|36613->24565|36650->24574|36680->24575|36719->24585|36749->24586|36782->24591|36811->24592|36841->24594|36870->24595|36902->24599|36966->24634|36996->24635|37027->24638|37140->24722|37170->24723|37212->24736|37478->24973|37508->24974|37545->24983|37597->25006|37627->25007|37666->25017|37773->25095|37803->25096|37843->25107|37914->25149|37944->25150|37983->25161|38013->25162|38050->25171|38101->25193|38131->25194|38170->25204|38236->25241|38266->25242|38306->25253|38365->25283|38395->25284|38434->25295|38464->25296|38500->25304|38529->25305|38566->25314|38596->25315|38628->25319|38657->25320|38690->25325|38800->25406|38830->25407|38861->25410|38970->25490|39000->25491|39032->25495|39105->25540|39134->25541|39165->25544|39303->25653|39333->25654|39365->25658|39424->25688|39454->25689|39484->25690|39540->25717|39570->25718|39601->25721|39630->25722|39661->25725|39707->25742|39737->25743|39769->25747|39914->25863|39944->25864|39977->25869|40181->26045|40210->26046|40241->26049|40270->26050|40302->26054|40331->26055|40363->26059|40580->26248|40609->26249|40639->26251|40668->26252|40705->26261
                  LINES: 28->1|36->5|39->8|39->8|39->8|41->10|41->10|44->13|46->15|46->15|47->16|55->24|55->24|55->24|55->24|58->27|58->27|58->27|59->28|59->28|59->28|61->30|61->30|61->30|72->41|72->41|72->41|84->53|84->53|84->53|95->64|95->64|95->64|114->83|114->83|114->83|137->106|137->106|137->106|138->107|140->109|140->109|140->109|141->110|141->110|141->110|142->111|143->112|143->112|143->112|144->113|144->113|144->113|148->117|148->117|149->118|149->118|149->118|153->122|153->122|153->122|153->122|153->122|156->125|156->125|157->126|157->126|157->126|157->126|157->126|157->126|158->127|158->127|158->127|159->128|159->128|159->128|159->128|159->128|159->128|159->128|160->129|161->130|166->135|166->135|167->136|167->136|167->136|171->140|171->140|174->143|174->143|174->143|174->143|174->143|177->146|177->146|177->146|183->152|183->152|183->152|184->153|184->153|184->153|185->154|185->154|185->154|185->154|185->154|185->154|185->154|186->155|187->156|190->159|190->159|190->159|195->164|196->165|266->235|266->235|266->235|267->236|267->236|267->236|267->236|267->236|267->236|267->236|267->236|267->236|267->236|267->236|267->236|267->236|267->236|267->236|267->236|267->236|267->236|267->236|268->237|268->237|268->237|269->238|269->238|269->238|270->239|270->239|270->239|271->240|271->240|271->240|273->242|274->243|306->275|306->275|306->275|307->276|307->276|307->276|307->276|307->276|307->276|307->276|308->277|309->278|341->310|341->310|341->310|342->311|342->311|342->311|342->311|342->311|342->311|342->311|343->312|344->313|353->322|353->322|353->322|354->323|354->323|354->323|354->323|354->323|354->323|354->323|355->324|356->325|419->388|424->393|426->395|426->395|430->399|430->399|430->399|431->400|434->403|434->403|435->404|436->405|436->405|437->406|437->406|437->406|441->410|441->410|446->415|446->415|446->415|447->416|447->416|447->416|447->416|447->416|447->416|447->416|448->417|452->421|455->424|455->424|455->424|457->426|457->426|458->427|489->458|489->458|489->458|490->459|490->459|490->459|490->459|490->459|490->459|490->459|491->460|492->461|518->487|518->487|518->487|519->488|519->488|519->488|519->488|519->488|519->488|519->488|520->489|521->490|528->497|528->497|530->499|530->499|530->499|531->500|534->503|534->503|536->505|536->505|536->505|537->506|546->515|546->515|548->517|548->517|548->517|549->518|550->519|550->519|551->520|554->523|554->523|558->527|558->527|559->528|560->529|560->529|562->531|562->531|564->533|564->533|567->536|567->536|567->536|568->537|569->538|569->538|570->539|571->540|571->540|572->541|573->542|573->542|574->543|574->543|574->543|574->543|575->544|578->547|578->547|579->548|580->549|580->549|580->549|580->549|581->550|582->551|582->551|583->552|584->553|584->553|585->554|585->554|586->555|586->555|587->556|588->557|588->557|590->559|590->559|590->559|591->560|594->563|594->563|595->564|602->571|602->571|603->572|605->574|605->574|606->575|606->575|607->576|607->576|609->578|609->578|609->578|610->579|617->586|617->586|618->587|625->594|625->594|626->595|626->595|626->595|627->596|627->596|627->596|628->597|629->598|629->598|630->599|630->599|631->600|631->600|631->600|632->601|648->617|648->617|649->618|649->618|650->619|650->619|651->620|651->620|655->624|655->624|655->624|657->626|659->628|659->628|660->629|660->629|660->629|661->630|662->631|662->631|663->632|663->632|663->632|663->632|664->633|664->633|664->633|665->634|666->635|666->635|667->636|667->636|667->636|667->636|668->637|682->651|682->651|683->652|690->659|690->659|691->660|691->660|691->660|692->661|692->661|692->661|693->662|694->663|694->663|695->664|695->664|695->664|695->664|696->665|696->665|696->665|697->666|698->667|698->667|699->668|699->668|699->668|699->668|701->670|711->680|711->680|712->681|712->681|713->682|713->682|714->683|714->683|715->684|715->684|717->686|717->686|717->686|718->687|720->689|720->689|721->690|728->697|728->697|729->698|729->698|729->698|730->699|730->699|730->699|731->700|732->701|732->701|733->702|733->702|734->703|734->703|734->703|735->704|735->704|735->704|736->705|737->706|737->706|738->707|738->707|739->708|739->708|740->709|740->709|741->710|741->710|744->713|745->714|745->714|746->715|748->717|748->717|749->718|750->719|750->719|751->720|752->721|752->721|753->722|753->722|753->722|753->722|753->722|753->722|754->723|754->723|755->724|755->724|755->724|756->725|758->727|758->727|759->728|762->731|762->731|763->732|763->732|763->732|763->732|764->733|768->737|768->737|769->738|769->738|775->744
                  -- GENERATED --
              */
          