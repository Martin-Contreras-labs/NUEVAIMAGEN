
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

object mainQR extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[String,Html,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(title: String)(content: Html):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
"""),format.raw/*3.1*/("""<!DOCTYPE html>

<html>
    <head>
    
    	<meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="author" content="INQSOL SPA">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
    	<meta name="google" value="notranslate">
    	<meta http-equiv="cache-control" content="no-cache"/>
    
        <title>"""),_display_(/*15.17*/title),format.raw/*15.22*/("""</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <link rel="icon" type="image/png" href="/assets/images/favicon.png">
        
        
       
		<script src=""""),_display_(/*21.17*/routes/*21.23*/.Assets.versioned("javascripts/jquery-3.3.1.slim.min.js")),format.raw/*21.80*/(""""></script>
		<script src=""""),_display_(/*22.17*/routes/*22.23*/.Assets.versioned("javascripts/popper.min.js")),format.raw/*22.69*/(""""></script>
        <script src=""""),_display_(/*23.23*/routes/*23.29*/.Assets.versioned("bootstrap-4.3.1/js/bootstrap.min.js")),format.raw/*23.85*/(""""></script>
     

        
        
    </head>
    <body>
        """),_display_(/*30.10*/content),format.raw/*30.17*/("""
    """),format.raw/*31.5*/("""</body>
</html>
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
                  SOURCE: app/viewsMnuQr/mainQR.scala.html
                  HASH: 8d6e92decdbb25997f700a245e5dc5d0b69cbd9f
                  MATRIX: 956->1|1080->32|1107->33|1508->407|1534->412|1903->754|1918->760|1996->817|2051->845|2066->851|2133->897|2194->931|2209->937|2286->993|2382->1062|2410->1069|2442->1074
                  LINES: 28->1|33->2|34->3|46->15|46->15|52->21|52->21|52->21|53->22|53->22|53->22|54->23|54->23|54->23|61->30|61->30|62->31
                  -- GENERATED --
              */
          