
package views.html

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

object modalPropietarioNuevo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[Map[String,String],List[tables.Regiones],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], listRegiones: List[tables.Regiones]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
  """),format.raw/*3.3*/("""<div id='modalPropietarioNuevo' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>AGREGAR NUEVO PROPIETARIO</h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<form id="formNuevoPropietario">
						<table class="table table-sm table-bordered table-condensed table-fluid">
							<tr>
								<td style="text-align:left;">"""),_display_(/*16.39*/mapDiccionario/*16.53*/.get("RUT")),format.raw/*16.64*/(""": </td>
								<td style="text-align:left;">
									<input type="text" class="form-control left"
										name="rut"
										id="propietarioRut"
										autocomplete="off"
										maxlength="50"
										onchange="value = value.trim();">
								</td>
							</tr>
							<tr>
								<td style="text-align:left;">NOMBRE LARGO: </td>
								<td style="text-align:left;">
									<input type="text" class="form-control left"
										id="propietarioNombre"
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
										id="propietarioNickName"
										name="nickName"
										autocomplete="off"
										maxlength="50"
										required
										onchange="value = value.trim();verificaNickPropietario(value);">
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
								<td style="text-align:left;">"""),_display_(/*60.39*/mapDiccionario/*60.53*/.get("Region")),format.raw/*60.67*/(""": </td>
								<td style="text-align:left;">
									<select class="custom-select" 
										name="cod_region" 
										onchange="actualizaComunasPropietario(value);">
										"""),_display_(/*65.12*/for(lista <- listRegiones) yield /*65.38*/{_display_(Seq[Any](format.raw/*65.39*/("""
											"""),format.raw/*66.12*/("""<option value=""""),_display_(/*66.28*/lista/*66.33*/.codigo),format.raw/*66.40*/("""">"""),_display_(/*66.43*/lista/*66.48*/.nombre),format.raw/*66.55*/("""</option>
										""")))}),format.raw/*67.12*/("""
									"""),format.raw/*68.10*/("""</select>
								</td>
							</tr>
							<tr>
								<td style="text-align:left;">"""),_display_(/*72.39*/mapDiccionario/*72.53*/.get("Comuna")),format.raw/*72.67*/(""": </td>
								<td style="text-align:left;">
									<div id="selectComunaPropietario">
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
								<td style="text-align:left;">CONTACTO: </td>
								<td style="text-align:left;">
									<input type="text" class="form-control left"
										name="contactoFactura"
										autocomplete="off"
										maxlength="50"
										onchange="value = value.trim();">
								</td>
							</tr>
							<tr>
								<td style="text-align:left;">"""),_display_(/*133.39*/mapDiccionario/*133.53*/.get("RUT")),format.raw/*133.64*/(""" """),format.raw/*133.65*/("""REPRES 1: </td>
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
								<td style="text-align:left;">"""),_display_(/*153.39*/mapDiccionario/*153.53*/.get("RUT")),format.raw/*153.64*/(""" """),format.raw/*153.65*/("""REPRES 2: </td>
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
						</table>
					</form>
					<div class="noprint">
						<div class="row justify-content-md-center" >
							<div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
								<input type="button"  value="GRABAR PROPIETARIO" class="btn btn-success btn-sm rounded-pill btn-block" onclick = "grabarPropietario();" data-dismiss='modal'>
							</div>
							<div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
								<button type='button' class='btn btn-sm  btn-warning rounded-pill btn-block' data-dismiss='modal'>Cancelar</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
<script type="text/javascript">

	const grabarPropietario = () =>"""),format.raw/*192.33*/("""{"""),format.raw/*192.34*/("""
		"""),format.raw/*193.3*/("""if($("#propietarioNickName").val().trim()=="")"""),format.raw/*193.49*/("""{"""),format.raw/*193.50*/("""
	     	"""),format.raw/*194.8*/("""alertify.alert('El nombre corto (nickName) del propietario es obligatorio', function () """),format.raw/*194.96*/("""{"""),format.raw/*194.97*/("""
				"""),format.raw/*195.5*/("""$("#propietarioNickName").val("");
				$('#modalPropietarioNuevo').modal('show');
	 		"""),format.raw/*197.5*/("""}"""),format.raw/*197.6*/(""");
		"""),format.raw/*198.3*/("""}"""),format.raw/*198.4*/("""else"""),format.raw/*198.8*/("""{"""),format.raw/*198.9*/("""
			"""),format.raw/*199.4*/("""var formData = new FormData(document.getElementById("formNuevoPropietario"));
			$.ajax("""),format.raw/*200.11*/("""{"""),format.raw/*200.12*/("""
	            """),format.raw/*201.14*/("""url: "/clienteGrabaAjax/",
	            type: "POST",
	            method: "POST",
	            data: formData,
	            cache: false,
	            contentType: false,
		     	processData: false,
		     	success: function(data)"""),format.raw/*208.32*/("""{"""),format.raw/*208.33*/("""
		     		"""),format.raw/*209.10*/("""if(data == "error")"""),format.raw/*209.29*/("""{"""),format.raw/*209.30*/("""
		     			"""),format.raw/*210.11*/("""alertify.alert(msgError, function () """),format.raw/*210.48*/("""{"""),format.raw/*210.49*/("""
			     			"""),format.raw/*211.12*/("""location.href = "/";
			     		"""),format.raw/*212.11*/("""}"""),format.raw/*212.12*/(""");
		     		"""),format.raw/*213.10*/("""}"""),format.raw/*213.11*/("""else if(data == "existe")"""),format.raw/*213.36*/("""{"""),format.raw/*213.37*/("""
						"""),format.raw/*214.7*/("""alertify.alert("El propietario ya existe", function () """),format.raw/*214.62*/("""{"""),format.raw/*214.63*/("""
			     		"""),format.raw/*215.11*/("""}"""),format.raw/*215.12*/(""");
						 
		     		"""),format.raw/*217.10*/("""}"""),format.raw/*217.11*/("""else"""),format.raw/*217.15*/("""{"""),format.raw/*217.16*/("""
						"""),format.raw/*218.7*/("""let id_propietario = data;
						propietarioGrabaAjax(id_propietario);
		     		"""),format.raw/*220.10*/("""}"""),format.raw/*220.11*/("""
		     	"""),format.raw/*221.9*/("""}"""),format.raw/*221.10*/("""
	        """),format.raw/*222.10*/("""}"""),format.raw/*222.11*/(""");
		"""),format.raw/*223.3*/("""}"""),format.raw/*223.4*/("""
	"""),format.raw/*224.2*/("""}"""),format.raw/*224.3*/("""

	"""),format.raw/*226.2*/("""const actualizaComunasPropietario = (cod_region) => """),format.raw/*226.54*/("""{"""),format.raw/*226.55*/("""
		"""),format.raw/*227.3*/("""var formData = new FormData();
		formData.append('cod_region', cod_region);
	  	$.ajax("""),format.raw/*229.12*/("""{"""),format.raw/*229.13*/("""
            """),format.raw/*230.13*/("""url: "/selComuna1Ajax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(data)"""),format.raw/*237.31*/("""{"""),format.raw/*237.32*/("""
	     		"""),format.raw/*238.9*/("""if(data=="error")"""),format.raw/*238.26*/("""{"""),format.raw/*238.27*/("""
	     			"""),format.raw/*239.10*/("""alertify.alert(msgError, function () """),format.raw/*239.47*/("""{"""),format.raw/*239.48*/("""
		     			"""),format.raw/*240.11*/("""location.href = "/";
		     		"""),format.raw/*241.10*/("""}"""),format.raw/*241.11*/(""");
	     		"""),format.raw/*242.9*/("""}"""),format.raw/*242.10*/("""else"""),format.raw/*242.14*/("""{"""),format.raw/*242.15*/("""
	     			"""),format.raw/*243.10*/("""data = data.replace(/&lt;/g,"<");
					data = data.replace(/&gt;/g,">");
					data = data.replace(/&#x27;/g,"\"");
	     			document.getElementById('selectComunaPropietario').innerHTML = data;
	     		"""),format.raw/*247.9*/("""}"""),format.raw/*247.10*/("""
	     	"""),format.raw/*248.8*/("""}"""),format.raw/*248.9*/("""
        """),format.raw/*249.9*/("""}"""),format.raw/*249.10*/(""");
	"""),format.raw/*250.2*/("""}"""),format.raw/*250.3*/("""
	
	"""),format.raw/*252.2*/("""const verificaNickPropietario = (propietarioNickName) => """),format.raw/*252.59*/("""{"""),format.raw/*252.60*/("""
		"""),format.raw/*253.3*/("""var formData = new FormData();
	  	formData.append('nickName',propietarioNickName);
        $.ajax("""),format.raw/*255.16*/("""{"""),format.raw/*255.17*/("""
            """),format.raw/*256.13*/("""url: "/verificaNickClienteAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*263.36*/("""{"""),format.raw/*263.37*/("""
	     		"""),format.raw/*264.9*/("""if(respuesta=="existe")"""),format.raw/*264.32*/("""{"""),format.raw/*264.33*/("""
	     			"""),format.raw/*265.10*/("""alertify.alert('El nombre corto (nickName) del propietario ya existe, intente con otro', function () """),format.raw/*265.111*/("""{"""),format.raw/*265.112*/("""
	     				"""),format.raw/*266.11*/("""$("#propietarioNickName").val("");
		     		"""),format.raw/*267.10*/("""}"""),format.raw/*267.11*/(""");
	     		"""),format.raw/*268.9*/("""}"""),format.raw/*268.10*/("""
	     		"""),format.raw/*269.9*/("""if(respuesta=="error")"""),format.raw/*269.31*/("""{"""),format.raw/*269.32*/("""
	     			"""),format.raw/*270.10*/("""alertify.alert(msgError, function () """),format.raw/*270.47*/("""{"""),format.raw/*270.48*/("""
		     			"""),format.raw/*271.11*/("""location.href = "/";
		     		"""),format.raw/*272.10*/("""}"""),format.raw/*272.11*/(""");
	     		"""),format.raw/*273.9*/("""}"""),format.raw/*273.10*/("""
	     	"""),format.raw/*274.8*/("""}"""),format.raw/*274.9*/("""
        """),format.raw/*275.9*/("""}"""),format.raw/*275.10*/(""");
	"""),format.raw/*276.2*/("""}"""),format.raw/*276.3*/("""

