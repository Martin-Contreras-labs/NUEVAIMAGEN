
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

object otDespachar extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template13[Map[String,String],Map[String,String],utilities.UserMnu,Long,String,tables.BodegaEmpresa,List[List[String]],Long,String,String,Long,List[tables.Transportista],Long,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
id_ot: Long, cabezeraOt: String, bodegaDestino: tables.BodegaEmpresa, listBodegasOrigen: List[List[String]],
nuevoNumeroGuia: Long, hoy: String, vistaDetOrigen: String, id_cotizacion: Long,
listaTransporte: List[tables.Transportista], id_comercial: Long):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*5.1*/("""
"""),_display_(/*6.2*/main("")/*6.10*/ {_display_(Seq[Any](format.raw/*6.12*/("""
	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),format.raw/*9.2*/("""<div id="enCarga" class="blocker" style="display: none;"><br><br><br><br><br><br><h1>Ahora se esta cargando.....</h1></div>
	
	<form id="formGrabaDespacho" action="/grabaDespacho/" enctype="multipart/form-data" method="POST">
		<div id="mostrarmostrar"  style="display:none;">
			"""),_display_(/*13.5*/barraTitulo(mapDiccionario, "NUEVO DESPACHO","DESDE UNA "+mapDiccionario.get("ORDEN_DE_TRABAJO").toUpperCase())),format.raw/*13.116*/("""
			
			"""),format.raw/*15.4*/("""<div class="row  justify-content-md-center">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					"""),_display_(/*17.7*/Html(cabezeraOt)),format.raw/*17.23*/("""
				"""),format.raw/*18.5*/("""</div>
			</div>
			
			<input type="hidden" name="id_guia" value="0">
			<input type="hidden" name="id_ot" value=""""),_display_(/*22.46*/id_ot),format.raw/*22.51*/("""">
			<input type="hidden" name="id_cotizacion" value=""""),_display_(/*23.54*/id_cotizacion),format.raw/*23.67*/("""">
			<input type="hidden" name="id_comercialDestino" value=""""),_display_(/*24.60*/id_comercial),format.raw/*24.72*/("""">
			
			<table class="table table-sm table-hover table-bordered table-condensed table-fluid">
				<thead style="background-color: #eeeeee">
					<tr>
						<td width="25%" rowspan="2">
							<div class="input-group">
						  		<div class="input-group-prepend">
						    		<span class="input-group-text" id="basic-addon1">Origen</span>
						  		</div>
						  		<input type="hidden" id="id_bodegaOrigen" name="id_bodegaOrigen" value="0">
								<input type="text" class="form-control left"
									id="nombreBodegaOrigen"
									onclick='$("#listaBodegasOrigen").modal("show")'
									style="background:white"
									required
									readonly>
							</div>
						</td>
						<td width="25%" rowspan="2">
							<div class="input-group">
						  		<div class="input-group-prepend">
						    		<span class="input-group-text" id="basic-addon1">Destino</span>
						  		</div>
								<input type="hidden" name="id_bodegaDestino" value=""""),_display_(/*48.62*/bodegaDestino/*48.75*/.getId()),format.raw/*48.83*/("""">
								<input type="text" class="form-control left"
									value=""""),_display_(/*50.18*/bodegaDestino/*50.31*/.getNombre()),format.raw/*50.43*/(""""
									readonly>
							</div>
						</td>
						<td>
							<div class="input-group">
						  		<div class="input-group-prepend">
						    		<span class="input-group-text" id="basic-addon1">Nro.Mov</span>
						  		</div>
									"""),_display_(if(mapPermiso.get("parametro.bloqueoNrosMovimientos")!=null && mapPermiso.get("parametro.bloqueoNrosMovimientos").equals("1"))/*59.137*/{_display_(Seq[Any](format.raw/*59.138*/("""
										"""),format.raw/*60.11*/("""<input type="text" class="form-control left"
							  				name="numeroGuia"
											id="numeroGuia"
											value = """"),_display_(/*63.22*/nuevoNumeroGuia),format.raw/*63.37*/(""""
											readonly
											required>
									""")))}else/*66.15*/{_display_(Seq[Any](format.raw/*66.16*/("""
										"""),format.raw/*67.11*/("""<input type="text" class="form-control left"
							  				name="numeroGuia"
											id="numeroGuia"
											value = """"),_display_(/*70.22*/nuevoNumeroGuia),format.raw/*70.37*/(""""
											onkeydown="return ingresoInt(window.event)"
											onchange="verificarNumeroGuia(value)"
											autocomplete="off"
											required>
									""")))}),format.raw/*75.11*/("""
							"""),format.raw/*76.8*/("""</div>
						</td>
						<td>
							<div class="input-group">
						  		<div class="input-group-prepend">
						    		<span class="input-group-text" id="basic-addon1">Nro.Ref</span>
						  		</div>
						  		<input type="text" class="form-control left"
									id="numGuiaCliente"
					  				name="numGuiaCliente"
					  				autocomplete="off"
					  				maxlength="50">
							</div>
						</td>
						<td rowspan="2" style="width: 50px; min-width: 50px; max-width: 50px; text-align: center; vertical-align: middle">
							<span class="btn btn-info btn-sm btn-file" style="font-size: 10px; display: inline-block">
								<input type="hidden" name="docAnexo" value="0">
								<div id="txtBtn">Subir Documento</div>
								<input type="file" multiple id="docAdjunto" name="docAdjunto[]" title="Adjuntar documentos"
								onchange="LimitAttach(this.form, this.form.docAdjunto.value);">
							</span>
						</td>
					</tr>
					<tr>
						<td width="230px">
							<div class="input-group">
						  		<div class="input-group-prepend">
						    		<span class="input-group-text" id="basic-addon1">Fecha Movimiento</span>
						  		</div>
						  		<input type="date" class="form-control center"
						  			name="fechaGuia" 
						  			id="fechaGuia"
									onblur="if(!limitaFecha(value,"""),_display_(/*108.41*/mapPermiso/*108.51*/.get("parametro.diasMenosGuia")),format.raw/*108.82*/(""","""),_display_(/*108.84*/mapPermiso/*108.94*/.get("parametro.diasMasGuia")),format.raw/*108.123*/(""")) """),format.raw/*108.126*/("""{"""),format.raw/*108.127*/("""value='"""),_display_(/*108.135*/hoy),format.raw/*108.138*/("""';$('#fechaIniTerGuia').val(value);"""),format.raw/*108.173*/("""}"""),format.raw/*108.174*/(""" """),format.raw/*108.175*/("""else """),format.raw/*108.180*/("""{"""),format.raw/*108.181*/("""$('#fechaIniTerGuia').val(value);"""),format.raw/*108.214*/("""}"""),format.raw/*108.215*/(""""
						  			value=""""),_display_(/*109.20*/hoy),format.raw/*109.23*/(""""
				        			required>
							</div>
						</td>
						<td width="230px">
							<div class="input-group">
								<div class="input-group-prepend">
									<span class="input-group-text" id="basic-addon1">Fecha Ini/Ter</span>
								</div>
								<input type="date" class="form-control center"
								name="fechaIniTerGuia"
								id="fechaIniTerGuia"
								onblur="if(!limitaFecha(value,"""),_display_(/*121.40*/mapPermiso/*121.50*/.get("parametro.diasMenosIniTer")),format.raw/*121.83*/(""","""),_display_(/*121.85*/mapPermiso/*121.95*/.get("parametro.diasMasIniTer")),format.raw/*121.126*/(""")) value='"""),_display_(/*121.137*/hoy),format.raw/*121.140*/("""';"
								value=""""),_display_(/*122.17*/hoy),format.raw/*122.20*/(""""
								required>
							</div>
						</td>

					</tr>
					<tr>
						<td colspan="6">
							<div class="input-group">
						  		<div class="input-group-prepend">
						    		<span class="input-group-text">Observaciones</span>
						  		</div>
								<textarea class="form-control" 
									id="observaciones"
									name="observaciones" 
									autocomplete="off"></textarea>
							</div>
						</td>
					</tr>
				</thead>
			</table>
			
			<div id="vistaDetOrigen">"""),_display_(/*144.30*/Html(vistaDetOrigen)),format.raw/*144.50*/("""</div>
			
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
					<input type="button"  id="transporte" value='Agregar Transportista' class="btn btn-info btn-sm rounded-pill btn-block" onclick="selectTransporte(0);" >
				</div>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
					<input type="button"  id="verifica" value='Verificar' class="btn btn-success btn-sm rounded-pill btn-block" onclick="verificaMovimientos();" >
				</div>
				<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
					<input type="button" id="cancelar" value="Cancelar" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
				</div>
				<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
					<input type="button"  id="aplica" value='Confirmar' class="btn btn-primary btn-sm rounded-pill btn-block" onclick="confirmaMovimientos();" style="visibility:hidden">
				</div>
				<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
					<input type="button"  id="modifica" value='Modificar' class="btn btn-warning btn-sm rounded-pill btn-block" onclick="modificaMovimientos();" style="visibility:hidden">
				</div>
			</div>
			
		</div>
		<input type="hidden" id="id_transportistaXLSX" name="id_transportista" value="0">
	</form>
	
	<div id='listaBodegasOrigen' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable' style="max-width: 80% !important;" role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>SELECCIONAR """),_display_(/*173.43*/mapDiccionario/*173.57*/.get("BODEGA")),format.raw/*173.71*/("""/PROYECTO DE ORIGEN</h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<table id="tablaListaBodegasOrigen" class='table table-sm table-bordered table-condensed table-hover table-fluid'>
						<thead style="background-color: #eeeeee">
							<TR> 
								<TH>SUCURSAL</TH>
								<TH>"""),_display_(/*183.14*/mapDiccionario/*183.28*/.get("BODEGA")),format.raw/*183.42*/("""/PROYECTO</TH>
								<TH>NOMBRE CLIENTE O PROPIETARIO</TH>
					        	<TH>NOMBRE DEL PROYECTO</TH>
					        	<TH>"""),_display_(/*186.20*/mapDiccionario/*186.34*/.get("COMUNA")),format.raw/*186.48*/(""" """),format.raw/*186.49*/("""PROYECTO</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*190.9*/for(lista1 <- listBodegasOrigen) yield /*190.41*/{_display_(Seq[Any](format.raw/*190.42*/("""
								"""),format.raw/*191.9*/("""<TR onClick="seleccionaBodegaOrigen("""),_display_(/*191.46*/lista1/*191.52*/.get(1)),format.raw/*191.59*/(""")" data-dismiss="modal">
									<td  style="text-align:left;">"""),_display_(/*192.41*/lista1/*192.47*/.get(16)),format.raw/*192.55*/("""</td>
									<td  style="text-align:left;"><div id="bodega_"""),_display_(/*193.57*/lista1/*193.63*/.get(1)),format.raw/*193.70*/("""">"""),_display_(/*193.73*/lista1/*193.79*/.get(5).toUpperCase()),format.raw/*193.100*/("""</div></td>
									<td  style="text-align:left;">"""),_display_(/*194.41*/lista1/*194.47*/.get(7)),format.raw/*194.54*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*195.41*/lista1/*195.47*/.get(8)),format.raw/*195.54*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*196.41*/lista1/*196.47*/.get(9)),format.raw/*196.54*/("""</td>
								</TR>
				 			""")))}),format.raw/*198.10*/("""
						"""),format.raw/*199.7*/("""</tbody>
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
	
	<div id='modalListaEquipos' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>BUSCAR Y SELECCIONAR EQUIPO</h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<div id="listaEquipos"></div>
	   				<div class='row'>
						<div class='col-sm-12' style='text-align:center'>
							<button type='button' class='btn btn-sm  btn-warning' style='font-size: 10px;' data-dismiss='modal'>Cancelar</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
		
	"""),_display_(/*232.3*/modalListaTransportistas2(mapDiccionario, listaTransporte)),format.raw/*232.61*/("""
""")))}),format.raw/*233.2*/("""



"""),format.raw/*237.1*/("""<script type="text/javascript">

		let auxId_guia = 0;
		let auxId_transportista = 0;
	
		const selectTransporte = (id_guia) => """),format.raw/*242.41*/("""{"""),format.raw/*242.42*/("""
			"""),format.raw/*243.4*/("""auxId_guia = id_guia;
			$('#modalListaTransporte').modal('show');
		"""),format.raw/*245.3*/("""}"""),format.raw/*245.4*/("""
		
		"""),format.raw/*247.3*/("""const seleccionaTransportista = (id_transportista) => """),format.raw/*247.57*/("""{"""),format.raw/*247.58*/("""
			"""),format.raw/*248.4*/("""$('#modalListaTransporte').modal('hide');
			$('#id_guiaXLSX').val(auxId_guia);
			$('#id_transportistaXLSX').val(id_transportista);
			auxId_transportista = id_transportista;
			transportistaSeleccionado();
		"""),format.raw/*253.3*/("""}"""),format.raw/*253.4*/("""
		
		"""),format.raw/*255.3*/("""const transportistaSeleccionado = () => """),format.raw/*255.43*/("""{"""),format.raw/*255.44*/("""
			"""),format.raw/*256.4*/("""var tableReg = document.getElementById("tablaListaTransporte");
			for (var i = 1; i < tableReg.rows.length; i++)"""),format.raw/*257.50*/("""{"""),format.raw/*257.51*/("""
				"""),format.raw/*258.5*/("""var cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
				let idTransporte = cellsOfRow[0].innerHTML;
				if(idTransporte == auxId_transportista)"""),format.raw/*260.44*/("""{"""),format.raw/*260.45*/("""
					"""),format.raw/*261.6*/("""tableReg.rows[i].style.backgroundColor = "yellow";
					
				"""),format.raw/*263.5*/("""}"""),format.raw/*263.6*/("""else"""),format.raw/*263.10*/("""{"""),format.raw/*263.11*/("""
					"""),format.raw/*264.6*/("""tableReg.rows[i].style.backgroundColor = "";
				"""),format.raw/*265.5*/("""}"""),format.raw/*265.6*/("""
			"""),format.raw/*266.4*/("""}"""),format.raw/*266.5*/("""
		"""),format.raw/*267.3*/("""}"""),format.raw/*267.4*/("""

	"""),format.raw/*269.2*/("""$(document).ready(function() """),format.raw/*269.31*/("""{"""),format.raw/*269.32*/("""
		"""),format.raw/*270.3*/("""document.getElementById('mostrarmostrar').style.display="block";
		
		$('#tablaListaBodegasOrigen').DataTable("""),format.raw/*272.43*/("""{"""),format.raw/*272.44*/("""
	    	"""),format.raw/*273.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
			"order": [[ 0, "asc" ],[ 1, "asc" ]],
	    	"language": """),format.raw/*276.19*/("""{"""),format.raw/*276.20*/("""
	        	"""),format.raw/*277.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*278.10*/("""}"""),format.raw/*278.11*/("""
		"""),format.raw/*279.3*/("""}"""),format.raw/*279.4*/(""" """),format.raw/*279.5*/(""");

	"""),format.raw/*281.2*/("""}"""),format.raw/*281.3*/(""");
	
	const seleccionaBodegaOrigen = (id_bodegaEmpresa) =>"""),format.raw/*283.54*/("""{"""),format.raw/*283.55*/("""
		"""),format.raw/*284.3*/("""let nombre = $("#bodega_"+id_bodegaEmpresa).text();
		$("#nombreBodegaOrigen").val(nombre);
		$("#id_bodegaOrigen").val(id_bodegaEmpresa);
		document.getElementById('vistaDetOrigen').innerHTML = """"),_display_(/*287.59*/Html(vistaDetOrigen)),format.raw/*287.79*/("""";
		llenaListEquipo(id_bodegaEmpresa);
	"""),format.raw/*289.2*/("""}"""),format.raw/*289.3*/("""
	
	"""),format.raw/*291.2*/("""const verificarNumeroGuia = (value) =>"""),format.raw/*291.40*/("""{"""),format.raw/*291.41*/("""
		"""),format.raw/*292.3*/("""if(value.trim()=="")"""),format.raw/*292.23*/("""{"""),format.raw/*292.24*/("""
			"""),format.raw/*293.4*/("""$("#numeroGuia").val(""""),_display_(/*293.27*/nuevoNumeroGuia),format.raw/*293.42*/("""");
		"""),format.raw/*294.3*/("""}"""),format.raw/*294.4*/("""else"""),format.raw/*294.8*/("""{"""),format.raw/*294.9*/("""
			"""),format.raw/*295.4*/("""let aux = '"""),_display_(/*295.16*/nuevoNumeroGuia),format.raw/*295.31*/("""';
			var formData = new FormData();
		  	formData.append('numeroGuia',value);
	        $.ajax("""),format.raw/*298.17*/("""{"""),format.raw/*298.18*/("""
	            """),format.raw/*299.14*/("""url: "/verificaNumeroGuiaAjax/",
	            type: "POST",
	            method: "POST",
	            data: formData,
	            cache: false,
	            contentType: false,
		     	processData: false,
		     	success: function(respuesta)"""),format.raw/*306.37*/("""{"""),format.raw/*306.38*/("""
		     		"""),format.raw/*307.10*/("""if(respuesta=="existe")"""),format.raw/*307.33*/("""{"""),format.raw/*307.34*/("""
		     			"""),format.raw/*308.11*/("""alertify.alert('El número de movimiento ya existe, intente con otro', function () """),format.raw/*308.93*/("""{"""),format.raw/*308.94*/("""
		     				"""),format.raw/*309.12*/("""$("#numeroGuia").val(""""),_display_(/*309.35*/nuevoNumeroGuia),format.raw/*309.50*/("""");
		     			"""),format.raw/*310.11*/("""}"""),format.raw/*310.12*/(""");
		     		"""),format.raw/*311.10*/("""}"""),format.raw/*311.11*/("""
		     		"""),format.raw/*312.10*/("""if(respuesta=="error")"""),format.raw/*312.32*/("""{"""),format.raw/*312.33*/("""
		     			"""),format.raw/*313.11*/("""alertify.alert(msgError, function () """),format.raw/*313.48*/("""{"""),format.raw/*313.49*/("""
			     			"""),format.raw/*314.12*/("""location.href = "/";
			     		"""),format.raw/*315.11*/("""}"""),format.raw/*315.12*/(""");
		     		"""),format.raw/*316.10*/("""}"""),format.raw/*316.11*/("""
		     	"""),format.raw/*317.9*/("""}"""),format.raw/*317.10*/("""
	        """),format.raw/*318.10*/("""}"""),format.raw/*318.11*/(""");
		"""),format.raw/*319.3*/("""}"""),format.raw/*319.4*/("""
	"""),format.raw/*320.2*/("""}"""),format.raw/*320.3*/("""
	
	"""),format.raw/*322.2*/("""let vistaHTML = "";
	
	const llenaListEquipo = (id_bodegaOrigen) => """),format.raw/*324.47*/("""{"""),format.raw/*324.48*/("""
		"""),format.raw/*325.3*/("""var formData = new FormData();
  		formData.append('id_bodegaOrigen',id_bodegaOrigen);
		$.ajax("""),format.raw/*327.10*/("""{"""),format.raw/*327.11*/("""
	            """),format.raw/*328.14*/("""url: "/tblListEquipoConStockAjax/",
	            type: "POST",
	            method: "POST",
	            data: formData,
	            cache: false,
	            contentType: false,
		     	processData: false,
		     	success: function(rs)"""),format.raw/*335.30*/("""{"""),format.raw/*335.31*/("""
					"""),format.raw/*336.6*/("""if(rs=="vacia")"""),format.raw/*336.21*/("""{"""),format.raw/*336.22*/("""
						"""),format.raw/*337.7*/("""alertify.alert('"""),_display_(/*337.24*/mapDiccionario/*337.38*/.get("BODEGA")),format.raw/*337.52*/("""/PROYECTO no tiene equipos para trasladar', function () """),format.raw/*337.108*/("""{"""),format.raw/*337.109*/("""
							"""),format.raw/*338.8*/("""$("#id_bodegaOrigen").val("0");
							$("#nombreBodegaOrigen").val("");
						"""),format.raw/*340.7*/("""}"""),format.raw/*340.8*/(""");
					"""),format.raw/*341.6*/("""}"""),format.raw/*341.7*/("""else"""),format.raw/*341.11*/("""{"""),format.raw/*341.12*/("""
						"""),format.raw/*342.7*/("""let map = new Map();
						vistaHTML = 
		    				"<table id='tablaListadoEquipos' class='table table-sm table-hover table-bordered table-condensed table-fluid'>"+
		    					"<thead style='background-color: #eeeeee'>"+
			    					"<tr>"+
			    						"<th>GRUPO</th>"+
			    						"<th>CODIGO</th>"+
			    						"<th>EQUIPO</th>"+
			    						"<th>UN</th>"+
			    						"<th>STOCK</th>"+
			    					"</tr>"+
			    				"</thead>"+
			    				"<tbody>";
		    			for(var i in rs)"""),format.raw/*355.26*/("""{"""),format.raw/*355.27*/("""
		    				"""),format.raw/*356.11*/("""let stockInicial = parseFloat(rs[i][9].replace(/,/g,''));
		    				vistaHTML += 
		    					"<tr onclick=\"selCod='"+rs[i][4]+"';"
		    								+ "selEquip='"+rs[i][5].replace(/"\\""/g,'')+"';"
		    								+ "selStock='"+rs[i][9]+"';"
		    								+ "selUnidad='"+rs[i][8]+"';"
		    								+ "selKg='"+rs[i][6]+"';"
		    								+ "selM2='"+rs[i][7]+"';"+
		    								" agregarLinea("+rs[i][0]+","+rs[i][1]+","+stockInicial+",'0.00','-1')\" data-dismiss='modal'>"+
		    						
			    						"<td style='text-align:left;'>"+rs[i][2]+"</td>"+
			    						"<td style='text-align:left;'>"+rs[i][4]+"</td>"+
			    						"<td style='text-align:left;'>"+rs[i][5]+"</td>"+ 
			    						"<td style='text-align:center;'>"+rs[i][8]+"</td>"+ 
			    						"<td style='text-align:right;'>"+rs[i][9]+"</td>"+
		    					"</tr>";
							map.set(rs[i][4], rs[i])
		    			"""),format.raw/*373.10*/("""}"""),format.raw/*373.11*/("""
		    			"""),format.raw/*374.10*/("""vistaHTML +=
		    					"</tbody>"+
		    				"</table>";
						
						document.getElementById('listaEquipos').innerHTML = vistaHTML;
						
						$('#tablaListadoEquipos').DataTable("""),format.raw/*380.43*/("""{"""),format.raw/*380.44*/("""
					    	"""),format.raw/*381.11*/(""""fixedHeader": true,
					    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
							"order": [[ 2, "asc" ]],
					    	"language": """),format.raw/*384.23*/("""{"""),format.raw/*384.24*/("""
					        	"""),format.raw/*385.15*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
					        """),format.raw/*386.14*/("""}"""),format.raw/*386.15*/("""
						"""),format.raw/*387.7*/("""}"""),format.raw/*387.8*/(""" """),format.raw/*387.9*/(""");
			
						let tdCodOrigen = document.getElementsByClassName('tdCodOrigen');
						let tdEquipOrigen = document.getElementsByClassName('tdEquipOrigen');
						let tdUnOrigen = document.getElementsByClassName('tdUnOrigen');
						let tdIdEquipOrigen = document.getElementsByClassName('tdIdEquipOrigen');
						let tdEsVentaOrigen = document.getElementsByClassName('tdEsVentaOrigen');
						let tdIdCotizaDetalle = document.getElementsByClassName('tdIdCotizaDetalle');
						let tdSaldo = document.getElementsByClassName('tdSaldo');
						
						for(let i = 0; i < tdCodOrigen.length; i++)"""),format.raw/*397.50*/("""{"""),format.raw/*397.51*/("""
							"""),format.raw/*398.8*/("""let aux = map.get(tdCodOrigen[i].textContent);
							
							index = i;
							idCotizaDetalle = tdIdCotizaDetalle[i].textContent;
							esVenta = tdEsVentaOrigen[i].textContent;
							saldo = tdSaldo[i].textContent;
							saldo = saldo.replace(/"\""/g,'').replace(/,/g,'');
							idCotizaDetalle = tdIdCotizaDetalle[i].textContent;
								
							if(aux == null)"""),format.raw/*407.23*/("""{"""),format.raw/*407.24*/("""
								"""),format.raw/*408.9*/("""selCod = tdCodOrigen[i].textContent;
								selEquip = tdEquipOrigen[i].textContent;
								selStock = "0.00";
								selUnidad = tdUnOrigen[i].textContent;
								selKg = "0.00";
								selM2 = "0.00";
								let stock = "0.00";
								saldo = "0.00";
								agregarLinea(idEquipOrigen,tdEsVentaOrigen[i].textContent,stock, saldo, idEquipOrigen);
							"""),format.raw/*417.8*/("""}"""),format.raw/*417.9*/("""else"""),format.raw/*417.13*/("""{"""),format.raw/*417.14*/("""
								"""),format.raw/*418.9*/("""selCod=aux[4];
		    					selEquip = aux[5].replace(/"\""/g,'');
		    					selStock = aux[9].replace(/,/g,'');
		    					selUnidad = aux[8];
		    					selKg = aux[6];
		    					selM2 = aux[7];
								let stock = parseFloat(selStock);
								let auxSaldo = parseFloat(saldo);
								if(stock < auxSaldo)"""),format.raw/*426.29*/("""{"""),format.raw/*426.30*/("""
									"""),format.raw/*427.10*/("""auxSaldo = stock;
								"""),format.raw/*428.9*/("""}"""),format.raw/*428.10*/("""
								"""),format.raw/*429.9*/("""agregarLinea(aux[0],aux[1],stock, auxSaldo, aux[0]);
							"""),format.raw/*430.8*/("""}"""),format.raw/*430.9*/("""
						"""),format.raw/*431.7*/("""}"""),format.raw/*431.8*/("""
						"""),format.raw/*432.7*/("""sumaTotales();
					"""),format.raw/*433.6*/("""}"""),format.raw/*433.7*/("""
				"""),format.raw/*434.5*/("""}"""),format.raw/*434.6*/("""
	      """),format.raw/*435.8*/("""}"""),format.raw/*435.9*/(""");
	"""),format.raw/*436.2*/("""}"""),format.raw/*436.3*/("""
	
	
	"""),format.raw/*439.2*/("""const muestraEquipos = () => """),format.raw/*439.31*/("""{"""),format.raw/*439.32*/("""
		"""),format.raw/*440.3*/("""if($("#id_bodegaOrigen").val() == 0) """),format.raw/*440.40*/("""{"""),format.raw/*440.41*/("""
			"""),format.raw/*441.4*/("""alertify.alert('Antes debe seleccionar """),_display_(/*441.44*/mapDiccionario/*441.58*/.get("BODEGA")),format.raw/*441.72*/("""/PROYECTO de origen , desde dónde se obtendrán los equipos a entregar', function () """),format.raw/*441.156*/("""{"""),format.raw/*441.157*/("""
				
			"""),format.raw/*443.4*/("""}"""),format.raw/*443.5*/(""");
		"""),format.raw/*444.3*/("""}"""),format.raw/*444.4*/("""else"""),format.raw/*444.8*/("""{"""),format.raw/*444.9*/("""
			
			"""),format.raw/*446.4*/("""document.getElementById('listaEquipos').innerHTML = vistaHTML;
			$('#tablaListadoEquipos').DataTable("""),format.raw/*447.40*/("""{"""),format.raw/*447.41*/("""
		    	"""),format.raw/*448.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
				"order": [[ 2, "asc" ]],
		    	"language": """),format.raw/*451.20*/("""{"""),format.raw/*451.21*/("""
		        	"""),format.raw/*452.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*453.11*/("""}"""),format.raw/*453.12*/("""
			"""),format.raw/*454.4*/("""}"""),format.raw/*454.5*/(""" """),format.raw/*454.6*/(""");
			
			let lista = document.getElementById('tablaListadoEquipos');
			let dato = document.getElementById('tabla_'+index);
			for(let i=0; i<lista.rows.length; i++)"""),format.raw/*458.42*/("""{"""),format.raw/*458.43*/("""
				"""),format.raw/*459.5*/("""let cod = lista.rows[i].cells[1].innerHTML;
				lista.rows[i].style.display = 'true';
				for(let j=0; j<dato.rows.length; j++)"""),format.raw/*461.42*/("""{"""),format.raw/*461.43*/("""
					
					"""),format.raw/*463.6*/("""let cod2 = dato.rows[j].cells[0].innerHTML;
					
					if(cod == cod2)"""),format.raw/*465.21*/("""{"""),format.raw/*465.22*/("""
						"""),format.raw/*466.7*/("""lista.rows[i].style.display = 'none';
					"""),format.raw/*467.6*/("""}"""),format.raw/*467.7*/("""
				"""),format.raw/*468.5*/("""}"""),format.raw/*468.6*/("""
			"""),format.raw/*469.4*/("""}"""),format.raw/*469.5*/("""
			"""),format.raw/*470.4*/("""$('#modalListaEquipos').modal('show');
		"""),format.raw/*471.3*/("""}"""),format.raw/*471.4*/("""
   """),format.raw/*472.4*/("""}"""),format.raw/*472.5*/("""



	"""),format.raw/*476.2*/("""let index = -1;
	let idCotizaDetalle = -1;
	let esVenta = -1;
	let idEquipOrigen = -1;
	let saldo = 0;

	
	let selCod = "";
	let selEquip = "";
	let selStock = 0;
	let selUnidad = "";
	let selKg = 0;
	let selM2 = 0;
	let cont = 0;
	
	const agregarLinea = (id_equipo, id_cotizacion, stockInicial, saldo, id_eqOrigen) =>"""),format.raw/*491.86*/("""{"""),format.raw/*491.87*/("""
		"""),format.raw/*492.3*/("""if(id_eqOrigen < 0)"""),format.raw/*492.22*/("""{"""),format.raw/*492.23*/("""
			"""),format.raw/*493.4*/("""id_eqOrigen = idEquipOrigen;
		"""),format.raw/*494.3*/("""}"""),format.raw/*494.4*/("""
		
		"""),format.raw/*496.3*/("""let lista = document.getElementById('tablaListadoEquipos');
		for(let i=0; i<lista.rows.length; i++)"""),format.raw/*497.41*/("""{"""),format.raw/*497.42*/("""
			"""),format.raw/*498.4*/("""lista.rows[i].style.display = '';
		"""),format.raw/*499.3*/("""}"""),format.raw/*499.4*/("""
		
		"""),format.raw/*501.3*/("""let tabla = document.getElementById('tabla_'+index);
		tr = tabla.insertRow(tabla.rows.length-1);
		
		td = tr.insertCell(tr.cells.length);
		td.setAttribute ('style', 'text-align:left');
		td.innerHTML = 	selCod;
		
		td = tr.insertCell(tr.cells.length);
		td.setAttribute ('style', 'text-align:left');
		td.innerHTML = 	"<input type='hidden' name='id_equipoDespacho[]' value='"+id_equipo+"'>"+
						"<input type='hidden' name='id_cotizaDetalle[]' value='"+idCotizaDetalle+"'>"+
						"<input type='hidden' name='esVenta[]' value='"+esVenta+"'>"+
						"<input type='hidden' name='esNuevo[]' value='0'>"+
						"<input type='hidden' name='id_equipoOrigen[]' value='"+id_eqOrigen+"'>"+
						selEquip;
		
		td = tr.insertCell(tr.cells.length);
		td.setAttribute ('style', 'text-align:center');
		td.innerHTML = selUnidad;
		
		td = tr.insertCell(tr.cells.length);
		td.setAttribute ('style', 'text-align:right');
		td.innerHTML="<div id='"+cont+"_cantStock'>"+selStock +"</div>";
		
		
		td = tr.insertCell(tr.cells.length);
		td.setAttribute ('style', 'text-align:center');
		td.innerHTML ="<input type='text' class='cantDespacho form-control right' "+
				" id='"+cont+"_cantDespacho_"+id_equipo+"_"+id_cotizacion+"' "+ 
				" name='cantDespacho[]' " +
				" value='"+formatStandar2(saldo)+"'"+
				" onfocus=\"value=value.replace(/,/g,'')\" "+
				" onkeydown='return ingresoDouble(window.event)' "+
				" onfocusout='value = formatStandar2(value);'"+
				" onchange='validaCantDespacho("+cont+",value,"+id_equipo+","+id_cotizacion+","+stockInicial+"); value=formatStandar2(value);'>";
		
				
		td = tr.insertCell(tr.cells.length);
		td.setAttribute ('style', 'text-align:center');
		td.innerHTML ="<input type='text' class='cantEquivalente form-control right' "+
			" id='"+cont+"_cantEquivalente' "+
			" name='cantEquivalente[]' " +
			" value='"+formatStandar2(saldo)+"'"+
			" onfocus=\"value=value.replace(/,/g,'')\" "+
			" onkeydown='return ingresoDouble(window.event)' "+
			" onfocusout='value = formatStandar2(value);'"+
			" onchange='value=formatStandar2(value);'>";
	
		td = tr.insertCell(tr.cells.length);
		td.setAttribute ('style', 'text-align:right;');
		td.innerHTML ="<div class='kg' id='"+cont+"_kgTot'>"+formatStandar2(saldo*selKg)+"</div>";
		
		td = tr.insertCell(tr.cells.length);
		td.setAttribute ('style', 'text-align:right;');
		td.innerHTML ="<div class='m2' id='"+cont+"_m2Tot'>"+formatStandar2(saldo*selM2)+"</div>";
		
		td = tr.insertCell(tr.cells.length);
		td.setAttribute ('style', 'display:none');
		td.innerHTML ="<div id='"+cont+"_kgUn'>"+selKg+"</div>";
		
		td = tr.insertCell(tr.cells.length);
		td.setAttribute ('style', 'display:none');
		td.innerHTML ="<div id='"+cont+"_m2Un'>"+selM2+"</div>";
			
		cont++;
			
	"""),format.raw/*567.2*/("""}"""),format.raw/*567.3*/("""
	
	"""),format.raw/*569.2*/("""const validaCantDespacho = (indCont, valor, id_equipo, id_cotizacion, stockInicial) =>"""),format.raw/*569.88*/("""{"""),format.raw/*569.89*/("""
		"""),format.raw/*570.3*/("""let stock = $("#"+indCont+"_cantStock").text().replace(/,/g,'');
		let kgUni = $("#"+indCont+"_kgUn").text().replace(/,/g,'');
		let m2Uni = $("#"+indCont+"_m2Un").text().replace(/,/g,'');
		let sum = 0;
		let elemento = document.getElementsByClassName('cantDespacho');
		for(let i = 0; i < elemento.length; i++)"""),format.raw/*575.43*/("""{"""),format.raw/*575.44*/("""
		    """),format.raw/*576.7*/("""let dePaso = elemento[i].getAttribute('id').split("_");
			if( id_equipo==dePaso[2] && id_cotizacion==dePaso[3] )"""),format.raw/*577.58*/("""{"""),format.raw/*577.59*/("""
				"""),format.raw/*578.5*/("""let auxNum = elemento[i].value;
				auxNum = auxNum.replace(/,/g,'');
				sum += parseFloat(auxNum);
			"""),format.raw/*581.4*/("""}"""),format.raw/*581.5*/("""
		"""),format.raw/*582.3*/("""}"""),format.raw/*582.4*/("""
		"""),format.raw/*583.3*/("""if(parseFloat(valor) > parseFloat(stock) || parseFloat(sum) > parseFloat(stock))"""),format.raw/*583.83*/("""{"""),format.raw/*583.84*/("""
			"""),format.raw/*584.4*/("""alertify.alert('La cantidad no puede ser mayor al stock disponible', function () """),format.raw/*584.85*/("""{"""),format.raw/*584.86*/("""
				"""),format.raw/*585.5*/("""$("#"+indCont+"_cantStock").text(formatStandar(stockInicial,2));
				$("#"+indCont+"_cantDespacho_"+id_equipo+"_"+id_cotizacion).val(formatStandar(parseFloat(0),2));
				$("#"+indCont+"_cantEquivalente").val(formatStandar(parseFloat(0),2));
				$("#"+indCont+"_kgTot").text(formatStandar(parseFloat(0)*parseFloat(kgUni),2));
				$("#"+indCont+"_m2Tot").text(formatStandar(parseFloat(0)*parseFloat(m2Uni),2));
			"""),format.raw/*590.4*/("""}"""),format.raw/*590.5*/(""");
		"""),format.raw/*591.3*/("""}"""),format.raw/*591.4*/(""" """),format.raw/*591.5*/("""else """),format.raw/*591.10*/("""{"""),format.raw/*591.11*/("""
			
			
			"""),format.raw/*594.4*/("""let stockResult = parseFloat(stockInicial) - parseFloat(sum);
			let elemento2 = document.getElementsByClassName('cantDespacho');
			for(let i = 0; i < elemento2.length; i++)"""),format.raw/*596.45*/("""{"""),format.raw/*596.46*/("""
			    """),format.raw/*597.8*/("""let dePaso = elemento2[i].getAttribute('id').split("_");
				if( id_equipo == dePaso[2] && id_cotizacion == dePaso[3] )"""),format.raw/*598.63*/("""{"""),format.raw/*598.64*/("""
					"""),format.raw/*599.6*/("""let auxCont = dePaso[0];
					$("#"+auxCont+"_cantStock").text(formatStandar(stockResult,2));
				"""),format.raw/*601.5*/("""}"""),format.raw/*601.6*/("""
			"""),format.raw/*602.4*/("""}"""),format.raw/*602.5*/("""
			
			"""),format.raw/*604.4*/("""$("#"+indCont+"_cantEquivalente").val(formatStandar(parseFloat(valor),2));
			$("#"+indCont+"_kgTot").text(formatStandar(parseFloat(valor)*parseFloat(kgUni),2));
			$("#"+indCont+"_m2Tot").text(formatStandar(parseFloat(valor)*parseFloat(m2Uni),2));
		"""),format.raw/*607.3*/("""}"""),format.raw/*607.4*/("""
		"""),format.raw/*608.3*/("""sumaTotales();
	"""),format.raw/*609.2*/("""}"""),format.raw/*609.3*/("""
	
	"""),format.raw/*611.2*/("""const sumaTotales = (indCont, valor, id_equipo, id_cotizacion) =>"""),format.raw/*611.67*/("""{"""),format.raw/*611.68*/("""
		"""),format.raw/*612.3*/("""let cant = 0;
		let kg = 0;
		let m2 = 0;
		
		let arrCant = document.getElementsByClassName('cantDespacho');
		let arrKg = document.getElementsByClassName('kg');
		let arrM2 = document.getElementsByClassName('m2');
		
		for(let i = 0; i < arrCant.length; i++)"""),format.raw/*620.42*/("""{"""),format.raw/*620.43*/("""
			"""),format.raw/*621.4*/("""let auxNum = arrCant[i].value;
			auxNum = auxNum.replace(/,/g,'');
			cant += parseFloat(auxNum);
		"""),format.raw/*624.3*/("""}"""),format.raw/*624.4*/("""
		
		"""),format.raw/*626.3*/("""for(let i = 0; i < arrKg.length; i++)"""),format.raw/*626.40*/("""{"""),format.raw/*626.41*/("""
			"""),format.raw/*627.4*/("""let auxNum = arrKg[i].innerHTML;
			auxNum = auxNum.replace(/,/g,'');
			kg += parseFloat(auxNum);
		"""),format.raw/*630.3*/("""}"""),format.raw/*630.4*/("""
		
		"""),format.raw/*632.3*/("""for(let i = 0; i < arrM2.length; i++)"""),format.raw/*632.40*/("""{"""),format.raw/*632.41*/("""
			"""),format.raw/*633.4*/("""let auxNum = arrM2[i].innerHTML;
			auxNum = auxNum.replace(/,/g,'');
			m2 += parseFloat(auxNum);
		"""),format.raw/*636.3*/("""}"""),format.raw/*636.4*/("""
		
		"""),format.raw/*638.3*/("""$("#totalCant").text(formatStandar(parseFloat(cant)));
		$("#totalKG").text(formatStandar(parseFloat(kg)));
		$("#totalM2").text(formatStandar(parseFloat(m2)));
	"""),format.raw/*641.2*/("""}"""),format.raw/*641.3*/("""
	
	"""),format.raw/*643.2*/("""const verificaMovimientos = () =>"""),format.raw/*643.35*/("""{"""),format.raw/*643.36*/("""
		
		"""),format.raw/*645.3*/("""document.getElementById("nombreBodegaOrigen").style.backgroundColor = "yellow";
		document.getElementById("nombreBodegaOrigen").setAttribute('disabled','true');
		
		document.getElementById("numeroGuia").style.backgroundColor = "yellow";
		document.getElementById("numeroGuia").setAttribute('disabled','true');
		
		document.getElementById("numGuiaCliente").style.backgroundColor = "yellow";
		document.getElementById("numGuiaCliente").setAttribute('disabled','true');
		
		document.getElementById("fechaGuia").style.backgroundColor = "yellow";
		document.getElementById("fechaGuia").setAttribute('disabled','true');

		document.getElementById("fechaIniTerGuia").style.backgroundColor = "yellow";
		document.getElementById("fechaIniTerGuia").setAttribute('disabled','true');
		
		
		let input = document.getElementsByClassName("cantDespacho");
		for (let i=0; i<input.length;i++)"""),format.raw/*662.36*/("""{"""),format.raw/*662.37*/("""
			"""),format.raw/*663.4*/("""input[i].style.backgroundColor = "yellow";
		    input[i].setAttribute('disabled','true');
	    """),format.raw/*665.6*/("""}"""),format.raw/*665.7*/("""
		
		"""),format.raw/*667.3*/("""input = document.getElementsByClassName("cantEquivalente");
		for (let i=0; i<input.length;i++)"""),format.raw/*668.36*/("""{"""),format.raw/*668.37*/("""
			"""),format.raw/*669.4*/("""input[i].style.backgroundColor = "yellow";
		    input[i].setAttribute('disabled','true');
	    """),format.raw/*671.6*/("""}"""),format.raw/*671.7*/("""

		"""),format.raw/*673.3*/("""let btnBuscar = document.getElementsByClassName("btnBuscar");
		for (let i=0; i<btnBuscar.length;i++)"""),format.raw/*674.40*/("""{"""),format.raw/*674.41*/("""
			"""),format.raw/*675.4*/("""btnBuscar[i].style.display = "none";
	    """),format.raw/*676.6*/("""}"""),format.raw/*676.7*/("""
		
		"""),format.raw/*678.3*/("""document.getElementById('verifica').style.visibility = 'hidden';
		document.getElementById('modifica').style.visibility = 'visible';
		document.getElementById('aplica').style.visibility = 'visible';
	"""),format.raw/*681.2*/("""}"""),format.raw/*681.3*/("""
	
	"""),format.raw/*683.2*/("""const modificaMovimientos = () =>"""),format.raw/*683.35*/("""{"""),format.raw/*683.36*/("""
		
		"""),format.raw/*685.3*/("""document.getElementById("nombreBodegaOrigen").style.backgroundColor = "white";
		document.getElementById("nombreBodegaOrigen").removeAttribute('disabled');
		
		document.getElementById("numeroGuia").style.backgroundColor = "white";
		document.getElementById("numeroGuia").removeAttribute('disabled');
		
		document.getElementById("numGuiaCliente").style.backgroundColor = "white";
		document.getElementById("numGuiaCliente").removeAttribute('disabled');
		
		document.getElementById("fechaGuia").style.backgroundColor = "white";
		document.getElementById("fechaGuia").removeAttribute('disabled');

		document.getElementById("fechaIniTerGuia").style.backgroundColor = "white";
		document.getElementById("fechaIniTerGuia").removeAttribute('disabled');

		
		let input = document.getElementsByClassName("cantDespacho");
		for (let i=0; i<input.length;i++)"""),format.raw/*702.36*/("""{"""),format.raw/*702.37*/("""
			"""),format.raw/*703.4*/("""input[i].style.backgroundColor = "white";
		    input[i].removeAttribute('disabled');
	    """),format.raw/*705.6*/("""}"""),format.raw/*705.7*/("""
		
		"""),format.raw/*707.3*/("""input = document.getElementsByClassName("cantEquivalente");
		for (let i=0; i<input.length;i++)"""),format.raw/*708.36*/("""{"""),format.raw/*708.37*/("""
			"""),format.raw/*709.4*/("""input[i].style.backgroundColor = "white";
		    input[i].removeAttribute('disabled');
	    """),format.raw/*711.6*/("""}"""),format.raw/*711.7*/("""

		"""),format.raw/*713.3*/("""let btnBuscar = document.getElementsByClassName("btnBuscar");
		for (let i=0; i<btnBuscar.length;i++)"""),format.raw/*714.40*/("""{"""),format.raw/*714.41*/("""
			"""),format.raw/*715.4*/("""btnBuscar[i].style.display = "block";
	    """),format.raw/*716.6*/("""}"""),format.raw/*716.7*/("""
		
		"""),format.raw/*718.3*/("""document.getElementById('verifica').style.visibility = 'visible';
		document.getElementById('modifica').style.visibility = 'hidden';
		document.getElementById('aplica').style.visibility = 'hidden';
		document.getElementById('cancelar').style.visibility = 'visible';
	"""),format.raw/*722.2*/("""}"""),format.raw/*722.3*/("""
	
	"""),format.raw/*724.2*/("""const confirmaMovimientos = () =>"""),format.raw/*724.35*/("""{"""),format.raw/*724.36*/("""
		"""),format.raw/*725.3*/("""document.getElementById('aplica').style.visibility = 'hidden';
		document.getElementById('modifica').style.visibility = 'hidden';
		document.getElementById('cancelar').style.visibility = 'hidden';
		document.getElementById('transporte').style.visibility = 'hidden';
		
		
		document.getElementById("nombreBodegaOrigen").removeAttribute('disabled');
		document.getElementById("numeroGuia").removeAttribute('disabled');
		document.getElementById("numGuiaCliente").removeAttribute('disabled');
		document.getElementById("fechaGuia").removeAttribute('disabled');
		document.getElementById("fechaIniTerGuia").removeAttribute('disabled');

		let input = document.getElementsByClassName("cantDespacho");
		for (let i=0; i<input.length;i++)"""),format.raw/*738.36*/("""{"""),format.raw/*738.37*/("""
		    """),format.raw/*739.7*/("""input[i].removeAttribute('disabled');
	    """),format.raw/*740.6*/("""}"""),format.raw/*740.7*/("""
		
		"""),format.raw/*742.3*/("""input = document.getElementsByClassName("cantEquivalente");
		for (let i=0; i<input.length;i++)"""),format.raw/*743.36*/("""{"""),format.raw/*743.37*/("""
		    """),format.raw/*744.7*/("""input[i].removeAttribute('disabled');
	    """),format.raw/*745.6*/("""}"""),format.raw/*745.7*/("""

		"""),format.raw/*747.3*/("""if($("#id_bodegaOrigen").val() == 0) """),format.raw/*747.40*/("""{"""),format.raw/*747.41*/("""
			"""),format.raw/*748.4*/("""alertify.alert('Antes debe seleccionar """),_display_(/*748.44*/mapDiccionario/*748.58*/.get("BODEGA")),format.raw/*748.72*/("""/PROYECTO de origen', function () """),format.raw/*748.106*/("""{"""),format.raw/*748.107*/("""
				"""),format.raw/*749.5*/("""modificaMovimientos();
			"""),format.raw/*750.4*/("""}"""),format.raw/*750.5*/(""");
		"""),format.raw/*751.3*/("""}"""),format.raw/*751.4*/("""else """),format.raw/*751.9*/("""{"""),format.raw/*751.10*/("""
			"""),format.raw/*752.4*/("""let totalCantidad = $("#totalCant").text();
			totalCantidad = totalCantidad.replace(/,/g,'');
			if(parseFloat(totalCantidad)>0)"""),format.raw/*754.35*/("""{"""),format.raw/*754.36*/("""
				"""),format.raw/*755.5*/("""document.getElementById('enCarga').style.display="block";
				document.getElementById("formGrabaDespacho").submit();
			"""),format.raw/*757.4*/("""}"""),format.raw/*757.5*/("""else"""),format.raw/*757.9*/("""{"""),format.raw/*757.10*/("""
				"""),format.raw/*758.5*/("""alertify.alert('No puede generar un despacho sin cantidades a trasladar', function () """),format.raw/*758.91*/("""{"""),format.raw/*758.92*/("""
					"""),format.raw/*759.6*/("""modificaMovimientos();
				"""),format.raw/*760.5*/("""}"""),format.raw/*760.6*/(""");
			"""),format.raw/*761.4*/("""}"""),format.raw/*761.5*/("""
		"""),format.raw/*762.3*/("""}"""),format.raw/*762.4*/("""
	"""),format.raw/*763.2*/("""}"""),format.raw/*763.3*/("""
	
	"""),format.raw/*765.2*/("""let extArray = new Array(".gif", ".jpg", ".png", ".jpeg", ".pdf", ".xls", ".doc", ".xlsx", ".docx", ".tar", ".zip", ".rar");
	const LimitAttach = (form, file) => """),format.raw/*766.38*/("""{"""),format.raw/*766.39*/("""
		
		"""),format.raw/*768.3*/("""if (!file) return;
		
		const $inputArchivos = document.querySelector("#docAdjunto");
		const archivosParaSubir = $inputArchivos.files;
		let tamanio = 0;
		let allowSubmit = false;
		
		for(let i=0; i<archivosParaSubir.length; i++)"""),format.raw/*775.48*/("""{"""),format.raw/*775.49*/("""
			"""),format.raw/*776.4*/("""tamanio += archivosParaSubir[i].size;
			let nombre = archivosParaSubir[i].name;
			let extencion = nombre.substring(nombre.lastIndexOf(".")).toLowerCase();
			for (let j = 0; j < extArray.length; j++) """),format.raw/*779.46*/("""{"""),format.raw/*779.47*/("""
				"""),format.raw/*780.5*/("""if (extArray[j] == extencion) """),format.raw/*780.35*/("""{"""),format.raw/*780.36*/(""" 
					"""),format.raw/*781.6*/("""allowSubmit = true;
				"""),format.raw/*782.5*/("""}"""),format.raw/*782.6*/("""
			"""),format.raw/*783.4*/("""}"""),format.raw/*783.5*/("""
			"""),format.raw/*784.4*/("""if(allowSubmit && i < archivosParaSubir.length -1)"""),format.raw/*784.54*/("""{"""),format.raw/*784.55*/("""
				"""),format.raw/*785.5*/("""allowSubmit = false;
			"""),format.raw/*786.4*/("""}"""),format.raw/*786.5*/("""
		"""),format.raw/*787.3*/("""}"""),format.raw/*787.4*/("""
		
		"""),format.raw/*789.3*/("""if (allowSubmit) """),format.raw/*789.20*/("""{"""),format.raw/*789.21*/("""
			"""),format.raw/*790.4*/("""if(tamanio > 200000000)"""),format.raw/*790.27*/("""{"""),format.raw/*790.28*/("""
				"""),format.raw/*791.5*/("""alert("Se permite máximo subir 200 MB,"
				+" lo que se intenta subir pesa: "+Math.round(tamanio/10000)/100+" MB");
				form.docAdjunto.value="";
			"""),format.raw/*794.4*/("""}"""),format.raw/*794.5*/("""else"""),format.raw/*794.9*/("""{"""),format.raw/*794.10*/("""
				"""),format.raw/*795.5*/("""alert("Documento subido con éxito");
				$("#txtBtn").text("Cambiar Documento");
			"""),format.raw/*797.4*/("""}"""),format.raw/*797.5*/("""
		"""),format.raw/*798.3*/("""}"""),format.raw/*798.4*/("""else"""),format.raw/*798.8*/("""{"""),format.raw/*798.9*/("""
			"""),format.raw/*799.4*/("""alert("Se permiten únicamente archivos con la extención: " 
			+ (extArray.join("  ")) + "\nPor favor, seleccione solo archivos con extenciones permitidas "
			+ "e intente de nuevo.");
			form.docAdjunto.value="";
		"""),format.raw/*803.3*/("""}"""),format.raw/*803.4*/("""
	"""),format.raw/*804.2*/("""}"""),format.raw/*804.3*/("""
		
		

	
"""),format.raw/*809.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,id_ot:Long,cabezeraOt:String,bodegaDestino:tables.BodegaEmpresa,listBodegasOrigen:List[List[String]],nuevoNumeroGuia:Long,hoy:String,vistaDetOrigen:String,id_cotizacion:Long,listaTransporte:List[tables.Transportista],id_comercial:Long): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,id_ot,cabezeraOt,bodegaDestino,listBodegasOrigen,nuevoNumeroGuia,hoy,vistaDetOrigen,id_cotizacion,listaTransporte,id_comercial)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,Long,String,tables.BodegaEmpresa,List[List[String]],Long,String,String,Long,List[tables.Transportista],Long) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,id_ot,cabezeraOt,bodegaDestino,listBodegasOrigen,nuevoNumeroGuia,hoy,vistaDetOrigen,id_cotizacion,listaTransporte,id_comercial) => apply(mapDiccionario,mapPermiso,userMnu,id_ot,cabezeraOt,bodegaDestino,listBodegasOrigen,nuevoNumeroGuia,hoy,vistaDetOrigen,id_cotizacion,listaTransporte,id_comercial)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizar/otDespachar.scala.html
                  HASH: d2dcf75470d0047b36fb84f39dfa8c3ff1a35fae
                  MATRIX: 1119->1|1564->353|1591->355|1607->363|1646->365|1674->368|1742->416|1772->420|2079->701|2212->812|2247->820|2382->929|2419->945|2451->950|2594->1066|2620->1071|2703->1127|2737->1140|2826->1202|2859->1214|3835->2163|3857->2176|3886->2184|3986->2257|4008->2270|4041->2282|4433->2646|4473->2647|4512->2658|4663->2782|4699->2797|4775->2854|4814->2855|4853->2866|5004->2990|5040->3005|5238->3172|5273->3180|6599->4478|6619->4488|6672->4519|6702->4521|6722->4531|6774->4560|6807->4563|6838->4564|6875->4572|6901->4575|6966->4610|6997->4611|7028->4612|7063->4617|7094->4618|7157->4651|7188->4652|7237->4673|7262->4676|7691->5077|7711->5087|7766->5120|7796->5122|7816->5132|7870->5163|7910->5174|7936->5177|7984->5197|8009->5200|8520->5683|8562->5703|10274->7387|10298->7401|10334->7415|10812->7865|10836->7879|10872->7893|11023->8016|11047->8030|11083->8044|11113->8045|11204->8109|11253->8141|11293->8142|11330->8151|11395->8188|11411->8194|11440->8201|11533->8266|11549->8272|11579->8280|11669->8342|11685->8348|11714->8355|11745->8358|11761->8364|11805->8385|11885->8437|11901->8443|11930->8450|12004->8496|12020->8502|12049->8509|12123->8555|12139->8561|12168->8568|12229->8597|12264->8604|13406->9719|13486->9777|13519->9779|13551->9783|13708->9911|13738->9912|13770->9916|13867->9985|13896->9986|13930->9992|14013->10046|14043->10047|14075->10051|14313->10261|14342->10262|14376->10268|14445->10308|14475->10309|14507->10313|14649->10426|14679->10427|14712->10432|14894->10585|14924->10586|14958->10592|15047->10653|15076->10654|15109->10658|15139->10659|15173->10665|15250->10714|15279->10715|15311->10719|15340->10720|15371->10723|15400->10724|15431->10727|15489->10756|15519->10757|15550->10760|15689->10870|15719->10871|15754->10878|15936->11031|15966->11032|16006->11043|16112->11120|16142->11121|16173->11124|16202->11125|16231->11126|16264->11131|16293->11132|16380->11190|16410->11191|16441->11194|16666->11391|16708->11411|16777->11452|16806->11453|16838->11457|16905->11495|16935->11496|16966->11499|17015->11519|17045->11520|17077->11524|17128->11547|17165->11562|17199->11568|17228->11569|17260->11573|17289->11574|17321->11578|17361->11590|17398->11605|17522->11700|17552->11701|17595->11715|17866->11957|17896->11958|17935->11968|17987->11991|18017->11992|18057->12003|18168->12085|18198->12086|18239->12098|18290->12121|18327->12136|18370->12150|18400->12151|18441->12163|18471->12164|18510->12174|18561->12196|18591->12197|18631->12208|18697->12245|18727->12246|18768->12258|18828->12289|18858->12290|18899->12302|18929->12303|18966->12312|18996->12313|19035->12323|19065->12324|19098->12329|19127->12330|19157->12332|19186->12333|19218->12337|19315->12405|19345->12406|19376->12409|19501->12505|19531->12506|19574->12520|19841->12758|19871->12759|19905->12765|19949->12780|19979->12781|20014->12788|20059->12805|20083->12819|20119->12833|20205->12889|20236->12890|20272->12898|20379->12977|20408->12978|20444->12986|20473->12987|20506->12991|20536->12992|20571->12999|21085->13484|21115->13485|21155->13496|22053->14365|22083->14366|22122->14376|22334->14559|22364->14560|22404->14571|22585->14723|22615->14724|22659->14739|22769->14820|22799->14821|22834->14828|22863->14829|22892->14830|23510->15419|23540->15420|23576->15428|23974->15797|24004->15798|24041->15807|24432->16170|24461->16171|24494->16175|24524->16176|24561->16185|24899->16494|24929->16495|24968->16505|25022->16531|25052->16532|25089->16541|25177->16601|25206->16602|25241->16609|25270->16610|25305->16617|25353->16637|25382->16638|25415->16643|25444->16644|25480->16652|25509->16653|25541->16657|25570->16658|25604->16664|25662->16693|25692->16694|25723->16697|25789->16734|25819->16735|25851->16739|25919->16779|25943->16793|25979->16807|26093->16891|26124->16892|26161->16901|26190->16902|26223->16907|26252->16908|26284->16912|26313->16913|26349->16921|26480->17023|26510->17024|26546->17032|26718->17175|26748->17176|26789->17188|26896->17266|26926->17267|26958->17271|26987->17272|27016->17273|27211->17439|27241->17440|27274->17445|27430->17572|27460->17573|27500->17585|27599->17655|27629->17656|27664->17663|27735->17706|27764->17707|27797->17712|27826->17713|27858->17717|27887->17718|27919->17722|27988->17763|28017->17764|28049->17768|28078->17769|28111->17774|28458->18092|28488->18093|28519->18096|28567->18115|28597->18116|28629->18120|28688->18151|28717->18152|28751->18158|28880->18258|28910->18259|28942->18263|29006->18299|29035->18300|29069->18306|31862->21071|31891->21072|31923->21076|32038->21162|32068->21163|32099->21166|32440->21478|32470->21479|32505->21486|32647->21599|32677->21600|32710->21605|32842->21709|32871->21710|32902->21713|32931->21714|32962->21717|33071->21797|33101->21798|33133->21802|33243->21883|33273->21884|33306->21889|33746->22301|33775->22302|33808->22307|33837->22308|33866->22309|33900->22314|33930->22315|33970->22327|34173->22501|34203->22502|34239->22510|34387->22629|34417->22630|34451->22636|34577->22734|34606->22735|34638->22739|34667->22740|34703->22748|34982->22999|35011->23000|35042->23003|35086->23019|35115->23020|35147->23024|35241->23089|35271->23090|35302->23093|35591->23353|35621->23354|35653->23358|35782->23459|35811->23460|35845->23466|35911->23503|35941->23504|35973->23508|36102->23609|36131->23610|36165->23616|36231->23653|36261->23654|36293->23658|36422->23759|36451->23760|36485->23766|36675->23928|36704->23929|36736->23933|36798->23966|36828->23967|36862->23973|37770->24852|37800->24853|37832->24857|37956->24953|37985->24954|38019->24960|38143->25055|38173->25056|38205->25060|38329->25156|38358->25157|38390->25161|38520->25262|38550->25263|38582->25267|38652->25309|38681->25310|38715->25316|38943->25516|38972->25517|39004->25521|39066->25554|39096->25555|39130->25561|40011->26413|40041->26414|40073->26418|40192->26509|40221->26510|40255->26516|40379->26611|40409->26612|40441->26616|40560->26707|40589->26708|40621->26712|40751->26813|40781->26814|40813->26818|40884->26861|40913->26862|40947->26868|41242->27135|41271->27136|41303->27140|41365->27173|41395->27174|41426->27177|42187->27909|42217->27910|42252->27917|42323->27960|42352->27961|42386->27967|42510->28062|42540->28063|42575->28070|42646->28113|42675->28114|42707->28118|42773->28155|42803->28156|42835->28160|42903->28200|42927->28214|42963->28228|43027->28262|43058->28263|43091->28268|43145->28294|43174->28295|43207->28300|43236->28301|43269->28306|43299->28307|43331->28311|43489->28440|43519->28441|43552->28446|43700->28566|43729->28567|43761->28571|43791->28572|43824->28577|43939->28663|43969->28664|44003->28670|44058->28697|44087->28698|44121->28704|44150->28705|44181->28708|44210->28709|44240->28711|44269->28712|44301->28716|44492->28878|44522->28879|44556->28885|44817->29117|44847->29118|44879->29122|45110->29324|45140->29325|45173->29330|45232->29360|45262->29361|45297->29368|45349->29392|45378->29393|45410->29397|45439->29398|45471->29402|45550->29452|45580->29453|45613->29458|45665->29482|45694->29483|45725->29486|45754->29487|45788->29493|45834->29510|45864->29511|45896->29515|45948->29538|45978->29539|46011->29544|46189->29694|46218->29695|46250->29699|46280->29700|46313->29705|46425->29789|46454->29790|46485->29793|46514->29794|46546->29798|46575->29799|46607->29803|46852->30020|46881->30021|46911->30023|46940->30024|46978->30034
                  LINES: 28->1|36->5|37->6|37->6|37->6|38->7|38->7|40->9|44->13|44->13|46->15|48->17|48->17|49->18|53->22|53->22|54->23|54->23|55->24|55->24|79->48|79->48|79->48|81->50|81->50|81->50|90->59|90->59|91->60|94->63|94->63|97->66|97->66|98->67|101->70|101->70|106->75|107->76|139->108|139->108|139->108|139->108|139->108|139->108|139->108|139->108|139->108|139->108|139->108|139->108|139->108|139->108|139->108|139->108|139->108|140->109|140->109|152->121|152->121|152->121|152->121|152->121|152->121|152->121|152->121|153->122|153->122|175->144|175->144|204->173|204->173|204->173|214->183|214->183|214->183|217->186|217->186|217->186|217->186|221->190|221->190|221->190|222->191|222->191|222->191|222->191|223->192|223->192|223->192|224->193|224->193|224->193|224->193|224->193|224->193|225->194|225->194|225->194|226->195|226->195|226->195|227->196|227->196|227->196|229->198|230->199|263->232|263->232|264->233|268->237|273->242|273->242|274->243|276->245|276->245|278->247|278->247|278->247|279->248|284->253|284->253|286->255|286->255|286->255|287->256|288->257|288->257|289->258|291->260|291->260|292->261|294->263|294->263|294->263|294->263|295->264|296->265|296->265|297->266|297->266|298->267|298->267|300->269|300->269|300->269|301->270|303->272|303->272|304->273|307->276|307->276|308->277|309->278|309->278|310->279|310->279|310->279|312->281|312->281|314->283|314->283|315->284|318->287|318->287|320->289|320->289|322->291|322->291|322->291|323->292|323->292|323->292|324->293|324->293|324->293|325->294|325->294|325->294|325->294|326->295|326->295|326->295|329->298|329->298|330->299|337->306|337->306|338->307|338->307|338->307|339->308|339->308|339->308|340->309|340->309|340->309|341->310|341->310|342->311|342->311|343->312|343->312|343->312|344->313|344->313|344->313|345->314|346->315|346->315|347->316|347->316|348->317|348->317|349->318|349->318|350->319|350->319|351->320|351->320|353->322|355->324|355->324|356->325|358->327|358->327|359->328|366->335|366->335|367->336|367->336|367->336|368->337|368->337|368->337|368->337|368->337|368->337|369->338|371->340|371->340|372->341|372->341|372->341|372->341|373->342|386->355|386->355|387->356|404->373|404->373|405->374|411->380|411->380|412->381|415->384|415->384|416->385|417->386|417->386|418->387|418->387|418->387|428->397|428->397|429->398|438->407|438->407|439->408|448->417|448->417|448->417|448->417|449->418|457->426|457->426|458->427|459->428|459->428|460->429|461->430|461->430|462->431|462->431|463->432|464->433|464->433|465->434|465->434|466->435|466->435|467->436|467->436|470->439|470->439|470->439|471->440|471->440|471->440|472->441|472->441|472->441|472->441|472->441|472->441|474->443|474->443|475->444|475->444|475->444|475->444|477->446|478->447|478->447|479->448|482->451|482->451|483->452|484->453|484->453|485->454|485->454|485->454|489->458|489->458|490->459|492->461|492->461|494->463|496->465|496->465|497->466|498->467|498->467|499->468|499->468|500->469|500->469|501->470|502->471|502->471|503->472|503->472|507->476|522->491|522->491|523->492|523->492|523->492|524->493|525->494|525->494|527->496|528->497|528->497|529->498|530->499|530->499|532->501|598->567|598->567|600->569|600->569|600->569|601->570|606->575|606->575|607->576|608->577|608->577|609->578|612->581|612->581|613->582|613->582|614->583|614->583|614->583|615->584|615->584|615->584|616->585|621->590|621->590|622->591|622->591|622->591|622->591|622->591|625->594|627->596|627->596|628->597|629->598|629->598|630->599|632->601|632->601|633->602|633->602|635->604|638->607|638->607|639->608|640->609|640->609|642->611|642->611|642->611|643->612|651->620|651->620|652->621|655->624|655->624|657->626|657->626|657->626|658->627|661->630|661->630|663->632|663->632|663->632|664->633|667->636|667->636|669->638|672->641|672->641|674->643|674->643|674->643|676->645|693->662|693->662|694->663|696->665|696->665|698->667|699->668|699->668|700->669|702->671|702->671|704->673|705->674|705->674|706->675|707->676|707->676|709->678|712->681|712->681|714->683|714->683|714->683|716->685|733->702|733->702|734->703|736->705|736->705|738->707|739->708|739->708|740->709|742->711|742->711|744->713|745->714|745->714|746->715|747->716|747->716|749->718|753->722|753->722|755->724|755->724|755->724|756->725|769->738|769->738|770->739|771->740|771->740|773->742|774->743|774->743|775->744|776->745|776->745|778->747|778->747|778->747|779->748|779->748|779->748|779->748|779->748|779->748|780->749|781->750|781->750|782->751|782->751|782->751|782->751|783->752|785->754|785->754|786->755|788->757|788->757|788->757|788->757|789->758|789->758|789->758|790->759|791->760|791->760|792->761|792->761|793->762|793->762|794->763|794->763|796->765|797->766|797->766|799->768|806->775|806->775|807->776|810->779|810->779|811->780|811->780|811->780|812->781|813->782|813->782|814->783|814->783|815->784|815->784|815->784|816->785|817->786|817->786|818->787|818->787|820->789|820->789|820->789|821->790|821->790|821->790|822->791|825->794|825->794|825->794|825->794|826->795|828->797|828->797|829->798|829->798|829->798|829->798|830->799|834->803|834->803|835->804|835->804|840->809
                  -- GENERATED --
              */
          