
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

object reportFacturaProyectoDetalleHVta extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template18[Map[String,String],Map[String,String],utilities.UserMnu,String,List[List[String]],tables.BodegaEmpresa,String,String,String,Double,Double,Double,tables.Cliente,tables.Proyecto,String,Long,String,Double,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu, idTipoUsuario: String,
datos: List[List[String]], bodega: tables.BodegaEmpresa, esVenta: String, fechaDesde: String, fechaHasta: String,
usd: Double, eur: Double, uf: Double,
cliente: tables.Cliente, proyecto: tables.Proyecto, oc: String,
nroDecimales: Long, mon: String, tasaIva: Double):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*6.1*/("""    	
"""),_display_(/*7.2*/main("")/*7.10*/ {_display_(Seq[Any](format.raw/*7.12*/("""

	"""),_display_(/*9.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*9.51*/("""
	
	"""),_display_(/*11.3*/modalEquipoDescripcion()),format.raw/*11.27*/("""
	"""),_display_(/*12.3*/modalVerCotizacion()),format.raw/*12.23*/("""
	
	"""),format.raw/*14.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*15.4*/barraTitulo(mapDiccionario, "DETALLE EP/FACTURA PROFORMA SIMPLE " + mapDiccionario.get("BODEGA") + "/PROYECTO: " + bodega.getNombre(),
			"PERIODO: desde " + utilities.Fechas.DDMMAA(fechaDesde) +" --- hasta: " +  utilities.Fechas.DDMMAA(fechaHasta))),format.raw/*16.115*/("""

				"""),format.raw/*18.5*/("""<div class="noprint">
					<table class="table table-sm table-condensed table-fluid">
						<tr>
							<td style="width: 25%">
"""),format.raw/*22.38*/("""
"""),format.raw/*23.49*/("""
"""),format.raw/*24.80*/("""
"""),format.raw/*25.22*/("""
"""),format.raw/*26.60*/("""
"""),format.raw/*27.44*/("""
"""),format.raw/*28.54*/("""
"""),format.raw/*29.19*/("""
							"""),format.raw/*30.8*/("""</td>
							<td>
								<div style="text-align: center;">
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
							<td width="25%">
								HACER """),_display_(/*47.16*/mapDiccionario/*47.30*/.get("PROFORMA")),format.raw/*47.46*/(""":
								<button id="btnArr" type="button" class="btn btn-mini btn-info" tabindex="-1"
								onclick=" this.disabled = true;
								if(parseFloat(neto) > 0)"""),format.raw/*50.33*/("""{"""),format.raw/*50.34*/("""
									"""),format.raw/*51.10*/("""document.getElementById('formProforma').submit();
								"""),format.raw/*52.9*/("""}"""),format.raw/*52.10*/("""else"""),format.raw/*52.14*/("""{"""),format.raw/*52.15*/("""
									"""),format.raw/*53.10*/("""alertify.alert('No hay valor de venta a emitir', function () """),format.raw/*53.71*/("""{"""),format.raw/*53.72*/("""}"""),format.raw/*53.73*/(""");
								"""),format.raw/*54.9*/("""}"""),format.raw/*54.10*/("""">
									VENTAS
								</button>
							</td>
						</tr>
					</table>
				</div>

				<table class="table table-sm table-bordered table-condensed table-fluid">
					<TR>
						<td style= "text-align: left; vertical-align:top; width: 44%;">
							<table class="table table-sm table-hover table-bordered table-condensed table-fluid">
								"""),_display_(if(!cliente.rut.equals(null))/*66.39*/{_display_(Seq[Any](format.raw/*66.40*/("""
									"""),format.raw/*67.10*/("""<tr style= "background-color: #F2F5A9;">
										<th style="text-align:left;">CLIENTE</th>
										<th style="text-align:left;">"""),_display_(/*69.41*/cliente/*69.48*/.rut),format.raw/*69.52*/(""" """),format.raw/*69.53*/("""-- """),_display_(/*69.57*/cliente/*69.64*/.nombre),format.raw/*69.71*/("""</th>
									</tr>
								""")))} else {null} ),format.raw/*71.10*/("""
								"""),format.raw/*72.9*/("""<tr style= "background-color: #F2F5A9;">
									<th style="text-align:left;">"""),_display_(/*73.40*/mapDiccionario/*73.54*/.get("BODEGA")),format.raw/*73.68*/("""</th>
									<TH style="text-align:left;">"""),_display_(/*74.40*/bodega/*74.46*/.nombre.toUpperCase()),format.raw/*74.67*/("""</TH>
								</tr>
								<tr style= "background-color: #F2F5A9;">
									<th style="text-align:left;">PROYECTO</th>
									<th style="text-align:left;">"""),_display_(/*78.40*/proyecto/*78.48*/.nickName.toUpperCase()),format.raw/*78.71*/("""</TH>
								</tr>
								<tr style= "background-color: #F2F5A9;">
									<th style="text-align:left;">PERIODO</th>
									<th style="text-align:left;">desde """),_display_(/*82.46*/utilities/*82.55*/.Fechas.DDMMAA(fechaDesde)),format.raw/*82.81*/(""" """),format.raw/*82.82*/("""hasta """),_display_(/*82.89*/utilities/*82.98*/.Fechas.DDMMAA(fechaHasta)),format.raw/*82.124*/("""</TH>
								</tr>
								<tr style= "background-color: #F2F5A9;">
									<th style="text-align:left;">COMERCIAL</th>
									<th style="text-align:left;">"""),_display_(/*86.40*/bodega/*86.46*/.comercial),format.raw/*86.56*/("""</TH>
								</tr>
							</table>

						</td>
						<td>&nbsp;</td>
						<td colspan="7" style= "text-align: left;  width: 34%;">
							<table class="table table-sm table-hover table-bordered table-condensed table-fluid">
								<thead style="background-color: #dddddd">
									<th colspan="2">TOTALES</th>
								</thead>
								<tbody>
									<tr>
										<td style="text-align:left;">NETO:</td>
										<td style="text-align:right;" id="NETO">445555</td>
									</tr>
									<tr>
										<td style="text-align:left;">"""),_display_(/*103.41*/mapDiccionario/*103.55*/.get("IVA")),format.raw/*103.66*/(""" """),_display_(/*103.68*/(tasaIva*100)),format.raw/*103.81*/("""%:</td>
										<td style="text-align:right;" id="IVA">232323</td>
									</tr>
									<tr>
										<th style="text-align:left;">TOTAL:</th>
										<th style="text-align:right;" id="TOTAL">232323</th>
									</tr>
								</tbody>
							</table>
						</td>
					</TR>
				</table>
				
				<div class="table-responsive">
					<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #dddddd">
							<TR>
								<th>GRUPO</th>
								<th>COTI</th>
								<th>CODIGO</th>
								<th>EQUIPO</th>
								<th>MON</th>
								<th>PRECIO<br>UNITARIO</th>
								<th>UN</th>
								<th>CANT</th>
								<th>TOTAL MON<br>ORIGEN</th>
								<th>TOTAL EN<br>"""),_display_(/*129.26*/mon),format.raw/*129.29*/("""</th>
								<th>Nro GUIA</th>
								<th style="min-width: 80px">FECHA</th>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*135.9*/for((nivel1,index1) <- datos.zipWithIndex) yield /*135.51*/ {_display_(Seq[Any](format.raw/*135.53*/("""
								"""),format.raw/*136.9*/("""<TR>
									"""),_display_(/*137.11*/for((nivel2,index2) <- nivel1.drop(1).zipWithIndex) yield /*137.62*/ {_display_(Seq[Any](format.raw/*137.64*/("""
										"""),_display_(if(index2<4)/*138.24*/{_display_(Seq[Any](format.raw/*138.25*/("""
											"""),format.raw/*139.12*/("""<td style="text-align: left;">"""),_display_(/*139.43*/nivel2),format.raw/*139.49*/("""</td>
										""")))}else if(index2==4 || index2==6 || index2==10 || index2==11)/*140.71*/{_display_(Seq[Any](format.raw/*140.72*/("""
											"""),format.raw/*141.12*/("""<td style="text-align: center;">"""),_display_(/*141.45*/nivel2),format.raw/*141.51*/("""</td>
										""")))}else if(index2==7)/*142.30*/{_display_(Seq[Any](format.raw/*142.31*/("""
											"""),format.raw/*143.12*/("""<td class="cantidades" style="text-align: right;">"""),_display_(/*143.63*/nivel2),format.raw/*143.69*/("""</td>
										""")))}else if(index2==9 )/*144.31*/{_display_(Seq[Any](format.raw/*144.32*/("""
											"""),format.raw/*145.12*/("""<td class="totales" style="text-align: right;">"""),_display_(/*145.60*/nivel2),format.raw/*145.66*/("""</td>
										""")))}else/*146.16*/{_display_(Seq[Any](format.raw/*146.17*/("""
											"""),format.raw/*147.12*/("""<td style="text-align: right;">"""),_display_(/*147.44*/nivel2),format.raw/*147.50*/("""</td>
										""")))}),format.raw/*148.12*/("""
									""")))}),format.raw/*149.11*/("""
								"""),format.raw/*150.9*/("""</TR>
							 """)))}),format.raw/*151.10*/("""
						"""),format.raw/*152.7*/("""</tbody>
						<tfoot>
							<TR>
								<th style="background-color: #eeeeee">TOTALES</th>
								<th style="background-color: #eeeeee"></th>
								<th style="background-color: #eeeeee"></th>
								<th style="background-color: #eeeeee"></th>
								<th style="background-color: #eeeeee"></th>
								<th style="background-color: #eeeeee"></th>
								<th style="background-color: #eeeeee"></th>
								<th style="text-align: right;background-color: #eeeeee""><div id="cant"></div></th>
								<th style="background-color: #eeeeee"></th>
								<th style="text-align: right;background-color: #eeeeee""><div id="tot"></div></th>
								<th style="background-color: #eeeeee"></th>
								<th style="background-color: #eeeeee"></th>
							</TR>
						</tfoot>

					</table>
				</div>
			</div>

	
	<form id="excel" class="formulario" method="post" action="/reportFacturaProyectoDetalleHVtaExcel/">
		<input type="hidden" name="id_bodega" value=""""),_display_(/*176.49*/bodega/*176.55*/.getId()),format.raw/*176.63*/("""">
		<input type="hidden" name="fechaDesde" value=""""),_display_(/*177.50*/fechaDesde),format.raw/*177.60*/("""">
		<input type="hidden" name="fechaHasta" value=""""),_display_(/*178.50*/fechaHasta),format.raw/*178.60*/("""">
		<input type="hidden" name="esVenta" value=""""),_display_(/*179.47*/esVenta),format.raw/*179.54*/("""">
		<input type="hidden" name="uf" value=""""),_display_(/*180.42*/uf),format.raw/*180.44*/("""">
		<input type="hidden" name="usd" value=""""),_display_(/*181.43*/usd),format.raw/*181.46*/("""">
		<input type="hidden" name="eur" value=""""),_display_(/*182.43*/eur),format.raw/*182.46*/("""">
	</form>

	<form id="formProforma" class="formulario" method="post" action="/reportFacturaProyectoDetalleHVtaProforma/">
		<input type="hidden" name="id_bodega" value=""""),_display_(/*186.49*/bodega/*186.55*/.getId()),format.raw/*186.63*/("""">
		<input type="hidden" name="fechaDesde" value=""""),_display_(/*187.50*/fechaDesde),format.raw/*187.60*/("""">
		<input type="hidden" name="fechaHasta" value=""""),_display_(/*188.50*/fechaHasta),format.raw/*188.60*/("""">
		<input type="hidden" name="esVenta" value=""""),_display_(/*189.47*/esVenta),format.raw/*189.54*/("""">
		<input type="hidden" name="uf" value=""""),_display_(/*190.42*/uf),format.raw/*190.44*/("""">
		<input type="hidden" name="usd" value=""""),_display_(/*191.43*/usd),format.raw/*191.46*/("""">
		<input type="hidden" name="eur" value=""""),_display_(/*192.43*/eur),format.raw/*192.46*/("""">
		<input type="hidden" name="tipo" value="VENTAS">
		<input type="hidden" id="profneto" name="neto">
		<input type="hidden" id="profiva" name="iva">
		<input type="hidden" id="proftotal" name="total">

		<input type="hidden" id="profAlq" name="alq" value="0">
		<input type="hidden" id="profVta" name="vta">
		<input type="hidden" id="profServ" name="serv" value="0">

	</form>


""")))}),format.raw/*205.2*/("""


"""),format.raw/*208.1*/("""<script type="text/javascript">
		let neto = 0;
	$(document).ready(function() """),format.raw/*210.31*/("""{"""),format.raw/*210.32*/("""
		"""),format.raw/*211.3*/("""let suma = 0;
		document.querySelectorAll('td.cantidades').forEach(td => """),format.raw/*212.60*/("""{"""),format.raw/*212.61*/("""
			"""),format.raw/*213.4*/("""const valor = parseFloat(td.textContent.replace(/,/g, ''));
			if (!isNaN(valor)) """),format.raw/*214.23*/("""{"""),format.raw/*214.24*/("""
				"""),format.raw/*215.5*/("""suma += valor;
			"""),format.raw/*216.4*/("""}"""),format.raw/*216.5*/("""
		"""),format.raw/*217.3*/("""}"""),format.raw/*217.4*/(""");
		$("#cant").text(formatStandar(suma,2));

		suma = 0;
		document.querySelectorAll('td.totales').forEach(td => """),format.raw/*221.57*/("""{"""),format.raw/*221.58*/("""
			"""),format.raw/*222.4*/("""const valor = parseFloat(td.textContent.replace(/,/g, ''));
			if (!isNaN(valor)) """),format.raw/*223.23*/("""{"""),format.raw/*223.24*/("""
				"""),format.raw/*224.5*/("""suma += valor;
			"""),format.raw/*225.4*/("""}"""),format.raw/*225.5*/("""
		"""),format.raw/*226.3*/("""}"""),format.raw/*226.4*/(""");
		$("#tot").text(formatStandar(suma,""""),_display_(/*227.39*/nroDecimales),format.raw/*227.51*/(""""));

		neto = suma;
		let iva = neto * parseFloat(""""),_display_(/*230.33*/tasaIva),format.raw/*230.40*/("""");
		let total = parseFloat(neto) + parseFloat(iva);
		$("#NETO").text(formatStandar(neto,""""),_display_(/*232.40*/nroDecimales),format.raw/*232.52*/(""""));
		$("#IVA").text(formatStandar(iva,""""),_display_(/*233.38*/nroDecimales),format.raw/*233.50*/(""""));
		$("#TOTAL").text(formatStandar(total,""""),_display_(/*234.42*/nroDecimales),format.raw/*234.54*/(""""));

		$("#profneto").val(formatStandar(neto,""""),_display_(/*236.43*/nroDecimales),format.raw/*236.55*/(""""));
		$("#profiva").val(formatStandar(iva,""""),_display_(/*237.41*/nroDecimales),format.raw/*237.53*/(""""));
		$("#proftotal").val(formatStandar(total,""""),_display_(/*238.45*/nroDecimales),format.raw/*238.57*/(""""));

		$("#profVta").val(formatStandar(neto,""""),_display_(/*240.42*/nroDecimales),format.raw/*240.54*/(""""));

		if ($('#tablaPrincipal tbody tr').length > 1) """),format.raw/*242.49*/("""{"""),format.raw/*242.50*/("""
			"""),format.raw/*243.4*/("""$('#tablaPrincipal').DataTable("""),format.raw/*243.35*/("""{"""),format.raw/*243.36*/("""
				"""),format.raw/*244.5*/(""""fixedHeader": true,
				"paging": false,
				"info": false,
				"searching": false,
				"order": false,
				"language": """),format.raw/*249.17*/("""{"""),format.raw/*249.18*/("""
					"""),format.raw/*250.6*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
				"""),format.raw/*251.5*/("""}"""),format.raw/*251.6*/("""
			"""),format.raw/*252.4*/("""}"""),format.raw/*252.5*/(""");
		"""),format.raw/*253.3*/("""}"""),format.raw/*253.4*/("""

		"""),format.raw/*255.3*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*256.2*/("""}"""),format.raw/*256.3*/(""");


</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,idTipoUsuario:String,datos:List[List[String]],bodega:tables.BodegaEmpresa,esVenta:String,fechaDesde:String,fechaHasta:String,usd:Double,eur:Double,uf:Double,cliente:tables.Cliente,proyecto:tables.Proyecto,oc:String,nroDecimales:Long,mon:String,tasaIva:Double): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,idTipoUsuario,datos,bodega,esVenta,fechaDesde,fechaHasta,usd,eur,uf,cliente,proyecto,oc,nroDecimales,mon,tasaIva)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,String,List[List[String]],tables.BodegaEmpresa,String,String,String,Double,Double,Double,tables.Cliente,tables.Proyecto,String,Long,String,Double) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,idTipoUsuario,datos,bodega,esVenta,fechaDesde,fechaHasta,usd,eur,uf,cliente,proyecto,oc,nroDecimales,mon,tasaIva) => apply(mapDiccionario,mapPermiso,userMnu,idTipoUsuario,datos,bodega,esVenta,fechaDesde,fechaHasta,usd,eur,uf,cliente,proyecto,oc,nroDecimales,mon,tasaIva)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reportFacturaProyectoDetalleHVta.scala.html
                  HASH: d73c23bfd1d949c9fa6c958bb55e6a68a9dc7644
                  MATRIX: 1179->1|1658->387|1690->394|1706->402|1745->404|1774->408|1842->456|1873->461|1918->485|1947->488|1988->508|2019->512|2096->563|2367->812|2400->818|2556->983|2585->1032|2614->1112|2643->1134|2672->1194|2701->1238|2730->1292|2759->1311|2794->1319|3426->1924|3449->1938|3486->1954|3674->2114|3703->2115|3741->2125|3826->2183|3855->2184|3887->2188|3916->2189|3954->2199|4043->2260|4072->2261|4101->2262|4139->2273|4168->2274|4572->2651|4611->2652|4649->2662|4809->2795|4825->2802|4850->2806|4879->2807|4910->2811|4926->2818|4954->2825|5028->2855|5064->2864|5171->2944|5194->2958|5229->2972|5301->3017|5316->3023|5358->3044|5545->3204|5562->3212|5606->3235|5798->3400|5816->3409|5863->3435|5892->3436|5926->3443|5944->3452|5992->3478|6180->3639|6195->3645|6226->3655|6797->4198|6821->4212|6854->4223|6884->4225|6919->4238|7708->4999|7733->5002|7889->5131|7948->5173|7989->5175|8026->5184|8069->5199|8137->5250|8178->5252|8230->5276|8270->5277|8311->5289|8370->5320|8398->5326|8494->5402|8534->5403|8575->5415|8636->5448|8664->5454|8719->5489|8759->5490|8800->5502|8879->5553|8907->5559|8963->5595|9003->5596|9044->5608|9120->5656|9148->5662|9189->5683|9229->5684|9270->5696|9330->5728|9358->5734|9407->5751|9450->5762|9487->5771|9534->5786|9569->5793|10558->6754|10574->6760|10604->6768|10684->6820|10716->6830|10796->6882|10828->6892|10905->6941|10934->6948|11006->6992|11030->6994|11103->7039|11128->7042|11201->7087|11226->7090|11426->7262|11442->7268|11472->7276|11552->7328|11584->7338|11664->7390|11696->7400|11773->7449|11802->7456|11874->7500|11898->7502|11971->7547|11996->7550|12069->7595|12094->7598|12509->7982|12540->7985|12647->8063|12677->8064|12708->8067|12810->8140|12840->8141|12872->8145|12983->8227|13013->8228|13046->8233|13092->8251|13121->8252|13152->8255|13181->8256|13324->8370|13354->8371|13386->8375|13497->8457|13527->8458|13560->8463|13606->8481|13635->8482|13666->8485|13695->8486|13764->8527|13798->8539|13879->8592|13908->8599|14029->8692|14063->8704|14133->8746|14167->8758|14241->8804|14275->8816|14351->8864|14385->8876|14458->8921|14492->8933|14569->8982|14603->8994|14678->9041|14712->9053|14795->9107|14825->9108|14857->9112|14917->9143|14947->9144|14980->9149|15130->9270|15160->9271|15194->9277|15294->9349|15323->9350|15355->9354|15384->9355|15417->9360|15446->9361|15478->9365|15572->9431|15601->9432
                  LINES: 28->1|37->6|38->7|38->7|38->7|40->9|40->9|42->11|42->11|43->12|43->12|45->14|46->15|47->16|49->18|53->22|54->23|55->24|56->25|57->26|58->27|59->28|60->29|61->30|78->47|78->47|78->47|81->50|81->50|82->51|83->52|83->52|83->52|83->52|84->53|84->53|84->53|84->53|85->54|85->54|97->66|97->66|98->67|100->69|100->69|100->69|100->69|100->69|100->69|100->69|102->71|103->72|104->73|104->73|104->73|105->74|105->74|105->74|109->78|109->78|109->78|113->82|113->82|113->82|113->82|113->82|113->82|113->82|117->86|117->86|117->86|134->103|134->103|134->103|134->103|134->103|160->129|160->129|166->135|166->135|166->135|167->136|168->137|168->137|168->137|169->138|169->138|170->139|170->139|170->139|171->140|171->140|172->141|172->141|172->141|173->142|173->142|174->143|174->143|174->143|175->144|175->144|176->145|176->145|176->145|177->146|177->146|178->147|178->147|178->147|179->148|180->149|181->150|182->151|183->152|207->176|207->176|207->176|208->177|208->177|209->178|209->178|210->179|210->179|211->180|211->180|212->181|212->181|213->182|213->182|217->186|217->186|217->186|218->187|218->187|219->188|219->188|220->189|220->189|221->190|221->190|222->191|222->191|223->192|223->192|236->205|239->208|241->210|241->210|242->211|243->212|243->212|244->213|245->214|245->214|246->215|247->216|247->216|248->217|248->217|252->221|252->221|253->222|254->223|254->223|255->224|256->225|256->225|257->226|257->226|258->227|258->227|261->230|261->230|263->232|263->232|264->233|264->233|265->234|265->234|267->236|267->236|268->237|268->237|269->238|269->238|271->240|271->240|273->242|273->242|274->243|274->243|274->243|275->244|280->249|280->249|281->250|282->251|282->251|283->252|283->252|284->253|284->253|286->255|287->256|287->256
                  -- GENERATED --
              */
          