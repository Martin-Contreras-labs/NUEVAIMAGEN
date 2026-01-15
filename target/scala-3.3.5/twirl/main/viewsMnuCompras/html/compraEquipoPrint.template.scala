
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

object compraEquipoPrint extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

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
							<TH>CODIGO</TH>
							<TH>EQUIPO</TH>
							<TH>UN</TH>
							<TH>CANT</TH>
							<TH>MOTIVO DE LA BAJA</TH>
							<TH>CONFIRMADA</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*60.8*/for(lista1 <- listaBaja) yield /*60.32*/{_display_(Seq[Any](format.raw/*60.33*/("""
							"""),format.raw/*61.8*/("""<TR>
								<td  style="text-align:center;">"""),_display_(/*62.42*/lista1/*62.48*/.get(1)),format.raw/*62.55*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*63.40*/lista1/*63.46*/.get(3)),format.raw/*63.53*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*64.40*/lista1/*64.46*/.get(4)),format.raw/*64.53*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*65.42*/lista1/*65.48*/.get(5)),format.raw/*65.55*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*66.41*/lista1/*66.47*/.get(6)),format.raw/*66.54*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*67.40*/lista1/*67.46*/.get(7)),format.raw/*67.53*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*68.42*/lista1/*68.48*/.get(8)),format.raw/*68.55*/("""</td>
							</TR>
			 			""")))}),format.raw/*70.9*/("""
					"""),format.raw/*71.6*/("""</tbody>
				</table>
			</div>
		</div>
		<div class="noprint" align="left">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
					<input type="button"  value="Volver" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/listComprasPorEquipo/';">
				</div>
			</div>
		</div>
	</div>

""")))}),format.raw/*84.2*/("""




"""),format.raw/*89.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*91.31*/("""{"""),format.raw/*91.32*/("""
		  """),format.raw/*92.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*93.2*/("""}"""),format.raw/*93.3*/(""");
	
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
                  SOURCE: app/viewsMnuCompras/compraEquipoPrint.scala.html
                  HASH: 70ce4d837b1be31650a33afde3dbcd882a0e9f81
                  MATRIX: 1054->1|1308->162|1341->170|1357->178|1396->180|1424->183|1492->231|1520->233|1596->284|1686->354|1716->357|2027->641|2050->655|2082->666|2409->967|2451->993|2490->994|2525->1002|2596->1046|2611->1052|2639->1059|2711->1104|2726->1110|2754->1117|2828->1164|2843->1170|2871->1177|2945->1224|2960->1230|2988->1237|3060->1282|3075->1288|3103->1295|3175->1340|3190->1346|3218->1353|3292->1400|3307->1406|3335->1413|3408->1459|3423->1465|3451->1472|3525->1519|3540->1525|3568->1532|3641->1578|3656->1584|3685->1592|3742->1619|3775->1625|4224->2048|4264->2072|4303->2073|4338->2081|4411->2127|4426->2133|4454->2140|4526->2185|4541->2191|4569->2198|4641->2243|4656->2249|4684->2256|4758->2303|4773->2309|4801->2316|4874->2362|4889->2368|4917->2375|4989->2420|5004->2426|5032->2433|5106->2480|5121->2486|5149->2493|5206->2520|5239->2526|5636->2893|5668->2898|5759->2961|5788->2962|5820->2967|5913->3033|5941->3034
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|47->16|47->16|47->16|60->29|60->29|60->29|61->30|62->31|62->31|62->31|63->32|63->32|63->32|64->33|64->33|64->33|65->34|65->34|65->34|66->35|66->35|66->35|67->36|67->36|67->36|68->37|68->37|68->37|69->38|69->38|69->38|70->39|70->39|70->39|71->40|71->40|71->40|73->42|74->43|91->60|91->60|91->60|92->61|93->62|93->62|93->62|94->63|94->63|94->63|95->64|95->64|95->64|96->65|96->65|96->65|97->66|97->66|97->66|98->67|98->67|98->67|99->68|99->68|99->68|101->70|102->71|115->84|120->89|122->91|122->91|123->92|124->93|124->93
                  -- GENERATED --
              */
          