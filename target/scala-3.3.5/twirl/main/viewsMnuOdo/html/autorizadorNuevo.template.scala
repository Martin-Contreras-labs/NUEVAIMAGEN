
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

object autorizadorNuevo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Usuario],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listUsuarios: List[tables.Usuario]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "AGREGAR/CREAR NUEVO OPERADOR", "")),format.raw/*9.67*/("""
		"""),format.raw/*10.3*/("""<form action="/operadorGraba/" method="POST">
			<div class="row  justify-content-md-center">
				<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<tr>
							<td style="text-align:left; vertical-align:top">"""),_display_(/*15.57*/mapDiccionario/*15.71*/.get("RUT")),format.raw/*15.82*/(""": </td>
							<td style="text-align:left; vertical-align:top">
								<input type="text" class="form-control left"
									name="rut"
									id="rut"
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
									required
									onkeydown="return sinReservados(window.event)"
									autocomplete="off"
									maxlength="200">
							</td>
						</tr>
						<tr>
							<td style="text-align:left; vertical-align:top">CARGO: </td>
							<td style="text-align:left; vertical-align:top">
								<input type="text" class="form-control left"
									name="cargo"
									value=""
									onkeydown="return sinReservados(window.event)"
									autocomplete="off"
									maxlength="100">
							</td>
						</tr>
						<tr>
							<td style="text-align:left; vertical-align:top">E-MAIL: </td>
							<td style="text-align:left; vertical-align:top">
								<input type="text" class="form-control left"
									name="email"
									value=""
									autocomplete="off"
									maxlength="100">
							</td>
						</tr>
						<tr>
							<td style="text-align:left; vertical-align:top">TELEFONO: </td>
							<td style="text-align:left; vertical-align:top">
								<input type="text" class="form-control left"
									name="fono"
									value=""
									autocomplete="off"
									maxlength="100">
							</td>
						</tr>
						<tr>
							<td style="text-align:left; vertical-align:top">NOTAS: </td>
							<td style="text-align:left; vertical-align:top">
								<textarea class="form-control"  rows="5"
									name="notas" 
									autocomplete="off"></textarea>
							</td>
						</tr>
						<TR>
							<td  style="text-align:left;">USUARIO MADA</td>
							<td style="text-align:left">
								<select class="custom-select"
									name="id_userAdam"
									required>
									<option value="0">N/A</option>
									"""),_display_(/*84.11*/for(lista <- listUsuarios) yield /*84.37*/{_display_(Seq[Any](format.raw/*84.38*/("""
										"""),format.raw/*85.11*/("""<option value=""""),_display_(/*85.27*/lista/*85.32*/.id),format.raw/*85.35*/("""">"""),_display_(/*85.38*/lista/*85.43*/.getUserName()),format.raw/*85.57*/("""</option>
									""")))}),format.raw/*86.11*/("""
								"""),format.raw/*87.9*/("""</select>
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
""")))}),format.raw/*105.2*/("""




"""),format.raw/*110.1*/("""<script type="text/javascript">

	

	$(document).ready(function() """),format.raw/*114.31*/("""{"""),format.raw/*114.32*/("""
		
		
		"""),format.raw/*117.3*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*118.2*/("""}"""),format.raw/*118.3*/(""");
	
	const formateaPrecio = (id_moneda) => """),format.raw/*120.40*/("""{"""),format.raw/*120.41*/("""
		"""),format.raw/*121.3*/("""$('#precio').val(formatStandar($('#precio').val(),mapMonDec[id_moneda]));
	"""),format.raw/*122.2*/("""}"""),format.raw/*122.3*/("""
	
	"""),format.raw/*124.2*/("""const verificaRut = (rut) => """),format.raw/*124.31*/("""{"""),format.raw/*124.32*/("""
		"""),format.raw/*125.3*/("""var formData = new FormData();
	  	formData.append('rut',rut);
        $.ajax("""),format.raw/*127.16*/("""{"""),format.raw/*127.17*/("""
            """),format.raw/*128.13*/("""url: "/verificaRutOperadorAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*135.36*/("""{"""),format.raw/*135.37*/("""
	     		"""),format.raw/*136.9*/("""if(respuesta=="existe")"""),format.raw/*136.32*/("""{"""),format.raw/*136.33*/("""
	     			"""),format.raw/*137.10*/("""alertify.alert("El RUT de operador ya existe, intente con otro", function () """),format.raw/*137.87*/("""{"""),format.raw/*137.88*/("""
	     				"""),format.raw/*138.11*/("""$("#rut").val("");
		     		"""),format.raw/*139.10*/("""}"""),format.raw/*139.11*/(""");
	     		"""),format.raw/*140.9*/("""}"""),format.raw/*140.10*/("""
	     		"""),format.raw/*141.9*/("""if(respuesta=="error")"""),format.raw/*141.31*/("""{"""),format.raw/*141.32*/("""
	     			"""),format.raw/*142.10*/("""alertify.alert(msgError, function () """),format.raw/*142.47*/("""{"""),format.raw/*142.48*/("""
		     			"""),format.raw/*143.11*/("""location.href = "/";
		     		"""),format.raw/*144.10*/("""}"""),format.raw/*144.11*/(""");
	     		"""),format.raw/*145.9*/("""}"""),format.raw/*145.10*/("""
	     	"""),format.raw/*146.8*/("""}"""),format.raw/*146.9*/("""
        """),format.raw/*147.9*/("""}"""),format.raw/*147.10*/(""");
	"""),format.raw/*148.2*/("""}"""),format.raw/*148.3*/("""
	
	

