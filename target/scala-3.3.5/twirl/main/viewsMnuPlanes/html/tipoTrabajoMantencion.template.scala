
package viewsMnuPlanes.html

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

object tipoTrabajoMantencion extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.TipoTrabajo],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listTipos: List[tables.TipoTrabajo]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "MANTENCION Y CREACION DE TIPOS DE TRABAJO","CREAR/MODIFICAR/ELIMINAR")),format.raw/*9.103*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR> 
							<TH style="vertical-align: top;">TIPO TRABAJO</TH>
							<TH>EDIT</TH>
							<TH>DEL</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*21.8*/for(lista1 <- listTipos) yield /*21.32*/{_display_(Seq[Any](format.raw/*21.33*/("""
							"""),format.raw/*22.8*/("""<TR>
								<td  style="text-align:left;"><div id=""""),_display_(/*23.49*/lista1/*23.55*/.getId()),format.raw/*23.63*/("""">"""),_display_(/*23.66*/lista1/*23.72*/.getNombre()),format.raw/*23.84*/("""</div></td>
								<td  style="text-align:center;">
									<form id="form_"""),_display_(/*25.26*/lista1/*25.32*/.getId()),format.raw/*25.40*/("""" method="post" action="/tipoTrabajoModifica/">
										<input type="hidden" name="id_tipo" value=""""),_display_(/*26.55*/lista1/*26.61*/.getId()),format.raw/*26.69*/("""">
										<a href="#" onclick='document.getElementById("form_"""),_display_(/*27.63*/lista1/*27.69*/.getId()),format.raw/*27.77*/("""").submit();'>
											<kbd style="background-color: #73C6B6">E</kbd>
										</a>
									</form>
								</td>
								<td  style="text-align:center;">
									<a href="#" onclick= "eliminarTipo('"""),_display_(/*33.47*/lista1/*33.53*/.getId()),format.raw/*33.61*/("""')">
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
					<input type="button"  value="AGREGAR" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/tipoTrabajoAgrega/';">
				</div>
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
				</div>
			</div>
		</div>
	</div>
	
	<form id="form_eliminar" method="post" action="/tipoTrabajoElimina/">
		<input type="hidden" id="form_id_tipo" name="id_tipo">
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
	const eliminarTipo = (id_tipo) => """),format.raw/*74.36*/("""{"""),format.raw/*74.37*/("""
		"""),format.raw/*75.3*/("""let nombre = $("#"+id_tipo).text();
		alertify.confirm("Esta seguro de eliminar este tipo: "+nombre, function (e) """),format.raw/*76.79*/("""{"""),format.raw/*76.80*/("""
			"""),format.raw/*77.4*/("""if (e) """),format.raw/*77.11*/("""{"""),format.raw/*77.12*/("""
				"""),format.raw/*78.5*/("""$("#form_id_tipo").val(id_tipo);
				document.getElementById("form_eliminar").submit();
			"""),format.raw/*80.4*/("""}"""),format.raw/*80.5*/("""
		"""),format.raw/*81.3*/("""}"""),format.raw/*81.4*/(""");
	"""),format.raw/*82.2*/("""}"""),format.raw/*82.3*/("""
"""),format.raw/*83.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listTipos:List[tables.TipoTrabajo]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listTipos)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.TipoTrabajo]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listTipos) => apply(mapDiccionario,mapPermiso,userMnu,listTipos)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuPlanes/tipoTrabajoMantencion.scala.html
                  HASH: 94d61eda80e6873f9074d426864b0cf6a30eee53
                  MATRIX: 1044->1|1271->135|1304->143|1320->151|1359->153|1387->156|1455->204|1483->206|1559->257|1679->356|1709->359|2148->772|2188->796|2227->797|2262->805|2342->858|2357->864|2386->872|2416->875|2431->881|2464->893|2569->971|2584->977|2613->985|2742->1087|2757->1093|2786->1101|2878->1166|2893->1172|2922->1180|3155->1386|3170->1392|3199->1400|3336->1507|3369->1513|4086->2200|4118->2205|4208->2267|4237->2268|4269->2273|4328->2304|4357->2305|4392->2313|4534->2427|4563->2428|4603->2440|4709->2518|4738->2519|4770->2524|4798->2525|4826->2526|4926->2599|4954->2600|5020->2638|5049->2639|5079->2642|5221->2756|5250->2757|5281->2761|5316->2768|5345->2769|5377->2774|5495->2865|5523->2866|5553->2869|5581->2870|5612->2874|5640->2875|5668->2876
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|52->21|52->21|52->21|53->22|54->23|54->23|54->23|54->23|54->23|54->23|56->25|56->25|56->25|57->26|57->26|57->26|58->27|58->27|58->27|64->33|64->33|64->33|69->38|70->39|89->58|94->63|95->64|95->64|96->65|96->65|96->65|97->66|99->68|99->68|100->69|101->70|101->70|102->71|102->71|102->71|104->73|104->73|105->74|105->74|106->75|107->76|107->76|108->77|108->77|108->77|109->78|111->80|111->80|112->81|112->81|113->82|113->82|114->83
                  -- GENERATED --
              */
          