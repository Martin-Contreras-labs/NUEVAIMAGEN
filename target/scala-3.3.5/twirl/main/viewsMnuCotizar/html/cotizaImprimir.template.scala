
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

object cotizaImprimir extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template7[Map[String,String],Map[String,String],utilities.UserMnu,Long,String,List[tables.Grupo],String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
id_cotizacion: Long, tabla: String, listGrupos: List[tables.Grupo], tasaIva: String ):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
		
		"""),format.raw/*8.3*/("""<div id="mostrarmostrar" style="display:none;">
			"""),_display_(/*9.5*/barraTitulo(mapDiccionario, "COTIZACION", "")),format.raw/*9.50*/("""
			"""),format.raw/*10.4*/("""<div class="noprint" align="left">
				<div class="row justify-content-md-center" >
					<div class="col-xs-3 col-sm-1 col-md-1 col-lg-1">
						<input type="button"  
							value="Imprimir" class="btn btn-primary btn-sm rounded-pill btn-block" 
							onclick="window.print(); return false;">
					</div>
					<div class="col-xs-5 col-sm-4 col-md-3 col-lg-2">
						<input type="button"  
							value='PDF """),_display_(/*19.20*/mapDiccionario/*19.34*/.get("Arriendo")),format.raw/*19.50*/("""' class="btn btn-secondary btn-sm rounded-pill btn-block" 
							onclick='$("#modalCFI").modal("show")'>
					</div>
					<div class="col-xs-5 col-sm-4 col-md-3 col-lg-2">
						<input type="button"  
							value="PDF Venta" class="btn btn-secondary btn-sm rounded-pill btn-block" 
							onclick="tipoCambio();">
					</div>
					<div class="col-xs-5 col-sm-4 col-md-3 col-lg-2">
						<form id='formArrVta' method='post' action='/pdfCotizaArrVta/' onsubmit="return validarForm();">
							<input type="hidden" name="id_cotizacion" value=""""),_display_(/*29.58*/id_cotizacion),format.raw/*29.71*/("""">
							<input type="hidden" id="sinDetCotArrVta" name="sinDetalle" value="0">
							<input type="hidden" id="selectGrupos" name="selectGrupos" value="">
							<input type="hidden" id="tasaIvaArrVta" name="tasaIva" value=""""),_display_(/*32.71*/tasaIva),format.raw/*32.78*/("""">
							
							"""),_display_(if(mapPermiso.get("parametro.cotizaCon_o_sinDetalle")!=null && mapPermiso.get("parametro.cotizaCon_o_sinDetalle").equals("1"))/*34.135*/{_display_(Seq[Any](format.raw/*34.136*/("""
								"""),format.raw/*35.9*/("""<input type="button"  value="PDF """),_display_(/*35.43*/mapDiccionario/*35.57*/.get("Arriendo")),format.raw/*35.73*/(""" """),format.raw/*35.74*/("""y Venta" class="btn btn-secondary btn-sm rounded-pill btn-block" 
									onclick="$('#modalArrVta').modal('show');">
							""")))}else/*37.13*/{_display_(Seq[Any](format.raw/*37.14*/("""
								"""),format.raw/*38.9*/("""<input type="submit" id="btnSubmit1"  
									value="PDF """),_display_(/*39.22*/mapDiccionario/*39.36*/.get("Arriendo")),format.raw/*39.52*/(""" """),format.raw/*39.53*/("""y Venta" class="btn btn-secondary btn-sm rounded-pill btn-block">
							""")))}),format.raw/*40.9*/("""
						"""),format.raw/*41.7*/("""</form>
					</div>
					<div class="col-xs-3 col-sm-1 col-md-1 col-lg-1">
						<form action="/cotizaExcel/" method="POST" onsubmit="return validarForm();">
							<input type="hidden" name="id_cotizacion" value=""""),_display_(/*45.58*/id_cotizacion),format.raw/*45.71*/("""">
							<input type="submit" id="btnSubmit2" class="btn btn-info btn-sm rounded-pill btn-block" value="Excel">
						</form>
						
					</div>
				</div>
				<br><br>
			</div>
			
			<div id="element">
				<div class="row  justify-content-md-center">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						"""),_display_(/*57.8*/Html(tabla)),format.raw/*57.19*/("""
					"""),format.raw/*58.6*/("""</div>
				</div>
			</div>
			<div class="noprint" align="left">
				<div class="row justify-content-md-center" >
					<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
						<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" 
							onclick="location.href='/cotizaListaImprimirPeriodo/';">
					</div>
				</div>
			</div>
		</div>
		
		<div id='modalCFI' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
			<div class='modal-dialog modal-dialog-scrollable' role='document' style="width:300px">
				<div class='modal-content'>
					<div class='modal-header'>
					        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
					          <span aria-hidden='true'>&times;</span>
					        </button>
					</div>
					<div class='modal-body'>
						<form id='formCFI' method='post' action='/pdfCotizaArriendo/' onsubmit="return validarForm();">
							<input type="hidden" name="id_cotizacion" value=""""),_display_(/*81.58*/id_cotizacion),format.raw/*81.71*/("""">
							<input type="hidden" id="sinDetCotArr" name="sinDetalle" value="0">
							<input type="hidden" id="noSeConsidera" name="selectGrupos" value="">
							
							<div class="input-group">
						  		<div class="input-group-prepend">
						    		<span class="input-group-text" id="basic-addon1">COSTO FIJO INICIAL</span>
						  		</div>
						  		<input type="text" class="form-control center"
									name="cfi"
									value="0.00 %"
									onfocus="value = value.replace(/,/g,'').replace(/%/g,'').replace(/ /g,'')"
									onkeydown="return ingresoDouble(window.event)"
									autocomplete="off"
									onchange="value = formatPorcentaje(value);">
							</div>
							
							"""),_display_(if(mapPermiso.get("parametro.ivaPorBodega")!=null && mapPermiso.get("parametro.ivaPorBodega").equals("1"))/*98.115*/{_display_(Seq[Any](format.raw/*98.116*/("""
								"""),format.raw/*99.9*/("""<br>
								<div class="input-group">
							  		<div class="input-group-prepend">
							    		<span class="input-group-text" id="basic-addon1">TASA IMPUESTO IVA</span>
							  		</div>
							  		<input type="text" class="form-control center"
										name="tasaIva"
										value=""""),_display_(/*106.19*/tasaIva),format.raw/*106.26*/(""""
										onfocus="value = value.replace(/,/g,'').replace(/%/g,'').replace(/ /g,'')"
										onkeydown="return ingresoDouble(window.event)"
										autocomplete="off"
										onchange="value = formatPorcentaje(value);">
								</div>
							""")))}else/*112.13*/{_display_(Seq[Any](format.raw/*112.14*/("""
								"""),format.raw/*113.9*/("""<input type="hidden" name="tasaIva" value="""),_display_(/*113.52*/tasaIva),format.raw/*113.59*/(""">
							""")))}),format.raw/*114.9*/("""
							
							"""),format.raw/*116.8*/("""<br>
								"""),_display_(if(mapPermiso.get("parametro.cotizaCon_o_sinDetalle")!=null && mapPermiso.get("parametro.cotizaCon_o_sinDetalle").equals("1"))/*117.136*/{_display_(Seq[Any](format.raw/*117.137*/("""
									"""),format.raw/*118.10*/("""<div class="form-check" onchange="$('#sinDetCotArr').val('0');">
	  									<input type="radio" class="form-check-input" id="radio1b" name="optradioB" value="option1" checked>CON DETALLE
	  									<label class="form-check-label" for="radio1b"></label>
									</div>
									<div class="form-check" onchange="$('#sinDetCotArr').val('1');">
	  									<input type="radio" class="form-check-input" id="radio2b" name="optradioB" value="option2">SIN DETALLE
	  									<label class="form-check-label" for="radio2b"></label>
									</div>
									
									<br>
								""")))} else {null} ),format.raw/*128.10*/("""
							"""),format.raw/*129.8*/("""<div class='row'>
								<div class='col-sm-12' style='text-align:center'>
									<input type="submit" id="btnSubmit3" class="btn btn-sm btn-primary" value='Generar' onclick='$("#modalCFI").modal("hide")'>
									<input type='button' class='btn btn-sm btn-warning' value='Cancelar' data-dismiss='modal'>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		
		<div id='modalTipoCambio' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
			<div class='modal-dialog modal-dialog-scrollable' role='document' style="width:300px">
				<div class='modal-content'>
					<div class='modal-header'>
					        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
					          <span aria-hidden='true'>&times;</span>
					        </button>
					</div>
					<div class='modal-body'>
						<form id='formTipoCambio' method='post' action='/pdfCotizaVenta/' onsubmit="return validarForm();">
							<input type="hidden" name="id_cotizacion" value=""""),_display_(/*151.58*/id_cotizacion),format.raw/*151.71*/("""">
							<input type="hidden" id="sinDetCotVta" name="sinDetalle" value="0">
							<input type="hidden" id="tasaIvaVta" name="tasaIva" value=""""),_display_(/*153.68*/tasaIva),format.raw/*153.75*/("""">
							
							<div class="input-group">
						  		<div class="input-group-prepend">
						    		<span class="input-group-text" id="basic-addon1">TIPO DE CAMBIO</span>
						  		</div>
						  		<input type="text" class="form-control center"
									name="tipoCambio"
									value="1.00"
									onfocus="value = value.replace(/,/g,'').trim()"
									onkeydown="return ingresoDouble(window.event)"
									autocomplete="off"
									onchange="value = formatStandar(value, 2);">
							</div>
							<br>
							<div class='row'>
								<div class='col-sm-12' style='text-align:center'>
									<input type="submit" id="btnSubmit4" class="btn btn-sm btn-primary" value='Generar' onclick='$("#modalCFI").modal("hide")'>
									<input type='button' class='btn btn-sm btn-warning' value='Cancelar' data-dismiss='modal'>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		
		
		<div id='modalArrVta' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
			<div class='modal-dialog modal-dialog-scrollable' role='document' style="width:300px">
				<div class='modal-content'>
					<div class='modal-header'>
					        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
					          <span aria-hidden='true'>&times;</span>
					        </button>
					</div>
					<div class='modal-body'>
					
								"""),_display_(if(mapPermiso.get("parametro.ivaPorBodega")!=null && mapPermiso.get("parametro.ivaPorBodega").equals("1"))/*191.116*/{_display_(Seq[Any](format.raw/*191.117*/("""
									"""),format.raw/*192.10*/("""<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">TASA IMPUESTO IVA</span>
								  		</div>
								  		<input type="text" class="form-control center"
											value=""""),_display_(/*197.20*/tasaIva),format.raw/*197.27*/(""""
											onfocus="value = value.replace(/,/g,'').replace(/%/g,'').replace(/ /g,'')"
											onkeydown="return ingresoDouble(window.event)"
											autocomplete="off"
											onchange="value = formatPorcentaje(value); $('#tasaIvaArrVta').val(value);">
									</div>
									<br>
								""")))}else/*204.14*/{_display_(Seq[Any](format.raw/*204.15*/("""
									"""),format.raw/*205.10*/("""<input type="hidden" name="tasaIva" value="""),_display_(/*205.53*/tasaIva),format.raw/*205.60*/(""">
								""")))}),format.raw/*206.10*/("""
							
						  		"""),format.raw/*208.11*/("""<div class="form-check" onchange="$('#sinDetCotArrVta').val('0');">
  									<input type="radio" class="form-check-input" id="radio1c" name="optradioC" value="option1" checked>CON DETALLE
  									<label class="form-check-label" for="radio1c"></label>
								</div>
								<div class="form-check" onchange="$('#sinDetCotArrVta').val('1');">
  									<input type="radio" class="form-check-input" id="radio2c" name="optradioC" value="option2">SIN DETALLE
  									<label class="form-check-label" for="radio2c"></label>
								</div>
								<div class="form-check" onchange="$('#sinDetCotArrVta').val('2'); ">
	  									<input type="radio" class="form-check-input" id="radio3c" name="optradioC" value="option3" 
											onclick='$("#modalArrVta").modal("hide"); $("#modalSelectGrupos").modal("show")'>POR GRUPO (selectivo)
	  									<label class="form-check-label" for="radio2b"></label>
								</div>
									
								<br>
							
							<div class='row'>
								<div class='col-sm-12' style='text-align:center'>
									<input type="button" class="btn btn-sm btn-primary" value='Generar' onclick='$("#modalArrVta").modal("hide");$("#formArrVta").submit();'>
									<input type='button' class='btn btn-sm btn-warning' value='Cancelar' data-dismiss='modal'>
								</div>
							</div>
					</div>
				</div>
			</div>
		</div>
		
		<div id='modalVta' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
			<div class='modal-dialog modal-dialog-scrollable' role='document' style="width:300px">
				<div class='modal-content'>
					<div class='modal-header'>
					        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
					          <span aria-hidden='true'>&times;</span>
					        </button>
					</div>
					<div class='modal-body'>
					
								"""),_display_(if(mapPermiso.get("parametro.ivaPorBodega")!=null && mapPermiso.get("parametro.ivaPorBodega").equals("1"))/*245.116*/{_display_(Seq[Any](format.raw/*245.117*/("""
									"""),format.raw/*246.10*/("""<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">TASA IMPUESTO IVA</span>
								  		</div>
								  		<input type="text" class="form-control center"
											value=""""),_display_(/*251.20*/tasaIva),format.raw/*251.27*/(""""
											onfocus="value = value.replace(/,/g,'').replace(/%/g,'').replace(/ /g,'')"
											onkeydown="return ingresoDouble(window.event)"
											autocomplete="off"
											onchange="value = formatPorcentaje(value); $('#tasaIvaVta').val(value);">
									</div>
									<br>
								""")))}else/*258.14*/{_display_(Seq[Any](format.raw/*258.15*/("""
									"""),format.raw/*259.10*/("""<input type="hidden" name="tasaIva" value="""),_display_(/*259.53*/tasaIva),format.raw/*259.60*/(""">
								""")))}),format.raw/*260.10*/("""
								
						  		"""),format.raw/*262.11*/("""<div class="form-check" onchange="$('#sinDetCotVta').val('0');">
  									<input type="radio" class="form-check-input" id="radio1a" name="optradioA" value="option1" checked>CON DETALLE
  									<label class="form-check-label" for="radio1a"></label>
								</div>
								<div class="form-check" onchange="$('#sinDetCotVta').val('1');">
  									<input type="radio" class="form-check-input" id="radio2a" name="optradioA" value="option2">SIN DETALLE
  									<label class="form-check-label" for="radio2a"></label>
								</div>
								<br>
							
							<div class='row'>
								<div class='col-sm-12' style='text-align:center'>
									<input type="button" class="btn btn-sm btn-primary" value='Generar' onclick='$("#modalVta").modal("hide");$("#formTipoCambio").submit();'>
									<input type='button' class='btn btn-sm btn-warning' value='Cancelar' data-dismiss='modal'>
								</div>
							</div>
					</div>
				</div>
			</div>
		</div>
		
		<div id='modalSelectGrupos' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
			<div class='modal-dialog modal-dialog-scrollable' role='document' style="width:300px">
				<div class='modal-content'>
					<div class='modal-header'>
					        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
					          <span aria-hidden='true'>&times;</span>
					        </button>
					</div>
					<div class='modal-body'>
							<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
								<thead style="background-color: #eeeeee">
									<TR>
										<TH>GRUPO</TH>
										<th></th>
									</TR>
								</thead>
								<tbody>
									"""),_display_(/*300.11*/for(lista <- listGrupos) yield /*300.35*/{_display_(Seq[Any](format.raw/*300.36*/("""
										"""),format.raw/*301.11*/("""<tr>
											<td>
												"""),_display_(/*303.14*/lista/*303.19*/.getNombre()),format.raw/*303.31*/("""
											"""),format.raw/*304.12*/("""</td>
											<td  style="text-align:center;">
												<input type="checkbox" id="check_"""),_display_(/*306.47*/lista/*306.52*/.getId()),format.raw/*306.60*/("""" value ="0" 
													onchange="cambiaEstado(id, value, '"""),_display_(/*307.50*/lista/*307.55*/.getId()),format.raw/*307.63*/("""')"></td>
											</td>
										</tr>
									""")))}),format.raw/*310.11*/("""
								"""),format.raw/*311.9*/("""</tbody>
							</table>
						  		
							
							
							<div class='row'>
								<div class='col-sm-12' style='text-align:center'>
									<input type="button" class="btn btn-sm btn-primary" value='Seleccionar' onclick='$("#modalSelectGrupos").modal("hide"); $("#modalArrVta").modal("show")'>
									<input type='button' class='btn btn-sm btn-warning' value='Cancelar' data-dismiss='modal'>
								</div>
							</div>
					</div>
				</div>
			</div>
		</div>
		
""")))}),format.raw/*327.2*/("""



"""),format.raw/*331.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*333.31*/("""{"""),format.raw/*333.32*/("""
		"""),format.raw/*334.3*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*335.2*/("""}"""),format.raw/*335.3*/(""");
	
	function tipoCambio()"""),format.raw/*337.23*/("""{"""),format.raw/*337.24*/("""
		"""),format.raw/*338.3*/("""if('"""),_display_(/*338.8*/mapDiccionario/*338.22*/.get("nEmpresa")),format.raw/*338.38*/("""' == 'MONTAX')"""),format.raw/*338.52*/("""{"""),format.raw/*338.53*/("""
			"""),format.raw/*339.4*/("""$('#modalTipoCambio').modal('show');
		"""),format.raw/*340.3*/("""}"""),format.raw/*340.4*/("""else"""),format.raw/*340.8*/("""{"""),format.raw/*340.9*/("""
			"""),format.raw/*341.4*/("""if('"""),_display_(/*341.9*/mapPermiso/*341.19*/.get("parametro.cotizaCon_o_sinDetalle")),format.raw/*341.59*/("""'!=null && '"""),_display_(/*341.72*/mapPermiso/*341.82*/.get("parametro.cotizaCon_o_sinDetalle")),format.raw/*341.122*/("""' == 1)"""),format.raw/*341.129*/("""{"""),format.raw/*341.130*/("""
				"""),format.raw/*342.5*/("""$('#modalVta').modal('show');
			"""),format.raw/*343.4*/("""}"""),format.raw/*343.5*/("""else"""),format.raw/*343.9*/("""{"""),format.raw/*343.10*/("""
				"""),format.raw/*344.5*/("""$("#formTipoCambio").submit();
			"""),format.raw/*345.4*/("""}"""),format.raw/*345.5*/("""
		"""),format.raw/*346.3*/("""}"""),format.raw/*346.4*/("""
	"""),format.raw/*347.2*/("""}"""),format.raw/*347.3*/("""
	
	
	"""),format.raw/*350.2*/("""const cambiaEstado = (auxId, valor, id_grupo) => """),format.raw/*350.51*/("""{"""),format.raw/*350.52*/("""
		"""),format.raw/*351.3*/("""if(valor==0) """),format.raw/*351.16*/("""{"""),format.raw/*351.17*/("""
			"""),format.raw/*352.4*/("""$("#"+auxId).val("1");
			let aux = ""+$("#selectGrupos").val()+ id_grupo + ";"
			$("#selectGrupos").val(aux);
		"""),format.raw/*355.3*/("""}"""),format.raw/*355.4*/("""else"""),format.raw/*355.8*/("""{"""),format.raw/*355.9*/("""
			"""),format.raw/*356.4*/("""$("#"+auxId).val("0");
			let aux = ""+ id_grupo + ";"
			$("#selectGrupos").val($("#selectGrupos").val().replace(aux,""));
		"""),format.raw/*359.3*/("""}"""),format.raw/*359.4*/("""
	"""),format.raw/*360.2*/("""}"""),format.raw/*360.3*/("""
	
	
	"""),format.raw/*363.2*/("""function exportarExcel(element, filename)"""),format.raw/*363.43*/("""{"""),format.raw/*363.44*/("""
	    """),format.raw/*364.6*/("""var html = document.getElementById(element).innerHTML;
	
	    var blob = new Blob(['ufeff', html], """),format.raw/*366.43*/("""{"""),format.raw/*366.44*/("""
	        """),format.raw/*367.10*/("""type: 'application/msexcel'
	    """),format.raw/*368.6*/("""}"""),format.raw/*368.7*/(""");
	    
	    // Specify link url
	    var url = 'data:application/vnd.ms-excel;charset=utf-8,' + encodeURIComponent(html);
	    
	    // Specify file name
	    filename = filename?filename+'.xls':filename;
	    
	    // Create download link element
	    var downloadLink = document.createElement("a");
	
	    document.body.appendChild(downloadLink);
	    
	    if(navigator.msSaveOrOpenBlob )"""),format.raw/*381.37*/("""{"""),format.raw/*381.38*/("""
	        """),format.raw/*382.10*/("""navigator.msSaveOrOpenBlob(blob, filename);
	    """),format.raw/*383.6*/("""}"""),format.raw/*383.7*/("""else"""),format.raw/*383.11*/("""{"""),format.raw/*383.12*/("""
	        """),format.raw/*384.10*/("""// Create a link to the file
	        downloadLink.href = url;
	        
	        // Setting the file name
	        downloadLink.download = filename;
	        
	        //triggering the function
	        downloadLink.click();
	    """),format.raw/*392.6*/("""}"""),format.raw/*392.7*/("""
	    
	    """),format.raw/*394.6*/("""document.body.removeChild(downloadLink);
	"""),format.raw/*395.2*/("""}"""),format.raw/*395.3*/("""
	
	"""),format.raw/*397.2*/("""function exportarWord(element, filename)"""),format.raw/*397.42*/("""{"""),format.raw/*397.43*/("""
	    """),format.raw/*398.6*/("""var preHtml = "<html xmlns:o='urn:schemas-microsoft-com:office:office' xmlns:w='urn:schemas-microsoft-com:office:word' xmlns='http://www.w3.org/TR/REC-html40'><head><meta charset='utf-8'><title>Export HTML To Doc</title></head><body>";
	    var postHtml = "</body></html>";
	    var html = preHtml+document.getElementById(element).innerHTML+postHtml;
	
	    var blob = new Blob(['ufeff', html], """),format.raw/*402.43*/("""{"""),format.raw/*402.44*/("""
	        """),format.raw/*403.10*/("""type: 'application/msword'
	    """),format.raw/*404.6*/("""}"""),format.raw/*404.7*/(""");
	    
	    // Specify link url
	    var url = 'data:application/vnd.ms-word;charset=utf-8,' + encodeURIComponent(html);
	    
	    // Specify file name
	    filename = filename?filename+'.doc':filename;
	    
	    // Create download link element
	    var downloadLink = document.createElement("a");
	
	    document.body.appendChild(downloadLink);
	    
	    if(navigator.msSaveOrOpenBlob )"""),format.raw/*417.37*/("""{"""),format.raw/*417.38*/("""
	        """),format.raw/*418.10*/("""navigator.msSaveOrOpenBlob(blob, filename);
	    """),format.raw/*419.6*/("""}"""),format.raw/*419.7*/("""else"""),format.raw/*419.11*/("""{"""),format.raw/*419.12*/("""
	        """),format.raw/*420.10*/("""// Create a link to the file
	        downloadLink.href = url;
	        
	        // Setting the file name
	        downloadLink.download = filename;
	        
	        //triggering the function
	        downloadLink.click();
	    """),format.raw/*428.6*/("""}"""),format.raw/*428.7*/("""
	    
	    """),format.raw/*430.6*/("""document.body.removeChild(downloadLink);
	"""),format.raw/*431.2*/("""}"""),format.raw/*431.3*/("""
	
	"""),format.raw/*433.2*/("""const validarForm = () =>"""),format.raw/*433.27*/("""{"""),format.raw/*433.28*/("""
		"""),format.raw/*434.3*/("""$("#btnSubmit1").attr('disabled',true);
		$("#btnSubmit2").attr('disabled',true);
		$("#btnSubmit3").attr('disabled',true);
		$("#btnSubmit4").attr('disabled',true);
		return(true);
	"""),format.raw/*439.2*/("""}"""),format.raw/*439.3*/("""
	


	
"""),format.raw/*444.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,id_cotizacion:Long,tabla:String,listGrupos:List[tables.Grupo],tasaIva:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,id_cotizacion,tabla,listGrupos,tasaIva)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,Long,String,List[tables.Grupo],String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,id_cotizacion,tabla,listGrupos,tasaIva) => apply(mapDiccionario,mapPermiso,userMnu,id_cotizacion,tabla,listGrupos,tasaIva)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizar/cotizaImprimir.scala.html
                  HASH: 03b4242390766f977aca13271c0cd9a200a4fef2
                  MATRIX: 1051->1|1327->184|1354->186|1370->194|1409->196|1438->200|1506->248|1538->254|1615->306|1680->351|1711->355|2148->765|2171->779|2208->795|2778->1338|2812->1351|3066->1578|3094->1585|3267->1730|3307->1731|3343->1740|3404->1774|3427->1788|3464->1804|3493->1805|3643->1936|3682->1937|3718->1946|3805->2006|3828->2020|3865->2036|3894->2037|3998->2111|4032->2118|4274->2333|4308->2346|4655->2667|4687->2678|4720->2684|5741->3678|5775->3691|6606->4494|6646->4495|6682->4504|7000->4794|7029->4801|7304->5056|7344->5057|7381->5066|7452->5109|7481->5116|7522->5126|7566->5142|7735->5282|7776->5283|7815->5293|8436->5869|8472->5877|9526->6903|9561->6916|9734->7061|9763->7068|11297->8573|11338->8574|11377->8584|11662->8841|11691->8848|12018->9155|12058->9156|12097->9166|12168->9209|12197->9216|12240->9227|12288->9246|14251->11180|14292->11181|14331->11191|14616->11448|14645->11455|14969->11759|15009->11760|15048->11770|15119->11813|15148->11820|15191->11831|15240->11851|16978->13561|17019->13585|17059->13586|17099->13597|17161->13631|17176->13636|17210->13648|17251->13660|17375->13756|17390->13761|17420->13769|17511->13832|17526->13837|17556->13845|17641->13898|17678->13907|18182->14380|18214->14384|18306->14447|18336->14448|18367->14451|18461->14517|18490->14518|18546->14545|18576->14546|18607->14549|18639->14554|18663->14568|18701->14584|18744->14598|18774->14599|18806->14603|18873->14642|18902->14643|18934->14647|18963->14648|18995->14652|19027->14657|19047->14667|19109->14707|19150->14720|19170->14730|19233->14770|19270->14777|19301->14778|19334->14783|19395->14816|19424->14817|19456->14821|19486->14822|19519->14827|19581->14861|19610->14862|19641->14865|19670->14866|19700->14868|19729->14869|19763->14875|19841->14924|19871->14925|19902->14928|19944->14941|19974->14942|20006->14946|20148->15060|20177->15061|20209->15065|20238->15066|20270->15070|20424->15196|20453->15197|20483->15199|20512->15200|20546->15206|20616->15247|20646->15248|20680->15254|20808->15353|20838->15354|20877->15364|20938->15397|20967->15398|21389->15791|21419->15792|21458->15802|21535->15851|21564->15852|21597->15856|21627->15857|21666->15867|21925->16098|21954->16099|21994->16111|22064->16153|22093->16154|22125->16158|22194->16198|22224->16199|22258->16205|22682->16600|22712->16601|22751->16611|22811->16643|22840->16644|23261->17036|23291->17037|23330->17047|23407->17096|23436->17097|23469->17101|23499->17102|23538->17112|23797->17343|23826->17344|23866->17356|23936->17398|23965->17399|23997->17403|24051->17428|24081->17429|24112->17432|24323->17615|24352->17616|24387->17623
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|39->8|40->9|40->9|41->10|50->19|50->19|50->19|60->29|60->29|63->32|63->32|65->34|65->34|66->35|66->35|66->35|66->35|66->35|68->37|68->37|69->38|70->39|70->39|70->39|70->39|71->40|72->41|76->45|76->45|88->57|88->57|89->58|112->81|112->81|129->98|129->98|130->99|137->106|137->106|143->112|143->112|144->113|144->113|144->113|145->114|147->116|148->117|148->117|149->118|159->128|160->129|182->151|182->151|184->153|184->153|222->191|222->191|223->192|228->197|228->197|235->204|235->204|236->205|236->205|236->205|237->206|239->208|276->245|276->245|277->246|282->251|282->251|289->258|289->258|290->259|290->259|290->259|291->260|293->262|331->300|331->300|331->300|332->301|334->303|334->303|334->303|335->304|337->306|337->306|337->306|338->307|338->307|338->307|341->310|342->311|358->327|362->331|364->333|364->333|365->334|366->335|366->335|368->337|368->337|369->338|369->338|369->338|369->338|369->338|369->338|370->339|371->340|371->340|371->340|371->340|372->341|372->341|372->341|372->341|372->341|372->341|372->341|372->341|372->341|373->342|374->343|374->343|374->343|374->343|375->344|376->345|376->345|377->346|377->346|378->347|378->347|381->350|381->350|381->350|382->351|382->351|382->351|383->352|386->355|386->355|386->355|386->355|387->356|390->359|390->359|391->360|391->360|394->363|394->363|394->363|395->364|397->366|397->366|398->367|399->368|399->368|412->381|412->381|413->382|414->383|414->383|414->383|414->383|415->384|423->392|423->392|425->394|426->395|426->395|428->397|428->397|428->397|429->398|433->402|433->402|434->403|435->404|435->404|448->417|448->417|449->418|450->419|450->419|450->419|450->419|451->420|459->428|459->428|461->430|462->431|462->431|464->433|464->433|464->433|465->434|470->439|470->439|475->444
                  -- GENERATED --
              */
          