
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

object mantTblEstadoOperacionalUpdate extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,tables.MantEstadoOperacional,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
estado: tables.MantEstadoOperacional):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "MODIFICAR/ELIMINAR ESTADO OPERACIONAL", "")),format.raw/*9.76*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<table class="table table-sm table-bordered table-condensed table-fluid">
					<tr>
						<td>
							<div class="row">
								<div class="col-xs-6 col-sm-2 col-md-2 col-lg-2">
									<b><font color="#008000">ESTADO:</font></b>
								</div>
								<div class="col-xs-6 col-sm-10 col-md-10 col-lg-10">
									<input type="hidden" name="id_estado" value=""""),_display_(/*20.56*/estado/*20.62*/.getId()),format.raw/*20.70*/("""">
									<input type="text" class="form-control"
										id="nombre"
										maxlength="50"
										onkeydown="return sinReservados(window.event)"
										value=""""),_display_(/*25.19*/estado/*25.25*/.getNombre()),format.raw/*25.37*/(""""
										onchange="value = value.trim(); modificarEstado(id)">
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
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/routes3/mantTblEstadoOperacionalMant/';">
				</div>
			</div>
	</div>
	
""")))}),format.raw/*42.2*/("""




"""),format.raw/*47.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*48.31*/("""{"""),format.raw/*48.32*/("""
		  """),format.raw/*49.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*50.2*/("""}"""),format.raw/*50.3*/(""");
	
	const modificarEstado = (campo) => """),format.raw/*52.37*/("""{"""),format.raw/*52.38*/("""
		"""),format.raw/*53.3*/("""let valor = $("#"+campo).val();
		var formData = new FormData();
	  	formData.append('campo',campo);
	  	formData.append('id_estado','"""),_display_(/*56.35*/estado/*56.41*/.getId()),format.raw/*56.49*/("""');
	  	formData.append('valor',valor);
        $.ajax("""),format.raw/*58.16*/("""{"""),format.raw/*58.17*/("""
            """),format.raw/*59.13*/("""url: "/routes3/modificaEstadoOperacionalPorCampoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*66.36*/("""{"""),format.raw/*66.37*/("""
	     		"""),format.raw/*67.9*/("""if(respuesta=="existe")"""),format.raw/*67.32*/("""{"""),format.raw/*67.33*/("""
	     			"""),format.raw/*68.10*/("""alertify.alert('El nombre de estado ya existe, intente con otro', function () """),format.raw/*68.88*/("""{"""),format.raw/*68.89*/("""
	     				"""),format.raw/*69.11*/("""$("#nombre").val(""""),_display_(/*69.30*/estado/*69.36*/.getNombre()),format.raw/*69.48*/("""");
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

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,estado:tables.MantEstadoOperacional): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,estado)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.MantEstadoOperacional) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,estado) => apply(mapDiccionario,mapPermiso,userMnu,estado)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuMantencion/mantTblEstadoOperacionalUpdate.scala.html
                  HASH: 0481f73b554ca8198a167add6e46a3545b4f7d0f
                  MATRIX: 1061->1|1289->136|1322->144|1338->152|1377->154|1405->157|1473->205|1501->207|1577->258|1669->330|1699->333|2191->798|2206->804|2235->812|2436->986|2451->992|2484->1004|2984->1474|3016->1479|3106->1541|3135->1542|3167->1547|3260->1613|3288->1614|3357->1655|3386->1656|3416->1659|3578->1794|3593->1800|3622->1808|3705->1863|3734->1864|3775->1877|4061->2135|4090->2136|4126->2145|4177->2168|4206->2169|4244->2179|4350->2257|4379->2258|4418->2269|4464->2288|4479->2294|4512->2306|4553->2319|4582->2320|4620->2331|4649->2332|4704->2359|4733->2360|4771->2370|4836->2407|4865->2408|4904->2419|4962->2449|4991->2450|5029->2461|5058->2462|5093->2470|5121->2471|5157->2480|5186->2481|5217->2485|5245->2486|5275->2489
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|51->20|51->20|51->20|56->25|56->25|56->25|73->42|78->47|79->48|79->48|80->49|81->50|81->50|83->52|83->52|84->53|87->56|87->56|87->56|89->58|89->58|90->59|97->66|97->66|98->67|98->67|98->67|99->68|99->68|99->68|100->69|100->69|100->69|100->69|101->70|101->70|102->71|102->71|102->71|102->71|103->72|103->72|103->72|104->73|105->74|105->74|106->75|106->75|107->76|107->76|108->77|108->77|109->78|109->78|111->80
                  -- GENERATED --
              */
          