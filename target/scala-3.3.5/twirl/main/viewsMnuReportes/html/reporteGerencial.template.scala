
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

object reporteGerencial extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,List[String],String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
series: List[String], titulo: String):play.twirl.api.HtmlFormat.Appendable = {
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
		"""),_display_(/*15.4*/barraTitulo(mapDiccionario, "REPORTE GERENCIAL - VENTAS NETAS",titulo)),format.raw/*15.74*/("""
		
		"""),format.raw/*17.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<table id="ESQUEMAGENERAL" class="table table-sm table-bordered table-condensed table-fluid">
					<tr>
						<TD  id="ventasAnioAnterior" style="width:50%; height: auto; margin: 0 auto"></TD>
						<TD  id="ventasAnioActual" style="width:50%; height: auto; margin: 0 auto"></TD>	
					</tr>
					<tr>
						<TD id="colocacionArriendo" style="width:50%; height: auto; margin: 0 auto"></TD>
						<TD id="colocacionInventarios" style="width:50%; height: auto; margin: 0 auto"></TD>
					</tr>
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
""")))}),format.raw/*39.2*/("""




"""),format.raw/*44.1*/("""<script type="text/javascript">
	$(document).ready(function () """),format.raw/*45.32*/("""{"""),format.raw/*45.33*/("""
		
		"""),format.raw/*47.3*/("""Highcharts.chart('colocacionInventarios',"""),format.raw/*47.44*/("""{"""),format.raw/*47.45*/("""
            """),format.raw/*48.13*/("""chart: """),format.raw/*48.20*/("""{"""),format.raw/*48.21*/("""
	            """),format.raw/*49.14*/("""type: 'column'
	        """),format.raw/*50.10*/("""}"""),format.raw/*50.11*/(""",
	        title: """),format.raw/*51.17*/("""{"""),format.raw/*51.18*/("""
	            """),format.raw/*52.14*/("""text: 'PRECIO COMPRA VS PRECIO REPOSICION REAL'
	        """),format.raw/*53.10*/("""}"""),format.raw/*53.11*/(""",
	        subtitle: """),format.raw/*54.20*/("""{"""),format.raw/*54.21*/("""
	            """),format.raw/*55.14*/("""text: '(en """),_display_(/*55.26*/mapDiccionario/*55.40*/.get("PESOS")),format.raw/*55.53*/(""")'
	        """),format.raw/*56.10*/("""}"""),format.raw/*56.11*/(""",
	        xAxis: """),format.raw/*57.17*/("""{"""),format.raw/*57.18*/("""
	            """),format.raw/*58.14*/("""categories: """),_display_(/*58.27*/Html(series.get(6))),format.raw/*58.46*/(""",
	            labels: """),format.raw/*59.22*/("""{"""),format.raw/*59.23*/("""
	                """),format.raw/*60.18*/("""rotation: -45
	            """),format.raw/*61.14*/("""}"""),format.raw/*61.15*/("""
	        """),format.raw/*62.10*/("""}"""),format.raw/*62.11*/(""",
	        yAxis: """),format.raw/*63.17*/("""{"""),format.raw/*63.18*/("""
	            """),format.raw/*64.14*/("""min: 0,
	            
	            title: """),format.raw/*66.21*/("""{"""),format.raw/*66.22*/("""
	                """),format.raw/*67.18*/("""text: ''
	            """),format.raw/*68.14*/("""}"""),format.raw/*68.15*/(""",
	            stackLabels: """),format.raw/*69.27*/("""{"""),format.raw/*69.28*/("""
	                """),format.raw/*70.18*/("""enabled: true,
	                style: """),format.raw/*71.25*/("""{"""),format.raw/*71.26*/("""
	                    """),format.raw/*72.22*/("""fontWeight: 'bold',
	                    color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
	                """),format.raw/*74.18*/("""}"""),format.raw/*74.19*/("""
	            """),format.raw/*75.14*/("""}"""),format.raw/*75.15*/("""
	        """),format.raw/*76.10*/("""}"""),format.raw/*76.11*/(""", 
	        series: ["""),format.raw/*77.19*/("""{"""),format.raw/*77.20*/("""
	        	"""),format.raw/*78.11*/("""name: 'a Precio Compra',
	        	data: """),_display_(/*79.18*/Html(series.get(10))),format.raw/*79.38*/(""",
				tooltip: """),format.raw/*80.14*/("""{"""),format.raw/*80.15*/("""
					"""),format.raw/*81.6*/("""valuePrefix: '"""),_display_(/*81.21*/mapDiccionario/*81.35*/.get("CLP")),format.raw/*81.46*/(""" """),format.raw/*81.47*/("""',
				"""),format.raw/*82.5*/("""}"""),format.raw/*82.6*/("""
	        """),format.raw/*83.10*/("""}"""),format.raw/*83.11*/(""","""),format.raw/*83.12*/("""{"""),format.raw/*83.13*/("""
	        	"""),format.raw/*84.11*/("""name: 'a Precio Reposición',
	        	data: """),_display_(/*85.18*/Html(series.get(9))),format.raw/*85.37*/(""",
				tooltip: """),format.raw/*86.14*/("""{"""),format.raw/*86.15*/("""
					"""),format.raw/*87.6*/("""valuePrefix: '"""),_display_(/*87.21*/mapDiccionario/*87.35*/.get("CLP")),format.raw/*87.46*/(""" """),format.raw/*87.47*/("""',
				"""),format.raw/*88.5*/("""}"""),format.raw/*88.6*/("""
	        """),format.raw/*89.10*/("""}"""),format.raw/*89.11*/("""]
        """),format.raw/*90.9*/("""}"""),format.raw/*90.10*/(""");

		Highcharts.chart('colocacionArriendo',"""),format.raw/*92.41*/("""{"""),format.raw/*92.42*/("""
            """),format.raw/*93.13*/("""chart: """),format.raw/*93.20*/("""{"""),format.raw/*93.21*/("""
                """),format.raw/*94.17*/("""type: 'column'
            """),format.raw/*95.13*/("""}"""),format.raw/*95.14*/(""",
            title: """),format.raw/*96.20*/("""{"""),format.raw/*96.21*/("""
                """),format.raw/*97.17*/("""text: 'PRECIO REAL VS PRECIO LISTA'
            """),format.raw/*98.13*/("""}"""),format.raw/*98.14*/(""",
            subtitle: """),format.raw/*99.23*/("""{"""),format.raw/*99.24*/("""
                """),format.raw/*100.17*/("""text: '(en """),_display_(/*100.29*/mapDiccionario/*100.43*/.get("PESOS")),format.raw/*100.56*/(""")'
            """),format.raw/*101.13*/("""}"""),format.raw/*101.14*/(""",
            xAxis: """),format.raw/*102.20*/("""{"""),format.raw/*102.21*/("""
                """),format.raw/*103.17*/("""categories: """),_display_(/*103.30*/Html(series.get(6))),format.raw/*103.49*/(""",
                labels: """),format.raw/*104.25*/("""{"""),format.raw/*104.26*/("""
                    """),format.raw/*105.21*/("""rotation: -45
                """),format.raw/*106.17*/("""}"""),format.raw/*106.18*/("""
            """),format.raw/*107.13*/("""}"""),format.raw/*107.14*/(""",
            yAxis: """),format.raw/*108.20*/("""{"""),format.raw/*108.21*/("""
                """),format.raw/*109.17*/("""min: 0,
                title: """),format.raw/*110.24*/("""{"""),format.raw/*110.25*/("""
                    """),format.raw/*111.21*/("""text: ''
                """),format.raw/*112.17*/("""}"""),format.raw/*112.18*/(""",
                stackLabels: """),format.raw/*113.30*/("""{"""),format.raw/*113.31*/("""
                    """),format.raw/*114.21*/("""enabled: true,
                    style: """),format.raw/*115.28*/("""{"""),format.raw/*115.29*/("""
                        """),format.raw/*116.25*/("""fontWeight: 'bold',
                        color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
                    """),format.raw/*118.21*/("""}"""),format.raw/*118.22*/("""
                """),format.raw/*119.17*/("""}"""),format.raw/*119.18*/("""
            """),format.raw/*120.13*/("""}"""),format.raw/*120.14*/(""", 
            series: ["""),format.raw/*121.22*/("""{"""),format.raw/*121.23*/("""
            	"""),format.raw/*122.14*/("""name: 'a Precio Real',
            	data: """),_display_(/*123.21*/Html(series.get(7))),format.raw/*123.40*/(""",
    			tooltip: """),format.raw/*124.17*/("""{"""),format.raw/*124.18*/("""
    				"""),format.raw/*125.9*/("""valuePrefix: '"""),_display_(/*125.24*/mapDiccionario/*125.38*/.get("CLP")),format.raw/*125.49*/(""" """),format.raw/*125.50*/("""',
    			"""),format.raw/*126.8*/("""}"""),format.raw/*126.9*/("""
            """),format.raw/*127.13*/("""}"""),format.raw/*127.14*/(""","""),format.raw/*127.15*/("""{"""),format.raw/*127.16*/("""
            	"""),format.raw/*128.14*/("""name: 'a Precio Lista',
            	data: """),_display_(/*129.21*/Html(series.get(8))),format.raw/*129.40*/(""",
    			tooltip: """),format.raw/*130.17*/("""{"""),format.raw/*130.18*/("""
    				"""),format.raw/*131.9*/("""valuePrefix: '"""),_display_(/*131.24*/mapDiccionario/*131.38*/.get("CLP")),format.raw/*131.49*/(""" """),format.raw/*131.50*/("""',
    			"""),format.raw/*132.8*/("""}"""),format.raw/*132.9*/("""
            """),format.raw/*133.13*/("""}"""),format.raw/*133.14*/("""]
        """),format.raw/*134.9*/("""}"""),format.raw/*134.10*/(""");

		Highcharts.chart('ventasAnioActual',"""),format.raw/*136.39*/("""{"""),format.raw/*136.40*/("""
            """),format.raw/*137.13*/("""chart: """),format.raw/*137.20*/("""{"""),format.raw/*137.21*/("""
                """),format.raw/*138.17*/("""zoomType: 'xy'
            """),format.raw/*139.13*/("""}"""),format.raw/*139.14*/(""",
            title: """),format.raw/*140.20*/("""{"""),format.raw/*140.21*/("""
                """),format.raw/*141.17*/("""text: 'INGRESOS AÑO ACTUAL'
            """),format.raw/*142.13*/("""}"""),format.raw/*142.14*/(""",
            subtitle: """),format.raw/*143.23*/("""{"""),format.raw/*143.24*/("""
                """),format.raw/*144.17*/("""text: '(en """),_display_(/*144.29*/mapDiccionario/*144.43*/.get("PESOS")),format.raw/*144.56*/(""")'
            """),format.raw/*145.13*/("""}"""),format.raw/*145.14*/(""",
            xAxis: ["""),format.raw/*146.21*/("""{"""),format.raw/*146.22*/("""
                """),format.raw/*147.17*/("""categories:  """),_display_(/*147.31*/Html(series.get(3))),format.raw/*147.50*/(""",
                labels: """),format.raw/*148.25*/("""{"""),format.raw/*148.26*/("""
                    """),format.raw/*149.21*/("""rotation: -45
                """),format.raw/*150.17*/("""}"""),format.raw/*150.18*/("""
            """),format.raw/*151.13*/("""}"""),format.raw/*151.14*/("""],
            yAxis: ["""),format.raw/*152.21*/("""{"""),format.raw/*152.22*/(""" """),format.raw/*152.23*/("""// Primary yAxis
            	min: 0,
                labels: """),format.raw/*154.25*/("""{"""),format.raw/*154.26*/("""
                """),format.raw/*155.17*/("""//	format: """"),format.raw/*155.29*/("""{"""),format.raw/*155.30*/("""value:point.y:,.0f"""),format.raw/*155.48*/("""}"""),format.raw/*155.49*/(""" """),format.raw/*155.50*/("""",
                    style: """),format.raw/*156.28*/("""{"""),format.raw/*156.29*/("""
                        """),format.raw/*157.25*/("""color: Highcharts.getOptions().colors[1]
                    """),format.raw/*158.21*/("""}"""),format.raw/*158.22*/("""
                """),format.raw/*159.17*/("""}"""),format.raw/*159.18*/(""",
                title: """),format.raw/*160.24*/("""{"""),format.raw/*160.25*/("""
                    """),format.raw/*161.21*/("""text: 'Acumulado',
                    style: """),format.raw/*162.28*/("""{"""),format.raw/*162.29*/("""
                        """),format.raw/*163.25*/("""color: Highcharts.getOptions().colors[1]
                    """),format.raw/*164.21*/("""}"""),format.raw/*164.22*/("""
                """),format.raw/*165.17*/("""}"""),format.raw/*165.18*/("""
            """),format.raw/*166.13*/("""}"""),format.raw/*166.14*/(""", """),format.raw/*166.16*/("""{"""),format.raw/*166.17*/(""" """),format.raw/*166.18*/("""// Secondary yAxis
            	min: 0,
                title: """),format.raw/*168.24*/("""{"""),format.raw/*168.25*/("""
                    """),format.raw/*169.21*/("""text: 'Parcial',
                    style: """),format.raw/*170.28*/("""{"""),format.raw/*170.29*/("""
                        """),format.raw/*171.25*/("""color: Highcharts.getOptions().colors[0]
                    """),format.raw/*172.21*/("""}"""),format.raw/*172.22*/("""
                """),format.raw/*173.17*/("""}"""),format.raw/*173.18*/(""",
                labels: """),format.raw/*174.25*/("""{"""),format.raw/*174.26*/("""
                    """),format.raw/*175.21*/("""//format: """"),format.raw/*175.32*/("""{"""),format.raw/*175.33*/("""value:point.y:,.0f"""),format.raw/*175.51*/("""}"""),format.raw/*175.52*/(""" """),format.raw/*175.53*/("""",
                    style: """),format.raw/*176.28*/("""{"""),format.raw/*176.29*/("""
                        """),format.raw/*177.25*/("""color: Highcharts.getOptions().colors[0]
                    """),format.raw/*178.21*/("""}"""),format.raw/*178.22*/("""
                """),format.raw/*179.17*/("""}"""),format.raw/*179.18*/(""",
                opposite: true
            """),format.raw/*181.13*/("""}"""),format.raw/*181.14*/("""],
            tooltip: """),format.raw/*182.22*/("""{"""),format.raw/*182.23*/("""
                """),format.raw/*183.17*/("""shared: true,		                
            """),format.raw/*184.13*/("""}"""),format.raw/*184.14*/(""",
            legend: """),format.raw/*185.21*/("""{"""),format.raw/*185.22*/("""
                """),format.raw/*186.17*/("""layout: 'vertical',
                align: 'left',
                x: 80,
                verticalAlign: 'top',
                y: 20,
                floating: true,
                backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'
            """),format.raw/*193.13*/("""}"""),format.raw/*193.14*/(""",
            series: ["""),format.raw/*194.22*/("""{"""),format.raw/*194.23*/("""
                """),format.raw/*195.17*/("""name: 'parcial',
                type: 'column',
                yAxis: 1,
                data: """),_display_(/*198.24*/Html(series.get(4))),format.raw/*198.43*/(""",
                tooltip: """),format.raw/*199.26*/("""{"""),format.raw/*199.27*/("""
                	"""),format.raw/*200.18*/("""valuePrefix: '"""),_display_(/*200.33*/mapDiccionario/*200.47*/.get("CLP")),format.raw/*200.58*/(""" """),format.raw/*200.59*/("""',
                """),format.raw/*201.17*/("""}"""),format.raw/*201.18*/("""		    
            """),format.raw/*202.13*/("""}"""),format.raw/*202.14*/(""", """),format.raw/*202.16*/("""{"""),format.raw/*202.17*/("""
                """),format.raw/*203.17*/("""name: 'acumulado',
                type: 'spline',
                data: """),_display_(/*205.24*/Html(series.get(5))),format.raw/*205.43*/(""",
                tooltip: """),format.raw/*206.26*/("""{"""),format.raw/*206.27*/("""
                	"""),format.raw/*207.18*/("""valuePrefix: '"""),_display_(/*207.33*/mapDiccionario/*207.47*/.get("CLP")),format.raw/*207.58*/(""" """),format.raw/*207.59*/("""',
                """),format.raw/*208.17*/("""}"""),format.raw/*208.18*/("""
            """),format.raw/*209.13*/("""}"""),format.raw/*209.14*/("""]
        """),format.raw/*210.9*/("""}"""),format.raw/*210.10*/(""");

		Highcharts.chart('ventasAnioAnterior',"""),format.raw/*212.41*/("""{"""),format.raw/*212.42*/("""
            """),format.raw/*213.13*/("""chart: """),format.raw/*213.20*/("""{"""),format.raw/*213.21*/("""
                """),format.raw/*214.17*/("""zoomType: 'xy'
            """),format.raw/*215.13*/("""}"""),format.raw/*215.14*/(""",
            title: """),format.raw/*216.20*/("""{"""),format.raw/*216.21*/("""
                """),format.raw/*217.17*/("""text: 'INGRESOS AÑO ANTERIOR'
            """),format.raw/*218.13*/("""}"""),format.raw/*218.14*/(""",
            subtitle: """),format.raw/*219.23*/("""{"""),format.raw/*219.24*/("""
                """),format.raw/*220.17*/("""text: '(en """),_display_(/*220.29*/mapDiccionario/*220.43*/.get("PESOS")),format.raw/*220.56*/(""")'
            """),format.raw/*221.13*/("""}"""),format.raw/*221.14*/(""",
            xAxis: ["""),format.raw/*222.21*/("""{"""),format.raw/*222.22*/("""
                """),format.raw/*223.17*/("""categories:  """),_display_(/*223.31*/Html(series.get(0))),format.raw/*223.50*/(""",
                labels: """),format.raw/*224.25*/("""{"""),format.raw/*224.26*/("""
                    """),format.raw/*225.21*/("""rotation: -45
                """),format.raw/*226.17*/("""}"""),format.raw/*226.18*/("""
            """),format.raw/*227.13*/("""}"""),format.raw/*227.14*/("""],
            yAxis: ["""),format.raw/*228.21*/("""{"""),format.raw/*228.22*/(""" """),format.raw/*228.23*/("""// Primary yAxis
            	min: 0,
                labels: """),format.raw/*230.25*/("""{"""),format.raw/*230.26*/("""
                """),format.raw/*231.17*/("""//	format: """"),format.raw/*231.29*/("""{"""),format.raw/*231.30*/("""value:point.y:,.0f"""),format.raw/*231.48*/("""}"""),format.raw/*231.49*/(""" """),format.raw/*231.50*/("""",
                    style: """),format.raw/*232.28*/("""{"""),format.raw/*232.29*/("""
                        """),format.raw/*233.25*/("""color: Highcharts.getOptions().colors[1]
                    """),format.raw/*234.21*/("""}"""),format.raw/*234.22*/("""
                """),format.raw/*235.17*/("""}"""),format.raw/*235.18*/(""",
                title: """),format.raw/*236.24*/("""{"""),format.raw/*236.25*/("""
                    """),format.raw/*237.21*/("""text: 'Acumulado',
                    style: """),format.raw/*238.28*/("""{"""),format.raw/*238.29*/("""
                        """),format.raw/*239.25*/("""color: Highcharts.getOptions().colors[1]
                    """),format.raw/*240.21*/("""}"""),format.raw/*240.22*/("""
                """),format.raw/*241.17*/("""}"""),format.raw/*241.18*/("""
            """),format.raw/*242.13*/("""}"""),format.raw/*242.14*/(""", """),format.raw/*242.16*/("""{"""),format.raw/*242.17*/(""" """),format.raw/*242.18*/("""// Secondary yAxis
            	min: 0,
                title: """),format.raw/*244.24*/("""{"""),format.raw/*244.25*/("""
                    """),format.raw/*245.21*/("""text: 'Parcial',
                    style: """),format.raw/*246.28*/("""{"""),format.raw/*246.29*/("""
                        """),format.raw/*247.25*/("""color: Highcharts.getOptions().colors[0]
                    """),format.raw/*248.21*/("""}"""),format.raw/*248.22*/("""
                """),format.raw/*249.17*/("""}"""),format.raw/*249.18*/(""",
                labels: """),format.raw/*250.25*/("""{"""),format.raw/*250.26*/("""
                    """),format.raw/*251.21*/("""//format: """"),format.raw/*251.32*/("""{"""),format.raw/*251.33*/("""value:point.y:,.0f"""),format.raw/*251.51*/("""}"""),format.raw/*251.52*/(""" """),format.raw/*251.53*/("""",
                    style: """),format.raw/*252.28*/("""{"""),format.raw/*252.29*/("""
                        """),format.raw/*253.25*/("""color: Highcharts.getOptions().colors[0]
                    """),format.raw/*254.21*/("""}"""),format.raw/*254.22*/("""
                """),format.raw/*255.17*/("""}"""),format.raw/*255.18*/(""",
                opposite: true
            """),format.raw/*257.13*/("""}"""),format.raw/*257.14*/("""],
            tooltip: """),format.raw/*258.22*/("""{"""),format.raw/*258.23*/("""
                """),format.raw/*259.17*/("""shared: true,
                
            """),format.raw/*261.13*/("""}"""),format.raw/*261.14*/(""",
            legend: """),format.raw/*262.21*/("""{"""),format.raw/*262.22*/("""
                """),format.raw/*263.17*/("""layout: 'vertical',
                align: 'left',
                x: 80,
                verticalAlign: 'top',
                y: 20,
                floating: true,
                backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'
            """),format.raw/*270.13*/("""}"""),format.raw/*270.14*/(""",
            series: ["""),format.raw/*271.22*/("""{"""),format.raw/*271.23*/("""
                """),format.raw/*272.17*/("""name: 'parcial',
                type: 'column',
                yAxis: 1,
                //data: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                data: """),_display_(/*276.24*/Html(series.get(1))),format.raw/*276.43*/(""",
                tooltip: """),format.raw/*277.26*/("""{"""),format.raw/*277.27*/("""
                	"""),format.raw/*278.18*/("""valuePrefix: '"""),_display_(/*278.33*/mapDiccionario/*278.47*/.get("CLP")),format.raw/*278.58*/(""" """),format.raw/*278.59*/("""',
                """),format.raw/*279.17*/("""}"""),format.raw/*279.18*/("""
    
            """),format.raw/*281.13*/("""}"""),format.raw/*281.14*/(""", """),format.raw/*281.16*/("""{"""),format.raw/*281.17*/("""
                """),format.raw/*282.17*/("""name: 'acumulado',
                type: 'spline',
                data: """),_display_(/*284.24*/Html(series.get(2))),format.raw/*284.43*/(""",
                tooltip: """),format.raw/*285.26*/("""{"""),format.raw/*285.27*/("""
                	"""),format.raw/*286.18*/("""valuePrefix: '"""),_display_(/*286.33*/mapDiccionario/*286.47*/.get("CLP")),format.raw/*286.58*/(""" """),format.raw/*286.59*/("""',
                """),format.raw/*287.17*/("""}"""),format.raw/*287.18*/("""
            """),format.raw/*288.13*/("""}"""),format.raw/*288.14*/("""]
        """),format.raw/*289.9*/("""}"""),format.raw/*289.10*/(""");

		document.getElementById('mostrarmostrar').style.display="block";
	  """),format.raw/*292.4*/("""}"""),format.raw/*292.5*/(""");

