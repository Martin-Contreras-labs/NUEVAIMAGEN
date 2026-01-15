
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

object reportInventarioEquipo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
lista: List[List[String]], fechaCorte: String, tipo:String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalEquipoDescripcion()),format.raw/*9.27*/("""
	
	"""),format.raw/*11.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*12.4*/barraTitulo(mapDiccionario, "INVENTARIO - "+tipo+" - POR EQUIPO (valorizado con precios de lista)","FECHA DE CORTE: " + utilities.Fechas.DDMMAA(fechaCorte))),format.raw/*12.160*/("""
		
		"""),format.raw/*14.3*/("""<div class="noprint">
			<table class="table table-sm table-condensed table-fluid">
				<tr>
					<td width="25%">
						<div class="input-group">
					  		<div class="input-group-prepend">
					    		<span class="input-group-text" id="basic-addon1">BUSCAR</span>
					  		</div>
					  		<input type="text" class="form-control left"
								id="searchTermtablaPrincipal"
								onkeyup="doSearch3('tablaPrincipal'); sumarTotales2();">
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
					<td width="25%">
						<div class="input-group">
					  		<div class="input-group-prepend">
					    		<span class="input-group-text" id="basic-addon1">TOTAL COMPRA ("""),_display_(/*45.76*/mapDiccionario/*45.90*/.get("PESOS")),format.raw/*45.103*/(""")</span>
					  		</div>
					  		<input type="text" class="form-control right"
								id="sumaGranTotalCompra"
								readonly>
						</div>
						<div class="input-group">
					  		<div class="input-group-prepend">
					    		<span class="input-group-text" id="basic-addon1">TOTAL VENTA ("""),_display_(/*53.75*/mapDiccionario/*53.89*/.get("PESOS")),format.raw/*53.102*/(""")</span>
					  		</div>
					  		<input type="text" class="form-control right"
								id="sumaGranTotalVenta"
								readonly>
						</div>
					</td>
				</tr>
			</table>
		</div>
		
		
		<div>
			ALTERNAR COLUMNAS: 
				<a class="toggle-vis" href="#" data-column="0"><kbd id="kdb0" style="background-color: #73C6B6">GRUPO</kbd></a>
				<a class="toggle-vis" href="#" data-column="1"><kbd id="kdb1" style="background-color: #73C6B6">PROPIEDAD</kbd></a>
				<a class="toggle-vis" href="#" data-column="2"><kbd id="kdb2" style="background-color: #73C6B6">IMG</kbd></a>
				<a class="toggle-vis" href="#" data-column="3"><kbd id="kdb3" style="background-color: #73C6B6">COD</kbd></a>
				<a class="toggle-vis" href="#" data-column="4"><kbd id="kdb4" style="background-color: #73C6B6">EQUIPO</kbd></a>
				<a class="toggle-vis" href="#" data-column="5"><kbd id="kdb5" style="background-color: #73C6B6">MON Compra</kbd></a>
				<a class="toggle-vis" href="#" data-column="6"><kbd id="kdb6" style="background-color: #73C6B6">P.U. Compra</kbd></a>
				<a class="toggle-vis" href="#" data-column="7"><kbd id="kdb7" style="background-color: #73C6B6">MON Vta/"""),_display_(/*74.110*/mapDiccionario/*74.124*/.get("ARR")),format.raw/*74.135*/("""</kbd></a>
				<a class="toggle-vis" href="#" data-column="8"><kbd id="kdb8" style="background-color: #73C6B6">P.U. Venta</kbd></a>
				<a class="toggle-vis" href="#" data-column="9"><kbd id="kdb9" style="background-color: #73C6B6">UN """),_display_(/*76.105*/mapDiccionario/*76.119*/.get("ARR")),format.raw/*76.130*/("""</kbd></a>
				<a class="toggle-vis" href="#" data-column="10"><kbd id="kdb10" style="background-color: #73C6B6">P.U. """),_display_(/*77.109*/mapDiccionario/*77.123*/.get("Arriendo")),format.raw/*77.139*/("""</kbd></a>
				<a class="toggle-vis" href="#" data-column="11"><kbd id="kdb11" style="background-color: #73C6B6">UN</kbd></a>
				<a class="toggle-vis" href="#" data-column="12"><kbd id="kdb12" style="background-color: #73C6B6">CANT</kbd></a>
				<a class="toggle-vis" href="#" data-column="13"><kbd id="kdb13" style="background-color: #73C6B6">TOTAL Compra</kbd></a>
				<a class="toggle-vis" href="#" data-column="14"><kbd id="kdb14" style="background-color: #73C6B6">TOTAL Venta</kbd></a>
				<a class="toggle-vis" href="#" data-column="15"><kbd id="kdb15" style="background-color: #73C6B6">Compra ("""),_display_(/*82.112*/mapDiccionario/*82.126*/.get("PESOS")),format.raw/*82.139*/(""")</kbd></a>
				<a class="toggle-vis" href="#" data-column="16"><kbd id="kdb16" style="background-color: #73C6B6">Venta ("""),_display_(/*83.111*/mapDiccionario/*83.125*/.get("PESOS")),format.raw/*83.138*/(""")</kbd></a>
		</div>
    				
    				
		<div class="table-responsive">
			<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
		        <thead style="background-color: #eeeeee">
			        <TR> 
						        <TH style= "text-align: right;vertical-align:top;width:10%"></TH>
						        <TH style= "text-align: right;vertical-align:top;"></TH>
								<TH style= "text-align: right;vertical-align:top;"></TH>
						        <TH style= "text-align: right;vertical-align:top;width:10%"></TH>
								<TH style= "text-align: right;vertical-align:top;width:30%;color:blue">SUBTOTALES (resultado de buscar):&nbsp</TH>
								<TH style= "text-align: right;vertical-align:top;color:blue"></TH>
								<TH style= "text-align: right;vertical-align:top;color:blue"></TH>
								<TH style= "text-align: center;vertical-align:top;color:blue"></TH>
								<TH style= "text-align: right;vertical-align:top;color:blue"></TH>
								<TH style= "text-align: right;vertical-align:top;color:blue"></TH>
								<TH style= "text-align: right;vertical-align:top;color:blue"></TH>
								<TH style= "text-align: right;vertical-align:top;color:blue"></TH>
								<TH style= "text-align: right;vertical-align:top;color:blue"></TH>
								<TH style= "text-align: right;vertical-align: top;color:blue"></TH>
								<TH style= "text-align: right;vertical-align: top;color:blue"></TH>
								<TH style= "text-align: right;vertical-align: top;color:blue"></TH>
				<!-- 15 -->		<TH style= "text-align: right;vertical-align: top;color:blue"></TH>
								<TH style= "text-align: right;vertical-align: top;width:4%;color:blue"></TH>
					</TR>
					<TR> 
					        <TH style= "text-align: center;vertical-align:top;width:10%">GRUPO</TH>
							<TH style= "text-align: center;vertical-align:top;width:10%">PROPIEDAD</TH>
					        <TH style= "text-align: center;vertical-align:top;">IMG</TH>
					        <TH style= "text-align: center;vertical-align:top;width:10%">COD</TH>
							<TH style= "text-align: center;vertical-align:top;width:30%">EQUIPO</TH>
							<TH style= "text-align: center;vertical-align:top;">MON</TH>
							<TH style= "text-align: center;vertical-align:top;">P.U.<br>Compra</TH>
							<TH style= "text-align: center;vertical-align:top;">MON</TH>
							<TH style= "text-align: center;vertical-align:top;">P.U.<br>Venta</TH>
							<TH style= "text-align: center;vertical-align:top;">UN """),_display_(/*120.64*/mapDiccionario/*120.78*/.get("ARR")),format.raw/*120.89*/("""</TH>
							<TH style= "text-align: center;vertical-align:top;">P.U.<br>"""),_display_(/*121.69*/mapDiccionario/*121.83*/.get("Arriendo")),format.raw/*121.99*/("""</TH>
							<TH style= "text-align: center;vertical-align:top;">UN</TH>
							<TH style= "text-align: center;vertical-align:top;">CANT</TH>
							<TH style= "text-align: center;vertical-align: top;">TOTAL<br>Compra</TH>
							<TH style= "text-align: center;vertical-align: top;">TOTAL<br>Venta</TH>
							<TH style= "text-align: center;vertical-align: top;">Compra<br>("""),_display_(/*126.73*/mapDiccionario/*126.87*/.get("PESOS")),format.raw/*126.100*/(""")</TH>
			<!-- 15 -->		<TH style= "text-align: center;vertical-align: top;">Venta<br>("""),_display_(/*127.81*/mapDiccionario/*127.95*/.get("PESOS")),format.raw/*127.108*/(""")</TH>
							<TH style= "text-align: center;vertical-align: top;width:4%">VER</TH>
					</TR>
				</thead>
				<tbody>
				"""),_display_(/*132.6*/for(lista1 <- lista) yield /*132.26*/{_display_(Seq[Any](format.raw/*132.27*/("""
				"""),format.raw/*133.5*/("""<TR>
					<td>"""),_display_(/*134.11*/lista1/*134.17*/.get(1)),format.raw/*134.24*/("""</td>
					<td>"""),_display_(/*135.11*/lista1/*135.17*/.get(23)),format.raw/*135.25*/("""</td>
					<td style="text-align:left;vertical-align:middle;">
						"""),_display_(if(lista1.get(16)!="0")/*137.31*/{_display_(Seq[Any](format.raw/*137.32*/("""
							"""),format.raw/*138.8*/("""<img src='/viewImg/"""),_display_(/*138.28*/lista1/*138.34*/.get(16)),format.raw/*138.42*/("""' style="width:auto; max-height:25px; max-width:50px;">
						""")))} else {null} ),format.raw/*139.8*/("""
					"""),format.raw/*140.6*/("""</td>
					<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="equipoDescripcion('"""),_display_(/*141.98*/lista1/*141.104*/.get(0)),format.raw/*141.111*/("""');">"""),_display_(/*141.117*/lista1/*141.123*/.get(2)),format.raw/*141.130*/("""</a></td>
					<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="equipoDescripcion('"""),_display_(/*142.98*/lista1/*142.104*/.get(0)),format.raw/*142.111*/("""');">"""),_display_(/*142.117*/lista1/*142.123*/.get(3)),format.raw/*142.130*/("""</a></td>
					<td style="text-align:center;vertical-align:middle;">"""),_display_(/*143.60*/lista1/*143.66*/.get(4)),format.raw/*143.73*/("""</td>
					<td style="text-align:right;vertical-align:middle;">"""),_display_(/*144.59*/lista1/*144.65*/.get(5)),format.raw/*144.72*/("""</td>
					<td style="text-align:center;vertical-align:middle;">"""),_display_(/*145.60*/lista1/*145.66*/.get(6)),format.raw/*145.73*/("""</td>
					<td style="text-align:right;vertical-align:middle;">"""),_display_(/*146.59*/lista1/*146.65*/.get(7)),format.raw/*146.72*/("""</td>
					<td style="text-align:center;vertical-align:middle;">"""),_display_(/*147.60*/lista1/*147.66*/.get(8)),format.raw/*147.73*/("""</td>
					<td style="text-align:right;vertical-align:middle;">"""),_display_(/*148.59*/lista1/*148.65*/.get(9)),format.raw/*148.72*/("""</td>
					<td style="text-align:center;vertical-align:middle;">"""),_display_(/*149.60*/lista1/*149.66*/.get(10)),format.raw/*149.74*/("""</td>
					<td style="text-align:right;vertical-align:middle;">"""),_display_(/*150.59*/lista1/*150.65*/.get(11)),format.raw/*150.73*/("""</td>
					<td style="text-align:right;vertical-align:middle;">"""),_display_(/*151.59*/lista1/*151.65*/.get(12)),format.raw/*151.73*/("""</td>
					<td style="text-align:right;vertical-align:middle;">"""),_display_(/*152.59*/lista1/*152.65*/.get(13)),format.raw/*152.73*/("""</td>
					<td style="text-align:right;vertical-align:middle;">"""),_display_(/*153.59*/lista1/*153.65*/.get(14)),format.raw/*153.73*/("""</td>
					<td style="text-align:right;vertical-align:middle;">"""),_display_(/*154.59*/lista1/*154.65*/.get(15)),format.raw/*154.73*/("""</td>
					
					<td title='SELECCIONAR' style="text-align:center;vertical-align:middle;">
						<form id="form_"""),_display_(/*157.23*/lista1/*157.29*/.get(0)),format.raw/*157.36*/("""" class="formulario" method="post" action="/reportInventarioGeneralXEquipo/">
							<input type="hidden" name="id_equipo" value=""""),_display_(/*158.54*/lista1/*158.60*/.get(0)),format.raw/*158.67*/("""">
							<input type="hidden" name="fechaCorte" value=""""),_display_(/*159.55*/fechaCorte),format.raw/*159.65*/("""">
							<input type="hidden" name="tipo" value=""""),_display_(/*160.49*/tipo),format.raw/*160.53*/("""">
							<a href="#" onclick="document.getElementById('form_"""),_display_(/*161.60*/lista1/*161.66*/.get(0)),format.raw/*161.73*/("""').submit()">
								<kbd style="background-color: #73C6B6">select</kbd>
							</a>
						</form>
					</td>
				</TR>
	 			""")))}),format.raw/*167.7*/("""
				"""),format.raw/*168.5*/("""</tbody>
			</table>
		</div>
	</div>
	
	<form id="excel" class="formulario" method="post" action="/reportInventarioEquipoExcel/">
		<input type="hidden" name="fechaCorte" value=""""),_display_(/*174.50*/fechaCorte),format.raw/*174.60*/("""">
		<input type="hidden" name="tipo" value=""""),_display_(/*175.44*/tipo),format.raw/*175.48*/("""">
	</form>

""")))}),format.raw/*178.2*/("""


"""),format.raw/*181.1*/("""<script type="text/javascript">
	let flag = true;
	$(document).ready(function() """),format.raw/*183.31*/("""{"""),format.raw/*183.32*/("""
            """),format.raw/*184.13*/("""sumarTotales2();
            flag = false;

		 $('#tablaPrincipal').DataTable("""),format.raw/*187.35*/("""{"""),format.raw/*187.36*/("""
		    	"""),format.raw/*188.8*/(""""fixedHeader": true,
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [[ 3, "asc" ]],
		    	"language": """),format.raw/*193.20*/("""{"""),format.raw/*193.21*/("""
		        	"""),format.raw/*194.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*195.11*/("""}"""),format.raw/*195.12*/("""
		  """),format.raw/*196.5*/("""}"""),format.raw/*196.6*/(""" """),format.raw/*196.7*/(""");
		  
		   $('a.toggle-vis').on('click', function (e) """),format.raw/*198.49*/("""{"""),format.raw/*198.50*/("""
		        """),format.raw/*199.11*/("""e.preventDefault();
		        // Get the column API object
		        let col = $(this).attr('data-column');
		        var column = $('#tablaPrincipal').DataTable().column(col);
		        // Toggle the visibility
		        column.visible(!column.visible());
		        if(column.visible())"""),format.raw/*205.31*/("""{"""),format.raw/*205.32*/("""
					"""),format.raw/*206.6*/("""document.getElementById('kdb'+col).style.backgroundColor = "#73C6B6";
				"""),format.raw/*207.5*/("""}"""),format.raw/*207.6*/("""else"""),format.raw/*207.10*/("""{"""),format.raw/*207.11*/("""
					"""),format.raw/*208.6*/("""document.getElementById('kdb'+col).style.backgroundColor = "#D6DBDF";
				"""),format.raw/*209.5*/("""}"""),format.raw/*209.6*/("""
		    """),format.raw/*210.7*/("""}"""),format.raw/*210.8*/(""");

			document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*213.2*/("""}"""),format.raw/*213.3*/(""");
	
	function sumarTotales2() """),format.raw/*215.27*/("""{"""),format.raw/*215.28*/("""
		"""),format.raw/*216.3*/("""var tabla = document.getElementById("tablaPrincipal");
		var cuentaItems = 0;
		var sumaCantidades = 0;
		if(flag)"""),format.raw/*219.11*/("""{"""),format.raw/*219.12*/("""
			"""),format.raw/*220.4*/("""var sumaGranTotalCompra = 0;
			var sumaGranTotalVenta = 0;
		"""),format.raw/*222.3*/("""}"""),format.raw/*222.4*/("""
		"""),format.raw/*223.3*/("""var sumaTotalesCompraMO = 0;
		var sumaTotalesVentaMO = 0;
		var sumaTotalesCompraMP = 0;
		var sumaTotalesVentaMP = 0;
		
		var numero = new DecimalFormat("#,##0.00");

		for (var i = 2; i < tabla.rows.length; i++) """),format.raw/*230.47*/("""{"""),format.raw/*230.48*/(""" 
			"""),format.raw/*231.4*/("""var aux = 0;
			
			if(flag)"""),format.raw/*233.12*/("""{"""),format.raw/*233.13*/("""
				"""),format.raw/*234.5*/("""aux = tabla.rows[i].cells[15].textContent;
				sumaGranTotalCompra = parseFloat(sumaGranTotalCompra) + parseFloat(numero.formatBack(aux));
				aux = tabla.rows[i].cells[16].textContent;
				sumaGranTotalVenta = parseFloat(sumaGranTotalVenta) + parseFloat(numero.formatBack(aux));
			"""),format.raw/*238.4*/("""}"""),format.raw/*238.5*/("""
			
			"""),format.raw/*240.4*/("""if(tabla.rows[i].style.display != "none")"""),format.raw/*240.45*/("""{"""),format.raw/*240.46*/("""
				"""),format.raw/*241.5*/("""cuentaItems++;
				aux = tabla.rows[i].cells[12].textContent;
				sumaCantidades = parseFloat(sumaCantidades) + parseFloat(numero.formatBack(aux));
				aux = tabla.rows[i].cells[13].textContent;
				sumaTotalesCompraMO = parseFloat(sumaTotalesCompraMO) + parseFloat(numero.formatBack(aux));
				aux = tabla.rows[i].cells[14].textContent;
				sumaTotalesVentaMO = parseFloat(sumaTotalesVentaMO) + parseFloat(numero.formatBack(aux));
				aux = tabla.rows[i].cells[15].textContent;
				sumaTotalesCompraMP = parseFloat(sumaTotalesCompraMP) + parseFloat(numero.formatBack(aux));
				aux = tabla.rows[i].cells[16].textContent;
				sumaTotalesVentaMP = parseFloat(sumaTotalesVentaMP) + parseFloat(numero.formatBack(aux));
			"""),format.raw/*252.4*/("""}"""),format.raw/*252.5*/("""
			
		"""),format.raw/*254.3*/("""}"""),format.raw/*254.4*/("""
		
		"""),format.raw/*256.3*/("""tabla.rows[0].cells[12].textContent = formatStandar0(sumaCantidades);
		tabla.rows[0].cells[5].textContent = formatStandar0(cuentaItems);
		tabla.rows[0].cells[6].textContent = formatStandar0("elementos");
		tabla.rows[0].cells[13].textContent = formatStandar0(sumaTotalesCompraMO);
		tabla.rows[0].cells[14].textContent = formatStandar0(sumaTotalesVentaMO);
		tabla.rows[0].cells[15].textContent = formatStandar0(sumaTotalesCompraMP);
		tabla.rows[0].cells[16].textContent = formatStandar0(sumaTotalesVentaMP);
		if(flag)"""),format.raw/*263.11*/("""{"""),format.raw/*263.12*/("""
			"""),format.raw/*264.4*/("""document.getElementById('sumaGranTotalCompra').value=formatStandar0(sumaGranTotalCompra);
			document.getElementById('sumaGranTotalVenta').value=formatStandar0(sumaGranTotalVenta);
		"""),format.raw/*266.3*/("""}"""),format.raw/*266.4*/("""
		
		
		
	"""),format.raw/*270.2*/("""}"""),format.raw/*270.3*/("""
	
