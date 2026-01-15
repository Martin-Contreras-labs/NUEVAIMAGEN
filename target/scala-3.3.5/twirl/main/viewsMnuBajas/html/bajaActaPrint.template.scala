
package viewsMnuBajas.html

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

object bajaActaPrint extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,tables.ActaBaja,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
actaBaja: tables.ActaBaja, detalleBaja: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
		"""),format.raw/*8.3*/("""<div id="mostrarmostrar" style="display:none;">
			"""),_display_(/*9.5*/barraTitulo(mapDiccionario, "DETALLE ACTA DE BAJA","")),format.raw/*9.59*/("""
			"""),format.raw/*10.4*/("""<div class="row  justify-content-md-center">
				<div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">
					<table class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<tr>
								<td width="300px">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Nro ACTA DE BAJA</span>
								  		</div>
								  		<input type="hidden" name="id_actaBaja" value=""""),_display_(/*20.61*/actaBaja/*20.69*/.getId()),format.raw/*20.77*/("""">
	  									<input type="text" class="form-control center"
	  										name="numero"
	  										value=""""),_display_(/*23.22*/actaBaja/*23.30*/.getNumero()),format.raw/*23.42*/(""""
	  										readonly>
									</div>
								</td>
								<td width="230px">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Fecha</span>
								  		</div>
								  		<input type="date" class="form-control center"
								  			name="fecha"
								  			value=""""),_display_(/*34.22*/utilities/*34.31*/.Fechas.AAMMDD(actaBaja.getFecha())),format.raw/*34.66*/(""""
						        			readonly>
									</div>
								</td>
								<td style="text-align:left;">
									"""),_display_(if(actaBaja.getActaBajaPDF().equals("0"))/*39.52*/{_display_(Seq[Any](format.raw/*39.53*/("""
										"""),format.raw/*40.11*/("""--
									""")))}else/*41.15*/{_display_(Seq[Any](format.raw/*41.16*/("""
										"""),format.raw/*42.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*42.52*/actaBaja/*42.60*/.getActaBajaPDF()),format.raw/*42.77*/("""')">
											<kbd style="background-color: #7F8C8D">doc</kbd>
										</a>
									""")))}),format.raw/*45.11*/("""
								"""),format.raw/*46.9*/("""</td>
							</tr>
							<tr>
								<td colspan="7">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Observaciones</span>
								  		</div>
	  									<textarea class="form-control" 
	  										name="observaciones" 
	  										autocomplete="off" readonly>"""),_display_(/*56.43*/actaBaja/*56.51*/.getObservaciones()),format.raw/*56.70*/("""</textarea>
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
						        <TH width="3%">UN</TH>
								<TH width="8%">CANTIDAD</TH>
								<TH width="3%">MONEDA</TH>
								<TH width="10%">P.UNITARIO<br>COMPRA</TH>
								<TH width="10%">TOTAL</TH>
								<TH width="28%">MOTIVO DE BAJA</TH>
								<TH width="8%">CONFIRMADA</TH>
							</TR>
						</thead>
						<tbody>
				 			"""),_display_(/*78.10*/for(lista1 <- detalleBaja) yield /*78.36*/{_display_(Seq[Any](format.raw/*78.37*/("""
								"""),format.raw/*79.9*/("""<TR>
									<td  style="text-align:left;">"""),_display_(/*80.41*/lista1/*80.47*/.get(4)),format.raw/*80.54*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*81.41*/lista1/*81.47*/.get(5)),format.raw/*81.54*/("""</td>
									<td  style="text-align:center;">"""),_display_(/*82.43*/lista1/*82.49*/.get(8)),format.raw/*82.56*/("""</td>
									<td  style="text-align:right;" class="cantidad">"""),_display_(/*83.59*/lista1/*83.65*/.get(13)),format.raw/*83.73*/("""</td>
									<td  style="text-align:center;">"""),_display_(/*84.43*/lista1/*84.49*/.get(10)),format.raw/*84.57*/("""</td>
									<td  style="text-align:right;">"""),_display_(/*85.42*/lista1/*85.48*/.get(11)),format.raw/*85.56*/("""</td>
									<td  style="text-align:right;" class="total">"""),_display_(/*86.56*/lista1/*86.62*/.get(16)),format.raw/*86.70*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*87.41*/lista1/*87.47*/.get(14)),format.raw/*87.55*/("""</td>
									<td  style="text-align:center;">"""),_display_(/*88.43*/lista1/*88.49*/.get(15)),format.raw/*88.57*/("""</td>
								</TR>
				 			""")))}),format.raw/*90.10*/("""
				 			"""),format.raw/*91.9*/("""<TR style="background-color: #eeeeee">
						        <td></td>
								<td>TOTALES</td>
								<td></td>
								<td><div id="totalCantidad" align="right">0.00</div></td>
								<td></td>
								<td></td>
								<td><div id="totalTotal" align="right">0.00</div></td>
								<td></td>
								<td></td>
							</TR>
						</tbody>
					</table>
				</div>
			</div>
			<div class="noprint" align="left">
				<div class="row justify-content-md-center" >
					<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
						<input type="button"  value="Volver" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/listBajasPorActa/';">
					</div>
				</div>
			</div>
		</div>
	
	<form class="formulario" id="formDescargaDocumento" method="post" action="/downloadPDF/">	
		<input type="hidden" id="descargaDocumento" name="nombreArchivo">
	</form>
	
