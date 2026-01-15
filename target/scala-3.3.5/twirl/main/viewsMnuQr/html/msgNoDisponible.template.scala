
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

object msgNoDisponible extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[String,String,tables.Equipo,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(nEmpresa: String, base: String, equipo: tables.Equipo):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
"""),_display_(/*3.2*/main("")/*3.10*/ {_display_(Seq[Any](format.raw/*3.12*/("""



	"""),format.raw/*7.2*/("""<div class="container">
	  	<br><br><br><br>
	  	
	  	<form>
	  		<div class="form-group row">
	    		<label for="inputEmpresa" class="col-sm-3 col-form-label"  style="font-size:30px">Propietario:</label>
			    <div class="col-sm-9">
			      <input type="text" class="form-control font-weight-bold" id="inputEmpresa" style="font-size:30px;color:blue" value=""""),_display_(/*14.127*/nEmpresa/*14.135*/.toUpperCase()),format.raw/*14.149*/("""" disabled>
			    </div>
	  		</div>
	  		<br>
	  		<div class="form-group row">
	    		<label for="inputCodigo" class="col-sm-3 col-form-label"  style="font-size:30px">Código:</label>
			    <div class="col-sm-9">
			      <input type="text" class="form-control font-weight-bold" id="inputCodigo" style="font-size:30px;color:blue" value=""""),_display_(/*21.126*/equipo/*21.132*/.codigo.toUpperCase()),format.raw/*21.153*/("""" disabled>
			    </div>
	  		</div>
	  		<br>
	  		<div class="form-group row">
	    		<label for="textAreaDecripcion" class="col-sm-3 col-form-label"  style="font-size:30px">Descripción:</label>
			    <div class="col-sm-9">
			      <textarea class="form-control font-weight-bold" id="textAreaDecripcion" rows="2" style="font-size:30px;color:blue" disabled>"""),_display_(/*28.135*/equipo/*28.141*/.nombre.toUpperCase()),format.raw/*28.162*/("""</textarea>
			    </div>
	  		</div>
	  		<br>
	  		<div class="form-group row">
	    		<label for="textAreaDecripcion" class="col-sm-3 col-form-label"  style="font-size:30px">NOTA:</label>
			    <div class="col-sm-9">
			      <textarea class="form-control font-weight-bold" id="textAreaDecripcion" rows="2" style="font-size:30px;color:red" disabled>EQUIPO NO DISPONIBLE Y/O DESCONTINUADO</textarea>
			    </div>
	  		</div>
		</form>
	  	
	  	
	</div>









""")))}),format.raw/*51.2*/("""
"""))
      }
    }
  }

  def render(nEmpresa:String,base:String,equipo:tables.Equipo): play.twirl.api.HtmlFormat.Appendable = apply(nEmpresa,base,equipo)

  def f:((String,String,tables.Equipo) => play.twirl.api.HtmlFormat.Appendable) = (nEmpresa,base,equipo) => apply(nEmpresa,base,equipo)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuQr/msgNoDisponible.scala.html
                  HASH: 2a7fccad07402508be6ae061bef74cb124a8a3e0
                  MATRIX: 981->1|1130->57|1157->59|1173->67|1212->69|1243->74|1632->435|1650->443|1686->457|2055->798|2071->804|2114->825|2504->1187|2520->1193|2563->1214|3060->1681
                  LINES: 28->1|33->2|34->3|34->3|34->3|38->7|45->14|45->14|45->14|52->21|52->21|52->21|59->28|59->28|59->28|82->51
                  -- GENERATED --
              */
          