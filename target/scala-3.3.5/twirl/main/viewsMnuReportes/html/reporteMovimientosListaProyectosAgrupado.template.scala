
package viewsMnuReportes.html

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

object reporteMovimientosListaProyectosAgrupado extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
lista: List[List[String]], fechaDesde: String, fechaHasta: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	
	"""),_display_(/*8.3*/modalContactoBodega(mapDiccionario)),format.raw/*8.38*/("""
	"""),_display_(/*9.3*/modalContactoCliente()),format.raw/*9.25*/("""
	"""),_display_(/*10.3*/modalContactoProyecto()),format.raw/*10.26*/("""
	
	"""),format.raw/*12.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*13.4*/barraTitulo(mapDiccionario, "SELECCION DE "+mapDiccionario.get("BODEGA")+"/PROYECTO","REPORTE DETALLE DE MOVIMIENTOS")),format.raw/*13.122*/("""
		"""),format.raw/*14.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-8 col-md-8 col-lg-8">
				<div class="noprint">
					<table class="table table-sm table-condensed table-fluid">
						<tr>
							<td width="25%">
								<div class="input-group">
							  		<div class="input-group-prepend">
							    		<span class="input-group-text" id="basic-addon1">BUSCAR</span>
							  		</div>
							  		<input type="text" class="form-control left"
										id="searchTermtablaPrincipal"
										onkeyup="doSearch2('tablaPrincipal');">
								</div>
							</td>
						</tr>
					</table>
				</div>
				
				<div class="table-responsive">
					<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
				        <thead style="background-color: #eeeeee">
					        <TR> 
								<TH style= "text-align: center;vertical-align: top;">SUCURSAL</TH>
						        <TH style= "text-align: center;vertical-align: top;">"""),_display_(/*38.69*/mapDiccionario/*38.83*/.get("BODEGA")),format.raw/*38.97*/("""/PROYECTO</TH>
								<TH style= "text-align: center;vertical-align: top;">NOMBRE<BR>CLIENTE</TH>
								<TH style= "text-align: center;vertical-align: top;">NOMBRE<br>PROYECTO</TH>
								<TH style= "text-align: center;vertical-align: top;">"""),_display_(/*41.63*/mapDiccionario/*41.77*/.get("COMUNA")),format.raw/*41.91*/("""<BR>PROYECTO</TH>
								<TH width="5%" >"""),_display_(/*42.26*/mapDiccionario/*42.40*/.get("ARRIENDO")),format.raw/*42.56*/("""<BR></TH>
								<TH width="5%" >VENTA<BR></TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*47.9*/for(lista1 <- lista) yield /*47.29*/{_display_(Seq[Any](format.raw/*47.30*/("""
								"""),format.raw/*48.9*/("""<TR>
									<td style="text-align:left;vertical-align:middle;">"""),_display_(/*49.62*/lista1/*49.68*/.get(11)),format.raw/*49.76*/("""</td>
									<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="modalContactoBodega('"""),_display_(/*50.104*/lista1/*50.110*/.get(1)),format.raw/*50.117*/("""');">"""),_display_(/*50.123*/lista1/*50.129*/.get(5)),format.raw/*50.136*/("""</a></td>
									<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="modalContactoCliente('"""),_display_(/*51.105*/lista1/*51.111*/.get(2)),format.raw/*51.118*/("""');">"""),_display_(/*51.124*/lista1/*51.130*/.get(7)),format.raw/*51.137*/("""</a></td>
									<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="modalContactoProyecto('"""),_display_(/*52.106*/lista1/*52.112*/.get(3)),format.raw/*52.119*/("""');">"""),_display_(/*52.125*/lista1/*52.131*/.get(8)),format.raw/*52.138*/("""</a></td>
									<td style="text-align:left;vertical-align:middle;">"""),_display_(/*53.62*/lista1/*53.68*/.get(9)),format.raw/*53.75*/("""</td>
									<td style="text-align:center;vertical-align:middle;">
										<form id="form0_"""),_display_(/*55.28*/lista1/*55.34*/.get(1)),format.raw/*55.41*/("""" class="formulario" method="post" action="/reporteMovimientosDetalleAgrupado/">
											<input type="hidden" name="id_bodega" value=""""),_display_(/*56.58*/lista1/*56.64*/.get(1)),format.raw/*56.71*/("""">
											<input type="hidden" name="fechaDesde" value=""""),_display_(/*57.59*/fechaDesde),format.raw/*57.69*/("""">
											<input type="hidden" name="fechaHasta" value=""""),_display_(/*58.59*/fechaHasta),format.raw/*58.69*/("""">
											<input type="hidden" name="esVenta" value="0">
											<a href="#" onclick="document.getElementById('form0_"""),_display_(/*60.65*/lista1/*60.71*/.get(1)),format.raw/*60.78*/("""').submit()">
												<kbd style="background-color: #73C6B6">"""),_display_(/*61.53*/mapDiccionario/*61.67*/.get("Arriendos")),format.raw/*61.84*/("""</kbd>
											</a>
										</form>
									</td>
									<td style="text-align:center;vertical-align:middle;">
										<form id="form1_"""),_display_(/*66.28*/lista1/*66.34*/.get(1)),format.raw/*66.41*/("""" class="formulario" method="post" action="/reporteMovimientosDetalleAgrupado/">
											<input type="hidden" name="id_bodega" value=""""),_display_(/*67.58*/lista1/*67.64*/.get(1)),format.raw/*67.71*/("""">
											<input type="hidden" name="fechaDesde" value=""""),_display_(/*68.59*/fechaDesde),format.raw/*68.69*/("""">
											<input type="hidden" name="fechaHasta" value=""""),_display_(/*69.59*/fechaHasta),format.raw/*69.69*/("""">
											<input type="hidden" name="esVenta" value="1">
											<a href="#" onclick="document.getElementById('form1_"""),_display_(/*71.65*/lista1/*71.71*/.get(1)),format.raw/*71.78*/("""').submit()">
												<kbd style="background-color: #73C6B6">Ventas</kbd>
											</a>
										</form>
									</td>
								</TR>
				 			""")))}),format.raw/*77.10*/("""
						"""),format.raw/*78.7*/("""</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>


""")))}),format.raw/*86.2*/("""


"""),format.raw/*89.1*/("""<script type="text/javascript">
	let flag = true;
	$(document).ready(function() """),format.raw/*91.31*/("""{"""),format.raw/*91.32*/("""

		 """),format.raw/*93.4*/("""$('#tablaPrincipal').DataTable("""),format.raw/*93.35*/("""{"""),format.raw/*93.36*/("""
		    	"""),format.raw/*94.8*/(""""fixedHeader": true,
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [[ 1, "asc" ]],
		    	"language": """),format.raw/*99.20*/("""{"""),format.raw/*99.21*/("""
		        	"""),format.raw/*100.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*101.11*/("""}"""),format.raw/*101.12*/("""
		  """),format.raw/*102.5*/("""}"""),format.raw/*102.6*/(""" """),format.raw/*102.7*/(""");

			document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*105.2*/("""}"""),format.raw/*105.3*/(""");
	
	
	
