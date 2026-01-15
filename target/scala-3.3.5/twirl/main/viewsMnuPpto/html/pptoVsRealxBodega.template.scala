
package viewsMnuPpto.html

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

object pptoVsRealxBodega extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template8[Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,List[List[String]],Long,List[List[String]],String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
bodega: tables.BodegaEmpresa, listPpto: List[List[String]], numDecimales: Long, listGrafico: List[List[String]], moneda: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""
	
"""),format.raw/*7.1*/("""<script src=""""),_display_(/*7.15*/routes/*7.21*/.Assets.versioned("highcharts/highcharts.js")),format.raw/*7.66*/(""""></script>
<script src=""""),_display_(/*8.15*/routes/*8.21*/.Assets.versioned("highcharts/series-label.js")),format.raw/*8.68*/(""""></script>
<script src=""""),_display_(/*9.15*/routes/*9.21*/.Assets.versioned("highcharts/exporting.js")),format.raw/*9.65*/(""""></script>
<script src=""""),_display_(/*10.15*/routes/*10.21*/.Assets.versioned("highcharts/export-data.js")),format.raw/*10.67*/(""""></script>

"""),_display_(/*12.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*12.50*/("""
	"""),format.raw/*13.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*14.4*/barraTitulo(mapDiccionario, "INFORME DE PRESUPUESTO:  "+ bodega.nombre.toUpperCase(),"")),format.raw/*14.92*/("""
		"""),format.raw/*15.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">
				<table class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<tr>
						<td width="30%">
							<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
								<thead style="background-color: #eeeeee">
									<TR>  
									    <TH style= "text-align: center;">AÑO-MES<BR></TH>
										<TH style= "text-align: center;">PPTO</TH>
										<TH style= "text-align: center;">REAL</TH>
									</TR>
								</thead>
								<tbody>
									"""),_display_(/*29.11*/for(lista1 <- listPpto) yield /*29.34*/{_display_(Seq[Any](format.raw/*29.35*/("""
										"""),format.raw/*30.11*/("""<TR>
											<td style= "text-align: center;">"""),_display_(/*31.46*/lista1/*31.52*/.get(0)),format.raw/*31.59*/("""</td>
											<td style= "text-align: right;">"""),_display_(/*32.45*/lista1/*32.51*/.get(1)),format.raw/*32.58*/("""</td>
											<td style= "text-align: right;">"""),_display_(/*33.45*/lista1/*33.51*/.get(3)),format.raw/*33.58*/("""</td>
										</TR>
							 		""")))}),format.raw/*35.12*/("""
								"""),format.raw/*36.9*/("""</tbody>
								<tfoot>
						 			<TR>
										<TH style= "text-align: left;">TOTALES</TH>
										<TH style= "text-align: right;"></TH>
										<th style= "text-align: right;"></th>
									</TR>
					 			</tfoot>
							</table>
						</td>
						<td width="70%" id="graficoPptovsReal" style="height: auto; margin: 0 auto"></td>
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
	
""")))}),format.raw/*60.2*/("""




"""),format.raw/*65.1*/("""<script type="text/javascript">
	$(document).ready(function () """),format.raw/*66.32*/("""{"""),format.raw/*66.33*/("""
            """),format.raw/*67.13*/("""let tabla = document.getElementById("tablaPrincipal");
            let numero = new DecimalFormat("#,##0.00");
            let totPpto = 0;
            let totReal = 0;
            for (let i = 2; i < tabla.rows.length-1; i++) """),format.raw/*71.59*/("""{"""),format.raw/*71.60*/(""" 
				"""),format.raw/*72.5*/("""var aux = tabla.rows[i].cells[1].textContent;
				totPpto = parseFloat(totPpto) + parseFloat(numero.formatBack(aux));
				var aux = tabla.rows[i].cells[2].textContent;
				if(aux!="")"""),format.raw/*75.16*/("""{"""),format.raw/*75.17*/("""
					"""),format.raw/*76.6*/("""totReal = parseFloat(totReal) + parseFloat(numero.formatBack(aux));
				"""),format.raw/*77.5*/("""}"""),format.raw/*77.6*/("""
			"""),format.raw/*78.4*/("""}"""),format.raw/*78.5*/("""
           """),format.raw/*79.12*/("""tabla.rows[tabla.rows.length-1].cells[1].textContent = formatStandar(totPpto,'"""),_display_(/*79.91*/numDecimales),format.raw/*79.103*/("""');
           tabla.rows[tabla.rows.length-1].cells[2].textContent = formatStandar(totReal,'"""),_display_(/*80.91*/numDecimales),format.raw/*80.103*/("""');

			Highcharts.chart('graficoPptovsReal',"""),format.raw/*82.41*/("""{"""),format.raw/*82.42*/("""
	            """),format.raw/*83.14*/("""chart: """),format.raw/*83.21*/("""{"""),format.raw/*83.22*/("""
	                """),format.raw/*84.18*/("""zoomType: 'xy'
	            """),format.raw/*85.14*/("""}"""),format.raw/*85.15*/(""",
	            title: """),format.raw/*86.21*/("""{"""),format.raw/*86.22*/("""
	                """),format.raw/*87.18*/("""text: 'Presupuesto Vs Real'
	            """),format.raw/*88.14*/("""}"""),format.raw/*88.15*/(""",
	            subtitle: """),format.raw/*89.24*/("""{"""),format.raw/*89.25*/("""
	                """),format.raw/*90.18*/("""text: ''
	            """),format.raw/*91.14*/("""}"""),format.raw/*91.15*/(""",
	            xAxis: ["""),format.raw/*92.22*/("""{"""),format.raw/*92.23*/("""
	                """),format.raw/*93.18*/("""categories: """),_display_(/*93.31*/Html(listGrafico.get(0).toString())),format.raw/*93.66*/("""
	            """),format.raw/*94.14*/("""}"""),format.raw/*94.15*/("""],
	            yAxis: ["""),format.raw/*95.22*/("""{"""),format.raw/*95.23*/(""" """),format.raw/*95.24*/("""// Primary yAxis
	            	min: 0,
	                labels: """),format.raw/*97.26*/("""{"""),format.raw/*97.27*/("""
	                	"""),format.raw/*98.19*/("""format: """"),format.raw/*98.28*/("""{"""),format.raw/*98.29*/("""value:point.y:,.0f"""),format.raw/*98.47*/("""}"""),format.raw/*98.48*/(""" """),format.raw/*98.49*/("""",
	                    style: """),format.raw/*99.29*/("""{"""),format.raw/*99.30*/("""
	                        """),format.raw/*100.26*/("""color: Highcharts.getOptions().colors[1]
	                    """),format.raw/*101.22*/("""}"""),format.raw/*101.23*/("""
	                """),format.raw/*102.18*/("""}"""),format.raw/*102.19*/(""",
	                title: """),format.raw/*103.25*/("""{"""),format.raw/*103.26*/("""
	                    """),format.raw/*104.22*/("""text: 'Parcial',
	                    style: """),format.raw/*105.29*/("""{"""),format.raw/*105.30*/("""
	                        """),format.raw/*106.26*/("""color: Highcharts.getOptions().colors[1]
	                    """),format.raw/*107.22*/("""}"""),format.raw/*107.23*/("""
	                """),format.raw/*108.18*/("""}"""),format.raw/*108.19*/("""
	            """),format.raw/*109.14*/("""}"""),format.raw/*109.15*/(""", """),format.raw/*109.17*/("""{"""),format.raw/*109.18*/(""" """),format.raw/*109.19*/("""// Secondary yAxis
	            	min: 0,
	                title: """),format.raw/*111.25*/("""{"""),format.raw/*111.26*/("""
	                    """),format.raw/*112.22*/("""text: 'Acumulado',
	                    style: """),format.raw/*113.29*/("""{"""),format.raw/*113.30*/("""
	                        """),format.raw/*114.26*/("""color: Highcharts.getOptions().colors[0]
	                    """),format.raw/*115.22*/("""}"""),format.raw/*115.23*/("""
	                """),format.raw/*116.18*/("""}"""),format.raw/*116.19*/(""",
	                labels: """),format.raw/*117.26*/("""{"""),format.raw/*117.27*/("""
	                    """),format.raw/*118.22*/("""format: """"),format.raw/*118.31*/("""{"""),format.raw/*118.32*/("""value:point.y:,.0f"""),format.raw/*118.50*/("""}"""),format.raw/*118.51*/(""" """),format.raw/*118.52*/("""",
	                    style: """),format.raw/*119.29*/("""{"""),format.raw/*119.30*/("""
	                        """),format.raw/*120.26*/("""color: Highcharts.getOptions().colors[0]
	                    """),format.raw/*121.22*/("""}"""),format.raw/*121.23*/("""
	                """),format.raw/*122.18*/("""}"""),format.raw/*122.19*/(""",
	                opposite: true
	            """),format.raw/*124.14*/("""}"""),format.raw/*124.15*/("""],
	            tooltip: """),format.raw/*125.23*/("""{"""),format.raw/*125.24*/("""
	                """),format.raw/*126.18*/("""shared: true,
	                
	            """),format.raw/*128.14*/("""}"""),format.raw/*128.15*/(""",
	            legend: """),format.raw/*129.22*/("""{"""),format.raw/*129.23*/("""
	                """),format.raw/*130.18*/("""layout: 'vertical',
	                align: 'left',
	                x: 80,
	                verticalAlign: 'top',
	                y: 20,
	                floating: true,
	                backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'
	            """),format.raw/*137.14*/("""}"""),format.raw/*137.15*/(""",
	            series: ["""),format.raw/*138.23*/("""{"""),format.raw/*138.24*/("""
	                """),format.raw/*139.18*/("""name: 'Parcial Presupuesto',
	                type: 'column',
	                color: '#FFC30F',
	                yAxis: 0,
	                data: """),_display_(/*143.25*/Html(listGrafico.get(1).toString())),format.raw/*143.60*/(""",
	                tooltip: """),format.raw/*144.27*/("""{"""),format.raw/*144.28*/("""
	                	"""),format.raw/*145.19*/("""valuePrefix: '"""),_display_(/*145.34*/moneda),format.raw/*145.40*/("""',
	                """),format.raw/*146.18*/("""}"""),format.raw/*146.19*/("""
	    
	            """),format.raw/*148.14*/("""}"""),format.raw/*148.15*/(""","""),format.raw/*148.16*/("""{"""),format.raw/*148.17*/("""
	                """),format.raw/*149.18*/("""name: 'Acumulado Presupuesto',
	                type: 'spline',
	                color: '#FFC30F',
	                yAxis: 1,
	                data: """),_display_(/*153.25*/Html(listGrafico.get(2).toString())),format.raw/*153.60*/(""",
	                tooltip: """),format.raw/*154.27*/("""{"""),format.raw/*154.28*/("""
	                	"""),format.raw/*155.19*/("""valuePrefix: '"""),_display_(/*155.34*/moneda),format.raw/*155.40*/(""" """),format.raw/*155.41*/("""',
	                """),format.raw/*156.18*/("""}"""),format.raw/*156.19*/("""
	            """),format.raw/*157.14*/("""}"""),format.raw/*157.15*/(""","""),format.raw/*157.16*/("""{"""),format.raw/*157.17*/("""
	                """),format.raw/*158.18*/("""name: 'Parcial Real',
	                type: 'column',
	                color: '#000000',
	                yAxis: 0,
	                data: """),_display_(/*162.25*/Html(listGrafico.get(3).toString())),format.raw/*162.60*/(""",
	                tooltip: """),format.raw/*163.27*/("""{"""),format.raw/*163.28*/("""
	                	"""),format.raw/*164.19*/("""valuePrefix: '"""),_display_(/*164.34*/moneda),format.raw/*164.40*/(""" """),format.raw/*164.41*/("""',
	                """),format.raw/*165.18*/("""}"""),format.raw/*165.19*/("""
	    
	            """),format.raw/*167.14*/("""}"""),format.raw/*167.15*/(""", """),format.raw/*167.17*/("""{"""),format.raw/*167.18*/("""
	                """),format.raw/*168.18*/("""name: 'Acumulado Real',
	                type: 'spline',
	                color: '#000000',
	                yAxis: 1,
	                data: """),_display_(/*172.25*/Html(listGrafico.get(4).toString())),format.raw/*172.60*/(""",
	                tooltip: """),format.raw/*173.27*/("""{"""),format.raw/*173.28*/("""
	                	"""),format.raw/*174.19*/("""valuePrefix: '"""),_display_(/*174.34*/moneda),format.raw/*174.40*/(""" """),format.raw/*174.41*/("""',
	                """),format.raw/*175.18*/("""}"""),format.raw/*175.19*/("""
	            """),format.raw/*176.14*/("""}"""),format.raw/*176.15*/("""]
	        """),format.raw/*177.10*/("""}"""),format.raw/*177.11*/(""");



			document.getElementById('mostrarmostrar').style.display="block";
	   """),format.raw/*182.5*/("""}"""),format.raw/*182.6*/(""");
			  
	
