
package viewsMnuBodegas.html

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

object bodegaAdministrar extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listBodegas: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalContactoBodega(mapDiccionario)),format.raw/*9.38*/("""
	"""),_display_(/*10.3*/modalContactoCliente()),format.raw/*10.25*/("""
	"""),_display_(/*11.3*/modalContactoProyecto()),format.raw/*11.26*/("""
	
	"""),format.raw/*13.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*14.4*/barraTitulo(mapDiccionario, "MANTENCION DE "+mapDiccionario.get("BODEGA")+"S/PROYECTOS", "(CREAR/MODIFICAR/ELIMINAR)")),format.raw/*14.122*/("""
		"""),format.raw/*15.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>TIPO</TH>
							<TH>SUCURSAL</TH>
							<TH>"""),_display_(/*22.13*/mapDiccionario/*22.27*/.get("BODEGA")),format.raw/*22.41*/("""/PROYECTO</TH>
							<TH>"""),_display_(/*23.13*/mapDiccionario/*23.27*/.get("RUT")),format.raw/*23.38*/(""" """),format.raw/*23.39*/("""(Cliente o Propietario)</TH>
							<TH>NOMBRE CLIENTE O PROPIETARIO</TH>
					        <TH>NOMBRE DEL PROYECTO</TH>
					        <TH>"""),_display_(/*26.19*/mapDiccionario/*26.33*/.get("COMUNA")),format.raw/*26.47*/(""" """),format.raw/*26.48*/("""PROYECTO</TH>
					        <TH>COMERCIAL</TH>
					        
					        """),_display_(if(mapPermiso.get("parametro.ivaPorBodega")!=null && mapPermiso.get("parametro.ivaPorBodega").equals("1"))/*29.121*/ {_display_(Seq[Any](format.raw/*29.123*/("""
								"""),format.raw/*30.9*/("""<TH>PEP</TH>
								<TH>IVA</TH>
							""")))} else {null} ),format.raw/*32.9*/("""
							
							"""),format.raw/*34.8*/("""<TH>EDIT</TH>
							<TH>DEL</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*39.8*/for(lista1 <- listBodegas) yield /*39.34*/{_display_(Seq[Any](format.raw/*39.35*/("""
							"""),format.raw/*40.8*/("""<TR>
								<td  style="text-align:left;">"""),_display_(/*41.40*/lista1/*41.46*/.get(4)),format.raw/*41.53*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*42.40*/lista1/*42.46*/.get(16)),format.raw/*42.54*/("""</td>
								<td style="text-align:left;"><a href="#" onclick="buscaBodega('"""),_display_(/*43.73*/lista1/*43.79*/.get(5)),format.raw/*43.86*/("""')"><div id=""""),_display_(/*43.100*/lista1/*43.106*/.get(1)),format.raw/*43.113*/("""">"""),_display_(/*43.116*/lista1/*43.122*/.get(5)),format.raw/*43.129*/("""</div></a></td>
								<td style="text-align:center;"><a href="#" onclick="buscaCliente('"""),_display_(/*44.76*/lista1/*44.82*/.get(7)),format.raw/*44.89*/("""')">"""),_display_(/*44.94*/lista1/*44.100*/.get(6)),format.raw/*44.107*/("""</a></td>
								<td style="text-align:left;"><a href="#" onclick="buscaCliente('"""),_display_(/*45.74*/lista1/*45.80*/.get(7)),format.raw/*45.87*/("""')">"""),_display_(/*45.92*/lista1/*45.98*/.get(7)),format.raw/*45.105*/("""</a></td>
								<td style="text-align:left;"><a href="#" onclick="buscaProyecto('"""),_display_(/*46.75*/lista1/*46.81*/.get(8)),format.raw/*46.88*/("""')">"""),_display_(/*46.93*/lista1/*46.99*/.get(8)),format.raw/*46.106*/("""</a></td>
								
								<td  style="text-align:left;">"""),_display_(/*48.40*/lista1/*48.46*/.get(9)),format.raw/*48.53*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*49.40*/lista1/*49.46*/.get(11)),format.raw/*49.54*/("""</td>
								
								"""),_display_(if(mapPermiso.get("parametro.ivaPorBodega")!=null && mapPermiso.get("parametro.ivaPorBodega").equals("1"))/*51.116*/ {_display_(Seq[Any](format.raw/*51.118*/("""
									
									"""),_display_(if(lista1.get(0).equals("1"))/*53.40*/{_display_(Seq[Any](format.raw/*53.41*/("""
										"""),format.raw/*54.11*/("""<td  style="text-align:center;">---</td>
										<td  style="text-align:center;">---</td>
									""")))}else/*56.15*/{_display_(Seq[Any](format.raw/*56.16*/("""
										"""),format.raw/*57.11*/("""<td  style="text-align:center;">"""),_display_(/*57.44*/lista1/*57.50*/.get(13)),format.raw/*57.58*/("""</td>
										<td  style="text-align:center;">"""),_display_(/*58.44*/lista1/*58.50*/.get(15)),format.raw/*58.58*/("""</td>
									""")))}),format.raw/*59.11*/("""
									
								""")))} else {null} ),format.raw/*61.10*/("""
								
								"""),format.raw/*63.9*/("""<td  style="text-align:center;">
									<form id="form_"""),_display_(/*64.26*/lista1/*64.32*/.get(1)),format.raw/*64.39*/("""" method="post" action="/bodegaModificar/">
										<input type="hidden" name="id_bodega" value=""""),_display_(/*65.57*/lista1/*65.63*/.get(1)),format.raw/*65.70*/("""">
										<a href="#" onclick='document.getElementById("form_"""),_display_(/*66.63*/lista1/*66.69*/.get(1)),format.raw/*66.76*/("""").submit();'>
											<kbd style="background-color: #73C6B6">E</kbd>
										</a>
									</form>
								</td>
								<td  style="text-align:center;">
									<a href="#" onclick= "eliminarBodega('"""),_display_(/*72.49*/lista1/*72.55*/.get(1)),format.raw/*72.62*/("""')">
										<kbd style="background-color: red">X</kbd>
									</a>
								</td>
							</TR>
			 			""")))}),format.raw/*77.9*/("""
					"""),format.raw/*78.6*/("""</tbody>
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value='AGREGAR """),_display_(/*85.44*/mapDiccionario/*85.58*/.get("BODEGA")),format.raw/*85.72*/("""' class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/bodegaCrear/';">
				</div>
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
				</div>
			</div>
		</div>
	</div>
	
	<form id="form_eliminar" method="post" action="/bodegaElimina/">
		<input type="hidden" id="form_id_bodega" name="id_bodega">
	</form>
""")))}),format.raw/*97.2*/("""




"""),format.raw/*102.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*103.31*/("""{"""),format.raw/*103.32*/("""
		  """),format.raw/*104.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*104.36*/("""{"""),format.raw/*104.37*/("""
		    	"""),format.raw/*105.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
				"order":[[0,"asc"],[2,"asc"]],
		    	"language": """),format.raw/*108.20*/("""{"""),format.raw/*108.21*/("""
		        	"""),format.raw/*109.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*110.11*/("""}"""),format.raw/*110.12*/("""
		  """),format.raw/*111.5*/("""}"""),format.raw/*111.6*/(""" """),format.raw/*111.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*113.2*/("""}"""),format.raw/*113.3*/(""");
	
	const eliminarBodega = (id_bodega) => """),format.raw/*115.40*/("""{"""),format.raw/*115.41*/("""
		"""),format.raw/*116.3*/("""let nombre = $("#"+id_bodega).text();
		alertify.confirm("Esta seguro de eliminar la bodega: "+nombre, function (e) """),format.raw/*117.79*/("""{"""),format.raw/*117.80*/("""
			"""),format.raw/*118.4*/("""if (e) """),format.raw/*118.11*/("""{"""),format.raw/*118.12*/("""
				"""),format.raw/*119.5*/("""$("#form_id_bodega").val(id_bodega);
				document.getElementById("form_eliminar").submit();
			"""),format.raw/*121.4*/("""}"""),format.raw/*121.5*/("""
		"""),format.raw/*122.3*/("""}"""),format.raw/*122.4*/(""");
	"""),format.raw/*123.2*/("""}"""),format.raw/*123.3*/("""
"""),format.raw/*124.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listBodegas:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listBodegas)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listBodegas) => apply(mapDiccionario,mapPermiso,userMnu,listBodegas)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuBodegas/bodegaAdministrar.scala.html
                  HASH: 8db27ed01573b63a58ab0b76bfa3cb02f2804d03
                  MATRIX: 1035->1|1258->131|1291->139|1307->147|1346->149|1375->153|1443->201|1473->206|1528->241|1557->244|1600->266|1629->269|1673->292|1704->296|1781->347|1921->465|1951->468|2306->796|2329->810|2364->824|2418->851|2441->865|2473->876|2502->877|2663->1011|2686->1025|2721->1039|2750->1040|2958->1220|2999->1222|3035->1231|3120->1273|3163->1289|3269->1369|3311->1395|3350->1396|3385->1404|3456->1448|3471->1454|3499->1461|3571->1506|3586->1512|3615->1520|3720->1598|3735->1604|3763->1611|3805->1625|3821->1631|3850->1638|3881->1641|3897->1647|3926->1654|4044->1745|4059->1751|4087->1758|4119->1763|4135->1769|4164->1776|4274->1859|4289->1865|4317->1872|4349->1877|4364->1883|4393->1890|4504->1974|4519->1980|4547->1987|4579->1992|4594->1998|4623->2005|4708->2063|4723->2069|4751->2076|4823->2121|4838->2127|4867->2135|5025->2265|5066->2267|5143->2317|5182->2318|5221->2329|5346->2435|5385->2436|5424->2447|5484->2480|5499->2486|5528->2494|5604->2543|5619->2549|5648->2557|5695->2573|5759->2593|5804->2611|5889->2669|5904->2675|5932->2682|6059->2782|6074->2788|6102->2795|6194->2860|6209->2866|6237->2873|6472->3081|6487->3087|6515->3094|6652->3201|6685->3207|6922->3417|6945->3431|6980->3445|7481->3916|7514->3921|7605->3983|7635->3984|7668->3989|7728->4020|7758->4021|7794->4029|7972->4178|8002->4179|8043->4191|8150->4269|8180->4270|8213->4275|8242->4276|8271->4277|8372->4350|8401->4351|8474->4395|8504->4396|8535->4399|8680->4515|8710->4516|8742->4520|8778->4527|8808->4528|8841->4533|8964->4628|8993->4629|9024->4632|9053->4633|9085->4637|9114->4638|9143->4639
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|41->10|41->10|42->11|42->11|44->13|45->14|45->14|46->15|53->22|53->22|53->22|54->23|54->23|54->23|54->23|57->26|57->26|57->26|57->26|60->29|60->29|61->30|63->32|65->34|70->39|70->39|70->39|71->40|72->41|72->41|72->41|73->42|73->42|73->42|74->43|74->43|74->43|74->43|74->43|74->43|74->43|74->43|74->43|75->44|75->44|75->44|75->44|75->44|75->44|76->45|76->45|76->45|76->45|76->45|76->45|77->46|77->46|77->46|77->46|77->46|77->46|79->48|79->48|79->48|80->49|80->49|80->49|82->51|82->51|84->53|84->53|85->54|87->56|87->56|88->57|88->57|88->57|88->57|89->58|89->58|89->58|90->59|92->61|94->63|95->64|95->64|95->64|96->65|96->65|96->65|97->66|97->66|97->66|103->72|103->72|103->72|108->77|109->78|116->85|116->85|116->85|128->97|133->102|134->103|134->103|135->104|135->104|135->104|136->105|139->108|139->108|140->109|141->110|141->110|142->111|142->111|142->111|144->113|144->113|146->115|146->115|147->116|148->117|148->117|149->118|149->118|149->118|150->119|152->121|152->121|153->122|153->122|154->123|154->123|155->124
                  -- GENERATED --
              */
          