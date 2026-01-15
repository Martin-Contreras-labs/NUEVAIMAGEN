
package viewsMnuCotizar.html

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

object cotizaSolucionMantencion extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.CotizaSolucion],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listSoluciones: List[tables.CotizaSolucion]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "TIPOS DE SOLUCIONES", "(CREAR/MODIFICAR/ELIMINAR)")),format.raw/*9.84*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR> 
							<TH style="vertical-align: top;">NOMBRE DE SOLUCION</TH>
							<TH>EDIT</TH>
							<TH>DEL</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*21.8*/for(lista1 <- listSoluciones) yield /*21.37*/{_display_(Seq[Any](format.raw/*21.38*/("""
							"""),format.raw/*22.8*/("""<TR>
								<td  style="text-align:left;"><div id=""""),_display_(/*23.49*/lista1/*23.55*/.getId()),format.raw/*23.63*/("""">"""),_display_(/*23.66*/lista1/*23.72*/.getSolucion()),format.raw/*23.86*/("""</div></td>
								<td  style="text-align:center;">
									<form id="form_"""),_display_(/*25.26*/lista1/*25.32*/.getId()),format.raw/*25.40*/("""" method="post" action="/routes2/cotizaSolucionModifica/">
										<input type="hidden" name="id_cotizaSolucion" value=""""),_display_(/*26.65*/lista1/*26.71*/.getId()),format.raw/*26.79*/("""">
										<a href="#" onclick='document.getElementById("form_"""),_display_(/*27.63*/lista1/*27.69*/.getId()),format.raw/*27.77*/("""").submit();'>
											<kbd style="background-color: #73C6B6">E</kbd>
										</a>
									</form>
								</td>
								<td  style="text-align:center;">
									"""),_display_(if(lista1.getId() > 1)/*33.33*/{_display_(Seq[Any](format.raw/*33.34*/("""
										"""),format.raw/*34.11*/("""<a href="#" onclick= "eliminarCotizaSolucion('"""),_display_(/*34.58*/lista1/*34.64*/.getId()),format.raw/*34.72*/("""')">
											<kbd style="background-color: red">X</kbd>
										</a>
									""")))} else {null} ),format.raw/*37.11*/("""
								"""),format.raw/*38.9*/("""</td>
							</TR>
			 			""")))}),format.raw/*40.9*/("""
					"""),format.raw/*41.6*/("""</tbody>
				</table>
			</div>
		</div>	
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="AGREGAR" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/routes2/cotizaSolucionAgrega/';">
				</div>
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
				</div>
			</div>
		</div>
	</div>
	
	<form id="form_eliminar" method="post" action="/routes2/cotizaSolucionElimina/">
		<input type="hidden" id="form_id_cotizaSolucion" name="id_cotizaSolucion">
	</form>
""")))}),format.raw/*60.2*/("""




"""),format.raw/*65.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*66.31*/("""{"""),format.raw/*66.32*/("""
		  """),format.raw/*67.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*67.36*/("""{"""),format.raw/*67.37*/("""
		    	"""),format.raw/*68.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"language": """),format.raw/*70.20*/("""{"""),format.raw/*70.21*/("""
		        	"""),format.raw/*71.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*72.11*/("""}"""),format.raw/*72.12*/("""
		  """),format.raw/*73.5*/("""}"""),format.raw/*73.6*/(""" """),format.raw/*73.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*75.2*/("""}"""),format.raw/*75.3*/(""");
	const eliminarCotizaSolucion = (id_cotizaSolucion) => """),format.raw/*76.56*/("""{"""),format.raw/*76.57*/("""
		"""),format.raw/*77.3*/("""let nombre = $("#"+id_cotizaSolucion).text();
		alertify.confirm("Esta seguro de eliminar esta solucion: "+nombre, function (e) """),format.raw/*78.83*/("""{"""),format.raw/*78.84*/("""
			"""),format.raw/*79.4*/("""if (e) """),format.raw/*79.11*/("""{"""),format.raw/*79.12*/("""
				"""),format.raw/*80.5*/("""$("#form_id_cotizaSolucion").val(id_cotizaSolucion);
				document.getElementById("form_eliminar").submit();
			"""),format.raw/*82.4*/("""}"""),format.raw/*82.5*/("""
		"""),format.raw/*83.3*/("""}"""),format.raw/*83.4*/(""");
	"""),format.raw/*84.2*/("""}"""),format.raw/*84.3*/("""
"""),format.raw/*85.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listSoluciones:List[tables.CotizaSolucion]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listSoluciones)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.CotizaSolucion]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listSoluciones) => apply(mapDiccionario,mapPermiso,userMnu,listSoluciones)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizar/cotizaSolucionMantencion.scala.html
                  HASH: cf518ed3cc4c49d53b1f58e3e02be2ad9d8921e2
                  MATRIX: 1051->1|1286->143|1319->151|1335->159|1374->161|1402->164|1470->212|1498->214|1574->265|1674->345|1704->348|2149->767|2194->796|2233->797|2268->805|2348->858|2363->864|2392->872|2422->875|2437->881|2472->895|2577->973|2592->979|2621->987|2771->1110|2786->1116|2815->1124|2907->1189|2922->1195|2951->1203|3170->1395|3209->1396|3248->1407|3322->1454|3337->1460|3366->1468|3494->1552|3530->1561|3587->1588|3620->1594|4379->2323|4411->2328|4501->2390|4530->2391|4562->2396|4621->2427|4650->2428|4685->2436|4827->2550|4856->2551|4896->2563|5002->2641|5031->2642|5063->2647|5091->2648|5119->2649|5219->2722|5247->2723|5333->2781|5362->2782|5392->2785|5548->2913|5577->2914|5608->2918|5643->2925|5672->2926|5704->2931|5842->3042|5870->3043|5900->3046|5928->3047|5959->3051|5987->3052|6015->3053
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|52->21|52->21|52->21|53->22|54->23|54->23|54->23|54->23|54->23|54->23|56->25|56->25|56->25|57->26|57->26|57->26|58->27|58->27|58->27|64->33|64->33|65->34|65->34|65->34|65->34|68->37|69->38|71->40|72->41|91->60|96->65|97->66|97->66|98->67|98->67|98->67|99->68|101->70|101->70|102->71|103->72|103->72|104->73|104->73|104->73|106->75|106->75|107->76|107->76|108->77|109->78|109->78|110->79|110->79|110->79|111->80|113->82|113->82|114->83|114->83|115->84|115->84|116->85
                  -- GENERATED --
              */
          