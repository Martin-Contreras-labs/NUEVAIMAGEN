
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

object modalClienteNuevo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[Map[String,String],List[tables.Regiones],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], listRegiones: List[tables.Regiones]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
  """),format.raw/*3.3*/("""<div id='modalClienteNuevo' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>AGREGAR NUEVO CLIENTE</h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<form id="formNuevoCliente">
						<table class="table table-sm table-bordered table-condensed table-fluid">
							<tr>
								<td style="text-align:left;">"""),_display_(/*16.39*/mapDiccionario/*16.53*/.get("RUT")),format.raw/*16.64*/(""": </td>
								<td style="text-align:left;">
									<input type="text" class="form-control left"
										name="rut"
										id="clienteRut"
										autocomplete="off"
										maxlength="50"
										onchange="value = value.trim();">
								</td>
							</tr>
							<tr>
								<td style="text-align:left;">NOMBRE LARGO: </td>
								<td style="text-align:left;">
									<input type="text" class="form-control left"
										id="clienteNombre"
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
										id="clienteNickName"
										name="nickName"
										autocomplete="off"
										maxlength="50"
										required
										onchange="value = value.trim();verificaNickCliente(value);">
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
										onchange="actualizaComunasCliente(value);">
										"""),_display_(/*65.12*/for(lista <- listRegiones) yield /*65.38*/{_display_(Seq[Any](format.raw/*65.39*/("""
											"""),format.raw/*66.12*/("""<option value=""""),_display_(/*66.28*/lista/*66.33*/.codigo),format.raw/*66.40*/("""">"""),_display_(/*66.43*/lista/*66.48*/.nombre),format.raw/*66.55*/("""</option>
										""")))}),format.raw/*67.12*/("""
									"""),format.raw/*68.10*/("""</select>
								</td>
							</tr>
							<tr>
								<td style="text-align:left;">"""),_display_(/*72.39*/mapDiccionario/*72.53*/.get("Comuna")),format.raw/*72.67*/(""": </td>
								<td style="text-align:left;">
									<div id="selectComunaCliente">
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
					</form>
					<div class="noprint">
						<div class="row justify-content-md-center" >
							<div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
								<input type="button"  value="GRABAR CLIENTE" class="btn btn-success btn-sm rounded-pill btn-block" onclick = "grabarCliente();" data-dismiss='modal'>
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

	const grabarCliente = () =>"""),format.raw/*212.29*/("""{"""),format.raw/*212.30*/("""
		
		"""),format.raw/*214.3*/("""if($("#clienteNickName").val().trim()=="")"""),format.raw/*214.45*/("""{"""),format.raw/*214.46*/("""
	     	"""),format.raw/*215.8*/("""alertify.alert('El nombre corto (nickName) del cliente es obligatorio', function () """),format.raw/*215.92*/("""{"""),format.raw/*215.93*/("""
				"""),format.raw/*216.5*/("""$("#clienteNickName").val("");
				$('#modalClienteNuevo').modal('show');
	 		"""),format.raw/*218.5*/("""}"""),format.raw/*218.6*/(""");
			
			
		"""),format.raw/*221.3*/("""}"""),format.raw/*221.4*/("""else"""),format.raw/*221.8*/("""{"""),format.raw/*221.9*/("""
			"""),format.raw/*222.4*/("""var formData = new FormData(document.getElementById("formNuevoCliente"));
			$.ajax("""),format.raw/*223.11*/("""{"""),format.raw/*223.12*/("""
	            """),format.raw/*224.14*/("""url: "/clienteGrabaAjax/",
	            type: "POST",
	            method: "POST",
	            data: formData,
	            cache: false,
	            contentType: false,
		     	processData: false,
		     	success: function(data)"""),format.raw/*231.32*/("""{"""),format.raw/*231.33*/("""
		     		"""),format.raw/*232.10*/("""if(data == "error")"""),format.raw/*232.29*/("""{"""),format.raw/*232.30*/("""
		     			"""),format.raw/*233.11*/("""alertify.alert(msgError, function () """),format.raw/*233.48*/("""{"""),format.raw/*233.49*/("""
			     			"""),format.raw/*234.12*/("""location.href = "/";
			     		"""),format.raw/*235.11*/("""}"""),format.raw/*235.12*/(""");
		     		"""),format.raw/*236.10*/("""}"""),format.raw/*236.11*/("""else if(data == "existe")"""),format.raw/*236.36*/("""{"""),format.raw/*236.37*/("""
						"""),format.raw/*237.7*/("""alertify.alert("El cliente ya existe", function () """),format.raw/*237.58*/("""{"""),format.raw/*237.59*/("""
			     		"""),format.raw/*238.11*/("""}"""),format.raw/*238.12*/(""");
						 
		     		"""),format.raw/*240.10*/("""}"""),format.raw/*240.11*/("""else"""),format.raw/*240.15*/("""{"""),format.raw/*240.16*/("""
						"""),format.raw/*241.7*/("""let id_cliente = data;
						clienteGrabaAjax(id_cliente);
		     		"""),format.raw/*243.10*/("""}"""),format.raw/*243.11*/("""
		     	"""),format.raw/*244.9*/("""}"""),format.raw/*244.10*/("""
	        """),format.raw/*245.10*/("""}"""),format.raw/*245.11*/(""");
		"""),format.raw/*246.3*/("""}"""),format.raw/*246.4*/("""
	"""),format.raw/*247.2*/("""}"""),format.raw/*247.3*/("""

	"""),format.raw/*249.2*/("""const actualizaComunasCliente = (cod_region) => """),format.raw/*249.50*/("""{"""),format.raw/*249.51*/("""
		"""),format.raw/*250.3*/("""var formData = new FormData();
		formData.append('cod_region', cod_region);
	  	$.ajax("""),format.raw/*252.12*/("""{"""),format.raw/*252.13*/("""
            """),format.raw/*253.13*/("""url: "/selComuna1Ajax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(data)"""),format.raw/*260.31*/("""{"""),format.raw/*260.32*/("""
	     		"""),format.raw/*261.9*/("""if(data=="error")"""),format.raw/*261.26*/("""{"""),format.raw/*261.27*/("""
	     			"""),format.raw/*262.10*/("""alertify.alert(msgError, function () """),format.raw/*262.47*/("""{"""),format.raw/*262.48*/("""
		     			"""),format.raw/*263.11*/("""location.href = "/";
		     		"""),format.raw/*264.10*/("""}"""),format.raw/*264.11*/(""");
	     		"""),format.raw/*265.9*/("""}"""),format.raw/*265.10*/("""else"""),format.raw/*265.14*/("""{"""),format.raw/*265.15*/("""
	     			"""),format.raw/*266.10*/("""data = data.replace(/&lt;/g,"<");
					data = data.replace(/&gt;/g,">");
					data = data.replace(/&#x27;/g,"\"");
	     			document.getElementById('selectComunaCliente').innerHTML = data;
	     		"""),format.raw/*270.9*/("""}"""),format.raw/*270.10*/("""
	     	"""),format.raw/*271.8*/("""}"""),format.raw/*271.9*/("""
        """),format.raw/*272.9*/("""}"""),format.raw/*272.10*/(""");
	"""),format.raw/*273.2*/("""}"""),format.raw/*273.3*/("""
	
	"""),format.raw/*275.2*/("""const verificaNickCliente = (clienteNickName) => """),format.raw/*275.51*/("""{"""),format.raw/*275.52*/("""
		"""),format.raw/*276.3*/("""var formData = new FormData();
	  	formData.append('nickName',clienteNickName);
        $.ajax("""),format.raw/*278.16*/("""{"""),format.raw/*278.17*/("""
            """),format.raw/*279.13*/("""url: "/verificaNickClienteAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*286.36*/("""{"""),format.raw/*286.37*/("""
	     		"""),format.raw/*287.9*/("""if(respuesta=="existe")"""),format.raw/*287.32*/("""{"""),format.raw/*287.33*/("""
	     			"""),format.raw/*288.10*/("""alertify.alert('El nombre corto (nickName) del cliente ya existe, intente con otro', function () """),format.raw/*288.107*/("""{"""),format.raw/*288.108*/("""
	     				"""),format.raw/*289.11*/("""$("#clienteNickName").val("");
		     		"""),format.raw/*290.10*/("""}"""),format.raw/*290.11*/(""");
	     		"""),format.raw/*291.9*/("""}"""),format.raw/*291.10*/("""
	     		"""),format.raw/*292.9*/("""if(respuesta=="error")"""),format.raw/*292.31*/("""{"""),format.raw/*292.32*/("""
	     			"""),format.raw/*293.10*/("""alertify.alert(msgError, function () """),format.raw/*293.47*/("""{"""),format.raw/*293.48*/("""
		     			"""),format.raw/*294.11*/("""location.href = "/";
		     		"""),format.raw/*295.10*/("""}"""),format.raw/*295.11*/(""");
	     		"""),format.raw/*296.9*/("""}"""),format.raw/*296.10*/("""
	     	"""),format.raw/*297.8*/("""}"""),format.raw/*297.9*/("""
        """),format.raw/*298.9*/("""}"""),format.raw/*298.10*/(""");
	"""),format.raw/*299.2*/("""}"""),format.raw/*299.3*/("""

