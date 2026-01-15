
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

object reportGraficoMovUso extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,List[String],List[String],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
 graficoOcupacion: List[String], graficoOcupacionUnidad: List[String]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""
	
	"""),format.raw/*7.2*/("""<script src=""""),_display_(/*7.16*/routes/*7.22*/.Assets.versioned("highcharts/highcharts.js")),format.raw/*7.67*/(""""></script>
	<script src=""""),_display_(/*8.16*/routes/*8.22*/.Assets.versioned("highcharts/series-label.js")),format.raw/*8.69*/(""""></script>
	<script src=""""),_display_(/*9.16*/routes/*9.22*/.Assets.versioned("highcharts/exporting.js")),format.raw/*9.66*/(""""></script>
	<script src=""""),_display_(/*10.16*/routes/*10.22*/.Assets.versioned("highcharts/export-data.js")),format.raw/*10.68*/(""""></script>

	"""),_display_(/*12.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*12.51*/("""
	
	"""),format.raw/*14.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*15.4*/barraTitulo(mapDiccionario, "OCUPACIÓN POR GRUPO MENSUAL - SITUACIÓN GLOBAL","")),format.raw/*15.84*/("""
		
		"""),format.raw/*17.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<table id="ESQUEMAGENERAL" class="table table-sm table-bordered table-condensed table-fluid">
					<TR>
				  		<TD colspan="2" width="90%" id="ocupacion" style="height: auto; margin: 0 auto"></TD>
					</TR>
					<TR>
				  		<TD colspan="2" width="90%" id="ocupacionUnidad" style="height: auto; margin: 0 0"></TD>
					</TR>
				</table>
				
				<div class="noprint">
					<div class="row justify-content-md-center" >
						<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
							<input type="button"  value="VOLVER" class="btn btn-success btn-sm rounded-pill btn-block" onclick="window.history.back()">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
""")))}),format.raw/*38.2*/("""




"""),format.raw/*43.1*/("""<script type="text/javascript">
	$(document).ready(function () """),format.raw/*44.32*/("""{"""),format.raw/*44.33*/("""

			"""),format.raw/*46.4*/("""Highcharts.chart('ocupacion',"""),format.raw/*46.33*/("""{"""),format.raw/*46.34*/("""
	            """),format.raw/*47.14*/("""chart: """),format.raw/*47.21*/("""{"""),format.raw/*47.22*/("""
	                """),format.raw/*48.18*/("""zoomType: 'xy'
	            """),format.raw/*49.14*/("""}"""),format.raw/*49.15*/(""",
	            title: """),format.raw/*50.21*/("""{"""),format.raw/*50.22*/("""
	                 """),format.raw/*51.19*/("""text: '% OCUPACIÓN MENSUAL POR GRUPO (Valorizado)'
	            """),format.raw/*52.14*/("""}"""),format.raw/*52.15*/(""",
	            subtitle: """),format.raw/*53.24*/("""{"""),format.raw/*53.25*/("""
	                """),format.raw/*54.18*/("""text: ''
	            """),format.raw/*55.14*/("""}"""),format.raw/*55.15*/(""",
	            xAxis: ["""),format.raw/*56.22*/("""{"""),format.raw/*56.23*/("""
	            	"""),format.raw/*57.15*/("""categories: """),_display_(/*57.28*/Html(graficoOcupacion.get(0))),format.raw/*57.57*/(""",
	                labels: """),format.raw/*58.26*/("""{"""),format.raw/*58.27*/("""
	                    """),format.raw/*59.22*/("""rotation: -45,
	                    align: 'right',
	                    style: """),format.raw/*61.29*/("""{"""),format.raw/*61.30*/("""
	                        """),format.raw/*62.26*/("""fontSize: '10px',
	                        fontFamily: 'Verdana, sans-serif'
	                    """),format.raw/*64.22*/("""}"""),format.raw/*64.23*/("""
	                """),format.raw/*65.18*/("""}"""),format.raw/*65.19*/("""
	            """),format.raw/*66.14*/("""}"""),format.raw/*66.15*/("""],
	            yAxis: """),format.raw/*67.21*/("""{"""),format.raw/*67.22*/("""
	                """),format.raw/*68.18*/("""min: 0,
	                max: 100,
	                title: """),format.raw/*70.25*/("""{"""),format.raw/*70.26*/("""
	                    """),format.raw/*71.22*/("""text: 'Porcentaje'
	                """),format.raw/*72.18*/("""}"""),format.raw/*72.19*/(""",
	                stackLabels: """),format.raw/*73.31*/("""{"""),format.raw/*73.32*/("""
	                    """),format.raw/*74.22*/("""enabled: true,
	                    style: """),format.raw/*75.29*/("""{"""),format.raw/*75.30*/("""
	                        """),format.raw/*76.26*/("""fontWeight: 'bold',
	                        color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
	                    """),format.raw/*78.22*/("""}"""),format.raw/*78.23*/("""
	                """),format.raw/*79.18*/("""}"""),format.raw/*79.19*/("""
	            """),format.raw/*80.14*/("""}"""),format.raw/*80.15*/(""",
	            tooltip: """),format.raw/*81.23*/("""{"""),format.raw/*81.24*/("""
	                """),format.raw/*82.18*/("""shared: true
	            """),format.raw/*83.14*/("""}"""),format.raw/*83.15*/(""",
	            legend: """),format.raw/*84.22*/("""{"""),format.raw/*84.23*/("""
	                """),format.raw/*85.18*/("""layout: 'horizontal',
	                align: 'center',
	                verticalAlign: 'top',
	                y: 30,
	                floating: false,
	                borderWidth: 0,
	                backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'
	            """),format.raw/*92.14*/("""}"""),format.raw/*92.15*/(""",
	            plotOptions: """),format.raw/*93.27*/("""{"""),format.raw/*93.28*/("""
	                """),format.raw/*94.18*/("""spline: """),format.raw/*94.26*/("""{"""),format.raw/*94.27*/("""
	                    """),format.raw/*95.22*/("""lineWidth: 1,
	                    states: """),format.raw/*96.30*/("""{"""),format.raw/*96.31*/("""
	                        """),format.raw/*97.26*/("""hover: """),format.raw/*97.33*/("""{"""),format.raw/*97.34*/("""
	                            """),format.raw/*98.30*/("""lineWidth: 6
	                        """),format.raw/*99.26*/("""}"""),format.raw/*99.27*/("""
	                    """),format.raw/*100.22*/("""}"""),format.raw/*100.23*/(""",
	                    marker: """),format.raw/*101.30*/("""{"""),format.raw/*101.31*/("""
	                        """),format.raw/*102.26*/("""enabled: false
	                    """),format.raw/*103.22*/("""}"""),format.raw/*103.23*/("""
	                """),format.raw/*104.18*/("""}"""),format.raw/*104.19*/("""
	            """),format.raw/*105.14*/("""}"""),format.raw/*105.15*/(""",
	            series: [
	               """),_display_(/*107.18*/for(lista <- graficoOcupacion.tail) yield /*107.53*/{_display_(Seq[Any](format.raw/*107.54*/("""
	            	   """),_display_(/*108.19*/Html(lista)),format.raw/*108.30*/("""
	               """)))}),format.raw/*109.18*/("""

	            """),format.raw/*111.14*/("""]
	        """),format.raw/*112.10*/("""}"""),format.raw/*112.11*/(""");

			Highcharts.chart('ocupacionUnidad',"""),format.raw/*114.39*/("""{"""),format.raw/*114.40*/("""
	            """),format.raw/*115.14*/("""chart: """),format.raw/*115.21*/("""{"""),format.raw/*115.22*/("""
	                """),format.raw/*116.18*/("""zoomType: 'xy'
	            """),format.raw/*117.14*/("""}"""),format.raw/*117.15*/(""",
	            title: """),format.raw/*118.21*/("""{"""),format.raw/*118.22*/("""
	                 """),format.raw/*119.19*/("""text: '% OCUPACIÓN MENSUAL POR GRUPO (por unidades)'
	            """),format.raw/*120.14*/("""}"""),format.raw/*120.15*/(""",
	            subtitle: """),format.raw/*121.24*/("""{"""),format.raw/*121.25*/("""
	                """),format.raw/*122.18*/("""text: ''
	            """),format.raw/*123.14*/("""}"""),format.raw/*123.15*/(""",
	            xAxis: ["""),format.raw/*124.22*/("""{"""),format.raw/*124.23*/("""
	            	"""),format.raw/*125.15*/("""categories: """),_display_(/*125.28*/Html(graficoOcupacionUnidad.get(0))),format.raw/*125.63*/(""",
	                labels: """),format.raw/*126.26*/("""{"""),format.raw/*126.27*/("""
	                    """),format.raw/*127.22*/("""rotation: -45,
	                    align: 'right',
	                    style: """),format.raw/*129.29*/("""{"""),format.raw/*129.30*/("""
	                        """),format.raw/*130.26*/("""fontSize: '10px',
	                        fontFamily: 'Verdana, sans-serif'
	                    """),format.raw/*132.22*/("""}"""),format.raw/*132.23*/("""
	                """),format.raw/*133.18*/("""}"""),format.raw/*133.19*/("""
	            """),format.raw/*134.14*/("""}"""),format.raw/*134.15*/("""],
	            yAxis: """),format.raw/*135.21*/("""{"""),format.raw/*135.22*/("""
	                """),format.raw/*136.18*/("""min: 0,
	                max: 100,
	                title: """),format.raw/*138.25*/("""{"""),format.raw/*138.26*/("""
	                    """),format.raw/*139.22*/("""text: 'Porcentaje'
	                """),format.raw/*140.18*/("""}"""),format.raw/*140.19*/(""",
	                stackLabels: """),format.raw/*141.31*/("""{"""),format.raw/*141.32*/("""
	                    """),format.raw/*142.22*/("""enabled: true,
	                    style: """),format.raw/*143.29*/("""{"""),format.raw/*143.30*/("""
	                        """),format.raw/*144.26*/("""fontWeight: 'bold',
	                        color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
	                    """),format.raw/*146.22*/("""}"""),format.raw/*146.23*/("""
	                """),format.raw/*147.18*/("""}"""),format.raw/*147.19*/("""
	            """),format.raw/*148.14*/("""}"""),format.raw/*148.15*/(""",
	            tooltip: """),format.raw/*149.23*/("""{"""),format.raw/*149.24*/("""
	                """),format.raw/*150.18*/("""shared: true
	            """),format.raw/*151.14*/("""}"""),format.raw/*151.15*/(""",
	            legend: """),format.raw/*152.22*/("""{"""),format.raw/*152.23*/("""
	                """),format.raw/*153.18*/("""layout: 'horizontal',
	                align: 'center',
	                verticalAlign: 'top',
	                y: 30,
	                floating: false,
	                borderWidth: 0,
	                backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'
	            """),format.raw/*160.14*/("""}"""),format.raw/*160.15*/(""",
	            plotOptions: """),format.raw/*161.27*/("""{"""),format.raw/*161.28*/("""
	                """),format.raw/*162.18*/("""spline: """),format.raw/*162.26*/("""{"""),format.raw/*162.27*/("""
	                    """),format.raw/*163.22*/("""lineWidth: 1,
	                    states: """),format.raw/*164.30*/("""{"""),format.raw/*164.31*/("""
	                        """),format.raw/*165.26*/("""hover: """),format.raw/*165.33*/("""{"""),format.raw/*165.34*/("""
	                            """),format.raw/*166.30*/("""lineWidth: 6
	                        """),format.raw/*167.26*/("""}"""),format.raw/*167.27*/("""
	                    """),format.raw/*168.22*/("""}"""),format.raw/*168.23*/(""",
	                    marker: """),format.raw/*169.30*/("""{"""),format.raw/*169.31*/("""
	                        """),format.raw/*170.26*/("""enabled: false
	                    """),format.raw/*171.22*/("""}"""),format.raw/*171.23*/("""
	                """),format.raw/*172.18*/("""}"""),format.raw/*172.19*/("""
	            """),format.raw/*173.14*/("""}"""),format.raw/*173.15*/(""",
	            series: [
	               """),_display_(/*175.18*/for(lista <- graficoOcupacionUnidad.tail) yield /*175.59*/{_display_(Seq[Any](format.raw/*175.60*/("""
	            	   """),_display_(/*176.19*/Html(lista)),format.raw/*176.30*/("""
	               """)))}),format.raw/*177.18*/("""

	            """),format.raw/*179.14*/("""]
	        """),format.raw/*180.10*/("""}"""),format.raw/*180.11*/(""");


			document.getElementById('mostrarmostrar').style.display="block";
	   """),format.raw/*184.5*/("""}"""),format.raw/*184.6*/(""");
			  
	
</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,graficoOcupacion:List[String],graficoOcupacionUnidad:List[String]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,graficoOcupacion,graficoOcupacionUnidad)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[String],List[String]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,graficoOcupacion,graficoOcupacionUnidad) => apply(mapDiccionario,mapPermiso,userMnu,graficoOcupacion,graficoOcupacionUnidad)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reportGraficoMovUso.scala.html
                  HASH: 0a59b3a64e51d736d6e6e314d0971bdd24a08bf1
                  MATRIX: 1045->1|1306->169|1339->177|1355->185|1394->187|1424->191|1464->205|1478->211|1543->256|1596->283|1610->289|1677->336|1730->363|1744->369|1808->413|1862->440|1877->446|1944->492|1985->507|2054->555|2085->559|2162->610|2263->690|2296->696|3104->1474|3136->1479|3227->1542|3256->1543|3288->1548|3345->1577|3374->1578|3416->1592|3451->1599|3480->1600|3526->1618|3582->1646|3611->1647|3661->1669|3690->1670|3737->1689|3829->1753|3858->1754|3911->1779|3940->1780|3986->1798|4036->1820|4065->1821|4116->1844|4145->1845|4188->1860|4228->1873|4278->1902|4333->1929|4362->1930|4412->1952|4520->2032|4549->2033|4603->2059|4729->2157|4758->2158|4804->2176|4833->2177|4875->2191|4904->2192|4955->2215|4984->2216|5030->2234|5117->2293|5146->2294|5196->2316|5260->2352|5289->2353|5349->2385|5378->2386|5428->2408|5499->2451|5528->2452|5582->2478|5742->2610|5771->2611|5817->2629|5846->2630|5888->2644|5917->2645|5969->2669|5998->2670|6044->2688|6098->2714|6127->2715|6178->2738|6207->2739|6253->2757|6588->3064|6617->3065|6673->3093|6702->3094|6748->3112|6784->3120|6813->3121|6863->3143|6934->3186|6963->3187|7017->3213|7052->3220|7081->3221|7139->3251|7205->3289|7234->3290|7285->3312|7315->3313|7375->3344|7405->3345|7460->3371|7525->3407|7555->3408|7602->3426|7632->3427|7675->3441|7705->3442|7775->3484|7827->3519|7867->3520|7914->3539|7947->3550|7997->3568|8041->3583|8081->3594|8111->3595|8182->3637|8212->3638|8255->3652|8291->3659|8321->3660|8368->3678|8425->3706|8455->3707|8506->3729|8536->3730|8584->3749|8679->3815|8709->3816|8763->3841|8793->3842|8840->3860|8891->3882|8921->3883|8973->3906|9003->3907|9047->3922|9088->3935|9145->3970|9201->3997|9231->3998|9282->4020|9391->4100|9421->4101|9476->4127|9603->4225|9633->4226|9680->4244|9710->4245|9753->4259|9783->4260|9835->4283|9865->4284|9912->4302|10000->4361|10030->4362|10081->4384|10146->4420|10176->4421|10237->4453|10267->4454|10318->4476|10390->4519|10420->4520|10475->4546|10636->4678|10666->4679|10713->4697|10743->4698|10786->4712|10816->4713|10869->4737|10899->4738|10946->4756|11001->4782|11031->4783|11083->4806|11113->4807|11160->4825|11496->5132|11526->5133|11583->5161|11613->5162|11660->5180|11697->5188|11727->5189|11778->5211|11850->5254|11880->5255|11935->5281|11971->5288|12001->5289|12060->5319|12127->5357|12157->5358|12208->5380|12238->5381|12298->5412|12328->5413|12383->5439|12448->5475|12478->5476|12525->5494|12555->5495|12598->5509|12628->5510|12698->5552|12756->5593|12796->5594|12843->5613|12876->5624|12926->5642|12970->5657|13010->5668|13040->5669|13145->5746|13174->5747
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|38->7|38->7|39->8|39->8|39->8|40->9|40->9|40->9|41->10|41->10|41->10|43->12|43->12|45->14|46->15|46->15|48->17|69->38|74->43|75->44|75->44|77->46|77->46|77->46|78->47|78->47|78->47|79->48|80->49|80->49|81->50|81->50|82->51|83->52|83->52|84->53|84->53|85->54|86->55|86->55|87->56|87->56|88->57|88->57|88->57|89->58|89->58|90->59|92->61|92->61|93->62|95->64|95->64|96->65|96->65|97->66|97->66|98->67|98->67|99->68|101->70|101->70|102->71|103->72|103->72|104->73|104->73|105->74|106->75|106->75|107->76|109->78|109->78|110->79|110->79|111->80|111->80|112->81|112->81|113->82|114->83|114->83|115->84|115->84|116->85|123->92|123->92|124->93|124->93|125->94|125->94|125->94|126->95|127->96|127->96|128->97|128->97|128->97|129->98|130->99|130->99|131->100|131->100|132->101|132->101|133->102|134->103|134->103|135->104|135->104|136->105|136->105|138->107|138->107|138->107|139->108|139->108|140->109|142->111|143->112|143->112|145->114|145->114|146->115|146->115|146->115|147->116|148->117|148->117|149->118|149->118|150->119|151->120|151->120|152->121|152->121|153->122|154->123|154->123|155->124|155->124|156->125|156->125|156->125|157->126|157->126|158->127|160->129|160->129|161->130|163->132|163->132|164->133|164->133|165->134|165->134|166->135|166->135|167->136|169->138|169->138|170->139|171->140|171->140|172->141|172->141|173->142|174->143|174->143|175->144|177->146|177->146|178->147|178->147|179->148|179->148|180->149|180->149|181->150|182->151|182->151|183->152|183->152|184->153|191->160|191->160|192->161|192->161|193->162|193->162|193->162|194->163|195->164|195->164|196->165|196->165|196->165|197->166|198->167|198->167|199->168|199->168|200->169|200->169|201->170|202->171|202->171|203->172|203->172|204->173|204->173|206->175|206->175|206->175|207->176|207->176|208->177|210->179|211->180|211->180|215->184|215->184
                  -- GENERATED --
              */
          