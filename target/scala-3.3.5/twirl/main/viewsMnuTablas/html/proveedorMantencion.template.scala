
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

object proveedorMantencion extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Proveedor],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listProveedor: List[tables.Proveedor]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "PROVEEDOR: CREAR/MODIFICAR/ELIMINAR", "")),format.raw/*9.74*/("""
		"""),format.raw/*10.3*/("""<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
			<thead style="background-color: #eeeeee">
				<TR> 
					<TH>"""),_display_(/*13.11*/mapDiccionario/*13.25*/.get("RUT")),format.raw/*13.36*/("""</TH>
					<TH>NOMBRE LARGO (FULL NAME)</TH>
					<TH>NOMBRE CORTO (NICK NAME)</TH>
			        <TH>DIRECCION</TH>
			        <TH>"""),_display_(/*17.17*/mapDiccionario/*17.31*/.get("Region")),format.raw/*17.45*/("""</TH>
			        <TH>"""),_display_(/*18.17*/mapDiccionario/*18.31*/.get("Comuna")),format.raw/*18.45*/("""</TH>
			        <TH>Ciudad</TH>
					<TH>EDIT</TH>
					<TH>DEL</TH>
				</TR>
			</thead>
			<tbody>
				"""),_display_(/*25.6*/for(lista1 <- listProveedor) yield /*25.34*/{_display_(Seq[Any](format.raw/*25.35*/("""
					"""),format.raw/*26.6*/("""<TR>
						<td  style="text-align:left;">"""),_display_(/*27.38*/lista1/*27.44*/.getRut()),format.raw/*27.53*/("""</td>
						<td  style="text-align:left;">"""),_display_(/*28.38*/lista1/*28.44*/.getNombre()),format.raw/*28.56*/("""</td>
						<td  style="text-align:left;"><div id=""""),_display_(/*29.47*/lista1/*29.53*/.getId()),format.raw/*29.61*/("""">"""),_display_(/*29.64*/lista1/*29.70*/.getNickName()),format.raw/*29.84*/("""</div></td>
						<td  style="text-align:left;">"""),_display_(/*30.38*/lista1/*30.44*/.getDireccion()),format.raw/*30.59*/("""</td>
						<td  style="text-align:left;">"""),_display_(/*31.38*/lista1/*31.44*/.getRegion()),format.raw/*31.56*/("""</td>
						<td  style="text-align:left;">"""),_display_(/*32.38*/lista1/*32.44*/.getComuna()),format.raw/*32.56*/("""</td>
						<td  style="text-align:left;">"""),_display_(/*33.38*/lista1/*33.44*/.getCiudad()),format.raw/*33.56*/("""</td>
						<td  style="text-align:center;">
							<a href="/proveedorModifica/"""),_display_(/*35.37*/lista1/*35.43*/.getId()),format.raw/*35.51*/("""">
								<kbd style="background-color: #73C6B6">E</kbd>
							</a>
						</td>
						<td  style="text-align:center;">
							<a href="#" onclick= "eliminarProveedor('"""),_display_(/*40.50*/lista1/*40.56*/.getId()),format.raw/*40.64*/("""')">
								<kbd style="background-color: red">X</kbd>
							</a>
						</td>
					</TR>
	 			""")))}),format.raw/*45.7*/("""
			"""),format.raw/*46.4*/("""</tbody>
		</table>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="AGREGAR" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/proveedorAgrega/';">
				</div>
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
				</div>
			</div>
		</div>
	</div>
	
	<form id="form_eliminar" method="post" action="/proveedorElimina/">
		<input type="hidden" id="form_id_proveedor" name="id_proveedor">
	</form>
""")))}),format.raw/*63.2*/("""




"""),format.raw/*68.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*69.31*/("""{"""),format.raw/*69.32*/("""
		  """),format.raw/*70.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*70.36*/("""{"""),format.raw/*70.37*/("""
		    	"""),format.raw/*71.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"language": """),format.raw/*73.20*/("""{"""),format.raw/*73.21*/("""
		        	"""),format.raw/*74.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*75.11*/("""}"""),format.raw/*75.12*/("""
		  """),format.raw/*76.5*/("""}"""),format.raw/*76.6*/(""" """),format.raw/*76.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*78.2*/("""}"""),format.raw/*78.3*/(""");
	const eliminarProveedor = (id_proveedor) => """),format.raw/*79.46*/("""{"""),format.raw/*79.47*/("""
		"""),format.raw/*80.3*/("""let nombre = $("#"+id_proveedor).text();
		alertify.confirm("Esta seguro de eliminar el proveedor: "+nombre, function (e) """),format.raw/*81.82*/("""{"""),format.raw/*81.83*/("""
			"""),format.raw/*82.4*/("""if (e) """),format.raw/*82.11*/("""{"""),format.raw/*82.12*/("""
				"""),format.raw/*83.5*/("""$("#form_id_proveedor").val(id_proveedor);
				document.getElementById("form_eliminar").submit();
			"""),format.raw/*85.4*/("""}"""),format.raw/*85.5*/("""
		"""),format.raw/*86.3*/("""}"""),format.raw/*86.4*/(""");
	"""),format.raw/*87.2*/("""}"""),format.raw/*87.3*/("""
"""),format.raw/*88.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listProveedor:List[tables.Proveedor]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listProveedor)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Proveedor]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listProveedor) => apply(mapDiccionario,mapPermiso,userMnu,listProveedor)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuTablas/proveedorMantencion.scala.html
                  HASH: 9fa3a049e26c7262743fc2920adb3bd17014f199
                  MATRIX: 1040->1|1269->137|1302->145|1318->153|1357->155|1385->158|1453->206|1481->208|1557->259|1647->329|1677->332|1875->503|1898->517|1930->528|2087->658|2110->672|2145->686|2194->708|2217->722|2252->736|2386->844|2430->872|2469->873|2502->879|2571->921|2586->927|2616->936|2686->979|2701->985|2734->997|2813->1049|2828->1055|2857->1063|2887->1066|2902->1072|2937->1086|3013->1135|3028->1141|3064->1156|3134->1199|3149->1205|3182->1217|3252->1260|3267->1266|3300->1278|3370->1321|3385->1327|3418->1339|3526->1420|3541->1426|3570->1434|3767->1604|3782->1610|3811->1618|3938->1715|3969->1719|4670->2390|4702->2395|4792->2457|4821->2458|4853->2463|4912->2494|4941->2495|4976->2503|5118->2617|5147->2618|5187->2630|5293->2708|5322->2709|5354->2714|5382->2715|5410->2716|5510->2789|5538->2790|5614->2838|5643->2839|5673->2842|5823->2964|5852->2965|5883->2969|5918->2976|5947->2977|5979->2982|6107->3083|6135->3084|6165->3087|6193->3088|6224->3092|6252->3093|6280->3094
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|44->13|44->13|44->13|48->17|48->17|48->17|49->18|49->18|49->18|56->25|56->25|56->25|57->26|58->27|58->27|58->27|59->28|59->28|59->28|60->29|60->29|60->29|60->29|60->29|60->29|61->30|61->30|61->30|62->31|62->31|62->31|63->32|63->32|63->32|64->33|64->33|64->33|66->35|66->35|66->35|71->40|71->40|71->40|76->45|77->46|94->63|99->68|100->69|100->69|101->70|101->70|101->70|102->71|104->73|104->73|105->74|106->75|106->75|107->76|107->76|107->76|109->78|109->78|110->79|110->79|111->80|112->81|112->81|113->82|113->82|113->82|114->83|116->85|116->85|117->86|117->86|118->87|118->87|119->88
                  -- GENERATED --
              */
          