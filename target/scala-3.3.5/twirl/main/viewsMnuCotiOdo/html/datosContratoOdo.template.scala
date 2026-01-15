
package viewsMnuCotiOdo.html

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

object datosContratoOdo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template10[Map[String,String],Map[String,String],utilities.UserMnu,forms.FormContratoOdo,tables.Regiones,tables.Comunas,List[tables.Regiones],List[tables.Comunas],List[List[String]],List[tables.Proyecto],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
datos: forms.FormContratoOdo, region: tables.Regiones, comuna: tables.Comunas, listRegiones: List[tables.Regiones], listComunas: List[tables.Comunas],
listBodegas: List[List[String]], listProyectos: List[tables.Proyecto]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.1*/("""
    	
"""),_display_(/*6.2*/main("")/*6.10*/ {_display_(Seq[Any](format.raw/*6.12*/("""

	"""),_display_(/*8.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*8.51*/("""
	"""),format.raw/*9.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*10.4*/barraTitulo(mapDiccionario, "DATOS PARA HACER CONTRATO ODO", "")),format.raw/*10.68*/("""
		"""),format.raw/*11.3*/("""<form id='listaForm' method='post' action='/routes2/hacerContratoOdo/'>
			<div class="row  justify-content-md-center">
				<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<tr>
							<td style="text-align:left;">NOMBRE CLIENTE:</td>
							<td style="text-align:left;">
								<input type="hidden" name="id_cotiOdo" value=""""),_display_(/*18.56*/datos/*18.61*/.id_cotiOdo),format.raw/*18.72*/("""">
								<input type="hidden" name="id_cliente" value=""""),_display_(/*19.56*/datos/*19.61*/.id_cliente),format.raw/*19.72*/("""">
								<input type="text" class="form-control left"
									name="clienteNombre"
									autocomplete="off"
									value=""""),_display_(/*23.18*/datos/*23.23*/.clienteNombre),format.raw/*23.37*/(""""
									maxlength="100"
									required>
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">"""),_display_(/*29.38*/mapDiccionario/*29.52*/.get("RUT")),format.raw/*29.63*/(""" """),format.raw/*29.64*/("""CLIENTE:</td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="clienteRut"
									autocomplete="off"
									value=""""),_display_(/*34.18*/datos/*34.23*/.clienteRut),format.raw/*34.34*/(""""
									maxlength="50"
									required>
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">GIRO:</td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="clienteGiro"
									autocomplete="off"
									value=""""),_display_(/*45.18*/datos/*45.23*/.clienteGiro),format.raw/*45.35*/(""""
									maxlength="50">
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">DIRECCION:</td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="clienteDireccion"
									autocomplete="off"
									value=""""),_display_(/*55.18*/datos/*55.23*/.clienteDireccion),format.raw/*55.40*/(""""
									maxlength="100">
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">"""),_display_(/*60.39*/mapDiccionario/*60.53*/.get("Region")),format.raw/*60.67*/(""": </td>
							<td style="text-align: left;">
								<input type="hidden" id="clienteRegion" name="clienteRegion" value=""""),_display_(/*62.78*/datos/*62.83*/.clienteRegion),format.raw/*62.97*/("""">
								<select class="input-large form-control" id="selectRegion" style="text-align:left;width:100%" 
											onchange="actualizaComunas(value,'cod_comuna');">
									<option value=""""),_display_(/*65.26*/region/*65.32*/.codigo),format.raw/*65.39*/("""">"""),_display_(/*65.42*/region/*65.48*/.nombre),format.raw/*65.55*/("""</option>
									"""),_display_(/*66.11*/for(lista <- listRegiones) yield /*66.37*/{_display_(Seq[Any](format.raw/*66.38*/("""
								     	"""),format.raw/*67.15*/("""<option value=""""),_display_(/*67.31*/lista/*67.36*/.codigo),format.raw/*67.43*/("""">"""),_display_(/*67.46*/lista/*67.51*/.nombre),format.raw/*67.58*/("""</option>
									""")))}),format.raw/*68.11*/("""
								"""),format.raw/*69.9*/("""</select>
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">"""),_display_(/*73.39*/mapDiccionario/*73.53*/.get("Comuna")),format.raw/*73.67*/(""": </td>
							<td style="text-align: left;">
								<input type="hidden" id="clienteComuna" name="clienteComuna" value=""""),_display_(/*75.78*/datos/*75.83*/.clienteComuna),format.raw/*75.97*/("""">
								<div id="selectComuna">
									<select class="input-large form-control" style="text-align:left;width:100%">
										<option value=""""),_display_(/*78.27*/comuna/*78.33*/.codigo),format.raw/*78.40*/("""">"""),_display_(/*78.43*/comuna/*78.49*/.nombre),format.raw/*78.56*/("""</option>
										"""),_display_(/*79.12*/for(lista <- listComunas) yield /*79.37*/{_display_(Seq[Any](format.raw/*79.38*/("""
									     	"""),format.raw/*80.16*/("""<option value=""""),_display_(/*80.32*/lista/*80.37*/.nombre),format.raw/*80.44*/("""">"""),_display_(/*80.47*/lista/*80.52*/.nombre),format.raw/*80.59*/("""</option>
										""")))}),format.raw/*81.12*/("""
									"""),format.raw/*82.10*/("""</select>
								</div>
							</td>
						</tr>	
						<tr>
							<td style="text-align:left;">NOMBRE REPRESENTANTE:</td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="clienteRepresentante1"
									autocomplete="off"
									value=""""),_display_(/*92.18*/datos/*92.23*/.clienteRepresentante1),format.raw/*92.45*/(""""
									maxlength="100">
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">"""),_display_(/*97.38*/mapDiccionario/*97.52*/.get("RUT")),format.raw/*97.63*/(""" """),format.raw/*97.64*/("""REPRESENTANTE:</td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="clienteRutRepresentante1"
									autocomplete="off"
									value=""""),_display_(/*102.18*/datos/*102.23*/.clienteRutRepresentante1),format.raw/*102.48*/(""""
									maxlength="50">
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">CARGO REPRESENTANTE:</td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="clienteCargoRepresentante1"
									autocomplete="off"
									value=""""),_display_(/*112.18*/datos/*112.23*/.clienteCargoRepresentante1),format.raw/*112.50*/(""""
									maxlength="100">
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">FECHA DE CONTRATO:</td>
							<td style="text-align:left;">
								"""),_display_(if(datos.fechaContrato==null)/*119.39*/{_display_(Seq[Any](format.raw/*119.40*/("""
									"""),format.raw/*120.10*/("""<input type="date" class="form-control left"
							  			name="fechaContrato"
					        			required>
								""")))}else/*123.14*/{_display_(Seq[Any](format.raw/*123.15*/("""
									"""),format.raw/*124.10*/("""<input type="date" class="form-control left"
							  			name="fechaContrato"
							  			value=""""),_display_(/*126.21*/utilities/*126.30*/.Fechas.AAMMDD(datos.fechaContrato)),format.raw/*126.65*/(""""
					        			required>
								""")))}),format.raw/*128.10*/("""
							"""),format.raw/*129.8*/("""</td>
						</tr>
						<tr>
							<td style="text-align:left;">NUMERO CONTRATO:</td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="numeroContrato"
									autocomplete="off"
									value=""""),_display_(/*137.18*/datos/*137.23*/.numeroContrato),format.raw/*137.38*/(""""
									maxlength="50">
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">E-MAIL:</td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="clienteEMail"
									autocomplete="off"
									value=""""),_display_(/*147.18*/datos/*147.23*/.clienteEMail),format.raw/*147.36*/(""""
									maxlength="50">
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">TELEFONO:</td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="clienteFono"
									autocomplete="off"
									value=""""),_display_(/*157.18*/datos/*157.23*/.clienteFono),format.raw/*157.35*/(""""
									maxlength="50">
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">Notas adicionales<br>(Se agregan al final del contrato):</td>
							<td style="text-align:left;">
								<textarea class="form-control" rows="5"
									name="notasAlContrato" 
									autocomplete="off">"""),_display_(/*166.30*/datos/*166.35*/.notasAlContrato),format.raw/*166.51*/("""</textarea>
							</td>
						</tr>
						
						
					</table>
				</div>
			</div>
			<div class="noprint">
				<div class="row justify-content-md-center" >
					<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
						<input type="submit"  value="GENERAR CONTRATO" class="btn btn-success btn-sm rounded-pill btn-block">
					</div>
				</div>
			</div>
		</form>
	</div>

""")))}),format.raw/*184.2*/("""



"""),format.raw/*188.1*/("""<script type="text/javascript">

	let id_cliente = """"),_display_(/*190.21*/datos/*190.26*/.id_cliente),format.raw/*190.37*/("""";

	$(document).ready(function() """),format.raw/*192.31*/("""{"""),format.raw/*192.32*/("""
		  """),format.raw/*193.5*/("""$('#cod_region > option[value=""""),_display_(/*193.37*/region/*193.43*/.codigo),format.raw/*193.50*/(""""]').attr('selected', 'selected');
		  $('#cod_comuna > option[value=""""),_display_(/*194.37*/comuna/*194.43*/.codigo),format.raw/*194.50*/(""""]').attr('selected', 'selected');
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*196.2*/("""}"""),format.raw/*196.3*/(""");
	
	const actualizaComunas = (cod_region) => """),format.raw/*198.43*/("""{"""),format.raw/*198.44*/("""
		
		"""),format.raw/*200.3*/("""var elt = document.getElementById("selectRegion");
		var region = elt.options[elt.selectedIndex].text;
		$("#clienteRegion").val(region);
		
		var formData = new FormData();
		formData.append('cod_region', cod_region);
		formData.append('id_cliente',id_cliente);
	  	$.ajax("""),format.raw/*207.12*/("""{"""),format.raw/*207.13*/("""
            """),format.raw/*208.13*/("""url: "/selComuna3Ajax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(data)"""),format.raw/*215.31*/("""{"""),format.raw/*215.32*/("""
	     		"""),format.raw/*216.9*/("""if(data=="error")"""),format.raw/*216.26*/("""{"""),format.raw/*216.27*/("""
	     			"""),format.raw/*217.10*/("""alertify.alert(msgError, function () """),format.raw/*217.47*/("""{"""),format.raw/*217.48*/("""
		     			"""),format.raw/*218.11*/("""location.href = "/";
		     		"""),format.raw/*219.10*/("""}"""),format.raw/*219.11*/(""");
	     		"""),format.raw/*220.9*/("""}"""),format.raw/*220.10*/("""else"""),format.raw/*220.14*/("""{"""),format.raw/*220.15*/("""
	     			"""),format.raw/*221.10*/("""data = data.replace(/&lt;/g,"<");
					data = data.replace(/&gt;/g,">");
					data = data.replace(/&#x27;/g,"\"");
	     			document.getElementById('selectComuna').innerHTML = data;
	     		"""),format.raw/*225.9*/("""}"""),format.raw/*225.10*/("""
	     	"""),format.raw/*226.8*/("""}"""),format.raw/*226.9*/("""
        """),format.raw/*227.9*/("""}"""),format.raw/*227.10*/(""");
	"""),format.raw/*228.2*/("""}"""),format.raw/*228.3*/("""
	
	"""),format.raw/*230.2*/("""const modificarCliente = (campo) => """),format.raw/*230.38*/("""{"""),format.raw/*230.39*/("""
		"""),format.raw/*231.3*/("""let valor = $("#"+campo).val();
		var formData = new FormData();
	  	formData.append('campo',campo);
	  	formData.append('id_cliente',id_cliente);
	  	formData.append('valor',valor);
        $.ajax("""),format.raw/*236.16*/("""{"""),format.raw/*236.17*/("""
            """),format.raw/*237.13*/("""url: "/modificaClientePorCampoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*244.36*/("""{"""),format.raw/*244.37*/("""}"""),format.raw/*244.38*/("""
        """),format.raw/*245.9*/("""}"""),format.raw/*245.10*/(""");
	"""),format.raw/*246.2*/("""}"""),format.raw/*246.3*/("""
	

