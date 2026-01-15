
package viewsMnuRedimensionar.html

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

object redimensionarConfirmar1 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,tables.ActaRedimensionar,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
acta: tables.ActaRedimensionar, detalle: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<form method="post" action="/routes2/redimensionarConfirmar2/">
		<div id="mostrarmostrar" style="display:none;">
			"""),_display_(/*10.5*/barraTitulo(mapDiccionario, "CONFIRMAR ACTA REDIMENSIONAR","")),format.raw/*10.67*/("""
			"""),format.raw/*11.4*/("""<div class="row  justify-content-md-center">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<table class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<tr>
								<td width="300px">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Nro ACTA REDIMENSIONAR</span>
								  		</div>
								  		<input type="hidden" name="id_actaRedimensionar" value=""""),_display_(/*21.70*/acta/*21.74*/.getId),format.raw/*21.80*/("""">
	  									<input type="text" class="form-control center"
	  										value=""""),_display_(/*23.22*/acta/*23.26*/.getNumero),format.raw/*23.36*/(""""
	  										readonly>
									</div>
								</td>
								<td width="230px">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Fecha</span>
								  		</div>
								  		<input type="date" class="form-control center"
								  			value=""""),_display_(/*33.22*/acta/*33.26*/.getFecha),format.raw/*33.35*/(""""
						        			readonly>
									</div>
								</td>
								<td>
									"""),_display_(if(acta.getActaPDF().equals("0"))/*38.44*/{_display_(Seq[Any](format.raw/*38.45*/("""
											"""),format.raw/*39.12*/("""--
									""")))}else/*40.15*/{_display_(Seq[Any](format.raw/*40.16*/("""
										"""),format.raw/*41.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*41.52*/acta/*41.56*/.getActaPDF()),format.raw/*41.69*/("""')">
											<kbd style="background-color: #7F8C8D">doc</kbd>
										</a>
									""")))}),format.raw/*44.11*/("""
								"""),format.raw/*45.9*/("""</td>
							</tr>
							<tr>
								<td colspan="3">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Observaciones</span>
								  		</div>
	  									<textarea class="form-control"
	  										readonly>"""),_display_(/*54.24*/acta/*54.28*/.getObservaciones),format.raw/*54.45*/("""</textarea>
									</div>
								</td>
							</tr>
						</thead>
					</table>
					<hr>
					
					<table id="detalle" class="table table-sm table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<tr>
								<th width="50%" colspan="7" style="text-align:center;">EQUIPOS PARA REDIMENSIONAR</th>
								<th width="5%"></th>
								<th width="45%" colspan="7" style="text-align:center;">DETALLE A REDIMENSIONAR</th>
							</tr>
							<tr> 
								<TH width="7%">CODIGO</TH>
								<TH width="17%">EQUIPO</TH>
						        <TH width="3%">UN</TH>
								<TH width="5%" style="background-color: rgb(192, 192, 192); max-width:120px">CANTIDAD<br>A REDIMENS</TH>
								<TH width="3%">MONEDA</TH>
								<TH width="7%">P.UNITARIO<br>COMPRA</TH>
								<TH width="7%">TOTAL</TH>
								<TH width="5%"></TH>
								<TH width=7%>CODIGO</TH>
								<TH width=17%>EQUIPO</TH>
								<TH width=3%>UN</TH>
								<TH width=5% style="background-color: rgb(192, 192, 192);">CANTIDAD<br>REDIMENS</TH>
								<TH width=13%>DESTINO</TH>
							</tr>
						</thead>
						
						<tbody>
							<tr>
								"""),_display_(/*88.10*/for((lista1, index) <- detalle.zipWithIndex) yield /*88.54*/{_display_(Seq[Any](format.raw/*88.55*/("""
									"""),format.raw/*89.10*/("""<TR>
										<td  style="text-align:center;" class="codOrigen">
											"""),_display_(/*91.13*/lista1/*91.19*/.get(2)),format.raw/*91.26*/("""
										"""),format.raw/*92.11*/("""</td>
										<td  style="text-align:left;">
											"""),_display_(/*94.13*/lista1/*94.19*/.get(3)),format.raw/*94.26*/("""
										"""),format.raw/*95.11*/("""</td>
										<td  style="text-align:center;">
											"""),_display_(/*97.13*/lista1/*97.19*/.get(4)),format.raw/*97.26*/("""
										"""),format.raw/*98.11*/("""</td>
										<td  style="text-align:right; background-color: rgb(192, 192, 192);" class="cantidad">
											"""),_display_(/*100.13*/lista1/*100.19*/.get(5)),format.raw/*100.26*/("""
										"""),format.raw/*101.11*/("""</td>
										<td  style="text-align:center;">
											"""),_display_(/*103.13*/lista1/*103.19*/.get(6)),format.raw/*103.26*/("""
										"""),format.raw/*104.11*/("""</td>
										<td  style="text-align:right;">
											"""),_display_(/*106.13*/lista1/*106.19*/.get(7)),format.raw/*106.26*/("""
										"""),format.raw/*107.11*/("""</td>
										<td  style="text-align:right;" class="total">
											"""),_display_(/*109.13*/lista1/*109.19*/.get(8)),format.raw/*109.26*/("""
										"""),format.raw/*110.11*/("""</td>
										
										
										
										<td></td>
										
										
										<td  style="text-align:center;">
											"""),_display_(/*118.13*/lista1/*118.19*/.get(9)),format.raw/*118.26*/("""
										"""),format.raw/*119.11*/("""</td>
										<td  style="text-align:left;">
											"""),_display_(/*121.13*/lista1/*121.19*/.get(10)),format.raw/*121.27*/("""
										"""),format.raw/*122.11*/("""</td>
										<td  style="text-align:center;">
											"""),_display_(/*124.13*/lista1/*124.19*/.get(11)),format.raw/*124.27*/("""
										"""),format.raw/*125.11*/("""</td>
										<td  style="text-align:right; background-color: rgb(192, 192, 192);" class="cantRedimensionar">
											"""),_display_(/*127.13*/lista1/*127.19*/.get(12)),format.raw/*127.27*/("""
										"""),format.raw/*128.11*/("""</td>
										
										<td  style="text-align:left;">
											"""),_display_(/*131.13*/lista1/*131.19*/.get(13)),format.raw/*131.27*/("""
										"""),format.raw/*132.11*/("""</td>
									</TR>
					 			""")))}),format.raw/*134.11*/("""
						"""),format.raw/*135.7*/("""</tbody>
						<tfoot>
							<tr> 
								<th></th>
								<th style="text-align:left;">TOTALES</th>
								<th></th>
								<th style="text-align:right; background-color: rgb(192, 192, 192);"><div id="totalCantidad" align="right">0.00</div></th>
								<th></th>
								<th></th>
								<th><div id="totalTotal" align="right">0.00</div></th>
								<th></th>
								<th></th>
								<th></th>
								<th></th>
								<th style="text-align:right; background-color: rgb(192, 192, 192);"><div id="totalRedimensionar" align="right">0.00</div></th>
								<th></th>
							</tr>
						</tfoot>
					</table>
					
					
			<div class="noprint" align="left">
				<div class="row justify-content-md-center" >
					<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
						<input type="button"  value="Cancelar" class="btn btn-warning btn-sm rounded-pill btn-block" onclick="window.history.back();">
					</div>
					<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
						<input type="submit"  id="aplica" value='CONFIRMAR' class="btn btn-primary btn-sm rounded-pill btn-block">
					</div>
				</div>
			</div>
		</div>
	</form>
	
	<form class="formulario" id="formDescargaDocumento" method="post" action="/downloadPDF/">	
		<input type="hidden" id="descargaDocumento" name="nombreArchivo">
	</form>
