
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

object compraListaModifica extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Factura],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listFacturas: List[tables.Factura]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "MODIFICAR COMPRA Y/O ADQUISICION","(modifica inventarios)")),format.raw/*9.92*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>SUCURSAL</TH>
							<TH>"""),_display_(/*16.13*/mapDiccionario/*16.27*/.get("RUT")),format.raw/*16.38*/("""</TH>
							<TH>PROVEEDOR</TH>
							<TH style="min-width:80px;">FECHA</TH>
							<TH>NUMERO</TH>
							<TH>OBSERVACIONES</TH>
							<TH>DOC</TH>
							<TH>EDIT</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*26.8*/for(lista1 <- listFacturas) yield /*26.35*/{_display_(Seq[Any](format.raw/*26.36*/("""
							"""),format.raw/*27.8*/("""<TR>
								<td  style="text-align:left;">"""),_display_(/*28.40*/lista1/*28.46*/.getNameSucursal()),format.raw/*28.64*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*29.40*/lista1/*29.46*/.getRut()),format.raw/*29.55*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*30.40*/lista1/*30.46*/.getNickNameProveedor()),format.raw/*30.69*/("""</td>
								<td  style="text-align:center;">
									<div style="display:none">"""),_display_(/*32.37*/utilities/*32.46*/.Fechas.AAMMDD(lista1.getFecha())),format.raw/*32.79*/("""</div>
									"""),_display_(/*33.11*/utilities/*33.20*/.Fechas.DDMMAA(lista1.getFecha())),format.raw/*33.53*/("""
								"""),format.raw/*34.9*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*35.40*/lista1/*35.46*/.getNumero()),format.raw/*35.58*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*36.40*/lista1/*36.46*/.getObservaciones()),format.raw/*36.65*/("""</td>
								<td style="text-align:center;">
									"""),_display_(if(lista1.getFacturaPDF().equals("0"))/*38.49*/{_display_(Seq[Any](format.raw/*38.50*/("""
										"""),format.raw/*39.11*/("""--
									""")))}else/*40.15*/{_display_(Seq[Any](format.raw/*40.16*/("""
										"""),format.raw/*41.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*41.52*/lista1/*41.58*/.getFacturaPDF()),format.raw/*41.74*/("""')">
											<kbd style="background-color: #7F8C8D">doc</kbd>
										</a>
									""")))}),format.raw/*44.11*/("""
								"""),format.raw/*45.9*/("""</td>
								<td  style="text-align:center;">
									<a href="/compraModifica/"""),_display_(/*47.36*/lista1/*47.42*/.getId()),format.raw/*47.50*/("""">
										<kbd style="background-color: #73C6B6">edit</kbd>
									</a>
								</td>
								
							</TR>
			 			""")))}),format.raw/*53.9*/("""
					"""),format.raw/*54.6*/("""</tbody>
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
	
	

""")))}),format.raw/*73.2*/("""


"""),format.raw/*76.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*77.31*/("""{"""),format.raw/*77.32*/("""
		  """),format.raw/*78.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*78.36*/("""{"""),format.raw/*78.37*/("""
		    	"""),format.raw/*79.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 3, "desc" ]],
		    	"language": """),format.raw/*82.20*/("""{"""),format.raw/*82.21*/("""
		        	"""),format.raw/*83.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*84.11*/("""}"""),format.raw/*84.12*/("""
		  """),format.raw/*85.5*/("""}"""),format.raw/*85.6*/(""" """),format.raw/*85.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*87.2*/("""}"""),format.raw/*87.3*/(""");
	
	
	const descargaDocumento = (nombreDOC) => """),format.raw/*90.43*/("""{"""),format.raw/*90.44*/("""
		  """),format.raw/*91.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*93.2*/("""}"""),format.raw/*93.3*/("""
	
	
		
		
	
	
	
"""),format.raw/*101.1*/("""</script>




		
		
	
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
                  SOURCE: app/viewsMnuCompras/compraListaModifica.scala.html
                  HASH: 90df5a45b3113ac0ce92bb11315f275bdb3e344b
                  MATRIX: 1039->1|1265->134|1298->142|1314->150|1353->152|1382->156|1450->204|1478->206|1554->257|1662->345|1692->348|2026->655|2049->669|2081->680|2325->898|2368->925|2407->926|2442->934|2513->978|2528->984|2567->1002|2639->1047|2654->1053|2684->1062|2756->1107|2771->1113|2815->1136|2925->1219|2943->1228|2997->1261|3041->1278|3059->1287|3113->1320|3149->1329|3221->1374|3236->1380|3269->1392|3341->1437|3356->1443|3396->1462|3517->1556|3556->1557|3595->1568|3631->1585|3670->1586|3709->1597|3777->1638|3792->1644|3829->1660|3950->1750|3986->1759|4095->1841|4110->1847|4139->1855|4290->1976|4323->1982|4865->2494|4895->2497|4985->2559|5014->2560|5046->2565|5105->2596|5134->2597|5169->2605|5344->2752|5373->2753|5413->2765|5519->2843|5548->2844|5580->2849|5608->2850|5636->2851|5736->2924|5764->2925|5841->2974|5870->2975|5902->2980|6033->3084|6061->3085|6106->3102
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|47->16|47->16|47->16|57->26|57->26|57->26|58->27|59->28|59->28|59->28|60->29|60->29|60->29|61->30|61->30|61->30|63->32|63->32|63->32|64->33|64->33|64->33|65->34|66->35|66->35|66->35|67->36|67->36|67->36|69->38|69->38|70->39|71->40|71->40|72->41|72->41|72->41|72->41|75->44|76->45|78->47|78->47|78->47|84->53|85->54|104->73|107->76|108->77|108->77|109->78|109->78|109->78|110->79|113->82|113->82|114->83|115->84|115->84|116->85|116->85|116->85|118->87|118->87|121->90|121->90|122->91|124->93|124->93|132->101
                  -- GENERATED --
              */
          