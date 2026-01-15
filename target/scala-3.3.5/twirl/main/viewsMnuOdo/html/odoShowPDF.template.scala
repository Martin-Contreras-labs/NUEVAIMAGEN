
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

object odoShowPDF extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(fileNamePdf: String, volver: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""



"""),_display_(/*6.2*/main("")/*6.10*/ {_display_(Seq[Any](format.raw/*6.12*/("""


	"""),format.raw/*9.2*/("""<div id="ocultar" style="display:none;">
		<div class="container" style="border-color:lightblue;padding-top: 25px;padding-left: 25px; text-align:center">
			<button class="btn btn-sm  btn-success" type="button" style="font-size: 10px;" onclick="location.href = '"""),_display_(/*11.110*/volver),format.raw/*11.116*/("""'">LISTO</button>
			<br><br>
			<object data='"""),_display_(/*13.19*/routes/*13.25*/.HomeController.showPdf(fileNamePdf)),format.raw/*13.61*/("""' type='application/pdf' width='80%' height='700px'></object> 
		</div> 
	</div> 
""")))}),format.raw/*16.2*/("""

"""),format.raw/*18.1*/("""<script>
    $(document).ready(function() """),format.raw/*19.34*/("""{"""),format.raw/*19.35*/("""
		"""),format.raw/*20.3*/("""document.getElementById('ocultar').style.display="block"; 
	"""),format.raw/*21.2*/("""}"""),format.raw/*21.3*/(""" """),format.raw/*21.4*/(""");
</script>"""))
      }
    }
  }

  def render(fileNamePdf:String,volver:String): play.twirl.api.HtmlFormat.Appendable = apply(fileNamePdf,volver)

  def f:((String,String) => play.twirl.api.HtmlFormat.Appendable) = (fileNamePdf,volver) => apply(fileNamePdf,volver)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuOdo/odoShowPDF.scala.html
                  HASH: 39880603436404f2967d586868e5825f1f177995
                  MATRIX: 963->1|1094->39|1124->44|1140->52|1179->54|1209->58|1500->321|1528->327|1603->375|1618->381|1675->417|1788->500|1817->502|1887->544|1916->545|1946->548|2033->608|2061->609|2089->610
                  LINES: 28->1|33->2|37->6|37->6|37->6|40->9|42->11|42->11|44->13|44->13|44->13|47->16|49->18|50->19|50->19|51->20|52->21|52->21|52->21
                  -- GENERATED --
              */
          