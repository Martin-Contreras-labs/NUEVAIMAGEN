
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

object modalProveedorNuevo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[Map[String,String],List[tables.Regiones],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], listRegiones: List[tables.Regiones]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
  """),format.raw/*3.3*/("""<div id='modalProveedorNuevo' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>AGREGAR NUEVO PROVEEDOR</h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<form id="formNuevoProveedor">
						<table class="table table-sm table-bordered table-condensed table-fluid">
							<tr>
								<td style="text-align:left;">"""),_display_(/*16.39*/mapDiccionario/*16.53*/.get("RUT")),format.raw/*16.64*/(""": </td>
								<td style="text-align:left;">
									<input type="text" class="form-control left"
										name="rut"
										id="proveedorRut"
										autocomplete="off"
										maxlength="50"
										onchange="value = value.trim();">
								</td>
							</tr>
							<tr>
								<td style="text-align:left;">NOMBRE LARGO: </td>
								<td style="text-align:left;">
									<input type="text" class="form-control left"
										id="proveedorNombre"
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
										id="proveedorNickName"
										name="nickName"
										autocomplete="off"
										maxlength="50"
										required
										onchange="value = value.trim();verificaNickProveedor(value);">
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
										onchange="actualizaComunasProveedor(value);">
										"""),_display_(/*65.12*/for(lista <- listRegiones) yield /*65.38*/{_display_(Seq[Any](format.raw/*65.39*/("""
											"""),format.raw/*66.12*/("""<option value=""""),_display_(/*66.28*/lista/*66.33*/.codigo),format.raw/*66.40*/("""">"""),_display_(/*66.43*/lista/*66.48*/.nombre),format.raw/*66.55*/("""</option>
										""")))}),format.raw/*67.12*/("""
									"""),format.raw/*68.10*/("""</select>
								</td>
							</tr>
							<tr>
								<td style="text-align:left;">"""),_display_(/*72.39*/mapDiccionario/*72.53*/.get("Comuna")),format.raw/*72.67*/(""": </td>
								<td style="text-align:left;">
									<div id="selectComunaProveedor">
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
					</form>
					<div class="noprint">
						<div class="row justify-content-md-center" >
							<div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
								<input type="button"  value="GRABAR PROVEEDOR" class="btn btn-success btn-sm rounded-pill btn-block" onclick = "grabarProveedor();" data-dismiss='modal'>
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

	const grabarProveedor = () =>"""),format.raw/*132.31*/("""{"""),format.raw/*132.32*/("""
		"""),format.raw/*133.3*/("""if($("#proveedorNickName").val().trim()=="")"""),format.raw/*133.47*/("""{"""),format.raw/*133.48*/("""
	     	"""),format.raw/*134.8*/("""alertify.alert('El nombre corto (nickName) del proveedor es obligatorio', function () """),format.raw/*134.94*/("""{"""),format.raw/*134.95*/("""
				"""),format.raw/*135.5*/("""$("#proveedorNickName").val("");
				$('#modalProveedorNuevo').modal('show');
	 		"""),format.raw/*137.5*/("""}"""),format.raw/*137.6*/(""");
		"""),format.raw/*138.3*/("""}"""),format.raw/*138.4*/("""else"""),format.raw/*138.8*/("""{"""),format.raw/*138.9*/("""
			"""),format.raw/*139.4*/("""var formData = new FormData(document.getElementById("formNuevoProveedor"));
			$.ajax("""),format.raw/*140.11*/("""{"""),format.raw/*140.12*/("""
	            """),format.raw/*141.14*/("""url: "/proveedorGrabaAjax/",
	            type: "POST",
	            method: "POST",
	            data: formData,
	            cache: false,
	            contentType: false,
		     	processData: false,
		     	success: function(data)"""),format.raw/*148.32*/("""{"""),format.raw/*148.33*/("""
		     		"""),format.raw/*149.10*/("""if(data == "error")"""),format.raw/*149.29*/("""{"""),format.raw/*149.30*/("""
		     			"""),format.raw/*150.11*/("""alertify.alert(msgError, function () """),format.raw/*150.48*/("""{"""),format.raw/*150.49*/("""
			     			"""),format.raw/*151.12*/("""location.href = "/";
			     		"""),format.raw/*152.11*/("""}"""),format.raw/*152.12*/(""");
		     		"""),format.raw/*153.10*/("""}"""),format.raw/*153.11*/("""else if(data == "existe")"""),format.raw/*153.36*/("""{"""),format.raw/*153.37*/("""
						"""),format.raw/*154.7*/("""alertify.alert("El proveedor ya existe", function () """),format.raw/*154.60*/("""{"""),format.raw/*154.61*/("""
			     		"""),format.raw/*155.11*/("""}"""),format.raw/*155.12*/(""");
						 
		     		"""),format.raw/*157.10*/("""}"""),format.raw/*157.11*/("""else"""),format.raw/*157.15*/("""{"""),format.raw/*157.16*/("""
						"""),format.raw/*158.7*/("""let id_proveedor = data;
						proveedorGrabaAjax(id_proveedor);
		     		"""),format.raw/*160.10*/("""}"""),format.raw/*160.11*/("""
		     	"""),format.raw/*161.9*/("""}"""),format.raw/*161.10*/("""
	        """),format.raw/*162.10*/("""}"""),format.raw/*162.11*/(""");
		"""),format.raw/*163.3*/("""}"""),format.raw/*163.4*/("""
	"""),format.raw/*164.2*/("""}"""),format.raw/*164.3*/("""

	"""),format.raw/*166.2*/("""const actualizaComunasProveedor = (cod_region) => """),format.raw/*166.52*/("""{"""),format.raw/*166.53*/("""
		"""),format.raw/*167.3*/("""var formData = new FormData();
		formData.append('cod_region', cod_region);
	  	$.ajax("""),format.raw/*169.12*/("""{"""),format.raw/*169.13*/("""
            """),format.raw/*170.13*/("""url: "/selComuna1Ajax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(data)"""),format.raw/*177.31*/("""{"""),format.raw/*177.32*/("""
	     		"""),format.raw/*178.9*/("""if(data=="error")"""),format.raw/*178.26*/("""{"""),format.raw/*178.27*/("""
	     			"""),format.raw/*179.10*/("""alertify.alert(msgError, function () """),format.raw/*179.47*/("""{"""),format.raw/*179.48*/("""
		     			"""),format.raw/*180.11*/("""location.href = "/";
		     		"""),format.raw/*181.10*/("""}"""),format.raw/*181.11*/(""");
	     		"""),format.raw/*182.9*/("""}"""),format.raw/*182.10*/("""else"""),format.raw/*182.14*/("""{"""),format.raw/*182.15*/("""
	     			"""),format.raw/*183.10*/("""data = data.replace(/&lt;/g,"<");
					data = data.replace(/&gt;/g,">");
					data = data.replace(/&#x27;/g,"\"");
	     			document.getElementById('selectComunaProveedor').innerHTML = data;
	     		"""),format.raw/*187.9*/("""}"""),format.raw/*187.10*/("""
	     	"""),format.raw/*188.8*/("""}"""),format.raw/*188.9*/("""
        """),format.raw/*189.9*/("""}"""),format.raw/*189.10*/(""");
	"""),format.raw/*190.2*/("""}"""),format.raw/*190.3*/("""
	
	"""),format.raw/*192.2*/("""const verificaNickProveedor = (proveedorNickName) => """),format.raw/*192.55*/("""{"""),format.raw/*192.56*/("""
		"""),format.raw/*193.3*/("""var formData = new FormData();
	  	formData.append('nickName',proveedorNickName);
        $.ajax("""),format.raw/*195.16*/("""{"""),format.raw/*195.17*/("""
            """),format.raw/*196.13*/("""url: "/verificaNickProveedorAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*203.36*/("""{"""),format.raw/*203.37*/("""
	     		"""),format.raw/*204.9*/("""if(respuesta=="existe")"""),format.raw/*204.32*/("""{"""),format.raw/*204.33*/("""
	     			"""),format.raw/*205.10*/("""alertify.alert('El nombre corto (nickName) del proveedor ya existe, intente con otro', function () """),format.raw/*205.109*/("""{"""),format.raw/*205.110*/("""
	     				"""),format.raw/*206.11*/("""$("#proveedorNickName").val("");
		     		"""),format.raw/*207.10*/("""}"""),format.raw/*207.11*/(""");
	     		"""),format.raw/*208.9*/("""}"""),format.raw/*208.10*/("""
	     		"""),format.raw/*209.9*/("""if(respuesta=="error")"""),format.raw/*209.31*/("""{"""),format.raw/*209.32*/("""
	     			"""),format.raw/*210.10*/("""alertify.alert(msgError, function () """),format.raw/*210.47*/("""{"""),format.raw/*210.48*/("""
		     			"""),format.raw/*211.11*/("""location.href = "/";
		     		"""),format.raw/*212.10*/("""}"""),format.raw/*212.11*/(""");
	     		"""),format.raw/*213.9*/("""}"""),format.raw/*213.10*/("""
	     	"""),format.raw/*214.8*/("""}"""),format.raw/*214.9*/("""
        """),format.raw/*215.9*/("""}"""),format.raw/*215.10*/(""");
	"""),format.raw/*216.2*/("""}"""),format.raw/*216.3*/("""

