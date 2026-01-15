
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

object ajustesEpRptOdo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
lista: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	"""),format.raw/*7.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*8.4*/barraTitulo(mapDiccionario, "REPORTE AJUSTES EP/PROFORMAS SERVICIOS","POR " + mapDiccionario.get("BODEGA") +"/PROYECTO")),format.raw/*8.124*/("""
		"""),format.raw/*9.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-8 col-md-8 col-lg-8">
				<div class="noprint">
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
						</tr>
					</table>
				</div>
				
				<div class="table-responsive">
					<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
				        <thead style="background-color: #eeeeee">
					        <TR> 
						        <TH style= "text-align: center;vertical-align: top;">SUCURSAL</TH>
								<TH style= "text-align: center;vertical-align: top;">"""),_display_(/*33.63*/mapDiccionario/*33.77*/.get("BODEGA")),format.raw/*33.91*/("""/PROYECTO</TH>
								<TH style= "text-align: center;vertical-align: top;">NOMBRE<BR>CLIENTE</TH>
								<TH style= "text-align: center;vertical-align: top;">NOMBRE<br>PROYECTO</TH>
								<TH style= "text-align: center;vertical-align: top;">"""),_display_(/*36.63*/mapDiccionario/*36.77*/.get("COMUNA")),format.raw/*36.91*/("""<BR>PROYECTO</TH>
								<TH style= "text-align: center;vertical-align: top;width:4%">SELECT</TH>
							</TR>
						</thead>
						<tbody>
						"""),_display_(/*41.8*/for(lista1 <- lista) yield /*41.28*/{_display_(Seq[Any](format.raw/*41.29*/("""
							"""),format.raw/*42.8*/("""<TR>
								<td style="text-align:left;vertical-align:middle;">"""),_display_(/*43.61*/lista1/*43.67*/.get(14)),format.raw/*43.75*/("""</td>
								<td style="text-align:left;vertical-align:middle;">"""),_display_(/*44.61*/lista1/*44.67*/.get(5)),format.raw/*44.74*/("""</td>
								<td style="text-align:left;vertical-align:middle;">"""),_display_(/*45.61*/lista1/*45.67*/.get(7)),format.raw/*45.74*/("""</td>
								<td style="text-align:left;vertical-align:middle;">"""),_display_(/*46.61*/lista1/*46.67*/.get(8)),format.raw/*46.74*/("""</td>
								<td style="text-align:left;vertical-align:middle;">"""),_display_(/*47.61*/lista1/*47.67*/.get(9)),format.raw/*47.74*/("""</td>
								<td style="text-align:left;vertical-align:middle;">
									<form id="form_"""),_display_(/*49.26*/lista1/*49.32*/.get(1)),format.raw/*49.39*/("""" class="formulario" method="post" action="/ajustesEpRptDetalleOdo/">
										<input type="hidden" name="id_bodega" value=""""),_display_(/*50.57*/lista1/*50.63*/.get(1)),format.raw/*50.70*/("""">
										<a href="#" onclick="document.getElementById('form_"""),_display_(/*51.63*/lista1/*51.69*/.get(1)),format.raw/*51.76*/("""').submit()">
											<kbd style="background-color: #73C6B6">select</kbd>
										</a>
									</form>
								</td>
							</TR>
			 			""")))}),format.raw/*57.9*/("""
						"""),format.raw/*58.7*/("""</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	

""")))}),format.raw/*66.2*/("""


"""),format.raw/*69.1*/("""<script type="text/javascript">
	let flag = true;
	$(document).ready(function() """),format.raw/*71.31*/("""{"""),format.raw/*71.32*/("""

		 """),format.raw/*73.4*/("""$('#tablaPrincipal').DataTable("""),format.raw/*73.35*/("""{"""),format.raw/*73.36*/("""
		    	"""),format.raw/*74.8*/(""""fixedHeader": true,
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [[ 1, "asc" ]],
		    	"language": """),format.raw/*79.20*/("""{"""),format.raw/*79.21*/("""
		        	"""),format.raw/*80.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*81.11*/("""}"""),format.raw/*81.12*/("""
		  """),format.raw/*82.5*/("""}"""),format.raw/*82.6*/(""" """),format.raw/*82.7*/(""");

			document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*85.2*/("""}"""),format.raw/*85.3*/(""");
	
	
	
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
                  SOURCE: app/viewsMnuOdo/ajustesEpRptOdo.scala.html
                  HASH: 6fe4599eea14c6670ac740610c5c970c37b55ee4
                  MATRIX: 1029->1|1246->125|1278->132|1294->140|1333->142|1362->146|1430->194|1458->196|1534->247|1675->367|1704->370|2706->1345|2729->1359|2764->1373|3037->1619|3060->1633|3095->1647|3269->1795|3305->1815|3344->1816|3379->1824|3471->1889|3486->1895|3515->1903|3608->1969|3623->1975|3651->1982|3744->2048|3759->2054|3787->2061|3880->2127|3895->2133|3923->2140|4016->2206|4031->2212|4059->2219|4177->2310|4192->2316|4220->2323|4373->2449|4388->2455|4416->2462|4508->2527|4523->2533|4551->2540|4725->2684|4759->2691|4854->2756|4884->2759|4992->2839|5021->2840|5053->2845|5112->2876|5141->2877|5176->2885|5343->3024|5372->3025|5412->3037|5518->3115|5547->3116|5579->3121|5607->3122|5635->3123|5735->3196|5763->3197
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|38->7|39->8|39->8|40->9|64->33|64->33|64->33|67->36|67->36|67->36|72->41|72->41|72->41|73->42|74->43|74->43|74->43|75->44|75->44|75->44|76->45|76->45|76->45|77->46|77->46|77->46|78->47|78->47|78->47|80->49|80->49|80->49|81->50|81->50|81->50|82->51|82->51|82->51|88->57|89->58|97->66|100->69|102->71|102->71|104->73|104->73|104->73|105->74|110->79|110->79|111->80|112->81|112->81|113->82|113->82|113->82|116->85|116->85
                  -- GENERATED --
              */
          