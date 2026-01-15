
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

object claseMantencion extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.ClaseServicio],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listClases: List[tables.ClaseServicio]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "FAMILIAS DE SERVICIO (CREAR/MODIFICAR/ELIMINAR)", "")),format.raw/*9.86*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR> 
							<TH style="vertical-align: top;">NOMBRE FAMILIA SERVICIO</TH>
							<TH>EDIT</TH>
							<TH>DEL</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*21.8*/for(lista1 <- listClases) yield /*21.33*/{_display_(Seq[Any](format.raw/*21.34*/("""
							"""),format.raw/*22.8*/("""<TR>
								<td  style="text-align:left;"><div id=""""),_display_(/*23.49*/lista1/*23.55*/.getId()),format.raw/*23.63*/("""">"""),_display_(/*23.66*/lista1/*23.72*/.getNombre()),format.raw/*23.84*/("""</div></td>
								<td  style="text-align:center;">
									<form id="form_"""),_display_(/*25.26*/lista1/*25.32*/.getId()),format.raw/*25.40*/("""" method="post" action="/claseModifica/">
										<input type="hidden" name="id_clase" value=""""),_display_(/*26.56*/lista1/*26.62*/.getId()),format.raw/*26.70*/("""">
										<a href="#" onclick='document.getElementById("form_"""),_display_(/*27.63*/lista1/*27.69*/.getId()),format.raw/*27.77*/("""").submit();'>
											<kbd style="background-color: #73C6B6">E</kbd>
										</a>
									</form>
								</td>
								<td  style="text-align:center;">
									"""),_display_(if(lista1.getId()!=1)/*33.32*/{_display_(Seq[Any](format.raw/*33.33*/("""
										"""),format.raw/*34.11*/("""<a href="#" onclick= "eliminarClase('"""),_display_(/*34.49*/lista1/*34.55*/.getId()),format.raw/*34.63*/("""')">
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
					<input type="button"  value="AGREGAR" class="btn btn-info btn-sm rounded-pill btn-block" onclick="location.href='/claseAgrega/';">
				</div>
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
				</div>
			</div>
		</div>
	</div>
	
	<form id="form_eliminar" method="post" action="/claseElimina/">
		<input type="hidden" id="form_id_clase" name="id_clase">
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
	const eliminarClase = (id_clase) => """),format.raw/*76.38*/("""{"""),format.raw/*76.39*/("""
		"""),format.raw/*77.3*/("""let nombre = $("#"+id_clase).text();
		alertify.confirm("Esta seguro de eliminar la familia: "+nombre, function (e) """),format.raw/*78.80*/("""{"""),format.raw/*78.81*/("""
			"""),format.raw/*79.4*/("""if (e) """),format.raw/*79.11*/("""{"""),format.raw/*79.12*/("""
				"""),format.raw/*80.5*/("""$("#form_id_clase").val(id_clase);
				document.getElementById("form_eliminar").submit();
			"""),format.raw/*82.4*/("""}"""),format.raw/*82.5*/("""
		"""),format.raw/*83.3*/("""}"""),format.raw/*83.4*/(""");
	"""),format.raw/*84.2*/("""}"""),format.raw/*84.3*/("""
"""),format.raw/*85.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listClases:List[tables.ClaseServicio]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listClases)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.ClaseServicio]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listClases) => apply(mapDiccionario,mapPermiso,userMnu,listClases)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuOdo/claseMantencion.scala.html
                  HASH: c7c483fccde3eec8dd6eabdb51ff0417a199af3d
                  MATRIX: 1037->1|1267->138|1300->146|1316->154|1355->156|1383->159|1451->207|1479->209|1555->260|1657->342|1687->345|2137->769|2178->794|2217->795|2252->803|2332->856|2347->862|2376->870|2406->873|2421->879|2454->891|2559->969|2574->975|2603->983|2727->1080|2742->1086|2771->1094|2863->1159|2878->1165|2907->1173|3125->1364|3164->1365|3203->1376|3268->1414|3283->1420|3312->1428|3440->1512|3476->1521|3533->1548|3566->1554|4269->2227|4301->2232|4391->2294|4420->2295|4452->2300|4511->2331|4540->2332|4575->2340|4717->2454|4746->2455|4786->2467|4892->2545|4921->2546|4953->2551|4981->2552|5009->2553|5109->2626|5137->2627|5205->2667|5234->2668|5264->2671|5408->2787|5437->2788|5468->2792|5503->2799|5532->2800|5564->2805|5684->2898|5712->2899|5742->2902|5770->2903|5801->2907|5829->2908|5857->2909
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|52->21|52->21|52->21|53->22|54->23|54->23|54->23|54->23|54->23|54->23|56->25|56->25|56->25|57->26|57->26|57->26|58->27|58->27|58->27|64->33|64->33|65->34|65->34|65->34|65->34|68->37|69->38|71->40|72->41|91->60|96->65|97->66|97->66|98->67|98->67|98->67|99->68|101->70|101->70|102->71|103->72|103->72|104->73|104->73|104->73|106->75|106->75|107->76|107->76|108->77|109->78|109->78|110->79|110->79|110->79|111->80|113->82|113->82|114->83|114->83|115->84|115->84|116->85
                  -- GENERATED --
              */
          