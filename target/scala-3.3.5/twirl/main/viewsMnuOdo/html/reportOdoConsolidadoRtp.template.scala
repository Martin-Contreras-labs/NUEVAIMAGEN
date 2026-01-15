
package viewsMnuOdo.html

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

object reportOdoConsolidadoRtp extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template9[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String,List[String],List[String],Map[String,List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
datos: List[List[String]], fecha: String, cantMeses: String, categorias: List[String], nameSerie: List[String], mapDataSerie: Map[String,List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	

"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""
	
	"""),format.raw/*7.2*/("""<script src=""""),_display_(/*7.16*/routes/*7.22*/.Assets.versioned("highcharts/highcharts.js")),format.raw/*7.67*/(""""></script>
	<script src=""""),_display_(/*8.16*/routes/*8.22*/.Assets.versioned("highcharts/series-label.js")),format.raw/*8.69*/(""""></script>
	<script src=""""),_display_(/*9.16*/routes/*9.22*/.Assets.versioned("highcharts/exporting.js")),format.raw/*9.66*/(""""></script>
	<script src=""""),_display_(/*10.16*/routes/*10.22*/.Assets.versioned("highcharts/export-data.js")),format.raw/*10.68*/(""""></script>

	"""),_display_(/*12.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*12.51*/("""
	"""),format.raw/*13.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*14.4*/barraTitulo(mapDiccionario, "REPORTE ODO CONSOLIDADO DE EP/PROFORMAS","POR "+cantMeses+" MESES VS " + mapDiccionario("BODEGA")+"/PROYECTO")),format.raw/*14.143*/("""

		"""),format.raw/*16.3*/("""<div class="noprint">
			<table class="table table-sm table-condensed table-fluid">
				<tr>
					<td width="25%">
						<div class="input-group">
					  		<div class="input-group-prepend">
					    		<span class="input-group-text" id="basic-addon1">BUSCAR</span>
					  		</div>
					  		<input type="text" class="form-control left"
								id="searchTermtablaPrincipal"
								onkeyup="doSearch2('tablaPrincipal');">
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
		
		<form id="excel" class="formulario" method="post" action="/reportOdoConsolidadoRtpExcel/">
			<input type="hidden" name="fecha" value=""""),_display_(/*49.46*/fecha),format.raw/*49.51*/("""">
			<input type="hidden" name="cantMeses" value=""""),_display_(/*50.50*/cantMeses),format.raw/*50.59*/("""">
		</form>

		<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
	  		<thead style="background-color: #eeeeee" class="header">
	  			<TR>
					"""),_display_(/*56.7*/for(titulos <- datos.get(0)) yield /*56.35*/{_display_(Seq[Any](format.raw/*56.36*/("""
						"""),format.raw/*57.7*/("""<th style="text-align:center;">"""),_display_(/*57.39*/titulos),format.raw/*57.46*/("""</th>
					""")))}),format.raw/*58.7*/("""
				"""),format.raw/*59.5*/("""</TR>
	  		</thead>
			<tbody>
			"""),_display_(/*62.5*/for(lista <- datos.tail) yield /*62.29*/{_display_(Seq[Any](format.raw/*62.30*/("""
				"""),format.raw/*63.5*/("""<TR>
					<td style="text-align:left;">"""),_display_(/*64.36*/Html(lista.get(0))),format.raw/*64.54*/("""</td>
					<td style="text-align:left;">"""),_display_(/*65.36*/Html(lista.get(1))),format.raw/*65.54*/("""</td>
					"""),_display_(/*66.7*/for(lista2 <- lista.tail.tail) yield /*66.37*/{_display_(Seq[Any](format.raw/*66.38*/("""
						"""),format.raw/*67.7*/("""<td style="text-align:right;">"""),_display_(/*67.38*/lista2),format.raw/*67.44*/("""</td>
					""")))}),format.raw/*68.7*/("""
				"""),format.raw/*69.5*/("""</TR>
			""")))}),format.raw/*70.5*/("""
			"""),format.raw/*71.4*/("""</tbody>
		</table>
			
		<table id="grafico" class="table table-sm table-bordered table-condensed table-fluid">
			<tr>
				<TD style="width:100%; margin: 0 0">
					<div id="graficoConsolidado" style="height:600px; width:100%; "></div>
				</TD>
			</tr>
		 </table>
	</div>
			  
""")))}),format.raw/*83.2*/("""

"""),format.raw/*85.1*/("""<script type="text/javascript">
	$(document).ready(function () """),format.raw/*86.32*/("""{"""),format.raw/*86.33*/("""
	
		

		
		"""),format.raw/*91.3*/("""Highcharts.chart('graficoConsolidado',"""),format.raw/*91.41*/("""{"""),format.raw/*91.42*/("""
            """),format.raw/*92.13*/("""chart: """),format.raw/*92.20*/("""{"""),format.raw/*92.21*/("""
                """),format.raw/*93.17*/("""type: 'column'
            """),format.raw/*94.13*/("""}"""),format.raw/*94.14*/(""",
            title: """),format.raw/*95.20*/("""{"""),format.raw/*95.21*/("""
                """),format.raw/*96.17*/("""text: 'CONSOLIDADO DE PROFORMAS'
            """),format.raw/*97.13*/("""}"""),format.raw/*97.14*/(""",
            xAxis: """),format.raw/*98.20*/("""{"""),format.raw/*98.21*/("""
                """),format.raw/*99.17*/("""categories: """),_display_(/*99.30*/Html(categorias.toString())),format.raw/*99.57*/(""",
                labels: """),format.raw/*100.25*/("""{"""),format.raw/*100.26*/("""
                    """),format.raw/*101.21*/("""rotation: -45
                """),format.raw/*102.17*/("""}"""),format.raw/*102.18*/("""
            """),format.raw/*103.13*/("""}"""),format.raw/*103.14*/(""",
            yAxis: """),format.raw/*104.20*/("""{"""),format.raw/*104.21*/("""
                """),format.raw/*105.17*/("""min: 0,
                title: """),format.raw/*106.24*/("""{"""),format.raw/*106.25*/("""
                    """),format.raw/*107.21*/("""text: ''
                """),format.raw/*108.17*/("""}"""),format.raw/*108.18*/(""",
                stackLabels: """),format.raw/*109.30*/("""{"""),format.raw/*109.31*/("""
                    """),format.raw/*110.21*/("""enabled: true,
                    style: """),format.raw/*111.28*/("""{"""),format.raw/*111.29*/("""
                        """),format.raw/*112.25*/("""fontWeight: 'bold',
                        color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
                    """),format.raw/*114.21*/("""}"""),format.raw/*114.22*/("""
                """),format.raw/*115.17*/("""}"""),format.raw/*115.18*/("""
            """),format.raw/*116.13*/("""}"""),format.raw/*116.14*/(""", 
            tooltip: """),format.raw/*117.22*/("""{"""),format.raw/*117.23*/("""
                """),format.raw/*118.17*/("""formatter: function() """),format.raw/*118.39*/("""{"""),format.raw/*118.40*/("""
                    """),format.raw/*119.21*/("""return '<b>'+ this.x +'</b><br/>'+
                        this.series.name +' $: '+ Highcharts.numberFormat(this.y,0);
                """),format.raw/*121.17*/("""}"""),format.raw/*121.18*/("""
            """),format.raw/*122.13*/("""}"""),format.raw/*122.14*/(""",
            series: [
	            """),_display_(/*124.15*/for((lista,index) <- nameSerie.zipWithIndex) yield /*124.59*/{_display_(Seq[Any](format.raw/*124.60*/("""
	            	"""),format.raw/*125.15*/("""{"""),format.raw/*125.16*/("""
		            	"""),format.raw/*126.16*/("""name: """),_display_(/*126.23*/Html(lista.toString())),format.raw/*126.45*/(""",
		            	data: """),_display_(/*127.23*/Html(mapDataSerie.get(lista).toString())),format.raw/*127.63*/("""
		            """),format.raw/*128.15*/("""}"""),format.raw/*128.16*/("""
	            	"""),_display_(if(index < nameSerie.size()-1)/*129.46*/{_display_(Seq[Any](format.raw/*129.47*/("""
	            		"""),format.raw/*130.16*/(""",
	            	""")))} else {null} ),format.raw/*131.16*/("""
	            """)))}),format.raw/*132.15*/("""
            """),format.raw/*133.13*/("""]
        """),format.raw/*134.9*/("""}"""),format.raw/*134.10*/(""");
			
		document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*137.2*/("""}"""),format.raw/*137.3*/(""");

</script>
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,datos:List[List[String]],fecha:String,cantMeses:String,categorias:List[String],nameSerie:List[String],mapDataSerie:Map[String,List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,datos,fecha,cantMeses,categorias,nameSerie,mapDataSerie)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String,List[String],List[String],Map[String,List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,datos,fecha,cantMeses,categorias,nameSerie,mapDataSerie) => apply(mapDiccionario,mapPermiso,userMnu,datos,fecha,cantMeses,categorias,nameSerie,mapDataSerie)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuOdo/reportOdoConsolidadoRtp.scala.html
                  HASH: 20aa25aeff8b9c6dd4c63f4717cbfa3b2f1eadb3
                  MATRIX: 1102->1|1444->250|1477->258|1493->266|1532->268|1562->272|1602->286|1616->292|1681->337|1734->364|1748->370|1815->417|1868->444|1882->450|1946->494|2000->521|2015->527|2082->573|2123->588|2192->636|2221->638|2298->689|2459->828|2490->832|3650->1965|3676->1970|3755->2022|3785->2031|4012->2232|4056->2260|4095->2261|4129->2268|4188->2300|4216->2307|4258->2319|4290->2324|4351->2359|4391->2383|4430->2384|4462->2389|4529->2429|4568->2447|4636->2488|4675->2506|4713->2518|4759->2548|4798->2549|4832->2556|4890->2587|4917->2593|4959->2605|4991->2610|5031->2620|5062->2624|5376->2908|5405->2910|5496->2973|5525->2974|5564->2986|5630->3024|5659->3025|5700->3038|5735->3045|5764->3046|5809->3063|5864->3090|5893->3091|5942->3112|5971->3113|6016->3130|6089->3175|6118->3176|6167->3197|6196->3198|6241->3215|6281->3228|6329->3255|6384->3281|6414->3282|6464->3303|6523->3333|6553->3334|6595->3347|6625->3348|6675->3369|6705->3370|6751->3387|6811->3418|6841->3419|6891->3440|6945->3465|6975->3466|7035->3497|7065->3498|7115->3519|7186->3561|7216->3562|7270->3587|7429->3717|7459->3718|7505->3735|7535->3736|7577->3749|7607->3750|7660->3774|7690->3775|7736->3792|7787->3814|7817->3815|7867->3836|8032->3972|8062->3973|8104->3986|8134->3987|8200->4025|8261->4069|8301->4070|8345->4085|8375->4086|8420->4102|8455->4109|8499->4131|8551->4155|8613->4195|8657->4210|8687->4211|8761->4257|8801->4258|8846->4274|8908->4291|8955->4306|8997->4319|9035->4329|9065->4330|9168->4405|9197->4406
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|38->7|38->7|39->8|39->8|39->8|40->9|40->9|40->9|41->10|41->10|41->10|43->12|43->12|44->13|45->14|45->14|47->16|80->49|80->49|81->50|81->50|87->56|87->56|87->56|88->57|88->57|88->57|89->58|90->59|93->62|93->62|93->62|94->63|95->64|95->64|96->65|96->65|97->66|97->66|97->66|98->67|98->67|98->67|99->68|100->69|101->70|102->71|114->83|116->85|117->86|117->86|122->91|122->91|122->91|123->92|123->92|123->92|124->93|125->94|125->94|126->95|126->95|127->96|128->97|128->97|129->98|129->98|130->99|130->99|130->99|131->100|131->100|132->101|133->102|133->102|134->103|134->103|135->104|135->104|136->105|137->106|137->106|138->107|139->108|139->108|140->109|140->109|141->110|142->111|142->111|143->112|145->114|145->114|146->115|146->115|147->116|147->116|148->117|148->117|149->118|149->118|149->118|150->119|152->121|152->121|153->122|153->122|155->124|155->124|155->124|156->125|156->125|157->126|157->126|157->126|158->127|158->127|159->128|159->128|160->129|160->129|161->130|162->131|163->132|164->133|165->134|165->134|168->137|168->137
                  -- GENERATED --
              */
          