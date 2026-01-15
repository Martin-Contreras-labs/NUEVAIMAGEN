
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

object reportFacturaProyectoDetalleHALL extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template21[Map[String,String],Map[String,String],utilities.UserMnu,String,List[List[String]],tables.BodegaEmpresa,String,String,String,Double,Double,Double,tables.Cliente,tables.Proyecto,String,List[List[String]],Long,String,Long,List[List[String]],Double,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu, idTipoUsuario: String,
lista: List[List[String]], bodega: tables.BodegaEmpresa, esVenta: String, fechaDesde: String, fechaHasta: String,
usd: Double, eur: Double, uf: Double, cliente: tables.Cliente, proyecto: tables.Proyecto, oc: String,
datos: List[List[String]], nroDecimales: Long, mon: String,
cantDec: Long, groupPorClaseServicioEquipo: List[List[String]], tasaIva: Double):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*6.1*/("""

"""),_display_(/*8.2*/main("")/*8.10*/ {_display_(Seq[Any](format.raw/*8.12*/("""

	"""),_display_(/*10.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*10.51*/("""

	"""),_display_(/*12.3*/modalEquipoDescripcion()),format.raw/*12.27*/("""
	"""),_display_(/*13.3*/modalVerCotizacion()),format.raw/*13.23*/("""

	"""),format.raw/*15.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*16.4*/barraTitulo(mapDiccionario, "DETALLE EP/FACTURA PROFORMA SIMPLE " + mapDiccionario.get("BODEGA") + "/PROYECTO: " + bodega.getNombre(),
			"PERIODO: desde " + utilities.Fechas.DDMMAA(fechaDesde) +" --- hasta: " +  utilities.Fechas.DDMMAA(fechaHasta))),format.raw/*17.115*/("""

				"""),format.raw/*19.5*/("""<div class="noprint">
					<table class="table table-sm table-condensed table-fluid">
						<tr>
							<td style="width: 25%">
"""),format.raw/*23.38*/("""
"""),format.raw/*24.49*/("""
"""),format.raw/*25.80*/("""
"""),format.raw/*26.22*/("""
"""),format.raw/*27.60*/("""
"""),format.raw/*28.44*/("""
"""),format.raw/*29.54*/("""
"""),format.raw/*30.19*/("""
							"""),format.raw/*31.8*/("""</td>
							<td>
								<div style="text-align: center;">
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
							<td style="width: 25%">
								HACER """),_display_(/*48.16*/mapDiccionario/*48.30*/.get("PROFORMA")),format.raw/*48.46*/(""":
								<button id="btnArr" type="button" class="btn btn-mini btn-info" tabindex="-1"
									onclick=" this.disabled = true;
										if(parseFloat(neto) > 0)"""),format.raw/*51.35*/("""{"""),format.raw/*51.36*/("""
											"""),format.raw/*52.12*/("""document.getElementById('formProforma').submit();
										"""),format.raw/*53.11*/("""}"""),format.raw/*53.12*/("""else"""),format.raw/*53.16*/("""{"""),format.raw/*53.17*/("""
											"""),format.raw/*54.12*/("""alertify.alert('No hay valores a emitir', function () """),format.raw/*54.66*/("""{"""),format.raw/*54.67*/("""}"""),format.raw/*54.68*/(""");
										"""),format.raw/*55.11*/("""}"""),format.raw/*55.12*/("""">
									TODO
								</button>
							</td>
						</tr>
					</table>
				</div>

				<table class="table table-sm table-bordered table-condensed table-fluid">
					<TR>
						<td style= "text-align: left; vertical-align:top; width: 44%;">
							<table class="table table-sm table-hover table-bordered table-condensed table-fluid">
								"""),_display_(if(!cliente.rut.equals(null))/*67.39*/{_display_(Seq[Any](format.raw/*67.40*/("""
									"""),format.raw/*68.10*/("""<tr style= "background-color: #F2F5A9;">
										<th style="text-align:left;">CLIENTE</th>
										<th style="text-align:left;">"""),_display_(/*70.41*/cliente/*70.48*/.rut),format.raw/*70.52*/(""" """),format.raw/*70.53*/("""-- """),_display_(/*70.57*/cliente/*70.64*/.nombre),format.raw/*70.71*/("""</th>
									</tr>
								""")))} else {null} ),format.raw/*72.10*/("""
								"""),format.raw/*73.9*/("""<tr style= "background-color: #F2F5A9;">
									<th style="text-align:left;">"""),_display_(/*74.40*/mapDiccionario/*74.54*/.get("BODEGA")),format.raw/*74.68*/("""</th>
									<TH style="text-align:left;">"""),_display_(/*75.40*/bodega/*75.46*/.nombre.toUpperCase()),format.raw/*75.67*/("""</TH>
								</tr>
								<tr style= "background-color: #F2F5A9;">
									<th style="text-align:left;">PROYECTO</th>
									<th style="text-align:left;">"""),_display_(/*79.40*/proyecto/*79.48*/.nickName.toUpperCase()),format.raw/*79.71*/("""</TH>
								</tr>
								<tr style= "background-color: #F2F5A9;">
									<th style="text-align:left;">PERIODO</th>
									<th style="text-align:left;">desde """),_display_(/*83.46*/utilities/*83.55*/.Fechas.DDMMAA(fechaDesde)),format.raw/*83.81*/(""" """),format.raw/*83.82*/("""hasta """),_display_(/*83.89*/utilities/*83.98*/.Fechas.DDMMAA(fechaHasta)),format.raw/*83.124*/("""</TH>
								</tr>
								<tr style= "background-color: #F2F5A9;">
									<th style="text-align:left;">COMERCIAL</th>
									<th style="text-align:left;">"""),_display_(/*87.40*/bodega/*87.46*/.comercial),format.raw/*87.56*/("""</TH>
								</tr>
							</table>

						</td>
						<td>&nbsp;</td>
						<td colspan="7" style= "text-align: left;  width: 34%;">
							<table class="table table-sm table-hover table-bordered table-condensed table-fluid">
								<thead style="background-color: #dddddd">
									<th colspan="2">TOTALES</th>
								</thead>
								<tbody>
									<tr>
										<td style="text-align:left;">ALQUILERES:</td>
										<td style="text-align:right;" id="ALQUILERES">445555</td>
									</tr>
									<tr>
										<td style="text-align:left;">VENTAS:</td>
										<td style="text-align:right;" id="VENTAS">232323</td>
									</tr>
									<tr>
										<td style="text-align:left;">SERVICIOS:</td>
										<td style="text-align:right;" id="SERVICIOS">232323</td>
									</tr>
								</tbody>
								<tfoot style="background-color: #dddddd">
									<tr>
										<td style="text-align:left;">NETO:</td>
										<td style="text-align:right;" id="NETO">445555</td>
									</tr>
									<tr>
										<td style="text-align:left;">"""),_display_(/*118.41*/mapDiccionario/*118.55*/.get("IVA")),format.raw/*118.66*/(""" """),_display_(/*118.68*/(tasaIva*100)),format.raw/*118.81*/("""%:</td>
										<td style="text-align:right;" id="IVA">232323</td>
									</tr>
									<tr>
										<th style="text-align:left;">TOTAL:</th>
										<th style="text-align:right;" id="TOTAL">232323</th>
									</tr>
								</tfoot>
							</table>
						</td>
					</TR>
				</table>

				"""),_display_(if(lista.size() > 0)/*131.26*/ {_display_(Seq[Any](format.raw/*131.28*/("""
					"""),format.raw/*132.6*/("""<div class="table-responsive">
						<h5>ALQUILERES:</h5>
						<table id="tablaPrincipalARR" class="table table-sm table-hover table-bordered table-condensed table-fluid">
							<thead style="background-color: #dddddd">
							"""),_display_(/*136.9*/for((nivel1, index1) <- lista.zipWithIndex) yield /*136.52*/ {_display_(Seq[Any](format.raw/*136.54*/("""
								"""),_display_(if(index1 < 3)/*137.24*/ {_display_(Seq[Any](format.raw/*137.26*/("""
									"""),format.raw/*138.10*/("""<TR>
									"""),_display_(/*139.11*/for((nivel2,index2) <- nivel1.zipWithIndex) yield /*139.54*/ {_display_(Seq[Any](format.raw/*139.56*/("""
										"""),_display_(if(index2==12 || index2==14)/*140.40*/{_display_(Seq[Any](format.raw/*140.41*/("""
											"""),format.raw/*141.12*/("""<td style="text-align: center; min-width: 80px">"""),_display_(/*141.61*/nivel2),format.raw/*141.67*/("""</td>
										""")))}else/*142.16*/{_display_(Seq[Any](format.raw/*142.17*/("""
											"""),format.raw/*143.12*/("""<td style="text-align: center">"""),_display_(/*143.44*/nivel2),format.raw/*143.50*/("""</td>
										""")))}),format.raw/*144.12*/("""
									""")))}),format.raw/*145.11*/("""
									"""),format.raw/*146.10*/("""</TR>
								""")))} else {null} ),format.raw/*147.10*/("""
							""")))}),format.raw/*148.9*/("""
							"""),format.raw/*149.8*/("""</thead>
							<tbody>
							"""),_display_(/*151.9*/for((nivel1, index1) <- lista.zipWithIndex) yield /*151.52*/ {_display_(Seq[Any](format.raw/*151.54*/("""
								"""),_display_(if(index1 > 2)/*152.24*/ {_display_(Seq[Any](format.raw/*152.26*/("""
									"""),format.raw/*153.10*/("""<TR>
									"""),_display_(/*154.11*/for((nivel2, index2) <- nivel1.zipWithIndex) yield /*154.55*/ {_display_(Seq[Any](format.raw/*154.57*/("""
										"""),_display_(if(index2 == 1)/*155.27*/ {_display_(Seq[Any](format.raw/*155.29*/("""
											"""),_display_(if(nivel2.equals("0") || nivel2.equals(""))/*156.56*/ {_display_(Seq[Any](format.raw/*156.58*/("""
												"""),format.raw/*157.13*/("""<td style="text-align: center;">--</td>
											""")))}else/*158.19*/{_display_(Seq[Any](format.raw/*158.20*/("""
												"""),format.raw/*159.13*/("""<td style="text-align: center;">"""),_display_(/*159.46*/nivel2),format.raw/*159.52*/("""</td>
											""")))}),format.raw/*160.13*/("""
										""")))}else if(index2 < 4)/*161.31*/ {_display_(Seq[Any](format.raw/*161.33*/("""
										"""),_display_(if(index2 == 2 || index2 == 3)/*162.42*/ {_display_(Seq[Any](format.raw/*162.44*/("""
											"""),format.raw/*163.12*/("""<td>"""),_display_(/*163.17*/nivel2),format.raw/*163.23*/("""</td>
										""")))}else/*164.18*/{_display_(Seq[Any](format.raw/*164.19*/("""
											"""),format.raw/*165.12*/("""<td>"""),_display_(/*165.17*/nivel2),format.raw/*165.23*/("""</td>
										""")))}),format.raw/*166.12*/("""
										""")))}else/*167.16*/{_display_(Seq[Any](format.raw/*167.17*/("""
										"""),format.raw/*168.11*/("""<td style="text-align: right;">"""),_display_(/*168.43*/nivel2),format.raw/*168.49*/("""</td>
										""")))}),format.raw/*169.12*/("""
									""")))}),format.raw/*170.11*/("""
									"""),format.raw/*171.10*/("""</TR>
								""")))} else {null} ),format.raw/*172.10*/("""
							""")))}),format.raw/*173.9*/("""
							"""),format.raw/*174.8*/("""</tbody>
						</table>
					</div>
				""")))} else {null} ),format.raw/*177.6*/("""

				"""),_display_(if(datos.size()>0)/*179.24*/{_display_(Seq[Any](format.raw/*179.25*/("""
					"""),format.raw/*180.6*/("""<div class="table-responsive">
						<h5>VENTAS:</h5>
						<table id="tablaPrincipalVTA" class="table table-sm table-hover table-bordered table-condensed table-fluid">
							<thead style="background-color: #dddddd">
								<TR>
									<th>GRUPO</th>
									<th>COTI</th>
									<th>CODIGO</th>
									<th>EQUIPO</th>
									<th>MON</th>
									<th>PRECIO<br>UNITARIO</th>
									<th>UN</th>
									<th>CANT</th>
									<th>TOTAL MON<br>ORIGEN</th>
									<th>TOTAL EN<br>"""),_display_(/*194.27*/mon),format.raw/*194.30*/("""</th>
									<th>Nro GUIA</th>
									<th style="min-width: 80px">FECHA</th>
								</TR>
							</thead>
							<tbody>
							"""),_display_(/*200.9*/for((nivel1,index1) <- datos.zipWithIndex) yield /*200.51*/ {_display_(Seq[Any](format.raw/*200.53*/("""
								"""),format.raw/*201.9*/("""<TR>
								"""),_display_(/*202.10*/for((nivel2,index2) <- nivel1.drop(1).zipWithIndex) yield /*202.61*/ {_display_(Seq[Any](format.raw/*202.63*/("""
									"""),_display_(if(index2<4)/*203.23*/{_display_(Seq[Any](format.raw/*203.24*/("""
										"""),format.raw/*204.11*/("""<td style="text-align: left;">"""),_display_(/*204.42*/nivel2),format.raw/*204.48*/("""</td>
									""")))}else if(index2==4 || index2==6 || index2==10 || index2==11)/*205.70*/{_display_(Seq[Any](format.raw/*205.71*/("""
									"""),format.raw/*206.10*/("""<td style="text-align: center;">"""),_display_(/*206.43*/nivel2),format.raw/*206.49*/("""</td>
									""")))}else if(index2==7)/*207.29*/{_display_(Seq[Any](format.raw/*207.30*/("""
									"""),format.raw/*208.10*/("""<td class="cantidades" style="text-align: right;">"""),_display_(/*208.61*/nivel2),format.raw/*208.67*/("""</td>
									""")))}else if(index2==9 )/*209.30*/{_display_(Seq[Any](format.raw/*209.31*/("""
									"""),format.raw/*210.10*/("""<td class="totales" style="text-align: right;">"""),_display_(/*210.58*/nivel2),format.raw/*210.64*/("""</td>
									""")))}else/*211.15*/{_display_(Seq[Any](format.raw/*211.16*/("""
									"""),format.raw/*212.10*/("""<td style="text-align: right;">"""),_display_(/*212.42*/nivel2),format.raw/*212.48*/("""</td>
									""")))}),format.raw/*213.11*/("""
								""")))}),format.raw/*214.10*/("""
								"""),format.raw/*215.9*/("""</TR>
							""")))}),format.raw/*216.9*/("""
							"""),format.raw/*217.8*/("""</tbody>
							<tfoot>
								<TR>
									<th style="background-color: #eeeeee">TOTALES</th>
									<th style="background-color: #eeeeee"></th>
									<th style="background-color: #eeeeee"></th>
									<th style="background-color: #eeeeee"></th>
									<th style="background-color: #eeeeee"></th>
									<th style="background-color: #eeeeee"></th>
									<th style="background-color: #eeeeee"></th>
									<th style="text-align: right;background-color: #eeeeee""><div id="cant"></div></th>
									<th style="background-color: #eeeeee"></th>
									<th style="text-align: right;background-color: #eeeeee""><div id="tot"></div></th>
									<th style="background-color: #eeeeee"></th>
									<th style="background-color: #eeeeee"></th>
								</TR>
							</tfoot>

						</table>
					</div>
				""")))} else {null} ),format.raw/*237.6*/("""

				"""),_display_(if(groupPorClaseServicioEquipo.size()>0)/*239.46*/{_display_(Seq[Any](format.raw/*239.47*/("""
					"""),format.raw/*240.6*/("""<div class="table-responsive">
						<h5>SERVICIOS:</h5>
						<table id="tablaPrincipalSERV" class="table table-sm table-hover table-bordered table-condensed table-fluid" >
							<thead class="header">
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
								"""),_display_(/*262.10*/for((inicial,index) <- groupPorClaseServicioEquipo.zipWithIndex) yield /*262.74*/{_display_(Seq[Any](format.raw/*262.75*/("""
									"""),format.raw/*263.10*/("""<tr>
										<td style="text-align: left;">"""),_display_(/*264.42*/inicial/*264.49*/.get(0)),format.raw/*264.56*/("""</td>
										<td style="text-align: center;">"""),_display_(/*265.44*/inicial/*265.51*/.get(12)),format.raw/*265.59*/("""</td>
										<td style="text-align: center;">"""),_display_(/*266.44*/inicial/*266.51*/.get(1)),format.raw/*266.58*/("""</td>
										<td style="text-align: left;">"""),_display_(/*267.42*/inicial/*267.49*/.get(2)),format.raw/*267.56*/("""</td>
										<td style="text-align: center;">"""),_display_(/*268.44*/inicial/*268.51*/.get(3)),format.raw/*268.58*/("""</td>
										<td style="text-align: left;">"""),_display_(/*269.42*/inicial/*269.49*/.get(4)),format.raw/*269.56*/("""</td>
										<td style="text-align: center;">"""),_display_(/*270.44*/inicial/*270.51*/.get(5)),format.raw/*270.58*/("""</td>
										<td style="text-align: right;" class="resumenTot1">"""),_display_(/*271.63*/inicial/*271.70*/.get(6)),format.raw/*271.77*/("""</td>
										<td style="text-align: right;" class="resumenTot2">"""),_display_(/*272.63*/inicial/*272.70*/.get(7)),format.raw/*272.77*/("""</td>

										<td style="text-align: right;" class="resumenTot21">"""),_display_(/*274.64*/inicial/*274.71*/.get(8)),format.raw/*274.78*/("""</td>
										<td style="text-align: right;" class="resumenTot22">"""),_display_(/*275.64*/inicial/*275.71*/.get(9)),format.raw/*275.78*/("""</td>
										<td style="text-align: right;" class="resumenTot23">"""),_display_(/*276.64*/inicial/*276.71*/.get(10)),format.raw/*276.79*/("""</td>
										<td style="text-align: right;" class="resumenTot24">"""),_display_(/*277.64*/inicial/*277.71*/.get(11)),format.raw/*277.79*/("""</td>
								""")))}),format.raw/*278.10*/("""
								"""),format.raw/*279.9*/("""<TR>
									<th style="background-color: #eeeeee" colspan="7">TOTALES NETO</th>
									<th style="background-color: #eeeeee"><div align="right" id="resumenTot1"></div></th>
									<th style="background-color: #eeeeee"><div align="right" id="resumenTot2"></div></th>
									<th style="background-color: #eeeeee"><div align="right" id="resumenTot21"></div></th>
									<th style="background-color: #eeeeee"><div align="right" id="resumenTot22"></div></th>
									<th style="background-color: #eeeeee"><div align="right" id="resumenTot23"></div></th>
									<th style="background-color: #eeeeee"><div align="right" id="resumenTot24"></div></th>

								</tr>
							</tbody>
						</table>
					</div>
				""")))} else {null} ),format.raw/*292.6*/("""
			"""),format.raw/*293.4*/("""</div>


	<form id="excel" class="formulario" method="post" action="/reportFacturaProyectoDetalleHAllExcel/">
		<input type="hidden" name="id_bodega" value=""""),_display_(/*297.49*/bodega/*297.55*/.getId()),format.raw/*297.63*/("""">
		<input type="hidden" name="fechaDesde" value=""""),_display_(/*298.50*/fechaDesde),format.raw/*298.60*/("""">
		<input type="hidden" name="fechaHasta" value=""""),_display_(/*299.50*/fechaHasta),format.raw/*299.60*/("""">
		<input type="hidden" name="esVenta" value=""""),_display_(/*300.47*/esVenta),format.raw/*300.54*/("""">
		<input type="hidden" name="uf" value=""""),_display_(/*301.42*/uf),format.raw/*301.44*/("""">
		<input type="hidden" name="usd" value=""""),_display_(/*302.43*/usd),format.raw/*302.46*/("""">
		<input type="hidden" name="eur" value=""""),_display_(/*303.43*/eur),format.raw/*303.46*/("""">
	</form>

	<form id="formProforma" class="formulario" method="post" action="/reportFacturaProyectoDetalleHAllProforma/">
		<input type="hidden" name="id_bodega" value=""""),_display_(/*307.49*/bodega/*307.55*/.getId()),format.raw/*307.63*/("""">
		<input type="hidden" name="fechaDesde" value=""""),_display_(/*308.50*/fechaDesde),format.raw/*308.60*/("""">
		<input type="hidden" name="fechaHasta" value=""""),_display_(/*309.50*/fechaHasta),format.raw/*309.60*/("""">
		<input type="hidden" name="esVenta" value=""""),_display_(/*310.47*/esVenta),format.raw/*310.54*/("""">
		<input type="hidden" name="uf" value=""""),_display_(/*311.42*/uf),format.raw/*311.44*/("""">
		<input type="hidden" name="usd" value=""""),_display_(/*312.43*/usd),format.raw/*312.46*/("""">
		<input type="hidden" name="eur" value=""""),_display_(/*313.43*/eur),format.raw/*313.46*/("""">
		<input type="hidden" name="tipo" value="TODO">
		<input type="hidden" id="profneto" name="neto">
		<input type="hidden" id="profiva" name="iva">
		<input type="hidden" id="proftotal" name="total">

		<input type="hidden" id="profAlq" name="alq">
		<input type="hidden" id="profVta" name="vta">
		<input type="hidden" id="profServ" name="serv">

	</form>


""")))}),format.raw/*326.2*/("""


"""),format.raw/*329.1*/("""<script type="text/javascript">
	var numero = new DecimalFormat("#,##0.00");
	let totArr = 0;
	let totVta = 0;
	let totServ = 0;
	let neto = 0;
	$(document).ready(function() """),format.raw/*335.31*/("""{"""),format.raw/*335.32*/("""
		"""),format.raw/*336.3*/("""var tabla = document.getElementById("tablaPrincipalARR");
		for(var k=0;k<tabla.rows[0].cells.length;k++)"""),format.raw/*337.48*/("""{"""),format.raw/*337.49*/("""
			"""),format.raw/*338.4*/("""tabla.rows[tabla.rows.length-1].cells[k].style.backgroundColor="#dddddd";
		"""),format.raw/*339.3*/("""}"""),format.raw/*339.4*/("""
		"""),_display_(if(lista.size()>0)/*340.22*/{_display_(Seq[Any](format.raw/*340.23*/("""
			"""),format.raw/*341.4*/("""let val = """"),_display_(/*341.16*/lista/*341.21*/.get(lista.size()-1).get(10)),format.raw/*341.49*/("""";
			val = val.replace(/,/g,'');
			totArr = val;
		""")))} else {null} ),format.raw/*344.4*/("""

		"""),_display_(if(datos.size()>0)/*346.22*/{_display_(Seq[Any](format.raw/*346.23*/("""
			"""),format.raw/*347.4*/("""let suma = 0;
			document.querySelectorAll('td.cantidades').forEach(td => """),format.raw/*348.61*/("""{"""),format.raw/*348.62*/("""
				"""),format.raw/*349.5*/("""const valor = parseFloat(td.textContent.replace(/,/g, ''));
				if (!isNaN(valor)) """),format.raw/*350.24*/("""{"""),format.raw/*350.25*/("""
					"""),format.raw/*351.6*/("""suma += valor;
				"""),format.raw/*352.5*/("""}"""),format.raw/*352.6*/("""
			"""),format.raw/*353.4*/("""}"""),format.raw/*353.5*/(""");
			$("#cant").text(formatStandar(suma,2));

			suma = 0;
			document.querySelectorAll('td.totales').forEach(td => """),format.raw/*357.58*/("""{"""),format.raw/*357.59*/("""
				"""),format.raw/*358.5*/("""const valor = parseFloat(td.textContent.replace(/,/g, ''));
				if (!isNaN(valor)) """),format.raw/*359.24*/("""{"""),format.raw/*359.25*/("""
					"""),format.raw/*360.6*/("""suma += valor;
				"""),format.raw/*361.5*/("""}"""),format.raw/*361.6*/("""
			"""),format.raw/*362.4*/("""}"""),format.raw/*362.5*/(""");
			$("#tot").text(formatStandar(suma,""""),_display_(/*363.40*/nroDecimales),format.raw/*363.52*/(""""));
			totVta = suma;
		""")))} else {null} ),format.raw/*365.4*/("""

		"""),_display_(if(groupPorClaseServicioEquipo.size()>0)/*367.44*/{_display_(Seq[Any](format.raw/*367.45*/("""
			"""),format.raw/*368.4*/("""let subtotal = 0;
			$(".resumenTot1").each(function() """),format.raw/*369.38*/("""{"""),format.raw/*369.39*/("""
				"""),format.raw/*370.5*/("""let val = $(this).text().replace(/,/g,'')  || 0;
				let numero = parseFloat(val) || 0;
				subtotal += parseFloat(numero);
			"""),format.raw/*373.4*/("""}"""),format.raw/*373.5*/(""");
			$("#resumenTot1").text(formatStandar(subtotal,'2'));

			subtotal = 0;
			$(".resumenTot2").each(function() """),format.raw/*377.38*/("""{"""),format.raw/*377.39*/("""
				"""),format.raw/*378.5*/("""let val = $(this).text().replace(/,/g,'');
				let numero = parseFloat(val) || 0;
				subtotal += parseFloat(numero);
			"""),format.raw/*381.4*/("""}"""),format.raw/*381.5*/(""");
			$("#resumenTot2").text(formatStandar(subtotal,'"""),_display_(/*382.52*/cantDec),format.raw/*382.59*/("""'));

			subtotal = 0;
			$(".total7").each(function() """),format.raw/*385.33*/("""{"""),format.raw/*385.34*/("""
				"""),format.raw/*386.5*/("""let val = $(this).text().replace(/,/g,'');
				let numero = parseFloat(val) || 0;
				subtotal += parseFloat(numero);
			"""),format.raw/*389.4*/("""}"""),format.raw/*389.5*/(""");
			$("#total7").text(formatStandar(subtotal,'"""),_display_(/*390.47*/cantDec),format.raw/*390.54*/("""'));

			subtotal = 0;
			$(".resumenTot21").each(function() """),format.raw/*393.39*/("""{"""),format.raw/*393.40*/("""
				"""),format.raw/*394.5*/("""let val = $(this).text().replace(/,/g,'');
				let numero = parseFloat(val) || 0;
				subtotal += parseFloat(numero);
			"""),format.raw/*397.4*/("""}"""),format.raw/*397.5*/(""");
			$("#resumenTot21").text(formatStandar(subtotal,'2'));

			subtotal = 0;
			$(".resumenTot22").each(function() """),format.raw/*401.39*/("""{"""),format.raw/*401.40*/("""
				"""),format.raw/*402.5*/("""let val = $(this).text().replace(/,/g,'');
				let numero = parseFloat(val) || 0;
				subtotal += parseFloat(numero);
			"""),format.raw/*405.4*/("""}"""),format.raw/*405.5*/(""");
			$("#resumenTot22").text(formatStandar(subtotal,'"""),_display_(/*406.53*/cantDec),format.raw/*406.60*/("""'));

			subtotal = 0;
			$(".resumenTot23").each(function() """),format.raw/*409.39*/("""{"""),format.raw/*409.40*/("""
				"""),format.raw/*410.5*/("""let val = $(this).text().replace(/,/g,'');
				let numero = parseFloat(val) || 0;
				subtotal += parseFloat(numero);
			"""),format.raw/*413.4*/("""}"""),format.raw/*413.5*/(""");
			$("#resumenTot23").text(formatStandar(subtotal,'"""),_display_(/*414.53*/cantDec),format.raw/*414.60*/("""'));

			subtotal = 0;
			$(".resumenTot24").each(function() """),format.raw/*417.39*/("""{"""),format.raw/*417.40*/("""
				"""),format.raw/*418.5*/("""let val = $(this).text().replace(/,/g,'');
				let numero = parseFloat(val) || 0;
				subtotal += parseFloat(numero);
			"""),format.raw/*421.4*/("""}"""),format.raw/*421.5*/(""");
			$("#resumenTot24").text(formatStandar(subtotal,'"""),_display_(/*422.53*/cantDec),format.raw/*422.60*/("""'));
			totServ = subtotal;
		""")))} else {null} ),format.raw/*424.4*/("""

		"""),format.raw/*426.3*/("""$("#ALQUILERES").text(formatStandar(totArr,""""),_display_(/*426.48*/cantDec),format.raw/*426.55*/(""""));
		$("#VENTAS").text(formatStandar(totVta,""""),_display_(/*427.44*/cantDec),format.raw/*427.51*/(""""));
		$("#SERVICIOS").text(formatStandar(totServ,""""),_display_(/*428.48*/cantDec),format.raw/*428.55*/(""""));
		neto = parseFloat(totArr) + parseFloat(totVta) + parseFloat(totServ);

		let iva = neto * parseFloat(""""),_display_(/*431.33*/tasaIva),format.raw/*431.40*/("""");
		let total = parseFloat(neto) + parseFloat(iva);
		$("#NETO").text(formatStandar(neto,""""),_display_(/*433.40*/cantDec),format.raw/*433.47*/(""""));
		$("#IVA").text(formatStandar(iva,""""),_display_(/*434.38*/cantDec),format.raw/*434.45*/(""""));
		$("#TOTAL").text(formatStandar(total,""""),_display_(/*435.42*/cantDec),format.raw/*435.49*/(""""));

		$("#profneto").val(formatStandar(neto,""""),_display_(/*437.43*/cantDec),format.raw/*437.50*/(""""));
		$("#profiva").val(formatStandar(iva,""""),_display_(/*438.41*/cantDec),format.raw/*438.48*/(""""));
		$("#proftotal").val(formatStandar(total,""""),_display_(/*439.45*/cantDec),format.raw/*439.52*/(""""));

		$("#profAlq").val(formatStandar(totArr,""""),_display_(/*441.44*/cantDec),format.raw/*441.51*/(""""));
		$("#profVta").val(formatStandar(totVta,""""),_display_(/*442.44*/cantDec),format.raw/*442.51*/(""""));
		$("#profServ").val(formatStandar(totServ,""""),_display_(/*443.46*/cantDec),format.raw/*443.53*/(""""));

		document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*446.2*/("""}"""),format.raw/*446.3*/(""");



	
