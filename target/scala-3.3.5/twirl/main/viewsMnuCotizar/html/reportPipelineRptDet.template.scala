
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

object reportPipelineRptDet extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template11[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String,String,String,String,Long,Long,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
detalle: List[List[String]], fechaDe: String, fechaA: String, tituloSucursal: String,
desdeAAMMDD: String, hastaAAMMDD: String, id_sucursal: Long, id_comercial: Long):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.1*/("""
    	
"""),_display_(/*6.2*/main("")/*6.10*/ {_display_(Seq[Any](format.raw/*6.12*/("""

	"""),_display_(/*8.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*8.51*/("""
	
	"""),_display_(/*10.3*/modalContactoCliente()),format.raw/*10.25*/("""
	"""),_display_(/*11.3*/modalContactoProyecto()),format.raw/*11.26*/("""
	
	"""),format.raw/*13.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*14.4*/barraTitulo(mapDiccionario, "REPORTE PIPELINE DETALLADO"+tituloSucursal,"período movil: de "+fechaDe+" a "+fechaA)),format.raw/*14.118*/("""
		"""),format.raw/*15.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">
			
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
			
			<form id="excel" class="formulario" method="post" action="/routes2/reportPipelineRptDetExcel/">
				<input type="hidden" name="desdeAAMMDD" value=""""),_display_(/*40.53*/desdeAAMMDD),format.raw/*40.64*/("""">
				<input type="hidden" name="hastaAAMMDD" value=""""),_display_(/*41.53*/hastaAAMMDD),format.raw/*41.64*/("""">
				<input type="hidden" name="id_sucursal" value=""""),_display_(/*42.53*/id_sucursal),format.raw/*42.64*/("""">
				<input type="hidden" name="id_comercial" value=""""),_display_(/*43.54*/id_comercial),format.raw/*43.66*/("""">
			</form>
			
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>FECHA<br>COTI</TH>
							<TH>NRO<br>COTI</TH>
							<TH>SUCURSAL</TH>
							<TH>COMERCIAL</TH>
							<TH>SOLUCION</TH>
							<TH>CLIENTE</TH>
							<TH>PROYECTO</TH>
							<TH>ESTADO</TH>
							<TH>FECHA<br>"""),_display_(/*57.22*/mapDiccionario/*57.36*/.get("OT")),format.raw/*57.46*/("""</TH>
							<TH>NRO<br>"""),_display_(/*58.20*/mapDiccionario/*58.34*/.get("OT")),format.raw/*58.44*/("""</TH>
							<TH>TOTAL<BR>NETO</TH>
							<TH>PESO<BR>KG</TH>
							<TH>FECHA<BR>PROBABLE</TH>
							<TH>DIBUJANTE<BR>PROYECTISTA</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*66.8*/for(lista1 <- detalle) yield /*66.30*/{_display_(Seq[Any](format.raw/*66.31*/("""
							"""),format.raw/*67.8*/("""<TR>
								<td style="text-align:center;">
									<div style='display:none'>"""),_display_(/*69.37*/lista1/*69.43*/.get(0)),format.raw/*69.50*/("""</div>
									"""),_display_(/*70.11*/utilities/*70.20*/.Fechas.DDMMAA(lista1.get(0))),format.raw/*70.49*/("""
								"""),format.raw/*71.9*/("""</td>
								<td style="text-align:center;">
									<a href="#" onclick="verCotizacion('"""),_display_(/*73.47*/lista1/*73.53*/.get(18)),format.raw/*73.61*/("""')">
										<kbd style="background-color: rgb(90, 200, 245); color: black">
											"""),_display_(/*75.13*/lista1/*75.19*/.get(16)),format.raw/*75.27*/("""
										"""),format.raw/*76.11*/("""</kbd>
									</a>
								</td>
								<td style="text-align:left;">"""),_display_(/*79.39*/lista1/*79.45*/.get(1)),format.raw/*79.52*/("""</td>
								<td style="text-align:left;">"""),_display_(/*80.39*/lista1/*80.45*/.get(6)),format.raw/*80.52*/("""</td>
								<td style="text-align:left;">"""),_display_(/*81.39*/lista1/*81.45*/.get(4)),format.raw/*81.52*/("""</td>
								<td style="text-align:left;">"""),_display_(/*82.39*/lista1/*82.45*/.get(2)),format.raw/*82.52*/("""</td>
								<td style="text-align:left;">"""),_display_(/*83.39*/lista1/*83.45*/.get(3)),format.raw/*83.52*/("""</td>
								<td style="text-align:left;">"""),_display_(/*84.39*/lista1/*84.45*/.get(5)),format.raw/*84.52*/("""</td>
								<td style="text-align:center;">
									<div style='display:none'>"""),_display_(/*86.37*/lista1/*86.43*/.get(15)),format.raw/*86.51*/("""</div>
									"""),_display_(/*87.11*/utilities/*87.20*/.Fechas.DDMMAA(lista1.get(15))),format.raw/*87.50*/("""
								"""),format.raw/*88.9*/("""</td>
								<td style="text-align:center;">
									"""),_display_(if( ! lista1.get(19).equals("0"))/*90.44*/{_display_(Seq[Any](format.raw/*90.45*/("""
										"""),format.raw/*91.11*/("""<a href="#" onclick="verOt('"""),_display_(/*91.40*/lista1/*91.46*/.get(19)),format.raw/*91.54*/("""')">
											<kbd style="background-color: rgb(90, 200, 245); color: black">
												"""),_display_(/*93.14*/lista1/*93.20*/.get(17)),format.raw/*93.28*/("""
											"""),format.raw/*94.12*/("""</kbd>
										</a>
									""")))} else {null} ),format.raw/*96.11*/("""
								"""),format.raw/*97.9*/("""</td>
								<td style="text-align:right;">"""),_display_(/*98.40*/lista1/*98.46*/.get(7)),format.raw/*98.53*/("""</td>
								<td style="text-align:right;">"""),_display_(/*99.40*/lista1/*99.46*/.get(14)),format.raw/*99.54*/("""</td>
								<td style="text-align:center;">"""),_display_(/*100.41*/lista1/*100.47*/.get(20)),format.raw/*100.55*/("""</td>
								<td style="text-align:left;">"""),_display_(/*101.39*/lista1/*101.45*/.get(21)),format.raw/*101.53*/("""</td>
							</TR>
			 			""")))}),format.raw/*103.9*/("""
					"""),format.raw/*104.6*/("""</tbody>
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

	<div id='modalVerOt' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable' style="max-width: 80% !important;" role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>"""),_display_(/*142.31*/mapDiccionario/*142.45*/.get("ORDEN_DE_TRABAJO")),format.raw/*142.69*/("""</h5>
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
	

""")))}),format.raw/*160.2*/("""


"""),format.raw/*163.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*164.31*/("""{"""),format.raw/*164.32*/("""
		  """),format.raw/*165.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*165.36*/("""{"""),format.raw/*165.37*/("""
		    	"""),format.raw/*166.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 0, "desc" ]],
		    	"language": """),format.raw/*169.20*/("""{"""),format.raw/*169.21*/("""
		        	"""),format.raw/*170.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*171.11*/("""}"""),format.raw/*171.12*/("""
		  """),format.raw/*172.5*/("""}"""),format.raw/*172.6*/(""" """),format.raw/*172.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*174.2*/("""}"""),format.raw/*174.3*/(""");
	
	const verCotizacion = (id_cotizacion) => """),format.raw/*176.43*/("""{"""),format.raw/*176.44*/("""
		"""),format.raw/*177.3*/("""var formData = new FormData();
	  	formData.append('id_cotizacion',id_cotizacion);
        $.ajax("""),format.raw/*179.16*/("""{"""),format.raw/*179.17*/("""
            """),format.raw/*180.13*/("""url: "/verCotizacionAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*187.36*/("""{"""),format.raw/*187.37*/("""
	     		"""),format.raw/*188.9*/("""document.getElementById('mostrarLaCotizacion').innerHTML = respuesta;
	     		$('#modalVerCotizacion').modal('show');
	     	"""),format.raw/*190.8*/("""}"""),format.raw/*190.9*/("""
        """),format.raw/*191.9*/("""}"""),format.raw/*191.10*/(""");
	"""),format.raw/*192.2*/("""}"""),format.raw/*192.3*/("""

	"""),format.raw/*194.2*/("""const verOt = (id_ot) => """),format.raw/*194.27*/("""{"""),format.raw/*194.28*/("""
		"""),format.raw/*195.3*/("""var formData = new FormData();
		formData.append('id_ot',id_ot);
		$.ajax("""),format.raw/*197.10*/("""{"""),format.raw/*197.11*/("""
			"""),format.raw/*198.4*/("""url: "/verOtAjax/",
			type: "POST",
			method: "POST",
			data: formData,
			cache: false,
			contentType: false,
			processData: false,
			success: function(respuesta)"""),format.raw/*205.32*/("""{"""),format.raw/*205.33*/("""
				"""),format.raw/*206.5*/("""document.getElementById('mostrarLaOt').innerHTML = respuesta;
				$('#modalVerOt').modal('show');
			"""),format.raw/*208.4*/("""}"""),format.raw/*208.5*/("""
		"""),format.raw/*209.3*/("""}"""),format.raw/*209.4*/(""");
	"""),format.raw/*210.2*/("""}"""),format.raw/*210.3*/("""
	
	
	
