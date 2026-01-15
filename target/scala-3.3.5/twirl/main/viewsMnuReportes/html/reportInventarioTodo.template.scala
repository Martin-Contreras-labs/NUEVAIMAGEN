
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

object reportInventarioTodo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
lista: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalEquipoDescripcion()),format.raw/*9.27*/("""
	
	"""),format.raw/*11.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*12.4*/barraTitulo(mapDiccionario, "REPORTE INVENTARIO (CON PRECIOS DE COMPRA Y LISTA)","INCLUYE EQUIPOS CON y SIN STOCK")),format.raw/*12.119*/("""
		"""),format.raw/*13.3*/("""<div class="noprint">
			<table class="table table-sm table-condensed table-fluid">
				<tr>
					<td width="25%">
						<div class="input-group">
					  		<div class="input-group-prepend">
					    		<span class="input-group-text" id="basic-addon1">BUSCAR</span>
					  		</div>
					  		<input type="text" class="form-control left"
								id="searchTermtablaPrincipal"
								onkeyup="doSearch3('tablaPrincipal');">
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
					</td>
				</tr>
			</table>
		</div>
		
		<div>
			ALTERNAR COLUMNAS: 
				<a class="toggle-vis" href="#" data-column="0"><kbd id="kdb0" style="background-color: #73C6B6">Grupo/Familia</kbd></a> 
				<a class="toggle-vis" href="#" data-column="1"><kbd id="kdb1" style="background-color: #73C6B6">Codigo</kbd></a> 
				<a class="toggle-vis" href="#" data-column="2"><kbd id="kdb2" style="background-color: #73C6B6">Equipo</kbd></a> 
				<a class="toggle-vis" href="#" data-column="3"><kbd id="kdb3" style="background-color: #73C6B6">Peso (KG)</kbd></a> 
				<a class="toggle-vis" href="#" data-column="4"><kbd id="kdb4" style="background-color: #73C6B6">Superficie (M2)</kbd></a> 
				<a class="toggle-vis" href="#" data-column="5"><kbd id="kdb5" style="background-color: #73C6B6">Unidad</kbd></a>
				<a class="toggle-vis" href="#" data-column="6"><kbd id="kdb6" style="background-color: #73C6B6">Cantidad</kbd></a>
				<a class="toggle-vis" href="#" data-column="7"><kbd id="kdb7" style="background-color: #73C6B6">VER</kbd></a> 
				<a class="toggle-vis" href="#" data-column="8"><kbd id="kdb8" style="background-color: #73C6B6">Mon Compra</kbd></a> 
				<a class="toggle-vis" href="#" data-column="9"><kbd id="kdb9" style="background-color: #73C6B6">P.Compra</kbd></a> 
				<a class="toggle-vis" href="#" data-column="10"><kbd id="kdb10" style="background-color: #73C6B6">Mon Vta/"""),_display_(/*59.112*/mapDiccionario/*59.126*/.get("ARR")),format.raw/*59.137*/("""</kbd></a> 
				<a class="toggle-vis" href="#" data-column="11"><kbd id="kdb11" style="background-color: #73C6B6">P.Venta</kbd></a> 
				<a class="toggle-vis" href="#" data-column="12"><kbd id="kdb12" style="background-color: #73C6B6">U.Alquiler</kbd></a> 
				<a class="toggle-vis" href="#" data-column="13"><kbd id="kdb13" style="background-color: #73C6B6">P.Alquiler</kbd></a> 
		</div>
		
		<div class="table-responsive">
			<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
		        <thead style="background-color: #eeeeee">
					<TR> 
					        <TH colspan="5" style= "text-align: center;">EQUIPOS</TH>
							<TH colspan="5" style= "text-align: center;">COMPRAS</TH>
							<TH colspan="4" style= "text-align: center;">MAESTRO DE PRECIOS</TH>
					</TR>
					<TR> 
							<TH style= "text-align: center;">Grupo/Familia</TH>
							<TH style= "text-align: center;">Codigo</TH>
							<TH style= "text-align: center;">Equipo</TH>
							<TH style= "text-align: center;">Peso (KG)</TH>
							<TH style= "text-align: center;">Superficie (M2)</TH>
							<TH style= "text-align: center;">Unidad</TH>
							<TH style= "text-align: center;">Cantidad</TH>
							<TH style= "text-align: center;vertical-align: top;width:4%">VER</TH>
							<TH style= "text-align: center;">Moneda</TH>
							<TH style= "text-align: center;">P.Compra</TH>
							<TH style= "text-align: center;">Moneda</TH>
							<TH style= "text-align: center;">P.Venta</TH>
							<TH style= "text-align: center;">U.Alquiler</TH>
							<TH style= "text-align: center;">P.Alquiler</TH>
							
					</TR>
				</thead>
				<tbody>
				"""),_display_(/*92.6*/for(lista1 <- lista) yield /*92.26*/{_display_(Seq[Any](format.raw/*92.27*/("""
					"""),format.raw/*93.6*/("""<TR>
						<td style= "text-align: left;">"""),_display_(/*94.39*/lista1/*94.45*/.get(12)),format.raw/*94.53*/("""</td>
						<td style= "text-align: left;"><a href="#" onclick="equipoDescripcion('"""),_display_(/*95.79*/lista1/*95.85*/.get(0)),format.raw/*95.92*/("""');">"""),_display_(/*95.98*/lista1/*95.104*/.get(1)),format.raw/*95.111*/("""</a></td>
						<td style= "text-align: left;"><a href="#" onclick="equipoDescripcion('"""),_display_(/*96.79*/lista1/*96.85*/.get(0)),format.raw/*96.92*/("""');">"""),_display_(/*96.98*/lista1/*96.104*/.get(2)),format.raw/*96.111*/("""</a></td>
						<td style= "text-align: right;">"""),_display_(/*97.40*/lista1/*97.46*/.get(3)),format.raw/*97.53*/("""</td>
						<td style= "text-align: right;">"""),_display_(/*98.40*/lista1/*98.46*/.get(13)),format.raw/*98.54*/("""</td>
						<td style= "text-align: center;">"""),_display_(/*99.41*/lista1/*99.47*/.get(4)),format.raw/*99.54*/("""</td>
						<td style= "text-align: right;">"""),_display_(/*100.40*/lista1/*100.46*/.get(5)),format.raw/*100.53*/("""</td>
						<td title='SELECCIONAR' style="text-align:center;vertical-align:middle;">
							<form id="form_"""),_display_(/*102.24*/lista1/*102.30*/.get(0)),format.raw/*102.37*/("""" class="formulario" method="post" action="/reportInventarioGeneralXEquipo/">
								<input type="hidden" name="id_equipo" value=""""),_display_(/*103.55*/lista1/*103.61*/.get(0)),format.raw/*103.68*/("""">
								<input type="hidden" name="fechaCorte" value="3000-01-01">
								<input type="hidden" name="tipo" value="TODO">
								<a href="#" onclick="document.getElementById('form_"""),_display_(/*106.61*/lista1/*106.67*/.get(0)),format.raw/*106.74*/("""').submit()">
									<kbd style="background-color: #73C6B6">revisar</kbd>
								</a>
							</form>
						</td>
						<td style= "text-align: center;">"""),_display_(/*111.41*/lista1/*111.47*/.get(6)),format.raw/*111.54*/("""</td>
						<td style= "text-align: right;">"""),_display_(/*112.40*/lista1/*112.46*/.get(7)),format.raw/*112.53*/("""</td>
						<td style= "text-align: center;">"""),_display_(/*113.41*/lista1/*113.47*/.get(8)),format.raw/*113.54*/("""</td>
						<td style= "text-align: right;">"""),_display_(/*114.40*/lista1/*114.46*/.get(9)),format.raw/*114.53*/("""</td>
						<td style= "text-align: center;">"""),_display_(/*115.41*/lista1/*115.47*/.get(10)),format.raw/*115.55*/("""</td>
						<td style= "text-align: right;">"""),_display_(/*116.40*/lista1/*116.46*/.get(11)),format.raw/*116.54*/("""</td>
					</TR>
	 			""")))}),format.raw/*118.7*/("""
				"""),format.raw/*119.5*/("""</tbody>
			</table>
		</div>
	</div>
	
	<form id="excel" class="formulario" method="post" action="/reportInventarioTodoExcel/">
	</form>

""")))}),format.raw/*127.2*/("""


"""),format.raw/*130.1*/("""<script type="text/javascript">
	let flag = true;
	$(document).ready(function() """),format.raw/*132.31*/("""{"""),format.raw/*132.32*/("""

		 """),format.raw/*134.4*/("""$('#tablaPrincipal').DataTable("""),format.raw/*134.35*/("""{"""),format.raw/*134.36*/("""
		    	"""),format.raw/*135.8*/(""""fixedHeader": true,
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [[ 2, "asc" ]],
		    	"language": """),format.raw/*140.20*/("""{"""),format.raw/*140.21*/("""
		        	"""),format.raw/*141.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*142.11*/("""}"""),format.raw/*142.12*/("""
		  """),format.raw/*143.5*/("""}"""),format.raw/*143.6*/(""" """),format.raw/*143.7*/(""");
		  
		  $('a.toggle-vis').on('click', function (e) """),format.raw/*145.48*/("""{"""),format.raw/*145.49*/("""
		        """),format.raw/*146.11*/("""e.preventDefault();
		        // Get the column API object
		        let col = $(this).attr('data-column');
		        var column = $('#tablaPrincipal').DataTable().column(col);
		        // Toggle the visibility
		        column.visible(!column.visible());
		        if(column.visible())"""),format.raw/*152.31*/("""{"""),format.raw/*152.32*/("""
					"""),format.raw/*153.6*/("""document.getElementById('kdb'+col).style.backgroundColor = "#73C6B6";
				"""),format.raw/*154.5*/("""}"""),format.raw/*154.6*/("""else"""),format.raw/*154.10*/("""{"""),format.raw/*154.11*/("""
					"""),format.raw/*155.6*/("""document.getElementById('kdb'+col).style.backgroundColor = "#D6DBDF";
				"""),format.raw/*156.5*/("""}"""),format.raw/*156.6*/("""
		  """),format.raw/*157.5*/("""}"""),format.raw/*157.6*/(""");

			document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*160.2*/("""}"""),format.raw/*160.3*/(""");
	
	
	
