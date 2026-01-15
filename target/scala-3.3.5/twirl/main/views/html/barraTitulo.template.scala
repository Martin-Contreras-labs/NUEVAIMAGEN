
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

object barraTitulo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[Map[String,String],String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], titulo: String, subtitulo: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
"""),format.raw/*3.1*/("""<div style="line-height:0.2;background-color:white;"><br></div>
<div class="container" style="max-width:100%;background-color: #e3f2fd; ">
	<div class="container" style="max-width:100%; background-color: #e3f2fd; display: flex; align-items: center; justify-content: space-between; padding: 5px 15px;">

			<!-- Izquierda -->
		<div style="font-weight: bold;">
			"""),_display_(/*9.5*/Html(mapDiccionario.get("nEmpresa")+"<br>"+mapDiccionario.get("pais"))),format.raw/*9.75*/("""
		"""),format.raw/*10.3*/("""</div>

			<!-- Centro -->
		<div style="flex-grow: 1; text-align: center; font-weight: bold; font-size: 14px; color: blue;">
			"""),_display_(/*14.5*/Html(titulo + "<br>" + subtitulo)),format.raw/*14.38*/("""
		"""),format.raw/*15.3*/("""</div>

			<!-- Derecha -->
		<div>
			<img src="/viewImg/"""),_display_(/*19.24*/mapDiccionario/*19.38*/.get("logoEmpresa")),format.raw/*19.57*/("""" height="40px">
		</div>
	</div>

</div>


<!--
<p class="text-uppercase font-weight-bold" style="float: right"><img src='/viewImg/"""),_display_(/*27.85*/mapDiccionario/*27.99*/.get("logoEmpresa")),format.raw/*27.118*/("""' width="auto" height="40px"></p>
<p class="font-weight-bold" align="center" style="font-size:14px;color:blue; height:42px;">"""),_display_(/*28.93*/Html(titulo+"<br>"+subtitulo)),format.raw/*28.122*/("""</p>
-->



		


"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],titulo:String,subtitulo:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,titulo,subtitulo)

  def f:((Map[String,String],String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,titulo,subtitulo) => apply(mapDiccionario,titulo,subtitulo)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/barraTitulo.scala.html
                  HASH: 8277be317bd8a9af91121c7976a2737935fe7623
                  MATRIX: 977->1|1142->73|1169->74|1558->438|1648->508|1678->511|1834->641|1888->674|1918->677|2004->736|2027->750|2067->769|2227->902|2250->916|2291->935|2444->1061|2495->1090
                  LINES: 28->1|33->2|34->3|40->9|40->9|41->10|45->14|45->14|46->15|50->19|50->19|50->19|58->27|58->27|58->27|59->28|59->28
                  -- GENERATED --
              */
          