
package viewsMnuOdo.html

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

object reportOdoConsolidado extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
fecha: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	"""),format.raw/*7.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*8.4*/barraTitulo(mapDiccionario, "REPORTE ODO CONSOLIDADO DE EP/PROFORMAS","POR MESES VS " +mapDiccionario("BODEGA")+"/PROYECTO")),format.raw/*8.128*/("""
		"""),format.raw/*9.3*/("""<form action="/reportOdoConsolidadoRtp/" method="POST">
			<br><br><br>
			<div class="row  justify-content-md-center">
				<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<TR>
								<TH colspan="2">CONSOLIDADAR POR:</TH>
							</TR>
							<TR>
								<TH>ULTIMO MES:</TH>
								<td>
									<input type="date" class="form-control center"
							  			name="fecha"
							  			value=""""),_display_(/*23.21*/fecha),format.raw/*23.26*/(""""
					        			required>
								</td>
							</TR>
							<TR>
								<TH>CANTIDAD DE MESES::</TH>
								<td style="text-align:left;">
									<select class="custom-select" name="cantMeses">
										<option value="12">12 meses</option>
										<option value="3">3 meses</option>
										<option value="6">6 meses</option>
										<option value="9">9 meses</option>
										<option value="12">12 meses</option>
										<option value="18">18 meses</option>
										<option value="24">24 meses</option>
									</select>
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
					<input type="submit" value='Generar Reporte' class="btn btn-primary btn-sm rounded-pill btn-block">
				</div>
			</div>
		</form>
	</div>

""")))}),format.raw/*55.2*/("""


"""),format.raw/*58.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*59.31*/("""{"""),format.raw/*59.32*/("""
		  """),format.raw/*60.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*61.2*/("""}"""),format.raw/*61.3*/(""");
	
	
	
</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,fecha:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,fecha)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,fecha) => apply(mapDiccionario,mapPermiso,userMnu,fecha)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuOdo/reportOdoConsolidado.scala.html
                  HASH: dd380b7f30ca90f965d965766ec3eec75257c6da
                  MATRIX: 1022->1|1227->113|1259->120|1275->128|1314->130|1343->134|1411->182|1439->184|1515->235|1660->359|1689->362|2244->890|2270->895|3195->1790|3225->1793|3315->1855|3344->1856|3376->1861|3469->1927|3497->1928
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|38->7|39->8|39->8|40->9|54->23|54->23|86->55|89->58|90->59|90->59|91->60|92->61|92->61
                  -- GENERATED --
              */
          