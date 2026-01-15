
package viewsMnuOdo.html

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

object proformaListaOdo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
lista: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	"""),format.raw/*7.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*8.4*/barraTitulo(mapDiccionario, "LISTADO DE EP/PROFORMA (FACTURAS)", "")),format.raw/*8.72*/("""

		"""),format.raw/*10.3*/("""<div class="noprint">
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
							<button type="button" class="btn btn-sm btn-success" tabindex="-1" onclick="window.print(); return false;" >
								Imprimir
							</button>
							&nbsp;&nbsp;
							<button type="button"  class="btn btn-sm btn-success" 
								onclick="location.href = '/home/'">
								Listo
							</button>
						</div>
					</td>
				</tr>
			</table>
		</div>
		
		<div class="table-responsive">
			<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
		        <thead style="background-color: #eeeeee">
					<TR> 
				        <TH>NUMERO</TH>
						<TH>FECHA</TH>
						<TH>DESDE</TH>
						<TH>HASTA</TH>
						<TH>RUT</TH>
						<TH>CLIENTE</TH>
						<TH>SUCURSAL</TH>
						<TH>"""),_display_(/*50.12*/mapDiccionario/*50.26*/.get("BODEGA")),format.raw/*50.40*/("""/PROYECTO</TH>
						<TH>Total<br>NETO</TH>
						<TH>Total<br>IVA</TH>
						<TH>Total<br>TOTAL</TH>
						<TH>Prof<br>PDF</TH>
						<TH>Excel<br>EP</TH>
						"""),_display_(if(mapPermiso.get("odoProformaListaEliminar")!=null)/*56.60*/ {_display_(Seq[Any](format.raw/*56.62*/("""
							"""),format.raw/*57.8*/("""<TH>DEL</TH>
						""")))} else {null} ),format.raw/*58.8*/("""
					"""),format.raw/*59.6*/("""</TR>
				</thead>
				<tbody>
					"""),_display_(/*62.7*/for(lista1 <- lista) yield /*62.27*/{_display_(Seq[Any](format.raw/*62.28*/("""
						"""),format.raw/*63.7*/("""<tr>
							<td style= "text-align: center;">"""),_display_(/*64.42*/lista1/*64.48*/.get(0)),format.raw/*64.55*/("""</td>
							<td style= "text-align: center;">
								<div style="display:none">"""),_display_(/*66.36*/utilities/*66.45*/.Fechas.AAMMDD(lista1.get(2))),format.raw/*66.74*/("""</div>
								"""),_display_(/*67.10*/lista1/*67.16*/.get(2)),format.raw/*67.23*/("""
							"""),format.raw/*68.8*/("""</td>
							<td style= "text-align: center;">
								<div style="display:none">"""),_display_(/*70.36*/utilities/*70.45*/.Fechas.AAMMDD(lista1.get(3))),format.raw/*70.74*/("""</div>
								"""),_display_(/*71.10*/lista1/*71.16*/.get(3)),format.raw/*71.23*/("""
							"""),format.raw/*72.8*/("""</td>
							<td style= "text-align: center;">
								<div style="display:none">"""),_display_(/*74.36*/utilities/*74.45*/.Fechas.AAMMDD(lista1.get(4))),format.raw/*74.74*/("""</div>
								"""),_display_(/*75.10*/lista1/*75.16*/.get(4)),format.raw/*75.23*/("""
							"""),format.raw/*76.8*/("""</td>
							<td style= "text-align: center;">"""),_display_(/*77.42*/lista1/*77.48*/.get(5)),format.raw/*77.55*/("""</td>
							<td style= "text-align: left;">"""),_display_(/*78.40*/lista1/*78.46*/.get(6)),format.raw/*78.53*/("""</td>
							<td style= "text-align: left;">"""),_display_(/*79.40*/lista1/*79.46*/.get(19)),format.raw/*79.54*/("""</td>
							<td style= "text-align: left;">"""),_display_(/*80.40*/lista1/*80.46*/.get(7)),format.raw/*80.53*/("""</td>
							<td style= "text-align: right;">"""),_display_(/*81.41*/lista1/*81.47*/.get(8)),format.raw/*81.54*/("""</td>
							<td style= "text-align: right;">"""),_display_(/*82.41*/lista1/*82.47*/.get(9)),format.raw/*82.54*/("""</td>
							<td style= "text-align: right;">"""),_display_(/*83.41*/lista1/*83.47*/.get(10)),format.raw/*83.55*/("""</td>
							<td style= "text-align: center;">
								"""),_display_(if(lista1.get(11)!="0")/*85.33*/{_display_(Seq[Any](format.raw/*85.34*/("""
									"""),format.raw/*86.10*/("""<a href="#" onclick="mostrarPDF('"""),_display_(/*86.44*/lista1/*86.50*/.get(11)),format.raw/*86.58*/("""')">
		    							<kbd style="background-color: #85C1E9">pdf</kbd>
		    						</a>
								""")))}else/*89.14*/{_display_(Seq[Any](format.raw/*89.15*/("""
									"""),format.raw/*90.10*/("""--
								""")))}),format.raw/*91.10*/("""
							"""),format.raw/*92.8*/("""</td>
							<td style="text-align:center;">
								"""),_display_(if(lista1.get(12)!="0")/*94.33*/{_display_(Seq[Any](format.raw/*94.34*/("""
									"""),format.raw/*95.10*/("""<a href="#" onclick= "descargaDocumento('"""),_display_(/*95.52*/lista1/*95.58*/.get(12)),format.raw/*95.66*/("""')">
										<kbd style="background-color: #85C1E9">xls</kbd>
									</a>
								""")))}else/*98.14*/{_display_(Seq[Any](format.raw/*98.15*/("""
									"""),format.raw/*99.10*/("""--
								""")))}),format.raw/*100.10*/("""
							"""),format.raw/*101.8*/("""</td>
							"""),_display_(if(mapPermiso.get("odoProformaListaEliminar")!=null)/*102.61*/ {_display_(Seq[Any](format.raw/*102.63*/("""
								"""),format.raw/*103.9*/("""<td style="text-align:center;">
									<a href="#" onclick="eliminaProforma('"""),_display_(/*104.49*/lista1/*104.55*/.get(0)),format.raw/*104.62*/("""');">
										<kbd style="background-color: red">X</kbd>
									</a>
								</td>
							""")))} else {null} ),format.raw/*108.9*/("""
					
					"""),format.raw/*110.6*/("""</tr>
	 			""")))}),format.raw/*111.7*/("""
				"""),format.raw/*112.5*/("""</tbody>
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
	</form>
	
	<form id="form_eliminar" method="post" action="/proformaEliminaOdo/">
		<input type="hidden" id="form_id_proforma" name="id_proforma">
	</form>
	
""")))}),format.raw/*146.2*/("""


"""),format.raw/*149.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*151.31*/("""{"""),format.raw/*151.32*/("""
		
		"""),format.raw/*153.3*/("""$('#tablaPrincipal').DataTable("""),format.raw/*153.34*/("""{"""),format.raw/*153.35*/("""
		    	"""),format.raw/*154.8*/(""""fixedHeader": true,
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [[ 2, "desc" ],[ 1, "desc" ]],
		    	"language": """),format.raw/*159.20*/("""{"""),format.raw/*159.21*/("""
		        	"""),format.raw/*160.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*161.11*/("""}"""),format.raw/*161.12*/("""
		  """),format.raw/*162.5*/("""}"""),format.raw/*162.6*/(""" """),format.raw/*162.7*/(""");

			document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*165.2*/("""}"""),format.raw/*165.3*/(""");
	
	
	const mostrarPDF = (nombrePDF) => """),format.raw/*168.36*/("""{"""),format.raw/*168.37*/("""
		  """),format.raw/*169.5*/("""document.getElementById('rutaPDF').innerHTML="<object data='/showPdf/"+nombrePDF+"' type='application/pdf' width='100%' height='720'></object>";
		  $('#muestraPDF').modal('show');
	"""),format.raw/*171.2*/("""}"""),format.raw/*171.3*/("""
	
	"""),format.raw/*173.2*/("""const descargaDocumento = (nombreDOC) => """),format.raw/*173.43*/("""{"""),format.raw/*173.44*/("""
		  """),format.raw/*174.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*176.2*/("""}"""),format.raw/*176.3*/("""
	
	"""),format.raw/*178.2*/("""const eliminaProforma = (id_proforma) => """),format.raw/*178.43*/("""{"""),format.raw/*178.44*/("""
		"""),format.raw/*179.3*/("""alertify.confirm('Esta seguro de eliminar """),_display_(/*179.46*/mapDiccionario("PROFORMA")),format.raw/*179.72*/(""" """),format.raw/*179.73*/("""numero: '+id_proforma, function (e) """),format.raw/*179.109*/("""{"""),format.raw/*179.110*/("""
			"""),format.raw/*180.4*/("""if (e) """),format.raw/*180.11*/("""{"""),format.raw/*180.12*/("""
				"""),format.raw/*181.5*/("""$("#form_id_proforma").val(id_proforma);
				document.getElementById("form_eliminar").submit();
			"""),format.raw/*183.4*/("""}"""),format.raw/*183.5*/("""
		"""),format.raw/*184.3*/("""}"""),format.raw/*184.4*/(""");
	"""),format.raw/*185.2*/("""}"""),format.raw/*185.3*/("""
	
	
