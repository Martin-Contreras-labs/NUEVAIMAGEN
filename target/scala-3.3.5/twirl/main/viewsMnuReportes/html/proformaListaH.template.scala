
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

object proformaListaH extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
lista: List[List[String]], desde: String, hasta: String ):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	"""),format.raw/*7.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*8.4*/barraTitulo(mapDiccionario, "LISTADO (Pre-Factura Simple)", "")),format.raw/*8.67*/("""

	
	
			"""),format.raw/*12.4*/("""<table class="table table-sm table-condensed table-fluid">
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
		
		<form id="excel" class="formulario" method="post" action="/proformaListaHExcel/">
			<input type="hidden" name="fechaDesde" value=""""),_display_(/*46.51*/desde),format.raw/*46.56*/("""">
			<input type="hidden" name="fechaHasta" value=""""),_display_(/*47.51*/hasta),format.raw/*47.56*/("""">
		</form>
			
		
		<div class="table-responsive">
			<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
		        <thead style="background-color: #eeeeee">
					<TR> 
						<TH>TIPO</TH>
				        <TH>NUMERO</TH>
						<TH>FECHA</TH>
						<TH>DESDE</TH>
						<TH>HASTA</TH>
						<TH>RUT</TH>
						<TH>CLIENTE</TH>
						<TH>SUCURSAL</TH>
						<TH>COMERCIAL</TH>
						<TH>RUBRO</TH>
						<TH>"""),_display_(/*65.12*/mapDiccionario/*65.26*/.get("BODEGA")),format.raw/*65.40*/("""/PROYECTO</TH>
						<TH>Total<br>NETO</TH>
						<TH>Total<br>IVA</TH>
						<TH>Total<br>TOTAL</TH>
						<TH>Excel<br>EP</TH>
						<TH>Prof<br>PDF</TH>
						"""),_display_(if(mapPermiso.get("proformaListadoEliminar")!=null)/*71.59*/ {_display_(Seq[Any](format.raw/*71.61*/("""
							"""),format.raw/*72.8*/("""<TH>DEL</TH>
						""")))} else {null} ),format.raw/*73.8*/("""
					"""),format.raw/*74.6*/("""</TR>
				</thead>
				<tbody>
					"""),_display_(/*77.7*/for(lista1 <- lista) yield /*77.27*/{_display_(Seq[Any](format.raw/*77.28*/("""
						"""),format.raw/*78.7*/("""<tr>
							<td style= "text-align: center;">"""),_display_(/*79.42*/lista1/*79.48*/.get(1)),format.raw/*79.55*/("""</td>
							<td style= "text-align: center;">"""),_display_(/*80.42*/lista1/*80.48*/.get(0)),format.raw/*80.55*/("""</td>
							<td style= "text-align: center; min-width:80px">
								<div style="display:none">"""),_display_(/*82.36*/utilities/*82.45*/.Fechas.AAMMDD(lista1.get(2))),format.raw/*82.74*/("""</div>
								"""),_display_(/*83.10*/lista1/*83.16*/.get(2)),format.raw/*83.23*/("""
							"""),format.raw/*84.8*/("""</td>
							<td style= "text-align: center; min-width:80px">
								<div style="display:none">"""),_display_(/*86.36*/utilities/*86.45*/.Fechas.AAMMDD(lista1.get(3))),format.raw/*86.74*/("""</div>
								"""),_display_(/*87.10*/lista1/*87.16*/.get(3)),format.raw/*87.23*/("""
							"""),format.raw/*88.8*/("""</td>
							<td style= "text-align: center; min-width:80px	">
								<div style="display:none">"""),_display_(/*90.36*/utilities/*90.45*/.Fechas.AAMMDD(lista1.get(4))),format.raw/*90.74*/("""</div>
								"""),_display_(/*91.10*/lista1/*91.16*/.get(4)),format.raw/*91.23*/("""
							"""),format.raw/*92.8*/("""</td>
							<td style= "text-align: center;" min-width:80px>"""),_display_(/*93.57*/lista1/*93.63*/.get(5)),format.raw/*93.70*/("""</td>
							<td style= "text-align: left;">"""),_display_(/*94.40*/lista1/*94.46*/.get(6)),format.raw/*94.53*/("""</td>
							<td style= "text-align: left;">"""),_display_(/*95.40*/lista1/*95.46*/.get(7)),format.raw/*95.53*/("""</td>
							<td style= "text-align: left;">"""),_display_(/*96.40*/lista1/*96.46*/.get(8)),format.raw/*96.53*/("""</td>
							<td style= "text-align: left;">"""),_display_(/*97.40*/lista1/*97.46*/.get(9)),format.raw/*97.53*/("""</td>
							<td style= "text-align: left;">"""),_display_(/*98.40*/lista1/*98.46*/.get(10)),format.raw/*98.54*/("""</td>
							<td style= "text-align: right;">"""),_display_(/*99.41*/lista1/*99.47*/.get(11)),format.raw/*99.55*/("""</td>
							<td style= "text-align: right;">"""),_display_(/*100.41*/lista1/*100.47*/.get(12)),format.raw/*100.55*/("""</td>
							<td style= "text-align: right;">"""),_display_(/*101.41*/lista1/*101.47*/.get(13)),format.raw/*101.55*/("""</td>

							<td style="text-align:center;">
								"""),_display_(if(lista1.get(14)!="0")/*104.33*/{_display_(Seq[Any](format.raw/*104.34*/("""
									"""),format.raw/*105.10*/("""<a href="#" onclick= "descargaDocumento('"""),_display_(/*105.52*/lista1/*105.58*/.get(14)),format.raw/*105.66*/("""')">
										<kbd style="background-color: #85C1E9">xls</kbd>
									</a>
								""")))}else/*108.14*/{_display_(Seq[Any](format.raw/*108.15*/("""
									"""),format.raw/*109.10*/("""--
								""")))}),format.raw/*110.10*/("""
							"""),format.raw/*111.8*/("""</td>
							<td style= "text-align: center;">
								"""),_display_(if(lista1.get(15)!="0")/*113.33*/{_display_(Seq[Any](format.raw/*113.34*/("""
									"""),format.raw/*114.10*/("""<a href="#" onclick="mostrarPDF('"""),_display_(/*114.44*/lista1/*114.50*/.get(15)),format.raw/*114.58*/("""')">
										<kbd style="background-color: #85C1E9">pdf</kbd>
									</a>
								""")))}else/*117.14*/{_display_(Seq[Any](format.raw/*117.15*/("""
									"""),format.raw/*118.10*/("""--
								""")))}),format.raw/*119.10*/("""
							"""),format.raw/*120.8*/("""</td>
							"""),_display_(if(mapPermiso.get("proformaListadoEliminar")!=null)/*121.60*/ {_display_(Seq[Any](format.raw/*121.62*/("""
								"""),format.raw/*122.9*/("""<td style="text-align:center;">
									<a href="#" onclick="eliminaProforma('"""),_display_(/*123.49*/lista1/*123.55*/.get(0)),format.raw/*123.62*/("""');">
										<kbd style="background-color: red">X</kbd>
									</a>
								</td>
							""")))} else {null} ),format.raw/*127.9*/("""
					"""),format.raw/*128.6*/("""</tr>
	 			""")))}),format.raw/*129.7*/("""
				"""),format.raw/*130.5*/("""</tbody>
			</table>
		</div>
	</div>

	<div id='muestraPDF' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>EP/PROFORMA (Pre-Factura Simple)</h5>
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
		<input type="hidden" name="fechaDesde" value=""""),_display_(/*158.50*/desde),format.raw/*158.55*/("""">
		<input type="hidden" name="fechaHasta" value=""""),_display_(/*159.50*/hasta),format.raw/*159.55*/("""">
	</form>

	<form id="form_eliminar" method="post" action="/proformaEliminaH/">
		<input type="hidden" id="form_id_proforma" name="id_proforma">
		<input type="hidden" name="fechaDesde" value=""""),_display_(/*164.50*/desde),format.raw/*164.55*/("""">
		<input type="hidden" name="fechaHasta" value=""""),_display_(/*165.50*/hasta),format.raw/*165.55*/("""">
	</form>


	
""")))}),format.raw/*170.2*/("""


"""),format.raw/*173.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*175.31*/("""{"""),format.raw/*175.32*/("""
		
		"""),format.raw/*177.3*/("""$('#tablaPrincipal').DataTable("""),format.raw/*177.34*/("""{"""),format.raw/*177.35*/("""
		    	"""),format.raw/*178.8*/(""""fixedHeader": true,
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [[ 2, "desc" ]],
		    	"language": """),format.raw/*183.20*/("""{"""),format.raw/*183.21*/("""
		        	"""),format.raw/*184.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*185.11*/("""}"""),format.raw/*185.12*/("""
		  """),format.raw/*186.5*/("""}"""),format.raw/*186.6*/(""" """),format.raw/*186.7*/(""");

			document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*189.2*/("""}"""),format.raw/*189.3*/(""");

	const descargaDocumento = (nombreDOC) => """),format.raw/*191.43*/("""{"""),format.raw/*191.44*/("""
		  """),format.raw/*192.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*194.2*/("""}"""),format.raw/*194.3*/("""
	
	"""),format.raw/*196.2*/("""const mostrarPDF = (nombrePDF) => """),format.raw/*196.36*/("""{"""),format.raw/*196.37*/("""
		  """),format.raw/*197.5*/("""document.getElementById('rutaPDF').innerHTML="<object data='/showPdf/"+nombrePDF+"' type='application/pdf' width='100%' height='720'></object>";
		  $('#muestraPDF').modal('show');
	"""),format.raw/*199.2*/("""}"""),format.raw/*199.3*/("""
	
	"""),format.raw/*201.2*/("""const eliminaProforma = (id_proforma) => """),format.raw/*201.43*/("""{"""),format.raw/*201.44*/("""
		"""),format.raw/*202.3*/("""alertify.confirm('Esta seguro de eliminar """),_display_(/*202.46*/mapDiccionario("PROFORMA")),format.raw/*202.72*/(""" """),format.raw/*202.73*/("""numero: '+id_proforma, function (e) """),format.raw/*202.109*/("""{"""),format.raw/*202.110*/("""
			"""),format.raw/*203.4*/("""if (e) """),format.raw/*203.11*/("""{"""),format.raw/*203.12*/("""
				"""),format.raw/*204.5*/("""$("#form_id_proforma").val(id_proforma);
				document.getElementById("form_eliminar").submit();
			"""),format.raw/*206.4*/("""}"""),format.raw/*206.5*/("""
		"""),format.raw/*207.3*/("""}"""),format.raw/*207.4*/(""");
	"""),format.raw/*208.2*/("""}"""),format.raw/*208.3*/("""

	
