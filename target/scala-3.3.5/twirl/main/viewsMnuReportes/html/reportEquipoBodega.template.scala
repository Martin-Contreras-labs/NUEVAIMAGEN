
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

object reportEquipoBodega extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String,play.twirl.api.HtmlFormat.Appendable] {

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
		"""),_display_(/*12.4*/barraTitulo(mapDiccionario, "INVENTARIO - "+tipo+" - POR "+mapDiccionario.get("BODEGA")+" Y POR EQUIPO (valorizado a precio compra y lista)","FECHA DE CORTE: " + utilities.Fechas.DDMMAA(fechaCorte))),format.raw/*12.202*/("""
		
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
				<a class="toggle-vis" href="#" data-column="0"><kbd id="kdb0" style="background-color: #73C6B6">SUCURSAL</kbd></a>
				<a class="toggle-vis" href="#" data-column="1"><kbd id="kdb1" style="background-color: #73C6B6">"""),_display_(/*68.102*/mapDiccionario/*68.116*/.get("BODEGA")),format.raw/*68.130*/("""/PROYECTO</kbd></a>
				<a class="toggle-vis" href="#" data-column="2"><kbd id="kdb2" style="background-color: #73C6B6">GRUPO</kbd></a>
				<a class="toggle-vis" href="#" data-column="3"><kbd id="kdb3" style="background-color: #73C6B6">PROPIEDAD</kbd></a>
				<a class="toggle-vis" href="#" data-column="4"><kbd id="kdb4" style="background-color: #73C6B6">IMG</kbd></a>
				<a class="toggle-vis" href="#" data-column="5"><kbd id="kdb5" style="background-color: #73C6B6">COD</kbd></a>
				<a class="toggle-vis" href="#" data-column="6"><kbd id="kdb6" style="background-color: #73C6B6">EQUIPO</kbd></a>
				<a class="toggle-vis" href="#" data-column="7"><kbd id="kdb7" style="background-color: #73C6B6">MON Compra</kbd></a>
				<a class="toggle-vis" href="#" data-column="8"><kbd id="kdb8" style="background-color: #73C6B6">P.U. Compra</kbd></a>
				<a class="toggle-vis" href="#" data-column="9"><kbd id="kdb9" style="background-color: #73C6B6">MON Vta/"""),_display_(/*76.110*/mapDiccionario/*76.124*/.get("ARR")),format.raw/*76.135*/("""</kbd></a>
				<a class="toggle-vis" href="#" data-column="10"><kbd id="kdb10" style="background-color: #73C6B6">P.U. Venta</kbd></a>
				<a class="toggle-vis" href="#" data-column="11"><kbd id="kdb11" style="background-color: #73C6B6">UN """),_display_(/*78.107*/mapDiccionario/*78.121*/.get("ARR")),format.raw/*78.132*/("""</kbd></a>
				<a class="toggle-vis" href="#" data-column="12"><kbd id="kdb12" style="background-color: #73C6B6">P.U. """),_display_(/*79.109*/mapDiccionario/*79.123*/.get("Arriendo")),format.raw/*79.139*/("""</kbd></a>
				<a class="toggle-vis" href="#" data-column="13"><kbd id="kdb13" style="background-color: #73C6B6">UN</kbd></a>
				<a class="toggle-vis" href="#" data-column="14"><kbd id="kdb14" style="background-color: #73C6B6">CANT</kbd></a>
				<a class="toggle-vis" href="#" data-column="15"><kbd id="kdb15" style="background-color: #73C6B6">TOTAL Compra</kbd></a>
				<a class="toggle-vis" href="#" data-column="16"><kbd id="kdb16" style="background-color: #73C6B6">TOTAL Venta</kbd></a>
				<a class="toggle-vis" href="#" data-column="17"><kbd id="kdb17" style="background-color: #73C6B6">Compra ("""),_display_(/*84.112*/mapDiccionario/*84.126*/.get("PESOS")),format.raw/*84.139*/(""")</kbd></a>
				<a class="toggle-vis" href="#" data-column="18"><kbd id="kdb18" style="background-color: #73C6B6">Venta ("""),_display_(/*85.111*/mapDiccionario/*85.125*/.get("PESOS")),format.raw/*85.138*/(""")</kbd></a>
		</div>
    				
    				
		<div class="table-responsive">
			<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
		        <thead style="background-color: #eeeeee">
			        <TR> 
						        <TH style= "text-align: right;vertical-align:top;width:10%"></TH>
								<TH style= "text-align: right;vertical-align:top;width:10%"></TH>
								<TH style= "text-align: right;vertical-align:top;width:10%"></TH>
 								<TH style= "text-align: right;vertical-align:top;width:10%"></TH>
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
							<TH style= "text-align: center;vertical-align:top;width:10%">SUCURSAL</TH>
					 		<TH style= "text-align: center;vertical-align:top;width:10%">"""),_display_(/*116.71*/mapDiccionario/*116.85*/.get("BODEGA")),format.raw/*116.99*/("""/PROYECTO</TH>
					        <TH style= "text-align: center;vertical-align:top;width:10%">GRUPO</TH>
							<TH style= "text-align: center;vertical-align:top;width:10%">PROPIEDAD</TH>
					        <TH style= "text-align: center;vertical-align:top;">IMG</TH>
					        <TH style= "text-align: center;vertical-align:top;width:10%">COD</TH>
							<TH style= "text-align: center;vertical-align:top;width:30%">EQUIPO</TH>
							<TH style= "text-align: center;vertical-align:top;">MON</TH>
							<TH style= "text-align: center;vertical-align:top;">P.U.<br>Compra</TH>
							<TH style= "text-align: center;vertical-align:top;">MON</TH>
							<TH style= "text-align: center;vertical-align:top;">P.U.<br>Venta</TH>
							<TH style= "text-align: center;vertical-align:top;">UN """),_display_(/*126.64*/mapDiccionario/*126.78*/.get("ARR")),format.raw/*126.89*/("""</TH>
							<TH style= "text-align: center;vertical-align:top;">P.U.<br>"""),_display_(/*127.69*/mapDiccionario/*127.83*/.get("Arriendo")),format.raw/*127.99*/("""</TH>
							<TH style= "text-align: center;vertical-align:top;">UN</TH>
							<TH style= "text-align: center;vertical-align:top;">CANT</TH>
							<TH style= "text-align: center;vertical-align: top;">TOTAL<br>Compra</TH>
							<TH style= "text-align: center;vertical-align: top;">TOTAL<br>Venta</TH>
							<TH style= "text-align: center;vertical-align: top;">Compra<br>("""),_display_(/*132.73*/mapDiccionario/*132.87*/.get("PESOS")),format.raw/*132.100*/(""")</TH>
			<!-- 15 -->		<TH style= "text-align: center;vertical-align: top;">Venta<br>("""),_display_(/*133.81*/mapDiccionario/*133.95*/.get("PESOS")),format.raw/*133.108*/(""")</TH>
							<TH style= "text-align: center;vertical-align: top;width:4%">VER</TH>
					</TR>
				</thead>
				<tbody>
				"""),_display_(/*138.6*/for(lista1 <- lista) yield /*138.26*/{_display_(Seq[Any](format.raw/*138.27*/("""
				"""),format.raw/*139.5*/("""<TR>
					<td>"""),_display_(/*140.11*/lista1/*140.17*/.get(22)),format.raw/*140.25*/("""</td>
					<td>"""),_display_(/*141.11*/lista1/*141.17*/.get(21)),format.raw/*141.25*/("""</td>
					<td>"""),_display_(/*142.11*/lista1/*142.17*/.get(1)),format.raw/*142.24*/("""</td>
					<td>"""),_display_(/*143.11*/lista1/*143.17*/.get(23)),format.raw/*143.25*/("""</td>
					<td style="text-align:left;vertical-align:middle;">
						"""),_display_(if(lista1.get(16)!="0")/*145.31*/{_display_(Seq[Any](format.raw/*145.32*/("""
							"""),format.raw/*146.8*/("""<img src='/viewImg/"""),_display_(/*146.28*/lista1/*146.34*/.get(16)),format.raw/*146.42*/("""' style="width:auto; max-height:25px; max-width:50px;">
						""")))} else {null} ),format.raw/*147.8*/("""
					"""),format.raw/*148.6*/("""</td>
					<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="equipoDescripcion('"""),_display_(/*149.98*/lista1/*149.104*/.get(0)),format.raw/*149.111*/("""');">"""),_display_(/*149.117*/lista1/*149.123*/.get(2)),format.raw/*149.130*/("""</a></td>
					<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="equipoDescripcion('"""),_display_(/*150.98*/lista1/*150.104*/.get(0)),format.raw/*150.111*/("""');">"""),_display_(/*150.117*/lista1/*150.123*/.get(3)),format.raw/*150.130*/("""</a></td>
					<td style="text-align:center;vertical-align:middle;">"""),_display_(/*151.60*/lista1/*151.66*/.get(4)),format.raw/*151.73*/("""</td>
					<td style="text-align:right;vertical-align:middle;">"""),_display_(/*152.59*/lista1/*152.65*/.get(5)),format.raw/*152.72*/("""</td>
					<td style="text-align:center;vertical-align:middle;">"""),_display_(/*153.60*/lista1/*153.66*/.get(6)),format.raw/*153.73*/("""</td>
					<td style="text-align:right;vertical-align:middle;">"""),_display_(/*154.59*/lista1/*154.65*/.get(7)),format.raw/*154.72*/("""</td>
					<td style="text-align:center;vertical-align:middle;">"""),_display_(/*155.60*/lista1/*155.66*/.get(8)),format.raw/*155.73*/("""</td>
					<td style="text-align:right;vertical-align:middle;">"""),_display_(/*156.59*/lista1/*156.65*/.get(9)),format.raw/*156.72*/("""</td>
					<td style="text-align:center;vertical-align:middle;">"""),_display_(/*157.60*/lista1/*157.66*/.get(10)),format.raw/*157.74*/("""</td>
					<td style="text-align:right;vertical-align:middle;">"""),_display_(/*158.59*/lista1/*158.65*/.get(11)),format.raw/*158.73*/("""</td>
					<td style="text-align:right;vertical-align:middle;">"""),_display_(/*159.59*/lista1/*159.65*/.get(12)),format.raw/*159.73*/("""</td>
					<td style="text-align:right;vertical-align:middle;">"""),_display_(/*160.59*/lista1/*160.65*/.get(13)),format.raw/*160.73*/("""</td>
					<td style="text-align:right;vertical-align:middle;">"""),_display_(/*161.59*/lista1/*161.65*/.get(14)),format.raw/*161.73*/("""</td>
					<td style="text-align:right;vertical-align:middle;">"""),_display_(/*162.59*/lista1/*162.65*/.get(15)),format.raw/*162.73*/("""</td>
					
					<td title='SELECCIONAR' style="text-align:center;vertical-align:middle;">
						<form id="form_"""),_display_(/*165.23*/lista1/*165.29*/.get(0)),format.raw/*165.36*/("""" class="formulario" method="post" action="/reportEquipoBodegaXEquipo/">
							<input type="hidden" name="id_equipo" value=""""),_display_(/*166.54*/lista1/*166.60*/.get(0)),format.raw/*166.67*/("""">
							<input type="hidden" name="fechaCorte" value=""""),_display_(/*167.55*/fechaCorte),format.raw/*167.65*/("""">
							<input type="hidden" name="tipo" value=""""),_display_(/*168.49*/tipo),format.raw/*168.53*/("""">
							<a href="#" onclick="document.getElementById('form_"""),_display_(/*169.60*/lista1/*169.66*/.get(0)),format.raw/*169.73*/("""').submit()">
								<kbd style="background-color: #73C6B6">select</kbd>
							</a>
						</form>
					</td>
				</TR>
	 			""")))}),format.raw/*175.7*/("""
				"""),format.raw/*176.5*/("""</tbody>
			</table>
		</div>
	</div>
	
	<form id="excel" class="formulario" method="post" action="/reportEquipoBodegaExcel/">
		<input type="hidden" name="fechaCorte" value=""""),_display_(/*182.50*/fechaCorte),format.raw/*182.60*/("""">
		<input type="hidden" name="tipo" value=""""),_display_(/*183.44*/tipo),format.raw/*183.48*/("""">
	</form>

""")))}),format.raw/*186.2*/("""


"""),format.raw/*189.1*/("""<script type="text/javascript">
	let flag = true;
	$(document).ready(function() """),format.raw/*191.31*/("""{"""),format.raw/*191.32*/("""
            """),format.raw/*192.13*/("""sumarTotales2();
            flag = false;

		 $('#tablaPrincipal').DataTable("""),format.raw/*195.35*/("""{"""),format.raw/*195.36*/("""
		    	"""),format.raw/*196.8*/(""""fixedHeader": true,
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [[ 5, "asc" ]],
		    	"language": """),format.raw/*201.20*/("""{"""),format.raw/*201.21*/("""
		        	"""),format.raw/*202.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*203.11*/("""}"""),format.raw/*203.12*/("""
		  """),format.raw/*204.5*/("""}"""),format.raw/*204.6*/(""" """),format.raw/*204.7*/(""");
		  
		   $('a.toggle-vis').on('click', function (e) """),format.raw/*206.49*/("""{"""),format.raw/*206.50*/("""
		        """),format.raw/*207.11*/("""e.preventDefault();
		        // Get the column API object
		        let col = $(this).attr('data-column');
		        var column = $('#tablaPrincipal').DataTable().column(col);
		        // Toggle the visibility
		        column.visible(!column.visible());
		        if(column.visible())"""),format.raw/*213.31*/("""{"""),format.raw/*213.32*/("""
					"""),format.raw/*214.6*/("""document.getElementById('kdb'+col).style.backgroundColor = "#73C6B6";
				"""),format.raw/*215.5*/("""}"""),format.raw/*215.6*/("""else"""),format.raw/*215.10*/("""{"""),format.raw/*215.11*/("""
					"""),format.raw/*216.6*/("""document.getElementById('kdb'+col).style.backgroundColor = "#D6DBDF";
				"""),format.raw/*217.5*/("""}"""),format.raw/*217.6*/("""
		    """),format.raw/*218.7*/("""}"""),format.raw/*218.8*/(""");

			document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*221.2*/("""}"""),format.raw/*221.3*/(""");
	
	function sumarTotales2() """),format.raw/*223.27*/("""{"""),format.raw/*223.28*/("""
		"""),format.raw/*224.3*/("""var tabla = document.getElementById("tablaPrincipal");
		var cuentaItems = 0;
		var sumaCantidades = 0;
		if(flag)"""),format.raw/*227.11*/("""{"""),format.raw/*227.12*/("""
			"""),format.raw/*228.4*/("""var sumaGranTotalCompra = 0;
			var sumaGranTotalVenta = 0;
		"""),format.raw/*230.3*/("""}"""),format.raw/*230.4*/("""
		"""),format.raw/*231.3*/("""var sumaTotalesCompraMO = 0;
		var sumaTotalesVentaMO = 0;
		var sumaTotalesCompraMP = 0;
		var sumaTotalesVentaMP = 0;
		
		var numero = new DecimalFormat("#,##0.00");

		for (var i = 2; i < tabla.rows.length; i++) """),format.raw/*238.47*/("""{"""),format.raw/*238.48*/(""" 
			"""),format.raw/*239.4*/("""var aux = 0;
			
			if(flag)"""),format.raw/*241.12*/("""{"""),format.raw/*241.13*/("""
				"""),format.raw/*242.5*/("""aux = tabla.rows[i].cells[15].textContent;
				sumaGranTotalCompra = parseFloat(sumaGranTotalCompra) + parseFloat(numero.formatBack(aux));
				aux = tabla.rows[i].cells[16].textContent;
				sumaGranTotalVenta = parseFloat(sumaGranTotalVenta) + parseFloat(numero.formatBack(aux));
			"""),format.raw/*246.4*/("""}"""),format.raw/*246.5*/("""
			
			"""),format.raw/*248.4*/("""if(tabla.rows[i].style.display != "none")"""),format.raw/*248.45*/("""{"""),format.raw/*248.46*/("""
				"""),format.raw/*249.5*/("""cuentaItems++;
				aux = tabla.rows[i].cells[14].textContent;
				sumaCantidades = parseFloat(sumaCantidades) + parseFloat(numero.formatBack(aux));
				aux = tabla.rows[i].cells[15].textContent;
				sumaTotalesCompraMO = parseFloat(sumaTotalesCompraMO) + parseFloat(numero.formatBack(aux));
				aux = tabla.rows[i].cells[16].textContent;
				sumaTotalesVentaMO = parseFloat(sumaTotalesVentaMO) + parseFloat(numero.formatBack(aux));
				aux = tabla.rows[i].cells[17].textContent;
				sumaTotalesCompraMP = parseFloat(sumaTotalesCompraMP) + parseFloat(numero.formatBack(aux));
				aux = tabla.rows[i].cells[18].textContent;
				sumaTotalesVentaMP = parseFloat(sumaTotalesVentaMP) + parseFloat(numero.formatBack(aux));
			"""),format.raw/*260.4*/("""}"""),format.raw/*260.5*/("""
			
		"""),format.raw/*262.3*/("""}"""),format.raw/*262.4*/("""
		
		"""),format.raw/*264.3*/("""tabla.rows[0].cells[14].textContent = formatStandar0(sumaCantidades);
		tabla.rows[0].cells[6].textContent = formatStandar0(cuentaItems);
		tabla.rows[0].cells[7].textContent = formatStandar0("elementos");
		tabla.rows[0].cells[15].textContent = formatStandar0(sumaTotalesCompraMO);
		tabla.rows[0].cells[16].textContent = formatStandar0(sumaTotalesVentaMO);
		tabla.rows[0].cells[17].textContent = formatStandar0(sumaTotalesCompraMP);
		tabla.rows[0].cells[18].textContent = formatStandar0(sumaTotalesVentaMP);
		if(flag)"""),format.raw/*271.11*/("""{"""),format.raw/*271.12*/("""
			"""),format.raw/*272.4*/("""document.getElementById('sumaGranTotalCompra').value=formatStandar0(sumaGranTotalCompra);
			document.getElementById('sumaGranTotalVenta').value=formatStandar0(sumaGranTotalVenta);
		"""),format.raw/*274.3*/("""}"""),format.raw/*274.4*/("""
		
		
		
	"""),format.raw/*278.2*/("""}"""),format.raw/*278.3*/("""
	
