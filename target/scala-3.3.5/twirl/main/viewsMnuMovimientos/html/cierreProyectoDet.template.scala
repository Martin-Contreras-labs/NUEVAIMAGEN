
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

object cierreProyectoDet extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template15[Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,List[List[String]],tables.BodegaEmpresa,Long,String,List[List[String]],List[tables.TipoEstado],List[tables.TipoReparacion],List[tables.Transportista],tables.Sucursal,tables.Comercial,String,play.twirl.api.HtmlFormat.Appendable] {

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

	<form action="/cierreAplicar/" enctype="multipart/form-data" method="POST" onsubmit="return validarForm();">
		<div id="mostrarmostrar" style="display:none;">

			"""),_display_(/*26.5*/barraTitulo(mapDiccionario, mapDiccionario.get("BODEGA")+"/PROYECTO PARA CIERRE", "(este proceso permite vender o pasar a baja todo el saldo de stock)")),format.raw/*26.157*/("""
			"""),format.raw/*27.4*/("""<div class="row  justify-content-md-center">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<table class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<tr>
								<td width="50%" colspan="2">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Origen</span>
								  		</div>
								  		<input type="hidden" name="id_bodegaOrigen" value=""""),_display_(/*37.65*/bodegaOrigen/*37.77*/.getId()),format.raw/*37.85*/("""">
	  									<input type="text" class="form-control"
	  										value=""""),_display_(/*39.22*/bodegaOrigen/*39.34*/.getNombre().toUpperCase),format.raw/*39.58*/(""""
	  										readonly>
									</div>
									<input type="hidden" name="id_bodegaDestino" id="id_bodegaDestino" value=""""),_display_(/*42.85*/bodegaDestino/*42.98*/.getId()),format.raw/*42.106*/("""">
								</td>
								<td>
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Nro.Mov</span>
								  		</div>
											"""),_display_(if(mapPermiso.get("parametro.bloqueoNrosMovimientos")!=null && mapPermiso.get("parametro.bloqueoNrosMovimientos").equals("1"))/*49.139*/{_display_(Seq[Any](format.raw/*49.140*/("""
												"""),format.raw/*50.13*/("""<input type="text" class="form-control left"
									  				name="numeroGuia"
													id="numeroGuia"
													value = """"),_display_(/*53.24*/nuevoNumeroGuia),format.raw/*53.39*/(""""
													readonly
													required>
											""")))}else/*56.17*/{_display_(Seq[Any](format.raw/*56.18*/("""
												"""),format.raw/*57.13*/("""<input type="text" class="form-control left"
									  				name="numeroGuia"
													id="numeroGuia"
													value = """"),_display_(/*60.24*/nuevoNumeroGuia),format.raw/*60.39*/(""""
													onkeydown="return ingresoInt(window.event)"
													onchange="verificarNumeroGuia(value)"
													autocomplete="off"
													required>
											""")))}),format.raw/*65.13*/("""
									"""),format.raw/*66.10*/("""</div>
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
									<input type="hidden" id="id_sucursalOrigen" name="id_sucursalOrigen" value=""""),_display_(/*92.87*/sucursal/*92.95*/.getId()),format.raw/*92.103*/("""">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Sucursal</span>
								  		</div>
											<input type="text" class="form-control left"
												value = """"),_display_(/*98.23*/sucursal/*98.31*/.getNombre()),format.raw/*98.43*/(""""
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
											    value=""""),_display_(/*109.24*/bodegaDestino/*109.37*/.getNameSucursal()),format.raw/*109.55*/(""""
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
										onblur="if(!limitaFecha(value,"""),_display_(/*122.42*/mapPermiso/*122.52*/.get("parametro.diasMenosGuia")),format.raw/*122.83*/(""","""),_display_(/*122.85*/mapPermiso/*122.95*/.get("parametro.diasMasGuia")),format.raw/*122.124*/(""")) """),format.raw/*122.127*/("""{"""),format.raw/*122.128*/("""value='"""),_display_(/*122.136*/hoy),format.raw/*122.139*/("""';$('#fechaIniTerGuia').val(value);"""),format.raw/*122.174*/("""}"""),format.raw/*122.175*/(""" """),format.raw/*122.176*/("""else """),format.raw/*122.181*/("""{"""),format.raw/*122.182*/("""$('#fechaIniTerGuia').val(value);"""),format.raw/*122.215*/("""}"""),format.raw/*122.216*/(""""
										value=""""),_display_(/*123.19*/hoy),format.raw/*123.22*/(""""
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
										onblur="if(!limitaFecha(value,"""),_display_(/*136.42*/mapPermiso/*136.52*/.get("parametro.diasMenosIniTer")),format.raw/*136.85*/(""","""),_display_(/*136.87*/mapPermiso/*136.97*/.get("parametro.diasMasIniTer")),format.raw/*136.128*/(""")) value='"""),_display_(/*136.139*/hoy),format.raw/*136.142*/("""';"
										value=""""),_display_(/*137.19*/hoy),format.raw/*137.22*/(""""
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
												value = """"),_display_(/*151.23*/comercial/*151.32*/.getNameUsuario()),format.raw/*151.49*/(""""
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


					"""),_display_(if(bodegaOrigen.esInterna == 1)/*184.38*/{_display_(Seq[Any](format.raw/*184.39*/("""
						"""),format.raw/*185.7*/("""<div class="noprint" align="center">
							<div class="row justify-content-md-center" >
								<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
									<input id="btnCargaExcel" type="button"  value="Copiar desde un Excel" class="btn btn-info btn-sm rounded-pill btn-block"
										onclick="$('#modalCargaMasiva').modal('show');">&nbsp;&nbsp;&nbsp;
								</div>
							</div>
						</div>
					""")))} else {null} ),format.raw/*193.7*/("""




					"""),format.raw/*198.6*/("""<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<TR>
								<TH>GRUPO</TH>
								<TH>Nro."""),_display_(/*202.18*/mapDiccionario/*202.32*/.get("OT")),format.raw/*202.42*/("""</TH>
								<TH with="80px">Fecha<br>"""),_display_(/*203.35*/mapDiccionario/*203.49*/.get("OT")),format.raw/*203.59*/("""</TH>
								<TH>Nro.COTI</TH>
								<TH>CODIGO</TH>
								<TH>EQUIPO</TH>
								<TH>KG</TH>
						        <TH>M2</TH>
						        <TH>UN</TH>
								<TH>STOCK</TH>
								<TH>SALIDA</TH>
								<TH>TOT.KG</TH>
								<TH>TOT.M2</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*217.9*/for(lista1 <- listEquipBodOrigen) yield /*217.42*/{_display_(Seq[Any](format.raw/*217.43*/("""
								"""),format.raw/*218.9*/("""<TR>
									<td  style="text-align:left;">
										<input type="hidden" name="id_equipo[]" value=""""),_display_(/*220.59*/lista1/*220.65*/.get(0)),format.raw/*220.72*/("""">
										<input type="hidden" name="id_cotizacion[]" value=""""),_display_(/*221.63*/lista1/*221.69*/.get(1)),format.raw/*221.76*/("""">
										"""),_display_(/*222.12*/lista1/*222.18*/.get(2)),format.raw/*222.25*/("""
									"""),format.raw/*223.10*/("""</td>

									"""),_display_(if(lista1.get(10).equals("0") || lista1.get(10).equals(""))/*225.70*/{_display_(Seq[Any](format.raw/*225.71*/("""
										"""),format.raw/*226.11*/("""<td style="text-align: center;">--</td>
									""")))}else/*227.15*/{_display_(Seq[Any](format.raw/*227.16*/("""
										"""),format.raw/*228.11*/("""<td style="text-align:center;" class="sorting_2">
											<a href="#" onclick="verOt('"""),_display_(/*229.41*/lista1/*229.47*/.get(10)),format.raw/*229.55*/("""')">
												<kbd style="background-color: rgb(90, 200, 245)">
													<font color="green">"""),_display_(/*231.35*/lista1/*231.41*/.get(11)),format.raw/*231.49*/("""</font>
												</kbd>
											</a>
										</td>
									""")))}),format.raw/*235.11*/("""

									"""),format.raw/*237.10*/("""<td style="text-align: center;">
										<div style="display: none;"> """),_display_(/*238.41*/utilities/*238.50*/.Fechas.AAMMDD(lista1.get(12))),format.raw/*238.80*/("""</div>
										"""),_display_(/*239.12*/lista1/*239.18*/.get(12)),format.raw/*239.26*/("""
									"""),format.raw/*240.10*/("""</td>

									"""),_display_(if(lista1.get(3).equals("0") || lista1.get(3).equals(""))/*242.68*/{_display_(Seq[Any](format.raw/*242.69*/("""
										"""),format.raw/*243.11*/("""<td style="text-align: center;">--</td>
									""")))}else/*244.15*/{_display_(Seq[Any](format.raw/*244.16*/("""
										"""),format.raw/*245.11*/("""<td style="text-align: center;">"""),_display_(/*245.44*/lista1/*245.50*/.get(3)),format.raw/*245.57*/("""</td>
									""")))}),format.raw/*246.11*/("""

									"""),format.raw/*248.10*/("""<td  style="text-align:left;"><a href="#" onclick="equipoDescripcion('"""),_display_(/*248.81*/lista1/*248.87*/.get(0)),format.raw/*248.94*/("""');"><div class="codigo">"""),_display_(/*248.120*/lista1/*248.126*/.get(4)),format.raw/*248.133*/("""</div></a></td>
									<td  style="text-align:left;"><a href="#" onclick="equipoDescripcion('"""),_display_(/*249.81*/lista1/*249.87*/.get(0)),format.raw/*249.94*/("""');">"""),_display_(/*249.100*/lista1/*249.106*/.get(5)),format.raw/*249.113*/("""</a></td>
									<td  class="kgUnit" style="text-align:right;">"""),_display_(/*250.57*/lista1/*250.63*/.get(6)),format.raw/*250.70*/("""</td>
									<td  class="m2Unit" style="text-align:right;">"""),_display_(/*251.57*/lista1/*251.63*/.get(7)),format.raw/*251.70*/("""</td>
									<td  style="text-align:center;">"""),_display_(/*252.43*/lista1/*252.49*/.get(8)),format.raw/*252.56*/("""</td>
									<td  style="text-align:right;">
										<div class="verificaStock" id="stock_"""),_display_(/*254.49*/lista1/*254.55*/.get(0)),format.raw/*254.62*/("""_"""),_display_(/*254.64*/lista1/*254.70*/.get(1)),format.raw/*254.77*/("""">"""),_display_(/*254.80*/lista1/*254.86*/.get(9)),format.raw/*254.93*/("""</div>
									</td>
									<td  style="text-align:center;">
										<div align="center">
											<input type="text" class="cantidad form-control height23px right width80px"
												name="cantidad[]"
												id="cantidad_"""),_display_(/*260.27*/lista1/*260.33*/.get(0)),format.raw/*260.40*/("""_"""),_display_(/*260.42*/lista1/*260.48*/.get(1)),format.raw/*260.55*/(""""
												value=""""),_display_(/*261.21*/lista1/*261.27*/.get(9)),format.raw/*261.34*/(""""
												onfocus="value=value.replace(/,/g,''); cantAux = value;"
												onkeydown="return ingresoDouble(window.event)"
												autocomplete="off"
												onchange="if(value=='') value=0; verificaCantidad('"""),_display_(/*265.65*/lista1/*265.71*/.get(0)),format.raw/*265.78*/("""','"""),_display_(/*265.82*/lista1/*265.88*/.get(1)),format.raw/*265.95*/("""','"""),_display_(/*265.99*/lista1/*265.105*/.get(9)),format.raw/*265.112*/("""',value,'"""),_display_(/*265.122*/lista1/*265.128*/.get(6)),format.raw/*265.135*/("""','"""),_display_(/*265.139*/lista1/*265.145*/.get(7)),format.raw/*265.152*/("""','"""),_display_(/*265.156*/listTipoEstado/*265.170*/.size()),format.raw/*265.177*/("""');">
										</div>
									</td>


									<td style="text-align:right;"><div class="KG" id="KG_"""),_display_(/*270.64*/lista1/*270.70*/.get(0)),format.raw/*270.77*/("""_"""),_display_(/*270.79*/lista1/*270.85*/.get(1)),format.raw/*270.92*/("""">0.00</div></td>
									<td style="text-align:right;">
										<div class="M2" id="M2_"""),_display_(/*272.35*/lista1/*272.41*/.get(0)),format.raw/*272.48*/("""_"""),_display_(/*272.50*/lista1/*272.56*/.get(1)),format.raw/*272.63*/("""">0.00</div>
										<input type="hidden" name="exceso[]"   id="exceso_"""),_display_(/*273.62*/lista1/*273.68*/.get(0)),format.raw/*273.75*/("""_"""),_display_(/*273.77*/lista1/*273.83*/.get(1)),format.raw/*273.90*/("""" value="0">
										<input type="hidden" name="estados[]"   id="estados_"""),_display_(/*274.64*/lista1/*274.70*/.get(0)),format.raw/*274.77*/("""_"""),_display_(/*274.79*/lista1/*274.85*/.get(1)),format.raw/*274.92*/("""">
										<input type="hidden" name="reparaciones[]"   id="reparaciones_"""),_display_(/*275.74*/lista1/*275.80*/.get(0)),format.raw/*275.87*/("""_"""),_display_(/*275.89*/lista1/*275.95*/.get(1)),format.raw/*275.102*/("""">
										<input type="hidden" name="cantCliente[]"   id="cantCliente_"""),_display_(/*276.72*/lista1/*276.78*/.get(0)),format.raw/*276.85*/("""_"""),_display_(/*276.87*/lista1/*276.93*/.get(1)),format.raw/*276.100*/("""" value="0">
										<input type="hidden" name="esVenta[]"   id="esVenta_"""),_display_(/*277.64*/lista1/*277.70*/.get(0)),format.raw/*277.77*/("""_"""),_display_(/*277.79*/lista1/*277.85*/.get(1)),format.raw/*277.92*/("""" value="0">
										<input type="hidden" name="esNuevo[]"   id="esNuevo_"""),_display_(/*278.64*/lista1/*278.70*/.get(0)),format.raw/*278.77*/("""_"""),_display_(/*278.79*/lista1/*278.85*/.get(1)),format.raw/*278.92*/("""" value="0">
									</td>
								</TR>
				 			""")))}),format.raw/*281.10*/("""
						"""),format.raw/*282.7*/("""</tbody>
					</table>
				</div>
			</div>

			<div class="noprint" align="left">
				<div class="row justify-content-md-center" >

					<input type="hidden" id="cierreVentaBaja" name="cierreVentaBaja" value="">
					<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
						<input type="submit"  value='Hacer Venta' onclick="$('#cierreVentaBaja').val('VENTA')" class="btn btn-info btn-sm rounded-pill btn-block" >
					</div>
					<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
						<input id="cancelar" type="button"  value="Cancelar" class="btn btn-warning btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
					</div>
					<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
						<input type="submit"  value='Enviar a Baja' onclick="$('#cierreVentaBaja').val('BAJA')" class="btn btn-info btn-sm rounded-pill btn-block">
					</div>
				</div>
			</div>

		</div>

	</form>




	<div id='modalVerOt' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable' style="max-width: 80% !important;" role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>"""),_display_(/*314.31*/mapDiccionario/*314.45*/.get("ORDEN_DE_TRABAJO")),format.raw/*314.69*/("""</h5>
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



""")))}),format.raw/*333.2*/("""


"""),format.raw/*336.1*/("""<style>
	#tablaPrincipal td:nth-child(2) """),format.raw/*337.34*/("""{"""),format.raw/*337.35*/(""" """),format.raw/*337.36*/("""text-align: center; """),format.raw/*337.56*/("""}"""),format.raw/*337.57*/("""
	"""),format.raw/*338.2*/("""#tablaPrincipal td:nth-child(3) """),format.raw/*338.34*/("""{"""),format.raw/*338.35*/(""" """),format.raw/*338.36*/("""text-align: center; """),format.raw/*338.56*/("""}"""),format.raw/*338.57*/("""
	"""),format.raw/*339.2*/("""#tablaPrincipal td:nth-child(4) """),format.raw/*339.34*/("""{"""),format.raw/*339.35*/(""" """),format.raw/*339.36*/("""text-align: center; """),format.raw/*339.56*/("""}"""),format.raw/*339.57*/("""
	"""),format.raw/*340.2*/("""#tablaPrincipal td:nth-child(5) """),format.raw/*340.34*/("""{"""),format.raw/*340.35*/(""" """),format.raw/*340.36*/("""text-align: left; """),format.raw/*340.54*/("""}"""),format.raw/*340.55*/("""
	"""),format.raw/*341.2*/("""#tablaPrincipal td:nth-child(7) """),format.raw/*341.34*/("""{"""),format.raw/*341.35*/(""" """),format.raw/*341.36*/("""text-align: right; """),format.raw/*341.55*/("""}"""),format.raw/*341.56*/("""
	"""),format.raw/*342.2*/("""#tablaPrincipal td:nth-child(8) """),format.raw/*342.34*/("""{"""),format.raw/*342.35*/(""" """),format.raw/*342.36*/("""text-align: right; """),format.raw/*342.55*/("""}"""),format.raw/*342.56*/("""
	"""),format.raw/*343.2*/("""#tablaPrincipal td:nth-child(9) """),format.raw/*343.34*/("""{"""),format.raw/*343.35*/(""" """),format.raw/*343.36*/("""text-align: center; """),format.raw/*343.56*/("""}"""),format.raw/*343.57*/("""
	"""),format.raw/*344.2*/("""#tablaPrincipal td:nth-child(10) """),format.raw/*344.35*/("""{"""),format.raw/*344.36*/(""" """),format.raw/*344.37*/("""text-align: right; """),format.raw/*344.56*/("""}"""),format.raw/*344.57*/("""
	"""),format.raw/*345.2*/("""#tablaPrincipal td:nth-child(11) """),format.raw/*345.35*/("""{"""),format.raw/*345.36*/(""" """),format.raw/*345.37*/("""text-align: right; """),format.raw/*345.56*/("""}"""),format.raw/*345.57*/("""
	"""),format.raw/*346.2*/("""#tablaPrincipal td:nth-child(12) """),format.raw/*346.35*/("""{"""),format.raw/*346.36*/(""" """),format.raw/*346.37*/("""text-align: center; """),format.raw/*346.57*/("""}"""),format.raw/*346.58*/("""
	"""),format.raw/*347.2*/("""#tablaPrincipal td:nth-child(13) """),format.raw/*347.35*/("""{"""),format.raw/*347.36*/(""" """),format.raw/*347.37*/("""text-align: center; """),format.raw/*347.57*/("""}"""),format.raw/*347.58*/("""
"""),format.raw/*348.1*/("""</style>

<script type="text/javascript">

		let auxId_guia = 0;
		let auxId_transportista = 0;
		
		let esInterna = """"),_display_(/*355.21*/bodegaOrigen/*355.33*/.esInterna),format.raw/*355.43*/("""";
	


		

	let tblPrincipal;
	let listReparaNiv1;
	
	$(document).ready(function() """),format.raw/*364.31*/("""{"""),format.raw/*364.32*/("""
		"""),format.raw/*365.3*/("""$('#tablaPrincipal').DataTable("""),format.raw/*365.34*/("""{"""),format.raw/*365.35*/("""
		    	"""),format.raw/*366.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[15, 50, 100, 200, -1], [15, 50, 100, 200, "All"]],
		    	"order": [[ 4, "asc" ]],
		    	"language": """),format.raw/*369.20*/("""{"""),format.raw/*369.21*/("""
		        	"""),format.raw/*370.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*371.11*/("""}"""),format.raw/*371.12*/("""
		"""),format.raw/*372.3*/("""}"""),format.raw/*372.4*/(""" """),format.raw/*372.5*/(""");
		agregaComercialYcliente();
		calcular();
		document.getElementById('mostrarmostrar').style.display="block";
		if(esInterna != "1")"""),format.raw/*376.23*/("""{"""),format.raw/*376.24*/("""
			"""),format.raw/*377.4*/("""let verifica = document.querySelectorAll(".verificaStock");
			let sinNegativos = 0;
			for (let i = 0; i < verifica.length; i++) """),format.raw/*379.46*/("""{"""),format.raw/*379.47*/("""
				"""),format.raw/*380.5*/("""let aux = verifica[i].textContent.replace(/,/g,'')
				if(parseFloat(aux) < 0)"""),format.raw/*381.28*/("""{"""),format.raw/*381.29*/("""
					"""),format.raw/*382.6*/("""sinNegativos = 1;
				"""),format.raw/*383.5*/("""}"""),format.raw/*383.6*/("""
			"""),format.raw/*384.4*/("""}"""),format.raw/*384.5*/("""
			"""),format.raw/*385.4*/("""if(sinNegativos>0)"""),format.raw/*385.22*/("""{"""),format.raw/*385.23*/("""
				"""),format.raw/*386.5*/("""alertify.alert('El estado de los stock de """),_display_(/*386.48*/mapDiccionario/*386.62*/.get("BODEGA")),format.raw/*386.76*/(""": """),_display_(/*386.79*/bodegaOrigen/*386.91*/.getNombre()),format.raw/*386.103*/(""".- '
					+'Presenta dificultades, favor contactar a soporte Inqsol indicando el nombre de """),_display_(/*387.88*/mapDiccionario/*387.102*/.get("BODEGA")),format.raw/*387.116*/(""" """),format.raw/*387.117*/("""y que es un movimiento', function () """),format.raw/*387.154*/("""{"""),format.raw/*387.155*/("""
			     			"""),format.raw/*388.12*/("""location.href = "/home/";
			     	"""),format.raw/*389.10*/("""}"""),format.raw/*389.11*/(""");
			"""),format.raw/*390.4*/("""}"""),format.raw/*390.5*/("""
		"""),format.raw/*391.3*/("""}"""),format.raw/*391.4*/("""
	"""),format.raw/*392.2*/("""}"""),format.raw/*392.3*/(""");

		const agregaComercialYcliente = () =>"""),format.raw/*394.40*/("""{"""),format.raw/*394.41*/("""
			"""),format.raw/*395.4*/("""let formData = new FormData();
			formData.append('id_bodegaOrigen','"""),_display_(/*396.40*/bodegaOrigen/*396.52*/.getId()),format.raw/*396.60*/("""');
			formData.append('id_bodegaDestino','"""),_display_(/*397.41*/bodegaDestino/*397.54*/.getId()),format.raw/*397.62*/("""');

			if(""""),_display_(/*399.9*/comercial/*399.18*/.getId()),format.raw/*399.26*/("""" == "-1")"""),format.raw/*399.36*/("""{"""),format.raw/*399.37*/("""
				"""),format.raw/*400.5*/("""$.ajax("""),format.raw/*400.12*/("""{"""),format.raw/*400.13*/("""
					"""),format.raw/*401.6*/("""url: "/nameComercialAjax/",
					type: "POST",
					method: "POST",
					data: formData,
					cache: false,
					contentType: false,
					processData: false,
					success: function(respuesta)"""),format.raw/*408.34*/("""{"""),format.raw/*408.35*/("""
						"""),format.raw/*409.7*/("""$("#nameComercial").val(respuesta);
					"""),format.raw/*410.6*/("""}"""),format.raw/*410.7*/("""
				"""),format.raw/*411.5*/("""}"""),format.raw/*411.6*/(""");
			"""),format.raw/*412.4*/("""}"""),format.raw/*412.5*/("""

			"""),format.raw/*414.4*/("""$.ajax("""),format.raw/*414.11*/("""{"""),format.raw/*414.12*/("""
				"""),format.raw/*415.5*/("""url: "/nickNameClienteAjax/",
				type: "POST",
				method: "POST",
				data: formData,
				cache: false,
				contentType: false,
				processData: false,
				success: function(respuesta)"""),format.raw/*422.33*/("""{"""),format.raw/*422.34*/("""
					"""),format.raw/*423.6*/("""$("#nickCliente").val(respuesta);
				"""),format.raw/*424.5*/("""}"""),format.raw/*424.6*/("""
			"""),format.raw/*425.4*/("""}"""),format.raw/*425.5*/(""");
		"""),format.raw/*426.3*/("""}"""),format.raw/*426.4*/("""

	"""),format.raw/*428.2*/("""const verificarNumeroGuia = (value) =>"""),format.raw/*428.40*/("""{"""),format.raw/*428.41*/("""
		"""),format.raw/*429.3*/("""if(value.trim()=="")"""),format.raw/*429.23*/("""{"""),format.raw/*429.24*/("""
			"""),format.raw/*430.4*/("""$("#numeroGuia").val(""""),_display_(/*430.27*/nuevoNumeroGuia),format.raw/*430.42*/("""");
		"""),format.raw/*431.3*/("""}"""),format.raw/*431.4*/("""else"""),format.raw/*431.8*/("""{"""),format.raw/*431.9*/("""
			"""),format.raw/*432.4*/("""let aux = '"""),_display_(/*432.16*/nuevoNumeroGuia),format.raw/*432.31*/("""';
			var formData = new FormData();
		  	formData.append('numeroGuia',value);
	        $.ajax("""),format.raw/*435.17*/("""{"""),format.raw/*435.18*/("""
	            """),format.raw/*436.14*/("""url: "/verificaNumeroGuiaAjax/",
	            type: "POST",
	            method: "POST",
	            data: formData,
	            cache: false,
	            contentType: false,
		     	processData: false,
		     	success: function(respuesta)"""),format.raw/*443.37*/("""{"""),format.raw/*443.38*/("""
		     		"""),format.raw/*444.10*/("""if(respuesta=="existe")"""),format.raw/*444.33*/("""{"""),format.raw/*444.34*/("""
		     			"""),format.raw/*445.11*/("""alertify.alert('El número de movimiento ya existe, intente con otro', function () """),format.raw/*445.93*/("""{"""),format.raw/*445.94*/("""
		     				"""),format.raw/*446.12*/("""$("#numeroGuia").val(""""),_display_(/*446.35*/nuevoNumeroGuia),format.raw/*446.50*/("""");
		     			"""),format.raw/*447.11*/("""}"""),format.raw/*447.12*/(""");
		     		"""),format.raw/*448.10*/("""}"""),format.raw/*448.11*/("""
		     		"""),format.raw/*449.10*/("""if(respuesta=="error")"""),format.raw/*449.32*/("""{"""),format.raw/*449.33*/("""
		     			"""),format.raw/*450.11*/("""alertify.alert(msgError, function () """),format.raw/*450.48*/("""{"""),format.raw/*450.49*/("""
			     			"""),format.raw/*451.12*/("""location.href = "/";
			     		"""),format.raw/*452.11*/("""}"""),format.raw/*452.12*/(""");
		     		"""),format.raw/*453.10*/("""}"""),format.raw/*453.11*/("""
		     	"""),format.raw/*454.9*/("""}"""),format.raw/*454.10*/("""
	        """),format.raw/*455.10*/("""}"""),format.raw/*455.11*/(""");
		"""),format.raw/*456.3*/("""}"""),format.raw/*456.4*/("""
	"""),format.raw/*457.2*/("""}"""),format.raw/*457.3*/("""
	
	"""),format.raw/*459.2*/("""const validarForm = () =>"""),format.raw/*459.27*/("""{"""),format.raw/*459.28*/("""
		"""),format.raw/*460.3*/("""$("#tablaPrincipal").dataTable().fnDestroy();
		let tabla = document.getElementById("tablaPrincipal");
		let validaCantidades = 0;
		for(i=1; i<tabla.rows.length; i++)"""),format.raw/*463.37*/("""{"""),format.raw/*463.38*/("""
			"""),format.raw/*464.4*/("""let cantidad = $(".cantidad",tabla.rows[i].cells[10]).val();
			cantidad = cantidad.replace(/,/g,'');
			validaCantidades += parseFloat(cantidad);
		"""),format.raw/*467.3*/("""}"""),format.raw/*467.4*/("""
		"""),format.raw/*468.3*/("""if(validaCantidades > 0)"""),format.raw/*468.27*/("""{"""),format.raw/*468.28*/("""
			"""),format.raw/*469.4*/("""for(i=1; i<tabla.rows.length-1; i++)"""),format.raw/*469.40*/("""{"""),format.raw/*469.41*/("""
				"""),format.raw/*470.5*/("""let cantidad = $(".cantidad",tabla.rows[i].cells[10]).val();
				cantidad = cantidad.replace(/,/g,'');
				let total = parseFloat(cantidad);
				if(total <= 0 )"""),format.raw/*473.20*/("""{"""),format.raw/*473.21*/("""
					"""),format.raw/*474.6*/("""tabla.deleteRow(i);
					i--;
				"""),format.raw/*476.5*/("""}"""),format.raw/*476.6*/("""
			"""),format.raw/*477.4*/("""}"""),format.raw/*477.5*/("""
			"""),format.raw/*478.4*/("""document.getElementById('enCarga').style.display="block";
			return(true);
		"""),format.raw/*480.3*/("""}"""),format.raw/*480.4*/("""else"""),format.raw/*480.8*/("""{"""),format.raw/*480.9*/("""
			"""),format.raw/*481.4*/("""alertify.alert('NO PUEDE GRABAR UN MOVIMIENTO SIN MOVER EQUIPOS', function () """),format.raw/*481.82*/("""{"""),format.raw/*481.83*/("""
				"""),format.raw/*482.5*/("""return(false);
			"""),format.raw/*483.4*/("""}"""),format.raw/*483.5*/(""");
		"""),format.raw/*484.3*/("""}"""),format.raw/*484.4*/("""
	"""),format.raw/*485.2*/("""}"""),format.raw/*485.3*/("""
	
	"""),format.raw/*487.2*/("""let cantAux = 0;
	const verificaCantidad = (id_equipo, id_cotizacion, stock, cantidad, KG, M2, largo) =>"""),format.raw/*488.88*/("""{"""),format.raw/*488.89*/("""
		"""),format.raw/*489.3*/("""let acum = 0;
		let exce = $("#exceso_"+id_equipo+"_"+id_cotizacion).val();
		exce = exce.replace(/,/g,'');
		
		if(auxBueno == 999)"""),format.raw/*493.22*/("""{"""),format.raw/*493.23*/("""
			"""),format.raw/*494.4*/("""for(let i=0; i<largo; i++)"""),format.raw/*494.30*/("""{"""),format.raw/*494.31*/("""
				"""),format.raw/*495.5*/("""if( ! (i == auxIndex && auxBueno == 999))"""),format.raw/*495.46*/("""{"""),format.raw/*495.47*/("""
					"""),format.raw/*496.6*/("""let auxAcum = $("#estado_"+id_equipo+"_"+id_cotizacion+"_"+i).val();
					auxAcum = auxAcum.replace(/,/g,'');
					acum += parseFloat(auxAcum);
				"""),format.raw/*499.5*/("""}"""),format.raw/*499.6*/("""
			"""),format.raw/*500.4*/("""}"""),format.raw/*500.5*/("""
		"""),format.raw/*501.3*/("""}"""),format.raw/*501.4*/("""else if('"""),_display_(/*501.14*/mapDiccionario/*501.28*/.get("nEmpresa")),format.raw/*501.44*/("""' == "HOHE" && ( auxBueno == 2 || auxBueno == 3 ) )"""),format.raw/*501.95*/("""{"""),format.raw/*501.96*/("""
			"""),format.raw/*502.4*/("""for(let i=0; i<largo; i++)"""),format.raw/*502.30*/("""{"""),format.raw/*502.31*/("""
				"""),format.raw/*503.5*/("""if( ! (i == auxIndex && (auxBueno == 2 || auxBueno == 3 )))"""),format.raw/*503.64*/("""{"""),format.raw/*503.65*/("""
					"""),format.raw/*504.6*/("""let auxAcum = $("#estado_"+id_equipo+"_"+id_cotizacion+"_"+i).val();
					auxAcum = auxAcum.replace(/,/g,'');
					acum += parseFloat(auxAcum);
				"""),format.raw/*507.5*/("""}"""),format.raw/*507.6*/("""
			"""),format.raw/*508.4*/("""}"""),format.raw/*508.5*/("""
		
		"""),format.raw/*510.3*/("""}"""),format.raw/*510.4*/("""else"""),format.raw/*510.8*/("""{"""),format.raw/*510.9*/("""
			"""),format.raw/*511.4*/("""for(let i=0; i<largo; i++)"""),format.raw/*511.30*/("""{"""),format.raw/*511.31*/("""
				"""),format.raw/*512.5*/("""acum += parseFloat($("#estado_"+id_equipo+"_"+id_cotizacion+"_"+i).val());
			"""),format.raw/*513.4*/("""}"""),format.raw/*513.5*/("""
		"""),format.raw/*514.3*/("""}"""),format.raw/*514.4*/("""
		
		"""),format.raw/*516.3*/("""if(parseFloat(acum) > (parseFloat(cantidad)))"""),format.raw/*516.48*/("""{"""),format.raw/*516.49*/("""
			"""),format.raw/*517.4*/("""let auxCant1 = cantAux;
			alertify.alert('La cantidad no puede ser menor a la suma de estados, modifique primero los estados', function () """),format.raw/*518.117*/("""{"""),format.raw/*518.118*/("""
				"""),format.raw/*519.5*/("""$("#cantidad_"+id_equipo+"_"+id_cotizacion).val(formatStandar(auxCant1,2));
     		"""),format.raw/*520.8*/("""}"""),format.raw/*520.9*/(""");
		"""),format.raw/*521.3*/("""}"""),format.raw/*521.4*/("""else"""),format.raw/*521.8*/("""{"""),format.raw/*521.9*/("""
			"""),format.raw/*522.4*/("""stock = stock.replace(/,/g,'');
			KG = KG.replace(/,/g,'');
			M2 = M2.replace(/,/g,'');
			let saldo = parseFloat(stock)-parseFloat(cantidad);
			
			if(saldo >= 0)"""),format.raw/*527.18*/("""{"""),format.raw/*527.19*/("""
				"""),format.raw/*528.5*/("""$("#stock_"+id_equipo+"_"+id_cotizacion).text(formatStandar(saldo,2));
				$("#cantidad_"+id_equipo+"_"+id_cotizacion).val(formatStandar(cantidad,2));
				$("#exceso_"+id_equipo+"_"+id_cotizacion).val(formatStandar("0"));
			"""),format.raw/*531.4*/("""}"""),format.raw/*531.5*/("""else"""),format.raw/*531.9*/("""{"""),format.raw/*531.10*/("""
				"""),format.raw/*532.5*/("""if('"""),_display_(/*532.10*/bodegaOrigen/*532.22*/.getEsInterna()),format.raw/*532.37*/("""' == '1')"""),format.raw/*532.46*/("""{"""),format.raw/*532.47*/("""
					"""),format.raw/*533.6*/("""let auxCant2 = cantAux;
					alertify.alert('La cantidad no puede ser mayor que el stock disponible', function () """),format.raw/*534.91*/("""{"""),format.raw/*534.92*/("""
						"""),format.raw/*535.7*/("""$("#cantidad_"+id_equipo+"_"+id_cotizacion).val(formatStandar(auxCant2,2));
		     		"""),format.raw/*536.10*/("""}"""),format.raw/*536.11*/(""");
				"""),format.raw/*537.5*/("""}"""),format.raw/*537.6*/("""else"""),format.raw/*537.10*/("""{"""),format.raw/*537.11*/("""
					"""),format.raw/*538.6*/("""$("#stock_"+id_equipo+"_"+id_cotizacion).text(formatStandar(0,2));
					$("#cantidad_"+id_equipo+"_"+id_cotizacion).val(formatStandar(stock,2));
					$("#exceso_"+id_equipo+"_"+id_cotizacion).val(formatStandar(saldo*-1,2));
					cantidad = stock;
				"""),format.raw/*542.5*/("""}"""),format.raw/*542.6*/("""
			"""),format.raw/*543.4*/("""}"""),format.raw/*543.5*/("""
			
			"""),format.raw/*545.4*/("""if(auxBueno == 999)"""),format.raw/*545.23*/("""{"""),format.raw/*545.24*/("""
				"""),format.raw/*546.5*/("""let difB = parseFloat(cantidad) - parseFloat(acum);
				$("#estado_"+id_equipo+"_"+id_cotizacion+"_"+auxIndex).val(formatStandar(difB,2));
				
				let aux = $("#estados_"+id_equipo+"_"+id_cotizacion).val();
				let niv1 = aux.split(";");
				let flag = true;
				let buscar = "";
				for(i=0; i<niv1.length && flag; i++)"""),format.raw/*553.41*/("""{"""),format.raw/*553.42*/("""
					"""),format.raw/*554.6*/("""let niv2 = niv1[i].split(":");
					if(niv2[0] == auxBueno)"""),format.raw/*555.29*/("""{"""),format.raw/*555.30*/("""
						"""),format.raw/*556.7*/("""buscar = auxBueno+":"+niv2[1]+";";
						flag = false;
					"""),format.raw/*558.6*/("""}"""),format.raw/*558.7*/("""
				"""),format.raw/*559.5*/("""}"""),format.raw/*559.6*/("""
				
				"""),format.raw/*561.5*/("""if(flag)"""),format.raw/*561.13*/("""{"""),format.raw/*561.14*/("""
					"""),format.raw/*562.6*/("""aux += auxBueno+":"+parseFloat(difB)+";";
					$("#estados_"+id_equipo+"_"+id_cotizacion).val(aux);
				"""),format.raw/*564.5*/("""}"""),format.raw/*564.6*/("""else"""),format.raw/*564.10*/("""{"""),format.raw/*564.11*/("""
					"""),format.raw/*565.6*/("""aux = aux.replace(buscar, auxBueno+":"+parseFloat(difB)+";");
					$("#estados_"+id_equipo+"_"+id_cotizacion).val(aux);
				"""),format.raw/*567.5*/("""}"""),format.raw/*567.6*/("""
				
			"""),format.raw/*569.4*/("""}"""),format.raw/*569.5*/(""" """),format.raw/*569.6*/("""else if('"""),_display_(/*569.16*/mapDiccionario/*569.30*/.get("nEmpresa")),format.raw/*569.46*/("""' == "HOHE" && ( auxBueno == 2 || auxBueno == 3 ) )"""),format.raw/*569.97*/("""{"""),format.raw/*569.98*/("""
				"""),format.raw/*570.5*/("""let difB = parseFloat(cantidad) - parseFloat(acum);
				$("#estado_"+id_equipo+"_"+id_cotizacion+"_"+auxIndex).val(formatStandar(difB,2));
				
				let aux = $("#estados_"+id_equipo+"_"+id_cotizacion).val();
				let niv1 = aux.split(";");
				let flag = true;
				let buscar = "";
				for(i=0; i<niv1.length && flag; i++)"""),format.raw/*577.41*/("""{"""),format.raw/*577.42*/("""
					"""),format.raw/*578.6*/("""let niv2 = niv1[i].split(":");
					if(niv2[0] == auxBueno)"""),format.raw/*579.29*/("""{"""),format.raw/*579.30*/("""
						"""),format.raw/*580.7*/("""buscar = auxBueno+":"+niv2[1]+";";
						flag = false;
					"""),format.raw/*582.6*/("""}"""),format.raw/*582.7*/("""
				"""),format.raw/*583.5*/("""}"""),format.raw/*583.6*/("""
				
				"""),format.raw/*585.5*/("""if(flag)"""),format.raw/*585.13*/("""{"""),format.raw/*585.14*/("""
					"""),format.raw/*586.6*/("""aux += auxBueno+":"+parseFloat(difB)+";";
					$("#estados_"+id_equipo+"_"+id_cotizacion).val(aux);
				"""),format.raw/*588.5*/("""}"""),format.raw/*588.6*/("""else"""),format.raw/*588.10*/("""{"""),format.raw/*588.11*/("""
					"""),format.raw/*589.6*/("""aux = aux.replace(buscar, auxBueno+":"+parseFloat(difB)+";");
					$("#estados_"+id_equipo+"_"+id_cotizacion).val(aux);
				"""),format.raw/*591.5*/("""}"""),format.raw/*591.6*/("""
			"""),format.raw/*592.4*/("""}"""),format.raw/*592.5*/("""
			
			"""),format.raw/*594.4*/("""let totKg = parseFloat(KG) * parseFloat(cantidad);
			let totM2 = parseFloat(M2) * parseFloat(cantidad);
			$("#KG_"+id_equipo+"_"+id_cotizacion).text(formatStandar(totKg,2));
			$("#M2_"+id_equipo+"_"+id_cotizacion).text(formatStandar(totM2,2));

		"""),format.raw/*599.3*/("""}"""),format.raw/*599.4*/("""
	"""),format.raw/*600.2*/("""}"""),format.raw/*600.3*/("""

	"""),format.raw/*602.2*/("""let extArray = new Array(".gif", ".jpg", ".png", ".jpeg", ".pdf", ".xls", ".doc", ".xlsx", ".docx", ".tar", ".zip", ".rar");
	const LimitAttach = (form, file) => """),format.raw/*603.38*/("""{"""),format.raw/*603.39*/("""
		"""),format.raw/*604.3*/("""if (!file) return;
		const $inputArchivos = document.querySelector("#docAdjunto");
		const archivosParaSubir = $inputArchivos.files;
		let tamanio = 0;
		let allowSubmit = false;
		for(let i=0; i<archivosParaSubir.length; i++)"""),format.raw/*609.48*/("""{"""),format.raw/*609.49*/("""
			"""),format.raw/*610.4*/("""tamanio += archivosParaSubir[i].size;
			let nombre = archivosParaSubir[i].name;
			let extencion = nombre.substring(nombre.lastIndexOf(".")).toLowerCase();
			for (let j = 0; j < extArray.length; j++) """),format.raw/*613.46*/("""{"""),format.raw/*613.47*/("""
				"""),format.raw/*614.5*/("""if (extArray[j] == extencion) """),format.raw/*614.35*/("""{"""),format.raw/*614.36*/(""" 
					"""),format.raw/*615.6*/("""allowSubmit = true;
				"""),format.raw/*616.5*/("""}"""),format.raw/*616.6*/("""
			"""),format.raw/*617.4*/("""}"""),format.raw/*617.5*/("""
			"""),format.raw/*618.4*/("""if(allowSubmit && i < archivosParaSubir.length -1)"""),format.raw/*618.54*/("""{"""),format.raw/*618.55*/("""
				"""),format.raw/*619.5*/("""allowSubmit = false;
			"""),format.raw/*620.4*/("""}"""),format.raw/*620.5*/("""
		"""),format.raw/*621.3*/("""}"""),format.raw/*621.4*/("""
		"""),format.raw/*622.3*/("""if (allowSubmit) """),format.raw/*622.20*/("""{"""),format.raw/*622.21*/("""
			"""),format.raw/*623.4*/("""if(tamanio > 200000000)"""),format.raw/*623.27*/("""{"""),format.raw/*623.28*/("""
				"""),format.raw/*624.5*/("""alert("Se permite máximo subir 200 MB,"
				+" lo que se intenta subir pesa: "+Math.round(tamanio/10000)/100+" MB");
				form.docAdjunto.value="";
			"""),format.raw/*627.4*/("""}"""),format.raw/*627.5*/("""else"""),format.raw/*627.9*/("""{"""),format.raw/*627.10*/("""
				"""),format.raw/*628.5*/("""alert("Documento subido con éxito");
				$("#txtBtn").text("Cambiar Documento");
			"""),format.raw/*630.4*/("""}"""),format.raw/*630.5*/("""
		"""),format.raw/*631.3*/("""}"""),format.raw/*631.4*/("""else"""),format.raw/*631.8*/("""{"""),format.raw/*631.9*/("""
			"""),format.raw/*632.4*/("""alert("Se permiten únicamente archivos con la extensión: "
			+ (extArray.join("  ")) + "\nPor favor, seleccione solo archivos con extensiones permitidas "
			+ "e intente de nuevo.");
			form.docAdjunto.value="";
		"""),format.raw/*636.3*/("""}"""),format.raw/*636.4*/("""
	"""),format.raw/*637.2*/("""}"""),format.raw/*637.3*/("""

	"""),format.raw/*639.2*/("""function calcular() """),format.raw/*639.22*/("""{"""),format.raw/*639.23*/("""
		"""),format.raw/*640.3*/("""let codigos = document.querySelectorAll(".codigo");
		let cantidades = document.querySelectorAll(".cantidad");
		let kgs = document.querySelectorAll(".kgUnit");
		let m2s = document.querySelectorAll(".m2Unit");
		let KGs = document.querySelectorAll(".KG");
		let M2s = document.querySelectorAll(".M2");
		for (let i = 0; i < codigos.length; i++) """),format.raw/*646.44*/("""{"""),format.raw/*646.45*/("""
			"""),format.raw/*647.4*/("""let cant = cantidades[i].value.replace(/,/g,'');
			let kg = kgs[i].textContent.replace(/,/g,'');
			let m2 = m2s[i].textContent.replace(/,/g,'');
			KGs[i].textContent = formatStandar(parseFloat(cant)*parseFloat(kg),2);
			M2s[i].textContent = formatStandar(parseFloat(cant)*parseFloat(m2),2);
		"""),format.raw/*652.3*/("""}"""),format.raw/*652.4*/("""
	"""),format.raw/*653.2*/("""}"""),format.raw/*653.3*/("""
	
	"""),format.raw/*655.2*/("""const verOt = (id_ot) => """),format.raw/*655.27*/("""{"""),format.raw/*655.28*/("""
		"""),format.raw/*656.3*/("""var formData = new FormData();
	  	formData.append('id_ot',id_ot);
        $.ajax("""),format.raw/*658.16*/("""{"""),format.raw/*658.17*/("""
            """),format.raw/*659.13*/("""url: "/verOtAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*666.36*/("""{"""),format.raw/*666.37*/("""
	     		"""),format.raw/*667.9*/("""document.getElementById('mostrarLaOt').innerHTML = respuesta;
	     		$('#modalVerOt').modal('show');
	     	"""),format.raw/*669.8*/("""}"""),format.raw/*669.9*/("""
        """),format.raw/*670.9*/("""}"""),format.raw/*670.10*/(""");
	"""),format.raw/*671.2*/("""}"""),format.raw/*671.3*/("""

	
