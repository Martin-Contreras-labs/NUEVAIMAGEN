
package viewsMnuBodegas.html

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

object bodegaContactoModifica extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,tables.ContactoBodegaEmpresa,tables.BodegaEmpresa,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
contacto: tables.ContactoBodegaEmpresa, bodega: tables.BodegaEmpresa):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "MODIFICAR CONTACTO DE "+mapDiccionario.get("BODEGA")+"/PROYECTO", "")),format.raw/*9.102*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<table class="table table-sm table-bordered table-condensed table-fluid">
					<tr>
						<td colspan="2" style="text-align: left;">
							<input type="hidden" name="id_bodega" value=""""),_display_(/*15.54*/bodega/*15.60*/.getId()),format.raw/*15.68*/("""">
							<h5>"""),_display_(/*16.13*/mapDiccionario/*16.27*/.get("BODEGA")),format.raw/*16.41*/("""/PROYECTO: """),_display_(/*16.53*/bodega/*16.59*/.getNombre()),format.raw/*16.71*/("""</h5>
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">NOMBRE: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="nombre"
								autocomplete="off"
								maxlength="100"
								onkeydown="return sinReservados(window.event)"
								value = """"),_display_(/*27.19*/contacto/*27.27*/.getNombre()),format.raw/*27.39*/(""""
								onchange="modificarContacto(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">CARGO: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="cargo"
								autocomplete="off"
								onkeydown="return sinReservados(window.event)"
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
							<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/bodegaModificaContacto/"""),_display_(/*80.147*/bodega/*80.153*/.getId()),format.raw/*80.161*/("""';">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

""")))}),format.raw/*88.2*/("""



"""),format.raw/*92.1*/("""<script type="text/javascript">

	let id_contacto = """"),_display_(/*94.22*/contacto/*94.30*/.getId()),format.raw/*94.38*/("""";

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
            """),format.raw/*107.13*/("""url: "/modificaContactoBodegaPorCampoAjax/",
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

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,contacto:tables.ContactoBodegaEmpresa,bodega:tables.BodegaEmpresa): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,contacto,bodega)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.ContactoBodegaEmpresa,tables.BodegaEmpresa) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,contacto,bodega) => apply(mapDiccionario,mapPermiso,userMnu,contacto,bodega)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuBodegas/bodegaContactoModifica.scala.html
                  HASH: 26f96a621c48315743cf9feaf356f76bf5cd452d
                  MATRIX: 1071->1|1331->168|1364->176|1380->184|1419->186|1447->189|1515->237|1543->239|1619->290|1738->388|1768->391|2084->680|2099->686|2128->694|2170->709|2193->723|2228->737|2267->749|2282->755|2315->767|2662->1087|2679->1095|2712->1107|3095->1463|3112->1471|3144->1482|3479->1790|3496->1798|3527->1808|3864->2118|3881->2126|3913->2137|4241->2438|4258->2446|4289->2456|4675->2814|4691->2820|4721->2828|4821->2898|4852->2902|4933->2956|4950->2964|4979->2972|5041->3006|5070->3007|5102->3012|5195->3078|5223->3079|5295->3122|5325->3123|5356->3126|5585->3326|5615->3327|5657->3340|5933->3587|5963->3588|6000->3597|6051->3619|6081->3620|6120->3630|6186->3667|6216->3668|6256->3679|6315->3709|6345->3710|6384->3721|6414->3722|6450->3730|6479->3731|6516->3740|6546->3741|6578->3745|6607->3746|6640->3751
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|46->15|46->15|46->15|47->16|47->16|47->16|47->16|47->16|47->16|58->27|58->27|58->27|70->39|70->39|70->39|81->50|81->50|81->50|92->61|92->61|92->61|103->72|103->72|103->72|111->80|111->80|111->80|119->88|123->92|125->94|125->94|125->94|127->96|127->96|128->97|129->98|129->98|131->100|131->100|132->101|137->106|137->106|138->107|145->114|145->114|146->115|146->115|146->115|147->116|147->116|147->116|148->117|149->118|149->118|150->119|150->119|151->120|151->120|152->121|152->121|153->122|153->122|156->125
                  -- GENERATED --
              */
          