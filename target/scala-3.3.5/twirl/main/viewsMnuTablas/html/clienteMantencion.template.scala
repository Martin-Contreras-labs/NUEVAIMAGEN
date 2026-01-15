
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

object clienteMantencion extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[Map[String,String],Map[String,String],utilities.UserMnu,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	
	"""),_display_(/*8.3*/modalContactoCliente()),format.raw/*8.25*/("""
	
	"""),format.raw/*10.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*11.4*/barraTitulo(mapDiccionario, "CLIENTES/PROPIETARIOS: CREAR/MODIFICAR/ELIMINAR", "")),format.raw/*11.86*/("""
		
		"""),format.raw/*13.3*/("""<div id="enCarga" class="blocker" style="display: block;"><br><br><br><br><br><br><h1>En proceso .....</h1></div>
		
		<form id="excel" class="formulario" method="post" action="/clienteMantencionExcel/"> </form>
			
		<div id="tblPrincipal"></div>
		
	</div>
	
	<form id="form_eliminar" method="post" action="/clienteElimina/">
		<input type="hidden" id="form_id_cliente" name="id_cliente">
	</form>
""")))}),format.raw/*24.2*/("""




"""),format.raw/*29.1*/("""<script type="text/javascript">

	window.addEventListener('load', function() """),format.raw/*31.45*/("""{"""),format.raw/*31.46*/("""
		"""),format.raw/*32.3*/("""document.getElementById('mostrarmostrar').style.display="block";
		vistaPrincipal();
		
	"""),format.raw/*35.2*/("""}"""),format.raw/*35.3*/(""");
	
	const vistaPrincipal = () =>"""),format.raw/*37.30*/("""{"""),format.raw/*37.31*/("""
		
		"""),format.raw/*39.3*/("""document.getElementById('enCarga').style.display="block";
		
		var formData = new FormData();
        $.ajax("""),format.raw/*42.16*/("""{"""),format.raw/*42.17*/("""
            """),format.raw/*43.13*/("""url: "/routes2/clienteMantencion2/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(rs)"""),format.raw/*50.29*/("""{"""),format.raw/*50.30*/("""
		
				"""),format.raw/*52.5*/("""let json = rs;
		
				let tabla =
					+ "<div class=\"noprint\">"
						+ "<table class=\"table table-sm table-condensed table-fluid\"><tr><td><div align=\"center\">"
						+ "<button type=\"button\"  class=\"btn btn-sm btn-success\" onclick=\"document.getElementById('excel').submit()\">"
							+ "Exportar a Excel"
						+ "</button></div></td></tr></table></div>"
			
					+ "<table id=\"tablaPrincipal\" class=\"table table-sm table-hover table-bordered table-condensed table-fluid\">"
						+ "<thead style=\"background-color: #eeeeee\">"
							+ "<TR>"
								+ '<TH>"""),_display_(/*64.17*/mapDiccionario/*64.31*/.get("RUT")),format.raw/*64.42*/("""</TH>'
								+ "<TH>RAZON SOCIAL (FULL NAME)</TH>"
								+ "<TH>NOMBRE CORTO (NICK NAME)</TH>"
								+ "<TH>GIRO</TH>"
						        + "<TH>DIRECCION</TH>"
						        + '<TH>"""),_display_(/*69.23*/mapDiccionario/*69.37*/.get("Region")),format.raw/*69.51*/("""</TH>'
						        + '<TH>"""),_display_(/*70.23*/mapDiccionario/*70.37*/.get("Comuna")),format.raw/*70.51*/("""</TH>'
						        + "<TH>Ciudad y/o Pais</TH>"
						        + "<TH>Contacto</TH>"
						        + "<TH>E-Mail</TH>"
						        + "<TH>Telefono</TH>"
								+ "<TH>EDIT</TH>"
								+ "<TH>VIGENTE</TH>"
								+ "<TH>DEL</TH>"
							+ "</TR>"
						+ "</thead>"
						+ "<tbody>";
					for(var i in json)"""),format.raw/*81.24*/("""{"""),format.raw/*81.25*/("""
						"""),format.raw/*82.7*/("""tabla +=
						 "<TR>"
							+ "<td style=\"text-align:center; min-width:80px\"><a href=\"#\" onclick=\"buscaCliente('"+json[i]["nickName"]+"')\">"+json[i]["rut"]+"</a></td>"
							+ "<td style=\"text-align:left;\"><a href=\"#\" onclick=\"buscaCliente('"+json[i]["nickName"]+"')\">"+json[i]["nombre"]+"</a></td>"
							+ "<td style=\"text-align:left;\"><a href=\"#\" onclick=\"buscaCliente('"+json[i]["nickName"]+"')\">"+json[i]["nickName"]+"</a></td>"
							+ "<td  style=\"text-align:left;\">"+json[i]["giro"]+"</td>"
							+ "<td  style=\"text-align:left;\">"+json[i]["direccion"]+"</td>"
							+ "<td  style=\"text-align:left;\">"+json[i]["region"]+"</td>"
							+ "<td  style=\"text-align:left;\">"+json[i]["comuna"]+"</td>"
							+ "<td  style=\"text-align:left;\">"+json[i]["ciudad"]+"</td>"
							+ "<td  style=\"text-align:left;\">"+json[i]["contactoFactura"]+"</td>"
							+ "<td  style=\"text-align:left;\">"+json[i]["mailFactura"]+"</td>"
							+ "<td  style=\"text-align:left;\">"+json[i]["fonoContacto"]+"</td>"
							+ "<td  style=\"text-align:center;\">"
								+ "<a href=\"/clienteModifica/"+json[i]["id"]+"\">"
									+ "<kbd style=\"background-color: #73C6B6\">E</kbd>"
								+ "</a>"
							+ "</td>"
							
							+ "<td  style=\"text-align:left;\">";

									if(json[i]["vigente"]==1)"""),format.raw/*103.35*/("""{"""),format.raw/*103.36*/("""
						        		"""),format.raw/*104.17*/("""tabla += "<select class=\"form-control form-control-sm\" onchange=\"cambiaEstado(value,'"+json[i]["id"]+"')\">"
								        	+ "<option value=\"1\">Vigente</option>"
							            	+ "<option value=\"0\">NO</option>"
								        + "</select>";
						        	"""),format.raw/*108.16*/("""}"""),format.raw/*108.17*/("""else"""),format.raw/*108.21*/("""{"""),format.raw/*108.22*/("""
						        		"""),format.raw/*109.17*/("""tabla += "<select class=\"form-control form-control-sm\" onchange=\"cambiaEstado(value,'"+json[i]["id"]+"')\">"
						        			+ "<option value=\"0\">NO</option>"
								        	+ "<option value=\"1\">Vigente</option>"
								        + "<option value=\"1\">Vigente</option>"
						        	"""),format.raw/*113.16*/("""}"""),format.raw/*113.17*/("""

							"""),format.raw/*115.8*/("""tabla += "</td>"
							
							+ "<td  style=\"text-align:center;\">"
								+ "<a href=\"#\" onclick= \"eliminarCliente('"+json[i]["id"]+"')\">"
									+ "<kbd style=\"background-color: red\">X</kbd>"
								+ "</a>"
							+ "</td>"
						+ "</TR>";
		 			"""),format.raw/*123.7*/("""}"""),format.raw/*123.8*/("""
				"""),format.raw/*124.5*/("""tabla +=
					 "</tbody>"
						+ "</table>"
					+ "<div class=\"noprint\">"
						+ "<div class=\"row justify-content-md-center\" >"
							+ "<div class=\"col-xs-5 col-sm-2 col-md-2 col-lg-2\">"
								+ "<input type=\"button\"  value=\"AGREGAR\" class=\"btn btn-success btn-sm rounded-pill btn-block\" onclick=\"location.href='/clienteAgrega/';\">"
							+ "</div>"
							+ "<div class=\"col-xs-5 col-sm-2 col-md-2 col-lg-2\">"
								+ "<input type=\"button\"  value=\"CANCELAR\" class=\"btn btn-light btn-sm rounded-pill btn-block\" onclick=\"location.href='/home/';\">"
							+ "</div>"
						+ "</div>"
					+ "</div>";
					
				
				document.getElementById('tblPrincipal').innerHTML = tabla;
		
				$('#tablaPrincipal').DataTable("""),format.raw/*141.36*/("""{"""),format.raw/*141.37*/("""
				    	"""),format.raw/*142.10*/(""""fixedHeader": true,
						"order": [[ 2, "asc" ]],
				    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
				    	"language": """),format.raw/*145.22*/("""{"""),format.raw/*145.23*/("""
				        	"""),format.raw/*146.14*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
				        """),format.raw/*147.13*/("""}"""),format.raw/*147.14*/("""
				"""),format.raw/*148.5*/("""}"""),format.raw/*148.6*/(""" """),format.raw/*148.7*/(""").on("init.dt", function()"""),format.raw/*148.33*/("""{"""),format.raw/*148.34*/("""
					"""),format.raw/*149.6*/("""document.getElementById('enCarga').style.display="none";
				"""),format.raw/*150.5*/("""}"""),format.raw/*150.6*/(""");
				
			"""),format.raw/*152.4*/("""}"""),format.raw/*152.5*/("""
			
        """),format.raw/*154.9*/("""}"""),format.raw/*154.10*/(""");
		
	"""),format.raw/*156.2*/("""}"""),format.raw/*156.3*/("""

	"""),format.raw/*158.2*/("""const eliminarCliente = (id_cliente) => """),format.raw/*158.42*/("""{"""),format.raw/*158.43*/("""
		"""),format.raw/*159.3*/("""let nombre = $("#"+id_cliente).text();
		alertify.confirm("Esta seguro de eliminar el cliente: "+nombre, function (e) """),format.raw/*160.80*/("""{"""),format.raw/*160.81*/("""
			"""),format.raw/*161.4*/("""if (e) """),format.raw/*161.11*/("""{"""),format.raw/*161.12*/("""
				"""),format.raw/*162.5*/("""$("#form_id_cliente").val(id_cliente);
				document.getElementById("form_eliminar").submit();
			"""),format.raw/*164.4*/("""}"""),format.raw/*164.5*/("""
		"""),format.raw/*165.3*/("""}"""),format.raw/*165.4*/(""");
	"""),format.raw/*166.2*/("""}"""),format.raw/*166.3*/("""
	
	"""),format.raw/*168.2*/("""const cambiaEstado = (estado,id_cliente) =>"""),format.raw/*168.45*/("""{"""),format.raw/*168.46*/("""
    	"""),format.raw/*169.6*/("""var formData = new FormData();
	  	formData.append('id_cliente',id_cliente);
	  	formData.append('estado',estado);
        $.ajax("""),format.raw/*172.16*/("""{"""),format.raw/*172.17*/("""
            """),format.raw/*173.13*/("""url: "/routes2/clienteCambiaEstado/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*180.36*/("""{"""),format.raw/*180.37*/("""
	     		"""),format.raw/*181.9*/("""if(!respuesta.status)"""),format.raw/*181.30*/("""{"""),format.raw/*181.31*/("""
	     				"""),format.raw/*182.11*/("""location.reload();
				"""),format.raw/*183.5*/("""}"""),format.raw/*183.6*/("""
	     	"""),format.raw/*184.8*/("""}"""),format.raw/*184.9*/("""
        """),format.raw/*185.9*/("""}"""),format.raw/*185.10*/(""");
	"""),format.raw/*186.2*/("""}"""),format.raw/*186.3*/("""
	
"""),format.raw/*188.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu) => apply(mapDiccionario,mapPermiso,userMnu)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuTablas/clienteMantencion.scala.html
                  HASH: 7c7d8108a305b9b3c0b3d20d339022b789e0d2b0
                  MATRIX: 1015->1|1205->98|1238->106|1254->114|1293->116|1322->120|1390->168|1420->173|1462->195|1493->199|1570->250|1673->332|1706->338|2137->739|2169->744|2274->821|2303->822|2333->825|2449->914|2477->915|2539->949|2568->950|2601->956|2738->1065|2767->1066|2808->1079|3068->1311|3097->1312|3132->1320|3737->1898|3760->1912|3792->1923|4003->2107|4026->2121|4061->2135|4117->2164|4140->2178|4175->2192|4517->2506|4546->2507|4580->2514|5936->3841|5966->3842|6012->3859|6313->4131|6343->4132|6376->4136|6406->4137|6452->4154|6776->4449|6806->4450|6843->4459|7134->4722|7163->4723|7196->4728|7970->5473|8000->5474|8039->5484|8217->5633|8247->5634|8290->5648|8399->5728|8429->5729|8462->5734|8491->5735|8520->5736|8575->5762|8605->5763|8639->5769|8728->5830|8757->5831|8796->5842|8825->5843|8866->5856|8896->5857|8931->5864|8960->5865|8991->5868|9060->5908|9090->5909|9121->5912|9268->6030|9298->6031|9330->6035|9366->6042|9396->6043|9429->6048|9554->6145|9583->6146|9614->6149|9643->6150|9675->6154|9704->6155|9736->6159|9808->6202|9838->6203|9872->6209|10031->6339|10061->6340|10103->6353|10372->6593|10402->6594|10439->6603|10489->6624|10519->6625|10559->6636|10610->6659|10639->6660|10675->6668|10704->6669|10741->6678|10771->6679|10803->6683|10832->6684|10863->6687
                  LINES: 28->1|33->2|35->4|35->4|35->4|37->6|37->6|39->8|39->8|41->10|42->11|42->11|44->13|55->24|60->29|62->31|62->31|63->32|66->35|66->35|68->37|68->37|70->39|73->42|73->42|74->43|81->50|81->50|83->52|95->64|95->64|95->64|100->69|100->69|100->69|101->70|101->70|101->70|112->81|112->81|113->82|134->103|134->103|135->104|139->108|139->108|139->108|139->108|140->109|144->113|144->113|146->115|154->123|154->123|155->124|172->141|172->141|173->142|176->145|176->145|177->146|178->147|178->147|179->148|179->148|179->148|179->148|179->148|180->149|181->150|181->150|183->152|183->152|185->154|185->154|187->156|187->156|189->158|189->158|189->158|190->159|191->160|191->160|192->161|192->161|192->161|193->162|195->164|195->164|196->165|196->165|197->166|197->166|199->168|199->168|199->168|200->169|203->172|203->172|204->173|211->180|211->180|212->181|212->181|212->181|213->182|214->183|214->183|215->184|215->184|216->185|216->185|217->186|217->186|219->188
                  -- GENERATED --
              */
          