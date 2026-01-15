
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

object proveedorContactoAgrega extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,tables.Proveedor,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
proveedor: tables.Proveedor):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "NUEVO CONTACTO DEL PROVEEDOR", "")),format.raw/*9.67*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<form id="form" method="post" action="/proveedorContactoGraba/">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<tr>
							<td colspan="2" style="text-align: left;">
								<input type="hidden" name="id_proveedor" value=""""),_display_(/*16.58*/proveedor/*16.67*/.getId()),format.raw/*16.75*/("""">
								<h5>PROVEEDOR: """),_display_(/*17.25*/proveedor/*17.34*/.getNickName()),format.raw/*17.48*/("""</h5>
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
								<input type="button" value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/proveedorModifica/"""),_display_(/*75.143*/proveedor/*75.152*/.getId()),format.raw/*75.160*/("""';">
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

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,proveedor:tables.Proveedor): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,proveedor)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.Proveedor) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,proveedor) => apply(mapDiccionario,mapPermiso,userMnu,proveedor)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuTablas/proveedorContactoAgrega.scala.html
                  HASH: fc58d4878e0610f35ee2016c060a088e9e8f3ab3
                  MATRIX: 1038->1|1257->127|1290->135|1306->143|1345->145|1373->148|1441->196|1469->198|1545->249|1628->312|1658->315|2050->680|2068->689|2097->697|2151->724|2169->733|2204->747|4137->2652|4156->2661|4186->2669|4301->2754|4332->2758|4422->2820|4451->2821|4483->2826|4576->2892|4604->2893
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|47->16|47->16|47->16|48->17|48->17|48->17|106->75|106->75|106->75|115->84|119->88|120->89|120->89|121->90|122->91|122->91
                  -- GENERATED --
              */
          