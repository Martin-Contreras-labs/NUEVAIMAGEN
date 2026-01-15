
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

object servicioPreciosVariable2 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template8[Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,tables.ListaPrecioServicio,List[tables.PrecioVariableServicio],Long,utilities.Fechas,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
bodega: tables.BodegaEmpresa, listaPrecioServicio: tables.ListaPrecioServicio, listPVariable: List[tables.PrecioVariableServicio], 
decMon: Long, hoy: utilities.Fechas):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.1*/("""    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "PRECIOS VARIABLES (CREAR/MODIFICAR/ELIMINAR)", mapDiccionario.get("BODEGA")+"/PROYECTO: "+bodega.getNombre().toUpperCase())),format.raw/*9.156*/("""
		"""),format.raw/*10.3*/("""<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button" value="AGREGAR NUEVA FECHA" class="btn btn-info btn-sm rounded-pill btn-block" onclick='$("#agregarFecha").modal("show")'>
				</div>
			</div>
		</div>
		<br>
		<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-8 col-md-8 col-lg-8">
				
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR> 
							<TH width="120px">FECHA</TH>
							<TH>NRO.COTI</TH>
							<TH>CODIGO</TH>
							<TH>SERVICIO</TH>
							<TH width="20px">MONEDA</TH>
							<TH>VALOR</TH>
							<TH width="5px">DEL</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*34.8*/for(lista1 <- listPVariable) yield /*34.36*/{_display_(Seq[Any](format.raw/*34.37*/("""
							"""),format.raw/*35.8*/("""<TR>
								<td style="text-align:center">
									<div style="display:none">"""),_display_(/*37.37*/utilities/*37.46*/.Fechas.AAMMDD(lista1.fecha)),format.raw/*37.74*/("""</div>
									"""),_display_(/*38.11*/utilities/*38.20*/.Fechas.DDMMAA(lista1.fecha)),format.raw/*38.48*/("""
								"""),format.raw/*39.9*/("""</td>
								<td style="text-align: center">"""),_display_(/*40.41*/listaPrecioServicio/*40.60*/.getNumeroCotiOdo()),format.raw/*40.79*/("""</td>
								<td style="text-align: center">"""),_display_(/*41.41*/listaPrecioServicio/*41.60*/.getCodServicio()),format.raw/*41.77*/("""</td>
								<td style="text-align:center">"""),_display_(/*42.40*/listaPrecioServicio/*42.59*/.getNomServicio()),format.raw/*42.76*/("""</td>
								<td style="text-align:center">
									"""),_display_(/*44.11*/listaPrecioServicio/*44.30*/.getNickMoneda()),format.raw/*44.46*/("""
								"""),format.raw/*45.9*/("""</td>
								<td style="text-align:right; max-width:50px !important">
									
									<div style="display:none">"""),_display_(/*48.37*/lista1/*48.43*/.getPrecioSTR()),format.raw/*48.58*/("""</div>
									
									<input type="text" class="form-control right width80"
										value = """"),_display_(/*51.21*/lista1/*51.27*/.getPrecioSTR()),format.raw/*51.42*/(""""
										onfocus="value=value.replace(/,/g,'')" 
										onfocusout = "if(value.trim()=='') value=0; value = formatStandar(value, '"""),_display_(/*53.86*/decMon),format.raw/*53.92*/("""');"
										onkeydown="return ingresoDouble(window.event)"
										onchange="if(value.trim()=='') value=0; cambiaPrecio('"""),_display_(/*55.66*/lista1/*55.72*/.getId_bodegaEmpresa()),format.raw/*55.94*/("""','"""),_display_(/*55.98*/lista1/*55.104*/.getId_servicio),format.raw/*55.119*/("""','"""),_display_(/*55.123*/utilities/*55.132*/.Fechas.AAMMDD(lista1.fecha)),format.raw/*55.160*/("""',value,'"""),_display_(/*55.170*/lista1/*55.176*/.getId_cotiOdo),format.raw/*55.190*/("""'); value = formatStandar(value, '"""),_display_(/*55.225*/decMon),format.raw/*55.231*/("""');">
								</td>
								<td style="text-align:center;">
									<a href="#" onclick= "eliminarPrecio('"""),_display_(/*58.49*/lista1/*58.55*/.getId_bodegaEmpresa()),format.raw/*58.77*/("""','"""),_display_(/*58.81*/lista1/*58.87*/.getId_servicio),format.raw/*58.102*/("""','"""),_display_(/*58.106*/utilities/*58.115*/.Fechas.AAMMDD(lista1.fecha)),format.raw/*58.143*/("""','"""),_display_(/*58.147*/lista1/*58.153*/.getId_cotiOdo),format.raw/*58.167*/("""')">
										<kbd style="background-color: red">X</kbd>
									</a>
								</td>
							</TR>
			 			""")))}),format.raw/*63.9*/("""
					"""),format.raw/*64.6*/("""</tbody>
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/servicioPreciosVariable1/"""),_display_(/*71.147*/bodega/*71.153*/.getId()),format.raw/*71.161*/("""';">
				</div>
			</div>
		</div>
	</div>
	
	
	<div id="agregarFecha" class="modal show" role="dialog" data-backdrop="static" data-keyboard="false" style="display: none; padding-right: 0px;" aria-modal="true">
		<div class="modal-dialog modal-dialog-scrollable modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">AGREGAR VALOR</h5>
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				          <span aria-hidden="true">×</span>
				        </button>
				</div>
				<div class="modal-body">
					<form action="/servicioAgregaVariable/" method="POST">
						<table class="table table-sm table-bordered table-condensed table-hover table-fluid">
							<tr>
								<td>FECHA:</td>
								<td width="80px" style="text-align:center">
									<input type="hidden" name="id_bodega" value=""""),_display_(/*93.56*/listaPrecioServicio/*93.75*/.getId_bodegaEmpresa()),format.raw/*93.97*/("""">
									<input type="hidden" name="id_servicio" value=""""),_display_(/*94.58*/listaPrecioServicio/*94.77*/.getId_servicio()),format.raw/*94.94*/("""">
									<input type="hidden" name="id_cotiOdo" value=""""),_display_(/*95.57*/listaPrecioServicio/*95.76*/.getId_cotiOdo()),format.raw/*95.92*/("""">
									<input type="date" class="form-control center"
										id="fecha"
										name="fecha"
							  			onblur="if(!limitaFecha(value,720,10)) """),format.raw/*99.52*/("""{"""),format.raw/*99.53*/("""
													"""),format.raw/*100.14*/("""value = '"""),_display_(/*100.24*/hoy/*100.27*/.getFechaStrAAMMDD()),format.raw/*100.47*/("""';
												"""),format.raw/*101.13*/("""}"""),format.raw/*101.14*/(""""
							  			value=""""),_display_(/*102.21*/hoy/*102.24*/.getFechaStrAAMMDD()),format.raw/*102.44*/(""""
										required>
								</td>
							</tr>
							<tr>
								<td>MONEDA:</td>
								<td style="text-align:center;">
									"""),_display_(/*109.11*/listaPrecioServicio/*109.30*/.getNickMoneda()),format.raw/*109.46*/("""
								"""),format.raw/*110.9*/("""</td>
							</tr>
							<tr>
								<td>VALOR:</td>
								<td style="text-align:right; max-width:50px !important">
									<input type="text" class="form-control right width80"
										name="precio"
										onfocus="value=value.replace(/,/g,'')" 
										onfocusout = "if(value.trim()=='') value=0; value = formatStandar(value, '"""),_display_(/*118.86*/decMon),format.raw/*118.92*/("""');"
										onkeydown="return ingresoDouble(window.event)"
										onchange="if(value.trim()=='') value=0; value = formatStandar(value,'"""),_display_(/*120.81*/decMon),format.raw/*120.87*/("""');"
										autocomplete="off"
										required>
								</td>
							</tr>
						</table>
					
						<div class="row">
							<div class="col-sm-12" style="text-align:center">
								<button type="submit" class="btn btn-sm  btn-success" style="font-size: 10px;">Grabar</button>
								<button type="button" class="btn btn-sm  btn-warning" style="font-size: 10px;" data-dismiss="modal">Cancelar</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	
	
	
	<form id="form_eliminar" method="post" action="/servicioEliminaVariable/">
		<input type="hidden" id="form_id_bodega" name="id_bodega">
		<input type="hidden" id="form_id_servicio" name="id_servicio">
		<input type="hidden" id="form_fecha" name="fecha">
		<input type="hidden" id="form_id_cotiOdo" name="id_cotiOdo">
	</form>
	
	
	
	
	
	
""")))}),format.raw/*154.2*/("""


"""),format.raw/*157.1*/("""<script type="text/javascript">
	
	$(document).ready(function() """),format.raw/*159.31*/("""{"""),format.raw/*159.32*/("""
		
		  """),format.raw/*161.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*161.36*/("""{"""),format.raw/*161.37*/("""
		    	"""),format.raw/*162.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 0, "desc" ]],
		    	"language": """),format.raw/*165.20*/("""{"""),format.raw/*165.21*/("""
		        	"""),format.raw/*166.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		         """),format.raw/*167.12*/("""}"""),format.raw/*167.13*/("""
		  """),format.raw/*168.5*/("""}"""),format.raw/*168.6*/(""" """),format.raw/*168.7*/(""");

		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*171.2*/("""}"""),format.raw/*171.3*/(""");
	
	const eliminarPrecio = (id_bodega,id_servicio,fecha, id_cotiOdo) => """),format.raw/*173.70*/("""{"""),format.raw/*173.71*/("""
		"""),format.raw/*174.3*/("""alertify.confirm("Esta seguro de eliminar el precio", function (e) """),format.raw/*174.70*/("""{"""),format.raw/*174.71*/("""
			"""),format.raw/*175.4*/("""if (e) """),format.raw/*175.11*/("""{"""),format.raw/*175.12*/("""
				"""),format.raw/*176.5*/("""$("#form_id_bodega").val(id_bodega);
				$("#form_id_servicio").val(id_servicio);
				$("#form_fecha").val(fecha);
				$("#form_id_cotiOdo").val(id_cotiOdo);
				document.getElementById("form_eliminar").submit();
			"""),format.raw/*181.4*/("""}"""),format.raw/*181.5*/("""
		"""),format.raw/*182.3*/("""}"""),format.raw/*182.4*/(""");
		
		
	"""),format.raw/*185.2*/("""}"""),format.raw/*185.3*/("""
	
	
	"""),format.raw/*188.2*/("""const cambiaPrecio = (id_bodega, id_servicio, fecha, valor, id_cotiOdo) => """),format.raw/*188.77*/("""{"""),format.raw/*188.78*/("""
		"""),format.raw/*189.3*/("""var formData = new FormData();
		formData.append('id_bodega',id_bodega);
	  	formData.append('id_servicio',id_servicio);
		formData.append('valor',valor);
		formData.append('fecha',fecha);
		formData.append('id_cotiOdo',id_cotiOdo);
        $.ajax("""),format.raw/*195.16*/("""{"""),format.raw/*195.17*/("""
            """),format.raw/*196.13*/("""url: "/modPrecioVariableServicioAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*203.36*/("""{"""),format.raw/*203.37*/("""
	     		"""),format.raw/*204.9*/("""if(respuesta=="error")"""),format.raw/*204.31*/("""{"""),format.raw/*204.32*/("""
	     			"""),format.raw/*205.10*/("""alertify.alert(msgError, function () """),format.raw/*205.47*/("""{"""),format.raw/*205.48*/("""
		     			"""),format.raw/*206.11*/("""location.href = "/";
		     		"""),format.raw/*207.10*/("""}"""),format.raw/*207.11*/(""");
	     		"""),format.raw/*208.9*/("""}"""),format.raw/*208.10*/("""
	     	"""),format.raw/*209.8*/("""}"""),format.raw/*209.9*/("""
        """),format.raw/*210.9*/("""}"""),format.raw/*210.10*/(""");
	"""),format.raw/*211.2*/("""}"""),format.raw/*211.3*/("""
	
	
