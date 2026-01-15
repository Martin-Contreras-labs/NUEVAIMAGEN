
package viewsMnuBajas.html

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

object bajaEquipoPrint extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listaCompra: List[List[String]], listaBaja: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "DETALLE DEL EQUIPO","(Compras vs Bajas)")),format.raw/*9.74*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">
				<h5>COMPRAS:</h5>
				<table class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>"""),_display_(/*16.13*/mapDiccionario/*16.27*/.get("RUT")),format.raw/*16.38*/("""</TH>
							<TH>PROVEEDOR</TH>
							<TH style="min-width:80px;">FECHA</TH>
							<TH>NUMERO<br>DOCUMENTO</TH>
							<TH>CODIGO</TH>
							<TH>EQUIPO</TH>
							<TH>UN</TH>
							<TH>CANT</TH>
							<TH>MON</TH>
							<TH>PRECIO<br>UNITARIO</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*29.8*/for(lista1 <- listaCompra) yield /*29.34*/{_display_(Seq[Any](format.raw/*29.35*/("""
							"""),format.raw/*30.8*/("""<TR>
								<td  style="text-align:left;">"""),_display_(/*31.40*/lista1/*31.46*/.get(1)),format.raw/*31.53*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*32.40*/lista1/*32.46*/.get(2)),format.raw/*32.53*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*33.42*/lista1/*33.48*/.get(3)),format.raw/*33.55*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*34.42*/lista1/*34.48*/.get(4)),format.raw/*34.55*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*35.40*/lista1/*35.46*/.get(5)),format.raw/*35.53*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*36.40*/lista1/*36.46*/.get(6)),format.raw/*36.53*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*37.42*/lista1/*37.48*/.get(7)),format.raw/*37.55*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*38.41*/lista1/*38.47*/.get(8)),format.raw/*38.54*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*39.42*/lista1/*39.48*/.get(9)),format.raw/*39.55*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*40.41*/lista1/*40.47*/.get(10)),format.raw/*40.55*/("""</td>
							</TR>
			 			""")))}),format.raw/*42.9*/("""
					"""),format.raw/*43.6*/("""</tbody>
				</table>
				<br><br>
				<h5>BAJAS:</h5>
				<table class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>FECHA ACTA</TH>
							<TH>NRO ACTA</TH>
							<TH>CODIGO</TH>
							<TH>EQUIPO</TH>
							<TH>UN</TH>
							<TH>CANT</TH>
							<TH>MOTIVO DE LA BAJA</TH>
							<TH>CONFIRMADA</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*61.8*/for(lista1 <- listaBaja) yield /*61.32*/{_display_(Seq[Any](format.raw/*61.33*/("""
							"""),format.raw/*62.8*/("""<TR>
								<td  style="text-align:center;">"""),_display_(/*63.42*/lista1/*63.48*/.get(1)),format.raw/*63.55*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*64.42*/lista1/*64.48*/.get(2)),format.raw/*64.55*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*65.40*/lista1/*65.46*/.get(3)),format.raw/*65.53*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*66.40*/lista1/*66.46*/.get(4)),format.raw/*66.53*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*67.42*/lista1/*67.48*/.get(5)),format.raw/*67.55*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*68.41*/lista1/*68.47*/.get(6)),format.raw/*68.54*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*69.40*/lista1/*69.46*/.get(7)),format.raw/*69.53*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*70.42*/lista1/*70.48*/.get(8)),format.raw/*70.55*/("""</td>
								
							
							</TR>
			 			""")))}),format.raw/*74.9*/("""
					"""),format.raw/*75.6*/("""</tbody>
				</table>
			</div>
		</div>
		<div class="noprint" align="left">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
					<input type="button"  value="Volver" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/listBajasPorEquipo/';">
				</div>
			</div>
		</div>
	</div>

""")))}),format.raw/*88.2*/("""




"""),format.raw/*93.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*95.31*/("""{"""),format.raw/*95.32*/("""
		  """),format.raw/*96.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*97.2*/("""}"""),format.raw/*97.3*/(""");
	
</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listaCompra:List[List[String]],listaBaja:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listaCompra,listaBaja)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listaCompra,listaBaja) => apply(mapDiccionario,mapPermiso,userMnu,listaCompra,listaBaja)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuBajas/bajaEquipoPrint.scala.html
                  HASH: dfd381bdc0af5aede5bfb2cad8d319dd15cc7cc9
                  MATRIX: 1050->1|1304->162|1337->170|1353->178|1392->180|1420->183|1488->231|1516->233|1592->284|1682->354|1712->357|2023->641|2046->655|2078->666|2405->967|2447->993|2486->994|2521->1002|2592->1046|2607->1052|2635->1059|2707->1104|2722->1110|2750->1117|2824->1164|2839->1170|2867->1177|2941->1224|2956->1230|2984->1237|3056->1282|3071->1288|3099->1295|3171->1340|3186->1346|3214->1353|3288->1400|3303->1406|3331->1413|3404->1459|3419->1465|3447->1472|3521->1519|3536->1525|3564->1532|3637->1578|3652->1584|3681->1592|3738->1619|3771->1625|4245->2073|4285->2097|4324->2098|4359->2106|4432->2152|4447->2158|4475->2165|4549->2212|4564->2218|4592->2225|4664->2270|4679->2276|4707->2283|4779->2328|4794->2334|4822->2341|4896->2388|4911->2394|4939->2401|5012->2447|5027->2453|5055->2460|5127->2505|5142->2511|5170->2518|5244->2565|5259->2571|5287->2578|5361->2622|5394->2628|5789->2993|5821->2998|5912->3061|5941->3062|5973->3067|6066->3133|6094->3134
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|47->16|47->16|47->16|60->29|60->29|60->29|61->30|62->31|62->31|62->31|63->32|63->32|63->32|64->33|64->33|64->33|65->34|65->34|65->34|66->35|66->35|66->35|67->36|67->36|67->36|68->37|68->37|68->37|69->38|69->38|69->38|70->39|70->39|70->39|71->40|71->40|71->40|73->42|74->43|92->61|92->61|92->61|93->62|94->63|94->63|94->63|95->64|95->64|95->64|96->65|96->65|96->65|97->66|97->66|97->66|98->67|98->67|98->67|99->68|99->68|99->68|100->69|100->69|100->69|101->70|101->70|101->70|105->74|106->75|119->88|124->93|126->95|126->95|127->96|128->97|128->97
                  -- GENERATED --
              */
          