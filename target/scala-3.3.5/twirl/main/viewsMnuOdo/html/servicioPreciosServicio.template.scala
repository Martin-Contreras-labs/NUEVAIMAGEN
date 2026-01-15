
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

object servicioPreciosServicio extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Servicio],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listServicios: List[tables.Servicio]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""

"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "MANTENCION PRECIOS EN "+mapDiccionario.get("BODEGA")+"S/PROYECTOS POR SERVICIO", "(SELECCIONAR)")),format.raw/*9.130*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>FAMILIA</TH>
							<TH>CODIGO</TH>
							<TH>SERVICIO</TH>
							<TH>SELECT</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*22.8*/for(lista1 <- listServicios) yield /*22.36*/{_display_(Seq[Any](format.raw/*22.37*/("""
							"""),format.raw/*23.8*/("""<TR>
								<td  style="text-align:left;">"""),_display_(/*24.40*/lista1/*24.46*/.getNombreClase()),format.raw/*24.63*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*25.40*/lista1/*25.46*/.getCodigo()),format.raw/*25.58*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*26.40*/lista1/*26.46*/.getNombre()),format.raw/*26.58*/("""</td>
								<td  style="text-align:center;">
									<a href="/routes2/servicioListaPreciosServicio/"""),_display_(/*28.58*/lista1/*28.64*/.getId()),format.raw/*28.72*/("""">
										<kbd style="background-color: #73C6B6">select</kbd>
									</a>
								</td>
							</TR>
			 			""")))}),format.raw/*33.9*/("""
					"""),format.raw/*34.6*/("""</tbody>
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
""")))}),format.raw/*46.2*/("""




"""),format.raw/*51.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*52.31*/("""{"""),format.raw/*52.32*/("""
		  """),format.raw/*53.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*53.36*/("""{"""),format.raw/*53.37*/("""
		    	"""),format.raw/*54.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
				"order": [[1,"asc"]],
		    	"language": """),format.raw/*57.20*/("""{"""),format.raw/*57.21*/("""
		        	"""),format.raw/*58.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*59.11*/("""}"""),format.raw/*59.12*/("""
		  """),format.raw/*60.5*/("""}"""),format.raw/*60.6*/(""" """),format.raw/*60.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*62.2*/("""}"""),format.raw/*62.3*/(""");
	
</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listServicios:List[tables.Servicio]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listServicios)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Servicio]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listServicios) => apply(mapDiccionario,mapPermiso,userMnu,listServicios)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuOdo/servicioPreciosServicio.scala.html
                  HASH: b10af4b86333fb274e605f5bb45bf9182facb6ac
                  MATRIX: 1040->1|1268->136|1296->139|1312->147|1351->149|1380->153|1448->201|1476->203|1552->254|1699->380|1729->383|2162->790|2206->818|2245->819|2280->827|2351->871|2366->877|2404->894|2476->939|2491->945|2524->957|2596->1002|2611->1008|2644->1020|2775->1124|2790->1130|2819->1138|2963->1252|2996->1258|3362->1594|3394->1599|3484->1661|3513->1662|3545->1667|3604->1698|3633->1699|3668->1707|3836->1847|3865->1848|3905->1860|4011->1938|4040->1939|4072->1944|4100->1945|4128->1946|4228->2019|4256->2020
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|53->22|53->22|53->22|54->23|55->24|55->24|55->24|56->25|56->25|56->25|57->26|57->26|57->26|59->28|59->28|59->28|64->33|65->34|77->46|82->51|83->52|83->52|84->53|84->53|84->53|85->54|88->57|88->57|89->58|90->59|90->59|91->60|91->60|91->60|93->62|93->62
                  -- GENERATED --
              */
          