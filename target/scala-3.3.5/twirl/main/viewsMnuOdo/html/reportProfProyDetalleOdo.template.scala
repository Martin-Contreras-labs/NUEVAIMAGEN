
package viewsMnuOdo.html

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

object reportProfProyDetalleOdo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template14[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],List[String],List[Double],tables.BodegaEmpresa,tables.Proyecto,tables.Cliente,Long,List[tables.TipoReferencia],List[List[String]],List[List[String]],List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu, 
detalleProformaPorServicio: List[List[String]], fechas: List[String], tasaCambio: List[Double], 
bodega: tables.BodegaEmpresa, proyecto: tables.Proyecto, cliente: tables.Cliente, cantDec: Long,
listReferencias: List[tables.TipoReferencia], agrupadoPorServicio: List[List[String]], listaAjustes: List[List[String]], groupPorClaseServicioEquipo: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*5.1*/("""



	"""),format.raw/*9.2*/("""<!-- ANCLA CABECERAS DE TABLAS -->
	<style type="text/css"> 
        thead tr th """),format.raw/*11.21*/("""{"""),format.raw/*11.22*/(""" 
            """),format.raw/*12.13*/("""position: sticky;
            top: 0;
        """),format.raw/*14.9*/("""}"""),format.raw/*14.10*/("""
    """),format.raw/*15.5*/("""</style>
	
	
	<form id="formProforma" class="formulario" method="post" action="/generarProfPdfXlsxXmlJsonOdo/">
		<input type="hidden" name="idBodega" value=""""),_display_(/*19.48*/bodega/*19.54*/.getId()),format.raw/*19.62*/("""">
		<input type="hidden" name="fechaDesde" value=""""),_display_(/*20.50*/fechas/*20.56*/.get(0)),format.raw/*20.63*/("""">
		<input type="hidden" name="fechaHasta" value=""""),_display_(/*21.50*/fechas/*21.56*/.get(1)),format.raw/*21.63*/("""">
		<input type="hidden" name="uf" value=""""),_display_(/*22.42*/tasaCambio/*22.52*/.get(0)),format.raw/*22.59*/("""">
		<input type="hidden" name="usd" value=""""),_display_(/*23.43*/tasaCambio/*23.53*/.get(1)),format.raw/*23.60*/("""">
		<input type="hidden" name="eur" value=""""),_display_(/*24.43*/tasaCambio/*24.53*/.get(2)),format.raw/*24.60*/("""">
		
		<div id='modalAgregaReferencias' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
			<div class='modal-dialog modal-dialog-scrollable' style="max-width: 50% !important;" role='document'>
			   	<div class='modal-content'>
					<div class='modal-header'>
						<h5 class='modal-title'>REFERENCIAS</h5>
						<div align="right">
							<button class='btn btn-sm btn-success' style='font-size: 10px;' data-dismiss='modal' onclick="document.getElementById('formProforma').submit()">
								GENERAR """),_display_(/*33.18*/mapDiccionario/*33.32*/.get("PROFORMA")),format.raw/*33.48*/("""
							"""),format.raw/*34.8*/("""</button>
					        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
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
				   	</div>
			   	</div>
			</div>
		</div>
		
	</form>
	
	<script>
		 	let verificaReferencia = (value, select) =>"""),format.raw/*67.48*/("""{"""),format.raw/*67.49*/("""
		 		"""),format.raw/*68.6*/("""if(value == 0)"""),format.raw/*68.20*/("""{"""),format.raw/*68.21*/("""
		 			"""),format.raw/*69.7*/("""select.parentNode.parentNode.remove();
		 			let tabla = document.getElementById('referencia');
		 			for (let i = 2; i < tabla.rows.length; i++)"""),format.raw/*71.50*/("""{"""),format.raw/*71.51*/("""
		 				"""),format.raw/*72.8*/("""let cellsOfRow = tabla.rows[i].getElementsByTagName('td');
		 				cellsOfRow[0].innerHTML=i-1;
		 			"""),format.raw/*74.7*/("""}"""),format.raw/*74.8*/("""
		 		"""),format.raw/*75.6*/("""}"""),format.raw/*75.7*/("""
		 	"""),format.raw/*76.5*/("""}"""),format.raw/*76.6*/("""
		 	"""),format.raw/*77.5*/("""let eliminaReferencia = (nodo) =>"""),format.raw/*77.38*/("""{"""),format.raw/*77.39*/("""
		 			"""),format.raw/*78.7*/("""nodo.parentNode.parentNode.remove();
		 			let tabla = document.getElementById('referencia');
		 			for (let i = 2; i < tabla.rows.length; i++)"""),format.raw/*80.50*/("""{"""),format.raw/*80.51*/("""
		 				"""),format.raw/*81.8*/("""let cellsOfRow = tabla.rows[i].getElementsByTagName('td');
		 				cellsOfRow[0].innerHTML=i-1;
		 			"""),format.raw/*83.7*/("""}"""),format.raw/*83.8*/("""
		 	"""),format.raw/*84.5*/("""}"""),format.raw/*84.6*/("""
	 		"""),format.raw/*85.5*/("""let agregarReferencia = () =>"""),format.raw/*85.34*/("""{"""),format.raw/*85.35*/("""
	 			"""),format.raw/*86.6*/("""let tabla = document.getElementById('referencia');
				let row = tabla.insertRow(tabla.rows.length);
				let cell = row.insertCell(0);
				let indice = tabla.rows.length-2;
				cell.innerHTML=indice+"<input type='hidden' id='nroLinRef' name='nroLinRef[]' value='"+indice+"'>";
				cell = row.insertCell(1);
				let listado = "<select id='tpoDocRef' name='tpoDocRef[]' onchange='verificaReferencia(value,this)'>";
				"""),_display_(/*93.6*/for(referencia <- listReferencias) yield /*93.40*/{_display_(Seq[Any](format.raw/*93.41*/("""
					"""),format.raw/*94.6*/("""listado = listado + "<option value='"""),_display_(/*94.43*/referencia/*94.53*/.codigo),format.raw/*94.60*/("""'>"""),_display_(/*94.63*/referencia/*94.73*/.concepto),format.raw/*94.82*/("""</option>";
				""")))}),format.raw/*95.6*/("""
				"""),format.raw/*96.5*/("""listado = listado + "</select>";
				cell.innerHTML=listado;
				cell=row.insertCell(2);
				cell.innerHTML="<input type='text' id='folioRef' name='folioRef[]' value=''>";
				cell=row.insertCell(3);
				cell.innerHTML="<input type='date' id='fchRef' name='fchRef[]' value=''>";
				cell=row.insertCell(4);
				cell.innerHTML="<input type='text' id='razonRef' name='razonRef[]' value=''>";
				cell=row.insertCell(5);
				cell.innerHTML="<a class='btn btn-mini btn-danger' onclick='eliminaReferencia(this)'>X</a>";
	 		"""),format.raw/*106.5*/("""}"""),format.raw/*106.6*/("""
	"""),format.raw/*107.2*/("""</script>

	
	"""),_display_(/*110.3*/main("")/*110.11*/ {_display_(Seq[Any](format.raw/*110.13*/("""

		"""),_display_(/*112.4*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*112.52*/("""
		"""),format.raw/*113.3*/("""<div id="mostrarmostrar" style="display:none;">
			"""),_display_(/*114.5*/barraTitulo(mapDiccionario, "DETALLE EP/FACTURA PROFORMA SERVICIOS " + mapDiccionario.get("BODEGA") + "/PROYECTO: " + bodega.getNombre(),
				"PERIODO: desde " + fechas.get(2) +" --- hasta: " +  fechas.get(3))),format.raw/*115.72*/("""
				
			"""),format.raw/*117.4*/("""<div class="noprint">
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
								<div align="right">
											HACER """),_display_(/*146.19*/mapDiccionario/*146.33*/.get("PROFORMA")),format.raw/*146.49*/(""": 
										<button type="button" class="btn btn-mini btn-info" tabindex="-1" 
											onclick=" 
												if(document.getElementById('totalTotal').value>0)"""),format.raw/*149.62*/("""{"""),format.raw/*149.63*/("""
													"""),format.raw/*150.14*/("""$('#modalAgregaReferencias').modal('show');
												"""),format.raw/*151.13*/("""}"""),format.raw/*151.14*/("""else"""),format.raw/*151.18*/("""{"""),format.raw/*151.19*/("""
													"""),format.raw/*152.14*/("""alertify.alert('No hay valor a emitir', function () """),format.raw/*152.66*/("""{"""),format.raw/*152.67*/("""}"""),format.raw/*152.68*/(""");
												"""),format.raw/*153.13*/("""}"""),format.raw/*153.14*/("""">
											DE SERVICIOS
										</button>
								</div>
						</td>
					</tr>
				</table>
			</div>
			
			<form id="excel" class="formulario" method="post" action="/reportProfProyDetalleOdoExcel/">
				<input type="hidden" name="fechaDesde" value=""""),_display_(/*163.52*/fechas/*163.58*/.get(0)),format.raw/*163.65*/("""">
				<input type="hidden" name="fechaHasta" value=""""),_display_(/*164.52*/fechas/*164.58*/.get(1)),format.raw/*164.65*/("""">
				<input type="hidden" name="uf" value=""""),_display_(/*165.44*/tasaCambio/*165.54*/.get(0)),format.raw/*165.61*/("""">
				<input type="hidden" name="usd" value=""""),_display_(/*166.45*/tasaCambio/*166.55*/.get(1)),format.raw/*166.62*/("""">
				<input type="hidden" name="eur" value=""""),_display_(/*167.45*/tasaCambio/*167.55*/.get(2)),format.raw/*167.62*/("""">
				<input type="hidden" name="id_bodega" value=""""),_display_(/*168.51*/bodega/*168.57*/.getId()),format.raw/*168.65*/("""">
			</form>
		

			
			<input type="hidden" id="totalTotal">
			
			<table class="table table-sm table-bordered table-condensed table-fluid">
					 <TR> 
						<td colspan="3" style= "text-align: left; vertical-align:top;">
							<table class="table table-sm table-hover table-bordered table-condensed table-fluid">
								"""),_display_(if(!cliente.rut.equals(null))/*179.39*/{_display_(Seq[Any](format.raw/*179.40*/("""
									"""),format.raw/*180.10*/("""<tr style= "background-color: #F2F5A9;">
										<th style="text-align:left;">CLIENTE</th>
										<th style="text-align:left;">"""),_display_(/*182.41*/cliente/*182.48*/.rut),format.raw/*182.52*/(""" """),format.raw/*182.53*/("""-- """),_display_(/*182.57*/cliente/*182.64*/.nombre),format.raw/*182.71*/("""</th>
									</tr>
								""")))} else {null} ),format.raw/*184.10*/("""
								"""),format.raw/*185.9*/("""<tr style= "background-color: #F2F5A9;">
									<th style="text-align:left;">"""),_display_(/*186.40*/mapDiccionario/*186.54*/.get("BODEGA")),format.raw/*186.68*/("""</th>
									<TH style="text-align:left;">"""),_display_(/*187.40*/bodega/*187.46*/.nombre.toUpperCase()),format.raw/*187.67*/("""</TH>
								</tr>
								<tr style= "background-color: #F2F5A9;">
									<th style="text-align:left;">PROYECTO</th>
									<th style="text-align:left;">"""),_display_(/*191.40*/proyecto/*191.48*/.nickName.toUpperCase()),format.raw/*191.71*/("""</TH>
								</tr>
								<tr style= "background-color: #F2F5A9;">
									<th style="text-align:left;">PERIODO</th>
									<th style="text-align:left;">desde """),_display_(/*195.46*/fechas/*195.52*/.get(2)),format.raw/*195.59*/(""" """),format.raw/*195.60*/("""hasta """),_display_(/*195.67*/fechas/*195.73*/.get(3)),format.raw/*195.80*/("""</TH>
								</tr>
								<tr style= "background-color: #F2F5A9;">
									<th style="text-align:left;">COMERCIAL</th>
									<th style="text-align:left;">"""),_display_(/*199.40*/bodega/*199.46*/.getNameComercial),format.raw/*199.63*/("""</TH>
								</tr>
							</table>
						</td>
						<td>&nbsp;</td>
						<td colspan="7" style= "text-align: left;">
							<table class="table table-sm table-hover table-bordered table-condensed table-fluid">
								<tr>
									<th colspan="3">TOTAL NETO + AJUSTES</th>
								</tr>
							</table>
							<table id="resumen" class="table table-sm table-hover table-bordered table-condensed table-fluid">
								<tr>
									<th width="55%"></th>
									<th width="15%" style="text-align:center;">TOTAL</th>
								</tr>
								"""),_display_(/*215.10*/for(subtotal <- agrupadoPorServicio) yield /*215.46*/{_display_(Seq[Any](format.raw/*215.47*/("""
									"""),format.raw/*216.10*/("""<tr>
										<td style="text-align:left;">"""),_display_(/*217.41*/subtotal/*217.49*/.get(0).toUpperCase()),format.raw/*217.70*/("""</td>
										<td style="text-align:right;" class="resumenTotal1">"""),_display_(/*218.64*/subtotal/*218.72*/.get(1)),format.raw/*218.79*/("""</td>
									</tr>
								""")))}),format.raw/*220.10*/("""
								
								"""),format.raw/*222.9*/("""<tr>
									<th style="text-align:left;">SUBTOTAL</th>
									<th style="text-align:right;"><div id="resumenTotal1"></div></th>
								</tr>
							</table>
							<table class="table table-sm table-hover table-bordered table-condensed table-fluid">
								"""),_display_(/*228.10*/for(lista <- listaAjustes) yield /*228.36*/{_display_(Seq[Any](format.raw/*228.37*/("""
									"""),format.raw/*229.10*/("""<tr>
										<td style= "text-align: left;">"""),_display_(/*230.43*/lista/*230.48*/.get(5)),format.raw/*230.55*/(""": """),_display_(/*230.58*/lista/*230.63*/.get(6)),format.raw/*230.70*/("""</td>
										<td style= "text-align: right;" class="ajustes">"""),_display_(/*231.60*/lista/*231.65*/.get(4)),format.raw/*231.72*/("""</td>
									<tr>
								""")))}),format.raw/*233.10*/("""
								
								"""),format.raw/*235.9*/("""<tr>
									<th  width="55%" style= "text-align: left;">GRAN TOTAL:</th>
									<th  width="15%" style="text-align:right;"><div id="resumenTotal12"></div></th>
								</tr>
							</table>
						</td>
					</TR>
			 </table>
		
			<table id="tablaResumen" class="table table-sm table-hover table-bordered table-condensed table-fluid" >
				<thead class="header">
					<TR> 
						<TH colspan="13" style="text-align:left;vertical-align:top;background-color:#eeeeee;">RESUMEN AGRUPADO:</TH>
					</TR>
		        	<TR> 
						<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">FAMILIA</TH>
						<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">NRO.COTI</TH>
						<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">COD-SERVICIO</TH>
						<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">SERVICIO</TH>
						<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">COD-EQUIPO</TH>
						<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">EQUIPO</TH>
						<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">UN</TH>
						<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">CANT</TH>
						<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">TOTAL</TH>
						
						<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">CANT MIN</TH>
						<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">TOTAL MINIMO</TH>
						<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">TOTAL ADICIONAL</TH>
						<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">GRAN TOTAL</TH>
					</TR>
				</thead>
				<tbody>
					"""),_display_(/*267.7*/for((inicial,index) <- groupPorClaseServicioEquipo.zipWithIndex) yield /*267.71*/{_display_(Seq[Any](format.raw/*267.72*/("""
						"""),format.raw/*268.7*/("""<tr>
							<td style="text-align: left;">"""),_display_(/*269.39*/inicial/*269.46*/.get(0)),format.raw/*269.53*/("""</td>
							<td style="text-align: center;">"""),_display_(/*270.41*/inicial/*270.48*/.get(12)),format.raw/*270.56*/("""</td>
							<td style="text-align: center;">"""),_display_(/*271.41*/inicial/*271.48*/.get(1)),format.raw/*271.55*/("""</td>
							<td style="text-align: left;">"""),_display_(/*272.39*/inicial/*272.46*/.get(2)),format.raw/*272.53*/("""</td>
							<td style="text-align: center;">"""),_display_(/*273.41*/inicial/*273.48*/.get(3)),format.raw/*273.55*/("""</td>
							<td style="text-align: left;">"""),_display_(/*274.39*/inicial/*274.46*/.get(4)),format.raw/*274.53*/("""</td>
							<td style="text-align: center;">"""),_display_(/*275.41*/inicial/*275.48*/.get(5)),format.raw/*275.55*/("""</td>
							<td style="text-align: right;" class="resumenTot1">"""),_display_(/*276.60*/inicial/*276.67*/.get(6)),format.raw/*276.74*/("""</td>
							<td style="text-align: right;" class="resumenTot2">"""),_display_(/*277.60*/inicial/*277.67*/.get(7)),format.raw/*277.74*/("""</td>
							
							<td style="text-align: right;" class="resumenTot21">"""),_display_(/*279.61*/inicial/*279.68*/.get(8)),format.raw/*279.75*/("""</td>
							<td style="text-align: right;" class="resumenTot22">"""),_display_(/*280.61*/inicial/*280.68*/.get(9)),format.raw/*280.75*/("""</td>
							<td style="text-align: right;" class="resumenTot23">"""),_display_(/*281.61*/inicial/*281.68*/.get(10)),format.raw/*281.76*/("""</td>
							<td style="text-align: right;" class="resumenTot24">"""),_display_(/*282.61*/inicial/*282.68*/.get(11)),format.raw/*282.76*/("""</td>
		 			""")))}),format.raw/*283.8*/("""
		 			"""),format.raw/*284.7*/("""<TR style="background-color: #9FF781">
						<td style="background-color: #9FF781" colspan="7">TOTALES NETO (sin ajustes)</td>
						<td style="background-color: #9FF781"><div align="right" id="resumenTot1"></div></td>
						<td style="background-color: #9FF781"><div align="right" id="resumenTot2"></div></td>

						<td style="background-color: #9FF781"><div align="right" id="resumenTot21"></div></td>
						<td style="background-color: #9FF781"><div align="right" id="resumenTot22"></div></td>
						<td style="background-color: #9FF781"><div align="right" id="resumenTot23"></div></td>
						<td style="background-color: #9FF781"><div align="right" id="resumenTot24"></div></td>
						
					</tr>
				</tbody>
			</table>
			
			<br>
			  
			<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid" >
				<thead class="header">
					<TR> 
						<TH colspan="7" style="text-align:left;vertical-align:top;background-color:#eeeeee;">DETALLE DE LOS SERVICIOS:</TH>
						<TH colspan="2" style="text-align:left;vertical-align:top;background-color:#eeeeee;">EQUIPOS ASOCIADOS:</TH>
						<TH colspan="2" style="text-align:left;vertical-align:top;background-color:#eeeeee;">HORARIO:</TH>
						<TH colspan="3" style="text-align:left;vertical-align:top;background-color:#eeeeee;">LECTURA:</TH>
						<TH colspan="6" style="text-align:left;vertical-align:top;background-color:#eeeeee;">VALORIZACION:</TH>
					</TR>
		        	<TR> 
						<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">ID</TH>
						<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">FAMILIA</TH>
						<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">NRO.COTI</TH>
						<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">COD-SERVICIO</TH>
						<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">SERVICIO</TH>
						<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">FECHA</TH>
						<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">DETALLE</TH>
						<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">COD-EQUIPO</TH>
						<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">EQUIPO</TH>
						<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">HR INI</TH>
						<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">HR TER</TH>
						<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">UN</TH>
						<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">LECT INI</TH>
						<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">LECT TER</TH>
						<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">CANT</TH>
						<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">MON</TH>
						<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">PU</TH>
						<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">SUBTOTAL</TH>
						<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">TASA</TH>
						<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">TOTAL</TH>
					</TR>
				</thead>
				<tbody>
					"""),_display_(/*333.7*/for((inicial,index) <- detalleProformaPorServicio.zipWithIndex) yield /*333.70*/{_display_(Seq[Any](format.raw/*333.71*/("""
						"""),format.raw/*334.7*/("""<tr>
							<td style="text-align: center;">"""),_display_(/*335.41*/inicial/*335.48*/.get(6)),format.raw/*335.55*/("""</td>
							<td style="text-align: left;">"""),_display_(/*336.39*/inicial/*336.46*/.get(1)),format.raw/*336.53*/("""</td>
							<td style="text-align: center;">"""),_display_(/*337.41*/inicial/*337.48*/.get(24)),format.raw/*337.56*/("""</td>
							<td style="text-align: center;">"""),_display_(/*338.41*/inicial/*338.48*/.get(2)),format.raw/*338.55*/("""</td>
							<td style="text-align: left;">"""),_display_(/*339.39*/inicial/*339.46*/.get(3)),format.raw/*339.53*/("""</td>
							<td style="text-align: center;">
								<div style="display: none;">"""),_display_(/*341.38*/utilities/*341.47*/.Fechas.AAMMDD(inicial.get(4))),format.raw/*341.77*/("""</div>
								"""),_display_(/*342.10*/inicial/*342.17*/.get(4)),format.raw/*342.24*/("""
							"""),format.raw/*343.8*/("""</td>
							<td style="text-align: left;">"""),_display_(/*344.39*/inicial/*344.46*/.get(5)),format.raw/*344.53*/("""</td>
							<td style="text-align: center;">"""),_display_(/*345.41*/inicial/*345.48*/.get(9)),format.raw/*345.55*/("""</td>
							<td style="text-align: left;">"""),_display_(/*346.39*/inicial/*346.46*/.get(10)),format.raw/*346.54*/("""</td>
							<td style="text-align: center;">"""),_display_(/*347.41*/inicial/*347.48*/.get(7)),format.raw/*347.55*/("""</td>
							<td style="text-align: center;">"""),_display_(/*348.41*/inicial/*348.48*/.get(8)),format.raw/*348.55*/("""</td>
							<td style="text-align: center;">"""),_display_(/*349.41*/inicial/*349.48*/.get(11)),format.raw/*349.56*/("""</td>
							<td style="text-align: right;">"""),_display_(/*350.40*/inicial/*350.47*/.get(12)),format.raw/*350.55*/("""</td>
							<td style="text-align: right;">"""),_display_(/*351.40*/inicial/*351.47*/.get(13)),format.raw/*351.55*/("""</td>
							<td style="text-align: right;">"""),_display_(/*352.40*/inicial/*352.47*/.get(14)),format.raw/*352.55*/("""</td>
							<td style="text-align: right;">"""),_display_(/*353.40*/inicial/*353.47*/.get(15)),format.raw/*353.55*/("""</td>
							
							
							<td style="text-align: right;">"""),_display_(/*356.40*/inicial/*356.47*/.get(16)),format.raw/*356.55*/("""</td>
							<td style="text-align: right;" class="total5">"""),_display_(/*357.55*/inicial/*357.62*/.get(17)),format.raw/*357.70*/("""</td>
							<td style="text-align: right;">"""),_display_(/*358.40*/inicial/*358.47*/.get(18)),format.raw/*358.55*/("""</td>
							<td style="text-align: right;" class="total7">"""),_display_(/*359.55*/inicial/*359.62*/.get(19)),format.raw/*359.70*/("""</td>
						</TR>
		 			""")))}),format.raw/*361.8*/("""
		 			"""),format.raw/*362.7*/("""<TR style="background-color: #9FF781">
						<td style="background-color: #9FF781" colspan="13">TOTALES NETO (sin ajustes)</td>
						<td style="background-color: #9FF781"><div align="right" id="total1"></div></td>
						<td style="background-color: #9FF781"><div align="right" id="total2"></div></td>
						<td style="background-color: #9FF781"><div align="right" id="total3"></div></td>
						<td style="background-color: #9FF781"><div align="right" id="total4"></div></td>
						<td style="background-color: #9FF781"><div align="right" id="total5"></div></td>
						<td style="background-color: #9FF781"><div align="right" id="total6"></div></td>
						<td style="background-color: #9FF781"><div align="right" id="total7"></div></td>
					</tr>
				</tbody>
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
			  $(document).ready(function () """),format.raw/*394.36*/("""{"""),format.raw/*394.37*/("""
				
					"""),format.raw/*396.6*/("""let subtotal = 0;
					$(".resumenTot1").each(function() """),format.raw/*397.40*/("""{"""),format.raw/*397.41*/("""
						"""),format.raw/*398.7*/("""let val = $(this).text().replace(/,/g,'');
						let numero = parseFloat(val) || 0;
						subtotal += parseFloat(numero);
					"""),format.raw/*401.6*/("""}"""),format.raw/*401.7*/(""");
					$("#resumenTot1").text(formatStandar(subtotal,'2'));
					
					subtotal = 0;
					$(".resumenTot2").each(function() """),format.raw/*405.40*/("""{"""),format.raw/*405.41*/("""
						"""),format.raw/*406.7*/("""let val = $(this).text().replace(/,/g,'');
						let numero = parseFloat(val) || 0;
						subtotal += parseFloat(numero);
					"""),format.raw/*409.6*/("""}"""),format.raw/*409.7*/(""");
					$("#resumenTot2").text(formatStandar(subtotal,'"""),_display_(/*410.54*/cantDec),format.raw/*410.61*/("""'));
					
					subtotal = 0;
					$(".total7").each(function() """),format.raw/*413.35*/("""{"""),format.raw/*413.36*/("""
						"""),format.raw/*414.7*/("""let val = $(this).text().replace(/,/g,'');
						let numero = parseFloat(val) || 0;
						subtotal += parseFloat(numero);
					"""),format.raw/*417.6*/("""}"""),format.raw/*417.7*/(""");
					$("#total7").text(formatStandar(subtotal,'"""),_display_(/*418.49*/cantDec),format.raw/*418.56*/("""'));
					
					subtotal = 0;
					$(".resumenTotal1").each(function() """),format.raw/*421.42*/("""{"""),format.raw/*421.43*/("""
						"""),format.raw/*422.7*/("""let val = $(this).text().replace(/,/g,'');
						let numero = parseFloat(val) || 0;
						subtotal += parseFloat(numero);
					"""),format.raw/*425.6*/("""}"""),format.raw/*425.7*/(""");
					$("#resumenTotal1").text(formatStandar(subtotal,'"""),_display_(/*426.56*/cantDec),format.raw/*426.63*/("""'));
					
					
					subtotal = 0;
					$(".resumenTot21").each(function() """),format.raw/*430.41*/("""{"""),format.raw/*430.42*/("""
						"""),format.raw/*431.7*/("""let val = $(this).text().replace(/,/g,'');
						let numero = parseFloat(val) || 0;
						subtotal += parseFloat(numero);
					"""),format.raw/*434.6*/("""}"""),format.raw/*434.7*/(""");
					$("#resumenTot21").text(formatStandar(subtotal,'2'));
					
					subtotal = 0;
					$(".resumenTot22").each(function() """),format.raw/*438.41*/("""{"""),format.raw/*438.42*/("""
						"""),format.raw/*439.7*/("""let val = $(this).text().replace(/,/g,'');
						let numero = parseFloat(val) || 0;
						subtotal += parseFloat(numero);
					"""),format.raw/*442.6*/("""}"""),format.raw/*442.7*/(""");
					$("#resumenTot22").text(formatStandar(subtotal,'"""),_display_(/*443.55*/cantDec),format.raw/*443.62*/("""'));
					
					subtotal = 0;
					$(".resumenTot23").each(function() """),format.raw/*446.41*/("""{"""),format.raw/*446.42*/("""
						"""),format.raw/*447.7*/("""let val = $(this).text().replace(/,/g,'');
						let numero = parseFloat(val) || 0;
						subtotal += parseFloat(numero);
					"""),format.raw/*450.6*/("""}"""),format.raw/*450.7*/(""");
					$("#resumenTot23").text(formatStandar(subtotal,'"""),_display_(/*451.55*/cantDec),format.raw/*451.62*/("""'));
					
					subtotal = 0;
					$(".resumenTot24").each(function() """),format.raw/*454.41*/("""{"""),format.raw/*454.42*/("""
						"""),format.raw/*455.7*/("""let val = $(this).text().replace(/,/g,'');
						let numero = parseFloat(val) || 0;
						subtotal += parseFloat(numero);
					"""),format.raw/*458.6*/("""}"""),format.raw/*458.7*/(""");
					$("#resumenTot24").text(formatStandar(subtotal,'"""),_display_(/*459.55*/cantDec),format.raw/*459.62*/("""'));
					
					
					
					
					let ajuste = 0;
					$(".ajustes").each(function() """),format.raw/*465.36*/("""{"""),format.raw/*465.37*/("""
						"""),format.raw/*466.7*/("""let val = $(this).text().replace(/,/g,'');
						ajuste += parseFloat(val);
					"""),format.raw/*468.6*/("""}"""),format.raw/*468.7*/(""");
					$("#resumenTotal12").text(formatStandar(subtotal+ajuste,'"""),_display_(/*469.64*/cantDec),format.raw/*469.71*/("""'));
					$("#totalTotal").val(subtotal+ajuste);
					
				    document.getElementById('mostrarmostrar').style.display="block"; 
						
			   """),format.raw/*474.7*/("""}"""),format.raw/*474.8*/(""");
			

		</script>
		

		
		
""")))}),format.raw/*482.2*/("""
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,detalleProformaPorServicio:List[List[String]],fechas:List[String],tasaCambio:List[Double],bodega:tables.BodegaEmpresa,proyecto:tables.Proyecto,cliente:tables.Cliente,cantDec:Long,listReferencias:List[tables.TipoReferencia],agrupadoPorServicio:List[List[String]],listaAjustes:List[List[String]],groupPorClaseServicioEquipo:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,detalleProformaPorServicio,fechas,tasaCambio,bodega,proyecto,cliente,cantDec,listReferencias,agrupadoPorServicio,listaAjustes,groupPorClaseServicioEquipo)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],List[String],List[Double],tables.BodegaEmpresa,tables.Proyecto,tables.Cliente,Long,List[tables.TipoReferencia],List[List[String]],List[List[String]],List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,detalleProformaPorServicio,fechas,tasaCambio,bodega,proyecto,cliente,cantDec,listReferencias,agrupadoPorServicio,listaAjustes,groupPorClaseServicioEquipo) => apply(mapDiccionario,mapPermiso,userMnu,detalleProformaPorServicio,fechas,tasaCambio,bodega,proyecto,cliente,cantDec,listReferencias,agrupadoPorServicio,listaAjustes,groupPorClaseServicioEquipo)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuOdo/reportProfProyDetalleOdo.scala.html
                  HASH: 29cdf4f460ef8d241a9d574baafe3a3df93e0831
                  MATRIX: 1207->1|1762->463|1793->468|1902->549|1931->550|1973->564|2046->610|2075->611|2107->616|2293->775|2308->781|2337->789|2416->841|2431->847|2459->854|2538->906|2553->912|2581->919|2652->963|2671->973|2699->980|2771->1025|2790->1035|2818->1042|2890->1087|2909->1097|2937->1104|3493->1633|3516->1647|3553->1663|3588->1671|4584->2639|4613->2640|4646->2646|4688->2660|4717->2661|4751->2668|4924->2813|4953->2814|4988->2822|5116->2923|5144->2924|5177->2930|5205->2931|5237->2936|5265->2937|5297->2942|5358->2975|5387->2976|5421->2983|5592->3126|5621->3127|5656->3135|5784->3236|5812->3237|5844->3242|5872->3243|5904->3248|5961->3277|5990->3278|6023->3284|6467->3702|6517->3736|6556->3737|6589->3743|6653->3780|6672->3790|6700->3797|6730->3800|6749->3810|6779->3819|6826->3836|6858->3841|7405->4360|7434->4361|7464->4363|7506->4378|7524->4386|7565->4388|7597->4393|7667->4441|7698->4444|7777->4496|8008->4705|8045->4714|9121->5762|9145->5776|9183->5792|9375->5955|9405->5956|9448->5970|9533->6026|9563->6027|9596->6031|9626->6032|9669->6046|9750->6098|9780->6099|9810->6100|9854->6115|9884->6116|10170->6374|10186->6380|10215->6387|10297->6441|10313->6447|10342->6454|10416->6500|10436->6510|10465->6517|10540->6564|10560->6574|10589->6581|10664->6628|10684->6638|10713->6645|10794->6698|10810->6704|10840->6712|11225->7069|11265->7070|11304->7080|11465->7213|11482->7220|11508->7224|11538->7225|11570->7229|11587->7236|11616->7243|11691->7273|11728->7282|11836->7362|11860->7376|11896->7390|11969->7435|11985->7441|12028->7462|12216->7622|12234->7630|12279->7653|12472->7818|12488->7824|12517->7831|12547->7832|12582->7839|12598->7845|12627->7852|12816->8013|12832->8019|12871->8036|13442->8579|13495->8615|13535->8616|13574->8626|13647->8671|13665->8679|13708->8700|13805->8769|13823->8777|13852->8784|13914->8814|13960->8832|14252->9096|14295->9122|14335->9123|14374->9133|14449->9180|14464->9185|14493->9192|14524->9195|14539->9200|14568->9207|14661->9272|14676->9277|14705->9284|14766->9313|14812->9331|16648->11140|16729->11204|16769->11205|16804->11212|16875->11255|16892->11262|16921->11269|16995->11315|17012->11322|17042->11330|17116->11376|17133->11383|17162->11390|17234->11434|17251->11441|17280->11448|17354->11494|17371->11501|17400->11508|17472->11552|17489->11559|17518->11566|17592->11612|17609->11619|17638->11626|17731->11691|17748->11698|17777->11705|17870->11770|17887->11777|17916->11784|18018->11858|18035->11865|18064->11872|18158->11938|18175->11945|18204->11952|18298->12018|18315->12025|18345->12033|18439->12099|18456->12106|18486->12114|18530->12127|18565->12134|21973->15515|22053->15578|22093->15579|22128->15586|22201->15631|22218->15638|22247->15645|22319->15689|22336->15696|22365->15703|22439->15749|22456->15756|22486->15764|22560->15810|22577->15817|22606->15824|22678->15868|22695->15875|22724->15882|22835->15965|22854->15974|22906->16004|22950->16020|22967->16027|22996->16034|23032->16042|23104->16086|23121->16093|23150->16100|23224->16146|23241->16153|23270->16160|23342->16204|23359->16211|23389->16219|23463->16265|23480->16272|23509->16279|23583->16325|23600->16332|23629->16339|23703->16385|23720->16392|23750->16400|23823->16445|23840->16452|23870->16460|23943->16505|23960->16512|23990->16520|24063->16565|24080->16572|24110->16580|24183->16625|24200->16632|24230->16640|24319->16701|24336->16708|24366->16716|24454->16776|24471->16783|24501->16791|24574->16836|24591->16843|24621->16851|24709->16911|24726->16918|24756->16926|24812->16951|24847->16958|26003->18085|26033->18086|26072->18097|26158->18154|26188->18155|26223->18162|26378->18289|26407->18290|26561->18415|26591->18416|26626->18423|26781->18550|26810->18551|26894->18607|26923->18614|27016->18678|27046->18679|27081->18686|27236->18813|27265->18814|27344->18865|27373->18872|27473->18943|27503->18944|27538->18951|27693->19078|27722->19079|27808->19137|27837->19144|27942->19220|27972->19221|28007->19228|28162->19355|28191->19356|28347->19483|28377->19484|28412->19491|28567->19618|28596->19619|28681->19676|28710->19683|28809->19753|28839->19754|28874->19761|29029->19888|29058->19889|29143->19946|29172->19953|29271->20023|29301->20024|29336->20031|29491->20158|29520->20159|29605->20216|29634->20223|29748->20308|29778->20309|29813->20316|29922->20397|29951->20398|30045->20464|30074->20471|30244->20613|30273->20614|30335->20645
                  LINES: 28->1|36->5|40->9|42->11|42->11|43->12|45->14|45->14|46->15|50->19|50->19|50->19|51->20|51->20|51->20|52->21|52->21|52->21|53->22|53->22|53->22|54->23|54->23|54->23|55->24|55->24|55->24|64->33|64->33|64->33|65->34|98->67|98->67|99->68|99->68|99->68|100->69|102->71|102->71|103->72|105->74|105->74|106->75|106->75|107->76|107->76|108->77|108->77|108->77|109->78|111->80|111->80|112->81|114->83|114->83|115->84|115->84|116->85|116->85|116->85|117->86|124->93|124->93|124->93|125->94|125->94|125->94|125->94|125->94|125->94|125->94|126->95|127->96|137->106|137->106|138->107|141->110|141->110|141->110|143->112|143->112|144->113|145->114|146->115|148->117|177->146|177->146|177->146|180->149|180->149|181->150|182->151|182->151|182->151|182->151|183->152|183->152|183->152|183->152|184->153|184->153|194->163|194->163|194->163|195->164|195->164|195->164|196->165|196->165|196->165|197->166|197->166|197->166|198->167|198->167|198->167|199->168|199->168|199->168|210->179|210->179|211->180|213->182|213->182|213->182|213->182|213->182|213->182|213->182|215->184|216->185|217->186|217->186|217->186|218->187|218->187|218->187|222->191|222->191|222->191|226->195|226->195|226->195|226->195|226->195|226->195|226->195|230->199|230->199|230->199|246->215|246->215|246->215|247->216|248->217|248->217|248->217|249->218|249->218|249->218|251->220|253->222|259->228|259->228|259->228|260->229|261->230|261->230|261->230|261->230|261->230|261->230|262->231|262->231|262->231|264->233|266->235|298->267|298->267|298->267|299->268|300->269|300->269|300->269|301->270|301->270|301->270|302->271|302->271|302->271|303->272|303->272|303->272|304->273|304->273|304->273|305->274|305->274|305->274|306->275|306->275|306->275|307->276|307->276|307->276|308->277|308->277|308->277|310->279|310->279|310->279|311->280|311->280|311->280|312->281|312->281|312->281|313->282|313->282|313->282|314->283|315->284|364->333|364->333|364->333|365->334|366->335|366->335|366->335|367->336|367->336|367->336|368->337|368->337|368->337|369->338|369->338|369->338|370->339|370->339|370->339|372->341|372->341|372->341|373->342|373->342|373->342|374->343|375->344|375->344|375->344|376->345|376->345|376->345|377->346|377->346|377->346|378->347|378->347|378->347|379->348|379->348|379->348|380->349|380->349|380->349|381->350|381->350|381->350|382->351|382->351|382->351|383->352|383->352|383->352|384->353|384->353|384->353|387->356|387->356|387->356|388->357|388->357|388->357|389->358|389->358|389->358|390->359|390->359|390->359|392->361|393->362|425->394|425->394|427->396|428->397|428->397|429->398|432->401|432->401|436->405|436->405|437->406|440->409|440->409|441->410|441->410|444->413|444->413|445->414|448->417|448->417|449->418|449->418|452->421|452->421|453->422|456->425|456->425|457->426|457->426|461->430|461->430|462->431|465->434|465->434|469->438|469->438|470->439|473->442|473->442|474->443|474->443|477->446|477->446|478->447|481->450|481->450|482->451|482->451|485->454|485->454|486->455|489->458|489->458|490->459|490->459|496->465|496->465|497->466|499->468|499->468|500->469|500->469|505->474|505->474|513->482
                  -- GENERATED --
              */
          