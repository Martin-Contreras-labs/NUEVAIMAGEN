
package viewsMnuPlanes.html

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

object tipoPlanModifica extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,tables.TipoPlan,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
tipo: tables.TipoPlan):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "MODIFICAR TIPO PLAN DE MANTENCION", "")),format.raw/*9.72*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<table class="table table-sm table-bordered table-condensed table-fluid">
					<tr>
						<td>
							<div class="row">
								<div class="col-xs-6 col-sm-2 col-md-2 col-lg-2">
									<b><font color="#008000">TIPO PLAN DE MANTENCION:</font></b>
								</div>
								<div class="col-xs-6 col-sm-10 col-md-10 col-lg-10">
									<input type="hidden" name="id_tipo" value=""""),_display_(/*20.54*/tipo/*20.58*/.getId()),format.raw/*20.66*/("""">
									<input type="text" class="form-control"
										id="nombre"
										maxlength="100"
										value=""""),_display_(/*24.19*/tipo/*24.23*/.getNombre()),format.raw/*24.35*/(""""
										onchange="value = value.trim(); modificarTipo(id)">
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
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/tipoPlanMantencion/';">
				</div>
			</div>
	</div>
	
""")))}),format.raw/*41.2*/("""




"""),format.raw/*46.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*47.31*/("""{"""),format.raw/*47.32*/("""
		  """),format.raw/*48.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*49.2*/("""}"""),format.raw/*49.3*/(""");
	
	const modificarTipo = (campo) => """),format.raw/*51.35*/("""{"""),format.raw/*51.36*/("""
		"""),format.raw/*52.3*/("""let valor = $("#"+campo).val();
		var formData = new FormData();
	  	formData.append('campo',campo);
	  	formData.append('id_tipo','"""),_display_(/*55.33*/tipo/*55.37*/.getId()),format.raw/*55.45*/("""');
	  	formData.append('valor',valor);
        $.ajax("""),format.raw/*57.16*/("""{"""),format.raw/*57.17*/("""
            """),format.raw/*58.13*/("""url: "/modificaPlanPorCampoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*65.36*/("""{"""),format.raw/*65.37*/("""
	     		"""),format.raw/*66.9*/("""if(respuesta=="existe")"""),format.raw/*66.32*/("""{"""),format.raw/*66.33*/("""
	     			"""),format.raw/*67.10*/("""alertify.alert('El nombre de tipo plan de mantención ya existe, intente con otro', function () """),format.raw/*67.105*/("""{"""),format.raw/*67.106*/("""
	     				"""),format.raw/*68.11*/("""$("#nombre").val(""""),_display_(/*68.30*/tipo/*68.34*/.getNombre()),format.raw/*68.46*/("""");
		     		"""),format.raw/*69.10*/("""}"""),format.raw/*69.11*/(""");
	     		"""),format.raw/*70.9*/("""}"""),format.raw/*70.10*/("""else if(respuesta=="error")"""),format.raw/*70.37*/("""{"""),format.raw/*70.38*/("""
	     			"""),format.raw/*71.10*/("""alertify.alert(msgError, function () """),format.raw/*71.47*/("""{"""),format.raw/*71.48*/("""
		     			"""),format.raw/*72.11*/("""location.href = "/";
		     		"""),format.raw/*73.10*/("""}"""),format.raw/*73.11*/(""");
	     		"""),format.raw/*74.9*/("""}"""),format.raw/*74.10*/("""
	     	"""),format.raw/*75.8*/("""}"""),format.raw/*75.9*/("""
        """),format.raw/*76.9*/("""}"""),format.raw/*76.10*/(""");
	"""),format.raw/*77.2*/("""}"""),format.raw/*77.3*/("""
	
"""),format.raw/*79.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,tipo:tables.TipoPlan): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,tipo)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.TipoPlan) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,tipo) => apply(mapDiccionario,mapPermiso,userMnu,tipo)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuPlanes/tipoPlanModifica.scala.html
                  HASH: 9c2385dc969c8f4cdcf46a202a5c9b94906922c1
                  MATRIX: 1030->1|1243->121|1276->129|1292->137|1331->139|1359->142|1427->190|1455->192|1531->243|1619->311|1649->314|2156->794|2169->798|2198->806|2343->924|2356->928|2389->940|2869->1390|2901->1395|2991->1457|3020->1458|3052->1463|3145->1529|3173->1530|3240->1569|3269->1570|3299->1573|3459->1706|3472->1710|3501->1718|3584->1773|3613->1774|3654->1787|3919->2024|3948->2025|3984->2034|4035->2057|4064->2058|4102->2068|4226->2163|4256->2164|4295->2175|4341->2194|4354->2198|4387->2210|4428->2223|4457->2224|4495->2235|4524->2236|4579->2263|4608->2264|4646->2274|4711->2311|4740->2312|4779->2323|4837->2353|4866->2354|4904->2365|4933->2366|4968->2374|4996->2375|5032->2384|5061->2385|5092->2389|5120->2390|5150->2393
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|51->20|51->20|51->20|55->24|55->24|55->24|72->41|77->46|78->47|78->47|79->48|80->49|80->49|82->51|82->51|83->52|86->55|86->55|86->55|88->57|88->57|89->58|96->65|96->65|97->66|97->66|97->66|98->67|98->67|98->67|99->68|99->68|99->68|99->68|100->69|100->69|101->70|101->70|101->70|101->70|102->71|102->71|102->71|103->72|104->73|104->73|105->74|105->74|106->75|106->75|107->76|107->76|108->77|108->77|110->79
                  -- GENERATED --
              */
          