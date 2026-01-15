
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

object proveedorAgrega extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Regiones],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listRegiones: List[tables.Regiones]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "AGREGAR NUEVO PROVEEDOR", "")),format.raw/*9.62*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<form id="form" method="post" action="/proveedorGraba/">
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
									onkeydown="return sinReservados(window.event)"
									maxlength="100"
									onchange="value = value.trim();">
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">NOMBRE CORTO: </td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									id="nickName"
									name="nickName"
									onkeydown="return sinReservados(window.event)"
									autocomplete="off"
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
							<td style="text-align:left;">"""),_display_(/*59.38*/mapDiccionario/*59.52*/.get("Region")),format.raw/*59.66*/(""": </td>
							<td style="text-align:left;">
								<select class="custom-select" 
									name="cod_region" 
									onchange="actualizaComunas(value);">
									"""),_display_(/*64.11*/for(lista <- listRegiones) yield /*64.37*/{_display_(Seq[Any](format.raw/*64.38*/("""
										"""),format.raw/*65.11*/("""<option value=""""),_display_(/*65.27*/lista/*65.32*/.codigo),format.raw/*65.39*/("""">"""),_display_(/*65.42*/lista/*65.47*/.nombre),format.raw/*65.54*/("""</option>
									""")))}),format.raw/*66.11*/("""
								"""),format.raw/*67.9*/("""</select>
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">"""),_display_(/*71.38*/mapDiccionario/*71.52*/.get("Comuna")),format.raw/*71.66*/(""": </td>
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
							<td style="text-align:left;">Ciudad: </td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="ciudad"
									autocomplete="off"
									maxlength="30"
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
								<input type="submit"  value="GRABAR PROVEEDOR" class="btn btn-success btn-sm rounded-pill btn-block">
							</div>
							<div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
								<input type="button" value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/proveedorMantencion/';">
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

""")))}),format.raw/*127.2*/("""



"""),format.raw/*131.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*133.31*/("""{"""),format.raw/*133.32*/("""
		  """),format.raw/*134.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*135.2*/("""}"""),format.raw/*135.3*/(""");
	
	const actualizaComunas = (cod_region) => """),format.raw/*137.43*/("""{"""),format.raw/*137.44*/("""
		"""),format.raw/*138.3*/("""var formData = new FormData();
		formData.append('cod_region', cod_region);
	  	$.ajax("""),format.raw/*140.12*/("""{"""),format.raw/*140.13*/("""
            """),format.raw/*141.13*/("""url: "/selComuna1Ajax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(data)"""),format.raw/*148.31*/("""{"""),format.raw/*148.32*/("""
	     		"""),format.raw/*149.9*/("""if(data=="error")"""),format.raw/*149.26*/("""{"""),format.raw/*149.27*/("""
	     			"""),format.raw/*150.10*/("""alertify.alert(msgError, function () """),format.raw/*150.47*/("""{"""),format.raw/*150.48*/("""
		     			"""),format.raw/*151.11*/("""location.href = "/";
		     		"""),format.raw/*152.10*/("""}"""),format.raw/*152.11*/(""");
	     		"""),format.raw/*153.9*/("""}"""),format.raw/*153.10*/("""else"""),format.raw/*153.14*/("""{"""),format.raw/*153.15*/("""
	     			"""),format.raw/*154.10*/("""data = data.replace(/&lt;/g,"<");
					data = data.replace(/&gt;/g,">");
					data = data.replace(/&#x27;/g,"\"");
	     			document.getElementById('selectComuna').innerHTML = data;
	     		"""),format.raw/*158.9*/("""}"""),format.raw/*158.10*/("""
	     	"""),format.raw/*159.8*/("""}"""),format.raw/*159.9*/("""
        """),format.raw/*160.9*/("""}"""),format.raw/*160.10*/(""");
	"""),format.raw/*161.2*/("""}"""),format.raw/*161.3*/("""
	
	"""),format.raw/*163.2*/("""const verificaNick = (nickName) => """),format.raw/*163.37*/("""{"""),format.raw/*163.38*/("""
		"""),format.raw/*164.3*/("""var formData = new FormData();
	  	formData.append('nickName',nickName);
        $.ajax("""),format.raw/*166.16*/("""{"""),format.raw/*166.17*/("""
            """),format.raw/*167.13*/("""url: "/verificaNickProveedorAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*174.36*/("""{"""),format.raw/*174.37*/("""
	     		"""),format.raw/*175.9*/("""if(respuesta=="existe")"""),format.raw/*175.32*/("""{"""),format.raw/*175.33*/("""
	     			"""),format.raw/*176.10*/("""alertify.alert('El nombre corto (nickName) del proveedor ya existe, intente con otro', function () """),format.raw/*176.109*/("""{"""),format.raw/*176.110*/("""
	     				"""),format.raw/*177.11*/("""$("#nickName").val("");
		     		"""),format.raw/*178.10*/("""}"""),format.raw/*178.11*/(""");
	     		"""),format.raw/*179.9*/("""}"""),format.raw/*179.10*/("""
	     		"""),format.raw/*180.9*/("""if(respuesta=="error")"""),format.raw/*180.31*/("""{"""),format.raw/*180.32*/("""
	     			"""),format.raw/*181.10*/("""alertify.alert(msgError, function () """),format.raw/*181.47*/("""{"""),format.raw/*181.48*/("""
		     			"""),format.raw/*182.11*/("""location.href = "/";
		     		"""),format.raw/*183.10*/("""}"""),format.raw/*183.11*/(""");
	     		"""),format.raw/*184.9*/("""}"""),format.raw/*184.10*/("""
	     	"""),format.raw/*185.8*/("""}"""),format.raw/*185.9*/("""
        """),format.raw/*186.9*/("""}"""),format.raw/*186.10*/(""");
	"""),format.raw/*187.2*/("""}"""),format.raw/*187.3*/("""

