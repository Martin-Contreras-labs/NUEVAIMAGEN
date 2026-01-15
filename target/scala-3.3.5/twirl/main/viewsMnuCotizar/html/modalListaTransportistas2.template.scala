
package viewsMnuCotizar.html

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

object modalListaTransportistas2 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[Map[String,String],List[tables.Transportista],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], listaTransporte: List[tables.Transportista]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""	
"""),format.raw/*3.1*/("""<div id="modalListaTransporte" class="modal" data-backdrop="static" data-keyboard="false">
   	<div class='modal-dialog modal-dialog-scrollable' style="max-width: 80% !important;" role='document'>
	   	<div class='modal-content'>
	   		<div class='modal-header'>
				<h5 class="modal-title">LISTA DE SELECCION DE TRANSPORTISTAS</h5>
			    <div align="center">
			           	<button type="button" class='btn btn-sm btn-success' data-dismiss="modal" onclick="$('#modalNuevoTransportista').modal('show');">AGREGAR TRANSPORTISTA</button>
			    </div>
			</div>
		     <div class="modal-body">
		     
				<div id="detalleTablaListaTransportista">
			     	<table id="tablaListaTransporte" class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<TR> 
								   <th>id</th>
							       <th>"""),_display_(/*19.20*/mapDiccionario/*19.34*/.get("RUT")),format.raw/*19.45*/(""" """),format.raw/*19.46*/("""Conductor</th>
							       <th>Conductor</th>
							       <th>"""),_display_(/*21.20*/mapDiccionario/*21.34*/.get("RUT")),format.raw/*21.45*/(""" """),format.raw/*21.46*/("""Empresa</th>
							       <th>Empresa</th>
							       <th>Vehiculo</th>
							       <th>"""),_display_(/*24.20*/mapDiccionario/*24.34*/.get("Patente")),format.raw/*24.49*/("""</th>
							       <th>Nro Inscripcion</th>
							       <th>Nro Licencia</th>
							       <th>Fono Contacto</th>
							       <th>SELECT</th>
							       <th>EDIT</th>
							       <th>DEL</th>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*34.9*/for(lista <- listaTransporte) yield /*34.38*/{_display_(Seq[Any](format.raw/*34.39*/("""
								"""),format.raw/*35.9*/("""<TR>
									<td style="text-align:left;">"""),_display_(/*36.40*/lista/*36.45*/.getId()),format.raw/*36.53*/("""</td>
									<td style="text-align:left;">"""),_display_(/*37.40*/lista/*37.45*/.getRutConductor()),format.raw/*37.63*/("""</td>
									<td style="text-align:left;">"""),_display_(/*38.40*/lista/*38.45*/.getConductor()),format.raw/*38.60*/("""</td>
									<td style="text-align:left;">"""),_display_(/*39.40*/lista/*39.45*/.getRutEmpresa()),format.raw/*39.61*/("""</td>
									<td style="text-align:left;">"""),_display_(/*40.40*/lista/*40.45*/.getEmpresa()),format.raw/*40.58*/("""</td>
									<td style="text-align:left;">"""),_display_(/*41.40*/lista/*41.45*/.getVehiculo()),format.raw/*41.59*/("""</td>
									<td style="text-align:left;">"""),_display_(/*42.40*/lista/*42.45*/.getPatente()),format.raw/*42.58*/("""</td>
									<td style="text-align:left;">"""),_display_(/*43.40*/lista/*43.45*/.getInscripcion()),format.raw/*43.62*/("""</td>
									<td style="text-align:left;">"""),_display_(/*44.40*/lista/*44.45*/.getLicencia()),format.raw/*44.59*/("""</td>
									<td style="text-align:left;">"""),_display_(/*45.40*/lista/*45.45*/.getFonoContacto()),format.raw/*45.63*/("""</td>
									<td style='text-align:center;'>
										<a href='#' onclick="seleccionaTransportista('"""),_display_(/*47.58*/lista/*47.63*/.id),format.raw/*47.66*/("""')">
											<kbd style="background-color: green">SELECT</kbd>
										</a>
									</td>
									<td style='text-align:center;'>
										<a href='#' onclick="editaTransportista('"""),_display_(/*52.53*/lista/*52.58*/.getId()),format.raw/*52.66*/("""')">
											<kbd style="background-color: #73C6B6">edit</kbd>
										</a>
									</td>
									<td style='text-align:center;'>
										<a href='#' onclick="eliminaTransportista('"""),_display_(/*57.55*/lista/*57.60*/.id),format.raw/*57.63*/("""')">
											<kbd style="background-color: red">X</kbd>
										</a>
									</td>
								</TR>
							""")))}),format.raw/*62.9*/("""
						"""),format.raw/*63.7*/("""</tbody>
					</table>
				</div>
				<div align="center">
					<button type='button' class='btn btn-sm btn-light' style='font-size: 10px;' data-dismiss='modal' onclick="limpiarTransportista()">Cancelar</button>
				</div>
		   	</div>
		</div>
	</div>
</div>

	
<div id='modalNuevoTransportista' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
	<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
		<div class='modal-content'>
		   	<div class="modal-header">
		           <h5 class="modal-title">NUEVO TRANSPORTISTA</h5>
			</div>
	     	<div class="modal-body">
	     		<form id="formNuevoTransportista" method="post" action="/nuevoTransportistaAjax/">
		     		<table class="table table-sm table-bordered table-condensed table-fluid">
		     			<tr>
							<td style="text-align:left;">"""),_display_(/*85.38*/mapDiccionario/*85.52*/.get("RUT")),format.raw/*85.63*/(""" """),format.raw/*85.64*/("""Conductor: </td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="rutConductor"
									id="nuevoRutConductor"
									autocomplete="off"
									maxlength="50"
									onchange="value = value.trim();">
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">Nombre Conductor: </td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="conductor"
									id="nuevoNombreConductor"
									autocomplete="off"
									maxlength="100"
									onchange="value = value.trim();">
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">"""),_display_(/*107.38*/mapDiccionario/*107.52*/.get("RUT")),format.raw/*107.63*/(""" """),format.raw/*107.64*/("""Empresa: </td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="rutEmpresa"
									id="nuevoRutEmpresa"
									autocomplete="off"
									maxlength="50"
									onchange="value = value.trim();">
							</td>
						</tr>
		     			<tr>
							<td style="text-align:left;">Nombre Empresa: </td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="empresa"
									id="nuevoNombreEmpresa"
									autocomplete="off"
									maxlength="100"
									onchange="value = value.trim();">
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">Veh&iacute;culo: </td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="vehiculo"
									id="nuevoVehiculo"
									autocomplete="off"
									maxlength="100"
									onchange="value = value.trim();">
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">"""),_display_(/*140.38*/mapDiccionario/*140.52*/.get("Patente")),format.raw/*140.67*/(""": </td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="patente"
									id="nuevoPatente"
									autocomplete="off"
									maxlength="50"
									onchange="value = value.trim();">
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">Nro Inscripci&oacute;n: </td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="inscripcion"
									id="nuevoInscripcion"
									autocomplete="off"
									maxlength="50"
									onchange="value = value.trim();">
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">Nro Licencia: </td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="licencia"
									id="nuevoLicencia"
									autocomplete="off"
									maxlength="50"
									onchange="value = value.trim();">
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">Fono Contacto: </td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="fonoContacto"
									id="nuevoFonoContacto"
									autocomplete="off"
									maxlength="50"
									onchange="value = value.trim();">
							</td>
						</tr>
					</table>
					<div align="center">
						<button type='button' class='btn btn-sm btn-light' style='font-size: 10px;' data-dismiss='modal' onclick="limpiarTransportista()">Cancelar</button>
						<button type='button' class='btn btn-sm btn-success' style='font-size: 10px;' data-dismiss='modal' onClick="grabarTransportista()">Grabar</button>
					</div>
				</form>
	   		</div>
	   	</div>
	</div>
</div>

<div id='modalEditaTransportista' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
	<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
		<div class='modal-content'>
			<div class="modal-header">
				<h5 class='modal-title'>MODIFICAR TRANSPORTISTA</h5>
			</div>
		   	<div class="modal-body">
		   		<div id="vistaEditaTransp"></div>
		   	</div>
		</div>
	</div>
</div>

<script>

	$(document).ready(function() """),format.raw/*209.31*/("""{"""),format.raw/*209.32*/("""
		"""),format.raw/*210.3*/("""$('#tablaListaTransporte').DataTable("""),format.raw/*210.40*/("""{"""),format.raw/*210.41*/("""
	    	"""),format.raw/*211.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
	    	"order": [[ 4, "desc" ]],
	    	"language": """),format.raw/*214.19*/("""{"""),format.raw/*214.20*/("""
	        	"""),format.raw/*215.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*216.10*/("""}"""),format.raw/*216.11*/("""
		"""),format.raw/*217.3*/("""}"""),format.raw/*217.4*/(""" """),format.raw/*217.5*/(""");
	"""),format.raw/*218.2*/("""}"""),format.raw/*218.3*/(""");
	
	const limpiarTransportista = () => """),format.raw/*220.37*/("""{"""),format.raw/*220.38*/("""
		"""),format.raw/*221.3*/("""$('#nuevoRutConductor').val("");
		$('#nuevoNombreConductor').val("");
		$('#nuevoRutEmpresa').val("");
		$('#nuevoNombreEmpresa').val("");
		$('#nuevoVehiculo').val("");
		$('#nuevoPatente').val("");
		$('#nuevoInscripcion').val("");
		$('#nuevoLicencia').val("");
		$('#nuevoFonoContacto').val("");
	"""),format.raw/*230.2*/("""}"""),format.raw/*230.3*/("""
	
	"""),format.raw/*232.2*/("""const editaTransportista = (id_transportista) => """),format.raw/*232.51*/("""{"""),format.raw/*232.52*/("""
		"""),format.raw/*233.3*/("""$('#modalListaTransporte').modal('hide');
		let formData = new FormData();
	  	formData.append('id_transportista',id_transportista);
        $.ajax("""),format.raw/*236.16*/("""{"""),format.raw/*236.17*/("""
            """),format.raw/*237.13*/("""url: "/editaTransportistaAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(vista)"""),format.raw/*244.32*/("""{"""),format.raw/*244.33*/("""
	     		"""),format.raw/*245.9*/("""document.getElementById('vistaEditaTransp').innerHTML = vista;
	     		$('#modalEditaTransportista').modal('show');
	     	"""),format.raw/*247.8*/("""}"""),format.raw/*247.9*/("""
        """),format.raw/*248.9*/("""}"""),format.raw/*248.10*/(""");
	"""),format.raw/*249.2*/("""}"""),format.raw/*249.3*/("""

	"""),format.raw/*251.2*/("""const grabarTransportista = () => """),format.raw/*251.36*/("""{"""),format.raw/*251.37*/("""
		"""),format.raw/*252.3*/("""var formElement = document.getElementById("formNuevoTransportista");
		formData = new FormData(formElement);
		$.ajax("""),format.raw/*254.10*/("""{"""),format.raw/*254.11*/("""
            """),format.raw/*255.13*/("""url: $("#formNuevoTransportista").attr("action"),
            type: $("#formNuevoTransportista").attr("method"),
            method: $("#formNuevoTransportista").attr("method"),
            data:formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(vista)"""),format.raw/*262.32*/("""{"""),format.raw/*262.33*/("""
	     		"""),format.raw/*263.9*/("""document.getElementById('detalleTablaListaTransportista').innerHTML = vista;
	     		$('#tablaListaTransporte').DataTable("""),format.raw/*264.46*/("""{"""),format.raw/*264.47*/("""
	    	    	"""),format.raw/*265.12*/(""""fixedHeader": true,
	    	    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
	    	    	"order": [[ 4, "desc" ]],
	    	    	"language": """),format.raw/*268.24*/("""{"""),format.raw/*268.25*/("""
	    	        	"""),format.raw/*269.16*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	    	        """),format.raw/*270.15*/("""}"""),format.raw/*270.16*/("""
	    		"""),format.raw/*271.8*/("""}"""),format.raw/*271.9*/(""" """),format.raw/*271.10*/(""");
	    		transportistaSeleccionado();
				$('#modalListaTransporte').modal('show');
	     	"""),format.raw/*274.8*/("""}"""),format.raw/*274.9*/("""
        """),format.raw/*275.9*/("""}"""),format.raw/*275.10*/(""");
		limpiarTransportista();
	"""),format.raw/*277.2*/("""}"""),format.raw/*277.3*/("""
	
	"""),format.raw/*279.2*/("""const modificarTransportista = () => """),format.raw/*279.39*/("""{"""),format.raw/*279.40*/("""
		"""),format.raw/*280.3*/("""var formElement = document.getElementById("formModificaTransportista");
		formData = new FormData(formElement);
		$.ajax("""),format.raw/*282.10*/("""{"""),format.raw/*282.11*/("""
            """),format.raw/*283.13*/("""url: $("#formModificaTransportista").attr("action"),
            type: $("#formModificaTransportista").attr("method"),
            method: $("#formModificaTransportista").attr("method"),
            data:formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(vista)"""),format.raw/*290.32*/("""{"""),format.raw/*290.33*/("""
	     		"""),format.raw/*291.9*/("""document.getElementById('detalleTablaListaTransportista').innerHTML = vista;
	     		$('#tablaListaTransporte').DataTable("""),format.raw/*292.46*/("""{"""),format.raw/*292.47*/("""
	    	    	"""),format.raw/*293.12*/(""""fixedHeader": true,
	    	    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
	    	    	"order": [[ 4, "desc" ]],
	    	    	"language": """),format.raw/*296.24*/("""{"""),format.raw/*296.25*/("""
	    	        	"""),format.raw/*297.16*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	    	        """),format.raw/*298.15*/("""}"""),format.raw/*298.16*/("""
	    		"""),format.raw/*299.8*/("""}"""),format.raw/*299.9*/(""" """),format.raw/*299.10*/(""");
	    		transportistaSeleccionado();
				$('#modalListaTransporte').modal('show');
	     	"""),format.raw/*302.8*/("""}"""),format.raw/*302.9*/("""
        """),format.raw/*303.9*/("""}"""),format.raw/*303.10*/(""");
		limpiarTransportista();
	"""),format.raw/*305.2*/("""}"""),format.raw/*305.3*/("""
	
	"""),format.raw/*307.2*/("""const eliminaTransportista = (id_transportista) => """),format.raw/*307.53*/("""{"""),format.raw/*307.54*/("""
		"""),format.raw/*308.3*/("""alertify.confirm("Esta seguro de eliminar el transportista", function (e) """),format.raw/*308.77*/("""{"""),format.raw/*308.78*/("""
			"""),format.raw/*309.4*/("""if (e) """),format.raw/*309.11*/("""{"""),format.raw/*309.12*/("""
				"""),format.raw/*310.5*/("""var formData = new FormData();
				formData.append('id_transportista', id_transportista);
				$.ajax("""),format.raw/*312.12*/("""{"""),format.raw/*312.13*/("""
					"""),format.raw/*313.6*/("""url: "/eliminaTransportistaAjax/",
		            type: "POST",
		            method: "POST",
		            data: formData,
		            cache: false,
		            contentType: false,
			     	processData: false,
			     	success: function(vista)"""),format.raw/*320.34*/("""{"""),format.raw/*320.35*/("""
						"""),format.raw/*321.7*/("""if(vista=="FALSO")"""),format.raw/*321.25*/("""{"""),format.raw/*321.26*/("""
							"""),format.raw/*322.8*/("""alertify.alert('No es posible eliminar, esta en uso', function () """),format.raw/*322.74*/("""{"""),format.raw/*322.75*/("""
			     				"""),format.raw/*323.13*/("""$('#modalListaTransporte').modal('show');
			     			"""),format.raw/*324.12*/("""}"""),format.raw/*324.13*/(""");
						"""),format.raw/*325.7*/("""}"""),format.raw/*325.8*/("""else"""),format.raw/*325.12*/("""{"""),format.raw/*325.13*/("""
							"""),format.raw/*326.8*/("""document.getElementById('detalleTablaListaTransportista').innerHTML = vista;
				     		$('#tablaListaTransporte').DataTable("""),format.raw/*327.49*/("""{"""),format.raw/*327.50*/("""
				    	    	"""),format.raw/*328.15*/(""""fixedHeader": true,
				    	    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
				    	    	"order": [[ 4, "desc" ]],
				    	    	"language": """),format.raw/*331.27*/("""{"""),format.raw/*331.28*/("""
				    	        	"""),format.raw/*332.19*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
				    	        """),format.raw/*333.18*/("""}"""),format.raw/*333.19*/("""
				    		"""),format.raw/*334.11*/("""}"""),format.raw/*334.12*/(""" """),format.raw/*334.13*/(""");
				    		transportistaSeleccionado();
				    		$('#modalListaTransporte').modal('show');
						"""),format.raw/*337.7*/("""}"""),format.raw/*337.8*/("""
			     	"""),format.raw/*338.10*/("""}"""),format.raw/*338.11*/("""
		        """),format.raw/*339.11*/("""}"""),format.raw/*339.12*/(""");
				limpiarTransportista();
			"""),format.raw/*341.4*/("""}"""),format.raw/*341.5*/("""
		"""),format.raw/*342.3*/("""}"""),format.raw/*342.4*/(""");
	"""),format.raw/*343.2*/("""}"""),format.raw/*343.3*/("""
	
"""),format.raw/*345.1*/("""</script>











		
		
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],listaTransporte:List[tables.Transportista]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,listaTransporte)

  def f:((Map[String,String],List[tables.Transportista]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,listaTransporte) => apply(mapDiccionario,listaTransporte)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizar/modalListaTransportistas2.scala.html
                  HASH: 89301ca5f0725d7077ce542e8669fdfda658defc
                  MATRIX: 1014->1|1189->83|1217->85|2114->955|2137->969|2169->980|2198->981|2292->1048|2315->1062|2347->1073|2376->1074|2498->1169|2521->1183|2557->1198|2836->1451|2881->1480|2920->1481|2956->1490|3027->1534|3041->1539|3070->1547|3142->1592|3156->1597|3195->1615|3267->1660|3281->1665|3317->1680|3389->1725|3403->1730|3440->1746|3512->1791|3526->1796|3560->1809|3632->1854|3646->1859|3681->1873|3753->1918|3767->1923|3801->1936|3873->1981|3887->1986|3925->2003|3997->2048|4011->2053|4046->2067|4118->2112|4132->2117|4171->2135|4302->2239|4316->2244|4340->2247|4556->2436|4570->2441|4599->2449|4817->2640|4831->2645|4855->2648|4996->2759|5030->2766|5898->3607|5921->3621|5953->3632|5982->3633|6689->4312|6713->4326|6746->4337|6776->4338|7809->5343|7833->5357|7870->5372|10057->7530|10087->7531|10118->7534|10184->7571|10214->7572|10249->7579|10422->7723|10452->7724|10492->7735|10598->7812|10628->7813|10659->7816|10688->7817|10717->7818|10749->7822|10778->7823|10848->7864|10878->7865|10909->7868|11239->8170|11268->8171|11300->8175|11378->8224|11408->8225|11439->8228|11616->8376|11646->8377|11688->8390|11948->8621|11978->8622|12015->8631|12166->8754|12195->8755|12232->8764|12262->8765|12294->8769|12323->8770|12354->8773|12417->8807|12447->8808|12478->8811|12625->8929|12655->8930|12697->8943|13047->9264|13077->9265|13114->9274|13265->9396|13295->9397|13336->9409|13524->9568|13554->9569|13599->9585|13710->9667|13740->9668|13776->9676|13805->9677|13835->9678|13955->9770|13984->9771|14021->9780|14051->9781|14109->9811|14138->9812|14170->9816|14236->9853|14266->9854|14297->9857|14447->9978|14477->9979|14519->9992|14878->10322|14908->10323|14945->10332|15096->10454|15126->10455|15167->10467|15355->10626|15385->10627|15430->10643|15541->10725|15571->10726|15607->10734|15636->10735|15666->10736|15786->10828|15815->10829|15852->10838|15882->10839|15940->10869|15969->10870|16001->10874|16081->10925|16111->10926|16142->10929|16245->11003|16275->11004|16307->11008|16343->11015|16373->11016|16406->11021|16536->11122|16566->11123|16600->11129|16876->11376|16906->11377|16941->11384|16988->11402|17018->11403|17054->11411|17149->11477|17179->11478|17221->11491|17303->11544|17333->11545|17370->11554|17399->11555|17432->11559|17462->11560|17498->11568|17652->11693|17682->11694|17726->11709|17923->11877|17953->11878|18001->11897|18115->11982|18145->11983|18185->11994|18215->11995|18245->11996|18373->12096|18402->12097|18441->12107|18471->12108|18511->12119|18541->12120|18603->12154|18632->12155|18663->12158|18692->12159|18724->12163|18753->12164|18784->12167
                  LINES: 28->1|33->2|34->3|50->19|50->19|50->19|50->19|52->21|52->21|52->21|52->21|55->24|55->24|55->24|65->34|65->34|65->34|66->35|67->36|67->36|67->36|68->37|68->37|68->37|69->38|69->38|69->38|70->39|70->39|70->39|71->40|71->40|71->40|72->41|72->41|72->41|73->42|73->42|73->42|74->43|74->43|74->43|75->44|75->44|75->44|76->45|76->45|76->45|78->47|78->47|78->47|83->52|83->52|83->52|88->57|88->57|88->57|93->62|94->63|116->85|116->85|116->85|116->85|138->107|138->107|138->107|138->107|171->140|171->140|171->140|240->209|240->209|241->210|241->210|241->210|242->211|245->214|245->214|246->215|247->216|247->216|248->217|248->217|248->217|249->218|249->218|251->220|251->220|252->221|261->230|261->230|263->232|263->232|263->232|264->233|267->236|267->236|268->237|275->244|275->244|276->245|278->247|278->247|279->248|279->248|280->249|280->249|282->251|282->251|282->251|283->252|285->254|285->254|286->255|293->262|293->262|294->263|295->264|295->264|296->265|299->268|299->268|300->269|301->270|301->270|302->271|302->271|302->271|305->274|305->274|306->275|306->275|308->277|308->277|310->279|310->279|310->279|311->280|313->282|313->282|314->283|321->290|321->290|322->291|323->292|323->292|324->293|327->296|327->296|328->297|329->298|329->298|330->299|330->299|330->299|333->302|333->302|334->303|334->303|336->305|336->305|338->307|338->307|338->307|339->308|339->308|339->308|340->309|340->309|340->309|341->310|343->312|343->312|344->313|351->320|351->320|352->321|352->321|352->321|353->322|353->322|353->322|354->323|355->324|355->324|356->325|356->325|356->325|356->325|357->326|358->327|358->327|359->328|362->331|362->331|363->332|364->333|364->333|365->334|365->334|365->334|368->337|368->337|369->338|369->338|370->339|370->339|372->341|372->341|373->342|373->342|374->343|374->343|376->345
                  -- GENERATED --
              */
          