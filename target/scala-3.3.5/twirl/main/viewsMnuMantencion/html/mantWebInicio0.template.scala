
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

object mantWebInicio0 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[List[String],String,Long,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(listaPaises: List[String], mensaje: String, id_equipo: Long):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
"""),_display_(/*3.2*/main("")/*3.10*/ {_display_(Seq[Any](format.raw/*3.12*/("""

"""),format.raw/*5.1*/("""<style>
	.fondo"""),format.raw/*6.8*/("""{"""),format.raw/*6.9*/("""
	   """),format.raw/*7.5*/("""background: url('/assets/images/area5-fondo.jpg') no-repeat center center;
	   background-size: cover;
	   width: 100% ;
	   text-align: center;
	 
	"""),format.raw/*12.2*/("""}"""),format.raw/*12.3*/("""
"""),format.raw/*13.1*/("""</style>

	<div class="row">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div style="height:400px; padding-top:60px;" class="fondo">
				<div class="container align-bottom" >
					<div class="row justify-content-md-center" >
						<div class="col-xs-10 col-sm-4 col-md-4 col-lg-4">
	                    	<h2>MADA MANTENCION</h2>
		                    <form onsubmit="return validarIngreso()">
		                    		<input type="hidden" id="gRecaptchaResponse" name="gRecaptchaResponse">
		                    		<div class="input-group mb-3">
									  <div class="input-group-prepend">
									    <label class="input-group-text" for="pais" style="width:120px">Pais</label>
									  </div>
									  <select class="custom-select" style="min-width:120px" id="pais" name="pais" 
									  		onchange='$("#ingPais").val(value)'>
									  	"""),_display_(/*30.14*/for(lista <- listaPaises) yield /*30.39*/{_display_(Seq[Any](format.raw/*30.40*/("""
									  		"""),format.raw/*31.14*/("""<option value=""""),_display_(/*31.30*/lista),format.raw/*31.35*/("""">"""),_display_(/*31.38*/lista),format.raw/*31.43*/("""</option>
									  	""")))}),format.raw/*32.14*/("""
									  """),format.raw/*33.12*/("""</select>
									</div>
									<div class="input-group mb-3">
									  <div class="input-group-prepend">
									    <label class="input-group-text" for="empresa" style="width:120px">Empresa</label>
									  </div>
									  <input type="text" class="form-control" style="min-width:120px" 
									  		onchange='value= value.toUpperCase(); $("#ingEmpresa").val(value.toUpperCase())' 
												id="empresa" name="empresa" placeholder="Empresa" required>
									</div>
									<div class="input-group mb-3">
									  <div class="input-group-prepend">
									    <label class="input-group-text" for="userName"  style="width:120px">Usuario</label>
									  </div>
									  <input type="text" class="form-control" style="min-width:120px" 
									  		onchange='value= value.toUpperCase(); $("#ingUserName").val(value.toUpperCase())' id="userName" name="userName" placeholder="Usuario" required>
									</div>
									<div class="input-group mb-3">
									  <div class="input-group-prepend">
									    <label class="input-group-text" for="userKey"  style="width:120px">Clave</label>
									  </div>
									  <input type="password" class="form-control" style="min-width:120px" 
									  		onchange='$("#ingUserKey").val(value)' name="userKey" placeholder="Clave" required>
									</div>
			                    
			
			
									<div id="ingresar1">
										<input type="submit"  value="INGRESAR" class="btn btn-success rounded-pill btn-block">
									</div>
			

									
		                    </form>
		                    <br>
						</div>
	                  	<div class="col-xs-10 col-sm-4 col-md-4 col-lg-4" align="center">
	                  	<br>
	                    	<img src="/assets/images/mada-pc.png" alt="MADA" width="80%" height="auto">
	                  	</div>
	         		</div>
	         	</div>
	         </div>
		</div>


<form id="ingresar" action="/routes3/mantWebInicio1/" method="POST">
	<input type="hidden" id="ingCaptcha" name="gRecaptchaResponse">
	<input type="hidden" id="ingPais" name="pais" value="CHILE">
	<input type="hidden" id="ingEmpresa" name="empresa">
	<input type="hidden" id="ingUserName" name="userName">
	<input type="hidden" id="ingUserKey" name="userKey">
	<input type="hidden" name="id_equipo" value=""""),_display_(/*85.48*/id_equipo),format.raw/*85.57*/("""">
</form>


""")))}),format.raw/*89.2*/("""

"""),format.raw/*91.1*/("""<script src="https://www.google.com/recaptcha/enterprise.js?render=6Lcm3DUjAAAAAMEhoeru8yNe1j7XGIRV2vG2nsV9"></script>
<script>
	function validarIngreso() """),format.raw/*93.28*/("""{"""),format.raw/*93.29*/("""
	  """),format.raw/*94.4*/("""grecaptcha.enterprise.ready(async () => """),format.raw/*94.44*/("""{"""),format.raw/*94.45*/("""
	    """),format.raw/*95.6*/("""const token = await grecaptcha.enterprise.execute('6Lcm3DUjAAAAAMEhoeru8yNe1j7XGIRV2vG2nsV9', """),format.raw/*95.100*/("""{"""),format.raw/*95.101*/("""action: 'LOGIN'"""),format.raw/*95.116*/("""}"""),format.raw/*95.117*/(""").then(onSuccessIngreso, onError);
	  """),format.raw/*96.4*/("""}"""),format.raw/*96.5*/(""");
	  if($("#gRecaptchaResponse").val().length > 0)"""),format.raw/*97.49*/("""{"""),format.raw/*97.50*/("""
	  	"""),format.raw/*98.5*/("""return true;
	  """),format.raw/*99.4*/("""}"""),format.raw/*99.5*/("""else"""),format.raw/*99.9*/("""{"""),format.raw/*99.10*/("""
	  	"""),format.raw/*100.5*/("""return false;
	  """),format.raw/*101.4*/("""}"""),format.raw/*101.5*/("""
	"""),format.raw/*102.2*/("""}"""),format.raw/*102.3*/("""
	"""),format.raw/*103.2*/("""function onSuccessIngreso(action_token)"""),format.raw/*103.41*/("""{"""),format.raw/*103.42*/("""
		
		"""),format.raw/*105.3*/("""let btnIngresar = "<button style=\"visibility: visible;\" class=\"btn btn-success rounded-pill btn-block\""+
		" type=\"button\" disabled>"+
		"<span class=\"spinner-border spinner-border-sm mr-2\"></span>Enviando datos...</button>";
		$("#ingresar1").html(btnIngresar);
		
			$("#ingCaptcha").val(action_token);
			$("#ingresar").submit();
	"""),format.raw/*112.2*/("""}"""),format.raw/*112.3*/("""
	
	"""),format.raw/*114.2*/("""function validarRecuperar() """),format.raw/*114.30*/("""{"""),format.raw/*114.31*/("""
	  """),format.raw/*115.4*/("""grecaptcha.enterprise.ready(async () => """),format.raw/*115.44*/("""{"""),format.raw/*115.45*/("""
	    """),format.raw/*116.6*/("""const token = await grecaptcha.enterprise.execute('6Lcm3DUjAAAAAMEhoeru8yNe1j7XGIRV2vG2nsV9', """),format.raw/*116.100*/("""{"""),format.raw/*116.101*/("""action: 'LOGIN'"""),format.raw/*116.116*/("""}"""),format.raw/*116.117*/(""").then(onSuccessRecuperar, onError);
	  """),format.raw/*117.4*/("""}"""),format.raw/*117.5*/(""");
	  if($("#gRecaptchaResponse").val().length > 0)"""),format.raw/*118.49*/("""{"""),format.raw/*118.50*/("""
	  	"""),format.raw/*119.5*/("""return true;
	  """),format.raw/*120.4*/("""}"""),format.raw/*120.5*/("""else"""),format.raw/*120.9*/("""{"""),format.raw/*120.10*/("""
	  	"""),format.raw/*121.5*/("""return false;
	  """),format.raw/*122.4*/("""}"""),format.raw/*122.5*/("""
	"""),format.raw/*123.2*/("""}"""),format.raw/*123.3*/("""
	"""),format.raw/*124.2*/("""function onSuccessRecuperar(action_token)"""),format.raw/*124.43*/("""{"""),format.raw/*124.44*/("""
		
		"""),format.raw/*126.3*/("""let btnRecuperar = "<button style=\"visibility: visible;\" class=\"btn btn-success rounded-pill btn-block\""+
		" type=\"button\" disabled>"+
		"<span class=\"spinner-border spinner-border-sm mr-2\"></span>Enviando datos...</button>";
		$("#recuperar1").html(btnRecuperar);
			
			$("#recuCaptcha").val(action_token);
			$("#recuperar").submit();
	"""),format.raw/*133.2*/("""}"""),format.raw/*133.3*/("""
	
	"""),format.raw/*135.2*/("""function onError(reason) """),format.raw/*135.27*/("""{"""),format.raw/*135.28*/("""
	  """),format.raw/*136.4*/("""alert("problemas con el captcha, contactar a inqsol.cl");
      location.reload();
    """),format.raw/*138.5*/("""}"""),format.raw/*138.6*/("""
"""),format.raw/*139.1*/("""</script>


	<script>
            $(document).ready(function() """),format.raw/*143.42*/("""{"""),format.raw/*143.43*/("""
                """),format.raw/*144.17*/("""if(""""),_display_(/*144.22*/mensaje),format.raw/*144.29*/(""""!="0") alert(""""),_display_(/*144.45*/mensaje),format.raw/*144.52*/("""");          
            """),format.raw/*145.13*/("""}"""),format.raw/*145.14*/(""");
    </script>
    
    
    
    
    
    
    
    
    
    
    
"""))
      }
    }
  }

  def render(listaPaises:List[String],mensaje:String,id_equipo:Long): play.twirl.api.HtmlFormat.Appendable = apply(listaPaises,mensaje,id_equipo)

  def f:((List[String],String,Long) => play.twirl.api.HtmlFormat.Appendable) = (listaPaises,mensaje,id_equipo) => apply(listaPaises,mensaje,id_equipo)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuMantencion/mantWebInicio0.scala.html
                  HASH: 0409fbdeff2604a2a670090d1cbc7bbe27df48d3
                  MATRIX: 985->1|1140->63|1167->65|1183->73|1222->75|1250->77|1291->92|1318->93|1349->98|1525->247|1553->248|1581->249|2470->1111|2511->1136|2550->1137|2592->1151|2635->1167|2661->1172|2691->1175|2717->1180|2771->1203|2811->1215|5128->3505|5158->3514|5202->3528|5231->3530|5414->3685|5443->3686|5474->3690|5542->3730|5571->3731|5604->3737|5727->3831|5757->3832|5801->3847|5831->3848|5896->3886|5924->3887|6003->3938|6032->3939|6064->3944|6107->3960|6135->3961|6166->3965|6195->3966|6228->3971|6273->3988|6302->3989|6332->3991|6361->3992|6391->3994|6459->4033|6489->4034|6523->4040|6893->4382|6922->4383|6954->4387|7011->4415|7041->4416|7073->4420|7142->4460|7172->4461|7206->4467|7330->4561|7361->4562|7406->4577|7437->4578|7505->4618|7534->4619|7614->4670|7644->4671|7677->4676|7721->4692|7750->4693|7782->4697|7812->4698|7845->4703|7890->4720|7919->4721|7949->4723|7978->4724|8008->4726|8078->4767|8108->4768|8142->4774|8518->5122|8547->5123|8579->5127|8633->5152|8663->5153|8695->5157|8810->5244|8839->5245|8868->5246|8960->5309|8990->5310|9036->5327|9069->5332|9098->5339|9142->5355|9171->5362|9226->5388|9256->5389
                  LINES: 28->1|33->2|34->3|34->3|34->3|36->5|37->6|37->6|38->7|43->12|43->12|44->13|61->30|61->30|61->30|62->31|62->31|62->31|62->31|62->31|63->32|64->33|116->85|116->85|120->89|122->91|124->93|124->93|125->94|125->94|125->94|126->95|126->95|126->95|126->95|126->95|127->96|127->96|128->97|128->97|129->98|130->99|130->99|130->99|130->99|131->100|132->101|132->101|133->102|133->102|134->103|134->103|134->103|136->105|143->112|143->112|145->114|145->114|145->114|146->115|146->115|146->115|147->116|147->116|147->116|147->116|147->116|148->117|148->117|149->118|149->118|150->119|151->120|151->120|151->120|151->120|152->121|153->122|153->122|154->123|154->123|155->124|155->124|155->124|157->126|164->133|164->133|166->135|166->135|166->135|167->136|169->138|169->138|170->139|174->143|174->143|175->144|175->144|175->144|175->144|175->144|176->145|176->145
                  -- GENERATED --
              */
          