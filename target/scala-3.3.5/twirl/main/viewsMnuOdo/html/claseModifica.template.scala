
package viewsMnuOdo.html

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

object claseModifica extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,tables.ClaseServicio,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
clase: tables.ClaseServicio):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "MODIFICAR FAMILIA DE SERVICIO", "")),format.raw/*9.68*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<table class="table table-sm table-bordered table-condensed table-fluid">
					<tr>
						<td>
							<div class="row">
								<div class="col-xs-6 col-sm-2 col-md-2 col-lg-2">
									<b><font color="#008000">FAMILIA DE SERVICIO:</font></b>
								</div>
								<div class="col-xs-6 col-sm-10 col-md-10 col-lg-10">
									<input type="hidden" name="id_clase" value=""""),_display_(/*20.55*/clase/*20.60*/.getId()),format.raw/*20.68*/("""">
									<input type="text" class="form-control"
										id="nombre"
										maxlength="50"
										value=""""),_display_(/*24.19*/clase/*24.24*/.getNombre()),format.raw/*24.36*/(""""
										onchange="value = value.trim(); modificarClase(id)">
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
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/claseMantencion/';">
				</div>
			</div>
	</div>
	
""")))}),format.raw/*41.2*/("""




"""),format.raw/*46.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*47.31*/("""{"""),format.raw/*47.32*/("""
		  """),format.raw/*48.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*49.2*/("""}"""),format.raw/*49.3*/(""");
	
	const modificarClase = (campo) => """),format.raw/*51.36*/("""{"""),format.raw/*51.37*/("""
		"""),format.raw/*52.3*/("""let valor = $("#"+campo).val();
		var formData = new FormData();
	  	formData.append('campo',campo);
	  	formData.append('id_clase','"""),_display_(/*55.34*/clase/*55.39*/.getId()),format.raw/*55.47*/("""');
	  	formData.append('valor',valor);
        $.ajax("""),format.raw/*57.16*/("""{"""),format.raw/*57.17*/("""
            """),format.raw/*58.13*/("""url: "/modificaClasePorCampoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*65.36*/("""{"""),format.raw/*65.37*/("""
	     		"""),format.raw/*66.9*/("""if(respuesta=="existe")"""),format.raw/*66.32*/("""{"""),format.raw/*66.33*/("""
	     			"""),format.raw/*67.10*/("""alertify.alert('El nombre de la familia ya existe, intente con otro', function () """),format.raw/*67.92*/("""{"""),format.raw/*67.93*/("""
	     				"""),format.raw/*68.11*/("""$("#nombre").val(""""),_display_(/*68.30*/clase/*68.35*/.getNombre()),format.raw/*68.47*/("""");
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

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,clase:tables.ClaseServicio): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,clase)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.ClaseServicio) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,clase) => apply(mapDiccionario,mapPermiso,userMnu,clase)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuOdo/claseModifica.scala.html
                  HASH: cffd28a235bb19763ae48c283fc3b6bc109c2623
                  MATRIX: 1029->1|1248->127|1281->135|1297->143|1336->145|1364->148|1432->196|1460->198|1536->249|1620->313|1650->316|2154->793|2168->798|2197->806|2341->923|2355->928|2388->940|2866->1388|2898->1393|2988->1455|3017->1456|3049->1461|3142->1527|3170->1528|3238->1568|3267->1569|3297->1572|3458->1706|3472->1711|3501->1719|3584->1774|3613->1775|3654->1788|3920->2026|3949->2027|3985->2036|4036->2059|4065->2060|4103->2070|4213->2152|4242->2153|4281->2164|4327->2183|4341->2188|4374->2200|4415->2213|4444->2214|4482->2225|4511->2226|4566->2253|4595->2254|4633->2264|4698->2301|4727->2302|4766->2313|4824->2343|4853->2344|4891->2355|4920->2356|4955->2364|4983->2365|5019->2374|5048->2375|5079->2379|5107->2380|5137->2383
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|51->20|51->20|51->20|55->24|55->24|55->24|72->41|77->46|78->47|78->47|79->48|80->49|80->49|82->51|82->51|83->52|86->55|86->55|86->55|88->57|88->57|89->58|96->65|96->65|97->66|97->66|97->66|98->67|98->67|98->67|99->68|99->68|99->68|99->68|100->69|100->69|101->70|101->70|101->70|101->70|102->71|102->71|102->71|103->72|104->73|104->73|105->74|105->74|106->75|106->75|107->76|107->76|108->77|108->77|110->79
                  -- GENERATED --
              */
          