
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

object reportGraficoMovPorGrupo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template10[Map[String,String],Map[String,String],utilities.UserMnu,List[String],List[String],List[tables.Grupo],List[tables.BodegaEmpresa],String,String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
valorizado: List[String], porUnidades: List[String], listGrupos: List[tables.Grupo], listBodegas: List[tables.BodegaEmpresa], vistaCheckBox: String, idTipoBodega: String, subtitulo: String):play.twirl.api.HtmlFormat.Appendable = {
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
				
					  		<form id="emitirGrafico" action="/refreshGraficoMovPorGrupo/"""),_display_(/*26.71*/idTipoBodega),format.raw/*26.83*/("""" method="POST">
						  		<div id="checkBox">
							  		<h6>GRUPOS:</h6>
								  		"""),_display_(/*29.14*/for(lista <- listGrupos) yield /*29.38*/{_display_(Seq[Any](format.raw/*29.39*/("""
											"""),format.raw/*30.12*/("""<input type="hidden" name="listIdGruposAll[]" value=""""),_display_(/*30.66*/lista/*30.71*/.getId()),format.raw/*30.79*/("""">
								  			<input type="checkbox" name="listIdGrupos[]" value=""""),_display_(/*31.67*/lista/*31.72*/.getId()),format.raw/*31.80*/("""" checked>"""),_display_(/*31.91*/lista/*31.96*/.getNombre()),format.raw/*31.108*/("""<br>
								  		""")))}),format.raw/*32.14*/("""
							  		"""),format.raw/*33.12*/("""<br>
					  				<h6>"""),_display_(/*34.17*/mapDiccionario/*34.31*/.get("BODEGAS")),format.raw/*34.46*/(""":</h6>
								  		"""),_display_(/*35.14*/for(lista <- listBodegas) yield /*35.39*/{_display_(Seq[Any](format.raw/*35.40*/("""
											"""),format.raw/*36.12*/("""<input type="hidden" name="listIdBodegasAll[]" value=""""),_display_(/*36.67*/lista/*36.72*/.getId()),format.raw/*36.80*/("""">
								  			<input type="checkbox" name="listIdBodegas[]" value=""""),_display_(/*37.68*/lista/*37.73*/.getId()),format.raw/*37.81*/("""" checked>"""),_display_(/*37.92*/lista/*37.97*/.getNombre()),format.raw/*37.109*/("""<br>
								  		""")))}),format.raw/*38.14*/("""
									"""),format.raw/*39.10*/("""<br>
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
""")))}),format.raw/*63.2*/("""




"""),format.raw/*68.1*/("""<script type="text/javascript">
	$(document).ready(function () """),format.raw/*69.32*/("""{"""),format.raw/*69.33*/("""
		
		"""),format.raw/*71.3*/("""if(""""),_display_(/*71.8*/Html(vistaCheckBox)),format.raw/*71.27*/(""""!=0)"""),format.raw/*71.32*/("""{"""),format.raw/*71.33*/("""
				  """),format.raw/*72.7*/("""document.getElementById('checkBox').innerHTML=""""),_display_(/*72.55*/Html(vistaCheckBox)),format.raw/*72.74*/("""";
			  """),format.raw/*73.6*/("""}"""),format.raw/*73.7*/("""	

			"""),format.raw/*75.4*/("""Highcharts.chart('valorizadoGrupoBodegas',"""),format.raw/*75.46*/("""{"""),format.raw/*75.47*/("""
	            """),format.raw/*76.14*/("""chart: """),format.raw/*76.21*/("""{"""),format.raw/*76.22*/("""
                	"""),format.raw/*77.18*/("""type: 'column'
	            """),format.raw/*78.14*/("""}"""),format.raw/*78.15*/(""",
	            title: """),format.raw/*79.21*/("""{"""),format.raw/*79.22*/("""
	                """),format.raw/*80.18*/("""text: 'DISTRIBUCION PORCENTUAL POR VALORIZACION'
	            """),format.raw/*81.14*/("""}"""),format.raw/*81.15*/(""",
	            xAxis: """),format.raw/*82.21*/("""{"""),format.raw/*82.22*/("""
	            	"""),format.raw/*83.15*/("""categories: """),_display_(/*83.28*/Html(valorizado.get(0))),format.raw/*83.51*/(""",
	                labels: """),format.raw/*84.26*/("""{"""),format.raw/*84.27*/("""
	                    """),format.raw/*85.22*/("""rotation: -45,
	                    align: 'right',
	                    style: """),format.raw/*87.29*/("""{"""),format.raw/*87.30*/("""
	                        """),format.raw/*88.26*/("""fontSize: '10px',
	                        fontFamily: 'Verdana, sans-serif'
	                    """),format.raw/*90.22*/("""}"""),format.raw/*90.23*/("""
	                """),format.raw/*91.18*/("""}"""),format.raw/*91.19*/("""
	            """),format.raw/*92.14*/("""}"""),format.raw/*92.15*/(""",
	            yAxis: """),format.raw/*93.21*/("""{"""),format.raw/*93.22*/("""
	                """),format.raw/*94.18*/("""min: 0,
					max:100,
	                title: """),format.raw/*96.25*/("""{"""),format.raw/*96.26*/("""
	                    """),format.raw/*97.22*/("""text: 'Porcentaje'
	                """),format.raw/*98.18*/("""}"""),format.raw/*98.19*/(""",
	                stackLabels: """),format.raw/*99.31*/("""{"""),format.raw/*99.32*/("""
	                    """),format.raw/*100.22*/("""enabled: false,
	                    style: """),format.raw/*101.29*/("""{"""),format.raw/*101.30*/("""
	                        """),format.raw/*102.26*/("""fontWeight: 'bold',
	                        color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
	                    """),format.raw/*104.22*/("""}"""),format.raw/*104.23*/("""
	                """),format.raw/*105.18*/("""}"""),format.raw/*105.19*/("""
	            """),format.raw/*106.14*/("""}"""),format.raw/*106.15*/(""",
	            legend: """),format.raw/*107.22*/("""{"""),format.raw/*107.23*/("""
	            	"""),format.raw/*108.15*/("""layout: 'horizontal',
	                align: 'center',
	                verticalAlign: 'top',
	                y: 30,
	                floating: false,
	                backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
	                borderColor: '#CCC',
	                borderWidth: 1,
	                shadow: false
	            """),format.raw/*117.14*/("""}"""),format.raw/*117.15*/(""",
	            tooltip: """),format.raw/*118.23*/("""{"""),format.raw/*118.24*/("""
	                """),format.raw/*119.18*/("""formatter: function() """),format.raw/*119.40*/("""{"""),format.raw/*119.41*/("""
	                    """),format.raw/*120.22*/("""return '<b>'+ this.x +'</b><br/>'+
	                        this.series.name +' M$: '+ Highcharts.numberFormat(this.y,0) +'<br/>'+
	                        'Total M$: '+ Highcharts.numberFormat(this.point.stackTotal,0);
	                """),format.raw/*123.18*/("""}"""),format.raw/*123.19*/("""
	            """),format.raw/*124.14*/("""}"""),format.raw/*124.15*/(""",
	            plotOptions: """),format.raw/*125.27*/("""{"""),format.raw/*125.28*/("""
	                """),format.raw/*126.18*/("""column: """),format.raw/*126.26*/("""{"""),format.raw/*126.27*/("""
	                    """),format.raw/*127.22*/("""stacking: 'normal',
	                    dataLabels: """),format.raw/*128.34*/("""{"""),format.raw/*128.35*/("""
	                        """),format.raw/*129.26*/("""enabled: false,
	                        color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
	                        style: """),format.raw/*131.33*/("""{"""),format.raw/*131.34*/("""
	                            """),format.raw/*132.30*/("""textShadow: '0 0 3px black, 0 0 3px black'
	                        """),format.raw/*133.26*/("""}"""),format.raw/*133.27*/("""
	                    """),format.raw/*134.22*/("""}"""),format.raw/*134.23*/("""
	                """),format.raw/*135.18*/("""}"""),format.raw/*135.19*/("""
	            """),format.raw/*136.14*/("""}"""),format.raw/*136.15*/(""",
	            series: """),_display_(/*137.23*/Html(valorizado.get(1))),format.raw/*137.46*/("""
	        """),format.raw/*138.10*/("""}"""),format.raw/*138.11*/(""");

			Highcharts.chart('porUnidadesGrupoBodegas',"""),format.raw/*140.47*/("""{"""),format.raw/*140.48*/("""
	            """),format.raw/*141.14*/("""chart: """),format.raw/*141.21*/("""{"""),format.raw/*141.22*/("""
                	"""),format.raw/*142.18*/("""type: 'column'
	            """),format.raw/*143.14*/("""}"""),format.raw/*143.15*/(""",
	            title: """),format.raw/*144.21*/("""{"""),format.raw/*144.22*/("""
	                """),format.raw/*145.18*/("""text: 'DISTRIBUCION PORCENTUAL POR UNIDADES'
	            """),format.raw/*146.14*/("""}"""),format.raw/*146.15*/(""",
	            xAxis: """),format.raw/*147.21*/("""{"""),format.raw/*147.22*/("""
	            	"""),format.raw/*148.15*/("""categories: """),_display_(/*148.28*/Html(porUnidades.get(0))),format.raw/*148.52*/(""",
	                labels: """),format.raw/*149.26*/("""{"""),format.raw/*149.27*/("""
	                    """),format.raw/*150.22*/("""rotation: -45,
	                    align: 'right',
	                    style: """),format.raw/*152.29*/("""{"""),format.raw/*152.30*/("""
	                        """),format.raw/*153.26*/("""fontSize: '10px',
	                        fontFamily: 'Verdana, sans-serif'
	                    """),format.raw/*155.22*/("""}"""),format.raw/*155.23*/("""
	                """),format.raw/*156.18*/("""}"""),format.raw/*156.19*/("""
	            """),format.raw/*157.14*/("""}"""),format.raw/*157.15*/(""",
	            yAxis: """),format.raw/*158.21*/("""{"""),format.raw/*158.22*/("""
	                """),format.raw/*159.18*/("""min: 0,
					max:100,
	                title: """),format.raw/*161.25*/("""{"""),format.raw/*161.26*/("""
	                    """),format.raw/*162.22*/("""text: 'Porcentaje'
	                """),format.raw/*163.18*/("""}"""),format.raw/*163.19*/(""",
	                stackLabels: """),format.raw/*164.31*/("""{"""),format.raw/*164.32*/("""
	                    """),format.raw/*165.22*/("""enabled: false,
	                    style: """),format.raw/*166.29*/("""{"""),format.raw/*166.30*/("""
	                        """),format.raw/*167.26*/("""fontWeight: 'bold',
	                        color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
	                    """),format.raw/*169.22*/("""}"""),format.raw/*169.23*/("""
	                """),format.raw/*170.18*/("""}"""),format.raw/*170.19*/("""
	            """),format.raw/*171.14*/("""}"""),format.raw/*171.15*/(""",
	            legend: """),format.raw/*172.22*/("""{"""),format.raw/*172.23*/("""
	            	"""),format.raw/*173.15*/("""layout: 'horizontal',
	                align: 'center',
	                verticalAlign: 'top',
	                y: 30,
	                floating: false,
	                backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
	                borderColor: '#CCC',
	                borderWidth: 1,
	                shadow: false
	            """),format.raw/*182.14*/("""}"""),format.raw/*182.15*/(""",
	            tooltip: """),format.raw/*183.23*/("""{"""),format.raw/*183.24*/("""
	            	"""),format.raw/*184.15*/("""formatter: function() """),format.raw/*184.37*/("""{"""),format.raw/*184.38*/("""
	                    """),format.raw/*185.22*/("""return '<b>'+ this.x +'</b><br/>'+
	                        this.series.name +'  '+ Highcharts.numberFormat(this.y,0) +' % <br/>'+
	                        'Total '+ Highcharts.numberFormat(this.point.stackTotal,0)+' %';
	                """),format.raw/*188.18*/("""}"""),format.raw/*188.19*/("""
	            """),format.raw/*189.14*/("""}"""),format.raw/*189.15*/(""",
	            plotOptions: """),format.raw/*190.27*/("""{"""),format.raw/*190.28*/("""
	                """),format.raw/*191.18*/("""column: """),format.raw/*191.26*/("""{"""),format.raw/*191.27*/("""
	                    """),format.raw/*192.22*/("""stacking: 'normal',
	                    dataLabels: """),format.raw/*193.34*/("""{"""),format.raw/*193.35*/("""
	                        """),format.raw/*194.26*/("""enabled: false,
	                        color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
	                        style: """),format.raw/*196.33*/("""{"""),format.raw/*196.34*/("""
	                            """),format.raw/*197.30*/("""textShadow: '0 0 3px black, 0 0 3px black'
	                        """),format.raw/*198.26*/("""}"""),format.raw/*198.27*/("""
	                    """),format.raw/*199.22*/("""}"""),format.raw/*199.23*/("""
	                """),format.raw/*200.18*/("""}"""),format.raw/*200.19*/("""
	            """),format.raw/*201.14*/("""}"""),format.raw/*201.15*/(""",
	            series: """),_display_(/*202.23*/Html(porUnidades.get(1))),format.raw/*202.47*/("""
	        """),format.raw/*203.10*/("""}"""),format.raw/*203.11*/(""");

			document.getElementById('mostrarmostrar').style.display="block";
	   """),format.raw/*206.5*/("""}"""),format.raw/*206.6*/(""");
			  
	
</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,valorizado:List[String],porUnidades:List[String],listGrupos:List[tables.Grupo],listBodegas:List[tables.BodegaEmpresa],vistaCheckBox:String,idTipoBodega:String,subtitulo:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,valorizado,porUnidades,listGrupos,listBodegas,vistaCheckBox,idTipoBodega,subtitulo)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[String],List[String],List[tables.Grupo],List[tables.BodegaEmpresa],String,String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,valorizado,porUnidades,listGrupos,listBodegas,vistaCheckBox,idTipoBodega,subtitulo) => apply(mapDiccionario,mapPermiso,userMnu,valorizado,porUnidades,listGrupos,listBodegas,vistaCheckBox,idTipoBodega,subtitulo)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reportGraficoMovPorGrupo.scala.html
                  HASH: a3bbcee39d4a749f41be5b86e7e96062ea218607
                  MATRIX: 1118->1|1498->288|1531->296|1547->304|1586->306|1616->310|1656->324|1670->330|1735->375|1788->402|1802->408|1869->455|1922->482|1936->488|2000->532|2054->559|2069->565|2136->611|2177->626|2246->674|2277->678|2354->729|2458->812|2491->818|3100->1400|3133->1412|3248->1500|3288->1524|3327->1525|3367->1537|3448->1591|3462->1596|3491->1604|3587->1673|3601->1678|3630->1686|3668->1697|3682->1702|3716->1714|3765->1732|3805->1744|3853->1765|3876->1779|3912->1794|3959->1814|4000->1839|4039->1840|4079->1852|4161->1907|4175->1912|4204->1920|4301->1990|4315->1995|4344->2003|4382->2014|4396->2019|4430->2031|4479->2049|4517->2059|5192->2704|5224->2709|5315->2772|5344->2773|5377->2779|5408->2784|5448->2803|5481->2808|5510->2809|5544->2816|5619->2864|5659->2883|5694->2891|5722->2892|5755->2898|5825->2940|5854->2941|5896->2955|5931->2962|5960->2963|6006->2981|6062->3009|6091->3010|6141->3032|6170->3033|6216->3051|6306->3113|6335->3114|6385->3136|6414->3137|6457->3152|6497->3165|6541->3188|6596->3215|6625->3216|6675->3238|6783->3318|6812->3319|6866->3345|6992->3443|7021->3444|7067->3462|7096->3463|7138->3477|7167->3478|7217->3500|7246->3501|7292->3519|7366->3565|7395->3566|7445->3588|7509->3624|7538->3625|7598->3657|7627->3658|7678->3680|7751->3724|7781->3725|7836->3751|7997->3883|8027->3884|8074->3902|8104->3903|8147->3917|8177->3918|8229->3941|8259->3942|8303->3957|8697->4322|8727->4323|8780->4347|8810->4348|8857->4366|8908->4388|8938->4389|8989->4411|9255->4648|9285->4649|9328->4663|9358->4664|9415->4692|9445->4693|9492->4711|9529->4719|9559->4720|9610->4742|9692->4795|9722->4796|9777->4822|9953->4969|9983->4970|10042->5000|10139->5068|10169->5069|10220->5091|10250->5092|10297->5110|10327->5111|10370->5125|10400->5126|10452->5150|10497->5173|10536->5183|10566->5184|10645->5234|10675->5235|10718->5249|10754->5256|10784->5257|10831->5275|10888->5303|10918->5304|10969->5326|10999->5327|11046->5345|11133->5403|11163->5404|11214->5426|11244->5427|11288->5442|11329->5455|11375->5479|11431->5506|11461->5507|11512->5529|11621->5609|11651->5610|11706->5636|11833->5734|11863->5735|11910->5753|11940->5754|11983->5768|12013->5769|12064->5791|12094->5792|12141->5810|12216->5856|12246->5857|12297->5879|12362->5915|12392->5916|12453->5948|12483->5949|12534->5971|12607->6015|12637->6016|12692->6042|12853->6174|12883->6175|12930->6193|12960->6194|13003->6208|13033->6209|13085->6232|13115->6233|13159->6248|13553->6613|13583->6614|13636->6638|13666->6639|13710->6654|13761->6676|13791->6677|13842->6699|14109->6937|14139->6938|14182->6952|14212->6953|14269->6981|14299->6982|14346->7000|14383->7008|14413->7009|14464->7031|14546->7084|14576->7085|14631->7111|14807->7258|14837->7259|14896->7289|14993->7357|15023->7358|15074->7380|15104->7381|15151->7399|15181->7400|15224->7414|15254->7415|15306->7439|15352->7463|15391->7473|15421->7474|15525->7550|15554->7551
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|38->7|38->7|39->8|39->8|39->8|40->9|40->9|40->9|41->10|41->10|41->10|43->12|43->12|45->14|46->15|46->15|48->17|57->26|57->26|60->29|60->29|60->29|61->30|61->30|61->30|61->30|62->31|62->31|62->31|62->31|62->31|62->31|63->32|64->33|65->34|65->34|65->34|66->35|66->35|66->35|67->36|67->36|67->36|67->36|68->37|68->37|68->37|68->37|68->37|68->37|69->38|70->39|94->63|99->68|100->69|100->69|102->71|102->71|102->71|102->71|102->71|103->72|103->72|103->72|104->73|104->73|106->75|106->75|106->75|107->76|107->76|107->76|108->77|109->78|109->78|110->79|110->79|111->80|112->81|112->81|113->82|113->82|114->83|114->83|114->83|115->84|115->84|116->85|118->87|118->87|119->88|121->90|121->90|122->91|122->91|123->92|123->92|124->93|124->93|125->94|127->96|127->96|128->97|129->98|129->98|130->99|130->99|131->100|132->101|132->101|133->102|135->104|135->104|136->105|136->105|137->106|137->106|138->107|138->107|139->108|148->117|148->117|149->118|149->118|150->119|150->119|150->119|151->120|154->123|154->123|155->124|155->124|156->125|156->125|157->126|157->126|157->126|158->127|159->128|159->128|160->129|162->131|162->131|163->132|164->133|164->133|165->134|165->134|166->135|166->135|167->136|167->136|168->137|168->137|169->138|169->138|171->140|171->140|172->141|172->141|172->141|173->142|174->143|174->143|175->144|175->144|176->145|177->146|177->146|178->147|178->147|179->148|179->148|179->148|180->149|180->149|181->150|183->152|183->152|184->153|186->155|186->155|187->156|187->156|188->157|188->157|189->158|189->158|190->159|192->161|192->161|193->162|194->163|194->163|195->164|195->164|196->165|197->166|197->166|198->167|200->169|200->169|201->170|201->170|202->171|202->171|203->172|203->172|204->173|213->182|213->182|214->183|214->183|215->184|215->184|215->184|216->185|219->188|219->188|220->189|220->189|221->190|221->190|222->191|222->191|222->191|223->192|224->193|224->193|225->194|227->196|227->196|228->197|229->198|229->198|230->199|230->199|231->200|231->200|232->201|232->201|233->202|233->202|234->203|234->203|237->206|237->206
                  -- GENERATED --
              */
          