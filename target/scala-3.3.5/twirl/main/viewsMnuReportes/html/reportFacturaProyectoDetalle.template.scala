
package viewsMnuReportes.html

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

object reportFacturaProyectoDetalle extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template20[Map[String,String],Map[String,String],utilities.UserMnu,String,List[List[String]],List[List[String]],List[String],tables.BodegaEmpresa,tables.Proyecto,List[Double],List[List[String]],Long,List[List[String]],tables.Cliente,List[List[String]],Map[String,List[List[String]]],Long,List[tables.TipoReferencia],String,List[tables.CotizaSolucion],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu, idTipoUsuario: String,
inicioPer: List[List[String]],guiasPer: List[List[String]],
fechas: List[String],
bodega: tables.BodegaEmpresa, proyecto: tables.Proyecto, tasaCambio: List[Double],
resumenSubtotales: List[List[String]], id_bodegaEmpresa: Long, finalPer: List[List[String]], cliente: tables.Cliente,
detalleAjuste: List[List[String]],
mapReportPorGuia10: Map[String,List[List[String]]], cantDec: Long, listReferencias: List[tables.TipoReferencia],
oc: String, listSol: List[tables.CotizaSolucion]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*9.1*/("""
	"""),format.raw/*10.2*/("""<!-- ANCLA CABECERAS DE TABLAS -->
	<style type="text/css"> 
        thead tr th """),format.raw/*12.21*/("""{"""),format.raw/*12.22*/(""" 
            """),format.raw/*13.13*/("""position: sticky;
            top: 0;
        """),format.raw/*15.9*/("""}"""),format.raw/*15.10*/("""
    """),format.raw/*16.5*/("""</style>



	
	
	<form id="formProforma" class="formulario" method="post" action="/generarProformaPdfXlsxXmlJson/">
	
			"""),_display_(if(mapPermiso.get("parametro.proformaListar-llenarApiRelBase")!=null && mapPermiso.get("parametro.proformaListar-llenarApiRelBase").equals("1") && mapPermiso.get("enviarApiFactura")!=null)/*24.193*/{_display_(Seq[Any](format.raw/*24.194*/("""
				"""),format.raw/*25.5*/("""<div id="modalAdicRelBase" class="modal" data-backdrop="static" data-keyboard="false">
				   	<div class='modal-dialog modal-dialog-scrollable' style="min-width: 60%;" role='document'>
					   	<div class='modal-content'>
							<div class='modal-header'>
								<h5 class="modal-title">CONCEPTO, DESCRIPCION Y OBSERVACIONES</h5>
							</div>
							<div class="modal-body">
								<table id="tablaSoluciones" class="table table-sm table-hover table-bordered table-condensed table-fluid">
										<thead style="background-color: #eeeeee">
											<TR> 
												<th style="width:40%; white-space: nowrap;">
													CONCEPTO<br>
													<select class="form-control form-control-sm" name="id_cotizaSolucion">
														"""),_display_(/*38.16*/for(lista <- listSol) yield /*38.37*/{_display_(Seq[Any](format.raw/*38.38*/("""
															"""),format.raw/*39.16*/("""<option value=""""),_display_(/*39.32*/lista/*39.37*/.getId()),format.raw/*39.45*/("""">"""),_display_(/*39.48*/lista/*39.53*/.getSolucion()),format.raw/*39.67*/("""</option>
														""")))}),format.raw/*40.16*/("""
													"""),format.raw/*41.14*/("""</select>
												</th>
												<th>
													DESCRIPCION <textarea class="form-control form-control-sm"
																	rows="2"
																	name="sol_description"
																	autocomplete="off"> </textarea>
												</th>
											</TR>
											<TR> 
												<th colspan="2" >
													OBSERVACIONES <textarea class="form-control form-control-sm"
																	rows="8"
																	name="sol_observaciones"
																	autocomplete="off">"""),_display_(/*55.38*/Html("PERIODO: de "+fechas.get(2)+" a "+fechas.get(3))),format.raw/*55.92*/("""</textarea>
												</th>
											</TR>
										</thead>
									</table>
								<div align="center">
									<button class='btn btn-sm btn-success' style='font-size: 10px;' data-dismiss='modal' onclick="document.getElementById('formProforma').submit()">
										GENERAR """),_display_(/*62.20*/mapDiccionario/*62.34*/.get("PROFORMA")),format.raw/*62.50*/("""
									"""),format.raw/*63.10*/("""</button>
									<button type='button' class='btn btn-sm btn-light' style='font-size: 10px;' data-dismiss='modal'>Cancelar</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			""")))} else {null} ),format.raw/*70.5*/("""
	
		"""),format.raw/*72.3*/("""<input type="hidden" name="idBodega" value=""""),_display_(/*72.48*/bodega/*72.54*/.id),format.raw/*72.57*/("""">
		<input type="hidden" name="fechaDesde" value=""""),_display_(/*73.50*/fechas/*73.56*/.get(0)),format.raw/*73.63*/("""">
		<input type="hidden" name="fechaHasta" value=""""),_display_(/*74.50*/fechas/*74.56*/.get(1)),format.raw/*74.63*/("""">
		<input type="hidden" name="uf" value=""""),_display_(/*75.42*/tasaCambio/*75.52*/.get(0)),format.raw/*75.59*/("""">
		<input type="hidden" name="usd" value=""""),_display_(/*76.43*/tasaCambio/*76.53*/.get(1)),format.raw/*76.60*/("""">
		<input type="hidden" name="eur" value=""""),_display_(/*77.43*/tasaCambio/*77.53*/.get(2)),format.raw/*77.60*/("""">
		<input type="hidden" id="esVenta" name="esVenta">
		<div id='modalAgregaReferencias' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
			<div class='modal-dialog modal-dialog-scrollable' style="max-width: 50% !important;" role='document'>
			   	<div class='modal-content'>
					<div class='modal-header'>
						<h5 class='modal-title'>REFERENCIAS</h5>
						<div align="right">
							"""),_display_(if(mapPermiso.get("parametro.proformaListar-llenarApiRelBase")!=null && mapPermiso.get("parametro.proformaListar-llenarApiRelBase").equals("1") && mapPermiso.get("enviarApiFactura")!=null)/*85.197*/{_display_(Seq[Any](format.raw/*85.198*/("""
								"""),format.raw/*86.9*/("""<button class='btn btn-sm btn-success' style='font-size: 10px;' data-dismiss='modal' 
									onclick="$('#modalAdicRelBase').modal('show');">
									GENERAR """),_display_(/*88.19*/mapDiccionario/*88.33*/.get("PROFORMA")),format.raw/*88.49*/("""
								"""),format.raw/*89.9*/("""</button>
							""")))}else/*90.13*/{_display_(Seq[Any](format.raw/*90.14*/("""
								"""),format.raw/*91.9*/("""<button class='btn btn-sm btn-success' style='font-size: 10px;' data-dismiss='modal' onclick="document.getElementById('formProforma').submit()">
									GENERAR """),_display_(/*92.19*/mapDiccionario/*92.33*/.get("PROFORMA")),format.raw/*92.49*/("""
								"""),format.raw/*93.9*/("""</button>
							""")))}),format.raw/*94.9*/("""
							
					        """),format.raw/*96.14*/("""<button type='button' class='close' data-dismiss='modal' aria-label='Close' 
									onclick='document.getElementById("btnArr").disabled=false; document.getElementById("btnVta").disabled=false;'>
					          	<span aria-hidden='true'>&times;</span>
					        </button>
						</div>
					</div>
				    <div class="modal-body">
						<table id="referencia" class="table table-hover table-sm table-condensed table-bordered table-fluid">
						 	<tr>
						 		<td colspan="4">REFERENCIAS:</td>
						 		<td colspan="2">
						 			<div align="right">
						 				<a type="button"  class="btn btn-sm btn-info" style='font-size: 10px;' onclick="agregarReferencia()">agregar referencia</a>
						 			</div>
						 		</td>
						 	</tr>
						 	<tr>
						 		<td>NroLinRef</td>
						 		<td>TpoDocRef</td>
						 		<td>FolioRef</td>
						 		<td>FchRef</td>
						 		<td>RazonRef</td>
						 		<td width="5%">del</td>
						 	</tr>
					 	</table>
					<br>
						"""),_display_(if(mapPermiso.get("parametro.proformaListar-llenarWebIConstruye")!=null && mapPermiso.get("parametro.proformaListar-llenarWebIConstruye").equals("1"))/*122.158*/{_display_(Seq[Any](format.raw/*122.159*/("""
							"""),format.raw/*123.8*/("""<div class="mb-3">
							  <label for="comentarios" class="form-label">COMENTARIOS:</label>
							  <textarea class="form-control" id="comentarios" rows="2"
												name="comentarios" 
		  										autocomplete="off"></textarea>
							</div>
						""")))}else/*129.12*/{_display_(Seq[Any](format.raw/*129.13*/("""
							"""),format.raw/*130.8*/("""<input type="text" name="comentarios" value="" style="display: none">
						""")))}),format.raw/*131.8*/("""
				   	"""),format.raw/*132.9*/("""</div>
			   	</div>
			</div>
		</div>
	</form>
	
	<script>
	
			let validarCliente = (id_cliente) =>"""),format.raw/*140.40*/("""{"""),format.raw/*140.41*/("""
				"""),format.raw/*141.5*/("""if(id_cliente=="0")"""),format.raw/*141.24*/("""{"""),format.raw/*141.25*/("""
						"""),format.raw/*142.7*/("""let msg = "ERROR 1: El RUT del cliente no existe en RELBASE o no esta asociado el cliente al proyecto.<br>"
							+"No se puede generar la proforma, antes debe corregir";
			    		alertify.alert(msg, function () """),format.raw/*144.42*/("""{"""),format.raw/*144.43*/("""}"""),format.raw/*144.44*/(""");
				"""),format.raw/*145.5*/("""}"""),format.raw/*145.6*/("""else"""),format.raw/*145.10*/("""{"""),format.raw/*145.11*/("""
					"""),format.raw/*146.6*/("""var formData = new FormData();
					formData.append('id_cliente', id_cliente);
					$.ajax("""),format.raw/*148.13*/("""{"""),format.raw/*148.14*/("""
						"""),format.raw/*149.7*/("""url: "/validarClienteAjax/",
			            type: "POST",
			            method: "POST",
			            data: formData,
			            cache: false,
			            contentType: false,
				     	processData: false,
				     	success: function(rs)"""),format.raw/*156.32*/("""{"""),format.raw/*156.33*/("""
				     		"""),format.raw/*157.12*/("""if(rs=="0")"""),format.raw/*157.23*/("""{"""),format.raw/*157.24*/("""
								"""),format.raw/*158.9*/("""let msg = "ERROR 2: El RUT del cliente no existe en RELBASE o no esta asociado el cliente al proyecto.<br>"
									+"No se puede generar la proforma, antes debe corregir";
					    		alertify.alert(msg, function () """),format.raw/*160.44*/("""{"""),format.raw/*160.45*/("""}"""),format.raw/*160.46*/(""");
							"""),format.raw/*161.8*/("""}"""),format.raw/*161.9*/("""else"""),format.raw/*161.13*/("""{"""),format.raw/*161.14*/("""
								 """),format.raw/*162.10*/("""$('#modalAgregaReferencias').modal('show');
							"""),format.raw/*163.8*/("""}"""),format.raw/*163.9*/("""
				     	"""),format.raw/*164.11*/("""}"""),format.raw/*164.12*/("""
			        """),format.raw/*165.12*/("""}"""),format.raw/*165.13*/(""");
				"""),format.raw/*166.5*/("""}"""),format.raw/*166.6*/("""
				
			"""),format.raw/*168.4*/("""}"""),format.raw/*168.5*/("""
			
				

		 	"""),format.raw/*172.5*/("""let eliminaReferencia = (nodo) =>"""),format.raw/*172.38*/("""{"""),format.raw/*172.39*/("""
		 			"""),format.raw/*173.7*/("""nodo.parentNode.parentNode.remove();
		 			let tabla = document.getElementById('referencia');
		 			for (let i = 2; i < tabla.rows.length; i++)"""),format.raw/*175.50*/("""{"""),format.raw/*175.51*/("""
		 				"""),format.raw/*176.8*/("""let cellsOfRow = tabla.rows[i].getElementsByTagName('td');
		 				cellsOfRow[0].innerHTML=i-1;
		 			"""),format.raw/*178.7*/("""}"""),format.raw/*178.8*/("""
		 	"""),format.raw/*179.5*/("""}"""),format.raw/*179.6*/("""
	 		"""),format.raw/*180.5*/("""let agregarReferencia = () =>"""),format.raw/*180.34*/("""{"""),format.raw/*180.35*/("""
	 			"""),format.raw/*181.6*/("""let tabla = document.getElementById('referencia');
				let row = tabla.insertRow(tabla.rows.length);
				let cell = row.insertCell(0);
				let indice = tabla.rows.length-2;
				cell.innerHTML=indice+"<input type='hidden' name='nroLinRef[]' value='"+indice+"'>";
				cell = row.insertCell(1);
				let listado = "<select  name='tpoDocRef[]'>";
				"""),_display_(/*188.6*/for(referencia <- listReferencias) yield /*188.40*/{_display_(Seq[Any](format.raw/*188.41*/("""
					"""),format.raw/*189.6*/("""listado = listado + "<option value='"""),_display_(/*189.43*/referencia/*189.53*/.codigo),format.raw/*189.60*/("""'>"""),_display_(/*189.63*/referencia/*189.73*/.concepto),format.raw/*189.82*/("""</option>";
				""")))}),format.raw/*190.6*/("""
				"""),format.raw/*191.5*/("""listado = listado + "</select>";
				cell.innerHTML=listado;
				cell=row.insertCell(2);
				cell.innerHTML="<input type='text' name='folioRef[]' value=''>";
				cell=row.insertCell(3);
				cell.innerHTML="<input type='date' name='fchRef[]' value=''>";
				cell=row.insertCell(4);
				cell.innerHTML="<input type='text' name='razonRef[]' value=''>";
				cell=row.insertCell(5);
				cell.innerHTML="<a class='btn btn-mini btn-danger' onclick='eliminaReferencia(this)'>X</a>";
	 		"""),format.raw/*201.5*/("""}"""),format.raw/*201.6*/("""
	"""),format.raw/*202.2*/("""</script>

	
	"""),_display_(/*205.3*/main("")/*205.11*/ {_display_(Seq[Any](format.raw/*205.13*/("""

		"""),_display_(/*207.4*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*207.52*/("""
		
		"""),_display_(/*209.4*/modalEquipoDescripcion()),format.raw/*209.28*/("""
		"""),_display_(/*210.4*/modalVerCotizacion()),format.raw/*210.24*/("""
		
		"""),format.raw/*212.3*/("""<div id="mostrarmostrar" style="display:none;">
			"""),_display_(/*213.5*/barraTitulo(mapDiccionario, "DETALLE EP/FACTURA PROFORMA " + mapDiccionario.get("BODEGA") + "/PROYECTO: " + bodega.getNombre(),
				"PERIODO: desde " + fechas.get(2) +" --- hasta: " +  fechas.get(3))),format.raw/*214.72*/("""
				
			"""),format.raw/*216.4*/("""<div class="noprint">
				<table class="table table-sm table-condensed table-fluid">
					<tr>
						<td width="25%">
							<div class="input-group">
						  		<div class="input-group-prepend">
						    		<span class="input-group-text" id="basic-addon1">BUSCAR</span>
						  		</div>
						  		<input type="text" class="form-control left"
									id="searchTermtablaPrincipal"
									onkeyup="doSearch3('tablaPrincipal');">
							</div>
						</td>
						<td>
							<div align="center">
								<button type="button"  class="btn btn-sm btn-success" onclick="document.getElementById('excel').submit()">
									Exportar a Excel
								</button>
								<button type="button" class="btn btn-sm btn-success" tabindex="-1" onclick="window.print(); return false;" >
									Imprimir
								</button>
								<button type="button"  class="btn btn-sm btn-success" 
									onclick="history.go(-1);return false;">
									Volver
								</button>
							</div>
						</td>
						<td width="25%">
							"""),_display_(if(!idTipoUsuario.equals("7"))/*244.39*/{_display_(Seq[Any](format.raw/*244.40*/("""
								"""),format.raw/*245.9*/("""<div align="right">
											HACER """),_display_(/*246.19*/mapDiccionario/*246.33*/.get("PROFORMA")),format.raw/*246.49*/(""": 
										<button id="btnArr" type="button" class="btn btn-mini btn-info" tabindex="-1" 
											onclick=" this.disabled = true;
												if(parseFloat(numero.formatBack($('#resumenTotal12').text()))>0)"""),format.raw/*249.77*/("""{"""),format.raw/*249.78*/("""
													"""),format.raw/*250.14*/("""$('#esVenta').val('0');
													"""),_display_(if(mapPermiso.get("parametro.proformaListar-llenarApiRelBase")!=null && mapPermiso.get("parametro.proformaListar-llenarApiRelBase").equals("1") && mapPermiso.get("enviarApiFactura")!=null)/*251.203*/{_display_(Seq[Any](format.raw/*251.204*/("""
														"""),format.raw/*252.15*/("""validarCliente('"""),_display_(/*252.32*/cliente/*252.39*/.id),format.raw/*252.42*/("""');
													""")))}else/*253.19*/{_display_(Seq[Any](format.raw/*253.20*/("""
														"""),format.raw/*254.15*/("""$('#modalAgregaReferencias').modal('show');
													""")))}),format.raw/*255.15*/("""
												"""),format.raw/*256.13*/("""}"""),format.raw/*256.14*/("""else"""),format.raw/*256.18*/("""{"""),format.raw/*256.19*/("""
													"""),format.raw/*257.14*/("""alertify.alert('No hay valor de alquiler a emitir', function () """),format.raw/*257.78*/("""{"""),format.raw/*257.79*/("""}"""),format.raw/*257.80*/(""");
												"""),format.raw/*258.13*/("""}"""),format.raw/*258.14*/("""">
											DE """),_display_(/*259.16*/mapDiccionario/*259.30*/.get("ARRIENDO")),format.raw/*259.46*/("""
										"""),format.raw/*260.11*/("""</button>
										
										
										<button id="btnVta" type="button" class="btn btn-mini btn-info" tabindex="-1" 
											onclick=" this.disabled = true;
											if(parseFloat(numero.formatBack($('#resumenTotal22').text()))>0)"""),format.raw/*265.76*/("""{"""),format.raw/*265.77*/("""
												"""),format.raw/*266.13*/("""$('#esVenta').val('1');
												"""),_display_(if(mapPermiso.get("parametro.proformaListar-llenarApiRelBase")!=null && mapPermiso.get("parametro.proformaListar-llenarApiRelBase").equals("1") && mapPermiso.get("enviarApiFactura")!=null)/*267.202*/{_display_(Seq[Any](format.raw/*267.203*/("""
													"""),format.raw/*268.14*/("""validarCliente('"""),_display_(/*268.31*/cliente/*268.38*/.id),format.raw/*268.41*/("""');
												""")))}else/*269.18*/{_display_(Seq[Any](format.raw/*269.19*/("""
													"""),format.raw/*270.14*/("""$('#modalAgregaReferencias').modal('show');
												""")))}),format.raw/*271.14*/("""
											"""),format.raw/*272.12*/("""}"""),format.raw/*272.13*/("""else"""),format.raw/*272.17*/("""{"""),format.raw/*272.18*/("""
												"""),format.raw/*273.13*/("""alertify.alert('No hay valor de venta a emitir', function () """),format.raw/*273.74*/("""{"""),format.raw/*273.75*/("""}"""),format.raw/*273.76*/(""");
											"""),format.raw/*274.12*/("""}"""),format.raw/*274.13*/("""">
											DE VENTA
										</button>
								</div>
							""")))} else {null} ),format.raw/*278.9*/("""
						"""),format.raw/*279.7*/("""</td>
					</tr>
				</table>
			</div>
			
			<form id="excel" class="formulario" method="post" action="/reportFacturaProyectoDetExcel/">
				<input type="hidden" name="fechaDesde" value=""""),_display_(/*285.52*/fechas/*285.58*/.get(0)),format.raw/*285.65*/("""">
				<input type="hidden" name="fechaHasta" value=""""),_display_(/*286.52*/fechas/*286.58*/.get(1)),format.raw/*286.65*/("""">
				<input type="hidden" name="uf" value=""""),_display_(/*287.44*/tasaCambio/*287.54*/.get(0)),format.raw/*287.61*/("""">
				<input type="hidden" name="usd" value=""""),_display_(/*288.45*/tasaCambio/*288.55*/.get(1)),format.raw/*288.62*/("""">
				<input type="hidden" name="eur" value=""""),_display_(/*289.45*/tasaCambio/*289.55*/.get(2)),format.raw/*289.62*/("""">
				<input type="hidden" name="id_bodega" value=""""),_display_(/*290.51*/bodega/*290.57*/.getId()),format.raw/*290.65*/("""">
			</form>
		

			
			<input type="hidden" id="TA">
			<input type="hidden" id="TV">
			<input type="hidden" id="TCFI">
			
			<table class="table table-sm table-bordered table-condensed table-fluid">
					 <TR> 
						<td width="44%" colspan="3" style= "text-align: left; vertical-align:top;">
							<table class="table table-sm table-hover table-bordered table-condensed table-fluid">
								"""),_display_(if(!cliente.rut.equals(null))/*303.39*/{_display_(Seq[Any](format.raw/*303.40*/("""
									"""),format.raw/*304.10*/("""<tr style= "background-color: #F2F5A9;">
										<th style="text-align:left;">CLIENTE</th>
										<th style="text-align:left;">"""),_display_(/*306.41*/cliente/*306.48*/.rut),format.raw/*306.52*/(""" """),format.raw/*306.53*/("""-- """),_display_(/*306.57*/cliente/*306.64*/.nombre),format.raw/*306.71*/("""</th>
									</tr>
								""")))} else {null} ),format.raw/*308.10*/("""
								"""),format.raw/*309.9*/("""<tr style= "background-color: #F2F5A9;">
									<th style="text-align:left;">"""),_display_(/*310.40*/mapDiccionario/*310.54*/.get("BODEGA")),format.raw/*310.68*/("""</th>
									<TH style="text-align:left;">"""),_display_(/*311.40*/bodega/*311.46*/.nombre.toUpperCase()),format.raw/*311.67*/("""</TH>
								</tr>
								<tr style= "background-color: #F2F5A9;">
									<th style="text-align:left;">PROYECTO</th>
									<th style="text-align:left;">"""),_display_(/*315.40*/proyecto/*315.48*/.nickName.toUpperCase()),format.raw/*315.71*/("""</TH>
								</tr>
								<tr style= "background-color: #F2F5A9;">
									<th style="text-align:left;">PERIODO</th>
									<th style="text-align:left;">desde """),_display_(/*319.46*/fechas/*319.52*/.get(2)),format.raw/*319.59*/(""" """),format.raw/*319.60*/("""hasta """),_display_(/*319.67*/fechas/*319.73*/.get(3)),format.raw/*319.80*/("""</TH>
								</tr>
								<tr style= "background-color: #F2F5A9;">
									<th style="text-align:left;">COMERCIAL</th>
									<th style="text-align:left;">"""),_display_(/*323.40*/bodega/*323.46*/.comercial),format.raw/*323.56*/("""</TH>
								</tr>
							</table>
							<table class="table table-sm table-hover table-bordered table-condensed table-fluid">
								<tr>
									<th  width="55%" style= "text-align: center;">CONCEPTO</th>
									<th  width="15%" style= "text-align: center;">CFI</th>
									<th  width="15%" style= "text-align: center;">"""),_display_(/*330.57*/mapDiccionario/*330.71*/.get("ARRIENDO")),format.raw/*330.87*/("""</th>
									<th  width="15%" style= "text-align: center;">VENTA</th>
								</tr>
								
								<tr>
									<td style= "text-align: left;">Inventario Inicial:</td>
									<td id="inicialCfi" style= "text-align: right;"></td>
									<td id="inicialArriendo" style= "text-align: right;"></td>
									<td id="inicialVenta" style= "text-align: right;"></td>
								</tr>
								<tr>
									<td style= "text-align: left;">Mas Despachos:</td>
									<td id="despachoCfi" style= "text-align: right;"></td>
									<td id="despachoArriendo" style= "text-align: right;"></td>
									<td id="despachoVenta" style= "text-align: right;"></td>
								</tr>
								<tr>
									<td style= "text-align: left;">Menos Devoluciones:</td>
									<td id="devolucionCfi" style= "text-align: right;"></td>
									<td id="devolucionArriendo" style= "text-align: right;"></td>
									<td id="devolucionVenta" style= "text-align: right;"></td>
								</tr>
								<tr>
									<th style= "text-align: left;">SUBTOTAL:</th>
									<th id="totCfi" style= "text-align: right;"></th>
									<th id="totArriendo" style= "text-align: right;"></th>
									<th id="totVenta" style= "text-align: right;"></th>
								</tr>
								
								"""),_display_(/*359.10*/for(lista <- detalleAjuste) yield /*359.37*/{_display_(Seq[Any](format.raw/*359.38*/("""
									"""),format.raw/*360.10*/("""<tr>
										<td style= "text-align: left;">"""),_display_(/*361.43*/lista/*361.48*/.get(0)),format.raw/*361.55*/("""</td>
										<td style= "text-align: right;">0</td>
										<td style= "text-align: right;">"""),_display_(/*363.44*/lista/*363.49*/.get(1)),format.raw/*363.56*/("""</td>
										<td style= "text-align: right;">"""),_display_(/*364.44*/lista/*364.49*/.get(2)),format.raw/*364.56*/("""</td>
									<tr>
								""")))}),format.raw/*366.10*/("""
								
								"""),format.raw/*368.9*/("""<tr>
									<th style= "text-align: left;">TOTAL:</th>
									<th id="totCfi2" style= "text-align: right;"></th>
									<th id="totArriendo2" style= "text-align: right;"></th>
									<th id="totVenta2" style= "text-align: right;"></th>
								</tr>
							</table>
						</td>
						<td width="2%">&nbsp;</td>
						<td width="44%" colspan="7" style= "text-align: left;">
							<table class="table table-sm table-hover table-bordered table-condensed table-fluid">
								<tr>
									<th colspan="2">TOTAL NETO (CFI + """),_display_(/*380.45*/mapDiccionario/*380.59*/.get("ARRIENDO")),format.raw/*380.75*/(""" """),format.raw/*380.76*/("""+ VENTA NETO + AJUSTES)</th>
									<th style="text-align:right;" colspan="2"><div id="resumenTotal3"></div></th>
								</tr>
							</table>
							<table id="resumen" class="table table-sm table-hover table-bordered table-condensed table-fluid">
								<tr>
									<th width="55%"></th>
									<th width="15%" style="text-align:center;">CFI</th>
									<th width="15%" style="text-align:center;">"""),_display_(/*388.54*/mapDiccionario/*388.68*/.get("ARRIENDO")),format.raw/*388.84*/("""</th>
									<th width="15%" style="text-align:center;">VENTA</th>
								</tr>
								"""),_display_(/*391.10*/for(subtotal <- resumenSubtotales) yield /*391.44*/{_display_(Seq[Any](format.raw/*391.45*/("""
									"""),_display_(if(!(subtotal.get(1).equals("0")&&subtotal.get(2).equals("0")&&subtotal.get(3).equals("0")))/*392.103*/{_display_(Seq[Any](format.raw/*392.104*/("""
									"""),format.raw/*393.10*/("""<tr>
										<td style="text-align:left;">"""),_display_(/*394.41*/subtotal/*394.49*/.get(0).toUpperCase()),format.raw/*394.70*/("""</td>
										<td style="text-align:right;">"""),_display_(/*395.42*/subtotal/*395.50*/.get(3)),format.raw/*395.57*/("""</td>
										<td style="text-align:right;">"""),_display_(/*396.42*/subtotal/*396.50*/.get(1)),format.raw/*396.57*/("""</td>
										<td style="text-align:right;">"""),_display_(/*397.42*/subtotal/*397.50*/.get(2)),format.raw/*397.57*/("""</td>
									</tr>
									""")))} else {null} ),format.raw/*399.11*/("""
								""")))}),format.raw/*400.10*/("""
								"""),format.raw/*401.9*/("""<tr>
									<th style="text-align:left;">SUBTOTAL</th>
									<th style="text-align:right;"><div id="resumenTotal0"></div></th>
									<th style="text-align:right;"><div id="resumenTotal1"></div></th>
									<th style="text-align:right;"><div id="resumenTotal2"></div></th>
								</tr>
							</table>
							<table class="table table-sm table-hover table-bordered table-condensed table-fluid">
								"""),_display_(/*409.10*/for(lista <- detalleAjuste) yield /*409.37*/{_display_(Seq[Any](format.raw/*409.38*/("""
									"""),format.raw/*410.10*/("""<tr>
										<td style= "text-align: left;">"""),_display_(/*411.43*/lista/*411.48*/.get(0)),format.raw/*411.55*/("""</td>
										<td style= "text-align: right;">0</td>
										<td style= "text-align: right;">"""),_display_(/*413.44*/lista/*413.49*/.get(1)),format.raw/*413.56*/("""</td>
										<td style= "text-align: right;">"""),_display_(/*414.44*/lista/*414.49*/.get(2)),format.raw/*414.56*/("""</td>
									<tr>
								""")))}),format.raw/*416.10*/("""
								
								"""),format.raw/*418.9*/("""<tr>
									<th  width="55%" style= "text-align: left;">TOTAL:</th>
									<th  width="15%" style="text-align:right;"><div id="resumenTotal02"></div></th>
									<th  width="15%" style="text-align:right;"><div id="resumenTotal12"></div></th>
									<th  width="15%" style="text-align:right;"><div id="resumenTotal22"></div></th>
								</tr>
								<tr>
									<td colspan="4" style= "text-align: left;">"""),_display_(/*425.54*/oc),format.raw/*425.56*/("""<td>
								</tr>
							</table>
						</td>
					</TR>
			 </table>
		
			 <script>
			 	 var numero = new DecimalFormat("#,##0.00");
				 var totalInicial=0;
				 var totalDespachoCfi=0;
				 var totalDespachoArr=0;
				 var totalDespachoVta=0;
				 var totalDevolucionArr=0;
				 var totalDevolucionVta=0;
			 </script>
		
			  
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid" >
					<thead class="header">
			        	<TR> 
							<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">GRUPO</TH>
							<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">Nro.Coti</TH>
							<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">CODIGO</TH>
							<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">EQUIPO</TH>
							<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">UN</TH>
							<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">CANT</TH>
							<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">MON</TH>
							
							"""),_display_(if(mapPermiso.get("parametro.proforma_conDctosCoti").equals("1"))/*454.74*/{_display_(Seq[Any](format.raw/*454.75*/("""
								"""),format.raw/*455.9*/("""<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">P.Venta<br>sin dcto</TH>
								<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">Dcto</TH>
								<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">P.Venta<br>con Dcto</TH>
							""")))}else/*458.13*/{_display_(Seq[Any](format.raw/*458.14*/("""
								"""),format.raw/*459.9*/("""<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">Precio<br>Venta</TH>
							""")))}),format.raw/*460.9*/("""
							
							"""),_display_(if(!mapPermiso.get("parametro.proforma_ocultaTasaArriendo").equals("1"))/*462.81*/{_display_(Seq[Any](format.raw/*462.82*/("""
								"""),format.raw/*463.9*/("""<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">Tasa Mes<br>"""),_display_(/*463.97*/mapDiccionario/*463.111*/.get("Arr")),format.raw/*463.122*/("""</TH>
							""")))} else {null} ),format.raw/*464.9*/("""
							
							"""),_display_(if(mapPermiso.get("parametro.proforma_conDctosCoti").equals("1"))/*466.74*/{_display_(Seq[Any](format.raw/*466.75*/("""
								"""),format.raw/*467.9*/("""<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">P."""),_display_(/*467.87*/mapDiccionario/*467.101*/.get("ARR")),format.raw/*467.112*/(""" """),format.raw/*467.113*/("""DIA<br>sin dcto</TH>
								<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">Dcto</TH>
								<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">P."""),_display_(/*469.87*/mapDiccionario/*469.101*/.get("ARR")),format.raw/*469.112*/(""" """),format.raw/*469.113*/("""DIA<br>con Dcto</TH>
							""")))}else/*470.13*/{_display_(Seq[Any](format.raw/*470.14*/("""
								"""),format.raw/*471.9*/("""<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">P."""),_display_(/*471.87*/mapDiccionario/*471.101*/.get("ARR")),format.raw/*471.112*/("""<br>por DIA</TH>
							""")))}),format.raw/*472.9*/("""
							"""),format.raw/*473.8*/("""<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">CANT<br>DIAS</TH>
							<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">TASA<br>CAMBIO</TH>
							
							"""),_display_(if(mapDiccionario.get("nEmpresa").equals("EMIN") && tasaCambio.get(0).toString().equals("1"))/*476.102*/{_display_(Seq[Any](format.raw/*476.103*/("""
								"""),format.raw/*477.9*/("""<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">CFI<br>en UF</TH>
								<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">"""),_display_(/*478.85*/mapDiccionario/*478.99*/.get("ARRIENDO")),format.raw/*478.115*/("""<br>en UF</TH>
								<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">VENTA<br>en UF</TH>
							""")))}else/*480.13*/{_display_(Seq[Any](format.raw/*480.14*/("""
								"""),format.raw/*481.9*/("""<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">CFI<br>en """),_display_(/*481.95*/mapDiccionario/*481.109*/.get("PESOS")),format.raw/*481.122*/("""</TH>
								<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">"""),_display_(/*482.85*/mapDiccionario/*482.99*/.get("ARRIENDO")),format.raw/*482.115*/("""<br>en """),_display_(/*482.123*/mapDiccionario/*482.137*/.get("PESOS")),format.raw/*482.150*/("""</TH>
								<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">VENTA<br>en """),_display_(/*483.97*/mapDiccionario/*483.111*/.get("PESOS")),format.raw/*483.124*/("""</TH>
							""")))}),format.raw/*484.9*/("""
							
							
							"""),format.raw/*487.8*/("""<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;" title="Corresponde a la fecha del primer despacho de equipo por la cotizacion">DESDE<br>FECHA</TH>
						</TR>
					</thead>
					<tbody>
						<TR >
							<td style="background-color: #9FF781" colspan="20">INVENTARIO INICIAL</td>
						</tr>
						"""),_display_(/*494.8*/for((inicial,index) <- inicioPer.zipWithIndex) yield /*494.54*/{_display_(Seq[Any](format.raw/*494.55*/("""
							"""),format.raw/*495.8*/("""<tr>
								<td style="text-align: left;">"""),_display_(/*496.40*/inicial/*496.47*/.get(7)),format.raw/*496.54*/("""</td>
								
								"""),_display_(if(inicial.get(8).equals("0") || inicial.get(8).equals(""))/*498.69*/{_display_(Seq[Any](format.raw/*498.70*/("""
									"""),format.raw/*499.10*/("""<td style="text-align: center;">--</td>
								""")))}else/*500.14*/{_display_(Seq[Any](format.raw/*500.15*/("""
									"""),format.raw/*501.10*/("""<td style="text-align: center;"><a href="#" onclick="buscaCotizacion('"""),_display_(/*501.81*/inicial/*501.88*/.get(8)),format.raw/*501.95*/("""')">"""),_display_(/*501.100*/inicial/*501.107*/.get(8)),format.raw/*501.114*/("""</td>
								""")))}),format.raw/*502.10*/("""
								
								"""),format.raw/*504.9*/("""<td style="text-align: left;"><a href="#" onclick="buscaEquipo('"""),_display_(/*504.74*/inicial/*504.81*/.get(9)),format.raw/*504.88*/("""');">"""),_display_(/*504.94*/inicial/*504.101*/.get(9)),format.raw/*504.108*/("""</a></td>
								<td style="text-align: left;"><a href="#" onclick="buscaEquipo('"""),_display_(/*505.74*/inicial/*505.81*/.get(9)),format.raw/*505.88*/("""');">"""),_display_(/*505.94*/inicial/*505.101*/.get(10)),format.raw/*505.109*/("""</a></td>
								
								<td style="text-align: center;">"""),_display_(/*507.42*/inicial/*507.49*/.get(11)),format.raw/*507.57*/("""</td>
								<td style="text-align: right;">"""),_display_(/*508.41*/inicial/*508.48*/.get(12)),format.raw/*508.56*/("""</td>
								<td style="text-align: center;">"""),_display_(/*509.42*/inicial/*509.49*/.get(13)),format.raw/*509.57*/("""</td>
								"""),_display_(if(inicial.get(19).trim().equals("0") && mapDiccionario.get("nEmpresa").equals("SANTAFE"))/*510.100*/{_display_(Seq[Any](format.raw/*510.101*/("""
									"""),format.raw/*511.10*/("""<td style="text-align: right;"></td>
									<td style="text-align: right;"></td>
								""")))}else/*513.14*/{_display_(Seq[Any](format.raw/*513.15*/("""
									"""),_display_(if(mapPermiso.get("parametro.proforma_conDctosCoti").equals("1"))/*514.76*/{_display_(Seq[Any](format.raw/*514.77*/("""
										"""),format.raw/*515.11*/("""<td style="text-align: right;">
											<div id="dctoVenta"""),_display_(/*516.31*/index),format.raw/*516.36*/(""""></div>
											<script>
												var auxVta = numero.formatBack(""""),_display_(/*518.46*/inicial/*518.53*/.get(14)),format.raw/*518.61*/("""");
												var dcto = """"),_display_(/*519.26*/inicial/*519.33*/.get(25)),format.raw/*519.41*/("""";
												var auxDcto = numero.formatBack(dcto.replace("%","").trim());
												if(auxDcto>0)"""),format.raw/*521.26*/("""{"""),format.raw/*521.27*/("""
													"""),format.raw/*522.14*/("""var rs = formatStandar4(parseFloat(auxVta)/(1-parseFloat(auxDcto)/100));
													document.getElementById('dctoVenta"""),_display_(/*523.49*/index),format.raw/*523.54*/("""').innerHTML=rs;
												"""),format.raw/*524.13*/("""}"""),format.raw/*524.14*/("""else"""),format.raw/*524.18*/("""{"""),format.raw/*524.19*/("""
													"""),format.raw/*525.14*/("""var rs = formatStandar4(parseFloat(auxVta));
													document.getElementById('dctoVenta"""),_display_(/*526.49*/index),format.raw/*526.54*/("""').innerHTML=rs;
												"""),format.raw/*527.13*/("""}"""),format.raw/*527.14*/("""
											"""),format.raw/*528.12*/("""</script>
										</td>
										<td style="text-align: right;">"""),_display_(/*530.43*/inicial/*530.50*/.get(25)),format.raw/*530.58*/("""</td>
									""")))} else {null} ),format.raw/*531.11*/("""
									"""),format.raw/*532.10*/("""<td style="text-align: right;">"""),_display_(/*532.42*/inicial/*532.49*/.get(14)),format.raw/*532.57*/("""</td>
									"""),_display_(if(!mapPermiso.get("parametro.proforma_ocultaTasaArriendo").equals("1"))/*533.83*/{_display_(Seq[Any](format.raw/*533.84*/("""
										"""),format.raw/*534.11*/("""<td style="text-align: right;">
											<div id="inicio"""),_display_(/*535.28*/index),format.raw/*535.33*/(""""></div>
											<script>
												var auxVta = numero.formatBack(""""),_display_(/*537.46*/inicial/*537.53*/.get(14)),format.raw/*537.61*/("""");
												var auxArr = numero.formatBack(""""),_display_(/*538.46*/inicial/*538.53*/.get(15)),format.raw/*538.61*/("""");
												if(auxVta==0)"""),format.raw/*539.26*/("""{"""),format.raw/*539.27*/("""
													"""),format.raw/*540.14*/("""document.getElementById('inicio"""),_display_(/*540.46*/index),format.raw/*540.51*/("""').innerHTML="";
												"""),format.raw/*541.13*/("""}"""),format.raw/*541.14*/("""else"""),format.raw/*541.18*/("""{"""),format.raw/*541.19*/("""
													"""),format.raw/*542.14*/("""var rs = formatStandar2(parseFloat(auxArr)*30*100/parseFloat(auxVta))+"%";
													document.getElementById('inicio"""),_display_(/*543.46*/index),format.raw/*543.51*/("""').innerHTML=rs;
												"""),format.raw/*544.13*/("""}"""),format.raw/*544.14*/("""
												
											"""),format.raw/*546.12*/("""</script>
										</td>
									""")))} else {null} ),format.raw/*548.11*/("""
								""")))}),format.raw/*549.10*/("""
								"""),_display_(if(mapPermiso.get("parametro.proforma_conDctosCoti").equals("1"))/*550.75*/{_display_(Seq[Any](format.raw/*550.76*/("""
									"""),format.raw/*551.10*/("""<td style="text-align: right;">
										<div id="dctoArr"""),_display_(/*552.28*/index),format.raw/*552.33*/(""""></div>
										<script>
											var auxArr = numero.formatBack(""""),_display_(/*554.45*/inicial/*554.52*/.get(15)),format.raw/*554.60*/("""");
											var dcto = """"),_display_(/*555.25*/inicial/*555.32*/.get(24)),format.raw/*555.40*/("""";
											var auxDcto = numero.formatBack(dcto.replace("%","").trim());
											if(auxDcto>0)"""),format.raw/*557.25*/("""{"""),format.raw/*557.26*/("""
												"""),format.raw/*558.13*/("""var rs = formatStandar4(parseFloat(auxArr)/(1-parseFloat(auxDcto)/100));
												document.getElementById('dctoArr"""),_display_(/*559.46*/index),format.raw/*559.51*/("""').innerHTML=rs;
											"""),format.raw/*560.12*/("""}"""),format.raw/*560.13*/("""else"""),format.raw/*560.17*/("""{"""),format.raw/*560.18*/("""
												"""),format.raw/*561.13*/("""var rs = formatStandar4(parseFloat(auxArr));
												document.getElementById('dctoArr"""),_display_(/*562.46*/index),format.raw/*562.51*/("""').innerHTML=rs;
											"""),format.raw/*563.12*/("""}"""),format.raw/*563.13*/("""
										"""),format.raw/*564.11*/("""</script>
									</td>
									<td style="text-align: right;">"""),_display_(/*566.42*/inicial/*566.49*/.get(24)),format.raw/*566.57*/("""</td>
								""")))} else {null} ),format.raw/*567.10*/("""
								"""),format.raw/*568.9*/("""<td style="text-align: right;">"""),_display_(/*568.41*/inicial/*568.48*/.get(15)),format.raw/*568.56*/("""</td>
								<td style="text-align: center;">"""),_display_(/*569.42*/inicial/*569.49*/.get(16)),format.raw/*569.57*/("""</td>
								<td style="text-align: right;">"""),_display_(/*570.41*/inicial/*570.48*/.get(17)),format.raw/*570.56*/("""</td>
								<td style="text-align: right;">"""),_display_(/*571.41*/inicial/*571.48*/.get(23)),format.raw/*571.56*/("""</td>
								<td style="text-align: right;">
									"""),_display_(/*573.11*/inicial/*573.18*/.get(18)),format.raw/*573.26*/("""
									"""),format.raw/*574.10*/("""<script>
										totalInicial = totalInicial + parseFloat(numero.formatBack(""""),_display_(/*575.72*/inicial/*575.79*/.get(18)),format.raw/*575.87*/(""""));
									</script>
								</td>
								<td style="text-align: right;">"""),_display_(/*578.41*/inicial/*578.48*/.get(19)),format.raw/*578.56*/("""</td>
								<td style="text-align: center;">"""),_display_(/*579.42*/inicial/*579.49*/.get(21).replaceAll("-", "/")),format.raw/*579.78*/("""</td>
							</TR>
			 			""")))}),format.raw/*581.9*/("""
			 			"""),format.raw/*582.8*/("""<TR >
							<td style="background-color: #9FF781" colspan="20">DETALLE MOVIMIENTOS DEL MES</td>
						</tr>
			 			"""),_display_(/*585.9*/for((detalle1,index2) <- guiasPer.zipWithIndex) yield /*585.56*/{_display_(Seq[Any](format.raw/*585.57*/("""
								"""),format.raw/*586.9*/("""<TR>
								<td colspan="3" style="background-color: #eeeeee">"""),_display_(/*587.60*/detalle1/*587.68*/.get(7)),format.raw/*587.75*/(""": """),_display_(/*587.78*/detalle1/*587.86*/.get(2)),format.raw/*587.93*/("""</td>
								<td colspan="1" style="background-color: #eeeeee">FECHA: """),_display_(/*588.67*/mapDiccionario/*588.81*/.get("GUIA")),format.raw/*588.93*/(""": """),_display_(/*588.96*/detalle1/*588.104*/.get(3)),format.raw/*588.111*/(""" """),format.raw/*588.112*/("""--  INI/TER: """),_display_(/*588.126*/detalle1/*588.134*/.get(10)),format.raw/*588.142*/("""</td>
								<td colspan="4" style="background-color: #eeeeee">Nro de Ref: """),_display_(/*589.72*/detalle1/*589.80*/.get(5)),format.raw/*589.87*/("""</td>
								<td colspan="16" style="background-color: #eeeeee">Observaciones: """),_display_(/*590.76*/detalle1/*590.84*/.get(9)),format.raw/*590.91*/("""</td>
								</tr>
								"""),_display_(if(mapReportPorGuia10.get(detalle1.get(8))!=null)/*592.59*/{_display_(Seq[Any](format.raw/*592.60*/("""
									"""),_display_(/*593.11*/for((detalle3,index) <- mapReportPorGuia10.get(detalle1.get(8)).zipWithIndex) yield /*593.88*/{_display_(Seq[Any](format.raw/*593.89*/("""
										"""),_display_(if(!(detalle3.get(13).equals("0") || detalle3.get(13).equals("-0")))/*594.80*/{_display_(Seq[Any](format.raw/*594.81*/("""
											"""),format.raw/*595.12*/("""<tr>
												<td style="text-align: left;">"""),_display_(/*596.44*/detalle3/*596.52*/.get(8)),format.raw/*596.59*/("""</td>
												
												
												"""),_display_(if(detalle3.get(9).equals("0") || detalle3.get(9).equals(""))/*599.75*/{_display_(Seq[Any](format.raw/*599.76*/("""
													"""),format.raw/*600.14*/("""<td style="text-align: center;">--</td>
												""")))}else/*601.18*/{_display_(Seq[Any](format.raw/*601.19*/("""
													"""),format.raw/*602.14*/("""<td style="text-align: center;"><a href="#" onclick="buscaCotizacion('"""),_display_(/*602.85*/detalle3/*602.93*/.get(9)),format.raw/*602.100*/("""')">"""),_display_(/*602.105*/detalle3/*602.113*/.get(9)),format.raw/*602.120*/("""</td>
												""")))}),format.raw/*603.14*/("""
												
												"""),format.raw/*605.13*/("""<td style="text-align: left;"><a href="#" onclick="buscaEquipo('"""),_display_(/*605.78*/detalle3/*605.86*/.get(10)),format.raw/*605.94*/("""');">"""),_display_(/*605.100*/detalle3/*605.108*/.get(10)),format.raw/*605.116*/("""</a></td>
												<td style="text-align: left;"><a href="#" onclick="buscaEquipo('"""),_display_(/*606.78*/detalle3/*606.86*/.get(10)),format.raw/*606.94*/("""');">"""),_display_(/*606.100*/detalle3/*606.108*/.get(11)),format.raw/*606.116*/("""</a></td>
								
												<td style="text-align: center;">"""),_display_(/*608.46*/detalle3/*608.54*/.get(12)),format.raw/*608.62*/("""</td>
												<td style="text-align: right;">"""),_display_(/*609.45*/detalle3/*609.53*/.get(13)),format.raw/*609.61*/("""</td>
												<td style="text-align: center;">"""),_display_(/*610.46*/detalle3/*610.54*/.get(14)),format.raw/*610.62*/("""</td>
												"""),_display_(if(detalle3.get(20).trim().equals("0") && mapDiccionario.get("nEmpresa").equals("SANTAFE"))/*611.105*/{_display_(Seq[Any](format.raw/*611.106*/("""
													"""),format.raw/*612.14*/("""<td style="text-align: right;"></td>
													<td style="text-align: right;"></td>
												""")))}else/*614.18*/{_display_(Seq[Any](format.raw/*614.19*/("""
													"""),_display_(if(mapPermiso.get("parametro.proforma_conDctosCoti").equals("1"))/*615.80*/{_display_(Seq[Any](format.raw/*615.81*/("""
														"""),format.raw/*616.15*/("""<td style="text-align: right;">
															<div id="dctoVenta"""),_display_(/*617.35*/index),format.raw/*617.40*/(""" """),_display_(/*617.42*/index2),format.raw/*617.48*/(""""></div>
															<script>
																var auxVta = numero.formatBack(""""),_display_(/*619.50*/detalle3/*619.58*/.get(15)),format.raw/*619.66*/("""");
																var dcto = """"),_display_(/*620.30*/detalle3/*620.38*/.get(25)),format.raw/*620.46*/("""";
																var auxDcto = numero.formatBack(dcto.replace("%","").trim());
																if(auxDcto>0)"""),format.raw/*622.30*/("""{"""),format.raw/*622.31*/("""
																	"""),format.raw/*623.18*/("""var rs = formatStandar4(parseFloat(auxVta)/(1-parseFloat(auxDcto)/100));
																	document.getElementById('dctoVenta"""),_display_(/*624.53*/index),format.raw/*624.58*/(""" """),_display_(/*624.60*/index2),format.raw/*624.66*/("""').innerHTML=rs;
																"""),format.raw/*625.17*/("""}"""),format.raw/*625.18*/("""else"""),format.raw/*625.22*/("""{"""),format.raw/*625.23*/("""
																	"""),format.raw/*626.18*/("""var rs = formatStandar4(parseFloat(auxVta));
																	document.getElementById('dctoVenta"""),_display_(/*627.53*/index),format.raw/*627.58*/(""" """),_display_(/*627.60*/index2),format.raw/*627.66*/("""').innerHTML=rs;
																"""),format.raw/*628.17*/("""}"""),format.raw/*628.18*/("""
															"""),format.raw/*629.16*/("""</script>
														</td>
														<td style="text-align: right;">"""),_display_(/*631.47*/detalle3/*631.55*/.get(25)),format.raw/*631.63*/("""</td>
													""")))} else {null} ),format.raw/*632.15*/("""
													"""),format.raw/*633.14*/("""<td style="text-align: right;">"""),_display_(/*633.46*/detalle3/*633.54*/.get(15)),format.raw/*633.62*/("""</td>
													"""),_display_(if(!mapPermiso.get("parametro.proforma_ocultaTasaArriendo").equals("1"))/*634.87*/{_display_(Seq[Any](format.raw/*634.88*/("""
														"""),format.raw/*635.15*/("""<td style="text-align: right;">
															<div id="guiasPer"""),_display_(/*636.34*/index),format.raw/*636.39*/(""" """),_display_(/*636.41*/index2),format.raw/*636.47*/(""""></div>
															<script>
																var auxVta = numero.formatBack(""""),_display_(/*638.50*/detalle3/*638.58*/.get(15)),format.raw/*638.66*/("""");
																var auxArr = numero.formatBack(""""),_display_(/*639.50*/detalle3/*639.58*/.get(16)),format.raw/*639.66*/("""");
																if(auxVta==0)"""),format.raw/*640.30*/("""{"""),format.raw/*640.31*/("""
																	"""),format.raw/*641.18*/("""document.getElementById('guiasPer"""),_display_(/*641.52*/index),format.raw/*641.57*/(""" """),_display_(/*641.59*/index2),format.raw/*641.65*/("""').innerHTML="";
																"""),format.raw/*642.17*/("""}"""),format.raw/*642.18*/("""else"""),format.raw/*642.22*/("""{"""),format.raw/*642.23*/("""
																	"""),format.raw/*643.18*/("""var rs = formatStandar2(parseFloat(auxArr)*30*100/parseFloat(auxVta))+"%";
																	document.getElementById('guiasPer"""),_display_(/*644.52*/index),format.raw/*644.57*/(""" """),_display_(/*644.59*/index2),format.raw/*644.65*/("""').innerHTML=rs;
																"""),format.raw/*645.17*/("""}"""),format.raw/*645.18*/("""
																
															"""),format.raw/*647.16*/("""</script>
														</td>
													""")))} else {null} ),format.raw/*649.15*/("""
												""")))}),format.raw/*650.14*/("""
												"""),_display_(if(mapPermiso.get("parametro.proforma_conDctosCoti").equals("1"))/*651.79*/{_display_(Seq[Any](format.raw/*651.80*/("""
													"""),format.raw/*652.14*/("""<td style="text-align: right;">
														<div id="dctoArr"""),_display_(/*653.32*/index),format.raw/*653.37*/(""" """),_display_(/*653.39*/index2),format.raw/*653.45*/(""""></div>
														<script>
															var auxArr = numero.formatBack(""""),_display_(/*655.49*/detalle3/*655.57*/.get(16)),format.raw/*655.65*/("""");
															var dcto = """"),_display_(/*656.29*/detalle3/*656.37*/.get(24)),format.raw/*656.45*/("""";
															var auxDcto = numero.formatBack(dcto.replace("%","").trim());
															if(auxDcto>0)"""),format.raw/*658.29*/("""{"""),format.raw/*658.30*/("""
																"""),format.raw/*659.17*/("""var rs = formatStandar4(parseFloat(auxArr)/(1-parseFloat(auxDcto)/100));
																document.getElementById('dctoArr"""),_display_(/*660.50*/index),format.raw/*660.55*/(""" """),_display_(/*660.57*/index2),format.raw/*660.63*/("""').innerHTML=rs;
															"""),format.raw/*661.16*/("""}"""),format.raw/*661.17*/("""else"""),format.raw/*661.21*/("""{"""),format.raw/*661.22*/("""
																"""),format.raw/*662.17*/("""var rs = formatStandar4(parseFloat(auxArr));
																document.getElementById('dctoArr"""),_display_(/*663.50*/index),format.raw/*663.55*/(""" """),_display_(/*663.57*/index2),format.raw/*663.63*/("""').innerHTML=rs;
															"""),format.raw/*664.16*/("""}"""),format.raw/*664.17*/("""
														"""),format.raw/*665.15*/("""</script>
													</td>
													<td style="text-align: right;">"""),_display_(/*667.46*/detalle3/*667.54*/.get(24)),format.raw/*667.62*/("""</td>
												""")))} else {null} ),format.raw/*668.14*/("""
												"""),format.raw/*669.13*/("""<td style="text-align: right;">"""),_display_(/*669.45*/detalle3/*669.53*/.get(16)),format.raw/*669.61*/("""</td>
												<td style="text-align: right;">"""),_display_(/*670.45*/detalle3/*670.53*/.get(17)),format.raw/*670.61*/("""</td>
												<td style="text-align: right;">"""),_display_(/*671.45*/detalle3/*671.53*/.get(18)),format.raw/*671.61*/("""</td>
												<td style="text-align: right;">
													"""),_display_(/*673.15*/detalle3/*673.23*/.get(23)),format.raw/*673.31*/("""
													"""),format.raw/*674.14*/("""<script>
														if(parseFloat(numero.formatBack(""""),_display_(/*675.49*/detalle3/*675.57*/.get(23)),format.raw/*675.65*/(""""))>0)"""),format.raw/*675.71*/("""{"""),format.raw/*675.72*/("""
																"""),format.raw/*676.17*/("""totalDespachoCfi = totalDespachoCfi + parseFloat(numero.formatBack(""""),_display_(/*676.86*/detalle3/*676.94*/.get(23)),format.raw/*676.102*/(""""));
														"""),format.raw/*677.15*/("""}"""),format.raw/*677.16*/("""
													"""),format.raw/*678.14*/("""</script>
												</td>
												<td style="text-align: right;">
													"""),_display_(/*681.15*/detalle3/*681.23*/.get(19)),format.raw/*681.31*/("""
													"""),format.raw/*682.14*/("""<script>
														if(parseFloat(numero.formatBack(""""),_display_(/*683.49*/detalle3/*683.57*/.get(19)),format.raw/*683.65*/(""""))>0)"""),format.raw/*683.71*/("""{"""),format.raw/*683.72*/("""
															"""),format.raw/*684.16*/("""totalDespachoArr = totalDespachoArr + parseFloat(numero.formatBack(""""),_display_(/*684.85*/detalle3/*684.93*/.get(19)),format.raw/*684.101*/(""""));
														"""),format.raw/*685.15*/("""}"""),format.raw/*685.16*/("""else"""),format.raw/*685.20*/("""{"""),format.raw/*685.21*/("""
															"""),format.raw/*686.16*/("""totalDevolucionArr = totalDevolucionArr + parseFloat(numero.formatBack(""""),_display_(/*686.89*/detalle3/*686.97*/.get(19)),format.raw/*686.105*/(""""));
														"""),format.raw/*687.15*/("""}"""),format.raw/*687.16*/("""
													"""),format.raw/*688.14*/("""</script>
												</td>
												<td style="text-align: right;">
													"""),_display_(/*691.15*/detalle3/*691.23*/.get(20)),format.raw/*691.31*/("""
													"""),format.raw/*692.14*/("""<script>
														if(parseFloat(numero.formatBack(""""),_display_(/*693.49*/detalle3/*693.57*/.get(20)),format.raw/*693.65*/(""""))>0)"""),format.raw/*693.71*/("""{"""),format.raw/*693.72*/("""
															"""),format.raw/*694.16*/("""totalDespachoVta = totalDespachoVta + parseFloat(numero.formatBack(""""),_display_(/*694.85*/detalle3/*694.93*/.get(20)),format.raw/*694.101*/(""""));
														"""),format.raw/*695.15*/("""}"""),format.raw/*695.16*/("""else"""),format.raw/*695.20*/("""{"""),format.raw/*695.21*/("""
															"""),format.raw/*696.16*/("""totalDevolucionVta = totalDevolucionVta + parseFloat(numero.formatBack(""""),_display_(/*696.89*/detalle3/*696.97*/.get(20)),format.raw/*696.105*/(""""));
														"""),format.raw/*697.15*/("""}"""),format.raw/*697.16*/("""
													"""),format.raw/*698.14*/("""</script>
												</td>
												<td style="text-align: center;"></td>
											</TR>
											
										""")))} else {null} ),format.raw/*703.12*/("""
									""")))}),format.raw/*704.11*/("""
								""")))} else {null} ),format.raw/*705.10*/("""
			 			""")))}),format.raw/*706.9*/("""
			 			"""),format.raw/*707.8*/("""<TR style="background-color: #FFFF00">
							<td style="background-color: #FFFF00" colspan="12">TOTALES NETO</td>
							"""),_display_(if(mapPermiso.get("parametro.proforma_conDctosCoti").equals("1"))/*709.74*/{_display_(Seq[Any](format.raw/*709.75*/("""
								"""),format.raw/*710.9*/("""<td style="background-color: #FFFF00"></td>
								<td style="background-color: #FFFF00"></td>
								<td style="background-color: #FFFF00"></td>
							""")))} else {null} ),format.raw/*713.9*/("""
							"""),format.raw/*714.8*/("""<td style="background-color: #FFFF00"><div align="right" id="total0"></div></td>
							<td style="background-color: #FFFF00"><div align="right" id="total1"></div></td>
							<td style="background-color: #FFFF00"><div align="right" id="total2"></div></td>
							<td style="background-color: #FFFF00"></td>
						</tr>
						<TR style="background-color: #FFFF00">
							<td style="text-align:right;background-color: #FFFF00" colspan="14">TOTAL """),_display_(/*720.83*/mapDiccionario/*720.97*/.get("ARRIENDO")),format.raw/*720.113*/(""" """),format.raw/*720.114*/("""+ VENTA NETO:</td>
							"""),_display_(if(mapPermiso.get("parametro.proforma_conDctosCoti").equals("1"))/*721.74*/{_display_(Seq[Any](format.raw/*721.75*/("""
								"""),format.raw/*722.9*/("""<td style="background-color: #FFFF00"></td>
								<td style="background-color: #FFFF00"></td>
								<td style="background-color: #FFFF00"></td>
							""")))} else {null} ),format.raw/*725.9*/("""
							"""),format.raw/*726.8*/("""<td style="background-color: #FFFF00"><div align="right" id="total3"></div></td>
							<td style="background-color: #FFFF00"></td>
						</tr>
						"""),_display_(if(mapDiccionario.get("nEmpresa").equals("GYL"))/*729.56*/{_display_(Seq[Any](format.raw/*729.57*/("""
							"""),format.raw/*730.8*/("""<TR style="background-color: #FFFF00">
								<td style="text-align:center" colspan="3"><br><br>V°B° MAQUINARIAS</td>
								<td style="text-align:center" colspan="25"><br><br>V°B° GERENCIA</td>
							</tr>
						""")))} else {null} ),format.raw/*734.8*/("""
					"""),format.raw/*735.6*/("""</tbody>
				</table>
				
				
				
				<BR>
				<h3><B>INVENTARIO FINAL DEL PROYECTO</B></h3>
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead class="header">
			        	<TR> 
							<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">GRUPO</TH>
							<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">Nro.Coti</TH>
							<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">CODIGO</TH>
							<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">EQUIPO</TH>
							<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">UN</TH>
							<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">CANT</TH>
							<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">MON</TH>
							"""),_display_(if(mapPermiso.get("parametro.proforma_conDctosCoti").equals("1"))/*752.74*/{_display_(Seq[Any](format.raw/*752.75*/("""
								"""),format.raw/*753.9*/("""<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">P.Venta<br>sin dcto</TH>
								<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">Dcto</TH>
								<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">P.Venta<br>con Dcto</TH>
							""")))}else/*756.13*/{_display_(Seq[Any](format.raw/*756.14*/("""
								"""),format.raw/*757.9*/("""<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">Precio<br>Venta</TH>
							""")))}),format.raw/*758.9*/("""
							"""),_display_(if(!mapPermiso.get("parametro.proforma_ocultaTasaArriendo").equals("1"))/*759.81*/{_display_(Seq[Any](format.raw/*759.82*/("""
								"""),format.raw/*760.9*/("""<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">Tasa Mes<br>Arr</TH>
							""")))} else {null} ),format.raw/*761.9*/("""
							"""),_display_(if(mapPermiso.get("parametro.proforma_conDctosCoti").equals("1"))/*762.74*/{_display_(Seq[Any](format.raw/*762.75*/("""
								"""),format.raw/*763.9*/("""<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">P.ARR DIA<br>sin dcto</TH>
								<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">Dcto</TH>
								<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">P.ARR DIA<br>con Dcto</TH>
							""")))}else/*766.13*/{_display_(Seq[Any](format.raw/*766.14*/("""
								"""),format.raw/*767.9*/("""<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">ARR<br>por DIA</TH>
							""")))}),format.raw/*768.9*/("""
							"""),format.raw/*769.8*/("""<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">CANT<br>DIAS</TH>
							<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">TASA<br>CAMBIO</TH>
							"""),_display_(if(mapDiccionario.get("nEmpresa").equals("EMIN") && tasaCambio.get(0).equals("1"))/*771.91*/{_display_(Seq[Any](format.raw/*771.92*/("""
								"""),format.raw/*772.9*/("""<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">"""),_display_(/*772.85*/mapDiccionario/*772.99*/.get("ARRIENDO")),format.raw/*772.115*/("""<br>en UF</TH>
								<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">VENTA<br>en UF</TH>
								<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">DESDE<br>FECHA</TH>
							""")))}else/*775.13*/{_display_(Seq[Any](format.raw/*775.14*/("""
								"""),format.raw/*776.9*/("""<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">"""),_display_(/*776.85*/mapDiccionario/*776.99*/.get("ARRIENDO")),format.raw/*776.115*/("""<br>en """),_display_(/*776.123*/mapDiccionario/*776.137*/.get("PESOS")),format.raw/*776.150*/("""</TH>
								<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">VENTA<br>en """),_display_(/*777.97*/mapDiccionario/*777.111*/.get("PESOS")),format.raw/*777.124*/("""</TH>
								<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;" title="Corresponde a la fecha del primer despacho de equipo por la cotizacion">DESDE<br>FECHA</TH>
							""")))}),format.raw/*779.9*/("""
							
						"""),format.raw/*781.7*/("""</TR>
					</thead>
					<tbody>
						<TR >
							<td style="background-color: #9FF781" colspan="20">INVENTARIO FINAL</td>
						</tr>
						"""),_display_(/*787.8*/for((inicial,index) <- finalPer.zipWithIndex) yield /*787.53*/{_display_(Seq[Any](format.raw/*787.54*/("""
							"""),format.raw/*788.8*/("""<tr>
								<td style="text-align: left;">"""),_display_(/*789.40*/inicial/*789.47*/.get(7)),format.raw/*789.54*/("""</td>
								
								"""),_display_(if(inicial.get(8).equals("0") || inicial.get(8).equals(""))/*791.69*/{_display_(Seq[Any](format.raw/*791.70*/("""
									"""),format.raw/*792.10*/("""<td style="text-align: center;">--</td>
								""")))}else/*793.14*/{_display_(Seq[Any](format.raw/*793.15*/("""
									"""),format.raw/*794.10*/("""<td style="text-align: center;"><a href="#" onclick="buscaCotizacion('"""),_display_(/*794.81*/inicial/*794.88*/.get(8)),format.raw/*794.95*/("""')">"""),_display_(/*794.100*/inicial/*794.107*/.get(8)),format.raw/*794.114*/("""</td>
								""")))}),format.raw/*795.10*/("""
								
								"""),format.raw/*797.9*/("""<td style="text-align: left;"><a href="#" onclick="buscaEquipo('"""),_display_(/*797.74*/inicial/*797.81*/.get(9)),format.raw/*797.88*/("""');">"""),_display_(/*797.94*/inicial/*797.101*/.get(9)),format.raw/*797.108*/("""</a></td>
								<td style="text-align: left;"><a href="#" onclick="buscaEquipo('"""),_display_(/*798.74*/inicial/*798.81*/.get(9)),format.raw/*798.88*/("""');">"""),_display_(/*798.94*/inicial/*798.101*/.get(10)),format.raw/*798.109*/("""</a></td>
								
								<td style="text-align: center;">"""),_display_(/*800.42*/inicial/*800.49*/.get(11)),format.raw/*800.57*/("""</td>
								<td class="cantInvFin" style="text-align: right;">"""),_display_(/*801.60*/inicial/*801.67*/.get(12)),format.raw/*801.75*/("""</td>
								<td style="text-align: center;">"""),_display_(/*802.42*/inicial/*802.49*/.get(13)),format.raw/*802.57*/("""</td>
								"""),_display_(if(inicial.get(19).trim().equals("0") && mapDiccionario.get("nEmpresa").equals("SANTAFE"))/*803.100*/{_display_(Seq[Any](format.raw/*803.101*/("""
									"""),format.raw/*804.10*/("""<td style="text-align: right;"></td>
									<td style="text-align: right;"></td>
								""")))}else/*806.14*/{_display_(Seq[Any](format.raw/*806.15*/("""
									"""),_display_(if(mapPermiso.get("parametro.proforma_conDctosCoti").equals("1"))/*807.76*/{_display_(Seq[Any](format.raw/*807.77*/("""
										"""),format.raw/*808.11*/("""<td style="text-align: right;">
											<div id="finaldctoVenta"""),_display_(/*809.36*/index),format.raw/*809.41*/(""""></div>
											<script>
												var auxVta = numero.formatBack(""""),_display_(/*811.46*/inicial/*811.53*/.get(14)),format.raw/*811.61*/("""");
												var dcto = """"),_display_(/*812.26*/inicial/*812.33*/.get(25)),format.raw/*812.41*/("""";
												var auxDcto = numero.formatBack(dcto.replace("%","").trim());
												if(auxDcto>0)"""),format.raw/*814.26*/("""{"""),format.raw/*814.27*/("""
													"""),format.raw/*815.14*/("""var rs = formatStandar4(parseFloat(auxVta)/(1-parseFloat(auxDcto)/100));
													document.getElementById('finaldctoVenta"""),_display_(/*816.54*/index),format.raw/*816.59*/("""').innerHTML=rs;
												"""),format.raw/*817.13*/("""}"""),format.raw/*817.14*/("""else"""),format.raw/*817.18*/("""{"""),format.raw/*817.19*/("""
													"""),format.raw/*818.14*/("""var rs = formatStandar4(parseFloat(auxVta));
													document.getElementById('finaldctoVenta"""),_display_(/*819.54*/index),format.raw/*819.59*/("""').innerHTML=rs;
												"""),format.raw/*820.13*/("""}"""),format.raw/*820.14*/("""
											"""),format.raw/*821.12*/("""</script>
										</td>
										<td style="text-align: right;">"""),_display_(/*823.43*/inicial/*823.50*/.get(25)),format.raw/*823.58*/("""</td>
									""")))} else {null} ),format.raw/*824.11*/("""
									"""),format.raw/*825.10*/("""<td class="pVentaInvFin" style="text-align: right;">"""),_display_(/*825.63*/inicial/*825.70*/.get(14)),format.raw/*825.78*/("""</td>
									"""),_display_(if(!mapPermiso.get("parametro.proforma_ocultaTasaArriendo").equals("1"))/*826.83*/{_display_(Seq[Any](format.raw/*826.84*/("""
										"""),format.raw/*827.11*/("""<td style="text-align: right;">
											<div id="finalPer"""),_display_(/*828.30*/index),format.raw/*828.35*/(""""></div>
											<script>
												var auxVta = numero.formatBack(""""),_display_(/*830.46*/inicial/*830.53*/.get(14)),format.raw/*830.61*/("""");
												var auxArr = numero.formatBack(""""),_display_(/*831.46*/inicial/*831.53*/.get(15)),format.raw/*831.61*/("""");
												if(auxVta==0)"""),format.raw/*832.26*/("""{"""),format.raw/*832.27*/("""
													"""),format.raw/*833.14*/("""document.getElementById('finalPer"""),_display_(/*833.48*/index),format.raw/*833.53*/("""').innerHTML="";
												"""),format.raw/*834.13*/("""}"""),format.raw/*834.14*/("""else"""),format.raw/*834.18*/("""{"""),format.raw/*834.19*/("""
													"""),format.raw/*835.14*/("""var rs = formatStandar2(parseFloat(auxArr)*30*100/parseFloat(auxVta))+"%";
													document.getElementById('finalPer"""),_display_(/*836.48*/index),format.raw/*836.53*/("""').innerHTML=rs;
												"""),format.raw/*837.13*/("""}"""),format.raw/*837.14*/("""
												
											"""),format.raw/*839.12*/("""</script>
										</td>
									""")))} else {null} ),format.raw/*841.11*/("""
								""")))}),format.raw/*842.10*/("""
								"""),_display_(if(mapPermiso.get("parametro.proforma_conDctosCoti").equals("1"))/*843.75*/{_display_(Seq[Any](format.raw/*843.76*/("""
									"""),format.raw/*844.10*/("""<td style="text-align: right;">
										<div id="finaldctoArr"""),_display_(/*845.33*/index),format.raw/*845.38*/(""""></div>
										<script>
											var auxArr = numero.formatBack(""""),_display_(/*847.45*/inicial/*847.52*/.get(15)),format.raw/*847.60*/("""");
											var dcto = """"),_display_(/*848.25*/inicial/*848.32*/.get(24)),format.raw/*848.40*/("""";
											var auxDcto = numero.formatBack(dcto.replace("%","").trim());
											if(auxDcto>0)"""),format.raw/*850.25*/("""{"""),format.raw/*850.26*/("""
												"""),format.raw/*851.13*/("""var rs = formatStandar4(parseFloat(auxArr)/(1-parseFloat(auxDcto)/100));
												document.getElementById('finaldctoArr"""),_display_(/*852.51*/index),format.raw/*852.56*/("""').innerHTML=rs;
											"""),format.raw/*853.12*/("""}"""),format.raw/*853.13*/("""else"""),format.raw/*853.17*/("""{"""),format.raw/*853.18*/("""
												"""),format.raw/*854.13*/("""var rs = formatStandar4(parseFloat(auxArr));
												document.getElementById('finaldctoArr"""),_display_(/*855.51*/index),format.raw/*855.56*/("""').innerHTML=rs;
											"""),format.raw/*856.12*/("""}"""),format.raw/*856.13*/("""
										"""),format.raw/*857.11*/("""</script>
									</td>
									<td style="text-align: right;">"""),_display_(/*859.42*/inicial/*859.49*/.get(24)),format.raw/*859.57*/("""</td>
								""")))} else {null} ),format.raw/*860.10*/("""
								"""),format.raw/*861.9*/("""<td class="pArrDiaInvFin" style="text-align: right;">"""),_display_(/*861.63*/inicial/*861.70*/.get(15)),format.raw/*861.78*/("""</td>
								<td style="text-align: right;">"""),_display_(/*862.41*/inicial/*862.48*/.get(16)),format.raw/*862.56*/("""</td>
								<td style="text-align: right;">"""),_display_(/*863.41*/inicial/*863.48*/.get(17)),format.raw/*863.56*/("""</td>
								<td style="text-align: right;">"""),_display_(/*864.41*/inicial/*864.48*/.get(18)),format.raw/*864.56*/("""</td>
								<td style="text-align: right;">0</td>
								<td style="text-align: center;">"""),_display_(/*866.42*/inicial/*866.49*/.get(21).replaceAll("-", "/")),format.raw/*866.78*/("""</td>
							</TR>
			 			""")))}),format.raw/*868.9*/("""
			 		"""),format.raw/*869.7*/("""</tbody>
					<tfoot>
							<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;"></TH>
							<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;"></TH>
							<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;"></TH>
							<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;"></TH>
							<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">TOTAL CANT x PU</TH>
							<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;"></TH>
							<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;"></TH>
							"""),_display_(if(mapPermiso.get("parametro.proforma_conDctosCoti").equals("1"))/*878.74*/{_display_(Seq[Any](format.raw/*878.75*/("""
								"""),format.raw/*879.9*/("""<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;"></TH>
								<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;"></TH>
								<TH id="totPvtaInvFin" style="text-align: right;vertical-align:top;background-color:#eeeeee;"></TH>
							""")))}else/*882.13*/{_display_(Seq[Any](format.raw/*882.14*/("""
								"""),format.raw/*883.9*/("""<TH id="totPvtaInvFin"  style="text-align: right;vertical-align:top;background-color:#eeeeee;"></TH>
							""")))}),format.raw/*884.9*/("""
							"""),_display_(if(!mapPermiso.get("parametro.proforma_ocultaTasaArriendo").equals("1"))/*885.81*/{_display_(Seq[Any](format.raw/*885.82*/("""
								"""),format.raw/*886.9*/("""<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;"></TH>
							""")))} else {null} ),format.raw/*887.9*/("""
							"""),_display_(if(mapPermiso.get("parametro.proforma_conDctosCoti").equals("1"))/*888.74*/{_display_(Seq[Any](format.raw/*888.75*/("""
								"""),format.raw/*889.9*/("""<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;"></TH>
								<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;"></TH>
								<TH id="totParrDiaInvFin" style="text-align: right;vertical-align:top;background-color:#eeeeee;"></TH>
							""")))}else/*892.13*/{_display_(Seq[Any](format.raw/*892.14*/("""
								"""),format.raw/*893.9*/("""<TH id="totParrDiaInvFin" style="text-align: right;vertical-align:top;background-color:#eeeeee;"></TH>
							""")))}),format.raw/*894.9*/("""
							"""),format.raw/*895.8*/("""<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;"></TH>
							<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;"></TH>
							"""),_display_(if(mapDiccionario.get("nEmpresa").equals("EMIN") && tasaCambio.get(0).equals("1"))/*897.91*/{_display_(Seq[Any](format.raw/*897.92*/("""
								"""),format.raw/*898.9*/("""<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;"></TH>
								<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;"></TH>
								<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;"></TH>
							""")))}else/*901.13*/{_display_(Seq[Any](format.raw/*901.14*/("""
								"""),format.raw/*902.9*/("""<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;"></TH>
								<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;"></TH>
								<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;"></TH>
							""")))}),format.raw/*905.9*/("""
					"""),format.raw/*906.6*/("""</tfoot>
			 	</table>
			</div>
			</div>
			<div class="noprint">
				<form>
					<div align="center">
						<button type="button"  class="btn btn-sm btn-success" 
							onclick="history.go(-1);return false;">
							Volver
						</button>
					</div>
				</form>
			</div>
		</div>
		
		
		
		
		<script type="text/javascript">
			  $(document).ready(function () """),format.raw/*926.36*/("""{"""),format.raw/*926.37*/("""
				
			            """),format.raw/*928.16*/("""document.getElementById('mostrarmostrar').style.display="block"; 
				
			            var numero = new DecimalFormat("#,##0.00");
						
						var totCfi = parseFloat(totalDespachoCfi);
						var totArriendo = parseFloat(totalInicial)+parseFloat(totalDespachoArr)+parseFloat(totalDevolucionArr);
						var totVenta = parseFloat(totalDespachoVta)+parseFloat(totalDevolucionVta);
						
						var cantDec = """"),_display_(/*936.23*/cantDec),format.raw/*936.30*/("""";
						
						if(cantDec==0)"""),format.raw/*938.21*/("""{"""),format.raw/*938.22*/("""
							"""),format.raw/*939.8*/("""document.getElementById('total0').innerHTML=formatStandar0(totCfi);
							document.getElementById('total1').innerHTML=formatStandar0(totArriendo);
							document.getElementById('total2').innerHTML=formatStandar0(totVenta);
							document.getElementById('total3').innerHTML=formatStandar0(totVenta+totArriendo+totCfi);
						"""),format.raw/*943.7*/("""}"""),format.raw/*943.8*/("""
						"""),format.raw/*944.7*/("""if(cantDec==2)"""),format.raw/*944.21*/("""{"""),format.raw/*944.22*/("""
							"""),format.raw/*945.8*/("""document.getElementById('total0').innerHTML=formatStandar2(totCfi);
							document.getElementById('total1').innerHTML=formatStandar2(totArriendo);
							document.getElementById('total2').innerHTML=formatStandar2(totVenta);
							document.getElementById('total3').innerHTML=formatStandar2(totVenta+totArriendo+totCfi);
						"""),format.raw/*949.7*/("""}"""),format.raw/*949.8*/("""
						"""),format.raw/*950.7*/("""if(cantDec==4)"""),format.raw/*950.21*/("""{"""),format.raw/*950.22*/("""
							"""),format.raw/*951.8*/("""document.getElementById('total0').innerHTML=formatStandar4(totCfi);
							document.getElementById('total1').innerHTML=formatStandar4(totArriendo);
							document.getElementById('total2').innerHTML=formatStandar4(totVenta);
							document.getElementById('total3').innerHTML=formatStandar4(totVenta+totArriendo+totCfi);
						"""),format.raw/*955.7*/("""}"""),format.raw/*955.8*/("""
						"""),format.raw/*956.7*/("""if(cantDec==6)"""),format.raw/*956.21*/("""{"""),format.raw/*956.22*/("""
							"""),format.raw/*957.8*/("""document.getElementById('total0').innerHTML=formatStandar6(totCfi);
							document.getElementById('total1').innerHTML=formatStandar6(totArriendo);
							document.getElementById('total2').innerHTML=formatStandar6(totVenta);
							document.getElementById('total3').innerHTML=formatStandar6(totVenta+totArriendo+totCfi);
						"""),format.raw/*961.7*/("""}"""),format.raw/*961.8*/("""
						
						"""),format.raw/*963.7*/("""tabla = document.getElementById('resumen');
						celdasDeFila = "";
						var totalResumen0 = 0;
						var totalResumen1 = 0;
						var totalResumen2 = 0;
						var numero = new DecimalFormat("#,##0.00");
							for (var i=1;i<tabla.rows.length-1;i++)"""),format.raw/*969.47*/("""{"""),format.raw/*969.48*/("""
								"""),format.raw/*970.9*/("""celdas = tabla.rows[i].getElementsByTagName('td');
								totalResumen0 = totalResumen0 + parseFloat(numero.formatBack(celdas[1].innerHTML));
								totalResumen1 = totalResumen1 + parseFloat(numero.formatBack(celdas[2].innerHTML));
								totalResumen2 = totalResumen2 + parseFloat(numero.formatBack(celdas[3].innerHTML));
							"""),format.raw/*974.8*/("""}"""),format.raw/*974.9*/("""
						"""),format.raw/*975.7*/("""document.getElementById('TCFI').value=totalResumen0;
						document.getElementById('TA').value=totalResumen1;
						document.getElementById('TV').value=totalResumen2;
						
						
						//Determina total de ajustes al ep
						var totAjusteArr = 0;
						var totAjusteVta = 0;
						
						
						"""),_display_(/*985.8*/for(lista <- detalleAjuste) yield /*985.35*/{_display_(Seq[Any](format.raw/*985.36*/("""
							"""),format.raw/*986.8*/("""totAjusteArr = parseFloat(totAjusteArr) + parseFloat(numero.formatBack(""""),_display_(/*986.81*/lista/*986.86*/.get(1)),format.raw/*986.93*/(""""));
							totAjusteVta = parseFloat(totAjusteVta) + parseFloat(numero.formatBack(""""),_display_(/*987.81*/lista/*987.86*/.get(2)),format.raw/*987.93*/(""""));
						""")))}),format.raw/*988.8*/("""
						
						"""),format.raw/*990.7*/("""var totArriendo2 = parseFloat(totArriendo)+parseFloat(totAjusteArr);
						var totVenta2 = parseFloat(totVenta)+parseFloat(totAjusteVta);
						
						if(cantDec==0)"""),format.raw/*993.21*/("""{"""),format.raw/*993.22*/("""
							"""),format.raw/*994.8*/("""document.getElementById('resumenTotal0').innerHTML=formatStandar0(totCfi);
							document.getElementById('resumenTotal1').innerHTML=formatStandar0(totArriendo);
							document.getElementById('resumenTotal2').innerHTML=formatStandar0(totVenta);
							document.getElementById('resumenTotal3').innerHTML=formatStandar0(totCfi+totArriendo2+totVenta2);
							
							document.getElementById('inicialCfi').innerHTML=formatStandar0("0");
							document.getElementById('inicialArriendo').innerHTML=formatStandar0(totalInicial);
							document.getElementById('inicialVenta').innerHTML=formatStandar0("0");
							
							document.getElementById('despachoCfi').innerHTML=formatStandar0(totalDespachoCfi);
							document.getElementById('despachoArriendo').innerHTML=formatStandar0(totalDespachoArr);
							document.getElementById('despachoVenta').innerHTML=formatStandar0(totalDespachoVta);
							
							document.getElementById('devolucionCfi').innerHTML=formatStandar0("0");
							document.getElementById('devolucionArriendo').innerHTML=formatStandar0(totalDevolucionArr);
							document.getElementById('devolucionVenta').innerHTML=formatStandar0(totalDevolucionVta);
							
							document.getElementById('totCfi').innerHTML=formatStandar0(totCfi);
							document.getElementById('totArriendo').innerHTML=formatStandar0(totArriendo);
							document.getElementById('totVenta').innerHTML=formatStandar0(totVenta);
							
							document.getElementById('totCfi2').innerHTML=formatStandar0(totCfi);
							document.getElementById('totArriendo2').innerHTML=formatStandar0(totArriendo2);
							document.getElementById('totVenta2').innerHTML=formatStandar0(totVenta2);
							
							document.getElementById('resumenTotal02').innerHTML=formatStandar0(totCfi);
							document.getElementById('resumenTotal12').innerHTML=formatStandar0(totArriendo2);
							document.getElementById('resumenTotal22').innerHTML=formatStandar0(totVenta2);
						"""),format.raw/*1022.7*/("""}"""),format.raw/*1022.8*/("""
						
						"""),format.raw/*1024.7*/("""if(cantDec==2)"""),format.raw/*1024.21*/("""{"""),format.raw/*1024.22*/("""
							"""),format.raw/*1025.8*/("""document.getElementById('resumenTotal0').innerHTML=formatStandar2(totCfi);
							document.getElementById('resumenTotal1').innerHTML=formatStandar2(totArriendo);
							document.getElementById('resumenTotal2').innerHTML=formatStandar2(totVenta);
							document.getElementById('resumenTotal3').innerHTML=formatStandar2(totCfi+totArriendo2+totVenta2);
							
							document.getElementById('inicialCfi').innerHTML=formatStandar2("0");
							document.getElementById('inicialArriendo').innerHTML=formatStandar2(totalInicial);
							document.getElementById('inicialVenta').innerHTML=formatStandar2("0");
							
							document.getElementById('despachoCfi').innerHTML=formatStandar2(totalDespachoCfi);
							document.getElementById('despachoArriendo').innerHTML=formatStandar2(totalDespachoArr);
							document.getElementById('despachoVenta').innerHTML=formatStandar2(totalDespachoVta);
							
							document.getElementById('devolucionCfi').innerHTML=formatStandar2("0");
							document.getElementById('devolucionArriendo').innerHTML=formatStandar2(totalDevolucionArr);
							document.getElementById('devolucionVenta').innerHTML=formatStandar2(totalDevolucionVta);
							
							document.getElementById('totCfi').innerHTML=formatStandar2(totCfi);
							document.getElementById('totArriendo').innerHTML=formatStandar2(totArriendo);
							document.getElementById('totVenta').innerHTML=formatStandar2(totVenta);
							
							document.getElementById('totCfi2').innerHTML=formatStandar2(totCfi);
							document.getElementById('totArriendo2').innerHTML=formatStandar2(totArriendo2);
							document.getElementById('totVenta2').innerHTML=formatStandar2(totVenta2);
							
							document.getElementById('resumenTotal02').innerHTML=formatStandar2(totCfi);
							document.getElementById('resumenTotal12').innerHTML=formatStandar2(totArriendo2);
							document.getElementById('resumenTotal22').innerHTML=formatStandar2(totVenta2);
						"""),format.raw/*1053.7*/("""}"""),format.raw/*1053.8*/("""
						
						"""),format.raw/*1055.7*/("""if(cantDec==4)"""),format.raw/*1055.21*/("""{"""),format.raw/*1055.22*/("""
							"""),format.raw/*1056.8*/("""document.getElementById('resumenTotal0').innerHTML=formatStandar4(totCfi);
							document.getElementById('resumenTotal1').innerHTML=formatStandar4(totArriendo);
							document.getElementById('resumenTotal2').innerHTML=formatStandar4(totVenta);
							document.getElementById('resumenTotal3').innerHTML=formatStandar4(totCfi+totArriendo2+totVenta2);
							
							document.getElementById('inicialCfi').innerHTML=formatStandar4("0");
							document.getElementById('inicialArriendo').innerHTML=formatStandar4(totalInicial);
							document.getElementById('inicialVenta').innerHTML=formatStandar4("0");
							
							document.getElementById('despachoCfi').innerHTML=formatStandar4(totalDespachoCfi);
							document.getElementById('despachoArriendo').innerHTML=formatStandar4(totalDespachoArr);
							document.getElementById('despachoVenta').innerHTML=formatStandar4(totalDespachoVta);
							
							document.getElementById('devolucionCfi').innerHTML=formatStandar4("0");
							document.getElementById('devolucionArriendo').innerHTML=formatStandar4(totalDevolucionArr);
							document.getElementById('devolucionVenta').innerHTML=formatStandar4(totalDevolucionVta);
							
							document.getElementById('totCfi').innerHTML=formatStandar4(totCfi);
							document.getElementById('totArriendo').innerHTML=formatStandar4(totArriendo);
							document.getElementById('totVenta').innerHTML=formatStandar4(totVenta);
							
							document.getElementById('totCfi2').innerHTML=formatStandar4(totCfi);
							document.getElementById('totArriendo2').innerHTML=formatStandar4(totArriendo2);
							document.getElementById('totVenta2').innerHTML=formatStandar4(totVenta2);
							
							document.getElementById('resumenTotal02').innerHTML=formatStandar4(totCfi);
							document.getElementById('resumenTotal12').innerHTML=formatStandar4(totArriendo2);
							document.getElementById('resumenTotal22').innerHTML=formatStandar4(totVenta2);
						"""),format.raw/*1084.7*/("""}"""),format.raw/*1084.8*/("""
						
						"""),format.raw/*1086.7*/("""if(cantDec==6)"""),format.raw/*1086.21*/("""{"""),format.raw/*1086.22*/("""
							"""),format.raw/*1087.8*/("""document.getElementById('resumenTotal0').innerHTML=formatStandar6(totCfi);
							document.getElementById('resumenTotal1').innerHTML=formatStandar6(totArriendo);
							document.getElementById('resumenTotal2').innerHTML=formatStandar6(totVenta);
							document.getElementById('resumenTotal3').innerHTML=formatStandar6(totCfi+totArriendo2+totVenta2);
							
							document.getElementById('inicialCfi').innerHTML=formatStandar6("0");
							document.getElementById('inicialArriendo').innerHTML=formatStandar6(totalInicial);
							document.getElementById('inicialVenta').innerHTML=formatStandar6("0");
							
							document.getElementById('despachoCfi').innerHTML=formatStandar6(totalDespachoCfi);
							document.getElementById('despachoArriendo').innerHTML=formatStandar6(totalDespachoArr);
							document.getElementById('despachoVenta').innerHTML=formatStandar6(totalDespachoVta);
							
							document.getElementById('devolucionCfi').innerHTML=formatStandar6("0");
							document.getElementById('devolucionArriendo').innerHTML=formatStandar6(totalDevolucionArr);
							document.getElementById('devolucionVenta').innerHTML=formatStandar6(totalDevolucionVta);
							
							document.getElementById('totCfi').innerHTML=formatStandar6(totCfi);
							document.getElementById('totArriendo').innerHTML=formatStandar6(totArriendo);
							document.getElementById('totVenta').innerHTML=formatStandar6(totVenta);
							
							document.getElementById('totCfi2').innerHTML=formatStandar6(totCfi);
							document.getElementById('totArriendo2').innerHTML=formatStandar6(totArriendo2);
							document.getElementById('totVenta2').innerHTML=formatStandar6(totVenta2);
							
							document.getElementById('resumenTotal02').innerHTML=formatStandar6(totCfi);
							document.getElementById('resumenTotal12').innerHTML=formatStandar6(totArriendo2);
							document.getElementById('resumenTotal22').innerHTML=formatStandar6(totVenta2);
						"""),format.raw/*1115.7*/("""}"""),format.raw/*1115.8*/("""
						
						
						
						
						
						"""),format.raw/*1121.7*/("""let pArrDiaInvFin = document.querySelectorAll('.pArrDiaInvFin');
						let pVentaInvFin = document.querySelectorAll('.pVentaInvFin');
						let cantInvFin = document.querySelectorAll('.cantInvFin');
						
						let totVtaFin = 0;
						let totArrFin = 0;
						
						for (let i = 0; i < cantInvFin.length; i++) """),format.raw/*1128.51*/("""{"""),format.raw/*1128.52*/("""
							"""),format.raw/*1129.8*/("""let cantFin = cantInvFin[i].textContent.replace(/,/g,'');
							let pVtaFin = pVentaInvFin[i].textContent.replace(/,/g,'');
							let pArrFin = pArrDiaInvFin[i].textContent.replace(/,/g,'');
							
							totVtaFin += parseFloat(cantFin) * parseFloat(pVtaFin);
							totArrFin += parseFloat(cantFin) * parseFloat(pArrFin);
							
						"""),format.raw/*1136.7*/("""}"""),format.raw/*1136.8*/("""
						
						
						"""),format.raw/*1139.7*/("""$("#totParrDiaInvFin").text(formatStandar(totArrFin, cantDec));
						$("#totPvtaInvFin").text(formatStandar(totVtaFin, cantDec));
						
			   """),format.raw/*1142.7*/("""}"""),format.raw/*1142.8*/(""");
				
				
				
				
			

		</script>
		

		
		
""")))}),format.raw/*1154.2*/("""
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,idTipoUsuario:String,inicioPer:List[List[String]],guiasPer:List[List[String]],fechas:List[String],bodega:tables.BodegaEmpresa,proyecto:tables.Proyecto,tasaCambio:List[Double],resumenSubtotales:List[List[String]],id_bodegaEmpresa:Long,finalPer:List[List[String]],cliente:tables.Cliente,detalleAjuste:List[List[String]],mapReportPorGuia10:Map[String,List[List[String]]],cantDec:Long,listReferencias:List[tables.TipoReferencia],oc:String,listSol:List[tables.CotizaSolucion]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,idTipoUsuario,inicioPer,guiasPer,fechas,bodega,proyecto,tasaCambio,resumenSubtotales,id_bodegaEmpresa,finalPer,cliente,detalleAjuste,mapReportPorGuia10,cantDec,listReferencias,oc,listSol)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,String,List[List[String]],List[List[String]],List[String],tables.BodegaEmpresa,tables.Proyecto,List[Double],List[List[String]],Long,List[List[String]],tables.Cliente,List[List[String]],Map[String,List[List[String]]],Long,List[tables.TipoReferencia],String,List[tables.CotizaSolucion]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,idTipoUsuario,inicioPer,guiasPer,fechas,bodega,proyecto,tasaCambio,resumenSubtotales,id_bodegaEmpresa,finalPer,cliente,detalleAjuste,mapReportPorGuia10,cantDec,listReferencias,oc,listSol) => apply(mapDiccionario,mapPermiso,userMnu,idTipoUsuario,inicioPer,guiasPer,fechas,bodega,proyecto,tasaCambio,resumenSubtotales,id_bodegaEmpresa,finalPer,cliente,detalleAjuste,mapReportPorGuia10,cantDec,listReferencias,oc,listSol)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reportFacturaProyectoDetalle.scala.html
                  HASH: 4ba87f5b2a13da995b408069d3d2788146e492e0
                  MATRIX: 1313->1|2007->602|2036->604|2145->685|2174->686|2216->700|2289->746|2318->747|2350->752|2688->1062|2728->1063|2760->1068|3531->1812|3568->1833|3607->1834|3651->1850|3694->1866|3708->1871|3737->1879|3767->1882|3781->1887|3816->1901|3872->1926|3914->1940|4434->2433|4509->2487|4822->2773|4845->2787|4882->2803|4920->2813|5166->3016|5198->3021|5270->3066|5285->3072|5309->3075|5388->3127|5403->3133|5431->3140|5510->3192|5525->3198|5553->3205|5624->3249|5643->3259|5671->3266|5743->3311|5762->3321|5790->3328|5862->3373|5881->3383|5909->3390|6542->3995|6582->3996|6618->4005|6807->4167|6830->4181|6867->4197|6903->4206|6944->4228|6983->4229|7019->4238|7209->4401|7232->4415|7269->4431|7305->4440|7353->4458|7403->4480|8545->5593|8586->5594|8622->5602|8903->5863|8943->5864|8979->5872|9087->5949|9124->5958|9255->6060|9285->6061|9318->6066|9366->6085|9396->6086|9431->6093|9673->6306|9703->6307|9733->6308|9768->6315|9797->6316|9830->6320|9860->6321|9894->6327|10014->6418|10044->6419|10079->6426|10353->6671|10383->6672|10424->6684|10464->6695|10494->6696|10531->6705|10777->6922|10807->6923|10837->6924|10875->6934|10904->6935|10937->6939|10967->6940|11006->6950|11085->7001|11114->7002|11154->7013|11184->7014|11225->7026|11255->7027|11290->7034|11319->7035|11356->7044|11385->7045|11428->7060|11490->7093|11520->7094|11555->7101|11727->7244|11757->7245|11793->7253|11922->7354|11951->7355|11984->7360|12013->7361|12046->7366|12104->7395|12134->7396|12168->7402|12542->7749|12593->7783|12633->7784|12667->7790|12732->7827|12752->7837|12781->7844|12812->7847|12832->7857|12863->7866|12911->7883|12944->7888|13451->8367|13480->8368|13510->8370|13552->8385|13570->8393|13611->8395|13643->8400|13713->8448|13747->8455|13793->8479|13824->8483|13866->8503|13900->8509|13979->8561|14200->8760|14237->8769|15305->9809|15345->9810|15382->9819|15448->9857|15472->9871|15510->9887|15750->10098|15780->10099|15823->10113|16078->10339|16119->10340|16163->10355|16208->10372|16225->10379|16250->10382|16292->10404|16332->10405|16376->10420|16466->10478|16508->10491|16538->10492|16571->10496|16601->10497|16644->10511|16737->10575|16767->10576|16797->10577|16841->10592|16871->10593|16917->10611|16941->10625|16979->10641|17019->10652|17287->10891|17317->10892|17359->10905|17613->11130|17654->11131|17697->11145|17742->11162|17759->11169|17784->11172|17825->11193|17865->11194|17908->11208|17997->11265|18038->11277|18068->11278|18101->11282|18131->11283|18173->11296|18263->11357|18293->11358|18323->11359|18366->11373|18396->11374|18506->11440|18541->11447|18759->11637|18775->11643|18804->11650|18886->11704|18902->11710|18931->11717|19005->11763|19025->11773|19054->11780|19129->11827|19149->11837|19178->11844|19253->11891|19273->11901|19302->11908|19383->11961|19399->11967|19429->11975|19886->12404|19926->12405|19965->12415|20126->12548|20143->12555|20169->12559|20199->12560|20231->12564|20248->12571|20277->12578|20352->12608|20389->12617|20497->12697|20521->12711|20557->12725|20630->12770|20646->12776|20689->12797|20877->12957|20895->12965|20940->12988|21133->13153|21149->13159|21178->13166|21208->13167|21243->13174|21259->13180|21288->13187|21477->13348|21493->13354|21525->13364|21884->13695|21908->13709|21946->13725|23224->14975|23268->15002|23308->15003|23347->15013|23422->15060|23437->15065|23466->15072|23592->15170|23607->15175|23636->15182|23713->15231|23728->15236|23757->15243|23818->15272|23864->15290|24423->15821|24447->15835|24485->15851|24515->15852|24953->16262|24977->16276|25015->16292|25135->16384|25186->16418|25226->16419|25358->16522|25399->16523|25438->16533|25511->16578|25529->16586|25572->16607|25647->16654|25665->16662|25694->16669|25769->16716|25787->16724|25816->16731|25891->16778|25909->16786|25938->16793|26014->16824|26056->16834|26093->16843|26535->17257|26579->17284|26619->17285|26658->17295|26733->17342|26748->17347|26777->17354|26903->17452|26918->17457|26947->17464|27024->17513|27039->17518|27068->17525|27129->17554|27175->17572|27620->17989|27644->17991|28894->19213|28934->19214|28971->19223|29304->19536|29344->19537|29381->19546|29516->19650|29633->19739|29673->19740|29710->19749|29826->19837|29851->19851|29885->19862|29943->19876|30053->19958|30093->19959|30130->19968|30236->20046|30261->20060|30295->20071|30326->20072|30554->20272|30579->20286|30613->20297|30644->20298|30697->20331|30737->20332|30774->20341|30880->20419|30905->20433|30939->20444|30995->20469|31031->20477|31364->20781|31405->20782|31442->20791|31647->20968|31671->20982|31710->20998|31860->21128|31900->21129|31937->21138|32051->21224|32076->21238|32112->21251|32230->21341|32254->21355|32293->21371|32330->21379|32355->21393|32391->21406|32521->21508|32546->21522|32582->21535|32627->21549|32679->21573|33032->21899|33095->21945|33135->21946|33171->21954|33243->21998|33260->22005|33289->22012|33400->22095|33440->22096|33479->22106|33552->22159|33592->22160|33631->22170|33730->22241|33747->22248|33776->22255|33810->22260|33828->22267|33858->22274|33905->22289|33951->22307|34044->22372|34061->22379|34090->22386|34124->22392|34142->22399|34172->22406|34283->22489|34300->22496|34329->22503|34363->22509|34381->22516|34412->22524|34500->22584|34517->22591|34547->22599|34621->22645|34638->22652|34668->22660|34743->22707|34760->22714|34790->22722|34924->22827|34965->22828|35004->22838|35120->22934|35160->22935|35264->23011|35304->23012|35344->23023|35434->23085|35461->23090|35563->23164|35580->23171|35610->23179|35667->23208|35684->23215|35714->23223|35845->23325|35875->23326|35918->23340|36067->23461|36094->23466|36152->23495|36182->23496|36215->23500|36245->23501|36288->23515|36409->23608|36436->23613|36494->23642|36524->23643|36565->23655|36661->23723|36678->23730|36708->23738|36769->23754|36808->23764|36868->23796|36885->23803|36915->23811|37031->23899|37071->23900|37111->23911|37198->23970|37225->23975|37327->24049|37344->24056|37374->24064|37451->24113|37468->24120|37498->24128|37556->24157|37586->24158|37629->24172|37689->24204|37716->24209|37774->24238|37804->24239|37837->24243|37867->24244|37910->24258|38058->24378|38085->24383|38143->24412|38173->24413|38227->24438|38308->24474|38350->24484|38453->24559|38493->24560|38532->24570|38619->24629|38646->24634|38746->24706|38763->24713|38793->24721|38849->24749|38866->24756|38896->24764|39025->24864|39055->24865|39097->24878|39243->24996|39270->25001|39327->25029|39357->25030|39390->25034|39420->25035|39462->25048|39580->25138|39607->25143|39664->25171|39694->25172|39734->25183|39828->25249|39845->25256|39875->25264|39935->25279|39972->25288|40032->25320|40049->25327|40079->25335|40154->25382|40171->25389|40201->25397|40275->25443|40292->25450|40322->25458|40396->25504|40413->25511|40443->25519|40527->25575|40544->25582|40574->25590|40613->25600|40721->25680|40738->25687|40768->25695|40874->25773|40891->25780|40921->25788|40996->25835|41013->25842|41064->25871|41122->25898|41158->25906|41302->26023|41366->26070|41406->26071|41443->26080|41535->26144|41553->26152|41582->26159|41613->26162|41631->26170|41660->26177|41760->26249|41784->26263|41818->26275|41849->26278|41868->26286|41898->26293|41929->26294|41972->26308|41991->26316|42022->26324|42127->26401|42145->26409|42174->26416|42283->26497|42301->26505|42330->26512|42436->26590|42476->26591|42515->26602|42609->26679|42649->26680|42757->26760|42797->26761|42838->26773|42914->26821|42932->26829|42961->26836|43095->26942|43135->26943|43178->26957|43255->27014|43295->27015|43338->27029|43437->27100|43455->27108|43485->27115|43519->27120|43538->27128|43568->27135|43619->27154|43674->27180|43767->27245|43785->27253|43815->27261|43850->27267|43869->27275|43900->27283|44015->27370|44033->27378|44063->27386|44098->27392|44117->27400|44148->27408|44240->27472|44258->27480|44288->27488|44366->27538|44384->27546|44414->27554|44493->27605|44511->27613|44541->27621|44680->27731|44721->27732|44764->27746|44888->27850|44928->27851|45036->27931|45076->27932|45120->27947|45214->28013|45241->28018|45271->28020|45299->28026|45409->28108|45427->28116|45457->28124|45518->28157|45536->28165|45566->28173|45705->28283|45735->28284|45782->28302|45935->28427|45962->28432|45992->28434|46020->28440|46082->28473|46112->28474|46145->28478|46175->28479|46222->28497|46347->28594|46374->28599|46404->28601|46432->28607|46494->28640|46524->28641|46569->28657|46673->28733|46691->28741|46721->28749|46786->28769|46829->28783|46889->28815|46907->28823|46937->28831|47057->28923|47097->28924|47141->28939|47234->29004|47261->29009|47291->29011|47319->29017|47429->29099|47447->29107|47477->29115|47558->29168|47576->29176|47606->29184|47668->29217|47698->29218|47745->29236|47807->29270|47834->29275|47864->29277|47892->29283|47954->29316|47984->29317|48017->29321|48047->29322|48094->29340|48248->29466|48275->29471|48305->29473|48333->29479|48395->29512|48425->29513|48487->29546|48576->29590|48622->29604|48729->29683|48769->29684|48812->29698|48903->29761|48930->29766|48960->29768|48988->29774|49096->29854|49114->29862|49144->29870|49204->29902|49222->29910|49252->29918|49389->30026|49419->30027|49465->30044|49615->30166|49642->30171|49672->30173|49700->30179|49761->30211|49791->30212|49824->30216|49854->30217|49900->30234|50022->30328|50049->30333|50079->30335|50107->30341|50168->30373|50198->30374|50242->30389|50344->30463|50362->30471|50392->30479|50456->30498|50498->30511|50558->30543|50576->30551|50606->30559|50684->30609|50702->30617|50732->30625|50810->30675|50828->30683|50858->30691|50950->30755|50968->30763|50998->30771|51041->30785|51126->30842|51144->30850|51174->30858|51209->30864|51239->30865|51285->30882|51382->30951|51400->30959|51431->30967|51479->30986|51509->30987|51552->31001|51666->31087|51684->31095|51714->31103|51757->31117|51842->31174|51860->31182|51890->31190|51925->31196|51955->31197|52000->31213|52097->31282|52115->31290|52146->31298|52194->31317|52224->31318|52257->31322|52287->31323|52332->31339|52433->31412|52451->31420|52482->31428|52530->31447|52560->31448|52603->31462|52717->31548|52735->31556|52765->31564|52808->31578|52893->31635|52911->31643|52941->31651|52976->31657|53006->31658|53051->31674|53148->31743|53166->31751|53197->31759|53245->31778|53275->31779|53308->31783|53338->31784|53383->31800|53484->31873|53502->31881|53533->31889|53581->31908|53611->31909|53654->31923|53817->32041|53860->32052|53915->32062|53955->32071|53991->32079|54207->32267|54247->32268|54284->32277|54484->32433|54520->32441|54995->32888|55019->32902|55058->32918|55089->32919|55209->33011|55249->33012|55286->33021|55486->33177|55522->33185|55749->33384|55789->33385|55825->33393|56086->33610|56120->33616|57123->34591|57163->34592|57200->34601|57533->34914|57573->34915|57610->34924|57745->35028|57854->35109|57894->35110|57931->35119|58079->35223|58181->35297|58221->35298|58258->35307|58595->35624|58635->35625|58672->35634|58806->35737|58842->35745|59155->36030|59195->36031|59232->36040|59336->36116|59360->36130|59399->36146|59652->36379|59692->36380|59729->36389|59833->36465|59857->36479|59896->36495|59933->36503|59958->36517|59994->36530|60124->36632|60149->36646|60185->36659|60412->36855|60455->36870|60626->37014|60688->37059|60728->37060|60764->37068|60836->37112|60853->37119|60882->37126|60993->37209|61033->37210|61072->37220|61145->37273|61185->37274|61224->37284|61323->37355|61340->37362|61369->37369|61403->37374|61421->37381|61451->37388|61498->37403|61544->37421|61637->37486|61654->37493|61683->37500|61717->37506|61735->37513|61765->37520|61876->37603|61893->37610|61922->37617|61956->37623|61974->37630|62005->37638|62093->37698|62110->37705|62140->37713|62233->37778|62250->37785|62280->37793|62355->37840|62372->37847|62402->37855|62536->37960|62577->37961|62616->37971|62732->38067|62772->38068|62876->38144|62916->38145|62956->38156|63051->38223|63078->38228|63180->38302|63197->38309|63227->38317|63284->38346|63301->38353|63331->38361|63462->38463|63492->38464|63535->38478|63689->38604|63716->38609|63774->38638|63804->38639|63837->38643|63867->38644|63910->38658|64036->38756|64063->38761|64121->38790|64151->38791|64192->38803|64288->38871|64305->38878|64335->38886|64396->38902|64435->38912|64516->38965|64533->38972|64563->38980|64679->39068|64719->39069|64759->39080|64848->39141|64875->39146|64977->39220|64994->39227|65024->39235|65101->39284|65118->39291|65148->39299|65206->39328|65236->39329|65279->39343|65341->39377|65368->39382|65426->39411|65456->39412|65489->39416|65519->39417|65562->39431|65712->39553|65739->39558|65797->39587|65827->39588|65881->39613|65962->39649|66004->39659|66107->39734|66147->39735|66186->39745|66278->39809|66305->39814|66405->39886|66422->39893|66452->39901|66508->39929|66525->39936|66555->39944|66684->40044|66714->40045|66756->40058|66907->40181|66934->40186|66991->40214|67021->40215|67054->40219|67084->40220|67126->40233|67249->40328|67276->40333|67333->40361|67363->40362|67403->40373|67497->40439|67514->40446|67544->40454|67604->40469|67641->40478|67723->40532|67740->40539|67770->40547|67844->40593|67861->40600|67891->40608|67965->40654|67982->40661|68012->40669|68086->40715|68103->40722|68133->40730|68254->40823|68271->40830|68322->40859|68380->40886|68415->40893|69169->41619|69209->41620|69246->41629|69556->41919|69596->41920|69633->41929|69773->42038|69882->42119|69922->42120|69959->42129|70092->42218|70194->42292|70234->42293|70271->42302|70584->42595|70624->42596|70661->42605|70803->42716|70839->42724|71126->42983|71166->42984|71203->42993|71494->43264|71534->43265|71571->43274|71869->43541|71903->43547|72299->43914|72329->43915|72379->43936|72814->44343|72843->44350|72902->44380|72932->44381|72968->44389|73323->44716|73352->44717|73387->44724|73430->44738|73460->44739|73496->44747|73851->45074|73880->45075|73915->45082|73958->45096|73988->45097|74024->45105|74379->45432|74408->45433|74443->45440|74486->45454|74516->45455|74552->45463|74907->45790|74936->45791|74978->45805|75259->46057|75289->46058|75326->46067|75688->46401|75717->46402|75752->46409|76078->46708|76122->46735|76162->46736|76198->46744|76299->46817|76314->46822|76343->46829|76456->46914|76471->46919|76500->46926|76543->46938|76585->46952|76779->47117|76809->47118|76845->47126|78815->49067|78845->49068|78888->49082|78932->49096|78963->49097|79000->49105|80970->51046|81000->51047|81043->51061|81087->51075|81118->51076|81155->51084|83125->53025|83155->53026|83198->53040|83242->53054|83273->53055|83310->53063|85280->55004|85310->55005|85381->55047|85724->55360|85755->55361|85792->55369|86162->55710|86192->55711|86242->55732|86415->55876|86445->55877|86528->55928
                  LINES: 28->1|40->9|41->10|43->12|43->12|44->13|46->15|46->15|47->16|55->24|55->24|56->25|69->38|69->38|69->38|70->39|70->39|70->39|70->39|70->39|70->39|70->39|71->40|72->41|86->55|86->55|93->62|93->62|93->62|94->63|101->70|103->72|103->72|103->72|103->72|104->73|104->73|104->73|105->74|105->74|105->74|106->75|106->75|106->75|107->76|107->76|107->76|108->77|108->77|108->77|116->85|116->85|117->86|119->88|119->88|119->88|120->89|121->90|121->90|122->91|123->92|123->92|123->92|124->93|125->94|127->96|153->122|153->122|154->123|160->129|160->129|161->130|162->131|163->132|171->140|171->140|172->141|172->141|172->141|173->142|175->144|175->144|175->144|176->145|176->145|176->145|176->145|177->146|179->148|179->148|180->149|187->156|187->156|188->157|188->157|188->157|189->158|191->160|191->160|191->160|192->161|192->161|192->161|192->161|193->162|194->163|194->163|195->164|195->164|196->165|196->165|197->166|197->166|199->168|199->168|203->172|203->172|203->172|204->173|206->175|206->175|207->176|209->178|209->178|210->179|210->179|211->180|211->180|211->180|212->181|219->188|219->188|219->188|220->189|220->189|220->189|220->189|220->189|220->189|220->189|221->190|222->191|232->201|232->201|233->202|236->205|236->205|236->205|238->207|238->207|240->209|240->209|241->210|241->210|243->212|244->213|245->214|247->216|275->244|275->244|276->245|277->246|277->246|277->246|280->249|280->249|281->250|282->251|282->251|283->252|283->252|283->252|283->252|284->253|284->253|285->254|286->255|287->256|287->256|287->256|287->256|288->257|288->257|288->257|288->257|289->258|289->258|290->259|290->259|290->259|291->260|296->265|296->265|297->266|298->267|298->267|299->268|299->268|299->268|299->268|300->269|300->269|301->270|302->271|303->272|303->272|303->272|303->272|304->273|304->273|304->273|304->273|305->274|305->274|309->278|310->279|316->285|316->285|316->285|317->286|317->286|317->286|318->287|318->287|318->287|319->288|319->288|319->288|320->289|320->289|320->289|321->290|321->290|321->290|334->303|334->303|335->304|337->306|337->306|337->306|337->306|337->306|337->306|337->306|339->308|340->309|341->310|341->310|341->310|342->311|342->311|342->311|346->315|346->315|346->315|350->319|350->319|350->319|350->319|350->319|350->319|350->319|354->323|354->323|354->323|361->330|361->330|361->330|390->359|390->359|390->359|391->360|392->361|392->361|392->361|394->363|394->363|394->363|395->364|395->364|395->364|397->366|399->368|411->380|411->380|411->380|411->380|419->388|419->388|419->388|422->391|422->391|422->391|423->392|423->392|424->393|425->394|425->394|425->394|426->395|426->395|426->395|427->396|427->396|427->396|428->397|428->397|428->397|430->399|431->400|432->401|440->409|440->409|440->409|441->410|442->411|442->411|442->411|444->413|444->413|444->413|445->414|445->414|445->414|447->416|449->418|456->425|456->425|485->454|485->454|486->455|489->458|489->458|490->459|491->460|493->462|493->462|494->463|494->463|494->463|494->463|495->464|497->466|497->466|498->467|498->467|498->467|498->467|498->467|500->469|500->469|500->469|500->469|501->470|501->470|502->471|502->471|502->471|502->471|503->472|504->473|507->476|507->476|508->477|509->478|509->478|509->478|511->480|511->480|512->481|512->481|512->481|512->481|513->482|513->482|513->482|513->482|513->482|513->482|514->483|514->483|514->483|515->484|518->487|525->494|525->494|525->494|526->495|527->496|527->496|527->496|529->498|529->498|530->499|531->500|531->500|532->501|532->501|532->501|532->501|532->501|532->501|532->501|533->502|535->504|535->504|535->504|535->504|535->504|535->504|535->504|536->505|536->505|536->505|536->505|536->505|536->505|538->507|538->507|538->507|539->508|539->508|539->508|540->509|540->509|540->509|541->510|541->510|542->511|544->513|544->513|545->514|545->514|546->515|547->516|547->516|549->518|549->518|549->518|550->519|550->519|550->519|552->521|552->521|553->522|554->523|554->523|555->524|555->524|555->524|555->524|556->525|557->526|557->526|558->527|558->527|559->528|561->530|561->530|561->530|562->531|563->532|563->532|563->532|563->532|564->533|564->533|565->534|566->535|566->535|568->537|568->537|568->537|569->538|569->538|569->538|570->539|570->539|571->540|571->540|571->540|572->541|572->541|572->541|572->541|573->542|574->543|574->543|575->544|575->544|577->546|579->548|580->549|581->550|581->550|582->551|583->552|583->552|585->554|585->554|585->554|586->555|586->555|586->555|588->557|588->557|589->558|590->559|590->559|591->560|591->560|591->560|591->560|592->561|593->562|593->562|594->563|594->563|595->564|597->566|597->566|597->566|598->567|599->568|599->568|599->568|599->568|600->569|600->569|600->569|601->570|601->570|601->570|602->571|602->571|602->571|604->573|604->573|604->573|605->574|606->575|606->575|606->575|609->578|609->578|609->578|610->579|610->579|610->579|612->581|613->582|616->585|616->585|616->585|617->586|618->587|618->587|618->587|618->587|618->587|618->587|619->588|619->588|619->588|619->588|619->588|619->588|619->588|619->588|619->588|619->588|620->589|620->589|620->589|621->590|621->590|621->590|623->592|623->592|624->593|624->593|624->593|625->594|625->594|626->595|627->596|627->596|627->596|630->599|630->599|631->600|632->601|632->601|633->602|633->602|633->602|633->602|633->602|633->602|633->602|634->603|636->605|636->605|636->605|636->605|636->605|636->605|636->605|637->606|637->606|637->606|637->606|637->606|637->606|639->608|639->608|639->608|640->609|640->609|640->609|641->610|641->610|641->610|642->611|642->611|643->612|645->614|645->614|646->615|646->615|647->616|648->617|648->617|648->617|648->617|650->619|650->619|650->619|651->620|651->620|651->620|653->622|653->622|654->623|655->624|655->624|655->624|655->624|656->625|656->625|656->625|656->625|657->626|658->627|658->627|658->627|658->627|659->628|659->628|660->629|662->631|662->631|662->631|663->632|664->633|664->633|664->633|664->633|665->634|665->634|666->635|667->636|667->636|667->636|667->636|669->638|669->638|669->638|670->639|670->639|670->639|671->640|671->640|672->641|672->641|672->641|672->641|672->641|673->642|673->642|673->642|673->642|674->643|675->644|675->644|675->644|675->644|676->645|676->645|678->647|680->649|681->650|682->651|682->651|683->652|684->653|684->653|684->653|684->653|686->655|686->655|686->655|687->656|687->656|687->656|689->658|689->658|690->659|691->660|691->660|691->660|691->660|692->661|692->661|692->661|692->661|693->662|694->663|694->663|694->663|694->663|695->664|695->664|696->665|698->667|698->667|698->667|699->668|700->669|700->669|700->669|700->669|701->670|701->670|701->670|702->671|702->671|702->671|704->673|704->673|704->673|705->674|706->675|706->675|706->675|706->675|706->675|707->676|707->676|707->676|707->676|708->677|708->677|709->678|712->681|712->681|712->681|713->682|714->683|714->683|714->683|714->683|714->683|715->684|715->684|715->684|715->684|716->685|716->685|716->685|716->685|717->686|717->686|717->686|717->686|718->687|718->687|719->688|722->691|722->691|722->691|723->692|724->693|724->693|724->693|724->693|724->693|725->694|725->694|725->694|725->694|726->695|726->695|726->695|726->695|727->696|727->696|727->696|727->696|728->697|728->697|729->698|734->703|735->704|736->705|737->706|738->707|740->709|740->709|741->710|744->713|745->714|751->720|751->720|751->720|751->720|752->721|752->721|753->722|756->725|757->726|760->729|760->729|761->730|765->734|766->735|783->752|783->752|784->753|787->756|787->756|788->757|789->758|790->759|790->759|791->760|792->761|793->762|793->762|794->763|797->766|797->766|798->767|799->768|800->769|802->771|802->771|803->772|803->772|803->772|803->772|806->775|806->775|807->776|807->776|807->776|807->776|807->776|807->776|807->776|808->777|808->777|808->777|810->779|812->781|818->787|818->787|818->787|819->788|820->789|820->789|820->789|822->791|822->791|823->792|824->793|824->793|825->794|825->794|825->794|825->794|825->794|825->794|825->794|826->795|828->797|828->797|828->797|828->797|828->797|828->797|828->797|829->798|829->798|829->798|829->798|829->798|829->798|831->800|831->800|831->800|832->801|832->801|832->801|833->802|833->802|833->802|834->803|834->803|835->804|837->806|837->806|838->807|838->807|839->808|840->809|840->809|842->811|842->811|842->811|843->812|843->812|843->812|845->814|845->814|846->815|847->816|847->816|848->817|848->817|848->817|848->817|849->818|850->819|850->819|851->820|851->820|852->821|854->823|854->823|854->823|855->824|856->825|856->825|856->825|856->825|857->826|857->826|858->827|859->828|859->828|861->830|861->830|861->830|862->831|862->831|862->831|863->832|863->832|864->833|864->833|864->833|865->834|865->834|865->834|865->834|866->835|867->836|867->836|868->837|868->837|870->839|872->841|873->842|874->843|874->843|875->844|876->845|876->845|878->847|878->847|878->847|879->848|879->848|879->848|881->850|881->850|882->851|883->852|883->852|884->853|884->853|884->853|884->853|885->854|886->855|886->855|887->856|887->856|888->857|890->859|890->859|890->859|891->860|892->861|892->861|892->861|892->861|893->862|893->862|893->862|894->863|894->863|894->863|895->864|895->864|895->864|897->866|897->866|897->866|899->868|900->869|909->878|909->878|910->879|913->882|913->882|914->883|915->884|916->885|916->885|917->886|918->887|919->888|919->888|920->889|923->892|923->892|924->893|925->894|926->895|928->897|928->897|929->898|932->901|932->901|933->902|936->905|937->906|957->926|957->926|959->928|967->936|967->936|969->938|969->938|970->939|974->943|974->943|975->944|975->944|975->944|976->945|980->949|980->949|981->950|981->950|981->950|982->951|986->955|986->955|987->956|987->956|987->956|988->957|992->961|992->961|994->963|1000->969|1000->969|1001->970|1005->974|1005->974|1006->975|1016->985|1016->985|1016->985|1017->986|1017->986|1017->986|1017->986|1018->987|1018->987|1018->987|1019->988|1021->990|1024->993|1024->993|1025->994|1053->1022|1053->1022|1055->1024|1055->1024|1055->1024|1056->1025|1084->1053|1084->1053|1086->1055|1086->1055|1086->1055|1087->1056|1115->1084|1115->1084|1117->1086|1117->1086|1117->1086|1118->1087|1146->1115|1146->1115|1152->1121|1159->1128|1159->1128|1160->1129|1167->1136|1167->1136|1170->1139|1173->1142|1173->1142|1185->1154
                  -- GENERATED --
              */
          