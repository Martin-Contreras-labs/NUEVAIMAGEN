
package viewsMnuPlanes.html

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

object hojaVidaReportGrafico extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template7[Map[String,String],Map[String,String],utilities.UserMnu,List[String],List[List[String]],Long,Long,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
series: List[String], listGrupos: List[List[String]], idGrupo: Long, periodo: Long):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""
	
	"""),format.raw/*6.2*/("""<script src=""""),_display_(/*6.16*/routes/*6.22*/.Assets.versioned("highcharts/highcharts.js")),format.raw/*6.67*/(""""></script>
	<script src=""""),_display_(/*7.16*/routes/*7.22*/.Assets.versioned("highcharts/series-label.js")),format.raw/*7.69*/(""""></script>
	<script src=""""),_display_(/*8.16*/routes/*8.22*/.Assets.versioned("highcharts/exporting.js")),format.raw/*8.66*/(""""></script>
	<script src=""""),_display_(/*9.16*/routes/*9.22*/.Assets.versioned("highcharts/export-data.js")),format.raw/*9.68*/(""""></script>

	"""),_display_(/*11.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*11.51*/("""
	
	"""),format.raw/*13.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*14.4*/barraTitulo(mapDiccionario, "GRAFICO MANTENCIONES ULTIMOS " + periodo + " DIAS","")),format.raw/*14.87*/("""
		
		"""),format.raw/*16.3*/("""<br><br>
		<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<table id="ESQUEMAGENERAL" class="table table-sm table-bordered table-condensed table-fluid">
					<TR style="min-height:100% ¡required">
				  		<TD width="85%" id="grafico30" style="height: auto; margin: 0 0"></TD>
				  		<TD width="15%" style="vertical-align:top; height:800px;">
					  		<div id="radio">
						  		<h6>GRUPOS:</h6>
									<input type="radio" id="0" onclick="location.href = '/hojaVidaReportGrafico/0,"""),_display_(/*25.89*/periodo),format.raw/*25.96*/("""'">Todos<br>
							  		"""),_display_(/*26.13*/for(lista <- listGrupos) yield /*26.37*/{_display_(Seq[Any](format.raw/*26.38*/("""
							  			"""),format.raw/*27.13*/("""<input type="radio" id=""""),_display_(/*27.38*/lista/*27.43*/.get(0)),format.raw/*27.50*/("""" onclick="location.href = '/hojaVidaReportGrafico/"""),_display_(/*27.102*/lista/*27.107*/.get(0)),format.raw/*27.114*/(""","""),_display_(/*27.116*/periodo),format.raw/*27.123*/("""'">"""),_display_(/*27.127*/lista/*27.132*/.get(1)),format.raw/*27.139*/("""<br>
							  		""")))}),format.raw/*28.13*/("""
						  		"""),format.raw/*29.11*/("""<br>
						  	</div>
				  		</TD>
					</TR>
				
				</table>
				
				<div class="noprint">
					<div class="row justify-content-md-center" >
						<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
							<input type="button"  value="VOLVER" class="btn btn-success btn-sm rounded-pill btn-block" onclick="window.history.back(); return false">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
""")))}),format.raw/*46.2*/("""




"""),format.raw/*51.1*/("""<script type="text/javascript">
	$(document).ready(function () """),format.raw/*52.32*/("""{"""),format.raw/*52.33*/("""
		
			"""),format.raw/*54.4*/("""document.getElementById('"""),_display_(/*54.30*/idGrupo),format.raw/*54.37*/("""').checked="checked";

			Highcharts.chart('grafico30',"""),format.raw/*56.33*/("""{"""),format.raw/*56.34*/("""
	            """),format.raw/*57.14*/("""chart: """),format.raw/*57.21*/("""{"""),format.raw/*57.22*/("""
	                """),format.raw/*58.18*/("""type: 'column'
	            """),format.raw/*59.14*/("""}"""),format.raw/*59.15*/(""",
	            title: """),format.raw/*60.21*/("""{"""),format.raw/*60.22*/("""
	                """),format.raw/*61.18*/("""text: 'MANTENCION ULTIMOS """),_display_(/*61.45*/periodo),format.raw/*61.52*/(""" """),format.raw/*61.53*/("""DIAS'
	            """),format.raw/*62.14*/("""}"""),format.raw/*62.15*/(""",
	            xAxis: """),format.raw/*63.21*/("""{"""),format.raw/*63.22*/("""
	            	"""),format.raw/*64.15*/("""categories: ["""),_display_(/*64.29*/Html(series.get(0))),format.raw/*64.48*/("""],
	                labels: """),format.raw/*65.26*/("""{"""),format.raw/*65.27*/("""
	                    """),format.raw/*66.22*/("""rotation: -45,
	                    align: 'right',
	                    style: """),format.raw/*68.29*/("""{"""),format.raw/*68.30*/("""
	                        """),format.raw/*69.26*/("""fontSize: '10px',
	                        fontFamily: 'Verdana, sans-serif'
	                    """),format.raw/*71.22*/("""}"""),format.raw/*71.23*/("""
	                """),format.raw/*72.18*/("""}"""),format.raw/*72.19*/("""
	            """),format.raw/*73.14*/("""}"""),format.raw/*73.15*/(""",
	            yAxis: """),format.raw/*74.21*/("""{"""),format.raw/*74.22*/("""
	                """),format.raw/*75.18*/("""min: 0,
	                title: """),format.raw/*76.25*/("""{"""),format.raw/*76.26*/("""
	                    """),format.raw/*77.22*/("""text: 'Miles de """),_display_(/*77.39*/mapDiccionario/*77.53*/.get("PESOS")),format.raw/*77.66*/("""'
	                """),format.raw/*78.18*/("""}"""),format.raw/*78.19*/(""",
	                stackLabels: """),format.raw/*79.31*/("""{"""),format.raw/*79.32*/("""
	                    """),format.raw/*80.22*/("""enabled: false,
	                    style: """),format.raw/*81.29*/("""{"""),format.raw/*81.30*/("""
	                        """),format.raw/*82.26*/("""fontWeight: 'bold',
	                        color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
	                    """),format.raw/*84.22*/("""}"""),format.raw/*84.23*/("""
	                """),format.raw/*85.18*/("""}"""),format.raw/*85.19*/("""
	            """),format.raw/*86.14*/("""}"""),format.raw/*86.15*/(""",
	            legend: """),format.raw/*87.22*/("""{"""),format.raw/*87.23*/("""
	            	"""),format.raw/*88.15*/("""layout: 'horizontal',
	                align: 'center',
	                verticalAlign: 'top',
	                y: 30,
	                floating: false,
	                backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
	                borderColor: '#CCC',
	                borderWidth: 1,
	                shadow: false
	            """),format.raw/*97.14*/("""}"""),format.raw/*97.15*/(""",
	            tooltip: """),format.raw/*98.23*/("""{"""),format.raw/*98.24*/("""
	            	"""),format.raw/*99.15*/("""//pointFormat: "Value: """),format.raw/*99.38*/("""{"""),format.raw/*99.39*/("""point.y:,.0f"""),format.raw/*99.51*/("""}"""),format.raw/*99.52*/("""",
	                formatter: function() """),format.raw/*100.40*/("""{"""),format.raw/*100.41*/("""
	                    """),format.raw/*101.22*/("""return '<b>'+ this.x +'</b><br/>'+
	                        this.series.name +': $ '+ formatStandar0(this.y);
	                """),format.raw/*103.18*/("""}"""),format.raw/*103.19*/(""",
	                
	            """),format.raw/*105.14*/("""}"""),format.raw/*105.15*/(""",
	            series: ["""),_display_(/*106.24*/Html(series.get(1))),format.raw/*106.43*/("""]
	        """),format.raw/*107.10*/("""}"""),format.raw/*107.11*/(""");

			document.getElementById('mostrarmostrar').style.display="block";
	   """),format.raw/*110.5*/("""}"""),format.raw/*110.6*/(""");
			  
	
</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,series:List[String],listGrupos:List[List[String]],idGrupo:Long,periodo:Long): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,series,listGrupos,idGrupo,periodo)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[String],List[List[String]],Long,Long) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,series,listGrupos,idGrupo,periodo) => apply(mapDiccionario,mapPermiso,userMnu,series,listGrupos,idGrupo,periodo)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuPlanes/hojaVidaReportGrafico.scala.html
                  HASH: 8d7031f4546f46698d353bafbe43a28df909b9e0
                  MATRIX: 1061->1|1335->182|1367->189|1383->197|1422->199|1452->203|1492->217|1506->223|1571->268|1624->295|1638->301|1705->348|1758->375|1772->381|1836->425|1889->452|1903->458|1969->504|2010->519|2079->567|2110->571|2187->622|2291->705|2324->711|2893->1253|2921->1260|2973->1285|3013->1309|3052->1310|3093->1323|3145->1348|3159->1353|3187->1360|3267->1412|3282->1417|3311->1424|3341->1426|3370->1433|3402->1437|3417->1442|3446->1449|3494->1466|3533->1477|3973->1887|4005->1892|4096->1955|4125->1956|4159->1963|4212->1989|4240->1996|4323->2051|4352->2052|4394->2066|4429->2073|4458->2074|4504->2092|4560->2120|4589->2121|4639->2143|4668->2144|4714->2162|4768->2189|4796->2196|4825->2197|4872->2216|4901->2217|4951->2239|4980->2240|5023->2255|5064->2269|5104->2288|5160->2316|5189->2317|5239->2339|5347->2419|5376->2420|5430->2446|5556->2544|5585->2545|5631->2563|5660->2564|5702->2578|5731->2579|5781->2601|5810->2602|5856->2620|5916->2652|5945->2653|5995->2675|6039->2692|6062->2706|6096->2719|6143->2738|6172->2739|6232->2771|6261->2772|6311->2794|6383->2838|6412->2839|6466->2865|6626->2997|6655->2998|6701->3016|6730->3017|6772->3031|6801->3032|6852->3055|6881->3056|6924->3071|7317->3436|7346->3437|7398->3461|7427->3462|7470->3477|7521->3500|7550->3501|7590->3513|7619->3514|7690->3556|7720->3557|7771->3579|7927->3706|7957->3707|8019->3740|8049->3741|8102->3766|8143->3785|8183->3796|8213->3797|8317->3873|8346->3874
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|37->6|37->6|38->7|38->7|38->7|39->8|39->8|39->8|40->9|40->9|40->9|42->11|42->11|44->13|45->14|45->14|47->16|56->25|56->25|57->26|57->26|57->26|58->27|58->27|58->27|58->27|58->27|58->27|58->27|58->27|58->27|58->27|58->27|58->27|59->28|60->29|77->46|82->51|83->52|83->52|85->54|85->54|85->54|87->56|87->56|88->57|88->57|88->57|89->58|90->59|90->59|91->60|91->60|92->61|92->61|92->61|92->61|93->62|93->62|94->63|94->63|95->64|95->64|95->64|96->65|96->65|97->66|99->68|99->68|100->69|102->71|102->71|103->72|103->72|104->73|104->73|105->74|105->74|106->75|107->76|107->76|108->77|108->77|108->77|108->77|109->78|109->78|110->79|110->79|111->80|112->81|112->81|113->82|115->84|115->84|116->85|116->85|117->86|117->86|118->87|118->87|119->88|128->97|128->97|129->98|129->98|130->99|130->99|130->99|130->99|130->99|131->100|131->100|132->101|134->103|134->103|136->105|136->105|137->106|137->106|138->107|138->107|141->110|141->110
                  -- GENERATED --
              */
          