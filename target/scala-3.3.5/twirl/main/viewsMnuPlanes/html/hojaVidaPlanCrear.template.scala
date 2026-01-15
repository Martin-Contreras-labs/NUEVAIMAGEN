
package viewsMnuPlanes.html

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

object hojaVidaPlanCrear extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Equipo],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listEquipos: List[tables.Equipo]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "SELECCION DE EQUIPOS (CREAR PLAN DE MANTENCION)", "")),format.raw/*9.86*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-10 col-md-10 col-lg-10">
				
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR> 
							 <TH style= "text-align: center;">GRUPO</TH>
					        <TH style= "text-align: center;">CODIGO</TH>
							<TH style= "text-align: center;">EQUIPO</TH>
							<TH style= "text-align: center;">UNIDAD</TH>
							<TH style= "text-align: center;">SEL</TH>
					</TR>
					</thead>
					<tbody>
						"""),_display_(/*24.8*/for(lista1 <- listEquipos) yield /*24.34*/{_display_(Seq[Any](format.raw/*24.35*/("""
							"""),format.raw/*25.8*/("""<TR>
								<td style="text-align:left;">"""),_display_(/*26.39*/lista1/*26.45*/.getGrupo()),format.raw/*26.56*/("""</td>
								<td style="text-align:center;">"""),_display_(/*27.41*/lista1/*27.47*/.getCodigo()),format.raw/*27.59*/("""</td>
								<td style="text-align:left;">"""),_display_(/*28.39*/lista1/*28.45*/.getNombre()),format.raw/*28.57*/("""</td>
								<td style="text-align:center;">"""),_display_(/*29.41*/lista1/*29.47*/.getUnidad()),format.raw/*29.59*/("""</td>
								<td style="text-align:center;">
									<form id="form_"""),_display_(/*31.26*/lista1/*31.32*/.getId()),format.raw/*31.40*/("""" method="post" action="/hojaVidaPlanCrear2/">
										<input type="hidden" name="id_equipo" value=""""),_display_(/*32.57*/lista1/*32.63*/.getId()),format.raw/*32.71*/("""">
										<a href="#" onclick='document.getElementById("form_"""),_display_(/*33.63*/lista1/*33.69*/.getId()),format.raw/*33.77*/("""").submit();'>
											<kbd style="background-color: #73C6B6">select</kbd>
										</a>
									</form>
								</td>
							</TR>
			 			""")))}),format.raw/*39.9*/("""
					"""),format.raw/*40.6*/("""</tbody>
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center">
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/hojaVidaPlanMantencion/';">
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
		    	"order": [[ 0, "asc" ],[ 1, "asc" ]],
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

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listEquipos:List[tables.Equipo]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listEquipos)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Equipo]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listEquipos) => apply(mapDiccionario,mapPermiso,userMnu,listEquipos)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuPlanes/hojaVidaPlanCrear.scala.html
                  HASH: 57d4e9273fbffe42d4c75eb17bcad3ce9e5d3450
                  MATRIX: 1035->1|1259->132|1292->140|1308->148|1347->150|1375->153|1443->201|1471->203|1547->254|1649->336|1679->339|2289->923|2331->949|2370->950|2405->958|2475->1001|2490->1007|2522->1018|2595->1064|2610->1070|2643->1082|2714->1126|2729->1132|2762->1144|2835->1190|2850->1196|2883->1208|2981->1279|2996->1285|3025->1293|3155->1396|3170->1402|3199->1410|3291->1475|3306->1481|3335->1489|3510->1634|3543->1640|3929->1996|3959->1999|4049->2061|4078->2062|4110->2067|4169->2098|4198->2099|4233->2107|4420->2266|4449->2267|4489->2279|4595->2357|4624->2358|4656->2363|4684->2364|4712->2365|4812->2438|4840->2439
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|55->24|55->24|55->24|56->25|57->26|57->26|57->26|58->27|58->27|58->27|59->28|59->28|59->28|60->29|60->29|60->29|62->31|62->31|62->31|63->32|63->32|63->32|64->33|64->33|64->33|70->39|71->40|84->53|87->56|88->57|88->57|89->58|89->58|89->58|90->59|93->62|93->62|94->63|95->64|95->64|96->65|96->65|96->65|98->67|98->67
                  -- GENERATED --
              */
          