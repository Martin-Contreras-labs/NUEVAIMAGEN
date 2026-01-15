
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

object operadorModifica extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Usuario],tables.OperadorServicio,play.twirl.api.HtmlFormat.Appendable] {

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
									value=""""),_display_(/*34.18*/operador/*34.26*/.getNombre()),format.raw/*34.38*/(""""
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
									value=""""),_display_(/*46.18*/operador/*46.26*/.getCargo()),format.raw/*46.37*/(""""
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
						<input type="hidden" name="id_userAdam" value=""""),_display_(/*80.55*/operador/*80.63*/.getId_userAdam()),format.raw/*80.80*/("""">
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
""")))}),format.raw/*96.2*/("""




"""),format.raw/*101.1*/("""<script type="text/javascript">

	

	$(document).ready(function() """),format.raw/*105.31*/("""{"""),format.raw/*105.32*/("""
		
		
		"""),format.raw/*108.3*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*109.2*/("""}"""),format.raw/*109.3*/(""");
	
	const formateaPrecio = (id_moneda) => """),format.raw/*111.40*/("""{"""),format.raw/*111.41*/("""
		"""),format.raw/*112.3*/("""$('#precio').val(formatStandar($('#precio').val(),mapMonDec[id_moneda]));
	"""),format.raw/*113.2*/("""}"""),format.raw/*113.3*/("""
	
	"""),format.raw/*115.2*/("""const verificaRut = (rut) => """),format.raw/*115.31*/("""{"""),format.raw/*115.32*/("""
		"""),format.raw/*116.3*/("""var formData = new FormData();
	  	formData.append('rut',rut);
        $.ajax("""),format.raw/*118.16*/("""{"""),format.raw/*118.17*/("""
            """),format.raw/*119.13*/("""url: "/verificaRutOperadorAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*126.36*/("""{"""),format.raw/*126.37*/("""
	     		"""),format.raw/*127.9*/("""if(respuesta=="existe")"""),format.raw/*127.32*/("""{"""),format.raw/*127.33*/("""
	     			"""),format.raw/*128.10*/("""alertify.alert("El RUT ya existe como operador o autorizador, intente con otro", function () """),format.raw/*128.103*/("""{"""),format.raw/*128.104*/("""
	     				"""),format.raw/*129.11*/("""$("#rut").val("");
		     		"""),format.raw/*130.10*/("""}"""),format.raw/*130.11*/(""");
	     		"""),format.raw/*131.9*/("""}"""),format.raw/*131.10*/("""
	     		"""),format.raw/*132.9*/("""if(respuesta=="error")"""),format.raw/*132.31*/("""{"""),format.raw/*132.32*/("""
	     			"""),format.raw/*133.10*/("""alertify.alert(msgError, function () """),format.raw/*133.47*/("""{"""),format.raw/*133.48*/("""
		     			"""),format.raw/*134.11*/("""location.href = "/";
		     		"""),format.raw/*135.10*/("""}"""),format.raw/*135.11*/(""");
	     		"""),format.raw/*136.9*/("""}"""),format.raw/*136.10*/("""
	     	"""),format.raw/*137.8*/("""}"""),format.raw/*137.9*/("""
        """),format.raw/*138.9*/("""}"""),format.raw/*138.10*/(""");
	"""),format.raw/*139.2*/("""}"""),format.raw/*139.3*/("""
	
	

"""),format.raw/*143.1*/("""</script>


		
		
	
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
                  SOURCE: app/viewsMnuOdo/operadorModifica.scala.html
                  HASH: 674fd6e3e63228fe653e61ab60bdbc57c6745a40
                  MATRIX: 1056->1|1317->169|1350->177|1366->185|1405->187|1433->190|1501->238|1529->240|1605->291|1678->344|1708->347|2031->643|2054->657|2086->668|2224->779|2241->787|2270->795|2408->906|2425->914|2455->923|2949->1390|2966->1398|2999->1410|3408->1792|3425->1800|3457->1811|3849->2176|3866->2184|3898->2195|4235->2505|4252->2513|4283->2523|4627->2840|4644->2848|4676->2859|4794->2950|4811->2958|4849->2975|5409->3505|5442->3510|5537->3576|5567->3577|5604->3586|5698->3652|5727->3653|5800->3697|5830->3698|5861->3701|5964->3776|5993->3777|6025->3781|6083->3810|6113->3811|6144->3814|6251->3892|6281->3893|6323->3906|6588->4142|6618->4143|6655->4152|6707->4175|6737->4176|6776->4186|6899->4279|6930->4280|6970->4291|7027->4319|7057->4320|7096->4331|7126->4332|7163->4341|7214->4363|7244->4364|7283->4374|7349->4411|7379->4412|7419->4423|7478->4453|7508->4454|7547->4465|7577->4466|7613->4474|7642->4475|7679->4484|7709->4485|7741->4489|7770->4490|7804->4496
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|46->15|46->15|46->15|48->17|48->17|48->17|52->21|52->21|52->21|65->34|65->34|65->34|77->46|77->46|77->46|88->57|88->57|88->57|98->67|98->67|98->67|108->77|108->77|108->77|111->80|111->80|111->80|127->96|132->101|136->105|136->105|139->108|140->109|140->109|142->111|142->111|143->112|144->113|144->113|146->115|146->115|146->115|147->116|149->118|149->118|150->119|157->126|157->126|158->127|158->127|158->127|159->128|159->128|159->128|160->129|161->130|161->130|162->131|162->131|163->132|163->132|163->132|164->133|164->133|164->133|165->134|166->135|166->135|167->136|167->136|168->137|168->137|169->138|169->138|170->139|170->139|174->143
                  -- GENERATED --
              */
          