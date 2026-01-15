
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

object proyectoAgrega extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Regiones],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listRegiones: List[tables.Regiones]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "AGREGAR NUEVO PROYECTO", "")),format.raw/*9.61*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<form id="form" method="post" action="/proyectoGraba/">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<tr>
							<td style="text-align:left;">NOMBRE LARGO: </td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="nombre"
									onkeydown="return sinReservados(window.event)"
									autocomplete="off"
									maxlength="100"
									onchange="value = value.trim();">
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">NOMBRE CORTO (*): </td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									id="nickName"
									name="nickName"
									onkeydown="return sinReservados(window.event)"
									autocomplete="off"
									maxlength="50"
									required
									onchange="value = value.trim();verificaNick(value);">
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">DIRECCION: </td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="direccion"
									autocomplete="off"
									maxlength="100"
									onchange="value = value.trim();">
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">"""),_display_(/*49.38*/mapDiccionario/*49.52*/.get("Region")),format.raw/*49.66*/(""": </td>
							<td style="text-align:left;">
								<select class="custom-select" 
									name="cod_region" 
									onchange="actualizaComunas(value);">
									"""),_display_(/*54.11*/for(lista <- listRegiones) yield /*54.37*/{_display_(Seq[Any](format.raw/*54.38*/("""
										"""),format.raw/*55.11*/("""<option value=""""),_display_(/*55.27*/lista/*55.32*/.codigo),format.raw/*55.39*/("""">"""),_display_(/*55.42*/lista/*55.47*/.nombre),format.raw/*55.54*/("""</option>
									""")))}),format.raw/*56.11*/("""
								"""),format.raw/*57.9*/("""</select>
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">"""),_display_(/*61.38*/mapDiccionario/*61.52*/.get("Comuna")),format.raw/*61.66*/(""": </td>
							<td style="text-align:left;">
								<div id="selectComuna">
									<select class="custom-select" 
										name="cod_comuna" >
										<option value="0"> </option>
									</select>
								</div>
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">Ciudad: </td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="ciudad"
									autocomplete="off"
									maxlength="30"
									onchange="value = value.trim();">
							</td>
						</tr>
					</table>
					<div class="noprint">
						<div class="row justify-content-md-center" >
							<div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
								<input type="submit"  value="GRABAR PROYECTO" class="btn btn-success btn-sm rounded-pill btn-block">
							</div>
							<div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
								<input type="button" value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/proyectoMantencion/';">
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

""")))}),format.raw/*97.2*/("""



"""),format.raw/*101.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*103.31*/("""{"""),format.raw/*103.32*/("""
		  """),format.raw/*104.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*105.2*/("""}"""),format.raw/*105.3*/(""");
	
	const actualizaComunas = (cod_region) => """),format.raw/*107.43*/("""{"""),format.raw/*107.44*/("""
		"""),format.raw/*108.3*/("""var formData = new FormData();
		formData.append('cod_region', cod_region);
	  	$.ajax("""),format.raw/*110.12*/("""{"""),format.raw/*110.13*/("""
            """),format.raw/*111.13*/("""url: "/selComuna1Ajax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(data)"""),format.raw/*118.31*/("""{"""),format.raw/*118.32*/("""
	     		"""),format.raw/*119.9*/("""if(data=="error")"""),format.raw/*119.26*/("""{"""),format.raw/*119.27*/("""
	     			"""),format.raw/*120.10*/("""alertify.alert(msgError, function () """),format.raw/*120.47*/("""{"""),format.raw/*120.48*/("""
		     			"""),format.raw/*121.11*/("""location.href = "/";
		     		"""),format.raw/*122.10*/("""}"""),format.raw/*122.11*/(""");
	     		"""),format.raw/*123.9*/("""}"""),format.raw/*123.10*/("""else"""),format.raw/*123.14*/("""{"""),format.raw/*123.15*/("""
	     			"""),format.raw/*124.10*/("""data = data.replace(/&lt;/g,"<");
					data = data.replace(/&gt;/g,">");
					data = data.replace(/&#x27;/g,"\"");
	     			document.getElementById('selectComuna').innerHTML = data;
	     		"""),format.raw/*128.9*/("""}"""),format.raw/*128.10*/("""
	     	"""),format.raw/*129.8*/("""}"""),format.raw/*129.9*/("""
        """),format.raw/*130.9*/("""}"""),format.raw/*130.10*/(""");
	"""),format.raw/*131.2*/("""}"""),format.raw/*131.3*/("""
	
	"""),format.raw/*133.2*/("""const verificaNick = (nickName) => """),format.raw/*133.37*/("""{"""),format.raw/*133.38*/("""
		"""),format.raw/*134.3*/("""var formData = new FormData();
	  	formData.append('nickName',nickName);
        $.ajax("""),format.raw/*136.16*/("""{"""),format.raw/*136.17*/("""
            """),format.raw/*137.13*/("""url: "/verificaNickProyectoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*144.36*/("""{"""),format.raw/*144.37*/("""
	     		"""),format.raw/*145.9*/("""if(respuesta=="existe")"""),format.raw/*145.32*/("""{"""),format.raw/*145.33*/("""
	     			"""),format.raw/*146.10*/("""alertify.alert('El nombre corto (nickName) del proyecto ya existe, intente con otro', function () """),format.raw/*146.108*/("""{"""),format.raw/*146.109*/("""
	     				"""),format.raw/*147.11*/("""$("#nickName").val("");
		     		"""),format.raw/*148.10*/("""}"""),format.raw/*148.11*/(""");
	     		"""),format.raw/*149.9*/("""}"""),format.raw/*149.10*/("""
	     		"""),format.raw/*150.9*/("""if(respuesta=="error")"""),format.raw/*150.31*/("""{"""),format.raw/*150.32*/("""
	     			"""),format.raw/*151.10*/("""alertify.alert(msgError, function () """),format.raw/*151.47*/("""{"""),format.raw/*151.48*/("""
		     			"""),format.raw/*152.11*/("""location.href = "/";
		     		"""),format.raw/*153.10*/("""}"""),format.raw/*153.11*/(""");
	     		"""),format.raw/*154.9*/("""}"""),format.raw/*154.10*/("""
	     	"""),format.raw/*155.8*/("""}"""),format.raw/*155.9*/("""
        """),format.raw/*156.9*/("""}"""),format.raw/*156.10*/(""");
	"""),format.raw/*157.2*/("""}"""),format.raw/*157.3*/("""

