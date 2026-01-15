
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

object otListaDespachoModificar extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Guia],play.twirl.api.HtmlFormat.Appendable] {

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
		"""),_display_(/*12.4*/barraTitulo(mapDiccionario, "LISTA DE DESPACHOS","MODIFICAR y/o ELIMINAR (SOLO DESPACHOS DESDE "+mapDiccionario.get("OT")+")")),format.raw/*12.130*/("""
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
							<TH>EDIT</TH>
							<TH style="min-width:80px;">FECHA<br>MOV</TH>
							<TH style="min-width:80px;">FECHA<br>INI/TER</TH>
							<TH>DESDE<br>"""),_display_(/*28.22*/mapDiccionario/*28.36*/.get("BODEGA")),format.raw/*28.50*/("""/PROYECTO</TH>
							<TH>HASTA<br>"""),_display_(/*29.22*/mapDiccionario/*29.36*/.get("BODEGA")),format.raw/*29.50*/("""/PROYECTO</TH>
							<TH>ULTIMA<br>ACTUALIZACION</TH>
							<TH>ESTIMACION<br>DE ENTREGA</TH>
							<TH>EDIT</TH>
							<TH>OBSERVACIONES</TH>
							<TH>GUIA</TH>
							<TH>ANEXO</TH>
							<TH>ALBUM</TH>
							<TH>DEL</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*41.8*/for(lista1 <- listaGuias) yield /*41.33*/{_display_(Seq[Any](format.raw/*41.34*/("""
							"""),format.raw/*42.8*/("""<TR>
								<td  style="text-align:left;">"""),_display_(/*43.40*/lista1/*43.46*/.getTipoGuia()),format.raw/*43.60*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*44.40*/lista1/*44.46*/.getNameSucursalDestino),format.raw/*44.69*/("""</td>
								<td  style="text-align:center;">
									"""),_display_(if(lista1.getNumeroOt()!=0)/*46.38*/{_display_(Seq[Any](format.raw/*46.39*/("""
										"""),format.raw/*47.11*/("""<a href="#" onclick="verOt('"""),_display_(/*47.40*/lista1/*47.46*/.getId_ot()),format.raw/*47.57*/("""')">
											<kbd style="background-color: #73C6B6">"""),_display_(/*48.52*/lista1/*48.58*/.getNumeroOt()),format.raw/*48.72*/("""</kbd>
										</a>
									""")))}else/*50.15*/{_display_(Seq[Any](format.raw/*50.16*/("""
										"""),format.raw/*51.11*/("""--
									""")))}),format.raw/*52.11*/("""
								"""),format.raw/*53.9*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*54.42*/lista1/*54.48*/.getNumeroCotizacion()),format.raw/*54.70*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*55.42*/lista1/*55.48*/.getNumero()),format.raw/*55.60*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*56.42*/lista1/*56.48*/.getNumGuiaCliente()),format.raw/*56.68*/("""</td>
								<td  style="text-align:center;">
									"""),_display_(if(lista1.getNumeroCotizacion()>0)/*58.45*/{_display_(Seq[Any](format.raw/*58.46*/("""
										"""),format.raw/*59.11*/("""<form id="form_"""),_display_(/*59.27*/lista1/*59.33*/.getId()),format.raw/*59.41*/("""" method="post" action="/otDespachoModificar/">
											<input type="hidden" name="id_guia" value=""""),_display_(/*60.56*/lista1/*60.62*/.getId()),format.raw/*60.70*/("""">
											<a href="#" onclick='document.getElementById("form_"""),_display_(/*61.64*/lista1/*61.70*/.getId()),format.raw/*61.78*/("""").submit();'>
												<kbd style="background-color: #73C6B6">edit</kbd>
											</a>
										</form>
									""")))}else/*65.15*/{_display_(Seq[Any](format.raw/*65.16*/("""
										"""),format.raw/*66.11*/("""--
									""")))}),format.raw/*67.11*/("""
								"""),format.raw/*68.9*/("""</td>
								
								<td  style="min-width: 80px; text-align:center;">
									<div style="display:none">"""),_display_(/*71.37*/lista1/*71.43*/.getFecha()),format.raw/*71.54*/("""</div>
									"""),_display_(/*72.11*/utilities/*72.20*/.Fechas.DDMMAA(lista1.getFecha())),format.raw/*72.53*/("""
								"""),format.raw/*73.9*/("""</td>
								<td  style="min-width: 80px; text-align:center;">
									<div style="display:none">"""),_display_(/*75.37*/lista1/*75.43*/.getFechaIniTerGuia()),format.raw/*75.64*/("""</div>
									"""),_display_(/*76.11*/utilities/*76.20*/.Fechas.DDMMAA(lista1.getFechaIniTerGuia())),format.raw/*76.63*/("""
								"""),format.raw/*77.9*/("""</td>
								<td style="text-align:left;"><a href="#" onclick="buscaBodega('"""),_display_(/*78.73*/lista1/*78.79*/.getBodegaOrigen()),format.raw/*78.97*/("""')">"""),_display_(/*78.102*/lista1/*78.108*/.getBodegaOrigen()),format.raw/*78.126*/("""</a></td>
								<td style="text-align:left;"><a href="#" onclick="buscaBodega('"""),_display_(/*79.73*/lista1/*79.79*/.getBodegaDestino()),format.raw/*79.98*/("""')">"""),_display_(/*79.103*/lista1/*79.109*/.getBodegaDestino()),format.raw/*79.128*/("""</a></td>
								<td style="min-width: 80px; text-align:center;">
									<div style="display: none;">"""),_display_(/*81.39*/utilities/*81.48*/.Fechas.AAMMDD(lista1.getFechaActualizacion())),format.raw/*81.94*/("""</div>
									"""),_display_(/*82.11*/lista1/*82.17*/.getFechaActualizacion()),format.raw/*82.41*/("""
								"""),format.raw/*83.9*/("""</td>
								<td style="min-width: 80px; text-align:center;">
									<div style="display: none;">"""),_display_(/*85.39*/utilities/*85.48*/.Fechas.AAMMDD(lista1.getFechaEnvio())),format.raw/*85.86*/("""</div>
									"""),_display_(/*86.11*/lista1/*86.17*/.getFechaEnvio()),format.raw/*86.33*/("""
								"""),format.raw/*87.9*/("""</td>
								<td  style="text-align:center;">
									"""),_display_(if(lista1.getNumeroCotizacion()>0)/*89.45*/{_display_(Seq[Any](format.raw/*89.46*/("""
										"""),format.raw/*90.11*/("""<form id="form_"""),_display_(/*90.27*/lista1/*90.33*/.getId()),format.raw/*90.41*/("""" method="post" action="/otDespachoModificar/">
											<input type="hidden" name="id_guia" value=""""),_display_(/*91.56*/lista1/*91.62*/.getId()),format.raw/*91.70*/("""">
											<a href="#" onclick='document.getElementById("form_"""),_display_(/*92.64*/lista1/*92.70*/.getId()),format.raw/*92.78*/("""").submit();'>
												<kbd style="background-color: #73C6B6">edit</kbd>
											</a>
										</form>
									""")))}else/*96.15*/{_display_(Seq[Any](format.raw/*96.16*/("""
										"""),format.raw/*97.11*/("""--
									""")))}),format.raw/*98.11*/("""
								"""),format.raw/*99.9*/("""</td>
								"""),_display_(if(lista1.getObservaciones().length()>61)/*100.51*/{_display_(Seq[Any](format.raw/*100.52*/("""
									"""),format.raw/*101.10*/("""<td style="text-align:left;">"""),_display_(/*101.40*/lista1/*101.46*/.getObservaciones().substring(0,60)),format.raw/*101.81*/("""</td>
								""")))}else/*102.14*/{_display_(Seq[Any](format.raw/*102.15*/("""
									"""),format.raw/*103.10*/("""<td style="text-align:left;">"""),_display_(/*103.40*/lista1/*103.46*/.getObservaciones()),format.raw/*103.65*/("""</td>
								""")))}),format.raw/*104.10*/("""
								"""),format.raw/*105.9*/("""<td  style="text-align:center;">
									"""),_display_(if(lista1.getGuiaPDF().equals("0"))/*106.46*/{_display_(Seq[Any](format.raw/*106.47*/("""
										"""),format.raw/*107.11*/("""--
									""")))}else/*108.15*/{_display_(Seq[Any](format.raw/*108.16*/("""
										"""),format.raw/*109.11*/("""<a href="#" onclick="mostrarPDF('"""),_display_(/*109.45*/lista1/*109.51*/.getGuiaPDF()),format.raw/*109.64*/("""')">
											<kbd style="background-color: #85C1E9">pdf</kbd>
										</a>
									""")))}),format.raw/*112.11*/("""
								"""),format.raw/*113.9*/("""</td>
								<td  style="text-align:center;">
									"""),_display_(if(lista1.getDocAnexo().equals("0"))/*115.47*/{_display_(Seq[Any](format.raw/*115.48*/("""
										"""),format.raw/*116.11*/("""--
									""")))}else/*117.15*/{_display_(Seq[Any](format.raw/*117.16*/("""
										"""),format.raw/*118.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*118.52*/lista1/*118.58*/.getDocAnexo()),format.raw/*118.72*/("""')">
											<kbd style="background-color: #7F8C8D">doc</kbd>
										</a>
									""")))}),format.raw/*121.11*/("""
								"""),format.raw/*122.9*/("""</td>
								<td style="text-align:center;">
									"""),_display_(if(lista1.getFotos().equals("0"))/*124.44*/{_display_(Seq[Any](format.raw/*124.45*/("""
										"""),format.raw/*125.11*/("""--
									""")))}else/*126.15*/{_display_(Seq[Any](format.raw/*126.16*/("""
										"""),format.raw/*127.11*/("""<a href="/muestraAlbumFotos/"""),_display_(/*127.40*/lista1/*127.46*/.getFotos()),format.raw/*127.57*/("""">
											<kbd style="background-color: #7F8C8D">Ver</kbd>
										</a>
									""")))}),format.raw/*130.11*/("""
								"""),format.raw/*131.9*/("""</td>
								<td  style="text-align:center;">
									"""),_display_(if(lista1.getNumeroCotizacion()>0)/*133.45*/{_display_(Seq[Any](format.raw/*133.46*/("""
										"""),format.raw/*134.11*/("""<a href="#" onclick="eliminarDespacho('"""),_display_(/*134.51*/lista1/*134.57*/.getNumero()),format.raw/*134.69*/("""', '"""),_display_(/*134.74*/lista1/*134.80*/.getId_ot()),format.raw/*134.91*/("""','"""),_display_(/*134.95*/lista1/*134.101*/.getId()),format.raw/*134.109*/("""');">
											<kbd style="background-color: red">X</kbd>
										</a>
									""")))}else/*137.15*/{_display_(Seq[Any](format.raw/*137.16*/("""
										"""),format.raw/*138.11*/("""--
									""")))}),format.raw/*139.11*/("""
								"""),format.raw/*140.9*/("""</td>
							</TR>
			 			""")))}),format.raw/*142.9*/("""
					"""),format.raw/*143.6*/("""</tbody>
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
					<h5 class="modal-title">"""),_display_(/*160.31*/mapDiccionario/*160.45*/.get("ORDEN_DE_TRABAJO")),format.raw/*160.69*/("""</h5>
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

""")))}),format.raw/*228.2*/("""


"""),format.raw/*231.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*232.31*/("""{"""),format.raw/*232.32*/("""
		  """),format.raw/*233.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*233.36*/("""{"""),format.raw/*233.37*/("""
		    	"""),format.raw/*234.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 7, "desc" ]],
		    	"language": """),format.raw/*237.20*/("""{"""),format.raw/*237.21*/("""
		        	"""),format.raw/*238.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*239.11*/("""}"""),format.raw/*239.12*/("""
		  """),format.raw/*240.5*/("""}"""),format.raw/*240.6*/(""" """),format.raw/*240.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*242.2*/("""}"""),format.raw/*242.3*/(""");
	
	
	const verCotizacion = (id_cotizacion) => """),format.raw/*245.43*/("""{"""),format.raw/*245.44*/("""
		"""),format.raw/*246.3*/("""var formData = new FormData();
	  	formData.append('id_cotizacion',id_cotizacion);
        $.ajax("""),format.raw/*248.16*/("""{"""),format.raw/*248.17*/("""
            """),format.raw/*249.13*/("""url: "/verCotizacionAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*256.36*/("""{"""),format.raw/*256.37*/("""
	     		"""),format.raw/*257.9*/("""document.getElementById('mostrarLaCotizacion').innerHTML = respuesta;
	     		$('#modalVerCotizacion').modal('show');
	     	"""),format.raw/*259.8*/("""}"""),format.raw/*259.9*/("""
        """),format.raw/*260.9*/("""}"""),format.raw/*260.10*/(""");
	"""),format.raw/*261.2*/("""}"""),format.raw/*261.3*/("""
	
	"""),format.raw/*263.2*/("""const verOt = (id_ot) => """),format.raw/*263.27*/("""{"""),format.raw/*263.28*/("""
		"""),format.raw/*264.3*/("""var formData = new FormData();
	  	formData.append('id_ot',id_ot);
        $.ajax("""),format.raw/*266.16*/("""{"""),format.raw/*266.17*/("""
            """),format.raw/*267.13*/("""url: "/verOtAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*274.36*/("""{"""),format.raw/*274.37*/("""
	     		"""),format.raw/*275.9*/("""document.getElementById('mostrarLaOt').innerHTML = respuesta;
	     		$('#modalVerOt').modal('show');
	     	"""),format.raw/*277.8*/("""}"""),format.raw/*277.9*/("""
        """),format.raw/*278.9*/("""}"""),format.raw/*278.10*/(""");
	"""),format.raw/*279.2*/("""}"""),format.raw/*279.3*/("""
	
	"""),format.raw/*281.2*/("""const mostrarPDF = (nombrePDF) => """),format.raw/*281.36*/("""{"""),format.raw/*281.37*/("""
		  """),format.raw/*282.5*/("""document.getElementById('rutaPDF').innerHTML="<object data='/showPdf/"+nombrePDF+"' type='application/pdf' width='100%' height='720'></object>";
		  $('#muestraPDF').modal('show');
	"""),format.raw/*284.2*/("""}"""),format.raw/*284.3*/("""
	
	"""),format.raw/*286.2*/("""const descargaDocumento = (nombreDOC) => """),format.raw/*286.43*/("""{"""),format.raw/*286.44*/("""
		  """),format.raw/*287.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*289.2*/("""}"""),format.raw/*289.3*/("""
	
	"""),format.raw/*291.2*/("""const eliminarDespacho = (numGuia, id_ot, id_guia) => """),format.raw/*291.56*/("""{"""),format.raw/*291.57*/("""
		"""),format.raw/*292.3*/("""alertify.confirm("Esta seguro de eliminar el despacho con movimiento nro: "+numGuia, function (e) """),format.raw/*292.101*/("""{"""),format.raw/*292.102*/("""
			"""),format.raw/*293.4*/("""if (e) """),format.raw/*293.11*/("""{"""),format.raw/*293.12*/("""
				"""),format.raw/*294.5*/("""$("#form_id_guia").val(id_guia);
				$("#form_id_ot").val(id_ot);
				document.getElementById("form_eliminar").submit();
			"""),format.raw/*297.4*/("""}"""),format.raw/*297.5*/("""
		"""),format.raw/*298.3*/("""}"""),format.raw/*298.4*/(""");
	"""),format.raw/*299.2*/("""}"""),format.raw/*299.3*/("""
	
	
