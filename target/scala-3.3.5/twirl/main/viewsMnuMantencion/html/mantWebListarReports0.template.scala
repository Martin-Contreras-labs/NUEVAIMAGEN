
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

object mantWebListarReports0 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],String,String,Long,Long,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], fechaDesde: String, fechaHasta: String, id_userOperMec: Long, id_equipo: Long):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""    	
"""),_display_(/*3.2*/main("")/*3.10*/ {_display_(Seq[Any](format.raw/*3.12*/("""

	"""),format.raw/*5.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*6.4*/barraTitulo(mapDiccionario, "LISTA DE MIS REPORT INGRESADOS","")),format.raw/*6.68*/("""
		"""),format.raw/*7.3*/("""<form action="/routes3/mantWebListarReports1/" method="POST" onsubmit="return validarForm();">
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
									<input type="hidden" name="id_userOperMec" value=""""),_display_(/*19.61*/id_userOperMec),format.raw/*19.75*/("""">
									<input type="hidden" name="id_equipo" value=""""),_display_(/*20.56*/id_equipo),format.raw/*20.65*/("""">
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
		var desde = Date.parse(document.getElementById('fechaDesde').value);
		var hasta = Date.parse(document.getElementById('fechaHasta').value);
		let diffDias = Math.floor((hasta - desde)/(1000 * 60 * 60 * 24))+1;
		if(desde > hasta)"""),format.raw/*65.20*/("""{"""),format.raw/*65.21*/("""
			"""),format.raw/*66.4*/("""flag = false;
			alertify.alert('LA FECHA INICIAL NO PUEDE SER MAYOR A LA FECHA FINAL', function () """),format.raw/*67.87*/("""{"""),format.raw/*67.88*/("""
				"""),format.raw/*68.5*/("""return(flag);
     		"""),format.raw/*69.8*/("""}"""),format.raw/*69.9*/(""");
		"""),format.raw/*70.3*/("""}"""),format.raw/*70.4*/("""
		
		"""),format.raw/*72.3*/("""if(diffDias > 92)"""),format.raw/*72.20*/("""{"""),format.raw/*72.21*/("""
			"""),format.raw/*73.4*/("""flag = false;
			alertify.alert('EL PERIODO NO PUEDE SER MAYOR A 92 DIAS', function () """),format.raw/*74.74*/("""{"""),format.raw/*74.75*/("""
				"""),format.raw/*75.5*/("""return(flag);
     		"""),format.raw/*76.8*/("""}"""),format.raw/*76.9*/(""");
		"""),format.raw/*77.3*/("""}"""),format.raw/*77.4*/("""
		"""),format.raw/*78.3*/("""return(flag);
	"""),format.raw/*79.2*/("""}"""),format.raw/*79.3*/("""
	
	
"""),format.raw/*82.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],fechaDesde:String,fechaHasta:String,id_userOperMec:Long,id_equipo:Long): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,fechaDesde,fechaHasta,id_userOperMec,id_equipo)

  def f:((Map[String,String],Map[String,String],String,String,Long,Long) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,fechaDesde,fechaHasta,id_userOperMec,id_equipo) => apply(mapDiccionario,mapPermiso,fechaDesde,fechaHasta,id_userOperMec,id_equipo)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuMantencion/mantWebListarReports0.scala.html
                  HASH: 4c18571a36104a530dd9ff275ef94ddfd346e372
                  MATRIX: 1029->1|1270->149|1302->156|1318->164|1357->166|1386->169|1462->220|1546->284|1575->287|2132->817|2167->831|2252->889|2282->898|2444->1033|2475->1043|2744->1285|2775->1295|3187->1677|3217->1680|3307->1742|3336->1743|3368->1748|3461->1814|3489->1815|3548->1846|3577->1847|3607->1850|3883->2098|3912->2099|3943->2103|4071->2203|4100->2204|4132->2209|4180->2230|4208->2231|4240->2236|4268->2237|4301->2243|4346->2260|4375->2261|4406->2265|4521->2352|4550->2353|4582->2358|4630->2379|4658->2380|4690->2385|4718->2386|4748->2389|4790->2404|4818->2405|4850->2410
                  LINES: 28->1|33->2|34->3|34->3|34->3|36->5|37->6|37->6|38->7|50->19|50->19|51->20|51->20|55->24|55->24|65->34|65->34|83->52|86->55|87->56|87->56|88->57|89->58|89->58|91->60|91->60|92->61|96->65|96->65|97->66|98->67|98->67|99->68|100->69|100->69|101->70|101->70|103->72|103->72|103->72|104->73|105->74|105->74|106->75|107->76|107->76|108->77|108->77|109->78|110->79|110->79|113->82
                  -- GENERATED --
              */
          