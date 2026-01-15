
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

object reportFacturaProyectoDetalleHServ extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template11[Map[String,String],Map[String,String],utilities.UserMnu,List[String],List[Double],tables.BodegaEmpresa,tables.Proyecto,tables.Cliente,Long,List[List[String]],Double,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu, 
fechas: List[String], tasaCambio: List[Double],
bodega: tables.BodegaEmpresa, proyecto: tables.Proyecto, cliente: tables.Cliente, cantDec: Long,
groupPorClaseServicioEquipo: List[List[String]], tasaIva: Double):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*5.1*/("""
	"""),format.raw/*6.2*/("""<!-- ANCLA CABECERAS DE TABLAS -->
	<style type="text/css"> 
        thead tr th """),format.raw/*8.21*/("""{"""),format.raw/*8.22*/(""" 
            """),format.raw/*9.13*/("""position: sticky;
            top: 0;
        """),format.raw/*11.9*/("""}"""),format.raw/*11.10*/("""
    """),format.raw/*12.5*/("""</style>

	
	"""),_display_(/*15.3*/main("")/*15.11*/ {_display_(Seq[Any](format.raw/*15.13*/("""

		"""),_display_(/*17.4*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*17.52*/("""
		"""),format.raw/*18.3*/("""<div id="mostrarmostrar" style="display:none;">
			"""),_display_(/*19.5*/barraTitulo(mapDiccionario, "DETALLE EP/FACTURA PROFORMA SERVICIOS " + mapDiccionario.get("BODEGA") + "/PROYECTO: " + bodega.getNombre(),
				"PERIODO: desde " + fechas.get(2) +" --- hasta: " +  fechas.get(3))),format.raw/*20.72*/("""
				
			"""),format.raw/*22.4*/("""<div class="noprint">
				<table class="table table-sm table-condensed table-fluid">
					<tr>
						<td width="25%">
"""),format.raw/*26.37*/("""
"""),format.raw/*27.48*/("""
"""),format.raw/*28.79*/("""
"""),format.raw/*29.21*/("""
"""),format.raw/*30.59*/("""
"""),format.raw/*31.43*/("""
"""),format.raw/*32.53*/("""
"""),format.raw/*33.18*/("""
						"""),format.raw/*34.7*/("""</td>
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
						<td width="25%">
							HACER """),_display_(/*50.15*/mapDiccionario/*50.29*/.get("PROFORMA")),format.raw/*50.45*/(""":
							<button id="btnArr" type="button" class="btn btn-mini btn-info" tabindex="-1"
							onclick=" this.disabled = true;
							if(parseFloat(neto) > 0)"""),format.raw/*53.32*/("""{"""),format.raw/*53.33*/("""
								"""),format.raw/*54.9*/("""document.getElementById('formProforma').submit();
							"""),format.raw/*55.8*/("""}"""),format.raw/*55.9*/("""else"""),format.raw/*55.13*/("""{"""),format.raw/*55.14*/("""
								"""),format.raw/*56.9*/("""alertify.alert('No hay valor de servicio a emitir', function () """),format.raw/*56.73*/("""{"""),format.raw/*56.74*/("""}"""),format.raw/*56.75*/(""");
							"""),format.raw/*57.8*/("""}"""),format.raw/*57.9*/("""">
								SERVICIOS
							</button>
						</td>
					</tr>
				</table>
			</div>
			
			<form id="excel" class="formulario" method="post" action="/reportFacturaProyectoDetalleHServExcel/">
				<input type="hidden" name="fechaDesde" value=""""),_display_(/*66.52*/fechas/*66.58*/.get(0)),format.raw/*66.65*/("""">
				<input type="hidden" name="fechaHasta" value=""""),_display_(/*67.52*/fechas/*67.58*/.get(1)),format.raw/*67.65*/("""">
				<input type="hidden" name="uf" value=""""),_display_(/*68.44*/tasaCambio/*68.54*/.get(0)),format.raw/*68.61*/("""">
				<input type="hidden" name="usd" value=""""),_display_(/*69.45*/tasaCambio/*69.55*/.get(1)),format.raw/*69.62*/("""">
				<input type="hidden" name="eur" value=""""),_display_(/*70.45*/tasaCambio/*70.55*/.get(2)),format.raw/*70.62*/("""">
				<input type="hidden" name="id_bodega" value=""""),_display_(/*71.51*/bodega/*71.57*/.getId()),format.raw/*71.65*/("""">
			</form>

			<form id="formProforma" class="formulario" method="post" action="/reportFacturaProyectoDetalleHServProforma/">
				<input type="hidden" name="id_bodega" value=""""),_display_(/*75.51*/bodega/*75.57*/.getId()),format.raw/*75.65*/("""">
				<input type="hidden" name="fechaDesde" value=""""),_display_(/*76.52*/fechas/*76.58*/.get(0)),format.raw/*76.65*/("""">
				<input type="hidden" name="fechaHasta" value=""""),_display_(/*77.52*/fechas/*77.58*/.get(1)),format.raw/*77.65*/("""">
				<input type="hidden" name="uf" value=""""),_display_(/*78.44*/tasaCambio/*78.54*/.get(0)),format.raw/*78.61*/("""">
				<input type="hidden" name="usd" value=""""),_display_(/*79.45*/tasaCambio/*79.55*/.get(1)),format.raw/*79.62*/("""">
				<input type="hidden" name="eur" value=""""),_display_(/*80.45*/tasaCambio/*80.55*/.get(2)),format.raw/*80.62*/("""">
				<input type="hidden" name="tipo" value="SERVICIOS">
				<input type="hidden" id="profneto" name="neto">
				<input type="hidden" id="profiva" name="iva">
				<input type="hidden" id="proftotal" name="total">

				<input type="hidden" id="profAlq" name="alq" value="0">
				<input type="hidden" id="profVta" name="vta" value="0">
				<input type="hidden" id="profServ" name="serv">

			</form>
		


			
			<table class="table table-sm table-bordered table-condensed table-fluid">
					 <TR>
						 <td style= "text-align: left; vertical-align:top; width: 44%;">
							<table class="table table-sm table-hover table-bordered table-condensed table-fluid">
								"""),_display_(if(!cliente.rut.equals(null))/*99.39*/{_display_(Seq[Any](format.raw/*99.40*/("""
									"""),format.raw/*100.10*/("""<tr style= "background-color: #F2F5A9;">
										<th style="text-align:left;">CLIENTE</th>
										<th style="text-align:left;">"""),_display_(/*102.41*/cliente/*102.48*/.rut),format.raw/*102.52*/(""" """),format.raw/*102.53*/("""-- """),_display_(/*102.57*/cliente/*102.64*/.nombre),format.raw/*102.71*/("""</th>
									</tr>
								""")))} else {null} ),format.raw/*104.10*/("""
								"""),format.raw/*105.9*/("""<tr style= "background-color: #F2F5A9;">
									<th style="text-align:left;">"""),_display_(/*106.40*/mapDiccionario/*106.54*/.get("BODEGA")),format.raw/*106.68*/("""</th>
									<TH style="text-align:left;">"""),_display_(/*107.40*/bodega/*107.46*/.nombre.toUpperCase()),format.raw/*107.67*/("""</TH>
								</tr>
								<tr style= "background-color: #F2F5A9;">
									<th style="text-align:left;">PROYECTO</th>
									<th style="text-align:left;">"""),_display_(/*111.40*/proyecto/*111.48*/.nickName.toUpperCase()),format.raw/*111.71*/("""</TH>
								</tr>
								<tr style= "background-color: #F2F5A9;">
									<th style="text-align:left;">PERIODO</th>
									<th style="text-align:left;">desde """),_display_(/*115.46*/fechas/*115.52*/.get(2)),format.raw/*115.59*/(""" """),format.raw/*115.60*/("""hasta """),_display_(/*115.67*/fechas/*115.73*/.get(3)),format.raw/*115.80*/("""</TH>
								</tr>
								<tr style= "background-color: #F2F5A9;">
									<th style="text-align:left;">COMERCIAL</th>
									<th style="text-align:left;">"""),_display_(/*119.40*/bodega/*119.46*/.getNameComercial),format.raw/*119.63*/("""</TH>
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
										 <td style="text-align:left;">"""),_display_(/*135.42*/mapDiccionario/*135.56*/.get("IVA")),format.raw/*135.67*/(""" """),_display_(/*135.69*/(tasaIva*100)),format.raw/*135.82*/("""%:</td>
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
		
			<table id="tablaResumen" class="table table-sm table-hover table-bordered table-condensed table-fluid" >
				<thead class="header">
		        	<TR> 
						<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">FAMILIA</TH>
						<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">NRO.COTI</TH>
						<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">COD-SERVICIO</TH>
						<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">SERVICIO</TH>
						<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">COD-EQUIPO</TH>
						<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">EQUIPO</TH>
						<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">UN</TH>
						<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">CANT</TH>
						<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">TOTAL</TH>
						
						<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">CANT MIN</TH>
						<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">TOTAL MINIMO</TH>
						<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">TOTAL ADICIONAL</TH>
						<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">GRAN TOTAL</TH>
					</TR>
				</thead>
				<tbody>
					"""),_display_(/*168.7*/for((inicial,index) <- groupPorClaseServicioEquipo.zipWithIndex) yield /*168.71*/{_display_(Seq[Any](format.raw/*168.72*/("""
						"""),format.raw/*169.7*/("""<tr>
							<td style="text-align: left;">"""),_display_(/*170.39*/inicial/*170.46*/.get(0)),format.raw/*170.53*/("""</td>
							<td style="text-align: center;">"""),_display_(/*171.41*/inicial/*171.48*/.get(12)),format.raw/*171.56*/("""</td>
							<td style="text-align: center;">"""),_display_(/*172.41*/inicial/*172.48*/.get(1)),format.raw/*172.55*/("""</td>
							<td style="text-align: left;">"""),_display_(/*173.39*/inicial/*173.46*/.get(2)),format.raw/*173.53*/("""</td>
							<td style="text-align: center;">"""),_display_(/*174.41*/inicial/*174.48*/.get(3)),format.raw/*174.55*/("""</td>
							<td style="text-align: left;">"""),_display_(/*175.39*/inicial/*175.46*/.get(4)),format.raw/*175.53*/("""</td>
							<td style="text-align: center;">"""),_display_(/*176.41*/inicial/*176.48*/.get(5)),format.raw/*176.55*/("""</td>
							<td style="text-align: right;" class="resumenTot1">"""),_display_(/*177.60*/inicial/*177.67*/.get(6)),format.raw/*177.74*/("""</td>
							<td style="text-align: right;" class="resumenTot2">"""),_display_(/*178.60*/inicial/*178.67*/.get(7)),format.raw/*178.74*/("""</td>
							
							<td style="text-align: right;" class="resumenTot21">"""),_display_(/*180.61*/inicial/*180.68*/.get(8)),format.raw/*180.75*/("""</td>
							<td style="text-align: right;" class="resumenTot22">"""),_display_(/*181.61*/inicial/*181.68*/.get(9)),format.raw/*181.75*/("""</td>
							<td style="text-align: right;" class="resumenTot23">"""),_display_(/*182.61*/inicial/*182.68*/.get(10)),format.raw/*182.76*/("""</td>
							<td style="text-align: right;" class="resumenTot24">"""),_display_(/*183.61*/inicial/*183.68*/.get(11)),format.raw/*183.76*/("""</td>
		 			""")))}),format.raw/*184.8*/("""
					"""),format.raw/*185.6*/("""<TR>
						<th style="background-color: #eeeeee" colspan="7">TOTALES NETO</th>
						<th style="background-color: #eeeeee"><div align="right" id="resumenTot1"></div></th>
						<th style="background-color: #eeeeee"><div align="right" id="resumenTot2"></div></th>
						<th style="background-color: #eeeeee"><div align="right" id="resumenTot21"></div></th>
						<th style="background-color: #eeeeee"><div align="right" id="resumenTot22"></div></th>
						<th style="background-color: #eeeeee"><div align="right" id="resumenTot23"></div></th>
						<th style="background-color: #eeeeee"><div align="right" id="resumenTot24"></div></th>
						
					</tr>
				</tbody>
			</table>

		</div>

		
		<script type="text/javascript">
				let neto = 0;
			  $(document).ready(function () """),format.raw/*203.36*/("""{"""),format.raw/*203.37*/("""

					"""),format.raw/*205.6*/("""let subtotal = 0;
					$(".resumenTot1").each(function() """),format.raw/*206.40*/("""{"""),format.raw/*206.41*/("""
							"""),format.raw/*207.8*/("""let val = $(this).text().replace(/,/g,'')  || 0;
							let numero = parseFloat(val) || 0;
							subtotal += parseFloat(numero);
					"""),format.raw/*210.6*/("""}"""),format.raw/*210.7*/(""");
					$("#resumenTot1").text(formatStandar(subtotal,'2'));
					
					subtotal = 0;
					$(".resumenTot2").each(function() """),format.raw/*214.40*/("""{"""),format.raw/*214.41*/("""
						"""),format.raw/*215.7*/("""let val = $(this).text().replace(/,/g,'');
						let numero = parseFloat(val) || 0;
						subtotal += parseFloat(numero);
					"""),format.raw/*218.6*/("""}"""),format.raw/*218.7*/(""");
					$("#resumenTot2").text(formatStandar(subtotal,'"""),_display_(/*219.54*/cantDec),format.raw/*219.61*/("""'));
					
					subtotal = 0;
					$(".total7").each(function() """),format.raw/*222.35*/("""{"""),format.raw/*222.36*/("""
						"""),format.raw/*223.7*/("""let val = $(this).text().replace(/,/g,'');
						let numero = parseFloat(val) || 0;
						subtotal += parseFloat(numero);
					"""),format.raw/*226.6*/("""}"""),format.raw/*226.7*/(""");
					$("#total7").text(formatStandar(subtotal,'"""),_display_(/*227.49*/cantDec),format.raw/*227.56*/("""'));
					
					subtotal = 0;
					$(".resumenTot21").each(function() """),format.raw/*230.41*/("""{"""),format.raw/*230.42*/("""
						"""),format.raw/*231.7*/("""let val = $(this).text().replace(/,/g,'');
						let numero = parseFloat(val) || 0;
						subtotal += parseFloat(numero);
					"""),format.raw/*234.6*/("""}"""),format.raw/*234.7*/(""");
					$("#resumenTot21").text(formatStandar(subtotal,'2'));
					
					subtotal = 0;
					$(".resumenTot22").each(function() """),format.raw/*238.41*/("""{"""),format.raw/*238.42*/("""
						"""),format.raw/*239.7*/("""let val = $(this).text().replace(/,/g,'');
						let numero = parseFloat(val) || 0;
						subtotal += parseFloat(numero);
					"""),format.raw/*242.6*/("""}"""),format.raw/*242.7*/(""");
					$("#resumenTot22").text(formatStandar(subtotal,'"""),_display_(/*243.55*/cantDec),format.raw/*243.62*/("""'));
					
					subtotal = 0;
					$(".resumenTot23").each(function() """),format.raw/*246.41*/("""{"""),format.raw/*246.42*/("""
						"""),format.raw/*247.7*/("""let val = $(this).text().replace(/,/g,'');
						let numero = parseFloat(val) || 0;
						subtotal += parseFloat(numero);
					"""),format.raw/*250.6*/("""}"""),format.raw/*250.7*/(""");
					$("#resumenTot23").text(formatStandar(subtotal,'"""),_display_(/*251.55*/cantDec),format.raw/*251.62*/("""'));
					
					subtotal = 0;
					$(".resumenTot24").each(function() """),format.raw/*254.41*/("""{"""),format.raw/*254.42*/("""
						"""),format.raw/*255.7*/("""let val = $(this).text().replace(/,/g,'');
						let numero = parseFloat(val) || 0;
						subtotal += parseFloat(numero);
					"""),format.raw/*258.6*/("""}"""),format.raw/*258.7*/(""");
					$("#resumenTot24").text(formatStandar(subtotal,'"""),_display_(/*259.55*/cantDec),format.raw/*259.62*/("""'));

				  neto = subtotal;
				  let iva = neto * parseFloat(""""),_display_(/*262.37*/tasaIva),format.raw/*262.44*/("""");
				  let total = parseFloat(neto) + parseFloat(iva);
				  $("#NETO").text(formatStandar(neto,""""),_display_(/*264.44*/cantDec),format.raw/*264.51*/(""""));
				  $("#IVA").text(formatStandar(iva,""""),_display_(/*265.42*/cantDec),format.raw/*265.49*/(""""));
				  $("#TOTAL").text(formatStandar(total,""""),_display_(/*266.46*/cantDec),format.raw/*266.53*/(""""));

				  $("#profneto").val(formatStandar(neto,""""),_display_(/*268.47*/cantDec),format.raw/*268.54*/(""""));
				  $("#profiva").val(formatStandar(iva,""""),_display_(/*269.45*/cantDec),format.raw/*269.52*/(""""));
				  $("#proftotal").val(formatStandar(total,""""),_display_(/*270.49*/cantDec),format.raw/*270.56*/(""""));

				  $("#profServ").val(formatStandar(neto,""""),_display_(/*272.47*/cantDec),format.raw/*272.54*/(""""));

				    document.getElementById('mostrarmostrar').style.display="block"; 
						
			   """),format.raw/*276.7*/("""}"""),format.raw/*276.8*/(""");


		</script>
		

		
		
""")))}),format.raw/*284.2*/("""
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,fechas:List[String],tasaCambio:List[Double],bodega:tables.BodegaEmpresa,proyecto:tables.Proyecto,cliente:tables.Cliente,cantDec:Long,groupPorClaseServicioEquipo:List[List[String]],tasaIva:Double): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,fechas,tasaCambio,bodega,proyecto,cliente,cantDec,groupPorClaseServicioEquipo,tasaIva)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[String],List[Double],tables.BodegaEmpresa,tables.Proyecto,tables.Cliente,Long,List[List[String]],Double) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,fechas,tasaCambio,bodega,proyecto,cliente,cantDec,groupPorClaseServicioEquipo,tasaIva) => apply(mapDiccionario,mapPermiso,userMnu,fechas,tasaCambio,bodega,proyecto,cliente,cantDec,groupPorClaseServicioEquipo,tasaIva)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reportFacturaProyectoDetalleHServ.scala.html
                  HASH: 3d382361d28e4c97fc82b780fe202bd4ae14931e
                  MATRIX: 1143->1|1545->310|1573->312|1681->393|1709->394|1750->408|1823->454|1852->455|1884->460|1924->474|1941->482|1981->484|2012->489|2081->537|2111->540|2189->592|2419->801|2455->810|2601->964|2630->1012|2659->1091|2688->1112|2717->1171|2746->1214|2775->1267|2804->1285|2838->1292|3431->1858|3454->1872|3491->1888|3676->2045|3705->2046|3741->2055|3825->2112|3853->2113|3885->2117|3914->2118|3950->2127|4042->2191|4071->2192|4100->2193|4137->2203|4165->2204|4435->2447|4450->2453|4478->2460|4559->2514|4574->2520|4602->2527|4675->2573|4694->2583|4722->2590|4796->2637|4815->2647|4843->2654|4917->2701|4936->2711|4964->2718|5044->2771|5059->2777|5088->2785|5294->2964|5309->2970|5338->2978|5419->3032|5434->3038|5462->3045|5543->3099|5558->3105|5586->3112|5659->3158|5678->3168|5706->3175|5780->3222|5799->3232|5827->3239|5901->3286|5920->3296|5948->3303|6674->4002|6713->4003|6752->4013|6913->4146|6930->4153|6956->4157|6986->4158|7018->4162|7035->4169|7064->4176|7139->4206|7176->4215|7284->4295|7308->4309|7344->4323|7417->4368|7433->4374|7476->4395|7664->4555|7682->4563|7727->4586|7920->4751|7936->4757|7965->4764|7995->4765|8030->4772|8046->4778|8075->4785|8264->4946|8280->4952|8319->4969|8902->5524|8926->5538|8959->5549|8989->5551|9024->5564|10798->7311|10879->7375|10919->7376|10954->7383|11025->7426|11042->7433|11071->7440|11145->7486|11162->7493|11192->7501|11266->7547|11283->7554|11312->7561|11384->7605|11401->7612|11430->7619|11504->7665|11521->7672|11550->7679|11622->7723|11639->7730|11668->7737|11742->7783|11759->7790|11788->7797|11881->7862|11898->7869|11927->7876|12020->7941|12037->7948|12066->7955|12168->8029|12185->8036|12214->8043|12308->8109|12325->8116|12354->8123|12448->8189|12465->8196|12495->8204|12589->8270|12606->8277|12636->8285|12680->8298|12714->8304|13522->9083|13552->9084|13587->9091|13673->9148|13703->9149|13739->9157|13902->9292|13931->9293|14085->9418|14115->9419|14150->9426|14305->9553|14334->9554|14418->9610|14447->9617|14540->9681|14570->9682|14605->9689|14760->9816|14789->9817|14868->9868|14897->9875|14996->9945|15026->9946|15061->9953|15216->10080|15245->10081|15401->10208|15431->10209|15466->10216|15621->10343|15650->10344|15735->10401|15764->10408|15863->10478|15893->10479|15928->10486|16083->10613|16112->10614|16197->10671|16226->10678|16325->10748|16355->10749|16390->10756|16545->10883|16574->10884|16659->10941|16688->10948|16781->11013|16810->11020|16939->11121|16968->11128|17042->11174|17071->11181|17149->11231|17178->11238|17258->11290|17287->11297|17364->11346|17393->11353|17474->11406|17503->11413|17583->11465|17612->11472|17733->11565|17762->11566|17821->11594
                  LINES: 28->1|36->5|37->6|39->8|39->8|40->9|42->11|42->11|43->12|46->15|46->15|46->15|48->17|48->17|49->18|50->19|51->20|53->22|57->26|58->27|59->28|60->29|61->30|62->31|63->32|64->33|65->34|81->50|81->50|81->50|84->53|84->53|85->54|86->55|86->55|86->55|86->55|87->56|87->56|87->56|87->56|88->57|88->57|97->66|97->66|97->66|98->67|98->67|98->67|99->68|99->68|99->68|100->69|100->69|100->69|101->70|101->70|101->70|102->71|102->71|102->71|106->75|106->75|106->75|107->76|107->76|107->76|108->77|108->77|108->77|109->78|109->78|109->78|110->79|110->79|110->79|111->80|111->80|111->80|130->99|130->99|131->100|133->102|133->102|133->102|133->102|133->102|133->102|133->102|135->104|136->105|137->106|137->106|137->106|138->107|138->107|138->107|142->111|142->111|142->111|146->115|146->115|146->115|146->115|146->115|146->115|146->115|150->119|150->119|150->119|166->135|166->135|166->135|166->135|166->135|199->168|199->168|199->168|200->169|201->170|201->170|201->170|202->171|202->171|202->171|203->172|203->172|203->172|204->173|204->173|204->173|205->174|205->174|205->174|206->175|206->175|206->175|207->176|207->176|207->176|208->177|208->177|208->177|209->178|209->178|209->178|211->180|211->180|211->180|212->181|212->181|212->181|213->182|213->182|213->182|214->183|214->183|214->183|215->184|216->185|234->203|234->203|236->205|237->206|237->206|238->207|241->210|241->210|245->214|245->214|246->215|249->218|249->218|250->219|250->219|253->222|253->222|254->223|257->226|257->226|258->227|258->227|261->230|261->230|262->231|265->234|265->234|269->238|269->238|270->239|273->242|273->242|274->243|274->243|277->246|277->246|278->247|281->250|281->250|282->251|282->251|285->254|285->254|286->255|289->258|289->258|290->259|290->259|293->262|293->262|295->264|295->264|296->265|296->265|297->266|297->266|299->268|299->268|300->269|300->269|301->270|301->270|303->272|303->272|307->276|307->276|315->284
                  -- GENERATED --
              */
          