
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

object movimientoOrigenDestinoMultiple1 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template15[Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,List[List[String]],tables.BodegaEmpresa,Long,String,List[List[String]],List[tables.TipoEstado],List[tables.TipoReparacion],List[tables.Transportista],tables.Sucursal,tables.Comercial,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
bodegaOrigen: tables.BodegaEmpresa, listEquipBodOrigen: List[List[String]], bodegaDestino: tables.BodegaEmpresa,
nuevoNumeroGuia: Long, hoy: String, listEquipNoEnBodOrigen: List[List[String]], listTipoEstado: List[tables.TipoEstado], listTipoReparacion: List[tables.TipoReparacion],
listaTransporte: List[tables.Transportista], 
sucursal: tables.Sucursal, comercial: tables.Comercial,
sinVenta: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*7.1*/("""

"""),_display_(/*9.2*/main("")/*9.10*/ {_display_(Seq[Any](format.raw/*9.12*/("""

	"""),_display_(/*11.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*11.51*/("""

	"""),format.raw/*13.2*/("""<div id="enCarga" class="blocker" style="display: none;"><br><br><br><br><br><br><h1>Ahora se esta cargando.....</h1></div>

	"""),_display_(/*15.3*/modalEquipoDescripcion()),format.raw/*15.27*/("""
	"""),_display_(/*16.3*/modalVerCotizacion()),format.raw/*16.23*/("""

	"""),format.raw/*18.2*/("""<script>
		let auxBueno = -1;
		let auxIndex = -1;
	</script>

	<form action="/movimientoAplicar/" enctype="multipart/form-data" method="POST" onsubmit="return validarForm();">
		<div id="mostrarmostrar" style="display:none;">
			"""),_display_(/*25.5*/barraTitulo(mapDiccionario, "MOVIMIENTOS ENTRE "+mapDiccionario.get("BODEGA")+" Y/O PROYECTOS", "")),format.raw/*25.104*/("""
			"""),format.raw/*26.4*/("""<div class="row  justify-content-md-center">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<table class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<tr>
								<td width="25%">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Origen</span>
								  		</div>
								  		<input type="hidden" name="id_bodegaOrigen" value=""""),_display_(/*36.65*/bodegaOrigen/*36.77*/.getId()),format.raw/*36.85*/("""">
	  									<input type="text" class="form-control"
	  										value=""""),_display_(/*38.22*/bodegaOrigen/*38.34*/.getNombre().toUpperCase),format.raw/*38.58*/(""""
	  										readonly>
									</div>
								</td>
								<td width="25%">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Destino</span>
								  		</div>
	  										<input type="hidden" name="id_bodegaDestino" id="id_bodegaDestino" value=""""),_display_(/*47.89*/bodegaDestino/*47.102*/.getId()),format.raw/*47.110*/("""">
											<input type="text" class="form-control left"
												id="nombreBodegaDestino"
												style="background:white"
											    value=""""),_display_(/*51.24*/bodegaDestino/*51.37*/.getNombre().toUpperCase),format.raw/*51.61*/(""""
												required
												readonly>
									</div>
								</td>
								<td>
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Nro.Mov</span>
								  		</div>
											"""),_display_(if(mapPermiso.get("parametro.bloqueoNrosMovimientos")!=null && mapPermiso.get("parametro.bloqueoNrosMovimientos").equals("1"))/*61.139*/{_display_(Seq[Any](format.raw/*61.140*/("""
												"""),format.raw/*62.13*/("""<input type="text" class="form-control left"
									  				name="numeroGuia"
													id="numeroGuia"
													value = """"),_display_(/*65.24*/nuevoNumeroGuia),format.raw/*65.39*/(""""
													readonly
													required>
											""")))}else/*68.17*/{_display_(Seq[Any](format.raw/*68.18*/("""
												"""),format.raw/*69.13*/("""<input type="text" class="form-control left"
									  				name="numeroGuia"
													id="numeroGuia"
													value = """"),_display_(/*72.24*/nuevoNumeroGuia),format.raw/*72.39*/(""""
													onkeydown="return ingresoInt(window.event)"
													onchange="verificarNumeroGuia(value)"
													autocomplete="off"
													required>
											""")))}),format.raw/*77.13*/("""
									"""),format.raw/*78.10*/("""</div>
								</td>
								<td>
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Nro.Ref</span>
								  		</div>
								  		<input type="text" class="form-control left"
								  				name="numGuiaCliente"
								  				autocomplete="off"
								  				maxlength="50">
									</div>
								</td>
								<td rowspan="2" style="width: 50px; min-width: 50px; max-width: 50px; text-align: center; vertical-align: middle">
									<span class="btn btn-info btn-sm btn-file" style="font-size: 10px; display: inline-block">
										<input type="hidden" name="docAnexo" value="0">
										<div id="txtBtn">Subir Documento</div>
										<input type="file" multiple id="docAdjunto" name="docAdjunto[]" title="Adjuntar documentos"
											onchange="LimitAttach(this.form, this.form.docAdjunto.value);">
									</span>
								</td>
							</tr>


							<tr>
								<td align="center">
									<input type="hidden" id="id_sucursalOrigen" name="id_sucursalOrigen" value=""""),_display_(/*104.87*/sucursal/*104.95*/.getId()),format.raw/*104.103*/("""">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Sucursal</span>
								  		</div>
											<input type="text" class="form-control left"
												value = """"),_display_(/*110.23*/sucursal/*110.31*/.getNombre()),format.raw/*110.43*/(""""
												readonly>
									</div>
								</td>
								<td align="center">
									<input type="hidden" id="id_sucursalDestino" name="id_sucursalDestino">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Sucursal</span>
								  		</div>
											<input type="text" id="nameSucursalDestino" class="form-control left"
											    value=""""),_display_(/*121.24*/bodegaDestino/*121.37*/.getNameSucursal()),format.raw/*121.55*/(""""
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
										onblur="if(!limitaFecha(value,"""),_display_(/*134.42*/mapPermiso/*134.52*/.get("parametro.diasMenosGuia")),format.raw/*134.83*/(""","""),_display_(/*134.85*/mapPermiso/*134.95*/.get("parametro.diasMasGuia")),format.raw/*134.124*/(""")) """),format.raw/*134.127*/("""{"""),format.raw/*134.128*/("""value='"""),_display_(/*134.136*/hoy),format.raw/*134.139*/("""';$('#fechaIniTerGuia').val(value);"""),format.raw/*134.174*/("""}"""),format.raw/*134.175*/(""" """),format.raw/*134.176*/("""else """),format.raw/*134.181*/("""{"""),format.raw/*134.182*/("""$('#fechaIniTerGuia').val(value);"""),format.raw/*134.215*/("""}"""),format.raw/*134.216*/(""""
										value=""""),_display_(/*135.19*/hoy),format.raw/*135.22*/(""""
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
										onblur="if(!limitaFecha(value,"""),_display_(/*148.42*/mapPermiso/*148.52*/.get("parametro.diasMenosIniTer")),format.raw/*148.85*/(""","""),_display_(/*148.87*/mapPermiso/*148.97*/.get("parametro.diasMasIniTer")),format.raw/*148.128*/(""")) value='"""),_display_(/*148.139*/hoy),format.raw/*148.142*/("""';"
										value=""""),_display_(/*149.19*/hoy),format.raw/*149.22*/(""""
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
												id = "nameComercial"
												value = """"),_display_(/*163.23*/comercial/*163.32*/.getNameUsuario()),format.raw/*163.49*/(""""
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
								<td colspan="20" rowspan="3">
									<div class="input-group">
										<div class="input-group-prepend">
											<span class="input-group-text" id="basic-addon1">Observaciones</span>
										</div>
										<textarea class="form-control" rows="2"
										name="observaciones"
										autocomplete="off"></textarea>
									</div>
								</td>
							</tr>



						</thead>
					</table>


					"""),_display_(if(bodegaOrigen.esInterna == 1)/*196.38*/{_display_(Seq[Any](format.raw/*196.39*/("""
						"""),format.raw/*197.7*/("""<div class="noprint" align="center">
							<div class="row justify-content-md-center" >
								<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
									<input id="btnCargaExcel" type="button"  value="Copiar desde un Excel" class="btn btn-info btn-sm rounded-pill btn-block"
										onclick="$('#modalCargaMasiva').modal('show');">&nbsp;&nbsp;&nbsp;
								</div>
							</div>
						</div>
					""")))} else {null} ),format.raw/*205.7*/("""




					"""),format.raw/*210.6*/("""<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<TR>
								<TH>GRUPO</TH>
								<TH>Nro."""),_display_(/*214.18*/mapDiccionario/*214.32*/.get("OT")),format.raw/*214.42*/("""</TH>
								<TH with="80px">Fecha<br>"""),_display_(/*215.35*/mapDiccionario/*215.49*/.get("OT")),format.raw/*215.59*/("""</TH>
								<TH>Nro.COTI</TH>
								<TH>CODIGO</TH>
								<TH>EQUIPO</TH>
								<TH>KG</TH>
						        <TH>M2</TH>
						        <TH>UN</TH>
								<TH>STOCK</TH>
								<TH>SALIDA</TH>
								"""),_display_(if(sinVenta.equals("1"))/*224.34*/{_display_(Seq[Any](format.raw/*224.35*/("""
									"""),format.raw/*225.10*/("""<TH style="display:none">VENTA</TH>
								""")))}else/*226.14*/{_display_(Seq[Any](format.raw/*226.15*/("""
									"""),format.raw/*227.10*/("""<TH>VENTA</TH>
								""")))}),format.raw/*228.10*/("""
								"""),format.raw/*229.9*/("""<TH style="display:none"></TH>
								<TH>EXCESO</TH>
								<TH>TOT.KG</TH>
								<TH>TOT.M2</TH>
								"""),_display_(if(bodegaOrigen.esInterna!=1)/*233.39*/{_display_(Seq[Any](format.raw/*233.40*/("""
									"""),format.raw/*234.10*/("""<th style="display:none"></th>
									"""),_display_(/*235.11*/for(titEstado <- listTipoEstado) yield /*235.43*/{_display_(Seq[Any](format.raw/*235.44*/("""
										"""),format.raw/*236.11*/("""<TH style="font-size:10px; vertical-align:middle">
											"""),_display_(/*237.13*/titEstado/*237.22*/.getSigla()),format.raw/*237.33*/("""
										"""),format.raw/*238.11*/("""</TH>
									""")))}),format.raw/*239.11*/("""
									"""),_display_(if(mapPermiso.get("parametro.movimiento-devolucion-cantCliente")!=null && mapPermiso.get("parametro.movimiento-devolucion-cantCliente").equals("1"))/*240.159*/{_display_(Seq[Any](format.raw/*240.160*/("""
										"""),format.raw/*241.11*/("""<TH style="text-align:center;">cliente</TH>
										<TH style="text-align:center;">diferen</TH>
									""")))} else {null} ),format.raw/*243.11*/("""
								""")))} else {null} ),format.raw/*244.10*/("""
							"""),format.raw/*245.8*/("""</TR>
						</thead>
						<tbody>
							"""),_display_(/*248.9*/for(lista1 <- listEquipBodOrigen) yield /*248.42*/{_display_(Seq[Any](format.raw/*248.43*/("""
								"""),format.raw/*249.9*/("""<TR>
									<td  style="text-align:left;">
										<input type="hidden" name="id_equipo[]" value=""""),_display_(/*251.59*/lista1/*251.65*/.get(0)),format.raw/*251.72*/("""">
										<input type="hidden" name="id_cotizacion[]" value=""""),_display_(/*252.63*/lista1/*252.69*/.get(1)),format.raw/*252.76*/("""">
										"""),_display_(/*253.12*/lista1/*253.18*/.get(2)),format.raw/*253.25*/("""
									"""),format.raw/*254.10*/("""</td>

									"""),_display_(if(lista1.get(10).equals("0") || lista1.get(10).equals(""))/*256.70*/{_display_(Seq[Any](format.raw/*256.71*/("""
										"""),format.raw/*257.11*/("""<td style="text-align: center;">--</td>
									""")))}else/*258.15*/{_display_(Seq[Any](format.raw/*258.16*/("""
										"""),format.raw/*259.11*/("""<td style="text-align:center;" class="sorting_2">
											<a href="#" onclick="verOt('"""),_display_(/*260.41*/lista1/*260.47*/.get(10)),format.raw/*260.55*/("""')">
												<kbd style="background-color: rgb(90, 200, 245)">
													<font color="green">"""),_display_(/*262.35*/lista1/*262.41*/.get(11)),format.raw/*262.49*/("""</font>
												</kbd>
											</a>
										</td>
									""")))}),format.raw/*266.11*/("""

									"""),format.raw/*268.10*/("""<td style="text-align: center;">
										<div style="display: none;"> """),_display_(/*269.41*/utilities/*269.50*/.Fechas.AAMMDD(lista1.get(12))),format.raw/*269.80*/("""</div>
										"""),_display_(/*270.12*/lista1/*270.18*/.get(12)),format.raw/*270.26*/("""
									"""),format.raw/*271.10*/("""</td>

									"""),_display_(if(lista1.get(3).equals("0") || lista1.get(3).equals(""))/*273.68*/{_display_(Seq[Any](format.raw/*273.69*/("""
										"""),format.raw/*274.11*/("""<td style="text-align: center;">--</td>
									""")))}else/*275.15*/{_display_(Seq[Any](format.raw/*275.16*/("""
										"""),format.raw/*276.11*/("""<td style="text-align: center;">"""),_display_(/*276.44*/lista1/*276.50*/.get(3)),format.raw/*276.57*/("""</td>
									""")))}),format.raw/*277.11*/("""

									"""),format.raw/*279.10*/("""<td  style="text-align:left;"><a href="#" onclick="equipoDescripcion('"""),_display_(/*279.81*/lista1/*279.87*/.get(0)),format.raw/*279.94*/("""');"><div class="codigo">"""),_display_(/*279.120*/lista1/*279.126*/.get(4)),format.raw/*279.133*/("""</div></a></td>
									<td  style="text-align:left;"><a href="#" onclick="equipoDescripcion('"""),_display_(/*280.81*/lista1/*280.87*/.get(0)),format.raw/*280.94*/("""');">"""),_display_(/*280.100*/lista1/*280.106*/.get(5)),format.raw/*280.113*/("""</a></td>
									<td  class="kgUnit" style="text-align:right;">"""),_display_(/*281.57*/lista1/*281.63*/.get(6)),format.raw/*281.70*/("""</td>
									<td  class="m2Unit" style="text-align:right;">"""),_display_(/*282.57*/lista1/*282.63*/.get(7)),format.raw/*282.70*/("""</td>
									<td  style="text-align:center;">"""),_display_(/*283.43*/lista1/*283.49*/.get(8)),format.raw/*283.56*/("""</td>
									<td  style="text-align:right;">
										<div class="verificaStock" id="stock_"""),_display_(/*285.49*/lista1/*285.55*/.get(0)),format.raw/*285.62*/("""_"""),_display_(/*285.64*/lista1/*285.70*/.get(1)),format.raw/*285.77*/("""">"""),_display_(/*285.80*/lista1/*285.86*/.get(9)),format.raw/*285.93*/("""</div>
									</td>
									<td  style="text-align:center;">
										<div align="center">
											<input type="text" class="cantidad form-control height23px right width80px"
												name="cantidad[]"
												id="cantidad_"""),_display_(/*291.27*/lista1/*291.33*/.get(0)),format.raw/*291.40*/("""_"""),_display_(/*291.42*/lista1/*291.48*/.get(1)),format.raw/*291.55*/(""""
												value="0.00"
												onfocus="value=value.replace(/,/g,''); cantAux = value;"
												onkeydown="return ingresoDouble(window.event)"
												autocomplete="off"
												onchange="if(value=='') value=0; verificaCantidad('"""),_display_(/*296.65*/lista1/*296.71*/.get(0)),format.raw/*296.78*/("""','"""),_display_(/*296.82*/lista1/*296.88*/.get(1)),format.raw/*296.95*/("""','"""),_display_(/*296.99*/lista1/*296.105*/.get(9)),format.raw/*296.112*/("""',value,'"""),_display_(/*296.122*/lista1/*296.128*/.get(6)),format.raw/*296.135*/("""','"""),_display_(/*296.139*/lista1/*296.145*/.get(7)),format.raw/*296.152*/("""','"""),_display_(/*296.156*/listTipoEstado/*296.170*/.size()),format.raw/*296.177*/("""');">
										</div>
									</td>
									"""),_display_(if(sinVenta.equals("1"))/*299.35*/{_display_(Seq[Any](format.raw/*299.36*/("""
										"""),format.raw/*300.11*/("""<td  style="text-align:center; display:none;">
											<input type="hidden" class="esVenta0" name="esVenta[]" id="esVenta_"""),_display_(/*301.80*/lista1/*301.86*/.get(0)),format.raw/*301.93*/("""_"""),_display_(/*301.95*/lista1/*301.101*/.get(1)),format.raw/*301.108*/("""" value="0">
											<input type="checkbox" class="esVenta1" onchange='if($("#esVenta_"""),_display_(/*302.78*/lista1/*302.84*/.get(0)),format.raw/*302.91*/("""_"""),_display_(/*302.93*/lista1/*302.99*/.get(1)),format.raw/*302.106*/("""").val()==1)
											$("#esVenta_"""),_display_(/*303.25*/lista1/*303.31*/.get(0)),format.raw/*303.38*/("""_"""),_display_(/*303.40*/lista1/*303.46*/.get(1)),format.raw/*303.53*/("""").val(0); else $("#esVenta_"""),_display_(/*303.82*/lista1/*303.88*/.get(0)),format.raw/*303.95*/("""_"""),_display_(/*303.97*/lista1/*303.103*/.get(1)),format.raw/*303.110*/("""").val(1);'>
										</td>
									""")))}else/*305.15*/{_display_(Seq[Any](format.raw/*305.16*/("""
										"""),format.raw/*306.11*/("""<td  style="text-align:center;">
											<input type="hidden" class="esVenta0" name="esVenta[]" id="esVenta_"""),_display_(/*307.80*/lista1/*307.86*/.get(0)),format.raw/*307.93*/("""_"""),_display_(/*307.95*/lista1/*307.101*/.get(1)),format.raw/*307.108*/("""" value="0">
											<input type="checkbox" class="esVenta1" onchange='if($("#esVenta_"""),_display_(/*308.78*/lista1/*308.84*/.get(0)),format.raw/*308.91*/("""_"""),_display_(/*308.93*/lista1/*308.99*/.get(1)),format.raw/*308.106*/("""").val()==1)
											$("#esVenta_"""),_display_(/*309.25*/lista1/*309.31*/.get(0)),format.raw/*309.38*/("""_"""),_display_(/*309.40*/lista1/*309.46*/.get(1)),format.raw/*309.53*/("""").val(0); else $("#esVenta_"""),_display_(/*309.82*/lista1/*309.88*/.get(0)),format.raw/*309.95*/("""_"""),_display_(/*309.97*/lista1/*309.103*/.get(1)),format.raw/*309.110*/("""").val(1);'>
										</td>
									""")))}),format.raw/*311.11*/("""
									"""),format.raw/*312.10*/("""<td width="1px" style="text-align:center; display:none"">
										<input type="hidden" name="esNuevo[]" id="esNuevo_"""),_display_(/*313.62*/lista1/*313.68*/.get(0)),format.raw/*313.75*/("""_"""),_display_(/*313.77*/lista1/*313.83*/.get(1)),format.raw/*313.90*/("""" value="0">
									</td>
									<td style="text-align:center;">
										<div align="center">
											<input type="text" class="exceso form-control height23px right width80px"
												name="exceso[]"
												id="exceso_"""),_display_(/*319.25*/lista1/*319.31*/.get(0)),format.raw/*319.38*/("""_"""),_display_(/*319.40*/lista1/*319.46*/.get(1)),format.raw/*319.53*/(""""
												value="0.00"
												readonly>
										</div>
									</td>
									<td style="text-align:right;"><div class="KG" id="KG_"""),_display_(/*324.64*/lista1/*324.70*/.get(0)),format.raw/*324.77*/("""_"""),_display_(/*324.79*/lista1/*324.85*/.get(1)),format.raw/*324.92*/("""">0.00</div></td>
									<td style="text-align:right;"><div class="M2" id="M2_"""),_display_(/*325.64*/lista1/*325.70*/.get(0)),format.raw/*325.77*/("""_"""),_display_(/*325.79*/lista1/*325.85*/.get(1)),format.raw/*325.92*/("""">0.00</div></td>
									"""),_display_(if(bodegaOrigen.esInterna!=1)/*326.40*/{_display_(Seq[Any](format.raw/*326.41*/("""
										"""),format.raw/*327.11*/("""<td style="display:none">
											<input type="hidden" name="estados[]"   id="estados_"""),_display_(/*328.65*/lista1/*328.71*/.get(0)),format.raw/*328.78*/("""_"""),_display_(/*328.80*/lista1/*328.86*/.get(1)),format.raw/*328.93*/("""">
											<input type="hidden" name="reparaciones[]"   id="reparaciones_"""),_display_(/*329.75*/lista1/*329.81*/.get(0)),format.raw/*329.88*/("""_"""),_display_(/*329.90*/lista1/*329.96*/.get(1)),format.raw/*329.103*/("""">
										</td>
										"""),_display_(/*331.12*/for((est,index) <- listTipoEstado.zipWithIndex) yield /*331.59*/{_display_(Seq[Any](format.raw/*331.60*/("""
											"""),format.raw/*332.12*/("""<td style="font-size:10px; vertical-align:middle">
												"""),_display_(if(est.getId() == 999)/*333.36*/{_display_(Seq[Any](format.raw/*333.37*/("""
													"""),format.raw/*334.14*/("""<input type="text" class="form-control height23px right width60px"
														style="font-size:10px; vertical-align:middle"
														id="estado_"""),_display_(/*336.27*/lista1/*336.33*/.get(0)),format.raw/*336.40*/("""_"""),_display_(/*336.42*/lista1/*336.48*/.get(1)),format.raw/*336.55*/("""_"""),_display_(/*336.57*/index),format.raw/*336.62*/(""""
														value="0.00"
														readonly>
													<script>
														auxBueno = 999;
														auxIndex = """"),_display_(/*341.28*/index),format.raw/*341.33*/("""";
													</script>
												""")))}else if( mapDiccionario.get("nEmpresa").equals("HOHE") && ( est.getId() == 2 || est.getId() == 3 ) )/*343.114*/{_display_(Seq[Any](format.raw/*343.115*/("""
													"""),format.raw/*344.14*/("""<input type="text" class="form-control height23px right width60px"
														style="font-size:10px; vertical-align:middle"
														id="estado_"""),_display_(/*346.27*/lista1/*346.33*/.get(0)),format.raw/*346.40*/("""_"""),_display_(/*346.42*/lista1/*346.48*/.get(1)),format.raw/*346.55*/("""_"""),_display_(/*346.57*/index),format.raw/*346.62*/(""""
														value="0.00"
														readonly>
													<script>
														auxBueno = """"),_display_(/*350.28*/est/*350.31*/.getId()),format.raw/*350.39*/("""";
														auxIndex = """"),_display_(/*351.28*/index),format.raw/*351.33*/("""";
													</script>
												""")))}else/*353.18*/{_display_(Seq[Any](format.raw/*353.19*/("""
													"""),format.raw/*354.14*/("""<input type="text" class="form-control height23px right width60px"
														style="font-size:10px; vertical-align:middle"
														id="estado_"""),_display_(/*356.27*/lista1/*356.33*/.get(0)),format.raw/*356.40*/("""_"""),_display_(/*356.42*/lista1/*356.48*/.get(1)),format.raw/*356.55*/("""_"""),_display_(/*356.57*/index),format.raw/*356.62*/(""""
														value="0.00"
														onfocus="value=value.replace(/,/g,'')"
														onkeydown="return ingresoDouble(window.event)"
														autocomplete="off"
														ondblclick = "if(value>0 && '"""),_display_(/*361.45*/est/*361.48*/.getReparable()),format.raw/*361.63*/("""'=='1') editaReparaciones('"""),_display_(/*361.91*/index),format.raw/*361.96*/("""','"""),_display_(/*361.100*/listTipoEstado/*361.114*/.size()),format.raw/*361.121*/("""','"""),_display_(/*361.125*/lista1/*361.131*/.get(0)),format.raw/*361.138*/("""','"""),_display_(/*361.142*/lista1/*361.148*/.get(1)),format.raw/*361.155*/("""',value,'"""),_display_(/*361.165*/est/*361.168*/.getReparable()),format.raw/*361.183*/("""','"""),_display_(/*361.187*/est/*361.190*/.getId()),format.raw/*361.198*/("""')"
														onchange="if(value=='') """),format.raw/*362.39*/("""{"""),format.raw/*362.40*/("""
																		"""),format.raw/*363.19*/("""value='0.00';
																		verificaCantEstado('"""),_display_(/*364.40*/index),format.raw/*364.45*/("""','"""),_display_(/*364.49*/listTipoEstado/*364.63*/.size()),format.raw/*364.70*/("""','"""),_display_(/*364.74*/lista1/*364.80*/.get(0)),format.raw/*364.87*/("""','"""),_display_(/*364.91*/lista1/*364.97*/.get(1)),format.raw/*364.104*/("""',value,'"""),_display_(/*364.114*/est/*364.117*/.getReparable()),format.raw/*364.132*/("""','"""),_display_(/*364.136*/est/*364.139*/.getId()),format.raw/*364.147*/("""');
																  """),format.raw/*365.19*/("""}"""),format.raw/*365.20*/("""else"""),format.raw/*365.24*/("""{"""),format.raw/*365.25*/("""
																		"""),format.raw/*366.19*/("""verificaCantEstado('"""),_display_(/*366.40*/index),format.raw/*366.45*/("""','"""),_display_(/*366.49*/listTipoEstado/*366.63*/.size()),format.raw/*366.70*/("""','"""),_display_(/*366.74*/lista1/*366.80*/.get(0)),format.raw/*366.87*/("""','"""),_display_(/*366.91*/lista1/*366.97*/.get(1)),format.raw/*366.104*/("""',value,'"""),_display_(/*366.114*/est/*366.117*/.getReparable()),format.raw/*366.132*/("""','"""),_display_(/*366.136*/est/*366.139*/.getId()),format.raw/*366.147*/("""');
																  """),format.raw/*367.19*/("""}"""),format.raw/*367.20*/("""">
												""")))}),format.raw/*368.14*/("""
											"""),format.raw/*369.12*/("""</td>
										""")))}),format.raw/*370.12*/("""
										"""),_display_(if(mapPermiso.get("parametro.movimiento-devolucion-cantCliente")!=null && mapPermiso.get("parametro.movimiento-devolucion-cantCliente").equals("1"))/*371.160*/{_display_(Seq[Any](format.raw/*371.161*/("""
											"""),format.raw/*372.12*/("""<td  style="text-align:center;">
												<div align="center">
													<input type="text" class="cantCliente form-control height23px right width80px"
														name="cantCliente[]"
														id="cantCliente_"""),_display_(/*376.32*/lista1/*376.38*/.get(0)),format.raw/*376.45*/("""_"""),_display_(/*376.47*/lista1/*376.53*/.get(1)),format.raw/*376.60*/(""""
														value="0.00"
														onfocus="value=value.replace(/,/g,''); cantAux = value;"
														onkeydown="return ingresoDouble(window.event)"
														autocomplete="off"
														onchange="if(value=='') value=0; actualizaCantCliente('"""),_display_(/*381.71*/lista1/*381.77*/.get(0)),format.raw/*381.84*/("""', '"""),_display_(/*381.89*/lista1/*381.95*/.get(1)),format.raw/*381.102*/("""')">
												</div>
											</td>
											<td style="text-align:right;"><div class="difCliente" id="difCliente_"""),_display_(/*384.82*/lista1/*384.88*/.get(0)),format.raw/*384.95*/("""_"""),_display_(/*384.97*/lista1/*384.103*/.get(1)),format.raw/*384.110*/("""">0.00</div></td>
										""")))} else {null} ),format.raw/*385.12*/("""
									""")))} else {null} ),format.raw/*386.11*/("""
								"""),format.raw/*387.9*/("""</TR>
				 			""")))}),format.raw/*388.10*/("""
						"""),format.raw/*389.7*/("""</tbody>
					</table>
				</div>
			</div>



			<div class="noprint" align="left">
				<div class="row justify-content-md-left" >
					<div class="col-xs-5 col-sm-3 col-md-3 col-lg-3">
						"""),_display_(if(bodegaOrigen.esInterna != 1)/*399.39*/{_display_(Seq[Any](format.raw/*399.40*/("""
							"""),format.raw/*400.8*/("""<input type="button"  id="agregarEquiposNoEnviados" value="Agregar equipos que no fueron enviados"
								class="btn btn-warning btn-sm rounded-pill btn-block"
								onclick='$("#equipNoEnBodOrigen").modal("show")'>
						""")))} else {null} ),format.raw/*403.8*/("""
					"""),format.raw/*404.6*/("""</div>
				</div>


				<div class="row justify-content-md-center" >
					<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
						<input type="button"  id="transporte" value='Agregar Transportista' class="btn btn-info btn-sm rounded-pill btn-block" onclick="selectTransporte(0);" >
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
						<input type="button"  id="modifica" value='Modificar' class="btn btn-warning btn-sm rounded-pill btn-block"
							onclick="modificaMovimientos();" style="visibility:hidden">
					</div>
				</div>
			</div>
		</div>
		<input type="hidden" id="id_transportistaXLSX" name="id_transportista" value="0">
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
							"""),_display_(/*459.9*/for(lista1 <- listEquipNoEnBodOrigen) yield /*459.46*/{_display_(Seq[Any](format.raw/*459.47*/("""
								"""),format.raw/*460.9*/("""<TR onClick="seleccionaEquipNoEnBodOrigen(this, """),_display_(/*460.58*/lista1/*460.64*/.get(0)),format.raw/*460.71*/(""",'"""),_display_(/*460.74*/lista1/*460.80*/.get(6)),format.raw/*460.87*/("""','"""),_display_(/*460.91*/lista1/*460.97*/.get(7)),format.raw/*460.104*/("""','"""),_display_(/*460.108*/lista1/*460.114*/.get(8)),format.raw/*460.121*/("""')" data-dismiss="modal">
									<td  style="text-align:left;"><div id="grupoNo_"""),_display_(/*461.58*/lista1/*461.64*/.get(0)),format.raw/*461.71*/("""">"""),_display_(/*461.74*/lista1/*461.80*/.get(2)),format.raw/*461.87*/("""</div></td>
									<td  style="text-align:left;"><div id="codigoNo_"""),_display_(/*462.59*/lista1/*462.65*/.get(0)),format.raw/*462.72*/("""">"""),_display_(/*462.75*/lista1/*462.81*/.get(4)),format.raw/*462.88*/("""</div></td>
									<td  style="text-align:left;"><div id="equipoNo_"""),_display_(/*463.59*/lista1/*463.65*/.get(0)),format.raw/*463.72*/("""">"""),_display_(/*463.75*/lista1/*463.81*/.get(5)),format.raw/*463.88*/("""</div></td>
								</TR>
				 			""")))}),format.raw/*465.10*/("""
						"""),format.raw/*466.7*/("""</tbody>
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

	<div id="modalCargaMasiva" class="modal" role="dialog" data-backdrop="static" data-keyboard="false">
	  <div class="modal-dialog modal-sm modal-dialog-scrollable" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title">Carga masiva de equipos a mover</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">

			<div class="row">
				<div class="col-sm-12" style="text-align:center">
					<form action="/routes2/movPlantilla/" method="POST"  onsubmit="return validarForm2();">
					<input type="hidden" name="id_bodegaOrigen" value=""""),_display_(/*509.58*/bodegaOrigen/*509.70*/.getId()),format.raw/*509.78*/("""">
						<button type="submit" id="btnSubmit2" class="btn btn-sm  btn-success" style="font-size: 10px;">DESCARGAR PLANTILLA</button>
					</form>
					<br>
					<span class="btn btn-info btn-sm btn-file" style="font-size: 8px;">
						<div>SUBIR ARCHIVO</div>
						<input type="file" id="archivoXLSX"  value="" onchange="subirArchivo(this.form, this); $('#modalCargaMasiva').modal('hide');">
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

	<div id='modalVerOt' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable' style="max-width: 80% !important;" role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>"""),_display_(/*531.31*/mapDiccionario/*531.45*/.get("ORDEN_DE_TRABAJO")),format.raw/*531.69*/("""</h5>
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

	"""),_display_(/*548.3*/modalListaTransportistas(mapDiccionario, mapPermiso, listaTransporte)),format.raw/*548.72*/(""";

""")))}),format.raw/*550.2*/("""


"""),format.raw/*553.1*/("""<style>
	#tablaPrincipal td:nth-child(2) """),format.raw/*554.34*/("""{"""),format.raw/*554.35*/(""" """),format.raw/*554.36*/("""text-align: center; """),format.raw/*554.56*/("""}"""),format.raw/*554.57*/("""
	"""),format.raw/*555.2*/("""#tablaPrincipal td:nth-child(3) """),format.raw/*555.34*/("""{"""),format.raw/*555.35*/(""" """),format.raw/*555.36*/("""text-align: center; """),format.raw/*555.56*/("""}"""),format.raw/*555.57*/("""
	"""),format.raw/*556.2*/("""#tablaPrincipal td:nth-child(4) """),format.raw/*556.34*/("""{"""),format.raw/*556.35*/(""" """),format.raw/*556.36*/("""text-align: center; """),format.raw/*556.56*/("""}"""),format.raw/*556.57*/("""
	"""),format.raw/*557.2*/("""#tablaPrincipal td:nth-child(5) """),format.raw/*557.34*/("""{"""),format.raw/*557.35*/(""" """),format.raw/*557.36*/("""text-align: left; """),format.raw/*557.54*/("""}"""),format.raw/*557.55*/("""
	"""),format.raw/*558.2*/("""#tablaPrincipal td:nth-child(7) """),format.raw/*558.34*/("""{"""),format.raw/*558.35*/(""" """),format.raw/*558.36*/("""text-align: right; """),format.raw/*558.55*/("""}"""),format.raw/*558.56*/("""
	"""),format.raw/*559.2*/("""#tablaPrincipal td:nth-child(8) """),format.raw/*559.34*/("""{"""),format.raw/*559.35*/(""" """),format.raw/*559.36*/("""text-align: right; """),format.raw/*559.55*/("""}"""),format.raw/*559.56*/("""
	"""),format.raw/*560.2*/("""#tablaPrincipal td:nth-child(9) """),format.raw/*560.34*/("""{"""),format.raw/*560.35*/(""" """),format.raw/*560.36*/("""text-align: center; """),format.raw/*560.56*/("""}"""),format.raw/*560.57*/("""
	"""),format.raw/*561.2*/("""#tablaPrincipal td:nth-child(10) """),format.raw/*561.35*/("""{"""),format.raw/*561.36*/(""" """),format.raw/*561.37*/("""text-align: right; """),format.raw/*561.56*/("""}"""),format.raw/*561.57*/("""
	"""),format.raw/*562.2*/("""#tablaPrincipal td:nth-child(11) """),format.raw/*562.35*/("""{"""),format.raw/*562.36*/(""" """),format.raw/*562.37*/("""text-align: right; """),format.raw/*562.56*/("""}"""),format.raw/*562.57*/("""
	"""),format.raw/*563.2*/("""#tablaPrincipal td:nth-child(12) """),format.raw/*563.35*/("""{"""),format.raw/*563.36*/(""" """),format.raw/*563.37*/("""text-align: center; """),format.raw/*563.57*/("""}"""),format.raw/*563.58*/("""
	"""),format.raw/*564.2*/("""#tablaPrincipal td:nth-child(13) """),format.raw/*564.35*/("""{"""),format.raw/*564.36*/(""" """),format.raw/*564.37*/("""text-align: center; """),format.raw/*564.57*/("""}"""),format.raw/*564.58*/("""
	"""),format.raw/*565.2*/("""#tablaPrincipal td:nth-child(14) """),format.raw/*565.35*/("""{"""),format.raw/*565.36*/(""" """),format.raw/*565.37*/("""text-align: right; """),format.raw/*565.56*/("""}"""),format.raw/*565.57*/("""
	"""),format.raw/*566.2*/("""#tablaPrincipal td:nth-child(15) """),format.raw/*566.35*/("""{"""),format.raw/*566.36*/(""" """),format.raw/*566.37*/("""text-align: right; """),format.raw/*566.56*/("""}"""),format.raw/*566.57*/("""
	"""),format.raw/*567.2*/("""#tablaPrincipal td:nth-child(16) """),format.raw/*567.35*/("""{"""),format.raw/*567.36*/(""" """),format.raw/*567.37*/("""text-align: right; """),format.raw/*567.56*/("""}"""),format.raw/*567.57*/("""
	"""),_display_(if(bodegaOrigen.esInterna!=1)/*568.32*/{_display_(Seq[Any](format.raw/*568.33*/("""
		"""),format.raw/*569.3*/("""#tablaPrincipal td:nth-child(17) """),format.raw/*569.36*/("""{"""),format.raw/*569.37*/(""" """),format.raw/*569.38*/("""vertical-align: middle; display:none; """),format.raw/*569.76*/("""}"""),format.raw/*569.77*/("""
		"""),_display_(/*570.4*/for((est,index) <- listTipoEstado.zipWithIndex) yield /*570.51*/{_display_(Seq[Any](format.raw/*570.52*/("""
			"""),format.raw/*571.4*/("""#tablaPrincipal td:nth-child("""),_display_(/*571.34*/(index+18)),format.raw/*571.44*/(""") """),format.raw/*571.46*/("""{"""),format.raw/*571.47*/(""" """),format.raw/*571.48*/("""vertical-align: middle; """),format.raw/*571.72*/("""}"""),format.raw/*571.73*/("""
		""")))}),format.raw/*572.4*/("""

		"""),format.raw/*574.3*/("""#tablaPrincipal td:nth-child("""),_display_(/*574.33*/(listTipoEstado.size()+18)),format.raw/*574.59*/(""") """),format.raw/*574.61*/("""{"""),format.raw/*574.62*/(""" """),format.raw/*574.63*/("""text-align: right; vertical-align: middle;"""),format.raw/*574.105*/("""}"""),format.raw/*574.106*/("""
		"""),format.raw/*575.3*/("""#tablaPrincipal td:nth-child("""),_display_(/*575.33*/(listTipoEstado.size()+19)),format.raw/*575.59*/(""") """),format.raw/*575.61*/("""{"""),format.raw/*575.62*/(""" """),format.raw/*575.63*/("""text-align: right; vertical-align: middle;"""),format.raw/*575.105*/("""}"""),format.raw/*575.106*/("""
		"""),format.raw/*576.3*/("""#tablaPrincipal td:nth-child("""),_display_(/*576.33*/(listTipoEstado.size()+20)),format.raw/*576.59*/(""") """),format.raw/*576.61*/("""{"""),format.raw/*576.62*/(""" """),format.raw/*576.63*/("""text-align: right; vertical-align: middle;"""),format.raw/*576.105*/("""}"""),format.raw/*576.106*/("""
	""")))} else {null} ),format.raw/*577.3*/("""
"""),format.raw/*578.1*/("""</style>

<script type="text/javascript">

		let auxId_guia = 0;
		let auxId_transportista = 0;
		
		let esInterna = """"),_display_(/*585.21*/bodegaOrigen/*585.33*/.esInterna),format.raw/*585.43*/("""";
	
		const selectTransporte = (id_guia) => """),format.raw/*587.41*/("""{"""),format.raw/*587.42*/("""
			"""),format.raw/*588.4*/("""auxId_guia = id_guia;
			$('#modalListaTransporte').modal('show');
		"""),format.raw/*590.3*/("""}"""),format.raw/*590.4*/("""
		
		"""),format.raw/*592.3*/("""const seleccionaTransportista = (id_transportista) => """),format.raw/*592.57*/("""{"""),format.raw/*592.58*/("""
			"""),format.raw/*593.4*/("""$('#modalListaTransporte').modal('hide');
			$('#id_guiaXLSX').val(auxId_guia);
			$('#id_transportistaXLSX').val(id_transportista);
			auxId_transportista = id_transportista;
			transportistaSeleccionado();
		"""),format.raw/*598.3*/("""}"""),format.raw/*598.4*/("""
		
		"""),format.raw/*600.3*/("""const transportistaSeleccionado = () => """),format.raw/*600.43*/("""{"""),format.raw/*600.44*/("""
			"""),format.raw/*601.4*/("""var tableReg = document.getElementById("tablaListaTransporte");
			for (var i = 1; i < tableReg.rows.length; i++)"""),format.raw/*602.50*/("""{"""),format.raw/*602.51*/("""
				"""),format.raw/*603.5*/("""var cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
				let idTransporte = cellsOfRow[0].innerHTML;
				if(idTransporte == auxId_transportista)"""),format.raw/*605.44*/("""{"""),format.raw/*605.45*/("""
					"""),format.raw/*606.6*/("""tableReg.rows[i].style.backgroundColor = "yellow";
					
				"""),format.raw/*608.5*/("""}"""),format.raw/*608.6*/("""else"""),format.raw/*608.10*/("""{"""),format.raw/*608.11*/("""
					"""),format.raw/*609.6*/("""tableReg.rows[i].style.backgroundColor = "";
				"""),format.raw/*610.5*/("""}"""),format.raw/*610.6*/("""
			"""),format.raw/*611.4*/("""}"""),format.raw/*611.5*/("""
		"""),format.raw/*612.3*/("""}"""),format.raw/*612.4*/("""
		

	"""),format.raw/*615.2*/("""let tblPrincipal;
	let listReparaNiv1;
	
	$(document).ready(function() """),format.raw/*618.31*/("""{"""),format.raw/*618.32*/("""
		"""),format.raw/*619.3*/("""$('#tablaPrincipal').DataTable("""),format.raw/*619.34*/("""{"""),format.raw/*619.35*/("""
		    	"""),format.raw/*620.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[15, 50, 100, 200, -1], [15, 50, 100, 200, "All"]],
		    	"order": [[ 4, "asc" ]],
		    	"language": """),format.raw/*623.20*/("""{"""),format.raw/*623.21*/("""
		        	"""),format.raw/*624.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*625.11*/("""}"""),format.raw/*625.12*/("""
		  """),format.raw/*626.5*/("""}"""),format.raw/*626.6*/(""" """),format.raw/*626.7*/(""");
		 
		  let repAux = ""
		  """),_display_(/*629.6*/for(r <- listTipoReparacion) yield /*629.34*/{_display_(Seq[Any](format.raw/*629.35*/("""
				"""),format.raw/*630.5*/("""repAux += """"),_display_(/*630.17*/r/*630.18*/.getId()),format.raw/*630.26*/(""":&&:"""),_display_(/*630.31*/r/*630.32*/.id_tipoEstado),format.raw/*630.46*/(""":&&:"""),_display_(/*630.51*/r/*630.52*/.getSigla()),format.raw/*630.63*/(""":&&:"""),_display_(/*630.68*/r/*630.69*/.getNombre()),format.raw/*630.81*/(""":&&:"""),_display_(/*630.86*/r/*630.87*/.getMoneda()),format.raw/*630.99*/(""":&&:"""),_display_(/*630.104*/r/*630.105*/.getPrecio()),format.raw/*630.117*/(""";&&;"
		  """)))}),format.raw/*631.6*/("""
		  """),format.raw/*632.5*/("""listReparaNiv1 = repAux.split(";&&;");
		  
		  $('#tablaListaBodegasDestino').DataTable("""),format.raw/*634.46*/("""{"""),format.raw/*634.47*/("""
		    	"""),format.raw/*635.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
				"order": [[ 1, "asc" ],[ 2, "asc" ]],
		    	"language": """),format.raw/*638.20*/("""{"""),format.raw/*638.21*/("""
		        	"""),format.raw/*639.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*640.11*/("""}"""),format.raw/*640.12*/("""
		  """),format.raw/*641.5*/("""}"""),format.raw/*641.6*/(""" """),format.raw/*641.7*/(""");
		  
		  $('#tablaListaEquipNoEnBodOrigen').DataTable("""),format.raw/*643.50*/("""{"""),format.raw/*643.51*/("""
		    	"""),format.raw/*644.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 2, "asc" ]],
		    	"language": """),format.raw/*647.20*/("""{"""),format.raw/*647.21*/("""
		        	"""),format.raw/*648.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*649.11*/("""}"""),format.raw/*649.12*/("""
		  """),format.raw/*650.5*/("""}"""),format.raw/*650.6*/(""" """),format.raw/*650.7*/(""");

		agregaComercialYcliente();
		  
		  document.getElementById('mostrarmostrar').style.display="block";

		
		if(esInterna != "1")"""),format.raw/*657.23*/("""{"""),format.raw/*657.24*/("""
			"""),format.raw/*658.4*/("""let verifica = document.querySelectorAll(".verificaStock");
			let sinNegativos = 0;
			for (let i = 0; i < verifica.length; i++) """),format.raw/*660.46*/("""{"""),format.raw/*660.47*/("""
				"""),format.raw/*661.5*/("""let aux = verifica[i].textContent.replace(/,/g,'')
				if(parseFloat(aux) < 0)"""),format.raw/*662.28*/("""{"""),format.raw/*662.29*/("""
					"""),format.raw/*663.6*/("""sinNegativos = 1;
				"""),format.raw/*664.5*/("""}"""),format.raw/*664.6*/("""
			"""),format.raw/*665.4*/("""}"""),format.raw/*665.5*/("""
			"""),format.raw/*666.4*/("""if(sinNegativos>0)"""),format.raw/*666.22*/("""{"""),format.raw/*666.23*/("""
				"""),format.raw/*667.5*/("""alertify.alert('El estado de los stock de """),_display_(/*667.48*/mapDiccionario/*667.62*/.get("BODEGA")),format.raw/*667.76*/(""": """),_display_(/*667.79*/bodegaOrigen/*667.91*/.getNombre()),format.raw/*667.103*/(""".- '
					+'Presenta dificultades, favor contactar a soporte Inqsol indicando el nombre de """),_display_(/*668.88*/mapDiccionario/*668.102*/.get("BODEGA")),format.raw/*668.116*/(""" """),format.raw/*668.117*/("""y que es un movimiento', function () """),format.raw/*668.154*/("""{"""),format.raw/*668.155*/("""
			     			"""),format.raw/*669.12*/("""location.href = "/home/";
			     	"""),format.raw/*670.10*/("""}"""),format.raw/*670.11*/(""");
			"""),format.raw/*671.4*/("""}"""),format.raw/*671.5*/("""
		"""),format.raw/*672.3*/("""}"""),format.raw/*672.4*/("""
	"""),format.raw/*673.2*/("""}"""),format.raw/*673.3*/(""");

		const agregaComercialYcliente = () =>"""),format.raw/*675.40*/("""{"""),format.raw/*675.41*/("""
			"""),format.raw/*676.4*/("""let formData = new FormData();
			formData.append('id_bodegaOrigen','"""),_display_(/*677.40*/bodegaOrigen/*677.52*/.getId()),format.raw/*677.60*/("""');
			formData.append('id_bodegaDestino','"""),_display_(/*678.41*/bodegaDestino/*678.54*/.getId()),format.raw/*678.62*/("""');

			if(""""),_display_(/*680.9*/comercial/*680.18*/.getId()),format.raw/*680.26*/("""" == "-1")"""),format.raw/*680.36*/("""{"""),format.raw/*680.37*/("""
				"""),format.raw/*681.5*/("""$.ajax("""),format.raw/*681.12*/("""{"""),format.raw/*681.13*/("""
					"""),format.raw/*682.6*/("""url: "/nameComercialAjax/",
					type: "POST",
					method: "POST",
					data: formData,
					cache: false,
					contentType: false,
					processData: false,
					success: function(respuesta)"""),format.raw/*689.34*/("""{"""),format.raw/*689.35*/("""
						"""),format.raw/*690.7*/("""$("#nameComercial").val(respuesta);
					"""),format.raw/*691.6*/("""}"""),format.raw/*691.7*/("""
				"""),format.raw/*692.5*/("""}"""),format.raw/*692.6*/(""");
			"""),format.raw/*693.4*/("""}"""),format.raw/*693.5*/("""

			"""),format.raw/*695.4*/("""$.ajax("""),format.raw/*695.11*/("""{"""),format.raw/*695.12*/("""
				"""),format.raw/*696.5*/("""url: "/nickNameClienteAjax/",
				type: "POST",
				method: "POST",
				data: formData,
				cache: false,
				contentType: false,
				processData: false,
				success: function(respuesta)"""),format.raw/*703.33*/("""{"""),format.raw/*703.34*/("""
					"""),format.raw/*704.6*/("""$("#nickCliente").val(respuesta);
				"""),format.raw/*705.5*/("""}"""),format.raw/*705.6*/("""
			"""),format.raw/*706.4*/("""}"""),format.raw/*706.5*/(""");

		"""),format.raw/*708.3*/("""}"""),format.raw/*708.4*/("""
	
	"""),format.raw/*710.2*/("""const verificarNumeroGuia = (value) =>"""),format.raw/*710.40*/("""{"""),format.raw/*710.41*/("""
		"""),format.raw/*711.3*/("""if(value.trim()=="")"""),format.raw/*711.23*/("""{"""),format.raw/*711.24*/("""
			"""),format.raw/*712.4*/("""$("#numeroGuia").val(""""),_display_(/*712.27*/nuevoNumeroGuia),format.raw/*712.42*/("""");
		"""),format.raw/*713.3*/("""}"""),format.raw/*713.4*/("""else"""),format.raw/*713.8*/("""{"""),format.raw/*713.9*/("""
			"""),format.raw/*714.4*/("""let aux = '"""),_display_(/*714.16*/nuevoNumeroGuia),format.raw/*714.31*/("""';
			var formData = new FormData();
		  	formData.append('numeroGuia',value);
	        $.ajax("""),format.raw/*717.17*/("""{"""),format.raw/*717.18*/("""
	            """),format.raw/*718.14*/("""url: "/verificaNumeroGuiaAjax/",
	            type: "POST",
	            method: "POST",
	            data: formData,
	            cache: false,
	            contentType: false,
		     	processData: false,
		     	success: function(respuesta)"""),format.raw/*725.37*/("""{"""),format.raw/*725.38*/("""
		     		"""),format.raw/*726.10*/("""if(respuesta=="existe")"""),format.raw/*726.33*/("""{"""),format.raw/*726.34*/("""
		     			"""),format.raw/*727.11*/("""alertify.alert('El número de movimiento ya existe, intente con otro', function () """),format.raw/*727.93*/("""{"""),format.raw/*727.94*/("""
		     				"""),format.raw/*728.12*/("""$("#numeroGuia").val(""""),_display_(/*728.35*/nuevoNumeroGuia),format.raw/*728.50*/("""");
		     			"""),format.raw/*729.11*/("""}"""),format.raw/*729.12*/(""");
		     		"""),format.raw/*730.10*/("""}"""),format.raw/*730.11*/("""
		     		"""),format.raw/*731.10*/("""if(respuesta=="error")"""),format.raw/*731.32*/("""{"""),format.raw/*731.33*/("""
		     			"""),format.raw/*732.11*/("""alertify.alert(msgError, function () """),format.raw/*732.48*/("""{"""),format.raw/*732.49*/("""
			     			"""),format.raw/*733.12*/("""location.href = "/";
			     		"""),format.raw/*734.11*/("""}"""),format.raw/*734.12*/(""");
		     		"""),format.raw/*735.10*/("""}"""),format.raw/*735.11*/("""
		     	"""),format.raw/*736.9*/("""}"""),format.raw/*736.10*/("""
	        """),format.raw/*737.10*/("""}"""),format.raw/*737.11*/(""");
		"""),format.raw/*738.3*/("""}"""),format.raw/*738.4*/("""
	"""),format.raw/*739.2*/("""}"""),format.raw/*739.3*/("""
	
	"""),format.raw/*741.2*/("""const validarForm = () =>"""),format.raw/*741.27*/("""{"""),format.raw/*741.28*/("""
		"""),format.raw/*742.3*/("""$("#aplica").attr('disabled',true);
		let flag = true;
		if($("#id_bodegaDestino").val()=="")"""),format.raw/*744.39*/("""{"""),format.raw/*744.40*/("""
			"""),format.raw/*745.4*/("""flag = false;
			alertify.alert('FALTA SELECCIONAR DESTINO DE """),_display_(/*746.50*/mapDiccionario/*746.64*/.get("BODEGA")),format.raw/*746.78*/("""/PROYECTO<br>(CAMPO DESTINO)', function () """),format.raw/*746.121*/("""{"""),format.raw/*746.122*/("""
				"""),format.raw/*747.5*/("""document.getElementById('modifica').style.visibility = 'visible';
				document.getElementById('aplica').style.visibility = 'visible';
				document.getElementById('cancelar').style.visibility = 'visible';
				$("#aplica").attr('disabled',false);
				return(flag);
     		"""),format.raw/*752.8*/("""}"""),format.raw/*752.9*/(""");
		"""),format.raw/*753.3*/("""}"""),format.raw/*753.4*/("""else"""),format.raw/*753.8*/("""{"""),format.raw/*753.9*/("""
			"""),format.raw/*754.4*/("""$("#tablaPrincipal").dataTable().fnDestroy();
			let tabla = document.getElementById("tablaPrincipal");
			let validaCantidades = 0;
			for(i=1; i<tabla.rows.length-1; i++)"""),format.raw/*757.40*/("""{"""),format.raw/*757.41*/("""
				"""),format.raw/*758.5*/("""let cantidad = $(".cantidad",tabla.rows[i].cells[10]).val();
				let exceso = $(".exceso",tabla.rows[i].cells[13]).val();
				cantidad = cantidad.replace(/,/g,'');
				exceso = exceso.replace(/,/g,'');
				validaCantidades += parseFloat(cantidad) + parseFloat(exceso);
			"""),format.raw/*763.4*/("""}"""),format.raw/*763.5*/("""
			"""),format.raw/*764.4*/("""if(validaCantidades > 0)"""),format.raw/*764.28*/("""{"""),format.raw/*764.29*/("""
				"""),format.raw/*765.5*/("""for(i=1; i<tabla.rows.length-1; i++)"""),format.raw/*765.41*/("""{"""),format.raw/*765.42*/("""
					"""),format.raw/*766.6*/("""let cantidad = $(".cantidad",tabla.rows[i].cells[10]).val();
					let exceso = $(".exceso",tabla.rows[i].cells[13]).val();
					cantidad = cantidad.replace(/,/g,'');
					exceso = exceso.replace(/,/g,'');
					let total = parseFloat(cantidad) + parseFloat(exceso);
					if(total <= 0 )"""),format.raw/*771.21*/("""{"""),format.raw/*771.22*/("""
						"""),format.raw/*772.7*/("""tabla.deleteRow(i);
						i--;
					"""),format.raw/*774.6*/("""}"""),format.raw/*774.7*/("""
				"""),format.raw/*775.5*/("""}"""),format.raw/*775.6*/("""
				"""),format.raw/*776.5*/("""document.getElementById('enCarga').style.display="block";
				return(true);
			"""),format.raw/*778.4*/("""}"""),format.raw/*778.5*/("""else"""),format.raw/*778.9*/("""{"""),format.raw/*778.10*/("""
				"""),format.raw/*779.5*/("""flag = false;
				alertify.alert('NO PUEDE GRABAR UN MOVIMIENTO SIN MOVER EQUIPOS', function () """),format.raw/*780.83*/("""{"""),format.raw/*780.84*/("""
					"""),format.raw/*781.6*/("""document.getElementById('modifica').style.visibility = 'visible';
					document.getElementById('aplica').style.visibility = 'visible';
					document.getElementById('cancelar').style.visibility = 'visible';
					$("#aplica").attr('disabled',false);
					return(flag);
	     		"""),format.raw/*786.9*/("""}"""),format.raw/*786.10*/(""");
			"""),format.raw/*787.4*/("""}"""),format.raw/*787.5*/("""
		"""),format.raw/*788.3*/("""}"""),format.raw/*788.4*/("""
		"""),format.raw/*789.3*/("""return(flag);
	"""),format.raw/*790.2*/("""}"""),format.raw/*790.3*/("""
	
	"""),format.raw/*792.2*/("""let cantAux = 0;
	const verificaCantidad = (id_equipo, id_cotizacion, stock, cantidad, KG, M2, largo) =>"""),format.raw/*793.88*/("""{"""),format.raw/*793.89*/("""
		"""),format.raw/*794.3*/("""let acum = 0;
		let exce = $("#exceso_"+id_equipo+"_"+id_cotizacion).val();
		exce = exce.replace(/,/g,'');
		
		if(auxBueno == 999)"""),format.raw/*798.22*/("""{"""),format.raw/*798.23*/("""
			"""),format.raw/*799.4*/("""for(let i=0; i<largo; i++)"""),format.raw/*799.30*/("""{"""),format.raw/*799.31*/("""
				"""),format.raw/*800.5*/("""if( ! (i == auxIndex && auxBueno == 999))"""),format.raw/*800.46*/("""{"""),format.raw/*800.47*/("""
					"""),format.raw/*801.6*/("""let auxAcum = $("#estado_"+id_equipo+"_"+id_cotizacion+"_"+i).val();
					auxAcum = auxAcum.replace(/,/g,'');
					acum += parseFloat(auxAcum);
				"""),format.raw/*804.5*/("""}"""),format.raw/*804.6*/("""
			"""),format.raw/*805.4*/("""}"""),format.raw/*805.5*/("""
		"""),format.raw/*806.3*/("""}"""),format.raw/*806.4*/("""else if('"""),_display_(/*806.14*/mapDiccionario/*806.28*/.get("nEmpresa")),format.raw/*806.44*/("""' == "HOHE" && ( auxBueno == 2 || auxBueno == 3 ) )"""),format.raw/*806.95*/("""{"""),format.raw/*806.96*/("""
			"""),format.raw/*807.4*/("""for(let i=0; i<largo; i++)"""),format.raw/*807.30*/("""{"""),format.raw/*807.31*/("""
				"""),format.raw/*808.5*/("""if( ! (i == auxIndex && (auxBueno == 2 || auxBueno == 3 )))"""),format.raw/*808.64*/("""{"""),format.raw/*808.65*/("""
					"""),format.raw/*809.6*/("""let auxAcum = $("#estado_"+id_equipo+"_"+id_cotizacion+"_"+i).val();
					auxAcum = auxAcum.replace(/,/g,'');
					acum += parseFloat(auxAcum);
				"""),format.raw/*812.5*/("""}"""),format.raw/*812.6*/("""
			"""),format.raw/*813.4*/("""}"""),format.raw/*813.5*/("""
		
		"""),format.raw/*815.3*/("""}"""),format.raw/*815.4*/("""else"""),format.raw/*815.8*/("""{"""),format.raw/*815.9*/("""
			"""),format.raw/*816.4*/("""for(let i=0; i<largo; i++)"""),format.raw/*816.30*/("""{"""),format.raw/*816.31*/("""
				"""),format.raw/*817.5*/("""acum += parseFloat($("#estado_"+id_equipo+"_"+id_cotizacion+"_"+i).val());
			"""),format.raw/*818.4*/("""}"""),format.raw/*818.5*/("""
		"""),format.raw/*819.3*/("""}"""),format.raw/*819.4*/("""
		
		
		
		"""),format.raw/*823.3*/("""if(parseFloat(acum) > (parseFloat(cantidad)))"""),format.raw/*823.48*/("""{"""),format.raw/*823.49*/("""
			"""),format.raw/*824.4*/("""let auxCant1 = cantAux;
			alertify.alert('La cantidad no puede ser menor a la suma de estados, modifique primero los estados', function () """),format.raw/*825.117*/("""{"""),format.raw/*825.118*/("""
				"""),format.raw/*826.5*/("""$("#cantidad_"+id_equipo+"_"+id_cotizacion).val(formatStandar(auxCant1,2));
     		"""),format.raw/*827.8*/("""}"""),format.raw/*827.9*/(""");
		"""),format.raw/*828.3*/("""}"""),format.raw/*828.4*/("""else"""),format.raw/*828.8*/("""{"""),format.raw/*828.9*/("""
			"""),format.raw/*829.4*/("""stock = stock.replace(/,/g,'');
			KG = KG.replace(/,/g,'');
			M2 = M2.replace(/,/g,'');
			let saldo = parseFloat(stock)-parseFloat(cantidad);
			
			if(saldo >= 0)"""),format.raw/*834.18*/("""{"""),format.raw/*834.19*/("""
				"""),format.raw/*835.5*/("""$("#stock_"+id_equipo+"_"+id_cotizacion).text(formatStandar(saldo,2));
				$("#cantidad_"+id_equipo+"_"+id_cotizacion).val(formatStandar(cantidad,2));
				$("#exceso_"+id_equipo+"_"+id_cotizacion).val(formatStandar("0"));
			"""),format.raw/*838.4*/("""}"""),format.raw/*838.5*/("""else"""),format.raw/*838.9*/("""{"""),format.raw/*838.10*/("""
				"""),format.raw/*839.5*/("""if('"""),_display_(/*839.10*/bodegaOrigen/*839.22*/.getEsInterna()),format.raw/*839.37*/("""' == '1')"""),format.raw/*839.46*/("""{"""),format.raw/*839.47*/("""
					"""),format.raw/*840.6*/("""let auxCant2 = cantAux;
					alertify.alert('La cantidad no puede ser mayor que el stock disponible', function () """),format.raw/*841.91*/("""{"""),format.raw/*841.92*/("""
						"""),format.raw/*842.7*/("""$("#cantidad_"+id_equipo+"_"+id_cotizacion).val(formatStandar(auxCant2,2));
		     		"""),format.raw/*843.10*/("""}"""),format.raw/*843.11*/(""");
				"""),format.raw/*844.5*/("""}"""),format.raw/*844.6*/("""else"""),format.raw/*844.10*/("""{"""),format.raw/*844.11*/("""
					"""),format.raw/*845.6*/("""$("#stock_"+id_equipo+"_"+id_cotizacion).text(formatStandar(0,2));
					$("#cantidad_"+id_equipo+"_"+id_cotizacion).val(formatStandar(stock,2));
					$("#exceso_"+id_equipo+"_"+id_cotizacion).val(formatStandar(saldo*-1,2));
					cantidad = stock;
				"""),format.raw/*849.5*/("""}"""),format.raw/*849.6*/("""
			"""),format.raw/*850.4*/("""}"""),format.raw/*850.5*/("""
			
			"""),format.raw/*852.4*/("""if(auxBueno == 999)"""),format.raw/*852.23*/("""{"""),format.raw/*852.24*/("""
				"""),format.raw/*853.5*/("""let difB = parseFloat(cantidad) - parseFloat(acum);
				$("#estado_"+id_equipo+"_"+id_cotizacion+"_"+auxIndex).val(formatStandar(difB,2));
				
				let aux = $("#estados_"+id_equipo+"_"+id_cotizacion).val();
				let niv1 = aux.split(";");
				let flag = true;
				let buscar = "";
				for(i=0; i<niv1.length && flag; i++)"""),format.raw/*860.41*/("""{"""),format.raw/*860.42*/("""
					"""),format.raw/*861.6*/("""let niv2 = niv1[i].split(":");
					if(niv2[0] == auxBueno)"""),format.raw/*862.29*/("""{"""),format.raw/*862.30*/("""
						"""),format.raw/*863.7*/("""buscar = auxBueno+":"+niv2[1]+";";
						flag = false;
					"""),format.raw/*865.6*/("""}"""),format.raw/*865.7*/("""
				"""),format.raw/*866.5*/("""}"""),format.raw/*866.6*/("""
				
				"""),format.raw/*868.5*/("""if(flag)"""),format.raw/*868.13*/("""{"""),format.raw/*868.14*/("""
					"""),format.raw/*869.6*/("""aux += auxBueno+":"+parseFloat(difB)+";";
					$("#estados_"+id_equipo+"_"+id_cotizacion).val(aux);
				"""),format.raw/*871.5*/("""}"""),format.raw/*871.6*/("""else"""),format.raw/*871.10*/("""{"""),format.raw/*871.11*/("""
					"""),format.raw/*872.6*/("""aux = aux.replace(buscar, auxBueno+":"+parseFloat(difB)+";");
					$("#estados_"+id_equipo+"_"+id_cotizacion).val(aux);
				"""),format.raw/*874.5*/("""}"""),format.raw/*874.6*/("""
				
			"""),format.raw/*876.4*/("""}"""),format.raw/*876.5*/(""" """),format.raw/*876.6*/("""else if('"""),_display_(/*876.16*/mapDiccionario/*876.30*/.get("nEmpresa")),format.raw/*876.46*/("""' == "HOHE" && ( auxBueno == 2 || auxBueno == 3 ) )"""),format.raw/*876.97*/("""{"""),format.raw/*876.98*/("""
				"""),format.raw/*877.5*/("""let difB = parseFloat(cantidad) - parseFloat(acum);
				$("#estado_"+id_equipo+"_"+id_cotizacion+"_"+auxIndex).val(formatStandar(difB,2));
				
				let aux = $("#estados_"+id_equipo+"_"+id_cotizacion).val();
				let niv1 = aux.split(";");
				let flag = true;
				let buscar = "";
				for(i=0; i<niv1.length && flag; i++)"""),format.raw/*884.41*/("""{"""),format.raw/*884.42*/("""
					"""),format.raw/*885.6*/("""let niv2 = niv1[i].split(":");
					if(niv2[0] == auxBueno)"""),format.raw/*886.29*/("""{"""),format.raw/*886.30*/("""
						"""),format.raw/*887.7*/("""buscar = auxBueno+":"+niv2[1]+";";
						flag = false;
					"""),format.raw/*889.6*/("""}"""),format.raw/*889.7*/("""
				"""),format.raw/*890.5*/("""}"""),format.raw/*890.6*/("""
				
				"""),format.raw/*892.5*/("""if(flag)"""),format.raw/*892.13*/("""{"""),format.raw/*892.14*/("""
					"""),format.raw/*893.6*/("""aux += auxBueno+":"+parseFloat(difB)+";";
					$("#estados_"+id_equipo+"_"+id_cotizacion).val(aux);
				"""),format.raw/*895.5*/("""}"""),format.raw/*895.6*/("""else"""),format.raw/*895.10*/("""{"""),format.raw/*895.11*/("""
					"""),format.raw/*896.6*/("""aux = aux.replace(buscar, auxBueno+":"+parseFloat(difB)+";");
					$("#estados_"+id_equipo+"_"+id_cotizacion).val(aux);
				"""),format.raw/*898.5*/("""}"""),format.raw/*898.6*/("""
			"""),format.raw/*899.4*/("""}"""),format.raw/*899.5*/("""
			
			"""),format.raw/*901.4*/("""let totKg = parseFloat(KG) * parseFloat(cantidad);
			let totM2 = parseFloat(M2) * parseFloat(cantidad);
			$("#KG_"+id_equipo+"_"+id_cotizacion).text(formatStandar(totKg,2));
			$("#M2_"+id_equipo+"_"+id_cotizacion).text(formatStandar(totM2,2));
			
			if(esInterna != "1")"""),format.raw/*906.24*/("""{"""),format.raw/*906.25*/("""
				"""),_display_(if(mapPermiso.get("parametro.movimiento-devolucion-cantCliente")!=null && mapPermiso.get("parametro.movimiento-devolucion-cantCliente").equals("1"))/*907.154*/{_display_(Seq[Any](format.raw/*907.155*/("""
					"""),format.raw/*908.6*/("""actualizaCantCliente(id_equipo, id_cotizacion);
				""")))} else {null} ),format.raw/*909.6*/("""
			"""),format.raw/*910.4*/("""}"""),format.raw/*910.5*/("""
		"""),format.raw/*911.3*/("""}"""),format.raw/*911.4*/("""
	"""),format.raw/*912.2*/("""}"""),format.raw/*912.3*/("""
	
	"""),format.raw/*914.2*/("""const actualizaCantCliente = (id_equipo, id_cotizacion) =>"""),format.raw/*914.60*/("""{"""),format.raw/*914.61*/("""
		"""),format.raw/*915.3*/("""let cantCliente = $("#cantCliente_"+id_equipo+"_"+id_cotizacion).val();
		cantCliente = cantCliente.replace(/,/g,'');
		
		let cantidad = $("#cantidad_"+id_equipo+"_"+id_cotizacion).val();
		cantidad = cantidad.replace(/,/g,'');
		
		let difCliente = parseFloat(cantidad) - parseFloat(cantCliente);
		
		$("#difCliente_"+id_equipo+"_"+id_cotizacion).text(formatStandar(difCliente,2));
	"""),format.raw/*924.2*/("""}"""),format.raw/*924.3*/("""
	
	"""),format.raw/*926.2*/("""const seleccionaEquipNoEnBodOrigen = (nodo, id_equipo, KG, M2, UN) =>"""),format.raw/*926.71*/("""{"""),format.raw/*926.72*/("""
		"""),format.raw/*927.3*/("""let grupo = 
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
							" onchange=\"if(value=='') value=0; verificaCantidad("+id_equipo+",'0','0',value,'"+KG+"','"+M2+"',"""),_display_(/*941.108*/listTipoEstado/*941.122*/.size()),format.raw/*941.129*/(""");\"></div>";
		let esVenta = "<input type='hidden' name='esVenta[]' id=\"esVenta_"+id_equipo+"_0\" value='0'>"+
						"<input type='checkbox' onchange='if($(\"#esVenta_"+id_equipo+"_0\").val()==1) "+
						"$(\"#esVenta_"+id_equipo+"_0\").val(0); else $(\"#esVenta_"+id_equipo+"_0\").val(1);'>";
		let esNuevo = "<input type='hidden' name='esNuevo[]' id=\"esNuevo_"+id_equipo+"_0\" value='0'>";
		let exceso = "<div align='center'><input type='text' class='exceso form-control height23px right width80px' "+
							" name='exceso[]' "+
							" id='exceso_"+id_equipo+"_0' "+
							" value='0.00' "+
							" readonly></div>";
		let auxKG = "<div class='KG' id='KG_"+id_equipo+"_0'>0.00</div>";
		let auxM2 = "<div class='M2' id='M2_"+id_equipo+"_0'>0.00</div>";
		
		
		let row = $('#tablaPrincipal').dataTable().fnAddData( [
			grupo,"--","","--",codigo,equipo,KG,M2,UN,stock,cantidad,esVenta,esNuevo,exceso,auxKG,auxM2,
				"""),_display_(if(bodegaOrigen.esInterna!=1)/*957.35*/{_display_(Seq[Any](format.raw/*957.36*/("""
					"""),format.raw/*958.6*/(""""<input type='hidden' name='estados[]'   id='estados_"+id_equipo+"_0'><input type='hidden' name='reparaciones[]'   id='reparaciones_"+id_equipo+"_0'>",
					"""),_display_(/*959.7*/for((est,index) <- listTipoEstado.zipWithIndex) yield /*959.54*/{_display_(Seq[Any](format.raw/*959.55*/("""
							"""),format.raw/*960.8*/(""""<input type='text' class='form-control height23px right width60px' "+
								" style='font-size:10px; vertical-align:middle' "+
								" id='estado_"+id_equipo+"_0_"""),_display_(/*962.39*/index),format.raw/*962.44*/("""' " +
								" value='0.00' "+
								" readonly> ",
					""")))}),format.raw/*965.7*/("""
					"""),_display_(if(mapPermiso.get("parametro.movimiento-devolucion-cantCliente")!=null && mapPermiso.get("parametro.movimiento-devolucion-cantCliente").equals("1"))/*966.155*/{_display_(Seq[Any](format.raw/*966.156*/("""
						"""),format.raw/*967.7*/(""""<div align='center'><input type=\"text\" class=\"cantCliente form-control height23px right width80px\" "+
								" name=\"cantCliente[]\" "+
								" id=\"cantCliente_"+id_equipo+"_0\" "+
								" value=\"0.00\" "+
								" onfocus=\"value=value.replace(/,/g,\'\');\" "+
								" onkeydown=\"return ingresoDouble(window.event)\" "+
								" autocomplete='off' "+
								" onchange=\"if(value=='') value=0; actualizaCantCliente("+id_equipo+",'0');\"></div>",
								
						"<div class='difCliente' id='difCliente_"+id_equipo+"_0'>0.00</div>",
					""")))} else {null} ),format.raw/*977.7*/("""
				""")))} else {null} ),format.raw/*978.6*/("""
		"""),format.raw/*979.3*/("""] );
		
		$("#tablaPrincipal").dataTable().fnDestroy();
			var tableReg = document.getElementById("tablaPrincipal");
			for (var i = 1; i < tableReg.rows.length; i++)"""),format.raw/*983.50*/("""{"""),format.raw/*983.51*/("""
				"""),format.raw/*984.5*/("""var cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
				if(cellsOfRow[4].innerHTML == codigo)"""),format.raw/*985.42*/("""{"""),format.raw/*985.43*/("""
					"""),format.raw/*986.6*/("""if(""""),_display_(/*986.11*/sinVenta),format.raw/*986.19*/("""" == "1")"""),format.raw/*986.28*/("""{"""),format.raw/*986.29*/("""
						"""),format.raw/*987.7*/("""cellsOfRow[11].style.display = 'none';
						cellsOfRow[12].style.display = 'none';
					"""),format.raw/*989.6*/("""}"""),format.raw/*989.7*/("""else"""),format.raw/*989.11*/("""{"""),format.raw/*989.12*/("""
						"""),format.raw/*990.7*/("""cellsOfRow[12].style.display = 'none';
					"""),format.raw/*991.6*/("""}"""),format.raw/*991.7*/("""
					
				"""),format.raw/*993.5*/("""}"""),format.raw/*993.6*/("""
			"""),format.raw/*994.4*/("""}"""),format.raw/*994.5*/("""
		"""),format.raw/*995.3*/("""tblPrincipal = $('#tablaPrincipal').DataTable("""),format.raw/*995.49*/("""{"""),format.raw/*995.50*/("""
	    	"""),format.raw/*996.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[15, 50, 100, 200, -1], [15, 50, 100, 200, "All"]],
	    	"order": [[ 4, "asc" ]],
	    	"language": """),format.raw/*999.19*/("""{"""),format.raw/*999.20*/("""
	        	"""),format.raw/*1000.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*1001.10*/("""}"""),format.raw/*1001.11*/("""
	  	"""),format.raw/*1002.5*/("""}"""),format.raw/*1002.6*/(""" """),format.raw/*1002.7*/(""");
		
		
		$('#tablaListaEquipNoEnBodOrigen').dataTable().fnDeleteRow(nodo);
		$("#equipNoEnBodOrigen").modal("hide");
	"""),format.raw/*1007.2*/("""}"""),format.raw/*1007.3*/("""
	
	
	"""),format.raw/*1010.2*/("""const verificaCantEstado = (index, largo, id_equipo, id_cotizacion, cantEstado, esReparable, id_tipoEstado) =>"""),format.raw/*1010.112*/("""{"""),format.raw/*1010.113*/("""
		"""),format.raw/*1011.3*/("""let acum = 0;
		let cant = $("#cantidad_"+id_equipo+"_"+id_cotizacion).val();
		let exce = $("#exceso_"+id_equipo+"_"+id_cotizacion).val();
		cant = cant.replace(/,/g,'');
		exce = exce.replace(/,/g,'');
		
		for(let i=0; i<largo; i++)"""),format.raw/*1017.29*/("""{"""),format.raw/*1017.30*/("""
			"""),format.raw/*1018.4*/("""if( (! (i == auxIndex && auxBueno == 999)) && (! (i == auxIndex && (auxBueno == 2 || auxBueno == 3))))"""),format.raw/*1018.106*/("""{"""),format.raw/*1018.107*/("""
				"""),format.raw/*1019.5*/("""let auxAcum = $("#estado_"+id_equipo+"_"+id_cotizacion+"_"+i).val();
				auxAcum = auxAcum.replace(/,/g,'');
				acum += parseFloat(auxAcum);
			"""),format.raw/*1022.4*/("""}"""),format.raw/*1022.5*/("""
		"""),format.raw/*1023.3*/("""}"""),format.raw/*1023.4*/("""
		
		
		"""),format.raw/*1026.3*/("""if(parseFloat(acum) > (parseFloat(cant)))"""),format.raw/*1026.44*/("""{"""),format.raw/*1026.45*/("""
			"""),format.raw/*1027.4*/("""alertify.alert('La suma de estados no puede ser mayor a campo cantidad', function () """),format.raw/*1027.89*/("""{"""),format.raw/*1027.90*/("""
				"""),format.raw/*1028.5*/("""$("#estado_"+id_equipo+"_"+id_cotizacion+"_"+index).val("0.00");
				
				if(auxBueno == 999)"""),format.raw/*1030.24*/("""{"""),format.raw/*1030.25*/("""
					"""),format.raw/*1031.6*/("""let newAcum = 0;
					for(let i=0; i<largo; i++)"""),format.raw/*1032.32*/("""{"""),format.raw/*1032.33*/("""
						"""),format.raw/*1033.7*/("""if( ! (i == auxIndex && auxBueno == 999))"""),format.raw/*1033.48*/("""{"""),format.raw/*1033.49*/("""
							"""),format.raw/*1034.8*/("""let auxAcum = $("#estado_"+id_equipo+"_"+id_cotizacion+"_"+i).val();
							auxAcum = auxAcum.replace(/,/g,'');
							newAcum += parseFloat(auxAcum);
						"""),format.raw/*1037.7*/("""}"""),format.raw/*1037.8*/("""
					"""),format.raw/*1038.6*/("""}"""),format.raw/*1038.7*/("""
					"""),format.raw/*1039.6*/("""let difB = parseFloat(cant) - parseFloat(newAcum);
					$("#estado_"+id_equipo+"_"+id_cotizacion+"_"+auxIndex).val(formatStandar(difB,2));
					
					let aux = $("#estados_"+id_equipo+"_"+id_cotizacion).val();
					let niv1 = aux.split(";");
					let flag = true;
					let buscar = "";
					for(i=0; i<niv1.length && flag; i++)"""),format.raw/*1046.42*/("""{"""),format.raw/*1046.43*/("""
						"""),format.raw/*1047.7*/("""let niv2 = niv1[i].split(":");
						if(niv2[0] == auxBueno)"""),format.raw/*1048.30*/("""{"""),format.raw/*1048.31*/("""
							"""),format.raw/*1049.8*/("""buscar = auxBueno+":"+niv2[1]+";";
							flag = false;
						"""),format.raw/*1051.7*/("""}"""),format.raw/*1051.8*/("""
					"""),format.raw/*1052.6*/("""}"""),format.raw/*1052.7*/("""
					
					"""),format.raw/*1054.6*/("""if(flag)"""),format.raw/*1054.14*/("""{"""),format.raw/*1054.15*/("""
						"""),format.raw/*1055.7*/("""aux += auxBueno+":"+parseFloat(difB)+";";
						$("#estados_"+id_equipo+"_"+id_cotizacion).val(aux);
					"""),format.raw/*1057.6*/("""}"""),format.raw/*1057.7*/("""else"""),format.raw/*1057.11*/("""{"""),format.raw/*1057.12*/("""
						"""),format.raw/*1058.7*/("""aux = aux.replace(buscar,auxBueno+":"+parseFloat(difB)+";");
						$("#estados_"+id_equipo+"_"+id_cotizacion).val(aux);
					"""),format.raw/*1060.6*/("""}"""),format.raw/*1060.7*/("""
				"""),format.raw/*1061.5*/("""}"""),format.raw/*1061.6*/("""else if('"""),_display_(/*1061.16*/mapDiccionario/*1061.30*/.get("nEmpresa")),format.raw/*1061.46*/("""' == "HOHE" && ( auxBueno == 2 || auxBueno == 3 ) )"""),format.raw/*1061.97*/("""{"""),format.raw/*1061.98*/("""
					"""),format.raw/*1062.6*/("""let newAcum = 0;
					for(let i=0; i<largo; i++)"""),format.raw/*1063.32*/("""{"""),format.raw/*1063.33*/("""
						"""),format.raw/*1064.7*/("""if( ! (i == auxIndex && (auxBueno == 2 || auxBueno == 3)))"""),format.raw/*1064.65*/("""{"""),format.raw/*1064.66*/("""
							"""),format.raw/*1065.8*/("""let auxAcum = $("#estado_"+id_equipo+"_"+id_cotizacion+"_"+i).val();
							auxAcum = auxAcum.replace(/,/g,'');
							newAcum += parseFloat(auxAcum);
						"""),format.raw/*1068.7*/("""}"""),format.raw/*1068.8*/("""
					"""),format.raw/*1069.6*/("""}"""),format.raw/*1069.7*/("""
					"""),format.raw/*1070.6*/("""let difB = parseFloat(cant) - parseFloat(newAcum);
					$("#estado_"+id_equipo+"_"+id_cotizacion+"_"+auxIndex).val(formatStandar(difB,2));
					
					let aux = $("#estados_"+id_equipo+"_"+id_cotizacion).val();
					let niv1 = aux.split(";");
					let flag = true;
					let buscar = "";
					for(i=0; i<niv1.length && flag; i++)"""),format.raw/*1077.42*/("""{"""),format.raw/*1077.43*/("""
						"""),format.raw/*1078.7*/("""let niv2 = niv1[i].split(":");
						if(niv2[0] == auxBueno)"""),format.raw/*1079.30*/("""{"""),format.raw/*1079.31*/("""
							"""),format.raw/*1080.8*/("""buscar = auxBueno+":"+niv2[1]+";";
							flag = false;
						"""),format.raw/*1082.7*/("""}"""),format.raw/*1082.8*/("""
					"""),format.raw/*1083.6*/("""}"""),format.raw/*1083.7*/("""
					
					"""),format.raw/*1085.6*/("""if(flag)"""),format.raw/*1085.14*/("""{"""),format.raw/*1085.15*/("""
						"""),format.raw/*1086.7*/("""aux += auxBueno+":"+parseFloat(difB)+";";
						$("#estados_"+id_equipo+"_"+id_cotizacion).val(aux);
					"""),format.raw/*1088.6*/("""}"""),format.raw/*1088.7*/("""else"""),format.raw/*1088.11*/("""{"""),format.raw/*1088.12*/("""
						"""),format.raw/*1089.7*/("""aux = aux.replace(buscar,auxBueno+":"+parseFloat(difB)+";");
						$("#estados_"+id_equipo+"_"+id_cotizacion).val(aux);
					"""),format.raw/*1091.6*/("""}"""),format.raw/*1091.7*/("""
				"""),format.raw/*1092.5*/("""}"""),format.raw/*1092.6*/("""
     		"""),format.raw/*1093.8*/("""}"""),format.raw/*1093.9*/(""");
		"""),format.raw/*1094.3*/("""}"""),format.raw/*1094.4*/("""else"""),format.raw/*1094.8*/("""{"""),format.raw/*1094.9*/("""
			
			"""),format.raw/*1096.4*/("""if(auxBueno == 999)"""),format.raw/*1096.23*/("""{"""),format.raw/*1096.24*/("""
				"""),format.raw/*1097.5*/("""let difB = parseFloat(cant) - parseFloat(acum);
				$("#estado_"+id_equipo+"_"+id_cotizacion+"_"+auxIndex).val(formatStandar(difB,2));
				
				let aux = $("#estados_"+id_equipo+"_"+id_cotizacion).val();
				let niv1 = aux.split(";");
				let flag = true;
				let buscar = "";
				for(i=0; i<niv1.length && flag; i++)"""),format.raw/*1104.41*/("""{"""),format.raw/*1104.42*/("""
					"""),format.raw/*1105.6*/("""let niv2 = niv1[i].split(":");
					if(niv2[0] == auxBueno)"""),format.raw/*1106.29*/("""{"""),format.raw/*1106.30*/("""
						"""),format.raw/*1107.7*/("""buscar = auxBueno+":"+niv2[1]+";";
						flag = false;
					"""),format.raw/*1109.6*/("""}"""),format.raw/*1109.7*/("""
				"""),format.raw/*1110.5*/("""}"""),format.raw/*1110.6*/("""
				
				"""),format.raw/*1112.5*/("""if(flag)"""),format.raw/*1112.13*/("""{"""),format.raw/*1112.14*/("""
					"""),format.raw/*1113.6*/("""aux += auxBueno+":"+parseFloat(difB)+";";
					$("#estados_"+id_equipo+"_"+id_cotizacion).val(aux);
				"""),format.raw/*1115.5*/("""}"""),format.raw/*1115.6*/("""else"""),format.raw/*1115.10*/("""{"""),format.raw/*1115.11*/("""
					"""),format.raw/*1116.6*/("""aux = aux.replace(buscar,auxBueno+":"+parseFloat(difB)+";");
					$("#estados_"+id_equipo+"_"+id_cotizacion).val(aux);
				"""),format.raw/*1118.5*/("""}"""),format.raw/*1118.6*/("""
				
			"""),format.raw/*1120.4*/("""}"""),format.raw/*1120.5*/("""else if('"""),_display_(/*1120.15*/mapDiccionario/*1120.29*/.get("nEmpresa")),format.raw/*1120.45*/("""' == "HOHE" && ( auxBueno == 2 || auxBueno == 3 ) )"""),format.raw/*1120.96*/("""{"""),format.raw/*1120.97*/("""
				"""),format.raw/*1121.5*/("""let difB = parseFloat(cant) - parseFloat(acum);
				$("#estado_"+id_equipo+"_"+id_cotizacion+"_"+auxIndex).val(formatStandar(difB,2));
				
				let aux = $("#estados_"+id_equipo+"_"+id_cotizacion).val();
				let niv1 = aux.split(";");
				let flag = true;
				let buscar = "";
				for(i=0; i<niv1.length && flag; i++)"""),format.raw/*1128.41*/("""{"""),format.raw/*1128.42*/("""
					"""),format.raw/*1129.6*/("""let niv2 = niv1[i].split(":");
					if(niv2[0] == auxBueno)"""),format.raw/*1130.29*/("""{"""),format.raw/*1130.30*/("""
						"""),format.raw/*1131.7*/("""buscar = auxBueno+":"+niv2[1]+";";
						flag = false;
					"""),format.raw/*1133.6*/("""}"""),format.raw/*1133.7*/("""
				"""),format.raw/*1134.5*/("""}"""),format.raw/*1134.6*/("""
				
				"""),format.raw/*1136.5*/("""if(flag)"""),format.raw/*1136.13*/("""{"""),format.raw/*1136.14*/("""
					"""),format.raw/*1137.6*/("""aux += auxBueno+":"+parseFloat(difB)+";";
					$("#estados_"+id_equipo+"_"+id_cotizacion).val(aux);
				"""),format.raw/*1139.5*/("""}"""),format.raw/*1139.6*/("""else"""),format.raw/*1139.10*/("""{"""),format.raw/*1139.11*/("""
					"""),format.raw/*1140.6*/("""aux = aux.replace(buscar,auxBueno+":"+parseFloat(difB)+";");
					$("#estados_"+id_equipo+"_"+id_cotizacion).val(aux);
				"""),format.raw/*1142.5*/("""}"""),format.raw/*1142.6*/("""
			"""),format.raw/*1143.4*/("""}"""),format.raw/*1143.5*/("""
			
			"""),format.raw/*1145.4*/("""let aux = $("#estados_"+id_equipo+"_"+id_cotizacion).val();
			let niv1 = aux.split(";");
			let flag = true;
			let buscar = "";
			for(i=0; i<niv1.length && flag; i++)"""),format.raw/*1149.40*/("""{"""),format.raw/*1149.41*/("""
				"""),format.raw/*1150.5*/("""let niv2 = niv1[i].split(":");
				if(niv2[0]==id_tipoEstado)"""),format.raw/*1151.31*/("""{"""),format.raw/*1151.32*/("""
					"""),format.raw/*1152.6*/("""buscar = id_tipoEstado+":"+niv2[1]+";";
					flag = false;
				"""),format.raw/*1154.5*/("""}"""),format.raw/*1154.6*/("""
			"""),format.raw/*1155.4*/("""}"""),format.raw/*1155.5*/("""
			"""),format.raw/*1156.4*/("""if(flag)"""),format.raw/*1156.12*/("""{"""),format.raw/*1156.13*/("""
				"""),format.raw/*1157.5*/("""aux += id_tipoEstado+":"+parseFloat(cantEstado)+";";
				$("#estados_"+id_equipo+"_"+id_cotizacion).val(aux);
			"""),format.raw/*1159.4*/("""}"""),format.raw/*1159.5*/("""else"""),format.raw/*1159.9*/("""{"""),format.raw/*1159.10*/("""
				"""),format.raw/*1160.5*/("""aux = aux.replace(buscar,id_tipoEstado+":"+parseFloat(cantEstado)+";");
				$("#estados_"+id_equipo+"_"+id_cotizacion).val(aux);
			"""),format.raw/*1162.4*/("""}"""),format.raw/*1162.5*/("""
			"""),format.raw/*1163.4*/("""if(esReparable==1 && cantEstado>0)"""),format.raw/*1163.38*/("""{"""),format.raw/*1163.39*/("""
				
			    """),format.raw/*1165.8*/("""let modalReparaciones="<div id='reparaciones' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>"+
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
				    for(i=0;i<listReparaNiv1.length;i++)"""),format.raw/*1179.45*/("""{"""),format.raw/*1179.46*/("""
				    	"""),format.raw/*1180.10*/("""let listReparaNiv2 = listReparaNiv1[i].split(":&&:");
				    	if(listReparaNiv2[1]==id_tipoEstado)"""),format.raw/*1181.46*/("""{"""),format.raw/*1181.47*/("""
				    		"""),format.raw/*1182.11*/("""//  0=getId()   1=id_tipoEstado   2=getSigla() 3=getNombre()   4=getMoneda()   5=getPrecio()
				    		tablaReparaciones += "<tr onclick=\"actualizaReparacion("+id_equipo+","+id_cotizacion+","+id_tipoEstado+","+listReparaNiv2[0]+"); $('#modalTablaReparaciones').modal('hide');\">"+
				    			"<td>"+listReparaNiv2[2]+"</td><td>"+listReparaNiv2[3]+"</td><td style='text-align: center'>"+listReparaNiv2[4]+"</td><td style='text-align: right'>"+listReparaNiv2[5]+"</td>"+
				    			"</tr>";
				    	"""),format.raw/*1186.10*/("""}"""),format.raw/*1186.11*/("""
				    """),format.raw/*1187.9*/("""}"""),format.raw/*1187.10*/("""
				    """),format.raw/*1188.9*/("""tablaReparaciones += "</tbody></table>"
			    
			     
			    document.getElementById("tablaReparaciones").innerHTML = tablaReparaciones;
			    
			     $('#tblTablaReparaciones').DataTable("""),format.raw/*1193.46*/("""{"""),format.raw/*1193.47*/("""
				    	"""),format.raw/*1194.10*/(""""fixedHeader": true,
				    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
				    	"order": [[ 2, "asc" ]],
				    	"language": """),format.raw/*1197.22*/("""{"""),format.raw/*1197.23*/("""
				        	"""),format.raw/*1198.14*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
				        """),format.raw/*1199.13*/("""}"""),format.raw/*1199.14*/("""
				  """),format.raw/*1200.7*/("""}"""),format.raw/*1200.8*/(""" """),format.raw/*1200.9*/(""");
											    
			    
			    document.getElementById("selListaReparaciones").innerHTML = "<input type=\"text\" class=\"form-control\" onclick = \"$('#modalTablaReparaciones').modal('show');\" readonly value=\"select\">";
			    
			    
			    let repar = $("#reparaciones_"+id_equipo+"_"+id_cotizacion).val();
			    let reparNiv1 = repar.split(";");
			    for(let i=0; i<reparNiv1.length; i++)"""),format.raw/*1208.45*/("""{"""),format.raw/*1208.46*/("""
			    	"""),format.raw/*1209.9*/("""let reparNiv2 = reparNiv1[i].split(":");
			    	if(reparNiv2[0]==id_tipoEstado)"""),format.raw/*1210.40*/("""{"""),format.raw/*1210.41*/("""
			    		"""),format.raw/*1211.10*/("""repar = repar.replace(reparNiv1[i]+";","");
			    	"""),format.raw/*1212.9*/("""}"""),format.raw/*1212.10*/(""" 
			    """),format.raw/*1213.8*/("""}"""),format.raw/*1213.9*/("""
			    
			    """),format.raw/*1215.8*/("""$("#reparaciones_"+id_equipo+"_"+id_cotizacion).val(repar);
				$("#reparaciones").modal("show");
			"""),format.raw/*1217.4*/("""}"""),format.raw/*1217.5*/("""
			"""),format.raw/*1218.4*/("""$("#estado_"+id_equipo+"_"+id_cotizacion+"_"+index).val(formatStandar(cantEstado,2));
		"""),format.raw/*1219.3*/("""}"""),format.raw/*1219.4*/("""
	"""),format.raw/*1220.2*/("""}"""),format.raw/*1220.3*/("""
	
	"""),format.raw/*1222.2*/("""const editaReparaciones = (index, largo, id_equipo, id_cotizacion, cantEstado, esReparable, id_tipoEstado) =>"""),format.raw/*1222.111*/("""{"""),format.raw/*1222.112*/("""
		"""),format.raw/*1223.3*/("""let modalReparaciones="<div id='reparaciones' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>"+
			"<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'><div class='modal-content'><div class='modal-header'>"+
			"<h5 class='modal-title'>SELECCIONAR REPARACIONES</h5><button type='button' class='close' data-dismiss='modal' aria-label='Close'>"+
	        "<span aria-hidden='true'>&times;</span></button></div><div class='modal-body'>"+
			"<table id='tablaListaReparaciones' class='table table-sm table-bordered table-condensed table-hover table-fluid'>"+
			"<thead style='background-color: #eeeeee'><TR><TH>SIGLA</TH><TH>REPARACION</TH><TH>MONEDA</TH><TH>PRECIO</TH><TH>CANTIDAD</TH><TH>DEL</TH></TR></thead>"+
			"<tbody>";
			let repar = $("#reparaciones_"+id_equipo+"_"+id_cotizacion).val();
			let reparNiv1 = repar.split(";");
			for(let j=0; j<reparNiv1.length; j++)"""),format.raw/*1232.41*/("""{"""),format.raw/*1232.42*/("""
				"""),format.raw/*1233.5*/("""let reparNiv2 = reparNiv1[j].split(":");
				if(reparNiv2.length>0 && reparNiv2[0]==id_tipoEstado)"""),format.raw/*1234.58*/("""{"""),format.raw/*1234.59*/("""
					"""),format.raw/*1235.6*/("""for(i=0;i<listReparaNiv1.length;i++)"""),format.raw/*1235.42*/("""{"""),format.raw/*1235.43*/("""
				    	"""),format.raw/*1236.10*/("""let listReparaNiv2 = listReparaNiv1[i].split(":&&:");
				    	if(listReparaNiv2[1]==id_tipoEstado)"""),format.raw/*1237.46*/("""{"""),format.raw/*1237.47*/("""
				    		"""),format.raw/*1238.11*/("""//  0=getId()   1=id_tipoEstado   2=getSigla() 3=getNombre()   4=getMoneda()   5=getPrecio()
				    		if(listReparaNiv2[0]==reparNiv2[1])"""),format.raw/*1239.46*/("""{"""),format.raw/*1239.47*/("""
				    			"""),format.raw/*1240.12*/("""modalReparaciones += "<tr>"+
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
				    		"""),format.raw/*1256.11*/("""}"""),format.raw/*1256.12*/("""
				    	"""),format.raw/*1257.10*/("""}"""),format.raw/*1257.11*/("""
				    """),format.raw/*1258.9*/("""}"""),format.raw/*1258.10*/("""
				"""),format.raw/*1259.5*/("""}"""),format.raw/*1259.6*/("""
			"""),format.raw/*1260.4*/("""}"""),format.raw/*1260.5*/("""
			"""),format.raw/*1261.4*/("""modalReparaciones += "<TR><td  colspan='6'><div class='row justify-content-md-left'><div class='col-xs-12 col-sm-6 col-md-6 col-lg-6'><div id='selListaReparaciones'>"+
				"</div></div></div></TR></tbody></table><div class='row'><div class='col-sm-12' style='text-align:center'>"+
				"<button type='button' class='btn btn-sm  btn-warning' style='font-size: 10px;' data-dismiss='modal'>Listo</button></div></div></div></div></div></div>";
			
			let tablaReparaciones = "<table id='tblTablaReparaciones' class=\"table table-sm table-hover table-bordered table-condensed table-fluid\">"+
						"<thead style=\"background-color: #eeeeee\"><th>SIGLA</th><th>NOMBRE</th><th>MONEDA</th><th>PRECIO</th></thead>"+
						"<tbody>";
			    for(i=0;i<listReparaNiv1.length;i++)"""),format.raw/*1268.44*/("""{"""),format.raw/*1268.45*/("""
			    	"""),format.raw/*1269.9*/("""let listReparaNiv2 = listReparaNiv1[i].split(":&&:");
			    	if(listReparaNiv2[1]==id_tipoEstado)"""),format.raw/*1270.45*/("""{"""),format.raw/*1270.46*/("""
			    		"""),format.raw/*1271.10*/("""//  0=getId()   1=id_tipoEstado   2=getSigla() 3=getNombre()   4=getMoneda()   5=getPrecio()
			    		tablaReparaciones += "<tr onclick=\"actualizaReparacion("+id_equipo+","+id_cotizacion+","+id_tipoEstado+","+listReparaNiv2[0]+"); $('#modalTablaReparaciones').modal('hide');\">"+
			    			"<td>"+listReparaNiv2[2]+"</td><td>"+listReparaNiv2[3]+"</td><td style='text-align: center'>"+listReparaNiv2[4]+"</td><td style='text-align: right'>"+listReparaNiv2[5]+"</td>"+
			    			"</tr>";
			    	"""),format.raw/*1275.9*/("""}"""),format.raw/*1275.10*/("""
			    """),format.raw/*1276.8*/("""}"""),format.raw/*1276.9*/("""
			    """),format.raw/*1277.8*/("""tablaReparaciones += "</tbody></table>"
		    
		     
		    document.getElementById("tablaReparaciones").innerHTML = tablaReparaciones;
		    
		    
		//    document.getElementById("selListaReparaciones").innerHTML = lista;
		    $("#reparaciones").modal("show");
	"""),format.raw/*1285.2*/("""}"""),format.raw/*1285.3*/("""

	"""),format.raw/*1287.2*/("""const actualizaReparacion = (id_equipo, id_cotizacion, id_tipoEstado, id_reparacion) =>"""),format.raw/*1287.89*/("""{"""),format.raw/*1287.90*/("""
		"""),format.raw/*1288.3*/("""if(id_reparacion > 0)"""),format.raw/*1288.24*/("""{"""),format.raw/*1288.25*/("""
			"""),format.raw/*1289.4*/("""for(i=0;i<listReparaNiv1.length;i++)"""),format.raw/*1289.40*/("""{"""),format.raw/*1289.41*/("""
		    	"""),format.raw/*1290.8*/("""let listReparaNiv2 = listReparaNiv1[i].split(":&&:");
		    	if(listReparaNiv2[0]==id_reparacion)"""),format.raw/*1291.44*/("""{"""),format.raw/*1291.45*/("""
		    		"""),format.raw/*1292.9*/("""//  0=getId()   1=id_tipoEstado   2=getSigla() 3=getNombre()   4=getMoneda()   5=getPrecio()
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
		    	"""),format.raw/*1325.8*/("""}"""),format.raw/*1325.9*/("""
		    """),format.raw/*1326.7*/("""}"""),format.raw/*1326.8*/("""
		"""),format.raw/*1327.3*/("""}"""),format.raw/*1327.4*/("""
	"""),format.raw/*1328.2*/("""}"""),format.raw/*1328.3*/("""
	"""),format.raw/*1329.2*/("""const modificaReparacion = (id_equipo, id_cotizacion, id_tipoEstado, id_reparacion, cantReparacion) =>"""),format.raw/*1329.104*/("""{"""),format.raw/*1329.105*/("""
		"""),format.raw/*1330.3*/("""let repar = $("#reparaciones_"+id_equipo+"_"+id_cotizacion).val();
		let reparNiv1 = repar.split(";");
	    for(let i=0; i<reparNiv1.length; i++)"""),format.raw/*1332.43*/("""{"""),format.raw/*1332.44*/("""
	    	"""),format.raw/*1333.7*/("""let reparNiv2 = reparNiv1[i].split(":");
	    	if(reparNiv2[0]==id_tipoEstado && reparNiv2[1]==id_reparacion)"""),format.raw/*1334.69*/("""{"""),format.raw/*1334.70*/("""
	    		"""),format.raw/*1335.8*/("""let nuevo = reparNiv2[0]+":"+reparNiv2[1]+":"+cantReparacion+";";
	    		repar = repar.replace(reparNiv1[i]+";",nuevo);
	    	"""),format.raw/*1337.7*/("""}"""),format.raw/*1337.8*/(""" 
	    """),format.raw/*1338.6*/("""}"""),format.raw/*1338.7*/("""
	    """),format.raw/*1339.6*/("""$("#reparaciones_"+id_equipo+"_"+id_cotizacion).val(repar);
	"""),format.raw/*1340.2*/("""}"""),format.raw/*1340.3*/("""
	
	"""),format.raw/*1342.2*/("""const eliminaReparacion = (nodo, id_equipo, id_cotizacion, id_tipoEstado, id_reparacion) =>"""),format.raw/*1342.93*/("""{"""),format.raw/*1342.94*/("""
		"""),format.raw/*1343.3*/("""let nodoTd = nodo.parentNode.parentNode; 
		$(nodoTd).remove();
		let repar = $("#reparaciones_"+id_equipo+"_"+id_cotizacion).val();
		let reparNiv1 = repar.split(";");
	    for(let i=0; i<reparNiv1.length; i++)"""),format.raw/*1347.43*/("""{"""),format.raw/*1347.44*/("""
	    	"""),format.raw/*1348.7*/("""let reparNiv2 = reparNiv1[i].split(":");
	    	if(reparNiv2[0]==id_tipoEstado && reparNiv2[1]==id_reparacion)"""),format.raw/*1349.69*/("""{"""),format.raw/*1349.70*/("""
	    		"""),format.raw/*1350.8*/("""repar = repar.replace(reparNiv1[i]+";","");
	    	"""),format.raw/*1351.7*/("""}"""),format.raw/*1351.8*/(""" 
	    """),format.raw/*1352.6*/("""}"""),format.raw/*1352.7*/("""
	    """),format.raw/*1353.6*/("""$("#reparaciones_"+id_equipo+"_"+id_cotizacion).val(repar);
	"""),format.raw/*1354.2*/("""}"""),format.raw/*1354.3*/("""
	
	"""),format.raw/*1356.2*/("""const verificaMovimientos = () =>"""),format.raw/*1356.35*/("""{"""),format.raw/*1356.36*/("""
		
		"""),format.raw/*1358.3*/("""if(""""),_display_(/*1358.8*/bodegaOrigen/*1358.20*/.esInterna),format.raw/*1358.30*/("""" != "1")"""),format.raw/*1358.39*/("""{"""),format.raw/*1358.40*/("""
			
			"""),format.raw/*1360.4*/("""if(""""),_display_(/*1360.9*/sinVenta),format.raw/*1360.17*/("""" == "1")"""),format.raw/*1360.26*/("""{"""),format.raw/*1360.27*/("""
				"""),format.raw/*1361.5*/("""$('#tablaPrincipal').dataTable().fnAddData( [
					"TOTALES","","","","","","","","","",
					"<div id='totalCant'>1.00</div>","<div id='totalExce'>0.00</div>","<div id='totalKg'>0.00</div>","<div id='totalM2'>0.00</div>",
						"""),_display_(if(bodegaOrigen.esInterna!=1)/*1364.37*/{_display_(Seq[Any](format.raw/*1364.38*/("""
							"""),format.raw/*1365.8*/(""""",
							"""),_display_(/*1366.9*/for((est,index) <- listTipoEstado.zipWithIndex) yield /*1366.56*/{_display_(Seq[Any](format.raw/*1366.57*/("""
									"""),format.raw/*1367.10*/(""""",
							""")))}),format.raw/*1368.9*/("""
							"""),_display_(if(mapPermiso.get("parametro.movimiento-devolucion-cantCliente")!=null && mapPermiso.get("parametro.movimiento-devolucion-cantCliente").equals("1"))/*1369.157*/{_display_(Seq[Any](format.raw/*1369.158*/("""
								"""),format.raw/*1370.9*/(""""<div id='totalCantCliente'>0.00</div>",
								"<div id='totalDifCliente'>0.00</div>",
							""")))} else {null} ),format.raw/*1372.9*/("""
						""")))} else {null} ),format.raw/*1373.8*/("""
						"""),format.raw/*1374.7*/(""""","",
				] );
			"""),format.raw/*1376.4*/("""}"""),format.raw/*1376.5*/("""else"""),format.raw/*1376.9*/("""{"""),format.raw/*1376.10*/("""
				"""),format.raw/*1377.5*/("""$('#tablaPrincipal').dataTable().fnAddData( [
					"TOTALES","","","","","","","","","",
					"<div id='totalCant'>1.00</div>","","<div id='totalExce'>0.00</div>","<div id='totalKg'>0.00</div>","<div id='totalM2'>0.00</div>",
						"""),_display_(if(bodegaOrigen.esInterna!=1)/*1380.37*/{_display_(Seq[Any](format.raw/*1380.38*/("""
							"""),format.raw/*1381.8*/(""""",
							"""),_display_(/*1382.9*/for((est,index) <- listTipoEstado.zipWithIndex) yield /*1382.56*/{_display_(Seq[Any](format.raw/*1382.57*/("""
									"""),format.raw/*1383.10*/(""""",
							""")))}),format.raw/*1384.9*/("""
							"""),_display_(if(mapPermiso.get("parametro.movimiento-devolucion-cantCliente")!=null && mapPermiso.get("parametro.movimiento-devolucion-cantCliente").equals("1"))/*1385.157*/{_display_(Seq[Any](format.raw/*1385.158*/("""
								"""),format.raw/*1386.9*/(""""<div id='totalCantCliente'>0.00</div>",
								"<div id='totalDifCliente'>0.00</div>",
							""")))} else {null} ),format.raw/*1388.9*/("""
						""")))} else {null} ),format.raw/*1389.8*/("""
						"""),format.raw/*1390.7*/(""""","",
				] );
			"""),format.raw/*1392.4*/("""}"""),format.raw/*1392.5*/("""
			
			
		"""),format.raw/*1395.3*/("""}"""),format.raw/*1395.4*/("""else"""),format.raw/*1395.8*/("""{"""),format.raw/*1395.9*/("""
			"""),format.raw/*1396.4*/("""$('#tablaPrincipal').dataTable().fnAddData( [
				"TOTALES","","","","","","","","","",
				"<div id='totalCant'>1.00</div>","","","<div id='totalKg'>0.00</div>","<div id='totalM2'>0.00</div>",
					"""),_display_(if(bodegaOrigen.esInterna!=1)/*1399.36*/{_display_(Seq[Any](format.raw/*1399.37*/("""
						"""),format.raw/*1400.7*/(""""",
						"""),_display_(/*1401.8*/for((est,index) <- listTipoEstado.zipWithIndex) yield /*1401.55*/{_display_(Seq[Any](format.raw/*1401.56*/("""
								"""),format.raw/*1402.9*/(""""",
						""")))}),format.raw/*1403.8*/("""
						"""),_display_(if(mapPermiso.get("parametro.movimiento-devolucion-cantCliente")!=null && mapPermiso.get("parametro.movimiento-devolucion-cantCliente").equals("1"))/*1404.156*/{_display_(Seq[Any](format.raw/*1404.157*/("""
							"""),format.raw/*1405.8*/(""""<div id='totalCantCliente'>0.00</div>",
							"<div id='totalDifCliente'>0.00</div>",
						""")))} else {null} ),format.raw/*1407.8*/("""
					""")))} else {null} ),format.raw/*1408.7*/("""
					"""),format.raw/*1409.6*/(""""","",
			] );
		"""),format.raw/*1411.3*/("""}"""),format.raw/*1411.4*/("""
		
		
		
		"""),format.raw/*1415.3*/("""$("#tablaPrincipal").dataTable().fnDestroy();
		let tabla = document.getElementById("tablaPrincipal");
		let totCant = 0;
		let totExce = 0;
		let totKg = 0;
		let totM2 = 0;
		
		let totCantCliente = 0;
		let totDifCliente = 0;
		
		for(i=1; i<tabla.rows.length-1; i++)"""),format.raw/*1425.39*/("""{"""),format.raw/*1425.40*/("""
			"""),format.raw/*1426.4*/("""let cantidad = $(".cantidad",tabla.rows[i].cells[10]).val();
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
			
			if(esInterna != "1")"""),format.raw/*1440.24*/("""{"""),format.raw/*1440.25*/("""
				"""),_display_(if(mapPermiso.get("parametro.movimiento-devolucion-cantCliente")!=null && mapPermiso.get("parametro.movimiento-devolucion-cantCliente").equals("1"))/*1441.154*/{_display_(Seq[Any](format.raw/*1441.155*/("""
					"""),format.raw/*1442.6*/("""let cantCliente = $(".cantCliente",tabla.rows[i].cells[tabla.rows[i].cells.length-2]).val();
					let difCliente = $(".difCliente",tabla.rows[i].cells[tabla.rows[i].cells.length-1]).text();
					
					cantCliente = cantCliente.replace(/,/g,'');
					difCliente = difCliente.replace(/,/g,'');
					
					totCantCliente += parseFloat(cantCliente);
					totDifCliente += parseFloat(difCliente);
				""")))} else {null} ),format.raw/*1450.6*/("""
			"""),format.raw/*1451.4*/("""}"""),format.raw/*1451.5*/("""
			
			"""),format.raw/*1453.4*/("""if(total <= 0 )"""),format.raw/*1453.19*/("""{"""),format.raw/*1453.20*/("""
				"""),format.raw/*1454.5*/("""tabla.rows[i].style.display = 'none';
			"""),format.raw/*1455.4*/("""}"""),format.raw/*1455.5*/("""
		"""),format.raw/*1456.3*/("""}"""),format.raw/*1456.4*/("""
		"""),format.raw/*1457.3*/("""$("#totalCant").text(formatStandar(totCant,2));
		$("#totalExce").text(formatStandar(totExce,2));
		$("#totalKg").text(formatStandar(totKg,2));
		$("#totalM2").text(formatStandar(totM2,2));
		
		if(esInterna != "1")"""),format.raw/*1462.23*/("""{"""),format.raw/*1462.24*/("""
			"""),_display_(if(mapPermiso.get("parametro.movimiento-devolucion-cantCliente")!=null && mapPermiso.get("parametro.movimiento-devolucion-cantCliente").equals("1"))/*1463.153*/{_display_(Seq[Any](format.raw/*1463.154*/("""
				"""),format.raw/*1464.5*/("""$("#totalCantCliente").text(formatStandar(totCantCliente,2));
				$("#totalDifCliente").text(formatStandar(totDifCliente,2));
			""")))} else {null} ),format.raw/*1466.5*/("""
		"""),format.raw/*1467.3*/("""}"""),format.raw/*1467.4*/("""
		
		"""),format.raw/*1469.3*/("""document.getElementById('verifica').style.visibility = 'hidden';
		document.getElementById('modifica').style.visibility = 'visible';
		document.getElementById('aplica').style.visibility = 'visible';
		if('"""),_display_(/*1472.8*/bodegaOrigen/*1472.20*/.esInterna),format.raw/*1472.30*/("""'=='2') document.getElementById('agregarEquiposNoEnviados').style.visibility = 'hidden';
		
		
		$('#tablaPrincipal').DataTable("""),format.raw/*1475.34*/("""{"""),format.raw/*1475.35*/("""
	    	"""),format.raw/*1476.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[-1, 15, 50, 100, 200], ["ALL", 15, 50, 100, 200]],
	    	"order": [[ 4, "asc" ]],
	    	"language": """),format.raw/*1479.19*/("""{"""),format.raw/*1479.20*/("""
	        	"""),format.raw/*1480.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*1481.10*/("""}"""),format.raw/*1481.11*/("""
	  	"""),format.raw/*1482.5*/("""}"""),format.raw/*1482.6*/(""" """),format.raw/*1482.7*/(""");

	"""),format.raw/*1484.2*/("""}"""),format.raw/*1484.3*/("""
	
	"""),format.raw/*1486.2*/("""const modificaMovimientos = () =>"""),format.raw/*1486.35*/("""{"""),format.raw/*1486.36*/("""
		"""),format.raw/*1487.3*/("""$("#tablaPrincipal").dataTable().fnDestroy();
		let tabla = document.getElementById("tablaPrincipal");
		tabla.deleteRow(tabla.rows.length-1);
		for(i=1; i<tabla.rows.length; i++)"""),format.raw/*1490.37*/("""{"""),format.raw/*1490.38*/("""
			"""),format.raw/*1491.4*/("""tabla.rows[i].style.display = '';
		"""),format.raw/*1492.3*/("""}"""),format.raw/*1492.4*/("""
		"""),format.raw/*1493.3*/("""tblPrincipal = $('#tablaPrincipal').DataTable("""),format.raw/*1493.49*/("""{"""),format.raw/*1493.50*/("""
	    	"""),format.raw/*1494.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[15, 50, 100, 200, -1], [15, 50, 100, 200, "All"]],
	    	"order": [[ 4, "asc" ]],
	    	"language": """),format.raw/*1497.19*/("""{"""),format.raw/*1497.20*/("""
	        	"""),format.raw/*1498.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*1499.10*/("""}"""),format.raw/*1499.11*/("""
	  	"""),format.raw/*1500.5*/("""}"""),format.raw/*1500.6*/(""" """),format.raw/*1500.7*/(""");
		document.getElementById('verifica').style.visibility = 'visible';
		document.getElementById('modifica').style.visibility = 'hidden';
		document.getElementById('aplica').style.visibility = 'hidden';
		if('"""),_display_(/*1504.8*/bodegaOrigen/*1504.20*/.esInterna),format.raw/*1504.30*/("""'=='2') document.getElementById('agregarEquiposNoEnviados').style.visibility = 'visible';
	"""),format.raw/*1505.2*/("""}"""),format.raw/*1505.3*/("""

	"""),format.raw/*1507.2*/("""let extArray = new Array(".gif", ".jpg", ".png", ".jpeg", ".pdf", ".xls", ".doc", ".xlsx", ".docx", ".tar", ".zip", ".rar");
	const LimitAttach = (form, file) => """),format.raw/*1508.38*/("""{"""),format.raw/*1508.39*/("""
		
		"""),format.raw/*1510.3*/("""if (!file) return;
		
		const $inputArchivos = document.querySelector("#docAdjunto");
		const archivosParaSubir = $inputArchivos.files;
		let tamanio = 0;
		let allowSubmit = false;
		
		for(let i=0; i<archivosParaSubir.length; i++)"""),format.raw/*1517.48*/("""{"""),format.raw/*1517.49*/("""
			"""),format.raw/*1518.4*/("""tamanio += archivosParaSubir[i].size;
			let nombre = archivosParaSubir[i].name;
			let extencion = nombre.substring(nombre.lastIndexOf(".")).toLowerCase();
			for (let j = 0; j < extArray.length; j++) """),format.raw/*1521.46*/("""{"""),format.raw/*1521.47*/("""
				"""),format.raw/*1522.5*/("""if (extArray[j] == extencion) """),format.raw/*1522.35*/("""{"""),format.raw/*1522.36*/(""" 
					"""),format.raw/*1523.6*/("""allowSubmit = true;
				"""),format.raw/*1524.5*/("""}"""),format.raw/*1524.6*/("""
			"""),format.raw/*1525.4*/("""}"""),format.raw/*1525.5*/("""
			"""),format.raw/*1526.4*/("""if(allowSubmit && i < archivosParaSubir.length -1)"""),format.raw/*1526.54*/("""{"""),format.raw/*1526.55*/("""
				"""),format.raw/*1527.5*/("""allowSubmit = false;
			"""),format.raw/*1528.4*/("""}"""),format.raw/*1528.5*/("""
		"""),format.raw/*1529.3*/("""}"""),format.raw/*1529.4*/("""
		
		"""),format.raw/*1531.3*/("""if (allowSubmit) """),format.raw/*1531.20*/("""{"""),format.raw/*1531.21*/("""
			"""),format.raw/*1532.4*/("""if(tamanio > 200000000)"""),format.raw/*1532.27*/("""{"""),format.raw/*1532.28*/("""
				"""),format.raw/*1533.5*/("""alert("Se permite máximo subir 200 MB,"
				+" lo que se intenta subir pesa: "+Math.round(tamanio/10000)/100+" MB");
				form.docAdjunto.value="";
			"""),format.raw/*1536.4*/("""}"""),format.raw/*1536.5*/("""else"""),format.raw/*1536.9*/("""{"""),format.raw/*1536.10*/("""
				"""),format.raw/*1537.5*/("""alert("Documento subido con éxito");
				$("#txtBtn").text("Cambiar Documento");
			"""),format.raw/*1539.4*/("""}"""),format.raw/*1539.5*/("""
		"""),format.raw/*1540.3*/("""}"""),format.raw/*1540.4*/("""else"""),format.raw/*1540.8*/("""{"""),format.raw/*1540.9*/("""
			"""),format.raw/*1541.4*/("""alert("Se permiten únicamente archivos con la extensión: "
			+ (extArray.join("  ")) + "\nPor favor, seleccione solo archivos con extensiones permitidas "
			+ "e intente de nuevo.");
			form.docAdjunto.value="";
		"""),format.raw/*1545.3*/("""}"""),format.raw/*1545.4*/("""
	"""),format.raw/*1546.2*/("""}"""),format.raw/*1546.3*/("""
	
	"""),format.raw/*1548.2*/("""let extArray2 = new Array(".xls", ".xlsx");
	function subirArchivo(form, nodo) """),format.raw/*1549.36*/("""{"""),format.raw/*1549.37*/("""
		"""),format.raw/*1550.3*/("""allowSubmit = false;
		if (!nodo.files[0])"""),format.raw/*1551.22*/("""{"""),format.raw/*1551.23*/("""
			"""),format.raw/*1552.4*/("""return;
		"""),format.raw/*1553.3*/("""}"""),format.raw/*1553.4*/("""
		
		"""),format.raw/*1555.3*/("""let nombre = nodo.files[0].name;
		while (nombre.indexOf("\\") != -1)"""),format.raw/*1556.37*/("""{"""),format.raw/*1556.38*/("""
			"""),format.raw/*1557.4*/("""nombre = nombre.slice(nombre.indexOf("\\") + 1);
		"""),format.raw/*1558.3*/("""}"""),format.raw/*1558.4*/("""

		"""),format.raw/*1560.3*/("""ext = nombre.slice(nombre.lastIndexOf(".")).toLowerCase();
		for (var i = 0; i < extArray2.length; i++) """),format.raw/*1561.46*/("""{"""),format.raw/*1561.47*/("""
			"""),format.raw/*1562.4*/("""if (extArray2[i] == ext) """),format.raw/*1562.29*/("""{"""),format.raw/*1562.30*/(""" """),format.raw/*1562.31*/("""allowSubmit = true; break; """),format.raw/*1562.58*/("""}"""),format.raw/*1562.59*/("""
		"""),format.raw/*1563.3*/("""}"""),format.raw/*1563.4*/("""
		"""),format.raw/*1564.3*/("""if (allowSubmit) """),format.raw/*1564.20*/("""{"""),format.raw/*1564.21*/("""
			"""),format.raw/*1565.4*/("""var formData = new FormData();
			formData.append("file",nodo.files[0]);
			formData.append("id_bodegaOrigen",'"""),_display_(/*1567.40*/bodegaOrigen/*1567.52*/.getId()),format.raw/*1567.60*/("""');
			$.ajax("""),format.raw/*1568.11*/("""{"""),format.raw/*1568.12*/("""
				"""),format.raw/*1569.5*/("""async: false,
				url: "/routes2/movValySubePlantilla/",
		        type: "POST",
		        method: "POST",
		        data: formData,
		        cache: false,
		        contentType: false,
		     	processData: false,
		     	success: function(rs)"""),format.raw/*1577.30*/("""{"""),format.raw/*1577.31*/("""
		     		"""),format.raw/*1578.10*/("""if(rs["status"])"""),format.raw/*1578.26*/("""{"""),format.raw/*1578.27*/("""
						"""),format.raw/*1579.7*/("""$("#tablaPrincipal").dataTable().fnDestroy();
						let codigos = document.querySelectorAll(".codigo");
						let cantidades = document.querySelectorAll(".cantidad");
						let esVenta0 = document.querySelectorAll(".esVenta0");
						let esVenta1 = document.querySelectorAll(".esVenta1");
						let verificaStock = document.querySelectorAll(".verificaStock");
						let mensajes = "";
						
						
						let kgs = document.querySelectorAll(".kgUnit");
						let m2s = document.querySelectorAll(".m2Unit");
						let KGs = document.querySelectorAll(".KG");
						let M2s = document.querySelectorAll(".M2");
						
						const mapCodAux = new Map();
						for (let i = 0; i < codigos.length; i++) """),format.raw/*1594.48*/("""{"""),format.raw/*1594.49*/("""
							"""),format.raw/*1595.8*/("""mapCodAux.set(codigos[i].textContent.toUpperCase(),codigos[i].textContent.toUpperCase());
						"""),format.raw/*1596.7*/("""}"""),format.raw/*1596.8*/("""
						
						
						
						
						"""),format.raw/*1601.7*/("""for (let i = 0; i < codigos.length; i++) """),format.raw/*1601.48*/("""{"""),format.raw/*1601.49*/("""
							"""),format.raw/*1602.8*/("""let aux = rs["map"][codigos[i].textContent.toUpperCase()];
							document.getElementById('btnCargaExcel').style.visibility = 'hidden';
							if(aux != null)"""),format.raw/*1604.23*/("""{"""),format.raw/*1604.24*/("""
								"""),format.raw/*1605.9*/("""let valores = aux;
								let codigo = valores[0].toUpperCase();
								let cant = valores[2].replace(/,/g,'');
								let stock = verificaStock[i].textContent.replace(/,/g,'');
								if(parseFloat(cant) > parseFloat(stock))"""),format.raw/*1609.49*/("""{"""),format.raw/*1609.50*/("""
									"""),format.raw/*1610.10*/("""cantidades[i].value = formatStandar(stock,2);
									mensajes += "La cantidad en codigo "+codigo+" supera el stock, cantidad = "+formatStandar(cant,2)+" y stock = "+formatStandar(stock,2)+", se coloca el stock.<br>";
								"""),format.raw/*1612.9*/("""}"""),format.raw/*1612.10*/("""else"""),format.raw/*1612.14*/("""{"""),format.raw/*1612.15*/("""
									"""),format.raw/*1613.10*/("""cantidades[i].value = formatStandar(cant,2);
									let detStock = parseFloat(stock) - parseFloat(cant);
									verificaStock[i].textContent = formatStandar(detStock,2);
								"""),format.raw/*1616.9*/("""}"""),format.raw/*1616.10*/("""
								"""),format.raw/*1617.9*/("""esVenta0[i].value = valores[3];
								if(esVenta0[i].value == "1")"""),format.raw/*1618.37*/("""{"""),format.raw/*1618.38*/("""
									"""),format.raw/*1619.10*/("""esVenta1[i].checked = true;
								"""),format.raw/*1620.9*/("""}"""),format.raw/*1620.10*/("""
								
								"""),format.raw/*1622.9*/("""let kg = kgs[i].textContent.replace(/,/g,'');
								let m2 = m2s[i].textContent.replace(/,/g,'');
								
								KGs[i].textContent = formatStandar(parseFloat(cant)*parseFloat(kg),2);
								M2s[i].textContent = formatStandar(parseFloat(cant)*parseFloat(m2),2);
								
							"""),format.raw/*1628.8*/("""}"""),format.raw/*1628.9*/("""
						"""),format.raw/*1629.7*/("""}"""),format.raw/*1629.8*/("""
						"""),format.raw/*1630.7*/("""const mapCodReves = new Map(Object.entries(rs["map"]));
						
							let auxMsg = "";
							for (let [key, value] of mapCodReves) """),format.raw/*1633.46*/("""{"""),format.raw/*1633.47*/("""
								"""),format.raw/*1634.9*/("""aux = mapCodAux.get(`$"""),format.raw/*1634.31*/("""{"""),format.raw/*1634.32*/("""key"""),format.raw/*1634.35*/("""}"""),format.raw/*1634.36*/("""`);
								if(aux == null)"""),format.raw/*1635.24*/("""{"""),format.raw/*1635.25*/("""
									"""),format.raw/*1636.10*/("""auxMsg += key+", ";
								"""),format.raw/*1637.9*/("""}"""),format.raw/*1637.10*/("""
							"""),format.raw/*1638.8*/("""}"""),format.raw/*1638.9*/("""
							"""),format.raw/*1639.8*/("""if(auxMsg.length>0)"""),format.raw/*1639.27*/("""{"""),format.raw/*1639.28*/("""
								"""),format.raw/*1640.9*/("""mensajes += "<br>Los codigos: <br>"+auxMsg+"<br>no estan en el disponible, no se consideran.";
							"""),format.raw/*1641.8*/("""}"""),format.raw/*1641.9*/("""
							
						"""),format.raw/*1643.7*/("""verificaMovimientos();
						if(mensajes.length > 5)"""),format.raw/*1644.30*/("""{"""),format.raw/*1644.31*/("""
							"""),format.raw/*1645.8*/("""alertify.alert("ALERTA:<br> " + mensajes);
						"""),format.raw/*1646.7*/("""}"""),format.raw/*1646.8*/("""
		     		"""),format.raw/*1647.10*/("""}"""),format.raw/*1647.11*/("""else"""),format.raw/*1647.15*/("""{"""),format.raw/*1647.16*/("""
						"""),format.raw/*1648.7*/("""alertify.alert("ERROR EN ARCHIVO:<br> " + rs["msg"]);
		     		"""),format.raw/*1649.10*/("""}"""),format.raw/*1649.11*/("""
				"""),format.raw/*1650.5*/("""}"""),format.raw/*1650.6*/("""
			 """),format.raw/*1651.5*/("""}"""),format.raw/*1651.6*/(""");
		"""),format.raw/*1652.3*/("""}"""),format.raw/*1652.4*/("""else"""),format.raw/*1652.8*/("""{"""),format.raw/*1652.9*/("""
			"""),format.raw/*1653.4*/("""alertify.alert("Se permiten &uacute;nicamente archivos con la extensi&oacute;n: "
			+ (extArray2.join("  ")) + "\nPor favor, seleccione un archivo valido "
			+ "e intente de nuevo.");
			form.archivoXLSX.value="";
		"""),format.raw/*1657.3*/("""}"""),format.raw/*1657.4*/("""
		
	"""),format.raw/*1659.2*/("""}"""),format.raw/*1659.3*/("""
	
	"""),format.raw/*1661.2*/("""const validarForm2 = () =>"""),format.raw/*1661.28*/("""{"""),format.raw/*1661.29*/("""
		"""),format.raw/*1662.3*/("""$("#btnSubmit2").attr('disabled',true);
		return(true);
	"""),format.raw/*1664.2*/("""}"""),format.raw/*1664.3*/("""
	
	"""),format.raw/*1666.2*/("""const verOt = (id_ot) => """),format.raw/*1666.27*/("""{"""),format.raw/*1666.28*/("""
		"""),format.raw/*1667.3*/("""var formData = new FormData();
	  	formData.append('id_ot',id_ot);
        $.ajax("""),format.raw/*1669.16*/("""{"""),format.raw/*1669.17*/("""
            """),format.raw/*1670.13*/("""url: "/verOtAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*1677.36*/("""{"""),format.raw/*1677.37*/("""
	     		"""),format.raw/*1678.9*/("""document.getElementById('mostrarLaOt').innerHTML = respuesta;
	     		$('#modalVerOt').modal('show');
	     	"""),format.raw/*1680.8*/("""}"""),format.raw/*1680.9*/("""
        """),format.raw/*1681.9*/("""}"""),format.raw/*1681.10*/(""");
	"""),format.raw/*1682.2*/("""}"""),format.raw/*1682.3*/("""

	
"""),format.raw/*1685.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,bodegaOrigen:tables.BodegaEmpresa,listEquipBodOrigen:List[List[String]],bodegaDestino:tables.BodegaEmpresa,nuevoNumeroGuia:Long,hoy:String,listEquipNoEnBodOrigen:List[List[String]],listTipoEstado:List[tables.TipoEstado],listTipoReparacion:List[tables.TipoReparacion],listaTransporte:List[tables.Transportista],sucursal:tables.Sucursal,comercial:tables.Comercial,sinVenta:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,bodegaOrigen,listEquipBodOrigen,bodegaDestino,nuevoNumeroGuia,hoy,listEquipNoEnBodOrigen,listTipoEstado,listTipoReparacion,listaTransporte,sucursal,comercial,sinVenta)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,List[List[String]],tables.BodegaEmpresa,Long,String,List[List[String]],List[tables.TipoEstado],List[tables.TipoReparacion],List[tables.Transportista],tables.Sucursal,tables.Comercial,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,bodegaOrigen,listEquipBodOrigen,bodegaDestino,nuevoNumeroGuia,hoy,listEquipNoEnBodOrigen,listTipoEstado,listTipoReparacion,listaTransporte,sucursal,comercial,sinVenta) => apply(mapDiccionario,mapPermiso,userMnu,bodegaOrigen,listEquipBodOrigen,bodegaDestino,nuevoNumeroGuia,hoy,listEquipNoEnBodOrigen,listTipoEstado,listTipoReparacion,listaTransporte,sucursal,comercial,sinVenta)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuMovimientos/movimientoOrigenDestinoMultiple1.scala.html
                  HASH: 456d0fc10d979d470084baa943b1c63191e1c6b2
                  MATRIX: 1247->1|1840->501|1868->504|1884->512|1923->514|1953->518|2022->566|2052->569|2205->696|2250->720|2279->723|2320->743|2350->746|2607->977|2728->1076|2759->1080|3306->1600|3327->1612|3356->1620|3459->1696|3480->1708|3525->1732|3898->2078|3921->2091|3951->2099|4134->2255|4156->2268|4201->2292|4633->2696|4673->2697|4714->2710|4871->2840|4907->2855|4989->2918|5028->2919|5069->2932|5226->3062|5262->3077|5470->3254|5508->3264|6613->4341|6631->4349|6662->4357|6950->4617|6968->4625|7002->4637|7476->5083|7499->5096|7539->5114|7979->5526|7999->5536|8052->5567|8082->5569|8102->5579|8154->5608|8187->5611|8218->5612|8255->5620|8281->5623|8346->5658|8377->5659|8408->5660|8443->5665|8474->5666|8537->5699|8568->5700|8616->5720|8641->5723|9088->6142|9108->6152|9163->6185|9193->6187|9213->6197|9267->6228|9307->6239|9333->6242|9383->6264|9408->6267|9833->6664|9852->6673|9891->6690|10792->7563|10832->7564|10867->7571|11314->7974|11352->7984|11586->8190|11610->8204|11642->8214|11710->8254|11734->8268|11766->8278|12026->8510|12066->8511|12105->8521|12174->8570|12214->8571|12253->8581|12309->8605|12346->8614|12515->8755|12555->8756|12594->8766|12663->8807|12712->8839|12752->8840|12792->8851|12883->8914|12902->8923|12935->8934|12975->8945|13023->8961|13211->9120|13252->9121|13292->9132|13445->9240|13500->9250|13536->9258|13606->9301|13656->9334|13696->9335|13733->9344|13864->9447|13880->9453|13909->9460|14002->9525|14018->9531|14047->9538|14089->9552|14105->9558|14134->9565|14173->9575|14277->9651|14317->9652|14357->9663|14431->9717|14471->9718|14511->9729|14629->9819|14645->9825|14675->9833|14804->9934|14820->9940|14850->9948|14951->10017|14991->10028|15092->10101|15111->10110|15163->10140|15209->10158|15225->10164|15255->10172|15294->10182|15396->10256|15436->10257|15476->10268|15550->10322|15590->10323|15630->10334|15691->10367|15707->10373|15736->10380|15784->10396|15824->10407|15923->10478|15939->10484|15968->10491|16023->10517|16040->10523|16070->10530|16194->10626|16210->10632|16239->10639|16274->10645|16291->10651|16321->10658|16415->10724|16431->10730|16460->10737|16550->10799|16566->10805|16595->10812|16671->10860|16687->10866|16716->10873|16839->10968|16855->10974|16884->10981|16914->10983|16930->10989|16959->10996|16990->10999|17006->11005|17035->11012|17301->11250|17317->11256|17346->11263|17376->11265|17392->11271|17421->11278|17699->11528|17715->11534|17744->11541|17776->11545|17792->11551|17821->11558|17853->11562|17870->11568|17900->11575|17939->11585|17956->11591|17986->11598|18019->11602|18036->11608|18066->11615|18099->11619|18124->11633|18154->11640|18254->11712|18294->11713|18334->11724|18488->11850|18504->11856|18533->11863|18563->11865|18580->11871|18610->11878|18728->11968|18744->11974|18773->11981|18803->11983|18819->11989|18849->11996|18914->12033|18930->12039|18959->12046|18989->12048|19005->12054|19034->12061|19091->12090|19107->12096|19136->12103|19166->12105|19183->12111|19213->12118|19276->12161|19316->12162|19356->12173|19496->12285|19512->12291|19541->12298|19571->12300|19588->12306|19618->12313|19736->12403|19752->12409|19781->12416|19811->12418|19827->12424|19857->12431|19922->12468|19938->12474|19967->12481|19997->12483|20013->12489|20042->12496|20099->12525|20115->12531|20144->12538|20174->12540|20191->12546|20221->12553|20292->12592|20331->12602|20478->12721|20494->12727|20523->12734|20553->12736|20569->12742|20598->12749|20863->12986|20879->12992|20908->12999|20938->13001|20954->13007|20983->13014|21155->13158|21171->13164|21200->13171|21230->13173|21246->13179|21275->13186|21384->13267|21400->13273|21429->13280|21459->13282|21475->13288|21504->13295|21589->13352|21629->13353|21669->13364|21787->13454|21803->13460|21832->13467|21862->13469|21878->13475|21907->13482|22012->13559|22028->13565|22057->13572|22087->13574|22103->13580|22133->13587|22191->13617|22255->13664|22295->13665|22336->13677|22450->13763|22490->13764|22533->13778|22714->13931|22730->13937|22759->13944|22789->13946|22805->13952|22834->13959|22864->13961|22891->13966|23051->14098|23078->14103|23238->14242|23279->14243|23322->14257|23503->14410|23519->14416|23548->14423|23578->14425|23594->14431|23623->14438|23653->14440|23680->14445|23810->14547|23823->14550|23853->14558|23911->14588|23938->14593|24001->14636|24041->14637|24084->14651|24265->14804|24281->14810|24310->14817|24340->14819|24356->14825|24385->14832|24415->14834|24442->14839|24690->15059|24703->15062|24740->15077|24796->15105|24823->15110|24856->15114|24881->15128|24911->15135|24944->15139|24961->15145|24991->15152|25024->15156|25041->15162|25071->15169|25110->15179|25124->15182|25162->15197|25195->15201|25209->15204|25240->15212|25311->15254|25341->15255|25389->15274|25470->15327|25497->15332|25529->15336|25553->15350|25582->15357|25614->15361|25630->15367|25659->15374|25691->15378|25707->15384|25737->15391|25776->15401|25790->15404|25828->15419|25861->15423|25875->15426|25906->15434|25957->15456|25987->15457|26020->15461|26050->15462|26098->15481|26147->15502|26174->15507|26206->15511|26230->15525|26259->15532|26291->15536|26307->15542|26336->15549|26368->15553|26384->15559|26414->15566|26453->15576|26467->15579|26505->15594|26538->15598|26552->15601|26583->15609|26634->15631|26664->15632|26712->15648|26753->15660|26802->15677|26991->15837|27032->15838|27073->15850|27325->16074|27341->16080|27370->16087|27400->16089|27416->16095|27445->16102|27737->16366|27753->16372|27782->16379|27815->16384|27831->16390|27861->16397|28011->16519|28027->16525|28056->16532|28086->16534|28103->16540|28133->16547|28207->16576|28263->16587|28300->16596|28347->16611|28382->16618|28635->16843|28675->16844|28711->16852|28981->17078|29015->17084|31702->19744|31756->19781|31796->19782|31833->19791|31910->19840|31926->19846|31955->19853|31986->19856|32002->19862|32031->19869|32063->19873|32079->19879|32109->19886|32142->19890|32159->19896|32189->19903|32300->19986|32316->19992|32345->19999|32376->20002|32392->20008|32421->20015|32519->20085|32535->20091|32564->20098|32595->20101|32611->20107|32640->20114|32738->20184|32754->20190|32783->20197|32814->20200|32830->20206|32859->20213|32926->20248|32961->20255|34777->22043|34799->22055|34829->22063|35766->22972|35790->22986|35836->23010|36378->23525|36469->23594|36504->23598|36535->23601|36605->23642|36635->23643|36665->23644|36714->23664|36744->23665|36774->23667|36835->23699|36865->23700|36895->23701|36944->23721|36974->23722|37004->23724|37065->23756|37095->23757|37125->23758|37174->23778|37204->23779|37234->23781|37295->23813|37325->23814|37355->23815|37402->23833|37432->23834|37462->23836|37523->23868|37553->23869|37583->23870|37631->23889|37661->23890|37691->23892|37752->23924|37782->23925|37812->23926|37860->23945|37890->23946|37920->23948|37981->23980|38011->23981|38041->23982|38090->24002|38120->24003|38150->24005|38212->24038|38242->24039|38272->24040|38320->24059|38350->24060|38380->24062|38442->24095|38472->24096|38502->24097|38550->24116|38580->24117|38610->24119|38672->24152|38702->24153|38732->24154|38781->24174|38811->24175|38841->24177|38903->24210|38933->24211|38963->24212|39012->24232|39042->24233|39072->24235|39134->24268|39164->24269|39194->24270|39242->24289|39272->24290|39302->24292|39364->24325|39394->24326|39424->24327|39472->24346|39502->24347|39532->24349|39594->24382|39624->24383|39654->24384|39702->24403|39732->24404|39792->24436|39832->24437|39863->24440|39925->24473|39955->24474|39985->24475|40052->24513|40082->24514|40113->24518|40177->24565|40217->24566|40249->24570|40307->24600|40339->24610|40370->24612|40400->24613|40430->24614|40483->24638|40513->24639|40548->24643|40580->24647|40638->24677|40686->24703|40717->24705|40747->24706|40777->24707|40849->24749|40880->24750|40911->24753|40969->24783|41017->24809|41048->24811|41078->24812|41108->24813|41180->24855|41211->24856|41242->24859|41300->24889|41348->24915|41379->24917|41409->24918|41439->24919|41511->24961|41542->24962|41589->24965|41618->24966|41765->25085|41787->25097|41819->25107|41893->25152|41923->25153|41955->25157|42052->25226|42081->25227|42115->25233|42198->25287|42228->25288|42260->25292|42498->25502|42527->25503|42561->25509|42630->25549|42660->25550|42692->25554|42834->25667|42864->25668|42897->25673|43079->25826|43109->25827|43143->25833|43232->25894|43261->25895|43294->25899|43324->25900|43358->25906|43435->25955|43464->25956|43496->25960|43525->25961|43556->25964|43585->25965|43619->25971|43719->26042|43749->26043|43780->26046|43840->26077|43870->26078|43906->26086|44081->26232|44111->26233|44152->26245|44259->26323|44289->26324|44322->26329|44351->26330|44380->26331|44439->26363|44484->26391|44524->26392|44557->26397|44597->26409|44608->26410|44638->26418|44671->26423|44682->26424|44718->26438|44751->26443|44762->26444|44795->26455|44828->26460|44839->26461|44873->26473|44906->26478|44917->26479|44951->26491|44985->26496|44997->26497|45032->26509|45074->26520|45107->26525|45225->26614|45255->26615|45291->26623|45476->26779|45506->26780|45547->26792|45654->26870|45684->26871|45717->26876|45746->26877|45775->26878|45861->26935|45891->26936|45927->26944|46102->27090|46132->27091|46173->27103|46280->27181|46310->27182|46343->27187|46372->27188|46401->27189|46563->27322|46593->27323|46625->27327|46784->27457|46814->27458|46847->27463|46954->27541|46984->27542|47018->27548|47068->27570|47097->27571|47129->27575|47158->27576|47190->27580|47237->27598|47267->27599|47300->27604|47371->27647|47395->27661|47431->27675|47462->27678|47484->27690|47519->27702|47639->27794|47664->27808|47701->27822|47732->27823|47799->27860|47830->27861|47871->27873|47935->27908|47965->27909|47999->27915|48028->27916|48059->27919|48088->27920|48118->27922|48147->27923|48219->27966|48249->27967|48281->27971|48379->28041|48401->28053|48431->28061|48503->28105|48526->28118|48556->28126|48596->28139|48615->28148|48645->28156|48684->28166|48714->28167|48747->28172|48783->28179|48813->28180|48847->28186|49067->28377|49097->28378|49132->28385|49201->28426|49230->28427|49263->28432|49292->28433|49326->28439|49355->28440|49388->28445|49424->28452|49454->28453|49487->28458|49702->28644|49732->28645|49766->28651|49832->28689|49861->28690|49893->28694|49922->28695|49956->28701|49985->28702|50017->28706|50084->28744|50114->28745|50145->28748|50194->28768|50224->28769|50256->28773|50307->28796|50344->28811|50378->28817|50407->28818|50439->28822|50468->28823|50500->28827|50540->28839|50577->28854|50701->28949|50731->28950|50774->28964|51045->29206|51075->29207|51114->29217|51166->29240|51196->29241|51236->29252|51347->29334|51377->29335|51418->29347|51469->29370|51506->29385|51549->29399|51579->29400|51620->29412|51650->29413|51689->29423|51740->29445|51770->29446|51810->29457|51876->29494|51906->29495|51947->29507|52007->29538|52037->29539|52078->29551|52108->29552|52145->29561|52175->29562|52214->29572|52244->29573|52277->29578|52306->29579|52336->29581|52365->29582|52397->29586|52451->29611|52481->29612|52512->29615|52634->29708|52664->29709|52696->29713|52787->29776|52811->29790|52847->29804|52920->29847|52951->29848|52984->29853|53282->30123|53311->30124|53344->30129|53373->30130|53405->30134|53434->30135|53466->30139|53667->30311|53697->30312|53730->30317|54030->30589|54059->30590|54091->30594|54144->30618|54174->30619|54207->30624|54272->30660|54302->30661|54336->30667|54650->30952|54680->30953|54715->30960|54779->30996|54808->30997|54841->31002|54870->31003|54903->31008|55010->31087|55039->31088|55071->31092|55101->31093|55134->31098|55259->31194|55289->31195|55323->31201|55626->31476|55656->31477|55690->31483|55719->31484|55750->31487|55779->31488|55810->31491|55853->31506|55882->31507|55914->31511|56047->31615|56077->31616|56108->31619|56269->31751|56299->31752|56331->31756|56386->31782|56416->31783|56449->31788|56519->31829|56549->31830|56583->31836|56759->31984|56788->31985|56820->31989|56849->31990|56880->31993|56909->31994|56947->32004|56971->32018|57009->32034|57089->32085|57119->32086|57151->32090|57206->32116|57236->32117|57269->32122|57357->32181|57387->32182|57421->32188|57597->32336|57626->32337|57658->32341|57687->32342|57721->32348|57750->32349|57782->32353|57811->32354|57843->32358|57898->32384|57928->32385|57961->32390|58067->32468|58096->32469|58127->32472|58156->32473|58196->32485|58270->32530|58300->32531|58332->32535|58502->32675|58533->32676|58566->32681|58677->32764|58706->32765|58739->32770|58768->32771|58800->32775|58829->32776|58861->32780|59056->32946|59086->32947|59119->32952|59372->33177|59401->33178|59433->33182|59463->33183|59496->33188|59529->33193|59551->33205|59588->33220|59626->33229|59656->33230|59690->33236|59833->33350|59863->33351|59898->33358|60012->33443|60042->33444|60077->33451|60106->33452|60139->33456|60169->33457|60203->33463|60482->33714|60511->33715|60543->33719|60572->33720|60608->33728|60656->33747|60686->33748|60719->33753|61069->34074|61099->34075|61133->34081|61221->34140|61251->34141|61286->34148|61374->34208|61403->34209|61436->34214|61465->34215|61503->34225|61540->34233|61570->34234|61604->34240|61736->34344|61765->34345|61798->34349|61828->34350|61862->34356|62014->34480|62043->34481|62080->34490|62109->34491|62138->34492|62176->34502|62200->34516|62238->34532|62318->34583|62348->34584|62381->34589|62731->34910|62761->34911|62795->34917|62883->34976|62913->34977|62948->34984|63036->35044|63065->35045|63098->35050|63127->35051|63165->35061|63202->35069|63232->35070|63266->35076|63398->35180|63427->35181|63460->35185|63490->35186|63524->35192|63676->35316|63705->35317|63737->35321|63766->35322|63802->35330|64105->35604|64135->35605|64318->35759|64359->35760|64393->35766|64490->35819|64522->35823|64551->35824|64582->35827|64611->35828|64641->35830|64670->35831|64702->35835|64789->35893|64819->35894|64850->35897|65264->36283|65293->36284|65325->36288|65423->36357|65453->36358|65484->36361|66347->37195|66372->37209|66402->37216|67389->38175|67429->38176|67463->38182|67648->38340|67712->38387|67752->38388|67788->38396|67984->38564|68011->38569|68103->38630|68287->38785|68328->38786|68363->38793|68964->39350|69014->39356|69045->39359|69240->39525|69270->39526|69303->39531|69435->39634|69465->39635|69499->39641|69532->39646|69562->39654|69600->39663|69630->39664|69665->39671|69782->39760|69811->39761|69844->39765|69874->39766|69909->39773|69981->39817|70010->39818|70049->39829|70078->39830|70110->39834|70139->39835|70170->39838|70245->39884|70275->39885|70310->39892|70482->40035|70512->40036|70553->40047|70660->40124|70691->40125|70725->40130|70755->40131|70785->40132|70934->40252|70964->40253|70999->40259|71140->40369|71172->40370|71204->40373|71469->40608|71500->40609|71533->40613|71666->40715|71698->40716|71732->40721|71906->40866|71936->40867|71968->40870|71998->40871|72036->40880|72107->40921|72138->40922|72171->40926|72286->41011|72317->41012|72351->41017|72474->41110|72505->41111|72540->41117|72618->41165|72649->41166|72685->41173|72756->41214|72787->41215|72824->41223|73010->41380|73040->41381|73075->41387|73105->41388|73140->41394|73497->41721|73528->41722|73564->41729|73654->41789|73685->41790|73722->41798|73813->41860|73843->41861|73878->41867|73908->41868|73949->41880|73987->41888|74018->41889|74054->41896|74189->42002|74219->42003|74253->42007|74284->42008|74320->42015|74474->42140|74504->42141|74538->42146|74568->42147|74607->42157|74632->42171|74671->42187|74752->42238|74783->42239|74818->42245|74896->42293|74927->42294|74963->42301|75051->42359|75082->42360|75119->42368|75305->42525|75335->42526|75370->42532|75400->42533|75435->42539|75792->42866|75823->42867|75859->42874|75949->42934|75980->42935|76017->42943|76108->43005|76138->43006|76173->43012|76203->43013|76244->43025|76282->43033|76313->43034|76349->43041|76484->43147|76514->43148|76548->43152|76579->43153|76615->43160|76769->43285|76799->43286|76833->43291|76863->43292|76900->43300|76930->43301|76964->43306|76994->43307|77027->43311|77057->43312|77094->43320|77143->43339|77174->43340|77208->43345|77555->43662|77586->43663|77621->43669|77710->43728|77741->43729|77777->43736|77866->43796|77896->43797|77930->43802|77960->43803|77999->43813|78037->43821|78068->43822|78103->43828|78236->43932|78266->43933|78300->43937|78331->43938|78366->43944|78518->44067|78548->44068|78586->44077|78616->44078|78655->44088|78680->44102|78719->44118|78800->44169|78831->44170|78865->44175|79212->44492|79243->44493|79278->44499|79367->44558|79398->44559|79434->44566|79523->44626|79553->44627|79587->44632|79617->44633|79656->44643|79694->44651|79725->44652|79760->44658|79893->44762|79923->44763|79957->44767|79988->44768|80023->44774|80175->44897|80205->44898|80238->44902|80268->44903|80305->44911|80504->45080|80535->45081|80569->45086|80660->45147|80691->45148|80726->45154|80818->45217|80848->45218|80881->45222|80911->45223|80944->45227|80982->45235|81013->45236|81047->45241|81189->45354|81219->45355|81252->45359|81283->45360|81317->45365|81478->45497|81508->45498|81541->45502|81605->45536|81636->45537|81678->45550|83345->47187|83376->47188|83416->47198|83545->47297|83576->47298|83617->47309|84146->47808|84177->47809|84215->47818|84246->47819|84284->47828|84507->48021|84538->48022|84578->48032|84760->48184|84791->48185|84835->48199|84945->48279|84976->48280|85012->48287|85042->48288|85072->48289|85503->48690|85534->48691|85572->48700|85682->48780|85713->48781|85753->48791|85834->48843|85865->48844|85903->48853|85933->48854|85978->48870|86108->48971|86138->48972|86171->48976|86288->49064|86318->49065|86349->49067|86379->49068|86412->49072|86552->49181|86584->49182|86616->49185|87568->50107|87599->50108|87633->50113|87761->50211|87792->50212|87827->50218|87893->50254|87924->50255|87964->50265|88093->50364|88124->50365|88165->50376|88333->50514|88364->50515|88406->50527|89501->51592|89532->51593|89572->51603|89603->51604|89641->51613|89672->51614|89706->51619|89736->51620|89769->51624|89799->51625|89832->51629|90629->52396|90660->52397|90698->52406|90826->52504|90857->52505|90897->52515|91421->53010|91452->53011|91489->53019|91519->53020|91556->53028|91852->53295|91882->53296|91914->53299|92031->53386|92062->53387|92094->53390|92145->53411|92176->53412|92209->53416|92275->53452|92306->53453|92343->53461|92470->53558|92501->53559|92539->53568|94336->55336|94366->55337|94402->55344|94432->55345|94464->55348|94494->55349|94525->55351|94555->55352|94586->55354|94719->55456|94751->55457|94783->55460|94958->55605|94989->55606|95025->55613|95164->55722|95195->55723|95232->55731|95387->55857|95417->55858|95453->55865|95483->55866|95518->55872|95608->55933|95638->55934|95671->55938|95792->56029|95823->56030|95855->56033|96096->56244|96127->56245|96163->56252|96302->56361|96333->56362|96370->56370|96449->56420|96479->56421|96515->56428|96545->56429|96580->56435|96670->56496|96700->56497|96733->56501|96796->56534|96827->56535|96862->56541|96895->56546|96918->56558|96951->56568|96990->56577|97021->56578|97058->56586|97091->56591|97122->56599|97161->56608|97192->56609|97226->56614|97514->56873|97555->56874|97592->56882|97632->56894|97697->56941|97738->56942|97778->56952|97822->56964|98009->57121|98051->57122|98089->57131|98231->57228|98284->57236|98320->57243|98368->57262|98398->57263|98431->57267|98462->57268|98496->57273|98787->57535|98828->57536|98865->57544|98905->57556|98970->57603|99011->57604|99051->57614|99095->57626|99282->57783|99324->57784|99362->57793|99504->57890|99557->57898|99593->57905|99641->57924|99671->57925|99711->57936|99741->57937|99774->57941|99804->57942|99837->57946|100095->58175|100136->58176|100172->58183|100211->58194|100276->58241|100317->58242|100355->58251|100398->58262|100584->58418|100626->58419|100663->58427|100803->58522|100855->58529|100890->58535|100936->58552|100966->58553|101007->58565|101307->58835|101338->58836|101371->58840|101975->59414|102006->59415|102190->59569|102232->59570|102267->59576|102709->59973|102742->59977|102772->59978|102809->59986|102854->60001|102885->60002|102919->60007|102989->60048|103019->60049|103051->60052|103081->60053|103113->60056|103358->60271|103389->60272|103572->60425|103614->60426|103648->60431|103823->60561|103855->60564|103885->60565|103920->60571|104154->60777|104177->60789|104210->60799|104368->60927|104399->60928|104435->60935|104608->61078|104639->61079|104680->61090|104787->61167|104818->61168|104852->61173|104882->61174|104912->61175|104946->61180|104976->61181|105009->61185|105072->61218|105103->61219|105135->61222|105344->61401|105375->61402|105408->61406|105473->61442|105503->61443|105535->61446|105611->61492|105642->61493|105678->61500|105851->61643|105882->61644|105923->61655|106030->61732|106061->61733|106095->61738|106125->61739|106155->61740|106393->61950|106416->61962|106449->61972|106569->62063|106599->62064|106631->62067|106823->62229|106854->62230|106889->62236|107151->62468|107182->62469|107215->62473|107447->62675|107478->62676|107512->62681|107572->62711|107603->62712|107639->62719|107692->62743|107722->62744|107755->62748|107785->62749|107818->62753|107898->62803|107929->62804|107963->62809|108016->62833|108046->62834|108078->62837|108108->62838|108143->62844|108190->62861|108221->62862|108254->62866|108307->62889|108338->62890|108372->62895|108551->63045|108581->63046|108614->63050|108645->63051|108679->63056|108792->63140|108822->63141|108854->63144|108884->63145|108917->63149|108947->63150|108980->63154|109225->63370|109255->63371|109286->63373|109316->63374|109349->63378|109458->63457|109489->63458|109521->63461|109593->63503|109624->63504|109657->63508|109696->63518|109726->63519|109761->63525|109860->63594|109891->63595|109924->63599|110004->63650|110034->63651|110067->63655|110201->63759|110232->63760|110265->63764|110320->63789|110351->63790|110382->63791|110439->63818|110470->63819|110502->63822|110532->63823|110564->63826|110611->63843|110642->63844|110675->63848|110816->63960|110839->63972|110870->63980|110914->63994|110945->63995|110979->64000|111253->64244|111284->64245|111324->64255|111370->64271|111401->64272|111437->64279|112163->64975|112194->64976|112231->64984|112356->65080|112386->65081|112450->65116|112521->65157|112552->65158|112589->65166|112777->65324|112808->65325|112846->65334|113105->65563|113136->65564|113176->65574|113432->65801|113463->65802|113497->65806|113528->65807|113568->65817|113779->65999|113810->66000|113848->66009|113946->66077|113977->66078|114017->66088|114082->66124|114113->66125|114160->66143|114472->66426|114502->66427|114538->66434|114568->66435|114604->66442|114766->66574|114797->66575|114835->66584|114887->66606|114918->66607|114951->66610|114982->66611|115039->66638|115070->66639|115110->66649|115167->66677|115198->66678|115235->66686|115265->66687|115302->66695|115351->66714|115382->66715|115420->66724|115551->66826|115581->66827|115625->66842|115707->66894|115738->66895|115775->66903|115853->66952|115883->66953|115923->66963|115954->66964|115988->66968|116019->66969|116055->66976|116148->67039|116179->67040|116213->67045|116243->67046|116277->67051|116307->67052|116341->67057|116371->67058|116404->67062|116434->67063|116467->67067|116714->67285|116744->67286|116778->67291|116808->67292|116841->67296|116897->67322|116928->67323|116960->67326|117046->67383|117076->67384|117109->67388|117164->67413|117195->67414|117227->67417|117339->67499|117370->67500|117413->67513|117665->67735|117696->67736|117734->67745|117872->67854|117902->67855|117940->67864|117971->67865|118004->67869|118034->67870|118067->67874
                  LINES: 28->1|38->7|40->9|40->9|40->9|42->11|42->11|44->13|46->15|46->15|47->16|47->16|49->18|56->25|56->25|57->26|67->36|67->36|67->36|69->38|69->38|69->38|78->47|78->47|78->47|82->51|82->51|82->51|92->61|92->61|93->62|96->65|96->65|99->68|99->68|100->69|103->72|103->72|108->77|109->78|135->104|135->104|135->104|141->110|141->110|141->110|152->121|152->121|152->121|165->134|165->134|165->134|165->134|165->134|165->134|165->134|165->134|165->134|165->134|165->134|165->134|165->134|165->134|165->134|165->134|165->134|166->135|166->135|179->148|179->148|179->148|179->148|179->148|179->148|179->148|179->148|180->149|180->149|194->163|194->163|194->163|227->196|227->196|228->197|236->205|241->210|245->214|245->214|245->214|246->215|246->215|246->215|255->224|255->224|256->225|257->226|257->226|258->227|259->228|260->229|264->233|264->233|265->234|266->235|266->235|266->235|267->236|268->237|268->237|268->237|269->238|270->239|271->240|271->240|272->241|274->243|275->244|276->245|279->248|279->248|279->248|280->249|282->251|282->251|282->251|283->252|283->252|283->252|284->253|284->253|284->253|285->254|287->256|287->256|288->257|289->258|289->258|290->259|291->260|291->260|291->260|293->262|293->262|293->262|297->266|299->268|300->269|300->269|300->269|301->270|301->270|301->270|302->271|304->273|304->273|305->274|306->275|306->275|307->276|307->276|307->276|307->276|308->277|310->279|310->279|310->279|310->279|310->279|310->279|310->279|311->280|311->280|311->280|311->280|311->280|311->280|312->281|312->281|312->281|313->282|313->282|313->282|314->283|314->283|314->283|316->285|316->285|316->285|316->285|316->285|316->285|316->285|316->285|316->285|322->291|322->291|322->291|322->291|322->291|322->291|327->296|327->296|327->296|327->296|327->296|327->296|327->296|327->296|327->296|327->296|327->296|327->296|327->296|327->296|327->296|327->296|327->296|327->296|330->299|330->299|331->300|332->301|332->301|332->301|332->301|332->301|332->301|333->302|333->302|333->302|333->302|333->302|333->302|334->303|334->303|334->303|334->303|334->303|334->303|334->303|334->303|334->303|334->303|334->303|334->303|336->305|336->305|337->306|338->307|338->307|338->307|338->307|338->307|338->307|339->308|339->308|339->308|339->308|339->308|339->308|340->309|340->309|340->309|340->309|340->309|340->309|340->309|340->309|340->309|340->309|340->309|340->309|342->311|343->312|344->313|344->313|344->313|344->313|344->313|344->313|350->319|350->319|350->319|350->319|350->319|350->319|355->324|355->324|355->324|355->324|355->324|355->324|356->325|356->325|356->325|356->325|356->325|356->325|357->326|357->326|358->327|359->328|359->328|359->328|359->328|359->328|359->328|360->329|360->329|360->329|360->329|360->329|360->329|362->331|362->331|362->331|363->332|364->333|364->333|365->334|367->336|367->336|367->336|367->336|367->336|367->336|367->336|367->336|372->341|372->341|374->343|374->343|375->344|377->346|377->346|377->346|377->346|377->346|377->346|377->346|377->346|381->350|381->350|381->350|382->351|382->351|384->353|384->353|385->354|387->356|387->356|387->356|387->356|387->356|387->356|387->356|387->356|392->361|392->361|392->361|392->361|392->361|392->361|392->361|392->361|392->361|392->361|392->361|392->361|392->361|392->361|392->361|392->361|392->361|392->361|392->361|392->361|393->362|393->362|394->363|395->364|395->364|395->364|395->364|395->364|395->364|395->364|395->364|395->364|395->364|395->364|395->364|395->364|395->364|395->364|395->364|395->364|396->365|396->365|396->365|396->365|397->366|397->366|397->366|397->366|397->366|397->366|397->366|397->366|397->366|397->366|397->366|397->366|397->366|397->366|397->366|397->366|397->366|397->366|398->367|398->367|399->368|400->369|401->370|402->371|402->371|403->372|407->376|407->376|407->376|407->376|407->376|407->376|412->381|412->381|412->381|412->381|412->381|412->381|415->384|415->384|415->384|415->384|415->384|415->384|416->385|417->386|418->387|419->388|420->389|430->399|430->399|431->400|434->403|435->404|490->459|490->459|490->459|491->460|491->460|491->460|491->460|491->460|491->460|491->460|491->460|491->460|491->460|491->460|491->460|491->460|492->461|492->461|492->461|492->461|492->461|492->461|493->462|493->462|493->462|493->462|493->462|493->462|494->463|494->463|494->463|494->463|494->463|494->463|496->465|497->466|540->509|540->509|540->509|562->531|562->531|562->531|579->548|579->548|581->550|584->553|585->554|585->554|585->554|585->554|585->554|586->555|586->555|586->555|586->555|586->555|586->555|587->556|587->556|587->556|587->556|587->556|587->556|588->557|588->557|588->557|588->557|588->557|588->557|589->558|589->558|589->558|589->558|589->558|589->558|590->559|590->559|590->559|590->559|590->559|590->559|591->560|591->560|591->560|591->560|591->560|591->560|592->561|592->561|592->561|592->561|592->561|592->561|593->562|593->562|593->562|593->562|593->562|593->562|594->563|594->563|594->563|594->563|594->563|594->563|595->564|595->564|595->564|595->564|595->564|595->564|596->565|596->565|596->565|596->565|596->565|596->565|597->566|597->566|597->566|597->566|597->566|597->566|598->567|598->567|598->567|598->567|598->567|598->567|599->568|599->568|600->569|600->569|600->569|600->569|600->569|600->569|601->570|601->570|601->570|602->571|602->571|602->571|602->571|602->571|602->571|602->571|602->571|603->572|605->574|605->574|605->574|605->574|605->574|605->574|605->574|605->574|606->575|606->575|606->575|606->575|606->575|606->575|606->575|606->575|607->576|607->576|607->576|607->576|607->576|607->576|607->576|607->576|608->577|609->578|616->585|616->585|616->585|618->587|618->587|619->588|621->590|621->590|623->592|623->592|623->592|624->593|629->598|629->598|631->600|631->600|631->600|632->601|633->602|633->602|634->603|636->605|636->605|637->606|639->608|639->608|639->608|639->608|640->609|641->610|641->610|642->611|642->611|643->612|643->612|646->615|649->618|649->618|650->619|650->619|650->619|651->620|654->623|654->623|655->624|656->625|656->625|657->626|657->626|657->626|660->629|660->629|660->629|661->630|661->630|661->630|661->630|661->630|661->630|661->630|661->630|661->630|661->630|661->630|661->630|661->630|661->630|661->630|661->630|661->630|661->630|661->630|662->631|663->632|665->634|665->634|666->635|669->638|669->638|670->639|671->640|671->640|672->641|672->641|672->641|674->643|674->643|675->644|678->647|678->647|679->648|680->649|680->649|681->650|681->650|681->650|688->657|688->657|689->658|691->660|691->660|692->661|693->662|693->662|694->663|695->664|695->664|696->665|696->665|697->666|697->666|697->666|698->667|698->667|698->667|698->667|698->667|698->667|698->667|699->668|699->668|699->668|699->668|699->668|699->668|700->669|701->670|701->670|702->671|702->671|703->672|703->672|704->673|704->673|706->675|706->675|707->676|708->677|708->677|708->677|709->678|709->678|709->678|711->680|711->680|711->680|711->680|711->680|712->681|712->681|712->681|713->682|720->689|720->689|721->690|722->691|722->691|723->692|723->692|724->693|724->693|726->695|726->695|726->695|727->696|734->703|734->703|735->704|736->705|736->705|737->706|737->706|739->708|739->708|741->710|741->710|741->710|742->711|742->711|742->711|743->712|743->712|743->712|744->713|744->713|744->713|744->713|745->714|745->714|745->714|748->717|748->717|749->718|756->725|756->725|757->726|757->726|757->726|758->727|758->727|758->727|759->728|759->728|759->728|760->729|760->729|761->730|761->730|762->731|762->731|762->731|763->732|763->732|763->732|764->733|765->734|765->734|766->735|766->735|767->736|767->736|768->737|768->737|769->738|769->738|770->739|770->739|772->741|772->741|772->741|773->742|775->744|775->744|776->745|777->746|777->746|777->746|777->746|777->746|778->747|783->752|783->752|784->753|784->753|784->753|784->753|785->754|788->757|788->757|789->758|794->763|794->763|795->764|795->764|795->764|796->765|796->765|796->765|797->766|802->771|802->771|803->772|805->774|805->774|806->775|806->775|807->776|809->778|809->778|809->778|809->778|810->779|811->780|811->780|812->781|817->786|817->786|818->787|818->787|819->788|819->788|820->789|821->790|821->790|823->792|824->793|824->793|825->794|829->798|829->798|830->799|830->799|830->799|831->800|831->800|831->800|832->801|835->804|835->804|836->805|836->805|837->806|837->806|837->806|837->806|837->806|837->806|837->806|838->807|838->807|838->807|839->808|839->808|839->808|840->809|843->812|843->812|844->813|844->813|846->815|846->815|846->815|846->815|847->816|847->816|847->816|848->817|849->818|849->818|850->819|850->819|854->823|854->823|854->823|855->824|856->825|856->825|857->826|858->827|858->827|859->828|859->828|859->828|859->828|860->829|865->834|865->834|866->835|869->838|869->838|869->838|869->838|870->839|870->839|870->839|870->839|870->839|870->839|871->840|872->841|872->841|873->842|874->843|874->843|875->844|875->844|875->844|875->844|876->845|880->849|880->849|881->850|881->850|883->852|883->852|883->852|884->853|891->860|891->860|892->861|893->862|893->862|894->863|896->865|896->865|897->866|897->866|899->868|899->868|899->868|900->869|902->871|902->871|902->871|902->871|903->872|905->874|905->874|907->876|907->876|907->876|907->876|907->876|907->876|907->876|907->876|908->877|915->884|915->884|916->885|917->886|917->886|918->887|920->889|920->889|921->890|921->890|923->892|923->892|923->892|924->893|926->895|926->895|926->895|926->895|927->896|929->898|929->898|930->899|930->899|932->901|937->906|937->906|938->907|938->907|939->908|940->909|941->910|941->910|942->911|942->911|943->912|943->912|945->914|945->914|945->914|946->915|955->924|955->924|957->926|957->926|957->926|958->927|972->941|972->941|972->941|988->957|988->957|989->958|990->959|990->959|990->959|991->960|993->962|993->962|996->965|997->966|997->966|998->967|1008->977|1009->978|1010->979|1014->983|1014->983|1015->984|1016->985|1016->985|1017->986|1017->986|1017->986|1017->986|1017->986|1018->987|1020->989|1020->989|1020->989|1020->989|1021->990|1022->991|1022->991|1024->993|1024->993|1025->994|1025->994|1026->995|1026->995|1026->995|1027->996|1030->999|1030->999|1031->1000|1032->1001|1032->1001|1033->1002|1033->1002|1033->1002|1038->1007|1038->1007|1041->1010|1041->1010|1041->1010|1042->1011|1048->1017|1048->1017|1049->1018|1049->1018|1049->1018|1050->1019|1053->1022|1053->1022|1054->1023|1054->1023|1057->1026|1057->1026|1057->1026|1058->1027|1058->1027|1058->1027|1059->1028|1061->1030|1061->1030|1062->1031|1063->1032|1063->1032|1064->1033|1064->1033|1064->1033|1065->1034|1068->1037|1068->1037|1069->1038|1069->1038|1070->1039|1077->1046|1077->1046|1078->1047|1079->1048|1079->1048|1080->1049|1082->1051|1082->1051|1083->1052|1083->1052|1085->1054|1085->1054|1085->1054|1086->1055|1088->1057|1088->1057|1088->1057|1088->1057|1089->1058|1091->1060|1091->1060|1092->1061|1092->1061|1092->1061|1092->1061|1092->1061|1092->1061|1092->1061|1093->1062|1094->1063|1094->1063|1095->1064|1095->1064|1095->1064|1096->1065|1099->1068|1099->1068|1100->1069|1100->1069|1101->1070|1108->1077|1108->1077|1109->1078|1110->1079|1110->1079|1111->1080|1113->1082|1113->1082|1114->1083|1114->1083|1116->1085|1116->1085|1116->1085|1117->1086|1119->1088|1119->1088|1119->1088|1119->1088|1120->1089|1122->1091|1122->1091|1123->1092|1123->1092|1124->1093|1124->1093|1125->1094|1125->1094|1125->1094|1125->1094|1127->1096|1127->1096|1127->1096|1128->1097|1135->1104|1135->1104|1136->1105|1137->1106|1137->1106|1138->1107|1140->1109|1140->1109|1141->1110|1141->1110|1143->1112|1143->1112|1143->1112|1144->1113|1146->1115|1146->1115|1146->1115|1146->1115|1147->1116|1149->1118|1149->1118|1151->1120|1151->1120|1151->1120|1151->1120|1151->1120|1151->1120|1151->1120|1152->1121|1159->1128|1159->1128|1160->1129|1161->1130|1161->1130|1162->1131|1164->1133|1164->1133|1165->1134|1165->1134|1167->1136|1167->1136|1167->1136|1168->1137|1170->1139|1170->1139|1170->1139|1170->1139|1171->1140|1173->1142|1173->1142|1174->1143|1174->1143|1176->1145|1180->1149|1180->1149|1181->1150|1182->1151|1182->1151|1183->1152|1185->1154|1185->1154|1186->1155|1186->1155|1187->1156|1187->1156|1187->1156|1188->1157|1190->1159|1190->1159|1190->1159|1190->1159|1191->1160|1193->1162|1193->1162|1194->1163|1194->1163|1194->1163|1196->1165|1210->1179|1210->1179|1211->1180|1212->1181|1212->1181|1213->1182|1217->1186|1217->1186|1218->1187|1218->1187|1219->1188|1224->1193|1224->1193|1225->1194|1228->1197|1228->1197|1229->1198|1230->1199|1230->1199|1231->1200|1231->1200|1231->1200|1239->1208|1239->1208|1240->1209|1241->1210|1241->1210|1242->1211|1243->1212|1243->1212|1244->1213|1244->1213|1246->1215|1248->1217|1248->1217|1249->1218|1250->1219|1250->1219|1251->1220|1251->1220|1253->1222|1253->1222|1253->1222|1254->1223|1263->1232|1263->1232|1264->1233|1265->1234|1265->1234|1266->1235|1266->1235|1266->1235|1267->1236|1268->1237|1268->1237|1269->1238|1270->1239|1270->1239|1271->1240|1287->1256|1287->1256|1288->1257|1288->1257|1289->1258|1289->1258|1290->1259|1290->1259|1291->1260|1291->1260|1292->1261|1299->1268|1299->1268|1300->1269|1301->1270|1301->1270|1302->1271|1306->1275|1306->1275|1307->1276|1307->1276|1308->1277|1316->1285|1316->1285|1318->1287|1318->1287|1318->1287|1319->1288|1319->1288|1319->1288|1320->1289|1320->1289|1320->1289|1321->1290|1322->1291|1322->1291|1323->1292|1356->1325|1356->1325|1357->1326|1357->1326|1358->1327|1358->1327|1359->1328|1359->1328|1360->1329|1360->1329|1360->1329|1361->1330|1363->1332|1363->1332|1364->1333|1365->1334|1365->1334|1366->1335|1368->1337|1368->1337|1369->1338|1369->1338|1370->1339|1371->1340|1371->1340|1373->1342|1373->1342|1373->1342|1374->1343|1378->1347|1378->1347|1379->1348|1380->1349|1380->1349|1381->1350|1382->1351|1382->1351|1383->1352|1383->1352|1384->1353|1385->1354|1385->1354|1387->1356|1387->1356|1387->1356|1389->1358|1389->1358|1389->1358|1389->1358|1389->1358|1389->1358|1391->1360|1391->1360|1391->1360|1391->1360|1391->1360|1392->1361|1395->1364|1395->1364|1396->1365|1397->1366|1397->1366|1397->1366|1398->1367|1399->1368|1400->1369|1400->1369|1401->1370|1403->1372|1404->1373|1405->1374|1407->1376|1407->1376|1407->1376|1407->1376|1408->1377|1411->1380|1411->1380|1412->1381|1413->1382|1413->1382|1413->1382|1414->1383|1415->1384|1416->1385|1416->1385|1417->1386|1419->1388|1420->1389|1421->1390|1423->1392|1423->1392|1426->1395|1426->1395|1426->1395|1426->1395|1427->1396|1430->1399|1430->1399|1431->1400|1432->1401|1432->1401|1432->1401|1433->1402|1434->1403|1435->1404|1435->1404|1436->1405|1438->1407|1439->1408|1440->1409|1442->1411|1442->1411|1446->1415|1456->1425|1456->1425|1457->1426|1471->1440|1471->1440|1472->1441|1472->1441|1473->1442|1481->1450|1482->1451|1482->1451|1484->1453|1484->1453|1484->1453|1485->1454|1486->1455|1486->1455|1487->1456|1487->1456|1488->1457|1493->1462|1493->1462|1494->1463|1494->1463|1495->1464|1497->1466|1498->1467|1498->1467|1500->1469|1503->1472|1503->1472|1503->1472|1506->1475|1506->1475|1507->1476|1510->1479|1510->1479|1511->1480|1512->1481|1512->1481|1513->1482|1513->1482|1513->1482|1515->1484|1515->1484|1517->1486|1517->1486|1517->1486|1518->1487|1521->1490|1521->1490|1522->1491|1523->1492|1523->1492|1524->1493|1524->1493|1524->1493|1525->1494|1528->1497|1528->1497|1529->1498|1530->1499|1530->1499|1531->1500|1531->1500|1531->1500|1535->1504|1535->1504|1535->1504|1536->1505|1536->1505|1538->1507|1539->1508|1539->1508|1541->1510|1548->1517|1548->1517|1549->1518|1552->1521|1552->1521|1553->1522|1553->1522|1553->1522|1554->1523|1555->1524|1555->1524|1556->1525|1556->1525|1557->1526|1557->1526|1557->1526|1558->1527|1559->1528|1559->1528|1560->1529|1560->1529|1562->1531|1562->1531|1562->1531|1563->1532|1563->1532|1563->1532|1564->1533|1567->1536|1567->1536|1567->1536|1567->1536|1568->1537|1570->1539|1570->1539|1571->1540|1571->1540|1571->1540|1571->1540|1572->1541|1576->1545|1576->1545|1577->1546|1577->1546|1579->1548|1580->1549|1580->1549|1581->1550|1582->1551|1582->1551|1583->1552|1584->1553|1584->1553|1586->1555|1587->1556|1587->1556|1588->1557|1589->1558|1589->1558|1591->1560|1592->1561|1592->1561|1593->1562|1593->1562|1593->1562|1593->1562|1593->1562|1593->1562|1594->1563|1594->1563|1595->1564|1595->1564|1595->1564|1596->1565|1598->1567|1598->1567|1598->1567|1599->1568|1599->1568|1600->1569|1608->1577|1608->1577|1609->1578|1609->1578|1609->1578|1610->1579|1625->1594|1625->1594|1626->1595|1627->1596|1627->1596|1632->1601|1632->1601|1632->1601|1633->1602|1635->1604|1635->1604|1636->1605|1640->1609|1640->1609|1641->1610|1643->1612|1643->1612|1643->1612|1643->1612|1644->1613|1647->1616|1647->1616|1648->1617|1649->1618|1649->1618|1650->1619|1651->1620|1651->1620|1653->1622|1659->1628|1659->1628|1660->1629|1660->1629|1661->1630|1664->1633|1664->1633|1665->1634|1665->1634|1665->1634|1665->1634|1665->1634|1666->1635|1666->1635|1667->1636|1668->1637|1668->1637|1669->1638|1669->1638|1670->1639|1670->1639|1670->1639|1671->1640|1672->1641|1672->1641|1674->1643|1675->1644|1675->1644|1676->1645|1677->1646|1677->1646|1678->1647|1678->1647|1678->1647|1678->1647|1679->1648|1680->1649|1680->1649|1681->1650|1681->1650|1682->1651|1682->1651|1683->1652|1683->1652|1683->1652|1683->1652|1684->1653|1688->1657|1688->1657|1690->1659|1690->1659|1692->1661|1692->1661|1692->1661|1693->1662|1695->1664|1695->1664|1697->1666|1697->1666|1697->1666|1698->1667|1700->1669|1700->1669|1701->1670|1708->1677|1708->1677|1709->1678|1711->1680|1711->1680|1712->1681|1712->1681|1713->1682|1713->1682|1716->1685
                  -- GENERATED --
              */
          