</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,series:List[String],titulo:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,series,titulo)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[String],String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,series,titulo) => apply(mapDiccionario,mapPermiso,userMnu,series,titulo)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reporteGerencial.scala.html
                  HASH: f658ecffc7f459dbfdfa81a8cc79dfe6867dc9ba
                  MATRIX: 1036->1|1264->136|1297->144|1313->152|1352->154|1382->158|1422->172|1436->178|1501->223|1554->250|1568->256|1635->303|1688->330|1702->336|1766->380|1820->407|1835->413|1902->459|1943->474|2012->522|2043->526|2120->577|2211->647|2244->653|3214->1593|3246->1598|3337->1661|3366->1662|3399->1668|3468->1709|3497->1710|3538->1723|3573->1730|3602->1731|3644->1745|3696->1769|3725->1770|3771->1788|3800->1789|3842->1803|3927->1860|3956->1861|4005->1882|4034->1883|4076->1897|4115->1909|4138->1923|4172->1936|4212->1948|4241->1949|4287->1967|4316->1968|4358->1982|4398->1995|4438->2014|4489->2037|4518->2038|4564->2056|4619->2083|4648->2084|4686->2094|4715->2095|4761->2113|4790->2114|4832->2128|4902->2170|4931->2171|4977->2189|5027->2211|5056->2212|5112->2240|5141->2241|5187->2259|5254->2298|5283->2299|5333->2321|5485->2445|5514->2446|5556->2460|5585->2461|5623->2471|5652->2472|5701->2493|5730->2494|5769->2505|5838->2547|5879->2567|5922->2582|5951->2583|5984->2589|6026->2604|6049->2618|6081->2629|6110->2630|6144->2637|6172->2638|6210->2648|6239->2649|6268->2650|6297->2651|6336->2662|6409->2708|6449->2727|6492->2742|6521->2743|6554->2749|6596->2764|6619->2778|6651->2789|6680->2790|6714->2797|6742->2798|6780->2808|6809->2809|6846->2819|6875->2820|6947->2864|6976->2865|7017->2878|7052->2885|7081->2886|7126->2903|7181->2930|7210->2931|7259->2952|7288->2953|7333->2970|7409->3018|7438->3019|7490->3043|7519->3044|7565->3061|7605->3073|7629->3087|7664->3100|7708->3115|7738->3116|7788->3137|7818->3138|7864->3155|7905->3168|7946->3187|8001->3213|8031->3214|8081->3235|8140->3265|8170->3266|8212->3279|8242->3280|8292->3301|8322->3302|8368->3319|8428->3350|8458->3351|8508->3372|8562->3397|8592->3398|8652->3429|8682->3430|8732->3451|8803->3493|8833->3494|8887->3519|9046->3649|9076->3650|9122->3667|9152->3668|9194->3681|9224->3682|9277->3706|9307->3707|9350->3721|9421->3764|9462->3783|9509->3801|9539->3802|9576->3811|9619->3826|9643->3840|9676->3851|9706->3852|9744->3862|9773->3863|9815->3876|9845->3877|9875->3878|9905->3879|9948->3893|10020->3937|10061->3956|10108->3974|10138->3975|10175->3984|10218->3999|10242->4013|10275->4024|10305->4025|10343->4035|10372->4036|10414->4049|10444->4050|10482->4060|10512->4061|10583->4103|10613->4104|10655->4117|10691->4124|10721->4125|10767->4142|10823->4169|10853->4170|10903->4191|10933->4192|10979->4209|11048->4249|11078->4250|11131->4274|11161->4275|11207->4292|11247->4304|11271->4318|11306->4331|11350->4346|11380->4347|11431->4369|11461->4370|11507->4387|11549->4401|11590->4420|11645->4446|11675->4447|11725->4468|11784->4498|11814->4499|11856->4512|11886->4513|11938->4536|11968->4537|11998->4538|12089->4600|12119->4601|12165->4618|12206->4630|12236->4631|12283->4649|12313->4650|12343->4651|12402->4681|12432->4682|12486->4707|12576->4768|12606->4769|12652->4786|12682->4787|12736->4812|12766->4813|12816->4834|12891->4880|12921->4881|12975->4906|13065->4967|13095->4968|13141->4985|13171->4986|13213->4999|13243->5000|13274->5002|13304->5003|13334->5004|13426->5067|13456->5068|13506->5089|13579->5133|13609->5134|13663->5159|13753->5220|13783->5221|13829->5238|13859->5239|13914->5265|13944->5266|13994->5287|14034->5298|14064->5299|14111->5317|14141->5318|14171->5319|14230->5349|14260->5350|14314->5375|14404->5436|14434->5437|14480->5454|14510->5455|14584->5500|14614->5501|14667->5525|14697->5526|14743->5543|14816->5587|14846->5588|14897->5610|14927->5611|14973->5628|15288->5914|15318->5915|15370->5938|15400->5939|15446->5956|15572->6054|15613->6073|15669->6100|15699->6101|15746->6119|15789->6134|15813->6148|15846->6159|15876->6160|15924->6179|15954->6180|16002->6199|16032->6200|16063->6202|16093->6203|16139->6220|16241->6294|16282->6313|16338->6340|16368->6341|16415->6359|16458->6374|16482->6388|16515->6399|16545->6400|16593->6419|16623->6420|16665->6433|16695->6434|16733->6444|16763->6445|16836->6489|16866->6490|16908->6503|16944->6510|16974->6511|17020->6528|17076->6555|17106->6556|17156->6577|17186->6578|17232->6595|17303->6637|17333->6638|17386->6662|17416->6663|17462->6680|17502->6692|17526->6706|17561->6719|17605->6734|17635->6735|17686->6757|17716->6758|17762->6775|17804->6789|17845->6808|17900->6834|17930->6835|17980->6856|18039->6886|18069->6887|18111->6900|18141->6901|18193->6924|18223->6925|18253->6926|18344->6988|18374->6989|18420->7006|18461->7018|18491->7019|18538->7037|18568->7038|18598->7039|18657->7069|18687->7070|18741->7095|18831->7156|18861->7157|18907->7174|18937->7175|18991->7200|19021->7201|19071->7222|19146->7268|19176->7269|19230->7294|19320->7355|19350->7356|19396->7373|19426->7374|19468->7387|19498->7388|19529->7390|19559->7391|19589->7392|19681->7455|19711->7456|19761->7477|19834->7521|19864->7522|19918->7547|20008->7608|20038->7609|20084->7626|20114->7627|20169->7653|20199->7654|20249->7675|20289->7686|20319->7687|20366->7705|20396->7706|20426->7707|20485->7737|20515->7738|20569->7763|20659->7824|20689->7825|20735->7842|20765->7843|20839->7888|20869->7889|20922->7913|20952->7914|20998->7931|21070->7974|21100->7975|21151->7997|21181->7998|21227->8015|21542->8301|21572->8302|21624->8325|21654->8326|21700->8343|21888->8503|21929->8522|21985->8549|22015->8550|22062->8568|22105->8583|22129->8597|22162->8608|22192->8609|22240->8628|22270->8629|22317->8647|22347->8648|22378->8650|22408->8651|22454->8668|22556->8742|22597->8761|22653->8788|22683->8789|22730->8807|22773->8822|22797->8836|22830->8847|22860->8848|22908->8867|22938->8868|22980->8881|23010->8882|23048->8892|23078->8893|23180->8967|23209->8968
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|38->7|38->7|39->8|39->8|39->8|40->9|40->9|40->9|41->10|41->10|41->10|43->12|43->12|45->14|46->15|46->15|48->17|70->39|75->44|76->45|76->45|78->47|78->47|78->47|79->48|79->48|79->48|80->49|81->50|81->50|82->51|82->51|83->52|84->53|84->53|85->54|85->54|86->55|86->55|86->55|86->55|87->56|87->56|88->57|88->57|89->58|89->58|89->58|90->59|90->59|91->60|92->61|92->61|93->62|93->62|94->63|94->63|95->64|97->66|97->66|98->67|99->68|99->68|100->69|100->69|101->70|102->71|102->71|103->72|105->74|105->74|106->75|106->75|107->76|107->76|108->77|108->77|109->78|110->79|110->79|111->80|111->80|112->81|112->81|112->81|112->81|112->81|113->82|113->82|114->83|114->83|114->83|114->83|115->84|116->85|116->85|117->86|117->86|118->87|118->87|118->87|118->87|118->87|119->88|119->88|120->89|120->89|121->90|121->90|123->92|123->92|124->93|124->93|124->93|125->94|126->95|126->95|127->96|127->96|128->97|129->98|129->98|130->99|130->99|131->100|131->100|131->100|131->100|132->101|132->101|133->102|133->102|134->103|134->103|134->103|135->104|135->104|136->105|137->106|137->106|138->107|138->107|139->108|139->108|140->109|141->110|141->110|142->111|143->112|143->112|144->113|144->113|145->114|146->115|146->115|147->116|149->118|149->118|150->119|150->119|151->120|151->120|152->121|152->121|153->122|154->123|154->123|155->124|155->124|156->125|156->125|156->125|156->125|156->125|157->126|157->126|158->127|158->127|158->127|158->127|159->128|160->129|160->129|161->130|161->130|162->131|162->131|162->131|162->131|162->131|163->132|163->132|164->133|164->133|165->134|165->134|167->136|167->136|168->137|168->137|168->137|169->138|170->139|170->139|171->140|171->140|172->141|173->142|173->142|174->143|174->143|175->144|175->144|175->144|175->144|176->145|176->145|177->146|177->146|178->147|178->147|178->147|179->148|179->148|180->149|181->150|181->150|182->151|182->151|183->152|183->152|183->152|185->154|185->154|186->155|186->155|186->155|186->155|186->155|186->155|187->156|187->156|188->157|189->158|189->158|190->159|190->159|191->160|191->160|192->161|193->162|193->162|194->163|195->164|195->164|196->165|196->165|197->166|197->166|197->166|197->166|197->166|199->168|199->168|200->169|201->170|201->170|202->171|203->172|203->172|204->173|204->173|205->174|205->174|206->175|206->175|206->175|206->175|206->175|206->175|207->176|207->176|208->177|209->178|209->178|210->179|210->179|212->181|212->181|213->182|213->182|214->183|215->184|215->184|216->185|216->185|217->186|224->193|224->193|225->194|225->194|226->195|229->198|229->198|230->199|230->199|231->200|231->200|231->200|231->200|231->200|232->201|232->201|233->202|233->202|233->202|233->202|234->203|236->205|236->205|237->206|237->206|238->207|238->207|238->207|238->207|238->207|239->208|239->208|240->209|240->209|241->210|241->210|243->212|243->212|244->213|244->213|244->213|245->214|246->215|246->215|247->216|247->216|248->217|249->218|249->218|250->219|250->219|251->220|251->220|251->220|251->220|252->221|252->221|253->222|253->222|254->223|254->223|254->223|255->224|255->224|256->225|257->226|257->226|258->227|258->227|259->228|259->228|259->228|261->230|261->230|262->231|262->231|262->231|262->231|262->231|262->231|263->232|263->232|264->233|265->234|265->234|266->235|266->235|267->236|267->236|268->237|269->238|269->238|270->239|271->240|271->240|272->241|272->241|273->242|273->242|273->242|273->242|273->242|275->244|275->244|276->245|277->246|277->246|278->247|279->248|279->248|280->249|280->249|281->250|281->250|282->251|282->251|282->251|282->251|282->251|282->251|283->252|283->252|284->253|285->254|285->254|286->255|286->255|288->257|288->257|289->258|289->258|290->259|292->261|292->261|293->262|293->262|294->263|301->270|301->270|302->271|302->271|303->272|307->276|307->276|308->277|308->277|309->278|309->278|309->278|309->278|309->278|310->279|310->279|312->281|312->281|312->281|312->281|313->282|315->284|315->284|316->285|316->285|317->286|317->286|317->286|317->286|317->286|318->287|318->287|319->288|319->288|320->289|320->289|323->292|323->292
                  -- GENERATED --
              */
          