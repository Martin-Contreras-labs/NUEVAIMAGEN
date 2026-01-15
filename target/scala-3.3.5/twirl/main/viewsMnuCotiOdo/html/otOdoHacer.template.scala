
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

object otOdoHacer extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template10[Map[String,String],Map[String,String],utilities.UserMnu,forms.FormOtOdo,List[List[String]],String,List[tables.Proyecto],List[tables.Regiones],List[tables.OperadorServicio],List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
formOtOdo: forms.FormOtOdo, listBodegas: List[List[String]], tabla: String, listProyectos: List[tables.Proyecto], listRegiones: List[tables.Regiones],
listOperadoresServicio: List[tables.OperadorServicio], listEquipos: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.1*/("""
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
		
		"""),format.raw/*10.3*/("""<!-- MODAL CREA PROYECTOS -->
			"""),_display_(/*11.5*/modalProyectoNuevo(mapDiccionario, listRegiones)),format.raw/*11.53*/("""
			"""),format.raw/*12.4*/("""<script>
				const proyectoGrabaAjax = (id_proyecto) =>"""),format.raw/*13.47*/("""{"""),format.raw/*13.48*/("""
					"""),format.raw/*14.6*/("""$('#id_proyecto').val(id_proyecto);
					$('#nombreProyecto').val($("#proyectoNickName").val());
					let tabla = document.getElementById('tablaListaProyectos');
					let row = tabla.insertRow(tabla.rows.length);
					let cell = row.insertCell(0);
					cell.style.textAlign = "left";
					cell.innerHTML = $("#proyectoNickName").val();
					cell = row.insertCell(1);
					cell.style.textAlign = "left";
					cell.innerHTML = $("#proyectoNombre").val();
					row.setAttribute("onClick", "selectProyecto('" + id_proyecto + "')");
					row.setAttribute("data-dismiss", "modal");
					$("#id_proyecto").val(id_proyecto);
					$("#nickProyecto").val($("#proyectoNickName").val());
				"""),format.raw/*28.5*/("""}"""),format.raw/*28.6*/("""
			"""),format.raw/*29.4*/("""</script>
		<!-- FIN MODAL CREA PROYECTOS -->
		

		<div id="mostrarmostrar" style="display:none;">
			"""),_display_(/*34.5*/barraTitulo(mapDiccionario, "COTIZACION ODO PARA EMITIR "+mapDiccionario.get("ORDEN_DE_TRABAJO"), "")),format.raw/*34.106*/("""
			"""),format.raw/*35.4*/("""<form action="/routes2/otOdoGrabar/" enctype="multipart/form-data" method="POST" onsubmit="return validarForm();">
				<h6>"""),_display_(/*36.10*/mapDiccionario/*36.24*/.get("ORDEN_DE_TRABAJO")),format.raw/*36.48*/(""":</h6>
				<div class="row  justify-content-md-center">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<table class="table table-sm table-hover table-bordered table-condensed table-fluid">
							<thead style="background-color: #eeeeee">
								<tr>
									<td width="260px">
										<input type="hidden" id="id_otOdo" name="id_otOdo" value=""""),_display_(/*43.70*/formOtOdo/*43.79*/.id_otOdo),format.raw/*43.88*/("""">
										<input type="hidden" id="id_cotiOdo" name="id_cotiOdo" value=""""),_display_(/*44.74*/formOtOdo/*44.83*/.id_cotiOdo),format.raw/*44.94*/("""">
										<div class="input-group">
									  		<div class="input-group-prepend">
									    		<span class="input-group-text" id="basic-addon1">Nro. """),_display_(/*47.71*/mapDiccionario/*47.85*/.get("Orden_de_trabajo")),format.raw/*47.109*/("""</span>
									  		</div>
									  		<input type="text" class="form-control center"
									  				name="numeroOt"
													value = """"),_display_(/*51.24*/formOtOdo/*51.33*/.numeroOt),format.raw/*51.42*/(""""
													readonly>
										</div>
									</td>
									<td width="230px">
										<div class="input-group">
									  		<div class="input-group-prepend">
									    		<span class="input-group-text" id="basic-addon1">Fecha</span>
									  		</div>
									  		<input type="date" class="form-control center"
									  			name="fechaOt" 
									  			id="fechaOt"
									  			onblur="if(!limitaFecha(value,720,10)) value='"""),_display_(/*63.62*/formOtOdo/*63.71*/.fechaOt),format.raw/*63.79*/("""';"
									  			value=""""),_display_(/*64.23*/formOtOdo/*64.32*/.fechaOt),format.raw/*64.40*/(""""
							        			required>
										</div>
									</td>
									<td width="32%">
										<input type="hidden" id="id_bodegaEmpresa" name="id_bodegaEmpresa" value=""""),_display_(/*69.86*/formOtOdo/*69.95*/.id_bodegaEmpresa),format.raw/*69.112*/("""">
										<div class="input-group">
									  		<div class="input-group-prepend">
									    		<span class="input-group-text" id="basic-addon1">"""),_display_(/*72.66*/mapDiccionario/*72.80*/.get("BODEGA")),format.raw/*72.94*/("""/PROYECTO</span>
									  		</div>
											"""),_display_(if(formOtOdo.id_bodegaEmpresa > 0)/*74.47*/ {_display_(Seq[Any](format.raw/*74.49*/("""
										  		"""),format.raw/*75.15*/("""<input type="text" class="form-control left"
														value = """"),_display_(/*76.25*/formOtOdo/*76.34*/.nombreBodega),format.raw/*76.47*/(""""
														readonly>
											""")))}else/*78.17*/{_display_(Seq[Any](format.raw/*78.18*/("""
												"""),format.raw/*79.13*/("""<input type="text" class="form-control"
			  										id="nombreBodega"
			  										value="" 
			  										style="background:white"
			  										onclick="$('#listaBodega').modal('show');"
			  										readonly>
											""")))}),format.raw/*85.13*/("""
										"""),format.raw/*86.11*/("""</div>
									</td>
									
									
									<td width="32%">
										<input type="hidden" id="id_proyecto" name="id_proyecto" value=""""),_display_(/*91.76*/formOtOdo/*91.85*/.id_proyecto),format.raw/*91.97*/("""">
										<div class="input-group">
									  		<div class="input-group-prepend">
									    		<span class="input-group-text" id="basic-addon1">NOMBRE PROYECTO</span>
									  		</div>
											<input type="text" class="form-control"
		  										id="nickProyecto"
		  										value=""""),_display_(/*98.23*/formOtOdo/*98.32*/.nickProyecto),format.raw/*98.45*/("""" 
		  										style="background:white"
		  										onclick="$('#listaProyecto').modal('show');"
		  										readonly>
										</div>
									</td>
									
									<td align="center">
										<span class="btn btn-info btn-sm btn-file" style="font-size: 8px;">
											<div id="txtBtn">Adjuntar</div>
					    					<input type="file" id="docAdjunto" name="docAdjunto" onchange="LimitAttach(this.form, this.form.docAdjunto.value);">
										</span>
									</td>
								</tr>
								<tr>
									<td width="15%">
										<div class="input-group">
									  		<div class="input-group-prepend">
									    		<span class="input-group-text" id="basic-addon1">OPERADOR</span>
									  		</div>
									
											<select name="id_operadorServicio" class="custom-select"  style="width: 80px;">
												<option value="0"></option>
												"""),_display_(/*121.14*/for(lista2 <- listOperadoresServicio) yield /*121.51*/{_display_(Seq[Any](format.raw/*121.52*/("""
													"""),format.raw/*122.14*/("""<option value=""""),_display_(/*122.30*/lista2/*122.36*/.getId()),format.raw/*122.44*/("""">"""),_display_(/*122.47*/lista2/*122.53*/.getNombre()),format.raw/*122.65*/("""</option>
												""")))}),format.raw/*123.14*/("""
											"""),format.raw/*124.12*/("""</select>
										</div>
									</td>
									<td width="30%">
										<div class="input-group">
									  		<div class="input-group-prepend">
									    		<span class="input-group-text" id="basic-addon1">Fecha de Servicio</span>
									  		</div>
											<input type="date" class="form-control center"
									  			name="fechaEnvio"
									  			onblur="if(!limitaFecha(value,30,360)) value='';">
											
										</div>
									</td>
									<td colspan="20">
										<div class="input-group">
									  		<div class="input-group-prepend">
									    		<span class="input-group-text" id="basic-addon1">Observaciones</span>
									  		</div>
		  									<textarea class="form-control" 
		  										name="observaciones" 
		  										autocomplete="off">"""),_display_(/*145.35*/formOtOdo/*145.44*/.observaciones),format.raw/*145.58*/("""</textarea>
										</div>
									</td>
								</tr>
							</thead>
						</table>
					</div>
				</div>
				<hr>
				<h6>COTIZACION:</h6>
				<div class="row  justify-content-md-center">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						"""),_display_(/*157.8*/Html(tabla)),format.raw/*157.19*/("""
					"""),format.raw/*158.6*/("""</div>
				</div>
				
				<div class="noprint" align="left">
					<div class="row justify-content-md-center" >
						<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
							<input type="button"  value="Cancelar" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
						</div>
						<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
							<input type="submit"  id="aplica" value='GENERAR """),_display_(/*167.58*/mapDiccionario/*167.72*/.get("OT")),format.raw/*167.82*/("""' class="btn btn-success btn-sm rounded-pill btn-block">
						</div>
					</div>
				</div>
			</form>
		</div>
		
		<div id='listaBodega' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
			<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
				<div class='modal-content'>
					<div class='modal-header'>
						<h5 class='modal-title'>SELECCIONAR """),_display_(/*178.44*/mapDiccionario/*178.58*/.get("BODEGA")),format.raw/*178.72*/("""/PROYECTO</h5>
					        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
					          <span aria-hidden='true'>&times;</span>
					        </button>
					</div>
					<div class='modal-body'>
						<table id="tablaListaBodegas" class='table table-sm table-bordered table-condensed table-hover table-fluid'>
							<thead style="background-color: #eeeeee">
								<TR>
									<TH>"""),_display_(/*187.15*/mapDiccionario/*187.29*/.get("BODEGA")),format.raw/*187.43*/("""/PROYECTO</TH>
									<TH>CLIENTE</TH>
							        <TH>PROYECTO</TH>
								</TR>
							</thead>
							<tbody>
								"""),_display_(/*193.10*/for(lista1 <- listBodegas) yield /*193.36*/{_display_(Seq[Any](format.raw/*193.37*/("""
									"""),format.raw/*194.10*/("""<TR onclick="selectBodega("""),_display_(/*194.37*/lista1/*194.43*/.get(1)),format.raw/*194.50*/(""")">
										<td  style="text-align:left;"><div id="bodega_"""),_display_(/*195.58*/lista1/*195.64*/.get(1)),format.raw/*195.71*/("""">"""),_display_(/*195.74*/lista1/*195.80*/.get(5)),format.raw/*195.87*/("""</div></td>
										<td  style="text-align:left;">"""),_display_(/*196.42*/lista1/*196.48*/.get(7)),format.raw/*196.55*/("""</td>
										<td  style="text-align:left;">"""),_display_(/*197.42*/lista1/*197.48*/.get(8)),format.raw/*197.55*/("""</td>
									</TR>
					 			""")))}),format.raw/*199.11*/("""
							"""),format.raw/*200.8*/("""</tbody>
						</table>
		   				<div class='row'>
							<div class='col-sm-12' style='text-align:center'>
								<button type='button' class='btn btn-sm  btn-warning' style='font-size: 10px;' data-dismiss='modal'>Cancelar</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div id='listaProyecto' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
			<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
				<div class='modal-content'>
					<div class='modal-header'>
						<h5 class='modal-title'>SELECCIONAR PROYECTO</h5>
					        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
					          <span aria-hidden='true'>&times;</span>
					        </button>
					</div>
					<div class='modal-body'>
						<div class="noprint" align="center">
							<a href="#" onclick="$('#listaProyecto').modal('hide'); $('#modalProyectoNuevo').modal('show');">
								<kbd style="background-color: #73C6B6">Agregar Nuevo</kbd>
							</a>
						</div>
						<table id="tablaListaProyectos" class='table table-sm table-bordered table-condensed table-hover table-fluid'>
							<thead style="background-color: #eeeeee">
								<TR>
									<TH>NOMBRE CORTO</TH>
									<TH>NOMBRE LARGO</TH>
								</TR>
							</thead>
							<tbody>
								"""),_display_(/*235.10*/for(lista1 <- listProyectos) yield /*235.38*/{_display_(Seq[Any](format.raw/*235.39*/("""
									"""),format.raw/*236.10*/("""<TR onclick="selectProyecto("""),_display_(/*236.39*/lista1/*236.45*/.id),format.raw/*236.48*/(""")">
										<td  style="text-align:left;"><div id="proyecto_"""),_display_(/*237.60*/lista1/*237.66*/.id),format.raw/*237.69*/("""">"""),_display_(/*237.72*/lista1/*237.78*/.nickName),format.raw/*237.87*/("""</div></td>
										<td  style="text-align:left;">"""),_display_(/*238.42*/lista1/*238.48*/.nombre),format.raw/*238.55*/("""</td>
									</TR>
					 			""")))}),format.raw/*240.11*/("""
							"""),format.raw/*241.8*/("""</tbody>
						</table>
		   				<div class='row'>
							<div class='col-sm-12' style='text-align:center'>
								<button type='button' class='btn btn-sm  btn-warning' style='font-size: 10px;' data-dismiss='modal'>Cancelar</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	<div id="listaEquipos" class="modal" role="dialog" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog modal-dialog-scrollable modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">SELECCIONAR EQUIPO ASOCIADO</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<table id="tablaListaEquipos" class="table table-sm table-bordered table-condensed table-hover table-fluid">
						<thead style="background-color: #eeeeee">
							<TR>
								<TH>CODIGO</TH>
								<TH>EQUIPO</TH>
								<TH>UBICACION</TH>
							</TR>
						</thead>
						<tbody>
							<TR onClick=
									"$('#equipoAsociado_'+auxEquipoAsociado).val(' - ');
										modificaPorCampo(auxId_cotiOdoDetalle,'id_equipo',0);"
									data-dismiss="modal">
								<td  style="text-align:left;">Sin Equipo</td>
								<td  style="text-align:left;">Sin Equipo</td>
								<td  style="text-align:left;">Sin Equipo</td>
							</TR>
							"""),_display_(/*280.9*/for(lista1 <- listEquipos) yield /*280.35*/{_display_(Seq[Any](format.raw/*280.36*/("""
								"""),format.raw/*281.9*/("""<TR onClick=
									"$('#equipoAsociado_'+auxEquipoAsociado).val('"""),_display_(/*282.57*/lista1/*282.63*/.get(1)),format.raw/*282.70*/(""" """),format.raw/*282.71*/("""- """),_display_(/*282.74*/lista1/*282.80*/.get(2)),format.raw/*282.87*/("""');
										modificaPorCampo(auxId_cotiOdoDetalle,'id_equipo',"""),_display_(/*283.62*/lista1/*283.68*/.get(0)),format.raw/*283.75*/(""");"
									data-dismiss="modal">
									<td  style="text-align:left;">"""),_display_(/*285.41*/lista1/*285.47*/.get(1)),format.raw/*285.54*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*286.41*/lista1/*286.47*/.get(2)),format.raw/*286.54*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*287.41*/lista1/*287.47*/.get(3)),format.raw/*287.54*/("""</td>
								</TR>
							""")))}),format.raw/*289.9*/("""
						"""),format.raw/*290.7*/("""</tbody>
					</table>
					<div class="row">
						<div class="col-sm-12" style="text-align:center">
							<button type="button" class="btn btn-sm  btn-warning" style="font-size: 10px;" data-dismiss="modal">Cancelar</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

		
		
	
	
""")))}),format.raw/*306.2*/("""



"""),format.raw/*310.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*312.31*/("""{"""),format.raw/*312.32*/("""
		
		"""),format.raw/*314.3*/("""$('#tablaListaBodegas').DataTable("""),format.raw/*314.37*/("""{"""),format.raw/*314.38*/("""
	    	"""),format.raw/*315.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
	    	"order": [[ 0, "asc" ]],
	    	"language": """),format.raw/*318.19*/("""{"""),format.raw/*318.20*/("""
	        	"""),format.raw/*319.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*320.10*/("""}"""),format.raw/*320.11*/("""
	  	"""),format.raw/*321.5*/("""}"""),format.raw/*321.6*/(""" """),format.raw/*321.7*/(""");

		$('#tablaListaProyectos').DataTable("""),format.raw/*323.39*/("""{"""),format.raw/*323.40*/("""
	    	"""),format.raw/*324.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
	    	"order": [[ 0, "asc" ]],
	    	"language": """),format.raw/*327.19*/("""{"""),format.raw/*327.20*/("""
	        	"""),format.raw/*328.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*329.10*/("""}"""),format.raw/*329.11*/("""
	  	"""),format.raw/*330.5*/("""}"""),format.raw/*330.6*/(""" """),format.raw/*330.7*/(""");

		$('#tablaListaEquipos').DataTable("""),format.raw/*332.37*/("""{"""),format.raw/*332.38*/("""
			"""),format.raw/*333.4*/(""""fixedHeader": true,
			"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
			"order": [[ 1, "asc" ]],
			"language": """),format.raw/*336.16*/("""{"""),format.raw/*336.17*/("""
				"""),format.raw/*337.5*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
			"""),format.raw/*338.4*/("""}"""),format.raw/*338.5*/("""
		"""),format.raw/*339.3*/("""}"""),format.raw/*339.4*/(""" """),format.raw/*339.5*/(""");

		document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*342.2*/("""}"""),format.raw/*342.3*/(""");

	let auxId_cotiOdoDetalle = "0";
	let auxEquipoAsociado = "0";

	const modificaPorCampo = (id_cotiOdoDetalle, campo, valor) =>"""),format.raw/*347.63*/("""{"""),format.raw/*347.64*/("""
		"""),format.raw/*348.3*/("""var formData = new FormData();
		formData.append('id_cotiOdoDetalle',id_cotiOdoDetalle);
		formData.append('campo',campo);
		formData.append('valor',valor);
		$.ajax("""),format.raw/*352.10*/("""{"""),format.raw/*352.11*/("""
			"""),format.raw/*353.4*/("""url: "/routes2/cotiOdoModifyXCampo/",
			type: "POST",
			method: "POST",
			data: formData,
			cache: false,
			contentType: false,
			processData: false,
			success: function(respuesta)"""),format.raw/*360.32*/("""{"""),format.raw/*360.33*/("""}"""),format.raw/*360.34*/("""
		"""),format.raw/*361.3*/("""}"""),format.raw/*361.4*/(""");
	"""),format.raw/*362.2*/("""}"""),format.raw/*362.3*/("""
	
	"""),format.raw/*364.2*/("""const selectBodega = (id_bodega) =>"""),format.raw/*364.37*/("""{"""),format.raw/*364.38*/("""
		"""),format.raw/*365.3*/("""$("#id_bodegaEmpresa").val(id_bodega);
		$("#nombreBodega").val($("#bodega_"+id_bodega).text());
		$('#listaBodega').modal('hide');
	"""),format.raw/*368.2*/("""}"""),format.raw/*368.3*/("""
	
	"""),format.raw/*370.2*/("""const selectProyecto = (id_proyecto) =>"""),format.raw/*370.41*/("""{"""),format.raw/*370.42*/("""
		"""),format.raw/*371.3*/("""$("#id_proyecto").val(id_proyecto);
		$("#nickProyecto").val($("#proyecto_"+id_proyecto).text());
		$('#listaProyecto').modal('hide');
	"""),format.raw/*374.2*/("""}"""),format.raw/*374.3*/("""
	
	"""),format.raw/*376.2*/("""const validarForm = () =>"""),format.raw/*376.27*/("""{"""),format.raw/*376.28*/("""
		"""),format.raw/*377.3*/("""let flag = true;
		let id_bodegaEmpresa = $("#id_bodegaEmpresa").val();
		if(id_bodegaEmpresa == 0)"""),format.raw/*379.28*/("""{"""),format.raw/*379.29*/("""
			"""),format.raw/*380.4*/("""flag = false;
			alertify.alert('DEBE ASOCIAR LA ORDEN A """),_display_(/*381.45*/mapDiccionario/*381.59*/.get("BODEGA")),format.raw/*381.73*/("""/PROYECTO', function () """),format.raw/*381.97*/("""{"""),format.raw/*381.98*/("""
				"""),format.raw/*382.5*/("""document.getElementById("nombreBodega").focus();
				return(flag);
     		"""),format.raw/*384.8*/("""}"""),format.raw/*384.9*/(""");
		"""),format.raw/*385.3*/("""}"""),format.raw/*385.4*/("""
		"""),format.raw/*386.3*/("""return(flag);
	"""),format.raw/*387.2*/("""}"""),format.raw/*387.3*/("""
	
	"""),format.raw/*389.2*/("""let extArray = new Array(".gif", ".jpg", ".png", ".jpeg", ".pdf", ".xls", ".doc", ".xlsx", ".docx", ".tar", ".zip", ".rar");
	const LimitAttach = (form, file) => """),format.raw/*390.38*/("""{"""),format.raw/*390.39*/("""
		"""),format.raw/*391.3*/("""let allowSubmit = false;
		if (!file) return;
		while (file.indexOf("\\") != -1)"""),format.raw/*393.35*/("""{"""),format.raw/*393.36*/("""
			"""),format.raw/*394.4*/("""file = file.slice(file.indexOf("\\") + 1);
		"""),format.raw/*395.3*/("""}"""),format.raw/*395.4*/("""
		"""),format.raw/*396.3*/("""let extencion = file.slice(file.lastIndexOf(".")).toLowerCase();
		for (var i = 0; i < extArray.length; i++) """),format.raw/*397.45*/("""{"""),format.raw/*397.46*/("""
			"""),format.raw/*398.4*/("""if (extArray[i] == extencion) """),format.raw/*398.34*/("""{"""),format.raw/*398.35*/(""" """),format.raw/*398.36*/("""allowSubmit = true; break; """),format.raw/*398.63*/("""}"""),format.raw/*398.64*/("""
		"""),format.raw/*399.3*/("""}"""),format.raw/*399.4*/("""
		"""),format.raw/*400.3*/("""if (allowSubmit) """),format.raw/*400.20*/("""{"""),format.raw/*400.21*/("""
			"""),format.raw/*401.4*/("""var file = $("#docAdjunto")[0].files[0];
			var fileSize = file.size; //tamaño del archivo
			if(fileSize>100000000)"""),format.raw/*403.26*/("""{"""),format.raw/*403.27*/("""
				"""),format.raw/*404.5*/("""alert("Se permiten únicamente archivos que no superen los 100 MB,"
				+" el que se intenta subir pesa: "+Math.round(file.size/10)/100+" KB");
				form.docAdjunto.value="";
			"""),format.raw/*407.4*/("""}"""),format.raw/*407.5*/("""else"""),format.raw/*407.9*/("""{"""),format.raw/*407.10*/("""
				"""),format.raw/*408.5*/("""alert("Documento subido con éxito");
				$("#txtBtn").text("Cambiar Documento");
			"""),format.raw/*410.4*/("""}"""),format.raw/*410.5*/("""
		"""),format.raw/*411.3*/("""}"""),format.raw/*411.4*/("""else"""),format.raw/*411.8*/("""{"""),format.raw/*411.9*/("""
			"""),format.raw/*412.4*/("""alert("Se permiten únicamente archivos con la extención: " 
			+ (extArray.join("  ")) + "\nPor favor, seleccione otro archivo "
			+ "e intente de nuevo.");
			form.docAdjunto.value="";
		"""),format.raw/*416.3*/("""}"""),format.raw/*416.4*/("""
	"""),format.raw/*417.2*/("""}"""),format.raw/*417.3*/("""
	

	
