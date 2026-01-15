
package viewsMnuMantencion.html

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

object mantTblTipoActividadMant extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.MantTipoActividad],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listTipoActividad: List[tables.MantTipoActividad]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "TIPOS DE ACTIVIDAD: CREAR/MODIFICAR/ELIMINAR", "")),format.raw/*9.83*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR> 
							<TH style="vertical-align: top;">NOMBRE TIPO DE ACTIVIDAD</TH>
							<TH>EDIT</TH>
							<TH>DEL</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*21.8*/for(lista1 <- listTipoActividad) yield /*21.40*/{_display_(Seq[Any](format.raw/*21.41*/("""
							"""),format.raw/*22.8*/("""<TR>
								<td  style="text-align:left;"><div id=""""),_display_(/*23.49*/lista1/*23.55*/.getId()),format.raw/*23.63*/("""">"""),_display_(/*23.66*/lista1/*23.72*/.getNombre()),format.raw/*23.84*/("""</div></td>
								<td  style="text-align:center;">
									<form id="form_"""),_display_(/*25.26*/lista1/*25.32*/.getId()),format.raw/*25.40*/("""" method="post" action="/routes3/mantTblTipoActividadUpdate/">
										<input type="hidden" name="id_tipoActividad" value=""""),_display_(/*26.64*/lista1/*26.70*/.getId()),format.raw/*26.78*/("""">
										<a href="#" onclick='document.getElementById("form_"""),_display_(/*27.63*/lista1/*27.69*/.getId()),format.raw/*27.77*/("""").submit();'>
											<kbd style="background-color: #73C6B6">E</kbd>
										</a>
									</form>
								</td>
								<td  style="text-align:center;">
									<a href="#" onclick= "eliminarTipoActividad('"""),_display_(/*33.56*/lista1/*33.62*/.getId()),format.raw/*33.70*/("""')">
										<kbd style="background-color: red">X</kbd>
									</a>
								</td>
							</TR>
			 			""")))}),format.raw/*38.9*/("""
					"""),format.raw/*39.6*/("""</tbody>
				</table>
			</div>
		</div>	
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="AGREGAR" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/routes3/mantTblTipoActividadNew/';">
				</div>
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
				</div>
			</div>
		</div>
	</div>
	
	<form id="form_eliminar" method="post" action="/routes3/mantTblTipoActividadDel/">
		<input type="hidden" id="form_id_tipoActividad" name="id_tipoActividad">
	</form>
""")))}),format.raw/*58.2*/("""




"""),format.raw/*63.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*64.31*/("""{"""),format.raw/*64.32*/("""
		  """),format.raw/*65.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*65.36*/("""{"""),format.raw/*65.37*/("""
		    	"""),format.raw/*66.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"language": """),format.raw/*68.20*/("""{"""),format.raw/*68.21*/("""
		        	"""),format.raw/*69.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*70.11*/("""}"""),format.raw/*70.12*/("""
		  """),format.raw/*71.5*/("""}"""),format.raw/*71.6*/(""" """),format.raw/*71.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*73.2*/("""}"""),format.raw/*73.3*/(""");
	const eliminarTipoActividad = (id_tipoActividad) => """),format.raw/*74.54*/("""{"""),format.raw/*74.55*/("""
		"""),format.raw/*75.3*/("""let nombre = $("#"+id_tipoActividad).text();
		alertify.confirm("Esta seguro de eliminar el tipo de actividad: "+nombre, function (e) """),format.raw/*76.90*/("""{"""),format.raw/*76.91*/("""
			"""),format.raw/*77.4*/("""if (e) """),format.raw/*77.11*/("""{"""),format.raw/*77.12*/("""
				"""),format.raw/*78.5*/("""$("#form_id_tipoActividad").val(id_tipoActividad);
				document.getElementById("form_eliminar").submit();
			"""),format.raw/*80.4*/("""}"""),format.raw/*80.5*/("""
		"""),format.raw/*81.3*/("""}"""),format.raw/*81.4*/(""");
	"""),format.raw/*82.2*/("""}"""),format.raw/*82.3*/("""
"""),format.raw/*83.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listTipoActividad:List[tables.MantTipoActividad]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listTipoActividad)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.MantTipoActividad]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listTipoActividad) => apply(mapDiccionario,mapPermiso,userMnu,listTipoActividad)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuMantencion/mantTblTipoActividadMant.scala.html
                  HASH: 3dc9569fcb2a757c874162e5d0c6dcf1cb4e6a22
                  MATRIX: 1057->1|1298->149|1331->157|1347->165|1386->167|1414->170|1482->218|1510->220|1586->271|1685->350|1715->353|2166->778|2214->810|2253->811|2288->819|2368->872|2383->878|2412->886|2442->889|2457->895|2490->907|2595->985|2610->991|2639->999|2792->1125|2807->1131|2836->1139|2928->1204|2943->1210|2972->1218|3214->1433|3229->1439|3258->1447|3395->1554|3428->1560|4190->2292|4222->2297|4312->2359|4341->2360|4373->2365|4432->2396|4461->2397|4496->2405|4638->2519|4667->2520|4707->2532|4813->2610|4842->2611|4874->2616|4902->2617|4930->2618|5030->2691|5058->2692|5142->2748|5171->2749|5201->2752|5363->2886|5392->2887|5423->2891|5458->2898|5487->2899|5519->2904|5655->3013|5683->3014|5713->3017|5741->3018|5772->3022|5800->3023|5828->3024
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|52->21|52->21|52->21|53->22|54->23|54->23|54->23|54->23|54->23|54->23|56->25|56->25|56->25|57->26|57->26|57->26|58->27|58->27|58->27|64->33|64->33|64->33|69->38|70->39|89->58|94->63|95->64|95->64|96->65|96->65|96->65|97->66|99->68|99->68|100->69|101->70|101->70|102->71|102->71|102->71|104->73|104->73|105->74|105->74|106->75|107->76|107->76|108->77|108->77|108->77|109->78|111->80|111->80|112->81|112->81|113->82|113->82|114->83
                  -- GENERATED --
              */
          