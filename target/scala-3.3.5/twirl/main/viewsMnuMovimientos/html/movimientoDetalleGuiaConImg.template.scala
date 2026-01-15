
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

object movimientoDetalleGuiaConImg extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template9[Map[String,String],Map[String,String],utilities.UserMnu,tables.Guia,List[List[String]],tables.BodegaEmpresa,List[tables.TipoEstado],List[tables.TipoReparacion],String,play.twirl.api.HtmlFormat.Appendable] {

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
		"""),_display_(/*14.4*/barraTitulo(mapDiccionario, "DETALLE DE MOVIMIENTO CON IMAGEN","")),format.raw/*14.70*/("""
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
  										value=""""),_display_(/*26.21*/guia/*26.25*/.getBodegaOrigen().toUpperCase),format.raw/*26.55*/("""" 
  										readonly>
								</div>
							</td>
							<td width="25%">
								<div class="input-group">
							  		<div class="input-group-prepend">
							    		<span class="input-group-text" id="basic-addon1">Destino</span>
							  		</div>
										<input type="text" class="form-control left"
											value=""""),_display_(/*36.20*/guia/*36.24*/.getBodegaDestino().toUpperCase),format.raw/*36.55*/("""" 
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
							  		<input type="text" class="form-control left"
							  				value=""""),_display_(/*56.22*/guia/*56.26*/.getNumGuiaCliente()),format.raw/*56.46*/(""""
							  				readonly>
								</div>
							</td>
							<td style="width: 1px; min-width: 1px; max-width: 1px; text-align: center; vertical-align: middle">
							<td style="width: 50px; min-width: 50px; max-width: 50px; text-align: center; vertical-align: middle">
								"""),_display_(if(guia.getDocAnexo().equals("0"))/*62.44*/{_display_(Seq[Any](format.raw/*62.45*/("""
									"""),format.raw/*63.10*/("""--
								""")))}else/*64.14*/{_display_(Seq[Any](format.raw/*64.15*/("""
									"""),format.raw/*65.10*/("""<a href="#" onclick="document.getElementById('formDescargaDocumento').submit();">
										<kbd style="background-color: #7F8C8D">DOC</kbd>
									</a>
								""")))}),format.raw/*68.10*/("""
							"""),format.raw/*69.8*/("""</td>
						</tr>
						<tr>
							<td align="center">
								<div class="input-group">
							  		<div class="input-group-prepend">
							    		<span class="input-group-text" id="basic-addon1">Sucursal</span>
							  		</div>
										<input type="text" class="form-control left"
											value = """"),_display_(/*78.22*/guia/*78.26*/.getNameSucursalOrigen()),format.raw/*78.50*/(""""
											readonly>
								</div>
							</td>
							<td align="center">
								<div class="input-group">
							  		<div class="input-group-prepend">
							    		<span class="input-group-text" id="basic-addon1">Sucursal</span>
							  		</div>
										<input type="text" class="form-control left"
											value = """"),_display_(/*88.22*/guia/*88.26*/.getNameSucursalDestino()),format.raw/*88.51*/(""""
											readonly>
								</div>
							</td>

