
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

object otDetalleDespachoImg extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template10[Map[String,String],Map[String,String],utilities.UserMnu,tables.Guia,List[List[String]],tables.BodegaEmpresa,List[tables.TipoEstado],List[tables.TipoReparacion],tables.Cotizacion,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
guia: tables.Guia, detalleMovimiento: List[List[String]], bodegaOrigen: tables.BodegaEmpresa, listTipoEstado: List[tables.TipoEstado], 
listTipoReparacion: List[tables.TipoReparacion], coti: tables.Cotizacion, nickCliente: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.1*/("""    			
    	
"""),_display_(/*6.2*/main("")/*6.10*/ {_display_(Seq[Any](format.raw/*6.12*/("""

"""),_display_(/*8.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*8.50*/("""
	"""),format.raw/*9.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*10.4*/barraTitulo(mapDiccionario, "DETALLE DE MOVIMIENTO CON IMAGEN","")),format.raw/*10.70*/("""
		"""),format.raw/*11.3*/("""<div class="row  justify-content-md-center">
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
									value=""""),_display_(/*22.18*/guia/*22.22*/.getBodegaOrigen().toUpperCase),format.raw/*22.52*/(""""
									readonly>
								</div>
							</td>
							<td width="25%">
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text" id="basic-addon1">Destino</span>
									</div>
									<input type="text" class="form-control left"
									value=""""),_display_(/*32.18*/guia/*32.22*/.getBodegaDestino().toUpperCase),format.raw/*32.53*/(""""
									readonly>
								</div>
							</td>
							<td>
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text" id="basic-addon1">Nro.Mov</span>
									</div>
									<input type="text" class="form-control left"
									value = """"),_display_(/*42.20*/guia/*42.24*/.getNumero()),format.raw/*42.36*/(""""
									readonly>
								</div>
							</td>
							<td>
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text" id="basic-addon1">Nro.Ref</span>
									</div>
									<input type="text" class="form-control left"
									value=""""),_display_(/*52.18*/guia/*52.22*/.getNumGuiaCliente()),format.raw/*52.42*/(""""
									readonly>
								</div>
							</td>
							<td rowspan="2" style="width: 50px; min-width: 50px; max-width: 50px; text-align: center; vertical-align: middle">
							"""),_display_(if(guia.getDocAnexo().equals("0"))/*57.43*/{_display_(Seq[Any](format.raw/*57.44*/("""
								"""),format.raw/*58.9*/("""--
							""")))}else/*59.13*/{_display_(Seq[Any](format.raw/*59.14*/("""
								"""),format.raw/*60.9*/("""<a href="#" onclick="document.getElementById('formDescargaDocumento').submit();">
									<kbd style="background-color: #7F8C8D">DOC</kbd>
								</a>
							""")))}),format.raw/*63.9*/("""
							"""),format.raw/*64.8*/("""</td>
						</tr>
						<tr>
							<td>
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text" id="basic-addon1">Sucursal</span>
									</div>
									<input type="text" class="form-control left"
									value=""""),_display_(/*73.18*/guia/*73.22*/.nameSucursalOrigen),format.raw/*73.41*/(""""
									readonly>
								</div>
							</td>
							<td>
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text" id="basic-addon1">Sucursal</span>
									</div>
									<input type="text" class="form-control left"
									value=""""),_display_(/*83.18*/guia/*83.22*/.nameSucursalDestino),format.raw/*83.42*/(""""
									readonly>
								</div>
							</td>
							<td width="230px">
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text" id="basic-addon1">Fecha Movimiento</span>
									</div>
									<input type="date" class="form-control center"
									value=""""),_display_(/*93.18*/utilities/*93.27*/.Fechas.AAMMDD(guia.getFecha())),format.raw/*93.58*/(""""
									readonly>
								</div>
							</td>
							<td width="230px">
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text" id="basic-addon1">Fecha Ini/Ter</span>
									</div>
									<input type="date" class="form-control center"
									value=""""),_display_(/*103.18*/utilities/*103.27*/.Fechas.AAMMDD(guia.getFechaIniTerGuia())),format.raw/*103.68*/(""""
									readonly>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text" id="basic-addon1">Comercial</span>
									</div>
									<input type="text" class="form-control left"
									value=""""),_display_(/*115.18*/guia/*115.22*/.getNameComercial),format.raw/*115.39*/(""""
									readonly>
								</div>
							</td>
							<td align="center">
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text" id="basic-addon1">Cliente</span>
									</div>
									<input type="text" class="form-control left"
									value = """"),_display_(/*125.20*/nickCliente),format.raw/*125.31*/(""""
									readonly>
								</div>
							</td>
							<td colspan="6" >
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text" id="basic-addon1">Observaciones</span>
									</div>
									<textarea class="form-control" rows="2"
									autocomplete="off"
									readonly>"""),_display_(/*136.20*/guia/*136.24*/.getObservaciones()),format.raw/*136.43*/("""</textarea>
								</div>
							</td>
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
							<TH>CANTIDAD</TH>
							<TH>VTA</TH>
							<TH>MON</TH>
							<TH>P.Repos<br>Unitario</TH>
							<TH>P.Repos<br>Total</TH>
							<TH>TOT.KG</TH>
							<TH>TOT.M2</TH>
							"""),_display_(if(bodegaOrigen.esInterna!=1)/*161.38*/{_display_(Seq[Any](format.raw/*161.39*/("""
								"""),_display_(/*162.10*/for(titEstado <- listTipoEstado) yield /*162.42*/{_display_(Seq[Any](format.raw/*162.43*/("""
									"""),format.raw/*163.10*/("""<TH style="font-size:10px; vertical-align:middle">
										"""),_display_(/*164.12*/titEstado/*164.21*/.getSigla()),format.raw/*164.32*/("""
									"""),format.raw/*165.10*/("""</TH>
								""")))}),format.raw/*166.10*/("""
							""")))} else {null} ),format.raw/*167.9*/("""
						"""),format.raw/*168.7*/("""</TR>
					</thead>
					<tbody>
						"""),_display_(/*171.8*/for(lista1 <- detalleMovimiento) yield /*171.40*/{_display_(Seq[Any](format.raw/*171.41*/("""
							"""),format.raw/*172.8*/("""<TR>
								<td  style="text-align:center;">
									"""),_display_(if(!lista1.get(22).equals("0"))/*174.42*/{_display_(Seq[Any](format.raw/*174.43*/("""
											"""),format.raw/*175.12*/("""<img src='/viewImg/"""),_display_(/*175.32*/lista1/*175.38*/.get(22)),format.raw/*175.46*/("""' style="width:auto; max-height:60px">
									""")))} else {null} ),format.raw/*176.11*/("""
								"""),format.raw/*177.9*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*178.40*/lista1/*178.46*/.get(4)),format.raw/*178.53*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*179.42*/lista1/*179.48*/.get(24)),format.raw/*179.56*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*180.42*/lista1/*180.48*/.get(5)),format.raw/*180.55*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*181.40*/lista1/*181.46*/.get(6)),format.raw/*181.53*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*182.41*/lista1/*182.47*/.get(25)),format.raw/*182.55*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*183.41*/lista1/*183.47*/.get(26)),format.raw/*183.55*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*184.42*/lista1/*184.48*/.get(7)),format.raw/*184.55*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*185.41*/lista1/*185.47*/.get(8)),format.raw/*185.54*/("""</td>
								<td  style="text-align:center;">
									"""),_display_(if(lista1.get(20).equals("1"))/*187.41*/{_display_(Seq[Any](format.raw/*187.42*/("""
										"""),format.raw/*188.11*/("""<input type="checkbox" checked disabled>
									""")))}else/*189.15*/{_display_(Seq[Any](format.raw/*189.16*/("""
										"""),format.raw/*190.11*/("""<input type="checkbox" disabled>
									""")))}),format.raw/*191.11*/("""
								"""),format.raw/*192.9*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*193.42*/lista1/*193.48*/.get(11)),format.raw/*193.56*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*194.41*/lista1/*194.47*/.get(9)),format.raw/*194.54*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*195.41*/lista1/*195.47*/.get(33)),format.raw/*195.55*/("""</td>
								
								<td  style="text-align:right;">"""),_display_(/*197.41*/lista1/*197.47*/.get(27)),format.raw/*197.55*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*198.41*/lista1/*198.47*/.get(28)),format.raw/*198.55*/("""</td>
								
								"""),_display_(if(bodegaOrigen.esInterna!=1)/*200.39*/{_display_(Seq[Any](format.raw/*200.40*/("""
										"""),format.raw/*201.11*/("""<td style="display:none">
											<input type="hidden" value=""""),_display_(/*202.41*/lista1/*202.47*/.get(16)),format.raw/*202.55*/("""" id="estados_"""),_display_(/*202.70*/lista1/*202.76*/.get(2)),format.raw/*202.83*/("""_"""),_display_(/*202.85*/lista1/*202.91*/.get(23)),format.raw/*202.99*/("""">
											<input type="hidden" value=""""),_display_(/*203.41*/lista1/*203.47*/.get(32)),format.raw/*203.55*/("""" id="reparaciones_"""),_display_(/*203.75*/lista1/*203.81*/.get(2)),format.raw/*203.88*/("""_"""),_display_(/*203.90*/lista1/*203.96*/.get(23)),format.raw/*203.104*/("""">
										</td>
									"""),_display_(/*205.11*/for((est,index) <- listTipoEstado.zipWithIndex) yield /*205.58*/{_display_(Seq[Any](format.raw/*205.59*/("""
										"""),format.raw/*206.11*/("""<td style="font-size:10px; vertical-align:middle">
											<input type="text" class="form-control height23px right width40px"
												style="font-size:10px; vertical-align:middle"
												id="estado_"""),_display_(/*209.25*/lista1/*209.31*/.get(2)),format.raw/*209.38*/("""_"""),_display_(/*209.40*/lista1/*209.46*/.get(23)),format.raw/*209.54*/("""_"""),_display_(/*209.56*/index),format.raw/*209.61*/(""""
												value="0"
												onfocus="value=value.replace(/,/g,'')" 
												onkeydown="return ingresoInt(window.event)"
												autocomplete="off"
												ondblclick = "if(value>0 && """),_display_(/*214.42*/est/*214.45*/.getReparable()),format.raw/*214.60*/("""==1) editaReparaciones("""),_display_(/*214.84*/index),format.raw/*214.89*/(""","""),_display_(/*214.91*/listTipoEstado/*214.105*/.size()),format.raw/*214.112*/(""","""),_display_(/*214.114*/lista1/*214.120*/.get(2)),format.raw/*214.127*/(""","""),_display_(/*214.129*/lista1/*214.135*/.get(23)),format.raw/*214.143*/(""",value,"""),_display_(/*214.151*/est/*214.154*/.getReparable()),format.raw/*214.169*/(""","""),_display_(/*214.171*/est/*214.174*/.getId()),format.raw/*214.182*/(""")"
												readonly>

												<script>
													niv = """"),_display_(/*218.22*/lista1/*218.28*/.get(31)),format.raw/*218.36*/("""";
													niv1 = niv.split(";");
													for(let i=0; i<niv1.length && niv1.length>1; i++)"""),format.raw/*220.63*/("""{"""),format.raw/*220.64*/("""
														"""),format.raw/*221.15*/("""let niv2 = niv1[i].split(":");
														if(niv2[0]==""""),_display_(/*222.29*/est/*222.32*/.getId()),format.raw/*222.40*/("""")"""),format.raw/*222.42*/("""{"""),format.raw/*222.43*/("""
															"""),format.raw/*223.16*/("""$("#estado_"""),_display_(/*223.28*/lista1/*223.34*/.get(2)),format.raw/*223.41*/("""_"""),_display_(/*223.43*/lista1/*223.49*/.get(23)),format.raw/*223.57*/("""_"""),_display_(/*223.59*/index),format.raw/*223.64*/("""").val(niv2[1]);
														"""),format.raw/*224.15*/("""}"""),format.raw/*224.16*/("""
													"""),format.raw/*225.14*/("""}"""),format.raw/*225.15*/("""
												"""),format.raw/*226.13*/("""</script>
												
										</td>
									""")))}),format.raw/*229.11*/("""
								""")))} else {null} ),format.raw/*230.10*/("""
							"""),format.raw/*231.8*/("""</TR>
			 			""")))}),format.raw/*232.9*/("""
					"""),format.raw/*233.6*/("""</tbody>
				</table>
			</div>
		</div>
		
		<form class="formulario" id="formDescargaDocumento" method="post" action="/downloadPDF/">	
			<input type="hidden" id="descargaDocumento" name="nombreArchivo" value=""""),_display_(/*239.77*/guia/*239.81*/.getDocAnexo()),format.raw/*239.95*/("""">
		</form>
		
		
		
		<div class="noprint" align="left">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" 
						onclick="location.href='/routes2/otListarDespachosImgPeriodo/';">
				</div>
			</div>
		</div>
	</div>

	<div id='modalReparaciones'></div>
	
	
	
	
	
				
				
	
""")))}),format.raw/*263.2*/("""


"""),format.raw/*266.1*/("""<style>
	#tablaPrincipal td:nth-child(2) """),format.raw/*267.34*/("""{"""),format.raw/*267.35*/(""" """),format.raw/*267.36*/("""text-align: center; """),format.raw/*267.56*/("""}"""),format.raw/*267.57*/("""
	"""),format.raw/*268.2*/("""#tablaPrincipal td:nth-child(3) """),format.raw/*268.34*/("""{"""),format.raw/*268.35*/(""" """),format.raw/*268.36*/("""text-align: center; """),format.raw/*268.56*/("""}"""),format.raw/*268.57*/("""
	"""),format.raw/*269.2*/("""#tablaPrincipal td:nth-child(5) """),format.raw/*269.34*/("""{"""),format.raw/*269.35*/(""" """),format.raw/*269.36*/("""text-align: right; """),format.raw/*269.55*/("""}"""),format.raw/*269.56*/("""
	"""),format.raw/*270.2*/("""#tablaPrincipal td:nth-child(6) """),format.raw/*270.34*/("""{"""),format.raw/*270.35*/(""" """),format.raw/*270.36*/("""text-align: right; """),format.raw/*270.55*/("""}"""),format.raw/*270.56*/("""
	"""),format.raw/*271.2*/("""#tablaPrincipal td:nth-child(7) """),format.raw/*271.34*/("""{"""),format.raw/*271.35*/(""" """),format.raw/*271.36*/("""text-align: center; """),format.raw/*271.56*/("""}"""),format.raw/*271.57*/("""
	"""),format.raw/*272.2*/("""#tablaPrincipal td:nth-child(8) """),format.raw/*272.34*/("""{"""),format.raw/*272.35*/(""" """),format.raw/*272.36*/("""text-align: right; """),format.raw/*272.55*/("""}"""),format.raw/*272.56*/("""
	"""),format.raw/*273.2*/("""#tablaPrincipal td:nth-child(9) """),format.raw/*273.34*/("""{"""),format.raw/*273.35*/(""" """),format.raw/*273.36*/("""text-align: right; """),format.raw/*273.55*/("""}"""),format.raw/*273.56*/("""
	"""),format.raw/*274.2*/("""#tablaPrincipal td:nth-child(10) """),format.raw/*274.35*/("""{"""),format.raw/*274.36*/(""" """),format.raw/*274.37*/("""text-align: center; """),format.raw/*274.57*/("""}"""),format.raw/*274.58*/("""
	"""),format.raw/*275.2*/("""#tablaPrincipal td:nth-child(11) """),format.raw/*275.35*/("""{"""),format.raw/*275.36*/(""" """),format.raw/*275.37*/("""text-align: center; """),format.raw/*275.57*/("""}"""),format.raw/*275.58*/("""
	"""),format.raw/*276.2*/("""#tablaPrincipal td:nth-child(12) """),format.raw/*276.35*/("""{"""),format.raw/*276.36*/(""" """),format.raw/*276.37*/("""text-align: right; """),format.raw/*276.56*/("""}"""),format.raw/*276.57*/("""
	"""),format.raw/*277.2*/("""#tablaPrincipal td:nth-child(13) """),format.raw/*277.35*/("""{"""),format.raw/*277.36*/(""" """),format.raw/*277.37*/("""text-align: right; """),format.raw/*277.56*/("""}"""),format.raw/*277.57*/("""
	"""),format.raw/*278.2*/("""#tablaPrincipal td:nth-child(14) """),format.raw/*278.35*/("""{"""),format.raw/*278.36*/(""" """),format.raw/*278.37*/("""text-align: right; """),format.raw/*278.56*/("""}"""),format.raw/*278.57*/("""
	"""),_display_(if(bodegaOrigen.esInterna!=1)/*279.32*/{_display_(Seq[Any](format.raw/*279.33*/("""
		"""),_display_(/*280.4*/for((est,index) <- listTipoEstado.zipWithIndex) yield /*280.51*/{_display_(Seq[Any](format.raw/*280.52*/("""
			"""),format.raw/*281.4*/("""#tablaPrincipal td:nth-child("""),_display_(/*281.34*/(index+16)),format.raw/*281.44*/(""") """),format.raw/*281.46*/("""{"""),format.raw/*281.47*/(""" """),format.raw/*281.48*/("""vertical-align: middle; """),format.raw/*281.72*/("""}"""),format.raw/*281.73*/("""
		""")))}),format.raw/*282.4*/("""
	""")))} else {null} ),format.raw/*283.3*/("""
"""),format.raw/*284.1*/("""</style>

<script type="text/javascript">
	let tblPrincipal;
	let listReparaNiv1;
	$(document).ready(function() """),format.raw/*289.31*/("""{"""),format.raw/*289.32*/("""
		  """),format.raw/*290.5*/("""let repAux = ""
		  """),_display_(/*291.6*/for(r <- listTipoReparacion) yield /*291.34*/{_display_(Seq[Any](format.raw/*291.35*/("""
				"""),format.raw/*292.5*/("""repAux += """"),_display_(/*292.17*/r/*292.18*/.getId()),format.raw/*292.26*/(""":&&:"""),_display_(/*292.31*/r/*292.32*/.id_tipoEstado),format.raw/*292.46*/(""":&&:"""),_display_(/*292.51*/r/*292.52*/.getSigla()),format.raw/*292.63*/(""":&&:"""),_display_(/*292.68*/r/*292.69*/.getNombre()),format.raw/*292.81*/(""":&&:"""),_display_(/*292.86*/r/*292.87*/.getMoneda()),format.raw/*292.99*/(""":&&:"""),_display_(/*292.104*/r/*292.105*/.getPrecio()),format.raw/*292.117*/(""";&&;"
		  """)))}),format.raw/*293.6*/("""
		  """),format.raw/*294.5*/("""listReparaNiv1 = repAux.split(";&&;");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*296.2*/("""}"""),format.raw/*296.3*/(""");
	
	
	
	const editaReparaciones = (index, largo, id_equipo, id_cotizacion, cantEstado, esReparable, id_tipoEstado) =>"""),format.raw/*300.111*/("""{"""),format.raw/*300.112*/("""
		"""),format.raw/*301.3*/("""let modalReparaciones="<div id='reparaciones' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>"+
			"<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'><div class='modal-content'><div class='modal-header'>"+
			"<h5 class='modal-title'>SELECCIONAR REPARACIONES</h5><button type='button' class='close' data-dismiss='modal' aria-label='Close'>"+
	        "<span aria-hidden='true'>&times;</span></button></div><div class='modal-body'>"+
			"<table id='tablaListaReparaciones' class='table table-sm table-bordered table-condensed table-hover table-fluid'>"+
			"<thead style='background-color: #eeeeee'><TR><TH>SIGLA</TH><TH>REPARACION</TH><TH>MONEDA</TH><TH>PRECIO</TH><TH>CANTIDAD</TH></TR></thead>"+
			"<tbody>";
			let repar = $("#reparaciones_"+id_equipo+"_"+id_cotizacion).val();
			let reparNiv1 = repar.split(";");
			for(let j=0; j<reparNiv1.length; j++)"""),format.raw/*310.41*/("""{"""),format.raw/*310.42*/("""
				"""),format.raw/*311.5*/("""let reparNiv2 = reparNiv1[j].split(":");
				if(reparNiv2.length>0 && reparNiv2[0]==id_tipoEstado)"""),format.raw/*312.58*/("""{"""),format.raw/*312.59*/("""
					"""),format.raw/*313.6*/("""for(i=0;i<listReparaNiv1.length;i++)"""),format.raw/*313.42*/("""{"""),format.raw/*313.43*/("""
				    	"""),format.raw/*314.10*/("""let listReparaNiv2 = listReparaNiv1[i].split(":&&:");
				    	if(listReparaNiv2[1]==id_tipoEstado)"""),format.raw/*315.46*/("""{"""),format.raw/*315.47*/("""
				    		"""),format.raw/*316.11*/("""//  0=getId()   1=id_tipoEstado   2=getSigla() 3=getNombre()   4=getMoneda()   5=getPrecio()
				    		if(listReparaNiv2[0]==reparNiv2[1])"""),format.raw/*317.46*/("""{"""),format.raw/*317.47*/("""
				    			"""),format.raw/*318.12*/("""modalReparaciones += "<tr>"+
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
				    		"""),format.raw/*331.11*/("""}"""),format.raw/*331.12*/("""
				    	"""),format.raw/*332.10*/("""}"""),format.raw/*332.11*/("""
				    """),format.raw/*333.9*/("""}"""),format.raw/*333.10*/("""
				"""),format.raw/*334.5*/("""}"""),format.raw/*334.6*/("""
			"""),format.raw/*335.4*/("""}"""),format.raw/*335.5*/("""
			"""),format.raw/*336.4*/("""modalReparaciones += "<TR><td  colspan='6'><div class='row justify-content-md-left'><div class='col-xs-12 col-sm-6 col-md-6 col-lg-6'>"+
				"</div></div></TR></tbody></table><div class='row'><div class='col-sm-12' style='text-align:center'>"+
				"<button type='button' class='btn btn-sm  btn-warning' style='font-size: 10px;' data-dismiss='modal'>Listo</button></div></div></div></div></div></div>";
		    document.getElementById("modalReparaciones").innerHTML = modalReparaciones;
		    $("#reparaciones").modal("show");
	"""),format.raw/*341.2*/("""}"""),format.raw/*341.3*/("""

	
	
	
	
	


	
"""),format.raw/*351.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,guia:tables.Guia,detalleMovimiento:List[List[String]],bodegaOrigen:tables.BodegaEmpresa,listTipoEstado:List[tables.TipoEstado],listTipoReparacion:List[tables.TipoReparacion],coti:tables.Cotizacion,nickCliente:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,guia,detalleMovimiento,bodegaOrigen,listTipoEstado,listTipoReparacion,coti,nickCliente)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.Guia,List[List[String]],tables.BodegaEmpresa,List[tables.TipoEstado],List[tables.TipoReparacion],tables.Cotizacion,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,guia,detalleMovimiento,bodegaOrigen,listTipoEstado,listTipoReparacion,coti,nickCliente) => apply(mapDiccionario,mapPermiso,userMnu,guia,detalleMovimiento,bodegaOrigen,listTipoEstado,listTipoReparacion,coti,nickCliente)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizar/otDetalleDespachoImg.scala.html
                  HASH: 8166a9c5fc498336f15aff50a0df6740a9516d4d
                  MATRIX: 1149->1|1570->329|1610->344|1626->352|1665->354|1693->357|1761->405|1789->407|1866->458|1953->524|1983->527|2516->1033|2529->1037|2580->1067|2918->1378|2931->1382|2983->1413|3311->1714|3324->1718|3357->1730|3683->2029|3696->2033|3737->2053|3977->2266|4016->2267|4052->2276|4086->2291|4125->2292|4161->2301|4352->2462|4387->2470|4694->2750|4707->2754|4747->2773|5074->3073|5087->3077|5128->3097|5479->3421|5497->3430|5549->3461|5898->3782|5917->3791|5980->3832|6332->4156|6346->4160|6385->4177|6729->4493|6762->4504|7133->4847|7147->4851|7188->4870|7871->5525|7911->5526|7949->5536|7998->5568|8038->5569|8077->5579|8167->5641|8186->5650|8219->5661|8258->5671|8305->5686|8358->5695|8393->5702|8460->5742|8509->5774|8549->5775|8585->5783|8700->5870|8740->5871|8781->5883|8829->5903|8845->5909|8875->5917|8969->5966|9006->5975|9079->6020|9095->6026|9124->6033|9199->6080|9215->6086|9245->6094|9320->6141|9336->6147|9365->6154|9438->6199|9454->6205|9483->6212|9557->6258|9573->6264|9603->6272|9677->6318|9693->6324|9723->6332|9798->6379|9814->6385|9843->6392|9917->6438|9933->6444|9962->6451|10077->6538|10117->6539|10157->6550|10232->6605|10272->6606|10312->6617|10387->6660|10424->6669|10499->6716|10515->6722|10545->6730|10619->6776|10635->6782|10664->6789|10738->6835|10754->6841|10784->6849|10867->6904|10883->6910|10913->6918|10987->6964|11003->6970|11033->6978|11114->7031|11154->7032|11194->7043|11288->7109|11304->7115|11334->7123|11377->7138|11393->7144|11422->7151|11452->7153|11468->7159|11498->7167|11569->7210|11585->7216|11615->7224|11663->7244|11679->7250|11708->7257|11738->7259|11754->7265|11785->7273|11842->7302|11906->7349|11946->7350|11986->7361|12225->7572|12241->7578|12270->7585|12300->7587|12316->7593|12346->7601|12376->7603|12403->7608|12635->7812|12648->7815|12685->7830|12737->7854|12764->7859|12794->7861|12819->7875|12849->7882|12880->7884|12897->7890|12927->7897|12958->7899|12975->7905|13006->7913|13043->7921|13057->7924|13095->7939|13126->7941|13140->7944|13171->7952|13267->8020|13283->8026|13313->8034|13443->8135|13473->8136|13517->8151|13604->8210|13617->8213|13647->8221|13678->8223|13708->8224|13753->8240|13793->8252|13809->8258|13838->8265|13868->8267|13884->8273|13914->8281|13944->8283|13971->8288|14031->8319|14061->8320|14104->8334|14134->8335|14176->8348|14257->8397|14312->8407|14348->8415|14393->8429|14427->8435|14668->8648|14682->8652|14718->8666|15176->9093|15207->9096|15277->9137|15307->9138|15337->9139|15386->9159|15416->9160|15446->9162|15507->9194|15537->9195|15567->9196|15616->9216|15646->9217|15676->9219|15737->9251|15767->9252|15797->9253|15845->9272|15875->9273|15905->9275|15966->9307|15996->9308|16026->9309|16074->9328|16104->9329|16134->9331|16195->9363|16225->9364|16255->9365|16304->9385|16334->9386|16364->9388|16425->9420|16455->9421|16485->9422|16533->9441|16563->9442|16593->9444|16654->9476|16684->9477|16714->9478|16762->9497|16792->9498|16822->9500|16884->9533|16914->9534|16944->9535|16993->9555|17023->9556|17053->9558|17115->9591|17145->9592|17175->9593|17224->9613|17254->9614|17284->9616|17346->9649|17376->9650|17406->9651|17454->9670|17484->9671|17514->9673|17576->9706|17606->9707|17636->9708|17684->9727|17714->9728|17744->9730|17806->9763|17836->9764|17866->9765|17914->9784|17944->9785|18004->9817|18044->9818|18075->9822|18139->9869|18179->9870|18211->9874|18269->9904|18301->9914|18332->9916|18362->9917|18392->9918|18445->9942|18475->9943|18510->9947|18557->9950|18586->9951|18727->10063|18757->10064|18790->10069|18838->10090|18883->10118|18923->10119|18956->10124|18996->10136|19007->10137|19037->10145|19070->10150|19081->10151|19117->10165|19150->10170|19161->10171|19194->10182|19227->10187|19238->10188|19272->10200|19305->10205|19316->10206|19350->10218|19384->10223|19396->10224|19431->10236|19473->10247|19506->10252|19643->10361|19672->10362|19821->10481|19852->10482|19883->10485|20822->11395|20852->11396|20885->11401|21012->11499|21042->11500|21076->11506|21141->11542|21171->11543|21210->11553|21338->11652|21368->11653|21408->11664|21575->11802|21605->11803|21646->11815|22380->12520|22410->12521|22449->12531|22479->12532|22516->12541|22546->12542|22579->12547|22608->12548|22640->12552|22669->12553|22701->12557|23254->13082|23283->13083|23327->13099
                  LINES: 28->1|35->4|37->6|37->6|37->6|39->8|39->8|40->9|41->10|41->10|42->11|53->22|53->22|53->22|63->32|63->32|63->32|73->42|73->42|73->42|83->52|83->52|83->52|88->57|88->57|89->58|90->59|90->59|91->60|94->63|95->64|104->73|104->73|104->73|114->83|114->83|114->83|124->93|124->93|124->93|134->103|134->103|134->103|146->115|146->115|146->115|156->125|156->125|167->136|167->136|167->136|192->161|192->161|193->162|193->162|193->162|194->163|195->164|195->164|195->164|196->165|197->166|198->167|199->168|202->171|202->171|202->171|203->172|205->174|205->174|206->175|206->175|206->175|206->175|207->176|208->177|209->178|209->178|209->178|210->179|210->179|210->179|211->180|211->180|211->180|212->181|212->181|212->181|213->182|213->182|213->182|214->183|214->183|214->183|215->184|215->184|215->184|216->185|216->185|216->185|218->187|218->187|219->188|220->189|220->189|221->190|222->191|223->192|224->193|224->193|224->193|225->194|225->194|225->194|226->195|226->195|226->195|228->197|228->197|228->197|229->198|229->198|229->198|231->200|231->200|232->201|233->202|233->202|233->202|233->202|233->202|233->202|233->202|233->202|233->202|234->203|234->203|234->203|234->203|234->203|234->203|234->203|234->203|234->203|236->205|236->205|236->205|237->206|240->209|240->209|240->209|240->209|240->209|240->209|240->209|240->209|245->214|245->214|245->214|245->214|245->214|245->214|245->214|245->214|245->214|245->214|245->214|245->214|245->214|245->214|245->214|245->214|245->214|245->214|245->214|245->214|249->218|249->218|249->218|251->220|251->220|252->221|253->222|253->222|253->222|253->222|253->222|254->223|254->223|254->223|254->223|254->223|254->223|254->223|254->223|254->223|255->224|255->224|256->225|256->225|257->226|260->229|261->230|262->231|263->232|264->233|270->239|270->239|270->239|294->263|297->266|298->267|298->267|298->267|298->267|298->267|299->268|299->268|299->268|299->268|299->268|299->268|300->269|300->269|300->269|300->269|300->269|300->269|301->270|301->270|301->270|301->270|301->270|301->270|302->271|302->271|302->271|302->271|302->271|302->271|303->272|303->272|303->272|303->272|303->272|303->272|304->273|304->273|304->273|304->273|304->273|304->273|305->274|305->274|305->274|305->274|305->274|305->274|306->275|306->275|306->275|306->275|306->275|306->275|307->276|307->276|307->276|307->276|307->276|307->276|308->277|308->277|308->277|308->277|308->277|308->277|309->278|309->278|309->278|309->278|309->278|309->278|310->279|310->279|311->280|311->280|311->280|312->281|312->281|312->281|312->281|312->281|312->281|312->281|312->281|313->282|314->283|315->284|320->289|320->289|321->290|322->291|322->291|322->291|323->292|323->292|323->292|323->292|323->292|323->292|323->292|323->292|323->292|323->292|323->292|323->292|323->292|323->292|323->292|323->292|323->292|323->292|323->292|324->293|325->294|327->296|327->296|331->300|331->300|332->301|341->310|341->310|342->311|343->312|343->312|344->313|344->313|344->313|345->314|346->315|346->315|347->316|348->317|348->317|349->318|362->331|362->331|363->332|363->332|364->333|364->333|365->334|365->334|366->335|366->335|367->336|372->341|372->341|382->351
                  -- GENERATED --
              */
          