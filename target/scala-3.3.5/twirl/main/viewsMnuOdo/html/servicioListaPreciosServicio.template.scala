
package viewsMnuOdo.html

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

object servicioListaPreciosServicio extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template7[Map[String,String],Map[String,String],utilities.UserMnu,tables.Servicio,List[tables.ListaPrecioServicio],List[tables.Moneda],List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
servicio: tables.Servicio, listPrecio: List[tables.ListaPrecioServicio], listMoneda: List[tables.Moneda], listBodegasNoServ: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

"""),_display_(/*6.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.50*/("""
	"""),format.raw/*7.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*8.4*/barraTitulo(mapDiccionario, "MANTENCION PRECIOS EN "+mapDiccionario.get("BODEGA")+"S/PROYECTOS POR SERVICIO", "SERVICIO: "+servicio.getNombre().toUpperCase())),format.raw/*8.162*/("""
		"""),format.raw/*9.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-11 col-md-10 col-lg-11">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR> 
							<TH>VIGENTE</TH>
							<TH>"""),_display_(/*15.13*/mapDiccionario/*15.27*/.get("BODEGA")),format.raw/*15.41*/("""</TH>
							<TH>NRO.COTI</TH>
							<TH>CODIGO</TH>
							<TH>SERVICIO</TH>
							<TH>UNIDAD</TH>
							<TH>MONEDA</TH>
							<TH>PRECIO</TH>
							<TH>APLICA<br>MINIMO</TH>
							<TH>CANT<br>MINIMO</TH>
							<TH>PRECIO<br>ADICIONAL</TH>
							<TH>VARIABLE</TH>
							<TH style="min-width:80px;">FECHA PRECIO</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*30.8*/for(lista2 <- listPrecio) yield /*30.33*/{_display_(Seq[Any](format.raw/*30.34*/("""
							"""),format.raw/*31.8*/("""<tr class="align-middle">
								<td  style="text-align:center;">
									<input type="hidden" id="vigente_"""),_display_(/*33.44*/lista2/*33.50*/.getId_bodegaEmpresa()),format.raw/*33.72*/("""_"""),_display_(/*33.74*/lista2/*33.80*/.getId_cotiOdo),format.raw/*33.94*/("""" value=""""),_display_(/*33.104*/lista2/*33.110*/.getVigente()),format.raw/*33.123*/("""">
									"""),_display_(if(lista2.getVigente()==1)/*34.37*/{_display_(Seq[Any](format.raw/*34.38*/("""
										"""),format.raw/*35.11*/("""<input type="checkbox" checked onchange="modificaVigente('"""),_display_(/*35.70*/lista2/*35.76*/.getId_bodegaEmpresa()),format.raw/*35.98*/("""','"""),_display_(/*35.102*/lista2/*35.108*/.getId_cotiOdo),format.raw/*35.122*/("""');">
									""")))}else/*36.15*/{_display_(Seq[Any](format.raw/*36.16*/("""
										"""),format.raw/*37.11*/("""<input type="checkbox" onchange="modificaVigente('"""),_display_(/*37.62*/lista2/*37.68*/.getId_bodegaEmpresa()),format.raw/*37.90*/("""','"""),_display_(/*37.94*/lista2/*37.100*/.getId_cotiOdo),format.raw/*37.114*/("""');">
									""")))}),format.raw/*38.11*/("""
								"""),format.raw/*39.9*/("""</td>
								<td>"""),_display_(/*40.14*/lista2/*40.20*/.getNomBodega()),format.raw/*40.35*/("""</td>
								<td style="text-align: center;">"""),_display_(/*41.42*/lista2/*41.48*/.getNumeroCotiOdo()),format.raw/*41.67*/("""</td>
								<td style="text-align: center">"""),_display_(/*42.41*/lista2/*42.47*/.getCodServicio()),format.raw/*42.64*/("""</td>
								<td>"""),_display_(/*43.14*/lista2/*43.20*/.getNomServicio()),format.raw/*43.37*/("""</td>
								<td style="text-align: center">"""),_display_(/*44.41*/lista2/*44.47*/.getNomUnidad()),format.raw/*44.62*/("""</td>
								<td>
									<select class="custom-select" style="text-align-last:center;"
										id="idmoneda_"""),_display_(/*47.25*/lista2/*47.31*/.getId_bodegaEmpresa()),format.raw/*47.53*/("""_"""),_display_(/*47.55*/lista2/*47.61*/.getId_cotiOdo),format.raw/*47.75*/("""" 
										onchange="modificaPorCampo('"""),_display_(/*48.40*/lista2/*48.46*/.getId_bodegaEmpresa()),format.raw/*48.68*/("""', 'id_moneda', value, '"""),_display_(/*48.93*/lista2/*48.99*/.getId_cotiOdo),format.raw/*48.113*/("""');">
										<option value=""""),_display_(/*49.27*/lista2/*49.33*/.getId_moneda()),format.raw/*49.48*/("""">"""),_display_(/*49.51*/lista2/*49.57*/.getNickMoneda()),format.raw/*49.73*/("""</option>
										"""),_display_(/*50.12*/for(lista <- listMoneda) yield /*50.36*/{_display_(Seq[Any](format.raw/*50.37*/("""
											"""),format.raw/*51.12*/("""<option value=""""),_display_(/*51.28*/lista/*51.33*/.id),format.raw/*51.36*/("""">"""),_display_(/*51.39*/lista/*51.44*/.nickName),format.raw/*51.53*/("""</option>
										""")))}),format.raw/*52.12*/("""
									"""),format.raw/*53.10*/("""</select>
								</td>
								<td>
								
									<div style="display:none">"""),_display_(/*57.37*/lista2/*57.43*/.getPrecioStr()),format.raw/*57.58*/("""</div>
								
									<input type="text" class="form-control right"
										id="precio_"""),_display_(/*60.23*/lista2/*60.29*/.getId_bodegaEmpresa()),format.raw/*60.51*/("""_"""),_display_(/*60.53*/lista2/*60.59*/.getId_cotiOdo),format.raw/*60.73*/(""""
										value=""""),_display_(/*61.19*/lista2/*61.25*/.getPrecioStr()),format.raw/*61.40*/(""""
										onfocus="value=value.replace(/,/g,'')" 
										onkeydown="return ingresoDouble(window.event)"
										onchange="modificaPorCampo('"""),_display_(/*64.40*/lista2/*64.46*/.getId_bodegaEmpresa()),format.raw/*64.68*/("""', 'precio', value, '"""),_display_(/*64.90*/lista2/*64.96*/.getId_cotiOdo),format.raw/*64.110*/("""');">
										
								</td>
								
								
								<td  style="text-align:center;">
									<input type="hidden" id="aplicaMinimo_"""),_display_(/*70.49*/lista2/*70.55*/.getId_bodegaEmpresa()),format.raw/*70.77*/("""_"""),_display_(/*70.79*/lista2/*70.85*/.getId_cotiOdo),format.raw/*70.99*/("""" value=""""),_display_(/*70.109*/lista2/*70.115*/.getAplicaMinimo()),format.raw/*70.133*/("""">
									"""),_display_(if(lista2.getAplicaMinimo()==1)/*71.42*/{_display_(Seq[Any](format.raw/*71.43*/("""
										"""),format.raw/*72.11*/("""<input type="checkbox" checked onchange="modificaAplicaMinimo('"""),_display_(/*72.75*/lista2/*72.81*/.getId_bodegaEmpresa()),format.raw/*72.103*/("""','"""),_display_(/*72.107*/lista2/*72.113*/.getId_cotiOdo),format.raw/*72.127*/("""');">
									""")))}else/*73.15*/{_display_(Seq[Any](format.raw/*73.16*/("""
										"""),format.raw/*74.11*/("""<input type="checkbox" onchange="modificaAplicaMinimo('"""),_display_(/*74.67*/lista2/*74.73*/.getId_bodegaEmpresa()),format.raw/*74.95*/("""','"""),_display_(/*74.99*/lista2/*74.105*/.getId_cotiOdo),format.raw/*74.119*/("""');">
									""")))}),format.raw/*75.11*/("""
								"""),format.raw/*76.9*/("""</td>
								<td>
								
									<div style="display:none">"""),_display_(/*79.37*/lista2/*79.43*/.getCantMinimoStr()),format.raw/*79.62*/("""</div>
									
									"""),_display_(if(lista2.getAplicaMinimo()==1)/*81.42*/{_display_(Seq[Any](format.raw/*81.43*/("""
										"""),format.raw/*82.11*/("""<input type="text" class="form-control right"
											id="cantMinimo_"""),_display_(/*83.28*/lista2/*83.34*/.getId_bodegaEmpresa()),format.raw/*83.56*/("""_"""),_display_(/*83.58*/lista2/*83.64*/.getId_cotiOdo),format.raw/*83.78*/(""""
											value=""""),_display_(/*84.20*/lista2/*84.26*/.getCantMinimoStr()),format.raw/*84.45*/(""""
											onfocus="value=value.replace(/,/g,'')" 
											onkeydown="return ingresoDouble(window.event)"
											onchange="modificaPorCampo('"""),_display_(/*87.41*/lista2/*87.47*/.getId_bodegaEmpresa()),format.raw/*87.69*/("""', 'cantMinimo', value, '"""),_display_(/*87.95*/lista2/*87.101*/.getId_cotiOdo),format.raw/*87.115*/("""');">
									""")))}else/*88.15*/{_display_(Seq[Any](format.raw/*88.16*/("""
										"""),format.raw/*89.11*/("""<input type="text" class="form-control right"
											id="cantMinimo_"""),_display_(/*90.28*/lista2/*90.34*/.getId_bodegaEmpresa()),format.raw/*90.56*/("""_"""),_display_(/*90.58*/lista2/*90.64*/.getId_cotiOdo),format.raw/*90.78*/(""""
											value=""""),_display_(/*91.20*/lista2/*91.26*/.getCantMinimoStr()),format.raw/*91.45*/(""""
											onfocus="value=value.replace(/,/g,'')" 
											onkeydown="return ingresoDouble(window.event)"
											onchange="modificaPorCampo('"""),_display_(/*94.41*/lista2/*94.47*/.getId_bodegaEmpresa()),format.raw/*94.69*/("""', 'cantMinimo', value, '"""),_display_(/*94.95*/lista2/*94.101*/.getId_cotiOdo),format.raw/*94.115*/("""');"
											disabled>
									""")))}),format.raw/*96.11*/("""
								"""),format.raw/*97.9*/("""</td>
								<td>
								
									<div style="display:none">"""),_display_(/*100.37*/lista2/*100.43*/.getPrecioAdicionalStr()),format.raw/*100.67*/("""</div>
									
									"""),_display_(if(lista2.getAplicaMinimo()==1)/*102.42*/{_display_(Seq[Any](format.raw/*102.43*/("""
										"""),format.raw/*103.11*/("""<input type="text" class="form-control right"
											id="precioAdicional_"""),_display_(/*104.33*/lista2/*104.39*/.getId_bodegaEmpresa()),format.raw/*104.61*/("""_"""),_display_(/*104.63*/lista2/*104.69*/.getId_cotiOdo),format.raw/*104.83*/(""""
											value=""""),_display_(/*105.20*/lista2/*105.26*/.getPrecioAdicionalStr()),format.raw/*105.50*/(""""
											onfocus="value=value.replace(/,/g,'')" 
											onkeydown="return ingresoDouble(window.event)"
											onchange="modificaPorCampo('"""),_display_(/*108.41*/lista2/*108.47*/.getId_bodegaEmpresa()),format.raw/*108.69*/("""', 'precioAdicional', value, '"""),_display_(/*108.100*/lista2/*108.106*/.getId_cotiOdo),format.raw/*108.120*/("""');">
									""")))}else/*109.15*/{_display_(Seq[Any](format.raw/*109.16*/("""
										"""),format.raw/*110.11*/("""<input type="text" class="form-control right"
											id="precioAdicional_"""),_display_(/*111.33*/lista2/*111.39*/.getId_bodegaEmpresa()),format.raw/*111.61*/("""_"""),_display_(/*111.63*/lista2/*111.69*/.getId_cotiOdo),format.raw/*111.83*/(""""
											value=""""),_display_(/*112.20*/lista2/*112.26*/.getPrecioAdicionalStr()),format.raw/*112.50*/(""""
											onfocus="value=value.replace(/,/g,'')" 
											onkeydown="return ingresoDouble(window.event)"
											onchange="modificaPorCampo('"""),_display_(/*115.41*/lista2/*115.47*/.getId_bodegaEmpresa()),format.raw/*115.69*/("""', 'precioAdicional', value, '"""),_display_(/*115.100*/lista2/*115.106*/.getId_cotiOdo),format.raw/*115.120*/("""');"
											disabled>
									""")))}),format.raw/*117.11*/("""
										
								"""),format.raw/*119.9*/("""</td>
								
								
								<td  style="text-align:center;">
									<input type="hidden" id="esVariable_"""),_display_(/*123.47*/lista2/*123.53*/.getId_bodegaEmpresa()),format.raw/*123.75*/("""_"""),_display_(/*123.77*/lista2/*123.83*/.getId_cotiOdo),format.raw/*123.97*/("""" value=""""),_display_(/*123.107*/lista2/*123.113*/.getEsVariable()),format.raw/*123.129*/("""">
									"""),_display_(if(lista2.getEsVariable()==1)/*124.40*/{_display_(Seq[Any](format.raw/*124.41*/("""
										"""),format.raw/*125.11*/("""<input type="checkbox" checked onchange="modificaVariable('"""),_display_(/*125.71*/lista2/*125.77*/.getId_bodegaEmpresa()),format.raw/*125.99*/("""', '"""),_display_(/*125.104*/lista2/*125.110*/.getId_cotiOdo),format.raw/*125.124*/("""');">
									""")))}else/*126.15*/{_display_(Seq[Any](format.raw/*126.16*/("""
										"""),format.raw/*127.11*/("""<input type="checkbox" onchange="modificaVariable('"""),_display_(/*127.63*/lista2/*127.69*/.getId_bodegaEmpresa()),format.raw/*127.91*/("""', '"""),_display_(/*127.96*/lista2/*127.102*/.getId_cotiOdo),format.raw/*127.116*/("""');">
									""")))}),format.raw/*128.11*/("""
								"""),format.raw/*129.9*/("""</td>
								<td style="text-align:center;">
									<div id="fechaAAMMDD_"""),_display_(/*131.32*/lista2/*131.38*/.getId_bodegaEmpresa()),format.raw/*131.60*/("""_"""),_display_(/*131.62*/lista2/*131.68*/.getId_cotiOdo),format.raw/*131.82*/("""" style="display:none">"""),_display_(/*131.106*/utilities/*131.115*/.Fechas.AAMMDD(lista2.getFecha())),format.raw/*131.148*/("""</div>
									<div id="fechaDDMMAA_"""),_display_(/*132.32*/lista2/*132.38*/.getId_bodegaEmpresa()),format.raw/*132.60*/("""_"""),_display_(/*132.62*/lista2/*132.68*/.getId_cotiOdo),format.raw/*132.82*/("""">"""),_display_(/*132.85*/utilities/*132.94*/.Fechas.DDMMAA(lista2.getFecha())),format.raw/*132.127*/("""</div>
								</td>
							</tr>
						 """)))}),format.raw/*135.9*/("""
					"""),format.raw/*136.6*/("""</tbody>
				</table>
				<div class="noprint">
					<div class="row justify-content-md-center" >
						<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
							<input type="button"  value="AGREGAR """),_display_(/*141.46*/mapDiccionario/*141.60*/.get("BODEGA")),format.raw/*141.74*/("""" class="btn btn-info btn-sm rounded-pill btn-block" onclick='$("#bodegasNoEnServ").modal("show")'>
						</div>
						<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
							<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/routes2/servicioPreciosServicio/'">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div id='bodegasNoEnServ' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>SELECCIONAR SERVICIO</h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<table id="tablaListaServicioNoEnBod" class='table table-sm table-bordered table-condensed table-hover table-fluid'>
						<thead style="background-color: #eeeeee">
							<TR> 
								<TH>SUCURSAL</TH>
								<TH>"""),_display_(/*166.14*/mapDiccionario/*166.28*/.get("BODEGA")),format.raw/*166.42*/("""</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*170.9*/for(lista1 <- listBodegasNoServ) yield /*170.41*/{_display_(Seq[Any](format.raw/*170.42*/("""
								"""),format.raw/*171.9*/("""<TR onClick="$('#id_bodegaEmpresa').val('"""),_display_(/*171.51*/lista1/*171.57*/.get(1)),format.raw/*171.64*/("""'); document.getElementById('formAgregaBodega').submit();" data-dismiss="modal">
									<td  style="text-align:left;">"""),_display_(/*172.41*/lista1/*172.47*/.get(16)),format.raw/*172.55*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*173.41*/lista1/*173.47*/.get(5)),format.raw/*173.54*/("""</td>
								</TR>
				 			""")))}),format.raw/*175.10*/("""
						"""),format.raw/*176.7*/("""</tbody>
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
	
	<form class="formulario" id="formAgregaBodega" method="post" action="/routes2/servicioAgregaPrecioServicio/">	
		<input type="hidden" name="id_servicio" value=""""),_display_(/*189.51*/servicio/*189.59*/.getId()),format.raw/*189.67*/("""">
		<input type="hidden" id="id_bodegaEmpresa" name="id_bodegaEmpresa">
		<input type="hidden" name="id_cotiOdo" value="0">
	</form>
	
""")))}),format.raw/*194.2*/("""



"""),format.raw/*198.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*199.31*/("""{"""),format.raw/*199.32*/("""
		  """),format.raw/*200.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*200.36*/("""{"""),format.raw/*200.37*/("""
		    	"""),format.raw/*201.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
				"order": [[ 1, "asc" ]],
		    	"language": """),format.raw/*204.20*/("""{"""),format.raw/*204.21*/("""
		        	"""),format.raw/*205.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*206.11*/("""}"""),format.raw/*206.12*/("""
		  """),format.raw/*207.5*/("""}"""),format.raw/*207.6*/(""" """),format.raw/*207.7*/(""");

		  $('#tablaListaServicioNoEnBod').DataTable("""),format.raw/*209.47*/("""{"""),format.raw/*209.48*/("""
		    	"""),format.raw/*210.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 1, "asc" ]],
		    	"language": """),format.raw/*213.20*/("""{"""),format.raw/*213.21*/("""
		        	"""),format.raw/*214.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*215.11*/("""}"""),format.raw/*215.12*/("""
		  """),format.raw/*216.5*/("""}"""),format.raw/*216.6*/(""" """),format.raw/*216.7*/(""");

		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*219.2*/("""}"""),format.raw/*219.3*/(""");
	
	const modificaVariable = (id_bodega, id_cotiOdo) => """),format.raw/*221.54*/("""{"""),format.raw/*221.55*/("""
		"""),format.raw/*222.3*/("""let esVariable = $("#esVariable_"+id_bodega+"_"+id_cotiOdo).val();
		if(esVariable==1)"""),format.raw/*223.20*/("""{"""),format.raw/*223.21*/("""
			"""),format.raw/*224.4*/("""modificaPorCampo(id_bodega, "esVariable", 0, id_cotiOdo);
			$("#esVariable_"+id_bodega+"_"+id_cotiOdo).val(0);
		"""),format.raw/*226.3*/("""}"""),format.raw/*226.4*/("""else"""),format.raw/*226.8*/("""{"""),format.raw/*226.9*/("""
			"""),format.raw/*227.4*/("""modificaPorCampo(id_bodega, "esVariable", 1, id_cotiOdo);
			$("#esVariable_"+id_bodega+"_"+id_cotiOdo).val(1);
		"""),format.raw/*229.3*/("""}"""),format.raw/*229.4*/("""
	"""),format.raw/*230.2*/("""}"""),format.raw/*230.3*/("""
	
	"""),format.raw/*232.2*/("""const modificaAplicaMinimo = (id_bodega, id_cotiOdo) => """),format.raw/*232.58*/("""{"""),format.raw/*232.59*/("""
		"""),format.raw/*233.3*/("""let esVariable = $("#aplicaMinimo_"+id_bodega+"_"+id_cotiOdo).val();
		if(esVariable==1)"""),format.raw/*234.20*/("""{"""),format.raw/*234.21*/("""
			"""),format.raw/*235.4*/("""modificaPorCampo(id_bodega, "aplicaMinimo", 0, id_cotiOdo);
			$("#aplicaMinimo_"+id_bodega+"_"+id_cotiOdo).val(0);
			$("#cantMinimo_"+id_bodega+"_"+id_cotiOdo).attr("disabled",true);
			$("#precioAdicional_"+id_bodega+"_"+id_cotiOdo).attr("disabled",true);
		"""),format.raw/*239.3*/("""}"""),format.raw/*239.4*/("""else"""),format.raw/*239.8*/("""{"""),format.raw/*239.9*/("""
			"""),format.raw/*240.4*/("""modificaPorCampo(id_bodega, "aplicaMinimo", 1, id_cotiOdo);
			$("#aplicaMinimo_"+id_bodega+"_"+id_cotiOdo).val(1);
			$("#cantMinimo_"+id_bodega+"_"+id_cotiOdo).attr("disabled",false);
			$("#precioAdicional_"+id_bodega+"_"+id_cotiOdo).attr("disabled",false);
		"""),format.raw/*244.3*/("""}"""),format.raw/*244.4*/("""
	"""),format.raw/*245.2*/("""}"""),format.raw/*245.3*/("""
	
	
	"""),format.raw/*248.2*/("""const modificaVigente = (id_bodega, id_cotiOdo) => """),format.raw/*248.53*/("""{"""),format.raw/*248.54*/("""
		"""),format.raw/*249.3*/("""let vigente = $("#vigente_"+id_bodega+"_"+id_cotiOdo).val();
		if(vigente==1)"""),format.raw/*250.17*/("""{"""),format.raw/*250.18*/("""
			"""),format.raw/*251.4*/("""modificaPorCampo(id_bodega, "vigente", 0, id_cotiOdo);
			$("#vigente_"+id_bodega+"_"+id_cotiOdo).val(0);
		"""),format.raw/*253.3*/("""}"""),format.raw/*253.4*/("""else"""),format.raw/*253.8*/("""{"""),format.raw/*253.9*/("""
			"""),format.raw/*254.4*/("""modificaPorCampo(id_bodega, "vigente", 1, id_cotiOdo);
			$("#vigente_"+id_bodega+"_"+id_cotiOdo).val(1);
		"""),format.raw/*256.3*/("""}"""),format.raw/*256.4*/("""
	"""),format.raw/*257.2*/("""}"""),format.raw/*257.3*/("""
	

	"""),format.raw/*260.2*/("""const modificaPorCampo = (id_bodega, campo, valor, id_cotiOdo) => """),format.raw/*260.68*/("""{"""),format.raw/*260.69*/("""
		"""),format.raw/*261.3*/("""let precio = $("#precio_"+id_bodega+"_"+id_cotiOdo).val();
		precio = precio.replace(/,/g,'');
		let cantMinimo = $("#cantMinimo_"+id_bodega+"_"+id_cotiOdo).val();
		cantMinimo = cantMinimo.replace(/,/g,'');
		let precioAdicional = $("#precioAdicional_"+id_bodega+"_"+id_cotiOdo).val();
		precioAdicional = precioAdicional.replace(/,/g,'');

		let formData = new FormData();
		formData.append('id_bodegaEmpresa',id_bodega);
		formData.append('id_servicio','"""),_display_(/*270.35*/servicio/*270.43*/.getId),format.raw/*270.49*/("""');
		formData.append('campo',campo);
		formData.append('valor',valor);
		formData.append('id_cotiOdo',id_cotiOdo);
		$.ajax("""),format.raw/*274.10*/("""{"""),format.raw/*274.11*/("""
            """),format.raw/*275.13*/("""url: "/modPorCampoListaPServicioAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*282.36*/("""{"""),format.raw/*282.37*/("""
	     		"""),format.raw/*283.9*/("""if(respuesta=="error")"""),format.raw/*283.31*/("""{"""),format.raw/*283.32*/("""
	     			"""),format.raw/*284.10*/("""alertify.alert(msgError, function () """),format.raw/*284.47*/("""{"""),format.raw/*284.48*/("""
		     			"""),format.raw/*285.11*/("""location.href = "/";
		     		"""),format.raw/*286.10*/("""}"""),format.raw/*286.11*/(""");
	     		"""),format.raw/*287.9*/("""}"""),format.raw/*287.10*/("""else"""),format.raw/*287.14*/("""{"""),format.raw/*287.15*/("""
					"""),format.raw/*288.6*/("""let rs = respuesta.split("-");
					$("#fechaAAMMDD_"+id_bodega+"_"+id_cotiOdo).text(rs[0]+"-"+rs[1]+"-"+rs[1]);
					$("#fechaDDMMAA_"+id_bodega+"_"+id_cotiOdo).text(rs[2]+"-"+rs[1]+"-"+rs[0]);
					$("#precio_"+id_bodega+"_"+id_cotiOdo).val(formatStandar(precio,rs[3]));
					$("#cantMinimo_"+id_bodega+"_"+id_cotiOdo).val(formatStandar(cantMinimo,"2"));
					$("#precioAdicional_"+id_bodega+"_"+id_cotiOdo).val(formatStandar(precioAdicional,rs[3]));
				"""),format.raw/*294.5*/("""}"""),format.raw/*294.6*/("""
	     	"""),format.raw/*295.8*/("""}"""),format.raw/*295.9*/("""
        """),format.raw/*296.9*/("""}"""),format.raw/*296.10*/(""");
	"""),format.raw/*297.2*/("""}"""),format.raw/*297.3*/("""
