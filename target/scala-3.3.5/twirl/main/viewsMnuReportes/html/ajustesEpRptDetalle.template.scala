
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

object ajustesEpRptDetalle extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.AjustesEP],tables.BodegaEmpresa,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listAjustes: List[tables.AjustesEP], bodegaEmpresa: tables.BodegaEmpresa):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "REPORTE AJUSTES A EP/PROFORMAS A: "+bodegaEmpresa.nombre,"")),format.raw/*9.93*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-10 col-md-10 col-lg-10">
				
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR> 
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
						"""),_display_(/*27.8*/for(lista1 <- listAjustes) yield /*27.34*/{_display_(Seq[Any](format.raw/*27.35*/("""
							"""),format.raw/*28.8*/("""<TR>
								<td style="text-align:center;">
									<div style="display:none">"""),_display_(/*30.37*/utilities/*30.46*/.Fechas.AAMMDD(lista1.fechaAjuste)),format.raw/*30.80*/("""</div>
									"""),_display_(/*31.11*/lista1/*31.17*/.fechaAjuste),format.raw/*31.29*/("""
								"""),format.raw/*32.9*/("""</td>
								<td style="text-align:center;">"""),_display_(/*33.41*/lista1/*33.47*/.tipoAjuste),format.raw/*33.58*/("""</td>
								<td style="text-align:center;">"""),_display_(/*34.41*/lista1/*34.47*/.tipoAjusteVenta),format.raw/*34.63*/("""</td>
								<td style="text-align:center;">"""),_display_(/*35.41*/lista1/*35.47*/.moneda),format.raw/*35.54*/("""</td>
								<td style="text-align:right;">"""),_display_(/*36.40*/lista1/*36.46*/.totalAjuste),format.raw/*36.58*/("""</td>
								<td style="text-align:left;">"""),_display_(/*37.39*/lista1/*37.45*/.concepto),format.raw/*37.54*/("""</td>
								<td style="text-align:left;">"""),_display_(/*38.39*/lista1/*38.45*/.observaciones),format.raw/*38.59*/("""</td>
								<td style= "text-align: center;vertical-align: middle;">
									"""),_display_(if(lista1.ajustePDF.equals("0"))/*40.43*/{_display_(Seq[Any](format.raw/*40.44*/("""
										"""),format.raw/*41.11*/("""<div id="actualizarPDF"""),_display_(/*41.34*/lista1/*41.40*/.id),format.raw/*41.43*/("""">
											--
										</div>
									""")))}else/*44.15*/{_display_(Seq[Any](format.raw/*44.16*/("""
										"""),format.raw/*45.11*/("""<a href="#" onclick="mostrarPDF('"""),_display_(/*45.45*/lista1/*45.51*/.ajustePDF),format.raw/*45.61*/("""')">
											<kbd style="background-color: #85C1E9">pdf</kbd>
										</a>
									""")))}),format.raw/*48.11*/("""
								"""),format.raw/*49.9*/("""</td>
							</TR>
			 			""")))}),format.raw/*51.9*/("""
					"""),format.raw/*52.6*/("""</tbody>
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
	
""")))}),format.raw/*86.2*/("""


"""),format.raw/*89.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*90.31*/("""{"""),format.raw/*90.32*/("""
		  """),format.raw/*91.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*91.36*/("""{"""),format.raw/*91.37*/("""
		    	"""),format.raw/*92.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[25, 50, 100, 200, -1], [25, 50, 100, 200, "All"]],
		    	"order": [[ 0, "desc" ]],
		    	"language": """),format.raw/*95.20*/("""{"""),format.raw/*95.21*/("""
		        	"""),format.raw/*96.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*97.11*/("""}"""),format.raw/*97.12*/("""
		  """),format.raw/*98.5*/("""}"""),format.raw/*98.6*/(""" """),format.raw/*98.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*100.2*/("""}"""),format.raw/*100.3*/(""");
	
	const mostrarPDF = (nombrePDF) => """),format.raw/*102.36*/("""{"""),format.raw/*102.37*/("""
		  """),format.raw/*103.5*/("""document.getElementById('rutaPDF').innerHTML="<object data='/showPdf/"+nombrePDF+"' type='application/pdf' width='100%' height='720'></object>";
		  $('#muestraPDF').modal('show');
	"""),format.raw/*105.2*/("""}"""),format.raw/*105.3*/("""
	
	
"""),format.raw/*108.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listAjustes:List[tables.AjustesEP],bodegaEmpresa:tables.BodegaEmpresa): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listAjustes,bodegaEmpresa)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.AjustesEP],tables.BodegaEmpresa) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listAjustes,bodegaEmpresa) => apply(mapDiccionario,mapPermiso,userMnu,listAjustes,bodegaEmpresa)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/ajustesEpRptDetalle.scala.html
                  HASH: aa276e809f17e65ae8af9daaf85a724f5ffd15f6
                  MATRIX: 1063->1|1327->172|1360->180|1376->188|1415->190|1443->193|1511->241|1539->243|1615->294|1724->383|1754->386|2581->1187|2623->1213|2662->1214|2697->1222|2805->1303|2823->1312|2878->1346|2922->1363|2937->1369|2970->1381|3006->1390|3079->1436|3094->1442|3126->1453|3199->1499|3214->1505|3251->1521|3324->1567|3339->1573|3367->1580|3439->1625|3454->1631|3487->1643|3558->1687|3573->1693|3603->1702|3674->1746|3689->1752|3724->1766|3864->1879|3903->1880|3942->1891|3992->1914|4007->1920|4031->1923|4098->1971|4137->1972|4176->1983|4237->2017|4252->2023|4283->2033|4404->2123|4440->2132|4497->2159|4530->2165|5690->3295|5720->3298|5810->3360|5839->3361|5871->3366|5930->3397|5959->3398|5994->3406|6169->3553|6198->3554|6238->3566|6344->3644|6373->3645|6405->3650|6433->3651|6461->3652|6562->3725|6591->3726|6660->3766|6690->3767|6723->3772|6933->3954|6962->3955|6995->3960
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|58->27|58->27|58->27|59->28|61->30|61->30|61->30|62->31|62->31|62->31|63->32|64->33|64->33|64->33|65->34|65->34|65->34|66->35|66->35|66->35|67->36|67->36|67->36|68->37|68->37|68->37|69->38|69->38|69->38|71->40|71->40|72->41|72->41|72->41|72->41|75->44|75->44|76->45|76->45|76->45|76->45|79->48|80->49|82->51|83->52|117->86|120->89|121->90|121->90|122->91|122->91|122->91|123->92|126->95|126->95|127->96|128->97|128->97|129->98|129->98|129->98|131->100|131->100|133->102|133->102|134->103|136->105|136->105|139->108
                  -- GENERATED --
              */
          