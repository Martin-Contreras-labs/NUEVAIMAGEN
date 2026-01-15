
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

object cotiOdoListaModifica extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

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
		"""),_display_(/*13.4*/barraTitulo(mapDiccionario, "MODIFICAR/ELIMINAR COTIZACION ODO","(solo cotizaciones pendientes de confirmar)")),format.raw/*13.114*/("""
		"""),format.raw/*14.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
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
						"""),_display_(/*30.8*/for(lista1 <- listCotizaciones) yield /*30.39*/{_display_(Seq[Any](format.raw/*30.40*/("""
							"""),format.raw/*31.8*/("""<TR>
								<td style="text-align:center;">
									<a href="#" onclick="verCotizacion('"""),_display_(/*33.47*/lista1/*33.53*/.get(0)),format.raw/*33.60*/("""')">
											<kbd style="background-color: rgb(90, 200, 245)">
												<font color="green">"""),_display_(/*35.34*/lista1/*35.40*/.get(1)),format.raw/*35.47*/("""</font>
											</kbd>
									</a>
								</td>
								<td style="text-align:center;">
									<div style="display:none">"""),_display_(/*40.37*/lista1/*40.43*/.get(2)),format.raw/*40.50*/("""</div>
									"""),_display_(/*41.11*/utilities/*41.20*/.Fechas.DDMMAA(lista1.get(2))),format.raw/*41.49*/("""
								"""),format.raw/*42.9*/("""</td>
								<td style="text-align:left;"><a href="#" onclick="buscaCliente('"""),_display_(/*43.74*/lista1/*43.80*/.get(3)),format.raw/*43.87*/("""')">"""),_display_(/*43.92*/lista1/*43.98*/.get(3)),format.raw/*43.105*/("""</a></td>
								<td style="text-align:left;"><a href="#" onclick="buscaProyecto('"""),_display_(/*44.75*/lista1/*44.81*/.get(6)),format.raw/*44.88*/("""')">"""),_display_(/*44.93*/lista1/*44.99*/.get(6)),format.raw/*44.106*/("""</a></td>
								<td style="text-align:left;">"""),_display_(/*45.39*/lista1/*45.45*/.get(4)),format.raw/*45.52*/("""</td>
								<td style="text-align:center;">
									"""),_display_(if(lista1.get(5).equals("0"))/*47.40*/{_display_(Seq[Any](format.raw/*47.41*/("""
										"""),format.raw/*48.11*/("""--
									""")))}else/*49.15*/{_display_(Seq[Any](format.raw/*49.16*/("""
										"""),format.raw/*50.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*50.52*/lista1/*50.58*/.get(5)),format.raw/*50.65*/("""')">
											<kbd style="background-color: #7F8C8D">doc</kbd>
										</a>
									""")))}),format.raw/*53.11*/("""
								"""),format.raw/*54.9*/("""</td>
								<td  style="text-align:center;">
									<a href="#" onclick="cotizaModifica('"""),_display_(/*56.48*/lista1/*56.54*/.get(0)),format.raw/*56.61*/("""')">
										<kbd style="background-color: #73C6B6">edit</kbd>
									</a>
								</td>
								<td  style="text-align:center;">
									<a href="#" onclick= "eliminarCotizacion('"""),_display_(/*61.53*/lista1/*61.59*/.get(0)),format.raw/*61.66*/("""','"""),_display_(/*61.70*/lista1/*61.76*/.get(1)),format.raw/*61.83*/("""')">
										<kbd style="background-color: red">X</kbd>
									</a>
								</td>
							</TR>
			 			""")))}),format.raw/*66.9*/("""
					"""),format.raw/*67.6*/("""</tbody>
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
					<h5 class='modal-title'>COTIZACION ODO</h5>
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
	
	<form id="eliminarCotizacion" method="post" action="/routes2/cotiOdoElimina/">
		<input type="hidden" id="del_idCoti" name="id_cotiOdo">
	</form>
	
	<form id="cotizaModifica" method="post" action="/routes2/cotiOdoModifica/">
		<input type="hidden" id="mod_idCoti" name="id_cotiOdo">
	</form>

""")))}),format.raw/*113.2*/("""


"""),format.raw/*116.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*117.31*/("""{"""),format.raw/*117.32*/("""
		  """),format.raw/*118.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*118.36*/("""{"""),format.raw/*118.37*/("""
		    	"""),format.raw/*119.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 1, "desc" ],[ 0, "desc" ]],
		    	"language": """),format.raw/*122.20*/("""{"""),format.raw/*122.21*/("""
		        	"""),format.raw/*123.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*124.11*/("""}"""),format.raw/*124.12*/("""
		  """),format.raw/*125.5*/("""}"""),format.raw/*125.6*/(""" """),format.raw/*125.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*127.2*/("""}"""),format.raw/*127.3*/(""");
	
	
	const verCotizacion = (id_cotiOdo) => """),format.raw/*130.40*/("""{"""),format.raw/*130.41*/("""
		"""),format.raw/*131.3*/("""var formData = new FormData();
	  	formData.append('id_cotiOdo',id_cotiOdo);
        $.ajax("""),format.raw/*133.16*/("""{"""),format.raw/*133.17*/("""
            """),format.raw/*134.13*/("""url: "/routes2/verCotiOdoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*141.36*/("""{"""),format.raw/*141.37*/("""
	     		"""),format.raw/*142.9*/("""document.getElementById('mostrarLaCotizacion').innerHTML = respuesta;
	     		$('#modalVerCotizacion').modal('show');
	     	"""),format.raw/*144.8*/("""}"""),format.raw/*144.9*/("""
        """),format.raw/*145.9*/("""}"""),format.raw/*145.10*/(""");
	"""),format.raw/*146.2*/("""}"""),format.raw/*146.3*/("""
	
	"""),format.raw/*148.2*/("""const descargaDocumento = (nombreDOC) => """),format.raw/*148.43*/("""{"""),format.raw/*148.44*/("""
		  """),format.raw/*149.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*151.2*/("""}"""),format.raw/*151.3*/("""
	
	"""),format.raw/*153.2*/("""const eliminarCotizacion = (id_cotiOdo, numero) => """),format.raw/*153.53*/("""{"""),format.raw/*153.54*/("""
		"""),format.raw/*154.3*/("""alertify.confirm("Esta seguro de eliminar la cotización nro: "+numero, function (e) """),format.raw/*154.87*/("""{"""),format.raw/*154.88*/("""
			"""),format.raw/*155.4*/("""if (e) """),format.raw/*155.11*/("""{"""),format.raw/*155.12*/("""
				"""),format.raw/*156.5*/("""$("#del_idCoti").val(id_cotiOdo);
				document.getElementById("eliminarCotizacion").submit();
			"""),format.raw/*158.4*/("""}"""),format.raw/*158.5*/("""
		"""),format.raw/*159.3*/("""}"""),format.raw/*159.4*/(""");
	"""),format.raw/*160.2*/("""}"""),format.raw/*160.3*/("""
	
	"""),format.raw/*162.2*/("""const cotizaModifica = (id_cotiOdo) => """),format.raw/*162.41*/("""{"""),format.raw/*162.42*/("""
				"""),format.raw/*163.5*/("""$("#mod_idCoti").val(id_cotiOdo);
				document.getElementById("cotizaModifica").submit();
	"""),format.raw/*165.2*/("""}"""),format.raw/*165.3*/("""
	
	
	