""")))}),format.raw/*172.2*/("""




"""),format.raw/*177.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*179.31*/("""{"""),format.raw/*179.32*/("""
		"""),format.raw/*180.3*/("""sumaTotales();
		document.getElementById('mostrarmostrar').style.display="block";
		
		
		$('#tablaListadoEquipos').DataTable("""),format.raw/*184.39*/("""{"""),format.raw/*184.40*/("""
		    	"""),format.raw/*185.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
				"order": [[ 0, "asc" ]],
		    	"language": """),format.raw/*188.20*/("""{"""),format.raw/*188.21*/("""
		        	"""),format.raw/*189.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*190.11*/("""}"""),format.raw/*190.12*/("""
			"""),format.raw/*191.4*/("""}"""),format.raw/*191.5*/(""" """),format.raw/*191.6*/(""");
	"""),format.raw/*192.2*/("""}"""),format.raw/*192.3*/(""");
	
	const sumaTotales = () => """),format.raw/*194.28*/("""{"""),format.raw/*194.29*/("""
		
		 	"""),format.raw/*196.5*/("""let sum = 0;
			$(".cantidad").each(function() """),format.raw/*197.35*/("""{"""),format.raw/*197.36*/("""
				"""),format.raw/*198.5*/("""let aux = $(this).text();
				aux = aux.trim();
				if(aux.length > 0)"""),format.raw/*200.23*/("""{"""),format.raw/*200.24*/("""
					"""),format.raw/*201.6*/("""let val = aux.replace(/,/g,'');
					sum += parseFloat(val);
				"""),format.raw/*203.5*/("""}"""),format.raw/*203.6*/("""
			"""),format.raw/*204.4*/("""}"""),format.raw/*204.5*/("""); 
			$("#totalCantidad").text(formatStandar(sum,2));
		
			sum = 0;
			$(".total").each(function() """),format.raw/*208.32*/("""{"""),format.raw/*208.33*/("""
				"""),format.raw/*209.5*/("""let aux = $(this).text();
				aux = aux.trim();
				if(aux.length > 0)"""),format.raw/*211.23*/("""{"""),format.raw/*211.24*/("""
					"""),format.raw/*212.6*/("""let val = $(this).text().replace(/,/g,'');
					sum += parseFloat(val);
				"""),format.raw/*214.5*/("""}"""),format.raw/*214.6*/("""
			"""),format.raw/*215.4*/("""}"""),format.raw/*215.5*/("""); $("#totalTotal").text(formatStandar(sum,0));
			
			sum = 0;
			$(".cantRedimensionar").each(function() """),format.raw/*218.44*/("""{"""),format.raw/*218.45*/("""
				"""),format.raw/*219.5*/("""let val = $(this).text().replace(/,/g,'');
				sum += parseFloat(val);
			"""),format.raw/*221.4*/("""}"""),format.raw/*221.5*/("""); 
			$("#totalRedimensionar").text(formatStandar(sum,2));
	"""),format.raw/*223.2*/("""}"""),format.raw/*223.3*/("""
	
	"""),format.raw/*225.2*/("""const descargaDocumento = (nombreDOC) => """),format.raw/*225.43*/("""{"""),format.raw/*225.44*/("""
		  """),format.raw/*226.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*228.2*/("""}"""),format.raw/*228.3*/("""
	


	
"""),format.raw/*233.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,acta:tables.ActaRedimensionar,detalle:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,acta,detalle)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.ActaRedimensionar,List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,acta,detalle) => apply(mapDiccionario,mapPermiso,userMnu,acta,detalle)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuRedimensionar/redimensionarConfirmar1.scala.html
                  HASH: 4a7fc2a5f8f4cee4899e8da8bff8e2cac08763a0
                  MATRIX: 1072->1|1323->159|1356->167|1372->175|1411->177|1439->180|1507->228|1535->230|1679->348|1762->410|1793->414|2363->957|2376->961|2403->967|2513->1050|2526->1054|2557->1064|2922->1402|2935->1406|2965->1415|3107->1530|3146->1531|3186->1543|3222->1560|3261->1561|3300->1572|3368->1613|3381->1617|3415->1630|3536->1720|3572->1729|3905->2035|3918->2039|3956->2056|5139->3212|5199->3256|5238->3257|5276->3267|5381->3345|5396->3351|5424->3358|5463->3369|5549->3428|5564->3434|5592->3441|5631->3452|5719->3513|5734->3519|5762->3526|5801->3537|5944->3652|5960->3658|5989->3665|6029->3676|6118->3737|6134->3743|6163->3750|6203->3761|6291->3821|6307->3827|6336->3834|6376->3845|6478->3919|6494->3925|6523->3932|6563->3943|6727->4079|6743->4085|6772->4092|6812->4103|6899->4162|6915->4168|6945->4176|6985->4187|7074->4248|7090->4254|7120->4262|7160->4273|7312->4397|7328->4403|7358->4411|7398->4422|7496->4492|7512->4498|7542->4506|7582->4517|7645->4548|7680->4555|9019->5863|9052->5868|9144->5931|9174->5932|9205->5935|9360->6061|9390->6062|9426->6070|9598->6213|9628->6214|9669->6226|9776->6304|9806->6305|9838->6309|9867->6310|9896->6311|9928->6315|9957->6316|10018->6348|10048->6349|10084->6357|10160->6404|10190->6405|10223->6410|10322->6480|10352->6481|10386->6487|10479->6552|10508->6553|10540->6557|10569->6558|10699->6659|10729->6660|10762->6665|10861->6735|10891->6736|10925->6742|11029->6818|11058->6819|11090->6823|11119->6824|11255->6931|11285->6932|11318->6937|11420->7011|11449->7012|11538->7073|11567->7074|11599->7078|11669->7119|11699->7120|11732->7125|11864->7229|11893->7230|11928->7237
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|41->10|41->10|42->11|52->21|52->21|52->21|54->23|54->23|54->23|64->33|64->33|64->33|69->38|69->38|70->39|71->40|71->40|72->41|72->41|72->41|72->41|75->44|76->45|85->54|85->54|85->54|119->88|119->88|119->88|120->89|122->91|122->91|122->91|123->92|125->94|125->94|125->94|126->95|128->97|128->97|128->97|129->98|131->100|131->100|131->100|132->101|134->103|134->103|134->103|135->104|137->106|137->106|137->106|138->107|140->109|140->109|140->109|141->110|149->118|149->118|149->118|150->119|152->121|152->121|152->121|153->122|155->124|155->124|155->124|156->125|158->127|158->127|158->127|159->128|162->131|162->131|162->131|163->132|165->134|166->135|203->172|208->177|210->179|210->179|211->180|215->184|215->184|216->185|219->188|219->188|220->189|221->190|221->190|222->191|222->191|222->191|223->192|223->192|225->194|225->194|227->196|228->197|228->197|229->198|231->200|231->200|232->201|234->203|234->203|235->204|235->204|239->208|239->208|240->209|242->211|242->211|243->212|245->214|245->214|246->215|246->215|249->218|249->218|250->219|252->221|252->221|254->223|254->223|256->225|256->225|256->225|257->226|259->228|259->228|264->233
                  -- GENERATED --
              */
          