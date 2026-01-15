
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

object redimensionarListar1 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,tables.ActaRedimensionar,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
acta: tables.ActaRedimensionar, detalle: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
		"""),format.raw/*8.3*/("""<div id="mostrarmostrar" style="display:none;">
			"""),_display_(/*9.5*/barraTitulo(mapDiccionario, "ACTA REDIMENSIONAR CONFIRMADA","")),format.raw/*9.68*/("""
			"""),format.raw/*10.4*/("""<div class="row  justify-content-md-center">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<table class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<tr>
								<td width="300px">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Nro ACTA REDIMENSIONAR</span>
								  		</div>
								  		<input type="hidden" name="id_actaRedimensionar" value=""""),_display_(/*20.70*/acta/*20.74*/.getId),format.raw/*20.80*/("""">
	  									<input type="text" class="form-control center"
	  										value=""""),_display_(/*22.22*/acta/*22.26*/.getNumero()),format.raw/*22.38*/(""""
	  										readonly>
									</div>
								</td>
								<td width="230px">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Fecha Acta</span>
								  		</div>
								  		<input type="date" class="form-control center"
								  			value=""""),_display_(/*32.22*/acta/*32.26*/.getFecha()),format.raw/*32.37*/(""""
						        			readonly>
									</div>
								</td>
								<td width="300px">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Fecha Confirmada</span>
								  		</div>
								  		<input type="date" class="form-control center"
								  			value=""""),_display_(/*42.22*/acta/*42.26*/.getFechaConfirma()),format.raw/*42.45*/(""""
						        			readonly>
									</div>
								</td>
								<td>
									"""),_display_(if(acta.getActaPDF().equals("0"))/*47.44*/{_display_(Seq[Any](format.raw/*47.45*/("""
											"""),format.raw/*48.12*/("""--
									""")))}else/*49.15*/{_display_(Seq[Any](format.raw/*49.16*/("""
										"""),format.raw/*50.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*50.52*/acta/*50.56*/.getActaPDF()),format.raw/*50.69*/("""')">
											<kbd style="background-color: #7F8C8D">doc</kbd>
										</a>
									""")))}),format.raw/*53.11*/("""
								"""),format.raw/*54.9*/("""</td>
							</tr>
							<tr>
								<td colspan="3">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Observaciones</span>
								  		</div>
	  									<textarea class="form-control"
	  										readonly>"""),_display_(/*63.24*/acta/*63.28*/.getObservaciones),format.raw/*63.45*/("""</textarea>
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
								"""),_display_(/*97.10*/for((lista1, index) <- detalle.zipWithIndex) yield /*97.54*/{_display_(Seq[Any](format.raw/*97.55*/("""
									"""),format.raw/*98.10*/("""<TR>
										<td  style="text-align:center;" class="codOrigen">
											"""),_display_(/*100.13*/lista1/*100.19*/.get(2)),format.raw/*100.26*/("""
										"""),format.raw/*101.11*/("""</td>
										<td  style="text-align:left;">
											"""),_display_(/*103.13*/lista1/*103.19*/.get(3)),format.raw/*103.26*/("""
										"""),format.raw/*104.11*/("""</td>
										<td  style="text-align:center;">
											"""),_display_(/*106.13*/lista1/*106.19*/.get(4)),format.raw/*106.26*/("""
										"""),format.raw/*107.11*/("""</td>
										<td  style="text-align:right; background-color: rgb(192, 192, 192);" class="cantidad">
											"""),_display_(/*109.13*/lista1/*109.19*/.get(5)),format.raw/*109.26*/("""
										"""),format.raw/*110.11*/("""</td>
										<td  style="text-align:center;">
											"""),_display_(/*112.13*/lista1/*112.19*/.get(6)),format.raw/*112.26*/("""
										"""),format.raw/*113.11*/("""</td>
										<td  style="text-align:right;">
											"""),_display_(/*115.13*/lista1/*115.19*/.get(7)),format.raw/*115.26*/("""
										"""),format.raw/*116.11*/("""</td>
										<td  style="text-align:right;" class="total">
											"""),_display_(/*118.13*/lista1/*118.19*/.get(8)),format.raw/*118.26*/("""
										"""),format.raw/*119.11*/("""</td>
										
										
										
										<td></td>
										
										
										<td  style="text-align:center;">
											"""),_display_(/*127.13*/lista1/*127.19*/.get(9)),format.raw/*127.26*/("""
										"""),format.raw/*128.11*/("""</td>
										<td  style="text-align:left;">
											"""),_display_(/*130.13*/lista1/*130.19*/.get(10)),format.raw/*130.27*/("""
										"""),format.raw/*131.11*/("""</td>
										<td  style="text-align:center;">
											"""),_display_(/*133.13*/lista1/*133.19*/.get(11)),format.raw/*133.27*/("""
										"""),format.raw/*134.11*/("""</td>
										<td  style="text-align:right; background-color: rgb(192, 192, 192);" class="cantRedimensionar">
											"""),_display_(/*136.13*/lista1/*136.19*/.get(12)),format.raw/*136.27*/("""
										"""),format.raw/*137.11*/("""</td>
										
										<td  style="text-align:left;">
											"""),_display_(/*140.13*/lista1/*140.19*/.get(13)),format.raw/*140.27*/("""
										"""),format.raw/*141.11*/("""</td>
									</TR>
					 			""")))}),format.raw/*143.11*/("""
						"""),format.raw/*144.7*/("""</tbody>
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
						<input type="button"  value="Listo" class="btn btn-primary btn-sm rounded-pill btn-block" onclick="window.history.back();">
					</div>
				</div>
			</div>
		</div>
	
	<form class="formulario" id="formDescargaDocumento" method="post" action="/downloadPDF/">	
		<input type="hidden" id="descargaDocumento" name="nombreArchivo">
	</form>