</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,lista:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,lista)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,lista) => apply(mapDiccionario,mapPermiso,userMnu,lista)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reportInventarioTodo.scala.html
                  HASH: 049cc53b6298769711c5022520151a5f5f202657
                  MATRIX: 1039->1|1256->125|1289->133|1305->141|1344->143|1373->147|1441->195|1471->200|1515->224|1546->228|1623->279|1760->394|1790->397|4190->2769|4214->2783|4247->2794|5939->4460|5975->4480|6014->4481|6047->4487|6117->4530|6132->4536|6161->4544|6272->4628|6287->4634|6315->4641|6348->4647|6364->4653|6393->4660|6508->4748|6523->4754|6551->4761|6584->4767|6600->4773|6629->4780|6705->4829|6720->4835|6748->4842|6820->4887|6835->4893|6864->4901|6937->4947|6952->4953|6980->4960|7053->5005|7069->5011|7098->5018|7235->5127|7251->5133|7280->5140|7440->5272|7456->5278|7485->5285|7698->5470|7714->5476|7743->5483|7927->5639|7943->5645|7972->5652|8045->5697|8061->5703|8090->5710|8164->5756|8180->5762|8209->5769|8282->5814|8298->5820|8327->5827|8401->5873|8417->5879|8447->5887|8520->5932|8536->5938|8566->5946|8620->5969|8653->5974|8824->6114|8855->6117|8964->6197|8994->6198|9027->6203|9087->6234|9117->6235|9153->6243|9321->6382|9351->6383|9392->6395|9499->6473|9529->6474|9562->6479|9591->6480|9620->6481|9704->6536|9734->6537|9774->6548|10090->6835|10120->6836|10154->6842|10256->6916|10285->6917|10318->6921|10348->6922|10382->6928|10484->7002|10513->7003|10546->7008|10575->7009|10676->7082|10705->7083
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|42->11|43->12|43->12|44->13|90->59|90->59|90->59|123->92|123->92|123->92|124->93|125->94|125->94|125->94|126->95|126->95|126->95|126->95|126->95|126->95|127->96|127->96|127->96|127->96|127->96|127->96|128->97|128->97|128->97|129->98|129->98|129->98|130->99|130->99|130->99|131->100|131->100|131->100|133->102|133->102|133->102|134->103|134->103|134->103|137->106|137->106|137->106|142->111|142->111|142->111|143->112|143->112|143->112|144->113|144->113|144->113|145->114|145->114|145->114|146->115|146->115|146->115|147->116|147->116|147->116|149->118|150->119|158->127|161->130|163->132|163->132|165->134|165->134|165->134|166->135|171->140|171->140|172->141|173->142|173->142|174->143|174->143|174->143|176->145|176->145|177->146|183->152|183->152|184->153|185->154|185->154|185->154|185->154|186->155|187->156|187->156|188->157|188->157|191->160|191->160
                  -- GENERATED --
              */
          