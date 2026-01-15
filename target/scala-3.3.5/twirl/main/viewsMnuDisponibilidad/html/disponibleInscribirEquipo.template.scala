
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

object disponibleInscribirEquipo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Cronograma],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listadoNoInscritos: List[tables.Cronograma]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "DISPONIBILIDAD DE EQUIPOS EN "+mapDiccionario.get("BODEGAS")+"/PROYECTOS","INSCRIBIR EQUIPOS")),format.raw/*9.127*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-8 col-md-8 col-lg-8">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>GRUPO/FAMILIA</TH>
							<TH>CODIGO</TH>
							<TH>EQUIPO</TH>
							<TH>SELECCIONAR</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*22.8*/for(lista1 <- listadoNoInscritos) yield /*22.41*/{_display_(Seq[Any](format.raw/*22.42*/("""
							"""),format.raw/*23.8*/("""<TR>
								<TD style= "text-align: left;">"""),_display_(/*24.41*/lista1/*24.47*/.nombreGrupo),format.raw/*24.59*/("""</TD>
								<TD style= "text-align: left;">"""),_display_(/*25.41*/lista1/*25.47*/.codEquipo),format.raw/*25.57*/("""</TD>
								<TD style= "text-align: left;">"""),_display_(/*26.41*/lista1/*26.47*/.nombreEquipo),format.raw/*26.60*/("""</TD>
								<td style= "text-align: center;">
									<form id="form_"""),_display_(/*28.26*/lista1/*28.32*/.id_equipo),format.raw/*28.42*/("""" method="post" action="/disponibleInscribirEquipo2/">
										<input type="hidden" name="id_equipo" value=""""),_display_(/*29.57*/lista1/*29.63*/.id_equipo),format.raw/*29.73*/("""">
										<a href="#" onclick="document.getElementById('form_"""),_display_(/*30.63*/lista1/*30.69*/.id_equipo),format.raw/*30.79*/("""').submit()">
											<kbd style="background-color: #73C6B6">select</kbd>
										</a>
									</form>
								</td>
							</TR>
			 			""")))}),format.raw/*36.9*/("""
					"""),format.raw/*37.6*/("""</tbody>
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

""")))}),format.raw/*50.2*/("""


"""),format.raw/*53.1*/("""<script type="text/javascript">
	let fechaAux = "";
	$(document).ready(function() """),format.raw/*55.31*/("""{"""),format.raw/*55.32*/("""
		  """),format.raw/*56.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*56.36*/("""{"""),format.raw/*56.37*/("""
		    	"""),format.raw/*57.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 1, "desc" ],[ 3, "desc" ]],
		    	"language": """),format.raw/*60.20*/("""{"""),format.raw/*60.21*/("""
		        	"""),format.raw/*61.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*62.11*/("""}"""),format.raw/*62.12*/("""
		  """),format.raw/*63.5*/("""}"""),format.raw/*63.6*/(""" """),format.raw/*63.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*65.2*/("""}"""),format.raw/*65.3*/(""");
	
	
		
	
	
	
</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listadoNoInscritos:List[tables.Cronograma]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listadoNoInscritos)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Cronograma]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listadoNoInscritos) => apply(mapDiccionario,mapPermiso,userMnu,listadoNoInscritos)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuDisponibilidad/disponibleInscribirEquipo.scala.html
                  HASH: ffd636b27c3d29dd84ac4fd8d5e814f48ae57b5e
                  MATRIX: 1055->1|1290->143|1323->151|1339->159|1378->161|1407->165|1475->213|1503->215|1579->266|1723->389|1753->392|2196->809|2245->842|2284->843|2319->851|2391->896|2406->902|2439->914|2512->960|2527->966|2558->976|2631->1022|2646->1028|2680->1041|2780->1114|2795->1120|2826->1130|2964->1241|2979->1247|3010->1257|3102->1322|3117->1328|3148->1338|3322->1482|3355->1488|3722->1825|3752->1828|3862->1910|3891->1911|3923->1916|3982->1947|4011->1948|4046->1956|4235->2117|4264->2118|4304->2130|4410->2208|4439->2209|4471->2214|4499->2215|4527->2216|4627->2289|4655->2290
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|53->22|53->22|53->22|54->23|55->24|55->24|55->24|56->25|56->25|56->25|57->26|57->26|57->26|59->28|59->28|59->28|60->29|60->29|60->29|61->30|61->30|61->30|67->36|68->37|81->50|84->53|86->55|86->55|87->56|87->56|87->56|88->57|91->60|91->60|92->61|93->62|93->62|94->63|94->63|94->63|96->65|96->65
                  -- GENERATED --
              */
          