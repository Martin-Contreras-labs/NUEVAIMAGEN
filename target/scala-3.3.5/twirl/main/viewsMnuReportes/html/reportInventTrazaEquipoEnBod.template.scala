
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

object reportInventTrazaEquipoEnBod extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,List[List[String]],String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
bodega: tables.BodegaEmpresa, lista: List[List[String]], tipo: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalEquipoDescripcion()),format.raw/*9.27*/("""
	
	"""),format.raw/*11.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*12.4*/barraTitulo(mapDiccionario, "TRAZABILIDAD DEL EQUIPO - "+tipo+" -- "+mapDiccionario.get("BODEGA")+"/PROYECTO : "+bodega.getNombre(),"PROYECTO: "+bodega.nickProyecto)),format.raw/*12.169*/("""
		
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
								onkeyup="doSearch2('tablaPrincipal');">
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
					    		<span class="input-group-text" id="basic-addon1">EXISTENCIA</span>
					  		</div>
					  		<input type="text" class="form-control right"
								id="totalCantidad"
								readonly>
						</div>
					</div>
						
					</td>
				</tr>
			</table>
		</div>
		
		<div>
			ALTERNAR COLUMNAS: 
				<a class="toggle-vis" href="#" data-column="0"><kbd id="kdb0" style="background-color: #73C6B6">NRO. MOV</kbd></a> 
				<a class="toggle-vis" href="#" data-column="1"><kbd id="kdb1" style="background-color: #73C6B6">NRO. REF</kbd></a> 
				<a class="toggle-vis" href="#" data-column="2"><kbd id="kdb2" style="background-color: #73C6B6">TIPO</kbd></a> 
				<a class="toggle-vis" href="#" data-column="3"><kbd id="kdb3" style="background-color: #73C6B6">FECHA</kbd></a> 
				<a class="toggle-vis" href="#" data-column="4"><kbd id="kdb4" style="background-color: #73C6B6">Nro.Coti</kbd></a> 
				<a class="toggle-vis" href="#" data-column="5"><kbd id="kdb5" style="background-color: #73C6B6">CODIGO</kbd></a>
				<a class="toggle-vis" href="#" data-column="6"><kbd id="kdb6" style="background-color: #73C6B6">EQUIPO</kbd></a>
				<a class="toggle-vis" href="#" data-column="7"><kbd id="kdb7" style="background-color: #73C6B6">UN</kbd></a> 
				<a class="toggle-vis" href="#" data-column="8"><kbd id="kdb8" style="background-color: #73C6B6">CANT</kbd></a> 
		</div>
		
		<div class="table-responsive">
			<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
		        <thead style="background-color: #eeeeee">
			        <TR> 
						       
						        <TH style= "text-align: center;vertical-align: top;">NRO. MOV</TH>
						        <TH style= "text-align: center;vertical-align: top;">NRO. REF</TH>
						        <TH style= "text-align: center;vertical-align: top;">TIPO</TH>
								<TH style= "text-align: center;vertical-align: top;min-width:80px;">FECHA</TH>
						        <TH style= "text-align: center;vertical-align: top;">Nro.Coti</TH>
								<TH style= "text-align: center;vertical-align: top;">CODIGO</TH>
								<TH style= "text-align: center;vertical-align: top;">EQUIPO</TH>
								<TH style= "text-align: center;vertical-align: top;">UN</TH>
								<TH style= "text-align: center;vertical-align: top;">CANT</TH>
					</TR>
				</thead>
				<tbody>
				"""),_display_(/*81.6*/for(lista1 <- lista) yield /*81.26*/{_display_(Seq[Any](format.raw/*81.27*/("""
				"""),format.raw/*82.5*/("""<TR>
					<td style= "text-align: center;">"""),_display_(/*83.40*/lista1/*83.46*/.get(0)),format.raw/*83.53*/("""</td>
					<td style= "text-align: center;">"""),_display_(/*84.40*/lista1/*84.46*/.get(9)),format.raw/*84.53*/("""</td>
					<td>"""),_display_(/*85.11*/lista1/*85.17*/.get(8)),format.raw/*85.24*/("""</td>
					<td style= "text-align: center;">
						<div style="display:none">"""),_display_(/*87.34*/utilities/*87.43*/.Fechas.AAMMDD(lista1.get(1))),format.raw/*87.72*/("""</div>
						"""),_display_(/*88.8*/lista1/*88.14*/.get(1)),format.raw/*88.21*/("""
					"""),format.raw/*89.6*/("""</td>
					<td style= "text-align: center;">
						"""),_display_(if(lista1.get(6).equals("0"))/*91.37*/{_display_(Seq[Any](format.raw/*91.38*/("""
								"""),format.raw/*92.9*/("""--
						""")))}else/*93.12*/{_display_(Seq[Any](format.raw/*93.13*/("""
							"""),format.raw/*94.8*/("""<a href="#" onclick="verCotizacion('"""),_display_(/*94.45*/lista1/*94.51*/.get(7)),format.raw/*94.58*/("""')">
								<kbd style="background-color: rgb(90, 200, 245)">
									<font color="green">"""),_display_(/*96.31*/lista1/*96.37*/.get(6)),format.raw/*96.44*/("""</font>
								</kbd>
							</a>

						""")))}),format.raw/*100.8*/("""
					"""),format.raw/*101.6*/("""</td>
					<td><a href="#" onclick="buscaEquipo('"""),_display_(/*102.45*/lista1/*102.51*/.get(2)),format.raw/*102.58*/("""');">"""),_display_(/*102.64*/lista1/*102.70*/.get(2)),format.raw/*102.77*/("""</a></td>
					<td><a href="#" onclick="buscaEquipo('"""),_display_(/*103.45*/lista1/*103.51*/.get(2)),format.raw/*103.58*/("""');">"""),_display_(/*103.64*/lista1/*103.70*/.get(3)),format.raw/*103.77*/("""</a></td>
					<td style= "text-align: center;">"""),_display_(/*104.40*/lista1/*104.46*/.get(4)),format.raw/*104.53*/("""</td>
					<td style= "text-align: right;">"""),_display_(/*105.39*/lista1/*105.45*/.get(5)),format.raw/*105.52*/("""</td>
				</TR>
	 			""")))}),format.raw/*107.7*/("""
				"""),format.raw/*108.5*/("""</tbody>
			</table>
		</div>
		
		
		
		
		
	</div>
	
	<div id='modalVerCotizacion' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable' style="max-width: 80% !important;" role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>COTIZACION</h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<div id='mostrarLaCotizacion'></div>
	   				<div class='row'>
						<div class='col-sm-12' style='text-align:center'>
							<button type='button' class='btn btn-sm  btn-success' style='font-size: 10px;' data-dismiss='modal'>Listo</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

""")))}),format.raw/*139.2*/("""


"""),format.raw/*142.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*143.31*/("""{"""),format.raw/*143.32*/("""
         """),format.raw/*144.10*/("""sumarTotales();
		 $('#tablaPrincipal').DataTable("""),format.raw/*145.35*/("""{"""),format.raw/*145.36*/("""
		    	"""),format.raw/*146.8*/(""""fixedHeader": true,
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [[ 3, "desc" ]],
		    	"language": """),format.raw/*151.20*/("""{"""),format.raw/*151.21*/("""
		        	"""),format.raw/*152.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*153.11*/("""}"""),format.raw/*153.12*/("""
		  """),format.raw/*154.5*/("""}"""),format.raw/*154.6*/(""" """),format.raw/*154.7*/(""");
		  
		  $('a.toggle-vis').on('click', function (e) """),format.raw/*156.48*/("""{"""),format.raw/*156.49*/("""
		        """),format.raw/*157.11*/("""e.preventDefault();
		        // Get the column API object
		        let col = $(this).attr('data-column');
		        var column = $('#tablaPrincipal').DataTable().column(col);
		        // Toggle the visibility
		        column.visible(!column.visible());
		        if(column.visible())"""),format.raw/*163.31*/("""{"""),format.raw/*163.32*/("""
					"""),format.raw/*164.6*/("""document.getElementById('kdb'+col).style.backgroundColor = "#73C6B6";
				"""),format.raw/*165.5*/("""}"""),format.raw/*165.6*/("""else"""),format.raw/*165.10*/("""{"""),format.raw/*165.11*/("""
					"""),format.raw/*166.6*/("""document.getElementById('kdb'+col).style.backgroundColor = "#D6DBDF";
				"""),format.raw/*167.5*/("""}"""),format.raw/*167.6*/("""
		  """),format.raw/*168.5*/("""}"""),format.raw/*168.6*/(""");
		    
		document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*171.2*/("""}"""),format.raw/*171.3*/(""");
	
	function sumarTotales() """),format.raw/*173.26*/("""{"""),format.raw/*173.27*/("""
		"""),format.raw/*174.3*/("""var tabla = document.getElementById("tablaPrincipal");
		var sumaTotales= 0;
		var numero = new DecimalFormat("#,##0.00");
		for (var i = 1; i < tabla.rows.length; i++) """),format.raw/*177.47*/("""{"""),format.raw/*177.48*/(""" 
			"""),format.raw/*178.4*/("""var aux = tabla.rows[i].cells[8].textContent;
			sumaTotales = parseFloat(sumaTotales) + parseFloat(numero.formatBack(aux));
		"""),format.raw/*180.3*/("""}"""),format.raw/*180.4*/("""
		"""),format.raw/*181.3*/("""$("#totalCantidad").val(formatStandar(sumaTotales,0));
	"""),format.raw/*182.2*/("""}"""),format.raw/*182.3*/("""
	
	"""),format.raw/*184.2*/("""const verCotizacion = (id_cotizacion) => """),format.raw/*184.43*/("""{"""),format.raw/*184.44*/("""
		"""),format.raw/*185.3*/("""var formData = new FormData();
	  	formData.append('id_cotizacion',id_cotizacion);
        $.ajax("""),format.raw/*187.16*/("""{"""),format.raw/*187.17*/("""
            """),format.raw/*188.13*/("""url: "/verCotizacionAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*195.36*/("""{"""),format.raw/*195.37*/("""
	     		"""),format.raw/*196.9*/("""document.getElementById('mostrarLaCotizacion').innerHTML = respuesta;
	     		$('#modalVerCotizacion').modal('show');
	     	"""),format.raw/*198.8*/("""}"""),format.raw/*198.9*/("""
        """),format.raw/*199.9*/("""}"""),format.raw/*199.10*/(""");
	"""),format.raw/*200.2*/("""}"""),format.raw/*200.3*/("""
	
	
