
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

object cotizaListaResumen0 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listClientes: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalContactoCliente()),format.raw/*9.25*/("""
	"""),_display_(/*10.3*/modalContactoProyecto()),format.raw/*10.26*/("""
	
	"""),format.raw/*12.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*13.4*/barraTitulo(mapDiccionario, "RESUMEN COTIZACIONES","SELECCION DE CLIENTE")),format.raw/*13.78*/("""
			"""),format.raw/*14.4*/("""<div class="row  justify-content-md-center">
				<div class="col-xs-9 col-sm-7 col-md-7 col-lg-7">
					<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<TR>
								<TH>RUT</TH>
								<TH>CLIENTE</TH>
								<TH>SELECT</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*25.9*/for(lista1 <- listClientes) yield /*25.36*/{_display_(Seq[Any](format.raw/*25.37*/("""
								"""),format.raw/*26.9*/("""<TR>
									<td style="text-align:center;vertical-align:middle;">"""),_display_(/*27.64*/lista1/*27.70*/.get(1)),format.raw/*27.77*/("""</td>
									<td style="text-align:left;vertical-align:middle;">"""),_display_(/*28.62*/lista1/*28.68*/.get(3)),format.raw/*28.75*/("""</td>
									<td style="text-align:center;vertical-align:middle;">
										<form id="form0_"""),_display_(/*30.28*/lista1/*30.34*/.get(0)),format.raw/*30.41*/("""" class="formulario" method="post" action="/routes2/cotizaListaResumen00/">
											<input type="hidden" name="id_cliente" value=""""),_display_(/*31.59*/lista1/*31.65*/.get(0)),format.raw/*31.72*/("""">
											<a href="#" onclick="document.getElementById('form0_"""),_display_(/*32.65*/lista1/*32.71*/.get(0)),format.raw/*32.78*/("""').submit()">
												<kbd style="background-color: #73C6B6">select</kbd>
											</a>
										</form>
									</td>
								</TR>
				 			""")))}),format.raw/*38.10*/("""
						"""),format.raw/*39.7*/("""</tbody>
					</table>
				</div>
			</div>
			<div class="noprint">
				<div class="row justify-content-md-center" >
					<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
						<input type="button"  value="Cancelar" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
					</div>
				</div>
			</div>
	</div>
	
	

""")))}),format.raw/*54.2*/("""


"""),format.raw/*57.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*58.31*/("""{"""),format.raw/*58.32*/("""
		  """),format.raw/*59.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*59.36*/("""{"""),format.raw/*59.37*/("""
		    	"""),format.raw/*60.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 1, "asc" ]],
		    	"language": """),format.raw/*63.20*/("""{"""),format.raw/*63.21*/("""
		        	"""),format.raw/*64.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*65.11*/("""}"""),format.raw/*65.12*/("""
		  """),format.raw/*66.5*/("""}"""),format.raw/*66.6*/(""" """),format.raw/*66.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*68.2*/("""}"""),format.raw/*68.3*/(""");
	
	
	
	
</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listClientes:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listClientes)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listClientes) => apply(mapDiccionario,mapPermiso,userMnu,listClientes)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizar/cotizaListaResumen0.scala.html
                  HASH: 57bab47fca2caba6c95fcd70f0bc69ad11fad01c
                  MATRIX: 1037->1|1261->132|1294->140|1310->148|1349->150|1378->154|1446->202|1476->207|1518->229|1547->232|1591->255|1622->259|1699->310|1794->384|1825->388|2241->778|2284->805|2323->806|2359->815|2454->883|2469->889|2497->896|2591->963|2606->969|2634->976|2757->1072|2772->1078|2800->1085|2961->1219|2976->1225|3004->1232|3098->1299|3113->1305|3141->1312|3322->1462|3356->1469|3738->1821|3768->1824|3858->1886|3887->1887|3919->1892|3978->1923|4007->1924|4042->1932|4216->2078|4245->2079|4285->2091|4391->2169|4420->2170|4452->2175|4480->2176|4508->2177|4608->2250|4636->2251
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|41->10|41->10|43->12|44->13|44->13|45->14|56->25|56->25|56->25|57->26|58->27|58->27|58->27|59->28|59->28|59->28|61->30|61->30|61->30|62->31|62->31|62->31|63->32|63->32|63->32|69->38|70->39|85->54|88->57|89->58|89->58|90->59|90->59|90->59|91->60|94->63|94->63|95->64|96->65|96->65|97->66|97->66|97->66|99->68|99->68
                  -- GENERATED --
              */
          