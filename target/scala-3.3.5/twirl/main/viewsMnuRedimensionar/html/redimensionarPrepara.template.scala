
package viewsMnuRedimensionar.html

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

object redimensionarPrepara extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template12[Map[String,String],Map[String,String],utilities.UserMnu,Long,List[List[String]],String,List[tables.Equipo],List[tables.Grupo],List[tables.Fabrica],List[tables.Unidad],String,Long,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
numeroActaRedimensionar: Long, listEquipBodOrigen: List[List[String]], fecha: String,
listEquipos: List[tables.Equipo], listGrupos: List[tables.Grupo], listFabrica: List[tables.Fabrica], listUnidades: List[tables.Unidad],
optBodegas: String, id_bodegaOrigen: Long):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*5.1*/("""
    	
"""),_display_(/*7.2*/main("")/*7.10*/ {_display_(Seq[Any](format.raw/*7.12*/("""

"""),_display_(/*9.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*9.50*/("""
	"""),format.raw/*10.2*/("""<form action="/routes2/redimensionarNuevo/" enctype="multipart/form-data" method="POST" onsubmit="return validarForm();">
		<input type="hidden" name="id_bodegaOrigen" value=""""),_display_(/*11.55*/id_bodegaOrigen),format.raw/*11.70*/("""">
		<div id="mostrarmostrar" style="display:none;">
			"""),_display_(/*13.5*/barraTitulo(mapDiccionario, "PREPARAR ACTA PARA REDIMENSIONAR","")),format.raw/*13.71*/("""
			"""),format.raw/*14.4*/("""<div class="row  justify-content-md-center">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<table class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<tr>
								<td width="300px">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Nro ACTA REDIMENSIONAR</span>
								  		</div>
								  		<input type="hidden" name="id_actaRedimensionar" value="0">
	  									<input type="text" class="form-control center"
	  										name="numero"
	  										value=""""),_display_(/*27.22*/numeroActaRedimensionar),format.raw/*27.45*/(""""
	  										readonly>
									</div>
								</td>
								<td width="230px">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Fecha</span>
								  		</div>
								  		<input type="date" class="form-control center"
								  			name="fecha"
								  			onblur="if(!limitaFecha(value,720,10)) value=''"
								  			value=""""),_display_(/*39.22*/fecha),format.raw/*39.27*/(""""
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
					
					<table id="detalle" class="table table-sm table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<tr>
								<th width="50%" colspan="7" style="text-align:center;">EQUIPOS PARA REDIMENSIONAR</th>
								<th width="5%"></th>
								<th width="45%" colspan="7" style="text-align:center;">DETALLE A REDIMENSIONAR</th>
							</tr>
							<tr> 
								<TH width="7%">CODIGO</TH>
								<TH width="17%">EQUIPO</TH>
						        <TH width="3%">UN</TH>
								<TH width="5%" style="background-color: rgb(192, 192, 192);">STOCK</TH>
								<TH width="3%">MONEDA</TH>
								<TH width="7%">P.UNITARIO<br>COMPRA</TH>
								<TH width="7%">TOTAL</TH>
								<TH  width="5%" style="background-color: rgb(192, 192, 192);">CANTIDAD<br>A REDIMENS</TH>
								<TH width=7%>CODIGO</TH>
								<TH width=17%>EQUIPO</TH>
								<TH width=3%>UN</TH>
								<TH width=5% style="background-color: rgb(192, 192, 192);">CANTIDAD<br>REDIMENS</TH>
								<TH width=13%>DESTINO</TH>
							</tr>
						</thead>
						<tbody>
							<tr>
								"""),_display_(/*91.10*/for((lista1, index) <- listEquipBodOrigen.zipWithIndex) yield /*91.65*/{_display_(Seq[Any](format.raw/*91.66*/("""
									"""),format.raw/*92.10*/("""<TR>
										<td  style="text-align:left;">
											"""),_display_(/*94.13*/lista1/*94.19*/.get(4)),format.raw/*94.26*/("""
										"""),format.raw/*95.11*/("""</td>
										
										<td  style="text-align:left;">
											<input type="hidden" name="id_equipoOrigen[]" value=""""),_display_(/*98.66*/lista1/*98.72*/.get(0)),format.raw/*98.79*/("""">
											"""),_display_(/*99.13*/lista1/*99.19*/.get(5)),format.raw/*99.26*/("""
										"""),format.raw/*100.11*/("""</td>
										<td  style="text-align:center;">"""),_display_(/*101.44*/lista1/*101.50*/.get(8)),format.raw/*101.57*/("""</td>
										<td  style="text-align:right; background-color: rgb(192, 192, 192);" class="stock">"""),_display_(/*102.95*/lista1/*102.101*/.get(9)),format.raw/*102.108*/("""</td>
										<td  style="text-align:center;">"""),_display_(/*103.44*/lista1/*103.50*/.get(10)),format.raw/*103.58*/("""</td>
										<td  style="text-align:right;">"""),_display_(/*104.43*/lista1/*104.49*/.get(11)),format.raw/*104.57*/("""</td>
										<td  style="text-align:right;" class="total">"""),_display_(/*105.57*/lista1/*105.63*/.get(12)),format.raw/*105.71*/("""</td>
										<td  style="text-align:center; background-color: rgb(192, 192, 192);">
											<input type="text" class="cantidad form-control right"
												name="cantEquipoOrigen[]"
												id="cantidad_"""),_display_(/*109.27*/lista1/*109.33*/.get(0)),format.raw/*109.40*/("""_"""),_display_(/*109.42*/lista1/*109.48*/.get(1)),format.raw/*109.55*/(""""
												value="0.00"
												onfocus="value=value.replace(/,/g,''); cantAux = value;" 
												onkeydown="return ingresoDouble(window.event)"
												autocomplete="off"
												onchange="if(value=='') value=0; verificaCantidad('"""),_display_(/*114.65*/lista1/*114.71*/.get(0)),format.raw/*114.78*/("""','"""),_display_(/*114.82*/lista1/*114.88*/.get(1)),format.raw/*114.95*/("""','"""),_display_(/*114.99*/lista1/*114.105*/.get(9)),format.raw/*114.112*/("""', value);">
										</td>
										<td colspan="5">
											<table id="tabla_"""),_display_(/*117.30*/index),format.raw/*117.35*/("""" class="table table-sm table-bordered table-condensed table-fluid">
												<tbody>
													<tr>
														<td width="16%">
															<div class="input-group input-group-sm">
															  <input class="form-control" type="text" 
																	onclick="idEquipOrigen='"""),_display_(/*123.43*/lista1/*123.49*/.get(0)),format.raw/*123.56*/("""'; muestraEquipos('"""),_display_(/*123.76*/index),format.raw/*123.81*/("""');"
																	readonly>
															  <div class="input-group-append">
															    <span class="input-group-text" onclick="idEquipOrigen='"""),_display_(/*126.76*/lista1/*126.82*/.get(0)),format.raw/*126.89*/("""'; muestraEquipos('"""),_display_(/*126.109*/index),format.raw/*126.114*/("""');">B</span>
															  </div>
															</div>
														</td>
														<td width="37%"></td>
														<td width="7%"></td>
														<td width="11%" style="background-color: rgb(192, 192, 192);"></td>
														<td width="29%"></td>
													</tr>
												</tbody>
											</table>
										</td>
									</TR>
					 			""")))}),format.raw/*139.11*/("""
						"""),format.raw/*140.7*/("""</tbody>
						<tfoot>
							<tr> 
								<th></th>
								<th style="text-align:left;">TOTALES</th>
								<th></th>
								<th style="background-color: rgb(192, 192, 192);"><div id="totalStock" align="right">0.00</div></th>
								<th></th>
								<th></th>
								<th><div id="totalTotal" align="right">0.00</div></th>
								<th style="background-color: rgb(192, 192, 192);"><div id="totalCantidad" align="right">0.00</div></th>
								<th></th>
								<th></th>
								<th style="background-color: rgb(192, 192, 192);"><div id="totalRedimensionar" align="right">0.00</div></th>
								<th></th>
							</tr>
						</tfoot>
					</table>
					
					
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
	
	
	<div id='modalListaEquipos' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
						<h5 class='modal-title'>BUSCAR Y SELECCIONAR EQUIPO</h5>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type='button' class='btn btn-sm  btn-info' style='font-size: 10px;' data-dismiss='modal' onclick='$("#modalNuevoEquipo").modal("show")'>Agregar nuevo equipo</button>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
				
					<table id='tablaListadoEquipos' class='table table-sm table-hover table-bordered table-condensed table-fluid'>
    					<thead style='background-color: #eeeeee'>
	    					<tr>
	    						<th>CODIGO</th>
	    						<th>EQUIPO</th>
	    						<th>UN</th>
	    					</tr>
	    				</thead>
	    				<tbody>
							"""),_display_(/*196.9*/for(lista <- listEquipos) yield /*196.34*/{_display_(Seq[Any](format.raw/*196.35*/("""
								"""),format.raw/*197.9*/("""<tr onclick="
									selCod='"""),_display_(/*198.19*/lista/*198.24*/.getCodigo),format.raw/*198.34*/("""';
    								selEquip='"""),_display_(/*199.24*/lista/*199.29*/.getNombre),format.raw/*199.39*/("""';
    								selUnidad='"""),_display_(/*200.25*/lista/*200.30*/.getUnidad),format.raw/*200.40*/("""';
    								agregarLinea('"""),_display_(/*201.28*/lista/*201.33*/.getId),format.raw/*201.39*/("""');"
				
									 data-dismiss='modal'>
	    						
		    						<td style='text-align:left;'>"""),_display_(/*205.43*/lista/*205.48*/.getCodigo),format.raw/*205.58*/("""</td>
		    						<td style='text-align:left;'>"""),_display_(/*206.43*/lista/*206.48*/.getNombre),format.raw/*206.58*/("""</td>
		    						<td style='text-align:center'>"""),_display_(/*207.44*/lista/*207.49*/.getUnidad),format.raw/*207.59*/("""</td>
	    						</tr>
							""")))}),format.raw/*209.9*/("""
							
						"""),format.raw/*211.7*/("""</tbody>
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
									"""),_display_(/*241.11*/for(lista <- listGrupos) yield /*241.35*/{_display_(Seq[Any](format.raw/*241.36*/("""
										"""),format.raw/*242.11*/("""<option value=""""),_display_(/*242.27*/lista/*242.32*/.getId()),format.raw/*242.40*/("""">"""),_display_(/*242.43*/lista/*242.48*/.getNombre()),format.raw/*242.60*/("""</option>
									""")))}),format.raw/*243.11*/("""
								"""),format.raw/*244.9*/("""</select>
							</td>
						</TR>
						<tr>
							<td style="text-align:left; vertical-align:top">CODIGO EQUIPO: </td>
							<td style="text-align:left; vertical-align:top">
								<input type="text" class="form-control left"
									id="nuevoEquipoCodigo"
									autocomplete="off"
									onkeydown="return sinReservCodigos(window.event)"
									onchange="value=value.replace(/\s/g,'').toUpperCase(); verificaCodigo(value)"
									maxlength="50"
									value = " ">
							</td>
						</tr>
						<tr>
							<td style="text-align:left; vertical-align:top">NOMBRE EQUIPO: </td>
							<td style="text-align:left; vertical-align:top">
								<input type="text" class="form-control left"
									id="nuevoEquipoNombre"
									onkeydown="return sinReservados(window.event)"
									autocomplete="off"
									maxlength="100"
									value = " ">
							</td>
						</tr>
						<tr>
							<td style="text-align:left; vertical-align:top">FABRICANTE: </td>
							<td style="text-align:left; vertical-align:top">
								<select class="custom-select" 
									id="nuevoEquipoId_fabrica"
									required>
									"""),_display_(/*276.11*/for(lista <- listFabrica) yield /*276.36*/{_display_(Seq[Any](format.raw/*276.37*/("""
										"""),format.raw/*277.11*/("""<option value=""""),_display_(/*277.27*/lista/*277.32*/.id),format.raw/*277.35*/("""">"""),_display_(/*277.38*/lista/*277.43*/.nickName),format.raw/*277.52*/("""</option>
									""")))}),format.raw/*278.11*/("""
								"""),format.raw/*279.9*/("""</select>
							</td>
						</tr>
						<tr>
							<td style="text-align:left; vertical-align:top">UNIDAD: </td>
							<td style="text-align:left; vertical-align:top">
								<select class="custom-select" 
									id="nuevoEquipoId_unidad"
									required>
									"""),_display_(/*288.11*/for(lista <- listUnidades) yield /*288.37*/{_display_(Seq[Any](format.raw/*288.38*/("""
										"""),format.raw/*289.11*/("""<option value=""""),_display_(/*289.27*/lista/*289.32*/.id),format.raw/*289.35*/("""">"""),_display_(/*289.38*/lista/*289.43*/.nombre),format.raw/*289.50*/("""</option>
									""")))}),format.raw/*290.11*/("""
								"""),format.raw/*291.9*/("""</select>
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
	
""")))}),format.raw/*332.2*/("""




"""),format.raw/*337.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*339.31*/("""{"""),format.raw/*339.32*/("""
		"""),format.raw/*340.3*/("""sumaTotales();
		document.getElementById('mostrarmostrar').style.display="block";
		
		
		$('#tablaListadoEquipos').DataTable("""),format.raw/*344.39*/("""{"""),format.raw/*344.40*/("""
		    	"""),format.raw/*345.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
				"order": [[ 0, "asc" ]],
		    	"language": """),format.raw/*348.20*/("""{"""),format.raw/*348.21*/("""
		        	"""),format.raw/*349.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*350.11*/("""}"""),format.raw/*350.12*/("""
			"""),format.raw/*351.4*/("""}"""),format.raw/*351.5*/(""" """),format.raw/*351.6*/(""");
	"""),format.raw/*352.2*/("""}"""),format.raw/*352.3*/(""");
	
	
	
	
	
	let selCod = "";
	let selEquip = "";
	let selUnidad = "";
	let index = 0;
	let cont = 0;
	let idEquipOrigen = 0;
	
	const muestraEquipos = (auxIndex) => """),format.raw/*365.39*/("""{"""),format.raw/*365.40*/("""
			"""),format.raw/*366.4*/("""index = auxIndex;
			let lista = document.getElementById('tablaListadoEquipos');
			for(let i=0; i<lista.rows.length; i++)"""),format.raw/*368.42*/("""{"""),format.raw/*368.43*/("""
				"""),format.raw/*369.5*/("""lista.rows[i].style.display = '';
			"""),format.raw/*370.4*/("""}"""),format.raw/*370.5*/("""
			"""),format.raw/*371.4*/("""let dato = document.getElementById('tabla_'+index);
			for(let i=0; i<lista.rows.length; i++)"""),format.raw/*372.42*/("""{"""),format.raw/*372.43*/("""
				"""),format.raw/*373.5*/("""let cod = lista.rows[i].cells[0].innerHTML;
				lista.rows[i].style.display = 'true';
				for(let j=0; j<dato.rows.length; j++)"""),format.raw/*375.42*/("""{"""),format.raw/*375.43*/("""
					"""),format.raw/*376.6*/("""let cod2 = dato.rows[j].cells[0].innerHTML;
					if(cod == cod2)"""),format.raw/*377.21*/("""{"""),format.raw/*377.22*/("""
						"""),format.raw/*378.7*/("""lista.rows[i].style.display = 'none';
					"""),format.raw/*379.6*/("""}"""),format.raw/*379.7*/("""
				"""),format.raw/*380.5*/("""}"""),format.raw/*380.6*/("""
			"""),format.raw/*381.4*/("""}"""),format.raw/*381.5*/("""
			
			"""),format.raw/*383.4*/("""$('#modalListaEquipos').modal('show');
   	"""),format.raw/*384.5*/("""}"""),format.raw/*384.6*/("""

	"""),format.raw/*386.2*/("""const agregarLinea = (id_equipo) =>"""),format.raw/*386.37*/("""{"""),format.raw/*386.38*/("""
		
		"""),format.raw/*388.3*/("""let tabla = document.getElementById('tabla_'+index);
		tr = tabla.insertRow(tabla.rows.length-1);
		
		td = tr.insertCell(tr.cells.length);
		td.setAttribute ('style', 'text-align:left');
		td.innerHTML = 	selCod;
		
		td = tr.insertCell(tr.cells.length);
		td.setAttribute ('style', 'text-align:left');
		td.innerHTML = 	
			 "<input type='hidden' name='id_a_redimensionar[]' value='"+idEquipOrigen+"'>"
			+ "<input type='hidden' name='id_redimensionar[]' value='"+id_equipo+"'>"
			+ selEquip;
	
		td = tr.insertCell(tr.cells.length);
		td.setAttribute ('style', 'text-align:center');
		td.innerHTML = selUnidad;
		
		td = tr.insertCell(tr.cells.length);
		td.setAttribute ('style', 'text-align:center; background-color: rgb(192, 192, 192);');
		td.innerHTML ="<input height=\"5\" type=\"text\" class=\"cantRedimensionar form-control right\""
				+" name=\"cantEquipoRedimensionar[]\""
				+" id=\"\""
				+" value=\"0.00\""
				+" onfocus=\"value=value.replace(/,/g,''); cantAux = value;\""
				+" onkeydown=\"return ingresoDouble(window.event)\""
				+" autocomplete=\"off\""
				+" onchange=\"if(value=='') value=0; sumaTotales();\">";
												
		
		td = tr.insertCell(tr.cells.length);
		td.setAttribute ('style', 'text-align:center');
		let des = "<div id='selBodegaDestino_"+cont+"'><select class=\"selBodDest form-control form-control-sm\" name=\"id_bodegaDestino[]\">"""),_display_(/*420.137*/Html(optBodegas)),format.raw/*420.153*/("""</select></div>";
		td.innerHTML = des;
	
		cont++;
			
	"""),format.raw/*425.2*/("""}"""),format.raw/*425.3*/("""

	"""),format.raw/*427.2*/("""const verificaCantidad = (id_equipo, id_cotizacion, stock, cantidad) =>"""),format.raw/*427.73*/("""{"""),format.raw/*427.74*/("""
		"""),format.raw/*428.3*/("""stock = stock.replace(/,/g,'');
		if(parseFloat(cantidad) > parseFloat(stock))"""),format.raw/*429.47*/("""{"""),format.raw/*429.48*/("""
			"""),format.raw/*430.4*/("""alertify.alert('La cantidad no puede ser mayor al stock disponible para dar de baja', function () """),format.raw/*430.102*/("""{"""),format.raw/*430.103*/("""
				"""),format.raw/*431.5*/("""$("#cantidad_"+id_equipo+"_"+id_cotizacion).val(formatStandar(0,2));
     		"""),format.raw/*432.8*/("""}"""),format.raw/*432.9*/(""");
		"""),format.raw/*433.3*/("""}"""),format.raw/*433.4*/("""else"""),format.raw/*433.8*/("""{"""),format.raw/*433.9*/("""
			"""),format.raw/*434.4*/("""$("#cantidad_"+id_equipo+"_"+id_cotizacion).val(formatStandar(cantidad,2));
		"""),format.raw/*435.3*/("""}"""),format.raw/*435.4*/("""
		"""),format.raw/*436.3*/("""sumaTotales();
	"""),format.raw/*437.2*/("""}"""),format.raw/*437.3*/("""
	
	"""),format.raw/*439.2*/("""const validarForm = () =>"""),format.raw/*439.27*/("""{"""),format.raw/*439.28*/("""
		"""),format.raw/*440.3*/("""$("#aplica").attr('disabled',true);
		let flag = false;
		let cantTotal = $("#totalCantidad").text().replace(/,/g,'');
		let cantTotalRedimensionar = $("#totalRedimensionar").text().replace(/,/g,'');
		if(parseFloat(cantTotal) > 0 && parseFloat(cantTotalRedimensionar) > 0)"""),format.raw/*444.74*/("""{"""),format.raw/*444.75*/("""
			"""),format.raw/*445.4*/("""return(true);
		"""),format.raw/*446.3*/("""}"""),format.raw/*446.4*/("""else"""),format.raw/*446.8*/("""{"""),format.raw/*446.9*/("""
			"""),format.raw/*447.4*/("""flag = false;
			alertify.alert('PARA GRABAR AL MENOS DEBE CONSIDERAR UN EQUIPO PARA BAJA, CON AL MENOS UN EQUIPO PARA REDIMENSIONAR', function () """),format.raw/*448.134*/("""{"""),format.raw/*448.135*/("""
				"""),format.raw/*449.5*/("""$("#aplica").attr('disabled',false);
				return(flag);
     		"""),format.raw/*451.8*/("""}"""),format.raw/*451.9*/(""");
		"""),format.raw/*452.3*/("""}"""),format.raw/*452.4*/("""
		"""),format.raw/*453.3*/("""return(flag);
	"""),format.raw/*454.2*/("""}"""),format.raw/*454.3*/("""
	
	"""),format.raw/*456.2*/("""const sumaTotales = () => """),format.raw/*456.28*/("""{"""),format.raw/*456.29*/("""
		 """),format.raw/*457.4*/("""let sum = 0;
			$(".stock").each(function() """),format.raw/*458.32*/("""{"""),format.raw/*458.33*/("""
				"""),format.raw/*459.5*/("""let val = $(this).text().replace(/,/g,'');
				sum += parseFloat(val);
			"""),format.raw/*461.4*/("""}"""),format.raw/*461.5*/("""); $("#totalStock").text(formatStandar(sum,2));
			sum = 0;
			$(".total").each(function() """),format.raw/*463.32*/("""{"""),format.raw/*463.33*/("""
				"""),format.raw/*464.5*/("""let val = $(this).text().replace(/,/g,'');
				sum += parseFloat(val);
			"""),format.raw/*466.4*/("""}"""),format.raw/*466.5*/("""); $("#totalTotal").text(formatStandar(sum,0));
			sum = 0;
			$(".cantidad").each(function() """),format.raw/*468.35*/("""{"""),format.raw/*468.36*/("""
				"""),format.raw/*469.5*/("""let val = $(this).val().replace(/,/g,'');
				sum += parseFloat(val);
			"""),format.raw/*471.4*/("""}"""),format.raw/*471.5*/("""); $("#totalCantidad").text(formatStandar(sum,2));
			sum = 0;
			$(".cantRedimensionar").each(function() """),format.raw/*473.44*/("""{"""),format.raw/*473.45*/("""
				"""),format.raw/*474.5*/("""let val = $(this).val().replace(/,/g,'');
				sum += parseFloat(val);
			"""),format.raw/*476.4*/("""}"""),format.raw/*476.5*/("""); $("#totalRedimensionar").text(formatStandar(sum,2));
	"""),format.raw/*477.2*/("""}"""),format.raw/*477.3*/("""
	
	"""),format.raw/*479.2*/("""const grabaNuevoEquipo = () =>"""),format.raw/*479.32*/("""{"""),format.raw/*479.33*/("""
		
		"""),format.raw/*481.3*/("""let nuevoEquipoCodigo = $("#nuevoEquipoCodigo").val().trim();
		let nuevoEquipoNombre = $("#nuevoEquipoNombre").val().trim();
		if(nuevoEquipoCodigo=="")"""),format.raw/*483.28*/("""{"""),format.raw/*483.29*/("""
			"""),format.raw/*484.4*/("""alertify.alert("Debe ingresar un código de equipo", function () """),format.raw/*484.68*/("""{"""),format.raw/*484.69*/("""
 				"""),format.raw/*485.6*/("""$("#nuevoEquipoCodigo").focus();
     		"""),format.raw/*486.8*/("""}"""),format.raw/*486.9*/(""");
		"""),format.raw/*487.3*/("""}"""),format.raw/*487.4*/("""else if(nuevoEquipoNombre=="")"""),format.raw/*487.34*/("""{"""),format.raw/*487.35*/("""
			"""),format.raw/*488.4*/("""alertify.alert("Debe ingresar un nombre de equipo", function () """),format.raw/*488.68*/("""{"""),format.raw/*488.69*/("""
 				"""),format.raw/*489.6*/("""$("#nuevoEquipoNombre").focus();
     		"""),format.raw/*490.8*/("""}"""),format.raw/*490.9*/(""");
		"""),format.raw/*491.3*/("""}"""),format.raw/*491.4*/("""else"""),format.raw/*491.8*/("""{"""),format.raw/*491.9*/("""
			"""),format.raw/*492.4*/("""$("#modalNuevoEquipo").modal('hide');
			let auxOpcion = document.getElementById("nuevoEquipoId_unidad");
			let auxEquipoUnidad = auxOpcion.options[auxOpcion.selectedIndex].text;
			
			var formData = new FormData();
			formData.append('id_grupo',$("#nuevoEquipoId_grupo").val());
			formData.append('codigo',$("#nuevoEquipoCodigo").val());
			formData.append('nombre',$("#nuevoEquipoNombre").val());
			formData.append('id_fabrica',$("#nuevoEquipoId_fabrica").val());
			formData.append('id_unidad',$("#nuevoEquipoId_unidad").val());
			formData.append('desdeMenu',"REDIMENSIONAR");
			formData.append('kg',$("#nuevoEquipoId_kg").val().replace(/,/g,''));
			formData.append('m2',$("#nuevoEquipoId_m2").val().replace(/,/g,''));
			
			
			$.ajax("""),format.raw/*507.11*/("""{"""),format.raw/*507.12*/("""
	            """),format.raw/*508.14*/("""url: "/nuevoEquipoAjax/",
	            type: "POST",
	            method: "POST",
	            data: formData,
	            cache: false,
	            contentType: false,
		     	processData: false,
		     	success: function(respuesta)"""),format.raw/*515.37*/("""{"""),format.raw/*515.38*/("""
		     		"""),format.raw/*516.10*/("""if(respuesta=="existe")"""),format.raw/*516.33*/("""{"""),format.raw/*516.34*/("""
		     			"""),format.raw/*517.11*/("""alertify.alert("El código de equipo ya existe, intente con otro", function () """),format.raw/*517.89*/("""{"""),format.raw/*517.90*/("""
		     				"""),format.raw/*518.12*/("""$("#nuevoEquipoCodigo").val("");
			     		"""),format.raw/*519.11*/("""}"""),format.raw/*519.12*/(""");
		     		"""),format.raw/*520.10*/("""}"""),format.raw/*520.11*/("""else if(respuesta=="error")"""),format.raw/*520.38*/("""{"""),format.raw/*520.39*/("""
		     			"""),format.raw/*521.11*/("""alertify.alert(msgError, function () """),format.raw/*521.48*/("""{"""),format.raw/*521.49*/("""
			     			"""),format.raw/*522.12*/("""location.href = "/";
			     		"""),format.raw/*523.11*/("""}"""),format.raw/*523.12*/(""");
		     		"""),format.raw/*524.10*/("""}"""),format.raw/*524.11*/("""else"""),format.raw/*524.15*/("""{"""),format.raw/*524.16*/("""
		     			"""),format.raw/*525.11*/("""let rs = respuesta.split("_");
						selCod = $("#nuevoEquipoCodigo").val();
						selEquip = $("#nuevoEquipoNombre").val();
						
						let auxOpcion = document.getElementById("nuevoEquipoId_unidad");
						selUnidad = auxOpcion.options[auxOpcion.selectedIndex].text;
						
						
						$("#tablaListadoEquipos").dataTable().fnDestroy();
						

						let tabla = document.getElementById("tablaListadoEquipos");
						tr = tabla.insertRow(tabla.rows.length-1);
			
						td = tr.insertCell(tr.cells.length);
						td.setAttribute ('style', 'text-align:left');
						td.innerHTML = 	selCod;
			
						td = tr.insertCell(tr.cells.length);
						td.setAttribute ('style', 'text-align:left');
						td.innerHTML = 	selEquip;
					
						td = tr.insertCell(tr.cells.length);
						td.setAttribute ('style', 'text-align:center');
						td.innerHTML = auxEquipoUnidad;
						
						$('#tablaListadoEquipos').DataTable("""),format.raw/*551.43*/("""{"""),format.raw/*551.44*/("""
					    	"""),format.raw/*552.11*/(""""fixedHeader": true,
					    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
							"order": [[ 0, "asc" ]],
					    	"language": """),format.raw/*555.23*/("""{"""),format.raw/*555.24*/("""
					        	"""),format.raw/*556.15*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
					        """),format.raw/*557.14*/("""}"""),format.raw/*557.15*/("""
						"""),format.raw/*558.7*/("""}"""),format.raw/*558.8*/(""" """),format.raw/*558.9*/(""");
					
					
		     			agregarLinea(rs[0]);
		     			$("#nuevoEquipoCodigo").val("");
		     			$("#nuevoEquipoNombre").val("");
		     		"""),format.raw/*564.10*/("""}"""),format.raw/*564.11*/("""
		     	"""),format.raw/*565.9*/("""}"""),format.raw/*565.10*/("""
	        """),format.raw/*566.10*/("""}"""),format.raw/*566.11*/(""");
		"""),format.raw/*567.3*/("""}"""),format.raw/*567.4*/("""
	"""),format.raw/*568.2*/("""}"""),format.raw/*568.3*/("""
	
	"""),format.raw/*570.2*/("""const verificaCodigo = (codigo) => """),format.raw/*570.37*/("""{"""),format.raw/*570.38*/("""
		"""),format.raw/*571.3*/("""var formData = new FormData();
	  	formData.append('codigo',codigo);
        $.ajax("""),format.raw/*573.16*/("""{"""),format.raw/*573.17*/("""
            """),format.raw/*574.13*/("""url: "/verificaCodigoEquipoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*581.36*/("""{"""),format.raw/*581.37*/("""
	     		"""),format.raw/*582.9*/("""if(respuesta=="existe")"""),format.raw/*582.32*/("""{"""),format.raw/*582.33*/("""
	     			"""),format.raw/*583.10*/("""alertify.alert("El código de equipo ya existe, intente con otro", function () """),format.raw/*583.88*/("""{"""),format.raw/*583.89*/("""
	     				"""),format.raw/*584.11*/("""$("#nuevoEquipoCodigo").val("");
		     		"""),format.raw/*585.10*/("""}"""),format.raw/*585.11*/(""");
	     		"""),format.raw/*586.9*/("""}"""),format.raw/*586.10*/("""
	     		"""),format.raw/*587.9*/("""if(respuesta=="error")"""),format.raw/*587.31*/("""{"""),format.raw/*587.32*/("""
	     			"""),format.raw/*588.10*/("""alertify.alert(msgError, function () """),format.raw/*588.47*/("""{"""),format.raw/*588.48*/("""
		     			"""),format.raw/*589.11*/("""location.href = "/";
		     		"""),format.raw/*590.10*/("""}"""),format.raw/*590.11*/(""");
	     		"""),format.raw/*591.9*/("""}"""),format.raw/*591.10*/("""
	     	"""),format.raw/*592.8*/("""}"""),format.raw/*592.9*/("""
        """),format.raw/*593.9*/("""}"""),format.raw/*593.10*/(""");
	"""),format.raw/*594.2*/("""}"""),format.raw/*594.3*/("""

	"""),format.raw/*596.2*/("""let extArray = new Array(".gif", ".jpg", ".png", ".jpeg", ".pdf", ".xls", ".doc", ".xlsx", ".docx", ".tar", ".zip", ".rar");
	const LimitAttach = (form, file) => """),format.raw/*597.38*/("""{"""),format.raw/*597.39*/("""
		"""),format.raw/*598.3*/("""let allowSubmit = false;
		if (!file) return;
		while (file.indexOf("\\") != -1)"""),format.raw/*600.35*/("""{"""),format.raw/*600.36*/("""
			"""),format.raw/*601.4*/("""file = file.slice(file.indexOf("\\") + 1);
		"""),format.raw/*602.3*/("""}"""),format.raw/*602.4*/("""
		"""),format.raw/*603.3*/("""let extencion = file.slice(file.lastIndexOf(".")).toLowerCase();
		for (var i = 0; i < extArray.length; i++) """),format.raw/*604.45*/("""{"""),format.raw/*604.46*/("""
			"""),format.raw/*605.4*/("""if (extArray[i] == extencion) """),format.raw/*605.34*/("""{"""),format.raw/*605.35*/(""" """),format.raw/*605.36*/("""allowSubmit = true; break; """),format.raw/*605.63*/("""}"""),format.raw/*605.64*/("""
		"""),format.raw/*606.3*/("""}"""),format.raw/*606.4*/("""
		"""),format.raw/*607.3*/("""if (allowSubmit) """),format.raw/*607.20*/("""{"""),format.raw/*607.21*/("""
			"""),format.raw/*608.4*/("""var file = $("#docAdjunto")[0].files[0];
			var fileSize = file.size; //tamaño del archivo
			if(fileSize>100000000)"""),format.raw/*610.26*/("""{"""),format.raw/*610.27*/("""
				"""),format.raw/*611.5*/("""alert("Se permiten únicamente archivos que no superen los 100 MB,"
				+" el que se intenta subir pesa: "+Math.round(file.size/10)/100+" KB");
				form.docAdjunto.value="";
			"""),format.raw/*614.4*/("""}"""),format.raw/*614.5*/("""
		"""),format.raw/*615.3*/("""}"""),format.raw/*615.4*/("""else"""),format.raw/*615.8*/("""{"""),format.raw/*615.9*/("""
			"""),format.raw/*616.4*/("""alert("Se permiten únicamente archivos con la extención: " 
			+ (extArray.join("  ")) + "\nPor favor, seleccione otro archivo "
			+ "e intente de nuevo.");
			form.docAdjunto.value="";
		"""),format.raw/*620.3*/("""}"""),format.raw/*620.4*/("""
	"""),format.raw/*621.2*/("""}"""),format.raw/*621.3*/("""
	
	


	
