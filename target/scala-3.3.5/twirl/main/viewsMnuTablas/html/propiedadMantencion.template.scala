
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

object propiedadMantencion extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Propiedad],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listPropiedad: List[tables.Propiedad]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "DPROPIEDAD EQUIPO: CREAR/MODIFICAR", "")),format.raw/*9.73*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR> 
							<TH>NOMBRE</TH>
							<TH style="width: 10px">EDIT</TH>
							<TH>DEL</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*21.8*/for(lista1 <- listPropiedad) yield /*21.36*/{_display_(Seq[Any](format.raw/*21.37*/("""
							"""),format.raw/*22.8*/("""<TR>
								<td  style="text-align:left;">"""),_display_(/*23.40*/lista1/*23.46*/.getNombre()),format.raw/*23.58*/("""</td>
								<td  style="text-align:center;">
									<a href="#" onclick="propiedadModifica('"""),_display_(/*25.51*/lista1/*25.57*/.getId()),format.raw/*25.65*/("""');">
										<kbd style="background-color: #73C6B6">E</kbd>
									</a>
								</td>
								<td  style="text-align:center;">
									<a href="#" onclick= "eliminarPropiedad('"""),_display_(/*30.52*/lista1/*30.58*/.getId()),format.raw/*30.66*/("""')">
										<kbd style="background-color: red">X</kbd>
									</a>
								</td>
							</TR>
			 			""")))}),format.raw/*35.9*/("""
					"""),format.raw/*36.6*/("""</tbody>
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="AGREGAR" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/routes2/propiedadAgrega/';">
				</div>
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
				</div>
			</div>
		</div>
	</div>
	
	<form id="propiedadModifica" method="post" action="/routes2/propiedadModifica/">
		<input type="hidden" id="mod_idPropiedad" name="id_propiedad">
	</form>

	<form id="form_eliminar" method="post" action="/routes2/propiedadElimina/">
		<input type="hidden" id="form_id_propiedad" name="id_propiedad">
	</form>
""")))}),format.raw/*59.2*/("""

"""),format.raw/*61.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*62.31*/("""{"""),format.raw/*62.32*/("""
		  """),format.raw/*63.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*63.36*/("""{"""),format.raw/*63.37*/("""
		    	"""),format.raw/*64.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"language": """),format.raw/*66.20*/("""{"""),format.raw/*66.21*/("""
		        	"""),format.raw/*67.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*68.11*/("""}"""),format.raw/*68.12*/("""
		  """),format.raw/*69.5*/("""}"""),format.raw/*69.6*/(""" """),format.raw/*69.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*71.2*/("""}"""),format.raw/*71.3*/(""");
	
	const propiedadModifica = (id_propiedad) => """),format.raw/*73.46*/("""{"""),format.raw/*73.47*/("""
		"""),format.raw/*74.3*/("""let nombre = $("#"+id_propiedad).text();
				$("#mod_idPropiedad").val(id_propiedad);
				document.getElementById("propiedadModifica").submit();
	"""),format.raw/*77.2*/("""}"""),format.raw/*77.3*/("""

	"""),format.raw/*79.2*/("""const eliminarPropiedad = (id_propiedad) => """),format.raw/*79.46*/("""{"""),format.raw/*79.47*/("""
		"""),format.raw/*80.3*/("""let nombre = $("#"+id_propiedad).text();
		alertify.confirm("Esta seguro de eliminar esta propiedad: "+nombre, function (e) """),format.raw/*81.84*/("""{"""),format.raw/*81.85*/("""
			"""),format.raw/*82.4*/("""if (e) """),format.raw/*82.11*/("""{"""),format.raw/*82.12*/("""
				"""),format.raw/*83.5*/("""$("#form_id_propiedad").val(id_propiedad);
				document.getElementById("form_eliminar").submit();
			"""),format.raw/*85.4*/("""}"""),format.raw/*85.5*/("""
		"""),format.raw/*86.3*/("""}"""),format.raw/*86.4*/(""");
	"""),format.raw/*87.2*/("""}"""),format.raw/*87.3*/("""
"""),format.raw/*88.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listPropiedad:List[tables.Propiedad]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listPropiedad)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Propiedad]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listPropiedad) => apply(mapDiccionario,mapPermiso,userMnu,listPropiedad)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuTablas/propiedadMantencion.scala.html
                  HASH: a651d3ace6dd0077bb41a8a738e87fc7404f0190
                  MATRIX: 1040->1|1269->137|1302->145|1318->153|1357->155|1385->158|1453->206|1481->208|1557->259|1646->328|1676->331|2099->728|2143->756|2182->757|2217->765|2288->809|2303->815|2336->827|2460->924|2475->930|2504->938|2714->1121|2729->1127|2758->1135|2895->1242|2928->1248|3823->2113|3852->2115|3942->2177|3971->2178|4003->2183|4062->2214|4091->2215|4126->2223|4268->2337|4297->2338|4337->2350|4443->2428|4472->2429|4504->2434|4532->2435|4560->2436|4660->2509|4688->2510|4766->2560|4795->2561|4825->2564|4998->2710|5026->2711|5056->2714|5128->2758|5157->2759|5187->2762|5339->2886|5368->2887|5399->2891|5434->2898|5463->2899|5495->2904|5623->3005|5651->3006|5681->3009|5709->3010|5740->3014|5768->3015|5796->3016
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|52->21|52->21|52->21|53->22|54->23|54->23|54->23|56->25|56->25|56->25|61->30|61->30|61->30|66->35|67->36|90->59|92->61|93->62|93->62|94->63|94->63|94->63|95->64|97->66|97->66|98->67|99->68|99->68|100->69|100->69|100->69|102->71|102->71|104->73|104->73|105->74|108->77|108->77|110->79|110->79|110->79|111->80|112->81|112->81|113->82|113->82|113->82|114->83|116->85|116->85|117->86|117->86|118->87|118->87|119->88
                  -- GENERATED --
              */
          