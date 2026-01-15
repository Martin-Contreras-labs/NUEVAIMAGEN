
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

object reportFactConsolconGrupoRtp extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template13[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String,List[String],List[String],Map[String,List[String]],Map[String, List[String]],List[String],Map[String,List[String]],Map[String, List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
datos: List[List[String]], fecha: String, cantMeses: String, categorias: List[String], 
listSeleccion: List[String], mapGrupo: Map[String,List[String]], mapDatos: Map[String, List[String]],
listSeleccion2: List[String], mapEmpresa: Map[String,List[String]], mapDatosGrupo: Map[String, List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*5.1*/("""    	

"""),_display_(/*7.2*/main("")/*7.10*/ {_display_(Seq[Any](format.raw/*7.12*/("""
	
	"""),format.raw/*9.2*/("""<script src=""""),_display_(/*9.16*/routes/*9.22*/.Assets.versioned("highcharts/highcharts.js")),format.raw/*9.67*/(""""></script>
	<script src=""""),_display_(/*10.16*/routes/*10.22*/.Assets.versioned("highcharts/series-label.js")),format.raw/*10.69*/(""""></script>
	<script src=""""),_display_(/*11.16*/routes/*11.22*/.Assets.versioned("highcharts/exporting.js")),format.raw/*11.66*/(""""></script>
	<script src=""""),_display_(/*12.16*/routes/*12.22*/.Assets.versioned("highcharts/export-data.js")),format.raw/*12.68*/(""""></script>

	"""),_display_(/*14.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*14.51*/("""
	
	"""),format.raw/*16.2*/("""<div id="enCarga" class="blocker" style="display: block;"><br><br><br><br><br><br><h1>Ahora se esta cargando.....</h1></div>
	
	<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*19.4*/barraTitulo(mapDiccionario, "CONSOLIDADO DE PROFORMAS POR GRUPO (NO CONSIDERA AJUSTES A EP)","POR "+cantMeses+" MESES VS " + mapDiccionario("BODEGA")+"/PROYECTO")),format.raw/*19.166*/("""

		"""),format.raw/*21.3*/("""<div class="noprint">
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
		
		
		<form id="excel" class="formulario" method="post" action="/reportFactConsolconGrupoRtpExcel/">
			<input type="hidden" name="fecha" value=""""),_display_(/*56.46*/fecha),format.raw/*56.51*/("""">
			<input type="hidden" name="cantMeses" value=""""),_display_(/*57.50*/cantMeses),format.raw/*57.59*/("""">
		</form>

		<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
	  		<thead style="background-color: #eeeeee" class="header">
	  			<TR>
					"""),_display_(/*63.7*/for(titulos <- datos.get(0)) yield /*63.35*/{_display_(Seq[Any](format.raw/*63.36*/("""
						"""),format.raw/*64.7*/("""<th style="text-align:center;">"""),_display_(/*64.39*/titulos),format.raw/*64.46*/("""</th>
					""")))}),format.raw/*65.7*/("""
				"""),format.raw/*66.5*/("""</TR>
				<TR>
					"""),_display_(/*68.7*/for(titulos2 <- datos.get(1)) yield /*68.36*/{_display_(Seq[Any](format.raw/*68.37*/("""
						"""),format.raw/*69.7*/("""<th style="text-align:center;">"""),_display_(/*69.39*/titulos2),format.raw/*69.47*/("""</th>
					""")))}),format.raw/*70.7*/("""
				"""),format.raw/*71.5*/("""</TR>
	  		</thead>
			<tbody>
			"""),_display_(/*74.5*/for(lista <- datos.tail.tail) yield /*74.34*/{_display_(Seq[Any](format.raw/*74.35*/("""
				"""),format.raw/*75.5*/("""<TR>
					"""),_display_(if(lista.get(0).equals("TOTALES"))/*76.41*/{_display_(Seq[Any](format.raw/*76.42*/("""
						"""),format.raw/*77.7*/("""<td style="text-align:left;"><div style="display:none">ZZZZZZZZZZZZ</div>"""),_display_(/*77.81*/lista/*77.86*/.get(0)),format.raw/*77.93*/("""</td>
						<td style="text-align:left;"><div style="display:none">ZZZZZZZZZZZZ</div></td>
						"""),_display_(/*79.8*/for(lista2 <- lista.tail.tail) yield /*79.38*/{_display_(Seq[Any](format.raw/*79.39*/("""
							"""),format.raw/*80.8*/("""<td style="text-align:right;"><div style="display:none">ZZZZZZZZZZZZ</div>"""),_display_(/*80.83*/lista2),format.raw/*80.89*/("""</td>
						""")))}),format.raw/*81.8*/("""
					""")))}else/*82.11*/{_display_(Seq[Any](format.raw/*82.12*/("""
						"""),format.raw/*83.7*/("""<td style="text-align:left;">"""),_display_(/*83.37*/lista/*83.42*/.get(0)),format.raw/*83.49*/("""</td>
						<td style="text-align:left;">"""),_display_(/*84.37*/lista/*84.42*/.get(1)),format.raw/*84.49*/("""</td>
						"""),_display_(/*85.8*/for(lista2 <- lista.tail.tail) yield /*85.38*/{_display_(Seq[Any](format.raw/*85.39*/("""
							"""),format.raw/*86.8*/("""<td style="text-align:right;">"""),_display_(/*86.39*/lista2),format.raw/*86.45*/("""</td>
						""")))}),format.raw/*87.8*/("""
					""")))}),format.raw/*88.7*/("""
					
					
				"""),format.raw/*91.5*/("""</TR>
			""")))}),format.raw/*92.5*/("""
			"""),format.raw/*93.4*/("""</tbody>
		</table>
		
		<div class="noprint">
			<form>
				<div align="left">
					Graficar por """),_display_(/*99.20*/mapDiccionario/*99.34*/.get("BODEGA")),format.raw/*99.48*/(""":
					<select class="input-large" id="selectGrafico" name="cod_region" style="text-align:left;height:24px" onChange="graficar()">
						<option value="-1">seleccionar</option>
						"""),_display_(/*102.8*/for((lista,index) <- listSeleccion.zipWithIndex) yield /*102.56*/{_display_(Seq[Any](format.raw/*102.57*/("""
					     	"""),format.raw/*103.12*/("""<option value=""""),_display_(/*103.28*/index),format.raw/*103.33*/("""">"""),_display_(/*103.36*/lista),format.raw/*103.41*/("""</option>
						""")))}),format.raw/*104.8*/("""
					"""),format.raw/*105.6*/("""</select>
				</div>
			</form>
		</div>
			
		<table class="table table-sm table-bordered table-condensed table-fluid">
			<tr>
				<TD style="width:100%; margin: 0 0">
					"""),_display_(/*113.7*/for((lista,index) <-listSeleccion.zipWithIndex) yield /*113.54*/{_display_(Seq[Any](format.raw/*113.55*/("""
						"""),format.raw/*114.7*/("""<div class="graficos" id="grafico_"""),_display_(/*114.42*/index),format.raw/*114.47*/("""" style="height:600px; width:100%; display: none;" >"""),_display_(/*114.100*/Html(lista)),format.raw/*114.111*/("""</div>
					""")))}),format.raw/*115.7*/("""
				"""),format.raw/*116.5*/("""</TD>
			</tr>
		 </table>
		 
		 <div class="noprint">
			<form>
				<div align="left">
					Graficar por GRUPO:
					<select class="input-large" id="selectGraficoPorGrupo" name="cod_region" style="text-align:left;height:24px" onChange="graficarPorGrupo()">
						<option value="-1">seleccionar</option>
						"""),_display_(/*126.8*/for((lista,index) <- listSeleccion2.zipWithIndex) yield /*126.57*/{_display_(Seq[Any](format.raw/*126.58*/("""
					     	"""),format.raw/*127.12*/("""<option value=""""),_display_(/*127.28*/index),format.raw/*127.33*/("""">"""),_display_(/*127.36*/lista),format.raw/*127.41*/("""</option>
						""")))}),format.raw/*128.8*/("""
					"""),format.raw/*129.6*/("""</select>
				</div>
			</form>
		</div>
		
		<table class="table table-sm table-bordered table-condensed table-fluid">
			<tr>
				<TD style="width:100%; margin: 0 0">
					"""),_display_(/*137.7*/for((lista,index) <-listSeleccion2.zipWithIndex) yield /*137.55*/{_display_(Seq[Any](format.raw/*137.56*/("""
						"""),format.raw/*138.7*/("""<div class="graficosPorGrupo" id="graficosPorGrupo_"""),_display_(/*138.59*/index),format.raw/*138.64*/("""" style="height:600px; width:100%; display: none;" >"""),_display_(/*138.117*/Html(lista)),format.raw/*138.128*/("""</div>
					""")))}),format.raw/*139.7*/("""
				"""),format.raw/*140.5*/("""</TD>
			</tr>
		 </table>
		 
		 
		 
		 
		<div class="noprint">
			<br><br><br><br><br><br><br><br>
		</div>
	</div>
			  
""")))}),format.raw/*152.2*/("""

"""),format.raw/*154.1*/("""<script type="text/javascript">

		function sumarFiltro(tabla)"""),format.raw/*156.30*/("""{"""),format.raw/*156.31*/("""
			"""),format.raw/*157.4*/("""var tableReg = document.getElementById(tabla);
			for(var j = 2; j < tableReg.rows[0].cells.length; j++)"""),format.raw/*158.58*/("""{"""),format.raw/*158.59*/("""
				"""),format.raw/*159.5*/("""var acum = 0;
				for (var i = 2; i < tableReg.rows.length - 1; i++)"""),format.raw/*160.55*/("""{"""),format.raw/*160.56*/("""
					"""),format.raw/*161.6*/("""if(tableReg.rows[i].style.display != 'none')"""),format.raw/*161.50*/("""{"""),format.raw/*161.51*/("""
						"""),format.raw/*162.7*/("""var aux = tableReg.rows[i].cells[j].innerHTML;
						aux = aux.replace(/,/g,'');
						acum += parseFloat(aux);
					"""),format.raw/*165.6*/("""}"""),format.raw/*165.7*/("""
				"""),format.raw/*166.5*/("""}"""),format.raw/*166.6*/("""
				"""),format.raw/*167.5*/("""tableReg.rows[tableReg.rows.length - 1].cells[j].innerHTML = formatStandar(acum,0);
			"""),format.raw/*168.4*/("""}"""),format.raw/*168.5*/("""
		"""),format.raw/*169.3*/("""}"""),format.raw/*169.4*/("""
		
	"""),format.raw/*171.2*/("""$(document).ready(function () """),format.raw/*171.32*/("""{"""),format.raw/*171.33*/("""
		"""),format.raw/*172.3*/("""document.getElementById('mostrarmostrar').style.display="block";
		
		$('#tablaPrincipal').DataTable("""),format.raw/*174.34*/("""{"""),format.raw/*174.35*/("""
		    	"""),format.raw/*175.8*/(""""fixedHeader": true,
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [[ 1, "asc" ]],
		    	"language": """),format.raw/*180.20*/("""{"""),format.raw/*180.21*/("""
		        	"""),format.raw/*181.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*182.11*/("""}"""),format.raw/*182.12*/("""
		  """),format.raw/*183.5*/("""}"""),format.raw/*183.6*/(""" """),format.raw/*183.7*/(""");
		
		"""),_display_(/*185.4*/for((lista,index) <-listSeleccion.zipWithIndex) yield /*185.51*/{_display_(Seq[Any](format.raw/*185.52*/("""
			"""),format.raw/*186.4*/("""Highcharts.chart('grafico_"""),_display_(/*186.31*/index),format.raw/*186.36*/("""',"""),format.raw/*186.38*/("""{"""),format.raw/*186.39*/("""
	            """),format.raw/*187.14*/("""chart: """),format.raw/*187.21*/("""{"""),format.raw/*187.22*/("""
	                """),format.raw/*188.18*/("""type: 'column'
	            """),format.raw/*189.14*/("""}"""),format.raw/*189.15*/(""",
	            title: """),format.raw/*190.21*/("""{"""),format.raw/*190.22*/("""
	                """),format.raw/*191.18*/("""text: 'CONSOLIDADO '+ """),_display_(/*191.41*/Html(lista)),format.raw/*191.52*/("""
	            """),format.raw/*192.14*/("""}"""),format.raw/*192.15*/(""",
	            xAxis: """),format.raw/*193.21*/("""{"""),format.raw/*193.22*/("""
	                """),format.raw/*194.18*/("""categories: """),_display_(/*194.31*/Html(categorias.toString())),format.raw/*194.58*/(""",
	                labels: """),format.raw/*195.26*/("""{"""),format.raw/*195.27*/("""
	                    """),format.raw/*196.22*/("""rotation: -45
	                """),format.raw/*197.18*/("""}"""),format.raw/*197.19*/("""
	            """),format.raw/*198.14*/("""}"""),format.raw/*198.15*/(""",
	            yAxis: """),format.raw/*199.21*/("""{"""),format.raw/*199.22*/("""
	                """),format.raw/*200.18*/("""min: 0,
	                title: """),format.raw/*201.25*/("""{"""),format.raw/*201.26*/("""
	                    """),format.raw/*202.22*/("""text: ''
	                """),format.raw/*203.18*/("""}"""),format.raw/*203.19*/(""",
	                stackLabels: """),format.raw/*204.31*/("""{"""),format.raw/*204.32*/("""
	                    """),format.raw/*205.22*/("""enabled: true,
	                    style: """),format.raw/*206.29*/("""{"""),format.raw/*206.30*/("""
	                        """),format.raw/*207.26*/("""fontWeight: 'bold',
	                        color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
	                    """),format.raw/*209.22*/("""}"""),format.raw/*209.23*/("""
	                """),format.raw/*210.18*/("""}"""),format.raw/*210.19*/("""
	            """),format.raw/*211.14*/("""}"""),format.raw/*211.15*/(""", 
	            tooltip: """),format.raw/*212.23*/("""{"""),format.raw/*212.24*/("""
	                """),format.raw/*213.18*/("""formatter: function() """),format.raw/*213.40*/("""{"""),format.raw/*213.41*/("""
	                    """),format.raw/*214.22*/("""return '<b>'+ this.x +'</b><br/>'+
	                        this.series.name +' $: '+ Highcharts.numberFormat(this.y,0) +'<br/>'+
	                        'Total $: '+ Highcharts.numberFormat(this.point.stackTotal,0);
	                """),format.raw/*217.18*/("""}"""),format.raw/*217.19*/("""
	            """),format.raw/*218.14*/("""}"""),format.raw/*218.15*/(""",
	            plotOptions: """),format.raw/*219.27*/("""{"""),format.raw/*219.28*/("""
	                """),format.raw/*220.18*/("""column: """),format.raw/*220.26*/("""{"""),format.raw/*220.27*/("""
	                    """),format.raw/*221.22*/("""stacking: 'normal',
	                    dataLabels: """),format.raw/*222.34*/("""{"""),format.raw/*222.35*/("""
	                        """),format.raw/*223.26*/("""enabled: false,
	                        color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
	                        style: """),format.raw/*225.33*/("""{"""),format.raw/*225.34*/("""
	                            """),format.raw/*226.30*/("""textShadow: '0 0 3px black, 0 0 3px black'
	                        """),format.raw/*227.26*/("""}"""),format.raw/*227.27*/("""
	                    """),format.raw/*228.22*/("""}"""),format.raw/*228.23*/("""
	                """),format.raw/*229.18*/("""}"""),format.raw/*229.19*/("""
	            """),format.raw/*230.14*/("""}"""),format.raw/*230.15*/(""",
	            series: [
		            """),_display_(if(mapGrupo.get(lista)!=null)/*232.45*/{_display_(Seq[Any](format.raw/*232.46*/("""
	            		"""),_display_(/*233.17*/for((lista2,index2) <- mapGrupo.get(lista).zipWithIndex) yield /*233.73*/{_display_(Seq[Any](format.raw/*233.74*/("""
			            	"""),format.raw/*234.17*/("""{"""),format.raw/*234.18*/("""
				            	"""),format.raw/*235.18*/("""name: """),_display_(/*235.25*/Html(lista2)),format.raw/*235.37*/(""",
				            	data: """),_display_(/*236.25*/mapDatos/*236.33*/.get(lista+"_"+lista2).toString()),format.raw/*236.66*/("""
				            	
				            	
				            """),format.raw/*239.17*/("""}"""),format.raw/*239.18*/("""
			            	"""),_display_(if(index2 < mapGrupo.get(lista).size()-1)/*240.59*/{_display_(Seq[Any](format.raw/*240.60*/("""
			            		"""),format.raw/*241.18*/(""",
			            	""")))} else {null} ),format.raw/*242.18*/(""" 
	            		""")))}),format.raw/*243.17*/(""" 
	            	""")))} else {null} ),format.raw/*244.16*/("""
	            """),format.raw/*245.14*/("""]
	        """),format.raw/*246.10*/("""}"""),format.raw/*246.11*/(""");
		""")))}),format.raw/*247.4*/("""
		
		"""),_display_(/*249.4*/for((lista,index) <-listSeleccion2.zipWithIndex) yield /*249.52*/{_display_(Seq[Any](format.raw/*249.53*/("""
			"""),format.raw/*250.4*/("""Highcharts.chart('graficosPorGrupo_"""),_display_(/*250.40*/index),format.raw/*250.45*/("""',"""),format.raw/*250.47*/("""{"""),format.raw/*250.48*/("""
	            """),format.raw/*251.14*/("""chart: """),format.raw/*251.21*/("""{"""),format.raw/*251.22*/("""
	                """),format.raw/*252.18*/("""type: 'column'
	            """),format.raw/*253.14*/("""}"""),format.raw/*253.15*/(""",
	            title: """),format.raw/*254.21*/("""{"""),format.raw/*254.22*/("""
	                """),format.raw/*255.18*/("""text: 'CONSOLIDADO '+ """),_display_(/*255.41*/Html(lista)),format.raw/*255.52*/("""
	            """),format.raw/*256.14*/("""}"""),format.raw/*256.15*/(""",
	            xAxis: """),format.raw/*257.21*/("""{"""),format.raw/*257.22*/("""
	                """),format.raw/*258.18*/("""categories: """),_display_(/*258.31*/Html(categorias.toString())),format.raw/*258.58*/(""",
	                labels: """),format.raw/*259.26*/("""{"""),format.raw/*259.27*/("""
	                    """),format.raw/*260.22*/("""rotation: -45
	                """),format.raw/*261.18*/("""}"""),format.raw/*261.19*/("""
	            """),format.raw/*262.14*/("""}"""),format.raw/*262.15*/(""",
	            yAxis: """),format.raw/*263.21*/("""{"""),format.raw/*263.22*/("""
	                """),format.raw/*264.18*/("""min: 0,
	                title: """),format.raw/*265.25*/("""{"""),format.raw/*265.26*/("""
	                    """),format.raw/*266.22*/("""text: ''
	                """),format.raw/*267.18*/("""}"""),format.raw/*267.19*/(""",
	                stackLabels: """),format.raw/*268.31*/("""{"""),format.raw/*268.32*/("""
	                    """),format.raw/*269.22*/("""enabled: true,
	                    style: """),format.raw/*270.29*/("""{"""),format.raw/*270.30*/("""
	                        """),format.raw/*271.26*/("""fontWeight: 'bold',
	                        color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
	                    """),format.raw/*273.22*/("""}"""),format.raw/*273.23*/("""
	                """),format.raw/*274.18*/("""}"""),format.raw/*274.19*/("""
	            """),format.raw/*275.14*/("""}"""),format.raw/*275.15*/(""", 
	            tooltip: """),format.raw/*276.23*/("""{"""),format.raw/*276.24*/("""
	                """),format.raw/*277.18*/("""formatter: function() """),format.raw/*277.40*/("""{"""),format.raw/*277.41*/("""
	                    """),format.raw/*278.22*/("""return '<b>'+ this.x +'</b><br/>'+
	                        this.series.name +' $: '+ Highcharts.numberFormat(this.y,0) +'<br/>'+
	                        'Total $: '+ Highcharts.numberFormat(this.point.stackTotal,0);
	                """),format.raw/*281.18*/("""}"""),format.raw/*281.19*/("""
	            """),format.raw/*282.14*/("""}"""),format.raw/*282.15*/(""",
	            plotOptions: """),format.raw/*283.27*/("""{"""),format.raw/*283.28*/("""
	                """),format.raw/*284.18*/("""column: """),format.raw/*284.26*/("""{"""),format.raw/*284.27*/("""
	                    """),format.raw/*285.22*/("""stacking: 'normal',
	                    dataLabels: """),format.raw/*286.34*/("""{"""),format.raw/*286.35*/("""
	                        """),format.raw/*287.26*/("""enabled: false,
	                        color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
	                        style: """),format.raw/*289.33*/("""{"""),format.raw/*289.34*/("""
	                            """),format.raw/*290.30*/("""textShadow: '0 0 3px black, 0 0 3px black'
	                        """),format.raw/*291.26*/("""}"""),format.raw/*291.27*/("""
	                    """),format.raw/*292.22*/("""}"""),format.raw/*292.23*/("""
	                """),format.raw/*293.18*/("""}"""),format.raw/*293.19*/("""
	            """),format.raw/*294.14*/("""}"""),format.raw/*294.15*/(""",
	            series: [
		            """),_display_(if(mapEmpresa.get(lista)!=null)/*296.47*/{_display_(Seq[Any](format.raw/*296.48*/("""
	            		"""),_display_(/*297.17*/for((lista2,index2) <- mapEmpresa.get(lista).zipWithIndex) yield /*297.75*/{_display_(Seq[Any](format.raw/*297.76*/("""
			            	"""),format.raw/*298.17*/("""{"""),format.raw/*298.18*/("""
				            	"""),format.raw/*299.18*/("""name: """),_display_(/*299.25*/Html(lista2)),format.raw/*299.37*/(""",
				            	data: """),_display_(/*300.25*/mapDatosGrupo/*300.38*/.get(lista+"_"+lista2).toString()),format.raw/*300.71*/("""
				            	
				            	
				            """),format.raw/*303.17*/("""}"""),format.raw/*303.18*/("""
			            	"""),_display_(if(index2 < mapEmpresa.get(lista).size()-1)/*304.61*/{_display_(Seq[Any](format.raw/*304.62*/("""
			            		"""),format.raw/*305.18*/(""",
			            	""")))} else {null} ),format.raw/*306.18*/(""" 
	            		""")))}),format.raw/*307.17*/(""" 
	            	""")))} else {null} ),format.raw/*308.16*/("""
	            """),format.raw/*309.14*/("""]
	        """),format.raw/*310.10*/("""}"""),format.raw/*310.11*/(""");
		""")))}),format.raw/*311.4*/("""
		
		"""),format.raw/*313.3*/("""document.getElementById('enProceso').style.display="none";
		document.getElementById('mostrarmostrar').style.display="block";
		document.getElementById('enCarga').style.display="none";
		
	"""),format.raw/*317.2*/("""}"""),format.raw/*317.3*/(""");
	
	const graficar = () => """),format.raw/*319.25*/("""{"""),format.raw/*319.26*/("""
		"""),format.raw/*320.3*/("""let aux= "grafico_"+$("#selectGrafico").val();
		let graficos = document.getElementsByClassName("graficos");
		for(let i=0; i<graficos.length; i++)"""),format.raw/*322.39*/("""{"""),format.raw/*322.40*/("""
			"""),format.raw/*323.4*/("""graficos[i].style.display = "none";
		"""),format.raw/*324.3*/("""}"""),format.raw/*324.4*/("""
		"""),format.raw/*325.3*/("""if(aux!="grafico_-1")"""),format.raw/*325.24*/("""{"""),format.raw/*325.25*/("""
			"""),format.raw/*326.4*/("""let div = document.getElementById(aux);
			div.style.display = "block";
		"""),format.raw/*328.3*/("""}"""),format.raw/*328.4*/("""
	"""),format.raw/*329.2*/("""}"""),format.raw/*329.3*/("""
	
	"""),format.raw/*331.2*/("""const graficarPorGrupo = () => """),format.raw/*331.33*/("""{"""),format.raw/*331.34*/("""
		"""),format.raw/*332.3*/("""let aux2= "graficosPorGrupo_"+$("#selectGraficoPorGrupo").val();
		let graficosPorGrupo = document.getElementsByClassName("graficosPorGrupo");
		for(let i=0; i<graficosPorGrupo.length; i++)"""),format.raw/*334.47*/("""{"""),format.raw/*334.48*/("""
			"""),format.raw/*335.4*/("""graficosPorGrupo[i].style.display = "none";
		"""),format.raw/*336.3*/("""}"""),format.raw/*336.4*/("""
		"""),format.raw/*337.3*/("""if(aux2!="graficosPorGrupo_-1")"""),format.raw/*337.34*/("""{"""),format.raw/*337.35*/("""
			"""),format.raw/*338.4*/("""let div2 = document.getElementById(aux2);
			div2.style.display = "block";
		"""),format.raw/*340.3*/("""}"""),format.raw/*340.4*/("""
	"""),format.raw/*341.2*/("""}"""),format.raw/*341.3*/("""

"""),format.raw/*343.1*/("""</script>
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,datos:List[List[String]],fecha:String,cantMeses:String,categorias:List[String],listSeleccion:List[String],mapGrupo:Map[String,List[String]],mapDatos:Map[String, List[String]],listSeleccion2:List[String],mapEmpresa:Map[String,List[String]],mapDatosGrupo:Map[String, List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,datos,fecha,cantMeses,categorias,listSeleccion,mapGrupo,mapDatos,listSeleccion2,mapEmpresa,mapDatosGrupo)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String,List[String],List[String],Map[String,List[String]],Map[String, List[String]],List[String],Map[String,List[String]],Map[String, List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,datos,fecha,cantMeses,categorias,listSeleccion,mapGrupo,mapDatos,listSeleccion2,mapEmpresa,mapDatosGrupo) => apply(mapDiccionario,mapPermiso,userMnu,datos,fecha,cantMeses,categorias,listSeleccion,mapGrupo,mapDatos,listSeleccion2,mapEmpresa,mapDatosGrupo)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reportFactConsolconGrupoRtp.scala.html
                  HASH: 0b0000a78aff0d0fe37b3fb29ae5976a5db8e089
                  MATRIX: 1202->1|1692->398|1725->406|1741->414|1780->416|1810->420|1850->434|1864->440|1929->485|1983->512|1998->518|2066->565|2120->592|2135->598|2200->642|2254->669|2269->675|2336->721|2377->736|2446->784|2477->788|2682->967|2866->1129|2897->1133|4107->2316|4133->2321|4212->2373|4242->2382|4469->2583|4513->2611|4552->2612|4586->2619|4645->2651|4673->2658|4715->2670|4747->2675|4794->2696|4839->2725|4878->2726|4912->2733|4971->2765|5000->2773|5042->2785|5074->2790|5135->2825|5180->2854|5219->2855|5251->2860|5323->2905|5362->2906|5396->2913|5497->2987|5511->2992|5539->2999|5663->3097|5709->3127|5748->3128|5783->3136|5885->3211|5912->3217|5955->3230|5985->3241|6024->3242|6058->3249|6115->3279|6129->3284|6157->3291|6226->3333|6240->3338|6268->3345|6307->3358|6353->3388|6392->3389|6427->3397|6485->3428|6512->3434|6555->3447|6592->3454|6636->3471|6676->3481|6707->3485|6833->3584|6856->3598|6891->3612|7102->3796|7167->3844|7207->3845|7248->3857|7292->3873|7319->3878|7350->3881|7377->3886|7425->3903|7459->3909|7662->4085|7726->4132|7766->4133|7801->4140|7864->4175|7891->4180|7973->4233|8007->4244|8051->4257|8084->4262|8423->4574|8489->4623|8529->4624|8570->4636|8614->4652|8641->4657|8672->4660|8699->4665|8747->4682|8781->4688|8983->4863|9048->4911|9088->4912|9123->4919|9203->4971|9230->4976|9312->5029|9346->5040|9390->5053|9423->5058|9581->5185|9611->5187|9702->5249|9732->5250|9764->5254|9897->5358|9927->5359|9960->5364|10057->5432|10087->5433|10121->5439|10194->5483|10224->5484|10259->5491|10404->5608|10433->5609|10466->5614|10495->5615|10528->5620|10643->5707|10672->5708|10703->5711|10732->5712|10765->5717|10824->5747|10854->5748|10885->5751|11015->5852|11045->5853|11081->5861|11249->6000|11279->6001|11320->6013|11427->6091|11457->6092|11490->6097|11519->6098|11548->6099|11584->6108|11648->6155|11688->6156|11720->6160|11775->6187|11802->6192|11833->6194|11863->6195|11906->6209|11942->6216|11972->6217|12019->6235|12076->6263|12106->6264|12157->6286|12187->6287|12234->6305|12285->6328|12318->6339|12361->6353|12391->6354|12442->6376|12472->6377|12519->6395|12560->6408|12609->6435|12665->6462|12695->6463|12746->6485|12806->6516|12836->6517|12879->6531|12909->6532|12960->6554|12990->6555|13037->6573|13098->6605|13128->6606|13179->6628|13234->6654|13264->6655|13325->6687|13355->6688|13406->6710|13478->6753|13508->6754|13563->6780|13724->6912|13754->6913|13801->6931|13831->6932|13874->6946|13904->6947|13958->6972|13988->6973|14035->6991|14086->7013|14116->7014|14167->7036|14431->7271|14461->7272|14504->7286|14534->7287|14591->7315|14621->7316|14668->7334|14705->7342|14735->7343|14786->7365|14868->7418|14898->7419|14953->7445|15129->7592|15159->7593|15218->7623|15315->7691|15345->7692|15396->7714|15426->7715|15473->7733|15503->7734|15546->7748|15576->7749|15673->7818|15713->7819|15758->7836|15831->7892|15871->7893|15917->7910|15947->7911|15994->7929|16029->7936|16063->7948|16117->7974|16135->7982|16190->8015|16272->8068|16302->8069|16389->8128|16429->8129|16476->8147|16540->8166|16590->8184|16652->8201|16695->8215|16735->8226|16765->8227|16802->8233|16836->8240|16901->8288|16941->8289|16973->8293|17037->8329|17064->8334|17095->8336|17125->8337|17168->8351|17204->8358|17234->8359|17281->8377|17338->8405|17368->8406|17419->8428|17449->8429|17496->8447|17547->8470|17580->8481|17623->8495|17653->8496|17704->8518|17734->8519|17781->8537|17822->8550|17871->8577|17927->8604|17957->8605|18008->8627|18068->8658|18098->8659|18141->8673|18171->8674|18222->8696|18252->8697|18299->8715|18360->8747|18390->8748|18441->8770|18496->8796|18526->8797|18587->8829|18617->8830|18668->8852|18740->8895|18770->8896|18825->8922|18986->9054|19016->9055|19063->9073|19093->9074|19136->9088|19166->9089|19220->9114|19250->9115|19297->9133|19348->9155|19378->9156|19429->9178|19693->9413|19723->9414|19766->9428|19796->9429|19853->9457|19883->9458|19930->9476|19967->9484|19997->9485|20048->9507|20130->9560|20160->9561|20215->9587|20391->9734|20421->9735|20480->9765|20577->9833|20607->9834|20658->9856|20688->9857|20735->9875|20765->9876|20808->9890|20838->9891|20937->9962|20977->9963|21022->9980|21097->10038|21137->10039|21183->10056|21213->10057|21260->10075|21295->10082|21329->10094|21383->10120|21406->10133|21461->10166|21543->10219|21573->10220|21662->10281|21702->10282|21749->10300|21813->10319|21863->10337|21925->10354|21968->10368|22008->10379|22038->10380|22075->10386|22109->10392|22326->10581|22355->10582|22413->10611|22443->10612|22474->10615|22650->10762|22680->10763|22712->10767|22778->10805|22807->10806|22838->10809|22888->10830|22918->10831|22950->10835|23052->10909|23081->10910|23111->10912|23140->10913|23172->10917|23232->10948|23262->10949|23293->10952|23511->11141|23541->11142|23573->11146|23647->11192|23676->11193|23707->11196|23767->11227|23797->11228|23829->11232|23934->11309|23963->11310|23993->11312|24022->11313|24052->11315
                  LINES: 28->1|36->5|38->7|38->7|38->7|40->9|40->9|40->9|40->9|41->10|41->10|41->10|42->11|42->11|42->11|43->12|43->12|43->12|45->14|45->14|47->16|50->19|50->19|52->21|87->56|87->56|88->57|88->57|94->63|94->63|94->63|95->64|95->64|95->64|96->65|97->66|99->68|99->68|99->68|100->69|100->69|100->69|101->70|102->71|105->74|105->74|105->74|106->75|107->76|107->76|108->77|108->77|108->77|108->77|110->79|110->79|110->79|111->80|111->80|111->80|112->81|113->82|113->82|114->83|114->83|114->83|114->83|115->84|115->84|115->84|116->85|116->85|116->85|117->86|117->86|117->86|118->87|119->88|122->91|123->92|124->93|130->99|130->99|130->99|133->102|133->102|133->102|134->103|134->103|134->103|134->103|134->103|135->104|136->105|144->113|144->113|144->113|145->114|145->114|145->114|145->114|145->114|146->115|147->116|157->126|157->126|157->126|158->127|158->127|158->127|158->127|158->127|159->128|160->129|168->137|168->137|168->137|169->138|169->138|169->138|169->138|169->138|170->139|171->140|183->152|185->154|187->156|187->156|188->157|189->158|189->158|190->159|191->160|191->160|192->161|192->161|192->161|193->162|196->165|196->165|197->166|197->166|198->167|199->168|199->168|200->169|200->169|202->171|202->171|202->171|203->172|205->174|205->174|206->175|211->180|211->180|212->181|213->182|213->182|214->183|214->183|214->183|216->185|216->185|216->185|217->186|217->186|217->186|217->186|217->186|218->187|218->187|218->187|219->188|220->189|220->189|221->190|221->190|222->191|222->191|222->191|223->192|223->192|224->193|224->193|225->194|225->194|225->194|226->195|226->195|227->196|228->197|228->197|229->198|229->198|230->199|230->199|231->200|232->201|232->201|233->202|234->203|234->203|235->204|235->204|236->205|237->206|237->206|238->207|240->209|240->209|241->210|241->210|242->211|242->211|243->212|243->212|244->213|244->213|244->213|245->214|248->217|248->217|249->218|249->218|250->219|250->219|251->220|251->220|251->220|252->221|253->222|253->222|254->223|256->225|256->225|257->226|258->227|258->227|259->228|259->228|260->229|260->229|261->230|261->230|263->232|263->232|264->233|264->233|264->233|265->234|265->234|266->235|266->235|266->235|267->236|267->236|267->236|270->239|270->239|271->240|271->240|272->241|273->242|274->243|275->244|276->245|277->246|277->246|278->247|280->249|280->249|280->249|281->250|281->250|281->250|281->250|281->250|282->251|282->251|282->251|283->252|284->253|284->253|285->254|285->254|286->255|286->255|286->255|287->256|287->256|288->257|288->257|289->258|289->258|289->258|290->259|290->259|291->260|292->261|292->261|293->262|293->262|294->263|294->263|295->264|296->265|296->265|297->266|298->267|298->267|299->268|299->268|300->269|301->270|301->270|302->271|304->273|304->273|305->274|305->274|306->275|306->275|307->276|307->276|308->277|308->277|308->277|309->278|312->281|312->281|313->282|313->282|314->283|314->283|315->284|315->284|315->284|316->285|317->286|317->286|318->287|320->289|320->289|321->290|322->291|322->291|323->292|323->292|324->293|324->293|325->294|325->294|327->296|327->296|328->297|328->297|328->297|329->298|329->298|330->299|330->299|330->299|331->300|331->300|331->300|334->303|334->303|335->304|335->304|336->305|337->306|338->307|339->308|340->309|341->310|341->310|342->311|344->313|348->317|348->317|350->319|350->319|351->320|353->322|353->322|354->323|355->324|355->324|356->325|356->325|356->325|357->326|359->328|359->328|360->329|360->329|362->331|362->331|362->331|363->332|365->334|365->334|366->335|367->336|367->336|368->337|368->337|368->337|369->338|371->340|371->340|372->341|372->341|374->343
                  -- GENERATED --
              */
          