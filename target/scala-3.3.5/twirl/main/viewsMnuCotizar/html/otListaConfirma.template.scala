
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

object otListaConfirma extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

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
		"""),_display_(/*14.4*/barraTitulo(mapDiccionario, "LISTADO DE "+mapDiccionario.get("Ordenes_de_trabajo").toUpperCase()+" PENDIENTES DE CONFIRMAR","")),format.raw/*14.131*/("""
		"""),format.raw/*15.3*/("""<form method="post" action="/otConfirma/">
			<input type="hidden" id="cambiosDeEstados" name="cambiosDeEstados">
			<div class="row  justify-content-md-center">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<TR>
								<TH>SUCURSAL</TH>
								<TH>Nro."""),_display_(/*23.18*/mapDiccionario/*23.32*/.get("OT")),format.raw/*23.42*/("""</TH>
								<TH style="min-width:80px;">FECHA<br>"""),_display_(/*24.47*/mapDiccionario/*24.61*/.get("OT")),format.raw/*24.71*/("""</TH>
								<TH>Nro.COTI</TH>
								<TH style="min-width:80px;">FECHA<br>COTIZACION</TH>
								<TH>"""),_display_(/*27.14*/mapDiccionario/*27.28*/.get("BODEGA")),format.raw/*27.42*/("""/PROYECTO</TH>
								<TH>CLIENTE</TH>
								<TH>PROYECTO</TH>
								<TH>OBSERVACIONES<br>"""),_display_(/*30.31*/mapDiccionario/*30.45*/.get("OT")),format.raw/*30.55*/("""</TH>
								<TH>OBSERVACIONES<br>COTIZACION</TH>
								
								<TH>NRO OC<br>CLIENTE</TH>
								<TH>OC CLIENTE<br>ADJUNTO</TH>
								<TH>CONTRATO<br>ADJUNTO</TH>
								
								
								<TH>DOC ANEXO<br>"""),_display_(/*38.27*/mapDiccionario/*38.41*/.get("OT")),format.raw/*38.51*/("""</TH>
								<TH>DOC ANEXO<br>COTI</TH>
								<TH>ULTIMA<br>ACTUALIZACION</TH>
								<TH>ESTIMACION<br>DE ENTREGA</TH>
								<TH>CONFIRMAR</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*46.9*/for(lista1 <- listOt) yield /*46.30*/{_display_(Seq[Any](format.raw/*46.31*/("""
								"""),format.raw/*47.9*/("""<TR>
									<td style="text-align:left;">"""),_display_(/*48.40*/lista1/*48.46*/.get(13)),format.raw/*48.54*/("""</td>
									<td style="text-align:center;">
										<a href="#" onclick="verOt('"""),_display_(/*50.40*/lista1/*50.46*/.get(0)),format.raw/*50.53*/("""')">
											<kbd style="background-color: rgb(90, 200, 245)">
												<font color="green">"""),_display_(/*52.34*/lista1/*52.40*/.get(2)),format.raw/*52.47*/("""</font>
											</kbd>
										</a>
									</td>
									<td  style="min-width: 80px; text-align:center;">
										<div style="display:none">"""),_display_(/*57.38*/lista1/*57.44*/.get(3)),format.raw/*57.51*/("""</div>
										"""),_display_(/*58.12*/utilities/*58.21*/.Fechas.DDMMAA(lista1.get(3))),format.raw/*58.50*/("""
									"""),format.raw/*59.10*/("""</td>
									<td style="text-align:center;">
										<a href="#" onclick="verCotizacion('"""),_display_(/*61.48*/lista1/*61.54*/.get(1)),format.raw/*61.61*/("""')">
											<kbd style="background-color: rgb(90, 200, 245)">
												<font color="green">"""),_display_(/*63.34*/lista1/*63.40*/.get(4)),format.raw/*63.47*/("""</font>
											</kbd>
										</a>
									</td>
									<td  style="min-width: 80px; text-align:center;">
										<div style="display:none">"""),_display_(/*68.38*/lista1/*68.44*/.get(5)),format.raw/*68.51*/("""</div>
										"""),_display_(/*69.12*/utilities/*69.21*/.Fechas.DDMMAA(lista1.get(5))),format.raw/*69.50*/("""
									"""),format.raw/*70.10*/("""</td>
									<td style="text-align:left;"><a href="#" onclick="buscaBodega('"""),_display_(/*71.74*/lista1/*71.80*/.get(12)),format.raw/*71.88*/("""')">"""),_display_(/*71.93*/lista1/*71.99*/.get(12)),format.raw/*71.107*/("""</a></td>
									<td style="text-align:left;"><a href="#" onclick="buscaCliente('"""),_display_(/*72.75*/lista1/*72.81*/.get(6)),format.raw/*72.88*/("""')">"""),_display_(/*72.93*/lista1/*72.99*/.get(6)),format.raw/*72.106*/("""</a></td>
									<td style="text-align:left;"><a href="#" onclick="buscaProyecto('"""),_display_(/*73.76*/lista1/*73.82*/.get(7)),format.raw/*73.89*/("""')">"""),_display_(/*73.94*/lista1/*73.100*/.get(7)),format.raw/*73.107*/("""</a></td>
									<td style="text-align:left;">"""),_display_(/*74.40*/lista1/*74.46*/.get(8)),format.raw/*74.53*/("""</td>
									<td style="text-align:left;">"""),_display_(/*75.40*/lista1/*75.46*/.get(9)),format.raw/*75.53*/("""</td>
									
									
									<td style="text-align:left;">"""),_display_(/*78.40*/lista1/*78.46*/.get(17)),format.raw/*78.54*/("""</td>
									<td style="text-align:center;">
										"""),_display_(if(lista1.get(18).equals("0"))/*80.42*/{_display_(Seq[Any](format.raw/*80.43*/("""
											"""),format.raw/*81.12*/("""--
										""")))}else/*82.16*/{_display_(Seq[Any](format.raw/*82.17*/("""
											"""),format.raw/*83.12*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*83.53*/lista1/*83.59*/.get(18)),format.raw/*83.67*/("""')">
												<kbd style="background-color: #7F8C8D">doc</kbd>
											</a>
										""")))}),format.raw/*86.12*/("""
									"""),format.raw/*87.10*/("""</td>
									<td style="text-align:center;">
										"""),_display_(if(lista1.get(19).equals("0"))/*89.42*/{_display_(Seq[Any](format.raw/*89.43*/("""
											"""),format.raw/*90.12*/("""--
										""")))}else/*91.16*/{_display_(Seq[Any](format.raw/*91.17*/("""
											"""),format.raw/*92.12*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*92.53*/lista1/*92.59*/.get(19)),format.raw/*92.67*/("""')">
												<kbd style="background-color: #7F8C8D">doc</kbd>
											</a>
										""")))}),format.raw/*95.12*/("""
									"""),format.raw/*96.10*/("""</td>
										
									
									
									<td style="text-align:center;">
										"""),_display_(if(lista1.get(10).equals("0"))/*101.42*/{_display_(Seq[Any](format.raw/*101.43*/("""
											"""),format.raw/*102.12*/("""--
										""")))}else/*103.16*/{_display_(Seq[Any](format.raw/*103.17*/("""
											"""),format.raw/*104.12*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*104.53*/lista1/*104.59*/.get(10)),format.raw/*104.67*/("""')">
												<kbd style="background-color: #7F8C8D">doc</kbd>
											</a>
										""")))}),format.raw/*107.12*/("""
									"""),format.raw/*108.10*/("""</td>
									<td style="text-align:center;">
										"""),_display_(if(lista1.get(11).equals("0"))/*110.42*/{_display_(Seq[Any](format.raw/*110.43*/("""
											"""),format.raw/*111.12*/("""--
										""")))}else/*112.16*/{_display_(Seq[Any](format.raw/*112.17*/("""
											"""),format.raw/*113.12*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*113.53*/lista1/*113.59*/.get(11)),format.raw/*113.67*/("""')">
												<kbd style="background-color: #7F8C8D">doc</kbd>
											</a>
										""")))}),format.raw/*116.12*/("""
									"""),format.raw/*117.10*/("""</td>
									<td style="min-width: 80px; text-align:center;">
										<div style="display: none;">"""),_display_(/*119.40*/utilities/*119.49*/.Fechas.AAMMDD(lista1.get(15))),format.raw/*119.79*/("""</div>
										<div id="fechaActual_"""),_display_(/*120.33*/lista1/*120.39*/.get(0)),format.raw/*120.46*/("""">"""),_display_(/*120.49*/lista1/*120.55*/.get(15)),format.raw/*120.63*/("""</div>
									</td>
									<td style="min-width: 80px; text-align:center;">
										<div style="display: none;">"""),_display_(/*123.40*/utilities/*123.49*/.Fechas.AAMMDD(lista1.get(16))),format.raw/*123.79*/("""</div>
										<div id="fechaActual_"""),_display_(/*124.33*/lista1/*124.39*/.get(0)),format.raw/*124.46*/("""">"""),_display_(/*124.49*/lista1/*124.55*/.get(16)),format.raw/*124.63*/("""</div>
									</td>
									<td  style="text-align:center;">
										<input type="checkbox" id=""""),_display_(/*127.39*/lista1/*127.45*/.get(0)),format.raw/*127.52*/("""" value ="0" onchange="cambiaEstado(id,value)"></td>
									</td>
								</TR>
				 			""")))}),format.raw/*130.10*/("""
						"""),format.raw/*131.7*/("""</tbody>
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
					<h5 class='modal-title'>"""),_display_(/*152.31*/mapDiccionario/*152.45*/.get("ORDEN_DE_TRABAJO")),format.raw/*152.69*/("""</h5>
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

""")))}),format.raw/*199.2*/("""


"""),format.raw/*202.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*203.31*/("""{"""),format.raw/*203.32*/("""
		  """),format.raw/*204.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*204.36*/("""{"""),format.raw/*204.37*/("""
		    	"""),format.raw/*205.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 2, "desc" ]],
		    	"language": """),format.raw/*208.20*/("""{"""),format.raw/*208.21*/("""
		        	"""),format.raw/*209.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*210.11*/("""}"""),format.raw/*210.12*/("""
		  """),format.raw/*211.5*/("""}"""),format.raw/*211.6*/(""" """),format.raw/*211.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*213.2*/("""}"""),format.raw/*213.3*/(""");
	
	
	const verOt = (id_ot) => """),format.raw/*216.27*/("""{"""),format.raw/*216.28*/("""
		"""),format.raw/*217.3*/("""var formData = new FormData();
	  	formData.append('id_ot',id_ot);
        $.ajax("""),format.raw/*219.16*/("""{"""),format.raw/*219.17*/("""
            """),format.raw/*220.13*/("""url: "/verOtAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*227.36*/("""{"""),format.raw/*227.37*/("""
	     		"""),format.raw/*228.9*/("""document.getElementById('mostrarLaOt').innerHTML = respuesta;
	     		$('#modalVerOt').modal('show');
	     	"""),format.raw/*230.8*/("""}"""),format.raw/*230.9*/("""
        """),format.raw/*231.9*/("""}"""),format.raw/*231.10*/(""");
	"""),format.raw/*232.2*/("""}"""),format.raw/*232.3*/("""
	
	"""),format.raw/*234.2*/("""const verCotizacion = (id_cotizacion) => """),format.raw/*234.43*/("""{"""),format.raw/*234.44*/("""
		"""),format.raw/*235.3*/("""var formData = new FormData();
	  	formData.append('id_cotizacion',id_cotizacion);
        $.ajax("""),format.raw/*237.16*/("""{"""),format.raw/*237.17*/("""
            """),format.raw/*238.13*/("""url: "/verCotizacionAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*245.36*/("""{"""),format.raw/*245.37*/("""
	     		"""),format.raw/*246.9*/("""document.getElementById('mostrarLaCotizacion').innerHTML = respuesta;
	     		$('#modalVerCotizacion').modal('show');
	     	"""),format.raw/*248.8*/("""}"""),format.raw/*248.9*/("""
        """),format.raw/*249.9*/("""}"""),format.raw/*249.10*/(""");
	"""),format.raw/*250.2*/("""}"""),format.raw/*250.3*/("""
	
	"""),format.raw/*252.2*/("""const descargaDocumento = (nombreDOC) => """),format.raw/*252.43*/("""{"""),format.raw/*252.44*/("""
		  """),format.raw/*253.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*255.2*/("""}"""),format.raw/*255.3*/("""
	
	"""),format.raw/*257.2*/("""const cambiaEstado = (id_cotizacion, valor) => """),format.raw/*257.49*/("""{"""),format.raw/*257.50*/("""
		"""),format.raw/*258.3*/("""if(valor == 0) """),format.raw/*258.18*/("""{"""),format.raw/*258.19*/("""
			"""),format.raw/*259.4*/("""document.getElementById(id_cotizacion).value = "1";
			let aux = ""+$("#cambiosDeEstados").val()+id_cotizacion + ","
			$("#cambiosDeEstados").val(aux);
		"""),format.raw/*262.3*/("""}"""),format.raw/*262.4*/("""else"""),format.raw/*262.8*/("""{"""),format.raw/*262.9*/("""
			"""),format.raw/*263.4*/("""document.getElementById(id_cotizacion).value = "0";
			let aux = ""+id_cotizacion + ","
			$("#cambiosDeEstados").val($("#cambiosDeEstados").val().replace(aux,""));
		"""),format.raw/*266.3*/("""}"""),format.raw/*266.4*/("""
		
	"""),format.raw/*268.2*/("""}"""),format.raw/*268.3*/("""
		
		
	
	
	
