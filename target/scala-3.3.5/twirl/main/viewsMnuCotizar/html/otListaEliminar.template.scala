
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

object otListaEliminar extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

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
	
	"""),format.raw/*13.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*14.4*/barraTitulo(mapDiccionario, "LISTADO DE "+mapDiccionario.get("Ordenes_de_trabajo").toUpperCase()+" (ELIMINAR)","(solo OT pendientes de confirmar ó confirmadas que no tienen despachos efectuados)")),format.raw/*14.200*/("""
		"""),format.raw/*15.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>SUCURSAL</TH>
							<TH>Nro."""),_display_(/*21.17*/mapDiccionario/*21.31*/.get("OT")),format.raw/*21.41*/("""</TH>
							<TH style="min-width:80px;">FECHA<br>"""),_display_(/*22.46*/mapDiccionario/*22.60*/.get("OT")),format.raw/*22.70*/("""</TH>
							<TH style="min-width:80px;">CONFIRMADA<br>"""),_display_(/*23.51*/mapDiccionario/*23.65*/.get("OT")),format.raw/*23.75*/("""</TH>
							<TH>Nro.COTI</TH>
							<TH style="min-width:80px;">FECHA<br>COTIZACION</TH>
							<TH>"""),_display_(/*26.13*/mapDiccionario/*26.27*/.get("BODEGA")),format.raw/*26.41*/("""/PROYECTO</TH>
							<TH>CLIENTE</TH>
							<TH>PROYECTO</TH>
							<TH>OBSERVACIONES<br>"""),_display_(/*29.30*/mapDiccionario/*29.44*/.get("OT")),format.raw/*29.54*/("""</TH>
							<TH>OBSERVACIONES<br>COTIZACION</TH>
							<TH>DOC<br>"""),_display_(/*31.20*/mapDiccionario/*31.34*/.get("OT")),format.raw/*31.44*/("""</TH>
							<TH>DOC<br>COTI</TH>
							<TH>DEL</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*37.8*/for(lista1 <- listOt) yield /*37.29*/{_display_(Seq[Any](format.raw/*37.30*/("""
							"""),format.raw/*38.8*/("""<TR>
								<td style="text-align:left;">"""),_display_(/*39.39*/lista1/*39.45*/.get(13)),format.raw/*39.53*/("""</td>
								<td style="text-align:center;">
									<a href="#" onclick="verOt('"""),_display_(/*41.39*/lista1/*41.45*/.get(0)),format.raw/*41.52*/("""')">
										<kbd style="background-color: rgb(90, 200, 245)">
											<font color="green">"""),_display_(/*43.33*/lista1/*43.39*/.get(2)),format.raw/*43.46*/("""</font>
										</kbd>
									</a>
								</td>
								<td style="text-align:center;">
									<div style="display:none">"""),_display_(/*48.37*/lista1/*48.43*/.get(3)),format.raw/*48.50*/("""</div>
									"""),_display_(/*49.11*/utilities/*49.20*/.Fechas.DDMMAA(lista1.get(3))),format.raw/*49.49*/("""
								"""),format.raw/*50.9*/("""</td>
								<td style="min-width: 80px; text-align:center;">
									<div style="display:none">"""),_display_(/*52.37*/lista1/*52.43*/.get(15)),format.raw/*52.51*/("""</div>
									"""),_display_(/*53.11*/utilities/*53.20*/.Fechas.DDMMAA(lista1.get(15))),format.raw/*53.50*/("""
								"""),format.raw/*54.9*/("""</td>
								<td style="text-align:center;">
									<a href="#" onclick="verCotizacion('"""),_display_(/*56.47*/lista1/*56.53*/.get(1)),format.raw/*56.60*/("""')">
										<kbd style="background-color: rgb(90, 200, 245)">
											<font color="green">"""),_display_(/*58.33*/lista1/*58.39*/.get(4)),format.raw/*58.46*/("""</font>
										</kbd>
									</a>
								</td>
								<td style="text-align:center;">
									<div style="display:none">"""),_display_(/*63.37*/lista1/*63.43*/.get(5)),format.raw/*63.50*/("""</div>
									"""),_display_(/*64.11*/utilities/*64.20*/.Fechas.DDMMAA(lista1.get(5))),format.raw/*64.49*/("""
								"""),format.raw/*65.9*/("""</td>
								<td style="text-align:left;"><a href="#" onclick="buscaBodega('"""),_display_(/*66.73*/lista1/*66.79*/.get(12)),format.raw/*66.87*/("""')">"""),_display_(/*66.92*/lista1/*66.98*/.get(12)),format.raw/*66.106*/("""</a></td>
								<td style="text-align:left;"><a href="#" onclick="buscaCliente('"""),_display_(/*67.74*/lista1/*67.80*/.get(6)),format.raw/*67.87*/("""')">"""),_display_(/*67.92*/lista1/*67.98*/.get(6)),format.raw/*67.105*/("""</a></td>
								<td style="text-align:left;"><a href="#" onclick="buscaProyecto('"""),_display_(/*68.75*/lista1/*68.81*/.get(7)),format.raw/*68.88*/("""')">"""),_display_(/*68.93*/lista1/*68.99*/.get(7)),format.raw/*68.106*/("""</a></td>
								<td style="text-align:left;">"""),_display_(/*69.39*/lista1/*69.45*/.get(8)),format.raw/*69.52*/("""</td>
								<td style="text-align:left;">"""),_display_(/*70.39*/lista1/*70.45*/.get(9)),format.raw/*70.52*/("""</td>
								<td style="text-align:center;">
									"""),_display_(if(lista1.get(10).equals("0"))/*72.41*/{_display_(Seq[Any](format.raw/*72.42*/("""
										"""),format.raw/*73.11*/("""--
									""")))}else/*74.15*/{_display_(Seq[Any](format.raw/*74.16*/("""
										"""),format.raw/*75.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*75.52*/lista1/*75.58*/.get(10)),format.raw/*75.66*/("""')">
											<kbd style="background-color: #7F8C8D">doc</kbd>
										</a>
									""")))}),format.raw/*78.11*/("""
								"""),format.raw/*79.9*/("""</td>
								<td style="text-align:center;">
									"""),_display_(if(lista1.get(11).equals("0"))/*81.41*/{_display_(Seq[Any](format.raw/*81.42*/("""
										"""),format.raw/*82.11*/("""--
									""")))}else/*83.15*/{_display_(Seq[Any](format.raw/*83.16*/("""
										"""),format.raw/*84.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*84.52*/lista1/*84.58*/.get(11)),format.raw/*84.66*/("""')">
											<kbd style="background-color: #7F8C8D">doc</kbd>
										</a>
									""")))}),format.raw/*87.11*/("""
								"""),format.raw/*88.9*/("""</td>
								<td  style="text-align:center;">
									<a href="#" onclick= "eliminarOt('"""),_display_(/*90.45*/lista1/*90.51*/.get(0)),format.raw/*90.58*/("""','"""),_display_(/*90.62*/lista1/*90.68*/.get(2)),format.raw/*90.75*/("""')">
										<kbd style="background-color: red">X</kbd>
									</a>
								</td>
							</TR>
			 			""")))}),format.raw/*95.9*/("""
					"""),format.raw/*96.6*/("""</tbody>
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
					<h5 class='modal-title'>"""),_display_(/*113.31*/mapDiccionario/*113.45*/.get("ORDEN_DE_TRABAJO")),format.raw/*113.69*/("""</h5>
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
	
	<form id="eliminarOt" method="post" action="/otElimina/">
		<input type="hidden" id="del_idOt" name="id_ot">
	</form>

""")))}),format.raw/*160.2*/("""


"""),format.raw/*163.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*164.31*/("""{"""),format.raw/*164.32*/("""
		  """),format.raw/*165.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*165.36*/("""{"""),format.raw/*165.37*/("""
		    	"""),format.raw/*166.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 2, "desc" ]],
		    	"language": """),format.raw/*169.20*/("""{"""),format.raw/*169.21*/("""
		        	"""),format.raw/*170.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*171.11*/("""}"""),format.raw/*171.12*/("""
		  """),format.raw/*172.5*/("""}"""),format.raw/*172.6*/(""" """),format.raw/*172.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*174.2*/("""}"""),format.raw/*174.3*/(""");
	
	
	const verOt = (id_ot) => """),format.raw/*177.27*/("""{"""),format.raw/*177.28*/("""
		"""),format.raw/*178.3*/("""var formData = new FormData();
	  	formData.append('id_ot',id_ot);
        $.ajax("""),format.raw/*180.16*/("""{"""),format.raw/*180.17*/("""
            """),format.raw/*181.13*/("""url: "/verOtAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*188.36*/("""{"""),format.raw/*188.37*/("""
	     		"""),format.raw/*189.9*/("""document.getElementById('mostrarLaOt').innerHTML = respuesta;
	     		$('#modalVerOt').modal('show');
	     	"""),format.raw/*191.8*/("""}"""),format.raw/*191.9*/("""
        """),format.raw/*192.9*/("""}"""),format.raw/*192.10*/(""");
	"""),format.raw/*193.2*/("""}"""),format.raw/*193.3*/("""
	
	"""),format.raw/*195.2*/("""const verCotizacion = (id_cotizacion) => """),format.raw/*195.43*/("""{"""),format.raw/*195.44*/("""
		"""),format.raw/*196.3*/("""var formData = new FormData();
	  	formData.append('id_cotizacion',id_cotizacion);
        $.ajax("""),format.raw/*198.16*/("""{"""),format.raw/*198.17*/("""
            """),format.raw/*199.13*/("""url: "/verCotizacionAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*206.36*/("""{"""),format.raw/*206.37*/("""
	     		"""),format.raw/*207.9*/("""document.getElementById('mostrarLaCotizacion').innerHTML = respuesta;
	     		$('#modalVerCotizacion').modal('show');
	     	"""),format.raw/*209.8*/("""}"""),format.raw/*209.9*/("""
        """),format.raw/*210.9*/("""}"""),format.raw/*210.10*/(""");
	"""),format.raw/*211.2*/("""}"""),format.raw/*211.3*/("""
	
	"""),format.raw/*213.2*/("""const descargaDocumento = (nombreDOC) => """),format.raw/*213.43*/("""{"""),format.raw/*213.44*/("""
		  """),format.raw/*214.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*216.2*/("""}"""),format.raw/*216.3*/("""
	
	"""),format.raw/*218.2*/("""const eliminarOt = (id_ot, numero) => """),format.raw/*218.40*/("""{"""),format.raw/*218.41*/("""
		"""),format.raw/*219.3*/("""alertify.confirm('Esta seguro de eliminar """),_display_(/*219.46*/mapDiccionario/*219.60*/.get("Orden_de_trabajo")),format.raw/*219.84*/(""" """),format.raw/*219.85*/("""("""),_display_(/*219.87*/mapDiccionario/*219.101*/.get("OT")),format.raw/*219.111*/(""") nro: '+numero+".<br>Deberá confirmar nuevamente cotización", function (e) """),format.raw/*219.187*/("""{"""),format.raw/*219.188*/("""
			"""),format.raw/*220.4*/("""if (e) """),format.raw/*220.11*/("""{"""),format.raw/*220.12*/("""
				"""),format.raw/*221.5*/("""$("#del_idOt").val(id_ot);
				document.getElementById("eliminarOt").submit();
			"""),format.raw/*223.4*/("""}"""),format.raw/*223.5*/("""
		"""),format.raw/*224.3*/("""}"""),format.raw/*224.4*/(""");
	"""),format.raw/*225.2*/("""}"""),format.raw/*225.3*/("""
		
		
	
	
	