"""),format.raw/*302.1*/("""</script>


		
		
	
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
                  SOURCE: app/viewsMnuCotizar/otListaDespachoModificar.scala.html
                  HASH: 1706817534fd925844129519c7beaf64e8731b5e
                  MATRIX: 1041->1|1262->129|1295->137|1311->145|1350->147|1379->151|1447->199|1477->204|1532->239|1563->243|1640->294|1788->420|1818->423|2185->763|2208->777|2239->787|2506->1027|2529->1041|2564->1055|2627->1091|2650->1105|2685->1119|2989->1397|3030->1422|3069->1423|3104->1431|3175->1475|3190->1481|3225->1495|3297->1540|3312->1546|3356->1569|3467->1653|3506->1654|3545->1665|3601->1694|3616->1700|3648->1711|3731->1767|3746->1773|3781->1787|3836->1823|3875->1824|3914->1835|3958->1848|3994->1857|4068->1904|4083->1910|4126->1932|4200->1979|4215->1985|4248->1997|4322->2044|4337->2050|4378->2070|4496->2161|4535->2162|4574->2173|4617->2189|4632->2195|4661->2203|4791->2306|4806->2312|4835->2320|4928->2386|4943->2392|4972->2400|5116->2525|5155->2526|5194->2537|5238->2550|5274->2559|5410->2668|5425->2674|5457->2685|5501->2702|5519->2711|5573->2744|5609->2753|5736->2853|5751->2859|5793->2880|5837->2897|5855->2906|5919->2949|5955->2958|6060->3036|6075->3042|6114->3060|6147->3065|6163->3071|6203->3089|6312->3171|6327->3177|6367->3196|6400->3201|6416->3207|6457->3226|6589->3331|6607->3340|6674->3386|6718->3403|6733->3409|6778->3433|6814->3442|6942->3543|6960->3552|7019->3590|7063->3607|7078->3613|7115->3629|7151->3638|7269->3729|7308->3730|7347->3741|7390->3757|7405->3763|7434->3771|7564->3874|7579->3880|7608->3888|7701->3954|7716->3960|7745->3968|7889->4093|7928->4094|7967->4105|8011->4118|8047->4127|8131->4183|8171->4184|8210->4194|8268->4224|8284->4230|8341->4265|8380->4284|8420->4285|8459->4295|8517->4325|8533->4331|8574->4350|8621->4365|8658->4374|8764->4452|8804->4453|8844->4464|8881->4481|8921->4482|8961->4493|9023->4527|9039->4533|9074->4546|9196->4636|9233->4645|9354->4738|9394->4739|9434->4750|9471->4767|9511->4768|9551->4779|9620->4820|9636->4826|9672->4840|9794->4930|9831->4939|9948->5028|9988->5029|10028->5040|10065->5057|10105->5058|10145->5069|10202->5098|10218->5104|10251->5115|10371->5203|10408->5212|10527->5303|10567->5304|10607->5315|10675->5355|10691->5361|10725->5373|10758->5378|10774->5384|10807->5395|10839->5399|10856->5405|10887->5413|10996->5502|11036->5503|11076->5514|11121->5527|11158->5536|11216->5563|11250->5569|11907->6198|11931->6212|11977->6236|14490->8718|14521->8721|14612->8783|14642->8784|14675->8789|14735->8820|14765->8821|14801->8829|14977->8976|15007->8977|15048->8989|15155->9067|15185->9068|15218->9073|15247->9074|15276->9075|15377->9148|15406->9149|15484->9198|15514->9199|15545->9202|15672->9300|15702->9301|15744->9314|16003->9544|16033->9545|16070->9554|16223->9679|16252->9680|16289->9689|16319->9690|16351->9694|16380->9695|16412->9699|16466->9724|16496->9725|16527->9728|16638->9810|16668->9811|16710->9824|16961->10046|16991->10047|17028->10056|17165->10165|17194->10166|17231->10175|17261->10176|17293->10180|17322->10181|17354->10185|17417->10219|17447->10220|17480->10225|17690->10407|17719->10408|17751->10412|17821->10453|17851->10454|17884->10459|18016->10563|18045->10564|18077->10568|18160->10622|18190->10623|18221->10626|18349->10724|18380->10725|18412->10729|18448->10736|18478->10737|18511->10742|18663->10866|18692->10867|18723->10870|18752->10871|18784->10875|18813->10876|18846->10881
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|42->11|43->12|43->12|44->13|52->21|52->21|52->21|59->28|59->28|59->28|60->29|60->29|60->29|72->41|72->41|72->41|73->42|74->43|74->43|74->43|75->44|75->44|75->44|77->46|77->46|78->47|78->47|78->47|78->47|79->48|79->48|79->48|81->50|81->50|82->51|83->52|84->53|85->54|85->54|85->54|86->55|86->55|86->55|87->56|87->56|87->56|89->58|89->58|90->59|90->59|90->59|90->59|91->60|91->60|91->60|92->61|92->61|92->61|96->65|96->65|97->66|98->67|99->68|102->71|102->71|102->71|103->72|103->72|103->72|104->73|106->75|106->75|106->75|107->76|107->76|107->76|108->77|109->78|109->78|109->78|109->78|109->78|109->78|110->79|110->79|110->79|110->79|110->79|110->79|112->81|112->81|112->81|113->82|113->82|113->82|114->83|116->85|116->85|116->85|117->86|117->86|117->86|118->87|120->89|120->89|121->90|121->90|121->90|121->90|122->91|122->91|122->91|123->92|123->92|123->92|127->96|127->96|128->97|129->98|130->99|131->100|131->100|132->101|132->101|132->101|132->101|133->102|133->102|134->103|134->103|134->103|134->103|135->104|136->105|137->106|137->106|138->107|139->108|139->108|140->109|140->109|140->109|140->109|143->112|144->113|146->115|146->115|147->116|148->117|148->117|149->118|149->118|149->118|149->118|152->121|153->122|155->124|155->124|156->125|157->126|157->126|158->127|158->127|158->127|158->127|161->130|162->131|164->133|164->133|165->134|165->134|165->134|165->134|165->134|165->134|165->134|165->134|165->134|165->134|168->137|168->137|169->138|170->139|171->140|173->142|174->143|191->160|191->160|191->160|259->228|262->231|263->232|263->232|264->233|264->233|264->233|265->234|268->237|268->237|269->238|270->239|270->239|271->240|271->240|271->240|273->242|273->242|276->245|276->245|277->246|279->248|279->248|280->249|287->256|287->256|288->257|290->259|290->259|291->260|291->260|292->261|292->261|294->263|294->263|294->263|295->264|297->266|297->266|298->267|305->274|305->274|306->275|308->277|308->277|309->278|309->278|310->279|310->279|312->281|312->281|312->281|313->282|315->284|315->284|317->286|317->286|317->286|318->287|320->289|320->289|322->291|322->291|322->291|323->292|323->292|323->292|324->293|324->293|324->293|325->294|328->297|328->297|329->298|329->298|330->299|330->299|333->302
                  -- GENERATED --
              */
          