
package viewsMnuMantencion.html

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

object mantTblTipoActividadNew extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[Map[String,String],Map[String,String],utilities.UserMnu,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

"""),_display_(/*6.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.50*/("""
	"""),format.raw/*7.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*8.4*/barraTitulo(mapDiccionario, "AGREGAR/CREAR NUEVO TIPO DE ACTIVIDAD", "")),format.raw/*8.76*/("""
		"""),format.raw/*9.3*/("""<form action="/routes3/mantTblTipoActividadNewSave/" method="POST">
			<div class="row  justify-content-md-center">
				<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<tr>
							<td>
								<div class="row">
									<div class="col-xs-6 col-sm-2 col-md-2 col-lg-2">
										<b><font color="#008000">TIPO DE ACTIVIDAD:</font></b>
									</div>
									<div class="col-xs-6 col-sm-10 col-md-10 col-lg-10">
										<input type="text" class="form-control" name="nombreTipoActividad" maxlength="50" onkeydown="return sinReservados(window.event)" required>
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
						<input type="submit"  value="GRABAR TIPO" class="btn btn-success btn-sm rounded-pill btn-block">
					</div>
					<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
						<input type="button"  value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
					</div>
				</div>
			</div>
		</form>
	</div>
""")))}),format.raw/*40.2*/("""




"""),format.raw/*45.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*46.31*/("""{"""),format.raw/*46.32*/("""
		  """),format.raw/*47.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*48.2*/("""}"""),format.raw/*48.3*/(""");
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
                  SOURCE: app/viewsMnuMantencion/mantTblTipoActividadNew.scala.html
                  HASH: cdf7271022372f6045838aa26257afadbb154e23
                  MATRIX: 1025->1|1215->98|1248->106|1264->114|1303->116|1331->119|1399->167|1427->169|1503->220|1595->292|1624->295|2878->1519|2910->1524|3000->1586|3029->1587|3061->1592|3154->1658|3182->1659
                  LINES: 28->1|33->2|35->4|35->4|35->4|37->6|37->6|38->7|39->8|39->8|40->9|71->40|76->45|77->46|77->46|78->47|79->48|79->48
                  -- GENERATED --
              */
          