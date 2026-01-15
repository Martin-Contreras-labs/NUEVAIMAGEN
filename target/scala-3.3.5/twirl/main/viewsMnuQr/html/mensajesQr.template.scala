
package viewsMnuQr.html

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

object mensajesQr extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(retornar: String, mensaje: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
"""),_display_(/*3.2*/main("")/*3.10*/ {_display_(Seq[Any](format.raw/*3.12*/("""
	"""),format.raw/*4.2*/("""<input type="hidden" id="aux" name="aux" value=""""),_display_(/*4.51*/mensaje),format.raw/*4.58*/(""""></input>
""")))}),format.raw/*5.2*/("""

"""),format.raw/*7.1*/("""<script type="text/javascript">
	window.alert (aux.value);
	this.focus();
	location.href = "/"""),_display_(/*10.21*/retornar),format.raw/*10.29*/(""""
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
                  SOURCE: app/viewsMnuQr/mensajesQr.scala.html
                  HASH: a2ac7ff44fd9b1cd0cbca7ce4c8c15cd91c916bd
                  MATRIX: 962->1|1091->37|1118->39|1134->47|1173->49|1201->51|1276->100|1303->107|1344->119|1372->121|1493->215|1522->223
                  LINES: 28->1|33->2|34->3|34->3|34->3|35->4|35->4|35->4|36->5|38->7|41->10|41->10
                  -- GENERATED --
              */
          