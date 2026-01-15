
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

object reportFactConsolconEquipos extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
fecha: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	"""),format.raw/*7.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*8.4*/barraTitulo(mapDiccionario, "CONSOLIDADO DE PROFORMAS POR EQUIPOS (NO CONSIDERA AJUSTES A EP)","POR MESES VS " +mapDiccionario("BODEGA")+"/PROYECTO")),format.raw/*8.153*/("""
		"""),format.raw/*9.3*/("""<form action="/reportFactConsolconEquiposRtp/" method="POST" onsubmit="return validarForm();">
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
								<TH>CANTIDAD DE MESES:</TH>
								<td style="text-align:left;">
									<select class="custom-select" name="cantMeses">
										<option value="3">3 meses</option>
										<option value="6">6 meses</option>
										<option value="9">9 meses</option>
										<option value="12">12 meses</option>
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
					"""),_display_(if(mapDiccionario.get("nEmpresa").equals("SM8 DE MEXICO"))/*47.65*/{_display_(Seq[Any](format.raw/*47.66*/("""
						"""),format.raw/*48.7*/("""<input type="submit" id="btnSubmit" value='Exportar a Excel' class="btn btn-primary btn-sm rounded-pill btn-block">
					""")))}else/*49.11*/{_display_(Seq[Any](format.raw/*49.12*/("""
						"""),format.raw/*50.7*/("""<input type="submit" id="btnSubmit" value='Generar Reporte' onclick="document.getElementById('enProceso').style.display='block';" class="btn btn-primary btn-sm rounded-pill btn-block">
					""")))}),format.raw/*51.7*/("""
				"""),format.raw/*52.5*/("""</div>
			</div>
		</form>
	</div>

""")))}),format.raw/*57.2*/("""


"""),format.raw/*60.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*61.31*/("""{"""),format.raw/*61.32*/("""
		  """),format.raw/*62.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*63.2*/("""}"""),format.raw/*63.3*/(""");
	
	const validarForm = () =>"""),format.raw/*65.27*/("""{"""),format.raw/*65.28*/("""
		"""),format.raw/*66.3*/("""$("#btnSubmit").attr('disabled',true);
		return(true);
	"""),format.raw/*68.2*/("""}"""),format.raw/*68.3*/("""
	
	
"""),format.raw/*71.1*/("""</script>




		
		
	
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
                  SOURCE: app/viewsMnuReportes/reportFactConsolconEquipos.scala.html
                  HASH: ff31bbbc84cef28267ccc45f583f8473c17a2ebf
                  MATRIX: 1033->1|1238->113|1270->120|1286->128|1325->130|1354->134|1422->182|1450->184|1526->235|1696->384|1725->387|2319->954|2345->959|3090->1677|3129->1678|3163->1685|3308->1811|3347->1812|3381->1819|3602->2010|3634->2015|3701->2052|3731->2055|3821->2117|3850->2118|3882->2123|3975->2189|4003->2190|4062->2221|4091->2222|4121->2225|4204->2281|4232->2282|4264->2287
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|38->7|39->8|39->8|40->9|54->23|54->23|78->47|78->47|79->48|80->49|80->49|81->50|82->51|83->52|88->57|91->60|92->61|92->61|93->62|94->63|94->63|96->65|96->65|97->66|99->68|99->68|102->71
                  -- GENERATED --
              */
          