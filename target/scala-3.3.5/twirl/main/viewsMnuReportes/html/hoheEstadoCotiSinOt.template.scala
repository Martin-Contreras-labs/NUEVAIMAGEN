
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

object hoheEstadoCotiSinOt extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
lista: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	"""),format.raw/*7.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*8.4*/barraTitulo(mapDiccionario, "ESTADO DE COTIZACIONES SIN ORDENES DE TRABAJO", "(confirmadas y no confirmadas)")),format.raw/*8.114*/("""

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
				        <TH style= "text-align: center;" width="100px">Fecha<br>COTI</TH>
						<TH style= "text-align: center;">Cliente</TH>
						<TH style= "text-align: center;">Proyecto</TH>
						<TH style= "text-align: center;">Vta Pedido<br>P_Lista</TH>
						<TH style= "text-align: center;">Vta Pedido<br>P_Cierre</TH>
						<TH style= "text-align: center;">Arr(30) Pedido<br>P_Lista</TH>
						<TH style= "text-align: center;">Arr(30) Pedido<br>P_Cierre</TH>
						<TH style= "text-align: center;">Fecha<br>Confirmada</TH>
						<TH style= "text-align: center;">Estado<br>Cotizacion</TH>					
					</TR>
				</thead>
				<tbody>
					"""),_display_(/*55.7*/for(lista1 <- lista) yield /*55.27*/{_display_(Seq[Any](format.raw/*55.28*/("""
						"""),format.raw/*56.7*/("""<tr>
							<td style="text-align:center;">"""),_display_(/*57.40*/lista1/*57.46*/.get(0)),format.raw/*57.53*/("""</td>
							<td style="text-align:center;">"""),_display_(/*58.40*/lista1/*58.46*/.get(1)),format.raw/*58.53*/("""</td>
							<td style="text-align:left;">"""),_display_(/*59.38*/lista1/*59.44*/.get(2)),format.raw/*59.51*/("""</td>
							<td style="text-align:left;">"""),_display_(/*60.38*/lista1/*60.44*/.get(3)),format.raw/*60.51*/("""</td>
							<td style="text-align:right;">"""),_display_(/*61.39*/lista1/*61.45*/.get(5)),format.raw/*61.52*/("""</td>
							<td style="text-align:right;">"""),_display_(/*62.39*/lista1/*62.45*/.get(6)),format.raw/*62.52*/("""</td>
							<td style="text-align:right;">"""),_display_(/*63.39*/lista1/*63.45*/.get(7)),format.raw/*63.52*/("""</td>
							<td style="text-align:right;">"""),_display_(/*64.39*/lista1/*64.45*/.get(8)),format.raw/*64.52*/("""</td>
							<td style="text-align:center;">"""),_display_(/*65.40*/lista1/*65.46*/.get(10)),format.raw/*65.54*/("""</td>
							<td style="text-align:center;">"""),_display_(/*66.40*/lista1/*66.46*/.get(11)),format.raw/*66.54*/("""</td>
						</tr>
						<tr>
							<td colspan="20" style="text-align:left;">OBS_COTI """),_display_(/*69.60*/lista1/*69.66*/.get(0)),format.raw/*69.73*/(""": """),_display_(/*69.76*/lista1/*69.82*/.get(9)),format.raw/*69.89*/("""</td>
						</tr>
		 			""")))}),format.raw/*71.8*/("""
				"""),format.raw/*72.5*/("""</tbody>
			</table>
		</div>
	</div>

	<form id="excel" class="formulario" method="post" action="/hoheEstadoCotiSinOtExcel/">
	</form>



""")))}),format.raw/*82.2*/("""


"""),format.raw/*85.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*87.31*/("""{"""),format.raw/*87.32*/("""
			"""),format.raw/*88.4*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*89.2*/("""}"""),format.raw/*89.3*/(""");
	
	
	
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
                  SOURCE: app/viewsMnuReportes/hoheEstadoCotiSinOt.scala.html
                  HASH: a5e49432de65c1b93f7b9477dd23b6e357714c55
                  MATRIX: 1038->1|1255->125|1287->132|1303->140|1342->142|1371->146|1439->194|1467->196|1543->247|1674->357|1705->361|3462->2092|3498->2112|3537->2113|3571->2120|3642->2164|3657->2170|3685->2177|3757->2222|3772->2228|3800->2235|3870->2278|3885->2284|3913->2291|3983->2334|3998->2340|4026->2347|4097->2391|4112->2397|4140->2404|4211->2448|4226->2454|4254->2461|4325->2505|4340->2511|4368->2518|4439->2562|4454->2568|4482->2575|4554->2620|4569->2626|4598->2634|4670->2679|4685->2685|4714->2693|4829->2781|4844->2787|4872->2794|4902->2797|4917->2803|4945->2810|5000->2835|5032->2840|5202->2980|5232->2983|5323->3046|5352->3047|5383->3051|5476->3117|5504->3118
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|38->7|39->8|39->8|41->10|86->55|86->55|86->55|87->56|88->57|88->57|88->57|89->58|89->58|89->58|90->59|90->59|90->59|91->60|91->60|91->60|92->61|92->61|92->61|93->62|93->62|93->62|94->63|94->63|94->63|95->64|95->64|95->64|96->65|96->65|96->65|97->66|97->66|97->66|100->69|100->69|100->69|100->69|100->69|100->69|102->71|103->72|113->82|116->85|118->87|118->87|119->88|120->89|120->89
                  -- GENERATED --
              */
          