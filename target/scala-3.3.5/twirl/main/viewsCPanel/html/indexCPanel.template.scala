
package viewsCPanel.html

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

object indexCPanel extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mensaje: String):play.twirl.api.HtmlFormat.Appendable = {
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
	                    	<h2>CPANEL - MADA</h2>
		                    
								<form onsubmit="return validarIngreso()">
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
											onchange='value= value.toUpperCase(); $("#ingUserName").val(value.toUpperCase())' 
											id="userName" name="userName" placeholder="Usuario" required>
									</div>
									<div class="input-group mb-3">
									  <div class="input-group-prepend">
									    <label class="input-group-text" for="userKey"  style="width:120px">Clave</label>
									  </div>
									  <input type="password" class="form-control" style="min-width:120px" 
											onchange='$("#ingUserKey").val(value)' name="userKey" placeholder="Clave" required>
									</div>
									<input type="hidden" id="gRecaptchaResponse" name="gRecaptchaResponse">
			                      <input type="submit"  value="INGRESAR" class="btn btn-success rounded-pill btn-block">
		                    </form>
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
		         	<br><br>
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


<form id="ingresar" action="/inicioCPanel/" method="POST">
	<input type="hidden" id="ingCaptcha" name="gRecaptchaResponse">
	<input type="hidden" id="ingEmpresa" name="empresa">
	<input type="hidden" id="ingUserName" name="userName">
	<input type="hidden" id="ingUserKey" name="userKey">
</form>


""")))}),format.raw/*102.2*/("""


"""),format.raw/*105.1*/("""<script src="https://www.google.com/recaptcha/enterprise.js?render=6Lcm3DUjAAAAAMEhoeru8yNe1j7XGIRV2vG2nsV9"></script>
<script>
	function validarIngreso() """),format.raw/*107.28*/("""{"""),format.raw/*107.29*/("""
	  """),format.raw/*108.4*/("""grecaptcha.enterprise.ready(async () => """),format.raw/*108.44*/("""{"""),format.raw/*108.45*/("""
	    """),format.raw/*109.6*/("""const token = await grecaptcha.enterprise.execute('6Lcm3DUjAAAAAMEhoeru8yNe1j7XGIRV2vG2nsV9', """),format.raw/*109.100*/("""{"""),format.raw/*109.101*/("""action: 'LOGIN'"""),format.raw/*109.116*/("""}"""),format.raw/*109.117*/(""").then(onSuccess, onError);
	  """),format.raw/*110.4*/("""}"""),format.raw/*110.5*/(""");
	  if($("#gRecaptchaResponse").val().length > 0)"""),format.raw/*111.49*/("""{"""),format.raw/*111.50*/("""
	  	"""),format.raw/*112.5*/("""return true;
	  """),format.raw/*113.4*/("""}"""),format.raw/*113.5*/("""else"""),format.raw/*113.9*/("""{"""),format.raw/*113.10*/("""
	  	"""),format.raw/*114.5*/("""return false;
	  """),format.raw/*115.4*/("""}"""),format.raw/*115.5*/("""
	"""),format.raw/*116.2*/("""}"""),format.raw/*116.3*/("""
	
	"""),format.raw/*118.2*/("""function onSuccess(action_token)"""),format.raw/*118.34*/("""{"""),format.raw/*118.35*/("""
			"""),format.raw/*119.4*/("""$("#ingCaptcha").val(action_token);
			$("#ingresar").submit();
	"""),format.raw/*121.2*/("""}"""),format.raw/*121.3*/("""
	
	"""),format.raw/*123.2*/("""function onError(reason) """),format.raw/*123.27*/("""{"""),format.raw/*123.28*/("""
	  """),format.raw/*124.4*/("""alert("problemas con el captcha, contactar a inqsol.cl");
      location.reload();
    """),format.raw/*126.5*/("""}"""),format.raw/*126.6*/("""
"""),format.raw/*127.1*/("""</script>

	<script>
            $(document).ready(function() """),format.raw/*130.42*/("""{"""),format.raw/*130.43*/("""
                """),format.raw/*131.17*/("""if(""""),_display_(/*131.22*/mensaje),format.raw/*131.29*/(""""!="0") alert(""""),_display_(/*131.45*/mensaje),format.raw/*131.52*/("""");                
            """),format.raw/*132.13*/("""}"""),format.raw/*132.14*/(""");
    </script>
"""))
      }
    }
  }

  def render(mensaje:String): play.twirl.api.HtmlFormat.Appendable = apply(mensaje)

  def f:((String) => play.twirl.api.HtmlFormat.Appendable) = (mensaje) => apply(mensaje)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsCPanel/indexCPanel.scala.html
                  HASH: bf73e0e6670b1df8652ccc9b9997edf6ada6824d
                  MATRIX: 957->1|1068->19|1095->21|1111->29|1150->31|1178->33|1219->48|1246->49|1277->54|1453->203|1481->204|1509->205|5659->4324|5690->4327|5874->4482|5904->4483|5936->4487|6005->4527|6035->4528|6069->4534|6193->4628|6224->4629|6269->4644|6300->4645|6359->4676|6388->4677|6468->4728|6498->4729|6531->4734|6575->4750|6604->4751|6636->4755|6666->4756|6699->4761|6744->4778|6773->4779|6803->4781|6832->4782|6864->4786|6925->4818|6955->4819|6987->4823|7080->4888|7109->4889|7141->4893|7195->4918|7225->4919|7257->4923|7372->5010|7401->5011|7430->5012|7521->5074|7551->5075|7597->5092|7630->5097|7659->5104|7703->5120|7732->5127|7793->5159|7823->5160
                  LINES: 28->1|33->2|34->3|34->3|34->3|36->5|37->6|37->6|38->7|43->12|43->12|44->13|133->102|136->105|138->107|138->107|139->108|139->108|139->108|140->109|140->109|140->109|140->109|140->109|141->110|141->110|142->111|142->111|143->112|144->113|144->113|144->113|144->113|145->114|146->115|146->115|147->116|147->116|149->118|149->118|149->118|150->119|152->121|152->121|154->123|154->123|154->123|155->124|157->126|157->126|158->127|161->130|161->130|162->131|162->131|162->131|162->131|162->131|163->132|163->132
                  -- GENERATED --
              */
          