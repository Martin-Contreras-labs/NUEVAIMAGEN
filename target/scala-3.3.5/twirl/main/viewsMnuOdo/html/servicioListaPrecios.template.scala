
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

object servicioListaPrecios extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template7[Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,List[tables.ListaPrecioServicio],List[tables.Moneda],List[tables.Servicio],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
bodega: tables.BodegaEmpresa, listPrecio: List[tables.ListaPrecioServicio], listMoneda: List[tables.Moneda], listServicioNoBod: List[tables.Servicio]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

"""),_display_(/*6.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.50*/("""
	"""),format.raw/*7.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*8.4*/barraTitulo(mapDiccionario, "MANTENCION PRECIOS DE SERVICIOS POR "+mapDiccionario.get("BODEGA")+"S/PROYECTOS", mapDiccionario.get("BODEGA")+": "+bodega.getNombre().toUpperCase())),format.raw/*8.182*/("""
		"""),format.raw/*9.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-11 col-md-10 col-lg-11">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR> 
							<TH>VIGENTE</TH>
							<TH>FAMILIA</TH>
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
									<input type="hidden" id="vigente_"""),_display_(/*33.44*/lista2/*33.50*/.getId_servicio()),format.raw/*33.67*/("""_"""),_display_(/*33.69*/lista2/*33.75*/.getId_cotiOdo),format.raw/*33.89*/("""" value=""""),_display_(/*33.99*/lista2/*33.105*/.getVigente()),format.raw/*33.118*/("""">
									"""),_display_(if(lista2.getVigente()==1)/*34.37*/{_display_(Seq[Any](format.raw/*34.38*/("""
										"""),format.raw/*35.11*/("""<input type="checkbox" checked onchange="modificaVigente('"""),_display_(/*35.70*/lista2/*35.76*/.getId_servicio()),format.raw/*35.93*/("""','"""),_display_(/*35.97*/lista2/*35.103*/.getId_cotiOdo),format.raw/*35.117*/("""');">
									""")))}else/*36.15*/{_display_(Seq[Any](format.raw/*36.16*/("""
										"""),format.raw/*37.11*/("""<input type="checkbox" onchange="modificaVigente('"""),_display_(/*37.62*/lista2/*37.68*/.getId_servicio()),format.raw/*37.85*/("""','"""),_display_(/*37.89*/lista2/*37.95*/.getId_cotiOdo),format.raw/*37.109*/("""');">
									""")))}),format.raw/*38.11*/("""
								"""),format.raw/*39.9*/("""</td>
								<td>"""),_display_(/*40.14*/lista2/*40.20*/.getNomClaseServicio()),format.raw/*40.42*/("""</td>
								<td style="text-align: center;">"""),_display_(/*41.42*/lista2/*41.48*/.getNumeroCotiOdo()),format.raw/*41.67*/("""</td>
								<td style="text-align: center">"""),_display_(/*42.41*/lista2/*42.47*/.getCodServicio()),format.raw/*42.64*/("""</td>
								<td>"""),_display_(/*43.14*/lista2/*43.20*/.getNomServicio()),format.raw/*43.37*/("""</td>
								<td style="text-align: center">"""),_display_(/*44.41*/lista2/*44.47*/.getNomUnidad()),format.raw/*44.62*/("""</td>
								<td>
									<select class="custom-select" style="text-align-last:center;"
										id="idmoneda_"""),_display_(/*47.25*/lista2/*47.31*/.getId_servicio()),format.raw/*47.48*/("""_"""),_display_(/*47.50*/lista2/*47.56*/.getId_cotiOdo),format.raw/*47.70*/("""" 
										onchange="modificaPorCampo('"""),_display_(/*48.40*/lista2/*48.46*/.getId_servicio()),format.raw/*48.63*/("""', 'id_moneda', value, '"""),_display_(/*48.88*/lista2/*48.94*/.getId_cotiOdo),format.raw/*48.108*/("""');">
										<option value=""""),_display_(/*49.27*/lista2/*49.33*/.getId_moneda()),format.raw/*49.48*/("""">"""),_display_(/*49.51*/lista2/*49.57*/.getNickMoneda()),format.raw/*49.73*/("""</option>
										"""),_display_(/*50.12*/for(lista <- listMoneda) yield /*50.36*/{_display_(Seq[Any](format.raw/*50.37*/("""
											"""),format.raw/*51.12*/("""<option value=""""),_display_(/*51.28*/lista/*51.33*/.id),format.raw/*51.36*/("""">"""),_display_(/*51.39*/lista/*51.44*/.nickName),format.raw/*51.53*/("""</option>
										""")))}),format.raw/*52.12*/("""
									"""),format.raw/*53.10*/("""</select>
								</td>
								<td>
								
									<div style="display:none">"""),_display_(/*57.37*/lista2/*57.43*/.getPrecioStr()),format.raw/*57.58*/("""</div>
								
									<input type="text" class="form-control right"
										id="precio_"""),_display_(/*60.23*/lista2/*60.29*/.getId_servicio()),format.raw/*60.46*/("""_"""),_display_(/*60.48*/lista2/*60.54*/.getId_cotiOdo),format.raw/*60.68*/(""""
										value=""""),_display_(/*61.19*/lista2/*61.25*/.getPrecioStr()),format.raw/*61.40*/(""""
										onfocus="value=value.replace(/,/g,'')" 
										onkeydown="return ingresoDouble(window.event)"
										onchange="modificaPorCampo('"""),_display_(/*64.40*/lista2/*64.46*/.getId_servicio()),format.raw/*64.63*/("""', 'precio', value, '"""),_display_(/*64.85*/lista2/*64.91*/.getId_cotiOdo),format.raw/*64.105*/("""');">
										
								</td>
								
								
								<td  style="text-align:center;">
									<input type="hidden" id="aplicaMinimo_"""),_display_(/*70.49*/lista2/*70.55*/.getId_servicio()),format.raw/*70.72*/("""_"""),_display_(/*70.74*/lista2/*70.80*/.getId_cotiOdo),format.raw/*70.94*/("""" value=""""),_display_(/*70.104*/lista2/*70.110*/.getAplicaMinimo()),format.raw/*70.128*/("""">
									"""),_display_(if(lista2.getAplicaMinimo()==1)/*71.42*/{_display_(Seq[Any](format.raw/*71.43*/("""
										"""),format.raw/*72.11*/("""<input type="checkbox" checked onchange="modificaAplicaMinimo('"""),_display_(/*72.75*/lista2/*72.81*/.getId_servicio()),format.raw/*72.98*/("""','"""),_display_(/*72.102*/lista2/*72.108*/.getId_cotiOdo),format.raw/*72.122*/("""');">
									""")))}else/*73.15*/{_display_(Seq[Any](format.raw/*73.16*/("""
										"""),format.raw/*74.11*/("""<input type="checkbox" onchange="modificaAplicaMinimo('"""),_display_(/*74.67*/lista2/*74.73*/.getId_servicio()),format.raw/*74.90*/("""','"""),_display_(/*74.94*/lista2/*74.100*/.getId_cotiOdo),format.raw/*74.114*/("""');">
									""")))}),format.raw/*75.11*/("""
								"""),format.raw/*76.9*/("""</td>
								<td>
								
									<div style="display:none">"""),_display_(/*79.37*/lista2/*79.43*/.getCantMinimoStr()),format.raw/*79.62*/("""</div>
									
									"""),_display_(if(lista2.getAplicaMinimo()==1)/*81.42*/{_display_(Seq[Any](format.raw/*81.43*/("""
										"""),format.raw/*82.11*/("""<input type="text" class="form-control right"
											id="cantMinimo_"""),_display_(/*83.28*/lista2/*83.34*/.getId_servicio()),format.raw/*83.51*/("""_"""),_display_(/*83.53*/lista2/*83.59*/.getId_cotiOdo),format.raw/*83.73*/(""""
											value=""""),_display_(/*84.20*/lista2/*84.26*/.getCantMinimoStr()),format.raw/*84.45*/(""""
											onfocus="value=value.replace(/,/g,'')" 
											onkeydown="return ingresoDouble(window.event)"
											onchange="modificaPorCampo('"""),_display_(/*87.41*/lista2/*87.47*/.getId_servicio()),format.raw/*87.64*/("""', 'cantMinimo', value, '"""),_display_(/*87.90*/lista2/*87.96*/.getId_cotiOdo),format.raw/*87.110*/("""');">
									""")))}else/*88.15*/{_display_(Seq[Any](format.raw/*88.16*/("""
										"""),format.raw/*89.11*/("""<input type="text" class="form-control right"
											id="cantMinimo_"""),_display_(/*90.28*/lista2/*90.34*/.getId_servicio()),format.raw/*90.51*/("""_"""),_display_(/*90.53*/lista2/*90.59*/.getId_cotiOdo),format.raw/*90.73*/(""""
											value=""""),_display_(/*91.20*/lista2/*91.26*/.getCantMinimoStr()),format.raw/*91.45*/(""""
											onfocus="value=value.replace(/,/g,'')" 
											onkeydown="return ingresoDouble(window.event)"
											onchange="modificaPorCampo('"""),_display_(/*94.41*/lista2/*94.47*/.getId_servicio()),format.raw/*94.64*/("""', 'cantMinimo', value, '"""),_display_(/*94.90*/lista2/*94.96*/.getId_cotiOdo),format.raw/*94.110*/("""');"
											disabled>
									""")))}),format.raw/*96.11*/("""
								"""),format.raw/*97.9*/("""</td>
								<td>
								
									<div style="display:none">"""),_display_(/*100.37*/lista2/*100.43*/.getPrecioAdicionalStr()),format.raw/*100.67*/("""</div>
									
									"""),_display_(if(lista2.getAplicaMinimo()==1)/*102.42*/{_display_(Seq[Any](format.raw/*102.43*/("""
										"""),format.raw/*103.11*/("""<input type="text" class="form-control right"
											id="precioAdicional_"""),_display_(/*104.33*/lista2/*104.39*/.getId_servicio()),format.raw/*104.56*/("""_"""),_display_(/*104.58*/lista2/*104.64*/.getId_cotiOdo),format.raw/*104.78*/(""""
											value=""""),_display_(/*105.20*/lista2/*105.26*/.getPrecioAdicionalStr()),format.raw/*105.50*/(""""
											onfocus="value=value.replace(/,/g,'')" 
											onkeydown="return ingresoDouble(window.event)"
											onchange="modificaPorCampo('"""),_display_(/*108.41*/lista2/*108.47*/.getId_servicio()),format.raw/*108.64*/("""', 'precioAdicional', value, '"""),_display_(/*108.95*/lista2/*108.101*/.getId_cotiOdo),format.raw/*108.115*/("""');">
									""")))}else/*109.15*/{_display_(Seq[Any](format.raw/*109.16*/("""
										"""),format.raw/*110.11*/("""<input type="text" class="form-control right"
											id="precioAdicional_"""),_display_(/*111.33*/lista2/*111.39*/.getId_servicio()),format.raw/*111.56*/("""_"""),_display_(/*111.58*/lista2/*111.64*/.getId_cotiOdo),format.raw/*111.78*/(""""
											value=""""),_display_(/*112.20*/lista2/*112.26*/.getPrecioAdicionalStr()),format.raw/*112.50*/(""""
											onfocus="value=value.replace(/,/g,'')" 
											onkeydown="return ingresoDouble(window.event)"
											onchange="modificaPorCampo('"""),_display_(/*115.41*/lista2/*115.47*/.getId_servicio()),format.raw/*115.64*/("""', 'precioAdicional', value, '"""),_display_(/*115.95*/lista2/*115.101*/.getId_cotiOdo),format.raw/*115.115*/("""');"
											disabled>
									""")))}),format.raw/*117.11*/("""
										
								"""),format.raw/*119.9*/("""</td>
								
								
								<td  style="text-align:center;">
									<input type="hidden" id="esVariable_"""),_display_(/*123.47*/lista2/*123.53*/.getId_servicio()),format.raw/*123.70*/("""_"""),_display_(/*123.72*/lista2/*123.78*/.getId_cotiOdo),format.raw/*123.92*/("""" value=""""),_display_(/*123.102*/lista2/*123.108*/.getEsVariable()),format.raw/*123.124*/("""">
									"""),_display_(if(lista2.getEsVariable()==1)/*124.40*/{_display_(Seq[Any](format.raw/*124.41*/("""
										"""),format.raw/*125.11*/("""<input type="checkbox" checked onchange="modificaVariable('"""),_display_(/*125.71*/lista2/*125.77*/.getId_servicio()),format.raw/*125.94*/("""', '"""),_display_(/*125.99*/lista2/*125.105*/.getId_cotiOdo),format.raw/*125.119*/("""');">
									""")))}else/*126.15*/{_display_(Seq[Any](format.raw/*126.16*/("""
										"""),format.raw/*127.11*/("""<input type="checkbox" onchange="modificaVariable('"""),_display_(/*127.63*/lista2/*127.69*/.getId_servicio()),format.raw/*127.86*/("""', '"""),_display_(/*127.91*/lista2/*127.97*/.getId_cotiOdo),format.raw/*127.111*/("""');">
									""")))}),format.raw/*128.11*/("""
								"""),format.raw/*129.9*/("""</td>
								<td style="text-align:center;">
									<div id="fechaAAMMDD_"""),_display_(/*131.32*/lista2/*131.38*/.getId_servicio()),format.raw/*131.55*/("""_"""),_display_(/*131.57*/lista2/*131.63*/.getId_cotiOdo),format.raw/*131.77*/("""" style="display:none">"""),_display_(/*131.101*/utilities/*131.110*/.Fechas.AAMMDD(lista2.getFecha())),format.raw/*131.143*/("""</div>
									<div id="fechaDDMMAA_"""),_display_(/*132.32*/lista2/*132.38*/.getId_servicio()),format.raw/*132.55*/("""_"""),_display_(/*132.57*/lista2/*132.63*/.getId_cotiOdo),format.raw/*132.77*/("""">"""),_display_(/*132.80*/utilities/*132.89*/.Fechas.DDMMAA(lista2.getFecha())),format.raw/*132.122*/("""</div>
								</td>
							</tr>
						 """)))}),format.raw/*135.9*/("""
					"""),format.raw/*136.6*/("""</tbody>
				</table>
				<div class="noprint">
					<div class="row justify-content-md-center" >
						<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
							<input type="button"  value="AGREGAR SERVICIO" class="btn btn-info btn-sm rounded-pill btn-block" onclick='$("#serviciosNoEnBod").modal("show")'>
						</div>
						<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
							<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/servicioPrecios/'">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div id='serviciosNoEnBod' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
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
								<TH>FAMILIA</TH>
								<TH>CODIGO</TH>
								<TH>NOMBRE</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*171.9*/for(lista1 <- listServicioNoBod) yield /*171.41*/{_display_(Seq[Any](format.raw/*171.42*/("""
								"""),format.raw/*172.9*/("""<TR onClick="$('#id_servicio').val('"""),_display_(/*172.46*/lista1/*172.52*/.getId()),format.raw/*172.60*/("""'); document.getElementById('formAgregaServicio').submit();" data-dismiss="modal">
									<td  style="text-align:left;">"""),_display_(/*173.41*/lista1/*173.47*/.getNombreClase()),format.raw/*173.64*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*174.41*/lista1/*174.47*/.getCodigo()),format.raw/*174.59*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*175.41*/lista1/*175.47*/.getNombre()),format.raw/*175.59*/("""</td>
								</TR>
				 			""")))}),format.raw/*177.10*/("""
						"""),format.raw/*178.7*/("""</tbody>
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
	
	<form class="formulario" id="formAgregaServicio" method="post" action="/servicioAgregaPrecio/">	
		<input type="hidden" id="id_servicio" name="id_servicio">
		<input type="hidden" name="id_bodegaEmpresa" value=""""),_display_(/*192.56*/bodega/*192.62*/.getId()),format.raw/*192.70*/("""">
		<input type="hidden" name="id_cotiOdo" value="0">
	</form>
	
""")))}),format.raw/*196.2*/("""



"""),format.raw/*200.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*201.31*/("""{"""),format.raw/*201.32*/("""
		  """),format.raw/*202.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*202.36*/("""{"""),format.raw/*202.37*/("""
		    	"""),format.raw/*203.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
				"order": [[ 0, "asc" ],[ 3, "asc" ]],
		    	"language": """),format.raw/*206.20*/("""{"""),format.raw/*206.21*/("""
		        	"""),format.raw/*207.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*208.11*/("""}"""),format.raw/*208.12*/("""
		  """),format.raw/*209.5*/("""}"""),format.raw/*209.6*/(""" """),format.raw/*209.7*/(""");

		  $('#tablaListaServicioNoEnBod').DataTable("""),format.raw/*211.47*/("""{"""),format.raw/*211.48*/("""
		    	"""),format.raw/*212.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 2, "asc" ]],
		    	"language": """),format.raw/*215.20*/("""{"""),format.raw/*215.21*/("""
		        	"""),format.raw/*216.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*217.11*/("""}"""),format.raw/*217.12*/("""
		  """),format.raw/*218.5*/("""}"""),format.raw/*218.6*/(""" """),format.raw/*218.7*/(""");

		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*221.2*/("""}"""),format.raw/*221.3*/(""");
	
	const modificaVariable = (id_servicio, id_cotiOdo) => """),format.raw/*223.56*/("""{"""),format.raw/*223.57*/("""
		"""),format.raw/*224.3*/("""let esVariable = $("#esVariable_"+id_servicio+"_"+id_cotiOdo).val();
		if(esVariable==1)"""),format.raw/*225.20*/("""{"""),format.raw/*225.21*/("""
			"""),format.raw/*226.4*/("""modificaPorCampo(id_servicio, "esVariable", 0, id_cotiOdo);
			$("#esVariable_"+id_servicio+"_"+id_cotiOdo).val(0);
		"""),format.raw/*228.3*/("""}"""),format.raw/*228.4*/("""else"""),format.raw/*228.8*/("""{"""),format.raw/*228.9*/("""
			"""),format.raw/*229.4*/("""modificaPorCampo(id_servicio, "esVariable", 1, id_cotiOdo);
			$("#esVariable_"+id_servicio+"_"+id_cotiOdo).val(1);
		"""),format.raw/*231.3*/("""}"""),format.raw/*231.4*/("""
	"""),format.raw/*232.2*/("""}"""),format.raw/*232.3*/("""
	
	"""),format.raw/*234.2*/("""const modificaAplicaMinimo = (id_servicio, id_cotiOdo) => """),format.raw/*234.60*/("""{"""),format.raw/*234.61*/("""
		"""),format.raw/*235.3*/("""let esVariable = $("#aplicaMinimo_"+id_servicio+"_"+id_cotiOdo).val();
		if(esVariable==1)"""),format.raw/*236.20*/("""{"""),format.raw/*236.21*/("""
			"""),format.raw/*237.4*/("""modificaPorCampo(id_servicio, "aplicaMinimo", 0, id_cotiOdo);
			$("#aplicaMinimo_"+id_servicio+"_"+id_cotiOdo).val(0);
			$("#cantMinimo_"+id_servicio+"_"+id_cotiOdo).attr("disabled",true);
			$("#precioAdicional_"+id_servicio+"_"+id_cotiOdo).attr("disabled",true);
		"""),format.raw/*241.3*/("""}"""),format.raw/*241.4*/("""else"""),format.raw/*241.8*/("""{"""),format.raw/*241.9*/("""
			"""),format.raw/*242.4*/("""modificaPorCampo(id_servicio, "aplicaMinimo", 1, id_cotiOdo);
			$("#aplicaMinimo_"+id_servicio+"_"+id_cotiOdo).val(1);
			$("#cantMinimo_"+id_servicio+"_"+id_cotiOdo).attr("disabled",false);
			$("#precioAdicional_"+id_servicio+"_"+id_cotiOdo).attr("disabled",false);
		"""),format.raw/*246.3*/("""}"""),format.raw/*246.4*/("""
	"""),format.raw/*247.2*/("""}"""),format.raw/*247.3*/("""
	
	
	"""),format.raw/*250.2*/("""const modificaVigente = (id_servicio, id_cotiOdo) => """),format.raw/*250.55*/("""{"""),format.raw/*250.56*/("""
		"""),format.raw/*251.3*/("""let vigente = $("#vigente_"+id_servicio+"_"+id_cotiOdo).val();
		if(vigente==1)"""),format.raw/*252.17*/("""{"""),format.raw/*252.18*/("""
			"""),format.raw/*253.4*/("""modificaPorCampo(id_servicio, "vigente", 0, id_cotiOdo);
			$("#vigente_"+id_servicio+"_"+id_cotiOdo).val(0);
		"""),format.raw/*255.3*/("""}"""),format.raw/*255.4*/("""else"""),format.raw/*255.8*/("""{"""),format.raw/*255.9*/("""
			"""),format.raw/*256.4*/("""modificaPorCampo(id_servicio, "vigente", 1, id_cotiOdo);
			$("#vigente_"+id_servicio+"_"+id_cotiOdo).val(1);
		"""),format.raw/*258.3*/("""}"""),format.raw/*258.4*/("""
	"""),format.raw/*259.2*/("""}"""),format.raw/*259.3*/("""
	

	"""),format.raw/*262.2*/("""const modificaPorCampo = (id_servicio, campo, valor, id_cotiOdo) => """),format.raw/*262.70*/("""{"""),format.raw/*262.71*/("""
		"""),format.raw/*263.3*/("""let precio = $("#precio_"+id_servicio+"_"+id_cotiOdo).val();
		precio = precio.replace(/,/g,'');
		let cantMinimo = $("#cantMinimo_"+id_servicio+"_"+id_cotiOdo).val();
		cantMinimo = cantMinimo.replace(/,/g,'');
		let precioAdicional = $("#precioAdicional_"+id_servicio+"_"+id_cotiOdo).val();
		precioAdicional = precioAdicional.replace(/,/g,'');
		
		let formData = new FormData();
		formData.append('id_bodegaEmpresa','"""),_display_(/*271.40*/bodega/*271.46*/.getId),format.raw/*271.52*/("""');
		formData.append('id_servicio',id_servicio);
		formData.append('campo',campo);
		formData.append('valor',valor);
		formData.append('id_cotiOdo',id_cotiOdo);
		$.ajax("""),format.raw/*276.10*/("""{"""),format.raw/*276.11*/("""
            """),format.raw/*277.13*/("""url: "/modPorCampoListaPServicioAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*284.36*/("""{"""),format.raw/*284.37*/("""
	     		"""),format.raw/*285.9*/("""if(respuesta=="error")"""),format.raw/*285.31*/("""{"""),format.raw/*285.32*/("""
	     			"""),format.raw/*286.10*/("""alertify.alert(msgError, function () """),format.raw/*286.47*/("""{"""),format.raw/*286.48*/("""
		     			"""),format.raw/*287.11*/("""location.href = "/";
		     		"""),format.raw/*288.10*/("""}"""),format.raw/*288.11*/(""");
	     		"""),format.raw/*289.9*/("""}"""),format.raw/*289.10*/("""else"""),format.raw/*289.14*/("""{"""),format.raw/*289.15*/("""
					"""),format.raw/*290.6*/("""let rs = respuesta.split("-");
					$("#fechaAAMMDD_"+id_servicio+"_"+id_cotiOdo).text(rs[0]+"-"+rs[1]+"-"+rs[1]);
					$("#fechaDDMMAA_"+id_servicio+"_"+id_cotiOdo).text(rs[2]+"-"+rs[1]+"-"+rs[0]);
					$("#precio_"+id_servicio+"_"+id_cotiOdo).val(formatStandar(precio,rs[3]));
					$("#cantMinimo_"+id_servicio+"_"+id_cotiOdo).val(formatStandar(cantMinimo,"2"));
					$("#precioAdicional_"+id_servicio+"_"+id_cotiOdo).val(formatStandar(precioAdicional,rs[3]));
				"""),format.raw/*296.5*/("""}"""),format.raw/*296.6*/("""
	     	"""),format.raw/*297.8*/("""}"""),format.raw/*297.9*/("""
        """),format.raw/*298.9*/("""}"""),format.raw/*298.10*/(""");
	"""),format.raw/*299.2*/("""}"""),format.raw/*299.3*/("""
