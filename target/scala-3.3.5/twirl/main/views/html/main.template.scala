
package views.html

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

object main extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[String,Html,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*2.2*/(title: String)(content: Html):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
"""),format.raw/*4.1*/("""<!DOCTYPE html>

<style>
.blocker """),format.raw/*7.10*/("""{"""),format.raw/*7.11*/("""
"""),format.raw/*8.1*/("""position: absolute;
top: 0px;
left: 0px;
height:100%;
width:100%;        /* hacemos que ocupe toda la pantalla a cualquier resolución*/
z-index: 50;        /* lo colocamos por encima del resto de componentes*/
filter:alpha(opacity=50);
-moz-opacity: 50;
opacity: 0.50;
background-color: #909497;
text-align:center;
vertical-align: middle;
"""),format.raw/*20.1*/("""}"""),format.raw/*20.2*/("""
"""),format.raw/*21.1*/("""</style>


<html lang="en" style="padding-left:5px;padding-right:5px;font-size: 12px;">
    <head>
    	<meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="author" content="INQSOL SPA">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
    	<meta name="google" value="notranslate">
    	<meta http-equiv="cache-control" content="no-cache"/>
    	
    	
    	<link rel="icon" type="image/png" href="/assets/images/favicon.png">
    	
    	
    
        """),format.raw/*38.62*/("""
        """),format.raw/*39.9*/("""<title>MADA</title>
        
        <link rel="shortcut icon" type="image/png" href=""""),_display_(/*41.59*/routes/*41.65*/.Assets.versioned("images/favicon.png")),format.raw/*41.104*/("""">
        <link rel="stylesheet" media="print,page"  href=""""),_display_(/*42.59*/routes/*42.65*/.Assets.versioned("stylesheets/print.css")),format.raw/*42.107*/("""">
        <link rel="stylesheet" media="all" href=""""),_display_(/*43.51*/routes/*43.57*/.Assets.versioned("bootstrap-4.3.1/css/bootstrap.css")),format.raw/*43.111*/("""">
        <link rel="stylesheet" href=""""),_display_(/*44.39*/routes/*44.45*/.Assets.versioned("alertify.js/themes/alertify.core.css")),format.raw/*44.102*/("""">
        <link rel="stylesheet" href=""""),_display_(/*45.39*/routes/*45.45*/.Assets.versioned("alertify.js/themes/alertify.default.css")),format.raw/*45.105*/("""">
        <link rel="stylesheet" href=""""),_display_(/*46.39*/routes/*46.45*/.Assets.versioned("dataTables/dataTables.bootstrap4.min.css")),format.raw/*46.106*/("""">
        <link rel="stylesheet" href=""""),_display_(/*47.39*/routes/*47.45*/.Assets.versioned("dataTables/fixedHeader.dataTables.min.css")),format.raw/*47.107*/("""">
        <link rel="stylesheet" href=""""),_display_(/*48.39*/routes/*48.45*/.Assets.versioned("dataTables/fixedColumns.dataTables.min.css")),format.raw/*48.108*/("""">
        <link rel="stylesheet" media="screen" href=""""),_display_(/*49.54*/routes/*49.60*/.Assets.versioned("stylesheets/main.css")),format.raw/*49.101*/("""">
    </head>
    <body>
    
    	<script src=""""),_display_(/*53.20*/routes/*53.26*/.Assets.versioned("javascripts/main.js?v=1.0.1")),format.raw/*53.74*/(""""></script>
        <script src=""""),_display_(/*54.23*/routes/*54.29*/.Assets.versioned("javascripts/DecimalFormat.js?v=1.0.1")),format.raw/*54.86*/(""""></script>
		<script src=""""),_display_(/*55.17*/routes/*55.23*/.Assets.versioned("javascripts/jquery.js?v=1.0.1")),format.raw/*55.73*/(""""></script>
		<script src=""""),_display_(/*56.17*/routes/*56.23*/.Assets.versioned("bootstrap-4.3.1/js/bootstrap.min.js?v=1.0.1")),format.raw/*56.87*/(""""></script>
		<script src=""""),_display_(/*57.17*/routes/*57.23*/.Assets.versioned("alertify.js/lib/alertify.js?v=1.0.1")),format.raw/*57.79*/(""""></script>
		<script src=""""),_display_(/*58.17*/routes/*58.23*/.Assets.versioned("dataTables/jquery.dataTables.min.js?v=1.0.1")),format.raw/*58.87*/(""""></script>
		<script src=""""),_display_(/*59.17*/routes/*59.23*/.Assets.versioned("dataTables/dataTables.bootstrap4.min.js?v=1.0.1")),format.raw/*59.91*/(""""></script>
		
		<script src=""""),_display_(/*61.17*/routes/*61.23*/.Assets.versioned("dataTables/dataTables.fixedHeader.min.js?v=1.0.1")),format.raw/*61.92*/(""""></script>
		<script src=""""),_display_(/*62.17*/routes/*62.23*/.Assets.versioned("dataTables/dataTables.fixedColumns.min.js?v=1.0.1")),format.raw/*62.93*/(""""></script>

		
        """),format.raw/*65.88*/("""

		"""),format.raw/*67.3*/("""<div id="enProceso" class="blocker" style="display: none;">
			<br><br><br><br><br><br><h1>El reporte esta en proceso..... :)</h1>
		</div>

		<div id="bloquear" class="blocker" style="display:none;"><br><br><br><br><br><br><h1>Se perdió la conexión con Internet</h1></div>
		<div id="bloquear2" class="blocker" style="display:none;"></div>

        """),_display_(/*74.10*/content),format.raw/*74.17*/("""

        """),format.raw/*76.9*/("""<br><br><br><br><br><br><br><br>


		
    </body>
