
package viewsMnuMantencion.html

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

object mantTblActividadUpdate extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,tables.MantActividad,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
actividad: tables.MantActividad):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "MODIFICAR/ELIMINAR ACTIVIDAD", "")),format.raw/*9.67*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<table class="table table-sm table-bordered table-condensed table-fluid">
					<tr>
						<td>
							<div class="row">
								<div class="col-xs-6 col-sm-2 col-md-2 col-lg-2">
									<b><font color="#008000">ACTIVIDAD:</font></b>
								</div>
								<div class="col-xs-6 col-sm-10 col-md-10 col-lg-10">
									<input type="hidden" name="id_actividad" value=""""),_display_(/*20.59*/actividad/*20.68*/.getId()),format.raw/*20.76*/("""">
									<input type="text" class="form-control"
										id="nombre"
										maxlength="50"
										onkeydown="return sinReservados(window.event)"
										value=""""),_display_(/*25.19*/actividad/*25.28*/.getNombre()),format.raw/*25.40*/(""""
										onchange="value = value.trim(); modificarActividad(id)">
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
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/routes3/mantTblActividadMant/';">
				</div>
			</div>
	</div>
	
""")))}),format.raw/*42.2*/("""




"""),format.raw/*47.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*48.31*/("""{"""),format.raw/*48.32*/("""
		  """),format.raw/*49.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*50.2*/("""}"""),format.raw/*50.3*/(""");
	
	const modificarActividad = (campo) => """),format.raw/*52.40*/("""{"""),format.raw/*52.41*/("""
		"""),format.raw/*53.3*/("""let valor = $("#"+campo).val();
		var formData = new FormData();
	  	formData.append('campo',campo);
	  	formData.append('id_actividad','"""),_display_(/*56.38*/actividad/*56.47*/.getId()),format.raw/*56.55*/("""');
	  	formData.append('valor',valor);
        $.ajax("""),format.raw/*58.16*/("""{"""),format.raw/*58.17*/("""
            """),format.raw/*59.13*/("""url: "/routes3/modificaActividadPorCampoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*66.36*/("""{"""),format.raw/*66.37*/("""
	     		"""),format.raw/*67.9*/("""if(respuesta=="existe")"""),format.raw/*67.32*/("""{"""),format.raw/*67.33*/("""
	     			"""),format.raw/*68.10*/("""alertify.alert('El nombre de la actividad ya existe, intente con otro', function () """),format.raw/*68.94*/("""{"""),format.raw/*68.95*/("""
	     				"""),format.raw/*69.11*/("""$("#nombre").val(""""),_display_(/*69.30*/actividad/*69.39*/.getNombre()),format.raw/*69.51*/("""");
		     		"""),format.raw/*70.10*/("""}"""),format.raw/*70.11*/(""");
	     		"""),format.raw/*71.9*/("""}"""),format.raw/*71.10*/("""else if(respuesta=="error")"""),format.raw/*71.37*/("""{"""),format.raw/*71.38*/("""
	     			"""),format.raw/*72.10*/("""alertify.alert(msgError, function () """),format.raw/*72.47*/("""{"""),format.raw/*72.48*/("""
		     			"""),format.raw/*73.11*/("""location.href = "/";
		     		"""),format.raw/*74.10*/("""}"""),format.raw/*74.11*/(""");
	     		"""),format.raw/*75.9*/("""}"""),format.raw/*75.10*/("""
	     	"""),format.raw/*76.8*/("""}"""),format.raw/*76.9*/("""
        """),format.raw/*77.9*/("""}"""),format.raw/*77.10*/(""");
	"""),format.raw/*78.2*/("""}"""),format.raw/*78.3*/("""
	
"""),format.raw/*80.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,actividad:tables.MantActividad): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,actividad)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.MantActividad) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,actividad) => apply(mapDiccionario,mapPermiso,userMnu,actividad)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuMantencion/mantTblActividadUpdate.scala.html
                  HASH: ff5dd7359d3af5f5779a4f4307e30f168382d029
                  MATRIX: 1045->1|1268->131|1301->139|1317->147|1356->149|1384->152|1452->200|1480->202|1556->253|1639->316|1669->319|2167->790|2185->799|2214->807|2415->981|2433->990|2466->1002|2961->1467|2993->1472|3083->1534|3112->1535|3144->1540|3237->1606|3265->1607|3337->1651|3366->1652|3396->1655|3561->1793|3579->1802|3608->1810|3691->1865|3720->1866|3761->1879|4039->2129|4068->2130|4104->2139|4155->2162|4184->2163|4222->2173|4334->2257|4363->2258|4402->2269|4448->2288|4466->2297|4499->2309|4540->2322|4569->2323|4607->2334|4636->2335|4691->2362|4720->2363|4758->2373|4823->2410|4852->2411|4891->2422|4949->2452|4978->2453|5016->2464|5045->2465|5080->2473|5108->2474|5144->2483|5173->2484|5204->2488|5232->2489|5262->2492
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|51->20|51->20|51->20|56->25|56->25|56->25|73->42|78->47|79->48|79->48|80->49|81->50|81->50|83->52|83->52|84->53|87->56|87->56|87->56|89->58|89->58|90->59|97->66|97->66|98->67|98->67|98->67|99->68|99->68|99->68|100->69|100->69|100->69|100->69|101->70|101->70|102->71|102->71|102->71|102->71|103->72|103->72|103->72|104->73|105->74|105->74|106->75|106->75|107->76|107->76|108->77|108->77|109->78|109->78|111->80
                  -- GENERATED --
              */
          