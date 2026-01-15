
package viewsMnuMovimientos.html

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

object modalListaTransportistas extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[Map[String,String],Map[String,String],List[tables.Transportista],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], listaTransporte: List[tables.Transportista]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""	



"""),_display_(if(mapPermiso.get("parametro.movimientoListar-llenarApiRelBase")!=null 
			&& mapPermiso.get("parametro.movimientoListar-llenarApiRelBase").equals("1") 
			&& mapPermiso.get("enviarApiGuia")!=null)/*8.45*/{_display_(Seq[Any](format.raw/*8.46*/("""
				"""),format.raw/*9.5*/("""<div id="modalAdicRelBase" class="modal" data-backdrop="static" data-keyboard="false">
				   	<div class='modal-dialog modal-dialog-scrollable' style="min-width: 60%;" role='document'>
					   	<div class='modal-content'>
							<div class='modal-header'>
								<h5 class="modal-title">CONCEPTO, DESCRIPCION Y OBSERVACIONES</h5>
							</div>
							<div class="modal-body">
								<div id="soluciones"></div>
								<div align="center">
									<button type='button' class='btn btn-sm btn-success' style='font-size: 10px;' data-dismiss='modal' onClick="enviarRelBase()">ENVIAR</button>
									<button type='button' class='btn btn-sm btn-light' style='font-size: 10px;' data-dismiss='modal'>Cancelar</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				
""")))} else {null} ),format.raw/*26.2*/("""