</html>

<script>
	//VERIFICA CONEXION A INTERNET
	setInterval(estaConectado(),1000);
	function estaConectado()"""),format.raw/*86.26*/("""{"""),format.raw/*86.27*/("""
		"""),format.raw/*87.3*/("""if(!navigator.onLine)"""),format.raw/*87.24*/("""{"""),format.raw/*87.25*/("""
		    """),format.raw/*88.7*/("""document.getElementById('bloquear').style.display="block"; 
		"""),format.raw/*89.3*/("""}"""),format.raw/*89.4*/("""else"""),format.raw/*89.8*/("""{"""),format.raw/*89.9*/("""
			"""),format.raw/*90.4*/("""document.getElementById('bloquear').style.display="none"; 
		"""),format.raw/*91.3*/("""}"""),format.raw/*91.4*/("""
	"""),format.raw/*92.2*/("""}"""),format.raw/*92.3*/("""
	
	"""),format.raw/*94.2*/("""//RESTRINGE LA TECLA ENTER SOBRE SUMMIT
	document.addEventListener('DOMContentLoaded', () => """),format.raw/*95.54*/("""{"""),format.raw/*95.55*/("""
      """),format.raw/*96.7*/("""document.querySelectorAll('input[type=text]').forEach( node => node.addEventListener('keypress', e => """),format.raw/*96.109*/("""{"""),format.raw/*96.110*/("""
        """),format.raw/*97.9*/("""if(e.keyCode == 13) """),format.raw/*97.29*/("""{"""),format.raw/*97.30*/("""
          """),format.raw/*98.11*/("""e.preventDefault();
        """),format.raw/*99.9*/("""}"""),format.raw/*99.10*/("""
      """),format.raw/*100.7*/("""}"""),format.raw/*100.8*/("""))
    """),format.raw/*101.5*/("""}"""),format.raw/*101.6*/(""");
    

</script>






    """))
      }
    }
  }

  def render(title:String,content:Html): play.twirl.api.HtmlFormat.Appendable = apply(title)(content)

  def f:((String) => (Html) => play.twirl.api.HtmlFormat.Appendable) = (title) => (content) => apply(title)(content)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/main.scala.html
                  HASH: f2f0b0776a459005c8ada8e0a0868a57ddfe8df8
                  MATRIX: 949->2|1073->33|1100->34|1161->68|1189->69|1216->70|1582->409|1610->410|1638->411|2189->987|2225->996|2339->1083|2354->1089|2415->1128|2503->1189|2518->1195|2582->1237|2662->1290|2677->1296|2753->1350|2821->1391|2836->1397|2915->1454|2983->1495|2998->1501|3080->1561|3148->1602|3163->1608|3246->1669|3314->1710|3329->1716|3413->1778|3481->1819|3496->1825|3581->1888|3664->1944|3679->1950|3742->1991|3819->2041|3834->2047|3903->2095|3964->2129|3979->2135|4057->2192|4112->2220|4127->2226|4198->2276|4253->2304|4268->2310|4353->2374|4408->2402|4423->2408|4500->2464|4555->2492|4570->2498|4655->2562|4710->2590|4725->2596|4814->2664|4872->2695|4887->2701|4977->2770|5032->2798|5047->2804|5138->2874|5190->2977|5221->2981|5599->3332|5627->3339|5664->3349|5853->3510|5882->3511|5912->3514|5961->3535|5990->3536|6024->3543|6113->3605|6141->3606|6172->3610|6200->3611|6231->3615|6319->3676|6347->3677|6376->3679|6404->3680|6435->3684|6556->3777|6585->3778|6619->3785|6750->3887|6780->3888|6816->3897|6864->3917|6893->3918|6932->3929|6987->3957|7016->3958|7051->3965|7080->3966|7115->3973|7144->3974
                  LINES: 28->2|33->3|34->4|37->7|37->7|38->8|50->20|50->20|51->21|68->38|69->39|71->41|71->41|71->41|72->42|72->42|72->42|73->43|73->43|73->43|74->44|74->44|74->44|75->45|75->45|75->45|76->46|76->46|76->46|77->47|77->47|77->47|78->48|78->48|78->48|79->49|79->49|79->49|83->53|83->53|83->53|84->54|84->54|84->54|85->55|85->55|85->55|86->56|86->56|86->56|87->57|87->57|87->57|88->58|88->58|88->58|89->59|89->59|89->59|91->61|91->61|91->61|92->62|92->62|92->62|95->65|97->67|104->74|104->74|106->76|116->86|116->86|117->87|117->87|117->87|118->88|119->89|119->89|119->89|119->89|120->90|121->91|121->91|122->92|122->92|124->94|125->95|125->95|126->96|126->96|126->96|127->97|127->97|127->97|128->98|129->99|129->99|130->100|130->100|131->101|131->101
                  -- GENERATED --
              */
          