""")))}),format.raw/*119.2*/("""




"""),format.raw/*124.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*126.31*/("""{"""),format.raw/*126.32*/("""
		"""),format.raw/*127.3*/("""sumaTotales();
		document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*129.2*/("""}"""),format.raw/*129.3*/(""");
	
	let cantAux = 0;
	const verificaCantidad = (id_equipo, id_cotizacion, stock, cantidad) =>"""),format.raw/*132.73*/("""{"""),format.raw/*132.74*/("""
		"""),format.raw/*133.3*/("""stock = stock.replace(/,/g,'');
		if(parseFloat(cantidad) > parseFloat(stock))"""),format.raw/*134.47*/("""{"""),format.raw/*134.48*/("""
			"""),format.raw/*135.4*/("""alertify.alert('La cantidad no puede ser mayor al stock disponible para dar de baja', function () """),format.raw/*135.102*/("""{"""),format.raw/*135.103*/("""
				"""),format.raw/*136.5*/("""$("#cantidad_"+id_equipo+"_"+id_cotizacion).val(formatStandar(cantAux,2));
     		"""),format.raw/*137.8*/("""}"""),format.raw/*137.9*/(""");
		"""),format.raw/*138.3*/("""}"""),format.raw/*138.4*/("""else"""),format.raw/*138.8*/("""{"""),format.raw/*138.9*/("""
			"""),format.raw/*139.4*/("""$("#cantidad_"+id_equipo+"_"+id_cotizacion).val(formatStandar(cantidad,2));
		"""),format.raw/*140.3*/("""}"""),format.raw/*140.4*/("""
		"""),format.raw/*141.3*/("""sumaTotales();
	"""),format.raw/*142.2*/("""}"""),format.raw/*142.3*/("""
	
	
	"""),format.raw/*145.2*/("""const sumaTotales = () => """),format.raw/*145.28*/("""{"""),format.raw/*145.29*/("""
		 """),format.raw/*146.4*/("""let sum = 0;
			$(".total").each(function() """),format.raw/*147.32*/("""{"""),format.raw/*147.33*/("""
				"""),format.raw/*148.5*/("""let val = $(this).text().replace(/,/g,'');
				sum += parseFloat(val);
			"""),format.raw/*150.4*/("""}"""),format.raw/*150.5*/("""); $("#totalTotal").text(formatStandar(sum,2));
			sum = 0;
			$(".cantidad").each(function() """),format.raw/*152.35*/("""{"""),format.raw/*152.36*/("""
				"""),format.raw/*153.5*/("""let val = $(this).text().replace(/,/g,'');
				sum += parseFloat(val);
			"""),format.raw/*155.4*/("""}"""),format.raw/*155.5*/("""); $("#totalCantidad").text(formatStandar(sum,2));
	"""),format.raw/*156.2*/("""}"""),format.raw/*156.3*/("""
	
	"""),format.raw/*158.2*/("""const descargaDocumento = (nombreDOC) => """),format.raw/*158.43*/("""{"""),format.raw/*158.44*/("""
		  """),format.raw/*159.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*161.2*/("""}"""),format.raw/*161.3*/("""

	

	
"""),format.raw/*166.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,actaBaja:tables.ActaBaja,detalleBaja:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,actaBaja,detalleBaja)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.ActaBaja,List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,actaBaja,detalleBaja) => apply(mapDiccionario,mapPermiso,userMnu,actaBaja,detalleBaja)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuBajas/bajaActaPrint.scala.html
                  HASH: f57f606c6f0f2baa40984adee1a033bd774a1809
                  MATRIX: 1045->1|1295->158|1328->166|1344->174|1383->176|1411->179|1479->227|1508->230|1585->282|1659->336|1690->340|2245->868|2262->876|2291->884|2428->994|2445->1002|2478->1014|2869->1378|2887->1387|2943->1422|3118->1570|3157->1571|3196->1582|3232->1599|3271->1600|3310->1611|3378->1652|3395->1660|3433->1677|3554->1767|3590->1776|3978->2137|3995->2145|4035->2164|4727->2829|4769->2855|4808->2856|4844->2865|4916->2910|4931->2916|4959->2923|5032->2969|5047->2975|5075->2982|5150->3030|5165->3036|5193->3043|5284->3107|5299->3113|5328->3121|5403->3169|5418->3175|5447->3183|5521->3230|5536->3236|5565->3244|5653->3305|5668->3311|5697->3319|5770->3365|5785->3371|5814->3379|5889->3427|5904->3433|5933->3441|5993->3470|6029->3479|6932->4351|6965->4356|7057->4419|7087->4420|7118->4423|7229->4506|7258->4507|7382->4602|7412->4603|7443->4606|7550->4684|7580->4685|7612->4689|7740->4787|7771->4788|7804->4793|7914->4875|7943->4876|7976->4881|8005->4882|8037->4886|8066->4887|8098->4891|8204->4969|8233->4970|8264->4973|8308->4989|8337->4990|8371->4996|8426->5022|8456->5023|8488->5027|8561->5071|8591->5072|8624->5077|8726->5151|8755->5152|8878->5246|8908->5247|8941->5252|9043->5326|9072->5327|9152->5379|9181->5380|9213->5384|9283->5425|9313->5426|9346->5431|9478->5535|9507->5536|9542->5543
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|51->20|51->20|51->20|54->23|54->23|54->23|65->34|65->34|65->34|70->39|70->39|71->40|72->41|72->41|73->42|73->42|73->42|73->42|76->45|77->46|87->56|87->56|87->56|109->78|109->78|109->78|110->79|111->80|111->80|111->80|112->81|112->81|112->81|113->82|113->82|113->82|114->83|114->83|114->83|115->84|115->84|115->84|116->85|116->85|116->85|117->86|117->86|117->86|118->87|118->87|118->87|119->88|119->88|119->88|121->90|122->91|150->119|155->124|157->126|157->126|158->127|160->129|160->129|163->132|163->132|164->133|165->134|165->134|166->135|166->135|166->135|167->136|168->137|168->137|169->138|169->138|169->138|169->138|170->139|171->140|171->140|172->141|173->142|173->142|176->145|176->145|176->145|177->146|178->147|178->147|179->148|181->150|181->150|183->152|183->152|184->153|186->155|186->155|187->156|187->156|189->158|189->158|189->158|190->159|192->161|192->161|197->166
                  -- GENERATED --
              */
          