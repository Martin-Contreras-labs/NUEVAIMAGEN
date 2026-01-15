
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

object reportOperadorConsol0 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,String,String,tables.TasasCambio,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
fechaDesde: String, fechaHasta: String, tasas: tables.TasasCambio):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	"""),format.raw/*7.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*8.4*/barraTitulo(mapDiccionario, "CONSOLIDADO POR OPERADORES","(SOLO CANTIDADES)")),format.raw/*8.81*/("""
		"""),format.raw/*9.3*/("""<form action="/reportOperadorConsol1/" method="POST" onsubmit="return validarForm();">
			<br><br><br>
			<div class="row  justify-content-md-center">
				<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<TR>
								<TH colspan="2">PERIODO A CONSIDERAR</TH>
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

""")))}),format.raw/*52.2*/("""


"""),format.raw/*55.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*56.31*/("""{"""),format.raw/*56.32*/("""
		  """),format.raw/*57.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*58.2*/("""}"""),format.raw/*58.3*/(""");
	
	const validarForm = () =>"""),format.raw/*60.27*/("""{"""),format.raw/*60.28*/("""
		"""),format.raw/*61.3*/("""let flag = true;
		var desde = document.getElementById('fechaDesde').value;
		var hasta = document.getElementById('fechaHasta').value;
		if(desde > hasta)"""),format.raw/*64.20*/("""{"""),format.raw/*64.21*/("""
			"""),format.raw/*65.4*/("""flag = false;
			alertify.alert('LA FECHA INICIAL NO PUEDE SER MAYOR A LA FECHA FINAL', function () """),format.raw/*66.87*/("""{"""),format.raw/*66.88*/("""
				"""),format.raw/*67.5*/("""return(flag);
     		"""),format.raw/*68.8*/("""}"""),format.raw/*68.9*/(""");
		"""),format.raw/*69.3*/("""}"""),format.raw/*69.4*/("""
		"""),format.raw/*70.3*/("""return(flag);
	"""),format.raw/*71.2*/("""}"""),format.raw/*71.3*/("""
	
	
"""),format.raw/*74.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,fechaDesde:String,fechaHasta:String,tasas:tables.TasasCambio): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,fechaDesde,fechaHasta,tasas)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,String,String,tables.TasasCambio) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,fechaDesde,fechaHasta,tasas) => apply(mapDiccionario,mapPermiso,userMnu,fechaDesde,fechaHasta,tasas)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuOdo/reportOperadorConsol0.scala.html
                  HASH: 57200fb75206a73e96168444f7437aabda40c03b
                  MATRIX: 1049->1|1306->165|1338->172|1354->180|1393->182|1422->186|1490->234|1518->236|1594->287|1691->364|1720->367|2341->961|2372->971|2641->1213|2672->1223|3084->1605|3114->1608|3204->1670|3233->1671|3265->1676|3358->1742|3386->1743|3445->1774|3474->1775|3504->1778|3686->1932|3715->1933|3746->1937|3874->2037|3903->2038|3935->2043|3983->2064|4011->2065|4043->2070|4071->2071|4101->2074|4143->2089|4171->2090|4203->2095
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|38->7|39->8|39->8|40->9|55->24|55->24|65->34|65->34|83->52|86->55|87->56|87->56|88->57|89->58|89->58|91->60|91->60|92->61|95->64|95->64|96->65|97->66|97->66|98->67|99->68|99->68|100->69|100->69|101->70|102->71|102->71|105->74
                  -- GENERATED --
              */
          