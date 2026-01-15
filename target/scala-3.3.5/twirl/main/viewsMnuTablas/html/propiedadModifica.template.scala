
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

object propiedadModifica extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,tables.Propiedad,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
propiedad: tables.Propiedad):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "MODIFICAR PROPIEDAD EQUIPO", "")),format.raw/*9.65*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<table class="table table-sm table-bordered table-condensed table-fluid">
					<tr>
						<td style="text-align:left;">NOMBRE (fullName): </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="nombre"
								autocomplete="off"
								onkeydown="return sinReservados(window.event)"
								value=""""),_display_(/*20.17*/propiedad/*20.26*/.getNombre()),format.raw/*20.38*/(""""
								maxlength="100"
							    required
								onchange="value = value.trim(); modificarPropiedad(id)">
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/routes2/propiedadMantencion/';">
				</div>
			</div>
		</div>
	</div>

""")))}),format.raw/*38.2*/("""



"""),format.raw/*42.1*/("""<script type="text/javascript">
	let id_propiedad = """"),_display_(/*43.23*/propiedad/*43.32*/.getId()),format.raw/*43.40*/("""";

	$(document).ready(function() """),format.raw/*45.31*/("""{"""),format.raw/*45.32*/("""
		 """),format.raw/*46.4*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*47.2*/("""}"""),format.raw/*47.3*/(""");
	
	const modificarPropiedad = (campo) => """),format.raw/*49.40*/("""{"""),format.raw/*49.41*/("""
		"""),format.raw/*50.3*/("""let valor = $("#"+campo).val();
		var formData = new FormData();
	  	formData.append('campo',campo);
	  	formData.append('id_propiedad',id_propiedad);
	  	formData.append('valor',valor);
        $.ajax("""),format.raw/*55.16*/("""{"""),format.raw/*55.17*/("""
            """),format.raw/*56.13*/("""url: "/routes2/modificaPropiedadPorCampoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*63.36*/("""{"""),format.raw/*63.37*/("""
	     		"""),format.raw/*64.9*/("""if(respuesta=="existe")"""),format.raw/*64.32*/("""{"""),format.raw/*64.33*/("""
	     			"""),format.raw/*65.10*/("""alertify.alert('La propiedad ya existe, intente con otra', function () """),format.raw/*65.81*/("""{"""),format.raw/*65.82*/("""
	     				"""),format.raw/*66.11*/("""$("#nombre").val(""""),_display_(/*66.30*/propiedad/*66.39*/.getNombre()),format.raw/*66.51*/("""");
		     		"""),format.raw/*67.10*/("""}"""),format.raw/*67.11*/(""");
	     		"""),format.raw/*68.9*/("""}"""),format.raw/*68.10*/("""else if(respuesta=="error")"""),format.raw/*68.37*/("""{"""),format.raw/*68.38*/("""
	     			"""),format.raw/*69.10*/("""alertify.alert(msgError, function () """),format.raw/*69.47*/("""{"""),format.raw/*69.48*/("""
		     			"""),format.raw/*70.11*/("""location.href = "/";
		     		"""),format.raw/*71.10*/("""}"""),format.raw/*71.11*/(""");
	     		"""),format.raw/*72.9*/("""}"""),format.raw/*72.10*/("""
	     	"""),format.raw/*73.8*/("""}"""),format.raw/*73.9*/("""
        """),format.raw/*74.9*/("""}"""),format.raw/*74.10*/(""");
	"""),format.raw/*75.2*/("""}"""),format.raw/*75.3*/("""



"""),format.raw/*79.1*/("""</script>

		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,propiedad:tables.Propiedad): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,propiedad)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.Propiedad) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,propiedad) => apply(mapDiccionario,mapPermiso,userMnu,propiedad)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuTablas/propiedadModifica.scala.html
                  HASH: 2fb41cee563ded01b36ee41087e3dd410476d602
                  MATRIX: 1032->1|1251->127|1284->135|1300->143|1339->145|1367->148|1435->196|1463->198|1539->249|1620->310|1650->313|2130->766|2148->775|2181->787|2696->1272|2727->1276|2808->1330|2826->1339|2855->1347|2917->1381|2946->1382|2977->1386|3070->1452|3098->1453|3170->1497|3199->1498|3229->1501|3459->1703|3488->1704|3529->1717|3807->1967|3836->1968|3872->1977|3923->2000|3952->2001|3990->2011|4089->2082|4118->2083|4157->2094|4203->2113|4221->2122|4254->2134|4295->2147|4324->2148|4362->2159|4391->2160|4446->2187|4475->2188|4513->2198|4578->2235|4607->2236|4646->2247|4704->2277|4733->2278|4771->2289|4800->2290|4835->2298|4863->2299|4899->2308|4928->2309|4959->2313|4987->2314|5018->2318
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|51->20|51->20|51->20|69->38|73->42|74->43|74->43|74->43|76->45|76->45|77->46|78->47|78->47|80->49|80->49|81->50|86->55|86->55|87->56|94->63|94->63|95->64|95->64|95->64|96->65|96->65|96->65|97->66|97->66|97->66|97->66|98->67|98->67|99->68|99->68|99->68|99->68|100->69|100->69|100->69|101->70|102->71|102->71|103->72|103->72|104->73|104->73|105->74|105->74|106->75|106->75|110->79
                  -- GENERATED --
              */
          