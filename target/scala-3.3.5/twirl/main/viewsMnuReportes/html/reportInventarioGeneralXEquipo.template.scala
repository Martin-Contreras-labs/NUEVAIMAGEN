
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

object reportInventarioGeneralXEquipo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String,play.twirl.api.HtmlFormat.Appendable] {

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
		"""),_display_(/*13.4*/barraTitulo(mapDiccionario, "INVENTARIO - "+tipo+" - POR EQUIPO (valorizado con precios cliente y/o lista)","FECHA DE CORTE: "+ utilities.Fechas.DDMMAA(fechaCorte))),format.raw/*13.168*/("""
		
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
				<a class="toggle-vis" href="#" data-column="3"><kbd id="kdb3" style="background-color: #73C6B6">PROPIEDAD</kbd></a>
				<a class="toggle-vis" href="#" data-column="4"><kbd id="kdb4" style="background-color: #73C6B6">COD</kbd></a>
				<a class="toggle-vis" href="#" data-column="5"><kbd id="kdb5" style="background-color: #73C6B6">EQUIPO</kbd></a>
				<a class="toggle-vis" href="#" data-column="6"><kbd id="kdb6" style="background-color: #73C6B6">MON Compra</kbd></a>
				<a class="toggle-vis" href="#" data-column="7"><kbd id="kdb7" style="background-color: #73C6B6">P.U. Compra</kbd></a>
				<a class="toggle-vis" href="#" data-column="8"><kbd id="kdb8" style="background-color: #73C6B6">MON Vta/"""),_display_(/*79.110*/mapDiccionario/*79.124*/.get("ARR")),format.raw/*79.135*/("""</kbd></a>
				<a class="toggle-vis" href="#" data-column="9"><kbd id="kdb9" style="background-color: #73C6B6">P.U. Venta</kbd></a>
				<a class="toggle-vis" href="#" data-column="10"><kbd id="kdb10" style="background-color: #73C6B6">UN """),_display_(/*81.107*/mapDiccionario/*81.121*/.get("ARR")),format.raw/*81.132*/("""</kbd></a>
				<a class="toggle-vis" href="#" data-column="11"><kbd id="kdb11" style="background-color: #73C6B6">P.U. """),_display_(/*82.109*/mapDiccionario/*82.123*/.get("Arriendo")),format.raw/*82.139*/("""</kbd></a>
				<a class="toggle-vis" href="#" data-column="12"><kbd id="kdb12" style="background-color: #73C6B6">UN</kbd></a>
				<a class="toggle-vis" href="#" data-column="13"><kbd id="kdb13" style="background-color: #73C6B6">CANT</kbd></a>
				<a class="toggle-vis" href="#" data-column="14"><kbd id="kdb14" style="background-color: #73C6B6">TOTAL Compra</kbd></a>
				<a class="toggle-vis" href="#" data-column="15"><kbd id="kdb15" style="background-color: #73C6B6">TOTAL Venta</kbd></a>
				<a class="toggle-vis" href="#" data-column="16"><kbd id="kdb16" style="background-color: #73C6B6">Compra ("""),_display_(/*87.112*/mapDiccionario/*87.126*/.get("PESOS")),format.raw/*87.139*/(""")</kbd></a>
				<a class="toggle-vis" href="#" data-column="17"><kbd id="kdb17" style="background-color: #73C6B6">Venta ("""),_display_(/*88.111*/mapDiccionario/*88.125*/.get("PESOS")),format.raw/*88.138*/(""")</kbd></a>
		</div>
		
		<div class="table-responsive">
			<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
		        <thead style="background-color: #eeeeee">
			        <TR> 
						       
