
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

object otEstadoModifica extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,tables.OtEstado,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
ot: tables.OtEstado):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "MODIFICAR/ELIMINAR ESTADOS "+mapDiccionario.get("Ordenes_de_trabajo").toUpperCase(), "")),format.raw/*9.121*/("""
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
									<input type="hidden" name="id_otEstado" value=""""),_display_(/*20.58*/ot/*20.60*/.getId()),format.raw/*20.68*/("""">
									<input type="text" class="form-control"
										id="estado"
										maxlength="50"
										value=""""),_display_(/*24.19*/ot/*24.21*/.getEstado()),format.raw/*24.33*/(""""
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
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/otEstadoMantencion/';">
				</div>
			</div>
	</div>
	
""")))}),format.raw/*41.2*/("""




"""),format.raw/*46.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*47.31*/("""{"""),format.raw/*47.32*/("""
		  """),format.raw/*48.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*49.2*/("""}"""),format.raw/*49.3*/(""");
	
	const modificarEstado = (campo) => """),format.raw/*51.37*/("""{"""),format.raw/*51.38*/("""
		"""),format.raw/*52.3*/("""let valor = $("#"+campo).val();
		var formData = new FormData();
	  	formData.append('campo',campo);
	  	formData.append('id_otEstado','"""),_display_(/*55.37*/ot/*55.39*/.getId()),format.raw/*55.47*/("""');
	  	formData.append('valor',valor);
        $.ajax("""),format.raw/*57.16*/("""{"""),format.raw/*57.17*/("""
            """),format.raw/*58.13*/("""url: "/modificaOtEstadoPorCampoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*65.36*/("""{"""),format.raw/*65.37*/("""
	     		"""),format.raw/*66.9*/("""if(respuesta=="existe")"""),format.raw/*66.32*/("""{"""),format.raw/*66.33*/("""
	     			"""),format.raw/*67.10*/("""alertify.alert('El nombre de estado ya existe, intente con otro', function () """),format.raw/*67.88*/("""{"""),format.raw/*67.89*/("""
	     				"""),format.raw/*68.11*/("""$("#estado").val(""""),_display_(/*68.30*/ot/*68.32*/.getEstado()),format.raw/*68.44*/("""");
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

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,ot:tables.OtEstado): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,ot)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.OtEstado) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,ot) => apply(mapDiccionario,mapPermiso,userMnu,ot)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizar/otEstadoModifica.scala.html
                  HASH: 92bec7ad74cdb686e3f07c9bd914d54cce8ab793
                  MATRIX: 1031->1|1242->119|1275->127|1291->135|1330->137|1358->140|1426->188|1454->190|1530->241|1668->358|1698->361|2192->828|2203->830|2232->838|2376->955|2387->957|2420->969|2902->1421|2934->1426|3024->1488|3053->1489|3085->1494|3178->1560|3206->1561|3275->1602|3304->1603|3334->1606|3498->1743|3509->1745|3538->1753|3621->1808|3650->1809|3691->1822|3960->2063|3989->2064|4025->2073|4076->2096|4105->2097|4143->2107|4249->2185|4278->2186|4317->2197|4363->2216|4374->2218|4407->2230|4448->2243|4477->2244|4515->2255|4544->2256|4599->2283|4628->2284|4666->2294|4731->2331|4760->2332|4799->2343|4857->2373|4886->2374|4924->2385|4953->2386|4988->2394|5016->2395|5052->2404|5081->2405|5112->2409|5140->2410|5170->2413
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|51->20|51->20|51->20|55->24|55->24|55->24|72->41|77->46|78->47|78->47|79->48|80->49|80->49|82->51|82->51|83->52|86->55|86->55|86->55|88->57|88->57|89->58|96->65|96->65|97->66|97->66|97->66|98->67|98->67|98->67|99->68|99->68|99->68|99->68|100->69|100->69|101->70|101->70|101->70|101->70|102->71|102->71|102->71|103->72|104->73|104->73|105->74|105->74|106->75|106->75|107->76|107->76|108->77|108->77|110->79
                  -- GENERATED --
              */
          