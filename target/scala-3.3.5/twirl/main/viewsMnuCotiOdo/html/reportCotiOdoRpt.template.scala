
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

object reportCotiOdoRpt extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template9[Map[String,String],Map[String,String],utilities.UserMnu,String,String,String,String,String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
 grafValConf: String, grafCantConf: String, grafValNoConf: String, grafCantNoConf: String, fechaDe: String, fechaA: String):play.twirl.api.HtmlFormat.Appendable = {
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
		"""),_display_(/*15.4*/barraTitulo(mapDiccionario, "ANALISIS COTIZACIONES ODO","período movil: de "+fechaDe+" a "+fechaA)),format.raw/*15.102*/("""
		
		"""),format.raw/*17.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<table id="ESQUEMAGENERAL" class="table table-sm table-bordered table-condensed table-fluid">
					
					
					<TR>
						<TD id="grafValConf" style="height: auto; margin: 0 auto"></TD>
				  		<TD id="grafCantConf" style="height: auto; margin: 0 auto"></TD>
					</TR>
					<TR>
				  		<TD width="40%" id="grafValNoConf" style="height: auto; margin: 0 auto"></TD>
				  		<TD width="40%" id="grafCantNoConf" style="height: auto; margin: 0 auto"></TD>
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
""")))}),format.raw/*42.2*/("""




"""),format.raw/*47.1*/("""<script type="text/javascript">
	$(document).ready(function () """),format.raw/*48.32*/("""{"""),format.raw/*48.33*/("""
			"""),format.raw/*49.4*/("""let total = 0;
			Highcharts.chart('grafValConf',"""),format.raw/*50.35*/("""{"""),format.raw/*50.36*/("""
	            """),format.raw/*51.14*/("""chart: """),format.raw/*51.21*/("""{"""),format.raw/*51.22*/("""
	                """),format.raw/*52.18*/("""zoomType: 'pie',
					options3d: """),format.raw/*53.17*/("""{"""),format.raw/*53.18*/("""
							"""),format.raw/*54.8*/("""enabled: true,
                			alpha: 45,
                			beta: 0
            			"""),format.raw/*57.16*/("""}"""),format.raw/*57.17*/(""",
        			events: """),format.raw/*58.20*/("""{"""),format.raw/*58.21*/("""
        	            """),format.raw/*59.22*/("""load: function(event) """),format.raw/*59.44*/("""{"""),format.raw/*59.45*/("""
        	                """),format.raw/*60.26*/("""for(var i=0, len=this.series[0].yData.length; i<len; i++)"""),format.raw/*60.83*/("""{"""),format.raw/*60.84*/("""
        	                    """),format.raw/*61.30*/("""total += this.series[0].yData[i];
        	                """),format.raw/*62.26*/("""}"""),format.raw/*62.27*/("""
        	              """),format.raw/*63.24*/("""var text = this.renderer.text(
        	                'Total $: ' + Highcharts.numberFormat(total,0),
        	                this.plotLeft,
        	                this.plotTop - 20
        	            	).attr("""),format.raw/*67.30*/("""{"""),format.raw/*67.31*/("""
        	                	"""),format.raw/*68.27*/("""zIndex: 5
        	            	"""),format.raw/*69.23*/("""}"""),format.raw/*69.24*/(""").add()
        	            """),format.raw/*70.22*/("""}"""),format.raw/*70.23*/("""
        	        """),format.raw/*71.18*/("""}"""),format.raw/*71.19*/("""
    			"""),format.raw/*72.8*/("""}"""),format.raw/*72.9*/(""",
				title: """),format.raw/*73.12*/("""{"""),format.raw/*73.13*/("""
        			"""),format.raw/*74.12*/("""text: 'CONFIRMADAS vs NO CONFIRMADAS<br>(VALORIZADAS)'
    			"""),format.raw/*75.8*/("""}"""),format.raw/*75.9*/(""",
    			tooltip: """),format.raw/*76.17*/("""{"""),format.raw/*76.18*/("""
    				"""),format.raw/*77.9*/("""formatter: function() """),format.raw/*77.31*/("""{"""),format.raw/*77.32*/("""
    	                """),format.raw/*78.22*/("""return Highcharts.numberFormat(this.percentage, 2) + '%<br />' + '<b>' + 
    	                this.point.name + '</b><br />M$: ' + Highcharts.numberFormat(this.y, 0);
    	            """),format.raw/*80.18*/("""}"""),format.raw/*80.19*/("""
    	        
    			"""),format.raw/*82.8*/("""}"""),format.raw/*82.9*/(""",
				plotOptions: """),format.raw/*83.18*/("""{"""),format.raw/*83.19*/("""
        			"""),format.raw/*84.12*/("""pie: """),format.raw/*84.17*/("""{"""),format.raw/*84.18*/("""
            			"""),format.raw/*85.16*/("""allowPointSelect: true,
            			cursor: 'pointer',
            			depth: 50,
            			dataLabels: """),format.raw/*88.28*/("""{"""),format.raw/*88.29*/("""
                			"""),format.raw/*89.20*/("""enabled: true,
                			format: '"""),format.raw/*90.29*/("""{"""),format.raw/*90.30*/("""point.name"""),format.raw/*90.40*/("""}"""),format.raw/*90.41*/("""'
            			"""),format.raw/*91.16*/("""}"""),format.raw/*91.17*/("""
        			"""),format.raw/*92.12*/("""}"""),format.raw/*92.13*/("""
    			"""),format.raw/*93.8*/("""}"""),format.raw/*93.9*/(""",
            	series: ["""),format.raw/*94.23*/("""{"""),format.raw/*94.24*/("""
        			"""),format.raw/*95.12*/("""type: 'pie',
        			name: 'Porcentaje',
     				data:  """),_display_(/*97.18*/Html(grafValConf)),format.raw/*97.35*/("""
    			"""),format.raw/*98.8*/("""}"""),format.raw/*98.9*/("""]
	        """),format.raw/*99.10*/("""}"""),format.raw/*99.11*/(""");
	        
	        total = 0;
			Highcharts.chart('grafCantConf',"""),format.raw/*102.36*/("""{"""),format.raw/*102.37*/("""
	            """),format.raw/*103.14*/("""chart: """),format.raw/*103.21*/("""{"""),format.raw/*103.22*/("""
	                """),format.raw/*104.18*/("""zoomType: 'pie',
					options3d: """),format.raw/*105.17*/("""{"""),format.raw/*105.18*/("""
							"""),format.raw/*106.8*/("""enabled: true,
                			alpha: 45,
                			beta: 0
            			"""),format.raw/*109.16*/("""}"""),format.raw/*109.17*/(""",
        			events: """),format.raw/*110.20*/("""{"""),format.raw/*110.21*/("""
        	            """),format.raw/*111.22*/("""load: function(event) """),format.raw/*111.44*/("""{"""),format.raw/*111.45*/("""
        	                """),format.raw/*112.26*/("""for(var i=0, len=this.series[0].yData.length; i<len; i++)"""),format.raw/*112.83*/("""{"""),format.raw/*112.84*/("""
        	                    """),format.raw/*113.30*/("""total += this.series[0].yData[i];
        	                """),format.raw/*114.26*/("""}"""),format.raw/*114.27*/("""
        	              """),format.raw/*115.24*/("""var text = this.renderer.text(
        	                'Total Unidades: ' + Highcharts.numberFormat(total,0),
        	                this.plotLeft,
        	                this.plotTop - 20
        	            	).attr("""),format.raw/*119.30*/("""{"""),format.raw/*119.31*/("""
        	                	"""),format.raw/*120.27*/("""zIndex: 5
        	            	"""),format.raw/*121.23*/("""}"""),format.raw/*121.24*/(""").add()
        	            """),format.raw/*122.22*/("""}"""),format.raw/*122.23*/("""
        	        """),format.raw/*123.18*/("""}"""),format.raw/*123.19*/("""
    			"""),format.raw/*124.8*/("""}"""),format.raw/*124.9*/(""",
				title: """),format.raw/*125.12*/("""{"""),format.raw/*125.13*/("""
        			"""),format.raw/*126.12*/("""text: 'CONFIRMADAS vs NO CONFIRMADAS<br>(CANTIDAD DE COTIZACIONES)'
    			"""),format.raw/*127.8*/("""}"""),format.raw/*127.9*/(""",
    			tooltip: """),format.raw/*128.17*/("""{"""),format.raw/*128.18*/("""
    				"""),format.raw/*129.9*/("""formatter: function() """),format.raw/*129.31*/("""{"""),format.raw/*129.32*/("""
    	                """),format.raw/*130.22*/("""return Highcharts.numberFormat(this.percentage, 2) + '%<br />' + '<b>' + 
    	                this.point.name + '</b><br />UN: ' + Highcharts.numberFormat(this.y, 0);
    	            """),format.raw/*132.18*/("""}"""),format.raw/*132.19*/("""
    	        
    			"""),format.raw/*134.8*/("""}"""),format.raw/*134.9*/(""",
				plotOptions: """),format.raw/*135.18*/("""{"""),format.raw/*135.19*/("""
        			"""),format.raw/*136.12*/("""pie: """),format.raw/*136.17*/("""{"""),format.raw/*136.18*/("""
            			"""),format.raw/*137.16*/("""allowPointSelect: true,
            			cursor: 'pointer',
            			depth: 50,
            			dataLabels: """),format.raw/*140.28*/("""{"""),format.raw/*140.29*/("""
                			"""),format.raw/*141.20*/("""enabled: true,
                			format: '"""),format.raw/*142.29*/("""{"""),format.raw/*142.30*/("""point.name"""),format.raw/*142.40*/("""}"""),format.raw/*142.41*/("""'
            			"""),format.raw/*143.16*/("""}"""),format.raw/*143.17*/("""
        			"""),format.raw/*144.12*/("""}"""),format.raw/*144.13*/("""
    			"""),format.raw/*145.8*/("""}"""),format.raw/*145.9*/(""",
            	series: ["""),format.raw/*146.23*/("""{"""),format.raw/*146.24*/("""
        			"""),format.raw/*147.12*/("""type: 'pie',
        			name: 'Porcentaje',
     				data:  """),_display_(/*149.18*/Html(grafCantConf)),format.raw/*149.36*/("""
    			"""),format.raw/*150.8*/("""}"""),format.raw/*150.9*/("""]
	        """),format.raw/*151.10*/("""}"""),format.raw/*151.11*/(""");
	        
	        total = 0;
			Highcharts.chart('grafValNoConf',"""),format.raw/*154.37*/("""{"""),format.raw/*154.38*/("""
	            """),format.raw/*155.14*/("""chart: """),format.raw/*155.21*/("""{"""),format.raw/*155.22*/("""
	                """),format.raw/*156.18*/("""zoomType: 'pie',
					options3d: """),format.raw/*157.17*/("""{"""),format.raw/*157.18*/("""
							"""),format.raw/*158.8*/("""enabled: true,
                			alpha: 45,
                			beta: 0
            			"""),format.raw/*161.16*/("""}"""),format.raw/*161.17*/(""",
        			events: """),format.raw/*162.20*/("""{"""),format.raw/*162.21*/("""
        	            """),format.raw/*163.22*/("""load: function(event) """),format.raw/*163.44*/("""{"""),format.raw/*163.45*/("""
        	                """),format.raw/*164.26*/("""for(var i=0, len=this.series[0].yData.length; i<len; i++)"""),format.raw/*164.83*/("""{"""),format.raw/*164.84*/("""
        	                    """),format.raw/*165.30*/("""total += this.series[0].yData[i];
        	                """),format.raw/*166.26*/("""}"""),format.raw/*166.27*/("""
        	              """),format.raw/*167.24*/("""var text = this.renderer.text(
        	                'Total $: ' + Highcharts.numberFormat(total,0),
        	                this.plotLeft,
        	                this.plotTop - 20
        	            	).attr("""),format.raw/*171.30*/("""{"""),format.raw/*171.31*/("""
        	                	"""),format.raw/*172.27*/("""zIndex: 5
        	            	"""),format.raw/*173.23*/("""}"""),format.raw/*173.24*/(""").add()
        	            """),format.raw/*174.22*/("""}"""),format.raw/*174.23*/("""
        	        """),format.raw/*175.18*/("""}"""),format.raw/*175.19*/("""
    			"""),format.raw/*176.8*/("""}"""),format.raw/*176.9*/(""",
				title: """),format.raw/*177.12*/("""{"""),format.raw/*177.13*/("""
        			"""),format.raw/*178.12*/("""text: 'NO CONFIRMADAS POR ESTADO<br>(VALORIZADAS)'
    			"""),format.raw/*179.8*/("""}"""),format.raw/*179.9*/(""",
    			tooltip: """),format.raw/*180.17*/("""{"""),format.raw/*180.18*/("""
    				"""),format.raw/*181.9*/("""formatter: function() """),format.raw/*181.31*/("""{"""),format.raw/*181.32*/("""
    	                """),format.raw/*182.22*/("""return Highcharts.numberFormat(this.percentage, 2) + '%<br />' + '<b>' + 
    	                this.point.name + '</b><br />M$: ' + Highcharts.numberFormat(this.y, 0);
    	            """),format.raw/*184.18*/("""}"""),format.raw/*184.19*/("""
    	        
    			"""),format.raw/*186.8*/("""}"""),format.raw/*186.9*/(""",
				plotOptions: """),format.raw/*187.18*/("""{"""),format.raw/*187.19*/("""
        			"""),format.raw/*188.12*/("""pie: """),format.raw/*188.17*/("""{"""),format.raw/*188.18*/("""
            			"""),format.raw/*189.16*/("""allowPointSelect: true,
            			cursor: 'pointer',
            			depth: 50,
            			dataLabels: """),format.raw/*192.28*/("""{"""),format.raw/*192.29*/("""
                			"""),format.raw/*193.20*/("""enabled: true,
                			format: '"""),format.raw/*194.29*/("""{"""),format.raw/*194.30*/("""point.name"""),format.raw/*194.40*/("""}"""),format.raw/*194.41*/("""'
            			"""),format.raw/*195.16*/("""}"""),format.raw/*195.17*/("""
        			"""),format.raw/*196.12*/("""}"""),format.raw/*196.13*/("""
    			"""),format.raw/*197.8*/("""}"""),format.raw/*197.9*/(""",
            	series: ["""),format.raw/*198.23*/("""{"""),format.raw/*198.24*/("""
        			"""),format.raw/*199.12*/("""type: 'pie',
        			name: 'Porcentaje',
     				data:  """),_display_(/*201.18*/Html(grafValNoConf)),format.raw/*201.37*/("""
    			"""),format.raw/*202.8*/("""}"""),format.raw/*202.9*/("""]
	        """),format.raw/*203.10*/("""}"""),format.raw/*203.11*/(""");
	        
	        total = 0;
			Highcharts.chart('grafCantNoConf',"""),format.raw/*206.38*/("""{"""),format.raw/*206.39*/("""
	            """),format.raw/*207.14*/("""chart: """),format.raw/*207.21*/("""{"""),format.raw/*207.22*/("""
	                """),format.raw/*208.18*/("""zoomType: 'pie',
					options3d: """),format.raw/*209.17*/("""{"""),format.raw/*209.18*/("""
							"""),format.raw/*210.8*/("""enabled: true,
                			alpha: 45,
                			beta: 0
            			"""),format.raw/*213.16*/("""}"""),format.raw/*213.17*/(""",
        			events: """),format.raw/*214.20*/("""{"""),format.raw/*214.21*/("""
        	            """),format.raw/*215.22*/("""load: function(event) """),format.raw/*215.44*/("""{"""),format.raw/*215.45*/("""
        	                """),format.raw/*216.26*/("""for(var i=0, len=this.series[0].yData.length; i<len; i++)"""),format.raw/*216.83*/("""{"""),format.raw/*216.84*/("""
        	                    """),format.raw/*217.30*/("""total += this.series[0].yData[i];
        	                """),format.raw/*218.26*/("""}"""),format.raw/*218.27*/("""
        	              """),format.raw/*219.24*/("""var text = this.renderer.text(
        	                'Total Unidades: ' + Highcharts.numberFormat(total,0),
        	                this.plotLeft,
        	                this.plotTop - 20
        	            	).attr("""),format.raw/*223.30*/("""{"""),format.raw/*223.31*/("""
        	                	"""),format.raw/*224.27*/("""zIndex: 5
        	            	"""),format.raw/*225.23*/("""}"""),format.raw/*225.24*/(""").add()
        	            """),format.raw/*226.22*/("""}"""),format.raw/*226.23*/("""
        	        """),format.raw/*227.18*/("""}"""),format.raw/*227.19*/("""
    			"""),format.raw/*228.8*/("""}"""),format.raw/*228.9*/(""",
				title: """),format.raw/*229.12*/("""{"""),format.raw/*229.13*/("""
        			"""),format.raw/*230.12*/("""text: 'NO CONFIRMADAS POR ESTADO<br>(CANTIDAD DE COTIZACIONES)'
    			"""),format.raw/*231.8*/("""}"""),format.raw/*231.9*/(""",
    			tooltip: """),format.raw/*232.17*/("""{"""),format.raw/*232.18*/("""
    				"""),format.raw/*233.9*/("""formatter: function() """),format.raw/*233.31*/("""{"""),format.raw/*233.32*/("""
    	                """),format.raw/*234.22*/("""return Highcharts.numberFormat(this.percentage, 2) + '%<br />' + '<b>' + 
    	                this.point.name + '</b><br />UN: ' + Highcharts.numberFormat(this.y, 0);
    	            """),format.raw/*236.18*/("""}"""),format.raw/*236.19*/("""
    	        
    			"""),format.raw/*238.8*/("""}"""),format.raw/*238.9*/(""",
				plotOptions: """),format.raw/*239.18*/("""{"""),format.raw/*239.19*/("""
        			"""),format.raw/*240.12*/("""pie: """),format.raw/*240.17*/("""{"""),format.raw/*240.18*/("""
            			"""),format.raw/*241.16*/("""allowPointSelect: true,
            			cursor: 'pointer',
            			depth: 50,
            			dataLabels: """),format.raw/*244.28*/("""{"""),format.raw/*244.29*/("""
                			"""),format.raw/*245.20*/("""enabled: true,
                			format: '"""),format.raw/*246.29*/("""{"""),format.raw/*246.30*/("""point.name"""),format.raw/*246.40*/("""}"""),format.raw/*246.41*/("""'
            			"""),format.raw/*247.16*/("""}"""),format.raw/*247.17*/("""
        			"""),format.raw/*248.12*/("""}"""),format.raw/*248.13*/("""
    			"""),format.raw/*249.8*/("""}"""),format.raw/*249.9*/(""",
            	series: ["""),format.raw/*250.23*/("""{"""),format.raw/*250.24*/("""
        			"""),format.raw/*251.12*/("""type: 'pie',
        			name: 'Porcentaje',
     				data:  """),_display_(/*253.18*/Html(grafCantNoConf)),format.raw/*253.38*/("""
    			"""),format.raw/*254.8*/("""}"""),format.raw/*254.9*/("""]
	        """),format.raw/*255.10*/("""}"""),format.raw/*255.11*/(""");



			document.getElementById('mostrarmostrar').style.display="block";
	   """),format.raw/*260.5*/("""}"""),format.raw/*260.6*/(""");
			  
	
</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,grafValConf:String,grafCantConf:String,grafValNoConf:String,grafCantNoConf:String,fechaDe:String,fechaA:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,grafValConf,grafCantConf,grafValNoConf,grafCantNoConf,fechaDe,fechaA)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,String,String,String,String,String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,grafValConf,grafCantConf,grafValNoConf,grafCantNoConf,fechaDe,fechaA) => apply(mapDiccionario,mapPermiso,userMnu,grafValConf,grafCantConf,grafValNoConf,grafCantNoConf,fechaDe,fechaA)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotiOdo/reportCotiOdoRpt.scala.html
                  HASH: d419979858a7ff764f5c4ef1694627b80c7cf2f5
                  MATRIX: 1057->1|1371->222|1404->230|1420->238|1459->240|1489->244|1529->258|1543->264|1608->309|1661->336|1675->342|1742->389|1795->416|1809->422|1873->466|1927->493|1942->499|2009->545|2050->560|2119->608|2150->612|2227->663|2347->761|2380->767|3325->1682|3357->1687|3448->1750|3477->1751|3508->1755|3585->1804|3614->1805|3656->1819|3691->1826|3720->1827|3766->1845|3827->1878|3856->1879|3891->1887|4006->1974|4035->1975|4084->1996|4113->1997|4163->2019|4213->2041|4242->2042|4296->2068|4381->2125|4410->2126|4468->2156|4555->2215|4584->2216|4636->2240|4880->2456|4909->2457|4964->2484|5024->2516|5053->2517|5110->2546|5139->2547|5185->2565|5214->2566|5249->2574|5277->2575|5318->2588|5347->2589|5387->2601|5476->2663|5504->2664|5550->2682|5579->2683|5615->2692|5665->2714|5694->2715|5744->2737|5957->2922|5986->2923|6035->2945|6063->2946|6110->2965|6139->2966|6179->2978|6212->2983|6241->2984|6285->3000|6424->3111|6453->3112|6501->3132|6572->3175|6601->3176|6639->3186|6668->3187|6713->3204|6742->3205|6782->3217|6811->3218|6846->3226|6874->3227|6926->3251|6955->3252|6995->3264|7083->3325|7121->3342|7156->3350|7184->3351|7223->3362|7252->3363|7349->3431|7379->3432|7422->3446|7458->3453|7488->3454|7535->3472|7597->3505|7627->3506|7663->3514|7779->3601|7809->3602|7859->3623|7889->3624|7940->3646|7991->3668|8021->3669|8076->3695|8162->3752|8192->3753|8251->3783|8339->3842|8369->3843|8422->3867|8674->4090|8704->4091|8760->4118|8821->4150|8851->4151|8909->4180|8939->4181|8986->4199|9016->4200|9052->4208|9081->4209|9123->4222|9153->4223|9194->4235|9297->4310|9326->4311|9373->4329|9403->4330|9440->4339|9491->4361|9521->4362|9572->4384|9786->4569|9816->4570|9866->4592|9895->4593|9943->4612|9973->4613|10014->4625|10048->4630|10078->4631|10123->4647|10263->4758|10293->4759|10342->4779|10414->4822|10444->4823|10483->4833|10513->4834|10559->4851|10589->4852|10630->4864|10660->4865|10696->4873|10725->4874|10778->4898|10808->4899|10849->4911|10938->4972|10978->4990|11014->4998|11043->4999|11083->5010|11113->5011|11211->5080|11241->5081|11284->5095|11320->5102|11350->5103|11397->5121|11459->5154|11489->5155|11525->5163|11641->5250|11671->5251|11721->5272|11751->5273|11802->5295|11853->5317|11883->5318|11938->5344|12024->5401|12054->5402|12113->5432|12201->5491|12231->5492|12284->5516|12529->5732|12559->5733|12615->5760|12676->5792|12706->5793|12764->5822|12794->5823|12841->5841|12871->5842|12907->5850|12936->5851|12978->5864|13008->5865|13049->5877|13135->5935|13164->5936|13211->5954|13241->5955|13278->5964|13329->5986|13359->5987|13410->6009|13624->6194|13654->6195|13704->6217|13733->6218|13781->6237|13811->6238|13852->6250|13886->6255|13916->6256|13961->6272|14101->6383|14131->6384|14180->6404|14252->6447|14282->6448|14321->6458|14351->6459|14397->6476|14427->6477|14468->6489|14498->6490|14534->6498|14563->6499|14616->6523|14646->6524|14687->6536|14776->6597|14817->6616|14853->6624|14882->6625|14922->6636|14952->6637|15051->6707|15081->6708|15124->6722|15160->6729|15190->6730|15237->6748|15299->6781|15329->6782|15365->6790|15481->6877|15511->6878|15561->6899|15591->6900|15642->6922|15693->6944|15723->6945|15778->6971|15864->7028|15894->7029|15953->7059|16041->7118|16071->7119|16124->7143|16376->7366|16406->7367|16462->7394|16523->7426|16553->7427|16611->7456|16641->7457|16688->7475|16718->7476|16754->7484|16783->7485|16825->7498|16855->7499|16896->7511|16995->7582|17024->7583|17071->7601|17101->7602|17138->7611|17189->7633|17219->7634|17270->7656|17484->7841|17514->7842|17564->7864|17593->7865|17641->7884|17671->7885|17712->7897|17746->7902|17776->7903|17821->7919|17961->8030|17991->8031|18040->8051|18112->8094|18142->8095|18181->8105|18211->8106|18257->8123|18287->8124|18328->8136|18358->8137|18394->8145|18423->8146|18476->8170|18506->8171|18547->8183|18636->8244|18678->8264|18714->8272|18743->8273|18783->8284|18813->8285|18919->8363|18948->8364
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|38->7|38->7|39->8|39->8|39->8|40->9|40->9|40->9|41->10|41->10|41->10|43->12|43->12|45->14|46->15|46->15|48->17|73->42|78->47|79->48|79->48|80->49|81->50|81->50|82->51|82->51|82->51|83->52|84->53|84->53|85->54|88->57|88->57|89->58|89->58|90->59|90->59|90->59|91->60|91->60|91->60|92->61|93->62|93->62|94->63|98->67|98->67|99->68|100->69|100->69|101->70|101->70|102->71|102->71|103->72|103->72|104->73|104->73|105->74|106->75|106->75|107->76|107->76|108->77|108->77|108->77|109->78|111->80|111->80|113->82|113->82|114->83|114->83|115->84|115->84|115->84|116->85|119->88|119->88|120->89|121->90|121->90|121->90|121->90|122->91|122->91|123->92|123->92|124->93|124->93|125->94|125->94|126->95|128->97|128->97|129->98|129->98|130->99|130->99|133->102|133->102|134->103|134->103|134->103|135->104|136->105|136->105|137->106|140->109|140->109|141->110|141->110|142->111|142->111|142->111|143->112|143->112|143->112|144->113|145->114|145->114|146->115|150->119|150->119|151->120|152->121|152->121|153->122|153->122|154->123|154->123|155->124|155->124|156->125|156->125|157->126|158->127|158->127|159->128|159->128|160->129|160->129|160->129|161->130|163->132|163->132|165->134|165->134|166->135|166->135|167->136|167->136|167->136|168->137|171->140|171->140|172->141|173->142|173->142|173->142|173->142|174->143|174->143|175->144|175->144|176->145|176->145|177->146|177->146|178->147|180->149|180->149|181->150|181->150|182->151|182->151|185->154|185->154|186->155|186->155|186->155|187->156|188->157|188->157|189->158|192->161|192->161|193->162|193->162|194->163|194->163|194->163|195->164|195->164|195->164|196->165|197->166|197->166|198->167|202->171|202->171|203->172|204->173|204->173|205->174|205->174|206->175|206->175|207->176|207->176|208->177|208->177|209->178|210->179|210->179|211->180|211->180|212->181|212->181|212->181|213->182|215->184|215->184|217->186|217->186|218->187|218->187|219->188|219->188|219->188|220->189|223->192|223->192|224->193|225->194|225->194|225->194|225->194|226->195|226->195|227->196|227->196|228->197|228->197|229->198|229->198|230->199|232->201|232->201|233->202|233->202|234->203|234->203|237->206|237->206|238->207|238->207|238->207|239->208|240->209|240->209|241->210|244->213|244->213|245->214|245->214|246->215|246->215|246->215|247->216|247->216|247->216|248->217|249->218|249->218|250->219|254->223|254->223|255->224|256->225|256->225|257->226|257->226|258->227|258->227|259->228|259->228|260->229|260->229|261->230|262->231|262->231|263->232|263->232|264->233|264->233|264->233|265->234|267->236|267->236|269->238|269->238|270->239|270->239|271->240|271->240|271->240|272->241|275->244|275->244|276->245|277->246|277->246|277->246|277->246|278->247|278->247|279->248|279->248|280->249|280->249|281->250|281->250|282->251|284->253|284->253|285->254|285->254|286->255|286->255|291->260|291->260
                  -- GENERATED --
              */
          