
package viewsMnuOdoAppWeb.html

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

object odoAutorizaListarVentasWeb extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.VentaServicio],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listVentas: List[tables.VentaServicio]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""

"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""


	"""),format.raw/*8.2*/("""<form id="excel" class="formulario" method="post" action="/routes2/homeOdoAutorizaVentasWebExcel/"></form>
	
	<!-- menues(mapDiccionario, mapPermiso, userMnu, " ") -->
	<div id="mostrarmostrar" style="display:none;">
		<!-- barraTitulo(mapDiccionario, "LISTA DE REPORT DIARIO PARA AUTORIZAR", "(AUTORIZAR)") -->
		<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">
			
				<div align="center">
					<button type="button"  class="btn btn-sm btn-success" onclick="document.getElementById('excel').submit()">
						Exportar a Excel
					</button>
					
					
					"""),_display_(if(mapPermiso.get("reporteMovOdo0")!=null)/*22.49*/ {_display_(Seq[Any](format.raw/*22.51*/("""
						"""),format.raw/*23.7*/("""<button type="button"  class="btn btn-sm btn-info" onclick='window.location="/routes2/reporteMovOdoAutorizador/"'>
							Reporte Movimientos
						</button>
					""")))} else {null} ),format.raw/*26.7*/("""
					
				"""),format.raw/*28.5*/("""</div>
				
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>ID</TH>
							<TH style="min-width:80px;">FECHA</TH>
							<TH>OPERADOR</TH>
							<TH>"""),_display_(/*36.13*/mapDiccionario/*36.27*/.get("BODEGA")),format.raw/*36.41*/("""</TH>
							<TH>COTI</TH>
							<TH>CODIGO</TH>
							<TH>SERVICIO</TH>
							<TH>EQUIPO</TH>
							<TH>UN</TH>
							<TH>CANTIDAD</TH>
							<TH>DETALLE</TH>
							<TH>OPERADOR</TH>
							<TH>AUTORIZADOR</TH>
							"""),_display_(if(mapPermiso.get("odoVentasEliminar")!=null)/*46.54*/ {_display_(Seq[Any](format.raw/*46.56*/("""
								"""),format.raw/*47.9*/("""<TH>DEL</TH>
							""")))} else {null} ),format.raw/*48.9*/("""
						"""),format.raw/*49.7*/("""</TR>
					</thead>
					<tbody>
						"""),_display_(/*52.8*/for(lista1 <- listVentas) yield /*52.33*/{_display_(Seq[Any](format.raw/*52.34*/("""
							"""),format.raw/*53.8*/("""<TR>
								<td  style="text-align:center;">"""),_display_(/*54.42*/lista1/*54.48*/.getId()),format.raw/*54.56*/("""</td>
								<td  style="text-align:center; min-width:80px;">
									<div style="display:none">"""),_display_(/*56.37*/utilities/*56.46*/.Fechas.AAMMDD(lista1.getFecha())),format.raw/*56.79*/("""</div>
									"""),_display_(/*57.11*/utilities/*57.20*/.Fechas.DDMMAA(lista1.getFecha())),format.raw/*57.53*/("""
								"""),format.raw/*58.9*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*59.40*/lista1/*59.46*/.getNomOperador()),format.raw/*59.63*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*60.40*/lista1/*60.46*/.getNomBodegaEmpresa()),format.raw/*60.68*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*61.42*/lista1/*61.48*/.getNroCotiOdo()),format.raw/*61.64*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*62.42*/lista1/*62.48*/.getCodServicio()),format.raw/*62.65*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*63.40*/lista1/*63.46*/.getNomServicio()),format.raw/*63.63*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*64.40*/lista1/*64.46*/.getNomEquipo()),format.raw/*64.61*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*65.42*/lista1/*65.48*/.getUnidadServicio()),format.raw/*65.68*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*66.41*/lista1/*66.47*/.getCantidad()),format.raw/*66.61*/("""</td>
								<td style="text-align:center;">
									<a href="/odoAutorizaDetalleVentaWeb/"""),_display_(/*68.48*/lista1/*68.54*/.getId()),format.raw/*68.62*/("""">
										<kbd style="background-color: #73C6B6">detalle</kbd>
									</a>
								</td>
								<td style="text-align:center;">
									<img src="data:image/jpeg;base64, """),_display_(/*73.45*/lista1/*73.51*/.getFirmaPDFoperador()),format.raw/*73.73*/("""" height="10px" />
								</td>
								<td style="text-align:center;">
									<a href="/odoAutorizaFirmaWeb/"""),_display_(/*76.41*/lista1/*76.47*/.getId()),format.raw/*76.55*/("""">
										<kbd style="background-color: #F7DC6F">FIR</kbd>
									</a>
									<img src="data:image/jpeg;base64, """),_display_(/*79.45*/lista1/*79.51*/.getFirmaPDFautorizador()),format.raw/*79.76*/("""" height="10px" />
								</td>
								"""),_display_(if(mapPermiso.get("odoVentasEliminar")!=null)/*81.55*/ {_display_(Seq[Any](format.raw/*81.57*/("""
									"""),format.raw/*82.10*/("""<td  style="text-align:center;">
										"""),_display_(if(lista1.getId()!=1)/*83.33*/{_display_(Seq[Any](format.raw/*83.34*/("""
											"""),format.raw/*84.12*/("""<a href="#" onclick= "eliminarVentaServicio('"""),_display_(/*84.58*/lista1/*84.64*/.getId()),format.raw/*84.72*/("""')">
												<kbd style="background-color: red">X</kbd>
											</a>
										""")))} else {null} ),format.raw/*87.12*/("""
									"""),format.raw/*88.10*/("""</td>
								""")))} else {null} ),format.raw/*89.10*/("""
								
							"""),format.raw/*91.8*/("""</TR>
			 			""")))}),format.raw/*92.9*/("""
					"""),format.raw/*93.6*/("""</tbody>
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="SALIR" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href = '/odo'";">
				</div>
			</div>
		</div>
	</div>
	
	
	
	<form id="form_eliminar" method="post" action="/odoVentaServicioEliminaWeb/">
		<input type="hidden" id="form_id_ventaServicio" name="id_ventaServicio">
	</form>
""")))}),format.raw/*111.2*/("""




"""),format.raw/*116.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*117.31*/("""{"""),format.raw/*117.32*/("""
		  """),format.raw/*118.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*118.36*/("""{"""),format.raw/*118.37*/("""
		    	"""),format.raw/*119.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
				"order": [[ 1, "desc" ],[ 0, "desc" ]],
		    	"language": """),format.raw/*122.20*/("""{"""),format.raw/*122.21*/("""
		        	"""),format.raw/*123.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*124.11*/("""}"""),format.raw/*124.12*/("""
		  """),format.raw/*125.5*/("""}"""),format.raw/*125.6*/(""" """),format.raw/*125.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*127.2*/("""}"""),format.raw/*127.3*/(""");
	
	const eliminarVentaServicio = (id_ventaServicio) => """),format.raw/*129.54*/("""{"""),format.raw/*129.55*/("""
		"""),format.raw/*130.3*/("""alertify.confirm("Esta seguro de eliminar este report ID: "+id_ventaServicio, function (e) """),format.raw/*130.94*/("""{"""),format.raw/*130.95*/("""
			"""),format.raw/*131.4*/("""if (e) """),format.raw/*131.11*/("""{"""),format.raw/*131.12*/("""
				"""),format.raw/*132.5*/("""$("#form_id_ventaServicio").val(id_ventaServicio);
				document.getElementById("form_eliminar").submit();
			"""),format.raw/*134.4*/("""}"""),format.raw/*134.5*/("""
		"""),format.raw/*135.3*/("""}"""),format.raw/*135.4*/(""");
	"""),format.raw/*136.2*/("""}"""),format.raw/*136.3*/("""
	
"""),format.raw/*138.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listVentas:List[tables.VentaServicio]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listVentas)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.VentaServicio]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listVentas) => apply(mapDiccionario,mapPermiso,userMnu,listVentas)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuOdoAppWeb/odoAutorizaListarVentasWeb.scala.html
                  HASH: a809947b6f3713a1a090601dbef10eb7d316151e
                  MATRIX: 1054->1|1284->138|1312->141|1328->149|1367->151|1397->155|2079->810|2119->812|2153->819|2360->983|2398->994|2707->1276|2730->1290|2765->1304|3064->1576|3104->1578|3140->1587|3204->1608|3238->1615|3304->1655|3345->1680|3384->1681|3419->1689|3492->1735|3507->1741|3536->1749|3662->1848|3680->1857|3734->1890|3778->1907|3796->1916|3850->1949|3886->1958|3958->2003|3973->2009|4011->2026|4083->2071|4098->2077|4141->2099|4215->2146|4230->2152|4267->2168|4341->2215|4356->2221|4394->2238|4466->2283|4481->2289|4519->2306|4591->2351|4606->2357|4642->2372|4716->2419|4731->2425|4772->2445|4845->2491|4860->2497|4895->2511|5015->2604|5030->2610|5059->2618|5264->2796|5279->2802|5322->2824|5462->2937|5477->2943|5506->2951|5653->3071|5668->3077|5714->3102|5828->3189|5868->3191|5906->3201|5998->3266|6037->3267|6077->3279|6150->3325|6165->3331|6194->3339|6325->3426|6363->3436|6422->3451|6466->3468|6510->3482|6543->3488|7080->3994|7113->3999|7204->4061|7234->4062|7267->4067|7327->4098|7357->4099|7393->4107|7580->4265|7610->4266|7651->4278|7758->4356|7788->4357|7821->4362|7850->4363|7879->4364|7980->4437|8009->4438|8096->4496|8126->4497|8157->4500|8277->4591|8307->4592|8339->4596|8375->4603|8405->4604|8438->4609|8575->4718|8604->4719|8635->4722|8664->4723|8696->4727|8725->4728|8756->4731
                  LINES: 28->1|34->3|36->5|36->5|36->5|39->8|53->22|53->22|54->23|57->26|59->28|67->36|67->36|67->36|77->46|77->46|78->47|79->48|80->49|83->52|83->52|83->52|84->53|85->54|85->54|85->54|87->56|87->56|87->56|88->57|88->57|88->57|89->58|90->59|90->59|90->59|91->60|91->60|91->60|92->61|92->61|92->61|93->62|93->62|93->62|94->63|94->63|94->63|95->64|95->64|95->64|96->65|96->65|96->65|97->66|97->66|97->66|99->68|99->68|99->68|104->73|104->73|104->73|107->76|107->76|107->76|110->79|110->79|110->79|112->81|112->81|113->82|114->83|114->83|115->84|115->84|115->84|115->84|118->87|119->88|120->89|122->91|123->92|124->93|142->111|147->116|148->117|148->117|149->118|149->118|149->118|150->119|153->122|153->122|154->123|155->124|155->124|156->125|156->125|156->125|158->127|158->127|160->129|160->129|161->130|161->130|161->130|162->131|162->131|162->131|163->132|165->134|165->134|166->135|166->135|167->136|167->136|169->138
                  -- GENERATED --
              */
          