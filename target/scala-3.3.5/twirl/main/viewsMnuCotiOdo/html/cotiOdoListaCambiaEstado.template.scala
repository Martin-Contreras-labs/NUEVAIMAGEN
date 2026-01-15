
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

object cotiOdoListaCambiaEstado extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],List[tables.CotizaEstado],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listCotizaciones: List[List[String]], listEstados: List[tables.CotizaEstado]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalContactoCliente()),format.raw/*9.25*/("""
	"""),_display_(/*10.3*/modalContactoProyecto()),format.raw/*10.26*/("""
	
	"""),format.raw/*12.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*13.4*/barraTitulo(mapDiccionario, "CAMBIAR EL ESTADO DE COTIZACIONES ODO","(solo cotizaciones pendientes de confirmar)")),format.raw/*13.118*/("""
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
							<TH>ESTADO</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*29.8*/for(lista1 <- listCotizaciones) yield /*29.39*/{_display_(Seq[Any](format.raw/*29.40*/("""
							"""),format.raw/*30.8*/("""<TR>
								<td style="text-align:center;">
									<a href="#" onclick="verCotizacion('"""),_display_(/*32.47*/lista1/*32.53*/.get(0)),format.raw/*32.60*/("""')">
											<kbd style="background-color: rgb(90, 200, 245)">
												<font color="green">"""),_display_(/*34.34*/lista1/*34.40*/.get(1)),format.raw/*34.47*/("""</font>
											</kbd>
									</a>
								</td>
								<td style="text-align:center;">
									<div style="display:none">"""),_display_(/*39.37*/lista1/*39.43*/.get(2)),format.raw/*39.50*/("""</div>
									"""),_display_(/*40.11*/utilities/*40.20*/.Fechas.DDMMAA(lista1.get(2))),format.raw/*40.49*/("""
								"""),format.raw/*41.9*/("""</td>
								<td style="text-align:left;"><a href="#" onclick="buscaCliente('"""),_display_(/*42.74*/lista1/*42.80*/.get(3)),format.raw/*42.87*/("""')">"""),_display_(/*42.92*/lista1/*42.98*/.get(3)),format.raw/*42.105*/("""</a></td>
								<td style="text-align:left;"><a href="#" onclick="buscaProyecto('"""),_display_(/*43.75*/lista1/*43.81*/.get(6)),format.raw/*43.88*/("""')">"""),_display_(/*43.93*/lista1/*43.99*/.get(6)),format.raw/*43.106*/("""</a></td>
								<td style="text-align:left;">"""),_display_(/*44.39*/lista1/*44.45*/.get(4)),format.raw/*44.52*/("""</td>
								<td style="text-align:center;">
									"""),_display_(if(lista1.get(5).equals("0"))/*46.40*/{_display_(Seq[Any](format.raw/*46.41*/("""
										"""),format.raw/*47.11*/("""--
									""")))}else/*48.15*/{_display_(Seq[Any](format.raw/*48.16*/("""
										"""),format.raw/*49.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*49.52*/lista1/*49.58*/.get(5)),format.raw/*49.65*/("""')">
											<kbd style="background-color: #7F8C8D">doc</kbd>
										</a>
									""")))}),format.raw/*52.11*/("""
								"""),format.raw/*53.9*/("""</td>
								<td  style="text-align:center;">
									<select class="custom-select height23px font10px" onchange="cambiarEstado('"""),_display_(/*55.86*/lista1/*55.92*/.get(0)),format.raw/*55.99*/("""', value)">
										<option value=""""),_display_(/*56.27*/lista1/*56.33*/.get(7)),format.raw/*56.40*/("""">"""),_display_(/*56.43*/lista1/*56.49*/.get(8)),format.raw/*56.56*/("""</option>
										"""),_display_(/*57.12*/for(lista <- listEstados) yield /*57.37*/{_display_(Seq[Any](format.raw/*57.38*/("""
											"""),format.raw/*58.12*/("""<option value=""""),_display_(/*58.28*/lista/*58.33*/.id),format.raw/*58.36*/("""">"""),_display_(/*58.39*/lista/*58.44*/.estado),format.raw/*58.51*/("""</option>
										""")))}),format.raw/*59.12*/("""
									"""),format.raw/*60.10*/("""</select>
								</td>
							</TR>
			 			""")))}),format.raw/*63.9*/("""
					"""),format.raw/*64.6*/("""</tbody>
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
	
	

""")))}),format.raw/*104.2*/("""


"""),format.raw/*107.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*108.31*/("""{"""),format.raw/*108.32*/("""
		  """),format.raw/*109.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*109.36*/("""{"""),format.raw/*109.37*/("""
		    	"""),format.raw/*110.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 1, "desc" ],[ 0, "desc" ]],
		    	"language": """),format.raw/*113.20*/("""{"""),format.raw/*113.21*/("""
		        	"""),format.raw/*114.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*115.11*/("""}"""),format.raw/*115.12*/("""
		  """),format.raw/*116.5*/("""}"""),format.raw/*116.6*/(""" """),format.raw/*116.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*118.2*/("""}"""),format.raw/*118.3*/(""");
	
	
	const verCotizacion = (id_cotiOdo) => """),format.raw/*121.40*/("""{"""),format.raw/*121.41*/("""
		"""),format.raw/*122.3*/("""var formData = new FormData();
	  	formData.append('id_cotiOdo',id_cotiOdo);
        $.ajax("""),format.raw/*124.16*/("""{"""),format.raw/*124.17*/("""
            """),format.raw/*125.13*/("""url: "/routes2/verCotiOdoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*132.36*/("""{"""),format.raw/*132.37*/("""
	     		"""),format.raw/*133.9*/("""document.getElementById('mostrarLaCotizacion').innerHTML = respuesta;
	     		$('#modalVerCotizacion').modal('show');
	     	"""),format.raw/*135.8*/("""}"""),format.raw/*135.9*/("""
        """),format.raw/*136.9*/("""}"""),format.raw/*136.10*/(""");
	"""),format.raw/*137.2*/("""}"""),format.raw/*137.3*/("""
	
	"""),format.raw/*139.2*/("""const descargaDocumento = (nombreDOC) => """),format.raw/*139.43*/("""{"""),format.raw/*139.44*/("""
		  """),format.raw/*140.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*142.2*/("""}"""),format.raw/*142.3*/("""
	
	"""),format.raw/*144.2*/("""const cambiarEstado = (id_cotiOdo, id_cotizaEstado) => """),format.raw/*144.57*/("""{"""),format.raw/*144.58*/("""
		"""),format.raw/*145.3*/("""var formData = new FormData();
	  	formData.append('id_cotiOdo',id_cotiOdo);
	  	formData.append('id_cotizaEstado',id_cotizaEstado);
        $.ajax("""),format.raw/*148.16*/("""{"""),format.raw/*148.17*/("""
            """),format.raw/*149.13*/("""url: "/routes2/cambiarCotiOdoEstadoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*156.36*/("""{"""),format.raw/*156.37*/("""}"""),format.raw/*156.38*/("""
        """),format.raw/*157.9*/("""}"""),format.raw/*157.10*/(""");
	"""),format.raw/*158.2*/("""}"""),format.raw/*158.3*/("""
		
	
	