						        <TH style= "text-align: center;vertical-align: top;">TIPO</TH>
								<TH style= "text-align: center;vertical-align: top;">SUCURSAL</TH>
						        <TH style= "text-align: center;vertical-align: top;">"""),_display_(/*98.69*/mapDiccionario/*98.83*/.get("BODEGA")),format.raw/*98.97*/("""/PROYECTO</TH>
								<TH style= "text-align: center;vertical-align: top;">PROPIEDAD</TH>
						        <TH style= "text-align: center;vertical-align: top;width:10%;">COD</TH>
								<TH style= "text-align: center;vertical-align: top;width:20%;">EQUIPO</TH>
								<TH style= "text-align: center;vertical-align: top;">MON</TH>
								<TH style= "text-align: center;vertical-align: top;">P.U.<br>Compra</TH>
								<TH style= "text-align: center;vertical-align: top;">MON</TH>
								<TH style= "text-align: center;vertical-align: top;">P.U.<br>Venta</TH>
								<TH style= "text-align: center;vertical-align: top;">UN<br>"""),_display_(/*106.69*/mapDiccionario/*106.83*/.get("ARR")),format.raw/*106.94*/("""</TH>
								<TH style= "text-align: center;vertical-align: top;">P.U.<br>"""),_display_(/*107.71*/mapDiccionario/*107.85*/.get("Arriendo")),format.raw/*107.101*/("""</TH>
								<TH style= "text-align: center;vertical-align: top;">UN</TH>
								<TH style= "text-align: center;vertical-align: top;">CANT</TH>
								<TH style= "text-align: center;vertical-align: top;">TOTAL<br>Compra</TH>
								<TH style= "text-align: center;vertical-align: top;">TOTAL<br>Venta</TH>
								<TH style= "text-align: center;vertical-align: top;">Compra<br>("""),_display_(/*112.74*/mapDiccionario/*112.88*/.get("PESOS")),format.raw/*112.101*/(""")</TH>
								<TH style= "text-align: center;vertical-align: top;">Venta<br>("""),_display_(/*113.73*/mapDiccionario/*113.87*/.get("PESOS")),format.raw/*113.100*/(""")</TH>
								<TH style= "text-align: center;vertical-align: top;width:4%">VER</TH>
					</TR>
				</thead>
				<tbody>
				"""),_display_(/*118.6*/for(lista1 <- lista) yield /*118.26*/{_display_(Seq[Any](format.raw/*118.27*/("""
				"""),format.raw/*119.5*/("""<TR>
						<td style="text-align:left;vertical-align:middle;">"""),_display_(/*120.59*/lista1/*120.65*/.get(1)),format.raw/*120.72*/("""</td>
						<td style="text-align:left;vertical-align:middle;">"""),_display_(/*121.59*/lista1/*121.65*/.get(23)),format.raw/*121.73*/("""</td>
						<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="modalContactoBodega('"""),_display_(/*122.101*/lista1/*122.107*/.get(18)),format.raw/*122.115*/("""');">"""),_display_(/*122.121*/lista1/*122.127*/.get(2)),format.raw/*122.134*/("""</a></td>
						<td style="text-align:left;vertical-align:middle;">"""),_display_(/*123.59*/lista1/*123.65*/.get(24)),format.raw/*123.73*/("""</td>
						<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="equipoDescripcion('"""),_display_(/*124.99*/lista1/*124.105*/.get(0)),format.raw/*124.112*/("""');">"""),_display_(/*124.118*/lista1/*124.124*/.get(4)),format.raw/*124.131*/("""</a></td>
						<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="equipoDescripcion('"""),_display_(/*125.99*/lista1/*125.105*/.get(0)),format.raw/*125.112*/("""');">"""),_display_(/*125.118*/lista1/*125.124*/.get(5)),format.raw/*125.131*/("""</a></td>
						<td style="text-align:center;vertical-align:middle;">"""),_display_(/*126.61*/lista1/*126.67*/.get(6)),format.raw/*126.74*/("""</td>
						<td style="text-align:right;vertical-align:middle;">"""),_display_(/*127.60*/lista1/*127.66*/.get(7)),format.raw/*127.73*/("""</td>
						<td style="text-align:center;vertical-align:middle;">"""),_display_(/*128.61*/lista1/*128.67*/.get(8)),format.raw/*128.74*/("""</td>
						<td style="text-align:right;vertical-align:middle;">"""),_display_(/*129.60*/lista1/*129.66*/.get(9)),format.raw/*129.73*/("""</td>
						<td style="text-align:right;vertical-align:middle;">"""),_display_(/*130.60*/lista1/*130.66*/.get(10)),format.raw/*130.74*/("""</td>
						<td style="text-align:right;vertical-align:middle;">"""),_display_(/*131.60*/lista1/*131.66*/.get(11)),format.raw/*131.74*/("""</td>
						<td style="text-align:center;vertical-align:middle;">"""),_display_(/*132.61*/lista1/*132.67*/.get(12)),format.raw/*132.75*/("""</td>
						<td style="text-align:right;vertical-align:middle;">"""),_display_(/*133.60*/lista1/*133.66*/.get(13)),format.raw/*133.74*/("""</td>
						<td style="text-align:right;vertical-align:middle;">"""),_display_(/*134.60*/lista1/*134.66*/.get(14)),format.raw/*134.74*/("""</td>
						<td style="text-align:right;vertical-align:middle;">"""),_display_(/*135.60*/lista1/*135.66*/.get(15)),format.raw/*135.74*/("""</td>
						<td style="text-align:right;vertical-align:middle;">"""),_display_(/*136.60*/lista1/*136.66*/.get(16)),format.raw/*136.74*/("""</td>
						<td style="text-align:right;vertical-align:middle;">"""),_display_(/*137.60*/lista1/*137.66*/.get(17)),format.raw/*137.74*/("""</td>
					
					<td style="text-align:center;vertical-align:middle;">
						<form id="form_"""),_display_(/*140.23*/lista1/*140.29*/.get(18)),format.raw/*140.37*/("""" class="formulario" id="formDescargaDocumento" method="post" action="/reportInventTrazaEquipoEnBod/">
							<input type="hidden" name="id_bodega" value=""""),_display_(/*141.54*/lista1/*141.60*/.get(18)),format.raw/*141.68*/("""">
							<input type="hidden" name="id_equipo" value=""""),_display_(/*142.54*/lista1/*142.60*/.get(0)),format.raw/*142.67*/("""">
							<input type="hidden" name="tipo" value=""""),_display_(/*143.49*/tipo),format.raw/*143.53*/("""">
							<a href="#" onclick="document.getElementById('form_"""),_display_(/*144.60*/lista1/*144.66*/.get(18)),format.raw/*144.74*/("""').submit()">
								<kbd style="background-color: #73C6B6">select</kbd>
							</a>
						</form>
					</td>
				</TR>
	 			""")))}),format.raw/*150.7*/("""
				"""),format.raw/*151.5*/("""</tbody>
			</table>
		</div>
		
		
		
		
		
	</div>

""")))}),format.raw/*161.2*/("""


"""),format.raw/*164.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*165.31*/("""{"""),format.raw/*165.32*/("""
         """),format.raw/*166.10*/("""sumarTotales();
		 $('#tablaPrincipal').DataTable("""),format.raw/*167.35*/("""{"""),format.raw/*167.36*/("""
		    	"""),format.raw/*168.8*/(""""fixedHeader": true,
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [[ 0, "asc" ],[ 2, "asc" ]],
		    	"language": """),format.raw/*173.20*/("""{"""),format.raw/*173.21*/("""
		        	"""),format.raw/*174.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*175.11*/("""}"""),format.raw/*175.12*/("""
		  """),format.raw/*176.5*/("""}"""),format.raw/*176.6*/(""" """),format.raw/*176.7*/(""");
		  
		  $('a.toggle-vis').on('click', function (e) """),format.raw/*178.48*/("""{"""),format.raw/*178.49*/("""
		        """),format.raw/*179.11*/("""e.preventDefault();
		        // Get the column API object
		        let col = $(this).attr('data-column');
		        var column = $('#tablaPrincipal').DataTable().column(col);
		        // Toggle the visibility
		        column.visible(!column.visible());
		        if(column.visible())"""),format.raw/*185.31*/("""{"""),format.raw/*185.32*/("""
					"""),format.raw/*186.6*/("""document.getElementById('kdb'+col).style.backgroundColor = "#73C6B6";
				"""),format.raw/*187.5*/("""}"""),format.raw/*187.6*/("""else"""),format.raw/*187.10*/("""{"""),format.raw/*187.11*/("""
					"""),format.raw/*188.6*/("""document.getElementById('kdb'+col).style.backgroundColor = "#D6DBDF";
				"""),format.raw/*189.5*/("""}"""),format.raw/*189.6*/("""
		  """),format.raw/*190.5*/("""}"""),format.raw/*190.6*/(""");
		    
		document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*193.2*/("""}"""),format.raw/*193.3*/(""");
	
	function sumarTotales() """),format.raw/*195.26*/("""{"""),format.raw/*195.27*/("""
		"""),format.raw/*196.3*/("""var tabla = document.getElementById("tablaPrincipal");
		var sumaCantidades = 0;
		var sumaTotalesC= 0;
		var sumaTotalesV= 0;
		var numero = new DecimalFormat("#,##0.00");
		for (var i = 1; i < tabla.rows.length; i++) """),format.raw/*201.47*/("""{"""),format.raw/*201.48*/(""" 
			"""),format.raw/*202.4*/("""var aux = tabla.rows[i].cells[13].textContent;
			sumaCantidades = parseFloat(sumaCantidades) + parseFloat(numero.formatBack(aux));
			aux = tabla.rows[i].cells[14].textContent;
			sumaTotalesC = parseFloat(sumaTotalesC) + parseFloat(numero.formatBack(aux));
			aux = tabla.rows[i].cells[15].textContent;
			sumaTotalesV = parseFloat(sumaTotalesV) + parseFloat(numero.formatBack(aux));
		"""),format.raw/*208.3*/("""}"""),format.raw/*208.4*/("""
		"""),format.raw/*209.3*/("""$("#totalCantidad").val(formatStandar(sumaCantidades,0));
		$("#totalPesosC").val(formatStandar(sumaTotalesC,0));
		$("#totalPesosV").val(formatStandar(sumaTotalesV,0));
	"""),format.raw/*212.2*/("""}"""),format.raw/*212.3*/("""
	