"""),format.raw/*203.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,bodega:tables.BodegaEmpresa,lista:List[List[String]],tipo:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,bodega,lista,tipo)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,List[List[String]],String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,bodega,lista,tipo) => apply(mapDiccionario,mapPermiso,userMnu,bodega,lista,tipo)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reportInventTrazaEquipoEnBod.scala.html
                  HASH: 4f5945ce988a7535dd2d45977698d6c799601a30
                  MATRIX: 1075->1|1336->169|1369->177|1385->185|1424->187|1453->191|1521->239|1551->244|1595->268|1626->272|1703->323|1890->488|1923->494|5023->3568|5059->3588|5098->3589|5130->3594|5201->3638|5216->3644|5244->3651|5316->3696|5331->3702|5359->3709|5402->3725|5417->3731|5445->3738|5550->3816|5568->3825|5618->3854|5658->3868|5673->3874|5701->3881|5734->3887|5842->3968|5881->3969|5917->3978|5950->3992|5989->3993|6024->4001|6088->4038|6103->4044|6131->4051|6251->4144|6266->4150|6294->4157|6368->4200|6402->4206|6480->4256|6496->4262|6525->4269|6559->4275|6575->4281|6604->4288|6686->4342|6702->4348|6731->4355|6765->4361|6781->4367|6810->4374|6887->4423|6903->4429|6932->4436|7004->4480|7020->4486|7049->4493|7102->4515|7135->4520|8052->5406|8083->5409|8174->5471|8204->5472|8243->5482|8322->5532|8352->5533|8388->5541|8557->5681|8587->5682|8628->5694|8735->5772|8765->5773|8798->5778|8827->5779|8856->5780|8940->5835|8970->5836|9010->5847|9326->6134|9356->6135|9390->6141|9492->6215|9521->6216|9554->6220|9584->6221|9618->6227|9720->6301|9749->6302|9782->6307|9811->6308|9917->6386|9946->6387|10005->6417|10035->6418|10066->6421|10264->6590|10294->6591|10327->6596|10482->6723|10511->6724|10542->6727|10626->6783|10655->6784|10687->6788|10757->6829|10787->6830|10818->6833|10945->6931|10975->6932|11017->6945|11276->7175|11306->7176|11343->7185|11496->7310|11525->7311|11562->7320|11592->7321|11624->7325|11653->7326|11686->7331
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|42->11|43->12|43->12|45->14|112->81|112->81|112->81|113->82|114->83|114->83|114->83|115->84|115->84|115->84|116->85|116->85|116->85|118->87|118->87|118->87|119->88|119->88|119->88|120->89|122->91|122->91|123->92|124->93|124->93|125->94|125->94|125->94|125->94|127->96|127->96|127->96|131->100|132->101|133->102|133->102|133->102|133->102|133->102|133->102|134->103|134->103|134->103|134->103|134->103|134->103|135->104|135->104|135->104|136->105|136->105|136->105|138->107|139->108|170->139|173->142|174->143|174->143|175->144|176->145|176->145|177->146|182->151|182->151|183->152|184->153|184->153|185->154|185->154|185->154|187->156|187->156|188->157|194->163|194->163|195->164|196->165|196->165|196->165|196->165|197->166|198->167|198->167|199->168|199->168|202->171|202->171|204->173|204->173|205->174|208->177|208->177|209->178|211->180|211->180|212->181|213->182|213->182|215->184|215->184|215->184|216->185|218->187|218->187|219->188|226->195|226->195|227->196|229->198|229->198|230->199|230->199|231->200|231->200|234->203
                  -- GENERATED --
              */
          