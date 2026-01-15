
package viewsMnuReportes.html

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

object estadosFacturaProyecto extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template12[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String,Double,Double,Double,String,Long,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
lista: List[List[String]], fechaDesde: String, fechaHasta: String, usd: Double, eur: Double, uf: Double, archivoPDF: String,
nroDec: Long, mon: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.1*/("""    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalContactoBodega(mapDiccionario)),format.raw/*9.38*/("""
	"""),_display_(/*10.3*/modalContactoCliente()),format.raw/*10.25*/("""
	"""),_display_(/*11.3*/modalContactoProyecto()),format.raw/*11.26*/("""
	
	"""),format.raw/*13.2*/("""<div id="enCarga" class="blocker" style="display: none;"><br><br><br><br><br><br><h1>En proceso.....<div id="msgEspera1"></div><div id="msgEspera2"></div></h1></div>
	
	<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*16.4*/barraTitulo(mapDiccionario, "SELECCION DE "+mapDiccionario.get("BODEGA")+"/PROYECTO","PROCESO DE EP ESTADO EQUIPO COBRAR "+mapDiccionario.get("ARRIENDO")+" POR DAÑOS")),format.raw/*16.171*/("""

		"""),format.raw/*18.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-9 col-sm-7 col-md-7 col-lg-7">
				<div class="noprint">
					<div class="row">
						<div class="col-1"></div>
						<div class="col-5">
							"""),_display_(if(mapPermiso.get("parametro.cobraArriendoPorAuxiliar")!=null && mapPermiso.get("parametro.cobraArriendoPorAuxiliar").equals("1"))/*24.139*/{_display_(Seq[Any](format.raw/*24.140*/("""
								"""),format.raw/*25.9*/("""<button type="button"  id="btnEnvioMasivo" class="btn btn-sm btn-warning"
								onclick="this.setAttribute('disabled', 'true'); document.getElementById('enCarga').style.display='block';
												document.getElementById('envioMasivoListadoProforma').submit();">
									ENVIO MASIVO A: "Listado y Ajustes"
								</button>
							""")))} else {null} ),format.raw/*30.9*/("""
							"""),_display_(if(mapPermiso.get("parametro.cobraArriendoPorAuxiliar")!=null && mapPermiso.get("parametro.cobraArriendoPorAuxiliar").equals("2"))/*31.139*/{_display_(Seq[Any](format.raw/*31.140*/("""
								"""),format.raw/*32.9*/("""<button type="button"  id="btnEnvioMasivo" class="btn btn-sm btn-warning"
								onclick="$('#modalEnvioMasivoEnProceso').modal('show');">
									ENVIO MASIVO A: "Listado y Ajustes"
								</button>
							""")))} else {null} ),format.raw/*36.9*/("""
						"""),format.raw/*37.7*/("""</div>

						<div class="col-6">
							<table class="table table-sm table-condensed table-fluid">
								<tr>
									<td>
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
									</td>
								</tr>
							</table>
						</div>

					</div>
				</div>
				<div class="noprint">
					<table class="table table-sm table-condensed table-fluid">
						<tr>
							<td width="25%">
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text" id="basic-addon1">BUSCAR</span>
									</div>
									<input type="text" class="form-control left"
									id="searchTermtablaPrincipal"
									onkeyup="doSearch2_conTot('tablaPrincipal');sumarTotales();">
								</div>
							</td>
						</tr>
					</table>
				</div>
				<h5>Período desde """),_display_(/*76.24*/utilities/*76.33*/.Fechas.DDMMAA(fechaDesde)),format.raw/*76.59*/(""" """),format.raw/*76.60*/("""a """),_display_(/*76.63*/utilities/*76.72*/.Fechas.DDMMAA(fechaHasta)),format.raw/*76.98*/("""</h5>
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>SUCURSAL</TH>
							<TH>"""),_display_(/*81.13*/mapDiccionario/*81.27*/.get("BODEGA")),format.raw/*81.41*/("""</TH>
							<TH>TOTAL<br>(en """),_display_(/*82.26*/mon),format.raw/*82.29*/(""")</TH>
							<TH>DETALLE</TH>
						</TR>
					</thead>
					<tbody>
					"""),_display_(/*87.7*/for(lista1 <- lista) yield /*87.27*/{_display_(Seq[Any](format.raw/*87.28*/("""
						"""),format.raw/*88.7*/("""<TR>
							<td  style="text-align:left;">"""),_display_(/*89.39*/lista1/*89.45*/.get(1)),format.raw/*89.52*/("""</td>
							<td  style="text-align:left;">"""),_display_(/*90.39*/lista1/*90.45*/.get(2)),format.raw/*90.52*/("""</td>
							<td  class="total" style="text-align:right;">"""),_display_(/*91.54*/lista1/*91.60*/.get(3)),format.raw/*91.67*/("""</td>
							<td  style="text-align:center;">
								<a href="#" onclick="emitir('"""),_display_(/*93.39*/lista1/*93.45*/.get(0)),format.raw/*93.52*/("""');">
									<kbd style="background-color: #73C6B6">EMITIR</kbd>
								</a>
							</td>
						</TR>
					""")))}),format.raw/*98.7*/("""
					"""),format.raw/*99.6*/("""</tbody>
					<tfoot>
						<TR>
							<TH>TOTAL</TH>
							<TH></TH>
							<TH style="text-align:right;"><div id="granTotal"></div></TH>
							<TH></TH>
						</TR>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
	
	<div id='muestraPDF' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>FACTURA PROFORMA</h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<div id="rutaPDF"></div>
	   				<div class='row'>
						<div class='col-sm-12' style='text-align:center'>
							<button type='button' class='btn btn-sm  btn-success' style='font-size: 10px;' data-dismiss='modal'>Listo</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div id='modalEnvioMasivoEnProceso' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable' role='document' style="width:300px">
			<div class='modal-content'>
				<div class='modal-header'>
					ENVIO MASIVO PROFORMAS
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<div class="input-group">
			        	<p>
							No puede generar una nueva solicitud, ya existe una solicitud en proceso.
			        	</p>
					</div>
					<br>
					<div class='row'>
						<div class='col-sm-12' style='text-align:center'>
							<input type='button' class='btn btn-sm btn-warning' value='Cerrar' 
								onclick='$("#btnEnvioMasivo").prop("disabled",false);'
								data-dismiss='modal'>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<form id="envioMasivoListadoProforma" class="formulario" method="post" action="/routes3/envioMasivoAlistAjustes/">
		<input type="hidden" name="fechaDesde" value=""""),_display_(/*164.50*/fechaDesde),format.raw/*164.60*/("""">
		<input type="hidden" name="fechaHasta" value=""""),_display_(/*165.50*/fechaHasta),format.raw/*165.60*/("""">
		<input type="hidden" name="uf" value=""""),_display_(/*166.42*/uf),format.raw/*166.44*/("""">
		<input type="hidden" name="usd" value=""""),_display_(/*167.43*/usd),format.raw/*167.46*/("""">
		<input type="hidden" name="eur" value=""""),_display_(/*168.43*/eur),format.raw/*168.46*/("""">
	</form>


	<form id="emitir" method="post" action="/routes3/estadosFacturaProyectoDetalle/">
		<input type="hidden" name="fechaDesde" value=""""),_display_(/*173.50*/fechaDesde),format.raw/*173.60*/("""">
		<input type="hidden" name="fechaHasta" value=""""),_display_(/*174.50*/fechaHasta),format.raw/*174.60*/("""">
		<input type="hidden" name="uf" value=""""),_display_(/*175.42*/uf),format.raw/*175.44*/("""">
		<input type="hidden" name="usd" value=""""),_display_(/*176.43*/usd),format.raw/*176.46*/("""">
		<input type="hidden" name="eur" value=""""),_display_(/*177.43*/eur),format.raw/*177.46*/("""">
		<input type="hidden" id="emite_idBodega" name="id_bodegaEmpresa">
	</form>

	<form id="excel" class="formulario" method="post" action="/routes3/estadosFacturaProyectoExcel/">
		<input type="hidden" name="fechaDesde" value=""""),_display_(/*182.50*/fechaDesde),format.raw/*182.60*/("""">
		<input type="hidden" name="fechaHasta" value=""""),_display_(/*183.50*/fechaHasta),format.raw/*183.60*/("""">
		<input type="hidden" name="uf" value=""""),_display_(/*184.42*/uf),format.raw/*184.44*/("""">
		<input type="hidden" name="usd" value=""""),_display_(/*185.43*/usd),format.raw/*185.46*/("""">
		<input type="hidden" name="eur" value=""""),_display_(/*186.43*/eur),format.raw/*186.46*/("""">
	</form>

""")))}),format.raw/*189.2*/("""


"""),format.raw/*192.1*/("""<script type="text/javascript">

	let flag = true;
	$(document).ready(function() """),format.raw/*195.31*/("""{"""),format.raw/*195.32*/("""
		 """),format.raw/*196.4*/("""$('#tablaPrincipal').DataTable("""),format.raw/*196.35*/("""{"""),format.raw/*196.36*/("""
		    	"""),format.raw/*197.8*/(""""fixedHeader": true,
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [[ 2, "asc" ]],
		    	"language": """),format.raw/*202.20*/("""{"""),format.raw/*202.21*/("""
		        	"""),format.raw/*203.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*204.11*/("""}"""),format.raw/*204.12*/("""
		  """),format.raw/*205.5*/("""}"""),format.raw/*205.6*/(""" """),format.raw/*205.7*/(""");

		if (""""),_display_(/*207.9*/archivoPDF),format.raw/*207.19*/("""" !== "0") """),format.raw/*207.30*/("""{"""),format.raw/*207.31*/("""
			"""),format.raw/*208.4*/("""$("#rutaPDF").html(
					`<object data='/showPdf/"""),_display_(/*209.31*/archivoPDF),format.raw/*209.41*/("""' type='application/pdf' width='100%' height='720'></object>`
			);
			$("#muestraPDF").modal("show");
		"""),format.raw/*212.3*/("""}"""),format.raw/*212.4*/("""

		"""),format.raw/*214.3*/("""$("#mostrarmostrar").show();

		sumarTotales();

	"""),format.raw/*218.2*/("""}"""),format.raw/*218.3*/(""");

	const sumarTotales = () => """),format.raw/*220.29*/("""{"""),format.raw/*220.30*/("""
		"""),format.raw/*221.3*/("""let totalGranTotal = 0;
		$(".total:visible").each(function () """),format.raw/*222.40*/("""{"""),format.raw/*222.41*/("""
			"""),format.raw/*223.4*/("""totalGranTotal += parseFloat($(this).text().replace(/,/g, '') || 0);
		"""),format.raw/*224.3*/("""}"""),format.raw/*224.4*/(""");
		$("#granTotal").text(formatStandar(totalGranTotal,""""),_display_(/*225.55*/nroDec),format.raw/*225.61*/(""""));
	"""),format.raw/*226.2*/("""}"""),format.raw/*226.3*/("""

	"""),format.raw/*228.2*/("""const emitir = (id_bodegaEmpresa) => """),format.raw/*228.39*/("""{"""),format.raw/*228.40*/("""
		"""),format.raw/*229.3*/("""$("#emite_idBodega").val(id_bodegaEmpresa);
		document.getElementById("emitir").submit();
	"""),format.raw/*231.2*/("""}"""),format.raw/*231.3*/("""

"""),format.raw/*233.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,lista:List[List[String]],fechaDesde:String,fechaHasta:String,usd:Double,eur:Double,uf:Double,archivoPDF:String,nroDec:Long,mon:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,lista,fechaDesde,fechaHasta,usd,eur,uf,archivoPDF,nroDec,mon)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String,Double,Double,Double,String,Long,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,lista,fechaDesde,fechaHasta,usd,eur,uf,archivoPDF,nroDec,mon) => apply(mapDiccionario,mapPermiso,userMnu,lista,fechaDesde,fechaHasta,usd,eur,uf,archivoPDF,nroDec,mon)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/estadosFacturaProyecto.scala.html
                  HASH: c67f79aa1f9c9cb10db9b330cb20e081b07c4af1
                  MATRIX: 1096->1|1438->250|1470->257|1486->265|1525->267|1554->271|1622->319|1652->324|1707->359|1736->362|1779->384|1808->387|1852->410|1883->414|2129->634|2318->801|2349->805|2720->1148|2760->1149|2796->1158|3176->1495|3343->1634|3383->1635|3419->1644|3673->1855|3707->1862|4962->3090|4980->3099|5027->3125|5056->3126|5086->3129|5104->3138|5151->3164|5389->3375|5412->3389|5447->3403|5505->3434|5529->3437|5631->3513|5667->3533|5706->3534|5740->3541|5810->3584|5825->3590|5853->3597|5924->3641|5939->3647|5967->3654|6053->3713|6068->3719|6096->3726|6207->3810|6222->3816|6250->3823|6391->3934|6424->3940|8608->6096|8640->6106|8720->6158|8752->6168|8824->6212|8848->6214|8921->6259|8946->6262|9019->6307|9044->6310|9218->6456|9250->6466|9330->6518|9362->6528|9434->6572|9458->6574|9531->6619|9556->6622|9629->6667|9654->6670|9911->6899|9943->6909|10023->6961|10055->6971|10127->7015|10151->7017|10224->7062|10249->7065|10322->7110|10347->7113|10392->7127|10423->7130|10533->7211|10563->7212|10595->7216|10655->7247|10685->7248|10721->7256|10889->7395|10919->7396|10960->7408|11067->7486|11097->7487|11130->7492|11159->7493|11188->7494|11227->7506|11259->7516|11299->7527|11329->7528|11361->7532|11439->7582|11471->7592|11604->7697|11633->7698|11665->7702|11743->7752|11772->7753|11833->7785|11863->7786|11894->7789|11986->7852|12016->7853|12048->7857|12147->7928|12176->7929|12261->7986|12289->7992|12323->7998|12352->7999|12383->8002|12449->8039|12479->8040|12510->8043|12629->8134|12658->8135|12688->8137
                  LINES: 28->1|35->4|36->5|36->5|36->5|38->7|38->7|40->9|40->9|41->10|41->10|42->11|42->11|44->13|47->16|47->16|49->18|55->24|55->24|56->25|61->30|62->31|62->31|63->32|67->36|68->37|107->76|107->76|107->76|107->76|107->76|107->76|107->76|112->81|112->81|112->81|113->82|113->82|118->87|118->87|118->87|119->88|120->89|120->89|120->89|121->90|121->90|121->90|122->91|122->91|122->91|124->93|124->93|124->93|129->98|130->99|195->164|195->164|196->165|196->165|197->166|197->166|198->167|198->167|199->168|199->168|204->173|204->173|205->174|205->174|206->175|206->175|207->176|207->176|208->177|208->177|213->182|213->182|214->183|214->183|215->184|215->184|216->185|216->185|217->186|217->186|220->189|223->192|226->195|226->195|227->196|227->196|227->196|228->197|233->202|233->202|234->203|235->204|235->204|236->205|236->205|236->205|238->207|238->207|238->207|238->207|239->208|240->209|240->209|243->212|243->212|245->214|249->218|249->218|251->220|251->220|252->221|253->222|253->222|254->223|255->224|255->224|256->225|256->225|257->226|257->226|259->228|259->228|259->228|260->229|262->231|262->231|264->233
                  -- GENERATED --
              */
          