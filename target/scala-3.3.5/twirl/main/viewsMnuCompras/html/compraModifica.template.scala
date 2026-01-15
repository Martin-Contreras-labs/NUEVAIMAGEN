
package viewsMnuCompras.html

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

object compraModifica extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template15[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Proveedor],List[tables.Equipo],List[tables.Moneda],List[List[String]],List[tables.Moneda],List[tables.Grupo],List[tables.Fabrica],List[tables.Unidad],tables.Factura,List[List[String]],List[tables.Sucursal],List[tables.Propiedad],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listProveedor: List[tables.Proveedor], listEquipo: List[tables.Equipo], listMoneda: List[tables.Moneda], listBodegas: List[List[String]], listMon: List[tables.Moneda],
listGrupos: List[tables.Grupo], listFabrica: List[tables.Fabrica], listUnidades: List[tables.Unidad], factura: tables.Factura, detalleFactura: List[List[String]],
listSucursales: List[tables.Sucursal], listPropiedad: List[tables.Propiedad]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*5.1*/("""
    	
"""),_display_(/*7.2*/main("")/*7.10*/ {_display_(Seq[Any](format.raw/*7.12*/("""

"""),_display_(/*9.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*9.50*/("""
	"""),format.raw/*10.2*/("""<form action="/compraModificaGraba/" enctype="multipart/form-data" method="POST" onsubmit="return validarForm();">
		<div id="mostrarmostrar" style="display:none;">
			"""),_display_(/*12.5*/barraTitulo(mapDiccionario, "MODIFICAR COMPRA Y/O ADQUISICION","(modificar inventarios)")),format.raw/*12.94*/("""
			"""),format.raw/*13.4*/("""<div class="row  justify-content-md-center">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<table class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<tr>
								<td  width="350px">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">"""),_display_(/*21.65*/mapDiccionario/*21.79*/.get("RUT")),format.raw/*21.90*/(""" """),format.raw/*21.91*/("""PROVEEDOR</span>
								  		</div>
								  		<input type="hidden" name="id_factura" value=""""),_display_(/*23.60*/factura/*23.67*/.getId()),format.raw/*23.75*/("""">
								  		<input type="hidden" name="numOcIConstruye" value="0">
								  		<input type="hidden" id="id_proveedor" name="id_proveedor" value=""""),_display_(/*25.80*/factura/*25.87*/.getId_proveedor()),format.raw/*25.105*/("""">
	  									<input type="text" class="form-control"
	  										id="rutProveedor"
	  										value=""""),_display_(/*28.22*/factura/*28.29*/.getRut()),format.raw/*28.38*/(""""
											style="background:white"
											onclick="$('#listaProveedor').modal('show');"
	  										readonly>
									</div>
								</td>
								<td>
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Nombre Proveedor</span>
								  		</div>
											<input type="text" class="form-control left"
												id="nombreProveedor"
												value=""""),_display_(/*41.21*/factura/*41.28*/.getNickNameProveedor()),format.raw/*41.51*/(""""
												style="background:white"
												onclick='$("#listaProveedor").modal("show")'
												required
												readonly>
									</div>
								</td>
								<td width="250px">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Nro.Documento</span>
								  		</div>
								  		<input type="text" class="form-control left"
								  				name="numeroFactura"
												id="numeroFactura"
												value = """"),_display_(/*56.23*/factura/*56.30*/.getNumero()),format.raw/*56.42*/(""""
												onkeydown="return ingresoInt(window.event)"
												onchange="if(value!='') verificarNumeroDocumento(value)"
												autocomplete="off"
												required>
									</div>
								</td>
								<td width="230px">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Fecha</span>
								  		</div>
								  		<input type="date" class="form-control center"
								  			name="fechaFactura" 
								  			id="fechaFactura"
								  			onblur="if(!limitaFecha(value,720,10)) value='"""),_display_(/*71.61*/utilities/*71.70*/.Fechas.AAMMDD(factura.getFecha())),format.raw/*71.104*/("""'; else modificaFactura('fecha',value);"
								  			value=""""),_display_(/*72.22*/utilities/*72.31*/.Fechas.AAMMDD(factura.getFecha())),format.raw/*72.65*/(""""
						        			required>
									</div>
								</td>
								<td style="text-align:center;" width="70px">
									<span class="btn btn-info btn-sm btn-file" style="font-size: 8px;">
										"""),_display_(if(factura.getFacturaPDF().equals("0"))/*78.51*/{_display_(Seq[Any](format.raw/*78.52*/("""
											"""),format.raw/*79.12*/("""<div id="txtBtn">Adjuntar</div>
										""")))}else/*80.16*/{_display_(Seq[Any](format.raw/*80.17*/("""
											"""),format.raw/*81.12*/("""<div id="txtBtn">Cambiar</div>
										""")))}),format.raw/*82.12*/("""
				    					"""),format.raw/*83.14*/("""<input type="file" id="docAdjunto" name="docAdjunto" onchange="LimitAttach(this.form, this.form.docAdjunto.value);">
									</span>
								</td>
								<td style="text-align:left;">
									"""),_display_(if(factura.getFacturaPDF().equals("0"))/*87.50*/{_display_(Seq[Any](format.raw/*87.51*/("""
										"""),format.raw/*88.11*/("""--
									""")))}else/*89.15*/{_display_(Seq[Any](format.raw/*89.16*/("""
										"""),format.raw/*90.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*90.52*/factura/*90.59*/.getFacturaPDF()),format.raw/*90.75*/("""')">
											<kbd style="background-color: #7F8C8D">doc</kbd>
										</a>
									""")))}),format.raw/*93.11*/("""
								"""),format.raw/*94.9*/("""</td>
							</tr>
							<tr>
								<td colspan="7">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Observaciones</span>
								  		</div>
	  									<textarea class="form-control" 
	  										name="observaciones" 
											onchange="modificaFactura('observaciones',value)"
	  										autocomplete="off">"""),_display_(/*105.34*/factura/*105.41*/.getObservaciones()),format.raw/*105.60*/("""</textarea>
									</div>
								</td>
							</tr>
						</thead>
					</table>
					<hr>
					<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<TR>
								<TH width="10%">CODIGO</TH>
								<TH width="20%">EQUIPO</TH>
								<TH width="4%">KG</TH>
						        <TH width="4%">M2</TH>
						        <TH width="3%">UN</TH>
								<TH width="8%">CANTIDAD</TH>
								<TH width="3%">MONEDA</TH>
								<TH width="10%">P.UNITARIO<br>COMPRA</TH>
								<TH width="10%">TOTAL</TH>
								<TH width="10%">SUCURSAL</TH>
								<TH width="4%">ASIGNA<br>P.LISTA</TH>
								<TH width="15%">DESTINO</TH>
								<TH width="4%">TOT.KG</TH>
								<TH width="4%">TOT.M2</TH>
								<TH width="1%">DEL</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*133.9*/for((lista1, index) <- detalleFactura.zipWithIndex) yield /*133.60*/{_display_(Seq[Any](format.raw/*133.61*/("""
								"""),format.raw/*134.9*/("""<TR>
									<td  style="text-align:left;">"""),_display_(/*135.41*/lista1/*135.47*/.get(1)),format.raw/*135.54*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*136.41*/lista1/*136.47*/.get(2)),format.raw/*136.54*/("""</td>
									<td  style="text-align:right;" id="KG_"""),_display_(/*137.49*/index),format.raw/*137.54*/("""">"""),_display_(/*137.57*/lista1/*137.63*/.get(14)),format.raw/*137.71*/("""</td>
									<td  style="text-align:right;" id="M2_"""),_display_(/*138.49*/index),format.raw/*138.54*/("""">"""),_display_(/*138.57*/lista1/*138.63*/.get(15)),format.raw/*138.71*/("""</td>
									<td  style="text-align:center;">"""),_display_(/*139.43*/lista1/*139.49*/.get(3)),format.raw/*139.56*/("""</td>
									<td  style="text-align:center;">
										<input type="text" class="cantidad form-control right"
											value=""""),_display_(/*142.20*/lista1/*142.26*/.get(4)),format.raw/*142.33*/(""""
											id="cantidad_"""),_display_(/*143.26*/index),format.raw/*143.31*/(""""
											onfocus="value=value.replace(/,/g,'');" 
											onkeydown="return ingresoDouble(window.event)"
											autocomplete="off"
											onchange="if(value=='') value='"""),_display_(/*147.44*/lista1/*147.50*/.get(4)),format.raw/*147.57*/("""'; else modificaCompra('"""),_display_(/*147.82*/lista1/*147.88*/.get(9)),format.raw/*147.95*/("""','cantidad',value); actualTotales(id);" 
											"""),_display_(if(lista1.get(10).equals("1"))/*148.43*/{_display_(Seq[Any](format.raw/*148.44*/(""">""")))}else/*148.50*/{_display_(Seq[Any](format.raw/*148.51*/("""readonly>""")))}),format.raw/*148.61*/("""
									"""),format.raw/*149.10*/("""</td>
									<td  style="text-align:center;">
										 <select class="form-control form-control-sm" id="idMoneda_"""),_display_(/*151.71*/index),format.raw/*151.76*/("""" onchange="modificaCompra('"""),_display_(/*151.105*/lista1/*151.111*/.get(9)),format.raw/*151.118*/("""','id_moneda',value)">
											<option value=""""),_display_(/*152.28*/lista1/*152.34*/.get(5)),format.raw/*152.41*/("""">"""),_display_(/*152.44*/lista1/*152.50*/.get(6)),format.raw/*152.57*/("""</option>
							            	"""),_display_(/*153.22*/for(mon <- listMoneda) yield /*153.44*/{_display_(Seq[Any](format.raw/*153.45*/("""
							                	"""),format.raw/*154.25*/("""<option value=""""),_display_(/*154.41*/mon/*154.44*/.getId()),format.raw/*154.52*/("""" >"""),_display_(/*154.56*/mon/*154.59*/.getNickName()),format.raw/*154.73*/("""</option>
											""")))}),format.raw/*155.13*/("""
						        		"""),format.raw/*156.17*/("""</select>
									</td>
									<td  style="text-align:center;">
										<input type="text" class="form-control right"
											value=""""),_display_(/*160.20*/lista1/*160.26*/.get(7)),format.raw/*160.33*/(""""
											id="puCompra_"""),_display_(/*161.26*/index),format.raw/*161.31*/(""""
											onfocus="value=value.replace(/,/g,'');" 
											onkeydown="return ingresoDouble(window.event)"
											autocomplete="off"
											onchange="if(value=='') value='"""),_display_(/*165.44*/lista1/*165.50*/.get(7)),format.raw/*165.57*/("""'; else modificaCompra('"""),_display_(/*165.82*/lista1/*165.88*/.get(9)),format.raw/*165.95*/("""','precioUnidad',value); actualTotales(id);">
									</td>
									<td  style="text-align:center;">
										<div class="total" id="total_"""),_display_(/*168.41*/index),format.raw/*168.46*/("""" align="right">"""),_display_(/*168.63*/lista1/*168.69*/.get(8)),format.raw/*168.76*/("""</div>
									</td>
									<td style="text-align: left">
											 <select class="form-control form-control-sm" 
													onchange= "actualizaSucursal('"""),_display_(/*172.45*/index),format.raw/*172.50*/("""','"""),_display_(/*172.54*/lista1/*172.60*/.get(13)),format.raw/*172.68*/("""',value);"
												"""),_display_(if(lista1.get(10).equals("1"))/*173.44*/{_display_(Seq[Any](format.raw/*173.45*/("""
													"""),format.raw/*174.14*/(""">
												""")))}else/*175.18*/{_display_(Seq[Any](format.raw/*175.19*/("""
													"""),format.raw/*176.14*/("""disabled>
												""")))}),format.raw/*177.14*/("""
												"""),format.raw/*178.13*/("""<option value=""""),_display_(/*178.29*/lista1/*178.35*/.get(19)),format.raw/*178.43*/("""">"""),_display_(/*178.46*/lista1/*178.52*/.get(18)),format.raw/*178.60*/("""</option>
												"""),_display_(/*179.14*/for(lista <- listSucursales) yield /*179.42*/{_display_(Seq[Any](format.raw/*179.43*/("""
													"""),format.raw/*180.14*/("""<option value=""""),_display_(/*180.30*/lista/*180.35*/.getId()),format.raw/*180.43*/("""">"""),_display_(/*180.46*/lista/*180.51*/.getNombre()),format.raw/*180.63*/("""</option>
												""")))}),format.raw/*181.14*/("""
							        		"""),format.raw/*182.18*/("""</select>
									</td>
									<td style="text-align: center">
										<input type="hidden" id="id_sucursal_"""),_display_(/*185.49*/index),format.raw/*185.54*/("""" value=""""),_display_(/*185.64*/lista1/*185.70*/.get(19)),format.raw/*185.78*/("""">
										<a href="#" onclick="selSucursalPrecio('"""),_display_(/*186.52*/index),format.raw/*186.57*/("""','"""),_display_(/*186.61*/lista1/*186.67*/.get(13)),format.raw/*186.75*/("""')">
											<kbd style="background-color: #CACFD2">Asignar</kbd>
										</a>
									</td>
									<td  style="text-align:center;">
										<div id="selBodegaDestino_"""),_display_(/*191.38*/index),format.raw/*191.43*/(""",">
											 <select class="form-control form-control-sm" 
													onchange= "modificaCompra('"""),_display_(/*193.42*/lista1/*193.48*/.get(9)),format.raw/*193.55*/("""','id_bodegaEmpresa',value)"
													onclick = "actualizaSelect('"""),_display_(/*194.43*/index),format.raw/*194.48*/("""','"""),_display_(/*194.52*/lista1/*194.58*/.get(11)),format.raw/*194.66*/("""', '"""),_display_(/*194.71*/lista1/*194.77*/.get(13)),format.raw/*194.85*/("""');"
												"""),_display_(if(lista1.get(10).equals("1"))/*195.44*/{_display_(Seq[Any](format.raw/*195.45*/("""
													"""),format.raw/*196.14*/(""">
												""")))}else/*197.18*/{_display_(Seq[Any](format.raw/*197.19*/("""
													"""),format.raw/*198.14*/("""disabled>
												""")))}),format.raw/*199.14*/("""
												"""),format.raw/*200.13*/("""<option value=""""),_display_(/*200.29*/lista1/*200.35*/.get(11)),format.raw/*200.43*/("""" >"""),_display_(/*200.47*/lista1/*200.53*/.get(12)),format.raw/*200.61*/("""</option>
							        		</select>
										</div>
									</td>
									<td><div class="kg" id="totKG_"""),_display_(/*204.41*/index),format.raw/*204.46*/("""" align="right">"""),_display_(/*204.63*/lista1/*204.69*/.get(16)),format.raw/*204.77*/("""</div></td>
									<td><div class="m2" id="totM2_"""),_display_(/*205.41*/index),format.raw/*205.46*/("""" align="right">"""),_display_(/*205.63*/lista1/*205.69*/.get(17)),format.raw/*205.77*/("""</div></td>
									<td style="text-align: center;">
										"""),_display_(if(lista1.get(10).equals("1"))/*207.42*/{_display_(Seq[Any](format.raw/*207.43*/("""
											"""),format.raw/*208.12*/("""<a href="#" onclick="eliminarFila2(this, '"""),_display_(/*208.55*/lista1/*208.61*/.get(9)),format.raw/*208.68*/("""')">
												<kbd style="background-color: red">X</kbd>
											</a>
										""")))} else {null} ),format.raw/*211.12*/("""
									"""),format.raw/*212.10*/("""</td>
								</TR>
				 			""")))}),format.raw/*214.10*/("""
				 			"""),format.raw/*215.9*/("""<TR>
						        <td>
									<input type='text' class='form-control' style='background:white' 
										onclick="$('#listaEquipos').modal('show');"
										readonly>
						        </td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</TR>
				 			<TR style="background-color: #eeeeee">
						        <td></td>
								<td>TOTALES</td>
								<td></td>
								<td></td>
								<td></td>
								<td><div id="totalCantidad" align="right">0.00</div></td>
								<td></td>
								<td></td>
								<td><div id="totalTotal" align="right">0.00</div></td>
								<td></td>
								<td></td>
								<td></td>
								<td><div id="totalKG" align="right">0.00</div></td>
								<td><div id="totalM2" align="right">0.00</div></td>
								<td></td>
							</TR>
						</tbody>
					</table>
				</div>
			</div>
			<div class="noprint" align="left">
				<div class="row justify-content-md-center" >
					<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
						<input type="button"  value="Cancelar" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
					</div>
					<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
						<input type="submit"  id="aplica" value='Grabar' class="btn btn-primary btn-sm rounded-pill btn-block">
					</div>
				</div>
			</div>
		</div>
	</form>
	
	<div id='listaEquipos' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>SELECCIONAR EQUIPO</h5>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type='button' class='btn btn-sm  btn-info' style='font-size: 10px;' data-dismiss='modal' onclick='$("#modalNuevoEquipo").modal("show")'>Agregar nuevo equipo</button>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<table id="tablaListaEquipos" class='table table-sm table-bordered table-condensed table-hover table-fluid'>
						<thead style="background-color: #eeeeee">
							<TR> 
								<th>GRUPO</th>
								<th>CODIGO</th>
								<th>EQUIPO</th>
								<th>ORIGEN</th>
								
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*293.9*/for(lista1 <- listEquipo) yield /*293.34*/{_display_(Seq[Any](format.raw/*293.35*/("""
								"""),format.raw/*294.9*/("""<TR onClick="auxCodEquip='"""),_display_(/*294.36*/lista1/*294.42*/.getCodigo()),format.raw/*294.54*/("""'; auxNomEquip='"""),_display_(/*294.71*/lista1/*294.77*/.getNombre()),format.raw/*294.89*/("""'; auxKgEquip='"""),_display_(/*294.105*/lista1/*294.111*/.getKg()),format.raw/*294.119*/("""'; auxM2Equip='"""),_display_(/*294.135*/lista1/*294.141*/.getM2()),format.raw/*294.149*/("""'; 
										auxUnEquip='"""),_display_(/*295.24*/lista1/*295.30*/.getUnidad()),format.raw/*295.42*/("""'; selectEquipo("""),_display_(/*295.59*/lista1/*295.65*/.getId()),format.raw/*295.73*/(""")" data-dismiss="modal">
									<td  style="text-align:left;">"""),_display_(/*296.41*/lista1/*296.47*/.getGrupo()),format.raw/*296.58*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*297.41*/lista1/*297.47*/.getCodigo()),format.raw/*297.59*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*298.41*/lista1/*298.47*/.getNombre()),format.raw/*298.59*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*299.41*/lista1/*299.47*/.getFabrica()),format.raw/*299.60*/("""</td>
								</TR>
				 			""")))}),format.raw/*301.10*/("""
						"""),format.raw/*302.7*/("""</tbody>
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
	
	
	
	
	<div id='modalNuevoEquipo' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>INGRESAR NUEVO EQUIPO</h5>
					<button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<TR>
							<td  style="text-align:left;">GRUPO</td>
							<td style="text-align:left">
								<select class="custom-select"
									id="nuevoEquipoId_grupo"
									required>
									"""),_display_(/*334.11*/for(lista <- listGrupos) yield /*334.35*/{_display_(Seq[Any](format.raw/*334.36*/("""
										"""),format.raw/*335.11*/("""<option value=""""),_display_(/*335.27*/lista/*335.32*/.getId()),format.raw/*335.40*/("""">"""),_display_(/*335.43*/lista/*335.48*/.getNombre()),format.raw/*335.60*/("""</option>
									""")))}),format.raw/*336.11*/("""
								"""),format.raw/*337.9*/("""</select>
							</td>
						</TR>
						<TR>
							<td  style="text-align:left;">PROPIEDAD</td>
							<td style="text-align:left">
								<select class="custom-select"
								id="nuevoId_propiedad" required>
									<option value=""""),_display_(/*345.26*/listPropiedad/*345.39*/.get(0).getId()),format.raw/*345.54*/("""">"""),_display_(/*345.57*/listPropiedad/*345.70*/.get(0).getNombre()),format.raw/*345.89*/("""</option>
									"""),_display_(/*346.11*/for(lista <- listPropiedad) yield /*346.38*/{_display_(Seq[Any](format.raw/*346.39*/("""
										"""),format.raw/*347.11*/("""<option value=""""),_display_(/*347.27*/lista/*347.32*/.getId()),format.raw/*347.40*/("""">"""),_display_(/*347.43*/lista/*347.48*/.getNombre()),format.raw/*347.60*/("""</option>
									""")))}),format.raw/*348.11*/("""
								"""),format.raw/*349.9*/("""</select>
							</td>
						</TR>
						<tr>
							<td style="text-align:left; vertical-align:top">CODIGO EQUIPO: </td>
							<td style="text-align:left; vertical-align:top">
								<input type="text" class="form-control left"
									id="nuevoEquipoCodigo"
									required
									autocomplete="off"
									onkeydown="return sinReservCodigos(window.event)"
									onchange="value=value.replace(/\s/g,'').toUpperCase(); verificaCodigo(value)"
									maxlength="50">
							</td>
						</tr>
						<tr>
							<td style="text-align:left; vertical-align:top">NOMBRE EQUIPO: </td>
							<td style="text-align:left; vertical-align:top">
								<input type="text" class="form-control left"
									id="nuevoEquipoNombre"
									onkeydown="return sinReservados(window.event)"
									required
									autocomplete="off"
									maxlength="100">
							</td>
						</tr>
						<tr>
							<td style="text-align:left; vertical-align:top">FABRICANTE: </td>
							<td style="text-align:left; vertical-align:top">
								<select class="custom-select" 
									id="nuevoEquipoId_fabrica"
									required>
									"""),_display_(/*381.11*/for(lista <- listFabrica) yield /*381.36*/{_display_(Seq[Any](format.raw/*381.37*/("""
										"""),format.raw/*382.11*/("""<option value=""""),_display_(/*382.27*/lista/*382.32*/.id),format.raw/*382.35*/("""">"""),_display_(/*382.38*/lista/*382.43*/.nickName),format.raw/*382.52*/("""</option>
									""")))}),format.raw/*383.11*/("""
								"""),format.raw/*384.9*/("""</select>
							</td>
						</tr>
						<tr>
							<td style="text-align:left; vertical-align:top">UNIDAD: </td>
							<td style="text-align:left; vertical-align:top">
								<select class="custom-select" 
									id="nuevoEquipoId_unidad"
									required>
									"""),_display_(/*393.11*/for(lista <- listUnidades) yield /*393.37*/{_display_(Seq[Any](format.raw/*393.38*/("""
										"""),format.raw/*394.11*/("""<option value=""""),_display_(/*394.27*/lista/*394.32*/.id),format.raw/*394.35*/("""">"""),_display_(/*394.38*/lista/*394.43*/.nombre),format.raw/*394.50*/("""</option>
									""")))}),format.raw/*395.11*/("""
								"""),format.raw/*396.9*/("""</select>
							</td>
						</tr>
						<tr>
							<td style="text-align:left; vertical-align:top">PESO (KG): </td>
							<td style="text-align:left; vertical-align:top">
								<input type="text" class="form-control left"
									id="nuevoEquipoId_kg"
									autocomplete="off"
									value="0.00"
									onfocus="value=value.replace(/,/g,'')" 
									onkeydown="return ingresoDouble(window.event)"
									onchange="value = formatStandar(value,2);">
							</td>
						</tr>
						<tr>
							<td style="text-align:left; vertical-align:top">AREA (M2): </td>
							<td style="text-align:left; vertical-align:top">
								<input type="text" class="form-control left"
									id="nuevoEquipoId_m2"
									autocomplete="off"
									value="0.00"
									onfocus="value=value.replace(/,/g,'')" 
									onkeydown="return ingresoDouble(window.event)"
									onchange="value = formatStandar(value,2);">
							</td>
						</tr>
					</table>
					<div class='row'>
						<div class='col-sm-5' style='text-align:center'>
							<button type='button' class='btn btn-sm  btn-success' style='font-size: 10px;' onclick="grabaNuevoEquipo()">GRABAR</button>
						</div>
						<div class='col-sm-5' style='text-align:center'>
							<button type='button' class='btn btn-sm  btn-light' style='font-size: 10px;' data-dismiss='modal'>Cancelar</button>
						</div>
					</div>
			</div>
			</div>
		</div>
	</div>
	
	<div id='modalPrecioLista' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-md' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>ASIGNAR/CAMBIAR PRECIO LISTA</h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<div id="modalPrecios"></div>
	   				<div class='row'>
						<div class='col-sm-12' style='text-align:center'>
							<button type='button' class='btn btn-sm  btn-warning' style='font-size: 10px;' data-dismiss='modal'>CERRAR</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	
	<form class="formulario" id="formDescargaDocumento" method="post" action="/downloadPDF/">	
		<input type="hidden" id="descargaDocumento" name="nombreArchivo">
	</form>
	
	
""")))}),format.raw/*464.2*/("""

"""),format.raw/*466.1*/("""<div id='listaProveedor' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
	<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
		<div class='modal-content'>
			<div class='modal-header'>
				<h5 class='modal-title'>SELECCIONAR PROVEEDOR</h5>
				<button type='button' class='close' data-dismiss='modal' aria-label='Close'>
					<span aria-hidden='true'>&times;</span>
				</button>
			</div>
			<div class='modal-body'>
				<div class="noprint" align="center">
					<a href="#" onclick="$('#listaProveedor').modal('hide'); $('#modalProveedorNuevo').modal('show');">
						<kbd style="background-color: #73C6B6">Agregar Nuevo</kbd>
					</a>
				</div>
				<table id="tablaListaProveedores" class='table table-sm table-bordered table-condensed table-hover table-fluid'>
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>"""),_display_(/*484.13*/mapDiccionario/*484.27*/.get("RUT")),format.raw/*484.38*/("""</TH>
							<TH>NICK</TH>
							<TH>NOMBRE</TH>
						</TR>
					</thead>
					<tbody>
					"""),_display_(/*490.7*/for(lista1 <- listProveedor) yield /*490.35*/{_display_(Seq[Any](format.raw/*490.36*/("""
						"""),format.raw/*491.7*/("""<TR onClick="
								$('#id_proveedor').val("""),_display_(/*492.33*/lista1/*492.39*/.getId()),format.raw/*492.47*/(""");
								$('#rutProveedor').val('"""),_display_(/*493.34*/lista1/*493.40*/.getRut()),format.raw/*493.49*/("""');
								$('#nombreProveedor').val('"""),_display_(/*494.37*/lista1/*494.43*/.getNickName()),format.raw/*494.57*/("""');
								modificaFactura('id_proveedor',"""),_display_(/*495.41*/lista1/*495.47*/.getId()),format.raw/*495.55*/(""");"
								data-dismiss="modal">
							<td  style="text-align:left;">"""),_display_(/*497.39*/lista1/*497.45*/.getRut()),format.raw/*497.54*/("""</td>
							<td  style="text-align:left;">"""),_display_(/*498.39*/lista1/*498.45*/.getNickName()),format.raw/*498.59*/("""</td>
							<td  style="text-align:left;">"""),_display_(/*499.39*/lista1/*499.45*/.getNombre()),format.raw/*499.57*/("""</td>
						</TR>
					""")))}),format.raw/*501.7*/("""
					"""),format.raw/*502.6*/("""</tbody>
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



<script type="text/javascript">

	$(document).ready(function() """),format.raw/*518.31*/("""{"""),format.raw/*518.32*/("""
		
		"""),format.raw/*520.3*/("""$('#tablaListaProveedores').DataTable("""),format.raw/*520.41*/("""{"""),format.raw/*520.42*/("""
	    	"""),format.raw/*521.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
	    	"order": [[ 1, "asc" ]],
	    	"language": """),format.raw/*524.19*/("""{"""),format.raw/*524.20*/("""
	        	"""),format.raw/*525.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*526.10*/("""}"""),format.raw/*526.11*/("""
	  	"""),format.raw/*527.5*/("""}"""),format.raw/*527.6*/(""" """),format.raw/*527.7*/(""");
		
		$('#tablaListaEquipos').DataTable("""),format.raw/*529.37*/("""{"""),format.raw/*529.38*/("""
	    	"""),format.raw/*530.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
	    	"order": [[ 1, "asc" ]],
	    	"language": """),format.raw/*533.19*/("""{"""),format.raw/*533.20*/("""
	        	"""),format.raw/*534.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*535.10*/("""}"""),format.raw/*535.11*/("""
	  	"""),format.raw/*536.5*/("""}"""),format.raw/*536.6*/(""" """),format.raw/*536.7*/(""");
		  sumaTotales();
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*539.2*/("""}"""),format.raw/*539.3*/(""");
	
	const modificaFactura = (campo, valor) =>"""),format.raw/*541.43*/("""{"""),format.raw/*541.44*/("""
		"""),format.raw/*542.3*/("""var formData = new FormData();
	  	formData.append('id_factura','"""),_display_(/*543.36*/factura/*543.43*/.getId()),format.raw/*543.51*/("""');
	  	formData.append('campo',campo);
		formData.append('valor',valor);
        $.ajax("""),format.raw/*546.16*/("""{"""),format.raw/*546.17*/("""
            """),format.raw/*547.13*/("""url: "/modificaFacturaPorCampoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*554.36*/("""{"""),format.raw/*554.37*/("""}"""),format.raw/*554.38*/("""
        """),format.raw/*555.9*/("""}"""),format.raw/*555.10*/(""");
	"""),format.raw/*556.2*/("""}"""),format.raw/*556.3*/("""
	
	"""),format.raw/*558.2*/("""const modificaCompra = (id_compra, campo, valor) =>"""),format.raw/*558.53*/("""{"""),format.raw/*558.54*/("""
		"""),format.raw/*559.3*/("""var formData = new FormData();
	  	formData.append('id_compra',id_compra);
	  	formData.append('campo',campo);
		formData.append('valor',valor);
        $.ajax("""),format.raw/*563.16*/("""{"""),format.raw/*563.17*/("""
            """),format.raw/*564.13*/("""url: "/modificaCompraPorCampoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*571.36*/("""{"""),format.raw/*571.37*/("""}"""),format.raw/*571.38*/("""
        """),format.raw/*572.9*/("""}"""),format.raw/*572.10*/(""");
		sumaTotales();
	"""),format.raw/*574.2*/("""}"""),format.raw/*574.3*/("""
	
	"""),format.raw/*576.2*/("""const eliminarFila2 = (nodo, id_compra) =>"""),format.raw/*576.44*/("""{"""),format.raw/*576.45*/("""
		"""),format.raw/*577.3*/("""var formData = new FormData();
	  	formData.append('id_compra',id_compra);
        $.ajax("""),format.raw/*579.16*/("""{"""),format.raw/*579.17*/("""
            """),format.raw/*580.13*/("""url: "/eliminaCompraAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*587.36*/("""{"""),format.raw/*587.37*/("""}"""),format.raw/*587.38*/("""
        """),format.raw/*588.9*/("""}"""),format.raw/*588.10*/(""");
		let nodoTd = nodo.parentNode.parentNode; 
		$(nodoTd).remove();
		sumaTotales();
	"""),format.raw/*592.2*/("""}"""),format.raw/*592.3*/("""
	
	"""),format.raw/*594.2*/("""const verificarNumeroDocumento = (value) =>"""),format.raw/*594.45*/("""{"""),format.raw/*594.46*/("""
		"""),format.raw/*595.3*/("""let id_proveedor = $("#id_proveedor").val();
		if(id_proveedor==0)"""),format.raw/*596.22*/("""{"""),format.raw/*596.23*/("""
			"""),format.raw/*597.4*/("""alertify.alert('Primero debe seleccionar el proveedor', function () """),format.raw/*597.72*/("""{"""),format.raw/*597.73*/("""
 				"""),format.raw/*598.6*/("""$("#numeroFactura").val("");
 			"""),format.raw/*599.5*/("""}"""),format.raw/*599.6*/(""");
		"""),format.raw/*600.3*/("""}"""),format.raw/*600.4*/("""else"""),format.raw/*600.8*/("""{"""),format.raw/*600.9*/("""
			"""),format.raw/*601.4*/("""var formData = new FormData();
		  	formData.append('numeroFactura',value);
		  	formData.append('id_proveedor',id_proveedor);
	        $.ajax("""),format.raw/*604.17*/("""{"""),format.raw/*604.18*/("""
	            """),format.raw/*605.14*/("""url: "/verificaNumeroFacturaAjax/",
	            type: "POST",
	            method: "POST",
	            data: formData,
	            cache: false,
	            contentType: false,
		     	processData: false,
		     	success: function(respuesta)"""),format.raw/*612.37*/("""{"""),format.raw/*612.38*/("""
		     		"""),format.raw/*613.10*/("""if(respuesta=="existe")"""),format.raw/*613.33*/("""{"""),format.raw/*613.34*/("""
		     			"""),format.raw/*614.11*/("""alertify.alert('El número de documento ya existe para este proveedor, intente con otro', function () """),format.raw/*614.112*/("""{"""),format.raw/*614.113*/("""
		     				"""),format.raw/*615.12*/("""$("#numeroFactura").val(""""),_display_(/*615.38*/factura/*615.45*/.getNumero()),format.raw/*615.57*/("""");
		     			"""),format.raw/*616.11*/("""}"""),format.raw/*616.12*/(""");
		     		"""),format.raw/*617.10*/("""}"""),format.raw/*617.11*/("""else"""),format.raw/*617.15*/("""{"""),format.raw/*617.16*/("""
						"""),format.raw/*618.7*/("""modificaFactura("numero",value);
					"""),format.raw/*619.6*/("""}"""),format.raw/*619.7*/("""
		     		"""),format.raw/*620.10*/("""if(respuesta=="error")"""),format.raw/*620.32*/("""{"""),format.raw/*620.33*/("""
		     			"""),format.raw/*621.11*/("""alertify.alert(msgError, function () """),format.raw/*621.48*/("""{"""),format.raw/*621.49*/("""
			     			"""),format.raw/*622.12*/("""location.href = "/";
			     		"""),format.raw/*623.11*/("""}"""),format.raw/*623.12*/(""");
		     		"""),format.raw/*624.10*/("""}"""),format.raw/*624.11*/("""
		     	"""),format.raw/*625.9*/("""}"""),format.raw/*625.10*/("""
	        """),format.raw/*626.10*/("""}"""),format.raw/*626.11*/(""");
		"""),format.raw/*627.3*/("""}"""),format.raw/*627.4*/("""
			
	"""),format.raw/*629.2*/("""}"""),format.raw/*629.3*/("""
	
	"""),format.raw/*631.2*/("""let mapDec = new Map();
	"""),_display_(/*632.3*/for(lista <- listMon) yield /*632.24*/{_display_(Seq[Any](format.raw/*632.25*/("""
		"""),format.raw/*633.3*/("""mapDec.set("""),_display_(/*633.15*/lista/*633.20*/.getId()),format.raw/*633.28*/(""","""),_display_(/*633.30*/lista/*633.35*/.getNumeroDecimales()),format.raw/*633.56*/(""");
	""")))}),format.raw/*634.3*/("""
	

	
	"""),format.raw/*638.2*/("""let auxCodEquip = "";
	let auxNomEquip = "";
	let auxKgEquip = "";
	let auxM2Equip = "";
	let auxUnEquip = "";
	let cont = '"""),_display_(/*643.15*/detalleFactura/*643.29*/.size()),format.raw/*643.36*/("""';
	
	const selectEquipo = (id_equipo) => """),format.raw/*645.38*/("""{"""),format.raw/*645.39*/("""
		"""),format.raw/*646.3*/("""cont++;
		let tabla = document.getElementById("tablaPrincipal");
		let row = tabla.insertRow(tabla.rows.length-2);
		let cell;
		
		cell = row.insertCell(0);
		cell.style.textAlign = "left";
		cell.innerHTML = "<input type=\"hidden\" name=\"id_equipo[]\" value=\""+id_equipo+"\">"+auxCodEquip;
		
		cell = row.insertCell(1);
		cell.style.textAlign = "left";
		cell.innerHTML = auxNomEquip;
		
		cell = row.insertCell(2);
		cell.style.textAlign = "right";
		cell.innerHTML = "<div id=\"KG_"+cont+"\" align=\"right\">"+formatStandar(auxKgEquip,2)+"</div>";
		
		cell = row.insertCell(3);
		cell.style.textAlign = "right";
		cell.innerHTML = "<div id=\"M2_"+cont+"\" align=\"right\">"+formatStandar(auxM2Equip,2)+"</div>";
		
		cell = row.insertCell(4);
		cell.style.textAlign = "center";
		cell.innerHTML = auxUnEquip;
		
		cell = row.insertCell(5);
		cell.style.textAlign = "center";
		cell.innerHTML = "<input type=\"text\" class=\"cantidad form-control right\" "+
			"name=\"cantidad[]\" "+
			"id=\"cantidad_"+cont+"\" "+
			"value=\"0.00\" "+
			"onfocus=\"value=value.replace(/,/g,'');\" "+
			"onkeydown=\"return ingresoDouble(window.event)\" "+
			"autocomplete=\"off\" "+
			"onchange=\"if(value=='') value=0; value=formatStandar(value,2); actualTotales(id);\">";
		
		cell = row.insertCell(6);
		cell.style.textAlign = "center";
		let mon = "<select class=\"form-control form-control-sm\" id=\"idMoneda_"+cont+"\" name=\"id_monedaCompra[]\">";
    		"""),_display_(/*685.8*/for(mon <- listMoneda) yield /*685.30*/{_display_(Seq[Any](format.raw/*685.31*/("""
    			"""),format.raw/*686.8*/("""mon += "<option value='"""),_display_(/*686.32*/mon/*686.35*/.getId()),format.raw/*686.43*/("""'>"""),_display_(/*686.46*/mon/*686.49*/.getNickName()),format.raw/*686.63*/("""</option>";
			""")))}),format.raw/*687.5*/("""
    		"""),format.raw/*688.7*/("""mon += "</select>";
    	cell.innerHTML = mon;
	
		cell = row.insertCell(7);
		cell.style.textAlign = "center";
		cell.innerHTML = "<input type=\"text\" class=\"form-control right\" "+
			"name=\"puCompra[]\" "+
			"id=\"puCompra_"+cont+"\" "+
			"value=\"0\" "+
			"onfocus=\"value=value.replace(/,/g,'');\" "+ 
			"onkeydown=\"return ingresoDouble(window.event)\" "+
			"autocomplete=\"off\" "+
			"onchange=\"if(value=='') value=0; value=formatStandar(value,2); actualTotales(id);\"> ";
		
		cell = row.insertCell(8);
		cell.style.textAlign = "right";
		cell.innerHTML = "<div class=\"total\" id=\"total_"+cont+"\" align=\"right\">0.00</div>";
		
		cell = row.insertCell(9);
		cell.style.textAlign = "center";
		let auxSucursales = "<select class=\"form-control form-control-sm \"" +
								" onchange= \"actualizaSucursal('"+cont+"','"+id_equipo+"',value);\">" +
										"<option value='0'></option>";
												"""),_display_(/*711.14*/for(lista <- listSucursales) yield /*711.42*/{_display_(Seq[Any](format.raw/*711.43*/("""
													"""),format.raw/*712.14*/("""auxSucursales += "<option value=\""""),_display_(/*712.49*/lista/*712.54*/.getId()),format.raw/*712.62*/("""\">"""),_display_(/*712.66*/lista/*712.71*/.getNombre()),format.raw/*712.83*/("""</option>";
												""")))}),format.raw/*713.14*/("""
							        		"""),format.raw/*714.18*/("""auxSucursales += "</select></td>";
		cell.innerHTML = auxSucursales;
		
		cell = row.insertCell(10);
		cell.style.textAlign = "center";
		cell.innerHTML = "<input type=\"hidden\" id=\"id_sucursal_"+cont+"\" value=\"0\">" +
							"<a href=\"#\" onclick=\"selSucursalPrecio("+cont+","+id_equipo+")\"> "+
							"<kbd style=\"background-color: #CACFD2\">Asignar</kbd></a>";
		cell = row.insertCell(11);
		cell.style.textAlign = "center";
		let des = "<div id='selBodegaDestino_"+cont+"'><select class=\"form-control form-control-sm\" name=\"id_bodegaDestino[]\">"+
				"<option value='0'></option>";
    		des += "</select></div>";
		cell.innerHTML = des;
			
		cell = row.insertCell(12);
		cell.style.textAlign = "right";
		cell.innerHTML = "<div class=\"kg\" id=\"totKG_"+cont+"\" align=\"right\">0.00</div>";
		
		cell = row.insertCell(13);
		cell.style.textAlign = "right";
		cell.innerHTML = "<div class=\"m2\" id=\"totM2_"+cont+"\" align=\"right\">0.00</div>";
		
		cell = row.insertCell(14);
		cell.style.textAlign = "center";
		cell.innerHTML = "<a href=\"#\" onclick= \"eliminarFila(this)\"><kbd style=\"background-color: red\">X</kbd></a>";
		
	"""),format.raw/*741.2*/("""}"""),format.raw/*741.3*/("""
	
	"""),format.raw/*743.2*/("""const eliminarFila = (nodo) =>"""),format.raw/*743.32*/("""{"""),format.raw/*743.33*/("""
		"""),format.raw/*744.3*/("""let nodoTd = nodo.parentNode.parentNode; 
		$(nodoTd).remove();
		sumaTotales();
	"""),format.raw/*747.2*/("""}"""),format.raw/*747.3*/("""
	
	"""),format.raw/*749.2*/("""const actualTotales = (id) =>"""),format.raw/*749.31*/("""{"""),format.raw/*749.32*/("""
		"""),format.raw/*750.3*/("""let aux = id.split("_");
		
		let cantidad = $("#cantidad_"+aux[1]).val().replace(/,/g,'');
		let puCompra = $("#puCompra_"+aux[1]).val().replace(/,/g,'');
		
		let id_moneda = $("#idMoneda_"+aux[1]).val();
		let nroDecimal = mapDec.get(parseFloat(id_moneda));
		
		let kg = $("#KG_"+aux[1]).text().replace(/,/g,'');
		let m2 = $("#M2_"+aux[1]).text().replace(/,/g,'');
		
		let total = parseFloat(cantidad) * parseFloat(puCompra);
		
		let totKG = parseFloat(cantidad) * parseFloat(kg);
		let totM2 = parseFloat(cantidad) * parseFloat(m2);
		$("#puCompra_"+aux[1]).val(formatStandar(puCompra,nroDecimal));
		$("#total_"+aux[1]).text(formatStandar(total,nroDecimal));
		$("#totKG_"+aux[1]).text(formatStandar(totKG,2));
		$("#totM2_"+aux[1]).text(formatStandar(totM2,2));
		sumaTotales();
	"""),format.raw/*770.2*/("""}"""),format.raw/*770.3*/("""
	
	"""),format.raw/*772.2*/("""const sumaTotales = () => """),format.raw/*772.28*/("""{"""),format.raw/*772.29*/("""
		 """),format.raw/*773.4*/("""let sum = 0;
			$(".cantidad").each(function() """),format.raw/*774.35*/("""{"""),format.raw/*774.36*/("""
				"""),format.raw/*775.5*/("""let val = $(this).val().replace(/,/g,'');
				sum += parseFloat(val);
			"""),format.raw/*777.4*/("""}"""),format.raw/*777.5*/("""); $("#totalCantidad").text(formatStandar(sum,2));
			sum = 0;
			$(".total").each(function() """),format.raw/*779.32*/("""{"""),format.raw/*779.33*/("""
				"""),format.raw/*780.5*/("""let val = $(this).text().replace(/,/g,'');
				sum += parseFloat(val);
			"""),format.raw/*782.4*/("""}"""),format.raw/*782.5*/("""); $("#totalTotal").text(formatStandar(sum,2));
			sum = 0;
			$(".kg").each(function() """),format.raw/*784.29*/("""{"""),format.raw/*784.30*/("""
				"""),format.raw/*785.5*/("""let val = $(this).text().replace(/,/g,'');
				sum += parseFloat(val);
			"""),format.raw/*787.4*/("""}"""),format.raw/*787.5*/("""); $("#totalKG").text(formatStandar(sum,2));
			sum = 0;
			$(".m2").each(function() """),format.raw/*789.29*/("""{"""),format.raw/*789.30*/("""
				"""),format.raw/*790.5*/("""let val = $(this).text().replace(/,/g,'');
				sum += parseFloat(val);
			"""),format.raw/*792.4*/("""}"""),format.raw/*792.5*/("""); $("#totalM2").text(formatStandar(sum,2));
	 """),format.raw/*793.3*/("""}"""),format.raw/*793.4*/("""
	
	
	"""),format.raw/*796.2*/("""const validarForm = () =>"""),format.raw/*796.27*/("""{"""),format.raw/*796.28*/("""
		"""),format.raw/*797.3*/("""let flag = true;
		if($("#id_proveedor").val()=="0")"""),format.raw/*798.36*/("""{"""),format.raw/*798.37*/("""
			"""),format.raw/*799.4*/("""flag = false;
			alertify.alert('FALTA SELECCIONAR PROVEEDOR', function () """),format.raw/*800.62*/("""{"""),format.raw/*800.63*/("""
				"""),format.raw/*801.5*/("""return(flag);
     		"""),format.raw/*802.8*/("""}"""),format.raw/*802.9*/(""");
		"""),format.raw/*803.3*/("""}"""),format.raw/*803.4*/("""else"""),format.raw/*803.8*/("""{"""),format.raw/*803.9*/("""
			"""),format.raw/*804.4*/("""$("#tablaPrincipal").dataTable().fnDestroy();
			let tabla = document.getElementById("tablaPrincipal");
			let cantTotal = $("#totalCantidad").text().replace(/,/g,'');
			if(tabla.rows.length > 3 && parseFloat(cantTotal) > 0)"""),format.raw/*807.58*/("""{"""),format.raw/*807.59*/("""
				
				"""),format.raw/*809.5*/("""flag = true;
				$(".selBodDest").each(function() """),format.raw/*810.38*/("""{"""),format.raw/*810.39*/("""
					"""),format.raw/*811.6*/("""let val = $(this).val();
					if(val == 0)"""),format.raw/*812.18*/("""{"""),format.raw/*812.19*/("""
						"""),format.raw/*813.7*/("""flag = false;
					"""),format.raw/*814.6*/("""}"""),format.raw/*814.7*/("""
				"""),format.raw/*815.5*/("""}"""),format.raw/*815.6*/(""");
				
				if(flag)"""),format.raw/*817.13*/("""{"""),format.raw/*817.14*/("""
					"""),format.raw/*818.6*/("""return(flag);
				"""),format.raw/*819.5*/("""}"""),format.raw/*819.6*/("""else"""),format.raw/*819.10*/("""{"""),format.raw/*819.11*/("""
					"""),format.raw/*820.6*/("""alertify.alert('PARA GRABAR UNA COMPRA TODOS LAS LINEAS DEBEN TENER UN DESTINO', function () """),format.raw/*820.99*/("""{"""),format.raw/*820.100*/("""
						"""),format.raw/*821.7*/("""return(flag);
	     			"""),format.raw/*822.10*/("""}"""),format.raw/*822.11*/(""");
				"""),format.raw/*823.5*/("""}"""),format.raw/*823.6*/("""
			"""),format.raw/*824.4*/("""}"""),format.raw/*824.5*/("""else"""),format.raw/*824.9*/("""{"""),format.raw/*824.10*/("""
				"""),format.raw/*825.5*/("""flag = false;
				alertify.alert('PARA GRABAR UNA COMPRA AL MENOS DEBE COMPRAR UN EQUIPOS', function () """),format.raw/*826.91*/("""{"""),format.raw/*826.92*/("""
					"""),format.raw/*827.6*/("""return(flag);
	     		"""),format.raw/*828.9*/("""}"""),format.raw/*828.10*/(""");
			"""),format.raw/*829.4*/("""}"""),format.raw/*829.5*/("""
		"""),format.raw/*830.3*/("""}"""),format.raw/*830.4*/("""
		"""),format.raw/*831.3*/("""return(flag);
	"""),format.raw/*832.2*/("""}"""),format.raw/*832.3*/("""
	
	"""),format.raw/*834.2*/("""const selSucursalPrecio = (index,id_equipo) => """),format.raw/*834.49*/("""{"""),format.raw/*834.50*/("""
		"""),format.raw/*835.3*/("""let id_sucursal = $("#id_sucursal_"+index).val();
		if(id_sucursal != 0)"""),format.raw/*836.23*/("""{"""),format.raw/*836.24*/("""
			"""),format.raw/*837.4*/("""var formData = new FormData();
			formData.append('id_sucursal',id_sucursal);
		  	formData.append('id_equipo',id_equipo);

	        $.ajax("""),format.raw/*841.17*/("""{"""),format.raw/*841.18*/("""
	            """),format.raw/*842.14*/("""url: "/modalPreciosAjax/",
	            type: "POST",
	            method: "POST",
	            data: formData,
	            cache: false,
	            contentType: false,
		     	processData: false,
		     	success: function(rs1)"""),format.raw/*849.31*/("""{"""),format.raw/*849.32*/("""
					"""),format.raw/*850.6*/("""$.ajax("""),format.raw/*850.13*/("""{"""),format.raw/*850.14*/("""
			            """),format.raw/*851.16*/("""url: "/routes2/compraVistaSelBodDestAjax/",
			            type: "POST",
			            method: "POST",
			            data: formData,
			            cache: false,
			            contentType: false,
				     	processData: false,
				     	success: function(rs2)"""),format.raw/*858.33*/("""{"""),format.raw/*858.34*/("""
							"""),format.raw/*859.8*/("""document.getElementById("modalPrecios").innerHTML =  rs1;
				     		$("#modalPrecioLista").modal("show");
				     	"""),format.raw/*861.11*/("""}"""),format.raw/*861.12*/("""
			        """),format.raw/*862.12*/("""}"""),format.raw/*862.13*/(""");
		     	"""),format.raw/*863.9*/("""}"""),format.raw/*863.10*/("""
	        """),format.raw/*864.10*/("""}"""),format.raw/*864.11*/(""");
		"""),format.raw/*865.3*/("""}"""),format.raw/*865.4*/("""else"""),format.raw/*865.8*/("""{"""),format.raw/*865.9*/("""
			"""),format.raw/*866.4*/("""alertify.alert("Primero debe seleccionar la Sucursal", function () """),format.raw/*866.71*/("""{"""),format.raw/*866.72*/("""
     			"""),format.raw/*867.9*/("""}"""),format.raw/*867.10*/(""");
		"""),format.raw/*868.3*/("""}"""),format.raw/*868.4*/("""
	"""),format.raw/*869.2*/("""}"""),format.raw/*869.3*/("""
	
	"""),format.raw/*871.2*/("""const actualizaSucursal = (index, auxIdEquipo, id_sucursal) =>"""),format.raw/*871.64*/("""{"""),format.raw/*871.65*/("""
		"""),format.raw/*872.3*/("""$("#modalSucursal").modal("hide");
		if(id_sucursal != 0)"""),format.raw/*873.23*/("""{"""),format.raw/*873.24*/("""
			"""),format.raw/*874.4*/("""var formData = new FormData();
			formData.append('id_sucursal',id_sucursal);
		  	formData.append('id_equipo',auxIdEquipo);
	        $.ajax("""),format.raw/*877.17*/("""{"""),format.raw/*877.18*/("""
	            """),format.raw/*878.14*/("""url: "/routes2/compraVistaSelBodDestAjax/",
	            type: "POST",
	            method: "POST",
	            data: formData,
	            cache: false,
	            contentType: false,
		     	processData: false,
		     	success: function(rs2)"""),format.raw/*885.31*/("""{"""),format.raw/*885.32*/("""
		     		"""),format.raw/*886.10*/("""document.getElementById("selBodegaDestino_"+index).innerHTML =  rs2["vista"];
					$("#id_sucursal_"+index).val(id_sucursal);
		     		$("#sucursal_"+index).val(rs2["nameSucursal"]);
		     	"""),format.raw/*889.9*/("""}"""),format.raw/*889.10*/("""
	        """),format.raw/*890.10*/("""}"""),format.raw/*890.11*/(""");
		"""),format.raw/*891.3*/("""}"""),format.raw/*891.4*/("""
	"""),format.raw/*892.2*/("""}"""),format.raw/*892.3*/("""
	
	
	
	
	"""),format.raw/*897.2*/("""const actualizaSelect  = (index, id_bodega, id_equipo) => """),format.raw/*897.60*/("""{"""),format.raw/*897.61*/("""
		"""),format.raw/*898.3*/("""var formData = new FormData();
		formData.append('id_bodega',id_bodega);
	  	formData.append('id_equipo',id_equipo);
		$.ajax("""),format.raw/*901.10*/("""{"""),format.raw/*901.11*/("""
            """),format.raw/*902.13*/("""url: "/routes2/compraVistaSelBodDestAjax2/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(rs2)"""),format.raw/*909.30*/("""{"""),format.raw/*909.31*/("""
	     		"""),format.raw/*910.9*/("""document.getElementById("selBodegaDestino_"+index).innerHTML =  rs2;
	     	"""),format.raw/*911.8*/("""}"""),format.raw/*911.9*/("""
        """),format.raw/*912.9*/("""}"""),format.raw/*912.10*/(""");
	"""),format.raw/*913.2*/("""}"""),format.raw/*913.3*/("""
	
	

	"""),format.raw/*917.2*/("""const cambiarPrecio = (id_sucursal, id_equipo,campo,valor) => """),format.raw/*917.64*/("""{"""),format.raw/*917.65*/("""
		"""),format.raw/*918.3*/("""let id_moneda = $("#precioMoneda").val();
		var formData = new FormData();
		formData.append('id_sucursal',id_sucursal);
		formData.append('id_equipo',id_equipo);
		formData.append('id_moneda',id_moneda);
		formData.append('campo',campo);
		formData.append('valor',valor);
		$.ajax("""),format.raw/*925.10*/("""{"""),format.raw/*925.11*/("""
            """),format.raw/*926.13*/("""url: "/actualizaPrecioAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*933.36*/("""{"""),format.raw/*933.37*/("""
	     		"""),format.raw/*934.9*/("""if(respuesta=="error")"""),format.raw/*934.31*/("""{"""),format.raw/*934.32*/("""
	     			"""),format.raw/*935.10*/("""alertify.alert(msgError, function () """),format.raw/*935.47*/("""{"""),format.raw/*935.48*/("""
		     			"""),format.raw/*936.11*/("""location.href = "/";
		     		"""),format.raw/*937.10*/("""}"""),format.raw/*937.11*/(""");
	     		"""),format.raw/*938.9*/("""}"""),format.raw/*938.10*/("""
	     		"""),format.raw/*939.9*/("""if(respuesta.status)"""),format.raw/*939.29*/("""{"""),format.raw/*939.30*/("""
	     			"""),format.raw/*940.10*/("""let nroDecimal = mapDec.get(parseFloat(id_moneda));
	     			let precioVenta = $("#precioVenta").val().replace(/,/g,'');
	     			let precioArriendo = $("#precioArriendo").val().replace(/,/g,'');
	     			let precioMinimo = $("#precioMinimo").val().replace(/,/g,'');
	     			let permanenciaMinima = $("#permanenciaMinima").val().replace(/,/g,'');
	     			if(precioVenta=="") precioVenta = 0;
	     			if(precioArriendo=="") precioArriendo = 0;
	     			if(precioMinimo=="") precioMinimo = 0;
	     			if(permanenciaMinima=="") permanenciaMinima = 0;
	     			let tasa = parseFloat(precioArriendo)/parseFloat(precioVenta);
	     			if(parseFloat(precioVenta)==0) tasa = 0;
	     			$("#precioVenta").val(formatStandar(parseFloat(precioVenta),nroDecimal));
	     			$("#precioArriendo").val(formatStandar(parseFloat(precioArriendo),nroDecimal));
	     			$("#precioMinimo").val(formatStandar(parseFloat(precioMinimo),nroDecimal));
	     			$("#permanenciaMinima").val(formatStandar(parseFloat(permanenciaMinima),0));
	     			$("#precioTasa").val(formatPorcentaje(parseFloat(tasa*100)));
	     		"""),format.raw/*956.9*/("""}"""),format.raw/*956.10*/("""
	     	"""),format.raw/*957.8*/("""}"""),format.raw/*957.9*/("""
        """),format.raw/*958.9*/("""}"""),format.raw/*958.10*/(""");
	"""),format.raw/*959.2*/("""}"""),format.raw/*959.3*/("""
	
	"""),format.raw/*961.2*/("""const grabaNuevoEquipo = () =>"""),format.raw/*961.32*/("""{"""),format.raw/*961.33*/("""
		
		"""),format.raw/*963.3*/("""nuevoEquipoCodigo = $("#nuevoEquipoCodigo").val().trim();
		nuevoEquipoNombre = $("#nuevoEquipoNombre").val().trim();
		if(nuevoEquipoCodigo=="")"""),format.raw/*965.28*/("""{"""),format.raw/*965.29*/("""
			"""),format.raw/*966.4*/("""alertify.alert("Debe ingresar un código de equipo", function () """),format.raw/*966.68*/("""{"""),format.raw/*966.69*/("""
 				"""),format.raw/*967.6*/("""$("#nuevoEquipoCodigo").focus();
     		"""),format.raw/*968.8*/("""}"""),format.raw/*968.9*/(""");
		"""),format.raw/*969.3*/("""}"""),format.raw/*969.4*/("""else if(nuevoEquipoNombre=="")"""),format.raw/*969.34*/("""{"""),format.raw/*969.35*/("""
			"""),format.raw/*970.4*/("""alertify.alert("Debe ingresar un nombre de equipo", function () """),format.raw/*970.68*/("""{"""),format.raw/*970.69*/("""
 				"""),format.raw/*971.6*/("""$("#nuevoEquipoNombre").focus();
     		"""),format.raw/*972.8*/("""}"""),format.raw/*972.9*/(""");
		"""),format.raw/*973.3*/("""}"""),format.raw/*973.4*/("""else"""),format.raw/*973.8*/("""{"""),format.raw/*973.9*/("""
			"""),format.raw/*974.4*/("""$("#modalNuevoEquipo").modal('hide');
			let auxOpcion = document.getElementById("nuevoEquipoId_unidad");
			let auxEquipoUnidad = auxOpcion.options[auxOpcion.selectedIndex].text;
			
			var formData = new FormData();
			formData.append('id_grupo',$("#nuevoEquipoId_grupo").val());
			formData.append('codigo',$("#nuevoEquipoCodigo").val());
			formData.append('nombre',$("#nuevoEquipoNombre").val());
			formData.append('id_fabrica',$("#nuevoEquipoId_fabrica").val());
			formData.append('id_unidad',$("#nuevoEquipoId_unidad").val());
			formData.append('desdeMenu',"COMPRA");
			formData.append('kg',$("#nuevoEquipoId_kg").val().replace(/,/g,''));
			formData.append('m2',$("#nuevoEquipoId_m2").val().replace(/,/g,''));
			formData.append('id_propiedad',$("#nuevoId_propiedad").val().replace(/,/g,''));

			$.ajax("""),format.raw/*989.11*/("""{"""),format.raw/*989.12*/("""
	            """),format.raw/*990.14*/("""url: "/nuevoEquipoAjax/",
	            type: "POST",
	            method: "POST",
	            data: formData,
	            cache: false,
	            contentType: false,
		     	processData: false,
		     	success: function(respuesta)"""),format.raw/*997.37*/("""{"""),format.raw/*997.38*/("""
		     		"""),format.raw/*998.10*/("""if(respuesta=="existe")"""),format.raw/*998.33*/("""{"""),format.raw/*998.34*/("""
		     			"""),format.raw/*999.11*/("""alertify.alert("El código de equipo ya existe, intente con otro", function () """),format.raw/*999.89*/("""{"""),format.raw/*999.90*/("""
		     				"""),format.raw/*1000.12*/("""$("#nuevoEquipoCodigo").val("");
			     		"""),format.raw/*1001.11*/("""}"""),format.raw/*1001.12*/(""");
		     		"""),format.raw/*1002.10*/("""}"""),format.raw/*1002.11*/("""else if(respuesta=="error")"""),format.raw/*1002.38*/("""{"""),format.raw/*1002.39*/("""
		     			"""),format.raw/*1003.11*/("""alertify.alert(msgError, function () """),format.raw/*1003.48*/("""{"""),format.raw/*1003.49*/("""
			     			"""),format.raw/*1004.12*/("""location.href = "/";
			     		"""),format.raw/*1005.11*/("""}"""),format.raw/*1005.12*/(""");
		     		"""),format.raw/*1006.10*/("""}"""),format.raw/*1006.11*/("""else"""),format.raw/*1006.15*/("""{"""),format.raw/*1006.16*/("""
		     			
		     			"""),format.raw/*1008.11*/("""let rs = respuesta.split("_");
		     			auxCodEquip = $("#nuevoEquipoCodigo").val();
		     			auxNomEquip = $("#nuevoEquipoNombre").val();
		     			auxKgEquip = rs[1];
		     			auxM2Equip = rs[2];
		     			auxUnEquip = auxEquipoUnidad;
		     			selectEquipo(rs[0]);
		     			
		     			$("#nuevoEquipoCodigo").val("");
		     			$("#nuevoEquipoNombre").val("")
		     			
		     			
		     			
		     			
		     		"""),format.raw/*1022.10*/("""}"""),format.raw/*1022.11*/("""
		     	"""),format.raw/*1023.9*/("""}"""),format.raw/*1023.10*/("""
	        """),format.raw/*1024.10*/("""}"""),format.raw/*1024.11*/(""");
		"""),format.raw/*1025.3*/("""}"""),format.raw/*1025.4*/("""
	"""),format.raw/*1026.2*/("""}"""),format.raw/*1026.3*/("""
	
	"""),format.raw/*1028.2*/("""const verificaCodigo = (codigo) => """),format.raw/*1028.37*/("""{"""),format.raw/*1028.38*/("""
		"""),format.raw/*1029.3*/("""var formData = new FormData();
	  	formData.append('codigo',codigo);
        $.ajax("""),format.raw/*1031.16*/("""{"""),format.raw/*1031.17*/("""
            """),format.raw/*1032.13*/("""url: "/verificaCodigoEquipoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*1039.36*/("""{"""),format.raw/*1039.37*/("""
	     		"""),format.raw/*1040.9*/("""if(respuesta=="existe")"""),format.raw/*1040.32*/("""{"""),format.raw/*1040.33*/("""
	     			"""),format.raw/*1041.10*/("""alertify.alert("El código de equipo ya existe, intente con otro", function () """),format.raw/*1041.88*/("""{"""),format.raw/*1041.89*/("""
	     				"""),format.raw/*1042.11*/("""$("#nuevoEquipoCodigo").val("");
		     		"""),format.raw/*1043.10*/("""}"""),format.raw/*1043.11*/(""");
	     		"""),format.raw/*1044.9*/("""}"""),format.raw/*1044.10*/("""
	     		"""),format.raw/*1045.9*/("""if(respuesta=="error")"""),format.raw/*1045.31*/("""{"""),format.raw/*1045.32*/("""
	     			"""),format.raw/*1046.10*/("""alertify.alert(msgError, function () """),format.raw/*1046.47*/("""{"""),format.raw/*1046.48*/("""
		     			"""),format.raw/*1047.11*/("""location.href = "/";
		     		"""),format.raw/*1048.10*/("""}"""),format.raw/*1048.11*/(""");
	     		"""),format.raw/*1049.9*/("""}"""),format.raw/*1049.10*/("""
	     	"""),format.raw/*1050.8*/("""}"""),format.raw/*1050.9*/("""
        """),format.raw/*1051.9*/("""}"""),format.raw/*1051.10*/(""");
	"""),format.raw/*1052.2*/("""}"""),format.raw/*1052.3*/("""
	
	"""),format.raw/*1054.2*/("""const descargaDocumento = (nombreDOC) => """),format.raw/*1054.43*/("""{"""),format.raw/*1054.44*/("""
		  """),format.raw/*1055.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*1057.2*/("""}"""),format.raw/*1057.3*/("""
	

	"""),format.raw/*1060.2*/("""let extArray = new Array(".gif", ".jpg", ".png", ".jpeg", ".pdf", ".xls", ".doc", ".xlsx", ".docx", ".tar", ".zip", ".rar");
	const LimitAttach = (form, file) => """),format.raw/*1061.38*/("""{"""),format.raw/*1061.39*/("""
		"""),format.raw/*1062.3*/("""let allowSubmit = false;
		if (!file) return;
		while (file.indexOf("\\") != -1)"""),format.raw/*1064.35*/("""{"""),format.raw/*1064.36*/("""
			"""),format.raw/*1065.4*/("""file = file.slice(file.indexOf("\\") + 1);
		"""),format.raw/*1066.3*/("""}"""),format.raw/*1066.4*/("""
		"""),format.raw/*1067.3*/("""let extencion = file.slice(file.lastIndexOf(".")).toLowerCase();
		for (var i = 0; i < extArray.length; i++) """),format.raw/*1068.45*/("""{"""),format.raw/*1068.46*/("""
			"""),format.raw/*1069.4*/("""if (extArray[i] == extencion) """),format.raw/*1069.34*/("""{"""),format.raw/*1069.35*/(""" """),format.raw/*1069.36*/("""allowSubmit = true; break; """),format.raw/*1069.63*/("""}"""),format.raw/*1069.64*/("""
		"""),format.raw/*1070.3*/("""}"""),format.raw/*1070.4*/("""
		"""),format.raw/*1071.3*/("""if (allowSubmit) """),format.raw/*1071.20*/("""{"""),format.raw/*1071.21*/("""
			"""),format.raw/*1072.4*/("""var file = $("#docAdjunto")[0].files[0];
			var fileSize = file.size; //tamaño del archivo
			if(fileSize>100000000)"""),format.raw/*1074.26*/("""{"""),format.raw/*1074.27*/("""
				"""),format.raw/*1075.5*/("""alert("Se permiten únicamente archivos que no superen los 100 MB,"
				+" el que se intenta subir pesa: "+Math.round(file.size/10)/100+" KB");
				form.docAdjunto.value="";
			"""),format.raw/*1078.4*/("""}"""),format.raw/*1078.5*/("""
		"""),format.raw/*1079.3*/("""}"""),format.raw/*1079.4*/("""else"""),format.raw/*1079.8*/("""{"""),format.raw/*1079.9*/("""
			"""),format.raw/*1080.4*/("""alert("Se permiten únicamente archivos con la extención: " 
			+ (extArray.join("  ")) + "\nPor favor, seleccione otro archivo "
			+ "e intente de nuevo.");
			form.docAdjunto.value="";
		"""),format.raw/*1084.3*/("""}"""),format.raw/*1084.4*/("""
	"""),format.raw/*1085.2*/("""}"""),format.raw/*1085.3*/("""
	
	


	
