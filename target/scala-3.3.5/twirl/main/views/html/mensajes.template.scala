
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

object mensajes extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(retornar: String, mensaje: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
"""),_display_(/*3.2*/main("")/*3.10*/ {_display_(Seq[Any](format.raw/*3.12*/("""
	
"""),format.raw/*5.1*/("""<input type="hidden" id="aux" name="aux" value=""""),_display_(/*5.50*/mensaje),format.raw/*5.57*/(""""></input>
""")))}),format.raw/*6.2*/("""
"""),format.raw/*7.1*/("""<script type="text/javascript">
	alertify.alert("<b>"""),_display_(/*8.22*/mensaje),format.raw/*8.29*/("""</b>", function () """),format.raw/*8.48*/("""{"""),format.raw/*8.49*/("""
		"""),format.raw/*9.3*/("""location.href=""""),_display_(/*9.19*/retornar),format.raw/*9.27*/("""";
	"""),format.raw/*10.2*/("""}"""),format.raw/*10.3*/(""")
</script>

"""))
      }
    }
  }

  def render(retornar:String,mensaje:String): play.twirl.api.HtmlFormat.Appendable = apply(retornar,mensaje)

  def f:((String,String) => play.twirl.api.HtmlFormat.Appendable) = (retornar,mensaje) => apply(retornar,mensaje)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/mensajes.scala.html
                  HASH: abcc9c4134ba8c8b1299ffeb350f6b952ad16a72
                  MATRIX: 955->1|1084->37|1111->39|1127->47|1166->49|1195->52|1270->101|1297->108|1338->120|1365->121|1444->174|1471->181|1517->200|1545->201|1574->204|1616->220|1644->228|1675->232|1703->233
                  LINES: 28->1|33->2|34->3|34->3|34->3|36->5|36->5|36->5|37->6|38->7|39->8|39->8|39->8|39->8|40->9|40->9|40->9|41->10|41->10
                  -- GENERATED --
              */
          