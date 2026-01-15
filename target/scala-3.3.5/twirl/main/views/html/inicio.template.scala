
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

object inicio extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[List[String],String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(listaPaises: List[String], mensaje: String):play.twirl.api.HtmlFormat.Appendable = {
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
	                    	<h2>MADA</h2>
		                    <form onsubmit="return validarIngreso()">
		                    		<input type="hidden" id="gRecaptchaResponse" name="gRecaptchaResponse">
		                    		<div class="input-group mb-3">
									  <div class="input-group-prepend">
									    <label class="input-group-text" for="pais" style="width:120px">Pais</label>
									  </div>
									  <select class="custom-select" style="min-width:120px" id="pais" name="pais"
									  		onchange='$("#ingPais").val(this.value)'>
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
									  		onchange='this.value= this.value.toUpperCase(); $("#ingEmpresa").val(this.value.toUpperCase())'
												id="empresa" name="empresa" placeholder="Empresa" required>
									</div>
									<div class="input-group mb-3">
									  <div class="input-group-prepend">
									    <label class="input-group-text" for="userName"  style="width:120px">Usuario</label>
									  </div>
									  <input type="text" class="form-control" style="min-width:120px"
									  		onchange='this.value= this.value.toUpperCase(); $("#ingUserName").val(this.value.toUpperCase())' id="userName" name="userName" placeholder="Usuario" required>
									</div>
									<div class="input-group mb-3">
									  <div class="input-group-prepend">
									    <label class="input-group-text" for="userKey"  style="width:120px">Clave</label>
									  </div>
									  <input type="password" class="form-control" style="min-width:120px"
									  		onchange='$("#ingUserKey").val(this.value)' name="userKey" placeholder="Clave" required>
									</div>



									<div id="ingresar1">
										<input type="submit"  value="INGRESAR" class="btn btn-success rounded-pill btn-block">
									</div>



		                    </form>
		                    <br>
		                    <u><a href="#" onclick="$('#olvidoClave').modal('show');">recuperar clave</a></u>
						</div>
	                  	<div class="col-xs-10 col-sm-4 col-md-4 col-lg-4" align="center">
	                  	<br>
	                    	<img src="/assets/images/mada-pc.png" alt="MADA" width="80%" height="auto">
	                  	</div>
	         		</div>
	         	</div>
	         </div>
		</div>
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
	         <div style="padding:20px">
		         <div class="container">


					<style>
						.borderless td, .borderless th """),format.raw/*84.38*/("""{"""),format.raw/*84.39*/("""
						    """),format.raw/*85.11*/("""border: none;
							text-align: center;
						"""),format.raw/*87.7*/("""}"""),format.raw/*87.8*/("""
					"""),format.raw/*88.6*/("""</style>

					<table class="table table-sm borderless table-condensed table-fluid">
						<tr>
							<th>
								<input type="button" class="form-control font-weight-bold"
									style="text-align: center; color:blue"
									value="Más Mada..."
									onclick="window.open('https://inqsol.cl/servicios-mada/','_blank')">
							</th>
							<th>
								<input type="button" class="form-control font-weight-bold"
									style="text-align: center; color:blue"
									value="Tutoriales"
									onclick="window.open('https://inqsol.cl/tutoriales/#mada','_blank')">
							</th>
							<th>
								<input type="button" class="form-control font-weight-bold"
									style="text-align: center; color:blue"
									value="Contacto"
									onclick="window.open('https://inqsol.cl/#contacto','_blank')">
							</th>
							<th>
								<input type="button" class="form-control font-weight-bold"
									style="text-align: center; color:blue"
									value="Asesoría"
									onclick="window.open('https://inqsol.cl/asesoria/?utm_source=header','_blank')">
							</th>
						</tr>
					</table>


	         		<hr>
					<h5>ÁREAS CLAVE DE <span>MADA</span></h5>
					<hr>
	         		<div class="row">
						<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
						  <img src="/assets/images/area2-mejores-practicas.png" alt="MADA" width="20%" height="auto">
						  <h5>Mejores Prácticas</h5>
						  <p>Desarrollado por profesionales con vasta experiencia demostrable en el rubro. Reúne las mejores prácticas del mercado.</p>
						</div>
						<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
						  <img src="/assets/images/area3-innovacion.png" alt="MADA" width="20%" height="auto">
						  <h5>Innovación</h5>
						  <p>Es un producto innovador en el mercado, orientado a la gestión y control de inventarios como una unidad de negocios.</p>
						</div>
						<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
						  <img src="/assets/images/area4-prevencion.png" alt="MADA" width="20%" height="auto">
						  <h5>Prevención</h5>
						  <p>Administración y registro de mantenciones preventivas y correctivas sobre equipos, con sus respectivos costos y análisis.</p>
						</div>
					</div>
	     				<hr>
	  					<div align="left">
						<p class="font-weight-lighter">Contactos: pbarros&#64;inqsol.cl - jiprieto&#64;inqsol.cl</p>
	  					<p class="font-weight-lighter">Napoleon #3233, Las Condes, Santiago de Chile<br>Copyright 2016 &copy; Mada</p>
					</div>
				</div>
			</div>
		</div>
   	</div>

   	<div id='olvidoClave' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable ' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>RECUPERAR CLAVE</h5>
					<button type='button' class='close' data-dismiss='modal' aria-label='Close'>
			          <span aria-hidden='true'>&times;</span>
			        </button>
				</div>
				<div class='modal-body'>
					<div class="row justify-content-md-center" >
		                    <form onsubmit="return validarRecuperar()">
		                    		<input type="hidden" id="gRecaptchaResponse2" name="gRecaptchaResponse">
		                    		<div class="input-group mb-3">
									  <div class="input-group-prepend">
									    <label class="input-group-text" for="pais" style="width:120px">Pais</label>
									  </div>
									  <select class="custom-select" style="min-width:220px"
									  		onchange='$("#recuPais").val(value)' name="pais">
									  	"""),_display_(/*169.14*/for(lista <- listaPaises) yield /*169.39*/{_display_(Seq[Any](format.raw/*169.40*/("""
									  		"""),format.raw/*170.14*/("""<option value=""""),_display_(/*170.30*/lista),format.raw/*170.35*/("""">"""),_display_(/*170.38*/lista),format.raw/*170.43*/("""</option>
									  	""")))}),format.raw/*171.14*/("""
									  """),format.raw/*172.12*/("""</select>
									</div>
									<div class="input-group mb-3">
									  <div class="input-group-prepend">
									    <label class="input-group-text" for="empresa" style="width:120px">Empresa</label>
									  </div>
									  <input type="text" class="form-control" style="min-width:220px"
									  		onchange='value= value.toUpperCase(); $("#recuEmpresa").val(value.toUpperCase())' name="empresa" placeholder="Empresa" required>
									</div>
									<div class="input-group mb-3">
									  <div class="input-group-prepend">
									    <label class="input-group-text" for="userName"  style="width:120px">Usuario</label>
									  </div>
									  <input type="text" class="form-control" style="min-width:220px"
									  		onchange='value= value.toUpperCase(); $("#recuUserName").val(value.toUpperCase())' name="userName" placeholder="Usuario" required>
									</div>
									<div class="input-group mb-3">
									  <div class="input-group-prepend">
									    <label class="input-group-text" for="eMail"  style="width:120px">Correo (eMail)</label>
									  </div>
									  <input type="text" class="form-control" style="min-width:220px"
									  		onchange='value= value.toUpperCase(); $("#recueMail").val(value.toUpperCase())'  name="eMail" placeholder="eMail" required>
									</div>


									<div id="recuperar1">
										 <input type="submit"  value="ENVIAR" class="btn btn-success rounded-pill btn-block">
									</div>
		                    </form>
	         		</div>
	         	</div>
	         </div>
		</div>
	</div>

<form id="ingresar" action="/vistaPrincipal/" method="POST">
	<input type="hidden" id="ingCaptcha" name="gRecaptchaResponse">
	<input type="hidden" id="ingPais" name="pais" value="CHILE">
	<input type="hidden" id="ingEmpresa" name="empresa">
	<input type="hidden" id="ingUserName" name="userName">
	<input type="hidden" id="ingUserKey" name="userKey">
</form>

<form id="recuperar" action="/recuperarClave/" method="POST">
	<input type="hidden" id="recuCaptcha" name="gRecaptchaResponse">
	<input type="hidden" id="recuPais" name="pais" value="CHILE">
	<input type="hidden" id="recuEmpresa" name="empresa">
	<input type="hidden" id="recuUserName" name="userName">
	<input type="hidden" id="recueMail" name="eMail">
</form>

""")))}),format.raw/*223.2*/("""

"""),format.raw/*225.1*/("""<script src="https://www.google.com/recaptcha/enterprise.js?render=6Lcm3DUjAAAAAMEhoeru8yNe1j7XGIRV2vG2nsV9"></script>
<script>
	// function validarIngreso() """),format.raw/*227.31*/("""{"""),format.raw/*227.32*/("""
	"""),format.raw/*228.2*/("""//   grecaptcha.enterprise.ready(async () => """),format.raw/*228.47*/("""{"""),format.raw/*228.48*/("""
	"""),format.raw/*229.2*/("""//     const token = await grecaptcha.enterprise.execute('6Lcm3DUjAAAAAMEhoeru8yNe1j7XGIRV2vG2nsV9', """),format.raw/*229.103*/("""{"""),format.raw/*229.104*/("""action: 'LOGIN'"""),format.raw/*229.119*/("""}"""),format.raw/*229.120*/(""").then(onSuccessIngreso, onError);
	//   """),format.raw/*230.7*/("""}"""),format.raw/*230.8*/(""");
	//   if($("#gRecaptchaResponse").val().length > 0)"""),format.raw/*231.52*/("""{"""),format.raw/*231.53*/("""
	"""),format.raw/*232.2*/("""//   	return true;
	//   """),format.raw/*233.7*/("""}"""),format.raw/*233.8*/("""else"""),format.raw/*233.12*/("""{"""),format.raw/*233.13*/("""
	"""),format.raw/*234.2*/("""//   	return false;
	//   """),format.raw/*235.7*/("""}"""),format.raw/*235.8*/("""
	"""),format.raw/*236.2*/("""// """),format.raw/*236.5*/("""}"""),format.raw/*236.6*/("""
	"""),format.raw/*237.2*/("""function validarIngreso() """),format.raw/*237.28*/("""{"""),format.raw/*237.29*/("""
		"""),format.raw/*238.3*/("""grecaptcha.enterprise.ready(async () => """),format.raw/*238.43*/("""{"""),format.raw/*238.44*/("""
			"""),format.raw/*239.4*/("""const token = await grecaptcha.enterprise.execute('6Lcm3DUjAAAAAMEhoeru8yNe1j7XGIRV2vG2nsV9', """),format.raw/*239.98*/("""{"""),format.raw/*239.99*/("""action: 'LOGIN'"""),format.raw/*239.114*/("""}"""),format.raw/*239.115*/(""");
			onSuccessIngreso(token);
		"""),format.raw/*241.3*/("""}"""),format.raw/*241.4*/(""");
		return false; // evita envío inmediato
	"""),format.raw/*243.2*/("""}"""),format.raw/*243.3*/("""
	"""),format.raw/*244.2*/("""function onSuccessIngreso(action_token)"""),format.raw/*244.41*/("""{"""),format.raw/*244.42*/("""

		"""),format.raw/*246.3*/("""let btnIngresar = "<button style=\"visibility: visible;\" class=\"btn btn-success rounded-pill btn-block\""+
		" type=\"button\" disabled>"+
		"<span class=\"spinner-border spinner-border-sm mr-2\"></span>Enviando datos...</button>";
		$("#ingresar1").html(btnIngresar);

			$("#ingCaptcha").val(action_token);
			$("#ingresar").submit();
	"""),format.raw/*253.2*/("""}"""),format.raw/*253.3*/("""

	"""),format.raw/*255.2*/("""function validarRecuperar() """),format.raw/*255.30*/("""{"""),format.raw/*255.31*/("""
	  """),format.raw/*256.4*/("""grecaptcha.enterprise.ready(async () => """),format.raw/*256.44*/("""{"""),format.raw/*256.45*/("""
	    """),format.raw/*257.6*/("""const token = await grecaptcha.enterprise.execute('6Lcm3DUjAAAAAMEhoeru8yNe1j7XGIRV2vG2nsV9', """),format.raw/*257.100*/("""{"""),format.raw/*257.101*/("""action: 'LOGIN'"""),format.raw/*257.116*/("""}"""),format.raw/*257.117*/(""").then(onSuccessRecuperar, onError);
	  """),format.raw/*258.4*/("""}"""),format.raw/*258.5*/(""");
	  if($("#gRecaptchaResponse").val().length > 0)"""),format.raw/*259.49*/("""{"""),format.raw/*259.50*/("""
	  	"""),format.raw/*260.5*/("""return true;
	  """),format.raw/*261.4*/("""}"""),format.raw/*261.5*/("""else"""),format.raw/*261.9*/("""{"""),format.raw/*261.10*/("""
	  	"""),format.raw/*262.5*/("""return false;
	  """),format.raw/*263.4*/("""}"""),format.raw/*263.5*/("""
	"""),format.raw/*264.2*/("""}"""),format.raw/*264.3*/("""
	"""),format.raw/*265.2*/("""function onSuccessRecuperar(action_token)"""),format.raw/*265.43*/("""{"""),format.raw/*265.44*/("""

		"""),format.raw/*267.3*/("""let btnRecuperar = "<button style=\"visibility: visible;\" class=\"btn btn-success rounded-pill btn-block\""+
		" type=\"button\" disabled>"+
		"<span class=\"spinner-border spinner-border-sm mr-2\"></span>Enviando datos...</button>";
		$("#recuperar1").html(btnRecuperar);

			$("#recuCaptcha").val(action_token);
			$("#recuperar").submit();
	"""),format.raw/*274.2*/("""}"""),format.raw/*274.3*/("""

	"""),format.raw/*276.2*/("""function onError(reason) """),format.raw/*276.27*/("""{"""),format.raw/*276.28*/("""
	  """),format.raw/*277.4*/("""alert("problemas con el captcha, contactar a inqsol.cl");
      location.reload();
    """),format.raw/*279.5*/("""}"""),format.raw/*279.6*/("""
"""),format.raw/*280.1*/("""</script>


	<script>
            $(document).ready(function() """),format.raw/*284.42*/("""{"""),format.raw/*284.43*/("""
                """),format.raw/*285.17*/("""if(""""),_display_(/*285.22*/mensaje),format.raw/*285.29*/(""""!="0") alert(""""),_display_(/*285.45*/mensaje),format.raw/*285.52*/("""");
            """),format.raw/*286.13*/("""}"""),format.raw/*286.14*/(""");
    </script>











"""))
      }
    }
  }

  def render(listaPaises:List[String],mensaje:String): play.twirl.api.HtmlFormat.Appendable = apply(listaPaises,mensaje)

  def f:((List[String],String) => play.twirl.api.HtmlFormat.Appendable) = (listaPaises,mensaje) => apply(listaPaises,mensaje)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/inicio.scala.html
                  HASH: 2bdd44f7006add980b73c9be199df4a511fb12ed
                  MATRIX: 959->1|1097->46|1124->48|1140->56|1179->58|1207->60|1248->75|1275->76|1306->81|1480->228|1508->229|1536->230|2418->1085|2459->1110|2498->1111|2540->1125|2583->1141|2609->1146|2639->1149|2665->1154|2719->1177|2759->1189|4942->3344|4971->3345|5010->3356|5084->3403|5112->3404|5145->3410|8744->6981|8786->7006|8826->7007|8869->7021|8913->7037|8940->7042|8971->7045|8998->7050|9053->7073|9094->7085|11421->9381|11451->9383|11638->9541|11668->9542|11698->9544|11772->9589|11802->9590|11832->9592|11963->9693|11994->9694|12039->9709|12070->9710|12139->9751|12168->9752|12251->9806|12281->9807|12311->9809|12364->9834|12393->9835|12426->9839|12456->9840|12486->9842|12540->9868|12569->9869|12599->9871|12630->9874|12659->9875|12689->9877|12744->9903|12774->9904|12805->9907|12874->9947|12904->9948|12936->9952|13059->10046|13089->10047|13134->10062|13165->10063|13226->10096|13255->10097|13328->10142|13357->10143|13387->10145|13455->10184|13485->10185|13517->10189|13885->10529|13914->10530|13945->10533|14002->10561|14032->10562|14064->10566|14133->10606|14163->10607|14197->10613|14321->10707|14352->10708|14397->10723|14428->10724|14496->10764|14525->10765|14605->10816|14635->10817|14668->10822|14712->10838|14741->10839|14773->10843|14803->10844|14836->10849|14881->10866|14910->10867|14940->10869|14969->10870|14999->10872|15069->10913|15099->10914|15131->10918|15504->11263|15533->11264|15564->11267|15618->11292|15648->11293|15680->11297|15795->11384|15824->11385|15853->11386|15945->11449|15975->11450|16021->11467|16054->11472|16083->11479|16127->11495|16156->11502|16201->11518|16231->11519
                  LINES: 28->1|33->2|34->3|34->3|34->3|36->5|37->6|37->6|38->7|43->12|43->12|44->13|61->30|61->30|61->30|62->31|62->31|62->31|62->31|62->31|63->32|64->33|115->84|115->84|116->85|118->87|118->87|119->88|200->169|200->169|200->169|201->170|201->170|201->170|201->170|201->170|202->171|203->172|254->223|256->225|258->227|258->227|259->228|259->228|259->228|260->229|260->229|260->229|260->229|260->229|261->230|261->230|262->231|262->231|263->232|264->233|264->233|264->233|264->233|265->234|266->235|266->235|267->236|267->236|267->236|268->237|268->237|268->237|269->238|269->238|269->238|270->239|270->239|270->239|270->239|270->239|272->241|272->241|274->243|274->243|275->244|275->244|275->244|277->246|284->253|284->253|286->255|286->255|286->255|287->256|287->256|287->256|288->257|288->257|288->257|288->257|288->257|289->258|289->258|290->259|290->259|291->260|292->261|292->261|292->261|292->261|293->262|294->263|294->263|295->264|295->264|296->265|296->265|296->265|298->267|305->274|305->274|307->276|307->276|307->276|308->277|310->279|310->279|311->280|315->284|315->284|316->285|316->285|316->285|316->285|316->285|317->286|317->286
                  -- GENERATED --
              */
          