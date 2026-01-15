
package views.html

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

object modalProyectoNuevo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[Map[String,String],List[tables.Regiones],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], listRegiones: List[tables.Regiones]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
  """),format.raw/*3.3*/("""<div id='modalProyectoNuevo' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>AGREGAR NUEVO PROYECTO</h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<form id="formNuevoProyecto">
						<table class="table table-sm table-bordered table-condensed table-fluid">
							<tr>
								<td style="text-align:left;">NOMBRE LARGO: </td>
								<td style="text-align:left;">
									<input type="text" class="form-control left"
										name="nombre"
										id="proyectoNombre"
										autocomplete="off"
										maxlength="100"
										onchange="value = value.trim();">
								</td>
							</tr>
							<tr>
								<td style="text-align:left;">NOMBRE CORTO (*): </td>
								<td style="text-align:left;">
									<input type="text" class="form-control left"
										id="proyectoNickName"
										name="nickName"
										autocomplete="off"
										maxlength="50"
										required
										onchange="value = value.trim();verificaNickProyecto(value);">
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
								<td style="text-align:left;">"""),_display_(/*49.39*/mapDiccionario/*49.53*/.get("Region")),format.raw/*49.67*/(""": </td>
								<td style="text-align:left;">
									<select class="custom-select" 
										name="cod_region" 
										onchange="actualizaComunasProyecto(value);">
										"""),_display_(/*54.12*/for(lista <- listRegiones) yield /*54.38*/{_display_(Seq[Any](format.raw/*54.39*/("""
											"""),format.raw/*55.12*/("""<option value=""""),_display_(/*55.28*/lista/*55.33*/.codigo),format.raw/*55.40*/("""">"""),_display_(/*55.43*/lista/*55.48*/.nombre),format.raw/*55.55*/("""</option>
										""")))}),format.raw/*56.12*/("""
									"""),format.raw/*57.10*/("""</select>
								</td>
							</tr>
							<tr>
								<td style="text-align:left;">"""),_display_(/*61.39*/mapDiccionario/*61.53*/.get("Comuna")),format.raw/*61.67*/(""": </td>
								<td style="text-align:left;">
									<div id="selectComunaProyecto">
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
					</form>
					<div class="noprint">
						<div class="row justify-content-md-center" >
							<div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
								<input type="button"  value="GRABAR PROYECTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick = "grabarProyecto();" data-dismiss='modal'>
							</div>
							<div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
								<button type='button' class='btn btn-sm  btn-warning rounded-pill btn-block' data-dismiss='modal'>Cancelar</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
<script type="text/javascript">

	const grabarProyecto = () =>"""),format.raw/*101.30*/("""{"""),format.raw/*101.31*/("""
		"""),format.raw/*102.3*/("""if($("#proyectoNickName").val().trim()=="")"""),format.raw/*102.46*/("""{"""),format.raw/*102.47*/("""
	     	"""),format.raw/*103.8*/("""alertify.alert('El nombre corto (nickName) del proyecto es obligatorio', function () """),format.raw/*103.93*/("""{"""),format.raw/*103.94*/("""
				"""),format.raw/*104.5*/("""$("#proyectoNickName").val("");
				$('#modalProyectoNuevo').modal('show');
	 		"""),format.raw/*106.5*/("""}"""),format.raw/*106.6*/(""");
		"""),format.raw/*107.3*/("""}"""),format.raw/*107.4*/("""else"""),format.raw/*107.8*/("""{"""),format.raw/*107.9*/("""
			"""),format.raw/*108.4*/("""var formData = new FormData(document.getElementById("formNuevoProyecto"));
			$.ajax("""),format.raw/*109.11*/("""{"""),format.raw/*109.12*/("""
	            """),format.raw/*110.14*/("""url: "/proyectoGrabaAjax/",
	            type: "POST",
	            method: "POST",
	            data: formData,
	            cache: false,
	            contentType: false,
		     	processData: false,
		     	success: function(data)"""),format.raw/*117.32*/("""{"""),format.raw/*117.33*/("""
		     		"""),format.raw/*118.10*/("""if(data == "error")"""),format.raw/*118.29*/("""{"""),format.raw/*118.30*/("""
		     			"""),format.raw/*119.11*/("""alertify.alert(msgError, function () """),format.raw/*119.48*/("""{"""),format.raw/*119.49*/("""
			     			"""),format.raw/*120.12*/("""location.href = "/";
			     		"""),format.raw/*121.11*/("""}"""),format.raw/*121.12*/(""");
		     		"""),format.raw/*122.10*/("""}"""),format.raw/*122.11*/("""else if(data == "existe")"""),format.raw/*122.36*/("""{"""),format.raw/*122.37*/("""
						"""),format.raw/*123.7*/("""alertify.alert("El proyecto ya existe", function () """),format.raw/*123.59*/("""{"""),format.raw/*123.60*/("""
			     		"""),format.raw/*124.11*/("""}"""),format.raw/*124.12*/(""");
						 
		     		"""),format.raw/*126.10*/("""}"""),format.raw/*126.11*/("""else"""),format.raw/*126.15*/("""{"""),format.raw/*126.16*/("""
						"""),format.raw/*127.7*/("""let id_proyecto = data;
						proyectoGrabaAjax(id_proyecto);
		     		"""),format.raw/*129.10*/("""}"""),format.raw/*129.11*/("""
		     	"""),format.raw/*130.9*/("""}"""),format.raw/*130.10*/("""
	        """),format.raw/*131.10*/("""}"""),format.raw/*131.11*/(""");
		"""),format.raw/*132.3*/("""}"""),format.raw/*132.4*/("""
	"""),format.raw/*133.2*/("""}"""),format.raw/*133.3*/("""

	"""),format.raw/*135.2*/("""const actualizaComunasProyecto = (cod_region) => """),format.raw/*135.51*/("""{"""),format.raw/*135.52*/("""
		"""),format.raw/*136.3*/("""var formData = new FormData();
		formData.append('cod_region', cod_region);
	  	$.ajax("""),format.raw/*138.12*/("""{"""),format.raw/*138.13*/("""
            """),format.raw/*139.13*/("""url: "/selComuna1Ajax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(data)"""),format.raw/*146.31*/("""{"""),format.raw/*146.32*/("""
	     		"""),format.raw/*147.9*/("""if(data=="error")"""),format.raw/*147.26*/("""{"""),format.raw/*147.27*/("""
	     			"""),format.raw/*148.10*/("""alertify.alert(msgError, function () """),format.raw/*148.47*/("""{"""),format.raw/*148.48*/("""
		     			"""),format.raw/*149.11*/("""location.href = "/";
		     		"""),format.raw/*150.10*/("""}"""),format.raw/*150.11*/(""");
	     		"""),format.raw/*151.9*/("""}"""),format.raw/*151.10*/("""else"""),format.raw/*151.14*/("""{"""),format.raw/*151.15*/("""
	     			"""),format.raw/*152.10*/("""data = data.replace(/&lt;/g,"<");
					data = data.replace(/&gt;/g,">");
					data = data.replace(/&#x27;/g,"\"");
	     			document.getElementById('selectComunaProyecto').innerHTML = data;
	     		"""),format.raw/*156.9*/("""}"""),format.raw/*156.10*/("""
	     	"""),format.raw/*157.8*/("""}"""),format.raw/*157.9*/("""
        """),format.raw/*158.9*/("""}"""),format.raw/*158.10*/(""");
	"""),format.raw/*159.2*/("""}"""),format.raw/*159.3*/("""

	"""),format.raw/*161.2*/("""const verificaNickProyecto = (proyectoNickName) => """),format.raw/*161.53*/("""{"""),format.raw/*161.54*/("""
		"""),format.raw/*162.3*/("""var formData = new FormData();
	  	formData.append('nickName',proyectoNickName);
        $.ajax("""),format.raw/*164.16*/("""{"""),format.raw/*164.17*/("""
            """),format.raw/*165.13*/("""url: "/verificaNickProyectoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*172.36*/("""{"""),format.raw/*172.37*/("""
	     		"""),format.raw/*173.9*/("""if(respuesta=="existe")"""),format.raw/*173.32*/("""{"""),format.raw/*173.33*/("""
	     			"""),format.raw/*174.10*/("""alertify.alert('El nombre corto (nickName) del proyecto ya existe, intente con otro', function () """),format.raw/*174.108*/("""{"""),format.raw/*174.109*/("""
	     				"""),format.raw/*175.11*/("""$("#proyectoNickName").val("");
		     		"""),format.raw/*176.10*/("""}"""),format.raw/*176.11*/(""");
	     		"""),format.raw/*177.9*/("""}"""),format.raw/*177.10*/("""
	     		"""),format.raw/*178.9*/("""if(respuesta=="error")"""),format.raw/*178.31*/("""{"""),format.raw/*178.32*/("""
	     			"""),format.raw/*179.10*/("""alertify.alert(msgError, function () """),format.raw/*179.47*/("""{"""),format.raw/*179.48*/("""
		     			"""),format.raw/*180.11*/("""location.href = "/";
		     		"""),format.raw/*181.10*/("""}"""),format.raw/*181.11*/(""");
	     		"""),format.raw/*182.9*/("""}"""),format.raw/*182.10*/("""
	     	"""),format.raw/*183.8*/("""}"""),format.raw/*183.9*/("""
        """),format.raw/*184.9*/("""}"""),format.raw/*184.10*/(""");
	"""),format.raw/*185.2*/("""}"""),format.raw/*185.3*/("""

