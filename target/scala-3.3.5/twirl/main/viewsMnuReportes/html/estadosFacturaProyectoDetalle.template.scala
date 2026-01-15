
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

object estadosFacturaProyectoDetalle extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template12[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],tables.BodegaEmpresa,String,String,Double,Double,Double,Long,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
lista: List[List[String]], bodegaEmpresa: tables.BodegaEmpresa,
desdeAAMMDD: String, hastaAAMMDD: String, usd: Double, eur: Double, uf: Double, nroDec: Long, nomMon: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.1*/("""
    	
"""),_display_(/*6.2*/main("")/*6.10*/ {_display_(Seq[Any](format.raw/*6.12*/("""

"""),_display_(/*8.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*8.50*/("""
	"""),format.raw/*9.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*10.4*/barraTitulo(mapDiccionario, "DETALLE EP ESTADO EQUIPO COBRAR "+mapDiccionario.get("ARRIENDO")+" POR DAÑOS " + mapDiccionario.get("BODEGA") + "/PROYECTO: " + bodegaEmpresa.getNombre(),
			"PERIODO: desde " + utilities.Fechas.DDMMAA(desdeAAMMDD) +" --- hasta: " +  utilities.Fechas.DDMMAA(hastaAAMMDD))),format.raw/*11.117*/("""
		"""),format.raw/*12.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-10 col-md-10 col-lg-10">
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
									onkeyup="doSearch2('tablaPrincipal');">
								</div>
							</td>
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
							<td width="30%">
								<div align="right">
									ENVIAR A:
									<button id="btnArr" type="button" class="btn btn-mini btn-info" tabindex="-1"
											onclick=" this.disabled = true; document.getElementById('formProforma').submit()">
										Listado y Ajustes
									</button>
								</div>
							</td>
						</tr>
					</table>
				</div>
				<form id="excel" class="formulario" method="post" action="/routes3/estadosFacturaProyectoDetExcel/">
					<input type="hidden" name="fechaDesde" value=""""),_display_(/*54.53*/desdeAAMMDD),format.raw/*54.64*/("""">
					<input type="hidden" name="fechaHasta" value=""""),_display_(/*55.53*/hastaAAMMDD),format.raw/*55.64*/("""">
					<input type="hidden" name="uf" value=""""),_display_(/*56.45*/uf),format.raw/*56.47*/("""">
					<input type="hidden" name="usd" value=""""),_display_(/*57.46*/usd),format.raw/*57.49*/("""">
					<input type="hidden" name="eur" value=""""),_display_(/*58.46*/eur),format.raw/*58.49*/("""">
					<input type="hidden" name="id_bodegaEmpresa" value=""""),_display_(/*59.59*/bodegaEmpresa/*59.72*/.getId()),format.raw/*59.80*/("""">
				</form>
				<form id="formProforma" class="formulario" method="post" action="/routes3/enviarAlistAjustes/">
					<input type="hidden" name="fechaDesde" value=""""),_display_(/*62.53*/desdeAAMMDD),format.raw/*62.64*/("""">
					<input type="hidden" name="fechaHasta" value=""""),_display_(/*63.53*/hastaAAMMDD),format.raw/*63.64*/("""">
					<input type="hidden" name="uf" value=""""),_display_(/*64.45*/uf),format.raw/*64.47*/("""">
					<input type="hidden" name="usd" value=""""),_display_(/*65.46*/usd),format.raw/*65.49*/("""">
					<input type="hidden" name="eur" value=""""),_display_(/*66.46*/eur),format.raw/*66.49*/("""">
					<input type="hidden" name="id_bodegaEmpresa" value=""""),_display_(/*67.59*/bodegaEmpresa/*67.72*/.getId()),format.raw/*67.80*/("""">
				</form>
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
					        <TH>GRUPO</TH>
					        <TH>COTI</TH>
							<TH>CODIGO</TH>
					        <TH>EQUIPO</TH>
							<TH>FECHA<BR>MOVIM</TH>
					        <TH>NRO<BR></BR>MOVIM</TH>
							<TH>REF<BR> CLIENTE</TH>
							<TH>CANT<br>DIAS</TH>
							<TH>P.UNITARIO<BR>"""),_display_(/*80.27*/mapDiccionario/*80.41*/.get("ARR")),format.raw/*80.52*/(""" """),format.raw/*80.53*/("""X DIA</TH>
							<TH>CANT<BR>EQUIPOS</TH>
							<TH>TOTAL<BR>(en """),_display_(/*82.26*/nomMon),format.raw/*82.32*/(""")</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*86.8*/for(lista1 <- lista) yield /*86.28*/{_display_(Seq[Any](format.raw/*86.29*/("""
							"""),format.raw/*87.8*/("""<TR>
								<td  style="text-align:left;">"""),_display_(/*88.40*/lista1/*88.46*/.get(1)),format.raw/*88.53*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*89.42*/lista1/*89.48*/.get(2)),format.raw/*89.55*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*90.40*/lista1/*90.46*/.get(3)),format.raw/*90.53*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*91.40*/lista1/*91.46*/.get(4)),format.raw/*91.53*/("""</td>
								<td  style="text-align:center; min-width: 80px">
									<div style="display: none">"""),_display_(/*93.38*/lista1/*93.44*/.get(5)),format.raw/*93.51*/("""</div>
									"""),_display_(/*94.11*/utilities/*94.20*/.Fechas.DDMMAA(lista1.get(5))),format.raw/*94.49*/("""
								"""),format.raw/*95.9*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*96.42*/lista1/*96.48*/.get(6)),format.raw/*96.55*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*97.42*/lista1/*97.48*/.get(7)),format.raw/*97.55*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*98.42*/lista1/*98.48*/.get(8)),format.raw/*98.55*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*99.41*/lista1/*99.47*/.get(9)),format.raw/*99.54*/("""</td>
								<td  class="cant" style="text-align:right;">"""),_display_(/*100.54*/lista1/*100.60*/.get(10)),format.raw/*100.68*/("""</td>
								<td  class="total" style="text-align:right;">"""),_display_(/*101.55*/lista1/*101.61*/.get(11)),format.raw/*101.69*/("""</td>
							</TR>
			 			""")))}),format.raw/*103.9*/("""
					"""),format.raw/*104.6*/("""</tbody>
					<tfoot>
						<TR>
							<TH>TOTALES</TH>
							<TH></TH>
							<TH></TH>
							<TH></TH>
							<TH></TH>
							<TH></TH>
							<TH></TH>
							<TH></TH>
							<TH></TH>
							<TH style="text-align:right;"><div id="totalCant"></div></TH>
							<TH style="text-align:right;"><div id="granTotal"></div></TH>
						</TR>
					</tfoot>
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="LISTO" class="btn btn-light btn-sm rounded-pill btn-block" onclick="document.getElementById('salir').submit();">
				</div>
			</div>
		</div>
	</div>

	<form id="salir" action="/routes3/estadosFacturaProyecto/0" method="POST">
		<input type="hidden" name="fechaDesde" value=""""),_display_(/*133.50*/desdeAAMMDD),format.raw/*133.61*/("""">
		<input type="hidden" name="fechaHasta" value=""""),_display_(/*134.50*/hastaAAMMDD),format.raw/*134.61*/("""">
		<input type="hidden" name="uf" value=""""),_display_(/*135.42*/uf),format.raw/*135.44*/("""">
		<input type="hidden" name="usd" value=""""),_display_(/*136.43*/usd),format.raw/*136.46*/("""">
		<input type="hidden" name="eur" value=""""),_display_(/*137.43*/eur),format.raw/*137.46*/("""">
	</form>

""")))}),format.raw/*140.2*/("""




"""),format.raw/*145.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*146.31*/("""{"""),format.raw/*146.32*/("""
		  """),format.raw/*147.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*147.36*/("""{"""),format.raw/*147.37*/("""
				"""),format.raw/*148.5*/(""""fixedHeader": true,
				"lengthMenu": [[-1, 50, 100, 200], ["All", 50, 100, 200]],
				"language": """),format.raw/*150.17*/("""{"""),format.raw/*150.18*/("""
					"""),format.raw/*151.6*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
				"""),format.raw/*152.5*/("""}"""),format.raw/*152.6*/("""
		  """),format.raw/*153.5*/("""}"""),format.raw/*153.6*/(""" """),format.raw/*153.7*/(""");

		document.getElementById('mostrarmostrar').style.display="block";

		let totalCant = 0;
		$(".cant:visible").each(function () """),format.raw/*158.39*/("""{"""),format.raw/*158.40*/("""
			"""),format.raw/*159.4*/("""totalCant += parseFloat($(this).text().replace(/,/g, '') || 0);
		"""),format.raw/*160.3*/("""}"""),format.raw/*160.4*/(""");
		$("#totalCant").text(formatStandar(totalCant,""""),_display_(/*161.50*/nroDec),format.raw/*161.56*/(""""));

		let totalGranTotal = 0;
		$(".total:visible").each(function () """),format.raw/*164.40*/("""{"""),format.raw/*164.41*/("""
			"""),format.raw/*165.4*/("""totalGranTotal += parseFloat($(this).text().replace(/,/g, '') || 0);
		"""),format.raw/*166.3*/("""}"""),format.raw/*166.4*/(""");
		$("#granTotal").text(formatStandar(totalGranTotal,""""),_display_(/*167.55*/nroDec),format.raw/*167.61*/(""""));


	"""),format.raw/*170.2*/("""}"""),format.raw/*170.3*/(""");


</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,lista:List[List[String]],bodegaEmpresa:tables.BodegaEmpresa,desdeAAMMDD:String,hastaAAMMDD:String,usd:Double,eur:Double,uf:Double,nroDec:Long,nomMon:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,lista,bodegaEmpresa,desdeAAMMDD,hastaAAMMDD,usd,eur,uf,nroDec,nomMon)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],tables.BodegaEmpresa,String,String,Double,Double,Double,Long,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,lista,bodegaEmpresa,desdeAAMMDD,hastaAAMMDD,usd,eur,uf,nroDec,nomMon) => apply(mapDiccionario,mapPermiso,userMnu,lista,bodegaEmpresa,desdeAAMMDD,hastaAAMMDD,usd,eur,uf,nroDec,nomMon)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/estadosFacturaProyectoDetalle.scala.html
                  HASH: d62caaff33f7198a2f0c697afffd65597dc33a64
                  MATRIX: 1117->1|1481->272|1514->280|1530->288|1569->290|1597->293|1665->341|1693->343|1770->394|2092->694|2122->697|3768->2316|3800->2327|3882->2382|3914->2393|3988->2440|4011->2442|4086->2490|4110->2493|4185->2541|4209->2544|4297->2605|4319->2618|4348->2626|4542->2793|4574->2804|4656->2859|4688->2870|4762->2917|4785->2919|4860->2967|4884->2970|4959->3018|4983->3021|5071->3082|5093->3095|5122->3103|5597->3551|5620->3565|5652->3576|5681->3577|5776->3645|5803->3651|5882->3704|5918->3724|5957->3725|5992->3733|6063->3777|6078->3783|6106->3790|6180->3837|6195->3843|6223->3850|6295->3895|6310->3901|6338->3908|6410->3953|6425->3959|6453->3966|6580->4066|6595->4072|6623->4079|6667->4096|6685->4105|6735->4134|6771->4143|6845->4190|6860->4196|6888->4203|6962->4250|6977->4256|7005->4263|7079->4310|7094->4316|7122->4323|7195->4369|7210->4375|7238->4382|7325->4441|7341->4447|7371->4455|7459->4515|7475->4521|7505->4529|7563->4556|7597->4562|8451->5388|8484->5399|8564->5451|8597->5462|8669->5506|8693->5508|8766->5553|8791->5556|8864->5601|8889->5604|8934->5618|8967->5623|9058->5685|9088->5686|9121->5691|9181->5722|9211->5723|9244->5728|9373->5828|9403->5829|9437->5835|9537->5907|9566->5908|9599->5913|9628->5914|9657->5915|9817->6046|9847->6047|9879->6051|9973->6117|10002->6118|10082->6170|10110->6176|10210->6247|10240->6248|10272->6252|10371->6323|10400->6324|10485->6381|10513->6387|10549->6395|10578->6396
                  LINES: 28->1|35->4|37->6|37->6|37->6|39->8|39->8|40->9|41->10|42->11|43->12|85->54|85->54|86->55|86->55|87->56|87->56|88->57|88->57|89->58|89->58|90->59|90->59|90->59|93->62|93->62|94->63|94->63|95->64|95->64|96->65|96->65|97->66|97->66|98->67|98->67|98->67|111->80|111->80|111->80|111->80|113->82|113->82|117->86|117->86|117->86|118->87|119->88|119->88|119->88|120->89|120->89|120->89|121->90|121->90|121->90|122->91|122->91|122->91|124->93|124->93|124->93|125->94|125->94|125->94|126->95|127->96|127->96|127->96|128->97|128->97|128->97|129->98|129->98|129->98|130->99|130->99|130->99|131->100|131->100|131->100|132->101|132->101|132->101|134->103|135->104|164->133|164->133|165->134|165->134|166->135|166->135|167->136|167->136|168->137|168->137|171->140|176->145|177->146|177->146|178->147|178->147|178->147|179->148|181->150|181->150|182->151|183->152|183->152|184->153|184->153|184->153|189->158|189->158|190->159|191->160|191->160|192->161|192->161|195->164|195->164|196->165|197->166|197->166|198->167|198->167|201->170|201->170
                  -- GENERATED --
              */
          