"""),format.raw/*231.1*/("""</script>




		
		
	
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
                  SOURCE: app/viewsMnuCotizar/otListaEliminar.scala.html
                  HASH: 3e2c73a24fb3c4893ec1b925e7544208972928fe
                  MATRIX: 1033->1|1251->126|1284->134|1300->142|1339->144|1368->148|1436->196|1466->201|1521->236|1550->239|1593->261|1622->264|1666->287|1697->291|1774->342|1992->538|2022->541|2360->852|2383->866|2414->876|2492->927|2515->941|2546->951|2629->1007|2652->1021|2683->1031|2813->1134|2836->1148|2871->1162|2991->1255|3014->1269|3045->1279|3141->1348|3164->1362|3195->1372|3321->1472|3358->1493|3397->1494|3432->1502|3502->1545|3517->1551|3546->1559|3657->1643|3672->1649|3700->1656|3824->1753|3839->1759|3867->1766|4023->1895|4038->1901|4066->1908|4110->1925|4128->1934|4178->1963|4214->1972|4340->2071|4355->2077|4384->2085|4428->2102|4446->2111|4497->2141|4533->2150|4652->2242|4667->2248|4695->2255|4819->2352|4834->2358|4862->2365|5018->2494|5033->2500|5061->2507|5105->2524|5123->2533|5173->2562|5209->2571|5314->2649|5329->2655|5358->2663|5390->2668|5405->2674|5435->2682|5545->2765|5560->2771|5588->2778|5620->2783|5635->2789|5664->2796|5775->2880|5790->2886|5818->2893|5850->2898|5865->2904|5894->2911|5969->2959|5984->2965|6012->2972|6083->3016|6098->3022|6126->3029|6239->3115|6278->3116|6317->3127|6353->3144|6392->3145|6431->3156|6499->3197|6514->3203|6543->3211|6664->3301|6700->3310|6813->3396|6852->3397|6891->3408|6927->3425|6966->3426|7005->3437|7073->3478|7088->3484|7117->3492|7238->3582|7274->3591|7392->3682|7407->3688|7435->3695|7466->3699|7481->3705|7509->3712|7646->3819|7679->3825|8336->4454|8360->4468|8406->4492|10076->6131|10107->6134|10198->6196|10228->6197|10261->6202|10321->6233|10351->6234|10387->6242|10563->6389|10593->6390|10634->6402|10741->6480|10771->6481|10804->6486|10833->6487|10862->6488|10963->6561|10992->6562|11054->6595|11084->6596|11115->6599|11226->6681|11256->6682|11298->6695|11549->6917|11579->6918|11616->6927|11753->7036|11782->7037|11819->7046|11849->7047|11881->7051|11910->7052|11942->7056|12012->7097|12042->7098|12073->7101|12200->7199|12230->7200|12272->7213|12531->7443|12561->7444|12598->7453|12751->7578|12780->7579|12817->7588|12847->7589|12879->7593|12908->7594|12940->7598|13010->7639|13040->7640|13073->7645|13205->7749|13234->7750|13266->7754|13333->7792|13363->7793|13394->7796|13465->7839|13489->7853|13535->7877|13565->7878|13595->7880|13620->7894|13653->7904|13759->7980|13790->7981|13822->7985|13858->7992|13888->7993|13921->7998|14031->8080|14060->8081|14091->8084|14120->8085|14152->8089|14181->8090|14222->8103
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|41->10|41->10|42->11|42->11|44->13|45->14|45->14|46->15|52->21|52->21|52->21|53->22|53->22|53->22|54->23|54->23|54->23|57->26|57->26|57->26|60->29|60->29|60->29|62->31|62->31|62->31|68->37|68->37|68->37|69->38|70->39|70->39|70->39|72->41|72->41|72->41|74->43|74->43|74->43|79->48|79->48|79->48|80->49|80->49|80->49|81->50|83->52|83->52|83->52|84->53|84->53|84->53|85->54|87->56|87->56|87->56|89->58|89->58|89->58|94->63|94->63|94->63|95->64|95->64|95->64|96->65|97->66|97->66|97->66|97->66|97->66|97->66|98->67|98->67|98->67|98->67|98->67|98->67|99->68|99->68|99->68|99->68|99->68|99->68|100->69|100->69|100->69|101->70|101->70|101->70|103->72|103->72|104->73|105->74|105->74|106->75|106->75|106->75|106->75|109->78|110->79|112->81|112->81|113->82|114->83|114->83|115->84|115->84|115->84|115->84|118->87|119->88|121->90|121->90|121->90|121->90|121->90|121->90|126->95|127->96|144->113|144->113|144->113|191->160|194->163|195->164|195->164|196->165|196->165|196->165|197->166|200->169|200->169|201->170|202->171|202->171|203->172|203->172|203->172|205->174|205->174|208->177|208->177|209->178|211->180|211->180|212->181|219->188|219->188|220->189|222->191|222->191|223->192|223->192|224->193|224->193|226->195|226->195|226->195|227->196|229->198|229->198|230->199|237->206|237->206|238->207|240->209|240->209|241->210|241->210|242->211|242->211|244->213|244->213|244->213|245->214|247->216|247->216|249->218|249->218|249->218|250->219|250->219|250->219|250->219|250->219|250->219|250->219|250->219|250->219|250->219|251->220|251->220|251->220|252->221|254->223|254->223|255->224|255->224|256->225|256->225|262->231
                  -- GENERATED --
              */
          