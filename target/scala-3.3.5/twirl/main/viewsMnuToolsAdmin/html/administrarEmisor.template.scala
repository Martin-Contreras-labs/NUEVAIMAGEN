
package viewsMnuToolsAdmin.html

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

object administrarEmisor extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,tables.EmisorTributario,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
emisor: tables.EmisorTributario):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
		"""),format.raw/*7.3*/("""<div id="mostrarmostrar" style="display:none;">
			"""),_display_(/*8.5*/barraTitulo(mapDiccionario, "MANTENCION DE EMISOR TRIBUTARIO", "")),format.raw/*8.71*/("""
			"""),format.raw/*9.4*/("""<form action="/modificarEmisor/" method="POST">
				<div class="row  justify-content-md-center">
					<div class="col-xs-12 col-sm-10 col-md-10 col-lg-10">
						<table class="table table-sm table-hover table-bordered table-condensed table-fluid">
							<tr>
								<td style="text-align: left; width:30%">"""),_display_(/*14.50*/mapDiccionario/*14.64*/.get("RUT")),format.raw/*14.75*/(""":</td>
								<td style="text-align: left;">
									<input type="hidden" name="id" value=""""),_display_(/*16.49*/emisor/*16.55*/.id),format.raw/*16.58*/("""">
									<input type="text" class="form-control left"
										name="rut"
										autocomplete="off"
										value=""""),_display_(/*20.19*/emisor/*20.25*/.rut),format.raw/*20.29*/(""""
										maxlength="50">
							    </td>
							</tr>
							<tr>
								<td style="text-align: left;">RAZON SOCIAL:</td>
								<td style="text-align: left;">
								  	<input type="text" class="form-control left" 
										name="razonSocial" 
										value=""""),_display_(/*29.19*/emisor/*29.25*/.razonSocial),format.raw/*29.37*/(""""
								  		autocomplete="off" 
										maxlength="100">
							    </td>
							</tr>
							<tr>
								<td style="text-align: left;">NOMBRE DE FANTASIA:</td>
								<td style="text-align: left;">
								  	<input type="text" class="form-control left"
										name="nombreFantasia" 
										value=""""),_display_(/*39.19*/emisor/*39.25*/.nombreFantasia),format.raw/*39.40*/(""""
								  		autocomplete="off"
										maxlength="100">
							    </td>
							</tr>
							<tr>
								<td style="text-align: left;">GIRO (nombre no codigo):</td>
								<td style="text-align: left;">
								  	<input type="text" class="form-control left"
										name="giro" 
										value=""""),_display_(/*49.19*/emisor/*49.25*/.giro),format.raw/*49.30*/(""""
								  		autocomplete="off"
								  		maxlength="80">
							    </td>
							</tr>
							<tr>
								<td style="text-align: left;">ACTIVIDAD ECONOMICA (codigo de SII):</td>
								<td style="text-align: left;">
								  	<input type="text" class="form-control left"
										name="actividadEconomica" 
										value=""""),_display_(/*59.19*/emisor/*59.25*/.actividadEconomica),format.raw/*59.44*/(""""
								  		autocomplete="off" 
										onfocus="value=value.replace(/,/g,'')" 
										onkeydown="return ingresoInt(window.event)"
								  		maxlength="6">
							    </td>
							</tr>
							<tr>
								<td style="text-align: left;">DIRECCION:</td>
								<td style="text-align: left;">
								  	<input type="text" class="form-control left"
										name="direccion" 
										value=""""),_display_(/*71.19*/emisor/*71.25*/.direccion),format.raw/*71.35*/(""""
								  		autocomplete="off"
								  		maxlength="60">
							    </td>
							</tr>
							<tr>
								<td style="text-align: left;">COMUNA:</td>
								<td style="text-align: left;">
								  	<input type="text" class="form-control left"
										name="comuna" 
										value=""""),_display_(/*81.19*/emisor/*81.25*/.comuna),format.raw/*81.32*/(""""
								  		autocomplete="off"
								  		maxlength="20">
							    </td>
							</tr>
							<tr>
								<td style="text-align: left;">CIUDAD:</td>
								<td style="text-align: left;">
								  	<input type="text" class="form-control left"
										name="ciudad" 
										value=""""),_display_(/*91.19*/emisor/*91.25*/.ciudad),format.raw/*91.32*/(""""
								  		autocomplete="off"
								  		maxlength="20">
							    </td>
							</tr>
							<tr>
								<td style="text-align: left;">REGION:</td>
								<td style="text-align: left;">
								  	<input type="text" class="form-control left"
										name="region" 
										value=""""),_display_(/*101.19*/emisor/*101.25*/.region),format.raw/*101.32*/(""""
								  		autocomplete="off"
								  		maxlength="20">
							    </td>
							</tr>
							<tr>
								<td style="text-align: left;">PAIS:</td>
								<td style="text-align: left;">
								  	<input type="text" class="form-control left"
										name="pais" 
										value=""""),_display_(/*111.19*/emisor/*111.25*/.pais),format.raw/*111.30*/(""""
								  		autocomplete="off"
								  		maxlength="100">
							    </td>
							</tr>
							<tr>
								<td style="text-align: left;">TASA IVA:</td>
								<td style="text-align: left;">
								  	<input type="text" class="form-control left"
										name="tasaIva" 
										value=""""),_display_(/*121.19*/emisor/*121.25*/.tasaIva),format.raw/*121.33*/(""""
								  		autocomplete="off" 
										onfocus="value=value.replace(/,/g,'')" 
										onkeydown="return ingresoDouble(window.event)"
								  		maxlength="5">
							    </td>
							</tr>
							<tr>
								<td style="text-align: left;">E-MAIL (contacto factura):</td>
								<td style="text-align: left;">
								  	<input type="text" class="form-control left"
										name="email" 
										value=""""),_display_(/*133.19*/emisor/*133.25*/.email),format.raw/*133.31*/(""""
								  		autocomplete="off"
								  		maxlength="80">
							    </td>
							</tr>
							<tr>
								<td style="text-align: left;">FONO FIJO (contacto factura):</td>
								<td style="text-align: left;">
								  	<input type="text" class="form-control left"
										name="fonoFijo" value=""""),_display_(/*142.35*/emisor/*142.41*/.fonoFijo),format.raw/*142.50*/(""""
								  		autocomplete="off" 
										onfocus="value=value.replace(/,/g,'')" 
										onkeydown="return ingresoInt(window.event)"
								  		maxlength="20">
							    </td>
							</tr>
							<tr>
								<td style="text-align: left;">FONO MOVIL (contacto factura):</td>
								<td style="text-align: left;">
								  	<input type="text" class="form-control left"
										name="fonoMovil" 
										value=""""),_display_(/*154.19*/emisor/*154.25*/.fonoMovil),format.raw/*154.35*/(""""
								  		autocomplete="off" 
										onfocus="value=value.replace(/,/g,'')" 
										onkeydown="return ingresoInt(window.event)"
								  		maxlength="20">
							    </td>
							</tr>
							<tr>
								<td style="text-align: left;">FONO FAX (contacto factura):</td>
								<td style="text-align: left;">
								  	<input type="text" class="form-control left"
										name="fonoFax" 
										value=""""),_display_(/*166.19*/emisor/*166.25*/.fonoFax),format.raw/*166.33*/(""""
								  		autocomplete="off"
								  		maxlength="20">
							    </td>
							</tr>
							<tr>
								<td style="text-align: left;">RUT REPRESENTANTE 1:</td>
								<td style="text-align: left;">
								  	<input type="text" class="form-control left"
										name="rutRepresentante1" 
										value=""""),_display_(/*176.19*/emisor/*176.25*/.rutRepresentante1),format.raw/*176.43*/(""""
								  		autocomplete="off"
								  		maxlength="50">
							    </td>
							</tr>
							<tr>
								<td style="text-align: left;">NOMBRE REPRESENTANTE 1:</td>
								<td style="text-align: left;">
								  	<input type="text" class="form-control left"
										name="nombreRepresentante1" 
										value=""""),_display_(/*186.19*/emisor/*186.25*/.nombreRepresentante1),format.raw/*186.46*/(""""
								  		autocomplete="off"
								  		maxlength="100">
							    </td>
							</tr>
							<tr>
								<td style="text-align: left;">RUT REPRESENTANTE 2:</td>
								<td style="text-align: left;">
								  	<input type="text" class="form-control left"
										name="rutRepresentante2" 
										value=""""),_display_(/*196.19*/emisor/*196.25*/.rutRepresentante2),format.raw/*196.43*/(""""
								  		autocomplete="off"
								  		maxlength="50">
							    </td>
							</tr>
							<tr>
								<td style="text-align: left;">NOMBRE REPRESENTANTE 2:</td>
								<td style="text-align: left;">
								  	<input type="text" class="form-control left"
										name="nombreRepresentante2" 
										value=""""),_display_(/*206.19*/emisor/*206.25*/.nombreRepresentante2),format.raw/*206.46*/(""""
								  		autocomplete="off"
								  		maxlength="100">
							    </td>
							</tr>
							<tr>
								<td style="text-align: left;">E-MAIL de envio XML:</td>
								<td style="text-align: left;">
								  	<input type="text" class="form-control left"
										name="emailEnvioXML" 
										value=""""),_display_(/*216.19*/emisor/*216.25*/.emailEnvioXML),format.raw/*216.39*/(""""
								  		autocomplete="off"
								  		maxlength="80">
							    </td>
							</tr>
							<tr>
								<td style="text-align: left;">apiCompany:</td>
								<td style="text-align: left;">
								  	<input type="text" class="form-control left"
										name="apiCompany" 
										value=""""),_display_(/*226.19*/emisor/*226.25*/.apiCompany),format.raw/*226.36*/(""""
								  		autocomplete="off"
								  		maxlength="250">
							    </td>
							</tr>
							<tr>
								<td style="text-align: left;">apiUser:</td>
								<td style="text-align: left;">
								  	<input type="text" class="form-control left"
										name="apiUser" 
										value=""""),_display_(/*236.19*/emisor/*236.25*/.apiUser),format.raw/*236.33*/(""""
								  		autocomplete="off"
								  		maxlength="250">
							    </td>
							</tr>
							<tr>
								<td style="text-align: left;">apiKey:</td>
								<td style="text-align: left;">
								  	<input type="text" class="form-control left"
										name="apiKey" 
										value=""""),_display_(/*246.19*/emisor/*246.25*/.apiKey),format.raw/*246.32*/(""""
								  		autocomplete="off"
								  		maxlength="250">
							    </td>
							</tr>
							<tr>
								<td style="text-align: left;">apiUrl:</td>
								<td style="text-align: left;">
								  	<input type="text" class="form-control left"
										name="apiUrl" 
										value=""""),_display_(/*256.19*/emisor/*256.25*/.apiUrl),format.raw/*256.32*/(""""
								  		autocomplete="off"
								  		maxlength="250">
							    </td>
							</tr>
							<tr>
								<td style="text-align: left;">codigoSucursal:</td>
								<td style="text-align: left;">
								  	<input type="text" class="form-control left"
										name="codigoSucursal" 
										value=""""),_display_(/*266.19*/emisor/*266.25*/.codigoSucursal),format.raw/*266.40*/(""""
								  		autocomplete="off"
								  		maxlength="250">
							    </td>
							</tr>
							<tr>
								<td style="text-align: left;">rutFuncionario:</td>
								<td style="text-align: left;">
								  	<input type="text" class="form-control left"
										name="rutFuncionario" 
										value=""""),_display_(/*276.19*/emisor/*276.25*/.rutFuncionario),format.raw/*276.40*/(""""
								  		autocomplete="off"
								  		maxlength="250">
							    </td>
							</tr>
							
							<tr>
								<td style="text-align: left;">apiUserIConstruyeOC:</td>
								<td style="text-align: left;">
								  	<input type="text" class="form-control left"
										name="apiUserIConstruyeOC" 
										value=""""),_display_(/*287.19*/emisor/*287.25*/.apiUserIConstruyeOC),format.raw/*287.45*/(""""
								  		autocomplete="off"
								  		maxlength="250">
							    </td>
							</tr>
							<tr>
								<td style="text-align: left;">apiKeyIConstruyeOC:</td>
								<td style="text-align: left;">
								  	<input type="text" class="form-control left"
										name="apiKeyIConstruyeOC" 
										value=""""),_display_(/*297.19*/emisor/*297.25*/.apiKeyIConstruyeOC),format.raw/*297.44*/(""""
								  		autocomplete="off"
								  		maxlength="250">
							    </td>
							</tr>
							<tr>
								<td style="text-align: left;">apiUrlIConstruyeOC0:</td>
								<td style="text-align: left;">
								  	<input type="text" class="form-control left"
										name="apiUrlIConstruyeOC0" 
										value=""""),_display_(/*307.19*/emisor/*307.25*/.apiUrlIConstruyeOC0),format.raw/*307.45*/(""""
								  		autocomplete="off"
								  		maxlength="250">
							    </td>
							</tr>
							<tr>
								<td style="text-align: left;">apiUrlIConstruyeOC1:</td>
								<td style="text-align: left;">
								  	<input type="text" class="form-control left"
										name="apiUrlIConstruyeOC1" 
										value=""""),_display_(/*317.19*/emisor/*317.25*/.apiUrlIConstruyeOC1),format.raw/*317.45*/(""""
								  		autocomplete="off"
								  		maxlength="250">
							    </td>
							</tr>
							<tr>
								<td style="text-align: left;">apiUrlIConstruyeOC2:</td>
								<td style="text-align: left;">
								  	<input type="text" class="form-control left"
										name="apiUrlIConstruyeOC2" 
										value=""""),_display_(/*327.19*/emisor/*327.25*/.apiUrlIConstruyeOC2),format.raw/*327.45*/(""""
								  		autocomplete="off"
								  		maxlength="250">
							    </td>
							</tr>
							<tr>
								<td style="text-align: left;">apiUrlIConstruyeOC3:</td>
								<td style="text-align: left;">
								  	<input type="text" class="form-control left"
										name="apiUrlIConstruyeOC3" 
										value=""""),_display_(/*337.19*/emisor/*337.25*/.apiUrlIConstruyeOC3),format.raw/*337.45*/(""""
								  		autocomplete="off"
								  		maxlength="250">
							    </td>
							</tr>
						</table>
					</div>
				</div>
				<div class="noprint" align="left">
					<div class="row justify-content-md-center" >
						<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
							<input type="button"  value="Cancelar" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
						</div>
						<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
							<input type="submit" value='GRABAR' class="btn btn-success btn-sm rounded-pill btn-block">
						</div>
					</div>
				</div>
			</form>
		</div>
		
		

		
		
	
	