</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,bodega:tables.BodegaEmpresa,listPpto:List[List[String]],numDecimales:Long,listGrafico:List[List[String]],moneda:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,bodega,listPpto,numDecimales,listGrafico,moneda)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,List[List[String]],Long,List[List[String]],String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,bodega,listPpto,numDecimales,listGrafico,moneda) => apply(mapDiccionario,mapPermiso,userMnu,bodega,listPpto,numDecimales,listGrafico,moneda)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuPpto/pptoVsRealxBodega.scala.html
                  HASH: 34a39a68ba593faa42ea5aca5c9a629f4afbc7bf
                  MATRIX: 1084->1|1403->227|1436->235|1452->243|1491->245|1520->248|1560->262|1574->268|1639->313|1691->339|1705->345|1772->392|1824->418|1838->424|1902->468|1955->494|1970->500|2037->546|2077->560|2146->608|2175->610|2252->661|2361->749|2391->752|3049->1383|3088->1406|3127->1407|3166->1418|3243->1468|3258->1474|3286->1481|3363->1531|3378->1537|3406->1544|3483->1594|3498->1600|3526->1607|3590->1640|3626->1649|4348->2341|4380->2346|4471->2409|4500->2410|4541->2423|4796->2650|4825->2651|4858->2657|5069->2840|5098->2841|5131->2847|5230->2919|5258->2920|5289->2924|5317->2925|5357->2937|5463->3016|5497->3028|5618->3122|5652->3134|5725->3179|5754->3180|5796->3194|5831->3201|5860->3202|5906->3220|5962->3248|5991->3249|6041->3271|6070->3272|6116->3290|6185->3331|6214->3332|6267->3357|6296->3358|6342->3376|6392->3398|6421->3399|6472->3422|6501->3423|6547->3441|6587->3454|6643->3489|6685->3503|6714->3504|6766->3528|6795->3529|6824->3530|6916->3594|6945->3595|6992->3614|7029->3623|7058->3624|7104->3642|7133->3643|7162->3644|7221->3675|7250->3676|7305->3702|7396->3764|7426->3765|7473->3783|7503->3784|7558->3810|7588->3811|7639->3833|7713->3878|7743->3879|7798->3905|7889->3967|7919->3968|7966->3986|7996->3987|8039->4001|8069->4002|8100->4004|8130->4005|8160->4006|8254->4071|8284->4072|8335->4094|8411->4141|8441->4142|8496->4168|8587->4230|8617->4231|8664->4249|8694->4250|8750->4277|8780->4278|8831->4300|8869->4309|8899->4310|8946->4328|8976->4329|9006->4330|9066->4361|9096->4362|9151->4388|9242->4450|9272->4451|9319->4469|9349->4470|9425->4517|9455->4518|9509->4543|9539->4544|9586->4562|9660->4607|9690->4608|9742->4631|9772->4632|9819->4650|10141->4943|10171->4944|10224->4968|10254->4969|10301->4987|10477->5135|10534->5170|10591->5198|10621->5199|10669->5218|10712->5233|10740->5239|10789->5259|10819->5260|10868->5280|10898->5281|10928->5282|10958->5283|11005->5301|11183->5451|11240->5486|11297->5514|11327->5515|11375->5534|11418->5549|11446->5555|11476->5556|11525->5576|11555->5577|11598->5591|11628->5592|11658->5593|11688->5594|11735->5612|11904->5753|11961->5788|12018->5816|12048->5817|12096->5836|12139->5851|12167->5857|12197->5858|12246->5878|12276->5879|12325->5899|12355->5900|12386->5902|12416->5903|12463->5921|12634->6064|12691->6099|12748->6127|12778->6128|12826->6147|12869->6162|12897->6168|12927->6169|12976->6189|13006->6190|13049->6204|13079->6205|13119->6216|13149->6217|13255->6295|13284->6296
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|38->7|38->7|39->8|39->8|39->8|40->9|40->9|40->9|41->10|41->10|41->10|43->12|43->12|44->13|45->14|45->14|46->15|60->29|60->29|60->29|61->30|62->31|62->31|62->31|63->32|63->32|63->32|64->33|64->33|64->33|66->35|67->36|91->60|96->65|97->66|97->66|98->67|102->71|102->71|103->72|106->75|106->75|107->76|108->77|108->77|109->78|109->78|110->79|110->79|110->79|111->80|111->80|113->82|113->82|114->83|114->83|114->83|115->84|116->85|116->85|117->86|117->86|118->87|119->88|119->88|120->89|120->89|121->90|122->91|122->91|123->92|123->92|124->93|124->93|124->93|125->94|125->94|126->95|126->95|126->95|128->97|128->97|129->98|129->98|129->98|129->98|129->98|129->98|130->99|130->99|131->100|132->101|132->101|133->102|133->102|134->103|134->103|135->104|136->105|136->105|137->106|138->107|138->107|139->108|139->108|140->109|140->109|140->109|140->109|140->109|142->111|142->111|143->112|144->113|144->113|145->114|146->115|146->115|147->116|147->116|148->117|148->117|149->118|149->118|149->118|149->118|149->118|149->118|150->119|150->119|151->120|152->121|152->121|153->122|153->122|155->124|155->124|156->125|156->125|157->126|159->128|159->128|160->129|160->129|161->130|168->137|168->137|169->138|169->138|170->139|174->143|174->143|175->144|175->144|176->145|176->145|176->145|177->146|177->146|179->148|179->148|179->148|179->148|180->149|184->153|184->153|185->154|185->154|186->155|186->155|186->155|186->155|187->156|187->156|188->157|188->157|188->157|188->157|189->158|193->162|193->162|194->163|194->163|195->164|195->164|195->164|195->164|196->165|196->165|198->167|198->167|198->167|198->167|199->168|203->172|203->172|204->173|204->173|205->174|205->174|205->174|205->174|206->175|206->175|207->176|207->176|208->177|208->177|213->182|213->182
                  -- GENERATED --
              */
          