"""),format.raw/*162.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listCotizaciones:List[List[String]],listEstados:List[tables.CotizaEstado]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listCotizaciones,listEstados)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],List[tables.CotizaEstado]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listCotizaciones,listEstados) => apply(mapDiccionario,mapPermiso,userMnu,listCotizaciones,listEstados)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotiOdo/cotiOdoListaCambiaEstado.scala.html
                  HASH: 7ac8f2d90e905d1bdcffaac2bce2d4cbedf0f79e
                  MATRIX: 1068->1|1336->176|1369->184|1385->192|1424->194|1453->198|1521->246|1551->251|1593->273|1622->276|1666->299|1697->303|1774->354|1910->468|1940->471|2475->980|2522->1011|2561->1012|2596->1020|2714->1111|2729->1117|2757->1124|2883->1223|2898->1229|2926->1236|3083->1366|3098->1372|3126->1379|3170->1396|3188->1405|3238->1434|3274->1443|3380->1522|3395->1528|3423->1535|3455->1540|3470->1546|3499->1553|3610->1637|3625->1643|3653->1650|3685->1655|3700->1661|3729->1668|3804->1716|3819->1722|3847->1729|3959->1814|3998->1815|4037->1826|4073->1843|4112->1844|4151->1855|4219->1896|4234->1902|4262->1909|4383->1999|4419->2008|4578->2140|4593->2146|4621->2153|4686->2191|4701->2197|4729->2204|4759->2207|4774->2213|4802->2220|4850->2241|4891->2266|4930->2267|4970->2279|5013->2295|5027->2300|5051->2303|5081->2306|5095->2311|5123->2318|5175->2339|5213->2349|5288->2394|5321->2400|6695->3743|6726->3746|6817->3808|6847->3809|6880->3814|6940->3845|6970->3846|7006->3854|7196->4015|7226->4016|7267->4028|7374->4106|7404->4107|7437->4112|7466->4113|7495->4114|7596->4187|7625->4188|7700->4234|7730->4235|7761->4238|7882->4330|7912->4331|7954->4344|8218->4579|8248->4580|8285->4589|8438->4714|8467->4715|8504->4724|8534->4725|8566->4729|8595->4730|8627->4734|8697->4775|8727->4776|8760->4781|8892->4885|8921->4886|8953->4890|9037->4945|9067->4946|9098->4949|9275->5097|9305->5098|9347->5111|9621->5356|9651->5357|9681->5358|9718->5367|9748->5368|9780->5372|9809->5373|9845->5381
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|41->10|41->10|43->12|44->13|44->13|45->14|60->29|60->29|60->29|61->30|63->32|63->32|63->32|65->34|65->34|65->34|70->39|70->39|70->39|71->40|71->40|71->40|72->41|73->42|73->42|73->42|73->42|73->42|73->42|74->43|74->43|74->43|74->43|74->43|74->43|75->44|75->44|75->44|77->46|77->46|78->47|79->48|79->48|80->49|80->49|80->49|80->49|83->52|84->53|86->55|86->55|86->55|87->56|87->56|87->56|87->56|87->56|87->56|88->57|88->57|88->57|89->58|89->58|89->58|89->58|89->58|89->58|89->58|90->59|91->60|94->63|95->64|135->104|138->107|139->108|139->108|140->109|140->109|140->109|141->110|144->113|144->113|145->114|146->115|146->115|147->116|147->116|147->116|149->118|149->118|152->121|152->121|153->122|155->124|155->124|156->125|163->132|163->132|164->133|166->135|166->135|167->136|167->136|168->137|168->137|170->139|170->139|170->139|171->140|173->142|173->142|175->144|175->144|175->144|176->145|179->148|179->148|180->149|187->156|187->156|187->156|188->157|188->157|189->158|189->158|193->162
                  -- GENERATED --
              */
          