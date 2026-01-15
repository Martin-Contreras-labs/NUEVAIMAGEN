
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

object otOdoListaEliminar extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

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
		"""),_display_(/*14.4*/barraTitulo(mapDiccionario, "LISTADO DE "+mapDiccionario.get("Ordenes_de_trabajo").toUpperCase()+" ODO (ELIMINAR)","(solo pendientes de confirmar ó confirmadas que no posean ventas asociadas)")),format.raw/*14.197*/("""
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
							<TH>DEL</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*35.8*/for(lista1 <- listOt) yield /*35.29*/{_display_(Seq[Any](format.raw/*35.30*/("""
							"""),format.raw/*36.8*/("""<TR>
								<td style="text-align:center;">
									<a href="#" onclick="verOt('"""),_display_(/*38.39*/lista1/*38.45*/.get(0)),format.raw/*38.52*/("""')">
										<kbd style="background-color: rgb(90, 200, 245)">
											<font color="green">"""),_display_(/*40.33*/lista1/*40.39*/.get(2)),format.raw/*40.46*/("""</font>
										</kbd>
									</a>
								</td>
								<td style="text-align:center;">
									<div style="display:none">"""),_display_(/*45.37*/lista1/*45.43*/.get(3)),format.raw/*45.50*/("""</div>
									"""),_display_(/*46.11*/utilities/*46.20*/.Fechas.DDMMAA(lista1.get(3))),format.raw/*46.49*/("""
								"""),format.raw/*47.9*/("""</td>
								<td style="text-align:center;">
									<a href="#" onclick="verCotizacion('"""),_display_(/*49.47*/lista1/*49.53*/.get(1)),format.raw/*49.60*/("""')">
										<kbd style="background-color: rgb(90, 200, 245)">
											<font color="green">"""),_display_(/*51.33*/lista1/*51.39*/.get(4)),format.raw/*51.46*/("""</font>
										</kbd>
									</a>
								</td>
								<td style="text-align:center;">
									<div style="display:none">"""),_display_(/*56.37*/lista1/*56.43*/.get(5)),format.raw/*56.50*/("""</div>
									"""),_display_(/*57.11*/utilities/*57.20*/.Fechas.DDMMAA(lista1.get(5))),format.raw/*57.49*/("""
								"""),format.raw/*58.9*/("""</td>
								<td style="text-align:left;"><a href="#" onclick="buscaBodega('"""),_display_(/*59.73*/lista1/*59.79*/.get(12)),format.raw/*59.87*/("""')">"""),_display_(/*59.92*/lista1/*59.98*/.get(12)),format.raw/*59.106*/("""</a></td>
								<td style="text-align:left;"><a href="#" onclick="buscaCliente('"""),_display_(/*60.74*/lista1/*60.80*/.get(6)),format.raw/*60.87*/("""')">"""),_display_(/*60.92*/lista1/*60.98*/.get(6)),format.raw/*60.105*/("""</a></td>
								<td style="text-align:left;"><a href="#" onclick="buscaProyecto('"""),_display_(/*61.75*/lista1/*61.81*/.get(7)),format.raw/*61.88*/("""')">"""),_display_(/*61.93*/lista1/*61.99*/.get(7)),format.raw/*61.106*/("""</a></td>
								<td style="text-align:left;">"""),_display_(/*62.39*/lista1/*62.45*/.get(8)),format.raw/*62.52*/("""</td>
								<td style="text-align:left;">"""),_display_(/*63.39*/lista1/*63.45*/.get(9)),format.raw/*63.52*/("""</td>
								<td style="text-align:center;">
									"""),_display_(if(lista1.get(10).equals("0"))/*65.41*/{_display_(Seq[Any](format.raw/*65.42*/("""
										"""),format.raw/*66.11*/("""--
									""")))}else/*67.15*/{_display_(Seq[Any](format.raw/*67.16*/("""
										"""),format.raw/*68.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*68.52*/lista1/*68.58*/.get(10)),format.raw/*68.66*/("""')">
											<kbd style="background-color: #7F8C8D">doc</kbd>
										</a>
									""")))}),format.raw/*71.11*/("""
								"""),format.raw/*72.9*/("""</td>
								<td style="text-align:center;">
									"""),_display_(if(lista1.get(11).equals("0"))/*74.41*/{_display_(Seq[Any](format.raw/*74.42*/("""
										"""),format.raw/*75.11*/("""--
									""")))}else/*76.15*/{_display_(Seq[Any](format.raw/*76.16*/("""
										"""),format.raw/*77.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*77.52*/lista1/*77.58*/.get(11)),format.raw/*77.66*/("""')">
											<kbd style="background-color: #7F8C8D">doc</kbd>
										</a>
									""")))}),format.raw/*80.11*/("""
								"""),format.raw/*81.9*/("""</td>
								<td  style="text-align:center;">
									<a href="#" onclick= "eliminarOt('"""),_display_(/*83.45*/lista1/*83.51*/.get(0)),format.raw/*83.58*/("""','"""),_display_(/*83.62*/lista1/*83.68*/.get(2)),format.raw/*83.75*/("""')">
										<kbd style="background-color: red">X</kbd>
									</a>
								</td>
							</TR>
			 			""")))}),format.raw/*88.9*/("""
					"""),format.raw/*89.6*/("""</tbody>
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
					<h5 class='modal-title'>"""),_display_(/*106.31*/mapDiccionario/*106.45*/.get("ORDEN_DE_TRABAJO")),format.raw/*106.69*/("""</h5>
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
	
	<form id="eliminarOt" method="post" action="/routes2/otOdoElimina/">
		<input type="hidden" id="del_idOt" name="id_otOdo">
	</form>

""")))}),format.raw/*153.2*/("""


"""),format.raw/*156.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*157.31*/("""{"""),format.raw/*157.32*/("""
		  """),format.raw/*158.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*158.36*/("""{"""),format.raw/*158.37*/("""
		    	"""),format.raw/*159.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 1, "desc" ],[ 0, "desc" ]],
		    	"language": """),format.raw/*162.20*/("""{"""),format.raw/*162.21*/("""
		        	"""),format.raw/*163.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*164.11*/("""}"""),format.raw/*164.12*/("""
		  """),format.raw/*165.5*/("""}"""),format.raw/*165.6*/(""" """),format.raw/*165.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*167.2*/("""}"""),format.raw/*167.3*/(""");
	
	
	const verOt = (id_otOdo) => """),format.raw/*170.30*/("""{"""),format.raw/*170.31*/("""
		"""),format.raw/*171.3*/("""var formData = new FormData();
	  	formData.append('id_otOdo',id_otOdo);
        $.ajax("""),format.raw/*173.16*/("""{"""),format.raw/*173.17*/("""
            """),format.raw/*174.13*/("""url: "/routes2/verOtOdoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*181.36*/("""{"""),format.raw/*181.37*/("""
	     		"""),format.raw/*182.9*/("""document.getElementById('mostrarLaOt').innerHTML = respuesta;
	     		$('#modalVerOt').modal('show');
	     	"""),format.raw/*184.8*/("""}"""),format.raw/*184.9*/("""
        """),format.raw/*185.9*/("""}"""),format.raw/*185.10*/(""");
	"""),format.raw/*186.2*/("""}"""),format.raw/*186.3*/("""
	
	"""),format.raw/*188.2*/("""const verCotizacion = (id_cotiOdo) => """),format.raw/*188.40*/("""{"""),format.raw/*188.41*/("""
		"""),format.raw/*189.3*/("""var formData = new FormData();
	  	formData.append('id_cotiOdo',id_cotiOdo);
        $.ajax("""),format.raw/*191.16*/("""{"""),format.raw/*191.17*/("""
            """),format.raw/*192.13*/("""url: "/routes2/verCotiOdoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*199.36*/("""{"""),format.raw/*199.37*/("""
	     		"""),format.raw/*200.9*/("""document.getElementById('mostrarLaCotizacion').innerHTML = respuesta;
	     		$('#modalVerCotizacion').modal('show');
	     	"""),format.raw/*202.8*/("""}"""),format.raw/*202.9*/("""
        """),format.raw/*203.9*/("""}"""),format.raw/*203.10*/(""");
	"""),format.raw/*204.2*/("""}"""),format.raw/*204.3*/("""
	
	"""),format.raw/*206.2*/("""const descargaDocumento = (nombreDOC) => """),format.raw/*206.43*/("""{"""),format.raw/*206.44*/("""
		  """),format.raw/*207.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*209.2*/("""}"""),format.raw/*209.3*/("""
	
	"""),format.raw/*211.2*/("""const eliminarOt = (id_otOdo, numero) => """),format.raw/*211.43*/("""{"""),format.raw/*211.44*/("""
		"""),format.raw/*212.3*/("""alertify.confirm('Esta seguro de eliminar """),_display_(/*212.46*/mapDiccionario/*212.60*/.get("Orden_de_trabajo")),format.raw/*212.84*/(""" """),format.raw/*212.85*/("""("""),_display_(/*212.87*/mapDiccionario/*212.101*/.get("OT")),format.raw/*212.111*/(""") nro: '+numero+".<br>Deberá confirmar nuevamente cotización", function (e) """),format.raw/*212.187*/("""{"""),format.raw/*212.188*/("""
			"""),format.raw/*213.4*/("""if (e) """),format.raw/*213.11*/("""{"""),format.raw/*213.12*/("""
				"""),format.raw/*214.5*/("""$("#del_idOt").val(id_otOdo);
				document.getElementById("eliminarOt").submit();
			"""),format.raw/*216.4*/("""}"""),format.raw/*216.5*/("""
		"""),format.raw/*217.3*/("""}"""),format.raw/*217.4*/(""");
	"""),format.raw/*218.2*/("""}"""),format.raw/*218.3*/("""
		
		
	
	
	
