
package viewsMnuCotizarSelect.html

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

object cotizaListaModificaSel extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

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
							<TH>Nro.COTI</TH>
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
						"""),_display_(/*32.8*/for(lista1 <- listCotizaciones) yield /*32.39*/{_display_(Seq[Any](format.raw/*32.40*/("""
							"""),format.raw/*33.8*/("""<TR>
								<td style="text-align:left;">"""),_display_(/*34.39*/lista1/*34.45*/.get(7)),format.raw/*34.52*/("""</td>
								<td style="text-align:left;">"""),_display_(/*35.39*/lista1/*35.45*/.get(8)),format.raw/*35.52*/("""</td>
								<td style="text-align:center;">
									<a href="#" onclick="verCotizacion('"""),_display_(/*37.47*/lista1/*37.53*/.get(0)),format.raw/*37.60*/("""')">
											<kbd style="background-color: rgb(90, 200, 245)">
												<font color="green">"""),_display_(/*39.34*/lista1/*39.40*/.get(1)),format.raw/*39.47*/("""</font>
											</kbd>
									</a>
								</td>
								<td style="text-align:center;">
									<div style="display:none">"""),_display_(/*44.37*/lista1/*44.43*/.get(2)),format.raw/*44.50*/("""</div>
									"""),_display_(/*45.11*/utilities/*45.20*/.Fechas.DDMMAA(lista1.get(2))),format.raw/*45.49*/("""
								"""),format.raw/*46.9*/("""</td>
								<td style="text-align:left;"><a href="#" onclick="buscaCliente('"""),_display_(/*47.74*/lista1/*47.80*/.get(3)),format.raw/*47.87*/("""')">"""),_display_(/*47.92*/lista1/*47.98*/.get(3)),format.raw/*47.105*/("""</a></td>
								<td style="text-align:left;"><a href="#" onclick="buscaProyecto('"""),_display_(/*48.75*/lista1/*48.81*/.get(6)),format.raw/*48.88*/("""')">"""),_display_(/*48.93*/lista1/*48.99*/.get(6)),format.raw/*48.106*/("""</a></td>
								<td style="text-align:left;">"""),_display_(/*49.39*/lista1/*49.45*/.get(4)),format.raw/*49.52*/("""</td>
								<td style="text-align:center;">
									"""),_display_(if(lista1.get(5).equals("0"))/*51.40*/{_display_(Seq[Any](format.raw/*51.41*/("""
										"""),format.raw/*52.11*/("""--
									""")))}else/*53.15*/{_display_(Seq[Any](format.raw/*53.16*/("""
										"""),format.raw/*54.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*54.52*/lista1/*54.58*/.get(5)),format.raw/*54.65*/("""')">
											<kbd style="background-color: #7F8C8D">doc</kbd>
										</a>
									""")))}),format.raw/*57.11*/("""
								"""),format.raw/*58.9*/("""</td>
								<td  style="text-align:center;">
									<a href="#" onclick="cotizaModifica('"""),_display_(/*60.48*/lista1/*60.54*/.get(0)),format.raw/*60.61*/("""')">
										<kbd style="background-color: #73C6B6">edit</kbd>
									</a>
								</td>
								<td  style="text-align:center;">
									<a href="#" onclick= "eliminarCotizacion('"""),_display_(/*65.53*/lista1/*65.59*/.get(0)),format.raw/*65.66*/("""','"""),_display_(/*65.70*/lista1/*65.76*/.get(1)),format.raw/*65.83*/("""')">
										<kbd style="background-color: red">X</kbd>
									</a>
								</td>
							</TR>
			 			""")))}),format.raw/*70.9*/("""
					"""),format.raw/*71.6*/("""</tbody>
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
	
	<form id="cotizaModifica" method="post" action="/routes2/cotizaModificaSel/">
		<input type="hidden" id="mod_idCoti" name="id_cotizacion">
	</form>

""")))}),format.raw/*117.2*/("""


"""),format.raw/*120.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*121.31*/("""{"""),format.raw/*121.32*/("""
		  """),format.raw/*122.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*122.36*/("""{"""),format.raw/*122.37*/("""
		    	"""),format.raw/*123.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 3, "desc" ],[ 2, "desc" ],[ 0, "asc" ],[ 1, "asc" ]],
		    	"language": """),format.raw/*126.20*/("""{"""),format.raw/*126.21*/("""
		        	"""),format.raw/*127.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*128.11*/("""}"""),format.raw/*128.12*/("""
		  """),format.raw/*129.5*/("""}"""),format.raw/*129.6*/(""" """),format.raw/*129.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*131.2*/("""}"""),format.raw/*131.3*/(""");
	
	
	const verCotizacion = (id_cotizacion) => """),format.raw/*134.43*/("""{"""),format.raw/*134.44*/("""
		"""),format.raw/*135.3*/("""var formData = new FormData();
	  	formData.append('id_cotizacion',id_cotizacion);
        $.ajax("""),format.raw/*137.16*/("""{"""),format.raw/*137.17*/("""
            """),format.raw/*138.13*/("""url: "/verCotizacionAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*145.36*/("""{"""),format.raw/*145.37*/("""
	     		"""),format.raw/*146.9*/("""document.getElementById('mostrarLaCotizacion').innerHTML = respuesta;
	     		$('#modalVerCotizacion').modal('show');
	     	"""),format.raw/*148.8*/("""}"""),format.raw/*148.9*/("""
        """),format.raw/*149.9*/("""}"""),format.raw/*149.10*/(""");
	"""),format.raw/*150.2*/("""}"""),format.raw/*150.3*/("""
	
	"""),format.raw/*152.2*/("""const descargaDocumento = (nombreDOC) => """),format.raw/*152.43*/("""{"""),format.raw/*152.44*/("""
		  """),format.raw/*153.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*155.2*/("""}"""),format.raw/*155.3*/("""
	
	"""),format.raw/*157.2*/("""const eliminarCotizacion = (id_cotizacion, numero) => """),format.raw/*157.56*/("""{"""),format.raw/*157.57*/("""
		"""),format.raw/*158.3*/("""alertify.confirm("Esta seguro de eliminar la cotización nro: "+numero, function (e) """),format.raw/*158.87*/("""{"""),format.raw/*158.88*/("""
			"""),format.raw/*159.4*/("""if (e) """),format.raw/*159.11*/("""{"""),format.raw/*159.12*/("""
				"""),format.raw/*160.5*/("""$("#del_idCoti").val(id_cotizacion);
				document.getElementById("eliminarCotizacion").submit();
			"""),format.raw/*162.4*/("""}"""),format.raw/*162.5*/("""
		"""),format.raw/*163.3*/("""}"""),format.raw/*163.4*/(""");
	"""),format.raw/*164.2*/("""}"""),format.raw/*164.3*/("""
	
	"""),format.raw/*166.2*/("""const cotizaModifica = (id_cotizacion) => """),format.raw/*166.44*/("""{"""),format.raw/*166.45*/("""
				"""),format.raw/*167.5*/("""$("#mod_idCoti").val(id_cotizacion);
				document.getElementById("cotizaModifica").submit();
	"""),format.raw/*169.2*/("""}"""),format.raw/*169.3*/("""
	
	
	
