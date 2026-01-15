
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

object otListarDespachosImg extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Guia],play.twirl.api.HtmlFormat.Appendable] {

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
		"""),_display_(/*12.4*/barraTitulo(mapDiccionario, "DESPACHOS EFECTUADOS","(CON IMAGENES)")),format.raw/*12.72*/("""
		"""),format.raw/*13.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">

				
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>TIPO</TH>
							<TH>SUCURSAL</TH>
							<TH>Nro<br>"""),_display_(/*22.20*/mapDiccionario/*22.34*/.get("OT")),format.raw/*22.44*/("""</TH>
							<TH>Nro<br>COTI</TH>
							<TH>Nro<br>MOV</TH>
							<TH>Nro<br>REF</TH>
							<TH>SELECT</TH>
							<TH style="min-width:80px;">FECHA<br>MOV</TH>
							<TH style="min-width:80px;">FECHA<br>INI/TER</TH>
							<TH>DESDE<br>"""),_display_(/*29.22*/mapDiccionario/*29.36*/.get("BODEGA")),format.raw/*29.50*/("""/PROYECTO</TH>
							<TH>HASTA<br>"""),_display_(/*30.22*/mapDiccionario/*30.36*/.get("BODEGA")),format.raw/*30.50*/("""/PROYECTO</TH>
							<TH>SELECT</TH>
							<TH>OBSERVACIONES</TH>
							<TH>GUIA</TH>
							<TH>ANEXO</TH>
							<TH>ALBUM</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*39.8*/for(lista1 <- listaGuias) yield /*39.33*/{_display_(Seq[Any](format.raw/*39.34*/("""
							"""),format.raw/*40.8*/("""<TR>
								<td  style="text-align:left;">"""),_display_(/*41.40*/lista1/*41.46*/.getTipoGuia()),format.raw/*41.60*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*42.40*/lista1/*42.46*/.getNameSucursalDestino),format.raw/*42.69*/("""</td>
								<td  style="text-align:center;">
									"""),_display_(if(lista1.getNumeroOt()!=0)/*44.38*/{_display_(Seq[Any](format.raw/*44.39*/("""
										"""),format.raw/*45.11*/("""<a href="#" onclick="verOt('"""),_display_(/*45.40*/lista1/*45.46*/.getId_ot()),format.raw/*45.57*/("""')">
											<kbd style="background-color: #73C6B6">"""),_display_(/*46.52*/lista1/*46.58*/.getNumeroOt()),format.raw/*46.72*/("""</kbd>
										</a>
									""")))}else/*48.15*/{_display_(Seq[Any](format.raw/*48.16*/("""
										"""),format.raw/*49.11*/("""--
									""")))}),format.raw/*50.11*/("""
								"""),format.raw/*51.9*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*52.42*/lista1/*52.48*/.getNumeroCotizacion()),format.raw/*52.70*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*53.42*/lista1/*53.48*/.getNumero()),format.raw/*53.60*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*54.42*/lista1/*54.48*/.getNumGuiaCliente()),format.raw/*54.68*/("""</td>
								<td  style="text-align:center;">
									"""),_display_(if(lista1.getNumeroCotizacion()>0)/*56.45*/{_display_(Seq[Any](format.raw/*56.46*/("""
										"""),format.raw/*57.11*/("""<form id="form_"""),_display_(/*57.27*/lista1/*57.33*/.getId()),format.raw/*57.41*/("""" method="post" action="/routes2/otDetalleDespachoImg/">
											<input type="hidden" name="id_guia" value=""""),_display_(/*58.56*/lista1/*58.62*/.getId()),format.raw/*58.70*/("""">
											<a href="#" onclick='document.getElementById("form_"""),_display_(/*59.64*/lista1/*59.70*/.getId()),format.raw/*59.78*/("""").submit();'>
												<kbd style="background-color: #73C6B6">select</kbd>
											</a>
										</form>
									""")))}else/*63.15*/{_display_(Seq[Any](format.raw/*63.16*/("""
										"""),format.raw/*64.11*/("""--
									""")))}),format.raw/*65.11*/("""
								"""),format.raw/*66.9*/("""</td>
								<td  style="text-align:center;">
									<div style="display:none">"""),_display_(/*68.37*/lista1/*68.43*/.getFecha()),format.raw/*68.54*/("""</div>
									"""),_display_(/*69.11*/utilities/*69.20*/.Fechas.DDMMAA(lista1.getFecha())),format.raw/*69.53*/("""
								"""),format.raw/*70.9*/("""</td>
								<td  style="min-width: 80px; text-align:center;">
									<div style="display:none">"""),_display_(/*72.37*/lista1/*72.43*/.getFechaIniTerGuia()),format.raw/*72.64*/("""</div>
									"""),_display_(/*73.11*/utilities/*73.20*/.Fechas.DDMMAA(lista1.getFechaIniTerGuia())),format.raw/*73.63*/("""
								"""),format.raw/*74.9*/("""</td>
								<td style="text-align:left;"><a href="#" onclick="buscaBodega('"""),_display_(/*75.73*/lista1/*75.79*/.getBodegaOrigen()),format.raw/*75.97*/("""')">"""),_display_(/*75.102*/lista1/*75.108*/.getBodegaOrigen()),format.raw/*75.126*/("""</a></td>
								<td style="text-align:left;"><a href="#" onclick="buscaBodega('"""),_display_(/*76.73*/lista1/*76.79*/.getBodegaDestino()),format.raw/*76.98*/("""')">"""),_display_(/*76.103*/lista1/*76.109*/.getBodegaDestino()),format.raw/*76.128*/("""</a></td>
								
								<td  style="text-align:center;">
									"""),_display_(if(lista1.getNumeroCotizacion()>0)/*79.45*/{_display_(Seq[Any](format.raw/*79.46*/("""
										"""),format.raw/*80.11*/("""<form id="form_"""),_display_(/*80.27*/lista1/*80.33*/.getId()),format.raw/*80.41*/("""" method="post" action="/routes2/otDetalleDespachoImg/">
											<input type="hidden" name="id_guia" value=""""),_display_(/*81.56*/lista1/*81.62*/.getId()),format.raw/*81.70*/("""">
											<a href="#" onclick='document.getElementById("form_"""),_display_(/*82.64*/lista1/*82.70*/.getId()),format.raw/*82.78*/("""").submit();'>
												<kbd style="background-color: #73C6B6">select</kbd>
											</a>
										</form>
									""")))}else/*86.15*/{_display_(Seq[Any](format.raw/*86.16*/("""
										"""),format.raw/*87.11*/("""--
									""")))}),format.raw/*88.11*/("""
								"""),format.raw/*89.9*/("""</td>
								"""),_display_(if(lista1.getObservaciones().length()>61)/*90.51*/{_display_(Seq[Any](format.raw/*90.52*/("""
									"""),format.raw/*91.10*/("""<td style="text-align:left;">"""),_display_(/*91.40*/lista1/*91.46*/.getObservaciones().substring(0,60)),format.raw/*91.81*/("""</td>
								""")))}else/*92.14*/{_display_(Seq[Any](format.raw/*92.15*/("""
									"""),format.raw/*93.10*/("""<td style="text-align:left;">"""),_display_(/*93.40*/lista1/*93.46*/.getObservaciones()),format.raw/*93.65*/("""</td>
								""")))}),format.raw/*94.10*/("""
								"""),format.raw/*95.9*/("""<td  style="text-align:center;">
									"""),_display_(if(lista1.getGuiaPDF().equals("0"))/*96.46*/{_display_(Seq[Any](format.raw/*96.47*/("""
										"""),format.raw/*97.11*/("""--
									""")))}else/*98.15*/{_display_(Seq[Any](format.raw/*98.16*/("""
										"""),format.raw/*99.11*/("""<a href="#" onclick="mostrarPDF('"""),_display_(/*99.45*/lista1/*99.51*/.getGuiaPDF()),format.raw/*99.64*/("""')">
											<kbd style="background-color: #85C1E9">pdf</kbd>
										</a>
									""")))}),format.raw/*102.11*/("""
								"""),format.raw/*103.9*/("""</td>
								<td  style="text-align:center;">
									"""),_display_(if(lista1.getDocAnexo().equals("0"))/*105.47*/{_display_(Seq[Any](format.raw/*105.48*/("""
										"""),format.raw/*106.11*/("""--
									""")))}else/*107.15*/{_display_(Seq[Any](format.raw/*107.16*/("""
										"""),format.raw/*108.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*108.52*/lista1/*108.58*/.getDocAnexo()),format.raw/*108.72*/("""')">
											<kbd style="background-color: #7F8C8D">doc</kbd>
										</a>
									""")))}),format.raw/*111.11*/("""
								"""),format.raw/*112.9*/("""</td>
								<td style="text-align:center;">
									"""),_display_(if(lista1.getFotos().equals("0"))/*114.44*/{_display_(Seq[Any](format.raw/*114.45*/("""
										"""),format.raw/*115.11*/("""--
									""")))}else/*116.15*/{_display_(Seq[Any](format.raw/*116.16*/("""
										"""),format.raw/*117.11*/("""<a href="/muestraAlbumFotos/"""),_display_(/*117.40*/lista1/*117.46*/.getFotos()),format.raw/*117.57*/("""">
											<kbd style="background-color: #7F8C8D">Ver</kbd>
										</a>
									""")))}),format.raw/*120.11*/("""
								"""),format.raw/*121.9*/("""</td>
							</TR>
			 			""")))}),format.raw/*123.9*/("""
					"""),format.raw/*124.6*/("""</tbody>
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
					<h5 class="modal-title">"""),_display_(/*141.31*/mapDiccionario/*141.45*/.get("ORDEN_DE_TRABAJO")),format.raw/*141.69*/("""</h5>
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

""")))}),format.raw/*209.2*/("""


"""),format.raw/*212.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*213.31*/("""{"""),format.raw/*213.32*/("""
		  """),format.raw/*214.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*214.36*/("""{"""),format.raw/*214.37*/("""
		    	"""),format.raw/*215.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 7, "desc" ]],
		    	"language": """),format.raw/*218.20*/("""{"""),format.raw/*218.21*/("""
		        	"""),format.raw/*219.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*220.11*/("""}"""),format.raw/*220.12*/("""
		  """),format.raw/*221.5*/("""}"""),format.raw/*221.6*/(""" """),format.raw/*221.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*223.2*/("""}"""),format.raw/*223.3*/(""");
	
	
	const verCotizacion = (id_cotizacion) => """),format.raw/*226.43*/("""{"""),format.raw/*226.44*/("""
		"""),format.raw/*227.3*/("""var formData = new FormData();
	  	formData.append('id_cotizacion',id_cotizacion);
        $.ajax("""),format.raw/*229.16*/("""{"""),format.raw/*229.17*/("""
            """),format.raw/*230.13*/("""url: "/verCotizacionAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*237.36*/("""{"""),format.raw/*237.37*/("""
	     		"""),format.raw/*238.9*/("""document.getElementById('mostrarLaCotizacion').innerHTML = respuesta;
	     		$('#modalVerCotizacion').modal('show');
	     	"""),format.raw/*240.8*/("""}"""),format.raw/*240.9*/("""
        """),format.raw/*241.9*/("""}"""),format.raw/*241.10*/(""");
	"""),format.raw/*242.2*/("""}"""),format.raw/*242.3*/("""
	
	"""),format.raw/*244.2*/("""const verOt = (id_ot) => """),format.raw/*244.27*/("""{"""),format.raw/*244.28*/("""
		"""),format.raw/*245.3*/("""var formData = new FormData();
	  	formData.append('id_ot',id_ot);
        $.ajax("""),format.raw/*247.16*/("""{"""),format.raw/*247.17*/("""
            """),format.raw/*248.13*/("""url: "/verOtAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*255.36*/("""{"""),format.raw/*255.37*/("""
	     		"""),format.raw/*256.9*/("""document.getElementById('mostrarLaOt').innerHTML = respuesta;
	     		$('#modalVerOt').modal('show');
	     	"""),format.raw/*258.8*/("""}"""),format.raw/*258.9*/("""
        """),format.raw/*259.9*/("""}"""),format.raw/*259.10*/(""");
	"""),format.raw/*260.2*/("""}"""),format.raw/*260.3*/("""
	
	"""),format.raw/*262.2*/("""const mostrarPDF = (nombrePDF) => """),format.raw/*262.36*/("""{"""),format.raw/*262.37*/("""
		  """),format.raw/*263.5*/("""document.getElementById('rutaPDF').innerHTML="<object data='/showPdf/"+nombrePDF+"' type='application/pdf' width='100%' height='720'></object>";
		  $('#muestraPDF').modal('show');
	"""),format.raw/*265.2*/("""}"""),format.raw/*265.3*/("""
	
	"""),format.raw/*267.2*/("""const descargaDocumento = (nombreDOC) => """),format.raw/*267.43*/("""{"""),format.raw/*267.44*/("""
		  """),format.raw/*268.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*270.2*/("""}"""),format.raw/*270.3*/("""
	
	"""),format.raw/*272.2*/("""const eliminarDespacho = (numGuia, id_ot, id_guia) => """),format.raw/*272.56*/("""{"""),format.raw/*272.57*/("""
		"""),format.raw/*273.3*/("""alertify.confirm("Esta seguro de eliminar el despacho con movimiento nro: "+numGuia, function (e) """),format.raw/*273.101*/("""{"""),format.raw/*273.102*/("""
			"""),format.raw/*274.4*/("""if (e) """),format.raw/*274.11*/("""{"""),format.raw/*274.12*/("""
				"""),format.raw/*275.5*/("""$("#form_id_guia").val(id_guia);
				$("#form_id_ot").val(id_ot);
				document.getElementById("form_eliminar").submit();
			"""),format.raw/*278.4*/("""}"""),format.raw/*278.5*/("""
		"""),format.raw/*279.3*/("""}"""),format.raw/*279.4*/(""");
	"""),format.raw/*280.2*/("""}"""),format.raw/*280.3*/("""
	
	