"""),format.raw/*188.1*/("""</script>




		
		
	
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
                  SOURCE: app/viewsMnuOdo/proformaListaOdo.scala.html
                  HASH: 1b9802cf75a1fd1d287073e91bae02b929f7c942
                  MATRIX: 1030->1|1247->125|1279->132|1295->140|1334->142|1363->146|1431->194|1459->196|1535->247|1623->315|1654->319|2900->1538|2923->1552|2958->1566|3200->1781|3240->1783|3275->1791|3338->1811|3371->1817|3434->1854|3470->1874|3509->1875|3543->1882|3616->1928|3631->1934|3659->1941|3768->2023|3786->2032|3836->2061|3879->2077|3894->2083|3922->2090|3957->2098|4066->2180|4084->2189|4134->2218|4177->2234|4192->2240|4220->2247|4255->2255|4364->2337|4382->2346|4432->2375|4475->2391|4490->2397|4518->2404|4553->2412|4627->2459|4642->2465|4670->2472|4742->2517|4757->2523|4785->2530|4857->2575|4872->2581|4901->2589|4973->2634|4988->2640|5016->2647|5089->2693|5104->2699|5132->2706|5205->2752|5220->2758|5248->2765|5321->2811|5336->2817|5365->2825|5471->2904|5510->2905|5548->2915|5609->2949|5624->2955|5653->2963|5769->3060|5808->3061|5846->3071|5889->3083|5924->3091|6028->3168|6067->3169|6105->3179|6174->3221|6189->3227|6218->3235|6328->3326|6367->3327|6405->3337|6449->3349|6485->3357|6579->3423|6620->3425|6657->3434|6765->3514|6781->3520|6810->3527|6949->3622|6989->3634|7032->3646|7065->3651|8240->4795|8271->4798|8363->4861|8393->4862|8427->4868|8487->4899|8517->4900|8553->4908|8736->5062|8766->5063|8807->5075|8914->5153|8944->5154|8977->5159|9006->5160|9035->5161|9136->5234|9165->5235|9236->5277|9266->5278|9299->5283|9509->5465|9538->5466|9570->5470|9640->5511|9670->5512|9703->5517|9835->5621|9864->5622|9896->5626|9966->5667|9996->5668|10027->5671|10098->5714|10146->5740|10176->5741|10242->5777|10273->5778|10305->5782|10341->5789|10371->5790|10404->5795|10531->5894|10560->5895|10591->5898|10620->5899|10652->5903|10681->5904|10714->5909
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|38->7|39->8|39->8|41->10|81->50|81->50|81->50|87->56|87->56|88->57|89->58|90->59|93->62|93->62|93->62|94->63|95->64|95->64|95->64|97->66|97->66|97->66|98->67|98->67|98->67|99->68|101->70|101->70|101->70|102->71|102->71|102->71|103->72|105->74|105->74|105->74|106->75|106->75|106->75|107->76|108->77|108->77|108->77|109->78|109->78|109->78|110->79|110->79|110->79|111->80|111->80|111->80|112->81|112->81|112->81|113->82|113->82|113->82|114->83|114->83|114->83|116->85|116->85|117->86|117->86|117->86|117->86|120->89|120->89|121->90|122->91|123->92|125->94|125->94|126->95|126->95|126->95|126->95|129->98|129->98|130->99|131->100|132->101|133->102|133->102|134->103|135->104|135->104|135->104|139->108|141->110|142->111|143->112|177->146|180->149|182->151|182->151|184->153|184->153|184->153|185->154|190->159|190->159|191->160|192->161|192->161|193->162|193->162|193->162|196->165|196->165|199->168|199->168|200->169|202->171|202->171|204->173|204->173|204->173|205->174|207->176|207->176|209->178|209->178|209->178|210->179|210->179|210->179|210->179|210->179|210->179|211->180|211->180|211->180|212->181|214->183|214->183|215->184|215->184|216->185|216->185|219->188
                  -- GENERATED --
              */
          