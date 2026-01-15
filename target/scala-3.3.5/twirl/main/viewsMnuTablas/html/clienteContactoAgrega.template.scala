
package viewsMnuTablas.html

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

object clienteContactoAgrega extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,tables.Cliente,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
cliente: tables.Cliente):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "NUEVO CONTACTO DE CLIENTE/PROPIETARIO", "")),format.raw/*9.76*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<form id="form" method="post" action="/clienteContactoGraba/">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<tr>
							<td colspan="2" style="text-align: left;">
								<input type="hidden" name="id_cliente" value=""""),_display_(/*16.56*/cliente/*16.63*/.getId()),format.raw/*16.71*/("""">
								<h5>CLIENTE: """),_display_(/*17.23*/cliente/*17.30*/.getNickName()),format.raw/*17.44*/("""</h5>
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">NOMBRE: </td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="nombre"
									onkeydown="return sinReservados(window.event)"
									autocomplete="off"
									maxlength="100"
									required>
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">CARGO: </td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="cargo"
									onkeydown="return sinReservados(window.event)"
									autocomplete="off"
									maxlength="100">
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">TELEFONO FIJO: </td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="fijo"
									autocomplete="off"
									maxlength="100">
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">TELEFONO MOVIL: </td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="movil"
									autocomplete="off"
									maxlength="100">
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">E-MAIL: </td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="mail"
									autocomplete="off"
									maxlength="100">
							</td>
						</tr>
					</table>
					<div class="noprint">
						<div class="row justify-content-md-center" >
							<div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
								<input type="submit"  value="GRABAR CONTACTO" class="btn btn-success btn-sm rounded-pill btn-block">
							</div>
							<div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
								<input type="button" value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/clienteModifica/"""),_display_(/*75.141*/cliente/*75.148*/.getId()),format.raw/*75.156*/("""';">
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

""")))}),format.raw/*84.2*/("""



"""),format.raw/*88.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*89.31*/("""{"""),format.raw/*89.32*/("""
		  """),format.raw/*90.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*91.2*/("""}"""),format.raw/*91.3*/(""");
</script>

		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,cliente:tables.Cliente): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,cliente)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.Cliente) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,cliente) => apply(mapDiccionario,mapPermiso,userMnu,cliente)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuTablas/clienteContactoAgrega.scala.html
                  HASH: 0b4a5502cb150c15fd9ec57a595ece77b63037e5
                  MATRIX: 1034->1|1249->123|1282->131|1298->139|1337->141|1365->144|1433->192|1461->194|1537->245|1629->317|1659->320|2047->681|2063->688|2092->696|2144->721|2160->728|2195->742|4126->2645|4143->2652|4173->2660|4288->2745|4319->2749|4409->2811|4438->2812|4470->2817|4563->2883|4591->2884
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|47->16|47->16|47->16|48->17|48->17|48->17|106->75|106->75|106->75|115->84|119->88|120->89|120->89|121->90|122->91|122->91
                  -- GENERATED --
              */
          