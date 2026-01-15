
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

object fabricaContactoModifica extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,tables.ContactoFabrica,tables.Fabrica,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
contacto: tables.ContactoFabrica, fabrica: tables.Fabrica):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "MODIFICAR CONTACTO DEL FABRICANTE", "")),format.raw/*9.72*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<table class="table table-sm table-bordered table-condensed table-fluid">
					<tr>
						<td colspan="2" style="text-align: left;">
							<input type="hidden" name="id_fabrica" value=""""),_display_(/*15.55*/fabrica/*15.62*/.getId()),format.raw/*15.70*/("""">
							<h5>FABRICANTE: """),_display_(/*16.25*/fabrica/*16.32*/.getNickName()),format.raw/*16.46*/("""</h5>
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">NOMBRE: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="nombre"
								onkeydown="return sinReservados(window.event)"
								autocomplete="off"
								maxlength="100"
								value = """"),_display_(/*27.19*/contacto/*27.27*/.getNombre()),format.raw/*27.39*/(""""
								onchange="modificarContacto(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">CARGO: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="cargo"
								onkeydown="return sinReservados(window.event)"
								autocomplete="off"
								maxlength="100"
								value = """"),_display_(/*39.19*/contacto/*39.27*/.getCargo()),format.raw/*39.38*/(""""
								onchange="modificarContacto(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">TELEFONO FIJO: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="fijo"
								autocomplete="off"
								maxlength="100"
								value = """"),_display_(/*50.19*/contacto/*50.27*/.getFijo()),format.raw/*50.37*/(""""
								onchange="modificarContacto(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">TELEFONO MOVIL: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="movil"
								autocomplete="off"
								maxlength="100"
								value = """"),_display_(/*61.19*/contacto/*61.27*/.getMovil()),format.raw/*61.38*/(""""
								onchange="modificarContacto(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">E-MAIL: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="mail"
								autocomplete="off"
								maxlength="100"
								value = """"),_display_(/*72.19*/contacto/*72.27*/.getMail()),format.raw/*72.37*/(""""
								onchange="modificarContacto(id)">
						</td>
					</tr>
				</table>
				<div class="noprint">
					<div class="row justify-content-md-center" >
						<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
							<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/fabricaModifica/"""),_display_(/*80.140*/fabrica/*80.147*/.getId()),format.raw/*80.155*/("""';">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

""")))}),format.raw/*88.2*/("""



"""),format.raw/*92.1*/("""<script type="text/javascript">

	let id_contacto = """),_display_(/*94.21*/contacto/*94.29*/.getId()),format.raw/*94.37*/(""";

	$(document).ready(function() """),format.raw/*96.31*/("""{"""),format.raw/*96.32*/("""
		  """),format.raw/*97.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*98.2*/("""}"""),format.raw/*98.3*/(""");
	
	const modificarContacto = (campo) => """),format.raw/*100.39*/("""{"""),format.raw/*100.40*/("""
		"""),format.raw/*101.3*/("""let valor = $("#"+campo).val();
		var formData = new FormData();
	  	formData.append('campo',campo);
	  	formData.append('id_contacto',id_contacto);
	  	formData.append('valor',valor);
        $.ajax("""),format.raw/*106.16*/("""{"""),format.raw/*106.17*/("""
            """),format.raw/*107.13*/("""url: "/modificaContactoFabricaPorCampoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*114.36*/("""{"""),format.raw/*114.37*/("""
	     		"""),format.raw/*115.9*/("""if(respuesta=="error")"""),format.raw/*115.31*/("""{"""),format.raw/*115.32*/("""
	     			"""),format.raw/*116.10*/("""alertify.alert(msgError, function () """),format.raw/*116.47*/("""{"""),format.raw/*116.48*/("""
		     			"""),format.raw/*117.11*/("""location.href = "/";
		     		"""),format.raw/*118.10*/("""}"""),format.raw/*118.11*/(""");
	     		"""),format.raw/*119.9*/("""}"""),format.raw/*119.10*/("""
	     	"""),format.raw/*120.8*/("""}"""),format.raw/*120.9*/("""
        """),format.raw/*121.9*/("""}"""),format.raw/*121.10*/(""");
	"""),format.raw/*122.2*/("""}"""),format.raw/*122.3*/("""
	
	
"""),format.raw/*125.1*/("""</script>

		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,contacto:tables.ContactoFabrica,fabrica:tables.Fabrica): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,contacto,fabrica)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.ContactoFabrica,tables.Fabrica) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,contacto,fabrica) => apply(mapDiccionario,mapPermiso,userMnu,contacto,fabrica)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuTablas/fabricaContactoModifica.scala.html
                  HASH: 2c8da1cd50d1923a2689245d78916ea969adde99
                  MATRIX: 1059->1|1308->157|1341->165|1357->173|1396->175|1424->178|1492->226|1520->228|1596->279|1684->347|1714->350|2031->640|2047->647|2076->655|2130->682|2146->689|2181->703|2528->1023|2545->1031|2578->1043|2961->1399|2978->1407|3010->1418|3345->1726|3362->1734|3393->1744|3730->2054|3747->2062|3779->2073|4107->2374|4124->2382|4155->2392|4534->2743|4551->2750|4581->2758|4681->2828|4712->2832|4792->2885|4809->2893|4838->2901|4899->2934|4928->2935|4960->2940|5053->3006|5081->3007|5153->3050|5183->3051|5214->3054|5443->3254|5473->3255|5515->3268|5792->3516|5822->3517|5859->3526|5910->3548|5940->3549|5979->3559|6045->3596|6075->3597|6115->3608|6174->3638|6204->3639|6243->3650|6273->3651|6309->3659|6338->3660|6375->3669|6405->3670|6437->3674|6466->3675|6499->3680
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|46->15|46->15|46->15|47->16|47->16|47->16|58->27|58->27|58->27|70->39|70->39|70->39|81->50|81->50|81->50|92->61|92->61|92->61|103->72|103->72|103->72|111->80|111->80|111->80|119->88|123->92|125->94|125->94|125->94|127->96|127->96|128->97|129->98|129->98|131->100|131->100|132->101|137->106|137->106|138->107|145->114|145->114|146->115|146->115|146->115|147->116|147->116|147->116|148->117|149->118|149->118|150->119|150->119|151->120|151->120|152->121|152->121|153->122|153->122|156->125
                  -- GENERATED --
              */
          