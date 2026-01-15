
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

object ajustesEpOdo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
lista: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	"""),format.raw/*7.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*8.4*/barraTitulo(mapDiccionario, "HACER AJUSTES A EP/PROFORMAS SERVICIOS","POR " + mapDiccionario.get("BODEGA") +"/PROYECTO")),format.raw/*8.124*/("""
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
						        <TH style= "text-align: center;vertical-align: top;">"""),_display_(/*32.69*/mapDiccionario/*32.83*/.get("BODEGA")),format.raw/*32.97*/("""/PROYECTO</TH>
								<TH style= "text-align: center;vertical-align: top;">SUCURSAL</TH>
								<TH style= "text-align: center;vertical-align: top;width:4%">SELECT</TH>
							</TR>
						</thead>
						<tbody>
						"""),_display_(/*38.8*/for(lista1 <- lista) yield /*38.28*/{_display_(Seq[Any](format.raw/*38.29*/("""
							"""),format.raw/*39.8*/("""<TR>
								<td style="text-align:left;vertical-align:middle;">"""),_display_(/*40.61*/lista1/*40.67*/.get(2)),format.raw/*40.74*/("""</td>
								<td style="text-align:left;vertical-align:middle;">"""),_display_(/*41.61*/lista1/*41.67*/.get(1)),format.raw/*41.74*/("""</td>
								<td style="text-align:left;vertical-align:middle;">
									<form id="form_"""),_display_(/*43.26*/lista1/*43.32*/.get(0)),format.raw/*43.39*/("""" class="formulario" method="post" action="/ajustesEpListadoOdo/">
										<input type="hidden" name="id_bodega" value=""""),_display_(/*44.57*/lista1/*44.63*/.get(0)),format.raw/*44.70*/("""">
										<a href="#" onclick="document.getElementById('form_"""),_display_(/*45.63*/lista1/*45.69*/.get(0)),format.raw/*45.76*/("""').submit()">
											<kbd style="background-color: #73C6B6">select</kbd>
										</a>
									</form>
								</td>
							</TR>
			 			""")))}),format.raw/*51.9*/("""
						"""),format.raw/*52.7*/("""</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	

""")))}),format.raw/*60.2*/("""


"""),format.raw/*63.1*/("""<script type="text/javascript">
	let flag = true;
	$(document).ready(function() """),format.raw/*65.31*/("""{"""),format.raw/*65.32*/("""

		 """),format.raw/*67.4*/("""$('#tablaPrincipal').DataTable("""),format.raw/*67.35*/("""{"""),format.raw/*67.36*/("""
		    	"""),format.raw/*68.8*/(""""fixedHeader": true,
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [[ 1, "asc" ]],
		    	"language": """),format.raw/*73.20*/("""{"""),format.raw/*73.21*/("""
		        	"""),format.raw/*74.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*75.11*/("""}"""),format.raw/*75.12*/("""
		  """),format.raw/*76.5*/("""}"""),format.raw/*76.6*/(""" """),format.raw/*76.7*/(""");

			document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*79.2*/("""}"""),format.raw/*79.3*/(""");
	
	
	
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
                  SOURCE: app/viewsMnuOdo/ajustesEpOdo.scala.html
                  HASH: a9eec63814afe54dea83c690018fa8e3c711b230
                  MATRIX: 1026->1|1243->125|1275->132|1291->140|1330->142|1359->146|1427->194|1455->196|1531->247|1672->367|1701->370|2628->1270|2651->1284|2686->1298|2932->1518|2968->1538|3007->1539|3042->1547|3134->1612|3149->1618|3177->1625|3270->1691|3285->1697|3313->1704|3431->1795|3446->1801|3474->1808|3624->1931|3639->1937|3667->1944|3759->2009|3774->2015|3802->2022|3976->2166|4010->2173|4105->2238|4135->2241|4243->2321|4272->2322|4304->2327|4363->2358|4392->2359|4427->2367|4594->2506|4623->2507|4663->2519|4769->2597|4798->2598|4830->2603|4858->2604|4886->2605|4986->2678|5014->2679
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|38->7|39->8|39->8|40->9|63->32|63->32|63->32|69->38|69->38|69->38|70->39|71->40|71->40|71->40|72->41|72->41|72->41|74->43|74->43|74->43|75->44|75->44|75->44|76->45|76->45|76->45|82->51|83->52|91->60|94->63|96->65|96->65|98->67|98->67|98->67|99->68|104->73|104->73|105->74|106->75|106->75|107->76|107->76|107->76|110->79|110->79
                  -- GENERATED --
              */
          