"""),format.raw/*280.1*/("""</script>




		
		
	
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
                  SOURCE: app/viewsMnuReportes/reportEquipoBodega.scala.html
                  HASH: 096e12e881b4e08d8e9fff8e5348644bed75886a
                  MATRIX: 1051->1|1301->158|1334->166|1350->174|1389->176|1418->180|1486->228|1516->233|1560->257|1591->261|1668->312|1888->510|1921->516|3098->1666|3121->1680|3156->1693|3476->1986|3499->2000|3534->2013|4003->2454|4027->2468|4063->2482|5044->3435|5068->3449|5101->3460|5369->3700|5393->3714|5426->3725|5573->3844|5597->3858|5635->3874|6266->4477|6290->4491|6325->4504|6475->4626|6499->4640|6534->4653|8561->6652|8585->6666|8621->6680|9425->7456|9449->7470|9482->7481|9584->7555|9608->7569|9646->7585|10049->7960|10073->7974|10109->7987|10224->8074|10248->8088|10284->8101|10436->8226|10473->8246|10513->8247|10546->8252|10589->8267|10605->8273|10635->8281|10679->8297|10695->8303|10725->8311|10769->8327|10785->8333|10814->8340|10858->8356|10874->8362|10904->8370|11025->8463|11065->8464|11101->8472|11149->8492|11165->8498|11195->8506|11302->8569|11336->8575|11467->8678|11484->8684|11514->8691|11549->8697|11566->8703|11596->8710|11731->8817|11748->8823|11778->8830|11813->8836|11830->8842|11860->8849|11957->8918|11973->8924|12002->8931|12094->8995|12110->9001|12139->9008|12232->9073|12248->9079|12277->9086|12369->9150|12385->9156|12414->9163|12507->9228|12523->9234|12552->9241|12644->9305|12660->9311|12689->9318|12782->9383|12798->9389|12828->9397|12920->9461|12936->9467|12966->9475|13058->9539|13074->9545|13104->9553|13196->9617|13212->9623|13242->9631|13334->9695|13350->9701|13380->9709|13472->9773|13488->9779|13518->9787|13659->9900|13675->9906|13704->9913|13858->10039|13874->10045|13903->10052|13988->10109|14020->10119|14099->10170|14125->10174|14215->10236|14231->10242|14260->10249|14418->10376|14451->10381|14655->10557|14687->10567|14761->10613|14787->10617|14832->10631|14863->10634|14972->10714|15002->10715|15044->10728|15151->10806|15181->10807|15217->10815|15385->10954|15415->10955|15456->10967|15563->11045|15593->11046|15626->11051|15655->11052|15684->11053|15769->11109|15799->11110|15839->11121|16155->11408|16185->11409|16219->11415|16321->11489|16350->11490|16383->11494|16413->11495|16447->11501|16549->11575|16578->11576|16613->11583|16642->11584|16743->11657|16772->11658|16832->11689|16862->11690|16893->11693|17036->11807|17066->11808|17098->11812|17188->11874|17217->11875|17248->11878|17493->12094|17523->12095|17556->12100|17613->12128|17643->12129|17676->12134|17987->12417|18016->12418|18052->12426|18122->12467|18152->12468|18185->12473|18932->13192|18961->13193|18996->13200|19025->13201|19059->13207|19610->13729|19640->13730|19672->13734|19883->13917|19912->13918|19951->13929|19980->13930|20011->13933
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|42->11|43->12|43->12|45->14|76->45|76->45|76->45|84->53|84->53|84->53|99->68|99->68|99->68|107->76|107->76|107->76|109->78|109->78|109->78|110->79|110->79|110->79|115->84|115->84|115->84|116->85|116->85|116->85|147->116|147->116|147->116|157->126|157->126|157->126|158->127|158->127|158->127|163->132|163->132|163->132|164->133|164->133|164->133|169->138|169->138|169->138|170->139|171->140|171->140|171->140|172->141|172->141|172->141|173->142|173->142|173->142|174->143|174->143|174->143|176->145|176->145|177->146|177->146|177->146|177->146|178->147|179->148|180->149|180->149|180->149|180->149|180->149|180->149|181->150|181->150|181->150|181->150|181->150|181->150|182->151|182->151|182->151|183->152|183->152|183->152|184->153|184->153|184->153|185->154|185->154|185->154|186->155|186->155|186->155|187->156|187->156|187->156|188->157|188->157|188->157|189->158|189->158|189->158|190->159|190->159|190->159|191->160|191->160|191->160|192->161|192->161|192->161|193->162|193->162|193->162|196->165|196->165|196->165|197->166|197->166|197->166|198->167|198->167|199->168|199->168|200->169|200->169|200->169|206->175|207->176|213->182|213->182|214->183|214->183|217->186|220->189|222->191|222->191|223->192|226->195|226->195|227->196|232->201|232->201|233->202|234->203|234->203|235->204|235->204|235->204|237->206|237->206|238->207|244->213|244->213|245->214|246->215|246->215|246->215|246->215|247->216|248->217|248->217|249->218|249->218|252->221|252->221|254->223|254->223|255->224|258->227|258->227|259->228|261->230|261->230|262->231|269->238|269->238|270->239|272->241|272->241|273->242|277->246|277->246|279->248|279->248|279->248|280->249|291->260|291->260|293->262|293->262|295->264|302->271|302->271|303->272|305->274|305->274|309->278|309->278|311->280
                  -- GENERATED --
              */
          