
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

object reporteGerencialVentas extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template9[Map[String,String],Map[String,String],utilities.UserMnu,List[String],Long,Long,Long,List[List[String]],String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
series: List[String], yearActual: Long, yearAnterior: Long, yearAntAnterior: Long, tabla: List[List[String]], titulo: String):play.twirl.api.HtmlFormat.Appendable = {
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
		"""),_display_(/*15.4*/barraTitulo(mapDiccionario, "REPORTE DE VENTAS (NETAS)",titulo)),format.raw/*15.67*/("""

		"""),format.raw/*17.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<table class="table table-sm table-bordered table-condensed table-fluid">
					<tr>
						<TD  id="tabla" style="width:50%; height: auto; margin: 0 auto">
                            <table id="tablaPrincipal" class="table table-sm table-bordered table-condensed table-fluid">
                                <thead>
                                    <tr>
                                        <th></th>
                                        <th>"""),_display_(/*26.46*/yearAntAnterior),format.raw/*26.61*/("""</th>
                                        <th>"""),_display_(/*27.46*/yearAnterior),format.raw/*27.58*/("""</th>
                                        <th>"""),_display_(/*28.46*/yearActual),format.raw/*28.56*/("""</th>
                                        <th>Ranking M/Y</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    """),_display_(/*33.38*/for((lista,index) <- tabla.zipWithIndex) yield /*33.78*/{_display_(Seq[Any](format.raw/*33.79*/("""
                                        """),_display_(if(index < tabla.size() - 2)/*34.70*/ {_display_(Seq[Any](format.raw/*34.72*/("""
                                            """),format.raw/*35.45*/("""<tr>
                                                <td>"""),_display_(/*36.54*/lista/*36.59*/.get(0)),format.raw/*36.66*/("""</td>
                                                <td style="text-align: right; padding: 10px 10px;">"""),_display_(/*37.101*/lista/*37.106*/.get(1)),format.raw/*37.113*/("""</td>
                                                <td style="text-align: right; padding: 10px 10px;">"""),_display_(/*38.101*/lista/*38.106*/.get(2)),format.raw/*38.113*/("""</td>
                                                <td style="text-align: right; padding: 10px 10px;">"""),_display_(/*39.101*/lista/*39.106*/.get(3)),format.raw/*39.113*/("""</td>
                                                <td style="text-align: center; padding: 10px 10px;">"""),_display_(/*40.102*/lista/*40.107*/.get(4)),format.raw/*40.114*/("""</td>
                                            </tr>
                                        """)))} else {null} ),format.raw/*42.42*/("""
                                    """)))}),format.raw/*43.38*/("""
                                """),format.raw/*44.33*/("""</tbody>
                                <tfoot>
                                    <tr>
                                        <th>"""),_display_(/*47.46*/tabla/*47.51*/.get(tabla.size() - 2).get(0)),format.raw/*47.80*/("""</th>
                                        <th style="text-align: right; padding: 10px 10px;">"""),_display_(/*48.93*/tabla/*48.98*/.get(tabla.size() - 2).get(1)),format.raw/*48.127*/("""</th>
                                        <th style="text-align: right; padding: 10px 10px;">"""),_display_(/*49.93*/tabla/*49.98*/.get(tabla.size() - 2).get(2)),format.raw/*49.127*/("""</th>
                                        <th style="text-align: right; padding: 10px 10px;">"""),_display_(/*50.93*/tabla/*50.98*/.get(tabla.size() - 2).get(3)),format.raw/*50.127*/("""</th>
                                        <th style="text-align: right; padding: 10px 10px;">"""),_display_(/*51.93*/tabla/*51.98*/.get(tabla.size() - 2).get(4)),format.raw/*51.127*/("""</th>
                                    </tr>
                                    <tr>
                                        <th>"""),_display_(/*54.46*/tabla/*54.51*/.get(tabla.size() - 1).get(0)),format.raw/*54.80*/("""</th>
                                        <th style="text-align: right; padding: 10px 10px;">"""),_display_(/*55.93*/tabla/*55.98*/.get(tabla.size() - 1).get(1)),format.raw/*55.127*/("""</th>
                                        <th style="text-align: right; padding: 10px 10px;">"""),_display_(/*56.93*/tabla/*56.98*/.get(tabla.size() - 1).get(2)),format.raw/*56.127*/("""</th>
                                        <th style="text-align: right; padding: 10px 10px;">"""),_display_(/*57.93*/tabla/*57.98*/.get(tabla.size() - 1).get(3)),format.raw/*57.127*/("""</th>
                                        <th style="text-align: right; padding: 10px 10px;">"""),_display_(/*58.93*/tabla/*58.98*/.get(tabla.size() - 1).get(4)),format.raw/*58.127*/("""</th>
                                    </tr>
                                </tfoot>
                            </table>
                        </TD>
						<TD  id="grafico" style="width:50%; height: auto; margin: 0 auto"></TD>
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
""")))}),format.raw/*76.2*/("""




"""),format.raw/*81.1*/("""<script type="text/javascript">
	$(document).ready(function () """),format.raw/*82.32*/("""{"""),format.raw/*82.33*/("""
		

		"""),format.raw/*85.3*/("""Highcharts.chart('grafico',"""),format.raw/*85.30*/("""{"""),format.raw/*85.31*/("""
            """),format.raw/*86.13*/("""chart: """),format.raw/*86.20*/("""{"""),format.raw/*86.21*/("""
                """),format.raw/*87.17*/("""type: 'column'
            """),format.raw/*88.13*/("""}"""),format.raw/*88.14*/(""",
            title: """),format.raw/*89.20*/("""{"""),format.raw/*89.21*/("""
                """),format.raw/*90.17*/("""text: 'VENTAS NETAS (ALQ+VTAS+SERV)'
            """),format.raw/*91.13*/("""}"""),format.raw/*91.14*/(""",
            subtitle: """),format.raw/*92.23*/("""{"""),format.raw/*92.24*/("""
                """),format.raw/*93.17*/("""text: '(en """),_display_(/*93.29*/mapDiccionario/*93.43*/.get("PESOS")),format.raw/*93.56*/(""")'
            """),format.raw/*94.13*/("""}"""),format.raw/*94.14*/(""",
            xAxis: ["""),format.raw/*95.21*/("""{"""),format.raw/*95.22*/("""
                """),format.raw/*96.17*/("""categories:  ['Enero','Febrero','Marzo','Abril','Mayo','Junio','Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre'],
                labels: """),format.raw/*97.25*/("""{"""),format.raw/*97.26*/("""
                    """),format.raw/*98.21*/("""rotation: -45
                """),format.raw/*99.17*/("""}"""),format.raw/*99.18*/("""
            """),format.raw/*100.13*/("""}"""),format.raw/*100.14*/("""],
            yAxis: ["""),format.raw/*101.21*/("""{"""),format.raw/*101.22*/(""" """),format.raw/*101.23*/("""// 1er yAxis
            	min: 0,
                labels: """),format.raw/*103.25*/("""{"""),format.raw/*103.26*/("""
                    """),format.raw/*104.21*/("""style: """),format.raw/*104.28*/("""{"""),format.raw/*104.29*/("""
                        """),format.raw/*105.25*/("""color: Highcharts.getOptions().colors[1]
                    """),format.raw/*106.21*/("""}"""),format.raw/*106.22*/("""
                """),format.raw/*107.17*/("""}"""),format.raw/*107.18*/(""",
                title: """),format.raw/*108.24*/("""{"""),format.raw/*108.25*/("""
                    """),format.raw/*109.21*/("""text: 'Parcial',
                    style: """),format.raw/*110.28*/("""{"""),format.raw/*110.29*/("""
                        """),format.raw/*111.25*/("""color: Highcharts.getOptions().colors[1]
                    """),format.raw/*112.21*/("""}"""),format.raw/*112.22*/("""
                """),format.raw/*113.17*/("""}"""),format.raw/*113.18*/("""
            """),format.raw/*114.13*/("""}"""),format.raw/*114.14*/(""", """),format.raw/*114.16*/("""{"""),format.raw/*114.17*/(""" """),format.raw/*114.18*/("""// 2do yAxis
            	min: 0,
                title: """),format.raw/*116.24*/("""{"""),format.raw/*116.25*/("""
                    """),format.raw/*117.21*/("""text: 'Acumulado',
                    style: """),format.raw/*118.28*/("""{"""),format.raw/*118.29*/("""
                        """),format.raw/*119.25*/("""color: Highcharts.getOptions().colors[0]
                    """),format.raw/*120.21*/("""}"""),format.raw/*120.22*/("""
                """),format.raw/*121.17*/("""}"""),format.raw/*121.18*/(""",
                labels: """),format.raw/*122.25*/("""{"""),format.raw/*122.26*/("""
                    """),format.raw/*123.21*/("""style: """),format.raw/*123.28*/("""{"""),format.raw/*123.29*/("""
                        """),format.raw/*124.25*/("""color: Highcharts.getOptions().colors[0]
                    """),format.raw/*125.21*/("""}"""),format.raw/*125.22*/("""
                """),format.raw/*126.17*/("""}"""),format.raw/*126.18*/(""",
                opposite: true
            """),format.raw/*128.13*/("""}"""),format.raw/*128.14*/("""],
            tooltip: """),format.raw/*129.22*/("""{"""),format.raw/*129.23*/("""
                """),format.raw/*130.17*/("""shared: true,		                
            """),format.raw/*131.13*/("""}"""),format.raw/*131.14*/(""",
            legend: """),format.raw/*132.21*/("""{"""),format.raw/*132.22*/("""
                """),format.raw/*133.17*/("""layout: 'horizontal',
                align: 'center',
                verticalAlign: 'bottom',
                backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'
            """),format.raw/*137.13*/("""}"""),format.raw/*137.14*/(""",
            series: ["""),format.raw/*138.22*/("""{"""),format.raw/*138.23*/("""
                """),format.raw/*139.17*/("""name: 'Parcial """),_display_(/*139.33*/yearAntAnterior),format.raw/*139.48*/("""',
                type: 'column',
                yAxis: 1,
                data: """),_display_(/*142.24*/Html(series.get(1))),format.raw/*142.43*/(""",
                tooltip: """),format.raw/*143.26*/("""{"""),format.raw/*143.27*/("""
                	"""),format.raw/*144.18*/("""valuePrefix: '"""),_display_(/*144.33*/mapDiccionario/*144.47*/.get("CLP")),format.raw/*144.58*/(""" """),format.raw/*144.59*/("""',
                """),format.raw/*145.17*/("""}"""),format.raw/*145.18*/("""
            """),format.raw/*146.13*/("""}"""),format.raw/*146.14*/(""", """),format.raw/*146.16*/("""{"""),format.raw/*146.17*/("""
                """),format.raw/*147.17*/("""name: 'Acumulado """),_display_(/*147.35*/yearAntAnterior),format.raw/*147.50*/("""',
                type: 'spline',
                data: """),_display_(/*149.24*/Html(series.get(2))),format.raw/*149.43*/(""",
                tooltip: """),format.raw/*150.26*/("""{"""),format.raw/*150.27*/("""
                	"""),format.raw/*151.18*/("""valuePrefix: '"""),_display_(/*151.33*/mapDiccionario/*151.47*/.get("CLP")),format.raw/*151.58*/(""" """),format.raw/*151.59*/("""',
                """),format.raw/*152.17*/("""}"""),format.raw/*152.18*/("""
            """),format.raw/*153.13*/("""}"""),format.raw/*153.14*/(""","""),format.raw/*153.15*/("""{"""),format.raw/*153.16*/("""
                """),format.raw/*154.17*/("""name: 'Parcial """),_display_(/*154.33*/yearAnterior),format.raw/*154.45*/("""',
                type: 'column',
                yAxis: 1,
                data: """),_display_(/*157.24*/Html(series.get(4))),format.raw/*157.43*/(""",
                tooltip: """),format.raw/*158.26*/("""{"""),format.raw/*158.27*/("""
                    """),format.raw/*159.21*/("""valuePrefix: '"""),_display_(/*159.36*/mapDiccionario/*159.50*/.get("CLP")),format.raw/*159.61*/(""" """),format.raw/*159.62*/("""',
                """),format.raw/*160.17*/("""}"""),format.raw/*160.18*/("""
            """),format.raw/*161.13*/("""}"""),format.raw/*161.14*/(""", """),format.raw/*161.16*/("""{"""),format.raw/*161.17*/("""
                """),format.raw/*162.17*/("""name: 'Acumulado """),_display_(/*162.35*/yearAnterior),format.raw/*162.47*/("""',
                type: 'spline',
                data: """),_display_(/*164.24*/Html(series.get(5))),format.raw/*164.43*/(""",
                tooltip: """),format.raw/*165.26*/("""{"""),format.raw/*165.27*/("""
                    """),format.raw/*166.21*/("""valuePrefix: '"""),_display_(/*166.36*/mapDiccionario/*166.50*/.get("CLP")),format.raw/*166.61*/(""" """),format.raw/*166.62*/("""',
                """),format.raw/*167.17*/("""}"""),format.raw/*167.18*/("""
            """),format.raw/*168.13*/("""}"""),format.raw/*168.14*/(""","""),format.raw/*168.15*/("""{"""),format.raw/*168.16*/("""
                """),format.raw/*169.17*/("""name: 'Parcial """),_display_(/*169.33*/yearActual),format.raw/*169.43*/("""',
                type: 'column',
                yAxis: 1,
                data: """),_display_(/*172.24*/Html(series.get(7))),format.raw/*172.43*/(""",
                tooltip: """),format.raw/*173.26*/("""{"""),format.raw/*173.27*/("""
                    """),format.raw/*174.21*/("""valuePrefix: '"""),_display_(/*174.36*/mapDiccionario/*174.50*/.get("CLP")),format.raw/*174.61*/(""" """),format.raw/*174.62*/("""',
                """),format.raw/*175.17*/("""}"""),format.raw/*175.18*/("""
            """),format.raw/*176.13*/("""}"""),format.raw/*176.14*/(""", """),format.raw/*176.16*/("""{"""),format.raw/*176.17*/("""
                """),format.raw/*177.17*/("""name: 'Acumulado """),_display_(/*177.35*/yearActual),format.raw/*177.45*/("""',
                type: 'spline',
                data: """),_display_(/*179.24*/Html(series.get(8))),format.raw/*179.43*/(""",
                tooltip: """),format.raw/*180.26*/("""{"""),format.raw/*180.27*/("""
                    """),format.raw/*181.21*/("""valuePrefix: '"""),_display_(/*181.36*/mapDiccionario/*181.50*/.get("CLP")),format.raw/*181.61*/(""" """),format.raw/*181.62*/("""',
                """),format.raw/*182.17*/("""}"""),format.raw/*182.18*/("""
            """),format.raw/*183.13*/("""}"""),format.raw/*183.14*/("""]
        """),format.raw/*184.9*/("""}"""),format.raw/*184.10*/(""");
		document.getElementById('mostrarmostrar').style.display="block";
	  """),format.raw/*186.4*/("""}"""),format.raw/*186.5*/(""");

</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,series:List[String],yearActual:Long,yearAnterior:Long,yearAntAnterior:Long,tabla:List[List[String]],titulo:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,series,yearActual,yearAnterior,yearAntAnterior,tabla,titulo)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[String],Long,Long,Long,List[List[String]],String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,series,yearActual,yearAnterior,yearAntAnterior,tabla,titulo) => apply(mapDiccionario,mapPermiso,userMnu,series,yearActual,yearAnterior,yearAntAnterior,tabla,titulo)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reporteGerencialVentas.scala.html
                  HASH: 56ec5d6c7c743b14d11403568f355472d9fb466b
                  MATRIX: 1076->1|1392->224|1420->227|1436->235|1475->237|1504->240|1544->254|1558->260|1623->305|1676->332|1690->338|1757->385|1810->412|1824->418|1888->462|1942->489|1957->495|2024->541|2065->556|2134->604|2164->607|2241->658|2325->721|2356->725|2942->1284|2978->1299|3056->1350|3089->1362|3167->1413|3198->1423|3452->1650|3508->1690|3547->1691|3644->1761|3684->1763|3757->1808|3842->1866|3856->1871|3884->1878|4018->1984|4033->1989|4062->1996|4196->2102|4211->2107|4240->2114|4374->2220|4389->2225|4418->2232|4553->2339|4568->2344|4597->2351|4738->2448|4807->2486|4868->2519|5030->2654|5044->2659|5094->2688|5219->2786|5233->2791|5284->2820|5409->2918|5423->2923|5474->2952|5599->3050|5613->3055|5664->3084|5789->3182|5803->3187|5854->3216|6015->3350|6029->3355|6079->3384|6204->3482|6218->3487|6269->3516|6394->3614|6408->3619|6459->3648|6584->3746|6598->3751|6649->3780|6774->3878|6788->3883|6839->3912|7456->4499|7488->4504|7579->4567|7608->4568|7642->4575|7697->4602|7726->4603|7767->4616|7802->4623|7831->4624|7876->4641|7931->4668|7960->4669|8009->4690|8038->4691|8083->4708|8160->4757|8189->4758|8241->4782|8270->4783|8315->4800|8354->4812|8377->4826|8411->4839|8454->4854|8483->4855|8533->4877|8562->4878|8607->4895|8788->5048|8817->5049|8866->5070|8924->5100|8953->5101|8995->5114|9025->5115|9077->5138|9107->5139|9137->5140|9224->5198|9254->5199|9304->5220|9340->5227|9370->5228|9424->5253|9514->5314|9544->5315|9590->5332|9620->5333|9674->5358|9704->5359|9754->5380|9827->5424|9857->5425|9911->5450|10001->5511|10031->5512|10077->5529|10107->5530|10149->5543|10179->5544|10210->5546|10240->5547|10270->5548|10356->5605|10386->5606|10436->5627|10511->5673|10541->5674|10595->5699|10685->5760|10715->5761|10761->5778|10791->5779|10846->5805|10876->5806|10926->5827|10962->5834|10992->5835|11046->5860|11136->5921|11166->5922|11212->5939|11242->5940|11316->5985|11346->5986|11399->6010|11429->6011|11475->6028|11548->6072|11578->6073|11629->6095|11659->6096|11705->6113|11949->6328|11979->6329|12031->6352|12061->6353|12107->6370|12151->6386|12188->6401|12300->6485|12341->6504|12397->6531|12427->6532|12474->6550|12517->6565|12541->6579|12574->6590|12604->6591|12652->6610|12682->6611|12724->6624|12754->6625|12785->6627|12815->6628|12861->6645|12907->6663|12944->6678|13030->6736|13071->6755|13127->6782|13157->6783|13204->6801|13247->6816|13271->6830|13304->6841|13334->6842|13382->6861|13412->6862|13454->6875|13484->6876|13514->6877|13544->6878|13590->6895|13634->6911|13668->6923|13780->7007|13821->7026|13877->7053|13907->7054|13957->7075|14000->7090|14024->7104|14057->7115|14087->7116|14135->7135|14165->7136|14207->7149|14237->7150|14268->7152|14298->7153|14344->7170|14390->7188|14424->7200|14510->7258|14551->7277|14607->7304|14637->7305|14687->7326|14730->7341|14754->7355|14787->7366|14817->7367|14865->7386|14895->7387|14937->7400|14967->7401|14997->7402|15027->7403|15073->7420|15117->7436|15149->7446|15261->7530|15302->7549|15358->7576|15388->7577|15438->7598|15481->7613|15505->7627|15538->7638|15568->7639|15616->7658|15646->7659|15688->7672|15718->7673|15749->7675|15779->7676|15825->7693|15871->7711|15903->7721|15989->7779|16030->7798|16086->7825|16116->7826|16166->7847|16209->7862|16233->7876|16266->7887|16296->7888|16344->7907|16374->7908|16416->7921|16446->7922|16484->7932|16514->7933|16615->8006|16644->8007
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|38->7|38->7|39->8|39->8|39->8|40->9|40->9|40->9|41->10|41->10|41->10|43->12|43->12|45->14|46->15|46->15|48->17|57->26|57->26|58->27|58->27|59->28|59->28|64->33|64->33|64->33|65->34|65->34|66->35|67->36|67->36|67->36|68->37|68->37|68->37|69->38|69->38|69->38|70->39|70->39|70->39|71->40|71->40|71->40|73->42|74->43|75->44|78->47|78->47|78->47|79->48|79->48|79->48|80->49|80->49|80->49|81->50|81->50|81->50|82->51|82->51|82->51|85->54|85->54|85->54|86->55|86->55|86->55|87->56|87->56|87->56|88->57|88->57|88->57|89->58|89->58|89->58|107->76|112->81|113->82|113->82|116->85|116->85|116->85|117->86|117->86|117->86|118->87|119->88|119->88|120->89|120->89|121->90|122->91|122->91|123->92|123->92|124->93|124->93|124->93|124->93|125->94|125->94|126->95|126->95|127->96|128->97|128->97|129->98|130->99|130->99|131->100|131->100|132->101|132->101|132->101|134->103|134->103|135->104|135->104|135->104|136->105|137->106|137->106|138->107|138->107|139->108|139->108|140->109|141->110|141->110|142->111|143->112|143->112|144->113|144->113|145->114|145->114|145->114|145->114|145->114|147->116|147->116|148->117|149->118|149->118|150->119|151->120|151->120|152->121|152->121|153->122|153->122|154->123|154->123|154->123|155->124|156->125|156->125|157->126|157->126|159->128|159->128|160->129|160->129|161->130|162->131|162->131|163->132|163->132|164->133|168->137|168->137|169->138|169->138|170->139|170->139|170->139|173->142|173->142|174->143|174->143|175->144|175->144|175->144|175->144|175->144|176->145|176->145|177->146|177->146|177->146|177->146|178->147|178->147|178->147|180->149|180->149|181->150|181->150|182->151|182->151|182->151|182->151|182->151|183->152|183->152|184->153|184->153|184->153|184->153|185->154|185->154|185->154|188->157|188->157|189->158|189->158|190->159|190->159|190->159|190->159|190->159|191->160|191->160|192->161|192->161|192->161|192->161|193->162|193->162|193->162|195->164|195->164|196->165|196->165|197->166|197->166|197->166|197->166|197->166|198->167|198->167|199->168|199->168|199->168|199->168|200->169|200->169|200->169|203->172|203->172|204->173|204->173|205->174|205->174|205->174|205->174|205->174|206->175|206->175|207->176|207->176|207->176|207->176|208->177|208->177|208->177|210->179|210->179|211->180|211->180|212->181|212->181|212->181|212->181|212->181|213->182|213->182|214->183|214->183|215->184|215->184|217->186|217->186
                  -- GENERATED --
              */
          