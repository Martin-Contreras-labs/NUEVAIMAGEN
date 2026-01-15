
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

object reporteMovOdoValorizadoLista extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template11[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String,Double,Double,Double,String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
totalesPorProyecto: List[List[String]], fechaDesde: String, fechaHasta: String, usd: Double, eur: Double, uf: Double, desdeDDMMAA: String, hastaDDMMAA: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""
	
	"""),_display_(/*6.3*/modalContactoBodega(mapDiccionario)),format.raw/*6.38*/("""
	"""),_display_(/*7.3*/modalContactoCliente()),format.raw/*7.25*/("""
	"""),_display_(/*8.3*/modalContactoProyecto()),format.raw/*8.26*/("""

	"""),_display_(/*10.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*10.51*/("""
	"""),format.raw/*11.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*12.4*/barraTitulo(mapDiccionario, "SELECCION DE "+mapDiccionario.get("BODEGA")+"/PROYECTO","REPORTE ODO DE MOVIMIENTOS (VALORIZADO)")),format.raw/*12.131*/("""
		"""),format.raw/*13.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
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
				<h5>Período desde """),_display_(/*31.24*/desdeDDMMAA),format.raw/*31.35*/(""" """),format.raw/*31.36*/("""a """),_display_(/*31.39*/hastaDDMMAA),format.raw/*31.50*/("""</h5>
				<div class="table-responsive">
					<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
				        <thead style="background-color: #eeeeee">
					        <TR> 
						        <TH style= "text-align: center;vertical-align: top;">"""),_display_(/*36.69*/mapDiccionario/*36.83*/.get("BODEGA")),format.raw/*36.97*/("""/PROYECTO</TH>
								<TH style= "text-align: center;vertical-align: top;">NOMBRE<BR>CLIENTE</TH>
								<TH style= "text-align: center;vertical-align: top;">NOMBRE<br>PROYECTO</TH>
								<TH style= "text-align: center;vertical-align: top;">COMERCIAL</TH>
								
								<TH style= "text-align:center;vertical-align:top;">SUBTOTAL<BR>(en """),_display_(/*41.77*/mapDiccionario/*41.91*/.get("PESOS")),format.raw/*41.104*/(""")</TH>
								<TH style= "text-align:center;vertical-align:top;">AJUSTES<BR>(en """),_display_(/*42.76*/mapDiccionario/*42.90*/.get("PESOS")),format.raw/*42.103*/(""")</TH>
								<TH style= "text-align:center;vertical-align:top;">TOTAL<BR>(en """),_display_(/*43.74*/mapDiccionario/*43.88*/.get("PESOS")),format.raw/*43.101*/(""")</TH>
								
								<TH width="5%" >DETALLE<BR></TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*49.9*/for(lista1 <- totalesPorProyecto) yield /*49.42*/{_display_(Seq[Any](format.raw/*49.43*/("""
								"""),format.raw/*50.9*/("""<TR>
									<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="modalContactoBodega('"""),_display_(/*51.104*/lista1/*51.110*/.get(0)),format.raw/*51.117*/("""');">"""),_display_(/*51.123*/lista1/*51.129*/.get(1)),format.raw/*51.136*/("""</a></td>
									<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="modalContactoCliente('"""),_display_(/*52.105*/lista1/*52.111*/.get(8)),format.raw/*52.118*/("""');">"""),_display_(/*52.124*/lista1/*52.130*/.get(2)),format.raw/*52.137*/("""</a></td>
									<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="modalContactoProyecto('"""),_display_(/*53.106*/lista1/*53.112*/.get(9)),format.raw/*53.119*/("""');">"""),_display_(/*53.125*/lista1/*53.131*/.get(3)),format.raw/*53.138*/("""</a></td>
									
									<td style="text-align:left;vertical-align:middle;">"""),_display_(/*55.62*/lista1/*55.68*/.get(4)),format.raw/*55.75*/("""</td>
									<td style= "text-align:right;" class="subtotal">"""),_display_(/*56.59*/lista1/*56.65*/.get(5)),format.raw/*56.72*/("""</td>
									<td style= "text-align:right;" class="ajuste">"""),_display_(/*57.57*/lista1/*57.63*/.get(6)),format.raw/*57.70*/("""</td>
									<td style= "text-align:right;" class="total">"""),_display_(/*58.56*/lista1/*58.62*/.get(7)),format.raw/*58.69*/("""</td>
									
									<td style="text-align:center;vertical-align:middle;">
										<form id="form0_"""),_display_(/*61.28*/lista1/*61.34*/.get(0)),format.raw/*61.41*/("""" class="formulario" method="post" action="/reporteMovOdoValorizadoDetalle/">
											<input type="hidden" name="id_bodega" value=""""),_display_(/*62.58*/lista1/*62.64*/.get(0)),format.raw/*62.71*/("""">
											<input type="hidden" name="fechaDesde" value=""""),_display_(/*63.59*/fechaDesde),format.raw/*63.69*/("""">
											<input type="hidden" name="fechaHasta" value=""""),_display_(/*64.59*/fechaHasta),format.raw/*64.69*/("""">
											<input type="hidden" name="uf" value=""""),_display_(/*65.51*/uf),format.raw/*65.53*/("""">
											<input type="hidden" name="usd" value=""""),_display_(/*66.52*/usd),format.raw/*66.55*/("""">
											<input type="hidden" name="eur" value=""""),_display_(/*67.52*/eur),format.raw/*67.55*/("""">
											<a href="#" onclick="document.getElementById('form0_"""),_display_(/*68.65*/lista1/*68.71*/.get(0)),format.raw/*68.78*/("""').submit()">
												<kbd style="background-color: #73C6B6">Generar</kbd>
											</a>
										</form>
									</td>
								</TR>
				 			""")))}),format.raw/*74.10*/("""
						"""),format.raw/*75.7*/("""</tbody>
						<tfoot>
							<TR>
								<td style="background-color: #eeeeee">TOTALES</td>
								<td style="background-color: #eeeeee"></td>
								<td style="background-color: #eeeeee"></td>
								<td style="background-color: #eeeeee"></td>
								<td style="background-color: #eeeeee;text-align:right;" id="subtotal"></td>
								<td style="background-color: #eeeeee;text-align:right;" id="ajuste"></td>
								<td style="background-color: #eeeeee;text-align:right;" id="total"></td>
								<td style="background-color: #eeeeee;text-align:right;">
							</TR>
						</tfoot>
					</table>
				</div>
			</div>
		</div>
	</div>
	


""")))}),format.raw/*96.2*/("""


"""),format.raw/*99.1*/("""<script type="text/javascript">
	
	$(document).ready(function() """),format.raw/*101.31*/("""{"""),format.raw/*101.32*/("""

		 """),format.raw/*103.4*/("""$('#tablaPrincipal').DataTable("""),format.raw/*103.35*/("""{"""),format.raw/*103.36*/("""
		    	"""),format.raw/*104.8*/(""""fixedHeader": true,
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [[ 0, "asc" ]],
		    	"language": """),format.raw/*109.20*/("""{"""),format.raw/*109.21*/("""
		        	"""),format.raw/*110.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*111.11*/("""}"""),format.raw/*111.12*/("""
		  """),format.raw/*112.5*/("""}"""),format.raw/*112.6*/(""" """),format.raw/*112.7*/(""");

		let subtotal = 0;
		$(".subtotal").each(function() """),format.raw/*115.34*/("""{"""),format.raw/*115.35*/("""
			"""),format.raw/*116.4*/("""let val = $(this).text().replace(/,/g,'');
			subtotal += parseFloat(val);
		"""),format.raw/*118.3*/("""}"""),format.raw/*118.4*/(""");
		$("#subtotal").text(formatStandar(subtotal,0));
		
		let ajuste = 0;
		$(".ajuste").each(function() """),format.raw/*122.32*/("""{"""),format.raw/*122.33*/("""
			"""),format.raw/*123.4*/("""let val = $(this).text().replace(/,/g,'');
			ajuste += parseFloat(val);
		"""),format.raw/*125.3*/("""}"""),format.raw/*125.4*/(""");
		$("#ajuste").text(formatStandar(ajuste,0));
		
		let total = 0;
		$(".total").each(function() """),format.raw/*129.31*/("""{"""),format.raw/*129.32*/("""
			"""),format.raw/*130.4*/("""let val = $(this).text().replace(/,/g,'');
			total += parseFloat(val);
		"""),format.raw/*132.3*/("""}"""),format.raw/*132.4*/(""");
		$("#total").text(formatStandar(total,0));
		
		document.getElementById('mostrarmostrar').style.display="block";
		
		
	"""),format.raw/*138.2*/("""}"""),format.raw/*138.3*/(""");
	
	
	
</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,totalesPorProyecto:List[List[String]],fechaDesde:String,fechaHasta:String,usd:Double,eur:Double,uf:Double,desdeDDMMAA:String,hastaDDMMAA:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,totalesPorProyecto,fechaDesde,fechaHasta,usd,eur,uf,desdeDDMMAA,hastaDDMMAA)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String,Double,Double,Double,String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,totalesPorProyecto,fechaDesde,fechaHasta,usd,eur,uf,desdeDDMMAA,hastaDDMMAA) => apply(mapDiccionario,mapPermiso,userMnu,totalesPorProyecto,fechaDesde,fechaHasta,usd,eur,uf,desdeDDMMAA,hastaDDMMAA)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuOdo/reporteMovOdoValorizadoLista.scala.html
                  HASH: 65086a4c541cea54a4714c39623085e9c6190332
                  MATRIX: 1092->1|1442->258|1474->265|1490->273|1529->275|1559->280|1614->315|1642->318|1684->340|1712->343|1755->366|1785->370|1854->418|1883->420|1960->471|2109->598|2139->601|2800->1235|2832->1246|2861->1247|2891->1250|2923->1261|3243->1554|3266->1568|3301->1582|3673->1927|3696->1941|3731->1954|3840->2036|3863->2050|3898->2063|4005->2143|4028->2157|4063->2170|4196->2277|4245->2310|4284->2311|4320->2320|4456->2428|4472->2434|4501->2441|4535->2447|4551->2453|4580->2460|4722->2574|4738->2580|4767->2587|4801->2593|4817->2599|4846->2606|4989->2721|5005->2727|5034->2734|5068->2740|5084->2746|5113->2753|5221->2834|5236->2840|5264->2847|5355->2911|5370->2917|5398->2924|5487->2986|5502->2992|5530->2999|5618->3060|5633->3066|5661->3073|5794->3179|5809->3185|5837->3192|5999->3327|6014->3333|6042->3340|6130->3401|6161->3411|6249->3472|6280->3482|6360->3535|6383->3537|6464->3591|6488->3594|6569->3648|6593->3651|6687->3718|6702->3724|6730->3731|6912->3882|6946->3889|7623->4536|7653->4539|7746->4603|7776->4604|7809->4609|7869->4640|7899->4641|7935->4649|8103->4788|8133->4789|8174->4801|8281->4879|8311->4880|8344->4885|8373->4886|8402->4887|8488->4944|8518->4945|8550->4949|8655->5026|8684->5027|8818->5132|8848->5133|8880->5137|8983->5212|9012->5213|9140->5312|9170->5313|9202->5317|9304->5391|9333->5392|9485->5516|9514->5517
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|38->7|38->7|39->8|39->8|41->10|41->10|42->11|43->12|43->12|44->13|62->31|62->31|62->31|62->31|62->31|67->36|67->36|67->36|72->41|72->41|72->41|73->42|73->42|73->42|74->43|74->43|74->43|80->49|80->49|80->49|81->50|82->51|82->51|82->51|82->51|82->51|82->51|83->52|83->52|83->52|83->52|83->52|83->52|84->53|84->53|84->53|84->53|84->53|84->53|86->55|86->55|86->55|87->56|87->56|87->56|88->57|88->57|88->57|89->58|89->58|89->58|92->61|92->61|92->61|93->62|93->62|93->62|94->63|94->63|95->64|95->64|96->65|96->65|97->66|97->66|98->67|98->67|99->68|99->68|99->68|105->74|106->75|127->96|130->99|132->101|132->101|134->103|134->103|134->103|135->104|140->109|140->109|141->110|142->111|142->111|143->112|143->112|143->112|146->115|146->115|147->116|149->118|149->118|153->122|153->122|154->123|156->125|156->125|160->129|160->129|161->130|163->132|163->132|169->138|169->138
                  -- GENERATED --
              */
          