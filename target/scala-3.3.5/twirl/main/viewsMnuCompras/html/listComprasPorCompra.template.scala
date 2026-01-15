
package viewsMnuCompras.html

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

object listComprasPorCompra extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Factura],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listFacturas: List[tables.Factura]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "LISTADO DE COMPRAS POR DOCUMENTO","")),format.raw/*9.70*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-5 col-sm-4 col-md-3 col-lg-2">
				<form action="/listComprasPorCompraExcel/" method="POST">
					<input type="submit" class="btn btn-info btn-sm rounded-pill btn-block" value="Excel">
				</form>
			</div>
		</div>
		<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>"""),_display_(/*22.13*/mapDiccionario/*22.27*/.get("RUT")),format.raw/*22.38*/("""</TH>
							<TH>PROVEEDOR</TH>
							<TH style="min-width:80px;">FECHA</TH>
							<TH>NUMERO</TH>
							<TH>OBSERVACIONES</TH>
							<TH>DOC</TH>
							<TH>SELECT</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*32.8*/for(lista1 <- listFacturas) yield /*32.35*/{_display_(Seq[Any](format.raw/*32.36*/("""
							"""),format.raw/*33.8*/("""<TR>
								<td  style="text-align:left;">"""),_display_(/*34.40*/lista1/*34.46*/.getRut()),format.raw/*34.55*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*35.40*/lista1/*35.46*/.getNickNameProveedor()),format.raw/*35.69*/("""</td>
								<td  style="text-align:center;">
									<div style="display:none">"""),_display_(/*37.37*/utilities/*37.46*/.Fechas.AAMMDD(lista1.getFecha())),format.raw/*37.79*/("""</div>
									"""),_display_(/*38.11*/utilities/*38.20*/.Fechas.DDMMAA(lista1.getFecha())),format.raw/*38.53*/("""
								"""),format.raw/*39.9*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*40.42*/lista1/*40.48*/.getNumero()),format.raw/*40.60*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*41.40*/lista1/*41.46*/.getObservaciones()),format.raw/*41.65*/("""</td>
								<td style="text-align:center;">
									"""),_display_(if(lista1.getFacturaPDF().equals("0"))/*43.49*/{_display_(Seq[Any](format.raw/*43.50*/("""
										"""),format.raw/*44.11*/("""--
									""")))}else/*45.15*/{_display_(Seq[Any](format.raw/*45.16*/("""
										"""),format.raw/*46.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*46.52*/lista1/*46.58*/.getFacturaPDF()),format.raw/*46.74*/("""')">
											<kbd style="background-color: #7F8C8D">doc</kbd>
										</a>
									""")))}),format.raw/*49.11*/("""
								"""),format.raw/*50.9*/("""</td>
								<td  style="text-align:center;">
									<a href="/compraFacturaPrint/"""),_display_(/*52.40*/lista1/*52.46*/.getId()),format.raw/*52.54*/("""">
										<kbd style="background-color: #73C6B6">select</kbd>
									</a>
								</td>
								
							</TR>
			 			""")))}),format.raw/*58.9*/("""
					"""),format.raw/*59.6*/("""</tbody>
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
				</div>
			</div>
		</div>
	</div>
	
	<form class="formulario" id="formDescargaDocumento" method="post" action="/downloadPDF/">	
		<input type="hidden" id="descargaDocumento" name="nombreArchivo">
	</form>
	
	

""")))}),format.raw/*78.2*/("""


"""),format.raw/*81.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*82.31*/("""{"""),format.raw/*82.32*/("""
		  """),format.raw/*83.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*83.36*/("""{"""),format.raw/*83.37*/("""
		    	"""),format.raw/*84.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 2, "desc" ]],
		    	"language": """),format.raw/*87.20*/("""{"""),format.raw/*87.21*/("""
		        	"""),format.raw/*88.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*89.11*/("""}"""),format.raw/*89.12*/("""
		  """),format.raw/*90.5*/("""}"""),format.raw/*90.6*/(""" """),format.raw/*90.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*92.2*/("""}"""),format.raw/*92.3*/(""");
	
	
	const descargaDocumento = (nombreDOC) => """),format.raw/*95.43*/("""{"""),format.raw/*95.44*/("""
		  """),format.raw/*96.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*98.2*/("""}"""),format.raw/*98.3*/("""
	
	
		
		
	
	
	
"""),format.raw/*106.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listFacturas:List[tables.Factura]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listFacturas)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Factura]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listFacturas) => apply(mapDiccionario,mapPermiso,userMnu,listFacturas)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCompras/listComprasPorCompra.scala.html
                  HASH: 7356bee0bea3992354fbebd41bf2892710370c10
                  MATRIX: 1040->1|1266->134|1299->142|1315->150|1354->152|1383->156|1451->204|1479->206|1555->257|1641->323|1671->326|2265->893|2288->907|2320->918|2566->1138|2609->1165|2648->1166|2683->1174|2754->1218|2769->1224|2799->1233|2871->1278|2886->1284|2930->1307|3040->1390|3058->1399|3112->1432|3156->1449|3174->1458|3228->1491|3264->1500|3338->1547|3353->1553|3386->1565|3458->1610|3473->1616|3513->1635|3634->1729|3673->1730|3712->1741|3748->1758|3787->1759|3826->1770|3894->1811|3909->1817|3946->1833|4067->1923|4103->1932|4216->2018|4231->2024|4260->2032|4413->2155|4446->2161|4988->2673|5018->2676|5108->2738|5137->2739|5169->2744|5228->2775|5257->2776|5292->2784|5467->2931|5496->2932|5536->2944|5642->3022|5671->3023|5703->3028|5731->3029|5759->3030|5859->3103|5887->3104|5964->3153|5993->3154|6025->3159|6156->3263|6184->3264|6229->3281
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|53->22|53->22|53->22|63->32|63->32|63->32|64->33|65->34|65->34|65->34|66->35|66->35|66->35|68->37|68->37|68->37|69->38|69->38|69->38|70->39|71->40|71->40|71->40|72->41|72->41|72->41|74->43|74->43|75->44|76->45|76->45|77->46|77->46|77->46|77->46|80->49|81->50|83->52|83->52|83->52|89->58|90->59|109->78|112->81|113->82|113->82|114->83|114->83|114->83|115->84|118->87|118->87|119->88|120->89|120->89|121->90|121->90|121->90|123->92|123->92|126->95|126->95|127->96|129->98|129->98|137->106
                  -- GENERATED --
              */
          