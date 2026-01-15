
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

object cotizaEstadoModifica extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,tables.CotizaEstado,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
coti: tables.CotizaEstado):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "MODIFICAR/ELIMINAR ESTADOS COTIZACION", "")),format.raw/*9.76*/("""
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
									<input type="hidden" name="id_cotizaEstado" value=""""),_display_(/*20.62*/coti/*20.66*/.getId()),format.raw/*20.74*/("""">
									<input type="text" class="form-control"
										id="estado"
										maxlength="50"
										onkeydown="return sinReservados(window.event)"
										value=""""),_display_(/*25.19*/coti/*25.23*/.getEstado()),format.raw/*25.35*/(""""
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
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/cotizaEstadoMantencion/';">
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
	  	formData.append('id_cotizaEstado','"""),_display_(/*56.41*/coti/*56.45*/.getId()),format.raw/*56.53*/("""');
	  	formData.append('valor',valor);
        $.ajax("""),format.raw/*58.16*/("""{"""),format.raw/*58.17*/("""
            """),format.raw/*59.13*/("""url: "/modificaCotizaEstadoPorCampoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*66.36*/("""{"""),format.raw/*66.37*/("""
	     		"""),format.raw/*67.9*/("""if(respuesta=="existe")"""),format.raw/*67.32*/("""{"""),format.raw/*67.33*/("""
	     			"""),format.raw/*68.10*/("""alertify.alert('El nombre de estado ya existe, intente con otro', function () """),format.raw/*68.88*/("""{"""),format.raw/*68.89*/("""
	     				"""),format.raw/*69.11*/("""$("#estado").val(""""),_display_(/*69.30*/coti/*69.34*/.getEstado()),format.raw/*69.46*/("""");
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

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,coti:tables.CotizaEstado): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,coti)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.CotizaEstado) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,coti) => apply(mapDiccionario,mapPermiso,userMnu,coti)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizar/cotizaEstadoModifica.scala.html
                  HASH: 9b8d5c152634480e90b312ba5fb24303fe1f72ed
                  MATRIX: 1039->1|1256->125|1289->133|1305->141|1344->143|1372->146|1440->194|1468->196|1544->247|1636->319|1666->322|2164->793|2177->797|2206->805|2407->979|2420->983|2453->995|2939->1451|2971->1456|3061->1518|3090->1519|3122->1524|3215->1590|3243->1591|3312->1632|3341->1633|3371->1636|3539->1777|3552->1781|3581->1789|3664->1844|3693->1845|3734->1858|4007->2103|4036->2104|4072->2113|4123->2136|4152->2137|4190->2147|4296->2225|4325->2226|4364->2237|4410->2256|4423->2260|4456->2272|4497->2285|4526->2286|4564->2297|4593->2298|4648->2325|4677->2326|4715->2336|4780->2373|4809->2374|4848->2385|4906->2415|4935->2416|4973->2427|5002->2428|5037->2436|5065->2437|5101->2446|5130->2447|5161->2451|5189->2452|5219->2455
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|51->20|51->20|51->20|56->25|56->25|56->25|73->42|78->47|79->48|79->48|80->49|81->50|81->50|83->52|83->52|84->53|87->56|87->56|87->56|89->58|89->58|90->59|97->66|97->66|98->67|98->67|98->67|99->68|99->68|99->68|100->69|100->69|100->69|100->69|101->70|101->70|102->71|102->71|102->71|102->71|103->72|103->72|103->72|104->73|105->74|105->74|106->75|106->75|107->76|107->76|108->77|108->77|109->78|109->78|111->80
                  -- GENERATED --
              */
          