"""),format.raw/*30.1*/("""<div id="modalListaTransporte" class="modal" data-backdrop="static" data-keyboard="false">
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
							       <th>"""),_display_(/*46.20*/mapDiccionario/*46.34*/.get("RUT")),format.raw/*46.45*/(""" """),format.raw/*46.46*/("""Conductor</th>
							       <th>Conductor</th>
							       <th>"""),_display_(/*48.20*/mapDiccionario/*48.34*/.get("RUT")),format.raw/*48.45*/(""" """),format.raw/*48.46*/("""Empresa</th>
							       <th>Empresa</th>
							       <th>Vehiculo</th>
							       <th>"""),_display_(/*51.20*/mapDiccionario/*51.34*/.get("Patente")),format.raw/*51.49*/("""</th>
							       <th>Nro Inscripcion</th>
							       <th>Nro Licencia</th>
							       <th>Fono Contacto</th>
							       <th>SELECT</th>
							       <th>EDIT</th>
							       <th>DEL</th>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*61.9*/for(lista <- listaTransporte) yield /*61.38*/{_display_(Seq[Any](format.raw/*61.39*/("""
								"""),format.raw/*62.9*/("""<TR class="trListaTransporte">
									<td style="text-align:center;">"""),_display_(/*63.42*/lista/*63.47*/.getId()),format.raw/*63.55*/("""</td>
									<td style="text-align:left;">"""),_display_(/*64.40*/lista/*64.45*/.getRutConductor()),format.raw/*64.63*/("""</td>
									<td style="text-align:left;">"""),_display_(/*65.40*/lista/*65.45*/.getConductor()),format.raw/*65.60*/("""</td>
									<td style="text-align:left;">"""),_display_(/*66.40*/lista/*66.45*/.getRutEmpresa()),format.raw/*66.61*/("""</td>
									<td style="text-align:left;">"""),_display_(/*67.40*/lista/*67.45*/.getEmpresa()),format.raw/*67.58*/("""</td>
									<td style="text-align:left;">"""),_display_(/*68.40*/lista/*68.45*/.getVehiculo()),format.raw/*68.59*/("""</td>
									<td style="text-align:left;">"""),_display_(/*69.40*/lista/*69.45*/.getPatente()),format.raw/*69.58*/("""</td>
									<td style="text-align:left;">"""),_display_(/*70.40*/lista/*70.45*/.getInscripcion()),format.raw/*70.62*/("""</td>
									<td style="text-align:left;">"""),_display_(/*71.40*/lista/*71.45*/.getLicencia()),format.raw/*71.59*/("""</td>
									<td style="text-align:left;">"""),_display_(/*72.40*/lista/*72.45*/.getFonoContacto()),format.raw/*72.63*/("""</td>
									<td style='text-align:center;'>
										<a href='#' onclick="seleccionaTransportista('"""),_display_(/*74.58*/lista/*74.63*/.id),format.raw/*74.66*/("""')">
											<kbd style="background-color: green">SELECT</kbd>
										</a>
									</td>
									<td style='text-align:center;'>
										<a href='#' onclick="editaTransportista('"""),_display_(/*79.53*/lista/*79.58*/.getId()),format.raw/*79.66*/("""')">
											<kbd style="background-color: #73C6B6">edit</kbd>
										</a>
									</td>
									<td style='text-align:center;'>
										<a href='#' onclick="eliminaTransportista('"""),_display_(/*84.55*/lista/*84.60*/.id),format.raw/*84.63*/("""')">
											<kbd style="background-color: red">X</kbd>
										</a>
									</td>
								</TR>
							""")))}),format.raw/*89.9*/("""
						"""),format.raw/*90.7*/("""</tbody>
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
							<td style="text-align:left;">"""),_display_(/*112.38*/mapDiccionario/*112.52*/.get("RUT")),format.raw/*112.63*/(""" """),format.raw/*112.64*/("""Conductor: </td>
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
							<td style="text-align:left;">"""),_display_(/*134.38*/mapDiccionario/*134.52*/.get("RUT")),format.raw/*134.63*/(""" """),format.raw/*134.64*/("""Empresa: </td>
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
							<td style="text-align:left;">"""),_display_(/*167.38*/mapDiccionario/*167.52*/.get("Patente")),format.raw/*167.67*/(""": </td>
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

	$(document).ready(function() """),format.raw/*236.31*/("""{"""),format.raw/*236.32*/("""
		"""),format.raw/*237.3*/("""$('#tablaListaTransporte').DataTable("""),format.raw/*237.40*/("""{"""),format.raw/*237.41*/("""
	    	"""),format.raw/*238.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
	    	"order": [[ 4, "desc" ]],
	    	"language": """),format.raw/*241.19*/("""{"""),format.raw/*241.20*/("""
	        	"""),format.raw/*242.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*243.10*/("""}"""),format.raw/*243.11*/("""
		"""),format.raw/*244.3*/("""}"""),format.raw/*244.4*/(""" """),format.raw/*244.5*/(""");
	"""),format.raw/*245.2*/("""}"""),format.raw/*245.3*/(""");
	
	const limpiarTransportista = () => """),format.raw/*247.37*/("""{"""),format.raw/*247.38*/("""
		"""),format.raw/*248.3*/("""$('#nuevoRutConductor').val("");
		$('#nuevoNombreConductor').val("");
		$('#nuevoRutEmpresa').val("");
		$('#nuevoNombreEmpresa').val("");
		$('#nuevoVehiculo').val("");
		$('#nuevoPatente').val("");
		$('#nuevoInscripcion').val("");
		$('#nuevoLicencia').val("");
		$('#nuevoFonoContacto').val("");
	"""),format.raw/*257.2*/("""}"""),format.raw/*257.3*/("""
	
	"""),format.raw/*259.2*/("""const editaTransportista = (id_transportista) => """),format.raw/*259.51*/("""{"""),format.raw/*259.52*/("""
		"""),format.raw/*260.3*/("""$('#modalListaTransporte').modal('hide');
		let formData = new FormData();
	  	formData.append('id_transportista',id_transportista);
        $.ajax("""),format.raw/*263.16*/("""{"""),format.raw/*263.17*/("""
            """),format.raw/*264.13*/("""url: "/editaTransportistaAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(vista)"""),format.raw/*271.32*/("""{"""),format.raw/*271.33*/("""
	     		"""),format.raw/*272.9*/("""document.getElementById('vistaEditaTransp').innerHTML = vista;
	     		$('#modalEditaTransportista').modal('show');
	     	"""),format.raw/*274.8*/("""}"""),format.raw/*274.9*/("""
        """),format.raw/*275.9*/("""}"""),format.raw/*275.10*/(""");
	"""),format.raw/*276.2*/("""}"""),format.raw/*276.3*/("""

	"""),format.raw/*278.2*/("""const grabarTransportista = () => """),format.raw/*278.36*/("""{"""),format.raw/*278.37*/("""
		"""),format.raw/*279.3*/("""var formElement = document.getElementById("formNuevoTransportista");
		formData = new FormData(formElement);
		$.ajax("""),format.raw/*281.10*/("""{"""),format.raw/*281.11*/("""
            """),format.raw/*282.13*/("""url: $("#formNuevoTransportista").attr("action"),
            type: $("#formNuevoTransportista").attr("method"),
            method: $("#formNuevoTransportista").attr("method"),
            data:formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(vista)"""),format.raw/*289.32*/("""{"""),format.raw/*289.33*/("""
	     		"""),format.raw/*290.9*/("""document.getElementById('detalleTablaListaTransportista').innerHTML = vista;
	     		$('#tablaListaTransporte').DataTable("""),format.raw/*291.46*/("""{"""),format.raw/*291.47*/("""
	    	    	"""),format.raw/*292.12*/(""""fixedHeader": true,
	    	    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
	    	    	"order": [[ 4, "desc" ]],
	    	    	"language": """),format.raw/*295.24*/("""{"""),format.raw/*295.25*/("""
	    	        	"""),format.raw/*296.16*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	    	        """),format.raw/*297.15*/("""}"""),format.raw/*297.16*/("""
	    		"""),format.raw/*298.8*/("""}"""),format.raw/*298.9*/(""" """),format.raw/*298.10*/(""");
	    		transportistaSeleccionado();
				$('#modalListaTransporte').modal('show');
	     	"""),format.raw/*301.8*/("""}"""),format.raw/*301.9*/("""
        """),format.raw/*302.9*/("""}"""),format.raw/*302.10*/(""");
		limpiarTransportista();
	"""),format.raw/*304.2*/("""}"""),format.raw/*304.3*/("""
	
	"""),format.raw/*306.2*/("""const modificarTransportista = () => """),format.raw/*306.39*/("""{"""),format.raw/*306.40*/("""
		"""),format.raw/*307.3*/("""var formElement = document.getElementById("formModificaTransportista");
		formData = new FormData(formElement);
		$.ajax("""),format.raw/*309.10*/("""{"""),format.raw/*309.11*/("""
            """),format.raw/*310.13*/("""url: $("#formModificaTransportista").attr("action"),
            type: $("#formModificaTransportista").attr("method"),
            method: $("#formModificaTransportista").attr("method"),
            data:formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(vista)"""),format.raw/*317.32*/("""{"""),format.raw/*317.33*/("""
	     		"""),format.raw/*318.9*/("""document.getElementById('detalleTablaListaTransportista').innerHTML = vista;
	     		$('#tablaListaTransporte').DataTable("""),format.raw/*319.46*/("""{"""),format.raw/*319.47*/("""
	    	    	"""),format.raw/*320.12*/(""""fixedHeader": true,
	    	    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
	    	    	"order": [[ 4, "desc" ]],
	    	    	"language": """),format.raw/*323.24*/("""{"""),format.raw/*323.25*/("""
	    	        	"""),format.raw/*324.16*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	    	        """),format.raw/*325.15*/("""}"""),format.raw/*325.16*/("""
	    		"""),format.raw/*326.8*/("""}"""),format.raw/*326.9*/(""" """),format.raw/*326.10*/(""");
	    		transportistaSeleccionado();
				$('#modalListaTransporte').modal('show');
	     	"""),format.raw/*329.8*/("""}"""),format.raw/*329.9*/("""
        """),format.raw/*330.9*/("""}"""),format.raw/*330.10*/(""");
		limpiarTransportista();
	"""),format.raw/*332.2*/("""}"""),format.raw/*332.3*/("""
	
	"""),format.raw/*334.2*/("""const eliminaTransportista = (id_transportista) => """),format.raw/*334.53*/("""{"""),format.raw/*334.54*/("""
		"""),format.raw/*335.3*/("""alertify.confirm("Esta seguro de eliminar el transportista", function (e) """),format.raw/*335.77*/("""{"""),format.raw/*335.78*/("""
			"""),format.raw/*336.4*/("""if (e) """),format.raw/*336.11*/("""{"""),format.raw/*336.12*/("""
				"""),format.raw/*337.5*/("""var formData = new FormData();
				formData.append('id_transportista', id_transportista);
				$.ajax("""),format.raw/*339.12*/("""{"""),format.raw/*339.13*/("""
					"""),format.raw/*340.6*/("""url: "/eliminaTransportistaAjax/",
		            type: "POST",
		            method: "POST",
		            data: formData,
		            cache: false,
		            contentType: false,
			     	processData: false,
			     	success: function(vista)"""),format.raw/*347.34*/("""{"""),format.raw/*347.35*/("""
			     		"""),format.raw/*348.11*/("""if(vista=="FALSO")"""),format.raw/*348.29*/("""{"""),format.raw/*348.30*/("""
							"""),format.raw/*349.8*/("""alertify.alert('No es posible eliminar, esta en uso', function () """),format.raw/*349.74*/("""{"""),format.raw/*349.75*/("""
			     				"""),format.raw/*350.13*/("""$('#modalListaTransporte').modal('show');
			     			"""),format.raw/*351.12*/("""}"""),format.raw/*351.13*/(""");
						"""),format.raw/*352.7*/("""}"""),format.raw/*352.8*/("""else"""),format.raw/*352.12*/("""{"""),format.raw/*352.13*/("""
							"""),format.raw/*353.8*/("""document.getElementById('detalleTablaListaTransportista').innerHTML = vista;
				     		$('#tablaListaTransporte').DataTable("""),format.raw/*354.49*/("""{"""),format.raw/*354.50*/("""
				    	    	"""),format.raw/*355.15*/(""""fixedHeader": true,
				    	    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
				    	    	"order": [[ 4, "desc" ]],
				    	    	"language": """),format.raw/*358.27*/("""{"""),format.raw/*358.28*/("""
				    	        	"""),format.raw/*359.19*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
				    	        """),format.raw/*360.18*/("""}"""),format.raw/*360.19*/("""
				    		"""),format.raw/*361.11*/("""}"""),format.raw/*361.12*/(""" """),format.raw/*361.13*/(""");
				    		transportistaSeleccionado();
				    		$('#modalListaTransporte').modal('show');
						"""),format.raw/*364.7*/("""}"""),format.raw/*364.8*/("""
			     	"""),format.raw/*365.10*/("""}"""),format.raw/*365.11*/("""
		        """),format.raw/*366.11*/("""}"""),format.raw/*366.12*/(""");
				limpiarTransportista();
			"""),format.raw/*368.4*/("""}"""),format.raw/*368.5*/("""
		"""),format.raw/*369.3*/("""}"""),format.raw/*369.4*/(""");
	"""),format.raw/*370.2*/("""}"""),format.raw/*370.3*/("""
	
	
	
	"""),format.raw/*374.2*/("""const buscaSolucion = (id_guia, id_transportista) => """),format.raw/*374.55*/("""{"""),format.raw/*374.56*/("""
		"""),format.raw/*375.3*/("""var formData = new FormData();
		formData.append('id_guia', id_guia);
		formData.append('id_transportista', id_transportista);
		$.ajax("""),format.raw/*378.10*/("""{"""),format.raw/*378.11*/("""
			"""),format.raw/*379.4*/("""url: "/buscaCotiSolucionAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(vista)"""),format.raw/*386.32*/("""{"""),format.raw/*386.33*/("""
	     		"""),format.raw/*387.9*/("""if(vista!="ERR")"""),format.raw/*387.25*/("""{"""),format.raw/*387.26*/("""
		    		"""),format.raw/*388.9*/("""document.getElementById('soluciones').innerHTML = vista;
				"""),format.raw/*389.5*/("""}"""),format.raw/*389.6*/("""
	     	"""),format.raw/*390.8*/("""}"""),format.raw/*390.9*/("""
        """),format.raw/*391.9*/("""}"""),format.raw/*391.10*/(""");
	"""),format.raw/*392.2*/("""}"""),format.raw/*392.3*/("""
	
"""),format.raw/*394.1*/("""</script>











		
		
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],listaTransporte:List[tables.Transportista]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,listaTransporte)

  def f:((Map[String,String],Map[String,String],List[tables.Transportista]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,listaTransporte) => apply(mapDiccionario,mapPermiso,listaTransporte)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuMovimientos/modalListaTransportistas.scala.html
                  HASH: 8c145d1fee47d4a236a41e34cb04af2ad744c167
                  MATRIX: 1036->1|1243->115|1472->318|1510->319|1541->324|2372->1112|2403->1116|3293->1979|3316->1993|3348->2004|3377->2005|3471->2072|3494->2086|3526->2097|3555->2098|3677->2193|3700->2207|3736->2222|4015->2475|4060->2504|4099->2505|4135->2514|4234->2586|4248->2591|4277->2599|4349->2644|4363->2649|4402->2667|4474->2712|4488->2717|4524->2732|4596->2777|4610->2782|4647->2798|4719->2843|4733->2848|4767->2861|4839->2906|4853->2911|4888->2925|4960->2970|4974->2975|5008->2988|5080->3033|5094->3038|5132->3055|5204->3100|5218->3105|5253->3119|5325->3164|5339->3169|5378->3187|5509->3291|5523->3296|5547->3299|5763->3488|5777->3493|5806->3501|6024->3692|6038->3697|6062->3700|6203->3811|6237->3818|7106->4659|7130->4673|7163->4684|7193->4685|7900->5364|7924->5378|7957->5389|7987->5390|9020->6395|9044->6409|9081->6424|11268->8582|11298->8583|11329->8586|11395->8623|11425->8624|11460->8631|11633->8775|11663->8776|11703->8787|11809->8864|11839->8865|11870->8868|11899->8869|11928->8870|11960->8874|11989->8875|12059->8916|12089->8917|12120->8920|12450->9222|12479->9223|12511->9227|12589->9276|12619->9277|12650->9280|12827->9428|12857->9429|12899->9442|13159->9673|13189->9674|13226->9683|13377->9806|13406->9807|13443->9816|13473->9817|13505->9821|13534->9822|13565->9825|13628->9859|13658->9860|13689->9863|13836->9981|13866->9982|13908->9995|14258->10316|14288->10317|14325->10326|14476->10448|14506->10449|14547->10461|14735->10620|14765->10621|14810->10637|14921->10719|14951->10720|14987->10728|15016->10729|15046->10730|15166->10822|15195->10823|15232->10832|15262->10833|15320->10863|15349->10864|15381->10868|15447->10905|15477->10906|15508->10909|15658->11030|15688->11031|15730->11044|16089->11374|16119->11375|16156->11384|16307->11506|16337->11507|16378->11519|16566->11678|16596->11679|16641->11695|16752->11777|16782->11778|16818->11786|16847->11787|16877->11788|16997->11880|17026->11881|17063->11890|17093->11891|17151->11921|17180->11922|17212->11926|17292->11977|17322->11978|17353->11981|17456->12055|17486->12056|17518->12060|17554->12067|17584->12068|17617->12073|17747->12174|17777->12175|17811->12181|18087->12428|18117->12429|18157->12440|18204->12458|18234->12459|18270->12467|18365->12533|18395->12534|18437->12547|18519->12600|18549->12601|18586->12610|18615->12611|18648->12615|18678->12616|18714->12624|18868->12749|18898->12750|18942->12765|19139->12933|19169->12934|19217->12953|19331->13038|19361->13039|19401->13050|19431->13051|19461->13052|19589->13152|19618->13153|19657->13163|19687->13164|19727->13175|19757->13176|19819->13210|19848->13211|19879->13214|19908->13215|19940->13219|19969->13220|20005->13228|20087->13281|20117->13282|20148->13285|20313->13421|20343->13422|20375->13426|20634->13656|20664->13657|20701->13666|20746->13682|20776->13683|20813->13692|20902->13753|20931->13754|20967->13762|20996->13763|21033->13772|21063->13773|21095->13777|21124->13778|21155->13781
                  LINES: 28->1|33->2|39->8|39->8|40->9|57->26|61->30|77->46|77->46|77->46|77->46|79->48|79->48|79->48|79->48|82->51|82->51|82->51|92->61|92->61|92->61|93->62|94->63|94->63|94->63|95->64|95->64|95->64|96->65|96->65|96->65|97->66|97->66|97->66|98->67|98->67|98->67|99->68|99->68|99->68|100->69|100->69|100->69|101->70|101->70|101->70|102->71|102->71|102->71|103->72|103->72|103->72|105->74|105->74|105->74|110->79|110->79|110->79|115->84|115->84|115->84|120->89|121->90|143->112|143->112|143->112|143->112|165->134|165->134|165->134|165->134|198->167|198->167|198->167|267->236|267->236|268->237|268->237|268->237|269->238|272->241|272->241|273->242|274->243|274->243|275->244|275->244|275->244|276->245|276->245|278->247|278->247|279->248|288->257|288->257|290->259|290->259|290->259|291->260|294->263|294->263|295->264|302->271|302->271|303->272|305->274|305->274|306->275|306->275|307->276|307->276|309->278|309->278|309->278|310->279|312->281|312->281|313->282|320->289|320->289|321->290|322->291|322->291|323->292|326->295|326->295|327->296|328->297|328->297|329->298|329->298|329->298|332->301|332->301|333->302|333->302|335->304|335->304|337->306|337->306|337->306|338->307|340->309|340->309|341->310|348->317|348->317|349->318|350->319|350->319|351->320|354->323|354->323|355->324|356->325|356->325|357->326|357->326|357->326|360->329|360->329|361->330|361->330|363->332|363->332|365->334|365->334|365->334|366->335|366->335|366->335|367->336|367->336|367->336|368->337|370->339|370->339|371->340|378->347|378->347|379->348|379->348|379->348|380->349|380->349|380->349|381->350|382->351|382->351|383->352|383->352|383->352|383->352|384->353|385->354|385->354|386->355|389->358|389->358|390->359|391->360|391->360|392->361|392->361|392->361|395->364|395->364|396->365|396->365|397->366|397->366|399->368|399->368|400->369|400->369|401->370|401->370|405->374|405->374|405->374|406->375|409->378|409->378|410->379|417->386|417->386|418->387|418->387|418->387|419->388|420->389|420->389|421->390|421->390|422->391|422->391|423->392|423->392|425->394
                  -- GENERATED --
              */
          