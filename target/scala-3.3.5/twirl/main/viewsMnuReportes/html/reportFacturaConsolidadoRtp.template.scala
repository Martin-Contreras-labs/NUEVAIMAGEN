
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

object reportFacturaConsolidadoRtp extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template9[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String,List[String],List[String],Map[String,List[String]],play.twirl.api.HtmlFormat.Appendable] {

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
	
	"""),format.raw/*14.2*/("""<div id="enCarga" class="blocker" style="display: block;"><br><br><br><br><br><br><h1>Ahora se esta cargando.....</h1></div>
	
	<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*17.4*/barraTitulo(mapDiccionario, "REPORTE CONSOLIDADO DE EP/PROFORMAS","POR "+cantMeses+" MESES VS " + mapDiccionario("BODEGA")+"/PROYECTO")),format.raw/*17.139*/("""

		"""),format.raw/*19.3*/("""<div class="noprint">
			<table class="table table-sm table-condensed table-fluid">
				<tr>
					<td width="25%">
						<div class="input-group">
					  		<div class="input-group-prepend">
					    		<span class="input-group-text" id="basic-addon1">BUSCAR</span>
					  		</div>
					  		<input type="text" class="form-control left"
								id="searchTermtablaPrincipal"
								onkeyup="doSearch2_conTot('tablaPrincipal'); sumarFiltro('tablaPrincipal')">
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
		
		<form id="excel" class="formulario" method="post" action="/reportFacturaConsolidadoRtpExcel/">
			<input type="hidden" name="fecha" value=""""),_display_(/*52.46*/fecha),format.raw/*52.51*/("""">
			<input type="hidden" name="cantMeses" value=""""),_display_(/*53.50*/cantMeses),format.raw/*53.59*/("""">
		</form>

		<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
	  		<thead style="background-color: #eeeeee" class="header">
	  			<TR>
					"""),_display_(/*59.7*/for(titulos <- datos.get(0)) yield /*59.35*/{_display_(Seq[Any](format.raw/*59.36*/("""
						"""),format.raw/*60.7*/("""<th style="text-align:center;">"""),_display_(/*60.39*/titulos),format.raw/*60.46*/("""</th>
					""")))}),format.raw/*61.7*/("""
				"""),format.raw/*62.5*/("""</TR>
	  		</thead>
			<tbody>
			"""),_display_(/*65.5*/for(lista <- datos.tail) yield /*65.29*/{_display_(Seq[Any](format.raw/*65.30*/("""
				"""),format.raw/*66.5*/("""<TR>
					<td style="text-align:left;">"""),_display_(/*67.36*/Html(lista.get(0))),format.raw/*67.54*/("""</td>
					<td style="text-align:left;">"""),_display_(/*68.36*/Html(lista.get(1))),format.raw/*68.54*/("""</td>
					"""),_display_(/*69.7*/for(lista2 <- lista.tail.tail) yield /*69.37*/{_display_(Seq[Any](format.raw/*69.38*/("""
						"""),format.raw/*70.7*/("""<td style="text-align:right;">"""),_display_(/*70.38*/lista2),format.raw/*70.44*/("""</td>
					""")))}),format.raw/*71.7*/("""
				"""),format.raw/*72.5*/("""</TR>
			""")))}),format.raw/*73.5*/("""
			"""),format.raw/*74.4*/("""</tbody>
		</table>
			
		<table id="grafico" class="table table-sm table-bordered table-condensed table-fluid">
			<tr>
				<TD style="width:100%; margin: 0 0">
					<div id="graficoConsolidado" style="height:600px; width:100%; "></div>
				</TD>
			</tr>
		 </table>
	</div>
			  
""")))}),format.raw/*86.2*/("""

"""),format.raw/*88.1*/("""<script type="text/javascript">

		function sumarFiltro(tabla)"""),format.raw/*90.30*/("""{"""),format.raw/*90.31*/("""
			"""),format.raw/*91.4*/("""var tableReg = document.getElementById(tabla);
			for(var j = 1; j < tableReg.rows[0].cells.length; j++)"""),format.raw/*92.58*/("""{"""),format.raw/*92.59*/("""
				"""),format.raw/*93.5*/("""var acum = 0;
				for (var i = 1; i < tableReg.rows.length - 1; i++)"""),format.raw/*94.55*/("""{"""),format.raw/*94.56*/("""
					"""),format.raw/*95.6*/("""if(tableReg.rows[i].style.display != 'none')"""),format.raw/*95.50*/("""{"""),format.raw/*95.51*/("""
						"""),format.raw/*96.7*/("""var aux = tableReg.rows[i].cells[j].innerHTML;
						aux = aux.replace(/,/g,'');
						acum += parseFloat(aux);
					"""),format.raw/*99.6*/("""}"""),format.raw/*99.7*/("""
				"""),format.raw/*100.5*/("""}"""),format.raw/*100.6*/("""
				"""),format.raw/*101.5*/("""tableReg.rows[tableReg.rows.length - 1].cells[j].innerHTML = formatStandar(acum,0);
			"""),format.raw/*102.4*/("""}"""),format.raw/*102.5*/("""
		"""),format.raw/*103.3*/("""}"""),format.raw/*103.4*/("""
		
	"""),format.raw/*105.2*/("""$(document).ready(function () """),format.raw/*105.32*/("""{"""),format.raw/*105.33*/("""
		
		"""),format.raw/*107.3*/("""$('#tablaPrincipal').DataTable("""),format.raw/*107.34*/("""{"""),format.raw/*107.35*/("""
		    	"""),format.raw/*108.8*/(""""fixedHeader": true,
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [[ 1, "asc" ]],
		    	"language": """),format.raw/*113.20*/("""{"""),format.raw/*113.21*/("""
		        	"""),format.raw/*114.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*115.11*/("""}"""),format.raw/*115.12*/("""
		  """),format.raw/*116.5*/("""}"""),format.raw/*116.6*/(""" """),format.raw/*116.7*/(""");

		
		Highcharts.chart('graficoConsolidado',"""),format.raw/*119.41*/("""{"""),format.raw/*119.42*/("""
            """),format.raw/*120.13*/("""chart: """),format.raw/*120.20*/("""{"""),format.raw/*120.21*/("""
                """),format.raw/*121.17*/("""type: 'column'
            """),format.raw/*122.13*/("""}"""),format.raw/*122.14*/(""",
            title: """),format.raw/*123.20*/("""{"""),format.raw/*123.21*/("""
                """),format.raw/*124.17*/("""text: 'CONSOLIDADO DE PROFORMAS'
            """),format.raw/*125.13*/("""}"""),format.raw/*125.14*/(""",
            xAxis: """),format.raw/*126.20*/("""{"""),format.raw/*126.21*/("""
                """),format.raw/*127.17*/("""categories: """),_display_(/*127.30*/Html(categorias.toString())),format.raw/*127.57*/(""",
                labels: """),format.raw/*128.25*/("""{"""),format.raw/*128.26*/("""
                    """),format.raw/*129.21*/("""rotation: -45
                """),format.raw/*130.17*/("""}"""),format.raw/*130.18*/("""
            """),format.raw/*131.13*/("""}"""),format.raw/*131.14*/(""",
            yAxis: """),format.raw/*132.20*/("""{"""),format.raw/*132.21*/("""
                """),format.raw/*133.17*/("""min: 0,
                title: """),format.raw/*134.24*/("""{"""),format.raw/*134.25*/("""
                    """),format.raw/*135.21*/("""text: ''
                """),format.raw/*136.17*/("""}"""),format.raw/*136.18*/(""",
                stackLabels: """),format.raw/*137.30*/("""{"""),format.raw/*137.31*/("""
                    """),format.raw/*138.21*/("""enabled: true,
                    style: """),format.raw/*139.28*/("""{"""),format.raw/*139.29*/("""
                        """),format.raw/*140.25*/("""fontWeight: 'bold',
                        color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
                    """),format.raw/*142.21*/("""}"""),format.raw/*142.22*/("""
                """),format.raw/*143.17*/("""}"""),format.raw/*143.18*/("""
            """),format.raw/*144.13*/("""}"""),format.raw/*144.14*/(""", 
            tooltip: """),format.raw/*145.22*/("""{"""),format.raw/*145.23*/("""
                """),format.raw/*146.17*/("""formatter: function() """),format.raw/*146.39*/("""{"""),format.raw/*146.40*/("""
                    """),format.raw/*147.21*/("""return '<b>'+ this.x +'</b><br/>'+
                        this.series.name +' $: '+ Highcharts.numberFormat(this.y,0);
                """),format.raw/*149.17*/("""}"""),format.raw/*149.18*/("""
            """),format.raw/*150.13*/("""}"""),format.raw/*150.14*/(""",
            series: [
	            """),_display_(/*152.15*/for((lista,index) <- nameSerie.zipWithIndex) yield /*152.59*/{_display_(Seq[Any](format.raw/*152.60*/("""
	            	"""),format.raw/*153.15*/("""{"""),format.raw/*153.16*/("""
		            	"""),format.raw/*154.16*/("""name: """),_display_(/*154.23*/Html(lista.toString())),format.raw/*154.45*/(""",
		            	data: """),_display_(/*155.23*/Html(mapDataSerie.get(lista).toString())),format.raw/*155.63*/("""
		            """),format.raw/*156.15*/("""}"""),format.raw/*156.16*/("""
	            	"""),_display_(if(index < nameSerie.size()-1)/*157.46*/{_display_(Seq[Any](format.raw/*157.47*/("""
	            		"""),format.raw/*158.16*/(""",
	            	""")))} else {null} ),format.raw/*159.16*/("""
	            """)))}),format.raw/*160.15*/("""
            """),format.raw/*161.13*/("""]
        """),format.raw/*162.9*/("""}"""),format.raw/*162.10*/(""");
			
		document.getElementById('enProceso').style.display="none";
		document.getElementById('mostrarmostrar').style.display="block";
		document.getElementById('enCarga').style.display="none";
	"""),format.raw/*167.2*/("""}"""),format.raw/*167.3*/(""");

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
                  SOURCE: app/viewsMnuReportes/reportFacturaConsolidadoRtp.scala.html
                  HASH: 431458f4249d46c5c8d306446494feabcb4e354c
                  MATRIX: 1111->1|1453->250|1486->258|1502->266|1541->268|1571->272|1611->286|1625->292|1690->337|1743->364|1757->370|1824->417|1877->444|1891->450|1955->494|2009->521|2024->527|2091->573|2132->588|2201->636|2232->640|2437->819|2594->954|2625->958|3826->2132|3852->2137|3931->2189|3961->2198|4188->2399|4232->2427|4271->2428|4305->2435|4364->2467|4392->2474|4434->2486|4466->2491|4527->2526|4567->2550|4606->2551|4638->2556|4705->2596|4744->2614|4812->2655|4851->2673|4889->2685|4935->2715|4974->2716|5008->2723|5066->2754|5093->2760|5135->2772|5167->2777|5207->2787|5238->2791|5552->3075|5581->3077|5671->3139|5700->3140|5731->3144|5863->3248|5892->3249|5924->3254|6020->3322|6049->3323|6082->3329|6154->3373|6183->3374|6217->3381|6361->3498|6389->3499|6422->3504|6451->3505|6484->3510|6599->3597|6628->3598|6659->3601|6688->3602|6721->3607|6780->3637|6810->3638|6844->3644|6904->3675|6934->3676|6970->3684|7138->3823|7168->3824|7209->3836|7316->3914|7346->3915|7379->3920|7408->3921|7437->3922|7513->3969|7543->3970|7585->3983|7621->3990|7651->3991|7697->4008|7753->4035|7783->4036|7833->4057|7863->4058|7909->4075|7983->4120|8013->4121|8063->4142|8093->4143|8139->4160|8180->4173|8229->4200|8284->4226|8314->4227|8364->4248|8423->4278|8453->4279|8495->4292|8525->4293|8575->4314|8605->4315|8651->4332|8711->4363|8741->4364|8791->4385|8845->4410|8875->4411|8935->4442|8965->4443|9015->4464|9086->4506|9116->4507|9170->4532|9329->4662|9359->4663|9405->4680|9435->4681|9477->4694|9507->4695|9560->4719|9590->4720|9636->4737|9687->4759|9717->4760|9767->4781|9932->4917|9962->4918|10004->4931|10034->4932|10100->4970|10161->5014|10201->5015|10245->5030|10275->5031|10320->5047|10355->5054|10399->5076|10451->5100|10513->5140|10557->5155|10587->5156|10661->5202|10701->5203|10746->5219|10808->5236|10855->5251|10897->5264|10935->5274|10965->5275|11188->5470|11217->5471
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|38->7|38->7|39->8|39->8|39->8|40->9|40->9|40->9|41->10|41->10|41->10|43->12|43->12|45->14|48->17|48->17|50->19|83->52|83->52|84->53|84->53|90->59|90->59|90->59|91->60|91->60|91->60|92->61|93->62|96->65|96->65|96->65|97->66|98->67|98->67|99->68|99->68|100->69|100->69|100->69|101->70|101->70|101->70|102->71|103->72|104->73|105->74|117->86|119->88|121->90|121->90|122->91|123->92|123->92|124->93|125->94|125->94|126->95|126->95|126->95|127->96|130->99|130->99|131->100|131->100|132->101|133->102|133->102|134->103|134->103|136->105|136->105|136->105|138->107|138->107|138->107|139->108|144->113|144->113|145->114|146->115|146->115|147->116|147->116|147->116|150->119|150->119|151->120|151->120|151->120|152->121|153->122|153->122|154->123|154->123|155->124|156->125|156->125|157->126|157->126|158->127|158->127|158->127|159->128|159->128|160->129|161->130|161->130|162->131|162->131|163->132|163->132|164->133|165->134|165->134|166->135|167->136|167->136|168->137|168->137|169->138|170->139|170->139|171->140|173->142|173->142|174->143|174->143|175->144|175->144|176->145|176->145|177->146|177->146|177->146|178->147|180->149|180->149|181->150|181->150|183->152|183->152|183->152|184->153|184->153|185->154|185->154|185->154|186->155|186->155|187->156|187->156|188->157|188->157|189->158|190->159|191->160|192->161|193->162|193->162|198->167|198->167
                  -- GENERATED --
              */
          