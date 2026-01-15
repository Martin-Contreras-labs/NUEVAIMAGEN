
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

object proformaEstadoLista extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
lista: List[List[String]], desde: String, hasta: String ):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	"""),format.raw/*7.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*8.4*/barraTitulo(mapDiccionario, "LISTADO ESTADO EQUIPO COBRAR POR DAÑOS (AJUSTES)", "")),format.raw/*8.87*/("""

		"""),format.raw/*10.3*/("""<div class="noprint">
			<div class="row">
				<div class="col-1"></div>
			  	<div class="col-2">
						<button type="button"  id="btnEnvioMasivo" class="btn btn-sm btn-warning" 
							onclick="$('#modalGeneraUnPdf').modal('show');">
							Genera un PDF multiple
						</button>
			  	</div>
			</div>
		</div>
		
			
			<div id='modalGeneraUnPdf' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
				<div class='modal-dialog modal-dialog-scrollable' role='document' style="width:300px">
					<div class='modal-content'>
						<div class='modal-header'>
							GENERAR PDF UNICO<br>(debe ser máximo de 100 en 100)
						        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
						          <span aria-hidden='true'>&times;</span>
						        </button>
						</div>
						<div class='modal-body'>
							<form class="formulario" method="post" action="/routes3/proformaEstadoListaPdf/"  onsubmit="return validarForm();">
								<input type="hidden" name = "fechaDesde" value = """"),_display_(/*34.60*/desde),format.raw/*34.65*/("""">
								<input type="hidden" name = "fechaHasta" value = """"),_display_(/*35.60*/hasta),format.raw/*35.65*/("""">
								<table class="table table-sm table-bordered table-condensed table-fluid">
									<thead style="background-color: #eeeeee">
										<TR>
											<TH>DESDE NRO:</TH>
											<td>
												<input type="text" class="form-control center"
													id="nroIni"
													name="nroIni"
													onfocus="value = value.replace(/,/g,'')"
													onkeydown="return ingresoInt(window.event)"
													autocomplete="off"
													onchange="if(value=='') """),format.raw/*47.38*/("""{"""),format.raw/*47.39*/("""value='';"""),format.raw/*47.48*/("""}"""),format.raw/*47.49*/(""""
													required>
											</td>
										</TR>
										<TR>
											<TH>HASTA NRO:</TH>
											<td>
												<input type="text" class="form-control center"
													id="nroFin"
													name="nroFin"
													onfocus="value = value.replace(/,/g,'')"
													onkeydown="return ingresoInt(window.event)"
													autocomplete="off"
													onchange="if(value=='') """),format.raw/*60.38*/("""{"""),format.raw/*60.39*/("""value='';"""),format.raw/*60.48*/("""}"""),format.raw/*60.49*/(""""
													required>
											</td>
										</TR>
									</thead>
								</table>
								<br>
								<div class='row'>
									<div class='col-sm-12' style='text-align:center'>
										<input type="submit" id="btnSubmit" value='Generar PDF' class="btn btn-primary btn-sm rounded-pill btn-block">
										<br>
										<input type='button' class='btn btn-sm btn-warning' value='Cancelar' onclick='$("#btnSubmit").attr("disabled",false);' data-dismiss='modal'>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
	
	
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
							<button type="button"  class="btn btn-sm btn-success" onclick="document.getElementById('excel').submit()">
								Exportar a Excel
							</button>
							&nbsp;&nbsp;&nbsp;
							<button type="button" class="btn btn-sm btn-success" tabindex="-1" onclick="window.print(); return false;" >
								Imprimir
							</button>
							&nbsp;&nbsp;&nbsp;
							<button type="button"  class="btn btn-sm btn-success" 
								onclick="location.href = '/home/'">
								Volver
							</button>
							&nbsp;&nbsp;&nbsp;
						</div>
					</td>
				</tr>
			</table>
		</div>
		
		<form id="excel" class="formulario" method="post" action="/routes3/proformaEstadoListaExcel/">
			<input type="hidden" name="fechaDesde" value=""""),_display_(/*115.51*/desde),format.raw/*115.56*/("""">
			<input type="hidden" name="fechaHasta" value=""""),_display_(/*116.51*/hasta),format.raw/*116.56*/("""">
		</form>
			
		
		<div class="table-responsive">
			<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
		        <thead style="background-color: #eeeeee">
					<TR>
				        <TH>NUMERO</TH>
						<TH>FECHA</TH>
						<TH>DESDE</TH>
						<TH>HASTA</TH>
						<TH>SUCURSAL</TH>
						<TH>"""),_display_(/*129.12*/mapDiccionario/*129.26*/.get("BODEGA")),format.raw/*129.40*/("""/PROYECTO</TH>
						<TH>CLIENTE</TH>
						<TH>PROYECTO</TH>
						<TH>EXCEL</TH>
						<TH>PDF</TH>
						<TH>Total<br>NETO</TH>
						<TH>Total<br>IVA</TH>
						<TH>Total<br>TOTAL</TH>
						<TH>DEL</TH>
					</TR>
				</thead>
				<tbody>
					"""),_display_(/*141.7*/for(lista1 <- lista) yield /*141.27*/{_display_(Seq[Any](format.raw/*141.28*/("""
						"""),format.raw/*142.7*/("""<tr>
							<td style= "text-align: center;">"""),_display_(/*143.42*/lista1(0)),format.raw/*143.51*/("""</td>
							<td style= "text-align: center; min-width:80px">
								<div style="display:none">"""),_display_(/*145.36*/lista1(1)),format.raw/*145.45*/("""</div>
								"""),_display_(/*146.10*/utilities/*146.19*/.Fechas.DDMMAA(lista1(1))),format.raw/*146.44*/("""
							"""),format.raw/*147.8*/("""</td>
							<td style= "text-align: center; min-width:80px">
								<div style="display:none">"""),_display_(/*149.36*/lista1(2)),format.raw/*149.45*/("""</div>
								"""),_display_(/*150.10*/utilities/*150.19*/.Fechas.DDMMAA(lista1(2))),format.raw/*150.44*/("""
							"""),format.raw/*151.8*/("""</td>
							<td style= "text-align: center; min-width:80px	">
								<div style="display:none">"""),_display_(/*153.36*/lista1(3)),format.raw/*153.45*/("""</div>
								"""),_display_(/*154.10*/utilities/*154.19*/.Fechas.DDMMAA(lista1(3))),format.raw/*154.44*/("""
							"""),format.raw/*155.8*/("""</td>
							<td style= "text-align: left;">"""),_display_(/*156.40*/lista1(4)),format.raw/*156.49*/("""</td>
							<td style= "text-align: left;">"""),_display_(/*157.40*/lista1(5)),format.raw/*157.49*/("""</td>
							<td style= "text-align: left;">"""),_display_(/*158.40*/lista1(6)),format.raw/*158.49*/("""</td>
							<td style= "text-align: left;">"""),_display_(/*159.40*/lista1(7)),format.raw/*159.49*/("""</td>
							<td style="text-align:center;">
								"""),_display_(if(lista1(8)!="0")/*161.28*/{_display_(Seq[Any](format.raw/*161.29*/("""
									"""),format.raw/*162.10*/("""<a href="#" onclick= "descargaDocumento('"""),_display_(/*162.52*/lista1(8)),format.raw/*162.61*/("""')">
										<kbd style="background-color: #85C1E9">xls</kbd>
									</a>
								""")))}else/*165.14*/{_display_(Seq[Any](format.raw/*165.15*/("""
									"""),format.raw/*166.10*/("""--
								""")))}),format.raw/*167.10*/("""
							"""),format.raw/*168.8*/("""</td>
							<td style="text-align:center;">
								"""),_display_(if(lista1(9)!="0")/*170.28*/{_display_(Seq[Any](format.raw/*170.29*/("""
									"""),format.raw/*171.10*/("""<a href="#" onclick="mostrarPDF('"""),_display_(/*171.44*/lista1(9)),format.raw/*171.53*/("""')">
										<kbd style="background-color: #85C1E9">pdf</kbd>
									</a>
								""")))}else/*174.14*/{_display_(Seq[Any](format.raw/*174.15*/("""
									"""),format.raw/*175.10*/("""--
								""")))}),format.raw/*176.10*/("""
							"""),format.raw/*177.8*/("""</td>
							<td style= "text-align: right;">"""),_display_(/*178.41*/lista1(10)),format.raw/*178.51*/("""</td>
							<td style= "text-align: right;">"""),_display_(/*179.41*/lista1(11)),format.raw/*179.51*/("""</td>
							<td style= "text-align: right;">"""),_display_(/*180.41*/lista1(12)),format.raw/*180.51*/("""</td>
							<td style="text-align:center;">
								<a href="#" onclick="eliminaProforma('"""),_display_(/*182.48*/lista1(0)),format.raw/*182.57*/("""');">
									<kbd style="background-color: red">X</kbd>
								</a>
							</td>
						</tr>
	 				""")))}),format.raw/*187.8*/("""
				"""),format.raw/*188.5*/("""</tbody>
			</table>
		</div>
	</div>

	<div id='muestraPDF' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>EP/PROFORMA</h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<div id="rutaPDF"></div>
	   				<div class='row'>
						<div class='col-sm-12' style='text-align:center'>
							<button type='button' class='btn btn-sm  btn-success' style='font-size: 10px;' data-dismiss='modal'>Listo</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<form class="formulario" id="formDescargaDocumento" method="post" action="/downloadPDF/">	
		<input type="hidden" id="descargaDocumento" name="nombreArchivo">
		<input type="hidden" name="fechaDesde" value=""""),_display_(/*216.50*/desde),format.raw/*216.55*/("""">
		<input type="hidden" name="fechaHasta" value=""""),_display_(/*217.50*/hasta),format.raw/*217.55*/("""">
	</form>

	<form id="form_eliminar" method="post" action="/routes3/proformaEstadoElimina/">
		<input type="hidden" id="form_id_proformaEstado" name="id_proformaEstado">
		<input type="hidden" name="fechaDesde" value=""""),_display_(/*222.50*/desde),format.raw/*222.55*/("""">
		<input type="hidden" name="fechaHasta" value=""""),_display_(/*223.50*/hasta),format.raw/*223.55*/("""">
	</form>

""")))}),format.raw/*226.2*/("""


"""),format.raw/*229.1*/("""<script type="text/javascript">

	const validarForm = () =>"""),format.raw/*231.27*/("""{"""),format.raw/*231.28*/("""
		"""),format.raw/*232.3*/("""$("#btnSubmit").attr('disabled',true);
		let flag = true;
		var desde = document.getElementById('nroIni').value;
		var hasta = document.getElementById('nroFin').value;
		if(parseFloat(desde) > parseFloat(hasta))"""),format.raw/*236.44*/("""{"""),format.raw/*236.45*/("""
			"""),format.raw/*237.4*/("""flag = false;
			alertify.alert('EL NUMERO DESDE NO PUEDE SER MAYOR AL NUMERO HASTA', function () """),format.raw/*238.85*/("""{"""),format.raw/*238.86*/("""
				"""),format.raw/*239.5*/("""$("#btnSubmit").attr('disabled',false);
				return(flag);
     		"""),format.raw/*241.8*/("""}"""),format.raw/*241.9*/(""");
		"""),format.raw/*242.3*/("""}"""),format.raw/*242.4*/("""
		"""),format.raw/*243.3*/("""let aux =  parseFloat(hasta) - parseFloat(desde);
		if(aux > 100)"""),format.raw/*244.16*/("""{"""),format.raw/*244.17*/("""
			"""),format.raw/*245.4*/("""flag = false;
			alertify.alert('LA CANTIDAD DE SELECCIONADOS NO PUEDE SER MAYOR A 100', function () """),format.raw/*246.88*/("""{"""),format.raw/*246.89*/("""
				"""),format.raw/*247.5*/("""$("#btnSubmit").attr('disabled',false);
				return(flag);
     		"""),format.raw/*249.8*/("""}"""),format.raw/*249.9*/(""");
		"""),format.raw/*250.3*/("""}"""),format.raw/*250.4*/("""
		
		
		"""),format.raw/*253.3*/("""return(flag);
	"""),format.raw/*254.2*/("""}"""),format.raw/*254.3*/("""

	"""),format.raw/*256.2*/("""$(document).ready(function() """),format.raw/*256.31*/("""{"""),format.raw/*256.32*/("""
		
		"""),format.raw/*258.3*/("""$('#tablaPrincipal').DataTable("""),format.raw/*258.34*/("""{"""),format.raw/*258.35*/("""
		    	"""),format.raw/*259.8*/(""""fixedHeader": true,
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [[ 2, "desc" ],[ 1, "desc" ]],
		    	"language": """),format.raw/*264.20*/("""{"""),format.raw/*264.21*/("""
		        	"""),format.raw/*265.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*266.11*/("""}"""),format.raw/*266.12*/("""
		  """),format.raw/*267.5*/("""}"""),format.raw/*267.6*/(""" """),format.raw/*267.7*/(""");

			document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*270.2*/("""}"""),format.raw/*270.3*/(""");
	
	const descargaDocumento = (nombreDOC) => """),format.raw/*272.43*/("""{"""),format.raw/*272.44*/("""
		  """),format.raw/*273.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*275.2*/("""}"""),format.raw/*275.3*/("""
	
	"""),format.raw/*277.2*/("""const mostrarPDF = (nombrePDF) => """),format.raw/*277.36*/("""{"""),format.raw/*277.37*/("""
		  """),format.raw/*278.5*/("""document.getElementById('rutaPDF').innerHTML="<object data='/showPdf/"+nombrePDF+"' type='application/pdf' width='100%' height='720'></object>";
		  $('#muestraPDF').modal('show');
	"""),format.raw/*280.2*/("""}"""),format.raw/*280.3*/("""
	
	"""),format.raw/*282.2*/("""const eliminaProforma = (id_proformaEstado) => """),format.raw/*282.49*/("""{"""),format.raw/*282.50*/("""
		"""),format.raw/*283.3*/("""alertify.confirm('Esta seguro de eliminar este numero: '+id_proformaEstado, function (e) """),format.raw/*283.92*/("""{"""),format.raw/*283.93*/("""
			"""),format.raw/*284.4*/("""if (e) """),format.raw/*284.11*/("""{"""),format.raw/*284.12*/("""
				"""),format.raw/*285.5*/("""$("#form_id_proformaEstado").val(id_proformaEstado);
				document.getElementById("form_eliminar").submit();
			"""),format.raw/*287.4*/("""}"""),format.raw/*287.5*/("""
		"""),format.raw/*288.3*/("""}"""),format.raw/*288.4*/(""");
	"""),format.raw/*289.2*/("""}"""),format.raw/*289.3*/("""


	
