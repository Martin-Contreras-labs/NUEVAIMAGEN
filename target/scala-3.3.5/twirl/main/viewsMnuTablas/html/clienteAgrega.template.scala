
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

object clienteAgrega extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Regiones],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listRegiones: List[tables.Regiones]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "AGREGAR NUEVO CLIENTE/PROPIETARIO", "")),format.raw/*9.72*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<form id="form" method="post" action="/clienteGraba/">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<tr>
							<td style="text-align:left;">"""),_display_(/*15.38*/mapDiccionario/*15.52*/.get("RUT")),format.raw/*15.63*/(""": </td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="rut"
									autocomplete="off"
									maxlength="50"
									onchange="value = value.trim();">
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">NOMBRE LARGO: </td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="nombre"
									autocomplete="off"
									maxlength="100"
									onchange="value = value.trim();">
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">NOMBRE CORTO (*): </td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									id="nickName"
									name="nickName"
									autocomplete="off"
									onkeydown="return sinReservados(window.event)"
									maxlength="50"
									required
									onchange="value = value.trim();verificaNick(value);">
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
							<td style="text-align:left;">"""),_display_(/*58.38*/mapDiccionario/*58.52*/.get("Region")),format.raw/*58.66*/(""": </td>
							<td style="text-align:left;">
								<select class="custom-select" 
									name="cod_region" 
									onchange="actualizaComunas(value);">
									"""),_display_(/*63.11*/for(lista <- listRegiones) yield /*63.37*/{_display_(Seq[Any](format.raw/*63.38*/("""
										"""),format.raw/*64.11*/("""<option value=""""),_display_(/*64.27*/lista/*64.32*/.codigo),format.raw/*64.39*/("""">"""),_display_(/*64.42*/lista/*64.47*/.nombre),format.raw/*64.54*/("""</option>
									""")))}),format.raw/*65.11*/("""
								"""),format.raw/*66.9*/("""</select>
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">"""),_display_(/*70.38*/mapDiccionario/*70.52*/.get("Comuna")),format.raw/*70.66*/(""": </td>
							<td style="text-align:left;">
								<div id="selectComuna">
									<select class="custom-select" 
										name="cod_comuna" >
										<option value="0"> </option>
									</select>
								</div>
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">Ciudad y/o Pais: </td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="ciudad"
									autocomplete="off"
									maxlength="30"
									onchange="value = value.trim();">
							</td>
						</tr>
						<tr>
						<td style="text-align:left;">GIRO: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								name="giro"
								autocomplete="off"
								maxlength="100"
								onchange="value = value.trim();">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">E-MAIL: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								name="mailFactura"
								autocomplete="off"
								maxlength="50"
								onchange="value = value.trim();">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">TELEFONO: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								name="fonoContacto"
								autocomplete="off"
								maxlength="50"
								onchange="value = value.trim();">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">CONTACTO FACTURA: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								name="contactoFactura"
								autocomplete="off"
								maxlength="50"
								onchange="value = value.trim();">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">"""),_display_(/*131.37*/mapDiccionario/*131.51*/.get("RUT")),format.raw/*131.62*/(""" """),format.raw/*131.63*/("""REPRES 1: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								name="rutRepresentante1"
								autocomplete="off"
								maxlength="50"
								onchange="value = value.trim();">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">NOMBRE REPRES 1: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								name="nombreRepresentante1"
								autocomplete="off"
								maxlength="100"
								onchange="value = value.trim();">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">"""),_display_(/*151.37*/mapDiccionario/*151.51*/.get("RUT")),format.raw/*151.62*/(""" """),format.raw/*151.63*/("""REPRES 2: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								name="rutRepresentante2"
								autocomplete="off"
								maxlength="50"
								onchange="value = value.trim();">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">NOMBRE REPRES 2: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								name="nombreRepresentante2"
								autocomplete="off"
								maxlength="100"
								onchange="value = value.trim();">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">FORMA DE PAGO: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								name="formaDePago"
								autocomplete="off"
								maxlength="100"
								onchange="value = value.trim();">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">ESPECIALIDAD: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								name="especialidad"
								autocomplete="off"
								maxlength="100"
								onchange="value = value.trim();">
						</td>
					</tr>
					</table>
					<div class="noprint">
						<div class="row justify-content-md-center" >
							<div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
								<input type="submit"  value="GRABAR CLIENTE" class="btn btn-success btn-sm rounded-pill btn-block">
							</div>
							<div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
								<input type="button" value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/clienteMantencion/';">
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

""")))}),format.raw/*206.2*/("""



"""),format.raw/*210.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*212.31*/("""{"""),format.raw/*212.32*/("""
		  """),format.raw/*213.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*214.2*/("""}"""),format.raw/*214.3*/(""");
	
	const actualizaComunas = (cod_region) => """),format.raw/*216.43*/("""{"""),format.raw/*216.44*/("""
		"""),format.raw/*217.3*/("""var formData = new FormData();
		formData.append('cod_region', cod_region);
	  	$.ajax("""),format.raw/*219.12*/("""{"""),format.raw/*219.13*/("""
            """),format.raw/*220.13*/("""url: "/selComuna1Ajax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(data)"""),format.raw/*227.31*/("""{"""),format.raw/*227.32*/("""
	     		"""),format.raw/*228.9*/("""if(data=="error")"""),format.raw/*228.26*/("""{"""),format.raw/*228.27*/("""
	     			"""),format.raw/*229.10*/("""alertify.alert(msgError, function () """),format.raw/*229.47*/("""{"""),format.raw/*229.48*/("""
		     			"""),format.raw/*230.11*/("""location.href = "/";
		     		"""),format.raw/*231.10*/("""}"""),format.raw/*231.11*/(""");
	     		"""),format.raw/*232.9*/("""}"""),format.raw/*232.10*/("""else"""),format.raw/*232.14*/("""{"""),format.raw/*232.15*/("""
	     			"""),format.raw/*233.10*/("""data = data.replace(/&lt;/g,"<");
					data = data.replace(/&gt;/g,">");
					data = data.replace(/&#x27;/g,"\"");
	     			document.getElementById('selectComuna').innerHTML = data;
	     		"""),format.raw/*237.9*/("""}"""),format.raw/*237.10*/("""
	     	"""),format.raw/*238.8*/("""}"""),format.raw/*238.9*/("""
        """),format.raw/*239.9*/("""}"""),format.raw/*239.10*/(""");
	"""),format.raw/*240.2*/("""}"""),format.raw/*240.3*/("""
	
	"""),format.raw/*242.2*/("""const verificaNick = (nickName) => """),format.raw/*242.37*/("""{"""),format.raw/*242.38*/("""
		"""),format.raw/*243.3*/("""var formData = new FormData();
	  	formData.append('nickName',nickName);
        $.ajax("""),format.raw/*245.16*/("""{"""),format.raw/*245.17*/("""
            """),format.raw/*246.13*/("""url: "/verificaNickClienteAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*253.36*/("""{"""),format.raw/*253.37*/("""
	     		"""),format.raw/*254.9*/("""if(respuesta=="existe")"""),format.raw/*254.32*/("""{"""),format.raw/*254.33*/("""
	     			"""),format.raw/*255.10*/("""alertify.alert('El nombre corto (nickName) del cliente ya existe, intente con otro', function () """),format.raw/*255.107*/("""{"""),format.raw/*255.108*/("""
	     				"""),format.raw/*256.11*/("""$("#nickName").val("");
		     		"""),format.raw/*257.10*/("""}"""),format.raw/*257.11*/(""");
	     		"""),format.raw/*258.9*/("""}"""),format.raw/*258.10*/("""
	     		"""),format.raw/*259.9*/("""if(respuesta=="error")"""),format.raw/*259.31*/("""{"""),format.raw/*259.32*/("""
	     			"""),format.raw/*260.10*/("""alertify.alert(msgError, function () """),format.raw/*260.47*/("""{"""),format.raw/*260.48*/("""
		     			"""),format.raw/*261.11*/("""location.href = "/";
		     		"""),format.raw/*262.10*/("""}"""),format.raw/*262.11*/(""");
	     		"""),format.raw/*263.9*/("""}"""),format.raw/*263.10*/("""
	     	"""),format.raw/*264.8*/("""}"""),format.raw/*264.9*/("""
        """),format.raw/*265.9*/("""}"""),format.raw/*265.10*/(""");
	"""),format.raw/*266.2*/("""}"""),format.raw/*266.3*/("""