</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,lista:List[List[String]],fechaDesde:String,fechaHasta:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,lista,fechaDesde,fechaHasta)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,lista,fechaDesde,fechaHasta) => apply(mapDiccionario,mapPermiso,userMnu,lista,fechaDesde,fechaHasta)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reporteMovimientosListaProyectosAgrupado.scala.html
                  HASH: e9a0a258cafd6790a7897b71b54c9cdbfba0a1c7
                  MATRIX: 1073->1|1330->165|1362->172|1378->180|1417->182|1446->186|1514->234|1544->239|1599->274|1627->277|1669->299|1698->302|1742->325|1773->329|1850->380|1990->498|2020->501|3022->1476|3045->1490|3080->1504|3353->1750|3376->1764|3411->1778|3481->1821|3504->1835|3541->1851|3666->1950|3702->1970|3741->1971|3777->1980|3870->2046|3885->2052|3914->2060|4051->2169|4067->2175|4096->2182|4130->2188|4146->2194|4175->2201|4317->2315|4333->2321|4362->2328|4396->2334|4412->2340|4441->2347|4584->2462|4600->2468|4629->2475|4663->2481|4679->2487|4708->2494|4806->2565|4821->2571|4849->2578|4972->2674|4987->2680|5015->2687|5180->2825|5195->2831|5223->2838|5311->2899|5342->2909|5430->2970|5461->2980|5613->3105|5628->3111|5656->3118|5749->3184|5772->3198|5810->3215|5983->3361|5998->3367|6026->3374|6191->3512|6206->3518|6234->3525|6322->3586|6353->3596|6441->3657|6472->3667|6624->3792|6639->3798|6667->3805|6848->3955|6882->3962|6976->4026|7006->4029|7114->4109|7143->4110|7175->4115|7234->4146|7263->4147|7298->4155|7465->4294|7494->4295|7535->4307|7642->4385|7672->4386|7705->4391|7734->4392|7763->4393|7864->4466|7893->4467
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|39->8|39->8|40->9|40->9|41->10|41->10|43->12|44->13|44->13|45->14|69->38|69->38|69->38|72->41|72->41|72->41|73->42|73->42|73->42|78->47|78->47|78->47|79->48|80->49|80->49|80->49|81->50|81->50|81->50|81->50|81->50|81->50|82->51|82->51|82->51|82->51|82->51|82->51|83->52|83->52|83->52|83->52|83->52|83->52|84->53|84->53|84->53|86->55|86->55|86->55|87->56|87->56|87->56|88->57|88->57|89->58|89->58|91->60|91->60|91->60|92->61|92->61|92->61|97->66|97->66|97->66|98->67|98->67|98->67|99->68|99->68|100->69|100->69|102->71|102->71|102->71|108->77|109->78|117->86|120->89|122->91|122->91|124->93|124->93|124->93|125->94|130->99|130->99|131->100|132->101|132->101|133->102|133->102|133->102|136->105|136->105
                  -- GENERATED --
              */
          