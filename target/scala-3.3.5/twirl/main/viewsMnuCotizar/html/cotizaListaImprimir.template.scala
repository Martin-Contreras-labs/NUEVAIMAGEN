
package viewsMnuCotizar.html

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

object cotizaListaImprimir extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listCotizaciones: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalContactoCliente()),format.raw/*9.25*/("""
	"""),_display_(/*10.3*/modalContactoProyecto()),format.raw/*10.26*/("""
	
	"""),format.raw/*12.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*13.4*/barraTitulo(mapDiccionario, "LISTADO DE TODAS LAS COTIZACIONES","(pendientes y confirmadas)")),format.raw/*13.97*/("""
		"""),format.raw/*14.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>SUCURSAL</TH>
							<TH>COMERCIAL</TH>
							<TH>SOLUCION</TH>
							<TH>Nro.COTI</TH>
							<TH>SELECT</TH>
							<TH style="min-width:80px;">FECHA<br>COTIZACION</TH>
							<TH>CLIENTE</TH>
							<TH>PROYECTO</TH>
							<TH>OBSERVACIONES</TH>
							<TH>DOC<br>ADJ</TH>
							<TH>PDF<br>"""),_display_(/*29.20*/mapDiccionario/*29.34*/.get("ARR")),format.raw/*29.45*/("""</TH>
							<TH>PDF<br>VTA</TH>
							<TH>PDF<br>A+V</TH>
							<TH>ESTADO</TH>
							<TH style="min-width:80px;">FECHA<br>CONFIRMADA</TH>
							<TH>SELECT</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*38.8*/for(lista1 <- listCotizaciones) yield /*38.39*/{_display_(Seq[Any](format.raw/*38.40*/("""
							"""),format.raw/*39.8*/("""<TR>
								<td style="text-align:left;">"""),_display_(/*40.39*/lista1/*40.45*/.get(13)),format.raw/*40.53*/("""</td>
								<td style="text-align:left;">"""),_display_(/*41.39*/lista1/*41.45*/.get(14)),format.raw/*41.53*/("""</td>
								<td style="text-align:left;">"""),_display_(/*42.39*/lista1/*42.45*/.get(15)),format.raw/*42.53*/("""</td>
								<td style="text-align:center;">
									<a href="#" onclick="verCotizacion('"""),_display_(/*44.47*/lista1/*44.53*/.get(0)),format.raw/*44.60*/("""')">
											<kbd style="background-color: rgb(90, 200, 245)">
												<font color="green">"""),_display_(/*46.34*/lista1/*46.40*/.get(1)),format.raw/*46.47*/("""</font>
											</kbd>
									</a>
								</td>
								<td  style="text-align:center;">
									<a href="/cotizaImprimir/"""),_display_(/*51.36*/lista1/*51.42*/.get(0)),format.raw/*51.49*/("""">
										<kbd style="background-color: #73C6B6">select</kbd>
									</a>
								</td>
								<td style="text-align:center;">
									<div style="display:none">"""),_display_(/*56.37*/lista1/*56.43*/.get(2)),format.raw/*56.50*/("""</div>
									"""),_display_(/*57.11*/utilities/*57.20*/.Fechas.DDMMAA(lista1.get(2))),format.raw/*57.49*/("""
								"""),format.raw/*58.9*/("""</td>
								<td style="text-align:left;"><a href="#" onclick="buscaCliente('"""),_display_(/*59.74*/lista1/*59.80*/.get(3)),format.raw/*59.87*/("""')">"""),_display_(/*59.92*/lista1/*59.98*/.get(3)),format.raw/*59.105*/("""</a></td>
								<td style="text-align:left;"><a href="#" onclick="buscaProyecto('"""),_display_(/*60.75*/lista1/*60.81*/.get(6)),format.raw/*60.88*/("""')">"""),_display_(/*60.93*/lista1/*60.99*/.get(6)),format.raw/*60.106*/("""</a></td>
								
								"""),_display_(if(lista1.get(4).length()>61)/*62.39*/{_display_(Seq[Any](format.raw/*62.40*/("""
									"""),format.raw/*63.10*/("""<td style="text-align:left;">"""),_display_(/*63.40*/lista1/*63.46*/.get(4).substring(0,60)),format.raw/*63.69*/("""</td>
								""")))}else/*64.14*/{_display_(Seq[Any](format.raw/*64.15*/("""
									"""),format.raw/*65.10*/("""<td style="text-align:left;">"""),_display_(/*65.40*/lista1/*65.46*/.get(4)),format.raw/*65.53*/("""</td>
								""")))}),format.raw/*66.10*/("""
								
								
								"""),format.raw/*69.9*/("""<td style="text-align:center;">
									"""),_display_(if(lista1.get(5).equals("0"))/*70.40*/{_display_(Seq[Any](format.raw/*70.41*/("""
										"""),format.raw/*71.11*/("""--
									""")))}else/*72.15*/{_display_(Seq[Any](format.raw/*72.16*/("""
										"""),format.raw/*73.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*73.52*/lista1/*73.58*/.get(5)),format.raw/*73.65*/("""')">
											<kbd style="background-color: #7F8C8D">doc</kbd>
										</a>
									""")))}),format.raw/*76.11*/("""
								"""),format.raw/*77.9*/("""</td>
								<td style="text-align:center;">
									"""),_display_(if(lista1.get(10).equals("0"))/*79.41*/{_display_(Seq[Any](format.raw/*79.42*/("""
										"""),format.raw/*80.11*/("""--
									""")))}else/*81.15*/{_display_(Seq[Any](format.raw/*81.16*/("""
										"""),format.raw/*82.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*82.52*/lista1/*82.58*/.get(10)),format.raw/*82.66*/("""')">
											<kbd style="background-color: #7F8C8D">PDF</kbd>
										</a>
									""")))}),format.raw/*85.11*/("""
								"""),format.raw/*86.9*/("""</td>
								<td style="text-align:center;">
									"""),_display_(if(lista1.get(11).equals("0"))/*88.41*/{_display_(Seq[Any](format.raw/*88.42*/("""
										"""),format.raw/*89.11*/("""--
									""")))}else/*90.15*/{_display_(Seq[Any](format.raw/*90.16*/("""
										"""),format.raw/*91.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*91.52*/lista1/*91.58*/.get(11)),format.raw/*91.66*/("""')">
											<kbd style="background-color: #7F8C8D">PDF</kbd>
										</a>
									""")))}),format.raw/*94.11*/("""
								"""),format.raw/*95.9*/("""</td>
								<td style="text-align:center;">
									"""),_display_(if(lista1.get(12).equals("0"))/*97.41*/{_display_(Seq[Any](format.raw/*97.42*/("""
										"""),format.raw/*98.11*/("""--
									""")))}else/*99.15*/{_display_(Seq[Any](format.raw/*99.16*/("""
										"""),format.raw/*100.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*100.52*/lista1/*100.58*/.get(12)),format.raw/*100.66*/("""')">
											<kbd style="background-color: #7F8C8D">PDF</kbd>
										</a>
									""")))}),format.raw/*103.11*/("""
								"""),format.raw/*104.9*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*105.42*/lista1/*105.48*/.get(8)),format.raw/*105.55*/("""</td>
								<td style="text-align:center;">
									<div style="display:none">"""),_display_(/*107.37*/lista1/*107.43*/.get(9)),format.raw/*107.50*/("""</div>
									"""),_display_(/*108.11*/utilities/*108.20*/.Fechas.DDMMAA(lista1.get(9))),format.raw/*108.49*/("""
								"""),format.raw/*109.9*/("""</td>
								<td  style="text-align:center;">
									<a href="/cotizaImprimir/"""),_display_(/*111.36*/lista1/*111.42*/.get(0)),format.raw/*111.49*/("""">
										<kbd style="background-color: #73C6B6">select</kbd>
									</a>
								</td>
							</TR>
			 			""")))}),format.raw/*116.9*/("""
					"""),format.raw/*117.6*/("""</tbody>
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
	
	

""")))}),format.raw/*157.2*/("""


"""),format.raw/*160.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*161.31*/("""{"""),format.raw/*161.32*/("""
		  """),format.raw/*162.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*162.36*/("""{"""),format.raw/*162.37*/("""
		    	"""),format.raw/*163.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 5, "desc" ]],
		    	"language": """),format.raw/*166.20*/("""{"""),format.raw/*166.21*/("""
		        	"""),format.raw/*167.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*168.11*/("""}"""),format.raw/*168.12*/("""
		  """),format.raw/*169.5*/("""}"""),format.raw/*169.6*/(""" """),format.raw/*169.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*171.2*/("""}"""),format.raw/*171.3*/(""");
	
	
	const verCotizacion = (id_cotizacion) => """),format.raw/*174.43*/("""{"""),format.raw/*174.44*/("""
		"""),format.raw/*175.3*/("""var formData = new FormData();
	  	formData.append('id_cotizacion',id_cotizacion);
        $.ajax("""),format.raw/*177.16*/("""{"""),format.raw/*177.17*/("""
            """),format.raw/*178.13*/("""url: "/verCotizacionAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*185.36*/("""{"""),format.raw/*185.37*/("""
	     		"""),format.raw/*186.9*/("""document.getElementById('mostrarLaCotizacion').innerHTML = respuesta;
	     		$('#modalVerCotizacion').modal('show');
	     	"""),format.raw/*188.8*/("""}"""),format.raw/*188.9*/("""
        """),format.raw/*189.9*/("""}"""),format.raw/*189.10*/(""");
	"""),format.raw/*190.2*/("""}"""),format.raw/*190.3*/("""
	
	"""),format.raw/*192.2*/("""const descargaDocumento = (nombreDOC) => """),format.raw/*192.43*/("""{"""),format.raw/*192.44*/("""
		  """),format.raw/*193.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*195.2*/("""}"""),format.raw/*195.3*/("""
	
	
		
		
	
	
	
