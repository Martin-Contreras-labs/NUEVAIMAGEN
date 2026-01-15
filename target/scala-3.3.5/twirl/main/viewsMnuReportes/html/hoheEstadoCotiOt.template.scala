
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

object hoheEstadoCotiOt extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
lista: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	"""),format.raw/*7.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*8.4*/barraTitulo(mapDiccionario, "ESTADO DE COTIZACIONES Y ORDENES DE TRABAJO", "")),format.raw/*8.82*/("""

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
							<button type="button"  class="btn btn-sm btn-success" onclick="document.getElementById('excel').submit()">
								Exportar a Excel
							</button>
							<button type="button"  class="btn btn-sm btn-success" 
								onclick="history.go(-1);return false;">
								Volver
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
				        <TH style= "text-align: center;">Nro<br>COTI</TH>
				        <TH style= "text-align: center;" width="80px">Fecha<br>COTI</TH>
						<TH style= "text-align: center;">Nro<br>OT</TH>
						<TH style= "text-align: center;" width="80px">Fecha<br>OT</TH>
						<TH style= "text-align: center;">Cliente</TH>
						<TH style= "text-align: center;">Proyecto</TH>
						<TH style= "text-align: center;">Bodega/Proyecto</TH>
						<TH style= "text-align: center;">Vta Pedido<br>P_Lista</TH>
						<TH style= "text-align: center;">Vta Despachado<br>P_Lista</TH>
						<TH style= "text-align: center;">Vta Pedido<br>P_Cierre</TH>
						<TH style= "text-align: center;">Vta Despachado<br>P_Cierre</TH>
						<TH style= "text-align: center;">Arr(30) Pedido<br>P_Lista</TH>
						<TH style= "text-align: center;">Arr(30) Despachado<br>P_Lista</TH>
						<TH style= "text-align: center;">Arr(30) Pedido<br>P_Cierre</TH>
						<TH style= "text-align: center;">Arr(30) Despachado<br>P_Cierre</TH>
						<TH style= "text-align: center;">Estado<br>OT</TH>					
					</TR>
				</thead>
				<tbody>
					"""),_display_(/*61.7*/for(lista1 <- lista) yield /*61.27*/{_display_(Seq[Any](format.raw/*61.28*/("""
						"""),format.raw/*62.7*/("""<tr>
							<td style="text-align:center;">"""),_display_(/*63.40*/lista1/*63.46*/.get(0)),format.raw/*63.53*/("""</td>
							<td style="text-align:center;">"""),_display_(/*64.40*/lista1/*64.46*/.get(1)),format.raw/*64.53*/("""</td>
							<td style="text-align:center;">"""),_display_(/*65.40*/lista1/*65.46*/.get(2)),format.raw/*65.53*/("""</td>
							<td style="text-align:center;">"""),_display_(/*66.40*/lista1/*66.46*/.get(3)),format.raw/*66.53*/("""</td>
							<td style="text-align:left;">"""),_display_(/*67.38*/lista1/*67.44*/.get(4)),format.raw/*67.51*/("""</td>
							<td style="text-align:left;">"""),_display_(/*68.38*/lista1/*68.44*/.get(5)),format.raw/*68.51*/("""</td>
							<td style="text-align:left;">"""),_display_(/*69.38*/lista1/*69.44*/.get(6)),format.raw/*69.51*/("""</td>
							<td style="text-align:right;">"""),_display_(/*70.39*/lista1/*70.45*/.get(7)),format.raw/*70.52*/("""</td>
							<td style="text-align:right;">"""),_display_(/*71.39*/lista1/*71.45*/.get(8)),format.raw/*71.52*/("""</td>
							<td style="text-align:right;">"""),_display_(/*72.39*/lista1/*72.45*/.get(9)),format.raw/*72.52*/("""</td>
							<td style="text-align:right;">"""),_display_(/*73.39*/lista1/*73.45*/.get(10)),format.raw/*73.53*/("""</td>
							<td style="text-align:right;">"""),_display_(/*74.39*/lista1/*74.45*/.get(11)),format.raw/*74.53*/("""</td>
							<td style="text-align:right;">"""),_display_(/*75.39*/lista1/*75.45*/.get(12)),format.raw/*75.53*/("""</td>
							<td style="text-align:right;">"""),_display_(/*76.39*/lista1/*76.45*/.get(13)),format.raw/*76.53*/("""</td>
							<td style="text-align:right;">"""),_display_(/*77.39*/lista1/*77.45*/.get(14)),format.raw/*77.53*/("""</td>
							<td style="text-align:right;">"""),_display_(/*78.39*/lista1/*78.45*/.get(17)),format.raw/*78.53*/("""</td>
						</tr>
						<tr>
							<td colspan="20" style="text-align:left;">OBS_COTI """),_display_(/*81.60*/lista1/*81.66*/.get(0)),format.raw/*81.73*/(""": """),_display_(/*81.76*/lista1/*81.82*/.get(15)),format.raw/*81.90*/("""<BR>OBS_OT """),_display_(/*81.102*/lista1/*81.108*/.get(2)),format.raw/*81.115*/(""": """),_display_(/*81.118*/lista1/*81.124*/.get(16)),format.raw/*81.132*/("""</td>
						</tr>
		 			""")))}),format.raw/*83.8*/("""
				"""),format.raw/*84.5*/("""</tbody>
			</table>
		</div>
	</div>

	<form id="excel" class="formulario" method="post" action="/hoheEstadoCotiOtExcel/">
	</form>



