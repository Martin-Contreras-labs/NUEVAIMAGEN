
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

object clienteModifica extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template7[Map[String,String],Map[String,String],utilities.UserMnu,tables.Cliente,List[tables.Comunas],List[tables.Regiones],List[tables.ContactoCliente],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
cliente: tables.Cliente, listComunas: List[tables.Comunas], listRegiones: List[tables.Regiones], listContactos: List[tables.ContactoCliente]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "MODIFICAR CLIENTE/PROPIETARIO", "")),format.raw/*9.68*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<table class="table table-sm table-bordered table-condensed table-fluid">
					<tr>
						<td style="text-align:left;">"""),_display_(/*14.37*/mapDiccionario/*14.51*/.get("RUT")),format.raw/*14.62*/(""": </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="rut"
								autocomplete="off"
								value=""""),_display_(/*19.17*/cliente/*19.24*/.getRut()),format.raw/*19.33*/(""""
								maxlength="50"
								onchange="value = value.trim(); modificarCliente(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">NOMBRE LARGO: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="nombre"
								autocomplete="off"
								value=""""),_display_(/*30.17*/cliente/*30.24*/.getNombre()),format.raw/*30.36*/(""""
								maxlength="100"
								onchange="value = value.trim(); modificarCliente(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">NOMBRE CORTO (*): </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="nickName"
								autocomplete="off"
								value=""""),_display_(/*41.17*/cliente/*41.24*/.getNickName()),format.raw/*41.38*/(""""
								onkeydown="return sinReservados(window.event)"
								maxlength="50"
								onchange="value = value.trim(); modificarCliente(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">DIRECCION: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="direccion"
								autocomplete="off"
								value=""""),_display_(/*53.17*/cliente/*53.24*/.getDireccion()),format.raw/*53.39*/(""""
								maxlength="100"
								onchange="value = value.trim(); modificarCliente(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">"""),_display_(/*59.37*/mapDiccionario/*59.51*/.get("Region")),format.raw/*59.65*/(""": </td>
						<td style="text-align:left;">
							<select class="custom-select" 
								id="cod_region" 
								onchange="modificarCliente(id); actualizaComunas(value);">
								"""),_display_(/*64.10*/for(lista <- listRegiones) yield /*64.36*/{_display_(Seq[Any](format.raw/*64.37*/("""
									"""),format.raw/*65.10*/("""<option value=""""),_display_(/*65.26*/lista/*65.31*/.codigo),format.raw/*65.38*/("""">"""),_display_(/*65.41*/lista/*65.46*/.nombre),format.raw/*65.53*/("""</option>
								""")))}),format.raw/*66.10*/("""
							"""),format.raw/*67.8*/("""</select>
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">"""),_display_(/*71.37*/mapDiccionario/*71.51*/.get("Comuna")),format.raw/*71.65*/(""": </td>
						<td style="text-align:left;">
							<div id="selectComuna">
								<select class="custom-select" 
									id="cod_comuna" 
									onchange="modificarCliente(id);">
									"""),_display_(/*77.11*/for(lista <- listComunas) yield /*77.36*/{_display_(Seq[Any](format.raw/*77.37*/("""
										"""),format.raw/*78.11*/("""<option value=""""),_display_(/*78.27*/lista/*78.32*/.codigo),format.raw/*78.39*/("""">"""),_display_(/*78.42*/lista/*78.47*/.nombre),format.raw/*78.54*/("""</option>
									""")))}),format.raw/*79.11*/("""
								"""),format.raw/*80.9*/("""</select>
							</div>
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">Ciudad y/o Pais: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="ciudad"
								autocomplete="off"
								value=""""),_display_(/*90.17*/cliente/*90.24*/.getCiudad()),format.raw/*90.36*/(""""
								maxlength="30"
								onchange="value = value.trim(); modificarCliente(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">GIRO: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="giro"
								autocomplete="off"
								value=""""),_display_(/*101.17*/cliente/*101.24*/.getGiro()),format.raw/*101.34*/(""""
								maxlength="100"
								onchange="value = value.trim(); modificarCliente(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">E-MAIL: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="mailFactura"
								autocomplete="off"
								value=""""),_display_(/*112.17*/cliente/*112.24*/.getMailFactura()),format.raw/*112.41*/(""""
								maxlength="50"
								onchange="value = value.trim(); modificarCliente(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">TELEFONO: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="fonoContacto"
								autocomplete="off"
								value=""""),_display_(/*123.17*/cliente/*123.24*/.getFonoContacto()),format.raw/*123.42*/(""""
								maxlength="50"
								onchange="value = value.trim(); modificarCliente(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">CONTACTO FACTURA: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="contactoFactura"
								autocomplete="off"
								value=""""),_display_(/*134.17*/cliente/*134.24*/.getContactoFactura()),format.raw/*134.45*/(""""
								maxlength="50"
								onchange="value = value.trim(); modificarCliente(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">"""),_display_(/*140.37*/mapDiccionario/*140.51*/.get("RUT")),format.raw/*140.62*/(""" """),format.raw/*140.63*/("""REPRES 1: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="rutRepresentante1"
								autocomplete="off"
								value=""""),_display_(/*145.17*/cliente/*145.24*/.getRutRepresentante1()),format.raw/*145.47*/(""""
								maxlength="50"
								onchange="value = value.trim(); modificarCliente(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">NOMBRE REPRES 1: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="nombreRepresentante1"
								autocomplete="off"
								value=""""),_display_(/*156.17*/cliente/*156.24*/.getNombreRepresentante1()),format.raw/*156.50*/(""""
								maxlength="100"
								onchange="value = value.trim(); modificarCliente(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">"""),_display_(/*162.37*/mapDiccionario/*162.51*/.get("RUT")),format.raw/*162.62*/(""" """),format.raw/*162.63*/("""REPRES 2: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="rutRepresentante2"
								autocomplete="off"
								value=""""),_display_(/*167.17*/cliente/*167.24*/.getRutRepresentante2()),format.raw/*167.47*/(""""
								maxlength="50"
								onchange="value = value.trim(); modificarCliente(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">NOMBRE REPRES 2: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="nombreRepresentante2"
								autocomplete="off"
								value=""""),_display_(/*178.17*/cliente/*178.24*/.getNombreRepresentante2()),format.raw/*178.50*/(""""
								maxlength="100"
								onchange="value = value.trim(); modificarCliente(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">FORMA DE PAGO: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="formaDePago"
								value=""""),_display_(/*188.17*/cliente/*188.24*/.getFormaDePago()),format.raw/*188.41*/(""""
								maxlength="100"
								onchange="value = value.trim(); modificarCliente(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">ESPECIALIDAD: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="especialidad"
								value=""""),_display_(/*198.17*/cliente/*198.24*/.getEspecialidad()),format.raw/*198.42*/(""""
								maxlength="100"
								onchange="value = value.trim(); modificarCliente(id)">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<table class="table table-sm table-bordered table-condensed table-fluid">
								<thead style="background-color: #eeeeee">
									<tr>
										<th colspan="10" style="text-align:left">
											<font color="#008000"> LISTA DE CONTACTOS: </font>
										</th>
									</tr>
									<tr>
										<TH>NOMBRE</TH>
										<TH>CARGO</TH>
										<TH>FONO-FIJO</TH>
										<TH>FONO-MOVIL</TH>
										<TH>E-MAIL</TH>
										<TH>Edit</TH>
										<TH>Del</TH>
									</tr>
								</thead>
								<tbody>
									"""),_display_(/*223.11*/for(lista <- listContactos) yield /*223.38*/{_display_(Seq[Any](format.raw/*223.39*/("""
										"""),format.raw/*224.11*/("""<tr>
									        <td style="text-align:left;">"""),_display_(/*225.48*/lista/*225.53*/.getNombre()),format.raw/*225.65*/("""</td>
											<td style="text-align:left;">"""),_display_(/*226.42*/lista/*226.47*/.getCargo()),format.raw/*226.58*/("""</td>
											<td style="text-align:left;">"""),_display_(/*227.42*/lista/*227.47*/.getFijo()),format.raw/*227.57*/("""</td>
											<td style="text-align:left;">"""),_display_(/*228.42*/lista/*228.47*/.getMovil()),format.raw/*228.58*/("""</td>
											<td style="text-align:left;">"""),_display_(/*229.42*/lista/*229.47*/.getMail()),format.raw/*229.57*/("""</td>
											<td  style="text-align:center;" width="10%">
												<a href="/clienteContactoModifica/"""),_display_(/*231.48*/lista/*231.53*/.getId()),format.raw/*231.61*/(""","""),_display_(/*231.63*/cliente/*231.70*/.getId()),format.raw/*231.78*/("""">
													<kbd style="background-color: #73C6B6">E</kbd>
												</a>
											</td>
											<td  style="text-align:center;">
												<a href="#" onclick= "eliminarContacto('"""),_display_(/*236.54*/lista/*236.59*/.getId()),format.raw/*236.67*/("""')">
													<kbd style="background-color: red">X</kbd>
												</a>
											</td>
										</tr>
									""")))}),format.raw/*241.11*/("""
									"""),format.raw/*242.10*/("""<td colspan=7>
										<div align="center">
											<input type="button" class="btn btn-light btn-sm rounded-pill" 
												onclick="location.href='/clienteContactoAgrega/"""),_display_(/*245.61*/cliente/*245.68*/.getId()),format.raw/*245.76*/("""'"
												value = "Agregar Contacto">
										</div>
									</td>
								</tbody>
							</table>
						</td>
					</tr>
					
					
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/clienteMantencion/';">
				</div>
			</div>
		</div>
	</div>

""")))}),format.raw/*267.2*/("""



"""),format.raw/*271.1*/("""<script type="text/javascript">
	let id_cliente = '"""),_display_(/*272.21*/cliente/*272.28*/.getId()),format.raw/*272.36*/("""';

	$(document).ready(function() """),format.raw/*274.31*/("""{"""),format.raw/*274.32*/("""
		  """),format.raw/*275.5*/("""$('#cod_region > option[value=""""),_display_(/*275.37*/cliente/*275.44*/.getCod_region()),format.raw/*275.60*/(""""]').attr('selected', 'selected');
		  $('#cod_comuna > option[value=""""),_display_(/*276.37*/cliente/*276.44*/.getCod_comuna()),format.raw/*276.60*/(""""]').attr('selected', 'selected');
		  
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*279.2*/("""}"""),format.raw/*279.3*/(""");
	
	const actualizaComunas = (cod_region) => """),format.raw/*281.43*/("""{"""),format.raw/*281.44*/("""
		"""),format.raw/*282.3*/("""var formData = new FormData();
		formData.append('cod_region', cod_region);
		formData.append('id_cliente',id_cliente);
	  	$.ajax("""),format.raw/*285.12*/("""{"""),format.raw/*285.13*/("""
            """),format.raw/*286.13*/("""url: "/selComuna3Ajax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(data)"""),format.raw/*293.31*/("""{"""),format.raw/*293.32*/("""
	     		"""),format.raw/*294.9*/("""if(data=="error")"""),format.raw/*294.26*/("""{"""),format.raw/*294.27*/("""
	     			"""),format.raw/*295.10*/("""alertify.alert(msgError, function () """),format.raw/*295.47*/("""{"""),format.raw/*295.48*/("""
		     			"""),format.raw/*296.11*/("""location.href = "/";
		     		"""),format.raw/*297.10*/("""}"""),format.raw/*297.11*/(""");
	     		"""),format.raw/*298.9*/("""}"""),format.raw/*298.10*/("""else"""),format.raw/*298.14*/("""{"""),format.raw/*298.15*/("""
	     			"""),format.raw/*299.10*/("""data = data.replace(/&lt;/g,"<");
					data = data.replace(/&gt;/g,">");
					data = data.replace(/&#x27;/g,"\"");
	     			document.getElementById('selectComuna').innerHTML = data;
	     		"""),format.raw/*303.9*/("""}"""),format.raw/*303.10*/("""
	     	"""),format.raw/*304.8*/("""}"""),format.raw/*304.9*/("""
        """),format.raw/*305.9*/("""}"""),format.raw/*305.10*/(""");
	"""),format.raw/*306.2*/("""}"""),format.raw/*306.3*/("""
	
	"""),format.raw/*308.2*/("""const modificarCliente = (campo) => """),format.raw/*308.38*/("""{"""),format.raw/*308.39*/("""
		"""),format.raw/*309.3*/("""let valor = $("#"+campo).val();
		var formData = new FormData();
	  	formData.append('campo',campo);
	  	formData.append('id_cliente',id_cliente);
	  	formData.append('valor',valor);
        $.ajax("""),format.raw/*314.16*/("""{"""),format.raw/*314.17*/("""
            """),format.raw/*315.13*/("""url: "/modificaClientePorCampoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*322.36*/("""{"""),format.raw/*322.37*/("""
	     		"""),format.raw/*323.9*/("""if(respuesta=="existe")"""),format.raw/*323.32*/("""{"""),format.raw/*323.33*/("""
	     			"""),format.raw/*324.10*/("""alertify.alert("El nombre corto de cliente ya existe, intente con otro.", function () """),format.raw/*324.96*/("""{"""),format.raw/*324.97*/("""
	     				"""),format.raw/*325.11*/("""$("#nickName").val(""""),_display_(/*325.32*/cliente/*325.39*/.getNickName()),format.raw/*325.53*/("""");
		     		"""),format.raw/*326.10*/("""}"""),format.raw/*326.11*/(""");
	     		"""),format.raw/*327.9*/("""}"""),format.raw/*327.10*/("""else if(respuesta=="error")"""),format.raw/*327.37*/("""{"""),format.raw/*327.38*/("""
	     			"""),format.raw/*328.10*/("""alertify.alert(msgError, function () """),format.raw/*328.47*/("""{"""),format.raw/*328.48*/("""
		     			"""),format.raw/*329.11*/("""location.href = "/";
		     		"""),format.raw/*330.10*/("""}"""),format.raw/*330.11*/(""");
	     		"""),format.raw/*331.9*/("""}"""),format.raw/*331.10*/("""
	     	"""),format.raw/*332.8*/("""}"""),format.raw/*332.9*/("""
        """),format.raw/*333.9*/("""}"""),format.raw/*333.10*/(""");
	"""),format.raw/*334.2*/("""}"""),format.raw/*334.3*/("""
	
	"""),format.raw/*336.2*/("""const eliminarContacto = (id_contacto) => """),format.raw/*336.44*/("""{"""),format.raw/*336.45*/("""
		"""),format.raw/*337.3*/("""alertify.confirm("Esta seguro de eliminar el contacto", function (e) """),format.raw/*337.72*/("""{"""),format.raw/*337.73*/("""
			"""),format.raw/*338.4*/("""if (e) """),format.raw/*338.11*/("""{"""),format.raw/*338.12*/("""
				"""),format.raw/*339.5*/("""location.href = "/clienteContactoElimina/" + id_contacto + "," + id_cliente;
			"""),format.raw/*340.4*/("""}"""),format.raw/*340.5*/("""
		"""),format.raw/*341.3*/("""}"""),format.raw/*341.4*/(""");
	"""),format.raw/*342.2*/("""}"""),format.raw/*342.3*/("""

"""),format.raw/*344.1*/("""</script>

		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,cliente:tables.Cliente,listComunas:List[tables.Comunas],listRegiones:List[tables.Regiones],listContactos:List[tables.ContactoCliente]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,cliente,listComunas,listRegiones,listContactos)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.Cliente,List[tables.Comunas],List[tables.Regiones],List[tables.ContactoCliente]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,cliente,listComunas,listRegiones,listContactos) => apply(mapDiccionario,mapPermiso,userMnu,cliente,listComunas,listRegiones,listContactos)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuTablas/clienteModifica.scala.html
                  HASH: 9d96e88bc57ef9f40bd5821e4a9f2300b3c26f71
                  MATRIX: 1100->1|1432->240|1465->248|1481->256|1520->258|1548->261|1616->309|1644->311|1720->362|1804->426|1834->429|2084->652|2107->666|2139->677|2322->833|2338->840|2368->849|2722->1176|2738->1183|2771->1195|3132->1529|3148->1536|3183->1550|3592->1932|3608->1939|3644->1954|3829->2112|3852->2126|3887->2140|4097->2323|4139->2349|4178->2350|4216->2360|4259->2376|4273->2381|4301->2388|4331->2391|4345->2396|4373->2403|4423->2422|4458->2430|4564->2509|4587->2523|4622->2537|4842->2730|4883->2755|4922->2756|4961->2767|5004->2783|5018->2788|5046->2795|5076->2798|5090->2803|5118->2810|5169->2830|5205->2839|5498->3105|5514->3112|5547->3124|5892->3441|5909->3448|5941->3458|6296->3785|6313->3792|6352->3809|6709->4138|6726->4145|6766->4163|7134->4503|7151->4510|7194->4531|7379->4688|7403->4702|7436->4713|7466->4714|7672->4892|7689->4899|7734->4922|8106->5266|8123->5273|8171->5299|8357->5457|8381->5471|8414->5482|8444->5483|8650->5661|8667->5668|8712->5691|9084->6035|9101->6042|9149->6068|9484->6375|9501->6382|9540->6399|9875->6706|9892->6713|9932->6731|10650->7421|10694->7448|10734->7449|10774->7460|10854->7512|10869->7517|10903->7529|10978->7576|10993->7581|11026->7592|11101->7639|11116->7644|11148->7654|11223->7701|11238->7706|11271->7717|11346->7764|11361->7769|11393->7779|11530->7888|11545->7893|11575->7901|11605->7903|11622->7910|11652->7918|11874->8112|11889->8117|11919->8125|12072->8246|12111->8256|12320->8437|12337->8444|12367->8452|12882->8936|12914->8940|12994->8992|13011->8999|13041->9007|13104->9041|13134->9042|13167->9047|13227->9079|13244->9086|13282->9102|13381->9173|13398->9180|13436->9196|13574->9306|13603->9307|13679->9354|13709->9355|13740->9358|13900->9489|13930->9490|13972->9503|14223->9725|14253->9726|14290->9735|14336->9752|14366->9753|14405->9763|14471->9800|14501->9801|14541->9812|14600->9842|14630->9843|14669->9854|14699->9855|14732->9859|14762->9860|14801->9870|15019->10060|15049->10061|15085->10069|15114->10070|15151->10079|15181->10080|15213->10084|15242->10085|15274->10089|15339->10125|15369->10126|15400->10129|15627->10327|15657->10328|15699->10341|15968->10581|15998->10582|16035->10591|16087->10614|16117->10615|16156->10625|16271->10711|16301->10712|16341->10723|16390->10744|16407->10751|16443->10765|16485->10778|16515->10779|16554->10790|16584->10791|16640->10818|16670->10819|16709->10829|16775->10866|16805->10867|16845->10878|16904->10908|16934->10909|16973->10920|17003->10921|17039->10929|17068->10930|17105->10939|17135->10940|17167->10944|17196->10945|17228->10949|17299->10991|17329->10992|17360->10995|17458->11064|17488->11065|17520->11069|17556->11076|17586->11077|17619->11082|17727->11162|17756->11163|17787->11166|17816->11167|17848->11171|17877->11172|17907->11174
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|45->14|45->14|45->14|50->19|50->19|50->19|61->30|61->30|61->30|72->41|72->41|72->41|84->53|84->53|84->53|90->59|90->59|90->59|95->64|95->64|95->64|96->65|96->65|96->65|96->65|96->65|96->65|96->65|97->66|98->67|102->71|102->71|102->71|108->77|108->77|108->77|109->78|109->78|109->78|109->78|109->78|109->78|109->78|110->79|111->80|121->90|121->90|121->90|132->101|132->101|132->101|143->112|143->112|143->112|154->123|154->123|154->123|165->134|165->134|165->134|171->140|171->140|171->140|171->140|176->145|176->145|176->145|187->156|187->156|187->156|193->162|193->162|193->162|193->162|198->167|198->167|198->167|209->178|209->178|209->178|219->188|219->188|219->188|229->198|229->198|229->198|254->223|254->223|254->223|255->224|256->225|256->225|256->225|257->226|257->226|257->226|258->227|258->227|258->227|259->228|259->228|259->228|260->229|260->229|260->229|262->231|262->231|262->231|262->231|262->231|262->231|267->236|267->236|267->236|272->241|273->242|276->245|276->245|276->245|298->267|302->271|303->272|303->272|303->272|305->274|305->274|306->275|306->275|306->275|306->275|307->276|307->276|307->276|310->279|310->279|312->281|312->281|313->282|316->285|316->285|317->286|324->293|324->293|325->294|325->294|325->294|326->295|326->295|326->295|327->296|328->297|328->297|329->298|329->298|329->298|329->298|330->299|334->303|334->303|335->304|335->304|336->305|336->305|337->306|337->306|339->308|339->308|339->308|340->309|345->314|345->314|346->315|353->322|353->322|354->323|354->323|354->323|355->324|355->324|355->324|356->325|356->325|356->325|356->325|357->326|357->326|358->327|358->327|358->327|358->327|359->328|359->328|359->328|360->329|361->330|361->330|362->331|362->331|363->332|363->332|364->333|364->333|365->334|365->334|367->336|367->336|367->336|368->337|368->337|368->337|369->338|369->338|369->338|370->339|371->340|371->340|372->341|372->341|373->342|373->342|375->344
                  -- GENERATED --
              */
          