"""),format.raw/*173.1*/("""</script>




		
		
	
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
                  SOURCE: app/viewsMnuCotizarSelect/cotizaListaModificaSel.scala.html
                  HASH: a1d7f28a13e8a855689b36375e19a1d974e6c22d
                  MATRIX: 1046->1|1274->136|1307->144|1323->152|1362->154|1391->158|1459->206|1489->211|1531->233|1560->236|1604->259|1635->263|1712->314|1840->420|1870->423|2474->1001|2521->1032|2560->1033|2595->1041|2665->1084|2680->1090|2708->1097|2779->1141|2794->1147|2822->1154|2941->1246|2956->1252|2984->1259|3110->1358|3125->1364|3153->1371|3310->1501|3325->1507|3353->1514|3397->1531|3415->1540|3465->1569|3501->1578|3607->1657|3622->1663|3650->1670|3682->1675|3697->1681|3726->1688|3837->1772|3852->1778|3880->1785|3912->1790|3927->1796|3956->1803|4031->1851|4046->1857|4074->1864|4186->1949|4225->1950|4264->1961|4300->1978|4339->1979|4378->1990|4446->2031|4461->2037|4489->2044|4610->2134|4646->2143|4767->2237|4782->2243|4810->2250|5023->2436|5038->2442|5066->2449|5097->2453|5112->2459|5140->2466|5277->2573|5310->2579|6974->4212|7005->4215|7096->4277|7126->4278|7159->4283|7219->4314|7249->4315|7285->4323|7501->4510|7531->4511|7572->4523|7679->4601|7709->4602|7742->4607|7771->4608|7800->4609|7901->4682|7930->4683|8008->4732|8038->4733|8069->4736|8196->4834|8226->4835|8268->4848|8527->5078|8557->5079|8594->5088|8747->5213|8776->5214|8813->5223|8843->5224|8875->5228|8904->5229|8936->5233|9006->5274|9036->5275|9069->5280|9201->5384|9230->5385|9262->5389|9345->5443|9375->5444|9406->5447|9519->5531|9549->5532|9581->5536|9617->5543|9647->5544|9680->5549|9808->5649|9837->5650|9868->5653|9897->5654|9929->5658|9958->5659|9990->5663|10061->5705|10091->5706|10124->5711|10246->5805|10275->5806|10310->5813
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|41->10|41->10|43->12|44->13|44->13|45->14|63->32|63->32|63->32|64->33|65->34|65->34|65->34|66->35|66->35|66->35|68->37|68->37|68->37|70->39|70->39|70->39|75->44|75->44|75->44|76->45|76->45|76->45|77->46|78->47|78->47|78->47|78->47|78->47|78->47|79->48|79->48|79->48|79->48|79->48|79->48|80->49|80->49|80->49|82->51|82->51|83->52|84->53|84->53|85->54|85->54|85->54|85->54|88->57|89->58|91->60|91->60|91->60|96->65|96->65|96->65|96->65|96->65|96->65|101->70|102->71|148->117|151->120|152->121|152->121|153->122|153->122|153->122|154->123|157->126|157->126|158->127|159->128|159->128|160->129|160->129|160->129|162->131|162->131|165->134|165->134|166->135|168->137|168->137|169->138|176->145|176->145|177->146|179->148|179->148|180->149|180->149|181->150|181->150|183->152|183->152|183->152|184->153|186->155|186->155|188->157|188->157|188->157|189->158|189->158|189->158|190->159|190->159|190->159|191->160|193->162|193->162|194->163|194->163|195->164|195->164|197->166|197->166|197->166|198->167|200->169|200->169|204->173
                  -- GENERATED --
              */
          