""")))}),format.raw/*94.2*/("""


"""),format.raw/*97.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*99.31*/("""{"""),format.raw/*99.32*/("""
			"""),format.raw/*100.4*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*101.2*/("""}"""),format.raw/*101.3*/(""");
	
	
	
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
                  SOURCE: app/viewsMnuReportes/hoheEstadoCotiOt.scala.html
                  HASH: 09c8d6b44d9cc590b75d2ef58bbb6deadf7277c5
                  MATRIX: 1035->1|1252->125|1284->132|1300->140|1339->142|1368->146|1436->194|1464->196|1540->247|1638->325|1669->329|3826->2460|3862->2480|3901->2481|3935->2488|4006->2532|4021->2538|4049->2545|4121->2590|4136->2596|4164->2603|4236->2648|4251->2654|4279->2661|4351->2706|4366->2712|4394->2719|4464->2762|4479->2768|4507->2775|4577->2818|4592->2824|4620->2831|4690->2874|4705->2880|4733->2887|4804->2931|4819->2937|4847->2944|4918->2988|4933->2994|4961->3001|5032->3045|5047->3051|5075->3058|5146->3102|5161->3108|5190->3116|5261->3160|5276->3166|5305->3174|5376->3218|5391->3224|5420->3232|5491->3276|5506->3282|5535->3290|5606->3334|5621->3340|5650->3348|5721->3392|5736->3398|5765->3406|5880->3494|5895->3500|5923->3507|5953->3510|5968->3516|5997->3524|6037->3536|6053->3542|6082->3549|6113->3552|6129->3558|6159->3566|6214->3591|6246->3596|6413->3733|6443->3736|6534->3799|6563->3800|6595->3804|6689->3870|6718->3871
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|38->7|39->8|39->8|41->10|92->61|92->61|92->61|93->62|94->63|94->63|94->63|95->64|95->64|95->64|96->65|96->65|96->65|97->66|97->66|97->66|98->67|98->67|98->67|99->68|99->68|99->68|100->69|100->69|100->69|101->70|101->70|101->70|102->71|102->71|102->71|103->72|103->72|103->72|104->73|104->73|104->73|105->74|105->74|105->74|106->75|106->75|106->75|107->76|107->76|107->76|108->77|108->77|108->77|109->78|109->78|109->78|112->81|112->81|112->81|112->81|112->81|112->81|112->81|112->81|112->81|112->81|112->81|112->81|114->83|115->84|125->94|128->97|130->99|130->99|131->100|132->101|132->101
                  -- GENERATED --
              */
          