"""),format.raw/*1091.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listProveedor:List[tables.Proveedor],listEquipo:List[tables.Equipo],listMoneda:List[tables.Moneda],listBodegas:List[List[String]],listMon:List[tables.Moneda],listGrupos:List[tables.Grupo],listFabrica:List[tables.Fabrica],listUnidades:List[tables.Unidad],factura:tables.Factura,detalleFactura:List[List[String]],listSucursales:List[tables.Sucursal],listPropiedad:List[tables.Propiedad]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listProveedor,listEquipo,listMoneda,listBodegas,listMon,listGrupos,listFabrica,listUnidades,factura,detalleFactura,listSucursales,listPropiedad)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Proveedor],List[tables.Equipo],List[tables.Moneda],List[List[String]],List[tables.Moneda],List[tables.Grupo],List[tables.Fabrica],List[tables.Unidad],tables.Factura,List[List[String]],List[tables.Sucursal],List[tables.Propiedad]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listProveedor,listEquipo,listMoneda,listBodegas,listMon,listGrupos,listFabrica,listUnidades,factura,detalleFactura,listSucursales,listPropiedad) => apply(mapDiccionario,mapPermiso,userMnu,listProveedor,listEquipo,listMoneda,listBodegas,listMon,listGrupos,listFabrica,listUnidades,factura,detalleFactura,listSucursales,listPropiedad)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCompras/compraModifica.scala.html
                  HASH: 05d16c8975c73d483faa25d4d5ff345665c586fe
                  MATRIX: 1255->1|1854->507|1887->515|1903->523|1942->525|1970->528|2038->576|2067->578|2262->747|2372->836|2403->840|2857->1267|2880->1281|2912->1292|2941->1293|3063->1388|3079->1395|3108->1403|3284->1552|3300->1559|3340->1577|3474->1684|3490->1691|3520->1700|4004->2157|4020->2164|4064->2187|4616->2712|4632->2719|4665->2731|5289->3328|5307->3337|5363->3371|5452->3433|5470->3442|5525->3476|5791->3715|5830->3716|5870->3728|5936->3775|5975->3776|6015->3788|6088->3830|6130->3844|6392->4079|6431->4080|6470->4091|6506->4108|6545->4109|6584->4120|6652->4161|6668->4168|6705->4184|6826->4274|6862->4283|7303->4696|7320->4703|7361->4722|8260->5594|8328->5645|8368->5646|8405->5655|8478->5700|8494->5706|8523->5713|8597->5759|8613->5765|8642->5772|8724->5826|8751->5831|8782->5834|8798->5840|8828->5848|8910->5902|8937->5907|8968->5910|8984->5916|9014->5924|9090->5972|9106->5978|9135->5985|9295->6117|9311->6123|9340->6130|9395->6157|9422->6162|9635->6347|9651->6353|9680->6360|9733->6385|9749->6391|9778->6398|9890->6482|9930->6483|9956->6489|9996->6490|10038->6500|10077->6510|10223->6628|10250->6633|10308->6662|10325->6668|10355->6675|10433->6725|10449->6731|10478->6738|10509->6741|10525->6747|10554->6754|10613->6785|10652->6807|10692->6808|10746->6833|10790->6849|10803->6852|10833->6860|10865->6864|10878->6867|10914->6881|10968->6903|11014->6920|11184->7062|11200->7068|11229->7075|11284->7102|11311->7107|11524->7292|11540->7298|11569->7305|11622->7330|11638->7336|11667->7343|11838->7486|11865->7491|11910->7508|11926->7514|11955->7521|12146->7684|12173->7689|12205->7693|12221->7699|12251->7707|12333->7761|12373->7762|12416->7776|12455->7795|12495->7796|12538->7810|12593->7833|12635->7846|12679->7862|12695->7868|12725->7876|12756->7879|12772->7885|12802->7893|12853->7916|12898->7944|12938->7945|12981->7959|13025->7975|13040->7980|13070->7988|13101->7991|13116->7996|13150->8008|13205->8031|13252->8049|13394->8163|13421->8168|13459->8178|13475->8184|13505->8192|13587->8246|13614->8251|13646->8255|13662->8261|13692->8269|13898->8447|13925->8452|14056->8555|14072->8561|14101->8568|14200->8639|14227->8644|14259->8648|14275->8654|14305->8662|14338->8667|14354->8673|14384->8681|14460->8729|14500->8730|14543->8744|14582->8763|14622->8764|14665->8778|14720->8801|14762->8814|14806->8830|14822->8836|14852->8844|14884->8848|14900->8854|14930->8862|15067->8971|15094->8976|15139->8993|15155->8999|15185->9007|15265->9059|15292->9064|15337->9081|15353->9087|15383->9095|15506->9190|15546->9191|15587->9203|15658->9246|15674->9252|15703->9259|15835->9346|15874->9356|15935->9385|15972->9394|18687->12082|18729->12107|18769->12108|18806->12117|18861->12144|18877->12150|18911->12162|18956->12179|18972->12185|19006->12197|19051->12213|19068->12219|19099->12227|19144->12243|19161->12249|19192->12257|19247->12284|19263->12290|19297->12302|19342->12319|19358->12325|19388->12333|19481->12398|19497->12404|19530->12415|19604->12461|19620->12467|19654->12479|19728->12525|19744->12531|19778->12543|19852->12589|19868->12595|19903->12608|19964->12637|19999->12644|21099->13716|21140->13740|21180->13741|21220->13752|21264->13768|21279->13773|21309->13781|21340->13784|21355->13789|21389->13801|21441->13821|21478->13830|21744->14068|21767->14081|21804->14096|21835->14099|21858->14112|21899->14131|21947->14151|21991->14178|22031->14179|22071->14190|22115->14206|22130->14211|22160->14219|22191->14222|22206->14227|22240->14239|22292->14259|22329->14268|23487->15398|23529->15423|23569->15424|23609->15435|23653->15451|23668->15456|23693->15459|23724->15462|23739->15467|23770->15476|23822->15496|23859->15505|24161->15779|24204->15805|24244->15806|24284->15817|24328->15833|24343->15838|24368->15841|24399->15844|24414->15849|24443->15856|24495->15876|24532->15885|26978->18300|27008->18302|27924->19190|27948->19204|27981->19215|28103->19310|28148->19338|28188->19339|28223->19346|28297->19392|28313->19398|28343->19406|28407->19442|28423->19448|28454->19457|28522->19497|28538->19503|28574->19517|28646->19561|28662->19567|28692->19575|28792->19647|28808->19653|28839->19662|28911->19706|28927->19712|28963->19726|29035->19770|29051->19776|29085->19788|29140->19812|29174->19818|29549->20164|29579->20165|29613->20171|29680->20209|29710->20210|29745->20217|29917->20360|29947->20361|29987->20372|30093->20449|30123->20450|30156->20455|30185->20456|30214->20457|30285->20499|30315->20500|30350->20507|30522->20650|30552->20651|30592->20662|30698->20739|30728->20740|30761->20745|30790->20746|30819->20747|30939->20839|30968->20840|31044->20887|31074->20888|31105->20891|31199->20957|31216->20964|31246->20972|31364->21061|31394->21062|31436->21075|31705->21315|31735->21316|31765->21317|31802->21326|31832->21327|31864->21331|31893->21332|31925->21336|32005->21387|32035->21388|32066->21391|32255->21551|32285->21552|32327->21565|32595->21804|32625->21805|32655->21806|32692->21815|32722->21816|32771->21837|32800->21838|32832->21842|32903->21884|32933->21885|32964->21888|33083->21978|33113->21979|33155->21992|33414->22222|33444->22223|33474->22224|33511->22233|33541->22234|33656->22321|33685->22322|33717->22326|33789->22369|33819->22370|33850->22373|33945->22439|33975->22440|34007->22444|34104->22512|34134->22513|34168->22519|34229->22552|34258->22553|34291->22558|34320->22559|34352->22563|34381->22564|34413->22568|34585->22711|34615->22712|34658->22726|34932->22971|34962->22972|35001->22982|35053->23005|35083->23006|35123->23017|35254->23118|35285->23119|35326->23131|35380->23157|35397->23164|35431->23176|35474->23190|35504->23191|35545->23203|35575->23204|35608->23208|35638->23209|35673->23216|35739->23254|35768->23255|35807->23265|35858->23287|35888->23288|35928->23299|35994->23336|36024->23337|36065->23349|36125->23380|36155->23381|36196->23393|36226->23394|36263->23403|36293->23404|36332->23414|36362->23415|36395->23420|36424->23421|36458->23427|36487->23428|36519->23432|36572->23458|36610->23479|36650->23480|36681->23483|36721->23495|36736->23500|36766->23508|36796->23510|36811->23515|36854->23536|36890->23541|36925->23548|37078->23673|37102->23687|37131->23694|37202->23736|37232->23737|37263->23740|38749->25199|38788->25221|38828->25222|38864->25230|38916->25254|38929->25257|38959->25265|38990->25268|39003->25271|39039->25285|39086->25301|39121->25308|40071->26230|40116->26258|40156->26259|40199->26273|40262->26308|40277->26313|40307->26321|40339->26325|40354->26330|40388->26342|40445->26367|40492->26385|41673->27538|41702->27539|41734->27543|41793->27573|41823->27574|41854->27577|41964->27659|41993->27660|42025->27664|42083->27693|42113->27694|42144->27697|42962->28487|42991->28488|43023->28492|43078->28518|43108->28519|43140->28523|43216->28570|43246->28571|43279->28576|43380->28649|43409->28650|43532->28744|43562->28745|43595->28750|43697->28824|43726->28825|43843->28913|43873->28914|43906->28919|44008->28993|44037->28994|44151->29079|44181->29080|44214->29085|44316->29159|44345->29160|44420->29207|44449->29208|44483->29214|44537->29239|44567->29240|44598->29243|44679->29295|44709->29296|44741->29300|44845->29375|44875->29376|44908->29381|44957->29402|44986->29403|45019->29408|45048->29409|45080->29413|45109->29414|45141->29418|45395->29643|45425->29644|45463->29654|45542->29704|45572->29705|45606->29711|45677->29753|45707->29754|45742->29761|45789->29780|45818->29781|45851->29786|45880->29787|45929->29807|45959->29808|45993->29814|46039->29832|46068->29833|46101->29837|46131->29838|46165->29844|46287->29937|46318->29938|46353->29945|46405->29968|46435->29969|46470->29976|46499->29977|46531->29981|46560->29982|46592->29986|46622->29987|46655->29992|46788->30096|46818->30097|46852->30103|46902->30125|46932->30126|46966->30132|46995->30133|47026->30136|47055->30137|47086->30140|47129->30155|47158->30156|47190->30160|47266->30207|47296->30208|47327->30211|47428->30283|47458->30284|47490->30288|47659->30428|47689->30429|47732->30443|47991->30673|48021->30674|48055->30680|48091->30687|48121->30688|48166->30704|48456->30965|48486->30966|48522->30974|48668->31091|48698->31092|48739->31104|48769->31105|48808->31116|48838->31117|48877->31127|48907->31128|48940->31133|48969->31134|49001->31138|49030->31139|49062->31143|49158->31210|49188->31211|49225->31220|49255->31221|49288->31226|49317->31227|49347->31229|49376->31230|49408->31234|49499->31296|49529->31297|49560->31300|49646->31357|49676->31358|49708->31362|49878->31503|49908->31504|49951->31518|50227->31765|50257->31766|50296->31776|50515->31967|50545->31968|50584->31978|50614->31979|50647->31984|50676->31985|50706->31987|50735->31988|50773->31998|50860->32056|50890->32057|50921->32060|51076->32186|51106->32187|51148->32200|51418->32441|51448->32442|51485->32451|51589->32527|51618->32528|51655->32537|51685->32538|51717->32542|51746->32543|51781->32550|51872->32612|51902->32613|51933->32616|52244->32898|52274->32899|52316->32912|52577->33144|52607->33145|52644->33154|52695->33176|52725->33177|52764->33187|52830->33224|52860->33225|52900->33236|52959->33266|52989->33267|53028->33278|53058->33279|53095->33288|53144->33308|53174->33309|53213->33319|54337->34415|54367->34416|54403->34424|54432->34425|54469->34434|54499->34435|54531->34439|54560->34440|54592->34444|54651->34474|54681->34475|54715->34481|54889->34626|54919->34627|54951->34631|55044->34695|55074->34696|55108->34702|55176->34742|55205->34743|55238->34748|55267->34749|55326->34779|55356->34780|55388->34784|55481->34848|55511->34849|55545->34855|55613->34895|55642->34896|55675->34901|55704->34902|55736->34906|55765->34907|55797->34911|56642->35727|56672->35728|56715->35742|56979->35977|57009->35978|57048->35988|57100->36011|57130->36012|57170->36023|57277->36101|57307->36102|57349->36114|57422->36157|57453->36158|57495->36170|57526->36171|57583->36198|57614->36199|57655->36210|57722->36247|57753->36248|57795->36260|57856->36291|57887->36292|57929->36304|57960->36305|57994->36309|58025->36310|58077->36332|58528->36753|58559->36754|58597->36763|58628->36764|58668->36774|58699->36775|58733->36780|58763->36781|58794->36783|58824->36784|58857->36788|58922->36823|58953->36824|58985->36827|59099->36911|59130->36912|59173->36925|59440->37162|59471->37163|59509->37172|59562->37195|59593->37196|59633->37206|59741->37284|59772->37285|59813->37296|59885->37338|59916->37339|59956->37350|59987->37351|60025->37360|60077->37382|60108->37383|60148->37393|60215->37430|60246->37431|60287->37442|60347->37472|60378->37473|60418->37484|60449->37485|60486->37493|60516->37494|60554->37503|60585->37504|60618->37508|60648->37509|60681->37513|60752->37554|60783->37555|60817->37560|60950->37664|60980->37665|61014->37670|61206->37832|61237->37833|61269->37836|61379->37916|61410->37917|61443->37921|61517->37966|61547->37967|61579->37970|61718->38079|61749->38080|61782->38084|61842->38114|61873->38115|61904->38116|61961->38143|61992->38144|62024->38147|62054->38148|62086->38151|62133->38168|62164->38169|62197->38173|62343->38289|62374->38290|62408->38295|62613->38471|62643->38472|62675->38475|62705->38476|62738->38480|62768->38481|62801->38485|63019->38674|63049->38675|63080->38677|63110->38678|63148->38687
                  LINES: 28->1|36->5|38->7|38->7|38->7|40->9|40->9|41->10|43->12|43->12|44->13|52->21|52->21|52->21|52->21|54->23|54->23|54->23|56->25|56->25|56->25|59->28|59->28|59->28|72->41|72->41|72->41|87->56|87->56|87->56|102->71|102->71|102->71|103->72|103->72|103->72|109->78|109->78|110->79|111->80|111->80|112->81|113->82|114->83|118->87|118->87|119->88|120->89|120->89|121->90|121->90|121->90|121->90|124->93|125->94|136->105|136->105|136->105|164->133|164->133|164->133|165->134|166->135|166->135|166->135|167->136|167->136|167->136|168->137|168->137|168->137|168->137|168->137|169->138|169->138|169->138|169->138|169->138|170->139|170->139|170->139|173->142|173->142|173->142|174->143|174->143|178->147|178->147|178->147|178->147|178->147|178->147|179->148|179->148|179->148|179->148|179->148|180->149|182->151|182->151|182->151|182->151|182->151|183->152|183->152|183->152|183->152|183->152|183->152|184->153|184->153|184->153|185->154|185->154|185->154|185->154|185->154|185->154|185->154|186->155|187->156|191->160|191->160|191->160|192->161|192->161|196->165|196->165|196->165|196->165|196->165|196->165|199->168|199->168|199->168|199->168|199->168|203->172|203->172|203->172|203->172|203->172|204->173|204->173|205->174|206->175|206->175|207->176|208->177|209->178|209->178|209->178|209->178|209->178|209->178|209->178|210->179|210->179|210->179|211->180|211->180|211->180|211->180|211->180|211->180|211->180|212->181|213->182|216->185|216->185|216->185|216->185|216->185|217->186|217->186|217->186|217->186|217->186|222->191|222->191|224->193|224->193|224->193|225->194|225->194|225->194|225->194|225->194|225->194|225->194|225->194|226->195|226->195|227->196|228->197|228->197|229->198|230->199|231->200|231->200|231->200|231->200|231->200|231->200|231->200|235->204|235->204|235->204|235->204|235->204|236->205|236->205|236->205|236->205|236->205|238->207|238->207|239->208|239->208|239->208|239->208|242->211|243->212|245->214|246->215|324->293|324->293|324->293|325->294|325->294|325->294|325->294|325->294|325->294|325->294|325->294|325->294|325->294|325->294|325->294|325->294|326->295|326->295|326->295|326->295|326->295|326->295|327->296|327->296|327->296|328->297|328->297|328->297|329->298|329->298|329->298|330->299|330->299|330->299|332->301|333->302|365->334|365->334|365->334|366->335|366->335|366->335|366->335|366->335|366->335|366->335|367->336|368->337|376->345|376->345|376->345|376->345|376->345|376->345|377->346|377->346|377->346|378->347|378->347|378->347|378->347|378->347|378->347|378->347|379->348|380->349|412->381|412->381|412->381|413->382|413->382|413->382|413->382|413->382|413->382|413->382|414->383|415->384|424->393|424->393|424->393|425->394|425->394|425->394|425->394|425->394|425->394|425->394|426->395|427->396|495->464|497->466|515->484|515->484|515->484|521->490|521->490|521->490|522->491|523->492|523->492|523->492|524->493|524->493|524->493|525->494|525->494|525->494|526->495|526->495|526->495|528->497|528->497|528->497|529->498|529->498|529->498|530->499|530->499|530->499|532->501|533->502|549->518|549->518|551->520|551->520|551->520|552->521|555->524|555->524|556->525|557->526|557->526|558->527|558->527|558->527|560->529|560->529|561->530|564->533|564->533|565->534|566->535|566->535|567->536|567->536|567->536|570->539|570->539|572->541|572->541|573->542|574->543|574->543|574->543|577->546|577->546|578->547|585->554|585->554|585->554|586->555|586->555|587->556|587->556|589->558|589->558|589->558|590->559|594->563|594->563|595->564|602->571|602->571|602->571|603->572|603->572|605->574|605->574|607->576|607->576|607->576|608->577|610->579|610->579|611->580|618->587|618->587|618->587|619->588|619->588|623->592|623->592|625->594|625->594|625->594|626->595|627->596|627->596|628->597|628->597|628->597|629->598|630->599|630->599|631->600|631->600|631->600|631->600|632->601|635->604|635->604|636->605|643->612|643->612|644->613|644->613|644->613|645->614|645->614|645->614|646->615|646->615|646->615|646->615|647->616|647->616|648->617|648->617|648->617|648->617|649->618|650->619|650->619|651->620|651->620|651->620|652->621|652->621|652->621|653->622|654->623|654->623|655->624|655->624|656->625|656->625|657->626|657->626|658->627|658->627|660->629|660->629|662->631|663->632|663->632|663->632|664->633|664->633|664->633|664->633|664->633|664->633|664->633|665->634|669->638|674->643|674->643|674->643|676->645|676->645|677->646|716->685|716->685|716->685|717->686|717->686|717->686|717->686|717->686|717->686|717->686|718->687|719->688|742->711|742->711|742->711|743->712|743->712|743->712|743->712|743->712|743->712|743->712|744->713|745->714|772->741|772->741|774->743|774->743|774->743|775->744|778->747|778->747|780->749|780->749|780->749|781->750|801->770|801->770|803->772|803->772|803->772|804->773|805->774|805->774|806->775|808->777|808->777|810->779|810->779|811->780|813->782|813->782|815->784|815->784|816->785|818->787|818->787|820->789|820->789|821->790|823->792|823->792|824->793|824->793|827->796|827->796|827->796|828->797|829->798|829->798|830->799|831->800|831->800|832->801|833->802|833->802|834->803|834->803|834->803|834->803|835->804|838->807|838->807|840->809|841->810|841->810|842->811|843->812|843->812|844->813|845->814|845->814|846->815|846->815|848->817|848->817|849->818|850->819|850->819|850->819|850->819|851->820|851->820|851->820|852->821|853->822|853->822|854->823|854->823|855->824|855->824|855->824|855->824|856->825|857->826|857->826|858->827|859->828|859->828|860->829|860->829|861->830|861->830|862->831|863->832|863->832|865->834|865->834|865->834|866->835|867->836|867->836|868->837|872->841|872->841|873->842|880->849|880->849|881->850|881->850|881->850|882->851|889->858|889->858|890->859|892->861|892->861|893->862|893->862|894->863|894->863|895->864|895->864|896->865|896->865|896->865|896->865|897->866|897->866|897->866|898->867|898->867|899->868|899->868|900->869|900->869|902->871|902->871|902->871|903->872|904->873|904->873|905->874|908->877|908->877|909->878|916->885|916->885|917->886|920->889|920->889|921->890|921->890|922->891|922->891|923->892|923->892|928->897|928->897|928->897|929->898|932->901|932->901|933->902|940->909|940->909|941->910|942->911|942->911|943->912|943->912|944->913|944->913|948->917|948->917|948->917|949->918|956->925|956->925|957->926|964->933|964->933|965->934|965->934|965->934|966->935|966->935|966->935|967->936|968->937|968->937|969->938|969->938|970->939|970->939|970->939|971->940|987->956|987->956|988->957|988->957|989->958|989->958|990->959|990->959|992->961|992->961|992->961|994->963|996->965|996->965|997->966|997->966|997->966|998->967|999->968|999->968|1000->969|1000->969|1000->969|1000->969|1001->970|1001->970|1001->970|1002->971|1003->972|1003->972|1004->973|1004->973|1004->973|1004->973|1005->974|1020->989|1020->989|1021->990|1028->997|1028->997|1029->998|1029->998|1029->998|1030->999|1030->999|1030->999|1031->1000|1032->1001|1032->1001|1033->1002|1033->1002|1033->1002|1033->1002|1034->1003|1034->1003|1034->1003|1035->1004|1036->1005|1036->1005|1037->1006|1037->1006|1037->1006|1037->1006|1039->1008|1053->1022|1053->1022|1054->1023|1054->1023|1055->1024|1055->1024|1056->1025|1056->1025|1057->1026|1057->1026|1059->1028|1059->1028|1059->1028|1060->1029|1062->1031|1062->1031|1063->1032|1070->1039|1070->1039|1071->1040|1071->1040|1071->1040|1072->1041|1072->1041|1072->1041|1073->1042|1074->1043|1074->1043|1075->1044|1075->1044|1076->1045|1076->1045|1076->1045|1077->1046|1077->1046|1077->1046|1078->1047|1079->1048|1079->1048|1080->1049|1080->1049|1081->1050|1081->1050|1082->1051|1082->1051|1083->1052|1083->1052|1085->1054|1085->1054|1085->1054|1086->1055|1088->1057|1088->1057|1091->1060|1092->1061|1092->1061|1093->1062|1095->1064|1095->1064|1096->1065|1097->1066|1097->1066|1098->1067|1099->1068|1099->1068|1100->1069|1100->1069|1100->1069|1100->1069|1100->1069|1100->1069|1101->1070|1101->1070|1102->1071|1102->1071|1102->1071|1103->1072|1105->1074|1105->1074|1106->1075|1109->1078|1109->1078|1110->1079|1110->1079|1110->1079|1110->1079|1111->1080|1115->1084|1115->1084|1116->1085|1116->1085|1122->1091
                  -- GENERATED --
              */
          