"""),format.raw/*301.1*/("""</script>

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
                  SOURCE: app/views/modalClienteNuevo.scala.html
                  HASH: df5d296a0068ba15fc4f17a1f007fda70d29be0f
                  MATRIX: 991->1|1158->75|1187->78|1881->745|1904->759|1936->770|3329->2136|3352->2150|3387->2164|3594->2344|3636->2370|3675->2371|3715->2383|3758->2399|3772->2404|3800->2411|3830->2414|3844->2419|3872->2426|3924->2447|3962->2457|4076->2544|4099->2558|4134->2572|6014->4424|6038->4438|6071->4449|6101->4450|6775->5096|6799->5110|6832->5121|6862->5122|8779->7010|8809->7011|8843->7017|8914->7059|8944->7060|8980->7068|9093->7152|9123->7153|9156->7158|9262->7236|9291->7237|9332->7250|9361->7251|9393->7255|9422->7256|9454->7260|9567->7344|9597->7345|9640->7359|9900->7590|9930->7591|9969->7601|10017->7620|10047->7621|10087->7632|10153->7669|10183->7670|10224->7682|10284->7713|10314->7714|10355->7726|10385->7727|10439->7752|10469->7753|10504->7760|10584->7811|10614->7812|10654->7823|10684->7824|10733->7844|10763->7845|10796->7849|10826->7850|10861->7857|10958->7925|10988->7926|11025->7935|11055->7936|11094->7946|11124->7947|11157->7952|11186->7953|11216->7955|11245->7956|11276->7959|11353->8007|11383->8008|11414->8011|11530->8098|11560->8099|11602->8112|11853->8334|11883->8335|11920->8344|11966->8361|11996->8362|12035->8372|12101->8409|12131->8410|12171->8421|12230->8451|12260->8452|12299->8463|12329->8464|12362->8468|12392->8469|12431->8479|12656->8676|12686->8677|12722->8685|12751->8686|12788->8695|12818->8696|12850->8700|12879->8701|12911->8705|12989->8754|13019->8755|13050->8758|13174->8853|13204->8854|13246->8867|13511->9103|13541->9104|13578->9113|13630->9136|13660->9137|13699->9147|13826->9244|13857->9245|13897->9256|13966->9296|13996->9297|14035->9308|14065->9309|14102->9318|14153->9340|14183->9341|14222->9351|14288->9388|14318->9389|14358->9400|14417->9430|14447->9431|14486->9442|14516->9443|14552->9451|14581->9452|14618->9461|14648->9462|14680->9466|14709->9467|14739->9469
                  LINES: 28->1|33->2|34->3|47->16|47->16|47->16|91->60|91->60|91->60|96->65|96->65|96->65|97->66|97->66|97->66|97->66|97->66|97->66|97->66|98->67|99->68|103->72|103->72|103->72|164->133|164->133|164->133|164->133|184->153|184->153|184->153|184->153|243->212|243->212|245->214|245->214|245->214|246->215|246->215|246->215|247->216|249->218|249->218|252->221|252->221|252->221|252->221|253->222|254->223|254->223|255->224|262->231|262->231|263->232|263->232|263->232|264->233|264->233|264->233|265->234|266->235|266->235|267->236|267->236|267->236|267->236|268->237|268->237|268->237|269->238|269->238|271->240|271->240|271->240|271->240|272->241|274->243|274->243|275->244|275->244|276->245|276->245|277->246|277->246|278->247|278->247|280->249|280->249|280->249|281->250|283->252|283->252|284->253|291->260|291->260|292->261|292->261|292->261|293->262|293->262|293->262|294->263|295->264|295->264|296->265|296->265|296->265|296->265|297->266|301->270|301->270|302->271|302->271|303->272|303->272|304->273|304->273|306->275|306->275|306->275|307->276|309->278|309->278|310->279|317->286|317->286|318->287|318->287|318->287|319->288|319->288|319->288|320->289|321->290|321->290|322->291|322->291|323->292|323->292|323->292|324->293|324->293|324->293|325->294|326->295|326->295|327->296|327->296|328->297|328->297|329->298|329->298|330->299|330->299|332->301
                  -- GENERATED --
              */
          