"""),format.raw/*300.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,bodega:tables.BodegaEmpresa,listPrecio:List[tables.ListaPrecioServicio],listMoneda:List[tables.Moneda],listServicioNoBod:List[tables.Servicio]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,bodega,listPrecio,listMoneda,listServicioNoBod)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,List[tables.ListaPrecioServicio],List[tables.Moneda],List[tables.Servicio]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,bodega,listPrecio,listMoneda,listServicioNoBod) => apply(mapDiccionario,mapPermiso,userMnu,bodega,listPrecio,listMoneda,listServicioNoBod)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuOdo/servicioListaPrecios.scala.html
                  HASH: aaf28e7af51f3d87f4a9db473001cc0646dd15d1
                  MATRIX: 1111->1|1452->249|1479->251|1495->259|1534->261|1562->264|1630->312|1658->314|1734->365|1933->543|1962->546|2673->1231|2714->1256|2753->1257|2788->1265|2925->1375|2940->1381|2978->1398|3007->1400|3022->1406|3057->1420|3094->1430|3110->1436|3145->1449|3211->1488|3250->1489|3289->1500|3375->1559|3390->1565|3428->1582|3459->1586|3475->1592|3511->1606|3550->1626|3589->1627|3628->1638|3706->1689|3721->1695|3759->1712|3790->1716|3805->1722|3841->1736|3888->1752|3924->1761|3970->1780|3985->1786|4028->1808|4102->1855|4117->1861|4157->1880|4230->1926|4245->1932|4283->1949|4329->1968|4344->1974|4382->1991|4455->2037|4470->2043|4506->2058|4647->2172|4662->2178|4700->2195|4729->2197|4744->2203|4779->2217|4848->2259|4863->2265|4901->2282|4953->2307|4968->2313|5004->2327|5063->2359|5078->2365|5114->2380|5144->2383|5159->2389|5196->2405|5244->2426|5284->2450|5323->2451|5363->2463|5406->2479|5420->2484|5444->2487|5474->2490|5488->2495|5518->2504|5570->2525|5608->2535|5717->2617|5732->2623|5768->2638|5888->2731|5903->2737|5941->2754|5970->2756|5985->2762|6020->2776|6067->2796|6082->2802|6118->2817|6293->2965|6308->2971|6346->2988|6395->3010|6410->3016|6446->3030|6611->3168|6626->3174|6664->3191|6693->3193|6708->3199|6743->3213|6781->3223|6797->3229|6837->3247|6908->3291|6947->3292|6986->3303|7077->3367|7092->3373|7130->3390|7162->3394|7178->3400|7214->3414|7253->3434|7292->3435|7331->3446|7414->3502|7429->3508|7467->3525|7498->3529|7514->3535|7550->3549|7597->3565|7633->3574|7724->3638|7739->3644|7779->3663|7864->3721|7903->3722|7942->3733|8042->3806|8057->3812|8095->3829|8124->3831|8139->3837|8174->3851|8222->3872|8237->3878|8277->3897|8455->4048|8470->4054|8508->4071|8561->4097|8576->4103|8612->4117|8651->4137|8690->4138|8729->4149|8829->4222|8844->4228|8882->4245|8911->4247|8926->4253|8961->4267|9009->4288|9024->4294|9064->4313|9242->4464|9257->4470|9295->4487|9348->4513|9363->4519|9399->4533|9466->4569|9502->4578|9594->4642|9610->4648|9656->4672|9742->4730|9782->4731|9822->4742|9928->4820|9944->4826|9983->4843|10013->4845|10029->4851|10065->4865|10114->4886|10130->4892|10176->4916|10355->5067|10371->5073|10410->5090|10469->5121|10486->5127|10523->5141|10563->5161|10603->5162|10643->5173|10749->5251|10765->5257|10804->5274|10834->5276|10850->5282|10886->5296|10935->5317|10951->5323|10997->5347|11176->5498|11192->5504|11231->5521|11290->5552|11307->5558|11344->5572|11412->5608|11460->5628|11599->5739|11615->5745|11654->5762|11684->5764|11700->5770|11736->5784|11775->5794|11792->5800|11831->5816|11901->5858|11941->5859|11981->5870|12069->5930|12085->5936|12124->5953|12157->5958|12174->5964|12211->5978|12251->5998|12291->5999|12331->6010|12411->6062|12427->6068|12466->6085|12499->6090|12515->6096|12552->6110|12600->6126|12637->6135|12742->6212|12758->6218|12797->6235|12827->6237|12843->6243|12879->6257|12932->6281|12952->6290|13008->6323|13074->6361|13090->6367|13129->6384|13159->6386|13175->6392|13211->6406|13242->6409|13261->6418|13317->6451|13390->6493|13424->6499|14842->7890|14891->7922|14931->7923|14968->7932|15033->7969|15049->7975|15079->7983|15230->8106|15246->8112|15285->8129|15359->8175|15375->8181|15409->8193|15483->8239|15499->8245|15533->8257|15594->8286|15629->8293|16165->8801|16181->8807|16211->8815|16309->8882|16341->8886|16432->8948|16462->8949|16495->8954|16555->8985|16585->8986|16621->8994|16806->9150|16836->9151|16877->9163|16984->9241|17014->9242|17047->9247|17076->9248|17105->9249|17184->9299|17214->9300|17250->9308|17425->9454|17455->9455|17496->9467|17603->9545|17633->9546|17666->9551|17695->9552|17724->9553|17826->9627|17855->9628|17944->9688|17974->9689|18005->9692|18122->9780|18152->9781|18184->9785|18330->9903|18359->9904|18391->9908|18420->9909|18452->9913|18598->10031|18627->10032|18657->10034|18686->10035|18718->10039|18805->10097|18835->10098|18866->10101|18985->10191|19015->10192|19047->10196|19344->10465|19373->10466|19405->10470|19434->10471|19466->10475|19765->10746|19794->10747|19824->10749|19853->10750|19887->10756|19969->10809|19999->10810|20030->10813|20138->10892|20168->10893|20200->10897|20340->11009|20369->11010|20401->11014|20430->11015|20462->11019|20602->11131|20631->11132|20661->11134|20690->11135|20723->11140|20820->11208|20850->11209|20881->11212|21331->11634|21347->11640|21375->11646|21575->11817|21605->11818|21647->11831|21918->12073|21948->12074|21985->12083|22036->12105|22066->12106|22105->12116|22171->12153|22201->12154|22241->12165|22300->12195|22330->12196|22369->12207|22399->12208|22432->12212|22462->12213|22496->12219|22991->12686|23020->12687|23056->12695|23085->12696|23122->12705|23152->12706|23184->12710|23213->12711|23242->12712
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|38->7|39->8|39->8|40->9|61->30|61->30|61->30|62->31|64->33|64->33|64->33|64->33|64->33|64->33|64->33|64->33|64->33|65->34|65->34|66->35|66->35|66->35|66->35|66->35|66->35|66->35|67->36|67->36|68->37|68->37|68->37|68->37|68->37|68->37|68->37|69->38|70->39|71->40|71->40|71->40|72->41|72->41|72->41|73->42|73->42|73->42|74->43|74->43|74->43|75->44|75->44|75->44|78->47|78->47|78->47|78->47|78->47|78->47|79->48|79->48|79->48|79->48|79->48|79->48|80->49|80->49|80->49|80->49|80->49|80->49|81->50|81->50|81->50|82->51|82->51|82->51|82->51|82->51|82->51|82->51|83->52|84->53|88->57|88->57|88->57|91->60|91->60|91->60|91->60|91->60|91->60|92->61|92->61|92->61|95->64|95->64|95->64|95->64|95->64|95->64|101->70|101->70|101->70|101->70|101->70|101->70|101->70|101->70|101->70|102->71|102->71|103->72|103->72|103->72|103->72|103->72|103->72|103->72|104->73|104->73|105->74|105->74|105->74|105->74|105->74|105->74|105->74|106->75|107->76|110->79|110->79|110->79|112->81|112->81|113->82|114->83|114->83|114->83|114->83|114->83|114->83|115->84|115->84|115->84|118->87|118->87|118->87|118->87|118->87|118->87|119->88|119->88|120->89|121->90|121->90|121->90|121->90|121->90|121->90|122->91|122->91|122->91|125->94|125->94|125->94|125->94|125->94|125->94|127->96|128->97|131->100|131->100|131->100|133->102|133->102|134->103|135->104|135->104|135->104|135->104|135->104|135->104|136->105|136->105|136->105|139->108|139->108|139->108|139->108|139->108|139->108|140->109|140->109|141->110|142->111|142->111|142->111|142->111|142->111|142->111|143->112|143->112|143->112|146->115|146->115|146->115|146->115|146->115|146->115|148->117|150->119|154->123|154->123|154->123|154->123|154->123|154->123|154->123|154->123|154->123|155->124|155->124|156->125|156->125|156->125|156->125|156->125|156->125|156->125|157->126|157->126|158->127|158->127|158->127|158->127|158->127|158->127|158->127|159->128|160->129|162->131|162->131|162->131|162->131|162->131|162->131|162->131|162->131|162->131|163->132|163->132|163->132|163->132|163->132|163->132|163->132|163->132|163->132|166->135|167->136|202->171|202->171|202->171|203->172|203->172|203->172|203->172|204->173|204->173|204->173|205->174|205->174|205->174|206->175|206->175|206->175|208->177|209->178|223->192|223->192|223->192|227->196|231->200|232->201|232->201|233->202|233->202|233->202|234->203|237->206|237->206|238->207|239->208|239->208|240->209|240->209|240->209|242->211|242->211|243->212|246->215|246->215|247->216|248->217|248->217|249->218|249->218|249->218|252->221|252->221|254->223|254->223|255->224|256->225|256->225|257->226|259->228|259->228|259->228|259->228|260->229|262->231|262->231|263->232|263->232|265->234|265->234|265->234|266->235|267->236|267->236|268->237|272->241|272->241|272->241|272->241|273->242|277->246|277->246|278->247|278->247|281->250|281->250|281->250|282->251|283->252|283->252|284->253|286->255|286->255|286->255|286->255|287->256|289->258|289->258|290->259|290->259|293->262|293->262|293->262|294->263|302->271|302->271|302->271|307->276|307->276|308->277|315->284|315->284|316->285|316->285|316->285|317->286|317->286|317->286|318->287|319->288|319->288|320->289|320->289|320->289|320->289|321->290|327->296|327->296|328->297|328->297|329->298|329->298|330->299|330->299|331->300
                  -- GENERATED --
              */
          