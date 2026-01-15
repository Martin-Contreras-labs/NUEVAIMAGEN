
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

object disponibleInscribirBodega extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Cronograma],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listadoNoInscritosBod: List[tables.Cronograma]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "DISPONIBILIDAD "+mapDiccionario.get("BODEGAS")+"/PROYECTOS","INSCRIBIR PROYECTOS")),format.raw/*9.115*/("""
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
						"""),_display_(/*21.8*/for(lista1 <- listadoNoInscritosBod) yield /*21.44*/{_display_(Seq[Any](format.raw/*21.45*/("""
							"""),format.raw/*22.8*/("""<TR>
								<TD style= "text-align: left;">"""),_display_(/*23.41*/lista1/*23.47*/.nameSucursal),format.raw/*23.60*/("""</TD>
								<TD style= "text-align: left;">"""),_display_(/*24.41*/lista1/*24.47*/.nombreBodega),format.raw/*24.60*/("""</TD>
								<td style= "text-align: center;">
									<form id="form_"""),_display_(/*26.26*/lista1/*26.32*/.id_bodegaEmpresa),format.raw/*26.49*/("""" method="post" action="/disponibleInscribirBodega2/">
										<input type="hidden" name="id_bodegaEmpresa" value=""""),_display_(/*27.64*/lista1/*27.70*/.id_bodegaEmpresa),format.raw/*27.87*/("""">
										<a href="#" onclick="document.getElementById('form_"""),_display_(/*28.63*/lista1/*28.69*/.id_bodegaEmpresa),format.raw/*28.86*/("""').submit()">
											<kbd style="background-color: #73C6B6">select</kbd>
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
		    	"order": [[ 2, "desc" ],[ 1, "asc" ]],
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

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listadoNoInscritosBod:List[tables.Cronograma]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listadoNoInscritosBod)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Cronograma]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listadoNoInscritosBod) => apply(mapDiccionario,mapPermiso,userMnu,listadoNoInscritosBod)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuDisponibilidad/disponibleInscribirBodega.scala.html
                  HASH: 8438fe668f4365e9aa3a166359408ece5ef04b28
                  MATRIX: 1055->1|1293->146|1326->154|1342->162|1381->164|1410->168|1478->216|1506->218|1582->269|1714->380|1744->383|2075->687|2098->701|2134->716|2250->806|2302->842|2341->843|2376->851|2448->896|2463->902|2497->915|2570->961|2585->967|2619->980|2719->1053|2734->1059|2772->1076|2917->1194|2932->1200|2970->1217|3062->1282|3077->1288|3115->1305|3289->1449|3322->1455|3689->1792|3719->1795|3829->1877|3858->1878|3890->1883|3949->1914|3978->1915|4013->1923|4201->2083|4230->2084|4270->2096|4376->2174|4405->2175|4437->2180|4465->2181|4493->2182|4593->2255|4621->2256
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|47->16|47->16|47->16|52->21|52->21|52->21|53->22|54->23|54->23|54->23|55->24|55->24|55->24|57->26|57->26|57->26|58->27|58->27|58->27|59->28|59->28|59->28|65->34|66->35|79->48|82->51|84->53|84->53|85->54|85->54|85->54|86->55|89->58|89->58|90->59|91->60|91->60|92->61|92->61|92->61|94->63|94->63
                  -- GENERATED --
              */
          