"""),format.raw/*272.1*/("""</script>




		
		
	
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
                  SOURCE: app/viewsMnuReportes/reportInventarioEquipo.scala.html
                  HASH: 93d4ec89ab3c4f29bb2c9c2e31bc4f6d57a65d4f
                  MATRIX: 1055->1|1305->158|1338->166|1354->174|1393->176|1422->180|1490->228|1520->233|1564->257|1595->261|1672->312|1850->468|1883->474|3060->1624|3083->1638|3118->1651|3438->1944|3461->1958|3496->1971|4679->3126|4703->3140|4736->3151|5000->3387|5024->3401|5057->3412|5204->3531|5228->3545|5266->3561|5897->4164|5921->4178|5956->4191|6106->4313|6130->4327|6165->4340|8644->6791|8668->6805|8701->6816|8803->6890|8827->6904|8865->6920|9268->7295|9292->7309|9328->7322|9443->7409|9467->7423|9503->7436|9655->7561|9692->7581|9732->7582|9765->7587|9808->7602|9824->7608|9853->7615|9897->7631|9913->7637|9943->7645|10064->7738|10104->7739|10140->7747|10188->7767|10204->7773|10234->7781|10341->7844|10375->7850|10506->7953|10523->7959|10553->7966|10588->7972|10605->7978|10635->7985|10770->8092|10787->8098|10817->8105|10852->8111|10869->8117|10899->8124|10996->8193|11012->8199|11041->8206|11133->8270|11149->8276|11178->8283|11271->8348|11287->8354|11316->8361|11408->8425|11424->8431|11453->8438|11546->8503|11562->8509|11591->8516|11683->8580|11699->8586|11728->8593|11821->8658|11837->8664|11867->8672|11959->8736|11975->8742|12005->8750|12097->8814|12113->8820|12143->8828|12235->8892|12251->8898|12281->8906|12373->8970|12389->8976|12419->8984|12511->9048|12527->9054|12557->9062|12698->9175|12714->9181|12743->9188|12902->9319|12918->9325|12947->9332|13032->9389|13064->9399|13143->9450|13169->9454|13259->9516|13275->9522|13304->9529|13462->9656|13495->9661|13703->9841|13735->9851|13809->9897|13835->9901|13880->9915|13911->9918|14020->9998|14050->9999|14092->10012|14199->10090|14229->10091|14265->10099|14433->10238|14463->10239|14504->10251|14611->10329|14641->10330|14674->10335|14703->10336|14732->10337|14817->10393|14847->10394|14887->10405|15203->10692|15233->10693|15267->10699|15369->10773|15398->10774|15431->10778|15461->10779|15495->10785|15597->10859|15626->10860|15661->10867|15690->10868|15791->10941|15820->10942|15880->10973|15910->10974|15941->10977|16084->11091|16114->11092|16146->11096|16236->11158|16265->11159|16296->11162|16541->11378|16571->11379|16604->11384|16661->11412|16691->11413|16724->11418|17035->11701|17064->11702|17100->11710|17170->11751|17200->11752|17233->11757|17980->12476|18009->12477|18044->12484|18073->12485|18107->12491|18658->13013|18688->13014|18720->13018|18931->13201|18960->13202|18999->13213|19028->13214|19059->13217
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|42->11|43->12|43->12|45->14|76->45|76->45|76->45|84->53|84->53|84->53|105->74|105->74|105->74|107->76|107->76|107->76|108->77|108->77|108->77|113->82|113->82|113->82|114->83|114->83|114->83|151->120|151->120|151->120|152->121|152->121|152->121|157->126|157->126|157->126|158->127|158->127|158->127|163->132|163->132|163->132|164->133|165->134|165->134|165->134|166->135|166->135|166->135|168->137|168->137|169->138|169->138|169->138|169->138|170->139|171->140|172->141|172->141|172->141|172->141|172->141|172->141|173->142|173->142|173->142|173->142|173->142|173->142|174->143|174->143|174->143|175->144|175->144|175->144|176->145|176->145|176->145|177->146|177->146|177->146|178->147|178->147|178->147|179->148|179->148|179->148|180->149|180->149|180->149|181->150|181->150|181->150|182->151|182->151|182->151|183->152|183->152|183->152|184->153|184->153|184->153|185->154|185->154|185->154|188->157|188->157|188->157|189->158|189->158|189->158|190->159|190->159|191->160|191->160|192->161|192->161|192->161|198->167|199->168|205->174|205->174|206->175|206->175|209->178|212->181|214->183|214->183|215->184|218->187|218->187|219->188|224->193|224->193|225->194|226->195|226->195|227->196|227->196|227->196|229->198|229->198|230->199|236->205|236->205|237->206|238->207|238->207|238->207|238->207|239->208|240->209|240->209|241->210|241->210|244->213|244->213|246->215|246->215|247->216|250->219|250->219|251->220|253->222|253->222|254->223|261->230|261->230|262->231|264->233|264->233|265->234|269->238|269->238|271->240|271->240|271->240|272->241|283->252|283->252|285->254|285->254|287->256|294->263|294->263|295->264|297->266|297->266|301->270|301->270|303->272
                  -- GENERATED --
              */
          