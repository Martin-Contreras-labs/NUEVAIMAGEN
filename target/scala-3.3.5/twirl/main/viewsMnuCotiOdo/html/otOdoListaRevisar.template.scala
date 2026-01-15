
package viewsMnuCotiOdo.html

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

object otOdoListaRevisar extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listOt: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalContactoBodega(mapDiccionario)),format.raw/*9.38*/("""
	"""),_display_(/*10.3*/modalContactoCliente()),format.raw/*10.25*/("""
	"""),_display_(/*11.3*/modalContactoProyecto()),format.raw/*11.26*/("""
	
	"""),format.raw/*13.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*14.4*/barraTitulo(mapDiccionario, "REVISAR "+mapDiccionario.get("Ordenes_de_trabajo").toUpperCase()+" ODO","(confirmadas y pendientes de confirmar)")),format.raw/*14.147*/("""
		"""),format.raw/*15.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>Nro."""),_display_(/*20.17*/mapDiccionario/*20.31*/.get("OT")),format.raw/*20.41*/("""</TH>
							<TH style="min-width:80px;">FECHA<br>"""),_display_(/*21.46*/mapDiccionario/*21.60*/.get("OT")),format.raw/*21.70*/("""</TH>
							<TH>Nro.COTI</TH>
							<TH style="min-width:80px;">FECHA<br>COTIZACION</TH>
							<TH>"""),_display_(/*24.13*/mapDiccionario/*24.27*/.get("BODEGA")),format.raw/*24.41*/("""/PROYECTO</TH>
							<TH>CLIENTE</TH>
							<TH>PROYECTO</TH>
							<TH>OBSERVACIONES<br>"""),_display_(/*27.30*/mapDiccionario/*27.44*/.get("OT")),format.raw/*27.54*/("""</TH>
							<TH>OBSERVACIONES<br>COTIZACION</TH>
							<TH>DOC<br>"""),_display_(/*29.20*/mapDiccionario/*29.34*/.get("OT")),format.raw/*29.44*/("""</TH>
							<TH>DOC<br>COTI</TH>
							<TH>ULTIMA<br>ACTUALIZACION</TH>
							<TH>FECHA<br>DE SERVICIO</TH>
							<TH>OPERADOR</TH>
							<TH>ESTADO</TH>
							<TH style="min-width:80px;">FECHA<br>CONFIRMADA</TH>
							<TH>SELECT</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*40.8*/for(lista1 <- listOt) yield /*40.29*/{_display_(Seq[Any](format.raw/*40.30*/("""
							"""),format.raw/*41.8*/("""<TR>
								<td style="text-align:center;">
									<a href="#" onclick="verOt('"""),_display_(/*43.39*/lista1/*43.45*/.get(0)),format.raw/*43.52*/("""')">
										<kbd style="background-color: rgb(90, 200, 245)">
											<font color="green">"""),_display_(/*45.33*/lista1/*45.39*/.get(2)),format.raw/*45.46*/("""</font>
										</kbd>
									</a>
								</td>
								<td style="min-width: 80px; text-align:center;"">
									<div style="display:none">"""),_display_(/*50.37*/lista1/*50.43*/.get(3)),format.raw/*50.50*/("""</div>
									"""),_display_(/*51.11*/utilities/*51.20*/.Fechas.DDMMAA(lista1.get(3))),format.raw/*51.49*/("""
								"""),format.raw/*52.9*/("""</td>
								<td style="text-align:center;">
									<a href="#" onclick="verCotizacion('"""),_display_(/*54.47*/lista1/*54.53*/.get(1)),format.raw/*54.60*/("""')">
										<kbd style="background-color: rgb(90, 200, 245)">
											<font color="green">"""),_display_(/*56.33*/lista1/*56.39*/.get(4)),format.raw/*56.46*/("""</font>
										</kbd>
									</a>
								</td>
								<td style="min-width: 80px; text-align:center;">
									<div style="display:none">"""),_display_(/*61.37*/lista1/*61.43*/.get(5)),format.raw/*61.50*/("""</div>
									"""),_display_(/*62.11*/utilities/*62.20*/.Fechas.DDMMAA(lista1.get(5))),format.raw/*62.49*/("""
								"""),format.raw/*63.9*/("""</td>
								<td style="text-align:left;"><a href="#" onclick="buscaBodega('"""),_display_(/*64.73*/lista1/*64.79*/.get(14)),format.raw/*64.87*/("""')">"""),_display_(/*64.92*/lista1/*64.98*/.get(14)),format.raw/*64.106*/("""</a></td>
								<td style="text-align:left;"><a href="#" onclick="buscaCliente('"""),_display_(/*65.74*/lista1/*65.80*/.get(6)),format.raw/*65.87*/("""')">"""),_display_(/*65.92*/lista1/*65.98*/.get(6)),format.raw/*65.105*/("""</a></td>
								<td style="text-align:left;"><a href="#" onclick="buscaProyecto('"""),_display_(/*66.75*/lista1/*66.81*/.get(7)),format.raw/*66.88*/("""')">"""),_display_(/*66.93*/lista1/*66.99*/.get(7)),format.raw/*66.106*/("""</a></td>
								<td style="text-align:left;">"""),_display_(/*67.39*/lista1/*67.45*/.get(8)),format.raw/*67.52*/("""</td>
								<td style="text-align:left;">"""),_display_(/*68.39*/lista1/*68.45*/.get(9)),format.raw/*68.52*/("""</td>
								<td style="text-align:center;">
									"""),_display_(if(lista1.get(10).equals("0"))/*70.41*/{_display_(Seq[Any](format.raw/*70.42*/("""
										"""),format.raw/*71.11*/("""--
									""")))}else/*72.15*/{_display_(Seq[Any](format.raw/*72.16*/("""
										"""),format.raw/*73.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*73.52*/lista1/*73.58*/.get(10)),format.raw/*73.66*/("""')">
											<kbd style="background-color: #7F8C8D">doc</kbd>
										</a>
									""")))}),format.raw/*76.11*/("""
								"""),format.raw/*77.9*/("""</td>
								<td style="text-align:center;">
									"""),_display_(if(lista1.get(11).equals("0"))/*79.41*/{_display_(Seq[Any](format.raw/*79.42*/("""
										"""),format.raw/*80.11*/("""--
									""")))}else/*81.15*/{_display_(Seq[Any](format.raw/*81.16*/("""
										"""),format.raw/*82.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*82.52*/lista1/*82.58*/.get(11)),format.raw/*82.66*/("""')">
											<kbd style="background-color: #7F8C8D">doc</kbd>
										</a>
									""")))}),format.raw/*85.11*/("""
								"""),format.raw/*86.9*/("""</td>
								<td style="min-width: 80px; text-align:center;">
									<div style="display: none;">"""),_display_(/*88.39*/lista1/*88.45*/.get(16)),format.raw/*88.53*/("""</div>
									<div id="fechaActual_"""),_display_(/*89.32*/lista1/*89.38*/.get(0)),format.raw/*89.45*/("""">"""),_display_(/*89.48*/utilities/*89.57*/.Fechas.DDMMAA(lista1.get(16))),format.raw/*89.87*/("""</div>
								</td>
								<td  style="min-width: 80px; text-align:center;">
									<div style="display: none;">"""),_display_(/*92.39*/lista1/*92.45*/.get(17)),format.raw/*92.53*/("""</div>
									<div id="fechaActual_"""),_display_(/*93.32*/lista1/*93.38*/.get(0)),format.raw/*93.45*/("""">"""),_display_(/*93.48*/utilities/*93.57*/.Fechas.DDMMAA(lista1.get(17))),format.raw/*93.87*/("""</div>
								</td>
								<td  style="min-width: 150px; text-align:center;">
									"""),_display_(/*96.11*/lista1/*96.17*/.get(18)),format.raw/*96.25*/("""
								"""),format.raw/*97.9*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*98.42*/lista1/*98.48*/.get(12)),format.raw/*98.56*/("""</td>
								<td style="min-width: 80px; text-align:center;">
									<div style="display:none">"""),_display_(/*100.37*/lista1/*100.43*/.get(13)),format.raw/*100.51*/("""</div>
									"""),_display_(/*101.11*/utilities/*101.20*/.Fechas.DDMMAA(lista1.get(13))),format.raw/*101.50*/("""
								"""),format.raw/*102.9*/("""</td>
								<td  style="text-align:center;">
									<a href="#" onclick= "revisarOt('"""),_display_(/*104.44*/lista1/*104.50*/.get(0)),format.raw/*104.57*/("""')">
										<kbd style="background-color: #73C6B6">select</kbd>
									</a>
								</td>
							</TR>
			 			""")))}),format.raw/*109.9*/("""
					"""),format.raw/*110.6*/("""</tbody>
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
	
	<div id='modalVerOt' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable' style="max-width: 80% !important;" role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>"""),_display_(/*127.31*/mapDiccionario/*127.45*/.get("ORDEN_DE_TRABAJO")),format.raw/*127.69*/("""</h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<div id='mostrarLaOt'></div>
	   				<div class='row'>
						<div class='col-sm-12' style='text-align:center'>
							<button type='button' class='btn btn-sm  btn-success' style='font-size: 10px;' data-dismiss='modal'>Listo</button>
						</div>
					</div>
				</div>
			</div>
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
	
	
	<form class="formulario" id="formDescargaDocumento" method="post" action="/downloadPDF/">	
		<input type="hidden" id="descargaDocumento" name="nombreArchivo">
	</form>
	
	<form id="revisarOt" method="post" action="/routes2/revisarOtOdo/">
		<input type="hidden" id="revisarOt_idOt" name="id_otOdo">
	</form>

""")))}),format.raw/*174.2*/("""


"""),format.raw/*177.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*178.31*/("""{"""),format.raw/*178.32*/("""
		  """),format.raw/*179.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*179.36*/("""{"""),format.raw/*179.37*/("""
		    	"""),format.raw/*180.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 1, "desc" ],[ 0, "desc" ]],
		    	"language": """),format.raw/*183.20*/("""{"""),format.raw/*183.21*/("""
		        	"""),format.raw/*184.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*185.11*/("""}"""),format.raw/*185.12*/("""
		  """),format.raw/*186.5*/("""}"""),format.raw/*186.6*/(""" """),format.raw/*186.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*188.2*/("""}"""),format.raw/*188.3*/(""");
	
	
	const verOt = (id_otOdo) => """),format.raw/*191.30*/("""{"""),format.raw/*191.31*/("""
		"""),format.raw/*192.3*/("""var formData = new FormData();
	  	formData.append('id_otOdo',id_otOdo);
        $.ajax("""),format.raw/*194.16*/("""{"""),format.raw/*194.17*/("""
            """),format.raw/*195.13*/("""url: "/routes2/verOtOdoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*202.36*/("""{"""),format.raw/*202.37*/("""
	     		"""),format.raw/*203.9*/("""document.getElementById('mostrarLaOt').innerHTML = respuesta;
	     		$('#modalVerOt').modal('show');
	     	"""),format.raw/*205.8*/("""}"""),format.raw/*205.9*/("""
        """),format.raw/*206.9*/("""}"""),format.raw/*206.10*/(""");
	"""),format.raw/*207.2*/("""}"""),format.raw/*207.3*/("""
	
	"""),format.raw/*209.2*/("""const verCotizacion = (id_cotiOdo) => """),format.raw/*209.40*/("""{"""),format.raw/*209.41*/("""
		"""),format.raw/*210.3*/("""var formData = new FormData();
	  	formData.append('id_cotiOdo',id_cotiOdo);
        $.ajax("""),format.raw/*212.16*/("""{"""),format.raw/*212.17*/("""
            """),format.raw/*213.13*/("""url: "/routes2/verCotiOdoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*220.36*/("""{"""),format.raw/*220.37*/("""
	     		"""),format.raw/*221.9*/("""document.getElementById('mostrarLaCotizacion').innerHTML = respuesta;
	     		$('#modalVerCotizacion').modal('show');
	     	"""),format.raw/*223.8*/("""}"""),format.raw/*223.9*/("""
        """),format.raw/*224.9*/("""}"""),format.raw/*224.10*/(""");
	"""),format.raw/*225.2*/("""}"""),format.raw/*225.3*/("""
	
	"""),format.raw/*227.2*/("""const descargaDocumento = (nombreDOC) => """),format.raw/*227.43*/("""{"""),format.raw/*227.44*/("""
		  """),format.raw/*228.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*230.2*/("""}"""),format.raw/*230.3*/("""
	
	"""),format.raw/*232.2*/("""const revisarOt = (id_otOdo) => """),format.raw/*232.34*/("""{"""),format.raw/*232.35*/("""
			"""),format.raw/*233.4*/("""$("#revisarOt_idOt").val(id_otOdo);
			document.getElementById("revisarOt").submit();
	"""),format.raw/*235.2*/("""}"""),format.raw/*235.3*/("""
		
		
	
	
	
