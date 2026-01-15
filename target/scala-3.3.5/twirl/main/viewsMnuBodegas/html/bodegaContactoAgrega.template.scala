
package viewsMnuBodegas.html

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

object bodegaContactoAgrega extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
bodega: tables.BodegaEmpresa):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "NUEVO CONTACTO DE "+mapDiccionario.get("BODEGA")+"/PROYECTO", "")),format.raw/*9.98*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<form id="form" method="post" action="/bodegaContactoGraba/" onsubmit="return validarForm();">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<tr>
							<td colspan="2" style="text-align: left;">
								<input type="hidden" name="id_bodega" value=""""),_display_(/*16.55*/bodega/*16.61*/.getId()),format.raw/*16.69*/("""">
								<h5>"""),_display_(/*17.14*/mapDiccionario/*17.28*/.get("BODEGA")),format.raw/*17.42*/("""/PROYECTO: """),_display_(/*17.54*/bodega/*17.60*/.getNombre()),format.raw/*17.72*/("""</h5>
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
								<input type="submit"  id="btnSubmit" value="GRABAR CONTACTO" class="btn btn-success btn-sm rounded-pill btn-block">
							</div>
							<div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
								<input type="button" value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/bodegaModificaContacto/"""),_display_(/*75.148*/bodega/*75.154*/.getId()),format.raw/*75.162*/("""';">
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
	
	const validarForm = () =>"""),format.raw/*93.27*/("""{"""),format.raw/*93.28*/("""
		"""),format.raw/*94.3*/("""$("#btnSubmit").attr('disabled',true);
		return(true);
	"""),format.raw/*96.2*/("""}"""),format.raw/*96.3*/("""
"""),format.raw/*97.1*/("""</script>

		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,bodega:tables.BodegaEmpresa): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,bodega)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,bodega) => apply(mapDiccionario,mapPermiso,userMnu,bodega)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuBodegas/bodegaContactoAgrega.scala.html
                  HASH: c611686bb65d46a8e29a13ec04bf032cb515bba0
                  MATRIX: 1040->1|1260->128|1293->136|1309->144|1348->146|1376->149|1444->197|1472->199|1548->250|1662->344|1692->347|2111->739|2126->745|2155->753|2198->769|2221->783|2256->797|2295->809|2310->815|2343->827|4296->2752|4312->2758|4342->2766|4457->2851|4488->2855|4578->2917|4607->2918|4639->2923|4732->2989|4760->2990|4819->3021|4848->3022|4878->3025|4961->3081|4989->3082|5017->3083
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|47->16|47->16|47->16|48->17|48->17|48->17|48->17|48->17|48->17|106->75|106->75|106->75|115->84|119->88|120->89|120->89|121->90|122->91|122->91|124->93|124->93|125->94|127->96|127->96|128->97
                  -- GENERATED --
              */
          