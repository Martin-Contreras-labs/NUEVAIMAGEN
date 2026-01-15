
package viewsMnuCotizar.html

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

object cotizaSolucionAgrega extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[Map[String,String],Map[String,String],utilities.UserMnu,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

"""),_display_(/*6.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.50*/("""
	"""),format.raw/*7.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*8.4*/barraTitulo(mapDiccionario, "AGREGAR/CREAR NUEVA SOLUCION COTIZACION", "")),format.raw/*8.78*/("""
		"""),format.raw/*9.3*/("""<form action="/routes2/cotizaSolucionNuevo/" method="POST" onsubmit="return validarForm();">
			<div class="row  justify-content-md-center">
				<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<tr>
							<td>
								<div class="row">
									<div class="col-xs-6 col-sm-2 col-md-2 col-lg-2">
										<b><font color="#008000">SOLUCION:</font></b>
									</div>
									<div class="col-xs-6 col-sm-10 col-md-10 col-lg-10">
										<input type="text" class="form-control" 
											name="nombreSolucion" 
											maxlength="50" 
											required>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="noprint">
				<div class="row justify-content-md-center" >
					<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
						<input type="submit" id="btnSubmit"  value="GRABAR SOLUCION" class="btn btn-success btn-sm rounded-pill btn-block">
					</div>
					<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
						<input type="button"  value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
					</div>
				</div>
			</div>
		</form>
	</div>
""")))}),format.raw/*43.2*/("""




"""),format.raw/*48.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*49.31*/("""{"""),format.raw/*49.32*/("""
		  """),format.raw/*50.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*51.2*/("""}"""),format.raw/*51.3*/(""");
	
	const validarForm = () =>"""),format.raw/*53.27*/("""{"""),format.raw/*53.28*/("""
		"""),format.raw/*54.3*/("""$("#btnSubmit").attr('disabled',true);
		return(true);
	"""),format.raw/*56.2*/("""}"""),format.raw/*56.3*/("""
"""),format.raw/*57.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu) => apply(mapDiccionario,mapPermiso,userMnu)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizar/cotizaSolucionAgrega.scala.html
                  HASH: 9c470ba5a20bbc444f042f201c2f408493640685
                  MATRIX: 1019->1|1209->98|1242->106|1258->114|1297->116|1325->119|1393->167|1421->169|1497->220|1591->294|1620->297|2893->1540|2925->1545|3015->1607|3044->1608|3076->1613|3169->1679|3197->1680|3256->1711|3285->1712|3315->1715|3398->1771|3426->1772|3454->1773
                  LINES: 28->1|33->2|35->4|35->4|35->4|37->6|37->6|38->7|39->8|39->8|40->9|74->43|79->48|80->49|80->49|81->50|82->51|82->51|84->53|84->53|85->54|87->56|87->56|88->57
                  -- GENERATED --
              */
          