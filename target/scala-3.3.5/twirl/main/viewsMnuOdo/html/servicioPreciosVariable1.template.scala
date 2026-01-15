
package viewsMnuOdo.html

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

object servicioPreciosVariable1 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,List[tables.ListaPrecioServicio],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
bodega: tables.BodegaEmpresa, listLPServicios: List[tables.ListaPrecioServicio]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""

"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "MANTENCION PRECIOS VARIABLES de SERVICIOS (SELECCIONAR)", mapDiccionario.get("BODEGA")+"/PROYECTO: "+ bodega.getNombre().toUpperCase())),format.raw/*9.168*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-8 col-md-8 col-lg-8">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>FAMILIA</TH>
							<TH>NRO.COTI</TH>
							<TH>CODIGO</TH>
							<TH>SERVICIO</TH>
							<TH>UNIDAD</TH>
							<TH>MONEDA</TH>
							<TH>SELECT</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*25.8*/for(lista2 <- listLPServicios) yield /*25.38*/{_display_(Seq[Any](format.raw/*25.39*/("""
							"""),format.raw/*26.8*/("""<TR>
								<td>"""),_display_(/*27.14*/lista2/*27.20*/.getNomClaseServicio()),format.raw/*27.42*/("""</td>
								<td style="text-align: center">"""),_display_(/*28.41*/lista2/*28.47*/.getNumeroCotiOdo()),format.raw/*28.66*/("""</td>
								<td style="text-align: center">"""),_display_(/*29.41*/lista2/*29.47*/.getCodServicio()),format.raw/*29.64*/("""</td>
								<td>"""),_display_(/*30.14*/lista2/*30.20*/.getNomServicio()),format.raw/*30.37*/("""</td>
								<td style="text-align: center">"""),_display_(/*31.41*/lista2/*31.47*/.getNomUnidad()),format.raw/*31.62*/("""</td>
								<td style="text-align: center">"""),_display_(/*32.41*/lista2/*32.47*/.getNickMoneda()),format.raw/*32.63*/("""</td>
								<td  style="text-align:center;">
									<a href="/servicioPreciosVariable2/"""),_display_(/*34.46*/bodega/*34.52*/.getId()),format.raw/*34.60*/(""","""),_display_(/*34.62*/lista2/*34.68*/.getId_servicio()),format.raw/*34.85*/(""","""),_display_(/*34.87*/lista2/*34.93*/.getId_cotiOdo()),format.raw/*34.109*/("""">
										<kbd style="background-color: #73C6B6">select</kbd>
									</a>
								</td>
							</TR>
			 			""")))}),format.raw/*39.9*/("""
					"""),format.raw/*40.6*/("""</tbody>
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/servicioPreciosVariable0/';">
				</div>
			</div>
		</div>
	</div>
""")))}),format.raw/*52.2*/("""




"""),format.raw/*57.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*58.31*/("""{"""),format.raw/*58.32*/("""
		  """),format.raw/*59.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*59.36*/("""{"""),format.raw/*59.37*/("""
		    	"""),format.raw/*60.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"language": """),format.raw/*62.20*/("""{"""),format.raw/*62.21*/("""
		        	"""),format.raw/*63.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*64.11*/("""}"""),format.raw/*64.12*/("""
		  """),format.raw/*65.5*/("""}"""),format.raw/*65.6*/(""" """),format.raw/*65.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*67.2*/("""}"""),format.raw/*67.3*/(""");
	
</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,bodega:tables.BodegaEmpresa,listLPServicios:List[tables.ListaPrecioServicio]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,bodega,listLPServicios)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,List[tables.ListaPrecioServicio]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,bodega,listLPServicios) => apply(mapDiccionario,mapPermiso,userMnu,bodega,listLPServicios)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuOdo/servicioPreciosVariable1.scala.html
                  HASH: 3f1da3f8e72f9a55d828dd27de71d996b3b282ef
                  MATRIX: 1073->1|1344->179|1372->182|1388->190|1427->192|1456->196|1524->244|1552->246|1628->297|1813->461|1843->464|2348->943|2394->973|2433->974|2468->982|2513->1000|2528->1006|2571->1028|2644->1074|2659->1080|2699->1099|2772->1145|2787->1151|2825->1168|2871->1187|2886->1193|2924->1210|2997->1256|3012->1262|3048->1277|3121->1323|3136->1329|3173->1345|3292->1437|3307->1443|3336->1451|3365->1453|3380->1459|3418->1476|3447->1478|3462->1484|3500->1500|3644->1614|3677->1620|4063->1976|4095->1981|4185->2043|4214->2044|4246->2049|4305->2080|4334->2081|4369->2089|4511->2203|4540->2204|4580->2216|4686->2294|4715->2295|4747->2300|4775->2301|4803->2302|4903->2375|4931->2376
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|56->25|56->25|56->25|57->26|58->27|58->27|58->27|59->28|59->28|59->28|60->29|60->29|60->29|61->30|61->30|61->30|62->31|62->31|62->31|63->32|63->32|63->32|65->34|65->34|65->34|65->34|65->34|65->34|65->34|65->34|65->34|70->39|71->40|83->52|88->57|89->58|89->58|90->59|90->59|90->59|91->60|93->62|93->62|94->63|95->64|95->64|96->65|96->65|96->65|98->67|98->67
                  -- GENERATED --
              */
          