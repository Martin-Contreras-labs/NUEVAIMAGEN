
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

object operadorNuevo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Usuario],play.twirl.api.HtmlFormat.Appendable] {

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
									onkeydown="return sinReservados(window.event)"
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
									value=""
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
						<input type="hidden" name="id_userAdam" value="0">
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
""")))}),format.raw/*93.2*/("""




"""),format.raw/*98.1*/("""<script type="text/javascript">

	

	$(document).ready(function() """),format.raw/*102.31*/("""{"""),format.raw/*102.32*/("""
		
		
		"""),format.raw/*105.3*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*106.2*/("""}"""),format.raw/*106.3*/(""");
	
	const formateaPrecio = (id_moneda) => """),format.raw/*108.40*/("""{"""),format.raw/*108.41*/("""
		"""),format.raw/*109.3*/("""$('#precio').val(formatStandar($('#precio').val(),mapMonDec[id_moneda]));
	"""),format.raw/*110.2*/("""}"""),format.raw/*110.3*/("""
	
	"""),format.raw/*112.2*/("""const verificaRut = (rut) => """),format.raw/*112.31*/("""{"""),format.raw/*112.32*/("""
		"""),format.raw/*113.3*/("""var formData = new FormData();
	  	formData.append('rut',rut);
        $.ajax("""),format.raw/*115.16*/("""{"""),format.raw/*115.17*/("""
            """),format.raw/*116.13*/("""url: "/verificaRutOperadorAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*123.36*/("""{"""),format.raw/*123.37*/("""
	     		"""),format.raw/*124.9*/("""if(respuesta=="existe")"""),format.raw/*124.32*/("""{"""),format.raw/*124.33*/("""
	     			"""),format.raw/*125.10*/("""alertify.alert("El RUT ya existe como operador o autorizador, intente con otro", function () """),format.raw/*125.103*/("""{"""),format.raw/*125.104*/("""
	     				"""),format.raw/*126.11*/("""$("#rut").val("");
		     		"""),format.raw/*127.10*/("""}"""),format.raw/*127.11*/(""");
	     		"""),format.raw/*128.9*/("""}"""),format.raw/*128.10*/("""
	     		"""),format.raw/*129.9*/("""if(respuesta=="error")"""),format.raw/*129.31*/("""{"""),format.raw/*129.32*/("""
	     			"""),format.raw/*130.10*/("""alertify.alert(msgError, function () """),format.raw/*130.47*/("""{"""),format.raw/*130.48*/("""
		     			"""),format.raw/*131.11*/("""location.href = "/";
		     		"""),format.raw/*132.10*/("""}"""),format.raw/*132.11*/(""");
	     		"""),format.raw/*133.9*/("""}"""),format.raw/*133.10*/("""
	     	"""),format.raw/*134.8*/("""}"""),format.raw/*134.9*/("""
        """),format.raw/*135.9*/("""}"""),format.raw/*135.10*/(""");
	"""),format.raw/*136.2*/("""}"""),format.raw/*136.3*/("""
	
	

"""),format.raw/*140.1*/("""</script>


		
		
	
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
                  SOURCE: app/viewsMnuOdo/operadorNuevo.scala.html
                  HASH: 70f26b12f80b8bfcffeb922a60c609789c1c5f0d
                  MATRIX: 1029->1|1255->134|1288->142|1304->150|1343->152|1371->155|1439->203|1467->205|1543->256|1626->319|1656->322|1978->617|2001->631|2033->642|4655->3234|4687->3239|4782->3305|4812->3306|4849->3315|4943->3381|4972->3382|5045->3426|5075->3427|5106->3430|5209->3505|5238->3506|5270->3510|5328->3539|5358->3540|5389->3543|5496->3621|5526->3622|5568->3635|5833->3871|5863->3872|5900->3881|5952->3904|5982->3905|6021->3915|6144->4008|6175->4009|6215->4020|6272->4048|6302->4049|6341->4060|6371->4061|6408->4070|6459->4092|6489->4093|6528->4103|6594->4140|6624->4141|6664->4152|6723->4182|6753->4183|6792->4194|6822->4195|6858->4203|6887->4204|6924->4213|6954->4214|6986->4218|7015->4219|7049->4225
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|46->15|46->15|46->15|124->93|129->98|133->102|133->102|136->105|137->106|137->106|139->108|139->108|140->109|141->110|141->110|143->112|143->112|143->112|144->113|146->115|146->115|147->116|154->123|154->123|155->124|155->124|155->124|156->125|156->125|156->125|157->126|158->127|158->127|159->128|159->128|160->129|160->129|160->129|161->130|161->130|161->130|162->131|163->132|163->132|164->133|164->133|165->134|165->134|166->135|166->135|167->136|167->136|171->140
                  -- GENERATED --
              */
          