"""),format.raw/*214.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,bodega:tables.BodegaEmpresa,listaPrecioServicio:tables.ListaPrecioServicio,listPVariable:List[tables.PrecioVariableServicio],decMon:Long,hoy:utilities.Fechas): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,bodega,listaPrecioServicio,listPVariable,decMon,hoy)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,tables.ListaPrecioServicio,List[tables.PrecioVariableServicio],Long,utilities.Fechas) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,bodega,listaPrecioServicio,listPVariable,decMon,hoy) => apply(mapDiccionario,mapPermiso,userMnu,bodega,listaPrecioServicio,listPVariable,decMon,hoy)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuOdo/servicioPreciosVariable2.scala.html
                  HASH: b48890792fe07200c5ef795bfbb5ca87c5762688
                  MATRIX: 1125->1|1484->267|1516->274|1532->282|1571->284|1599->287|1667->335|1695->337|1771->388|1944->540|1974->543|2830->1373|2874->1401|2913->1402|2948->1410|3055->1490|3073->1499|3122->1527|3166->1544|3184->1553|3233->1581|3269->1590|3342->1636|3370->1655|3410->1674|3483->1720|3511->1739|3549->1756|3621->1801|3649->1820|3687->1837|3769->1892|3797->1911|3834->1927|3870->1936|4014->2053|4029->2059|4065->2074|4192->2174|4207->2180|4243->2195|4407->2332|4434->2338|4588->2465|4603->2471|4646->2493|4677->2497|4693->2503|4730->2518|4762->2522|4781->2531|4831->2559|4869->2569|4885->2575|4921->2589|4984->2624|5012->2630|5147->2738|5162->2744|5205->2766|5236->2770|5251->2776|5288->2791|5320->2795|5339->2804|5389->2832|5421->2836|5437->2842|5473->2856|5610->2963|5643->2969|5984->3282|6000->3288|6030->3296|6950->4189|6978->4208|7021->4230|7108->4290|7136->4309|7174->4326|7260->4385|7288->4404|7325->4420|7507->4574|7536->4575|7579->4589|7617->4599|7630->4602|7672->4622|7716->4637|7746->4638|7796->4660|7809->4663|7851->4683|8015->4819|8044->4838|8082->4854|8119->4863|8489->5205|8517->5211|8687->5353|8715->5359|9584->6197|9615->6200|9708->6264|9738->6265|9774->6273|9834->6304|9864->6305|9900->6313|10076->6460|10106->6461|10147->6473|10255->6552|10285->6553|10318->6558|10347->6559|10376->6560|10478->6634|10507->6635|10610->6709|10640->6710|10671->6713|10767->6780|10797->6781|10829->6785|10865->6792|10895->6793|10928->6798|11172->7014|11201->7015|11232->7018|11261->7019|11299->7029|11328->7030|11362->7036|11466->7111|11496->7112|11527->7115|11804->7363|11834->7364|11876->7377|12147->7619|12177->7620|12214->7629|12265->7651|12295->7652|12334->7662|12400->7699|12430->7700|12470->7711|12529->7741|12559->7742|12598->7753|12628->7754|12664->7762|12693->7763|12730->7772|12760->7773|12792->7777|12821->7778|12854->7783
                  LINES: 28->1|35->4|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|65->34|65->34|65->34|66->35|68->37|68->37|68->37|69->38|69->38|69->38|70->39|71->40|71->40|71->40|72->41|72->41|72->41|73->42|73->42|73->42|75->44|75->44|75->44|76->45|79->48|79->48|79->48|82->51|82->51|82->51|84->53|84->53|86->55|86->55|86->55|86->55|86->55|86->55|86->55|86->55|86->55|86->55|86->55|86->55|86->55|86->55|89->58|89->58|89->58|89->58|89->58|89->58|89->58|89->58|89->58|89->58|89->58|89->58|94->63|95->64|102->71|102->71|102->71|124->93|124->93|124->93|125->94|125->94|125->94|126->95|126->95|126->95|130->99|130->99|131->100|131->100|131->100|131->100|132->101|132->101|133->102|133->102|133->102|140->109|140->109|140->109|141->110|149->118|149->118|151->120|151->120|185->154|188->157|190->159|190->159|192->161|192->161|192->161|193->162|196->165|196->165|197->166|198->167|198->167|199->168|199->168|199->168|202->171|202->171|204->173|204->173|205->174|205->174|205->174|206->175|206->175|206->175|207->176|212->181|212->181|213->182|213->182|216->185|216->185|219->188|219->188|219->188|220->189|226->195|226->195|227->196|234->203|234->203|235->204|235->204|235->204|236->205|236->205|236->205|237->206|238->207|238->207|239->208|239->208|240->209|240->209|241->210|241->210|242->211|242->211|245->214
                  -- GENERATED --
              */
          