"""),format.raw/*298.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,servicio:tables.Servicio,listPrecio:List[tables.ListaPrecioServicio],listMoneda:List[tables.Moneda],listBodegasNoServ:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,servicio,listPrecio,listMoneda,listBodegasNoServ)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.Servicio,List[tables.ListaPrecioServicio],List[tables.Moneda],List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,servicio,listPrecio,listMoneda,listBodegasNoServ) => apply(mapDiccionario,mapPermiso,userMnu,servicio,listPrecio,listMoneda,listBodegasNoServ)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuOdo/servicioListaPreciosServicio.scala.html
                  HASH: 75c4e40154c0d375e7b9053f08c9b41c56bc5203
                  MATRIX: 1111->1|1446->243|1473->245|1489->253|1528->255|1556->258|1624->306|1652->308|1728->359|1907->517|1936->520|2270->827|2293->841|2328->855|2726->1227|2767->1252|2806->1253|2841->1261|2978->1371|2993->1377|3036->1399|3065->1401|3080->1407|3115->1421|3153->1431|3169->1437|3204->1450|3270->1489|3309->1490|3348->1501|3434->1560|3449->1566|3492->1588|3524->1592|3540->1598|3576->1612|3615->1632|3654->1633|3693->1644|3771->1695|3786->1701|3829->1723|3860->1727|3876->1733|3912->1747|3959->1763|3995->1772|4041->1791|4056->1797|4092->1812|4166->1859|4181->1865|4221->1884|4294->1930|4309->1936|4347->1953|4393->1972|4408->1978|4446->1995|4519->2041|4534->2047|4570->2062|4711->2176|4726->2182|4769->2204|4798->2206|4813->2212|4848->2226|4917->2268|4932->2274|4975->2296|5027->2321|5042->2327|5078->2341|5137->2373|5152->2379|5188->2394|5218->2397|5233->2403|5270->2419|5318->2440|5358->2464|5397->2465|5437->2477|5480->2493|5494->2498|5518->2501|5548->2504|5562->2509|5592->2518|5644->2539|5682->2549|5791->2631|5806->2637|5842->2652|5962->2745|5977->2751|6020->2773|6049->2775|6064->2781|6099->2795|6146->2815|6161->2821|6197->2836|6372->2984|6387->2990|6430->3012|6479->3034|6494->3040|6530->3054|6695->3192|6710->3198|6753->3220|6782->3222|6797->3228|6832->3242|6870->3252|6886->3258|6926->3276|6997->3320|7036->3321|7075->3332|7166->3396|7181->3402|7225->3424|7257->3428|7273->3434|7309->3448|7348->3468|7387->3469|7426->3480|7509->3536|7524->3542|7567->3564|7598->3568|7614->3574|7650->3588|7697->3604|7733->3613|7824->3677|7839->3683|7879->3702|7964->3760|8003->3761|8042->3772|8142->3845|8157->3851|8200->3873|8229->3875|8244->3881|8279->3895|8327->3916|8342->3922|8382->3941|8560->4092|8575->4098|8618->4120|8671->4146|8687->4152|8723->4166|8762->4186|8801->4187|8840->4198|8940->4271|8955->4277|8998->4299|9027->4301|9042->4307|9077->4321|9125->4342|9140->4348|9180->4367|9358->4518|9373->4524|9416->4546|9469->4572|9485->4578|9521->4592|9588->4628|9624->4637|9716->4701|9732->4707|9778->4731|9864->4789|9904->4790|9944->4801|10050->4879|10066->4885|10110->4907|10140->4909|10156->4915|10192->4929|10241->4950|10257->4956|10303->4980|10482->5131|10498->5137|10542->5159|10602->5190|10619->5196|10656->5210|10696->5230|10736->5231|10776->5242|10882->5320|10898->5326|10942->5348|10972->5350|10988->5356|11024->5370|11073->5391|11089->5397|11135->5421|11314->5572|11330->5578|11374->5600|11434->5631|11451->5637|11488->5651|11556->5687|11604->5707|11743->5818|11759->5824|11803->5846|11833->5848|11849->5854|11885->5868|11924->5878|11941->5884|11980->5900|12050->5942|12090->5943|12130->5954|12218->6014|12234->6020|12278->6042|12312->6047|12329->6053|12366->6067|12406->6087|12446->6088|12486->6099|12566->6151|12582->6157|12626->6179|12659->6184|12676->6190|12713->6204|12761->6220|12798->6229|12903->6306|12919->6312|12963->6334|12993->6336|13009->6342|13045->6356|13098->6380|13118->6389|13174->6422|13240->6460|13256->6466|13300->6488|13330->6490|13346->6496|13382->6510|13413->6513|13432->6522|13488->6555|13561->6597|13595->6603|13822->6802|13846->6816|13882->6830|15025->7945|15049->7959|15085->7973|15168->8029|15217->8061|15257->8062|15294->8071|15364->8113|15380->8119|15409->8126|15558->8247|15574->8253|15604->8261|15678->8307|15694->8313|15723->8320|15784->8349|15819->8356|16304->8813|16322->8821|16352->8829|16520->8966|16552->8970|16643->9032|16673->9033|16706->9038|16766->9069|16796->9070|16832->9078|17004->9221|17034->9222|17075->9234|17182->9312|17212->9313|17245->9318|17274->9319|17303->9320|17382->9370|17412->9371|17448->9379|17623->9525|17653->9526|17694->9538|17801->9616|17831->9617|17864->9622|17893->9623|17922->9624|18024->9698|18053->9699|18140->9757|18170->9758|18201->9761|18316->9847|18346->9848|18378->9852|18520->9966|18549->9967|18581->9971|18610->9972|18642->9976|18784->10090|18813->10091|18843->10093|18872->10094|18904->10098|18989->10154|19019->10155|19050->10158|19167->10246|19197->10247|19229->10251|19518->10512|19547->10513|19579->10517|19608->10518|19640->10522|19931->10785|19960->10786|19990->10788|20019->10789|20053->10795|20133->10846|20163->10847|20194->10850|20300->10927|20330->10928|20362->10932|20498->11040|20527->11041|20559->11045|20588->11046|20620->11050|20756->11158|20785->11159|20815->11161|20844->11162|20877->11167|20972->11233|21002->11234|21033->11237|21519->11695|21537->11703|21565->11709|21719->11834|21749->11835|21791->11848|22062->12090|22092->12091|22129->12100|22180->12122|22210->12123|22249->12133|22315->12170|22345->12171|22385->12182|22444->12212|22474->12213|22513->12224|22543->12225|22576->12229|22606->12230|22640->12236|23125->12693|23154->12694|23190->12702|23219->12703|23256->12712|23286->12713|23318->12717|23347->12718|23376->12719
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|38->7|39->8|39->8|40->9|46->15|46->15|46->15|61->30|61->30|61->30|62->31|64->33|64->33|64->33|64->33|64->33|64->33|64->33|64->33|64->33|65->34|65->34|66->35|66->35|66->35|66->35|66->35|66->35|66->35|67->36|67->36|68->37|68->37|68->37|68->37|68->37|68->37|68->37|69->38|70->39|71->40|71->40|71->40|72->41|72->41|72->41|73->42|73->42|73->42|74->43|74->43|74->43|75->44|75->44|75->44|78->47|78->47|78->47|78->47|78->47|78->47|79->48|79->48|79->48|79->48|79->48|79->48|80->49|80->49|80->49|80->49|80->49|80->49|81->50|81->50|81->50|82->51|82->51|82->51|82->51|82->51|82->51|82->51|83->52|84->53|88->57|88->57|88->57|91->60|91->60|91->60|91->60|91->60|91->60|92->61|92->61|92->61|95->64|95->64|95->64|95->64|95->64|95->64|101->70|101->70|101->70|101->70|101->70|101->70|101->70|101->70|101->70|102->71|102->71|103->72|103->72|103->72|103->72|103->72|103->72|103->72|104->73|104->73|105->74|105->74|105->74|105->74|105->74|105->74|105->74|106->75|107->76|110->79|110->79|110->79|112->81|112->81|113->82|114->83|114->83|114->83|114->83|114->83|114->83|115->84|115->84|115->84|118->87|118->87|118->87|118->87|118->87|118->87|119->88|119->88|120->89|121->90|121->90|121->90|121->90|121->90|121->90|122->91|122->91|122->91|125->94|125->94|125->94|125->94|125->94|125->94|127->96|128->97|131->100|131->100|131->100|133->102|133->102|134->103|135->104|135->104|135->104|135->104|135->104|135->104|136->105|136->105|136->105|139->108|139->108|139->108|139->108|139->108|139->108|140->109|140->109|141->110|142->111|142->111|142->111|142->111|142->111|142->111|143->112|143->112|143->112|146->115|146->115|146->115|146->115|146->115|146->115|148->117|150->119|154->123|154->123|154->123|154->123|154->123|154->123|154->123|154->123|154->123|155->124|155->124|156->125|156->125|156->125|156->125|156->125|156->125|156->125|157->126|157->126|158->127|158->127|158->127|158->127|158->127|158->127|158->127|159->128|160->129|162->131|162->131|162->131|162->131|162->131|162->131|162->131|162->131|162->131|163->132|163->132|163->132|163->132|163->132|163->132|163->132|163->132|163->132|166->135|167->136|172->141|172->141|172->141|197->166|197->166|197->166|201->170|201->170|201->170|202->171|202->171|202->171|202->171|203->172|203->172|203->172|204->173|204->173|204->173|206->175|207->176|220->189|220->189|220->189|225->194|229->198|230->199|230->199|231->200|231->200|231->200|232->201|235->204|235->204|236->205|237->206|237->206|238->207|238->207|238->207|240->209|240->209|241->210|244->213|244->213|245->214|246->215|246->215|247->216|247->216|247->216|250->219|250->219|252->221|252->221|253->222|254->223|254->223|255->224|257->226|257->226|257->226|257->226|258->227|260->229|260->229|261->230|261->230|263->232|263->232|263->232|264->233|265->234|265->234|266->235|270->239|270->239|270->239|270->239|271->240|275->244|275->244|276->245|276->245|279->248|279->248|279->248|280->249|281->250|281->250|282->251|284->253|284->253|284->253|284->253|285->254|287->256|287->256|288->257|288->257|291->260|291->260|291->260|292->261|301->270|301->270|301->270|305->274|305->274|306->275|313->282|313->282|314->283|314->283|314->283|315->284|315->284|315->284|316->285|317->286|317->286|318->287|318->287|318->287|318->287|319->288|325->294|325->294|326->295|326->295|327->296|327->296|328->297|328->297|329->298
                  -- GENERATED --
              */
          