
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

object grupoNuevoAtributo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,tables.Grupo,List[tables.Unidad],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
grupo: tables.Grupo, listUnidades: List[tables.Unidad]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "AGREGAR NUEVO ATRIBUTO PARA: " + grupo.getNombre(), "")),format.raw/*9.88*/("""
		"""),format.raw/*10.3*/("""<form action="/grupoGrabaAtributo/" method="POST">
			<input type="hidden" name="id_grupo" value=""""),_display_(/*11.49*/grupo/*11.54*/.getId()),format.raw/*11.62*/("""">
			<div class="row  justify-content-md-center">
				<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<tr>
							<td>NOMBRE ATRIBUTO</td>
							<td><input type="text" class="form-control" 
										name="nombreAtributo" 
										maxlength="50" 
										onkeydown="return sinReservados(window.event)"
										required>
							</td>
						</tr>
						<tr>
				 			<td>UNIDAD DE MEDIDA: </td>
				 			<td>
				 				<select class="custom-select" style="min-width:120px" name="id_unidad">
					            	"""),_display_(/*28.20*/for(lista <- listUnidades) yield /*28.46*/{_display_(Seq[Any](format.raw/*28.47*/("""
					                	"""),format.raw/*29.23*/("""<option value=""""),_display_(/*29.39*/lista/*29.44*/.getId()),format.raw/*29.52*/("""">"""),_display_(/*29.55*/lista/*29.60*/.getNombre()),format.raw/*29.72*/("""</option>
									""")))}),format.raw/*30.11*/("""
						        """),format.raw/*31.15*/("""</select>
				 			</td>
				 		</tr>
				 		<tr>
				 			<td>¿ES NUMERICO?: </td>
				 			<td>
				 				<select class="custom-select" style="min-width:120px" name="esNumerico">
					                <option value="1">SI</option>
					                <option value="0">NO</option>
						        </select>
				 			</td>
				 		</tr>
					</table>
				</div>
			</div>
			<div class="noprint">
				<div class="row justify-content-md-center" >
					<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
						<input type="submit"  value="GRABAR ATRIBUTO" class="btn btn-success btn-sm rounded-pill btn-block">
					</div>
					<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
						<input type="button"  value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
					</div>
				</div>
			</div>
		</form>
	</div>
""")))}),format.raw/*58.2*/("""




"""),format.raw/*63.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*64.31*/("""{"""),format.raw/*64.32*/("""
		  """),format.raw/*65.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*66.2*/("""}"""),format.raw/*66.3*/(""");
</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,grupo:tables.Grupo,listUnidades:List[tables.Unidad]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,grupo,listUnidades)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.Grupo,List[tables.Unidad]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,grupo,listUnidades) => apply(mapDiccionario,mapPermiso,userMnu,grupo,listUnidades)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuTablas/grupoNuevoAtributo.scala.html
                  HASH: 05e199b32734cf03df622b2e3fc77749baa6709a
                  MATRIX: 1049->1|1295->154|1328->162|1344->170|1383->172|1411->175|1479->223|1507->225|1583->276|1687->360|1717->363|1843->462|1857->467|1886->475|2514->1076|2556->1102|2595->1103|2646->1126|2689->1142|2703->1147|2732->1155|2762->1158|2776->1163|2809->1175|2860->1195|2903->1210|3785->2062|3817->2067|3907->2129|3936->2130|3968->2135|4061->2201|4089->2202
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|42->11|42->11|42->11|59->28|59->28|59->28|60->29|60->29|60->29|60->29|60->29|60->29|60->29|61->30|62->31|89->58|94->63|95->64|95->64|96->65|97->66|97->66
                  -- GENERATED --
              */
          