"""),format.raw/*674.1*/("""</script>


		
		
	
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
                  SOURCE: app/viewsMnuMovimientos/cierreProyectoDet.scala.html
                  HASH: b57affc848416c1df366e0aef2633141f8e1818d
                  MATRIX: 1232->1|1825->501|1853->504|1869->512|1908->514|1938->518|2007->566|2037->569|2190->696|2235->720|2264->723|2305->743|2335->746|2589->974|2763->1126|2794->1130|3353->1662|3374->1674|3403->1682|3506->1758|3527->1770|3572->1794|3724->1919|3746->1932|3776->1940|4150->2286|4190->2287|4231->2300|4388->2430|4424->2445|4506->2508|4545->2509|4586->2522|4743->2652|4779->2667|4987->2844|5025->2854|6129->3931|6146->3939|6176->3947|6463->4207|6480->4215|6513->4227|6987->4673|7010->4686|7050->4704|7490->5116|7510->5126|7563->5157|7593->5159|7613->5169|7665->5198|7698->5201|7729->5202|7766->5210|7792->5213|7857->5248|7888->5249|7919->5250|7954->5255|7985->5256|8048->5289|8079->5290|8127->5310|8152->5313|8599->5732|8619->5742|8674->5775|8704->5777|8724->5787|8778->5818|8818->5829|8844->5832|8894->5854|8919->5857|9344->6254|9363->6263|9402->6280|10303->7153|10343->7154|10378->7161|10825->7564|10863->7574|11097->7780|11121->7794|11153->7804|11221->7844|11245->7858|11277->7868|11601->8165|11651->8198|11691->8199|11728->8208|11859->8311|11875->8317|11904->8324|11997->8389|12013->8395|12042->8402|12084->8416|12100->8422|12129->8429|12168->8439|12272->8515|12312->8516|12352->8527|12426->8581|12466->8582|12506->8593|12624->8683|12640->8689|12670->8697|12799->8798|12815->8804|12845->8812|12946->8881|12986->8892|13087->8965|13106->8974|13158->9004|13204->9022|13220->9028|13250->9036|13289->9046|13391->9120|13431->9121|13471->9132|13545->9186|13585->9187|13625->9198|13686->9231|13702->9237|13731->9244|13779->9260|13819->9271|13918->9342|13934->9348|13963->9355|14018->9381|14035->9387|14065->9394|14189->9490|14205->9496|14234->9503|14269->9509|14286->9515|14316->9522|14410->9588|14426->9594|14455->9601|14545->9663|14561->9669|14590->9676|14666->9724|14682->9730|14711->9737|14834->9832|14850->9838|14879->9845|14909->9847|14925->9853|14954->9860|14985->9863|15001->9869|15030->9876|15296->10114|15312->10120|15341->10127|15371->10129|15387->10135|15416->10142|15466->10164|15482->10170|15511->10177|15764->10402|15780->10408|15809->10415|15841->10419|15857->10425|15886->10432|15918->10436|15935->10442|15965->10449|16004->10459|16021->10465|16051->10472|16084->10476|16101->10482|16131->10489|16164->10493|16189->10507|16219->10514|16350->10617|16366->10623|16395->10630|16425->10632|16441->10638|16470->10645|16590->10737|16606->10743|16635->10750|16665->10752|16681->10758|16710->10765|16812->10839|16828->10845|16857->10852|16887->10854|16903->10860|16932->10867|17036->10943|17052->10949|17081->10956|17111->10958|17127->10964|17156->10971|17260->11047|17276->11053|17305->11060|17335->11062|17351->11068|17381->11075|17483->11149|17499->11155|17528->11162|17558->11164|17574->11170|17604->11177|17708->11253|17724->11259|17753->11266|17783->11268|17799->11274|17828->11281|17932->11357|17948->11363|17977->11370|18007->11372|18023->11378|18052->11385|18135->11436|18170->11443|19389->12634|19413->12648|19459->12672|20006->13188|20037->13191|20107->13232|20137->13233|20167->13234|20216->13254|20246->13255|20276->13257|20337->13289|20367->13290|20397->13291|20446->13311|20476->13312|20506->13314|20567->13346|20597->13347|20627->13348|20676->13368|20706->13369|20736->13371|20797->13403|20827->13404|20857->13405|20904->13423|20934->13424|20964->13426|21025->13458|21055->13459|21085->13460|21133->13479|21163->13480|21193->13482|21254->13514|21284->13515|21314->13516|21362->13535|21392->13536|21422->13538|21483->13570|21513->13571|21543->13572|21592->13592|21622->13593|21652->13595|21714->13628|21744->13629|21774->13630|21822->13649|21852->13650|21882->13652|21944->13685|21974->13686|22004->13687|22052->13706|22082->13707|22112->13709|22174->13742|22204->13743|22234->13744|22283->13764|22313->13765|22343->13767|22405->13800|22435->13801|22465->13802|22514->13822|22544->13823|22573->13824|22720->13943|22742->13955|22774->13965|22886->14048|22916->14049|22947->14052|23007->14083|23037->14084|23073->14092|23248->14238|23278->14239|23319->14251|23426->14329|23456->14330|23487->14333|23516->14334|23545->14335|23709->14470|23739->14471|23771->14475|23930->14605|23960->14606|23993->14611|24100->14689|24130->14690|24164->14696|24214->14718|24243->14719|24275->14723|24304->14724|24336->14728|24383->14746|24413->14747|24446->14752|24517->14795|24541->14809|24577->14823|24608->14826|24630->14838|24665->14850|24785->14942|24810->14956|24847->14970|24878->14971|24945->15008|24976->15009|25017->15021|25081->15056|25111->15057|25145->15063|25174->15064|25205->15067|25234->15068|25264->15070|25293->15071|25365->15114|25395->15115|25427->15119|25525->15189|25547->15201|25577->15209|25649->15253|25672->15266|25702->15274|25742->15287|25761->15296|25791->15304|25830->15314|25860->15315|25893->15320|25929->15327|25959->15328|25993->15334|26213->15525|26243->15526|26278->15533|26347->15574|26376->15575|26409->15580|26438->15581|26472->15587|26501->15588|26534->15593|26570->15600|26600->15601|26633->15606|26848->15792|26878->15793|26912->15799|26978->15837|27007->15838|27039->15842|27068->15843|27101->15848|27130->15849|27161->15852|27228->15890|27258->15891|27289->15894|27338->15914|27368->15915|27400->15919|27451->15942|27488->15957|27522->15963|27551->15964|27583->15968|27612->15969|27644->15973|27684->15985|27721->16000|27845->16095|27875->16096|27918->16110|28189->16352|28219->16353|28258->16363|28310->16386|28340->16387|28380->16398|28491->16480|28521->16481|28562->16493|28613->16516|28650->16531|28693->16545|28723->16546|28764->16558|28794->16559|28833->16569|28884->16591|28914->16592|28954->16603|29020->16640|29050->16641|29091->16653|29151->16684|29181->16685|29222->16697|29252->16698|29289->16707|29319->16708|29358->16718|29388->16719|29421->16724|29450->16725|29480->16727|29509->16728|29541->16732|29595->16757|29625->16758|29656->16761|29852->16928|29882->16929|29914->16933|30091->17082|30120->17083|30151->17086|30204->17110|30234->17111|30266->17115|30331->17151|30361->17152|30394->17157|30583->17317|30613->17318|30647->17324|30709->17358|30738->17359|30770->17363|30799->17364|30831->17368|30936->17445|30965->17446|30997->17450|31026->17451|31058->17455|31165->17533|31195->17534|31228->17539|31274->17557|31303->17558|31336->17563|31365->17564|31395->17566|31424->17567|31456->17571|31589->17675|31619->17676|31650->17679|31811->17811|31841->17812|31873->17816|31928->17842|31958->17843|31991->17848|32061->17889|32091->17890|32125->17896|32301->18044|32330->18045|32362->18049|32391->18050|32422->18053|32451->18054|32489->18064|32513->18078|32551->18094|32631->18145|32661->18146|32693->18150|32748->18176|32778->18177|32811->18182|32899->18241|32929->18242|32963->18248|33139->18396|33168->18397|33200->18401|33229->18402|33263->18408|33292->18409|33324->18413|33353->18414|33385->18418|33440->18444|33470->18445|33503->18450|33609->18528|33638->18529|33669->18532|33698->18533|33732->18539|33806->18584|33836->18585|33868->18589|34038->18729|34069->18730|34102->18735|34213->18818|34242->18819|34275->18824|34304->18825|34336->18829|34365->18830|34397->18834|34592->19000|34622->19001|34655->19006|34908->19231|34937->19232|34969->19236|34999->19237|35032->19242|35065->19247|35087->19259|35124->19274|35162->19283|35192->19284|35226->19290|35369->19404|35399->19405|35434->19412|35548->19497|35578->19498|35613->19505|35642->19506|35675->19510|35705->19511|35739->19517|36018->19768|36047->19769|36079->19773|36108->19774|36144->19782|36192->19801|36222->19802|36255->19807|36605->20128|36635->20129|36669->20135|36757->20194|36787->20195|36822->20202|36910->20262|36939->20263|36972->20268|37001->20269|37039->20279|37076->20287|37106->20288|37140->20294|37272->20398|37301->20399|37334->20403|37364->20404|37398->20410|37550->20534|37579->20535|37616->20544|37645->20545|37674->20546|37712->20556|37736->20570|37774->20586|37854->20637|37884->20638|37917->20643|38267->20964|38297->20965|38331->20971|38419->21030|38449->21031|38484->21038|38572->21098|38601->21099|38634->21104|38663->21105|38701->21115|38738->21123|38768->21124|38802->21130|38934->21234|38963->21235|38996->21239|39026->21240|39060->21246|39212->21370|39241->21371|39273->21375|39302->21376|39338->21384|39616->21634|39645->21635|39675->21637|39704->21638|39735->21641|39926->21803|39956->21804|39987->21807|40242->22033|40272->22034|40304->22038|40535->22240|40565->22241|40598->22246|40657->22276|40687->22277|40722->22284|40774->22308|40803->22309|40835->22313|40864->22314|40896->22318|40975->22368|41005->22369|41038->22374|41090->22398|41119->22399|41150->22402|41179->22403|41210->22406|41256->22423|41286->22424|41318->22428|41370->22451|41400->22452|41433->22457|41611->22607|41640->22608|41672->22612|41702->22613|41735->22618|41847->22702|41876->22703|41907->22706|41936->22707|41968->22711|41997->22712|42029->22716|42273->22932|42302->22933|42332->22935|42361->22936|42392->22939|42441->22959|42471->22960|42502->22963|42877->23309|42907->23310|42939->23314|43264->23611|43293->23612|43323->23614|43352->23615|43384->23619|43438->23644|43468->23645|43499->23648|43610->23730|43640->23731|43682->23744|43933->23966|43963->23967|44000->23976|44137->24085|44166->24086|44203->24095|44233->24096|44265->24100|44294->24101|44326->24105
                  LINES: 28->1|38->7|40->9|40->9|40->9|42->11|42->11|44->13|46->15|46->15|47->16|47->16|49->18|57->26|57->26|58->27|68->37|68->37|68->37|70->39|70->39|70->39|73->42|73->42|73->42|80->49|80->49|81->50|84->53|84->53|87->56|87->56|88->57|91->60|91->60|96->65|97->66|123->92|123->92|123->92|129->98|129->98|129->98|140->109|140->109|140->109|153->122|153->122|153->122|153->122|153->122|153->122|153->122|153->122|153->122|153->122|153->122|153->122|153->122|153->122|153->122|153->122|153->122|154->123|154->123|167->136|167->136|167->136|167->136|167->136|167->136|167->136|167->136|168->137|168->137|182->151|182->151|182->151|215->184|215->184|216->185|224->193|229->198|233->202|233->202|233->202|234->203|234->203|234->203|248->217|248->217|248->217|249->218|251->220|251->220|251->220|252->221|252->221|252->221|253->222|253->222|253->222|254->223|256->225|256->225|257->226|258->227|258->227|259->228|260->229|260->229|260->229|262->231|262->231|262->231|266->235|268->237|269->238|269->238|269->238|270->239|270->239|270->239|271->240|273->242|273->242|274->243|275->244|275->244|276->245|276->245|276->245|276->245|277->246|279->248|279->248|279->248|279->248|279->248|279->248|279->248|280->249|280->249|280->249|280->249|280->249|280->249|281->250|281->250|281->250|282->251|282->251|282->251|283->252|283->252|283->252|285->254|285->254|285->254|285->254|285->254|285->254|285->254|285->254|285->254|291->260|291->260|291->260|291->260|291->260|291->260|292->261|292->261|292->261|296->265|296->265|296->265|296->265|296->265|296->265|296->265|296->265|296->265|296->265|296->265|296->265|296->265|296->265|296->265|296->265|296->265|296->265|301->270|301->270|301->270|301->270|301->270|301->270|303->272|303->272|303->272|303->272|303->272|303->272|304->273|304->273|304->273|304->273|304->273|304->273|305->274|305->274|305->274|305->274|305->274|305->274|306->275|306->275|306->275|306->275|306->275|306->275|307->276|307->276|307->276|307->276|307->276|307->276|308->277|308->277|308->277|308->277|308->277|308->277|309->278|309->278|309->278|309->278|309->278|309->278|312->281|313->282|345->314|345->314|345->314|364->333|367->336|368->337|368->337|368->337|368->337|368->337|369->338|369->338|369->338|369->338|369->338|369->338|370->339|370->339|370->339|370->339|370->339|370->339|371->340|371->340|371->340|371->340|371->340|371->340|372->341|372->341|372->341|372->341|372->341|372->341|373->342|373->342|373->342|373->342|373->342|373->342|374->343|374->343|374->343|374->343|374->343|374->343|375->344|375->344|375->344|375->344|375->344|375->344|376->345|376->345|376->345|376->345|376->345|376->345|377->346|377->346|377->346|377->346|377->346|377->346|378->347|378->347|378->347|378->347|378->347|378->347|379->348|386->355|386->355|386->355|395->364|395->364|396->365|396->365|396->365|397->366|400->369|400->369|401->370|402->371|402->371|403->372|403->372|403->372|407->376|407->376|408->377|410->379|410->379|411->380|412->381|412->381|413->382|414->383|414->383|415->384|415->384|416->385|416->385|416->385|417->386|417->386|417->386|417->386|417->386|417->386|417->386|418->387|418->387|418->387|418->387|418->387|418->387|419->388|420->389|420->389|421->390|421->390|422->391|422->391|423->392|423->392|425->394|425->394|426->395|427->396|427->396|427->396|428->397|428->397|428->397|430->399|430->399|430->399|430->399|430->399|431->400|431->400|431->400|432->401|439->408|439->408|440->409|441->410|441->410|442->411|442->411|443->412|443->412|445->414|445->414|445->414|446->415|453->422|453->422|454->423|455->424|455->424|456->425|456->425|457->426|457->426|459->428|459->428|459->428|460->429|460->429|460->429|461->430|461->430|461->430|462->431|462->431|462->431|462->431|463->432|463->432|463->432|466->435|466->435|467->436|474->443|474->443|475->444|475->444|475->444|476->445|476->445|476->445|477->446|477->446|477->446|478->447|478->447|479->448|479->448|480->449|480->449|480->449|481->450|481->450|481->450|482->451|483->452|483->452|484->453|484->453|485->454|485->454|486->455|486->455|487->456|487->456|488->457|488->457|490->459|490->459|490->459|491->460|494->463|494->463|495->464|498->467|498->467|499->468|499->468|499->468|500->469|500->469|500->469|501->470|504->473|504->473|505->474|507->476|507->476|508->477|508->477|509->478|511->480|511->480|511->480|511->480|512->481|512->481|512->481|513->482|514->483|514->483|515->484|515->484|516->485|516->485|518->487|519->488|519->488|520->489|524->493|524->493|525->494|525->494|525->494|526->495|526->495|526->495|527->496|530->499|530->499|531->500|531->500|532->501|532->501|532->501|532->501|532->501|532->501|532->501|533->502|533->502|533->502|534->503|534->503|534->503|535->504|538->507|538->507|539->508|539->508|541->510|541->510|541->510|541->510|542->511|542->511|542->511|543->512|544->513|544->513|545->514|545->514|547->516|547->516|547->516|548->517|549->518|549->518|550->519|551->520|551->520|552->521|552->521|552->521|552->521|553->522|558->527|558->527|559->528|562->531|562->531|562->531|562->531|563->532|563->532|563->532|563->532|563->532|563->532|564->533|565->534|565->534|566->535|567->536|567->536|568->537|568->537|568->537|568->537|569->538|573->542|573->542|574->543|574->543|576->545|576->545|576->545|577->546|584->553|584->553|585->554|586->555|586->555|587->556|589->558|589->558|590->559|590->559|592->561|592->561|592->561|593->562|595->564|595->564|595->564|595->564|596->565|598->567|598->567|600->569|600->569|600->569|600->569|600->569|600->569|600->569|600->569|601->570|608->577|608->577|609->578|610->579|610->579|611->580|613->582|613->582|614->583|614->583|616->585|616->585|616->585|617->586|619->588|619->588|619->588|619->588|620->589|622->591|622->591|623->592|623->592|625->594|630->599|630->599|631->600|631->600|633->602|634->603|634->603|635->604|640->609|640->609|641->610|644->613|644->613|645->614|645->614|645->614|646->615|647->616|647->616|648->617|648->617|649->618|649->618|649->618|650->619|651->620|651->620|652->621|652->621|653->622|653->622|653->622|654->623|654->623|654->623|655->624|658->627|658->627|658->627|658->627|659->628|661->630|661->630|662->631|662->631|662->631|662->631|663->632|667->636|667->636|668->637|668->637|670->639|670->639|670->639|671->640|677->646|677->646|678->647|683->652|683->652|684->653|684->653|686->655|686->655|686->655|687->656|689->658|689->658|690->659|697->666|697->666|698->667|700->669|700->669|701->670|701->670|702->671|702->671|705->674
                  -- GENERATED --
              */
          