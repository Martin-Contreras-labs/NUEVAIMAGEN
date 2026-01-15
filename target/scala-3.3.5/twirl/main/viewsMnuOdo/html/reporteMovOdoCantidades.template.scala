
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

object reporteMovOdoCantidades extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
fechaDesde: String, fechaHasta: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "PERIODO DE MOVIMIENTOS ODO","(solo cantidades)")),format.raw/*9.81*/("""
		"""),format.raw/*10.3*/("""<form action="/reporteMovOdoCantLista/" method="POST" onsubmit="return validarForm();">
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
							  			value=""""),_display_(/*22.21*/fechaDesde),format.raw/*22.31*/(""""
					        			required>
								</td>
							</TR>
							<TR>
								<TH>FECHA HASTA:</TH>
								<td>
									<input type="date" class="form-control center"
										id="fechaHasta"
							  			name="fechaHasta"
							  			value=""""),_display_(/*32.21*/fechaHasta),format.raw/*32.31*/(""""
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

""")))}),format.raw/*50.2*/("""


"""),format.raw/*53.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*54.31*/("""{"""),format.raw/*54.32*/("""
		  """),format.raw/*55.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*56.2*/("""}"""),format.raw/*56.3*/(""");
	
	const validarForm = () =>"""),format.raw/*58.27*/("""{"""),format.raw/*58.28*/("""
		"""),format.raw/*59.3*/("""let flag = true;
		var desde = document.getElementById('fechaDesde').value;
		var hasta = document.getElementById('fechaHasta').value;
		if(desde > hasta)"""),format.raw/*62.20*/("""{"""),format.raw/*62.21*/("""
			"""),format.raw/*63.4*/("""flag = false;
			alertify.alert('LA FECHA INICIAL NO PUEDE SER MAYOR A LA FECHA FINAL', function () """),format.raw/*64.87*/("""{"""),format.raw/*64.88*/("""
				"""),format.raw/*65.5*/("""return(flag);
     		"""),format.raw/*66.8*/("""}"""),format.raw/*66.9*/(""");
		"""),format.raw/*67.3*/("""}"""),format.raw/*67.4*/("""
		"""),format.raw/*68.3*/("""return(flag);
	"""),format.raw/*69.2*/("""}"""),format.raw/*69.3*/("""
	
"""),format.raw/*71.1*/("""</script>




		
		
	
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
                  SOURCE: app/viewsMnuOdo/reporteMovOdoCantidades.scala.html
                  HASH: 46da9fc3276771eaa4f5bd33e848eadf1acf3d9d
                  MATRIX: 1032->1|1262->138|1295->146|1311->154|1350->156|1379->160|1447->208|1475->210|1551->261|1648->338|1678->341|2225->861|2256->871|2525->1113|2556->1123|2968->1505|2998->1508|3088->1570|3117->1571|3149->1576|3242->1642|3270->1643|3329->1674|3358->1675|3388->1678|3570->1832|3599->1833|3630->1837|3758->1937|3787->1938|3819->1943|3867->1964|3895->1965|3927->1970|3955->1971|3985->1974|4027->1989|4055->1990|4085->1993
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|53->22|53->22|63->32|63->32|81->50|84->53|85->54|85->54|86->55|87->56|87->56|89->58|89->58|90->59|93->62|93->62|94->63|95->64|95->64|96->65|97->66|97->66|98->67|98->67|99->68|100->69|100->69|102->71
                  -- GENERATED --
              */
          