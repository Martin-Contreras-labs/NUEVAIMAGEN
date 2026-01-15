
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

object reportFactConsolconEquiposRtp extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template14[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String,List[String],List[String],Map[String,List[String]],Map[String, List[String]],List[String],Map[String,List[String]],Map[String, List[String]],Map[String,String],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
datos: List[List[String]], fecha: String, cantMeses: String,
categorias: List[String], 
listSeleccion: List[String], mapEquipo: Map[String,List[String]], mapDatos: Map[String, List[String]],
listSeleccion2: List[String], mapEmpresa: Map[String,List[String]], mapDatosEquipo: Map[String, List[String]], mapNomEquip: Map[String,String]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*6.1*/("""    	

"""),_display_(/*8.2*/main("")/*8.10*/ {_display_(Seq[Any](format.raw/*8.12*/("""
	
	"""),format.raw/*10.2*/("""<script src=""""),_display_(/*10.16*/routes/*10.22*/.Assets.versioned("highcharts/highcharts.js")),format.raw/*10.67*/(""""></script>
	<script src=""""),_display_(/*11.16*/routes/*11.22*/.Assets.versioned("highcharts/series-label.js")),format.raw/*11.69*/(""""></script>
	<script src=""""),_display_(/*12.16*/routes/*12.22*/.Assets.versioned("highcharts/exporting.js")),format.raw/*12.66*/(""""></script>
	<script src=""""),_display_(/*13.16*/routes/*13.22*/.Assets.versioned("highcharts/export-data.js")),format.raw/*13.68*/(""""></script>

	"""),_display_(/*15.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*15.51*/("""
	
	"""),format.raw/*17.2*/("""<div id="enCarga" class="blocker" style="display: block;"><br><br><br><br><br><br><h1>Ahora se esta cargando.....</h1></div>
	
	<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*20.4*/barraTitulo(mapDiccionario, "CONSOLIDADO DE PROFORMAS POR EQUIPOS (NO CONSIDERA AJUSTES A EP)","POR "+cantMeses+" MESES VS " + mapDiccionario("BODEGA")+"/PROYECTO")),format.raw/*20.168*/("""

		"""),format.raw/*22.3*/("""<div class="noprint">
			<table class="table table-sm table-condensed table-fluid">
				<tr>
					<td width="25%">
						<div class="input-group">
					  		<div class="input-group-prepend">
					    		<span class="input-group-text" id="basic-addon1">BUSCAR</span>
					  		</div>
					  		<input type="text" class="form-control left"
								id="searchTermtablaPrincipal"
								onkeyup="doSearch3_conTot('tablaPrincipal'); sumarFiltro('tablaPrincipal')">
						</div>
					</td>
					<td>
						<div align="center">
							<button type="button"  class="btn btn-sm btn-success" onclick="document.getElementById('excel').submit()">
								Exportar a Excel
							</button>
							<button type="button" class="btn btn-sm btn-success" tabindex="-1" onclick="window.print(); return false;" >
								Imprimir
							</button>
							
							<button type="button"  class="btn btn-sm btn-success" 
								onclick="history.go(-1);return false;">
								Volver
							</button>
						</div>
					</td>
				</tr>
			</table>
		</div>
		
		<form id="excel" class="formulario" method="post" action="/reportFactConsolconEquiposRtpExcel/">
			<input type="hidden" name="fecha" value=""""),_display_(/*55.46*/fecha),format.raw/*55.51*/("""">
			<input type="hidden" name="cantMeses" value=""""),_display_(/*56.50*/cantMeses),format.raw/*56.59*/("""">
		</form>

		<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
	  		<thead style="background-color: #eeeeee" class="header">
	  			<TR>
					"""),_display_(/*62.7*/for(titulos <- datos.get(0)) yield /*62.35*/{_display_(Seq[Any](format.raw/*62.36*/("""
						"""),format.raw/*63.7*/("""<th style="text-align:center;">"""),_display_(/*63.39*/titulos),format.raw/*63.46*/("""</th>
					""")))}),format.raw/*64.7*/("""
				"""),format.raw/*65.5*/("""</TR>
				<TR>
					"""),_display_(/*67.7*/for(titulos2 <- datos.get(1)) yield /*67.36*/{_display_(Seq[Any](format.raw/*67.37*/("""
						"""),format.raw/*68.7*/("""<th style="text-align:center;">"""),_display_(/*68.39*/titulos2),format.raw/*68.47*/("""</th>
					""")))}),format.raw/*69.7*/("""
				"""),format.raw/*70.5*/("""</TR>
	  		</thead>
			<tbody>
			"""),_display_(/*73.5*/for(lista <- datos.tail.tail) yield /*73.34*/{_display_(Seq[Any](format.raw/*73.35*/("""
				
				
				"""),format.raw/*76.5*/("""<TR>
					"""),_display_(if(lista.get(0).equals("TOTALES"))/*77.41*/{_display_(Seq[Any](format.raw/*77.42*/("""
						"""),format.raw/*78.7*/("""<td style="text-align:left;"><div style="display:none">ZZZZZZZZZZZZ</div>"""),_display_(/*78.81*/lista/*78.86*/.get(0)),format.raw/*78.93*/("""</td>
						<td style="text-align:left;"><div style="display:none">ZZZZZZZZZZZZ</div>"""),_display_(/*79.81*/lista/*79.86*/.get(1)),format.raw/*79.93*/("""</td>
						<td style="text-align:left;"><div style="display:none">ZZZZZZZZZZZZ</div>"""),_display_(/*80.81*/lista/*80.86*/.get(2)),format.raw/*80.93*/("""</td>
						<td style="text-align:left;"><div style="display:none">ZZZZZZZZZZZZ</div>"""),_display_(/*81.81*/lista/*81.86*/.get(3)),format.raw/*81.93*/("""</td>
						<td style="text-align:left;"><div style="display:none">ZZZZZZZZZZZZ</div>"""),_display_(/*82.81*/lista/*82.86*/.get(4)),format.raw/*82.93*/("""</td>
						"""),_display_(/*83.8*/for(lista2 <- lista.tail.tail.tail.tail.tail) yield /*83.53*/{_display_(Seq[Any](format.raw/*83.54*/("""
							"""),format.raw/*84.8*/("""<td style="text-align:right;"><div style="display:none">ZZZZZZZZZZZZ</div>"""),_display_(/*84.83*/lista2),format.raw/*84.89*/("""</td>
						""")))}),format.raw/*85.8*/("""
					""")))}else/*86.11*/{_display_(Seq[Any](format.raw/*86.12*/("""
						"""),format.raw/*87.7*/("""<td style="text-align:left;">"""),_display_(/*87.37*/lista/*87.42*/.get(0)),format.raw/*87.49*/("""</td>
						<td style="text-align:left;">"""),_display_(/*88.37*/lista/*88.42*/.get(1)),format.raw/*88.49*/("""</td>
						<td style="text-align:left;">"""),_display_(/*89.37*/lista/*89.42*/.get(2)),format.raw/*89.49*/("""</td>
						<td style="text-align:left;">"""),_display_(/*90.37*/lista/*90.42*/.get(3)),format.raw/*90.49*/("""</td>
						<td style="text-align:left;">"""),_display_(/*91.37*/lista/*91.42*/.get(4)),format.raw/*91.49*/("""</td>
						"""),_display_(/*92.8*/for(lista2 <- lista.tail.tail.tail.tail.tail) yield /*92.53*/{_display_(Seq[Any](format.raw/*92.54*/("""
							"""),format.raw/*93.8*/("""<td style="text-align:right;">"""),_display_(/*93.39*/lista2),format.raw/*93.45*/("""</td>
						""")))}),format.raw/*94.8*/("""
					""")))}),format.raw/*95.7*/("""
					
					
				"""),format.raw/*98.5*/("""</TR>
				
				
				
				
			""")))}),format.raw/*103.5*/("""
			"""),format.raw/*104.4*/("""</tbody>
		</table>
		
		
		
		
		
		
		
		
		
		
		<div class="noprint">
			<form>
				<div align="left">
					Graficar por EMPRESA:
					<select class="input-large" id="selectGrafico" name="cod_region" style="text-align:left;height:24px" onChange="graficar()">
						<option value="-1">seleccionar</option>
						"""),_display_(/*122.8*/for((lista,index) <- listSeleccion.zipWithIndex) yield /*122.56*/{_display_(Seq[Any](format.raw/*122.57*/("""
					     	"""),format.raw/*123.12*/("""<option value=""""),_display_(/*123.28*/index),format.raw/*123.33*/("""">"""),_display_(/*123.36*/lista),format.raw/*123.41*/("""</option>
						""")))}),format.raw/*124.8*/("""
					"""),format.raw/*125.6*/("""</select>
				</div>
			</form>
		</div>
			
		<table class="table table-sm table-bordered table-condensed table-fluid">
			<tr>
				<TD style="width:100%; margin: 0 0">
					"""),_display_(/*133.7*/for((lista,index) <-listSeleccion.zipWithIndex) yield /*133.54*/{_display_(Seq[Any](format.raw/*133.55*/("""
						"""),format.raw/*134.7*/("""<div class="graficos" id="grafico_"""),_display_(/*134.42*/index),format.raw/*134.47*/("""" style="height:600px; width:100%; display: none;" >"""),_display_(/*134.100*/Html(lista)),format.raw/*134.111*/("""</div>
					""")))}),format.raw/*135.7*/("""
				"""),format.raw/*136.5*/("""</TD>
			</tr>
		 </table>
		 
		 <div class="noprint">
			<form>
				<div align="left">
					Graficar por EQUIPO:
					<select class="input-large" id="selectGraficoPorEquipo" name="cod_region" style="text-align:left;height:24px" onChange="graficarPorEquipo()">
						<option value="-1">seleccionar</option>
						"""),_display_(/*146.8*/for((lista,index) <- listSeleccion2.zipWithIndex) yield /*146.57*/{_display_(Seq[Any](format.raw/*146.58*/("""
					     	"""),format.raw/*147.12*/("""<option value=""""),_display_(/*147.28*/index),format.raw/*147.33*/("""">"""),_display_(/*147.36*/lista),format.raw/*147.41*/("""</option>
						""")))}),format.raw/*148.8*/("""
					"""),format.raw/*149.6*/("""</select>
				</div>
			</form>
		</div>
		
		<table class="table table-sm table-bordered table-condensed table-fluid">
			<tr>
				<TD style="width:100%; margin: 0 0">
					"""),_display_(/*157.7*/for((lista,index) <-listSeleccion2.zipWithIndex) yield /*157.55*/{_display_(Seq[Any](format.raw/*157.56*/("""
						"""),format.raw/*158.7*/("""<div class="graficosPorEquipo" id="graficosPorEquipo_"""),_display_(/*158.61*/index),format.raw/*158.66*/("""" style="height:600px; width:100%; display: none;" >"""),_display_(/*158.119*/Html(lista)),format.raw/*158.130*/("""</div>
					""")))}),format.raw/*159.7*/("""
				"""),format.raw/*160.5*/("""</TD>
			</tr>
		 </table>
		 
		 
		 
		 

		
		 
		<div class="noprint">
			<br><br><br><br><br><br><br><br>
		</div>
	</div>
			  
""")))}),format.raw/*175.2*/("""

"""),format.raw/*177.1*/("""<script type="text/javascript">

		function sumarFiltro(tabla)"""),format.raw/*179.30*/("""{"""),format.raw/*179.31*/("""
			"""),format.raw/*180.4*/("""var tableReg = document.getElementById(tabla);
			for(var j = 4; j < tableReg.rows[0].cells.length; j++)"""),format.raw/*181.58*/("""{"""),format.raw/*181.59*/("""
				"""),format.raw/*182.5*/("""var acum = 0;
				for (var i = 2; i < tableReg.rows.length - 1; i++)"""),format.raw/*183.55*/("""{"""),format.raw/*183.56*/("""
					"""),format.raw/*184.6*/("""if(tableReg.rows[i].style.display != 'none')"""),format.raw/*184.50*/("""{"""),format.raw/*184.51*/("""
						"""),format.raw/*185.7*/("""var aux = tableReg.rows[i].cells[j].innerHTML;
						aux = aux.replace(/,/g,'');
						acum += parseFloat(aux);
					"""),format.raw/*188.6*/("""}"""),format.raw/*188.7*/("""
				"""),format.raw/*189.5*/("""}"""),format.raw/*189.6*/("""
				"""),format.raw/*190.5*/("""tableReg.rows[tableReg.rows.length - 1].cells[j].innerHTML = formatStandar(acum,0);
			"""),format.raw/*191.4*/("""}"""),format.raw/*191.5*/("""
		"""),format.raw/*192.3*/("""}"""),format.raw/*192.4*/("""
		
	"""),format.raw/*194.2*/("""$(document).ready(function () """),format.raw/*194.32*/("""{"""),format.raw/*194.33*/("""
		"""),format.raw/*195.3*/("""document.getElementById('mostrarmostrar').style.display="block";
		
		$('#tablaPrincipal').DataTable("""),format.raw/*197.34*/("""{"""),format.raw/*197.35*/("""
		    	"""),format.raw/*198.8*/(""""fixedHeader": true,
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [[ 4, "asc" ]],
		    	"language": """),format.raw/*203.20*/("""{"""),format.raw/*203.21*/("""
		        	"""),format.raw/*204.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*205.11*/("""}"""),format.raw/*205.12*/("""
		  """),format.raw/*206.5*/("""}"""),format.raw/*206.6*/(""" """),format.raw/*206.7*/(""");

		  
		  """),_display_(/*209.6*/for((lista,index) <-listSeleccion.zipWithIndex) yield /*209.53*/{_display_(Seq[Any](format.raw/*209.54*/("""
			"""),format.raw/*210.4*/("""Highcharts.chart('grafico_"""),_display_(/*210.31*/index),format.raw/*210.36*/("""',"""),format.raw/*210.38*/("""{"""),format.raw/*210.39*/("""
	            """),format.raw/*211.14*/("""chart: """),format.raw/*211.21*/("""{"""),format.raw/*211.22*/("""
	                """),format.raw/*212.18*/("""type: 'column'
	            """),format.raw/*213.14*/("""}"""),format.raw/*213.15*/(""",
	            title: """),format.raw/*214.21*/("""{"""),format.raw/*214.22*/("""
	                """),format.raw/*215.18*/("""text: 'CONSOLIDADO ' + """),_display_(/*215.42*/Html(lista)),format.raw/*215.53*/("""
	            """),format.raw/*216.14*/("""}"""),format.raw/*216.15*/(""",
	            xAxis: """),format.raw/*217.21*/("""{"""),format.raw/*217.22*/("""
	                """),format.raw/*218.18*/("""categories: """),_display_(/*218.31*/Html(categorias.toString())),format.raw/*218.58*/(""",
	                labels: """),format.raw/*219.26*/("""{"""),format.raw/*219.27*/("""
	                    """),format.raw/*220.22*/("""rotation: -45
	                """),format.raw/*221.18*/("""}"""),format.raw/*221.19*/("""
	            """),format.raw/*222.14*/("""}"""),format.raw/*222.15*/(""",
	            yAxis: """),format.raw/*223.21*/("""{"""),format.raw/*223.22*/("""
	                """),format.raw/*224.18*/("""min: 0,
	                title: """),format.raw/*225.25*/("""{"""),format.raw/*225.26*/("""
	                    """),format.raw/*226.22*/("""text: ''
	                """),format.raw/*227.18*/("""}"""),format.raw/*227.19*/(""",
	                stackLabels: """),format.raw/*228.31*/("""{"""),format.raw/*228.32*/("""
	                    """),format.raw/*229.22*/("""enabled: true,
	                    style: """),format.raw/*230.29*/("""{"""),format.raw/*230.30*/("""
	                        """),format.raw/*231.26*/("""fontWeight: 'bold',
	                        color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
	                    """),format.raw/*233.22*/("""}"""),format.raw/*233.23*/("""
	                """),format.raw/*234.18*/("""}"""),format.raw/*234.19*/("""
	            """),format.raw/*235.14*/("""}"""),format.raw/*235.15*/(""",
	            tooltip: """),format.raw/*236.23*/("""{"""),format.raw/*236.24*/("""
	                """),format.raw/*237.18*/("""formatter: function() """),format.raw/*237.40*/("""{"""),format.raw/*237.41*/("""
	                    """),format.raw/*238.22*/("""return '<b>'+ this.x +'</b><br/>'+
	                        this.series.name +' $: '+ Highcharts.numberFormat(this.y,0) +'<br/>'+
	                        'Total $: '+ Highcharts.numberFormat(this.point.stackTotal,0);
	                """),format.raw/*241.18*/("""}"""),format.raw/*241.19*/("""
	            """),format.raw/*242.14*/("""}"""),format.raw/*242.15*/(""",
	            plotOptions: """),format.raw/*243.27*/("""{"""),format.raw/*243.28*/("""
	                """),format.raw/*244.18*/("""column: """),format.raw/*244.26*/("""{"""),format.raw/*244.27*/("""
	                    """),format.raw/*245.22*/("""stacking: 'normal',
	                    dataLabels: """),format.raw/*246.34*/("""{"""),format.raw/*246.35*/("""
	                        """),format.raw/*247.26*/("""enabled: false,
	                        color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
	                        style: """),format.raw/*249.33*/("""{"""),format.raw/*249.34*/("""
	                            """),format.raw/*250.30*/("""textShadow: '0 0 3px black, 0 0 3px black'
	                        """),format.raw/*251.26*/("""}"""),format.raw/*251.27*/("""
	                    """),format.raw/*252.22*/("""}"""),format.raw/*252.23*/("""
	                """),format.raw/*253.18*/("""}"""),format.raw/*253.19*/("""
	            """),format.raw/*254.14*/("""}"""),format.raw/*254.15*/(""",
	            series: [
		            """),_display_(if(mapEquipo.get(lista)!=null)/*256.46*/{_display_(Seq[Any](format.raw/*256.47*/("""
	            		"""),_display_(/*257.17*/for((lista2,index2) <- mapEquipo.get(lista).zipWithIndex) yield /*257.74*/{_display_(Seq[Any](format.raw/*257.75*/("""
			            	"""),format.raw/*258.17*/("""{"""),format.raw/*258.18*/("""
				            	"""),format.raw/*259.18*/("""name: """),_display_(/*259.25*/Html(lista2)),format.raw/*259.37*/(""",
				            	data: """),_display_(/*260.25*/mapDatos/*260.33*/.get(lista+"_"+lista2).toString()),format.raw/*260.66*/("""


				            """),format.raw/*263.17*/("""}"""),format.raw/*263.18*/("""
			            	"""),_display_(if(index2 < mapEquipo.get(lista).size()-1)/*264.60*/{_display_(Seq[Any](format.raw/*264.61*/("""
			            		"""),format.raw/*265.18*/(""",
			            	""")))} else {null} ),format.raw/*266.18*/("""
	            		""")))}),format.raw/*267.17*/("""
	            	""")))} else {null} ),format.raw/*268.16*/("""
	            """),format.raw/*269.14*/("""]
	        """),format.raw/*270.10*/("""}"""),format.raw/*270.11*/(""");
		""")))}),format.raw/*271.4*/("""
		

		
		
		
		"""),_display_(/*277.4*/for((lista,index) <-listSeleccion2.zipWithIndex) yield /*277.52*/{_display_(Seq[Any](format.raw/*277.53*/("""
			"""),format.raw/*278.4*/("""Highcharts.chart('graficosPorEquipo_"""),_display_(/*278.41*/index),format.raw/*278.46*/("""',"""),format.raw/*278.48*/("""{"""),format.raw/*278.49*/("""
	            """),format.raw/*279.14*/("""chart: """),format.raw/*279.21*/("""{"""),format.raw/*279.22*/("""
	                """),format.raw/*280.18*/("""type: 'column'
	            """),format.raw/*281.14*/("""}"""),format.raw/*281.15*/(""",
	            title: """),format.raw/*282.21*/("""{"""),format.raw/*282.22*/("""
	                """),format.raw/*283.18*/("""text: 'CONSOLIDADO """),_display_(/*283.38*/Html(mapNomEquip.get(lista))),format.raw/*283.66*/("""'
	            """),format.raw/*284.14*/("""}"""),format.raw/*284.15*/(""",
	            xAxis: """),format.raw/*285.21*/("""{"""),format.raw/*285.22*/("""
	                """),format.raw/*286.18*/("""categories: """),_display_(/*286.31*/Html(categorias.toString())),format.raw/*286.58*/(""",
	                labels: """),format.raw/*287.26*/("""{"""),format.raw/*287.27*/("""
	                    """),format.raw/*288.22*/("""rotation: -45
	                """),format.raw/*289.18*/("""}"""),format.raw/*289.19*/("""
	            """),format.raw/*290.14*/("""}"""),format.raw/*290.15*/(""",
	            yAxis: """),format.raw/*291.21*/("""{"""),format.raw/*291.22*/("""
	                """),format.raw/*292.18*/("""min: 0,
	                title: """),format.raw/*293.25*/("""{"""),format.raw/*293.26*/("""
	                    """),format.raw/*294.22*/("""text: ''
	                """),format.raw/*295.18*/("""}"""),format.raw/*295.19*/(""",
	                stackLabels: """),format.raw/*296.31*/("""{"""),format.raw/*296.32*/("""
	                    """),format.raw/*297.22*/("""enabled: true,
	                    style: """),format.raw/*298.29*/("""{"""),format.raw/*298.30*/("""
	                        """),format.raw/*299.26*/("""fontWeight: 'bold',
	                        color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
	                    """),format.raw/*301.22*/("""}"""),format.raw/*301.23*/("""
	                """),format.raw/*302.18*/("""}"""),format.raw/*302.19*/("""
	            """),format.raw/*303.14*/("""}"""),format.raw/*303.15*/(""", 
	            tooltip: """),format.raw/*304.23*/("""{"""),format.raw/*304.24*/("""
	                """),format.raw/*305.18*/("""formatter: function() """),format.raw/*305.40*/("""{"""),format.raw/*305.41*/("""
	                    """),format.raw/*306.22*/("""return '<b>'+ this.x +'</b><br/>'+
	                        this.series.name +' $: '+ Highcharts.numberFormat(this.y,0) +'<br/>'+
	                        'Total $: '+ Highcharts.numberFormat(this.point.stackTotal,0);
	                """),format.raw/*309.18*/("""}"""),format.raw/*309.19*/("""
	            """),format.raw/*310.14*/("""}"""),format.raw/*310.15*/(""",
	            plotOptions: """),format.raw/*311.27*/("""{"""),format.raw/*311.28*/("""
	                """),format.raw/*312.18*/("""column: """),format.raw/*312.26*/("""{"""),format.raw/*312.27*/("""
	                    """),format.raw/*313.22*/("""stacking: 'normal',
	                    dataLabels: """),format.raw/*314.34*/("""{"""),format.raw/*314.35*/("""
	                        """),format.raw/*315.26*/("""enabled: false,
	                        color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
	                        style: """),format.raw/*317.33*/("""{"""),format.raw/*317.34*/("""
	                            """),format.raw/*318.30*/("""textShadow: '0 0 3px black, 0 0 3px black'
	                        """),format.raw/*319.26*/("""}"""),format.raw/*319.27*/("""
	                    """),format.raw/*320.22*/("""}"""),format.raw/*320.23*/("""
	                """),format.raw/*321.18*/("""}"""),format.raw/*321.19*/("""
	            """),format.raw/*322.14*/("""}"""),format.raw/*322.15*/(""",
	            series: [
		            """),_display_(if(mapEmpresa.get(lista)!=null)/*324.47*/{_display_(Seq[Any](format.raw/*324.48*/("""
	            		"""),_display_(/*325.17*/for((lista2,index2) <- mapEmpresa.get(lista).zipWithIndex) yield /*325.75*/{_display_(Seq[Any](format.raw/*325.76*/("""
			            	"""),format.raw/*326.17*/("""{"""),format.raw/*326.18*/("""
				            	"""),format.raw/*327.18*/("""name: """),_display_(/*327.25*/Html(lista2)),format.raw/*327.37*/(""",
				            	data: """),_display_(/*328.25*/mapDatosEquipo/*328.39*/.get(lista+"_"+lista2).toString()),format.raw/*328.72*/("""
				            	
				            	
				            """),format.raw/*331.17*/("""}"""),format.raw/*331.18*/("""
			            	"""),_display_(if(index2 < mapEmpresa.get(lista).size()-1)/*332.61*/{_display_(Seq[Any](format.raw/*332.62*/("""
			            		"""),format.raw/*333.18*/(""",
			            	""")))} else {null} ),format.raw/*334.18*/(""" 
	            		""")))}),format.raw/*335.17*/(""" 
	            	""")))} else {null} ),format.raw/*336.16*/("""
	            """),format.raw/*337.14*/("""]
	        """),format.raw/*338.10*/("""}"""),format.raw/*338.11*/(""");
		""")))}),format.raw/*339.4*/("""

		"""),format.raw/*341.3*/("""document.getElementById('enProceso').style.display="none";
		document.getElementById('mostrarmostrar').style.display="block";
		document.getElementById('enCarga').style.display="none";
		
	"""),format.raw/*345.2*/("""}"""),format.raw/*345.3*/(""");
	
	
	const graficar = () => """),format.raw/*348.25*/("""{"""),format.raw/*348.26*/("""
		"""),format.raw/*349.3*/("""let aux= "grafico_"+$("#selectGrafico").val();
		let graficos = document.getElementsByClassName("graficos");
		for(let i=0; i<graficos.length; i++)"""),format.raw/*351.39*/("""{"""),format.raw/*351.40*/("""
			"""),format.raw/*352.4*/("""graficos[i].style.display = "none";
		"""),format.raw/*353.3*/("""}"""),format.raw/*353.4*/("""
		"""),format.raw/*354.3*/("""if(aux!="grafico_-1")"""),format.raw/*354.24*/("""{"""),format.raw/*354.25*/("""
			"""),format.raw/*355.4*/("""let div = document.getElementById(aux);
			div.style.display = "block";
		"""),format.raw/*357.3*/("""}"""),format.raw/*357.4*/("""
	"""),format.raw/*358.2*/("""}"""),format.raw/*358.3*/("""
	
	"""),format.raw/*360.2*/("""const graficarPorEquipo = () => """),format.raw/*360.34*/("""{"""),format.raw/*360.35*/("""
		"""),format.raw/*361.3*/("""let aux2= "graficosPorEquipo_"+$("#selectGraficoPorEquipo").val();
		let graficosPorEquipo = document.getElementsByClassName("graficosPorEquipo");
		for(let i=0; i<graficosPorEquipo.length; i++)"""),format.raw/*363.48*/("""{"""),format.raw/*363.49*/("""
			"""),format.raw/*364.4*/("""graficosPorEquipo[i].style.display = "none";
		"""),format.raw/*365.3*/("""}"""),format.raw/*365.4*/("""
		"""),format.raw/*366.3*/("""if(aux2!="graficosPorEquipo_-1")"""),format.raw/*366.35*/("""{"""),format.raw/*366.36*/("""
			"""),format.raw/*367.4*/("""let div2 = document.getElementById(aux2);
			div2.style.display = "block";
		"""),format.raw/*369.3*/("""}"""),format.raw/*369.4*/("""
	"""),format.raw/*370.2*/("""}"""),format.raw/*370.3*/("""
	
	

"""),format.raw/*374.1*/("""</script>
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,datos:List[List[String]],fecha:String,cantMeses:String,categorias:List[String],listSeleccion:List[String],mapEquipo:Map[String,List[String]],mapDatos:Map[String, List[String]],listSeleccion2:List[String],mapEmpresa:Map[String,List[String]],mapDatosEquipo:Map[String, List[String]],mapNomEquip:Map[String,String]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,datos,fecha,cantMeses,categorias,listSeleccion,mapEquipo,mapDatos,listSeleccion2,mapEmpresa,mapDatosEquipo,mapNomEquip)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String,List[String],List[String],Map[String,List[String]],Map[String, List[String]],List[String],Map[String,List[String]],Map[String, List[String]],Map[String,String]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,datos,fecha,cantMeses,categorias,listSeleccion,mapEquipo,mapDatos,listSeleccion2,mapEmpresa,mapDatosEquipo,mapNomEquip) => apply(mapDiccionario,mapPermiso,userMnu,datos,fecha,cantMeses,categorias,listSeleccion,mapEquipo,mapDatos,listSeleccion2,mapEmpresa,mapDatosEquipo,mapNomEquip)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reportFactConsolconEquiposRtp.scala.html
                  HASH: 0e1a3bab04fa5d7c7c95df0fb71f170099cca6ce
                  MATRIX: 1223->1|1748->433|1781->441|1797->449|1836->451|1867->455|1908->469|1923->475|1989->520|2043->547|2058->553|2126->600|2180->627|2195->633|2260->677|2314->704|2329->710|2396->756|2437->771|2506->819|2537->823|2742->1002|2928->1166|2959->1170|4162->2346|4188->2351|4267->2403|4297->2412|4524->2613|4568->2641|4607->2642|4641->2649|4700->2681|4728->2688|4770->2700|4802->2705|4849->2726|4894->2755|4933->2756|4967->2763|5026->2795|5055->2803|5097->2815|5129->2820|5190->2855|5235->2884|5274->2885|5316->2900|5388->2945|5427->2946|5461->2953|5562->3027|5576->3032|5604->3039|5717->3125|5731->3130|5759->3137|5872->3223|5886->3228|5914->3235|6027->3321|6041->3326|6069->3333|6182->3419|6196->3424|6224->3431|6263->3444|6324->3489|6363->3490|6398->3498|6500->3573|6527->3579|6570->3592|6600->3603|6639->3604|6673->3611|6730->3641|6744->3646|6772->3653|6841->3695|6855->3700|6883->3707|6952->3749|6966->3754|6994->3761|7063->3803|7077->3808|7105->3815|7174->3857|7188->3862|7216->3869|7255->3882|7316->3927|7355->3928|7390->3936|7448->3967|7475->3973|7518->3986|7555->3993|7599->4010|7660->4040|7692->4044|8035->4360|8100->4408|8140->4409|8181->4421|8225->4437|8252->4442|8283->4445|8310->4450|8358->4467|8392->4473|8595->4649|8659->4696|8699->4697|8734->4704|8797->4739|8824->4744|8906->4797|8940->4808|8984->4821|9017->4826|9359->5141|9425->5190|9465->5191|9506->5203|9550->5219|9577->5224|9608->5227|9635->5232|9683->5249|9717->5255|9919->5430|9984->5478|10024->5479|10059->5486|10141->5540|10168->5545|10250->5598|10284->5609|10328->5622|10361->5627|10527->5762|10557->5764|10648->5826|10678->5827|10710->5831|10843->5935|10873->5936|10906->5941|11003->6009|11033->6010|11067->6016|11140->6060|11170->6061|11205->6068|11350->6185|11379->6186|11412->6191|11441->6192|11474->6197|11589->6284|11618->6285|11649->6288|11678->6289|11711->6294|11770->6324|11800->6325|11831->6328|11961->6429|11991->6430|12027->6438|12195->6577|12225->6578|12266->6590|12373->6668|12403->6669|12436->6674|12465->6675|12494->6676|12535->6690|12599->6737|12639->6738|12671->6742|12726->6769|12753->6774|12784->6776|12814->6777|12857->6791|12893->6798|12923->6799|12970->6817|13027->6845|13057->6846|13108->6868|13138->6869|13185->6887|13237->6911|13270->6922|13313->6936|13343->6937|13394->6959|13424->6960|13471->6978|13512->6991|13561->7018|13617->7045|13647->7046|13698->7068|13758->7099|13788->7100|13831->7114|13861->7115|13912->7137|13942->7138|13989->7156|14050->7188|14080->7189|14131->7211|14186->7237|14216->7238|14277->7270|14307->7271|14358->7293|14430->7336|14460->7337|14515->7363|14676->7495|14706->7496|14753->7514|14783->7515|14826->7529|14856->7530|14909->7554|14939->7555|14986->7573|15037->7595|15067->7596|15118->7618|15382->7853|15412->7854|15455->7868|15485->7869|15542->7897|15572->7898|15619->7916|15656->7924|15686->7925|15737->7947|15819->8000|15849->8001|15904->8027|16080->8174|16110->8175|16169->8205|16266->8273|16296->8274|16347->8296|16377->8297|16424->8315|16454->8316|16497->8330|16527->8331|16625->8401|16665->8402|16710->8419|16784->8476|16824->8477|16870->8494|16900->8495|16947->8513|16982->8520|17016->8532|17070->8558|17088->8566|17143->8599|17191->8618|17221->8619|17309->8679|17349->8680|17396->8698|17460->8717|17509->8734|17570->8750|17613->8764|17653->8775|17683->8776|17720->8782|17764->8799|17829->8847|17869->8848|17901->8852|17966->8889|17993->8894|18024->8896|18054->8897|18097->8911|18133->8918|18163->8919|18210->8937|18267->8965|18297->8966|18348->8988|18378->8989|18425->9007|18473->9027|18523->9055|18567->9070|18597->9071|18648->9093|18678->9094|18725->9112|18766->9125|18815->9152|18871->9179|18901->9180|18952->9202|19012->9233|19042->9234|19085->9248|19115->9249|19166->9271|19196->9272|19243->9290|19304->9322|19334->9323|19385->9345|19440->9371|19470->9372|19531->9404|19561->9405|19612->9427|19684->9470|19714->9471|19769->9497|19930->9629|19960->9630|20007->9648|20037->9649|20080->9663|20110->9664|20164->9689|20194->9690|20241->9708|20292->9730|20322->9731|20373->9753|20637->9988|20667->9989|20710->10003|20740->10004|20797->10032|20827->10033|20874->10051|20911->10059|20941->10060|20992->10082|21074->10135|21104->10136|21159->10162|21335->10309|21365->10310|21424->10340|21521->10408|21551->10409|21602->10431|21632->10432|21679->10450|21709->10451|21752->10465|21782->10466|21881->10537|21921->10538|21966->10555|22041->10613|22081->10614|22127->10631|22157->10632|22204->10650|22239->10657|22273->10669|22327->10695|22351->10709|22406->10742|22488->10795|22518->10796|22607->10857|22647->10858|22694->10876|22758->10895|22808->10913|22870->10930|22913->10944|22953->10955|22983->10956|23020->10962|23052->10966|23269->11155|23298->11156|23358->11187|23388->11188|23419->11191|23595->11338|23625->11339|23657->11343|23723->11381|23752->11382|23783->11385|23833->11406|23863->11407|23895->11411|23997->11485|24026->11486|24056->11488|24085->11489|24117->11493|24178->11525|24208->11526|24239->11529|24462->11723|24492->11724|24524->11728|24599->11775|24628->11776|24659->11779|24720->11811|24750->11812|24782->11816|24887->11893|24916->11894|24946->11896|24975->11897|25009->11903
                  LINES: 28->1|37->6|39->8|39->8|39->8|41->10|41->10|41->10|41->10|42->11|42->11|42->11|43->12|43->12|43->12|44->13|44->13|44->13|46->15|46->15|48->17|51->20|51->20|53->22|86->55|86->55|87->56|87->56|93->62|93->62|93->62|94->63|94->63|94->63|95->64|96->65|98->67|98->67|98->67|99->68|99->68|99->68|100->69|101->70|104->73|104->73|104->73|107->76|108->77|108->77|109->78|109->78|109->78|109->78|110->79|110->79|110->79|111->80|111->80|111->80|112->81|112->81|112->81|113->82|113->82|113->82|114->83|114->83|114->83|115->84|115->84|115->84|116->85|117->86|117->86|118->87|118->87|118->87|118->87|119->88|119->88|119->88|120->89|120->89|120->89|121->90|121->90|121->90|122->91|122->91|122->91|123->92|123->92|123->92|124->93|124->93|124->93|125->94|126->95|129->98|134->103|135->104|153->122|153->122|153->122|154->123|154->123|154->123|154->123|154->123|155->124|156->125|164->133|164->133|164->133|165->134|165->134|165->134|165->134|165->134|166->135|167->136|177->146|177->146|177->146|178->147|178->147|178->147|178->147|178->147|179->148|180->149|188->157|188->157|188->157|189->158|189->158|189->158|189->158|189->158|190->159|191->160|206->175|208->177|210->179|210->179|211->180|212->181|212->181|213->182|214->183|214->183|215->184|215->184|215->184|216->185|219->188|219->188|220->189|220->189|221->190|222->191|222->191|223->192|223->192|225->194|225->194|225->194|226->195|228->197|228->197|229->198|234->203|234->203|235->204|236->205|236->205|237->206|237->206|237->206|240->209|240->209|240->209|241->210|241->210|241->210|241->210|241->210|242->211|242->211|242->211|243->212|244->213|244->213|245->214|245->214|246->215|246->215|246->215|247->216|247->216|248->217|248->217|249->218|249->218|249->218|250->219|250->219|251->220|252->221|252->221|253->222|253->222|254->223|254->223|255->224|256->225|256->225|257->226|258->227|258->227|259->228|259->228|260->229|261->230|261->230|262->231|264->233|264->233|265->234|265->234|266->235|266->235|267->236|267->236|268->237|268->237|268->237|269->238|272->241|272->241|273->242|273->242|274->243|274->243|275->244|275->244|275->244|276->245|277->246|277->246|278->247|280->249|280->249|281->250|282->251|282->251|283->252|283->252|284->253|284->253|285->254|285->254|287->256|287->256|288->257|288->257|288->257|289->258|289->258|290->259|290->259|290->259|291->260|291->260|291->260|294->263|294->263|295->264|295->264|296->265|297->266|298->267|299->268|300->269|301->270|301->270|302->271|308->277|308->277|308->277|309->278|309->278|309->278|309->278|309->278|310->279|310->279|310->279|311->280|312->281|312->281|313->282|313->282|314->283|314->283|314->283|315->284|315->284|316->285|316->285|317->286|317->286|317->286|318->287|318->287|319->288|320->289|320->289|321->290|321->290|322->291|322->291|323->292|324->293|324->293|325->294|326->295|326->295|327->296|327->296|328->297|329->298|329->298|330->299|332->301|332->301|333->302|333->302|334->303|334->303|335->304|335->304|336->305|336->305|336->305|337->306|340->309|340->309|341->310|341->310|342->311|342->311|343->312|343->312|343->312|344->313|345->314|345->314|346->315|348->317|348->317|349->318|350->319|350->319|351->320|351->320|352->321|352->321|353->322|353->322|355->324|355->324|356->325|356->325|356->325|357->326|357->326|358->327|358->327|358->327|359->328|359->328|359->328|362->331|362->331|363->332|363->332|364->333|365->334|366->335|367->336|368->337|369->338|369->338|370->339|372->341|376->345|376->345|379->348|379->348|380->349|382->351|382->351|383->352|384->353|384->353|385->354|385->354|385->354|386->355|388->357|388->357|389->358|389->358|391->360|391->360|391->360|392->361|394->363|394->363|395->364|396->365|396->365|397->366|397->366|397->366|398->367|400->369|400->369|401->370|401->370|405->374
                  -- GENERATED --
              */
          