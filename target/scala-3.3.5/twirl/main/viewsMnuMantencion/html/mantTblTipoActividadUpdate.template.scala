
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

object mantTblTipoActividadUpdate extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,tables.MantTipoActividad,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
tipoActividad: tables.MantTipoActividad):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "MODIFICAR/ELIMINAR TIPO DE ACTIVIDAD", "")),format.raw/*9.75*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<table class="table table-sm table-bordered table-condensed table-fluid">
					<tr>
						<td>
							<div class="row">
								<div class="col-xs-6 col-sm-2 col-md-2 col-lg-2">
									<b><font color="#008000">TIPO DE ACTIVIDAD:</font></b>
								</div>
								<div class="col-xs-6 col-sm-10 col-md-10 col-lg-10">
									<input type="hidden" name="id_tipoActividad" value=""""),_display_(/*20.63*/tipoActividad/*20.76*/.getId()),format.raw/*20.84*/("""">
									<input type="text" class="form-control"
										id="nombre"
										maxlength="50"
										onkeydown="return sinReservados(window.event)"
										value=""""),_display_(/*25.19*/tipoActividad/*25.32*/.getNombre()),format.raw/*25.44*/(""""
										onchange="value = value.trim(); modificarTipoActividad(id)">
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
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/routes3/mantTblTipoActividadMant/';">
				</div>
			</div>
	</div>
	
""")))}),format.raw/*42.2*/("""




"""),format.raw/*47.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*48.31*/("""{"""),format.raw/*48.32*/("""
		  """),format.raw/*49.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*50.2*/("""}"""),format.raw/*50.3*/(""");
	
	const modificarTipoActividad = (campo) => """),format.raw/*52.44*/("""{"""),format.raw/*52.45*/("""
		"""),format.raw/*53.3*/("""let valor = $("#"+campo).val();
		var formData = new FormData();
	  	formData.append('campo',campo);
	  	formData.append('id_tipoActividad','"""),_display_(/*56.42*/tipoActividad/*56.55*/.getId()),format.raw/*56.63*/("""');
	  	formData.append('valor',valor);
        $.ajax("""),format.raw/*58.16*/("""{"""),format.raw/*58.17*/("""
            """),format.raw/*59.13*/("""url: "/routes3/modificaTipoActividadPorCampoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*66.36*/("""{"""),format.raw/*66.37*/("""
	     		"""),format.raw/*67.9*/("""if(respuesta=="existe")"""),format.raw/*67.32*/("""{"""),format.raw/*67.33*/("""
	     			"""),format.raw/*68.10*/("""alertify.alert('El nombre de tipo de actividad ya existe, intente con otro', function () """),format.raw/*68.99*/("""{"""),format.raw/*68.100*/("""
	     				"""),format.raw/*69.11*/("""$("#nombre").val(""""),_display_(/*69.30*/tipoActividad/*69.43*/.getNombre()),format.raw/*69.55*/("""");
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

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,tipoActividad:tables.MantTipoActividad): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,tipoActividad)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.MantTipoActividad) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,tipoActividad) => apply(mapDiccionario,mapPermiso,userMnu,tipoActividad)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuMantencion/mantTblTipoActividadUpdate.scala.html
                  HASH: f7faf1305c0e96826d1add692a2cccfb0fb4183f
                  MATRIX: 1053->1|1284->139|1317->147|1333->155|1372->157|1400->160|1468->208|1496->210|1572->261|1663->332|1693->335|2203->818|2225->831|2254->839|2455->1013|2477->1026|2510->1038|3013->1511|3045->1516|3135->1578|3164->1579|3196->1584|3289->1650|3317->1651|3393->1699|3422->1700|3452->1703|3621->1845|3643->1858|3672->1866|3755->1921|3784->1922|3825->1935|4107->2189|4136->2190|4172->2199|4223->2222|4252->2223|4290->2233|4407->2322|4437->2323|4476->2334|4522->2353|4544->2366|4577->2378|4618->2391|4647->2392|4685->2403|4714->2404|4769->2431|4798->2432|4836->2442|4901->2479|4930->2480|4969->2491|5027->2521|5056->2522|5094->2533|5123->2534|5158->2542|5186->2543|5222->2552|5251->2553|5282->2557|5310->2558|5340->2561
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|51->20|51->20|51->20|56->25|56->25|56->25|73->42|78->47|79->48|79->48|80->49|81->50|81->50|83->52|83->52|84->53|87->56|87->56|87->56|89->58|89->58|90->59|97->66|97->66|98->67|98->67|98->67|99->68|99->68|99->68|100->69|100->69|100->69|100->69|101->70|101->70|102->71|102->71|102->71|102->71|103->72|103->72|103->72|104->73|105->74|105->74|106->75|106->75|107->76|107->76|108->77|108->77|109->78|109->78|111->80
                  -- GENERATED --
              */
          