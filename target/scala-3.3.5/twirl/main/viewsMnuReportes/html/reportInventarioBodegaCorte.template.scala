
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

object reportInventarioBodegaCorte extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
fechaHoy: String, tipo: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "INVENTARIO POR "+mapDiccionario.get("BODEGA")+"/PROYECTO - "+tipo+" - valorizado","Fecha de Corte")),format.raw/*9.132*/("""
		"""),format.raw/*10.3*/("""<form action="/reportInventarioBodega/" method="POST" onsubmit="return validarForm();">
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
							<TR>
								<td colspan="2" align="center">
									<div class="form-check form-check-inline">
									  <input type="hidden" name="soloVigentes" id="soloVigentes" value="1">
									  <input class="form-check-input" type="checkbox" checked id="inlineCheckbox1" value="" onchange="checkSoloVigente();">
									  <label class="form-check-label" for="inlineCheckbox1">Solo Vigentes</label>
									</div>
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

""")))}),format.raw/*50.2*/("""


"""),format.raw/*53.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*54.31*/("""{"""),format.raw/*54.32*/("""
		  """),format.raw/*55.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*56.2*/("""}"""),format.raw/*56.3*/(""");
	
	const checkSoloVigente = () =>"""),format.raw/*58.32*/("""{"""),format.raw/*58.33*/("""
		"""),format.raw/*59.3*/("""if(document.getElementById("inlineCheckbox1").checked)"""),format.raw/*59.57*/("""{"""),format.raw/*59.58*/("""
			"""),format.raw/*60.4*/("""$("#solovigentes").val("1");
		"""),format.raw/*61.3*/("""}"""),format.raw/*61.4*/("""else"""),format.raw/*61.8*/("""{"""),format.raw/*61.9*/("""
			"""),format.raw/*62.4*/("""$("#solovigentes").val("0");
		"""),format.raw/*63.3*/("""}"""),format.raw/*63.4*/("""
	"""),format.raw/*64.2*/("""}"""),format.raw/*64.3*/(""" 
	
	"""),format.raw/*66.2*/("""const validarForm = () =>"""),format.raw/*66.27*/("""{"""),format.raw/*66.28*/("""
		"""),format.raw/*67.3*/("""$("#btnSubmit").attr('disabled',true);
		return(true);
	"""),format.raw/*69.2*/("""}"""),format.raw/*69.3*/("""
	
"""),format.raw/*71.1*/("""</script>




		
		
	
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
                  SOURCE: app/viewsMnuReportes/reportInventarioBodegaCorte.scala.html
                  HASH: b642009593fc01c50718720ab20e3602bac56258
                  MATRIX: 1041->1|1263->130|1296->138|1312->146|1351->148|1380->152|1448->200|1476->202|1552->253|1701->381|1731->384|2199->825|2224->829|2386->964|2415->972|3286->1813|3316->1816|3406->1878|3435->1879|3467->1884|3560->1950|3588->1951|3652->1987|3681->1988|3711->1991|3793->2045|3822->2046|3853->2050|3911->2081|3939->2082|3970->2086|3998->2087|4029->2091|4087->2122|4115->2123|4144->2125|4172->2126|4204->2131|4257->2156|4286->2157|4316->2160|4399->2216|4427->2217|4457->2220
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|50->19|50->19|54->23|54->23|81->50|84->53|85->54|85->54|86->55|87->56|87->56|89->58|89->58|90->59|90->59|90->59|91->60|92->61|92->61|92->61|92->61|93->62|94->63|94->63|95->64|95->64|97->66|97->66|97->66|98->67|100->69|100->69|102->71
                  -- GENERATED --
              */
          