
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

object reportGraficoMovResumen extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template8[Map[String,String],Map[String,String],utilities.UserMnu,List[String],List[String],List[tables.Grupo],String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
valorizado: List[String], porUnidades: List[String], listGrupos: List[tables.Grupo], vistaCheckBox: String, subtitulo: String):play.twirl.api.HtmlFormat.Appendable = {
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
		"""),_display_(/*15.4*/barraTitulo(mapDiccionario, "DISTRIBUCION ACTUAL - UBICACION PORCENTUAL",subtitulo)),format.raw/*15.87*/("""
		
		"""),format.raw/*17.3*/("""<br><br>
		<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<table id="ESQUEMAGENERAL" class="table table-sm table-bordered table-condensed table-fluid">
					<TR style="min-height:100% ¡required">
				  		<TD width="45%" id="valorizadoGrupoBodegas" style="height: auto; margin: 0 0"></TD>
				  		<TD width="45%" id="porUnidadesGrupoBodegas" style="height: auto; margin: 0 0"></TD>
				  		<TD width="15%" style="vertical-align:top; height:800px;">
				
					  		<form id="emitirGrafico" action="/refreshGraficoMovResumen/" method="POST">
						  		<div id="checkBox">
							  		<h6>GRUPOS:</h6>
								  		"""),_display_(/*29.14*/for(lista <- listGrupos) yield /*29.38*/{_display_(Seq[Any](format.raw/*29.39*/("""
								  			"""),format.raw/*30.14*/("""<input type="checkbox" name="listIdGrupos[]" value=""""),_display_(/*30.67*/lista/*30.72*/.getId),format.raw/*30.78*/("""" checked>"""),_display_(/*30.89*/lista/*30.94*/.getNombre()),format.raw/*30.106*/("""<br>
								  		""")))}),format.raw/*31.14*/("""
							  		"""),format.raw/*32.12*/("""<br>
							  	</div>
					  		</form>
					  		<div align="center">
					  			<button type="button" class="btn btn-success btn-mini rounded-pill btn-blocki" 
					  				onclick="document.getElementById('emitirGrafico').submit()">
									APLICAR
					  		</div>
					
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
""")))}),format.raw/*56.2*/("""




"""),format.raw/*61.1*/("""<script type="text/javascript">
	$(document).ready(function () """),format.raw/*62.32*/("""{"""),format.raw/*62.33*/("""
		
		"""),format.raw/*64.3*/("""if(""""),_display_(/*64.8*/Html(vistaCheckBox)),format.raw/*64.27*/(""""!=0)"""),format.raw/*64.32*/("""{"""),format.raw/*64.33*/("""
				  """),format.raw/*65.7*/("""document.getElementById('checkBox').innerHTML=""""),_display_(/*65.55*/Html(vistaCheckBox)),format.raw/*65.74*/("""";
			  """),format.raw/*66.6*/("""}"""),format.raw/*66.7*/("""	

			"""),format.raw/*68.4*/("""Highcharts.chart('valorizadoGrupoBodegas',"""),format.raw/*68.46*/("""{"""),format.raw/*68.47*/("""
	            """),format.raw/*69.14*/("""chart: """),format.raw/*69.21*/("""{"""),format.raw/*69.22*/("""
                	"""),format.raw/*70.18*/("""type: 'column'
	            """),format.raw/*71.14*/("""}"""),format.raw/*71.15*/(""",
	            title: """),format.raw/*72.21*/("""{"""),format.raw/*72.22*/("""
	                """),format.raw/*73.18*/("""text: 'DISTRIBUCION PORCENTUAL POR VALORIZACION'
	            """),format.raw/*74.14*/("""}"""),format.raw/*74.15*/(""",
	            xAxis: """),format.raw/*75.21*/("""{"""),format.raw/*75.22*/("""
	            	"""),format.raw/*76.15*/("""categories: """),_display_(/*76.28*/Html(valorizado.get(0))),format.raw/*76.51*/(""",
	                labels: """),format.raw/*77.26*/("""{"""),format.raw/*77.27*/("""
	                    """),format.raw/*78.22*/("""rotation: -45,
	                    align: 'right',
	                    style: """),format.raw/*80.29*/("""{"""),format.raw/*80.30*/("""
	                        """),format.raw/*81.26*/("""fontSize: '10px',
	                        fontFamily: 'Verdana, sans-serif'
	                    """),format.raw/*83.22*/("""}"""),format.raw/*83.23*/("""
	                """),format.raw/*84.18*/("""}"""),format.raw/*84.19*/("""
	            """),format.raw/*85.14*/("""}"""),format.raw/*85.15*/(""",
	            yAxis: """),format.raw/*86.21*/("""{"""),format.raw/*86.22*/("""
	                """),format.raw/*87.18*/("""min: 0,
					max:100,
	                title: """),format.raw/*89.25*/("""{"""),format.raw/*89.26*/("""
	                    """),format.raw/*90.22*/("""text: 'Porcentaje'
	                """),format.raw/*91.18*/("""}"""),format.raw/*91.19*/(""",
	                stackLabels: """),format.raw/*92.31*/("""{"""),format.raw/*92.32*/("""
	                    """),format.raw/*93.22*/("""enabled: false,
	                    style: """),format.raw/*94.29*/("""{"""),format.raw/*94.30*/("""
	                        """),format.raw/*95.26*/("""fontWeight: 'bold',
	                        color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
	                    """),format.raw/*97.22*/("""}"""),format.raw/*97.23*/("""
	                """),format.raw/*98.18*/("""}"""),format.raw/*98.19*/("""
	            """),format.raw/*99.14*/("""}"""),format.raw/*99.15*/(""",
	            legend: """),format.raw/*100.22*/("""{"""),format.raw/*100.23*/("""
	            	"""),format.raw/*101.15*/("""layout: 'horizontal',
	                align: 'center',
	                verticalAlign: 'top',
	                y: 30,
	                floating: false,
	                backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
	                borderColor: '#CCC',
	                borderWidth: 1,
	                shadow: false
	            """),format.raw/*110.14*/("""}"""),format.raw/*110.15*/(""",
	            tooltip: """),format.raw/*111.23*/("""{"""),format.raw/*111.24*/("""
	                """),format.raw/*112.18*/("""formatter: function() """),format.raw/*112.40*/("""{"""),format.raw/*112.41*/("""
	                    """),format.raw/*113.22*/("""return '<b>'+ this.x +'</b><br/>'+
	                        this.series.name +' M$: '+ Highcharts.numberFormat(this.y,0) +'<br/>'+
	                        'Total M$: '+ Highcharts.numberFormat(this.point.stackTotal,0);
	                """),format.raw/*116.18*/("""}"""),format.raw/*116.19*/("""
	            """),format.raw/*117.14*/("""}"""),format.raw/*117.15*/(""",
	            plotOptions: """),format.raw/*118.27*/("""{"""),format.raw/*118.28*/("""
	                """),format.raw/*119.18*/("""column: """),format.raw/*119.26*/("""{"""),format.raw/*119.27*/("""
	                    """),format.raw/*120.22*/("""stacking: 'normal',
	                    dataLabels: """),format.raw/*121.34*/("""{"""),format.raw/*121.35*/("""
	                        """),format.raw/*122.26*/("""enabled: false,
	                        color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
	                        style: """),format.raw/*124.33*/("""{"""),format.raw/*124.34*/("""
	                            """),format.raw/*125.30*/("""textShadow: '0 0 3px black, 0 0 3px black'
	                        """),format.raw/*126.26*/("""}"""),format.raw/*126.27*/("""
	                    """),format.raw/*127.22*/("""}"""),format.raw/*127.23*/("""
	                """),format.raw/*128.18*/("""}"""),format.raw/*128.19*/("""
	            """),format.raw/*129.14*/("""}"""),format.raw/*129.15*/(""",
	            series: """),_display_(/*130.23*/Html(valorizado.get(1))),format.raw/*130.46*/("""
	        """),format.raw/*131.10*/("""}"""),format.raw/*131.11*/(""");

			Highcharts.chart('porUnidadesGrupoBodegas',"""),format.raw/*133.47*/("""{"""),format.raw/*133.48*/("""
	            """),format.raw/*134.14*/("""chart: """),format.raw/*134.21*/("""{"""),format.raw/*134.22*/("""
                	"""),format.raw/*135.18*/("""type: 'column'
	            """),format.raw/*136.14*/("""}"""),format.raw/*136.15*/(""",
	            title: """),format.raw/*137.21*/("""{"""),format.raw/*137.22*/("""
	                """),format.raw/*138.18*/("""text: 'DISTRIBUCION PORCENTUAL POR UNIDADES'
	            """),format.raw/*139.14*/("""}"""),format.raw/*139.15*/(""",
	            xAxis: """),format.raw/*140.21*/("""{"""),format.raw/*140.22*/("""
	            	"""),format.raw/*141.15*/("""categories: """),_display_(/*141.28*/Html(porUnidades.get(0))),format.raw/*141.52*/(""",
	                labels: """),format.raw/*142.26*/("""{"""),format.raw/*142.27*/("""
	                    """),format.raw/*143.22*/("""rotation: -45,
	                    align: 'right',
	                    style: """),format.raw/*145.29*/("""{"""),format.raw/*145.30*/("""
	                        """),format.raw/*146.26*/("""fontSize: '10px',
	                        fontFamily: 'Verdana, sans-serif'
	                    """),format.raw/*148.22*/("""}"""),format.raw/*148.23*/("""
	                """),format.raw/*149.18*/("""}"""),format.raw/*149.19*/("""
	            """),format.raw/*150.14*/("""}"""),format.raw/*150.15*/(""",
	            yAxis: """),format.raw/*151.21*/("""{"""),format.raw/*151.22*/("""
	                """),format.raw/*152.18*/("""min: 0,
					max:100,
	                title: """),format.raw/*154.25*/("""{"""),format.raw/*154.26*/("""
	                    """),format.raw/*155.22*/("""text: 'Porcentaje'
	                """),format.raw/*156.18*/("""}"""),format.raw/*156.19*/(""",
	                stackLabels: """),format.raw/*157.31*/("""{"""),format.raw/*157.32*/("""
	                    """),format.raw/*158.22*/("""enabled: false,
	                    style: """),format.raw/*159.29*/("""{"""),format.raw/*159.30*/("""
	                        """),format.raw/*160.26*/("""fontWeight: 'bold',
	                        color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
	                    """),format.raw/*162.22*/("""}"""),format.raw/*162.23*/("""
	                """),format.raw/*163.18*/("""}"""),format.raw/*163.19*/("""
	            """),format.raw/*164.14*/("""}"""),format.raw/*164.15*/(""",
	            legend: """),format.raw/*165.22*/("""{"""),format.raw/*165.23*/("""
	            	"""),format.raw/*166.15*/("""layout: 'horizontal',
	                align: 'center',
	                verticalAlign: 'top',
	                y: 30,
	                floating: false,
	                backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
	                borderColor: '#CCC',
	                borderWidth: 1,
	                shadow: false
	            """),format.raw/*175.14*/("""}"""),format.raw/*175.15*/(""",
	            tooltip: """),format.raw/*176.23*/("""{"""),format.raw/*176.24*/("""
	            	"""),format.raw/*177.15*/("""formatter: function() """),format.raw/*177.37*/("""{"""),format.raw/*177.38*/("""
	                    """),format.raw/*178.22*/("""return '<b>'+ this.x +'</b><br/>'+
	                        this.series.name +'  '+ Highcharts.numberFormat(this.y,0) +' % <br/>'+
	                        'Total '+ Highcharts.numberFormat(this.point.stackTotal,0)+' %';
	                """),format.raw/*181.18*/("""}"""),format.raw/*181.19*/("""
	            """),format.raw/*182.14*/("""}"""),format.raw/*182.15*/(""",
	            plotOptions: """),format.raw/*183.27*/("""{"""),format.raw/*183.28*/("""
	                """),format.raw/*184.18*/("""column: """),format.raw/*184.26*/("""{"""),format.raw/*184.27*/("""
	                    """),format.raw/*185.22*/("""stacking: 'normal',
	                    dataLabels: """),format.raw/*186.34*/("""{"""),format.raw/*186.35*/("""
	                        """),format.raw/*187.26*/("""enabled: false,
	                        color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
	                        style: """),format.raw/*189.33*/("""{"""),format.raw/*189.34*/("""
	                            """),format.raw/*190.30*/("""textShadow: '0 0 3px black, 0 0 3px black'
	                        """),format.raw/*191.26*/("""}"""),format.raw/*191.27*/("""
	                    """),format.raw/*192.22*/("""}"""),format.raw/*192.23*/("""
	                """),format.raw/*193.18*/("""}"""),format.raw/*193.19*/("""
	            """),format.raw/*194.14*/("""}"""),format.raw/*194.15*/(""",
	            series: """),_display_(/*195.23*/Html(porUnidades.get(1))),format.raw/*195.47*/("""
	        """),format.raw/*196.10*/("""}"""),format.raw/*196.11*/(""");

			document.getElementById('mostrarmostrar').style.display="block";
	   """),format.raw/*199.5*/("""}"""),format.raw/*199.6*/(""");
			  
	
</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,valorizado:List[String],porUnidades:List[String],listGrupos:List[tables.Grupo],vistaCheckBox:String,subtitulo:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,valorizado,porUnidades,listGrupos,vistaCheckBox,subtitulo)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[String],List[String],List[tables.Grupo],String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,valorizado,porUnidades,listGrupos,vistaCheckBox,subtitulo) => apply(mapDiccionario,mapPermiso,userMnu,valorizado,porUnidades,listGrupos,vistaCheckBox,subtitulo)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reportGraficoMovResumen.scala.html
                  HASH: 343835bb845742da52bf2b181c59d9a7423c8713
                  MATRIX: 1082->1|1399->225|1432->233|1448->241|1487->243|1517->247|1557->261|1571->267|1636->312|1689->339|1703->345|1770->392|1823->419|1837->425|1901->469|1955->496|1970->502|2037->548|2078->563|2147->611|2178->615|2255->666|2359->749|2392->755|3087->1423|3127->1447|3166->1448|3208->1462|3288->1515|3302->1520|3329->1526|3367->1537|3381->1542|3415->1554|3464->1572|3504->1584|4179->2229|4211->2234|4302->2297|4331->2298|4364->2304|4395->2309|4435->2328|4468->2333|4497->2334|4531->2341|4606->2389|4646->2408|4681->2416|4709->2417|4742->2423|4812->2465|4841->2466|4883->2480|4918->2487|4947->2488|4993->2506|5049->2534|5078->2535|5128->2557|5157->2558|5203->2576|5293->2638|5322->2639|5372->2661|5401->2662|5444->2677|5484->2690|5528->2713|5583->2740|5612->2741|5662->2763|5770->2843|5799->2844|5853->2870|5979->2968|6008->2969|6054->2987|6083->2988|6125->3002|6154->3003|6204->3025|6233->3026|6279->3044|6353->3090|6382->3091|6432->3113|6496->3149|6525->3150|6585->3182|6614->3183|6664->3205|6736->3249|6765->3250|6819->3276|6979->3408|7008->3409|7054->3427|7083->3428|7125->3442|7154->3443|7206->3466|7236->3467|7280->3482|7674->3847|7704->3848|7757->3872|7787->3873|7834->3891|7885->3913|7915->3914|7966->3936|8232->4173|8262->4174|8305->4188|8335->4189|8392->4217|8422->4218|8469->4236|8506->4244|8536->4245|8587->4267|8669->4320|8699->4321|8754->4347|8930->4494|8960->4495|9019->4525|9116->4593|9146->4594|9197->4616|9227->4617|9274->4635|9304->4636|9347->4650|9377->4651|9429->4675|9474->4698|9513->4708|9543->4709|9622->4759|9652->4760|9695->4774|9731->4781|9761->4782|9808->4800|9865->4828|9895->4829|9946->4851|9976->4852|10023->4870|10110->4928|10140->4929|10191->4951|10221->4952|10265->4967|10306->4980|10352->5004|10408->5031|10438->5032|10489->5054|10598->5134|10628->5135|10683->5161|10810->5259|10840->5260|10887->5278|10917->5279|10960->5293|10990->5294|11041->5316|11071->5317|11118->5335|11193->5381|11223->5382|11274->5404|11339->5440|11369->5441|11430->5473|11460->5474|11511->5496|11584->5540|11614->5541|11669->5567|11830->5699|11860->5700|11907->5718|11937->5719|11980->5733|12010->5734|12062->5757|12092->5758|12136->5773|12530->6138|12560->6139|12613->6163|12643->6164|12687->6179|12738->6201|12768->6202|12819->6224|13086->6462|13116->6463|13159->6477|13189->6478|13246->6506|13276->6507|13323->6525|13360->6533|13390->6534|13441->6556|13523->6609|13553->6610|13608->6636|13784->6783|13814->6784|13873->6814|13970->6882|14000->6883|14051->6905|14081->6906|14128->6924|14158->6925|14201->6939|14231->6940|14283->6964|14329->6988|14368->6998|14398->6999|14502->7075|14531->7076
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|38->7|38->7|39->8|39->8|39->8|40->9|40->9|40->9|41->10|41->10|41->10|43->12|43->12|45->14|46->15|46->15|48->17|60->29|60->29|60->29|61->30|61->30|61->30|61->30|61->30|61->30|61->30|62->31|63->32|87->56|92->61|93->62|93->62|95->64|95->64|95->64|95->64|95->64|96->65|96->65|96->65|97->66|97->66|99->68|99->68|99->68|100->69|100->69|100->69|101->70|102->71|102->71|103->72|103->72|104->73|105->74|105->74|106->75|106->75|107->76|107->76|107->76|108->77|108->77|109->78|111->80|111->80|112->81|114->83|114->83|115->84|115->84|116->85|116->85|117->86|117->86|118->87|120->89|120->89|121->90|122->91|122->91|123->92|123->92|124->93|125->94|125->94|126->95|128->97|128->97|129->98|129->98|130->99|130->99|131->100|131->100|132->101|141->110|141->110|142->111|142->111|143->112|143->112|143->112|144->113|147->116|147->116|148->117|148->117|149->118|149->118|150->119|150->119|150->119|151->120|152->121|152->121|153->122|155->124|155->124|156->125|157->126|157->126|158->127|158->127|159->128|159->128|160->129|160->129|161->130|161->130|162->131|162->131|164->133|164->133|165->134|165->134|165->134|166->135|167->136|167->136|168->137|168->137|169->138|170->139|170->139|171->140|171->140|172->141|172->141|172->141|173->142|173->142|174->143|176->145|176->145|177->146|179->148|179->148|180->149|180->149|181->150|181->150|182->151|182->151|183->152|185->154|185->154|186->155|187->156|187->156|188->157|188->157|189->158|190->159|190->159|191->160|193->162|193->162|194->163|194->163|195->164|195->164|196->165|196->165|197->166|206->175|206->175|207->176|207->176|208->177|208->177|208->177|209->178|212->181|212->181|213->182|213->182|214->183|214->183|215->184|215->184|215->184|216->185|217->186|217->186|218->187|220->189|220->189|221->190|222->191|222->191|223->192|223->192|224->193|224->193|225->194|225->194|226->195|226->195|227->196|227->196|230->199|230->199
                  -- GENERATED --
              */
          