"""),format.raw/*218.1*/("""</script>

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
                  SOURCE: app/views/modalProveedorNuevo.scala.html
                  HASH: fc72e541f25e7e7c07e6cd5af8a77004968a2103
                  MATRIX: 993->1|1160->75|1189->78|1889->751|1912->765|1944->776|3345->2150|3368->2164|3403->2178|3612->2360|3654->2386|3693->2387|3733->2399|3776->2415|3790->2420|3818->2427|3848->2430|3862->2435|3890->2442|3942->2463|3980->2473|4094->2560|4117->2574|4152->2588|6046->4453|6076->4454|6107->4457|6180->4501|6210->4502|6246->4510|6361->4596|6391->4597|6424->4602|6534->4684|6563->4685|6596->4690|6625->4691|6657->4695|6686->4696|6718->4700|6833->4786|6863->4787|6906->4801|7168->5034|7198->5035|7237->5045|7285->5064|7315->5065|7355->5076|7421->5113|7451->5114|7492->5126|7552->5157|7582->5158|7623->5170|7653->5171|7707->5196|7737->5197|7772->5204|7854->5257|7884->5258|7924->5269|7954->5270|8003->5290|8033->5291|8066->5295|8096->5296|8131->5303|8234->5377|8264->5378|8301->5387|8331->5388|8370->5398|8400->5399|8433->5404|8462->5405|8492->5407|8521->5408|8552->5411|8631->5461|8661->5462|8692->5465|8808->5552|8838->5553|8880->5566|9131->5788|9161->5789|9198->5798|9244->5815|9274->5816|9313->5826|9379->5863|9409->5864|9449->5875|9508->5905|9538->5906|9577->5917|9607->5918|9640->5922|9670->5923|9709->5933|9936->6132|9966->6133|10002->6141|10031->6142|10068->6151|10098->6152|10130->6156|10159->6157|10191->6161|10273->6214|10303->6215|10334->6218|10460->6315|10490->6316|10532->6329|10799->6567|10829->6568|10866->6577|10918->6600|10948->6601|10987->6611|11116->6710|11147->6711|11187->6722|11258->6764|11288->6765|11327->6776|11357->6777|11394->6786|11445->6808|11475->6809|11514->6819|11580->6856|11610->6857|11650->6868|11709->6898|11739->6899|11778->6910|11808->6911|11844->6919|11873->6920|11910->6929|11940->6930|11972->6934|12001->6935|12031->6937
                  LINES: 28->1|33->2|34->3|47->16|47->16|47->16|91->60|91->60|91->60|96->65|96->65|96->65|97->66|97->66|97->66|97->66|97->66|97->66|97->66|98->67|99->68|103->72|103->72|103->72|163->132|163->132|164->133|164->133|164->133|165->134|165->134|165->134|166->135|168->137|168->137|169->138|169->138|169->138|169->138|170->139|171->140|171->140|172->141|179->148|179->148|180->149|180->149|180->149|181->150|181->150|181->150|182->151|183->152|183->152|184->153|184->153|184->153|184->153|185->154|185->154|185->154|186->155|186->155|188->157|188->157|188->157|188->157|189->158|191->160|191->160|192->161|192->161|193->162|193->162|194->163|194->163|195->164|195->164|197->166|197->166|197->166|198->167|200->169|200->169|201->170|208->177|208->177|209->178|209->178|209->178|210->179|210->179|210->179|211->180|212->181|212->181|213->182|213->182|213->182|213->182|214->183|218->187|218->187|219->188|219->188|220->189|220->189|221->190|221->190|223->192|223->192|223->192|224->193|226->195|226->195|227->196|234->203|234->203|235->204|235->204|235->204|236->205|236->205|236->205|237->206|238->207|238->207|239->208|239->208|240->209|240->209|240->209|241->210|241->210|241->210|242->211|243->212|243->212|244->213|244->213|245->214|245->214|246->215|246->215|247->216|247->216|249->218
                  -- GENERATED --
              */
          