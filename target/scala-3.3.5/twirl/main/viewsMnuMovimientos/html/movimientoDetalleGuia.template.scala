
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

object movimientoDetalleGuia extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template9[Map[String,String],Map[String,String],utilities.UserMnu,tables.Guia,List[List[String]],tables.BodegaEmpresa,List[tables.TipoEstado],List[tables.TipoReparacion],String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
guia: tables.Guia, detalleMovimiento: List[List[String]], bodegaOrigen: tables.BodegaEmpresa, listTipoEstado: List[tables.TipoEstado], listTipoReparacion: List[tables.TipoReparacion],
nickCliente: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.1*/("""    			
    	
"""),_display_(/*6.2*/main("")/*6.10*/ {_display_(Seq[Any](format.raw/*6.12*/("""

	"""),_display_(/*8.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*8.51*/("""
	
	"""),_display_(/*10.3*/modalEquipoDescripcion()),format.raw/*10.27*/("""
	"""),_display_(/*11.3*/modalVerCotizacion()),format.raw/*11.23*/("""
	
	"""),format.raw/*13.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*14.4*/barraTitulo(mapDiccionario, "DETALLE DE MOVIMIENTO SIN IMAGEN","")),format.raw/*14.70*/("""
		"""),format.raw/*15.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<table class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<tr>
							<td width="25%">
								<div class="input-group">
							  		<div class="input-group-prepend">
							    		<span class="input-group-text" id="basic-addon1">Origen</span>
							  		</div>
  									<input type="text" class="form-control" 
  										value=""""),_display_(/*26.21*/guia/*26.25*/.getBodegaOrigen().toUpperCase()),format.raw/*26.57*/("""" 
  										readonly>
								</div>
							</td>
							<td width="25%">
								<div class="input-group">
							  		<div class="input-group-prepend">
							    		<span class="input-group-text" id="basic-addon1">Destino</span>
							  		</div>
										<input type="text" class="form-control left"
											value=""""),_display_(/*36.20*/guia/*36.24*/.getBodegaDestino().toUpperCase()),format.raw/*36.57*/("""" 
											readonly>
								</div>
							</td>
							<td>
								<div class="input-group">
							  		<div class="input-group-prepend">
							    		<span class="input-group-text" id="basic-addon1">Nro.Mov</span>
							  		</div>
							  		<input type="text" class="form-control left"
											value = """"),_display_(/*46.22*/guia/*46.26*/.getNumero()),format.raw/*46.38*/(""""
											readonly>
								</div>
							</td>
							<td>
								<div class="input-group">
							  		<div class="input-group-prepend">
							    		<span class="input-group-text" id="basic-addon1">Nro.Ref</span>
							  		</div>
									"""),_display_(if(mapDiccionario.get("nEmpresa").equals("GFS"))/*55.59*/{_display_(Seq[Any](format.raw/*55.60*/("""
										"""),format.raw/*56.11*/("""<input type="text" class="form-control left"
													value=""""),_display_(/*57.22*/guia/*57.26*/.getNumGuiaCliente()),format.raw/*57.46*/(""""
													onchange="cambiarNroRef('"""),_display_(/*58.40*/guia/*58.44*/.getId()),format.raw/*58.52*/("""', value)">
									""")))}else/*59.15*/{_display_(Seq[Any](format.raw/*59.16*/("""
										"""),format.raw/*60.11*/("""<input type="text" class="form-control left"
													value=""""),_display_(/*61.22*/guia/*61.26*/.getNumGuiaCliente()),format.raw/*61.46*/(""""
													readonly>
									""")))}),format.raw/*63.11*/("""
								"""),format.raw/*64.9*/("""</div>
							</td>
							<td style="width: 1px; min-width: 1px; max-width: 1px; text-align: center; vertical-align: middle">
							<td style="width: 50px; min-width: 50px; max-width: 50px; text-align: center; vertical-align: middle">
								"""),_display_(if(guia.getDocAnexo().equals("0"))/*68.44*/{_display_(Seq[Any](format.raw/*68.45*/("""
									"""),format.raw/*69.10*/("""--
								""")))}else/*70.14*/{_display_(Seq[Any](format.raw/*70.15*/("""
									"""),format.raw/*71.10*/("""<a href="#" onclick="document.getElementById('formDescargaDocumento').submit();">
										<kbd style="background-color: #7F8C8D">DOC</kbd>
									</a>
								""")))}),format.raw/*74.10*/("""
							"""),format.raw/*75.8*/("""</td>
						</tr>
						
						
						
						<tr>
							<td align="center">
								<div class="input-group">
							  		<div class="input-group-prepend">
							    		<span class="input-group-text" id="basic-addon1">Sucursal</span>
							  		</div>
										<input type="text" class="form-control left"
											value = """"),_display_(/*87.22*/guia/*87.26*/.getNameSucursalOrigen()),format.raw/*87.50*/(""""
											readonly>
								</div>
							</td>
							<td align="center">
								<div class="input-group">
							  		<div class="input-group-prepend">
							    		<span class="input-group-text" id="basic-addon1">Sucursal</span>
							  		</div>
										<input type="text" class="form-control left"
											value = """"),_display_(/*97.22*/guia/*97.26*/.getNameSucursalDestino()),format.raw/*97.51*/(""""
											readonly>
								</div>
							</td>

							<td width="230px">
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text" id="basic-addon1">Fecha Movimiento</span>
									</div>
									<input type="date" class="form-control center"
									value=""""),_display_(/*108.18*/utilities/*108.27*/.Fechas.AAMMDD(guia.getFecha())),format.raw/*108.58*/(""""
									readonly>
								</div>
							</td>
							<td width="230px">
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text" id="basic-addon1">Fecha Ini/Ter</span>
									</div>
									<input type="date" class="form-control center"
									value=""""),_display_(/*118.18*/utilities/*118.27*/.Fechas.AAMMDD(guia.getFechaIniTerGuia())),format.raw/*118.68*/(""""
									readonly>
								</div>
							</td>
							<td style="width: 1px; min-width: 1px; max-width: 1px; text-align: center; vertical-align: middle">
							<td style="width: 50px; min-width: 50px; max-width: 50px; text-align: center; vertical-align: middle"></td>
						</tr>
						<tr>
							<td align="center">
								<div class="input-group">
							  		<div class="input-group-prepend">
							    		<span class="input-group-text" id="basic-addon1">Comercial</span>
							  		</div>
										<input type="text" class="form-control left"
											value = """"),_display_(/*132.22*/guia/*132.26*/.getNameComercial()),format.raw/*132.45*/(""""
											readonly>
								</div>
							</td>
							<td align="center">
								<div class="input-group">
							  		<div class="input-group-prepend">
							    		<span class="input-group-text" id="basic-addon1">Cliente</span>
							  		</div>
										<input type="text" class="form-control left"
											value = """"),_display_(/*142.22*/nickCliente),format.raw/*142.33*/(""""
											readonly>
								</div>
							</td>
							<td colspan="2">
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text" id="basic-addon1">Observaciones</span>
									</div>
									<textarea class="form-control" rows="2" onchange="$('#seModifico').val(1);"
									name="observaciones"
									autocomplete="off">"""),_display_(/*153.30*/guia/*153.34*/.getObservaciones()),format.raw/*153.53*/("""</textarea>
								</div>
							</td>
							<td style="width: 1px; min-width: 1px; max-width: 1px; text-align: center; vertical-align: middle">
							<td  style="width: 50px; min-width: 50px; max-width: 50px; text-align: center; vertical-align: middle">
								"""),_display_(if(guia.getFotos().equals("0"))/*158.41*/{_display_(Seq[Any](format.raw/*158.42*/("""
									"""),format.raw/*159.10*/("""<div id="cargadasFotos">--</div>
								""")))}else/*160.14*/{_display_(Seq[Any](format.raw/*160.15*/("""
									"""),format.raw/*161.10*/("""<a href="/muestraAlbumFotos/"""),_display_(/*161.39*/guia/*161.43*/.getFotos()),format.raw/*161.54*/("""">
										<kbd style="background-color: #7F8C8D">Ver Album</kbd>
									</a>
								""")))}),format.raw/*164.10*/("""
							"""),format.raw/*165.8*/("""</td>
						</tr>
					</thead>
				</table>
				<hr>
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>GRUPO</TH>
							<TH>Nro.COTI</TH>
							<TH>CODIGO</TH>
							<TH>EQUIPO</TH>
							<TH>KG</TH>
					        <TH>M2</TH>
					        <TH>UN</TH>
							<TH>SALIDA</TH>
							<TH>VTA</TH>
							<TH>EXCESO</TH>
							<TH>MON</TH>
							<TH>P.Repos<br>Unitario</TH>
							<TH>P.Repos<br>Total</TH>
							<TH>TOT.KG</TH>
							<TH>TOT.M2</TH>
							"""),_display_(if(bodegaOrigen.esInterna!=1)/*188.38*/{_display_(Seq[Any](format.raw/*188.39*/("""
								"""),_display_(/*189.10*/for(titEstado <- listTipoEstado) yield /*189.42*/{_display_(Seq[Any](format.raw/*189.43*/("""
									"""),format.raw/*190.10*/("""<TH style="font-size:10px; vertical-align:middle">
										"""),_display_(/*191.12*/titEstado/*191.21*/.getSigla()),format.raw/*191.32*/("""
									"""),format.raw/*192.10*/("""</TH>
								""")))}),format.raw/*193.10*/("""
								"""),_display_(if(mapPermiso.get("parametro.movimiento-devolucion-cantCliente")!=null && mapPermiso.get("parametro.movimiento-devolucion-cantCliente").equals("1"))/*194.158*/{_display_(Seq[Any](format.raw/*194.159*/("""
									"""),format.raw/*195.10*/("""<TH style="text-align:center; vertical-align:top">cliente</TH>
									<TH style="text-align:center; vertical-align:top">diferen</TH>
								""")))} else {null} ),format.raw/*197.10*/("""
							""")))} else {null} ),format.raw/*198.9*/("""
						"""),format.raw/*199.7*/("""</TR>
					</thead>
					<tbody>
						"""),_display_(/*202.8*/for(lista1 <- detalleMovimiento) yield /*202.40*/{_display_(Seq[Any](format.raw/*202.41*/("""
							"""),format.raw/*203.8*/("""<TR>
								<td  style="text-align:left;">"""),_display_(/*204.40*/lista1/*204.46*/.get(4)),format.raw/*204.53*/("""</td>
								"""),_display_(if(lista1.get(24).equals("0") || lista1.get(24).equals(""))/*205.69*/{_display_(Seq[Any](format.raw/*205.70*/("""
									"""),format.raw/*206.10*/("""<td style="text-align: center;">--</td>
								""")))}else/*207.14*/{_display_(Seq[Any](format.raw/*207.15*/("""
									"""),format.raw/*208.10*/("""<td style="text-align: center;">"""),_display_(/*208.43*/lista1/*208.49*/.get(24)),format.raw/*208.57*/("""</td>
								""")))}),format.raw/*209.10*/("""
								
								"""),format.raw/*211.9*/("""<td  style="text-align:left;"><a href="#" onclick="buscaEquipo('"""),_display_(/*211.74*/lista1/*211.80*/.get(5)),format.raw/*211.87*/("""');">"""),_display_(/*211.93*/lista1/*211.99*/.get(5)),format.raw/*211.106*/("""</a></td>
								<td  style="text-align:left;"><a href="#" onclick="buscaEquipo('"""),_display_(/*212.74*/lista1/*212.80*/.get(5)),format.raw/*212.87*/("""');">"""),_display_(/*212.93*/lista1/*212.99*/.get(6)),format.raw/*212.106*/("""</a></td>
							
								<td  style="text-align:right;">"""),_display_(/*214.41*/lista1/*214.47*/.get(25)),format.raw/*214.55*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*215.41*/lista1/*215.47*/.get(26)),format.raw/*215.55*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*216.42*/lista1/*216.48*/.get(7)),format.raw/*216.55*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*217.41*/lista1/*217.47*/.get(8)),format.raw/*217.54*/("""</td>
								<td  style="text-align:center;">
									"""),_display_(if(lista1.get(20).equals("1"))/*219.41*/{_display_(Seq[Any](format.raw/*219.42*/("""
										"""),format.raw/*220.11*/("""<input type="checkbox" checked disabled>
									""")))}else/*221.15*/{_display_(Seq[Any](format.raw/*221.16*/("""
										"""),format.raw/*222.11*/("""<input type="checkbox" disabled>
									""")))}),format.raw/*223.11*/("""
								"""),format.raw/*224.9*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*225.42*/lista1/*225.48*/.get(18)),format.raw/*225.56*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*226.42*/lista1/*226.48*/.get(11)),format.raw/*226.56*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*227.41*/lista1/*227.47*/.get(9)),format.raw/*227.54*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*228.41*/lista1/*228.47*/.get(33)),format.raw/*228.55*/("""</td>
								
								<td  style="text-align:right;">"""),_display_(/*230.41*/lista1/*230.47*/.get(27)),format.raw/*230.55*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*231.41*/lista1/*231.47*/.get(28)),format.raw/*231.55*/("""</td>
								
								"""),_display_(if(bodegaOrigen.esInterna!=1)/*233.39*/{_display_(Seq[Any](format.raw/*233.40*/("""
										"""),format.raw/*234.11*/("""<td style="display:none">
											<input type="hidden" value=""""),_display_(/*235.41*/lista1/*235.47*/.get(16)),format.raw/*235.55*/("""" id="estados_"""),_display_(/*235.70*/lista1/*235.76*/.get(2)),format.raw/*235.83*/("""_"""),_display_(/*235.85*/lista1/*235.91*/.get(23)),format.raw/*235.99*/("""">
											<input type="hidden" value=""""),_display_(/*236.41*/lista1/*236.47*/.get(32)),format.raw/*236.55*/("""" id="reparaciones_"""),_display_(/*236.75*/lista1/*236.81*/.get(2)),format.raw/*236.88*/("""_"""),_display_(/*236.90*/lista1/*236.96*/.get(23)),format.raw/*236.104*/("""">
										</td>
									"""),_display_(/*238.11*/for((est,index) <- listTipoEstado.zipWithIndex) yield /*238.58*/{_display_(Seq[Any](format.raw/*238.59*/("""
										"""),format.raw/*239.11*/("""<td style="font-size:10px; vertical-align:middle">
											<input type="text" class="form-control height23px right width60px"
												style="font-size:10px; vertical-align:middle"
												id="estado_"""),_display_(/*242.25*/lista1/*242.31*/.get(2)),format.raw/*242.38*/("""_"""),_display_(/*242.40*/lista1/*242.46*/.get(23)),format.raw/*242.54*/("""_"""),_display_(/*242.56*/index),format.raw/*242.61*/(""""
												value="0.00"
												onfocus="value=value.replace(/,/g,'')" 
												onkeydown="return ingresoInt(window.event)"
												autocomplete="off"
												ondblclick = "if(value>0 && '"""),_display_(/*247.43*/est/*247.46*/.getReparable()),format.raw/*247.61*/("""'=='1') editaReparaciones('"""),_display_(/*247.89*/index),format.raw/*247.94*/("""','"""),_display_(/*247.98*/listTipoEstado/*247.112*/.size()),format.raw/*247.119*/("""','"""),_display_(/*247.123*/lista1/*247.129*/.get(2)),format.raw/*247.136*/("""','"""),_display_(/*247.140*/lista1/*247.146*/.get(23)),format.raw/*247.154*/("""',value,'"""),_display_(/*247.164*/est/*247.167*/.getReparable()),format.raw/*247.182*/("""','"""),_display_(/*247.186*/est/*247.189*/.getId()),format.raw/*247.197*/("""')"
												readonly>

												<script>
													niv = """"),_display_(/*251.22*/lista1/*251.28*/.get(31)),format.raw/*251.36*/("""";
													niv1 = niv.split(";");
													for(let i=0; i<niv1.length && niv1.length>1; i++)"""),format.raw/*253.63*/("""{"""),format.raw/*253.64*/("""
														"""),format.raw/*254.15*/("""let niv2 = niv1[i].split(":");
														if(niv2[0]==""""),_display_(/*255.29*/est/*255.32*/.getId()),format.raw/*255.40*/("""")"""),format.raw/*255.42*/("""{"""),format.raw/*255.43*/("""
															"""),format.raw/*256.16*/("""$("#estado_"""),_display_(/*256.28*/lista1/*256.34*/.get(2)),format.raw/*256.41*/("""_"""),_display_(/*256.43*/lista1/*256.49*/.get(23)),format.raw/*256.57*/("""_"""),_display_(/*256.59*/index),format.raw/*256.64*/("""").val(formatStandar(niv2[1],2));
														"""),format.raw/*257.15*/("""}"""),format.raw/*257.16*/("""
													"""),format.raw/*258.14*/("""}"""),format.raw/*258.15*/("""
												"""),format.raw/*259.13*/("""</script>
												
										</td>
									""")))}),format.raw/*262.11*/("""
									"""),_display_(if(mapPermiso.get("parametro.movimiento-devolucion-cantCliente")!=null && mapPermiso.get("parametro.movimiento-devolucion-cantCliente").equals("1"))/*263.159*/{_display_(Seq[Any](format.raw/*263.160*/("""
										"""),format.raw/*264.11*/("""<td  style="text-align: right; vertical-align: middle;">"""),_display_(/*264.68*/lista1/*264.74*/.get(34)),format.raw/*264.82*/("""</td>
										<td style="text-align: right; vertical-align: middle;">"""),_display_(/*265.67*/lista1/*265.73*/.get(35)),format.raw/*265.81*/("""</td>
									""")))} else {null} ),format.raw/*266.11*/("""
								""")))} else {null} ),format.raw/*267.10*/("""
							"""),format.raw/*268.8*/("""</TR>
			 			""")))}),format.raw/*269.9*/("""
					"""),format.raw/*270.6*/("""</tbody>
				</table>
			</div>
		</div>
		
		<form class="formulario" id="formDescargaDocumento" method="post" action="/downloadPDF/">	
			<input type="hidden" id="descargaDocumento" name="nombreArchivo" value=""""),_display_(/*276.77*/guia/*276.81*/.getDocAnexo()),format.raw/*276.95*/("""">
		</form>
		
		
		
		<div class="noprint" align="left">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/movimientoListarPeriodo/';">
				</div>
			</div>
		</div>
	</div>

	<div id='modalReparaciones'></div>
	
	
	
	
	
				
				
	
""")))}),format.raw/*299.2*/("""


"""),format.raw/*302.1*/("""<style>
	#tablaPrincipal td:nth-child(2) """),format.raw/*303.34*/("""{"""),format.raw/*303.35*/(""" """),format.raw/*303.36*/("""text-align: center; """),format.raw/*303.56*/("""}"""),format.raw/*303.57*/("""
	"""),format.raw/*304.2*/("""#tablaPrincipal td:nth-child(3) """),format.raw/*304.34*/("""{"""),format.raw/*304.35*/(""" """),format.raw/*304.36*/("""text-align: center; """),format.raw/*304.56*/("""}"""),format.raw/*304.57*/("""
	"""),format.raw/*305.2*/("""#tablaPrincipal td:nth-child(5) """),format.raw/*305.34*/("""{"""),format.raw/*305.35*/(""" """),format.raw/*305.36*/("""text-align: right; """),format.raw/*305.55*/("""}"""),format.raw/*305.56*/("""
	"""),format.raw/*306.2*/("""#tablaPrincipal td:nth-child(6) """),format.raw/*306.34*/("""{"""),format.raw/*306.35*/(""" """),format.raw/*306.36*/("""text-align: right; """),format.raw/*306.55*/("""}"""),format.raw/*306.56*/("""
	"""),format.raw/*307.2*/("""#tablaPrincipal td:nth-child(7) """),format.raw/*307.34*/("""{"""),format.raw/*307.35*/(""" """),format.raw/*307.36*/("""text-align: center; """),format.raw/*307.56*/("""}"""),format.raw/*307.57*/("""
	"""),format.raw/*308.2*/("""#tablaPrincipal td:nth-child(8) """),format.raw/*308.34*/("""{"""),format.raw/*308.35*/(""" """),format.raw/*308.36*/("""text-align: right; """),format.raw/*308.55*/("""}"""),format.raw/*308.56*/("""
	"""),format.raw/*309.2*/("""#tablaPrincipal td:nth-child(9) """),format.raw/*309.34*/("""{"""),format.raw/*309.35*/(""" """),format.raw/*309.36*/("""text-align: right; """),format.raw/*309.55*/("""}"""),format.raw/*309.56*/("""
	"""),format.raw/*310.2*/("""#tablaPrincipal td:nth-child(10) """),format.raw/*310.35*/("""{"""),format.raw/*310.36*/(""" """),format.raw/*310.37*/("""text-align: center; """),format.raw/*310.57*/("""}"""),format.raw/*310.58*/("""
	"""),format.raw/*311.2*/("""#tablaPrincipal td:nth-child(11) """),format.raw/*311.35*/("""{"""),format.raw/*311.36*/(""" """),format.raw/*311.37*/("""text-align: center; """),format.raw/*311.57*/("""}"""),format.raw/*311.58*/("""
	"""),format.raw/*312.2*/("""#tablaPrincipal td:nth-child(12) """),format.raw/*312.35*/("""{"""),format.raw/*312.36*/(""" """),format.raw/*312.37*/("""text-align: right; """),format.raw/*312.56*/("""}"""),format.raw/*312.57*/("""
	"""),format.raw/*313.2*/("""#tablaPrincipal td:nth-child(13) """),format.raw/*313.35*/("""{"""),format.raw/*313.36*/(""" """),format.raw/*313.37*/("""text-align: right; """),format.raw/*313.56*/("""}"""),format.raw/*313.57*/("""
	"""),format.raw/*314.2*/("""#tablaPrincipal td:nth-child(14) """),format.raw/*314.35*/("""{"""),format.raw/*314.36*/(""" """),format.raw/*314.37*/("""text-align: right; """),format.raw/*314.56*/("""}"""),format.raw/*314.57*/("""
	"""),_display_(if(bodegaOrigen.esInterna!=1)/*315.32*/{_display_(Seq[Any](format.raw/*315.33*/("""
		"""),_display_(/*316.4*/for((est,index) <- listTipoEstado.zipWithIndex) yield /*316.51*/{_display_(Seq[Any](format.raw/*316.52*/("""
			"""),format.raw/*317.4*/("""#tablaPrincipal td:nth-child("""),_display_(/*317.34*/(index+16)),format.raw/*317.44*/(""") """),format.raw/*317.46*/("""{"""),format.raw/*317.47*/(""" """),format.raw/*317.48*/("""vertical-align: middle; """),format.raw/*317.72*/("""}"""),format.raw/*317.73*/("""
		""")))}),format.raw/*318.4*/("""
		"""),format.raw/*319.3*/("""#tablaPrincipal td:nth-child("""),_display_(/*319.33*/(listTipoEstado.size()+16)),format.raw/*319.59*/(""") """),format.raw/*319.61*/("""{"""),format.raw/*319.62*/(""" """),format.raw/*319.63*/("""text-align: right; vertical-align: middle;"""),format.raw/*319.105*/("""}"""),format.raw/*319.106*/("""
		"""),format.raw/*320.3*/("""#tablaPrincipal td:nth-child("""),_display_(/*320.33*/(listTipoEstado.size()+17)),format.raw/*320.59*/(""") """),format.raw/*320.61*/("""{"""),format.raw/*320.62*/(""" """),format.raw/*320.63*/("""text-align: right; vertical-align: middle;"""),format.raw/*320.105*/("""}"""),format.raw/*320.106*/("""
		"""),format.raw/*321.3*/("""#tablaPrincipal td:nth-child("""),_display_(/*321.33*/(listTipoEstado.size()+18)),format.raw/*321.59*/(""") """),format.raw/*321.61*/("""{"""),format.raw/*321.62*/(""" """),format.raw/*321.63*/("""text-align: right; vertical-align: middle;"""),format.raw/*321.105*/("""}"""),format.raw/*321.106*/("""
	""")))} else {null} ),format.raw/*322.3*/("""
"""),format.raw/*323.1*/("""</style>

<script type="text/javascript">
	let tblPrincipal;
	let listReparaNiv1;
	$(document).ready(function() """),format.raw/*328.31*/("""{"""),format.raw/*328.32*/("""
		  """),format.raw/*329.5*/("""let repAux = ""
		  """),_display_(/*330.6*/for(r <- listTipoReparacion) yield /*330.34*/{_display_(Seq[Any](format.raw/*330.35*/("""
				"""),format.raw/*331.5*/("""repAux += """"),_display_(/*331.17*/r/*331.18*/.getId()),format.raw/*331.26*/(""":&&:"""),_display_(/*331.31*/r/*331.32*/.id_tipoEstado),format.raw/*331.46*/(""":&&:"""),_display_(/*331.51*/r/*331.52*/.getSigla()),format.raw/*331.63*/(""":&&:"""),_display_(/*331.68*/r/*331.69*/.getNombre()),format.raw/*331.81*/(""":&&:"""),_display_(/*331.86*/r/*331.87*/.getMoneda()),format.raw/*331.99*/(""":&&:"""),_display_(/*331.104*/r/*331.105*/.getPrecio()),format.raw/*331.117*/(""";&&;"
		  """)))}),format.raw/*332.6*/("""
		  """),format.raw/*333.5*/("""listReparaNiv1 = repAux.split(";&&;");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*335.2*/("""}"""),format.raw/*335.3*/(""");
	
	
	
	const editaReparaciones = (index, largo, id_equipo, id_cotizacion, cantEstado, esReparable, id_tipoEstado) =>"""),format.raw/*339.111*/("""{"""),format.raw/*339.112*/("""
		"""),format.raw/*340.3*/("""let modalReparaciones="<div id='reparaciones' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>"+
			"<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'><div class='modal-content'><div class='modal-header'>"+
			"<h5 class='modal-title'>SELECCIONAR REPARACIONES</h5><button type='button' class='close' data-dismiss='modal' aria-label='Close'>"+
	        "<span aria-hidden='true'>&times;</span></button></div><div class='modal-body'>"+
			"<table id='tablaListaReparaciones' class='table table-sm table-bordered table-condensed table-hover table-fluid'>"+
			"<thead style='background-color: #eeeeee'><TR><TH>SIGLA</TH><TH>REPARACION</TH><TH>MONEDA</TH><TH>PRECIO</TH><TH>CANTIDAD</TH></TR></thead>"+
			"<tbody>";
			let repar = $("#reparaciones_"+id_equipo+"_"+id_cotizacion).val();
			let reparNiv1 = repar.split(";");
			for(let j=0; j<reparNiv1.length; j++)"""),format.raw/*349.41*/("""{"""),format.raw/*349.42*/("""
				"""),format.raw/*350.5*/("""let reparNiv2 = reparNiv1[j].split(":");
				if(reparNiv2.length>0 && reparNiv2[0]==id_tipoEstado)"""),format.raw/*351.58*/("""{"""),format.raw/*351.59*/("""
					"""),format.raw/*352.6*/("""for(i=0;i<listReparaNiv1.length;i++)"""),format.raw/*352.42*/("""{"""),format.raw/*352.43*/("""
				    	"""),format.raw/*353.10*/("""let listReparaNiv2 = listReparaNiv1[i].split(":&&:");
				    	if(listReparaNiv2[1]==id_tipoEstado)"""),format.raw/*354.46*/("""{"""),format.raw/*354.47*/("""
				    		"""),format.raw/*355.11*/("""//  0=getId()   1=id_tipoEstado   2=getSigla() 3=getNombre()   4=getMoneda()   5=getPrecio()
				    		if(listReparaNiv2[0]==reparNiv2[1])"""),format.raw/*356.46*/("""{"""),format.raw/*356.47*/("""
				    			"""),format.raw/*357.12*/("""modalReparaciones += "<tr>"+
				    				"<td style='text-align:left;'>"+listReparaNiv2[2]+"</td>"+
				    				"<td style='text-align:left;'>"+listReparaNiv2[3]+"</td>"+
				    				"<td style='text-align:center;'>"+listReparaNiv2[4]+"</td>"+
				    				"<td style='text-align:right;'>"+listReparaNiv2[5]+"</td>"+
				    				"<td class='width80px' style='text-align:center;'>"+
					    				"<input type='text' class='form-control right width80px' "+
										" value='"+reparNiv2[2]+"' "+ 
										" onfocus=\"value=value.replace(/,/g,'')\"  "+
										" onkeydown='return ingresoInt(window.event)' "+
										" autocomplete='off' "+
										" readonly>"+
				    				"</td></tr>";
				    		"""),format.raw/*370.11*/("""}"""),format.raw/*370.12*/("""
				    	"""),format.raw/*371.10*/("""}"""),format.raw/*371.11*/("""
				    """),format.raw/*372.9*/("""}"""),format.raw/*372.10*/("""
				"""),format.raw/*373.5*/("""}"""),format.raw/*373.6*/("""
			"""),format.raw/*374.4*/("""}"""),format.raw/*374.5*/("""
			"""),format.raw/*375.4*/("""modalReparaciones += "<TR><td  colspan='6'><div class='row justify-content-md-left'><div class='col-xs-12 col-sm-6 col-md-6 col-lg-6'>"+
				"</div></div></TR></tbody></table><div class='row'><div class='col-sm-12' style='text-align:center'>"+
				"<button type='button' class='btn btn-sm  btn-warning' style='font-size: 10px;' data-dismiss='modal'>Listo</button></div></div></div></div></div></div>";
		    document.getElementById("modalReparaciones").innerHTML = modalReparaciones;
		    $("#reparaciones").modal("show");
	"""),format.raw/*380.2*/("""}"""),format.raw/*380.3*/("""

	"""),format.raw/*382.2*/("""const cambiarNroRef = (id_guia, nroRef) => """),format.raw/*382.45*/("""{"""),format.raw/*382.46*/("""
		"""),format.raw/*383.3*/("""let formData = new FormData();
		formData.append("id_guia",id_guia);
		formData.append("nroRef",nroRef);
		$.ajax("""),format.raw/*386.10*/("""{"""),format.raw/*386.11*/("""
		    """),format.raw/*387.7*/("""url: "/guiaCambiarNroRef/",
		    type: "POST",
		    method: "POST",
		    data: formData,
		    cache: false,
		    contentType: false,
		 	processData: false,
		 	success: function(rs)"""),format.raw/*394.26*/("""{"""),format.raw/*394.27*/("""
			"""),format.raw/*395.4*/("""}"""),format.raw/*395.5*/("""
		"""),format.raw/*396.3*/("""}"""),format.raw/*396.4*/(""");
	"""),format.raw/*397.2*/("""}"""),format.raw/*397.3*/("""
	
	
	
	


	
"""),format.raw/*405.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,guia:tables.Guia,detalleMovimiento:List[List[String]],bodegaOrigen:tables.BodegaEmpresa,listTipoEstado:List[tables.TipoEstado],listTipoReparacion:List[tables.TipoReparacion],nickCliente:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,guia,detalleMovimiento,bodegaOrigen,listTipoEstado,listTipoReparacion,nickCliente)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.Guia,List[List[String]],tables.BodegaEmpresa,List[tables.TipoEstado],List[tables.TipoReparacion],String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,guia,detalleMovimiento,bodegaOrigen,listTipoEstado,listTipoReparacion,nickCliente) => apply(mapDiccionario,mapPermiso,userMnu,guia,detalleMovimiento,bodegaOrigen,listTipoEstado,listTipoReparacion,nickCliente)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuMovimientos/movimientoDetalleGuia.scala.html
                  HASH: fc957e0a683275db18aad41616e190328a550f8b
                  MATRIX: 1135->1|1530->303|1570->318|1586->326|1625->328|1654->332|1722->380|1753->385|1798->409|1827->412|1868->432|1899->436|1976->487|2063->553|2093->556|2639->1075|2652->1079|2705->1111|3057->1436|3070->1440|3124->1473|3466->1788|3479->1792|3512->1804|3834->2099|3873->2100|3912->2111|4005->2177|4018->2181|4059->2201|4127->2242|4140->2246|4169->2254|4214->2280|4253->2281|4292->2292|4385->2358|4398->2362|4439->2382|4505->2417|4541->2426|4848->2706|4887->2707|4925->2717|4960->2733|4999->2734|5037->2744|5232->2908|5267->2916|5622->3244|5635->3248|5680->3272|6036->3601|6049->3605|6095->3630|6450->3957|6469->3966|6522->3997|6871->4318|6890->4327|6953->4368|7554->4941|7568->4945|7609->4964|7965->5292|7998->5303|8418->5695|8432->5699|8473->5718|8799->6016|8839->6017|8878->6027|8944->6073|8984->6074|9023->6084|9080->6113|9094->6117|9127->6128|9250->6219|9286->6227|9936->6849|9976->6850|10014->6860|10063->6892|10103->6893|10142->6903|10232->6965|10251->6974|10284->6985|10323->6995|10370->7010|10557->7168|10598->7169|10637->7179|10826->7323|10879->7332|10914->7339|10981->7379|11030->7411|11070->7412|11106->7420|11178->7464|11194->7470|11223->7477|11325->7551|11365->7552|11404->7562|11477->7615|11517->7616|11556->7626|11617->7659|11633->7665|11663->7673|11710->7688|11756->7706|11849->7771|11865->7777|11894->7784|11928->7790|11944->7796|11974->7803|12085->7886|12101->7892|12130->7899|12164->7905|12180->7911|12210->7918|12296->7976|12312->7982|12342->7990|12416->8036|12432->8042|12462->8050|12537->8097|12553->8103|12582->8110|12656->8156|12672->8162|12701->8169|12816->8256|12856->8257|12896->8268|12971->8323|13011->8324|13051->8335|13126->8378|13163->8387|13238->8434|13254->8440|13284->8448|13359->8495|13375->8501|13405->8509|13479->8555|13495->8561|13524->8568|13598->8614|13614->8620|13644->8628|13727->8683|13743->8689|13773->8697|13847->8743|13863->8749|13893->8757|13974->8810|14014->8811|14054->8822|14148->8888|14164->8894|14194->8902|14237->8917|14253->8923|14282->8930|14312->8932|14328->8938|14358->8946|14429->8989|14445->8995|14475->9003|14523->9023|14539->9029|14568->9036|14598->9038|14614->9044|14645->9052|14702->9081|14766->9128|14806->9129|14846->9140|15085->9351|15101->9357|15130->9364|15160->9366|15176->9372|15206->9380|15236->9382|15263->9387|15499->9595|15512->9598|15549->9613|15605->9641|15632->9646|15664->9650|15689->9664|15719->9671|15752->9675|15769->9681|15799->9688|15832->9692|15849->9698|15880->9706|15919->9716|15933->9719|15971->9734|16004->9738|16018->9741|16049->9749|16146->9818|16162->9824|16192->9832|16322->9933|16352->9934|16396->9949|16483->10008|16496->10011|16526->10019|16557->10021|16587->10022|16632->10038|16672->10050|16688->10056|16717->10063|16747->10065|16763->10071|16793->10079|16823->10081|16850->10086|16927->10134|16957->10135|17000->10149|17030->10150|17072->10163|17153->10212|17341->10371|17382->10372|17422->10383|17507->10440|17523->10446|17553->10454|17653->10526|17669->10532|17699->10540|17760->10556|17815->10566|17851->10574|17896->10588|17930->10594|18171->10807|18185->10811|18221->10825|18660->11233|18691->11236|18761->11277|18791->11278|18821->11279|18870->11299|18900->11300|18930->11302|18991->11334|19021->11335|19051->11336|19100->11356|19130->11357|19160->11359|19221->11391|19251->11392|19281->11393|19329->11412|19359->11413|19389->11415|19450->11447|19480->11448|19510->11449|19558->11468|19588->11469|19618->11471|19679->11503|19709->11504|19739->11505|19788->11525|19818->11526|19848->11528|19909->11560|19939->11561|19969->11562|20017->11581|20047->11582|20077->11584|20138->11616|20168->11617|20198->11618|20246->11637|20276->11638|20306->11640|20368->11673|20398->11674|20428->11675|20477->11695|20507->11696|20537->11698|20599->11731|20629->11732|20659->11733|20708->11753|20738->11754|20768->11756|20830->11789|20860->11790|20890->11791|20938->11810|20968->11811|20998->11813|21060->11846|21090->11847|21120->11848|21168->11867|21198->11868|21228->11870|21290->11903|21320->11904|21350->11905|21398->11924|21428->11925|21488->11957|21528->11958|21559->11962|21623->12009|21663->12010|21695->12014|21753->12044|21785->12054|21816->12056|21846->12057|21876->12058|21929->12082|21959->12083|21994->12087|22025->12090|22083->12120|22131->12146|22162->12148|22192->12149|22222->12150|22294->12192|22325->12193|22356->12196|22414->12226|22462->12252|22493->12254|22523->12255|22553->12256|22625->12298|22656->12299|22687->12302|22745->12332|22793->12358|22824->12360|22854->12361|22884->12362|22956->12404|22987->12405|23034->12408|23063->12409|23204->12521|23234->12522|23267->12527|23315->12548|23360->12576|23400->12577|23433->12582|23473->12594|23484->12595|23514->12603|23547->12608|23558->12609|23594->12623|23627->12628|23638->12629|23671->12640|23704->12645|23715->12646|23749->12658|23782->12663|23793->12664|23827->12676|23861->12681|23873->12682|23908->12694|23950->12705|23983->12710|24120->12819|24149->12820|24298->12939|24329->12940|24360->12943|25299->13853|25329->13854|25362->13859|25489->13957|25519->13958|25553->13964|25618->14000|25648->14001|25687->14011|25815->14110|25845->14111|25885->14122|26052->14260|26082->14261|26123->14273|26857->14978|26887->14979|26926->14989|26956->14990|26993->14999|27023->15000|27056->15005|27085->15006|27117->15010|27146->15011|27178->15015|27731->15540|27760->15541|27791->15544|27863->15587|27893->15588|27924->15591|28067->15705|28097->15706|28132->15713|28348->15900|28378->15901|28410->15905|28439->15906|28470->15909|28499->15910|28531->15914|28560->15915|28601->15928
                  LINES: 28->1|35->4|37->6|37->6|37->6|39->8|39->8|41->10|41->10|42->11|42->11|44->13|45->14|45->14|46->15|57->26|57->26|57->26|67->36|67->36|67->36|77->46|77->46|77->46|86->55|86->55|87->56|88->57|88->57|88->57|89->58|89->58|89->58|90->59|90->59|91->60|92->61|92->61|92->61|94->63|95->64|99->68|99->68|100->69|101->70|101->70|102->71|105->74|106->75|118->87|118->87|118->87|128->97|128->97|128->97|139->108|139->108|139->108|149->118|149->118|149->118|163->132|163->132|163->132|173->142|173->142|184->153|184->153|184->153|189->158|189->158|190->159|191->160|191->160|192->161|192->161|192->161|192->161|195->164|196->165|219->188|219->188|220->189|220->189|220->189|221->190|222->191|222->191|222->191|223->192|224->193|225->194|225->194|226->195|228->197|229->198|230->199|233->202|233->202|233->202|234->203|235->204|235->204|235->204|236->205|236->205|237->206|238->207|238->207|239->208|239->208|239->208|239->208|240->209|242->211|242->211|242->211|242->211|242->211|242->211|242->211|243->212|243->212|243->212|243->212|243->212|243->212|245->214|245->214|245->214|246->215|246->215|246->215|247->216|247->216|247->216|248->217|248->217|248->217|250->219|250->219|251->220|252->221|252->221|253->222|254->223|255->224|256->225|256->225|256->225|257->226|257->226|257->226|258->227|258->227|258->227|259->228|259->228|259->228|261->230|261->230|261->230|262->231|262->231|262->231|264->233|264->233|265->234|266->235|266->235|266->235|266->235|266->235|266->235|266->235|266->235|266->235|267->236|267->236|267->236|267->236|267->236|267->236|267->236|267->236|267->236|269->238|269->238|269->238|270->239|273->242|273->242|273->242|273->242|273->242|273->242|273->242|273->242|278->247|278->247|278->247|278->247|278->247|278->247|278->247|278->247|278->247|278->247|278->247|278->247|278->247|278->247|278->247|278->247|278->247|278->247|278->247|278->247|282->251|282->251|282->251|284->253|284->253|285->254|286->255|286->255|286->255|286->255|286->255|287->256|287->256|287->256|287->256|287->256|287->256|287->256|287->256|287->256|288->257|288->257|289->258|289->258|290->259|293->262|294->263|294->263|295->264|295->264|295->264|295->264|296->265|296->265|296->265|297->266|298->267|299->268|300->269|301->270|307->276|307->276|307->276|330->299|333->302|334->303|334->303|334->303|334->303|334->303|335->304|335->304|335->304|335->304|335->304|335->304|336->305|336->305|336->305|336->305|336->305|336->305|337->306|337->306|337->306|337->306|337->306|337->306|338->307|338->307|338->307|338->307|338->307|338->307|339->308|339->308|339->308|339->308|339->308|339->308|340->309|340->309|340->309|340->309|340->309|340->309|341->310|341->310|341->310|341->310|341->310|341->310|342->311|342->311|342->311|342->311|342->311|342->311|343->312|343->312|343->312|343->312|343->312|343->312|344->313|344->313|344->313|344->313|344->313|344->313|345->314|345->314|345->314|345->314|345->314|345->314|346->315|346->315|347->316|347->316|347->316|348->317|348->317|348->317|348->317|348->317|348->317|348->317|348->317|349->318|350->319|350->319|350->319|350->319|350->319|350->319|350->319|350->319|351->320|351->320|351->320|351->320|351->320|351->320|351->320|351->320|352->321|352->321|352->321|352->321|352->321|352->321|352->321|352->321|353->322|354->323|359->328|359->328|360->329|361->330|361->330|361->330|362->331|362->331|362->331|362->331|362->331|362->331|362->331|362->331|362->331|362->331|362->331|362->331|362->331|362->331|362->331|362->331|362->331|362->331|362->331|363->332|364->333|366->335|366->335|370->339|370->339|371->340|380->349|380->349|381->350|382->351|382->351|383->352|383->352|383->352|384->353|385->354|385->354|386->355|387->356|387->356|388->357|401->370|401->370|402->371|402->371|403->372|403->372|404->373|404->373|405->374|405->374|406->375|411->380|411->380|413->382|413->382|413->382|414->383|417->386|417->386|418->387|425->394|425->394|426->395|426->395|427->396|427->396|428->397|428->397|436->405
                  -- GENERATED --
              */
          