
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

object showPDF extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,String,String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
fileNamePdf: String, volver: String, titulo: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""



"""),_display_(/*7.2*/main("")/*7.10*/ {_display_(Seq[Any](format.raw/*7.12*/("""
	"""),_display_(/*8.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*8.51*/("""
	"""),_display_(/*9.3*/barraTitulo(mapDiccionario, titulo, "")),format.raw/*9.42*/("""

	"""),format.raw/*11.2*/("""<div id="ocultar" style="display:none;">
		<div class="container" style="border-color:lightblue;padding-top: 25px;padding-left: 25px; text-align:center">
			<button class="btn btn-sm  btn-success" type="button" style="font-size: 10px;" onclick="location.href = '"""),_display_(/*13.110*/volver),format.raw/*13.116*/("""'">LISTO</button>
			<br><br>
			<object data='"""),_display_(/*15.19*/routes/*15.25*/.HomeController.showPdf(fileNamePdf)),format.raw/*15.61*/("""' type='application/pdf' width='80%' height='700px'></object>
		</div>
	</div>
""")))}),format.raw/*18.2*/("""

"""),format.raw/*20.1*/("""<script>
    $(document).ready(function() """),format.raw/*21.34*/("""{"""),format.raw/*21.35*/("""
		"""),format.raw/*22.3*/("""document.getElementById('ocultar').style.display="block";
	"""),format.raw/*23.2*/("""}"""),format.raw/*23.3*/(""" """),format.raw/*23.4*/(""");
</script>"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,fileNamePdf:String,volver:String,titulo:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,fileNamePdf,volver,titulo)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,String,String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,fileNamePdf,volver,titulo) => apply(mapDiccionario,mapPermiso,userMnu,fileNamePdf,volver,titulo)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/showPDF.scala.html
                  HASH: 006309a9828018227919b0d993e4a8a7fa32e10f
                  MATRIX: 1017->1|1260->151|1290->156|1306->164|1345->166|1373->169|1441->217|1469->220|1528->259|1558->262|1849->525|1877->531|1952->579|1967->585|2024->621|2134->701|2163->703|2233->745|2262->746|2292->749|2378->808|2406->809|2434->810
                  LINES: 28->1|34->3|38->7|38->7|38->7|39->8|39->8|40->9|40->9|42->11|44->13|44->13|46->15|46->15|46->15|49->18|51->20|52->21|52->21|53->22|54->23|54->23|54->23
                  -- GENERATED --
              */
          