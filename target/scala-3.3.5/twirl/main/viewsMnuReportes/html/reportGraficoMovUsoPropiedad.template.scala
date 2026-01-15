
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

object reportGraficoMovUsoPropiedad extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,List[String],List[String],play.twirl.api.HtmlFormat.Appendable] {

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
		"""),_display_(/*15.4*/barraTitulo(mapDiccionario, "OCUPACIÓN MENSUAL POR PROPIEDAD - SITUACIÓN GLOBAL","")),format.raw/*15.88*/("""
		
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
	                 """),format.raw/*51.19*/("""text: '% OCUPACIÓN MENSUAL POR PROPIEDAD (Valorizado)'
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
	                 """),format.raw/*119.19*/("""text: '% OCUPACIÓN MENSUAL POR PROPIEDAD (por unidades)'
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
                  SOURCE: app/viewsMnuReportes/reportGraficoMovUsoPropiedad.scala.html
                  HASH: 5df7ed2fb505a4f2717db861d89403e97d44706a
                  MATRIX: 1054->1|1315->169|1348->177|1364->185|1403->187|1433->191|1473->205|1487->211|1552->256|1605->283|1619->289|1686->336|1739->363|1753->369|1817->413|1871->440|1886->446|1953->492|1994->507|2063->555|2094->559|2171->610|2276->694|2309->700|3117->1478|3149->1483|3240->1546|3269->1547|3301->1552|3358->1581|3387->1582|3429->1596|3464->1603|3493->1604|3539->1622|3595->1650|3624->1651|3674->1673|3703->1674|3750->1693|3846->1761|3875->1762|3928->1787|3957->1788|4003->1806|4053->1828|4082->1829|4133->1852|4162->1853|4205->1868|4245->1881|4295->1910|4350->1937|4379->1938|4429->1960|4537->2040|4566->2041|4620->2067|4746->2165|4775->2166|4821->2184|4850->2185|4892->2199|4921->2200|4972->2223|5001->2224|5047->2242|5134->2301|5163->2302|5213->2324|5277->2360|5306->2361|5366->2393|5395->2394|5445->2416|5516->2459|5545->2460|5599->2486|5759->2618|5788->2619|5834->2637|5863->2638|5905->2652|5934->2653|5986->2677|6015->2678|6061->2696|6115->2722|6144->2723|6195->2746|6224->2747|6270->2765|6605->3072|6634->3073|6690->3101|6719->3102|6765->3120|6801->3128|6830->3129|6880->3151|6951->3194|6980->3195|7034->3221|7069->3228|7098->3229|7156->3259|7222->3297|7251->3298|7302->3320|7332->3321|7392->3352|7422->3353|7477->3379|7542->3415|7572->3416|7619->3434|7649->3435|7692->3449|7722->3450|7792->3492|7844->3527|7884->3528|7931->3547|7964->3558|8014->3576|8058->3591|8098->3602|8128->3603|8199->3645|8229->3646|8272->3660|8308->3667|8338->3668|8385->3686|8442->3714|8472->3715|8523->3737|8553->3738|8601->3757|8700->3827|8730->3828|8784->3853|8814->3854|8861->3872|8912->3894|8942->3895|8994->3918|9024->3919|9068->3934|9109->3947|9166->3982|9222->4009|9252->4010|9303->4032|9412->4112|9442->4113|9497->4139|9624->4237|9654->4238|9701->4256|9731->4257|9774->4271|9804->4272|9856->4295|9886->4296|9933->4314|10021->4373|10051->4374|10102->4396|10167->4432|10197->4433|10258->4465|10288->4466|10339->4488|10411->4531|10441->4532|10496->4558|10657->4690|10687->4691|10734->4709|10764->4710|10807->4724|10837->4725|10890->4749|10920->4750|10967->4768|11022->4794|11052->4795|11104->4818|11134->4819|11181->4837|11517->5144|11547->5145|11604->5173|11634->5174|11681->5192|11718->5200|11748->5201|11799->5223|11871->5266|11901->5267|11956->5293|11992->5300|12022->5301|12081->5331|12148->5369|12178->5370|12229->5392|12259->5393|12319->5424|12349->5425|12404->5451|12469->5487|12499->5488|12546->5506|12576->5507|12619->5521|12649->5522|12719->5564|12777->5605|12817->5606|12864->5625|12897->5636|12947->5654|12991->5669|13031->5680|13061->5681|13166->5758|13195->5759
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|38->7|38->7|39->8|39->8|39->8|40->9|40->9|40->9|41->10|41->10|41->10|43->12|43->12|45->14|46->15|46->15|48->17|69->38|74->43|75->44|75->44|77->46|77->46|77->46|78->47|78->47|78->47|79->48|80->49|80->49|81->50|81->50|82->51|83->52|83->52|84->53|84->53|85->54|86->55|86->55|87->56|87->56|88->57|88->57|88->57|89->58|89->58|90->59|92->61|92->61|93->62|95->64|95->64|96->65|96->65|97->66|97->66|98->67|98->67|99->68|101->70|101->70|102->71|103->72|103->72|104->73|104->73|105->74|106->75|106->75|107->76|109->78|109->78|110->79|110->79|111->80|111->80|112->81|112->81|113->82|114->83|114->83|115->84|115->84|116->85|123->92|123->92|124->93|124->93|125->94|125->94|125->94|126->95|127->96|127->96|128->97|128->97|128->97|129->98|130->99|130->99|131->100|131->100|132->101|132->101|133->102|134->103|134->103|135->104|135->104|136->105|136->105|138->107|138->107|138->107|139->108|139->108|140->109|142->111|143->112|143->112|145->114|145->114|146->115|146->115|146->115|147->116|148->117|148->117|149->118|149->118|150->119|151->120|151->120|152->121|152->121|153->122|154->123|154->123|155->124|155->124|156->125|156->125|156->125|157->126|157->126|158->127|160->129|160->129|161->130|163->132|163->132|164->133|164->133|165->134|165->134|166->135|166->135|167->136|169->138|169->138|170->139|171->140|171->140|172->141|172->141|173->142|174->143|174->143|175->144|177->146|177->146|178->147|178->147|179->148|179->148|180->149|180->149|181->150|182->151|182->151|183->152|183->152|184->153|191->160|191->160|192->161|192->161|193->162|193->162|193->162|194->163|195->164|195->164|196->165|196->165|196->165|197->166|198->167|198->167|199->168|199->168|200->169|200->169|201->170|202->171|202->171|203->172|203->172|204->173|204->173|206->175|206->175|206->175|207->176|207->176|208->177|210->179|211->180|211->180|215->184|215->184
                  -- GENERATED --
              */
          