"""),format.raw/*627.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,numeroActaRedimensionar:Long,listEquipBodOrigen:List[List[String]],fecha:String,listEquipos:List[tables.Equipo],listGrupos:List[tables.Grupo],listFabrica:List[tables.Fabrica],listUnidades:List[tables.Unidad],optBodegas:String,id_bodegaOrigen:Long): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,numeroActaRedimensionar,listEquipBodOrigen,fecha,listEquipos,listGrupos,listFabrica,listUnidades,optBodegas,id_bodegaOrigen)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,Long,List[List[String]],String,List[tables.Equipo],List[tables.Grupo],List[tables.Fabrica],List[tables.Unidad],String,Long) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,numeroActaRedimensionar,listEquipBodOrigen,fecha,listEquipos,listGrupos,listFabrica,listUnidades,optBodegas,id_bodegaOrigen) => apply(mapDiccionario,mapPermiso,userMnu,numeroActaRedimensionar,listEquipBodOrigen,fecha,listEquipos,listGrupos,listFabrica,listUnidades,optBodegas,id_bodegaOrigen)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuRedimensionar/redimensionarPrepara.scala.html
                  HASH: b02acb8de5fcb2a4db6ebed02744a75f790b5113
                  MATRIX: 1149->1|1604->363|1637->371|1653->379|1692->381|1720->384|1788->432|1817->434|2020->610|2056->625|2139->682|2226->748|2257->752|2937->1405|2981->1428|3434->1854|3460->1859|5369->3741|5440->3796|5479->3797|5517->3807|5602->3865|5617->3871|5645->3878|5684->3889|5834->4012|5849->4018|5877->4025|5919->4040|5934->4046|5962->4053|6002->4064|6079->4113|6095->4119|6124->4126|6252->4226|6269->4232|6299->4239|6376->4288|6392->4294|6422->4302|6498->4350|6514->4356|6544->4364|6634->4426|6650->4432|6680->4440|6925->4657|6941->4663|6970->4670|7000->4672|7016->4678|7045->4685|7324->4936|7340->4942|7369->4949|7401->4953|7417->4959|7446->4966|7478->4970|7495->4976|7525->4983|7638->5068|7665->5073|7987->5367|8003->5373|8032->5380|8080->5400|8107->5405|8292->5562|8308->5568|8337->5575|8386->5595|8414->5600|8816->5970|8851->5977|11216->8315|11258->8340|11298->8341|11335->8350|11395->8382|11410->8387|11442->8397|11496->8423|11511->8428|11543->8438|11598->8465|11613->8470|11645->8480|11703->8510|11718->8515|11746->8521|11870->8617|11885->8622|11917->8632|11993->8680|12008->8685|12040->8695|12117->8744|12132->8749|12164->8759|12226->8790|12269->8805|13367->9875|13408->9899|13448->9900|13488->9911|13532->9927|13547->9932|13577->9940|13608->9943|13623->9948|13657->9960|13709->9980|13746->9989|14910->11125|14952->11150|14992->11151|15032->11162|15076->11178|15091->11183|15116->11186|15147->11189|15162->11194|15193->11203|15245->11223|15282->11232|15584->11506|15627->11532|15667->11533|15707->11544|15751->11560|15766->11565|15791->11568|15822->11571|15837->11576|15866->11583|15918->11603|15955->11612|17412->13038|17445->13043|17537->13106|17567->13107|17598->13110|17753->13236|17783->13237|17819->13245|17991->13388|18021->13389|18062->13401|18169->13479|18199->13480|18231->13484|18260->13485|18289->13486|18321->13490|18350->13491|18546->13658|18576->13659|18608->13663|18759->13785|18789->13786|18822->13791|18887->13828|18916->13829|18948->13833|19070->13926|19100->13927|19133->13932|19289->14059|19319->14060|19353->14066|19446->14130|19476->14131|19511->14138|19582->14181|19611->14182|19644->14187|19673->14188|19705->14192|19734->14193|19770->14201|19841->14244|19870->14245|19901->14248|19965->14283|19995->14284|20029->14290|21441->15673|21480->15689|21565->15746|21594->15747|21625->15750|21725->15821|21755->15822|21786->15825|21893->15903|21923->15904|21955->15908|22083->16006|22114->16007|22147->16012|22251->16088|22280->16089|22313->16094|22342->16095|22374->16099|22403->16100|22435->16104|22541->16182|22570->16183|22601->16186|22645->16202|22674->16203|22706->16207|22760->16232|22790->16233|22821->16236|23123->16509|23153->16510|23185->16514|23229->16530|23258->16531|23290->16535|23319->16536|23351->16540|23528->16687|23559->16688|23592->16693|23682->16755|23711->16756|23744->16761|23773->16762|23804->16765|23847->16780|23876->16781|23908->16785|23963->16811|23993->16812|24025->16816|24098->16860|24128->16861|24161->16866|24263->16940|24292->16941|24412->17032|24442->17033|24475->17038|24577->17112|24606->17113|24729->17207|24759->17208|24792->17213|24893->17286|24922->17287|25057->17393|25087->17394|25120->17399|25221->17472|25250->17473|25335->17530|25364->17531|25396->17535|25455->17565|25485->17566|25519->17572|25701->17725|25731->17726|25763->17730|25856->17794|25886->17795|25920->17801|25988->17841|26017->17842|26050->17847|26079->17848|26138->17878|26168->17879|26200->17883|26293->17947|26323->17948|26357->17954|26425->17994|26454->17995|26487->18000|26516->18001|26548->18005|26577->18006|26609->18010|27385->18757|27415->18758|27458->18772|27722->19007|27752->19008|27791->19018|27843->19041|27873->19042|27913->19053|28020->19131|28050->19132|28091->19144|28163->19187|28193->19188|28234->19200|28264->19201|28320->19228|28350->19229|28390->19240|28456->19277|28486->19278|28527->19290|28587->19321|28617->19322|28658->19334|28688->19335|28721->19339|28751->19340|28791->19351|29734->20265|29764->20266|29804->20277|29985->20429|30015->20430|30059->20445|30169->20526|30199->20527|30234->20534|30263->20535|30292->20536|30462->20677|30492->20678|30529->20687|30559->20688|30598->20698|30628->20699|30661->20704|30690->20705|30720->20707|30749->20708|30781->20712|30845->20747|30875->20748|30906->20751|31019->20835|31049->20836|31091->20849|31357->21086|31387->21087|31424->21096|31476->21119|31506->21120|31545->21130|31652->21208|31682->21209|31722->21220|31793->21262|31823->21263|31862->21274|31892->21275|31929->21284|31980->21306|32010->21307|32049->21317|32115->21354|32145->21355|32185->21366|32244->21396|32274->21397|32313->21408|32343->21409|32379->21417|32408->21418|32445->21427|32475->21428|32507->21432|32536->21433|32567->21436|32758->21598|32788->21599|32819->21602|32928->21682|32958->21683|32990->21687|33063->21732|33092->21733|33123->21736|33261->21845|33291->21846|33323->21850|33382->21880|33412->21881|33442->21882|33498->21909|33528->21910|33559->21913|33588->21914|33619->21917|33665->21934|33695->21935|33727->21939|33872->22055|33902->22056|33935->22061|34139->22237|34168->22238|34199->22241|34228->22242|34260->22246|34289->22247|34321->22251|34538->22440|34567->22441|34597->22443|34626->22444|34663->22453
                  LINES: 28->1|36->5|38->7|38->7|38->7|40->9|40->9|41->10|42->11|42->11|44->13|44->13|45->14|58->27|58->27|70->39|70->39|122->91|122->91|122->91|123->92|125->94|125->94|125->94|126->95|129->98|129->98|129->98|130->99|130->99|130->99|131->100|132->101|132->101|132->101|133->102|133->102|133->102|134->103|134->103|134->103|135->104|135->104|135->104|136->105|136->105|136->105|140->109|140->109|140->109|140->109|140->109|140->109|145->114|145->114|145->114|145->114|145->114|145->114|145->114|145->114|145->114|148->117|148->117|154->123|154->123|154->123|154->123|154->123|157->126|157->126|157->126|157->126|157->126|170->139|171->140|227->196|227->196|227->196|228->197|229->198|229->198|229->198|230->199|230->199|230->199|231->200|231->200|231->200|232->201|232->201|232->201|236->205|236->205|236->205|237->206|237->206|237->206|238->207|238->207|238->207|240->209|242->211|272->241|272->241|272->241|273->242|273->242|273->242|273->242|273->242|273->242|273->242|274->243|275->244|307->276|307->276|307->276|308->277|308->277|308->277|308->277|308->277|308->277|308->277|309->278|310->279|319->288|319->288|319->288|320->289|320->289|320->289|320->289|320->289|320->289|320->289|321->290|322->291|363->332|368->337|370->339|370->339|371->340|375->344|375->344|376->345|379->348|379->348|380->349|381->350|381->350|382->351|382->351|382->351|383->352|383->352|396->365|396->365|397->366|399->368|399->368|400->369|401->370|401->370|402->371|403->372|403->372|404->373|406->375|406->375|407->376|408->377|408->377|409->378|410->379|410->379|411->380|411->380|412->381|412->381|414->383|415->384|415->384|417->386|417->386|417->386|419->388|451->420|451->420|456->425|456->425|458->427|458->427|458->427|459->428|460->429|460->429|461->430|461->430|461->430|462->431|463->432|463->432|464->433|464->433|464->433|464->433|465->434|466->435|466->435|467->436|468->437|468->437|470->439|470->439|470->439|471->440|475->444|475->444|476->445|477->446|477->446|477->446|477->446|478->447|479->448|479->448|480->449|482->451|482->451|483->452|483->452|484->453|485->454|485->454|487->456|487->456|487->456|488->457|489->458|489->458|490->459|492->461|492->461|494->463|494->463|495->464|497->466|497->466|499->468|499->468|500->469|502->471|502->471|504->473|504->473|505->474|507->476|507->476|508->477|508->477|510->479|510->479|510->479|512->481|514->483|514->483|515->484|515->484|515->484|516->485|517->486|517->486|518->487|518->487|518->487|518->487|519->488|519->488|519->488|520->489|521->490|521->490|522->491|522->491|522->491|522->491|523->492|538->507|538->507|539->508|546->515|546->515|547->516|547->516|547->516|548->517|548->517|548->517|549->518|550->519|550->519|551->520|551->520|551->520|551->520|552->521|552->521|552->521|553->522|554->523|554->523|555->524|555->524|555->524|555->524|556->525|582->551|582->551|583->552|586->555|586->555|587->556|588->557|588->557|589->558|589->558|589->558|595->564|595->564|596->565|596->565|597->566|597->566|598->567|598->567|599->568|599->568|601->570|601->570|601->570|602->571|604->573|604->573|605->574|612->581|612->581|613->582|613->582|613->582|614->583|614->583|614->583|615->584|616->585|616->585|617->586|617->586|618->587|618->587|618->587|619->588|619->588|619->588|620->589|621->590|621->590|622->591|622->591|623->592|623->592|624->593|624->593|625->594|625->594|627->596|628->597|628->597|629->598|631->600|631->600|632->601|633->602|633->602|634->603|635->604|635->604|636->605|636->605|636->605|636->605|636->605|636->605|637->606|637->606|638->607|638->607|638->607|639->608|641->610|641->610|642->611|645->614|645->614|646->615|646->615|646->615|646->615|647->616|651->620|651->620|652->621|652->621|658->627
                  -- GENERATED --
              */
          