
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

object reporteGerencialKG extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template9[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],List[List[String]],List[List[String]],String,String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
datosActual: List[List[String]], datosAnterior: List[List[String]], datosAnteriorAnterior: List[List[String]], anioActual: String, anioAnterior: String, anioAnteriorAnterior: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "TONELADAS MOVIDAS POR AÑO","(Corresponde a toneladas movidas desde y hacia "+mapDiccionario("BODEGAS")+" externas/clientes)")),format.raw/*9.158*/("""

		"""),format.raw/*11.3*/("""<div class="noprint">
			<table class="table table-sm table-condensed table-fluid">
				<tr>
					<td>
						NOTA: 
						<br>Si traslada desde """),_display_(/*16.30*/mapDiccionario("Bodega")),format.raw/*16.54*/(""" """),format.raw/*16.55*/("""interna a """),_display_(/*16.66*/mapDiccionario("Bodega")),format.raw/*16.90*/(""" """),format.raw/*16.91*/("""cliente, 
						queda registrado como una SALIDA en la sucursal de """),_display_(/*17.59*/mapDiccionario("Bodega")),format.raw/*17.83*/(""" """),format.raw/*17.84*/("""cliente.
						<br>Si el traslado es a la inversa queda registrado como una ENTRADA en la sucursal de """),_display_(/*18.95*/mapDiccionario("Bodega")),format.raw/*18.119*/(""" """),format.raw/*18.120*/("""cliente.
					</td>
				</tr>
				<tr>
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
				</tr>
			</table>
		</div>
		
		<div class="table-responsive">
			<table id="anioActual" class="table table-sm table-hover table-bordered table-condensed table-fluid">
				<thead>
					<tr>
						<th colspan="50"><h5>TONELADAS MOVIDOS ANO """),_display_(/*45.51*/anioActual),format.raw/*45.61*/("""</h5></th>
					</tr>
					<tr>
						<th></th>
						<th colspan="2" style="text-align:center">ENERO</th>
						<th colspan="2" style="text-align:center">FEBRERO</th>
						<th colspan="2" style="text-align:center">MARZO</th>
						<th colspan="2" style="text-align:center">ABRIL</th>
						<th colspan="2" style="text-align:center">MAYO</th>
						<th colspan="2" style="text-align:center">JUNIO</th>
						<th colspan="2" style="text-align:center">JULIO</th>
						<th colspan="2" style="text-align:center">AGOSTO</th>
						<th colspan="2" style="text-align:center">SEPTIEMBRE</th>
						<th colspan="2" style="text-align:center">OCTUBRE</th>
						<th colspan="2" style="text-align:center">NOVIEMBRE</th>
						<th colspan="2" style="text-align:center">DICIEMBRE</th>
						<th colspan="2" style="text-align:center">TOTALES</th>
					</tr>
					<tr>
						<th>FAMILIA/GRUPO</th>
						<th style="text-align:center">Salida</th>
						<th style="text-align:center">Entrada</th>
						<th style="text-align:center">Salida</th>
						<th style="text-align:center">Entrada</th>
						<th style="text-align:center">Salida</th>
						<th style="text-align:center">Entrada</th>
						<th style="text-align:center">Salida</th>
						<th style="text-align:center">Entrada</th>
						<th style="text-align:center">Salida</th>
						<th style="text-align:center">Entrada</th>
						<th style="text-align:center">Salida</th>
						<th style="text-align:center">Entrada</th>
						<th style="text-align:center">Salida</th>
						<th style="text-align:center">Entrada</th>
						<th style="text-align:center">Salida</th>
						<th style="text-align:center">Entrada</th>
						<th style="text-align:center">Salida</th>
						<th style="text-align:center">Entrada</th>
						<th style="text-align:center">Salida</th>
						<th style="text-align:center">Entrada</th>
						<th style="text-align:center">Salida</th>
						<th style="text-align:center">Entrada</th>
						<th style="text-align:center">Salida</th>
						<th style="text-align:center">Entrada</th>
						<th style="text-align:center">Salida</th>
						<th style="text-align:center">Entrada</th>
					</tr>
				</thead>
				<tbody>
					"""),_display_(/*94.7*/for(lista <- datosActual) yield /*94.32*/{_display_(Seq[Any](format.raw/*94.33*/("""
						"""),format.raw/*95.7*/("""<tr>
							"""),_display_(/*96.9*/for(lista2 <-lista) yield /*96.28*/{_display_(Seq[Any](format.raw/*96.29*/("""
								"""),format.raw/*97.9*/("""<td style="text-align:right">"""),_display_(/*97.39*/lista2),format.raw/*97.45*/("""</td>
							""")))}),format.raw/*98.9*/("""
						"""),format.raw/*99.7*/("""</tr>
					""")))}),format.raw/*100.7*/("""
					"""),format.raw/*101.6*/("""<tr>
						<td style="background-color:#F8E6E0">TOTALES</td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
					</tr>
				</tbody>
			</table>
			<br>
			<table id="anioAnterior" class="table table-sm table-hover table-bordered table-condensed table-fluid">
				<thead>
					<tr>
						<th colspan="50"><h5>TONELADAS MOVIDOS ANO """),_display_(/*136.51*/anioAnterior),format.raw/*136.63*/("""</h5></th>
					</tr>
					<tr>
						<th></th>
						<th colspan="2" style="text-align:center">ENERO</th>
						<th colspan="2" style="text-align:center">FEBRERO</th>
						<th colspan="2" style="text-align:center">MARZO</th>
						<th colspan="2" style="text-align:center">ABRIL</th>
						<th colspan="2" style="text-align:center">MAYO</th>
						<th colspan="2" style="text-align:center">JUNIO</th>
						<th colspan="2" style="text-align:center">JULIO</th>
						<th colspan="2" style="text-align:center">AGOSTO</th>
						<th colspan="2" style="text-align:center">SEPTIEMBRE</th>
						<th colspan="2" style="text-align:center">OCTUBRE</th>
						<th colspan="2" style="text-align:center">NOVIEMBRE</th>
						<th colspan="2" style="text-align:center">DICIEMBRE</th>
						<th colspan="2" style="text-align:center">TOTALES</th>
					</tr>
					<tr>
						<th>FAMILIA/GRUPO</th>
						<th style="text-align:center">Salida</th>
						<th style="text-align:center">Entrada</th>
						<th style="text-align:center">Salida</th>
						<th style="text-align:center">Entrada</th>
						<th style="text-align:center">Salida</th>
						<th style="text-align:center">Entrada</th>
						<th style="text-align:center">Salida</th>
						<th style="text-align:center">Entrada</th>
						<th style="text-align:center">Salida</th>
						<th style="text-align:center">Entrada</th>
						<th style="text-align:center">Salida</th>
						<th style="text-align:center">Entrada</th>
						<th style="text-align:center">Salida</th>
						<th style="text-align:center">Entrada</th>
						<th style="text-align:center">Salida</th>
						<th style="text-align:center">Entrada</th>
						<th style="text-align:center">Salida</th>
						<th style="text-align:center">Entrada</th>
						<th style="text-align:center">Salida</th>
						<th style="text-align:center">Entrada</th>
						<th style="text-align:center">Salida</th>
						<th style="text-align:center">Entrada</th>
						<th style="text-align:center">Salida</th>
						<th style="text-align:center">Entrada</th>
						<th style="text-align:center">Salida</th>
						<th style="text-align:center">Entrada</th>
					</tr>
				</thead>
				<tbody>
					"""),_display_(/*185.7*/for(lista <- datosAnterior) yield /*185.34*/{_display_(Seq[Any](format.raw/*185.35*/("""
						"""),format.raw/*186.7*/("""<tr>
						"""),_display_(/*187.8*/for(lista2 <-lista) yield /*187.27*/{_display_(Seq[Any](format.raw/*187.28*/("""
							"""),format.raw/*188.8*/("""<td style="text-align:right">"""),_display_(/*188.38*/lista2),format.raw/*188.44*/("""</td>
						""")))}),format.raw/*189.8*/("""
						"""),format.raw/*190.7*/("""</tr>
					""")))}),format.raw/*191.7*/("""
					"""),format.raw/*192.6*/("""<tr>
						<td style="background-color:#F8E6E0">TOTALES</td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

<br>
			<table id="anioAnteriorAnterior" class="table table-sm table-hover table-bordered table-condensed table-fluid">
				<thead>
					<tr>
						<th colspan="50"><h5>TONELADAS MOVIDOS ANO """),_display_(/*230.51*/anioAnteriorAnterior),format.raw/*230.71*/("""</h5></th>
					</tr>
					<tr>
						<th></th>
						<th colspan="2" style="text-align:center">ENERO</th>
						<th colspan="2" style="text-align:center">FEBRERO</th>
						<th colspan="2" style="text-align:center">MARZO</th>
						<th colspan="2" style="text-align:center">ABRIL</th>
						<th colspan="2" style="text-align:center">MAYO</th>
						<th colspan="2" style="text-align:center">JUNIO</th>
						<th colspan="2" style="text-align:center">JULIO</th>
						<th colspan="2" style="text-align:center">AGOSTO</th>
						<th colspan="2" style="text-align:center">SEPTIEMBRE</th>
						<th colspan="2" style="text-align:center">OCTUBRE</th>
						<th colspan="2" style="text-align:center">NOVIEMBRE</th>
						<th colspan="2" style="text-align:center">DICIEMBRE</th>
						<th colspan="2" style="text-align:center">TOTALES</th>
					</tr>
					<tr>
						<th>FAMILIA/GRUPO</th>
						<th style="text-align:center">Salida</th>
						<th style="text-align:center">Entrada</th>
						<th style="text-align:center">Salida</th>
						<th style="text-align:center">Entrada</th>
						<th style="text-align:center">Salida</th>
						<th style="text-align:center">Entrada</th>
						<th style="text-align:center">Salida</th>
						<th style="text-align:center">Entrada</th>
						<th style="text-align:center">Salida</th>
						<th style="text-align:center">Entrada</th>
						<th style="text-align:center">Salida</th>
						<th style="text-align:center">Entrada</th>
						<th style="text-align:center">Salida</th>
						<th style="text-align:center">Entrada</th>
						<th style="text-align:center">Salida</th>
						<th style="text-align:center">Entrada</th>
						<th style="text-align:center">Salida</th>
						<th style="text-align:center">Entrada</th>
						<th style="text-align:center">Salida</th>
						<th style="text-align:center">Entrada</th>
						<th style="text-align:center">Salida</th>
						<th style="text-align:center">Entrada</th>
						<th style="text-align:center">Salida</th>
						<th style="text-align:center">Entrada</th>
						<th style="text-align:center">Salida</th>
						<th style="text-align:center">Entrada</th>
					</tr>
				</thead>
				<tbody>
					"""),_display_(/*279.7*/for(lista <- datosAnteriorAnterior) yield /*279.42*/{_display_(Seq[Any](format.raw/*279.43*/("""
						"""),format.raw/*280.7*/("""<tr>
						"""),_display_(/*281.8*/for(lista2 <-lista) yield /*281.27*/{_display_(Seq[Any](format.raw/*281.28*/("""
							"""),format.raw/*282.8*/("""<td style="text-align:right">"""),_display_(/*282.38*/lista2),format.raw/*282.44*/("""</td>
						""")))}),format.raw/*283.8*/("""
						"""),format.raw/*284.7*/("""</tr>
					""")))}),format.raw/*285.7*/("""
					"""),format.raw/*286.6*/("""<tr>
						<td style="background-color:#F8E6E0">TOTALES</td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
						<td style="text-align:right;background-color:#F8E6E0"></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

	
	<form id="excel" class="formulario" method="post" action="/reporteGerencialKGExcel/">
		<input type="hidden" name="anioActual" value=""""),_display_(/*322.50*/anioActual),format.raw/*322.60*/("""">
		<input type="hidden" name="anioAnterior" value=""""),_display_(/*323.52*/anioAnterior),format.raw/*323.64*/("""">
		<input type="hidden" name="anioAnteriorAnterior" value=""""),_display_(/*324.60*/anioAnteriorAnterior),format.raw/*324.80*/("""">
	</form>


""")))}),format.raw/*328.2*/("""


"""),format.raw/*331.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*333.31*/("""{"""),format.raw/*333.32*/("""
		
	  """),format.raw/*335.4*/("""var tbActual = document.getElementById('anioActual');
	  for (var i=3;i<tbActual.rows.length;i++)"""),format.raw/*336.44*/("""{"""),format.raw/*336.45*/("""
		  	"""),format.raw/*337.6*/("""tbActual.rows[i].cells[0].style.textAlign="left";
	  """),format.raw/*338.4*/("""}"""),format.raw/*338.5*/("""
	  """),format.raw/*339.4*/("""var numero = new DecimalFormat("#,##0.00");
	  for (var i=1;i<tbActual.rows[2].cells.length;i++)"""),format.raw/*340.53*/("""{"""),format.raw/*340.54*/("""
		  """),format.raw/*341.5*/("""var total=0;
		  for (var j=3;j<tbActual.rows.length-2;j++)"""),format.raw/*342.47*/("""{"""),format.raw/*342.48*/("""
				"""),format.raw/*343.5*/("""var celdas = tbActual.rows[j].getElementsByTagName('td');
				total = parseFloat(total)+parseFloat(numero.formatBack(celdas[i].innerHTML));
		  """),format.raw/*345.5*/("""}"""),format.raw/*345.6*/("""
		  """),format.raw/*346.5*/("""var celdas = tbActual.rows[tbActual.rows.length-1].getElementsByTagName('td');
		  celdas[i].innerHTML = formatStandar2(total);
	  """),format.raw/*348.4*/("""}"""),format.raw/*348.5*/("""
	  
	  """),format.raw/*350.4*/("""var tbAnterior = document.getElementById('anioAnterior');
	  for (var i=3;i<tbAnterior.rows.length;i++)"""),format.raw/*351.46*/("""{"""),format.raw/*351.47*/("""
		  """),format.raw/*352.5*/("""tbAnterior.rows[i].cells[0].style.textAlign="left";
	  """),format.raw/*353.4*/("""}"""),format.raw/*353.5*/("""
	  """),format.raw/*354.4*/("""for (var i=1;i<tbAnterior.rows[2].cells.length;i++)"""),format.raw/*354.55*/("""{"""),format.raw/*354.56*/("""
		  """),format.raw/*355.5*/("""var total=0;
		  for (var j=3;j<tbAnterior.rows.length-2;j++)"""),format.raw/*356.49*/("""{"""),format.raw/*356.50*/("""
				"""),format.raw/*357.5*/("""var celdas = tbAnterior.rows[j].getElementsByTagName('td');
				total = parseFloat(total)+parseFloat(numero.formatBack(celdas[i].innerHTML));
		  """),format.raw/*359.5*/("""}"""),format.raw/*359.6*/("""
		  """),format.raw/*360.5*/("""var celdas = tbAnterior.rows[tbActual.rows.length-1].getElementsByTagName('td');
		  celdas[i].innerHTML = formatStandar2(total);
	  """),format.raw/*362.4*/("""}"""),format.raw/*362.5*/("""
	  
	  """),format.raw/*364.4*/("""var tbAnteriorAnterior = document.getElementById('anioAnteriorAnterior');
	  for (var i=3;i<tbAnteriorAnterior.rows.length;i++)"""),format.raw/*365.54*/("""{"""),format.raw/*365.55*/("""
		  """),format.raw/*366.5*/("""tbAnteriorAnterior.rows[i].cells[0].style.textAlign="left";
	  """),format.raw/*367.4*/("""}"""),format.raw/*367.5*/("""
	  """),format.raw/*368.4*/("""for (var i=1;i<tbAnteriorAnterior.rows[2].cells.length;i++)"""),format.raw/*368.63*/("""{"""),format.raw/*368.64*/("""
		  """),format.raw/*369.5*/("""var total=0;
		  for (var j=3;j<tbAnteriorAnterior.rows.length-2;j++)"""),format.raw/*370.57*/("""{"""),format.raw/*370.58*/("""
				"""),format.raw/*371.5*/("""var celdas = tbAnteriorAnterior.rows[j].getElementsByTagName('td');
				total = parseFloat(total)+parseFloat(numero.formatBack(celdas[i].innerHTML));
		  """),format.raw/*373.5*/("""}"""),format.raw/*373.6*/("""
		  """),format.raw/*374.5*/("""var celdas = tbAnteriorAnterior.rows[tbActual.rows.length-1].getElementsByTagName('td');
		  celdas[i].innerHTML = formatStandar2(total);
	  """),format.raw/*376.4*/("""}"""),format.raw/*376.5*/("""

	  """),format.raw/*378.4*/("""document.getElementById('mostrarmostrar').style.display="block";

	"""),format.raw/*380.2*/("""}"""),format.raw/*380.3*/(""");
	
	
	
</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,datosActual:List[List[String]],datosAnterior:List[List[String]],datosAnteriorAnterior:List[List[String]],anioActual:String,anioAnterior:String,anioAnteriorAnterior:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,datosActual,datosAnterior,datosAnteriorAnterior,anioActual,anioAnterior,anioAnteriorAnterior)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],List[List[String]],List[List[String]],String,String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,datosActual,datosAnterior,datosAnteriorAnterior,anioActual,anioAnterior,anioAnteriorAnterior) => apply(mapDiccionario,mapPermiso,userMnu,datosActual,datosAnterior,datosAnteriorAnterior,anioActual,anioAnterior,anioAnteriorAnterior)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reporteGerencialKG.scala.html
                  HASH: ffdb4533785b4e8e4edf23e9bb7fc630ecdf0033
                  MATRIX: 1096->1|1469->281|1502->289|1518->297|1557->299|1586->303|1654->351|1682->353|1758->404|1933->558|1964->562|2136->707|2181->731|2210->732|2248->743|2293->767|2322->768|2417->836|2462->860|2491->861|2621->964|2667->988|2697->989|3524->1789|3555->1799|5768->3986|5809->4011|5848->4012|5882->4019|5921->4032|5956->4051|5995->4052|6031->4061|6088->4091|6115->4097|6159->4111|6193->4118|6236->4130|6270->4136|8298->6136|8332->6148|10546->8335|10590->8362|10630->8363|10665->8370|10704->8382|10740->8401|10780->8402|10816->8410|10874->8440|10902->8446|10946->8459|10981->8466|11024->8478|11058->8484|13109->10507|13151->10527|15365->12714|15417->12749|15457->12750|15492->12757|15531->12769|15567->12788|15607->12789|15643->12797|15701->12827|15729->12833|15773->12846|15808->12853|15851->12865|15885->12871|17882->14840|17914->14850|17996->14904|18030->14916|18120->14978|18162->14998|18208->15013|18239->15016|18331->15079|18361->15080|18396->15087|18522->15184|18552->15185|18586->15191|18667->15244|18696->15245|18728->15249|18853->15345|18883->15346|18916->15351|19004->15410|19034->15411|19067->15416|19239->15560|19268->15561|19301->15566|19460->15697|19489->15698|19525->15706|19657->15809|19687->15810|19720->15815|19803->15870|19832->15871|19864->15875|19944->15926|19974->15927|20007->15932|20097->15993|20127->15994|20160->15999|20334->16145|20363->16146|20396->16151|20557->16284|20586->16285|20622->16293|20778->16420|20808->16421|20841->16426|20932->16489|20961->16490|20993->16494|21081->16553|21111->16554|21144->16559|21242->16628|21272->16629|21305->16634|21487->16788|21516->16789|21549->16794|21718->16935|21747->16936|21780->16941|21875->17008|21904->17009
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|42->11|47->16|47->16|47->16|47->16|47->16|47->16|48->17|48->17|48->17|49->18|49->18|49->18|76->45|76->45|125->94|125->94|125->94|126->95|127->96|127->96|127->96|128->97|128->97|128->97|129->98|130->99|131->100|132->101|167->136|167->136|216->185|216->185|216->185|217->186|218->187|218->187|218->187|219->188|219->188|219->188|220->189|221->190|222->191|223->192|261->230|261->230|310->279|310->279|310->279|311->280|312->281|312->281|312->281|313->282|313->282|313->282|314->283|315->284|316->285|317->286|353->322|353->322|354->323|354->323|355->324|355->324|359->328|362->331|364->333|364->333|366->335|367->336|367->336|368->337|369->338|369->338|370->339|371->340|371->340|372->341|373->342|373->342|374->343|376->345|376->345|377->346|379->348|379->348|381->350|382->351|382->351|383->352|384->353|384->353|385->354|385->354|385->354|386->355|387->356|387->356|388->357|390->359|390->359|391->360|393->362|393->362|395->364|396->365|396->365|397->366|398->367|398->367|399->368|399->368|399->368|400->369|401->370|401->370|402->371|404->373|404->373|405->374|407->376|407->376|409->378|411->380|411->380
                  -- GENERATED --
              */
          