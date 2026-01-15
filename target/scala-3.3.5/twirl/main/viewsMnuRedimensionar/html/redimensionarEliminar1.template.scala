
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

object redimensionarEliminar1 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,tables.ActaRedimensionar,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
acta: tables.ActaRedimensionar, detalle: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<form method="post" action="/routes2/redimensionarEliminar2/">
		<div id="mostrarmostrar" style="display:none;">
			"""),_display_(/*10.5*/barraTitulo(mapDiccionario, "ELIMINAR ACTA REDIMENSIONAR","SOLO PENDIENTES DE CONFIRMAR")),format.raw/*10.94*/("""
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
						<input type="submit"  id="aplica" value='ELIMINAR' class="btn btn-danger btn-sm rounded-pill btn-block">
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
                  SOURCE: app/viewsMnuRedimensionar/redimensionarEliminar1.scala.html
                  HASH: 73b9158594a0e13ba22e2ed64d5eae27e5b719b3
                  MATRIX: 1071->1|1322->159|1355->167|1371->175|1410->177|1438->180|1506->228|1534->230|1677->347|1787->436|1818->440|2388->983|2401->987|2428->993|2538->1076|2551->1080|2582->1090|2947->1428|2960->1432|2990->1441|3132->1556|3171->1557|3211->1569|3247->1586|3286->1587|3325->1598|3393->1639|3406->1643|3440->1656|3561->1746|3597->1755|3930->2061|3943->2065|3981->2082|5164->3238|5224->3282|5263->3283|5301->3293|5406->3371|5421->3377|5449->3384|5488->3395|5574->3454|5589->3460|5617->3467|5656->3478|5744->3539|5759->3545|5787->3552|5826->3563|5969->3678|5985->3684|6014->3691|6054->3702|6143->3763|6159->3769|6188->3776|6228->3787|6316->3847|6332->3853|6361->3860|6401->3871|6503->3945|6519->3951|6548->3958|6588->3969|6752->4105|6768->4111|6797->4118|6837->4129|6924->4188|6940->4194|6970->4202|7010->4213|7099->4274|7115->4280|7145->4288|7185->4299|7337->4423|7353->4429|7383->4437|7423->4448|7521->4518|7537->4524|7567->4532|7607->4543|7670->4574|7705->4581|9042->5887|9075->5892|9167->5955|9197->5956|9228->5959|9383->6085|9413->6086|9449->6094|9621->6237|9651->6238|9692->6250|9799->6328|9829->6329|9861->6333|9890->6334|9919->6335|9951->6339|9980->6340|10041->6372|10071->6373|10107->6381|10183->6428|10213->6429|10246->6434|10345->6504|10375->6505|10409->6511|10502->6576|10531->6577|10563->6581|10592->6582|10722->6683|10752->6684|10785->6689|10884->6759|10914->6760|10948->6766|11052->6842|11081->6843|11113->6847|11142->6848|11278->6955|11308->6956|11341->6961|11443->7035|11472->7036|11561->7097|11590->7098|11622->7102|11692->7143|11722->7144|11755->7149|11887->7253|11916->7254|11951->7261
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|41->10|41->10|42->11|52->21|52->21|52->21|54->23|54->23|54->23|64->33|64->33|64->33|69->38|69->38|70->39|71->40|71->40|72->41|72->41|72->41|72->41|75->44|76->45|85->54|85->54|85->54|119->88|119->88|119->88|120->89|122->91|122->91|122->91|123->92|125->94|125->94|125->94|126->95|128->97|128->97|128->97|129->98|131->100|131->100|131->100|132->101|134->103|134->103|134->103|135->104|137->106|137->106|137->106|138->107|140->109|140->109|140->109|141->110|149->118|149->118|149->118|150->119|152->121|152->121|152->121|153->122|155->124|155->124|155->124|156->125|158->127|158->127|158->127|159->128|162->131|162->131|162->131|163->132|165->134|166->135|203->172|208->177|210->179|210->179|211->180|215->184|215->184|216->185|219->188|219->188|220->189|221->190|221->190|222->191|222->191|222->191|223->192|223->192|225->194|225->194|227->196|228->197|228->197|229->198|231->200|231->200|232->201|234->203|234->203|235->204|235->204|239->208|239->208|240->209|242->211|242->211|243->212|245->214|245->214|246->215|246->215|249->218|249->218|250->219|252->221|252->221|254->223|254->223|256->225|256->225|256->225|257->226|259->228|259->228|264->233
                  -- GENERATED --
              */
          