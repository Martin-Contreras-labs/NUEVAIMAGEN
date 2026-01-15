
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

object proyectoMantencion extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Proyecto],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listProyectos: List[tables.Proyecto]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalContactoProyecto()),format.raw/*9.26*/("""
	
	"""),format.raw/*11.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*12.4*/barraTitulo(mapDiccionario, "PROYECTOS: CREAR/MODIFICAR/ELIMINAR", "")),format.raw/*12.74*/("""
		
		
		
		"""),format.raw/*16.3*/("""<div class="noprint">
			<table class="table table-sm table-condensed table-fluid">
				<tr>
					<td>
						<div align="center">
							<button type="button"  class="btn btn-sm btn-success" onclick="document.getElementById('excel').submit()">
								Exportar a Excel
							</button>
						</div>
					</td>
				</tr>
			</table>
		</div>
		
		<form id="excel" class="formulario" method="post" action="/routes2/proyectoMantencionExcel/">
		</form>
			
		<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
			<thead style="background-color: #eeeeee">
				<TR> 
					<TH>NOMBRE LARGO (FULL NAME)</TH>
					<TH>NOMBRE CORTO (NICK NAME)</TH>
			        <TH>DIRECCION</TH>
			        <TH>"""),_display_(/*39.17*/mapDiccionario/*39.31*/.get("Region")),format.raw/*39.45*/("""</TH>
			        <TH>"""),_display_(/*40.17*/mapDiccionario/*40.31*/.get("Comuna")),format.raw/*40.45*/("""</TH>
			        <TH>Ciudad</TH>
					<TH>EDIT</TH>
					<TH>DEL</TH>
				</TR>
			</thead>
			<tbody>
				"""),_display_(/*47.6*/for(lista1 <- listProyectos) yield /*47.34*/{_display_(Seq[Any](format.raw/*47.35*/("""
					"""),format.raw/*48.6*/("""<TR>
						<td style="text-align:left;"><a href="#" onclick="buscaProyecto('"""),_display_(/*49.73*/lista1/*49.79*/.getNickName()),format.raw/*49.93*/("""')">"""),_display_(/*49.98*/lista1/*49.104*/.getNombre()),format.raw/*49.116*/("""</a></td>
						<td style="text-align:left;"><a href="#" onclick="buscaProyecto('"""),_display_(/*50.73*/lista1/*50.79*/.getNickName()),format.raw/*50.93*/("""')"><div id=""""),_display_(/*50.107*/lista1/*50.113*/.getId()),format.raw/*50.121*/("""">"""),_display_(/*50.124*/lista1/*50.130*/.getNickName()),format.raw/*50.144*/("""</div></a></td>
						
						<td  style="text-align:left;">"""),_display_(/*52.38*/lista1/*52.44*/.getDireccion()),format.raw/*52.59*/("""</td>
						<td  style="text-align:left;">"""),_display_(/*53.38*/lista1/*53.44*/.getRegion()),format.raw/*53.56*/("""</td>
						<td  style="text-align:left;">"""),_display_(/*54.38*/lista1/*54.44*/.getComuna()),format.raw/*54.56*/("""</td>
						<td  style="text-align:left;">"""),_display_(/*55.38*/lista1/*55.44*/.getCiudad()),format.raw/*55.56*/("""</td>
						<td  style="text-align:center;">
							<a href="/proyectoModifica/"""),_display_(/*57.36*/lista1/*57.42*/.getId()),format.raw/*57.50*/("""">
								<kbd style="background-color: #73C6B6">E</kbd>
							</a>
						</td>
						<td  style="text-align:center;">
							<a href="#" onclick= "eliminarProyecto('"""),_display_(/*62.49*/lista1/*62.55*/.getId()),format.raw/*62.63*/("""')">
								<kbd style="background-color: red">X</kbd>
							</a>
						</td>
					</TR>
	 			""")))}),format.raw/*67.7*/("""
			"""),format.raw/*68.4*/("""</tbody>
		</table>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="AGREGAR" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/proyectoAgrega/';">
				</div>
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
				</div>
			</div>
		</div>
	</div>
	
	<form id="form_eliminar" method="post" action="/proyectoElimina/">
		<input type="hidden" id="form_id_proyecto" name="id_proyecto">
	</form>
""")))}),format.raw/*85.2*/("""




"""),format.raw/*90.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*91.31*/("""{"""),format.raw/*91.32*/("""
		  """),format.raw/*92.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*92.36*/("""{"""),format.raw/*92.37*/("""
		    	"""),format.raw/*93.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"language": """),format.raw/*95.20*/("""{"""),format.raw/*95.21*/("""
		        	"""),format.raw/*96.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*97.11*/("""}"""),format.raw/*97.12*/("""
		  """),format.raw/*98.5*/("""}"""),format.raw/*98.6*/(""" """),format.raw/*98.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*100.2*/("""}"""),format.raw/*100.3*/(""");
	const eliminarProyecto = (id_proyecto) => """),format.raw/*101.44*/("""{"""),format.raw/*101.45*/("""
		"""),format.raw/*102.3*/("""let nombre = $("#"+id_proyecto).text();
		alertify.confirm("Esta seguro de eliminar el proyecto: "+nombre, function (e) """),format.raw/*103.81*/("""{"""),format.raw/*103.82*/("""
			"""),format.raw/*104.4*/("""if (e) """),format.raw/*104.11*/("""{"""),format.raw/*104.12*/("""
				"""),format.raw/*105.5*/("""$("#form_id_proyecto").val(id_proyecto);
				document.getElementById("form_eliminar").submit();
			"""),format.raw/*107.4*/("""}"""),format.raw/*107.5*/("""
		"""),format.raw/*108.3*/("""}"""),format.raw/*108.4*/(""");
	"""),format.raw/*109.2*/("""}"""),format.raw/*109.3*/("""
"""),format.raw/*110.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listProyectos:List[tables.Proyecto]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listProyectos)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Proyecto]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listProyectos) => apply(mapDiccionario,mapPermiso,userMnu,listProyectos)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuTablas/proyectoMantencion.scala.html
                  HASH: 3604f02b086b415743416ecc997b5e9a25431275
                  MATRIX: 1038->1|1266->136|1299->144|1315->152|1354->154|1383->158|1451->206|1481->211|1524->234|1555->238|1632->289|1723->359|1762->371|2530->1112|2553->1126|2588->1140|2637->1162|2660->1176|2695->1190|2829->1298|2873->1326|2912->1327|2945->1333|3049->1410|3064->1416|3099->1430|3131->1435|3147->1441|3181->1453|3290->1535|3305->1541|3340->1555|3382->1569|3398->1575|3428->1583|3459->1586|3475->1592|3511->1606|3598->1666|3613->1672|3649->1687|3719->1730|3734->1736|3767->1748|3837->1791|3852->1797|3885->1809|3955->1852|3970->1858|4003->1870|4110->1950|4125->1956|4154->1964|4350->2133|4365->2139|4394->2147|4521->2244|4552->2248|5249->2915|5281->2920|5371->2982|5400->2983|5432->2988|5491->3019|5520->3020|5555->3028|5697->3142|5726->3143|5766->3155|5872->3233|5901->3234|5933->3239|5961->3240|5989->3241|6090->3314|6119->3315|6194->3361|6224->3362|6255->3365|6404->3485|6434->3486|6466->3490|6502->3497|6532->3498|6565->3503|6692->3602|6721->3603|6752->3606|6781->3607|6813->3611|6842->3612|6871->3613
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|42->11|43->12|43->12|47->16|70->39|70->39|70->39|71->40|71->40|71->40|78->47|78->47|78->47|79->48|80->49|80->49|80->49|80->49|80->49|80->49|81->50|81->50|81->50|81->50|81->50|81->50|81->50|81->50|81->50|83->52|83->52|83->52|84->53|84->53|84->53|85->54|85->54|85->54|86->55|86->55|86->55|88->57|88->57|88->57|93->62|93->62|93->62|98->67|99->68|116->85|121->90|122->91|122->91|123->92|123->92|123->92|124->93|126->95|126->95|127->96|128->97|128->97|129->98|129->98|129->98|131->100|131->100|132->101|132->101|133->102|134->103|134->103|135->104|135->104|135->104|136->105|138->107|138->107|139->108|139->108|140->109|140->109|141->110
                  -- GENERATED --
              */
          