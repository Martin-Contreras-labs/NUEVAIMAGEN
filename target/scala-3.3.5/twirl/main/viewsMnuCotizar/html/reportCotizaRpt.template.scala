
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

object reportCotizaRpt extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template10[Map[String,String],Map[String,String],utilities.UserMnu,String,String,String,String,String,String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
 grafValConf: String, grafCantConf: String, grafValNoConf: String, grafCantNoConf: String, fechaDe: String, fechaA: String, tituloSucursal: String):play.twirl.api.HtmlFormat.Appendable = {
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
		"""),_display_(/*15.4*/barraTitulo(mapDiccionario, "ANALISIS COTIZACIONES"+tituloSucursal,"período movil: de "+fechaDe+" a "+fechaA)),format.raw/*15.113*/("""
		
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
        			"""),format.raw/*74.12*/("""text: 'CONFIRMADAS (CON Y SIN OT) vs NO CONFIRMADAS<br>(VALORIZADAS)'
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
        			"""),format.raw/*126.12*/("""text: 'CONFIRMADAS (CON Y SIN OT) vs NO CONFIRMADAS<br>(CANTIDAD DE COTIZACIONES)'
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

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,grafValConf:String,grafCantConf:String,grafValNoConf:String,grafCantNoConf:String,fechaDe:String,fechaA:String,tituloSucursal:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,grafValConf,grafCantConf,grafValNoConf,grafCantNoConf,fechaDe,fechaA,tituloSucursal)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,String,String,String,String,String,String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,grafValConf,grafCantConf,grafValNoConf,grafCantNoConf,fechaDe,fechaA,tituloSucursal) => apply(mapDiccionario,mapPermiso,userMnu,grafValConf,grafCantConf,grafValNoConf,grafCantNoConf,fechaDe,fechaA,tituloSucursal)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizar/reportCotizaRpt.scala.html
                  HASH: d8f37e6720a0fb00a4f44478bcb6ea8386c1d64b
                  MATRIX: 1064->1|1402->246|1435->254|1451->262|1490->264|1520->268|1560->282|1574->288|1639->333|1692->360|1706->366|1773->413|1826->440|1840->446|1904->490|1958->517|1973->523|2040->569|2081->584|2150->632|2181->636|2258->687|2389->796|2422->802|3367->1717|3399->1722|3490->1785|3519->1786|3550->1790|3627->1839|3656->1840|3698->1854|3733->1861|3762->1862|3808->1880|3869->1913|3898->1914|3933->1922|4048->2009|4077->2010|4126->2031|4155->2032|4205->2054|4255->2076|4284->2077|4338->2103|4423->2160|4452->2161|4510->2191|4597->2250|4626->2251|4678->2275|4922->2491|4951->2492|5006->2519|5066->2551|5095->2552|5152->2581|5181->2582|5227->2600|5256->2601|5291->2609|5319->2610|5360->2623|5389->2624|5429->2636|5533->2713|5561->2714|5607->2732|5636->2733|5672->2742|5722->2764|5751->2765|5801->2787|6014->2972|6043->2973|6092->2995|6120->2996|6167->3015|6196->3016|6236->3028|6269->3033|6298->3034|6342->3050|6481->3161|6510->3162|6558->3182|6629->3225|6658->3226|6696->3236|6725->3237|6770->3254|6799->3255|6839->3267|6868->3268|6903->3276|6931->3277|6983->3301|7012->3302|7052->3314|7140->3375|7178->3392|7213->3400|7241->3401|7280->3412|7309->3413|7406->3481|7436->3482|7479->3496|7515->3503|7545->3504|7592->3522|7654->3555|7684->3556|7720->3564|7836->3651|7866->3652|7916->3673|7946->3674|7997->3696|8048->3718|8078->3719|8133->3745|8219->3802|8249->3803|8308->3833|8396->3892|8426->3893|8479->3917|8731->4140|8761->4141|8817->4168|8878->4200|8908->4201|8966->4230|8996->4231|9043->4249|9073->4250|9109->4258|9138->4259|9180->4272|9210->4273|9251->4285|9369->4375|9398->4376|9445->4394|9475->4395|9512->4404|9563->4426|9593->4427|9644->4449|9858->4634|9888->4635|9938->4657|9967->4658|10015->4677|10045->4678|10086->4690|10120->4695|10150->4696|10195->4712|10335->4823|10365->4824|10414->4844|10486->4887|10516->4888|10555->4898|10585->4899|10631->4916|10661->4917|10702->4929|10732->4930|10768->4938|10797->4939|10850->4963|10880->4964|10921->4976|11010->5037|11050->5055|11086->5063|11115->5064|11155->5075|11185->5076|11283->5145|11313->5146|11356->5160|11392->5167|11422->5168|11469->5186|11531->5219|11561->5220|11597->5228|11713->5315|11743->5316|11793->5337|11823->5338|11874->5360|11925->5382|11955->5383|12010->5409|12096->5466|12126->5467|12185->5497|12273->5556|12303->5557|12356->5581|12601->5797|12631->5798|12687->5825|12748->5857|12778->5858|12836->5887|12866->5888|12913->5906|12943->5907|12979->5915|13008->5916|13050->5929|13080->5930|13121->5942|13207->6000|13236->6001|13283->6019|13313->6020|13350->6029|13401->6051|13431->6052|13482->6074|13696->6259|13726->6260|13776->6282|13805->6283|13853->6302|13883->6303|13924->6315|13958->6320|13988->6321|14033->6337|14173->6448|14203->6449|14252->6469|14324->6512|14354->6513|14393->6523|14423->6524|14469->6541|14499->6542|14540->6554|14570->6555|14606->6563|14635->6564|14688->6588|14718->6589|14759->6601|14848->6662|14889->6681|14925->6689|14954->6690|14994->6701|15024->6702|15123->6772|15153->6773|15196->6787|15232->6794|15262->6795|15309->6813|15371->6846|15401->6847|15437->6855|15553->6942|15583->6943|15633->6964|15663->6965|15714->6987|15765->7009|15795->7010|15850->7036|15936->7093|15966->7094|16025->7124|16113->7183|16143->7184|16196->7208|16448->7431|16478->7432|16534->7459|16595->7491|16625->7492|16683->7521|16713->7522|16760->7540|16790->7541|16826->7549|16855->7550|16897->7563|16927->7564|16968->7576|17067->7647|17096->7648|17143->7666|17173->7667|17210->7676|17261->7698|17291->7699|17342->7721|17556->7906|17586->7907|17636->7929|17665->7930|17713->7949|17743->7950|17784->7962|17818->7967|17848->7968|17893->7984|18033->8095|18063->8096|18112->8116|18184->8159|18214->8160|18253->8170|18283->8171|18329->8188|18359->8189|18400->8201|18430->8202|18466->8210|18495->8211|18548->8235|18578->8236|18619->8248|18708->8309|18750->8329|18786->8337|18815->8338|18855->8349|18885->8350|18991->8428|19020->8429
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|38->7|38->7|39->8|39->8|39->8|40->9|40->9|40->9|41->10|41->10|41->10|43->12|43->12|45->14|46->15|46->15|48->17|73->42|78->47|79->48|79->48|80->49|81->50|81->50|82->51|82->51|82->51|83->52|84->53|84->53|85->54|88->57|88->57|89->58|89->58|90->59|90->59|90->59|91->60|91->60|91->60|92->61|93->62|93->62|94->63|98->67|98->67|99->68|100->69|100->69|101->70|101->70|102->71|102->71|103->72|103->72|104->73|104->73|105->74|106->75|106->75|107->76|107->76|108->77|108->77|108->77|109->78|111->80|111->80|113->82|113->82|114->83|114->83|115->84|115->84|115->84|116->85|119->88|119->88|120->89|121->90|121->90|121->90|121->90|122->91|122->91|123->92|123->92|124->93|124->93|125->94|125->94|126->95|128->97|128->97|129->98|129->98|130->99|130->99|133->102|133->102|134->103|134->103|134->103|135->104|136->105|136->105|137->106|140->109|140->109|141->110|141->110|142->111|142->111|142->111|143->112|143->112|143->112|144->113|145->114|145->114|146->115|150->119|150->119|151->120|152->121|152->121|153->122|153->122|154->123|154->123|155->124|155->124|156->125|156->125|157->126|158->127|158->127|159->128|159->128|160->129|160->129|160->129|161->130|163->132|163->132|165->134|165->134|166->135|166->135|167->136|167->136|167->136|168->137|171->140|171->140|172->141|173->142|173->142|173->142|173->142|174->143|174->143|175->144|175->144|176->145|176->145|177->146|177->146|178->147|180->149|180->149|181->150|181->150|182->151|182->151|185->154|185->154|186->155|186->155|186->155|187->156|188->157|188->157|189->158|192->161|192->161|193->162|193->162|194->163|194->163|194->163|195->164|195->164|195->164|196->165|197->166|197->166|198->167|202->171|202->171|203->172|204->173|204->173|205->174|205->174|206->175|206->175|207->176|207->176|208->177|208->177|209->178|210->179|210->179|211->180|211->180|212->181|212->181|212->181|213->182|215->184|215->184|217->186|217->186|218->187|218->187|219->188|219->188|219->188|220->189|223->192|223->192|224->193|225->194|225->194|225->194|225->194|226->195|226->195|227->196|227->196|228->197|228->197|229->198|229->198|230->199|232->201|232->201|233->202|233->202|234->203|234->203|237->206|237->206|238->207|238->207|238->207|239->208|240->209|240->209|241->210|244->213|244->213|245->214|245->214|246->215|246->215|246->215|247->216|247->216|247->216|248->217|249->218|249->218|250->219|254->223|254->223|255->224|256->225|256->225|257->226|257->226|258->227|258->227|259->228|259->228|260->229|260->229|261->230|262->231|262->231|263->232|263->232|264->233|264->233|264->233|265->234|267->236|267->236|269->238|269->238|270->239|270->239|271->240|271->240|271->240|272->241|275->244|275->244|276->245|277->246|277->246|277->246|277->246|278->247|278->247|279->248|279->248|280->249|280->249|281->250|281->250|282->251|284->253|284->253|285->254|285->254|286->255|286->255|291->260|291->260
                  -- GENERATED --
              */
          