"""),format.raw/*159.1*/("""</script>

		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listRegiones:List[tables.Regiones]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listRegiones)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Regiones]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listRegiones) => apply(mapDiccionario,mapPermiso,userMnu,listRegiones)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuTablas/proyectoAgrega.scala.html
                  HASH: 5ddf2e6fc743fcdbd9aa04bc57640fc972f10067
                  MATRIX: 1034->1|1261->135|1294->143|1310->151|1349->153|1377->156|1445->204|1473->206|1549->257|1626->314|1656->317|3050->1684|3073->1698|3108->1712|3303->1880|3345->1906|3384->1907|3423->1918|3466->1934|3480->1939|3508->1946|3538->1949|3552->1954|3580->1961|3631->1981|3667->1990|3777->2073|3800->2087|3835->2101|4960->3196|4992->3200|5084->3263|5114->3264|5147->3269|5241->3335|5270->3336|5346->3383|5376->3384|5407->3387|5523->3474|5553->3475|5595->3488|5846->3710|5876->3711|5913->3720|5959->3737|5989->3738|6028->3748|6094->3785|6124->3786|6164->3797|6223->3827|6253->3828|6292->3839|6322->3840|6355->3844|6385->3845|6424->3855|6642->4045|6672->4046|6708->4054|6737->4055|6774->4064|6804->4065|6836->4069|6865->4070|6897->4074|6961->4109|6991->4110|7022->4113|7139->4201|7169->4202|7211->4215|7477->4452|7507->4453|7544->4462|7596->4485|7626->4486|7665->4496|7793->4594|7824->4595|7864->4606|7926->4639|7956->4640|7995->4651|8025->4652|8062->4661|8113->4683|8143->4684|8182->4694|8248->4731|8278->4732|8318->4743|8377->4773|8407->4774|8446->4785|8476->4786|8512->4794|8541->4795|8578->4804|8608->4805|8640->4809|8669->4810|8699->4812
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|80->49|80->49|80->49|85->54|85->54|85->54|86->55|86->55|86->55|86->55|86->55|86->55|86->55|87->56|88->57|92->61|92->61|92->61|128->97|132->101|134->103|134->103|135->104|136->105|136->105|138->107|138->107|139->108|141->110|141->110|142->111|149->118|149->118|150->119|150->119|150->119|151->120|151->120|151->120|152->121|153->122|153->122|154->123|154->123|154->123|154->123|155->124|159->128|159->128|160->129|160->129|161->130|161->130|162->131|162->131|164->133|164->133|164->133|165->134|167->136|167->136|168->137|175->144|175->144|176->145|176->145|176->145|177->146|177->146|177->146|178->147|179->148|179->148|180->149|180->149|181->150|181->150|181->150|182->151|182->151|182->151|183->152|184->153|184->153|185->154|185->154|186->155|186->155|187->156|187->156|188->157|188->157|190->159
                  -- GENERATED --
              */
          