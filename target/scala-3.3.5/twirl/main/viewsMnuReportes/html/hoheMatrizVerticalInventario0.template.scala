
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

object hoheMatrizVerticalInventario0 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,String,play.twirl.api.HtmlFormat.Appendable] {

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
		"""),format.raw/*10.3*/("""<form action="/hoheMatrizVerticalInventario/" method="POST" onsubmit="return validarForm();">
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
                  SOURCE: app/viewsMnuReportes/hoheMatrizVerticalInventario0.scala.html
                  HASH: f6398b2821fac347d5d8bb98df494666c378cfa3
                  MATRIX: 1036->1|1244->116|1277->124|1293->132|1332->134|1361->138|1429->186|1457->188|1533->239|1621->307|1651->310|2207->839|2236->847|2663->1244|2693->1247|2783->1309|2812->1310|2844->1315|2937->1381|2965->1382|3024->1413|3053->1414|3083->1417|3166->1473|3194->1474|3224->1477
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|53->22|53->22|71->40|74->43|75->44|75->44|76->45|77->46|77->46|79->48|79->48|80->49|82->51|82->51|84->53
                  -- GENERATED --
              */
          