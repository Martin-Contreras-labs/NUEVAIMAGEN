
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

object movimientoListarImg extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Guia],List[tables.Transportista],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listaGuias: List[tables.Guia], listaTransporte: List[tables.Transportista]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalContactoBodega(mapDiccionario)),format.raw/*9.38*/("""
	
	"""),format.raw/*11.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*12.4*/barraTitulo(mapDiccionario, "MOVIMIENTOS Y DESPACHOS EFECTUADOS","(CON IMAGENES)")),format.raw/*12.86*/("""
		"""),format.raw/*13.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">
				
				
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>TIPO</TH>
							<TH>COMERCIAL</TH>
							<TH>Nro<br>COTI</TH>
							<TH>Nro<br>MOV</TH>
							<TH>Nro<br>REF</TH>
							<TH>SELECT</TH>
							<TH style="min-width:80px;">FECHA<br>MOV</TH>
							<TH style="min-width:80px;">FECHA<br>INI/TER</TH>
							<TH>SUCURSAL<br>DESDE</TH>
							<TH>DESDE<br>"""),_display_(/*29.22*/mapDiccionario/*29.36*/.get("BODEGA")),format.raw/*29.50*/("""/PROYECTO</TH>
							<TH>SUCURSAL<br>HASTA</TH>
							<TH>HASTA<br>"""),_display_(/*31.22*/mapDiccionario/*31.36*/.get("BODEGA")),format.raw/*31.50*/("""/PROYECTO</TH>
							<TH>SELECT</TH>
							<TH>GUIA</TH>
							<TH>ANEXO</TH>
							<TH>ALBUM</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*39.8*/for(lista1 <- listaGuias) yield /*39.33*/{_display_(Seq[Any](format.raw/*39.34*/("""
							"""),format.raw/*40.8*/("""<TR>
								<td  style="text-align:left;">"""),_display_(/*41.40*/lista1/*41.46*/.getTipoGuia()),format.raw/*41.60*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*42.40*/lista1/*42.46*/.getNameComercial()),format.raw/*42.65*/("""</td>
								<td  style="text-align:center;">
									"""),_display_(if(lista1.getNumeroCotizacion()!=0)/*44.46*/{_display_(Seq[Any](format.raw/*44.47*/("""
										"""),format.raw/*45.11*/("""<a href="#" onclick="verCotizacion('"""),_display_(/*45.48*/lista1/*45.54*/.getId_cotizacion()),format.raw/*45.73*/("""')">
											<kbd style="background-color: #73C6B6">"""),_display_(/*46.52*/lista1/*46.58*/.getNumeroCotizacion()),format.raw/*46.80*/("""</kbd>
										</a>
									""")))}else/*48.15*/{_display_(Seq[Any](format.raw/*48.16*/("""
										"""),format.raw/*49.11*/("""--
									""")))}),format.raw/*50.11*/("""
								"""),format.raw/*51.9*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*52.42*/lista1/*52.48*/.getNumero()),format.raw/*52.60*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*53.42*/lista1/*53.48*/.getNumGuiaCliente()),format.raw/*53.68*/("""</td>
								<td style="text-align:center;">
									<a href="/movimientoDetalleGuiaConImg/"""),_display_(/*55.49*/lista1/*55.55*/.getId()),format.raw/*55.63*/("""">
										<kbd style="background-color: #73C6B6">select</kbd>
									</a>
								</td>
								<td  style="text-align:center; min-width:80px;">
									<div style="display:none">"""),_display_(/*60.37*/lista1/*60.43*/.getFecha()),format.raw/*60.54*/("""</div>
									"""),_display_(/*61.11*/utilities/*61.20*/.Fechas.DDMMAA(lista1.getFecha())),format.raw/*61.53*/("""
								"""),format.raw/*62.9*/("""</td>
								<td  style="text-align:center; min-width:80px;">
									<div style="display:none">"""),_display_(/*64.37*/lista1/*64.43*/.getFechaIniTerGuia()),format.raw/*64.64*/("""</div>
									"""),_display_(/*65.11*/utilities/*65.20*/.Fechas.DDMMAA(lista1.getFechaIniTerGuia())),format.raw/*65.63*/("""
								"""),format.raw/*66.9*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*67.40*/lista1/*67.46*/.getNameSucursalOrigen()),format.raw/*67.70*/("""</td>
								<td style="text-align:left;"><a href="#" onclick="buscaBodega('"""),_display_(/*68.73*/lista1/*68.79*/.getBodegaOrigen()),format.raw/*68.97*/("""')">"""),_display_(/*68.102*/lista1/*68.108*/.getBodegaOrigen()),format.raw/*68.126*/("""</a></td>
								<td  style="text-align:left;">"""),_display_(/*69.40*/lista1/*69.46*/.getNameSucursalDestino()),format.raw/*69.71*/("""</td>
								<td style="text-align:left;"><a href="#" onclick="buscaBodega('"""),_display_(/*70.73*/lista1/*70.79*/.getBodegaDestino()),format.raw/*70.98*/("""')">"""),_display_(/*70.103*/lista1/*70.109*/.getBodegaDestino()),format.raw/*70.128*/("""</a></td>
								
								<td style="text-align:center;">
									<a href="/movimientoDetalleGuiaConImg/"""),_display_(/*73.49*/lista1/*73.55*/.getId()),format.raw/*73.63*/("""">
										<kbd style="background-color: #73C6B6">select</kbd>
									</a>
								</td>
								<td  style="text-align:center;">
									"""),_display_(if(lista1.getGuiaPDF().equals("0"))/*78.46*/{_display_(Seq[Any](format.raw/*78.47*/("""
										"""),format.raw/*79.11*/("""--
									""")))}else/*80.15*/{_display_(Seq[Any](format.raw/*80.16*/("""
										"""),format.raw/*81.11*/("""<a href="#" onclick="mostrarPDF('"""),_display_(/*81.45*/lista1/*81.51*/.getGuiaPDF()),format.raw/*81.64*/("""')">
											<kbd style="background-color: #85C1E9">pdf</kbd>
										</a>
									""")))}),format.raw/*84.11*/("""
								"""),format.raw/*85.9*/("""</td>
								<td style="text-align:center;">
									"""),_display_(if(lista1.getDocAnexo().equals("0"))/*87.47*/{_display_(Seq[Any](format.raw/*87.48*/("""
										"""),format.raw/*88.11*/("""--
									""")))}else/*89.15*/{_display_(Seq[Any](format.raw/*89.16*/("""
										"""),format.raw/*90.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*90.52*/lista1/*90.58*/.getDocAnexo()),format.raw/*90.72*/("""')">
											<kbd style="background-color: #7F8C8D">doc</kbd>
										</a>
									""")))}),format.raw/*93.11*/("""
								"""),format.raw/*94.9*/("""</td>
								<td style="text-align:center;">
									"""),_display_(if(lista1.getFotos().equals("0"))/*96.44*/{_display_(Seq[Any](format.raw/*96.45*/("""
										"""),format.raw/*97.11*/("""--
									""")))}else/*98.15*/{_display_(Seq[Any](format.raw/*98.16*/("""
										"""),format.raw/*99.11*/("""<a href="/muestraAlbumFotos/"""),_display_(/*99.40*/lista1/*99.46*/.getFotos()),format.raw/*99.57*/("""">
											<kbd style="background-color: #7F8C8D">Ver</kbd>
										</a>
									""")))}),format.raw/*102.11*/("""
								"""),format.raw/*103.9*/("""</td>
								
							</TR>
			 			""")))}),format.raw/*106.9*/("""
					"""),format.raw/*107.6*/("""</tbody>
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
	
	

""")))}),format.raw/*168.2*/("""


"""),format.raw/*171.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*172.31*/("""{"""),format.raw/*172.32*/("""
		  """),format.raw/*173.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*173.36*/("""{"""),format.raw/*173.37*/("""
		    	"""),format.raw/*174.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 3, "desc" ]],
		    	"language": """),format.raw/*177.20*/("""{"""),format.raw/*177.21*/("""
		        	"""),format.raw/*178.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*179.11*/("""}"""),format.raw/*179.12*/("""
		  """),format.raw/*180.5*/("""}"""),format.raw/*180.6*/(""" """),format.raw/*180.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*182.2*/("""}"""),format.raw/*182.3*/(""");
	
	
	const verCotizacion = (id_cotizacion) => """),format.raw/*185.43*/("""{"""),format.raw/*185.44*/("""
		"""),format.raw/*186.3*/("""var formData = new FormData();
	  	formData.append('id_cotizacion',id_cotizacion);
        $.ajax("""),format.raw/*188.16*/("""{"""),format.raw/*188.17*/("""
            """),format.raw/*189.13*/("""url: "/verCotizacionAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*196.36*/("""{"""),format.raw/*196.37*/("""
	     		"""),format.raw/*197.9*/("""document.getElementById('mostrarLaCotizacion').innerHTML = respuesta;
	     		$('#modalVerCotizacion').modal('show');
	     	"""),format.raw/*199.8*/("""}"""),format.raw/*199.9*/("""
        """),format.raw/*200.9*/("""}"""),format.raw/*200.10*/(""");
	"""),format.raw/*201.2*/("""}"""),format.raw/*201.3*/("""
	
	"""),format.raw/*203.2*/("""const mostrarPDF = (nombrePDF) => """),format.raw/*203.36*/("""{"""),format.raw/*203.37*/("""
		  """),format.raw/*204.5*/("""document.getElementById('rutaPDF').innerHTML="<object data='/showPdf/"+nombrePDF+"' type='application/pdf' width='100%' height='720'></object>";
		  $('#muestraPDF').modal('show');
	"""),format.raw/*206.2*/("""}"""),format.raw/*206.3*/("""
	
	"""),format.raw/*208.2*/("""const descargaDocumento = (nombreDOC) => """),format.raw/*208.43*/("""{"""),format.raw/*208.44*/("""
		  """),format.raw/*209.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*211.2*/("""}"""),format.raw/*211.3*/("""
	
	
		
		
	
	
	
