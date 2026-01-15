
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

object reportInventarioGrupoCorte extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
fechaHoy: String, tipo: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "INVENTARIO - " + tipo + " (por grupo/familia valorizado)","Fecha de Corte")),format.raw/*9.108*/("""
		"""),format.raw/*10.3*/("""<form action="/reportInventarioGrupo/" method="POST" onsubmit="return validarForm();">
			<br><br><br>
			<div class="row  justify-content-md-center">
				<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<TR>
								<TH>FECHA DE CORTE:</TH>
								<td>
									<input type="hidden" name="tipo" value=""""),_display_(/*19.51*/tipo),format.raw/*19.55*/("""">
									<input type="date" class="form-control center"
										id="fechaCorte"
							  			name="fechaCorte"
							  			value=""""),_display_(/*23.21*/fechaHoy),format.raw/*23.29*/(""""
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

""")))}),format.raw/*41.2*/("""


"""),format.raw/*44.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*45.31*/("""{"""),format.raw/*45.32*/("""
		  """),format.raw/*46.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*47.2*/("""}"""),format.raw/*47.3*/(""");
	
	const validarForm = () =>"""),format.raw/*49.27*/("""{"""),format.raw/*49.28*/("""
		"""),format.raw/*50.3*/("""$("#btnSubmit").attr('disabled',true);
		return(true);
	"""),format.raw/*52.2*/("""}"""),format.raw/*52.3*/("""
	
"""),format.raw/*54.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,fechaHoy:String,tipo:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,fechaHoy,tipo)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,fechaHoy,tipo) => apply(mapDiccionario,mapPermiso,userMnu,fechaHoy,tipo)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reportInventarioGrupoCorte.scala.html
                  HASH: 8ba172e01b0233784e09f5ade63eaf5a01e76f69
                  MATRIX: 1040->1|1262->130|1295->138|1311->146|1350->148|1379->152|1447->200|1475->202|1551->253|1676->357|1706->360|2173->800|2198->804|2360->939|2389->947|2816->1344|2846->1347|2936->1409|2965->1410|2997->1415|3090->1481|3118->1482|3177->1513|3206->1514|3236->1517|3319->1573|3347->1574|3377->1577
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|50->19|50->19|54->23|54->23|72->41|75->44|76->45|76->45|77->46|78->47|78->47|80->49|80->49|81->50|83->52|83->52|85->54
                  -- GENERATED --
              */
          