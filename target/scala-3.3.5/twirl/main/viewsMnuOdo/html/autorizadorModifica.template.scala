
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

object autorizadorModifica extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Usuario],tables.OperadorServicio,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listUsuarios: List[tables.Usuario], operador: tables.OperadorServicio):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "MODIFICAR OPERADOR", "")),format.raw/*9.57*/("""
		"""),format.raw/*10.3*/("""<form action="/operadorUpdate/" method="POST">
			<div class="row  justify-content-md-center">
				<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<tr>
							<td style="text-align:left; vertical-align:top">"""),_display_(/*15.57*/mapDiccionario/*15.71*/.get("RUT")),format.raw/*15.82*/(""": </td>
							<td style="text-align:left; vertical-align:top">
								<input type="hidden" name="id" value=""""),_display_(/*17.48*/operador/*17.56*/.getId()),format.raw/*17.64*/("""">
								<input type="text" class="form-control left"
									name="rut"
									id="rut"
									value=""""),_display_(/*21.18*/operador/*21.26*/.getRut()),format.raw/*21.35*/(""""
									required
									autocomplete="off"
									onkeydown="return sinReservados(window.event)"
									onchange="value=value.replace(/\s/g,'').toUpperCase(); verificaRut(value)"
									maxlength="25">
							</td>
						</tr>
						<tr>
							<td style="text-align:left; vertical-align:top">NOMBRE: </td>
							<td style="text-align:left; vertical-align:top">
								<input type="text" class="form-control left"
									name="nombre"
									onkeydown="return sinReservados(window.event)"
									value=""""),_display_(/*35.18*/operador/*35.26*/.getNombre()),format.raw/*35.38*/(""""
									required
									autocomplete="off"
									maxlength="200">
							</td>
						</tr>
						<tr>
							<td style="text-align:left; vertical-align:top">CARGO: </td>
							<td style="text-align:left; vertical-align:top">
								<input type="text" class="form-control left"
									name="cargo"
									onkeydown="return sinReservados(window.event)"
									value=""""),_display_(/*47.18*/operador/*47.26*/.getCargo()),format.raw/*47.37*/(""""
									autocomplete="off"
									maxlength="100">
							</td>
						</tr>
						<tr>
							<td style="text-align:left; vertical-align:top">E-MAIL: </td>
							<td style="text-align:left; vertical-align:top">
								<input type="text" class="form-control left"
									name="email"
									value=""""),_display_(/*57.18*/operador/*57.26*/.getEmail()),format.raw/*57.37*/(""""
									autocomplete="off"
									maxlength="100">
							</td>
						</tr>
						<tr>
							<td style="text-align:left; vertical-align:top">TELEFONO: </td>
							<td style="text-align:left; vertical-align:top">
								<input type="text" class="form-control left"
									name="fono"
									value=""""),_display_(/*67.18*/operador/*67.26*/.getFono()),format.raw/*67.36*/(""""
									autocomplete="off"
									maxlength="100">
							</td>
						</tr>
						<tr>
							<td style="text-align:left; vertical-align:top">NOTAS: </td>
							<td style="text-align:left; vertical-align:top">
								<textarea class="form-control"  rows="5"
									name="notas" 
									autocomplete="off">"""),_display_(/*77.30*/operador/*77.38*/.getNotas()),format.raw/*77.49*/("""</textarea>
							</td>
						</tr>
						<TR>
							<td  style="text-align:left;">USUARIO MADA</td>
							<td style="text-align:left">
								<select class="custom-select"
									name="id_userAdam"
									required>
									<option value=""""),_display_(/*86.26*/operador/*86.34*/.getId_userAdam()),format.raw/*86.51*/("""">"""),_display_(/*86.54*/operador/*86.62*/.getUserAdamName()),format.raw/*86.80*/("""</option>
									"""),_display_(/*87.11*/for(lista <- listUsuarios) yield /*87.37*/{_display_(Seq[Any](format.raw/*87.38*/("""
										"""),format.raw/*88.11*/("""<option value=""""),_display_(/*88.27*/lista/*88.32*/.id),format.raw/*88.35*/("""">"""),_display_(/*88.38*/lista/*88.43*/.getUserName()),format.raw/*88.57*/("""</option>
									""")))}),format.raw/*89.11*/("""
									"""),format.raw/*90.10*/("""<option value="0">N/A</option>
								</select>
							</td>
						</TR>
					</table>
				</div>
			</div>
			<div class="noprint">
				<div class="row justify-content-md-center" >
					<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
						<input type="submit"  value="GRABAR" class="btn btn-success btn-sm rounded-pill btn-block">
					</div>
					<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
						<input type="button"  value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/operadorMantencion/';">
					</div>
				</div>
			</div>
		</form>
	</div>
""")))}),format.raw/*109.2*/("""




"""),format.raw/*114.1*/("""<script type="text/javascript">

	

	$(document).ready(function() """),format.raw/*118.31*/("""{"""),format.raw/*118.32*/("""
		
		
		"""),format.raw/*121.3*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*122.2*/("""}"""),format.raw/*122.3*/(""");
	
	const formateaPrecio = (id_moneda) => """),format.raw/*124.40*/("""{"""),format.raw/*124.41*/("""
		"""),format.raw/*125.3*/("""$('#precio').val(formatStandar($('#precio').val(),mapMonDec[id_moneda]));
	"""),format.raw/*126.2*/("""}"""),format.raw/*126.3*/("""
	
	"""),format.raw/*128.2*/("""const verificaRut = (rut) => """),format.raw/*128.31*/("""{"""),format.raw/*128.32*/("""
		"""),format.raw/*129.3*/("""var formData = new FormData();
	  	formData.append('rut',rut);
        $.ajax("""),format.raw/*131.16*/("""{"""),format.raw/*131.17*/("""
            """),format.raw/*132.13*/("""url: "/verificaRutOperadorAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*139.36*/("""{"""),format.raw/*139.37*/("""
	     		"""),format.raw/*140.9*/("""if(respuesta=="existe")"""),format.raw/*140.32*/("""{"""),format.raw/*140.33*/("""
	     			"""),format.raw/*141.10*/("""alertify.alert("El RUT de operador ya existe, intente con otro", function () """),format.raw/*141.87*/("""{"""),format.raw/*141.88*/("""
	     				"""),format.raw/*142.11*/("""$("#rut").val("");
		     		"""),format.raw/*143.10*/("""}"""),format.raw/*143.11*/(""");
	     		"""),format.raw/*144.9*/("""}"""),format.raw/*144.10*/("""
	     		"""),format.raw/*145.9*/("""if(respuesta=="error")"""),format.raw/*145.31*/("""{"""),format.raw/*145.32*/("""
	     			"""),format.raw/*146.10*/("""alertify.alert(msgError, function () """),format.raw/*146.47*/("""{"""),format.raw/*146.48*/("""
		     			"""),format.raw/*147.11*/("""location.href = "/";
		     		"""),format.raw/*148.10*/("""}"""),format.raw/*148.11*/(""");
	     		"""),format.raw/*149.9*/("""}"""),format.raw/*149.10*/("""
	     	"""),format.raw/*150.8*/("""}"""),format.raw/*150.9*/("""
        """),format.raw/*151.9*/("""}"""),format.raw/*151.10*/(""");
	"""),format.raw/*152.2*/("""}"""),format.raw/*152.3*/("""
	
	

