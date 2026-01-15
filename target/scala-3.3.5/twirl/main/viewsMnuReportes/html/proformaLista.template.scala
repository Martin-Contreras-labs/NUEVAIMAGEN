
package viewsMnuReportes.html

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

object proformaLista extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
lista: List[List[String]], desde: String, hasta: String ):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	"""),format.raw/*7.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*8.4*/barraTitulo(mapDiccionario, "LISTADO DE EP/PROFORMA (FACTURAS)", "")),format.raw/*8.72*/("""

		"""),format.raw/*10.3*/("""<div class="noprint">
			<div class="row">
				<div class="col-1"></div>
			  	<div class="col-2">
						<button type="button"  id="btnEnvioMasivo" class="btn btn-sm btn-warning" 
							onclick="$('#modalGeneraUnPdf').modal('show');">
							Genera un PDF multiple
						</button>
			  	</div>
				"""),_display_(if(mapDiccionario.get("nEmpresa").equals("HOHE")
					&& mapPermiso.get("parametro.proformaListar-llenarApiManager")!=null
					&& mapPermiso.get("parametro.proformaListar-llenarApiManager").equals("1")
					&& mapPermiso.get("enviarApiFactura")!=null)/*22.50*/{_display_(Seq[Any](format.raw/*22.51*/("""
						"""),format.raw/*23.7*/("""<div class="col-2">
							<button type="button"  id="btnEnvioNotasVenta" class="btn btn-sm btn-info" 
								onclick="$('#modalNotasVenta').modal('show');">
								Notas de Venta desde Excel
							</button>
				  		</div>
				""")))} else {null} ),format.raw/*29.6*/("""
		
			"""),format.raw/*31.4*/("""</div>
		</div>
		
			
			<div id='modalGeneraUnPdf' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
				<div class='modal-dialog modal-dialog-scrollable' role='document' style="width:300px">
					<div class='modal-content'>
						<div class='modal-header'>
							GENERAR PDF UNICO<br>(debe ser máximo de 100 en 100)
						        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
						          <span aria-hidden='true'>&times;</span>
						        </button>
						</div>
						<div class='modal-body'>
							<form class="formulario" method="post" action="/routes2/proformaListaPdf/"  onsubmit="return validarForm();">
								<input type="hidden" name = "fechaDesde" value = """"),_display_(/*46.60*/desde),format.raw/*46.65*/("""">
								<input type="hidden" name = "fechaHasta" value = """"),_display_(/*47.60*/hasta),format.raw/*47.65*/("""">
								<table class="table table-sm table-bordered table-condensed table-fluid">
									<thead style="background-color: #eeeeee">
										<TR>
											<TH>DESDE NRO:</TH>
											<td>
												<input type="text" class="form-control center"
													id="nroIni"
													name="nroIni"
													onfocus="value = value.replace(/,/g,'')"
													onkeydown="return ingresoInt(window.event)"
													autocomplete="off"
													onchange="if(value=='') """),format.raw/*59.38*/("""{"""),format.raw/*59.39*/("""value='';"""),format.raw/*59.48*/("""}"""),format.raw/*59.49*/(""""
													required>
											</td>
										</TR>
										<TR>
											<TH>HASTA NRO:</TH>
											<td>
												<input type="text" class="form-control center"
													id="nroFin"
													name="nroFin"
													onfocus="value = value.replace(/,/g,'')"
													onkeydown="return ingresoInt(window.event)"
													autocomplete="off"
													onchange="if(value=='') """),format.raw/*72.38*/("""{"""),format.raw/*72.39*/("""value='';"""),format.raw/*72.48*/("""}"""),format.raw/*72.49*/(""""
													required>
											</td>
										</TR>
									</thead>
								</table>
								<br>
								<div class='row'>
									<div class='col-sm-12' style='text-align:center'>
										<input type="submit" id="btnSubmit" value='Generar PDF' class="btn btn-primary btn-sm rounded-pill btn-block">
										<br>
										<input type='button' class='btn btn-sm btn-warning' value='Cancelar' onclick='$("#btnSubmit").attr("disabled",false);' data-dismiss='modal'>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
	
	
			<table class="table table-sm table-condensed table-fluid">
				<tr>
					<td width="25%">
						<div class="input-group">
					  		<div class="input-group-prepend">
					    		<span class="input-group-text" id="basic-addon1">BUSCAR</span>
					  		</div>
					  		<input type="text" class="form-control left"
								id="searchTermtablaPrincipal"
								onkeyup="doSearch2('tablaPrincipal');">
						</div>
					</td>
					<td>
						<div align="center">
							<button type="button"  class="btn btn-sm btn-success" onclick="document.getElementById('excel').submit()">
								Exportar a Excel
							</button>
							&nbsp;&nbsp;&nbsp;
							<button type="button" class="btn btn-sm btn-success" tabindex="-1" onclick="window.print(); return false;" >
								Imprimir
							</button>
							&nbsp;&nbsp;&nbsp;
							<button type="button"  class="btn btn-sm btn-success" 
								onclick="location.href = '/home/'">
								Volver
							</button>
							&nbsp;&nbsp;&nbsp;
						</div>
					</td>
				</tr>
			</table>
		</div>
		
		<form id="excel" class="formulario" method="post" action="/proformaListaExcel/">
			<input type="hidden" name="fechaDesde" value=""""),_display_(/*127.51*/desde),format.raw/*127.56*/("""">
			<input type="hidden" name="fechaHasta" value=""""),_display_(/*128.51*/hasta),format.raw/*128.56*/("""">
		</form>
			
		
		<div class="table-responsive">
			<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
		        <thead style="background-color: #eeeeee">
					<TR> 
						<TH>TIPO</TH>
				        <TH>NUMERO</TH>
						<TH>FECHA</TH>
						<TH>DESDE</TH>
						<TH>HASTA</TH>
						<TH>RUT</TH>
						<TH>CLIENTE</TH>
						<TH>SUCURSAL</TH>
						<TH>COMERCIAL</TH>
						<TH>RUBRO</TH>
						<TH>"""),_display_(/*146.12*/mapDiccionario/*146.26*/.get("BODEGA")),format.raw/*146.40*/("""/PROYECTO</TH>
						<TH>Total<br>NETO</TH>
						<TH>Total<br>IVA</TH>
						<TH>Total<br>TOTAL</TH>
						<TH>Prof<br>PDF</TH>
						<TH>Excel<br>EP</TH>
						<TH>Excel<br>MOV</TH>
						<!-- <TH>Doc<br>ANEXO</TH> -->
						"""),_display_(if(mapPermiso.get("parametro.proformaLista-descargarXLM")!=null && !mapPermiso.get("parametro.proformaLista-descargarXLM").equals("")  && mapPermiso.get("parametro.proformaLista-descargarXLM").equals("1"))/*154.213*/{_display_(Seq[Any](format.raw/*154.214*/("""
							"""),format.raw/*155.8*/("""<TH>Down<br>XML</TH>
						""")))} else {null} ),format.raw/*156.8*/("""
						"""),_display_(if(mapPermiso.get("parametro.proformaListar-llenarApiManager")!=null && !mapPermiso.get("parametro.proformaListar-llenarApiManager").equals("")  && mapPermiso.get("parametro.proformaListar-llenarApiManager").equals("1") && mapPermiso.get("enviarApiFactura")!=null)/*157.272*/{_display_(Seq[Any](format.raw/*157.273*/("""
							"""),format.raw/*158.8*/("""<TH style="text-align:center">API<br>Manager</TH>
						""")))} else {null} ),format.raw/*159.8*/("""
						"""),_display_(if(mapPermiso.get("parametro.proformaListar-llenarApiNubox")!=null && !mapPermiso.get("parametro.proformaListar-llenarApiNubox").equals("")  && mapPermiso.get("parametro.proformaListar-llenarApiNubox").equals("1") && mapPermiso.get("enviarApiFactura")!=null)/*160.266*/{_display_(Seq[Any](format.raw/*160.267*/("""
							"""),format.raw/*161.8*/("""<TH style="text-align:center">API<br>Nubox</TH>
						""")))} else {null} ),format.raw/*162.8*/("""
						"""),_display_(if(mapPermiso.get("parametro.proformaListar-llenarApiSapConconcreto")!=null && !mapPermiso.get("parametro.proformaListar-llenarApiSapConconcreto").equals("")  && mapPermiso.get("parametro.proformaListar-llenarApiSapConconcreto").equals("1") && mapPermiso.get("enviarApiFactura")!=null)/*163.293*/{_display_(Seq[Any](format.raw/*163.294*/("""
							"""),format.raw/*164.8*/("""<TH style="text-align:center">SAP<br>Conconcreto</TH>
							<TH style="text-align:center">Nro SAP</TH>
						""")))} else {null} ),format.raw/*166.8*/("""
						"""),_display_(if(mapPermiso.get("parametro.proformaListar-llenarWebIConstruye")!= null && !mapPermiso.get("parametro.proformaListar-llenarWebIConstruye").equals("") && mapPermiso.get("parametro.proformaListar-llenarWebIConstruye").equals("1") && mapPermiso.get("enviarApiFactura")!=null)/*167.281*/{_display_(Seq[Any](format.raw/*167.282*/("""
							"""),format.raw/*168.8*/("""<TH style="text-align:center">ICONSTRUYE<br>FACTURAS</TH>
						""")))} else {null} ),format.raw/*169.8*/("""
						"""),_display_(if(mapPermiso.get("parametro.proformaListar-llenarWebMaximise")!=null && mapPermiso.get("parametro.proformaListar-llenarWebMaximise").equals("1") && mapPermiso.get("enviarApiFactura")!=null)/*170.198*/{_display_(Seq[Any](format.raw/*170.199*/("""
							"""),format.raw/*171.8*/("""<TH>WEB<br>Maximise</TH>
							<TH>Nro.Int</TH>
						""")))} else {null} ),format.raw/*173.8*/("""
						"""),_display_(if(mapPermiso.get("proformaListadoEliminar")!=null)/*174.59*/ {_display_(Seq[Any](format.raw/*174.61*/("""
							"""),format.raw/*175.8*/("""<TH>DEL</TH>
						""")))} else {null} ),format.raw/*176.8*/("""
						"""),_display_(if(mapPermiso.get("parametro.proformaListar-llenarApiRelBase")!=null && mapPermiso.get("parametro.proformaListar-llenarApiRelBase").equals("1") && mapPermiso.get("enviarApiFactura")!=null)/*177.196*/{_display_(Seq[Any](format.raw/*177.197*/("""
							"""),format.raw/*178.8*/("""<TH>API<br>RELBASE</TH>
						""")))} else {null} ),format.raw/*179.8*/("""
						"""),_display_(if(mapPermiso.get("parametro.proformaListar-llenarApiSapSchwager")!=null && mapPermiso.get("parametro.proformaListar-llenarApiSapSchwager").equals("1") && mapPermiso.get("enviarApiFactura")!=null)/*180.204*/{_display_(Seq[Any](format.raw/*180.205*/("""
							"""),format.raw/*181.8*/("""<TH>API<br>SAP</TH>
						""")))} else {null} ),format.raw/*182.8*/("""
						
						
						
					"""),format.raw/*186.6*/("""</TR>
				</thead>
				<tbody>
					"""),_display_(/*189.7*/for(lista1 <- lista) yield /*189.27*/{_display_(Seq[Any](format.raw/*189.28*/("""
						"""),format.raw/*190.7*/("""<tr>
							<td style= "text-align: center;">"""),_display_(/*191.42*/lista1/*191.48*/.get(16)),format.raw/*191.56*/("""</td>
							<td style= "text-align: center;">"""),_display_(/*192.42*/lista1/*192.48*/.get(0)),format.raw/*192.55*/("""</td>
							<td style= "text-align: center; min-width:80px">
								<div style="display:none">"""),_display_(/*194.36*/utilities/*194.45*/.Fechas.AAMMDD(lista1.get(2))),format.raw/*194.74*/("""</div>
								"""),_display_(/*195.10*/lista1/*195.16*/.get(2)),format.raw/*195.23*/("""
							"""),format.raw/*196.8*/("""</td>
							<td style= "text-align: center; min-width:80px">
								<div style="display:none">"""),_display_(/*198.36*/utilities/*198.45*/.Fechas.AAMMDD(lista1.get(3))),format.raw/*198.74*/("""</div>
								"""),_display_(/*199.10*/lista1/*199.16*/.get(3)),format.raw/*199.23*/("""
							"""),format.raw/*200.8*/("""</td>
							<td style= "text-align: center; min-width:80px	">
								<div style="display:none">"""),_display_(/*202.36*/utilities/*202.45*/.Fechas.AAMMDD(lista1.get(4))),format.raw/*202.74*/("""</div>
								"""),_display_(/*203.10*/lista1/*203.16*/.get(4)),format.raw/*203.23*/("""
							"""),format.raw/*204.8*/("""</td>
							<td style= "text-align: center;" min-width:80px>"""),_display_(/*205.57*/lista1/*205.63*/.get(5)),format.raw/*205.70*/("""</td>
							<td style= "text-align: left;">"""),_display_(/*206.40*/lista1/*206.46*/.get(6)),format.raw/*206.53*/("""</td>
							<td style= "text-align: left;">"""),_display_(/*207.40*/lista1/*207.46*/.get(22)),format.raw/*207.54*/("""</td>
							<td style= "text-align: left;">"""),_display_(/*208.40*/lista1/*208.46*/.get(23)),format.raw/*208.54*/("""</td>
							<td style= "text-align: left;">"""),_display_(/*209.40*/lista1/*209.46*/.get(25)),format.raw/*209.54*/("""</td>
							<td style= "text-align: left;">"""),_display_(/*210.40*/lista1/*210.46*/.get(7)),format.raw/*210.53*/("""</td>
							<td style= "text-align: right;">"""),_display_(/*211.41*/lista1/*211.47*/.get(8)),format.raw/*211.54*/("""</td>
							<td style= "text-align: right;">"""),_display_(/*212.41*/lista1/*212.47*/.get(9)),format.raw/*212.54*/("""</td>
							<td style= "text-align: right;">"""),_display_(/*213.41*/lista1/*213.47*/.get(10)),format.raw/*213.55*/("""</td>
							<td style= "text-align: center;">
								"""),_display_(if(lista1.get(11)!="0")/*215.33*/{_display_(Seq[Any](format.raw/*215.34*/("""
									"""),format.raw/*216.10*/("""<a href="#" onclick="mostrarPDF('"""),_display_(/*216.44*/lista1/*216.50*/.get(11)),format.raw/*216.58*/("""')">
		    							<kbd style="background-color: #85C1E9">pdf</kbd>
		    						</a>
								""")))}else/*219.14*/{_display_(Seq[Any](format.raw/*219.15*/("""
									"""),format.raw/*220.10*/("""--
								""")))}),format.raw/*221.10*/("""
							"""),format.raw/*222.8*/("""</td>
							<td style="text-align:center;">
								"""),_display_(if(lista1.get(12)!="0")/*224.33*/{_display_(Seq[Any](format.raw/*224.34*/("""
									"""),format.raw/*225.10*/("""<a href="#" onclick= "descargaDocumento('"""),_display_(/*225.52*/lista1/*225.58*/.get(12)),format.raw/*225.66*/("""')">
										<kbd style="background-color: #85C1E9">xls</kbd>
									</a>
								""")))}else/*228.14*/{_display_(Seq[Any](format.raw/*228.15*/("""
									"""),format.raw/*229.10*/("""--
								""")))}),format.raw/*230.10*/("""
							"""),format.raw/*231.8*/("""</td>
							<td style="text-align:center;">
								"""),_display_(if(lista1.get(13)!="0")/*233.33*/{_display_(Seq[Any](format.raw/*233.34*/("""
									"""),format.raw/*234.10*/("""<a href="#" onclick= "descargaDocumento('"""),_display_(/*234.52*/lista1/*234.58*/.get(13)),format.raw/*234.66*/("""')">
										<kbd style="background-color: #85C1E9">xls</kbd>
									</a>
								""")))}else/*237.14*/{_display_(Seq[Any](format.raw/*237.15*/("""
									"""),format.raw/*238.10*/("""--
								""")))}),format.raw/*239.10*/("""
							"""),format.raw/*240.8*/("""</td>
							<!-- <td style="text-align:center;">
								"""),_display_(if(lista1.get(14)!="0")/*242.33*/{_display_(Seq[Any](format.raw/*242.34*/("""
									"""),format.raw/*243.10*/("""<a href="#" onclick= "descargaDocumento('"""),_display_(/*243.52*/lista1/*243.58*/.get(14)),format.raw/*243.66*/("""')">
										<kbd style="background-color: #85C1E9">doc</kbd>
									</a>
								""")))}else/*246.14*/{_display_(Seq[Any](format.raw/*246.15*/("""
									"""),format.raw/*247.10*/("""--
								""")))}),format.raw/*248.10*/("""
							"""),format.raw/*249.8*/("""</td> -->
					
							"""),_display_(if(mapPermiso.get("parametro.proformaLista-descargarXLM")!=null && mapPermiso.get("parametro.proformaLista-descargarXLM").equals("1"))/*251.143*/{_display_(Seq[Any](format.raw/*251.144*/("""
								"""),format.raw/*252.9*/("""<td style="text-align:center;">
									"""),_display_(if(lista1.get(17)!="0")/*253.34*/{_display_(Seq[Any](format.raw/*253.35*/("""
										"""),format.raw/*254.11*/("""<a href="#" onclick="enviarXML('"""),_display_(/*254.44*/lista1/*254.50*/.get(0)),format.raw/*254.57*/("""')">
											"""),_display_(if(lista1.get(18).equals("0"))/*255.43*/{_display_(Seq[Any](format.raw/*255.44*/("""
												"""),format.raw/*256.13*/("""<kbd style="background-color:#C39BD3">enviar</kbd>
											""")))}else/*257.17*/{_display_(Seq[Any](format.raw/*257.18*/("""
												"""),format.raw/*258.13*/("""<kbd style="background-color: #85C1E9">reenviar</kbd>
											""")))}),format.raw/*259.13*/("""
										"""),format.raw/*260.11*/("""</a>
									""")))}else/*261.15*/{_display_(Seq[Any](format.raw/*261.16*/("""
										"""),format.raw/*262.11*/("""--
									""")))}),format.raw/*263.11*/("""
								"""),format.raw/*264.9*/("""</td>
							""")))} else {null} ),format.raw/*265.9*/("""
					
							"""),_display_(if(mapPermiso.get("parametro.proformaListar-llenarApiManager")!=null && mapPermiso.get("parametro.proformaListar-llenarApiManager").equals("1") && mapPermiso.get("enviarApiFactura")!=null)/*267.197*/{_display_(Seq[Any](format.raw/*267.198*/("""
								"""),format.raw/*268.9*/("""<td style="text-align:center;">
									"""),_display_(if(lista1.get(17)!="0")/*269.34*/{_display_(Seq[Any](format.raw/*269.35*/("""
										"""),_display_(if(lista1.get(19)!=null)/*270.36*/{_display_(Seq[Any](format.raw/*270.37*/("""
											"""),format.raw/*271.12*/("""<a href="#" onclick="enviarApiManager('"""),_display_(/*271.52*/lista1/*271.58*/.get(0)),format.raw/*271.65*/("""')">
												<kbd style="background-color: #C39BD3">enviar</kbd>
											</a>
										""")))}else/*274.16*/{_display_(Seq[Any](format.raw/*274.17*/("""
											"""),format.raw/*275.12*/("""--
										""")))}),format.raw/*276.12*/("""
									""")))}else/*277.15*/{_display_(Seq[Any](format.raw/*277.16*/("""
										"""),format.raw/*278.11*/("""--
									""")))}),format.raw/*279.11*/("""
								"""),format.raw/*280.9*/("""</td>
							""")))} else {null} ),format.raw/*281.9*/("""
							"""),_display_(if(mapPermiso.get("parametro.proformaListar-llenarApiNubox")!=null && mapPermiso.get("parametro.proformaListar-llenarApiNubox").equals("1") && mapPermiso.get("enviarApiFactura")!=null)/*282.193*/{_display_(Seq[Any](format.raw/*282.194*/("""
								"""),format.raw/*283.9*/("""<td style="text-align:center;">
									"""),_display_(if(lista1.get(17)!="0")/*284.34*/{_display_(Seq[Any](format.raw/*284.35*/("""
										"""),_display_(if(lista1.get(19)!=null)/*285.36*/{_display_(Seq[Any](format.raw/*285.37*/("""
											"""),_display_(if(!lista1.get(16).equals("Venta"))/*286.48*/{_display_(Seq[Any](format.raw/*286.49*/("""
												"""),_display_(if(lista1.get(20).equals("0") || lista1.get(20).length()>50)/*287.74*/{_display_(Seq[Any](format.raw/*287.75*/("""
													"""),format.raw/*288.14*/("""<a href="#" onclick="enviarApiNubox('"""),_display_(/*288.52*/lista1/*288.58*/.get(0)),format.raw/*288.65*/("""')">
														<kbd style="background-color: #C39BD3">enviar</kbd>
													</a>
												""")))}else/*291.18*/{_display_(Seq[Any](format.raw/*291.19*/("""
													"""),format.raw/*292.14*/("""<a href="#" onclick="enviarApiNubox('"""),_display_(/*292.52*/lista1/*292.58*/.get(0)),format.raw/*292.65*/("""')">
														<kbd style="background-color: #85C1E9">reenviar</kbd>
													</a>
												""")))}),format.raw/*295.14*/("""
											""")))} else {null} ),format.raw/*296.13*/("""
										""")))}else/*297.16*/{_display_(Seq[Any](format.raw/*297.17*/("""
											"""),format.raw/*298.12*/("""--
										""")))}),format.raw/*299.12*/("""
									""")))}else/*300.15*/{_display_(Seq[Any](format.raw/*300.16*/("""
										"""),format.raw/*301.11*/("""--
									""")))}),format.raw/*302.11*/("""
								"""),format.raw/*303.9*/("""</td>
							""")))} else {null} ),format.raw/*304.9*/("""
							"""),_display_(if(mapPermiso.get("parametro.proformaListar-llenarApiSapConconcreto")!=null && mapPermiso.get("parametro.proformaListar-llenarApiSapConconcreto").equals("1") && mapPermiso.get("enviarApiFactura")!=null)/*305.211*/{_display_(Seq[Any](format.raw/*305.212*/("""
								"""),format.raw/*306.9*/("""<td style="text-align:center;">
									"""),_display_(if(lista1.get(17)!="0")/*307.34*/{_display_(Seq[Any](format.raw/*307.35*/("""
										"""),_display_(if(lista1.get(19)!=null)/*308.36*/{_display_(Seq[Any](format.raw/*308.37*/("""
											"""),_display_(if(!lista1.get(16).equals("Venta"))/*309.48*/{_display_(Seq[Any](format.raw/*309.49*/("""
												"""),_display_(if(lista1.get(21).equals("0"))/*310.44*/{_display_(Seq[Any](format.raw/*310.45*/("""
													"""),format.raw/*311.14*/("""<a href="#" onclick="document.getElementById('bloquear2').style.display='block'; enviarApiSapConconcreto('"""),_display_(/*311.121*/lista1/*311.127*/.get(0)),format.raw/*311.134*/("""',1)">
														<kbd style="background-color: #C39BD3">enviar</kbd>
													</a>
												""")))}else/*314.18*/{_display_(Seq[Any](format.raw/*314.19*/("""
													"""),format.raw/*315.14*/("""<a href="#" onclick="document.getElementById('bloquear2').style.display='block'; enviarApiSapConconcreto('"""),_display_(/*315.121*/lista1/*315.127*/.get(0)),format.raw/*315.134*/("""',0)">
														<kbd style="background-color: #85C1E9">reenviar</kbd>
													</a>
												""")))}),format.raw/*318.14*/("""
											""")))} else {null} ),format.raw/*319.13*/("""
										""")))}else/*320.16*/{_display_(Seq[Any](format.raw/*320.17*/("""
											"""),format.raw/*321.12*/("""--
										""")))}),format.raw/*322.12*/("""
									""")))}else/*323.15*/{_display_(Seq[Any](format.raw/*323.16*/("""
										"""),format.raw/*324.11*/("""--
									""")))}),format.raw/*325.11*/("""
								"""),format.raw/*326.9*/("""</td>
								<td style="text-align:center;">
									"""),_display_(if(!lista1.get(21).equals("0"))/*328.42*/{_display_(Seq[Any](format.raw/*328.43*/("""
										"""),_display_(/*329.12*/lista1/*329.18*/.get(21)),format.raw/*329.26*/("""
									""")))} else {null} ),format.raw/*330.11*/("""
								"""),format.raw/*331.9*/("""</td>
							""")))} else {null} ),format.raw/*332.9*/("""
							"""),_display_(if(mapPermiso.get("parametro.proformaListar-llenarWebIConstruye")!= null && !mapPermiso.get("parametro.proformaListar-llenarWebIConstruye").equals("") && mapPermiso.get("parametro.proformaListar-llenarWebIConstruye").equals("1") && mapPermiso.get("enviarApiFactura")!=null)/*333.282*/{_display_(Seq[Any](format.raw/*333.283*/("""
								"""),format.raw/*334.9*/("""<td style="text-align:center;">
									"""),_display_(if(lista1.get(17)!="0")/*335.34*/{_display_(Seq[Any](format.raw/*335.35*/("""
										"""),_display_(if(lista1.get(19)!=null)/*336.36*/{_display_(Seq[Any](format.raw/*336.37*/("""
												"""),_display_(if(lista1.get(21).equals("0"))/*337.44*/{_display_(Seq[Any](format.raw/*337.45*/("""
													"""),format.raw/*338.14*/("""<a href="#" onclick="document.getElementById('bloquear2').style.display='block'; enviarWebIConstruye('"""),_display_(/*338.117*/lista1/*338.123*/.get(0)),format.raw/*338.130*/("""')">
														<kbd style="background-color: #C39BD3">IC</kbd>
													</a>
												""")))}else/*341.18*/{_display_(Seq[Any](format.raw/*341.19*/("""
													"""),format.raw/*342.14*/("""<a href="#" onclick="downFacturaIconstruye('"""),_display_(/*342.59*/lista1/*342.65*/.get(0)),format.raw/*342.72*/("""')">
														<kbd style="background-color: rgb(0, 143, 81)">FACT: """),_display_(/*343.69*/lista1/*343.75*/.get(21)),format.raw/*343.83*/("""</kbd>
													</a>
												""")))}),format.raw/*345.14*/("""
										""")))}else/*346.16*/{_display_(Seq[Any](format.raw/*346.17*/("""
											"""),format.raw/*347.12*/("""--
										""")))}),format.raw/*348.12*/("""
									""")))}else/*349.15*/{_display_(Seq[Any](format.raw/*349.16*/("""
										"""),format.raw/*350.11*/("""--
									""")))}),format.raw/*351.11*/("""
								"""),format.raw/*352.9*/("""</td>
								
							""")))} else {null} ),format.raw/*354.9*/("""
							
							
						"""),_display_(if(mapPermiso.get("parametro.proformaListar-llenarWebMaximise")!=null && mapPermiso.get("parametro.proformaListar-llenarWebMaximise").equals("1") && mapPermiso.get("enviarApiFactura")!=null)/*357.198*/{_display_(Seq[Any](format.raw/*357.199*/("""
							"""),format.raw/*358.8*/("""<td style="text-align:center;">
								"""),_display_(if(lista1.get(16).equals(mapDiccionario.get("Arriendo")))/*359.67*/{_display_(Seq[Any](format.raw/*359.68*/("""
									"""),_display_(if(lista1.get(18).equals("0"))/*360.41*/{_display_(Seq[Any](format.raw/*360.42*/("""
										"""),format.raw/*361.11*/("""<a href="#" onclick= "$('#id_proformaWebMaximise').val('"""),_display_(/*361.68*/lista1/*361.74*/.get(0)),format.raw/*361.81*/("""'); $('#selectSucursal').modal('show');">
											<kbd style="background-color: #C39BD3">enviar</kbd>
										</a>
									""")))}else/*364.15*/{_display_(Seq[Any](format.raw/*364.16*/("""
										"""),format.raw/*365.11*/("""<a href="#" onclick= "$('#id_proformaWebMaximise').val('"""),_display_(/*365.68*/lista1/*365.74*/.get(0)),format.raw/*365.81*/("""'); $('#selectSucursal').modal('show');">
											<kbd style="background-color: #85C1E9">reenviar</kbd>
										</a>
									""")))}),format.raw/*368.11*/("""
									
								""")))}else/*370.14*/{_display_(Seq[Any](format.raw/*370.15*/("""
									"""),format.raw/*371.10*/("""--
								""")))}),format.raw/*372.10*/("""
							"""),format.raw/*373.8*/("""</td>
							<td style="text-align:center;">
								"""),_display_(if(lista1.get(16).equals(mapDiccionario.get("Arriendo")))/*375.67*/{_display_(Seq[Any](format.raw/*375.68*/("""
									"""),_display_(if(!lista1.get(21).equals("0"))/*376.42*/{_display_(Seq[Any](format.raw/*376.43*/("""
										"""),format.raw/*377.11*/("""<a href="/downFacturaMaximise/"""),_display_(/*377.42*/lista1/*377.48*/.get(21)),_display_(/*377.57*/desde),format.raw/*377.62*/(""","""),_display_(/*377.64*/hasta),format.raw/*377.69*/("""" > 
										<kbd style="background-color: rgb(50, 215, 75)">"""),_display_(/*378.60*/lista1/*378.66*/.get(21)),format.raw/*378.74*/("""</kbd>
									</a> 
									""")))}else/*380.15*/{_display_(Seq[Any](format.raw/*380.16*/("""
										"""),format.raw/*381.11*/("""--
									""")))}),format.raw/*382.11*/("""
								""")))}else/*383.14*/{_display_(Seq[Any](format.raw/*383.15*/("""
									"""),format.raw/*384.10*/("""--
								""")))}),format.raw/*385.10*/("""
							"""),format.raw/*386.8*/("""</td>
						""")))} else {null} ),format.raw/*387.8*/("""
						
						
						"""),_display_(if(mapPermiso.get("proformaListadoEliminar")!=null)/*390.59*/ {_display_(Seq[Any](format.raw/*390.61*/("""
							"""),format.raw/*391.8*/("""<td style="text-align:center;">
								<a href="#" onclick="eliminaProforma('"""),_display_(/*392.48*/lista1/*392.54*/.get(0)),format.raw/*392.61*/("""');">
									<kbd style="background-color: red">X</kbd>
								</a>
							</td>
						""")))} else {null} ),format.raw/*396.8*/("""
						
						"""),_display_(if(mapPermiso.get("parametro.proformaListar-llenarApiSapConconcreto")!=null && mapPermiso.get("parametro.proformaListar-llenarApiSapConconcreto").equals("1") && mapPermiso.get("enviarApiFactura")!=null)/*398.210*/{_display_(Seq[Any](format.raw/*398.211*/("""
								"""),format.raw/*399.9*/("""<td style="text-align:center;">
									"""),_display_(if(lista1.get(17)!="0")/*400.34*/{_display_(Seq[Any](format.raw/*400.35*/("""
										"""),_display_(if(lista1.get(19)!=null)/*401.36*/{_display_(Seq[Any](format.raw/*401.37*/("""
											"""),_display_(if(!lista1.get(16).equals("Venta"))/*402.48*/{_display_(Seq[Any](format.raw/*402.49*/("""
												"""),_display_(if(lista1.get(21).equals("0"))/*403.44*/{_display_(Seq[Any](format.raw/*403.45*/("""
													"""),format.raw/*404.14*/("""<a href="#" onclick="document.getElementById('bloquear2').style.display='block'; enviarApiSapConconcreto('"""),_display_(/*404.121*/lista1/*404.127*/.get(0)),format.raw/*404.134*/("""',1)">
														<kbd style="background-color: #C39BD3">enviar</kbd>
													</a>
												""")))}else/*407.18*/{_display_(Seq[Any](format.raw/*407.19*/("""
													"""),format.raw/*408.14*/("""<a href="#" onclick="document.getElementById('bloquear2').style.display='block'; enviarApiSapConconcreto('"""),_display_(/*408.121*/lista1/*408.127*/.get(0)),format.raw/*408.134*/("""',0)">
														<kbd style="background-color: #85C1E9">reenviar</kbd>
													</a>
												""")))}),format.raw/*411.14*/("""
											""")))} else {null} ),format.raw/*412.13*/("""
										""")))}else/*413.16*/{_display_(Seq[Any](format.raw/*413.17*/("""
											"""),format.raw/*414.12*/("""--
										""")))}),format.raw/*415.12*/("""
									""")))}else/*416.15*/{_display_(Seq[Any](format.raw/*416.16*/("""
										"""),format.raw/*417.11*/("""--
									""")))}),format.raw/*418.11*/("""
								"""),format.raw/*419.9*/("""</td>
								<td style="text-align:center;">
									"""),_display_(if(!lista1.get(21).equals("0"))/*421.42*/{_display_(Seq[Any](format.raw/*421.43*/("""
										"""),_display_(/*422.12*/lista1/*422.18*/.get(21)),format.raw/*422.26*/("""
									""")))} else {null} ),format.raw/*423.11*/("""
								"""),format.raw/*424.9*/("""</td>
						""")))} else {null} ),format.raw/*425.8*/("""
						
						
						
						"""),_display_(if(mapPermiso.get("parametro.proformaListar-llenarApiRelBase")!=null && mapPermiso.get("parametro.proformaListar-llenarApiRelBase").equals("1") && mapPermiso.get("enviarApiFactura")!=null)/*429.196*/{_display_(Seq[Any](format.raw/*429.197*/("""
								"""),format.raw/*430.9*/("""<td style="text-align:center;">
									"""),_display_(if(lista1.get(21).equals("0"))/*431.41*/{_display_(Seq[Any](format.raw/*431.42*/("""
										"""),format.raw/*432.11*/("""<a href="#" onclick="document.getElementById('bloquear2').style.display='block'; enviarApiRelBase('"""),_display_(/*432.111*/lista1/*432.117*/.get(0)),format.raw/*432.124*/("""')">
											<kbd style="background-color: #C39BD3">enviar</kbd>
										</a>
									""")))}else/*435.15*/{_display_(Seq[Any](format.raw/*435.16*/("""
										"""),_display_(if(!lista1.get(21).equals("0"))/*436.43*/{_display_(Seq[Any](format.raw/*436.44*/("""
											"""),format.raw/*437.12*/("""<div id="pdfRelBase_"""),_display_(/*437.33*/lista1/*437.39*/.get(21)),format.raw/*437.47*/("""">
												<a href="#" onclick="pdfReBase('"""),_display_(/*438.46*/lista1/*438.52*/.get(21)),format.raw/*438.60*/("""')">
													<kbd style="background-color: rgb(50, 215, 75)">Fact: """),_display_(/*439.69*/lista1/*439.75*/.get(21)),format.raw/*439.83*/("""</kbd>
												</a>
											</div>
										""")))}else/*442.16*/{_display_(Seq[Any](format.raw/*442.17*/("""
											"""),format.raw/*443.12*/("""--
										""")))}),format.raw/*444.12*/("""
									""")))}),format.raw/*445.11*/("""
								"""),format.raw/*446.9*/("""</td>
						""")))} else {null} ),format.raw/*447.8*/("""
						
						"""),_display_(if(mapPermiso.get("parametro.proformaListar-llenarApiSapSchwager")!=null && mapPermiso.get("parametro.proformaListar-llenarApiSapSchwager").equals("1") && mapPermiso.get("enviarApiFactura")!=null)/*449.204*/{_display_(Seq[Any](format.raw/*449.205*/("""
								"""),format.raw/*450.9*/("""<td style="text-align:center;">
									"""),_display_(if(lista1.get(21).equals("0"))/*451.41*/{_display_(Seq[Any](format.raw/*451.42*/("""
										"""),format.raw/*452.11*/("""<a href="#" onclick="document.getElementById('bloquear2').style.display='block'; enviarApiSapSchwager('"""),_display_(/*452.115*/lista1/*452.121*/.get(0)),format.raw/*452.128*/("""')">
											<kbd style="background-color: #C39BD3">enviar</kbd>
										</a>
									""")))}else/*455.15*/{_display_(Seq[Any](format.raw/*455.16*/("""
										"""),_display_(/*456.12*/lista1/*456.18*/.get(21)),format.raw/*456.26*/("""
									""")))}),format.raw/*457.11*/("""
								"""),format.raw/*458.9*/("""</td>
						""")))} else {null} ),format.raw/*459.8*/("""
					"""),format.raw/*460.6*/("""</tr>
	 			""")))}),format.raw/*461.7*/("""
				"""),format.raw/*462.5*/("""</tbody>
			</table>
		</div>
	</div>

	<div id='muestraPDF' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>EP/PROFORMA</h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<div id="rutaPDF"></div>
	   				<div class='row'>
						<div class='col-sm-12' style='text-align:center'>
							<button type='button' class='btn btn-sm  btn-success' style='font-size: 10px;' data-dismiss='modal'>Listo</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<form class="formulario" id="formEnviarXml" method="post" action="/generaProformaXml/">	
		<input type="hidden" id="id_proformaXml" name="id_proforma">
		<input type="hidden" name="fechaDesde" value=""""),_display_(/*490.50*/desde),format.raw/*490.55*/("""">
		<input type="hidden" name="fechaHasta" value=""""),_display_(/*491.50*/hasta),format.raw/*491.55*/("""">
	</form>
	
	<form class="formulario" id="formEnviaApiManager" method="post" action="/generaProformaApiManager/">	
		<input type="hidden" id="id_proformaManager" name="id_proforma">
		<input type="hidden" name="fechaDesde" value=""""),_display_(/*496.50*/desde),format.raw/*496.55*/("""">
		<input type="hidden" name="fechaHasta" value=""""),_display_(/*497.50*/hasta),format.raw/*497.55*/("""">
	</form>
	
	<form class="formulario" id="formEnviaApiNubox" method="post" action="/generaProformaApiNubox/">	
		<input type="hidden" id="id_proformaNubox" name="id_proforma">
		<input type="hidden" name="fechaDesde" value=""""),_display_(/*502.50*/desde),format.raw/*502.55*/("""">
		<input type="hidden" name="fechaHasta" value=""""),_display_(/*503.50*/hasta),format.raw/*503.55*/("""">
	</form>
	
	<form class="formulario" id="formEnviaApiSapConconcreto" method="post" action="/generaProformaApiSapConconcreto/">	
		<input type="hidden" id="id_proformaSapConconcreto" name="id_proforma">
		<input type="hidden" name="fechaDesde" value=""""),_display_(/*508.50*/desde),format.raw/*508.55*/("""">
		<input type="hidden" name="fechaHasta" value=""""),_display_(/*509.50*/hasta),format.raw/*509.55*/("""">
	</form>

	<form class="formulario" id="formEnviaWebIConstruye" method="post" action="/generaFacturaIConstruye/">	
		<input type="hidden" id="id_proformaWebIConstruye" name="id_proforma">
		<input type="hidden" name="fechaDesde" value=""""),_display_(/*514.50*/desde),format.raw/*514.55*/("""">
		<input type="hidden" name="fechaHasta" value=""""),_display_(/*515.50*/hasta),format.raw/*515.55*/("""">
	</form>
	
	<form class="formulario" id="downFacturaIconstruye" method="post" action="/downFacturaIconstruye/">	
		<input type="hidden" id="id_facturaIConstruye2" name="id_proforma">
		<input type="hidden" name="fechaDesde" value=""""),_display_(/*520.50*/desde),format.raw/*520.55*/("""">
		<input type="hidden" name="fechaHasta" value=""""),_display_(/*521.50*/hasta),format.raw/*521.55*/("""">
	</form>
	
	<form class="formulario" id="formEnviaWebMaximise" method="post" action="/generaProformaWebMaximise/">	
		<input type="hidden" id="id_proformaWebMaximise" name="id_proforma">
		<input type="hidden" id="sucursalMaximise" name="sucursalMaximise">
		<input type="hidden" id="codigoMaximise" name="codigoMaximise">
		<input type="hidden" name="fechaDesde" value=""""),_display_(/*528.50*/desde),format.raw/*528.55*/("""">
		<input type="hidden" name="fechaHasta" value=""""),_display_(/*529.50*/hasta),format.raw/*529.55*/("""">
	</form>
	
	<form class="formulario" id="formEnviaApiRelBase" method="post" action="/generaProformaApiRelBase/">	
		<input type="hidden" id="id_proformaApiRelBase" name="id_proforma">
		<input type="hidden" name="fechaDesde" value=""""),_display_(/*534.50*/desde),format.raw/*534.55*/("""">
		<input type="hidden" name="fechaHasta" value=""""),_display_(/*535.50*/hasta),format.raw/*535.55*/("""">
	</form>
	
	<form class="formulario" id="formEnviaApiSapSchwager" method="post" action="/generaProformaApiSapSchwager/">	
		<input type="hidden" id="id_proformaSapSchwager" name="id_proforma">
		<input type="hidden" name="fechaDesde" value=""""),_display_(/*540.50*/desde),format.raw/*540.55*/("""">
		<input type="hidden" name="fechaHasta" value=""""),_display_(/*541.50*/hasta),format.raw/*541.55*/("""">
	</form>
	
	
	
	
	
	<form class="formulario" id="formDescargaDocumento" method="post" action="/downloadPDF/">	
		<input type="hidden" id="descargaDocumento" name="nombreArchivo">
		<input type="hidden" name="fechaDesde" value=""""),_display_(/*550.50*/desde),format.raw/*550.55*/("""">
		<input type="hidden" name="fechaHasta" value=""""),_display_(/*551.50*/hasta),format.raw/*551.55*/("""">
	</form>

	<form id="form_eliminar" method="post" action="/proformaElimina/">
		<input type="hidden" id="form_id_proforma" name="id_proforma">
		<input type="hidden" name="fechaDesde" value=""""),_display_(/*556.50*/desde),format.raw/*556.55*/("""">
		<input type="hidden" name="fechaHasta" value=""""),_display_(/*557.50*/hasta),format.raw/*557.55*/("""">
	</form>
	
	<div id='selectSucursal' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-sm' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>Seleccione Sucursal</h5>
				</div>
				<div class='modal-body'>
					<a href="" onclick='$("#sucursalMaximise").val("1"); $("#selectCodigo").modal("show");' data-dismiss='modal'>BSTGO CENTRAL SANTIAGO</a>
					<br>
					<a href="" onclick='$("#sucursalMaximise").val("2"); $("#selectCodigo").modal("show");' data-dismiss='modal'>BANTO CENTRAL ANTOFAGASTA</a>
				</div>
			</div>
		</div>
	</div>
	
	<div id='selectCodigo' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-sm' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>Seleccione Concepto</h5>
				</div>
				<div class='modal-body'>
					<a href="" onclick='$("#codigoMaximise").val("VT-001"); enviarWebMaximise()' data-dismiss='modal'>VT-001 ARRIENDO ANDAMIOS</a>
					<br>
					<a href="" onclick='$("#codigoMaximise").val("VT-002"); enviarWebMaximise()' data-dismiss='modal'>VT-002 ARRIENDO ENCOFRADOS</a>
				</div>
			</div>
		</div>
	</div>
	
	<div id="modalNotasVenta" class="modal" role="dialog" data-backdrop="static" data-keyboard="false">
	  <div class="modal-dialog modal-sm modal-dialog-scrollable" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title">Notas de venta desde un Excel</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        
			<div class="row">
				<div class="col-sm-12" style="text-align:center">
					<form action="/hohePlantillaNV/" method="POST"  onsubmit="return validarForm2();">
						<button type="submit" id="btnSubmit2" class="btn btn-sm  btn-success" style="font-size: 10px;">DESCARGAR PLANTILLA</button>
					</form>
					<br>
					<span class="btn btn-info btn-sm btn-file" style="font-size: 8px;">
						<div>SUBIR ARCHIVO</div>
						<input type="file" id="archivoXLSX"  value="" onchange="subirArchivo(this.form, this); $('#modalNotasVenta').modal('hide');">
					</span>
					<br><br>
					<button type="button" class="btn btn-sm  btn-warning" style="font-size: 10px;" data-dismiss="modal">Cancelar</button>
				</div>
			</div>
			<br>
	      </div>
	    </div>
	  </div>
	</div>
	
""")))}),format.raw/*621.2*/("""


"""),format.raw/*624.1*/("""<script type="text/javascript">

	const validarForm = () =>"""),format.raw/*626.27*/("""{"""),format.raw/*626.28*/("""
		"""),format.raw/*627.3*/("""$("#btnSubmit").attr('disabled',true);
		let flag = true;
		var desde = document.getElementById('nroIni').value;
		var hasta = document.getElementById('nroFin').value;
		if(parseFloat(desde) > parseFloat(hasta))"""),format.raw/*631.44*/("""{"""),format.raw/*631.45*/("""
			"""),format.raw/*632.4*/("""flag = false;
			alertify.alert('EL NUMERO DESDE NO PUEDE SER MAYOR AL NUMERO HASTA', function () """),format.raw/*633.85*/("""{"""),format.raw/*633.86*/("""
				"""),format.raw/*634.5*/("""$("#btnSubmit").attr('disabled',false);
				return(flag);
     		"""),format.raw/*636.8*/("""}"""),format.raw/*636.9*/(""");
		"""),format.raw/*637.3*/("""}"""),format.raw/*637.4*/("""
		"""),format.raw/*638.3*/("""let aux =  parseFloat(hasta) - parseFloat(desde);
		if(aux > 100)"""),format.raw/*639.16*/("""{"""),format.raw/*639.17*/("""
			"""),format.raw/*640.4*/("""flag = false;
			alertify.alert('LA CANTIDAD DE PROFORMAS SELECCIONADAS NO PUEDE SER MAYOR A 100', function () """),format.raw/*641.98*/("""{"""),format.raw/*641.99*/("""
				"""),format.raw/*642.5*/("""$("#btnSubmit").attr('disabled',false);
				return(flag);
     		"""),format.raw/*644.8*/("""}"""),format.raw/*644.9*/(""");
		"""),format.raw/*645.3*/("""}"""),format.raw/*645.4*/("""
		
		
		"""),format.raw/*648.3*/("""return(flag);
	"""),format.raw/*649.2*/("""}"""),format.raw/*649.3*/("""

	"""),format.raw/*651.2*/("""$(document).ready(function() """),format.raw/*651.31*/("""{"""),format.raw/*651.32*/("""
		
		"""),format.raw/*653.3*/("""$('#tablaPrincipal').DataTable("""),format.raw/*653.34*/("""{"""),format.raw/*653.35*/("""
		    	"""),format.raw/*654.8*/(""""fixedHeader": true,
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [[ 2, "desc" ],[ 1, "desc" ]],
		    	"language": """),format.raw/*659.20*/("""{"""),format.raw/*659.21*/("""
		        	"""),format.raw/*660.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*661.11*/("""}"""),format.raw/*661.12*/("""
		  """),format.raw/*662.5*/("""}"""),format.raw/*662.6*/(""" """),format.raw/*662.7*/(""");

			document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*665.2*/("""}"""),format.raw/*665.3*/(""");
	
	
	const enviarXML = (id_proforma) =>"""),format.raw/*668.36*/("""{"""),format.raw/*668.37*/("""
		"""),format.raw/*669.3*/("""$('#id_proformaXml').val(id_proforma);
		document.getElementById('formEnviarXml').submit();
	"""),format.raw/*671.2*/("""}"""),format.raw/*671.3*/("""
	
	"""),format.raw/*673.2*/("""const enviarApiManager = (id_proforma) =>"""),format.raw/*673.43*/("""{"""),format.raw/*673.44*/("""
		"""),format.raw/*674.3*/("""$('#id_proformaManager').val(id_proforma);
		document.getElementById('formEnviaApiManager').submit();
	"""),format.raw/*676.2*/("""}"""),format.raw/*676.3*/("""
	
	"""),format.raw/*678.2*/("""const enviarApiNubox = (id_proforma) =>"""),format.raw/*678.41*/("""{"""),format.raw/*678.42*/("""
		"""),format.raw/*679.3*/("""$('#id_proformaNubox').val(id_proforma);
		document.getElementById('formEnviaApiNubox').submit();
	"""),format.raw/*681.2*/("""}"""),format.raw/*681.3*/("""
	
	"""),format.raw/*683.2*/("""const enviarApiSapConconcreto = (id_proforma, primeraVez) =>"""),format.raw/*683.62*/("""{"""),format.raw/*683.63*/("""
		"""),format.raw/*684.3*/("""$('#id_proformaSapConconcreto').val(id_proforma);
		if(primeraVez == 1)"""),format.raw/*685.22*/("""{"""),format.raw/*685.23*/("""
			"""),format.raw/*686.4*/("""document.getElementById('formEnviaApiSapConconcreto').submit();
		"""),format.raw/*687.3*/("""}"""),format.raw/*687.4*/("""else"""),format.raw/*687.8*/("""{"""),format.raw/*687.9*/("""
			"""),format.raw/*688.4*/("""alertify.confirm('Esta seguro de re-enviar """),_display_(/*688.48*/mapDiccionario("PROFORMA")),format.raw/*688.74*/(""" """),format.raw/*688.75*/("""numero: '+id_proforma+"\n esto generara un nuevo número y documento en SAP.\nNOTA: Si el reenvio es debido a cambios efectuados en mada, este reenvio no sirve, debe generar una nueva proforma para que aplique los cambios.", function (e) """),format.raw/*688.312*/("""{"""),format.raw/*688.313*/("""
				"""),format.raw/*689.5*/("""if (e) """),format.raw/*689.12*/("""{"""),format.raw/*689.13*/("""
					"""),format.raw/*690.6*/("""document.getElementById('formEnviaApiSapConconcreto').submit();
				"""),format.raw/*691.5*/("""}"""),format.raw/*691.6*/("""else"""),format.raw/*691.10*/("""{"""),format.raw/*691.11*/("""
					"""),format.raw/*692.6*/("""document.getElementById('bloquear2').style.display='none';
				"""),format.raw/*693.5*/("""}"""),format.raw/*693.6*/("""
			"""),format.raw/*694.4*/("""}"""),format.raw/*694.5*/(""");
		"""),format.raw/*695.3*/("""}"""),format.raw/*695.4*/("""
		
	"""),format.raw/*697.2*/("""}"""),format.raw/*697.3*/("""
	
	"""),format.raw/*699.2*/("""const enviarApiSapSchwager = (id_proforma) =>"""),format.raw/*699.47*/("""{"""),format.raw/*699.48*/("""
		"""),format.raw/*700.3*/("""$('#id_proformaSapSchwager').val(id_proforma);
		document.getElementById('formEnviaApiSapSchwager').submit();
	"""),format.raw/*702.2*/("""}"""),format.raw/*702.3*/("""
	
	"""),format.raw/*704.2*/("""const enviarApiRelBase = (id_proforma) =>"""),format.raw/*704.43*/("""{"""),format.raw/*704.44*/("""
		"""),format.raw/*705.3*/("""$('#id_proformaApiRelBase').val(id_proforma);
		document.getElementById('formEnviaApiRelBase').submit();
	"""),format.raw/*707.2*/("""}"""),format.raw/*707.3*/("""
	
	"""),format.raw/*709.2*/("""const enviarWebIConstruye = (id_proforma) =>"""),format.raw/*709.46*/("""{"""),format.raw/*709.47*/("""
		"""),format.raw/*710.3*/("""$('#id_proformaWebIConstruye').val(id_proforma);
		document.getElementById('formEnviaWebIConstruye').submit();
	"""),format.raw/*712.2*/("""}"""),format.raw/*712.3*/("""
	
	"""),format.raw/*714.2*/("""const enviarWebMaximise = () =>"""),format.raw/*714.33*/("""{"""),format.raw/*714.34*/("""
		"""),format.raw/*715.3*/("""document.getElementById('formEnviaWebMaximise').submit();
	"""),format.raw/*716.2*/("""}"""),format.raw/*716.3*/("""
	
	"""),format.raw/*718.2*/("""const descargaDocumento = (nombreDOC) => """),format.raw/*718.43*/("""{"""),format.raw/*718.44*/("""
		  """),format.raw/*719.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*721.2*/("""}"""),format.raw/*721.3*/("""
	
	"""),format.raw/*723.2*/("""const mostrarPDF = (nombrePDF) => """),format.raw/*723.36*/("""{"""),format.raw/*723.37*/("""
		  """),format.raw/*724.5*/("""document.getElementById('rutaPDF').innerHTML="<object data='/showPdf/"+nombrePDF+"' type='application/pdf' width='100%' height='720'></object>";
		  $('#muestraPDF').modal('show');
	"""),format.raw/*726.2*/("""}"""),format.raw/*726.3*/("""
	
	"""),format.raw/*728.2*/("""const eliminaProforma = (id_proforma) => """),format.raw/*728.43*/("""{"""),format.raw/*728.44*/("""
		"""),format.raw/*729.3*/("""alertify.confirm('Esta seguro de eliminar """),_display_(/*729.46*/mapDiccionario("PROFORMA")),format.raw/*729.72*/(""" """),format.raw/*729.73*/("""numero: '+id_proforma, function (e) """),format.raw/*729.109*/("""{"""),format.raw/*729.110*/("""
			"""),format.raw/*730.4*/("""if (e) """),format.raw/*730.11*/("""{"""),format.raw/*730.12*/("""
				"""),format.raw/*731.5*/("""$("#form_id_proforma").val(id_proforma);
				document.getElementById("form_eliminar").submit();
			"""),format.raw/*733.4*/("""}"""),format.raw/*733.5*/("""
		"""),format.raw/*734.3*/("""}"""),format.raw/*734.4*/(""");
	"""),format.raw/*735.2*/("""}"""),format.raw/*735.3*/("""
	
	"""),format.raw/*737.2*/("""const downFacturaIconstruye = id_guia =>"""),format.raw/*737.42*/("""{"""),format.raw/*737.43*/("""
		"""),format.raw/*738.3*/("""$('#id_facturaIConstruye2').val(id_guia);
		document.getElementById('downFacturaIconstruye').submit();
	"""),format.raw/*740.2*/("""}"""),format.raw/*740.3*/("""
	
	
	"""),format.raw/*743.2*/("""let extArray2 = new Array(".xls", ".xlsx");
	function subirArchivo(form, nodo) """),format.raw/*744.36*/("""{"""),format.raw/*744.37*/("""
		"""),format.raw/*745.3*/("""allowSubmit = false;
		if (!nodo.files[0])"""),format.raw/*746.22*/("""{"""),format.raw/*746.23*/("""
			"""),format.raw/*747.4*/("""return;
		"""),format.raw/*748.3*/("""}"""),format.raw/*748.4*/("""
		
		"""),format.raw/*750.3*/("""let nombre = nodo.files[0].name;
		while (nombre.indexOf("\\") != -1)"""),format.raw/*751.37*/("""{"""),format.raw/*751.38*/("""
			"""),format.raw/*752.4*/("""nombre = nombre.slice(nombre.indexOf("\\") + 1);
		"""),format.raw/*753.3*/("""}"""),format.raw/*753.4*/("""

		"""),format.raw/*755.3*/("""ext = nombre.slice(nombre.lastIndexOf(".")).toLowerCase();
		for (var i = 0; i < extArray2.length; i++) """),format.raw/*756.46*/("""{"""),format.raw/*756.47*/("""
			"""),format.raw/*757.4*/("""if (extArray2[i] == ext) """),format.raw/*757.29*/("""{"""),format.raw/*757.30*/(""" """),format.raw/*757.31*/("""allowSubmit = true; break; """),format.raw/*757.58*/("""}"""),format.raw/*757.59*/("""
		"""),format.raw/*758.3*/("""}"""),format.raw/*758.4*/("""
		"""),format.raw/*759.3*/("""if (allowSubmit) """),format.raw/*759.20*/("""{"""),format.raw/*759.21*/("""
			"""),format.raw/*760.4*/("""var formData = new FormData();
			formData.append("file",nodo.files[0]);
			formData.append("hoheValySubePlantillaNV","hoheValySubePlantillaNV");
			$.ajax("""),format.raw/*763.11*/("""{"""),format.raw/*763.12*/("""
				"""),format.raw/*764.5*/("""async: false,
				url: "/hoheValySubePlantillaNV/",
		        type: "POST",
		        method: "POST",
		        data: formData,
		        cache: false,
		        contentType: false,
		     	processData: false,
		     	success: function(rs)"""),format.raw/*772.30*/("""{"""),format.raw/*772.31*/("""
		     		"""),format.raw/*773.10*/("""if(rs["status"])"""),format.raw/*773.26*/("""{"""),format.raw/*773.27*/("""
						"""),format.raw/*774.7*/("""if(rs["error"])"""),format.raw/*774.22*/("""{"""),format.raw/*774.23*/("""
							"""),format.raw/*775.8*/("""let errores = rs["detalle"];
							alertify.alert(""+errores);
						"""),format.raw/*777.7*/("""}"""),format.raw/*777.8*/("""else"""),format.raw/*777.12*/("""{"""),format.raw/*777.13*/("""
							"""),format.raw/*778.8*/("""alertify.alert(rs["msg"], function () """),format.raw/*778.46*/("""{"""),format.raw/*778.47*/("""
								"""),format.raw/*779.9*/("""location.reload();
				     		"""),format.raw/*780.12*/("""}"""),format.raw/*780.13*/(""");
						"""),format.raw/*781.7*/("""}"""),format.raw/*781.8*/("""
		     		"""),format.raw/*782.10*/("""}"""),format.raw/*782.11*/("""else"""),format.raw/*782.15*/("""{"""),format.raw/*782.16*/("""
						"""),format.raw/*783.7*/("""alertify.alert("SE PRESENTO UN ERROR");
		     		"""),format.raw/*784.10*/("""}"""),format.raw/*784.11*/("""
				"""),format.raw/*785.5*/("""}"""),format.raw/*785.6*/("""
			 """),format.raw/*786.5*/("""}"""),format.raw/*786.6*/(""");
		"""),format.raw/*787.3*/("""}"""),format.raw/*787.4*/("""else"""),format.raw/*787.8*/("""{"""),format.raw/*787.9*/("""
			"""),format.raw/*788.4*/("""alertify.alert("Se permiten &uacute;nicamente archivos con la extenci&oacute;n: " 
			+ (extArray2.join("  ")) + "\nPor favor, seleccione un archivo valido "
			+ "e intente de nuevo.");
			form.archivoXLSX.value="";
		"""),format.raw/*792.3*/("""}"""),format.raw/*792.4*/("""
		
	"""),format.raw/*794.2*/("""}"""),format.raw/*794.3*/("""
	
	"""),format.raw/*796.2*/("""const validarForm2 = () =>"""),format.raw/*796.28*/("""{"""),format.raw/*796.29*/("""
		"""),format.raw/*797.3*/("""$("#btnSubmit2").attr('disabled',true);
		return(true);
	"""),format.raw/*799.2*/("""}"""),format.raw/*799.3*/("""
	
	"""),format.raw/*801.2*/("""const pdfReBase = (nroFiscal) =>"""),format.raw/*801.34*/("""{"""),format.raw/*801.35*/("""
		"""),format.raw/*802.3*/("""$('#linkRelBase').text("GUIA");
		document.getElementById('rutaPDF').innerHTML="<object data='/showPdfRelBase/"+33+","+nroFiscal+"' type='application/pdf' width='100%' height='720'></object>";
		$('#muestraPDF').modal('show');
	"""),format.raw/*805.2*/("""}"""),format.raw/*805.3*/("""
	
