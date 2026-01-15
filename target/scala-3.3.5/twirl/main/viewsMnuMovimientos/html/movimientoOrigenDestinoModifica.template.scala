
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

object movimientoOrigenDestinoModifica extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template15[Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,List[List[String]],List[tables.TipoEstado],List[tables.TipoReparacion],tables.Guia,List[List[String]],Long,List[tables.Transportista],tables.Sucursal,tables.Comercial,tables.Sucursal,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
bodegaOrigen: tables.BodegaEmpresa, 
listEquipNoEnBodOrigen: List[List[String]], listTipoEstado: List[tables.TipoEstado], listTipoReparacion: List[tables.TipoReparacion],
guia: tables.Guia, detalleMovimiento: List[List[String]], permiteExcedentes: Long,
listaTransporte: List[tables.Transportista], 
sucursalOrigen: tables.Sucursal, comercial: tables.Comercial, sucursalDestino: tables.Sucursal,
sinVenta: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*8.1*/("""
    	 
"""),_display_(/*10.2*/main("")/*10.10*/ {_display_(Seq[Any](format.raw/*10.12*/("""

	"""),_display_(/*12.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*12.51*/("""
	
	"""),format.raw/*14.2*/("""<div id="enCarga" class="blocker" style="display: none;"><br><br><br><br><br><br><h1>En proceso.....<div id="msgEspera"></div></h1></div>
	
	"""),_display_(/*16.3*/modalEquipoDescripcion()),format.raw/*16.27*/("""
	"""),_display_(/*17.3*/modalVerCotizacion()),format.raw/*17.23*/("""

	"""),format.raw/*19.2*/("""<script>
		let auxBueno = -1;
		let auxIndex = -1;
	</script>
	
	<form action="/movimientoAplicarCambios/" enctype="multipart/form-data" method="POST" onsubmit="return validarForm();">
		<div id="mostrarmostrar" style="display:none;">
			"""),_display_(/*26.5*/barraTitulo(mapDiccionario, "MODIFICAR MOVIMIENTO","")),format.raw/*26.59*/("""
			"""),format.raw/*27.4*/("""<div class="row  justify-content-md-center">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<table class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<tr>
								<td width="25%">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Origen</span>
								  		</div>
										<input type="hidden" name="seModifico" id="seModifico" value="0">
								  		<input type="hidden" name="id_bodegaOrigen" value=""""),_display_(/*38.65*/bodegaOrigen/*38.77*/.getId()),format.raw/*38.85*/("""">
								  		<input type="hidden" name="id_guia" value=""""),_display_(/*39.57*/guia/*39.61*/.getId()),format.raw/*39.69*/("""">
	  									<input type="text" class="form-control" 
	  										value=""""),_display_(/*41.22*/bodegaOrigen/*41.34*/.getNombre().toUpperCase),format.raw/*41.58*/("""" 
	  										readonly>
									</div>
								</td>
								<td width="25%">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Destino</span>
								  		</div>
	  										<input type="hidden" name="id_bodegaDestino" id="id_bodegaDestino" value=""""),_display_(/*50.89*/guia/*50.93*/.getId_bodegaDestino()),format.raw/*50.115*/("""">
											<input type="text" class="form-control left"
												id="nombreBodegaDestino"
												value=""""),_display_(/*53.21*/guia/*53.25*/.getBodegaDestino().toUpperCase),format.raw/*53.56*/(""""
												required
												readonly>
									</div>
								</td>
								<td>
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Nro.Mov</span>
								  		</div>
											"""),_display_(if(mapPermiso.get("parametro.bloqueoNrosMovimientos")!=null && mapPermiso.get("parametro.bloqueoNrosMovimientos").equals("1"))/*63.139*/{_display_(Seq[Any](format.raw/*63.140*/("""
												"""),format.raw/*64.13*/("""<input type="text" class="form-control left"
											  				name="numeroGuia"
															id="numeroGuia"
															value = """"),_display_(/*67.26*/guia/*67.30*/.getNumero()),format.raw/*67.42*/(""""
															readonly
															required>
											""")))}else/*70.17*/{_display_(Seq[Any](format.raw/*70.18*/("""
												"""),format.raw/*71.13*/("""<input type="text" class="form-control left"
											  				name="numeroGuia"
															id="numeroGuia"
															value = """"),_display_(/*74.26*/guia/*74.30*/.getNumero()),format.raw/*74.42*/(""""
															onkeydown="return ingresoInt(window.event)"
															onchange="verificarNumeroGuia(value)"
															autocomplete="off"
															required>
											""")))}),format.raw/*79.13*/("""
									"""),format.raw/*80.10*/("""</div>
								</td>
								<td>
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Nro.Ref</span>
								  		</div>
								  		<input type="text" class="form-control left"
								  				name="numGuiaCliente"
								  				value=""""),_display_(/*89.23*/guia/*89.27*/.getNumGuiaCliente()),format.raw/*89.47*/(""""
												onchange="$('#seModifico').val(1);"
								  				autocomplete="off"
								  				maxlength="50">
									</div>
								</td>
								<td rowspan="2" style="width: 50px; min-width: 50px; max-width: 50px; text-align: center; vertical-align: middle">
									<span class="btn btn-info btn-sm btn-file" style="font-size: 10px; display: inline-block">
									"""),_display_(if(guia.getDocAnexo().equals("0"))/*97.45*/{_display_(Seq[Any](format.raw/*97.46*/("""
										"""),format.raw/*98.11*/("""<input type="hidden" name="docAnexo" value="0">
										<div id="txtBtn">Subir Documento</div>
										<input type="file" multiple id="docAdjunto" name="docAdjunto[]" title="Adjuntar documentos"
										onchange="LimitAttach(this.form, this.form.docAdjunto.value); $('#seModifico').val(1);">
									""")))}else/*102.15*/{_display_(Seq[Any](format.raw/*102.16*/("""
										"""),format.raw/*103.11*/("""<input type="hidden" name="docAnexo" value=""""),_display_(/*103.56*/guia/*103.60*/.getDocAnexo()),format.raw/*103.74*/("""">
										<div id="txtBtn">Cambiar Documento</div>
										<input type="file" multiple id="docAdjunto" name="docAdjunto[]" title="Cabiar documentos" onchange="LimitAttach(this.form, this.form.docAdjunto.value); $('#seModifico').val(1);">
										""")))}),format.raw/*106.12*/("""
									"""),format.raw/*107.10*/("""</span>
								</td>
								<td rowspan="2" style="width: 50px; min-width: 50px; max-width: 50px; text-align: center; vertical-align: middle">
									"""),_display_(if(guia.getDocAnexo().equals("0"))/*110.45*/{_display_(Seq[Any](format.raw/*110.46*/("""
										"""),format.raw/*111.11*/("""--
									""")))}else/*112.15*/{_display_(Seq[Any](format.raw/*112.16*/("""
										"""),format.raw/*113.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*113.52*/guia/*113.56*/.getDocAnexo()),format.raw/*113.70*/("""')">
											<kbd style="background-color: #7F8C8D">doc</kbd>
										</a>
									""")))}),format.raw/*116.11*/("""
								"""),format.raw/*117.9*/("""</td>
							</tr>

							<tr>
								<td align="center">
									<input type="hidden" id="id_sucursalOrigen" name="id_sucursalOrigen" value=""""),_display_(/*122.87*/sucursalOrigen/*122.101*/.getId()),format.raw/*122.109*/("""">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Sucursal</span>
								  		</div>
											<input type="text" class="form-control left"
												value = """"),_display_(/*128.23*/sucursalOrigen/*128.37*/.getNombre()),format.raw/*128.49*/(""""
												readonly>
									</div>
								</td>
								<td align="center">
									<input type="hidden" id="id_sucursalDestino" name="id_sucursalDestino" value=""""),_display_(/*133.89*/sucursalDestino/*133.104*/.getId()),format.raw/*133.112*/("""">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Sucursal</span>
								  		</div>
											<input type="text" class="form-control left"
												value = """"),_display_(/*139.23*/sucursalDestino/*139.38*/.getNombre()),format.raw/*139.50*/(""""
												readonly>
									</div>
								</td>
								<td width="230px">
									<div class="input-group">
										<div class="input-group-prepend">
											<span class="input-group-text" id="basic-addon1">Fecha Movimiento</span>
										</div>
										<input type="date" class="form-control center"
										name="fechaGuia"
										id="fechaGuia"
										onblur="if(!limitaFecha(value,"""),_display_(/*151.42*/mapPermiso/*151.52*/.get("parametro.diasMenosGuia")),format.raw/*151.83*/(""","""),_display_(/*151.85*/mapPermiso/*151.95*/.get("parametro.diasMasGuia")),format.raw/*151.124*/(""")) value='"""),_display_(/*151.135*/utilities/*151.144*/.Fechas.AAMMDD(guia.getFecha())),format.raw/*151.175*/("""'; else $('#seModifico').val(1);"
										value=""""),_display_(/*152.19*/utilities/*152.28*/.Fechas.AAMMDD(guia.getFecha())),format.raw/*152.59*/(""""
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
										onblur="if(!limitaFecha(value,"""),_display_(/*165.42*/mapPermiso/*165.52*/.get("parametro.diasMenosIniTer")),format.raw/*165.85*/(""","""),_display_(/*165.87*/mapPermiso/*165.97*/.get("parametro.diasMasIniTer")),format.raw/*165.128*/(""")) value='"""),_display_(/*165.139*/utilities/*165.148*/.Fechas.AAMMDD(guia.getFechaIniTerGuia())),format.raw/*165.189*/("""'; else $('#seModifico').val(1);"
										value=""""),_display_(/*166.19*/utilities/*166.28*/.Fechas.AAMMDD(guia.getFechaIniTerGuia())),format.raw/*166.69*/(""""
										required>
									</div>
								</td>
							</tr>
							<tr>
								<td align="center">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Comercial</span>
								  		</div>
											<input type="text" class="form-control left"
												value = """"),_display_(/*178.23*/comercial/*178.32*/.getNameUsuario()),format.raw/*178.49*/(""""
												readonly>
									</div>
								</td>
								<td align="center">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Cliente</span>
								  		</div>
											<input type="text" class="form-control left"
												id = "nickCliente"
												value = ""
												readonly>
									</div>
								</td>
								<td colspan="2" rowspan="2">
									<div class="input-group">
										<div class="input-group-prepend">
											<span class="input-group-text" id="basic-addon1">Observaciones</span>
										</div>
										<textarea class="form-control" rows="2" onchange="$('#seModifico').val(1);"
										name="observaciones"
										autocomplete="off">"""),_display_(/*200.31*/guia/*200.35*/.getObservaciones()),format.raw/*200.54*/("""</textarea>
									</div>
								</td>
								<td colspan="2" rowspan="3" style="text-align:center;" width="70px" id="sube1">
									"""),_display_(if(guia.getFotos().equals("0"))/*204.42*/{_display_(Seq[Any](format.raw/*204.43*/("""
										"""),format.raw/*205.11*/("""<div id="cargadasFotos">--</div>
									""")))}else/*206.15*/{_display_(Seq[Any](format.raw/*206.16*/("""
										"""),format.raw/*207.11*/("""<a href="/muestraAlbumFotos/"""),_display_(/*207.40*/guia/*207.44*/.getFotos()),format.raw/*207.55*/("""">
											<kbd style="background-color: #7F8C8D">Ver Album</kbd>
										</a>
									""")))}),format.raw/*210.11*/("""
								"""),format.raw/*211.9*/("""</td>
							</tr>
							
							
						</thead>
					</table>
					<hr>
					<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<TR>
								<TH>GRUPO</TH>
								<TH>Nro."""),_display_(/*222.18*/mapDiccionario/*222.32*/.get("OT")),format.raw/*222.42*/("""</TH>
								<TH with="80px">Fecha<br>"""),_display_(/*223.35*/mapDiccionario/*223.49*/.get("OT")),format.raw/*223.59*/("""</TH>
								<TH>Nro.COTI</TH>
								<TH>CODIGO</TH>
								<TH>EQUIPO</TH>
								<TH>KG</TH>
						        <TH>M2</TH>
						        <TH>UN</TH>
								<TH>STOCK</TH>
								<TH>SALIDA</TH>
								
								"""),_display_(if(sinVenta.equals("1"))/*233.34*/{_display_(Seq[Any](format.raw/*233.35*/("""
									"""),format.raw/*234.10*/("""<TH style="display:none">VENTA</TH>
								""")))}else/*235.14*/{_display_(Seq[Any](format.raw/*235.15*/("""
									"""),format.raw/*236.10*/("""<TH>VENTA</TH>
								""")))}),format.raw/*237.10*/("""
								
								"""),format.raw/*239.9*/("""<TH style="display:none"></TH>
								<TH>EXCESO</TH>
								<TH>TOT.KG</TH>
								<TH>TOT.M2</TH>
								"""),_display_(if(bodegaOrigen.esInterna!=1)/*243.39*/{_display_(Seq[Any](format.raw/*243.40*/("""
									"""),format.raw/*244.10*/("""<th style="display:none"></th>
									"""),_display_(/*245.11*/for(titEstado <- listTipoEstado) yield /*245.43*/{_display_(Seq[Any](format.raw/*245.44*/("""
										"""),format.raw/*246.11*/("""<TH style="font-size:10px; vertical-align:middle">
											"""),_display_(/*247.13*/titEstado/*247.22*/.getSigla()),format.raw/*247.33*/("""
										"""),format.raw/*248.11*/("""</TH>
									""")))}),format.raw/*249.11*/("""
									"""),_display_(if(mapPermiso.get("parametro.movimiento-devolucion-cantCliente")!=null && mapPermiso.get("parametro.movimiento-devolucion-cantCliente").equals("1"))/*250.159*/{_display_(Seq[Any](format.raw/*250.160*/("""
										"""),format.raw/*251.11*/("""<TH style="text-align:center;">cliente</TH>
										<TH style="text-align:center;">diferen</TH>
									""")))} else {null} ),format.raw/*253.11*/("""
									
								""")))} else {null} ),format.raw/*255.10*/("""
							"""),format.raw/*256.8*/("""</TR>
						</thead>
						<tbody>
							"""),_display_(/*259.9*/for(lista1 <- detalleMovimiento) yield /*259.41*/{_display_(Seq[Any](format.raw/*259.42*/("""
								"""),format.raw/*260.9*/("""<TR>
									<td  style="text-align:left;">
										<input type="hidden" name="id_equipo[]" value=""""),_display_(/*262.59*/lista1/*262.65*/.get(0)),format.raw/*262.72*/("""">
										<input type="hidden" name="id_cotizacion[]" value=""""),_display_(/*263.63*/lista1/*263.69*/.get(1)),format.raw/*263.76*/("""">
										"""),_display_(/*264.12*/lista1/*264.18*/.get(2)),format.raw/*264.25*/("""
									"""),format.raw/*265.10*/("""</td>
									
									"""),_display_(if(lista1.get(20).equals("0") || lista1.get(20).equals(""))/*267.70*/{_display_(Seq[Any](format.raw/*267.71*/("""
										"""),format.raw/*268.11*/("""<td style="text-align: center;">--</td>
									""")))}else/*269.15*/{_display_(Seq[Any](format.raw/*269.16*/("""
										"""),format.raw/*270.11*/("""<td style="text-align:center;" class="sorting_2">
											<a href="#" onclick="verOt('"""),_display_(/*271.41*/lista1/*271.47*/.get(20)),format.raw/*271.55*/("""')">
												<kbd style="background-color: rgb(90, 200, 245)">
													<font color="green">"""),_display_(/*273.35*/lista1/*273.41*/.get(21)),format.raw/*273.49*/("""</font>
												</kbd>
											</a>
										</td>
									""")))}),format.raw/*277.11*/("""
									
									"""),format.raw/*279.10*/("""<td style="text-align: center;">
										<div style="display: none;">"""),_display_(/*280.40*/lista1/*280.46*/.get(22)),format.raw/*280.54*/("""</div>
										"""),_display_(/*281.12*/utilities/*281.21*/.Fechas.DDMMAA(lista1.get(22))),format.raw/*281.51*/("""
									"""),format.raw/*282.10*/("""</td>
									
									"""),_display_(if(lista1.get(3).equals("0") || lista1.get(3).equals(""))/*284.68*/{_display_(Seq[Any](format.raw/*284.69*/("""
										"""),format.raw/*285.11*/("""<td style="text-align: center;">--</td>
									""")))}else/*286.15*/{_display_(Seq[Any](format.raw/*286.16*/("""
										"""),format.raw/*287.11*/("""<td style="text-align: center;">"""),_display_(/*287.44*/lista1/*287.50*/.get(3)),format.raw/*287.57*/("""</td>
									""")))}),format.raw/*288.11*/("""
									
									"""),format.raw/*290.10*/("""<td  style="text-align:left;"><a href="#" onclick="buscaEquipo('"""),_display_(/*290.75*/lista1/*290.81*/.get(4)),format.raw/*290.88*/("""');">"""),_display_(/*290.94*/lista1/*290.100*/.get(4)),format.raw/*290.107*/("""</a></td>
									<td  style="text-align:left;"><a href="#" onclick="buscaEquipo('"""),_display_(/*291.75*/lista1/*291.81*/.get(4)),format.raw/*291.88*/("""');">"""),_display_(/*291.94*/lista1/*291.100*/.get(5)),format.raw/*291.107*/("""</a></td>
								
						
									<td  style="text-align:right;">"""),_display_(/*294.42*/lista1/*294.48*/.get(6)),format.raw/*294.55*/("""</td>
									<td  style="text-align:right;">"""),_display_(/*295.42*/lista1/*295.48*/.get(7)),format.raw/*295.55*/("""</td>
									<td  style="text-align:center;">"""),_display_(/*296.43*/lista1/*296.49*/.get(8)),format.raw/*296.56*/("""</td>
									<td  style="text-align:right;">
										<div class="verificaStock" id="stock_"""),_display_(/*298.49*/lista1/*298.55*/.get(0)),format.raw/*298.62*/("""_"""),_display_(/*298.64*/lista1/*298.70*/.get(1)),format.raw/*298.77*/("""">"""),_display_(/*298.80*/lista1/*298.86*/.get(9)),format.raw/*298.93*/("""</div>
									</td>
									<td  style="text-align:center;">
										<div style='display:none'>"""),_display_(/*301.38*/lista1/*301.44*/.get(10)),format.raw/*301.52*/("""</div>
										<div align="center">
											<input type="text" class="cantidad form-control height23px right width80px"
												name="cantidad[]"
												id="cantidad_"""),_display_(/*305.27*/lista1/*305.33*/.get(0)),format.raw/*305.40*/("""_"""),_display_(/*305.42*/lista1/*305.48*/.get(1)),format.raw/*305.55*/(""""
												value=""""),_display_(/*306.21*/lista1/*306.27*/.get(10)),format.raw/*306.35*/(""""
												onfocus="value=value.replace(/,/g,''); cantAux = value;" 
												onkeydown="return ingresoDouble(window.event)"
												autocomplete="off"
												onchange="if(value=='') value=0; verificaCantidad('"""),_display_(/*310.65*/lista1/*310.71*/.get(0)),format.raw/*310.78*/("""','"""),_display_(/*310.82*/lista1/*310.88*/.get(1)),format.raw/*310.95*/("""',value,'"""),_display_(/*310.105*/lista1/*310.111*/.get(6)),format.raw/*310.118*/("""','"""),_display_(/*310.122*/lista1/*310.128*/.get(7)),format.raw/*310.135*/("""','"""),_display_(/*310.139*/listTipoEstado/*310.153*/.size()),format.raw/*310.160*/("""');">
										</div>
									</td>
									"""),_display_(if(sinVenta.equals("1"))/*313.35*/{_display_(Seq[Any](format.raw/*313.36*/("""
										"""),format.raw/*314.11*/("""<td  style="text-align:center; display:none;">
											<input type="hidden" name="esVenta[]" id="esVenta_"""),_display_(/*315.63*/lista1/*315.69*/.get(0)),format.raw/*315.76*/("""_"""),_display_(/*315.78*/lista1/*315.84*/.get(1)),format.raw/*315.91*/("""" value=""""),_display_(/*315.101*/lista1/*315.107*/.get(11)),format.raw/*315.115*/("""">
											"""),_display_(if(lista1.get(11).equals("1"))/*316.43*/{_display_(Seq[Any](format.raw/*316.44*/("""
												"""),format.raw/*317.13*/("""<input type="checkbox" checked onchange='if($("#esVenta_"""),_display_(/*317.70*/lista1/*317.76*/.get(0)),format.raw/*317.83*/("""_"""),_display_(/*317.85*/lista1/*317.91*/.get(1)),format.raw/*317.98*/("""").val()==1) 
												$("#esVenta_"""),_display_(/*318.26*/lista1/*318.32*/.get(0)),format.raw/*318.39*/("""_"""),_display_(/*318.41*/lista1/*318.47*/.get(1)),format.raw/*318.54*/("""").val(0); else $("#esVenta_"""),_display_(/*318.83*/lista1/*318.89*/.get(0)),format.raw/*318.96*/("""_"""),_display_(/*318.98*/lista1/*318.104*/.get(1)),format.raw/*318.111*/("""").val(1); $("#seModifico").val(1)'>
											""")))}else/*319.17*/{_display_(Seq[Any](format.raw/*319.18*/("""
												"""),format.raw/*320.13*/("""<input type="checkbox" onchange='if($("#esVenta_"""),_display_(/*320.62*/lista1/*320.68*/.get(0)),format.raw/*320.75*/("""_"""),_display_(/*320.77*/lista1/*320.83*/.get(1)),format.raw/*320.90*/("""").val()==1) 
												$("#esVenta_"""),_display_(/*321.26*/lista1/*321.32*/.get(0)),format.raw/*321.39*/("""_"""),_display_(/*321.41*/lista1/*321.47*/.get(1)),format.raw/*321.54*/("""").val(0); else $("#esVenta_"""),_display_(/*321.83*/lista1/*321.89*/.get(0)),format.raw/*321.96*/("""_"""),_display_(/*321.98*/lista1/*321.104*/.get(1)),format.raw/*321.111*/("""").val(1); $("#seModifico").val(1)'>
											""")))}),format.raw/*322.13*/("""
										"""),format.raw/*323.11*/("""</td>
									""")))}else/*324.15*/{_display_(Seq[Any](format.raw/*324.16*/("""
										"""),format.raw/*325.11*/("""<td  style="text-align:center;">
											<input type="hidden" name="esVenta[]" id="esVenta_"""),_display_(/*326.63*/lista1/*326.69*/.get(0)),format.raw/*326.76*/("""_"""),_display_(/*326.78*/lista1/*326.84*/.get(1)),format.raw/*326.91*/("""" value=""""),_display_(/*326.101*/lista1/*326.107*/.get(11)),format.raw/*326.115*/("""">
											"""),_display_(if(lista1.get(11).equals("1"))/*327.43*/{_display_(Seq[Any](format.raw/*327.44*/("""
												"""),format.raw/*328.13*/("""<input type="checkbox" checked onchange='if($("#esVenta_"""),_display_(/*328.70*/lista1/*328.76*/.get(0)),format.raw/*328.83*/("""_"""),_display_(/*328.85*/lista1/*328.91*/.get(1)),format.raw/*328.98*/("""").val()==1) 
												$("#esVenta_"""),_display_(/*329.26*/lista1/*329.32*/.get(0)),format.raw/*329.39*/("""_"""),_display_(/*329.41*/lista1/*329.47*/.get(1)),format.raw/*329.54*/("""").val(0); else $("#esVenta_"""),_display_(/*329.83*/lista1/*329.89*/.get(0)),format.raw/*329.96*/("""_"""),_display_(/*329.98*/lista1/*329.104*/.get(1)),format.raw/*329.111*/("""").val(1); $("#seModifico").val(1)'>
											""")))}else/*330.17*/{_display_(Seq[Any](format.raw/*330.18*/("""
												"""),format.raw/*331.13*/("""<input type="checkbox" onchange='if($("#esVenta_"""),_display_(/*331.62*/lista1/*331.68*/.get(0)),format.raw/*331.75*/("""_"""),_display_(/*331.77*/lista1/*331.83*/.get(1)),format.raw/*331.90*/("""").val()==1) 
												$("#esVenta_"""),_display_(/*332.26*/lista1/*332.32*/.get(0)),format.raw/*332.39*/("""_"""),_display_(/*332.41*/lista1/*332.47*/.get(1)),format.raw/*332.54*/("""").val(0); else $("#esVenta_"""),_display_(/*332.83*/lista1/*332.89*/.get(0)),format.raw/*332.96*/("""_"""),_display_(/*332.98*/lista1/*332.104*/.get(1)),format.raw/*332.111*/("""").val(1); $("#seModifico").val(1)'>
											""")))}),format.raw/*333.13*/("""
										"""),format.raw/*334.11*/("""</td>
									""")))}),format.raw/*335.11*/("""
									
									"""),format.raw/*337.10*/("""<td width="1px" style="text-align:center; display:none">
										<input type="hidden" name="esNuevo[]" id="esNuevo_"""),_display_(/*338.62*/lista1/*338.68*/.get(0)),format.raw/*338.75*/("""_"""),_display_(/*338.77*/lista1/*338.83*/.get(1)),format.raw/*338.90*/("""" value=""""),_display_(/*338.100*/lista1/*338.106*/.get(12)),format.raw/*338.114*/("""">
									</td>
									<td style="text-align:right;">
										<div style='display:none'>"""),_display_(/*341.38*/lista1/*341.44*/.get(13)),format.raw/*341.52*/("""</div>
										<div align="center">
											<input type="text" class="exceso form-control height23px right width80px"
												name="exceso[]"
												value=""""),_display_(/*345.21*/lista1/*345.27*/.get(13)),format.raw/*345.35*/(""""
												id="exceso_"""),_display_(/*346.25*/lista1/*346.31*/.get(0)),format.raw/*346.38*/("""_"""),_display_(/*346.40*/lista1/*346.46*/.get(1)),format.raw/*346.53*/(""""
												value="0.00"
												readonly>
										</div>
									</td>
									<td style="text-align:right;"><div class="KG" id="KG_"""),_display_(/*351.64*/lista1/*351.70*/.get(0)),format.raw/*351.77*/("""_"""),_display_(/*351.79*/lista1/*351.85*/.get(1)),format.raw/*351.92*/("""">"""),_display_(/*351.95*/lista1/*351.101*/.get(14)),format.raw/*351.109*/("""</div></td>
									<td style="text-align:right;"><div class="M2" id="M2_"""),_display_(/*352.64*/lista1/*352.70*/.get(0)),format.raw/*352.77*/("""_"""),_display_(/*352.79*/lista1/*352.85*/.get(1)),format.raw/*352.92*/("""">"""),_display_(/*352.95*/lista1/*352.101*/.get(15)),format.raw/*352.109*/("""</div></td>
									"""),_display_(if(bodegaOrigen.esInterna!=1)/*353.40*/{_display_(Seq[Any](format.raw/*353.41*/("""
										"""),format.raw/*354.11*/("""<td style="display:none">
											<input type="hidden" name="estados[]" value=""""),_display_(/*355.58*/lista1/*355.64*/.get(16)),format.raw/*355.72*/("""" id="estados_"""),_display_(/*355.87*/lista1/*355.93*/.get(0)),format.raw/*355.100*/("""_"""),_display_(/*355.102*/lista1/*355.108*/.get(1)),format.raw/*355.115*/("""">
											<input type="hidden" name="reparaciones[]" value=""""),_display_(/*356.63*/lista1/*356.69*/.get(17)),format.raw/*356.77*/("""" id="reparaciones_"""),_display_(/*356.97*/lista1/*356.103*/.get(0)),format.raw/*356.110*/("""_"""),_display_(/*356.112*/lista1/*356.118*/.get(1)),format.raw/*356.125*/("""">
										</td>
										"""),_display_(/*358.12*/for((est,index) <- listTipoEstado.zipWithIndex) yield /*358.59*/{_display_(Seq[Any](format.raw/*358.60*/("""
											"""),format.raw/*359.12*/("""<td style="font-size:10px; vertical-align:middle">
												"""),_display_(if(est.getId() == 999)/*360.36*/{_display_(Seq[Any](format.raw/*360.37*/("""
													"""),format.raw/*361.14*/("""<input type="text" class="form-control height23px right width60px"
														style="font-size:10px; vertical-align:middle"
														id="estado_"""),_display_(/*363.27*/lista1/*363.33*/.get(0)),format.raw/*363.40*/("""_"""),_display_(/*363.42*/lista1/*363.48*/.get(1)),format.raw/*363.55*/("""_"""),_display_(/*363.57*/index),format.raw/*363.62*/(""""
														value="0.00"
														readonly>
													<script>
														auxBueno = 999;
														auxIndex = """"),_display_(/*368.28*/index),format.raw/*368.33*/("""";
														niv = """"),_display_(/*369.23*/lista1/*369.29*/.get(16)),format.raw/*369.37*/("""";
														niv1 = niv.split(";");
														for(let i=0; i<niv1.length && niv1.length>1; i++)"""),format.raw/*371.64*/("""{"""),format.raw/*371.65*/("""
															"""),format.raw/*372.16*/("""let niv2 = niv1[i].split(":");
															if(niv2[0]==""""),_display_(/*373.30*/est/*373.33*/.getId()),format.raw/*373.41*/("""")"""),format.raw/*373.43*/("""{"""),format.raw/*373.44*/("""
																"""),format.raw/*374.17*/("""$("#estado_"""),_display_(/*374.29*/lista1/*374.35*/.get(0)),format.raw/*374.42*/("""_"""),_display_(/*374.44*/lista1/*374.50*/.get(1)),format.raw/*374.57*/("""_"""),_display_(/*374.59*/index),format.raw/*374.64*/("""").val(formatStandar(niv2[1],2));
															"""),format.raw/*375.16*/("""}"""),format.raw/*375.17*/("""
														"""),format.raw/*376.15*/("""}"""),format.raw/*376.16*/("""
													"""),format.raw/*377.14*/("""</script>
													
												""")))}else if( mapDiccionario.get("nEmpresa").equals("HOHE") && ( est.getId() == 2 || est.getId() == 3 ) )/*379.114*/{_display_(Seq[Any](format.raw/*379.115*/("""
													"""),format.raw/*380.14*/("""<input type="text" class="form-control height23px right width60px"
														style="font-size:10px; vertical-align:middle"
														id="estado_"""),_display_(/*382.27*/lista1/*382.33*/.get(0)),format.raw/*382.40*/("""_"""),_display_(/*382.42*/lista1/*382.48*/.get(1)),format.raw/*382.55*/("""_"""),_display_(/*382.57*/index),format.raw/*382.62*/(""""
														value="0.00"
														readonly>
													<script>
														auxBueno = """"),_display_(/*386.28*/est/*386.31*/.getId()),format.raw/*386.39*/("""";
														auxIndex = """"),_display_(/*387.28*/index),format.raw/*387.33*/("""";
														niv = """"),_display_(/*388.23*/lista1/*388.29*/.get(16)),format.raw/*388.37*/("""";
														niv1 = niv.split(";");
														for(let i=0; i<niv1.length && niv1.length>1; i++)"""),format.raw/*390.64*/("""{"""),format.raw/*390.65*/("""
															"""),format.raw/*391.16*/("""let niv2 = niv1[i].split(":");
															if(niv2[0]==""""),_display_(/*392.30*/est/*392.33*/.getId()),format.raw/*392.41*/("""")"""),format.raw/*392.43*/("""{"""),format.raw/*392.44*/("""
																"""),format.raw/*393.17*/("""$("#estado_"""),_display_(/*393.29*/lista1/*393.35*/.get(0)),format.raw/*393.42*/("""_"""),_display_(/*393.44*/lista1/*393.50*/.get(1)),format.raw/*393.57*/("""_"""),_display_(/*393.59*/index),format.raw/*393.64*/("""").val(formatStandar(niv2[1],2));
															"""),format.raw/*394.16*/("""}"""),format.raw/*394.17*/("""
														"""),format.raw/*395.15*/("""}"""),format.raw/*395.16*/("""
													"""),format.raw/*396.14*/("""</script>
													
												""")))}else/*398.18*/{_display_(Seq[Any](format.raw/*398.19*/("""
													"""),format.raw/*399.14*/("""<input type="text" class="form-control height23px right width60px"
														style="font-size:10px; vertical-align:middle"
														id="estado_"""),_display_(/*401.27*/lista1/*401.33*/.get(0)),format.raw/*401.40*/("""_"""),_display_(/*401.42*/lista1/*401.48*/.get(1)),format.raw/*401.55*/("""_"""),_display_(/*401.57*/index),format.raw/*401.62*/(""""
														value="0.00"
														onfocus="value=value.replace(/,/g,'')" 
														onkeydown="return ingresoDouble(window.event)"
														autocomplete="off"
														ondblclick = "if(value>0 && '"""),_display_(/*406.45*/est/*406.48*/.getReparable()),format.raw/*406.63*/("""'=='1') editaReparaciones('"""),_display_(/*406.91*/index),format.raw/*406.96*/("""','"""),_display_(/*406.100*/listTipoEstado/*406.114*/.size()),format.raw/*406.121*/("""','"""),_display_(/*406.125*/lista1/*406.131*/.get(0)),format.raw/*406.138*/("""','"""),_display_(/*406.142*/lista1/*406.148*/.get(1)),format.raw/*406.155*/("""',value,'"""),_display_(/*406.165*/est/*406.168*/.getReparable()),format.raw/*406.183*/("""','"""),_display_(/*406.187*/est/*406.190*/.getId()),format.raw/*406.198*/("""')"
														onchange="if(value=='') """),format.raw/*407.39*/("""{"""),format.raw/*407.40*/("""
																		"""),format.raw/*408.19*/("""value='0.00';
																		verificaCantEstado('"""),_display_(/*409.40*/index),format.raw/*409.45*/("""','"""),_display_(/*409.49*/listTipoEstado/*409.63*/.size()),format.raw/*409.70*/("""','"""),_display_(/*409.74*/lista1/*409.80*/.get(0)),format.raw/*409.87*/("""','"""),_display_(/*409.91*/lista1/*409.97*/.get(1)),format.raw/*409.104*/("""',value,'"""),_display_(/*409.114*/est/*409.117*/.getReparable()),format.raw/*409.132*/("""','"""),_display_(/*409.136*/est/*409.139*/.getId()),format.raw/*409.147*/("""');
																  """),format.raw/*410.19*/("""}"""),format.raw/*410.20*/("""else"""),format.raw/*410.24*/("""{"""),format.raw/*410.25*/(""" 
																		"""),format.raw/*411.19*/("""verificaCantEstado('"""),_display_(/*411.40*/index),format.raw/*411.45*/("""','"""),_display_(/*411.49*/listTipoEstado/*411.63*/.size()),format.raw/*411.70*/("""','"""),_display_(/*411.74*/lista1/*411.80*/.get(0)),format.raw/*411.87*/("""','"""),_display_(/*411.91*/lista1/*411.97*/.get(1)),format.raw/*411.104*/("""',value,'"""),_display_(/*411.114*/est/*411.117*/.getReparable()),format.raw/*411.132*/("""','"""),_display_(/*411.136*/est/*411.139*/.getId()),format.raw/*411.147*/("""');
																  """),format.raw/*412.19*/("""}"""),format.raw/*412.20*/("""">
													<script>
														niv = """"),_display_(/*414.23*/lista1/*414.29*/.get(16)),format.raw/*414.37*/("""";
														niv1 = niv.split(";");
														for(let i=0; i<niv1.length && niv1.length>1; i++)"""),format.raw/*416.64*/("""{"""),format.raw/*416.65*/("""
															"""),format.raw/*417.16*/("""let niv2 = niv1[i].split(":");
															if(niv2[0]==""""),_display_(/*418.30*/est/*418.33*/.getId()),format.raw/*418.41*/("""")"""),format.raw/*418.43*/("""{"""),format.raw/*418.44*/("""
																"""),format.raw/*419.17*/("""$("#estado_"""),_display_(/*419.29*/lista1/*419.35*/.get(0)),format.raw/*419.42*/("""_"""),_display_(/*419.44*/lista1/*419.50*/.get(1)),format.raw/*419.57*/("""_"""),_display_(/*419.59*/index),format.raw/*419.64*/("""").val(formatStandar(niv2[1],2));
															"""),format.raw/*420.16*/("""}"""),format.raw/*420.17*/("""
														"""),format.raw/*421.15*/("""}"""),format.raw/*421.16*/("""
													"""),format.raw/*422.14*/("""</script>
												""")))}),format.raw/*423.14*/("""
											"""),format.raw/*424.12*/("""</td>
										""")))}),format.raw/*425.12*/("""
										"""),_display_(if(mapPermiso.get("parametro.movimiento-devolucion-cantCliente")!=null && mapPermiso.get("parametro.movimiento-devolucion-cantCliente").equals("1"))/*426.160*/{_display_(Seq[Any](format.raw/*426.161*/("""
											"""),format.raw/*427.12*/("""<td  style="text-align:center;">
												<div align="center">
													<input type="text" class="cantCliente form-control height23px right width80px"
														name="cantCliente[]"
														id="cantCliente_"""),_display_(/*431.32*/lista1/*431.38*/.get(0)),format.raw/*431.45*/("""_"""),_display_(/*431.47*/lista1/*431.53*/.get(1)),format.raw/*431.60*/(""""
														value=""""),_display_(/*432.23*/lista1/*432.29*/.get(18)),format.raw/*432.37*/(""""
														onfocus="value=value.replace(/,/g,''); cantAux = value;" 
														onkeydown="return ingresoDouble(window.event)"
														autocomplete="off"
														onchange="if(value=='') value=0; actualizaCantCliente('"""),_display_(/*436.71*/lista1/*436.77*/.get(0)),format.raw/*436.84*/("""', '"""),_display_(/*436.89*/lista1/*436.95*/.get(1)),format.raw/*436.102*/("""')">
												</div>
											</td>
											<td style="text-align:right;"><div class="difCliente" id="difCliente_"""),_display_(/*439.82*/lista1/*439.88*/.get(0)),format.raw/*439.95*/("""_"""),_display_(/*439.97*/lista1/*439.103*/.get(1)),format.raw/*439.110*/("""">"""),_display_(/*439.113*/lista1/*439.119*/.get(19)),format.raw/*439.127*/("""</div></td>
										""")))} else {null} ),format.raw/*440.12*/("""
									""")))} else {null} ),format.raw/*441.11*/("""
								"""),format.raw/*442.9*/("""</TR>
				 			""")))}),format.raw/*443.10*/("""
						"""),format.raw/*444.7*/("""</tbody>
					</table>
				</div>
			</div>
			
			
			
			<div class="noprint" align="left">
				<div class="row justify-content-md-left" >
					<div class="col-xs-5 col-sm-3 col-md-3 col-lg-3">
						"""),_display_(if(bodegaOrigen.esInterna != 1)/*454.39*/{_display_(Seq[Any](format.raw/*454.40*/("""
							"""),format.raw/*455.8*/("""<input type="button"  id="agregarEquiposNoEnviados" value="Agregar equipos que no fueron enviados" 
								class="btn btn-warning btn-sm rounded-pill btn-block" 
								onclick='$("#equipNoEnBodOrigen").modal("show")'>
						""")))} else {null} ),format.raw/*458.8*/("""
					"""),format.raw/*459.6*/("""</div>
				</div>
			

				<div class="row justify-content-md-center" >
					<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
						<input type="button"  id="transporte" value='Agregar Transportista' class="btn btn-info btn-sm rounded-pill btn-block" onclick="selectTransporte('"""),_display_(/*465.154*/guia/*465.158*/.getId()),format.raw/*465.166*/("""');" >
					</div>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
						<input type="button"  id="verifica" value='Verificar' class="btn btn-success btn-sm rounded-pill btn-block" onclick="verificaMovimientos();" >
					</div>
					<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
						<input id="cancelar" type="button"  value="Cancelar" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
					</div>
					<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
						<input type="submit"  id="aplica" value='Confirmar' class="btn btn-primary btn-sm rounded-pill btn-block" style="visibility:hidden" 
							onclick="
								document.getElementById('aplica').style.visibility = 'hidden';
								document.getElementById('verifica').style.visibility = 'hidden';
								document.getElementById('modifica').style.visibility = 'hidden';
								document.getElementById('aplica').style.visibility = 'hidden';
								document.getElementById('cancelar').style.visibility = 'hidden';
								document.getElementById('transporte').style.visibility = 'hidden';
							">
					</div>
					<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
						<input type="button"  id="modifica" value='Modificar' class="btn btn-warning btn-sm rounded-pill btn-block" onclick="modificaMovimientos();" style="visibility:hidden">
					</div>
				</div>
			</div>
		</div>
		<input type="hidden" id="id_transportistaXLSX" name="id_transportista" value=""""),_display_(/*491.82*/guia/*491.86*/.getId_transportista()),format.raw/*491.108*/("""">
	</form>
	
	<div id='equipNoEnBodOrigen' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>SELECCIONAR EQUIPO NO ENVIADO</h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<table id="tablaListaEquipNoEnBodOrigen" class='table table-sm table-bordered table-condensed table-hover table-fluid'>
						<thead style="background-color: #eeeeee">
							<TR> 
								<TH>GRUPO</TH>
								<TH>CODIGO</TH>
								<TH>NOMBRE</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*513.9*/for(lista1 <- listEquipNoEnBodOrigen) yield /*513.46*/{_display_(Seq[Any](format.raw/*513.47*/("""
								"""),format.raw/*514.9*/("""<TR onClick="seleccionaEquipNoEnBodOrigen(this, """),_display_(/*514.58*/lista1/*514.64*/.get(0)),format.raw/*514.71*/(""",'"""),_display_(/*514.74*/lista1/*514.80*/.get(6)),format.raw/*514.87*/("""','"""),_display_(/*514.91*/lista1/*514.97*/.get(7)),format.raw/*514.104*/("""','"""),_display_(/*514.108*/lista1/*514.114*/.get(8)),format.raw/*514.121*/("""')" data-dismiss="modal">
									<td  style="text-align:left;"><div id="grupoNo_"""),_display_(/*515.58*/lista1/*515.64*/.get(0)),format.raw/*515.71*/("""">"""),_display_(/*515.74*/lista1/*515.80*/.get(2)),format.raw/*515.87*/("""</div></td>
									<td  style="text-align:left;"><div id="codigoNo_"""),_display_(/*516.59*/lista1/*516.65*/.get(0)),format.raw/*516.72*/("""">"""),_display_(/*516.75*/lista1/*516.81*/.get(4)),format.raw/*516.88*/("""</div></td>
									<td  style="text-align:left;"><div id="equipoNo_"""),_display_(/*517.59*/lista1/*517.65*/.get(0)),format.raw/*517.72*/("""">"""),_display_(/*517.75*/lista1/*517.81*/.get(5)),format.raw/*517.88*/("""</div></td>
								</TR>
				 			""")))}),format.raw/*519.10*/("""
						"""),format.raw/*520.7*/("""</tbody>
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
	
	<div id='modalReparaciones'></div>
	
	
	<div id='modalTablaReparaciones' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>"+
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>SELECCIONAR REPARACIONES</h5>
					<button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>&times;</span></button>
		        </div>
		        <div class='modal-body'>
		        	<div id='tablaReparaciones'></div>
		        	<button type='button' class='btn btn-sm  btn-warning' style='font-size: 10px;' data-dismiss='modal'>Listo</button>
		        </div>
	    	</div>
		</div>
	</div>
	
	<form class="formulario" id="formDescargaDocumento" method="post" action="/downloadPDF/">	
		<input type="hidden" id="descargaDocumento" name="nombreArchivo">
	</form>
	
	"""),_display_(/*554.3*/modalListaTransportistas(mapDiccionario, mapPermiso, listaTransporte)),format.raw/*554.72*/("""
	

	
	"""),_display_(if(mapPermiso.get("parametro.album-fotos")!=null && mapPermiso.get("parametro.album-fotos").equals("1"))/*558.107*/{_display_(Seq[Any](format.raw/*558.108*/("""
	
		"""),format.raw/*560.3*/("""<br>
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
						"""),_display_(if(guia.getFotos().equals("0"))/*574.39*/{_display_(Seq[Any](format.raw/*574.40*/("""
							"""),format.raw/*575.8*/("""<div id="cargadasFotos">--</div>
						""")))}else/*576.12*/{_display_(Seq[Any](format.raw/*576.13*/("""
							"""),format.raw/*577.8*/("""<a href="/muestraAlbumFotos/"""),_display_(/*577.37*/guia/*577.41*/.getFotos()),format.raw/*577.52*/("""">
								<kbd style="background-color: #7F8C8D">Ver Album</kbd>
							</a>
						""")))}),format.raw/*580.8*/("""
					"""),format.raw/*581.6*/("""</td>
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
	""")))} else {null} ),format.raw/*602.3*/("""
	
	"""),format.raw/*604.2*/("""<div id='modalVerOt' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable' style="max-width: 80% !important;" role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>"""),_display_(/*608.31*/mapDiccionario/*608.45*/.get("ORDEN_DE_TRABAJO")),format.raw/*608.69*/("""</h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<div id='mostrarLaOt'></div>
	   				<div class='row'>
						<div class='col-sm-12' style='text-align:center'>
							<button type='button' class='btn btn-sm  btn-success' style='font-size: 10px;' data-dismiss='modal'>Listo</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

""")))}),format.raw/*625.2*/("""


"""),format.raw/*628.1*/("""<style>
	#tablaPrincipal td:nth-child(2) """),format.raw/*629.34*/("""{"""),format.raw/*629.35*/(""" """),format.raw/*629.36*/("""text-align: center; """),format.raw/*629.56*/("""}"""),format.raw/*629.57*/("""
	"""),format.raw/*630.2*/("""#tablaPrincipal td:nth-child(3) """),format.raw/*630.34*/("""{"""),format.raw/*630.35*/(""" """),format.raw/*630.36*/("""text-align: center; """),format.raw/*630.56*/("""}"""),format.raw/*630.57*/("""
	"""),format.raw/*631.2*/("""#tablaPrincipal td:nth-child(4) """),format.raw/*631.34*/("""{"""),format.raw/*631.35*/(""" """),format.raw/*631.36*/("""text-align: center; """),format.raw/*631.56*/("""}"""),format.raw/*631.57*/("""
	"""),format.raw/*632.2*/("""#tablaPrincipal td:nth-child(5) """),format.raw/*632.34*/("""{"""),format.raw/*632.35*/(""" """),format.raw/*632.36*/("""text-align: left; """),format.raw/*632.54*/("""}"""),format.raw/*632.55*/("""
	"""),format.raw/*633.2*/("""#tablaPrincipal td:nth-child(7) """),format.raw/*633.34*/("""{"""),format.raw/*633.35*/(""" """),format.raw/*633.36*/("""text-align: right; """),format.raw/*633.55*/("""}"""),format.raw/*633.56*/("""
	"""),format.raw/*634.2*/("""#tablaPrincipal td:nth-child(8) """),format.raw/*634.34*/("""{"""),format.raw/*634.35*/(""" """),format.raw/*634.36*/("""text-align: right; """),format.raw/*634.55*/("""}"""),format.raw/*634.56*/("""
	"""),format.raw/*635.2*/("""#tablaPrincipal td:nth-child(9) """),format.raw/*635.34*/("""{"""),format.raw/*635.35*/(""" """),format.raw/*635.36*/("""text-align: center; """),format.raw/*635.56*/("""}"""),format.raw/*635.57*/("""
	"""),format.raw/*636.2*/("""#tablaPrincipal td:nth-child(10) """),format.raw/*636.35*/("""{"""),format.raw/*636.36*/(""" """),format.raw/*636.37*/("""text-align: right; """),format.raw/*636.56*/("""}"""),format.raw/*636.57*/("""
	"""),format.raw/*637.2*/("""#tablaPrincipal td:nth-child(11) """),format.raw/*637.35*/("""{"""),format.raw/*637.36*/(""" """),format.raw/*637.37*/("""text-align: right; """),format.raw/*637.56*/("""}"""),format.raw/*637.57*/("""
	"""),format.raw/*638.2*/("""#tablaPrincipal td:nth-child(12) """),format.raw/*638.35*/("""{"""),format.raw/*638.36*/(""" """),format.raw/*638.37*/("""text-align: center; """),format.raw/*638.57*/("""}"""),format.raw/*638.58*/("""
	"""),format.raw/*639.2*/("""#tablaPrincipal td:nth-child(13) """),format.raw/*639.35*/("""{"""),format.raw/*639.36*/(""" """),format.raw/*639.37*/("""text-align: center; """),format.raw/*639.57*/("""}"""),format.raw/*639.58*/("""
	"""),format.raw/*640.2*/("""#tablaPrincipal td:nth-child(14) """),format.raw/*640.35*/("""{"""),format.raw/*640.36*/(""" """),format.raw/*640.37*/("""text-align: right; """),format.raw/*640.56*/("""}"""),format.raw/*640.57*/("""
	"""),format.raw/*641.2*/("""#tablaPrincipal td:nth-child(15) """),format.raw/*641.35*/("""{"""),format.raw/*641.36*/(""" """),format.raw/*641.37*/("""text-align: right; """),format.raw/*641.56*/("""}"""),format.raw/*641.57*/("""
	"""),format.raw/*642.2*/("""#tablaPrincipal td:nth-child(16) """),format.raw/*642.35*/("""{"""),format.raw/*642.36*/(""" """),format.raw/*642.37*/("""text-align: right; """),format.raw/*642.56*/("""}"""),format.raw/*642.57*/("""
	"""),_display_(if(bodegaOrigen.esInterna!=1)/*643.32*/{_display_(Seq[Any](format.raw/*643.33*/("""
		"""),format.raw/*644.3*/("""#tablaPrincipal td:nth-child(17) """),format.raw/*644.36*/("""{"""),format.raw/*644.37*/(""" """),format.raw/*644.38*/("""vertical-align: middle; display:none; """),format.raw/*644.76*/("""}"""),format.raw/*644.77*/("""
		"""),_display_(/*645.4*/for((est,index) <- listTipoEstado.zipWithIndex) yield /*645.51*/{_display_(Seq[Any](format.raw/*645.52*/("""
			"""),format.raw/*646.4*/("""#tablaPrincipal td:nth-child("""),_display_(/*646.34*/(index+16)),format.raw/*646.44*/(""") """),format.raw/*646.46*/("""{"""),format.raw/*646.47*/(""" """),format.raw/*646.48*/("""vertical-align: middle; """),format.raw/*646.72*/("""}"""),format.raw/*646.73*/("""
		""")))}),format.raw/*647.4*/("""
		
		"""),format.raw/*649.3*/("""#tablaPrincipal td:nth-child("""),_display_(/*649.33*/(listTipoEstado.size()+16)),format.raw/*649.59*/(""") """),format.raw/*649.61*/("""{"""),format.raw/*649.62*/(""" """),format.raw/*649.63*/("""text-align: right; vertical-align: middle;"""),format.raw/*649.105*/("""}"""),format.raw/*649.106*/("""
		"""),format.raw/*650.3*/("""#tablaPrincipal td:nth-child("""),_display_(/*650.33*/(listTipoEstado.size()+17)),format.raw/*650.59*/(""") """),format.raw/*650.61*/("""{"""),format.raw/*650.62*/(""" """),format.raw/*650.63*/("""text-align: right; vertical-align: middle;"""),format.raw/*650.105*/("""}"""),format.raw/*650.106*/("""
		"""),format.raw/*651.3*/("""#tablaPrincipal td:nth-child("""),_display_(/*651.33*/(listTipoEstado.size()+18)),format.raw/*651.59*/(""") """),format.raw/*651.61*/("""{"""),format.raw/*651.62*/(""" """),format.raw/*651.63*/("""text-align: right; vertical-align: middle;"""),format.raw/*651.105*/("""}"""),format.raw/*651.106*/("""
	""")))} else {null} ),format.raw/*652.3*/("""
"""),format.raw/*653.1*/("""</style>

<script type="text/javascript">

		let auxId_guia = 0;
		let auxId_transportista = """"),_display_(/*658.31*/guia/*658.35*/.getId_transportista()),format.raw/*658.57*/("""";
		
		let esInterna = """"),_display_(/*660.21*/bodegaOrigen/*660.33*/.esInterna),format.raw/*660.43*/("""";
		
		
		
		
		const selectTransporte = (id_guia) => """),format.raw/*665.41*/("""{"""),format.raw/*665.42*/("""
			"""),format.raw/*666.4*/("""auxId_guia = id_guia;
			$('#modalListaTransporte').modal('show');
		"""),format.raw/*668.3*/("""}"""),format.raw/*668.4*/("""
		
		"""),format.raw/*670.3*/("""const seleccionaTransportista = (id_transportista) => """),format.raw/*670.57*/("""{"""),format.raw/*670.58*/("""
			"""),format.raw/*671.4*/("""$('#modalListaTransporte').modal('hide');
			$('#id_guiaXLSX').val(auxId_guia);
			$('#id_transportistaXLSX').val(id_transportista);
			auxId_transportista = id_transportista;
			$('#seModifico').val(1);
			transportistaSeleccionado();
			
		"""),format.raw/*678.3*/("""}"""),format.raw/*678.4*/("""
		
		"""),format.raw/*680.3*/("""const transportistaSeleccionado = () => """),format.raw/*680.43*/("""{"""),format.raw/*680.44*/("""
			"""),format.raw/*681.4*/("""var tableReg = document.getElementById("tablaListaTransporte");
			for (var i = 1; i < tableReg.rows.length; i++)"""),format.raw/*682.50*/("""{"""),format.raw/*682.51*/("""
				"""),format.raw/*683.5*/("""var cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
				let idTransporte = cellsOfRow[0].innerHTML;
				if(idTransporte == auxId_transportista)"""),format.raw/*685.44*/("""{"""),format.raw/*685.45*/("""
					"""),format.raw/*686.6*/("""tableReg.rows[i].style.backgroundColor = "yellow";
					
				"""),format.raw/*688.5*/("""}"""),format.raw/*688.6*/("""else"""),format.raw/*688.10*/("""{"""),format.raw/*688.11*/("""
					"""),format.raw/*689.6*/("""tableReg.rows[i].style.backgroundColor = "";
				"""),format.raw/*690.5*/("""}"""),format.raw/*690.6*/("""
			"""),format.raw/*691.4*/("""}"""),format.raw/*691.5*/("""
		"""),format.raw/*692.3*/("""}"""),format.raw/*692.4*/("""
	
	"""),format.raw/*694.2*/("""let tblPrincipal;
	let listReparaNiv1;
	$(document).ready(function() """),format.raw/*696.31*/("""{"""),format.raw/*696.32*/("""
		
		"""),format.raw/*698.3*/("""var formDataAux = new FormData();
		formDataAux.append('id_bodegaOrigen','"""),_display_(/*699.42*/bodegaOrigen/*699.54*/.getId()),format.raw/*699.62*/("""');
		formDataAux.append('id_bodegaDestino','"""),_display_(/*700.43*/guia/*700.47*/.getId_bodegaDestino()),format.raw/*700.69*/("""');
		$.ajax("""),format.raw/*701.10*/("""{"""),format.raw/*701.11*/("""
            """),format.raw/*702.13*/("""url: "/nickNameClienteAjax/",
            type: "POST",
            method: "POST",
            data: formDataAux,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*709.36*/("""{"""),format.raw/*709.37*/("""
				"""),format.raw/*710.5*/("""$("#nickCliente").val(respuesta);
	     	"""),format.raw/*711.8*/("""}"""),format.raw/*711.9*/("""
        """),format.raw/*712.9*/("""}"""),format.raw/*712.10*/(""");

		$('#tablaPrincipal').DataTable("""),format.raw/*714.34*/("""{"""),format.raw/*714.35*/("""
		    	"""),format.raw/*715.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[15, 50, 100, 200, -1], [15, 50, 100, 200, "All"]],
		    	"order": [[ 2, "asc" ]],
		    	"language": """),format.raw/*718.20*/("""{"""),format.raw/*718.21*/("""
		        	"""),format.raw/*719.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*720.11*/("""}"""),format.raw/*720.12*/("""
		  """),format.raw/*721.5*/("""}"""),format.raw/*721.6*/(""" """),format.raw/*721.7*/(""");
		 
		  let repAux = ""
		  """),_display_(/*724.6*/for(r <- listTipoReparacion) yield /*724.34*/{_display_(Seq[Any](format.raw/*724.35*/("""
				"""),format.raw/*725.5*/("""repAux += """"),_display_(/*725.17*/r/*725.18*/.getId()),format.raw/*725.26*/(""":&&:"""),_display_(/*725.31*/r/*725.32*/.id_tipoEstado),format.raw/*725.46*/(""":&&:"""),_display_(/*725.51*/r/*725.52*/.getSigla()),format.raw/*725.63*/(""":&&:"""),_display_(/*725.68*/r/*725.69*/.getNombre()),format.raw/*725.81*/(""":&&:"""),_display_(/*725.86*/r/*725.87*/.getMoneda()),format.raw/*725.99*/(""":&&:"""),_display_(/*725.104*/r/*725.105*/.getPrecio()),format.raw/*725.117*/(""";&&;"
		  """)))}),format.raw/*726.6*/("""
		  """),format.raw/*727.5*/("""listReparaNiv1 = repAux.split(";&&;");
		  
		  $('#tablaListaBodegasDestino').DataTable("""),format.raw/*729.46*/("""{"""),format.raw/*729.47*/("""
		    	"""),format.raw/*730.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"language": """),format.raw/*732.20*/("""{"""),format.raw/*732.21*/("""
		        	"""),format.raw/*733.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*734.11*/("""}"""),format.raw/*734.12*/("""
		  """),format.raw/*735.5*/("""}"""),format.raw/*735.6*/(""" """),format.raw/*735.7*/(""");
		  
		  $('#tablaListaEquipNoEnBodOrigen').DataTable("""),format.raw/*737.50*/("""{"""),format.raw/*737.51*/("""
		    	"""),format.raw/*738.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 2, "asc" ]],
		    	"language": """),format.raw/*741.20*/("""{"""),format.raw/*741.21*/("""
		        	"""),format.raw/*742.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*743.11*/("""}"""),format.raw/*743.12*/("""
		  """),format.raw/*744.5*/("""}"""),format.raw/*744.6*/(""" """),format.raw/*744.7*/(""");
		  
		  transportistaSeleccionado();
	
		  document.getElementById('mostrarmostrar').style.display="block";

		if(esInterna != "1")"""),format.raw/*750.23*/("""{"""),format.raw/*750.24*/("""
			"""),format.raw/*751.4*/("""let verifica = document.querySelectorAll(".verificaStock");
			let sinNegativos = 0;
			for (let i = 0; i < verifica.length; i++) """),format.raw/*753.46*/("""{"""),format.raw/*753.47*/("""
				"""),format.raw/*754.5*/("""let aux = verifica[i].textContent.replace(/,/g,'')
				if(parseFloat(aux) < -0.001)"""),format.raw/*755.33*/("""{"""),format.raw/*755.34*/("""
					"""),format.raw/*756.6*/("""sinNegativos = 1;
				"""),format.raw/*757.5*/("""}"""),format.raw/*757.6*/("""
			"""),format.raw/*758.4*/("""}"""),format.raw/*758.5*/("""
			"""),format.raw/*759.4*/("""if(sinNegativos>0)"""),format.raw/*759.22*/("""{"""),format.raw/*759.23*/("""
				"""),format.raw/*760.5*/("""alertify.alert('El estado de los stock de """),_display_(/*760.48*/mapDiccionario/*760.62*/.get("BODEGA")),format.raw/*760.76*/(""": """),_display_(/*760.79*/bodegaOrigen/*760.91*/.getNombre()),format.raw/*760.103*/(""".- '
					+'Presenta dificultades, favor contactar a soporte Inqsol indicando el nombre de """),_display_(/*761.88*/mapDiccionario/*761.102*/.get("BODEGA")),format.raw/*761.116*/(""" """),format.raw/*761.117*/("""y que es un movimiento', function () """),format.raw/*761.154*/("""{"""),format.raw/*761.155*/("""
			     			"""),format.raw/*762.12*/("""location.href = "/home/";
			     	"""),format.raw/*763.10*/("""}"""),format.raw/*763.11*/(""");
			"""),format.raw/*764.4*/("""}"""),format.raw/*764.5*/("""
		"""),format.raw/*765.3*/("""}"""),format.raw/*765.4*/("""
		
		
	"""),format.raw/*768.2*/("""}"""),format.raw/*768.3*/(""");
	
	const verificarNumeroGuia = (value) =>"""),format.raw/*770.40*/("""{"""),format.raw/*770.41*/("""
		"""),format.raw/*771.3*/("""if(value.trim()=="")"""),format.raw/*771.23*/("""{"""),format.raw/*771.24*/("""
			"""),format.raw/*772.4*/("""$("#numeroGuia").val(""""),_display_(/*772.27*/guia/*772.31*/.getNumero()),format.raw/*772.43*/("""");
		"""),format.raw/*773.3*/("""}"""),format.raw/*773.4*/("""else"""),format.raw/*773.8*/("""{"""),format.raw/*773.9*/("""
			"""),format.raw/*774.4*/("""let aux = '"""),_display_(/*774.16*/guia/*774.20*/.getNumero()),format.raw/*774.32*/("""';
			var formData = new FormData();
		  	formData.append('numeroGuia',value);
	        $.ajax("""),format.raw/*777.17*/("""{"""),format.raw/*777.18*/("""
	            """),format.raw/*778.14*/("""url: "/verificaNumeroGuiaAjax/",
	            type: "POST",
	            method: "POST",
	            data: formData,
	            cache: false,
	            contentType: false,
		     	processData: false,
		     	success: function(respuesta)"""),format.raw/*785.37*/("""{"""),format.raw/*785.38*/("""
		     		"""),format.raw/*786.10*/("""if(respuesta=="existe")"""),format.raw/*786.33*/("""{"""),format.raw/*786.34*/("""
		     			"""),format.raw/*787.11*/("""alertify.alert('El número de movimiento ya existe, intente con otro', function () """),format.raw/*787.93*/("""{"""),format.raw/*787.94*/("""
		     				"""),format.raw/*788.12*/("""$("#numeroGuia").val(""""),_display_(/*788.35*/guia/*788.39*/.getNumero()),format.raw/*788.51*/("""");
		     			"""),format.raw/*789.11*/("""}"""),format.raw/*789.12*/(""");
		     		"""),format.raw/*790.10*/("""}"""),format.raw/*790.11*/("""else if(respuesta=="error")"""),format.raw/*790.38*/("""{"""),format.raw/*790.39*/("""
		     			"""),format.raw/*791.11*/("""alertify.alert(msgError, function () """),format.raw/*791.48*/("""{"""),format.raw/*791.49*/("""
			     			"""),format.raw/*792.12*/("""location.href = "/";
			     		"""),format.raw/*793.11*/("""}"""),format.raw/*793.12*/(""");
		     		"""),format.raw/*794.10*/("""}"""),format.raw/*794.11*/("""else"""),format.raw/*794.15*/("""{"""),format.raw/*794.16*/("""
						"""),format.raw/*795.7*/("""$("#seModifico").val(1);
					"""),format.raw/*796.6*/("""}"""),format.raw/*796.7*/("""
		     	"""),format.raw/*797.9*/("""}"""),format.raw/*797.10*/("""
	        """),format.raw/*798.10*/("""}"""),format.raw/*798.11*/(""");
		"""),format.raw/*799.3*/("""}"""),format.raw/*799.4*/("""
	"""),format.raw/*800.2*/("""}"""),format.raw/*800.3*/("""
	
	"""),format.raw/*802.2*/("""const validarForm = () =>"""),format.raw/*802.27*/("""{"""),format.raw/*802.28*/("""
		"""),format.raw/*803.3*/("""$("#aplica").attr('disabled',true);
		let flag = true;
		if($("#id_bodegaDestino").val()=="")"""),format.raw/*805.39*/("""{"""),format.raw/*805.40*/("""
			"""),format.raw/*806.4*/("""flag = false;
			alertify.alert('FALTA SELECCIONAR DESTINO DE """),_display_(/*807.50*/mapDiccionario/*807.64*/.get("BODEGA")),format.raw/*807.78*/("""/PROYECTO<br>(CAMPO DESTINO)', function () """),format.raw/*807.121*/("""{"""),format.raw/*807.122*/("""
				"""),format.raw/*808.5*/("""document.getElementById('modifica').style.visibility = 'visible';
				document.getElementById('aplica').style.visibility = 'visible';
				document.getElementById('cancelar').style.visibility = 'visible';
				$("#aplica").attr('disabled',false);
				return(flag);
     		"""),format.raw/*813.8*/("""}"""),format.raw/*813.9*/(""");
		"""),format.raw/*814.3*/("""}"""),format.raw/*814.4*/("""else"""),format.raw/*814.8*/("""{"""),format.raw/*814.9*/("""
			"""),format.raw/*815.4*/("""$("#tablaPrincipal").dataTable().fnDestroy();
			let tabla = document.getElementById("tablaPrincipal");
			let validaCantidades = 0;
			for(i=1; i<tabla.rows.length-1; i++)"""),format.raw/*818.40*/("""{"""),format.raw/*818.41*/("""
				"""),format.raw/*819.5*/("""let cantidad = $(".cantidad",tabla.rows[i].cells[10]).val();
				let exceso = $(".exceso",tabla.rows[i].cells[13]).val();
				cantidad = cantidad.replace(/,/g,'');
				exceso = exceso.replace(/,/g,'');
				validaCantidades += parseFloat(cantidad) + parseFloat(exceso);
			"""),format.raw/*824.4*/("""}"""),format.raw/*824.5*/("""
			"""),format.raw/*825.4*/("""if(validaCantidades > 0)"""),format.raw/*825.28*/("""{"""),format.raw/*825.29*/("""
				"""),format.raw/*826.5*/("""for(i=1; i<tabla.rows.length-1; i++)"""),format.raw/*826.41*/("""{"""),format.raw/*826.42*/("""
					"""),format.raw/*827.6*/("""let cantidad = $(".cantidad",tabla.rows[i].cells[10]).val();
					let exceso = $(".exceso",tabla.rows[i].cells[13]).val();
					cantidad = cantidad.replace(/,/g,'');
					exceso = exceso.replace(/,/g,'');
					let total = parseFloat(cantidad) + parseFloat(exceso);
					if(total <= 0 )"""),format.raw/*832.21*/("""{"""),format.raw/*832.22*/("""
						"""),format.raw/*833.7*/("""tabla.deleteRow(i);
						i--;
					"""),format.raw/*835.6*/("""}"""),format.raw/*835.7*/("""
				"""),format.raw/*836.5*/("""}"""),format.raw/*836.6*/("""
				"""),format.raw/*837.5*/("""document.getElementById('enCarga').style.display="block";
				return(true);
			"""),format.raw/*839.4*/("""}"""),format.raw/*839.5*/("""else"""),format.raw/*839.9*/("""{"""),format.raw/*839.10*/("""
				"""),format.raw/*840.5*/("""flag = false;
				alertify.alert('NO PUEDE GRABAR UN MOVIMIENTO SIN MOVER EQUIPOS', function () """),format.raw/*841.83*/("""{"""),format.raw/*841.84*/("""
					"""),format.raw/*842.6*/("""document.getElementById('modifica').style.visibility = 'visible';
					document.getElementById('aplica').style.visibility = 'visible';
					document.getElementById('cancelar').style.visibility = 'visible';
					$("#aplica").attr('disabled',false);
					return(flag);
	     		"""),format.raw/*847.9*/("""}"""),format.raw/*847.10*/(""");
			"""),format.raw/*848.4*/("""}"""),format.raw/*848.5*/("""
		"""),format.raw/*849.3*/("""}"""),format.raw/*849.4*/("""
		"""),format.raw/*850.3*/("""return(flag);
	"""),format.raw/*851.2*/("""}"""),format.raw/*851.3*/("""
	
	"""),format.raw/*853.2*/("""let cantAux = 0;
	const verificaCantidad = (id_equipo, id_cotizacion, cantidad, KG, M2, largo) =>"""),format.raw/*854.81*/("""{"""),format.raw/*854.82*/("""
		"""),format.raw/*855.3*/("""let acum = 0;
		cantidad = cantidad.replace(/,/g,'');
		let exce = $("#exceso_"+id_equipo+"_"+id_cotizacion).val();
		exce = exce.replace(/,/g,'');
		
		if(auxBueno == 999)"""),format.raw/*860.22*/("""{"""),format.raw/*860.23*/("""
			"""),format.raw/*861.4*/("""for(let i=0; i<largo; i++)"""),format.raw/*861.30*/("""{"""),format.raw/*861.31*/("""
				"""),format.raw/*862.5*/("""if( ! (i == auxIndex && auxBueno == 999))"""),format.raw/*862.46*/("""{"""),format.raw/*862.47*/("""
					"""),format.raw/*863.6*/("""let auxAcum = $("#estado_"+id_equipo+"_"+id_cotizacion+"_"+i).val();
					auxAcum = auxAcum.replace(/,/g,'');
					acum += parseFloat(auxAcum);
				"""),format.raw/*866.5*/("""}"""),format.raw/*866.6*/("""
			"""),format.raw/*867.4*/("""}"""),format.raw/*867.5*/("""
		"""),format.raw/*868.3*/("""}"""),format.raw/*868.4*/("""else if('"""),_display_(/*868.14*/mapDiccionario/*868.28*/.get("nEmpresa")),format.raw/*868.44*/("""' == "HOHE" && ( auxBueno == 2 || auxBueno == 3 ) )"""),format.raw/*868.95*/("""{"""),format.raw/*868.96*/("""
			"""),format.raw/*869.4*/("""for(let i=0; i<largo; i++)"""),format.raw/*869.30*/("""{"""),format.raw/*869.31*/("""
				"""),format.raw/*870.5*/("""if( ! (i == auxIndex && (auxBueno == 2 || auxBueno == 3 )))"""),format.raw/*870.64*/("""{"""),format.raw/*870.65*/("""
					"""),format.raw/*871.6*/("""let auxAcum = $("#estado_"+id_equipo+"_"+id_cotizacion+"_"+i).val();
					auxAcum = auxAcum.replace(/,/g,'');
					acum += parseFloat(auxAcum);
				"""),format.raw/*874.5*/("""}"""),format.raw/*874.6*/("""
			"""),format.raw/*875.4*/("""}"""),format.raw/*875.5*/("""
		
		"""),format.raw/*877.3*/("""}"""),format.raw/*877.4*/("""else"""),format.raw/*877.8*/("""{"""),format.raw/*877.9*/("""
			"""),format.raw/*878.4*/("""for(let i=0; i<largo; i++)"""),format.raw/*878.30*/("""{"""),format.raw/*878.31*/("""
				"""),format.raw/*879.5*/("""acum += parseFloat($("#estado_"+id_equipo+"_"+id_cotizacion+"_"+i).val());
			"""),format.raw/*880.4*/("""}"""),format.raw/*880.5*/("""
		"""),format.raw/*881.3*/("""}"""),format.raw/*881.4*/("""
		
		
		"""),format.raw/*884.3*/("""if(parseFloat(acum) > (parseFloat(cantidad)))"""),format.raw/*884.48*/("""{"""),format.raw/*884.49*/("""
			"""),format.raw/*885.4*/("""let auxCant1 = cantAux;
			alertify.alert('La cantidad no puede ser menor a la suma de estados, modifique primero los estados', function () """),format.raw/*886.117*/("""{"""),format.raw/*886.118*/("""
				"""),format.raw/*887.5*/("""$("#cantidad_"+id_equipo+"_"+id_cotizacion).val(formatStandar(auxCant1,2));
     		"""),format.raw/*888.8*/("""}"""),format.raw/*888.9*/(""");
		"""),format.raw/*889.3*/("""}"""),format.raw/*889.4*/("""else"""),format.raw/*889.8*/("""{"""),format.raw/*889.9*/("""
			"""),format.raw/*890.4*/("""stockAux1 = $("#stock_"+id_equipo+"_"+id_cotizacion).text();
			stockAux1 = stockAux1.replace(/,/g,'');
			KG = KG.replace(/,/g,'');
			M2 = M2.replace(/,/g,'');
			
			let stokDisp = parseFloat(stockAux1) + parseFloat(cantAux);
			let saldo = parseFloat(stokDisp) - parseFloat(cantidad);
			
			if(saldo >= 0)"""),format.raw/*898.18*/("""{"""),format.raw/*898.19*/("""
				"""),format.raw/*899.5*/("""$("#stock_"+id_equipo+"_"+id_cotizacion).text(formatStandar(saldo,2));
				$("#cantidad_"+id_equipo+"_"+id_cotizacion).val(formatStandar(cantidad,2));
				$("#exceso_"+id_equipo+"_"+id_cotizacion).val(formatStandar("0"));
			"""),format.raw/*902.4*/("""}"""),format.raw/*902.5*/("""else"""),format.raw/*902.9*/("""{"""),format.raw/*902.10*/("""
				"""),format.raw/*903.5*/("""if('"""),_display_(/*903.10*/permiteExcedentes),format.raw/*903.27*/("""'=='0')"""),format.raw/*903.34*/("""{"""),format.raw/*903.35*/("""
					"""),format.raw/*904.6*/("""let auxCant2 = cantAux;
					alertify.alert('La cantidad no puede ser mayor que el stock disponible', function () """),format.raw/*905.91*/("""{"""),format.raw/*905.92*/("""
						"""),format.raw/*906.7*/("""$("#cantidad_"+id_equipo+"_"+id_cotizacion).val(formatStandar(auxCant2,2));
		     		"""),format.raw/*907.10*/("""}"""),format.raw/*907.11*/(""");
				"""),format.raw/*908.5*/("""}"""),format.raw/*908.6*/("""else"""),format.raw/*908.10*/("""{"""),format.raw/*908.11*/("""
					"""),format.raw/*909.6*/("""$("#stock_"+id_equipo+"_"+id_cotizacion).text(formatStandar(0,2));
					$("#cantidad_"+id_equipo+"_"+id_cotizacion).val(formatStandar(stokDisp,2));
					$("#exceso_"+id_equipo+"_"+id_cotizacion).val(formatStandar(saldo*-1,2));
					cantidad = stokDisp;
				"""),format.raw/*913.5*/("""}"""),format.raw/*913.6*/("""
			"""),format.raw/*914.4*/("""}"""),format.raw/*914.5*/("""
			
			
			"""),format.raw/*917.4*/("""if(auxBueno == 999)"""),format.raw/*917.23*/("""{"""),format.raw/*917.24*/("""
				"""),format.raw/*918.5*/("""let difB = parseFloat(cantidad) - parseFloat(acum);
				$("#estado_"+id_equipo+"_"+id_cotizacion+"_"+auxIndex).val(formatStandar(difB,2));
				
				let aux = $("#estados_"+id_equipo+"_"+id_cotizacion).val();
				let niv1 = aux.split(";");
				let flag = true;
				let buscar = "";
				for(i=0; i<niv1.length && flag; i++)"""),format.raw/*925.41*/("""{"""),format.raw/*925.42*/("""
					"""),format.raw/*926.6*/("""let niv2 = niv1[i].split(":");
					if(niv2[0] == auxBueno)"""),format.raw/*927.29*/("""{"""),format.raw/*927.30*/("""
						"""),format.raw/*928.7*/("""buscar = auxBueno+":"+niv2[1]+";";
						flag = false;
					"""),format.raw/*930.6*/("""}"""),format.raw/*930.7*/("""
				"""),format.raw/*931.5*/("""}"""),format.raw/*931.6*/("""
				
				"""),format.raw/*933.5*/("""if(flag)"""),format.raw/*933.13*/("""{"""),format.raw/*933.14*/("""
					"""),format.raw/*934.6*/("""aux += auxBueno+":"+parseFloat(difB)+";";
					$("#estados_"+id_equipo+"_"+id_cotizacion).val(aux);
				"""),format.raw/*936.5*/("""}"""),format.raw/*936.6*/("""else"""),format.raw/*936.10*/("""{"""),format.raw/*936.11*/("""
					"""),format.raw/*937.6*/("""aux = aux.replace(buscar, auxBueno+":"+parseFloat(difB)+";");
					$("#estados_"+id_equipo+"_"+id_cotizacion).val(aux);
				"""),format.raw/*939.5*/("""}"""),format.raw/*939.6*/("""
				
			"""),format.raw/*941.4*/("""}"""),format.raw/*941.5*/(""" """),format.raw/*941.6*/("""else if('"""),_display_(/*941.16*/mapDiccionario/*941.30*/.get("nEmpresa")),format.raw/*941.46*/("""' == "HOHE" && ( auxBueno == 2 || auxBueno == 3 ) )"""),format.raw/*941.97*/("""{"""),format.raw/*941.98*/("""
				"""),format.raw/*942.5*/("""let difB = parseFloat(cantidad) - parseFloat(acum);
				$("#estado_"+id_equipo+"_"+id_cotizacion+"_"+auxIndex).val(formatStandar(difB,2));
				
				let aux = $("#estados_"+id_equipo+"_"+id_cotizacion).val();
				let niv1 = aux.split(";");
				let flag = true;
				let buscar = "";
				for(i=0; i<niv1.length && flag; i++)"""),format.raw/*949.41*/("""{"""),format.raw/*949.42*/("""
					"""),format.raw/*950.6*/("""let niv2 = niv1[i].split(":");
					if(niv2[0] == auxBueno)"""),format.raw/*951.29*/("""{"""),format.raw/*951.30*/("""
						"""),format.raw/*952.7*/("""buscar = auxBueno+":"+niv2[1]+";";
						flag = false;
					"""),format.raw/*954.6*/("""}"""),format.raw/*954.7*/("""
				"""),format.raw/*955.5*/("""}"""),format.raw/*955.6*/("""
				
				"""),format.raw/*957.5*/("""if(flag)"""),format.raw/*957.13*/("""{"""),format.raw/*957.14*/("""
					"""),format.raw/*958.6*/("""aux += auxBueno+":"+parseFloat(difB)+";";
					$("#estados_"+id_equipo+"_"+id_cotizacion).val(aux);
				"""),format.raw/*960.5*/("""}"""),format.raw/*960.6*/("""else"""),format.raw/*960.10*/("""{"""),format.raw/*960.11*/("""
					"""),format.raw/*961.6*/("""aux = aux.replace(buscar, auxBueno+":"+parseFloat(difB)+";");
					$("#estados_"+id_equipo+"_"+id_cotizacion).val(aux);
				"""),format.raw/*963.5*/("""}"""),format.raw/*963.6*/("""
			"""),format.raw/*964.4*/("""}"""),format.raw/*964.5*/("""
			
			
			"""),format.raw/*967.4*/("""let totKg = parseFloat(KG) * parseFloat(cantidad);
			let totM2 = parseFloat(M2) * parseFloat(cantidad);
			$("#KG_"+id_equipo+"_"+id_cotizacion).text(formatStandar(totKg,2));
			$("#M2_"+id_equipo+"_"+id_cotizacion).text(formatStandar(totM2,2));
			$('#seModifico').val(1);
			
			if(esInterna != "1")"""),format.raw/*973.24*/("""{"""),format.raw/*973.25*/("""
				"""),_display_(if(mapPermiso.get("parametro.movimiento-devolucion-cantCliente")!=null && mapPermiso.get("parametro.movimiento-devolucion-cantCliente").equals("1"))/*974.154*/{_display_(Seq[Any](format.raw/*974.155*/("""
					"""),format.raw/*975.6*/("""actualizaCantCliente(id_equipo, id_cotizacion);
				""")))} else {null} ),format.raw/*976.6*/("""
			"""),format.raw/*977.4*/("""}"""),format.raw/*977.5*/("""
		"""),format.raw/*978.3*/("""}"""),format.raw/*978.4*/("""
	"""),format.raw/*979.2*/("""}"""),format.raw/*979.3*/("""
	
	"""),format.raw/*981.2*/("""const actualizaCantCliente = (id_equipo, id_cotizacion) =>"""),format.raw/*981.60*/("""{"""),format.raw/*981.61*/("""
		"""),format.raw/*982.3*/("""let cantCliente = $("#cantCliente_"+id_equipo+"_"+id_cotizacion).val();
		cantCliente = cantCliente.replace(/,/g,'');
		
		let cantidad = $("#cantidad_"+id_equipo+"_"+id_cotizacion).val();
		cantidad = cantidad.replace(/,/g,'');
		
		let difCliente = parseFloat(cantidad) - parseFloat(cantCliente);
		
		$("#difCliente_"+id_equipo+"_"+id_cotizacion).text(formatStandar(difCliente,2));
		
		$('#seModifico').val(1);
	"""),format.raw/*993.2*/("""}"""),format.raw/*993.3*/("""
	
	
	
	"""),format.raw/*997.2*/("""const seleccionaEquipNoEnBodOrigen = (nodo, id_equipo, KG, M2, UN) =>"""),format.raw/*997.71*/("""{"""),format.raw/*997.72*/("""
		"""),format.raw/*998.3*/("""let grupo = 
			"<input type='hidden' name='id_equipo[]' value='"+id_equipo+"'>"+
			"<input type='hidden' name='id_cotizacion[]' value='0'>"+
			$("#grupoNo_"+id_equipo).text();
		let codigo = $("#codigoNo_"+id_equipo).text();
		let equipo = $("#equipoNo_"+id_equipo).text();
		let stock = "<div id=\"stock_"+id_equipo+"_0\">0.00</div>"
		let cantidad = "<div align='center'><input type=\"text\" class=\"cantidad form-control height23px right width80px\" "+
							" name=\"cantidad[]\" "+
							" id=\"cantidad_"+id_equipo+"_0\" "+
							" value=\"0.00\" "+
							" onfocus=\"value=value.replace(/,/g,\'\'); cantAux = value;\" "+
							" onkeydown=\"return ingresoDouble(window.event)\" "+
							" autocomplete='off' "+
							" onchange=\"if(value=='') value=0; verificaCantidad("+id_equipo+",'0',value,'"+KG+"','"+M2+"',"""),_display_(/*1012.104*/listTipoEstado/*1012.118*/.size()),format.raw/*1012.125*/(""");\"></div>";
		let esVenta = "<input type='hidden' name='esVenta[]' id=\"esVenta_"+id_equipo+"_0\" value='0'>"+
						"<input type='checkbox' onchange='if($(\"#esVenta_"+id_equipo+"_0\").val()==1) "+
						"$(\"#esVenta_"+id_equipo+"_0\").val(0); else $(\"#esVenta_"+id_equipo+"_0\").val(1); $(\"#seModifico\").val(1);'>";
		let esNuevo = "<input type='hidden' name='esNuevo[]' id=\"esNuevo_"+id_equipo+"_0\" value='0'>";
		let exceso = "<div align='center'><input type='text' class='exceso form-control height23px right width80px' "+
							" name='exceso[]' "+
							" id='exceso_"+id_equipo+"_0' "+
							" value='0.00' "+
							" readonly></div>";
		let auxKG = "<div class='KG' id='KG_"+id_equipo+"_0'>0.00</div>";
		let auxM2 = "<div class='M2' id='M2_"+id_equipo+"_0'>0.00</div>";
		
		
		let row = $('#tablaPrincipal').dataTable().fnAddData( [
			grupo,"--","--","--",codigo,equipo,KG,M2,UN,stock,cantidad,esVenta,esNuevo,exceso,auxKG,auxM2,
				"""),_display_(if(bodegaOrigen.esInterna!=1)/*1028.35*/{_display_(Seq[Any](format.raw/*1028.36*/("""
					"""),format.raw/*1029.6*/(""""<input type='hidden' name='estados[]'   id='estados_"+id_equipo+"_0'><input type='hidden' name='reparaciones[]'   id='reparaciones_"+id_equipo+"_0'>",
					"""),_display_(/*1030.7*/for((est,index) <- listTipoEstado.zipWithIndex) yield /*1030.54*/{_display_(Seq[Any](format.raw/*1030.55*/("""
							"""),format.raw/*1031.8*/(""""<input type='text' class='form-control height23px right width60px' "+
								" style='font-size:10px; vertical-align:middle' "+
								" id='estado_"+id_equipo+"_0_"""),_display_(/*1033.39*/index),format.raw/*1033.44*/("""' " +
								" value='0.00' "+
								" readonly> ",
					""")))}),format.raw/*1036.7*/("""
					
					"""),_display_(if(mapPermiso.get("parametro.movimiento-devolucion-cantCliente")!=null && mapPermiso.get("parametro.movimiento-devolucion-cantCliente").equals("1"))/*1038.155*/{_display_(Seq[Any](format.raw/*1038.156*/("""
						"""),format.raw/*1039.7*/(""""<div align='center'><input type=\"text\" class=\"cantCliente form-control height23px right width80px\" "+
								" name=\"cantCliente[]\" "+
								" id=\"cantCliente_"+id_equipo+"_0\" "+
								" value=\"0.00\" "+
								" onfocus=\"value=value.replace(/,/g,\'\');\" "+
								" onkeydown=\"return ingresoDouble(window.event)\" "+
								" autocomplete='off' "+
								" onchange=\"if(value=='') value=0; actualizaCantCliente("+id_equipo+",'0');\"></div>",
								
						"<div class='difCliente' id='difCliente_"+id_equipo+"_0'>0.00</div>",
					""")))} else {null} ),format.raw/*1049.7*/("""
					
					
					
				""")))} else {null} ),format.raw/*1053.6*/("""
		"""),format.raw/*1054.3*/("""] );
		
		$("#tablaPrincipal").dataTable().fnDestroy();
			var tableReg = document.getElementById("tablaPrincipal");
			for (var i = 1; i < tableReg.rows.length; i++)"""),format.raw/*1058.50*/("""{"""),format.raw/*1058.51*/("""
				"""),format.raw/*1059.5*/("""var cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
				if(cellsOfRow[4].innerHTML == codigo)"""),format.raw/*1060.42*/("""{"""),format.raw/*1060.43*/("""
					"""),format.raw/*1061.6*/("""if(""""),_display_(/*1061.11*/sinVenta),format.raw/*1061.19*/("""" == "1")"""),format.raw/*1061.28*/("""{"""),format.raw/*1061.29*/("""
						"""),format.raw/*1062.7*/("""cellsOfRow[11].style.display = 'none';
						cellsOfRow[12].style.display = 'none';
					"""),format.raw/*1064.6*/("""}"""),format.raw/*1064.7*/("""else"""),format.raw/*1064.11*/("""{"""),format.raw/*1064.12*/("""
						"""),format.raw/*1065.7*/("""cellsOfRow[12].style.display = 'none';
					"""),format.raw/*1066.6*/("""}"""),format.raw/*1066.7*/("""
					
				"""),format.raw/*1068.5*/("""}"""),format.raw/*1068.6*/("""
			"""),format.raw/*1069.4*/("""}"""),format.raw/*1069.5*/("""
		"""),format.raw/*1070.3*/("""tblPrincipal = $('#tablaPrincipal').DataTable("""),format.raw/*1070.49*/("""{"""),format.raw/*1070.50*/("""
	    	"""),format.raw/*1071.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[15, 50, 100, 200, -1], [15, 50, 100, 200, "All"]],
	    	"order": [[ 2, "asc" ]],
	    	"language": """),format.raw/*1074.19*/("""{"""),format.raw/*1074.20*/("""
	        	"""),format.raw/*1075.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*1076.10*/("""}"""),format.raw/*1076.11*/("""
	  	"""),format.raw/*1077.5*/("""}"""),format.raw/*1077.6*/(""" """),format.raw/*1077.7*/(""");
		
		
		
		$('#tablaListaEquipNoEnBodOrigen').dataTable().fnDeleteRow(nodo);
		$("#equipNoEnBodOrigen").modal("hide");
	"""),format.raw/*1083.2*/("""}"""),format.raw/*1083.3*/("""
	
	"""),format.raw/*1085.2*/("""const verificaCantEstado = (index, largo, id_equipo, id_cotizacion, cantEstado, esReparable, id_tipoEstado) =>"""),format.raw/*1085.112*/("""{"""),format.raw/*1085.113*/("""
		"""),format.raw/*1086.3*/("""let acum = 0;
		let cant = $("#cantidad_"+id_equipo+"_"+id_cotizacion).val();
		let exce = $("#exceso_"+id_equipo+"_"+id_cotizacion).val();
		cant = cant.replace(/,/g,'');
		exce = exce.replace(/,/g,'');
		
		for(let i=0; i<largo; i++)"""),format.raw/*1092.29*/("""{"""),format.raw/*1092.30*/("""
			"""),format.raw/*1093.4*/("""if( (! (i == auxIndex && auxBueno == 999)) && (! (i == auxIndex && (auxBueno == 2 || auxBueno == 3))))"""),format.raw/*1093.106*/("""{"""),format.raw/*1093.107*/("""
				"""),format.raw/*1094.5*/("""let auxAcum = $("#estado_"+id_equipo+"_"+id_cotizacion+"_"+i).val();
				auxAcum = auxAcum.replace(/,/g,'');
				acum += parseFloat(auxAcum);
			"""),format.raw/*1097.4*/("""}"""),format.raw/*1097.5*/("""
		"""),format.raw/*1098.3*/("""}"""),format.raw/*1098.4*/("""
		
		"""),format.raw/*1100.3*/("""if(parseFloat(acum) > parseFloat(cant))"""),format.raw/*1100.42*/("""{"""),format.raw/*1100.43*/("""
			"""),format.raw/*1101.4*/("""alertify.alert('La suma de estados no puede ser mayor a campo cantidad', function () """),format.raw/*1101.89*/("""{"""),format.raw/*1101.90*/("""
				"""),format.raw/*1102.5*/("""$("#estado_"+id_equipo+"_"+id_cotizacion+"_"+index).val("0");
				
				if(auxBueno == 999)"""),format.raw/*1104.24*/("""{"""),format.raw/*1104.25*/("""
					"""),format.raw/*1105.6*/("""let newAcum = 0;
					for(let i=0; i<largo; i++)"""),format.raw/*1106.32*/("""{"""),format.raw/*1106.33*/("""
						"""),format.raw/*1107.7*/("""if( ! (i == auxIndex && auxBueno == 999))"""),format.raw/*1107.48*/("""{"""),format.raw/*1107.49*/("""
							"""),format.raw/*1108.8*/("""let auxAcum = $("#estado_"+id_equipo+"_"+id_cotizacion+"_"+i).val();
							auxAcum = auxAcum.replace(/,/g,'');
							newAcum += parseFloat(auxAcum);
						"""),format.raw/*1111.7*/("""}"""),format.raw/*1111.8*/("""
					"""),format.raw/*1112.6*/("""}"""),format.raw/*1112.7*/("""
					"""),format.raw/*1113.6*/("""let difB = parseFloat(cant) - parseFloat(newAcum);
					$("#estado_"+id_equipo+"_"+id_cotizacion+"_"+auxIndex).val(formatStandar(difB,2));
					
					let aux = $("#estados_"+id_equipo+"_"+id_cotizacion).val();
					let niv1 = aux.split(";");
					let flag = true;
					let buscar = "";
					for(i=0; i<niv1.length && flag; i++)"""),format.raw/*1120.42*/("""{"""),format.raw/*1120.43*/("""
						"""),format.raw/*1121.7*/("""let niv2 = niv1[i].split(":");
						if(niv2[0] == auxBueno)"""),format.raw/*1122.30*/("""{"""),format.raw/*1122.31*/("""
							"""),format.raw/*1123.8*/("""buscar = auxBueno+":"+niv2[1]+";";
							flag = false;
						"""),format.raw/*1125.7*/("""}"""),format.raw/*1125.8*/("""
					"""),format.raw/*1126.6*/("""}"""),format.raw/*1126.7*/("""
					
					"""),format.raw/*1128.6*/("""if(flag)"""),format.raw/*1128.14*/("""{"""),format.raw/*1128.15*/("""
						"""),format.raw/*1129.7*/("""aux += auxBueno+":"+parseFloat(difB)+";";
						$("#estados_"+id_equipo+"_"+id_cotizacion).val(aux);
					"""),format.raw/*1131.6*/("""}"""),format.raw/*1131.7*/("""else"""),format.raw/*1131.11*/("""{"""),format.raw/*1131.12*/("""
						"""),format.raw/*1132.7*/("""aux = aux.replace(buscar,auxBueno+":"+parseFloat(difB)+";");
						$("#estados_"+id_equipo+"_"+id_cotizacion).val(aux);
					"""),format.raw/*1134.6*/("""}"""),format.raw/*1134.7*/("""
				"""),format.raw/*1135.5*/("""}"""),format.raw/*1135.6*/("""else if('"""),_display_(/*1135.16*/mapDiccionario/*1135.30*/.get("nEmpresa")),format.raw/*1135.46*/("""' == "HOHE" && ( auxBueno == 2 || auxBueno == 3 ) )"""),format.raw/*1135.97*/("""{"""),format.raw/*1135.98*/("""
					"""),format.raw/*1136.6*/("""let newAcum = 0;
					for(let i=0; i<largo; i++)"""),format.raw/*1137.32*/("""{"""),format.raw/*1137.33*/("""
						"""),format.raw/*1138.7*/("""if( ! (i == auxIndex && (auxBueno == 2 || auxBueno == 3)))"""),format.raw/*1138.65*/("""{"""),format.raw/*1138.66*/("""
							"""),format.raw/*1139.8*/("""let auxAcum = $("#estado_"+id_equipo+"_"+id_cotizacion+"_"+i).val();
							auxAcum = auxAcum.replace(/,/g,'');
							newAcum += parseFloat(auxAcum);
						"""),format.raw/*1142.7*/("""}"""),format.raw/*1142.8*/("""
					"""),format.raw/*1143.6*/("""}"""),format.raw/*1143.7*/("""
					"""),format.raw/*1144.6*/("""let difB = parseFloat(cant) - parseFloat(newAcum);
					$("#estado_"+id_equipo+"_"+id_cotizacion+"_"+auxIndex).val(formatStandar(difB,2));
					
					let aux = $("#estados_"+id_equipo+"_"+id_cotizacion).val();
					let niv1 = aux.split(";");
					let flag = true;
					let buscar = "";
					for(i=0; i<niv1.length && flag; i++)"""),format.raw/*1151.42*/("""{"""),format.raw/*1151.43*/("""
						"""),format.raw/*1152.7*/("""let niv2 = niv1[i].split(":");
						if(niv2[0] == auxBueno)"""),format.raw/*1153.30*/("""{"""),format.raw/*1153.31*/("""
							"""),format.raw/*1154.8*/("""buscar = auxBueno+":"+niv2[1]+";";
							flag = false;
						"""),format.raw/*1156.7*/("""}"""),format.raw/*1156.8*/("""
					"""),format.raw/*1157.6*/("""}"""),format.raw/*1157.7*/("""
					
					"""),format.raw/*1159.6*/("""if(flag)"""),format.raw/*1159.14*/("""{"""),format.raw/*1159.15*/("""
						"""),format.raw/*1160.7*/("""aux += auxBueno+":"+parseFloat(difB)+";";
						$("#estados_"+id_equipo+"_"+id_cotizacion).val(aux);
					"""),format.raw/*1162.6*/("""}"""),format.raw/*1162.7*/("""else"""),format.raw/*1162.11*/("""{"""),format.raw/*1162.12*/("""
						"""),format.raw/*1163.7*/("""aux = aux.replace(buscar,auxBueno+":"+parseFloat(difB)+";");
						$("#estados_"+id_equipo+"_"+id_cotizacion).val(aux);
					"""),format.raw/*1165.6*/("""}"""),format.raw/*1165.7*/("""
				"""),format.raw/*1166.5*/("""}"""),format.raw/*1166.6*/("""
     		"""),format.raw/*1167.8*/("""}"""),format.raw/*1167.9*/(""");
		"""),format.raw/*1168.3*/("""}"""),format.raw/*1168.4*/("""else"""),format.raw/*1168.8*/("""{"""),format.raw/*1168.9*/("""
			
			"""),format.raw/*1170.4*/("""if(auxBueno == 999)"""),format.raw/*1170.23*/("""{"""),format.raw/*1170.24*/("""
				"""),format.raw/*1171.5*/("""let difB = parseFloat(cant) - parseFloat(acum);
				$("#estado_"+id_equipo+"_"+id_cotizacion+"_"+auxIndex).val(formatStandar(difB,2));
				
				let aux = $("#estados_"+id_equipo+"_"+id_cotizacion).val();
				let niv1 = aux.split(";");
				let flag = true;
				let buscar = "";
				for(i=0; i<niv1.length && flag; i++)"""),format.raw/*1178.41*/("""{"""),format.raw/*1178.42*/("""
					"""),format.raw/*1179.6*/("""let niv2 = niv1[i].split(":");
					if(niv2[0] == auxBueno)"""),format.raw/*1180.29*/("""{"""),format.raw/*1180.30*/("""
						"""),format.raw/*1181.7*/("""buscar = auxBueno+":"+niv2[1]+";";
						flag = false;
					"""),format.raw/*1183.6*/("""}"""),format.raw/*1183.7*/("""
				"""),format.raw/*1184.5*/("""}"""),format.raw/*1184.6*/("""
				
				"""),format.raw/*1186.5*/("""if(flag)"""),format.raw/*1186.13*/("""{"""),format.raw/*1186.14*/("""
					"""),format.raw/*1187.6*/("""aux += auxBueno+":"+parseFloat(difB)+";";
					$("#estados_"+id_equipo+"_"+id_cotizacion).val(aux);
				"""),format.raw/*1189.5*/("""}"""),format.raw/*1189.6*/("""else"""),format.raw/*1189.10*/("""{"""),format.raw/*1189.11*/("""
					"""),format.raw/*1190.6*/("""aux = aux.replace(buscar,auxBueno+":"+parseFloat(difB)+";");
					$("#estados_"+id_equipo+"_"+id_cotizacion).val(aux);
				"""),format.raw/*1192.5*/("""}"""),format.raw/*1192.6*/("""
				
			"""),format.raw/*1194.4*/("""}"""),format.raw/*1194.5*/("""else if('"""),_display_(/*1194.15*/mapDiccionario/*1194.29*/.get("nEmpresa")),format.raw/*1194.45*/("""' == "HOHE" && ( auxBueno == 2 || auxBueno == 3 ) )"""),format.raw/*1194.96*/("""{"""),format.raw/*1194.97*/("""
				"""),format.raw/*1195.5*/("""let difB = parseFloat(cant) - parseFloat(acum);
				$("#estado_"+id_equipo+"_"+id_cotizacion+"_"+auxIndex).val(formatStandar(difB,2));
				
				let aux = $("#estados_"+id_equipo+"_"+id_cotizacion).val();
				let niv1 = aux.split(";");
				let flag = true;
				let buscar = "";
				for(i=0; i<niv1.length && flag; i++)"""),format.raw/*1202.41*/("""{"""),format.raw/*1202.42*/("""
					"""),format.raw/*1203.6*/("""let niv2 = niv1[i].split(":");
					if(niv2[0] == auxBueno)"""),format.raw/*1204.29*/("""{"""),format.raw/*1204.30*/("""
						"""),format.raw/*1205.7*/("""buscar = auxBueno+":"+niv2[1]+";";
						flag = false;
					"""),format.raw/*1207.6*/("""}"""),format.raw/*1207.7*/("""
				"""),format.raw/*1208.5*/("""}"""),format.raw/*1208.6*/("""
				
				"""),format.raw/*1210.5*/("""if(flag)"""),format.raw/*1210.13*/("""{"""),format.raw/*1210.14*/("""
					"""),format.raw/*1211.6*/("""aux += auxBueno+":"+parseFloat(difB)+";";
					$("#estados_"+id_equipo+"_"+id_cotizacion).val(aux);
				"""),format.raw/*1213.5*/("""}"""),format.raw/*1213.6*/("""else"""),format.raw/*1213.10*/("""{"""),format.raw/*1213.11*/("""
					"""),format.raw/*1214.6*/("""aux = aux.replace(buscar,auxBueno+":"+parseFloat(difB)+";");
					$("#estados_"+id_equipo+"_"+id_cotizacion).val(aux);
				"""),format.raw/*1216.5*/("""}"""),format.raw/*1216.6*/("""
			"""),format.raw/*1217.4*/("""}"""),format.raw/*1217.5*/("""
			
		
		
			"""),format.raw/*1221.4*/("""let aux = $("#estados_"+id_equipo+"_"+id_cotizacion).val();
			let niv1 = aux.split(";");
			let flag = true;
			let buscar = "";
			for(i=0; i<niv1.length && flag; i++)"""),format.raw/*1225.40*/("""{"""),format.raw/*1225.41*/("""
				"""),format.raw/*1226.5*/("""let niv2 = niv1[i].split(":");
				if(niv2[0]==id_tipoEstado)"""),format.raw/*1227.31*/("""{"""),format.raw/*1227.32*/("""
					"""),format.raw/*1228.6*/("""buscar = id_tipoEstado+":"+niv2[1]+";";
					flag = false;
				"""),format.raw/*1230.5*/("""}"""),format.raw/*1230.6*/("""
			"""),format.raw/*1231.4*/("""}"""),format.raw/*1231.5*/("""
			
			"""),format.raw/*1233.4*/("""if(flag)"""),format.raw/*1233.12*/("""{"""),format.raw/*1233.13*/("""
				"""),format.raw/*1234.5*/("""aux += id_tipoEstado+":"+parseFloat(cantEstado)+";";
				$("#estados_"+id_equipo+"_"+id_cotizacion).val(aux);
			"""),format.raw/*1236.4*/("""}"""),format.raw/*1236.5*/("""else"""),format.raw/*1236.9*/("""{"""),format.raw/*1236.10*/("""
				"""),format.raw/*1237.5*/("""aux = aux.replace(buscar,id_tipoEstado+":"+parseFloat(cantEstado)+";");
				$("#estados_"+id_equipo+"_"+id_cotizacion).val(aux);
			"""),format.raw/*1239.4*/("""}"""),format.raw/*1239.5*/("""
			
			"""),format.raw/*1241.4*/("""if(esReparable==1 && cantEstado>0)"""),format.raw/*1241.38*/("""{"""),format.raw/*1241.39*/("""
			    
			    """),format.raw/*1243.8*/("""let modalReparaciones="<div id='reparaciones' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>"+
			    		"<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'><div class='modal-content'><div class='modal-header'>"+
						"<h5 class='modal-title'>SELECCIONAR REPARACIONES</h5><button type='button' class='close' data-dismiss='modal' aria-label='Close'>"+
				        "<span aria-hidden='true'>&times;</span></button></div><div class='modal-body'>"+
						"<table id='tablaListaReparaciones' class='table table-sm table-bordered table-condensed table-hover table-fluid'>"+
						"<thead style='background-color: #eeeeee'><TR><TH>SIGLA</TH><TH>REPARACION</TH><TH>MONEDA</TH><TH>PRECIO</TH><TH>CANTIDAD</TH><TH>DEL</TH></TR></thead>"+
						"<tbody><TR><td  colspan='6'><div class='row justify-content-md-left'><div class='col-xs-12 col-sm-6 col-md-6 col-lg-6'><div id='selListaReparaciones'>"+
						"</div></div></div></TR></tbody></table><div class='row'><div class='col-sm-12' style='text-align:center'>"+
						"<button type='button' class='btn btn-sm  btn-warning' style='font-size: 10px;' data-dismiss='modal'>Listo</button></div></div></div></div></div></div>";
			    document.getElementById("modalReparaciones").innerHTML = modalReparaciones;
			    
			    
			    
			    
			    
				     let tablaReparaciones = "<table id='tblTablaReparaciones' class=\"table table-sm table-hover table-bordered table-condensed table-fluid\">"+
							"<thead style=\"background-color: #eeeeee\"><th>SIGLA</th><th>NOMBRE</th><th>MONEDA</th><th>PRECIO</th></thead>"+
							"<tbody>";
				    for(i=0;i<listReparaNiv1.length;i++)"""),format.raw/*1261.45*/("""{"""),format.raw/*1261.46*/("""
				    	"""),format.raw/*1262.10*/("""let listReparaNiv2 = listReparaNiv1[i].split(":&&:");
				    	if(listReparaNiv2[1]==id_tipoEstado)"""),format.raw/*1263.46*/("""{"""),format.raw/*1263.47*/("""
				    		"""),format.raw/*1264.11*/("""//  0=getId()   1=id_tipoEstado   2=getSigla() 3=getNombre()   4=getMoneda()   5=getPrecio()
				    		tablaReparaciones += "<tr onclick=\"actualizaReparacion("+id_equipo+","+id_cotizacion+","+id_tipoEstado+","+listReparaNiv2[0]+"); $('#modalTablaReparaciones').modal('hide');\">"+
				    			"<td>"+listReparaNiv2[2]+"</td><td>"+listReparaNiv2[3]+"</td><td style='text-align: center'>"+listReparaNiv2[4]+"</td><td style='text-align: right'>"+listReparaNiv2[5]+"</td>"+
				    			"</tr>";
				    	"""),format.raw/*1268.10*/("""}"""),format.raw/*1268.11*/("""
				    """),format.raw/*1269.9*/("""}"""),format.raw/*1269.10*/("""
				    """),format.raw/*1270.9*/("""tablaReparaciones += "</tbody></table>"
			    
			     
			    document.getElementById("tablaReparaciones").innerHTML = tablaReparaciones;
			    
			     $('#tblTablaReparaciones').DataTable("""),format.raw/*1275.46*/("""{"""),format.raw/*1275.47*/("""
				    	"""),format.raw/*1276.10*/(""""fixedHeader": true,
				    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
				    	"order": [[ 2, "asc" ]],
				    	"language": """),format.raw/*1279.22*/("""{"""),format.raw/*1279.23*/("""
				        	"""),format.raw/*1280.14*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
				        """),format.raw/*1281.13*/("""}"""),format.raw/*1281.14*/("""
				  """),format.raw/*1282.7*/("""}"""),format.raw/*1282.8*/(""" """),format.raw/*1282.9*/(""");
				    
			    
			    document.getElementById("selListaReparaciones").innerHTML = "<input type=\"text\" class=\"form-control\" onclick = \"$('#modalTablaReparaciones').modal('show');\" readonly value=\"select\">";
			    
			    let repar = $("#reparaciones_"+id_equipo+"_"+id_cotizacion).val();
			    let reparNiv1 = repar.split(";");
			    for(let i=0; i<reparNiv1.length; i++)"""),format.raw/*1289.45*/("""{"""),format.raw/*1289.46*/("""
			    	"""),format.raw/*1290.9*/("""let reparNiv2 = reparNiv1[i].split(":");
			    	if(reparNiv2[0]==id_tipoEstado)"""),format.raw/*1291.40*/("""{"""),format.raw/*1291.41*/("""
			    		"""),format.raw/*1292.10*/("""repar = repar.replace(reparNiv1[i]+";","");
			    	"""),format.raw/*1293.9*/("""}"""),format.raw/*1293.10*/(""" 
			    """),format.raw/*1294.8*/("""}"""),format.raw/*1294.9*/("""
			    
			    """),format.raw/*1296.8*/("""$("#reparaciones_"+id_equipo+"_"+id_cotizacion).val(repar);
				$("#reparaciones").modal("show");
			"""),format.raw/*1298.4*/("""}"""),format.raw/*1298.5*/("""
			"""),format.raw/*1299.4*/("""$("#estado_"+id_equipo+"_"+id_cotizacion+"_"+index).val(formatStandar(cantEstado,0));
			$('#seModifico').val(1);
			
			
			
			
			
			
		"""),format.raw/*1307.3*/("""}"""),format.raw/*1307.4*/("""
	"""),format.raw/*1308.2*/("""}"""),format.raw/*1308.3*/("""
	
	"""),format.raw/*1310.2*/("""const editaReparaciones = (index, largo, id_equipo, id_cotizacion, cantEstado, esReparable, id_tipoEstado) =>"""),format.raw/*1310.111*/("""{"""),format.raw/*1310.112*/("""
		"""),format.raw/*1311.3*/("""let modalReparaciones="<div id='reparaciones' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>"+
			"<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'><div class='modal-content'><div class='modal-header'>"+
			"<h5 class='modal-title'>SELECCIONAR REPARACIONES</h5><button type='button' class='close' data-dismiss='modal' aria-label='Close'>"+
	        "<span aria-hidden='true'>&times;</span></button></div><div class='modal-body'>"+
			"<table id='tablaListaReparaciones' class='table table-sm table-bordered table-condensed table-hover table-fluid'>"+
			"<thead style='background-color: #eeeeee'><TR><TH>SIGLA</TH><TH>REPARACION</TH><TH>MONEDA</TH><TH>PRECIO</TH><TH>CANTIDAD</TH><TH>DEL</TH></TR></thead>"+
			"<tbody>";
			let repar = $("#reparaciones_"+id_equipo+"_"+id_cotizacion).val();
			let reparNiv1 = repar.split(";");
			for(let j=0; j<reparNiv1.length; j++)"""),format.raw/*1320.41*/("""{"""),format.raw/*1320.42*/("""
				"""),format.raw/*1321.5*/("""let reparNiv2 = reparNiv1[j].split(":");
				if(reparNiv2.length>0 && reparNiv2[0]==id_tipoEstado)"""),format.raw/*1322.58*/("""{"""),format.raw/*1322.59*/("""
					"""),format.raw/*1323.6*/("""for(i=0;i<listReparaNiv1.length;i++)"""),format.raw/*1323.42*/("""{"""),format.raw/*1323.43*/("""
				    	"""),format.raw/*1324.10*/("""let listReparaNiv2 = listReparaNiv1[i].split(":&&:");
				    	if(listReparaNiv2[1]==id_tipoEstado)"""),format.raw/*1325.46*/("""{"""),format.raw/*1325.47*/("""
				    		"""),format.raw/*1326.11*/("""//  0=getId()   1=id_tipoEstado   2=getSigla() 3=getNombre()   4=getMoneda()   5=getPrecio()
				    		if(listReparaNiv2[0]==reparNiv2[1])"""),format.raw/*1327.46*/("""{"""),format.raw/*1327.47*/("""
				    			"""),format.raw/*1328.12*/("""modalReparaciones += "<tr>"+
				    				"<td style='text-align:left;'>"+listReparaNiv2[2]+"</td>"+
				    				"<td style='text-align:left;'>"+listReparaNiv2[3]+"</td>"+
				    				"<td style='text-align:center;'>"+listReparaNiv2[4]+"</td>"+
				    				"<td style='text-align:right;'>"+listReparaNiv2[5]+"</td>"+
				    				"<td class='width80px' style='text-align:center;'>"+
					    				"<input type='text' class='form-control right width80px' "+
										" value='"+formatStandar(reparNiv2[2],2)+"' "+ 
										" onfocus=\"value=value.replace(/,/g,'')\"  "+
										" onkeydown='return ingresoDouble(window.event)' "+
										" autocomplete='off' "+
										" onchange='modificaReparacion("+id_equipo+","+id_cotizacion+","+id_tipoEstado+","+reparNiv2[1]+",value)'>"+
				    				"</td>"+
				    				"<td style='text-align:center;'>"+
				    					"<a href='#' onclick= 'eliminaReparacion(this,"+id_equipo+","+id_cotizacion+","+id_tipoEstado+","+reparNiv2[1]+")'><kbd style='background-color: red'>X</kbd></a>"+
				    				"</td></tr>";
				    		"""),format.raw/*1344.11*/("""}"""),format.raw/*1344.12*/("""
				    	"""),format.raw/*1345.10*/("""}"""),format.raw/*1345.11*/("""
				    """),format.raw/*1346.9*/("""}"""),format.raw/*1346.10*/("""
				"""),format.raw/*1347.5*/("""}"""),format.raw/*1347.6*/("""
			"""),format.raw/*1348.4*/("""}"""),format.raw/*1348.5*/("""
			"""),format.raw/*1349.4*/("""modalReparaciones += "<TR><td  colspan='6'><div class='row justify-content-md-left'><div class='col-xs-12 col-sm-6 col-md-6 col-lg-6'><div id='selListaReparaciones'>"+
				"</div></div></div></TR></tbody></table><div class='row'><div class='col-sm-12' style='text-align:center'>"+
				"<button type='button' class='btn btn-sm  btn-warning' style='font-size: 10px;' data-dismiss='modal'>Listo</button></div></div></div></div></div></div>";
				
		    
		        let tablaReparaciones = "<table id='tblTablaReparaciones' class=\"table table-sm table-hover table-bordered table-condensed table-fluid\">"+
						"<thead style=\"background-color: #eeeeee\"><th>SIGLA</th><th>NOMBRE</th><th>MONEDA</th><th>PRECIO</th></thead>"+
						"<tbody>";
			    for(i=0;i<listReparaNiv1.length;i++)"""),format.raw/*1357.44*/("""{"""),format.raw/*1357.45*/("""
			    	"""),format.raw/*1358.9*/("""let listReparaNiv2 = listReparaNiv1[i].split(":&&:");
			    	if(listReparaNiv2[1]==id_tipoEstado)"""),format.raw/*1359.45*/("""{"""),format.raw/*1359.46*/("""
			    		"""),format.raw/*1360.10*/("""//  0=getId()   1=id_tipoEstado   2=getSigla() 3=getNombre()   4=getMoneda()   5=getPrecio()
			    		tablaReparaciones += "<tr onclick=\"actualizaReparacion("+id_equipo+","+id_cotizacion+","+id_tipoEstado+","+listReparaNiv2[0]+"); $('#modalTablaReparaciones').modal('hide');\">"+
			    			"<td>"+listReparaNiv2[2]+"</td><td>"+listReparaNiv2[3]+"</td><td style='text-align: center'>"+listReparaNiv2[4]+"</td><td style='text-align: right'>"+listReparaNiv2[5]+"</td>"+
			    			"</tr>";
			    	"""),format.raw/*1364.9*/("""}"""),format.raw/*1364.10*/("""
			    """),format.raw/*1365.8*/("""}"""),format.raw/*1365.9*/("""
			    """),format.raw/*1366.8*/("""tablaReparaciones += "</tbody></table>"
		    
		     
		    document.getElementById("tablaReparaciones").innerHTML = tablaReparaciones;
		    
		     $('#tblTablaReparaciones').DataTable("""),format.raw/*1371.45*/("""{"""),format.raw/*1371.46*/("""
			    	"""),format.raw/*1372.9*/(""""fixedHeader": true,
			    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
			    	"order": [[ 2, "asc" ]],
			    	"language": """),format.raw/*1375.21*/("""{"""),format.raw/*1375.22*/("""
			        	"""),format.raw/*1376.13*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
			        """),format.raw/*1377.12*/("""}"""),format.raw/*1377.13*/("""
			  """),format.raw/*1378.6*/("""}"""),format.raw/*1378.7*/(""" """),format.raw/*1378.8*/(""");
		    
		    
		    document.getElementById("modalReparaciones").innerHTML = modalReparaciones;
		    document.getElementById("selListaReparaciones").innerHTML = "<input type=\"text\" class=\"form-control\" onclick = \"$('#modalTablaReparaciones').modal('show');\" readonly value=\"select\">";
			$('#seModifico').val(1);
		    $("#reparaciones").modal("show");
	"""),format.raw/*1385.2*/("""}"""),format.raw/*1385.3*/("""

	"""),format.raw/*1387.2*/("""const actualizaReparacion = (id_equipo, id_cotizacion, id_tipoEstado, id_reparacion) =>"""),format.raw/*1387.89*/("""{"""),format.raw/*1387.90*/("""
		"""),format.raw/*1388.3*/("""if(id_reparacion > 0)"""),format.raw/*1388.24*/("""{"""),format.raw/*1388.25*/("""
			"""),format.raw/*1389.4*/("""for(i=0;i<listReparaNiv1.length;i++)"""),format.raw/*1389.40*/("""{"""),format.raw/*1389.41*/("""
		    	"""),format.raw/*1390.8*/("""let listReparaNiv2 = listReparaNiv1[i].split(":&&:");
		    	if(listReparaNiv2[0]==id_reparacion)"""),format.raw/*1391.44*/("""{"""),format.raw/*1391.45*/("""
		    		"""),format.raw/*1392.9*/("""//  0=getId()   1=id_tipoEstado   2=getSigla() 3=getNombre()   4=getMoneda()   5=getPrecio()
		    		let tabla = document.getElementById('tablaListaReparaciones');
		    		let row = tabla.insertRow(tabla.rows.length-1);
		    		let cell = row.insertCell(0);
		    		cell.style.textAlign = "left";
		    		cell.innerHTML = listReparaNiv2[2];
		    		cell = row.insertCell(1);
		    		cell.style.textAlign = "left";
		    		cell.innerHTML = listReparaNiv2[3];
		    		cell = row.insertCell(2);
		    		cell.style.textAlign = "center";
		    		cell.innerHTML = listReparaNiv2[4];
		    		cell = row.insertCell(3);
		    		cell.style.textAlign = "right";
		    		cell.innerHTML = listReparaNiv2[5];
		    		cell = row.insertCell(4);
		    		cell.style.textAlign = "center";
		    		cell.setAttribute("class", "width80px");
		    		cell.innerHTML = "<input type='text' class='form-control right width80px' "+
							" value='0.00' "+
							" onfocus=\"value=value.replace(/,/g,'')\"  "+
							" onkeydown='return ingresoDouble(window.event)' "+
							" autocomplete='off' "+
							" onchange='modificaReparacion("+id_equipo+","+id_cotizacion+","+id_tipoEstado+","+id_reparacion+",value)'>";
					cell = row.insertCell(5);
		    		cell.style.textAlign = "center";
		    		cell.innerHTML = "<a href='#' onclick= 'eliminaReparacion(this,"+id_equipo+","+id_cotizacion+","+id_tipoEstado+","+id_reparacion+")'><kbd style='background-color: red'>X</kbd></a>";
		    		
		    		$('#selReparacion').val("0");
		    		$("#selReparacion option[value='"+id_reparacion+"']").remove();
		    		let aux = $("#reparaciones_"+id_equipo+"_"+id_cotizacion).val();
		    		aux += id_tipoEstado+":"+id_reparacion+":0;";
					$("#reparaciones_"+id_equipo+"_"+id_cotizacion).val(aux);
		    	"""),format.raw/*1425.8*/("""}"""),format.raw/*1425.9*/("""
		    """),format.raw/*1426.7*/("""}"""),format.raw/*1426.8*/("""
		"""),format.raw/*1427.3*/("""}"""),format.raw/*1427.4*/("""
	"""),format.raw/*1428.2*/("""}"""),format.raw/*1428.3*/("""
	"""),format.raw/*1429.2*/("""const modificaReparacion = (id_equipo, id_cotizacion, id_tipoEstado, id_reparacion, cantReparacion) =>"""),format.raw/*1429.104*/("""{"""),format.raw/*1429.105*/("""
		"""),format.raw/*1430.3*/("""let repar = $("#reparaciones_"+id_equipo+"_"+id_cotizacion).val();
		let reparNiv1 = repar.split(";");
	    for(let i=0; i<reparNiv1.length; i++)"""),format.raw/*1432.43*/("""{"""),format.raw/*1432.44*/("""
	    	"""),format.raw/*1433.7*/("""let reparNiv2 = reparNiv1[i].split(":");
	    	if(reparNiv2[0]==id_tipoEstado && reparNiv2[1]==id_reparacion)"""),format.raw/*1434.69*/("""{"""),format.raw/*1434.70*/("""
	    		"""),format.raw/*1435.8*/("""let nuevo = reparNiv2[0]+":"+reparNiv2[1]+":"+cantReparacion+";";
	    		repar = repar.replace(reparNiv1[i]+";",nuevo);
	    	"""),format.raw/*1437.7*/("""}"""),format.raw/*1437.8*/(""" 
	    """),format.raw/*1438.6*/("""}"""),format.raw/*1438.7*/("""
	    """),format.raw/*1439.6*/("""$("#reparaciones_"+id_equipo+"_"+id_cotizacion).val(repar);
	"""),format.raw/*1440.2*/("""}"""),format.raw/*1440.3*/("""
	
	"""),format.raw/*1442.2*/("""const eliminaReparacion = (nodo, id_equipo, id_cotizacion, id_tipoEstado, id_reparacion) =>"""),format.raw/*1442.93*/("""{"""),format.raw/*1442.94*/("""
		"""),format.raw/*1443.3*/("""let nodoTd = nodo.parentNode.parentNode; 
		$(nodoTd).remove();
		let repar = $("#reparaciones_"+id_equipo+"_"+id_cotizacion).val();
		let reparNiv1 = repar.split(";");
	    for(let i=0; i<reparNiv1.length; i++)"""),format.raw/*1447.43*/("""{"""),format.raw/*1447.44*/("""
	    	"""),format.raw/*1448.7*/("""let reparNiv2 = reparNiv1[i].split(":");
	    	if(reparNiv2[0]==id_tipoEstado && reparNiv2[1]==id_reparacion)"""),format.raw/*1449.69*/("""{"""),format.raw/*1449.70*/("""
	    		"""),format.raw/*1450.8*/("""repar = repar.replace(reparNiv1[i]+";","");
	    	"""),format.raw/*1451.7*/("""}"""),format.raw/*1451.8*/(""" 
	    """),format.raw/*1452.6*/("""}"""),format.raw/*1452.7*/("""
	    """),format.raw/*1453.6*/("""$("#reparaciones_"+id_equipo+"_"+id_cotizacion).val(repar);
	"""),format.raw/*1454.2*/("""}"""),format.raw/*1454.3*/("""
	
	"""),format.raw/*1456.2*/("""const verificaMovimientos = () =>"""),format.raw/*1456.35*/("""{"""),format.raw/*1456.36*/("""
		
		
		"""),format.raw/*1459.3*/("""if(""""),_display_(/*1459.8*/bodegaOrigen/*1459.20*/.esInterna),format.raw/*1459.30*/("""" != "1")"""),format.raw/*1459.39*/("""{"""),format.raw/*1459.40*/("""
			
			"""),format.raw/*1461.4*/("""if(""""),_display_(/*1461.9*/sinVenta),format.raw/*1461.17*/("""" == "1")"""),format.raw/*1461.26*/("""{"""),format.raw/*1461.27*/("""
				"""),format.raw/*1462.5*/("""$('#tablaPrincipal').dataTable().fnAddData( [
					"TOTALES","","","","","","","","","",
					"<div id='totalCant'>1.00</div>","<div id='totalExce'>0.00</div>","<div id='totalKg'>0.00</div>","<div id='totalM2'>0.00</div>",
						"""),_display_(if(bodegaOrigen.esInterna!=1)/*1465.37*/{_display_(Seq[Any](format.raw/*1465.38*/("""
							"""),format.raw/*1466.8*/(""""",
							"""),_display_(/*1467.9*/for((est,index) <- listTipoEstado.zipWithIndex) yield /*1467.56*/{_display_(Seq[Any](format.raw/*1467.57*/("""
									"""),format.raw/*1468.10*/(""""",
							""")))}),format.raw/*1469.9*/("""
							"""),_display_(if(mapPermiso.get("parametro.movimiento-devolucion-cantCliente")!=null && mapPermiso.get("parametro.movimiento-devolucion-cantCliente").equals("1"))/*1470.157*/{_display_(Seq[Any](format.raw/*1470.158*/("""
								"""),format.raw/*1471.9*/(""""<div id='totalCantCliente'>0.00</div>",
								"<div id='totalDifCliente'>0.00</div>",
							""")))} else {null} ),format.raw/*1473.9*/("""
						""")))} else {null} ),format.raw/*1474.8*/("""
						"""),format.raw/*1475.7*/(""""","",
				] );
			"""),format.raw/*1477.4*/("""}"""),format.raw/*1477.5*/("""else"""),format.raw/*1477.9*/("""{"""),format.raw/*1477.10*/("""
				"""),format.raw/*1478.5*/("""$('#tablaPrincipal').dataTable().fnAddData( [
					"TOTALES","","","","","","","","","",
					"<div id='totalCant'>1.00</div>","","<div id='totalExce'>0.00</div>","<div id='totalKg'>0.00</div>","<div id='totalM2'>0.00</div>",
						"""),_display_(if(bodegaOrigen.esInterna!=1)/*1481.37*/{_display_(Seq[Any](format.raw/*1481.38*/("""
							"""),format.raw/*1482.8*/(""""",
							"""),_display_(/*1483.9*/for((est,index) <- listTipoEstado.zipWithIndex) yield /*1483.56*/{_display_(Seq[Any](format.raw/*1483.57*/("""
									"""),format.raw/*1484.10*/(""""",
							""")))}),format.raw/*1485.9*/("""
							"""),_display_(if(mapPermiso.get("parametro.movimiento-devolucion-cantCliente")!=null && mapPermiso.get("parametro.movimiento-devolucion-cantCliente").equals("1"))/*1486.157*/{_display_(Seq[Any](format.raw/*1486.158*/("""
								"""),format.raw/*1487.9*/(""""<div id='totalCantCliente'>0.00</div>",
								"<div id='totalDifCliente'>0.00</div>",
							""")))} else {null} ),format.raw/*1489.9*/("""
						""")))} else {null} ),format.raw/*1490.8*/("""
						"""),format.raw/*1491.7*/(""""","",
				] );
			"""),format.raw/*1493.4*/("""}"""),format.raw/*1493.5*/("""
			
			
		"""),format.raw/*1496.3*/("""}"""),format.raw/*1496.4*/("""else"""),format.raw/*1496.8*/("""{"""),format.raw/*1496.9*/("""
			"""),format.raw/*1497.4*/("""$('#tablaPrincipal').dataTable().fnAddData( [
				"TOTALES","","","","","","","","","",
				"<div id='totalCant'>1.00</div>","","","<div id='totalKg'>0.00</div>","<div id='totalM2'>0.00</div>",
					"""),_display_(if(bodegaOrigen.esInterna!=1)/*1500.36*/{_display_(Seq[Any](format.raw/*1500.37*/("""
						"""),format.raw/*1501.7*/(""""",
						"""),_display_(/*1502.8*/for((est,index) <- listTipoEstado.zipWithIndex) yield /*1502.55*/{_display_(Seq[Any](format.raw/*1502.56*/("""
								"""),format.raw/*1503.9*/(""""",
						""")))}),format.raw/*1504.8*/("""
						"""),_display_(if(mapPermiso.get("parametro.movimiento-devolucion-cantCliente")!=null && mapPermiso.get("parametro.movimiento-devolucion-cantCliente").equals("1"))/*1505.156*/{_display_(Seq[Any](format.raw/*1505.157*/("""
							"""),format.raw/*1506.8*/(""""<div id='totalCantCliente'>0.00</div>",
							"<div id='totalDifCliente'>0.00</div>",
						""")))} else {null} ),format.raw/*1508.8*/("""
					""")))} else {null} ),format.raw/*1509.7*/("""
					"""),format.raw/*1510.6*/(""""","",
			] );
		"""),format.raw/*1512.3*/("""}"""),format.raw/*1512.4*/("""
		
		
		
		"""),format.raw/*1516.3*/("""$("#tablaPrincipal").dataTable().fnDestroy();
		let tabla = document.getElementById("tablaPrincipal");
		let totCant = 0;
		let totExce = 0;
		let totKg = 0;
		let totM2 = 0;
		
		let totCantCliente = 0;
		let totDifCliente = 0;
		
		for(i=1; i<tabla.rows.length-1; i++)"""),format.raw/*1526.39*/("""{"""),format.raw/*1526.40*/("""
			"""),format.raw/*1527.4*/("""let cantidad = $(".cantidad",tabla.rows[i].cells[10]).val();
			let exceso = $(".exceso",tabla.rows[i].cells[13]).val();
			let kg = $(".KG",tabla.rows[i].cells[14]).text();
			let m2 = $(".M2",tabla.rows[i].cells[15]).text();
			
			cantidad = cantidad.replace(/,/g,'');
			exceso = exceso.replace(/,/g,'');
			kg = kg.replace(/,/g,'');
			m2 = m2.replace(/,/g,'');
			
			let total = parseFloat(cantidad) + parseFloat(exceso);
			totCant += parseFloat(cantidad);
			totExce += parseFloat(exceso);
			totKg += parseFloat(kg);
			totM2 += parseFloat(m2);
			
			if(esInterna != "1")"""),format.raw/*1543.24*/("""{"""),format.raw/*1543.25*/("""
				"""),_display_(if(mapPermiso.get("parametro.movimiento-devolucion-cantCliente")!=null && mapPermiso.get("parametro.movimiento-devolucion-cantCliente").equals("1"))/*1544.154*/{_display_(Seq[Any](format.raw/*1544.155*/("""
					
					"""),format.raw/*1546.6*/("""let cantCliente = $(".cantCliente",tabla.rows[i].cells[tabla.rows[i].cells.length-2]).val();
					let difCliente = $(".difCliente",tabla.rows[i].cells[tabla.rows[i].cells.length-1]).text();
					
					cantCliente = cantCliente.replace(/,/g,'');
					difCliente = difCliente.replace(/,/g,'');
					
					totCantCliente += parseFloat(cantCliente);
					totDifCliente += parseFloat(difCliente);
				""")))} else {null} ),format.raw/*1554.6*/("""
			"""),format.raw/*1555.4*/("""}"""),format.raw/*1555.5*/("""
			
			"""),format.raw/*1557.4*/("""if(total <= 0 )"""),format.raw/*1557.19*/("""{"""),format.raw/*1557.20*/("""
				"""),format.raw/*1558.5*/("""tabla.rows[i].style.display = 'none';
			"""),format.raw/*1559.4*/("""}"""),format.raw/*1559.5*/("""
		"""),format.raw/*1560.3*/("""}"""),format.raw/*1560.4*/("""
		
		"""),format.raw/*1562.3*/("""$("#totalCant").text(formatStandar(totCant,2));
		$("#totalExce").text(formatStandar(totExce,2));
		$("#totalKg").text(formatStandar(totKg,2));
		$("#totalM2").text(formatStandar(totM2,2));
		
		if(esInterna != "1")"""),format.raw/*1567.23*/("""{"""),format.raw/*1567.24*/("""
			"""),_display_(if(mapPermiso.get("parametro.movimiento-devolucion-cantCliente")!=null && mapPermiso.get("parametro.movimiento-devolucion-cantCliente").equals("1"))/*1568.153*/{_display_(Seq[Any](format.raw/*1568.154*/("""
				"""),format.raw/*1569.5*/("""$("#totalCantCliente").text(formatStandar(totCantCliente,2));
				$("#totalDifCliente").text(formatStandar(totDifCliente,2));
			""")))} else {null} ),format.raw/*1571.5*/("""
		"""),format.raw/*1572.3*/("""}"""),format.raw/*1572.4*/("""
		
		"""),format.raw/*1574.3*/("""document.getElementById('verifica').style.visibility = 'hidden';
		document.getElementById('modifica').style.visibility = 'visible';
		document.getElementById('aplica').style.visibility = 'visible';
		if('"""),_display_(/*1577.8*/bodegaOrigen/*1577.20*/.esInterna),format.raw/*1577.30*/("""'=='2') document.getElementById('agregarEquiposNoEnviados').style.visibility = 'hidden';
		
		
		$('#tablaPrincipal').DataTable("""),format.raw/*1580.34*/("""{"""),format.raw/*1580.35*/("""
	    	"""),format.raw/*1581.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[-1, 15, 50, 100, 200], ["ALL", 15, 50, 100, 200]],
	    	"order": [[ 4, "asc" ]],
	    	"language": """),format.raw/*1584.19*/("""{"""),format.raw/*1584.20*/("""
	        	"""),format.raw/*1585.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*1586.10*/("""}"""),format.raw/*1586.11*/("""
	  	"""),format.raw/*1587.5*/("""}"""),format.raw/*1587.6*/(""" """),format.raw/*1587.7*/(""");
		
	"""),format.raw/*1589.2*/("""}"""),format.raw/*1589.3*/("""
		
	
	
	"""),format.raw/*1593.2*/("""const modificaMovimientos = () =>"""),format.raw/*1593.35*/("""{"""),format.raw/*1593.36*/("""
		"""),format.raw/*1594.3*/("""$("#tablaPrincipal").dataTable().fnDestroy();
		let tabla = document.getElementById("tablaPrincipal");
		tabla.deleteRow(tabla.rows.length-1);
		for(i=1; i<tabla.rows.length; i++)"""),format.raw/*1597.37*/("""{"""),format.raw/*1597.38*/("""
			"""),format.raw/*1598.4*/("""tabla.rows[i].style.display = '';
		"""),format.raw/*1599.3*/("""}"""),format.raw/*1599.4*/("""
		"""),format.raw/*1600.3*/("""tblPrincipal = $('#tablaPrincipal').DataTable("""),format.raw/*1600.49*/("""{"""),format.raw/*1600.50*/("""
	    	"""),format.raw/*1601.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[15, 50, 100, 200, -1], [15, 50, 100, 200, "All"]],
	    	"order": [[ 4, "asc" ]],
	    	"language": """),format.raw/*1604.19*/("""{"""),format.raw/*1604.20*/("""
	        	"""),format.raw/*1605.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*1606.10*/("""}"""),format.raw/*1606.11*/("""
	  	"""),format.raw/*1607.5*/("""}"""),format.raw/*1607.6*/(""" """),format.raw/*1607.7*/(""");
		document.getElementById('verifica').style.visibility = 'visible';
		document.getElementById('modifica').style.visibility = 'hidden';
		document.getElementById('aplica').style.visibility = 'hidden';
		if('"""),_display_(/*1611.8*/bodegaOrigen/*1611.20*/.esInterna),format.raw/*1611.30*/("""'=='2') document.getElementById('agregarEquiposNoEnviados').style.visibility = 'visible';
	"""),format.raw/*1612.2*/("""}"""),format.raw/*1612.3*/("""
	
	"""),format.raw/*1614.2*/("""const descargaDocumento = (nombreDOC) => """),format.raw/*1614.43*/("""{"""),format.raw/*1614.44*/("""
		  """),format.raw/*1615.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*1617.2*/("""}"""),format.raw/*1617.3*/("""

	"""),format.raw/*1619.2*/("""let extArray = new Array(".gif", ".jpg", ".png", ".jpeg", ".pdf", ".xls", ".doc", ".xlsx", ".docx", ".tar", ".zip", ".rar");
	const LimitAttach = (form, file) => """),format.raw/*1620.38*/("""{"""),format.raw/*1620.39*/("""
		
		"""),format.raw/*1622.3*/("""if (!file) return;
		
		const $inputArchivos = document.querySelector("#docAdjunto");
		const archivosParaSubir = $inputArchivos.files;
		let tamanio = 0;
		let allowSubmit = false;
		
		for(let i=0; i<archivosParaSubir.length; i++)"""),format.raw/*1629.48*/("""{"""),format.raw/*1629.49*/("""
			"""),format.raw/*1630.4*/("""tamanio += archivosParaSubir[i].size;
			let nombre = archivosParaSubir[i].name;
			let extencion = nombre.substring(nombre.lastIndexOf(".")).toLowerCase();
			for (let j = 0; j < extArray.length; j++) """),format.raw/*1633.46*/("""{"""),format.raw/*1633.47*/("""
				"""),format.raw/*1634.5*/("""if (extArray[j] == extencion) """),format.raw/*1634.35*/("""{"""),format.raw/*1634.36*/(""" 
					"""),format.raw/*1635.6*/("""allowSubmit = true;
				"""),format.raw/*1636.5*/("""}"""),format.raw/*1636.6*/("""
			"""),format.raw/*1637.4*/("""}"""),format.raw/*1637.5*/("""
			"""),format.raw/*1638.4*/("""if(allowSubmit && i < archivosParaSubir.length -1)"""),format.raw/*1638.54*/("""{"""),format.raw/*1638.55*/("""
				"""),format.raw/*1639.5*/("""allowSubmit = false;
			"""),format.raw/*1640.4*/("""}"""),format.raw/*1640.5*/("""
		"""),format.raw/*1641.3*/("""}"""),format.raw/*1641.4*/("""
		
		"""),format.raw/*1643.3*/("""if (allowSubmit) """),format.raw/*1643.20*/("""{"""),format.raw/*1643.21*/("""
			"""),format.raw/*1644.4*/("""if(tamanio > 200000000)"""),format.raw/*1644.27*/("""{"""),format.raw/*1644.28*/("""
				"""),format.raw/*1645.5*/("""alert("Se permite máximo subir 200 MB,"
				+" lo que se intenta subir pesa: "+Math.round(tamanio/10000)/100+" MB");
				form.docAdjunto.value="";
			"""),format.raw/*1648.4*/("""}"""),format.raw/*1648.5*/("""else"""),format.raw/*1648.9*/("""{"""),format.raw/*1648.10*/("""
				"""),format.raw/*1649.5*/("""alert("Documento subido con éxito");
				$("#txtBtn").text("Cambiar Documento");
			"""),format.raw/*1651.4*/("""}"""),format.raw/*1651.5*/("""
		"""),format.raw/*1652.3*/("""}"""),format.raw/*1652.4*/("""else"""),format.raw/*1652.8*/("""{"""),format.raw/*1652.9*/("""
			"""),format.raw/*1653.4*/("""alert("Se permiten únicamente archivos con la extensión: "
			+ (extArray.join("  ")) + "\nPor favor, seleccione solo archivos con extensiones permitidas "
			+ "e intente de nuevo.");
			form.docAdjunto.value="";
		"""),format.raw/*1657.3*/("""}"""),format.raw/*1657.4*/("""
	"""),format.raw/*1658.2*/("""}"""),format.raw/*1658.3*/("""
		
		

	
"""),format.raw/*1663.1*/("""</script>




<script type="text/javascript">
		
			const $imagen = document.querySelector("#imagen");
			
			const cambiarTexto = (nodo) =>"""),format.raw/*1672.34*/("""{"""),format.raw/*1672.35*/("""
				"""),format.raw/*1673.5*/("""$("#textBtnComprimir").text(nodo.files.length + " imagenes seleccionadas");
			"""),format.raw/*1674.4*/("""}"""),format.raw/*1674.5*/("""
			
			"""),format.raw/*1676.4*/("""document.querySelector("#btnComprimirBlob").addEventListener("click", async () => """),format.raw/*1676.86*/("""{"""),format.raw/*1676.87*/("""
				"""),format.raw/*1677.5*/("""if ($imagen.files.length <= 0) """),format.raw/*1677.36*/("""{"""),format.raw/*1677.37*/("""
					"""),format.raw/*1678.6*/("""return;
				"""),format.raw/*1679.5*/("""}"""),format.raw/*1679.6*/("""
				"""),format.raw/*1680.5*/("""document.getElementById('enCarga').style.display="block";
				let sigla = $("#textSigla").val();
				if(sigla.length > 0)"""),format.raw/*1682.25*/("""{"""),format.raw/*1682.26*/("""
					"""),format.raw/*1683.6*/("""sigla = sigla+"_";
				"""),format.raw/*1684.5*/("""}"""),format.raw/*1684.6*/("""
				"""),format.raw/*1685.5*/("""subirArchivo2($imagen, contador, sigla);
			"""),format.raw/*1686.4*/("""}"""),format.raw/*1686.5*/(""");
			
			let contador = 0;

				
				const MAX_WIDTH = 1000;
				const MAX_HEIGHT = 750;
				const MIME_TYPE = "image/jpeg";
				const QUALITY = 0.7;

				const input = document.getElementById("img-input");
				
				function subirArchivo2($imagen, contador, sigla) """),format.raw/*1698.54*/("""{"""),format.raw/*1698.55*/("""
					"""),format.raw/*1699.6*/("""const file = $imagen.files[contador]; 
					  const blobURL = URL.createObjectURL(file);
					  const img = new Image();
					  img.src = blobURL;
					  img.onerror = function () """),format.raw/*1703.34*/("""{"""),format.raw/*1703.35*/("""
					    """),format.raw/*1704.10*/("""URL.revokeObjectURL(this.src);
					  """),format.raw/*1705.8*/("""}"""),format.raw/*1705.9*/(""";
					  
					  
					  img.onload = function () """),format.raw/*1708.33*/("""{"""),format.raw/*1708.34*/("""
					    """),format.raw/*1709.10*/("""URL.revokeObjectURL(this.src);
					    const [newWidth, newHeight] = calculateSize(img, MAX_WIDTH, MAX_HEIGHT);
					    const canvas = document.createElement("canvas");
					    canvas.width = newWidth;
					    canvas.height = newHeight;
					    const ctx = canvas.getContext("2d");
					    ctx.drawImage(img, 0, 0, newWidth, newHeight);
					    canvas.toBlob(
					      (blob) => """),format.raw/*1717.22*/("""{"""),format.raw/*1717.23*/("""
					        """),format.raw/*1718.14*/("""displayInfo('Original file', file);
					        displayInfo('Compressed file', blob);
					      
					        const myFile = new File([blob], sigla + $imagen.files[contador].name, """),format.raw/*1721.84*/("""{"""),format.raw/*1721.85*/("""
							    """),format.raw/*1722.12*/("""type: blob.type,
							"""),format.raw/*1723.8*/("""}"""),format.raw/*1723.9*/(""");
							
							$("#msgEspera").text(" cargando imagen "+(contador+1)+" de "+$imagen.files.length);
							$("#msgEspera2").text(" En proceso..... cargando imagen "+(contador+1)+" de "+$imagen.files.length);
							
							
							var formData = new FormData();
							formData.append("fotosAdjunto[0]",myFile);
							formData.append("id_guia",'"""),_display_(/*1731.36*/guia/*1731.40*/.id),format.raw/*1731.43*/("""');
							formData.append("iniCarpeta",'Fotos_Mov_');
								$.ajax("""),format.raw/*1733.16*/("""{"""),format.raw/*1733.17*/("""
									"""),format.raw/*1734.10*/("""async: false,
							        url: "/grabaAlbumFotosAjax/",
							        type: "POST",
							        method: "POST",
							        data: formData,
							        cache: false,
							        contentType: false,
							     	processData: false,
							     	success: function(rs)"""),format.raw/*1742.35*/("""{"""),format.raw/*1742.36*/("""
							     		"""),format.raw/*1743.15*/("""if(rs == "error")"""),format.raw/*1743.32*/("""{"""),format.raw/*1743.33*/("""
							     			"""),format.raw/*1744.16*/("""alertify.alert('Se presento un error, no fueron subidas las imagenes', function () """),format.raw/*1744.99*/("""{"""),format.raw/*1744.100*/("""
							     				"""),format.raw/*1745.17*/("""document.getElementById("imagen").value = "";
							     			"""),format.raw/*1746.16*/("""}"""),format.raw/*1746.17*/(""");
							     		"""),format.raw/*1747.15*/("""}"""),format.raw/*1747.16*/("""else"""),format.raw/*1747.20*/("""{"""),format.raw/*1747.21*/("""
							     			"""),format.raw/*1748.16*/("""contador++;
							     			if(contador < $imagen.files.length)"""),format.raw/*1749.51*/("""{"""),format.raw/*1749.52*/("""
							     				"""),format.raw/*1750.17*/("""subirArchivo2($imagen, contador, sigla);
							     			"""),format.raw/*1751.16*/("""}"""),format.raw/*1751.17*/("""else"""),format.raw/*1751.21*/("""{"""),format.raw/*1751.22*/("""
							     				"""),format.raw/*1752.17*/("""alertify.alert('Subidas las imagenes con exito', function () """),format.raw/*1752.78*/("""{"""),format.raw/*1752.79*/("""
									     			"""),format.raw/*1753.18*/("""document.getElementById("imagen").value = "";
									     			$("#textBtnComprimir").text("Ninguna imagen seleccionada");
									     			
									     			document.getElementById("sube1").innerHTML = 
									     				"<a href='/muestraAlbumFotos/"+rs+"'><kbd style='background-color: #7F8C8D'>Ver Album</kbd></a>";
									     			document.getElementById("sube2").innerHTML = 
									     				"<a href='/muestraAlbumFotos/"+rs+"'><kbd style='background-color: #7F8C8D'>Ver Album</kbd></a>";
									     		"""),format.raw/*1760.17*/("""}"""),format.raw/*1760.18*/(""");
												document.getElementById('enCarga').style.display="none";
							     			"""),format.raw/*1762.16*/("""}"""),format.raw/*1762.17*/("""
							     		"""),format.raw/*1763.15*/("""}"""),format.raw/*1763.16*/("""
							     	"""),format.raw/*1764.14*/("""}"""),format.raw/*1764.15*/("""
							    """),format.raw/*1765.12*/("""}"""),format.raw/*1765.13*/(""");
					      """),format.raw/*1766.12*/("""}"""),format.raw/*1766.13*/(""",
					      MIME_TYPE,
					      QUALITY
					    );
					  """),format.raw/*1770.8*/("""}"""),format.raw/*1770.9*/(""";
				"""),format.raw/*1771.5*/("""}"""),format.raw/*1771.6*/("""

				"""),format.raw/*1773.5*/("""function calculateSize(img, maxWidth, maxHeight) """),format.raw/*1773.54*/("""{"""),format.raw/*1773.55*/("""
				  """),format.raw/*1774.7*/("""let width = img.width;
				  let height = img.height;

				  if (width > height) """),format.raw/*1777.27*/("""{"""),format.raw/*1777.28*/("""
				    """),format.raw/*1778.9*/("""if (width > maxWidth) """),format.raw/*1778.31*/("""{"""),format.raw/*1778.32*/("""
				      """),format.raw/*1779.11*/("""height = Math.round((height * maxWidth) / width);
				      width = maxWidth;
				    """),format.raw/*1781.9*/("""}"""),format.raw/*1781.10*/("""
				  """),format.raw/*1782.7*/("""}"""),format.raw/*1782.8*/(""" """),format.raw/*1782.9*/("""else """),format.raw/*1782.14*/("""{"""),format.raw/*1782.15*/("""
				    """),format.raw/*1783.9*/("""if (height > maxHeight) """),format.raw/*1783.33*/("""{"""),format.raw/*1783.34*/("""
				      """),format.raw/*1784.11*/("""width = Math.round((width * maxHeight) / height);
				      height = maxHeight;
				    """),format.raw/*1786.9*/("""}"""),format.raw/*1786.10*/("""
				  """),format.raw/*1787.7*/("""}"""),format.raw/*1787.8*/("""
				  """),format.raw/*1788.7*/("""return [width, height];
				"""),format.raw/*1789.5*/("""}"""),format.raw/*1789.6*/("""

				"""),format.raw/*1791.5*/("""function displayInfo(label, file) """),format.raw/*1791.39*/("""{"""),format.raw/*1791.40*/("""
				  """),format.raw/*1792.7*/("""const p = document.createElement('p');
				  p.innerText = `$"""),format.raw/*1793.23*/("""{"""),format.raw/*1793.24*/("""label"""),format.raw/*1793.29*/("""}"""),format.raw/*1793.30*/(""" """),format.raw/*1793.31*/("""- $"""),format.raw/*1793.34*/("""{"""),format.raw/*1793.35*/("""readableBytes(file.size)"""),format.raw/*1793.59*/("""}"""),format.raw/*1793.60*/("""`;
				"""),format.raw/*1794.5*/("""}"""),format.raw/*1794.6*/("""

				"""),format.raw/*1796.5*/("""function readableBytes(bytes) """),format.raw/*1796.35*/("""{"""),format.raw/*1796.36*/("""
				  """),format.raw/*1797.7*/("""const i = Math.floor(Math.log(bytes) / Math.log(1024)),
				    sizes = ['B', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'];

				  return (bytes / Math.pow(1024, i)).toFixed(2) + ' ' + sizes[i];
				"""),format.raw/*1801.5*/("""}"""),format.raw/*1801.6*/("""
				
				
	"""),format.raw/*1804.2*/("""const verOt = (id_ot) => """),format.raw/*1804.27*/("""{"""),format.raw/*1804.28*/("""
		"""),format.raw/*1805.3*/("""var formData = new FormData();
	  	formData.append('id_ot',id_ot);
        $.ajax("""),format.raw/*1807.16*/("""{"""),format.raw/*1807.17*/("""
            """),format.raw/*1808.13*/("""url: "/verOtAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*1815.36*/("""{"""),format.raw/*1815.37*/("""
	     		"""),format.raw/*1816.9*/("""document.getElementById('mostrarLaOt').innerHTML = respuesta;
	     		$('#modalVerOt').modal('show');
	     	"""),format.raw/*1818.8*/("""}"""),format.raw/*1818.9*/("""
        """),format.raw/*1819.9*/("""}"""),format.raw/*1819.10*/(""");
	"""),format.raw/*1820.2*/("""}"""),format.raw/*1820.3*/("""
"""),format.raw/*1821.1*/("""</script>
		

		

"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,bodegaOrigen:tables.BodegaEmpresa,listEquipNoEnBodOrigen:List[List[String]],listTipoEstado:List[tables.TipoEstado],listTipoReparacion:List[tables.TipoReparacion],guia:tables.Guia,detalleMovimiento:List[List[String]],permiteExcedentes:Long,listaTransporte:List[tables.Transportista],sucursalOrigen:tables.Sucursal,comercial:tables.Comercial,sucursalDestino:tables.Sucursal,sinVenta:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,bodegaOrigen,listEquipNoEnBodOrigen,listTipoEstado,listTipoReparacion,guia,detalleMovimiento,permiteExcedentes,listaTransporte,sucursalOrigen,comercial,sucursalDestino,sinVenta)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,List[List[String]],List[tables.TipoEstado],List[tables.TipoReparacion],tables.Guia,List[List[String]],Long,List[tables.Transportista],tables.Sucursal,tables.Comercial,tables.Sucursal,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,bodegaOrigen,listEquipNoEnBodOrigen,listTipoEstado,listTipoReparacion,guia,detalleMovimiento,permiteExcedentes,listaTransporte,sucursalOrigen,comercial,sucursalDestino,sinVenta) => apply(mapDiccionario,mapPermiso,userMnu,bodegaOrigen,listEquipNoEnBodOrigen,listTipoEstado,listTipoReparacion,guia,detalleMovimiento,permiteExcedentes,listaTransporte,sucursalOrigen,comercial,sucursalDestino,sinVenta)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuMovimientos/movimientoOrigenDestinoModifica.scala.html
                  HASH: 91cc61996c5c4dbf48f678d5885271529bd5ff8d
                  MATRIX: 1246->1|1850->512|1885->521|1902->529|1942->531|1972->535|2041->583|2072->587|2240->729|2285->753|2314->756|2355->776|2385->779|2650->1018|2725->1072|2756->1076|3379->1672|3400->1684|3429->1692|3515->1751|3528->1755|3557->1763|3661->1840|3682->1852|3727->1876|4101->2223|4114->2227|4158->2249|4301->2365|4314->2369|4366->2400|4798->2804|4838->2805|4879->2818|5042->2954|5055->2958|5088->2970|5174->3037|5213->3038|5254->3051|5417->3187|5430->3191|5463->3203|5679->3388|5717->3398|6071->3725|6084->3729|6125->3749|6562->4159|6601->4160|6640->4171|6972->4483|7012->4484|7052->4495|7125->4540|7139->4544|7175->4558|7461->4812|7500->4822|7717->5011|7757->5012|7797->5023|7834->5040|7874->5041|7914->5052|7983->5093|7997->5097|8033->5111|8155->5201|8192->5210|8366->5356|8391->5370|8422->5378|8710->5638|8734->5652|8768->5664|8966->5834|8992->5849|9023->5857|9311->6117|9336->6132|9370->6144|9809->6555|9829->6565|9882->6596|9912->6598|9932->6608|9984->6637|10024->6648|10044->6657|10098->6688|10178->6740|10197->6749|10250->6780|10697->7199|10717->7209|10772->7242|10802->7244|10822->7254|10876->7285|10916->7296|10936->7305|11000->7346|11080->7398|11099->7407|11162->7448|11553->7811|11572->7820|11611->7837|12422->8620|12436->8624|12477->8643|12676->8814|12716->8815|12756->8826|12823->8873|12863->8874|12903->8885|12960->8914|12974->8918|13007->8929|13133->9023|13170->9032|13483->9317|13507->9331|13539->9341|13607->9381|13631->9395|13663->9405|13932->9646|13972->9647|14011->9657|14080->9706|14120->9707|14159->9717|14215->9741|14261->9759|14430->9900|14470->9901|14509->9911|14578->9952|14627->9984|14667->9985|14707->9996|14798->10059|14817->10068|14850->10079|14890->10090|14938->10106|15126->10265|15167->10266|15207->10277|15360->10385|15425->10405|15461->10413|15531->10456|15580->10488|15620->10489|15657->10498|15788->10601|15804->10607|15833->10614|15926->10679|15942->10685|15971->10692|16013->10706|16029->10712|16058->10719|16097->10729|16210->10814|16250->10815|16290->10826|16364->10880|16404->10881|16444->10892|16562->10982|16578->10988|16608->10996|16737->11097|16753->11103|16783->11111|16884->11180|16933->11200|17033->11272|17049->11278|17079->11286|17125->11304|17144->11313|17196->11343|17235->11353|17346->11436|17386->11437|17426->11448|17500->11502|17540->11503|17580->11514|17641->11547|17657->11553|17686->11560|17734->11576|17783->11596|17876->11661|17892->11667|17921->11674|17955->11680|17972->11686|18002->11693|18114->11777|18130->11783|18159->11790|18193->11796|18210->11802|18240->11809|18335->11876|18351->11882|18380->11889|18455->11936|18471->11942|18500->11949|18576->11997|18592->12003|18621->12010|18744->12105|18760->12111|18789->12118|18819->12120|18835->12126|18864->12133|18895->12136|18911->12142|18940->12149|19069->12250|19085->12256|19115->12264|19324->12445|19340->12451|19369->12458|19399->12460|19415->12466|19444->12473|19494->12495|19510->12501|19540->12509|19794->12735|19810->12741|19839->12748|19871->12752|19887->12758|19916->12765|19955->12775|19972->12781|20002->12788|20035->12792|20052->12798|20082->12805|20115->12809|20140->12823|20170->12830|20270->12902|20310->12903|20350->12914|20487->13023|20503->13029|20532->13036|20562->13038|20578->13044|20607->13051|20646->13061|20663->13067|20694->13075|20767->13120|20807->13121|20849->13134|20934->13191|20950->13197|20979->13204|21009->13206|21025->13212|21054->13219|21121->13258|21137->13264|21166->13271|21196->13273|21212->13279|21241->13286|21298->13315|21314->13321|21343->13328|21373->13330|21390->13336|21420->13343|21493->13396|21533->13397|21575->13410|21652->13459|21668->13465|21697->13472|21727->13474|21743->13480|21772->13487|21839->13526|21855->13532|21884->13539|21914->13541|21930->13547|21959->13554|22016->13583|22032->13589|22061->13596|22091->13598|22108->13604|22138->13611|22219->13660|22259->13671|22299->13691|22339->13692|22379->13703|22502->13798|22518->13804|22547->13811|22577->13813|22593->13819|22622->13826|22661->13836|22678->13842|22709->13850|22782->13895|22822->13896|22864->13909|22949->13966|22965->13972|22994->13979|23024->13981|23040->13987|23069->13994|23136->14033|23152->14039|23181->14046|23211->14048|23227->14054|23256->14061|23313->14090|23329->14096|23358->14103|23388->14105|23405->14111|23435->14118|23508->14171|23548->14172|23590->14185|23667->14234|23683->14240|23712->14247|23742->14249|23758->14255|23787->14262|23854->14301|23870->14307|23899->14314|23929->14316|23945->14322|23974->14329|24031->14358|24047->14364|24076->14371|24106->14373|24123->14379|24153->14386|24234->14435|24274->14446|24322->14462|24371->14482|24517->14600|24533->14606|24562->14613|24592->14615|24608->14621|24637->14628|24676->14638|24693->14644|24724->14652|24847->14747|24863->14753|24893->14761|25092->14932|25108->14938|25138->14946|25192->14972|25208->14978|25237->14985|25267->14987|25283->14993|25312->15000|25484->15144|25500->15150|25529->15157|25559->15159|25575->15165|25604->15172|25635->15175|25652->15181|25683->15189|25786->15264|25802->15270|25831->15277|25861->15279|25877->15285|25906->15292|25937->15295|25954->15301|25985->15309|26064->15360|26104->15361|26144->15372|26255->15455|26271->15461|26301->15469|26344->15484|26360->15490|26390->15497|26421->15499|26438->15505|26468->15512|26561->15577|26577->15583|26607->15591|26655->15611|26672->15617|26702->15624|26733->15626|26750->15632|26780->15639|26838->15669|26902->15716|26942->15717|26983->15729|27097->15815|27137->15816|27180->15830|27361->15983|27377->15989|27406->15996|27436->15998|27452->16004|27481->16011|27511->16013|27538->16018|27698->16150|27725->16155|27778->16180|27794->16186|27824->16194|27956->16297|27986->16298|28031->16314|28119->16374|28132->16377|28162->16385|28193->16387|28223->16388|28269->16405|28309->16417|28325->16423|28354->16430|28384->16432|28400->16438|28429->16445|28459->16447|28486->16452|28564->16501|28594->16502|28638->16517|28668->16518|28711->16532|28869->16669|28910->16670|28953->16684|29134->16837|29150->16843|29179->16850|29209->16852|29225->16858|29254->16865|29284->16867|29311->16872|29441->16974|29454->16977|29484->16985|29542->17015|29569->17020|29622->17045|29638->17051|29668->17059|29800->17162|29830->17163|29875->17179|29963->17239|29976->17242|30006->17250|30037->17252|30067->17253|30113->17270|30153->17282|30169->17288|30198->17295|30228->17297|30244->17303|30273->17310|30303->17312|30330->17317|30408->17366|30438->17367|30482->17382|30512->17383|30555->17397|30616->17438|30656->17439|30699->17453|30880->17606|30896->17612|30925->17619|30955->17621|30971->17627|31000->17634|31030->17636|31057->17641|31306->17862|31319->17865|31356->17880|31412->17908|31439->17913|31472->17917|31497->17931|31527->17938|31560->17942|31577->17948|31607->17955|31640->17959|31657->17965|31687->17972|31726->17982|31740->17985|31778->18000|31811->18004|31825->18007|31856->18015|31927->18057|31957->18058|32005->18077|32086->18130|32113->18135|32145->18139|32169->18153|32198->18160|32230->18164|32246->18170|32275->18177|32307->18181|32323->18187|32353->18194|32392->18204|32406->18207|32444->18222|32477->18226|32491->18229|32522->18237|32573->18259|32603->18260|32636->18264|32666->18265|32715->18285|32764->18306|32791->18311|32823->18315|32847->18329|32876->18336|32908->18340|32924->18346|32953->18353|32985->18357|33001->18363|33031->18370|33070->18380|33084->18383|33122->18398|33155->18402|33169->18405|33200->18413|33251->18435|33281->18436|33356->18483|33372->18489|33402->18497|33534->18600|33564->18601|33609->18617|33697->18677|33710->18680|33740->18688|33771->18690|33801->18691|33847->18708|33887->18720|33903->18726|33932->18733|33962->18735|33978->18741|34007->18748|34037->18750|34064->18755|34142->18804|34172->18805|34216->18820|34246->18821|34289->18835|34344->18858|34385->18870|34434->18887|34623->19047|34664->19048|34705->19060|34957->19284|34973->19290|35002->19297|35032->19299|35048->19305|35077->19312|35129->19336|35145->19342|35175->19350|35441->19588|35457->19594|35486->19601|35519->19606|35535->19612|35565->19619|35715->19741|35731->19747|35760->19754|35790->19756|35807->19762|35837->19769|35869->19772|35886->19778|35917->19786|35985->19809|36041->19820|36078->19829|36125->19844|36160->19851|36422->20085|36462->20086|36498->20094|36770->20322|36804->20328|37113->20608|37128->20612|37159->20620|38734->22167|38748->22171|38793->22193|39654->23027|39708->23064|39748->23065|39785->23074|39862->23123|39878->23129|39907->23136|39938->23139|39954->23145|39983->23152|40015->23156|40031->23162|40061->23169|40094->23173|40111->23179|40141->23186|40252->23269|40268->23275|40297->23282|40328->23285|40344->23291|40373->23298|40471->23368|40487->23374|40516->23381|40547->23384|40563->23390|40592->23397|40690->23467|40706->23473|40735->23480|40766->23483|40782->23489|40811->23496|40878->23531|40913->23538|42160->24758|42251->24827|42392->24939|42433->24940|42466->24945|43006->25457|43046->25458|43082->25466|43146->25510|43186->25511|43222->25519|43279->25548|43293->25552|43326->25563|43442->25648|43476->25654|44090->26224|44122->26228|44441->26519|44465->26533|44511->26557|45056->27071|45087->27074|45157->27115|45187->27116|45217->27117|45266->27137|45296->27138|45326->27140|45387->27172|45417->27173|45447->27174|45496->27194|45526->27195|45556->27197|45617->27229|45647->27230|45677->27231|45726->27251|45756->27252|45786->27254|45847->27286|45877->27287|45907->27288|45954->27306|45984->27307|46014->27309|46075->27341|46105->27342|46135->27343|46183->27362|46213->27363|46243->27365|46304->27397|46334->27398|46364->27399|46412->27418|46442->27419|46472->27421|46533->27453|46563->27454|46593->27455|46642->27475|46672->27476|46702->27478|46764->27511|46794->27512|46824->27513|46872->27532|46902->27533|46932->27535|46994->27568|47024->27569|47054->27570|47102->27589|47132->27590|47162->27592|47224->27625|47254->27626|47284->27627|47333->27647|47363->27648|47393->27650|47455->27683|47485->27684|47515->27685|47564->27705|47594->27706|47624->27708|47686->27741|47716->27742|47746->27743|47794->27762|47824->27763|47854->27765|47916->27798|47946->27799|47976->27800|48024->27819|48054->27820|48084->27822|48146->27855|48176->27856|48206->27857|48254->27876|48284->27877|48344->27909|48384->27910|48415->27913|48477->27946|48507->27947|48537->27948|48604->27986|48634->27987|48665->27991|48729->28038|48769->28039|48801->28043|48859->28073|48891->28083|48922->28085|48952->28086|48982->28087|49035->28111|49065->28112|49100->28116|49134->28122|49192->28152|49240->28178|49271->28180|49301->28181|49331->28182|49403->28224|49434->28225|49465->28228|49523->28258|49571->28284|49602->28286|49632->28287|49662->28288|49734->28330|49765->28331|49796->28334|49854->28364|49902->28390|49933->28392|49963->28393|49993->28394|50065->28436|50096->28437|50143->28440|50172->28441|50295->28536|50309->28540|50353->28562|50407->28588|50429->28600|50461->28610|50545->28665|50575->28666|50607->28670|50704->28739|50733->28740|50767->28746|50850->28800|50880->28801|50912->28805|51182->29047|51211->29048|51245->29054|51314->29094|51344->29095|51376->29099|51518->29212|51548->29213|51581->29218|51763->29371|51793->29372|51827->29378|51916->29439|51945->29440|51978->29444|52008->29445|52042->29451|52119->29500|52148->29501|52180->29505|52209->29506|52240->29509|52269->29510|52301->29514|52399->29583|52429->29584|52463->29590|52566->29665|52588->29677|52618->29685|52692->29731|52706->29735|52750->29757|52792->29770|52822->29771|52864->29784|53128->30019|53158->30020|53191->30025|53260->30066|53289->30067|53326->30076|53356->30077|53422->30114|53452->30115|53488->30123|53663->30269|53693->30270|53734->30282|53841->30360|53871->30361|53904->30366|53933->30367|53962->30368|54021->30400|54066->30428|54106->30429|54139->30434|54179->30446|54190->30447|54220->30455|54253->30460|54264->30461|54300->30475|54333->30480|54344->30481|54377->30492|54410->30497|54421->30498|54455->30510|54488->30515|54499->30516|54533->30528|54567->30533|54579->30534|54614->30546|54656->30557|54689->30562|54807->30651|54837->30652|54873->30660|55016->30774|55046->30775|55087->30787|55194->30865|55224->30866|55257->30871|55286->30872|55315->30873|55401->30930|55431->30931|55467->30939|55642->31085|55672->31086|55713->31098|55820->31176|55850->31177|55883->31182|55912->31183|55941->31184|56105->31319|56135->31320|56167->31324|56326->31454|56356->31455|56389->31460|56501->31543|56531->31544|56565->31550|56615->31572|56644->31573|56676->31577|56705->31578|56737->31582|56784->31600|56814->31601|56847->31606|56918->31649|56942->31663|56978->31677|57009->31680|57031->31692|57066->31704|57186->31796|57211->31810|57248->31824|57279->31825|57346->31862|57377->31863|57418->31875|57482->31910|57512->31911|57546->31917|57575->31918|57606->31921|57635->31922|57671->31930|57700->31931|57773->31975|57803->31976|57834->31979|57883->31999|57913->32000|57945->32004|57996->32027|58010->32031|58044->32043|58078->32049|58107->32050|58139->32054|58168->32055|58200->32059|58240->32071|58254->32075|58288->32087|58412->32182|58442->32183|58485->32197|58756->32439|58786->32440|58825->32450|58877->32473|58907->32474|58947->32485|59058->32567|59088->32568|59129->32580|59180->32603|59194->32607|59228->32619|59271->32633|59301->32634|59342->32646|59372->32647|59428->32674|59458->32675|59498->32686|59564->32723|59594->32724|59635->32736|59695->32767|59725->32768|59766->32780|59796->32781|59829->32785|59859->32786|59894->32793|59952->32823|59981->32824|60018->32833|60048->32834|60087->32844|60117->32845|60150->32850|60179->32851|60209->32853|60238->32854|60270->32858|60324->32883|60354->32884|60385->32887|60507->32980|60537->32981|60569->32985|60660->33048|60684->33062|60720->33076|60793->33119|60824->33120|60857->33125|61155->33395|61184->33396|61217->33401|61246->33402|61278->33406|61307->33407|61339->33411|61540->33583|61570->33584|61603->33589|61903->33861|61932->33862|61964->33866|62017->33890|62047->33891|62080->33896|62145->33932|62175->33933|62209->33939|62523->34224|62553->34225|62588->34232|62652->34268|62681->34269|62714->34274|62743->34275|62776->34280|62883->34359|62912->34360|62944->34364|62974->34365|63007->34370|63132->34466|63162->34467|63196->34473|63499->34748|63529->34749|63563->34755|63592->34756|63623->34759|63652->34760|63683->34763|63726->34778|63755->34779|63787->34783|63913->34880|63943->34881|63974->34884|64175->35056|64205->35057|64237->35061|64292->35087|64322->35088|64355->35093|64425->35134|64455->35135|64489->35141|64665->35289|64694->35290|64726->35294|64755->35295|64786->35298|64815->35299|64853->35309|64877->35323|64915->35339|64995->35390|65025->35391|65057->35395|65112->35421|65142->35422|65175->35427|65263->35486|65293->35487|65327->35493|65503->35641|65532->35642|65564->35646|65593->35647|65627->35653|65656->35654|65688->35658|65717->35659|65749->35663|65804->35689|65834->35690|65867->35695|65973->35773|66002->35774|66033->35777|66062->35778|66099->35787|66173->35832|66203->35833|66235->35837|66405->35977|66436->35978|66469->35983|66580->36066|66609->36067|66642->36072|66671->36073|66703->36077|66732->36078|66764->36082|67103->36392|67133->36393|67166->36398|67419->36623|67448->36624|67480->36628|67510->36629|67543->36634|67576->36639|67615->36656|67651->36663|67681->36664|67715->36670|67858->36784|67888->36785|67923->36792|68037->36877|68067->36878|68102->36885|68131->36886|68164->36890|68194->36891|68228->36897|68513->37154|68542->37155|68574->37159|68603->37160|68643->37172|68691->37191|68721->37192|68754->37197|69104->37518|69134->37519|69168->37525|69256->37584|69286->37585|69321->37592|69409->37652|69438->37653|69471->37658|69500->37659|69538->37669|69575->37677|69605->37678|69639->37684|69771->37788|69800->37789|69833->37793|69863->37794|69897->37800|70049->37924|70078->37925|70115->37934|70144->37935|70173->37936|70211->37946|70235->37960|70273->37976|70353->38027|70383->38028|70416->38033|70766->38354|70796->38355|70830->38361|70918->38420|70948->38421|70983->38428|71071->38488|71100->38489|71133->38494|71162->38495|71200->38505|71237->38513|71267->38514|71301->38520|71433->38624|71462->38625|71495->38629|71525->38630|71559->38636|71711->38760|71740->38761|71772->38765|71801->38766|71841->38778|72172->39080|72202->39081|72385->39235|72426->39236|72460->39242|72557->39295|72589->39299|72618->39300|72649->39303|72678->39304|72708->39306|72737->39307|72769->39311|72856->39369|72886->39370|72917->39373|73361->39789|73390->39790|73426->39798|73524->39867|73554->39868|73585->39871|74445->40701|74471->40715|74502->40722|75519->41710|75560->41711|75595->41717|75781->41875|75846->41922|75887->41923|75924->41931|76121->42099|76149->42104|76242->42165|76433->42326|76475->42327|76511->42334|77113->42891|77182->42915|77214->42918|77410->43084|77441->43085|77475->43090|77608->43193|77639->43194|77674->43200|77708->43205|77739->43213|77778->43222|77809->43223|77845->43230|77963->43319|77993->43320|78027->43324|78058->43325|78094->43332|78167->43376|78197->43377|78237->43388|78267->43389|78300->43393|78330->43394|78362->43397|78438->43443|78469->43444|78505->43451|78678->43594|78709->43595|78750->43606|78857->43683|78888->43684|78922->43689|78952->43690|78982->43691|79134->43814|79164->43815|79197->43819|79338->43929|79370->43930|79402->43933|79667->44168|79698->44169|79731->44173|79864->44275|79896->44276|79930->44281|80104->44426|80134->44427|80166->44430|80196->44431|80231->44437|80300->44476|80331->44477|80364->44481|80479->44566|80510->44567|80544->44572|80664->44662|80695->44663|80730->44669|80808->44717|80839->44718|80875->44725|80946->44766|80977->44767|81014->44775|81200->44932|81230->44933|81265->44939|81295->44940|81330->44946|81687->45273|81718->45274|81754->45281|81844->45341|81875->45342|81912->45350|82003->45412|82033->45413|82068->45419|82098->45420|82139->45432|82177->45440|82208->45441|82244->45448|82379->45554|82409->45555|82443->45559|82474->45560|82510->45567|82664->45692|82694->45693|82728->45698|82758->45699|82797->45709|82822->45723|82861->45739|82942->45790|82973->45791|83008->45797|83086->45845|83117->45846|83153->45853|83241->45911|83272->45912|83309->45920|83495->46077|83525->46078|83560->46084|83590->46085|83625->46091|83982->46418|84013->46419|84049->46426|84139->46486|84170->46487|84207->46495|84298->46557|84328->46558|84363->46564|84393->46565|84434->46577|84472->46585|84503->46586|84539->46593|84674->46699|84704->46700|84738->46704|84769->46705|84805->46712|84959->46837|84989->46838|85023->46843|85053->46844|85090->46852|85120->46853|85154->46858|85184->46859|85217->46863|85247->46864|85284->46872|85333->46891|85364->46892|85398->46897|85745->47214|85776->47215|85811->47221|85900->47280|85931->47281|85967->47288|86056->47348|86086->47349|86120->47354|86150->47355|86189->47365|86227->47373|86258->47374|86293->47380|86426->47484|86456->47485|86490->47489|86521->47490|86556->47496|86708->47619|86738->47620|86776->47629|86806->47630|86845->47640|86870->47654|86909->47670|86990->47721|87021->47722|87055->47727|87402->48044|87433->48045|87468->48051|87557->48110|87588->48111|87624->48118|87713->48178|87743->48179|87777->48184|87807->48185|87846->48195|87884->48203|87915->48204|87950->48210|88083->48314|88113->48315|88147->48319|88178->48320|88213->48326|88365->48449|88395->48450|88428->48454|88458->48455|88501->48469|88700->48638|88731->48639|88765->48644|88856->48705|88887->48706|88922->48712|89014->48775|89044->48776|89077->48780|89107->48781|89144->48789|89182->48797|89213->48798|89247->48803|89389->48916|89419->48917|89452->48921|89483->48922|89517->48927|89678->49059|89708->49060|89745->49068|89809->49102|89840->49103|89885->49119|91585->50789|91616->50790|91656->50800|91785->50899|91816->50900|91857->50911|92386->51410|92417->51411|92455->51420|92486->51421|92524->51430|92747->51623|92778->51624|92818->51634|93000->51786|93031->51787|93075->51801|93185->51881|93216->51882|93252->51889|93282->51890|93312->51891|93728->52277|93759->52278|93797->52287|93907->52367|93938->52368|93978->52378|94059->52430|94090->52431|94128->52440|94158->52441|94203->52457|94333->52558|94363->52559|94396->52563|94565->52703|94595->52704|94626->52706|94656->52707|94689->52711|94829->52820|94861->52821|94893->52824|95845->53746|95876->53747|95910->53752|96038->53850|96069->53851|96104->53857|96170->53893|96201->53894|96241->53904|96370->54003|96401->54004|96442->54015|96610->54153|96641->54154|96683->54166|97778->55231|97809->55232|97849->55242|97880->55243|97918->55252|97949->55253|97983->55258|98013->55259|98046->55263|98076->55264|98109->55268|98921->56050|98952->56051|98990->56060|99118->56158|99149->56159|99189->56169|99713->56664|99744->56665|99781->56673|99811->56674|99848->56682|100066->56870|100097->56871|100135->56880|100314->57029|100345->57030|100388->57043|100497->57122|100528->57123|100563->57129|100593->57130|100623->57131|101018->57497|101048->57498|101080->57501|101197->57588|101228->57589|101260->57592|101311->57613|101342->57614|101375->57618|101441->57654|101472->57655|101509->57663|101636->57760|101667->57761|101705->57770|103502->59538|103532->59539|103568->59546|103598->59547|103630->59550|103660->59551|103691->59553|103721->59554|103752->59556|103885->59658|103917->59659|103949->59662|104124->59807|104155->59808|104191->59815|104330->59924|104361->59925|104398->59933|104553->60059|104583->60060|104619->60067|104649->60068|104684->60074|104774->60135|104804->60136|104837->60140|104958->60231|104989->60232|105021->60235|105262->60446|105293->60447|105329->60454|105468->60563|105499->60564|105536->60572|105615->60622|105645->60623|105681->60630|105711->60631|105746->60637|105836->60698|105866->60699|105899->60703|105962->60736|105993->60737|106031->60746|106064->60751|106087->60763|106120->60773|106159->60782|106190->60783|106227->60791|106260->60796|106291->60804|106330->60813|106361->60814|106395->60819|106683->61078|106724->61079|106761->61087|106801->61099|106866->61146|106907->61147|106947->61157|106991->61169|107178->61326|107220->61327|107258->61336|107400->61433|107453->61441|107489->61448|107537->61467|107567->61468|107600->61472|107631->61473|107665->61478|107956->61740|107997->61741|108034->61749|108074->61761|108139->61808|108180->61809|108220->61819|108264->61831|108451->61988|108493->61989|108531->61998|108673->62095|108726->62103|108762->62110|108810->62129|108840->62130|108880->62141|108910->62142|108943->62146|108973->62147|109006->62151|109264->62380|109305->62381|109341->62388|109380->62399|109445->62446|109486->62447|109524->62456|109567->62467|109753->62623|109795->62624|109832->62632|109972->62727|110024->62734|110059->62740|110105->62757|110135->62758|110176->62770|110476->63040|110507->63041|110540->63045|111152->63627|111183->63628|111367->63782|111409->63783|111450->63795|111892->64192|111925->64196|111955->64197|111992->64205|112037->64220|112068->64221|112102->64226|112172->64267|112202->64268|112234->64271|112264->64272|112299->64278|112544->64493|112575->64494|112758->64647|112800->64648|112834->64653|113009->64783|113041->64786|113071->64787|113106->64793|113340->64999|113363->65011|113396->65021|113554->65149|113585->65150|113621->65157|113794->65300|113825->65301|113866->65312|113973->65389|114004->65390|114038->65395|114068->65396|114098->65397|114134->65404|114164->65405|114202->65414|114265->65447|114296->65448|114328->65451|114537->65630|114568->65631|114601->65635|114666->65671|114696->65672|114728->65675|114804->65721|114835->65722|114871->65729|115044->65872|115075->65873|115116->65884|115223->65961|115254->65962|115288->65967|115318->65968|115348->65969|115586->66179|115609->66191|115642->66201|115762->66292|115792->66293|115825->66297|115896->66338|115927->66339|115961->66344|116094->66448|116124->66449|116156->66452|116348->66614|116379->66615|116414->66621|116676->66853|116707->66854|116740->66858|116972->67060|117003->67061|117037->67066|117097->67096|117128->67097|117164->67104|117217->67128|117247->67129|117280->67133|117310->67134|117343->67138|117423->67188|117454->67189|117488->67194|117541->67218|117571->67219|117603->67222|117633->67223|117668->67229|117715->67246|117746->67247|117779->67251|117832->67274|117863->67275|117897->67280|118076->67430|118106->67431|118139->67435|118170->67436|118204->67441|118317->67525|118347->67526|118379->67529|118409->67530|118442->67534|118472->67535|118505->67539|118750->67755|118780->67756|118811->67758|118841->67759|118880->67769|119050->67909|119081->67910|119115->67915|119223->67994|119253->67995|119290->68003|119402->68085|119433->68086|119467->68091|119528->68122|119559->68123|119594->68129|119635->68141|119665->68142|119699->68147|119850->68268|119881->68269|119916->68275|119968->68298|119998->68299|120032->68304|120105->68348|120135->68349|120431->68615|120462->68616|120497->68622|120707->68802|120738->68803|120778->68813|120845->68851|120875->68852|120955->68902|120986->68903|121026->68913|121444->69301|121475->69302|121519->69316|121731->69498|121762->69499|121804->69511|121857->69535|121887->69536|122265->69885|122280->69889|122306->69892|122406->69962|122437->69963|122477->69973|122788->70254|122819->70255|122864->70270|122911->70287|122942->70288|122988->70304|123101->70387|123133->70388|123180->70405|123271->70466|123302->70467|123349->70484|123380->70485|123414->70489|123445->70490|123491->70506|123583->70568|123614->70569|123661->70586|123747->70642|123778->70643|123812->70647|123843->70648|123890->70665|123981->70726|124012->70727|124060->70745|124605->71260|124636->71261|124753->71348|124784->71349|124829->71364|124860->71365|124904->71379|124935->71380|124977->71392|125008->71393|125052->71407|125083->71408|125174->71470|125204->71471|125239->71477|125269->71478|125304->71484|125383->71533|125414->71534|125450->71541|125561->71622|125592->71623|125630->71632|125682->71654|125713->71655|125754->71666|125869->71752|125900->71753|125936->71760|125966->71761|125996->71762|126031->71767|126062->71768|126100->71777|126154->71801|126185->71802|126226->71813|126343->71901|126374->71902|126410->71909|126440->71910|126476->71917|126533->71945|126563->71946|126598->71952|126662->71986|126693->71987|126729->71994|126820->72055|126851->72056|126886->72061|126917->72062|126948->72063|126981->72066|127012->72067|127066->72091|127097->72092|127133->72099|127163->72100|127198->72106|127258->72136|127289->72137|127325->72144|127556->72346|127586->72347|127627->72359|127682->72384|127713->72385|127745->72388|127857->72470|127888->72471|127931->72484|128183->72706|128214->72707|128252->72716|128390->72825|128420->72826|128458->72835|128489->72836|128522->72840|128552->72841|128582->72842
                  LINES: 28->1|39->8|41->10|41->10|41->10|43->12|43->12|45->14|47->16|47->16|48->17|48->17|50->19|57->26|57->26|58->27|69->38|69->38|69->38|70->39|70->39|70->39|72->41|72->41|72->41|81->50|81->50|81->50|84->53|84->53|84->53|94->63|94->63|95->64|98->67|98->67|98->67|101->70|101->70|102->71|105->74|105->74|105->74|110->79|111->80|120->89|120->89|120->89|128->97|128->97|129->98|133->102|133->102|134->103|134->103|134->103|134->103|137->106|138->107|141->110|141->110|142->111|143->112|143->112|144->113|144->113|144->113|144->113|147->116|148->117|153->122|153->122|153->122|159->128|159->128|159->128|164->133|164->133|164->133|170->139|170->139|170->139|182->151|182->151|182->151|182->151|182->151|182->151|182->151|182->151|182->151|183->152|183->152|183->152|196->165|196->165|196->165|196->165|196->165|196->165|196->165|196->165|196->165|197->166|197->166|197->166|209->178|209->178|209->178|231->200|231->200|231->200|235->204|235->204|236->205|237->206|237->206|238->207|238->207|238->207|238->207|241->210|242->211|253->222|253->222|253->222|254->223|254->223|254->223|264->233|264->233|265->234|266->235|266->235|267->236|268->237|270->239|274->243|274->243|275->244|276->245|276->245|276->245|277->246|278->247|278->247|278->247|279->248|280->249|281->250|281->250|282->251|284->253|286->255|287->256|290->259|290->259|290->259|291->260|293->262|293->262|293->262|294->263|294->263|294->263|295->264|295->264|295->264|296->265|298->267|298->267|299->268|300->269|300->269|301->270|302->271|302->271|302->271|304->273|304->273|304->273|308->277|310->279|311->280|311->280|311->280|312->281|312->281|312->281|313->282|315->284|315->284|316->285|317->286|317->286|318->287|318->287|318->287|318->287|319->288|321->290|321->290|321->290|321->290|321->290|321->290|321->290|322->291|322->291|322->291|322->291|322->291|322->291|325->294|325->294|325->294|326->295|326->295|326->295|327->296|327->296|327->296|329->298|329->298|329->298|329->298|329->298|329->298|329->298|329->298|329->298|332->301|332->301|332->301|336->305|336->305|336->305|336->305|336->305|336->305|337->306|337->306|337->306|341->310|341->310|341->310|341->310|341->310|341->310|341->310|341->310|341->310|341->310|341->310|341->310|341->310|341->310|341->310|344->313|344->313|345->314|346->315|346->315|346->315|346->315|346->315|346->315|346->315|346->315|346->315|347->316|347->316|348->317|348->317|348->317|348->317|348->317|348->317|348->317|349->318|349->318|349->318|349->318|349->318|349->318|349->318|349->318|349->318|349->318|349->318|349->318|350->319|350->319|351->320|351->320|351->320|351->320|351->320|351->320|351->320|352->321|352->321|352->321|352->321|352->321|352->321|352->321|352->321|352->321|352->321|352->321|352->321|353->322|354->323|355->324|355->324|356->325|357->326|357->326|357->326|357->326|357->326|357->326|357->326|357->326|357->326|358->327|358->327|359->328|359->328|359->328|359->328|359->328|359->328|359->328|360->329|360->329|360->329|360->329|360->329|360->329|360->329|360->329|360->329|360->329|360->329|360->329|361->330|361->330|362->331|362->331|362->331|362->331|362->331|362->331|362->331|363->332|363->332|363->332|363->332|363->332|363->332|363->332|363->332|363->332|363->332|363->332|363->332|364->333|365->334|366->335|368->337|369->338|369->338|369->338|369->338|369->338|369->338|369->338|369->338|369->338|372->341|372->341|372->341|376->345|376->345|376->345|377->346|377->346|377->346|377->346|377->346|377->346|382->351|382->351|382->351|382->351|382->351|382->351|382->351|382->351|382->351|383->352|383->352|383->352|383->352|383->352|383->352|383->352|383->352|383->352|384->353|384->353|385->354|386->355|386->355|386->355|386->355|386->355|386->355|386->355|386->355|386->355|387->356|387->356|387->356|387->356|387->356|387->356|387->356|387->356|387->356|389->358|389->358|389->358|390->359|391->360|391->360|392->361|394->363|394->363|394->363|394->363|394->363|394->363|394->363|394->363|399->368|399->368|400->369|400->369|400->369|402->371|402->371|403->372|404->373|404->373|404->373|404->373|404->373|405->374|405->374|405->374|405->374|405->374|405->374|405->374|405->374|405->374|406->375|406->375|407->376|407->376|408->377|410->379|410->379|411->380|413->382|413->382|413->382|413->382|413->382|413->382|413->382|413->382|417->386|417->386|417->386|418->387|418->387|419->388|419->388|419->388|421->390|421->390|422->391|423->392|423->392|423->392|423->392|423->392|424->393|424->393|424->393|424->393|424->393|424->393|424->393|424->393|424->393|425->394|425->394|426->395|426->395|427->396|429->398|429->398|430->399|432->401|432->401|432->401|432->401|432->401|432->401|432->401|432->401|437->406|437->406|437->406|437->406|437->406|437->406|437->406|437->406|437->406|437->406|437->406|437->406|437->406|437->406|437->406|437->406|437->406|437->406|437->406|437->406|438->407|438->407|439->408|440->409|440->409|440->409|440->409|440->409|440->409|440->409|440->409|440->409|440->409|440->409|440->409|440->409|440->409|440->409|440->409|440->409|441->410|441->410|441->410|441->410|442->411|442->411|442->411|442->411|442->411|442->411|442->411|442->411|442->411|442->411|442->411|442->411|442->411|442->411|442->411|442->411|442->411|442->411|443->412|443->412|445->414|445->414|445->414|447->416|447->416|448->417|449->418|449->418|449->418|449->418|449->418|450->419|450->419|450->419|450->419|450->419|450->419|450->419|450->419|450->419|451->420|451->420|452->421|452->421|453->422|454->423|455->424|456->425|457->426|457->426|458->427|462->431|462->431|462->431|462->431|462->431|462->431|463->432|463->432|463->432|467->436|467->436|467->436|467->436|467->436|467->436|470->439|470->439|470->439|470->439|470->439|470->439|470->439|470->439|470->439|471->440|472->441|473->442|474->443|475->444|485->454|485->454|486->455|489->458|490->459|496->465|496->465|496->465|522->491|522->491|522->491|544->513|544->513|544->513|545->514|545->514|545->514|545->514|545->514|545->514|545->514|545->514|545->514|545->514|545->514|545->514|545->514|546->515|546->515|546->515|546->515|546->515|546->515|547->516|547->516|547->516|547->516|547->516|547->516|548->517|548->517|548->517|548->517|548->517|548->517|550->519|551->520|585->554|585->554|589->558|589->558|591->560|605->574|605->574|606->575|607->576|607->576|608->577|608->577|608->577|608->577|611->580|612->581|633->602|635->604|639->608|639->608|639->608|656->625|659->628|660->629|660->629|660->629|660->629|660->629|661->630|661->630|661->630|661->630|661->630|661->630|662->631|662->631|662->631|662->631|662->631|662->631|663->632|663->632|663->632|663->632|663->632|663->632|664->633|664->633|664->633|664->633|664->633|664->633|665->634|665->634|665->634|665->634|665->634|665->634|666->635|666->635|666->635|666->635|666->635|666->635|667->636|667->636|667->636|667->636|667->636|667->636|668->637|668->637|668->637|668->637|668->637|668->637|669->638|669->638|669->638|669->638|669->638|669->638|670->639|670->639|670->639|670->639|670->639|670->639|671->640|671->640|671->640|671->640|671->640|671->640|672->641|672->641|672->641|672->641|672->641|672->641|673->642|673->642|673->642|673->642|673->642|673->642|674->643|674->643|675->644|675->644|675->644|675->644|675->644|675->644|676->645|676->645|676->645|677->646|677->646|677->646|677->646|677->646|677->646|677->646|677->646|678->647|680->649|680->649|680->649|680->649|680->649|680->649|680->649|680->649|681->650|681->650|681->650|681->650|681->650|681->650|681->650|681->650|682->651|682->651|682->651|682->651|682->651|682->651|682->651|682->651|683->652|684->653|689->658|689->658|689->658|691->660|691->660|691->660|696->665|696->665|697->666|699->668|699->668|701->670|701->670|701->670|702->671|709->678|709->678|711->680|711->680|711->680|712->681|713->682|713->682|714->683|716->685|716->685|717->686|719->688|719->688|719->688|719->688|720->689|721->690|721->690|722->691|722->691|723->692|723->692|725->694|727->696|727->696|729->698|730->699|730->699|730->699|731->700|731->700|731->700|732->701|732->701|733->702|740->709|740->709|741->710|742->711|742->711|743->712|743->712|745->714|745->714|746->715|749->718|749->718|750->719|751->720|751->720|752->721|752->721|752->721|755->724|755->724|755->724|756->725|756->725|756->725|756->725|756->725|756->725|756->725|756->725|756->725|756->725|756->725|756->725|756->725|756->725|756->725|756->725|756->725|756->725|756->725|757->726|758->727|760->729|760->729|761->730|763->732|763->732|764->733|765->734|765->734|766->735|766->735|766->735|768->737|768->737|769->738|772->741|772->741|773->742|774->743|774->743|775->744|775->744|775->744|781->750|781->750|782->751|784->753|784->753|785->754|786->755|786->755|787->756|788->757|788->757|789->758|789->758|790->759|790->759|790->759|791->760|791->760|791->760|791->760|791->760|791->760|791->760|792->761|792->761|792->761|792->761|792->761|792->761|793->762|794->763|794->763|795->764|795->764|796->765|796->765|799->768|799->768|801->770|801->770|802->771|802->771|802->771|803->772|803->772|803->772|803->772|804->773|804->773|804->773|804->773|805->774|805->774|805->774|805->774|808->777|808->777|809->778|816->785|816->785|817->786|817->786|817->786|818->787|818->787|818->787|819->788|819->788|819->788|819->788|820->789|820->789|821->790|821->790|821->790|821->790|822->791|822->791|822->791|823->792|824->793|824->793|825->794|825->794|825->794|825->794|826->795|827->796|827->796|828->797|828->797|829->798|829->798|830->799|830->799|831->800|831->800|833->802|833->802|833->802|834->803|836->805|836->805|837->806|838->807|838->807|838->807|838->807|838->807|839->808|844->813|844->813|845->814|845->814|845->814|845->814|846->815|849->818|849->818|850->819|855->824|855->824|856->825|856->825|856->825|857->826|857->826|857->826|858->827|863->832|863->832|864->833|866->835|866->835|867->836|867->836|868->837|870->839|870->839|870->839|870->839|871->840|872->841|872->841|873->842|878->847|878->847|879->848|879->848|880->849|880->849|881->850|882->851|882->851|884->853|885->854|885->854|886->855|891->860|891->860|892->861|892->861|892->861|893->862|893->862|893->862|894->863|897->866|897->866|898->867|898->867|899->868|899->868|899->868|899->868|899->868|899->868|899->868|900->869|900->869|900->869|901->870|901->870|901->870|902->871|905->874|905->874|906->875|906->875|908->877|908->877|908->877|908->877|909->878|909->878|909->878|910->879|911->880|911->880|912->881|912->881|915->884|915->884|915->884|916->885|917->886|917->886|918->887|919->888|919->888|920->889|920->889|920->889|920->889|921->890|929->898|929->898|930->899|933->902|933->902|933->902|933->902|934->903|934->903|934->903|934->903|934->903|935->904|936->905|936->905|937->906|938->907|938->907|939->908|939->908|939->908|939->908|940->909|944->913|944->913|945->914|945->914|948->917|948->917|948->917|949->918|956->925|956->925|957->926|958->927|958->927|959->928|961->930|961->930|962->931|962->931|964->933|964->933|964->933|965->934|967->936|967->936|967->936|967->936|968->937|970->939|970->939|972->941|972->941|972->941|972->941|972->941|972->941|972->941|972->941|973->942|980->949|980->949|981->950|982->951|982->951|983->952|985->954|985->954|986->955|986->955|988->957|988->957|988->957|989->958|991->960|991->960|991->960|991->960|992->961|994->963|994->963|995->964|995->964|998->967|1004->973|1004->973|1005->974|1005->974|1006->975|1007->976|1008->977|1008->977|1009->978|1009->978|1010->979|1010->979|1012->981|1012->981|1012->981|1013->982|1024->993|1024->993|1028->997|1028->997|1028->997|1029->998|1043->1012|1043->1012|1043->1012|1059->1028|1059->1028|1060->1029|1061->1030|1061->1030|1061->1030|1062->1031|1064->1033|1064->1033|1067->1036|1069->1038|1069->1038|1070->1039|1080->1049|1084->1053|1085->1054|1089->1058|1089->1058|1090->1059|1091->1060|1091->1060|1092->1061|1092->1061|1092->1061|1092->1061|1092->1061|1093->1062|1095->1064|1095->1064|1095->1064|1095->1064|1096->1065|1097->1066|1097->1066|1099->1068|1099->1068|1100->1069|1100->1069|1101->1070|1101->1070|1101->1070|1102->1071|1105->1074|1105->1074|1106->1075|1107->1076|1107->1076|1108->1077|1108->1077|1108->1077|1114->1083|1114->1083|1116->1085|1116->1085|1116->1085|1117->1086|1123->1092|1123->1092|1124->1093|1124->1093|1124->1093|1125->1094|1128->1097|1128->1097|1129->1098|1129->1098|1131->1100|1131->1100|1131->1100|1132->1101|1132->1101|1132->1101|1133->1102|1135->1104|1135->1104|1136->1105|1137->1106|1137->1106|1138->1107|1138->1107|1138->1107|1139->1108|1142->1111|1142->1111|1143->1112|1143->1112|1144->1113|1151->1120|1151->1120|1152->1121|1153->1122|1153->1122|1154->1123|1156->1125|1156->1125|1157->1126|1157->1126|1159->1128|1159->1128|1159->1128|1160->1129|1162->1131|1162->1131|1162->1131|1162->1131|1163->1132|1165->1134|1165->1134|1166->1135|1166->1135|1166->1135|1166->1135|1166->1135|1166->1135|1166->1135|1167->1136|1168->1137|1168->1137|1169->1138|1169->1138|1169->1138|1170->1139|1173->1142|1173->1142|1174->1143|1174->1143|1175->1144|1182->1151|1182->1151|1183->1152|1184->1153|1184->1153|1185->1154|1187->1156|1187->1156|1188->1157|1188->1157|1190->1159|1190->1159|1190->1159|1191->1160|1193->1162|1193->1162|1193->1162|1193->1162|1194->1163|1196->1165|1196->1165|1197->1166|1197->1166|1198->1167|1198->1167|1199->1168|1199->1168|1199->1168|1199->1168|1201->1170|1201->1170|1201->1170|1202->1171|1209->1178|1209->1178|1210->1179|1211->1180|1211->1180|1212->1181|1214->1183|1214->1183|1215->1184|1215->1184|1217->1186|1217->1186|1217->1186|1218->1187|1220->1189|1220->1189|1220->1189|1220->1189|1221->1190|1223->1192|1223->1192|1225->1194|1225->1194|1225->1194|1225->1194|1225->1194|1225->1194|1225->1194|1226->1195|1233->1202|1233->1202|1234->1203|1235->1204|1235->1204|1236->1205|1238->1207|1238->1207|1239->1208|1239->1208|1241->1210|1241->1210|1241->1210|1242->1211|1244->1213|1244->1213|1244->1213|1244->1213|1245->1214|1247->1216|1247->1216|1248->1217|1248->1217|1252->1221|1256->1225|1256->1225|1257->1226|1258->1227|1258->1227|1259->1228|1261->1230|1261->1230|1262->1231|1262->1231|1264->1233|1264->1233|1264->1233|1265->1234|1267->1236|1267->1236|1267->1236|1267->1236|1268->1237|1270->1239|1270->1239|1272->1241|1272->1241|1272->1241|1274->1243|1292->1261|1292->1261|1293->1262|1294->1263|1294->1263|1295->1264|1299->1268|1299->1268|1300->1269|1300->1269|1301->1270|1306->1275|1306->1275|1307->1276|1310->1279|1310->1279|1311->1280|1312->1281|1312->1281|1313->1282|1313->1282|1313->1282|1320->1289|1320->1289|1321->1290|1322->1291|1322->1291|1323->1292|1324->1293|1324->1293|1325->1294|1325->1294|1327->1296|1329->1298|1329->1298|1330->1299|1338->1307|1338->1307|1339->1308|1339->1308|1341->1310|1341->1310|1341->1310|1342->1311|1351->1320|1351->1320|1352->1321|1353->1322|1353->1322|1354->1323|1354->1323|1354->1323|1355->1324|1356->1325|1356->1325|1357->1326|1358->1327|1358->1327|1359->1328|1375->1344|1375->1344|1376->1345|1376->1345|1377->1346|1377->1346|1378->1347|1378->1347|1379->1348|1379->1348|1380->1349|1388->1357|1388->1357|1389->1358|1390->1359|1390->1359|1391->1360|1395->1364|1395->1364|1396->1365|1396->1365|1397->1366|1402->1371|1402->1371|1403->1372|1406->1375|1406->1375|1407->1376|1408->1377|1408->1377|1409->1378|1409->1378|1409->1378|1416->1385|1416->1385|1418->1387|1418->1387|1418->1387|1419->1388|1419->1388|1419->1388|1420->1389|1420->1389|1420->1389|1421->1390|1422->1391|1422->1391|1423->1392|1456->1425|1456->1425|1457->1426|1457->1426|1458->1427|1458->1427|1459->1428|1459->1428|1460->1429|1460->1429|1460->1429|1461->1430|1463->1432|1463->1432|1464->1433|1465->1434|1465->1434|1466->1435|1468->1437|1468->1437|1469->1438|1469->1438|1470->1439|1471->1440|1471->1440|1473->1442|1473->1442|1473->1442|1474->1443|1478->1447|1478->1447|1479->1448|1480->1449|1480->1449|1481->1450|1482->1451|1482->1451|1483->1452|1483->1452|1484->1453|1485->1454|1485->1454|1487->1456|1487->1456|1487->1456|1490->1459|1490->1459|1490->1459|1490->1459|1490->1459|1490->1459|1492->1461|1492->1461|1492->1461|1492->1461|1492->1461|1493->1462|1496->1465|1496->1465|1497->1466|1498->1467|1498->1467|1498->1467|1499->1468|1500->1469|1501->1470|1501->1470|1502->1471|1504->1473|1505->1474|1506->1475|1508->1477|1508->1477|1508->1477|1508->1477|1509->1478|1512->1481|1512->1481|1513->1482|1514->1483|1514->1483|1514->1483|1515->1484|1516->1485|1517->1486|1517->1486|1518->1487|1520->1489|1521->1490|1522->1491|1524->1493|1524->1493|1527->1496|1527->1496|1527->1496|1527->1496|1528->1497|1531->1500|1531->1500|1532->1501|1533->1502|1533->1502|1533->1502|1534->1503|1535->1504|1536->1505|1536->1505|1537->1506|1539->1508|1540->1509|1541->1510|1543->1512|1543->1512|1547->1516|1557->1526|1557->1526|1558->1527|1574->1543|1574->1543|1575->1544|1575->1544|1577->1546|1585->1554|1586->1555|1586->1555|1588->1557|1588->1557|1588->1557|1589->1558|1590->1559|1590->1559|1591->1560|1591->1560|1593->1562|1598->1567|1598->1567|1599->1568|1599->1568|1600->1569|1602->1571|1603->1572|1603->1572|1605->1574|1608->1577|1608->1577|1608->1577|1611->1580|1611->1580|1612->1581|1615->1584|1615->1584|1616->1585|1617->1586|1617->1586|1618->1587|1618->1587|1618->1587|1620->1589|1620->1589|1624->1593|1624->1593|1624->1593|1625->1594|1628->1597|1628->1597|1629->1598|1630->1599|1630->1599|1631->1600|1631->1600|1631->1600|1632->1601|1635->1604|1635->1604|1636->1605|1637->1606|1637->1606|1638->1607|1638->1607|1638->1607|1642->1611|1642->1611|1642->1611|1643->1612|1643->1612|1645->1614|1645->1614|1645->1614|1646->1615|1648->1617|1648->1617|1650->1619|1651->1620|1651->1620|1653->1622|1660->1629|1660->1629|1661->1630|1664->1633|1664->1633|1665->1634|1665->1634|1665->1634|1666->1635|1667->1636|1667->1636|1668->1637|1668->1637|1669->1638|1669->1638|1669->1638|1670->1639|1671->1640|1671->1640|1672->1641|1672->1641|1674->1643|1674->1643|1674->1643|1675->1644|1675->1644|1675->1644|1676->1645|1679->1648|1679->1648|1679->1648|1679->1648|1680->1649|1682->1651|1682->1651|1683->1652|1683->1652|1683->1652|1683->1652|1684->1653|1688->1657|1688->1657|1689->1658|1689->1658|1694->1663|1703->1672|1703->1672|1704->1673|1705->1674|1705->1674|1707->1676|1707->1676|1707->1676|1708->1677|1708->1677|1708->1677|1709->1678|1710->1679|1710->1679|1711->1680|1713->1682|1713->1682|1714->1683|1715->1684|1715->1684|1716->1685|1717->1686|1717->1686|1729->1698|1729->1698|1730->1699|1734->1703|1734->1703|1735->1704|1736->1705|1736->1705|1739->1708|1739->1708|1740->1709|1748->1717|1748->1717|1749->1718|1752->1721|1752->1721|1753->1722|1754->1723|1754->1723|1762->1731|1762->1731|1762->1731|1764->1733|1764->1733|1765->1734|1773->1742|1773->1742|1774->1743|1774->1743|1774->1743|1775->1744|1775->1744|1775->1744|1776->1745|1777->1746|1777->1746|1778->1747|1778->1747|1778->1747|1778->1747|1779->1748|1780->1749|1780->1749|1781->1750|1782->1751|1782->1751|1782->1751|1782->1751|1783->1752|1783->1752|1783->1752|1784->1753|1791->1760|1791->1760|1793->1762|1793->1762|1794->1763|1794->1763|1795->1764|1795->1764|1796->1765|1796->1765|1797->1766|1797->1766|1801->1770|1801->1770|1802->1771|1802->1771|1804->1773|1804->1773|1804->1773|1805->1774|1808->1777|1808->1777|1809->1778|1809->1778|1809->1778|1810->1779|1812->1781|1812->1781|1813->1782|1813->1782|1813->1782|1813->1782|1813->1782|1814->1783|1814->1783|1814->1783|1815->1784|1817->1786|1817->1786|1818->1787|1818->1787|1819->1788|1820->1789|1820->1789|1822->1791|1822->1791|1822->1791|1823->1792|1824->1793|1824->1793|1824->1793|1824->1793|1824->1793|1824->1793|1824->1793|1824->1793|1824->1793|1825->1794|1825->1794|1827->1796|1827->1796|1827->1796|1828->1797|1832->1801|1832->1801|1835->1804|1835->1804|1835->1804|1836->1805|1838->1807|1838->1807|1839->1808|1846->1815|1846->1815|1847->1816|1849->1818|1849->1818|1850->1819|1850->1819|1851->1820|1851->1820|1852->1821
                  -- GENERATED --
              */
          