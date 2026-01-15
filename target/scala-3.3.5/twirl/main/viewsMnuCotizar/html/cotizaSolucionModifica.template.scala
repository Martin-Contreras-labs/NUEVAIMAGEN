
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

object cotizaSolucionModifica extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,tables.CotizaSolucion,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
coti: tables.CotizaSolucion):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "MODIFICAR/ELIMINAR SOLUCIONES COTIZACION", "")),format.raw/*9.79*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<table class="table table-sm table-bordered table-condensed table-fluid">
					<tr>
						<td>
							<div class="row">
								<div class="col-xs-6 col-sm-2 col-md-2 col-lg-2">
									<b><font color="#008000">SOLUCION:</font></b>
								</div>
								<div class="col-xs-6 col-sm-10 col-md-10 col-lg-10">
									<input type="hidden" name="id_cotizaSolucion" value=""""),_display_(/*20.64*/coti/*20.68*/.getId()),format.raw/*20.76*/("""">
									<input type="text" class="form-control"
										id="solucion"
										maxlength="50"
										value=""""),_display_(/*24.19*/coti/*24.23*/.getSolucion()),format.raw/*24.37*/(""""
										onchange="value = value.trim(); modificarSolucion(id)">
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
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/routes2/cotizaSolucionMantencion/';">
				</div>
			</div>
	</div>
	
""")))}),format.raw/*41.2*/("""




"""),format.raw/*46.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*47.31*/("""{"""),format.raw/*47.32*/("""
		  """),format.raw/*48.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*49.2*/("""}"""),format.raw/*49.3*/(""");
	
	const modificarSolucion = (campo) => """),format.raw/*51.39*/("""{"""),format.raw/*51.40*/("""
		"""),format.raw/*52.3*/("""let valor = $("#"+campo).val();
		var formData = new FormData();
	  	formData.append('campo',campo);
	  	formData.append('id_cotizaSolucion','"""),_display_(/*55.43*/coti/*55.47*/.getId()),format.raw/*55.55*/("""');
	  	formData.append('valor',valor);
        $.ajax("""),format.raw/*57.16*/("""{"""),format.raw/*57.17*/("""
            """),format.raw/*58.13*/("""url: "/routes2/modificaCotizaSolucionPorCampoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*65.36*/("""{"""),format.raw/*65.37*/("""
	     		"""),format.raw/*66.9*/("""if(respuesta=="existe")"""),format.raw/*66.32*/("""{"""),format.raw/*66.33*/("""
	     			"""),format.raw/*67.10*/("""alertify.alert('El nombre de solucion ya existe, intente con otro', function () """),format.raw/*67.90*/("""{"""),format.raw/*67.91*/("""
	     				"""),format.raw/*68.11*/("""$("#solucion").val(""""),_display_(/*68.32*/coti/*68.36*/.getSolucion()),format.raw/*68.50*/("""");
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

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,coti:tables.CotizaSolucion): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,coti)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.CotizaSolucion) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,coti) => apply(mapDiccionario,mapPermiso,userMnu,coti)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizar/cotizaSolucionModifica.scala.html
                  HASH: 662e216ce6e2674cb18d87e25ddc47528624245a
                  MATRIX: 1043->1|1262->127|1295->135|1311->143|1350->145|1378->148|1446->196|1474->198|1550->249|1645->324|1675->327|2177->802|2190->806|2219->814|2365->933|2378->937|2413->951|2911->1419|2943->1424|3033->1486|3062->1487|3094->1492|3187->1558|3215->1559|3286->1602|3315->1603|3345->1606|3515->1749|3528->1753|3557->1761|3640->1816|3669->1817|3710->1830|3993->2085|4022->2086|4058->2095|4109->2118|4138->2119|4176->2129|4284->2209|4313->2210|4352->2221|4400->2242|4413->2246|4448->2260|4489->2273|4518->2274|4556->2285|4585->2286|4640->2313|4669->2314|4707->2324|4772->2361|4801->2362|4840->2373|4898->2403|4927->2404|4965->2415|4994->2416|5029->2424|5057->2425|5093->2434|5122->2435|5153->2439|5181->2440|5211->2443
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|51->20|51->20|51->20|55->24|55->24|55->24|72->41|77->46|78->47|78->47|79->48|80->49|80->49|82->51|82->51|83->52|86->55|86->55|86->55|88->57|88->57|89->58|96->65|96->65|97->66|97->66|97->66|98->67|98->67|98->67|99->68|99->68|99->68|99->68|100->69|100->69|101->70|101->70|101->70|101->70|102->71|102->71|102->71|103->72|104->73|104->73|105->74|105->74|106->75|106->75|107->76|107->76|108->77|108->77|110->79
                  -- GENERATED --
              */
          