
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

object otDetalleDespacho extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template10[Map[String,String],Map[String,String],utilities.UserMnu,tables.Guia,List[List[String]],tables.BodegaEmpresa,List[tables.TipoEstado],List[tables.TipoReparacion],tables.Cotizacion,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
guia: tables.Guia, detalleMovimiento: List[List[String]], bodegaOrigen: tables.BodegaEmpresa, listTipoEstado: List[tables.TipoEstado], listTipoReparacion: List[tables.TipoReparacion], 
coti: tables.Cotizacion, nickCliente: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.1*/("""    			
    	
"""),_display_(/*6.2*/main("")/*6.10*/ {_display_(Seq[Any](format.raw/*6.12*/("""

"""),_display_(/*8.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*8.50*/("""
	"""),format.raw/*9.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*10.4*/barraTitulo(mapDiccionario, "DETALLE DE MOVIMIENTO SIN IMAGEN","")),format.raw/*10.70*/("""
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
  										value=""""),_display_(/*22.21*/guia/*22.25*/.getBodegaOrigen().toUpperCase),format.raw/*22.55*/("""" 
  										readonly>
								</div>
							</td>
							<td width="25%">
								<div class="input-group">
							  		<div class="input-group-prepend">
							    		<span class="input-group-text" id="basic-addon1">Destino</span>
							  		</div>
										<input type="text" class="form-control left"
											value=""""),_display_(/*32.20*/guia/*32.24*/.getBodegaDestino().toUpperCase),format.raw/*32.55*/("""" 
											readonly>
								</div>
							</td>
							<td>
								<div class="input-group">
							  		<div class="input-group-prepend">
							    		<span class="input-group-text" id="basic-addon1">Nro.Mov</span>
							  		</div>
							  		<input type="text" class="form-control left"
											value = """"),_display_(/*42.22*/guia/*42.26*/.getNumero()),format.raw/*42.38*/(""""
											readonly>
								</div>
							</td>
							<td>
								<div class="input-group">
							  		<div class="input-group-prepend">
							    		<span class="input-group-text" id="basic-addon1">Nro.Ref</span>
							  		</div>
							  		<input type="text" class="form-control left"
							  				value=""""),_display_(/*52.22*/guia/*52.26*/.getNumGuiaCliente()),format.raw/*52.46*/(""""
							  				readonly>
								</div>
							</td>
							<td rowspan="2" style="width: 50px; min-width: 50px; max-width: 50px; text-align: center; vertical-align: middle">
								"""),_display_(if(guia.getDocAnexo().equals("0"))/*57.44*/{_display_(Seq[Any](format.raw/*57.45*/("""
									"""),format.raw/*58.10*/("""--
								""")))}else/*59.14*/{_display_(Seq[Any](format.raw/*59.15*/("""
									"""),format.raw/*60.10*/("""<a href="#" onclick="document.getElementById('formDescargaDocumento').submit();">
										<kbd style="background-color: #7F8C8D">DOC</kbd>
									</a>
								""")))}),format.raw/*63.10*/("""
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
							  			value=""""),_display_(/*115.21*/guia/*115.25*/.getNameComercial),format.raw/*115.42*/(""""
					        			readonly>
								</div>
							</td>
							<td align="center">
								<div class="input-group">
							  		<div class="input-group-prepend">
							    		<span class="input-group-text" id="basic-addon1">Cliente</span>
							  		</div>
										<input type="text" class="form-control left"
											value = """"),_display_(/*125.22*/nickCliente),format.raw/*125.33*/(""""
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
							"""),_display_(if(bodegaOrigen.esInterna!=1)/*160.38*/{_display_(Seq[Any](format.raw/*160.39*/("""
								"""),_display_(/*161.10*/for(titEstado <- listTipoEstado) yield /*161.42*/{_display_(Seq[Any](format.raw/*161.43*/("""
									"""),format.raw/*162.10*/("""<TH style="font-size:10px; vertical-align:middle">
										"""),_display_(/*163.12*/titEstado/*163.21*/.getSigla()),format.raw/*163.32*/("""
									"""),format.raw/*164.10*/("""</TH>
								""")))}),format.raw/*165.10*/("""
							""")))} else {null} ),format.raw/*166.9*/("""
						"""),format.raw/*167.7*/("""</TR>
					</thead>
					<tbody>
						"""),_display_(/*170.8*/for(lista1 <- detalleMovimiento) yield /*170.40*/{_display_(Seq[Any](format.raw/*170.41*/("""
							"""),format.raw/*171.8*/("""<TR>
								<td  style="text-align:left;">"""),_display_(/*172.40*/lista1/*172.46*/.get(4)),format.raw/*172.53*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*173.42*/lista1/*173.48*/.get(24)),format.raw/*173.56*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*174.42*/lista1/*174.48*/.get(5)),format.raw/*174.55*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*175.40*/lista1/*175.46*/.get(6)),format.raw/*175.53*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*176.41*/lista1/*176.47*/.get(25)),format.raw/*176.55*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*177.41*/lista1/*177.47*/.get(26)),format.raw/*177.55*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*178.42*/lista1/*178.48*/.get(7)),format.raw/*178.55*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*179.41*/lista1/*179.47*/.get(8)),format.raw/*179.54*/("""</td>
								<td  style="text-align:center;">
									"""),_display_(if(lista1.get(20).equals("1"))/*181.41*/{_display_(Seq[Any](format.raw/*181.42*/("""
										"""),format.raw/*182.11*/("""<input type="checkbox" checked disabled>
									""")))}else/*183.15*/{_display_(Seq[Any](format.raw/*183.16*/("""
										"""),format.raw/*184.11*/("""<input type="checkbox" disabled>
									""")))}),format.raw/*185.11*/("""
								"""),format.raw/*186.9*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*187.42*/lista1/*187.48*/.get(11)),format.raw/*187.56*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*188.41*/lista1/*188.47*/.get(9)),format.raw/*188.54*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*189.41*/lista1/*189.47*/.get(33)),format.raw/*189.55*/("""</td>
								
								<td  style="text-align:right;">"""),_display_(/*191.41*/lista1/*191.47*/.get(27)),format.raw/*191.55*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*192.41*/lista1/*192.47*/.get(28)),format.raw/*192.55*/("""</td>
								
								"""),_display_(if(bodegaOrigen.esInterna!=1)/*194.39*/{_display_(Seq[Any](format.raw/*194.40*/("""
										"""),format.raw/*195.11*/("""<td style="display:none">
											<input type="hidden" value=""""),_display_(/*196.41*/lista1/*196.47*/.get(16)),format.raw/*196.55*/("""" id="estados_"""),_display_(/*196.70*/lista1/*196.76*/.get(2)),format.raw/*196.83*/("""_"""),_display_(/*196.85*/lista1/*196.91*/.get(23)),format.raw/*196.99*/("""">
											<input type="hidden" value=""""),_display_(/*197.41*/lista1/*197.47*/.get(32)),format.raw/*197.55*/("""" id="reparaciones_"""),_display_(/*197.75*/lista1/*197.81*/.get(2)),format.raw/*197.88*/("""_"""),_display_(/*197.90*/lista1/*197.96*/.get(23)),format.raw/*197.104*/("""">
										</td>
									"""),_display_(/*199.11*/for((est,index) <- listTipoEstado.zipWithIndex) yield /*199.58*/{_display_(Seq[Any](format.raw/*199.59*/("""
										"""),format.raw/*200.11*/("""<td style="font-size:10px; vertical-align:middle">
											<input type="text" class="form-control height23px right width40px"
												style="font-size:10px; vertical-align:middle"
												id="estado_"""),_display_(/*203.25*/lista1/*203.31*/.get(2)),format.raw/*203.38*/("""_"""),_display_(/*203.40*/lista1/*203.46*/.get(23)),format.raw/*203.54*/("""_"""),_display_(/*203.56*/index),format.raw/*203.61*/(""""
												value="0"
												onfocus="value=value.replace(/,/g,'')" 
												onkeydown="return ingresoInt(window.event)"
												autocomplete="off"
												ondblclick = "if(value>0 && '"""),_display_(/*208.43*/est/*208.46*/.getReparable()),format.raw/*208.61*/("""'==1) editaReparaciones('"""),_display_(/*208.87*/index),format.raw/*208.92*/("""','"""),_display_(/*208.96*/listTipoEstado/*208.110*/.size()),format.raw/*208.117*/("""','"""),_display_(/*208.121*/lista1/*208.127*/.get(2)),format.raw/*208.134*/("""','"""),_display_(/*208.138*/lista1/*208.144*/.get(23)),format.raw/*208.152*/("""',value,'"""),_display_(/*208.162*/est/*208.165*/.getReparable()),format.raw/*208.180*/("""','"""),_display_(/*208.184*/est/*208.187*/.getId()),format.raw/*208.195*/("""')"
												readonly>

												<script>
													niv = """"),_display_(/*212.22*/lista1/*212.28*/.get(31)),format.raw/*212.36*/("""";
													niv1 = niv.split(";");
													for(let i=0; i<niv1.length && niv1.length>1; i++)"""),format.raw/*214.63*/("""{"""),format.raw/*214.64*/("""
														"""),format.raw/*215.15*/("""let niv2 = niv1[i].split(":");
														if(niv2[0]==""""),_display_(/*216.29*/est/*216.32*/.getId()),format.raw/*216.40*/("""")"""),format.raw/*216.42*/("""{"""),format.raw/*216.43*/("""
															"""),format.raw/*217.16*/("""$("#estado_"""),_display_(/*217.28*/lista1/*217.34*/.get(2)),format.raw/*217.41*/("""_"""),_display_(/*217.43*/lista1/*217.49*/.get(23)),format.raw/*217.57*/("""_"""),_display_(/*217.59*/index),format.raw/*217.64*/("""").val(niv2[1]);
														"""),format.raw/*218.15*/("""}"""),format.raw/*218.16*/("""
													"""),format.raw/*219.14*/("""}"""),format.raw/*219.15*/("""
												"""),format.raw/*220.13*/("""</script>
												
										</td>
									""")))}),format.raw/*223.11*/("""
								""")))} else {null} ),format.raw/*224.10*/("""
							"""),format.raw/*225.8*/("""</TR>
			 			""")))}),format.raw/*226.9*/("""
					"""),format.raw/*227.6*/("""</tbody>
				</table>
			</div>
		</div>
		
		<form class="formulario" id="formDescargaDocumento" method="post" action="/downloadPDF/">	
			<input type="hidden" id="descargaDocumento" name="nombreArchivo" value=""""),_display_(/*233.77*/guia/*233.81*/.getDocAnexo()),format.raw/*233.95*/("""">
		</form>
		
		
		
		<div class="noprint" align="left">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" 
						onclick="location.href='/routes2/otListarDespachosPeriodo/';">
				</div>
			</div>
		</div>
	</div>

	<div id='modalReparaciones'></div>
	
	
	
	
	
				
				
	
""")))}),format.raw/*257.2*/("""


"""),format.raw/*260.1*/("""<style>
	#tablaPrincipal td:nth-child(2) """),format.raw/*261.34*/("""{"""),format.raw/*261.35*/(""" """),format.raw/*261.36*/("""text-align: center; """),format.raw/*261.56*/("""}"""),format.raw/*261.57*/("""
	"""),format.raw/*262.2*/("""#tablaPrincipal td:nth-child(3) """),format.raw/*262.34*/("""{"""),format.raw/*262.35*/(""" """),format.raw/*262.36*/("""text-align: center; """),format.raw/*262.56*/("""}"""),format.raw/*262.57*/("""
	"""),format.raw/*263.2*/("""#tablaPrincipal td:nth-child(5) """),format.raw/*263.34*/("""{"""),format.raw/*263.35*/(""" """),format.raw/*263.36*/("""text-align: right; """),format.raw/*263.55*/("""}"""),format.raw/*263.56*/("""
	"""),format.raw/*264.2*/("""#tablaPrincipal td:nth-child(6) """),format.raw/*264.34*/("""{"""),format.raw/*264.35*/(""" """),format.raw/*264.36*/("""text-align: right; """),format.raw/*264.55*/("""}"""),format.raw/*264.56*/("""
	"""),format.raw/*265.2*/("""#tablaPrincipal td:nth-child(7) """),format.raw/*265.34*/("""{"""),format.raw/*265.35*/(""" """),format.raw/*265.36*/("""text-align: center; """),format.raw/*265.56*/("""}"""),format.raw/*265.57*/("""
	"""),format.raw/*266.2*/("""#tablaPrincipal td:nth-child(8) """),format.raw/*266.34*/("""{"""),format.raw/*266.35*/(""" """),format.raw/*266.36*/("""text-align: right; """),format.raw/*266.55*/("""}"""),format.raw/*266.56*/("""
	"""),format.raw/*267.2*/("""#tablaPrincipal td:nth-child(9) """),format.raw/*267.34*/("""{"""),format.raw/*267.35*/(""" """),format.raw/*267.36*/("""text-align: right; """),format.raw/*267.55*/("""}"""),format.raw/*267.56*/("""
	"""),format.raw/*268.2*/("""#tablaPrincipal td:nth-child(10) """),format.raw/*268.35*/("""{"""),format.raw/*268.36*/(""" """),format.raw/*268.37*/("""text-align: center; """),format.raw/*268.57*/("""}"""),format.raw/*268.58*/("""
	"""),format.raw/*269.2*/("""#tablaPrincipal td:nth-child(11) """),format.raw/*269.35*/("""{"""),format.raw/*269.36*/(""" """),format.raw/*269.37*/("""text-align: center; """),format.raw/*269.57*/("""}"""),format.raw/*269.58*/("""
	"""),format.raw/*270.2*/("""#tablaPrincipal td:nth-child(12) """),format.raw/*270.35*/("""{"""),format.raw/*270.36*/(""" """),format.raw/*270.37*/("""text-align: right; """),format.raw/*270.56*/("""}"""),format.raw/*270.57*/("""
	"""),format.raw/*271.2*/("""#tablaPrincipal td:nth-child(13) """),format.raw/*271.35*/("""{"""),format.raw/*271.36*/(""" """),format.raw/*271.37*/("""text-align: right; """),format.raw/*271.56*/("""}"""),format.raw/*271.57*/("""
	"""),format.raw/*272.2*/("""#tablaPrincipal td:nth-child(14) """),format.raw/*272.35*/("""{"""),format.raw/*272.36*/(""" """),format.raw/*272.37*/("""text-align: right; """),format.raw/*272.56*/("""}"""),format.raw/*272.57*/("""
	"""),_display_(if(bodegaOrigen.esInterna!=1)/*273.32*/{_display_(Seq[Any](format.raw/*273.33*/("""
		"""),_display_(/*274.4*/for((est,index) <- listTipoEstado.zipWithIndex) yield /*274.51*/{_display_(Seq[Any](format.raw/*274.52*/("""
			"""),format.raw/*275.4*/("""#tablaPrincipal td:nth-child("""),_display_(/*275.34*/(index+16)),format.raw/*275.44*/(""") """),format.raw/*275.46*/("""{"""),format.raw/*275.47*/(""" """),format.raw/*275.48*/("""vertical-align: middle; """),format.raw/*275.72*/("""}"""),format.raw/*275.73*/("""
		""")))}),format.raw/*276.4*/("""
	""")))} else {null} ),format.raw/*277.3*/("""
"""),format.raw/*278.1*/("""</style>

<script type="text/javascript">
	let tblPrincipal;
	let listReparaNiv1;
	$(document).ready(function() """),format.raw/*283.31*/("""{"""),format.raw/*283.32*/("""
		  """),format.raw/*284.5*/("""let repAux = ""
		  """),_display_(/*285.6*/for(r <- listTipoReparacion) yield /*285.34*/{_display_(Seq[Any](format.raw/*285.35*/("""
				"""),format.raw/*286.5*/("""repAux += """"),_display_(/*286.17*/r/*286.18*/.getId()),format.raw/*286.26*/(""":&&:"""),_display_(/*286.31*/r/*286.32*/.id_tipoEstado),format.raw/*286.46*/(""":&&:"""),_display_(/*286.51*/r/*286.52*/.getSigla()),format.raw/*286.63*/(""":&&:"""),_display_(/*286.68*/r/*286.69*/.getNombre()),format.raw/*286.81*/(""":&&:"""),_display_(/*286.86*/r/*286.87*/.getMoneda()),format.raw/*286.99*/(""":&&:"""),_display_(/*286.104*/r/*286.105*/.getPrecio()),format.raw/*286.117*/(""";&&;"
		  """)))}),format.raw/*287.6*/("""
		  """),format.raw/*288.5*/("""listReparaNiv1 = repAux.split(";&&;");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*290.2*/("""}"""),format.raw/*290.3*/(""");
	
	
	
	const editaReparaciones = (index, largo, id_equipo, id_cotizacion, cantEstado, esReparable, id_tipoEstado) =>"""),format.raw/*294.111*/("""{"""),format.raw/*294.112*/("""
		"""),format.raw/*295.3*/("""let modalReparaciones="<div id='reparaciones' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>"+
			"<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'><div class='modal-content'><div class='modal-header'>"+
			"<h5 class='modal-title'>SELECCIONAR REPARACIONES</h5><button type='button' class='close' data-dismiss='modal' aria-label='Close'>"+
	        "<span aria-hidden='true'>&times;</span></button></div><div class='modal-body'>"+
			"<table id='tablaListaReparaciones' class='table table-sm table-bordered table-condensed table-hover table-fluid'>"+
			"<thead style='background-color: #eeeeee'><TR><TH>SIGLA</TH><TH>REPARACION</TH><TH>MONEDA</TH><TH>PRECIO</TH><TH>CANTIDAD</TH></TR></thead>"+
			"<tbody>";
			let repar = $("#reparaciones_"+id_equipo+"_"+id_cotizacion).val();
			let reparNiv1 = repar.split(";");
			for(let j=0; j<reparNiv1.length; j++)"""),format.raw/*304.41*/("""{"""),format.raw/*304.42*/("""
				"""),format.raw/*305.5*/("""let reparNiv2 = reparNiv1[j].split(":");
				if(reparNiv2.length>0 && reparNiv2[0]==id_tipoEstado)"""),format.raw/*306.58*/("""{"""),format.raw/*306.59*/("""
					"""),format.raw/*307.6*/("""for(i=0;i<listReparaNiv1.length;i++)"""),format.raw/*307.42*/("""{"""),format.raw/*307.43*/("""
				    	"""),format.raw/*308.10*/("""let listReparaNiv2 = listReparaNiv1[i].split(":&&:");
				    	if(listReparaNiv2[1]==id_tipoEstado)"""),format.raw/*309.46*/("""{"""),format.raw/*309.47*/("""
				    		"""),format.raw/*310.11*/("""//  0=getId()   1=id_tipoEstado   2=getSigla() 3=getNombre()   4=getMoneda()   5=getPrecio()
				    		if(listReparaNiv2[0]==reparNiv2[1])"""),format.raw/*311.46*/("""{"""),format.raw/*311.47*/("""
				    			"""),format.raw/*312.12*/("""modalReparaciones += "<tr>"+
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
				    		"""),format.raw/*325.11*/("""}"""),format.raw/*325.12*/("""
				    	"""),format.raw/*326.10*/("""}"""),format.raw/*326.11*/("""
				    """),format.raw/*327.9*/("""}"""),format.raw/*327.10*/("""
				"""),format.raw/*328.5*/("""}"""),format.raw/*328.6*/("""
			"""),format.raw/*329.4*/("""}"""),format.raw/*329.5*/("""
			"""),format.raw/*330.4*/("""modalReparaciones += "<TR><td  colspan='6'><div class='row justify-content-md-left'><div class='col-xs-12 col-sm-6 col-md-6 col-lg-6'>"+
				"</div></div></TR></tbody></table><div class='row'><div class='col-sm-12' style='text-align:center'>"+
				"<button type='button' class='btn btn-sm  btn-warning' style='font-size: 10px;' data-dismiss='modal'>Listo</button></div></div></div></div></div></div>";
		    document.getElementById("modalReparaciones").innerHTML = modalReparaciones;
		    $("#reparaciones").modal("show");
	"""),format.raw/*335.2*/("""}"""),format.raw/*335.3*/("""

	
	
	
	
	


	
"""),format.raw/*345.1*/("""</script>


		
		
	
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
                  SOURCE: app/viewsMnuCotizar/otDetalleDespacho.scala.html
                  HASH: b6eeb37c9ba6305bf39c036fbcfc703bb4950bfb
                  MATRIX: 1146->1|1567->329|1607->344|1623->352|1662->354|1690->357|1758->405|1786->407|1863->458|1950->524|1980->527|2526->1046|2539->1050|2590->1080|2942->1405|2955->1409|3007->1440|3349->1755|3362->1759|3395->1771|3736->2085|3749->2089|3790->2109|4035->2327|4074->2328|4112->2338|4147->2354|4186->2355|4224->2365|4419->2529|4454->2537|4761->2817|4774->2821|4814->2840|5141->3140|5154->3144|5195->3164|5546->3488|5564->3497|5616->3528|5965->3849|5984->3858|6047->3899|6411->4235|6425->4239|6464->4256|6825->4589|6858->4600|7231->4945|7245->4949|7286->4968|7949->5603|7989->5604|8027->5614|8076->5646|8116->5647|8155->5657|8245->5719|8264->5728|8297->5739|8336->5749|8383->5764|8436->5773|8471->5780|8538->5820|8587->5852|8627->5853|8663->5861|8735->5905|8751->5911|8780->5918|8855->5965|8871->5971|8901->5979|8976->6026|8992->6032|9021->6039|9094->6084|9110->6090|9139->6097|9213->6143|9229->6149|9259->6157|9333->6203|9349->6209|9379->6217|9454->6264|9470->6270|9499->6277|9573->6323|9589->6329|9618->6336|9733->6423|9773->6424|9813->6435|9888->6490|9928->6491|9968->6502|10043->6545|10080->6554|10155->6601|10171->6607|10201->6615|10275->6661|10291->6667|10320->6674|10394->6720|10410->6726|10440->6734|10523->6789|10539->6795|10569->6803|10643->6849|10659->6855|10689->6863|10770->6916|10810->6917|10850->6928|10944->6994|10960->7000|10990->7008|11033->7023|11049->7029|11078->7036|11108->7038|11124->7044|11154->7052|11225->7095|11241->7101|11271->7109|11319->7129|11335->7135|11364->7142|11394->7144|11410->7150|11441->7158|11498->7187|11562->7234|11602->7235|11642->7246|11881->7457|11897->7463|11926->7470|11956->7472|11972->7478|12002->7486|12032->7488|12059->7493|12292->7698|12305->7701|12342->7716|12396->7742|12423->7747|12455->7751|12480->7765|12510->7772|12543->7776|12560->7782|12590->7789|12623->7793|12640->7799|12671->7807|12710->7817|12724->7820|12762->7835|12795->7839|12809->7842|12840->7850|12937->7919|12953->7925|12983->7933|13113->8034|13143->8035|13187->8050|13274->8109|13287->8112|13317->8120|13348->8122|13378->8123|13423->8139|13463->8151|13479->8157|13508->8164|13538->8166|13554->8172|13584->8180|13614->8182|13641->8187|13701->8218|13731->8219|13774->8233|13804->8234|13846->8247|13927->8296|13982->8306|14018->8314|14063->8328|14097->8334|14338->8547|14352->8551|14388->8565|14843->8989|14874->8992|14944->9033|14974->9034|15004->9035|15053->9055|15083->9056|15113->9058|15174->9090|15204->9091|15234->9092|15283->9112|15313->9113|15343->9115|15404->9147|15434->9148|15464->9149|15512->9168|15542->9169|15572->9171|15633->9203|15663->9204|15693->9205|15741->9224|15771->9225|15801->9227|15862->9259|15892->9260|15922->9261|15971->9281|16001->9282|16031->9284|16092->9316|16122->9317|16152->9318|16200->9337|16230->9338|16260->9340|16321->9372|16351->9373|16381->9374|16429->9393|16459->9394|16489->9396|16551->9429|16581->9430|16611->9431|16660->9451|16690->9452|16720->9454|16782->9487|16812->9488|16842->9489|16891->9509|16921->9510|16951->9512|17013->9545|17043->9546|17073->9547|17121->9566|17151->9567|17181->9569|17243->9602|17273->9603|17303->9604|17351->9623|17381->9624|17411->9626|17473->9659|17503->9660|17533->9661|17581->9680|17611->9681|17671->9713|17711->9714|17742->9718|17806->9765|17846->9766|17878->9770|17936->9800|17968->9810|17999->9812|18029->9813|18059->9814|18112->9838|18142->9839|18177->9843|18224->9846|18253->9847|18394->9959|18424->9960|18457->9965|18505->9986|18550->10014|18590->10015|18623->10020|18663->10032|18674->10033|18704->10041|18737->10046|18748->10047|18784->10061|18817->10066|18828->10067|18861->10078|18894->10083|18905->10084|18939->10096|18972->10101|18983->10102|19017->10114|19051->10119|19063->10120|19098->10132|19140->10143|19173->10148|19310->10257|19339->10258|19488->10377|19519->10378|19550->10381|20489->11291|20519->11292|20552->11297|20679->11395|20709->11396|20743->11402|20808->11438|20838->11439|20877->11449|21005->11548|21035->11549|21075->11560|21242->11698|21272->11699|21313->11711|22047->12416|22077->12417|22116->12427|22146->12428|22183->12437|22213->12438|22246->12443|22275->12444|22307->12448|22336->12449|22368->12453|22921->12978|22950->12979|22994->12995
                  LINES: 28->1|35->4|37->6|37->6|37->6|39->8|39->8|40->9|41->10|41->10|42->11|53->22|53->22|53->22|63->32|63->32|63->32|73->42|73->42|73->42|83->52|83->52|83->52|88->57|88->57|89->58|90->59|90->59|91->60|94->63|95->64|104->73|104->73|104->73|114->83|114->83|114->83|124->93|124->93|124->93|134->103|134->103|134->103|146->115|146->115|146->115|156->125|156->125|167->136|167->136|167->136|191->160|191->160|192->161|192->161|192->161|193->162|194->163|194->163|194->163|195->164|196->165|197->166|198->167|201->170|201->170|201->170|202->171|203->172|203->172|203->172|204->173|204->173|204->173|205->174|205->174|205->174|206->175|206->175|206->175|207->176|207->176|207->176|208->177|208->177|208->177|209->178|209->178|209->178|210->179|210->179|210->179|212->181|212->181|213->182|214->183|214->183|215->184|216->185|217->186|218->187|218->187|218->187|219->188|219->188|219->188|220->189|220->189|220->189|222->191|222->191|222->191|223->192|223->192|223->192|225->194|225->194|226->195|227->196|227->196|227->196|227->196|227->196|227->196|227->196|227->196|227->196|228->197|228->197|228->197|228->197|228->197|228->197|228->197|228->197|228->197|230->199|230->199|230->199|231->200|234->203|234->203|234->203|234->203|234->203|234->203|234->203|234->203|239->208|239->208|239->208|239->208|239->208|239->208|239->208|239->208|239->208|239->208|239->208|239->208|239->208|239->208|239->208|239->208|239->208|239->208|239->208|239->208|243->212|243->212|243->212|245->214|245->214|246->215|247->216|247->216|247->216|247->216|247->216|248->217|248->217|248->217|248->217|248->217|248->217|248->217|248->217|248->217|249->218|249->218|250->219|250->219|251->220|254->223|255->224|256->225|257->226|258->227|264->233|264->233|264->233|288->257|291->260|292->261|292->261|292->261|292->261|292->261|293->262|293->262|293->262|293->262|293->262|293->262|294->263|294->263|294->263|294->263|294->263|294->263|295->264|295->264|295->264|295->264|295->264|295->264|296->265|296->265|296->265|296->265|296->265|296->265|297->266|297->266|297->266|297->266|297->266|297->266|298->267|298->267|298->267|298->267|298->267|298->267|299->268|299->268|299->268|299->268|299->268|299->268|300->269|300->269|300->269|300->269|300->269|300->269|301->270|301->270|301->270|301->270|301->270|301->270|302->271|302->271|302->271|302->271|302->271|302->271|303->272|303->272|303->272|303->272|303->272|303->272|304->273|304->273|305->274|305->274|305->274|306->275|306->275|306->275|306->275|306->275|306->275|306->275|306->275|307->276|308->277|309->278|314->283|314->283|315->284|316->285|316->285|316->285|317->286|317->286|317->286|317->286|317->286|317->286|317->286|317->286|317->286|317->286|317->286|317->286|317->286|317->286|317->286|317->286|317->286|317->286|317->286|318->287|319->288|321->290|321->290|325->294|325->294|326->295|335->304|335->304|336->305|337->306|337->306|338->307|338->307|338->307|339->308|340->309|340->309|341->310|342->311|342->311|343->312|356->325|356->325|357->326|357->326|358->327|358->327|359->328|359->328|360->329|360->329|361->330|366->335|366->335|376->345
                  -- GENERATED --
              */
          