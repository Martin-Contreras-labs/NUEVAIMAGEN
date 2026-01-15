
package viewsMnuOdoAppWeb.html

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

object reporteMovOdoAutorizador extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
fechaDesde: String, fechaHasta: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""


	"""),format.raw/*8.2*/("""<!-- menues(mapDiccionario, mapPermiso, userMnu, " ") -->
	<div id="mostrarmostrar" style="display:none;">
	<!-- barraTitulo(mapDiccionario, "PERIODO DE MOVIMIENTOS ODO","(solo cantidades)") -->
		<form action="/routes2/reporteMovOdoAutorizadorDetalle/" method="POST" onsubmit="return validarForm();">
			<br><br><br>
			<div class="row  justify-content-md-center">
				<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<TR>
								<TH>FECHA DESDE:</TH>
								<td>
									<input type="date" class="form-control center"
										id="fechaDesde"
							  			name="fechaDesde"
							  			value=""""),_display_(/*23.21*/fechaDesde),format.raw/*23.31*/(""""
					        			required>
								</td>
							</TR>
							<TR>
								<TH>FECHA HASTA:</TH>
								<td>
									<input type="date" class="form-control center"
										id="fechaHasta"
							  			name="fechaHasta"
							  			value=""""),_display_(/*33.21*/fechaHasta),format.raw/*33.31*/(""""
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
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="SALIR" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href = '/odo'";">
				</div>
			</div>
		</form>
	</div>

""")))}),format.raw/*54.2*/("""


"""),format.raw/*57.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*58.31*/("""{"""),format.raw/*58.32*/("""
		  """),format.raw/*59.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*60.2*/("""}"""),format.raw/*60.3*/(""");
	
	const validarForm = () =>"""),format.raw/*62.27*/("""{"""),format.raw/*62.28*/("""
		"""),format.raw/*63.3*/("""let flag = true;
		var desde = document.getElementById('fechaDesde').value;
		var hasta = document.getElementById('fechaHasta').value;
		if(desde > hasta)"""),format.raw/*66.20*/("""{"""),format.raw/*66.21*/("""
			"""),format.raw/*67.4*/("""flag = false;
			alertify.alert('LA FECHA INICIAL NO PUEDE SER MAYOR A LA FECHA FINAL', function () """),format.raw/*68.87*/("""{"""),format.raw/*68.88*/("""
				"""),format.raw/*69.5*/("""return(flag);
     		"""),format.raw/*70.8*/("""}"""),format.raw/*70.9*/(""");
		"""),format.raw/*71.3*/("""}"""),format.raw/*71.4*/("""
		"""),format.raw/*72.3*/("""return(flag);
	"""),format.raw/*73.2*/("""}"""),format.raw/*73.3*/("""
	
"""),format.raw/*75.1*/("""</script>




		
		
	
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
                  SOURCE: app/viewsMnuOdoAppWeb/reporteMovOdoAutorizador.scala.html
                  HASH: 94b4f9bc151e757cd13de1f9c7c7383ed2985512
                  MATRIX: 1039->1|1269->138|1302->146|1318->154|1357->156|1387->160|2148->894|2179->904|2448->1146|2479->1156|3087->1734|3117->1737|3207->1799|3236->1800|3268->1805|3361->1871|3389->1872|3448->1903|3477->1904|3507->1907|3689->2061|3718->2062|3749->2066|3877->2166|3906->2167|3938->2172|3986->2193|4014->2194|4046->2199|4074->2200|4104->2203|4146->2218|4174->2219|4204->2222
                  LINES: 28->1|34->3|36->5|36->5|36->5|39->8|54->23|54->23|64->33|64->33|85->54|88->57|89->58|89->58|90->59|91->60|91->60|93->62|93->62|94->63|97->66|97->66|98->67|99->68|99->68|100->69|101->70|101->70|102->71|102->71|103->72|104->73|104->73|106->75
                  -- GENERATED --
              */
          