
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

object otListaRevisar extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listOt: List[List[String]], fechaDesde: String, fechaHasta: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalContactoBodega(mapDiccionario)),format.raw/*9.38*/("""
	"""),_display_(/*10.3*/modalContactoCliente()),format.raw/*10.25*/("""
	"""),_display_(/*11.3*/modalContactoProyecto()),format.raw/*11.26*/("""
	
	"""),format.raw/*13.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*14.4*/barraTitulo(mapDiccionario, "REVISAR "+mapDiccionario.get("Ordenes_de_trabajo").toUpperCase(),"(confirmadas y pendientes de confirmar)")),format.raw/*14.140*/("""
		"""),format.raw/*15.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			
			
			
				<div class="noprint">
					<table class="table table-sm table-condensed table-fluid">
						<tr>
							<td>
								<div align="center">
									<button type="button"  class="btn btn-sm btn-success" onclick="document.getElementById('excel').submit()">
										Exportar a Excel
									</button>
									<button type="button" class="btn btn-sm btn-success" tabindex="-1" onclick="window.print(); return false;" >
										Imprimir
									</button>
									<button type="button"  class="btn btn-sm btn-success" 
										onclick="history.go(-1);return false;">
										Volver
									</button>
								</div>
							</td>
						</tr>
					</table>
				</div>
				
				<form id="excel" class="formulario" method="post" action="/routes2/otListaRevisarExcel/">
					<input type="hidden" name="fechaDesde" value=""""),_display_(/*42.53*/fechaDesde),format.raw/*42.63*/("""">
					<input type="hidden" name="fechaHasta" value=""""),_display_(/*43.53*/fechaHasta),format.raw/*43.63*/("""">
				</form>
				
				
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>SUCURSAL</TH>
							<TH>COMERCIAL</TH>
							<TH>Nro."""),_display_(/*52.17*/mapDiccionario/*52.31*/.get("OT")),format.raw/*52.41*/("""</TH>
							<TH style="min-width:80px;">FECHA<br>"""),_display_(/*53.46*/mapDiccionario/*53.60*/.get("OT")),format.raw/*53.70*/("""</TH>
							<TH style="min-width:80px;">CONFIRMADA<br>"""),_display_(/*54.51*/mapDiccionario/*54.65*/.get("OT")),format.raw/*54.75*/("""</TH>
							<TH>Nro.COTI</TH>
							<TH>SELECT</TH>
							<TH style="min-width:80px;">FECHA<br>COTIZACION</TH>
							<TH>"""),_display_(/*58.13*/mapDiccionario/*58.27*/.get("BODEGA")),format.raw/*58.41*/("""/PROYECTO</TH>
							<TH>CLIENTE</TH>
							<TH>PROYECTO</TH>
							<TH>OBSERVACIONES<br>"""),_display_(/*61.30*/mapDiccionario/*61.44*/.get("OT")),format.raw/*61.54*/("""</TH>
							<TH>OBSERVACIONES<br>COTIZACION</TH>
							<TH>DOC<br>"""),_display_(/*63.20*/mapDiccionario/*63.34*/.get("OT")),format.raw/*63.44*/("""</TH>
							<TH>DOC<br>COTI</TH>
							<TH>ULTIMA<br>ACTUALIZACION</TH>
							<TH>ESTIMACION<br>DE ENTREGA</TH>
							<TH>SALDO</TH>
							<TH>ESTADO</TH>
							<TH>SELECT</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*73.8*/for(lista1 <- listOt) yield /*73.29*/{_display_(Seq[Any](format.raw/*73.30*/("""
							"""),format.raw/*74.8*/("""<TR>
								<td style="text-align:left;">"""),_display_(/*75.39*/lista1/*75.45*/.get(15)),format.raw/*75.53*/("""</td>
								<td style="text-align:left;">"""),_display_(/*76.39*/lista1/*76.45*/.get(16)),format.raw/*76.53*/("""</td>
								<td style="text-align:center;">
									<a href="#" onclick="verOt('"""),_display_(/*78.39*/lista1/*78.45*/.get(0)),format.raw/*78.52*/("""')">
										<kbd style="background-color: rgb(90, 200, 245)">
											<font color="green">"""),_display_(/*80.33*/lista1/*80.39*/.get(2)),format.raw/*80.46*/("""</font>
										</kbd>
									</a>
								</td>
								<td  style="min-width: 80px; text-align:center;">
									<div style="display:none">"""),_display_(/*85.37*/lista1/*85.43*/.get(3)),format.raw/*85.50*/("""</div>
									"""),_display_(/*86.11*/utilities/*86.20*/.Fechas.DDMMAA(lista1.get(3))),format.raw/*86.49*/("""
								"""),format.raw/*87.9*/("""</td>
								<td style="min-width: 80px; text-align:center;">
									<div style="display:none">"""),_display_(/*89.37*/lista1/*89.43*/.get(19)),format.raw/*89.51*/("""</div>
									"""),_display_(/*90.11*/utilities/*90.20*/.Fechas.DDMMAA(lista1.get(19))),format.raw/*90.50*/("""
								"""),format.raw/*91.9*/("""</td>
								<td style="text-align:center;">
									<a href="#" onclick="verCotizacion('"""),_display_(/*93.47*/lista1/*93.53*/.get(1)),format.raw/*93.60*/("""')">
										<kbd style="background-color: rgb(90, 200, 245)">
											<font color="green">"""),_display_(/*95.33*/lista1/*95.39*/.get(4)),format.raw/*95.46*/("""</font>
										</kbd>
									</a>
								</td>
								<td  style="text-align:center;">
									<a href="#" onclick= "revisarOt('"""),_display_(/*100.44*/lista1/*100.50*/.get(0)),format.raw/*100.57*/("""')">
										<kbd style="background-color: #73C6B6">select</kbd>
									</a>
								</td>
								<td  style="min-width: 80px; text-align:center;">
									<div style="display:none">"""),_display_(/*105.37*/lista1/*105.43*/.get(5)),format.raw/*105.50*/("""</div>
									"""),_display_(/*106.11*/utilities/*106.20*/.Fechas.DDMMAA(lista1.get(5))),format.raw/*106.49*/("""
								"""),format.raw/*107.9*/("""</td>
								<td style="text-align:left;"><a href="#" onclick="buscaBodega('"""),_display_(/*108.73*/lista1/*108.79*/.get(14)),format.raw/*108.87*/("""')">"""),_display_(/*108.92*/lista1/*108.98*/.get(14)),format.raw/*108.106*/("""</a></td>
								<td style="text-align:left;"><a href="#" onclick="buscaCliente('"""),_display_(/*109.74*/lista1/*109.80*/.get(6)),format.raw/*109.87*/("""')">"""),_display_(/*109.92*/lista1/*109.98*/.get(6)),format.raw/*109.105*/("""</a></td>
								<td style="text-align:left;"><a href="#" onclick="buscaProyecto('"""),_display_(/*110.75*/lista1/*110.81*/.get(7)),format.raw/*110.88*/("""')">"""),_display_(/*110.93*/lista1/*110.99*/.get(7)),format.raw/*110.106*/("""</a></td>
								<td style="text-align:left;">"""),_display_(/*111.39*/lista1/*111.45*/.get(8)),format.raw/*111.52*/("""</td>
								<td style="text-align:left;">"""),_display_(/*112.39*/lista1/*112.45*/.get(9)),format.raw/*112.52*/("""</td>
								<td style="text-align:center;">
									"""),_display_(if(lista1.get(10).equals("0"))/*114.41*/{_display_(Seq[Any](format.raw/*114.42*/("""
										"""),format.raw/*115.11*/("""--
									""")))}else/*116.15*/{_display_(Seq[Any](format.raw/*116.16*/("""
										"""),format.raw/*117.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*117.52*/lista1/*117.58*/.get(10)),format.raw/*117.66*/("""')">
											<kbd style="background-color: #7F8C8D">doc</kbd>
										</a>
									""")))}),format.raw/*120.11*/("""
								"""),format.raw/*121.9*/("""</td>
								<td style="text-align:center;">
									"""),_display_(if(lista1.get(11).equals("0"))/*123.41*/{_display_(Seq[Any](format.raw/*123.42*/("""
										"""),format.raw/*124.11*/("""--
									""")))}else/*125.15*/{_display_(Seq[Any](format.raw/*125.16*/("""
										"""),format.raw/*126.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*126.52*/lista1/*126.58*/.get(11)),format.raw/*126.66*/("""')">
											<kbd style="background-color: #7F8C8D">doc</kbd>
										</a>
									""")))}),format.raw/*129.11*/("""
								"""),format.raw/*130.9*/("""</td>
								<td style="min-width: 80px; text-align:center;">
									<div style="display: none;">"""),_display_(/*132.39*/utilities/*132.48*/.Fechas.AAMMDD(lista1.get(17))),format.raw/*132.78*/("""</div>
									<div id="fechaActual_"""),_display_(/*133.32*/lista1/*133.38*/.get(0)),format.raw/*133.45*/("""">"""),_display_(/*133.48*/lista1/*133.54*/.get(17)),format.raw/*133.62*/("""</div>
								</td>
								<td style="min-width: 80px; text-align:center;">
									<div style="display: none;">"""),_display_(/*136.39*/utilities/*136.48*/.Fechas.AAMMDD(lista1.get(18))),format.raw/*136.78*/("""</div>
									<div id="fechaActual_"""),_display_(/*137.32*/lista1/*137.38*/.get(0)),format.raw/*137.45*/("""">"""),_display_(/*137.48*/lista1/*137.54*/.get(18)),format.raw/*137.62*/("""</div>
								</td>
								<td style="text-align:center;">"""),_display_(/*139.41*/lista1/*139.47*/.get(20)),format.raw/*139.55*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*140.42*/lista1/*140.48*/.get(12)),format.raw/*140.56*/("""</td>
								<td  style="text-align:center;">
									<a href="#" onclick= "revisarOt('"""),_display_(/*142.44*/lista1/*142.50*/.get(0)),format.raw/*142.57*/("""')">
										<kbd style="background-color: #73C6B6">select</kbd>
									</a>
								</td>
							</TR>
			 			""")))}),format.raw/*147.9*/("""
					"""),format.raw/*148.6*/("""</tbody>
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
					<h5 class='modal-title'>"""),_display_(/*165.31*/mapDiccionario/*165.45*/.get("ORDEN_DE_TRABAJO")),format.raw/*165.69*/("""</h5>
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
	
	
	<form class="formulario" id="formDescargaDocumento" method="post" action="/downloadPDF/">	
		<input type="hidden" id="descargaDocumento" name="nombreArchivo">
	</form>
	
	<form id="revisarOt" method="post" action="/routes2/revisarOt/">
		<input type="hidden" id="revisarOt_idOt" name="id_ot">
	</form>

""")))}),format.raw/*212.2*/("""


"""),format.raw/*215.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*216.31*/("""{"""),format.raw/*216.32*/("""
		  """),format.raw/*217.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*217.36*/("""{"""),format.raw/*217.37*/("""
		    	"""),format.raw/*218.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 3, "desc" ]],
		    	"language": """),format.raw/*221.20*/("""{"""),format.raw/*221.21*/("""
		        	"""),format.raw/*222.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*223.11*/("""}"""),format.raw/*223.12*/("""
		  """),format.raw/*224.5*/("""}"""),format.raw/*224.6*/(""" """),format.raw/*224.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*226.2*/("""}"""),format.raw/*226.3*/(""");
	
	
	const verOt = (id_ot) => """),format.raw/*229.27*/("""{"""),format.raw/*229.28*/("""
		"""),format.raw/*230.3*/("""var formData = new FormData();
	  	formData.append('id_ot',id_ot);
        $.ajax("""),format.raw/*232.16*/("""{"""),format.raw/*232.17*/("""
            """),format.raw/*233.13*/("""url: "/verOtAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*240.36*/("""{"""),format.raw/*240.37*/("""
	     		"""),format.raw/*241.9*/("""document.getElementById('mostrarLaOt').innerHTML = respuesta;
	     		$('#modalVerOt').modal('show');
	     	"""),format.raw/*243.8*/("""}"""),format.raw/*243.9*/("""
        """),format.raw/*244.9*/("""}"""),format.raw/*244.10*/(""");
	"""),format.raw/*245.2*/("""}"""),format.raw/*245.3*/("""
	
	"""),format.raw/*247.2*/("""const verCotizacion = (id_cotizacion) => """),format.raw/*247.43*/("""{"""),format.raw/*247.44*/("""
		"""),format.raw/*248.3*/("""var formData = new FormData();
	  	formData.append('id_cotizacion',id_cotizacion);
        $.ajax("""),format.raw/*250.16*/("""{"""),format.raw/*250.17*/("""
            """),format.raw/*251.13*/("""url: "/verCotizacionAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*258.36*/("""{"""),format.raw/*258.37*/("""
	     		"""),format.raw/*259.9*/("""document.getElementById('mostrarLaCotizacion').innerHTML = respuesta;
	     		$('#modalVerCotizacion').modal('show');
	     	"""),format.raw/*261.8*/("""}"""),format.raw/*261.9*/("""
        """),format.raw/*262.9*/("""}"""),format.raw/*262.10*/(""");
	"""),format.raw/*263.2*/("""}"""),format.raw/*263.3*/("""
	
	"""),format.raw/*265.2*/("""const descargaDocumento = (nombreDOC) => """),format.raw/*265.43*/("""{"""),format.raw/*265.44*/("""
		  """),format.raw/*266.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*268.2*/("""}"""),format.raw/*268.3*/("""
	
	"""),format.raw/*270.2*/("""const revisarOt = (id_ot) => """),format.raw/*270.31*/("""{"""),format.raw/*270.32*/("""
			"""),format.raw/*271.4*/("""$("#revisarOt_idOt").val(id_ot);
			document.getElementById("revisarOt").submit();
	"""),format.raw/*273.2*/("""}"""),format.raw/*273.3*/("""
		
		
	
	
	
