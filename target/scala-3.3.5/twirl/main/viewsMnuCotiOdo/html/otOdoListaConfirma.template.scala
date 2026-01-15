
package viewsMnuCotiOdo.html

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

object otOdoListaConfirma extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listOtOdo: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalContactoBodega(mapDiccionario)),format.raw/*9.38*/("""
	"""),_display_(/*10.3*/modalContactoCliente()),format.raw/*10.25*/("""
	"""),_display_(/*11.3*/modalContactoProyecto()),format.raw/*11.26*/("""
	
	"""),format.raw/*13.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*14.4*/barraTitulo(mapDiccionario, "LISTADO DE "+mapDiccionario.get("Ordenes_de_trabajo").toUpperCase()+" ODO - PENDIENTES DE CONFIRMAR","")),format.raw/*14.137*/("""
		"""),format.raw/*15.3*/("""<form method="post" action="/routes2/otOdoConfirma/">
			<input type="hidden" id="cambiosDeEstados" name="cambiosDeEstados">
			<div class="row  justify-content-md-center">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<TR>
								<TH>Nro."""),_display_(/*22.18*/mapDiccionario/*22.32*/.get("OT")),format.raw/*22.42*/("""</TH>
								<TH style="min-width:80px;">FECHA<br>"""),_display_(/*23.47*/mapDiccionario/*23.61*/.get("OT")),format.raw/*23.71*/("""</TH>
								<TH>Nro.COTI</TH>
								<TH style="min-width:80px;">FECHA<br>COTIZACION</TH>
								<TH>"""),_display_(/*26.14*/mapDiccionario/*26.28*/.get("BODEGA")),format.raw/*26.42*/("""/PROYECTO</TH>
								<TH>CLIENTE</TH>
								<TH>PROYECTO</TH>
								<TH>OBSERVACIONES<br>"""),_display_(/*29.31*/mapDiccionario/*29.45*/.get("OT")),format.raw/*29.55*/("""</TH>
								<TH>OBSERVACIONES<br>COTIZACION</TH>
								<TH>DOC<br>"""),_display_(/*31.21*/mapDiccionario/*31.35*/.get("OT")),format.raw/*31.45*/("""</TH>
								<TH>DOC<br>COTI</TH>
								<TH>CONFIRMAR</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*37.9*/for(lista1 <- listOtOdo) yield /*37.33*/{_display_(Seq[Any](format.raw/*37.34*/("""
								"""),format.raw/*38.9*/("""<TR>
									<td style="text-align:center;">
										<a href="#" onclick="verOt('"""),_display_(/*40.40*/lista1/*40.46*/.get(0)),format.raw/*40.53*/("""')">
											<kbd style="background-color: rgb(90, 200, 245)">
												<font color="green">"""),_display_(/*42.34*/lista1/*42.40*/.get(2)),format.raw/*42.47*/("""</font>
											</kbd>
										</a>
									</td>
									<td style="text-align:center;">
										<div style="display:none">"""),_display_(/*47.38*/lista1/*47.44*/.get(3)),format.raw/*47.51*/("""</div>
										"""),_display_(/*48.12*/utilities/*48.21*/.Fechas.DDMMAA(lista1.get(3))),format.raw/*48.50*/("""
									"""),format.raw/*49.10*/("""</td>
									<td style="text-align:center;">
										<a href="#" onclick="verCotizacion('"""),_display_(/*51.48*/lista1/*51.54*/.get(1)),format.raw/*51.61*/("""')">
											<kbd style="background-color: rgb(90, 200, 245)">
												<font color="green">"""),_display_(/*53.34*/lista1/*53.40*/.get(4)),format.raw/*53.47*/("""</font>
											</kbd>
										</a>
									</td>
									<td style="text-align:center;">
										<div style="display:none">"""),_display_(/*58.38*/lista1/*58.44*/.get(5)),format.raw/*58.51*/("""</div>
										"""),_display_(/*59.12*/utilities/*59.21*/.Fechas.DDMMAA(lista1.get(5))),format.raw/*59.50*/("""
									"""),format.raw/*60.10*/("""</td>
									<td style="text-align:left;"><a href="#" onclick="buscaBodega('"""),_display_(/*61.74*/lista1/*61.80*/.get(12)),format.raw/*61.88*/("""')">"""),_display_(/*61.93*/lista1/*61.99*/.get(12)),format.raw/*61.107*/("""</a></td>
									<td style="text-align:left;"><a href="#" onclick="buscaCliente('"""),_display_(/*62.75*/lista1/*62.81*/.get(6)),format.raw/*62.88*/("""')">"""),_display_(/*62.93*/lista1/*62.99*/.get(6)),format.raw/*62.106*/("""</a></td>
									<td style="text-align:left;"><a href="#" onclick="buscaProyecto('"""),_display_(/*63.76*/lista1/*63.82*/.get(7)),format.raw/*63.89*/("""')">"""),_display_(/*63.94*/lista1/*63.100*/.get(7)),format.raw/*63.107*/("""</a></td>
									<td style="text-align:left;">"""),_display_(/*64.40*/lista1/*64.46*/.get(8)),format.raw/*64.53*/("""</td>
									<td style="text-align:left;">"""),_display_(/*65.40*/lista1/*65.46*/.get(9)),format.raw/*65.53*/("""</td>
									<td style="text-align:center;">
										"""),_display_(if(lista1.get(10).equals("0"))/*67.42*/{_display_(Seq[Any](format.raw/*67.43*/("""
											"""),format.raw/*68.12*/("""--
										""")))}else/*69.16*/{_display_(Seq[Any](format.raw/*69.17*/("""
											"""),format.raw/*70.12*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*70.53*/lista1/*70.59*/.get(10)),format.raw/*70.67*/("""')">
												<kbd style="background-color: #7F8C8D">doc</kbd>
											</a>
										""")))}),format.raw/*73.12*/("""
									"""),format.raw/*74.10*/("""</td>
									<td style="text-align:center;">
										"""),_display_(if(lista1.get(11).equals("0"))/*76.42*/{_display_(Seq[Any](format.raw/*76.43*/("""
											"""),format.raw/*77.12*/("""--
										""")))}else/*78.16*/{_display_(Seq[Any](format.raw/*78.17*/("""
											"""),format.raw/*79.12*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*79.53*/lista1/*79.59*/.get(11)),format.raw/*79.67*/("""')">
												<kbd style="background-color: #7F8C8D">doc</kbd>
											</a>
										""")))}),format.raw/*82.12*/("""
									"""),format.raw/*83.10*/("""</td>
									<td  style="text-align:center;">
										<input type="checkbox" id=""""),_display_(/*85.39*/lista1/*85.45*/.get(0)),format.raw/*85.52*/("""" value ="0" onchange="cambiaEstado(id,value)"></td>
									</td>
								</TR>
				 			""")))}),format.raw/*88.10*/("""
						"""),format.raw/*89.7*/("""</tbody>
					</table>
				</div>
			</div>
			<div class="noprint">
				<div class="row justify-content-md-center" >
					<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
						<input type="button"  value="Cancelar" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
					</div>
					<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
						<input type="submit" value='CONFIRMAR' class="btn btn-primary btn-sm rounded-pill btn-block">
					</div>
				</div>
			</div>
		</form>
	</div>
	
	<div id='modalVerOt' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable' style="max-width: 80% !important;" role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>"""),_display_(/*110.31*/mapDiccionario/*110.45*/.get("ORDEN_DE_TRABAJO")),format.raw/*110.69*/("""</h5>
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
	

""")))}),format.raw/*154.2*/("""


"""),format.raw/*157.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*158.31*/("""{"""),format.raw/*158.32*/("""
		  """),format.raw/*159.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*159.36*/("""{"""),format.raw/*159.37*/("""
		    	"""),format.raw/*160.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 1, "desc" ],[ 0, "desc" ]],
		    	"language": """),format.raw/*163.20*/("""{"""),format.raw/*163.21*/("""
		        	"""),format.raw/*164.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*165.11*/("""}"""),format.raw/*165.12*/("""
		  """),format.raw/*166.5*/("""}"""),format.raw/*166.6*/(""" """),format.raw/*166.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*168.2*/("""}"""),format.raw/*168.3*/(""");
	
	
	const verOt = (id_otOdo) => """),format.raw/*171.30*/("""{"""),format.raw/*171.31*/("""
		"""),format.raw/*172.3*/("""var formData = new FormData();
	  	formData.append('id_otOdo',id_otOdo);
        $.ajax("""),format.raw/*174.16*/("""{"""),format.raw/*174.17*/("""
            """),format.raw/*175.13*/("""url: "/routes2/verOtOdoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*182.36*/("""{"""),format.raw/*182.37*/("""
	     		"""),format.raw/*183.9*/("""document.getElementById('mostrarLaOt').innerHTML = respuesta;
	     		$('#modalVerOt').modal('show');
	     	"""),format.raw/*185.8*/("""}"""),format.raw/*185.9*/("""
        """),format.raw/*186.9*/("""}"""),format.raw/*186.10*/(""");
	"""),format.raw/*187.2*/("""}"""),format.raw/*187.3*/("""
	
	"""),format.raw/*189.2*/("""const verCotizacion = (id_cotiOdo) => """),format.raw/*189.40*/("""{"""),format.raw/*189.41*/("""
		"""),format.raw/*190.3*/("""var formData = new FormData();
	  	formData.append('id_cotiOdo',id_cotiOdo);
        $.ajax("""),format.raw/*192.16*/("""{"""),format.raw/*192.17*/("""
            """),format.raw/*193.13*/("""url: "/routes2/verCotiOdoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*200.36*/("""{"""),format.raw/*200.37*/("""
	     		"""),format.raw/*201.9*/("""document.getElementById('mostrarLaCotizacion').innerHTML = respuesta;
	     		$('#modalVerCotizacion').modal('show');
	     	"""),format.raw/*203.8*/("""}"""),format.raw/*203.9*/("""
        """),format.raw/*204.9*/("""}"""),format.raw/*204.10*/(""");
	"""),format.raw/*205.2*/("""}"""),format.raw/*205.3*/("""
	
	"""),format.raw/*207.2*/("""const descargaDocumento = (nombreDOC) => """),format.raw/*207.43*/("""{"""),format.raw/*207.44*/("""
		  """),format.raw/*208.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*210.2*/("""}"""),format.raw/*210.3*/("""
	
	"""),format.raw/*212.2*/("""const cambiaEstado = (id_cotiOdo, valor) => """),format.raw/*212.46*/("""{"""),format.raw/*212.47*/("""
		"""),format.raw/*213.3*/("""if(valor == 0) """),format.raw/*213.18*/("""{"""),format.raw/*213.19*/("""
			"""),format.raw/*214.4*/("""document.getElementById(id_cotiOdo).value = "1";
			let aux = ""+$("#cambiosDeEstados").val()+id_cotiOdo + ","
			$("#cambiosDeEstados").val(aux);
		"""),format.raw/*217.3*/("""}"""),format.raw/*217.4*/("""else"""),format.raw/*217.8*/("""{"""),format.raw/*217.9*/("""
			"""),format.raw/*218.4*/("""document.getElementById(id_cotiOdo).value = "0";
			let aux = ""+id_cotiOdo + ","
			$("#cambiosDeEstados").val($("#cambiosDeEstados").val().replace(aux,""));
		"""),format.raw/*221.3*/("""}"""),format.raw/*221.4*/("""
		
	"""),format.raw/*223.2*/("""}"""),format.raw/*223.3*/("""
		
		
	
	
	
