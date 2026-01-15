
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

object compraConfirma extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listaAconfirmar: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "MODIFICAR COMPRA Y/O ADQUISICION","(modifica inventarios)")),format.raw/*9.92*/("""
		"""),format.raw/*10.3*/("""<form id="form" method="post" action="/compraConfirmaIngreso/">
			<input type="hidden" id="cambiosDeEstados" name="cambiosDeEstados">
			<div class="row  justify-content-md-center">
				<div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">
					<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<TR>
								<TH colspan="12">DATOS DE LA COMPRA</TH>
								<TH colspan="4">MAESTRO PRECIOS LISTA</TH>
								<TH>CONFIRMAR</TH>
							</TR>
							<TR>
								<TH style="min-width:80px;">FECHA<BR>COMPRA</TH>
								<TH>Nro<BR>DOC</TH>
								<TH>OBS</TH>
								<TH>CODIGO<BR>EQUIPO</TH>
								<TH>NOMBRE<BR>EQUIPO</TH>
								<TH>UN<br>COMPRA</TH>
								<TH>CANT<br>COMPRA</TH>
								<TH>MON<br>COMPRA</TH>
								<TH>P.U.<br>COMPRA</TH>
								<TH>TOTAL<br>COMPRA</TH>
								<TH>SUCURSAL</TH>
								<TH>"""),_display_(/*33.14*/mapDiccionario/*33.28*/.get("BODEGA")),format.raw/*33.42*/("""<BR>DESTINO</TH>
								
								<TH>MONEDA<BR>Rep/"""),_display_(/*35.28*/mapDiccionario/*35.42*/.get("Arr")),format.raw/*35.53*/("""</TH>
								<TH>PRECIO<br>Repos o Venta</TH>
								<TH>UNIDAD<BR>"""),_display_(/*37.24*/mapDiccionario/*37.38*/.get("Arriendo")),format.raw/*37.54*/("""</TH>
								<TH>PRECIO<br>"""),_display_(/*38.24*/mapDiccionario/*38.38*/.get("Arriendo")),format.raw/*38.54*/("""</TH>
								
								
								<TH>
									<input id="btnSelectAll" type="button" class="btn btn-sm btn-secondary" onclick="selectAll()" value="Select Todo">
								</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*47.9*/for(lista1 <- listaAconfirmar) yield /*47.39*/{_display_(Seq[Any](format.raw/*47.40*/("""
								"""),format.raw/*48.9*/("""<TR>
									<td  style="text-align:center;">
										<div style="display:none">"""),_display_(/*50.38*/lista1/*50.44*/.get(3)),format.raw/*50.51*/("""</div>
										"""),_display_(/*51.12*/utilities/*51.21*/.Fechas.DDMMAA(lista1.get(3))),format.raw/*51.50*/("""
									"""),format.raw/*52.10*/("""</td>
									<td  style="text-align:center;">"""),_display_(/*53.43*/lista1/*53.49*/.get(4)),format.raw/*53.56*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*54.41*/lista1/*54.47*/.get(11)),format.raw/*54.55*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*55.41*/lista1/*55.47*/.get(5)),format.raw/*55.54*/("""</td>
									
									<td  style="text-align:left;">"""),_display_(/*57.41*/lista1/*57.47*/.get(6)),format.raw/*57.54*/("""</td>
									<td  style="text-align:center;">"""),_display_(/*58.43*/lista1/*58.49*/.get(7)),format.raw/*58.56*/("""</td>
									<td  style="text-align:right;">"""),_display_(/*59.42*/lista1/*59.48*/.get(8)),format.raw/*59.55*/("""</td>
									
									<td  style="text-align:center;">"""),_display_(/*61.43*/lista1/*61.49*/.get(15)),format.raw/*61.57*/("""</td>
									<td  style="text-align:right;">"""),_display_(/*62.42*/lista1/*62.48*/.get(16)),format.raw/*62.56*/("""</td>
									<td  style="text-align:right;">"""),_display_(/*63.42*/lista1/*63.48*/.get(17)),format.raw/*63.56*/("""</td>
									
									<td  style="text-align:left;">"""),_display_(/*65.41*/lista1/*65.47*/.get(12)),format.raw/*65.55*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*66.41*/lista1/*66.47*/.get(9)),format.raw/*66.54*/("""</td>
									
									<td  style="text-align:center;">"""),_display_(/*68.43*/lista1/*68.49*/.get(18)),format.raw/*68.57*/("""</td>
									<td  style="text-align:right;">"""),_display_(/*69.42*/lista1/*69.48*/.get(19)),format.raw/*69.56*/("""</td>
									<td  style="text-align:center;">"""),_display_(/*70.43*/lista1/*70.49*/.get(20)),format.raw/*70.57*/("""</td>
									<td  style="text-align:right;">"""),_display_(/*71.42*/lista1/*71.48*/.get(21)),format.raw/*71.56*/("""</td>
									
									
									
									<td  style="text-align:center;">
									
										<input type="hidden" class="idCompra" value=""""),_display_(/*77.57*/lista1/*77.63*/.get(0)),format.raw/*77.70*/("""">
										<input type="hidden" class="idEquipo" value=""""),_display_(/*78.57*/lista1/*78.63*/.get(10)),format.raw/*78.71*/("""">
										<input type="hidden" class="cantCant" value=""""),_display_(/*79.57*/lista1/*79.63*/.get(8)),format.raw/*79.70*/("""">
										<input type="hidden" class="idBodDest" value=""""),_display_(/*80.58*/lista1/*80.64*/.get(2)),format.raw/*80.71*/("""">
										<input type="hidden" class="idSucursal" value=""""),_display_(/*81.59*/lista1/*81.65*/.get(13)),format.raw/*81.73*/("""">
										<input type="hidden" class="idFactura" value=""""),_display_(/*82.58*/lista1/*82.64*/.get(14)),format.raw/*82.72*/("""">
										<input type="hidden" class="fechaFactura" value=""""),_display_(/*83.61*/lista1/*83.67*/.get(3)),format.raw/*83.74*/("""">
										
										<input type="checkbox" class="checkVal" id=""""),_display_(/*85.56*/lista1/*85.62*/.get(0)),format.raw/*85.69*/("""" value ="0" 
										onchange="cambiaEstado(id,value,'"""),_display_(/*86.45*/lista1/*86.51*/.get(10)),format.raw/*86.59*/("""','"""),_display_(/*86.63*/lista1/*86.69*/.get(8)),format.raw/*86.76*/("""','"""),_display_(/*86.80*/lista1/*86.86*/.get(2)),format.raw/*86.93*/("""','"""),_display_(/*86.97*/lista1/*86.103*/.get(13)),format.raw/*86.111*/("""','"""),_display_(/*86.115*/lista1/*86.121*/.get(14)),format.raw/*86.129*/("""','"""),_display_(/*86.133*/lista1/*86.139*/.get(3)),format.raw/*86.146*/("""')">
									</td>
								</TR>
				 			""")))}),format.raw/*89.10*/("""
						"""),format.raw/*90.7*/("""</tbody>
					</table>
				</div>
			</div>
			<div class="noprint">
				<div class="row justify-content-md-center" >
					<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
						<input type="button"  value="Cancelar" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
					</div>
					<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
						<input type="button" value='CONFIRMAR' onclick="this.disabled=true; document.getElementById('form').submit();" class="btn btn-primary btn-sm rounded-pill btn-block">
					</div>
				</div>
			</div>
		</form>
	</div>
	
	

""")))}),format.raw/*109.2*/("""


"""),format.raw/*112.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*113.31*/("""{"""),format.raw/*113.32*/("""
		  """),format.raw/*114.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*114.36*/("""{"""),format.raw/*114.37*/("""
		    	"""),format.raw/*115.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 0, "desc" ]],
		    	"language": """),format.raw/*118.20*/("""{"""),format.raw/*118.21*/("""
		        	"""),format.raw/*119.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*120.11*/("""}"""),format.raw/*120.12*/("""
		  """),format.raw/*121.5*/("""}"""),format.raw/*121.6*/(""" """),format.raw/*121.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*123.2*/("""}"""),format.raw/*123.3*/(""");
	
	
	const cambiaEstado = (id_compra, valor, id_equipo, cantidad, id_bodegaDestino, id_sucursal, id_factura, fecha_factura) => """),format.raw/*126.124*/("""{"""),format.raw/*126.125*/("""
		"""),format.raw/*127.3*/("""cantidad = cantidad.replace(/,/g,'');
		if(valor==0) """),format.raw/*128.16*/("""{"""),format.raw/*128.17*/("""
			"""),format.raw/*129.4*/("""document.getElementById(id_compra).value ="1";
			let aux = ""+$("#cambiosDeEstados").val()+id_compra + "," + id_sucursal  + "," + id_equipo + "," + cantidad + "," + id_bodegaDestino + "," + id_factura + "," + fecha_factura + ";"
			$("#cambiosDeEstados").val(aux);
		"""),format.raw/*132.3*/("""}"""),format.raw/*132.4*/("""else"""),format.raw/*132.8*/("""{"""),format.raw/*132.9*/("""
			"""),format.raw/*133.4*/("""document.getElementById(id_compra).value ="0";
			let aux = ""+id_compra + "," + id_sucursal + "," + id_equipo + "," + cantidad + "," + id_bodegaDestino  + "," + id_factura + "," + fecha_factura + ";"
			$("#cambiosDeEstados").val($("#cambiosDeEstados").val().replace(aux,""));
		"""),format.raw/*136.3*/("""}"""),format.raw/*136.4*/("""
		
	"""),format.raw/*138.2*/("""}"""),format.raw/*138.3*/("""
	
			
	"""),format.raw/*141.2*/("""const selectAll = () => """),format.raw/*141.26*/("""{"""),format.raw/*141.27*/("""
		"""),format.raw/*142.3*/("""$(".checkVal").attr('checked',false);
		let filas = $(".checkVal").length;
		let aux = "";
		for(let i=0; i<filas; i++)"""),format.raw/*145.29*/("""{"""),format.raw/*145.30*/("""
			"""),format.raw/*146.4*/("""$(".checkVal")[i].checked = true;
			$(".checkVal")[i].value = "1";
			let cantidad = $(".cantCant")[i].value;
			cantidad = cantidad.replace(/,/g,'');
			
			aux += ""+$(".idCompra")[i].value + "," + $(".idSucursal")[i].value  
					+ "," + $(".idEquipo")[i].value 
					+ "," + cantidad 
					+ "," + $(".idBodDest")[i].value 
					+ "," + $(".idFactura")[i].value 
					+ "," + $(".fechaFactura")[i].value 
					+ ";"
			$("#cambiosDeEstados").val(aux);
		"""),format.raw/*159.3*/("""}"""),format.raw/*159.4*/("""
	"""),format.raw/*160.2*/("""}"""),format.raw/*160.3*/("""
	
	
"""),format.raw/*163.1*/("""</script>

		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listaAconfirmar:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listaAconfirmar)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listaAconfirmar) => apply(mapDiccionario,mapPermiso,userMnu,listaAconfirmar)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCompras/compraConfirma.scala.html
                  HASH: 5841615f5857bd32f264fa515a2d44d6f1179331
                  MATRIX: 1032->1|1259->135|1292->143|1308->151|1347->153|1376->157|1444->205|1472->207|1548->258|1656->346|1686->349|2648->1284|2671->1298|2706->1312|2786->1365|2809->1379|2841->1390|2938->1460|2961->1474|2998->1490|3054->1519|3077->1533|3114->1549|3365->1774|3411->1804|3450->1805|3486->1814|3597->1898|3612->1904|3640->1911|3685->1929|3703->1938|3753->1967|3791->1977|3866->2025|3881->2031|3909->2038|3982->2084|3997->2090|4026->2098|4099->2144|4114->2150|4142->2157|4225->2213|4240->2219|4268->2226|4343->2274|4358->2280|4386->2287|4460->2334|4475->2340|4503->2347|4588->2405|4603->2411|4632->2419|4706->2466|4721->2472|4750->2480|4824->2527|4839->2533|4868->2541|4951->2597|4966->2603|4995->2611|5068->2657|5083->2663|5111->2670|5196->2728|5211->2734|5240->2742|5314->2789|5329->2795|5358->2803|5433->2851|5448->2857|5477->2865|5551->2912|5566->2918|5595->2926|5766->3070|5781->3076|5809->3083|5895->3142|5910->3148|5939->3156|6025->3215|6040->3221|6068->3228|6155->3288|6170->3294|6198->3301|6286->3362|6301->3368|6330->3376|6417->3436|6432->3442|6461->3450|6551->3513|6566->3519|6594->3526|6690->3595|6705->3601|6733->3608|6818->3666|6833->3672|6862->3680|6893->3684|6908->3690|6936->3697|6967->3701|6982->3707|7010->3714|7041->3718|7057->3724|7087->3732|7119->3736|7135->3742|7165->3750|7197->3754|7213->3760|7242->3767|7316->3810|7350->3817|7982->4418|8013->4421|8104->4483|8134->4484|8167->4489|8227->4520|8257->4521|8293->4529|8469->4676|8499->4677|8540->4689|8647->4767|8677->4768|8710->4773|8739->4774|8768->4775|8869->4848|8898->4849|9058->4979|9089->4980|9120->4983|9202->5036|9232->5037|9264->5041|9560->5309|9589->5310|9621->5314|9650->5315|9682->5319|9990->5599|10019->5600|10052->5605|10081->5606|10117->5614|10170->5638|10200->5639|10231->5642|10379->5761|10409->5762|10441->5766|10928->6225|10957->6226|10987->6228|11016->6229|11049->6234
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|64->33|64->33|64->33|66->35|66->35|66->35|68->37|68->37|68->37|69->38|69->38|69->38|78->47|78->47|78->47|79->48|81->50|81->50|81->50|82->51|82->51|82->51|83->52|84->53|84->53|84->53|85->54|85->54|85->54|86->55|86->55|86->55|88->57|88->57|88->57|89->58|89->58|89->58|90->59|90->59|90->59|92->61|92->61|92->61|93->62|93->62|93->62|94->63|94->63|94->63|96->65|96->65|96->65|97->66|97->66|97->66|99->68|99->68|99->68|100->69|100->69|100->69|101->70|101->70|101->70|102->71|102->71|102->71|108->77|108->77|108->77|109->78|109->78|109->78|110->79|110->79|110->79|111->80|111->80|111->80|112->81|112->81|112->81|113->82|113->82|113->82|114->83|114->83|114->83|116->85|116->85|116->85|117->86|117->86|117->86|117->86|117->86|117->86|117->86|117->86|117->86|117->86|117->86|117->86|117->86|117->86|117->86|117->86|117->86|117->86|120->89|121->90|140->109|143->112|144->113|144->113|145->114|145->114|145->114|146->115|149->118|149->118|150->119|151->120|151->120|152->121|152->121|152->121|154->123|154->123|157->126|157->126|158->127|159->128|159->128|160->129|163->132|163->132|163->132|163->132|164->133|167->136|167->136|169->138|169->138|172->141|172->141|172->141|173->142|176->145|176->145|177->146|190->159|190->159|191->160|191->160|194->163
                  -- GENERATED --
              */
          