"""),format.raw/*279.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listOt:List[List[String]],fechaDesde:String,fechaHasta:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listOt,fechaDesde,fechaHasta)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listOt,fechaDesde,fechaHasta) => apply(mapDiccionario,mapPermiso,userMnu,listOt,fechaDesde,fechaHasta)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizar/otListaRevisar.scala.html
                  HASH: 1366952ca4c42c66c84310a18f3288b52a2b79cf
                  MATRIX: 1046->1|1304->166|1337->174|1353->182|1392->184|1421->188|1489->236|1519->241|1574->276|1603->279|1646->301|1675->304|1719->327|1750->331|1827->382|1985->518|2015->521|2982->1461|3013->1471|3095->1526|3126->1536|3413->1796|3436->1810|3467->1820|3545->1871|3568->1885|3599->1895|3682->1951|3705->1965|3736->1975|3889->2101|3912->2115|3947->2129|4067->2222|4090->2236|4121->2246|4217->2315|4240->2329|4271->2339|4526->2568|4563->2589|4602->2590|4637->2598|4707->2641|4722->2647|4751->2655|4822->2699|4837->2705|4866->2713|4977->2797|4992->2803|5020->2810|5144->2907|5159->2913|5187->2920|5361->3067|5376->3073|5404->3080|5448->3097|5466->3106|5516->3135|5552->3144|5678->3243|5693->3249|5722->3257|5766->3274|5784->3283|5835->3313|5871->3322|5990->3414|6005->3420|6033->3427|6157->3524|6172->3530|6200->3537|6365->3674|6381->3680|6410->3687|6627->3876|6643->3882|6672->3889|6717->3906|6736->3915|6787->3944|6824->3953|6930->4031|6946->4037|6976->4045|7009->4050|7025->4056|7056->4064|7167->4147|7183->4153|7212->4160|7245->4165|7261->4171|7291->4178|7403->4262|7419->4268|7448->4275|7481->4280|7497->4286|7527->4293|7603->4341|7619->4347|7648->4354|7720->4398|7736->4404|7765->4411|7879->4497|7919->4498|7959->4509|7996->4526|8036->4527|8076->4538|8145->4579|8161->4585|8191->4593|8313->4683|8350->4692|8464->4778|8504->4779|8544->4790|8581->4807|8621->4808|8661->4819|8730->4860|8746->4866|8776->4874|8898->4964|8935->4973|9064->5074|9083->5083|9135->5113|9201->5151|9217->5157|9246->5164|9277->5167|9293->5173|9323->5181|9467->5297|9486->5306|9538->5336|9604->5374|9620->5380|9649->5387|9680->5390|9696->5396|9726->5404|9815->5465|9831->5471|9861->5479|9936->5526|9952->5532|9982->5540|10100->5630|10116->5636|10145->5643|10292->5759|10326->5765|10983->6394|11007->6408|11053->6432|12736->8084|12767->8087|12858->8149|12888->8150|12921->8155|12981->8186|13011->8187|13047->8195|13223->8342|13253->8343|13294->8355|13401->8433|13431->8434|13464->8439|13493->8440|13522->8441|13623->8514|13652->8515|13714->8548|13744->8549|13775->8552|13886->8634|13916->8635|13958->8648|14209->8870|14239->8871|14276->8880|14413->8989|14442->8990|14479->8999|14509->9000|14541->9004|14570->9005|14602->9009|14672->9050|14702->9051|14733->9054|14860->9152|14890->9153|14932->9166|15191->9396|15221->9397|15258->9406|15411->9531|15440->9532|15477->9541|15507->9542|15539->9546|15568->9547|15600->9551|15670->9592|15700->9593|15733->9598|15865->9702|15894->9703|15926->9707|15984->9736|16014->9737|16046->9741|16158->9825|16187->9826|16228->9839
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|41->10|41->10|42->11|42->11|44->13|45->14|45->14|46->15|73->42|73->42|74->43|74->43|83->52|83->52|83->52|84->53|84->53|84->53|85->54|85->54|85->54|89->58|89->58|89->58|92->61|92->61|92->61|94->63|94->63|94->63|104->73|104->73|104->73|105->74|106->75|106->75|106->75|107->76|107->76|107->76|109->78|109->78|109->78|111->80|111->80|111->80|116->85|116->85|116->85|117->86|117->86|117->86|118->87|120->89|120->89|120->89|121->90|121->90|121->90|122->91|124->93|124->93|124->93|126->95|126->95|126->95|131->100|131->100|131->100|136->105|136->105|136->105|137->106|137->106|137->106|138->107|139->108|139->108|139->108|139->108|139->108|139->108|140->109|140->109|140->109|140->109|140->109|140->109|141->110|141->110|141->110|141->110|141->110|141->110|142->111|142->111|142->111|143->112|143->112|143->112|145->114|145->114|146->115|147->116|147->116|148->117|148->117|148->117|148->117|151->120|152->121|154->123|154->123|155->124|156->125|156->125|157->126|157->126|157->126|157->126|160->129|161->130|163->132|163->132|163->132|164->133|164->133|164->133|164->133|164->133|164->133|167->136|167->136|167->136|168->137|168->137|168->137|168->137|168->137|168->137|170->139|170->139|170->139|171->140|171->140|171->140|173->142|173->142|173->142|178->147|179->148|196->165|196->165|196->165|243->212|246->215|247->216|247->216|248->217|248->217|248->217|249->218|252->221|252->221|253->222|254->223|254->223|255->224|255->224|255->224|257->226|257->226|260->229|260->229|261->230|263->232|263->232|264->233|271->240|271->240|272->241|274->243|274->243|275->244|275->244|276->245|276->245|278->247|278->247|278->247|279->248|281->250|281->250|282->251|289->258|289->258|290->259|292->261|292->261|293->262|293->262|294->263|294->263|296->265|296->265|296->265|297->266|299->268|299->268|301->270|301->270|301->270|302->271|304->273|304->273|310->279
                  -- GENERATED --
              */
          