"""),format.raw/*421.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,formOtOdo:forms.FormOtOdo,listBodegas:List[List[String]],tabla:String,listProyectos:List[tables.Proyecto],listRegiones:List[tables.Regiones],listOperadoresServicio:List[tables.OperadorServicio],listEquipos:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,formOtOdo,listBodegas,tabla,listProyectos,listRegiones,listOperadoresServicio,listEquipos)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,forms.FormOtOdo,List[List[String]],String,List[tables.Proyecto],List[tables.Regiones],List[tables.OperadorServicio],List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,formOtOdo,listBodegas,tabla,listProyectos,listRegiones,listOperadoresServicio,listEquipos) => apply(mapDiccionario,mapPermiso,userMnu,formOtOdo,listBodegas,tabla,listProyectos,listRegiones,listOperadoresServicio,listEquipos)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotiOdo/otOdoHacer.scala.html
                  HASH: a5e5d66a0bad89d2eaa2ffcf22dd5a176f495278
                  MATRIX: 1145->1|1574->337|1601->339|1617->347|1656->349|1685->353|1753->401|1788->409|1848->443|1917->491|1948->495|2031->550|2060->551|2093->557|2800->1237|2828->1238|2859->1242|2989->1346|3112->1447|3143->1451|3294->1575|3317->1589|3362->1613|3755->1979|3773->1988|3803->1997|3906->2073|3924->2082|3956->2093|4139->2249|4162->2263|4208->2287|4377->2429|4395->2438|4425->2447|4895->2890|4913->2899|4942->2907|4995->2933|5013->2942|5042->2950|5242->3123|5260->3132|5299->3149|5477->3300|5500->3314|5535->3328|5645->3411|5685->3413|5728->3428|5824->3497|5842->3506|5876->3519|5937->3561|5976->3562|6017->3575|6281->3808|6320->3819|6490->3962|6508->3971|6541->3983|6866->4281|6884->4290|6918->4303|7817->5174|7871->5211|7911->5212|7954->5226|7998->5242|8014->5248|8044->5256|8075->5259|8091->5265|8125->5277|8180->5300|8221->5312|9038->6101|9057->6110|9093->6124|9381->6385|9414->6396|9448->6402|9903->6829|9927->6843|9959->6853|10387->7253|10411->7267|10447->7281|10891->7697|10915->7711|10951->7725|11107->7853|11150->7879|11190->7880|11229->7890|11284->7917|11300->7923|11329->7930|11418->7991|11434->7997|11463->8004|11494->8007|11510->8013|11539->8020|11620->8073|11636->8079|11665->8086|11740->8133|11756->8139|11785->8146|11848->8177|11884->8185|13262->9535|13307->9563|13347->9564|13386->9574|13443->9603|13459->9609|13484->9612|13575->9675|13591->9681|13616->9684|13647->9687|13663->9693|13694->9702|13775->9755|13791->9761|13820->9768|13883->9799|13919->9807|15385->11246|15428->11272|15468->11273|15505->11282|15602->11351|15618->11357|15647->11364|15677->11365|15708->11368|15724->11374|15753->11381|15846->11446|15862->11452|15891->11459|15994->11534|16010->11540|16039->11547|16113->11593|16129->11599|16158->11606|16232->11652|16248->11658|16277->11665|16336->11693|16371->11700|16704->12002|16736->12006|16828->12069|16858->12070|16892->12076|16955->12110|16985->12111|17020->12118|17192->12261|17222->12262|17262->12273|17368->12350|17398->12351|17431->12356|17460->12357|17489->12358|17560->12400|17590->12401|17625->12408|17797->12551|17827->12552|17867->12563|17973->12640|18003->12641|18036->12646|18065->12647|18094->12648|18163->12688|18193->12689|18225->12693|18388->12827|18418->12828|18451->12833|18550->12904|18579->12905|18610->12908|18639->12909|18668->12910|18768->12982|18797->12983|18956->13113|18986->13114|19017->13117|19212->13283|19242->13284|19274->13288|19490->13475|19520->13476|19550->13477|19581->13480|19610->13481|19642->13485|19671->13486|19703->13490|19767->13525|19797->13526|19828->13529|19989->13662|20018->13663|20050->13667|20118->13706|20148->13707|20179->13710|20343->13846|20372->13847|20404->13851|20458->13876|20488->13877|20519->13880|20647->13979|20677->13980|20709->13984|20795->14042|20819->14056|20855->14070|20908->14094|20938->14095|20971->14100|21073->14174|21102->14175|21135->14180|21164->14181|21195->14184|21238->14199|21267->14200|21299->14204|21490->14366|21520->14367|21551->14370|21660->14450|21690->14451|21722->14455|21795->14500|21824->14501|21855->14504|21993->14613|22023->14614|22055->14618|22114->14648|22144->14649|22174->14650|22230->14677|22260->14678|22291->14681|22320->14682|22351->14685|22397->14702|22427->14703|22459->14707|22604->14823|22634->14824|22667->14829|22871->15005|22900->15006|22932->15010|22962->15011|22995->15016|23107->15100|23136->15101|23167->15104|23196->15105|23228->15109|23257->15110|23289->15114|23506->15303|23535->15304|23565->15306|23594->15307|23628->15313
                  LINES: 28->1|35->4|36->5|36->5|36->5|38->7|38->7|41->10|42->11|42->11|43->12|44->13|44->13|45->14|59->28|59->28|60->29|65->34|65->34|66->35|67->36|67->36|67->36|74->43|74->43|74->43|75->44|75->44|75->44|78->47|78->47|78->47|82->51|82->51|82->51|94->63|94->63|94->63|95->64|95->64|95->64|100->69|100->69|100->69|103->72|103->72|103->72|105->74|105->74|106->75|107->76|107->76|107->76|109->78|109->78|110->79|116->85|117->86|122->91|122->91|122->91|129->98|129->98|129->98|152->121|152->121|152->121|153->122|153->122|153->122|153->122|153->122|153->122|153->122|154->123|155->124|176->145|176->145|176->145|188->157|188->157|189->158|198->167|198->167|198->167|209->178|209->178|209->178|218->187|218->187|218->187|224->193|224->193|224->193|225->194|225->194|225->194|225->194|226->195|226->195|226->195|226->195|226->195|226->195|227->196|227->196|227->196|228->197|228->197|228->197|230->199|231->200|266->235|266->235|266->235|267->236|267->236|267->236|267->236|268->237|268->237|268->237|268->237|268->237|268->237|269->238|269->238|269->238|271->240|272->241|311->280|311->280|311->280|312->281|313->282|313->282|313->282|313->282|313->282|313->282|313->282|314->283|314->283|314->283|316->285|316->285|316->285|317->286|317->286|317->286|318->287|318->287|318->287|320->289|321->290|337->306|341->310|343->312|343->312|345->314|345->314|345->314|346->315|349->318|349->318|350->319|351->320|351->320|352->321|352->321|352->321|354->323|354->323|355->324|358->327|358->327|359->328|360->329|360->329|361->330|361->330|361->330|363->332|363->332|364->333|367->336|367->336|368->337|369->338|369->338|370->339|370->339|370->339|373->342|373->342|378->347|378->347|379->348|383->352|383->352|384->353|391->360|391->360|391->360|392->361|392->361|393->362|393->362|395->364|395->364|395->364|396->365|399->368|399->368|401->370|401->370|401->370|402->371|405->374|405->374|407->376|407->376|407->376|408->377|410->379|410->379|411->380|412->381|412->381|412->381|412->381|412->381|413->382|415->384|415->384|416->385|416->385|417->386|418->387|418->387|420->389|421->390|421->390|422->391|424->393|424->393|425->394|426->395|426->395|427->396|428->397|428->397|429->398|429->398|429->398|429->398|429->398|429->398|430->399|430->399|431->400|431->400|431->400|432->401|434->403|434->403|435->404|438->407|438->407|438->407|438->407|439->408|441->410|441->410|442->411|442->411|442->411|442->411|443->412|447->416|447->416|448->417|448->417|452->421
                  -- GENERATED --
              */
          