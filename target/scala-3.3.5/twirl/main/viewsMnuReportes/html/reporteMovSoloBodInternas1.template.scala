
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

object reporteMovSoloBodInternas1 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String,play.twirl.api.HtmlFormat.Appendable] {

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
		"""),_display_(/*13.4*/barraTitulo(mapDiccionario, "PERIODO DE MOVIMIENTOS POR " + mapDiccionario.get("BODEGA")+" INTERNAS","")),format.raw/*13.108*/("""
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
								<TH style= "text-align: center;vertical-align: top;">"""),_display_(/*38.63*/mapDiccionario/*38.77*/.get("BODEGA")),format.raw/*38.91*/("""/PROYECTO</TH>
								<TH style= "text-align: center;vertical-align: top;">NOMBRE<BR>PROPIETARIO</TH>
								<TH width="5%" >REPORTE<BR></TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*44.9*/for(lista1 <- lista) yield /*44.29*/{_display_(Seq[Any](format.raw/*44.30*/("""
								"""),format.raw/*45.9*/("""<TR>
									<td style="text-align:left;vertical-align:middle;">"""),_display_(/*46.62*/lista1/*46.68*/.get(11)),format.raw/*46.76*/("""</td>
									<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="modalContactoBodega('"""),_display_(/*47.104*/lista1/*47.110*/.get(1)),format.raw/*47.117*/("""');">"""),_display_(/*47.123*/lista1/*47.129*/.get(5)),format.raw/*47.136*/("""</a></td>
									<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="modalContactoCliente('"""),_display_(/*48.105*/lista1/*48.111*/.get(2)),format.raw/*48.118*/("""');">"""),_display_(/*48.124*/lista1/*48.130*/.get(7)),format.raw/*48.137*/("""</a></td>
									<td style="text-align:center;vertical-align:middle;">
										<form id="form0_"""),_display_(/*50.28*/lista1/*50.34*/.get(1)),format.raw/*50.41*/("""" class="formulario" method="post" action="/reporteMovSoloBodInternas2/">
											<input type="hidden" name="id_bodega" value=""""),_display_(/*51.58*/lista1/*51.64*/.get(1)),format.raw/*51.71*/("""">
											<input type="hidden" name="fechaDesde" value=""""),_display_(/*52.59*/fechaDesde),format.raw/*52.69*/("""">
											<input type="hidden" name="fechaHasta" value=""""),_display_(/*53.59*/fechaHasta),format.raw/*53.69*/("""">
											<a href="#" onclick="document.getElementById('form0_"""),_display_(/*54.65*/lista1/*54.71*/.get(1)),format.raw/*54.78*/("""').submit()">
												<kbd style="background-color: #73C6B6">generar</kbd>
											</a>
										</form>
									</td>
								</TR>
				 			""")))}),format.raw/*60.10*/("""
						"""),format.raw/*61.7*/("""</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>


""")))}),format.raw/*69.2*/("""


"""),format.raw/*72.1*/("""<script type="text/javascript">
	let flag = true;
	$(document).ready(function() """),format.raw/*74.31*/("""{"""),format.raw/*74.32*/("""

		 """),format.raw/*76.4*/("""$('#tablaPrincipal').DataTable("""),format.raw/*76.35*/("""{"""),format.raw/*76.36*/("""
		    	"""),format.raw/*77.8*/(""""fixedHeader": true,
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [[ 1, "asc" ]],
		    	"language": """),format.raw/*82.20*/("""{"""),format.raw/*82.21*/("""
		        	"""),format.raw/*83.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*84.11*/("""}"""),format.raw/*84.12*/("""
		  """),format.raw/*85.5*/("""}"""),format.raw/*85.6*/(""" """),format.raw/*85.7*/(""");

			document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*88.2*/("""}"""),format.raw/*88.3*/(""");
	
	
	
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
                  SOURCE: app/viewsMnuReportes/reporteMovSoloBodInternas1.scala.html
                  HASH: 280b57f0285b1baf79082516c025693424197be0
                  MATRIX: 1059->1|1316->165|1348->172|1364->180|1403->182|1432->186|1500->234|1530->239|1585->274|1613->277|1655->299|1684->302|1728->325|1759->329|1836->380|1962->484|1992->487|2994->1462|3017->1476|3052->1490|3272->1684|3308->1704|3347->1705|3383->1714|3476->1780|3491->1786|3520->1794|3657->1903|3673->1909|3702->1916|3736->1922|3752->1928|3781->1935|3923->2049|3939->2055|3968->2062|4002->2068|4018->2074|4047->2081|4174->2181|4189->2187|4217->2194|4375->2325|4390->2331|4418->2338|4506->2399|4537->2409|4625->2470|4656->2480|4750->2547|4765->2553|4793->2560|4975->2711|5009->2718|5103->2782|5133->2785|5241->2865|5270->2866|5302->2871|5361->2902|5390->2903|5425->2911|5592->3050|5621->3051|5661->3063|5767->3141|5796->3142|5828->3147|5856->3148|5884->3149|5984->3222|6012->3223
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|39->8|39->8|40->9|40->9|41->10|41->10|43->12|44->13|44->13|45->14|69->38|69->38|69->38|75->44|75->44|75->44|76->45|77->46|77->46|77->46|78->47|78->47|78->47|78->47|78->47|78->47|79->48|79->48|79->48|79->48|79->48|79->48|81->50|81->50|81->50|82->51|82->51|82->51|83->52|83->52|84->53|84->53|85->54|85->54|85->54|91->60|92->61|100->69|103->72|105->74|105->74|107->76|107->76|107->76|108->77|113->82|113->82|114->83|115->84|115->84|116->85|116->85|116->85|119->88|119->88
                  -- GENERATED --
              */
          