"""),format.raw/*283.1*/("""</script>


		
		
	
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
                  SOURCE: app/viewsMnuCotizar/otListarDespachosImg.scala.html
                  HASH: cd2bd299f89f687c620bb2bc1c3bfacc37ee64d5
                  MATRIX: 1037->1|1258->129|1291->137|1307->145|1346->147|1375->151|1443->199|1473->204|1528->239|1559->243|1636->294|1725->362|1755->365|2123->706|2146->720|2177->730|2446->972|2469->986|2504->1000|2567->1036|2590->1050|2625->1064|2830->1243|2871->1268|2910->1269|2945->1277|3016->1321|3031->1327|3066->1341|3138->1386|3153->1392|3197->1415|3308->1499|3347->1500|3386->1511|3442->1540|3457->1546|3489->1557|3572->1613|3587->1619|3622->1633|3677->1669|3716->1670|3755->1681|3799->1694|3835->1703|3909->1750|3924->1756|3967->1778|4041->1825|4056->1831|4089->1843|4163->1890|4178->1896|4219->1916|4337->2007|4376->2008|4415->2019|4458->2035|4473->2041|4502->2049|4641->2161|4656->2167|4685->2175|4778->2241|4793->2247|4822->2255|4968->2382|5007->2383|5046->2394|5090->2407|5126->2416|5236->2499|5251->2505|5283->2516|5327->2533|5345->2542|5399->2575|5435->2584|5562->2684|5577->2690|5619->2711|5663->2728|5681->2737|5745->2780|5781->2789|5886->2867|5901->2873|5940->2891|5973->2896|5989->2902|6029->2920|6138->3002|6153->3008|6193->3027|6226->3032|6242->3038|6283->3057|6414->3161|6453->3162|6492->3173|6535->3189|6550->3195|6579->3203|6718->3315|6733->3321|6762->3329|6855->3395|6870->3401|6899->3409|7045->3536|7084->3537|7123->3548|7167->3561|7203->3570|7286->3626|7325->3627|7363->3637|7420->3667|7435->3673|7491->3708|7529->3727|7568->3728|7606->3738|7663->3768|7678->3774|7718->3793|7764->3808|7800->3817|7905->3895|7944->3896|7983->3907|8019->3924|8058->3925|8097->3936|8158->3970|8173->3976|8207->3989|8329->4079|8366->4088|8487->4181|8527->4182|8567->4193|8604->4210|8644->4211|8684->4222|8753->4263|8769->4269|8805->4283|8927->4373|8964->4382|9081->4471|9121->4472|9161->4483|9198->4500|9238->4501|9278->4512|9335->4541|9351->4547|9384->4558|9504->4646|9541->4655|9599->4682|9633->4688|10290->5317|10314->5331|10360->5355|12873->7837|12904->7840|12995->7902|13025->7903|13058->7908|13118->7939|13148->7940|13184->7948|13360->8095|13390->8096|13431->8108|13538->8186|13568->8187|13601->8192|13630->8193|13659->8194|13760->8267|13789->8268|13867->8317|13897->8318|13928->8321|14055->8419|14085->8420|14127->8433|14386->8663|14416->8664|14453->8673|14606->8798|14635->8799|14672->8808|14702->8809|14734->8813|14763->8814|14795->8818|14849->8843|14879->8844|14910->8847|15021->8929|15051->8930|15093->8943|15344->9165|15374->9166|15411->9175|15548->9284|15577->9285|15614->9294|15644->9295|15676->9299|15705->9300|15737->9304|15800->9338|15830->9339|15863->9344|16073->9526|16102->9527|16134->9531|16204->9572|16234->9573|16267->9578|16399->9682|16428->9683|16460->9687|16543->9741|16573->9742|16604->9745|16732->9843|16763->9844|16795->9848|16831->9855|16861->9856|16894->9861|17046->9985|17075->9986|17106->9989|17135->9990|17167->9994|17196->9995|17229->10000
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|42->11|43->12|43->12|44->13|53->22|53->22|53->22|60->29|60->29|60->29|61->30|61->30|61->30|70->39|70->39|70->39|71->40|72->41|72->41|72->41|73->42|73->42|73->42|75->44|75->44|76->45|76->45|76->45|76->45|77->46|77->46|77->46|79->48|79->48|80->49|81->50|82->51|83->52|83->52|83->52|84->53|84->53|84->53|85->54|85->54|85->54|87->56|87->56|88->57|88->57|88->57|88->57|89->58|89->58|89->58|90->59|90->59|90->59|94->63|94->63|95->64|96->65|97->66|99->68|99->68|99->68|100->69|100->69|100->69|101->70|103->72|103->72|103->72|104->73|104->73|104->73|105->74|106->75|106->75|106->75|106->75|106->75|106->75|107->76|107->76|107->76|107->76|107->76|107->76|110->79|110->79|111->80|111->80|111->80|111->80|112->81|112->81|112->81|113->82|113->82|113->82|117->86|117->86|118->87|119->88|120->89|121->90|121->90|122->91|122->91|122->91|122->91|123->92|123->92|124->93|124->93|124->93|124->93|125->94|126->95|127->96|127->96|128->97|129->98|129->98|130->99|130->99|130->99|130->99|133->102|134->103|136->105|136->105|137->106|138->107|138->107|139->108|139->108|139->108|139->108|142->111|143->112|145->114|145->114|146->115|147->116|147->116|148->117|148->117|148->117|148->117|151->120|152->121|154->123|155->124|172->141|172->141|172->141|240->209|243->212|244->213|244->213|245->214|245->214|245->214|246->215|249->218|249->218|250->219|251->220|251->220|252->221|252->221|252->221|254->223|254->223|257->226|257->226|258->227|260->229|260->229|261->230|268->237|268->237|269->238|271->240|271->240|272->241|272->241|273->242|273->242|275->244|275->244|275->244|276->245|278->247|278->247|279->248|286->255|286->255|287->256|289->258|289->258|290->259|290->259|291->260|291->260|293->262|293->262|293->262|294->263|296->265|296->265|298->267|298->267|298->267|299->268|301->270|301->270|303->272|303->272|303->272|304->273|304->273|304->273|305->274|305->274|305->274|306->275|309->278|309->278|310->279|310->279|311->280|311->280|314->283
                  -- GENERATED --
              */
          