"""),format.raw/*189.1*/("""</script>

		
	
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
                  SOURCE: app/viewsMnuTablas/proveedorAgrega.scala.html
                  HASH: ccc1a0fe586edf747bedbfc23e8217ed6b5b6765
                  MATRIX: 1035->1|1262->135|1295->143|1311->151|1350->153|1378->156|1446->204|1474->206|1550->257|1628->315|1658->318|1972->605|1995->619|2027->630|3417->1993|3440->2007|3475->2021|3670->2189|3712->2215|3751->2216|3790->2227|3833->2243|3847->2248|3875->2255|3905->2258|3919->2263|3947->2270|3998->2290|4034->2299|4144->2382|4167->2396|4202->2410|5944->4121|5976->4125|6068->4188|6098->4189|6131->4194|6225->4260|6254->4261|6330->4308|6360->4309|6391->4312|6507->4399|6537->4400|6579->4413|6830->4635|6860->4636|6897->4645|6943->4662|6973->4663|7012->4673|7078->4710|7108->4711|7148->4722|7207->4752|7237->4753|7276->4764|7306->4765|7339->4769|7369->4770|7408->4780|7626->4970|7656->4971|7692->4979|7721->4980|7758->4989|7788->4990|7820->4994|7849->4995|7881->4999|7945->5034|7975->5035|8006->5038|8123->5126|8153->5127|8195->5140|8462->5378|8492->5379|8529->5388|8581->5411|8611->5412|8650->5422|8779->5521|8810->5522|8850->5533|8912->5566|8942->5567|8981->5578|9011->5579|9048->5588|9099->5610|9129->5611|9168->5621|9234->5658|9264->5659|9304->5670|9363->5700|9393->5701|9432->5712|9462->5713|9498->5721|9527->5722|9564->5731|9594->5732|9626->5736|9655->5737|9685->5739
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|46->15|46->15|46->15|90->59|90->59|90->59|95->64|95->64|95->64|96->65|96->65|96->65|96->65|96->65|96->65|96->65|97->66|98->67|102->71|102->71|102->71|158->127|162->131|164->133|164->133|165->134|166->135|166->135|168->137|168->137|169->138|171->140|171->140|172->141|179->148|179->148|180->149|180->149|180->149|181->150|181->150|181->150|182->151|183->152|183->152|184->153|184->153|184->153|184->153|185->154|189->158|189->158|190->159|190->159|191->160|191->160|192->161|192->161|194->163|194->163|194->163|195->164|197->166|197->166|198->167|205->174|205->174|206->175|206->175|206->175|207->176|207->176|207->176|208->177|209->178|209->178|210->179|210->179|211->180|211->180|211->180|212->181|212->181|212->181|213->182|214->183|214->183|215->184|215->184|216->185|216->185|217->186|217->186|218->187|218->187|220->189
                  -- GENERATED --
              */
          