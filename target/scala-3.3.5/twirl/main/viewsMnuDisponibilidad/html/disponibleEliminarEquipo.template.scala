
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

object disponibleEliminarEquipo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Cronograma],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listadoInscritos: List[tables.Cronograma]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "DISPONIBILIDAD DE EQUIPOS EN "+mapDiccionario.get("BODEGAS")+"/PROYECTOS","DES-INCRIBIR EQUIPOS (SUSPENDER)")),format.raw/*9.142*/("""
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
						"""),_display_(/*22.8*/for(lista1 <- listadoInscritos) yield /*22.39*/{_display_(Seq[Any](format.raw/*22.40*/("""
							"""),format.raw/*23.8*/("""<TR>
								<TD style= "text-align: left;">"""),_display_(/*24.41*/lista1/*24.47*/.nombreGrupo),format.raw/*24.59*/("""</TD>
								<TD style= "text-align: left;">"""),_display_(/*25.41*/lista1/*25.47*/.codEquipo),format.raw/*25.57*/("""</TD>
								<TD style= "text-align: left;">"""),_display_(/*26.41*/lista1/*26.47*/.nombreEquipo),format.raw/*26.60*/("""</TD>
								<td style= "text-align: center;">
									<form id="form_"""),_display_(/*28.26*/lista1/*28.32*/.id_equipo),format.raw/*28.42*/("""" method="post" action="/disponibleEliminarEquipo2/">
										<input type="hidden" name="id_equipo" value=""""),_display_(/*29.57*/lista1/*29.63*/.id_equipo),format.raw/*29.73*/("""">
										<a href="#" onclick="document.getElementById('form_"""),_display_(/*30.63*/lista1/*30.69*/.id_equipo),format.raw/*30.79*/("""').submit()">
											<kbd style="background-color:red">suspender</kbd>
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
		    	"order": [[ 1, "asc" ],[ 3, "asc" ]],
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

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listadoInscritos:List[tables.Cronograma]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listadoInscritos)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Cronograma]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listadoInscritos) => apply(mapDiccionario,mapPermiso,userMnu,listadoInscritos)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuDisponibilidad/disponibleEliminarEquipo.scala.html
                  HASH: 134171f2a4e96fcc35a71854754c05b1235a40f3
                  MATRIX: 1054->1|1287->141|1320->149|1336->157|1375->159|1404->163|1472->211|1500->213|1576->264|1735->402|1765->405|2208->822|2255->853|2294->854|2329->862|2401->907|2416->913|2449->925|2522->971|2537->977|2568->987|2641->1033|2656->1039|2690->1052|2790->1125|2805->1131|2836->1141|2973->1251|2988->1257|3019->1267|3111->1332|3126->1338|3157->1348|3329->1490|3362->1496|3729->1833|3759->1836|3869->1918|3898->1919|3930->1924|3989->1955|4018->1956|4053->1964|4240->2123|4269->2124|4309->2136|4415->2214|4444->2215|4476->2220|4504->2221|4532->2222|4632->2295|4660->2296
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|53->22|53->22|53->22|54->23|55->24|55->24|55->24|56->25|56->25|56->25|57->26|57->26|57->26|59->28|59->28|59->28|60->29|60->29|60->29|61->30|61->30|61->30|67->36|68->37|81->50|84->53|86->55|86->55|87->56|87->56|87->56|88->57|91->60|91->60|92->61|93->62|93->62|94->63|94->63|94->63|96->65|96->65
                  -- GENERATED --
              */
          