
package viewsMnuBodegas.html

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

object rubroModifica extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,tables.Rubro,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
rubro: tables.Rubro):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "MODIFICAR RUBRO", "")),format.raw/*9.54*/("""
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
								value=""""),_display_(/*20.17*/rubro/*20.22*/.getNombre()),format.raw/*20.34*/(""""
								maxlength="100"
							    required
								onchange="value = value.trim(); modificarRubro(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">OBSERVACIONES: </td>
						<td style="text-align:left;">
							<textarea class='form-control form-control-sm' rows='3'
								id="observaciones"
								onchange="value = value.trim(); modificarRubro(id)"
								autocomplete="off">"""),_display_(/*32.29*/rubro/*32.34*/.getObservaciones()),format.raw/*32.53*/("""</textarea>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/routes2/rubroMantencion/';">
				</div>
			</div>
		</div>
	</div>

""")))}),format.raw/*47.2*/("""



"""),format.raw/*51.1*/("""<script type="text/javascript">
	let id_rubro = """"),_display_(/*52.19*/rubro/*52.24*/.getId()),format.raw/*52.32*/("""";

	$(document).ready(function() """),format.raw/*54.31*/("""{"""),format.raw/*54.32*/("""
		 """),format.raw/*55.4*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*56.2*/("""}"""),format.raw/*56.3*/(""");
	
	const modificarRubro = (campo) => """),format.raw/*58.36*/("""{"""),format.raw/*58.37*/("""
		"""),format.raw/*59.3*/("""let valor = $("#"+campo).val();
		var formData = new FormData();
	  	formData.append('campo',campo);
	  	formData.append('id_rubro',id_rubro);
	  	formData.append('valor',valor);
        $.ajax("""),format.raw/*64.16*/("""{"""),format.raw/*64.17*/("""
            """),format.raw/*65.13*/("""url: "/routes2/modificaRubroPorCampoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*72.36*/("""{"""),format.raw/*72.37*/("""
	     		"""),format.raw/*73.9*/("""if(respuesta=="existe")"""),format.raw/*73.32*/("""{"""),format.raw/*73.33*/("""
	     			"""),format.raw/*74.10*/("""alertify.alert('El rubro/proyectista ya existe, intente con otro', function () """),format.raw/*74.89*/("""{"""),format.raw/*74.90*/("""
	     				"""),format.raw/*75.11*/("""$("#nombre").val(""""),_display_(/*75.30*/rubro/*75.35*/.getNombre()),format.raw/*75.47*/("""");
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

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,rubro:tables.Rubro): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,rubro)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.Rubro) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,rubro) => apply(mapDiccionario,mapPermiso,userMnu,rubro)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuBodegas/rubroModifica.scala.html
                  HASH: 9b828a7669e76eee65ba3872f3fbdc5f21cf5b4a
                  MATRIX: 1025->1|1236->119|1269->127|1285->135|1324->137|1352->140|1420->188|1448->190|1524->241|1594->291|1624->294|2104->747|2118->752|2151->764|2588->1174|2602->1179|2642->1198|3054->1580|3085->1584|3162->1634|3176->1639|3205->1647|3267->1681|3296->1682|3327->1686|3420->1752|3448->1753|3516->1793|3545->1794|3575->1797|3797->1991|3826->1992|3867->2005|4141->2251|4170->2252|4206->2261|4257->2284|4286->2285|4324->2295|4431->2374|4460->2375|4499->2386|4545->2405|4559->2410|4592->2422|4633->2435|4662->2436|4700->2447|4729->2448|4784->2475|4813->2476|4851->2486|4916->2523|4945->2524|4984->2535|5042->2565|5071->2566|5109->2577|5138->2578|5173->2586|5201->2587|5237->2596|5266->2597|5297->2601|5325->2602|5356->2606
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|51->20|51->20|51->20|63->32|63->32|63->32|78->47|82->51|83->52|83->52|83->52|85->54|85->54|86->55|87->56|87->56|89->58|89->58|90->59|95->64|95->64|96->65|103->72|103->72|104->73|104->73|104->73|105->74|105->74|105->74|106->75|106->75|106->75|106->75|107->76|107->76|108->77|108->77|108->77|108->77|109->78|109->78|109->78|110->79|111->80|111->80|112->81|112->81|113->82|113->82|114->83|114->83|115->84|115->84|119->88
                  -- GENERATED --
              */
          