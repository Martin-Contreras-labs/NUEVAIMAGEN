
package viewsMnuDisponibilidad.html

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

object reportDisponibilidadAll extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Cronograma],String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listaDisponibilidad: List[tables.Cronograma], cartaGantt: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""
	
	"""),format.raw/*7.2*/("""<link rel="stylesheet" media="all" href=""""),_display_(/*7.44*/routes/*7.50*/.Assets.versioned("stylesheets/jsgantt.css")),format.raw/*7.94*/("""">
	<script src=""""),_display_(/*8.16*/routes/*8.22*/.Assets.versioned("javascripts/jsgantt.js")),format.raw/*8.65*/(""""></script>

	"""),_display_(/*10.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*10.51*/("""
	"""),format.raw/*11.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*12.4*/barraTitulo(mapDiccionario, "DISPONIBILIDAD DE EQUIPOS EN "+mapDiccionario.get("BODEGAS")+"/PROYECTOS","")),format.raw/*12.110*/("""
		"""),format.raw/*13.3*/("""<div class="row  justify-content-md-center">
		
			<button type="button"  class="btn btn-sm btn-success" onclick="document.getElementById('excel').submit()">
				Exportar tabla a Excel
			</button>
						
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>SUCURSAL</TH>
							<TH>"""),_display_(/*24.13*/mapDiccionario/*24.27*/.get("BODEGA")),format.raw/*24.41*/("""/PROYECTO</TH>
							<TH>GRUPO/FAMILIA</TH>
							<TH>CODIGO</TH>
							<TH>EQUIPO</TH>
							<TH>UN</TH>
							<TH>CANT</TH>
							<TH>EN PROYECTO DESDE</TH>
							<TH>ULTIMA MODIFICACION</TH>
							<TH>DISPONIBLE A PARTIR DE</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*36.8*/for(lista1 <- listaDisponibilidad) yield /*36.42*/{_display_(Seq[Any](format.raw/*36.43*/("""
							"""),format.raw/*37.8*/("""<TR>
								<TD style= "text-align: left;">"""),_display_(/*38.41*/lista1/*38.47*/.nameSucursal),format.raw/*38.60*/("""</TD>
								<TD style= "text-align: left;">"""),_display_(/*39.41*/lista1/*39.47*/.nombreBodega),format.raw/*39.60*/("""</TD>
								<TD style= "text-align: left;">"""),_display_(/*40.41*/lista1/*40.47*/.nombreGrupo),format.raw/*40.59*/("""</TD>
								<TD style= "text-align: left;">"""),_display_(/*41.41*/lista1/*41.47*/.codEquipo),format.raw/*41.57*/("""</TD>
								<TD style= "text-align: left;">"""),_display_(/*42.41*/lista1/*42.47*/.nombreEquipo),format.raw/*42.60*/("""</TD>
								<TD style= "text-align: center;">"""),_display_(/*43.43*/lista1/*43.49*/.unidad),format.raw/*43.56*/("""</TD>
								<TD style= "text-align: right;">"""),_display_(/*44.42*/lista1/*44.48*/.cantidad),format.raw/*44.57*/("""</TD>
								<TD style= "text-align: center;">
									<div style="display:none">"""),_display_(/*46.37*/utilities/*46.46*/.Fechas.AAMMDD(lista1.fechaDesde)),format.raw/*46.79*/("""</div>
									"""),_display_(/*47.11*/lista1/*47.17*/.fechaDesde),format.raw/*47.28*/("""
								"""),format.raw/*48.9*/("""</TD>
								<TD style= "text-align: center;">
									<div style="display:none">"""),_display_(/*50.37*/utilities/*50.46*/.Fechas.AAMMDD(lista1.fechaActualizacion)),format.raw/*50.87*/("""</div>
									"""),_display_(/*51.11*/lista1/*51.17*/.fechaActualizacion),format.raw/*51.36*/("""
								"""),format.raw/*52.9*/("""</TD>
								<TD style= "text-align: center;">
									<div style="display:none">"""),_display_(/*54.37*/utilities/*54.46*/.Fechas.AAMMDD(lista1.fechaDisponible)),format.raw/*54.84*/("""</div>
									"""),_display_(/*55.11*/lista1/*55.17*/.fechaDisponible),format.raw/*55.33*/("""
								"""),format.raw/*56.9*/("""</TD>
								</TD>
							</TR>
			 			""")))}),format.raw/*59.9*/("""
					"""),format.raw/*60.6*/("""</tbody>
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
				</div>
			</div>
		</div>
		<br><br>
		<div style="position:relative" class="gantt" id="embedded-Gantt"></div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
				</div>
			</div>
		</div>
	</div>
	
	<form id="excel" class="formulario" method="post" action="/reportDisponibilidadAllExcel/"></form>

""")))}),format.raw/*84.2*/("""







"""),format.raw/*92.1*/("""<script type="text/javascript">
	let fechaAux = "";
	$(document).ready(function() """),format.raw/*94.31*/("""{"""),format.raw/*94.32*/("""
		  """),format.raw/*95.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*95.36*/("""{"""),format.raw/*95.37*/("""
		    	"""),format.raw/*96.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 1, "asc" ],[ 3, "asc" ], [ 0, "asc" ]],
		    	"language": """),format.raw/*99.20*/("""{"""),format.raw/*99.21*/("""
		        	"""),format.raw/*100.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*101.11*/("""}"""),format.raw/*101.12*/("""
		  """),format.raw/*102.5*/("""}"""),format.raw/*102.6*/(""" """),format.raw/*102.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*104.2*/("""}"""),format.raw/*104.3*/(""");
	
	var g = new JSGantt.GanttChart(document.getElementById('embedded-Gantt'), 'day');
		if (g.getDivId() != null) """),format.raw/*107.29*/("""{"""),format.raw/*107.30*/("""
				  """),format.raw/*108.7*/("""g.setCaptionType('Complete');  // Set to Show Caption (None,Caption,Resource,Duration,Complete)
				  g.setQuarterColWidth(36);
				  g.setDateTaskDisplayFormat('day dd month yyyy'); // Showntool tip box
				  g.setDayMajorDateDisplayFormat('mon yyyy - Week ww') // Set format to display datesthe "Major" header of the "Day" view
				  g.setWeekMinorDateDisplayFormat('dd mon') // Set format to display datesthe "Minor" header of the "Week" view
				  g.setShowTaskInfoLink(1); // Show linktool tip (0/1)
				  g.setShowEndWeekDate(0); // Show/Hide the date for the last day of the weekheader for daily view (1/0)
				  g.setUseSingleCell(10000); // Set the threshold at which we will only use one cell per table row (0 disables).  Helps with rendering performance for large charts.
				  g.setFormatArr('Day', 'Week', 'Month', 'Quarter'); // Even with setUseSingleCell using Hour format on such a large chart can cause issuessome browsers
		  
		  
				  g.setShowRes(1); // oculta columna de recursos
				  g.setShowComp(0);
				  g.setShowTaskInfoRes(0);
				  g.setShowTaskInfoComp(0);
				  g.setShowTaskInfoDur(0);
				  g.setShowTaskInfoStartDate(1);
				  g.setShowTaskInfoEndDate(0);
				  g.setShowTaskInfoNotes(0);
				  g.setShowDeps(1);
		  
		  		/*
			  		// Parameters                     (pID, 	pName,                  	pStart,       	pEnd,        	pStyle,         	pLink (unused)  	pMile, 	pRes,       pComp, 	pGroup, pParent, 	pOpen, pDepend, pCaption, pNotes, pGantt)
					g.AddTaskItem(new JSGantt.TaskItem(3,   	'Code Javascript',      	'',           	'',          	'ggroupblack',  	'',   				0, 		'bodega',    0,     	2,      0,       	1,     '',      '',      '',      g));
			 		 g.AddTaskItem(new JSGantt.TaskItem(32,		'Despacho',     			'2020-03-06',	'2020-03-06', 	'gtaskred',    		'',     			0, 		'',    		 0,    		0,      3,      	1,     '',      '',      '',      g));
			  		g.AddTaskItem(new JSGantt.TaskItem(33,		'Actualizado',  			'2020-03-11',	'2020-03-11', 	'gtaskblue',    	'',       			0, 		'',    		 0,     	0,      3,      	1,     '32',   	'',      '',      g));
			  		g.AddTaskItem(new JSGantt.TaskItem(34,		'Devolucion',  				'2020-03-18',	'2020-03-18', 	'gtaskgreen',   	'',      			0, 		'',   		 0,     	0,      3,      	1,     '33',   	'',      '',      g));
			  		
			  		g.AddTaskItem(new JSGantt.TaskItem(1,   'Define Chart API',     '',           '',          'ggroupblack',  '',       0, 'Brian',    0,   1, 0,  1, '',      '',      'Some Notes text', g ));
			  	  g.AddTaskItem(new JSGantt.TaskItem(11,  'Chart Object',         '2016-02-20','2016-02-20', 'gmilestone',   '',       1, 'Shlomy',   100, 0, 1,  1, '',      '',      '',      g));
			  	  g.AddTaskItem(new JSGantt.TaskItem(12,  'Task Objects',         '',           '',          'ggroupblack',  '',       0, 'Shlomy',   40,  1, 1,  1, '',      '',      '',      g));
			  	  g.AddTaskItem(new JSGantt.TaskItem(121, 'Constructor Proc',     '2016-02-21','2016-03-09', 'gtaskblue',    '',       0, 'Brian T.', 60,  0, 12, 1, '',      '',      '',      g));
			  	  g.AddTaskItem(new JSGantt.TaskItem(122, 'Task Variables',       '2016-03-06','2016-03-11', 'gtaskred',     '',       0, 'Brian',    60,  0, 12, 1, 121,     '',      '',      g));
			  	  g.AddTaskItem(new JSGantt.TaskItem(123, 'Task by Minute/Hour',  '2016-03-09','2016-03-14 12:00', 'gtaskyellow', '',  0, 'Ilan',     60,  0, 12, 1, '',      '',      '',      g));
			  	  g.AddTaskItem(new JSGantt.TaskItem(124, 'Task Functions',       '2016-03-09','2016-03-29', 'gtaskred',     '',       0, 'Anyone',   60,  0, 12, 1, '123SS', 'This is a caption', null, g));
			  	  g.AddTaskItem(new JSGantt.TaskItem(2,   'Create HTML Shell',    '2016-03-24','2016-03-24', 'gtaskyellow',  '',       0, 'Brian',    20,  0, 0,  1, 122,     '',      '',      g));
			  	  g.AddTaskItem(new JSGantt.TaskItem(3,   'Code Javascript',      '',           '',          'ggroupblack',  '',       0, 'Brian',    0,   1, 0,  1, '',      '',      '',      g));
			  	  g.AddTaskItem(new JSGantt.TaskItem(31,  'Define Variables',     '2016-02-25','2016-03-17', 'gtaskpurple',  '',       0, 'Brian',    30,  0, 3,  1, '',      'Caption 1','',   g));
			  	  g.AddTaskItem(new JSGantt.TaskItem(32,  'Calculate Chart Size', '2016-03-15','2016-03-24', 'gtaskgreen',   '',       0, 'Shlomy',   40,  0, 3,  1, '',      '',      '',      g));
			  	  g.AddTaskItem(new JSGantt.TaskItem(33,  'Draw Task Items',      '',           '',          'ggroupblack',  '',       0, 'Someone',  40,  2, 3,  1, '',      '',      '',      g));
			  	  g.AddTaskItem(new JSGantt.TaskItem(332, 'Task Label Table',     '2016-03-06','2016-03-09', 'gtaskblue',    '',       0, 'Brian',    60,  0, 33, 1, '',      '',      '',      g));
			  	  g.AddTaskItem(new JSGantt.TaskItem(333, 'Task Scrolling Grid',  '2016-03-11','2016-03-20', 'gtaskblue',    '',       0, 'Brian',    0,   0, 33, 1, '332',   '',      '',      g));
			  	  g.AddTaskItem(new JSGantt.TaskItem(34,  'Draw Task Bars',       '',           '',          'ggroupblack',  '',       0, 'Anybody',  60,  1, 3,  0, '',      '',      '',      g));
			  	  g.AddTaskItem(new JSGantt.TaskItem(341, 'Loop each Task',       '2016-03-26','2016-04-11', 'gtaskred',     '',       0, 'Brian',    60,  0, 34, 1, '',      '',      '',      g));
			  	  g.AddTaskItem(new JSGantt.TaskItem(342, 'Calculate Start/Stop', '2016-04-12','2016-05-18', 'gtaskpink',    '',       0, 'Brian',    60,  0, 34, 1, '',      '',      '',      g));
			  	  g.AddTaskItem(new JSGantt.TaskItem(343, 'Draw Task Div',        '2016-05-13','2016-05-17', 'gtaskred',     '',       0, 'Brian',    60,  0, 34, 1, '',      '',      '',      g));
			  	  g.AddTaskItem(new JSGantt.TaskItem(344, 'Draw Completion Div',  '2016-05-17','2016-06-04', 'gtaskred',     '',       0, 'Brian',    60,  0, 34, 1, "342,343",'',     '',      g));
			  	  g.AddTaskItem(new JSGantt.TaskItem(35,  'Make Updates',         '2016-07-17','2017-09-04', 'gtaskpurple',  '',       0, 'Brian',    30,  0, 3,  1, '333',   '',      '',      g));
			  */
		  		"""),_display_(/*157.8*/Html(cartaGantt)),format.raw/*157.24*/("""
		 
		  
		  		"""),format.raw/*160.7*/("""g.Draw();
	  """),format.raw/*161.4*/("""}"""),format.raw/*161.5*/(""" """),format.raw/*161.6*/("""else """),format.raw/*161.11*/("""{"""),format.raw/*161.12*/("""
  		"""),format.raw/*162.5*/("""alert("Error, unable to create Gantt Chart");
	  """),format.raw/*163.4*/("""}"""),format.raw/*163.5*/("""
	
	
	
"""),format.raw/*167.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listaDisponibilidad:List[tables.Cronograma],cartaGantt:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listaDisponibilidad,cartaGantt)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Cronograma],String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listaDisponibilidad,cartaGantt) => apply(mapDiccionario,mapPermiso,userMnu,listaDisponibilidad,cartaGantt)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuDisponibilidad/reportDisponibilidadAll.scala.html
                  HASH: 94b5aeb3681848a1f052b7849870422fb5178ba4
                  MATRIX: 1060->1|1316->164|1349->172|1365->180|1404->182|1434->186|1502->228|1516->234|1580->278|1624->296|1638->302|1701->345|1742->360|1811->408|1840->410|1917->461|2045->567|2075->570|2569->1037|2592->1051|2627->1065|2939->1351|2989->1385|3028->1386|3063->1394|3135->1439|3150->1445|3184->1458|3257->1504|3272->1510|3306->1523|3379->1569|3394->1575|3427->1587|3500->1633|3515->1639|3546->1649|3619->1695|3634->1701|3668->1714|3743->1762|3758->1768|3786->1775|3860->1822|3875->1828|3905->1837|4016->1921|4034->1930|4088->1963|4132->1980|4147->1986|4179->1997|4215->2006|4326->2090|4344->2099|4406->2140|4450->2157|4465->2163|4505->2182|4541->2191|4652->2275|4670->2284|4729->2322|4773->2339|4788->2345|4825->2361|4861->2370|4932->2411|4965->2417|5804->3226|5839->3234|5949->3316|5978->3317|6010->3322|6069->3353|6098->3354|6133->3362|6334->3535|6363->3536|6404->3548|6511->3626|6541->3627|6574->3632|6603->3633|6632->3634|6733->3707|6762->3708|6907->3824|6937->3825|6972->3832|13087->9920|13125->9936|13169->9952|13210->9965|13239->9966|13268->9967|13302->9972|13332->9973|13365->9978|13442->10027|13471->10028|13506->10035
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|38->7|38->7|39->8|39->8|39->8|41->10|41->10|42->11|43->12|43->12|44->13|55->24|55->24|55->24|67->36|67->36|67->36|68->37|69->38|69->38|69->38|70->39|70->39|70->39|71->40|71->40|71->40|72->41|72->41|72->41|73->42|73->42|73->42|74->43|74->43|74->43|75->44|75->44|75->44|77->46|77->46|77->46|78->47|78->47|78->47|79->48|81->50|81->50|81->50|82->51|82->51|82->51|83->52|85->54|85->54|85->54|86->55|86->55|86->55|87->56|90->59|91->60|115->84|123->92|125->94|125->94|126->95|126->95|126->95|127->96|130->99|130->99|131->100|132->101|132->101|133->102|133->102|133->102|135->104|135->104|138->107|138->107|139->108|188->157|188->157|191->160|192->161|192->161|192->161|192->161|192->161|193->162|194->163|194->163|198->167
                  -- GENERATED --
              */
          