"""),format.raw/*278.1*/("""</script>

"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],listRegiones:List[tables.Regiones]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,listRegiones)

  def f:((Map[String,String],List[tables.Regiones]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,listRegiones) => apply(mapDiccionario,listRegiones)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/modalPropietarioNuevo.scala.html
                  HASH: 4cfeba7576b7cf107d10c0345631ad96eabd156c
                  MATRIX: 995->1|1162->75|1191->78|1897->757|1920->771|1952->782|3361->2164|3384->2178|3419->2192|3630->2376|3672->2402|3711->2403|3751->2415|3794->2431|3808->2436|3836->2443|3866->2446|3880->2451|3908->2458|3960->2479|3998->2489|4112->2576|4135->2590|4170->2604|6054->4460|6078->4474|6111->4485|6141->4486|6815->5132|6839->5146|6872->5157|6902->5158|8197->6424|8227->6425|8258->6428|8333->6474|8363->6475|8399->6483|8516->6571|8546->6572|8579->6577|8693->6663|8722->6664|8755->6669|8784->6670|8816->6674|8845->6675|8877->6679|8994->6767|9024->6768|9067->6782|9327->7013|9357->7014|9396->7024|9444->7043|9474->7044|9514->7055|9580->7092|9610->7093|9651->7105|9711->7136|9741->7137|9782->7149|9812->7150|9866->7175|9896->7176|9931->7183|10015->7238|10045->7239|10085->7250|10115->7251|10164->7271|10194->7272|10227->7276|10257->7277|10292->7284|10401->7364|10431->7365|10468->7374|10498->7375|10537->7385|10567->7386|10600->7391|10629->7392|10659->7394|10688->7395|10719->7398|10800->7450|10830->7451|10861->7454|10977->7541|11007->7542|11049->7555|11300->7777|11330->7778|11367->7787|11413->7804|11443->7805|11482->7815|11548->7852|11578->7853|11618->7864|11677->7894|11707->7895|11746->7906|11776->7907|11809->7911|11839->7912|11878->7922|12107->8123|12137->8124|12173->8132|12202->8133|12239->8142|12269->8143|12301->8147|12330->8148|12362->8152|12448->8209|12478->8210|12509->8213|12637->8312|12667->8313|12709->8326|12974->8562|13004->8563|13041->8572|13093->8595|13123->8596|13162->8606|13293->8707|13324->8708|13364->8719|13437->8763|13467->8764|13506->8775|13536->8776|13573->8785|13624->8807|13654->8808|13693->8818|13759->8855|13789->8856|13829->8867|13888->8897|13918->8898|13957->8909|13987->8910|14023->8918|14052->8919|14089->8928|14119->8929|14151->8933|14180->8934|14210->8936
                  LINES: 28->1|33->2|34->3|47->16|47->16|47->16|91->60|91->60|91->60|96->65|96->65|96->65|97->66|97->66|97->66|97->66|97->66|97->66|97->66|98->67|99->68|103->72|103->72|103->72|164->133|164->133|164->133|164->133|184->153|184->153|184->153|184->153|223->192|223->192|224->193|224->193|224->193|225->194|225->194|225->194|226->195|228->197|228->197|229->198|229->198|229->198|229->198|230->199|231->200|231->200|232->201|239->208|239->208|240->209|240->209|240->209|241->210|241->210|241->210|242->211|243->212|243->212|244->213|244->213|244->213|244->213|245->214|245->214|245->214|246->215|246->215|248->217|248->217|248->217|248->217|249->218|251->220|251->220|252->221|252->221|253->222|253->222|254->223|254->223|255->224|255->224|257->226|257->226|257->226|258->227|260->229|260->229|261->230|268->237|268->237|269->238|269->238|269->238|270->239|270->239|270->239|271->240|272->241|272->241|273->242|273->242|273->242|273->242|274->243|278->247|278->247|279->248|279->248|280->249|280->249|281->250|281->250|283->252|283->252|283->252|284->253|286->255|286->255|287->256|294->263|294->263|295->264|295->264|295->264|296->265|296->265|296->265|297->266|298->267|298->267|299->268|299->268|300->269|300->269|300->269|301->270|301->270|301->270|302->271|303->272|303->272|304->273|304->273|305->274|305->274|306->275|306->275|307->276|307->276|309->278
                  -- GENERATED --
              */
          