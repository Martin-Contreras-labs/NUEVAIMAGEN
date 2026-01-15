
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

object cotizaEstadoMantencion extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.CotizaEstado],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listEstados: List[tables.CotizaEstado]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "ESTADOS COTIZACIONES", "(CREAR/MODIFICAR/ELIMINAR)")),format.raw/*9.85*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR> 
							<TH style="vertical-align: top;">NOMBRE DE ESTADO</TH>
							<TH>EDIT</TH>
							<TH>DEL</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*21.8*/for(lista1 <- listEstados) yield /*21.34*/{_display_(Seq[Any](format.raw/*21.35*/("""
							"""),format.raw/*22.8*/("""<TR>
								<td  style="text-align:left;"><div id=""""),_display_(/*23.49*/lista1/*23.55*/.getId()),format.raw/*23.63*/("""">"""),_display_(/*23.66*/lista1/*23.72*/.getEstado()),format.raw/*23.84*/("""</div></td>
								<td  style="text-align:center;">
									<form id="form_"""),_display_(/*25.26*/lista1/*25.32*/.getId()),format.raw/*25.40*/("""" method="post" action="/cotizaEstadoModifica/">
										<input type="hidden" name="id_cotizaEstado" value=""""),_display_(/*26.63*/lista1/*26.69*/.getId()),format.raw/*26.77*/("""">
										<a href="#" onclick='document.getElementById("form_"""),_display_(/*27.63*/lista1/*27.69*/.getId()),format.raw/*27.77*/("""").submit();'>
											<kbd style="background-color: #73C6B6">E</kbd>
										</a>
									</form>
								</td>
								<td  style="text-align:center;">
									"""),_display_(if(lista1.getId() > 1)/*33.33*/{_display_(Seq[Any](format.raw/*33.34*/("""
										"""),format.raw/*34.11*/("""<a href="#" onclick= "eliminarCotizaEstado('"""),_display_(/*34.56*/lista1/*34.62*/.getId()),format.raw/*34.70*/("""')">
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
					<input type="button"  value="AGREGAR" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/cotizaEstadoAgrega/';">
				</div>
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
				</div>
			</div>
		</div>
	</div>
	
	<form id="form_eliminar" method="post" action="/cotizaEstadoElimina/">
		<input type="hidden" id="form_id_cotizaEstado" name="id_cotizaEstado">
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
	const eliminarCotizaEstado = (id_cotizaEstado) => """),format.raw/*76.52*/("""{"""),format.raw/*76.53*/("""
		"""),format.raw/*77.3*/("""let nombre = $("#"+id_cotizaEstado).text();
		alertify.confirm("Esta seguro de eliminar este estado: "+nombre, function (e) """),format.raw/*78.81*/("""{"""),format.raw/*78.82*/("""
			"""),format.raw/*79.4*/("""if (e) """),format.raw/*79.11*/("""{"""),format.raw/*79.12*/("""
				"""),format.raw/*80.5*/("""$("#form_id_cotizaEstado").val(id_cotizaEstado);
				document.getElementById("form_eliminar").submit();
			"""),format.raw/*82.4*/("""}"""),format.raw/*82.5*/("""
		"""),format.raw/*83.3*/("""}"""),format.raw/*83.4*/(""");
	"""),format.raw/*84.2*/("""}"""),format.raw/*84.3*/("""
"""),format.raw/*85.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listEstados:List[tables.CotizaEstado]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listEstados)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.CotizaEstado]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listEstados) => apply(mapDiccionario,mapPermiso,userMnu,listEstados)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizar/cotizaEstadoMantencion.scala.html
                  HASH: cc09c0d917114c703bbf67f0c91a40d749f1d0d5
                  MATRIX: 1047->1|1277->138|1310->146|1326->154|1365->156|1393->159|1461->207|1489->209|1565->260|1666->341|1696->344|2139->761|2181->787|2220->788|2255->796|2335->849|2350->855|2379->863|2409->866|2424->872|2457->884|2562->962|2577->968|2606->976|2744->1087|2759->1093|2788->1101|2880->1166|2895->1172|2924->1180|3143->1372|3182->1373|3221->1384|3293->1429|3308->1435|3337->1443|3465->1527|3501->1536|3558->1563|3591->1569|4326->2274|4358->2279|4448->2341|4477->2342|4509->2347|4568->2378|4597->2379|4632->2387|4774->2501|4803->2502|4843->2514|4949->2592|4978->2593|5010->2598|5038->2599|5066->2600|5166->2673|5194->2674|5276->2728|5305->2729|5335->2732|5487->2856|5516->2857|5547->2861|5582->2868|5611->2869|5643->2874|5777->2981|5805->2982|5835->2985|5863->2986|5894->2990|5922->2991|5950->2992
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|52->21|52->21|52->21|53->22|54->23|54->23|54->23|54->23|54->23|54->23|56->25|56->25|56->25|57->26|57->26|57->26|58->27|58->27|58->27|64->33|64->33|65->34|65->34|65->34|65->34|68->37|69->38|71->40|72->41|91->60|96->65|97->66|97->66|98->67|98->67|98->67|99->68|101->70|101->70|102->71|103->72|103->72|104->73|104->73|104->73|106->75|106->75|107->76|107->76|108->77|109->78|109->78|110->79|110->79|110->79|111->80|113->82|113->82|114->83|114->83|115->84|115->84|116->85
                  -- GENERATED --
              */
          