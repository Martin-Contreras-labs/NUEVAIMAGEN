
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

object proveedorContactoModifica extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,tables.ContactoProveedor,tables.Proveedor,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
contacto: tables.ContactoProveedor, proveedor: tables.Proveedor):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "MODIFICAR CONTACTO DEL PROVEEDOR", "")),format.raw/*9.71*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<table class="table table-sm table-bordered table-condensed table-fluid">
					<tr>
						<td colspan="2" style="text-align: left;">
							<input type="hidden" name="id_proveedor" value=""""),_display_(/*15.57*/proveedor/*15.66*/.getId()),format.raw/*15.74*/("""">
							<h5>PROVEEDOR: """),_display_(/*16.24*/proveedor/*16.33*/.getNickName()),format.raw/*16.47*/("""</h5>
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
							<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/proveedorModifica/"""),_display_(/*80.142*/proveedor/*80.151*/.getId()),format.raw/*80.159*/("""';">
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
            """),format.raw/*107.13*/("""url: "/modificaContactoProveedorPorCampoAjax/",
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

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,contacto:tables.ContactoProveedor,proveedor:tables.Proveedor): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,contacto,proveedor)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.ContactoProveedor,tables.Proveedor) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,contacto,proveedor) => apply(mapDiccionario,mapPermiso,userMnu,contacto,proveedor)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuTablas/proveedorContactoModifica.scala.html
                  HASH: 89ea9339a9eedf569aa41cd3c6d0aa1885607466
                  MATRIX: 1065->1|1320->163|1353->171|1369->179|1408->181|1436->184|1504->232|1532->234|1608->285|1695->352|1725->355|2044->647|2062->656|2091->664|2144->690|2162->699|2197->713|2544->1033|2561->1041|2594->1053|2977->1409|2994->1417|3026->1428|3361->1736|3378->1744|3409->1754|3746->2064|3763->2072|3795->2083|4123->2384|4140->2392|4171->2402|4552->2755|4571->2764|4601->2772|4701->2842|4732->2846|4812->2899|4829->2907|4858->2915|4919->2948|4948->2949|4980->2954|5073->3020|5101->3021|5173->3064|5203->3065|5234->3068|5463->3268|5493->3269|5535->3282|5814->3532|5844->3533|5881->3542|5932->3564|5962->3565|6001->3575|6067->3612|6097->3613|6137->3624|6196->3654|6226->3655|6265->3666|6295->3667|6331->3675|6360->3676|6397->3685|6427->3686|6459->3690|6488->3691|6521->3696
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|46->15|46->15|46->15|47->16|47->16|47->16|58->27|58->27|58->27|70->39|70->39|70->39|81->50|81->50|81->50|92->61|92->61|92->61|103->72|103->72|103->72|111->80|111->80|111->80|119->88|123->92|125->94|125->94|125->94|127->96|127->96|128->97|129->98|129->98|131->100|131->100|132->101|137->106|137->106|138->107|145->114|145->114|146->115|146->115|146->115|147->116|147->116|147->116|148->117|149->118|149->118|150->119|150->119|151->120|151->120|152->121|152->121|153->122|153->122|156->125
                  -- GENERATED --
              */
          