"""),format.raw/*241.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listOt:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listOt)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listOt) => apply(mapDiccionario,mapPermiso,userMnu,listOt)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotiOdo/otOdoListaRevisar.scala.html
                  HASH: 96355ab1dc1e6133aef41edf0563fac7da756e74
                  MATRIX: 1035->1|1253->126|1286->134|1302->142|1341->144|1370->148|1438->196|1468->201|1523->236|1552->239|1595->261|1624->264|1668->287|1699->291|1776->342|1941->485|1971->488|2284->774|2307->788|2338->798|2416->849|2439->863|2470->873|2600->976|2623->990|2658->1004|2778->1097|2801->1111|2832->1121|2928->1190|2951->1204|2982->1214|3296->1502|3333->1523|3372->1524|3407->1532|3517->1615|3532->1621|3560->1628|3684->1725|3699->1731|3727->1738|3901->1885|3916->1891|3944->1898|3988->1915|4006->1924|4056->1953|4092->1962|4211->2054|4226->2060|4254->2067|4378->2164|4393->2170|4421->2177|4594->2323|4609->2329|4637->2336|4681->2353|4699->2362|4749->2391|4785->2400|4890->2478|4905->2484|4934->2492|4966->2497|4981->2503|5011->2511|5121->2594|5136->2600|5164->2607|5196->2612|5211->2618|5240->2625|5351->2709|5366->2715|5394->2722|5426->2727|5441->2733|5470->2740|5545->2788|5560->2794|5588->2801|5659->2845|5674->2851|5702->2858|5815->2944|5854->2945|5893->2956|5929->2973|5968->2974|6007->2985|6075->3026|6090->3032|6119->3040|6240->3130|6276->3139|6389->3225|6428->3226|6467->3237|6503->3254|6542->3255|6581->3266|6649->3307|6664->3313|6693->3321|6814->3411|6850->3420|6978->3521|6993->3527|7022->3535|7087->3573|7102->3579|7130->3586|7160->3589|7178->3598|7229->3628|7373->3745|7388->3751|7417->3759|7482->3797|7497->3803|7525->3810|7555->3813|7573->3822|7624->3852|7741->3942|7756->3948|7785->3956|7821->3965|7895->4012|7910->4018|7939->4026|8066->4125|8082->4131|8112->4139|8157->4156|8176->4165|8228->4195|8265->4204|8383->4294|8399->4300|8428->4307|8575->4423|8609->4429|9266->5058|9290->5072|9336->5096|11025->6754|11056->6757|11147->6819|11177->6820|11210->6825|11270->6856|11300->6857|11336->6865|11526->7026|11556->7027|11597->7039|11704->7117|11734->7118|11767->7123|11796->7124|11825->7125|11926->7198|11955->7199|12020->7235|12050->7236|12081->7239|12198->7327|12228->7328|12270->7341|12532->7574|12562->7575|12599->7584|12736->7693|12765->7694|12802->7703|12832->7704|12864->7708|12893->7709|12925->7713|12992->7751|13022->7752|13053->7755|13174->7847|13204->7848|13246->7861|13510->8096|13540->8097|13577->8106|13730->8231|13759->8232|13796->8241|13826->8242|13858->8246|13887->8247|13919->8251|13989->8292|14019->8293|14052->8298|14184->8402|14213->8403|14245->8407|14306->8439|14336->8440|14368->8444|14483->8531|14512->8532|14553->8545
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|41->10|41->10|42->11|42->11|44->13|45->14|45->14|46->15|51->20|51->20|51->20|52->21|52->21|52->21|55->24|55->24|55->24|58->27|58->27|58->27|60->29|60->29|60->29|71->40|71->40|71->40|72->41|74->43|74->43|74->43|76->45|76->45|76->45|81->50|81->50|81->50|82->51|82->51|82->51|83->52|85->54|85->54|85->54|87->56|87->56|87->56|92->61|92->61|92->61|93->62|93->62|93->62|94->63|95->64|95->64|95->64|95->64|95->64|95->64|96->65|96->65|96->65|96->65|96->65|96->65|97->66|97->66|97->66|97->66|97->66|97->66|98->67|98->67|98->67|99->68|99->68|99->68|101->70|101->70|102->71|103->72|103->72|104->73|104->73|104->73|104->73|107->76|108->77|110->79|110->79|111->80|112->81|112->81|113->82|113->82|113->82|113->82|116->85|117->86|119->88|119->88|119->88|120->89|120->89|120->89|120->89|120->89|120->89|123->92|123->92|123->92|124->93|124->93|124->93|124->93|124->93|124->93|127->96|127->96|127->96|128->97|129->98|129->98|129->98|131->100|131->100|131->100|132->101|132->101|132->101|133->102|135->104|135->104|135->104|140->109|141->110|158->127|158->127|158->127|205->174|208->177|209->178|209->178|210->179|210->179|210->179|211->180|214->183|214->183|215->184|216->185|216->185|217->186|217->186|217->186|219->188|219->188|222->191|222->191|223->192|225->194|225->194|226->195|233->202|233->202|234->203|236->205|236->205|237->206|237->206|238->207|238->207|240->209|240->209|240->209|241->210|243->212|243->212|244->213|251->220|251->220|252->221|254->223|254->223|255->224|255->224|256->225|256->225|258->227|258->227|258->227|259->228|261->230|261->230|263->232|263->232|263->232|264->233|266->235|266->235|272->241
                  -- GENERATED --
              */
          