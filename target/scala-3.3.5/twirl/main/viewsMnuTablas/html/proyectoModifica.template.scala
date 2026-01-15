
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

object proyectoModifica extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template7[Map[String,String],Map[String,String],utilities.UserMnu,tables.Proyecto,List[tables.Comunas],List[tables.Regiones],List[tables.ContactoProyecto],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
proyecto: tables.Proyecto, listComunas: List[tables.Comunas], listRegiones: List[tables.Regiones], listContactos: List[tables.ContactoProyecto]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "MODIFICAR PROYECTO", "")),format.raw/*9.57*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<table class="table table-sm table-bordered table-condensed table-fluid">
					<tr>
						<td style="text-align:left;">NOMBRE LARGO: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="nombre"
								autocomplete="off"
								value=""""),_display_(/*19.17*/proyecto/*19.25*/.getNombre()),format.raw/*19.37*/(""""
								onkeydown="return sinReservados(window.event)"
								maxlength="100"
								onchange="value = value.trim(); modificarProyecto(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">NOMBRE CORTO (*): </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="nickName"
								autocomplete="off"
								onkeydown="return sinReservados(window.event)"
								value=""""),_display_(/*32.17*/proyecto/*32.25*/.getNickName()),format.raw/*32.39*/(""""
								maxlength="50"
								onchange="value = value.trim(); modificarProyecto(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">DIRECCION: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="direccion"
								autocomplete="off"
								value=""""),_display_(/*43.17*/proyecto/*43.25*/.getDireccion()),format.raw/*43.40*/(""""
								maxlength="100"
								onchange="value = value.trim(); modificarProyecto(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">"""),_display_(/*49.37*/mapDiccionario/*49.51*/.get("Region")),format.raw/*49.65*/(""": </td>
						<td style="text-align:left;">
							<select class="custom-select" 
								id="cod_region" 
								onchange="modificarProyecto(id); actualizaComunas(value);">
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
									onchange="modificarProyecto(id);">
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
								value=""""),_display_(/*80.17*/proyecto/*80.25*/.getCiudad()),format.raw/*80.37*/(""""
								maxlength="30"
								onchange="value = value.trim(); modificarProyecto(id)">
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
												<a href="/proyectoContactoModifica/"""),_display_(/*113.49*/lista/*113.54*/.getId()),format.raw/*113.62*/(""","""),_display_(/*113.64*/proyecto/*113.72*/.getId()),format.raw/*113.80*/("""">
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
												onclick="location.href='/proyectoContactoAgrega/"""),_display_(/*127.62*/proyecto/*127.70*/.getId()),format.raw/*127.78*/("""'"
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
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/proyectoMantencion/';">
				</div>
			</div>
		</div>
	</div>

""")))}),format.raw/*149.2*/("""



"""),format.raw/*153.1*/("""<script type="text/javascript">
	let id_proyecto = """),_display_(/*154.21*/proyecto/*154.29*/.getId()),format.raw/*154.37*/(""";

	$(document).ready(function() """),format.raw/*156.31*/("""{"""),format.raw/*156.32*/("""
		  """),format.raw/*157.5*/("""$('#cod_region > option[value=""""),_display_(/*157.37*/proyecto/*157.45*/.getCod_region()),format.raw/*157.61*/(""""]').attr('selected', 'selected');
		  $('#cod_comuna > option[value=""""),_display_(/*158.37*/proyecto/*158.45*/.getCod_comuna()),format.raw/*158.61*/(""""]').attr('selected', 'selected');
		  
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*161.2*/("""}"""),format.raw/*161.3*/(""");
	
	const actualizaComunas = (cod_region) => """),format.raw/*163.43*/("""{"""),format.raw/*163.44*/("""
		"""),format.raw/*164.3*/("""var formData = new FormData();
		formData.append('cod_region', cod_region);
		formData.append('id_proyecto',id_proyecto);
	  	$.ajax("""),format.raw/*167.12*/("""{"""),format.raw/*167.13*/("""
            """),format.raw/*168.13*/("""url: "/selComuna2Ajax/",
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
	
	"""),format.raw/*190.2*/("""const modificarProyecto = (campo) => """),format.raw/*190.39*/("""{"""),format.raw/*190.40*/("""
		"""),format.raw/*191.3*/("""let valor = $("#"+campo).val();
		var formData = new FormData();
	  	formData.append('campo',campo);
	  	formData.append('id_proyecto',id_proyecto);
	  	formData.append('valor',valor);
        $.ajax("""),format.raw/*196.16*/("""{"""),format.raw/*196.17*/("""
            """),format.raw/*197.13*/("""url: "/modificaProyectoPorCampoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*204.36*/("""{"""),format.raw/*204.37*/("""
	     		"""),format.raw/*205.9*/("""if(respuesta=="existe")"""),format.raw/*205.32*/("""{"""),format.raw/*205.33*/("""
	     			"""),format.raw/*206.10*/("""alertify.alert('El nombre corto de proyecto ya existe, intente con otro', function () """),format.raw/*206.96*/("""{"""),format.raw/*206.97*/("""
	     				"""),format.raw/*207.11*/("""$("#nickName").val(""""),_display_(/*207.32*/proyecto/*207.40*/.getNickName()),format.raw/*207.54*/("""");
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
				"""),format.raw/*221.5*/("""location.href = "/proyectoContactoElimina/" + id_contacto + "," + id_proyecto;
			"""),format.raw/*222.4*/("""}"""),format.raw/*222.5*/("""
		"""),format.raw/*223.3*/("""}"""),format.raw/*223.4*/(""");
	"""),format.raw/*224.2*/("""}"""),format.raw/*224.3*/("""

"""),format.raw/*226.1*/("""</script>

		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,proyecto:tables.Proyecto,listComunas:List[tables.Comunas],listRegiones:List[tables.Regiones],listContactos:List[tables.ContactoProyecto]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,proyecto,listComunas,listRegiones,listContactos)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.Proyecto,List[tables.Comunas],List[tables.Regiones],List[tables.ContactoProyecto]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,proyecto,listComunas,listRegiones,listContactos) => apply(mapDiccionario,mapPermiso,userMnu,proyecto,listComunas,listRegiones,listContactos)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuTablas/proyectoModifica.scala.html
                  HASH: 60e2b09219ea4e7a814f6dfc2ed0adb2081d1d70
                  MATRIX: 1103->1|1438->243|1471->251|1487->259|1526->261|1554->264|1622->312|1650->314|1726->365|1799->418|1829->421|2249->814|2266->822|2299->834|2771->1279|2788->1287|2823->1301|3178->1629|3195->1637|3231->1652|3417->1811|3440->1825|3475->1839|3686->2023|3728->2049|3767->2050|3805->2060|3848->2076|3862->2081|3890->2088|3920->2091|3934->2096|3962->2103|4012->2122|4047->2130|4153->2209|4176->2223|4211->2237|4432->2431|4473->2456|4512->2457|4551->2468|4594->2484|4608->2489|4636->2496|4666->2499|4680->2504|4708->2511|4759->2531|4795->2540|5079->2797|5096->2805|5129->2817|5861->3521|5905->3548|5945->3549|5985->3560|6065->3612|6080->3617|6114->3629|6189->3676|6204->3681|6237->3692|6312->3739|6327->3744|6359->3754|6434->3801|6449->3806|6482->3817|6557->3864|6572->3869|6604->3879|6742->3989|6757->3994|6787->4002|6817->4004|6835->4012|6865->4020|7086->4213|7101->4218|7131->4226|7283->4346|7322->4356|7532->4538|7550->4546|7580->4554|8096->5039|8128->5043|8208->5095|8226->5103|8256->5111|8318->5144|8348->5145|8381->5150|8441->5182|8459->5190|8497->5206|8596->5277|8614->5285|8652->5301|8790->5411|8819->5412|8895->5459|8925->5460|8956->5463|9118->5596|9148->5597|9190->5610|9441->5832|9471->5833|9508->5842|9554->5859|9584->5860|9623->5870|9689->5907|9719->5908|9759->5919|9818->5949|9848->5950|9887->5961|9917->5962|9950->5966|9980->5967|10019->5977|10237->6167|10267->6168|10303->6176|10332->6177|10369->6186|10399->6187|10431->6191|10460->6192|10492->6196|10558->6233|10588->6234|10619->6237|10848->6437|10878->6438|10920->6451|11190->6692|11220->6693|11257->6702|11309->6725|11339->6726|11378->6736|11493->6822|11523->6823|11563->6834|11612->6855|11630->6863|11666->6877|11708->6890|11738->6891|11777->6902|11807->6903|11863->6930|11893->6931|11932->6941|11998->6978|12028->6979|12068->6990|12127->7020|12157->7021|12196->7032|12226->7033|12262->7041|12291->7042|12328->7051|12358->7052|12390->7056|12419->7057|12451->7061|12522->7103|12552->7104|12583->7107|12681->7176|12711->7177|12743->7181|12779->7188|12809->7189|12842->7194|12952->7276|12981->7277|13012->7280|13041->7281|13073->7285|13102->7286|13132->7288
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|50->19|50->19|50->19|63->32|63->32|63->32|74->43|74->43|74->43|80->49|80->49|80->49|85->54|85->54|85->54|86->55|86->55|86->55|86->55|86->55|86->55|86->55|87->56|88->57|92->61|92->61|92->61|98->67|98->67|98->67|99->68|99->68|99->68|99->68|99->68|99->68|99->68|100->69|101->70|111->80|111->80|111->80|136->105|136->105|136->105|137->106|138->107|138->107|138->107|139->108|139->108|139->108|140->109|140->109|140->109|141->110|141->110|141->110|142->111|142->111|142->111|144->113|144->113|144->113|144->113|144->113|144->113|149->118|149->118|149->118|154->123|155->124|158->127|158->127|158->127|180->149|184->153|185->154|185->154|185->154|187->156|187->156|188->157|188->157|188->157|188->157|189->158|189->158|189->158|192->161|192->161|194->163|194->163|195->164|198->167|198->167|199->168|206->175|206->175|207->176|207->176|207->176|208->177|208->177|208->177|209->178|210->179|210->179|211->180|211->180|211->180|211->180|212->181|216->185|216->185|217->186|217->186|218->187|218->187|219->188|219->188|221->190|221->190|221->190|222->191|227->196|227->196|228->197|235->204|235->204|236->205|236->205|236->205|237->206|237->206|237->206|238->207|238->207|238->207|238->207|239->208|239->208|240->209|240->209|240->209|240->209|241->210|241->210|241->210|242->211|243->212|243->212|244->213|244->213|245->214|245->214|246->215|246->215|247->216|247->216|249->218|249->218|249->218|250->219|250->219|250->219|251->220|251->220|251->220|252->221|253->222|253->222|254->223|254->223|255->224|255->224|257->226
                  -- GENERATED --
              */
          