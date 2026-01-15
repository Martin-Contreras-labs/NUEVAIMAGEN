
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

object dibujanteModifica extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,tables.Dibujante,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
dibujante: tables.Dibujante):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "MODIFICAR DIBUJANTE/PROYECTISTA", "")),format.raw/*9.70*/("""
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
								value=""""),_display_(/*20.17*/dibujante/*20.26*/.getNombre()),format.raw/*20.38*/(""""
								maxlength="100"
							    required
								onchange="value = value.trim(); modificarDibujante(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">OBSERVACIONES: </td>
						<td style="text-align:left;">
							<textarea class='form-control form-control-sm' rows='3'
								id="observaciones"
								onchange="value = value.trim(); modificarDibujante(id)"
								autocomplete="off">"""),_display_(/*32.29*/dibujante/*32.38*/.getObservaciones()),format.raw/*32.57*/("""</textarea>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/routes2/dibujanteMantencion/';">
				</div>
			</div>
		</div>
	</div>

""")))}),format.raw/*47.2*/("""



"""),format.raw/*51.1*/("""<script type="text/javascript">
	let id_dibujante = """"),_display_(/*52.23*/dibujante/*52.32*/.getId()),format.raw/*52.40*/("""";

	$(document).ready(function() """),format.raw/*54.31*/("""{"""),format.raw/*54.32*/("""
		 """),format.raw/*55.4*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*56.2*/("""}"""),format.raw/*56.3*/(""");
	
	const modificarDibujante = (campo) => """),format.raw/*58.40*/("""{"""),format.raw/*58.41*/("""
		"""),format.raw/*59.3*/("""let valor = $("#"+campo).val();
		var formData = new FormData();
	  	formData.append('campo',campo);
	  	formData.append('id_dibujante',id_dibujante);
	  	formData.append('valor',valor);
        $.ajax("""),format.raw/*64.16*/("""{"""),format.raw/*64.17*/("""
            """),format.raw/*65.13*/("""url: "/routes2/modificaDibujantePorCampoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*72.36*/("""{"""),format.raw/*72.37*/("""
	     		"""),format.raw/*73.9*/("""if(respuesta=="existe")"""),format.raw/*73.32*/("""{"""),format.raw/*73.33*/("""
	     			"""),format.raw/*74.10*/("""alertify.alert('El dibujante/proyectista ya existe, intente con otro', function () """),format.raw/*74.93*/("""{"""),format.raw/*74.94*/("""
	     				"""),format.raw/*75.11*/("""$("#nombre").val(""""),_display_(/*75.30*/dibujante/*75.39*/.getNombre()),format.raw/*75.51*/("""");
		     		"""),format.raw/*76.10*/("""}"""),format.raw/*76.11*/(""");
	     		"""),format.raw/*77.9*/("""}"""),format.raw/*77.10*/("""else if(respuesta=="error")"""),format.raw/*77.37*/("""{"""),format.raw/*77.38*/("""
	     			"""),format.raw/*78.10*/("""alertify.alert(msgError, function () """),format.raw/*78.47*/("""{"""),format.raw/*78.48*/("""
		     			"""),format.raw/*79.11*/("""location.href = "/";
		     		"""),format.raw/*80.10*/("""}"""),format.raw/*80.11*/(""");
	     		"""),format.raw/*81.9*/("""}"""),format.raw/*81.10*/("""
	     	"""),format.raw/*82.8*/("""}"""),format.raw/*82.9*/("""
        """),format.raw/*83.9*/("""}"""),format.raw/*83.10*/(""");
	"""),format.raw/*84.2*/("""}"""),format.raw/*84.3*/("""



"""),format.raw/*88.1*/("""</script>

		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,dibujante:tables.Dibujante): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,dibujante)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.Dibujante) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,dibujante) => apply(mapDiccionario,mapPermiso,userMnu,dibujante)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuTablas/dibujanteModifica.scala.html
                  HASH: 6ddf67ec469367d57d11a8d9d14fc6fbeb13b8a0
                  MATRIX: 1032->1|1251->127|1284->135|1300->143|1339->145|1367->148|1435->196|1463->198|1539->249|1625->315|1655->318|2135->771|2153->780|2186->792|2631->1210|2649->1219|2689->1238|3105->1624|3136->1628|3217->1682|3235->1691|3264->1699|3326->1733|3355->1734|3386->1738|3479->1804|3507->1805|3579->1849|3608->1850|3638->1853|3868->2055|3897->2056|3938->2069|4216->2319|4245->2320|4281->2329|4332->2352|4361->2353|4399->2363|4510->2446|4539->2447|4578->2458|4624->2477|4642->2486|4675->2498|4716->2511|4745->2512|4783->2523|4812->2524|4867->2551|4896->2552|4934->2562|4999->2599|5028->2600|5067->2611|5125->2641|5154->2642|5192->2653|5221->2654|5256->2662|5284->2663|5320->2672|5349->2673|5380->2677|5408->2678|5439->2682
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|51->20|51->20|51->20|63->32|63->32|63->32|78->47|82->51|83->52|83->52|83->52|85->54|85->54|86->55|87->56|87->56|89->58|89->58|90->59|95->64|95->64|96->65|103->72|103->72|104->73|104->73|104->73|105->74|105->74|105->74|106->75|106->75|106->75|106->75|107->76|107->76|108->77|108->77|108->77|108->77|109->78|109->78|109->78|110->79|111->80|111->80|112->81|112->81|113->82|113->82|114->83|114->83|115->84|115->84|119->88
                  -- GENERATED --
              */
          