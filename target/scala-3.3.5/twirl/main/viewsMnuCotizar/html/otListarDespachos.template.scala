
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

object otListarDespachos extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Guia],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listaGuias: List[tables.Guia]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalContactoBodega(mapDiccionario)),format.raw/*9.38*/("""
	
	"""),format.raw/*11.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*12.4*/barraTitulo(mapDiccionario, "DESPACHOS EFECTUADOS","(SIN IMAGENES)")),format.raw/*12.72*/("""
		"""),format.raw/*13.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">
				
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>TIPO</TH>
							<TH>SUCURSAL</TH>
							<TH>Nro<br>"""),_display_(/*21.20*/mapDiccionario/*21.34*/.get("OT")),format.raw/*21.44*/("""</TH>
							<TH>Nro<br>COTI</TH>
							<TH>Nro<br>MOV</TH>
							<TH>Nro<br>REF</TH>
							<TH>SELECT</TH>
							<TH style="min-width:80px;">FECHA<br>MOV</TH>
							<TH style="min-width:80px;">FECHA<br>INI/TER</TH>
							<TH>DESDE<br>"""),_display_(/*28.22*/mapDiccionario/*28.36*/.get("BODEGA")),format.raw/*28.50*/("""/PROYECTO</TH>
							<TH>HASTA<br>"""),_display_(/*29.22*/mapDiccionario/*29.36*/.get("BODEGA")),format.raw/*29.50*/("""/PROYECTO</TH>
							<TH>SELECT</TH>
							<TH>OBSERVACIONES</TH>
							<TH>GUIA</TH>
							<TH>ANEXO</TH>
							<TH>ALBUM</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*38.8*/for(lista1 <- listaGuias) yield /*38.33*/{_display_(Seq[Any](format.raw/*38.34*/("""
							"""),format.raw/*39.8*/("""<TR>
								<td  style="text-align:left;">"""),_display_(/*40.40*/lista1/*40.46*/.getTipoGuia()),format.raw/*40.60*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*41.40*/lista1/*41.46*/.getNameSucursalDestino),format.raw/*41.69*/("""</td>
								<td  style="text-align:center;">
									"""),_display_(if(lista1.getNumeroOt()!=0)/*43.38*/{_display_(Seq[Any](format.raw/*43.39*/("""
										"""),format.raw/*44.11*/("""<a href="#" onclick="verOt('"""),_display_(/*44.40*/lista1/*44.46*/.getId_ot()),format.raw/*44.57*/("""')">
											<kbd style="background-color: #73C6B6">"""),_display_(/*45.52*/lista1/*45.58*/.getNumeroOt()),format.raw/*45.72*/("""</kbd>
										</a>
									""")))}else/*47.15*/{_display_(Seq[Any](format.raw/*47.16*/("""
										"""),format.raw/*48.11*/("""--
									""")))}),format.raw/*49.11*/("""
								"""),format.raw/*50.9*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*51.42*/lista1/*51.48*/.getNumeroCotizacion()),format.raw/*51.70*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*52.42*/lista1/*52.48*/.getNumero()),format.raw/*52.60*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*53.42*/lista1/*53.48*/.getNumGuiaCliente()),format.raw/*53.68*/("""</td>
								<td  style="text-align:center;">
									"""),_display_(if(lista1.getNumeroCotizacion()>0)/*55.45*/{_display_(Seq[Any](format.raw/*55.46*/("""
										"""),format.raw/*56.11*/("""<form id="form_"""),_display_(/*56.27*/lista1/*56.33*/.getId()),format.raw/*56.41*/("""" method="post" action="/routes2/otDetalleDespacho/">
											<input type="hidden" name="id_guia" value=""""),_display_(/*57.56*/lista1/*57.62*/.getId()),format.raw/*57.70*/("""">
											<a href="#" onclick='document.getElementById("form_"""),_display_(/*58.64*/lista1/*58.70*/.getId()),format.raw/*58.78*/("""").submit();'>
												<kbd style="background-color: #73C6B6">select</kbd>
											</a>
										</form>
									""")))}else/*62.15*/{_display_(Seq[Any](format.raw/*62.16*/("""
										"""),format.raw/*63.11*/("""--
									""")))}),format.raw/*64.11*/("""
								"""),format.raw/*65.9*/("""</td>
								<td  style="text-align:center;">
									<div style="display:none">"""),_display_(/*67.37*/lista1/*67.43*/.getFecha()),format.raw/*67.54*/("""</div>
									"""),_display_(/*68.11*/utilities/*68.20*/.Fechas.DDMMAA(lista1.getFecha())),format.raw/*68.53*/("""
								"""),format.raw/*69.9*/("""</td>
								<td  style="min-width: 80px; text-align:center;">
									<div style="display:none">"""),_display_(/*71.37*/lista1/*71.43*/.getFechaIniTerGuia()),format.raw/*71.64*/("""</div>
									"""),_display_(/*72.11*/utilities/*72.20*/.Fechas.DDMMAA(lista1.getFechaIniTerGuia())),format.raw/*72.63*/("""
								"""),format.raw/*73.9*/("""</td>
								<td style="text-align:left;"><a href="#" onclick="buscaBodega('"""),_display_(/*74.73*/lista1/*74.79*/.getBodegaOrigen()),format.raw/*74.97*/("""')">"""),_display_(/*74.102*/lista1/*74.108*/.getBodegaOrigen()),format.raw/*74.126*/("""</a></td>
								<td style="text-align:left;"><a href="#" onclick="buscaBodega('"""),_display_(/*75.73*/lista1/*75.79*/.getBodegaDestino()),format.raw/*75.98*/("""')">"""),_display_(/*75.103*/lista1/*75.109*/.getBodegaDestino()),format.raw/*75.128*/("""</a></td>
								
								<td  style="text-align:center;">
									"""),_display_(if(lista1.getNumeroCotizacion()>0)/*78.45*/{_display_(Seq[Any](format.raw/*78.46*/("""
										"""),format.raw/*79.11*/("""<form id="form_"""),_display_(/*79.27*/lista1/*79.33*/.getId()),format.raw/*79.41*/("""" method="post" action="/routes2/otDetalleDespacho/">
											<input type="hidden" name="id_guia" value=""""),_display_(/*80.56*/lista1/*80.62*/.getId()),format.raw/*80.70*/("""">
											<a href="#" onclick='document.getElementById("form_"""),_display_(/*81.64*/lista1/*81.70*/.getId()),format.raw/*81.78*/("""").submit();'>
												<kbd style="background-color: #73C6B6">select</kbd>
											</a>
										</form>
									""")))}else/*85.15*/{_display_(Seq[Any](format.raw/*85.16*/("""
										"""),format.raw/*86.11*/("""--
									""")))}),format.raw/*87.11*/("""
								"""),format.raw/*88.9*/("""</td>
								"""),_display_(if(lista1.getObservaciones().length()>61)/*89.51*/{_display_(Seq[Any](format.raw/*89.52*/("""
									"""),format.raw/*90.10*/("""<td style="text-align:left;">"""),_display_(/*90.40*/lista1/*90.46*/.getObservaciones().substring(0,60)),format.raw/*90.81*/("""</td>
								""")))}else/*91.14*/{_display_(Seq[Any](format.raw/*91.15*/("""
									"""),format.raw/*92.10*/("""<td style="text-align:left;">"""),_display_(/*92.40*/lista1/*92.46*/.getObservaciones()),format.raw/*92.65*/("""</td>
								""")))}),format.raw/*93.10*/("""
								"""),format.raw/*94.9*/("""<td  style="text-align:center;">
									"""),_display_(if(lista1.getGuiaPDF().equals("0"))/*95.46*/{_display_(Seq[Any](format.raw/*95.47*/("""
										"""),format.raw/*96.11*/("""--
									""")))}else/*97.15*/{_display_(Seq[Any](format.raw/*97.16*/("""
										"""),format.raw/*98.11*/("""<a href="#" onclick="mostrarPDF('"""),_display_(/*98.45*/lista1/*98.51*/.getGuiaPDF()),format.raw/*98.64*/("""')">
											<kbd style="background-color: #85C1E9">pdf</kbd>
										</a>
									""")))}),format.raw/*101.11*/("""
								"""),format.raw/*102.9*/("""</td>
								<td  style="text-align:center;">
									"""),_display_(if(lista1.getDocAnexo().equals("0"))/*104.47*/{_display_(Seq[Any](format.raw/*104.48*/("""
										"""),format.raw/*105.11*/("""--
									""")))}else/*106.15*/{_display_(Seq[Any](format.raw/*106.16*/("""
										"""),format.raw/*107.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*107.52*/lista1/*107.58*/.getDocAnexo()),format.raw/*107.72*/("""')">
											<kbd style="background-color: #7F8C8D">doc</kbd>
										</a>
									""")))}),format.raw/*110.11*/("""
								"""),format.raw/*111.9*/("""</td>
								<td style="text-align:center;">
									"""),_display_(if(lista1.getFotos().equals("0"))/*113.44*/{_display_(Seq[Any](format.raw/*113.45*/("""
										"""),format.raw/*114.11*/("""--
									""")))}else/*115.15*/{_display_(Seq[Any](format.raw/*115.16*/("""
										"""),format.raw/*116.11*/("""<a href="/muestraAlbumFotos/"""),_display_(/*116.40*/lista1/*116.46*/.getFotos()),format.raw/*116.57*/("""">
											<kbd style="background-color: #7F8C8D">Ver</kbd>
										</a>
									""")))}),format.raw/*119.11*/("""
								"""),format.raw/*120.9*/("""</td>
							</TR>
			 			""")))}),format.raw/*122.9*/("""
					"""),format.raw/*123.6*/("""</tbody>
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
				</div>
			</div>
		</div>
	</div>
	
	<div id='modalVerOt' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable' style="max-width: 80% !important;" role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class="modal-title">"""),_display_(/*140.31*/mapDiccionario/*140.45*/.get("ORDEN_DE_TRABAJO")),format.raw/*140.69*/("""</h5>
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				          <span aria-hidden="true">×</span>
				        </button>
			</div>
			<div class="modal-body">
				<div id="mostrarLaOt"></div>
   				<div class='row'>
						<div class='col-sm-12' style='text-align:center'>
							<button type='button' class='btn btn-sm  btn-success' style='font-size: 10px;' data-dismiss='modal'>Listo</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div id='modalVerCotizacion' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable' style="max-width: 80% !important;" role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>COTIZACION</h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<div id='mostrarLaCotizacion'></div>
	   				<div class='row'>
						<div class='col-sm-12' style='text-align:center'>
							<button type='button' class='btn btn-sm  btn-success' style='font-size: 10px;' data-dismiss='modal'>Listo</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div id='muestraPDF' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>MOVIMIENTO</h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<div id="rutaPDF"></div>
	   				<div class='row'>
						<div class='col-sm-12' style='text-align:center'>
							<button type='button' class='btn btn-sm  btn-success' style='font-size: 10px;' data-dismiss='modal'>Listo</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<form class="formulario" id="formDescargaDocumento" method="post" action="/downloadPDF/">	
		<input type="hidden" id="descargaDocumento" name="nombreArchivo">
	</form>
	
	<form id="form_eliminar" method="post" action="/otDespachoElimina/">
		<input type="hidden" id="form_id_guia" name="id_guia">
		<input type="hidden" id="form_id_ot" name="id_ot">
	</form>

""")))}),format.raw/*208.2*/("""


"""),format.raw/*211.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*212.31*/("""{"""),format.raw/*212.32*/("""
		  """),format.raw/*213.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*213.36*/("""{"""),format.raw/*213.37*/("""
		    	"""),format.raw/*214.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 7, "desc" ]],
		    	"language": """),format.raw/*217.20*/("""{"""),format.raw/*217.21*/("""
		        	"""),format.raw/*218.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*219.11*/("""}"""),format.raw/*219.12*/("""
		  """),format.raw/*220.5*/("""}"""),format.raw/*220.6*/(""" """),format.raw/*220.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*222.2*/("""}"""),format.raw/*222.3*/(""");
	
	
	const verCotizacion = (id_cotizacion) => """),format.raw/*225.43*/("""{"""),format.raw/*225.44*/("""
		"""),format.raw/*226.3*/("""var formData = new FormData();
	  	formData.append('id_cotizacion',id_cotizacion);
        $.ajax("""),format.raw/*228.16*/("""{"""),format.raw/*228.17*/("""
            """),format.raw/*229.13*/("""url: "/verCotizacionAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*236.36*/("""{"""),format.raw/*236.37*/("""
	     		"""),format.raw/*237.9*/("""document.getElementById('mostrarLaCotizacion').innerHTML = respuesta;
	     		$('#modalVerCotizacion').modal('show');
	     	"""),format.raw/*239.8*/("""}"""),format.raw/*239.9*/("""
        """),format.raw/*240.9*/("""}"""),format.raw/*240.10*/(""");
	"""),format.raw/*241.2*/("""}"""),format.raw/*241.3*/("""
	
	"""),format.raw/*243.2*/("""const verOt = (id_ot) => """),format.raw/*243.27*/("""{"""),format.raw/*243.28*/("""
		"""),format.raw/*244.3*/("""var formData = new FormData();
	  	formData.append('id_ot',id_ot);
        $.ajax("""),format.raw/*246.16*/("""{"""),format.raw/*246.17*/("""
            """),format.raw/*247.13*/("""url: "/verOtAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*254.36*/("""{"""),format.raw/*254.37*/("""
	     		"""),format.raw/*255.9*/("""document.getElementById('mostrarLaOt').innerHTML = respuesta;
	     		$('#modalVerOt').modal('show');
	     	"""),format.raw/*257.8*/("""}"""),format.raw/*257.9*/("""
        """),format.raw/*258.9*/("""}"""),format.raw/*258.10*/(""");
	"""),format.raw/*259.2*/("""}"""),format.raw/*259.3*/("""
	
	"""),format.raw/*261.2*/("""const mostrarPDF = (nombrePDF) => """),format.raw/*261.36*/("""{"""),format.raw/*261.37*/("""
		  """),format.raw/*262.5*/("""document.getElementById('rutaPDF').innerHTML="<object data='/showPdf/"+nombrePDF+"' type='application/pdf' width='100%' height='720'></object>";
		  $('#muestraPDF').modal('show');
	"""),format.raw/*264.2*/("""}"""),format.raw/*264.3*/("""
	
	"""),format.raw/*266.2*/("""const descargaDocumento = (nombreDOC) => """),format.raw/*266.43*/("""{"""),format.raw/*266.44*/("""
		  """),format.raw/*267.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*269.2*/("""}"""),format.raw/*269.3*/("""
	
	"""),format.raw/*271.2*/("""const eliminarDespacho = (numGuia, id_ot, id_guia) => """),format.raw/*271.56*/("""{"""),format.raw/*271.57*/("""
		"""),format.raw/*272.3*/("""alertify.confirm("Esta seguro de eliminar el despacho con movimiento nro: "+numGuia, function (e) """),format.raw/*272.101*/("""{"""),format.raw/*272.102*/("""
			"""),format.raw/*273.4*/("""if (e) """),format.raw/*273.11*/("""{"""),format.raw/*273.12*/("""
				"""),format.raw/*274.5*/("""$("#form_id_guia").val(id_guia);
				$("#form_id_ot").val(id_ot);
				document.getElementById("form_eliminar").submit();
			"""),format.raw/*277.4*/("""}"""),format.raw/*277.5*/("""
		"""),format.raw/*278.3*/("""}"""),format.raw/*278.4*/(""");
	"""),format.raw/*279.2*/("""}"""),format.raw/*279.3*/("""
	
	
