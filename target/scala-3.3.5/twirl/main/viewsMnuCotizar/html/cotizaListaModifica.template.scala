
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

object cotizaListaModifica extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listCotizaciones: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalContactoCliente()),format.raw/*9.25*/("""
	"""),_display_(/*10.3*/modalContactoProyecto()),format.raw/*10.26*/("""
	
	"""),format.raw/*12.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*13.4*/barraTitulo(mapDiccionario, "MODIFICAR/ELIMINAR COTIZACION","(solo cotizaciones pendientes de confirmar)")),format.raw/*13.110*/("""
		"""),format.raw/*14.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>Sucursal</TH>
							<TH>Comercial</TH>
							<TH>Solucion</TH>
							<TH>Nro.COTI</TH>
							<TH>EDIT</TH>
							<TH style="min-width:80px;">FECHA</TH>
							<TH>CLIENTE</TH>
							<TH>PROYECTO</TH>
							<TH>OBSERVACIONES</TH>
							<TH>DOC</TH>
							<TH>EDIT</TH>
							<TH>DEL</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*34.8*/for(lista1 <- listCotizaciones) yield /*34.39*/{_display_(Seq[Any](format.raw/*34.40*/("""
							"""),format.raw/*35.8*/("""<TR>
								<td style="text-align:left;">"""),_display_(/*36.39*/lista1/*36.45*/.get(7)),format.raw/*36.52*/("""</td>
								<td style="text-align:left;">"""),_display_(/*37.39*/lista1/*37.45*/.get(8)),format.raw/*37.52*/("""</td>
								<td style="text-align:left;">"""),_display_(/*38.39*/lista1/*38.45*/.get(9)),format.raw/*38.52*/("""</td>
								<td style="text-align:center;">
									<a href="#" onclick="verCotizacion('"""),_display_(/*40.47*/lista1/*40.53*/.get(0)),format.raw/*40.60*/("""')">
											<kbd style="background-color: rgb(90, 200, 245)">
												<font color="green">"""),_display_(/*42.34*/lista1/*42.40*/.get(1)),format.raw/*42.47*/("""</font>
											</kbd>
									</a>
								</td>
								<td  style="text-align:center;">
									<a href="#" onclick="cotizaModifica('"""),_display_(/*47.48*/lista1/*47.54*/.get(0)),format.raw/*47.61*/("""')">
										<kbd style="background-color: #73C6B6">edit</kbd>
									</a>
								</td>
								<td style="text-align:center;">
									<div style="display:none">"""),_display_(/*52.37*/lista1/*52.43*/.get(2)),format.raw/*52.50*/("""</div>
									"""),_display_(/*53.11*/utilities/*53.20*/.Fechas.DDMMAA(lista1.get(2))),format.raw/*53.49*/("""
								"""),format.raw/*54.9*/("""</td>
								<td style="text-align:left;"><a href="#" onclick="buscaCliente('"""),_display_(/*55.74*/lista1/*55.80*/.get(3)),format.raw/*55.87*/("""')">"""),_display_(/*55.92*/lista1/*55.98*/.get(3)),format.raw/*55.105*/("""</a></td>
								<td style="text-align:left;"><a href="#" onclick="buscaProyecto('"""),_display_(/*56.75*/lista1/*56.81*/.get(6)),format.raw/*56.88*/("""')">"""),_display_(/*56.93*/lista1/*56.99*/.get(6)),format.raw/*56.106*/("""</a></td>
								<td style="text-align:left;">"""),_display_(/*57.39*/lista1/*57.45*/.get(4)),format.raw/*57.52*/("""</td>
								<td style="text-align:center;">
									"""),_display_(if(lista1.get(5).equals("0"))/*59.40*/{_display_(Seq[Any](format.raw/*59.41*/("""
										"""),format.raw/*60.11*/("""--
									""")))}else/*61.15*/{_display_(Seq[Any](format.raw/*61.16*/("""
										"""),format.raw/*62.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*62.52*/lista1/*62.58*/.get(5)),format.raw/*62.65*/("""')">
											<kbd style="background-color: #7F8C8D">doc</kbd>
										</a>
									""")))}),format.raw/*65.11*/("""
								"""),format.raw/*66.9*/("""</td>
								<td  style="text-align:center;">
									<a href="#" onclick="cotizaModifica('"""),_display_(/*68.48*/lista1/*68.54*/.get(0)),format.raw/*68.61*/("""')">
										<kbd style="background-color: #73C6B6">edit</kbd>
									</a>
								</td>
								<td  style="text-align:center;">
									<a href="#" onclick= "eliminarCotizacion('"""),_display_(/*73.53*/lista1/*73.59*/.get(0)),format.raw/*73.66*/("""','"""),_display_(/*73.70*/lista1/*73.76*/.get(1)),format.raw/*73.83*/("""')">
										<kbd style="background-color: red">X</kbd>
									</a>
								</td>
							</TR>
			 			""")))}),format.raw/*78.9*/("""
					"""),format.raw/*79.6*/("""</tbody>
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
	
	<form class="formulario" id="formDescargaDocumento" method="post" action="/downloadPDF/">	
		<input type="hidden" id="descargaDocumento" name="nombreArchivo">
	</form>
	
	<form id="eliminarCotizacion" method="post" action="/cotizaElimina/">
		<input type="hidden" id="del_idCoti" name="id_cotizacion">
	</form>
	
	<form id="cotizaModifica" method="post" action="/cotizaModifica/">
		<input type="hidden" id="mod_idCoti" name="id_cotizacion">
	</form>

""")))}),format.raw/*125.2*/("""


"""),format.raw/*128.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*129.31*/("""{"""),format.raw/*129.32*/("""
		  """),format.raw/*130.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*130.36*/("""{"""),format.raw/*130.37*/("""
		    	"""),format.raw/*131.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 5, "desc" ]],
		    	"language": """),format.raw/*134.20*/("""{"""),format.raw/*134.21*/("""
		        	"""),format.raw/*135.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*136.11*/("""}"""),format.raw/*136.12*/("""
		  """),format.raw/*137.5*/("""}"""),format.raw/*137.6*/(""" """),format.raw/*137.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*139.2*/("""}"""),format.raw/*139.3*/(""");
	
	
	const verCotizacion = (id_cotizacion) => """),format.raw/*142.43*/("""{"""),format.raw/*142.44*/("""
		"""),format.raw/*143.3*/("""var formData = new FormData();
	  	formData.append('id_cotizacion',id_cotizacion);
        $.ajax("""),format.raw/*145.16*/("""{"""),format.raw/*145.17*/("""
            """),format.raw/*146.13*/("""url: "/verCotizacionAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*153.36*/("""{"""),format.raw/*153.37*/("""
	     		"""),format.raw/*154.9*/("""document.getElementById('mostrarLaCotizacion').innerHTML = respuesta;
	     		$('#modalVerCotizacion').modal('show');
	     	"""),format.raw/*156.8*/("""}"""),format.raw/*156.9*/("""
        """),format.raw/*157.9*/("""}"""),format.raw/*157.10*/(""");
	"""),format.raw/*158.2*/("""}"""),format.raw/*158.3*/("""
	
	"""),format.raw/*160.2*/("""const descargaDocumento = (nombreDOC) => """),format.raw/*160.43*/("""{"""),format.raw/*160.44*/("""
		  """),format.raw/*161.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*163.2*/("""}"""),format.raw/*163.3*/("""
	
	"""),format.raw/*165.2*/("""const eliminarCotizacion = (id_cotizacion, numero) => """),format.raw/*165.56*/("""{"""),format.raw/*165.57*/("""
		"""),format.raw/*166.3*/("""alertify.confirm("Esta seguro de eliminar la cotización nro: "+numero, function (e) """),format.raw/*166.87*/("""{"""),format.raw/*166.88*/("""
			"""),format.raw/*167.4*/("""if (e) """),format.raw/*167.11*/("""{"""),format.raw/*167.12*/("""
				"""),format.raw/*168.5*/("""$("#del_idCoti").val(id_cotizacion);
				document.getElementById("eliminarCotizacion").submit();
			"""),format.raw/*170.4*/("""}"""),format.raw/*170.5*/("""
		"""),format.raw/*171.3*/("""}"""),format.raw/*171.4*/(""");
	"""),format.raw/*172.2*/("""}"""),format.raw/*172.3*/("""
	
	"""),format.raw/*174.2*/("""const cotizaModifica = (id_cotizacion) => """),format.raw/*174.44*/("""{"""),format.raw/*174.45*/("""
				"""),format.raw/*175.5*/("""$("#mod_idCoti").val(id_cotizacion);
				document.getElementById("cotizaModifica").submit();
	"""),format.raw/*177.2*/("""}"""),format.raw/*177.3*/("""
	
	
	
