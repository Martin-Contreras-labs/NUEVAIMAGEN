
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

object reporteEjecutivo1 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template9[Map[String,String],Map[String,String],utilities.UserMnu,List[String],List[String],String,List[String],List[String],List[String],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
valorizado: List[String], porUnidades: List[String], grafInversion: String, graficoOcupacion: List[String], valorizadoPorBodega: List[String], graficoPotencialPorMesYGrupo: List[String]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""
	

	
	"""),format.raw/*9.2*/("""<script src=""""),_display_(/*9.16*/routes/*9.22*/.Assets.versioned("highcharts/highcharts.js")),format.raw/*9.67*/(""""></script>
	<script src=""""),_display_(/*10.16*/routes/*10.22*/.Assets.versioned("highcharts/series-label.js")),format.raw/*10.69*/(""""></script>
	<script src=""""),_display_(/*11.16*/routes/*11.22*/.Assets.versioned("highcharts/exporting.js")),format.raw/*11.66*/(""""></script>
	<script src=""""),_display_(/*12.16*/routes/*12.22*/.Assets.versioned("highcharts/export-data.js")),format.raw/*12.68*/(""""></script>

	"""),_display_(/*14.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*14.51*/("""
	
	"""),format.raw/*16.2*/("""<div id="enCarga" class="blocker" style="display: block;"><br><br><br><br><br><br><h1>Ahora se esta cargando.....</h1></div>
	
	
	<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*20.4*/barraTitulo(mapDiccionario, "REPORTE EJECUTIVO - SITUACIÓN GLOBAL","")),format.raw/*20.74*/("""
		
		"""),format.raw/*22.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<table id="ESQUEMAGENERAL" class="table table-sm table-bordered table-condensed table-fluid">
					<TR>
				  		<TD>
							<a href="/graficoInversionExcel/">
								<kbd style="background-color: #73C6B6">Excel</kbd>
							</a>
						</TD>
						<TD>
							<a href="/graficoOcupacionExcel/">
								<kbd style="background-color: #73C6B6">Excel</kbd>
							</a>
						</TD>
					</TR>
					
					<TR>
						<TD id="inversion" style="height: auto; margin: 0 auto"></TD>
				  		<TD id="ocupacion" style="height: auto; margin: 0 auto"></TD>
					</TR>
					
					<TR>
						<TD>
							<a href="/graficoValorizadoGrupoExcel/">
								<kbd style="background-color: #73C6B6">Excel</kbd>
							</a>
						</TD>
						<TD>
							<a href="/graficoUnidadesExcel/">
								<kbd style="background-color: #73C6B6">Excel</kbd>
							</a>
						</TD>
					</TR>
					<TR>
				  		<TD width="40%" id="valorizadoGrupoBodegas" style="height: auto; margin: 0 auto"></TD>
				  		<TD width="40%" id="porUnidadesGrupoBodegas" style="height: auto; margin: 0 auto"></TD>
					</TR>
					<TR>
				  		<TD colspan="2">
							<a href="/graficoValorizadoBodegaExcel/">
								<kbd style="background-color: #73C6B6">Excel</kbd>
							</a>
						</TD>
					</TR>
					<TR>
				  		<TD colspan="2" width="90%" id="valorizadoBodegasEmpresa" style="height: auto; margin: 0 auto">
						</TD>
					</TR>
					<TR>
				  		<TD colspan="2">
							<a href="/graficoPotencialExcel/">
								<kbd style="background-color: #73C6B6">Excel</kbd>
							</a>
						</TD>
					</TR>
					<TR>
				  		<TD colspan="2" width="90%" id="graficoPotencialPorMesYGrupo" style="height: auto; margin: 0 0">
						</TD>
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
""")))}),format.raw/*93.2*/("""




"""),format.raw/*98.1*/("""<script type="text/javascript">
	$(document).ready(function () """),format.raw/*99.32*/("""{"""),format.raw/*99.33*/("""
			"""),format.raw/*100.4*/("""let total = 0;
			Highcharts.chart('inversion',"""),format.raw/*101.33*/("""{"""),format.raw/*101.34*/("""
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
        	                'Total M$: ' + Highcharts.numberFormat(total,0),
        	                this.plotLeft,
        	                this.plotTop - 20
        	            	).attr("""),format.raw/*118.30*/("""{"""),format.raw/*118.31*/("""
        	                	"""),format.raw/*119.27*/("""zIndex: 5
        	            	"""),format.raw/*120.23*/("""}"""),format.raw/*120.24*/(""").add()
        	            """),format.raw/*121.22*/("""}"""),format.raw/*121.23*/("""
        	        """),format.raw/*122.18*/("""}"""),format.raw/*122.19*/("""
    			"""),format.raw/*123.8*/("""}"""),format.raw/*123.9*/(""",
				title: """),format.raw/*124.12*/("""{"""),format.raw/*124.13*/("""
        			"""),format.raw/*125.12*/("""text: 'INVERSIÓN'
    			"""),format.raw/*126.8*/("""}"""),format.raw/*126.9*/(""",
    			tooltip: """),format.raw/*127.17*/("""{"""),format.raw/*127.18*/("""
    				"""),format.raw/*128.9*/("""formatter: function() """),format.raw/*128.31*/("""{"""),format.raw/*128.32*/("""
    	                """),format.raw/*129.22*/("""return Highcharts.numberFormat(this.percentage, 2) + '%<br />' + '<b>' + 
    	                this.point.name + '</b><br />M$: ' + Highcharts.numberFormat(this.y, 0);
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
     				data:  """),_display_(/*148.18*/Html(grafInversion)),format.raw/*148.37*/("""
    			"""),format.raw/*149.8*/("""}"""),format.raw/*149.9*/("""]
	        """),format.raw/*150.10*/("""}"""),format.raw/*150.11*/(""");


			Highcharts.chart('ocupacion',"""),format.raw/*153.33*/("""{"""),format.raw/*153.34*/("""
	            """),format.raw/*154.14*/("""chart: """),format.raw/*154.21*/("""{"""),format.raw/*154.22*/("""
	                """),format.raw/*155.18*/("""zoomType: 'xy'
	            """),format.raw/*156.14*/("""}"""),format.raw/*156.15*/(""",
	            title: """),format.raw/*157.21*/("""{"""),format.raw/*157.22*/("""
	                 """),format.raw/*158.19*/("""text: '% OCUPACIÓN MENSUAL (Valorizado)'
	            """),format.raw/*159.14*/("""}"""),format.raw/*159.15*/(""",
	            subtitle: """),format.raw/*160.24*/("""{"""),format.raw/*160.25*/("""
	                """),format.raw/*161.18*/("""text: ''
	            """),format.raw/*162.14*/("""}"""),format.raw/*162.15*/(""",
	            xAxis: ["""),format.raw/*163.22*/("""{"""),format.raw/*163.23*/("""
	            	"""),format.raw/*164.15*/("""categories: """),_display_(/*164.28*/Html(graficoOcupacion.get(0))),format.raw/*164.57*/(""",
	                labels: """),format.raw/*165.26*/("""{"""),format.raw/*165.27*/("""
	                    """),format.raw/*166.22*/("""rotation: -45,
	                    align: 'right',
	                    style: """),format.raw/*168.29*/("""{"""),format.raw/*168.30*/("""
	                        """),format.raw/*169.26*/("""fontSize: '10px',
	                        fontFamily: 'Verdana, sans-serif'
	                    """),format.raw/*171.22*/("""}"""),format.raw/*171.23*/("""
	                """),format.raw/*172.18*/("""}"""),format.raw/*172.19*/("""
	            """),format.raw/*173.14*/("""}"""),format.raw/*173.15*/("""],
	            yAxis: """),format.raw/*174.21*/("""{"""),format.raw/*174.22*/("""
	                """),format.raw/*175.18*/("""min: 0,
	                max: 100,
	                title: """),format.raw/*177.25*/("""{"""),format.raw/*177.26*/("""
	                    """),format.raw/*178.22*/("""text: 'Porcentaje'
	                """),format.raw/*179.18*/("""}"""),format.raw/*179.19*/(""",
	                stackLabels: """),format.raw/*180.31*/("""{"""),format.raw/*180.32*/("""
	                    """),format.raw/*181.22*/("""enabled: true,
	                    style: """),format.raw/*182.29*/("""{"""),format.raw/*182.30*/("""
	                        """),format.raw/*183.26*/("""fontWeight: 'bold',
	                        color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
	                    """),format.raw/*185.22*/("""}"""),format.raw/*185.23*/("""
	                """),format.raw/*186.18*/("""}"""),format.raw/*186.19*/("""
	            """),format.raw/*187.14*/("""}"""),format.raw/*187.15*/(""",
	            tooltip: """),format.raw/*188.23*/("""{"""),format.raw/*188.24*/("""
	                """),format.raw/*189.18*/("""shared: true
	            """),format.raw/*190.14*/("""}"""),format.raw/*190.15*/(""",
	            legend: """),format.raw/*191.22*/("""{"""),format.raw/*191.23*/("""
	                """),format.raw/*192.18*/("""layout: 'horizontal',
	                align: 'center',
	                verticalAlign: 'top',
	                y: 30,
	                floating: false,
	                borderWidth: 0,
	                backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'
	            """),format.raw/*199.14*/("""}"""),format.raw/*199.15*/(""",
	            plotOptions: """),format.raw/*200.27*/("""{"""),format.raw/*200.28*/("""
	                """),format.raw/*201.18*/("""spline: """),format.raw/*201.26*/("""{"""),format.raw/*201.27*/("""
	                    """),format.raw/*202.22*/("""lineWidth: 1,
	                    states: """),format.raw/*203.30*/("""{"""),format.raw/*203.31*/("""
	                        """),format.raw/*204.26*/("""hover: """),format.raw/*204.33*/("""{"""),format.raw/*204.34*/("""
	                            """),format.raw/*205.30*/("""lineWidth: 6
	                        """),format.raw/*206.26*/("""}"""),format.raw/*206.27*/("""
	                    """),format.raw/*207.22*/("""}"""),format.raw/*207.23*/(""",
	                    marker: """),format.raw/*208.30*/("""{"""),format.raw/*208.31*/("""
	                        """),format.raw/*209.26*/("""enabled: false
	                    """),format.raw/*210.22*/("""}"""),format.raw/*210.23*/("""
	                """),format.raw/*211.18*/("""}"""),format.raw/*211.19*/("""
	            """),format.raw/*212.14*/("""}"""),format.raw/*212.15*/(""",
	            series: [
	               """),_display_(/*214.18*/for(lista <- graficoOcupacion.tail) yield /*214.53*/{_display_(Seq[Any](format.raw/*214.54*/("""
	            	   """),_display_(/*215.19*/Html(lista)),format.raw/*215.30*/("""
	               """)))}),format.raw/*216.18*/("""

	            """),format.raw/*218.14*/("""]
	        """),format.raw/*219.10*/("""}"""),format.raw/*219.11*/(""");

			Highcharts.chart('valorizadoGrupoBodegas',"""),format.raw/*221.46*/("""{"""),format.raw/*221.47*/("""
	            """),format.raw/*222.14*/("""chart: """),format.raw/*222.21*/("""{"""),format.raw/*222.22*/("""
                	"""),format.raw/*223.18*/("""type: 'column',
                	events: """),format.raw/*224.26*/("""{"""),format.raw/*224.27*/("""
        	            """),format.raw/*225.22*/("""load: function(event) """),format.raw/*225.44*/("""{"""),format.raw/*225.45*/("""
        	                """),format.raw/*226.26*/("""superTotal=total;
        	              var text = this.renderer.text(
        	                'Total M$: ' + Highcharts.numberFormat(total,0),
        	                this.plotLeft -60,
        	                this.plotTop - 60
        	            	).attr("""),format.raw/*231.30*/("""{"""),format.raw/*231.31*/("""
        	                	"""),format.raw/*232.27*/("""zIndex: 5
        	            	"""),format.raw/*233.23*/("""}"""),format.raw/*233.24*/(""").add()
        	            """),format.raw/*234.22*/("""}"""),format.raw/*234.23*/("""
        	        """),format.raw/*235.18*/("""}"""),format.raw/*235.19*/("""
	            """),format.raw/*236.14*/("""}"""),format.raw/*236.15*/(""",
	            title: """),format.raw/*237.21*/("""{"""),format.raw/*237.22*/("""
	                """),format.raw/*238.18*/("""text: 'DISTRIBUCION PORCENTUAL POR VALORIZACION'
	            """),format.raw/*239.14*/("""}"""),format.raw/*239.15*/(""",
	            xAxis: """),format.raw/*240.21*/("""{"""),format.raw/*240.22*/("""
	            	"""),format.raw/*241.15*/("""categories: """),_display_(/*241.28*/Html(valorizado.get(0))),format.raw/*241.51*/(""",
	                labels: """),format.raw/*242.26*/("""{"""),format.raw/*242.27*/("""
	                    """),format.raw/*243.22*/("""rotation: -45,
	                    align: 'right',
	                    style: """),format.raw/*245.29*/("""{"""),format.raw/*245.30*/("""
	                        """),format.raw/*246.26*/("""fontSize: '10px',
	                        fontFamily: 'Verdana, sans-serif'
	                    """),format.raw/*248.22*/("""}"""),format.raw/*248.23*/("""
	                """),format.raw/*249.18*/("""}"""),format.raw/*249.19*/("""
	            """),format.raw/*250.14*/("""}"""),format.raw/*250.15*/(""",
	            yAxis: """),format.raw/*251.21*/("""{"""),format.raw/*251.22*/("""
	                """),format.raw/*252.18*/("""min: 0,
					max:100,
	                title: """),format.raw/*254.25*/("""{"""),format.raw/*254.26*/("""
	                    """),format.raw/*255.22*/("""text: 'Porcentaje'
	                """),format.raw/*256.18*/("""}"""),format.raw/*256.19*/(""",
	                stackLabels: """),format.raw/*257.31*/("""{"""),format.raw/*257.32*/("""
	                    """),format.raw/*258.22*/("""enabled: false,
	                    style: """),format.raw/*259.29*/("""{"""),format.raw/*259.30*/("""
	                        """),format.raw/*260.26*/("""fontWeight: 'bold',
	                        color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
	                    """),format.raw/*262.22*/("""}"""),format.raw/*262.23*/("""
	                """),format.raw/*263.18*/("""}"""),format.raw/*263.19*/("""
	            """),format.raw/*264.14*/("""}"""),format.raw/*264.15*/(""",
	            legend: """),format.raw/*265.22*/("""{"""),format.raw/*265.23*/("""
	            	"""),format.raw/*266.15*/("""layout: 'horizontal',
	                align: 'center',
	                verticalAlign: 'top',
	                y: 30,
	                floating: false,
	                backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
	                borderColor: '#CCC',
	                borderWidth: 1,
	                shadow: false
	            """),format.raw/*275.14*/("""}"""),format.raw/*275.15*/(""",
	            tooltip: """),format.raw/*276.23*/("""{"""),format.raw/*276.24*/("""
	                """),format.raw/*277.18*/("""formatter: function() """),format.raw/*277.40*/("""{"""),format.raw/*277.41*/("""
	                    """),format.raw/*278.22*/("""return '<b>'+ this.x +'</b><br/>'+
	                        this.series.name +'  '+ Highcharts.numberFormat(this.y,0) +' %';
	                """),format.raw/*280.18*/("""}"""),format.raw/*280.19*/("""
	            """),format.raw/*281.14*/("""}"""),format.raw/*281.15*/(""",
	            plotOptions: """),format.raw/*282.27*/("""{"""),format.raw/*282.28*/("""
	                """),format.raw/*283.18*/("""column: """),format.raw/*283.26*/("""{"""),format.raw/*283.27*/("""
	                    """),format.raw/*284.22*/("""stacking: 'normal',
	                    dataLabels: """),format.raw/*285.34*/("""{"""),format.raw/*285.35*/("""
	                        """),format.raw/*286.26*/("""enabled: false,
	                        color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
	                        style: """),format.raw/*288.33*/("""{"""),format.raw/*288.34*/("""
	                            """),format.raw/*289.30*/("""textShadow: '0 0 3px black, 0 0 3px black'
	                        """),format.raw/*290.26*/("""}"""),format.raw/*290.27*/("""
	                    """),format.raw/*291.22*/("""}"""),format.raw/*291.23*/("""
	                """),format.raw/*292.18*/("""}"""),format.raw/*292.19*/("""
	            """),format.raw/*293.14*/("""}"""),format.raw/*293.15*/(""",
	            series: """),_display_(/*294.23*/Html(valorizado.get(1))),format.raw/*294.46*/("""
	        """),format.raw/*295.10*/("""}"""),format.raw/*295.11*/(""");

			Highcharts.chart('graficoPotencialPorMesYGrupo',"""),format.raw/*297.52*/("""{"""),format.raw/*297.53*/("""
	            """),format.raw/*298.14*/("""chart: """),format.raw/*298.21*/("""{"""),format.raw/*298.22*/("""
                	"""),format.raw/*299.18*/("""type: 'column',
                	events: """),format.raw/*300.26*/("""{"""),format.raw/*300.27*/("""
        	            """),format.raw/*301.22*/("""load: function(event) """),format.raw/*301.44*/("""{"""),format.raw/*301.45*/("""
        	              """),format.raw/*302.24*/("""let totalarr = 0;
        	                for(var i=0, len=this.series[1].yData.length; i<len; i++)"""),format.raw/*303.83*/("""{"""),format.raw/*303.84*/("""
        	                    """),format.raw/*304.30*/("""totalarr += this.series[0].yData[i]+this.series[1].yData[i];
        	                """),format.raw/*305.26*/("""}"""),format.raw/*305.27*/("""
        	              """),format.raw/*306.24*/("""var text = this.renderer.text(
        	                'Total M$: ' + Highcharts.numberFormat(totalarr,0),
        	                this.plotLeft -60,
        	                this.plotTop - 60
        	            	).attr("""),format.raw/*310.30*/("""{"""),format.raw/*310.31*/("""
        	                	"""),format.raw/*311.27*/("""zIndex: 5
        	            	"""),format.raw/*312.23*/("""}"""),format.raw/*312.24*/(""").add()
        	            """),format.raw/*313.22*/("""}"""),format.raw/*313.23*/("""
        	        """),format.raw/*314.18*/("""}"""),format.raw/*314.19*/("""
	            """),format.raw/*315.14*/("""}"""),format.raw/*315.15*/(""",
	            title: """),format.raw/*316.21*/("""{"""),format.raw/*316.22*/("""
	                """),format.raw/*317.18*/("""text: 'POTENCIAL DE """),_display_(/*317.39*/mapDiccionario/*317.53*/.get("ARRIENDO")),format.raw/*317.69*/(""" """),format.raw/*317.70*/("""30 DIAS <br> (a Precio Lista en Miles de """),_display_(/*317.112*/mapDiccionario/*317.126*/.get("PESOS")),format.raw/*317.139*/(""")'
	            """),format.raw/*318.14*/("""}"""),format.raw/*318.15*/(""",
	            xAxis: """),format.raw/*319.21*/("""{"""),format.raw/*319.22*/("""
	            	"""),format.raw/*320.15*/("""categories: """),_display_(/*320.28*/Html(graficoPotencialPorMesYGrupo.get(0))),format.raw/*320.69*/(""",
	                labels: """),format.raw/*321.26*/("""{"""),format.raw/*321.27*/("""
	                    """),format.raw/*322.22*/("""rotation: -45,
	                    align: 'right',
	                    style: """),format.raw/*324.29*/("""{"""),format.raw/*324.30*/("""
	                        """),format.raw/*325.26*/("""fontSize: '10px',
	                        fontFamily: 'Verdana, sans-serif'
	                    """),format.raw/*327.22*/("""}"""),format.raw/*327.23*/("""
	                """),format.raw/*328.18*/("""}"""),format.raw/*328.19*/("""
	            """),format.raw/*329.14*/("""}"""),format.raw/*329.15*/(""",
	            yAxis: """),format.raw/*330.21*/("""{"""),format.raw/*330.22*/("""
	                """),format.raw/*331.18*/("""min: 0,
	                title: """),format.raw/*332.25*/("""{"""),format.raw/*332.26*/("""
	                    """),format.raw/*333.22*/("""text: 'Miles de """),_display_(/*333.39*/mapDiccionario/*333.53*/.get("PESOS")),format.raw/*333.66*/("""'
	                """),format.raw/*334.18*/("""}"""),format.raw/*334.19*/(""",
	                stackLabels: """),format.raw/*335.31*/("""{"""),format.raw/*335.32*/("""
	                    """),format.raw/*336.22*/("""enabled: false,
	                    style: """),format.raw/*337.29*/("""{"""),format.raw/*337.30*/("""
	                        """),format.raw/*338.26*/("""fontWeight: 'bold',
	                        color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
	                    """),format.raw/*340.22*/("""}"""),format.raw/*340.23*/("""
	                """),format.raw/*341.18*/("""}"""),format.raw/*341.19*/("""
	            """),format.raw/*342.14*/("""}"""),format.raw/*342.15*/(""",
	            legend: """),format.raw/*343.22*/("""{"""),format.raw/*343.23*/("""
	            	"""),format.raw/*344.15*/("""layout: 'horizontal',
	                align: 'center',
	                verticalAlign: 'top',
	                y: 60,
	                floating: false,
	                backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
	                borderColor: '#CCC',
	                borderWidth: 1,
	                shadow: false
	            """),format.raw/*353.14*/("""}"""),format.raw/*353.15*/(""",
	            tooltip: """),format.raw/*354.23*/("""{"""),format.raw/*354.24*/("""
	                """),format.raw/*355.18*/("""formatter: function() """),format.raw/*355.40*/("""{"""),format.raw/*355.41*/("""
	                    """),format.raw/*356.22*/("""return '<b>'+ this.x +'</b><br/>'+
	                        this.series.name +' M$: '+ Highcharts.numberFormat(this.y,0) +'<br/>'+
	                        'Total M$: '+ Highcharts.numberFormat(this.point.stackTotal,0);
	                """),format.raw/*359.18*/("""}"""),format.raw/*359.19*/("""
	            """),format.raw/*360.14*/("""}"""),format.raw/*360.15*/(""",
	            plotOptions: """),format.raw/*361.27*/("""{"""),format.raw/*361.28*/("""
	                """),format.raw/*362.18*/("""column: """),format.raw/*362.26*/("""{"""),format.raw/*362.27*/("""
	                    """),format.raw/*363.22*/("""stacking: 'normal',
	                    dataLabels: """),format.raw/*364.34*/("""{"""),format.raw/*364.35*/("""
	                        """),format.raw/*365.26*/("""enabled: false,
	                        color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
	                        style: """),format.raw/*367.33*/("""{"""),format.raw/*367.34*/("""
	                            """),format.raw/*368.30*/("""textShadow: '0 0 3px black, 0 0 3px black'
	                        """),format.raw/*369.26*/("""}"""),format.raw/*369.27*/("""
	                    """),format.raw/*370.22*/("""}"""),format.raw/*370.23*/("""
	                """),format.raw/*371.18*/("""}"""),format.raw/*371.19*/("""
	            """),format.raw/*372.14*/("""}"""),format.raw/*372.15*/(""",
	            series: """),_display_(/*373.23*/Html(graficoPotencialPorMesYGrupo.get(1))),format.raw/*373.64*/("""
	        """),format.raw/*374.10*/("""}"""),format.raw/*374.11*/(""");

			Highcharts.chart('porUnidadesGrupoBodegas',"""),format.raw/*376.47*/("""{"""),format.raw/*376.48*/("""
	            """),format.raw/*377.14*/("""chart: """),format.raw/*377.21*/("""{"""),format.raw/*377.22*/("""
                	"""),format.raw/*378.18*/("""type: 'column'
	            """),format.raw/*379.14*/("""}"""),format.raw/*379.15*/(""",
	            title: """),format.raw/*380.21*/("""{"""),format.raw/*380.22*/("""
	                """),format.raw/*381.18*/("""text: 'DISTRIBUCION PORCENTUAL POR UNIDADES'
	            """),format.raw/*382.14*/("""}"""),format.raw/*382.15*/(""",
	            xAxis: """),format.raw/*383.21*/("""{"""),format.raw/*383.22*/("""
	            	"""),format.raw/*384.15*/("""categories: """),_display_(/*384.28*/Html(porUnidades.get(0))),format.raw/*384.52*/(""",
	                labels: """),format.raw/*385.26*/("""{"""),format.raw/*385.27*/("""
	                    """),format.raw/*386.22*/("""rotation: -45,
	                    align: 'right',
	                    style: """),format.raw/*388.29*/("""{"""),format.raw/*388.30*/("""
	                        """),format.raw/*389.26*/("""fontSize: '10px',
	                        fontFamily: 'Verdana, sans-serif'
	                    """),format.raw/*391.22*/("""}"""),format.raw/*391.23*/("""
	                """),format.raw/*392.18*/("""}"""),format.raw/*392.19*/("""
	            """),format.raw/*393.14*/("""}"""),format.raw/*393.15*/(""",
	            yAxis: """),format.raw/*394.21*/("""{"""),format.raw/*394.22*/("""
	                """),format.raw/*395.18*/("""min: 0,
					max:100,
	                title: """),format.raw/*397.25*/("""{"""),format.raw/*397.26*/("""
	                    """),format.raw/*398.22*/("""text: 'Porcentaje'
	                """),format.raw/*399.18*/("""}"""),format.raw/*399.19*/(""",
	                stackLabels: """),format.raw/*400.31*/("""{"""),format.raw/*400.32*/("""
	                    """),format.raw/*401.22*/("""enabled: false,
	                    style: """),format.raw/*402.29*/("""{"""),format.raw/*402.30*/("""
	                        """),format.raw/*403.26*/("""fontWeight: 'bold',
	                        color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
	                    """),format.raw/*405.22*/("""}"""),format.raw/*405.23*/("""
	                """),format.raw/*406.18*/("""}"""),format.raw/*406.19*/("""
	            """),format.raw/*407.14*/("""}"""),format.raw/*407.15*/(""",
	            legend: """),format.raw/*408.22*/("""{"""),format.raw/*408.23*/("""
	            	"""),format.raw/*409.15*/("""layout: 'horizontal',
	                align: 'center',
	                verticalAlign: 'top',
	                y: 30,
	                floating: false,
	                backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
	                borderColor: '#CCC',
	                borderWidth: 1,
	                shadow: false
	            """),format.raw/*418.14*/("""}"""),format.raw/*418.15*/(""",
	            tooltip: """),format.raw/*419.23*/("""{"""),format.raw/*419.24*/("""
	            	"""),format.raw/*420.15*/("""formatter: function() """),format.raw/*420.37*/("""{"""),format.raw/*420.38*/("""
	                    """),format.raw/*421.22*/("""return '<b>'+ this.x +'</b><br/>'+
	                        this.series.name +'  '+ Highcharts.numberFormat(this.y,0) +' %';
	                """),format.raw/*423.18*/("""}"""),format.raw/*423.19*/("""
	            """),format.raw/*424.14*/("""}"""),format.raw/*424.15*/(""",
	            plotOptions: """),format.raw/*425.27*/("""{"""),format.raw/*425.28*/("""
	                """),format.raw/*426.18*/("""column: """),format.raw/*426.26*/("""{"""),format.raw/*426.27*/("""
	                    """),format.raw/*427.22*/("""stacking: 'normal',
	                    dataLabels: """),format.raw/*428.34*/("""{"""),format.raw/*428.35*/("""
	                        """),format.raw/*429.26*/("""enabled: false,
	                        color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
	                        style: """),format.raw/*431.33*/("""{"""),format.raw/*431.34*/("""
	                            """),format.raw/*432.30*/("""textShadow: '0 0 3px black, 0 0 3px black'
	                        """),format.raw/*433.26*/("""}"""),format.raw/*433.27*/("""
	                    """),format.raw/*434.22*/("""}"""),format.raw/*434.23*/("""
	                """),format.raw/*435.18*/("""}"""),format.raw/*435.19*/("""
	            """),format.raw/*436.14*/("""}"""),format.raw/*436.15*/(""",
	            series: """),_display_(/*437.23*/Html(porUnidades.get(1))),format.raw/*437.47*/("""
	        """),format.raw/*438.10*/("""}"""),format.raw/*438.11*/(""");

			Highcharts.chart('valorizadoBodegasEmpresa',"""),format.raw/*440.48*/("""{"""),format.raw/*440.49*/("""
	            """),format.raw/*441.14*/("""chart: """),format.raw/*441.21*/("""{"""),format.raw/*441.22*/("""
	                """),format.raw/*442.18*/("""type: 'column',
	                events: """),format.raw/*443.26*/("""{"""),format.raw/*443.27*/("""
	    	            """),format.raw/*444.19*/("""load: function(event) """),format.raw/*444.41*/("""{"""),format.raw/*444.42*/("""
	
	    	              """),format.raw/*446.21*/("""var text = this.renderer.text(
	    	                'Total M$: ' + Highcharts.numberFormat(superTotal,0),
	    	                this.plotLeft -50,
	    	                this.plotTop -20
	    	            	).attr("""),format.raw/*450.27*/("""{"""),format.raw/*450.28*/("""
	    	                	"""),format.raw/*451.24*/("""zIndex: 5
	    	            	"""),format.raw/*452.20*/("""}"""),format.raw/*452.21*/(""").add()
	    	            """),format.raw/*453.19*/("""}"""),format.raw/*453.20*/("""
	    	        """),format.raw/*454.15*/("""}"""),format.raw/*454.16*/("""
	            """),format.raw/*455.14*/("""}"""),format.raw/*455.15*/(""",
	            title: """),format.raw/*456.21*/("""{"""),format.raw/*456.22*/("""
	                """),format.raw/*457.18*/("""text: 'INVENTARIO VALORIZADO - solo """),_display_(/*457.55*/mapDiccionario/*457.69*/.get("BODEGA")),format.raw/*457.83*/("""/PROYECTO (en Miles de """),_display_(/*457.107*/mapDiccionario/*457.121*/.get("PESOS")),format.raw/*457.134*/(""")'
	            """),format.raw/*458.14*/("""}"""),format.raw/*458.15*/(""",
	            xAxis: """),format.raw/*459.21*/("""{"""),format.raw/*459.22*/("""
	            	"""),format.raw/*460.15*/("""categories: """),_display_(/*460.28*/Html(valorizadoPorBodega.get(0))),format.raw/*460.60*/(""",
	                labels: """),format.raw/*461.26*/("""{"""),format.raw/*461.27*/("""
	                    """),format.raw/*462.22*/("""rotation: -45,
	                    align: 'right',
	                    style: """),format.raw/*464.29*/("""{"""),format.raw/*464.30*/("""
	                        """),format.raw/*465.26*/("""fontSize: '10px',
	                        fontFamily: 'Verdana, sans-serif'
	                    """),format.raw/*467.22*/("""}"""),format.raw/*467.23*/("""
	                """),format.raw/*468.18*/("""}"""),format.raw/*468.19*/("""
	            """),format.raw/*469.14*/("""}"""),format.raw/*469.15*/(""",
	            yAxis: """),format.raw/*470.21*/("""{"""),format.raw/*470.22*/("""
	                """),format.raw/*471.18*/("""min: 0,
	                title: """),format.raw/*472.25*/("""{"""),format.raw/*472.26*/("""
	                    """),format.raw/*473.22*/("""text: 'Miles de """),_display_(/*473.39*/mapDiccionario/*473.53*/.get("PESOS")),format.raw/*473.66*/("""'
	                """),format.raw/*474.18*/("""}"""),format.raw/*474.19*/(""",
	                stackLabels: """),format.raw/*475.31*/("""{"""),format.raw/*475.32*/("""
	                    """),format.raw/*476.22*/("""enabled: false,
	                    style: """),format.raw/*477.29*/("""{"""),format.raw/*477.30*/("""
	                        """),format.raw/*478.26*/("""fontWeight: 'bold',
	                        color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
	                    """),format.raw/*480.22*/("""}"""),format.raw/*480.23*/("""
	                """),format.raw/*481.18*/("""}"""),format.raw/*481.19*/("""
	            """),format.raw/*482.14*/("""}"""),format.raw/*482.15*/(""",
	            
	            legend: """),format.raw/*484.22*/("""{"""),format.raw/*484.23*/("""
	            	"""),format.raw/*485.15*/("""layout: 'horizontal',
	                align: 'center',
	                verticalAlign: 'top',
	                y: 30,
	                floating: false,
	                backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
	                borderColor: '#CCC',
	                borderWidth: 1,
	                shadow: false
	            """),format.raw/*494.14*/("""}"""),format.raw/*494.15*/(""",
	            tooltip: """),format.raw/*495.23*/("""{"""),format.raw/*495.24*/("""
	            	"""),format.raw/*496.15*/("""formatter: function() """),format.raw/*496.37*/("""{"""),format.raw/*496.38*/("""
	                    """),format.raw/*497.22*/("""return '<b>'+ this.series.name +' M$: '+ Highcharts.numberFormat(this.y,0) +'</b>';
	                """),format.raw/*498.18*/("""}"""),format.raw/*498.19*/("""
	            """),format.raw/*499.14*/("""}"""),format.raw/*499.15*/(""",
	            plotOptions: """),format.raw/*500.27*/("""{"""),format.raw/*500.28*/("""
	                """),format.raw/*501.18*/("""column: """),format.raw/*501.26*/("""{"""),format.raw/*501.27*/("""
	                    """),format.raw/*502.22*/("""stacking: 'normal',
	                    dataLabels: """),format.raw/*503.34*/("""{"""),format.raw/*503.35*/("""
	                        """),format.raw/*504.26*/("""enabled: false,
	                        color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
	                        style: """),format.raw/*506.33*/("""{"""),format.raw/*506.34*/("""
	                            """),format.raw/*507.30*/("""textShadow: '0 0 3px black, 0 0 3px black'
	                        """),format.raw/*508.26*/("""}"""),format.raw/*508.27*/("""
	                    """),format.raw/*509.22*/("""}"""),format.raw/*509.23*/("""
	                """),format.raw/*510.18*/("""}"""),format.raw/*510.19*/("""
	            """),format.raw/*511.14*/("""}"""),format.raw/*511.15*/(""",
	            series: """),_display_(/*512.23*/Html(valorizadoPorBodega.get(1))),format.raw/*512.55*/("""
	        """),format.raw/*513.10*/("""}"""),format.raw/*513.11*/(""");


			document.getElementById('enProceso').style.display="none";
			document.getElementById('mostrarmostrar').style.display="block";
			document.getElementById('enCarga').style.display="none";
			
	   """),format.raw/*520.5*/("""}"""),format.raw/*520.6*/(""");
			  
	
</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,valorizado:List[String],porUnidades:List[String],grafInversion:String,graficoOcupacion:List[String],valorizadoPorBodega:List[String],graficoPotencialPorMesYGrupo:List[String]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,valorizado,porUnidades,grafInversion,graficoOcupacion,valorizadoPorBodega,graficoPotencialPorMesYGrupo)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[String],List[String],String,List[String],List[String],List[String]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,valorizado,porUnidades,grafInversion,graficoOcupacion,valorizadoPorBodega,graficoPotencialPorMesYGrupo) => apply(mapDiccionario,mapPermiso,userMnu,valorizado,porUnidades,grafInversion,graficoOcupacion,valorizadoPorBodega,graficoPotencialPorMesYGrupo)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reporteEjecutivo1.scala.html
                  HASH: 18eb9639f340bf7752b9f0d081b7163d7775f5ff
                  MATRIX: 1089->1|1466->285|1499->293|1515->301|1554->303|1587->310|1627->324|1641->330|1706->375|1760->402|1775->408|1843->455|1897->482|1912->488|1977->532|2031->559|2046->565|2113->611|2154->626|2223->674|2254->678|2461->859|2552->929|2585->935|4760->3080|4792->3085|4883->3148|4912->3149|4944->3153|5020->3200|5050->3201|5093->3215|5129->3222|5159->3223|5206->3241|5268->3274|5298->3275|5334->3283|5450->3370|5480->3371|5530->3392|5560->3393|5611->3415|5662->3437|5692->3438|5747->3464|5833->3521|5863->3522|5922->3552|6010->3611|6040->3612|6093->3636|6339->3853|6369->3854|6425->3881|6486->3913|6516->3914|6574->3943|6604->3944|6651->3962|6681->3963|6717->3971|6746->3972|6788->3985|6818->3986|6859->3998|6912->4023|6941->4024|6988->4042|7018->4043|7055->4052|7106->4074|7136->4075|7187->4097|7401->4282|7431->4283|7481->4305|7510->4306|7558->4325|7588->4326|7629->4338|7663->4343|7693->4344|7738->4360|7878->4471|7908->4472|7957->4492|8029->4535|8059->4536|8098->4546|8128->4547|8174->4564|8204->4565|8245->4577|8275->4578|8311->4586|8340->4587|8393->4611|8423->4612|8464->4624|8553->4685|8594->4704|8630->4712|8659->4713|8699->4724|8729->4725|8795->4762|8825->4763|8868->4777|8904->4784|8934->4785|8981->4803|9038->4831|9068->4832|9119->4854|9149->4855|9197->4874|9280->4928|9310->4929|9364->4954|9394->4955|9441->4973|9492->4995|9522->4996|9574->5019|9604->5020|9648->5035|9689->5048|9740->5077|9796->5104|9826->5105|9877->5127|9986->5207|10016->5208|10071->5234|10198->5332|10228->5333|10275->5351|10305->5352|10348->5366|10378->5367|10430->5390|10460->5391|10507->5409|10595->5468|10625->5469|10676->5491|10741->5527|10771->5528|10832->5560|10862->5561|10913->5583|10985->5626|11015->5627|11070->5653|11231->5785|11261->5786|11308->5804|11338->5805|11381->5819|11411->5820|11464->5844|11494->5845|11541->5863|11596->5889|11626->5890|11678->5913|11708->5914|11755->5932|12091->6239|12121->6240|12178->6268|12208->6269|12255->6287|12292->6295|12322->6296|12373->6318|12445->6361|12475->6362|12530->6388|12566->6395|12596->6396|12655->6426|12722->6464|12752->6465|12803->6487|12833->6488|12893->6519|12923->6520|12978->6546|13043->6582|13073->6583|13120->6601|13150->6602|13193->6616|13223->6617|13293->6659|13345->6694|13385->6695|13432->6714|13465->6725|13515->6743|13559->6758|13599->6769|13629->6770|13707->6819|13737->6820|13780->6834|13816->6841|13846->6842|13893->6860|13963->6901|13993->6902|14044->6924|14095->6946|14125->6947|14180->6973|14471->7235|14501->7236|14557->7263|14618->7295|14648->7296|14706->7325|14736->7326|14783->7344|14813->7345|14856->7359|14886->7360|14937->7382|14967->7383|15014->7401|15105->7463|15135->7464|15186->7486|15216->7487|15260->7502|15301->7515|15346->7538|15402->7565|15432->7566|15483->7588|15592->7668|15622->7669|15677->7695|15804->7793|15834->7794|15881->7812|15911->7813|15954->7827|15984->7828|16035->7850|16065->7851|16112->7869|16187->7915|16217->7916|16268->7938|16333->7974|16363->7975|16424->8007|16454->8008|16505->8030|16578->8074|16608->8075|16663->8101|16824->8233|16854->8234|16901->8252|16931->8253|16974->8267|17004->8268|17056->8291|17086->8292|17130->8307|17524->8672|17554->8673|17607->8697|17637->8698|17684->8716|17735->8738|17765->8739|17816->8761|17987->8903|18017->8904|18060->8918|18090->8919|18147->8947|18177->8948|18224->8966|18261->8974|18291->8975|18342->8997|18424->9050|18454->9051|18509->9077|18685->9224|18715->9225|18774->9255|18871->9323|18901->9324|18952->9346|18982->9347|19029->9365|19059->9366|19102->9380|19132->9381|19184->9405|19229->9428|19268->9438|19298->9439|19382->9494|19412->9495|19455->9509|19491->9516|19521->9517|19568->9535|19638->9576|19668->9577|19719->9599|19770->9621|19800->9622|19853->9646|19982->9746|20012->9747|20071->9777|20186->9863|20216->9864|20269->9888|20522->10112|20552->10113|20608->10140|20669->10172|20699->10173|20757->10202|20787->10203|20834->10221|20864->10222|20907->10236|20937->10237|20988->10259|21018->10260|21065->10278|21114->10299|21138->10313|21176->10329|21206->10330|21277->10372|21302->10386|21338->10399|21383->10415|21413->10416|21464->10438|21494->10439|21538->10454|21579->10467|21642->10508|21698->10535|21728->10536|21779->10558|21888->10638|21918->10639|21973->10665|22100->10763|22130->10764|22177->10782|22207->10783|22250->10797|22280->10798|22331->10820|22361->10821|22408->10839|22469->10871|22499->10872|22550->10894|22595->10911|22619->10925|22654->10938|22702->10957|22732->10958|22793->10990|22823->10991|22874->11013|22947->11057|22977->11058|23032->11084|23193->11216|23223->11217|23270->11235|23300->11236|23343->11250|23373->11251|23425->11274|23455->11275|23499->11290|23893->11655|23923->11656|23976->11680|24006->11681|24053->11699|24104->11721|24134->11722|24185->11744|24451->11981|24481->11982|24524->11996|24554->11997|24611->12025|24641->12026|24688->12044|24725->12052|24755->12053|24806->12075|24888->12128|24918->12129|24973->12155|25149->12302|25179->12303|25238->12333|25335->12401|25365->12402|25416->12424|25446->12425|25493->12443|25523->12444|25566->12458|25596->12459|25648->12483|25711->12524|25750->12534|25780->12535|25859->12585|25889->12586|25932->12600|25968->12607|25998->12608|26045->12626|26102->12654|26132->12655|26183->12677|26213->12678|26260->12696|26347->12754|26377->12755|26428->12777|26458->12778|26502->12793|26543->12806|26589->12830|26645->12857|26675->12858|26726->12880|26835->12960|26865->12961|26920->12987|27047->13085|27077->13086|27124->13104|27154->13105|27197->13119|27227->13120|27278->13142|27308->13143|27355->13161|27430->13207|27460->13208|27511->13230|27576->13266|27606->13267|27667->13299|27697->13300|27748->13322|27821->13366|27851->13367|27906->13393|28067->13525|28097->13526|28144->13544|28174->13545|28217->13559|28247->13560|28299->13583|28329->13584|28373->13599|28767->13964|28797->13965|28850->13989|28880->13990|28924->14005|28975->14027|29005->14028|29056->14050|29227->14192|29257->14193|29300->14207|29330->14208|29387->14236|29417->14237|29464->14255|29501->14263|29531->14264|29582->14286|29664->14339|29694->14340|29749->14366|29925->14513|29955->14514|30014->14544|30111->14612|30141->14613|30192->14635|30222->14636|30269->14654|30299->14655|30342->14669|30372->14670|30424->14694|30470->14718|30509->14728|30539->14729|30619->14780|30649->14781|30692->14795|30728->14802|30758->14803|30805->14821|30875->14862|30905->14863|30953->14882|31004->14904|31034->14905|31086->14928|31328->15141|31358->15142|31411->15166|31469->15195|31499->15196|31554->15222|31584->15223|31628->15238|31658->15239|31701->15253|31731->15254|31782->15276|31812->15277|31859->15295|31924->15332|31948->15346|31984->15360|32037->15384|32062->15398|32098->15411|32143->15427|32173->15428|32224->15450|32254->15451|32298->15466|32339->15479|32393->15511|32449->15538|32479->15539|32530->15561|32639->15641|32669->15642|32724->15668|32851->15766|32881->15767|32928->15785|32958->15786|33001->15800|33031->15801|33082->15823|33112->15824|33159->15842|33220->15874|33250->15875|33301->15897|33346->15914|33370->15928|33405->15941|33453->15960|33483->15961|33544->15993|33574->15994|33625->16016|33698->16060|33728->16061|33783->16087|33944->16219|33974->16220|34021->16238|34051->16239|34094->16253|34124->16254|34190->16291|34220->16292|34264->16307|34658->16672|34688->16673|34741->16697|34771->16698|34815->16713|34866->16735|34896->16736|34947->16758|35077->16859|35107->16860|35150->16874|35180->16875|35237->16903|35267->16904|35314->16922|35351->16930|35381->16931|35432->16953|35514->17006|35544->17007|35599->17033|35775->17180|35805->17181|35864->17211|35961->17279|35991->17280|36042->17302|36072->17303|36119->17321|36149->17322|36192->17336|36222->17337|36274->17361|36328->17393|36367->17403|36397->17404|36628->17607|36657->17608
                  LINES: 28->1|34->3|36->5|36->5|36->5|40->9|40->9|40->9|40->9|41->10|41->10|41->10|42->11|42->11|42->11|43->12|43->12|43->12|45->14|45->14|47->16|51->20|51->20|53->22|124->93|129->98|130->99|130->99|131->100|132->101|132->101|133->102|133->102|133->102|134->103|135->104|135->104|136->105|139->108|139->108|140->109|140->109|141->110|141->110|141->110|142->111|142->111|142->111|143->112|144->113|144->113|145->114|149->118|149->118|150->119|151->120|151->120|152->121|152->121|153->122|153->122|154->123|154->123|155->124|155->124|156->125|157->126|157->126|158->127|158->127|159->128|159->128|159->128|160->129|162->131|162->131|164->133|164->133|165->134|165->134|166->135|166->135|166->135|167->136|170->139|170->139|171->140|172->141|172->141|172->141|172->141|173->142|173->142|174->143|174->143|175->144|175->144|176->145|176->145|177->146|179->148|179->148|180->149|180->149|181->150|181->150|184->153|184->153|185->154|185->154|185->154|186->155|187->156|187->156|188->157|188->157|189->158|190->159|190->159|191->160|191->160|192->161|193->162|193->162|194->163|194->163|195->164|195->164|195->164|196->165|196->165|197->166|199->168|199->168|200->169|202->171|202->171|203->172|203->172|204->173|204->173|205->174|205->174|206->175|208->177|208->177|209->178|210->179|210->179|211->180|211->180|212->181|213->182|213->182|214->183|216->185|216->185|217->186|217->186|218->187|218->187|219->188|219->188|220->189|221->190|221->190|222->191|222->191|223->192|230->199|230->199|231->200|231->200|232->201|232->201|232->201|233->202|234->203|234->203|235->204|235->204|235->204|236->205|237->206|237->206|238->207|238->207|239->208|239->208|240->209|241->210|241->210|242->211|242->211|243->212|243->212|245->214|245->214|245->214|246->215|246->215|247->216|249->218|250->219|250->219|252->221|252->221|253->222|253->222|253->222|254->223|255->224|255->224|256->225|256->225|256->225|257->226|262->231|262->231|263->232|264->233|264->233|265->234|265->234|266->235|266->235|267->236|267->236|268->237|268->237|269->238|270->239|270->239|271->240|271->240|272->241|272->241|272->241|273->242|273->242|274->243|276->245|276->245|277->246|279->248|279->248|280->249|280->249|281->250|281->250|282->251|282->251|283->252|285->254|285->254|286->255|287->256|287->256|288->257|288->257|289->258|290->259|290->259|291->260|293->262|293->262|294->263|294->263|295->264|295->264|296->265|296->265|297->266|306->275|306->275|307->276|307->276|308->277|308->277|308->277|309->278|311->280|311->280|312->281|312->281|313->282|313->282|314->283|314->283|314->283|315->284|316->285|316->285|317->286|319->288|319->288|320->289|321->290|321->290|322->291|322->291|323->292|323->292|324->293|324->293|325->294|325->294|326->295|326->295|328->297|328->297|329->298|329->298|329->298|330->299|331->300|331->300|332->301|332->301|332->301|333->302|334->303|334->303|335->304|336->305|336->305|337->306|341->310|341->310|342->311|343->312|343->312|344->313|344->313|345->314|345->314|346->315|346->315|347->316|347->316|348->317|348->317|348->317|348->317|348->317|348->317|348->317|348->317|349->318|349->318|350->319|350->319|351->320|351->320|351->320|352->321|352->321|353->322|355->324|355->324|356->325|358->327|358->327|359->328|359->328|360->329|360->329|361->330|361->330|362->331|363->332|363->332|364->333|364->333|364->333|364->333|365->334|365->334|366->335|366->335|367->336|368->337|368->337|369->338|371->340|371->340|372->341|372->341|373->342|373->342|374->343|374->343|375->344|384->353|384->353|385->354|385->354|386->355|386->355|386->355|387->356|390->359|390->359|391->360|391->360|392->361|392->361|393->362|393->362|393->362|394->363|395->364|395->364|396->365|398->367|398->367|399->368|400->369|400->369|401->370|401->370|402->371|402->371|403->372|403->372|404->373|404->373|405->374|405->374|407->376|407->376|408->377|408->377|408->377|409->378|410->379|410->379|411->380|411->380|412->381|413->382|413->382|414->383|414->383|415->384|415->384|415->384|416->385|416->385|417->386|419->388|419->388|420->389|422->391|422->391|423->392|423->392|424->393|424->393|425->394|425->394|426->395|428->397|428->397|429->398|430->399|430->399|431->400|431->400|432->401|433->402|433->402|434->403|436->405|436->405|437->406|437->406|438->407|438->407|439->408|439->408|440->409|449->418|449->418|450->419|450->419|451->420|451->420|451->420|452->421|454->423|454->423|455->424|455->424|456->425|456->425|457->426|457->426|457->426|458->427|459->428|459->428|460->429|462->431|462->431|463->432|464->433|464->433|465->434|465->434|466->435|466->435|467->436|467->436|468->437|468->437|469->438|469->438|471->440|471->440|472->441|472->441|472->441|473->442|474->443|474->443|475->444|475->444|475->444|477->446|481->450|481->450|482->451|483->452|483->452|484->453|484->453|485->454|485->454|486->455|486->455|487->456|487->456|488->457|488->457|488->457|488->457|488->457|488->457|488->457|489->458|489->458|490->459|490->459|491->460|491->460|491->460|492->461|492->461|493->462|495->464|495->464|496->465|498->467|498->467|499->468|499->468|500->469|500->469|501->470|501->470|502->471|503->472|503->472|504->473|504->473|504->473|504->473|505->474|505->474|506->475|506->475|507->476|508->477|508->477|509->478|511->480|511->480|512->481|512->481|513->482|513->482|515->484|515->484|516->485|525->494|525->494|526->495|526->495|527->496|527->496|527->496|528->497|529->498|529->498|530->499|530->499|531->500|531->500|532->501|532->501|532->501|533->502|534->503|534->503|535->504|537->506|537->506|538->507|539->508|539->508|540->509|540->509|541->510|541->510|542->511|542->511|543->512|543->512|544->513|544->513|551->520|551->520
                  -- GENERATED --
              */
          