
package viewsCPanel.html

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

object inicioCPanel extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[List[List[String]],List[String],List[String],List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(datos: List[List[String]], graficoOcupacion: List[String], graficoPRvsPL: List[String], ventas: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""    	
"""),format.raw/*3.1*/("""<meta charset="utf-8">

"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""




	"""),format.raw/*10.2*/("""<script src=""""),_display_(/*10.16*/routes/*10.22*/.Assets.versioned("highcharts/highcharts.js")),format.raw/*10.67*/(""""></script>
	<script src=""""),_display_(/*11.16*/routes/*11.22*/.Assets.versioned("highcharts/series-label.js")),format.raw/*11.69*/(""""></script>
	<script src=""""),_display_(/*12.16*/routes/*12.22*/.Assets.versioned("highcharts/exporting.js")),format.raw/*12.66*/(""""></script>
	<script src=""""),_display_(/*13.16*/routes/*13.22*/.Assets.versioned("highcharts/export-data.js")),format.raw/*13.68*/(""""></script>

<br><br>
<div class="row  justify-content-md-center">
	<div class="col-xs-12 col-sm-10 col-md-10 col-lg-10">
	<h1></h1>
	<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
		<thead style="background-color: #eeeeee">
			<TR>
        		<TH colspan="20" style="text-align:center;">PANEL</TH>
			</TR>
        	<TR> 
        		<TH style="text-align:center;">Nro</TH>
		        <TH style="text-align:center;">PAIS</TH>
				<TH style="text-align:center;">Fecha<BR>Creacion</TH>
				<TH style="text-align:center;">Fecha Ultimo Ingreso<br>(de un movimiento)</TH>
				<TH style="text-align:center;">Fecha Ultimo Ingreso<br>(de una guía)</TH>
				<TH style="text-align:center;">Fecha Ultimo Ingreso<br>(de una cotizacion)</TH>
				<TH style="text-align:center;">Fecha de Ingreso<br>(ultimo usuario)</TH>
				<TH style="text-align:center;">Cantidad<br>(Items controlados)</TH>
				<TH style="text-align:center;">Cantidad<br>(Proyectos activos)</TH>
				<TH style="text-align:center;">Cantidad<br>(Proyectos inactivos)</TH>
				<TH style="text-align:center;">SEL</TH>
			</TR>
		</thead>
		<tbody>
			"""),_display_(/*39.5*/for(lista1 <- datos) yield /*39.25*/{_display_(Seq[Any](format.raw/*39.26*/("""
				"""),format.raw/*40.5*/("""<TR>
					<td style="text-align:center;">"""),_display_(/*41.38*/lista1/*41.44*/.get(0)),format.raw/*41.51*/("""</td>
					<td style="text-align:left;">"""),_display_(/*42.36*/lista1/*42.42*/.get(1)),format.raw/*42.49*/("""</td>
					<td style="text-align:center;">"""),_display_(/*43.38*/lista1/*43.44*/.get(2)),format.raw/*43.51*/("""</td>
					<td style="text-align:center;">"""),_display_(/*44.38*/lista1/*44.44*/.get(3)),format.raw/*44.51*/("""</td>
					<td style="text-align:center;">"""),_display_(/*45.38*/lista1/*45.44*/.get(4)),format.raw/*45.51*/("""</td>
					<td style="text-align:center;">"""),_display_(/*46.38*/lista1/*46.44*/.get(5)),format.raw/*46.51*/("""</td>
					<td style="text-align:center;">"""),_display_(/*47.38*/lista1/*47.44*/.get(6)),format.raw/*47.51*/("""</td>
					<td style="text-align:center;"><a href="#" onclick="modalItemsControlados('"""),_display_(/*48.82*/lista1/*48.88*/.get(10)),format.raw/*48.96*/("""');"><font color="blue"><u>"""),_display_(/*48.124*/lista1/*48.130*/.get(7)),format.raw/*48.137*/("""</u></font></a></td>
					<td style="text-align:center;"><a href="#" onclick="modalVigentes('"""),_display_(/*49.74*/lista1/*49.80*/.get(10)),format.raw/*49.88*/("""');"><font color="blue"><u>"""),_display_(/*49.116*/lista1/*49.122*/.get(8)),format.raw/*49.129*/("""</u></font></a></td>
					<td style="text-align:center;"><a href="#" onclick="modalNoVigentes('"""),_display_(/*50.76*/lista1/*50.82*/.get(10)),format.raw/*50.90*/("""');"><font color="blue"><u>"""),_display_(/*50.118*/lista1/*50.124*/.get(9)),format.raw/*50.131*/("""</u></font></a></td>
					<td style="text-align:center;">
						<a href="#" onclick= "$('#id_cPanel').val('"""),_display_(/*52.51*/lista1/*52.57*/.get(10)),format.raw/*52.65*/("""');$('#iniciar').submit()">
							<code><font color="blue" size="2">
							<strong>S</strong></font></code>
						</a>
					</td>
				</TR>
		 	""")))}),format.raw/*58.6*/("""
		"""),format.raw/*59.3*/("""</tbody>
	</table>
	<table id="tablaVentas" class="table table-sm table-hover table-bordered table-condensed table-fluid">
		<thead style="background-color: #eeeeee">
			<TR>
        		<TH colspan="20" style="text-align:center;">VENTAS</TH>
			</TR>
        	<TR> 
        		"""),_display_(/*67.12*/for(tit1 <- ventas.get(0)) yield /*67.38*/{_display_(Seq[Any](format.raw/*67.39*/("""
        			"""),format.raw/*68.12*/("""<TH style="text-align:center;">"""),_display_(/*68.44*/tit1),format.raw/*68.48*/("""</TH>
        		""")))}),format.raw/*69.12*/("""
			"""),format.raw/*70.4*/("""</TR>
		</thead>
		<tbody>
			"""),_display_(/*73.5*/for(lista1 <- ventas.tail) yield /*73.31*/{_display_(Seq[Any](format.raw/*73.32*/("""
				"""),format.raw/*74.5*/("""<TR>
					<td style="text-align:center;">"""),_display_(/*75.38*/lista1/*75.44*/.get(0)),format.raw/*75.51*/("""</td>
					<td style="text-align:center;">"""),_display_(/*76.38*/lista1/*76.44*/.get(1)),format.raw/*76.51*/("""</td>
					"""),_display_(/*77.7*/for(lista2 <- lista1.tail.tail) yield /*77.38*/{_display_(Seq[Any](format.raw/*77.39*/("""
						"""),format.raw/*78.7*/("""<td style="text-align:right;">"""),_display_(/*78.38*/lista2),format.raw/*78.44*/("""</td>
					""")))}),format.raw/*79.7*/("""
				"""),format.raw/*80.5*/("""</TR>
		 	""")))}),format.raw/*81.6*/("""
		"""),format.raw/*82.3*/("""</tbody>
	</table>
	
	
			<table class="table table-sm table-hover table-bordered table-condensed table-fluid">
				<TR>
			  		<TD id="ocupacion" style="height: auto;"></TD>
				</TR>
			 </table>
			 
			<table class="table table-sm table-hover table-bordered table-condensed table-fluid">
				<TR>
			  		<TD id="PRvsPL" style="height: auto;"></TD>
				</TR>
			 </table>
			 
			  
			  
			  
</div>
</div>

<form id="iniciar" action="/inicioCPanel2/" method="POST">
	<input type="hidden" id="id_cPanel" name="id_cPanel">
</form>

<div id="vigentes" class="modal" role='dialog' data-backdrop='static' data-keyboard='false'>
	<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
   				<div id="modalVigentes"></div>
			</div>
	</div>
</div>

<div id="noVigentes" class="modal" role='dialog' data-backdrop='static' data-keyboard='false'>
	<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
   				<div id="modalNoVigentes"></div>
			</div>
	</div>
</div>

<div id="itemsControlados" class="modal" role='dialog' data-backdrop='static' data-keyboard='false'>
	<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
   				<div id="modalItemsControlados"></div>
			</div>
	</div>
</div>



""")))}),format.raw/*134.2*/("""

	"""),format.raw/*136.2*/("""<script>
		function modalVigentes(id_cPanel)"""),format.raw/*137.36*/("""{"""),format.raw/*137.37*/("""
			"""),format.raw/*138.4*/("""var formData = new FormData();
	    	formData.append('id_cPanel',id_cPanel);
            $.ajax("""),format.raw/*140.20*/("""{"""),format.raw/*140.21*/("""
                """),format.raw/*141.17*/("""url: "/cPanelModalVigentes/",
                type: "POST",
                method: "POST",
                data: formData,
                cache: false,
                contentType: false,
		     	processData: false,
		     	success: function(respuesta)"""),format.raw/*148.37*/("""{"""),format.raw/*148.38*/("""
		     		"""),format.raw/*149.10*/("""document.getElementById('modalVigentes').innerHTML = respuesta;

				$('#tableVigentes').DataTable("""),format.raw/*151.35*/("""{"""),format.raw/*151.36*/("""
						"""),format.raw/*152.7*/(""""fixedHeader": true,
				    	"paging": false,
						"info": false,
						"searching": false,
						"scrollY": 550,
						"scrollX": true,
				    	"language": """),format.raw/*158.22*/("""{"""),format.raw/*158.23*/("""
				        	"""),format.raw/*159.14*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
				        """),format.raw/*160.13*/("""}"""),format.raw/*160.14*/("""
				  """),format.raw/*161.7*/("""}"""),format.raw/*161.8*/(""" """),format.raw/*161.9*/(""");
		
		     		$('#vigentes').modal('show');
		     	"""),format.raw/*164.9*/("""}"""),format.raw/*164.10*/("""
	        """),format.raw/*165.10*/("""}"""),format.raw/*165.11*/(""");   
		"""),format.raw/*166.3*/("""}"""),format.raw/*166.4*/(""" 
		
		"""),format.raw/*168.3*/("""function modalNoVigentes(id_cPanel)"""),format.raw/*168.38*/("""{"""),format.raw/*168.39*/("""
			"""),format.raw/*169.4*/("""var formData = new FormData();
	    	formData.append('id_cPanel',id_cPanel);
            $.ajax("""),format.raw/*171.20*/("""{"""),format.raw/*171.21*/("""
                """),format.raw/*172.17*/("""url: "/cPanelModalNoVigentes/",
                type: "POST",
                method: "POST",
                data: formData,
                cache: false,
                contentType: false,
		     	processData: false,
		     	success: function(respuesta)"""),format.raw/*179.37*/("""{"""),format.raw/*179.38*/("""
		     		"""),format.raw/*180.10*/("""document.getElementById('modalNoVigentes').innerHTML = respuesta;

				$('#tableNoVigentes').DataTable("""),format.raw/*182.37*/("""{"""),format.raw/*182.38*/("""
						"""),format.raw/*183.7*/(""""fixedHeader": true,
				    	"paging": false,
						"info": false,
						"searching": false,
						"scrollY": 550,
						"scrollX": true,
				    	"language": """),format.raw/*189.22*/("""{"""),format.raw/*189.23*/("""
				        	"""),format.raw/*190.14*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
				        """),format.raw/*191.13*/("""}"""),format.raw/*191.14*/("""
				  """),format.raw/*192.7*/("""}"""),format.raw/*192.8*/(""" """),format.raw/*192.9*/(""");
		
		     		$('#noVigentes').modal('show');
		     	"""),format.raw/*195.9*/("""}"""),format.raw/*195.10*/("""
	        """),format.raw/*196.10*/("""}"""),format.raw/*196.11*/(""");   
		"""),format.raw/*197.3*/("""}"""),format.raw/*197.4*/(""" 
		
		"""),format.raw/*199.3*/("""function modalItemsControlados(id_cPanel)"""),format.raw/*199.44*/("""{"""),format.raw/*199.45*/("""
			"""),format.raw/*200.4*/("""var formData = new FormData();
	    	formData.append('id_cPanel',id_cPanel);
            $.ajax("""),format.raw/*202.20*/("""{"""),format.raw/*202.21*/("""
                """),format.raw/*203.17*/("""url: "/cPanelModalItemsControlados/",
                type: "POST",
                method: "POST",
                data: formData,
                cache: false,
                contentType: false,
		     	processData: false,
		     	success: function(respuesta)"""),format.raw/*210.37*/("""{"""),format.raw/*210.38*/("""
		     		"""),format.raw/*211.10*/("""document.getElementById('modalItemsControlados').innerHTML = respuesta;

				$('#tableItemsControlados').DataTable("""),format.raw/*213.43*/("""{"""),format.raw/*213.44*/("""
						"""),format.raw/*214.7*/(""""fixedHeader": true,
				    	"paging": false,
						"info": false,
						"searching": false,
						"scrollY": 550,
						"scrollX": true,
				    	"language": """),format.raw/*220.22*/("""{"""),format.raw/*220.23*/("""
				        	"""),format.raw/*221.14*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
				        """),format.raw/*222.13*/("""}"""),format.raw/*222.14*/("""
				  """),format.raw/*223.7*/("""}"""),format.raw/*223.8*/(""" """),format.raw/*223.9*/(""");



		     		$('#itemsControlados').modal('show');
		     	"""),format.raw/*228.9*/("""}"""),format.raw/*228.10*/("""
	        """),format.raw/*229.10*/("""}"""),format.raw/*229.11*/(""");   
		"""),format.raw/*230.3*/("""}"""),format.raw/*230.4*/(""" 
		
	"""),format.raw/*232.2*/("""</script>



		<script>
			$(function () """),format.raw/*237.18*/("""{"""),format.raw/*237.19*/("""
			        """),format.raw/*238.12*/("""$('#ocupacion').highcharts("""),format.raw/*238.39*/("""{"""),format.raw/*238.40*/("""
			        	"""),format.raw/*239.13*/("""chart: """),format.raw/*239.20*/("""{"""),format.raw/*239.21*/("""
			                """),format.raw/*240.20*/("""zoomType: 'xy'
			            """),format.raw/*241.16*/("""}"""),format.raw/*241.17*/(""",
			            title: """),format.raw/*242.23*/("""{"""),format.raw/*242.24*/("""
			                """),format.raw/*243.20*/("""text: '% OCUPACIÓN MENSUAL (Sobre Precio de Compra)'
			            """),format.raw/*244.16*/("""}"""),format.raw/*244.17*/(""",
			            subtitle: """),format.raw/*245.26*/("""{"""),format.raw/*245.27*/("""
			                """),format.raw/*246.20*/("""text: ''
			            """),format.raw/*247.16*/("""}"""),format.raw/*247.17*/(""",
			            xAxis: ["""),format.raw/*248.24*/("""{"""),format.raw/*248.25*/("""
			            	"""),format.raw/*249.17*/("""categories: """),_display_(/*249.30*/Html(graficoOcupacion.get(0))),format.raw/*249.59*/(""",
			                labels: """),format.raw/*250.28*/("""{"""),format.raw/*250.29*/("""
			                    """),format.raw/*251.24*/("""rotation: -45,
			                    align: 'right',
			                    style: """),format.raw/*253.31*/("""{"""),format.raw/*253.32*/("""
			                        """),format.raw/*254.28*/("""fontSize: '10px',
			                        fontFamily: 'Verdana, sans-serif'
			                    """),format.raw/*256.24*/("""}"""),format.raw/*256.25*/("""
			                """),format.raw/*257.20*/("""}"""),format.raw/*257.21*/("""
			            """),format.raw/*258.16*/("""}"""),format.raw/*258.17*/("""],
			            yAxis: """),format.raw/*259.23*/("""{"""),format.raw/*259.24*/("""
			                """),format.raw/*260.20*/("""min: 0,
			                max: 100,
			                title: """),format.raw/*262.27*/("""{"""),format.raw/*262.28*/("""
			                    """),format.raw/*263.24*/("""text: 'Porcentaje'
			                """),format.raw/*264.20*/("""}"""),format.raw/*264.21*/(""",
			                stackLabels: """),format.raw/*265.33*/("""{"""),format.raw/*265.34*/("""
			                    """),format.raw/*266.24*/("""enabled: true,
			                    style: """),format.raw/*267.31*/("""{"""),format.raw/*267.32*/("""
			                        """),format.raw/*268.28*/("""fontWeight: 'bold',
			                        color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
			                    """),format.raw/*270.24*/("""}"""),format.raw/*270.25*/("""
			                """),format.raw/*271.20*/("""}"""),format.raw/*271.21*/("""
			            """),format.raw/*272.16*/("""}"""),format.raw/*272.17*/(""",
			            tooltip: """),format.raw/*273.25*/("""{"""),format.raw/*273.26*/("""
			                """),format.raw/*274.20*/("""shared: true
			            """),format.raw/*275.16*/("""}"""),format.raw/*275.17*/(""",
			            legend: """),format.raw/*276.24*/("""{"""),format.raw/*276.25*/("""
			                """),format.raw/*277.20*/("""layout: 'horizontal',
			                align: 'center',
			                verticalAlign: 'top',
			                y: 30,
			                floating: false,
			                borderWidth: 0,
			                backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'
			            """),format.raw/*284.16*/("""}"""),format.raw/*284.17*/(""",
			            plotOptions: """),format.raw/*285.29*/("""{"""),format.raw/*285.30*/("""
			                """),format.raw/*286.20*/("""spline: """),format.raw/*286.28*/("""{"""),format.raw/*286.29*/("""
			                    """),format.raw/*287.24*/("""lineWidth: 1,
			                    states: """),format.raw/*288.32*/("""{"""),format.raw/*288.33*/("""
			                        """),format.raw/*289.28*/("""hover: """),format.raw/*289.35*/("""{"""),format.raw/*289.36*/("""
			                            """),format.raw/*290.32*/("""lineWidth: 6
			                        """),format.raw/*291.28*/("""}"""),format.raw/*291.29*/("""
			                    """),format.raw/*292.24*/("""}"""),format.raw/*292.25*/(""",
			                    marker: """),format.raw/*293.32*/("""{"""),format.raw/*293.33*/("""
			                        """),format.raw/*294.28*/("""enabled: false
			                    """),format.raw/*295.24*/("""}"""),format.raw/*295.25*/("""
			                """),format.raw/*296.20*/("""}"""),format.raw/*296.21*/("""
			            """),format.raw/*297.16*/("""}"""),format.raw/*297.17*/(""",
			            series: [
			              
			               """),_display_(/*300.20*/for(lista <- graficoOcupacion.tail) yield /*300.55*/{_display_(Seq[Any](format.raw/*300.56*/("""
			            	   """),_display_(/*301.21*/Html(lista)),format.raw/*301.32*/("""
			               """)))}),format.raw/*302.20*/("""

			            """),format.raw/*304.16*/("""]
			        """),format.raw/*305.12*/("""}"""),format.raw/*305.13*/(""");
			    """),format.raw/*306.8*/("""}"""),format.raw/*306.9*/(""");
			</script>
			
			<script>
			$(function () """),format.raw/*310.18*/("""{"""),format.raw/*310.19*/("""
			        """),format.raw/*311.12*/("""$('#PRvsPL').highcharts("""),format.raw/*311.36*/("""{"""),format.raw/*311.37*/("""
			        	"""),format.raw/*312.13*/("""chart: """),format.raw/*312.20*/("""{"""),format.raw/*312.21*/("""
			                """),format.raw/*313.20*/("""zoomType: 'xy'
			            """),format.raw/*314.16*/("""}"""),format.raw/*314.17*/(""",
			            title: """),format.raw/*315.23*/("""{"""),format.raw/*315.24*/("""
			                """),format.raw/*316.20*/("""text: 'Compara Alquiler entre Precio Real y Precio Lista (Factor = PR/PL x 100)'
			            """),format.raw/*317.16*/("""}"""),format.raw/*317.17*/(""",
			            subtitle: """),format.raw/*318.26*/("""{"""),format.raw/*318.27*/("""
			                """),format.raw/*319.20*/("""text: ''
			            """),format.raw/*320.16*/("""}"""),format.raw/*320.17*/(""",
			            xAxis: ["""),format.raw/*321.24*/("""{"""),format.raw/*321.25*/("""
			            	"""),format.raw/*322.17*/("""categories: """),_display_(/*322.30*/Html(graficoPRvsPL.get(0))),format.raw/*322.56*/(""",
			                labels: """),format.raw/*323.28*/("""{"""),format.raw/*323.29*/("""
			                    """),format.raw/*324.24*/("""rotation: -45,
			                    align: 'right',
			                    style: """),format.raw/*326.31*/("""{"""),format.raw/*326.32*/("""
			                        """),format.raw/*327.28*/("""fontSize: '10px',
			                        fontFamily: 'Verdana, sans-serif'
			                    """),format.raw/*329.24*/("""}"""),format.raw/*329.25*/("""
			                """),format.raw/*330.20*/("""}"""),format.raw/*330.21*/("""
			            """),format.raw/*331.16*/("""}"""),format.raw/*331.17*/("""],
			            yAxis: """),format.raw/*332.23*/("""{"""),format.raw/*332.24*/("""
			                """),format.raw/*333.20*/("""min: 0,
			                max: 2,
			                title: """),format.raw/*335.27*/("""{"""),format.raw/*335.28*/("""
			                    """),format.raw/*336.24*/("""text: 'Factor'
			                """),format.raw/*337.20*/("""}"""),format.raw/*337.21*/(""",
			                stackLabels: """),format.raw/*338.33*/("""{"""),format.raw/*338.34*/("""
			                    """),format.raw/*339.24*/("""enabled: true,
			                    style: """),format.raw/*340.31*/("""{"""),format.raw/*340.32*/("""
			                        """),format.raw/*341.28*/("""fontWeight: 'bold',
			                        color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
			                    """),format.raw/*343.24*/("""}"""),format.raw/*343.25*/("""
			                """),format.raw/*344.20*/("""}"""),format.raw/*344.21*/("""
			            """),format.raw/*345.16*/("""}"""),format.raw/*345.17*/(""",
			            tooltip: """),format.raw/*346.25*/("""{"""),format.raw/*346.26*/("""
			                """),format.raw/*347.20*/("""shared: true
			            """),format.raw/*348.16*/("""}"""),format.raw/*348.17*/(""",
			            legend: """),format.raw/*349.24*/("""{"""),format.raw/*349.25*/("""
			                """),format.raw/*350.20*/("""layout: 'horizontal',
			                align: 'center',
			                verticalAlign: 'top',
			                y: 30,
			                floating: false,
			                borderWidth: 0,
			                backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'
			            """),format.raw/*357.16*/("""}"""),format.raw/*357.17*/(""",
			            plotOptions: """),format.raw/*358.29*/("""{"""),format.raw/*358.30*/("""
			                """),format.raw/*359.20*/("""spline: """),format.raw/*359.28*/("""{"""),format.raw/*359.29*/("""
			                    """),format.raw/*360.24*/("""lineWidth: 1,
			                    states: """),format.raw/*361.32*/("""{"""),format.raw/*361.33*/("""
			                        """),format.raw/*362.28*/("""hover: """),format.raw/*362.35*/("""{"""),format.raw/*362.36*/("""
			                            """),format.raw/*363.32*/("""lineWidth: 6
			                        """),format.raw/*364.28*/("""}"""),format.raw/*364.29*/("""
			                    """),format.raw/*365.24*/("""}"""),format.raw/*365.25*/(""",
			                    marker: """),format.raw/*366.32*/("""{"""),format.raw/*366.33*/("""
			                        """),format.raw/*367.28*/("""enabled: false
			                    """),format.raw/*368.24*/("""}"""),format.raw/*368.25*/("""
			                """),format.raw/*369.20*/("""}"""),format.raw/*369.21*/("""
			            """),format.raw/*370.16*/("""}"""),format.raw/*370.17*/(""",
			            series: [
			              
			               """),_display_(/*373.20*/for(lista <- graficoPRvsPL.tail) yield /*373.52*/{_display_(Seq[Any](format.raw/*373.53*/("""
			            	   """),_display_(/*374.21*/Html(lista)),format.raw/*374.32*/("""
			               """)))}),format.raw/*375.20*/("""

			            """),format.raw/*377.16*/("""]
			        """),format.raw/*378.12*/("""}"""),format.raw/*378.13*/(""");
			    """),format.raw/*379.8*/("""}"""),format.raw/*379.9*/(""");
			</script>
			
			

"""))
      }
    }
  }

  def render(datos:List[List[String]],graficoOcupacion:List[String],graficoPRvsPL:List[String],ventas:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(datos,graficoOcupacion,graficoPRvsPL,ventas)

  def f:((List[List[String]],List[String],List[String],List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (datos,graficoOcupacion,graficoPRvsPL,ventas) => apply(datos,graficoOcupacion,graficoPRvsPL,ventas)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsCPanel/inicioCPanel.scala.html
                  HASH: 0daa199318ced0badf5f31ae9af018297038df6b
                  MATRIX: 1015->1|1225->118|1257->124|1307->149|1323->157|1362->159|1395->165|1436->179|1451->185|1517->230|1571->257|1586->263|1654->310|1708->337|1723->343|1788->387|1842->414|1857->420|1924->466|3108->1624|3144->1644|3183->1645|3215->1650|3284->1692|3299->1698|3327->1705|3395->1746|3410->1752|3438->1759|3508->1802|3523->1808|3551->1815|3621->1858|3636->1864|3664->1871|3734->1914|3749->1920|3777->1927|3847->1970|3862->1976|3890->1983|3960->2026|3975->2032|4003->2039|4117->2126|4132->2132|4161->2140|4217->2168|4233->2174|4262->2181|4383->2275|4398->2281|4427->2289|4483->2317|4499->2323|4528->2330|4651->2426|4666->2432|4695->2440|4751->2468|4767->2474|4796->2481|4931->2589|4946->2595|4975->2603|5152->2750|5182->2753|5485->3029|5527->3055|5566->3056|5606->3068|5665->3100|5690->3104|5738->3121|5769->3125|5826->3156|5868->3182|5907->3183|5939->3188|6008->3230|6023->3236|6051->3243|6121->3286|6136->3292|6164->3299|6202->3311|6249->3342|6288->3343|6322->3350|6380->3381|6407->3387|6449->3399|6481->3404|6522->3415|6552->3418|7937->4772|7968->4775|8041->4819|8071->4820|8103->4824|8228->4920|8258->4921|8304->4938|8587->5192|8617->5193|8656->5203|8784->5302|8814->5303|8849->5310|9038->5470|9068->5471|9111->5485|9220->5565|9250->5566|9285->5573|9314->5574|9343->5575|9424->5628|9454->5629|9493->5639|9523->5640|9559->5648|9588->5649|9623->5656|9687->5691|9717->5692|9749->5696|9874->5792|9904->5793|9950->5810|10235->6066|10265->6067|10304->6077|10436->6180|10466->6181|10501->6188|10690->6348|10720->6349|10763->6363|10872->6443|10902->6444|10937->6451|10966->6452|10995->6453|11078->6508|11108->6509|11147->6519|11177->6520|11213->6528|11242->6529|11277->6536|11347->6577|11377->6578|11409->6582|11534->6678|11564->6679|11610->6696|11901->6958|11931->6959|11970->6969|12114->7084|12144->7085|12179->7092|12368->7252|12398->7253|12441->7267|12550->7347|12580->7348|12615->7355|12644->7356|12673->7357|12762->7418|12792->7419|12831->7429|12861->7430|12897->7438|12926->7439|12960->7445|13030->7486|13060->7487|13101->7499|13157->7526|13187->7527|13229->7540|13265->7547|13295->7548|13344->7568|13403->7598|13433->7599|13486->7623|13516->7624|13565->7644|13662->7712|13692->7713|13748->7740|13778->7741|13827->7761|13880->7785|13910->7786|13964->7811|13994->7812|14040->7829|14081->7842|14132->7871|14190->7900|14220->7901|14273->7925|14386->8009|14416->8010|14473->8038|14604->8140|14634->8141|14683->8161|14713->8162|14758->8178|14788->8179|14842->8204|14872->8205|14921->8225|15013->8288|15043->8289|15096->8313|15163->8351|15193->8352|15256->8386|15286->8387|15339->8411|15413->8456|15443->8457|15500->8485|15665->8621|15695->8622|15744->8642|15774->8643|15819->8659|15849->8660|15904->8686|15934->8687|15983->8707|16040->8735|16070->8736|16124->8761|16154->8762|16203->8782|16553->9103|16583->9104|16642->9134|16672->9135|16721->9155|16758->9163|16788->9164|16841->9188|16915->9233|16945->9234|17002->9262|17038->9269|17068->9270|17129->9302|17198->9342|17228->9343|17281->9367|17311->9368|17373->9401|17403->9402|17460->9430|17527->9468|17557->9469|17606->9489|17636->9490|17681->9506|17711->9507|17803->9571|17855->9606|17895->9607|17944->9628|17977->9639|18029->9659|18075->9676|18117->9689|18147->9690|18185->9700|18214->9701|18292->9750|18322->9751|18363->9763|18416->9787|18446->9788|18488->9801|18524->9808|18554->9809|18603->9829|18662->9859|18692->9860|18745->9884|18775->9885|18824->9905|18949->10001|18979->10002|19035->10029|19065->10030|19114->10050|19167->10074|19197->10075|19251->10100|19281->10101|19327->10118|19368->10131|19416->10157|19474->10186|19504->10187|19557->10211|19670->10295|19700->10296|19757->10324|19888->10426|19918->10427|19967->10447|19997->10448|20042->10464|20072->10465|20126->10490|20156->10491|20205->10511|20295->10572|20325->10573|20378->10597|20441->10631|20471->10632|20534->10666|20564->10667|20617->10691|20691->10736|20721->10737|20778->10765|20943->10901|20973->10902|21022->10922|21052->10923|21097->10939|21127->10940|21182->10966|21212->10967|21261->10987|21318->11015|21348->11016|21402->11041|21432->11042|21481->11062|21831->11383|21861->11384|21920->11414|21950->11415|21999->11435|22036->11443|22066->11444|22119->11468|22193->11513|22223->11514|22280->11542|22316->11549|22346->11550|22407->11582|22476->11622|22506->11623|22559->11647|22589->11648|22651->11681|22681->11682|22738->11710|22805->11748|22835->11749|22884->11769|22914->11770|22959->11786|22989->11787|23081->11851|23130->11883|23170->11884|23219->11905|23252->11916|23304->11936|23350->11953|23392->11966|23422->11967|23460->11977|23489->11978
                  LINES: 28->1|33->2|34->3|36->5|36->5|36->5|41->10|41->10|41->10|41->10|42->11|42->11|42->11|43->12|43->12|43->12|44->13|44->13|44->13|70->39|70->39|70->39|71->40|72->41|72->41|72->41|73->42|73->42|73->42|74->43|74->43|74->43|75->44|75->44|75->44|76->45|76->45|76->45|77->46|77->46|77->46|78->47|78->47|78->47|79->48|79->48|79->48|79->48|79->48|79->48|80->49|80->49|80->49|80->49|80->49|80->49|81->50|81->50|81->50|81->50|81->50|81->50|83->52|83->52|83->52|89->58|90->59|98->67|98->67|98->67|99->68|99->68|99->68|100->69|101->70|104->73|104->73|104->73|105->74|106->75|106->75|106->75|107->76|107->76|107->76|108->77|108->77|108->77|109->78|109->78|109->78|110->79|111->80|112->81|113->82|165->134|167->136|168->137|168->137|169->138|171->140|171->140|172->141|179->148|179->148|180->149|182->151|182->151|183->152|189->158|189->158|190->159|191->160|191->160|192->161|192->161|192->161|195->164|195->164|196->165|196->165|197->166|197->166|199->168|199->168|199->168|200->169|202->171|202->171|203->172|210->179|210->179|211->180|213->182|213->182|214->183|220->189|220->189|221->190|222->191|222->191|223->192|223->192|223->192|226->195|226->195|227->196|227->196|228->197|228->197|230->199|230->199|230->199|231->200|233->202|233->202|234->203|241->210|241->210|242->211|244->213|244->213|245->214|251->220|251->220|252->221|253->222|253->222|254->223|254->223|254->223|259->228|259->228|260->229|260->229|261->230|261->230|263->232|268->237|268->237|269->238|269->238|269->238|270->239|270->239|270->239|271->240|272->241|272->241|273->242|273->242|274->243|275->244|275->244|276->245|276->245|277->246|278->247|278->247|279->248|279->248|280->249|280->249|280->249|281->250|281->250|282->251|284->253|284->253|285->254|287->256|287->256|288->257|288->257|289->258|289->258|290->259|290->259|291->260|293->262|293->262|294->263|295->264|295->264|296->265|296->265|297->266|298->267|298->267|299->268|301->270|301->270|302->271|302->271|303->272|303->272|304->273|304->273|305->274|306->275|306->275|307->276|307->276|308->277|315->284|315->284|316->285|316->285|317->286|317->286|317->286|318->287|319->288|319->288|320->289|320->289|320->289|321->290|322->291|322->291|323->292|323->292|324->293|324->293|325->294|326->295|326->295|327->296|327->296|328->297|328->297|331->300|331->300|331->300|332->301|332->301|333->302|335->304|336->305|336->305|337->306|337->306|341->310|341->310|342->311|342->311|342->311|343->312|343->312|343->312|344->313|345->314|345->314|346->315|346->315|347->316|348->317|348->317|349->318|349->318|350->319|351->320|351->320|352->321|352->321|353->322|353->322|353->322|354->323|354->323|355->324|357->326|357->326|358->327|360->329|360->329|361->330|361->330|362->331|362->331|363->332|363->332|364->333|366->335|366->335|367->336|368->337|368->337|369->338|369->338|370->339|371->340|371->340|372->341|374->343|374->343|375->344|375->344|376->345|376->345|377->346|377->346|378->347|379->348|379->348|380->349|380->349|381->350|388->357|388->357|389->358|389->358|390->359|390->359|390->359|391->360|392->361|392->361|393->362|393->362|393->362|394->363|395->364|395->364|396->365|396->365|397->366|397->366|398->367|399->368|399->368|400->369|400->369|401->370|401->370|404->373|404->373|404->373|405->374|405->374|406->375|408->377|409->378|409->378|410->379|410->379
                  -- GENERATED --
              */
          