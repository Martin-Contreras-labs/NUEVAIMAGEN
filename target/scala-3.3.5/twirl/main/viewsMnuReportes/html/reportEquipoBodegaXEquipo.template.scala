
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

object reportEquipoBodegaXEquipo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
lista: List[List[String]], fechaCorte: String, tipo:String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalEquipoDescripcion()),format.raw/*9.27*/("""
	"""),_display_(/*10.3*/modalContactoBodega(mapDiccionario)),format.raw/*10.38*/("""
	
	"""),format.raw/*12.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*13.4*/barraTitulo(mapDiccionario, "INVENTARIO - "+tipo+" - "+mapDiccionario.get("BODEGA")+"/PROYECTO Y POR EQUIPO (valorizado a precio compra y lista)","FECHA DE CORTE: "+ utilities.Fechas.DDMMAA(fechaCorte))),format.raw/*13.206*/("""
		
		"""),format.raw/*15.3*/("""<div class="noprint">
			<table class="table table-sm table-condensed table-fluid">
				<tr>
					<td width="25%">
						<div class="input-group">
					  		<div class="input-group-prepend">
					    		<span class="input-group-text" id="basic-addon1">BUSCAR</span>
					  		</div>
					  		<input type="text" class="form-control left"
								id="searchTermtablaPrincipal"
								onkeyup="doSearch2('tablaPrincipal'); sumarTotales();">
						</div>
					</td>
					<td>
						<div align="center">
							<button type="button"  class="btn btn-sm btn-success" 
								onclick="history.go(-1);return false;">
								Volver
							</button>
						</div>
					</td>
					<td width="25%">
						<div class="input-group">
					  		<div class="input-group-prepend">
					    		<span class="input-group-text" id="basic-addon1">TOTAL CANTIDAD</span>
					  		</div>
					  		<input type="text" class="form-control right"
								id="totalCantidad"
								readonly>
						</div>
						<div class="input-group">
					  		<div class="input-group-prepend">
					    		<span class="input-group-text" id="basic-addon1">"""),_display_(/*47.62*/mapDiccionario/*47.76*/.get("PESOS")),format.raw/*47.89*/(""" """),format.raw/*47.90*/("""COMPRA</span>
					  		</div>
					  		<input type="text" class="form-control right"
								id="totalPesosC"
								readonly>
						</div>
						<div class="input-group">
					  		<div class="input-group-prepend">
					    		<span class="input-group-text" id="basic-addon1">"""),_display_(/*55.62*/mapDiccionario/*55.76*/.get("PESOS")),format.raw/*55.89*/(""" """),format.raw/*55.90*/("""VENTA</span>
					  		</div>
					  		<input type="text" class="form-control right"
								id="totalPesosV"
								readonly>
						</div>
						
					</div>
						
					</td>
				</tr>
			</table>
		</div>
		
		<div>
			ALTERNAR COLUMNAS: 
				<a class="toggle-vis" href="#" data-column="0"><kbd id="kdb0" style="background-color: #73C6B6">TIPO</kbd></a>
				<a class="toggle-vis" href="#" data-column="1"><kbd id="kdb1" style="background-color: #73C6B6">SUCURSAL</kbd></a>  
				<a class="toggle-vis" href="#" data-column="2"><kbd id="kdb2" style="background-color: #73C6B6">"""),_display_(/*73.102*/mapDiccionario/*73.116*/.get("BODEGA")),format.raw/*73.130*/("""/PROYECTO</kbd></a> 
				<a class="toggle-vis" href="#" data-column="3"><kbd id="kdb3" style="background-color: #73C6B6">COD</kbd></a> 
				<a class="toggle-vis" href="#" data-column="4"><kbd id="kdb4" style="background-color: #73C6B6">EQUIPO</kbd></a> 
				<a class="toggle-vis" href="#" data-column="5"><kbd id="kdb5" style="background-color: #73C6B6">MON Compra</kbd></a> 
				<a class="toggle-vis" href="#" data-column="6"><kbd id="kdb6" style="background-color: #73C6B6">P.U. Compra</kbd></a>
				<a class="toggle-vis" href="#" data-column="7"><kbd id="kdb7" style="background-color: #73C6B6">MON Vta/"""),_display_(/*78.110*/mapDiccionario/*78.124*/.get("ARR")),format.raw/*78.135*/("""</kbd></a>
				<a class="toggle-vis" href="#" data-column="8"><kbd id="kdb8" style="background-color: #73C6B6">P.U. Venta</kbd></a> 
				<a class="toggle-vis" href="#" data-column="9"><kbd id="kdb9" style="background-color: #73C6B6">UN """),_display_(/*80.105*/mapDiccionario/*80.119*/.get("ARR")),format.raw/*80.130*/("""</kbd></a> 
				<a class="toggle-vis" href="#" data-column="10"><kbd id="kdb10" style="background-color: #73C6B6">P.U. """),_display_(/*81.109*/mapDiccionario/*81.123*/.get("Arriendo")),format.raw/*81.139*/("""</kbd></a> 
				<a class="toggle-vis" href="#" data-column="11"><kbd id="kdb11" style="background-color: #73C6B6">UN</kbd></a> 
				<a class="toggle-vis" href="#" data-column="12"><kbd id="kdb12" style="background-color: #73C6B6">CANT</kbd></a> 
				<a class="toggle-vis" href="#" data-column="13"><kbd id="kdb13" style="background-color: #73C6B6">TOTAL Compra</kbd></a> 
				<a class="toggle-vis" href="#" data-column="14"><kbd id="kdb14" style="background-color: #73C6B6">TOTAL Venta</kbd></a> 
				<a class="toggle-vis" href="#" data-column="15"><kbd id="kdb15" style="background-color: #73C6B6">Compra ("""),_display_(/*86.112*/mapDiccionario/*86.126*/.get("PESOS")),format.raw/*86.139*/(""")</kbd></a> 
				<a class="toggle-vis" href="#" data-column="16"><kbd id="kdb16" style="background-color: #73C6B6">Venta ("""),_display_(/*87.111*/mapDiccionario/*87.125*/.get("PESOS")),format.raw/*87.138*/(""")</kbd></a>
		</div>
		
		<div class="table-responsive">
			<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
		        <thead style="background-color: #eeeeee">
			        <TR> 
						       
						        <TH style= "text-align: center;vertical-align: top;">TIPO</TH>
								<TH style= "text-align: center;vertical-align: top;">SUCURSAL</TH>
						        <TH style= "text-align: center;vertical-align: top;">"""),_display_(/*97.69*/mapDiccionario/*97.83*/.get("BODEGA")),format.raw/*97.97*/("""/PROYECTO</TH>
						        <TH style= "text-align: center;vertical-align: top;width:10%;">COD</TH>
								<TH style= "text-align: center;vertical-align: top;width:20%;">EQUIPO</TH>
								<TH style= "text-align: center;vertical-align: top;">MON</TH>
								<TH style= "text-align: center;vertical-align: top;">P.U.<br>Compra</TH>
								<TH style= "text-align: center;vertical-align: top;">MON</TH>
								<TH style= "text-align: center;vertical-align: top;">P.U.<br>Venta</TH>
								<TH style= "text-align: center;vertical-align: top;">UN<br>"""),_display_(/*104.69*/mapDiccionario/*104.83*/.get("ARR")),format.raw/*104.94*/("""</TH>
								<TH style= "text-align: center;vertical-align: top;">P.U.<br>"""),_display_(/*105.71*/mapDiccionario/*105.85*/.get("Arriendo")),format.raw/*105.101*/("""</TH>
								<TH style= "text-align: center;vertical-align: top;">UN</TH>
								<TH style= "text-align: center;vertical-align: top;">CANT</TH>
								<TH style= "text-align: center;vertical-align: top;">TOTAL<br>Compra</TH>
								<TH style= "text-align: center;vertical-align: top;">TOTAL<br>Venta</TH>
								<TH style= "text-align: center;vertical-align: top;">Compra<br>("""),_display_(/*110.74*/mapDiccionario/*110.88*/.get("PESOS")),format.raw/*110.101*/(""")</TH>
								<TH style= "text-align: center;vertical-align: top;">Venta<br>("""),_display_(/*111.73*/mapDiccionario/*111.87*/.get("PESOS")),format.raw/*111.100*/(""")</TH>
								<TH style= "text-align: center;vertical-align: top;width:4%">VER</TH>
					</TR>
				</thead>
				<tbody>
				"""),_display_(/*116.6*/for(lista1 <- lista) yield /*116.26*/{_display_(Seq[Any](format.raw/*116.27*/("""
				"""),format.raw/*117.5*/("""<TR>
					<td style="text-align:left;vertical-align:middle;">"""),_display_(/*118.58*/lista1/*118.64*/.get(1)),format.raw/*118.71*/("""</td>
					<td style="text-align:left;vertical-align:middle;">"""),_display_(/*119.58*/lista1/*119.64*/.get(23)),format.raw/*119.72*/("""</td>
						<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="modalContactoBodega('"""),_display_(/*120.101*/lista1/*120.107*/.get(18)),format.raw/*120.115*/("""');">"""),_display_(/*120.121*/lista1/*120.127*/.get(2)),format.raw/*120.134*/("""</a></td>
						<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="equipoDescripcion('"""),_display_(/*121.99*/lista1/*121.105*/.get(0)),format.raw/*121.112*/("""');">"""),_display_(/*121.118*/lista1/*121.124*/.get(4)),format.raw/*121.131*/("""</a></td>
						<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="equipoDescripcion('"""),_display_(/*122.99*/lista1/*122.105*/.get(0)),format.raw/*122.112*/("""');">"""),_display_(/*122.118*/lista1/*122.124*/.get(5)),format.raw/*122.131*/("""</a></td>
						<td style="text-align:center;vertical-align:middle;">"""),_display_(/*123.61*/lista1/*123.67*/.get(6)),format.raw/*123.74*/("""</td>
						<td style="text-align:right;vertical-align:middle;">"""),_display_(/*124.60*/lista1/*124.66*/.get(7)),format.raw/*124.73*/("""</td>
						<td style="text-align:center;vertical-align:middle;">"""),_display_(/*125.61*/lista1/*125.67*/.get(8)),format.raw/*125.74*/("""</td>
						<td style="text-align:right;vertical-align:middle;">"""),_display_(/*126.60*/lista1/*126.66*/.get(9)),format.raw/*126.73*/("""</td>
						<td style="text-align:right;vertical-align:middle;">"""),_display_(/*127.60*/lista1/*127.66*/.get(10)),format.raw/*127.74*/("""</td>
						<td style="text-align:right;vertical-align:middle;">"""),_display_(/*128.60*/lista1/*128.66*/.get(11)),format.raw/*128.74*/("""</td>
						<td style="text-align:center;vertical-align:middle;">"""),_display_(/*129.61*/lista1/*129.67*/.get(12)),format.raw/*129.75*/("""</td>
						<td style="text-align:right;vertical-align:middle;">"""),_display_(/*130.60*/lista1/*130.66*/.get(13)),format.raw/*130.74*/("""</td>
						<td style="text-align:right;vertical-align:middle;">"""),_display_(/*131.60*/lista1/*131.66*/.get(14)),format.raw/*131.74*/("""</td>
						<td style="text-align:right;vertical-align:middle;">"""),_display_(/*132.60*/lista1/*132.66*/.get(15)),format.raw/*132.74*/("""</td>
						<td style="text-align:right;vertical-align:middle;">"""),_display_(/*133.60*/lista1/*133.66*/.get(16)),format.raw/*133.74*/("""</td>
						<td style="text-align:right;vertical-align:middle;">"""),_display_(/*134.60*/lista1/*134.66*/.get(17)),format.raw/*134.74*/("""</td>
					
					<td style="text-align:center;vertical-align:middle;">
						<form id="form_"""),_display_(/*137.23*/lista1/*137.29*/.get(18)),format.raw/*137.37*/("""" class="formulario" id="formDescargaDocumento" method="post" action="/reportEquipoBodegaTrazaEquipoEnBod/">
							<input type="hidden" name="id_bodega" value=""""),_display_(/*138.54*/lista1/*138.60*/.get(18)),format.raw/*138.68*/("""">
							<input type="hidden" name="id_equipo" value=""""),_display_(/*139.54*/lista1/*139.60*/.get(0)),format.raw/*139.67*/("""">
							<input type="hidden" name="tipo" value=""""),_display_(/*140.49*/tipo),format.raw/*140.53*/("""">
							<a href="#" onclick="document.getElementById('form_"""),_display_(/*141.60*/lista1/*141.66*/.get(18)),format.raw/*141.74*/("""').submit()">
								<kbd style="background-color: #73C6B6">select</kbd>
							</a>
						</form>
					</td>
				</TR>
	 			""")))}),format.raw/*147.7*/("""
				"""),format.raw/*148.5*/("""</tbody>
			</table>
		</div>
		
		
		
		
		
	</div>

""")))}),format.raw/*158.2*/("""


"""),format.raw/*161.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*162.31*/("""{"""),format.raw/*162.32*/("""
         """),format.raw/*163.10*/("""sumarTotales();
		 $('#tablaPrincipal').DataTable("""),format.raw/*164.35*/("""{"""),format.raw/*164.36*/("""
		    	"""),format.raw/*165.8*/(""""fixedHeader": true,
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [[ 0, "asc" ],[ 2, "asc" ]],
		    	"language": """),format.raw/*170.20*/("""{"""),format.raw/*170.21*/("""
		        	"""),format.raw/*171.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*172.11*/("""}"""),format.raw/*172.12*/("""
		  """),format.raw/*173.5*/("""}"""),format.raw/*173.6*/(""" """),format.raw/*173.7*/(""");
		  
		  $('a.toggle-vis').on('click', function (e) """),format.raw/*175.48*/("""{"""),format.raw/*175.49*/("""
		        """),format.raw/*176.11*/("""e.preventDefault();
		        // Get the column API object
		        let col = $(this).attr('data-column');
		        var column = $('#tablaPrincipal').DataTable().column(col);
		        // Toggle the visibility
		        column.visible(!column.visible());
		        if(column.visible())"""),format.raw/*182.31*/("""{"""),format.raw/*182.32*/("""
					"""),format.raw/*183.6*/("""document.getElementById('kdb'+col).style.backgroundColor = "#73C6B6";
				"""),format.raw/*184.5*/("""}"""),format.raw/*184.6*/("""else"""),format.raw/*184.10*/("""{"""),format.raw/*184.11*/("""
					"""),format.raw/*185.6*/("""document.getElementById('kdb'+col).style.backgroundColor = "#D6DBDF";
				"""),format.raw/*186.5*/("""}"""),format.raw/*186.6*/("""
		  """),format.raw/*187.5*/("""}"""),format.raw/*187.6*/(""");
		    
		document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*190.2*/("""}"""),format.raw/*190.3*/(""");
	
	function sumarTotales() """),format.raw/*192.26*/("""{"""),format.raw/*192.27*/("""
		"""),format.raw/*193.3*/("""var tabla = document.getElementById("tablaPrincipal");
		var sumaCantidades = 0;
		var sumaTotalesC= 0;
		var sumaTotalesV= 0;
		var numero = new DecimalFormat("#,##0.00");
		for (var i = 1; i < tabla.rows.length; i++) """),format.raw/*198.47*/("""{"""),format.raw/*198.48*/(""" 
			"""),format.raw/*199.4*/("""var aux = tabla.rows[i].cells[12].textContent;
			sumaCantidades = parseFloat(sumaCantidades) + parseFloat(numero.formatBack(aux));
			aux = tabla.rows[i].cells[15].textContent;
			sumaTotalesC = parseFloat(sumaTotalesC) + parseFloat(numero.formatBack(aux));
			aux = tabla.rows[i].cells[16].textContent;
			sumaTotalesV = parseFloat(sumaTotalesV) + parseFloat(numero.formatBack(aux));
		"""),format.raw/*205.3*/("""}"""),format.raw/*205.4*/("""
		"""),format.raw/*206.3*/("""$("#totalCantidad").val(formatStandar(sumaCantidades,0));
		$("#totalPesosC").val(formatStandar(sumaTotalesC,0));
		$("#totalPesosV").val(formatStandar(sumaTotalesV,0));
	"""),format.raw/*209.2*/("""}"""),format.raw/*209.3*/("""
	
