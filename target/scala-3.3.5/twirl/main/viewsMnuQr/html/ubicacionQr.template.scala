
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

object ubicacionQr extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[String,String,tables.Equipo,Double,String,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(nEmpresa: String, base: String, equipo: tables.Equipo, stock: Double, ubicacion: String, lista: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
"""),_display_(/*3.2*/mainQR("MADA_QR")/*3.19*/ {_display_(Seq[Any](format.raw/*3.21*/("""



"""),format.raw/*7.1*/("""<div class="container">
  	<br><br><br><br>
  	
  	<form>
  		<div class="form-group row">
    		<label for="inputEmpresa" class="col-sm-3 col-form-label"  style="font-size:30px">Propietario:</label>
		    <div class="col-sm-9">
		      <input type="text" class="form-control font-weight-bold" id="inputEmpresa" style="font-size:30px;color:blue" value=""""),_display_(/*14.126*/nEmpresa/*14.134*/.toUpperCase()),format.raw/*14.148*/("""" disabled>
		    </div>
  		</div>
  		<br>
  		<div class="form-group row">
    		<label for="inputCodigo" class="col-sm-3 col-form-label"  style="font-size:30px">Código:</label>
		    <div class="col-sm-9">
		      <input type="text" class="form-control font-weight-bold" id="inputCodigo" style="font-size:30px;color:blue" value=""""),_display_(/*21.125*/equipo/*21.131*/.codigo.toUpperCase()),format.raw/*21.152*/("""" disabled>
		    </div>
  		</div>
  		<br>
  		<div class="form-group row">
    		<label for="textAreaDecripcion" class="col-sm-3 col-form-label"  style="font-size:30px">Descripción:</label>
		    <div class="col-sm-9">
		      <textarea class="form-control font-weight-bold" id="textAreaDecripcion" rows="2" style="font-size:30px;color:blue" disabled>"""),_display_(/*28.134*/equipo/*28.140*/.nombre.toUpperCase()),format.raw/*28.161*/("""</textarea>
		    </div>
  		</div>
  		<br>
  		<div class="form-group row">
    		<label for="textAreaGrupo" class="col-sm-3 col-form-label"  style="font-size:30px">Grupo:</label>
		    <div class="col-sm-9">
		      <textarea class="form-control font-weight-bold" id="textAreaGrupo" rows="2" style="font-size:30px;color:blue" disabled>"""),_display_(/*35.129*/equipo/*35.135*/.grupo.toUpperCase()),format.raw/*35.155*/("""</textarea>
		    </div>
  		</div>
  		<br>
  		"""),_display_(if(stock<2)/*39.17*/{_display_(Seq[Any](format.raw/*39.18*/("""
	  		"""),format.raw/*40.6*/("""<div class="form-group row">
	    		<label for="textAreaUbicacion" class="col-sm-3 col-form-label"  style="font-size:30px">Ubicación:</label>
			    <div class="col-sm-9">
			      <textarea class="form-control font-weight-bold" id="textAreaUbicacion" rows="2" style="font-size:30px;color:blue" disabled>"""),_display_(/*43.134*/ubicacion/*43.143*/.toUpperCase()),format.raw/*43.157*/("""</textarea>
			    </div>
	  		</div>
  		""")))} else {null} ),format.raw/*46.6*/("""
  		"""),format.raw/*47.5*/("""<table class="table table-bordered table-fluid">
  			<thead style="background-color: #eeeeee">
				<TR>
	        		<th style="font-size:20px;text-align:center;">TIPO</th>
	        		<th style="font-size:20px;text-align:center;">BODEGA</th>
	        		<th style="font-size:20px;text-align:center;">STOCK</th>
				</TR>
			</thead>
			<tbody>
				"""),_display_(/*56.6*/for(list <- lista) yield /*56.24*/{_display_(Seq[Any](format.raw/*56.25*/("""
					"""),format.raw/*57.6*/("""<TR>
						<td class="font-weight-bold" style="font-size:20px;color:blue;text-align:left">"""),_display_(/*58.87*/list/*58.91*/.get(0).toUpperCase()),format.raw/*58.112*/("""</td>
						<td class="font-weight-bold" style="font-size:20px;color:blue;text-align:left">"""),_display_(/*59.87*/list/*59.91*/.get(1).toUpperCase()),format.raw/*59.112*/("""</td>
						<td class="font-weight-bold" style="font-size:20px;color:blue;text-align:right">"""),_display_(/*60.88*/list/*60.92*/.get(2)),format.raw/*60.99*/("""</td>
					</TR>
				""")))}),format.raw/*62.6*/("""
			"""),format.raw/*63.4*/("""</tbody>
  		</table>
	</form>
  	
  	<br><br>
  	<div align="center">
  		<button type="button" class="btn btn-info btn-lg" style="font-size:30px" onclick="history.go(-1)">VOLVER</button>
	</div>
	<br><br>
</div>









""")))}),format.raw/*82.2*/("""
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
                  SOURCE: app/viewsMnuQr/ubicacionQr.scala.html
                  HASH: f8f0b89f23edac996272d4f74d87560752aac4a0
                  MATRIX: 1010->1|1220->118|1247->120|1272->137|1311->139|1341->143|1723->497|1741->505|1777->519|2139->853|2155->859|2198->880|2581->1235|2597->1241|2640->1262|3007->1601|3023->1607|3065->1627|3153->1688|3192->1689|3225->1695|3558->2000|3577->2009|3613->2023|3699->2066|3731->2071|4104->2418|4138->2436|4177->2437|4210->2443|4328->2534|4341->2538|4384->2559|4503->2651|4516->2655|4559->2676|4679->2769|4692->2773|4720->2780|4772->2802|4803->2806|5057->3030
                  LINES: 28->1|33->2|34->3|34->3|34->3|38->7|45->14|45->14|45->14|52->21|52->21|52->21|59->28|59->28|59->28|66->35|66->35|66->35|70->39|70->39|71->40|74->43|74->43|74->43|77->46|78->47|87->56|87->56|87->56|88->57|89->58|89->58|89->58|90->59|90->59|90->59|91->60|91->60|91->60|93->62|94->63|113->82
                  -- GENERATED --
              */
          