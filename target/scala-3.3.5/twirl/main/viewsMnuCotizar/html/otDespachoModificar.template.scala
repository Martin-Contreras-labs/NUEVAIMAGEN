
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

object otDespachoModificar extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template14[Map[String,String],Map[String,String],utilities.UserMnu,tables.Guia,String,tables.BodegaEmpresa,String,String,Long,Long,tables.BodegaEmpresa,List[tables.Transportista],Long,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
guia: tables.Guia, cabezeraOt: String, bodegaDestino: tables.BodegaEmpresa,
fechaGuia: String, vistaDetOrigen: String, id_cotizacion: Long, cont: Long, bodegaOrigen: tables.BodegaEmpresa,
listaTransporte: List[tables.Transportista], id_comercial: Long, fechaIniTerGuia: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*5.1*/("""
"""),_display_(/*6.2*/main("")/*6.10*/ {_display_(Seq[Any](format.raw/*6.12*/("""
	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),format.raw/*9.2*/("""<div id="enCarga" class="blocker" style="display: none;"><br><br><br><br><br><br><h1>En proceso.....<div id="msgEspera"></div></h1></div>
	
	<form id="formModificaDespacho" action="/modificaDespacho/" enctype="multipart/form-data" method="POST">
		<div id="mostrarmostrar" style="display:none;">
			"""),_display_(/*13.5*/barraTitulo(mapDiccionario, "MODIFICAR DESPACHO","")),format.raw/*13.57*/("""
			
			"""),format.raw/*15.4*/("""<div class="row  justify-content-md-center">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					"""),_display_(/*17.7*/Html(cabezeraOt)),format.raw/*17.23*/("""
				"""),format.raw/*18.5*/("""</div>
			</div>
			
			<input type="hidden" name="seModifico" id="seModifico" value="0">
			<input type="hidden" name="id_guia" value=""""),_display_(/*22.48*/guia/*22.52*/.getId()),format.raw/*22.60*/("""">
			<input type="hidden" name="id_ot" value=""""),_display_(/*23.46*/guia/*23.50*/.getId_ot()),format.raw/*23.61*/("""">
			<input type="hidden" name="id_cotizacion" value=""""),_display_(/*24.54*/id_cotizacion),format.raw/*24.67*/("""">
			<input type="hidden" name="id_comercialDestino" value=""""),_display_(/*25.60*/id_comercial),format.raw/*25.72*/("""">
			
			<table class="table table-sm table-hover table-bordered table-condensed table-fluid">
				<thead style="background-color: #eeeeee">
					<tr>
						<td width="25%" rowspan="2">
							<div class="input-group">
						  		<div class="input-group-prepend">
						    		<span class="input-group-text" id="basic-addon1">Origen</span>
						  		</div>
						  		<input type="hidden" id="id_bodegaOrigen" name="id_bodegaOrigen" value=""""),_display_(/*35.84*/bodegaOrigen/*35.96*/.getId()),format.raw/*35.104*/("""">
								<input type="text" class="form-control left"
									id="nombreBodegaOrigen"
									value=""""),_display_(/*38.18*/bodegaOrigen/*38.30*/.getNombre()),format.raw/*38.42*/(""""
									readonly>
							</div>
						</td>
						<td width="25%" rowspan="2">
							<div class="input-group">
						  		<div class="input-group-prepend">
						    		<span class="input-group-text" id="basic-addon1">Destino</span>
						  		</div>
								<input type="hidden" name="id_bodegaDestino" value=""""),_display_(/*47.62*/bodegaDestino/*47.75*/.getId()),format.raw/*47.83*/("""">
								<input type="text" class="form-control left"
									value=""""),_display_(/*49.18*/bodegaDestino/*49.31*/.getNombre()),format.raw/*49.43*/(""""
									readonly>
							</div>
						</td>
						<td>
							<div class="input-group">
						  		<div class="input-group-prepend">
						    		<span class="input-group-text" id="basic-addon1">Nro.Mov</span>
						  		</div>
									"""),_display_(if(mapPermiso.get("parametro.bloqueoNrosMovimientos")!=null && mapPermiso.get("parametro.bloqueoNrosMovimientos").equals("1"))/*58.137*/{_display_(Seq[Any](format.raw/*58.138*/("""
										"""),format.raw/*59.11*/("""<input type="text" class="form-control left"
							  				name="numeroGuia"
											id="numeroGuia"
											value = """"),_display_(/*62.22*/guia/*62.26*/.getNumero()),format.raw/*62.38*/(""""
											readonly
											required>
									""")))}else/*65.15*/{_display_(Seq[Any](format.raw/*65.16*/("""
										"""),format.raw/*66.11*/("""<input type="text" class="form-control left"
							  				name="numeroGuia"
											id="numeroGuia"
											value = """"),_display_(/*69.22*/guia/*69.26*/.getNumero()),format.raw/*69.38*/(""""
											onkeydown="return ingresoInt(window.event)"
											onchange="verificarNumeroGuia(value)"
											autocomplete="off"
											required>
						  			""")))}),format.raw/*74.13*/("""
							"""),format.raw/*75.8*/("""</div>
						</td>
						<td>
							<div class="input-group">
						  		<div class="input-group-prepend">
						    		<span class="input-group-text" id="basic-addon1">Nro.Ref</span>
						  		</div>
						  		<input type="text" class="form-control left"
									id="numGuiaCliente"
					  				name="numGuiaCliente"
									value = """"),_display_(/*85.20*/guia/*85.24*/.getNumGuiaCliente()),format.raw/*85.44*/(""""
									onchange='$("#seModifico").val(1)';
					  				autocomplete="off"
					  				maxlength="50">
							</div>
						</td>
						<td rowspan="2" style="width: 50px; min-width: 50px; max-width: 50px; text-align: center; vertical-align: middle">
							<span class="btn btn-info btn-sm btn-file" style="font-size: 10px; display: inline-block">
								"""),_display_(if(guia.getDocAnexo().equals("0"))/*93.44*/{_display_(Seq[Any](format.raw/*93.45*/("""
									"""),format.raw/*94.10*/("""<input type="hidden" name="docAnexo" value="0">
									<div id="txtBtn">Adjuntar Documento</div>
								""")))}else/*96.14*/{_display_(Seq[Any](format.raw/*96.15*/("""
									"""),format.raw/*97.10*/("""<input type="hidden" name="docAnexo" value=""""),_display_(/*97.55*/guia/*97.59*/.getDocAnexo()),format.raw/*97.73*/("""">
									<div id="txtBtn">Cambiar Documento</div>
								""")))}),format.raw/*99.10*/("""
								"""),format.raw/*100.9*/("""<input type="file" multiple id="docAdjunto" name="docAdjunto[]" onchange="LimitAttach(this.form, this.form.docAdjunto.value); $('#seModifico').val(1);">
							</span>
						</td>
						<td rowspan="2" style="width: 50px; min-width: 50px; max-width: 50px; text-align: center; vertical-align: middle">
							"""),_display_(if(guia.getDocAnexo().equals("0"))/*104.43*/{_display_(Seq[Any](format.raw/*104.44*/("""
								"""),format.raw/*105.9*/("""--
							""")))}else/*106.13*/{_display_(Seq[Any](format.raw/*106.14*/("""
								"""),format.raw/*107.9*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*107.50*/guia/*107.54*/.getDocAnexo()),format.raw/*107.68*/("""')">
									<kbd style="background-color: #7F8C8D">doc</kbd>
								</a>
							""")))}),format.raw/*110.9*/("""
						"""),format.raw/*111.7*/("""</td>
					</tr>
					<tr>
						<td width="230px">
							<div class="input-group">
						  		<div class="input-group-prepend">
						    		<span class="input-group-text" id="basic-addon1">Fecha Movimiento</span>
						  		</div>
						  		<input type="date" class="form-control center"
						  			name="fechaGuia" 
						  			id="fechaGuia"
						  			onblur="if(!limitaFecha(value,"""),_display_(/*122.43*/mapPermiso/*122.53*/.get("parametro.diasMenosGuia")),format.raw/*122.84*/(""","""),_display_(/*122.86*/mapPermiso/*122.96*/.get("parametro.diasMasGuia")),format.raw/*122.125*/(""")) value='"""),_display_(/*122.136*/fechaGuia),format.raw/*122.145*/("""'; else $('#seModifico').val(1);"
						  			value=""""),_display_(/*123.20*/fechaGuia),format.raw/*123.29*/(""""
				        			required>
							</div>
						</td>
						<td width="230px">
							<div class="input-group">
								<div class="input-group-prepend">
									<span class="input-group-text" id="basic-addon1">Fecha Ini/Ter</span>
								</div>
								<input type="date" class="form-control center"
								name="fechaIniTerGuia"
								id="fechaIniTerGuia"
								onblur="if(!limitaFecha(value,"""),_display_(/*135.40*/mapPermiso/*135.50*/.get("parametro.diasMenosIniTer")),format.raw/*135.83*/(""","""),_display_(/*135.85*/mapPermiso/*135.95*/.get("parametro.diasMasIniTer")),format.raw/*135.126*/(""")) value='"""),_display_(/*135.137*/fechaIniTerGuia),format.raw/*135.152*/("""'; else $('#seModifico').val(1);"
								value=""""),_display_(/*136.17*/fechaIniTerGuia),format.raw/*136.32*/(""""
								required>
							</div>
						</td>


					</tr>
					<tr>
						<td colspan="4">
							<div class="input-group">
						  		<div class="input-group-prepend">
						    		<span class="input-group-text">Observaciones</span>
						  		</div>
								<textarea class="form-control" 
									id="observaciones"
									name="observaciones" 
									autocomplete="off"
									onchange="$('#seModifico').val(1);">"""),_display_(/*153.47*/guia/*153.51*/.getObservaciones()),format.raw/*153.70*/("""</textarea>
							</div>
						</td>
						<td colspan="2" style="text-align:center;" width="70px" id="sube1">
							"""),_display_(if(guia.getFotos().equals("0"))/*157.40*/{_display_(Seq[Any](format.raw/*157.41*/("""
								"""),format.raw/*158.9*/("""<div id="cargadasFotos">--</div>
							""")))}else/*159.13*/{_display_(Seq[Any](format.raw/*159.14*/("""
								"""),format.raw/*160.9*/("""<a href="/muestraAlbumFotos/"""),_display_(/*160.38*/guia/*160.42*/.getFotos()),format.raw/*160.53*/("""">
									<kbd style="background-color: #7F8C8D">Ver Album</kbd>
								</a>
							""")))}),format.raw/*163.9*/("""
						"""),format.raw/*164.7*/("""</td>
					</tr>
				</thead>
			</table>
			
			<div id="vistaDetOrigen">"""),_display_(/*169.30*/Html(vistaDetOrigen)),format.raw/*169.50*/("""</div>
			
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
					<input type="button"  id="transporte" value='Agregar Transportista' class="btn btn-info btn-sm rounded-pill btn-block" onclick="selectTransporte(0);" >
				</div>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
					<input type="button"  id="verifica" value='Verificar' class="btn btn-success btn-sm rounded-pill btn-block" onclick="verificaMovimientos();" >
				</div>
				<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
					<input type="button" id="cancelar" value="Cancelar" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
				</div>
				<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
					<input type="button"  id="aplica" value='Confirmar' class="btn btn-primary btn-sm rounded-pill btn-block" onclick="confirmaMovimientos();" style="visibility:hidden">
				</div>
				<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
					<input type="button"  id="modifica" value='Modificar' class="btn btn-warning btn-sm rounded-pill btn-block" onclick="modificaMovimientos();" style="visibility:hidden">
				</div>
			</div>
			
		</div>
		<input type="hidden" id="id_transportistaXLSX" name="id_transportista" value=""""),_display_(/*191.82*/guia/*191.86*/.getId_transportista()),format.raw/*191.108*/("""">
	</form>
	
	
	
	<div id='modalListaEquipos' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>BUSCAR Y SELECCIONAR EQUIPO</h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<div id="listaEquipos"></div>
	   				<div class='row'>
						<div class='col-sm-12' style='text-align:center'>
							<button type='button' class='btn btn-sm  btn-warning' style='font-size: 10px;' data-dismiss='modal'>Cancelar</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
		
	<form class="formulario" id="formDescargaDocumento" method="post" action="/downloadPDF/">	
		<input type="hidden" id="descargaDocumento" name="nombreArchivo">
	</form>
		
	"""),_display_(/*221.3*/modalListaTransportistas2(mapDiccionario, listaTransporte)),format.raw/*221.61*/("""
	
	"""),_display_(if(mapPermiso.get("parametro.album-fotos")!=null && mapPermiso.get("parametro.album-fotos").equals("1"))/*223.107*/{_display_(Seq[Any](format.raw/*223.108*/("""
	
		"""),format.raw/*225.3*/("""<br>
		<hr style="height:2px; border:none; color: black; background-color: black;">
		<table class="table-bordered table-condensed">
			<tbody>
				<tr>
					<td>
						<span class="btn btn-info btn-sm btn-file" style="font-size: 12px;">
							<div id="txtBtn">Seleccionar Imagenes</div>
								<input type="file" multiple accept="image/*" id="imagen" onchange="cambiarTexto(this)">
							</div>
						</span>
					</td>
					<td style="text-align:center" id="sube2">
						"""),_display_(if(guia.getFotos().equals("0"))/*238.39*/{_display_(Seq[Any](format.raw/*238.40*/("""
							"""),format.raw/*239.8*/("""<div id="cargadasFotos">--</div>
						""")))}else/*240.12*/{_display_(Seq[Any](format.raw/*240.13*/("""
							"""),format.raw/*241.8*/("""<a href="/muestraAlbumFotos/"""),_display_(/*241.37*/guia/*241.41*/.getFotos()),format.raw/*241.52*/("""">
								<kbd style="background-color: #7F8C8D">Ver Album</kbd>
							</a>
						""")))}),format.raw/*244.8*/("""
					"""),format.raw/*245.6*/("""</td>
				</tr>
				<tr>
					<td>
						Anteponer sigla
					</td>
					<td>
						<input type="text" class="form-control" id="textSigla" value="" autocomplete="off" maxlength="5" placeholder="max 5 caracteres" onchange="value = value.trim();">
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" class="btn btn-sm btn-warning" id="btnComprimirBlob" value="Subir las imagenes">
					</td>
					<td>
						<div id="textBtnComprimir">&nbsp;Ninguna imagen seleccionada&nbsp;</div>
					</td>
				</tr>
			</tbody>
		</table>
		<div id="msgEspera2"></div>
	""")))} else {null} ),format.raw/*266.3*/("""
	
""")))}),format.raw/*268.2*/("""



"""),format.raw/*272.1*/("""<script type="text/javascript">

		let auxId_guia = 0;
		let auxId_transportista = """"),_display_(/*275.31*/guia/*275.35*/.getId_transportista()),format.raw/*275.57*/("""";
	
		const selectTransporte = (id_guia) => """),format.raw/*277.41*/("""{"""),format.raw/*277.42*/("""
			"""),format.raw/*278.4*/("""auxId_guia = id_guia;
			$('#modalListaTransporte').modal('show');
		"""),format.raw/*280.3*/("""}"""),format.raw/*280.4*/("""
		
		"""),format.raw/*282.3*/("""const seleccionaTransportista = (id_transportista) => """),format.raw/*282.57*/("""{"""),format.raw/*282.58*/("""
			"""),format.raw/*283.4*/("""$('#modalListaTransporte').modal('hide');
			$('#id_guiaXLSX').val(auxId_guia);
			$('#id_transportistaXLSX').val(id_transportista);
			auxId_transportista = id_transportista;
			$('#seModifico').val(1);
			transportistaSeleccionado();
		"""),format.raw/*289.3*/("""}"""),format.raw/*289.4*/("""
		
		"""),format.raw/*291.3*/("""const transportistaSeleccionado = () => """),format.raw/*291.43*/("""{"""),format.raw/*291.44*/("""
			"""),format.raw/*292.4*/("""var tableReg = document.getElementById("tablaListaTransporte");
			for (var i = 1; i < tableReg.rows.length; i++)"""),format.raw/*293.50*/("""{"""),format.raw/*293.51*/("""
				"""),format.raw/*294.5*/("""var cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
				let idTransporte = cellsOfRow[0].innerHTML;
				if(idTransporte == auxId_transportista)"""),format.raw/*296.44*/("""{"""),format.raw/*296.45*/("""
					"""),format.raw/*297.6*/("""tableReg.rows[i].style.backgroundColor = "yellow";
					
				"""),format.raw/*299.5*/("""}"""),format.raw/*299.6*/("""else"""),format.raw/*299.10*/("""{"""),format.raw/*299.11*/("""
					"""),format.raw/*300.6*/("""tableReg.rows[i].style.backgroundColor = "";
				"""),format.raw/*301.5*/("""}"""),format.raw/*301.6*/("""
			"""),format.raw/*302.4*/("""}"""),format.raw/*302.5*/("""
		"""),format.raw/*303.3*/("""}"""),format.raw/*303.4*/("""

	"""),format.raw/*305.2*/("""$(document).ready(function() """),format.raw/*305.31*/("""{"""),format.raw/*305.32*/("""
		"""),format.raw/*306.3*/("""document.getElementById('mostrarmostrar').style.display="block";
		
		let elemento = document.getElementsByClassName('cantDespacho');
		for(let i = 0; i < elemento.length; i++)"""),format.raw/*309.43*/("""{"""),format.raw/*309.44*/("""
			"""),format.raw/*310.4*/("""elemento[i].setAttribute("onfocus","value=value.replace(/,/g,'')");
		"""),format.raw/*311.3*/("""}"""),format.raw/*311.4*/("""
		
		"""),format.raw/*313.3*/("""cont = 100000;
		
		$('#tablaListaBodegasOrigen').DataTable("""),format.raw/*315.43*/("""{"""),format.raw/*315.44*/("""
	    	"""),format.raw/*316.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
			"order": [[ 0, "asc" ],[ 1, "asc" ]],
	    	"language": """),format.raw/*319.19*/("""{"""),format.raw/*319.20*/("""
	        	"""),format.raw/*320.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*321.10*/("""}"""),format.raw/*321.11*/("""
		"""),format.raw/*322.3*/("""}"""),format.raw/*322.4*/(""" """),format.raw/*322.5*/(""");
		
		sumaTotales();
		llenaListEquipo('"""),_display_(/*325.21*/guia/*325.25*/.getId_bodegaOrigen()),format.raw/*325.46*/("""');
		
		 transportistaSeleccionado();

	"""),format.raw/*329.2*/("""}"""),format.raw/*329.3*/(""");
	
	const verificarNumeroGuia = (value) =>"""),format.raw/*331.40*/("""{"""),format.raw/*331.41*/("""
		"""),format.raw/*332.3*/("""if(value.trim()=="")"""),format.raw/*332.23*/("""{"""),format.raw/*332.24*/("""
			"""),format.raw/*333.4*/("""$("#numeroGuia").val(""""),_display_(/*333.27*/guia/*333.31*/.getNumero()),format.raw/*333.43*/("""");
		"""),format.raw/*334.3*/("""}"""),format.raw/*334.4*/("""else"""),format.raw/*334.8*/("""{"""),format.raw/*334.9*/("""
			"""),format.raw/*335.4*/("""let aux = '"""),_display_(/*335.16*/guia/*335.20*/.getNumero()),format.raw/*335.32*/("""';
			var formData = new FormData();
		  	formData.append('numeroGuia',value);
	        $.ajax("""),format.raw/*338.17*/("""{"""),format.raw/*338.18*/("""
	            """),format.raw/*339.14*/("""url: "/verificaNumeroGuiaAjax/",
	            type: "POST",
	            method: "POST",
	            data: formData,
	            cache: false,
	            contentType: false,
		     	processData: false,
		     	success: function(respuesta)"""),format.raw/*346.37*/("""{"""),format.raw/*346.38*/("""
		     		"""),format.raw/*347.10*/("""if(respuesta=="existe")"""),format.raw/*347.33*/("""{"""),format.raw/*347.34*/("""
		     			"""),format.raw/*348.11*/("""alertify.alert('El número de movimiento ya existe, intente con otro', function () """),format.raw/*348.93*/("""{"""),format.raw/*348.94*/("""
		     				"""),format.raw/*349.12*/("""$("#numeroGuia").val(""""),_display_(/*349.35*/guia/*349.39*/.getNumero()),format.raw/*349.51*/("""");
		     			"""),format.raw/*350.11*/("""}"""),format.raw/*350.12*/(""");
		     		"""),format.raw/*351.10*/("""}"""),format.raw/*351.11*/("""else if(respuesta=="error")"""),format.raw/*351.38*/("""{"""),format.raw/*351.39*/("""
		     			"""),format.raw/*352.11*/("""alertify.alert(msgError, function () """),format.raw/*352.48*/("""{"""),format.raw/*352.49*/("""
			     			"""),format.raw/*353.12*/("""location.href = "/";
			     		"""),format.raw/*354.11*/("""}"""),format.raw/*354.12*/(""");
		     		"""),format.raw/*355.10*/("""}"""),format.raw/*355.11*/("""else"""),format.raw/*355.15*/("""{"""),format.raw/*355.16*/("""
						"""),format.raw/*356.7*/("""$("#seModifico").val(1);
					"""),format.raw/*357.6*/("""}"""),format.raw/*357.7*/("""
				"""),format.raw/*358.5*/("""}"""),format.raw/*358.6*/("""
	        """),format.raw/*359.10*/("""}"""),format.raw/*359.11*/(""");
		"""),format.raw/*360.3*/("""}"""),format.raw/*360.4*/("""
	"""),format.raw/*361.2*/("""}"""),format.raw/*361.3*/("""
	
	"""),format.raw/*363.2*/("""let vistaHTML = "";
	
	const llenaListEquipo = (id_bodegaOrigen) => """),format.raw/*365.47*/("""{"""),format.raw/*365.48*/("""
		"""),format.raw/*366.3*/("""var formData = new FormData();
  		formData.append('id_bodegaOrigen',id_bodegaOrigen);
		$.ajax("""),format.raw/*368.10*/("""{"""),format.raw/*368.11*/("""
	            """),format.raw/*369.14*/("""url: "/tblListEquipoConStockAjax/",
	            type: "POST",
	            method: "POST",
	            data: formData,
	            cache: false,
	            contentType: false,
		     	processData: false,
		     	success: function(rs)"""),format.raw/*376.30*/("""{"""),format.raw/*376.31*/("""
					"""),format.raw/*377.6*/("""if(rs=="vacia")"""),format.raw/*377.21*/("""{"""),format.raw/*377.22*/("""
						"""),format.raw/*378.7*/("""alertify.alert('"""),_display_(/*378.24*/mapDiccionario/*378.38*/.get("BODEGA")),format.raw/*378.52*/("""/PROYECTO no tiene equipos para trasladar', function () """),format.raw/*378.108*/("""{"""),format.raw/*378.109*/("""
							"""),format.raw/*379.8*/("""$("#id_bodegaOrigen").val("0");
							$("#nombreBodegaOrigen").val("");
						"""),format.raw/*381.7*/("""}"""),format.raw/*381.8*/(""");
					"""),format.raw/*382.6*/("""}"""),format.raw/*382.7*/("""else"""),format.raw/*382.11*/("""{"""),format.raw/*382.12*/("""
						"""),format.raw/*383.7*/("""let map = new Map();
						vistaHTML = 
		    				"<table id='tablaListadoEquipos' class='table table-sm table-hover table-bordered table-condensed table-fluid'>"+
		    					"<thead style='background-color: #eeeeee'>"+
			    					"<tr>"+
			    						"<th>GRUPO</th>"+
			    						"<th>CODIGO</th>"+
			    						"<th>EQUIPO</th>"+
			    						"<th>UN</th>"+
			    						"<th>STOCK</th>"+
			    					"</tr>"+
			    				"</thead>"+
			    				"<tbody>";
		    			for(var i in rs)"""),format.raw/*396.26*/("""{"""),format.raw/*396.27*/("""
		    				"""),format.raw/*397.11*/("""let stockInicial = parseFloat(rs[i][9].replace(/,/g,''));
		    				vistaHTML += 
		    					"<tr onclick=\"selCod='"+rs[i][4]+"';"
		    								+ "selEquip='"+rs[i][5].replace(/"\""/g,'')+"';"
		    								+ "selStock='"+rs[i][9]+"';"
		    								+ "selUnidad='"+rs[i][8]+"';"
		    								+ "selKg='"+rs[i][6]+"';"
		    								+ "selM2='"+rs[i][7]+"';"+
		    								" agregarLinea("+rs[i][0]+","+rs[i][1]+","+stockInicial+")\" data-dismiss='modal'>"+
		    						
			    						"<td style='text-align:left;'>"+rs[i][2]+"</td>"+
			    						"<td style='text-align:left;'>"+rs[i][4]+"</td>"+
			    						"<td style='text-align:left;'>"+rs[i][5]+"</td>"+ 
			    						"<td style='text-align:center;'>"+rs[i][8]+"</td>"+ 
			    						"<td style='text-align:right;'>"+rs[i][9]+"</td>"+
		    					"</tr>";
							map.set(rs[i][4], rs[i])
		    			"""),format.raw/*414.10*/("""}"""),format.raw/*414.11*/("""
		    			"""),format.raw/*415.10*/("""vistaHTML +=
		    					"</tbody>"+
		    				"</table>";
		
						document.getElementById('listaEquipos').innerHTML = vistaHTML;
						
						$('#tablaListadoEquipos').DataTable("""),format.raw/*421.43*/("""{"""),format.raw/*421.44*/("""
					    	"""),format.raw/*422.11*/(""""fixedHeader": true,
					    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
							"order": [[ 2, "asc" ]],
					    	"language": """),format.raw/*425.23*/("""{"""),format.raw/*425.24*/("""
					        	"""),format.raw/*426.15*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
					        """),format.raw/*427.14*/("""}"""),format.raw/*427.15*/("""
						"""),format.raw/*428.7*/("""}"""),format.raw/*428.8*/(""" """),format.raw/*428.9*/(""");
						
					"""),format.raw/*430.6*/("""}"""),format.raw/*430.7*/("""
		     	"""),format.raw/*431.9*/("""}"""),format.raw/*431.10*/("""
	      """),format.raw/*432.8*/("""}"""),format.raw/*432.9*/(""");
	"""),format.raw/*433.2*/("""}"""),format.raw/*433.3*/("""
	
	
	"""),format.raw/*436.2*/("""const muestraEquipos = () => """),format.raw/*436.31*/("""{"""),format.raw/*436.32*/("""
		"""),format.raw/*437.3*/("""if($("#id_bodegaOrigen").val() == 0) """),format.raw/*437.40*/("""{"""),format.raw/*437.41*/("""
			"""),format.raw/*438.4*/("""alertify.alert('Antes debe seleccionar """),_display_(/*438.44*/mapDiccionario/*438.58*/.get("BODEGA")),format.raw/*438.72*/("""/PROYECTO de origen , desde dónde se obtendrán los equipos a entregar', function () """),format.raw/*438.156*/("""{"""),format.raw/*438.157*/("""
				
			"""),format.raw/*440.4*/("""}"""),format.raw/*440.5*/(""");
		"""),format.raw/*441.3*/("""}"""),format.raw/*441.4*/("""else"""),format.raw/*441.8*/("""{"""),format.raw/*441.9*/("""
			
			"""),format.raw/*443.4*/("""document.getElementById('listaEquipos').innerHTML = vistaHTML;
			$('#tablaListadoEquipos').DataTable("""),format.raw/*444.40*/("""{"""),format.raw/*444.41*/("""
		    	"""),format.raw/*445.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
				"order": [[ 2, "asc" ]],
		    	"language": """),format.raw/*448.20*/("""{"""),format.raw/*448.21*/("""
		        	"""),format.raw/*449.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*450.11*/("""}"""),format.raw/*450.12*/("""
			"""),format.raw/*451.4*/("""}"""),format.raw/*451.5*/(""" """),format.raw/*451.6*/(""");
			
			let lista = document.getElementById('tablaListadoEquipos');
			let dato = document.getElementById('tabla_'+index);
			for(let i=0; i<lista.rows.length; i++)"""),format.raw/*455.42*/("""{"""),format.raw/*455.43*/("""
				"""),format.raw/*456.5*/("""let cod = lista.rows[i].cells[1].innerHTML;
				lista.rows[i].style.display = 'true';
				for(let j=0; j<dato.rows.length; j++)"""),format.raw/*458.42*/("""{"""),format.raw/*458.43*/("""
					"""),format.raw/*459.6*/("""let cod2 = dato.rows[j].cells[0].innerHTML;
					if(cod == cod2)"""),format.raw/*460.21*/("""{"""),format.raw/*460.22*/("""
						"""),format.raw/*461.7*/("""lista.rows[i].style.display = 'none';
					"""),format.raw/*462.6*/("""}"""),format.raw/*462.7*/("""
				"""),format.raw/*463.5*/("""}"""),format.raw/*463.6*/("""
			"""),format.raw/*464.4*/("""}"""),format.raw/*464.5*/("""
			"""),format.raw/*465.4*/("""$("#seModifico").val(1);
			$('#modalListaEquipos').modal('show');
		"""),format.raw/*467.3*/("""}"""),format.raw/*467.4*/("""
   """),format.raw/*468.4*/("""}"""),format.raw/*468.5*/("""




	"""),format.raw/*473.2*/("""let index = -1;
	let idCotizaDetalle = -1;
	let esVenta = -1;
	let idEquipOrigen = -1;

	
	let selCod = "";
	let selEquip = "";
	let selStock = 0;
	let selUnidad = "";
	let selKg = 0;
	let selM2 = 0;
	let cont = 0;
	
	const agregarLinea = (id_equipo, id_cotizacion, stockInicial) =>"""),format.raw/*487.66*/("""{"""),format.raw/*487.67*/("""
		
		"""),format.raw/*489.3*/("""let lista = document.getElementById('tablaListadoEquipos');
		for(let i=0; i<lista.rows.length; i++)"""),format.raw/*490.41*/("""{"""),format.raw/*490.42*/("""
			"""),format.raw/*491.4*/("""lista.rows[i].style.display = '';
		"""),format.raw/*492.3*/("""}"""),format.raw/*492.4*/("""
		
		"""),format.raw/*494.3*/("""let tabla = document.getElementById('tabla_'+index);
		tr = tabla.insertRow(tabla.rows.length-1);
		
		td = tr.insertCell(tr.cells.length);
		td.setAttribute ('style', 'text-align:left');
		td.innerHTML = 	selCod;
		
		td = tr.insertCell(tr.cells.length);
		td.setAttribute ('style', 'text-align:left');
		td.innerHTML = 	"<input type='hidden' name='id_equipoDespacho[]' value='"+id_equipo+"'>"+
						"<input type='hidden' name='id_cotizaDetalle[]' value='"+idCotizaDetalle+"'>"+
						"<input type='hidden' name='esVenta[]' value='"+esVenta+"'>"+
						"<input type='hidden' name='esNuevo[]' value='0'>"+
						"<input type='hidden' name='id_equipoOrigen[]' value='"+idEquipOrigen+"'>"+
						selEquip;
		
		td = tr.insertCell(tr.cells.length);
		td.setAttribute ('style', 'text-align:center');
		td.innerHTML = selUnidad;
		
		td = tr.insertCell(tr.cells.length);
		td.setAttribute ('style', 'text-align:right');
		td.innerHTML="<div id='"+cont+"_cantStock'>"+selStock +"</div>";
		
		
		td = tr.insertCell(tr.cells.length);
		td.setAttribute ('style', 'text-align:center');
		td.innerHTML ="<input type='text' class='cantDespacho form-control right' value='0.00' "+
				" id='"+cont+"_cantDespacho_"+id_equipo+"_"+id_cotizacion+"' "+ 
				" name='cantDespacho[]' " +
				" onfocus=\"value=value.replace(/,/g,'')\" "+
				" onkeydown='return ingresoDouble(window.event)' "+
				" onfocusout='value = formatStandar2(value);'"+
				" onchange=' validaCantDespacho("+cont+",value,"+id_equipo+","+id_cotizacion+","+stockInicial+",0,0); value = formatStandar2(value);'>";
		
				
		td = tr.insertCell(tr.cells.length);
		td.setAttribute ('style', 'text-align:center');
		td.innerHTML ="<input type='text' class='cantEquivalente form-control right' value='0.00' "+
			" id='"+cont+"_cantEquivalente' "+
			" name='cantEquivalente[]' " +
			" onfocus=\"value=value.replace(/,/g,'')\" "+
			" onkeydown='return ingresoDouble(window.event)' "+
			" onfocusout='value = formatStandar2(value);'"+
			" onchange='value=formatStandar2(value);'>";
	
		td = tr.insertCell(tr.cells.length);
		td.setAttribute ('style', 'text-align:right;');
		td.innerHTML ="<div class='kg' id='"+cont+"_kgTot'>0.00</div>";
		
		td = tr.insertCell(tr.cells.length);
		td.setAttribute ('style', 'text-align:right;');
		td.innerHTML ="<div class='m2' id='"+cont+"_m2Tot'>0.00</div>";
		
		td = tr.insertCell(tr.cells.length);
		td.setAttribute ('style', 'display:none');
		td.innerHTML ="<div id='"+cont+"_kgUn'>"+selKg+"</div>";
		
		td = tr.insertCell(tr.cells.length);
		td.setAttribute ('style', 'display:none');
		td.innerHTML ="<div id='"+cont+"_m2Un'>"+selM2+"</div>";
		
		$("#seModifico").val(1);
			
		cont++;
			
	"""),format.raw/*560.2*/("""}"""),format.raw/*560.3*/("""
	
	"""),format.raw/*562.2*/("""const validaCantDespacho = (indCont, valor, id_equipo, id_cotizacion, stockInicial, cantInicial, cantDespAgrup) =>"""),format.raw/*562.116*/("""{"""),format.raw/*562.117*/("""
		
		"""),format.raw/*564.3*/("""let stock = $("#"+indCont+"_cantStock").text().replace(/,/g,'');
		let kgUni = $("#"+indCont+"_kgUn").text().replace(/,/g,'');
		let m2Uni = $("#"+indCont+"_m2Un").text().replace(/,/g,'');
		
		let sum = 0;
		let elemento = document.getElementsByClassName('cantDespacho');
		for(let i = 0; i < elemento.length; i++)"""),format.raw/*570.43*/("""{"""),format.raw/*570.44*/("""
		    """),format.raw/*571.7*/("""let dePaso = elemento[i].getAttribute('id').split("_");
			if( id_equipo==dePaso[2] && id_cotizacion==dePaso[3] )"""),format.raw/*572.58*/("""{"""),format.raw/*572.59*/("""
				"""),format.raw/*573.5*/("""let auxNum = elemento[i].value;
				auxNum = auxNum.replace(/,/g,'');
				sum += parseFloat(auxNum);
			"""),format.raw/*576.4*/("""}"""),format.raw/*576.5*/("""
		"""),format.raw/*577.3*/("""}"""),format.raw/*577.4*/("""
		
		"""),format.raw/*579.3*/("""let stockTotal = parseFloat(stockInicial) + parseFloat(cantDespAgrup);
		
		
		if(parseFloat(valor) > parseFloat(stockTotal) || parseFloat(sum) > parseFloat(stockTotal))"""),format.raw/*582.93*/("""{"""),format.raw/*582.94*/("""
			
			"""),format.raw/*584.4*/("""alertify.alert('La cantidad no puede ser mayor al stock disponible', function () """),format.raw/*584.85*/("""{"""),format.raw/*584.86*/("""
				
				"""),format.raw/*586.5*/("""$("#"+indCont+"_cantStock").text(formatStandar(stockInicial,2));
				$("#"+indCont+"_cantDespacho_"+id_equipo+"_"+id_cotizacion).val(formatStandar(cantInicial,2));
				
				$("#"+indCont+"_cantEquivalente").val(formatStandar(parseFloat(0),2));
				$("#"+indCont+"_kgTot").text(formatStandar(parseFloat(0)*parseFloat(kgUni),2));
				$("#"+indCont+"_m2Tot").text(formatStandar(parseFloat(0)*parseFloat(m2Uni),2));
			"""),format.raw/*592.4*/("""}"""),format.raw/*592.5*/(""");
		"""),format.raw/*593.3*/("""}"""),format.raw/*593.4*/(""" """),format.raw/*593.5*/("""else """),format.raw/*593.10*/("""{"""),format.raw/*593.11*/("""
			
			"""),format.raw/*595.4*/("""let stockResult = parseFloat(stockTotal) - parseFloat(sum);
			let elemento2 = document.getElementsByClassName('cantDespacho');
			for(let i = 0; i < elemento2.length; i++)"""),format.raw/*597.45*/("""{"""),format.raw/*597.46*/("""
			    """),format.raw/*598.8*/("""let dePaso = elemento2[i].getAttribute('id').split("_");
				if( id_equipo == dePaso[2] && id_cotizacion == dePaso[3] )"""),format.raw/*599.63*/("""{"""),format.raw/*599.64*/("""
					"""),format.raw/*600.6*/("""let auxCont = dePaso[0];
					$("#"+auxCont+"_cantStock").text(formatStandar(stockResult,2));
				"""),format.raw/*602.5*/("""}"""),format.raw/*602.6*/("""
			"""),format.raw/*603.4*/("""}"""),format.raw/*603.5*/("""
			
			"""),format.raw/*605.4*/("""$("#"+indCont+"_cantEquivalente").val(formatStandar(parseFloat(valor),2));
			$("#"+indCont+"_kgTot").text(formatStandar(parseFloat(valor)*parseFloat(kgUni),2));
			$("#"+indCont+"_m2Tot").text(formatStandar(parseFloat(valor)*parseFloat(m2Uni),2));
		"""),format.raw/*608.3*/("""}"""),format.raw/*608.4*/("""
		"""),format.raw/*609.3*/("""$("#seModifico").val(1);
		sumaTotales();
	"""),format.raw/*611.2*/("""}"""),format.raw/*611.3*/("""
	
	"""),format.raw/*613.2*/("""const sumaTotales = () =>"""),format.raw/*613.27*/("""{"""),format.raw/*613.28*/("""
		"""),format.raw/*614.3*/("""let cant = 0;
		let kg = 0;
		let m2 = 0;
		
		let arrCant = document.getElementsByClassName('cantDespacho');
		let arrKg = document.getElementsByClassName('kg');
		let arrM2 = document.getElementsByClassName('m2');
		
		for(let i = 0; i < arrCant.length; i++)"""),format.raw/*622.42*/("""{"""),format.raw/*622.43*/("""
			"""),format.raw/*623.4*/("""let auxNum = arrCant[i].value;
			auxNum = auxNum.replace(/,/g,'');
			cant += parseFloat(auxNum);
		"""),format.raw/*626.3*/("""}"""),format.raw/*626.4*/("""
		
		"""),format.raw/*628.3*/("""for(let i = 0; i < arrKg.length; i++)"""),format.raw/*628.40*/("""{"""),format.raw/*628.41*/("""
			"""),format.raw/*629.4*/("""let auxNum = arrKg[i].innerHTML;
			auxNum = auxNum.replace(/,/g,'');
			kg += parseFloat(auxNum);
		"""),format.raw/*632.3*/("""}"""),format.raw/*632.4*/("""
		
		"""),format.raw/*634.3*/("""for(let i = 0; i < arrM2.length; i++)"""),format.raw/*634.40*/("""{"""),format.raw/*634.41*/("""
			"""),format.raw/*635.4*/("""let auxNum = arrM2[i].innerHTML;
			auxNum = auxNum.replace(/,/g,'');
			m2 += parseFloat(auxNum);
		"""),format.raw/*638.3*/("""}"""),format.raw/*638.4*/("""
		
		"""),format.raw/*640.3*/("""$("#totalCant").text(formatStandar(parseFloat(cant)));
		$("#totalKG").text(formatStandar(parseFloat(kg)));
		$("#totalM2").text(formatStandar(parseFloat(m2)));
	"""),format.raw/*643.2*/("""}"""),format.raw/*643.3*/("""
	
	"""),format.raw/*645.2*/("""const verificaMovimientos = () =>"""),format.raw/*645.35*/("""{"""),format.raw/*645.36*/("""
		
		"""),format.raw/*647.3*/("""document.getElementById("nombreBodegaOrigen").style.backgroundColor = "yellow";
		document.getElementById("nombreBodegaOrigen").setAttribute('disabled','true');
		
		document.getElementById("numeroGuia").style.backgroundColor = "yellow";
		document.getElementById("numeroGuia").setAttribute('disabled','true');
		
		document.getElementById("numGuiaCliente").style.backgroundColor = "yellow";
		document.getElementById("numGuiaCliente").setAttribute('disabled','true');
		
		document.getElementById("fechaGuia").style.backgroundColor = "yellow";
		document.getElementById("fechaGuia").setAttribute('disabled','true');

		document.getElementById("fechaIniTerGuia").style.backgroundColor = "yellow";
		document.getElementById("fechaIniTerGuia").setAttribute('disabled','true');
		
		let input = document.getElementsByClassName("cantDespacho");
		for (let i=0; i<input.length;i++)"""),format.raw/*663.36*/("""{"""),format.raw/*663.37*/("""
			"""),format.raw/*664.4*/("""input[i].style.backgroundColor = "yellow";
		    input[i].setAttribute('disabled','true');
	    """),format.raw/*666.6*/("""}"""),format.raw/*666.7*/("""
		
		"""),format.raw/*668.3*/("""input = document.getElementsByClassName("cantEquivalente");
		for (let i=0; i<input.length;i++)"""),format.raw/*669.36*/("""{"""),format.raw/*669.37*/("""
			"""),format.raw/*670.4*/("""input[i].style.backgroundColor = "yellow";
		    input[i].setAttribute('disabled','true');
	    """),format.raw/*672.6*/("""}"""),format.raw/*672.7*/("""

		"""),format.raw/*674.3*/("""let btnBuscar = document.getElementsByClassName("btnBuscar");
		for (let i=0; i<btnBuscar.length;i++)"""),format.raw/*675.40*/("""{"""),format.raw/*675.41*/("""
			"""),format.raw/*676.4*/("""btnBuscar[i].style.display = "none";
	    """),format.raw/*677.6*/("""}"""),format.raw/*677.7*/("""
		
		"""),format.raw/*679.3*/("""document.getElementById('verifica').style.visibility = 'hidden';
		document.getElementById('modifica').style.visibility = 'visible';
		document.getElementById('aplica').style.visibility = 'visible';
	"""),format.raw/*682.2*/("""}"""),format.raw/*682.3*/("""
	
	"""),format.raw/*684.2*/("""const modificaMovimientos = () =>"""),format.raw/*684.35*/("""{"""),format.raw/*684.36*/("""
		
		"""),format.raw/*686.3*/("""document.getElementById("nombreBodegaOrigen").style.backgroundColor = "white";
		document.getElementById("nombreBodegaOrigen").removeAttribute('disabled');
		
		document.getElementById("numeroGuia").style.backgroundColor = "white";
		document.getElementById("numeroGuia").removeAttribute('disabled');
		
		document.getElementById("numGuiaCliente").style.backgroundColor = "white";
		document.getElementById("numGuiaCliente").removeAttribute('disabled');
		
		document.getElementById("fechaGuia").style.backgroundColor = "white";
		document.getElementById("fechaGuia").removeAttribute('disabled');

		document.getElementById("fechaIniTerGuia").style.backgroundColor = "white";
		document.getElementById("fechaIniTerGuia").removeAttribute('disabled');
		
		let input = document.getElementsByClassName("cantDespacho");
		for (let i=0; i<input.length;i++)"""),format.raw/*702.36*/("""{"""),format.raw/*702.37*/("""
			"""),format.raw/*703.4*/("""input[i].style.backgroundColor = "white";
		    input[i].removeAttribute('disabled');
	    """),format.raw/*705.6*/("""}"""),format.raw/*705.7*/("""
		
		"""),format.raw/*707.3*/("""input = document.getElementsByClassName("cantEquivalente");
		for (let i=0; i<input.length;i++)"""),format.raw/*708.36*/("""{"""),format.raw/*708.37*/("""
			"""),format.raw/*709.4*/("""input[i].style.backgroundColor = "white";
		    input[i].removeAttribute('disabled');
	    """),format.raw/*711.6*/("""}"""),format.raw/*711.7*/("""

		"""),format.raw/*713.3*/("""let btnBuscar = document.getElementsByClassName("btnBuscar");
		for (let i=0; i<btnBuscar.length;i++)"""),format.raw/*714.40*/("""{"""),format.raw/*714.41*/("""
			"""),format.raw/*715.4*/("""btnBuscar[i].style.display = "block";
	    """),format.raw/*716.6*/("""}"""),format.raw/*716.7*/("""
		
		"""),format.raw/*718.3*/("""document.getElementById('verifica').style.visibility = 'visible';
		document.getElementById('modifica').style.visibility = 'hidden';
		document.getElementById('aplica').style.visibility = 'hidden';
		document.getElementById('cancelar').style.visibility = 'visible';
	"""),format.raw/*722.2*/("""}"""),format.raw/*722.3*/("""
	
	"""),format.raw/*724.2*/("""const confirmaMovimientos = () =>"""),format.raw/*724.35*/("""{"""),format.raw/*724.36*/("""
		"""),format.raw/*725.3*/("""document.getElementById('aplica').style.visibility = 'hidden';
		document.getElementById('modifica').style.visibility = 'hidden';
		document.getElementById('cancelar').style.visibility = 'hidden';
		document.getElementById('transporte').style.visibility = 'hidden';
		
		
		document.getElementById("nombreBodegaOrigen").removeAttribute('disabled');
		document.getElementById("numeroGuia").removeAttribute('disabled');
		document.getElementById("numGuiaCliente").removeAttribute('disabled');
		document.getElementById("fechaGuia").removeAttribute('disabled');
		document.getElementById("fechaIniTerGuia").removeAttribute('disabled');

		let input = document.getElementsByClassName("cantDespacho");
		for (let i=0; i<input.length;i++)"""),format.raw/*738.36*/("""{"""),format.raw/*738.37*/("""
		    """),format.raw/*739.7*/("""input[i].removeAttribute('disabled');
	    """),format.raw/*740.6*/("""}"""),format.raw/*740.7*/("""
		
		"""),format.raw/*742.3*/("""input = document.getElementsByClassName("cantEquivalente");
		for (let i=0; i<input.length;i++)"""),format.raw/*743.36*/("""{"""),format.raw/*743.37*/("""
		    """),format.raw/*744.7*/("""input[i].removeAttribute('disabled');
	    """),format.raw/*745.6*/("""}"""),format.raw/*745.7*/("""

		"""),format.raw/*747.3*/("""if($("#id_bodegaOrigen").val() == 0) """),format.raw/*747.40*/("""{"""),format.raw/*747.41*/("""
			"""),format.raw/*748.4*/("""alertify.alert('Antes debe seleccionar """),_display_(/*748.44*/mapDiccionario/*748.58*/.get("BODEGA")),format.raw/*748.72*/("""/PROYECTO de origen', function () """),format.raw/*748.106*/("""{"""),format.raw/*748.107*/("""
				"""),format.raw/*749.5*/("""modificaMovimientos();
			"""),format.raw/*750.4*/("""}"""),format.raw/*750.5*/(""");
		"""),format.raw/*751.3*/("""}"""),format.raw/*751.4*/("""else """),format.raw/*751.9*/("""{"""),format.raw/*751.10*/("""
			"""),format.raw/*752.4*/("""let totalCantidad = $("#totalCant").text();
			totalCantidad = totalCantidad.replace(/,/g,'');
			if(parseFloat(totalCantidad)>0)"""),format.raw/*754.35*/("""{"""),format.raw/*754.36*/("""
				"""),format.raw/*755.5*/("""document.getElementById('enCarga').style.display="block";
				document.getElementById("formModificaDespacho").submit();
			"""),format.raw/*757.4*/("""}"""),format.raw/*757.5*/("""else"""),format.raw/*757.9*/("""{"""),format.raw/*757.10*/("""
				"""),format.raw/*758.5*/("""alertify.alert('No puede generar un despacho sin cantidades a trasladar', function () """),format.raw/*758.91*/("""{"""),format.raw/*758.92*/("""
					"""),format.raw/*759.6*/("""modificaMovimientos();
				"""),format.raw/*760.5*/("""}"""),format.raw/*760.6*/(""");
			"""),format.raw/*761.4*/("""}"""),format.raw/*761.5*/("""
		"""),format.raw/*762.3*/("""}"""),format.raw/*762.4*/("""
	"""),format.raw/*763.2*/("""}"""),format.raw/*763.3*/("""
	
	"""),format.raw/*765.2*/("""const descargaDocumento = (nombreDOC) => """),format.raw/*765.43*/("""{"""),format.raw/*765.44*/("""
		  """),format.raw/*766.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*768.2*/("""}"""),format.raw/*768.3*/("""
	
	"""),format.raw/*770.2*/("""let extArray = new Array(".gif", ".jpg", ".png", ".jpeg", ".pdf", ".xls", ".doc", ".xlsx", ".docx", ".tar", ".zip", ".rar");
	const LimitAttach = (form, file) => """),format.raw/*771.38*/("""{"""),format.raw/*771.39*/("""
		
		"""),format.raw/*773.3*/("""if (!file) return;
		
		const $inputArchivos = document.querySelector("#docAdjunto");
		const archivosParaSubir = $inputArchivos.files;
		let tamanio = 0;
		let allowSubmit = false;
		
		for(let i=0; i<archivosParaSubir.length; i++)"""),format.raw/*780.48*/("""{"""),format.raw/*780.49*/("""
			"""),format.raw/*781.4*/("""tamanio += archivosParaSubir[i].size;
			let nombre = archivosParaSubir[i].name;
			let extencion = nombre.substring(nombre.lastIndexOf(".")).toLowerCase();
			for (let j = 0; j < extArray.length; j++) """),format.raw/*784.46*/("""{"""),format.raw/*784.47*/("""
				"""),format.raw/*785.5*/("""if (extArray[j] == extencion) """),format.raw/*785.35*/("""{"""),format.raw/*785.36*/(""" 
					"""),format.raw/*786.6*/("""allowSubmit = true;
				"""),format.raw/*787.5*/("""}"""),format.raw/*787.6*/("""
			"""),format.raw/*788.4*/("""}"""),format.raw/*788.5*/("""
			"""),format.raw/*789.4*/("""if(allowSubmit && i < archivosParaSubir.length -1)"""),format.raw/*789.54*/("""{"""),format.raw/*789.55*/("""
				"""),format.raw/*790.5*/("""allowSubmit = false;
			"""),format.raw/*791.4*/("""}"""),format.raw/*791.5*/("""
		"""),format.raw/*792.3*/("""}"""),format.raw/*792.4*/("""
		
		"""),format.raw/*794.3*/("""if (allowSubmit) """),format.raw/*794.20*/("""{"""),format.raw/*794.21*/("""
			"""),format.raw/*795.4*/("""if(tamanio > 200000000)"""),format.raw/*795.27*/("""{"""),format.raw/*795.28*/("""
				"""),format.raw/*796.5*/("""alert("Se permite máximo subir 200 MB,"
				+" lo que se intenta subir pesa: "+Math.round(tamanio/10000)/100+" MB");
				form.docAdjunto.value="";
			"""),format.raw/*799.4*/("""}"""),format.raw/*799.5*/("""else"""),format.raw/*799.9*/("""{"""),format.raw/*799.10*/("""
				"""),format.raw/*800.5*/("""alert("Documento subido con éxito");
				$("#txtBtn").text("Cambiar Documento");
			"""),format.raw/*802.4*/("""}"""),format.raw/*802.5*/("""
		"""),format.raw/*803.3*/("""}"""),format.raw/*803.4*/("""else"""),format.raw/*803.8*/("""{"""),format.raw/*803.9*/("""
			"""),format.raw/*804.4*/("""alert("Se permiten únicamente archivos con la extención: " 
			+ (extArray.join("  ")) + "\nPor favor, seleccione solo archivos con extenciones permitidas "
			+ "e intente de nuevo.");
			form.docAdjunto.value="";
		"""),format.raw/*808.3*/("""}"""),format.raw/*808.4*/("""
	"""),format.raw/*809.2*/("""}"""),format.raw/*809.3*/("""
		
		

	
"""),format.raw/*814.1*/("""</script>

<script type="text/javascript">
		
			const $imagen = document.querySelector("#imagen");
			
			const cambiarTexto = (nodo) =>"""),format.raw/*820.34*/("""{"""),format.raw/*820.35*/("""
				"""),format.raw/*821.5*/("""$("#textBtnComprimir").text(nodo.files.length + " imagenes seleccionadas");
			"""),format.raw/*822.4*/("""}"""),format.raw/*822.5*/("""
			
			"""),format.raw/*824.4*/("""document.querySelector("#btnComprimirBlob").addEventListener("click", async () => """),format.raw/*824.86*/("""{"""),format.raw/*824.87*/("""
				"""),format.raw/*825.5*/("""if ($imagen.files.length <= 0) """),format.raw/*825.36*/("""{"""),format.raw/*825.37*/("""
					"""),format.raw/*826.6*/("""return;
				"""),format.raw/*827.5*/("""}"""),format.raw/*827.6*/("""
				"""),format.raw/*828.5*/("""document.getElementById('enCarga').style.display="block";
				let sigla = $("#textSigla").val();
				if(sigla.length > 0)"""),format.raw/*830.25*/("""{"""),format.raw/*830.26*/("""
					"""),format.raw/*831.6*/("""sigla = sigla+"_";
				"""),format.raw/*832.5*/("""}"""),format.raw/*832.6*/("""
				"""),format.raw/*833.5*/("""subirArchivo2($imagen, contador, sigla);
			"""),format.raw/*834.4*/("""}"""),format.raw/*834.5*/(""");
			
			let contador = 0;

				
				const MAX_WIDTH = 1000;
				const MAX_HEIGHT = 750;
				const MIME_TYPE = "image/jpeg";
				const QUALITY = 0.7;

				const input = document.getElementById("img-input");
				
				function subirArchivo2($imagen, contador, sigla) """),format.raw/*846.54*/("""{"""),format.raw/*846.55*/("""
					"""),format.raw/*847.6*/("""const file = $imagen.files[contador]; 
					  const blobURL = URL.createObjectURL(file);
					  const img = new Image();
					  img.src = blobURL;
					  img.onerror = function () """),format.raw/*851.34*/("""{"""),format.raw/*851.35*/("""
					    """),format.raw/*852.10*/("""URL.revokeObjectURL(this.src);
					  """),format.raw/*853.8*/("""}"""),format.raw/*853.9*/(""";
					  
					  
					  img.onload = function () """),format.raw/*856.33*/("""{"""),format.raw/*856.34*/("""
					    """),format.raw/*857.10*/("""URL.revokeObjectURL(this.src);
					    const [newWidth, newHeight] = calculateSize(img, MAX_WIDTH, MAX_HEIGHT);
					    const canvas = document.createElement("canvas");
					    canvas.width = newWidth;
					    canvas.height = newHeight;
					    const ctx = canvas.getContext("2d");
					    ctx.drawImage(img, 0, 0, newWidth, newHeight);
					    canvas.toBlob(
					      (blob) => """),format.raw/*865.22*/("""{"""),format.raw/*865.23*/("""
					        """),format.raw/*866.14*/("""displayInfo('Original file', file);
					        displayInfo('Compressed file', blob);
					      
					        const myFile = new File([blob], sigla + $imagen.files[contador].name, """),format.raw/*869.84*/("""{"""),format.raw/*869.85*/("""
							    """),format.raw/*870.12*/("""type: blob.type,
							"""),format.raw/*871.8*/("""}"""),format.raw/*871.9*/(""");
							
							$("#msgEspera").text(" cargando imagen "+(contador+1)+" de "+$imagen.files.length);
							$("#msgEspera2").text(" En proceso..... cargando imagen "+(contador+1)+" de "+$imagen.files.length);
							
							
							var formData = new FormData();
							formData.append("fotosAdjunto[0]",myFile);
							formData.append("id_guia",'"""),_display_(/*879.36*/guia/*879.40*/.id),format.raw/*879.43*/("""');
							formData.append("iniCarpeta",'Fotos_Mov_');
								$.ajax("""),format.raw/*881.16*/("""{"""),format.raw/*881.17*/("""
									"""),format.raw/*882.10*/("""async: false,
							        url: "/grabaAlbumFotosAjax/",
							        type: "POST",
							        method: "POST",
							        data: formData,
							        cache: false,
							        contentType: false,
							     	processData: false,
							     	success: function(rs)"""),format.raw/*890.35*/("""{"""),format.raw/*890.36*/("""
							     		"""),format.raw/*891.15*/("""if(rs == "error")"""),format.raw/*891.32*/("""{"""),format.raw/*891.33*/("""
							     			"""),format.raw/*892.16*/("""alertify.alert('Se presento un error, no fueron subidas las imagenes', function () """),format.raw/*892.99*/("""{"""),format.raw/*892.100*/("""
							     				"""),format.raw/*893.17*/("""document.getElementById("imagen").value = "";
							     			"""),format.raw/*894.16*/("""}"""),format.raw/*894.17*/(""");
							     		"""),format.raw/*895.15*/("""}"""),format.raw/*895.16*/("""else"""),format.raw/*895.20*/("""{"""),format.raw/*895.21*/("""
							     			"""),format.raw/*896.16*/("""contador++;
							     			if(contador < $imagen.files.length)"""),format.raw/*897.51*/("""{"""),format.raw/*897.52*/("""
							     				"""),format.raw/*898.17*/("""subirArchivo2($imagen, contador, sigla);
							     			"""),format.raw/*899.16*/("""}"""),format.raw/*899.17*/("""else"""),format.raw/*899.21*/("""{"""),format.raw/*899.22*/("""
							     				"""),format.raw/*900.17*/("""alertify.alert('Subidas las imagenes con exito', function () """),format.raw/*900.78*/("""{"""),format.raw/*900.79*/("""
									     			"""),format.raw/*901.18*/("""document.getElementById("imagen").value = "";
									     			$("#textBtnComprimir").text("Ninguna imagen seleccionada");
									     			
									     			document.getElementById("sube1").innerHTML = 
									     				"<a href='/muestraAlbumFotos/"+rs+"'><kbd style='background-color: #7F8C8D'>Ver Album</kbd></a>";
									     			document.getElementById("sube2").innerHTML = 
									     				"<a href='/muestraAlbumFotos/"+rs+"'><kbd style='background-color: #7F8C8D'>Ver Album</kbd></a>";
									     		"""),format.raw/*908.17*/("""}"""),format.raw/*908.18*/(""");
												document.getElementById('enCarga').style.display="none";
							     			"""),format.raw/*910.16*/("""}"""),format.raw/*910.17*/("""
							     		"""),format.raw/*911.15*/("""}"""),format.raw/*911.16*/("""
							     	"""),format.raw/*912.14*/("""}"""),format.raw/*912.15*/("""
							    """),format.raw/*913.12*/("""}"""),format.raw/*913.13*/(""");
					      """),format.raw/*914.12*/("""}"""),format.raw/*914.13*/(""",
					      MIME_TYPE,
					      QUALITY
					    );
					  """),format.raw/*918.8*/("""}"""),format.raw/*918.9*/(""";
				"""),format.raw/*919.5*/("""}"""),format.raw/*919.6*/("""

				"""),format.raw/*921.5*/("""function calculateSize(img, maxWidth, maxHeight) """),format.raw/*921.54*/("""{"""),format.raw/*921.55*/("""
				  """),format.raw/*922.7*/("""let width = img.width;
				  let height = img.height;

				  if (width > height) """),format.raw/*925.27*/("""{"""),format.raw/*925.28*/("""
				    """),format.raw/*926.9*/("""if (width > maxWidth) """),format.raw/*926.31*/("""{"""),format.raw/*926.32*/("""
				      """),format.raw/*927.11*/("""height = Math.round((height * maxWidth) / width);
				      width = maxWidth;
				    """),format.raw/*929.9*/("""}"""),format.raw/*929.10*/("""
				  """),format.raw/*930.7*/("""}"""),format.raw/*930.8*/(""" """),format.raw/*930.9*/("""else """),format.raw/*930.14*/("""{"""),format.raw/*930.15*/("""
				    """),format.raw/*931.9*/("""if (height > maxHeight) """),format.raw/*931.33*/("""{"""),format.raw/*931.34*/("""
				      """),format.raw/*932.11*/("""width = Math.round((width * maxHeight) / height);
				      height = maxHeight;
				    """),format.raw/*934.9*/("""}"""),format.raw/*934.10*/("""
				  """),format.raw/*935.7*/("""}"""),format.raw/*935.8*/("""
				  """),format.raw/*936.7*/("""return [width, height];
				"""),format.raw/*937.5*/("""}"""),format.raw/*937.6*/("""

				"""),format.raw/*939.5*/("""function displayInfo(label, file) """),format.raw/*939.39*/("""{"""),format.raw/*939.40*/("""
				  """),format.raw/*940.7*/("""const p = document.createElement('p');
				  p.innerText = `$"""),format.raw/*941.23*/("""{"""),format.raw/*941.24*/("""label"""),format.raw/*941.29*/("""}"""),format.raw/*941.30*/(""" """),format.raw/*941.31*/("""- $"""),format.raw/*941.34*/("""{"""),format.raw/*941.35*/("""readableBytes(file.size)"""),format.raw/*941.59*/("""}"""),format.raw/*941.60*/("""`;
				"""),format.raw/*942.5*/("""}"""),format.raw/*942.6*/("""

				"""),format.raw/*944.5*/("""function readableBytes(bytes) """),format.raw/*944.35*/("""{"""),format.raw/*944.36*/("""
				  """),format.raw/*945.7*/("""const i = Math.floor(Math.log(bytes) / Math.log(1024)),
				    sizes = ['B', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'];

				  return (bytes / Math.pow(1024, i)).toFixed(2) + ' ' + sizes[i];
				"""),format.raw/*949.5*/("""}"""),format.raw/*949.6*/("""
"""),format.raw/*950.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,guia:tables.Guia,cabezeraOt:String,bodegaDestino:tables.BodegaEmpresa,fechaGuia:String,vistaDetOrigen:String,id_cotizacion:Long,cont:Long,bodegaOrigen:tables.BodegaEmpresa,listaTransporte:List[tables.Transportista],id_comercial:Long,fechaIniTerGuia:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,guia,cabezeraOt,bodegaDestino,fechaGuia,vistaDetOrigen,id_cotizacion,cont,bodegaOrigen,listaTransporte,id_comercial,fechaIniTerGuia)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.Guia,String,tables.BodegaEmpresa,String,String,Long,Long,tables.BodegaEmpresa,List[tables.Transportista],Long,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,guia,cabezeraOt,bodegaDestino,fechaGuia,vistaDetOrigen,id_cotizacion,cont,bodegaOrigen,listaTransporte,id_comercial,fechaIniTerGuia) => apply(mapDiccionario,mapPermiso,userMnu,guia,cabezeraOt,bodegaDestino,fechaGuia,vistaDetOrigen,id_cotizacion,cont,bodegaOrigen,listaTransporte,id_comercial,fechaIniTerGuia)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizar/otDespachoModificar.scala.html
                  HASH: 515dbcc95285b7ea42d7615001758cc2bb453853
                  MATRIX: 1143->1|1611->376|1638->378|1654->386|1693->388|1721->391|1789->439|1819->443|2145->743|2218->795|2253->803|2388->912|2425->928|2457->933|2621->1070|2634->1074|2663->1082|2738->1130|2751->1134|2783->1145|2866->1201|2900->1214|2989->1276|3022->1288|3488->1727|3509->1739|3539->1747|3672->1853|3693->1865|3726->1877|4066->2190|4088->2203|4117->2211|4217->2284|4239->2297|4272->2309|4664->2673|4704->2674|4743->2685|4894->2809|4907->2813|4940->2825|5016->2882|5055->2883|5094->2894|5245->3018|5258->3022|5291->3034|5491->3203|5526->3211|5889->3547|5902->3551|5943->3571|6362->3963|6401->3964|6439->3974|6570->4086|6609->4087|6647->4097|6719->4142|6732->4146|6767->4160|6860->4222|6897->4231|7268->4574|7308->4575|7345->4584|7380->4599|7420->4600|7457->4609|7526->4650|7540->4654|7576->4668|7691->4752|7726->4759|8139->5144|8159->5154|8212->5185|8242->5187|8262->5197|8314->5226|8354->5237|8386->5246|8467->5299|8498->5308|8927->5709|8947->5719|9002->5752|9032->5754|9052->5764|9106->5795|9146->5806|9184->5821|9262->5871|9299->5886|9750->6309|9764->6313|9805->6332|9984->6483|10024->6484|10061->6493|10126->6538|10166->6539|10203->6548|10260->6577|10274->6581|10307->6592|10426->6680|10461->6687|10564->6762|10606->6782|11992->8140|12006->8144|12051->8166|13088->9176|13168->9234|13306->9343|13347->9344|13380->9349|13917->9858|13957->9859|13993->9867|14057->9911|14097->9912|14133->9920|14190->9949|14204->9953|14237->9964|14353->10049|14387->10055|15001->10625|15036->10629|15068->10633|15181->10718|15195->10722|15239->10744|15313->10789|15343->10790|15375->10794|15472->10863|15501->10864|15535->10870|15618->10924|15648->10925|15680->10929|15946->11167|15975->11168|16009->11174|16078->11214|16108->11215|16140->11219|16282->11332|16312->11333|16345->11338|16527->11491|16557->11492|16591->11498|16680->11559|16709->11560|16742->11564|16772->11565|16806->11571|16883->11620|16912->11621|16944->11625|16973->11626|17004->11629|17033->11630|17064->11633|17122->11662|17152->11663|17183->11666|17388->11842|17418->11843|17450->11847|17548->11917|17577->11918|17611->11924|17700->11984|17730->11985|17765->11992|17947->12145|17977->12146|18017->12157|18123->12234|18153->12235|18184->12238|18213->12239|18242->12240|18313->12283|18327->12287|18370->12308|18439->12349|18468->12350|18541->12394|18571->12395|18602->12398|18651->12418|18681->12419|18713->12423|18764->12446|18778->12450|18812->12462|18846->12468|18875->12469|18907->12473|18936->12474|18968->12478|19008->12490|19022->12494|19056->12506|19180->12601|19210->12602|19253->12616|19524->12858|19554->12859|19593->12869|19645->12892|19675->12893|19715->12904|19826->12986|19856->12987|19897->12999|19948->13022|19962->13026|19996->13038|20039->13052|20069->13053|20110->13065|20140->13066|20196->13093|20226->13094|20266->13105|20332->13142|20362->13143|20403->13155|20463->13186|20493->13187|20534->13199|20564->13200|20597->13204|20627->13205|20662->13212|20720->13242|20749->13243|20782->13248|20811->13249|20850->13259|20880->13260|20913->13265|20942->13266|20972->13268|21001->13269|21033->13273|21130->13341|21160->13342|21191->13345|21316->13441|21346->13442|21389->13456|21656->13694|21686->13695|21720->13701|21764->13716|21794->13717|21829->13724|21874->13741|21898->13755|21934->13769|22020->13825|22051->13826|22087->13834|22194->13913|22223->13914|22259->13922|22288->13923|22321->13927|22351->13928|22386->13935|22900->14420|22930->14421|22970->14432|23855->15288|23885->15289|23924->15299|24132->15478|24162->15479|24202->15490|24383->15642|24413->15643|24457->15658|24567->15739|24597->15740|24632->15747|24661->15748|24690->15749|24733->15764|24762->15765|24799->15774|24829->15775|24865->15783|24894->15784|24926->15788|24955->15789|24989->15795|25047->15824|25077->15825|25108->15828|25174->15865|25204->15866|25236->15870|25304->15910|25328->15924|25364->15938|25478->16022|25509->16023|25546->16032|25575->16033|25608->16038|25637->16039|25669->16043|25698->16044|25734->16052|25865->16154|25895->16155|25931->16163|26103->16306|26133->16307|26174->16319|26281->16397|26311->16398|26343->16402|26372->16403|26401->16404|26596->16570|26626->16571|26659->16576|26815->16703|26845->16704|26879->16710|26972->16774|27002->16775|27037->16782|27108->16825|27137->16826|27170->16831|27199->16832|27231->16836|27260->16837|27292->16841|27389->16910|27418->16911|27450->16915|27479->16916|27513->16922|27824->17204|27854->17205|27888->17211|28017->17311|28047->17312|28079->17316|28143->17352|28172->17353|28206->17359|30927->20052|30956->20053|30988->20057|31132->20171|31163->20172|31197->20178|31541->20493|31571->20494|31606->20501|31748->20614|31778->20615|31811->20620|31943->20724|31972->20725|32003->20728|32032->20729|32066->20735|32264->20904|32294->20905|32330->20913|32440->20994|32470->20995|32508->21005|32951->21420|32980->21421|33013->21426|33042->21427|33071->21428|33105->21433|33135->21434|33171->21442|33372->21614|33402->21615|33438->21623|33586->21742|33616->21743|33650->21749|33776->21847|33805->21848|33837->21852|33866->21853|33902->21861|34181->22112|34210->22113|34241->22116|34312->22159|34341->22160|34373->22164|34427->22189|34457->22190|34488->22193|34777->22453|34807->22454|34839->22458|34968->22559|34997->22560|35031->22566|35097->22603|35127->22604|35159->22608|35288->22709|35317->22710|35351->22716|35417->22753|35447->22754|35479->22758|35608->22859|35637->22860|35671->22866|35861->23028|35890->23029|35922->23033|35984->23066|36014->23067|36048->23073|36953->23949|36983->23950|37015->23954|37139->24050|37168->24051|37202->24057|37326->24152|37356->24153|37388->24157|37512->24253|37541->24254|37573->24258|37703->24359|37733->24360|37765->24364|37835->24406|37864->24407|37898->24413|38126->24613|38155->24614|38187->24618|38249->24651|38279->24652|38313->24658|39193->25509|39223->25510|39255->25514|39374->25605|39403->25606|39437->25612|39561->25707|39591->25708|39623->25712|39742->25803|39771->25804|39803->25808|39933->25909|39963->25910|39995->25914|40066->25957|40095->25958|40129->25964|40424->26231|40453->26232|40485->26236|40547->26269|40577->26270|40608->26273|41369->27005|41399->27006|41434->27013|41505->27056|41534->27057|41568->27063|41692->27158|41722->27159|41757->27166|41828->27209|41857->27210|41889->27214|41955->27251|41985->27252|42017->27256|42085->27296|42109->27310|42145->27324|42209->27358|42240->27359|42273->27364|42327->27390|42356->27391|42389->27396|42418->27397|42451->27402|42481->27403|42513->27407|42671->27536|42701->27537|42734->27542|42885->27665|42914->27666|42946->27670|42976->27671|43009->27676|43124->27762|43154->27763|43188->27769|43243->27796|43272->27797|43306->27803|43335->27804|43366->27807|43395->27808|43425->27810|43454->27811|43486->27815|43556->27856|43586->27857|43619->27862|43751->27966|43780->27967|43812->27971|44003->28133|44033->28134|44067->28140|44328->28372|44358->28373|44390->28377|44621->28579|44651->28580|44684->28585|44743->28615|44773->28616|44808->28623|44860->28647|44889->28648|44921->28652|44950->28653|44982->28657|45061->28707|45091->28708|45124->28713|45176->28737|45205->28738|45236->28741|45265->28742|45299->28748|45345->28765|45375->28766|45407->28770|45459->28793|45489->28794|45522->28799|45700->28949|45729->28950|45761->28954|45791->28955|45824->28960|45936->29044|45965->29045|45996->29048|46025->29049|46057->29053|46086->29054|46118->29058|46363->29275|46392->29276|46422->29278|46451->29279|46489->29289|46655->29426|46685->29427|46718->29432|46825->29511|46854->29512|46890->29520|47001->29602|47031->29603|47064->29608|47124->29639|47154->29640|47188->29646|47228->29658|47257->29659|47290->29664|47440->29785|47470->29786|47504->29792|47555->29815|47584->29816|47617->29821|47689->29865|47718->29866|48013->30132|48043->30133|48077->30139|48286->30319|48316->30320|48355->30330|48421->30368|48450->30369|48529->30419|48559->30420|48598->30430|49015->30818|49045->30819|49088->30833|49299->31015|49329->31016|49370->31028|49422->31052|49451->31053|49828->31402|49842->31406|49867->31409|49966->31479|49996->31480|50035->31490|50345->31771|50375->31772|50419->31787|50465->31804|50495->31805|50540->31821|50652->31904|50683->31905|50729->31922|50819->31983|50849->31984|50895->32001|50925->32002|50958->32006|50988->32007|51033->32023|51124->32085|51154->32086|51200->32103|51285->32159|51315->32160|51348->32164|51378->32165|51424->32182|51514->32243|51544->32244|51591->32262|52135->32777|52165->32778|52281->32865|52311->32866|52355->32881|52385->32882|52428->32896|52458->32897|52499->32909|52529->32910|52572->32924|52602->32925|52692->32987|52721->32988|52755->32994|52784->32995|52818->33001|52896->33050|52926->33051|52961->33058|53071->33139|53101->33140|53138->33149|53189->33171|53219->33172|53259->33183|53373->33269|53403->33270|53438->33277|53467->33278|53496->33279|53530->33284|53560->33285|53597->33294|53650->33318|53680->33319|53720->33330|53836->33418|53866->33419|53901->33426|53930->33427|53965->33434|54021->33462|54050->33463|54084->33469|54147->33503|54177->33504|54212->33511|54302->33572|54332->33573|54366->33578|54396->33579|54426->33580|54458->33583|54488->33584|54541->33608|54571->33609|54606->33616|54635->33617|54669->33623|54728->33653|54758->33654|54793->33661|55023->33863|55052->33864|55081->33865
                  LINES: 28->1|36->5|37->6|37->6|37->6|38->7|38->7|40->9|44->13|44->13|46->15|48->17|48->17|49->18|53->22|53->22|53->22|54->23|54->23|54->23|55->24|55->24|56->25|56->25|66->35|66->35|66->35|69->38|69->38|69->38|78->47|78->47|78->47|80->49|80->49|80->49|89->58|89->58|90->59|93->62|93->62|93->62|96->65|96->65|97->66|100->69|100->69|100->69|105->74|106->75|116->85|116->85|116->85|124->93|124->93|125->94|127->96|127->96|128->97|128->97|128->97|128->97|130->99|131->100|135->104|135->104|136->105|137->106|137->106|138->107|138->107|138->107|138->107|141->110|142->111|153->122|153->122|153->122|153->122|153->122|153->122|153->122|153->122|154->123|154->123|166->135|166->135|166->135|166->135|166->135|166->135|166->135|166->135|167->136|167->136|184->153|184->153|184->153|188->157|188->157|189->158|190->159|190->159|191->160|191->160|191->160|191->160|194->163|195->164|200->169|200->169|222->191|222->191|222->191|252->221|252->221|254->223|254->223|256->225|269->238|269->238|270->239|271->240|271->240|272->241|272->241|272->241|272->241|275->244|276->245|297->266|299->268|303->272|306->275|306->275|306->275|308->277|308->277|309->278|311->280|311->280|313->282|313->282|313->282|314->283|320->289|320->289|322->291|322->291|322->291|323->292|324->293|324->293|325->294|327->296|327->296|328->297|330->299|330->299|330->299|330->299|331->300|332->301|332->301|333->302|333->302|334->303|334->303|336->305|336->305|336->305|337->306|340->309|340->309|341->310|342->311|342->311|344->313|346->315|346->315|347->316|350->319|350->319|351->320|352->321|352->321|353->322|353->322|353->322|356->325|356->325|356->325|360->329|360->329|362->331|362->331|363->332|363->332|363->332|364->333|364->333|364->333|364->333|365->334|365->334|365->334|365->334|366->335|366->335|366->335|366->335|369->338|369->338|370->339|377->346|377->346|378->347|378->347|378->347|379->348|379->348|379->348|380->349|380->349|380->349|380->349|381->350|381->350|382->351|382->351|382->351|382->351|383->352|383->352|383->352|384->353|385->354|385->354|386->355|386->355|386->355|386->355|387->356|388->357|388->357|389->358|389->358|390->359|390->359|391->360|391->360|392->361|392->361|394->363|396->365|396->365|397->366|399->368|399->368|400->369|407->376|407->376|408->377|408->377|408->377|409->378|409->378|409->378|409->378|409->378|409->378|410->379|412->381|412->381|413->382|413->382|413->382|413->382|414->383|427->396|427->396|428->397|445->414|445->414|446->415|452->421|452->421|453->422|456->425|456->425|457->426|458->427|458->427|459->428|459->428|459->428|461->430|461->430|462->431|462->431|463->432|463->432|464->433|464->433|467->436|467->436|467->436|468->437|468->437|468->437|469->438|469->438|469->438|469->438|469->438|469->438|471->440|471->440|472->441|472->441|472->441|472->441|474->443|475->444|475->444|476->445|479->448|479->448|480->449|481->450|481->450|482->451|482->451|482->451|486->455|486->455|487->456|489->458|489->458|490->459|491->460|491->460|492->461|493->462|493->462|494->463|494->463|495->464|495->464|496->465|498->467|498->467|499->468|499->468|504->473|518->487|518->487|520->489|521->490|521->490|522->491|523->492|523->492|525->494|591->560|591->560|593->562|593->562|593->562|595->564|601->570|601->570|602->571|603->572|603->572|604->573|607->576|607->576|608->577|608->577|610->579|613->582|613->582|615->584|615->584|615->584|617->586|623->592|623->592|624->593|624->593|624->593|624->593|624->593|626->595|628->597|628->597|629->598|630->599|630->599|631->600|633->602|633->602|634->603|634->603|636->605|639->608|639->608|640->609|642->611|642->611|644->613|644->613|644->613|645->614|653->622|653->622|654->623|657->626|657->626|659->628|659->628|659->628|660->629|663->632|663->632|665->634|665->634|665->634|666->635|669->638|669->638|671->640|674->643|674->643|676->645|676->645|676->645|678->647|694->663|694->663|695->664|697->666|697->666|699->668|700->669|700->669|701->670|703->672|703->672|705->674|706->675|706->675|707->676|708->677|708->677|710->679|713->682|713->682|715->684|715->684|715->684|717->686|733->702|733->702|734->703|736->705|736->705|738->707|739->708|739->708|740->709|742->711|742->711|744->713|745->714|745->714|746->715|747->716|747->716|749->718|753->722|753->722|755->724|755->724|755->724|756->725|769->738|769->738|770->739|771->740|771->740|773->742|774->743|774->743|775->744|776->745|776->745|778->747|778->747|778->747|779->748|779->748|779->748|779->748|779->748|779->748|780->749|781->750|781->750|782->751|782->751|782->751|782->751|783->752|785->754|785->754|786->755|788->757|788->757|788->757|788->757|789->758|789->758|789->758|790->759|791->760|791->760|792->761|792->761|793->762|793->762|794->763|794->763|796->765|796->765|796->765|797->766|799->768|799->768|801->770|802->771|802->771|804->773|811->780|811->780|812->781|815->784|815->784|816->785|816->785|816->785|817->786|818->787|818->787|819->788|819->788|820->789|820->789|820->789|821->790|822->791|822->791|823->792|823->792|825->794|825->794|825->794|826->795|826->795|826->795|827->796|830->799|830->799|830->799|830->799|831->800|833->802|833->802|834->803|834->803|834->803|834->803|835->804|839->808|839->808|840->809|840->809|845->814|851->820|851->820|852->821|853->822|853->822|855->824|855->824|855->824|856->825|856->825|856->825|857->826|858->827|858->827|859->828|861->830|861->830|862->831|863->832|863->832|864->833|865->834|865->834|877->846|877->846|878->847|882->851|882->851|883->852|884->853|884->853|887->856|887->856|888->857|896->865|896->865|897->866|900->869|900->869|901->870|902->871|902->871|910->879|910->879|910->879|912->881|912->881|913->882|921->890|921->890|922->891|922->891|922->891|923->892|923->892|923->892|924->893|925->894|925->894|926->895|926->895|926->895|926->895|927->896|928->897|928->897|929->898|930->899|930->899|930->899|930->899|931->900|931->900|931->900|932->901|939->908|939->908|941->910|941->910|942->911|942->911|943->912|943->912|944->913|944->913|945->914|945->914|949->918|949->918|950->919|950->919|952->921|952->921|952->921|953->922|956->925|956->925|957->926|957->926|957->926|958->927|960->929|960->929|961->930|961->930|961->930|961->930|961->930|962->931|962->931|962->931|963->932|965->934|965->934|966->935|966->935|967->936|968->937|968->937|970->939|970->939|970->939|971->940|972->941|972->941|972->941|972->941|972->941|972->941|972->941|972->941|972->941|973->942|973->942|975->944|975->944|975->944|976->945|980->949|980->949|981->950
                  -- GENERATED --
              */
          