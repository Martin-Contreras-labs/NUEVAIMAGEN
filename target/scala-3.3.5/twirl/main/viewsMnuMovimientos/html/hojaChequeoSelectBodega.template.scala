
package viewsMnuMovimientos.html

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

object hojaChequeoSelectBodega extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listBodegas: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalContactoBodega(mapDiccionario)),format.raw/*9.38*/("""
	"""),_display_(/*10.3*/modalContactoCliente()),format.raw/*10.25*/("""
	"""),_display_(/*11.3*/modalContactoProyecto()),format.raw/*11.26*/("""
	
	"""),format.raw/*13.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*14.4*/barraTitulo(mapDiccionario, "EMISION DE HOJAS DE CHEQUEO - "+mapDiccionario.get("BODEGA")+"S/PROYECTOS", "(agrupado por equipo y cotización)")),format.raw/*14.146*/("""
		"""),format.raw/*15.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>SUCURSAL</TH>
							<TH>TIPO</TH>
							<TH>"""),_display_(/*22.13*/mapDiccionario/*22.27*/.get("BODEGA")),format.raw/*22.41*/("""/PROYECTO</TH>
							<TH>"""),_display_(/*23.13*/mapDiccionario/*23.27*/.get("RUT")),format.raw/*23.38*/(""" """),format.raw/*23.39*/("""(Cliente o Propietario)</TH>
							<TH>NOMBRE CLIENTE O PROPIETARIO</TH>
					        <TH>NOMBRE DEL PROYECTO</TH>
					        <TH>"""),_display_(/*26.19*/mapDiccionario/*26.33*/.get("COMUNA")),format.raw/*26.47*/(""" """),format.raw/*26.48*/("""PROYECTO</TH>
							<TH>SELECT</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*31.8*/for(lista1 <- listBodegas) yield /*31.34*/{_display_(Seq[Any](format.raw/*31.35*/("""
							"""),format.raw/*32.8*/("""<TR>
								<td  style="text-align:left;">"""),_display_(/*33.40*/lista1/*33.46*/.get(16)),format.raw/*33.54*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*34.40*/lista1/*34.46*/.get(4)),format.raw/*34.53*/("""</td>
								<td style="text-align:left;"><a href="#" onclick="buscaBodega('"""),_display_(/*35.73*/lista1/*35.79*/.get(5)),format.raw/*35.86*/("""')"><div id=""""),_display_(/*35.100*/lista1/*35.106*/.get(1)),format.raw/*35.113*/("""">"""),_display_(/*35.116*/lista1/*35.122*/.get(5)),format.raw/*35.129*/("""</div></a></td>
								<td style="text-align:center;"><a href="#" onclick="buscaCliente('"""),_display_(/*36.76*/lista1/*36.82*/.get(7)),format.raw/*36.89*/("""')">"""),_display_(/*36.94*/lista1/*36.100*/.get(6)),format.raw/*36.107*/("""</a></td>
								<td style="text-align:left;"><a href="#" onclick="buscaCliente('"""),_display_(/*37.74*/lista1/*37.80*/.get(7)),format.raw/*37.87*/("""')">"""),_display_(/*37.92*/lista1/*37.98*/.get(7)),format.raw/*37.105*/("""</a></td>
								<td style="text-align:left;"><a href="#" onclick="buscaProyecto('"""),_display_(/*38.75*/lista1/*38.81*/.get(8)),format.raw/*38.88*/("""')">"""),_display_(/*38.93*/lista1/*38.99*/.get(8)),format.raw/*38.106*/("""</a></td>
								
								<td  style="text-align:left;">"""),_display_(/*40.40*/lista1/*40.46*/.get(9)),format.raw/*40.53*/("""</td>
								<td  style="text-align:center;">
									<a href="/hojaChequeoDetalle/"""),_display_(/*42.40*/lista1/*42.46*/.get(1)),format.raw/*42.53*/("""">
										<kbd style="background-color: #73C6B6">select</kbd>
									</a>
								</td>
							</TR>
			 			""")))}),format.raw/*47.9*/("""
					"""),format.raw/*48.6*/("""</tbody>
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
""")))}),format.raw/*60.2*/("""




"""),format.raw/*65.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*66.31*/("""{"""),format.raw/*66.32*/("""
		  """),format.raw/*67.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*67.36*/("""{"""),format.raw/*67.37*/("""
		    	"""),format.raw/*68.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
				"order": [[ 1, "asc" ]],
		    	"language": """),format.raw/*71.20*/("""{"""),format.raw/*71.21*/("""
		        	"""),format.raw/*72.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*73.11*/("""}"""),format.raw/*73.12*/("""
		  """),format.raw/*74.5*/("""}"""),format.raw/*74.6*/(""" """),format.raw/*74.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*76.2*/("""}"""),format.raw/*76.3*/(""");
	
</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listBodegas:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listBodegas)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listBodegas) => apply(mapDiccionario,mapPermiso,userMnu,listBodegas)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuMovimientos/hojaChequeoSelectBodega.scala.html
                  HASH: 74b28886daf4ecc70057a809ec8e8c778c1b77d3
                  MATRIX: 1045->1|1268->131|1301->139|1317->147|1356->149|1385->153|1453->201|1483->206|1538->241|1567->244|1610->266|1639->269|1683->292|1714->296|1791->347|1955->489|1985->492|2340->820|2363->834|2398->848|2452->875|2475->889|2507->900|2536->901|2697->1035|2720->1049|2755->1063|2784->1064|2893->1147|2935->1173|2974->1174|3009->1182|3080->1226|3095->1232|3124->1240|3196->1285|3211->1291|3239->1298|3344->1376|3359->1382|3387->1389|3429->1403|3445->1409|3474->1416|3505->1419|3521->1425|3550->1432|3668->1523|3683->1529|3711->1536|3743->1541|3759->1547|3788->1554|3898->1637|3913->1643|3941->1650|3973->1655|3988->1661|4017->1668|4128->1752|4143->1758|4171->1765|4203->1770|4218->1776|4247->1783|4332->1841|4347->1847|4375->1854|4488->1940|4503->1946|4531->1953|4675->2067|4708->2073|5074->2409|5106->2414|5196->2476|5225->2477|5257->2482|5316->2513|5345->2514|5380->2522|5551->2665|5580->2666|5620->2678|5726->2756|5755->2757|5787->2762|5815->2763|5843->2764|5943->2837|5971->2838
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|41->10|41->10|42->11|42->11|44->13|45->14|45->14|46->15|53->22|53->22|53->22|54->23|54->23|54->23|54->23|57->26|57->26|57->26|57->26|62->31|62->31|62->31|63->32|64->33|64->33|64->33|65->34|65->34|65->34|66->35|66->35|66->35|66->35|66->35|66->35|66->35|66->35|66->35|67->36|67->36|67->36|67->36|67->36|67->36|68->37|68->37|68->37|68->37|68->37|68->37|69->38|69->38|69->38|69->38|69->38|69->38|71->40|71->40|71->40|73->42|73->42|73->42|78->47|79->48|91->60|96->65|97->66|97->66|98->67|98->67|98->67|99->68|102->71|102->71|103->72|104->73|104->73|105->74|105->74|105->74|107->76|107->76
                  -- GENERATED --
              */
          