"""),format.raw/*274.1*/("""</script>




		
		
	
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
                  SOURCE: app/viewsMnuCotizar/otListaConfirma.scala.html
                  HASH: 41cab0df7fb46589625a475e75e55f6ed1cc75f3
                  MATRIX: 1033->1|1251->126|1284->134|1300->142|1339->144|1368->148|1436->196|1466->201|1521->236|1550->239|1593->261|1622->264|1666->287|1697->291|1774->342|1923->469|1953->472|2414->906|2437->920|2468->930|2547->982|2570->996|2601->1006|2734->1112|2757->1126|2792->1140|2915->1236|2938->1250|2969->1260|3211->1475|3234->1489|3265->1499|3492->1700|3529->1721|3568->1722|3604->1731|3675->1775|3690->1781|3719->1789|3832->1875|3847->1881|3875->1888|4001->1987|4016->1993|4044->2000|4223->2152|4238->2158|4266->2165|4311->2183|4329->2192|4379->2221|4417->2231|4538->2325|4553->2331|4581->2338|4707->2437|4722->2443|4750->2450|4929->2602|4944->2608|4972->2615|5017->2633|5035->2642|5085->2671|5123->2681|5229->2760|5244->2766|5273->2774|5305->2779|5320->2785|5350->2793|5461->2877|5476->2883|5504->2890|5536->2895|5551->2901|5580->2908|5692->2993|5707->2999|5735->3006|5767->3011|5783->3017|5812->3024|5888->3073|5903->3079|5931->3086|6003->3131|6018->3137|6046->3144|6138->3209|6153->3215|6182->3223|6297->3311|6336->3312|6376->3324|6413->3342|6452->3343|6492->3355|6560->3396|6575->3402|6604->3410|6728->3503|6766->3513|6881->3601|6920->3602|6960->3614|6997->3632|7036->3633|7076->3645|7144->3686|7159->3692|7188->3700|7312->3793|7350->3803|7497->3922|7537->3923|7578->3935|7616->3953|7656->3954|7697->3966|7766->4007|7782->4013|7812->4021|7937->4114|7976->4124|8092->4212|8132->4213|8173->4225|8211->4243|8251->4244|8292->4256|8361->4297|8377->4303|8407->4311|8532->4404|8571->4414|8702->4517|8721->4526|8773->4556|8840->4595|8856->4601|8885->4608|8916->4611|8932->4617|8962->4625|9109->4744|9128->4753|9180->4783|9247->4822|9263->4828|9292->4835|9323->4838|9339->4844|9369->4852|9499->4954|9515->4960|9544->4967|9667->5058|9702->5065|10547->5882|10571->5896|10617->5920|12287->7559|12318->7562|12409->7624|12439->7625|12472->7630|12532->7661|12562->7662|12598->7670|12774->7817|12804->7818|12845->7830|12952->7908|12982->7909|13015->7914|13044->7915|13073->7916|13174->7989|13203->7990|13265->8023|13295->8024|13326->8027|13437->8109|13467->8110|13509->8123|13760->8345|13790->8346|13827->8355|13964->8464|13993->8465|14030->8474|14060->8475|14092->8479|14121->8480|14153->8484|14223->8525|14253->8526|14284->8529|14411->8627|14441->8628|14483->8641|14742->8871|14772->8872|14809->8881|14962->9006|14991->9007|15028->9016|15058->9017|15090->9021|15119->9022|15151->9026|15221->9067|15251->9068|15284->9073|15416->9177|15445->9178|15477->9182|15553->9229|15583->9230|15614->9233|15658->9248|15688->9249|15720->9253|15903->9408|15932->9409|15964->9413|15993->9414|16025->9418|16220->9585|16249->9586|16282->9591|16311->9592|16352->9605
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|41->10|41->10|42->11|42->11|44->13|45->14|45->14|46->15|54->23|54->23|54->23|55->24|55->24|55->24|58->27|58->27|58->27|61->30|61->30|61->30|69->38|69->38|69->38|77->46|77->46|77->46|78->47|79->48|79->48|79->48|81->50|81->50|81->50|83->52|83->52|83->52|88->57|88->57|88->57|89->58|89->58|89->58|90->59|92->61|92->61|92->61|94->63|94->63|94->63|99->68|99->68|99->68|100->69|100->69|100->69|101->70|102->71|102->71|102->71|102->71|102->71|102->71|103->72|103->72|103->72|103->72|103->72|103->72|104->73|104->73|104->73|104->73|104->73|104->73|105->74|105->74|105->74|106->75|106->75|106->75|109->78|109->78|109->78|111->80|111->80|112->81|113->82|113->82|114->83|114->83|114->83|114->83|117->86|118->87|120->89|120->89|121->90|122->91|122->91|123->92|123->92|123->92|123->92|126->95|127->96|132->101|132->101|133->102|134->103|134->103|135->104|135->104|135->104|135->104|138->107|139->108|141->110|141->110|142->111|143->112|143->112|144->113|144->113|144->113|144->113|147->116|148->117|150->119|150->119|150->119|151->120|151->120|151->120|151->120|151->120|151->120|154->123|154->123|154->123|155->124|155->124|155->124|155->124|155->124|155->124|158->127|158->127|158->127|161->130|162->131|183->152|183->152|183->152|230->199|233->202|234->203|234->203|235->204|235->204|235->204|236->205|239->208|239->208|240->209|241->210|241->210|242->211|242->211|242->211|244->213|244->213|247->216|247->216|248->217|250->219|250->219|251->220|258->227|258->227|259->228|261->230|261->230|262->231|262->231|263->232|263->232|265->234|265->234|265->234|266->235|268->237|268->237|269->238|276->245|276->245|277->246|279->248|279->248|280->249|280->249|281->250|281->250|283->252|283->252|283->252|284->253|286->255|286->255|288->257|288->257|288->257|289->258|289->258|289->258|290->259|293->262|293->262|293->262|293->262|294->263|297->266|297->266|299->268|299->268|305->274
                  -- GENERATED --
              */
          