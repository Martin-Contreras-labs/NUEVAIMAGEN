
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

object reportFacturaConsolidado extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
fecha: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	"""),format.raw/*7.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*8.4*/barraTitulo(mapDiccionario, "REPORTE CONSOLIDADO DE EP/PROFORMAS","POR MESES VS " +mapDiccionario("BODEGA")+"/PROYECTO")),format.raw/*8.124*/("""
		"""),format.raw/*9.3*/("""<form action="/reportFacturaConsolidadoRtp/" method="POST" onsubmit="return validarForm();">
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
					"""),_display_(if(mapDiccionario.get("nEmpresa").equals("SM8 DE MEXICO"))/*46.65*/{_display_(Seq[Any](format.raw/*46.66*/("""
						"""),format.raw/*47.7*/("""<input type="submit" id="btnSubmit" value='Exportar a Excel' class="btn btn-primary btn-sm rounded-pill btn-block">
					""")))}else/*48.11*/{_display_(Seq[Any](format.raw/*48.12*/("""
						"""),format.raw/*49.7*/("""<input type="submit" id="btnSubmit" value='Generar Reporte' onclick="document.getElementById('enProceso').style.display='block';" class="btn btn-primary btn-sm rounded-pill btn-block">
					""")))}),format.raw/*50.7*/("""
				"""),format.raw/*51.5*/("""</div>
			</div>
		</form>
	</div>

""")))}),format.raw/*56.2*/("""


"""),format.raw/*59.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*60.31*/("""{"""),format.raw/*60.32*/("""
		  """),format.raw/*61.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*62.2*/("""}"""),format.raw/*62.3*/(""");
	
	const validarForm = () =>"""),format.raw/*64.27*/("""{"""),format.raw/*64.28*/("""
		"""),format.raw/*65.3*/("""$("#btnSubmit").attr('disabled',true);
		return(true);
	"""),format.raw/*67.2*/("""}"""),format.raw/*67.3*/("""
	
	
	
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
                  SOURCE: app/viewsMnuReportes/reportFacturaConsolidado.scala.html
                  HASH: b672c58100de1f33d036a2f0970e1e3b563ef4a3
                  MATRIX: 1031->1|1236->113|1268->120|1284->128|1323->130|1352->134|1420->182|1448->184|1524->235|1665->355|1694->358|2286->923|2312->928|3010->1599|3049->1600|3083->1607|3228->1733|3267->1734|3301->1741|3522->1932|3554->1937|3621->1974|3651->1977|3741->2039|3770->2040|3802->2045|3895->2111|3923->2112|3982->2143|4011->2144|4041->2147|4124->2203|4152->2204|4186->2211
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|38->7|39->8|39->8|40->9|54->23|54->23|77->46|77->46|78->47|79->48|79->48|80->49|81->50|82->51|87->56|90->59|91->60|91->60|92->61|93->62|93->62|95->64|95->64|96->65|98->67|98->67|102->71
                  -- GENERATED --
              */
          