</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,idTipoUsuario:String,lista:List[List[String]],bodega:tables.BodegaEmpresa,esVenta:String,fechaDesde:String,fechaHasta:String,usd:Double,eur:Double,uf:Double,cliente:tables.Cliente,proyecto:tables.Proyecto,oc:String,datos:List[List[String]],nroDecimales:Long,mon:String,cantDec:Long,groupPorClaseServicioEquipo:List[List[String]],tasaIva:Double): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,idTipoUsuario,lista,bodega,esVenta,fechaDesde,fechaHasta,usd,eur,uf,cliente,proyecto,oc,datos,nroDecimales,mon,cantDec,groupPorClaseServicioEquipo,tasaIva)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,String,List[List[String]],tables.BodegaEmpresa,String,String,String,Double,Double,Double,tables.Cliente,tables.Proyecto,String,List[List[String]],Long,String,Long,List[List[String]],Double) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,idTipoUsuario,lista,bodega,esVenta,fechaDesde,fechaHasta,usd,eur,uf,cliente,proyecto,oc,datos,nroDecimales,mon,cantDec,groupPorClaseServicioEquipo,tasaIva) => apply(mapDiccionario,mapPermiso,userMnu,idTipoUsuario,lista,bodega,esVenta,fechaDesde,fechaHasta,usd,eur,uf,cliente,proyecto,oc,datos,nroDecimales,mon,cantDec,groupPorClaseServicioEquipo,tasaIva)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reportFacturaProyectoDetalleHALL.scala.html
                  HASH: ac9463e876025abc9872b41571985d1db53ae6fd
                  MATRIX: 1222->1|1792->478|1820->481|1836->489|1875->491|1905->495|1974->543|2004->547|2049->571|2078->574|2119->594|2149->597|2226->648|2497->897|2530->903|2686->1068|2715->1117|2744->1197|2773->1219|2802->1279|2831->1323|2860->1377|2889->1396|2924->1404|3553->2006|3576->2020|3613->2036|3804->2199|3833->2200|3873->2212|3961->2272|3990->2273|4022->2277|4051->2278|4091->2290|4173->2344|4202->2345|4231->2346|4272->2359|4301->2360|4703->2735|4742->2736|4780->2746|4940->2879|4956->2886|4981->2890|5010->2891|5041->2895|5057->2902|5085->2909|5159->2939|5195->2948|5302->3028|5325->3042|5360->3056|5432->3101|5447->3107|5489->3128|5676->3288|5693->3296|5737->3319|5929->3484|5947->3493|5994->3519|6023->3520|6057->3527|6075->3536|6123->3562|6311->3723|6326->3729|6357->3739|7444->4798|7468->4812|7501->4823|7531->4825|7566->4838|7916->5160|7957->5162|7991->5168|8248->5398|8308->5441|8349->5443|8401->5467|8442->5469|8481->5479|8524->5494|8584->5537|8625->5539|8693->5579|8733->5580|8774->5592|8851->5641|8879->5647|8920->5668|8960->5669|9001->5681|9061->5713|9089->5719|9138->5736|9181->5747|9220->5757|9280->5772|9320->5781|9356->5789|9415->5821|9475->5864|9516->5866|9568->5890|9609->5892|9648->5902|9691->5917|9752->5961|9793->5963|9848->5990|9889->5992|9973->6048|10014->6050|10056->6063|10132->6121|10172->6122|10214->6135|10275->6168|10303->6174|10353->6192|10404->6223|10445->6225|10515->6267|10556->6269|10597->6281|10630->6286|10658->6292|10699->6315|10739->6316|10780->6328|10813->6333|10841->6339|10890->6356|10926->6372|10966->6373|11006->6384|11066->6416|11094->6422|11143->6439|11186->6450|11225->6460|11285->6475|11325->6484|11361->6492|11446->6533|11499->6558|11539->6559|11573->6565|12096->7060|12121->7063|12282->7197|12341->7239|12382->7241|12419->7250|12461->7264|12529->7315|12570->7317|12621->7340|12661->7341|12701->7352|12760->7383|12788->7389|12883->7464|12923->7465|12962->7475|13023->7508|13051->7514|13105->7548|13145->7549|13184->7559|13263->7610|13291->7616|13346->7651|13386->7652|13425->7662|13501->7710|13529->7716|13569->7736|13609->7737|13648->7747|13708->7779|13736->7785|13784->7801|13826->7811|13863->7820|13908->7834|13944->7842|14810->8664|14885->8711|14925->8712|14959->8718|16536->10267|16617->10331|16657->10332|16696->10342|16770->10388|16787->10395|16816->10402|16893->10451|16910->10458|16940->10466|17017->10515|17034->10522|17063->10529|17138->10576|17155->10583|17184->10590|17261->10639|17278->10646|17307->10653|17382->10700|17399->10707|17428->10714|17505->10763|17522->10770|17551->10777|17647->10845|17664->10852|17693->10859|17789->10927|17806->10934|17835->10941|17933->11011|17950->11018|17979->11025|18076->11094|18093->11101|18122->11108|18219->11177|18236->11184|18266->11192|18363->11261|18380->11268|18410->11276|18457->11291|18494->11300|19257->12019|19289->12023|19475->12181|19491->12187|19521->12195|19601->12247|19633->12257|19713->12309|19745->12319|19822->12368|19851->12375|19923->12419|19947->12421|20020->12466|20045->12469|20118->12514|20143->12517|20343->12689|20359->12695|20389->12703|20469->12755|20501->12765|20581->12817|20613->12827|20690->12876|20719->12883|20791->12927|20815->12929|20888->12974|20913->12977|20986->13022|21011->13025|21404->13387|21435->13390|21638->13564|21668->13565|21699->13568|21833->13673|21863->13674|21895->13678|21999->13754|22028->13755|22078->13777|22118->13778|22150->13782|22190->13794|22205->13799|22255->13827|22353->13881|22404->13904|22444->13905|22476->13909|22579->13983|22609->13984|22642->13989|22754->14072|22784->14073|22818->14079|22865->14098|22894->14099|22926->14103|22955->14104|23101->14221|23131->14222|23164->14227|23276->14310|23306->14311|23340->14317|23387->14336|23416->14337|23448->14341|23477->14342|23547->14384|23581->14396|23651->14422|23724->14467|23764->14468|23796->14472|23880->14527|23910->14528|23943->14533|24098->14660|24127->14661|24270->14775|24300->14776|24333->14781|24482->14902|24511->14903|24593->14957|24622->14964|24706->15019|24736->15020|24769->15025|24918->15146|24947->15147|25024->15196|25053->15203|25143->15264|25173->15265|25206->15270|25355->15391|25384->15392|25529->15508|25559->15509|25592->15514|25741->15635|25770->15636|25853->15691|25882->15698|25972->15759|26002->15760|26035->15765|26184->15886|26213->15887|26296->15942|26325->15949|26415->16010|26445->16011|26478->16016|26627->16137|26656->16138|26739->16193|26768->16200|26843->16231|26875->16235|26948->16280|26977->16287|27053->16335|27082->16342|27162->16394|27191->16401|27329->16511|27358->16518|27479->16611|27508->16618|27578->16660|27607->16667|27681->16713|27710->16720|27786->16768|27815->16775|27888->16820|27917->16827|27994->16876|28023->16883|28100->16932|28129->16939|28205->16987|28234->16994|28312->17044|28341->17051|28443->17125|28472->17126
                  LINES: 28->1|37->6|39->8|39->8|39->8|41->10|41->10|43->12|43->12|44->13|44->13|46->15|47->16|48->17|50->19|54->23|55->24|56->25|57->26|58->27|59->28|60->29|61->30|62->31|79->48|79->48|79->48|82->51|82->51|83->52|84->53|84->53|84->53|84->53|85->54|85->54|85->54|85->54|86->55|86->55|98->67|98->67|99->68|101->70|101->70|101->70|101->70|101->70|101->70|101->70|103->72|104->73|105->74|105->74|105->74|106->75|106->75|106->75|110->79|110->79|110->79|114->83|114->83|114->83|114->83|114->83|114->83|114->83|118->87|118->87|118->87|149->118|149->118|149->118|149->118|149->118|162->131|162->131|163->132|167->136|167->136|167->136|168->137|168->137|169->138|170->139|170->139|170->139|171->140|171->140|172->141|172->141|172->141|173->142|173->142|174->143|174->143|174->143|175->144|176->145|177->146|178->147|179->148|180->149|182->151|182->151|182->151|183->152|183->152|184->153|185->154|185->154|185->154|186->155|186->155|187->156|187->156|188->157|189->158|189->158|190->159|190->159|190->159|191->160|192->161|192->161|193->162|193->162|194->163|194->163|194->163|195->164|195->164|196->165|196->165|196->165|197->166|198->167|198->167|199->168|199->168|199->168|200->169|201->170|202->171|203->172|204->173|205->174|208->177|210->179|210->179|211->180|225->194|225->194|231->200|231->200|231->200|232->201|233->202|233->202|233->202|234->203|234->203|235->204|235->204|235->204|236->205|236->205|237->206|237->206|237->206|238->207|238->207|239->208|239->208|239->208|240->209|240->209|241->210|241->210|241->210|242->211|242->211|243->212|243->212|243->212|244->213|245->214|246->215|247->216|248->217|268->237|270->239|270->239|271->240|293->262|293->262|293->262|294->263|295->264|295->264|295->264|296->265|296->265|296->265|297->266|297->266|297->266|298->267|298->267|298->267|299->268|299->268|299->268|300->269|300->269|300->269|301->270|301->270|301->270|302->271|302->271|302->271|303->272|303->272|303->272|305->274|305->274|305->274|306->275|306->275|306->275|307->276|307->276|307->276|308->277|308->277|308->277|309->278|310->279|323->292|324->293|328->297|328->297|328->297|329->298|329->298|330->299|330->299|331->300|331->300|332->301|332->301|333->302|333->302|334->303|334->303|338->307|338->307|338->307|339->308|339->308|340->309|340->309|341->310|341->310|342->311|342->311|343->312|343->312|344->313|344->313|357->326|360->329|366->335|366->335|367->336|368->337|368->337|369->338|370->339|370->339|371->340|371->340|372->341|372->341|372->341|372->341|375->344|377->346|377->346|378->347|379->348|379->348|380->349|381->350|381->350|382->351|383->352|383->352|384->353|384->353|388->357|388->357|389->358|390->359|390->359|391->360|392->361|392->361|393->362|393->362|394->363|394->363|396->365|398->367|398->367|399->368|400->369|400->369|401->370|404->373|404->373|408->377|408->377|409->378|412->381|412->381|413->382|413->382|416->385|416->385|417->386|420->389|420->389|421->390|421->390|424->393|424->393|425->394|428->397|428->397|432->401|432->401|433->402|436->405|436->405|437->406|437->406|440->409|440->409|441->410|444->413|444->413|445->414|445->414|448->417|448->417|449->418|452->421|452->421|453->422|453->422|455->424|457->426|457->426|457->426|458->427|458->427|459->428|459->428|462->431|462->431|464->433|464->433|465->434|465->434|466->435|466->435|468->437|468->437|469->438|469->438|470->439|470->439|472->441|472->441|473->442|473->442|474->443|474->443|477->446|477->446
                  -- GENERATED --
              */
          