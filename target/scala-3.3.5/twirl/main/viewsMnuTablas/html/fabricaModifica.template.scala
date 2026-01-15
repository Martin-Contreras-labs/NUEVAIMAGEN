
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

object fabricaModifica extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template7[Map[String,String],Map[String,String],utilities.UserMnu,tables.Fabrica,List[tables.Comunas],List[tables.Regiones],List[tables.ContactoFabrica],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
fabrica: tables.Fabrica, listComunas: List[tables.Comunas], listRegiones: List[tables.Regiones], listContactos: List[tables.ContactoFabrica]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "MODIFICAR FABRICANTE", "")),format.raw/*9.59*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<table class="table table-sm table-bordered table-condensed table-fluid">
					<tr>
						<td style="text-align:left;">NOMBRE LARGO: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="nombre"
								onkeydown="return sinReservados(window.event)"
								autocomplete="off"
								value=""""),_display_(/*20.17*/fabrica/*20.24*/.getNombre()),format.raw/*20.36*/(""""
								maxlength="100"
								onchange="value = value.trim(); modificarFabrica(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">NOMBRE CORTO: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="nickName"
								onkeydown="return sinReservados(window.event)"
								autocomplete="off"
								value=""""),_display_(/*32.17*/fabrica/*32.24*/.getNickName()),format.raw/*32.38*/(""""
								maxlength="50"
								onchange="value = value.trim(); modificarFabrica(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">DIRECCION: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="direccion"
								autocomplete="off"
								value=""""),_display_(/*43.17*/fabrica/*43.24*/.getDireccion()),format.raw/*43.39*/(""""
								maxlength="100"
								onchange="value = value.trim(); modificarFabrica(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">"""),_display_(/*49.37*/mapDiccionario/*49.51*/.get("Region")),format.raw/*49.65*/(""": </td>
						<td style="text-align:left;">
							<select class="custom-select" 
								id="cod_region" 
								onchange="modificarFabrica(id); actualizaComunas(value);">
								"""),_display_(/*54.10*/for(lista <- listRegiones) yield /*54.36*/{_display_(Seq[Any](format.raw/*54.37*/("""
									"""),format.raw/*55.10*/("""<option value=""""),_display_(/*55.26*/lista/*55.31*/.codigo),format.raw/*55.38*/("""">"""),_display_(/*55.41*/lista/*55.46*/.nombre),format.raw/*55.53*/("""</option>
								""")))}),format.raw/*56.10*/("""
							"""),format.raw/*57.8*/("""</select>
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">"""),_display_(/*61.37*/mapDiccionario/*61.51*/.get("Comuna")),format.raw/*61.65*/(""": </td>
						<td style="text-align:left;">
							<div id="selectComuna">
								<select class="custom-select" 
									id="cod_comuna" 
									onchange="modificarFabrica(id);">
									"""),_display_(/*67.11*/for(lista <- listComunas) yield /*67.36*/{_display_(Seq[Any](format.raw/*67.37*/("""
										"""),format.raw/*68.11*/("""<option value=""""),_display_(/*68.27*/lista/*68.32*/.codigo),format.raw/*68.39*/("""">"""),_display_(/*68.42*/lista/*68.47*/.nombre),format.raw/*68.54*/("""</option>
									""")))}),format.raw/*69.11*/("""
								"""),format.raw/*70.9*/("""</select>
							</div>
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">Ciudad: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="ciudad"
								autocomplete="off"
								value=""""),_display_(/*80.17*/fabrica/*80.24*/.getCiudad()),format.raw/*80.36*/(""""
								maxlength="30"
								onchange="value = value.trim(); modificarFabrica(id)">
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
									"""),_display_(/*105.11*/for(lista <- listContactos) yield /*105.38*/{_display_(Seq[Any](format.raw/*105.39*/("""
										"""),format.raw/*106.11*/("""<tr>
									        <td style="text-align:left;">"""),_display_(/*107.48*/lista/*107.53*/.getNombre()),format.raw/*107.65*/("""</td>
											<td style="text-align:left;">"""),_display_(/*108.42*/lista/*108.47*/.getCargo()),format.raw/*108.58*/("""</td>
											<td style="text-align:left;">"""),_display_(/*109.42*/lista/*109.47*/.getFijo()),format.raw/*109.57*/("""</td>
											<td style="text-align:left;">"""),_display_(/*110.42*/lista/*110.47*/.getMovil()),format.raw/*110.58*/("""</td>
											<td style="text-align:left;">"""),_display_(/*111.42*/lista/*111.47*/.getMail()),format.raw/*111.57*/("""</td>
											<td  style="text-align:center;" width="10%">
												<a href="/fabricaContactoModifica/"""),_display_(/*113.48*/lista/*113.53*/.getId()),format.raw/*113.61*/(""","""),_display_(/*113.63*/fabrica/*113.70*/.getId()),format.raw/*113.78*/("""">
													<kbd style="background-color: #73C6B6">E</kbd>
												</a>
											</td>
											<td  style="text-align:center;">
												<a href="#" onclick= "eliminarContacto("""),_display_(/*118.53*/lista/*118.58*/.getId()),format.raw/*118.66*/(""")">
													<kbd style="background-color: red">X</kbd>
												</a>
											</td>
										</tr>
									""")))}),format.raw/*123.11*/("""
									"""),format.raw/*124.10*/("""<td colspan=7>
										<div align="center">
											<input type="button" class="btn btn-light btn-sm rounded-pill" 
												onclick="location.href='/fabricaContactoAgrega/"""),_display_(/*127.61*/fabrica/*127.68*/.getId()),format.raw/*127.76*/("""'"
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
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/fabricaMantencion/';">
				</div>
			</div>
		</div>
	</div>

""")))}),format.raw/*149.2*/("""



"""),format.raw/*153.1*/("""<script type="text/javascript">
	let id_fabrica = """),_display_(/*154.20*/fabrica/*154.27*/.getId()),format.raw/*154.35*/(""";

	$(document).ready(function() """),format.raw/*156.31*/("""{"""),format.raw/*156.32*/("""
		  """),format.raw/*157.5*/("""$('#cod_region > option[value=""""),_display_(/*157.37*/fabrica/*157.44*/.getCod_region()),format.raw/*157.60*/(""""]').attr('selected', 'selected');
		  $('#cod_comuna > option[value=""""),_display_(/*158.37*/fabrica/*158.44*/.getCod_comuna()),format.raw/*158.60*/(""""]').attr('selected', 'selected');
		  
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*161.2*/("""}"""),format.raw/*161.3*/(""");
	
	const actualizaComunas = (cod_region) => """),format.raw/*163.43*/("""{"""),format.raw/*163.44*/("""
		"""),format.raw/*164.3*/("""var formData = new FormData();
		formData.append('cod_region', cod_region);
		formData.append('id_fabrica',id_fabrica);
	  	$.ajax("""),format.raw/*167.12*/("""{"""),format.raw/*167.13*/("""
            """),format.raw/*168.13*/("""url: "/selComuna4Ajax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(data)"""),format.raw/*175.31*/("""{"""),format.raw/*175.32*/("""
	     		"""),format.raw/*176.9*/("""if(data=="error")"""),format.raw/*176.26*/("""{"""),format.raw/*176.27*/("""
	     			"""),format.raw/*177.10*/("""alertify.alert(msgError, function () """),format.raw/*177.47*/("""{"""),format.raw/*177.48*/("""
		     			"""),format.raw/*178.11*/("""location.href = "/";
		     		"""),format.raw/*179.10*/("""}"""),format.raw/*179.11*/(""");
	     		"""),format.raw/*180.9*/("""}"""),format.raw/*180.10*/("""else"""),format.raw/*180.14*/("""{"""),format.raw/*180.15*/("""
	     			"""),format.raw/*181.10*/("""data = data.replace(/&lt;/g,"<");
					data = data.replace(/&gt;/g,">");
					data = data.replace(/&#x27;/g,"\"");
	     			document.getElementById('selectComuna').innerHTML = data;
	     		"""),format.raw/*185.9*/("""}"""),format.raw/*185.10*/("""
	     	"""),format.raw/*186.8*/("""}"""),format.raw/*186.9*/("""
        """),format.raw/*187.9*/("""}"""),format.raw/*187.10*/(""");
	"""),format.raw/*188.2*/("""}"""),format.raw/*188.3*/("""
	
	"""),format.raw/*190.2*/("""const modificarFabrica = (campo) => """),format.raw/*190.38*/("""{"""),format.raw/*190.39*/("""
		"""),format.raw/*191.3*/("""let valor = $("#"+campo).val();
		var formData = new FormData();
	  	formData.append('campo',campo);
	  	formData.append('id_fabrica',id_fabrica);
	  	formData.append('valor',valor);
        $.ajax("""),format.raw/*196.16*/("""{"""),format.raw/*196.17*/("""
            """),format.raw/*197.13*/("""url: "/modificaFabricaPorCampoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*204.36*/("""{"""),format.raw/*204.37*/("""
	     		"""),format.raw/*205.9*/("""if(respuesta=="existe")"""),format.raw/*205.32*/("""{"""),format.raw/*205.33*/("""
	     			"""),format.raw/*206.10*/("""alertify.alert('El nombre corto del fabricante ya existe, intente con otro', function () """),format.raw/*206.99*/("""{"""),format.raw/*206.100*/("""
	     				"""),format.raw/*207.11*/("""$("#nickName").val(""""),_display_(/*207.32*/fabrica/*207.39*/.getNickName()),format.raw/*207.53*/("""");
		     		"""),format.raw/*208.10*/("""}"""),format.raw/*208.11*/(""");
	     		"""),format.raw/*209.9*/("""}"""),format.raw/*209.10*/("""else if(respuesta=="error")"""),format.raw/*209.37*/("""{"""),format.raw/*209.38*/("""
	     			"""),format.raw/*210.10*/("""alertify.alert(msgError, function () """),format.raw/*210.47*/("""{"""),format.raw/*210.48*/("""
		     			"""),format.raw/*211.11*/("""location.href = "/";
		     		"""),format.raw/*212.10*/("""}"""),format.raw/*212.11*/(""");
	     		"""),format.raw/*213.9*/("""}"""),format.raw/*213.10*/("""
	     	"""),format.raw/*214.8*/("""}"""),format.raw/*214.9*/("""
        """),format.raw/*215.9*/("""}"""),format.raw/*215.10*/(""");
	"""),format.raw/*216.2*/("""}"""),format.raw/*216.3*/("""
	
	"""),format.raw/*218.2*/("""const eliminarContacto = (id_contacto) => """),format.raw/*218.44*/("""{"""),format.raw/*218.45*/("""
		"""),format.raw/*219.3*/("""alertify.confirm("Esta seguro de eliminar el contacto", function (e) """),format.raw/*219.72*/("""{"""),format.raw/*219.73*/("""
			"""),format.raw/*220.4*/("""if (e) """),format.raw/*220.11*/("""{"""),format.raw/*220.12*/("""
				"""),format.raw/*221.5*/("""location.href = "/fabricaContactoElimina/" + id_contacto + "," + id_fabrica;
			"""),format.raw/*222.4*/("""}"""),format.raw/*222.5*/("""
		"""),format.raw/*223.3*/("""}"""),format.raw/*223.4*/(""");
	"""),format.raw/*224.2*/("""}"""),format.raw/*224.3*/("""

"""),format.raw/*226.1*/("""</script>

		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,fabrica:tables.Fabrica,listComunas:List[tables.Comunas],listRegiones:List[tables.Regiones],listContactos:List[tables.ContactoFabrica]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,fabrica,listComunas,listRegiones,listContactos)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.Fabrica,List[tables.Comunas],List[tables.Regiones],List[tables.ContactoFabrica]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,fabrica,listComunas,listRegiones,listContactos) => apply(mapDiccionario,mapPermiso,userMnu,fabrica,listComunas,listRegiones,listContactos)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuTablas/fabricaModifica.scala.html
                  HASH: f5d2b257f9668c7c1e2351023a3a32a1e697e468
                  MATRIX: 1100->1|1432->240|1465->248|1481->256|1520->258|1548->261|1616->309|1644->311|1720->362|1795->417|1825->420|2300->868|2316->875|2349->887|2761->1272|2777->1279|2812->1293|3166->1620|3182->1627|3218->1642|3403->1800|3426->1814|3461->1828|3671->2011|3713->2037|3752->2038|3790->2048|3833->2064|3847->2069|3875->2076|3905->2079|3919->2084|3947->2091|3997->2110|4032->2118|4138->2197|4161->2211|4196->2225|4416->2418|4457->2443|4496->2444|4535->2455|4578->2471|4592->2476|4620->2483|4650->2486|4664->2491|4692->2498|4743->2518|4779->2527|5063->2784|5079->2791|5112->2803|5843->3506|5887->3533|5927->3534|5967->3545|6047->3597|6062->3602|6096->3614|6171->3661|6186->3666|6219->3677|6294->3724|6309->3729|6341->3739|6416->3786|6431->3791|6464->3802|6539->3849|6554->3854|6586->3864|6723->3973|6738->3978|6768->3986|6798->3988|6815->3995|6845->4003|7066->4196|7081->4201|7111->4209|7263->4329|7302->4339|7511->4520|7528->4527|7558->4535|8073->5019|8105->5023|8184->5074|8201->5081|8231->5089|8293->5122|8323->5123|8356->5128|8416->5160|8433->5167|8471->5183|8570->5254|8587->5261|8625->5277|8763->5387|8792->5388|8868->5435|8898->5436|8929->5439|9089->5570|9119->5571|9161->5584|9412->5806|9442->5807|9479->5816|9525->5833|9555->5834|9594->5844|9660->5881|9690->5882|9730->5893|9789->5923|9819->5924|9858->5935|9888->5936|9921->5940|9951->5941|9990->5951|10208->6141|10238->6142|10274->6150|10303->6151|10340->6160|10370->6161|10402->6165|10431->6166|10463->6170|10528->6206|10558->6207|10589->6210|10816->6408|10846->6409|10888->6422|11157->6662|11187->6663|11224->6672|11276->6695|11306->6696|11345->6706|11463->6795|11494->6796|11534->6807|11583->6828|11600->6835|11636->6849|11678->6862|11708->6863|11747->6874|11777->6875|11833->6902|11863->6903|11902->6913|11968->6950|11998->6951|12038->6962|12097->6992|12127->6993|12166->7004|12196->7005|12232->7013|12261->7014|12298->7023|12328->7024|12360->7028|12389->7029|12421->7033|12492->7075|12522->7076|12553->7079|12651->7148|12681->7149|12713->7153|12749->7160|12779->7161|12812->7166|12920->7246|12949->7247|12980->7250|13009->7251|13041->7255|13070->7256|13100->7258
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|51->20|51->20|51->20|63->32|63->32|63->32|74->43|74->43|74->43|80->49|80->49|80->49|85->54|85->54|85->54|86->55|86->55|86->55|86->55|86->55|86->55|86->55|87->56|88->57|92->61|92->61|92->61|98->67|98->67|98->67|99->68|99->68|99->68|99->68|99->68|99->68|99->68|100->69|101->70|111->80|111->80|111->80|136->105|136->105|136->105|137->106|138->107|138->107|138->107|139->108|139->108|139->108|140->109|140->109|140->109|141->110|141->110|141->110|142->111|142->111|142->111|144->113|144->113|144->113|144->113|144->113|144->113|149->118|149->118|149->118|154->123|155->124|158->127|158->127|158->127|180->149|184->153|185->154|185->154|185->154|187->156|187->156|188->157|188->157|188->157|188->157|189->158|189->158|189->158|192->161|192->161|194->163|194->163|195->164|198->167|198->167|199->168|206->175|206->175|207->176|207->176|207->176|208->177|208->177|208->177|209->178|210->179|210->179|211->180|211->180|211->180|211->180|212->181|216->185|216->185|217->186|217->186|218->187|218->187|219->188|219->188|221->190|221->190|221->190|222->191|227->196|227->196|228->197|235->204|235->204|236->205|236->205|236->205|237->206|237->206|237->206|238->207|238->207|238->207|238->207|239->208|239->208|240->209|240->209|240->209|240->209|241->210|241->210|241->210|242->211|243->212|243->212|244->213|244->213|245->214|245->214|246->215|246->215|247->216|247->216|249->218|249->218|249->218|250->219|250->219|250->219|251->220|251->220|251->220|252->221|253->222|253->222|254->223|254->223|255->224|255->224|257->226
                  -- GENERATED --
              */
          