"""),format.raw/*211.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,lista:List[List[String]],fechaCorte:String,tipo:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,lista,fechaCorte,tipo)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,lista,fechaCorte,tipo) => apply(mapDiccionario,mapPermiso,userMnu,lista,fechaCorte,tipo)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reportEquipoBodegaXEquipo.scala.html
                  HASH: c63e10f2753bab3b592f6f6cacabdb9a2cf202f4
                  MATRIX: 1058->1|1308->158|1341->166|1357->174|1396->176|1425->180|1493->228|1523->233|1567->257|1596->260|1652->295|1683->299|1760->350|1984->552|2017->558|3151->1665|3174->1679|3208->1692|3237->1693|3541->1970|3564->1984|3598->1997|3627->1998|4234->2577|4258->2591|4294->2605|4929->3212|4953->3226|4986->3237|5251->3474|5275->3488|5308->3499|5456->3619|5480->3633|5518->3649|6154->4257|6178->4271|6213->4284|6364->4407|6388->4421|6423->4434|6919->4903|6942->4917|6977->4931|7558->5484|7582->5498|7615->5509|7719->5585|7743->5599|7782->5615|8192->5997|8216->6011|8252->6024|8359->6103|8383->6117|8419->6130|8572->6256|8609->6276|8649->6277|8682->6282|8772->6344|8788->6350|8817->6357|8908->6420|8924->6426|8954->6434|9089->6540|9106->6546|9137->6554|9172->6560|9189->6566|9219->6573|9355->6681|9372->6687|9402->6694|9437->6700|9454->6706|9484->6713|9620->6821|9637->6827|9667->6834|9702->6840|9719->6846|9749->6853|9847->6923|9863->6929|9892->6936|9985->7001|10001->7007|10030->7014|10124->7080|10140->7086|10169->7093|10262->7158|10278->7164|10307->7171|10400->7236|10416->7242|10446->7250|10539->7315|10555->7321|10585->7329|10679->7395|10695->7401|10725->7409|10818->7474|10834->7480|10864->7488|10957->7553|10973->7559|11003->7567|11096->7632|11112->7638|11142->7646|11235->7711|11251->7717|11281->7725|11374->7790|11390->7796|11420->7804|11541->7897|11557->7903|11587->7911|11777->8073|11793->8079|11823->8087|11907->8143|11923->8149|11952->8156|12031->8207|12057->8211|12147->8273|12163->8279|12193->8287|12351->8414|12384->8419|12470->8474|12501->8477|12592->8539|12622->8540|12661->8550|12740->8600|12770->8601|12806->8609|12987->8761|13017->8762|13058->8774|13165->8852|13195->8853|13228->8858|13257->8859|13286->8860|13370->8915|13400->8916|13440->8927|13756->9214|13786->9215|13820->9221|13922->9295|13951->9296|13984->9300|14014->9301|14048->9307|14150->9381|14179->9382|14212->9387|14241->9388|14347->9466|14376->9467|14435->9497|14465->9498|14496->9501|14744->9720|14774->9721|14807->9726|15223->10114|15252->10115|15283->10118|15482->10289|15511->10290|15542->10293
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|41->10|41->10|43->12|44->13|44->13|46->15|78->47|78->47|78->47|78->47|86->55|86->55|86->55|86->55|104->73|104->73|104->73|109->78|109->78|109->78|111->80|111->80|111->80|112->81|112->81|112->81|117->86|117->86|117->86|118->87|118->87|118->87|128->97|128->97|128->97|135->104|135->104|135->104|136->105|136->105|136->105|141->110|141->110|141->110|142->111|142->111|142->111|147->116|147->116|147->116|148->117|149->118|149->118|149->118|150->119|150->119|150->119|151->120|151->120|151->120|151->120|151->120|151->120|152->121|152->121|152->121|152->121|152->121|152->121|153->122|153->122|153->122|153->122|153->122|153->122|154->123|154->123|154->123|155->124|155->124|155->124|156->125|156->125|156->125|157->126|157->126|157->126|158->127|158->127|158->127|159->128|159->128|159->128|160->129|160->129|160->129|161->130|161->130|161->130|162->131|162->131|162->131|163->132|163->132|163->132|164->133|164->133|164->133|165->134|165->134|165->134|168->137|168->137|168->137|169->138|169->138|169->138|170->139|170->139|170->139|171->140|171->140|172->141|172->141|172->141|178->147|179->148|189->158|192->161|193->162|193->162|194->163|195->164|195->164|196->165|201->170|201->170|202->171|203->172|203->172|204->173|204->173|204->173|206->175|206->175|207->176|213->182|213->182|214->183|215->184|215->184|215->184|215->184|216->185|217->186|217->186|218->187|218->187|221->190|221->190|223->192|223->192|224->193|229->198|229->198|230->199|236->205|236->205|237->206|240->209|240->209|242->211
                  -- GENERATED --
              */
          