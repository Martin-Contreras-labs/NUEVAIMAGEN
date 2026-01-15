
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

object atributoQr extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[String,String,tables.Equipo,Double,String,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(nEmpresa: String, base: String, equipo: tables.Equipo, stock: Double, ubicacion: String, lista: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
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
	  		<div class="form-group row">
	    		<label for="inputfabrica" class="col-sm-3 col-form-label"  style="font-size:30px">Fabricante:</label>
			    <div class="col-sm-9">
			      <input type="text" class="form-control font-weight-bold" id="inputfabrica" style="font-size:30px;color:blue" value=""""),_display_(/*42.127*/equipo/*42.133*/.fabrica.toUpperCase()),format.raw/*42.155*/("""" disabled>
			    </div>
	  		</div>
			<div class="form-group row">
	    		<label for="inputfabrica" class="col-sm-3 col-form-label"  style="font-size:30px">Peso (KG):</label>
			    <div class="col-sm-9">
			      <input type="text" class="form-control font-weight-bold" id="inputfabrica" style="font-size:30px;color:blue" value=""""),_display_(/*48.127*/equipo/*48.133*/.getKg()),format.raw/*48.141*/("""" disabled>
			    </div>
	  		</div>
			<div class="form-group row">
	    		<label for="inputfabrica" class="col-sm-3 col-form-label"  style="font-size:30px">Area (M2):</label>
			    <div class="col-sm-9">
			      <input type="text" class="form-control font-weight-bold" id="inputfabrica" style="font-size:30px;color:blue" value=""""),_display_(/*54.127*/equipo/*54.133*/.getM2()),format.raw/*54.141*/("""" disabled>
			    </div>
	  		</div>
	  		"""),_display_(if(stock<2)/*57.18*/{_display_(Seq[Any](format.raw/*57.19*/("""
		  		"""),format.raw/*58.7*/("""<div class="form-group row">
		    		<label for="textAreaUbicacion" class="col-sm-3 col-form-label"  style="font-size:30px">Ubicación:</label>
				    <div class="col-sm-9">
				      <textarea class="form-control font-weight-bold" id="textAreaUbicacion" rows="2" style="font-size:30px;color:blue" disabled>"""),_display_(/*61.135*/ubicacion/*61.144*/.toUpperCase()),format.raw/*61.158*/("""</textarea>
				    </div>
		  		</div>
	  		""")))} else {null} ),format.raw/*64.7*/("""
	  		"""),_display_(/*65.7*/for(list <-lista) yield /*65.24*/{_display_(Seq[Any](format.raw/*65.25*/("""
	  			"""),format.raw/*66.7*/("""<br>
		  		<div class="form-group row">
		    		<label for="input"""),_display_(/*68.27*/list/*68.31*/.get(0)),format.raw/*68.38*/("""" class="col-sm-5 col-form-label"  style="font-size:30px">"""),_display_(/*68.97*/list/*68.101*/.get(0).toUpperCase()),format.raw/*68.122*/(""":</label>
				    <div class="col-sm-7">
				      <input type="text" class="form-control font-weight-bold" id="input"""),_display_(/*70.78*/list/*70.82*/.get(0)),format.raw/*70.89*/("""" style="font-size:30px;color:blue" value=""""),_display_(/*70.133*/list/*70.137*/.get(1).toUpperCase()),format.raw/*70.158*/("""" disabled>
				    </div>
		  		</div>
	  		""")))}),format.raw/*73.7*/("""
		"""),format.raw/*74.3*/("""</form>
	  	
	  	<br><br>
	  	<div align="center">
	  		<button type="button" class="btn btn-info btn-lg" style="font-size:30px" onclick="history.go(-1)">VOLVER</button>
		</div>
		<br><br>
	</div>
	








""")))}),format.raw/*91.2*/("""
"""))
      }
    }
  }

  def render(nEmpresa:String,base:String,equipo:tables.Equipo,stock:Double,ubicacion:String,lista:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(nEmpresa,base,equipo,stock,ubicacion,lista)

  def f:((String,String,tables.Equipo,Double,String,List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (nEmpresa,base,equipo,stock,ubicacion,lista) => apply(nEmpresa,base,equipo,stock,ubicacion,lista)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuQr/atributoQr.scala.html
                  HASH: 647d7d4078aa7f1e22146bfcd623484cc4e021dc
                  MATRIX: 1009->1|1219->118|1246->120|1271->137|1310->139|1342->145|1731->506|1749->514|1785->528|2154->869|2170->875|2213->896|2603->1258|2619->1264|2662->1285|3036->1631|3052->1637|3094->1657|3469->2004|3485->2010|3529->2032|3891->2366|3907->2372|3937->2380|4299->2714|4315->2720|4345->2728|4427->2783|4466->2784|4500->2791|4836->3099|4855->3108|4891->3122|4980->3168|5013->3175|5046->3192|5085->3193|5119->3200|5212->3266|5225->3270|5253->3277|5339->3336|5353->3340|5396->3361|5541->3479|5554->3483|5582->3490|5654->3534|5668->3538|5711->3559|5787->3605|5817->3608|6056->3817
                  LINES: 28->1|33->2|34->3|34->3|34->3|38->7|45->14|45->14|45->14|52->21|52->21|52->21|59->28|59->28|59->28|66->35|66->35|66->35|73->42|73->42|73->42|79->48|79->48|79->48|85->54|85->54|85->54|88->57|88->57|89->58|92->61|92->61|92->61|95->64|96->65|96->65|96->65|97->66|99->68|99->68|99->68|99->68|99->68|99->68|101->70|101->70|101->70|101->70|101->70|101->70|104->73|105->74|122->91
                  -- GENERATED --
              */
          