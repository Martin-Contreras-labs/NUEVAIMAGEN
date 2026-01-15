
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

object clienteContactoModifica extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,tables.ContactoCliente,tables.Cliente,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
contacto: tables.ContactoCliente, cliente: tables.Cliente):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "MODIFICAR CONTACTO DE CLIENTE/PROPIETARIO", "")),format.raw/*9.80*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<table class="table table-sm table-bordered table-condensed table-fluid">
					<tr>
						<td colspan="2" style="text-align: left;">
							<input type="hidden" name="id_cliente" value=""""),_display_(/*15.55*/cliente/*15.62*/.getId()),format.raw/*15.70*/("""">
							<h5>CLIENTE: """),_display_(/*16.22*/cliente/*16.29*/.getNickName()),format.raw/*16.43*/("""</h5>
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
							<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/clienteModifica/"""),_display_(/*80.140*/cliente/*80.147*/.getId()),format.raw/*80.155*/("""';">
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
            """),format.raw/*107.13*/("""url: "/modificaContactoClientePorCampoAjax/",
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

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,contacto:tables.ContactoCliente,cliente:tables.Cliente): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,contacto,cliente)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.ContactoCliente,tables.Cliente) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,contacto,cliente) => apply(mapDiccionario,mapPermiso,userMnu,contacto,cliente)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuTablas/clienteContactoModifica.scala.html
                  HASH: 3b4fc2cf4049719fb5a76d7a7714a7d2014c296b
                  MATRIX: 1059->1|1308->157|1341->165|1357->173|1396->175|1424->178|1492->226|1520->228|1596->279|1692->355|1722->358|2039->648|2055->655|2084->663|2135->687|2151->694|2186->708|2533->1028|2550->1036|2583->1048|2966->1404|2983->1412|3015->1423|3350->1731|3367->1739|3398->1749|3735->2059|3752->2067|3784->2078|4112->2379|4129->2387|4160->2397|4539->2748|4556->2755|4586->2763|4686->2833|4717->2837|4797->2890|4814->2898|4843->2906|4904->2939|4933->2940|4965->2945|5058->3011|5086->3012|5158->3055|5188->3056|5219->3059|5448->3259|5478->3260|5520->3273|5797->3521|5827->3522|5864->3531|5915->3553|5945->3554|5984->3564|6050->3601|6080->3602|6120->3613|6179->3643|6209->3644|6248->3655|6278->3656|6314->3664|6343->3665|6380->3674|6410->3675|6442->3679|6471->3680|6504->3685
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|46->15|46->15|46->15|47->16|47->16|47->16|58->27|58->27|58->27|70->39|70->39|70->39|81->50|81->50|81->50|92->61|92->61|92->61|103->72|103->72|103->72|111->80|111->80|111->80|119->88|123->92|125->94|125->94|125->94|127->96|127->96|128->97|129->98|129->98|131->100|131->100|132->101|137->106|137->106|138->107|145->114|145->114|146->115|146->115|146->115|147->116|147->116|147->116|148->117|149->118|149->118|150->119|150->119|151->120|151->120|152->121|152->121|153->122|153->122|156->125
                  -- GENERATED --
              */
          