"""),format.raw/*229.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listOtOdo:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listOtOdo)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listOtOdo) => apply(mapDiccionario,mapPermiso,userMnu,listOtOdo)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotiOdo/otOdoListaConfirma.scala.html
                  HASH: e000ecb642e3d620f21317729ea767d1c11e7bd7
                  MATRIX: 1036->1|1257->129|1290->137|1306->145|1345->147|1374->151|1442->199|1472->204|1527->239|1556->242|1599->264|1628->267|1672->290|1703->294|1780->345|1935->478|1965->481|2411->900|2434->914|2465->924|2544->976|2567->990|2598->1000|2731->1106|2754->1120|2789->1134|2912->1230|2935->1244|2966->1254|3064->1325|3087->1339|3118->1349|3256->1461|3296->1485|3335->1486|3371->1495|3483->1580|3498->1586|3526->1593|3652->1692|3667->1698|3695->1705|3856->1839|3871->1845|3899->1852|3944->1870|3962->1879|4012->1908|4050->1918|4171->2012|4186->2018|4214->2025|4340->2124|4355->2130|4383->2137|4544->2271|4559->2277|4587->2284|4632->2302|4650->2311|4700->2340|4738->2350|4844->2429|4859->2435|4888->2443|4920->2448|4935->2454|4965->2462|5076->2546|5091->2552|5119->2559|5151->2564|5166->2570|5195->2577|5307->2662|5322->2668|5350->2675|5382->2680|5398->2686|5427->2693|5503->2742|5518->2748|5546->2755|5618->2800|5633->2806|5661->2813|5776->2901|5815->2902|5855->2914|5892->2932|5931->2933|5971->2945|6039->2986|6054->2992|6083->3000|6207->3093|6245->3103|6360->3191|6399->3192|6439->3204|6476->3222|6515->3223|6555->3235|6623->3276|6638->3282|6667->3290|6791->3383|6829->3393|6942->3479|6957->3485|6985->3492|7107->3583|7141->3590|7986->4407|8010->4421|8056->4445|9607->5965|9638->5968|9729->6030|9759->6031|9792->6036|9852->6067|9882->6068|9918->6076|10108->6237|10138->6238|10179->6250|10286->6328|10316->6329|10349->6334|10378->6335|10407->6336|10508->6409|10537->6410|10602->6446|10632->6447|10663->6450|10780->6538|10810->6539|10852->6552|11114->6785|11144->6786|11181->6795|11318->6904|11347->6905|11384->6914|11414->6915|11446->6919|11475->6920|11507->6924|11574->6962|11604->6963|11635->6966|11756->7058|11786->7059|11828->7072|12092->7307|12122->7308|12159->7317|12312->7442|12341->7443|12378->7452|12408->7453|12440->7457|12469->7458|12501->7462|12571->7503|12601->7504|12634->7509|12766->7613|12795->7614|12827->7618|12900->7662|12930->7663|12961->7666|13005->7681|13035->7682|13067->7686|13244->7835|13273->7836|13305->7840|13334->7841|13366->7845|13555->8006|13584->8007|13617->8012|13646->8013|13687->8026
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|41->10|41->10|42->11|42->11|44->13|45->14|45->14|46->15|53->22|53->22|53->22|54->23|54->23|54->23|57->26|57->26|57->26|60->29|60->29|60->29|62->31|62->31|62->31|68->37|68->37|68->37|69->38|71->40|71->40|71->40|73->42|73->42|73->42|78->47|78->47|78->47|79->48|79->48|79->48|80->49|82->51|82->51|82->51|84->53|84->53|84->53|89->58|89->58|89->58|90->59|90->59|90->59|91->60|92->61|92->61|92->61|92->61|92->61|92->61|93->62|93->62|93->62|93->62|93->62|93->62|94->63|94->63|94->63|94->63|94->63|94->63|95->64|95->64|95->64|96->65|96->65|96->65|98->67|98->67|99->68|100->69|100->69|101->70|101->70|101->70|101->70|104->73|105->74|107->76|107->76|108->77|109->78|109->78|110->79|110->79|110->79|110->79|113->82|114->83|116->85|116->85|116->85|119->88|120->89|141->110|141->110|141->110|185->154|188->157|189->158|189->158|190->159|190->159|190->159|191->160|194->163|194->163|195->164|196->165|196->165|197->166|197->166|197->166|199->168|199->168|202->171|202->171|203->172|205->174|205->174|206->175|213->182|213->182|214->183|216->185|216->185|217->186|217->186|218->187|218->187|220->189|220->189|220->189|221->190|223->192|223->192|224->193|231->200|231->200|232->201|234->203|234->203|235->204|235->204|236->205|236->205|238->207|238->207|238->207|239->208|241->210|241->210|243->212|243->212|243->212|244->213|244->213|244->213|245->214|248->217|248->217|248->217|248->217|249->218|252->221|252->221|254->223|254->223|260->229
                  -- GENERATED --
              */
          