
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

object mantHistorialReports1 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template10[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String,Long,Long,Long,Long,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listado: List[List[String]], desdeAAMMDD: String, hastaAAMMDD: String,
id_mantActorPersonal: Long, id_operMec: Long, id_tipoMantencion: Long, id_equipo: Long):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.1*/("""

"""),_display_(/*6.2*/main("")/*6.10*/ {_display_(Seq[Any](format.raw/*6.12*/("""

	"""),_display_(/*8.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*8.51*/("""
	
	"""),format.raw/*10.2*/("""<div id="enCarga" class="blocker" style="display: none;"><br><br><br><br><br><br><h1>En proceso.....<div id="msgEspera"></div></h1></div>
	
	<form id="excel" class="formulario" method="post" action="/routes3/mantHistorialReports1Excel/">
		<input type="hidden" name="fechaDesde" value=""""),_display_(/*13.50*/desdeAAMMDD),format.raw/*13.61*/("""">
		<input type="hidden" name="fechaHasta" value=""""),_display_(/*14.50*/hastaAAMMDD),format.raw/*14.61*/("""">
		<input type="hidden" name="id_mantActorPersonal" value=""""),_display_(/*15.60*/id_mantActorPersonal),format.raw/*15.80*/("""">
		<input type="hidden" name="id_operMec" value=""""),_display_(/*16.50*/id_operMec),format.raw/*16.60*/("""">
		<input type="hidden" name="id_tipoMantencion" value=""""),_display_(/*17.57*/id_tipoMantencion),format.raw/*17.74*/("""">
		<input type="hidden" name="id_equipo" value=""""),_display_(/*18.49*/id_equipo),format.raw/*18.58*/("""">
		
	</form>
	
	
	<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*24.4*/barraTitulo(mapDiccionario, "HISTORIAL DE REPORT INGRESADOS", "")),format.raw/*24.69*/("""
		"""),format.raw/*25.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			
				<div align="center">
					<button type="button"  class="btn btn-sm btn-success" onclick="document.getElementById('excel').submit()">
						Exportar a Excel
					</button>
				</div>
				
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>ID</TH>
							<TH style="min-width:80px;">FECHA</TH>
							<TH>USER_MADA</TH>
							<TH>PERFIL</TH>
							<TH>OPERADOR<br>MECANICO</TH>
							<TH>TIPO<br>MANTENCION</TH>
							<TH>CODIGO</TH>
							<TH>EQUIPO</TH>
							
							<TH>LECTURA<br>ANTERIOR</TH>
							<TH>LECTURA<br>ACTUAL</TH>
							<TH>USO</TH>
							<TH>UBICACION</TH>
							
							<TH>ESTADO<br>EN SITIO</TH>
							<TH>ESTADO<br>OPERACIONAL</TH>
							<TH>ESTADO<br>EN TALLER</TH>
							<TH>COMENTARIOS</TH>
							<TH>ESTADO FINAL</TH>
							<TH>OBSERVACIONES</TH>
							
							<TH>REPORT</TH>
							<TH>DETALLE</TH>
							<TH>ANEXO</TH>
							
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*65.8*/for(lista1 <- listado) yield /*65.30*/{_display_(Seq[Any](format.raw/*65.31*/("""
							"""),format.raw/*66.8*/("""<TR>
								<td  style="text-align:center;">"""),_display_(/*67.42*/lista1/*67.48*/.get(0)),format.raw/*67.55*/("""</td>
								<td  style="text-align:center; min-width:80px;">
									<div style="display:none">"""),_display_(/*69.37*/utilities/*69.46*/.Fechas.AAMMDD(lista1.get(1))),format.raw/*69.75*/("""</div>
									"""),_display_(/*70.11*/utilities/*70.20*/.Fechas.DDMMAA(lista1.get(1))),format.raw/*70.49*/("""
								"""),format.raw/*71.9*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*72.42*/lista1/*72.48*/.get(2)),format.raw/*72.55*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*73.40*/lista1/*73.46*/.get(3)),format.raw/*73.53*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*74.40*/lista1/*74.46*/.get(4)),format.raw/*74.53*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*75.40*/lista1/*75.46*/.get(5).toUpperCase()),format.raw/*75.67*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*76.40*/lista1/*76.46*/.get(6)),format.raw/*76.53*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*77.40*/lista1/*77.46*/.get(7)),format.raw/*77.53*/("""</td>
								
								<td  style="text-align:right;">"""),_display_(/*79.41*/lista1/*79.47*/.get(8)),format.raw/*79.54*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*80.41*/lista1/*80.47*/.get(9)),format.raw/*80.54*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*81.41*/lista1/*81.47*/.get(10)),format.raw/*81.55*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*82.40*/lista1/*82.46*/.get(11)),format.raw/*82.54*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*83.40*/lista1/*83.46*/.get(12)),format.raw/*83.54*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*84.40*/lista1/*84.46*/.get(13)),format.raw/*84.54*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*85.40*/lista1/*85.46*/.get(14)),format.raw/*85.54*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*86.40*/lista1/*86.46*/.get(15)),format.raw/*86.54*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*87.40*/lista1/*87.46*/.get(16)),format.raw/*87.54*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*88.40*/lista1/*88.46*/.get(17)),format.raw/*88.54*/("""</td>
								
								<td  style="text-align:center;">
									"""),_display_(if(lista1.get(18).equals("0"))/*91.41*/{_display_(Seq[Any](format.raw/*91.42*/("""
										"""),format.raw/*92.11*/("""--
									""")))}else/*93.15*/{_display_(Seq[Any](format.raw/*93.16*/("""
										"""),format.raw/*94.11*/("""<a href="#" onclick="mostrarPDF('"""),_display_(/*94.45*/lista1/*94.51*/.get(18)),format.raw/*94.59*/("""')">
											<kbd style="background-color: #85C1E9">pdf</kbd>
										</a>
									""")))}),format.raw/*97.11*/("""
								"""),format.raw/*98.9*/("""</td>
								<td style="text-align:center;">
									<form id="mantReportDetalle_"""),_display_(/*100.39*/lista1/*100.45*/.get(0)),format.raw/*100.52*/("""" action="/routes3/mantReportDetalle/" method="POST">
										<input type="hidden" name="id_mantTransacReport" value=""""),_display_(/*101.68*/lista1/*101.74*/.get(0)),format.raw/*101.81*/("""">
										<input type="hidden" name="desdeAAMMDD" value=""""),_display_(/*102.59*/desdeAAMMDD),format.raw/*102.70*/("""">
										<input type="hidden" name="hastaAAMMDD" value=""""),_display_(/*103.59*/hastaAAMMDD),format.raw/*103.70*/("""">
									</form>
									<a href="#" onclick='$("#mantReportDetalle_"""),_display_(/*105.54*/lista1/*105.60*/.get(0)),format.raw/*105.67*/("""").submit();'>
										<kbd style="background-color: #73C6B6">detalle</kbd>
									</a>
								</td>
								<td  style="text-align:center;">
									"""),_display_(if(lista1.get(19).equals("0"))/*110.41*/{_display_(Seq[Any](format.raw/*110.42*/("""
										"""),format.raw/*111.11*/("""---
									""")))}else/*112.15*/{_display_(Seq[Any](format.raw/*112.16*/("""
										"""),format.raw/*113.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*113.52*/lista1/*113.58*/.get(19)),format.raw/*113.66*/("""')">
											<kbd style="background-color: #7F8C8D">docum</kbd>
										</a>
									""")))}),format.raw/*116.11*/("""
								"""),format.raw/*117.9*/("""</td>
								
								
							</TR>
			 			""")))}),format.raw/*121.9*/("""
					"""),format.raw/*122.6*/("""</tbody>
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
	
	
	<form class="formulario" id="formDescargaDocumento" method="post" action="/downloadPDF/">	
		<input type="hidden" id="descargaDocumento" name="nombreArchivo">
	</form>
""")))}),format.raw/*160.2*/("""




"""),format.raw/*165.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*166.31*/("""{"""),format.raw/*166.32*/("""
		  """),format.raw/*167.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*167.36*/("""{"""),format.raw/*167.37*/("""
		    	"""),format.raw/*168.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
				"order": [[ 1, "desc" ],[ 0, "desc" ]],
		    	"language": """),format.raw/*171.20*/("""{"""),format.raw/*171.21*/("""
		        	"""),format.raw/*172.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*173.11*/("""}"""),format.raw/*173.12*/("""
		  """),format.raw/*174.5*/("""}"""),format.raw/*174.6*/(""" """),format.raw/*174.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*176.2*/("""}"""),format.raw/*176.3*/(""");
	
	const mostrarPDF = (nombrePDF) => """),format.raw/*178.36*/("""{"""),format.raw/*178.37*/("""
		  """),format.raw/*179.5*/("""document.getElementById('rutaPDF').innerHTML="<object data='/showPdf/"+nombrePDF+"' type='application/pdf' width='100%' height='720'></object>";
		  $('#muestraPDF').modal('show');
	"""),format.raw/*181.2*/("""}"""),format.raw/*181.3*/("""
	
	"""),format.raw/*183.2*/("""const descargaDocumento = (nombreDOC) => """),format.raw/*183.43*/("""{"""),format.raw/*183.44*/("""
		  """),format.raw/*184.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*186.2*/("""}"""),format.raw/*186.3*/("""
	
	
	
"""),format.raw/*190.1*/("""</script>


		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listado:List[List[String]],desdeAAMMDD:String,hastaAAMMDD:String,id_mantActorPersonal:Long,id_operMec:Long,id_tipoMantencion:Long,id_equipo:Long): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listado,desdeAAMMDD,hastaAAMMDD,id_mantActorPersonal,id_operMec,id_tipoMantencion,id_equipo)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String,Long,Long,Long,Long) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listado,desdeAAMMDD,hastaAAMMDD,id_mantActorPersonal,id_operMec,id_tipoMantencion,id_equipo) => apply(mapDiccionario,mapPermiso,userMnu,listado,desdeAAMMDD,hastaAAMMDD,id_mantActorPersonal,id_operMec,id_tipoMantencion,id_equipo)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuMantencion/mantHistorialReports1.scala.html
                  HASH: 1c9b7747d799ef6328251fd317447ff7145b3e71
                  MATRIX: 1077->1|1426->257|1454->260|1470->268|1509->270|1538->274|1606->322|1637->326|1951->613|1983->624|2062->676|2094->687|2183->749|2224->769|2303->821|2334->831|2420->890|2458->907|2536->958|2566->967|2663->1038|2749->1103|2779->1106|3961->2262|3999->2284|4038->2285|4073->2293|4146->2339|4161->2345|4189->2352|4315->2451|4333->2460|4383->2489|4427->2506|4445->2515|4495->2544|4531->2553|4605->2600|4620->2606|4648->2613|4720->2658|4735->2664|4763->2671|4835->2716|4850->2722|4878->2729|4950->2774|4965->2780|5007->2801|5079->2846|5094->2852|5122->2859|5194->2904|5209->2910|5237->2917|5319->2972|5334->2978|5362->2985|5435->3031|5450->3037|5478->3044|5551->3090|5566->3096|5595->3104|5667->3149|5682->3155|5711->3163|5783->3208|5798->3214|5827->3222|5899->3267|5914->3273|5943->3281|6015->3326|6030->3332|6059->3340|6131->3385|6146->3391|6175->3399|6247->3444|6262->3450|6291->3458|6363->3503|6378->3509|6407->3517|6530->3613|6569->3614|6608->3625|6644->3642|6683->3643|6722->3654|6783->3688|6798->3694|6827->3702|6948->3792|6984->3801|7096->3885|7112->3891|7141->3898|7290->4019|7306->4025|7335->4032|7424->4093|7457->4104|7546->4165|7579->4176|7680->4249|7696->4255|7725->4262|7940->4449|7980->4450|8020->4461|8058->4479|8098->4480|8138->4491|8207->4532|8223->4538|8253->4546|8377->4638|8414->4647|8490->4692|8524->4698|9845->5988|9878->5993|9969->6055|9999->6056|10032->6061|10092->6092|10122->6093|10158->6101|10345->6259|10375->6260|10416->6272|10523->6350|10553->6351|10586->6356|10615->6357|10644->6358|10745->6431|10774->6432|10843->6472|10873->6473|10906->6478|11116->6660|11145->6661|11177->6665|11247->6706|11277->6707|11310->6712|11442->6816|11471->6817|11506->6824
                  LINES: 28->1|35->4|37->6|37->6|37->6|39->8|39->8|41->10|44->13|44->13|45->14|45->14|46->15|46->15|47->16|47->16|48->17|48->17|49->18|49->18|55->24|55->24|56->25|96->65|96->65|96->65|97->66|98->67|98->67|98->67|100->69|100->69|100->69|101->70|101->70|101->70|102->71|103->72|103->72|103->72|104->73|104->73|104->73|105->74|105->74|105->74|106->75|106->75|106->75|107->76|107->76|107->76|108->77|108->77|108->77|110->79|110->79|110->79|111->80|111->80|111->80|112->81|112->81|112->81|113->82|113->82|113->82|114->83|114->83|114->83|115->84|115->84|115->84|116->85|116->85|116->85|117->86|117->86|117->86|118->87|118->87|118->87|119->88|119->88|119->88|122->91|122->91|123->92|124->93|124->93|125->94|125->94|125->94|125->94|128->97|129->98|131->100|131->100|131->100|132->101|132->101|132->101|133->102|133->102|134->103|134->103|136->105|136->105|136->105|141->110|141->110|142->111|143->112|143->112|144->113|144->113|144->113|144->113|147->116|148->117|152->121|153->122|191->160|196->165|197->166|197->166|198->167|198->167|198->167|199->168|202->171|202->171|203->172|204->173|204->173|205->174|205->174|205->174|207->176|207->176|209->178|209->178|210->179|212->181|212->181|214->183|214->183|214->183|215->184|217->186|217->186|221->190
                  -- GENERATED --
              */
          