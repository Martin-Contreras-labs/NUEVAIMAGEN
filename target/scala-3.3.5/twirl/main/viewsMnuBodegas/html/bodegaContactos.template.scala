
package viewsMnuBodegas.html

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

object bodegaContactos extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listBodegas: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	
	"""),_display_(/*8.3*/modalContactoBodega(mapDiccionario)),format.raw/*8.38*/("""
	"""),_display_(/*9.3*/modalContactoCliente()),format.raw/*9.25*/("""
	"""),_display_(/*10.3*/modalContactoProyecto()),format.raw/*10.26*/("""
	
	"""),format.raw/*12.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*13.4*/barraTitulo(mapDiccionario, "LISTAR O MODIFICAR CONTACTOS POR "+mapDiccionario.get("BODEGA")+"/PROYECTO", "")),format.raw/*13.113*/("""
		"""),format.raw/*14.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-8 col-md-8 col-lg-8">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>SUCURSAL</TH>
							<TH>"""),_display_(/*20.13*/mapDiccionario/*20.27*/.get("BODEGA")),format.raw/*20.41*/("""/PROYECTO</TH>
							<TH>NOMBRE CLIENTE</TH>
					        <TH>NOMBRE DEL PROYECTO</TH>
							<TH>FACTOR</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*27.8*/for(lista1 <- listBodegas) yield /*27.34*/{_display_(Seq[Any](format.raw/*27.35*/("""
							"""),format.raw/*28.8*/("""<TR>
								<td  style="text-align:left;">"""),_display_(/*29.40*/lista1/*29.46*/.get(16)),format.raw/*29.54*/("""</td>
								<td style="text-align:left;"><a href="#" onclick="buscaBodega('"""),_display_(/*30.73*/lista1/*30.79*/.get(5)),format.raw/*30.86*/("""')">"""),_display_(/*30.91*/lista1/*30.97*/.get(5)),format.raw/*30.104*/("""</a></td>
								<td style="text-align:left;"><a href="#" onclick="buscaCliente('"""),_display_(/*31.74*/lista1/*31.80*/.get(7)),format.raw/*31.87*/("""')">"""),_display_(/*31.92*/lista1/*31.98*/.get(7)),format.raw/*31.105*/("""</a></td>
								<td style="text-align:left;"><a href="#" onclick="buscaProyecto('"""),_display_(/*32.75*/lista1/*32.81*/.get(8)),format.raw/*32.88*/("""')">"""),_display_(/*32.93*/lista1/*32.99*/.get(8)),format.raw/*32.106*/("""</a></td>
								
								<td  style="text-align:center;">
									<a href="/bodegaModificaContacto/"""),_display_(/*35.44*/lista1/*35.50*/.get(1)),format.raw/*35.57*/("""">
										<kbd style="background-color: #73C6B6">E</kbd>
									</a>
								</td>
							</TR>
			 			""")))}),format.raw/*40.9*/("""
					"""),format.raw/*41.6*/("""</tbody>
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
				</div>
			</div>
		</div>
	</div>
""")))}),format.raw/*53.2*/("""




"""),format.raw/*58.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*59.31*/("""{"""),format.raw/*59.32*/("""
		  """),format.raw/*60.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*60.36*/("""{"""),format.raw/*60.37*/("""
		    	"""),format.raw/*61.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
				"order":[[1,"asc"]],
		    	"language": """),format.raw/*64.20*/("""{"""),format.raw/*64.21*/("""
		        	"""),format.raw/*65.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*66.11*/("""}"""),format.raw/*66.12*/("""
		  """),format.raw/*67.5*/("""}"""),format.raw/*67.6*/(""" """),format.raw/*67.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*69.2*/("""}"""),format.raw/*69.3*/(""");
	
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
                  SOURCE: app/viewsMnuBodegas/bodegaContactos.scala.html
                  HASH: c45450f7d97e0606140594b1580966b0b7da776b
                  MATRIX: 1033->1|1256->131|1283->133|1299->141|1338->143|1367->147|1435->195|1465->200|1520->235|1548->238|1590->260|1619->263|1663->286|1694->290|1771->341|1902->450|1932->453|2263->757|2286->771|2321->785|2504->942|2546->968|2585->969|2620->977|2691->1021|2706->1027|2735->1035|2840->1113|2855->1119|2883->1126|2915->1131|2930->1137|2959->1144|3069->1227|3084->1233|3112->1240|3144->1245|3159->1251|3188->1258|3299->1342|3314->1348|3342->1355|3374->1360|3389->1366|3418->1373|3548->1476|3563->1482|3591->1489|3730->1598|3763->1604|4130->1941|4162->1946|4252->2008|4281->2009|4313->2014|4372->2045|4401->2046|4436->2054|4603->2193|4632->2194|4672->2206|4778->2284|4807->2285|4839->2290|4867->2291|4895->2292|4995->2365|5023->2366
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|39->8|39->8|40->9|40->9|41->10|41->10|43->12|44->13|44->13|45->14|51->20|51->20|51->20|58->27|58->27|58->27|59->28|60->29|60->29|60->29|61->30|61->30|61->30|61->30|61->30|61->30|62->31|62->31|62->31|62->31|62->31|62->31|63->32|63->32|63->32|63->32|63->32|63->32|66->35|66->35|66->35|71->40|72->41|84->53|89->58|90->59|90->59|91->60|91->60|91->60|92->61|95->64|95->64|96->65|97->66|97->66|98->67|98->67|98->67|100->69|100->69
                  -- GENERATED --
              */
          