"""),format.raw/*169.1*/("""</script>




		
		
	
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
                  SOURCE: app/viewsMnuCotiOdo/cotiOdoListaModifica.scala.html
                  HASH: 14e25ff8b5933014b3d114264c67677186fb2084
                  MATRIX: 1038->1|1266->136|1299->144|1315->152|1354->154|1383->158|1451->206|1481->211|1523->233|1552->236|1596->259|1627->263|1704->314|1836->424|1866->427|2419->954|2466->985|2505->986|2540->994|2658->1085|2673->1091|2701->1098|2827->1197|2842->1203|2870->1210|3027->1340|3042->1346|3070->1353|3114->1370|3132->1379|3182->1408|3218->1417|3324->1496|3339->1502|3367->1509|3399->1514|3414->1520|3443->1527|3554->1611|3569->1617|3597->1624|3629->1629|3644->1635|3673->1642|3748->1690|3763->1696|3791->1703|3903->1788|3942->1789|3981->1800|4017->1817|4056->1818|4095->1829|4163->1870|4178->1876|4206->1883|4327->1973|4363->1982|4484->2076|4499->2082|4527->2089|4740->2275|4755->2281|4783->2288|4814->2292|4829->2298|4857->2305|4994->2412|5027->2418|6696->4056|6727->4059|6818->4121|6848->4122|6881->4127|6941->4158|6971->4159|7007->4167|7197->4328|7227->4329|7268->4341|7375->4419|7405->4420|7438->4425|7467->4426|7496->4427|7597->4500|7626->4501|7701->4547|7731->4548|7762->4551|7883->4643|7913->4644|7955->4657|8219->4892|8249->4893|8286->4902|8439->5027|8468->5028|8505->5037|8535->5038|8567->5042|8596->5043|8628->5047|8698->5088|8728->5089|8761->5094|8893->5198|8922->5199|8954->5203|9034->5254|9064->5255|9095->5258|9208->5342|9238->5343|9270->5347|9306->5354|9336->5355|9369->5360|9494->5457|9523->5458|9554->5461|9583->5462|9615->5466|9644->5467|9676->5471|9744->5510|9774->5511|9807->5516|9926->5607|9955->5608|9990->5615
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|41->10|41->10|43->12|44->13|44->13|45->14|61->30|61->30|61->30|62->31|64->33|64->33|64->33|66->35|66->35|66->35|71->40|71->40|71->40|72->41|72->41|72->41|73->42|74->43|74->43|74->43|74->43|74->43|74->43|75->44|75->44|75->44|75->44|75->44|75->44|76->45|76->45|76->45|78->47|78->47|79->48|80->49|80->49|81->50|81->50|81->50|81->50|84->53|85->54|87->56|87->56|87->56|92->61|92->61|92->61|92->61|92->61|92->61|97->66|98->67|144->113|147->116|148->117|148->117|149->118|149->118|149->118|150->119|153->122|153->122|154->123|155->124|155->124|156->125|156->125|156->125|158->127|158->127|161->130|161->130|162->131|164->133|164->133|165->134|172->141|172->141|173->142|175->144|175->144|176->145|176->145|177->146|177->146|179->148|179->148|179->148|180->149|182->151|182->151|184->153|184->153|184->153|185->154|185->154|185->154|186->155|186->155|186->155|187->156|189->158|189->158|190->159|190->159|191->160|191->160|193->162|193->162|193->162|194->163|196->165|196->165|200->169
                  -- GENERATED --
              */
          