
package viewsMnuTablas.html

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

object precioMantSelSucursal extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Sucursal],tables.Sucursal,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listSucursal: List[tables.Sucursal], sucursal: tables.Sucursal, esPorSucursal: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalEquipoDescripcion()),format.raw/*9.27*/("""
	
	"""),format.raw/*11.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*12.4*/barraTitulo(mapDiccionario, "ADMINISTRAR EL MAESTRO DE PRECIOS", "(seleccionar sucursal)")),format.raw/*12.94*/("""
		"""),format.raw/*13.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-8 col-sm-4 col-md-4 col-lg-4">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR> 
							<TH style="vertical-align: top;">SUCURSAL</TH>
							<TH style="max-width: 5%;">SELECT</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(if(esPorSucursal.equals("1"))/*23.37*/{_display_(Seq[Any](format.raw/*23.38*/("""
							"""),format.raw/*24.8*/("""<tr class="align-middle">
								<td style="text-align:left;" width="50%">"""),_display_(/*25.51*/sucursal/*25.59*/.getNombre()),format.raw/*25.71*/("""</td>
								<td style="text-align:center;">
									<form id=""""),_display_(/*27.21*/sucursal/*27.29*/.getId),format.raw/*27.35*/("""" method="post" action="/precioMantencion/">
										<input type="hidden" id="sucursal.id" name="id_sucursal" value=""""),_display_(/*28.76*/sucursal/*28.84*/.id),format.raw/*28.87*/("""">
										<a href="#" onclick='$("#"""),_display_(/*29.37*/sucursal/*29.45*/.getId),format.raw/*29.51*/("""").submit();'>
											<kbd style="background-color: #73C6B6">Select</kbd>
										</a>
									</form>
								</td>
							</tr>
						""")))}else/*35.12*/{_display_(Seq[Any](format.raw/*35.13*/("""
							"""),_display_(/*36.9*/for(lista2 <- listSucursal) yield /*36.36*/{_display_(Seq[Any](format.raw/*36.37*/("""
								"""),format.raw/*37.9*/("""<tr class="align-middle">
									<td style="text-align:left;" width="50%">"""),_display_(/*38.52*/lista2/*38.58*/.getNombre()),format.raw/*38.70*/("""</td>
									<td style="text-align:center;">
										<form id=""""),_display_(/*40.22*/lista2/*40.28*/.getId),format.raw/*40.34*/("""" method="post" action="/precioMantencion/">
											<input type="hidden" id="id_sucursal_"""),_display_(/*41.50*/lista2/*41.56*/.getId),format.raw/*41.62*/("""" name="id_sucursal">
											<a href="#" onclick='$("#id_sucursal_"""),_display_(/*42.50*/lista2/*42.56*/.getId),format.raw/*42.62*/("""").val(""""),_display_(/*42.71*/lista2/*42.77*/.getId),format.raw/*42.83*/(""""); $("#"""),_display_(/*42.92*/lista2/*42.98*/.getId),format.raw/*42.104*/("""").submit();'>
												<kbd style="background-color: #73C6B6">Select</kbd>
											</a>
										</form>
									</td>
								</tr>
						 	""")))}),format.raw/*48.10*/("""
						""")))}),format.raw/*49.8*/("""
						
					"""),format.raw/*51.6*/("""</tbody>
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/home/'">
				</div>
			</div>
		</div>
	</div>
""")))}),format.raw/*63.2*/("""




"""),format.raw/*68.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*69.31*/("""{"""),format.raw/*69.32*/("""
		  """),format.raw/*70.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*70.36*/("""{"""),format.raw/*70.37*/("""
		    	"""),format.raw/*71.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 0, "asc" ]],
		    	"language": """),format.raw/*74.20*/("""{"""),format.raw/*74.21*/("""
		        	"""),format.raw/*75.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*76.11*/("""}"""),format.raw/*76.12*/("""
		  """),format.raw/*77.5*/("""}"""),format.raw/*77.6*/(""" """),format.raw/*77.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*79.2*/("""}"""),format.raw/*79.3*/(""");

</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listSucursal:List[tables.Sucursal],sucursal:tables.Sucursal,esPorSucursal:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listSucursal,sucursal,esPorSucursal)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Sucursal],tables.Sucursal,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listSucursal,sucursal,esPorSucursal) => apply(mapDiccionario,mapPermiso,userMnu,listSucursal,sucursal,esPorSucursal)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuTablas/precioMantSelSucursal.scala.html
                  HASH: 5f01974c339234dd9c6558c616f03b8bbc0dc6d7
                  MATRIX: 1064->1|1341->185|1374->193|1390->201|1429->203|1458->207|1526->255|1556->260|1600->284|1631->288|1708->339|1819->429|1849->432|2318->874|2357->875|2392->883|2495->959|2512->967|2545->979|2638->1045|2655->1053|2682->1059|2829->1179|2846->1187|2870->1190|2936->1229|2953->1237|2980->1243|3147->1391|3186->1392|3221->1401|3264->1428|3303->1429|3339->1438|3443->1515|3458->1521|3491->1533|3586->1601|3601->1607|3628->1613|3749->1707|3764->1713|3791->1719|3889->1790|3904->1796|3931->1802|3967->1811|3982->1817|4009->1823|4045->1832|4060->1838|4088->1844|4270->1995|4308->2003|4348->2016|4713->2351|4745->2356|4835->2418|4864->2419|4896->2424|4955->2455|4984->2456|5019->2464|5193->2610|5222->2611|5262->2623|5368->2701|5397->2702|5429->2707|5457->2708|5485->2709|5585->2782|5613->2783
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|42->11|43->12|43->12|44->13|54->23|54->23|55->24|56->25|56->25|56->25|58->27|58->27|58->27|59->28|59->28|59->28|60->29|60->29|60->29|66->35|66->35|67->36|67->36|67->36|68->37|69->38|69->38|69->38|71->40|71->40|71->40|72->41|72->41|72->41|73->42|73->42|73->42|73->42|73->42|73->42|73->42|73->42|73->42|79->48|80->49|82->51|94->63|99->68|100->69|100->69|101->70|101->70|101->70|102->71|105->74|105->74|106->75|107->76|107->76|108->77|108->77|108->77|110->79|110->79
                  -- GENERATED --
              */
          