"""),format.raw/*214.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,detalle:List[List[String]],fechaDe:String,fechaA:String,tituloSucursal:String,desdeAAMMDD:String,hastaAAMMDD:String,id_sucursal:Long,id_comercial:Long): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,detalle,fechaDe,fechaA,tituloSucursal,desdeAAMMDD,hastaAAMMDD,id_sucursal,id_comercial)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String,String,String,String,Long,Long) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,detalle,fechaDe,fechaA,tituloSucursal,desdeAAMMDD,hastaAAMMDD,id_sucursal,id_comercial) => apply(mapDiccionario,mapPermiso,userMnu,detalle,fechaDe,fechaA,tituloSucursal,desdeAAMMDD,hastaAAMMDD,id_sucursal,id_comercial)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizar/reportPipelineRptDet.scala.html
                  HASH: 19a9ed824d909200f7d4b6dd8b059fb2a24b851d
                  MATRIX: 1084->1|1441->265|1474->273|1490->281|1529->283|1558->287|1626->335|1657->340|1700->362|1729->365|1773->388|1804->392|1881->443|2017->557|2047->560|2990->1476|3022->1487|3104->1542|3136->1553|3218->1608|3250->1619|3333->1675|3366->1687|3806->2100|3829->2114|3860->2124|3912->2149|3935->2163|3966->2173|4176->2357|4214->2379|4253->2380|4288->2388|4396->2469|4411->2475|4439->2482|4483->2499|4501->2508|4551->2537|4587->2546|4706->2638|4721->2644|4750->2652|4868->2743|4883->2749|4912->2757|4951->2768|5051->2841|5066->2847|5094->2854|5165->2898|5180->2904|5208->2911|5279->2955|5294->2961|5322->2968|5393->3012|5408->3018|5436->3025|5507->3069|5522->3075|5550->3082|5621->3126|5636->3132|5664->3139|5773->3221|5788->3227|5817->3235|5861->3252|5879->3261|5930->3291|5966->3300|6082->3389|6121->3390|6160->3401|6216->3430|6231->3436|6260->3444|6380->3537|6395->3543|6424->3551|6464->3563|6540->3595|6576->3604|6648->3649|6663->3655|6691->3662|6763->3707|6778->3713|6807->3721|6881->3767|6897->3773|6927->3781|6999->3825|7015->3831|7045->3839|7103->3866|7137->3872|8624->5331|8648->5345|8694->5369|9216->5860|9247->5863|9338->5925|9368->5926|9401->5931|9461->5962|9491->5963|9527->5971|9703->6118|9733->6119|9774->6131|9881->6209|9911->6210|9944->6215|9973->6216|10002->6217|10103->6290|10132->6291|10208->6338|10238->6339|10269->6342|10396->6440|10426->6441|10468->6454|10727->6684|10757->6685|10794->6694|10947->6819|10976->6820|11013->6829|11043->6830|11075->6834|11104->6835|11135->6838|11189->6863|11219->6864|11250->6867|11353->6941|11383->6942|11415->6946|11613->7115|11643->7116|11676->7121|11805->7222|11834->7223|11865->7226|11894->7227|11926->7231|11955->7232|11990->7239
                  LINES: 28->1|35->4|37->6|37->6|37->6|39->8|39->8|41->10|41->10|42->11|42->11|44->13|45->14|45->14|46->15|71->40|71->40|72->41|72->41|73->42|73->42|74->43|74->43|88->57|88->57|88->57|89->58|89->58|89->58|97->66|97->66|97->66|98->67|100->69|100->69|100->69|101->70|101->70|101->70|102->71|104->73|104->73|104->73|106->75|106->75|106->75|107->76|110->79|110->79|110->79|111->80|111->80|111->80|112->81|112->81|112->81|113->82|113->82|113->82|114->83|114->83|114->83|115->84|115->84|115->84|117->86|117->86|117->86|118->87|118->87|118->87|119->88|121->90|121->90|122->91|122->91|122->91|122->91|124->93|124->93|124->93|125->94|127->96|128->97|129->98|129->98|129->98|130->99|130->99|130->99|131->100|131->100|131->100|132->101|132->101|132->101|134->103|135->104|173->142|173->142|173->142|191->160|194->163|195->164|195->164|196->165|196->165|196->165|197->166|200->169|200->169|201->170|202->171|202->171|203->172|203->172|203->172|205->174|205->174|207->176|207->176|208->177|210->179|210->179|211->180|218->187|218->187|219->188|221->190|221->190|222->191|222->191|223->192|223->192|225->194|225->194|225->194|226->195|228->197|228->197|229->198|236->205|236->205|237->206|239->208|239->208|240->209|240->209|241->210|241->210|245->214
                  -- GENERATED --
              */
          