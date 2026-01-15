
package viewsMnuCotiOdo.html

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

object reportOtOdoRpt extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template9[Map[String,String],Map[String,String],utilities.UserMnu,String,String,String,String,String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
 grafValOt: String, grafCantOt: String, grafValSinOt: String, grafCantSinOt: String, fechaDe: String, fechaA: String):play.twirl.api.HtmlFormat.Appendable = {
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
		"""),_display_(/*14.4*/barraTitulo(mapDiccionario, "ANALISIS "+mapDiccionario.get("Ordenes_de_trabajo").toUpperCase(),"período movil: de "+fechaDe+" a "+fechaA)),format.raw/*14.141*/("""
		
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
        			"""),format.raw/*177.12*/("""text: '"""),_display_(/*177.20*/mapDiccionario/*177.34*/.get("Ordenes_de_trabajo").toUpperCase()),format.raw/*177.74*/("""<br>POR ESTADO (VALORIZADAS)'
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

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,grafValOt:String,grafCantOt:String,grafValSinOt:String,grafCantSinOt:String,fechaDe:String,fechaA:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,grafValOt,grafCantOt,grafValSinOt,grafCantSinOt,fechaDe,fechaA)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,String,String,String,String,String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,grafValOt,grafCantOt,grafValSinOt,grafCantSinOt,fechaDe,fechaA) => apply(mapDiccionario,mapPermiso,userMnu,grafValOt,grafCantOt,grafValSinOt,grafCantSinOt,fechaDe,fechaA)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotiOdo/reportOtOdoRpt.scala.html
                  HASH: 30e98f22b564a99d78403311117c320ad914dd09
                  MATRIX: 1055->1|1363->216|1390->218|1406->226|1445->228|1475->232|1515->246|1529->252|1594->297|1647->324|1661->330|1728->377|1781->404|1795->410|1859->454|1912->481|1926->487|1992->533|2033->548|2102->596|2133->600|2210->651|2369->788|2402->794|3341->1703|3373->1708|3464->1771|3493->1772|3524->1776|3599->1823|3628->1824|3670->1838|3705->1845|3734->1846|3780->1864|3841->1897|3870->1898|3905->1906|4020->1993|4049->1994|4098->2015|4127->2016|4177->2038|4227->2060|4256->2061|4310->2087|4395->2144|4424->2145|4482->2175|4569->2234|4598->2235|4650->2259|4894->2475|4923->2476|4978->2503|5038->2535|5067->2536|5124->2565|5153->2566|5199->2584|5228->2585|5263->2593|5291->2594|5332->2607|5361->2608|5401->2620|5440->2632|5463->2646|5494->2656|5523->2657|5558->2665|5581->2679|5612->2689|5665->2715|5693->2716|5739->2734|5768->2735|5804->2744|5854->2766|5883->2767|5933->2789|6146->2974|6175->2975|6224->2997|6252->2998|6299->3017|6328->3018|6368->3030|6401->3035|6430->3036|6474->3052|6613->3163|6642->3164|6690->3184|6761->3227|6790->3228|6828->3238|6857->3239|6902->3256|6931->3257|6971->3269|7000->3270|7035->3278|7063->3279|7115->3303|7144->3304|7184->3316|7272->3377|7308->3392|7343->3400|7371->3401|7410->3412|7439->3413|7539->3484|7569->3485|7612->3499|7648->3506|7678->3507|7725->3525|7787->3558|7817->3559|7853->3567|7969->3654|7999->3655|8049->3676|8079->3677|8130->3699|8181->3721|8211->3722|8266->3748|8352->3805|8382->3806|8441->3836|8529->3895|8559->3896|8612->3920|8864->4143|8894->4144|8950->4171|9011->4203|9041->4204|9099->4233|9129->4234|9176->4252|9206->4253|9242->4261|9271->4262|9313->4275|9343->4276|9384->4288|9424->4300|9448->4314|9480->4324|9510->4325|9546->4333|9570->4347|9602->4357|9657->4384|9686->4385|9733->4403|9763->4404|9800->4413|9851->4435|9881->4436|9932->4458|10146->4643|10176->4644|10226->4666|10255->4667|10303->4686|10333->4687|10374->4699|10408->4704|10438->4705|10483->4721|10623->4832|10653->4833|10702->4853|10774->4896|10804->4897|10843->4907|10873->4908|10919->4925|10949->4926|10990->4938|11020->4939|11056->4947|11085->4948|11138->4972|11168->4973|11209->4985|11298->5046|11336->5062|11372->5070|11401->5071|11441->5082|11471->5083|11568->5151|11598->5152|11641->5166|11677->5173|11707->5174|11754->5192|11816->5225|11846->5226|11882->5234|11998->5321|12028->5322|12078->5343|12108->5344|12159->5366|12210->5388|12240->5389|12295->5415|12381->5472|12411->5473|12470->5503|12558->5562|12588->5563|12641->5587|12886->5803|12916->5804|12972->5831|13033->5863|13063->5864|13121->5893|13151->5894|13198->5912|13228->5913|13264->5921|13293->5922|13335->5935|13365->5936|13406->5948|13442->5956|13466->5970|13528->6010|13593->6047|13622->6048|13669->6066|13699->6067|13736->6076|13787->6098|13817->6099|13868->6121|14082->6306|14112->6307|14162->6329|14191->6330|14239->6349|14269->6350|14310->6362|14344->6367|14374->6368|14419->6384|14559->6495|14589->6496|14638->6516|14710->6559|14740->6560|14779->6570|14809->6571|14855->6588|14885->6589|14926->6601|14956->6602|14992->6610|15021->6611|15074->6635|15104->6636|15145->6648|15234->6709|15274->6727|15310->6735|15339->6736|15379->6747|15409->6748|15498->6808|15528->6809|15571->6823|15607->6830|15637->6831|15684->6849|15746->6882|15776->6883|15812->6891|15928->6978|15958->6979|16008->7000|16038->7001|16089->7023|16140->7045|16170->7046|16225->7072|16311->7129|16341->7130|16400->7160|16488->7219|16518->7220|16571->7244|16823->7467|16853->7468|16909->7495|16970->7527|17000->7528|17058->7557|17088->7558|17135->7576|17165->7577|17201->7585|17230->7586|17272->7599|17302->7600|17343->7612|17379->7620|17403->7634|17465->7674|17527->7708|17556->7709|17603->7727|17633->7728|17670->7737|17721->7759|17751->7760|17802->7782|18016->7967|18046->7968|18096->7990|18125->7991|18173->8010|18203->8011|18244->8023|18278->8028|18308->8029|18353->8045|18493->8156|18523->8157|18572->8177|18644->8220|18674->8221|18713->8231|18743->8232|18789->8249|18819->8250|18860->8262|18890->8263|18926->8271|18955->8272|19008->8296|19038->8297|19079->8309|19168->8370|19209->8389|19245->8397|19274->8398|19314->8409|19344->8410|19450->8488|19479->8489
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|37->6|37->6|38->7|38->7|38->7|39->8|39->8|39->8|40->9|40->9|40->9|42->11|42->11|44->13|45->14|45->14|47->16|72->41|77->46|78->47|78->47|79->48|80->49|80->49|81->50|81->50|81->50|82->51|83->52|83->52|84->53|87->56|87->56|88->57|88->57|89->58|89->58|89->58|90->59|90->59|90->59|91->60|92->61|92->61|93->62|97->66|97->66|98->67|99->68|99->68|100->69|100->69|101->70|101->70|102->71|102->71|103->72|103->72|104->73|104->73|104->73|104->73|104->73|104->73|104->73|104->73|105->74|105->74|106->75|106->75|107->76|107->76|107->76|108->77|110->79|110->79|112->81|112->81|113->82|113->82|114->83|114->83|114->83|115->84|118->87|118->87|119->88|120->89|120->89|120->89|120->89|121->90|121->90|122->91|122->91|123->92|123->92|124->93|124->93|125->94|127->96|127->96|128->97|128->97|129->98|129->98|132->101|132->101|133->102|133->102|133->102|134->103|135->104|135->104|136->105|139->108|139->108|140->109|140->109|141->110|141->110|141->110|142->111|142->111|142->111|143->112|144->113|144->113|145->114|149->118|149->118|150->119|151->120|151->120|152->121|152->121|153->122|153->122|154->123|154->123|155->124|155->124|156->125|156->125|156->125|156->125|156->125|156->125|156->125|156->125|157->126|157->126|158->127|158->127|159->128|159->128|159->128|160->129|162->131|162->131|164->133|164->133|165->134|165->134|166->135|166->135|166->135|167->136|170->139|170->139|171->140|172->141|172->141|172->141|172->141|173->142|173->142|174->143|174->143|175->144|175->144|176->145|176->145|177->146|179->148|179->148|180->149|180->149|181->150|181->150|184->153|184->153|185->154|185->154|185->154|186->155|187->156|187->156|188->157|191->160|191->160|192->161|192->161|193->162|193->162|193->162|194->163|194->163|194->163|195->164|196->165|196->165|197->166|201->170|201->170|202->171|203->172|203->172|204->173|204->173|205->174|205->174|206->175|206->175|207->176|207->176|208->177|208->177|208->177|208->177|209->178|209->178|210->179|210->179|211->180|211->180|211->180|212->181|214->183|214->183|216->185|216->185|217->186|217->186|218->187|218->187|218->187|219->188|222->191|222->191|223->192|224->193|224->193|224->193|224->193|225->194|225->194|226->195|226->195|227->196|227->196|228->197|228->197|229->198|231->200|231->200|232->201|232->201|233->202|233->202|236->205|236->205|237->206|237->206|237->206|238->207|239->208|239->208|240->209|243->212|243->212|244->213|244->213|245->214|245->214|245->214|246->215|246->215|246->215|247->216|248->217|248->217|249->218|253->222|253->222|254->223|255->224|255->224|256->225|256->225|257->226|257->226|258->227|258->227|259->228|259->228|260->229|260->229|260->229|260->229|261->230|261->230|262->231|262->231|263->232|263->232|263->232|264->233|266->235|266->235|268->237|268->237|269->238|269->238|270->239|270->239|270->239|271->240|274->243|274->243|275->244|276->245|276->245|276->245|276->245|277->246|277->246|278->247|278->247|279->248|279->248|280->249|280->249|281->250|283->252|283->252|284->253|284->253|285->254|285->254|290->259|290->259
                  -- GENERATED --
              */
          