
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

object otListaDespachar extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listOt: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalContactoBodega(mapDiccionario)),format.raw/*9.38*/("""
	"""),_display_(/*10.3*/modalContactoCliente()),format.raw/*10.25*/("""
	"""),_display_(/*11.3*/modalContactoProyecto()),format.raw/*11.26*/("""
	
	
	
	"""),format.raw/*15.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*16.4*/barraTitulo(mapDiccionario, mapDiccionario.get("Ordenes_de_trabajo").toUpperCase(),"SELECCIONAR PARA HACER DESPACHO")),format.raw/*16.121*/("""
		
		"""),format.raw/*18.3*/("""<div class="noprint" align="center">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<form method="post" action="/routes2/otCambiarEstadoTerminadas/">
						<input id="btnCargaExcel" type="submit"  value="Pasar a terminadas las """),_display_(/*22.79*/mapDiccionario/*22.93*/.get("OT")),format.raw/*22.103*/(""" """),format.raw/*22.104*/("""completadas" class="btn btn-info btn-sm rounded-pill btn-block">
					</form>
				</div>
			</div>
		</div>
	
		<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>SUCURSAL</TH>
							<TH>Nro."""),_display_(/*34.17*/mapDiccionario/*34.31*/.get("OT")),format.raw/*34.41*/("""</TH>
							<TH style="min-width:80px;">FECHA<br>"""),_display_(/*35.46*/mapDiccionario/*35.60*/.get("OT")),format.raw/*35.70*/("""</TH>
							<TH>Nro.COTI</TH>
							<TH style="min-width:80px;">FECHA<br>COTIZACION</TH>
							<TH>OBSERVACIONES COTI</TH>
							<TH>"""),_display_(/*39.13*/mapDiccionario/*39.27*/.get("BODEGA")),format.raw/*39.41*/("""/PROYECTO</TH>
							<TH>CLIENTE</TH>
							<TH>PROYECTO</TH>
							<TH>ULTIMA<br>ACTUALIZACION</TH>
							<TH>ESTIMACION<br>DE ENTREGA</TH>
							<TH>SELECT</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*48.8*/for(lista1 <- listOt) yield /*48.29*/{_display_(Seq[Any](format.raw/*48.30*/("""
							"""),format.raw/*49.8*/("""<TR>
								<td style="text-align:left;">"""),_display_(/*50.39*/lista1/*50.45*/.get(15)),format.raw/*50.53*/("""</td>
								<td style="text-align:center;">
									<a href="#" onclick="verOt('"""),_display_(/*52.39*/lista1/*52.45*/.get(0)),format.raw/*52.52*/("""')">
										<kbd style="background-color: rgb(90, 200, 245)">
											<font color="green">"""),_display_(/*54.33*/lista1/*54.39*/.get(2)),format.raw/*54.46*/("""</font>
										</kbd>
									</a>
								</td>
								<td style="min-width: 80px; text-align:center;">
									<div style="display:none">"""),_display_(/*59.37*/lista1/*59.43*/.get(3)),format.raw/*59.50*/("""</div>
									"""),_display_(/*60.11*/utilities/*60.20*/.Fechas.DDMMAA(lista1.get(3))),format.raw/*60.49*/("""
								"""),format.raw/*61.9*/("""</td>
								<td style="text-align:center;">"""),_display_(/*62.41*/lista1/*62.47*/.get(4)),format.raw/*62.54*/("""</td>
								<td style="min-width: 80px; text-align:center;">
									<div style="display:none">"""),_display_(/*64.37*/lista1/*64.43*/.get(5)),format.raw/*64.50*/("""</div>
									"""),_display_(/*65.11*/utilities/*65.20*/.Fechas.DDMMAA(lista1.get(5))),format.raw/*65.49*/("""
								"""),format.raw/*66.9*/("""</td>
								<td style="text-align:left;">"""),_display_(/*67.39*/lista1/*67.45*/.get(9)),format.raw/*67.52*/("""</td>
								<td style="text-align:left;"><a href="#" onclick="buscaBodega('"""),_display_(/*68.73*/lista1/*68.79*/.get(14)),format.raw/*68.87*/("""')">"""),_display_(/*68.92*/lista1/*68.98*/.get(14)),format.raw/*68.106*/("""</a></td>
								<td style="text-align:left;"><a href="#" onclick="buscaCliente('"""),_display_(/*69.74*/lista1/*69.80*/.get(6)),format.raw/*69.87*/("""')">"""),_display_(/*69.92*/lista1/*69.98*/.get(6)),format.raw/*69.105*/("""</a></td>
								<td style="text-align:left;"><a href="#" onclick="buscaProyecto('"""),_display_(/*70.75*/lista1/*70.81*/.get(7)),format.raw/*70.88*/("""')">"""),_display_(/*70.93*/lista1/*70.99*/.get(7)),format.raw/*70.106*/("""</a></td>
								<td style="min-width: 80px; text-align:center;">
									<div style="display: none;">"""),_display_(/*72.39*/utilities/*72.48*/.Fechas.AAMMDD(lista1.get(17))),format.raw/*72.78*/("""</div>
									<div id="fechaActual_"""),_display_(/*73.32*/lista1/*73.38*/.get(0)),format.raw/*73.45*/("""">"""),_display_(/*73.48*/lista1/*73.54*/.get(17)),format.raw/*73.62*/("""</div>
								</td>
								<td style="min-width: 80px; text-align:center;">
									<div style="display: none;">"""),_display_(/*76.39*/utilities/*76.48*/.Fechas.AAMMDD(lista1.get(18))),format.raw/*76.78*/("""</div>
									<div id="fechaActual_"""),_display_(/*77.32*/lista1/*77.38*/.get(0)),format.raw/*77.45*/("""">"""),_display_(/*77.48*/lista1/*77.54*/.get(18)),format.raw/*77.62*/("""</div>
								</td>
								<td  style="text-align:center;">
									<a href="#" onclick= "selectOt('"""),_display_(/*80.43*/lista1/*80.49*/.get(0)),format.raw/*80.56*/("""')">
										<kbd style="background-color: #73C6B6">select</kbd>
									</a>
								</td>
							</TR>
			 			""")))}),format.raw/*85.9*/("""
					"""),format.raw/*86.6*/("""</tbody>
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
					<h5 class='modal-title'>"""),_display_(/*103.31*/mapDiccionario/*103.45*/.get("ORDEN_DE_TRABAJO")),format.raw/*103.69*/("""</h5>
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
	
	<form id="formDespacharOt" method="post" action="/otDespachar/">
		<input type="hidden" id="form_id_ot" name="id_ot">
	</form>

""")))}),format.raw/*145.2*/("""


"""),format.raw/*148.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*149.31*/("""{"""),format.raw/*149.32*/("""
		  """),format.raw/*150.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*150.36*/("""{"""),format.raw/*150.37*/("""
		    	"""),format.raw/*151.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 2, "desc" ]],
		    	"language": """),format.raw/*154.20*/("""{"""),format.raw/*154.21*/("""
		        	"""),format.raw/*155.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*156.11*/("""}"""),format.raw/*156.12*/("""
		  """),format.raw/*157.5*/("""}"""),format.raw/*157.6*/(""" """),format.raw/*157.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*159.2*/("""}"""),format.raw/*159.3*/(""");
	
	
	const verOt = (id_ot) => """),format.raw/*162.27*/("""{"""),format.raw/*162.28*/("""
		"""),format.raw/*163.3*/("""var formData = new FormData();
	  	formData.append('id_ot',id_ot);
        $.ajax("""),format.raw/*165.16*/("""{"""),format.raw/*165.17*/("""
            """),format.raw/*166.13*/("""url: "/verOtAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*173.36*/("""{"""),format.raw/*173.37*/("""
	     		"""),format.raw/*174.9*/("""document.getElementById('mostrarLaOt').innerHTML = respuesta;
	     		$('#modalVerOt').modal('show');
	     	"""),format.raw/*176.8*/("""}"""),format.raw/*176.9*/("""
        """),format.raw/*177.9*/("""}"""),format.raw/*177.10*/(""");
	"""),format.raw/*178.2*/("""}"""),format.raw/*178.3*/("""
	
	"""),format.raw/*180.2*/("""const verCotizacion = (id_cotizacion) => """),format.raw/*180.43*/("""{"""),format.raw/*180.44*/("""
		"""),format.raw/*181.3*/("""var formData = new FormData();
	  	formData.append('id_cotizacion',id_cotizacion);
        $.ajax("""),format.raw/*183.16*/("""{"""),format.raw/*183.17*/("""
            """),format.raw/*184.13*/("""url: "/verCotizacionAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*191.36*/("""{"""),format.raw/*191.37*/("""
	     		"""),format.raw/*192.9*/("""document.getElementById('mostrarLaCotizacion').innerHTML = respuesta;
	     		$('#modalVerCotizacion').modal('show');
	     	"""),format.raw/*194.8*/("""}"""),format.raw/*194.9*/("""
        """),format.raw/*195.9*/("""}"""),format.raw/*195.10*/(""");
	"""),format.raw/*196.2*/("""}"""),format.raw/*196.3*/("""
	
	"""),format.raw/*198.2*/("""const selectOt = (id_ot) => """),format.raw/*198.30*/("""{"""),format.raw/*198.31*/("""
			"""),format.raw/*199.4*/("""$("#form_id_ot").val(id_ot);
			document.getElementById("formDespacharOt").submit();
	"""),format.raw/*201.2*/("""}"""),format.raw/*201.3*/("""
		
		
	
	
	