""")))}),format.raw/*177.2*/("""




"""),format.raw/*182.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*184.31*/("""{"""),format.raw/*184.32*/("""
		"""),format.raw/*185.3*/("""sumaTotales();
		document.getElementById('mostrarmostrar').style.display="block";
		
		
		$('#tablaListadoEquipos').DataTable("""),format.raw/*189.39*/("""{"""),format.raw/*189.40*/("""
		    	"""),format.raw/*190.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
				"order": [[ 0, "asc" ]],
		    	"language": """),format.raw/*193.20*/("""{"""),format.raw/*193.21*/("""
		        	"""),format.raw/*194.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*195.11*/("""}"""),format.raw/*195.12*/("""
			"""),format.raw/*196.4*/("""}"""),format.raw/*196.5*/(""" """),format.raw/*196.6*/(""");
	"""),format.raw/*197.2*/("""}"""),format.raw/*197.3*/(""");
	
	const sumaTotales = () => """),format.raw/*199.28*/("""{"""),format.raw/*199.29*/("""
		
		 	"""),format.raw/*201.5*/("""let sum = 0;
			$(".cantidad").each(function() """),format.raw/*202.35*/("""{"""),format.raw/*202.36*/("""
				"""),format.raw/*203.5*/("""let aux = $(this).text();
				aux = aux.trim();
				if(aux.length > 0)"""),format.raw/*205.23*/("""{"""),format.raw/*205.24*/("""
					"""),format.raw/*206.6*/("""let val = aux.replace(/,/g,'');
					sum += parseFloat(val);
				"""),format.raw/*208.5*/("""}"""),format.raw/*208.6*/("""
			"""),format.raw/*209.4*/("""}"""),format.raw/*209.5*/("""); 
			$("#totalCantidad").text(formatStandar(sum,2));
		
			sum = 0;
			$(".total").each(function() """),format.raw/*213.32*/("""{"""),format.raw/*213.33*/("""
				"""),format.raw/*214.5*/("""let aux = $(this).text();
				aux = aux.trim();
				if(aux.length > 0)"""),format.raw/*216.23*/("""{"""),format.raw/*216.24*/("""
					"""),format.raw/*217.6*/("""let val = $(this).text().replace(/,/g,'');
					sum += parseFloat(val);
				"""),format.raw/*219.5*/("""}"""),format.raw/*219.6*/("""
			"""),format.raw/*220.4*/("""}"""),format.raw/*220.5*/("""); $("#totalTotal").text(formatStandar(sum,0));
			
			sum = 0;
			$(".cantRedimensionar").each(function() """),format.raw/*223.44*/("""{"""),format.raw/*223.45*/("""
				"""),format.raw/*224.5*/("""let val = $(this).text().replace(/,/g,'');
				sum += parseFloat(val);
			"""),format.raw/*226.4*/("""}"""),format.raw/*226.5*/("""); 
			$("#totalRedimensionar").text(formatStandar(sum,2));
	"""),format.raw/*228.2*/("""}"""),format.raw/*228.3*/("""
	
	"""),format.raw/*230.2*/("""const descargaDocumento = (nombreDOC) => """),format.raw/*230.43*/("""{"""),format.raw/*230.44*/("""
		  """),format.raw/*231.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*233.2*/("""}"""),format.raw/*233.3*/("""
	


	
"""),format.raw/*238.1*/("""</script>


		
		
	
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
                  SOURCE: app/viewsMnuRedimensionar/redimensionarListar1.scala.html
                  HASH: 559ff7e3f6be7829bb1914b1ae3550e3895bf7a0
                  MATRIX: 1069->1|1320->159|1353->167|1369->175|1408->177|1436->180|1504->228|1533->231|1610->283|1693->346|1724->350|2294->893|2307->897|2334->903|2444->986|2457->990|2490->1002|2860->1345|2873->1349|2905->1360|3285->1713|3298->1717|3338->1736|3480->1851|3519->1852|3559->1864|3595->1881|3634->1882|3673->1893|3741->1934|3754->1938|3788->1951|3909->2041|3945->2050|4278->2356|4291->2360|4329->2377|5512->3533|5572->3577|5611->3578|5649->3588|5755->3666|5771->3672|5800->3679|5840->3690|5927->3749|5943->3755|5972->3762|6012->3773|6101->3834|6117->3840|6146->3847|6186->3858|6329->3973|6345->3979|6374->3986|6414->3997|6503->4058|6519->4064|6548->4071|6588->4082|6676->4142|6692->4148|6721->4155|6761->4166|6863->4240|6879->4246|6908->4253|6948->4264|7112->4400|7128->4406|7157->4413|7197->4424|7284->4483|7300->4489|7330->4497|7370->4508|7459->4569|7475->4575|7505->4583|7545->4594|7697->4718|7713->4724|7743->4732|7783->4743|7881->4813|7897->4819|7927->4827|7967->4838|8030->4869|8065->4876|9212->5992|9245->5997|9337->6060|9367->6061|9398->6064|9553->6190|9583->6191|9619->6199|9791->6342|9821->6343|9862->6355|9969->6433|9999->6434|10031->6438|10060->6439|10089->6440|10121->6444|10150->6445|10211->6477|10241->6478|10277->6486|10353->6533|10383->6534|10416->6539|10515->6609|10545->6610|10579->6616|10672->6681|10701->6682|10733->6686|10762->6687|10892->6788|10922->6789|10955->6794|11054->6864|11084->6865|11118->6871|11222->6947|11251->6948|11283->6952|11312->6953|11448->7060|11478->7061|11511->7066|11613->7140|11642->7141|11731->7202|11760->7203|11792->7207|11862->7248|11892->7249|11925->7254|12057->7358|12086->7359|12121->7366
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|51->20|51->20|51->20|53->22|53->22|53->22|63->32|63->32|63->32|73->42|73->42|73->42|78->47|78->47|79->48|80->49|80->49|81->50|81->50|81->50|81->50|84->53|85->54|94->63|94->63|94->63|128->97|128->97|128->97|129->98|131->100|131->100|131->100|132->101|134->103|134->103|134->103|135->104|137->106|137->106|137->106|138->107|140->109|140->109|140->109|141->110|143->112|143->112|143->112|144->113|146->115|146->115|146->115|147->116|149->118|149->118|149->118|150->119|158->127|158->127|158->127|159->128|161->130|161->130|161->130|162->131|164->133|164->133|164->133|165->134|167->136|167->136|167->136|168->137|171->140|171->140|171->140|172->141|174->143|175->144|208->177|213->182|215->184|215->184|216->185|220->189|220->189|221->190|224->193|224->193|225->194|226->195|226->195|227->196|227->196|227->196|228->197|228->197|230->199|230->199|232->201|233->202|233->202|234->203|236->205|236->205|237->206|239->208|239->208|240->209|240->209|244->213|244->213|245->214|247->216|247->216|248->217|250->219|250->219|251->220|251->220|254->223|254->223|255->224|257->226|257->226|259->228|259->228|261->230|261->230|261->230|262->231|264->233|264->233|269->238
                  -- GENERATED --
              */
          