
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

object ajustesPeriodoEpRpt2Odo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.AjustesEpOdo],String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listAjustes: List[tables.AjustesEpOdo], desde: String, hasta: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "REPORTE AJUSTES EP/PROFORMAS SERVICIOS (POR PERIODOS) POR " + mapDiccionario.get("BODEGA") +"/PROYECTO", "(desde "+desde+" hasta "+hasta+")")),format.raw/*9.174*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-10 col-md-10 col-lg-10">
				
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR> 
							<th style="text-align:center;">SUCURSAL</th>
							<th style="text-align:center;">"""),_display_(/*17.40*/mapDiccionario/*17.54*/.get("BODEGA")),format.raw/*17.68*/("""/PROYECTO</th>
					        <th style="text-align:center; min-width:80px;">FECHA</th>
					        <th style="text-align:center;">TIPO AJUSTE</th>
					        <th style="text-align:center;">MONEDA</th>
					        <th style="text-align:center;">VALOR</th>
					        <th style="text-align:center;">CONCEPTO</th>
					        <th style="text-align:center;">NOTAS</th>
							<TH style= "text-align: center;">PDF<br>ADJUNTO</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*28.8*/for(lista1 <- listAjustes) yield /*28.34*/{_display_(Seq[Any](format.raw/*28.35*/("""
							"""),format.raw/*29.8*/("""<TR>
								<td style="text-align:left;">"""),_display_(/*30.39*/lista1/*30.45*/.nameSucursal),format.raw/*30.58*/("""</td>
								<td style="text-align:left;">"""),_display_(/*31.39*/lista1/*31.45*/.bodegaEmpresa),format.raw/*31.59*/("""</td>
								<td style="text-align:center;">
									<div style="display:none">"""),_display_(/*33.37*/utilities/*33.46*/.Fechas.AAMMDD(lista1.fechaAjuste)),format.raw/*33.80*/("""</div>
									"""),_display_(/*34.11*/lista1/*34.17*/.fechaAjuste),format.raw/*34.29*/("""
								"""),format.raw/*35.9*/("""</td>
								<td style="text-align:center;">"""),_display_(/*36.41*/lista1/*36.47*/.tipoAjuste),format.raw/*36.58*/("""</td>
								<td style="text-align:center;">"""),_display_(/*37.41*/lista1/*37.47*/.moneda),format.raw/*37.54*/("""</td>
								<td style="text-align:right;">"""),_display_(/*38.40*/lista1/*38.46*/.totalAjuste),format.raw/*38.58*/("""</td>
								<td style="text-align:left;">"""),_display_(/*39.39*/lista1/*39.45*/.concepto),format.raw/*39.54*/("""</td>
								<td style="text-align:left;">"""),_display_(/*40.39*/lista1/*40.45*/.observaciones),format.raw/*40.59*/("""</td>
								<td style= "text-align: center;vertical-align: middle;">
									"""),_display_(if(lista1.ajustePDF.equals("0"))/*42.43*/{_display_(Seq[Any](format.raw/*42.44*/("""
										"""),format.raw/*43.11*/("""<div id="actualizarPDF"""),_display_(/*43.34*/lista1/*43.40*/.id),format.raw/*43.43*/("""">
											--
										</div>
									""")))}else/*46.15*/{_display_(Seq[Any](format.raw/*46.16*/("""
										"""),format.raw/*47.11*/("""<a href="#" onclick="mostrarPDF('"""),_display_(/*47.45*/lista1/*47.51*/.ajustePDF),format.raw/*47.61*/("""')">
											<kbd style="background-color: #85C1E9">pdf</kbd>
										</a>
									""")))}),format.raw/*50.11*/("""
								"""),format.raw/*51.9*/("""</td>
							</TR>
			 			""")))}),format.raw/*53.9*/("""
					"""),format.raw/*54.6*/("""</tbody>
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
	
""")))}),format.raw/*88.2*/("""


"""),format.raw/*91.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*92.31*/("""{"""),format.raw/*92.32*/("""
		  """),format.raw/*93.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*93.36*/("""{"""),format.raw/*93.37*/("""
		    	"""),format.raw/*94.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[25, 50, 100, 200, -1], [25, 50, 100, 200, "All"]],
		    	"order": [[ 1, "asc" ],[ 2, "desc" ]],
		    	"language": """),format.raw/*97.20*/("""{"""),format.raw/*97.21*/("""
		        	"""),format.raw/*98.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*99.11*/("""}"""),format.raw/*99.12*/("""
		  """),format.raw/*100.5*/("""}"""),format.raw/*100.6*/(""" """),format.raw/*100.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*102.2*/("""}"""),format.raw/*102.3*/(""");
	
	const mostrarPDF = (nombrePDF) => """),format.raw/*104.36*/("""{"""),format.raw/*104.37*/("""
		  """),format.raw/*105.5*/("""document.getElementById('rutaPDF').innerHTML="<object data='/showPdf/"+nombrePDF+"' type='application/pdf' width='100%' height='720'></object>";
		  $('#muestraPDF').modal('show');
	"""),format.raw/*107.2*/("""}"""),format.raw/*107.3*/("""
	
	
"""),format.raw/*110.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listAjustes:List[tables.AjustesEpOdo],desde:String,hasta:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listAjustes,desde,hasta)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.AjustesEpOdo],String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listAjustes,desde,hasta) => apply(mapDiccionario,mapPermiso,userMnu,listAjustes,desde,hasta)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuOdo/ajustesPeriodoEpRpt2Odo.scala.html
                  HASH: ef7894f17c1703dce2615161489169393632d9d1
                  MATRIX: 1058->1|1318->168|1351->176|1367->184|1406->186|1434->189|1502->237|1530->239|1606->290|1797->460|1827->463|2221->830|2244->844|2279->858|2782->1335|2824->1361|2863->1362|2898->1370|2968->1413|2983->1419|3017->1432|3088->1476|3103->1482|3138->1496|3247->1578|3265->1587|3320->1621|3364->1638|3379->1644|3412->1656|3448->1665|3521->1711|3536->1717|3568->1728|3641->1774|3656->1780|3684->1787|3756->1832|3771->1838|3804->1850|3875->1894|3890->1900|3920->1909|3991->1953|4006->1959|4041->1973|4181->2086|4220->2087|4259->2098|4309->2121|4324->2127|4348->2130|4415->2178|4454->2179|4493->2190|4554->2224|4569->2230|4600->2240|4721->2330|4757->2339|4814->2366|4847->2372|6007->3502|6037->3505|6127->3567|6156->3568|6188->3573|6247->3604|6276->3605|6311->3613|6499->3773|6528->3774|6568->3786|6674->3864|6703->3865|6736->3870|6765->3871|6794->3872|6895->3945|6924->3946|6993->3986|7023->3987|7056->3992|7266->4174|7295->4175|7328->4180
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|48->17|48->17|48->17|59->28|59->28|59->28|60->29|61->30|61->30|61->30|62->31|62->31|62->31|64->33|64->33|64->33|65->34|65->34|65->34|66->35|67->36|67->36|67->36|68->37|68->37|68->37|69->38|69->38|69->38|70->39|70->39|70->39|71->40|71->40|71->40|73->42|73->42|74->43|74->43|74->43|74->43|77->46|77->46|78->47|78->47|78->47|78->47|81->50|82->51|84->53|85->54|119->88|122->91|123->92|123->92|124->93|124->93|124->93|125->94|128->97|128->97|129->98|130->99|130->99|131->100|131->100|131->100|133->102|133->102|135->104|135->104|136->105|138->107|138->107|141->110
                  -- GENERATED --
              */
          