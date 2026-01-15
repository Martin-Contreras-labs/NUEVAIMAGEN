
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

object proveedorModifica extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template7[Map[String,String],Map[String,String],utilities.UserMnu,tables.Proveedor,List[tables.Comunas],List[tables.Regiones],List[tables.ContactoProveedor],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
proveedor: tables.Proveedor, listComunas: List[tables.Comunas], listRegiones: List[tables.Regiones], listContactos: List[tables.ContactoProveedor]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "MODIFICAR PROVEEDOR", "")),format.raw/*9.58*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<table class="table table-sm table-bordered table-condensed table-fluid">
					<tr>
						<td style="text-align:left;">"""),_display_(/*14.37*/mapDiccionario/*14.51*/.get("RUT")),format.raw/*14.62*/(""": </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="rut"
								autocomplete="off"
								value=""""),_display_(/*19.17*/proveedor/*19.26*/.getRut()),format.raw/*19.35*/(""""
								maxlength="50"
								onchange="value = value.trim(); modificarProveedor(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">NOMBRE LARGO: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="nombre"
								autocomplete="off"
								value=""""),_display_(/*30.17*/proveedor/*30.26*/.getNombre()),format.raw/*30.38*/(""""
								onkeydown="return sinReservados(window.event)"
								maxlength="100"
								onchange="value = value.trim(); modificarProveedor(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">NOMBRE CORTO: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="nickName"
								onkeydown="return sinReservados(window.event)"
								autocomplete="off"
								value=""""),_display_(/*43.17*/proveedor/*43.26*/.getNickName()),format.raw/*43.40*/(""""
								maxlength="50"
								onchange="value = value.trim(); modificarProveedor(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">DIRECCION: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="direccion"
								autocomplete="off"
								value=""""),_display_(/*54.17*/proveedor/*54.26*/.getDireccion()),format.raw/*54.41*/(""""
								maxlength="100"
								onchange="value = value.trim(); modificarProveedor(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">"""),_display_(/*60.37*/mapDiccionario/*60.51*/.get("Region")),format.raw/*60.65*/(""": </td>
						<td style="text-align:left;">
							<select class="custom-select" 
								id="cod_region" 
								onchange="modificarProveedor(id); actualizaComunas(value);">
								"""),_display_(/*65.10*/for(lista <- listRegiones) yield /*65.36*/{_display_(Seq[Any](format.raw/*65.37*/("""
									"""),format.raw/*66.10*/("""<option value=""""),_display_(/*66.26*/lista/*66.31*/.codigo),format.raw/*66.38*/("""">"""),_display_(/*66.41*/lista/*66.46*/.nombre),format.raw/*66.53*/("""</option>
								""")))}),format.raw/*67.10*/("""
							"""),format.raw/*68.8*/("""</select>
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">"""),_display_(/*72.37*/mapDiccionario/*72.51*/.get("Comuna")),format.raw/*72.65*/(""": </td>
						<td style="text-align:left;">
							<div id="selectComuna">
								<select class="custom-select" 
									id="cod_comuna" 
									onchange="modificarProveedor(id);">
									"""),_display_(/*78.11*/for(lista <- listComunas) yield /*78.36*/{_display_(Seq[Any](format.raw/*78.37*/("""
										"""),format.raw/*79.11*/("""<option value=""""),_display_(/*79.27*/lista/*79.32*/.codigo),format.raw/*79.39*/("""">"""),_display_(/*79.42*/lista/*79.47*/.nombre),format.raw/*79.54*/("""</option>
									""")))}),format.raw/*80.11*/("""
								"""),format.raw/*81.9*/("""</select>
							</div>
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">Ciudad: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="ciudad"
								autocomplete="off"
								value=""""),_display_(/*91.17*/proveedor/*91.26*/.getCiudad()),format.raw/*91.38*/(""""
								maxlength="30"
								onchange="value = value.trim(); modificarProveedor(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">FORMA DE PAGO: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="formaDePago"
								autocomplete="off"
								value=""""),_display_(/*102.17*/proveedor/*102.26*/.getFormaDePago()),format.raw/*102.43*/(""""
								maxlength="100"
								onchange="value = value.trim(); modificarProveedor(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">ESPECIALIDAD: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="especialidad"
								autocomplete="off"
								value=""""),_display_(/*113.17*/proveedor/*113.26*/.getEspecialidad()),format.raw/*113.44*/(""""
								maxlength="100"
								onchange="value = value.trim(); modificarProveedor(id)">
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
										<TH>TELEFONO<br>FIJO</TH>
										<TH>TELEFONO<br>MOVIL</TH>
										<TH>E-MAIL</TH>
										<TH>Edit</TH>
										<TH>Del</TH>
									</tr>
								</thead>
								<tbody>
									"""),_display_(/*138.11*/for(lista <- listContactos) yield /*138.38*/{_display_(Seq[Any](format.raw/*138.39*/("""
										"""),format.raw/*139.11*/("""<tr>
									        <td style="text-align:left;">"""),_display_(/*140.48*/lista/*140.53*/.getNombre()),format.raw/*140.65*/("""</td>
											<td style="text-align:left;">"""),_display_(/*141.42*/lista/*141.47*/.getCargo()),format.raw/*141.58*/("""</td>
											<td style="text-align:left;">"""),_display_(/*142.42*/lista/*142.47*/.getFijo()),format.raw/*142.57*/("""</td>
											<td style="text-align:left;">"""),_display_(/*143.42*/lista/*143.47*/.getMovil()),format.raw/*143.58*/("""</td>
											<td style="text-align:left;">"""),_display_(/*144.42*/lista/*144.47*/.getMail()),format.raw/*144.57*/("""</td>
											<td  style="text-align:center;" width="10%">
												<a href="/proveedorContactoModifica/"""),_display_(/*146.50*/lista/*146.55*/.getId()),format.raw/*146.63*/(""","""),_display_(/*146.65*/proveedor/*146.74*/.getId()),format.raw/*146.82*/("""">
													<kbd style="background-color: #73C6B6">E</kbd>
												</a>
											</td>
											<td  style="text-align:center;">
												<a href="#" onclick= "eliminarContacto("""),_display_(/*151.53*/lista/*151.58*/.getId()),format.raw/*151.66*/(""")">
													<kbd style="background-color: red">X</kbd>
												</a>
											</td>
										</tr>
									""")))}),format.raw/*156.11*/("""
									"""),format.raw/*157.10*/("""<td colspan=7>
										<div align="center">
											<input type="button" class="btn btn-light btn-sm rounded-pill" 
												onclick="location.href='/proveedorContactoAgrega/"""),_display_(/*160.63*/proveedor/*160.72*/.getId()),format.raw/*160.80*/("""'"
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
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/proveedorMantencion/';">
				</div>
			</div>
		</div>
	</div>

""")))}),format.raw/*182.2*/("""



"""),format.raw/*186.1*/("""<script type="text/javascript">
	let id_proveedor = """),_display_(/*187.22*/proveedor/*187.31*/.getId()),format.raw/*187.39*/(""";

	$(document).ready(function() """),format.raw/*189.31*/("""{"""),format.raw/*189.32*/("""
		  """),format.raw/*190.5*/("""$('#cod_region > option[value=""""),_display_(/*190.37*/proveedor/*190.46*/.getCod_region()),format.raw/*190.62*/(""""]').attr('selected', 'selected');
		  $('#cod_comuna > option[value=""""),_display_(/*191.37*/proveedor/*191.46*/.getCod_comuna()),format.raw/*191.62*/(""""]').attr('selected', 'selected');
		  
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*194.2*/("""}"""),format.raw/*194.3*/(""");
	
	const actualizaComunas = (cod_region) => """),format.raw/*196.43*/("""{"""),format.raw/*196.44*/("""
		"""),format.raw/*197.3*/("""var formData = new FormData();
		formData.append('cod_region', cod_region);
		formData.append('id_proveedor',id_proveedor);
	  	$.ajax("""),format.raw/*200.12*/("""{"""),format.raw/*200.13*/("""
            """),format.raw/*201.13*/("""url: "/selComuna5Ajax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(data)"""),format.raw/*208.31*/("""{"""),format.raw/*208.32*/("""
	     		"""),format.raw/*209.9*/("""if(data=="error")"""),format.raw/*209.26*/("""{"""),format.raw/*209.27*/("""
	     			"""),format.raw/*210.10*/("""alertify.alert(msgError, function () """),format.raw/*210.47*/("""{"""),format.raw/*210.48*/("""
		     			"""),format.raw/*211.11*/("""location.href = "/";
		     		"""),format.raw/*212.10*/("""}"""),format.raw/*212.11*/(""");
	     		"""),format.raw/*213.9*/("""}"""),format.raw/*213.10*/("""else"""),format.raw/*213.14*/("""{"""),format.raw/*213.15*/("""
	     			"""),format.raw/*214.10*/("""data = data.replace(/&lt;/g,"<");
					data = data.replace(/&gt;/g,">");
					data = data.replace(/&#x27;/g,"\"");
	     			document.getElementById('selectComuna').innerHTML = data;
	     		"""),format.raw/*218.9*/("""}"""),format.raw/*218.10*/("""
	     	"""),format.raw/*219.8*/("""}"""),format.raw/*219.9*/("""
        """),format.raw/*220.9*/("""}"""),format.raw/*220.10*/(""");
	"""),format.raw/*221.2*/("""}"""),format.raw/*221.3*/("""
	
	"""),format.raw/*223.2*/("""const modificarProveedor = (campo) => """),format.raw/*223.40*/("""{"""),format.raw/*223.41*/("""
		"""),format.raw/*224.3*/("""let valor = $("#"+campo).val();
		var formData = new FormData();
	  	formData.append('campo',campo);
	  	formData.append('id_proveedor',id_proveedor);
	  	formData.append('valor',valor);
        $.ajax("""),format.raw/*229.16*/("""{"""),format.raw/*229.17*/("""
            """),format.raw/*230.13*/("""url: "/modificaProveedorPorCampoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*237.36*/("""{"""),format.raw/*237.37*/("""
	     		"""),format.raw/*238.9*/("""if(respuesta=="existe")"""),format.raw/*238.32*/("""{"""),format.raw/*238.33*/("""
	     			"""),format.raw/*239.10*/("""alertify.alert('El nombre corto de proveedor ya existe, intente con otro', function () """),format.raw/*239.97*/("""{"""),format.raw/*239.98*/("""
	     				"""),format.raw/*240.11*/("""$("#nickName").val(""""),_display_(/*240.32*/proveedor/*240.41*/.getNickName()),format.raw/*240.55*/("""");
		     		"""),format.raw/*241.10*/("""}"""),format.raw/*241.11*/(""");
	     		"""),format.raw/*242.9*/("""}"""),format.raw/*242.10*/("""else if(respuesta=="error")"""),format.raw/*242.37*/("""{"""),format.raw/*242.38*/("""
	     			"""),format.raw/*243.10*/("""alertify.alert(msgError, function () """),format.raw/*243.47*/("""{"""),format.raw/*243.48*/("""
		     			"""),format.raw/*244.11*/("""location.href = "/";
		     		"""),format.raw/*245.10*/("""}"""),format.raw/*245.11*/(""");
	     		"""),format.raw/*246.9*/("""}"""),format.raw/*246.10*/("""
	     	"""),format.raw/*247.8*/("""}"""),format.raw/*247.9*/("""
        """),format.raw/*248.9*/("""}"""),format.raw/*248.10*/(""");
	"""),format.raw/*249.2*/("""}"""),format.raw/*249.3*/("""
	
	"""),format.raw/*251.2*/("""const eliminarContacto = (id_contacto) => """),format.raw/*251.44*/("""{"""),format.raw/*251.45*/("""
		"""),format.raw/*252.3*/("""alertify.confirm("Esta seguro de eliminar el contacto", function (e) """),format.raw/*252.72*/("""{"""),format.raw/*252.73*/("""
			"""),format.raw/*253.4*/("""if (e) """),format.raw/*253.11*/("""{"""),format.raw/*253.12*/("""
				"""),format.raw/*254.5*/("""location.href = "/proveedorContactoElimina/" + id_contacto + "," + id_proveedor;
			"""),format.raw/*255.4*/("""}"""),format.raw/*255.5*/("""
		"""),format.raw/*256.3*/("""}"""),format.raw/*256.4*/(""");
	"""),format.raw/*257.2*/("""}"""),format.raw/*257.3*/("""

"""),format.raw/*259.1*/("""</script>

		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,proveedor:tables.Proveedor,listComunas:List[tables.Comunas],listRegiones:List[tables.Regiones],listContactos:List[tables.ContactoProveedor]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,proveedor,listComunas,listRegiones,listContactos)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.Proveedor,List[tables.Comunas],List[tables.Regiones],List[tables.ContactoProveedor]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,proveedor,listComunas,listRegiones,listContactos) => apply(mapDiccionario,mapPermiso,userMnu,proveedor,listComunas,listRegiones,listContactos)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuTablas/proveedorModifica.scala.html
                  HASH: 99e0012b50e5149fbe75e42047c0c71ad78030d4
                  MATRIX: 1106->1|1444->246|1477->254|1493->262|1532->264|1560->267|1628->315|1656->317|1732->368|1806->422|1836->425|2086->648|2109->662|2141->673|2324->829|2342->838|2372->847|2728->1176|2746->1185|2779->1197|3248->1639|3266->1648|3301->1662|3657->1991|3675->2000|3711->2015|3898->2175|3921->2189|3956->2203|4168->2388|4210->2414|4249->2415|4287->2425|4330->2441|4344->2446|4372->2453|4402->2456|4416->2461|4444->2468|4494->2487|4529->2495|4635->2574|4658->2588|4693->2602|4915->2797|4956->2822|4995->2823|5034->2834|5077->2850|5091->2855|5119->2862|5149->2865|5163->2870|5191->2877|5242->2897|5278->2906|5562->3163|5580->3172|5613->3184|5976->3519|5995->3528|6034->3545|6398->3881|6417->3890|6457->3908|7191->4614|7235->4641|7275->4642|7315->4653|7395->4705|7410->4710|7444->4722|7519->4769|7534->4774|7567->4785|7642->4832|7657->4837|7689->4847|7764->4894|7779->4899|7812->4910|7887->4957|7902->4962|7934->4972|8073->5083|8088->5088|8118->5096|8148->5098|8167->5107|8197->5115|8418->5308|8433->5313|8463->5321|8615->5441|8654->5451|8865->5634|8884->5643|8914->5651|9431->6137|9463->6141|9544->6194|9563->6203|9593->6211|9655->6244|9685->6245|9718->6250|9778->6282|9797->6291|9835->6307|9934->6378|9953->6387|9991->6403|10129->6513|10158->6514|10234->6561|10264->6562|10295->6565|10459->6700|10489->6701|10531->6714|10782->6936|10812->6937|10849->6946|10895->6963|10925->6964|10964->6974|11030->7011|11060->7012|11100->7023|11159->7053|11189->7054|11228->7065|11258->7066|11291->7070|11321->7071|11360->7081|11578->7271|11608->7272|11644->7280|11673->7281|11710->7290|11740->7291|11772->7295|11801->7296|11833->7300|11900->7338|11930->7339|11961->7342|12192->7544|12222->7545|12264->7558|12535->7800|12565->7801|12602->7810|12654->7833|12684->7834|12723->7844|12839->7931|12869->7932|12909->7943|12958->7964|12977->7973|13013->7987|13055->8000|13085->8001|13124->8012|13154->8013|13210->8040|13240->8041|13279->8051|13345->8088|13375->8089|13415->8100|13474->8130|13504->8131|13543->8142|13573->8143|13609->8151|13638->8152|13675->8161|13705->8162|13737->8166|13766->8167|13798->8171|13869->8213|13899->8214|13930->8217|14028->8286|14058->8287|14090->8291|14126->8298|14156->8299|14189->8304|14301->8388|14330->8389|14361->8392|14390->8393|14422->8397|14451->8398|14481->8400
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|45->14|45->14|45->14|50->19|50->19|50->19|61->30|61->30|61->30|74->43|74->43|74->43|85->54|85->54|85->54|91->60|91->60|91->60|96->65|96->65|96->65|97->66|97->66|97->66|97->66|97->66|97->66|97->66|98->67|99->68|103->72|103->72|103->72|109->78|109->78|109->78|110->79|110->79|110->79|110->79|110->79|110->79|110->79|111->80|112->81|122->91|122->91|122->91|133->102|133->102|133->102|144->113|144->113|144->113|169->138|169->138|169->138|170->139|171->140|171->140|171->140|172->141|172->141|172->141|173->142|173->142|173->142|174->143|174->143|174->143|175->144|175->144|175->144|177->146|177->146|177->146|177->146|177->146|177->146|182->151|182->151|182->151|187->156|188->157|191->160|191->160|191->160|213->182|217->186|218->187|218->187|218->187|220->189|220->189|221->190|221->190|221->190|221->190|222->191|222->191|222->191|225->194|225->194|227->196|227->196|228->197|231->200|231->200|232->201|239->208|239->208|240->209|240->209|240->209|241->210|241->210|241->210|242->211|243->212|243->212|244->213|244->213|244->213|244->213|245->214|249->218|249->218|250->219|250->219|251->220|251->220|252->221|252->221|254->223|254->223|254->223|255->224|260->229|260->229|261->230|268->237|268->237|269->238|269->238|269->238|270->239|270->239|270->239|271->240|271->240|271->240|271->240|272->241|272->241|273->242|273->242|273->242|273->242|274->243|274->243|274->243|275->244|276->245|276->245|277->246|277->246|278->247|278->247|279->248|279->248|280->249|280->249|282->251|282->251|282->251|283->252|283->252|283->252|284->253|284->253|284->253|285->254|286->255|286->255|287->256|287->256|288->257|288->257|290->259
                  -- GENERATED --
              */
          