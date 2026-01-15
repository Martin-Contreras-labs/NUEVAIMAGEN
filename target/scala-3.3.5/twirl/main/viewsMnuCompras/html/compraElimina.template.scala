
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

object compraElimina extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Factura],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listFacturas: List[tables.Factura]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "LISTADO DE COMPRAS POSIBLES DE ELIMINAR","(compras que no tienen equipos confirmados)")),format.raw/*9.120*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>"""),_display_(/*15.13*/mapDiccionario/*15.27*/.get("RUT")),format.raw/*15.38*/("""</TH>
							<TH>PROVEEDOR</TH>
							<TH style="min-width:80px;">FECHA</TH>
							<TH>NUMERO</TH>
							<TH>OBSERVACIONES</TH>
							<TH>DOC</TH>
							<TH>DEL</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*25.8*/for(lista1 <- listFacturas) yield /*25.35*/{_display_(Seq[Any](format.raw/*25.36*/("""
							"""),format.raw/*26.8*/("""<TR>
								<td  style="text-align:left;">"""),_display_(/*27.40*/lista1/*27.46*/.getRut()),format.raw/*27.55*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*28.40*/lista1/*28.46*/.getNickNameProveedor()),format.raw/*28.69*/("""</td>
								<td  style="text-align:center;">
									<div style="display:none">"""),_display_(/*30.37*/utilities/*30.46*/.Fechas.AAMMDD(lista1.getFecha())),format.raw/*30.79*/("""</div>
									"""),_display_(/*31.11*/utilities/*31.20*/.Fechas.DDMMAA(lista1.getFecha())),format.raw/*31.53*/("""
								"""),format.raw/*32.9*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*33.42*/lista1/*33.48*/.getNumero()),format.raw/*33.60*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*34.40*/lista1/*34.46*/.getObservaciones()),format.raw/*34.65*/("""</td>
								<td style="text-align:center;">
									"""),_display_(if(lista1.getFacturaPDF().equals("0"))/*36.49*/{_display_(Seq[Any](format.raw/*36.50*/("""
										"""),format.raw/*37.11*/("""--
									""")))}else/*38.15*/{_display_(Seq[Any](format.raw/*38.16*/("""
										"""),format.raw/*39.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*39.52*/lista1/*39.58*/.getFacturaPDF()),format.raw/*39.74*/("""')">
											<kbd style="background-color: #7F8C8D">doc</kbd>
										</a>
									""")))}),format.raw/*42.11*/("""
								"""),format.raw/*43.9*/("""</td>
								<td  style="text-align:center;">
									<a href="#" onclick="elimarCompra('"""),_display_(/*45.46*/lista1/*45.52*/.getId()),format.raw/*45.60*/("""', '"""),_display_(/*45.65*/lista1/*45.71*/.getNumero()),format.raw/*45.83*/("""')">
										<kbd style="background-color: red">X</kbd>
									</a>
								</td>
								
							</TR>
			 			""")))}),format.raw/*51.9*/("""
					"""),format.raw/*52.6*/("""</tbody>
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
	
	<form class="formulario" id="eliminarCompra" method="post" action="/eliminaCompra/">	
		<input type="hidden" id="id_factura" name="id_factura">
	</form>
	
	

""")))}),format.raw/*71.2*/("""


"""),format.raw/*74.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*76.31*/("""{"""),format.raw/*76.32*/("""
		  """),format.raw/*77.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*78.2*/("""}"""),format.raw/*78.3*/(""");
	
	const elimarCompra = (id_factura, numeroFactura) => """),format.raw/*80.54*/("""{"""),format.raw/*80.55*/("""
		"""),format.raw/*81.3*/("""alertify.confirm("Esta seguro de eliminar la compra número: "+numeroFactura, function (e) """),format.raw/*81.93*/("""{"""),format.raw/*81.94*/("""
			"""),format.raw/*82.4*/("""if (e) """),format.raw/*82.11*/("""{"""),format.raw/*82.12*/("""
				"""),format.raw/*83.5*/("""$('#id_factura').val(id_factura);
		  		document.getElementById('eliminarCompra').submit();
			"""),format.raw/*85.4*/("""}"""),format.raw/*85.5*/("""
		"""),format.raw/*86.3*/("""}"""),format.raw/*86.4*/(""");
	"""),format.raw/*87.2*/("""}"""),format.raw/*87.3*/("""
	
"""),format.raw/*89.1*/("""</script>




		
		
	
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
                  SOURCE: app/viewsMnuCompras/compraElimina.scala.html
                  HASH: 0fa6580898a023b8243cab4a34ce34ad5564576c
                  MATRIX: 1033->1|1259->134|1292->142|1308->150|1347->152|1376->156|1444->204|1472->206|1548->257|1685->373|1715->376|2024->658|2047->672|2079->683|2322->900|2365->927|2404->928|2439->936|2510->980|2525->986|2555->995|2627->1040|2642->1046|2686->1069|2796->1152|2814->1161|2868->1194|2912->1211|2930->1220|2984->1253|3020->1262|3094->1309|3109->1315|3142->1327|3214->1372|3229->1378|3269->1397|3390->1491|3429->1492|3468->1503|3504->1520|3543->1521|3582->1532|3650->1573|3665->1579|3702->1595|3823->1685|3859->1694|3978->1786|3993->1792|4022->1800|4054->1805|4069->1811|4102->1823|4248->1939|4281->1945|4808->2442|4838->2445|4929->2508|4958->2509|4990->2514|5083->2580|5111->2581|5197->2639|5226->2640|5256->2643|5374->2733|5403->2734|5434->2738|5469->2745|5498->2746|5530->2751|5652->2846|5680->2847|5710->2850|5738->2851|5769->2855|5797->2856|5827->2859
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|46->15|46->15|46->15|56->25|56->25|56->25|57->26|58->27|58->27|58->27|59->28|59->28|59->28|61->30|61->30|61->30|62->31|62->31|62->31|63->32|64->33|64->33|64->33|65->34|65->34|65->34|67->36|67->36|68->37|69->38|69->38|70->39|70->39|70->39|70->39|73->42|74->43|76->45|76->45|76->45|76->45|76->45|76->45|82->51|83->52|102->71|105->74|107->76|107->76|108->77|109->78|109->78|111->80|111->80|112->81|112->81|112->81|113->82|113->82|113->82|114->83|116->85|116->85|117->86|117->86|118->87|118->87|120->89
                  -- GENERATED --
              */
          