"""),format.raw/*156.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listUsuarios:List[tables.Usuario],operador:tables.OperadorServicio): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listUsuarios,operador)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Usuario],tables.OperadorServicio) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listUsuarios,operador) => apply(mapDiccionario,mapPermiso,userMnu,listUsuarios,operador)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuOdo/autorizadorModifica.scala.html
                  HASH: 8ce0b2ee3a0caa06ed34e5aff93be5068a443f94
                  MATRIX: 1059->1|1320->169|1353->177|1369->185|1408->187|1436->190|1504->238|1532->240|1608->291|1681->344|1711->347|2034->643|2057->657|2089->668|2227->779|2244->787|2273->795|2411->906|2428->914|2458->923|3008->1446|3025->1454|3058->1466|3467->1848|3484->1856|3516->1867|3852->2176|3869->2184|3901->2195|4238->2505|4255->2513|4286->2523|4630->2840|4647->2848|4679->2859|4955->3108|4972->3116|5010->3133|5040->3136|5057->3144|5096->3162|5143->3182|5185->3208|5224->3209|5263->3220|5306->3236|5320->3241|5344->3244|5374->3247|5388->3252|5423->3266|5474->3286|5512->3296|6144->3897|6177->3902|6272->3968|6302->3969|6339->3978|6433->4044|6462->4045|6535->4089|6565->4090|6596->4093|6699->4168|6728->4169|6760->4173|6818->4202|6848->4203|6879->4206|6986->4284|7016->4285|7058->4298|7323->4534|7353->4535|7390->4544|7442->4567|7472->4568|7511->4578|7617->4655|7647->4656|7687->4667|7744->4695|7774->4696|7813->4707|7843->4708|7880->4717|7931->4739|7961->4740|8000->4750|8066->4787|8096->4788|8136->4799|8195->4829|8225->4830|8264->4841|8294->4842|8330->4850|8359->4851|8396->4860|8426->4861|8458->4865|8487->4866|8521->4872
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|46->15|46->15|46->15|48->17|48->17|48->17|52->21|52->21|52->21|66->35|66->35|66->35|78->47|78->47|78->47|88->57|88->57|88->57|98->67|98->67|98->67|108->77|108->77|108->77|117->86|117->86|117->86|117->86|117->86|117->86|118->87|118->87|118->87|119->88|119->88|119->88|119->88|119->88|119->88|119->88|120->89|121->90|140->109|145->114|149->118|149->118|152->121|153->122|153->122|155->124|155->124|156->125|157->126|157->126|159->128|159->128|159->128|160->129|162->131|162->131|163->132|170->139|170->139|171->140|171->140|171->140|172->141|172->141|172->141|173->142|174->143|174->143|175->144|175->144|176->145|176->145|176->145|177->146|177->146|177->146|178->147|179->148|179->148|180->149|180->149|181->150|181->150|182->151|182->151|183->152|183->152|187->156
                  -- GENERATED --
              */
          