"""),format.raw/*249.1*/("""</script>

		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,datos:forms.FormContratoOdo,region:tables.Regiones,comuna:tables.Comunas,listRegiones:List[tables.Regiones],listComunas:List[tables.Comunas],listBodegas:List[List[String]],listProyectos:List[tables.Proyecto]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,datos,region,comuna,listRegiones,listComunas,listBodegas,listProyectos)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,forms.FormContratoOdo,tables.Regiones,tables.Comunas,List[tables.Regiones],List[tables.Comunas],List[List[String]],List[tables.Proyecto]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,datos,region,comuna,listRegiones,listComunas,listBodegas,listProyectos) => apply(mapDiccionario,mapPermiso,userMnu,datos,region,comuna,listRegiones,listComunas,listBodegas,listProyectos)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotiOdo/datosContratoOdo.scala.html
                  HASH: e142674dc405036aac24f2021ab41f6f7ca3e138
                  MATRIX: 1153->1|1565->320|1598->328|1614->336|1653->338|1682->342|1750->390|1778->392|1855->443|1940->507|1970->510|2411->924|2425->929|2457->940|2542->998|2556->1003|2588->1014|2746->1145|2760->1150|2795->1164|2941->1283|2964->1297|2996->1308|3025->1309|3228->1485|3242->1490|3274->1501|3592->1792|3606->1797|3639->1809|3949->2092|3963->2097|4001->2114|4130->2216|4153->2230|4188->2244|4338->2367|4352->2372|4387->2386|4606->2578|4621->2584|4649->2591|4679->2594|4694->2600|4722->2607|4769->2627|4811->2653|4850->2654|4893->2669|4936->2685|4950->2690|4978->2697|5008->2700|5022->2705|5050->2712|5101->2732|5137->2741|5248->2825|5271->2839|5306->2853|5456->2976|5470->2981|5505->2995|5679->3142|5694->3148|5722->3155|5752->3158|5767->3164|5795->3171|5843->3192|5884->3217|5923->3218|5967->3234|6010->3250|6024->3255|6052->3262|6082->3265|6096->3270|6124->3277|6176->3298|6214->3308|6539->3606|6553->3611|6596->3633|6724->3734|6747->3748|6779->3759|6808->3760|7032->3956|7047->3961|7094->3986|7425->4289|7440->4294|7489->4321|7716->4520|7756->4521|7795->4531|7932->4648|7972->4649|8011->4659|8137->4757|8156->4766|8213->4801|8282->4838|8318->4846|8599->5099|8614->5104|8651->5119|8955->5395|8970->5400|9005->5413|9310->5690|9325->5695|9359->5707|9695->6015|9710->6020|9748->6036|10155->6412|10187->6416|10268->6469|10283->6474|10316->6485|10379->6519|10409->6520|10442->6525|10502->6557|10518->6563|10547->6570|10646->6641|10662->6647|10691->6654|10824->6759|10853->6760|10929->6807|10959->6808|10993->6814|11296->7088|11326->7089|11368->7102|11619->7324|11649->7325|11686->7334|11732->7351|11762->7352|11801->7362|11867->7399|11897->7400|11937->7411|11996->7441|12026->7442|12065->7453|12095->7454|12128->7458|12158->7459|12197->7469|12415->7659|12445->7660|12481->7668|12510->7669|12547->7678|12577->7679|12609->7683|12638->7684|12670->7688|12735->7724|12765->7725|12796->7728|13023->7926|13053->7927|13095->7940|13364->8180|13394->8181|13424->8182|13461->8191|13491->8192|13523->8196|13552->8197|13584->8201
                  LINES: 28->1|35->4|37->6|37->6|37->6|39->8|39->8|40->9|41->10|41->10|42->11|49->18|49->18|49->18|50->19|50->19|50->19|54->23|54->23|54->23|60->29|60->29|60->29|60->29|65->34|65->34|65->34|76->45|76->45|76->45|86->55|86->55|86->55|91->60|91->60|91->60|93->62|93->62|93->62|96->65|96->65|96->65|96->65|96->65|96->65|97->66|97->66|97->66|98->67|98->67|98->67|98->67|98->67|98->67|98->67|99->68|100->69|104->73|104->73|104->73|106->75|106->75|106->75|109->78|109->78|109->78|109->78|109->78|109->78|110->79|110->79|110->79|111->80|111->80|111->80|111->80|111->80|111->80|111->80|112->81|113->82|123->92|123->92|123->92|128->97|128->97|128->97|128->97|133->102|133->102|133->102|143->112|143->112|143->112|150->119|150->119|151->120|154->123|154->123|155->124|157->126|157->126|157->126|159->128|160->129|168->137|168->137|168->137|178->147|178->147|178->147|188->157|188->157|188->157|197->166|197->166|197->166|215->184|219->188|221->190|221->190|221->190|223->192|223->192|224->193|224->193|224->193|224->193|225->194|225->194|225->194|227->196|227->196|229->198|229->198|231->200|238->207|238->207|239->208|246->215|246->215|247->216|247->216|247->216|248->217|248->217|248->217|249->218|250->219|250->219|251->220|251->220|251->220|251->220|252->221|256->225|256->225|257->226|257->226|258->227|258->227|259->228|259->228|261->230|261->230|261->230|262->231|267->236|267->236|268->237|275->244|275->244|275->244|276->245|276->245|277->246|277->246|280->249
                  -- GENERATED --
              */
          