
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

object fabricaMantencion extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Fabrica],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listFabricantes: List[tables.Fabrica]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "FABRICANTES: CREAR/MODIFICAR/ELIMINAR", "")),format.raw/*9.76*/("""
		"""),format.raw/*10.3*/("""<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
			<thead style="background-color: #eeeeee">
				<TR> 
					<TH>NOMBRE LARGO (FULL NAME)</TH>
					<TH>NOMBRE CORTO (NICK NAME)</TH>
			        <TH>DIRECCION</TH>
			        <TH>"""),_display_(/*16.17*/mapDiccionario/*16.31*/.get("Region")),format.raw/*16.45*/("""</TH>
			        <TH>"""),_display_(/*17.17*/mapDiccionario/*17.31*/.get("Comuna")),format.raw/*17.45*/("""</TH>
			        <TH>Ciudad</TH>
					<TH>EDIT</TH>
					<TH>DEL</TH>
				</TR>
			</thead>
			<tbody>
				"""),_display_(/*24.6*/for(lista1 <- listFabricantes) yield /*24.36*/{_display_(Seq[Any](format.raw/*24.37*/("""
					"""),format.raw/*25.6*/("""<TR>
						<td  style="text-align:left;">"""),_display_(/*26.38*/lista1/*26.44*/.getNombre()),format.raw/*26.56*/("""</td>
						<td  style="text-align:left;"><div id=""""),_display_(/*27.47*/lista1/*27.53*/.getId()),format.raw/*27.61*/("""">"""),_display_(/*27.64*/lista1/*27.70*/.getNickName()),format.raw/*27.84*/("""</div></td>
						<td  style="text-align:left;">"""),_display_(/*28.38*/lista1/*28.44*/.getDireccion()),format.raw/*28.59*/("""</td>
						<td  style="text-align:left;">"""),_display_(/*29.38*/lista1/*29.44*/.getRegion()),format.raw/*29.56*/("""</td>
						<td  style="text-align:left;">"""),_display_(/*30.38*/lista1/*30.44*/.getComuna()),format.raw/*30.56*/("""</td>
						<td  style="text-align:left;">"""),_display_(/*31.38*/lista1/*31.44*/.getCiudad()),format.raw/*31.56*/("""</td>
						<td  style="text-align:center;">
							<a href="/fabricaModifica/"""),_display_(/*33.35*/lista1/*33.41*/.getId()),format.raw/*33.49*/("""">
								<kbd style="background-color: #73C6B6">E</kbd>
							</a>
						</td>
						<td  style="text-align:center;">
							<a href="#" onclick= "eliminarFabrica('"""),_display_(/*38.48*/lista1/*38.54*/.getId()),format.raw/*38.62*/("""')">
								<kbd style="background-color: red">X</kbd>
							</a>
						</td>
					</TR>
	 			""")))}),format.raw/*43.7*/("""
			"""),format.raw/*44.4*/("""</tbody>
		</table>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="AGREGAR" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/fabricaAgrega/';">
				</div>
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
				</div>
			</div>
		</div>
	</div>
	
	<form id="form_eliminar" method="post" action="/fabricaElimina/">
		<input type="hidden" id="form_id_fabrica" name="id_fabrica">
	</form>
""")))}),format.raw/*61.2*/("""




"""),format.raw/*66.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*67.31*/("""{"""),format.raw/*67.32*/("""
		  """),format.raw/*68.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*68.36*/("""{"""),format.raw/*68.37*/("""
		    	"""),format.raw/*69.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"language": """),format.raw/*71.20*/("""{"""),format.raw/*71.21*/("""
		        	"""),format.raw/*72.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*73.11*/("""}"""),format.raw/*73.12*/("""
		  """),format.raw/*74.5*/("""}"""),format.raw/*74.6*/(""" """),format.raw/*74.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*76.2*/("""}"""),format.raw/*76.3*/(""");
	const eliminarFabrica = (id_fabrica) => """),format.raw/*77.42*/("""{"""),format.raw/*77.43*/("""
		"""),format.raw/*78.3*/("""let nombre = $("#"+id_fabrica).text();
		alertify.confirm("Esta seguro de eliminar el fabricante: "+nombre, function (e) """),format.raw/*79.83*/("""{"""),format.raw/*79.84*/("""
			"""),format.raw/*80.4*/("""if (e) """),format.raw/*80.11*/("""{"""),format.raw/*80.12*/("""
				"""),format.raw/*81.5*/("""$("#form_id_fabrica").val(id_fabrica);
				document.getElementById("form_eliminar").submit();
			"""),format.raw/*83.4*/("""}"""),format.raw/*83.5*/("""
		"""),format.raw/*84.3*/("""}"""),format.raw/*84.4*/(""");
	"""),format.raw/*85.2*/("""}"""),format.raw/*85.3*/("""
"""),format.raw/*86.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listFabricantes:List[tables.Fabrica]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listFabricantes)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Fabrica]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listFabricantes) => apply(mapDiccionario,mapPermiso,userMnu,listFabricantes)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuTablas/fabricaMantencion.scala.html
                  HASH: 8010894d3536576bbbd6446d307ea481454de506
                  MATRIX: 1036->1|1265->137|1298->145|1314->153|1353->155|1381->158|1449->206|1477->208|1553->259|1645->331|1675->334|1987->619|2010->633|2045->647|2094->669|2117->683|2152->697|2286->805|2332->835|2371->836|2404->842|2473->884|2488->890|2521->902|2600->954|2615->960|2644->968|2674->971|2689->977|2724->991|2800->1040|2815->1046|2851->1061|2921->1104|2936->1110|2969->1122|3039->1165|3054->1171|3087->1183|3157->1226|3172->1232|3205->1244|3311->1323|3326->1329|3355->1337|3550->1505|3565->1511|3594->1519|3721->1616|3752->1620|4445->2283|4477->2288|4567->2350|4596->2351|4628->2356|4687->2387|4716->2388|4751->2396|4893->2510|4922->2511|4962->2523|5068->2601|5097->2602|5129->2607|5157->2608|5185->2609|5285->2682|5313->2683|5385->2727|5414->2728|5444->2731|5593->2852|5622->2853|5653->2857|5688->2864|5717->2865|5749->2870|5873->2967|5901->2968|5931->2971|5959->2972|5990->2976|6018->2977|6046->2978
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|47->16|47->16|47->16|48->17|48->17|48->17|55->24|55->24|55->24|56->25|57->26|57->26|57->26|58->27|58->27|58->27|58->27|58->27|58->27|59->28|59->28|59->28|60->29|60->29|60->29|61->30|61->30|61->30|62->31|62->31|62->31|64->33|64->33|64->33|69->38|69->38|69->38|74->43|75->44|92->61|97->66|98->67|98->67|99->68|99->68|99->68|100->69|102->71|102->71|103->72|104->73|104->73|105->74|105->74|105->74|107->76|107->76|108->77|108->77|109->78|110->79|110->79|111->80|111->80|111->80|112->81|114->83|114->83|115->84|115->84|116->85|116->85|117->86
                  -- GENERATED --
              */
          