"""),format.raw/*207.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listOt:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listOt)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listOt) => apply(mapDiccionario,mapPermiso,userMnu,listOt)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizar/otListaDespachar.scala.html
                  HASH: 81b6b0528adc70b4f3a9d9882c55bc87d2c62d4b
                  MATRIX: 1034->1|1252->126|1285->134|1301->142|1340->144|1369->148|1437->196|1467->201|1522->236|1551->239|1594->261|1623->264|1667->287|1702->295|1779->346|1918->463|1951->469|2266->757|2289->771|2321->781|2351->782|2801->1205|2824->1219|2855->1229|2933->1280|2956->1294|2987->1304|3152->1442|3175->1456|3210->1470|3450->1684|3487->1705|3526->1706|3561->1714|3631->1757|3646->1763|3675->1771|3786->1855|3801->1861|3829->1868|3953->1965|3968->1971|3996->1978|4169->2124|4184->2130|4212->2137|4256->2154|4274->2163|4324->2192|4360->2201|4433->2247|4448->2253|4476->2260|4602->2359|4617->2365|4645->2372|4689->2389|4707->2398|4757->2427|4793->2436|4864->2480|4879->2486|4907->2493|5012->2571|5027->2577|5056->2585|5088->2590|5103->2596|5133->2604|5243->2687|5258->2693|5286->2700|5318->2705|5333->2711|5362->2718|5473->2802|5488->2808|5516->2815|5548->2820|5563->2826|5592->2833|5724->2938|5742->2947|5793->2977|5858->3015|5873->3021|5901->3028|5931->3031|5946->3037|5975->3045|6118->3161|6136->3170|6187->3200|6252->3238|6267->3244|6295->3251|6325->3254|6340->3260|6369->3268|6500->3372|6515->3378|6543->3385|6689->3501|6722->3507|7379->4136|7403->4150|7449->4174|8955->5649|8986->5652|9077->5714|9107->5715|9140->5720|9200->5751|9230->5752|9266->5760|9442->5907|9472->5908|9513->5920|9620->5998|9650->5999|9683->6004|9712->6005|9741->6006|9842->6079|9871->6080|9933->6113|9963->6114|9994->6117|10105->6199|10135->6200|10177->6213|10428->6435|10458->6436|10495->6445|10632->6554|10661->6555|10698->6564|10728->6565|10760->6569|10789->6570|10821->6574|10891->6615|10921->6616|10952->6619|11079->6717|11109->6718|11151->6731|11410->6961|11440->6962|11477->6971|11630->7096|11659->7097|11696->7106|11726->7107|11758->7111|11787->7112|11819->7116|11876->7144|11906->7145|11938->7149|12052->7235|12081->7236|12122->7249
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|41->10|41->10|42->11|42->11|46->15|47->16|47->16|49->18|53->22|53->22|53->22|53->22|65->34|65->34|65->34|66->35|66->35|66->35|70->39|70->39|70->39|79->48|79->48|79->48|80->49|81->50|81->50|81->50|83->52|83->52|83->52|85->54|85->54|85->54|90->59|90->59|90->59|91->60|91->60|91->60|92->61|93->62|93->62|93->62|95->64|95->64|95->64|96->65|96->65|96->65|97->66|98->67|98->67|98->67|99->68|99->68|99->68|99->68|99->68|99->68|100->69|100->69|100->69|100->69|100->69|100->69|101->70|101->70|101->70|101->70|101->70|101->70|103->72|103->72|103->72|104->73|104->73|104->73|104->73|104->73|104->73|107->76|107->76|107->76|108->77|108->77|108->77|108->77|108->77|108->77|111->80|111->80|111->80|116->85|117->86|134->103|134->103|134->103|176->145|179->148|180->149|180->149|181->150|181->150|181->150|182->151|185->154|185->154|186->155|187->156|187->156|188->157|188->157|188->157|190->159|190->159|193->162|193->162|194->163|196->165|196->165|197->166|204->173|204->173|205->174|207->176|207->176|208->177|208->177|209->178|209->178|211->180|211->180|211->180|212->181|214->183|214->183|215->184|222->191|222->191|223->192|225->194|225->194|226->195|226->195|227->196|227->196|229->198|229->198|229->198|230->199|232->201|232->201|238->207
                  -- GENERATED --
              */
          