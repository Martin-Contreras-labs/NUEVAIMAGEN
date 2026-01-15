
package viewsMnuMantencion.html

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

object mantControlMantenciones extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listado: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""

"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),format.raw/*9.2*/("""<div id="enCarga" class="blocker" style="display: none;"><br><br><br><br><br><br><h1>En proceso.....<div id="msgEspera"></div></h1></div>
	
	<form id="excel" class="formulario" method="post" action="/routes3/mantControlMantencionesExcel/"></form>
	
	
	<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*15.4*/barraTitulo(mapDiccionario, "CONTROL DE MANTENCIONES", "")),format.raw/*15.62*/("""
		"""),format.raw/*16.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			
				<div align="center">
					<button type="button"  class="btn btn-sm btn-success" onclick="document.getElementById('excel').submit()">
						Exportar a Excel
					</button>
				</div>
				<br>
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>CODIGO<br>EQUIPO</TH>
							<TH>NOMBRE<br>EQUIPO</TH>
							<TH>UBICACION</TH>
							<TH></TH>
							<TH>ID_REPORT<br>OPER_CORR</TH>
							<TH style="min-width:80px;">FECHA<br>LECTURA</TH>
							<TH>OPER/CORR</TH>
							<TH>UNIDAD</TH>
							<TH>LECTURA<br>OPER_CORR</TH>
							<TH>ESTADO<br>EN OBRA</TH>
							<TH>COMENTARIO</TH>
							<TH></TH>
							<TH>ID_REPORT<br>PREVENTIVO</TH>
							<TH style="min-width:80px;">FECHA<br>MANT</TH>
							<TH>PLAN MANTENCION</TH>
							<TH>UNIDAD</TH>
							<TH>LECTURA<br>MANT</TH>
							<TH>FRECUENCIA</TH>
							<TH>PROXIMA<br>MANT</TH>
							<TH>FALTA</TH>
							<TH style="min-width:80px;">FECHA<br>ESTIMADA</TH>
							<TH>ESTADO<br>EN TALLER</TH>
							<TH>ESTADO<br>OPERACIONAL</TH>
							<TH>USO<br>ACUM</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*55.8*/for(lista1 <- listado) yield /*55.30*/{_display_(Seq[Any](format.raw/*55.31*/("""
							"""),format.raw/*56.8*/("""<TR>
								<td  style="text-align:left;">"""),_display_(/*57.40*/lista1/*57.46*/.get(2)),format.raw/*57.53*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*58.40*/lista1/*58.46*/.get(3)),format.raw/*58.53*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*59.40*/lista1/*59.46*/.get(4)),format.raw/*59.53*/("""</td>
								<td  style="background-color: #eeeeee"></td>
								<td  style="text-align:center;">
									"""),_display_(if(lista1.get(18).equals("0"))/*62.41*/{_display_(Seq[Any](format.raw/*62.42*/("""
										"""),format.raw/*63.11*/("""--
									""")))}else/*64.15*/{_display_(Seq[Any](format.raw/*64.16*/("""
										"""),format.raw/*65.11*/("""<a href="#" onclick="mostrarPDF('"""),_display_(/*65.45*/lista1/*65.51*/.get(18)),format.raw/*65.59*/("""')">
											<kbd style="background-color: #85C1E9">"""),_display_(/*66.52*/lista1/*66.58*/.get(1)),format.raw/*66.65*/("""</kbd>
										</a>
									""")))}),format.raw/*68.11*/("""
								"""),format.raw/*69.9*/("""</td>
								<td  style="text-align:center; min-width:80px;">
									<div style="display:none">"""),_display_(/*71.37*/utilities/*71.46*/.Fechas.AAMMDD(lista1.get(6))),format.raw/*71.75*/("""</div>
									"""),_display_(/*72.11*/utilities/*72.20*/.Fechas.DDMMAA(lista1.get(6))),format.raw/*72.49*/("""
								"""),format.raw/*73.9*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*74.42*/lista1/*74.48*/.get(23)),format.raw/*74.56*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*75.42*/lista1/*75.48*/.get(22)),format.raw/*75.56*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*76.41*/lista1/*76.47*/.get(5)),format.raw/*76.54*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*77.40*/lista1/*77.46*/.get(7)),format.raw/*77.53*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*78.40*/lista1/*78.46*/.get(8)),format.raw/*78.53*/("""</td>
								<td  style="background-color: #eeeeee"></td>
								<td  style="text-align:center;">
									"""),_display_(if(lista1.get(17).equals("0"))/*81.41*/{_display_(Seq[Any](format.raw/*81.42*/("""
										"""),format.raw/*82.11*/("""--
									""")))}else/*83.15*/{_display_(Seq[Any](format.raw/*83.16*/("""
										"""),format.raw/*84.11*/("""<a href="#" onclick="mostrarPDF('"""),_display_(/*84.45*/lista1/*84.51*/.get(17)),format.raw/*84.59*/("""')">
											<kbd style="background-color: #85C1E9">"""),_display_(/*85.52*/lista1/*85.58*/.get(0)),format.raw/*85.65*/("""</kbd>
										</a>
									""")))}),format.raw/*87.11*/("""
								"""),format.raw/*88.9*/("""</td>
								<td  style="text-align:center; min-width:80px;">
									<div style="display:none">"""),_display_(/*90.37*/utilities/*90.46*/.Fechas.AAMMDD(lista1.get(9))),format.raw/*90.75*/("""</div>
									"""),_display_(/*91.11*/utilities/*91.20*/.Fechas.DDMMAA(lista1.get(9))),format.raw/*91.49*/("""
								"""),format.raw/*92.9*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*93.41*/lista1/*93.47*/.get(21)),format.raw/*93.55*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*94.41*/lista1/*94.47*/.get(19)),format.raw/*94.55*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*95.41*/lista1/*95.47*/.get(10)),format.raw/*95.55*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*96.41*/lista1/*96.47*/.get(11)),format.raw/*96.55*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*97.41*/lista1/*97.47*/.get(12)),format.raw/*97.55*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*98.41*/lista1/*98.47*/.get(13)),format.raw/*98.55*/("""</td>
								<td  style="text-align:center; min-width:80px;">
									<div style="display:none">"""),_display_(/*100.37*/utilities/*100.46*/.Fechas.AAMMDD(lista1.get(14))),format.raw/*100.76*/("""</div>
									"""),_display_(/*101.11*/utilities/*101.20*/.Fechas.DDMMAA(lista1.get(14))),format.raw/*101.50*/("""
								"""),format.raw/*102.9*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*103.40*/lista1/*103.46*/.get(15)),format.raw/*103.54*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*104.40*/lista1/*104.46*/.get(16)),format.raw/*104.54*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*105.41*/lista1/*105.47*/.get(20)),format.raw/*105.55*/("""</td>
							</TR>
			 			""")))}),format.raw/*107.9*/("""
					"""),format.raw/*108.6*/("""</tbody>
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
				</div>
			</div>
		</div>
	</div>
	
	<div id='muestraPDF' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>REPORT</h5>
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
	
	
""")))}),format.raw/*143.2*/("""




"""),format.raw/*148.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*149.31*/("""{"""),format.raw/*149.32*/("""
		  """),format.raw/*150.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*150.36*/("""{"""),format.raw/*150.37*/("""
		    	"""),format.raw/*151.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
				"order": [[ 1, "desc" ],[ 0, "desc" ]],
		    	"language": """),format.raw/*154.20*/("""{"""),format.raw/*154.21*/("""
		        	"""),format.raw/*155.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*156.11*/("""}"""),format.raw/*156.12*/("""
		  """),format.raw/*157.5*/("""}"""),format.raw/*157.6*/(""" """),format.raw/*157.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*159.2*/("""}"""),format.raw/*159.3*/(""");
	
	const mostrarPDF = (nombrePDF) => """),format.raw/*161.36*/("""{"""),format.raw/*161.37*/("""
		  """),format.raw/*162.5*/("""document.getElementById('rutaPDF').innerHTML="<object data='/showPdf/"+nombrePDF+"' type='application/pdf' width='100%' height='720'></object>";
		  $('#muestraPDF').modal('show');
	"""),format.raw/*164.2*/("""}"""),format.raw/*164.3*/("""
	
	
	
"""),format.raw/*168.1*/("""</script>


		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listado:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listado)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listado) => apply(mapDiccionario,mapPermiso,userMnu,listado)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuMantencion/mantControlMantenciones.scala.html
                  HASH: a712b60816e94f4265c27bbbfb44e6df9ffc8db4
                  MATRIX: 1044->1|1263->127|1291->130|1307->138|1346->140|1375->144|1443->192|1473->196|1802->499|1881->557|1911->560|3243->1866|3281->1888|3320->1889|3355->1897|3426->1941|3441->1947|3469->1954|3541->1999|3556->2005|3584->2012|3656->2057|3671->2063|3699->2070|3866->2210|3905->2211|3944->2222|3980->2239|4019->2240|4058->2251|4119->2285|4134->2291|4163->2299|4246->2355|4261->2361|4289->2368|4352->2400|4388->2409|4514->2508|4532->2517|4582->2546|4626->2563|4644->2572|4694->2601|4730->2610|4804->2657|4819->2663|4848->2671|4922->2718|4937->2724|4966->2732|5039->2778|5054->2784|5082->2791|5154->2836|5169->2842|5197->2849|5269->2894|5284->2900|5312->2907|5479->3047|5518->3048|5557->3059|5593->3076|5632->3077|5671->3088|5732->3122|5747->3128|5776->3136|5859->3192|5874->3198|5902->3205|5965->3237|6001->3246|6127->3345|6145->3354|6195->3383|6239->3400|6257->3409|6307->3438|6343->3447|6416->3493|6431->3499|6460->3507|6533->3553|6548->3559|6577->3567|6650->3613|6665->3619|6694->3627|6767->3673|6782->3679|6811->3687|6884->3733|6899->3739|6928->3747|7001->3793|7016->3799|7045->3807|7172->3906|7191->3915|7243->3945|7288->3962|7307->3971|7359->4001|7396->4010|7469->4055|7485->4061|7515->4069|7588->4114|7604->4120|7634->4128|7708->4174|7724->4180|7754->4188|7812->4215|7846->4221|8998->5342|9031->5347|9122->5409|9152->5410|9185->5415|9245->5446|9275->5447|9311->5455|9498->5613|9528->5614|9569->5626|9676->5704|9706->5705|9739->5710|9768->5711|9797->5712|9898->5785|9927->5786|9996->5826|10026->5827|10059->5832|10269->6014|10298->6015|10333->6022
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|46->15|46->15|47->16|86->55|86->55|86->55|87->56|88->57|88->57|88->57|89->58|89->58|89->58|90->59|90->59|90->59|93->62|93->62|94->63|95->64|95->64|96->65|96->65|96->65|96->65|97->66|97->66|97->66|99->68|100->69|102->71|102->71|102->71|103->72|103->72|103->72|104->73|105->74|105->74|105->74|106->75|106->75|106->75|107->76|107->76|107->76|108->77|108->77|108->77|109->78|109->78|109->78|112->81|112->81|113->82|114->83|114->83|115->84|115->84|115->84|115->84|116->85|116->85|116->85|118->87|119->88|121->90|121->90|121->90|122->91|122->91|122->91|123->92|124->93|124->93|124->93|125->94|125->94|125->94|126->95|126->95|126->95|127->96|127->96|127->96|128->97|128->97|128->97|129->98|129->98|129->98|131->100|131->100|131->100|132->101|132->101|132->101|133->102|134->103|134->103|134->103|135->104|135->104|135->104|136->105|136->105|136->105|138->107|139->108|174->143|179->148|180->149|180->149|181->150|181->150|181->150|182->151|185->154|185->154|186->155|187->156|187->156|188->157|188->157|188->157|190->159|190->159|192->161|192->161|193->162|195->164|195->164|199->168
                  -- GENERATED --
              */
          