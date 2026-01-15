
package viewsMnuReportes.html

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

object hoheMatrizVerticalInv0Coti extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
fechaHoy: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "REPORTE MATRIZ VERTICAL INVENTARIO","")),format.raw/*9.72*/("""
		"""),format.raw/*10.3*/("""<form action="/hoheMatrizVerticalInvCoti/" method="POST" onsubmit="return validarForm();">
			<br><br><br>
			<div class="row  justify-content-md-center">
				<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<TR>
								<TH>FECHA DE CORTE:</TH>
								<td>
									<input type="date" class="form-control center"
										id="fechaCorte"
							  			name="fechaCorte"
							  			value=""""),_display_(/*22.21*/fechaHoy),format.raw/*22.29*/(""""
					        			required>
								</td>
							</TR>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
			<div class="row  justify-content-md-center">
				<div class="col-xs-4 col-sm-2 col-md-2 col-lg-2">
					<input type="submit" id="btnSubmit" value='Generar Reporte' class="btn btn-primary btn-sm rounded-pill btn-block">
				</div>
			</div>
		</form>
	</div>

""")))}),format.raw/*40.2*/("""


"""),format.raw/*43.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*44.31*/("""{"""),format.raw/*44.32*/("""
		  """),format.raw/*45.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*46.2*/("""}"""),format.raw/*46.3*/(""");
	
	const validarForm = () =>"""),format.raw/*48.27*/("""{"""),format.raw/*48.28*/("""
		"""),format.raw/*49.3*/("""$("#btnSubmit").attr('disabled',true);
		return(true);
	"""),format.raw/*51.2*/("""}"""),format.raw/*51.3*/("""
	
"""),format.raw/*53.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,fechaHoy:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,fechaHoy)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,fechaHoy) => apply(mapDiccionario,mapPermiso,userMnu,fechaHoy)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/hoheMatrizVerticalInv0Coti.scala.html
                  HASH: cbc34a59484b134ef34da61016800e8d252c84b5
                  MATRIX: 1033->1|1241->116|1274->124|1290->132|1329->134|1358->138|1426->186|1454->188|1530->239|1618->307|1648->310|2201->836|2230->844|2657->1241|2687->1244|2777->1306|2806->1307|2838->1312|2931->1378|2959->1379|3018->1410|3047->1411|3077->1414|3160->1470|3188->1471|3218->1474
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|53->22|53->22|71->40|74->43|75->44|75->44|76->45|77->46|77->46|79->48|79->48|80->49|82->51|82->51|84->53
                  -- GENERATED --
              */
          