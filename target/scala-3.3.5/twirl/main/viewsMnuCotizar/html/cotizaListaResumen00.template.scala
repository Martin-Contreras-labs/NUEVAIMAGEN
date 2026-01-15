
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

object cotizaListaResumen00 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listProyectos: List[List[String]], id_cliente: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalContactoCliente()),format.raw/*9.25*/("""
	"""),_display_(/*10.3*/modalContactoProyecto()),format.raw/*10.26*/("""
	
	"""),format.raw/*12.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*13.4*/barraTitulo(mapDiccionario, "RESUMEN COTIZACIONES","SELECCION DE PROYECTO")),format.raw/*13.79*/("""
			"""),format.raw/*14.4*/("""<div class="row  justify-content-md-center">
				<div class="col-xs-9 col-sm-7 col-md-7 col-lg-7">
					<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<TR>
								<TH>PROYECTO</TH>
								<TH>SELECT</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*24.9*/for(lista1 <- listProyectos) yield /*24.37*/{_display_(Seq[Any](format.raw/*24.38*/("""
								"""),format.raw/*25.9*/("""<TR>
									<td style="text-align:left;vertical-align:middle;">"""),_display_(/*26.62*/lista1/*26.68*/.get(1)),format.raw/*26.75*/("""</td>
									<td style="text-align:center;vertical-align:middle;">
										<form id="form0_"""),_display_(/*28.28*/lista1/*28.34*/.get(0)),format.raw/*28.41*/("""" class="formulario" method="post" action="/routes2/cotizaListaResumen1/">
											<input type="hidden" name="id_cliente" value=""""),_display_(/*29.59*/id_cliente),format.raw/*29.69*/("""">
											<input type="hidden" name="id_proyecto" value=""""),_display_(/*30.60*/lista1/*30.66*/.get(0)),format.raw/*30.73*/("""">
											<a href="#" onclick="document.getElementById('form0_"""),_display_(/*31.65*/lista1/*31.71*/.get(0)),format.raw/*31.78*/("""').submit()">
												<kbd style="background-color: #73C6B6">select</kbd>
											</a>
										</form>
									</td>
								</TR>
				 			""")))}),format.raw/*37.10*/("""
						"""),format.raw/*38.7*/("""</tbody>
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
	
	

""")))}),format.raw/*53.2*/("""


"""),format.raw/*56.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*57.31*/("""{"""),format.raw/*57.32*/("""
		  """),format.raw/*58.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*58.36*/("""{"""),format.raw/*58.37*/("""
		    	"""),format.raw/*59.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 0, "asc" ]],
		    	"language": """),format.raw/*62.20*/("""{"""),format.raw/*62.21*/("""
		        	"""),format.raw/*63.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*64.11*/("""}"""),format.raw/*64.12*/("""
		  """),format.raw/*65.5*/("""}"""),format.raw/*65.6*/(""" """),format.raw/*65.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*67.2*/("""}"""),format.raw/*67.3*/(""");
	
	
	
	
</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listProyectos:List[List[String]],id_cliente:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listProyectos,id_cliente)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listProyectos,id_cliente) => apply(mapDiccionario,mapPermiso,userMnu,listProyectos,id_cliente)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizar/cotizaListaResumen00.scala.html
                  HASH: c01922026e48edc216a6b16427f3a78514eeb64b
                  MATRIX: 1045->1|1290->153|1323->161|1339->169|1378->171|1407->175|1475->223|1505->228|1547->250|1576->253|1620->276|1651->280|1728->331|1824->406|1855->410|2251->780|2295->808|2334->809|2370->818|2463->884|2478->890|2506->897|2629->993|2644->999|2672->1006|2832->1139|2863->1149|2952->1211|2967->1217|2995->1224|3089->1291|3104->1297|3132->1304|3313->1454|3347->1461|3729->1813|3759->1816|3849->1878|3878->1879|3910->1884|3969->1915|3998->1916|4033->1924|4207->2070|4236->2071|4276->2083|4382->2161|4411->2162|4443->2167|4471->2168|4499->2169|4599->2242|4627->2243
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|41->10|41->10|43->12|44->13|44->13|45->14|55->24|55->24|55->24|56->25|57->26|57->26|57->26|59->28|59->28|59->28|60->29|60->29|61->30|61->30|61->30|62->31|62->31|62->31|68->37|69->38|84->53|87->56|88->57|88->57|89->58|89->58|89->58|90->59|93->62|93->62|94->63|95->64|95->64|96->65|96->65|96->65|98->67|98->67
                  -- GENERATED --
              */
          