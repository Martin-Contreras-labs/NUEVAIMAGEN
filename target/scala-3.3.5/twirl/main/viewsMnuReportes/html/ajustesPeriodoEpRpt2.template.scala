
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

object ajustesPeriodoEpRpt2 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.AjustesEP],String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listAjustes: List[tables.AjustesEP], desde: String, hasta: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "REPORTE AJUSTES EP/PROFORMAS (POR PERIODOS) POR " + mapDiccionario.get("BODEGA") +"/PROYECTO", "(desde "+desde+" hasta "+hasta+")")),format.raw/*9.164*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-10 col-md-10 col-lg-10">
				
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR> 
							<th style="text-align:center;">SUCURSAL</th>
							<th style="text-align:center;">"""),_display_(/*17.40*/mapDiccionario/*17.54*/.get("BODEGA")),format.raw/*17.68*/("""/PROYECTO</th>
					        <th style="text-align:center; min-width:80px;">FECHA</th>
					        <th style="text-align:center;">TIPO AJUSTE</th>
					        <th style="text-align:center;">APLICAR SOBRE</th>
					        <th style="text-align:center;">MONEDA</th>
					        <th style="text-align:center;">VALOR</th>
					        <th style="text-align:center;">CONCEPTO</th>
					        <th style="text-align:center;">NOTAS</th>
							<TH style= "text-align: center;">PDF<br>ADJUNTO</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*29.8*/for(lista1 <- listAjustes) yield /*29.34*/{_display_(Seq[Any](format.raw/*29.35*/("""
							"""),format.raw/*30.8*/("""<TR>
								<td style="text-align:left;">"""),_display_(/*31.39*/lista1/*31.45*/.nameSucursal),format.raw/*31.58*/("""</td>
								<td style="text-align:left;">"""),_display_(/*32.39*/lista1/*32.45*/.bodegaEmpresa),format.raw/*32.59*/("""</td>
								<td style="text-align:center;">
									<div style="display:none">"""),_display_(/*34.37*/utilities/*34.46*/.Fechas.AAMMDD(lista1.fechaAjuste)),format.raw/*34.80*/("""</div>
									"""),_display_(/*35.11*/lista1/*35.17*/.fechaAjuste),format.raw/*35.29*/("""
								"""),format.raw/*36.9*/("""</td>
								<td style="text-align:center;">"""),_display_(/*37.41*/lista1/*37.47*/.tipoAjuste),format.raw/*37.58*/("""</td>
								<td style="text-align:center;">"""),_display_(/*38.41*/lista1/*38.47*/.tipoAjusteVenta),format.raw/*38.63*/("""</td>
								<td style="text-align:center;">"""),_display_(/*39.41*/lista1/*39.47*/.moneda),format.raw/*39.54*/("""</td>
								<td style="text-align:right;">"""),_display_(/*40.40*/lista1/*40.46*/.totalAjuste),format.raw/*40.58*/("""</td>
								<td style="text-align:left;">"""),_display_(/*41.39*/lista1/*41.45*/.concepto),format.raw/*41.54*/("""</td>
								<td style="text-align:left;">"""),_display_(/*42.39*/lista1/*42.45*/.observaciones),format.raw/*42.59*/("""</td>
								<td style= "text-align: center;vertical-align: middle;">
									"""),_display_(if(lista1.ajustePDF.equals("0"))/*44.43*/{_display_(Seq[Any](format.raw/*44.44*/("""
										"""),format.raw/*45.11*/("""<div id="actualizarPDF"""),_display_(/*45.34*/lista1/*45.40*/.id),format.raw/*45.43*/("""">
											--
										</div>
									""")))}else/*48.15*/{_display_(Seq[Any](format.raw/*48.16*/("""
										"""),format.raw/*49.11*/("""<a href="#" onclick="mostrarPDF('"""),_display_(/*49.45*/lista1/*49.51*/.ajustePDF),format.raw/*49.61*/("""')">
											<kbd style="background-color: #85C1E9">pdf</kbd>
										</a>
									""")))}),format.raw/*52.11*/("""
								"""),format.raw/*53.9*/("""</td>
							</TR>
			 			""")))}),format.raw/*55.9*/("""
					"""),format.raw/*56.6*/("""</tbody>
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="VOLVER" class="btn btn-success btn-sm rounded-pill btn-block" onclick="window.history.back()">
				</div>
			</div>
		</div>
	</div>
	
	<div id='muestraPDF' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>AJUSTE EP/PROFORMA</h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<div id="rutaPDF"></div>
	   				<div class='row'>
						<div class='col-sm-12' style='text-align:center'>
							<button type='button' class='btn btn-sm  btn-success' style='font-size: 10px;' data-dismiss='modal'>Listo</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
""")))}),format.raw/*90.2*/("""


"""),format.raw/*93.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*94.31*/("""{"""),format.raw/*94.32*/("""
		  """),format.raw/*95.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*95.36*/("""{"""),format.raw/*95.37*/("""
		    	"""),format.raw/*96.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[25, 50, 100, 200, -1], [25, 50, 100, 200, "All"]],
		    	"order": [[ 1, "asc" ],[ 2, "desc" ]],
		    	"language": """),format.raw/*99.20*/("""{"""),format.raw/*99.21*/("""
		        	"""),format.raw/*100.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*101.11*/("""}"""),format.raw/*101.12*/("""
		  """),format.raw/*102.5*/("""}"""),format.raw/*102.6*/(""" """),format.raw/*102.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*104.2*/("""}"""),format.raw/*104.3*/(""");
	
	const mostrarPDF = (nombrePDF) => """),format.raw/*106.36*/("""{"""),format.raw/*106.37*/("""
		  """),format.raw/*107.5*/("""document.getElementById('rutaPDF').innerHTML="<object data='/showPdf/"+nombrePDF+"' type='application/pdf' width='100%' height='720'></object>";
		  $('#muestraPDF').modal('show');
	"""),format.raw/*109.2*/("""}"""),format.raw/*109.3*/("""
	
	
"""),format.raw/*112.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listAjustes:List[tables.AjustesEP],desde:String,hasta:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listAjustes,desde,hasta)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.AjustesEP],String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listAjustes,desde,hasta) => apply(mapDiccionario,mapPermiso,userMnu,listAjustes,desde,hasta)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/ajustesPeriodoEpRpt2.scala.html
                  HASH: e68fa65f7c5757958bfb11e13962424315e9c7b2
                  MATRIX: 1057->1|1314->165|1347->173|1363->181|1402->183|1430->186|1498->234|1526->236|1602->287|1783->447|1813->450|2207->817|2230->831|2265->845|2831->1385|2873->1411|2912->1412|2947->1420|3017->1463|3032->1469|3066->1482|3137->1526|3152->1532|3187->1546|3296->1628|3314->1637|3369->1671|3413->1688|3428->1694|3461->1706|3497->1715|3570->1761|3585->1767|3617->1778|3690->1824|3705->1830|3742->1846|3815->1892|3830->1898|3858->1905|3930->1950|3945->1956|3978->1968|4049->2012|4064->2018|4094->2027|4165->2071|4180->2077|4215->2091|4355->2204|4394->2205|4433->2216|4483->2239|4498->2245|4522->2248|4589->2296|4628->2297|4667->2308|4728->2342|4743->2348|4774->2358|4895->2448|4931->2457|4988->2484|5021->2490|6181->3620|6211->3623|6301->3685|6330->3686|6362->3691|6421->3722|6450->3723|6485->3731|6673->3891|6702->3892|6743->3904|6850->3982|6880->3983|6913->3988|6942->3989|6971->3990|7072->4063|7101->4064|7170->4104|7200->4105|7233->4110|7443->4292|7472->4293|7505->4298
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|48->17|48->17|48->17|60->29|60->29|60->29|61->30|62->31|62->31|62->31|63->32|63->32|63->32|65->34|65->34|65->34|66->35|66->35|66->35|67->36|68->37|68->37|68->37|69->38|69->38|69->38|70->39|70->39|70->39|71->40|71->40|71->40|72->41|72->41|72->41|73->42|73->42|73->42|75->44|75->44|76->45|76->45|76->45|76->45|79->48|79->48|80->49|80->49|80->49|80->49|83->52|84->53|86->55|87->56|121->90|124->93|125->94|125->94|126->95|126->95|126->95|127->96|130->99|130->99|131->100|132->101|132->101|133->102|133->102|133->102|135->104|135->104|137->106|137->106|138->107|140->109|140->109|143->112
                  -- GENERATED --
              */
          