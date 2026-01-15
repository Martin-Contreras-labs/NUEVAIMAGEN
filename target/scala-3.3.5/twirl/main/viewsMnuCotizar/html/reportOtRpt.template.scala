
package viewsMnuCotizar.html

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

object reportOtRpt extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template10[Map[String,String],Map[String,String],utilities.UserMnu,String,String,String,String,String,String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
 grafValOt: String, grafCantOt: String, grafValSinOt: String, grafCantSinOt: String, fechaDe: String, fechaA: String, tituloSucursal:String):play.twirl.api.HtmlFormat.Appendable = {
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
		"""),_display_(/*14.4*/barraTitulo(mapDiccionario, "ANALISIS "+mapDiccionario.get("Ordenes_de_trabajo").toUpperCase()+tituloSucursal,"período movil: de "+fechaDe+" a "+fechaA)),format.raw/*14.156*/("""
		
		"""),format.raw/*16.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<table id="ESQUEMAGENERAL" class="table table-sm table-bordered table-condensed table-fluid">
					
					
					<TR>
						<TD id="grafValOt" style="height: auto; margin: 0 auto"></TD>
				  		<TD id="grafCantOt" style="height: auto; margin: 0 auto"></TD>
					</TR>
					<TR>
				  		<TD width="40%" id="grafValSinOt" style="height: auto; margin: 0 auto"></TD>
				  		<TD width="40%" id="grafCantSinOt" style="height: auto; margin: 0 auto"></TD>
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
""")))}),format.raw/*41.2*/("""




"""),format.raw/*46.1*/("""<script type="text/javascript">
	$(document).ready(function () """),format.raw/*47.32*/("""{"""),format.raw/*47.33*/("""
			"""),format.raw/*48.4*/("""let total = 0;
			Highcharts.chart('grafValOt',"""),format.raw/*49.33*/("""{"""),format.raw/*49.34*/("""
	            """),format.raw/*50.14*/("""chart: """),format.raw/*50.21*/("""{"""),format.raw/*50.22*/("""
	                """),format.raw/*51.18*/("""zoomType: 'pie',
					options3d: """),format.raw/*52.17*/("""{"""),format.raw/*52.18*/("""
							"""),format.raw/*53.8*/("""enabled: true,
                			alpha: 45,
                			beta: 0
            			"""),format.raw/*56.16*/("""}"""),format.raw/*56.17*/(""",
        			events: """),format.raw/*57.20*/("""{"""),format.raw/*57.21*/("""
        	            """),format.raw/*58.22*/("""load: function(event) """),format.raw/*58.44*/("""{"""),format.raw/*58.45*/("""
        	                """),format.raw/*59.26*/("""for(var i=0, len=this.series[0].yData.length; i<len; i++)"""),format.raw/*59.83*/("""{"""),format.raw/*59.84*/("""
        	                    """),format.raw/*60.30*/("""total += this.series[0].yData[i];
        	                """),format.raw/*61.26*/("""}"""),format.raw/*61.27*/("""
        	              """),format.raw/*62.24*/("""var text = this.renderer.text(
        	                'Total $: ' + Highcharts.numberFormat(total,0),
        	                this.plotLeft,
        	                this.plotTop - 20
        	            	).attr("""),format.raw/*66.30*/("""{"""),format.raw/*66.31*/("""
        	                	"""),format.raw/*67.27*/("""zIndex: 5
        	            	"""),format.raw/*68.23*/("""}"""),format.raw/*68.24*/(""").add()
        	            """),format.raw/*69.22*/("""}"""),format.raw/*69.23*/("""
        	        """),format.raw/*70.18*/("""}"""),format.raw/*70.19*/("""
    			"""),format.raw/*71.8*/("""}"""),format.raw/*71.9*/(""",
				title: """),format.raw/*72.12*/("""{"""),format.raw/*72.13*/("""
        			"""),format.raw/*73.12*/("""text: 'CON """),_display_(/*73.24*/mapDiccionario/*73.38*/.get("OT")),format.raw/*73.48*/(""" """),format.raw/*73.49*/("""vs SIN """),_display_(/*73.57*/mapDiccionario/*73.71*/.get("OT")),format.raw/*73.81*/("""<br>(VALORIZADAS)'
    			"""),format.raw/*74.8*/("""}"""),format.raw/*74.9*/(""",
    			tooltip: """),format.raw/*75.17*/("""{"""),format.raw/*75.18*/("""
    				"""),format.raw/*76.9*/("""formatter: function() """),format.raw/*76.31*/("""{"""),format.raw/*76.32*/("""
    	                """),format.raw/*77.22*/("""return Highcharts.numberFormat(this.percentage, 2) + '%<br />' + '<b>' + 
    	                this.point.name + '</b><br />M$: ' + Highcharts.numberFormat(this.y, 0);
    	            """),format.raw/*79.18*/("""}"""),format.raw/*79.19*/("""
    	        
    			"""),format.raw/*81.8*/("""}"""),format.raw/*81.9*/(""",
				plotOptions: """),format.raw/*82.18*/("""{"""),format.raw/*82.19*/("""
        			"""),format.raw/*83.12*/("""pie: """),format.raw/*83.17*/("""{"""),format.raw/*83.18*/("""
            			"""),format.raw/*84.16*/("""allowPointSelect: true,
            			cursor: 'pointer',
            			depth: 50,
            			dataLabels: """),format.raw/*87.28*/("""{"""),format.raw/*87.29*/("""
                			"""),format.raw/*88.20*/("""enabled: true,
                			format: '"""),format.raw/*89.29*/("""{"""),format.raw/*89.30*/("""point.name"""),format.raw/*89.40*/("""}"""),format.raw/*89.41*/("""'
            			"""),format.raw/*90.16*/("""}"""),format.raw/*90.17*/("""
        			"""),format.raw/*91.12*/("""}"""),format.raw/*91.13*/("""
    			"""),format.raw/*92.8*/("""}"""),format.raw/*92.9*/(""",
            	series: ["""),format.raw/*93.23*/("""{"""),format.raw/*93.24*/("""
        			"""),format.raw/*94.12*/("""type: 'pie',
        			name: 'Porcentaje',
     				data:  """),_display_(/*96.18*/Html(grafValOt)),format.raw/*96.33*/("""
    			"""),format.raw/*97.8*/("""}"""),format.raw/*97.9*/("""]
	        """),format.raw/*98.10*/("""}"""),format.raw/*98.11*/(""");
	            	
	        total = 0;
			Highcharts.chart('grafCantOt',"""),format.raw/*101.34*/("""{"""),format.raw/*101.35*/("""
	            """),format.raw/*102.14*/("""chart: """),format.raw/*102.21*/("""{"""),format.raw/*102.22*/("""
	                """),format.raw/*103.18*/("""zoomType: 'pie',
					options3d: """),format.raw/*104.17*/("""{"""),format.raw/*104.18*/("""
							"""),format.raw/*105.8*/("""enabled: true,
                			alpha: 45,
                			beta: 0
            			"""),format.raw/*108.16*/("""}"""),format.raw/*108.17*/(""",
        			events: """),format.raw/*109.20*/("""{"""),format.raw/*109.21*/("""
        	            """),format.raw/*110.22*/("""load: function(event) """),format.raw/*110.44*/("""{"""),format.raw/*110.45*/("""
        	                """),format.raw/*111.26*/("""for(var i=0, len=this.series[0].yData.length; i<len; i++)"""),format.raw/*111.83*/("""{"""),format.raw/*111.84*/("""
        	                    """),format.raw/*112.30*/("""total += this.series[0].yData[i];
        	                """),format.raw/*113.26*/("""}"""),format.raw/*113.27*/("""
        	              """),format.raw/*114.24*/("""var text = this.renderer.text(
        	                'Total Unidades: ' + Highcharts.numberFormat(total,0),
        	                this.plotLeft,
        	                this.plotTop - 20
        	            	).attr("""),format.raw/*118.30*/("""{"""),format.raw/*118.31*/("""
        	                	"""),format.raw/*119.27*/("""zIndex: 5
        	            	"""),format.raw/*120.23*/("""}"""),format.raw/*120.24*/(""").add()
        	            """),format.raw/*121.22*/("""}"""),format.raw/*121.23*/("""
        	        """),format.raw/*122.18*/("""}"""),format.raw/*122.19*/("""
    			"""),format.raw/*123.8*/("""}"""),format.raw/*123.9*/(""",
				title: """),format.raw/*124.12*/("""{"""),format.raw/*124.13*/("""
        			"""),format.raw/*125.12*/("""text: 'CON """),_display_(/*125.24*/mapDiccionario/*125.38*/.get("OT")),format.raw/*125.48*/(""" """),format.raw/*125.49*/("""vs SIN """),_display_(/*125.57*/mapDiccionario/*125.71*/.get("OT")),format.raw/*125.81*/("""<br>(POR UNIDADES)'
    			"""),format.raw/*126.8*/("""}"""),format.raw/*126.9*/(""",
    			tooltip: """),format.raw/*127.17*/("""{"""),format.raw/*127.18*/("""
    				"""),format.raw/*128.9*/("""formatter: function() """),format.raw/*128.31*/("""{"""),format.raw/*128.32*/("""
    	                """),format.raw/*129.22*/("""return Highcharts.numberFormat(this.percentage, 2) + '%<br />' + '<b>' + 
    	                this.point.name + '</b><br />UN: ' + Highcharts.numberFormat(this.y, 0);
    	            """),format.raw/*131.18*/("""}"""),format.raw/*131.19*/("""
    	        
    			"""),format.raw/*133.8*/("""}"""),format.raw/*133.9*/(""",
				plotOptions: """),format.raw/*134.18*/("""{"""),format.raw/*134.19*/("""
        			"""),format.raw/*135.12*/("""pie: """),format.raw/*135.17*/("""{"""),format.raw/*135.18*/("""
            			"""),format.raw/*136.16*/("""allowPointSelect: true,
            			cursor: 'pointer',
            			depth: 50,
            			dataLabels: """),format.raw/*139.28*/("""{"""),format.raw/*139.29*/("""
                			"""),format.raw/*140.20*/("""enabled: true,
                			format: '"""),format.raw/*141.29*/("""{"""),format.raw/*141.30*/("""point.name"""),format.raw/*141.40*/("""}"""),format.raw/*141.41*/("""'
            			"""),format.raw/*142.16*/("""}"""),format.raw/*142.17*/("""
        			"""),format.raw/*143.12*/("""}"""),format.raw/*143.13*/("""
    			"""),format.raw/*144.8*/("""}"""),format.raw/*144.9*/(""",
            	series: ["""),format.raw/*145.23*/("""{"""),format.raw/*145.24*/("""
        			"""),format.raw/*146.12*/("""type: 'pie',
        			name: 'Porcentaje',
     				data:  """),_display_(/*148.18*/Html(grafCantOt)),format.raw/*148.34*/("""
    			"""),format.raw/*149.8*/("""}"""),format.raw/*149.9*/("""]
	        """),format.raw/*150.10*/("""}"""),format.raw/*150.11*/(""");
	        
	        total = 0;
			Highcharts.chart('grafValSinOt',"""),format.raw/*153.36*/("""{"""),format.raw/*153.37*/("""
	            """),format.raw/*154.14*/("""chart: """),format.raw/*154.21*/("""{"""),format.raw/*154.22*/("""
	                """),format.raw/*155.18*/("""zoomType: 'pie',
					options3d: """),format.raw/*156.17*/("""{"""),format.raw/*156.18*/("""
							"""),format.raw/*157.8*/("""enabled: true,
                			alpha: 45,
                			beta: 0
            			"""),format.raw/*160.16*/("""}"""),format.raw/*160.17*/(""",
        			events: """),format.raw/*161.20*/("""{"""),format.raw/*161.21*/("""
        	            """),format.raw/*162.22*/("""load: function(event) """),format.raw/*162.44*/("""{"""),format.raw/*162.45*/("""
        	                """),format.raw/*163.26*/("""for(var i=0, len=this.series[0].yData.length; i<len; i++)"""),format.raw/*163.83*/("""{"""),format.raw/*163.84*/("""
        	                    """),format.raw/*164.30*/("""total += this.series[0].yData[i];
        	                """),format.raw/*165.26*/("""}"""),format.raw/*165.27*/("""
        	              """),format.raw/*166.24*/("""var text = this.renderer.text(
        	                'Total $: ' + Highcharts.numberFormat(total,0),
        	                this.plotLeft,
        	                this.plotTop - 20
        	            	).attr("""),format.raw/*170.30*/("""{"""),format.raw/*170.31*/("""
        	                	"""),format.raw/*171.27*/("""zIndex: 5
        	            	"""),format.raw/*172.23*/("""}"""),format.raw/*172.24*/(""").add()
        	            """),format.raw/*173.22*/("""}"""),format.raw/*173.23*/("""
        	        """),format.raw/*174.18*/("""}"""),format.raw/*174.19*/("""
    			"""),format.raw/*175.8*/("""}"""),format.raw/*175.9*/(""",
				title: """),format.raw/*176.12*/("""{"""),format.raw/*176.13*/("""
        			"""),format.raw/*177.12*/("""text: '"""),_display_(/*177.20*/mapDiccionario/*177.34*/.get("Ordenes_de_trabajo").toUpperCase()),format.raw/*177.74*/(""" """),format.raw/*177.75*/("""<br>POR ESTADO (VALORIZADAS)'
    			"""),format.raw/*178.8*/("""}"""),format.raw/*178.9*/(""",
    			tooltip: """),format.raw/*179.17*/("""{"""),format.raw/*179.18*/("""
    				"""),format.raw/*180.9*/("""formatter: function() """),format.raw/*180.31*/("""{"""),format.raw/*180.32*/("""
    	                """),format.raw/*181.22*/("""return Highcharts.numberFormat(this.percentage, 2) + '%<br />' + '<b>' + 
    	                this.point.name + '</b><br />M$: ' + Highcharts.numberFormat(this.y, 0);
    	            """),format.raw/*183.18*/("""}"""),format.raw/*183.19*/("""
    	        
    			"""),format.raw/*185.8*/("""}"""),format.raw/*185.9*/(""",
				plotOptions: """),format.raw/*186.18*/("""{"""),format.raw/*186.19*/("""
        			"""),format.raw/*187.12*/("""pie: """),format.raw/*187.17*/("""{"""),format.raw/*187.18*/("""
            			"""),format.raw/*188.16*/("""allowPointSelect: true,
            			cursor: 'pointer',
            			depth: 50,
            			dataLabels: """),format.raw/*191.28*/("""{"""),format.raw/*191.29*/("""
                			"""),format.raw/*192.20*/("""enabled: true,
                			format: '"""),format.raw/*193.29*/("""{"""),format.raw/*193.30*/("""point.name"""),format.raw/*193.40*/("""}"""),format.raw/*193.41*/("""'
            			"""),format.raw/*194.16*/("""}"""),format.raw/*194.17*/("""
        			"""),format.raw/*195.12*/("""}"""),format.raw/*195.13*/("""
    			"""),format.raw/*196.8*/("""}"""),format.raw/*196.9*/(""",
            	series: ["""),format.raw/*197.23*/("""{"""),format.raw/*197.24*/("""
        			"""),format.raw/*198.12*/("""type: 'pie',
        			name: 'Porcentaje',
     				data:  """),_display_(/*200.18*/Html(grafValSinOt)),format.raw/*200.36*/("""
    			"""),format.raw/*201.8*/("""}"""),format.raw/*201.9*/("""]
	        """),format.raw/*202.10*/("""}"""),format.raw/*202.11*/(""");

	        total = 0;
			Highcharts.chart('grafCantSinOt',"""),format.raw/*205.37*/("""{"""),format.raw/*205.38*/("""
	            """),format.raw/*206.14*/("""chart: """),format.raw/*206.21*/("""{"""),format.raw/*206.22*/("""
	                """),format.raw/*207.18*/("""zoomType: 'pie',
					options3d: """),format.raw/*208.17*/("""{"""),format.raw/*208.18*/("""
							"""),format.raw/*209.8*/("""enabled: true,
                			alpha: 45,
                			beta: 0
            			"""),format.raw/*212.16*/("""}"""),format.raw/*212.17*/(""",
        			events: """),format.raw/*213.20*/("""{"""),format.raw/*213.21*/("""
        	            """),format.raw/*214.22*/("""load: function(event) """),format.raw/*214.44*/("""{"""),format.raw/*214.45*/("""
        	                """),format.raw/*215.26*/("""for(var i=0, len=this.series[0].yData.length; i<len; i++)"""),format.raw/*215.83*/("""{"""),format.raw/*215.84*/("""
        	                    """),format.raw/*216.30*/("""total += this.series[0].yData[i];
        	                """),format.raw/*217.26*/("""}"""),format.raw/*217.27*/("""
        	              """),format.raw/*218.24*/("""var text = this.renderer.text(
        	                'Total Unidades: ' + Highcharts.numberFormat(total,0),
        	                this.plotLeft,
        	                this.plotTop - 20
        	            	).attr("""),format.raw/*222.30*/("""{"""),format.raw/*222.31*/("""
        	                	"""),format.raw/*223.27*/("""zIndex: 5
        	            	"""),format.raw/*224.23*/("""}"""),format.raw/*224.24*/(""").add()
        	            """),format.raw/*225.22*/("""}"""),format.raw/*225.23*/("""
        	        """),format.raw/*226.18*/("""}"""),format.raw/*226.19*/("""
    			"""),format.raw/*227.8*/("""}"""),format.raw/*227.9*/(""",
				title: """),format.raw/*228.12*/("""{"""),format.raw/*228.13*/("""
        			"""),format.raw/*229.12*/("""text: '"""),_display_(/*229.20*/mapDiccionario/*229.34*/.get("Ordenes_de_trabajo").toUpperCase()),format.raw/*229.74*/("""<br>POR ESTADO (UNIDADES)'
    			"""),format.raw/*230.8*/("""}"""),format.raw/*230.9*/(""",
    			tooltip: """),format.raw/*231.17*/("""{"""),format.raw/*231.18*/("""
    				"""),format.raw/*232.9*/("""formatter: function() """),format.raw/*232.31*/("""{"""),format.raw/*232.32*/("""
    	                """),format.raw/*233.22*/("""return Highcharts.numberFormat(this.percentage, 2) + '%<br />' + '<b>' + 
    	                this.point.name + '</b><br />UN: ' + Highcharts.numberFormat(this.y, 0);
    	            """),format.raw/*235.18*/("""}"""),format.raw/*235.19*/("""
    	        
    			"""),format.raw/*237.8*/("""}"""),format.raw/*237.9*/(""",
				plotOptions: """),format.raw/*238.18*/("""{"""),format.raw/*238.19*/("""
        			"""),format.raw/*239.12*/("""pie: """),format.raw/*239.17*/("""{"""),format.raw/*239.18*/("""
            			"""),format.raw/*240.16*/("""allowPointSelect: true,
            			cursor: 'pointer',
            			depth: 50,
            			dataLabels: """),format.raw/*243.28*/("""{"""),format.raw/*243.29*/("""
                			"""),format.raw/*244.20*/("""enabled: true,
                			format: '"""),format.raw/*245.29*/("""{"""),format.raw/*245.30*/("""point.name"""),format.raw/*245.40*/("""}"""),format.raw/*245.41*/("""'
            			"""),format.raw/*246.16*/("""}"""),format.raw/*246.17*/("""
        			"""),format.raw/*247.12*/("""}"""),format.raw/*247.13*/("""
    			"""),format.raw/*248.8*/("""}"""),format.raw/*248.9*/(""",
            	series: ["""),format.raw/*249.23*/("""{"""),format.raw/*249.24*/("""
        			"""),format.raw/*250.12*/("""type: 'pie',
        			name: 'Porcentaje',
     				data:  """),_display_(/*252.18*/Html(grafCantSinOt)),format.raw/*252.37*/("""
    			"""),format.raw/*253.8*/("""}"""),format.raw/*253.9*/("""]
	        """),format.raw/*254.10*/("""}"""),format.raw/*254.11*/(""");



			document.getElementById('mostrarmostrar').style.display="block";
	   """),format.raw/*259.5*/("""}"""),format.raw/*259.6*/(""");
			  
	
</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,grafValOt:String,grafCantOt:String,grafValSinOt:String,grafCantSinOt:String,fechaDe:String,fechaA:String,tituloSucursal:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,grafValOt,grafCantOt,grafValSinOt,grafCantSinOt,fechaDe,fechaA,tituloSucursal)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,String,String,String,String,String,String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,grafValOt,grafCantOt,grafValSinOt,grafCantSinOt,fechaDe,fechaA,tituloSucursal) => apply(mapDiccionario,mapPermiso,userMnu,grafValOt,grafCantOt,grafValSinOt,grafCantSinOt,fechaDe,fechaA,tituloSucursal)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizar/reportOtRpt.scala.html
                  HASH: c750d7945aef426fb47e68257fbe9c9bd36b4bce
                  MATRIX: 1060->1|1391->239|1418->241|1434->249|1473->251|1503->255|1543->269|1557->275|1622->320|1675->347|1689->353|1756->400|1809->427|1823->433|1887->477|1940->504|1954->510|2020->556|2061->571|2130->619|2161->623|2238->674|2412->826|2445->832|3384->1741|3416->1746|3507->1809|3536->1810|3567->1814|3642->1861|3671->1862|3713->1876|3748->1883|3777->1884|3823->1902|3884->1935|3913->1936|3948->1944|4063->2031|4092->2032|4141->2053|4170->2054|4220->2076|4270->2098|4299->2099|4353->2125|4438->2182|4467->2183|4525->2213|4612->2272|4641->2273|4693->2297|4937->2513|4966->2514|5021->2541|5081->2573|5110->2574|5167->2603|5196->2604|5242->2622|5271->2623|5306->2631|5334->2632|5375->2645|5404->2646|5444->2658|5483->2670|5506->2684|5537->2694|5566->2695|5601->2703|5624->2717|5655->2727|5708->2753|5736->2754|5782->2772|5811->2773|5847->2782|5897->2804|5926->2805|5976->2827|6189->3012|6218->3013|6267->3035|6295->3036|6342->3055|6371->3056|6411->3068|6444->3073|6473->3074|6517->3090|6656->3201|6685->3202|6733->3222|6804->3265|6833->3266|6871->3276|6900->3277|6945->3294|6974->3295|7014->3307|7043->3308|7078->3316|7106->3317|7158->3341|7187->3342|7227->3354|7315->3415|7351->3430|7386->3438|7414->3439|7453->3450|7482->3451|7582->3522|7612->3523|7655->3537|7691->3544|7721->3545|7768->3563|7830->3596|7860->3597|7896->3605|8012->3692|8042->3693|8092->3714|8122->3715|8173->3737|8224->3759|8254->3760|8309->3786|8395->3843|8425->3844|8484->3874|8572->3933|8602->3934|8655->3958|8907->4181|8937->4182|8993->4209|9054->4241|9084->4242|9142->4271|9172->4272|9219->4290|9249->4291|9285->4299|9314->4300|9356->4313|9386->4314|9427->4326|9467->4338|9491->4352|9523->4362|9553->4363|9589->4371|9613->4385|9645->4395|9700->4422|9729->4423|9776->4441|9806->4442|9843->4451|9894->4473|9924->4474|9975->4496|10189->4681|10219->4682|10269->4704|10298->4705|10346->4724|10376->4725|10417->4737|10451->4742|10481->4743|10526->4759|10666->4870|10696->4871|10745->4891|10817->4934|10847->4935|10886->4945|10916->4946|10962->4963|10992->4964|11033->4976|11063->4977|11099->4985|11128->4986|11181->5010|11211->5011|11252->5023|11341->5084|11379->5100|11415->5108|11444->5109|11484->5120|11514->5121|11611->5189|11641->5190|11684->5204|11720->5211|11750->5212|11797->5230|11859->5263|11889->5264|11925->5272|12041->5359|12071->5360|12121->5381|12151->5382|12202->5404|12253->5426|12283->5427|12338->5453|12424->5510|12454->5511|12513->5541|12601->5600|12631->5601|12684->5625|12929->5841|12959->5842|13015->5869|13076->5901|13106->5902|13164->5931|13194->5932|13241->5950|13271->5951|13307->5959|13336->5960|13378->5973|13408->5974|13449->5986|13485->5994|13509->6008|13571->6048|13601->6049|13666->6086|13695->6087|13742->6105|13772->6106|13809->6115|13860->6137|13890->6138|13941->6160|14155->6345|14185->6346|14235->6368|14264->6369|14312->6388|14342->6389|14383->6401|14417->6406|14447->6407|14492->6423|14632->6534|14662->6535|14711->6555|14783->6598|14813->6599|14852->6609|14882->6610|14928->6627|14958->6628|14999->6640|15029->6641|15065->6649|15094->6650|15147->6674|15177->6675|15218->6687|15307->6748|15347->6766|15383->6774|15412->6775|15452->6786|15482->6787|15571->6847|15601->6848|15644->6862|15680->6869|15710->6870|15757->6888|15819->6921|15849->6922|15885->6930|16001->7017|16031->7018|16081->7039|16111->7040|16162->7062|16213->7084|16243->7085|16298->7111|16384->7168|16414->7169|16473->7199|16561->7258|16591->7259|16644->7283|16896->7506|16926->7507|16982->7534|17043->7566|17073->7567|17131->7596|17161->7597|17208->7615|17238->7616|17274->7624|17303->7625|17345->7638|17375->7639|17416->7651|17452->7659|17476->7673|17538->7713|17600->7747|17629->7748|17676->7766|17706->7767|17743->7776|17794->7798|17824->7799|17875->7821|18089->8006|18119->8007|18169->8029|18198->8030|18246->8049|18276->8050|18317->8062|18351->8067|18381->8068|18426->8084|18566->8195|18596->8196|18645->8216|18717->8259|18747->8260|18786->8270|18816->8271|18862->8288|18892->8289|18933->8301|18963->8302|18999->8310|19028->8311|19081->8335|19111->8336|19152->8348|19241->8409|19282->8428|19318->8436|19347->8437|19387->8448|19417->8449|19523->8527|19552->8528
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|37->6|37->6|38->7|38->7|38->7|39->8|39->8|39->8|40->9|40->9|40->9|42->11|42->11|44->13|45->14|45->14|47->16|72->41|77->46|78->47|78->47|79->48|80->49|80->49|81->50|81->50|81->50|82->51|83->52|83->52|84->53|87->56|87->56|88->57|88->57|89->58|89->58|89->58|90->59|90->59|90->59|91->60|92->61|92->61|93->62|97->66|97->66|98->67|99->68|99->68|100->69|100->69|101->70|101->70|102->71|102->71|103->72|103->72|104->73|104->73|104->73|104->73|104->73|104->73|104->73|104->73|105->74|105->74|106->75|106->75|107->76|107->76|107->76|108->77|110->79|110->79|112->81|112->81|113->82|113->82|114->83|114->83|114->83|115->84|118->87|118->87|119->88|120->89|120->89|120->89|120->89|121->90|121->90|122->91|122->91|123->92|123->92|124->93|124->93|125->94|127->96|127->96|128->97|128->97|129->98|129->98|132->101|132->101|133->102|133->102|133->102|134->103|135->104|135->104|136->105|139->108|139->108|140->109|140->109|141->110|141->110|141->110|142->111|142->111|142->111|143->112|144->113|144->113|145->114|149->118|149->118|150->119|151->120|151->120|152->121|152->121|153->122|153->122|154->123|154->123|155->124|155->124|156->125|156->125|156->125|156->125|156->125|156->125|156->125|156->125|157->126|157->126|158->127|158->127|159->128|159->128|159->128|160->129|162->131|162->131|164->133|164->133|165->134|165->134|166->135|166->135|166->135|167->136|170->139|170->139|171->140|172->141|172->141|172->141|172->141|173->142|173->142|174->143|174->143|175->144|175->144|176->145|176->145|177->146|179->148|179->148|180->149|180->149|181->150|181->150|184->153|184->153|185->154|185->154|185->154|186->155|187->156|187->156|188->157|191->160|191->160|192->161|192->161|193->162|193->162|193->162|194->163|194->163|194->163|195->164|196->165|196->165|197->166|201->170|201->170|202->171|203->172|203->172|204->173|204->173|205->174|205->174|206->175|206->175|207->176|207->176|208->177|208->177|208->177|208->177|208->177|209->178|209->178|210->179|210->179|211->180|211->180|211->180|212->181|214->183|214->183|216->185|216->185|217->186|217->186|218->187|218->187|218->187|219->188|222->191|222->191|223->192|224->193|224->193|224->193|224->193|225->194|225->194|226->195|226->195|227->196|227->196|228->197|228->197|229->198|231->200|231->200|232->201|232->201|233->202|233->202|236->205|236->205|237->206|237->206|237->206|238->207|239->208|239->208|240->209|243->212|243->212|244->213|244->213|245->214|245->214|245->214|246->215|246->215|246->215|247->216|248->217|248->217|249->218|253->222|253->222|254->223|255->224|255->224|256->225|256->225|257->226|257->226|258->227|258->227|259->228|259->228|260->229|260->229|260->229|260->229|261->230|261->230|262->231|262->231|263->232|263->232|263->232|264->233|266->235|266->235|268->237|268->237|269->238|269->238|270->239|270->239|270->239|271->240|274->243|274->243|275->244|276->245|276->245|276->245|276->245|277->246|277->246|278->247|278->247|279->248|279->248|280->249|280->249|281->250|283->252|283->252|284->253|284->253|285->254|285->254|290->259|290->259
                  -- GENERATED --
              */
          