"""),format.raw/*293.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,lista:List[List[String]],desde:String,hasta:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,lista,desde,hasta)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,lista,desde,hasta) => apply(mapDiccionario,mapPermiso,userMnu,lista,desde,hasta)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/proformaEstadoLista.scala.html
                  HASH: dd8de45bf5d479bccf2badaf09cfbda04efdb242
                  MATRIX: 1052->1|1300->156|1332->163|1348->171|1387->173|1416->177|1484->225|1512->227|1588->278|1691->361|1722->365|2788->1404|2814->1409|2903->1471|2929->1476|3446->1965|3475->1966|3512->1975|3541->1976|3980->2387|4009->2388|4046->2397|4075->2398|5863->4158|5890->4163|5971->4216|5998->4221|6376->4571|6400->4585|6436->4599|6713->4849|6750->4869|6790->4870|6825->4877|6899->4923|6930->4932|7055->5029|7086->5038|7130->5054|7149->5063|7196->5088|7232->5096|7357->5193|7388->5202|7432->5218|7451->5227|7498->5252|7534->5260|7660->5358|7691->5367|7735->5383|7754->5392|7801->5417|7837->5425|7910->5470|7941->5479|8014->5524|8045->5533|8118->5578|8149->5587|8222->5632|8253->5641|8353->5713|8393->5714|8432->5724|8502->5766|8533->5775|8644->5866|8684->5867|8723->5877|8767->5889|8803->5897|8903->5969|8943->5970|8982->5980|9044->6014|9075->6023|9186->6114|9226->6115|9265->6125|9309->6137|9345->6145|9419->6191|9451->6201|9525->6247|9557->6257|9631->6303|9663->6313|9783->6405|9814->6414|9948->6517|9981->6522|11042->7555|11069->7560|11149->7612|11176->7617|11425->7838|11452->7843|11532->7895|11559->7900|11604->7914|11635->7917|11723->7976|11753->7977|11784->7980|12024->8191|12054->8192|12086->8196|12213->8294|12243->8295|12276->8300|12369->8365|12398->8366|12431->8371|12460->8372|12491->8375|12585->8440|12615->8441|12647->8445|12777->8546|12807->8547|12840->8552|12933->8617|12962->8618|12995->8623|13024->8624|13061->8633|13104->8648|13133->8649|13164->8652|13222->8681|13252->8682|13286->8688|13346->8719|13376->8720|13412->8728|13595->8882|13625->8883|13666->8895|13773->8973|13803->8974|13836->8979|13865->8980|13894->8981|13995->9054|14024->9055|14100->9102|14130->9103|14163->9108|14295->9212|14324->9213|14356->9217|14419->9251|14449->9252|14482->9257|14692->9439|14721->9440|14753->9444|14829->9491|14859->9492|14890->9495|15008->9584|15038->9585|15070->9589|15106->9596|15136->9597|15169->9602|15308->9713|15337->9714|15368->9717|15397->9718|15429->9722|15458->9723|15491->9728
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|38->7|39->8|39->8|41->10|65->34|65->34|66->35|66->35|78->47|78->47|78->47|78->47|91->60|91->60|91->60|91->60|146->115|146->115|147->116|147->116|160->129|160->129|160->129|172->141|172->141|172->141|173->142|174->143|174->143|176->145|176->145|177->146|177->146|177->146|178->147|180->149|180->149|181->150|181->150|181->150|182->151|184->153|184->153|185->154|185->154|185->154|186->155|187->156|187->156|188->157|188->157|189->158|189->158|190->159|190->159|192->161|192->161|193->162|193->162|193->162|196->165|196->165|197->166|198->167|199->168|201->170|201->170|202->171|202->171|202->171|205->174|205->174|206->175|207->176|208->177|209->178|209->178|210->179|210->179|211->180|211->180|213->182|213->182|218->187|219->188|247->216|247->216|248->217|248->217|253->222|253->222|254->223|254->223|257->226|260->229|262->231|262->231|263->232|267->236|267->236|268->237|269->238|269->238|270->239|272->241|272->241|273->242|273->242|274->243|275->244|275->244|276->245|277->246|277->246|278->247|280->249|280->249|281->250|281->250|284->253|285->254|285->254|287->256|287->256|287->256|289->258|289->258|289->258|290->259|295->264|295->264|296->265|297->266|297->266|298->267|298->267|298->267|301->270|301->270|303->272|303->272|304->273|306->275|306->275|308->277|308->277|308->277|309->278|311->280|311->280|313->282|313->282|313->282|314->283|314->283|314->283|315->284|315->284|315->284|316->285|318->287|318->287|319->288|319->288|320->289|320->289|324->293
                  -- GENERATED --
              */
          