"""),format.raw/*187.1*/("""</script>

"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],listRegiones:List[tables.Regiones]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,listRegiones)

  def f:((Map[String,String],List[tables.Regiones]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,listRegiones) => apply(mapDiccionario,listRegiones)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/modalProyectoNuevo.scala.html
                  HASH: 2510d36c56077f18ebca640142a642a90d5b22ae
                  MATRIX: 992->1|1159->75|1188->78|2932->1795|2955->1809|2990->1823|3198->2004|3240->2030|3279->2031|3319->2043|3362->2059|3376->2064|3404->2071|3434->2074|3448->2079|3476->2086|3528->2107|3566->2117|3680->2204|3703->2218|3738->2232|4994->3459|5024->3460|5055->3463|5127->3506|5157->3507|5193->3515|5307->3600|5337->3601|5370->3606|5478->3686|5507->3687|5540->3692|5569->3693|5601->3697|5630->3698|5662->3702|5776->3787|5806->3788|5849->3802|6110->4034|6140->4035|6179->4045|6227->4064|6257->4065|6297->4076|6363->4113|6393->4114|6434->4126|6494->4157|6524->4158|6565->4170|6595->4171|6649->4196|6679->4197|6714->4204|6795->4256|6825->4257|6865->4268|6895->4269|6944->4289|6974->4290|7007->4294|7037->4295|7072->4302|7172->4373|7202->4374|7239->4383|7269->4384|7308->4394|7338->4395|7371->4400|7400->4401|7430->4403|7459->4404|7490->4407|7568->4456|7598->4457|7629->4460|7745->4547|7775->4548|7817->4561|8068->4783|8098->4784|8135->4793|8181->4810|8211->4811|8250->4821|8316->4858|8346->4859|8386->4870|8445->4900|8475->4901|8514->4912|8544->4913|8577->4917|8607->4918|8646->4928|8872->5126|8902->5127|8938->5135|8967->5136|9004->5145|9034->5146|9066->5150|9095->5151|9126->5154|9206->5205|9236->5206|9267->5209|9392->5305|9422->5306|9464->5319|9730->5556|9760->5557|9797->5566|9849->5589|9879->5590|9918->5600|10046->5698|10077->5699|10117->5710|10187->5751|10217->5752|10256->5763|10286->5764|10323->5773|10374->5795|10404->5796|10443->5806|10509->5843|10539->5844|10579->5855|10638->5885|10668->5886|10707->5897|10737->5898|10773->5906|10802->5907|10839->5916|10869->5917|10901->5921|10930->5922|10960->5924
                  LINES: 28->1|33->2|34->3|80->49|80->49|80->49|85->54|85->54|85->54|86->55|86->55|86->55|86->55|86->55|86->55|86->55|87->56|88->57|92->61|92->61|92->61|132->101|132->101|133->102|133->102|133->102|134->103|134->103|134->103|135->104|137->106|137->106|138->107|138->107|138->107|138->107|139->108|140->109|140->109|141->110|148->117|148->117|149->118|149->118|149->118|150->119|150->119|150->119|151->120|152->121|152->121|153->122|153->122|153->122|153->122|154->123|154->123|154->123|155->124|155->124|157->126|157->126|157->126|157->126|158->127|160->129|160->129|161->130|161->130|162->131|162->131|163->132|163->132|164->133|164->133|166->135|166->135|166->135|167->136|169->138|169->138|170->139|177->146|177->146|178->147|178->147|178->147|179->148|179->148|179->148|180->149|181->150|181->150|182->151|182->151|182->151|182->151|183->152|187->156|187->156|188->157|188->157|189->158|189->158|190->159|190->159|192->161|192->161|192->161|193->162|195->164|195->164|196->165|203->172|203->172|204->173|204->173|204->173|205->174|205->174|205->174|206->175|207->176|207->176|208->177|208->177|209->178|209->178|209->178|210->179|210->179|210->179|211->180|212->181|212->181|213->182|213->182|214->183|214->183|215->184|215->184|216->185|216->185|218->187
                  -- GENERATED --
              */
          