"""),format.raw/*181.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listCotizaciones:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listCotizaciones)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listCotizaciones) => apply(mapDiccionario,mapPermiso,userMnu,listCotizaciones)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizar/cotizaListaModifica.scala.html
                  HASH: d666555e395ea76836c37d1d303d9d239166ff2d
                  MATRIX: 1037->1|1265->136|1298->144|1314->152|1353->154|1382->158|1450->206|1480->211|1522->233|1551->236|1595->259|1626->263|1703->314|1831->420|1861->423|2511->1047|2558->1078|2597->1079|2632->1087|2702->1130|2717->1136|2745->1143|2816->1187|2831->1193|2859->1200|2930->1244|2945->1250|2973->1257|3092->1349|3107->1355|3135->1362|3261->1461|3276->1467|3304->1474|3473->1616|3488->1622|3516->1629|3712->1798|3727->1804|3755->1811|3799->1828|3817->1837|3867->1866|3903->1875|4009->1954|4024->1960|4052->1967|4084->1972|4099->1978|4128->1985|4239->2069|4254->2075|4282->2082|4314->2087|4329->2093|4358->2100|4433->2148|4448->2154|4476->2161|4588->2246|4627->2247|4666->2258|4702->2275|4741->2276|4780->2287|4848->2328|4863->2334|4891->2341|5012->2431|5048->2440|5169->2534|5184->2540|5212->2547|5425->2733|5440->2739|5468->2746|5499->2750|5514->2756|5542->2763|5679->2870|5712->2876|7365->4498|7396->4501|7487->4563|7517->4564|7550->4569|7610->4600|7640->4601|7676->4609|7852->4756|7882->4757|7923->4769|8030->4847|8060->4848|8093->4853|8122->4854|8151->4855|8252->4928|8281->4929|8359->4978|8389->4979|8420->4982|8547->5080|8577->5081|8619->5094|8878->5324|8908->5325|8945->5334|9098->5459|9127->5460|9164->5469|9194->5470|9226->5474|9255->5475|9287->5479|9357->5520|9387->5521|9420->5526|9552->5630|9581->5631|9613->5635|9696->5689|9726->5690|9757->5693|9870->5777|9900->5778|9932->5782|9968->5789|9998->5790|10031->5795|10159->5895|10188->5896|10219->5899|10248->5900|10280->5904|10309->5905|10341->5909|10412->5951|10442->5952|10475->5957|10597->6051|10626->6052|10661->6059
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|41->10|41->10|43->12|44->13|44->13|45->14|65->34|65->34|65->34|66->35|67->36|67->36|67->36|68->37|68->37|68->37|69->38|69->38|69->38|71->40|71->40|71->40|73->42|73->42|73->42|78->47|78->47|78->47|83->52|83->52|83->52|84->53|84->53|84->53|85->54|86->55|86->55|86->55|86->55|86->55|86->55|87->56|87->56|87->56|87->56|87->56|87->56|88->57|88->57|88->57|90->59|90->59|91->60|92->61|92->61|93->62|93->62|93->62|93->62|96->65|97->66|99->68|99->68|99->68|104->73|104->73|104->73|104->73|104->73|104->73|109->78|110->79|156->125|159->128|160->129|160->129|161->130|161->130|161->130|162->131|165->134|165->134|166->135|167->136|167->136|168->137|168->137|168->137|170->139|170->139|173->142|173->142|174->143|176->145|176->145|177->146|184->153|184->153|185->154|187->156|187->156|188->157|188->157|189->158|189->158|191->160|191->160|191->160|192->161|194->163|194->163|196->165|196->165|196->165|197->166|197->166|197->166|198->167|198->167|198->167|199->168|201->170|201->170|202->171|202->171|203->172|203->172|205->174|205->174|205->174|206->175|208->177|208->177|212->181
                  -- GENERATED --
              */
          