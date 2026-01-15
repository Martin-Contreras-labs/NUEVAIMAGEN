
package viewsMnuTablas.html

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

object dibujanteAgrega extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[Map[String,String],Map[String,String],utilities.UserMnu,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

"""),_display_(/*6.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.50*/("""
	"""),format.raw/*7.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*8.4*/barraTitulo(mapDiccionario, "AGREGAR NUEVO DIBUJANTE/PROYECTISTA", "")),format.raw/*8.74*/("""
		"""),format.raw/*9.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<form id="form" method="post" action="/routes2/dibujanteGraba/">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<tr>
							<td style="text-align:left;">NOMBRE: </td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="nombre"
									autocomplete="off"
									maxlength="100"
								    required
									onchange="value = value.trim();">
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">OBSERVACIONES: </td>
							<td style="text-align:left;">
								<textarea class='form-control form-control-sm' rows='3'
									name="observaciones"
									autocomplete="off"></textarea>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div id="mensajePorProyecto" align="center"></div>
							</td>
						</tr>
					</table>
					<div class="noprint">
						<div class="row justify-content-md-center" >
							<div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
								<input type="submit"  value="GRABAR DIBUJANTE" class="btn btn-success btn-sm rounded-pill btn-block">
							</div>
							<div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
								<input type="button" value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/routes2/dibujanteMantencion/';">
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

""")))}),format.raw/*53.2*/("""



"""),format.raw/*57.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*59.31*/("""{"""),format.raw/*59.32*/("""
	  	"""),format.raw/*60.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*61.2*/("""}"""),format.raw/*61.3*/(""");
	


</script>

		
	
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
                  SOURCE: app/viewsMnuTablas/dibujanteAgrega.scala.html
                  HASH: 0df1b7ef6345356fe3822209d171d852ffb69691
                  MATRIX: 1013->1|1203->98|1236->106|1252->114|1291->116|1319->119|1387->167|1415->169|1491->220|1581->290|1610->293|3152->1805|3183->1809|3274->1872|3303->1873|3335->1878|3428->1944|3456->1945
                  LINES: 28->1|33->2|35->4|35->4|35->4|37->6|37->6|38->7|39->8|39->8|40->9|84->53|88->57|90->59|90->59|91->60|92->61|92->61
                  -- GENERATED --
              */
          