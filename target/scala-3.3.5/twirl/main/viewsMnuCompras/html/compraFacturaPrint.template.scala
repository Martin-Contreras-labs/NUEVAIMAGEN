
package viewsMnuCompras.html

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

object compraFacturaPrint extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,tables.Factura,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
factura: tables.Factura, detalleFactura: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "DETALLE DE LA COMPRA","")),format.raw/*9.58*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			
			<div class="noprint">
				<table class="table table-sm table-condensed table-fluid">
					<tr>
						<td>
							<div align="center">
								<button type="button"  class="btn btn-sm btn-success" onclick="document.getElementById('excel').submit()">
									Exportar a Excel
								</button>
							</div>
						</td>
					</tr>
				</table>
			</div>
			
			<form id="excel" class="formulario" method="post" action="/compraFacturaPrintExcel/">
				<input type="hidden" name="id_factura" value=""""),_display_(/*28.52*/factura/*28.59*/.getId()),format.raw/*28.67*/("""">
			</form>
			
				<table class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<tr>
							<td  width="350px">
								<div class="input-group">
							  		<div class="input-group-prepend">
							    		<span class="input-group-text" id="basic-addon1">"""),_display_(/*37.64*/mapDiccionario/*37.78*/.get("RUT")),format.raw/*37.89*/(""" """),format.raw/*37.90*/("""PROVEEDOR</span>
							  		</div>
  									<input type="text" class="form-control"
  										value=""""),_display_(/*40.21*/factura/*40.28*/.getRut()),format.raw/*40.37*/("""" 
  										readonly>
								</div>
							</td>
							<td>
								<div class="input-group">
							  		<div class="input-group-prepend">
							    		<span class="input-group-text" id="basic-addon1">Nombre Proveedor</span>
							  		</div>
										<input type="text" class="form-control left"
											value=""""),_display_(/*50.20*/factura/*50.27*/.getNickNameProveedor()),format.raw/*50.50*/(""""
											readonly>
								</div>
							</td>
							<td width="250px">
								<div class="input-group">
							  		<div class="input-group-prepend">
							    		<span class="input-group-text" id="basic-addon1">Nro.Documento</span>
							  		</div>
							  		<input type="text" class="form-control left"
											value = """"),_display_(/*60.22*/factura/*60.29*/.getNumero()),format.raw/*60.41*/(""""
											readonly>
								</div>
							</td>
							<td width="230px">
								<div class="input-group">
							  		<div class="input-group-prepend">
							    		<span class="input-group-text" id="basic-addon1">Fecha</span>
							  		</div>
							  		<input type="date" class="form-control center"
							  			value=""""),_display_(/*70.21*/utilities/*70.30*/.Fechas.AAMMDD(factura.getFecha())),format.raw/*70.64*/(""""
										readonly>
								</div>
							</td>
							<td>
								
							</td>
						</tr>
						<tr>
							<td colspan="6">
								<div class="input-group">
							  		<div class="input-group-prepend">
							    		<span class="input-group-text" id="basic-addon1">Observaciones</span>
							  		</div>
  									<textarea class="form-control" 
  										name="observaciones" readonly>"""),_display_(/*85.44*/factura/*85.51*/.getObservaciones()),format.raw/*85.70*/("""</textarea>
								</div>
							</td>
						</tr>
					</thead>
				</table>
				<hr>
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH width="10%">CODIGO</TH>
							<TH width="20%">EQUIPO</TH>
							<TH width="4%">KG</TH>
					        <TH width="4%">M2</TH>
					        <TH width="3%">UN</TH>
							<TH width="8%">CANT</TH>
							<TH width="3%">MON</TH>
							<TH width="10%">P.UNITARIO<br>COMPRA</TH>
							<TH width="10%">TOTAL</TH>
							<TH width="15%">DESTINO</TH>
							<TH width="4%">TOT.KG</TH>
							<TH width="4%">TOT.M2</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*110.8*/for((lista1, index) <- detalleFactura.zipWithIndex) yield /*110.59*/{_display_(Seq[Any](format.raw/*110.60*/("""
							"""),format.raw/*111.8*/("""<TR>
								<td  style="text-align:left;">"""),_display_(/*112.40*/lista1/*112.46*/.get(1)),format.raw/*112.53*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*113.40*/lista1/*113.46*/.get(2)),format.raw/*113.53*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*114.41*/lista1/*114.47*/.get(14)),format.raw/*114.55*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*115.41*/lista1/*115.47*/.get(15)),format.raw/*115.55*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*116.42*/lista1/*116.48*/.get(3)),format.raw/*116.55*/("""</td>
								<td  style="text-align:center;">
									<input type="text" class="cantidad form-control right"
										value=""""),_display_(/*119.19*/lista1/*119.25*/.get(4)),format.raw/*119.32*/(""""
										readonly>
								</td>
								<td  style="text-align:center;">"""),_display_(/*122.42*/lista1/*122.48*/.get(6)),format.raw/*122.55*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*123.42*/lista1/*123.48*/.get(7)),format.raw/*123.55*/("""</td>
								<td  style="text-align:center;">
									<div class="total" id="total_index" align="right">"""),_display_(/*125.61*/lista1/*125.67*/.get(8)),format.raw/*125.74*/("""</div>
								</td>
								<td  style="text-align:left;">"""),_display_(/*127.40*/lista1/*127.46*/.get(12)),format.raw/*127.54*/("""</td>
								<td><div class="kg" id="totKG_index" align="right">"""),_display_(/*128.61*/lista1/*128.67*/.get(16)),format.raw/*128.75*/("""</div></td>
								<td><div class="m2" id="totM2_index" align="right">"""),_display_(/*129.61*/lista1/*129.67*/.get(17)),format.raw/*129.75*/("""</div></td>
							</TR>
			 			""")))}),format.raw/*131.9*/("""
						"""),format.raw/*132.7*/("""<TR style="background-color: #eeeeee">
					        <td></td>
							<td>TOTALES</td>
							<td></td>
							<td></td>
							<td></td>
							<td><div id="totalCantidad" align="right">0.00</div></td>
							<td></td>
							<td></td>
							<td><div id="totalTotal" align="right">0.00</div></td>
							<td></td>
							<td><div id="totalKG" align="right">0.00</div></td>
							<td><div id="totalM2" align="right">0.00</div></td>
						</TR>
					</tbody>
				</table>
			</div>
		</div>
		<div class="noprint" align="left">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
					<input type="button"  value="Volver" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/listComprasPorCompra/';">
				</div>
			</div>
		</div>
	</div>

""")))}),format.raw/*159.2*/("""




"""),format.raw/*164.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*166.31*/("""{"""),format.raw/*166.32*/("""
		  """),format.raw/*167.5*/("""sumaTotales();
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*169.2*/("""}"""),format.raw/*169.3*/(""");
	
	const sumaTotales = () => """),format.raw/*171.28*/("""{"""),format.raw/*171.29*/("""
		 """),format.raw/*172.4*/("""let sum = 0;
			$(".cantidad").each(function() """),format.raw/*173.35*/("""{"""),format.raw/*173.36*/("""
				"""),format.raw/*174.5*/("""let val = $(this).val().replace(/,/g,'');
				sum += parseFloat(val);
			"""),format.raw/*176.4*/("""}"""),format.raw/*176.5*/("""); $("#totalCantidad").text(formatStandar(sum,2));
			sum = 0;
			$(".total").each(function() """),format.raw/*178.32*/("""{"""),format.raw/*178.33*/("""
				"""),format.raw/*179.5*/("""let val = $(this).text().replace(/,/g,'');
				sum += parseFloat(val);
			"""),format.raw/*181.4*/("""}"""),format.raw/*181.5*/("""); $("#totalTotal").text(formatStandar(sum,2));
			sum = 0;
			$(".kg").each(function() """),format.raw/*183.29*/("""{"""),format.raw/*183.30*/("""
				"""),format.raw/*184.5*/("""let val = $(this).text().replace(/,/g,'');
				sum += parseFloat(val);
			"""),format.raw/*186.4*/("""}"""),format.raw/*186.5*/("""); $("#totalKG").text(formatStandar(sum,2));
			sum = 0;
			$(".m2").each(function() """),format.raw/*188.29*/("""{"""),format.raw/*188.30*/("""
				"""),format.raw/*189.5*/("""let val = $(this).text().replace(/,/g,'');
				sum += parseFloat(val);
			"""),format.raw/*191.4*/("""}"""),format.raw/*191.5*/("""); $("#totalM2").text(formatStandar(sum,2));
	 """),format.raw/*192.3*/("""}"""),format.raw/*192.4*/("""


	
"""),format.raw/*196.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,factura:tables.Factura,detalleFactura:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,factura,detalleFactura)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.Factura,List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,factura,detalleFactura) => apply(mapDiccionario,mapPermiso,userMnu,factura,detalleFactura)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCompras/compraFacturaPrint.scala.html
                  HASH: 6c9c1393447920243076d140813518eb953e9280
                  MATRIX: 1051->1|1302->159|1335->167|1351->175|1390->177|1418->180|1486->228|1514->230|1590->281|1664->335|1694->338|2327->944|2343->951|2372->959|2734->1294|2757->1308|2789->1319|2818->1320|2951->1426|2967->1433|2997->1442|3346->1764|3362->1771|3406->1794|3767->2128|3783->2135|3816->2147|4170->2474|4188->2483|4243->2517|4667->2914|4683->2921|4723->2940|5474->3664|5542->3715|5582->3716|5618->3724|5690->3768|5706->3774|5735->3781|5808->3826|5824->3832|5853->3839|5927->3885|5943->3891|5973->3899|6047->3945|6063->3951|6093->3959|6168->4006|6184->4012|6213->4019|6370->4148|6386->4154|6415->4161|6520->4238|6536->4244|6565->4251|6640->4298|6656->4304|6685->4311|6820->4418|6836->4424|6865->4431|6953->4491|6969->4497|6999->4505|7093->4571|7109->4577|7139->4585|7239->4657|7255->4663|7285->4671|7349->4704|7384->4711|8232->5528|8265->5533|8357->5596|8387->5597|8420->5602|8533->5687|8562->5688|8623->5720|8653->5721|8685->5725|8761->5772|8791->5773|8824->5778|8925->5851|8954->5852|9077->5946|9107->5947|9140->5952|9242->6026|9271->6027|9388->6115|9418->6116|9451->6121|9553->6195|9582->6196|9696->6281|9726->6282|9759->6287|9861->6361|9890->6362|9965->6409|9994->6410|10027->6415
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|59->28|59->28|59->28|68->37|68->37|68->37|68->37|71->40|71->40|71->40|81->50|81->50|81->50|91->60|91->60|91->60|101->70|101->70|101->70|116->85|116->85|116->85|141->110|141->110|141->110|142->111|143->112|143->112|143->112|144->113|144->113|144->113|145->114|145->114|145->114|146->115|146->115|146->115|147->116|147->116|147->116|150->119|150->119|150->119|153->122|153->122|153->122|154->123|154->123|154->123|156->125|156->125|156->125|158->127|158->127|158->127|159->128|159->128|159->128|160->129|160->129|160->129|162->131|163->132|190->159|195->164|197->166|197->166|198->167|200->169|200->169|202->171|202->171|203->172|204->173|204->173|205->174|207->176|207->176|209->178|209->178|210->179|212->181|212->181|214->183|214->183|215->184|217->186|217->186|219->188|219->188|220->189|222->191|222->191|223->192|223->192|227->196
                  -- GENERATED --
              */
          