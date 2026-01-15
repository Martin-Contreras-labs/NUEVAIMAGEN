
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

object reportPipelineRptComercial extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template13[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String,String,String,String,Long,Long,Map[List[String],Double],Long,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu, detalle: List[List[String]], fechaDe: String, fechaA: String, tituloSucursal: String, desdeAAMMDD: String, hastaAAMMDD: String, id_sucursal: Long, id_comercial: Long, mapResumen: Map[List[String],Double], nroDec: Long):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	
	"""),_display_(/*8.3*/modalContactoCliente()),format.raw/*8.25*/("""
	"""),_display_(/*9.3*/modalContactoProyecto()),format.raw/*9.26*/("""
	
	"""),format.raw/*11.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*12.4*/barraTitulo(mapDiccionario, "PIPELINE RESUMEN Y DETALLE POR COMERCIAL"+tituloSucursal,"período movil: de "+fechaDe+" a "+fechaA)),format.raw/*12.132*/("""
		"""),format.raw/*13.3*/("""<div class="row  justify-content-md-center">
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
			
			<form id="excel" class="formulario" method="post" action="/routes2/reportPipelineRptComercialExcel/">
				<input type="hidden" name="desdeAAMMDD" value=""""),_display_(/*38.53*/desdeAAMMDD),format.raw/*38.64*/("""">
				<input type="hidden" name="hastaAAMMDD" value=""""),_display_(/*39.53*/hastaAAMMDD),format.raw/*39.64*/("""">
				<input type="hidden" name="id_sucursal" value=""""),_display_(/*40.53*/id_sucursal),format.raw/*40.64*/("""">
				<input type="hidden" name="id_comercial" value=""""),_display_(/*41.54*/id_comercial),format.raw/*41.66*/("""">
			</form>

				<div class="noprint">
					<table class="table table-sm table-condensed table-fluid">
						<tr>
							<td width="25%">
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text" id="basic-addon1">BUSCAR</span>
									</div>
									<input type="text" class="form-control left"
									id="searchTermtablaResumen"
									onkeyup="doSearch2('tablaResumen');
									$('#searchTermtablaResumen').attr('id','searchTermtablaPrincipal');
									doSearch2('tablaPrincipal');
									$('#searchTermtablaPrincipal').attr('id','searchTermtablaResumen');">
								</div>
							</td>
							<td>
							</td>
							<td>
							</td>
						</tr>
					</table>
				</div>

				<div class="col-xs-8 col-sm-7 col-md-6 col-lg-5">
					<h4>RESUMEN:</h4>
					<table id="tablaResumen" class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<TR>
								<TH>COMERCIAL</TH>
								<TH>ESTADO</TH>
								<TH>TOTAL<BR>NETO</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*79.9*/for(key <- mapResumen.keys) yield /*79.36*/ {_display_(Seq[Any](format.raw/*79.38*/("""
								"""),format.raw/*80.9*/("""<tr>
									<td>"""),_display_(/*81.15*/key/*81.18*/.get(0)),format.raw/*81.25*/("""</td>
									<td>"""),_display_(/*82.15*/key/*82.18*/.get(1)),format.raw/*82.25*/("""</td>
									<td class="resumen" style="text-align:right;">"""),_display_(/*83.57*/mapResumen(key)),format.raw/*83.72*/("""</td>
								</tr>
							""")))}),format.raw/*85.9*/("""
						"""),format.raw/*86.7*/("""</tbody>
					</table>
				</div>
			<br>
				<h4>DETALLE:</h4>
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
							<TH>FECHA<br>"""),_display_(/*102.22*/mapDiccionario/*102.36*/.get("OT")),format.raw/*102.46*/("""</TH>
							<TH>NRO<br>"""),_display_(/*103.20*/mapDiccionario/*103.34*/.get("OT")),format.raw/*103.44*/("""</TH>
							<TH>TOTAL<BR>NETO</TH>
							<TH>PESO<BR>KG</TH>
							<TH>FECHA<BR>PROBABLE</TH>
							<TH>DIBUJANTE<BR>PROYECTISTA</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*111.8*/for(lista1 <- detalle) yield /*111.30*/{_display_(Seq[Any](format.raw/*111.31*/("""
							"""),format.raw/*112.8*/("""<TR>
								<td style="text-align:center;">
									<div style='display:none'>"""),_display_(/*114.37*/lista1/*114.43*/.get(0)),format.raw/*114.50*/("""</div>
									"""),_display_(/*115.11*/utilities/*115.20*/.Fechas.DDMMAA(lista1.get(0))),format.raw/*115.49*/("""
								"""),format.raw/*116.9*/("""</td>
								<td style="text-align:center;">"""),_display_(/*117.41*/lista1/*117.47*/.get(16)),format.raw/*117.55*/("""</td>
								<td style="text-align:left;">"""),_display_(/*118.39*/lista1/*118.45*/.get(1)),format.raw/*118.52*/("""</td>
								<td style="text-align:left;">"""),_display_(/*119.39*/lista1/*119.45*/.get(6)),format.raw/*119.52*/("""</td>
								<td style="text-align:left;">"""),_display_(/*120.39*/lista1/*120.45*/.get(4)),format.raw/*120.52*/("""</td>
								<td style="text-align:left;">"""),_display_(/*121.39*/lista1/*121.45*/.get(2)),format.raw/*121.52*/("""</td>
								<td style="text-align:left;">"""),_display_(/*122.39*/lista1/*122.45*/.get(3)),format.raw/*122.52*/("""</td>
								<td style="text-align:left;">"""),_display_(/*123.39*/lista1/*123.45*/.get(5)),format.raw/*123.52*/("""</td>
								<td style="text-align:center;">
									<div style='display:none'>"""),_display_(/*125.37*/lista1/*125.43*/.get(15)),format.raw/*125.51*/("""</div>
									"""),_display_(/*126.11*/utilities/*126.20*/.Fechas.DDMMAA(lista1.get(15))),format.raw/*126.50*/("""
								"""),format.raw/*127.9*/("""</td>
								<td style="text-align:center;">"""),_display_(/*128.41*/lista1/*128.47*/.get(17)),format.raw/*128.55*/("""</td>
								<td style="text-align:right;">"""),_display_(/*129.40*/lista1/*129.46*/.get(7)),format.raw/*129.53*/("""</td>
								<td style="text-align:right;">"""),_display_(/*130.40*/lista1/*130.46*/.get(14)),format.raw/*130.54*/("""</td>
								<td style="text-align:center;">"""),_display_(/*131.41*/lista1/*131.47*/.get(20)),format.raw/*131.55*/("""</td>
								<td style="text-align:left;">"""),_display_(/*132.39*/lista1/*132.45*/.get(21)),format.raw/*132.53*/("""</td>
							</TR>
			 			""")))}),format.raw/*134.9*/("""
					"""),format.raw/*135.6*/("""</tbody>
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
					<h5 class='modal-title'>"""),_display_(/*173.31*/mapDiccionario/*173.45*/.get("ORDEN_DE_TRABAJO")),format.raw/*173.69*/("""</h5>
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
	

""")))}),format.raw/*191.2*/("""


"""),format.raw/*194.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*195.31*/("""{"""),format.raw/*195.32*/("""

		"""),format.raw/*197.3*/("""$('#tablaPrincipal').DataTable("""),format.raw/*197.34*/("""{"""),format.raw/*197.35*/("""
			"""),format.raw/*198.4*/(""""fixedHeader": true,
			"paging": false,
			"info": false,
			"searching": false,
			"order": [[ 1, "asc" ]],
			"language": """),format.raw/*203.16*/("""{"""),format.raw/*203.17*/("""
				"""),format.raw/*204.5*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
			"""),format.raw/*205.4*/("""}"""),format.raw/*205.5*/("""
		"""),format.raw/*206.3*/("""}"""),format.raw/*206.4*/(""" """),format.raw/*206.5*/(""");

		$('#tablaResumen').DataTable("""),format.raw/*208.32*/("""{"""),format.raw/*208.33*/("""
			"""),format.raw/*209.4*/(""""fixedHeader": true,
			"paging": false,
			"info": false,
			"searching": false,
			"order": [[ 0, "asc" ]],
			"language": """),format.raw/*214.16*/("""{"""),format.raw/*214.17*/("""
				"""),format.raw/*215.5*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
			"""),format.raw/*216.4*/("""}"""),format.raw/*216.5*/("""
		"""),format.raw/*217.3*/("""}"""),format.raw/*217.4*/(""" """),format.raw/*217.5*/(""");

		$(".resumen").each(function() """),format.raw/*219.33*/("""{"""),format.raw/*219.34*/("""
			"""),format.raw/*220.4*/("""let val = $(this).text().replace(/,/g,'');
			$(this).text(formatStandar(val,""""),_display_(/*221.37*/nroDec),format.raw/*221.43*/(""""));
		"""),format.raw/*222.3*/("""}"""),format.raw/*222.4*/(""");

		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*225.2*/("""}"""),format.raw/*225.3*/(""");

	
	
	
</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,detalle:List[List[String]],fechaDe:String,fechaA:String,tituloSucursal:String,desdeAAMMDD:String,hastaAAMMDD:String,id_sucursal:Long,id_comercial:Long,mapResumen:Map[List[String],Double],nroDec:Long): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,detalle,fechaDe,fechaA,tituloSucursal,desdeAAMMDD,hastaAAMMDD,id_sucursal,id_comercial,mapResumen,nroDec)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String,String,String,String,Long,Long,Map[List[String],Double],Long) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,detalle,fechaDe,fechaA,tituloSucursal,desdeAAMMDD,hastaAAMMDD,id_sucursal,id_comercial,mapResumen,nroDec) => apply(mapDiccionario,mapPermiso,userMnu,detalle,fechaDe,fechaA,tituloSucursal,desdeAAMMDD,hastaAAMMDD,id_sucursal,id_comercial,mapResumen,nroDec)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizar/reportPipelineRptComercial.scala.html
                  HASH: aeabbd437fc073f9b17be3de012c73172174eac1
                  MATRIX: 1120->1|1529->317|1562->325|1578->333|1617->335|1646->339|1714->387|1744->392|1786->414|1814->417|1857->440|1888->444|1965->495|2115->623|2145->626|3094->1548|3126->1559|3208->1614|3240->1625|3322->1680|3354->1691|3437->1747|3470->1759|4626->2889|4669->2916|4709->2918|4745->2927|4791->2946|4803->2949|4831->2956|4878->2976|4890->2979|4918->2986|5007->3048|5043->3063|5101->3091|5135->3098|5622->3557|5646->3571|5678->3581|5731->3606|5755->3620|5787->3630|5998->3814|6037->3836|6077->3837|6113->3845|6222->3926|6238->3932|6267->3939|6312->3956|6331->3965|6382->3994|6419->4003|6493->4049|6509->4055|6539->4063|6611->4107|6627->4113|6656->4120|6728->4164|6744->4170|6773->4177|6845->4221|6861->4227|6890->4234|6962->4278|6978->4284|7007->4291|7079->4335|7095->4341|7124->4348|7196->4392|7212->4398|7241->4405|7351->4487|7367->4493|7397->4501|7442->4518|7461->4527|7513->4557|7550->4566|7624->4612|7640->4618|7670->4626|7743->4671|7759->4677|7788->4684|7861->4729|7877->4735|7907->4743|7981->4789|7997->4795|8027->4803|8099->4847|8115->4853|8145->4861|8203->4888|8237->4894|9724->6353|9748->6367|9794->6391|10316->6882|10347->6885|10438->6947|10468->6948|10500->6952|10560->6983|10590->6984|10622->6988|10776->7113|10806->7114|10839->7119|10938->7190|10967->7191|10998->7194|11027->7195|11056->7196|11120->7231|11150->7232|11182->7236|11336->7361|11366->7362|11399->7367|11498->7438|11527->7439|11558->7442|11587->7443|11616->7444|11681->7480|11711->7481|11743->7485|11850->7564|11878->7570|11913->7577|11942->7578|12044->7652|12073->7653
                  LINES: 28->1|33->2|35->4|35->4|35->4|37->6|37->6|39->8|39->8|40->9|40->9|42->11|43->12|43->12|44->13|69->38|69->38|70->39|70->39|71->40|71->40|72->41|72->41|110->79|110->79|110->79|111->80|112->81|112->81|112->81|113->82|113->82|113->82|114->83|114->83|116->85|117->86|133->102|133->102|133->102|134->103|134->103|134->103|142->111|142->111|142->111|143->112|145->114|145->114|145->114|146->115|146->115|146->115|147->116|148->117|148->117|148->117|149->118|149->118|149->118|150->119|150->119|150->119|151->120|151->120|151->120|152->121|152->121|152->121|153->122|153->122|153->122|154->123|154->123|154->123|156->125|156->125|156->125|157->126|157->126|157->126|158->127|159->128|159->128|159->128|160->129|160->129|160->129|161->130|161->130|161->130|162->131|162->131|162->131|163->132|163->132|163->132|165->134|166->135|204->173|204->173|204->173|222->191|225->194|226->195|226->195|228->197|228->197|228->197|229->198|234->203|234->203|235->204|236->205|236->205|237->206|237->206|237->206|239->208|239->208|240->209|245->214|245->214|246->215|247->216|247->216|248->217|248->217|248->217|250->219|250->219|251->220|252->221|252->221|253->222|253->222|256->225|256->225
                  -- GENERATED --
              */
          