"""),format.raw/*807.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,lista:List[List[String]],desde:String,hasta:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,lista,desde,hasta)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,lista,desde,hasta) => apply(mapDiccionario,mapPermiso,userMnu,lista,desde,hasta)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/proformaLista.scala.html
                  HASH: 453391532b4a0dbfcefb48e22a31e4d03d1c1622
                  MATRIX: 1046->1|1294->156|1326->163|1342->171|1381->173|1410->177|1478->225|1506->227|1582->278|1670->346|1701->350|2280->902|2319->903|2353->910|2627->1141|2661->1148|3423->1883|3449->1888|3538->1950|3564->1955|4081->2444|4110->2445|4147->2454|4176->2455|4615->2866|4644->2867|4681->2876|4710->2877|6484->4623|6511->4628|6592->4681|6619->4686|7106->5145|7130->5159|7166->5173|7628->5606|7669->5607|7705->5615|7777->5643|8078->5915|8119->5916|8155->5924|8256->5981|8551->6247|8592->6248|8628->6256|8727->6311|9049->6604|9090->6605|9126->6613|9281->6724|9591->7005|9632->7006|9668->7014|9777->7079|10004->7277|10045->7278|10081->7286|10181->7342|10268->7401|10309->7403|10345->7411|10409->7431|10634->7627|10675->7628|10711->7636|10786->7667|11019->7871|11060->7872|11096->7880|11167->7907|11222->7934|11286->7971|11323->7991|11363->7992|11398->7999|11472->8045|11488->8051|11518->8059|11593->8106|11609->8112|11638->8119|11763->8216|11782->8225|11833->8254|11877->8270|11893->8276|11922->8283|11958->8291|12083->8388|12102->8397|12153->8426|12197->8442|12213->8448|12242->8455|12278->8463|12404->8561|12423->8570|12474->8599|12518->8615|12534->8621|12563->8628|12599->8636|12689->8698|12705->8704|12734->8711|12807->8756|12823->8762|12852->8769|12925->8814|12941->8820|12971->8828|13044->8873|13060->8879|13090->8887|13163->8932|13179->8938|13209->8946|13282->8991|13298->8997|13327->9004|13401->9050|13417->9056|13446->9063|13520->9109|13536->9115|13565->9122|13639->9168|13655->9174|13685->9182|13792->9261|13832->9262|13871->9272|13933->9306|13949->9312|13979->9320|14096->9417|14136->9418|14175->9428|14219->9440|14255->9448|14360->9525|14400->9526|14439->9536|14509->9578|14525->9584|14555->9592|14666->9683|14706->9684|14745->9694|14789->9706|14825->9714|14930->9791|14970->9792|15009->9802|15079->9844|15095->9850|15125->9858|15236->9949|15276->9950|15315->9960|15359->9972|15395->9980|15505->10062|15545->10063|15584->10073|15654->10115|15670->10121|15700->10129|15811->10220|15851->10221|15890->10231|15934->10243|15970->10251|16157->10409|16198->10410|16235->10419|16328->10484|16368->10485|16408->10496|16469->10529|16485->10535|16514->10542|16589->10589|16629->10590|16671->10603|16758->10670|16798->10671|16840->10684|16938->10750|16978->10761|17017->10780|17057->10781|17097->10792|17142->10805|17179->10814|17237->10828|17469->11031|17510->11032|17547->11041|17640->11106|17680->11107|17744->11143|17784->11144|17825->11156|17893->11196|17909->11202|17938->11209|18058->11309|18098->11310|18139->11322|18185->11336|18220->11351|18260->11352|18300->11363|18345->11376|18382->11385|18440->11399|18662->11592|18703->11593|18740->11602|18833->11667|18873->11668|18937->11704|18977->11705|19053->11753|19093->11754|19195->11828|19235->11829|19278->11843|19344->11881|19360->11887|19389->11894|19515->12000|19555->12001|19598->12015|19664->12053|19680->12059|19709->12066|19845->12170|19903->12183|19939->12199|19979->12200|20020->12212|20066->12226|20101->12241|20141->12242|20181->12253|20226->12266|20263->12275|20321->12289|20561->12500|20602->12501|20639->12510|20732->12575|20772->12576|20836->12612|20876->12613|20952->12661|20992->12662|21064->12706|21104->12707|21147->12721|21283->12828|21300->12834|21330->12841|21458->12949|21498->12950|21541->12964|21677->13071|21694->13077|21724->13084|21862->13190|21920->13203|21956->13219|21996->13220|22037->13232|22083->13246|22118->13261|22158->13262|22198->13273|22243->13286|22280->13295|22395->13382|22435->13383|22475->13395|22491->13401|22521->13409|22577->13420|22614->13429|22672->13443|22983->13725|23024->13726|23061->13735|23154->13800|23194->13801|23258->13837|23298->13838|23370->13882|23410->13883|23453->13897|23585->14000|23602->14006|23632->14013|23754->14115|23794->14116|23837->14130|23910->14175|23926->14181|23955->14188|24056->14261|24072->14267|24102->14275|24172->14313|24208->14329|24248->14330|24289->14342|24335->14356|24370->14371|24410->14372|24450->14383|24495->14396|24532->14405|24599->14428|24842->14642|24883->14643|24919->14651|25045->14749|25085->14750|25154->14791|25194->14792|25234->14803|25319->14860|25335->14866|25364->14873|25518->15007|25558->15008|25598->15019|25683->15076|25699->15082|25728->15089|25892->15221|25936->15245|25976->15246|26015->15256|26059->15268|26095->15276|26234->15387|26274->15388|26344->15430|26384->15431|26424->15442|26483->15473|26499->15479|26529->15488|26556->15493|26586->15495|26613->15500|26705->15564|26721->15570|26751->15578|26807->15614|26847->15615|26887->15626|26932->15639|26966->15653|27006->15654|27045->15664|27089->15676|27125->15684|27182->15697|27283->15770|27324->15772|27360->15780|27467->15859|27483->15865|27512->15872|27647->15963|27893->16180|27934->16181|27971->16190|28064->16255|28104->16256|28168->16292|28208->16293|28284->16341|28324->16342|28396->16386|28436->16387|28479->16401|28615->16508|28632->16514|28662->16521|28790->16629|28830->16630|28873->16644|29009->16751|29026->16757|29056->16764|29194->16870|29252->16883|29288->16899|29328->16900|29369->16912|29415->16926|29450->16941|29490->16942|29530->16953|29575->16966|29612->16975|29727->17062|29767->17063|29807->17075|29823->17081|29853->17089|29909->17100|29946->17109|30003->17122|30249->17339|30290->17340|30327->17349|30427->17421|30467->17422|30507->17433|30636->17533|30653->17539|30683->17546|30800->17643|30840->17644|30911->17687|30951->17688|30992->17700|31041->17721|31057->17727|31087->17735|31163->17783|31179->17789|31209->17797|31310->17870|31326->17876|31356->17884|31433->17941|31473->17942|31514->17954|31560->17968|31603->17979|31640->17988|31697->18001|31937->18212|31978->18213|32015->18222|32115->18294|32155->18295|32195->18306|32328->18410|32345->18416|32375->18423|32492->18520|32532->18521|32572->18533|32588->18539|32618->18547|32661->18558|32698->18567|32755->18580|32789->18586|32832->18598|32865->18603|33920->19630|33947->19635|34027->19687|34054->19692|34315->19925|34342->19930|34422->19982|34449->19987|34704->20214|34731->20219|34811->20271|34838->20276|35120->20530|35147->20535|35227->20587|35254->20592|35522->20832|35549->20837|35629->20889|35656->20894|35919->21129|35946->21134|36026->21186|36053->21191|36456->21566|36483->21571|36563->21623|36590->21628|36854->21864|36881->21869|36961->21921|36988->21926|37261->22171|37288->22176|37368->22228|37395->22233|37654->22464|37681->22469|37761->22521|37788->22526|38011->22721|38038->22726|38118->22778|38145->22783|40812->25419|40843->25422|40931->25481|40961->25482|40992->25485|41232->25696|41262->25697|41294->25701|41421->25799|41451->25800|41484->25805|41577->25870|41606->25871|41639->25876|41668->25877|41699->25880|41793->25945|41823->25946|41855->25950|41995->26061|42025->26062|42058->26067|42151->26132|42180->26133|42213->26138|42242->26139|42279->26148|42322->26163|42351->26164|42382->26167|42440->26196|42470->26197|42504->26203|42564->26234|42594->26235|42630->26243|42813->26397|42843->26398|42884->26410|42991->26488|43021->26489|43054->26494|43083->26495|43112->26496|43213->26569|43242->26570|43313->26612|43343->26613|43374->26616|43495->26709|43524->26710|43556->26714|43626->26755|43656->26756|43687->26759|43818->26862|43847->26863|43879->26867|43947->26906|43977->26907|44008->26910|44135->27009|44164->27010|44196->27014|44285->27074|44315->27075|44346->27078|44446->27149|44476->27150|44508->27154|44602->27220|44631->27221|44663->27225|44692->27226|44724->27230|44796->27274|44844->27300|44874->27301|45141->27538|45172->27539|45205->27544|45241->27551|45271->27552|45305->27558|45401->27626|45430->27627|45463->27631|45493->27632|45527->27638|45618->27701|45647->27702|45679->27706|45708->27707|45741->27712|45770->27713|45803->27718|45832->27719|45864->27723|45938->27768|45968->27769|45999->27772|46138->27883|46167->27884|46199->27888|46269->27929|46299->27930|46330->27933|46464->28039|46493->28040|46525->28044|46598->28088|46628->28089|46659->28092|46799->28204|46828->28205|46860->28209|46920->28240|46950->28241|46981->28244|47068->28303|47097->28304|47129->28308|47199->28349|47229->28350|47262->28355|47394->28459|47423->28460|47455->28464|47518->28498|47548->28499|47581->28504|47791->28686|47820->28687|47852->28691|47922->28732|47952->28733|47983->28736|48054->28779|48102->28805|48132->28806|48198->28842|48229->28843|48261->28847|48297->28854|48327->28855|48360->28860|48487->28959|48516->28960|48547->28963|48576->28964|48608->28968|48637->28969|48669->28973|48738->29013|48768->29014|48799->29017|48931->29121|48960->29122|48994->29128|49102->29207|49132->29208|49163->29211|49234->29253|49264->29254|49296->29258|49334->29268|49363->29269|49397->29275|49495->29344|49525->29345|49557->29349|49636->29400|49665->29401|49697->29405|49830->29509|49860->29510|49892->29514|49946->29539|49976->29540|50006->29541|50062->29568|50092->29569|50123->29572|50152->29573|50183->29576|50229->29593|50259->29594|50291->29598|50476->29754|50506->29755|50539->29760|50807->29999|50837->30000|50876->30010|50921->30026|50951->30027|50986->30034|51030->30049|51060->30050|51096->30058|51194->30128|51223->30129|51256->30133|51286->30134|51322->30142|51389->30180|51419->30181|51456->30190|51515->30220|51545->30221|51582->30230|51611->30231|51650->30241|51680->30242|51713->30246|51743->30247|51778->30254|51856->30303|51886->30304|51919->30309|51948->30310|51981->30315|52010->30316|52043->30321|52072->30322|52104->30326|52133->30327|52165->30331|52412->30550|52441->30551|52474->30556|52503->30557|52535->30561|52590->30587|52620->30588|52651->30591|52736->30648|52765->30649|52797->30653|52858->30685|52888->30686|52919->30689|53175->30917|53204->30918|53235->30921
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|38->7|39->8|39->8|41->10|53->22|53->22|54->23|60->29|62->31|77->46|77->46|78->47|78->47|90->59|90->59|90->59|90->59|103->72|103->72|103->72|103->72|158->127|158->127|159->128|159->128|177->146|177->146|177->146|185->154|185->154|186->155|187->156|188->157|188->157|189->158|190->159|191->160|191->160|192->161|193->162|194->163|194->163|195->164|197->166|198->167|198->167|199->168|200->169|201->170|201->170|202->171|204->173|205->174|205->174|206->175|207->176|208->177|208->177|209->178|210->179|211->180|211->180|212->181|213->182|217->186|220->189|220->189|220->189|221->190|222->191|222->191|222->191|223->192|223->192|223->192|225->194|225->194|225->194|226->195|226->195|226->195|227->196|229->198|229->198|229->198|230->199|230->199|230->199|231->200|233->202|233->202|233->202|234->203|234->203|234->203|235->204|236->205|236->205|236->205|237->206|237->206|237->206|238->207|238->207|238->207|239->208|239->208|239->208|240->209|240->209|240->209|241->210|241->210|241->210|242->211|242->211|242->211|243->212|243->212|243->212|244->213|244->213|244->213|246->215|246->215|247->216|247->216|247->216|247->216|250->219|250->219|251->220|252->221|253->222|255->224|255->224|256->225|256->225|256->225|256->225|259->228|259->228|260->229|261->230|262->231|264->233|264->233|265->234|265->234|265->234|265->234|268->237|268->237|269->238|270->239|271->240|273->242|273->242|274->243|274->243|274->243|274->243|277->246|277->246|278->247|279->248|280->249|282->251|282->251|283->252|284->253|284->253|285->254|285->254|285->254|285->254|286->255|286->255|287->256|288->257|288->257|289->258|290->259|291->260|292->261|292->261|293->262|294->263|295->264|296->265|298->267|298->267|299->268|300->269|300->269|301->270|301->270|302->271|302->271|302->271|302->271|305->274|305->274|306->275|307->276|308->277|308->277|309->278|310->279|311->280|312->281|313->282|313->282|314->283|315->284|315->284|316->285|316->285|317->286|317->286|318->287|318->287|319->288|319->288|319->288|319->288|322->291|322->291|323->292|323->292|323->292|323->292|326->295|327->296|328->297|328->297|329->298|330->299|331->300|331->300|332->301|333->302|334->303|335->304|336->305|336->305|337->306|338->307|338->307|339->308|339->308|340->309|340->309|341->310|341->310|342->311|342->311|342->311|342->311|345->314|345->314|346->315|346->315|346->315|346->315|349->318|350->319|351->320|351->320|352->321|353->322|354->323|354->323|355->324|356->325|357->326|359->328|359->328|360->329|360->329|360->329|361->330|362->331|363->332|364->333|364->333|365->334|366->335|366->335|367->336|367->336|368->337|368->337|369->338|369->338|369->338|369->338|372->341|372->341|373->342|373->342|373->342|373->342|374->343|374->343|374->343|376->345|377->346|377->346|378->347|379->348|380->349|380->349|381->350|382->351|383->352|385->354|388->357|388->357|389->358|390->359|390->359|391->360|391->360|392->361|392->361|392->361|392->361|395->364|395->364|396->365|396->365|396->365|396->365|399->368|401->370|401->370|402->371|403->372|404->373|406->375|406->375|407->376|407->376|408->377|408->377|408->377|408->377|408->377|408->377|408->377|409->378|409->378|409->378|411->380|411->380|412->381|413->382|414->383|414->383|415->384|416->385|417->386|418->387|421->390|421->390|422->391|423->392|423->392|423->392|427->396|429->398|429->398|430->399|431->400|431->400|432->401|432->401|433->402|433->402|434->403|434->403|435->404|435->404|435->404|435->404|438->407|438->407|439->408|439->408|439->408|439->408|442->411|443->412|444->413|444->413|445->414|446->415|447->416|447->416|448->417|449->418|450->419|452->421|452->421|453->422|453->422|453->422|454->423|455->424|456->425|460->429|460->429|461->430|462->431|462->431|463->432|463->432|463->432|463->432|466->435|466->435|467->436|467->436|468->437|468->437|468->437|468->437|469->438|469->438|469->438|470->439|470->439|470->439|473->442|473->442|474->443|475->444|476->445|477->446|478->447|480->449|480->449|481->450|482->451|482->451|483->452|483->452|483->452|483->452|486->455|486->455|487->456|487->456|487->456|488->457|489->458|490->459|491->460|492->461|493->462|521->490|521->490|522->491|522->491|527->496|527->496|528->497|528->497|533->502|533->502|534->503|534->503|539->508|539->508|540->509|540->509|545->514|545->514|546->515|546->515|551->520|551->520|552->521|552->521|559->528|559->528|560->529|560->529|565->534|565->534|566->535|566->535|571->540|571->540|572->541|572->541|581->550|581->550|582->551|582->551|587->556|587->556|588->557|588->557|652->621|655->624|657->626|657->626|658->627|662->631|662->631|663->632|664->633|664->633|665->634|667->636|667->636|668->637|668->637|669->638|670->639|670->639|671->640|672->641|672->641|673->642|675->644|675->644|676->645|676->645|679->648|680->649|680->649|682->651|682->651|682->651|684->653|684->653|684->653|685->654|690->659|690->659|691->660|692->661|692->661|693->662|693->662|693->662|696->665|696->665|699->668|699->668|700->669|702->671|702->671|704->673|704->673|704->673|705->674|707->676|707->676|709->678|709->678|709->678|710->679|712->681|712->681|714->683|714->683|714->683|715->684|716->685|716->685|717->686|718->687|718->687|718->687|718->687|719->688|719->688|719->688|719->688|719->688|719->688|720->689|720->689|720->689|721->690|722->691|722->691|722->691|722->691|723->692|724->693|724->693|725->694|725->694|726->695|726->695|728->697|728->697|730->699|730->699|730->699|731->700|733->702|733->702|735->704|735->704|735->704|736->705|738->707|738->707|740->709|740->709|740->709|741->710|743->712|743->712|745->714|745->714|745->714|746->715|747->716|747->716|749->718|749->718|749->718|750->719|752->721|752->721|754->723|754->723|754->723|755->724|757->726|757->726|759->728|759->728|759->728|760->729|760->729|760->729|760->729|760->729|760->729|761->730|761->730|761->730|762->731|764->733|764->733|765->734|765->734|766->735|766->735|768->737|768->737|768->737|769->738|771->740|771->740|774->743|775->744|775->744|776->745|777->746|777->746|778->747|779->748|779->748|781->750|782->751|782->751|783->752|784->753|784->753|786->755|787->756|787->756|788->757|788->757|788->757|788->757|788->757|788->757|789->758|789->758|790->759|790->759|790->759|791->760|794->763|794->763|795->764|803->772|803->772|804->773|804->773|804->773|805->774|805->774|805->774|806->775|808->777|808->777|808->777|808->777|809->778|809->778|809->778|810->779|811->780|811->780|812->781|812->781|813->782|813->782|813->782|813->782|814->783|815->784|815->784|816->785|816->785|817->786|817->786|818->787|818->787|818->787|818->787|819->788|823->792|823->792|825->794|825->794|827->796|827->796|827->796|828->797|830->799|830->799|832->801|832->801|832->801|833->802|836->805|836->805|838->807
                  -- GENERATED --
              */
          