""")))}),format.raw/*364.2*/("""



"""),format.raw/*368.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*370.31*/("""{"""),format.raw/*370.32*/("""
		"""),format.raw/*371.3*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*372.2*/("""}"""),format.raw/*372.3*/(""");
	
	
	

	
</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,emisor:tables.EmisorTributario): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,emisor)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.EmisorTributario) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,emisor) => apply(mapDiccionario,mapPermiso,userMnu,emisor)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuToolsAdmin/administrarEmisor.scala.html
                  HASH: c03790e4e44878e9766d52d03314c01c2ec19299
                  MATRIX: 1043->1|1266->131|1293->133|1309->141|1348->143|1377->147|1445->195|1474->198|1551->250|1637->316|1667->320|2003->629|2026->643|2058->654|2179->748|2194->754|2218->757|2370->882|2385->888|2410->892|2708->1163|2723->1169|2756->1181|3095->1493|3110->1499|3146->1514|3479->1820|3494->1826|3520->1831|3880->2164|3895->2170|3935->2189|4364->2591|4379->2597|4410->2607|4729->2899|4744->2905|4772->2912|5091->3204|5106->3210|5134->3217|5454->3509|5470->3515|5499->3522|5815->3810|5831->3816|5858->3821|6182->4117|6198->4123|6228->4131|6673->4548|6689->4554|6717->4560|7050->4865|7066->4871|7097->4880|7548->5303|7564->5309|7596->5319|8043->5738|8059->5744|8089->5752|8433->6068|8449->6074|8489->6092|8839->6414|8855->6420|8898->6441|9243->6758|9259->6764|9299->6782|9649->7104|9665->7110|9708->7131|10049->7444|10065->7450|10101->7464|10429->7764|10445->7770|10478->7781|10801->8076|10817->8082|10847->8090|11168->8383|11184->8389|11213->8396|11534->8689|11550->8695|11579->8702|11916->9011|11932->9017|11969->9032|12306->9341|12322->9347|12359->9362|12714->9689|12730->9695|12772->9715|13117->10032|13133->10038|13174->10057|13521->10376|13537->10382|13579->10402|13926->10721|13942->10727|13984->10747|14331->11066|14347->11072|14389->11092|14736->11411|14752->11417|14794->11437|15474->12086|15506->12090|15598->12153|15628->12154|15659->12157|15753->12223|15782->12224
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|38->7|39->8|39->8|40->9|45->14|45->14|45->14|47->16|47->16|47->16|51->20|51->20|51->20|60->29|60->29|60->29|70->39|70->39|70->39|80->49|80->49|80->49|90->59|90->59|90->59|102->71|102->71|102->71|112->81|112->81|112->81|122->91|122->91|122->91|132->101|132->101|132->101|142->111|142->111|142->111|152->121|152->121|152->121|164->133|164->133|164->133|173->142|173->142|173->142|185->154|185->154|185->154|197->166|197->166|197->166|207->176|207->176|207->176|217->186|217->186|217->186|227->196|227->196|227->196|237->206|237->206|237->206|247->216|247->216|247->216|257->226|257->226|257->226|267->236|267->236|267->236|277->246|277->246|277->246|287->256|287->256|287->256|297->266|297->266|297->266|307->276|307->276|307->276|318->287|318->287|318->287|328->297|328->297|328->297|338->307|338->307|338->307|348->317|348->317|348->317|358->327|358->327|358->327|368->337|368->337|368->337|395->364|399->368|401->370|401->370|402->371|403->372|403->372
                  -- GENERATED --
              */
          