"""),format.raw/*214.1*/("""</script>




		
		
	
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
                  SOURCE: app/viewsMnuReportes/reportInventarioGeneralXEquipo.scala.html
                  HASH: 2266d3d271c18f5ec0abc2d3ad4af60afd1dba34
                  MATRIX: 1063->1|1313->158|1346->166|1362->174|1401->176|1430->180|1498->228|1528->233|1572->257|1601->260|1657->295|1688->299|1765->350|1951->514|1984->520|3118->1627|3141->1641|3175->1654|3204->1655|3508->1932|3531->1946|3565->1959|3594->1960|4201->2539|4225->2553|4261->2567|5012->3290|5036->3304|5069->3315|5335->3553|5359->3567|5392->3578|5539->3697|5563->3711|5601->3727|6232->4330|6256->4344|6291->4357|6441->4479|6465->4493|6500->4506|6996->4975|7019->4989|7054->5003|7711->5632|7735->5646|7768->5657|7872->5733|7896->5747|7935->5763|8345->6145|8369->6159|8405->6172|8512->6251|8536->6265|8572->6278|8725->6404|8762->6424|8802->6425|8835->6430|8926->6493|8942->6499|8971->6506|9063->6570|9079->6576|9109->6584|9244->6690|9261->6696|9292->6704|9327->6710|9344->6716|9374->6723|9470->6791|9486->6797|9516->6805|9648->6909|9665->6915|9695->6922|9730->6928|9747->6934|9777->6941|9913->7049|9930->7055|9960->7062|9995->7068|10012->7074|10042->7081|10140->7151|10156->7157|10185->7164|10278->7229|10294->7235|10323->7242|10417->7308|10433->7314|10462->7321|10555->7386|10571->7392|10600->7399|10693->7464|10709->7470|10739->7478|10832->7543|10848->7549|10878->7557|10972->7623|10988->7629|11018->7637|11111->7702|11127->7708|11157->7716|11250->7781|11266->7787|11296->7795|11389->7860|11405->7866|11435->7874|11528->7939|11544->7945|11574->7953|11667->8018|11683->8024|11713->8032|11834->8125|11850->8131|11880->8139|12064->8295|12080->8301|12110->8309|12194->8365|12210->8371|12239->8378|12318->8429|12344->8433|12434->8495|12450->8501|12480->8509|12638->8636|12671->8641|12757->8696|12788->8699|12879->8761|12909->8762|12948->8772|13027->8822|13057->8823|13093->8831|13274->8983|13304->8984|13345->8996|13452->9074|13482->9075|13515->9080|13544->9081|13573->9082|13657->9137|13687->9138|13727->9149|14043->9436|14073->9437|14107->9443|14209->9517|14238->9518|14271->9522|14301->9523|14335->9529|14437->9603|14466->9604|14499->9609|14528->9610|14634->9688|14663->9689|14722->9719|14752->9720|14783->9723|15031->9942|15061->9943|15094->9948|15510->10336|15539->10337|15570->10340|15769->10511|15798->10512|15829->10515
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|41->10|41->10|43->12|44->13|44->13|46->15|78->47|78->47|78->47|78->47|86->55|86->55|86->55|86->55|104->73|104->73|104->73|110->79|110->79|110->79|112->81|112->81|112->81|113->82|113->82|113->82|118->87|118->87|118->87|119->88|119->88|119->88|129->98|129->98|129->98|137->106|137->106|137->106|138->107|138->107|138->107|143->112|143->112|143->112|144->113|144->113|144->113|149->118|149->118|149->118|150->119|151->120|151->120|151->120|152->121|152->121|152->121|153->122|153->122|153->122|153->122|153->122|153->122|154->123|154->123|154->123|155->124|155->124|155->124|155->124|155->124|155->124|156->125|156->125|156->125|156->125|156->125|156->125|157->126|157->126|157->126|158->127|158->127|158->127|159->128|159->128|159->128|160->129|160->129|160->129|161->130|161->130|161->130|162->131|162->131|162->131|163->132|163->132|163->132|164->133|164->133|164->133|165->134|165->134|165->134|166->135|166->135|166->135|167->136|167->136|167->136|168->137|168->137|168->137|171->140|171->140|171->140|172->141|172->141|172->141|173->142|173->142|173->142|174->143|174->143|175->144|175->144|175->144|181->150|182->151|192->161|195->164|196->165|196->165|197->166|198->167|198->167|199->168|204->173|204->173|205->174|206->175|206->175|207->176|207->176|207->176|209->178|209->178|210->179|216->185|216->185|217->186|218->187|218->187|218->187|218->187|219->188|220->189|220->189|221->190|221->190|224->193|224->193|226->195|226->195|227->196|232->201|232->201|233->202|239->208|239->208|240->209|243->212|243->212|245->214
                  -- GENERATED --
              */
          