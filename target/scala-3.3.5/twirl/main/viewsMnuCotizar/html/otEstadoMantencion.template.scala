
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

object otEstadoMantencion extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.OtEstado],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listEstados: List[tables.OtEstado]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "ESTADOS "+mapDiccionario.get("Ordenes_de_trabajo").toUpperCase(), "(CREAR/MODIFICAR/ELIMINAR)")),format.raw/*9.128*/("""
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
									<form id="form_"""),_display_(/*25.26*/lista1/*25.32*/.getId()),format.raw/*25.40*/("""" method="post" action="/otEstadoModifica/">
										<input type="hidden" name="id_otEstado" value=""""),_display_(/*26.59*/lista1/*26.65*/.getId()),format.raw/*26.73*/("""">
										<a href="#" onclick='document.getElementById("form_"""),_display_(/*27.63*/lista1/*27.69*/.getId()),format.raw/*27.77*/("""").submit();'>
											<kbd style="background-color: #73C6B6">E</kbd>
										</a>
									</form>
								</td>
								<td  style="text-align:center;">
									"""),_display_(if(!(lista1.getId()==1 || lista1.getId()==2  || lista1.getId()==3))/*33.78*/{_display_(Seq[Any](format.raw/*33.79*/("""
										"""),format.raw/*34.11*/("""<a href="#" onclick= "eliminarOtEstado('"""),_display_(/*34.52*/lista1/*34.58*/.getId()),format.raw/*34.66*/("""')">
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
					<input type="button"  value="AGREGAR" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/otEstadoAgrega/';">
				</div>
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
				</div>
			</div>
		</div>
	</div>
	
	<form id="form_eliminar" method="post" action="/otEstadoElimina/">
		<input type="hidden" id="form_id_otEstado" name="id_otEstado">
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
	const eliminarOtEstado = (id_otEstado) => """),format.raw/*76.44*/("""{"""),format.raw/*76.45*/("""
		"""),format.raw/*77.3*/("""let nombre = $("#"+id_otEstado).text();
		alertify.confirm("Esta seguro de eliminar este estado: "+nombre, function (e) """),format.raw/*78.81*/("""{"""),format.raw/*78.82*/("""
			"""),format.raw/*79.4*/("""if (e) """),format.raw/*79.11*/("""{"""),format.raw/*79.12*/("""
				"""),format.raw/*80.5*/("""$("#form_id_otEstado").val(id_otEstado);
				document.getElementById("form_eliminar").submit();
			"""),format.raw/*82.4*/("""}"""),format.raw/*82.5*/("""
		"""),format.raw/*83.3*/("""}"""),format.raw/*83.4*/(""");
	"""),format.raw/*84.2*/("""}"""),format.raw/*84.3*/("""
"""),format.raw/*85.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listEstados:List[tables.OtEstado]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listEstados)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.OtEstado]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listEstados) => apply(mapDiccionario,mapPermiso,userMnu,listEstados)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizar/otEstadoMantencion.scala.html
                  HASH: cff4f90b239625351184471be5d0cddcf61bf8c6
                  MATRIX: 1039->1|1265->134|1298->142|1314->150|1353->152|1381->155|1449->203|1477->205|1553->256|1698->380|1728->383|2171->800|2213->826|2252->827|2287->835|2367->888|2382->894|2411->902|2441->905|2456->911|2489->923|2594->1001|2609->1007|2638->1015|2768->1118|2783->1124|2812->1132|2904->1197|2919->1203|2948->1211|3212->1448|3251->1449|3290->1460|3358->1501|3373->1507|3402->1515|3530->1599|3566->1608|3623->1635|3656->1641|4375->2330|4407->2335|4497->2397|4526->2398|4558->2403|4617->2434|4646->2435|4681->2443|4823->2557|4852->2558|4892->2570|4998->2648|5027->2649|5059->2654|5087->2655|5115->2656|5215->2729|5243->2730|5317->2776|5346->2777|5376->2780|5524->2900|5553->2901|5584->2905|5619->2912|5648->2913|5680->2918|5806->3017|5834->3018|5864->3021|5892->3022|5923->3026|5951->3027|5979->3028
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|52->21|52->21|52->21|53->22|54->23|54->23|54->23|54->23|54->23|54->23|56->25|56->25|56->25|57->26|57->26|57->26|58->27|58->27|58->27|64->33|64->33|65->34|65->34|65->34|65->34|68->37|69->38|71->40|72->41|91->60|96->65|97->66|97->66|98->67|98->67|98->67|99->68|101->70|101->70|102->71|103->72|103->72|104->73|104->73|104->73|106->75|106->75|107->76|107->76|108->77|109->78|109->78|110->79|110->79|110->79|111->80|113->82|113->82|114->83|114->83|115->84|115->84|116->85
                  -- GENERATED --
              */
          