"""),format.raw/*219.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listaGuias:List[tables.Guia],listaTransporte:List[tables.Transportista]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listaGuias,listaTransporte)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Guia],List[tables.Transportista]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listaGuias,listaTransporte) => apply(mapDiccionario,mapPermiso,userMnu,listaGuias,listaTransporte)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuMovimientos/movimientoListarImg.scala.html
                  HASH: bcc07bf93b48823dc3440ed4433b1c2883649c47
                  MATRIX: 1067->1|1333->174|1366->182|1382->190|1421->192|1450->196|1518->244|1548->249|1603->284|1634->288|1711->339|1814->421|1844->424|2468->1021|2491->1035|2526->1049|2623->1119|2646->1133|2681->1147|2856->1296|2897->1321|2936->1322|2971->1330|3042->1374|3057->1380|3092->1394|3164->1439|3179->1445|3219->1464|3338->1556|3377->1557|3416->1568|3480->1605|3495->1611|3535->1630|3618->1686|3633->1692|3676->1714|3731->1750|3770->1751|3809->1762|3853->1775|3889->1784|3963->1831|3978->1837|4011->1849|4085->1896|4100->1902|4141->1922|4262->2016|4277->2022|4306->2030|4519->2216|4534->2222|4566->2233|4610->2250|4628->2259|4682->2292|4718->2301|4844->2400|4859->2406|4901->2427|4945->2444|4963->2453|5027->2496|5063->2505|5135->2550|5150->2556|5195->2580|5300->2658|5315->2664|5354->2682|5387->2687|5403->2693|5443->2711|5519->2760|5534->2766|5580->2791|5685->2869|5700->2875|5740->2894|5773->2899|5789->2905|5830->2924|5964->3031|5979->3037|6008->3045|6214->3224|6253->3225|6292->3236|6328->3253|6367->3254|6406->3265|6467->3299|6482->3305|6516->3318|6637->3408|6673->3417|6792->3509|6831->3510|6870->3521|6906->3538|6945->3539|6984->3550|7052->3591|7067->3597|7102->3611|7223->3701|7259->3710|7375->3799|7414->3800|7453->3811|7489->3828|7528->3829|7567->3840|7623->3869|7638->3875|7670->3886|7790->3974|7827->3983|7894->4019|7928->4025|10087->6153|10118->6156|10209->6218|10239->6219|10272->6224|10332->6255|10362->6256|10398->6264|10574->6411|10604->6412|10645->6424|10752->6502|10782->6503|10815->6508|10844->6509|10873->6510|10974->6583|11003->6584|11081->6633|11111->6634|11142->6637|11269->6735|11299->6736|11341->6749|11600->6979|11630->6980|11667->6989|11820->7114|11849->7115|11886->7124|11916->7125|11948->7129|11977->7130|12009->7134|12072->7168|12102->7169|12135->7174|12345->7356|12374->7357|12406->7361|12476->7402|12506->7403|12539->7408|12671->7512|12700->7513|12745->7530
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|42->11|43->12|43->12|44->13|60->29|60->29|60->29|62->31|62->31|62->31|70->39|70->39|70->39|71->40|72->41|72->41|72->41|73->42|73->42|73->42|75->44|75->44|76->45|76->45|76->45|76->45|77->46|77->46|77->46|79->48|79->48|80->49|81->50|82->51|83->52|83->52|83->52|84->53|84->53|84->53|86->55|86->55|86->55|91->60|91->60|91->60|92->61|92->61|92->61|93->62|95->64|95->64|95->64|96->65|96->65|96->65|97->66|98->67|98->67|98->67|99->68|99->68|99->68|99->68|99->68|99->68|100->69|100->69|100->69|101->70|101->70|101->70|101->70|101->70|101->70|104->73|104->73|104->73|109->78|109->78|110->79|111->80|111->80|112->81|112->81|112->81|112->81|115->84|116->85|118->87|118->87|119->88|120->89|120->89|121->90|121->90|121->90|121->90|124->93|125->94|127->96|127->96|128->97|129->98|129->98|130->99|130->99|130->99|130->99|133->102|134->103|137->106|138->107|199->168|202->171|203->172|203->172|204->173|204->173|204->173|205->174|208->177|208->177|209->178|210->179|210->179|211->180|211->180|211->180|213->182|213->182|216->185|216->185|217->186|219->188|219->188|220->189|227->196|227->196|228->197|230->199|230->199|231->200|231->200|232->201|232->201|234->203|234->203|234->203|235->204|237->206|237->206|239->208|239->208|239->208|240->209|242->211|242->211|250->219
                  -- GENERATED --
              */
          