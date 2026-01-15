
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

object transportistaMantencion extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Transportista],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listaTransporte: List[tables.Transportista]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "TRANSPORTISTAS: CREAR/MODIFICAR/ELIMINAR", "")),format.raw/*9.79*/("""
		"""),format.raw/*10.3*/("""<div align="center">
			<button type="button" class='btn btn-sm btn-success' data-dismiss="modal" onclick="$('#modalNuevoTransportista').modal('show');">AGREGAR TRANSPORTISTA</button>
		</div>
		<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
			<thead style="background-color: #eeeeee">
				<TR> 
					<th>id</th>
			       	<th>"""),_display_(/*17.17*/mapDiccionario/*17.31*/.get("RUT")),format.raw/*17.42*/(""" """),format.raw/*17.43*/("""Conductor</th>
			       	<th>Conductor</th>
			       	<th>"""),_display_(/*19.17*/mapDiccionario/*19.31*/.get("RUT")),format.raw/*19.42*/(""" """),format.raw/*19.43*/("""Empresa</th>
			       	<th>Empresa</th>
			       	<th>Vehiculo</th>
			       	<th>"""),_display_(/*22.17*/mapDiccionario/*22.31*/.get("Patente")),format.raw/*22.46*/("""</th>
			       	<th>Nro Inscripcion</th>
			       	<th>Nro Licencia</th>
			       	<th>Fono Contacto</th>
			       	<th>EDIT</th>
			       	<th>DEL</th>
				</TR>
			</thead>
			<tbody>
				"""),_display_(/*31.6*/for(lista <- listaTransporte) yield /*31.35*/{_display_(Seq[Any](format.raw/*31.36*/("""
					"""),format.raw/*32.6*/("""<TR class="trListaTransporte">
						<td style="text-align:center;">"""),_display_(/*33.39*/lista/*33.44*/.getId()),format.raw/*33.52*/("""</td>
						<td style="text-align:left;">"""),_display_(/*34.37*/lista/*34.42*/.getRutConductor()),format.raw/*34.60*/("""</td>
						<td style="text-align:left;">"""),_display_(/*35.37*/lista/*35.42*/.getConductor()),format.raw/*35.57*/("""</td>
						<td style="text-align:left;">"""),_display_(/*36.37*/lista/*36.42*/.getRutEmpresa()),format.raw/*36.58*/("""</td>
						<td style="text-align:left;">"""),_display_(/*37.37*/lista/*37.42*/.getEmpresa()),format.raw/*37.55*/("""</td>
						<td style="text-align:left;">"""),_display_(/*38.37*/lista/*38.42*/.getVehiculo()),format.raw/*38.56*/("""</td>
						<td style="text-align:left;">"""),_display_(/*39.37*/lista/*39.42*/.getPatente()),format.raw/*39.55*/("""</td>
						<td style="text-align:left;">"""),_display_(/*40.37*/lista/*40.42*/.getInscripcion()),format.raw/*40.59*/("""</td>
						<td style="text-align:left;">"""),_display_(/*41.37*/lista/*41.42*/.getLicencia()),format.raw/*41.56*/("""</td>
						<td style="text-align:left;">"""),_display_(/*42.37*/lista/*42.42*/.getFonoContacto()),format.raw/*42.60*/("""</td>
						<td style='text-align:center;'>
							<a href='#' onclick="editaTransportista('"""),_display_(/*44.50*/lista/*44.55*/.getId()),format.raw/*44.63*/("""')">
								<kbd style="background-color: #73C6B6">edit</kbd>
							</a>
						</td>
						<td style='text-align:center;'>
							<a href='#' onclick="eliminaTransportista('"""),_display_(/*49.52*/lista/*49.57*/.id),format.raw/*49.60*/("""')">
								<kbd style="background-color: red">X</kbd>
							</a>
						</td>
					</TR>
	 			""")))}),format.raw/*54.7*/("""
			"""),format.raw/*55.4*/("""</tbody>
		</table>
		<div class="noprint">
			<div align="center">
				<button type='button' class='btn btn-sm btn-light' style='font-size: 10px;' data-dismiss='modal' onclick="limpiarTransportista()">Cancelar</button>
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
							<td style="text-align:left;">"""),_display_(/*74.38*/mapDiccionario/*74.52*/.get("RUT")),format.raw/*74.63*/(""" """),format.raw/*74.64*/("""Conductor: </td>
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
							<td style="text-align:left;">"""),_display_(/*96.38*/mapDiccionario/*96.52*/.get("RUT")),format.raw/*96.63*/(""" """),format.raw/*96.64*/("""Empresa: </td>
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
							<td style="text-align:left;">"""),_display_(/*129.38*/mapDiccionario/*129.52*/.get("Patente")),format.raw/*129.67*/(""": </td>
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


""")))}),format.raw/*197.2*/("""



"""),format.raw/*201.1*/("""<script>

	$(document).ready(function() """),format.raw/*203.31*/("""{"""),format.raw/*203.32*/("""
		"""),format.raw/*204.3*/("""$('#tablaPrincipal').DataTable("""),format.raw/*204.34*/("""{"""),format.raw/*204.35*/("""
	    	"""),format.raw/*205.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
	    	"order": [[ 4, "desc" ]],
	    	"language": """),format.raw/*208.19*/("""{"""),format.raw/*208.20*/("""
	        	"""),format.raw/*209.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*210.10*/("""}"""),format.raw/*210.11*/("""
		"""),format.raw/*211.3*/("""}"""),format.raw/*211.4*/(""" """),format.raw/*211.5*/(""");
		
		document.getElementById('mostrarmostrar').style.display="block";
		
	"""),format.raw/*215.2*/("""}"""),format.raw/*215.3*/(""");
	
	const limpiarTransportista = () => """),format.raw/*217.37*/("""{"""),format.raw/*217.38*/("""
		"""),format.raw/*218.3*/("""$('#nuevoRutConductor').val("");
		$('#nuevoNombreConductor').val("");
		$('#nuevoRutEmpresa').val("");
		$('#nuevoNombreEmpresa').val("");
		$('#nuevoVehiculo').val("");
		$('#nuevoPatente').val("");
		$('#nuevoInscripcion').val("");
		$('#nuevoLicencia').val("");
		$('#nuevoFonoContacto').val("");
	"""),format.raw/*227.2*/("""}"""),format.raw/*227.3*/("""
	
	"""),format.raw/*229.2*/("""const editaTransportista = (id_transportista) => """),format.raw/*229.51*/("""{"""),format.raw/*229.52*/("""
		"""),format.raw/*230.3*/("""let formData = new FormData();
	  	formData.append('id_transportista',id_transportista);
        $.ajax("""),format.raw/*232.16*/("""{"""),format.raw/*232.17*/("""
            """),format.raw/*233.13*/("""url: "/editaTransportistaAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(vista)"""),format.raw/*240.32*/("""{"""),format.raw/*240.33*/("""
	     		"""),format.raw/*241.9*/("""document.getElementById('vistaEditaTransp').innerHTML = vista;
	     		$('#modalEditaTransportista').modal('show');
	     	"""),format.raw/*243.8*/("""}"""),format.raw/*243.9*/("""
        """),format.raw/*244.9*/("""}"""),format.raw/*244.10*/(""");
	"""),format.raw/*245.2*/("""}"""),format.raw/*245.3*/("""

	"""),format.raw/*247.2*/("""const grabarTransportista = () => """),format.raw/*247.36*/("""{"""),format.raw/*247.37*/("""
		"""),format.raw/*248.3*/("""var formElement = document.getElementById("formNuevoTransportista");
		formData = new FormData(formElement);
		$.ajax("""),format.raw/*250.10*/("""{"""),format.raw/*250.11*/("""
            """),format.raw/*251.13*/("""url: $("#formNuevoTransportista").attr("action"),
            type: $("#formNuevoTransportista").attr("method"),
            method: $("#formNuevoTransportista").attr("method"),
            data:formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(vista)"""),format.raw/*258.32*/("""{"""),format.raw/*258.33*/("""
				"""),format.raw/*259.5*/("""location.reload();
	     	"""),format.raw/*260.8*/("""}"""),format.raw/*260.9*/("""
        """),format.raw/*261.9*/("""}"""),format.raw/*261.10*/(""");
		limpiarTransportista();
	"""),format.raw/*263.2*/("""}"""),format.raw/*263.3*/("""
	
	"""),format.raw/*265.2*/("""const modificarTransportista = () => """),format.raw/*265.39*/("""{"""),format.raw/*265.40*/("""
		"""),format.raw/*266.3*/("""var formElement = document.getElementById("formModificaTransportista");
		formData = new FormData(formElement);
		$.ajax("""),format.raw/*268.10*/("""{"""),format.raw/*268.11*/("""
            """),format.raw/*269.13*/("""url: $("#formModificaTransportista").attr("action"),
            type: $("#formModificaTransportista").attr("method"),
            method: $("#formModificaTransportista").attr("method"),
            data:formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(vista)"""),format.raw/*276.32*/("""{"""),format.raw/*276.33*/("""
				"""),format.raw/*277.5*/("""location.reload();
	     	"""),format.raw/*278.8*/("""}"""),format.raw/*278.9*/("""
        """),format.raw/*279.9*/("""}"""),format.raw/*279.10*/(""");
		limpiarTransportista();
	"""),format.raw/*281.2*/("""}"""),format.raw/*281.3*/("""
	
	"""),format.raw/*283.2*/("""const eliminaTransportista = (id_transportista) => """),format.raw/*283.53*/("""{"""),format.raw/*283.54*/("""
		"""),format.raw/*284.3*/("""alertify.confirm("Esta seguro de eliminar el transportista", function (e) """),format.raw/*284.77*/("""{"""),format.raw/*284.78*/("""
			"""),format.raw/*285.4*/("""if (e) """),format.raw/*285.11*/("""{"""),format.raw/*285.12*/("""
				"""),format.raw/*286.5*/("""var formData = new FormData();
				formData.append('id_transportista', id_transportista);
				$.ajax("""),format.raw/*288.12*/("""{"""),format.raw/*288.13*/("""
					"""),format.raw/*289.6*/("""url: "/eliminaTransportistaAjax/",
		            type: "POST",
		            method: "POST",
		            data: formData,
		            cache: false,
		            contentType: false,
			     	processData: false,
			     	success: function(vista)"""),format.raw/*296.34*/("""{"""),format.raw/*296.35*/("""
			     		"""),format.raw/*297.11*/("""if(vista=="FALSO")"""),format.raw/*297.29*/("""{"""),format.raw/*297.30*/("""
							"""),format.raw/*298.8*/("""alertify.alert('No es posible eliminar, esta en uso', function () """),format.raw/*298.74*/("""{"""),format.raw/*298.75*/("""
								"""),format.raw/*299.9*/("""location.reload();
			     			"""),format.raw/*300.12*/("""}"""),format.raw/*300.13*/(""");
						"""),format.raw/*301.7*/("""}"""),format.raw/*301.8*/("""else"""),format.raw/*301.12*/("""{"""),format.raw/*301.13*/("""
							"""),format.raw/*302.8*/("""location.reload();
						"""),format.raw/*303.7*/("""}"""),format.raw/*303.8*/("""
			     	"""),format.raw/*304.10*/("""}"""),format.raw/*304.11*/("""
		        """),format.raw/*305.11*/("""}"""),format.raw/*305.12*/(""");
				limpiarTransportista();
			"""),format.raw/*307.4*/("""}"""),format.raw/*307.5*/("""
		"""),format.raw/*308.3*/("""}"""),format.raw/*308.4*/(""");
	"""),format.raw/*309.2*/("""}"""),format.raw/*309.3*/("""
	
"""),format.raw/*311.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listaTransporte:List[tables.Transportista]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listaTransporte)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Transportista]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listaTransporte) => apply(mapDiccionario,mapPermiso,userMnu,listaTransporte)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuTablas/transportistaMantencion.scala.html
                  HASH: 5ae9e33d5a7863680b6db12d19c30ed2acca03fd
                  MATRIX: 1048->1|1283->143|1316->151|1332->159|1371->161|1399->164|1467->212|1495->214|1571->265|1666->340|1696->343|2112->732|2135->746|2167->757|2196->758|2284->819|2307->833|2339->844|2368->845|2481->931|2504->945|2540->960|2762->1156|2807->1185|2846->1186|2879->1192|2975->1261|2989->1266|3018->1274|3087->1316|3101->1321|3140->1339|3209->1381|3223->1386|3259->1401|3328->1443|3342->1448|3379->1464|3448->1506|3462->1511|3496->1524|3565->1566|3579->1571|3614->1585|3683->1627|3697->1632|3731->1645|3800->1687|3814->1692|3852->1709|3921->1751|3935->1756|3970->1770|4039->1812|4053->1817|4092->1835|4212->1928|4226->1933|4255->1941|4458->2117|4472->2122|4496->2125|4623->2222|4654->2226|5508->3053|5531->3067|5563->3078|5592->3079|6298->3758|6321->3772|6353->3783|6382->3784|7415->4789|7439->4803|7476->4818|9627->6938|9659->6942|9728->6982|9758->6983|9789->6986|9849->7017|9879->7018|9914->7025|10087->7169|10117->7170|10157->7181|10263->7258|10293->7259|10324->7262|10353->7263|10382->7264|10487->7341|10516->7342|10586->7383|10616->7384|10647->7387|10977->7689|11006->7690|11038->7694|11116->7743|11146->7744|11177->7747|11310->7851|11340->7852|11382->7865|11642->8096|11672->8097|11709->8106|11860->8229|11889->8230|11926->8239|11956->8240|11988->8244|12017->8245|12048->8248|12111->8282|12141->8283|12172->8286|12319->8404|12349->8405|12391->8418|12741->8739|12771->8740|12804->8745|12858->8771|12887->8772|12924->8781|12954->8782|13012->8812|13041->8813|13073->8817|13139->8854|13169->8855|13200->8858|13350->8979|13380->8980|13422->8993|13781->9323|13811->9324|13844->9329|13898->9355|13927->9356|13964->9365|13994->9366|14052->9396|14081->9397|14113->9401|14193->9452|14223->9453|14254->9456|14357->9530|14387->9531|14419->9535|14455->9542|14485->9543|14518->9548|14648->9649|14678->9650|14712->9656|14988->9903|15018->9904|15058->9915|15105->9933|15135->9934|15171->9942|15266->10008|15296->10009|15333->10018|15392->10048|15422->10049|15459->10058|15488->10059|15521->10063|15551->10064|15587->10072|15640->10097|15669->10098|15708->10108|15738->10109|15778->10120|15808->10121|15870->10155|15899->10156|15930->10159|15959->10160|15991->10164|16020->10165|16051->10168
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|48->17|48->17|48->17|48->17|50->19|50->19|50->19|50->19|53->22|53->22|53->22|62->31|62->31|62->31|63->32|64->33|64->33|64->33|65->34|65->34|65->34|66->35|66->35|66->35|67->36|67->36|67->36|68->37|68->37|68->37|69->38|69->38|69->38|70->39|70->39|70->39|71->40|71->40|71->40|72->41|72->41|72->41|73->42|73->42|73->42|75->44|75->44|75->44|80->49|80->49|80->49|85->54|86->55|105->74|105->74|105->74|105->74|127->96|127->96|127->96|127->96|160->129|160->129|160->129|228->197|232->201|234->203|234->203|235->204|235->204|235->204|236->205|239->208|239->208|240->209|241->210|241->210|242->211|242->211|242->211|246->215|246->215|248->217|248->217|249->218|258->227|258->227|260->229|260->229|260->229|261->230|263->232|263->232|264->233|271->240|271->240|272->241|274->243|274->243|275->244|275->244|276->245|276->245|278->247|278->247|278->247|279->248|281->250|281->250|282->251|289->258|289->258|290->259|291->260|291->260|292->261|292->261|294->263|294->263|296->265|296->265|296->265|297->266|299->268|299->268|300->269|307->276|307->276|308->277|309->278|309->278|310->279|310->279|312->281|312->281|314->283|314->283|314->283|315->284|315->284|315->284|316->285|316->285|316->285|317->286|319->288|319->288|320->289|327->296|327->296|328->297|328->297|328->297|329->298|329->298|329->298|330->299|331->300|331->300|332->301|332->301|332->301|332->301|333->302|334->303|334->303|335->304|335->304|336->305|336->305|338->307|338->307|339->308|339->308|340->309|340->309|342->311
                  -- GENERATED --
              */
          