							<td width="230px">
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text" id="basic-addon1">Fecha Movimiento</span>
									</div>
									<input type="date" class="form-control center"
									value=""""),_display_(/*99.18*/utilities/*99.27*/.Fechas.AAMMDD(guia.getFecha())),format.raw/*99.58*/(""""
									readonly>
								</div>
							</td>
							<td width="230px">
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text" id="basic-addon1">Fecha Ini/Ter</span>
									</div>
									<input type="date" class="form-control center"
									value=""""),_display_(/*109.18*/utilities/*109.27*/.Fechas.AAMMDD(guia.getFechaIniTerGuia())),format.raw/*109.68*/(""""
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
											value = """"),_display_(/*123.22*/guia/*123.26*/.getNameComercial()),format.raw/*123.45*/(""""
											readonly>
								</div>
							</td>
							<td align="center">
								<div class="input-group">
							  		<div class="input-group-prepend">
							    		<span class="input-group-text" id="basic-addon1">Cliente</span>
							  		</div>
										<input type="text" class="form-control left"
											value = """"),_display_(/*133.22*/nickCliente),format.raw/*133.33*/(""""
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
									autocomplete="off">"""),_display_(/*144.30*/guia/*144.34*/.getObservaciones()),format.raw/*144.53*/("""</textarea>
								</div>
							</td>
							<td style="width: 1px; min-width: 1px; max-width: 1px; text-align: center; vertical-align: middle">
							<td  style="width: 50px; min-width: 50px; max-width: 50px; text-align: center; vertical-align: middle">
								"""),_display_(if(guia.getFotos().equals("0"))/*149.41*/{_display_(Seq[Any](format.raw/*149.42*/("""
									"""),format.raw/*150.10*/("""<div id="cargadasFotos">--</div>
								""")))}else/*151.14*/{_display_(Seq[Any](format.raw/*151.15*/("""
									"""),format.raw/*152.10*/("""<a href="/muestraAlbumFotos/"""),_display_(/*152.39*/guia/*152.43*/.getFotos()),format.raw/*152.54*/("""">
										<kbd style="background-color: #7F8C8D">Ver Album</kbd>
									</a>
								""")))}),format.raw/*155.10*/("""
							"""),format.raw/*156.8*/("""</td>
						</tr>
					</thead>
				</table>
				<hr>
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>IMG</TH>
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
							"""),_display_(if(bodegaOrigen.esInterna!=1)/*180.38*/{_display_(Seq[Any](format.raw/*180.39*/("""
								"""),_display_(/*181.10*/for(titEstado <- listTipoEstado) yield /*181.42*/{_display_(Seq[Any](format.raw/*181.43*/("""
									"""),format.raw/*182.10*/("""<TH style="font-size:10px; vertical-align:middle">
										"""),_display_(/*183.12*/titEstado/*183.21*/.getSigla()),format.raw/*183.32*/("""
									"""),format.raw/*184.10*/("""</TH>
								""")))}),format.raw/*185.10*/("""
								"""),_display_(if(mapPermiso.get("parametro.movimiento-devolucion-cantCliente")!=null && mapPermiso.get("parametro.movimiento-devolucion-cantCliente").equals("1"))/*186.158*/{_display_(Seq[Any](format.raw/*186.159*/("""
									"""),format.raw/*187.10*/("""<TH style="text-align:center; vertical-align:top">cliente</TH>
									<TH style="text-align:center; vertical-align:top">fiferen</TH>
								""")))} else {null} ),format.raw/*189.10*/("""
							""")))} else {null} ),format.raw/*190.9*/("""
						"""),format.raw/*191.7*/("""</TR>
					</thead>
					<tbody>
						"""),_display_(/*194.8*/for(lista1 <- detalleMovimiento) yield /*194.40*/{_display_(Seq[Any](format.raw/*194.41*/("""
							"""),format.raw/*195.8*/("""<TR>
								<td  style="text-align:center;">
									"""),_display_(if(!lista1.get(22).equals("0"))/*197.42*/{_display_(Seq[Any](format.raw/*197.43*/("""
											"""),format.raw/*198.12*/("""<img src='/viewImg/"""),_display_(/*198.32*/lista1/*198.38*/.get(22)),format.raw/*198.46*/("""' style="width:auto; max-height:60px">
									""")))} else {null} ),format.raw/*199.11*/("""
								"""),format.raw/*200.9*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*201.40*/lista1/*201.46*/.get(4)),format.raw/*201.53*/("""</td>
								"""),_display_(if(lista1.get(24).equals("0") || lista1.get(24).equals(""))/*202.69*/{_display_(Seq[Any](format.raw/*202.70*/("""
									"""),format.raw/*203.10*/("""<td style="text-align: center;">--</td>
								""")))}else/*204.14*/{_display_(Seq[Any](format.raw/*204.15*/("""
									"""),format.raw/*205.10*/("""<td style="text-align: center;">"""),_display_(/*205.43*/lista1/*205.49*/.get(24)),format.raw/*205.57*/("""</td>
								""")))}),format.raw/*206.10*/("""
								
								"""),format.raw/*208.9*/("""<td  style="text-align:left;"><a href="#" onclick="buscaEquipo('"""),_display_(/*208.74*/lista1/*208.80*/.get(5)),format.raw/*208.87*/("""');">"""),_display_(/*208.93*/lista1/*208.99*/.get(5)),format.raw/*208.106*/("""</a></td>
								<td  style="text-align:left;"><a href="#" onclick="buscaEquipo('"""),_display_(/*209.74*/lista1/*209.80*/.get(5)),format.raw/*209.87*/("""');">"""),_display_(/*209.93*/lista1/*209.99*/.get(6)),format.raw/*209.106*/("""</a></td>
								
								<td  style="text-align:right;">"""),_display_(/*211.41*/lista1/*211.47*/.get(25)),format.raw/*211.55*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*212.41*/lista1/*212.47*/.get(26)),format.raw/*212.55*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*213.42*/lista1/*213.48*/.get(7)),format.raw/*213.55*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*214.41*/lista1/*214.47*/.get(8)),format.raw/*214.54*/("""</td>
								<td  style="text-align:center;">
									"""),_display_(if(lista1.get(20).equals("1"))/*216.41*/{_display_(Seq[Any](format.raw/*216.42*/("""
										"""),format.raw/*217.11*/("""<input type="checkbox" checked disabled>
									""")))}else/*218.15*/{_display_(Seq[Any](format.raw/*218.16*/("""
										"""),format.raw/*219.11*/("""<input type="checkbox" disabled>
									""")))}),format.raw/*220.11*/("""
								"""),format.raw/*221.9*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*222.42*/lista1/*222.48*/.get(18)),format.raw/*222.56*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*223.42*/lista1/*223.48*/.get(11)),format.raw/*223.56*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*224.41*/lista1/*224.47*/.get(9)),format.raw/*224.54*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*225.41*/lista1/*225.47*/.get(33)),format.raw/*225.55*/("""</td>
								
								<td  style="text-align:right;">"""),_display_(/*227.41*/lista1/*227.47*/.get(27)),format.raw/*227.55*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*228.41*/lista1/*228.47*/.get(28)),format.raw/*228.55*/("""</td>
								
								"""),_display_(if(bodegaOrigen.esInterna!=1)/*230.39*/{_display_(Seq[Any](format.raw/*230.40*/("""
										"""),format.raw/*231.11*/("""<td style="display:none">
											<input type="hidden" value=""""),_display_(/*232.41*/lista1/*232.47*/.get(16)),format.raw/*232.55*/("""" id="estados_"""),_display_(/*232.70*/lista1/*232.76*/.get(2)),format.raw/*232.83*/("""_"""),_display_(/*232.85*/lista1/*232.91*/.get(23)),format.raw/*232.99*/("""">
											<input type="hidden" value=""""),_display_(/*233.41*/lista1/*233.47*/.get(32)),format.raw/*233.55*/("""" id="reparaciones_"""),_display_(/*233.75*/lista1/*233.81*/.get(2)),format.raw/*233.88*/("""_"""),_display_(/*233.90*/lista1/*233.96*/.get(23)),format.raw/*233.104*/("""">
										</td>
									"""),_display_(/*235.11*/for((est,index) <- listTipoEstado.zipWithIndex) yield /*235.58*/{_display_(Seq[Any](format.raw/*235.59*/("""
										"""),format.raw/*236.11*/("""<td style="font-size:10px; vertical-align:middle">
											<input type="text" class="form-control height23px right width40px"
												style="font-size:10px; vertical-align:middle"
												id="estado_"""),_display_(/*239.25*/lista1/*239.31*/.get(2)),format.raw/*239.38*/("""_"""),_display_(/*239.40*/lista1/*239.46*/.get(23)),format.raw/*239.54*/("""_"""),_display_(/*239.56*/index),format.raw/*239.61*/(""""
												value="0.00"
												onfocus="value=value.replace(/,/g,'')" 
												onkeydown="return ingresoInt(window.event)"
												autocomplete="off"
												ondblclick = "if(value>0 && '"""),_display_(/*244.43*/est/*244.46*/.getReparable()),format.raw/*244.61*/("""'=='1') editaReparaciones('"""),_display_(/*244.89*/index),format.raw/*244.94*/("""','"""),_display_(/*244.98*/listTipoEstado/*244.112*/.size()),format.raw/*244.119*/("""','"""),_display_(/*244.123*/lista1/*244.129*/.get(2)),format.raw/*244.136*/("""','"""),_display_(/*244.140*/lista1/*244.146*/.get(23)),format.raw/*244.154*/("""',value,'"""),_display_(/*244.164*/est/*244.167*/.getReparable()),format.raw/*244.182*/("""','"""),_display_(/*244.186*/est/*244.189*/.getId()),format.raw/*244.197*/("""')"
												readonly>

												<script>
													niv = """"),_display_(/*248.22*/lista1/*248.28*/.get(31)),format.raw/*248.36*/("""";
													niv1 = niv.split(";");
													for(let i=0; i<niv1.length && niv1.length>1; i++)"""),format.raw/*250.63*/("""{"""),format.raw/*250.64*/("""
														"""),format.raw/*251.15*/("""let niv2 = niv1[i].split(":");
														if(niv2[0]==""""),_display_(/*252.29*/est/*252.32*/.getId()),format.raw/*252.40*/("""")"""),format.raw/*252.42*/("""{"""),format.raw/*252.43*/("""
															"""),format.raw/*253.16*/("""$("#estado_"""),_display_(/*253.28*/lista1/*253.34*/.get(2)),format.raw/*253.41*/("""_"""),_display_(/*253.43*/lista1/*253.49*/.get(23)),format.raw/*253.57*/("""_"""),_display_(/*253.59*/index),format.raw/*253.64*/("""").val(formatStandar(niv2[1],2));
														"""),format.raw/*254.15*/("""}"""),format.raw/*254.16*/("""
													"""),format.raw/*255.14*/("""}"""),format.raw/*255.15*/("""
												"""),format.raw/*256.13*/("""</script>
												
										</td>
									""")))}),format.raw/*259.11*/("""
									"""),_display_(if(mapPermiso.get("parametro.movimiento-devolucion-cantCliente")!=null && mapPermiso.get("parametro.movimiento-devolucion-cantCliente").equals("1"))/*260.159*/{_display_(Seq[Any](format.raw/*260.160*/("""
										"""),format.raw/*261.11*/("""<td  style="text-align: right; vertical-align: middle;">"""),_display_(/*261.68*/lista1/*261.74*/.get(34)),format.raw/*261.82*/("""</td>
										<td style="text-align: right; vertical-align: middle;">"""),_display_(/*262.67*/lista1/*262.73*/.get(35)),format.raw/*262.81*/("""</td>
									""")))} else {null} ),format.raw/*263.11*/("""
								""")))} else {null} ),format.raw/*264.10*/("""
							"""),format.raw/*265.8*/("""</TR>
			 			""")))}),format.raw/*266.9*/("""
					"""),format.raw/*267.6*/("""</tbody>
				</table>
			</div>
		</div>
		
		<form class="formulario" id="formDescargaDocumento" method="post" action="/downloadPDF/">	
			<input type="hidden" id="descargaDocumento" name="nombreArchivo" value=""""),_display_(/*273.77*/guia/*273.81*/.getDocAnexo()),format.raw/*273.95*/("""">
		</form>
		
		
		
		<div class="noprint" align="left">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/movimientoListarImgPeriodo/';">
				</div>
			</div>
		</div>
	</div>

	<div id='modalReparaciones'></div>
	
	
	
	
	
				
				
	
""")))}),format.raw/*296.2*/("""


"""),format.raw/*299.1*/("""<style>
	#tablaPrincipal td:nth-child(2) """),format.raw/*300.34*/("""{"""),format.raw/*300.35*/(""" """),format.raw/*300.36*/("""text-align: center; """),format.raw/*300.56*/("""}"""),format.raw/*300.57*/("""
	"""),format.raw/*301.2*/("""#tablaPrincipal td:nth-child(3) """),format.raw/*301.34*/("""{"""),format.raw/*301.35*/(""" """),format.raw/*301.36*/("""text-align: center; """),format.raw/*301.56*/("""}"""),format.raw/*301.57*/("""
	"""),format.raw/*302.2*/("""#tablaPrincipal td:nth-child(5) """),format.raw/*302.34*/("""{"""),format.raw/*302.35*/(""" """),format.raw/*302.36*/("""text-align: right; """),format.raw/*302.55*/("""}"""),format.raw/*302.56*/("""
	"""),format.raw/*303.2*/("""#tablaPrincipal td:nth-child(6) """),format.raw/*303.34*/("""{"""),format.raw/*303.35*/(""" """),format.raw/*303.36*/("""text-align: right; """),format.raw/*303.55*/("""}"""),format.raw/*303.56*/("""
	"""),format.raw/*304.2*/("""#tablaPrincipal td:nth-child(7) """),format.raw/*304.34*/("""{"""),format.raw/*304.35*/(""" """),format.raw/*304.36*/("""text-align: center; """),format.raw/*304.56*/("""}"""),format.raw/*304.57*/("""
	"""),format.raw/*305.2*/("""#tablaPrincipal td:nth-child(8) """),format.raw/*305.34*/("""{"""),format.raw/*305.35*/(""" """),format.raw/*305.36*/("""text-align: right; """),format.raw/*305.55*/("""}"""),format.raw/*305.56*/("""
	"""),format.raw/*306.2*/("""#tablaPrincipal td:nth-child(9) """),format.raw/*306.34*/("""{"""),format.raw/*306.35*/(""" """),format.raw/*306.36*/("""text-align: right; """),format.raw/*306.55*/("""}"""),format.raw/*306.56*/("""
	"""),format.raw/*307.2*/("""#tablaPrincipal td:nth-child(10) """),format.raw/*307.35*/("""{"""),format.raw/*307.36*/(""" """),format.raw/*307.37*/("""text-align: center; """),format.raw/*307.57*/("""}"""),format.raw/*307.58*/("""
	"""),format.raw/*308.2*/("""#tablaPrincipal td:nth-child(11) """),format.raw/*308.35*/("""{"""),format.raw/*308.36*/(""" """),format.raw/*308.37*/("""text-align: center; """),format.raw/*308.57*/("""}"""),format.raw/*308.58*/("""
	"""),format.raw/*309.2*/("""#tablaPrincipal td:nth-child(12) """),format.raw/*309.35*/("""{"""),format.raw/*309.36*/(""" """),format.raw/*309.37*/("""text-align: right; """),format.raw/*309.56*/("""}"""),format.raw/*309.57*/("""
	"""),format.raw/*310.2*/("""#tablaPrincipal td:nth-child(13) """),format.raw/*310.35*/("""{"""),format.raw/*310.36*/(""" """),format.raw/*310.37*/("""text-align: right; """),format.raw/*310.56*/("""}"""),format.raw/*310.57*/("""
	"""),format.raw/*311.2*/("""#tablaPrincipal td:nth-child(14) """),format.raw/*311.35*/("""{"""),format.raw/*311.36*/(""" """),format.raw/*311.37*/("""text-align: right; """),format.raw/*311.56*/("""}"""),format.raw/*311.57*/("""
	"""),_display_(if(bodegaOrigen.esInterna!=1)/*312.32*/{_display_(Seq[Any](format.raw/*312.33*/("""
		"""),_display_(/*313.4*/for((est,index) <- listTipoEstado.zipWithIndex) yield /*313.51*/{_display_(Seq[Any](format.raw/*313.52*/("""
			"""),format.raw/*314.4*/("""#tablaPrincipal td:nth-child("""),_display_(/*314.34*/(index+16)),format.raw/*314.44*/(""") """),format.raw/*314.46*/("""{"""),format.raw/*314.47*/(""" """),format.raw/*314.48*/("""vertical-align: middle; """),format.raw/*314.72*/("""}"""),format.raw/*314.73*/("""
		""")))}),format.raw/*315.4*/("""
		"""),format.raw/*316.3*/("""#tablaPrincipal td:nth-child("""),_display_(/*316.33*/(listTipoEstado.size()+16)),format.raw/*316.59*/(""") """),format.raw/*316.61*/("""{"""),format.raw/*316.62*/(""" """),format.raw/*316.63*/("""text-align: right; vertical-align: middle;"""),format.raw/*316.105*/("""}"""),format.raw/*316.106*/("""
		"""),format.raw/*317.3*/("""#tablaPrincipal td:nth-child("""),_display_(/*317.33*/(listTipoEstado.size()+17)),format.raw/*317.59*/(""") """),format.raw/*317.61*/("""{"""),format.raw/*317.62*/(""" """),format.raw/*317.63*/("""text-align: right; vertical-align: middle;"""),format.raw/*317.105*/("""}"""),format.raw/*317.106*/("""
		"""),format.raw/*318.3*/("""#tablaPrincipal td:nth-child("""),_display_(/*318.33*/(listTipoEstado.size()+18)),format.raw/*318.59*/(""") """),format.raw/*318.61*/("""{"""),format.raw/*318.62*/(""" """),format.raw/*318.63*/("""text-align: right; vertical-align: middle;"""),format.raw/*318.105*/("""}"""),format.raw/*318.106*/("""
	""")))} else {null} ),format.raw/*319.3*/("""
"""),format.raw/*320.1*/("""</style>

<script type="text/javascript">
	let tblPrincipal;
	let listReparaNiv1;
	$(document).ready(function() """),format.raw/*325.31*/("""{"""),format.raw/*325.32*/("""
		  """),format.raw/*326.5*/("""let repAux = ""
		  """),_display_(/*327.6*/for(r <- listTipoReparacion) yield /*327.34*/{_display_(Seq[Any](format.raw/*327.35*/("""
				"""),format.raw/*328.5*/("""repAux += """"),_display_(/*328.17*/r/*328.18*/.getId()),format.raw/*328.26*/(""":&&:"""),_display_(/*328.31*/r/*328.32*/.id_tipoEstado),format.raw/*328.46*/(""":&&:"""),_display_(/*328.51*/r/*328.52*/.getSigla()),format.raw/*328.63*/(""":&&:"""),_display_(/*328.68*/r/*328.69*/.getNombre()),format.raw/*328.81*/(""":&&:"""),_display_(/*328.86*/r/*328.87*/.getMoneda()),format.raw/*328.99*/(""":&&:"""),_display_(/*328.104*/r/*328.105*/.getPrecio()),format.raw/*328.117*/(""";&&;"
		  """)))}),format.raw/*329.6*/("""
		  """),format.raw/*330.5*/("""listReparaNiv1 = repAux.split(";&&;");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*332.2*/("""}"""),format.raw/*332.3*/(""");
	
	
	
	const editaReparaciones = (index, largo, id_equipo, id_cotizacion, cantEstado, esReparable, id_tipoEstado) =>"""),format.raw/*336.111*/("""{"""),format.raw/*336.112*/("""
		"""),format.raw/*337.3*/("""let modalReparaciones="<div id='reparaciones' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>"+
			"<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'><div class='modal-content'><div class='modal-header'>"+
			"<h5 class='modal-title'>SELECCIONAR REPARACIONES</h5><button type='button' class='close' data-dismiss='modal' aria-label='Close'>"+
	        "<span aria-hidden='true'>&times;</span></button></div><div class='modal-body'>"+
			"<table id='tablaListaReparaciones' class='table table-sm table-bordered table-condensed table-hover table-fluid'>"+
			"<thead style='background-color: #eeeeee'><TR><TH>SIGLA</TH><TH>REPARACION</TH><TH>MONEDA</TH><TH>PRECIO</TH><TH>CANTIDAD</TH></TR></thead>"+
			"<tbody>";
			let repar = $("#reparaciones_"+id_equipo+"_"+id_cotizacion).val();
			let reparNiv1 = repar.split(";");
			for(let j=0; j<reparNiv1.length; j++)"""),format.raw/*346.41*/("""{"""),format.raw/*346.42*/("""
				"""),format.raw/*347.5*/("""let reparNiv2 = reparNiv1[j].split(":");
				if(reparNiv2.length>0 && reparNiv2[0]==id_tipoEstado)"""),format.raw/*348.58*/("""{"""),format.raw/*348.59*/("""
					"""),format.raw/*349.6*/("""for(i=0;i<listReparaNiv1.length;i++)"""),format.raw/*349.42*/("""{"""),format.raw/*349.43*/("""
				    	"""),format.raw/*350.10*/("""let listReparaNiv2 = listReparaNiv1[i].split(":&&:");
				    	if(listReparaNiv2[1]==id_tipoEstado)"""),format.raw/*351.46*/("""{"""),format.raw/*351.47*/("""
				    		"""),format.raw/*352.11*/("""//  0=getId()   1=id_tipoEstado   2=getSigla() 3=getNombre()   4=getMoneda()   5=getPrecio()
				    		if(listReparaNiv2[0]==reparNiv2[1])"""),format.raw/*353.46*/("""{"""),format.raw/*353.47*/("""
				    			"""),format.raw/*354.12*/("""modalReparaciones += "<tr>"+
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
				    		"""),format.raw/*367.11*/("""}"""),format.raw/*367.12*/("""
				    	"""),format.raw/*368.10*/("""}"""),format.raw/*368.11*/("""
				    """),format.raw/*369.9*/("""}"""),format.raw/*369.10*/("""
				"""),format.raw/*370.5*/("""}"""),format.raw/*370.6*/("""
			"""),format.raw/*371.4*/("""}"""),format.raw/*371.5*/("""
			"""),format.raw/*372.4*/("""modalReparaciones += "<TR><td  colspan='6'><div class='row justify-content-md-left'><div class='col-xs-12 col-sm-6 col-md-6 col-lg-6'>"+
				"</div></div></TR></tbody></table><div class='row'><div class='col-sm-12' style='text-align:center'>"+
				"<button type='button' class='btn btn-sm  btn-warning' style='font-size: 10px;' data-dismiss='modal'>Listo</button></div></div></div></div></div></div>";
		    document.getElementById("modalReparaciones").innerHTML = modalReparaciones;
		    $("#reparaciones").modal("show");
	"""),format.raw/*377.2*/("""}"""),format.raw/*377.3*/("""

	
	
	
	
	


	
"""),format.raw/*387.1*/("""</script>


		
		
	
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
                  SOURCE: app/viewsMnuMovimientos/movimientoDetalleGuiaConImg.scala.html
                  HASH: c8c89054ae47b52aee4af791ad4224cfa0351a45
                  MATRIX: 1141->1|1536->303|1576->318|1592->326|1631->328|1660->332|1728->380|1759->385|1804->409|1833->412|1874->432|1905->436|1982->487|2069->553|2099->556|2645->1075|2658->1079|2709->1109|3061->1434|3074->1438|3126->1469|3468->1784|3481->1788|3514->1800|3855->2114|3868->2118|3909->2138|4249->2451|4288->2452|4326->2462|4361->2478|4400->2479|4438->2489|4633->2653|4668->2661|5002->2968|5015->2972|5060->2996|5416->3325|5429->3329|5475->3354|5829->3681|5847->3690|5899->3721|6248->4042|6267->4051|6330->4092|6931->4665|6945->4669|6986->4688|7342->5016|7375->5027|7795->5419|7809->5423|7850->5442|8176->5740|8216->5741|8255->5751|8321->5797|8361->5798|8400->5808|8457->5837|8471->5841|8504->5852|8627->5943|8663->5951|9333->6593|9373->6594|9411->6604|9460->6636|9500->6637|9539->6647|9629->6709|9648->6718|9681->6729|9720->6739|9767->6754|9954->6912|9995->6913|10034->6923|10223->7067|10276->7076|10311->7083|10378->7123|10427->7155|10467->7156|10503->7164|10618->7251|10658->7252|10699->7264|10747->7284|10763->7290|10793->7298|10887->7347|10924->7356|10997->7401|11013->7407|11042->7414|11144->7488|11184->7489|11223->7499|11296->7552|11336->7553|11375->7563|11436->7596|11452->7602|11482->7610|11529->7625|11575->7643|11668->7708|11684->7714|11713->7721|11747->7727|11763->7733|11793->7740|11904->7823|11920->7829|11949->7836|11983->7842|11999->7848|12029->7855|12116->7914|12132->7920|12162->7928|12236->7974|12252->7980|12282->7988|12357->8035|12373->8041|12402->8048|12476->8094|12492->8100|12521->8107|12636->8194|12676->8195|12716->8206|12791->8261|12831->8262|12871->8273|12946->8316|12983->8325|13058->8372|13074->8378|13104->8386|13179->8433|13195->8439|13225->8447|13299->8493|13315->8499|13344->8506|13418->8552|13434->8558|13464->8566|13547->8621|13563->8627|13593->8635|13667->8681|13683->8687|13713->8695|13794->8748|13834->8749|13874->8760|13968->8826|13984->8832|14014->8840|14057->8855|14073->8861|14102->8868|14132->8870|14148->8876|14178->8884|14249->8927|14265->8933|14295->8941|14343->8961|14359->8967|14388->8974|14418->8976|14434->8982|14465->8990|14522->9019|14586->9066|14626->9067|14666->9078|14905->9289|14921->9295|14950->9302|14980->9304|14996->9310|15026->9318|15056->9320|15083->9325|15319->9533|15332->9536|15369->9551|15425->9579|15452->9584|15484->9588|15509->9602|15539->9609|15572->9613|15589->9619|15619->9626|15652->9630|15669->9636|15700->9644|15739->9654|15753->9657|15791->9672|15824->9676|15838->9679|15869->9687|15966->9756|15982->9762|16012->9770|16142->9871|16172->9872|16216->9887|16303->9946|16316->9949|16346->9957|16377->9959|16407->9960|16452->9976|16492->9988|16508->9994|16537->10001|16567->10003|16583->10009|16613->10017|16643->10019|16670->10024|16747->10072|16777->10073|16820->10087|16850->10088|16892->10101|16973->10150|17161->10309|17202->10310|17242->10321|17327->10378|17343->10384|17373->10392|17473->10464|17489->10470|17519->10478|17580->10494|17635->10504|17671->10512|17716->10526|17750->10532|17991->10745|18005->10749|18041->10763|18483->11174|18514->11177|18584->11218|18614->11219|18644->11220|18693->11240|18723->11241|18753->11243|18814->11275|18844->11276|18874->11277|18923->11297|18953->11298|18983->11300|19044->11332|19074->11333|19104->11334|19152->11353|19182->11354|19212->11356|19273->11388|19303->11389|19333->11390|19381->11409|19411->11410|19441->11412|19502->11444|19532->11445|19562->11446|19611->11466|19641->11467|19671->11469|19732->11501|19762->11502|19792->11503|19840->11522|19870->11523|19900->11525|19961->11557|19991->11558|20021->11559|20069->11578|20099->11579|20129->11581|20191->11614|20221->11615|20251->11616|20300->11636|20330->11637|20360->11639|20422->11672|20452->11673|20482->11674|20531->11694|20561->11695|20591->11697|20653->11730|20683->11731|20713->11732|20761->11751|20791->11752|20821->11754|20883->11787|20913->11788|20943->11789|20991->11808|21021->11809|21051->11811|21113->11844|21143->11845|21173->11846|21221->11865|21251->11866|21311->11898|21351->11899|21382->11903|21446->11950|21486->11951|21518->11955|21576->11985|21608->11995|21639->11997|21669->11998|21699->11999|21752->12023|21782->12024|21817->12028|21848->12031|21906->12061|21954->12087|21985->12089|22015->12090|22045->12091|22117->12133|22148->12134|22179->12137|22237->12167|22285->12193|22316->12195|22346->12196|22376->12197|22448->12239|22479->12240|22510->12243|22568->12273|22616->12299|22647->12301|22677->12302|22707->12303|22779->12345|22810->12346|22857->12349|22886->12350|23027->12462|23057->12463|23090->12468|23138->12489|23183->12517|23223->12518|23256->12523|23296->12535|23307->12536|23337->12544|23370->12549|23381->12550|23417->12564|23450->12569|23461->12570|23494->12581|23527->12586|23538->12587|23572->12599|23605->12604|23616->12605|23650->12617|23684->12622|23696->12623|23731->12635|23773->12646|23806->12651|23943->12760|23972->12761|24121->12880|24152->12881|24183->12884|25122->13794|25152->13795|25185->13800|25312->13898|25342->13899|25376->13905|25441->13941|25471->13942|25510->13952|25638->14051|25668->14052|25708->14063|25875->14201|25905->14202|25946->14214|26680->14919|26710->14920|26749->14930|26779->14931|26816->14940|26846->14941|26879->14946|26908->14947|26940->14951|26969->14952|27001->14956|27554->15481|27583->15482|27627->15498
                  LINES: 28->1|35->4|37->6|37->6|37->6|39->8|39->8|41->10|41->10|42->11|42->11|44->13|45->14|45->14|46->15|57->26|57->26|57->26|67->36|67->36|67->36|77->46|77->46|77->46|87->56|87->56|87->56|93->62|93->62|94->63|95->64|95->64|96->65|99->68|100->69|109->78|109->78|109->78|119->88|119->88|119->88|130->99|130->99|130->99|140->109|140->109|140->109|154->123|154->123|154->123|164->133|164->133|175->144|175->144|175->144|180->149|180->149|181->150|182->151|182->151|183->152|183->152|183->152|183->152|186->155|187->156|211->180|211->180|212->181|212->181|212->181|213->182|214->183|214->183|214->183|215->184|216->185|217->186|217->186|218->187|220->189|221->190|222->191|225->194|225->194|225->194|226->195|228->197|228->197|229->198|229->198|229->198|229->198|230->199|231->200|232->201|232->201|232->201|233->202|233->202|234->203|235->204|235->204|236->205|236->205|236->205|236->205|237->206|239->208|239->208|239->208|239->208|239->208|239->208|239->208|240->209|240->209|240->209|240->209|240->209|240->209|242->211|242->211|242->211|243->212|243->212|243->212|244->213|244->213|244->213|245->214|245->214|245->214|247->216|247->216|248->217|249->218|249->218|250->219|251->220|252->221|253->222|253->222|253->222|254->223|254->223|254->223|255->224|255->224|255->224|256->225|256->225|256->225|258->227|258->227|258->227|259->228|259->228|259->228|261->230|261->230|262->231|263->232|263->232|263->232|263->232|263->232|263->232|263->232|263->232|263->232|264->233|264->233|264->233|264->233|264->233|264->233|264->233|264->233|264->233|266->235|266->235|266->235|267->236|270->239|270->239|270->239|270->239|270->239|270->239|270->239|270->239|275->244|275->244|275->244|275->244|275->244|275->244|275->244|275->244|275->244|275->244|275->244|275->244|275->244|275->244|275->244|275->244|275->244|275->244|275->244|275->244|279->248|279->248|279->248|281->250|281->250|282->251|283->252|283->252|283->252|283->252|283->252|284->253|284->253|284->253|284->253|284->253|284->253|284->253|284->253|284->253|285->254|285->254|286->255|286->255|287->256|290->259|291->260|291->260|292->261|292->261|292->261|292->261|293->262|293->262|293->262|294->263|295->264|296->265|297->266|298->267|304->273|304->273|304->273|327->296|330->299|331->300|331->300|331->300|331->300|331->300|332->301|332->301|332->301|332->301|332->301|332->301|333->302|333->302|333->302|333->302|333->302|333->302|334->303|334->303|334->303|334->303|334->303|334->303|335->304|335->304|335->304|335->304|335->304|335->304|336->305|336->305|336->305|336->305|336->305|336->305|337->306|337->306|337->306|337->306|337->306|337->306|338->307|338->307|338->307|338->307|338->307|338->307|339->308|339->308|339->308|339->308|339->308|339->308|340->309|340->309|340->309|340->309|340->309|340->309|341->310|341->310|341->310|341->310|341->310|341->310|342->311|342->311|342->311|342->311|342->311|342->311|343->312|343->312|344->313|344->313|344->313|345->314|345->314|345->314|345->314|345->314|345->314|345->314|345->314|346->315|347->316|347->316|347->316|347->316|347->316|347->316|347->316|347->316|348->317|348->317|348->317|348->317|348->317|348->317|348->317|348->317|349->318|349->318|349->318|349->318|349->318|349->318|349->318|349->318|350->319|351->320|356->325|356->325|357->326|358->327|358->327|358->327|359->328|359->328|359->328|359->328|359->328|359->328|359->328|359->328|359->328|359->328|359->328|359->328|359->328|359->328|359->328|359->328|359->328|359->328|359->328|360->329|361->330|363->332|363->332|367->336|367->336|368->337|377->346|377->346|378->347|379->348|379->348|380->349|380->349|380->349|381->350|382->351|382->351|383->352|384->353|384->353|385->354|398->367|398->367|399->368|399->368|400->369|400->369|401->370|401->370|402->371|402->371|403->372|408->377|408->377|418->387
                  -- GENERATED --
              */
          