"""),format.raw/*203.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listCotizaciones:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listCotizaciones)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listCotizaciones) => apply(mapDiccionario,mapPermiso,userMnu,listCotizaciones)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizar/cotizaListaImprimir.scala.html
                  HASH: 8506fa19922e0975dbbc41f4182d5685016c254e
                  MATRIX: 1037->1|1265->136|1298->144|1314->152|1353->154|1382->158|1450->206|1480->211|1522->233|1551->236|1595->259|1626->263|1703->314|1817->407|1847->410|2453->989|2476->1003|2508->1014|2746->1226|2793->1257|2832->1258|2867->1266|2937->1309|2952->1315|2981->1323|3052->1367|3067->1373|3096->1381|3167->1425|3182->1431|3211->1439|3330->1531|3345->1537|3373->1544|3499->1643|3514->1649|3542->1656|3699->1786|3714->1792|3742->1799|3938->1968|3953->1974|3981->1981|4025->1998|4043->2007|4093->2036|4129->2045|4235->2124|4250->2130|4278->2137|4310->2142|4325->2148|4354->2155|4465->2239|4480->2245|4508->2252|4540->2257|4555->2263|4584->2270|4668->2327|4707->2328|4745->2338|4802->2368|4817->2374|4861->2397|4899->2416|4938->2417|4976->2427|5033->2457|5048->2463|5076->2470|5122->2485|5176->2512|5274->2583|5313->2584|5352->2595|5388->2612|5427->2613|5466->2624|5534->2665|5549->2671|5577->2678|5698->2768|5734->2777|5847->2863|5886->2864|5925->2875|5961->2892|6000->2893|6039->2904|6107->2945|6122->2951|6151->2959|6272->3049|6308->3058|6421->3144|6460->3145|6499->3156|6535->3173|6574->3174|6613->3185|6681->3226|6696->3232|6725->3240|6846->3330|6882->3339|6995->3425|7034->3426|7073->3437|7109->3454|7148->3455|7188->3466|7257->3507|7273->3513|7303->3521|7425->3611|7462->3620|7537->3667|7553->3673|7582->3680|7692->3762|7708->3768|7737->3775|7782->3792|7801->3801|7852->3830|7889->3839|7999->3921|8015->3927|8044->3934|8189->4048|8223->4054|9597->5397|9628->5400|9719->5462|9749->5463|9782->5468|9842->5499|9872->5500|9908->5508|10084->5655|10114->5656|10155->5668|10262->5746|10292->5747|10325->5752|10354->5753|10383->5754|10484->5827|10513->5828|10591->5877|10621->5878|10652->5881|10779->5979|10809->5980|10851->5993|11110->6223|11140->6224|11177->6233|11330->6358|11359->6359|11396->6368|11426->6369|11458->6373|11487->6374|11519->6378|11589->6419|11619->6420|11652->6425|11784->6529|11813->6530|11858->6547
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|41->10|41->10|43->12|44->13|44->13|45->14|60->29|60->29|60->29|69->38|69->38|69->38|70->39|71->40|71->40|71->40|72->41|72->41|72->41|73->42|73->42|73->42|75->44|75->44|75->44|77->46|77->46|77->46|82->51|82->51|82->51|87->56|87->56|87->56|88->57|88->57|88->57|89->58|90->59|90->59|90->59|90->59|90->59|90->59|91->60|91->60|91->60|91->60|91->60|91->60|93->62|93->62|94->63|94->63|94->63|94->63|95->64|95->64|96->65|96->65|96->65|96->65|97->66|100->69|101->70|101->70|102->71|103->72|103->72|104->73|104->73|104->73|104->73|107->76|108->77|110->79|110->79|111->80|112->81|112->81|113->82|113->82|113->82|113->82|116->85|117->86|119->88|119->88|120->89|121->90|121->90|122->91|122->91|122->91|122->91|125->94|126->95|128->97|128->97|129->98|130->99|130->99|131->100|131->100|131->100|131->100|134->103|135->104|136->105|136->105|136->105|138->107|138->107|138->107|139->108|139->108|139->108|140->109|142->111|142->111|142->111|147->116|148->117|188->157|191->160|192->161|192->161|193->162|193->162|193->162|194->163|197->166|197->166|198->167|199->168|199->168|200->169|200->169|200->169|202->171|202->171|205->174|205->174|206->175|208->177|208->177|209->178|216->185|216->185|217->186|219->188|219->188|220->189|220->189|221->190|221->190|223->192|223->192|223->192|224->193|226->195|226->195|234->203
                  -- GENERATED --
              */
          