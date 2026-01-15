
package viewsMnuCompras.html

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

object compraImportIconstruye1 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listaOC: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "PROCESO IMPORTAR COMPRAS (DESDE ICONSTRUYE)","SELECCIONAR ORDEN DE COMPRA")),format.raw/*9.108*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>NRO OC</TH>
							<TH>CENTRO GESTION</TH>
							<TH>PROVEEDOR</TH>
							<TH>SOLICITANTE</TH>
							<TH>IMPORTAR</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*23.8*/for(lista1 <- listaOC) yield /*23.30*/{_display_(Seq[Any](format.raw/*23.31*/("""
							"""),format.raw/*24.8*/("""<TR>
								<td  style="text-align:center;">"""),_display_(/*25.42*/lista1/*25.48*/.get(1)),format.raw/*25.55*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*26.40*/lista1/*26.46*/.get(2)),format.raw/*26.53*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*27.40*/lista1/*27.46*/.get(3)),format.raw/*27.53*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*28.40*/lista1/*28.46*/.get(4)),format.raw/*28.53*/("""</td>
								<td  style="text-align:center;">
									<form id=""""),_display_(/*30.21*/lista1/*30.27*/.get(0)),format.raw/*30.34*/("""" action="/compraImportIconstruye2/" method="POST">
										<input type="hidden" name="idDocumento" value=""""),_display_(/*31.59*/lista1/*31.65*/.get(0)),format.raw/*31.72*/("""">
										<a href="#" onclick="$('#"""),_display_(/*32.37*/lista1/*32.43*/.get(0)),format.raw/*32.50*/("""').submit()">
											<kbd style="background-color: #73C6B6">import</kbd>
										</a>
									</form>
								</td>
								
							</TR>
			 			""")))}),format.raw/*39.9*/("""
					"""),format.raw/*40.6*/("""</tbody>
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="CANCELAR" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
				</div>
			</div>
		</div>
	</div>
	
	<form class="formulario" id="formDescargaDocumento" method="post" action="/downloadPDF/">	
		<input type="hidden" id="descargaDocumento" name="nombreArchivo">
	</form>
	
	

""")))}),format.raw/*59.2*/("""


"""),format.raw/*62.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*63.31*/("""{"""),format.raw/*63.32*/("""
		  """),format.raw/*64.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*64.36*/("""{"""),format.raw/*64.37*/("""
		    	"""),format.raw/*65.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 0, "asc" ]],
		    	"language": """),format.raw/*68.20*/("""{"""),format.raw/*68.21*/("""
		        	"""),format.raw/*69.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*70.11*/("""}"""),format.raw/*70.12*/("""
		  """),format.raw/*71.5*/("""}"""),format.raw/*71.6*/(""" """),format.raw/*71.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*73.2*/("""}"""),format.raw/*73.3*/(""");
	
	
	const descargaDocumento = (nombreDOC) => """),format.raw/*76.43*/("""{"""),format.raw/*76.44*/("""
		  """),format.raw/*77.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*79.2*/("""}"""),format.raw/*79.3*/("""
	
	
		
		
	
	
	
"""),format.raw/*87.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listaOC:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listaOC)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listaOC) => apply(mapDiccionario,mapPermiso,userMnu,listaOC)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCompras/compraImportIconstruye1.scala.html
                  HASH: 693512ef7213468fae0bd15c8c692b1738146400
                  MATRIX: 1041->1|1260->127|1293->135|1309->143|1348->145|1377->149|1445->197|1473->199|1549->250|1674->354|1704->357|2179->806|2217->828|2256->829|2291->837|2364->883|2379->889|2407->896|2479->941|2494->947|2522->954|2594->999|2609->1005|2637->1012|2709->1057|2724->1063|2752->1070|2846->1137|2861->1143|2889->1150|3026->1260|3041->1266|3069->1273|3135->1312|3150->1318|3178->1325|3361->1478|3394->1484|3939->1999|3969->2002|4059->2064|4088->2065|4120->2070|4179->2101|4208->2102|4243->2110|4417->2256|4446->2257|4486->2269|4592->2347|4621->2348|4653->2353|4681->2354|4709->2355|4809->2428|4837->2429|4914->2478|4943->2479|4975->2484|5106->2588|5134->2589|5178->2606
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|54->23|54->23|54->23|55->24|56->25|56->25|56->25|57->26|57->26|57->26|58->27|58->27|58->27|59->28|59->28|59->28|61->30|61->30|61->30|62->31|62->31|62->31|63->32|63->32|63->32|70->39|71->40|90->59|93->62|94->63|94->63|95->64|95->64|95->64|96->65|99->68|99->68|100->69|101->70|101->70|102->71|102->71|102->71|104->73|104->73|107->76|107->76|108->77|110->79|110->79|118->87
                  -- GENERATED --
              */
          