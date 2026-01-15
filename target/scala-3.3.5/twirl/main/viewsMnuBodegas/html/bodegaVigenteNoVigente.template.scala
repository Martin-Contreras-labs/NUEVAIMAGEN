
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

object bodegaVigenteNoVigente extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

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
		"""),_display_(/*13.4*/barraTitulo(mapDiccionario, "CAMBIAR ESTADO (VIGENTE-NO VIGENTE) DE "+mapDiccionario.get("BODEGA")+"S/PROYECTOS", "")),format.raw/*13.121*/("""
		"""),format.raw/*14.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-8 col-md-8 col-lg-8">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>SUCURSAL</TH>
							<TH>"""),_display_(/*20.13*/mapDiccionario/*20.27*/.get("BODEGA")),format.raw/*20.41*/("""/PROYECTO</TH>
							<TH>NOMBRE CLIENTE</TH>
					        <TH>NOMBRE DEL PROYECTO</TH>
							<TH>CAMBIAR ESTADO</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*27.8*/for(lista1 <- listBodegas) yield /*27.34*/{_display_(Seq[Any](format.raw/*27.35*/("""
							"""),format.raw/*28.8*/("""<TR>
								<td  style="text-align:left;">"""),_display_(/*29.40*/lista1/*29.46*/.get(13)),format.raw/*29.54*/("""</td>
								<td style="text-align:left;"><a href="#" onclick="buscaBodega('"""),_display_(/*30.73*/lista1/*30.79*/.get(5)),format.raw/*30.86*/("""')">"""),_display_(/*30.91*/lista1/*30.97*/.get(5)),format.raw/*30.104*/("""</a></td>
								<td style="text-align:left;"><a href="#" onclick="buscaCliente('"""),_display_(/*31.74*/lista1/*31.80*/.get(7)),format.raw/*31.87*/("""')">"""),_display_(/*31.92*/lista1/*31.98*/.get(7)),format.raw/*31.105*/("""</a></td>
								<td style="text-align:left;"><a href="#" onclick="buscaProyecto('"""),_display_(/*32.75*/lista1/*32.81*/.get(8)),format.raw/*32.88*/("""')">"""),_display_(/*32.93*/lista1/*32.99*/.get(8)),format.raw/*32.106*/("""</a></td>
								
								<td style="text-align:center;">
									"""),_display_(if(lista1.get(10).equals("1"))/*35.41*/{_display_(Seq[Any](format.raw/*35.42*/("""
										"""),format.raw/*36.11*/("""<a href="/modificarBodegaEstado/"""),_display_(/*36.44*/lista1/*36.50*/.get(1)),format.raw/*36.57*/(""",0">
											<kbd style="background-color: #73C6B6">VIGENTE</kbd>
										</a>
									""")))}else/*39.15*/{_display_(Seq[Any](format.raw/*39.16*/("""
										"""),format.raw/*40.11*/("""<a href="/modificarBodegaEstado/"""),_display_(/*40.44*/lista1/*40.50*/.get(1)),format.raw/*40.57*/(""",1">
											<kbd style="background-color: red">NO VIGENTE</kbd>
										</a>
									""")))}),format.raw/*43.11*/("""
								"""),format.raw/*44.9*/("""</td>
							</TR>
			 			""")))}),format.raw/*46.9*/("""
					"""),format.raw/*47.6*/("""</tbody>
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
""")))}),format.raw/*59.2*/("""




"""),format.raw/*64.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*65.31*/("""{"""),format.raw/*65.32*/("""
		  """),format.raw/*66.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*66.36*/("""{"""),format.raw/*66.37*/("""
		    	"""),format.raw/*67.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
				"order":[[1,"asc"]],
		    	"language": """),format.raw/*70.20*/("""{"""),format.raw/*70.21*/("""
		        	"""),format.raw/*71.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*72.11*/("""}"""),format.raw/*72.12*/("""
		  """),format.raw/*73.5*/("""}"""),format.raw/*73.6*/(""" """),format.raw/*73.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*75.2*/("""}"""),format.raw/*75.3*/(""");
	
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
                  SOURCE: app/viewsMnuBodegas/bodegaVigenteNoVigente.scala.html
                  HASH: 807a7e55a9c96da5c7f2f758b2248c25fdc85dbc
                  MATRIX: 1040->1|1263->131|1290->133|1306->141|1345->143|1374->147|1442->195|1472->200|1527->235|1555->238|1597->260|1626->263|1670->286|1701->290|1778->341|1917->458|1947->461|2278->765|2301->779|2336->793|2527->958|2569->984|2608->985|2643->993|2714->1037|2729->1043|2758->1051|2863->1129|2878->1135|2906->1142|2938->1147|2953->1153|2982->1160|3092->1243|3107->1249|3135->1256|3167->1261|3182->1267|3211->1274|3322->1358|3337->1364|3365->1371|3397->1376|3412->1382|3441->1389|3567->1488|3606->1489|3645->1500|3705->1533|3720->1539|3748->1546|3865->1644|3904->1645|3943->1656|4003->1689|4018->1695|4046->1702|4170->1795|4206->1804|4263->1831|4296->1837|4662->2173|4694->2178|4784->2240|4813->2241|4845->2246|4904->2277|4933->2278|4968->2286|5135->2425|5164->2426|5204->2438|5310->2516|5339->2517|5371->2522|5399->2523|5427->2524|5527->2597|5555->2598
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|39->8|39->8|40->9|40->9|41->10|41->10|43->12|44->13|44->13|45->14|51->20|51->20|51->20|58->27|58->27|58->27|59->28|60->29|60->29|60->29|61->30|61->30|61->30|61->30|61->30|61->30|62->31|62->31|62->31|62->31|62->31|62->31|63->32|63->32|63->32|63->32|63->32|63->32|66->35|66->35|67->36|67->36|67->36|67->36|70->39|70->39|71->40|71->40|71->40|71->40|74->43|75->44|77->46|78->47|90->59|95->64|96->65|96->65|97->66|97->66|97->66|98->67|101->70|101->70|102->71|103->72|103->72|104->73|104->73|104->73|106->75|106->75
                  -- GENERATED --
              */
          