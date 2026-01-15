
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

object mantTblEstadoEnTallerMant extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.MantEstadoEnTaller],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listEstados: List[tables.MantEstadoEnTaller]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "ESTADOS EN TALLER: CREAR/MODIFICAR/ELIMINAR", "")),format.raw/*9.82*/("""
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
								<td  style="text-align:left;"><div id=""""),_display_(/*23.49*/lista1/*23.55*/.getId()),format.raw/*23.63*/("""">"""),_display_(/*23.66*/lista1/*23.72*/.getNombre()),format.raw/*23.84*/("""</div></td>
								<td  style="text-align:center;">
									<form id="form_"""),_display_(/*25.26*/lista1/*25.32*/.getId()),format.raw/*25.40*/("""" method="post" action="/routes3/mantTblEstadoEnTallerUpdate/">
										<input type="hidden" name="id_estado" value=""""),_display_(/*26.57*/lista1/*26.63*/.getId()),format.raw/*26.71*/("""">
										<a href="#" onclick='document.getElementById("form_"""),_display_(/*27.63*/lista1/*27.69*/.getId()),format.raw/*27.77*/("""").submit();'>
											<kbd style="background-color: #73C6B6">E</kbd>
										</a>
									</form>
								</td>
								<td  style="text-align:center;">
									<a href="#" onclick= "eliminarEstado('"""),_display_(/*33.49*/lista1/*33.55*/.getId()),format.raw/*33.63*/("""')">
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
					<input type="button"  value="AGREGAR" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/routes3/mantTblEstadoEnTallerNew/';">
				</div>
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
				</div>
			</div>
		</div>
	</div>
	
	<form id="form_eliminar" method="post" action="/routes3/mantTblEstadoEnTallerDel/">
		<input type="hidden" id="form_id_estado" name="id_estado">
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
	const eliminarEstado = (id_estado) => """),format.raw/*74.40*/("""{"""),format.raw/*74.41*/("""
		"""),format.raw/*75.3*/("""let nombre = $("#"+id_estado).text();
		alertify.confirm("Esta seguro de eliminar el estado: "+nombre, function (e) """),format.raw/*76.79*/("""{"""),format.raw/*76.80*/("""
			"""),format.raw/*77.4*/("""if (e) """),format.raw/*77.11*/("""{"""),format.raw/*77.12*/("""
				"""),format.raw/*78.5*/("""$("#form_id_estado").val(id_estado);
				document.getElementById("form_eliminar").submit();
			"""),format.raw/*80.4*/("""}"""),format.raw/*80.5*/("""
		"""),format.raw/*81.3*/("""}"""),format.raw/*81.4*/(""");
	"""),format.raw/*82.2*/("""}"""),format.raw/*82.3*/("""
"""),format.raw/*83.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listEstados:List[tables.MantEstadoEnTaller]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listEstados)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.MantEstadoEnTaller]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listEstados) => apply(mapDiccionario,mapPermiso,userMnu,listEstados)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuMantencion/mantTblEstadoEnTallerMant.scala.html
                  HASH: 68c2e3da0c83dfed42d7ad4ad322c0e545a5d12d
                  MATRIX: 1059->1|1295->144|1328->152|1344->160|1383->162|1411->165|1479->213|1507->215|1583->266|1681->344|1711->347|2154->764|2196->790|2235->791|2270->799|2350->852|2365->858|2394->866|2424->869|2439->875|2472->887|2577->965|2592->971|2621->979|2768->1099|2783->1105|2812->1113|2904->1178|2919->1184|2948->1192|3183->1400|3198->1406|3227->1414|3364->1521|3397->1527|4147->2247|4179->2252|4269->2314|4298->2315|4330->2320|4389->2351|4418->2352|4453->2360|4595->2474|4624->2475|4664->2487|4770->2565|4799->2566|4831->2571|4859->2572|4887->2573|4987->2646|5015->2647|5085->2689|5114->2690|5144->2693|5288->2809|5317->2810|5348->2814|5383->2821|5412->2822|5444->2827|5566->2922|5594->2923|5624->2926|5652->2927|5683->2931|5711->2932|5739->2933
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|52->21|52->21|52->21|53->22|54->23|54->23|54->23|54->23|54->23|54->23|56->25|56->25|56->25|57->26|57->26|57->26|58->27|58->27|58->27|64->33|64->33|64->33|69->38|70->39|89->58|94->63|95->64|95->64|96->65|96->65|96->65|97->66|99->68|99->68|100->69|101->70|101->70|102->71|102->71|102->71|104->73|104->73|105->74|105->74|106->75|107->76|107->76|108->77|108->77|108->77|109->78|111->80|111->80|112->81|112->81|113->82|113->82|114->83
                  -- GENERATED --
              */
          