"""),format.raw/*282.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listaGuias:List[tables.Guia]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listaGuias)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Guia]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listaGuias) => apply(mapDiccionario,mapPermiso,userMnu,listaGuias)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizar/otListarDespachos.scala.html
                  HASH: f8ffda45b67150bec07da6958e16003df0d18f42
                  MATRIX: 1034->1|1255->129|1288->137|1304->145|1343->147|1372->151|1440->199|1470->204|1525->239|1556->243|1633->294|1722->362|1752->365|2119->705|2142->719|2173->729|2442->971|2465->985|2500->999|2563->1035|2586->1049|2621->1063|2826->1242|2867->1267|2906->1268|2941->1276|3012->1320|3027->1326|3062->1340|3134->1385|3149->1391|3193->1414|3304->1498|3343->1499|3382->1510|3438->1539|3453->1545|3485->1556|3568->1612|3583->1618|3618->1632|3673->1668|3712->1669|3751->1680|3795->1693|3831->1702|3905->1749|3920->1755|3963->1777|4037->1824|4052->1830|4085->1842|4159->1889|4174->1895|4215->1915|4333->2006|4372->2007|4411->2018|4454->2034|4469->2040|4498->2048|4634->2157|4649->2163|4678->2171|4771->2237|4786->2243|4815->2251|4961->2378|5000->2379|5039->2390|5083->2403|5119->2412|5229->2495|5244->2501|5276->2512|5320->2529|5338->2538|5392->2571|5428->2580|5555->2680|5570->2686|5612->2707|5656->2724|5674->2733|5738->2776|5774->2785|5879->2863|5894->2869|5933->2887|5966->2892|5982->2898|6022->2916|6131->2998|6146->3004|6186->3023|6219->3028|6235->3034|6276->3053|6407->3157|6446->3158|6485->3169|6528->3185|6543->3191|6572->3199|6708->3308|6723->3314|6752->3322|6845->3388|6860->3394|6889->3402|7035->3529|7074->3530|7113->3541|7157->3554|7193->3563|7276->3619|7315->3620|7353->3630|7410->3660|7425->3666|7481->3701|7519->3720|7558->3721|7596->3731|7653->3761|7668->3767|7708->3786|7754->3801|7790->3810|7895->3888|7934->3889|7973->3900|8009->3917|8048->3918|8087->3929|8148->3963|8163->3969|8197->3982|8319->4072|8356->4081|8477->4174|8517->4175|8557->4186|8594->4203|8634->4204|8674->4215|8743->4256|8759->4262|8795->4276|8917->4366|8954->4375|9071->4464|9111->4465|9151->4476|9188->4493|9228->4494|9268->4505|9325->4534|9341->4540|9374->4551|9494->4639|9531->4648|9589->4675|9623->4681|10280->5310|10304->5324|10350->5348|12863->7830|12894->7833|12985->7895|13015->7896|13048->7901|13108->7932|13138->7933|13174->7941|13350->8088|13380->8089|13421->8101|13528->8179|13558->8180|13591->8185|13620->8186|13649->8187|13750->8260|13779->8261|13857->8310|13887->8311|13918->8314|14045->8412|14075->8413|14117->8426|14376->8656|14406->8657|14443->8666|14596->8791|14625->8792|14662->8801|14692->8802|14724->8806|14753->8807|14785->8811|14839->8836|14869->8837|14900->8840|15011->8922|15041->8923|15083->8936|15334->9158|15364->9159|15401->9168|15538->9277|15567->9278|15604->9287|15634->9288|15666->9292|15695->9293|15727->9297|15790->9331|15820->9332|15853->9337|16063->9519|16092->9520|16124->9524|16194->9565|16224->9566|16257->9571|16389->9675|16418->9676|16450->9680|16533->9734|16563->9735|16594->9738|16722->9836|16753->9837|16785->9841|16821->9848|16851->9849|16884->9854|17036->9978|17065->9979|17096->9982|17125->9983|17157->9987|17186->9988|17219->9993
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|42->11|43->12|43->12|44->13|52->21|52->21|52->21|59->28|59->28|59->28|60->29|60->29|60->29|69->38|69->38|69->38|70->39|71->40|71->40|71->40|72->41|72->41|72->41|74->43|74->43|75->44|75->44|75->44|75->44|76->45|76->45|76->45|78->47|78->47|79->48|80->49|81->50|82->51|82->51|82->51|83->52|83->52|83->52|84->53|84->53|84->53|86->55|86->55|87->56|87->56|87->56|87->56|88->57|88->57|88->57|89->58|89->58|89->58|93->62|93->62|94->63|95->64|96->65|98->67|98->67|98->67|99->68|99->68|99->68|100->69|102->71|102->71|102->71|103->72|103->72|103->72|104->73|105->74|105->74|105->74|105->74|105->74|105->74|106->75|106->75|106->75|106->75|106->75|106->75|109->78|109->78|110->79|110->79|110->79|110->79|111->80|111->80|111->80|112->81|112->81|112->81|116->85|116->85|117->86|118->87|119->88|120->89|120->89|121->90|121->90|121->90|121->90|122->91|122->91|123->92|123->92|123->92|123->92|124->93|125->94|126->95|126->95|127->96|128->97|128->97|129->98|129->98|129->98|129->98|132->101|133->102|135->104|135->104|136->105|137->106|137->106|138->107|138->107|138->107|138->107|141->110|142->111|144->113|144->113|145->114|146->115|146->115|147->116|147->116|147->116|147->116|150->119|151->120|153->122|154->123|171->140|171->140|171->140|239->208|242->211|243->212|243->212|244->213|244->213|244->213|245->214|248->217|248->217|249->218|250->219|250->219|251->220|251->220|251->220|253->222|253->222|256->225|256->225|257->226|259->228|259->228|260->229|267->236|267->236|268->237|270->239|270->239|271->240|271->240|272->241|272->241|274->243|274->243|274->243|275->244|277->246|277->246|278->247|285->254|285->254|286->255|288->257|288->257|289->258|289->258|290->259|290->259|292->261|292->261|292->261|293->262|295->264|295->264|297->266|297->266|297->266|298->267|300->269|300->269|302->271|302->271|302->271|303->272|303->272|303->272|304->273|304->273|304->273|305->274|308->277|308->277|309->278|309->278|310->279|310->279|313->282
                  -- GENERATED --
              */
          