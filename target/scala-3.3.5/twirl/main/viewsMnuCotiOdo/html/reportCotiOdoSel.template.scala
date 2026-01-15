
package viewsMnuCotiOdo.html

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

object reportCotiOdoSel extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
fechaDesde: String, fechaHasta: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	"""),format.raw/*7.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*8.4*/barraTitulo(mapDiccionario, "REPORTE COTIZACIONES ODO","(SELECCIONAR PERIODO MOVIL)")),format.raw/*8.89*/("""
		"""),format.raw/*9.3*/("""<form action="/routes2/reportCotiOdoRpt/" method="POST" onsubmit="return validarForm();">
			<br><br><br>
			<div class="row  justify-content-md-center">
				<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<TR>
								<TH colspan="2">PERIODO DE COTIZACIONES A CONSIDERAR</TH>
							</TR>
							<TR>
								<TH>FECHA DESDE:</TH>
								<td>
									<input type="date" class="form-control center"
										id="fechaDesde"
							  			name="fechaDesde"
							  			value=""""),_display_(/*24.21*/fechaDesde),format.raw/*24.31*/(""""
					        			required>
								</td>
							</TR>
							<TR>
								<TH>FECHA HASTA:</TH>
								<td>
									<input type="date" class="form-control center"
										id="fechaHasta"
							  			name="fechaHasta"
							  			value=""""),_display_(/*34.21*/fechaHasta),format.raw/*34.31*/(""""
					        			required>
								</td>
							</TR>
							<TR style="background-color: rgb(254, 255, 255)">
								<TH colspan="2"><br>
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
	
	const validarForm = () =>"""),format.raw/*63.27*/("""{"""),format.raw/*63.28*/("""
		"""),format.raw/*64.3*/("""let flag = true;
		var desde = document.getElementById('fechaDesde').value;
		var hasta = document.getElementById('fechaHasta').value;
		if(desde > hasta)"""),format.raw/*67.20*/("""{"""),format.raw/*67.21*/("""
			"""),format.raw/*68.4*/("""flag = false;
			alertify.alert('LA FECHA INICIAL NO PUEDE SER MAYOR A LA FECHA FINAL', function () """),format.raw/*69.87*/("""{"""),format.raw/*69.88*/("""
				"""),format.raw/*70.5*/("""return(flag);
     		"""),format.raw/*71.8*/("""}"""),format.raw/*71.9*/(""");
		"""),format.raw/*72.3*/("""}"""),format.raw/*72.4*/("""
		"""),format.raw/*73.3*/("""return(flag);
	"""),format.raw/*74.2*/("""}"""),format.raw/*74.3*/("""
	
	
	
"""),format.raw/*78.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,fechaDesde:String,fechaHasta:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,fechaDesde,fechaHasta)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,fechaDesde,fechaHasta) => apply(mapDiccionario,mapPermiso,userMnu,fechaDesde,fechaHasta)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotiOdo/reportCotiOdoSel.scala.html
                  HASH: 1b6f84b509f36fff8e0737c1d3e7b05733f18f50
                  MATRIX: 1029->1|1259->138|1291->145|1307->153|1346->155|1375->159|1443->207|1471->209|1547->260|1652->345|1681->348|2321->961|2352->971|2621->1213|2652->1223|3163->1704|3193->1707|3283->1769|3312->1770|3344->1775|3437->1841|3465->1842|3524->1873|3553->1874|3583->1877|3765->2031|3794->2032|3825->2036|3953->2136|3982->2137|4014->2142|4062->2163|4090->2164|4122->2169|4150->2170|4180->2173|4222->2188|4250->2189|4284->2196
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|38->7|39->8|39->8|40->9|55->24|55->24|65->34|65->34|86->55|89->58|90->59|90->59|91->60|92->61|92->61|94->63|94->63|95->64|98->67|98->67|99->68|100->69|100->69|101->70|102->71|102->71|103->72|103->72|104->73|105->74|105->74|109->78
                  -- GENERATED --
              */
          