"""),format.raw/*268.1*/("""</script>

		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listRegiones:List[tables.Regiones]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listRegiones)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Regiones]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listRegiones) => apply(mapDiccionario,mapPermiso,userMnu,listRegiones)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuTablas/clienteAgrega.scala.html
                  HASH: 03d864e7302bcb0d4c325a2f99e6fdc0ecedf107
                  MATRIX: 1033->1|1260->135|1293->143|1309->151|1348->153|1376->156|1444->204|1472->206|1548->257|1636->325|1666->328|1978->613|2001->627|2033->638|3371->1949|3394->1963|3429->1977|3624->2145|3666->2171|3705->2172|3744->2183|3787->2199|3801->2204|3829->2211|3859->2214|3873->2219|3901->2226|3952->2246|3988->2255|4098->2338|4121->2352|4156->2366|5944->4126|5968->4140|6001->4151|6031->4152|6665->4758|6689->4772|6722->4783|6752->4784|8491->6492|8523->6496|8615->6559|8645->6560|8678->6565|8772->6631|8801->6632|8877->6679|8907->6680|8938->6683|9054->6770|9084->6771|9126->6784|9377->7006|9407->7007|9444->7016|9490->7033|9520->7034|9559->7044|9625->7081|9655->7082|9695->7093|9754->7123|9784->7124|9823->7135|9853->7136|9886->7140|9916->7141|9955->7151|10173->7341|10203->7342|10239->7350|10268->7351|10305->7360|10335->7361|10367->7365|10396->7366|10428->7370|10492->7405|10522->7406|10553->7409|10670->7497|10700->7498|10742->7511|11007->7747|11037->7748|11074->7757|11126->7780|11156->7781|11195->7791|11322->7888|11353->7889|11393->7900|11455->7933|11485->7934|11524->7945|11554->7946|11591->7955|11642->7977|11672->7978|11711->7988|11777->8025|11807->8026|11847->8037|11906->8067|11936->8068|11975->8079|12005->8080|12041->8088|12070->8089|12107->8098|12137->8099|12169->8103|12198->8104|12228->8106
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|46->15|46->15|46->15|89->58|89->58|89->58|94->63|94->63|94->63|95->64|95->64|95->64|95->64|95->64|95->64|95->64|96->65|97->66|101->70|101->70|101->70|162->131|162->131|162->131|162->131|182->151|182->151|182->151|182->151|237->206|241->210|243->212|243->212|244->213|245->214|245->214|247->216|247->216|248->217|250->219|250->219|251->220|258->227|258->227|259->228|259->228|259->228|260->229|260->229|260->229|261->230|262->231|262->231|263->232|263->232|263->232|263->232|264->233|268->237|268->237|269->238|269->238|270->239|270->239|271->240|271->240|273->242|273->242|273->242|274->243|276->245|276->245|277->246|284->253|284->253|285->254|285->254|285->254|286->255|286->255|286->255|287->256|288->257|288->257|289->258|289->258|290->259|290->259|290->259|291->260|291->260|291->260|292->261|293->262|293->262|294->263|294->263|295->264|295->264|296->265|296->265|297->266|297->266|299->268
                  -- GENERATED --
              */
          