
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

object tipoTrabajoModifica extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,tables.TipoTrabajo,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
tipo: tables.TipoTrabajo):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "MODIFICAR TIPO DE TRABAJO", "")),format.raw/*9.64*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<table class="table table-sm table-bordered table-condensed table-fluid">
					<tr>
						<td>
							<div class="row">
								<div class="col-xs-6 col-sm-2 col-md-2 col-lg-2">
									<b><font color="#008000">TIPO DE TRABAJO:</font></b>
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
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/tipoTrabajoMantencion/';">
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
            """),format.raw/*58.13*/("""url: "/modificaTipoPorCampoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*65.36*/("""{"""),format.raw/*65.37*/("""
	     		"""),format.raw/*66.9*/("""if(respuesta=="existe")"""),format.raw/*66.32*/("""{"""),format.raw/*66.33*/("""
	     			"""),format.raw/*67.10*/("""alertify.alert('El nombre de tipo de trabajo ya existe, intente con otro', function () """),format.raw/*67.97*/("""{"""),format.raw/*67.98*/("""
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

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,tipo:tables.TipoTrabajo): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,tipo)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.TipoTrabajo) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,tipo) => apply(mapDiccionario,mapPermiso,userMnu,tipo)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuPlanes/tipoTrabajoModifica.scala.html
                  HASH: d60090706d7531dfc97fc1c50f0eee966a262622
                  MATRIX: 1036->1|1252->124|1285->132|1301->140|1340->142|1368->145|1436->193|1464->195|1540->246|1620->306|1650->309|2149->781|2162->785|2191->793|2336->911|2349->915|2382->927|2865->1380|2897->1385|2987->1447|3016->1448|3048->1453|3141->1519|3169->1520|3236->1559|3265->1560|3295->1563|3455->1696|3468->1700|3497->1708|3580->1763|3609->1764|3650->1777|3915->2014|3944->2015|3980->2024|4031->2047|4060->2048|4098->2058|4213->2145|4242->2146|4281->2157|4327->2176|4340->2180|4373->2192|4414->2205|4443->2206|4481->2217|4510->2218|4565->2245|4594->2246|4632->2256|4697->2293|4726->2294|4765->2305|4823->2335|4852->2336|4890->2347|4919->2348|4954->2356|4982->2357|5018->2366|5047->2367|5078->2371|5106->2372|5136->2375
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|51->20|51->20|51->20|55->24|55->24|55->24|72->41|77->46|78->47|78->47|79->48|80->49|80->49|82->51|82->51|83->52|86->55|86->55|86->55|88->57|88->57|89->58|96->65|96->65|97->66|97->66|97->66|98->67|98->67|98->67|99->68|99->68|99->68|99->68|100->69|100->69|101->70|101->70|101->70|101->70|102->71|102->71|102->71|103->72|104->73|104->73|105->74|105->74|106->75|106->75|107->76|107->76|108->77|108->77|110->79
                  -- GENERATED --
              */
          