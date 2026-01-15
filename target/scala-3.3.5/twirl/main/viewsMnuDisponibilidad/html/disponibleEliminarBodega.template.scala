
package viewsMnuDisponibilidad.html

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

object disponibleEliminarBodega extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Cronograma],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listadoInscritosBod: List[tables.Cronograma]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "DISPONIBILIDAD "+mapDiccionario.get("BODEGAS")+"/PROYECTOS","DES-INCRIBIR PROYECTOS (SUSPENDER)")),format.raw/*9.130*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-8 col-md-8 col-lg-8">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>SUCURSAL</TH>
							<TH>"""),_display_(/*16.13*/mapDiccionario/*16.27*/.get("BODEGAS")),format.raw/*16.42*/("""/PROYECTOS</TH>
							<TH>SELECCIONAR</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*21.8*/for(lista1 <- listadoInscritosBod) yield /*21.42*/{_display_(Seq[Any](format.raw/*21.43*/("""
							"""),format.raw/*22.8*/("""<TR>
								<TD style= "text-align: left;">"""),_display_(/*23.41*/lista1/*23.47*/.nameSucursal),format.raw/*23.60*/("""</TD>
								<TD style= "text-align: left;">"""),_display_(/*24.41*/lista1/*24.47*/.nombreBodega),format.raw/*24.60*/("""</TD>
								<td style= "text-align: center;">
									<form id="form_"""),_display_(/*26.26*/lista1/*26.32*/.id_bodegaEmpresa),format.raw/*26.49*/("""" method="post" action="/disponibleEliminarBodega2/">
										<input type="hidden" name="id_bodegaEmpresa" value=""""),_display_(/*27.64*/lista1/*27.70*/.id_bodegaEmpresa),format.raw/*27.87*/("""">
										<a href="#" onclick="document.getElementById('form_"""),_display_(/*28.63*/lista1/*28.69*/.id_bodegaEmpresa),format.raw/*28.86*/("""').submit()">
											<kbd style="background-color:red">suspender</kbd>
										</a>
									</form>
								</td>
							</TR>
			 			""")))}),format.raw/*34.9*/("""
					"""),format.raw/*35.6*/("""</tbody>
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

""")))}),format.raw/*48.2*/("""


"""),format.raw/*51.1*/("""<script type="text/javascript">
	let fechaAux = "";
	$(document).ready(function() """),format.raw/*53.31*/("""{"""),format.raw/*53.32*/("""
		  """),format.raw/*54.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*54.36*/("""{"""),format.raw/*54.37*/("""
		    	"""),format.raw/*55.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 1, "asc" ]],
		    	"language": """),format.raw/*58.20*/("""{"""),format.raw/*58.21*/("""
		        	"""),format.raw/*59.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*60.11*/("""}"""),format.raw/*60.12*/("""
		  """),format.raw/*61.5*/("""}"""),format.raw/*61.6*/(""" """),format.raw/*61.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*63.2*/("""}"""),format.raw/*63.3*/(""");
	
	
		
	
	
	
</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listadoInscritosBod:List[tables.Cronograma]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listadoInscritosBod)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Cronograma]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listadoInscritosBod) => apply(mapDiccionario,mapPermiso,userMnu,listadoInscritosBod)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuDisponibilidad/disponibleEliminarBodega.scala.html
                  HASH: 290eb074c6664fef8167cb77043d8225c8c07f00
                  MATRIX: 1054->1|1290->144|1323->152|1339->160|1378->162|1407->166|1475->214|1503->216|1579->267|1726->393|1756->396|2087->700|2110->714|2146->729|2262->819|2312->853|2351->854|2386->862|2458->907|2473->913|2507->926|2580->972|2595->978|2629->991|2729->1064|2744->1070|2782->1087|2926->1204|2941->1210|2979->1227|3071->1292|3086->1298|3124->1315|3296->1457|3329->1463|3696->1800|3726->1803|3836->1885|3865->1886|3897->1891|3956->1922|3985->1923|4020->1931|4194->2077|4223->2078|4263->2090|4369->2168|4398->2169|4430->2174|4458->2175|4486->2176|4586->2249|4614->2250
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|47->16|47->16|47->16|52->21|52->21|52->21|53->22|54->23|54->23|54->23|55->24|55->24|55->24|57->26|57->26|57->26|58->27|58->27|58->27|59->28|59->28|59->28|65->34|66->35|79->48|82->51|84->53|84->53|85->54|85->54|85->54|86->55|89->58|89->58|90->59|91->60|91->60|92->61|92->61|92->61|94->63|94->63
                  -- GENERATED --
              */
          