
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

object mantTblItemUpdate extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,tables.MantItemIntervenido,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
item: tables.MantItemIntervenido):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "MODIFICAR/ELIMINAR ITEM", "")),format.raw/*9.62*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<table class="table table-sm table-bordered table-condensed table-fluid">
					<tr>
						<td>
							<div class="row">
								<div class="col-xs-6 col-sm-2 col-md-2 col-lg-2">
									<b><font color="#008000">ITEM:</font></b>
								</div>
								<div class="col-xs-6 col-sm-10 col-md-10 col-lg-10">
									<input type="hidden" name="id_item" value=""""),_display_(/*20.54*/item/*20.58*/.getId()),format.raw/*20.66*/("""">
									<input type="text" class="form-control"
										id="nombre"
										maxlength="50"
										onkeydown="return sinReservados(window.event)"
										value=""""),_display_(/*25.19*/item/*25.23*/.getNombre()),format.raw/*25.35*/(""""
										onchange="value = value.trim(); modificarItem(id)">
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
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/routes3/mantTblItemMant/';">
				</div>
			</div>
	</div>
	
""")))}),format.raw/*42.2*/("""




"""),format.raw/*47.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*48.31*/("""{"""),format.raw/*48.32*/("""
		  """),format.raw/*49.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*50.2*/("""}"""),format.raw/*50.3*/(""");
	
	const modificarItem = (campo) => """),format.raw/*52.35*/("""{"""),format.raw/*52.36*/("""
		"""),format.raw/*53.3*/("""let valor = $("#"+campo).val();
		var formData = new FormData();
	  	formData.append('campo',campo);
	  	formData.append('id_item','"""),_display_(/*56.33*/item/*56.37*/.getId()),format.raw/*56.45*/("""');
	  	formData.append('valor',valor);
        $.ajax("""),format.raw/*58.16*/("""{"""),format.raw/*58.17*/("""
            """),format.raw/*59.13*/("""url: "/routes3/modificaItemPorCampoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*66.36*/("""{"""),format.raw/*66.37*/("""
	     		"""),format.raw/*67.9*/("""if(respuesta=="existe")"""),format.raw/*67.32*/("""{"""),format.raw/*67.33*/("""
	     			"""),format.raw/*68.10*/("""alertify.alert('El nombre de item ya existe, intente con otro', function () """),format.raw/*68.86*/("""{"""),format.raw/*68.87*/("""
	     				"""),format.raw/*69.11*/("""$("#nombre").val(""""),_display_(/*69.30*/item/*69.34*/.getNombre()),format.raw/*69.46*/("""");
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

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,item:tables.MantItemIntervenido): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,item)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.MantItemIntervenido) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,item) => apply(mapDiccionario,mapPermiso,userMnu,item)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuMantencion/mantTblItemUpdate.scala.html
                  HASH: 31112b95abb9d8e50f3c11d7676e952458bdc6a8
                  MATRIX: 1046->1|1270->132|1303->140|1319->148|1358->150|1386->153|1454->201|1482->203|1558->254|1636->312|1666->315|2154->776|2167->780|2196->788|2397->962|2410->966|2443->978|2928->1433|2960->1438|3050->1500|3079->1501|3111->1506|3204->1572|3232->1573|3299->1612|3328->1613|3358->1616|3518->1749|3531->1753|3560->1761|3643->1816|3672->1817|3713->1830|3986->2075|4015->2076|4051->2085|4102->2108|4131->2109|4169->2119|4273->2195|4302->2196|4341->2207|4387->2226|4400->2230|4433->2242|4474->2255|4503->2256|4541->2267|4570->2268|4625->2295|4654->2296|4692->2306|4757->2343|4786->2344|4825->2355|4883->2385|4912->2386|4950->2397|4979->2398|5014->2406|5042->2407|5078->2416|5107->2417|5138->2421|5166->2422|5196->2425
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|51->20|51->20|51->20|56->25|56->25|56->25|73->42|78->47|79->48|79->48|80->49|81->50|81->50|83->52|83->52|84->53|87->56|87->56|87->56|89->58|89->58|90->59|97->66|97->66|98->67|98->67|98->67|99->68|99->68|99->68|100->69|100->69|100->69|100->69|101->70|101->70|102->71|102->71|102->71|102->71|103->72|103->72|103->72|104->73|105->74|105->74|106->75|106->75|107->76|107->76|108->77|108->77|109->78|109->78|111->80
                  -- GENERATED --
              */
          