"""),format.raw/*211.1*/("""</script>




		
		
	
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
                  SOURCE: app/viewsMnuReportes/proformaListaH.scala.html
                  HASH: fdee3dded79a8238c91ffbfb9d2b963b47c08565
                  MATRIX: 1047->1|1295->156|1327->163|1343->171|1382->173|1411->177|1479->225|1507->227|1583->278|1666->341|1702->350|2899->1520|2925->1525|3005->1578|3031->1583|3517->2042|3540->2056|3575->2070|3816->2284|3856->2286|3891->2294|3954->2314|3987->2320|4050->2357|4086->2377|4125->2378|4159->2385|4232->2431|4247->2437|4275->2444|4349->2491|4364->2497|4392->2504|4516->2601|4534->2610|4584->2639|4627->2655|4642->2661|4670->2668|4705->2676|4829->2773|4847->2782|4897->2811|4940->2827|4955->2833|4983->2840|5018->2848|5143->2946|5161->2955|5211->2984|5254->3000|5269->3006|5297->3013|5332->3021|5421->3083|5436->3089|5464->3096|5536->3141|5551->3147|5579->3154|5651->3199|5666->3205|5694->3212|5766->3257|5781->3263|5809->3270|5881->3315|5896->3321|5924->3328|5996->3373|6011->3379|6040->3387|6113->3433|6128->3439|6157->3447|6231->3493|6247->3499|6277->3507|6351->3553|6367->3559|6397->3567|6503->3645|6543->3646|6582->3656|6652->3698|6668->3704|6698->3712|6809->3803|6849->3804|6888->3814|6932->3826|6968->3834|7075->3913|7115->3914|7154->3924|7216->3958|7232->3964|7262->3972|7373->4063|7413->4064|7452->4074|7496->4086|7532->4094|7625->4159|7666->4161|7703->4170|7811->4250|7827->4256|7856->4263|7995->4358|8029->4364|8072->4376|8105->4381|9187->5435|9214->5440|9294->5492|9321->5497|9545->5693|9572->5698|9652->5750|9679->5755|9727->5772|9758->5775|9850->5838|9880->5839|9914->5845|9974->5876|10004->5877|10040->5885|10209->6025|10239->6026|10280->6038|10387->6116|10417->6117|10450->6122|10479->6123|10508->6124|10609->6197|10638->6198|10713->6244|10743->6245|10776->6250|10908->6354|10937->6355|10969->6359|11032->6393|11062->6394|11095->6399|11305->6581|11334->6582|11366->6586|11436->6627|11466->6628|11497->6631|11568->6674|11616->6700|11646->6701|11712->6737|11743->6738|11775->6742|11811->6749|11841->6750|11874->6755|12001->6854|12030->6855|12061->6858|12090->6859|12122->6863|12151->6864|12183->6868
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|38->7|39->8|39->8|43->12|77->46|77->46|78->47|78->47|96->65|96->65|96->65|102->71|102->71|103->72|104->73|105->74|108->77|108->77|108->77|109->78|110->79|110->79|110->79|111->80|111->80|111->80|113->82|113->82|113->82|114->83|114->83|114->83|115->84|117->86|117->86|117->86|118->87|118->87|118->87|119->88|121->90|121->90|121->90|122->91|122->91|122->91|123->92|124->93|124->93|124->93|125->94|125->94|125->94|126->95|126->95|126->95|127->96|127->96|127->96|128->97|128->97|128->97|129->98|129->98|129->98|130->99|130->99|130->99|131->100|131->100|131->100|132->101|132->101|132->101|135->104|135->104|136->105|136->105|136->105|136->105|139->108|139->108|140->109|141->110|142->111|144->113|144->113|145->114|145->114|145->114|145->114|148->117|148->117|149->118|150->119|151->120|152->121|152->121|153->122|154->123|154->123|154->123|158->127|159->128|160->129|161->130|189->158|189->158|190->159|190->159|195->164|195->164|196->165|196->165|201->170|204->173|206->175|206->175|208->177|208->177|208->177|209->178|214->183|214->183|215->184|216->185|216->185|217->186|217->186|217->186|220->189|220->189|222->191|222->191|223->192|225->194|225->194|227->196|227->196|227->196|228->197|230->199|230->199|232->201|232->201|232->201|233->202|233->202|233->202|233->202|233->202|233->202|234->203|234->203|234->203|235->204|237->206|237->206|238->207|238->207|239->208|239->208|242->211
                  -- GENERATED --
              */
          