"""),format.raw/*152.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listUsuarios:List[tables.Usuario]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listUsuarios)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Usuario]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listUsuarios) => apply(mapDiccionario,mapPermiso,userMnu,listUsuarios)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuOdo/autorizadorNuevo.scala.html
                  HASH: de46811ad5722bb5781946bc43389bd2253d92f9
                  MATRIX: 1032->1|1258->134|1291->142|1307->150|1346->152|1374->155|1442->203|1470->205|1546->256|1629->319|1659->322|1981->617|2004->631|2036->642|4308->2887|4350->2913|4389->2914|4428->2925|4471->2941|4485->2946|4509->2949|4539->2952|4553->2957|4588->2971|4639->2991|4675->3000|5268->3562|5301->3567|5396->3633|5426->3634|5463->3643|5557->3709|5586->3710|5659->3754|5689->3755|5720->3758|5823->3833|5852->3834|5884->3838|5942->3867|5972->3868|6003->3871|6110->3949|6140->3950|6182->3963|6447->4199|6477->4200|6514->4209|6566->4232|6596->4233|6635->4243|6741->4320|6771->4321|6811->4332|6868->4360|6898->4361|6937->4372|6967->4373|7004->4382|7055->4404|7085->4405|7124->4415|7190->4452|7220->4453|7260->4464|7319->4494|7349->4495|7388->4506|7418->4507|7454->4515|7483->4516|7520->4525|7550->4526|7582->4530|7611->4531|7645->4537
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|46->15|46->15|46->15|115->84|115->84|115->84|116->85|116->85|116->85|116->85|116->85|116->85|116->85|117->86|118->87|136->105|141->110|145->114|145->114|148->117|149->118|149->118|151->120|151->120|152->121|153->122|153->122|155->124|155->124|155->124|156->125|158->127|158->127|159->128|166->135|166->135|167->136|167->136|167->136|168->137|168->137|168->137|169->138|170->139|170->139|171->140|171->140|172->141|172->141|172->141|173->142|173->142|173->142|174->143|175->144|175->144|176->145|176->145|177->146|177->146|178->147|178->147|179->148|179->148|183->152
                  -- GENERATED --
              */
          