
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

object imagenQr extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[String,String,tables.Equipo,Double,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(nEmpresa: String, base: String, equipo: tables.Equipo, stock: Double, ubicacion: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
"""),_display_(/*3.2*/mainQR("MADA_QR")/*3.19*/ {_display_(Seq[Any](format.raw/*3.21*/("""



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
	    		<label for="textAreaGrupo" class="col-sm-3 col-form-label"  style="font-size:30px">Grupo:</label>
			    <div class="col-sm-9">
			      <textarea class="form-control font-weight-bold" id="textAreaGrupo" rows="2" style="font-size:30px;color:blue" disabled>"""),_display_(/*35.130*/equipo/*35.136*/.grupo.toUpperCase()),format.raw/*35.156*/("""</textarea>
			    </div>
	  		</div>
	  		<br>
	  		"""),_display_(if(stock<2)/*39.18*/{_display_(Seq[Any](format.raw/*39.19*/("""
		  		"""),format.raw/*40.7*/("""<div class="form-group row">
		    		<label for="textAreaUbicacion" class="col-sm-3 col-form-label"  style="font-size:30px">Ubicación:</label>
				    <div class="col-sm-9">
				      <textarea class="form-control font-weight-bold" id="textAreaUbicacion" rows="2" style="font-size:30px;color:blue" disabled>"""),_display_(/*43.135*/ubicacion/*43.144*/.toUpperCase()),format.raw/*43.158*/("""</textarea>
				    </div>
		  		</div>
	  		""")))} else {null} ),format.raw/*46.7*/("""
		"""),format.raw/*47.3*/("""</form>
	  	
	  	<br><br>
	  	
	  	<div align="center">
			<img alt=""""),_display_(/*52.15*/equipo/*52.21*/.nombre),format.raw/*52.28*/("""" title=""""),_display_(/*52.38*/equipo/*52.44*/.nombre),format.raw/*52.51*/("""" src='/viewImgQr/"""),_display_(/*52.70*/base),format.raw/*52.74*/(""","""),_display_(/*52.76*/equipo/*52.82*/.getImg()),format.raw/*52.91*/("""' style="height:600px;max-width:100%">
	  		<br><br>
	  		<button type="button" class="btn btn-info btn-lg" style="font-size:30px" onclick="history.go(-1)">VOLVER</button>
		</div>
		<br><br>
	</div>






""")))}),format.raw/*64.2*/("""
"""))
      }
    }
  }

  def render(nEmpresa:String,base:String,equipo:tables.Equipo,stock:Double,ubicacion:String): play.twirl.api.HtmlFormat.Appendable = apply(nEmpresa,base,equipo,stock,ubicacion)

  def f:((String,String,tables.Equipo,Double,String) => play.twirl.api.HtmlFormat.Appendable) = (nEmpresa,base,equipo,stock,ubicacion) => apply(nEmpresa,base,equipo,stock,ubicacion)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuQr/imagenQr.scala.html
                  HASH: 239ade2bb1e71d53c46499d7d579c909b49f78f4
                  MATRIX: 988->1|1171->91|1198->93|1223->110|1262->112|1293->117|1682->478|1700->486|1736->500|2105->841|2121->847|2164->868|2554->1230|2570->1236|2613->1257|2987->1603|3003->1609|3045->1629|3137->1694|3176->1695|3210->1702|3546->2010|3565->2019|3601->2033|3690->2079|3720->2082|3817->2152|3832->2158|3860->2165|3897->2175|3912->2181|3940->2188|3986->2207|4011->2211|4040->2213|4055->2219|4085->2228|4322->2435
                  LINES: 28->1|33->2|34->3|34->3|34->3|38->7|45->14|45->14|45->14|52->21|52->21|52->21|59->28|59->28|59->28|66->35|66->35|66->35|70->39|70->39|71->40|74->43|74->43|74->43|77->46|78->47|83->52|83->52|83->52|83->52|83->52|83->52|83->52|83->52|83->52|83->52|83->52|95->64
                  -- GENERATED --
              */
          