
package viewsMnuCotizar.html

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

object cotizaEstadoAgrega extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[Map[String,String],Map[String,String],utilities.UserMnu,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

"""),_display_(/*6.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.50*/("""
	"""),format.raw/*7.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*8.4*/barraTitulo(mapDiccionario, "AGREGAR/CREAR NUEVO ESTADO COTIZACION", "")),format.raw/*8.76*/("""
		"""),format.raw/*9.3*/("""<form action="/cotizaEstadoNuevo/" method="POST" onsubmit="return validarForm();">
			<div class="row  justify-content-md-center">
				<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<tr>
							<td>
								<div class="row">
									<div class="col-xs-6 col-sm-2 col-md-2 col-lg-2">
										<b><font color="#008000">ESTADO:</font></b>
									</div>
									<div class="col-xs-6 col-sm-10 col-md-10 col-lg-10">
										<input type="text" class="form-control" 
											name="nombreEstado" 
											maxlength="50" 
											onkeydown="return sinReservados(window.event)"
											required>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="noprint">
				<div class="row justify-content-md-center" >
					<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
						<input type="submit" id="btnSubmit"  value="GRABAR ESTADO" class="btn btn-success btn-sm rounded-pill btn-block">
					</div>
					<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
						<input type="button"  value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
					</div>
				</div>
			</div>
		</form>
	</div>
""")))}),format.raw/*44.2*/("""




"""),format.raw/*49.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*50.31*/("""{"""),format.raw/*50.32*/("""
		  """),format.raw/*51.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*52.2*/("""}"""),format.raw/*52.3*/(""");
	
	const validarForm = () =>"""),format.raw/*54.27*/("""{"""),format.raw/*54.28*/("""
		"""),format.raw/*55.3*/("""$("#btnSubmit").attr('disabled',true);
		return(true);
	"""),format.raw/*57.2*/("""}"""),format.raw/*57.3*/("""
"""),format.raw/*58.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu) => apply(mapDiccionario,mapPermiso,userMnu)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizar/cotizaEstadoAgrega.scala.html
                  HASH: f14e04adde51b59063a41e84a248abd96e9f3b2e
                  MATRIX: 1017->1|1207->98|1240->106|1256->114|1295->116|1323->119|1391->167|1419->169|1495->220|1587->292|1616->295|2931->1580|2963->1585|3053->1647|3082->1648|3114->1653|3207->1719|3235->1720|3294->1751|3323->1752|3353->1755|3436->1811|3464->1812|3492->1813
                  LINES: 28->1|33->2|35->4|35->4|35->4|37->6|37->6|38->7|39->8|39->8|40->9|75->44|80->49|81->50|81->50|82->51|83->52|83->52|85->54|85->54|86->55|88->57|88->57|89->58
                  -- GENERATED --
              */
          