"""),format.raw/*224.1*/("""</script>




		
		
	
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
                  SOURCE: app/viewsMnuCotiOdo/otOdoListaEliminar.scala.html
                  HASH: 01ba70e2ccb903bf053a5435ef80080ef9aa1f59
                  MATRIX: 1036->1|1254->126|1287->134|1303->142|1342->144|1371->148|1439->196|1469->201|1524->236|1553->239|1596->261|1625->264|1669->287|1700->291|1777->342|1992->535|2022->538|2335->824|2358->838|2389->848|2467->899|2490->913|2521->923|2651->1026|2674->1040|2709->1054|2829->1147|2852->1161|2883->1171|2979->1240|3002->1254|3033->1264|3159->1364|3196->1385|3235->1386|3270->1394|3380->1477|3395->1483|3423->1490|3547->1587|3562->1593|3590->1600|3746->1729|3761->1735|3789->1742|3833->1759|3851->1768|3901->1797|3937->1806|4056->1898|4071->1904|4099->1911|4223->2008|4238->2014|4266->2021|4422->2150|4437->2156|4465->2163|4509->2180|4527->2189|4577->2218|4613->2227|4718->2305|4733->2311|4762->2319|4794->2324|4809->2330|4839->2338|4949->2421|4964->2427|4992->2434|5024->2439|5039->2445|5068->2452|5179->2536|5194->2542|5222->2549|5254->2554|5269->2560|5298->2567|5373->2615|5388->2621|5416->2628|5487->2672|5502->2678|5530->2685|5643->2771|5682->2772|5721->2783|5757->2800|5796->2801|5835->2812|5903->2853|5918->2859|5947->2867|6068->2957|6104->2966|6217->3052|6256->3053|6295->3064|6331->3081|6370->3082|6409->3093|6477->3134|6492->3140|6521->3148|6642->3238|6678->3247|6796->3338|6811->3344|6839->3351|6870->3355|6885->3361|6913->3368|7050->3475|7083->3481|7740->4110|7764->4124|7810->4148|9494->5801|9525->5804|9616->5866|9646->5867|9679->5872|9739->5903|9769->5904|9805->5912|9995->6073|10025->6074|10066->6086|10173->6164|10203->6165|10236->6170|10265->6171|10294->6172|10395->6245|10424->6246|10489->6282|10519->6283|10550->6286|10667->6374|10697->6375|10739->6388|11001->6621|11031->6622|11068->6631|11205->6740|11234->6741|11271->6750|11301->6751|11333->6755|11362->6756|11394->6760|11461->6798|11491->6799|11522->6802|11643->6894|11673->6895|11715->6908|11979->7143|12009->7144|12046->7153|12199->7278|12228->7279|12265->7288|12295->7289|12327->7293|12356->7294|12388->7298|12458->7339|12488->7340|12521->7345|12653->7449|12682->7450|12714->7454|12784->7495|12814->7496|12845->7499|12916->7542|12940->7556|12986->7580|13016->7581|13046->7583|13071->7597|13104->7607|13210->7683|13241->7684|13273->7688|13309->7695|13339->7696|13372->7701|13485->7786|13514->7787|13545->7790|13574->7791|13606->7795|13635->7796|13676->7809
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|41->10|41->10|42->11|42->11|44->13|45->14|45->14|46->15|51->20|51->20|51->20|52->21|52->21|52->21|55->24|55->24|55->24|58->27|58->27|58->27|60->29|60->29|60->29|66->35|66->35|66->35|67->36|69->38|69->38|69->38|71->40|71->40|71->40|76->45|76->45|76->45|77->46|77->46|77->46|78->47|80->49|80->49|80->49|82->51|82->51|82->51|87->56|87->56|87->56|88->57|88->57|88->57|89->58|90->59|90->59|90->59|90->59|90->59|90->59|91->60|91->60|91->60|91->60|91->60|91->60|92->61|92->61|92->61|92->61|92->61|92->61|93->62|93->62|93->62|94->63|94->63|94->63|96->65|96->65|97->66|98->67|98->67|99->68|99->68|99->68|99->68|102->71|103->72|105->74|105->74|106->75|107->76|107->76|108->77|108->77|108->77|108->77|111->80|112->81|114->83|114->83|114->83|114->83|114->83|114->83|119->88|120->89|137->106|137->106|137->106|184->153|187->156|188->157|188->157|189->158|189->158|189->158|190->159|193->162|193->162|194->163|195->164|195->164|196->165|196->165|196->165|198->167|198->167|201->170|201->170|202->171|204->173|204->173|205->174|212->181|212->181|213->182|215->184|215->184|216->185|216->185|217->186|217->186|219->188|219->188|219->188|220->189|222->191|222->191|223->192|230->199|230->199|231->200|233->202|233->202|234->203|234->203|235->204|235->204|237->206|237->206|237->206|238->207|240->209|240->209|242->211|242->211|242->211|243->212|243->212|243->212|243->212|243->212|243->212|243->212|243->212|243->212|243->212|244->213|244->213|244->213|245->214|247->216|247->216|248->217|248->217|249->218|249->218|255->224
                  -- GENERATED --
              */
          