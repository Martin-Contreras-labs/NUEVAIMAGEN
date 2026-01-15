
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

object historiaQr extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[String,String,tables.Equipo,Double,String,List[tables.HojaVida],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(nEmpresa: String, base: String, equipo: tables.Equipo, stock: Double, ubicacion: String, lista: List[tables.HojaVida]):play.twirl.api.HtmlFormat.Appendable = {
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
	        		<th style="font-size:20px;text-align:center;">FECHA</th>
	        		<th style="font-size:20px;text-align:center;">TIPO</th>
	        		<th style="font-size:20px;text-align:center;">TRABAJO HECHO</th>
				</TR>
			</thead>
			<tbody>
				"""),_display_(/*56.6*/for(list <- lista) yield /*56.24*/{_display_(Seq[Any](format.raw/*56.25*/("""
					"""),format.raw/*57.6*/("""<TR>
						<td class="font-weight-bold" style="font-size:20px;color:blue;text-align:center">"""),_display_(/*58.89*/list/*58.93*/.fechaInicio.replaceAll("-","/")),format.raw/*58.125*/("""</td>
						<td class="font-weight-bold" style="font-size:20px;color:blue;text-align:left">"""),_display_(/*59.87*/list/*59.91*/.tipoTrabajoNombre.toUpperCase()),format.raw/*59.123*/("""</td>
						<td class="font-weight-bold" style="font-size:20px;color:blue;text-align:left">"""),_display_(/*60.87*/list/*60.91*/.trabajoHecho.toUpperCase()),format.raw/*60.118*/("""</td>
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

  def render(nEmpresa:String,base:String,equipo:tables.Equipo,stock:Double,ubicacion:String,lista:List[tables.HojaVida]): play.twirl.api.HtmlFormat.Appendable = apply(nEmpresa,base,equipo,stock,ubicacion,lista)

  def f:((String,String,tables.Equipo,Double,String,List[tables.HojaVida]) => play.twirl.api.HtmlFormat.Appendable) = (nEmpresa,base,equipo,stock,ubicacion,lista) => apply(nEmpresa,base,equipo,stock,ubicacion,lista)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuQr/historiaQr.scala.html
                  HASH: b5e290f659b05a675e07046fdf015f983fdc6130
                  MATRIX: 1012->1|1225->121|1252->123|1277->140|1316->142|1346->146|1728->500|1746->508|1782->522|2144->856|2160->862|2203->883|2586->1238|2602->1244|2645->1265|3012->1604|3028->1610|3070->1630|3158->1691|3197->1692|3230->1698|3563->2003|3582->2012|3618->2026|3704->2069|3736->2074|4116->2428|4150->2446|4189->2447|4222->2453|4342->2546|4355->2550|4409->2582|4528->2674|4541->2678|4595->2710|4714->2802|4727->2806|4776->2833|4828->2855|4859->2859|5113->3083
                  LINES: 28->1|33->2|34->3|34->3|34->3|38->7|45->14|45->14|45->14|52->21|52->21|52->21|59->28|59->28|59->28|66->35|66->35|66->35|70->39|70->39|71->40|74->43|74->43|74->43|77->46|78->47|87->56|87->56|87->56|88->57|89->58|89->58|89->58|90->59|90->59|90->59|91->60|91->60|91->60|93->62|94->63|113->82
                  -- GENERATED --
              */
          